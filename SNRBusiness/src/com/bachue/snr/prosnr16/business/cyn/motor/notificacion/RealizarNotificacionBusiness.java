package com.bachue.snr.prosnr16.business.cyn.motor.notificacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.envioCorrespondenciaElectronica.BaseCorrespondenciaBusiness;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDecisionRecursoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoNotificacionCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.RecursoDecision;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


/**
 * Clase que contiene todos las propiedades RealizarNotificacionBusiness.
 *
 * @author  Manuel Blanco
 */
public class RealizarNotificacionBusiness extends BaseCorrespondenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RealizarNotificacionBusiness.class);

	/**
	 * Cambiar medio publicacion.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cambiarMedioPublicacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((ath_turnoHistoria != null) && validarDatosAuditoria(as_userId, as_remoteIp))
			{
				String ls_idDocumentoSalida;
				String ls_medioPubliacacion;
				Date   ld_fechaInicial;
				Date   ld_fechaPublicacion;
				Date   ld_fechaDesfijacion;

				ls_idDocumentoSalida     = ath_turnoHistoria.getIdDocumentoSalida();
				ls_medioPubliacacion     = ath_turnoHistoria.getMedioPublicacion();
				ld_fechaInicial          = ath_turnoHistoria.getFechaInicial();
				ld_fechaPublicacion      = ath_turnoHistoria.getFechaPublicacionAvisoWeb();
				ld_fechaDesfijacion      = ath_turnoHistoria.getFechaDesfijacionAviso();

				if(StringUtils.isValidString(ls_idDocumentoSalida))
				{
					if(
					    StringUtils.isValidString(ls_medioPubliacacion) && (ld_fechaInicial != null)
						    && (ld_fechaPublicacion != null) && (ld_fechaDesfijacion != null)
					)
					{
						Date ld_fechaActual;

						ld_fechaActual = new Date();

						if(ld_fechaPublicacion.before(ld_fechaActual) || ld_fechaPublicacion.equals(ld_fechaActual))
							DaoCreator.getDocumentosSalidaDAO(ldm_manager).updateMedioPublicacion(ath_turnoHistoria);
						else
							throw new B2BException(ErrorKeys.ERROR_FECHA_PUBLICACION);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TURNO_DOCUMENTO_SALIDA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cambiarMedioPublicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Cambiar responsable publicacion.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cambiarResponsablePublicacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((ath_turnoHistoria != null) && validarDatosAuditoria(as_userId, as_remoteIp))
			{
				String ls_idDocumentoSalida;
				String ls_idResponsablePublicacion;

				ls_idDocumentoSalida            = ath_turnoHistoria.getIdDocumentoSalida();
				ls_idResponsablePublicacion     = ath_turnoHistoria.getResponsablePublicacion();

				ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
				ath_turnoHistoria.setIpModificacion(as_remoteIp);

				if(StringUtils.isValidString(ls_idDocumentoSalida))
				{
					if(StringUtils.isValidString(ls_idResponsablePublicacion))
						DaoCreator.getDocumentosSalidaDAO(ldm_manager).updateResponsablePublicacion(ath_turnoHistoria);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TURNO_DOCUMENTO_SALIDA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cambiarMedioPublicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Consultar documento salida.
	 *
	 * @param al_idDocumentoSalida de al id documento salida
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DocumentosSalida consultarDocumentoSalida(long al_idDocumentoSalida)
	    throws B2BException
	{
		DocumentosSalida lds_documentoSalida;
		DAOManager       ldm_manager;

		lds_documentoSalida     = null;
		ldm_manager             = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_idDocumentoSalida > 0L)
				lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager).findById(al_idDocumentoSalida);
			else
				throw new B2BException(ErrorKeys.ERROR_TURNO_DOCUMENTO_SALIDA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarDocumentoSalida", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lds_documentoSalida;
	}

	/**
	 * Consultar informacion notificacion.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Notificacion consultarInformacionNotificacion(String as_idTurno)
	    throws B2BException
	{
		Notificacion ln_notificacion;
		DAOManager   ldm_manager;

		ln_notificacion     = new Notificacion();
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			ln_notificacion.setDocumentosNotificaciones(cargarDocumentosNotificaciones(ldm_manager, as_idTurno));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosNotificaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ln_notificacion;
	}

	/**
	 * Desfijar avisos.
	 *
	 * @param acth_turnos de acth turnos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void desfijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(!CollectionUtils.isValidCollection(acth_turnos))
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			DocumentosSalidaDAO ldsd_documentosSalidaDAO;
			SolicitudDAO        lsd_solicitudDAO;

			ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
			lsd_solicitudDAO             = DaoCreator.getSolicitudDAO(ldm_manager);

			for(TurnoHistoria lth_turnoHistoria : acth_turnos)
			{
				if(lth_turnoHistoria != null)
				{
					DocumentosSalida lds_documento;

					lds_documento = ldsd_documentosSalidaDAO.findLastByIdSolicitudTipo(
						    lth_turnoHistoria.getIdSolicitud(), TipoArchivoCommon.FIJACION_AVISO
						);

					if(lds_documento == null)
						throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);

					lds_documento.setFechaDesfijacionAviso(lth_turnoHistoria.getFechaDesfijacionAviso());
					lds_documento.setIdTipoNotificacion(TipoNotificacionCommon.AVISO_WEB);
					lds_documento.setIdUsuarioModificacion(as_userId);
					lds_documento.setIpModificacion(as_remoteIp);

					ldsd_documentosSalidaDAO.updateFechaDesfijacionAvisoWeb(lds_documento);

					{
						Solicitud ls_solicitud;

						ls_solicitud = lsd_solicitudDAO.findById(lth_turnoHistoria.getIdSolicitud());

						if(ls_solicitud != null)
						{
							boolean          lb_notificacion;
							DocumentosSalida lds_documentoDesfijacion;
							String           ls_idProceso;

							lb_notificacion              = StringUtils.getStringNotNull(
								    ls_solicitud.getIdAutorizacionNotificacion()
								).equals(MedioNotificarCommon.CORREO_ELECTRONICO);
							lds_documentoDesfijacion     = generarDocumentoSalidaBase(
								    lth_turnoHistoria, ls_solicitud, lb_notificacion, true, ldm_manager
								);
							ls_idProceso                 = StringUtils.getStringNotNull(
								    lth_turnoHistoria.getIdProceso()
								);

							lds_documentoDesfijacion.setTipo(TipoArchivoCommon.DESFIJACION_AVISO);
							lds_documentoDesfijacion.setIdTipoDocumental(TipoDocumentalCommon.DESFIJACION_AVISO);

							if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
							{
								lds_documentoDesfijacion.setDestinatario(lds_documento.getDestinatario());
								lds_documentoDesfijacion.setDireccion(lds_documento.getDireccion());
								lds_documentoDesfijacion.setIdPais(lds_documento.getIdPais());
								lds_documentoDesfijacion.setIdDepartamento(lds_documento.getIdDepartamento());
								lds_documentoDesfijacion.setIdMunicipio(lds_documento.getIdMunicipio());

								generarDocumentoCitatorioResol(
								    lth_turnoHistoria, lds_documento, lds_documentoDesfijacion,
								    ConstanteCommon.PLANTILLA_DESFIJACION_AVISO_WEB,
								    lds_documento.getConsecutivoResolucion(), lds_documento.getFechaResolucion(),
								    as_userId, as_remoteIp, ldm_manager
								);
							}
							else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6))
								generarDocumentoCitatorioResol(
								    lth_turnoHistoria, lds_documento, lds_documentoDesfijacion,
								    ConstanteCommon.PLANTILLA_DESFIJACION_AVISO_WEB,
								    lds_documento.getConsecutivoResolucion(), lds_documento.getFechaResolucion(),
								    as_userId, as_remoteIp, ldm_manager
								);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
					}

					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(
					        EtapaCommon.EN_ESPERA_TERMINO_DE_PUBLICACION_AVISO_WEB, MotivoTramiteCommon.AVISO_DESFIJADO
					    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA, false, true
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("desfijarAvisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Enviar aviso.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAviso(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_AVISO_WS_INVOKE, ConstanteCommon.JOB_ENVIO_AVISO_LIMITE_INTENTOS,
				    EtapaCommon.ENVIO_AVISO, true, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAviso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarAviso(lcth_parametros, as_remoteIp);
	}

	/**
	 * Enviar aviso.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAviso(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_AVISO_BLOQUEO;
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

				clh_LOGGER.error("enviarAviso", lb2be_e);

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

				clh_LOGGER.error("enviarAviso", lb2be_e);

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
									enviarAviso(
									    lth_iterador, lbpd_bitacora, lthd_turnoHistoriaDAO, ls_endpoint, ls_userId,
									    as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("enviarAviso", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarAviso", lb2be_e);
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
			clh_LOGGER.error("enviarAviso", lb2be_e);

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

					clh_LOGGER.error("enviarAviso", lb2be_e);

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
	 * Enviar aviso.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param athd_DAO de athd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAviso(
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

				DocumentosSalida lds_documento;

				lds_documento = generarDocumentoNotificacion(ath_parametros, as_userId, true, as_ipRemota, ldm_manager);

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
							    ConstanteCommon.JOB_ENVIO_AVISO_TIEMPO_ESPERA_INTENTO_ENVIO
							);
						ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
							    ConstanteCommon.JOB_ENVIO_AVISO_LIMITE_INTENTOS
							);

						while(lb_intentarEnvio && (ll_intentoActual <= ll_limiteIntentos))
						{
							try
							{
								legdb_envioGestor.enviarGestorDocumental(
								    lds_documento, abpd_DAO, ls_endpointGestorDocumental, as_userId, as_ipRemota,
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
				        EtapaCommon.ENVIO_AVISO, MotivoTramiteCommon.AVISO_ENVIADO_EN_ESPERA_FECHA_DE_ACUSE
				    ), as_userId, as_ipRemota, EstadoCommon.TERMINADA
				);

				ath_parametros.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_AVISO, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(ath_parametros.getIdTurnoHistoria())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_AVISO, ls_mensaje, as_userId, as_endpoint,
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
	 * Enviar aviso web.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAvisoWeb(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_AVISO_WEB_WS_INVOKE, ConstanteCommon.JOB_ENVIO_AVISO_WEB_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarAvisoWeb(lcth_parametros, as_remoteIp);
	}

	/**
	 * Enviar aviso web.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAvisoWeb(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_AVISO_WEB_BLOQUEO;
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

				clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

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

				clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

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

				ldm_bitacora = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									enviarAvisoWeb(lth_iterador, lbpd_bitacora, ls_userId, as_remoteIp);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_bitacora);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("enviarAvisoWeb", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

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

					clh_LOGGER.error("enviarAvisoWeb", lb2be_e);

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
	 * Enviar aviso web.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAvisoWeb(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			generarDocumentoNotificacion(ath_parametros, as_userId, false, as_ipRemota, ldm_manager);

			ath_parametros.setEstadoActividad(EstadoCommon.ASIGNADA);
			ath_parametros.setIdUsuarioModificacion(as_userId);
			ath_parametros.setIpModificacion(as_ipRemota);

			DaoCreator.getTurnoHistoriaDAO(ldm_manager).updateEstadoActividad(ath_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.ENVIO_AVISO_WEB, lb2be_e.getMessage(), as_userId, null,
			    (ath_parametros != null) ? StringUtils.getString(ath_parametros.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Envio visor SGD.
	 *
	 * @param as_idDocumentoSalida de as id documento salida
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized String envioVisorSGD(String as_idDocumentoSalida)
	    throws B2BException
	{
		String     ls_urlDocumento;
		DAOManager ldm_manager;

		ls_urlDocumento     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			DocumentosSalida lds_documento;

			lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
					                      .findById(NumericUtils.getLong(as_idDocumentoSalida));

			if(lds_documento != null)
			{
				Collection<DocumentoOWCC> lcdowcc_documentos;

				{
					DocumentoOWCC ldowcc_filtros;

					ldowcc_filtros     = new DocumentoOWCC(
						    lds_documento.getIdEcm(), lds_documento.getIdNombreDocumento()
						);

					lcdowcc_documentos = consultarDocumentosOWCC(
						    ldm_manager, ldowcc_filtros, lds_documento.getRepositorio()
						);
				}

				if(CollectionUtils.isValidCollection(lcdowcc_documentos))
				{
					for(DocumentoOWCC ldowcc_doc : lcdowcc_documentos)
					{
						if(ldowcc_doc != null)
						{
							ls_urlDocumento = ldowcc_doc.getURL();

							break;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("envioVisorSGD", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_urlDocumento;
	}

	/**
	 * Fijar avisos.
	 *
	 * @param acth_turnos de acth turnos
	 * @param al_idEtapa de id etapa
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void fijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, long al_idEtapa, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_bitacora;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		ldm_bitacora.setAutoCommit(true);

		try
		{
			if(!CollectionUtils.isValidCollection(acth_turnos))
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			EnvioGestorDocumentalBusiness legdb_envioBusiness;
			BitacoraProcesoDAO            lbpd_bitacora;
			DocumentosSalidaDAO           ldsd_documentosSalidaDAO;
			long                          llg_motivoTramite;

			legdb_envioBusiness          = getEnvioGestorDocumentalBusiness();
			lbpd_bitacora                = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
			ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
			llg_motivoTramite            = 0L;

			for(TurnoHistoria lth_turnoHistoria : acth_turnos)
			{
				if(lth_turnoHistoria != null)
				{
					DocumentosSalida lds_documento;

					lds_documento = generarDocumentoNotificacion(
						    lth_turnoHistoria, as_userId, true, as_remoteIp, ldm_manager
						);

					lds_documento.setRepositorio(RepositorioCommon.FINAL);

					{
						String ls_endpointGestorDocumental;

						ls_endpointGestorDocumental = DaoCreator.getConstantesDAO(ldm_manager)
								                                    .findString(
								    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
								);

						legdb_envioBusiness.enviarGestorDocumental(
						    lds_documento, lbpd_bitacora, ls_endpointGestorDocumental, as_userId, as_remoteIp,
						    ldm_manager
						);
					}

					lds_documento.setFechaPublicacionAvisoWeb(lth_turnoHistoria.getFechaPublicacionAvisoWeb());
					lds_documento.setIdUsuarioModificacion(as_userId);
					lds_documento.setIpModificacion(as_remoteIp);

					ldsd_documentosSalidaDAO.updateFechaPublicacionAvisoWeb(lds_documento);

					llg_motivoTramite = MotivoTramiteCommon.AVISO_FIJADO;

					{
						String ls_idProceso;
						String ls_idSubproceso;

						ls_idProceso        = lth_turnoHistoria.getIdProceso();
						ls_idSubproceso     = lth_turnoHistoria.getIdSubproceso();

						if(
						    StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso)
							    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
							    && ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM1)
							    && (al_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN))
						)
							llg_motivoTramite = MotivoTramiteCommon.RESOLUCION_TRASLADO_INDIVIDUAL_PUBLICADA_ENVIO_A_EJECUTOR;
					}

					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager, new MotivoTramite(al_idEtapa, llg_motivoTramite), as_userId,
					    as_remoteIp, EstadoCommon.TERMINADA, false, true
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("fijarAvisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_bitacora.close();
		}
	}

	/**
	 * Insertar documento salida.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia asociado al documento salida.
	 * @param li_imagen Argumento de tipo <code>Imagenes</code> que contiene la imagen asociada al documento salida.
	 * @param as_userId  Argumento de tipo <code>String </code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp  Argumento de tipo <code>String </code> que contiene el la dirección ip remota usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void insertarDocumentoSalida(
	    TurnoHistoria ath_turnoHistoria, Imagenes li_imagen, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((ath_turnoHistoria != null) && (li_imagen != null) && validarDatosAuditoria(as_userId, as_remoteIp))
			{
				long ll_idImagen;

				li_imagen.setIdUsuarioCreacion(as_userId);
				li_imagen.setIpCreacion(as_remoteIp);
				ll_idImagen = DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, true);

				if(ll_idImagen > 0L)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;
					String ls_idSolicitud;

					ll_idTurnoHistoria     = ath_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = ath_turnoHistoria.getIdTurno();
					ls_idSolicitud         = ath_turnoHistoria.getIdSolicitud();

					if(
					    NumericUtils.isValidLong(ll_idTurnoHistoria) && StringUtils.isValidString(ls_idTurno)
						    && StringUtils.isValidString(ls_idSolicitud)
					)
					{
						DocumentosSalidaDAO lds_DAO;
						DocumentosSalida    lds_documento;

						lds_documento     = new DocumentosSalida();
						lds_DAO           = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

						lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
						lds_documento.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
						lds_documento.setIdTurno(ls_idTurno);
						lds_documento.setIdSolicitud(ls_idSolicitud);
						lds_documento.setTipoArchivo(ExtensionCommon.PDF);
						lds_documento.setIdTipoDocumental(TipoDocumentalCommon.SOPORTES);
						lds_documento.setEstado(EstadoCommon.ACTIVO);
						lds_documento.setEliminarEnvio(EstadoCommon.N);
						lds_documento.setDocumentoAutomatico(EstadoCommon.N);
						lds_documento.setRepositorio(RepositorioCommon.FINAL);
						lds_documento.setDocumentoFinal(EstadoCommon.S);
						lds_documento.setTipo(IdentificadoresCommon.SOPORTES);
						lds_documento.setIdUsuarioCreacion(as_userId);
						lds_documento.setIdUsuarioCreacion(as_remoteIp);

						lds_DAO.insertOrUpdate(lds_documento, true);

						{
							DocumentosSalida lds_nuevoDocumento;

							lds_nuevoDocumento = lds_DAO.findByIdImagen(NumericUtils.getLongWrapper(ll_idImagen));

							if(lds_nuevoDocumento != null)
							{
								long ll_idDocumentoSalida;

								ll_idDocumentoSalida = lds_nuevoDocumento.getIdDocumentoSalida();

								if(ll_idDocumentoSalida > 0L)
								{
									ath_turnoHistoria.setIdDocumentoSalida(StringUtils.getString(ll_idDocumentoSalida));
									DaoCreator.getTurnoHistoriaDAO(ldm_manager)
										          .updateIdDocumentoSalida(ath_turnoHistoria);
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_IMAGEN_NO_VALIDA);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cambiarMedioPublicacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Obtener casos avisos.
	 *
	 * @param al_idEtapa de al id etapa
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TurnoHistoria> obtenerCasosAvisos(
	    long al_idEtapa, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TurnoHistoria> lcth_turnos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcth_turnos     = null;

		try
		{
			lcth_turnos = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
					                    .findByEtapaEstAct(al_idEtapa, EstadoCommon.ASIGNADA);

			if(CollectionUtils.isValidCollection(lcth_turnos))
			{
				DocumentosSalidaDAO ldsd_documentosSalidaDAO;
				ProcesoDAO          lpd_procesoDAO;
				SubprocesoDAO       lsd_subprocesoDAO;
				SolicitudDAO        lsd_solicitudDAO;
				TurnoHistoriaDAO    lth_turnoHistoriaDAO;

				ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				lpd_procesoDAO               = DaoCreator.getProcesoDAO(ldm_manager);
				lsd_subprocesoDAO            = DaoCreator.getSubprocesoDAO(ldm_manager);
				lsd_solicitudDAO             = DaoCreator.getSolicitudDAO(ldm_manager);
				lth_turnoHistoriaDAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(TurnoHistoria lth_turno : lcth_turnos)
				{
					if(lth_turno != null)
					{
						{
							DocumentosSalida lds_documento;

							lds_documento = ldsd_documentosSalidaDAO.findById(
								    NumericUtils.getLong(lth_turno.getIdDocumentoSalida())
								);

							if(lds_documento != null)
								lth_turno.setDestinatario(lds_documento.getDestinatario());
						}

						{
							String ls_idProceso;

							ls_idProceso = lth_turno.getIdProceso();

							{
								Proceso lp_proceso;
								String  ls_idSubproceso;

								lp_proceso          = lpd_procesoDAO.findById(ls_idProceso);
								ls_idSubproceso     = lth_turno.getIdSubproceso();

								if(lp_proceso != null)
								{
									lth_turno.setNombreProceso(lp_proceso.getNombre());

									if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38))
									{
										TurnoHistoria lth_turnoHistoriaAnterior;

										lth_turnoHistoriaAnterior = null;

										if(
										    (al_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
											    && ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM1)
										)
											lth_turnoHistoriaAnterior = lth_turnoHistoriaDAO.findMaxByIdEtapaIdTurno(
												    EtapaCommon.ID_ETAPA_APROBACION, lth_turno.getIdTurno()
												);
										else if(
										    (al_idEtapa == EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS)
											    && ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM2)
										)
											lth_turnoHistoriaAnterior = lth_turnoHistoriaDAO.findMaxByIdEtapaIdTurno(
												    EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO,
												    lth_turno.getIdTurno()
												);

										if(lth_turnoHistoriaAnterior != null)
										{
											DocumentosSalida lds_documentoSalida;

											lds_documentoSalida = new DocumentosSalida();

											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(lth_turnoHistoriaAnterior.getIdTurnoHistoria())
											);
											lds_documentoSalida.setIdTipoDocumental(
											    TipoDocumentalCommon.FIJACION_AVISO
											);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

											lds_documentoSalida = ldsd_documentosSalidaDAO
													.findByIdTurnoHistoriaTipoDocumentalEstado(lds_documentoSalida);

											if(lds_documentoSalida != null)
											{
												String ls_idEcm;
												String ls_idNombreDocumento;

												ls_idEcm                 = lds_documentoSalida.getIdEcm();
												ls_idNombreDocumento     = lds_documentoSalida.getIdNombreDocumento();

												if(
												    StringUtils.isValidString(ls_idEcm)
													    && StringUtils.isValidString(ls_idNombreDocumento)
												)
												{
													lds_documentoSalida.setIdTurnoHistoria(
													    NumericUtils.getInteger(lth_turno.getIdTurnoHistoria())
													);
													lds_documentoSalida.setIdUsuarioModificacion(as_remoteIp);
													lds_documentoSalida.setIpModificacion(as_userId);

													ldsd_documentosSalidaDAO.updateIdTurnoHistoria(lds_documentoSalida);

													{
														lth_turno.setIdDocumentoSalida(
														    StringUtils.getString(
														        lds_documentoSalida.getIdDocumentoSalida()
														    )
														);
														lth_turno.setIdUsuarioModificacion(as_remoteIp);
														lth_turno.setIpModificacion(as_userId);

														lth_turnoHistoriaDAO.updateIdDocumentoSalida(lth_turno);
													}
												}
											}
										}
									}
								}

								{
									Subproceso ls_subproceso;

									ls_subproceso = lsd_subprocesoDAO.findById(ls_idProceso, ls_idSubproceso);

									if(ls_subproceso != null)
										lth_turno.setNombreSubproceso(ls_subproceso.getNombre());
								}
							}
						}

						{
							Solicitud ls_solicitud;

							ls_solicitud = lsd_solicitudDAO.findById(lth_turno.getIdSolicitud());

							if(ls_solicitud != null)
								lth_turno.setNir(ls_solicitud.getNir());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCasosAvisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcth_turnos;
	}

	/**
	 * Obtener casos fijacion avisos.
	 *
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id de la etapa.
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id del proceso.
	 * @param as_subproceso Argumento de tipo <code>String</code> que contiene el subproceso.
	 * @param aa_aprobacion Argumento de tipo <code>Aprobacion</code> que contiene la información de la aprobación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TurnoHistoria> obtenerCasosFijacionAvisos(
	    long al_idEtapa, String as_idProceso, String as_subproceso, Aprobacion aa_aprobacion, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TurnoHistoria> lcth_turnos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcth_turnos     = null;

		try
		{
			if(aa_aprobacion != null)
				lcth_turnos = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                    .findByEtapaEstActProcesoSubproceso(
						    al_idEtapa, EstadoCommon.ASIGNADA, as_idProceso, as_subproceso, aa_aprobacion
						);
			else
				lcth_turnos = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                    .findByEtapaEstActProcesoSubproceso(
						    al_idEtapa, EstadoCommon.ASIGNADA, as_idProceso, as_subproceso
						);

			if(CollectionUtils.isValidCollection(lcth_turnos))
			{
				DocumentosSalidaDAO ldsd_documentosSalidaDAO;
				ProcesoDAO          lpd_procesoDAO;
				SubprocesoDAO       lsd_subprocesoDAO;
				SolicitudDAO        lsd_solicitudDAO;
				TurnoHistoriaDAO    lth_turnoHistoriaDAO;

				ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				lpd_procesoDAO               = DaoCreator.getProcesoDAO(ldm_manager);
				lsd_subprocesoDAO            = DaoCreator.getSubprocesoDAO(ldm_manager);
				lsd_solicitudDAO             = DaoCreator.getSolicitudDAO(ldm_manager);
				lth_turnoHistoriaDAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(TurnoHistoria lth_turno : lcth_turnos)
				{
					if(lth_turno != null)
					{
						{
							DocumentosSalida lds_documento;

							lds_documento = ldsd_documentosSalidaDAO.findById(
								    NumericUtils.getLong(lth_turno.getIdDocumentoSalida())
								);

							if(lds_documento != null)
								lth_turno.setDestinatario(lds_documento.getDestinatario());
						}

						{
							String ls_idProceso;

							ls_idProceso = lth_turno.getIdProceso();

							{
								Proceso lp_proceso;
								String  ls_idSubproceso;

								lp_proceso          = lpd_procesoDAO.findById(ls_idProceso);
								ls_idSubproceso     = lth_turno.getIdSubproceso();

								if(lp_proceso != null)
								{
									lth_turno.setNombreProceso(lp_proceso.getNombre());

									if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38))
									{
										TurnoHistoria lth_turnoHistoriaAnterior;

										lth_turnoHistoriaAnterior = null;

										if(
										    (al_idEtapa == EtapaCommon.ID_ETAPA_FIJACION_AVISOS_OFICINA_ORIGEN)
											    && ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM1)
										)
											lth_turnoHistoriaAnterior = lth_turnoHistoriaDAO.findMaxByIdEtapaIdTurno(
												    EtapaCommon.ID_ETAPA_APROBACION, lth_turno.getIdTurno()
												);
										else if(
										    (al_idEtapa == EtapaCommon.PUBLICACION_ACTOS_ADMINISTIVOS)
											    && ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM2)
										)
											lth_turnoHistoriaAnterior = lth_turnoHistoriaDAO.findMaxByIdEtapaIdTurno(
												    EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO,
												    lth_turno.getIdTurno()
												);

										if(lth_turnoHistoriaAnterior != null)
										{
											DocumentosSalida lds_documentoSalida;

											lds_documentoSalida = new DocumentosSalida();

											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(lth_turnoHistoriaAnterior.getIdTurnoHistoria())
											);
											lds_documentoSalida.setIdTipoDocumental(
											    TipoDocumentalCommon.FIJACION_AVISO
											);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

											lds_documentoSalida = ldsd_documentosSalidaDAO
													.findByIdTurnoHistoriaTipoDocumentalEstado(lds_documentoSalida);

											if(lds_documentoSalida != null)
											{
												String ls_idEcm;
												String ls_idNombreDocumento;

												ls_idEcm                 = lds_documentoSalida.getIdEcm();
												ls_idNombreDocumento     = lds_documentoSalida.getIdNombreDocumento();

												if(
												    StringUtils.isValidString(ls_idEcm)
													    && StringUtils.isValidString(ls_idNombreDocumento)
												)
												{
													lds_documentoSalida.setIdTurnoHistoria(
													    NumericUtils.getInteger(lth_turno.getIdTurnoHistoria())
													);
													lds_documentoSalida.setIdUsuarioModificacion(as_remoteIp);
													lds_documentoSalida.setIpModificacion(as_userId);

													ldsd_documentosSalidaDAO.updateIdTurnoHistoria(lds_documentoSalida);

													{
														lth_turno.setIdDocumentoSalida(
														    StringUtils.getString(
														        lds_documentoSalida.getIdDocumentoSalida()
														    )
														);
														lth_turno.setIdUsuarioModificacion(as_remoteIp);
														lth_turno.setIpModificacion(as_userId);

														lth_turnoHistoriaDAO.updateIdDocumentoSalida(lth_turno);
													}
												}
											}
										}
									}
								}

								{
									Subproceso ls_subproceso;

									ls_subproceso = lsd_subprocesoDAO.findById(ls_idProceso, ls_idSubproceso);

									if(ls_subproceso != null)
										lth_turno.setNombreSubproceso(ls_subproceso.getNombre());
								}
							}
						}

						{
							Solicitud ls_solicitud;

							ls_solicitud = lsd_solicitudDAO.findById(lth_turno.getIdSolicitud());

							if(ls_solicitud != null)
								lth_turno.setNir(ls_solicitud.getNir());
						}

						{
							Date ld_fechaActual;
							Date ld_fechaCreacion;

							ld_fechaActual       = new Date();
							ld_fechaCreacion     = lth_turno.getFechaCreacion();

							if(ld_fechaCreacion != null)
								lth_turno.setFechaInicial(ld_fechaCreacion);

							lth_turno.setFechaPublicacionAvisoWeb(ld_fechaActual);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCasosAvisos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcth_turnos;
	}

	/**
	 * Obtener documentos aviso.
	 *
	 * @param acth_turnos de acth turnos
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DocumentosSalida> obtenerDocumentosAviso(Collection<TurnoHistoria> acth_turnos)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentos;
		DAOManager                   ldm_manager;

		lcds_documentos     = new LinkedList<DocumentosSalida>();
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			if(!CollectionUtils.isValidCollection(acth_turnos))
				throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_TURNOS);

			DocumentosSalidaDAO ldsd_documentosSalidaDAO;

			ldsd_documentosSalidaDAO = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

			for(TurnoHistoria lth_turno : acth_turnos)
			{
				if(lth_turno != null)
				{
					DocumentosSalida lds_documento;

					lds_documento = ldsd_documentosSalidaDAO.findByIdImpresion(
						    NumericUtils.getLong(lth_turno.getIdDocumentoSalida())
						);

					if(lds_documento != null)
						lcds_documentos.add(lds_documento);
				}
			}

			if(lcds_documentos.isEmpty())
				throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDocumentosAviso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcds_documentos;
	}

	/**
	 * Realizar notificacion.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void realizarNotificacion(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_REALIZAR_NOTIFICACION_WS_INVOKE,
				    ConstanteCommon.JOB_REALIZAR_NOTIFICACION_LIMITE_INTENTOS, EtapaCommon.NOTIFICADO, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("realizarNotificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			realizarNotificacion(lcth_parametros, as_remoteIp);
	}

	/**
	 * Realizar notificacion.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void realizarNotificacion(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_REALIZAR_NOTIFICACION_BLOQUEO;
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

				clh_LOGGER.error("realizarNotificacion", lb2be_e);

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

				clh_LOGGER.error("realizarNotificacion", lb2be_e);

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

				ldm_bitacora = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									realizarNotificacion(lth_iterador, lbpd_bitacora, ls_userId, as_remoteIp);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("realizarNotificacion", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_bitacora);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("realizarNotificacion", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("realizarNotificacion", lb2be_e);

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

					clh_LOGGER.error("realizarNotificacion", lb2be_e);

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
	 * Realizar notificacion.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void realizarNotificacion(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			String ls_idTurno;

			ls_idTurno = ath_parametros.getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				Turno            lt_turno;
				Solicitud        ls_solicitud;
				TurnoHistoriaDAO lthd_DAO;

				ls_solicitud     = DaoCreator.getSolicitudDAO(ldm_manager).findById(ath_parametros.getIdSolicitud());
				lt_turno         = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);
				lthd_DAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				if((lt_turno != null) && (ls_solicitud != null))
				{
					boolean       lb_renunciaTerminos;
					long          ll_idMotivo;
					String        ls_idProceso;
					TurnoHistoria lth_turnoHistoria175;

					lb_renunciaTerminos      = false;
					ll_idMotivo              = MotivoTramiteCommon.NOTA_DEVOLUTIVA_NOTIFICADA_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS;
					ls_idProceso             = lt_turno.getIdProceso();
					lth_turnoHistoria175     = lthd_DAO.findByIdTurnoEtapa(
						    ls_idTurno, EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS
						);

					{
						TurnoHistoria lth_turno205;

						lth_turno205 = new TurnoHistoria();

						lth_turno205.setIdSolicitud(ath_parametros.getIdSolicitud());
						lth_turno205.setIdEtapa(
						    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
						);

						lth_turno205 = lthd_DAO.findBySolicitudEtapa(lth_turno205);

						if(lth_turno205 != null)
						{
							String ls_renunciaTerminos;

							ls_renunciaTerminos     = StringUtils.getStringNotNull(lth_turno205.getRenunciaTerminos());
							lb_renunciaTerminos     = ls_renunciaTerminos.equals(EstadoCommon.S);
						}
					}

					if(
					    StringUtils.isValidString(ls_idProceso)
						    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
						    && (lth_turnoHistoria175 != null)
					)
					{
						Long ll_idMotivo175;

						ll_idMotivo175 = lth_turnoHistoria175.getIdMotivo();

						if(NumericUtils.isValidLong(ll_idMotivo175))
						{
							long ll_idMotivoTramite;

							ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo175);

							if(!lb_renunciaTerminos)
								ll_idMotivo = MotivoTramiteCommon.ACTO_ADMINISTRATIVO_NOTIFICADO_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS;
							else
							{
								if(ll_idMotivoTramite == MotivoTramiteCommon.AUTO_DE_PRUEBAS)
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_VENCIMIENTO_AUTO_DE_PRUEBAS;
								else
									ll_idMotivo = MotivoTramiteCommon.FINALIZACION_PROCESO_NOTIFICACIONES_POR_RENUNCIA_A_TERMINOS;
							}
						}
						else
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = StringUtils.getString(lth_turnoHistoria175.getIdTurnoHistoria());

							throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
						}
					}
					else if(
					    StringUtils.isValidString(ls_idProceso)
						    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
					)
					{
						String ls_maxIdSolicitud;

						ls_maxIdSolicitud = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
								                          .findMaxByIdSolicitud(ls_solicitud.getIdSolicitud());

						if(ls_maxIdSolicitud != null)
						{
							Solicitud ls_solicitud1;

							ls_solicitud1 = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_maxIdSolicitud);

							if(ls_solicitud1 != null)
							{
								String ls_proceso1;
								String ls_idSubproceso1;

								ls_proceso1          = StringUtils.getStringNotNull(ls_solicitud1.getIdProceso());
								ls_idSubproceso1     = StringUtils.getStringNotNull(ls_solicitud1.getIdSubproceso());

								if(ls_proceso1.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47))
								{
									RecursoDecision lrd_recursoDecision;

									lrd_recursoDecision = new RecursoDecision();

									lrd_recursoDecision.setIdTurno(ls_idTurno);

									lrd_recursoDecision = DaoCreator.getRecursoDecisionDAO(ldm_manager)
											                            .findByIdTurno(lrd_recursoDecision);

									if(lrd_recursoDecision != null)
									{
										String ls_idTipoDecisionRecurso;
										ls_idTipoDecisionRecurso = lrd_recursoDecision.getIdTipoDecisionRecurso();

										if(StringUtils.isValidString(ls_idTipoDecisionRecurso))
										{
											if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.NIEGA_RECURSO_DE_REPOSICION
												    )
											)
											{
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_EL_RECURSO_DE_REPOSICION_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA;
												else if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION_CON_SUBSIDIO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_RECURSO_REPOSICION_Y_CONCEDIENDO_APELACION;
											}
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.REVOCA_TOTALMENTE_DECISION
												    )
											)
											{
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION
													    )
													    || ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION_CON_SUBSIDIO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION;
											}
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.CONFIRMA_DECISION_Y_CONCEDE_APELACION
												    )
											)
												ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_RECURSO_REPOSICION_Y_CONCEDIENDO_APELACION;
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.REVOCA_PARCIALMENTE_DECISION_Y_CONCEDE_APELACION_EN_LO_DEMAS
												    )
											)
											{
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION
													    )
													    || ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION_CON_SUBSIDIO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.REVOCA_PARCIALMENTE_DECISION_Y_CONCEDE_APELACION_EN_LO_DEMAS;
											}
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.RECHAZO_DE_LOS_RECURSOS
												    )
											)
											{
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_EL_RECURSO_DE_REPOSICION_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA;
												else if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_REPOSICION_CON_SUBSIDIO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_RECURSO_REPOSICION_Y_CONCEDIENDO_APELACION;
											}
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.CONCEDE_RECURSO_APELACION
												    )
											)
											{
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION;
											}
											else if(
											    ls_idTipoDecisionRecurso.equalsIgnoreCase(
												        TipoDecisionRecursoCommon.RECHAZA_RECURSO_APELACION
												    )
											)
												if(
												    ls_idSubproceso1.equalsIgnoreCase(
													        SubProcesoCommon.RECURSO_DE_APELACION
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_RECURSO_APELACION;
										}
									}
								}
								else if(ls_proceso1.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48))
								{
									if(
									    ls_idSubproceso1.equals(ProcesoCommon.ID_SUBPROCESO_1)
										    || ls_idSubproceso1.equals(ProcesoCommon.ID_SUBPROCESO_2)
										    || ls_idSubproceso1.equals(ProcesoCommon.ID_SUBPROCESO_3)
									)
									{
										boolean       lb_etapa430;
										TurnoHistoria lth_th;

										lth_th     = lthd_DAO.findMaxByIdEtapaIdTurno(
											    EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA, ls_idTurno
											);

										lb_etapa430 = lth_th != null;

										if(!lb_etapa430)
											lth_th = lthd_DAO.findMaxByIdEtapaIdTurno(
												    EtapaCommon.ID_ETAPA_460, ls_idTurno
												);

										if(lth_th != null)
										{
											Long lL_idMotivo;
											int  ll_idMotivoAnt;

											ll_idMotivoAnt     = 0;
											lL_idMotivo        = lth_th.getIdMotivo();

											if(lL_idMotivo != null)
											{
												ll_idMotivoAnt = NumericUtils.getInt(lL_idMotivo);

												if(lb_etapa430)
												{
													switch(ll_idMotivoAnt)
													{
														case 10:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_110_ETAPA_240;

															break;

														case 20:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_120_ETAPA_240;

															break;

														case 30:
															ll_idMotivo = MotivoTramiteCommon.RESOLUCION_NEGANDO_RECURSO_APELACION;

															break;

														case 40:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_130_ETAPA_240;

															break;

														case 50:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_150_ETAPA_240;

															break;

														case 60:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_160_ETAPA_240;

															break;

														case 70:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_170_ETAPA_240;

															break;

														case 80:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_140_ETAPA_240;

															break;

														case 90:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_180_ETAPA_240;

															break;

														default:
															ll_idMotivo = 0L;

															break;
													}
												}
												else
												{
													switch(ll_idMotivoAnt)
													{
														case 20:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_190_ETAPA_240;

															break;

														case 80:
															ll_idMotivo = MotivoTramiteCommon.MOTIVO_200_ETAPA_240;

															break;

														default:
															ll_idMotivo = 0L;

															break;
													}
												}
											}
										}
									}
								}
							}
						}
					}

					if(lb_renunciaTerminos)
						ll_idMotivo = MotivoTramiteCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA;

					terminarTurnoHistoriaYCrearEtapa(
					    ath_parametros, ldm_manager, new MotivoTramite(EtapaCommon.NOTIFICADO, ll_idMotivo), as_userId,
					    as_ipRemota, EstadoCommon.TERMINADA
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.REALIZAR_NOTIFICACION, lb2be_e.getMessage(), as_userId, null,
			    (ath_parametros != null) ? StringUtils.getString(ath_parametros.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Validar personas notificadas.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarPersonasNotificadas(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_VALIDACION_PERSONAS_NOTIFICADAS_WS_INVOKE,
				    ConstanteCommon.JOB_VALIDACION_PERSONAS_NOTIFICADAS_LIMITE_INTENTOS,
				    EtapaCommon.VALIDACION_PERSONAS_Y_O_TERCEROS_NOTIFICADAS, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			validarPersonasNotificadas(lcth_parametros, as_remoteIp);
	}

	/**
	 * Validar personas notificadas.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarPersonasNotificadas(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_VALIDACION_PERSONAS_NOTIFICADAS_BLOQUEO;
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

				clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

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

				clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

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

				ldm_bitacora = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									validarPersonasNotificadas(lth_iterador, lbpd_bitacora, ls_userId, as_remoteIp);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_bitacora);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

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

					clh_LOGGER.error("validarPersonasNotificadas", lb2be_e);

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
	 * Validar personas notificadas.
	 *
	 * @param ath_parametros de ath parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarPersonasNotificadas(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, String as_userId, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			DaoCreator.getProcedimientosDAO(ldm_manager)
				          .procValidaNotificacionTerceros(ath_parametros.getIdTurnoHistoria(), as_userId, as_ipRemota);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.VALIDACION_PERSONAS_Y_O_TERCEROS_NOTIFICADAS, lb2be_e.getMessage(),
			    as_userId, null,
			    (ath_parametros != null) ? StringUtils.getString(ath_parametros.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
