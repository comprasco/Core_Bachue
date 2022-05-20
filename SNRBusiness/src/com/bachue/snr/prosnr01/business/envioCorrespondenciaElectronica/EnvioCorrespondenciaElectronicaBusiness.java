package com.bachue.snr.prosnr01.business.envioCorrespondenciaElectronica;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaElectronicaBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
public class EnvioCorrespondenciaElectronicaBusiness extends BaseCorrespondenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaElectronicaBusiness.class);

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar etapas
	 * 220 con estado actividad AUT de Turno Historia.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_WS_INVOKE,
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_LIMITE_INTENTOS,
				    EtapaCommon.ENTREGA_Y_ENVIO_POR_CORREO_ELECTRONICO, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarCorrespondenciaElectronica(lcth_parametros, as_remoteIp);
	}

	/**
	 *  Método de transacciones con la base de datos para procesar casos encontrados
	 * en etapa 220 y estado actividad AUT.
	 *
	 * @param acth_parametros Objeto de tipo Collection<TurnoHistoria> que contiene casos a ser procesados.
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica(
	    Collection<TurnoHistoria> acth_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_BLOQUEO;
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

				clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);

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

				clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);

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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_ENDPOINT
						);

					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;
						TurnoHistoriaDAO   lthd_turnoHistoriaDAO;

						lbpd_bitacora             = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_bitacora);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								try
								{
									enviarCorrespondenciaElectronica(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);
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
			clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);

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

					clh_LOGGER.error("enviarCorrespondenciaElectronica", lb2be_e);

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
	 * Método encargado de ejecutar web service para el envio de correspondencia electronica al componente de comunicaciones y notificaciones.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, TurnoHistoriaDAO athd_DAO, String as_endpoint,
	    String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		if(ath_parametros != null)
		{
			DAOManager ldm_manager;
			String     ls_mensaje;
			long       ll_intentoActual;

			ldm_manager          = DaoManagerFactory.getDAOManager();
			ls_mensaje           = addMessage(MessagesKeys.PROCESO_EXITOSO);
			ll_intentoActual     = ath_parametros.getIntentosFallidosEjecucionAutomatica();

			ll_intentoActual++;

			try
			{
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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_TIEMPO_ESPERA_INTENTO_ENVIO
						);
					ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_LIMITE_INTENTOS
						);

					while(lb_intentarEnvio && (ll_intentoActual <= ll_limiteIntentos))
					{
						try
						{
							lb_intentarEnvio = enviarDocumentoMensajero(
								    ath_parametros, ldm_manager, as_userId, as_ipRemota, as_endpoint
								);

							if(lb_intentarEnvio)
								throw new B2BException(ErrorKeys.ERROR_EJECUCION_SERVICIO);
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
					}
				}

				terminarTurnoHistoriaYCrearEtapa(
				    ath_parametros, ldm_manager,
				    new MotivoTramite(
				        EtapaCommon.ENTREGA_Y_ENVIO_POR_CORREO_ELECTRONICO,
				        MotivoTramiteCommon.ENVIO_CITATORIO_EN_ESPERA_ACUSE_RECIBO
				    ), as_userId, as_ipRemota, EstadoCommon.TERMINADA
				);

				ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_ELECTRONICA, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_ELECTRONICA, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				Thread.currentThread().interrupt();

				throw new B2BException(ls_mensaje);
			}
			finally
			{
				ldm_manager.close();

				ath_parametros.setIntentosFallidosEjecucionAutomatica(ll_intentoActual);
				ath_parametros.setRespuestaEjecucionAutomatica(ls_mensaje);
				ath_parametros.setIpModificacion(as_ipRemota);
				ath_parametros.setIdUsuarioModificacion(as_userId);

				athd_DAO.updateIntentoEnvios(ath_parametros);
			}
		}
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
	}

	/**
	 * Enviar correspondencia electronica 206.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica206(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_206_WS_INVOKE,
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_206_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_ENTREGA_CORREO_ELECTRONICO_NOTA_DEVOLUTIVA, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarCorrespondenciaElectronica206(lcth_parametros, as_remoteIp);
	}

	/**
	 * Enviar correspondencia electronica 206.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica206(
	    Collection<TurnoHistoria> acth_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_206_BLOQUEO;
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

				clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);

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

				clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);

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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_ENDPOINT
						);

					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;
						TurnoHistoriaDAO   lthd_turnoHistoriaDAO;

						lbpd_bitacora             = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_bitacora);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								try
								{
									enviarCorrespondenciaElectronica206(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);
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
			clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);

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

					clh_LOGGER.error("enviarCorrespondenciaElectronica206", lb2be_e);

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
	 * Enviar correspondencia electronica 206.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaElectronica206(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, TurnoHistoriaDAO athd_DAO, String as_endpoint,
	    String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		if(ath_parametros != null)
		{
			DAOManager ldm_manager;
			String     ls_mensaje;
			long       ll_intentoActual;

			ldm_manager          = DaoManagerFactory.getDAOManager();
			ls_mensaje           = addMessage(MessagesKeys.PROCESO_EXITOSO);
			ll_intentoActual     = ath_parametros.getIntentosFallidosEjecucionAutomatica();

			ll_intentoActual++;

			try
			{
				if(!StringUtils.isValidString(as_endpoint))
					throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);

				DocumentosSalida lds_actaCorreo;

				lds_actaCorreo = generarDocumentoNotificacion(
					    ath_parametros, as_userId, true, as_ipRemota, ldm_manager
					);

				{
					String ls_endpointGestorDocumental;

					ls_endpointGestorDocumental = DaoCreator.getConstantesDAO(ldm_manager)
							                                    .findString(
							    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
							);

					{
						ConstantesDAO                 lcd_constantesDAO;
						EnvioGestorDocumentalBusiness legdb_envioGestor;
						boolean                       lb_intentarEnvio;
						long                          ll_tiempoEspera;
						long                          ll_limiteIntentos;

						lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
						legdb_envioGestor     = getEnvioGestorDocumentalBusiness();
						lb_intentarEnvio      = true;
						ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
							    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_206_TIEMPO_ESPERA_INTENTO_ENVIO
							);
						ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
							    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_ELECTRONICA_206_LIMITE_INTENTOS
							);

						while(lb_intentarEnvio && (ll_intentoActual <= ll_limiteIntentos))
						{
							try
							{
								legdb_envioGestor.enviarGestorDocumental(
								    lds_actaCorreo, abpd_DAO, ls_endpointGestorDocumental, as_userId, as_ipRemota,
								    ldm_manager
								);

								lb_intentarEnvio = enviarDocumentoMensajero(
									    ath_parametros, ldm_manager, as_userId, as_ipRemota, as_endpoint
									);

								if(lb_intentarEnvio)
									throw new B2BException(ErrorKeys.ERROR_EJECUCION_SERVICIO);
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
						}
					}
				}

				terminarTurnoHistoriaYCrearEtapa(
				    ath_parametros, ldm_manager,
				    new MotivoTramite(
				        EtapaCommon.ID_ETAPA_ENTREGA_CORREO_ELECTRONICO_NOTA_DEVOLUTIVA,
				        MotivoTramiteCommon.ACTA_NOTIFICACION_ENVIADA_EN_ESPERA_ACUSE_RECIBO
				    ), as_userId, as_ipRemota, EstadoCommon.TERMINADA
				);

				ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_ELECTRONICA_206, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_ELECTRONICA_206, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				Thread.currentThread().interrupt();

				throw new B2BException(ls_mensaje);
			}
			finally
			{
				ldm_manager.close();

				ath_parametros.setIntentosFallidosEjecucionAutomatica(ll_intentoActual);
				ath_parametros.setRespuestaEjecucionAutomatica(ls_mensaje);
				ath_parametros.setIpModificacion(as_ipRemota);
				ath_parametros.setIdUsuarioModificacion(as_userId);

				athd_DAO.updateIntentoEnvios(ath_parametros);
			}
		}
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
	}
}
