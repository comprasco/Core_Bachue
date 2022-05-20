package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastralesListaPrediosPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionDesdeCatastros;
import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionDesdeCatastrosDetalle;

import java.math.BigInteger;

import java.util.Calendar;
import java.util.Map;


/**
 * Clase para el manejo de reglas de negocio del servicio salvedades.
 *
 * @author Carlos Calderon
 */
public class SalvedadesBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CatastroBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite generar una salvedad en el folio de matrícula inmobiliaria por cambio de nomenclatura, soportada por una
	 * resolución emitida por el actor que genera el cambio de nomenclatura (Catastros y/o Secretarías de Planeación).
	 *
	 * @param atercs_param de TipoEntradaregistrarCambioSalvedades
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de tipo salidaregistrar cambio salvedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaregistrarCambioSalvedades registraCambioSalvedades(
	    TipoEntradaregistrarCambioSalvedades atercs_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atercs_param != null)
			{
				byte[] lb_documentoSoporte;

				lb_documentoSoporte = atercs_param.getDocumentoSoporte();

				if((lb_documentoSoporte != null) && (lb_documentoSoporte.length > NumericUtils.DEFAULT_INT_VALUE))
				{
					/* TODO Cambiar validación por: Si el documento de soporte es PDF*/
/*
                    if(lb_documentoSoporte != null)
*/
					{
						TipoEntradaregistrarCambioSalvedadesListaPrediosPredio[] ltercslpp_predio;

						ltercslpp_predio = atercs_param.getListaPredios();

						if(CollectionUtils.isValidCollection(ltercslpp_predio))
						{
							boolean lb_salvedadesTCatastralesF;
							String  ls_idSalvedad;

							lb_salvedadesTCatastralesF     = true;
							ls_idSalvedad                  = salvarServicioActualizacionDesdeCatastros(
								    lb_documentoSoporte, lb_salvedadesTCatastralesF, ldm_manager, as_userId, as_remoteIp
								);

							if(StringUtils.isValidString(ls_idSalvedad))
							{
								int li_idSalvedadDetalle;

								li_idSalvedadDetalle = DaoCreator.getServicioActualizacionDesdeCatastrosDetalleDAO(
									    ldm_manager
									).findMaxIdSalvedadDetalleByIdSalvedad(ls_idSalvedad);

								for(TipoEntradaregistrarCambioSalvedadesListaPrediosPredio ltercslpp_temp : ltercslpp_predio)
								{
									if(ltercslpp_temp != null)
									{
										li_idSalvedadDetalle++;

										salvarServicioActualizacionDesdeCatastrosDetalle(
										    new Predio(ltercslpp_temp), ls_idSalvedad, li_idSalvedadDetalle,
										    lb_salvedadesTCatastralesF, ldm_manager, as_userId, as_remoteIp
										);
									}
								}
							}
						}
					}

					/* TODO Cambiar validación por: Si el documento de soporte es PDF*/
/*
                    else
                        throw new B2BException(addErrorCTO(ErrorKeys.ERROR_DOCUMENTO_SOPORTE_PDF));
*/
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_DOCUMENTO_SOPORTE_SALVEDAD));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioSalvedades", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioSalvedades", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaregistrarCambioSalvedades ltsrcs_return;

			ltsrcs_return = new TipoSalidaregistrarCambioSalvedades(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsrcs_return;
		}
	}

	/**
	 * Registra identificadores catastrales.
	 *
	 * @param ateric_param de TipoEntradaRegistrarIdentificadoresCatastrales
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de tipo salida registrar identificadores catastrales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarIdentificadoresCatastrales registraIdentificadoresCatastrales(
	    TipoEntradaRegistrarIdentificadoresCatastrales ateric_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;
		String     ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(ateric_param != null)
			{
				byte[] lb_documentoSoporte;

				lb_documentoSoporte = ateric_param.getDocumentoSoporte();

				if((lb_documentoSoporte != null) && (lb_documentoSoporte.length > NumericUtils.DEFAULT_INT_VALUE))
				{
					/* TODO Cambiar validación por: Si el documento de soporte es PDF*/
