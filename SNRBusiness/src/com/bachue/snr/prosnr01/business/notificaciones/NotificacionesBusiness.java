package com.bachue.snr.prosnr01.business.notificaciones;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.ByteArrayUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.owcc.cierreAperturaExpedientes.ClienteCierreAperturaExpedientes;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.business.entrega.EntregaBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.parametros.ParametrosCierreAperturaExpedienteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.notificaciones.IndiceElectronico;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de Notificaciones.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/03/2020
 */
public class NotificacionesBusiness extends EntregaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificacionesBusiness.class);

	/**
	 * Cargar datos notificaciones.
	 *
	 * @param ae_entrega de ae entrega
	 * @param al_idEtapa de al id etapa
	 * @return el valor de notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Notificacion cargarDatosNotificaciones(Entrega ae_entrega, long al_idEtapa)
	    throws B2BException
	{
		Notificacion ln_notificacion;

		ln_notificacion = null;

		if((ae_entrega != null) && (al_idEtapa > 0))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idTurno;

				ls_idTurno = ae_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

					if(lt_turno == null)
						throw new B2BException(ErrorKeys.TURNO_NO_EXISTE);
					else
					{
						ln_notificacion = new Notificacion();

						ln_notificacion.setFechaNotificacion(lt_turno.getFechaNotificacion());
						ln_notificacion.setTipoNotificacion(lt_turno.getIdTipoNotificacion());
						ln_notificacion.setDocumentosNotificaciones(
						    cargarDocumentosNotificaciones(ldm_manager, ls_idTurno)
						);
						ln_notificacion.setPersonaNotificacion(
						    cargarPersonaNotificacion(
						        ldm_manager, lt_turno.getIdProceso(), lt_turno.getIdSolicitud(), lt_turno.getIdTurno(),
						        ae_entrega.getTurnoHistoria()
						    )
						);
					}
				}
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
		}

		return ln_notificacion;
	}

	/**
	 * Cierre expediente.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cierreExpediente(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_CIERRE_EXPEDIENTE_WS_INVOKE,
				    ConstanteCommon.JOB_CIERRE_EXPEDIENTE_LIMITE_INTENTOS, EtapaCommon.CIERRE_EXPEDIENTE, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cierreExpediente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			cierreExpediente(lcth_data, as_remoteIp);
	}

	/**
	 * Cierre expediente.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cierreExpediente(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CIERRE_EXPEDIENTE_BLOQUEO;
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

				clh_LOGGER.error("cierreExpediente", lb2be_e);

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
				Constantes    lc_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lc_constant      = lcd_constant.findById(ls_constant);

				if(lc_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lc_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("cierreExpediente", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						BitacoraProcesoDAO lbp_DAO;

						lbp_DAO = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									cierreExpediente(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("cierreExpediente", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("cierreExpediente", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cierreExpediente", lb2be_e);

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

					clh_LOGGER.error("cierreExpediente", lb2be_e);

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
	 * Cierre expediente.
	 *
	 * @param abpd_bitacoraProcesoDAO de abpd bitacora proceso DAO
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void cierreExpediente(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;
				String ls_llave1;

				ll_idTurnoHistoria     = ath_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = ath_turnoHistoria.getIdTurno();
				ls_llave1              = StringUtils.getString(ll_idTurnoHistoria);

				if(NumericUtils.isValidLong(ll_idTurnoHistoria) && StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						String ls_idProceso;
						String ls_idSubproceso;

						ls_idProceso        = lt_turno.getIdProceso();
						ls_idSubproceso     = lt_turno.getIdSubProceso();

						if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso))
						{
							long             ll_idEtapa244;
							long             ll_idMotivoTerminar;
							String           ls_idSolicitud;
							TurnoHistoriaDAO lth_DAO;

							ll_idEtapa244           = EtapaCommon.CIERRE_EXPEDIENTE;
							ll_idMotivoTerminar     = 0;
							ls_idSolicitud          = lt_turno.getIdSolicitud();
							lth_DAO                 = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

							if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
							{
								Map<String, String> lmss_certificadosEspeciales;
								Map<String, String> lmss_certificadosInmediatos;

								lmss_certificadosEspeciales     = generarCodigos(
									    ConstanteCommon.SUBPROCESOS_CERTIFICADOS_ESPECIALES, ldm_manager
									);
								lmss_certificadosInmediatos     = generarCodigos(
									    ConstanteCommon.SUBPROCESOS_CERTIFICADOS_INMEDIATOS, ldm_manager
									);

								if(
								    CollectionUtils.isValidCollection(lmss_certificadosEspeciales)
									    && lmss_certificadosEspeciales.containsKey(ls_idSubproceso)
								)
								{
									TurnoHistoria lth_turnoHistoria190;

									lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
										    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
										);

									if(lth_turnoHistoria190 != null)
									{
										Long ll_idMotivo;

										ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo))
										{
											long ll_idMotivoTramite;

											ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

											if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CERTIFICADOS_CORREO_ELECTRONICO)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CERTIFICADOS_ORIP)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_CERTIFICADO_FINALIZADO_APROBADO;
											else if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS_ORIP)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS_CORRESPONDENCIA)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS_CORREO_ELECTRONICO)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_CERTIFICADO_FINALIZADO_NEGADO;
										}
										else
										{
											Object[] loa_arg;

											loa_arg        = new String[1];
											loa_arg[0]     = StringUtils.getString(
												    lth_turnoHistoria190.getIdTurnoHistoria()
												);

											throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
										}
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
								}

								if(
								    CollectionUtils.isValidCollection(lmss_certificadosInmediatos)
									    && lmss_certificadosInmediatos.containsKey(ls_idSubproceso)
								)
								{
									TurnoHistoria lth_turnoHistoria300;

									lth_turnoHistoria300 = lth_DAO.findByIdTurnoEtapa(
										    ls_idTurno, EtapaCommon.ID_ETAPA_300
										);

									if(lth_turnoHistoria300 != null)
									{
										Long ll_idMotivo;

										ll_idMotivo = lth_turnoHistoria300.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo))
										{
											long ll_idMotivoTramite;

											ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

											if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CERTIFICADOS_EN_LA_ORIP)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CERTIFICADOS_POR_CORREO_ELECTRONICO)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_CERTIFICADO_FINALIZADO_APROBADO;
										}
										else
										{
											Object[] loa_arg;

											loa_arg        = new String[1];
											loa_arg[0]     = StringUtils.getString(
												    lth_turnoHistoria300.getIdTurnoHistoria()
												);

											throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
										}
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
								}
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2))
							{
								Map<String, String> lmss_consultas;

								lmss_consultas = generarCodigos(ConstanteCommon.SUBPROCESOS_CONSULTAS, ldm_manager);

								if(
								    CollectionUtils.isValidCollection(lmss_consultas)
									    && lmss_consultas.containsKey(ls_idSubproceso)
								)
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										if(BooleanUtils.getBooleanValue(ls_solicitud.getEntidadExenta()))
										{
											TurnoHistoria lth_turnoHistoria41;

											lth_turnoHistoria41 = lth_DAO.findByIdTurnoEtapa(
												    ls_idTurno,
												    EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS
												);

											if(lth_turnoHistoria41 != null)
											{
												Long ll_idMotivo;

												ll_idMotivo = lth_turnoHistoria41.getIdMotivo();

												if(NumericUtils.isValidLong(ll_idMotivo))
												{
													long ll_idMotivoTramite;

													ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

													if(
													    (ll_idMotivoTramite == MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_EN_ORIP)
														    || (ll_idMotivoTramite == MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_EMAIL)
														    || (ll_idMotivoTramite == MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_CORRESPONDENCIA)
													)
														ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_CONSULTAS_FINALIZADO_APROBADO;
												}
												else
												{
													Object[] loa_arg;

													loa_arg        = new String[1];
													loa_arg[0]     = StringUtils.getString(
														    lth_turnoHistoria41.getIdTurnoHistoria()
														);

													throw new B2BException(
													    ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg
													);
												}
											}
											else
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
										}
										else
										{
											TurnoHistoria lth_turnoHistoria40;

											lth_turnoHistoria40 = lth_DAO.findByIdTurnoEtapa(
												    ls_idTurno, EtapaCommon.ID_ETAPA_40_GENERACION_CONSULTAS_INMEDIATOS
												);

											if(lth_turnoHistoria40 != null)
											{
												Long ll_idMotivo;

												ll_idMotivo = lth_turnoHistoria40.getIdMotivo();

												if(NumericUtils.isValidLong(ll_idMotivo))
												{
													long ll_idMotivoTramite;

													ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

													if(
													    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CONSULTAS_EN_LA_ORIP)
														    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_EMAIL)
														    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_CORRESPONDENCIA)
													)
														ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_CONSULTAS_FINALIZADO_APROBADO;
												}
												else
												{
													Object[] loa_arg;

													loa_arg        = new String[1];
													loa_arg[0]     = StringUtils.getString(
														    lth_turnoHistoria40.getIdTurnoHistoria()
														);

													throw new B2BException(
													    ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg
													);
												}
											}
											else
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
										}
									}
								}
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
							{
								TurnoHistoria lth_turnoHistoria195;

								lth_turnoHistoria195 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES
									);

								if(lth_turnoHistoria195 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria195.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORRESPONDENCIA)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORREO_ELECTRONICO)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.CORRECCION_EXTERNA_FINALIZADA_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORREO_ELECTRONICO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORRESPONDENCIA)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.CORRECCION_EXTERNA_NEGADA_FINALIZADA_244;
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = StringUtils.getString(
											    lth_turnoHistoria195.getIdTurnoHistoria()
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
							{
								TurnoHistoria lth_turnoHistoria190;

								lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
									);

								if(lth_turnoHistoria190 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_REPRODUCCION_CONSTANCIA_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_RESTITUCION)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_MEDIDA_CAUTELAR)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_RESTITUCION)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_RESTITUCION)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_DESISTIMIENTO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_DESISTIMIENTO)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_POR_DESISTIMIENTO_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.TESTAMENTO_ENTREGA_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.TESTAMENTO_ENTREGA_DIRECCION_RESIDENCIA_CORRESPONDENCIA_190)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.ENTREGA_FINALIZADA_TESTAMENTOS_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_ORIP_REPRODUCCION_TESTAMENTOS)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_REPRODUCCION_TESTAMENTOS)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_DIRECCIONES_REPRODUCCION_TESTAMENTOS)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.TESTAMENTO_ENTREGA_CORREO_ELECTRONICO)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.FINALIZACION_PROCESO_DE_TESTAMENTOS_244;
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = StringUtils.getString(
											    lth_turnoHistoria190.getIdTurnoHistoria()
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_37))
							{
								TurnoHistoria lth_turnoHistoria190;

								lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
									);

								if(lth_turnoHistoria190 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_GRABACION_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORREO_ELECTRONICO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRESPONDENCIA)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_DE_GRABACION_FINALIZADO_NEGADO_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORREO_ELECTRONICO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORRESPONDENCIA)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_DE_GRABACION_FINALIZADO_APROBADO_244;
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = StringUtils.getString(
											    lth_turnoHistoria190.getIdTurnoHistoria()
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39))
							{
								TurnoHistoria lth_turnoHistoria190;

								lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
									);

								if(lth_turnoHistoria190 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_DESISTIMIENTO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_DESISTIMIENTO)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_POR_DESISTIMIENTO_244;
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = StringUtils.getString(
											    lth_turnoHistoria190.getIdTurnoHistoria()
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5))
							{
								TurnoHistoria lth_turnoHistoria190;

								lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
									);

								if(lth_turnoHistoria190 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_ORIP)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_CORREO_ELECTRONICO_620)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_CORREO_ELECTRONICO_640)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_ORIP_244;
										else if(
										    (ll_idMotivoTramite == MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_610)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_630)
											    || (ll_idMotivoTramite == MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_CORREO_ELECTRONICO)
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_DE_COPIAS_FINALIZADO_APROBADO_244;
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = StringUtils.getString(
											    lth_turnoHistoria190.getIdTurnoHistoria()
											);

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
									}
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42))
							{
								TurnoHistoria lth_turnoHistoria200;

								lth_turnoHistoria200 = lth_DAO.findByIdTurnoEtapa(
									    ls_idTurno, EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION
									);

								if(lth_turnoHistoria200 != null)
								{
									Long ll_idMotivo;

									ll_idMotivo = lth_turnoHistoria200.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivo))
									{
										long ll_idMotivoTramite;

										ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

										if(
										    ll_idMotivoTramite == MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5
										)
											ll_idMotivoTerminar = MotivoTramiteCommon.PRORROGA_MAYOR_VALOR_FINALIZADA_244;
									}
								}
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38))
							{
								{
									TurnoHistoria lth_turnoHistoria190;

									lth_turnoHistoria190 = lth_DAO.findByIdTurnoEtapa(
										    ls_idTurno, EtapaCommon.ID_ETAPA_APROBACION
										);

									if(lth_turnoHistoria190 != null)
									{
										Long ll_idMotivo;

										ll_idMotivo = lth_turnoHistoria190.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo))
										{
											long ll_idMotivoTramite;

											ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

											if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_CORREO_ELECTRONICO)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_DIRECCION_CORRESPONDENCIA)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_ORIP)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_NEGADO;
										}
									}
								}

								{
									TurnoHistoria lth_turnoHistoria191;

									lth_turnoHistoria191 = lth_DAO.findByIdTurnoEtapa(
										    ls_idTurno, EtapaCommon.APROBACION_DE_TRASLADOS_OFICINA_DESTINO
										);

									if(lth_turnoHistoria191 != null)
									{
										Long ll_idMotivo;

										ll_idMotivo = lth_turnoHistoria191.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo))
										{
											long ll_idMotivoTramite;

											ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

											if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_30)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_40)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_50)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO;
										}
									}
								}

								{
									TurnoHistoria lth_turnoHistoria671;

									lth_turnoHistoria671 = lth_DAO.findByIdTurnoEtapa(
										    ls_idTurno,
										    EtapaCommon.APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO
										);

									if(lth_turnoHistoria671 != null)
									{
										Long ll_idMotivo;

										ll_idMotivo = lth_turnoHistoria671.getIdMotivo();

										if(NumericUtils.isValidLong(ll_idMotivo))
										{
											long ll_idMotivoTramite;

											ll_idMotivoTramite = NumericUtils.getLong(ll_idMotivo);

											if(
											    (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_30)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_40)
												    || (ll_idMotivoTramite == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_50)
											)
												ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO;
										}
									}
								}

								if(ls_idSubproceso.equalsIgnoreCase(IdentificadoresCommon.NUM3))
									ll_idMotivoTerminar = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_APROBADO_Y_EJECUTADO;
							}

							if(ll_idMotivoTerminar > 0)
							{
								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(ll_idEtapa244, ll_idMotivoTerminar), as_userId, as_remoteIp,
								    EstadoCommon.TERMINADA
								);

//							TODO Se comenta mientras se genera el cliente y los artefactos del servicio 
//								{
//									Map<String, String> lmss_parametros;
//
//									lmss_parametros = generarParametrosCierreApertura(
//										    lt_turno, ll_idEtapa244, ldm_manager
//										);
//
//									if(CollectionUtils.isValidCollection(lmss_parametros))
//										ClienteCierreAperturaExpedientes.CierreOAperturaExpediente(
//										    SistemaOrigenCommon.CORE, lmss_parametros,
//										    DaoCreator.getConstantesDAO(ldm_manager)
//											              .findString(
//											        ConstanteCommon.CIERRE_REAPERTURA_EXPEDIENTES_OWCC_ENDPOINT
//											    ), IdentificadoresCommon.CIERRE
//										);
//								}
							}
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_llave1;

								throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.CIERRE_DE_EXPEDIENTE, lb2be_e.getMessage(), as_userId,
			    as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Busca los casos en estado aut para que el job los procese.
	 *
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void envioDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIAR_DOCUMENTO_WS_INVOKE, ConstanteCommon.JOB_ENVIAR_DOCUMENTO_LIMITE_INTENTOS,
				    EtapaCommon.ENVIO_DOCUMENTO_ELECTRONICO_OWCC, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			envioDocumentoElectronico(lcth_data, as_remoteIp);
	}

	/**
	 * Bloquea el JOB e invoca el proceso.
	 *
	 * @param acth_parametros Colección de casos a realizar el proceso
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void envioDocumentoElectronico(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIAR_DOCUMENTO_BLOQUEO;
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

				clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

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
				Constantes    lc_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lc_constant      = lcd_constant.findById(ls_constant);

				if(lc_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lc_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						BitacoraProcesoDAO lbp_DAO;

						lbp_DAO = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									envioDocumentoElectronico(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

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

					clh_LOGGER.error("envioDocumentoElectronico", lb2be_e);

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
	 * Método encargado de ejecutar el procedimiento de recepcion documentos.
	 *
	 * @param abpd_bitacoraProcesoDAO DAO de bitacoraProceso
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia
	 * @param as_remoteIp Variable que contiene la ip
	 * @param as_userId Variable que contiene el usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void envioDocumentoElectronico(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					BigDecimal lbd_idAnterior;

					lbd_idAnterior = ath_turnoHistoria.getIdAnterior();

					if(NumericUtils.isValidBigDecimal(lbd_idAnterior))
					{
						TurnoHistoriaDAO lthd_DAO;
						TurnoHistoria    lth_anterior;

						lthd_DAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						lth_anterior     = lthd_DAO.findById(NumericUtils.getLongWrapper(lbd_idAnterior));

						if(lth_anterior != null)
						{
							BigDecimal lbd_idEtapa;

							lbd_idEtapa = lth_anterior.getIdEtapa();

							if(lbd_idEtapa != null)
							{
								if(
								    lbd_idEtapa.compareTo(
									        BigDecimal.valueOf(EtapaCommon.FIRMAR_DOCUMENTO_ELECTRONICO_OWCC)
									    ) == 0
								)
								{
									String ls_estadoActividad;

									ls_estadoActividad = lth_anterior.getEstadoActividad();

									if(StringUtils.isValidString(ls_estadoActividad))
									{
										if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
										{
											DocumentosSalidaDAO ldsd_DAO;
											DocumentosSalida    lds_documentoSalida;

											ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
											lds_documentoSalida     = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(
												    new DocumentosSalida(
												        NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria()),
												        TipoDocumentalCommon.INDICE_ELECTRONICO
												    )
												);

											if(lds_documentoSalida != null)
											{
												enviarGestorDocumental(
												    lds_documentoSalida, abpd_bitacoraProcesoDAO,
												    DaoCreator.getConstantesDAO(ldm_manager)
													              .findString(
													        ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													    ), as_userId, as_remoteIp, ldm_manager
												);

												{
													TurnoHistoria lth_historia;

													lth_historia = terminarTurnoHistoriaYCrearEtapa(
														    ath_turnoHistoria, ldm_manager,
														    new MotivoTramite(
														        EtapaCommon.ENVIO_DOCUMENTO_ELECTRONICO_OWCC, 10L
														    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
														);

													if(lth_historia != null)
														actualizarIndiceElectronicoDocSalida(
														    new IndiceElectronico(
														        lds_documentoSalida, ll_idTurnoHistoria,
														        lth_historia.getIdTurnoHistoria(),
														        IdentificadoresCommon.ENVIO_DOCUMENTO_ELECTRONICO
														    ), as_remoteIp, as_userId, abpd_bitacoraProcesoDAO,
														    ldm_manager
														);
													else
														throw new B2BException(
														    ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA
														);
												}
											}
											else
												throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_ESTADO_ACTIVIDAD_NO_VALIDO);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_ESTADO_ACTIVIDAD_NO_VALIDO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.ENVIO_DOCUMENTO_ELECTRONICO, lb2be_e.getMessage(),
			    as_userId, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Find inbox by turno nir notificacion personal.
	 *
	 * @param atc_tc de atc tc
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteCantidad> findInboxByTurnoNirNotificacionPersonal(TramiteCantidad atc_tc)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lctc_tramitesCantidad;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lctc_tramitesCantidad     = null;

		try
		{
			if(atc_tc != null)
			{
				String ls_etapasNotificacion;

				ls_etapasNotificacion = DaoCreator.getConstantesDAO(ldm_manager)
						                              .findString(ConstanteCommon.ETAPAS_NOTIFICACION_PERSONAL);

				if(!StringUtils.isValidString(ls_etapasNotificacion))
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = ConstanteCommon.ETAPAS_NOTIFICACION_PERSONAL;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
				}
				else
				{
					lctc_tramitesCantidad = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                              .bandejaNotificacionPersonalOrip(
							    atc_tc, ls_etapasNotificacion.split(IdentificadoresCommon.SIMBOLO_COMA_TXT)
							);

					if(!CollectionUtils.isValidCollection(lctc_tramitesCantidad))
						throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInboxByTurnoNirNotificacionPersonal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_tramitesCantidad;
	}

	/**
	 * Busca los casos en estado aut para que el job los procese.
	 *
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmarDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_FIRMAR_DOCUMENTO_ELECTRONICO_WS_INVOKE,
				    ConstanteCommon.JOB_FIRMAR_DOCUMENTO_ELECTRONICO_LIMITE_INTENTOS,
				    EtapaCommon.FIRMAR_DOCUMENTO_ELECTRONICO_OWCC, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			firmarDocumentoElectronico(lcth_data, as_remoteIp);
	}

	/**
	 * Bloquea el JOB e invoca el proceso.
	 *
	 * @param acth_parametros Colección de casos a realizar el proceso
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmarDocumentoElectronico(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_FIRMAR_DOCUMENTO_ELECTRONICO_BLOQUEO;
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

				clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

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
				Constantes    lc_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lc_constant      = lcd_constant.findById(ls_constant);

				if(lc_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lc_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						BitacoraProcesoDAO lbp_DAO;

						lbp_DAO = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									firmarDocumentoElectronico(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

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

					clh_LOGGER.error("firmarDocumentoElectronico", lb2be_e);

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
	 * Método encargado de ejecutar el procedimiento de recepcion documentos.
	 *
	 * @param abpd_bitacoraProcesoDAO DAO de bitacoraProceso
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia
	 * @param as_remoteIp Variable que contiene la ip
	 * @param as_userId Variable que contiene el usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmarDocumentoElectronico(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					String     ls_idTurnoHistoria;
					BigDecimal lbd_idAnterior;

					ls_idTurnoHistoria     = StringUtils.getString(ll_idTurnoHistoria);
					lbd_idAnterior         = ath_turnoHistoria.getIdAnterior();

					if(NumericUtils.isValidBigDecimal(lbd_idAnterior))
					{
						TurnoHistoriaDAO lthd_DAO;
						TurnoHistoria    lth_anterior;

						lthd_DAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						lth_anterior     = lthd_DAO.findById(NumericUtils.getLongWrapper(lbd_idAnterior));

						if(lth_anterior != null)
						{
							BigDecimal lbd_idEtapa;

							lbd_idEtapa = lth_anterior.getIdEtapa();

							if(lbd_idEtapa != null)
							{
								if(
								    lbd_idEtapa.compareTo(
									        BigDecimal.valueOf(EtapaCommon.SOLICITAR_DOCUMENTO_ELECTRONICO_OWCC)
									    ) == 0
								)
								{
									String ls_estadoActividad;

									ls_estadoActividad = lth_anterior.getEstadoActividad();

									if(StringUtils.isValidString(ls_estadoActividad))
									{
										if(ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
										{
											DocumentosSalidaDAO ldsd_DAO;
											DocumentosSalida    lds_documentoSalida;

											ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
											lds_documentoSalida     = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(
												    new DocumentosSalida(
												        NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria()),
												        TipoDocumentalCommon.INDICE_ELECTRONICO
												    )
												);

											if(lds_documentoSalida != null)
											{
												ImagenesDAO lid_DAO;
												Imagenes    li_imagen;

												lid_DAO       = DaoCreator.getImagenesDAO(ldm_manager);
												li_imagen     = lid_DAO.findById(
													    new Imagenes(
													        NumericUtils.getLong(lds_documentoSalida.getIdImagen())
													    )
													);

												if(li_imagen != null)
												{
													byte[] lba_imagen;

													lba_imagen = li_imagen.getImagenBlob();

													if(ByteArrayUtils.isValidArray(lba_imagen))
													{
														lba_imagen = firmarDocumentos(
															    lba_imagen, ldm_manager, abpd_bitacoraProcesoDAO,
															    ls_idTurnoHistoria, as_remoteIp, as_userId
															);

														if(ByteArrayUtils.isValidArray(lba_imagen))
														{
															li_imagen.setImagenBlob(lba_imagen);
															lid_DAO.insertOrUpdate(li_imagen, false);

															{
																TurnoHistoria lth_historia;
																lth_historia = terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.FIRMAR_DOCUMENTO_ELECTRONICO_OWCC,
																	        10L
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);

																if(lth_historia != null)
																	actualizarIndiceElectronicoDocSalida(
																	    new IndiceElectronico(
																	        lds_documentoSalida, ll_idTurnoHistoria,
																	        lth_historia.getIdTurnoHistoria(),
																	        IdentificadoresCommon.FIRMAR_DOCUMENTO_ELECTRONICO
																	    ), as_remoteIp, as_userId,
																	    abpd_bitacoraProcesoDAO, ldm_manager
																	);
															}
														}
														else
															throw new B2BException(ErrorKeys.ERROR_IMAGEN_NO_VALIDA);
													}
													else
														throw new B2BException(ErrorKeys.ERROR_IMAGEN_NO_VALIDA);
												}
												else
													throw new B2BException(ErrorKeys.ERROR_SIN_ID_IMAGEN);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_ESTADO_ACTIVIDAD_NO_VALIDO);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_ESTADO_ACTIVIDAD_NO_VALIDO);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.FIRMAR_DOCUMENTO_ELECTRONICO, lb2be_e.getMessage(),
			    as_userId, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Generar acta notificacion personal.
	 *
	 * @param as_turno Argumento de tipo <code>String</code> que contiene el turno de la operación.
	 * @param as_solicitud Argumento de tipo <code>String</code> que contiene la solicitud de la operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip desde donde se realiza la operación.
	 * @param aofd_param Argumento de tipo <code>ObtenerFirmaDTO</code>.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void generarActaNotificacionPersonal(
	    String as_turno, Solicitud as_solicitud, String as_userId, String as_remoteIp, ObtenerFirmaDTO aofd_param
	)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_turno) && (as_solicitud != null)
			    && validarDatosAuditoria(as_userId, as_remoteIp) && (aofd_param != null)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_historia;

				lth_historia = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(as_turno);

				if(lth_historia != null)
				{
					String ls_idProceso;

					ls_idProceso = lth_historia.getIdProceso();

					if(StringUtils.isValidString(ls_idProceso))
					{
						DocumentosSalida lds_documento;

						lds_documento = generarDocumentoSalidaBase(lth_historia, as_solicitud, true, true, ldm_manager);

						agregarTipoDocumentalEnDocumentoSalida(lds_documento, false, false, true, false);

						switch(ls_idProceso)
						{
							case ProcesoCommon.ID_PROCESO_6:
								generarDocumentoCitatorio(
								    lth_historia, as_solicitud, null, lds_documento,
								    ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_PERSONAL_NOTDEV, as_userId, as_remoteIp,
								    ldm_manager, aofd_param
								);

								break;

							case ProcesoCommon.ID_PROCESO_3:
								generarDocumentoCitatorio(
								    lth_historia, as_solicitud, null, lds_documento,
								    ConstanteCommon.PLANTILLA_ACTA_NOTIFICACION_CORREO, as_userId, as_remoteIp,
								    ldm_manager, aofd_param
								);

								break;

							default:
								break;
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarActaNotificacionPersonal", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Metodo encargado de generar los parametros para un cierre apertura.
	 *
	 * @param at_turno Argumento de tipo <code>Turno</code> que contiene el turno de la operación.
	 * @param ai_etapa Argumento de tipo <code>long</code> que contiene la id etapa de la operación.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que contiene la conexión hacia la base de datos.
	 * @return Map de String, String de bytes que contiene la informacion generada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> generarParametrosCierreApertura(Turno at_turno, long ai_etapa, DAOManager adm_manager)
	    throws B2BException
	{
		Map<String, String> lmss_parametros;

		lmss_parametros = new HashMap<String, String>();

		if((at_turno != null) && (ai_etapa > 0) && (adm_manager != null))
		{
			try
			{
				{
					String ls_idTurno;

					ls_idTurno = at_turno.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
						lmss_parametros.put(ParametrosCierreAperturaExpedienteCommon.TURNO, ls_idTurno);
					else
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);
				}

				{
					String ls_idCirculo;

					ls_idCirculo = at_turno.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						CirculoRegistral lcr_circulo;

						lcr_circulo = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

						if(lcr_circulo != null)
						{
							{
								String ls_nombreCirculo;

								ls_nombreCirculo = lcr_circulo.getNombre();

								if(StringUtils.isValidString(ls_nombreCirculo))
									lmss_parametros.put(
									    ParametrosCierreAperturaExpedienteCommon.ORIP, ls_nombreCirculo
									);
								else
								{
									Object[] loa_args;

									loa_args        = new String[1];
									loa_args[0]     = ls_idCirculo;

									throw new B2BException(ErrorKeys.ERROR_CIRCULO_SIN_NOMBRE, loa_args);
								}
							}

							lmss_parametros.put(ParametrosCierreAperturaExpedienteCommon.CODIGO_ORIP, ls_idCirculo);
						}
						else
						{
							Object[] loa_args;

							loa_args        = new String[1];
							loa_args[0]     = ls_idCirculo;

							throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_NO_ENCONTRADO, loa_args);
						}
					}
				}

				{
					String ls_idSolicitud;

					ls_idSolicitud = at_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							String ls_nir;

							ls_nir = ls_solicitud.getNir();

							if(StringUtils.isValidString(ls_nir))
								lmss_parametros.put(ParametrosCierreAperturaExpedienteCommon.NIR, ls_nir);
						}
						else
						{
							Object[] loa_args;

							loa_args        = new String[1];
							loa_args[0]     = ls_idSolicitud;

							throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_SOLICITUD_ACC, loa_args);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_SOLICITUD_VALIDO);
				}

				lmss_parametros.put(
				    ParametrosCierreAperturaExpedienteCommon.CODIGO_ETAPA, StringUtils.getString(ai_etapa)
				);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarParametrosCierreApertura", lb2be_e);

				throw lb2be_e;
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		return lmss_parametros;
	}

	/**
	 * Salvar notificacion.
	 *
	 * @param al_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarNotificacion(Long al_turnoHistoria, String as_remoteIp, String as_userId)
	    throws B2BException
	{
		if(al_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					BigDecimal lbd_idEtapa;

					lbd_idEtapa = lth_turnoHistoria.getIdEtapa();

					if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
					{
						long ll_motivo;

						ll_motivo = MotivoTramiteCommon.DEFAULT;

						{
							boolean lb_205;
							String  ls_idProceso;

							lb_205           = NumericUtils.getInt(lbd_idEtapa) == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA;
							ls_idProceso     = lth_turnoHistoria.getIdProceso();

							if(StringUtils.isValidString(ls_idProceso))
							{
								if(lb_205)
								{
									switch(ls_idProceso)
									{
										case ProcesoCommon.ID_PROCESO_6:
											ll_motivo = MotivoTramiteCommon.NOTA_DEVOLUTIVA_NOTIFICADA;

											break;

										case ProcesoCommon.ID_PROCESO_3:
											ll_motivo = MotivoTramiteCommon.NOTA_DEVOLUTIVA_NOTIFICACION;

											break;

										default:
											break;
									}
								}
								else
									ll_motivo = MotivoTramiteCommon.NOTIFICADO_PERSONAL;
							}
						}

						if(ll_motivo != MotivoTramiteCommon.DEFAULT)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager,
							    new MotivoTramite(NumericUtils.getLong(lbd_idEtapa), ll_motivo), as_userId, as_remoteIp,
							    EstadoCommon.TERMINADA, false, true
							);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarNotificacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Bloquea el JOB e invoca el proceso.
	 *
	 * @param acth_parametros Colección de casos a realizar el proceso
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void solicitarDocumentoElectronico(
	    Collection<TurnoHistoria> acth_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_SOLICITUD_DOCUMENTO_ELECTRONICO_BLOQUEO;
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

				clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);

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
				Constantes    lc_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lc_constant      = lcd_constant.findById(ls_constant);

				if(lc_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lc_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						BitacoraProcesoDAO lbp_DAO;

						lbp_DAO = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									solicitarDocumentoElectronico(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);

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

					clh_LOGGER.error("solicitarDocumentoElectronico", lb2be_e);

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
	 * Método encargado de ejecutar el procedimiento de recepcion documentos.
	 *
	 * @param abpd_bitacoraProcesoDAO DAO de bitacoraProceso
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia
	 * @param as_remoteIp Variable que contiene la ip
	 * @param as_userId Variable que contiene el usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void solicitarDocumentoElectronico(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					byte[] lba_archivo;

					lba_archivo = obtenerArchivo(ldm_manager, ath_turnoHistoria);

					if(ByteArrayUtils.isValidArray(lba_archivo))
					{
						long ll_idImagen;

						ll_idImagen = 0;

						{
							Imagenes li_imagen;

							li_imagen = new Imagenes();

							li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagen.setImagenBlob(lba_archivo);
							li_imagen.setIdUsuarioCreacion(as_userId);
							li_imagen.setIpCreacion(as_remoteIp);

							ll_idImagen = DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, true);
						}

						if(ll_idImagen > NumericUtils.DEFAULT_LONG_VALUE)
						{
							DocumentosSalidaDAO ldsd_DAO;
							DocumentosSalida    lds_insert;

							ldsd_DAO       = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
							lds_insert     = new DocumentosSalida();

							lds_insert.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
							lds_insert.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_insert.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
							lds_insert.setIdTurno(ath_turnoHistoria.getIdTurno());
							lds_insert.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());
							lds_insert.setIdTipoDocumental(TipoDocumentalCommon.INDICE_ELECTRONICO);
							lds_insert.setTipo(TipoArchivoCommon.INDICE_ELECTRONICO);
							lds_insert.setEstado(EstadoCommon.ACTIVO);
							lds_insert.setRepositorio(RepositorioCommon.FINAL);
							lds_insert.setDocumentoFinal(EstadoCommon.N);
							lds_insert.setIdEcm(EstadoCommon.POR_FIRMAR);
							lds_insert.setIdNombreDocumento(EstadoCommon.POR_FIRMAR);
							lds_insert.setIdUsuarioCreacion(as_userId);
							lds_insert.setIpCreacion(as_remoteIp);

							ldsd_DAO.insertOrUpdate(lds_insert, true);

							{
								TurnoHistoria lth_historia;

								lth_historia = terminarTurnoHistoriaYCrearEtapa(
									    ath_turnoHistoria, ldm_manager,
									    new MotivoTramite(EtapaCommon.SOLICITAR_DOCUMENTO_ELECTRONICO_OWCC, 10L),
									    as_userId, as_remoteIp, EstadoCommon.TERMINADA
									);

								if(lth_historia != null)
									actualizarIndiceElectronicoDocSalida(
									    new IndiceElectronico(
									        lds_insert, ll_idTurnoHistoria, lth_historia.getIdTurnoHistoria(),
									        IdentificadoresCommon.SOLICITAR_DOCUMENTO_ELECTRONICO
									    ), as_remoteIp, as_userId, abpd_bitacoraProcesoDAO, ldm_manager
									);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INSERTANDO_IMAGEN);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_OBTENER_DOCUMENTO_OWCC);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.SOLICITAR_DOCUMENTO_ELECTRONICO, lb2be_e.getMessage(),
			    as_userId, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Busca los casos en estado aut para que el job los procese.
	 *
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void solicitudDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_SOLICITUD_DOCUMENTO_ELECTRONICO_WS_INVOKE,
				    ConstanteCommon.JOB_SOLICITUD_DOCUMENTO_ELECTRONICO_LIMITE_INTENTOS,
				    EtapaCommon.SOLICITAR_DOCUMENTO_ELECTRONICO_OWCC, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("solicitudDocumentoElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			solicitarDocumentoElectronico(lcth_data, as_remoteIp);
	}

	/**
	 * Actualizar indice electronico doc salida.
	 *
	 * @param aie_indice de aie indice
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @param abpd_bitacoraProcesoDAO de abpd bitacora proceso DAO
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void actualizarIndiceElectronicoDocSalida(
	    IndiceElectronico aie_indice, String as_remoteIp, String as_userId, BitacoraProcesoDAO abpd_bitacoraProcesoDAO,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if(
		    (aie_indice != null) && StringUtils.isValidString(as_remoteIp) && StringUtils.isValidString(as_userId)
			    && (abpd_bitacoraProcesoDAO != null) && (adm_manager != null)
		)
		{
			DocumentosSalidaDAO ldsd_DAO;
			Long                ll_turnoHistoria;

			ldsd_DAO             = DaoCreator.getDocumentosSalidaDAO(adm_manager);
			ll_turnoHistoria     = aie_indice.getIdTurnoHistoriaNuevo();

			if(NumericUtils.isValidLong(ll_turnoHistoria))
			{
				DocumentosSalida lds_documentosSalida;

				lds_documentosSalida = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(
					    aie_indice.getDocumentoSalidaActualizar()
					);

				if(lds_documentosSalida != null)
				{
					lds_documentosSalida.setIdTurnoHistoria(NumericUtils.getInteger(ll_turnoHistoria));
					lds_documentosSalida.setIdUsuarioModificacion(lds_documentosSalida.getIdUsuarioCreacion());
					lds_documentosSalida.setIpModificacion(lds_documentosSalida.getIpCreacion());

					ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
		}
	}

	/**
	 * Cargar persona notificacion.
	 *
	 * @param adm_manager de adm manager
	 * @param as_idProceso de as id proceso
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idTurno de as id turno
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private Collection<Persona> cargarPersonaNotificacion(
	    DAOManager adm_manager, String as_idProceso, String as_idSolicitud, String as_idTurno, Long al_idTurnoHistoria
	)
	    throws B2BException
	{
		Collection<Persona> lcp_notificados;

		lcp_notificados = null;

		if(adm_manager != null)
		{
			try
			{
				if(StringUtils.isValidString(as_idProceso))
				{
					if(StringUtils.isValidString(as_idTurno))
					{
						TurnoHistoriaDAO lthd_DAO;

						lthd_DAO            = DaoCreator.getTurnoHistoriaDAO(adm_manager);
						lcp_notificados     = new ArrayList<Persona>();

						switch(as_idProceso)
						{
							case ProcesoCommon.ID_PROCESO_6:

								if(StringUtils.isValidString(as_idSolicitud))
								{
									SolicitudDAO lsd_DAO;
									Solicitud    ls_solicitud;

									lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);
									ls_solicitud     = lsd_DAO.findById(as_idSolicitud);

									if(ls_solicitud != null)
									{
										PersonaDAO lpd_DAO;

										lpd_DAO = DaoCreator.getPersonaDAO(adm_manager);

										if(
										    lthd_DAO.tieneEtapa(
											        as_idTurno,
											        EtapaCommon.ID_ETAPA_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS
											    )
										)
										{
											Collection<Solicitud> lcs_vinculadas;

											lcs_vinculadas = lsd_DAO.findAssociateByIdSolicitudProceso(
												    as_idSolicitud, ProcesoCommon.ID_PROCESO_47
												);

											if(CollectionUtils.isValidCollection(lcs_vinculadas))
											{
												for(Solicitud ls_solicitudTemp : lcs_vinculadas)
												{
													if(ls_solicitudTemp != null)
													{
														Persona lp_personaTPM;

														lp_personaTPM = lpd_DAO.findById(
															    ls_solicitudTemp.getIdPersona()
															);

														if(lp_personaTPM != null)
															lcp_notificados.add(lp_personaTPM);
													}
												}
											}
										}
										else
										{
											Persona lp_persona;

											lp_persona = lpd_DAO.findById(ls_solicitud.getIdPersona());

											if(lp_persona != null)
												lcp_notificados.add(lp_persona);
										}
									}
									else
									{
										Object[] loa_args;

										loa_args        = new String[1];
										loa_args[0]     = as_idSolicitud;

										throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_SOLICITUD_ACC, loa_args);
									}
								}

								break;

							case ProcesoCommon.ID_PROCESO_3:

								TurnoHistoria lth_historia;
								lth_historia = lthd_DAO.findById(al_idTurnoHistoria);

								if(lth_historia != null)
								{
									String ls_idDocumentosSalida;

									ls_idDocumentosSalida = lth_historia.getIdDocumentoSalida();

									if(StringUtils.isValidString(ls_idDocumentosSalida))
									{
										DocumentosSalida lds_documento;

										lds_documento = DaoCreator.getDocumentosSalidaDAO(adm_manager)
												                      .findById(
												    NumericUtils.getLong(ls_idDocumentosSalida)
												);

										if(lds_documento != null)
										{
											String ls_destinatario;

											ls_destinatario = lds_documento.getDestinatario();

											if(StringUtils.isValidString(ls_destinatario))
											{
												Persona lp_temporal;

												lp_temporal = new Persona();

												lp_temporal.setNombreCompleto(ls_destinatario);
												lcp_notificados.add(lp_temporal);
											}
											else
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_DESTINATARIO);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);
								}

								break;

							default:
								break;
						}

						if(lcp_notificados.isEmpty())
							lcp_notificados = null;
					}
					else
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_PROCESO);
			}

			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarPersonaNotificacion", lb2be_e);

				throw lb2be_e;
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		return lcp_notificados;
	}

	/**
	 * Firmar documentos.
	 *
	 * @param aba_documento de aba documento
	 * @param adm_manager de adm manager
	 * @param abpd_bitacoraProcesoDAO de abpd bitacora proceso DAO
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] firmarDocumentos(
	    byte[] aba_documento, DAOManager adm_manager, BitacoraProcesoDAO abpd_bitacoraProcesoDAO,
	    String as_idTurnoHistoria, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(
		    ByteArrayUtils.isValidArray(aba_documento) && (adm_manager != null) && (abpd_bitacoraProcesoDAO != null)
			    && StringUtils.isValidString(as_idTurnoHistoria)
		)
		{
			try
			{
				Constantes lc_usuarioFirma;

				lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
						                        .findByIdWithImage(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

				if(lc_usuarioFirma != null)
				{
					byte[] lba_firma;

					lba_firma = lc_usuarioFirma.getImagenes();

					if(ByteArrayUtils.isValidArray(lba_firma))
					{
						PdfReader      lpr_reader;
						Image          li_image;
						int            li_pagina;
						OutputStream   los_out;
						PdfStamper     lps_pdfStamper;
						PdfContentByte lpc_content;

						lpr_reader         = new PdfReader(aba_documento);
						li_image           = Image.getInstance(lba_firma);
						li_pagina          = lpr_reader.getNumberOfPages();
						los_out            = new ByteArrayOutputStream();
						lps_pdfStamper     = new PdfStamper(lpr_reader, los_out);
						lpc_content        = lps_pdfStamper.getOverContent(li_pagina);
						li_image.setAbsolutePosition(((li_image.getWidth() / 2)), 100);

						lpc_content.addImage(li_image);
						lpr_reader.close();
						lps_pdfStamper.close();

						if(los_out instanceof ByteArrayOutputStream)
						{
							ByteArrayOutputStream lbaos_stream;

							lbaos_stream     = (ByteArrayOutputStream)los_out;

							lba_return = lbaos_stream.toByteArray();

							los_out.close();
						}
						else
							escribirEnBitacoraProceso(
							    abpd_bitacoraProcesoDAO, IdentificadoresCommon.FIRMAR_DOCUMENTO_ELECTRONICO,
							    addMessage(ErrorKeys.ERROR_IMAGEN_NO_VALIDA, true), as_userId, as_remoteIp,
							    as_idTurnoHistoria
							);
					}
					else
						escribirEnBitacoraProceso(
						    abpd_bitacoraProcesoDAO, IdentificadoresCommon.FIRMAR_DOCUMENTO_ELECTRONICO,
						    addMessage(ErrorKeys.ERROR_IMAGEN_NO_VALIDA, true), as_userId, as_remoteIp,
						    as_idTurnoHistoria
						);
				}
				else
				{
					Object[] loa_args;

					loa_args        = new String[1];
					loa_args[0]     = ConstanteCommon.USUARIO_FIRMA_SUSPENSION;

					escribirEnBitacoraProceso(
					    abpd_bitacoraProcesoDAO, IdentificadoresCommon.FIRMAR_DOCUMENTO_ELECTRONICO,
					    addMessage(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args, true), as_userId, as_remoteIp,
					    as_idTurnoHistoria
					);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("firmarDocumentos", lb2be_e);

				throw lb2be_e;
			}
			catch(IOException lioe_e)
			{
				clh_LOGGER.error("firmarDocumentos", lioe_e);

				throw new B2BException(cs_IOEXCEPTION, lioe_e);
			}
			catch(BadElementException lioe_e)
			{
				clh_LOGGER.error("firmarDocumentos", lioe_e);

				throw new B2BException(cs_BAD_ELEMENT_EXCEPTION, lioe_e);
			}
			catch(DocumentException lioe_e)
			{
				clh_LOGGER.error("firmarDocumentos", lioe_e);

				throw new B2BException(cs_DOCUMENT_EXCEPTION, lioe_e);
			}
		}

		return lba_return;
	}

	/**
	 * Obtener archivo.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] obtenerArchivo(DAOManager adm_manager, TurnoHistoria ath_historia)
	    throws B2BException
	{
		byte[] lba_imagen;

		lba_imagen = null;

		if((adm_manager != null) && (ath_historia != null))
		{
			try
			{
				Map<String, String> lmss_parametros;

				lmss_parametros = new HashMap<String, String>();

				lmss_parametros.put(ParametrosCierreAperturaExpedienteCommon.TURNO, ath_historia.getIdTurno());

				lba_imagen = ClienteCierreAperturaExpedientes.CierreOAperturaExpediente(
					    SistemaOrigenCommon.CORE, lmss_parametros,
					    DaoCreator.getConstantesDAO(adm_manager)
						              .findString(ConstanteCommon.CIERRE_REAPERTURA_EXPEDIENTES_OWCC_ENDPOINT),
					    IdentificadoresCommon.OBTENER_INDICE
					);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("obtenerArchivo", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_imagen;
	}
}
