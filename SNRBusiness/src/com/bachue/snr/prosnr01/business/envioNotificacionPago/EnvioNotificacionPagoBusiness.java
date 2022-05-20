package com.bachue.snr.prosnr01.business.envioNotificacionPago;

import co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion;

import co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasProxy;
import co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.rmi.RemoteException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene los métodos de negocio para el envió de liquidaciones generadas al componente de Notificacion de Pagos.
 *
 * @author Julian Vaca.
 *
 */
public class EnvioNotificacionPagoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioNotificacionPagoBusiness.class);

	/**
	 * Método encargado de consultar las liquidaciones pendientes de envio al componente de notificación de pagos.
	 *
	 * @throws B2BException
	 */
	public synchronized void enviarNotificacionPago(String as_remoteIp)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_liquidaciones;
		DAOManager              ldm_manager;

		lcl_liquidaciones     = null;
		ldm_manager           = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = lcd_constantesDAO.findById(ConstanteCommon.JOB_ENVIO_NOTIFICACION_PAGO_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_limiteIntentos;

				ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_ENVIO_NOTIFICACION_LIMITE_INTENTOS
					);

				lcl_liquidaciones = DaoCreator.getAccLiquidacionDAO(ldm_manager).findSettlementToSend(
					    ll_limiteIntentos
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("envioNotificacionPago", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcl_liquidaciones))
			enviarNotificacionPago(lcl_liquidaciones, as_remoteIp);
	}

	/**
	 *  Método encargado de validar las liquidaciones encontradas para enviarlas al componente de notificacion de pagos.
	 *
	 * @param acl_parametros Colección de datos de tipo Liquidacion que contiene las liquidaciones que se enviaran al componente de notificacion de pagos.
	 *
	 * @throws B2BException
	 */
	public synchronized void enviarNotificacionPago(Collection<Liquidacion> acl_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_NOTIFICACION_PAGO_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManager();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("enviarNotificacionPago", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManager();

			ldm_processing.setAutoCommit(true);

			try
			{
				ConstantesDAO lcd_constant;
				Constantes    lce_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lce_constant     = lcd_constant.findById(ls_constant);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = StringUtils.getString(ls_constant);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("enviarNotificacionPago", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing && StringUtils.isValidString(ls_userId))
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);
					ls_endpoint        = lcd_constantes.findString(
						    ConstanteCommon.JOB_ENVIO_NOTIFICACION_PAGO_ENDPOINT
						);

					if(CollectionUtils.isValidCollection(acl_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;
						LiquidacionDAO     lld_liquidacionDAO;

						lbpd_bitacora          = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						lld_liquidacionDAO     = DaoCreator.getAccLiquidacionDAO(ldm_bitacora);

						for(Liquidacion ll_iterador : acl_parametros)
						{
							if(ll_iterador != null)
							{
								try
								{
									enviarNotificacionPago(
									    ll_iterador, lbpd_bitacora, lld_liquidacionDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("enviarNotificacionPago", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarNotificacionPago", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_constantes.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarNotificacionPago", lb2be_e);

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
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("enviarNotificacionPago", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}
		}
	}

	/**
	 * Método encargado de ejecutar web service para el envio de la liquidacion al componente de notificacion de pagos.
	 *
	 * @param al_parametros  Objeto de tipo  Liquidacion que contiene datos de la liquidacion sin notificar.
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió  al componente de notificacion de pagos.
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la invocacion al metodo
	 * @param as_ipRemota Objeto de tipo String que contiene la ip desde la cual que realiza la invocacion al metodo
	 * @throws B2BException
	 */
	public synchronized void enviarNotificacionPago(
	    Liquidacion al_parametros, BitacoraProcesoDAO abpd_DAO, LiquidacionDAO ald_bitacoraIntentosDAO,
	    String as_endpoint, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		if(al_parametros != null)
		{
			DAOManager ldm_manager;
			String     ls_mensaje;
			String     ls_idSolicitud;
			long       ll_intentoActual;

			ldm_manager          = DaoManagerFactory.getDAOManager();
			ls_mensaje           = addMessage(MessagesKeys.PROCESO_EXITOSO);
			ls_idSolicitud       = al_parametros.getIdSolicitud();
			ll_intentoActual     = al_parametros.getIntentosFallidosEjecucionAutomatica();

			ll_intentoActual++;

			try
			{
				String         ls_numeroReferencia;
				LiquidacionDAO lld_DAO;

				ls_numeroReferencia     = al_parametros.getNumeroReferencia();
				lld_DAO                 = DaoCreator.getAccLiquidacionDAO(ldm_manager);

				if(!StringUtils.isValidString(ls_numeroReferencia))
					throw new B2BException(ErrorKeys.ERROR_NUMERO_REFERENCIA);

				if(!StringUtils.isValidString(ls_idSolicitud))
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_numeroReferencia;

					throw new B2BException(ErrorKeys.ERROR_SOLICITUD_REFERENCIA, loa_arg);
				}

				{
					String    ls_NIR;
					Solicitud lso_solicitud;

					ls_NIR            = null;
					lso_solicitud     = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

					if(lso_solicitud == null)
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = ls_idSolicitud;

						throw new B2BException(ErrorKeys.ERROR_SOLICITUD_ID_SOLICITUD, loa_arg);
					}

					{
						BigDecimal lbd_valorTotal;
						Calendar   lc_fechaVencimiento;
						Calendar   lc_fechaLiquidacion;
						String     ls_canalOrigenServicio;

						ls_NIR                     = lso_solicitud.getNir();
						lbd_valorTotal             = al_parametros.getValorTotal();
						lc_fechaLiquidacion        = null;
						lc_fechaVencimiento        = null;
						ls_canalOrigenServicio     = null;

						if(!StringUtils.isValidString(ls_NIR))
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = ls_idSolicitud;

							throw new B2BException(ErrorKeys.ERROR_NIR_ID_SOLICITUD, loa_arg);
						}

						{
							Date ld_fechaLiquidacion;

							ld_fechaLiquidacion = al_parametros.getFechaLiquidacion();

							if(ld_fechaLiquidacion != null)
								lc_fechaLiquidacion = obtenerCalendarDesdeDate(ld_fechaLiquidacion);
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_NIR;

								throw new B2BException(ErrorKeys.FECHA_LIQUIDACION_NO_VALIDA, loa_arg);
							}
						}

						{
							Date ld_fechaVecimiento;

							ld_fechaVecimiento = al_parametros.getFechaVencimiento();

							if(ld_fechaVecimiento != null)
								lc_fechaVencimiento = obtenerCalendarDesdeDate(ld_fechaVecimiento);
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_NIR;

								throw new B2BException(ErrorKeys.FECHA_VENCIMIENTO_NO_VALIDA, loa_arg);
							}
						}

						{
							String ls_oripPrincipal;

							ls_oripPrincipal = DaoCreator.getUtilDAO(ldm_manager).consultarOripPrincipal(
								    as_userId
								);

							if(StringUtils.isValidString(ls_oripPrincipal))
								ls_canalOrigenServicio = "ORIP" + ls_oripPrincipal;
						}

						if(!NumericUtils.isValidBigDecimal(lbd_valorTotal))
							throw new B2BException(ErrorKeys.ERROR_VALOR_LIQUIDACION);

						if(!StringUtils.isValidString(as_endpoint))
							throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);

						{
							ConstantesDAO lcd_constantesDAO;
							boolean       lb_intentarEnvio;
							long          ll_tiempoEspera;
							long          ll_limiteIntentos;

							lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
							lb_intentarEnvio      = true;
							ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
								    ConstanteCommon.JOB_ENVIO_NOTIFICACION_TIEMPO_ESPERA_INTENTO_ENVIO
								);
							ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
								    ConstanteCommon.JOB_ENVIO_NOTIFICACION_LIMITE_INTENTOS
								);

							while(lb_intentarEnvio && (ll_intentoActual <= ll_limiteIntentos))
							{
								try
								{
									SBB_CI_OperacionesFinancieras_PortType lsnof_interface;
									SBB_CI_OperacionesFinancierasProxy     lsnofp_proxy;

									lsnofp_proxy        = new SBB_CI_OperacionesFinancierasProxy(as_endpoint);
									lsnof_interface     = lsnofp_proxy.getSBB_CI_OperacionesFinancieras_PortType();

									if(lsnof_interface != null)
									{
										TipoSalidaRegistrarLiquidacion  ltsrl_response;
										TipoEntradaRegistrarLiquidacion lterl_registrarLiquidacion;

										al_parametros.setIdUsuarioModificacion(as_userId);
										al_parametros.setIpModificacion(as_ipRemota);
										al_parametros.setEnviadoNotificacionPago(EstadoCommon.SI);

										lld_DAO.actualizarEnvioNotificacionPago(al_parametros);

										lterl_registrarLiquidacion     = new TipoEntradaRegistrarLiquidacion(
											    ls_numeroReferencia, ls_NIR, lbd_valorTotal, lc_fechaLiquidacion,
											    lc_fechaVencimiento, ls_canalOrigenServicio
											);

										ltsrl_response = lsnof_interface.registrarLiquidacion(
											    lterl_registrarLiquidacion
											);

										if(ltsrl_response != null)
										{
											BigInteger lbi_codigoMensaje;

											lbi_codigoMensaje = ltsrl_response.getCodigoMensaje();

											if(
											    (lbi_codigoMensaje != null)
												    && (lbi_codigoMensaje.longValue() != CodigoMensajeCommon.CODIGO_200)
											)
											{
												String ls_descripcion;

												ls_descripcion = ltsrl_response.getDescripcionMensaje();

												if(
												    StringUtils.isValidString(ls_descripcion)
													    && !ls_descripcion.equalsIgnoreCase(
													        IdentificadoresCommon.ERROR_LIQUIDACION_NOTIFICADA
													    )
												)
													throw new B2BException(ltsrl_response.getDescripcionMensaje());

												lb_intentarEnvio = false;
											}
										}
										else
										{
											Object[] loa_arg;

											loa_arg        = new String[1];
											loa_arg[0]     = "registrarLiquidacion";

											throw new B2BException(ErrorKeys.ERROR_SIN_RESPUESTA_OPERACION, loa_arg);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_INTERFAZ_SERVICIOS);
								}
								catch(B2BException lb2be_e)
								{
									if((ll_intentoActual + 1) >= ll_limiteIntentos)
										throw lb2be_e;

									ll_intentoActual++;

									{
										Object lo_monitor;

										lo_monitor = new Object();

										synchronized(lo_monitor)
										{
											lo_monitor.wait(ll_tiempoEspera);
										}
									}
								}
								catch(RemoteException lre_e)
								{
									if((ll_intentoActual + 1) >= ll_limiteIntentos)
										throw new B2BException(lre_e.getLocalizedMessage());

									ll_intentoActual++;

									{
										Object lo_monitor;

										lo_monitor = new Object();

										synchronized(lo_monitor)
										{
											lo_monitor.wait(ll_tiempoEspera);
										}
									}
								}
							}
						}
					}
				}

				al_parametros.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_NOTIFICACION_PAGO, ls_mensaje, as_userId, as_endpoint,
				    ls_idSolicitud
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_NOTIFICACION_PAGO, ls_mensaje, as_userId, as_endpoint,
				    ls_idSolicitud
				);

				Thread.currentThread().interrupt();

				throw new B2BException(ls_mensaje);
			}
			finally
			{
				ldm_manager.close();

				al_parametros.setIntentosFallidosEjecucionAutomatica(ll_intentoActual);
				al_parametros.setRespuestaEjecucionAutomatica(ls_mensaje);
				al_parametros.setIpModificacion(as_ipRemota);
				al_parametros.setIdUsuarioModificacion(as_userId);

				ald_bitacoraIntentosDAO.updateIntentoEnvios(al_parametros);
			}
		}
	}
}