/*
                    if(lb_documentoSoporte != null)
*/
					{
						TipoEntradaRegistrarIdentificadoresCatastralesListaPrediosPredio[] ltericlpp_predio;

						ltericlpp_predio = ateric_param.getListaPredios();

						if(CollectionUtils.isValidCollection(ltericlpp_predio))
						{
							boolean lb_salvedadesTCatastralesF;
							String  ls_idSalvedad;

							lb_salvedadesTCatastralesF     = false;
							ls_idSalvedad                  = salvarServicioActualizacionDesdeCatastros(
								    lb_documentoSoporte, lb_salvedadesTCatastralesF, ldm_manager, as_userId, as_remoteIp
								);

							if(StringUtils.isValidString(ls_idSalvedad))
							{
								int li_idSalvedadDetalle;

								li_idSalvedadDetalle = DaoCreator.getServicioActualizacionDesdeCatastrosDetalleDAO(
									    ldm_manager
									).findMaxIdSalvedadDetalleByIdSalvedad(ls_idSalvedad);

								for(TipoEntradaRegistrarIdentificadoresCatastralesListaPrediosPredio ltericlpp_temp : ltericlpp_predio)
								{
									if(ltericlpp_temp != null)
									{
										li_idSalvedadDetalle++;

										salvarServicioActualizacionDesdeCatastrosDetalle(
										    new Predio(ltericlpp_temp), ls_idSalvedad, li_idSalvedadDetalle,
										    lb_salvedadesTCatastralesF, ldm_manager, as_userId, as_remoteIp
										);
									}
								}
							}
						}
					}

					/* TODO Cambiar validación por: Si el documento de soporte es PDF*/
