package com.bachue.snr.prosnr21.business.conciliaciones;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obteneraccesosporrol.v1.TipoAcceso;

import co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1.AccesosPorUsuario;

import com.aspose.words.CellMerge;
import com.aspose.words.CellVerticalAlignment;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.mail.SendMail;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.crps.CRPSBusiness;
import com.bachue.snr.prosnr01.business.crps.MultiCashBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.utils.FTPUtils;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.view.CRPSCabeceraDAO;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.EstadoCommon;
import com.bachue.snr.prosnr21.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.InconsistenciasCommon;
import com.bachue.snr.prosnr21.common.constants.PlantillaMensajeCommon;
import com.bachue.snr.prosnr21.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr21.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr21.dao.conciliaciones.ArchivoConciliacionDAO;
import com.bachue.snr.prosnr21.dao.png.ConArchivoDAO;
import com.bachue.snr.prosnr21.dao.png.ConInconsistenciaDAO;
import com.bachue.snr.prosnr21.dao.png.ConPartidaADAO;
import com.bachue.snr.prosnr21.dao.png.ConResumenDAO;
import com.bachue.snr.prosnr21.dao.png.CuentaAnalistaDAO;
import com.bachue.snr.prosnr21.dao.png.DRXCTotalBCODAO;
import com.bachue.snr.prosnr21.dao.png.DRXCTotalCTADAO;
import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraCuentaDAO;
import com.bachue.snr.prosnr21.dao.png.ExtractoBancoMensualDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashCabeceraDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashDetalleDAO;
import com.bachue.snr.prosnr21.dao.png.PeriodoCorteDAO;

import com.bachue.snr.prosnr21.model.conciliaciones.ArchivoConciliacion;
import com.bachue.snr.prosnr21.model.pgn.ACH;
import com.bachue.snr.prosnr21.model.pgn.ArchivoDRXC;
import com.bachue.snr.prosnr21.model.pgn.CRPSCabecera;
import com.bachue.snr.prosnr21.model.pgn.CRPSDetalle;
import com.bachue.snr.prosnr21.model.pgn.ConArchivo;
import com.bachue.snr.prosnr21.model.pgn.ConDocumentos;
import com.bachue.snr.prosnr21.model.pgn.ConImagenes;
import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;
import com.bachue.snr.prosnr21.model.pgn.ConPartidaA;
import com.bachue.snr.prosnr21.model.pgn.ConPlantillaMensaje;
import com.bachue.snr.prosnr21.model.pgn.ConResumen;
import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalBCO;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual;
import com.bachue.snr.prosnr21.model.pgn.MulticashCabecera;
import com.bachue.snr.prosnr21.model.pgn.MulticashDetalle;
import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;
import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;

import com.jcraft.jsch.ChannelSftp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigInteger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.ZoneId;

import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import java.util.regex.Pattern;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Clase para el manejo del negocio de conciliaciones.
 *
 * @author Edgar Prieto
 */
