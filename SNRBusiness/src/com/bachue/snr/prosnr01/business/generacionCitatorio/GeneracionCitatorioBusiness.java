package com.bachue.snr.prosnr01.business.generacionCitatorio;

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
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Clase para el manejo de la logica de negocio de generación citatorio.
 *
 * @author Manuel Blanco
 */
public class GeneracionCitatorioBusiness extends BaseCorrespondenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GeneracionCitatorioBusiness.class);

	/**
	 * Genera el citatorio o la notificación y envia el caso a una etapa de entrega.
	 *
	 * @param ath_turnoHistoria Objeto contenedor de la información del caso
	 * @param abpd_DAO Objeto contenedor de la conexión a la tabla de bitacora para el registro de errores
	 * @param as_endpoint de as endpoint
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_userId de as user id
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public synchronized void enviarCitatorioNotificacion(
	    TurnoHistoria ath_turnoHistoria, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			Solicitud    ls_solicitud;
			SolicitudDAO lsd_DAO;

			lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
			ls_solicitud     = lsd_DAO.findById(ath_turnoHistoria.getIdSolicitud());

			if(ls_solicitud != null)
			{
				boolean lb_notificacion;
				boolean lb_procesoCorrecciones;

				{
					String ls_medioNotificar;
					String ls_idSolicitudAsociada;

					ls_medioNotificar          = StringUtils.getStringNotNull(
						    ls_solicitud.getIdAutorizacionNotificacion()
						);
					lb_notificacion            = ls_medioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO);
					ls_idSolicitudAsociada     = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
							                                   .findMaxByIdSolicitud(ls_solicitud.getIdSolicitud());

					if(StringUtils.isValidString(ls_idSolicitudAsociada))
					{
						Solicitud ls_solicitudAsociada;

						ls_solicitudAsociada = lsd_DAO.findById(ls_idSolicitudAsociada);

						if(ls_solicitudAsociada != null)
						{
							String ls_idProceso;

							ls_idProceso = ls_solicitudAsociada.getIdProceso();

							if(StringUtils.isValidString(ls_idProceso))
							{
								if(
								    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
									    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48)
								)
								{
									String ls_medioNotificarAsociado;

									ls_medioNotificarAsociado     = StringUtils.getStringNotNull(
										    ls_solicitudAsociada.getIdAutorizacionNotificacion()
										);
									lb_notificacion               = ls_medioNotificarAsociado.equals(
										    MedioNotificarCommon.CORREO_ELECTRONICO
										);
								}
							}
						}
					}
				}

				lb_procesoCorrecciones = StringUtils.getStringNotNull(ls_solicitud.getIdProceso())
						                                .equals(ProcesoCommon.ID_PROCESO_3);

				{
					TurnoHistoriaDAO lthd_turnoHistoriaDAO;
					TurnoHistoria    lth_turnoAnterior;

					lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					lth_turnoAnterior         = lthd_turnoHistoriaDAO.findById(
						    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
						);

					if(lth_turnoAnterior != null)
					{
						DocumentosSalidaDAO ldsd_documentosSalidaDAO;

						ldsd_documentosSalidaDAO = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

						if(lb_procesoCorrecciones)
						{
							Collection<DocumentosSalida> lds_documentos;

							lds_documentos = ldsd_documentosSalidaDAO.findAllDocumentBySolicitudTipoEstado(
								    ls_solicitud.getIdSolicitud(), TipoDocumentalCommon.CITACION_NOTIFICACION,
								    EstadoCommon.A
								);

							if(CollectionUtils.isValidCollection(lds_documentos))
							{
								for(DocumentosSalida lds_documento : lds_documentos)
								{
									if(lds_documento != null)
										enviarDocumentoOWCC(
										    lds_documento, ldsd_documentosSalidaDAO, abpd_DAO, as_endpoint, as_remoteIp,
										    as_userId, ldm_manager
										);
								}
							}
						}
						else
						{
							DocumentosSalida lds_documento;

							lds_documento = new DocumentosSalida();

							lds_documento.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_turnoAnterior.getIdTurnoHistoria())
							);
							lds_documento.setIdTipoDocumental(
							    lb_notificacion ? TipoDocumentalCommon.ACTA_NOTIFICACION
							                    : TipoDocumentalCommon.CITACION_NOTIFICACION
							);

							lds_documento = ldsd_documentosSalidaDAO.findByIdTurnoHistoriaTipoDocumental(lds_documento);

							enviarDocumentoOWCC(
							    lds_documento, ldsd_documentosSalidaDAO, abpd_DAO, as_endpoint, as_remoteIp, as_userId,
							    ldm_manager
							);
						}

						ath_turnoHistoria.setIdDocumentoSalida(lth_turnoAnterior.getIdDocumentoSalida());
						ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
						ath_turnoHistoria.setIpModificacion(as_remoteIp);

						lthd_turnoHistoriaDAO.updateIdDocumentoSalida(ath_turnoHistoria);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);
				}

				if(lb_procesoCorrecciones)
					DaoCreator.getProcedimientosDAO(ldm_manager)
						          .procCrearComunicacionNotificacion(
						    ath_turnoHistoria.getIdTurnoHistoria(), as_userId, as_remoteIp
						);
				else
					terminarTurnoHistoriaYCrearEtapa(
					    ath_turnoHistoria, ldm_manager,
					    new MotivoTramite(
					        NumericUtils.getLong(ath_turnoHistoria.getIdEtapa()),
					        lb_notificacion ? MotivoTramiteCommon.NOTIFICACION_PERSONAL_CORREO_ELECTRONICO
					                        : MotivoTramiteCommon.NOTIFICACION_PERSONAL_DIRECCION_DE_RESIDENCIA
					    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
					);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.ENVIO_CITATORIO_NOTIFICACION, lb2be_e.getMessage(), as_userId,
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
	 * Verifica si existen casos encolados y comienza a procesarlos.
	 *
	 * @param as_remoteIp Dirección IP del del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void enviarDocumentos(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_ENVIO_CITATORIO_WS_INVOKE, ConstanteCommon.JOB_ENVIO_CITATORIO_LIMITE_INTENTOS,
				    EtapaCommon.ENVIO_CITATORIO_Y_O_ACTA_NOTIFICACION, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			enviarDocumentos(lcth_parametros, as_remoteIp);
	}

	/**
	 * Prepara el job para comenzar a desencolar los casos.
	 *
	 * @param acth_casos Objeto contenedor de los casos a procesar
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void enviarDocumentos(Collection<TurnoHistoria> acth_casos, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_CITATORIO_BLOQUEO;
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

				clh_LOGGER.error("enviarDocumentos", lb2be_e);

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
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("enviarDocumentos", lb2be_e);

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
					if(CollectionUtils.isValidCollection(acth_casos))
					{
						BitacoraProcesoDAO lbpd_bitacora;
						String             ls_endpoint;

						lbpd_bitacora     = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						ls_endpoint       = DaoCreator.getConstantesDAO(ldm_constantes)
								                          .findString(
								    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
								);

						ldm_bitacora.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_casos)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									enviarCitatorioNotificacion(
									    lth_iterador, lbpd_bitacora, ls_endpoint, as_remoteIp, ls_userId
									);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("enviarDocumentos", lb2be_e);

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
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarDocumentos", lb2be_e);
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
			clh_LOGGER.error("enviarDocumentos", lb2be_e);

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

					clh_LOGGER.error("enviarDocumentos", lb2be_e);

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
	 * Genera el citatorio o la notificación y envia el caso a una etapa de entrega.
	 *
	 * @param ath_turnoHistoria Objeto contenedor de la información del caso
	 * @param abpd_DAO Objeto contenedor de la conexión a la tabla de bitacora para el registro de errores
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param as_userId de as user id
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public synchronized void generarCitatorioNotificacion(
	    TurnoHistoria ath_turnoHistoria, BitacoraProcesoDAO abpd_DAO, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			String ls_idProceso;

			ls_idProceso = ath_turnoHistoria.getIdProceso();

			if(!StringUtils.isValidString(ls_idProceso))
				throw new B2BException(ErrorKeys.PROCESO_NO_EXISTE);

			if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_6))
				generarDocumentoNotificacion(ath_turnoHistoria, as_userId, false, as_remoteIp, ldm_manager);
			else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
			{
				boolean               lb_proceso47o48;
				Collection<Solicitud> lcs_solicitudesAsociadas;

				lb_proceso47o48              = false;
				lcs_solicitudesAsociadas     = obtenerSolicitudesVinculadas(
					    ath_turnoHistoria.getIdSolicitud(), false, ldm_manager
					);

				if(CollectionUtils.isValidCollection(lcs_solicitudesAsociadas))
				{
					Iterator<Solicitud> lis_iterator;

					lis_iterator = lcs_solicitudesAsociadas.iterator();

					while(lis_iterator.hasNext() && !lb_proceso47o48)
					{
						Solicitud ls_actual;

						ls_actual = lis_iterator.next();

						if(ls_actual != null)
						{
							String ls_procesoVinculado;

							ls_procesoVinculado = ls_actual.getIdProceso();

							if(StringUtils.isValidString(ls_procesoVinculado))
								lb_proceso47o48 = (ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
										|| ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48));
						}
					}
				}

				if(lb_proceso47o48)
					generarDocumentoNotificacion(ath_turnoHistoria, as_userId, false, as_remoteIp, ldm_manager, true);
				else
				{
					TurnoHistoriaDAO lthd_turnoHistoriaDAO;
					TurnoHistoria    lth_turno195;

					lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					lth_turno195              = lthd_turnoHistoriaDAO.findMaxByIdEtapa(
						    EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ath_turnoHistoria.getIdSolicitud()
						);

					if(lth_turno195 != null)
					{
						Long                         ll_idTurnoHistoria;
						DocumentosSalidaDAO          ldsd_documentosSalidaDAO;
						Collection<DocumentosSalida> lcds_comunicaciones;

						ll_idTurnoHistoria           = lth_turno195.getIdTurnoHistoria();
						ldsd_documentosSalidaDAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
						lcds_comunicaciones          = new LinkedList<DocumentosSalida>();

						{
							Integer li_idTurnoHistoria;

							li_idTurnoHistoria = NumericUtils.getInteger(ll_idTurnoHistoria);

							{
								Collection<DocumentosSalida> lcds_comunicados;

								lcds_comunicados = ldsd_documentosSalidaDAO.findByIdTurnoHistoriaTipo(
									    li_idTurnoHistoria, TipoArchivoCommon.COMUNICADO, false, true
									);

								if(CollectionUtils.isValidCollection(lcds_comunicados))
									lcds_comunicaciones.addAll(lcds_comunicados);
							}

							{
								Collection<DocumentosSalida> lcds_comunicadosIndeternminados;

								lcds_comunicadosIndeternminados = ldsd_documentosSalidaDAO.findByIdTurnoHistoriaTipo(
									    li_idTurnoHistoria, TipoArchivoCommon.COMUNICADO_INDETERMINADO, false, true
									);

								if(CollectionUtils.isValidCollection(lcds_comunicadosIndeternminados))
									lcds_comunicaciones.addAll(lcds_comunicadosIndeternminados);
							}
						}

						if(CollectionUtils.isValidCollection(lcds_comunicaciones))
						{
							Integer li_idTurnoHistoriaActual;
							Long    ll_numeroResolucion;
							Date    ld_fechaResolucion;

							li_idTurnoHistoriaActual     = NumericUtils.getInteger(
								    ath_turnoHistoria.getIdTurnoHistoria()
								);
							ll_numeroResolucion          = null;
							ld_fechaResolucion           = null;

							{
								DocumentosSalida lds_autoResolucion;

								lds_autoResolucion = ldsd_documentosSalidaDAO.findLastAutoResolucion(
									    ll_idTurnoHistoria
									);

								if(lds_autoResolucion != null)
								{
									ll_numeroResolucion     = lds_autoResolucion.getConsecutivoResolucion();
									ld_fechaResolucion      = lds_autoResolucion.getFechaResolucion();
								}
							}

							for(DocumentosSalida lds_documento : lcds_comunicaciones)
							{
								if(lds_documento != null)
								{
									{
										DocumentosSalida lds_citatorioNotificacion;

										{
											Solicitud ls_solicitud;

											ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
													                     .findById(ath_turnoHistoria.getIdSolicitud());

											if(ls_solicitud == null)
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

											lds_citatorioNotificacion = generarDocumentoSalidaBase(
												    ath_turnoHistoria, ls_solicitud,
												    StringUtils.getStringNotNull(
												        ls_solicitud.getIdAutorizacionNotificacion()
												    ).equals(MedioNotificarCommon.CORREO_ELECTRONICO), false,
												    ldm_manager
												);
										}

										lds_citatorioNotificacion.setTipo(TipoArchivoCommon.CITACION_NOTIFICACION);
										lds_citatorioNotificacion.setIdTipoDocumental(
										    TipoDocumentalCommon.CITACION_NOTIFICACION
										);
										lds_citatorioNotificacion.setDestinatario(lds_documento.getDestinatario());
										lds_citatorioNotificacion.setDireccion(lds_documento.getDireccion());
										lds_citatorioNotificacion.setCorreoElectronico(
										    lds_documento.getCorreoElectronico()
										);
										lds_citatorioNotificacion.setIdPais(lds_documento.getIdPais());
										lds_citatorioNotificacion.setIdDepartamento(lds_documento.getIdDepartamento());
										lds_citatorioNotificacion.setIdMunicipio(lds_documento.getIdMunicipio());
										lds_citatorioNotificacion.setIdPersonaNotificacion(
										    lds_documento.getIdPersonaNotificacion()
										);
										lds_citatorioNotificacion.setConsecutivoResolucion(
										    lds_documento.getConsecutivoResolucion()
										);
										lds_citatorioNotificacion.setFechaResolucion(
										    lds_documento.getFechaResolucion()
										);

										generarDocumentoCitatorioResol(
										    ath_turnoHistoria, lds_documento, lds_citatorioNotificacion,
										    ConstanteCommon.PLANTILLA_CITATORIO_NOTIFICACION_RESOL, ll_numeroResolucion,
										    ld_fechaResolucion, true, as_userId, as_remoteIp, ldm_manager
										);
									}

									lds_documento.setIdTurnoHistoria(li_idTurnoHistoriaActual);
									lds_documento.setIdUsuarioModificacion(as_userId);
									lds_documento.setIpModificacion(as_remoteIp);

									ldsd_documentosSalidaDAO.updateIdTurnoHistoria(lds_documento);
								}
							}
						}
					}
				}
			}

			terminarTurnoHistoriaYCrearEtapa(
			    ath_turnoHistoria, ldm_manager,
			    new MotivoTramite(
			        NumericUtils.getLong(ath_turnoHistoria.getIdEtapa()), MotivoTramiteCommon.NOTIFICACION_PERSONAL_ORIP
			    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
			);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.GENERACION_CITATORIO_NOTIFICACION, lb2be_e.getMessage(), as_userId,
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
	 * Verifica si existen casos encolados y comienza a procesarlos.
	 *
	 * @param as_remoteIp Dirección IP del del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void procesarCasos(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_parametros = obtenerTurnosJob(
				    ConstanteCommon.JOB_GENERACION_CITATORIO_WS_INVOKE,
				    ConstanteCommon.JOB_GENERACION_CITATORIO_LIMITE_INTENTOS,
				    EtapaCommon.GENERACION_CITATORIO_Y_ACTA_NOTIFICACION, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("procesarCasos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			procesarCasos(lcth_parametros, as_remoteIp);
	}

	/**
	 * Prepara el job para comenzar a desencolar los casos.
	 *
	 * @param acth_casos Objeto contenedor de los casos a procesar
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized void procesarCasos(Collection<TurnoHistoria> acth_casos, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_GENERACION_CITATORIO_BLOQUEO;
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

				clh_LOGGER.error("procesarCasos", lb2be_e);

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
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("procesarCasos", lb2be_e);

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
					if(CollectionUtils.isValidCollection(acth_casos))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_casos)
						{
							if((lth_iterador != null))
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									generarCitatorioNotificacion(lth_iterador, lbpd_bitacora, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("procesarCasos", lb2be_e);

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

					clh_LOGGER.error("procesarCasos", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesarCasos", lb2be_e);

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

					clh_LOGGER.error("procesarCasos", lb2be_e);

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
	 * Enviar documento OWCC.
	 *
	 * @param lds_documento de lds documento
	 * @param adsd_DAO de adsd DAO
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void enviarDocumentoOWCC(
	    DocumentosSalida lds_documento, DocumentosSalidaDAO adsd_DAO, BitacoraProcesoDAO abpd_DAO, String as_endpoint,
	    String as_remoteIp, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(lds_documento == null)
			throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_NO_VALIDO);

		lds_documento.setRepositorio(RepositorioCommon.FINAL);
		lds_documento.setIdUsuarioModificacion(as_userId);
		lds_documento.setIpModificacion(as_remoteIp);

		getEnvioGestorDocumentalBusiness()
			    .enviarGestorDocumental(lds_documento, abpd_DAO, as_endpoint, as_userId, as_remoteIp, adm_manager);

		adsd_DAO.updateRepositorio(lds_documento);
	}
}