/*
                    else
                        throw new B2BException(addErrorCTO(ErrorKeys.ERROR_DOCUMENTO_SOPORTE_PDF));
*/
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_DOCUMENTO_SOPORTE_SALVEDAD));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioSalvedades", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarCambioSalvedades", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRegistrarIdentificadoresCatastrales ltsric_return;

			ltsric_return = new TipoSalidaRegistrarIdentificadoresCatastrales(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsric_return;
		}
	}

	/**
	 * Salvar servicio actualizacion desde catastros.
	 *
	 * @param ab_documentoSoporte Variable de tipo <code>byte[]</code> que contiene documento soporte.
	 * @param ab_salvedadesTCatastralesF Variable de tipo <code>boolean</code> que para operacion RegistraCambioSalvedades <TRUE> y RegistraIdentificadoresCatastrales <FALSE>
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que maneja la transaccionalidad con la base de datos.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string idSalvedad generado en la transaccion.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized String salvarServicioActualizacionDesdeCatastros(
	    byte[] ab_documentoSoporte, boolean ab_salvedadesTCatastralesF, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		String ls_idSalvedad;

		ls_idSalvedad = null;

		try
		{
			if(ab_documentoSoporte != null)
			{
				ServicioActualizacionDesdeCatastros lsadc_servicioActualizacion;

				lsadc_servicioActualizacion = new ServicioActualizacionDesdeCatastros();

				lsadc_servicioActualizacion.setDocumentoSoporte(ab_documentoSoporte);

				if(ab_salvedadesTCatastralesF)
					lsadc_servicioActualizacion.setTipoSalvedad("NOMENCLATURA");
				else
					lsadc_servicioActualizacion.setTipoSalvedad("IDENTIFICADOR CATASTRAL");

				lsadc_servicioActualizacion.setIdUsuarioCreacion(as_userId);
				lsadc_servicioActualizacion.setIpCreacion(as_remoteIp);

				ls_idSalvedad = DaoCreator.getServicioActualizacionDesdeCatastrosDAO(adm_manager)
						                      .insert(lsadc_servicioActualizacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarServicioActualizacionDesdeCatastros", lb2be_e);
			throw lb2be_e;
		}

		return ls_idSalvedad;
	}

	/**
	 * Salvar servicio actualizacion desde catastros detalle.
	 *
	 * @param ac_datos Variable de tipo <code>Predio</code> que contiene los datos del predio
	 * @param ls_idSalvedad Variable de tipo <code>Predio</code> que contiene id salvedad
	 * @param ai_idSalvedadDetalle correspondiente al valor de  id salvedad detalle
	 * @param ab_salvedadesTCatastralesF Variable de tipo <code>boolean</code> que para operacion RegistraCambioSalvedades <TRUE> y RegistraIdentificadoresCatastrales <FALSE>
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que maneja la transaccionalidad con la base de datos.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void salvarServicioActualizacionDesdeCatastrosDetalle(
	    Predio ac_datos, String ls_idSalvedad, int ai_idSalvedadDetalle, boolean ab_salvedadesTCatastralesF,
	    DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if((ac_datos != null) && StringUtils.isValidString(ls_idSalvedad))
			{
				if(validarTipoIdentificacionPredio(ac_datos) && validarNumeroIdentificacionPredio(ac_datos))
				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(ac_datos, adm_manager);

					if((lpr_predioRegistro != null))
					{
						String ls_direccionPredio;
						String ls_descripcionSalvedad;

						ls_direccionPredio         = ac_datos.getDireccionPredio();
						ls_descripcionSalvedad     = ac_datos.getDescripcionSalvedad();

						if(!StringUtils.isValidString(ls_direccionPredio) && ab_salvedadesTCatastralesF)
							throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NOMENCLATURA_SALVEDAD));

						if(StringUtils.isValidString(ls_descripcionSalvedad))
						{
							Calendar lc_fechaSalvedad;

							lc_fechaSalvedad = ac_datos.getFechaSalvedad();

							if(lc_fechaSalvedad != null)
							{
								ServicioActualizacionDesdeCatastrosDetalle lsadcd_detalle;

								lsadcd_detalle = new ServicioActualizacionDesdeCatastrosDetalle();

								lsadcd_detalle.setIdSalvedad(ls_idSalvedad);
								lsadcd_detalle.setIdSalvedadDetalle(StringUtils.getString(ai_idSalvedadDetalle));
								lsadcd_detalle.setIdCirculo(lpr_predioRegistro.getIdCirculo());
								lsadcd_detalle.setTipoIdentificacionPreido(ac_datos.getTipoIdentificacionPredio());
								lsadcd_detalle.setNumeroIdentificacionCatastral(
								    ac_datos.getNumeroIdentificacionPredio()
								);
								lsadcd_detalle.setIdMatricula(
								    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula())
								);
								lsadcd_detalle.setDireccionPredio(ls_direccionPredio);
								lsadcd_detalle.setComentarioSalvedad(ac_datos.getComentarioSalvedad());
								lsadcd_detalle.setFechaSalvedad(obtenerDateDesdeCalendar(lc_fechaSalvedad));
								lsadcd_detalle.setDescripcionSalvedad(ac_datos.getDescripcionSalvedad());
								lsadcd_detalle.setIdUsuarioCreacion(as_userId);
								lsadcd_detalle.setIpCreacion(as_remoteIp);

								DaoCreator.getServicioActualizacionDesdeCatastrosDetalleDAO(adm_manager)
									          .insert(lsadcd_detalle);
							}
							else
								throw new B2BException(addErrorCTO(ErrorKeys.ERROR_FECHA_NO_VALIDA_CODIGO));
						}
						else
							throw new B2BException(addErrorCTO(ErrorKeys.ERROR_DESCRIPCION_SALVEDAD_INCLUIR));
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarServicioActualizacionDesdeCatastrosDetalle", lb2be_e);
			throw lb2be_e;
		}
	}
}
