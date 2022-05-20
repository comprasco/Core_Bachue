package com.bachue.snr.prosnr01.business.envioCorrespondenciaFisica;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.envioCorrespondenciaElectronica.BaseCorrespondenciaBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene los métodos de negocio para el envió de liquidaciones generadas al componente de Notificacion de Pagos.
 *
 * @author Julian Vaca.
 *
 */
public class EnvioCorrespondenciaFisicaBusiness extends BaseCorrespondenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaFisicaBusiness.class);

	/**
	 * Entrega documentos correo electronico.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorreoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO_WS_INVOKE,
				    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO_LIMITE_INTENTOS,
				    EtapaCommon.ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			entregaDocumentosCorreoElectronico(lcth_parametros, as_remoteIp);
	}

	/**
	 * Entrega documentos correo electronico.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorreoElectronico(
	    Collection<TurnoHistoria> acth_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO_BLOQUEO;
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

				clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);

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

				clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);

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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_ENDPOINT
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
									entregaDocumentosCorreoElectronico(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);
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
			clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);

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

					clh_LOGGER.error("entregaDocumentosCorreoElectronico", lb2be_e);

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
	 * Entrega documentos correo electronico.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorreoElectronico(
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
					ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO_LIMITE_INTENTOS
						);
					ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO_TIEMPO_ESPERA_INTENTO_ENVIO
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

				String ls_idProceso;
				long   ll_idMotivo203;
				String ls_idSolicitud;

				ls_idProceso       = StringUtils.getStringNotNull(ath_parametros.getIdProceso());
				ll_idMotivo203     = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
				ls_idSolicitud     = ath_parametros.getIdSolicitud();

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

				{
					long ll_idMotivoRecursos;

					ll_idMotivoRecursos = calcularIdMotivoRecursos(ldm_manager, ls_idSolicitud);

					if(ll_idMotivoRecursos > 0)
						ll_idMotivo203 = ll_idMotivoRecursos;
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
					{
						long ll_idMotivo195;

						ll_idMotivo195 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ath_parametros.getIdTurno(), ldm_manager
							);

						if(ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_COBRO_POR_MAYOR_VALOR)
							ll_idMotivo203 = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR_202;
						else if(
						    (ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORREO_ELECTRONICO)
							    || (ll_idMotivo195 == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORREO_ELECTRONICO)
						)
							ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
						else if(
						    ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_SOLICITUD_DOCUMENTACION
						)
							ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES_202;
						else if(ll_idMotivo195 == MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_CORREO_ELECTRONICO)
							ll_idMotivo203 = MotivoTramiteCommon.NEGACION_APERTURA_ACTUACIONES_ADMINISTRATIVAS;
					}
					else if(
					    ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6)
						    || ls_idProceso.equals(ProcesoCommon.ID_PROCESO_5)
						    || ls_idProceso.equals(ProcesoCommon.ID_PROCESO_38)
					)
					{
						long ll_idMotivo190;

						ll_idMotivo190 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION, ath_parametros.getIdTurno(), ldm_manager
							);

						if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6))
						{
							if(
							    (ll_idMotivo190 == MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO)
								    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_DESISTIMIENTO)
								    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_RESTITUCION)
								    || (ll_idMotivo190 == MotivoTramiteCommon.TESTAMENTO_ENTREGA_DIRECCION_RESIDENCIA_CORRESPONDENCIA_190)
								    || (ll_idMotivo190 == MotivoTramiteCommon.REPRODUCCION_TESTAMENTOS_ENTREGA_DIRECCIONES)
							)
								ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
							else if(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_CORREO_ELECTRONICO)
								ll_idMotivo203 = MotivoTramiteCommon.SUSPENSION_TEMPORAL_DEL_TRAMITE_DE_REGISTRO;
							else if(ll_idMotivo190 == MotivoTramiteCommon.MAYOR_VALOR_CORREO_ELECTRONICO)
								ll_idMotivo203 = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR_202_50;
							else if(
							    ll_idMotivo190 == MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORREO_ELECTRONICO
							)
								ll_idMotivo203 = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_202;

							if(
							    !(ll_idMotivo190 == MotivoTramiteCommon.MAYOR_VALOR_CORREO_ELECTRONICO)
								    && !(ll_idMotivo190 == MotivoTramiteCommon.MAYOR_VALOR_CORRESPONDENCIA)
								    && !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_CORREO_ELECTRONICO)
							)
								finalizarDocsEtapasCertificados(
								    ath_parametros.getIdTurno(), as_userId, as_ipRemota, ldm_manager
								);
						}
						else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_5))
						{
							if(ll_idMotivo190 == MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_ORIP)
								ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
							else if(ll_idMotivo190 == MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_610)
								ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						}
						else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_39))
						{
							if(ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_RESTITUCION)
								ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
						}
						else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_38))
						{
							if(ll_idMotivo190 == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_CORREO_ELECTRONICO)
								ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
							else if(
							    ll_idMotivo190 == MotivoTramiteCommon.EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_CORREO_ELECTRONICO
							)
								ll_idMotivo203 = MotivoTramiteCommon.EN_ESPERA_DOCUMENTACION_TRASLADOS;

							{
								long ll_idMotivo191;

								ll_idMotivo191 = obtenerIdMotivoDeEtapaEspecifica(
									    EtapaCommon.APROBACION_DE_TRASLADOS_OFICINA_DESTINO, ath_parametros.getIdTurno(),
									    ldm_manager
									);

								if(
								    ll_idMotivo191 == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_40
								)
									ll_idMotivo203 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
							}
						}
						else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_44))
						{
							long ll_idMotivo110;

							ll_idMotivo110 = obtenerIdMotivoDeEtapaEspecifica(
								    EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA, ath_parametros.getIdTurno(),
								    ldm_manager
								);

							if(
							    (ll_idMotivo110 == MotivoTramiteCommon.APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO_CORREO_ELECTRONICO)
								    || (ll_idMotivo110 == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_CORREO)
							)
								ll_idMotivo203 = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO_200;
							else if(
							    ll_idMotivo110 == MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_CORREO_ELECTRONICO
							)
								ll_idMotivo203 = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO_200;
						}
					}

					terminarTurnoHistoriaYCrearEtapa(
					    ath_parametros, ldm_manager,
					    new MotivoTramite(EtapaCommon.ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO, ll_idMotivo203), as_userId,
					    as_ipRemota, EstadoCommon.TERMINADA
					);

					ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO, ls_mensaje, as_userId,
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
	 * Entrega documentos correspondencia.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorrespondencia(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORRESPONDENCIA_WS_INVOKE,
				    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORRESPONDENCIA_LIMITE_INTENTOS,
				    EtapaCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			entregaDocumentosCorrespondencia(lcth_parametros, as_remoteIp);
	}

	/**
	 * Entrega documentos correspondencia.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorrespondencia(
	    Collection<TurnoHistoria> acth_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORRESPONDENCIA_BLOQUEO;
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

				clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);

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

				clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);

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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_ENDPOINT
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
									entregaDocumentosCorrespondencia(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);
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
			clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);

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

					clh_LOGGER.error("entregaDocumentosCorrespondencia", lb2be_e);

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
	 * Entrega documentos correspondencia.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void entregaDocumentosCorrespondencia(
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
					ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORRESPONDENCIA_LIMITE_INTENTOS
						);
					ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENTREGA_DOCUMENTOS_CORRESPONDENCIA_TIEMPO_ESPERA_INTENTO_ENVIO
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

				String ls_idProceso;
				long   ll_idMotivo202;
				String ls_idSolicitud;

				ls_idProceso       = StringUtils.getStringNotNull(ath_parametros.getIdProceso());
				ll_idMotivo202     = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
				ls_idSolicitud     = ath_parametros.getIdSolicitud();

				if(!StringUtils.isValidString(ls_idSolicitud))
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

				{
					long ll_idMotivoRecursos;

					ll_idMotivoRecursos = calcularIdMotivoRecursos(ldm_manager, ls_idSolicitud);

					if(ll_idMotivoRecursos > 0)
						ll_idMotivo202 = ll_idMotivoRecursos;
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
					{
						long ll_idMotivo195;

						ll_idMotivo195 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ath_parametros.getIdTurno(), ldm_manager
							);

						if(ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_CORRESPONDENCIA_COBRO_POR_MAYOR_VALOR)
							ll_idMotivo202 = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR_202;
						else if(
						    (ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORRESPONDENCIA)
							    || (ll_idMotivo195 == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORRESPONDENCIA)
						)
							ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
						else if(ll_idMotivo195 == MotivoTramiteCommon.ENTREGA_CORRESPONDENCIA_SOLICITUD_DOCUMENTACION)
							ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES_202;
						else if(
						    ll_idMotivo195 == MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_DIRECCION_DE_RESIDENCIA_CORRESPONDENCIA
						)
							ll_idMotivo202 = MotivoTramiteCommon.NEGACION_APERTURA_ACTUACIONES_ADMINISTRATIVAS;
					}
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6))
					{
						long ll_idMotivo190;

						ll_idMotivo190 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION, ath_parametros.getIdTurno(), ldm_manager
							);

						if(
						    (ll_idMotivo190 == MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO)
							    || (ll_idMotivo190 == MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA)
							    || (ll_idMotivo190 == MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA)
							    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_MEDIDA_CAUTELAR)
							    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO)
							    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_RESTITUCION)
							    || (ll_idMotivo190 == MotivoTramiteCommon.TESTAMENTO_ENTREGA_DIRECCION_RESIDENCIA_CORRESPONDENCIA_190)
							    || (ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_DIRECCIONES_REPRODUCCION_TESTAMENTOS)
						)
							ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						else if(
						    (ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_RESIDENCIA_)
							    || (ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_CORRESPONDENCIA)
						)
							ll_idMotivo202 = MotivoTramiteCommon.SUSPENSION_DE_TRAMITE_DE_REGISTRO_A_PREVENCION_202;
						else if(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_18)
							ll_idMotivo202 = MotivoTramiteCommon.SUSPENSION_TEMPORAL_DEL_TRAMITE_DE_REGISTRO;
						else if(ll_idMotivo190 == MotivoTramiteCommon.MAYOR_VALOR_CORRESPONDENCIA)
							ll_idMotivo202 = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR_202_50;
						else if(
						    ll_idMotivo190 == MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORRESPONDENCIA
						)
							ll_idMotivo202 = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_202;

						if(
						    !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_RESIDENCIA_)
							    && !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_CORRESPONDENCIA)
							    && !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_ORIP)
							    && !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_CERTIFICADO_DEVUELTO_AL_PUBLICO)
							    && !(ll_idMotivo190 == MotivoTramiteCommon.SUSPENSION_ARTICULO_18)
							    && !(ll_idMotivo190 == MotivoTramiteCommon.MAYOR_VALOR_CORRESPONDENCIA)
						)
							finalizarDocsEtapasCertificados(
							    ath_parametros.getIdTurno(), as_userId, as_ipRemota, ldm_manager
							);
					}
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_38))
					{
						long ll_idMotivo190;

						ll_idMotivo190 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION, ath_parametros.getIdTurno(), ldm_manager
							);

						if(
						    ll_idMotivo190 == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_DIRECCION_CORRESPONDENCIA
						)
							ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
						else if(
						    ll_idMotivo190 == MotivoTramiteCommon.EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_DIRECCION_RESIDENCIA
						)
							ll_idMotivo202 = MotivoTramiteCommon.EN_ESPERA_DOCUMENTACION_TRASLADOS;

						{
							long ll_idMotivo191;

							ll_idMotivo191 = obtenerIdMotivoDeEtapaEspecifica(
								    EtapaCommon.APROBACION_DE_TRASLADOS_OFICINA_DESTINO, ath_parametros.getIdTurno(),
								    ldm_manager
								);

							if(ll_idMotivo191 == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_50)
								ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
						}
					}
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_39))
					{
						long ll_idMotivo190;

						ll_idMotivo190 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION, ath_parametros.getIdTurno(), ldm_manager
							);

						if(ll_idMotivo190 == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO)
							ll_idMotivo202 = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC;
					}
					else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_44))
					{
						long ll_idMotivo110;

						ll_idMotivo110 = obtenerIdMotivoDeEtapaEspecifica(
							    EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA, ath_parametros.getIdTurno(),
							    ldm_manager
							);

						if(
						    (ll_idMotivo110 == MotivoTramiteCommon.APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO)
							    || (ll_idMotivo110 == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE)
						)
							ll_idMotivo202 = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO_200;
						else if(
						    ll_idMotivo110 == MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO
						)
							ll_idMotivo202 = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO_200;
					}

					terminarTurnoHistoriaYCrearEtapa(
					    ath_parametros, ldm_manager,
					    new MotivoTramite(EtapaCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA, ll_idMotivo202), as_userId,
					    as_ipRemota, EstadoCommon.TERMINADA
					);

					ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA, ls_mensaje, as_userId,
				    as_endpoint, StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA, ls_mensaje, as_userId,
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
	 * Método de transacciones con la base de datos con el fin de encontrar etapas
	 * 215 con estado actividad AUT de Turno Historia.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaFisica(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_WS_INVOKE,
				    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_ENTREGA_POR_CORRESPONDENCIA, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarCorrespondenciaFisica(lcth_parametros, as_remoteIp);
	}

	/**
	 *  Método de transacciones con la base de datos para procesar casos encontrados
	 * en etapa 215 y estado actividad AUT.
	 *
	 * @param acth_parametros Objeto de tipo Collection<TurnoHistoria> que contiene casos a ser procesados.
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaFisica(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_BLOQUEO;
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

				clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

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

				clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_ENDPOINT
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
									enviarCorrespondenciaFisica(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("enviarCorrespondenciaFisica", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);
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
			clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

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

					clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

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
	 * Método encargado de ejecutar web service para el envio de correspondencia fisica al componente de comunicaciones y notificaciones.
	 *
	 * @param ath_parametros  Objeto de tipo  Liquidacion que contiene datos de la liquidacion sin notificar.
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió  al componente de notificacion de pagos.
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la invocacion al metodo
	 * @param as_ipRemota Objeto de tipo String que contiene la ip desde la cual que realiza la invocacion al metodo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarCorrespondenciaFisica(
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
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_TIEMPO_ESPERA_INTENTO_ENVIO
						);
					ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_ENVIO_CORRESPONDENCIA_FISICA_LIMITE_INTENTOS
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
				        EtapaCommon.ID_ETAPA_ENTREGA_POR_CORRESPONDENCIA,
				        MotivoTramiteCommon.ENVIO_CORRESPONDENCIA_FISICA_DIRECCION
				    ), as_userId, as_ipRemota, EstadoCommon.TERMINADA
				);

				ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_FISICA, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_CORRESPONDENCIA_FISICA, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(ath_parametros.getIdTurnoHistoria())
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
	 * Finalizar docs etapas certificados.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void finalizarDocsEtapasCertificados(
	    String as_idTurno, String as_userId, String as_ipRemota, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<TurnoDerivado> lctd_turnoDerivados;

		lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(adm_manager).findByIdTurnoPadre(as_idTurno);

		if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
		{
			TurnoHistoriaDAO    lth_turnoHistoriaDAO;
			DocumentosSalidaDAO ldsd_documentosSalidaDAO;

			lth_turnoHistoriaDAO         = DaoCreator.getTurnoHistoriaDAO(adm_manager);
			ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

			for(TurnoDerivado ltd_actual : lctd_turnoDerivados)
			{
				if(ltd_actual != null)
				{
					String ls_idTurnoHijo;

					ls_idTurnoHijo = ltd_actual.getIdTurnoHijo();

					if(!ltd_actual.isIndVinculado())
					{
						Collection<DocumentosSalida> lcds_docsDerivados;

						lcds_docsDerivados = ldsd_documentosSalidaDAO.findByIdTurnoEstadoA(ls_idTurnoHijo, true);

						if(CollectionUtils.isValidCollection(lcds_docsDerivados))
						{
							for(DocumentosSalida lds_documento : lcds_docsDerivados)
							{
								if(lds_documento != null)
								{
									lds_documento.setEstado(EstadoCommon.ENTREGA);
									lds_documento.setIdUsuarioModificacion(as_userId);
									lds_documento.setIpModificacion(as_ipRemota);

									ldsd_documentosSalidaDAO.updateEstadoAny(lds_documento);
								}
							}
						}
					}

					{
						TurnoHistoria lth_etapa30;

						lth_etapa30 = lth_turnoHistoriaDAO.findByIdTurnoEtapaDiffASG(
							    ls_idTurnoHijo, EtapaCommon.ID_ETAPA_30
							);

						if(lth_etapa30 != null)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_etapa30, adm_manager,
							    new MotivoTramite(
							        EtapaCommon.ID_ETAPA_30, MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO
							    ), as_userId, as_ipRemota, EstadoCommon.TERMINADA, false, true, false, false
							);
					}
				}
			}
		}
	}

	/**
	 * Obtener id motivo de etapa especifica.
	 *
	 * @param al_idEtapa de al id etapa
	 * @param as_idTurno de as id solicitud
	 * @param adm_manager de adm manager
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized long obtenerIdMotivoDeEtapaEspecifica(
	    long al_idEtapa, String as_idTurno, DAOManager adm_manager
	)
	    throws B2BException
	{
		long          ll_idMotivo;
		TurnoHistoria lth_etapa;

		lth_etapa = DaoCreator.getTurnoHistoriaDAO(adm_manager).findMaxByIdEtapaIdTurno(al_idEtapa, as_idTurno);

		if(lth_etapa != null)
		{
			Long lL_idMotivo;

			lL_idMotivo = lth_etapa.getIdMotivo();

			if(!NumericUtils.isValidLong(lL_idMotivo))
				throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO);

			ll_idMotivo = lL_idMotivo.longValue();
		}
		else
			throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

		return ll_idMotivo;
	}
}