public class ConciliacionesBusiness extends EnvioGestorDocumentalConciliacionesBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConciliacionesBusiness.class);

	/**
	 * Cargar en BD los archivos que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param as_idProcesoConciliacion Id del proceso de conciliación que se ejecutará
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public synchronized void cargarArchivos(String as_idProcesoConciliacion, String as_userId, String as_remoteIp)
	    throws Exception
	{
		DAOManager          ldm_manager;
		ProcesoConciliacion lpc_proceso;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lpc_proceso     = null;

		if(StringUtils.isValidString(as_idProcesoConciliacion))
		{
			try
			{
				lpc_proceso = DaoCreator.getProcesoConciliacionDAO(ldm_manager).findById(as_idProcesoConciliacion);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarArchivos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		cargarArchivos(lpc_proceso, null, null, as_userId, as_remoteIp);
	}

	/**
	 * Cargar en BD los archivos que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param ad_fecha de ad fecha
	 * @param as_idCuenta de as id cuenta
	 * @param as_tipoArchivo de as tipo archivo
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public synchronized int cargarArchivos(
	    Date ad_fecha, String as_idCuenta, String as_tipoArchivo, String as_userId, String as_remoteIp
	)
	    throws B2BException, AddressException, MessagingException
	{
		int                      li_return;
		DAOManager               ldm_manager;
		EntidadRecaudadoraCuenta lcerc_cuenta;
		ProcesoConciliacion      lpc_proceso;

		li_return        = -1;
		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();
		lcerc_cuenta     = null;
		lpc_proceso      = null;

		if((ad_fecha != null) && StringUtils.isValidString(as_idCuenta))
		{
			try
			{
				lcerc_cuenta = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(as_idCuenta);

				if(lcerc_cuenta != null)
				{
					Constantes lc_dias;

					lc_dias         = DaoCreator.getConstantesDAO(ldm_manager)
							                        .findById(ConstanteCommon.DIAS_SALDO_INICIAL);
					lpc_proceso     = DaoCreator.getProcesoConciliacionDAO(ldm_manager)
							                        .findByIdEntidadRecaudadora(lcerc_cuenta.getIdEntidadRecaudadora());

					if(lc_dias != null)
					{
						long      ll_dias;
						long      ll_diferenciaDias;
						LocalDate lld_fechaDesde;
						LocalDate lld_fechaHasta;

						ll_dias               = NumericUtils.getLong(NumericUtils.getInt(lc_dias.getEntero()));
						lld_fechaDesde        = ad_fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						lld_fechaHasta        = LocalDate.now();
						ll_diferenciaDias     = ChronoUnit.DAYS.between(lld_fechaDesde, lld_fechaHasta);

						if(ll_diferenciaDias > ll_dias)
							throw new B2BException(ErrorKeys.ERROR_FECHA_DIAS_CONCILIACION);
					}
				}

				if(lpc_proceso != null)
					li_return = cargarArchivos(lpc_proceso, lcerc_cuenta, ad_fecha, as_userId, as_remoteIp);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarArchivos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return li_return;
	}

	/**
	 * Cargar en BD los archivos CRPS que se encuentran en el FTP.
	 *
	 * @param ad_fecha Fecha de transacción bancaria sobre la cual se extraeran los registros en el archivo CRPS
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cargarCRPS(Date ad_fecha, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		if(ad_fecha != null)
		{
			boolean lb_alreadyProcessing;
			String  ls_idConstanteBloqueo;

			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_USUARIO_NO_VALIDO));

			ls_idConstanteBloqueo = ConstanteCommon.JOB_CRPS_BLOQUEO;

			{
				DAOManager ldm_processing;

				ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

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

					clh_LOGGER.error("cargueCRPS", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}

			{
				ChannelSftp       lcs_canal;
				DAOManager        ldm_manager;
				ConInconsistencia lci_inconsistenciaInicial;

				lcs_canal                     = null;
				ldm_manager                   = DaoManagerFactory.getDAOManagerConciliacion();
				lci_inconsistenciaInicial     = new ConInconsistencia(as_userId, as_remoteIp, EstadoCommon.ACTIVO);

				{
					PeriodoCorte lpc_corte;

					lpc_corte = DaoCreator.getPeriodoCorteDAO(ldm_manager).findByDiaCorte(ad_fecha);

					if(lpc_corte != null)
						lci_inconsistenciaInicial.setIdPeriodoCorte(lpc_corte.getIdPeriodoCorte());
				}

				try
				{
					if(!lb_alreadyProcessing)
					{
						ArchivoConciliacion    la_crpsc;
						ArchivoConciliacion    la_crpsd;
						ArchivoConciliacionDAO lacd_archivo;
						Collection<String>     lcs_crpsc;
						Collection<String>     lcs_crpsd;
						ConstantesDAO          lcd_constante;
						Date                   ld_actual;
						PeriodoCorte           lpc_corte;
						ConInconsistencia      lci_param;
						String                 ls_plantillaRuta;
						String                 ls_rutaBase;
						String                 ls_rutaCRPS;
						String                 ls_rutaBaseCRPS;

						lci_param         = new ConInconsistencia(as_userId, as_remoteIp, EstadoCommon.ACTIVO);
						lcd_constante     = DaoCreator.getConstantesDAO(ldm_manager);
						lcs_canal         = abrirConexionFTP(
							    lcd_constante, lci_param, ConstanteCommon.SERVIDOR_SFTP_CLAVE,
							    ConstanteCommon.SERVIDOR_SFTP_USUARIO, ConstanteCommon.SERVIDOR_SFTP_IP,
							    ConstanteCommon.SERVIDOR_SFTP_PUERTO
							);
						ld_actual         = DateUtils.clearDateFrom(ad_fecha, Calendar.HOUR);
						lpc_corte         = DaoCreator.getPeriodoCorteDAO(ldm_manager).findByDiaCorte(ld_actual);
						ls_rutaBase       = obtenerConstanteCaracter(
							    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE
							);

						ls_plantillaRuta = obtenerConstanteCaracter(
							    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_CRPS
							);

						{
							String ls_nombreArchivo;
							String ls_plantillaNombreArchivo;

							ls_rutaBaseCRPS     = obtenerRutaCompleta(
								    ls_rutaBase, resolver(ls_plantillaRuta, null, null, ad_fecha)
								);

							ls_plantillaNombreArchivo     = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_CRPS_CABECERA
								);

							ls_nombreArchivo     = resolver(ls_plantillaNombreArchivo, null, null, ad_fecha);
							lacd_archivo         = DaoCreator.getArchivoConciliacionDAO(ldm_manager);
							lcs_crpsc            = null;
							ls_rutaCRPS          = obtenerRutaCompleta(ls_rutaBaseCRPS, ls_nombreArchivo);
							la_crpsc             = descargarArchivoFTP(lcs_canal, ls_rutaCRPS);

							if(la_crpsc != null)
							{
								la_crpsc.setIdPeriodoCorte(lpc_corte.getIdPeriodoCorte());
								la_crpsc.setIdUsuarioCreacion(as_userId);
								la_crpsc.setNombreArchivo(ls_nombreArchivo);
								la_crpsc.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_CRPS_CABECERA);

								lcs_crpsc     = la_crpsc.getLineas();
								la_crpsc      = lacd_archivo.insertar(la_crpsc);
							}
						}

						if(CollectionUtils.isValidCollection(lcs_crpsc))
						{
							Collection<CRPSCabecera>   lccrpsc_crpsc;
							CRPSCabeceraDAO            lccd_DAO;
							Map<Integer, CRPSCabecera> lmic;
							int                        li_registro;
							String                     ls_formatoFecha;
							String                     ls_separador;
							String                     ls_idArchivo;

							lccrpsc_crpsc       = new ArrayList<CRPSCabecera>();
							lccd_DAO            = DaoCreator.getCRPSCabeceraDAO(ldm_manager);
							lmic                = new HashMap<Integer, CRPSCabecera>(1);
							li_registro         = 1;
							ls_idArchivo        = la_crpsc.getId();
							ls_formatoFecha     = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.FORMATO_FECHA_CRPS_CABECERA
								);

							ls_separador = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SEPARADOR_ARCHIVO_CRPS_CABECERA
								);

							for(String ls_linea : lcs_crpsc)
							{
								CRPSCabecera lcrpsc_crpsc;

								lcrpsc_crpsc = obtenerCabeceraCRPS(
									    ls_linea, ls_idArchivo, li_registro, ls_separador, ls_formatoFecha, as_userId,
									    as_remoteIp
									);

								if(lcrpsc_crpsc != null)
								{
									int li_lcrpsc_temp;

									li_lcrpsc_temp = lccd_DAO.findByReferencia(lcrpsc_crpsc.getNumeroReferencia());

									if(li_lcrpsc_temp == 0)
										lccrpsc_crpsc.add(lcrpsc_crpsc);
									else
										lmic.put(NumericUtils.getInteger(lcrpsc_crpsc.getRegistro()), lcrpsc_crpsc);

									li_registro++;
								}
							}

							lccd_DAO.insertar(lccrpsc_crpsc);

							{
								String ls_nombreArchivo;
								String ls_plantillaNombreArchivo;

								ls_rutaBaseCRPS     = obtenerRutaCompleta(
									    ls_rutaBase, resolver(ls_plantillaRuta, null, null, ad_fecha)
									);

								ls_plantillaNombreArchivo     = obtenerConstanteCaracter(
									    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_CRPS_DETALLE
									);

								ls_nombreArchivo     = resolver(ls_plantillaNombreArchivo, null, null, ad_fecha);
								lacd_archivo         = DaoCreator.getArchivoConciliacionDAO(ldm_manager);
								lcs_crpsd            = null;
								ls_rutaCRPS          = obtenerRutaCompleta(ls_rutaBaseCRPS, ls_nombreArchivo);
								la_crpsd             = descargarArchivoFTP(lcs_canal, ls_rutaCRPS);

								if(la_crpsd != null)
								{
									la_crpsd.setIdPeriodoCorte(lpc_corte.getIdPeriodoCorte());
									la_crpsd.setIdUsuarioCreacion(as_userId);
									la_crpsd.setNombreArchivo(ls_nombreArchivo);
									la_crpsd.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_CRPS_DETALLE);

									lcs_crpsd     = la_crpsd.getLineas();
									la_crpsd      = lacd_archivo.insertar(la_crpsd);
								}
							}

							if(CollectionUtils.isValidCollection(lcs_crpsd))
							{
								Collection<CRPSDetalle> lccrpsc_crpsd;
								int                     li_registroDetalle;
								String                  ls_formatoFechaDetalle;
								String                  ls_separadorDetalle;
								String                  ls_idArchivoDetalle;

								lccrpsc_crpsd              = new ArrayList<CRPSDetalle>();
								li_registroDetalle         = 1;
								ls_idArchivoDetalle        = la_crpsd.getId();
								ls_formatoFechaDetalle     = obtenerConstanteCaracter(
									    lcd_constante, ConstanteCommon.FORMATO_FECHA_CRPS_DETALLE
									);

								ls_separadorDetalle = obtenerConstanteCaracter(
									    lcd_constante, ConstanteCommon.SEPARADOR_ARCHIVO_CRPS_DETALLE
									);

								for(String ls_linea : lcs_crpsd)
								{
									CRPSDetalle lcrpsc_crpsc;

									lcrpsc_crpsc = obtenerDetalleCRPS(
										    ls_linea, ls_idArchivoDetalle, ls_idArchivo, li_registroDetalle,
										    ls_separadorDetalle, ls_formatoFechaDetalle, as_userId, as_remoteIp
										);

									if(lcrpsc_crpsc != null)
									{
										if(!lmic.containsKey(NumericUtils.getInteger(lcrpsc_crpsc.getRegistro())))
										{
											lccrpsc_crpsd.add(lcrpsc_crpsc);
											li_registroDetalle++;
										}
									}
								}

								DaoCreator.getCRPSDetalleDAO(ldm_manager).insertar(lccrpsc_crpsd);

								generarInconsistencia(
								    lci_inconsistenciaInicial, InconsistenciasCommon.INCONSISTENCIA_92
								);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					generarInconsistencia(lci_inconsistenciaInicial, InconsistenciasCommon.INCONSISTENCIA_32);

					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("cargueCRPS", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					if(!lb_alreadyProcessing)
					{
						DAOManager ldm_processing;

						ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

						ldm_processing.setAutoCommit(true);

						try
						{
							DaoCreator.getConstantesDAO(ldm_processing)
								          .updateString(ls_idConstanteBloqueo, EstadoCommon.N, as_userId);
						}
						catch(B2BException lb2be_e)
						{
							ldm_processing.setRollbackOnly();

							clh_LOGGER.error("cargueCRPS", lb2be_e);

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
	 * Busca las opciones disponibles del menú para el usuario que inicia sesión.
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @param as_idComponente Id del componente a utilizar como filtro en la busqueda
	 * @return Colección de opciones resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos o no se encuentran opciones para el usuario
	 */
	public synchronized Collection<Opcion> cargarOpcionesMenu(String as_userId, String as_idComponente)
	    throws B2BException
	{
		Collection<Opcion> lco_opciones;
		DAOManager         ldm_manager;

		lco_opciones     = null;
		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(addErrorCMF(ErrorKeys.USUARIO_INVALIDO));

			ConstantesDAO lcd_dao;
			lcd_dao = DaoCreator.getConstantesDAO(ldm_manager);

			Constantes lc_constante;
			lc_constante = lcd_dao.findById(ConstanteCommon.OPCIONES_POR_USUARIO_ENDPOINT);

			if(lc_constante != null)
			{
				Collection<TipoAcceso> lcta_tipoAcceso;
				lcta_tipoAcceso = AccesosPorUsuario.obtenerAccesosPorUsuario(
					    as_userId, as_idComponente, lc_constante.getCaracter()
					);

				if(CollectionUtils.isValidCollection(lcta_tipoAcceso))
				{
					lco_opciones = armarOpcionesComponente(lcta_tipoAcceso);

					if(!CollectionUtils.isValidCollection(lco_opciones))
						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_USUARIO_SIN_OPCIONES));
				}
			}
			else
				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_SIN_CONSTANTE));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarOpcionesMenu", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lco_opciones;
	}

	/**
	 * Cargar en BD los archivos que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param as_idProcesoConciliacion Id del proceso de conciliación que se ejecutará
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cargueArchivos(String as_idProcesoConciliacion, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idProcesoConciliacion))
		{
			boolean lb_alreadyProcessing;
			String  ls_idConstanteBloqueo;

			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(addErrorCMF(ErrorKeys.ERROR_USUARIO_NO_VALIDO));

			{
				StringBuilder lsb_idConstanteBloqueo;

				lsb_idConstanteBloqueo = new StringBuilder(ConstanteCommon.JOB_CONCILIACIONES_BLOQUEO);

				lsb_idConstanteBloqueo.append("_");
				lsb_idConstanteBloqueo.append(as_idProcesoConciliacion);

				ls_idConstanteBloqueo = lsb_idConstanteBloqueo.toString();
			}

			{
				DAOManager ldm_processing;

				ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

				ldm_processing.setAutoCommit(true);

				try
				{
					ConstantesDAO lcd_constant;
					Constantes    lce_constant;

					lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
					lce_constant     = lcd_constant.findById(ls_idConstanteBloqueo);

					if(lce_constant != null)
					{
						lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

						if(!lb_alreadyProcessing)
							lcd_constant.updateString(ls_idConstanteBloqueo, EstadoCommon.S, as_userId);
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ls_idConstanteBloqueo;

						throw new B2BException(addErrorCMF(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("cargueArchivos", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}

			try
			{
				if(!lb_alreadyProcessing)
				{
					DAOManager ldm_manager;

					ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

					try
					{
						ProcesoConciliacion lpc_proceso;

						lpc_proceso = DaoCreator.getProcesoConciliacionDAO(ldm_manager)
								                    .findById(as_idProcesoConciliacion);

						System.err.println(
						    "ConciliacionesJob :: execute :: " + lpc_proceso.getIdEntidadRecaudadora() + " :: "
						    + new java.util.Date()
						);
					}
					catch(B2BException lb2be_e)
					{
						ldm_manager.setRollbackOnly();

						clh_LOGGER.error("cargueArchivos", lb2be_e);
					}
					finally
					{
						ldm_manager.close();
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargueArchivos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				if(!lb_alreadyProcessing)
				{
					DAOManager ldm_processing;

					ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

					ldm_processing.setAutoCommit(true);

					try
					{
						DaoCreator.getConstantesDAO(ldm_processing)
							          .updateString(ls_idConstanteBloqueo, EstadoCommon.N, as_userId);
					}
					catch(B2BException lb2be_e)
					{
						ldm_processing.setRollbackOnly();

						clh_LOGGER.error("cargueArchivos", lb2be_e);

						throw lb2be_e;
					}
					finally
					{
						ldm_processing.close();
					}
				}
			}
		}
	}

	/**
	 * Método para realizar la confrontacion de multicash con cprs.
	 *
	 * @param as_idArchivo correspondiente al valor del tipo de objeto String
	 * @param ab_proceso correspondiente al valor del tipo de objeto boolean
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_userIp correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DaoManager
	 * @return the int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized int confrontacionMulticashCPRS(
	    String as_idArchivo, boolean ab_proceso, String as_userId, String as_userIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		int               li_return;
		ProcedimientosDAO lpd_procedimientosDAO;

		li_return                 = -1;
		lpd_procedimientosDAO     = DaoCreator.getProcedimientosDAO(adm_manager);

		try
		{
			if(
			    StringUtils.isValidString(as_idArchivo) && StringUtils.isValidString(as_userId)
				    && StringUtils.isValidString(as_userIp)
			)
				li_return = lpd_procedimientosDAO.procConfrontacionMulticashCPRS(
					    as_idArchivo, ab_proceso ? ConstanteCommon.MANUAL : ConstanteCommon.AUTOMATICO, as_userId,
					    as_userIp
					);
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("confrontacionMulticashCPRS", lb2be_e);

			throw lb2be_e;
		}

		return li_return;
	}

	/**
	 * Ejecutar procedimiento reportes.
	 *
	 * @param as_fecha the as fecha
	 * @param as_procedimiento the as procedimiento
	 * @param as_userId the as user id
	 * @param as_remoteIpAddress the as remote ip address
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void ejecutarProcedimientoReportes(
	    String as_fecha, String as_procedimiento, String as_userId, String as_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(StringUtils.isValidString(as_fecha) && StringUtils.isValidString(as_procedimiento))
				DaoCreator.getUtilDAO(ldm_manager)
					          .procReportesConciliacionesDinamico(
					    as_procedimiento, as_fecha, as_userId, as_remoteIpAddress
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ejecutarProcedimientoReportes", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Fill numero fecha SIIF.
	 *
	 * @param adtc_objParametros de adtc obj parametros
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void fillNumeroFechaSIIF(DRXCTotalCTA adtc_objParametros, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		DRXCTotalCTADAO ldrxc_dao;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		ldrxc_dao       = DaoCreator.getDRXCTotalCTADAO(ldm_manager);

		try
		{
			if(adtc_objParametros == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

			ldrxc_dao.fillNumeroFechaSIIF(adtc_objParametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("fillNumeroFechaSIIF", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Retorna el valor del objeto de Collection de EntidadRecaudadoraConciliacion.
	 *
	 * @param is_IdUser correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de EntidadRecaudadoraConciliacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraConciliacion
	 */
	public synchronized Collection<EntidadRecaudadoraConciliacion> findEntidadRecaudadoraConciliacionByUserId(
	    String is_IdUser
	)
	    throws B2BException
	{
		DAOManager                                 ldm_manager;
		Collection<EntidadRecaudadoraConciliacion> lerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lerc_datos      = null;

		try
		{
			lerc_datos = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager).findByIdAnalista(is_IdUser);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraConciliacionById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lerc_datos;
	}

	/**
	 * Retorna el valor del objeto Collection de EntidadRecaudadoraCuenta.
	 *
	 * @param as_idEntidadRecaudadora correspondiente al valor del tipo de objeto String
	 * @param is_IdUser correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	public synchronized Collection<EntidadRecaudadoraCuenta> findEntidadRecaudadoraCuentaByUser(
	    String as_idEntidadRecaudadora, String is_IdUser
	)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<EntidadRecaudadoraCuenta> lerc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lerc_datos      = null;

		try
		{
			lerc_datos = findEntidadRecaudadoraCuentaByUser(as_idEntidadRecaudadora, is_IdUser, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraCuentaByEntidadAndUser", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lerc_datos;
	}

	/**
	 * Find entidad recaudadora cuenta data.
	 *
	 * @param as_idEntidadRecaudadora the as id entidad recaudadora
	 * @param is_IdUser the is id user
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized Map<String, Object> findEntidadRecaudadoraCuentaData(
	    String as_idEntidadRecaudadora, String is_IdUser
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Map<String, Object> lmso_return;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lmso_return     = new HashMap<String, Object>();

		try
		{
			if(StringUtils.isValidString(as_idEntidadRecaudadora))
			{
				Collection<EntidadRecaudadoraCuenta> lerc_datos;

				lerc_datos = findEntidadRecaudadoraCuentaByUser(as_idEntidadRecaudadora, is_IdUser, ldm_manager);

				if(CollectionUtils.isValidCollection(lerc_datos))
					lmso_return.put("CUENTAS", lerc_datos);

				EntidadRecaudadoraConciliacion lerc_entidadRecaudadora;

				lerc_entidadRecaudadora = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager)
						                                .findById(as_idEntidadRecaudadora);

				if(lerc_entidadRecaudadora != null)
					lmso_return.put("ENTIDAD", lerc_entidadRecaudadora);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntidadRecaudadoraCuentaData", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lmso_return.isEmpty())
			lmso_return = null;

		return lmso_return;
	}

	/**
	 * Find multicash detalle by cuenta and periodo.
	 *
	 * @param as_idCuenta de as id cuenta
	 * @param ad_fecha de ad fecha
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ConPartidaA> findMulticashDetalleByCuentaAndFecha(
	    String as_idCuenta, Date ad_fecha, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<ConPartidaA> lccpa_return;
		DAOManager              ldm_manager;

		lccpa_return     = null;
		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			Collection<MulticashDetalle> lcmd_datos;

			lcmd_datos = DaoCreator.getMulticashDetalleDAO(ldm_manager)
					                   .findMulticashDetalleByCuentaAndFecha(as_idCuenta, ad_fecha);

			if(lcmd_datos == null)
				throw new B2BException(addErrorCMF(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS));
			else
			{
				Iterator<MulticashDetalle> limd_iterator;

				limd_iterator = lcmd_datos.iterator();

				if(limd_iterator.hasNext())
				{
					MulticashDetalle lmd_detalle;

					lmd_detalle = limd_iterator.next();

					if(lmd_detalle != null)
					{
						String ls_idArchivo;

						ls_idArchivo = lmd_detalle.getIdArchivo();

						if(StringUtils.isValidString(ls_idArchivo))
						{
							ConPartidaADAO lcpad_DAO;

							lcpad_DAO        = DaoCreator.getConPartidaADAO(ldm_manager);
							lccpa_return     = lcpad_DAO.findByIdArchivo(ls_idArchivo);

							if(!CollectionUtils.isValidCollection(lccpa_return))
							{
								lccpa_return = new ArrayList<ConPartidaA>(1);

								for(MulticashDetalle lmd_iterador : lcmd_datos)
								{
									ConPartidaA lcpa_insert;

									lcpa_insert = new ConPartidaA();

									lcpa_insert.setIdArchivo(ls_idArchivo);
									lcpa_insert.setIdRegistro(lmd_iterador.getRegistro());
									lcpa_insert.setMonto(lmd_iterador.getMonto());
									lcpa_insert.setReferencia(lmd_iterador.getReferencia());
									lcpa_insert.setObservaciones(lmd_iterador.getObservacionesPartidasTipoA());
									lcpa_insert.setIdUsuarioCreacion(as_userId);
									lcpa_insert.setIpCreacion(as_remoteIp);
									lcpa_insert.setActivo(EstadoCommon.S);

									lcpa_insert = lcpad_DAO.insert(lcpa_insert);

									lccpa_return.add(lcpa_insert);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMulticashDetalleByCuentaAndPeriodo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccpa_return;
	}

	/**
	 * Generar reportes.
	 *
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @param accpa_consulta the accpa consulta
	 * @param ad_fechaConciliacion the ad fecha conciliacion
	 * @param as_idCuenta the as id cuenta
	 * @param as_idBanco the as id banco
	 * @return the byte[]
	 * @throws B2BException the b 2 B exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public synchronized byte[] generarReportes(
	    String as_userId, String as_remoteIp, Collection<ConPartidaA> accpa_consulta, Date ad_fechaConciliacion,
	    String as_idCuenta, String as_idBanco
	)
	    throws B2BException, IOException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;
		DAOManager ldm_bitacora;

		lba_return       = null;
		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		if(CollectionUtils.isValidCollection(accpa_consulta))
		{
			try
			{
				Iterator<ConPartidaA> licpa_iterator;

				licpa_iterator = accpa_consulta.iterator();

				if(licpa_iterator.hasNext())
				{
					ConPartidaA lcpa_data;

					lcpa_data = licpa_iterator.next();

					if(lcpa_data != null)
					{
						String ls_idArchivo;

						ls_idArchivo = lcpa_data.getIdArchivo();

						if(StringUtils.isValidString(ls_idArchivo))
						{
							Collection<String> lcs_dataReporte;

							lcs_dataReporte = DaoCreator.getConPartidaADAO(ldm_manager).findDataById(ls_idArchivo);

							if(CollectionUtils.isValidCollection(lcs_dataReporte))
							{
								BitacoraProcesoDAO       lbpd_bitacora;
								Collection<ZipEntryUtil> lczeu_zip;
								byte[]                   lba_excel;
								byte[]                   lba_csv;
								byte[]                   lba_pdf;
								String                   ls_nombreArchivo;
								String                   ls_endpoint;

								lczeu_zip            = new ArrayList<ZipEntryUtil>();
								ls_nombreArchivo     = "REPORTE PARTIDAS TIPO A";
								lba_excel            = getXSLFromStringCollection(lcs_dataReporte);
								lba_csv              = getTXTFromStringCollection(lcs_dataReporte);
								lba_pdf              = null;
								lbpd_bitacora        = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
								ls_endpoint          = DaoCreator.getConstantesDAO(ldm_manager)
										                             .findString(
										    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
										);

								ldm_bitacora.setAutoCommit(true);

								try
								{
									lba_pdf = generarReportePartidasAPdf(
										    lcs_dataReporte, ad_fechaConciliacion, as_idCuenta, as_idBanco, ldm_manager,
										    ls_nombreArchivo, as_userId, as_remoteIp
										);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("generarReportes", le_e);
								}

								if(lba_excel != null)
								{
									ZipEntryUtil lzeu_excel;

									lzeu_excel = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.XLSX_PUNTO,
										    new ByteArrayInputStream(lba_excel)
										);

									lczeu_zip.add(lzeu_excel);

									guardarDocumento(
									    ls_nombreArchivo, ExtensionCommon.XLS, lba_excel, as_userId, as_remoteIp,
									    ldm_manager, lbpd_bitacora, ls_endpoint, as_idCuenta
									);
								}

								if(lba_csv != null)
								{
									ZipEntryUtil lzeu_csv;

									lzeu_csv = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.CSV_PUNTO,
										    new ByteArrayInputStream(lba_csv)
										);

									lczeu_zip.add(lzeu_csv);

									guardarDocumento(
									    ls_nombreArchivo, ExtensionCommon.CSV, lba_excel, as_userId, as_remoteIp,
									    ldm_manager, lbpd_bitacora, ls_endpoint, as_idCuenta
									);
								}

								if(lba_pdf != null)
								{
									ZipEntryUtil lzeu_pdf;

									lzeu_pdf = new ZipEntryUtil(
										    ls_nombreArchivo + ExtensionCommon.PDF_PUNTO,
										    new ByteArrayInputStream(lba_pdf)
										);

									lczeu_zip.add(lzeu_pdf);

									guardarDocumento(
									    ls_nombreArchivo, ExtensionCommon.PDF, lba_excel, as_userId, as_remoteIp,
									    ldm_manager, lbpd_bitacora, ls_endpoint, as_idCuenta
									);
								}

								if(CollectionUtils.isValidCollection(lczeu_zip))
									lba_return = ZipUtils.generateZip(lczeu_zip);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				ldm_bitacora.setRollbackOnly();

				clh_LOGGER.error("generarReportes", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
				ldm_bitacora.close();
			}
		}

		return lba_return;
	}

	/**
	 * Procesos conciliaciones.
	 *
	 * @param as_procesoConciliacion the as proceso conciliacion
	 * @param ad_fechaDesde the ad fecha desde
	 * @param ad_fechaHasta the ad fecha hasta
	 * @param as_idCuenta the as id cuenta
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void procesosConciliaciones(
	    String as_procesoConciliacion, Date ad_fechaDesde, Date ad_fechaHasta, String as_idCuenta, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_procesoConciliacion))
			{
				ad_fechaHasta = DateUtils.getDate(ad_fechaHasta, Calendar.DATE, 1, false);

				if(as_procesoConciliacion.equalsIgnoreCase("C"))
				{
					while(ad_fechaDesde.before(ad_fechaHasta))
					{
						cargarCRPS(ad_fechaDesde, as_userId, as_remoteIp);

						ad_fechaDesde = DateUtils.getDate(ad_fechaDesde, Calendar.DATE, 1, false);
					}
				}
				else if(as_procesoConciliacion.equalsIgnoreCase("E"))
				{
					while(ad_fechaDesde.before(ad_fechaHasta))
					{
						new CRPSBusiness().generarCRPS(ad_fechaDesde, as_userId, as_remoteIp);

						ad_fechaDesde = DateUtils.getDate(ad_fechaDesde, Calendar.DATE, 1, false);
					}
				}
				else if(as_procesoConciliacion.equalsIgnoreCase("M"))
				{
					while(ad_fechaDesde.before(ad_fechaHasta))
					{
						new MultiCashBusiness().generarMultiCash(ad_fechaDesde, as_idCuenta, as_userId, as_remoteIp);

						ad_fechaDesde = DateUtils.getDate(ad_fechaDesde, Calendar.DATE, 1, false);
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			clh_LOGGER.error("realizarBusquedaFechaConciliarDRXCBanco", lb2b_e);

			throw lb2b_e;
		}
	}

	/**
	 * Realizar busqueda fecha conciliar DRXC.
	 *
	 * @param aerc_param the aerc param
	 * @param as_userId the as user id
	 * @param as_remoteIpAddress the as remote ip address
	 * @return the map
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized Map<String, Object> realizarBusquedaFechaConciliarDRXC(
	    EntidadRecaudadoraCuenta aerc_param, String as_userId, String as_remoteIpAddress
	)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Map<String, Object> lmso_return;

		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();
		lmso_return     = new HashMap<String, Object>(1);

		try
		{
			if((aerc_param != null) && (aerc_param.getFechaConfrontar() != null))
			{
				EntidadRecaudadoraCuentaDAO lerc_dao;
				PeriodoCorteDAO             lpc_dao;
				Collection<DRXCTotalCTA>    lcdtc_cuenta;
				Collection<DRXCTotalBCO>    lcdtb_banco;
				PeriodoCorte                lpc_periodoCorteObj;
				String                      ls_idPeriodoCorte;
				Date                        ld_date;

				lerc_dao                = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager);
				lpc_dao                 = DaoCreator.getPeriodoCorteDAO(ldm_manager);
				lcdtc_cuenta            = new ArrayList<DRXCTotalCTA>();
				lcdtb_banco             = new ArrayList<DRXCTotalBCO>();
				ld_date                 = aerc_param.getFechaConfrontar();
				lpc_periodoCorteObj     = lpc_dao.findByFecha(ld_date);
				ls_idPeriodoCorte       = null;

				if(lpc_periodoCorteObj == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				else
				{
					ls_idPeriodoCorte = lpc_periodoCorteObj.getIdPeriodoCorte();

					if(!StringUtils.isValidString(ls_idPeriodoCorte))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}

				{
					DRXCTotalBCODAO ldrxb_dao;

					ldrxb_dao = DaoCreator.getDRXCTotalBCODAO(ldm_manager);

					ldrxb_dao.procCrearDRXCTotalBCO(ls_idPeriodoCorte, as_userId, as_remoteIpAddress);

					lcdtb_banco = ldrxb_dao.findAllByPeriodo(ls_idPeriodoCorte);

					if(!CollectionUtils.isValidCollection(lcdtb_banco))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}

				lmso_return.put(IdentificadoresCommon.DRXC_BANCO, lcdtb_banco);

				{
					DRXCTotalCTADAO  ldrxc_dao;
					SimpleDateFormat lsdf_formatFecha;
					String           ls_dateStr;
					String           ls_idCuenta;

					ldrxc_dao            = DaoCreator.getDRXCTotalCTADAO(ldm_manager);
					lsdf_formatFecha     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);    //"dd/MM/yyyy"
					ls_dateStr           = lsdf_formatFecha.format(ld_date);
					ls_idCuenta          = aerc_param.getIdCuenta();

					ldrxc_dao.procCrearDRXCTotalCTA(
					    aerc_param.getIdEntidadRecaudadora(), ls_idPeriodoCorte, ls_idCuenta, as_userId,
					    as_remoteIpAddress
					);

					lcdtc_cuenta = ldrxc_dao.findAllByPeriodo(ls_idPeriodoCorte);

					if(!CollectionUtils.isValidCollection(lcdtc_cuenta))
					{
						EntidadRecaudadoraCuenta lerc_objTemp;

						lerc_objTemp = lerc_dao.findById(ls_idCuenta);

						if(lerc_objTemp != null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[3];
							loa_messageArgs[0]     = ls_dateStr;
							loa_messageArgs[1]     = lerc_objTemp.getNombreEntidadRecaudadora();
							loa_messageArgs[2]     = lerc_objTemp.getNumeroCuenta();
							throw new B2BException(
							    addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_BUSQUEDA, loa_messageArgs)
							);
						}
						else
							throw new B2BException(addErrorCMF(ErrorKeys.ERROR_INTERNO_SISTEMA));
					}

					for(DRXCTotalCTA ldtc_objCta : lcdtc_cuenta)
					{
						if(
						    StringUtils.isValidString(ldtc_objCta.getNumeroSIIF())
							    && (ldtc_objCta.getFechaSIIF() == null)
						)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_dateStr;

							throw new B2BException(
							    addErrorCMF(ErrorKeys.NO_EXISTE_INFORMACION_PARA_ESA_BUSQUEDA, loa_messageArgs)
							);
						}
					}
				}

				lmso_return.put(IdentificadoresCommon.DRXC_CUENTA, lcdtc_cuenta);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("realizarBusquedaFechaConciliarDRXCCuenta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lmso_return.isEmpty())
			lmso_return = null;

		return lmso_return;
	}

	/**
	 * Realizar nueva solicitud.
	 *
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @param ad_fechaConciliacion the ad fecha conciliacion
	 * @param as_idCuenta the as id cuenta
	 * @param as_correoElectronico the as correo electronico
	 * @param as_idTipoArchivo the as id tipo archivo
	 * @throws B2BException the b 2 B exception
	 * @throws AddressException cuando se produce algun error en el proceso
	 * @throws MessagingException cuando se produce algun error en el proceso
	 */
	public synchronized void realizarNuevaSolicitud(
	    String as_userId, String as_remoteIp, Date ad_fechaConciliacion, String as_idCuenta, String as_correoElectronico,
	    String as_idTipoArchivo
	)
	    throws B2BException, AddressException, MessagingException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

		try
		{
			if(ad_fechaConciliacion != null)
			{
				Constantes lc_dias;

				lc_dias = DaoCreator.getConstantesDAO(ldm_manager).findById(ConstanteCommon.DIAS_SALDO_INICIAL);

				if(lc_dias != null)
				{
					long      ll_dias;
					long      ll_diferenciaDias;
					LocalDate lld_fechaConciliacion;
					LocalDate lld_fechaActual;

					ll_dias                   = NumericUtils.getLong(NumericUtils.getInt(lc_dias.getEntero()));
					lld_fechaConciliacion     = ad_fechaConciliacion.toInstant().atZone(ZoneId.systemDefault())
							                                            .toLocalDate();
					lld_fechaActual           = LocalDate.now();
					ll_diferenciaDias         = ChronoUnit.DAYS.between(lld_fechaConciliacion, lld_fechaActual);

					if(ll_diferenciaDias > ll_dias)
						throw new B2BException(ErrorKeys.ERROR_FECHA_DIAS_NUEVA_SOLICITUD_CONFRONTACION);
				}
			}

			String ls_jndiJavaMail;

			ls_jndiJavaMail = DaoCreator.getConstantesDAO(ldm_manager)
					                        .findString(ConstanteCommon.JOB_MOTOR_ENVIO_ELECTRONICO_JNDI);

			if(StringUtils.isValidString(ls_jndiJavaMail))
			{
				ConPlantillaMensaje lcpm_plantilla;

				lcpm_plantilla = DaoCreator.getConPlantillaMensajeDAO(ldm_manager)
						                       .findById(PlantillaMensajeCommon.PLANTILLA_1);

				if(lcpm_plantilla != null)
				{
					SendMail lse_emailPorEnviar;
					String   ls_cuerpo;

					lse_emailPorEnviar     = new SendMail(ls_jndiJavaMail, false, false, false);
					ls_cuerpo              = lcpm_plantilla.getCuerpo();

					lse_emailPorEnviar.setSubject(lcpm_plantilla.getAsunto());

					if(StringUtils.isValidString(ls_cuerpo))
					{
						EntidadRecaudadoraCuentaDAO lercd_DAO;
						String                      ls_tag;

						lercd_DAO     = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager);
						ls_tag        = "<TAG_BANCO>";

						if(ls_cuerpo.contains(ls_tag))
						{
							EntidadRecaudadoraCuenta lerc_cuenta;

							lerc_cuenta = lercd_DAO.findById(as_idCuenta);

							if(lerc_cuenta != null)
							{
								String ls_idBanco;

								ls_idBanco = lerc_cuenta.getIdEntidadRecaudadora();

								if(StringUtils.isValidString(ls_idBanco))
								{
									EntidadRecaudadoraConciliacion lerc_banco;

									lerc_banco = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager)
											                   .findById(ls_idBanco);

									if(lerc_banco != null)
										ls_cuerpo = ls_cuerpo.replace(ls_tag, lerc_banco.getNombreEntidadRecaudadora());
								}
							}
						}

						ls_tag = "<TAG_FECHA_CONCILIACION>";

						if(ls_cuerpo.contains(ls_tag))
							ls_cuerpo = ls_cuerpo.replace(
								    ls_tag, new SimpleDateFormat("dd/MM/yyyy").format(ad_fechaConciliacion)
								);

						ls_tag = "<TAG_TIPO_ARCHIVO>";

						if(ls_cuerpo.contains(ls_tag))
							ls_cuerpo = ls_cuerpo.replace(ls_tag, as_idTipoArchivo);

						ls_tag = "<TAG_NUMERO_CUENTA>";

						if(ls_cuerpo.contains(ls_tag))
						{
							EntidadRecaudadoraCuenta lerc_cuenta;

							lerc_cuenta = lercd_DAO.findById(as_idCuenta);

							if(lerc_cuenta != null)
								ls_cuerpo = ls_cuerpo.replace(ls_tag, lerc_cuenta.getNumeroCuenta());
						}
					}

					lse_emailPorEnviar.setBody(ls_cuerpo);
					lse_emailPorEnviar.setTo(as_correoElectronico);
					lse_emailPorEnviar.sendMailEvent();

					{
						ConInconsistencia lci_param;

						lci_param = new ConInconsistencia(as_userId, as_remoteIp, EstadoCommon.ACTIVO);

						{
							PeriodoCorte lpc_periodoCorte;

							lpc_periodoCorte = DaoCreator.getPeriodoCorteDAO(ldm_manager)
									                         .findByFecha(ad_fechaConciliacion);

							if(lpc_periodoCorte != null)
								lci_param.setIdPeriodoCorte(lpc_periodoCorte.getIdPeriodoCorte());
						}

						lci_param.setIdCuenta(as_idCuenta);

						generarInconsistencia(lci_param, InconsistenciasCommon.INCONSISTENCIA_94);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("realizarNuevaSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar partidas A.
	 *
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @param accpa_consulta the accpa consulta
	 * @param acad_dataComprobante the acad data comprobante
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void salvarPartidasA(
	    String as_userId, String as_remoteIp, Collection<ConPartidaA> accpa_consulta,
	    Collection<ArchivoDRXC> acad_dataComprobante
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_bitacora;

		ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acad_dataComprobante))
			{
				Iterator<ArchivoDRXC> liad_iterator;

				liad_iterator = acad_dataComprobante.iterator();

				if(liad_iterator.hasNext())
				{
					ArchivoDRXC lad_data;

					lad_data = liad_iterator.next();

					if(lad_data != null)
					{
						ConPartidaADAO          lcpd_DAO;
						Collection<ConPartidaA> lcpa_collectionPartidaA;
						String                  ls_comprobanteSiif;
						Date                    ld_fechaSiif;
						String                  ls_confirmaSiif;
						BitacoraProcesoDAO      lbpd_bitacora;
						String                  ls_endpoint;

						lcpd_DAO                    = DaoCreator.getConPartidaADAO(ldm_manager);
						ls_comprobanteSiif          = lad_data.getNumeroSIIF();
						ld_fechaSiif                = lad_data.getFechaSIIF();
						ls_confirmaSiif             = lad_data.isConfirmaNoSiif() ? EstadoCommon.S : EstadoCommon.N;
						lcpa_collectionPartidaA     = new ArrayList<ConPartidaA>(0);
						lbpd_bitacora               = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						ls_endpoint                 = DaoCreator.getConstantesDAO(ldm_manager)
								                                    .findString(
								    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
								);

						ldm_bitacora.setAutoCommit(true);

						for(ConPartidaA lcpa_iterador : accpa_consulta)
						{
							if(lcpa_iterador != null)
							{
								lcpa_iterador.setNumeroComprobanteSiif(ls_comprobanteSiif);
								lcpa_iterador.setFechaComprobanteSiif(ld_fechaSiif);
								lcpa_iterador.setEnvioSiif(ls_confirmaSiif);
								lcpa_iterador.setIdUsuarioModificacion(as_userId);
								lcpa_iterador.setIpModificacion(as_remoteIp);

								lcpa_collectionPartidaA = lcpd_DAO.findById(lcpa_iterador);

								if(!lcpa_collectionPartidaA.isEmpty())
								{
									String ls_docSoporte;

									ls_docSoporte = lcpa_collectionPartidaA.iterator().next().getDocumentoSoporte();

									if(
									    ((ls_docSoporte == null) || ls_docSoporte.equalsIgnoreCase(EstadoCommon.N))
										    && (lcpa_iterador.getBytesArchivo() != null)
									)
										salvarPartidasAArchivos(
										    lcpa_iterador, as_userId, as_remoteIp, ldm_manager, lbpd_bitacora,
										    ls_endpoint
										);
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

								lcpd_DAO.update(lcpa_iterador);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_bitacora.setRollbackOnly();

			clh_LOGGER.error("salvarPartidasA", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_bitacora.close();
		}
	}

	/**
	 * Salvar partidas A archivos.
	 *
	 * @param acpa_partidaA de acpa partida A
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param ldm_manager de ldm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarPartidasAArchivos(
	    ConPartidaA acpa_partidaA, String as_userId, String as_remoteIp, DAOManager ldm_manager,
	    BitacoraProcesoDAO abpd_bitacora, String as_endpoint
	)
	    throws B2BException
	{
		try
		{
			if(acpa_partidaA == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);

			long          ll_idImagen;
			long          ll_idDocumento;
			ConDocumentos lcd_conDocumentos;
			ConImagenes   lci_conImagenes;
			String        ls_tipoArchivoSave;

			lcd_conDocumentos      = new ConDocumentos();
			lci_conImagenes        = new ConImagenes();
			ls_tipoArchivoSave     = acpa_partidaA.getTipoArchivo();

			lci_conImagenes.setEstado(EstadoCommon.ACTIVO);
			lci_conImagenes.setTamano(Long.valueOf(acpa_partidaA.getBytesArchivo().length));

			{
				String ls_tipoArchivo;

				ls_tipoArchivo = acpa_partidaA.getTipoArchivo();

				if(StringUtils.isValidString(ls_tipoArchivo))
				{
					if(ls_tipoArchivo.equalsIgnoreCase(TipoContenidoCommon.PDF))
						ls_tipoArchivoSave = ExtensionCommon.PDF_MAYUS;
				}
			}

			lci_conImagenes.setTipoArchivo(ls_tipoArchivoSave);
			lci_conImagenes.setIdUsuarioCreacion(as_userId);
			lci_conImagenes.setIpCreacion(as_remoteIp);
			lci_conImagenes.setImagenBlob(acpa_partidaA.getBytesArchivo());

			ll_idImagen = DaoCreator.getConImagenesDAO(ldm_manager).insertOrUpdate(lci_conImagenes, true);

			if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
				throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

			lcd_conDocumentos.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
			lcd_conDocumentos.setIdPartidaA(acpa_partidaA.getIdPartidaA());
			lcd_conDocumentos.setIdArchivo(acpa_partidaA.getIdArchivo());
			lcd_conDocumentos.setTipoArchivo(ls_tipoArchivoSave);
			lcd_conDocumentos.setEstado(EstadoCommon.ACTIVO);
			lcd_conDocumentos.setGenerado(EstadoCommon.S);
			lcd_conDocumentos.setIdUsuarioCreacion(as_userId);
			lcd_conDocumentos.setIpCreacion(as_remoteIp);
			lcd_conDocumentos.setTipo("DOCUMENTO_SOPORTE");
			lcd_conDocumentos.setRepositorio(RepositorioCommon.FINAL);

			ll_idDocumento = DaoCreator.getConDocumentosDAO(ldm_manager).insertOrUpdate(lcd_conDocumentos, true);

			lcd_conDocumentos.setIdDocumentoSalida(ll_idDocumento);

			enviarGestorDocumental(lcd_conDocumentos, abpd_bitacora, as_endpoint, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("asd", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Upload file extracto banco mensual.
	 *
	 * @param aebm_extractoBancoMensual de aebm extracto banco mensual
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public synchronized int uploadFileExtractoBancoMensual(
	    ExtractoBancoMensual aebm_extractoBancoMensual, String as_userId, String as_remoteIpAddress
	)
	    throws B2BException, IOException, ParseException
	{
		int        li_return;
		DAOManager ldm_manager;

		li_return       = -1;
		ldm_manager     = DaoManagerFactory.getDAOManagerConciliacion();

		if(aebm_extractoBancoMensual != null)
		{
			try
			{
				ArchivoConciliacion lac_extracto;
				String              ls_idEntidad;
				String              ls_idCuenta;
				String              ls_periodo;
				String              ls_nombreArchivo;

				lac_extracto         = null;
				ls_idEntidad         = aebm_extractoBancoMensual.getIdEntidadRecaudadora();
				ls_idCuenta          = aebm_extractoBancoMensual.getIdCuenta();
				ls_periodo           = aebm_extractoBancoMensual.getPeriodo();
				ls_nombreArchivo     = null;

				if(
				    StringUtils.isValidString(ls_idEntidad) && StringUtils.isValidString(ls_idCuenta)
					    && StringUtils.isValidString(ls_periodo)
				)
				{
					ConstantesDAO lcd_DAO;
					ChannelSftp   lcs_canal;
					String        ls_rutaBase;

					EntidadRecaudadoraConciliacion lerc_entidad;
					EntidadRecaudadoraCuenta       lerc_cuenta;
					Date                           ld_fecha;

					lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);
					lcs_canal        = abrirConexionFTP(
						    lcd_DAO, null, ConstanteCommon.SERVIDOR_SFTP_CLAVE, ConstanteCommon.SERVIDOR_SFTP_USUARIO,
						    ConstanteCommon.SERVIDOR_SFTP_IP, ConstanteCommon.SERVIDOR_SFTP_PUERTO
						);
					lerc_entidad     = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager)
							                         .findById(ls_idEntidad);
					lerc_cuenta      = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(ls_idCuenta);
					ld_fecha         = new SimpleDateFormat("yyyyMM").parse(ls_periodo);
					ls_rutaBase      = obtenerRutaCompleta(
						    obtenerConstanteCaracter(lcd_DAO, ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE),
						    resolver(
						        obtenerConstanteCaracter(
						            lcd_DAO, ConstanteCommon.SERVIDOR_SFTP_RUTA_EXB_BANCARIO_MENSUAL
						        ), lerc_entidad, lerc_cuenta, ld_fecha
						    )
						);

					try
					{
						lcs_canal.cd(ls_rutaBase);
					}
					catch(Exception le_e)
					{
						throw new B2BException("No se encontró la carpeta para el archivo EXB.");
					}

					ls_nombreArchivo = resolver(
						    obtenerConstanteCaracter(lcd_DAO, ConstanteCommon.NOMBRE_DEL_ARCHIVO_EXB_BANCARIO_MENSUAL),
						    lerc_entidad, lerc_cuenta, ld_fecha,
						    obtenerConstanteCaracter(lcd_DAO, ConstanteCommon.NOMBRE_TIPO_EXTRACTO_BANCARIO_MENSUAL)
						);

					try
					{
						lac_extracto = descargarArchivoFTP(
							    lcs_canal, obtenerRutaCompleta(ls_rutaBase, ls_nombreArchivo)
							);

						if(lac_extracto == null)
							throw new B2BException("No se encontró el archivo EXB en la carpeta.");
					}
					catch(Exception le_e)
					{
						throw new B2BException("No se encontró el archivo EXB en la carpeta.");
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);

				if(lac_extracto != null)
				{
					Collection<String> lcs_lineas;
					String             ls_idArchivo;

					lac_extracto.setIdCuenta(ls_idCuenta);
					lac_extracto.setIdUsuarioCreacion(as_userId);
					lac_extracto.setNombreArchivo(ls_nombreArchivo);
					lac_extracto.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_EXB);

					lcs_lineas       = lac_extracto.getLineas();
					lac_extracto     = DaoCreator.getArchivoConciliacionDAO(ldm_manager).insertar(lac_extracto);
					ls_idArchivo     = lac_extracto.getId();

					if(CollectionUtils.isValidCollection(lcs_lineas))
					{
						boolean                          lb_validacion;
						int                              li_idRegistro;
						Collection<ExtractoBancoMensual> lcebm_extractos;

						lb_validacion       = true;
						li_idRegistro       = 0;
						lcebm_extractos     = new ArrayList<ExtractoBancoMensual>(1);

						for(String ls_string : lcs_lineas)
						{
							if(li_idRegistro != 0)
							{
								if(StringUtils.isValidString(ls_string) && (ls_string.length() == 135))
								{
									ExtractoBancoMensual lebm_extractobancoMensual;
									lebm_extractobancoMensual = new ExtractoBancoMensual();

									lebm_extractobancoMensual.setIdArchivo(ls_idArchivo);
									lebm_extractobancoMensual.setIdRegistro(NumericUtils.getInteger(li_idRegistro));
									lebm_extractobancoMensual.setIdEntidadRecaudadora(ls_idEntidad);
									lebm_extractobancoMensual.setIdCuenta(ls_idCuenta);
									lebm_extractobancoMensual.setPeriodo(ls_periodo);
									lebm_extractobancoMensual.setNombreArchivo(ls_nombreArchivo);
									lebm_extractobancoMensual.setActivo(EstadoCommon.S);
									lebm_extractobancoMensual.setIdUsuarioCreacion(as_userId);
									lebm_extractobancoMensual.setIpCreacion(as_remoteIpAddress);

									{
										String ls_campo;
										ls_campo = ls_string.substring(0, 8);

										if(StringUtils.isValidString(ls_campo))
										{
											Date    ld_fechaCampo;
											Integer li_valorCampo;

											li_valorCampo     = obtenerValorEntero(ls_campo);
											ld_fechaCampo     = new SimpleDateFormat("DDMMyyyy").parse(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if((ld_fechaCampo != null) && (li_valorCampo != null))
												lebm_extractobancoMensual.setFecha(ls_campo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(8, 19);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setCuenta(ls_campo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(19, 25);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setConsecutivo(li_valorCampo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(25, 26);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setTipoCuenta(ls_campo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(26, 29);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setOficina(ls_campo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(29, 33);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setCodigoTransaccion(li_valorCampo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(33, 53);

										if(StringUtils.isValidString(ls_campo))
											lebm_extractobancoMensual.setNombreCortoTransaccion(ls_campo);
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(53, 93);

										if(StringUtils.isValidString(ls_campo))
											lebm_extractobancoMensual.setNombreExtractoTransaccion(ls_campo);
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(93, 99);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setDocumento(li_valorCampo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(99, 100);

										if(StringUtils.isValidString(ls_campo))
											lebm_extractobancoMensual.setSignoValor(ls_campo);
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(100, 115);

										if(StringUtils.isValidString(ls_campo))
										{
											Double ld_valorDouble;
											ld_valorDouble = obtenerValorDoble(ls_campo);

											if(ld_valorDouble == null)
												ld_valorDouble = obtenerValorDoble(obtenerStringDecimal(ls_campo));

											if(ld_valorDouble != null)
												lebm_extractobancoMensual.setValor(ld_valorDouble);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(115, 118);

										if(StringUtils.isValidString(ls_campo))
										{
											Integer li_valorCampo;

											li_valorCampo = obtenerValorEntero(ls_campo);

											if(li_valorCampo == null)
												li_valorCampo = obtenerValorEntero(ls_campo.trim());

											if(li_valorCampo != null)
												lebm_extractobancoMensual.setOficinaOrigen(li_valorCampo);
											else
												lb_validacion = false;
										}
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(118, 119);

										if(StringUtils.isValidString(ls_campo))
											lebm_extractobancoMensual.setTipoTransaccion(ls_campo);
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(119, 120);

										if(StringUtils.isValidString(ls_campo))
											lebm_extractobancoMensual.setSignoSaldo(ls_campo);
									}

									{
										String ls_campo;
										ls_campo = ls_string.substring(120, 135);

										if(StringUtils.isValidString(ls_campo))
										{
											Double ld_valorDouble;
											ld_valorDouble = obtenerValorDoble(ls_campo);

											if(ld_valorDouble == null)
												ld_valorDouble = obtenerValorDoble(obtenerStringDecimal(ls_campo));

											if(ld_valorDouble != null)
												lebm_extractobancoMensual.setSaldoDia(ld_valorDouble);
											else
												lb_validacion = false;
										}
									}

									lcebm_extractos.add(lebm_extractobancoMensual);
								}
								else
									throw new B2BException(
									    "El registro del EXB no cumple con la estructura establecida."
									);
							}

							li_idRegistro++;
						}

						if(lb_validacion)
						{
							ExtractoBancoMensualDAO lebmd_DAO;
							lebmd_DAO = DaoCreator.getExtractoBancoMensualDAO(ldm_manager);

							for(ExtractoBancoMensual lebm_objTemp : lcebm_extractos)
								lebmd_DAO.insert(lebm_objTemp);

							li_return = 0;
						}
						else
						{
							EntidadRecaudadoraCuenta lerc_objTemp;
							lerc_objTemp = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager).findById(ls_idCuenta);

							if(lerc_objTemp != null)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[3];
								loa_messageArgs[0]     = lerc_objTemp.getNombreEntidadRecaudadora();
								loa_messageArgs[1]     = ls_periodo;
								loa_messageArgs[2]     = lerc_objTemp.getNumeroCuenta();

								throw new B2BException(
								    addErrorCMF(ErrorKeys.ESTRUCTURA_DEL_ARCHIVO_ERRADA, loa_messageArgs)
								);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("uploadFileExtractoBancoMensual", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return li_return;
	}

	/**
	 * Método para armar el menú de conciliaciones y Workflow.
	 *
	 * @param ac_tipoAcceso de ac tipo acceso
	 * @return el valor de collection
	 */
	private Collection<Opcion> armarOpcionesComponente(Collection<TipoAcceso> ac_tipoAcceso)
	{
		Collection<Opcion> lco_coReturn;

		lco_coReturn = new LinkedList<Opcion>();

		if(CollectionUtils.isValidCollection(ac_tipoAcceso))
		{
			for(TipoAcceso ita_tipoAcceso : ac_tipoAcceso)
			{
				if(ita_tipoAcceso != null)
				{
					Opcion lo_opcion;
					lo_opcion = new Opcion();

					String ls_iterador;
					ls_iterador = null;

					{
						ls_iterador = ita_tipoAcceso.getIdOpcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setIdOpcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getOpcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setOpcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getDescripcion();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setDescripcion(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getOpcionPadre();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setIdOpcionPadre(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getTipo();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setTipo(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getUrl();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setUrl(ls_iterador);
					}

					{
						ls_iterador = ita_tipoAcceso.getActivo();

						if(StringUtils.isValidString(ls_iterador))
							lo_opcion.setActivo(ls_iterador);

						if(ls_iterador.equals("S"))
							lco_coReturn.add(lo_opcion);
					}
				}
			}
		}

		return lco_coReturn;
	}

	/**
	 * Cargar en BD los archivos ACH que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param aci_param de aci param
	 * @param acs_canal de acs canal
	 * @param apc_corte de apc corte
	 * @param aerc_cuenta de aerc cuenta
	 * @param aerc_entidad de aerc entidad
	 * @param ad_fecha de ad fecha
	 * @param as_rutaBase de as ruta base
	 * @param as_plantillaNombreArchivo de as plantilla nombre archivo
	 * @param as_plantillaRuta de as plantilla ruta
	 * @param as_formatoFecha de as formato fecha
	 * @param as_separador de as separador
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se ejecuta la accion
	 * @param apb_business
	 * @param aca_analista
	 * @return el valor de archivo conciliacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private synchronized ArchivoConciliacion cargarArchivoACH(
	    ConInconsistencia aci_param, ChannelSftp acs_canal, PeriodoCorte apc_corte, EntidadRecaudadoraCuenta aerc_cuenta,
	    EntidadRecaudadoraConciliacion aerc_entidad, Date ad_fecha, String as_rutaBase, String as_plantillaNombreArchivo,
	    String as_plantillaRuta, String as_formatoFecha, String as_separador, String as_userId, String as_remoteIp,
	    CuentaAnalista aca_analista, ParameterBusiness apb_business
	)
	    throws B2BException, AddressException, MessagingException
	{
		ArchivoConciliacion lac_ach;

		lac_ach = null;

		if(aerc_cuenta != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

			try
			{
				Collection<String> lcs_ach;

				lcs_ach = null;

				{
					String ls_nombreArchivo;

					ls_nombreArchivo = resolver(as_plantillaNombreArchivo, aerc_entidad, aerc_cuenta, ad_fecha);

					{
						String ls_ruta;
						String ls_rutaBase;

						ls_rutaBase     = obtenerRutaCompleta(
							    as_rutaBase, resolver(as_plantillaRuta, aerc_entidad, aerc_cuenta, ad_fecha)
							);
						ls_ruta         = obtenerRutaCompleta(ls_rutaBase, ls_nombreArchivo);

						{
							String ls_codInconsistencia;

							ls_codInconsistencia = InconsistenciasCommon.INCONSISTENCIA_8;

							try
							{
								lac_ach = descargarArchivoFTP(acs_canal, ls_ruta);
							}
							catch(Exception le_e)
							{
								generarInconsistencia(aci_param, ls_codInconsistencia, apb_business, aca_analista);
							}
						}
					}

					if(lac_ach != null)
					{
						lac_ach.setIdCuenta(aerc_cuenta.getIdCuenta());
						lac_ach.setIdPeriodoCorte(apc_corte.getIdPeriodoCorte());
						lac_ach.setIdUsuarioCreacion(as_userId);
						lac_ach.setNombreArchivo(ls_nombreArchivo);
						lac_ach.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_ACH);

						lcs_ach     = lac_ach.getLineas();
						lac_ach     = DaoCreator.getArchivoConciliacionDAO(ldm_manager).insertar(lac_ach);
					}
				}

				if(CollectionUtils.isValidCollection(lcs_ach))
				{
					Collection<ACH>      lcach_ach;
					int                  li_registro;
					Map<Integer, String> lmis_ordenCampos;
					String               ls_claveBanco;
					String               ls_cuenta;
					String               ls_idCuenta;
					String               ls_idPeriodoCorte;
					String[]             lsa_campos;
					String               ls_idArchivo;

					lcach_ach             = new ArrayList<ACH>();
					li_registro           = 1;
					lmis_ordenCampos      = new HashMap<Integer, String>();
					ls_claveBanco         = aerc_entidad.getCodigoEntidadRecaudadora();
					ls_cuenta             = aerc_cuenta.getNumeroCuenta();
					ls_idArchivo          = lac_ach.getId();
					ls_idCuenta           = aerc_cuenta.getIdCuenta();
					ls_idPeriodoCorte     = apc_corte.getIdPeriodoCorte();

					{
						String   ls_ordenCampos;
						String[] lsa_ordenCampos;

						ls_ordenCampos = lcs_ach.iterator().next();

						lcs_ach.remove(ls_ordenCampos);

						lsa_ordenCampos = ls_ordenCampos.split(as_separador);

						for(int li_campo = 0, li_campos = lsa_ordenCampos.length; li_campo < li_campos; li_campo++)
						{
							String ls_campo;

							ls_campo = lsa_ordenCampos[li_campo];

							if(StringUtils.isValidString(ls_campo))
								lmis_ordenCampos.put(
								    new Integer(li_campo),
								    StringUtils.getStringUpperCase(ls_campo).replaceAll("[\\.\\,-]", " ")
									               .replaceAll("\\s+", " ").replaceAll(" ", "_").replaceAll("Á", "A")
									               .replaceAll("É", "E").replaceAll("Í", "I").replaceAll("Ó", "O")
									               .replaceAll("Ú", "U")
								);
						}
					}

					for(String ls_linea : lcs_ach)
					{
						if(StringUtils.isValidString(ls_linea))
						{
							lsa_campos = ls_linea.split(as_separador);

							if(lsa_campos != null)
							{
								ACH lach_ach;

								lach_ach = new ACH();

								lcach_ach.add(lach_ach);

								lach_ach.setIdArchivo(ls_idArchivo);
								lach_ach.setIdCuenta(ls_idCuenta);
								lach_ach.setIdPeriodoCorte(ls_idPeriodoCorte);
								lach_ach.setIdUsuarioCreacion(as_userId);
								lach_ach.setIpCreacion(as_remoteIp);
								lach_ach.setRegistro(li_registro++);

								for(int li_campo = 0, li_campos = lsa_campos.length; li_campo < li_campos;
									    li_campo++
								)
								{
									String ls_campo;

									ls_campo = lsa_campos[li_campo];

									switch(lmis_ordenCampos.get(new Integer(li_campo)))
									{
										case ACH.CAMPO_BANCO_ORIGINADOR:
											lach_ach.setBancoOrigen(ls_campo);

											break;

										case ACH.CAMPO_BANCO_RECAUDADOR:
											lach_ach.setBancoRecaudador(ls_campo);

											break;

										case ACH.CAMPO_CICLO_ORIGEN:
											lach_ach.setCicloOrigen(obtenerValorEnteroLargo(ls_campo));

											break;

										case ACH.CAMPO_CICLO_TRANSACCION:
											lach_ach.setCicloTransaccion(obtenerValorEnteroLargo(ls_campo));

											break;

										case ACH.CAMPO_COD_DE_AUTORIZACION_RECHAZO_O_FALLIDA:
											lach_ach.setCodigoAutorizacion(ls_campo);

											break;

										case ACH.CAMPO_CUS:
											lach_ach.setCus(obtenerValorEnteroLargo(ls_campo));

											break;

										case ACH.CAMPO_DESCRIPCION:
											lach_ach.setDescripcion(ls_campo);

											break;

										case ACH.CAMPO_EMPRESA:
											lach_ach.setNombreComercio(ls_campo);

											break;

										case ACH.CAMPO_ESTADO:
											lach_ach.setEstadoComercio(ls_campo);

											break;

										case ACH.CAMPO_FECHA_AUTORIZACION:
											lach_ach.setFechaAutorizacion(obtenerValorFecha(ls_campo, as_formatoFecha));

											break;

										case ACH.CAMPO_FECHA_HORA_CREADA:
											lach_ach.setFechaCreada(obtenerValorFecha(ls_campo, as_formatoFecha));

											break;

										case ACH.CAMPO_FECHA_HORA_RESOLUCION_DE_LA_TRANSACCION:
											lach_ach.setFechaResolucion(obtenerValorFecha(ls_campo, as_formatoFecha));

											break;

										case ACH.CAMPO_FECHA_HORA_ULTIMO_ESTADO:
											lach_ach.setFechaUltimoEstado(obtenerValorFecha(ls_campo, as_formatoFecha));

											break;

										case ACH.CAMPO_ID_FUNCIONALIDAD:
											lach_ach.setIdFuncionalidad(ls_campo);

											break;

										case ACH.CAMPO_ID_TAQUILLA:
											lach_ach.setIdTaquilla(ls_campo);

											break;

										case ACH.CAMPO_IMPUESTO:

											Double ld_valorDoubleImpuesto;
											ld_valorDoubleImpuesto = obtenerValorDoble(ls_campo);

											if(ld_valorDoubleImpuesto == null)
												ld_valorDoubleImpuesto = obtenerValorDoble(
													    obtenerStringDecimal(ls_campo)
													);

											lach_ach.setImpuesto(ld_valorDoubleImpuesto);

											break;

										case ACH.CAMPO_MODALIDAD_DE_VINCULACION:
											lach_ach.setModalidadVinculacion(ls_campo);

											break;

										case ACH.CAMPO_NIT:
											lach_ach.setCodigoComercio(ls_campo);

											break;

										case ACH.CAMPO_NOMBRE_FUNCIONALIDAD:
											lach_ach.setNombreFuncionalidad(ls_campo);

											break;

										case ACH.CAMPO_NOMBRE_TAQUILLA:
											lach_ach.setNombreTaquilla(ls_campo);

											break;

										case ACH.CAMPO_REFERENCIA_1:
											lach_ach.setReferencia1(ls_campo);

											break;

										case ACH.CAMPO_REFERENCIA_2:
											lach_ach.setReferencia2(ls_campo);

											break;

										case ACH.CAMPO_REFERENCIA_3:
											lach_ach.setReferencia3(ls_campo);

											break;

										case ACH.CAMPO_SERVICIO_CODIGO:
											lach_ach.setCodigoServicio(obtenerValorEnteroLargo(ls_campo));

											break;

										case ACH.CAMPO_SERVICIO_NIT:
											lach_ach.setNitServicio(ls_campo);

											break;

										case ACH.CAMPO_SERVICIO_NOMBRE:
											lach_ach.setNombreServicio(ls_campo);

											break;

										case ACH.CAMPO_TICKET_ID:
											lach_ach.setTicketId(obtenerValorEnteroLargo(ls_campo));

											break;

										case ACH.CAMPO_TIPO_DE_AUTORIZACION:
											lach_ach.setTipoAutorizacion(ls_campo);

											break;

										case ACH.CAMPO_TIPO_DE_TRANSACCIONES:
											lach_ach.setTipoTransaccion(ls_campo);

											break;

										case ACH.CAMPO_TIPO_DE_USUARIO:
											lach_ach.setTipoUsuario(ls_campo);

											break;

										case ACH.CAMPO_VALOR:

											Double ld_valorDoubleCampoValor;
											ld_valorDoubleCampoValor = obtenerValorDoble(ls_campo);

											if(ld_valorDoubleCampoValor == null)
												ld_valorDoubleCampoValor = NumericUtils.getDoubleWrapper(
													    obtenerStringDecimal(ls_campo)
													);

											lach_ach.setValor(ld_valorDoubleCampoValor);

											break;

										default:
											break;
									}
								}
							}
						}
					}

					DaoCreator.getACHDAO(ldm_manager).insertar(lcach_ach);
				}
				else
					generarInconsistencia(
					    aci_param, InconsistenciasCommon.INCONSISTENCIA_20, apb_business, aca_analista
					);

				FTPUtils.crearDirectorioFTP(
				    acs_canal,
				    obtenerRutaCompleta(
				        as_rutaBase,
				        resolver(
				            as_plantillaRuta, aerc_entidad, aerc_cuenta,
				            DateUtils.getDate(ad_fecha, Calendar.DATE, 1, false)
				        )
				    ), ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO
				);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargueArchivosMulticash", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lac_ach;
	}

	/**
	 * Cargar archivo FTP.
	 *
	 * @param acs_canal the acs canal
	 * @param amss_catalogo the amss catalogo
	 * @param as_archivo the as archivo
	 * @param ad_fecha the ad fecha
	 * @param as_idPlantilla the as id plantilla
	 * @param as_rutaBase the as ruta base
	 * @param aerc_entidad the aerc entidad
	 * @param aerc_cuenta the aerc cuenta
	 * @throws B2BException the b 2 B exception
	 */
	private synchronized void cargarArchivoFTP(
	    ChannelSftp acs_canal, Map<String, String> amss_catalogo, String as_archivo, Date ad_fecha,
	    String as_idPlantilla, String as_rutaBase, EntidadRecaudadoraConciliacion aerc_entidad,
	    EntidadRecaudadoraCuenta aerc_cuenta, ConArchivo aca_archivo
	)
	    throws B2BException
	{
		String ls_nombreArchivo;
		String ls_rutaArchivo;
		String ls_plantillaNombreArchivo;

		ls_plantillaNombreArchivo     = obtenerValorCatalogo(amss_catalogo, as_idPlantilla);
		ls_nombreArchivo              = resolver(
			    ls_plantillaNombreArchivo, aerc_entidad, aerc_cuenta, ad_fecha, aca_archivo
			);
		ls_rutaArchivo                = obtenerRutaCompleta(as_rutaBase, ls_nombreArchivo);

		FTPUtils.cargarArchivoFTP(acs_canal, as_archivo, ls_rutaArchivo);
	}

	/**
	 * Cargar archivos.
	 *
	 * @param apc_proceso de apc proceso
	 * @param acerc_cuenta de acerc cuenta
	 * @param ad_fecha de ad fecha
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private synchronized int cargarArchivos(
	    ProcesoConciliacion apc_proceso, EntidadRecaudadoraCuenta acerc_cuenta, Date ad_fecha, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException, AddressException, MessagingException
	{
		int li_return;
		li_return = -1;

		if(apc_proceso != null)
		{
			boolean lb_alreadyProcessing;
			String  ls_idConstanteBloqueo;
			String  ls_userId;

			ls_userId = null;

			if(!StringUtils.isValidString(as_userId))
			{
				DAOManager ldm_usuario;
				ldm_usuario = DaoManagerFactory.getDAOManagerConciliacion();

				try
				{
					ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
				}
				catch(B2BException lb2be_e)
				{
					ldm_usuario.setRollbackOnly();

					clh_LOGGER.error("firmaMasiva", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_usuario.close();
				}
			}
			else
				ls_userId = as_userId;

			if(!StringUtils.isValidString(ls_userId))
				throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_VALIDO);

			{
				StringBuilder lsb_idConstanteBloqueo;
				lsb_idConstanteBloqueo = new StringBuilder(ConstanteCommon.JOB_CONCILIACIONES_BLOQUEO);

				lsb_idConstanteBloqueo.append("_");
				lsb_idConstanteBloqueo.append(apc_proceso.getIdProcesoConciliacion());

				ls_idConstanteBloqueo = lsb_idConstanteBloqueo.toString();
			}

			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

				ldm_processing.setAutoCommit(true);

				try
				{
					ConstantesDAO lcd_constant;

					lcd_constant             = DaoCreator.getConstantesDAO(ldm_processing);
					lb_alreadyProcessing     = BooleanUtils.getBooleanValue(
						    obtenerConstanteCaracter(lcd_constant, ls_idConstanteBloqueo)
						);

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_idConstanteBloqueo, EstadoCommon.S, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("cargarArchivos", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}

			{
				ChannelSftp       lcs_canal;
				DAOManager        ldm_manager;
				ConInconsistencia lci_inconsistenciaInicial;

				lcs_canal                     = null;
				ldm_manager                   = DaoManagerFactory.getDAOManagerConciliacion();
				lci_inconsistenciaInicial     = new ConInconsistencia(ls_userId, as_remoteIp, EstadoCommon.ACTIVO);

				try
				{
					boolean lb_procesoManual;
					lb_procesoManual = acerc_cuenta != null;

					if(!lb_alreadyProcessing)
					{
						String                               ls_idEntidadRecaudadora;
						ConstantesDAO                        lcd_constante;
						Collection<EntidadRecaudadoraCuenta> lcerc_cuentas;
						EntidadRecaudadoraConciliacion       lerc_entidad;

						lcd_constante               = DaoCreator.getConstantesDAO(ldm_manager);
						lcerc_cuentas               = null;
						ls_idEntidadRecaudadora     = apc_proceso.getIdEntidadRecaudadora();
						lerc_entidad                = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager)
								                                    .findById(ls_idEntidadRecaudadora);

						if(lerc_entidad != null)
						{
							if(lb_procesoManual)
							{
								if(ls_idEntidadRecaudadora.equals(acerc_cuenta.getIdEntidadRecaudadora()))
								{
									lcerc_cuentas = new ArrayList<EntidadRecaudadoraCuenta>();

									lcerc_cuentas.add(acerc_cuenta);
								}
							}
							else
								lcerc_cuentas = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager)
										                      .findAllPorEntidadRecaudadora(ls_idEntidadRecaudadora);
						}

						if(CollectionUtils.isValidCollection(lcerc_cuentas))
						{
							boolean              lb_validarAch;
							boolean              lb_validarAso;
							CuentaAnalistaDAO    lcad_DAO;
							ConstantesDAO        lcd_constant;
							Date                 ld_actual;
							PeriodoCorte         lpc_corte;
							String               ls_achFormatoFecha;
							String               ls_achPlantillaNombreArchivo;
							String               ls_achPlantillaRuta;
							String               ls_achSeparador;
							String               ls_muPlantillaRuta;
							String               ls_rutaInconsistencia;
							String               ls_mucPlantillaNombreArchivo;
							String               ls_mucSeparador;
							String               ls_mudPlantillaNombreArchivo;
							String               ls_mudSeparador;
							String               ls_rutaBase;
							String               ls_idPeriodoCorte;
							MulticashCabeceraDAO lmcd_DAO;
							MulticashDetalleDAO  lmdd_DAO;
							ConResumenDAO        lcrd_DAO;
							ConInconsistenciaDAO lcid_DAO;
							ParameterBusiness    lpb_business;
							ConArchivoDAO        lcArchivod_DAO;

							{
								boolean lb_automatico;
								Date    ld_fecha;

								lb_automatico     = ad_fecha == null;
								ld_fecha          = lb_automatico ? new Date() : ad_fecha;

								if(lb_automatico)
								{
									Calendar cal = Calendar.getInstance();

									cal.setTime(ld_fecha);
									cal.add(Calendar.DAY_OF_MONTH, -1);

									ld_fecha = cal.getTime();
								}

								ld_actual = DateUtils.clearDateFrom(ld_fecha, Calendar.HOUR);
							}

							ls_idPeriodoCorte     = null;
							lpc_corte             = DaoCreator.getPeriodoCorteDAO(ldm_manager).findByDiaCorte(
								    ld_actual
								);

							if(lpc_corte != null)
								ls_idPeriodoCorte = lpc_corte.getIdPeriodoCorte();

							lci_inconsistenciaInicial.setIdPeriodoCorte(ls_idPeriodoCorte);

							lpb_business       = new ParameterBusiness();
							lcArchivod_DAO     = DaoCreator.getConArchivoDAO(ldm_manager);
							lcid_DAO           = DaoCreator.getConInconsistenciaDAO(ldm_manager);
							lcd_constant       = DaoCreator.getConstantesDAO(ldm_manager);
							lcad_DAO           = DaoCreator.getCuentaAnalistaDAO(ldm_manager);
							lcrd_DAO           = DaoCreator.getConResumenDAO(ldm_manager);
							lmcd_DAO           = DaoCreator.getMulticashCabeceraDAO(ldm_manager);
							lmdd_DAO           = DaoCreator.getMulticashDetalleDAO(ldm_manager);
							lcs_canal          = abrirConexionFTP(
								    lcd_constante, lci_inconsistenciaInicial, ConstanteCommon.SERVIDOR_SFTP_CLAVE,
								    ConstanteCommon.SERVIDOR_SFTP_USUARIO, ConstanteCommon.SERVIDOR_SFTP_IP,
								    ConstanteCommon.SERVIDOR_SFTP_PUERTO
								);

							ls_achFormatoFecha               = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.FORMATO_FECHA_ACH
								);
							ls_achPlantillaNombreArchivo     = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_ACH
								);
							ls_achPlantillaRuta              = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_ACH
								);
							ls_achSeparador                  = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SEPARADOR_ARCHIVO_ACH
								);
							ls_muPlantillaRuta               = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_MULTICASH
								);
							ls_rutaInconsistencia            = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_INCONSISTENCIA_GENERAL
								);
							ls_mucPlantillaNombreArchivo     = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_CABECERA
								);
							ls_mucSeparador                  = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SEPARADOR_ARCHIVO_MULTICASH_CABECERA
								);
							ls_mudPlantillaNombreArchivo     = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_DETALLE
								);
							ls_mudSeparador                  = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SEPARADOR_ARCHIVO_MULTICASH_DETALLE
								);
							ls_rutaBase                      = obtenerConstanteCaracter(
								    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE
								);
							lb_validarAch                    = BooleanUtils.getBooleanValue(
								    obtenerConstanteCaracter(lcd_constant, ConstanteCommon.VALIDA_ARCHIVO_ACH)
								);
							lb_validarAso                    = BooleanUtils.getBooleanValue(
								    obtenerConstanteCaracter(lcd_constant, ConstanteCommon.VALIDA_ARCHIVO_ASOBANCARIA)
								);

							try
							{
								lcs_canal.cd(ls_rutaBase);
							}
							catch(Exception le_e)
							{
								generarInconsistencia(
								    lci_inconsistenciaInicial, InconsistenciasCommon.INCONSISTENCIA_2, lpb_business,
								    null
								);
							}

							for(EntidadRecaudadoraCuenta lerc_cuenta : lcerc_cuentas)
							{
								if(lerc_cuenta != null)
								{
									boolean        lb_confrontacionActual;
									CuentaAnalista lca_analista;
									ConResumen     lcr_resumenActual;
									String         ls_idCuenta;

									lb_confrontacionActual     = false;
									lca_analista               = null;
									ls_idCuenta                = lerc_cuenta.getIdCuenta();
									lcr_resumenActual          = lcrd_DAO.findByCuentaPeriodo(
										    ls_idCuenta, ls_idPeriodoCorte
										);

									if(lcr_resumenActual != null)
									{
										boolean lb_estadoMu;
										String  ls_estadoMU;

										ls_estadoMU     = lcr_resumenActual.getEstadoMu();
										lb_estadoMu     = StringUtils.isValidString(ls_estadoMU)
												&& ls_estadoMU.equalsIgnoreCase(EstadoCommon.CONFRONTADO);

										lb_confrontacionActual = lb_estadoMu;

										if(lb_validarAch && !lb_confrontacionActual)
										{
											boolean lb_estadoAch;
											String  ls_estadoAch;

											ls_estadoAch     = lcr_resumenActual.getEstadoAch();
											lb_estadoAch     = !StringUtils.isValidString(ls_estadoAch)
													|| (StringUtils.isValidString(ls_estadoAch)
													&& ls_estadoAch.equalsIgnoreCase(EstadoCommon.CONFRONTADO));

											lb_confrontacionActual = lb_estadoAch;
										}

										if(lb_validarAso && !lb_confrontacionActual)
										{
											boolean lb_estadoAso;
											String  ls_estadoAso;

											ls_estadoAso     = lcr_resumenActual.getEstadoAso();
											lb_estadoAso     = !StringUtils.isValidString(ls_estadoAso)
													|| (StringUtils.isValidString(ls_estadoAso)
													&& ls_estadoAso.equalsIgnoreCase(EstadoCommon.CONFRONTADO));

											lb_confrontacionActual = lb_estadoAso;
										}
									}

									if(!lb_confrontacionActual)
									{
										boolean    lb_primeraConciliacion;
										boolean    lb_confrontacionAnterior;
										String     ls_idPeriodoCorteAnterior;
										ConResumen lcr_resumenAnterior;

										{
											BigInteger lbi_periodo;

											lbi_periodo                   = NumericUtils.getBigInteger(
												    ls_idPeriodoCorte
												);
											lbi_periodo                   = lbi_periodo.subtract(
												    NumericUtils.getBigInteger(1)
												);
											ls_idPeriodoCorteAnterior     = StringUtils.getString(lbi_periodo);
										}

										lb_primeraConciliacion       = false;
										lb_confrontacionAnterior     = true;
										lcr_resumenAnterior          = lcrd_DAO.findByCuentaPeriodo(
											    ls_idCuenta, ls_idPeriodoCorteAnterior
											);

										lci_inconsistenciaInicial.setIdCuenta(ls_idCuenta);

										if(lcr_resumenAnterior != null)
										{
											boolean lb_estadoMu;
											String  ls_estadoMU;

											ls_estadoMU     = lcr_resumenAnterior.getEstadoMu();
											lb_estadoMu     = StringUtils.isValidString(ls_estadoMU)
													&& ls_estadoMU.equalsIgnoreCase(EstadoCommon.CONFRONTADO);

											lb_confrontacionAnterior = lb_estadoMu;

											if(lb_validarAch && lb_confrontacionAnterior)
											{
												boolean lb_estadoAch;
												String  ls_estadoAch;

												ls_estadoAch     = lcr_resumenAnterior.getEstadoAch();
												lb_estadoAch     = !StringUtils.isValidString(ls_estadoAch)
														|| (StringUtils.isValidString(ls_estadoAch)
														&& ls_estadoAch.equalsIgnoreCase(EstadoCommon.CONFRONTADO));

												lb_confrontacionAnterior = lb_estadoAch;
											}

											if(lb_validarAso && lb_confrontacionAnterior)
											{
												boolean lb_estadoAso;
												String  ls_estadoAso;

												ls_estadoAso     = lcr_resumenAnterior.getEstadoAso();
												lb_estadoAso     = !StringUtils.isValidString(ls_estadoAso)
														|| (StringUtils.isValidString(ls_estadoAso)
														&& ls_estadoAso.equalsIgnoreCase(EstadoCommon.CONFRONTADO));

												lb_confrontacionAnterior = lb_estadoAso;
											}
										}
										else
										{
											lb_primeraConciliacion = lcrd_DAO.findCountByCuenta(ls_idCuenta) <= 1;

											if(!lb_primeraConciliacion)
												lb_confrontacionAnterior = false;
										}

										if(lb_confrontacionAnterior)
										{
											boolean             lb_resumenExistente;
											ConResumen          lcr_resumen;
											ArchivoConciliacion lac_archivoAch;
											ArchivoConciliacion lac_archivoAso;
											ArchivoConciliacion lac_archivoMu;

											lac_archivoAch     = null;
											lac_archivoAso     = null;
											lac_archivoMu      = null;

											lcr_resumen             = lcrd_DAO.findByCuentaPeriodo(
												    ls_idCuenta, ls_idPeriodoCorte
												);
											lb_resumenExistente     = lcr_resumen != null;

											if(!lb_resumenExistente)
											{
												lcr_resumen = new ConResumen();

												lcr_resumen.setIdUsuarioCreacion(ls_userId);
												lcr_resumen.setIpCreacion(as_remoteIp);
												lcr_resumen.setIdCuenta(ls_idCuenta);
												lcr_resumen.setIdPeriodoCorte(ls_idPeriodoCorte);
												lcr_resumen.setEstadoMu(EstadoCommon.INCONSISTENTE);

												lcrd_DAO.insert(lcr_resumen);
											}
											else
											{
												lcr_resumen.setIdUsuarioModificacion(ls_userId);
												lcr_resumen.setIpModificacion(as_remoteIp);

												lcrd_DAO.update(lcr_resumen);
											}

											{
												String ls_nombreContacto;
												String ls_numeroContacto;
												String ls_correoContacto;

												ls_nombreContacto     = lerc_cuenta.getNombreContacto();
												ls_numeroContacto     = lerc_cuenta.getNumeroCelContacto();
												ls_correoContacto     = lerc_cuenta.getCorreoElectronicoContacto();

												if(
												    !StringUtils.isValidString(ls_nombreContacto)
													    || !StringUtils.isValidString(ls_numeroContacto)
													    || !StringUtils.isValidString(ls_correoContacto)
												)
													generarInconsistencia(
													    lci_inconsistenciaInicial,
													    InconsistenciasCommon.INCONSISTENCIA_6, lpb_business,
													    lca_analista
													);
											}

											try
											{
												lca_analista = lcad_DAO.findByIdCuenta(lerc_cuenta.getIdCuenta());

												if(lca_analista == null)
													generarInconsistencia(
													    lci_inconsistenciaInicial,
													    InconsistenciasCommon.INCONSISTENCIA_5, lpb_business,
													    lca_analista
													);

												if(lca_analista != null)
												{
													Double ld_saldoInicial;
													Date   ld_fechaInicial;

													ld_saldoInicial     = lca_analista.getSaldoInicial();
													ld_fechaInicial     = lca_analista.getFechaSaldoInicial();

													if((ld_saldoInicial == null) || (ld_fechaInicial == null))
														generarInconsistencia(
														    lci_inconsistenciaInicial,
														    InconsistenciasCommon.INCONSISTENCIA_7, lpb_business,
														    lca_analista
														);
													else if(lb_primeraConciliacion)
													{
														PeriodoCorte lpc_fechaInicial;
														lpc_fechaInicial = DaoCreator.getPeriodoCorteDAO(ldm_manager)
																                         .findByDiaCorte(
																    ld_fechaInicial
																);

														if(lpc_fechaInicial != null)
														{
															String ls_idPeriodoCorteFechaInicial;
															ls_idPeriodoCorteFechaInicial = lpc_fechaInicial
																	.getIdPeriodoCorte();

															if(
															    !ls_idPeriodoCorteFechaInicial.equalsIgnoreCase(
																        ls_idPeriodoCorte
																    )
															)
																generarInconsistencia(
																    lci_inconsistenciaInicial,
																    InconsistenciasCommon.INCONSISTENCIA_17,
																    lpb_business, lca_analista
																);
														}
													}
												}

												lac_archivoMu = cargarArchivosMulticash(
													    lca_analista, lci_inconsistenciaInicial, lcs_canal, lpc_corte,
													    lerc_cuenta, lerc_entidad, ld_actual, ls_rutaBase,
													    ls_mucPlantillaNombreArchivo, ls_mudPlantillaNombreArchivo,
													    ls_muPlantillaRuta, ls_mucSeparador, ls_mudSeparador, ls_userId,
													    as_remoteIp, lb_primeraConciliacion, lpb_business
													);

												if(lac_archivoMu != null)
												{
													MulticashCabecera lmc_multicash;
													String            ls_idArchivo;
													String            ls_estadoMu;
													String            ls_rutaMU;

													ls_idArchivo      = lac_archivoMu.getId();
													lmc_multicash     = lmcd_DAO.encontrarPorId(lac_archivoMu.getId());
													ls_rutaMU         = lac_archivoMu.getRuta();

													{
														boolean           lb_inconsistencias;
														ConInconsistencia lci_data;

														lci_data = new ConInconsistencia(lci_inconsistenciaInicial);

														lci_data.setIdArchivo(ls_idArchivo);

														lb_inconsistencias     = CollectionUtils.isValidCollection(
															    lcid_DAO.findById(lci_data)
															);
														ls_estadoMu            = lb_inconsistencias
															? EstadoCommon.INCONSISTENTE : EstadoCommon.CARGADO;

														if(lb_inconsistencias)
															generarArchivoInconsistencia(
															    ConstanteCommon.NOMBRE_ARCHIVO_SALIDA_DE_INCONSISTENCIAS,
															    ls_rutaMU,
															    lcid_DAO.findInformacionByArchivo(ls_idArchivo),
															    lcs_canal, lerc_cuenta, lerc_entidad, ld_actual,
															    lcd_constante, lpb_business,
															    lcArchivod_DAO.findById(ls_idArchivo), ldm_manager
															);
														else
														{
															String ls_idArchivoDetalle;

															ls_idArchivoDetalle     = lac_archivoMu.getIdDetalle();
															lci_data                = new ConInconsistencia(
																    lci_inconsistenciaInicial
																);

															lci_data.setIdArchivo(ls_idArchivoDetalle);

															lb_inconsistencias     = CollectionUtils.isValidCollection(
																    lcid_DAO.findById(lci_data)
																);
															ls_estadoMu            = lb_inconsistencias
																? EstadoCommon.INCONSISTENTE : EstadoCommon.CARGADO;

															if(lb_inconsistencias)
																generarArchivoInconsistencia(
																    ConstanteCommon.NOMBRE_ARCHIVO_SALIDA_DE_INCONSISTENCIAS,
																    ls_rutaMU,
																    lcid_DAO.findInformacionByArchivo(
																        ls_idArchivoDetalle
																    ), lcs_canal, lerc_cuenta, lerc_entidad, ld_actual,
																    lcd_constante, lpb_business,
																    lcArchivod_DAO.findById(ls_idArchivoDetalle),
																    ldm_manager
																);
														}

														if(!lb_inconsistencias)
															enviarCorreoInconsistencia(
															    generarInconsistencia(
															        lci_inconsistenciaInicial,
															        InconsistenciasCommon.INCONSISTENCIA_93,
															        lpb_business, lca_analista
															    ), lca_analista, null, null, null,
															    PlantillaMensajeCommon.PLANTILLA_2, ldm_manager,
															    lpb_business
															);
													}

													lcr_resumen.setIdArchivoMu(ls_idArchivo);
													lcr_resumen.setVersionArchivoMu(lac_archivoMu.getVersion());
													lcr_resumen.setEstadoMu(ls_estadoMu);
													lcr_resumen.setFechaEstadoMu(new Date());

													if(lmc_multicash != null)
													{
														lcr_resumen.setSaldoInicial(lmc_multicash.getSaldoInicial());
														lcr_resumen.setSaldoFinal(lmc_multicash.getSaldoFinal());
													}

													lcrd_DAO.update(lcr_resumen);

													if(
													    StringUtils.isValidString(ls_estadoMu)
														    && ls_estadoMu.equalsIgnoreCase(EstadoCommon.CARGADO)
													)
														li_return = confrontacionMulticashCPRS(
															    ls_idArchivo, lb_procesoManual, ls_userId, as_remoteIp,
															    ldm_manager
															);
												}

												if(lb_validarAch)
													lac_archivoAch = cargarArchivoACH(
														    lci_inconsistenciaInicial, lcs_canal, lpc_corte, lerc_cuenta,
														    lerc_entidad, ld_actual, ls_rutaBase,
														    ls_achPlantillaNombreArchivo, ls_achPlantillaRuta,
														    ls_achFormatoFecha, ls_achSeparador, ls_userId, as_remoteIp,
														    lca_analista, lpb_business
														);

												if(lb_validarAso)
												{
													// TODO Validar Asobancaria
												}
											}
											catch(Exception le_e)
											{
												clh_LOGGER.error("cargueArchivos", le_e);
											}
										}
										else
											generarInconsistencia(
											    lci_inconsistenciaInicial, InconsistenciasCommon.INCONSISTENCIA_31,
											    lpb_business, lca_analista
											);
									}

									generarArchivoInconsistencia(
									    ConstanteCommon.NOMBRE_ARCHIVO_SALIDA_DE_INCONSISTENCIAS_CUENTA_NO_ARCHIVO,
									    obtenerRutaCompleta(
									        ls_rutaBase,
									        resolver(ls_rutaInconsistencia, lerc_entidad, lerc_cuenta, ad_fecha)
									    ), lcid_DAO.findInformacionByArchivo(null, ls_idCuenta, ls_idPeriodoCorte),
									    lcs_canal, lerc_cuenta, lerc_entidad, ld_actual, lcd_constante, lpb_business,
									    null, ldm_manager
									);

									if(li_return != 0)
										generarInconsistencia(
										    lci_inconsistenciaInicial, InconsistenciasCommon.INCONSISTENCIA_95,
										    lpb_business, lca_analista
										);
								}
							}
						}
					}
					else if(lb_procesoManual)
						throw new B2BException(ErrorKeys.ERROR_PROCESO_BLOQUEADO);
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("cargarArchivos", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					if(!lb_alreadyProcessing)
					{
						DAOManager ldm_processing;
						ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

						ldm_processing.setAutoCommit(true);

						try
						{
							DaoCreator.getConstantesDAO(ldm_processing)
								          .updateString(ls_idConstanteBloqueo, EstadoCommon.N, ls_userId);
						}
						catch(B2BException lb2be_e)
						{
							ldm_processing.setRollbackOnly();

							clh_LOGGER.error("cargarArchivos", lb2be_e);

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

		return li_return;
	}

	/**
	 * Cargar en BD los archivos Multicash que se encuentran en el FTP para un banco / cuenta / día.
	 *
	 * @param aca_analista de aca analista
	 * @param aci_param de aci param
	 * @param acs_canal de acs canal
	 * @param apc_corte de apc corte
	 * @param aerc_cuenta de aerc cuenta
	 * @param aerc_entidad de aerc entidad
	 * @param ad_fecha de ad fecha
	 * @param as_rutaBase de as ruta base
	 * @param as_plantillaNombreArchivoCabecera de as plantilla nombre archivo cabecera
	 * @param as_plantillaNombreArchivoDetalle de as plantilla nombre archivo detalle
	 * @param as_plantillaRuta de as plantilla ruta
	 * @param as_separadorCabecera de as separador cabecera
	 * @param as_separadorDetalle de as separador detalle
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se ejecuta la accion
	 * @return el valor de archivo conciliacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws ParseException
	 */
	private synchronized ArchivoConciliacion cargarArchivosMulticash(
	    CuentaAnalista aca_analista, ConInconsistencia aci_param, ChannelSftp acs_canal, PeriodoCorte apc_corte,
	    EntidadRecaudadoraCuenta aerc_cuenta, EntidadRecaudadoraConciliacion aerc_entidad, Date ad_fecha,
	    String as_rutaBase, String as_plantillaNombreArchivoCabecera, String as_plantillaNombreArchivoDetalle,
	    String as_plantillaRuta, String as_separadorCabecera, String as_separadorDetalle, String as_userId,
	    String as_remoteIp, boolean ab_primeraConciliacion, ParameterBusiness apb_business
	)
	    throws B2BException, AddressException, MessagingException, ParseException
	{
		ArchivoConciliacion lac_muc;

		lac_muc = null;

		if((aerc_cuenta != null) && (apc_corte != null))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManagerConciliacion();

			try
			{
				boolean                lb_inconsistencia;
				ArchivoConciliacionDAO lacd_archivo;
				Collection<String>     lcs_muc;
				String                 ls_rutaMud;
				String                 ls_rutaBase;
				String                 ls_idCuenta;
				String                 ls_idArchivoCabecera;
				String                 ls_idArchivoDetalle;

				lb_inconsistencia        = false;
				lcs_muc                  = null;
				ls_idCuenta              = aerc_cuenta.getIdCuenta();
				lacd_archivo             = DaoCreator.getArchivoConciliacionDAO(ldm_manager);
				ls_idArchivoCabecera     = null;
				ls_idArchivoDetalle      = null;

				aci_param.setIdCuenta(ls_idCuenta);
				aci_param.setIdPeriodoCorte(apc_corte.getIdPeriodoCorte());

				{
					String ls_nombreArchivo;

					ls_rutaBase = obtenerRutaCompleta(
						    as_rutaBase, resolver(as_plantillaRuta, aerc_entidad, aerc_cuenta, ad_fecha)
						);

					try
					{
						acs_canal.cd(ls_rutaBase);
					}
					catch(Exception le_e)
					{
						lb_inconsistencia = true;
						generarInconsistencia(
						    aci_param, InconsistenciasCommon.INCONSISTENCIA_3, apb_business, aca_analista
						);
					}

					ls_nombreArchivo = resolver(as_plantillaNombreArchivoCabecera, aerc_entidad, aerc_cuenta, ad_fecha);

					try
					{
						lac_muc = descargarArchivoFTP(acs_canal, obtenerRutaCompleta(ls_rutaBase, ls_nombreArchivo));

						if(lac_muc == null)
						{
							lb_inconsistencia = true;
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_10, apb_business, aca_analista
							);
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_71, apb_business, aca_analista
							);
						}
					}
					catch(Exception le_e)
					{
						lb_inconsistencia = true;
						generarInconsistencia(
						    aci_param, InconsistenciasCommon.INCONSISTENCIA_10, apb_business, aca_analista
						);
					}

					if(lac_muc != null)
					{
						lac_muc.setIdCuenta(ls_idCuenta);
						lac_muc.setIdPeriodoCorte(apc_corte.getIdPeriodoCorte());
						lac_muc.setIdUsuarioCreacion(as_userId);
						lac_muc.setNombreArchivo(ls_nombreArchivo);
						lac_muc.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_MULTICASH_CABECERA);

						lcs_muc                  = lac_muc.getLineas();
						lac_muc                  = lacd_archivo.insertar(lac_muc);
						ls_idArchivoCabecera     = lac_muc.getId();
					}

					lac_muc.setRuta(ls_rutaBase);
				}

				if(CollectionUtils.isValidCollection(lcs_muc))
				{
					boolean                      lb_cabeceraOK;
					boolean                      lb_detalleOK;
					Collection<MulticashDetalle> lcmd_mud;
					int                          li_cantidadMovimientos;
					MulticashCabeceraDAO         lmcd_DAO;
					MulticashCabecera            lmc_muc;
					String                       ls_claveBanco;
					String                       ls_idPeriodoCorte;
					String                       ls_cuenta;
					Map<String, String>          lmss_caracteres;

					lb_cabeceraOK              = true;
					lb_detalleOK               = true;
					lcmd_mud                   = new ArrayList<MulticashDetalle>();
					li_cantidadMovimientos     = 0;
					lmcd_DAO                   = DaoCreator.getMulticashCabeceraDAO(ldm_manager);
					lmc_muc                    = new MulticashCabecera();
					ls_claveBanco              = aerc_entidad.getCodigoEntidadRecaudadora();
					ls_cuenta                  = aerc_cuenta.getNumeroCuenta();
					ls_idPeriodoCorte          = apc_corte.getIdPeriodoCorte();
					lmss_caracteres            = generarCodigos(
						    ConstanteCommon.CARACTERES_PERMITIDOS_ARCHIVO_MULTICASH, ldm_manager
						);

					aci_param.setIdArchivo(ls_idArchivoCabecera);

					{
						String[] lsa_campos;

						lsa_campos = lcs_muc.iterator().next().split(as_separadorCabecera, 100);

						if(lsa_campos != null)
						{
							if(lsa_campos.length == 18)
							{
								MulticashCabecera lmc_mucAnterior;
								String            ls_idPeriodoCorteAnterior;
								long              ll_corte;
								Long              ll_idRegistro;
								String            ls_tipoMoneda;

								ls_idPeriodoCorteAnterior     = null;
								ll_corte                      = apc_corte.getCorte();
								ll_idRegistro                 = NumericUtils.getLongWrapper(1);
								ls_tipoMoneda                 = obtenerConstanteCaracter(
									    DaoCreator.getConstantesDAO(ldm_manager), ConstanteCommon.TIPO_MONEDA_PESOS
									);

								{
									int li_periodoCorte;

									li_periodoCorte = NumericUtils.getInt(ls_idPeriodoCorte);
									li_periodoCorte--;

									ls_idPeriodoCorteAnterior = StringUtils.getString(li_periodoCorte);
								}

								lmc_mucAnterior = lmcd_DAO.findByCuentaPeriodo(ls_idCuenta, ls_idPeriodoCorteAnterior);

								lmc_muc.setIdArchivo(ls_idArchivoCabecera);
								lmc_muc.setIdCuenta(ls_idCuenta);
								lmc_muc.setIdPeriodoCorte(ls_idPeriodoCorte);
								lmc_muc.setIdUsuarioCreacion(as_userId);
								lmc_muc.setIpCreacion(as_remoteIp);

								for(
								    int li_campo = 0, li_campos = lsa_campos.length;
									    lb_cabeceraOK && (li_campo < li_campos); li_campo++
								)
								{
									String ls_campo;

									ls_campo = lsa_campos[li_campo];

									switch(li_campo)
									{
										case 0:

											if(
											    !((ls_claveBanco != null) && (ls_campo != null)
												    && ls_claveBanco.equals(ls_campo))
											)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(ls_campo.length() > 12)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}

											break;

										case 1:

											if(ls_campo.length() > 24)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(
											    !((ls_cuenta != null) && (ls_campo != null)
												    && ls_cuenta.equals(ls_campo))
											)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}

											break;

										case 2:

											if(ls_campo.length() > 4)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(obtenerValorEntero(ls_campo) == null)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(NumericUtils.getLong(ls_campo) != ll_corte)
												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_12, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}

											break;

										case 3:

											if(ls_campo.length() > 8)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												Date             ld_fechaCampo;
												SimpleDateFormat lsdf_format;

												ld_fechaCampo     = null;
												lsdf_format       = new SimpleDateFormat("dd.MM.yy");

												try
												{
													ld_fechaCampo = lsdf_format.parse(ls_campo);
												}
												catch(ParseException lpe_pe)
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}

												if(ld_fechaCampo == null)
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
												else
												{
													String ls_fechaCorte;

													ls_fechaCorte = lsdf_format.format(apc_corte.getDiaCorte());

													if(StringUtils.isValidString(ls_fechaCorte))
													{
														Date ld_fechaCorte;

														ld_fechaCorte = lsdf_format.parse(ls_fechaCorte);

														if(
														    (ld_fechaCorte != null)
															    && (ld_fechaCorte.compareTo(ld_fechaCampo) != 0)
														)
														{
															lb_cabeceraOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_100,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
													}
												}
											}

											break;

										case 4:

											if(ls_campo.length() > 3)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(!ls_tipoMoneda.equals(ls_campo))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
												lmc_muc.setClaveMoneda(ls_campo);

											break;

										case 5:

											if(ls_campo.length() > 22)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												String ls_signo;
												ls_signo = ls_campo.substring(0, 1);

												if(
												    ls_signo.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_MAS)
													    || ls_signo.equalsIgnoreCase(
													        IdentificadoresCommon.SIMBOLO_MENOS
													    )
												)
												{
													Double ld_saldoInicialActual;
													ld_saldoInicialActual = obtenerValorDoble(ls_campo);

													if(ld_saldoInicialActual == null)
														ld_saldoInicialActual = obtenerValorDoble(
															    obtenerStringDecimal(ls_campo)
															);

													if(ld_saldoInicialActual != null)
													{
														if(ab_primeraConciliacion)
														{
															Double ld_saldoInicialAnalista;
															ld_saldoInicialAnalista = aca_analista.getSaldoInicial();

															if(
															    ld_saldoInicialActual.compareTo(
																        ld_saldoInicialAnalista
																    ) != 0
															)
															{
																lb_inconsistencia = true;

																generarInconsistencia(
																    aci_param, InconsistenciasCommon.INCONSISTENCIA_17,
																    ll_idRegistro, StringUtils.getString(li_campo + 1),
																    apb_business, aca_analista
																);

																enviarCorreoInconsistencia(
																    null, null, aerc_cuenta, aerc_entidad, ad_fecha,
																    PlantillaMensajeCommon.PLANTILLA_4, ldm_manager,
																    null
																);
															}
														}
														else
														{
															Double ld_saldoFinalAnterior;
															ld_saldoFinalAnterior = lmc_mucAnterior.getSaldoFinal();

															if(
															    ld_saldoInicialActual.compareTo(ld_saldoFinalAnterior) != 0
															)
															{
																lb_inconsistencia = true;

																generarInconsistencia(
																    aci_param, InconsistenciasCommon.INCONSISTENCIA_16,
																    ll_idRegistro, StringUtils.getString(li_campo + 1),
																    apb_business, aca_analista
																);

																enviarCorreoInconsistencia(
																    null, null, aerc_cuenta, aerc_entidad, ad_fecha,
																    PlantillaMensajeCommon.PLANTILLA_4, ldm_manager,
																    null
																);
															}
														}

														lmc_muc.setSaldoInicial(ld_saldoInicialActual);
													}
													else
													{
														lb_cabeceraOK = false;

														generarInconsistencia(
														    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
														    ll_idRegistro, StringUtils.getString(li_campo + 1),
														    apb_business, aca_analista
														);
													}
												}
												else
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
											}

											break;

										case 6:

											if(ls_campo.length() > 22)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												String ls_signo;

												ls_signo = ls_campo.substring(0, 1);

												if(
												    ls_signo.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_MAS)
													    || ls_signo.equalsIgnoreCase(
													        IdentificadoresCommon.SIMBOLO_MENOS
													    )
												)
												{
													Double ld_valorDouble6;
													ld_valorDouble6 = obtenerValorDoble(ls_campo);

													if(ld_valorDouble6 == null)
														ld_valorDouble6 = obtenerValorDoble(
															    obtenerStringDecimal(ls_campo)
															);

													if(ld_valorDouble6 != null)
														lmc_muc.setTotalDebitos(ld_valorDouble6);
													else
													{
														lb_cabeceraOK = false;

														generarInconsistencia(
														    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
														    ll_idRegistro, StringUtils.getString(li_campo + 1),
														    apb_business, aca_analista
														);
													}
												}
												else
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
											}

											break;

										case 7:

											if(ls_campo.length() > 22)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												String ls_signo;

												ls_signo = ls_campo.substring(0, 1);

												if(
												    ls_signo.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_MAS)
													    || ls_signo.equalsIgnoreCase(
													        IdentificadoresCommon.SIMBOLO_MENOS
													    )
												)
												{
													Double ld_valorDouble7;
													ld_valorDouble7 = obtenerValorDoble(ls_campo);

													if(ld_valorDouble7 == null)
														ld_valorDouble7 = obtenerValorDoble(
															    obtenerStringDecimal(ls_campo)
															);

													if(ld_valorDouble7 != null)
														lmc_muc.setTotalCreditos(ld_valorDouble7);
													else
													{
														lb_cabeceraOK = false;

														generarInconsistencia(
														    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
														    ll_idRegistro, StringUtils.getString(li_campo + 1),
														    apb_business, aca_analista
														);
													}
												}
												else
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
											}

											break;

										case 8:

											if(ls_campo.length() > 22)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												String ls_signo;

												ls_signo = ls_campo.substring(0, 1);

												if(
												    ls_signo.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_MAS)
													    || ls_signo.equalsIgnoreCase(
													        IdentificadoresCommon.SIMBOLO_MENOS
													    )
												)
												{
													Double ld_valorDouble8;
													ld_valorDouble8 = obtenerValorDoble(ls_campo);

													if(ld_valorDouble8 == null)
														ld_valorDouble8 = obtenerValorDoble(
															    obtenerStringDecimal(ls_campo)
															);

													if(ld_valorDouble8 != null)
														lmc_muc.setSaldoFinal(ld_valorDouble8);
													else
													{
														lb_cabeceraOK = false;

														generarInconsistencia(
														    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
														    ll_idRegistro, StringUtils.getString(li_campo + 1),
														    apb_business, aca_analista
														);
													}
												}
												else
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
											}

											break;

										case 17:

											if(ls_campo.length() > 5)
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_66, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
											{
												lb_cabeceraOK = false;

												generarInconsistencia(
												    aci_param, InconsistenciasCommon.INCONSISTENCIA_67, ll_idRegistro,
												    StringUtils.getString(li_campo + 1), apb_business, aca_analista
												);
											}
											else
											{
												Integer li_valorCampo;

												li_valorCampo = obtenerValorEntero(ls_campo);

												if(li_valorCampo != null)
												{
													li_cantidadMovimientos = NumericUtils.getInt(li_valorCampo);

													lmc_muc.setCantidadMovimientos(li_valorCampo);
												}
												else
												{
													lb_cabeceraOK = false;

													generarInconsistencia(
													    aci_param, InconsistenciasCommon.INCONSISTENCIA_66,
													    ll_idRegistro, StringUtils.getString(li_campo + 1), apb_business,
													    aca_analista
													);
												}
											}

											break;

										default:
											break;
									}
								}
							}
							else
							{
								lb_cabeceraOK = false;

								generarInconsistencia(
								    aci_param, InconsistenciasCommon.INCONSISTENCIA_65, apb_business, aca_analista
								);
							}
						}
						else
						{
							lb_cabeceraOK = false;

							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_71, apb_business, aca_analista
							);
						}
					}

					if(lb_cabeceraOK && (li_cantidadMovimientos > 0))
					{
						ArchivoConciliacion lac_mud;
						Collection<String>  lcs_mud;
						String              ls_nombreArchivo;
						int                 li_registro;

						lac_mud              = null;
						ls_nombreArchivo     = resolver(
							    as_plantillaNombreArchivoDetalle, aerc_entidad, aerc_cuenta, ad_fecha
							);
						li_registro          = 0;
						ls_rutaMud           = obtenerRutaCompleta(ls_rutaBase, ls_nombreArchivo);

						try
						{
							lac_mud = descargarArchivoFTP(acs_canal, ls_rutaMud);

							if(lac_mud == null)
							{
								lb_inconsistencia = true;

								generarInconsistencia(
								    aci_param, InconsistenciasCommon.INCONSISTENCIA_9, apb_business, aca_analista
								);
								generarInconsistencia(
								    aci_param, InconsistenciasCommon.INCONSISTENCIA_72, apb_business, aca_analista
								);
							}
						}
						catch(Exception le_e)
						{
							lb_inconsistencia = true;
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_9, apb_business, aca_analista
							);
						}

						lcs_mud = null;

						if(lac_mud != null)
						{
							lac_mud.setIdCuenta(ls_idCuenta);
							lac_mud.setIdPeriodoCorte(ls_idPeriodoCorte);
							lac_mud.setIdUsuarioCreacion(as_userId);
							lac_mud.setNombreArchivo(ls_nombreArchivo);
							lac_mud.setTipoArchivo(ArchivoConciliacion.TIPO_ARCHIVO_MULTICASH_DETALLE);

							lcs_mud                 = lac_mud.getLineas();
							lac_mud                 = lacd_archivo.insertar(lac_mud);
							ls_idArchivoDetalle     = lac_mud.getId();

							lac_muc.setIdDetalle(ls_idArchivoDetalle);
						}

						if(CollectionUtils.isValidCollection(lcs_mud))
						{
							lb_detalleOK = true;

							aci_param.setIdArchivo(ls_idArchivoDetalle);

							for(String ls_linea : lcs_mud)
							{
								if(StringUtils.isValidString(ls_linea))
								{
									String[] lsa_campos;

									lsa_campos = ls_linea.split(as_separadorDetalle, 100);

									if(lsa_campos != null)
									{
										li_registro++;

										if((lsa_campos.length >= 36) && (lsa_campos.length <= 37))
										{
											Long             ll_idRegistro;
											MulticashDetalle lmd_mud;

											lmd_mud = new MulticashDetalle();

											lmd_mud.setIdArchivo(ls_idArchivoDetalle);
											lmd_mud.setIdArchivoCabecera(ls_idArchivoCabecera);
											lmd_mud.setIdCuenta(ls_idCuenta);
											lmd_mud.setIdPeriodoCorte(ls_idPeriodoCorte);
											lmd_mud.setIdUsuarioCreacion(as_userId);
											lmd_mud.setIpCreacion(as_remoteIp);
											lmd_mud.setRegistro(li_registro);

											ll_idRegistro = NumericUtils.getLongWrapper(li_registro);

											for(
											    int li_campo = 0, li_campos = lsa_campos.length;
												    lb_detalleOK && (li_campo < li_campos); li_campo++
											)
											{
												String ls_campo;

												ls_campo = lsa_campos[li_campo];

												switch(li_campo)
												{
													case 0:

														if(
														    !((ls_claveBanco != null) && (ls_campo != null)
															    && ls_claveBanco.equals(ls_campo))
														)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(ls_campo.length() > 12)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}

														break;

													case 1:

														if(
														    !((ls_cuenta != null) && (ls_campo != null)
															    && ls_cuenta.equals(ls_campo))
														)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(ls_campo.length() > 24)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}

														break;

													case 5:

														if(ls_campo.length() > 2)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setIdentificadorCredito(ls_campo);

														break;

													case 6:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setClaveTransaccion(ls_campo);

														break;

													case 9:

														if(ls_campo.length() > 16)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setNumeroCheque(ls_campo);

														break;

													case 10:

														if(ls_campo.length() > 22)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
														{
															String ls_signo;

															ls_signo = ls_campo.substring(0, 1);

															if(
															    ls_signo.equalsIgnoreCase(
																        IdentificadoresCommon.SIMBOLO_MAS
																    )
																    || ls_signo.equalsIgnoreCase(
																        IdentificadoresCommon.SIMBOLO_MENOS
																    )
															)
															{
																Double ld_monto;
																ld_monto = obtenerValorDoble(ls_campo);

																if(ld_monto == null)
																	ld_monto = obtenerValorDoble(
																		    obtenerStringDecimal(ls_campo)
																		);

																if(ld_monto != null)
																	lmd_mud.setMonto(ld_monto);
																else
																{
																	lb_detalleOK = false;

																	generarInconsistencia(
																	    aci_param,
																	    InconsistenciasCommon.INCONSISTENCIA_69,
																	    ll_idRegistro,
																	    StringUtils.getString(li_campo + 1),
																	    apb_business, aca_analista
																	);
																}
															}
															else
															{
																lb_detalleOK = false;

																generarInconsistencia(
																    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
																    ll_idRegistro, StringUtils.getString(li_campo + 1),
																    apb_business, aca_analista
																);
															}
														}

														break;

													case 13:

														if(ls_campo.length() > 8)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
														{
															Date ld_fechaAfectacionSaldo;

															ld_fechaAfectacionSaldo = DateUtils.getDate(
																    ls_campo, "yy.MM.dd"
																);

															if(ld_fechaAfectacionSaldo != null)
																lmd_mud.setFechaAfectacionSaldo(
																    ld_fechaAfectacionSaldo
																);
															else
															{
																lb_detalleOK = false;

																generarInconsistencia(
																    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
																    ll_idRegistro, StringUtils.getString(li_campo + 1),
																    apb_business, aca_analista
																);
															}
														}

														break;

													case 16:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setReferenciaNit(ls_campo);

														break;

													case 17:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setTipoRecaudo(ls_campo);

														break;

													case 18:

														if(ls_campo.length() > 24)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setReferencia(ls_campo);

														break;

													case 19:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setCausalRechazo(ls_campo);

														break;

													case 20:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setCodigoUnicoTransaccion(ls_campo);

														break;

													case 21:

														if(ls_campo.length() > 27)
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_69,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else if(contieneCaracteresEspeciales(ls_campo, lmss_caracteres))
														{
															lb_detalleOK = false;

															generarInconsistencia(
															    aci_param, InconsistenciasCommon.INCONSISTENCIA_70,
															    ll_idRegistro, StringUtils.getString(li_campo + 1),
															    apb_business, aca_analista
															);
														}
														else
															lmd_mud.setFormatoConsignacion(ls_campo);

														break;

													default:
														break;
												}
											}

											lcmd_mud.add(lmd_mud);
										}
										else
										{
											lb_detalleOK = false;

											generarInconsistencia(
											    aci_param, InconsistenciasCommon.INCONSISTENCIA_68,
											    NumericUtils.getLongWrapper(li_registro), null, apb_business,
											    aca_analista
											);
										}
									}
								}
							}
						}
						else
						{
							lb_detalleOK = false;

							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_72, apb_business, aca_analista
							);
						}

						if(li_registro != li_cantidadMovimientos)
						{
							lb_detalleOK = false;

							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_77, apb_business, aca_analista
							);
							aci_param.setIdArchivo(ls_idArchivoCabecera);
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_77, apb_business, aca_analista
							);
						}
					}

					if(lb_cabeceraOK && lb_detalleOK)
					{
						if(!lb_inconsistencia)
						{
							lmcd_DAO.insertar(lmc_muc);

							DaoCreator.getMulticashDetalleDAO(ldm_manager).insertar(lcmd_mud);

							aci_param.setIdArchivo(null);

							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_35, apb_business, aca_analista
							);
						}
					}
					else
					{
						lb_inconsistencia = true;

						if(!lb_cabeceraOK)
						{
							aci_param.setIdArchivo(ls_idArchivoCabecera);
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_11, apb_business, aca_analista
							);
						}

						if(!lb_detalleOK)
						{
							aci_param.setIdArchivo(ls_idArchivoDetalle);
							generarInconsistencia(
							    aci_param, InconsistenciasCommon.INCONSISTENCIA_11, apb_business, aca_analista
							);
						}
					}
				}
				else
				{
					lb_inconsistencia = true;

					aci_param.setIdArchivo(ls_idArchivoCabecera);
					generarInconsistencia(
					    aci_param, InconsistenciasCommon.INCONSISTENCIA_71, apb_business, aca_analista
					);
				}

				if(!lb_inconsistencia)
					FTPUtils.crearDirectorioFTP(
					    acs_canal,
					    obtenerRutaCompleta(
					        as_rutaBase,
					        resolver(
					            as_plantillaRuta, aerc_entidad, aerc_cuenta,
					            DateUtils.getDate(ad_fecha, Calendar.DATE, 1, false)
					        )
					    ), ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargueArchivosMulticash", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lac_muc;
	}

	/**
	 * Validar caracteres especiales.
	 *
	 * @param as_campo the as campo
	 * @param amss_caracteres the amss caracteres
	 * @return true, if successful
	 */
	private boolean contieneCaracteresEspeciales(String as_campo, Map<String, String> amss_caracteres)
	{
		boolean lb_return;

		lb_return = false;

		if(StringUtils.isValidString(as_campo))
		{
			StringBuilder lsb_pattern;
			lsb_pattern = new StringBuilder("[^0-9A-Za-záéíóúÁÉÍÓÚñÑ ");

			if(CollectionUtils.isValidCollection(amss_caracteres))
			{
				for(Map.Entry<String, String> lme_entry : amss_caracteres.entrySet())
				{
					if(lme_entry != null)
					{
						String ls_caracter;
						ls_caracter = lme_entry.getKey();

						if(StringUtils.isValidString(ls_caracter))
							lsb_pattern.append(ls_caracter);
					}
				}
			}

			lsb_pattern.append("]");

			lb_return = Pattern.compile(lsb_pattern.toString()).matcher(as_campo).find();
		}

		return lb_return;
	}

	/**
	 * Obtener el contenido de un archivo en el servidor PFD como un objeto de clase Archivo.
	 *
	 * @param acs_canal Objeto de conexión al servidor FTP
	 * @param as_rutaArchivo Ruta del archivo del cual se debe obtener el contenido
	 * @return Objeto Archivo con el contenido del archivo en el servidor FTP
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized ArchivoConciliacion descargarArchivoFTP(ChannelSftp acs_canal, String as_rutaArchivo)
	    throws B2BException
	{
		ArchivoConciliacion lac_archivo;

		lac_archivo = null;

		try
		{
			byte[]             lba_archivo;
			Collection<String> lcs_lineas;

			lba_archivo     = FTPUtils.descargarArchivoFTP(acs_canal, as_rutaArchivo);
			lcs_lineas      = getLinesFromByteArray(lba_archivo);

			if(lcs_lineas != null)
			{
				lac_archivo = new ArchivoConciliacion();

				lac_archivo.setArchivo(lba_archivo);
				lac_archivo.setLineas(lcs_lineas);
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("descargarArchivoFTP", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lac_archivo;
	}

	/**
	 * Retorna el valor del objeto Collection de EntidadRecaudadoraCuenta.
	 *
	 * @param as_idEntidadRecaudadora correspondiente al valor del tipo de objeto String
	 * @param is_IdUser correspondiente al valor del tipo de objeto String
	 * @param ldm_manager the ldm manager
	 * @return devuelve el valor de Collection de EntidadRecaudadoraCuenta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EntidadRecaudadoraCuenta
	 */
	private synchronized Collection<EntidadRecaudadoraCuenta> findEntidadRecaudadoraCuentaByUser(
	    String     as_idEntidadRecaudadora, String is_IdUser, DAOManager ldm_manager
	)
	    throws B2BException
	{
		Collection<EntidadRecaudadoraCuenta> lerc_datos;

		lerc_datos = null;

		try
		{
			lerc_datos = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager)
					                   .findAllByBancoAndUser(as_idEntidadRecaudadora, is_IdUser);
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findEntidadRecaudadoraCuentaByEntidadAndUser", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lerc_datos;
	}

	/**
	 * Generar archivo inconsistencia.
	 *
	 * @param as_incNombreArchivo the as inc nombre archivo
	 * @param as_rutaMU the as ruta MU
	 * @param as_idArchivo the as id archivo
	 * @param acid_DAO the acid DAO
	 * @param aci_param the aci param
	 * @param acs_canal the acs canal
	 * @param aerc_cuenta the aerc cuenta
	 * @param aerc_entidad the aerc entidad
	 * @param ad_actual the ad actual
	 * @param acd_DAO the lcd DAO
	 * @throws B2BException the b 2 B exception
	 */
	private synchronized void generarArchivoInconsistencia(
	    String as_incNombreArchivo, String as_rutaMU, ConInconsistencia aci_data, ChannelSftp acs_canal,
	    EntidadRecaudadoraCuenta aerc_cuenta, EntidadRecaudadoraConciliacion aerc_entidad, Date ad_actual,
	    ConstantesDAO acd_DAO, ParameterBusiness apb_business, ConArchivo aca_archivo, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			Map<String, String> lmss_catalogo;

			lmss_catalogo = new HashMap<String, String>(1);

			try
			{
				acs_canal.cd(as_rutaMU);
			}
			catch(Exception le_e)
			{
				FTPUtils.crearDirectorioFTP(acs_canal, as_rutaMU, ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO);
			}

			lmss_catalogo.put(as_incNombreArchivo, obtenerConstanteCaracter(acd_DAO, as_incNombreArchivo));

			aci_data = apb_business.fillTagsObservaciones(aci_data, adm_manager);

			if(aci_data != null)
				cargarArchivoFTP(
				    acs_canal, lmss_catalogo, aci_data.getMensaje(), ad_actual, as_incNombreArchivo, as_rutaMU,
				    aerc_entidad, aerc_cuenta, aca_archivo
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoInconsistencia", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Generar reporte partidas A pdf.
	 *
	 * @param acs_data de acs data
	 * @param ad_fechaConciliacion de ad fecha conciliacion
	 * @param as_idCuenta de as id cuenta
	 * @param as_idBanco de as id banco
	 * @param adm_manager de adm manager
	 * @param as_nombreReporte de as nombre reporte
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	private synchronized byte[] generarReportePartidasAPdf(
	    Collection<String> acs_data, Date ad_fechaConciliacion, String as_idCuenta, String as_idBanco,
	    DAOManager adm_manager, String as_nombreReporte, String as_userId, String as_remoteIp
	)
	    throws Exception
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acs_data))
			{
				Constantes lc_plantilla;

				lc_plantilla = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(ConstanteCommon.PLANTILLA_REPORTES);

				if(lc_plantilla != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_plantilla.getImagenes();

					if(lba_plantilla != null)
					{
						String ls_plantilla;

						ls_plantilla = new String(lba_plantilla);

						if(StringUtils.isValidString(ls_plantilla))
						{
							String ls_tag;

							ls_tag = "<TAG_TIPO_REPORTE>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, as_nombreReporte);

							ls_tag = "<TAG_BANCO>";

							if(ls_plantilla.contains(ls_tag))
							{
								EntidadRecaudadoraConciliacion lerc_banco;

								lerc_banco = DaoCreator.getEntidadRecaudadoraConciliacionDAO(adm_manager)
										                   .findById(as_idBanco);

								if(lerc_banco != null)
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, lerc_banco.getNombreEntidadRecaudadora()
										);
							}

							ls_tag = "<TAG_CUENTA>";

							if(ls_plantilla.contains(ls_tag))
							{
								EntidadRecaudadoraCuenta lerc_cuenta;

								lerc_cuenta = DaoCreator.getEntidadRecaudadoraCuentaDAO(adm_manager)
										                    .findById(as_idCuenta);

								if(lerc_cuenta != null)
									ls_plantilla = ls_plantilla.replace(ls_tag, lerc_cuenta.getNumeroCuenta());
							}

							ls_tag = "<TAG_FECHA_CONFRONTACION>";

							if(ls_plantilla.contains(ls_tag))
							{
								SimpleDateFormat formatter;

								formatter = new SimpleDateFormat("dd/MM/yyyy");

								if(ad_fechaConciliacion != null)
								{
									String ls_fecha;

									ls_fecha         = StringUtils.getStringNotNull(
										    formatter.format(ad_fechaConciliacion)
										);
									ls_plantilla     = ls_plantilla.replace(ls_tag, ls_fecha);
								}
							}

							{
								ls_tag = "<TAG_TABLA>";

								if(ls_plantilla.contains(ls_tag))
								{
									int li_finalTag;

									li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

									if(li_finalTag > 0)
									{
										String ls_textoMitadSuperior;
										String ls_textoMitadInferior;
										String ls_tagNew;

										ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
										ls_textoMitadInferior     = ls_plantilla.substring(
											    li_finalTag + ls_tag.length()
											);

										ls_tagNew     = "{\\*\\bkmkstart TAG_TABLA}{\\*\\bkmkend TAG_TABLA} \\line "
											+ ls_tag;

										ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
											+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
									}
								}

								ls_plantilla     = limpiarTags(ls_plantilla);
								ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

								{
									ByteArrayInputStream      lbais_docInStream;
									ByteArrayOutputStream     lbaos_docOutStream;
									com.aspose.words.Document ld_doc;
									DocumentBuilder           ldb_builder;
									int                       li_tamanoLetra;
									long                      ll_porcentajetablas;

									lbais_docInStream       = new ByteArrayInputStream(ls_plantilla.getBytes());
									lbaos_docOutStream      = new ByteArrayOutputStream();
									ld_doc                  = new com.aspose.words.Document(lbais_docInStream);
									ldb_builder             = new DocumentBuilder(ld_doc);
									ll_porcentajetablas     = (long)10.0;
									li_tamanoLetra          = 10;

									ldb_builder.moveToBookmark("TAG_TABLA");

									Table lt_table;

									ldb_builder.writeln(ControlChar.LINE_BREAK);

									lt_table = ldb_builder.startTable();

									for(String ls_linea : acs_data)
									{
										String[] lsa_contenido;

										lsa_contenido = ls_linea.split(IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);

										if(CollectionUtils.isValidCollection(lsa_contenido))
										{
											for(String ls_iterador : lsa_contenido)
											{
												ldb_builder.insertCell();
												ldb_builder.getParagraphFormat().setAlignment(
												    ParagraphAlignment.CENTER
												);
												ldb_builder.setBold(true);
												ldb_builder.getFont().setName("Courier New");
												ldb_builder.getFont().setUnderline(0);
												ldb_builder.getFont().setSize(li_tamanoLetra);
												ldb_builder.getCellFormat()
													           .setPreferredWidth(
													    PreferredWidth.fromPercent(ll_porcentajetablas)
													);
												ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
												ldb_builder.getCellFormat()
													           .setVerticalAlignment(CellVerticalAlignment.CENTER);
												ldb_builder.write(ls_iterador);
											}

											ldb_builder.endRow();
										}
									}

									lt_table.setAlignment(TableAlignment.CENTER);
									ldb_builder.endTable();
									ldb_builder.writeln(ControlChar.LINE_BREAK);
									ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

									lba_return = new PDFGenerator().convertirRTFToPDF(
										    lbaos_docOutStream.toByteArray(), ldm_manager
										);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.rollback();

			clh_LOGGER.error("generarReportePartidasAPdf", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Obtener cabecera CRPS.
	 *
	 * @param as_linea Linea con contenido de un registro CRPS
	 * @param as_idArchivo Id del archivo procesado
	 * @param ai_registro Número de registro en el arhivo
	 * @param as_separador Caracter de separación entre campos
	 * @param as_formatoFecha Formato usado en el arhivo CRPS
	 * @param as_userId usuario que realiza el proceso de cargue de archivos
	 * @param as_remoteIp IP desde donde se lanza la ejecucion del método
	 * @return el valor de CRPS cabecera
	 */
	private CRPSCabecera obtenerCabeceraCRPS(
	    String as_linea, String as_idArchivo, int ai_registro, String as_separador, String as_formatoFecha,
	    String as_userId, String as_remoteIp
	)
	{
		CRPSCabecera lcrpsc_crpsc;

		lcrpsc_crpsc = null;

		if(StringUtils.isValidString(as_linea))
		{
			String[] lsa_campos;

			lsa_campos = as_linea.split(as_separador);

			if(lsa_campos != null)
			{
				lcrpsc_crpsc = new CRPSCabecera();

				lcrpsc_crpsc.setIdArchivo(as_idArchivo);
				lcrpsc_crpsc.setIdUsuarioCreacion(as_userId);
				lcrpsc_crpsc.setIpCreacion(as_remoteIp);
				lcrpsc_crpsc.setRegistro(ai_registro);

				for(int li_campo = 0, li_campos = lsa_campos.length; li_campo < li_campos; li_campo++)
				{
					String ls_campo;

					ls_campo = lsa_campos[li_campo];

					switch(li_campo)
					{
						case 0:
							lcrpsc_crpsc.setIdRegistroPago(ls_campo);

							break;

						case 1:
							lcrpsc_crpsc.setIdSolicitud(ls_campo);

							break;

						case 2:
							lcrpsc_crpsc.setFechaSolicitud(obtenerValorFecha(ls_campo, as_formatoFecha));

							break;

						case 3:
							lcrpsc_crpsc.setNir(ls_campo);

							break;

						case 4:
							lcrpsc_crpsc.setIdProceso(ls_campo);

							break;

						case 5:
							lcrpsc_crpsc.setNombreProceso(ls_campo);

							break;

						case 6:
							lcrpsc_crpsc.setIdSubproceso(ls_campo);

							break;

						case 7:
							lcrpsc_crpsc.setNombreSubproceso(ls_campo);

							break;

						case 8:
							lcrpsc_crpsc.setDigitalizada(ls_campo);

							break;

						case 9:
							lcrpsc_crpsc.setIdUsuarioCreacionSolicitud(ls_campo);

							break;

						case 10:
							lcrpsc_crpsc.setIdDocumentoTipo(ls_campo);

							break;

						case 11:
							lcrpsc_crpsc.setNumeroDocumento(ls_campo);

							break;

						case 12:
							lcrpsc_crpsc.setIdTipoPersona(ls_campo);

							break;

						case 13:
							lcrpsc_crpsc.setPrimerNombre(ls_campo);

							break;

						case 14:
							lcrpsc_crpsc.setSegundoNombre(ls_campo);

							break;

						case 15:
							lcrpsc_crpsc.setPrimerApellido(ls_campo);

							break;

						case 16:
							lcrpsc_crpsc.setSegundoApellido(ls_campo);

							break;

						case 17:
							lcrpsc_crpsc.setRazonSocial(ls_campo);

							break;

						case 18:
							lcrpsc_crpsc.setIdLiquidacion(ls_campo);

							break;

						case 19:
							lcrpsc_crpsc.setConsecutivoLiquidacion(obtenerValorEntero(ls_campo));

							break;

						case 20:
							lcrpsc_crpsc.setFechaLiquidacion(obtenerValorFecha(ls_campo, as_formatoFecha));

							break;

						case 21:

							Double ld_valorDouble21;
							ld_valorDouble21 = obtenerValorDoble(ls_campo);

							if(ld_valorDouble21 == null)
								ld_valorDouble21 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsc.setValorLiquidacion(ld_valorDouble21);

							break;

						case 22:

							Double ld_valorDouble22;
							ld_valorDouble22 = obtenerValorDoble(ls_campo);

							if(ld_valorDouble22 == null)
								ld_valorDouble22 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsc.setValorImpuestoLiquidacion(ld_valorDouble22);

							break;

						case 23:

							Double ld_valorDouble23;
							ld_valorDouble23 = obtenerValorDoble(ls_campo);

							if(ld_valorDouble23 == null)
								ld_valorDouble23 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsc.setValorTotalLiquidacion(ld_valorDouble23);

							break;

						case 24:

							Double ld_valorDouble24;
							ld_valorDouble24 = obtenerValorDoble(ls_campo);

							if(ld_valorDouble24 == null)
								ld_valorDouble24 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsc.setValorConservacionDocumental(ld_valorDouble24);

							break;

						case 25:
							lcrpsc_crpsc.setNumeroReferencia(ls_campo);

							break;

						case 26:
							lcrpsc_crpsc.setIdTipoCanal(ls_campo);

							break;

						case 27:
							lcrpsc_crpsc.setIdEntidadRecaudo(ls_campo);

							break;

						case 28:
							lcrpsc_crpsc.setIdSucursalRecaudo(ls_campo);

							break;

						case 29:
							lcrpsc_crpsc.setNumeroCuentaRecaudo(ls_campo);

							break;

						case 30:
							lcrpsc_crpsc.setIdTipoRecaudo(ls_campo);

							break;

						case 31:
							lcrpsc_crpsc.setFechaBancaria(obtenerValorFecha(ls_campo, as_formatoFecha));

							break;

						case 32:

							Double ld_valorDouble32;
							ld_valorDouble32 = obtenerValorDoble(ls_campo);

							if(ld_valorDouble32 == null)
								ld_valorDouble32 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsc.setMontoTransaccion(ld_valorDouble32);

							break;

						case 33:
							lcrpsc_crpsc.setCodigoTransaccionRecaudador(ls_campo);

							break;

						case 34:
							lcrpsc_crpsc.setFechaTransaccion(obtenerValorFecha(ls_campo, as_formatoFecha));

							break;

						case 35:
							lcrpsc_crpsc.setNumeroReciboCaja(ls_campo);

							break;

						case 36:
							lcrpsc_crpsc.setFechaGeneracionRecibo(obtenerValorFecha(ls_campo, as_formatoFecha));

							break;

						case 37:
							lcrpsc_crpsc.setEstadoPago(ls_campo);

							break;

						default:
							break;
					}
				}
			}
		}

		return lcrpsc_crpsc;
	}

	/**
	 * Obtener detalle CRPS.
	 *
	 * @param as_linea the as linea
	 * @param as_idArchivo the as id archivo
	 * @param as_idArchivoCabecera de as id archivo cabecera
	 * @param ai_registro the ai registro
	 * @param as_separador the as separador
	 * @param as_formatoFecha the as formato fecha
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @return the CRPS detalle
	 */
	private CRPSDetalle obtenerDetalleCRPS(
	    String as_linea, String as_idArchivo, String as_idArchivoCabecera, int ai_registro, String as_separador,
	    String as_formatoFecha, String as_userId, String as_remoteIp
	)
	{
		CRPSDetalle lcrpsc_crpsd;

		lcrpsc_crpsd = null;

		if(StringUtils.isValidString(as_linea))
		{
			String[] lsa_campos;

			lsa_campos = as_linea.split(as_separador);

			if(lsa_campos != null)
			{
				lcrpsc_crpsd = new CRPSDetalle();

				lcrpsc_crpsd.setIdArchivo(as_idArchivo);
				lcrpsc_crpsd.setIdArchivoCabecera(as_idArchivoCabecera);
				lcrpsc_crpsd.setIdUsuarioCreacion(as_userId);
				lcrpsc_crpsd.setIpCreacion(as_remoteIp);
				lcrpsc_crpsd.setRegistro(ai_registro);

				for(int li_campo = 0, li_campos = lsa_campos.length; li_campo < li_campos; li_campo++)
				{
					String ls_campo;

					ls_campo = lsa_campos[li_campo];

					switch(li_campo)
					{
						case 0:
							lcrpsc_crpsd.setIdRegistroPago(ls_campo);

							break;

						case 1:
							lcrpsc_crpsd.setIdLiquidacion(ls_campo);

							break;

						case 2:
							lcrpsc_crpsd.setConsecutivoLiquidacion(NumericUtils.getInteger(ls_campo));

							break;

						case 3:
							lcrpsc_crpsd.setIdItem(NumericUtils.getInteger(ls_campo));

							break;

						case 4:
							lcrpsc_crpsd.setIdActo(ls_campo);

							break;

						case 5:
							lcrpsc_crpsd.setIdCirculo(ls_campo);

							break;

						case 6:
							lcrpsc_crpsd.setIdTipoActo(ls_campo);

							break;

						case 7:
							lcrpsc_crpsd.setVersion(NumericUtils.getInteger(ls_campo));

							break;

						case 8:
							lcrpsc_crpsd.setIdProceso(ls_campo);

							break;

						case 9:
							lcrpsc_crpsd.setNombreProceso(ls_campo);

							break;

						case 10:
							lcrpsc_crpsd.setIdSubproceso(ls_campo);

							break;

						case 11:
							lcrpsc_crpsd.setNombreSubproceso(ls_campo);

							break;

						case 12:
							lcrpsc_crpsd.setCantidad(NumericUtils.getInteger(ls_campo));

							break;

						case 13:

							Double ld_valor13;
							ld_valor13 = obtenerValorDoble(ls_campo);

							if(ld_valor13 == null)
								ld_valor13 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsd.setValorLiquidacion(ld_valor13);

							break;

						case 14:

							Double ld_valor14;
							ld_valor14 = obtenerValorDoble(ls_campo);

							if(ld_valor14 == null)
								ld_valor14 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsd.setValorImpuestoLiquidacion(ld_valor14);

							break;

						case 15:

							Double ld_valor15;
							ld_valor15 = obtenerValorDoble(ls_campo);

							if(ld_valor15 == null)
								ld_valor15 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsd.setValorTotalLiquidacion(ld_valor15);

							break;

						case 16:

							Double ld_valor16;
							ld_valor16 = obtenerValorDoble(ls_campo);

							if(ld_valor16 == null)
								ld_valor16 = obtenerValorDoble(obtenerStringDecimal(ls_campo));

							lcrpsc_crpsd.setValorConservacionDocumental(ld_valor16);

							break;

						case 17:
							lcrpsc_crpsd.setIdTurno(ls_campo);

							break;

						case 18:
							lcrpsc_crpsd.setIdCirculoTurno(ls_campo);

							break;

						case 19:
							lcrpsc_crpsd.setIdMatriculaCertificado(ls_campo);

							break;

						case 20:
							lcrpsc_crpsd.setIdMatriculaSegregacion(ls_campo);

							break;

						default:
							break;
					}
				}
			}
		}

		return lcrpsc_crpsd;
	}

	/**
	 * Obtener valor catalogo.
	 *
	 * @param amss_catalogo the amss catalogo
	 * @param as_llaveCatalogo the as llave catalogo
	 * @return the string
	 * @throws B2BException the b 2 B exception
	 */
	private String obtenerValorCatalogo(Map<String, String> amss_catalogo, String as_llaveCatalogo)
	    throws B2BException
	{
		String ls_valorCatalogo;

		ls_valorCatalogo = (amss_catalogo != null) ? amss_catalogo.get(as_llaveCatalogo) : null;

		if(!StringUtils.isValidString(ls_valorCatalogo))
		{
			Object[] loa_messageArgs;

			loa_messageArgs        = new String[1];
			loa_messageArgs[0]     = as_llaveCatalogo;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
		}

		return ls_valorCatalogo;
	}

	/**
	 * Obtener el valor Long de un cadena de caracteres.
	 *
	 * @param as_valor Cadena de caracteres con valor Long
	 * @return Valor Long de la cadena de caracteres o null en caso de que no se pueda hacer la conversion
	 */
	private Long obtenerValorEnteroLargo(String as_valor)
	{
		Long ll_valor;

		try
		{
			ll_valor = Long.valueOf(as_valor);
		}
		catch(NumberFormatException lnfe_e)
		{
			ll_valor = null;
		}

		return ll_valor;
	}

	/**
	 * Obtener el valor Date de un cadena de caracteres.
	 *
	 * @param as_valor Cadena de caracteres con valor Date
	 * @param as_formato Formato usado para la conversion a tipo Date
	 * @return Valor Date de la cadena de caracteres o null en caso de que no se pueda hacer la conversion
	 */
	private Date obtenerValorFecha(String as_valor, String as_formato)
	{
		return DateUtils.getDate(as_valor, as_formato);
	}
}
