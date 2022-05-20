package com.bachue.snr.prosnr01.business.crps;

import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.CatalogoType;
import co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.Catalogos;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.utils.FTPUtils;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.view.CRPSCabeceraDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr04.model.npa.RegistroPago;

import com.bachue.snr.prosnr21.model.pgn.CRPSCabecera;
import com.bachue.snr.prosnr21.model.pgn.CRPSDetalle;

import com.jcraft.jsch.ChannelSftp;

import java.math.BigInteger;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase la generación de archivos CRPS
 *
 * @author Edgar Prieto
 */
public class CRPSBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CRPSBusiness.class);

	/**
	 * Generar el archivo CRPS (cabecera y detalle) para una fecha específica, dejando el resultado en el FTP cuyos
	 * datos estan parametrizados en el módulo de conciliaciones
	 *
	 * @param ad_fecha Fecha de transacción bancaria sobre la cual se extraeran los registros en el archivo CRPS
	 * @param as_remoteIp de as remote ip
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void generarCRPS(Date ad_fecha, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		if(ad_fecha != null)
		{
			boolean lb_alreadyProcessing;
			String  ls_idConstanteBloqueo;

			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_VALIDO);

			ls_idConstanteBloqueo = com.bachue.snr.prosnr01.common.constants.ConstanteCommon.JOB_CRPS_BLOQUEO;

			{
				DAOManager ldm_processing;

				ldm_processing = DaoManagerFactory.getDAOManager();

				ldm_processing.setAutoCommit(true);

				try
				{
					ConstantesDAO lcd_constant;

					lcd_constant             = DaoCreator.getConstantesDAO(ldm_processing);
					lb_alreadyProcessing     = BooleanUtils.getBooleanValue(
						    obtenerConstanteCaracter(lcd_constant, ls_idConstanteBloqueo)
						);

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_idConstanteBloqueo, EstadoCommon.S, as_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("generarCRPS", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}

			{
				ChannelSftp lcs_canal;
				DAOManager  ldm_manager;

				lcs_canal       = null;
				ldm_manager     = DaoManagerFactory.getDAOManager();

				try
				{
					if(!lb_alreadyProcessing)
					{
						ConstantesDAO       lcd_constante;
						CRPSCabeceraDAO     lcrpsd_cabecera;
						Map<String, String> lmss_catalogo;
						String              ls_crpsc;
						String              ls_crpsd;
						String              ls_rutaBaseCrps;

						lcd_constante       = DaoCreator.getConstantesDAO(ldm_manager);
						lcrpsd_cabecera     = DaoCreator.getCRPSCabeceraDAO(ldm_manager);
						lmss_catalogo       = obtenerCatalogo();
						ls_crpsd            = null;
						ls_rutaBaseCrps     = null;

						lcrpsd_cabecera.actualizarParaEnvioConciliacion(ad_fecha, as_userId, as_remoteIp);

						{
							int    li_decimales;
							String ls_formatoFecha;
							String ls_formatoMoneda;
							String ls_separador;

							li_decimales     = obtenerConstanteEntero(
								    lcd_constante,
								    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.FORMATO_MONEDA_DECIMALES_CRPS_CABECERA
								);

							ls_formatoFecha     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.FORMATO_FECHA_CRPS_CABECERA
								);

							ls_formatoMoneda     = obtenerConstanteCaracter(
								    lcd_constante,
								    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.FORMATO_MONEDA_CRPS_CABECERA
								);

							ls_separador     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SEPARADOR_ARCHIVO_CRPS_CABECERA
								);

							ls_crpsc = obtenerCRPSCabecera(
								    lcrpsd_cabecera.consultarParaEnvioConciliacio(), ls_formatoFecha, ls_formatoMoneda,
								    li_decimales, ls_separador
								);
						}

						{
							String ls_rutaBase;
							String ls_plantillaRutaCrps;

							lcs_canal     = FTPUtils.abrirConexionFTP(
								    obtenerValorCatalogo(
								        lmss_catalogo,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_IP
								    ),
								    obtenerValorCatalogo(
								        lmss_catalogo,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_USUARIO
								    ),
								    obtenerValorCatalogo(
								        lmss_catalogo,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_CLAVE
								    ),
								    obtenerValorCatalogo(
								        lmss_catalogo,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_PUERTO,
								        false
								    )
								);

							ls_plantillaRutaCrps     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_RUTA_CRPS
								);

							ls_rutaBase     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE
								);

							ls_rutaBaseCrps = obtenerRutaCompleta(
								    ls_rutaBase, resolver(ls_plantillaRutaCrps, ad_fecha)
								);

							FTPUtils.crearDirectorioFTP(
							    lcs_canal, ls_rutaBaseCrps,
							    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO
							);
						}

						if(StringUtils.isValidString(ls_crpsc))
						{
							int    li_decimales;
							String ls_formatoFecha;
							String ls_formatoMoneda;
							String ls_separador;

							li_decimales     = obtenerConstanteEntero(
								    lcd_constante,
								    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.FORMATO_MONEDA_DECIMALES_CRPS_DETALLE
								);

							ls_formatoFecha     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.FORMATO_FECHA_CRPS_DETALLE
								);

							ls_formatoMoneda     = obtenerConstanteCaracter(
								    lcd_constante,
								    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.FORMATO_MONEDA_CRPS_DETALLE
								);

							ls_separador     = obtenerValorCatalogo(
								    lmss_catalogo,
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SEPARADOR_ARCHIVO_CRPS_DETALLE
								);

							ls_crpsd = obtenerCRPSDetalle(
								    DaoCreator.getCRPSDetalleDAO(ldm_manager).consultarParaEnvioConciliacio(),
								    ls_formatoFecha, ls_formatoMoneda, li_decimales, ls_separador
								);

							if(StringUtils.isValidString(ls_crpsd))
								lcrpsd_cabecera.actualizarEnviadoConciliacion(as_userId, as_remoteIp);
							else
								ldm_manager.setRollbackOnly();
						}

						if(StringUtils.isValidString(ls_rutaBaseCrps))
						{
							if(!StringUtils.isValidString(ls_crpsc))
								ls_crpsc = IdentificadoresCommon.ESPACIO_VACIO;

							if(!StringUtils.isValidString(ls_crpsd))
								ls_crpsd = IdentificadoresCommon.ESPACIO_VACIO;

							cargarArchivoFTP(
							    lcs_canal, lmss_catalogo, ls_crpsc, ad_fecha,
							    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_CRPS_CABECERA,
							    ls_rutaBaseCrps
							);

							cargarArchivoFTP(
							    lcs_canal, lmss_catalogo, ls_crpsd, ad_fecha,
							    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_CRPS_DETALLE,
							    ls_rutaBaseCrps
							);
						}

						if(!StringUtils.isValidString(ls_crpsc))
						{
							Collection<RegistroPago> lcrp_data;
							String[]                 lsa_arg;
							DateFormat               ldf_dateFormat;

							lcrp_data          = DaoCreator.getRegistroPagoDAO(ldm_manager)
									                           .findByFechaBancariaEnviadoConciliacion(ad_fecha);
							ldf_dateFormat     = new SimpleDateFormat("yyyy/MM/dd");
							lsa_arg            = new String[1];
							lsa_arg[0]         = ldf_dateFormat.format(ad_fecha);

							if(CollectionUtils.isValidCollection(lcrp_data))
								throw new B2BException(ErrorKeys.ERROR_CRPS_YA_GENERADO, lsa_arg);
							else
								throw new B2BException(ErrorKeys.ERROR_CRPS_NO_DATA, lsa_arg);
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("generarCRPS", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					if(!lb_alreadyProcessing)
					{
						DAOManager ldm_processing;

						ldm_processing = DaoManagerFactory.getDAOManager();

						ldm_processing.setAutoCommit(true);

						try
						{
							DaoCreator.getConstantesDAO(ldm_processing)
								          .updateString(ls_idConstanteBloqueo, EstadoCommon.N, as_userId);
						}
						catch(B2BException lb2be_e)
						{
							ldm_processing.setRollbackOnly();

							clh_LOGGER.error("generarCRPS", lb2be_e);

							throw lb2be_e;
						}
						finally
						{
							ldm_processing.close();
						}
					}

					if(ldm_manager != null)
						ldm_manager.close();

					FTPUtils.cerrarConexionFTP(lcs_canal);
				}
			}
		}
	}

	/**
	 * Adicionar al final de  un flujo de caracteres el valor suministrado de un campo de tipo String
	 * @param asb_archivo Flujo de caracter al que se adicionara el valor del campo de tipo String
	 * @param as_separador Caracter de separacion entre campos
	 * @param ad_campo Campo de tipo String a adicionar
	 */
	private void adicionar(StringBuilder asb_archivo, String as_separador, String as_campo)
	{
		asb_archivo.append(StringUtils.getStringNotNull(as_campo));
		asb_archivo.append(as_separador);
	}

	/**
	 * Adicionar al final de  un flujo de caracteres el valor suministrado de un campo de tipo Date
	 * @param asb_archivo Flujo de caracter al que se adicionara el valor del campo de tipo Date
	 * @param as_separador Caracter de separacion entre campos
	 * @param ad_campo Campo de tipo Date a adicionar
	 * @param af_formato Formato utilizado para la conversión a texto del campo tipo Date
	 */
	private void adicionar(StringBuilder asb_archivo, String as_separador, Date ad_campo, Format af_formato)
	{
		if(ad_campo != null)
			asb_archivo.append(af_formato.format(ad_campo));

		asb_archivo.append(as_separador);
	}

	/**
	 * Adicionar al final de  un flujo de caracteres el valor suministrado de un campo de tipo Integer
	 * @param asb_archivo Flujo de caracter al que se adicionara el valor del campo de tipo Integer
	 * @param as_separador Caracter de separacion entre campos
	 * @param ad_campo Campo de tipo Integer a adicionar
	 */
	private void adicionar(StringBuilder asb_archivo, String as_separador, Integer ai_campo)
	{
		if(ai_campo != null)
			asb_archivo.append(ai_campo);

		asb_archivo.append(as_separador);
	}

	/**
	 * Adicionar al final de  un flujo de caracteres el valor suministrado de un campo de tipo Double
	 * @param asb_archivo Flujo de caracter al que se adicionara el valor del campo de tipo Double
	 * @param as_separador Caracter de separacion entre campos
	 * @param ad_campo Campo de tipo Double a adicionar
	 * @param af_formato Formato utilizado para la conversión a texto del campo tipo Double
	 */
	private void adicionar(StringBuilder asb_archivo, String as_separador, Double ad_campo, Format af_formato)
	{
		if(ad_campo != null)
			asb_archivo.append(af_formato.format(ad_campo));

		asb_archivo.append(as_separador);
	}

	/**
	 * Cargar al FTP el contendido de un archivo
	 *
	 * @param acs_canal Objeto de conexión al servidor FTP
	 * @param amss_catalogo Mapa de catalogos con parametros
	 * @param as_archivo Contenido del archivo a subir al FTP
	 * @param ad_fecha Fecha del proceso de extracción de CRPS
	 * @param as_idPlantilla id de plantilla de nombre de archivo destino en el FTP
	 * @param as_rutaBase Ruta del FTP donde se debe dejar el archivo
	 * @throws B2BException
	 */
	private void cargarArchivoFTP(
	    ChannelSftp acs_canal, Map<String, String> amss_catalogo, String as_archivo, Date ad_fecha,
	    String as_idPlantilla, String as_rutaBase
	)
	    throws B2BException
	{
		byte[] lba_archivo;
		String ls_nombreArchivo;
		String ls_rutaArchivo;
		String ls_plantillaNombreArchivo;

		ls_plantillaNombreArchivo     = obtenerValorCatalogo(amss_catalogo, as_idPlantilla);
		ls_nombreArchivo              = resolver(ls_plantillaNombreArchivo, ad_fecha);
		ls_rutaArchivo                = obtenerRutaCompleta(as_rutaBase, ls_nombreArchivo);

		try
		{
			lba_archivo = FTPUtils.descargarArchivoFTP(acs_canal, ls_rutaArchivo);
		}
		catch(Exception le_e)
		{
			lba_archivo = null;
		}

		if(lba_archivo == null)
			FTPUtils.cargarArchivoFTP(acs_canal, as_archivo, ls_rutaArchivo);
	}

	/**
	 * Generar el contenido del archivo CRPS cabecera a partir de los registros obtenidos en base de daos
	 *
	 * @param acrpsc_registros Listado de registros a incluir en el contenido del archivo
	 * @param as_formatoFecha Formato de texto para los campos tipo fecha
	 * @param as_formatoMoneda Formato de texto para los campos tipo moneda
	 * @param ai_formatoMonedaDecimales Cantidad de decimales para los campos tipo moneda
	 * @param as_separador Separador de campos que se debe utiliara
	 * @return Cadena de texto con el contenido del archivo CRPS cabecera
	 * @throws B2BException
	 */
	private String obtenerCRPSCabecera(
	    Collection<CRPSCabecera> acrpsc_registros, String as_formatoFecha, String as_formatoMoneda,
	    int ai_formatoMonedaDecimales, String as_separador
	)
	    throws B2BException
	{
		String ls_crpsc;

		ls_crpsc = null;

		if(CollectionUtils.isValidCollection(acrpsc_registros))
		{
			DecimalFormat    ldf_crpsc;
			SimpleDateFormat lsdf_crpsc;
			StringBuilder    lsb_crpsc;

			ldf_crpsc      = (as_formatoMoneda != null) ? new DecimalFormat(as_formatoMoneda) : new DecimalFormat();
			lsb_crpsc      = new StringBuilder();
			lsdf_crpsc     = (as_formatoFecha != null) ? new SimpleDateFormat(as_formatoFecha) : new SimpleDateFormat();

			if(ai_formatoMonedaDecimales > 0)
				ldf_crpsc.setDecimalSeparatorAlwaysShown(true);

			ldf_crpsc.setMaximumFractionDigits(ai_formatoMonedaDecimales);
			ldf_crpsc.setMinimumFractionDigits(ai_formatoMonedaDecimales);

			lsdf_crpsc.setLenient(false);

			for(CRPSCabecera lrpsc_registro : acrpsc_registros)
			{
				if(lrpsc_registro != null)
				{
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdRegistroPago());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdSolicitud());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getFechaSolicitud(), lsdf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNir());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdProceso());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNombreProceso());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdSubproceso());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNombreSubproceso());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getDigitalizada());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdUsuarioCreacionSolicitud());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdDocumentoTipo());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNumeroDocumento());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdTipoPersona());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getPrimerNombre());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getSegundoNombre());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getPrimerApellido());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getSegundoApellido());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getRazonSocial());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdLiquidacion());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getConsecutivoLiquidacion());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getFechaLiquidacion(), lsdf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getValorLiquidacion(), ldf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getValorImpuestoLiquidacion(), ldf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getValorTotalLiquidacion(), ldf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getValorConservacionDocumental(), ldf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNumeroReferencia());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdTipoCanal());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdEntidadRecaudo());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdSucursalRecaudo());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNumeroCuentaRecaudo());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getIdTipoRecaudo());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getFechaBancaria(), lsdf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getMontoTransaccion(), ldf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getCodigoTransaccionRecaudador());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getFechaTransaccion(), lsdf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getNumeroReciboCaja());
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getFechaGeneracionRecibo(), lsdf_crpsc);
					adicionar(lsb_crpsc, as_separador, lrpsc_registro.getEstadoPago());

					lsb_crpsc.append("\n");
				}
			}

			ls_crpsc = lsb_crpsc.toString();
		}

		return ls_crpsc;
	}

	/**
	 * Generar el contenido del archivo CRPS detalle a partir de los registros obtenidos en base de daos
	 *
	 * @param acrpsd_registros Listado de registros a incluir en el contenido del archivo
	 * @param as_formatoFecha Formato de texto para los campos tipo fecha
	 * @param as_formatoMoneda Formato de texto para los campos tipo moneda
	 * @param ai_formatoMonedaDecimales Cantidad de decimales para los campos tipo moneda
	 * @param as_separador Separador de campos que se debe utiliara
	 * @return Cadena de texto con el contenido del archivo CRPS detalle
	 * @throws B2BException
	 */
	private String obtenerCRPSDetalle(
	    Collection<CRPSDetalle> acrpsd_registros, String as_formatoFecha, String as_formatoMoneda,
	    int ai_formatoMonedaDecimales, String as_separador
	)
	    throws B2BException
	{
		String ls_crpsd;

		ls_crpsd = null;

		if(CollectionUtils.isValidCollection(acrpsd_registros))
		{
			StringBuilder    lsb_crpsd;
			DecimalFormat    ldf_crpsd;
			SimpleDateFormat lsdf_crpsd;

			ldf_crpsd      = (as_formatoMoneda != null) ? new DecimalFormat(as_formatoMoneda) : new DecimalFormat();
			lsb_crpsd      = new StringBuilder();
			lsdf_crpsd     = (as_formatoFecha != null) ? new SimpleDateFormat(as_formatoFecha) : new SimpleDateFormat();

			if(ai_formatoMonedaDecimales > 0)
				ldf_crpsd.setDecimalSeparatorAlwaysShown(true);

			ldf_crpsd.setMaximumFractionDigits(ai_formatoMonedaDecimales);
			ldf_crpsd.setMinimumFractionDigits(ai_formatoMonedaDecimales);

			lsdf_crpsd.setLenient(false);

			for(CRPSDetalle lrpsd_registro : acrpsd_registros)
			{
				if(lrpsd_registro != null)
				{
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdRegistroPago());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdLiquidacion());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getConsecutivoLiquidacion());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdItem());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdActo());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdCirculo());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdTipoActo());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getVersion());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdProceso());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getNombreProceso());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdSubproceso());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getNombreSubproceso());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getCantidad());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getValorLiquidacion(), ldf_crpsd);
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getValorImpuestoLiquidacion(), ldf_crpsd);
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getValorTotalLiquidacion(), ldf_crpsd);
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getValorConservacionDocumental(), ldf_crpsd);
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdTurno());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdCirculoTurno());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdMatriculaCertificado());
					adicionar(lsb_crpsd, as_separador, lrpsd_registro.getIdMatriculaSegregacion());

					lsb_crpsd.append("\n");
				}
			}

			ls_crpsd = lsb_crpsd.toString();
		}

		return ls_crpsd;
	}

	/**
	 * @return Listado de parametrización de generación de archivos CRPS, en formato mapa, extraído como consulta de
	 * catalogo a través del bus
	 * @throws B2BException
	 */
	private Map<String, String> obtenerCatalogo()
	    throws B2BException
	{
		Map<String, String>          lmss_catalogo;
		TipoSalidaConsultarCatalogos ltscc_catalogo;

		lmss_catalogo      = new HashMap<String, String>();
		ltscc_catalogo     = consultarCatalogos(
			    Catalogos.CRPS, com.bachue.snr.prosnr01.common.constants.ConstanteCommon.MODULO_BACHUE, null,
			    DaoManagerFactory.getDAOManager()
			);

		if(ltscc_catalogo != null)
		{
			CatalogoType[] lcta_catalogos;

			lcta_catalogos = ltscc_catalogo.getCatalogos();

			if(lcta_catalogos != null)
			{
				for(CatalogoType lct_catalogo : lcta_catalogos)
				{
					if(lct_catalogo != null)
						lmss_catalogo.put(lct_catalogo.getCodigo(), lct_catalogo.getNombre());
				}
			}
		}

		return lmss_catalogo;
	}

	/**
	 * Obtener el valor del campo ENTERO de una constante en base de datos
	 * @param acd_constante DAO de constantes embebido en transacción
	 * @param as_idConstante Id de la constante a extraer
	 * @return Campo ENTERO de la constante identificada con as_idConstante
	 * @throws B2BException si la constante no existe en la base de datso
	 */
	private int obtenerConstanteEntero(ConstantesDAO acd_constante, String as_idConstante)
	    throws B2BException
	{
		Constantes lc_constante;
		BigInteger lbi_valorConstante;

		lc_constante           = acd_constante.findById(as_idConstante);
		lbi_valorConstante     = (lc_constante != null) ? lc_constante.getEntero() : null;

		if(lbi_valorConstante == null)
		{
			Object[] loa_messageArgs;

			loa_messageArgs        = new String[1];
			loa_messageArgs[0]     = as_idConstante;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
		}

		return lbi_valorConstante.intValue();
	}

	/**
	 * Obtener el valor de una entrada del catalogo
	 * @param amss_catalogo Catalogo de parametros
	 * @param as_llaveCatalogo Id del paramentro que se quiere obtener
	 * @return Valor de catalogo asociado al parametro con llave as_llaveCatalogo
	 * @throws B2BException Si el valor para el parametro no existe
	 */
	private String obtenerValorCatalogo(Map<String, String> amss_catalogo, String as_llaveCatalogo, boolean lb_validar)
	    throws B2BException
	{
		String ls_valorCatalogo;

		ls_valorCatalogo = (amss_catalogo != null) ? amss_catalogo.get(as_llaveCatalogo) : null;

		if(!StringUtils.isValidString(ls_valorCatalogo) && lb_validar)
		{
			Object[] loa_messageArgs;

			loa_messageArgs        = new String[1];
			loa_messageArgs[0]     = as_llaveCatalogo;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
		}

		return ls_valorCatalogo;
	}

	/**
	 * Obtener el valor de una entrada del catalogo
	 * @param amss_catalogo Catalogo de parametros
	 * @param as_llaveCatalogo Id del paramentro que se quiere obtener
	 * @return Valor de catalogo asociado al parametro con llave as_llaveCatalogo
	 * @throws B2BException Si el valor para el parametro no existe
	 */
	private String obtenerValorCatalogo(Map<String, String> amss_catalogo, String as_llaveCatalogo)
	    throws B2BException
	{
		return obtenerValorCatalogo(amss_catalogo, as_llaveCatalogo, true);
	}

	/**
	 * Resuelve la fecha, en el formato establecido por parametrización, en la base de ruta y nombre de archivo
	 *
	 * @param asb_base Plantilla de base de ruta o nombre de archivo
	 * @param as_tag Elemento que identifica la posición donde debe reemplazarse la fecha
	 * @param ad_fecha Fecha a remplazar en el formato establecido por parametrización
	 */
	private StringBuilder resolver(StringBuilder asb_base, String as_tag, Date ad_fecha)
	{
		if((asb_base != null) && (asb_base.length() > 0) && StringUtils.isValidString(as_tag) && (ad_fecha != null))
		{
			int    li_posicionInicial;
			String ls_tag;

			{
				StringBuilder lsb_tag;

				lsb_tag = new StringBuilder("<");

				lsb_tag.append(as_tag);
				lsb_tag.append("_");

				ls_tag                 = lsb_tag.toString();
				li_posicionInicial     = asb_base.indexOf(ls_tag);
			}

			while(li_posicionInicial >= 0)
			{
				int    li_posicionFinal;
				int    li_posicionRelleno;
				String ls_reemplazo;

				li_posicionFinal       = asb_base.indexOf(">", li_posicionInicial);
				li_posicionRelleno     = li_posicionInicial + ls_tag.length();
				ls_reemplazo           = new String();

				if(li_posicionFinal < li_posicionInicial)
					li_posicionFinal = asb_base.length() - 1;

				if(li_posicionRelleno < li_posicionFinal)
				{
					String ls_formato;

					ls_formato = asb_base.substring(li_posicionRelleno, li_posicionFinal);

					if(StringUtils.isValidString(ls_formato))
						ls_reemplazo = StringUtils.getString(ad_fecha, ls_formato);
				}

				asb_base.replace(li_posicionInicial, li_posicionFinal + 1, ls_reemplazo);

				li_posicionInicial = asb_base.indexOf(ls_tag);
			}
		}

		return asb_base;
	}

	/**
	 * Reemplaza todas las ocurrencias de TAG de formato en una cadena de texto
	 *
	 * @param as_base Cadena base con los TAGs sin resolver
	 * @param ad_fecha Fecha para utilizar en los reemplazos
	 * @return Cadena con los TAGs resueltos
	 */
	private String resolver(String as_base, Date ad_fecha)
	{
		String ls_respuesta;

		if(StringUtils.isValidString(as_base) && (ad_fecha != null))
		{
			StringBuilder lsb_base;

			lsb_base     = new StringBuilder(as_base);
			lsb_base     = resolver(lsb_base, "FECHA", ad_fecha);

			ls_respuesta = lsb_base.toString();
		}
		else
			ls_respuesta = new String();

		return ls_respuesta;
	}
}
