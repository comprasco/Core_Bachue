package com.bachue.snr.prosnr01.business.envioComunicado;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr16.integracion.cliente.cyn.mensajero.ClienteEnviarMensaje;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AlertaTitularDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SoporteTrasladoMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;

import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTitular;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacion;
import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacionEnviado;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.CodigoVariablePlantillaCommon;
import com.bachue.snr.prosnr16.common.constants.TipoParametroCommon;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades EnvioComunicadoBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/06/2020
 */
public class EnvioComunicadoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioComunicadoBusiness.class);

	/**
	 * Método encargado de consultar los casos con contenido valido para enviar al componente de comunicaciones y notificaciones.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarComunicado(String as_remoteIp)
	    throws B2BException
	{
		Collection<MensajeComunicacion> lcmc_comunicados;
		DAOManager                      ldm_manager;

		lcmc_comunicados     = null;
		ldm_manager          = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ENVIO_COMUNICADOS_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					lcmc_comunicados = DaoCreator.getMensajeComunicacionDAO(ldm_manager).buscarTodasLasComunicaciones();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarComunicado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcmc_comunicados))
			enviarComunicado(lcmc_comunicados, as_remoteIp);
	}

	/**
	 *  Método encargado de validar los comunicados encontrados para enviarlos al componente de comunicaciones y notificaiones.
	 *
	 * @param acmc_comunicados de acds parametros
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarComunicado(Collection<MensajeComunicacion> acmc_comunicados, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ENVIO_COMUNICADOS_BLOQUEO;
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

				clh_LOGGER.error("enviarComunicado", lb2be_e);

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

				clh_LOGGER.error("enviarGestorDocumental", lb2be_e);

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
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					String ls_endpoint;

					ls_endpoint = DaoCreator.getConstantesDAO(ldm_constantes)
							                    .findString(ConstanteCommon.JOB_ENVIO_COMUNICADOS_ENDPOINT);

					if(CollectionUtils.isValidCollection(acmc_comunicados))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(MensajeComunicacion lds_iterador : acmc_comunicados)
						{
							if(lds_iterador != null)
							{
								try
								{
									enviarComunicado(lds_iterador, lbpd_bitacora, ls_endpoint, ls_userId, as_remoteIp);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("enviarComunicado", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("enviarComunicado", lb2be_e);
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
			clh_LOGGER.error("enviarComunicado", lb2be_e);

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

					clh_LOGGER.error("enviarComunicado", lb2be_e);

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
	 * Método encargado de ejecutar web service para el envio de comunicados al componente de comunicaciones y notificaciones.
	 *
	 * @param amc_comunicado de MensajeComunicacion
	 * @param abpd_DAO Objeto de tipo  BitacoraProcesoDAO utilizado para crear instancia de la clase BitacoraProcesoDAO.
	 * @param amcd_DAO correspondiente al valor de amcd DAO
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino para el envió al componente de comunicaciones y notificaciones.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public synchronized void enviarComunicado(
	    MensajeComunicacion amc_comunicado, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_userId,
	    String as_ipRemota
	)
	    throws B2BException
	{
		if(amc_comunicado != null)
		{
			DAOManager ldm_manager;
			boolean    lb_incrementarIntento;
			String     ls_mensaje;
			long       ll_intentoActual;

			ldm_manager               = DaoManagerFactory.getDAOManager();
			lb_incrementarIntento     = false;
			ls_mensaje                = addMessage(MessagesKeys.PROCESO_EXITOSO);
			ll_intentoActual          = NumericUtils.getLong(amc_comunicado.getIntentosFallidosEjecucionAutomatica());

			try
			{
				if(!StringUtils.isValidString(as_endpoint))
					throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);
				else
				{
					Long                         ll_idTurnoHistoria;
					TurnoHistoriaDAO             lthd_DAO;
					TurnoHistoria                lth_turnoHistoria;
					Collection<DocumentosSalida> lcds_documentos;
					Map<String, TipoVariableEMI> lmstvemi_tags;
					String                       ls_idCirculo;
					String                       ls_idTurno;
					String                       ls_nir;
					String                       ls_idPlantilla;
					String                       ls_idSolicitud;
					ConstantesDAO                lcd_constantesDAO;
					SolicitudDAO                 lsd_DAO;

					ll_idTurnoHistoria     = NumericUtils.getLongWrapper(amc_comunicado.getIdTurnoHistoria());
					lthd_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					lth_turnoHistoria      = lthd_DAO.findById(ll_idTurnoHistoria);
					lcds_documentos        = new LinkedList<DocumentosSalida>();
					lmstvemi_tags          = new HashMap<String, TipoVariableEMI>();
					ls_idCirculo           = null;
					ls_idTurno             = null;
					ls_nir                 = null;
					ls_idPlantilla         = amc_comunicado.getIdPlantilla();
					ls_idSolicitud         = null;
					lcd_constantesDAO      = DaoCreator.getConstantesDAO(ldm_manager);
					lsd_DAO                = DaoCreator.getSolicitudDAO(ldm_manager);

					if(lth_turnoHistoria != null)
					{
						Turno     lt_turno;
						Solicitud ls_solicitud;

						lt_turno         = DaoCreator.getTurnoDAO(ldm_manager).findById(lth_turnoHistoria.getIdTurno());
						ls_solicitud     = lsd_DAO.findById(lth_turnoHistoria.getIdSolicitud());

						if(lt_turno != null)
						{
							ls_idTurno = lt_turno.getIdTurno();

							if(!StringUtils.isValidString(ls_idTurno))
								throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

							lmstvemi_tags.put(
							    CodigoVariablePlantillaCommon.TAG_TURNO,
							    new TipoVariableEMI(
							        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_TURNO, ls_idTurno
							    )
							);

							{
								String ls_matriculas;

								ls_matriculas = obtenerMatriculasBySolicitudCirculo(
									    lt_turno.getIdSolicitud(), lt_turno.getIdCirculo(), ldm_manager
									);

								if(StringUtils.isValidString(ls_matriculas))
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_MATRICULAS,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MATRICULAS,
									        ls_matriculas
									    )
									);
							}

							{
								Long ll_etapaActual;

								ll_etapaActual = lt_turno.getIdEtapaActual();

								if(NumericUtils.isValidLong(ll_etapaActual, 1L))
								{
									Etapa le_etapa;

									le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(ll_etapaActual.longValue());

									if(le_etapa != null)
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_ID_ETAPA_ACTUAL,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_ID_ETAPA_ACTUAL, le_etapa.getNombre()
										    )
										);
								}
							}

							{
								String ls_expediente;

								ls_expediente = lt_turno.getExpediente();

								if(StringUtils.isValidString(ls_expediente))
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_NUMERO_EXPEDIENTE,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_NUMERO_EXPEDIENTE, ls_expediente
									    )
									);
							}

							ls_idCirculo = lt_turno.getIdCirculo();

							{
								TurnoHistoria lth_tua610;

								lth_tua610 = lthd_DAO.findByIdTurnoEtapa(ls_idTurno, EtapaCommon.RECARGA_CUENTA_CUPO);

								if(lth_tua610 != null)
								{
									String ls_observaciones;

									ls_observaciones = lth_tua610.getObservaciones();

									if(StringUtils.isValidString(ls_observaciones))
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_JUSTIFICACION,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_JUSTIFICACION, ls_observaciones
										    )
										);
								}
							}
						}

						if(ls_solicitud != null)
						{
							String ls_idProceso;

							ls_idProceso       = ls_solicitud.getIdProceso();
							ls_idSolicitud     = ls_solicitud.getIdSolicitud();

							if(!StringUtils.isValidString(ls_idSolicitud))
								throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

							if(!StringUtils.isValidString(ls_idProceso))
								throw new B2BException(ErrorKeys.ID_PROCESO_INVALIDO);

							{
								SolicitudAsociada lsa_asociada;

								lsa_asociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
										                     .findByIdSolicitud(
										    new SolicitudAsociada(ls_idSolicitud, false)
										);

								if(lsa_asociada != null)
								{
									String ls_idSolicitudAsociada;

									ls_idSolicitudAsociada = lsa_asociada.getIdSolicitud1();

									if(StringUtils.isValidString(ls_idSolicitudAsociada))
									{
										Solicitud ls_solicitudAsociada;

										ls_solicitudAsociada = lsd_DAO.findById(ls_idSolicitudAsociada);

										if(ls_solicitudAsociada != null)
										{
											String ls_nirAsociado;

											ls_nirAsociado = ls_solicitudAsociada.getNir();

											if(StringUtils.isValidString(ls_nirAsociado))
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_NIR_RECEPCION,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_NIR_RECEPCION, ls_nirAsociado
												    )
												);
										}
									}
								}
							}

							{
								DevolucionDinero ldd_dd;

								ldd_dd = DaoCreator.getDevolucionDineroDAO(ldm_manager).findByIdSolicitud(
									    ls_idSolicitud
									);

								if(ldd_dd != null)
								{
									Integer ls_token;

									ls_token = ldd_dd.getTokenVerificacion();

									if(NumericUtils.isValidInteger(ls_token))
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_CODIGO_VERIFICACION_NIR,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_CODIGO_VERIFICACION_NIR,
										        ls_token.toString()
										    )
										);
								}
							}

							{
								if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
								{
									String ls_turnoAnt;

									ls_turnoAnt = ls_solicitud.getIdTurnoAnt();

									if(StringUtils.isValidString(ls_turnoAnt))
									{
										Turno lt_turnoAnt;

										lt_turnoAnt = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_turnoAnt);

										if(lt_turnoAnt != null)
										{
											Solicitud ls_solicitudtmp;

											ls_solicitudtmp = DaoCreator.getSolicitudDAO(ldm_manager)
													                        .findById(lt_turnoAnt.getIdSolicitud());

											if(ls_solicitudtmp != null)
											{
												SolicitudInterviniente lsi_si;
												String                 ls_idPersona;

												ls_idPersona     = ls_solicitudtmp.getIdPersona();
												lsi_si           = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
														                         .findById(
														    ls_solicitudtmp.getIdSolicitud(), ls_idPersona
														);

												if(lsi_si != null)
												{
													Persona lp_persona;

													lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
															                   .findById(ls_idPersona);

													if(lp_persona != null)
														lmstvemi_tags.put(
														    CodigoVariablePlantillaCommon.TAG_SOLICITANTE_TURNO_DEV_DINERO,
														    new TipoVariableEMI(
														        TipoParametroCommon.CUERPO,
														        CodigoVariablePlantillaCommon.TAG_SOLICITANTE_TURNO_DEV_DINERO,
														        lp_persona.getNombreCompleto()
														    )
														);
												}
											}
										}
									}
								}
							}

							{
								TurnoHistoria lth_recursos;

								lth_recursos = lthd_DAO.findMaxByIdEtapaIdTurno(EtapaCommon.ID_ETAPA_430, ls_idTurno);

								if(lth_recursos == null)
									lth_recursos = lthd_DAO.findMaxByIdEtapaIdTurno(
										    EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS, ls_idTurno
										);

								if(lth_recursos != null)
								{
									OficiosTexto lot_ot;

									lot_ot = DaoCreator.getOficiosTextoDAO(ldm_manager)
											               .findByTurnoHistoria(lth_recursos.getIdTurnoHistoria());

									if(lot_ot != null)
									{
										Long ll_diasSuspension;

										ll_diasSuspension = lot_ot.getDiasSuspension();

										if(NumericUtils.isValidLong(ll_diasSuspension))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_DIAS_SUSPENSION_RECURSOS,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_DIAS_SUSPENSION_RECURSOS,
											        ll_diasSuspension.toString()
											    )
											);
									}
								}
							}

							{
								Collection<SolicitudMatricula> lcsm_solicitudesMatricula;

								lcsm_solicitudesMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
										                                  .findByIdSolicitud(ls_idSolicitud, false);

								if(CollectionUtils.isValidCollection(lcsm_solicitudesMatricula))
								{
									Collection<AlertaTitular> lcat_alertas;

									lcat_alertas = DaoCreator.getAlertaTitularDAO(ldm_manager)
											                     .findByIdSolicitud(ls_idSolicitud);

									if(CollectionUtils.isValidCollection(lcat_alertas))
									{
										Collection<String>           lcs_matriculasDefinitivas;
										Iterator<SolicitudMatricula> lism_iterator;
										String                       ls_matriculas;

										lism_iterator                 = lcsm_solicitudesMatricula.iterator();
										lcs_matriculasDefinitivas     = new ArrayList<String>();
										ls_matriculas                 = null;

										while(lism_iterator.hasNext())
										{
											SolicitudMatricula lsm_matricula;

											lsm_matricula = lism_iterator.next();

											if(lsm_matricula != null)
											{
												Iterator<AlertaTitular> liat_alerta;
												boolean                 lb_encontro;

												liat_alerta     = lcat_alertas.iterator();
												lb_encontro     = false;

												while(liat_alerta.hasNext() && !lb_encontro)
												{
													AlertaTitular lat_alerta;

													lat_alerta = liat_alerta.next();

													if(lat_alerta != null)
													{
														if(lsm_matricula.equals(lat_alerta))
														{
															StringBuilder lsb_builder;

															lsb_builder = new StringBuilder(
																    lsm_matricula.getIdCirculo()
																);

															lsb_builder.append(
															    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
															);
															lsb_builder.append(lsm_matricula.getIdMatricula());

															lcs_matriculasDefinitivas.add(lsb_builder.toString());
														}
													}
												}
											}
										}

										if(CollectionUtils.isValidCollection(lcs_matriculasDefinitivas))
											ls_matriculas = StringUtils.getStringOfCollection(
												    lcs_matriculasDefinitivas
												);

										if(StringUtils.isValidString(ls_matriculas))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA_ALERTA,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA_ALERTA,
											        ls_matriculas
											    )
											);
									}
								}
							}

							{
								Documento ld_documento;

								ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
										                     .findDocumentoByIdSolicitud(ls_idSolicitud);

								if(ld_documento != null)
								{
									{
										String ls_idTipoDocumento;

										ls_idTipoDocumento = ld_documento.getIdTipoDocumento();

										if(StringUtils.isValidString(ls_idTipoDocumento))
										{
											TipoDocumentoPublico ltdp_documento;

											ltdp_documento = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
													                       .findById(ls_idTipoDocumento);

											if(ltdp_documento != null)
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_ID_DOCUMENTO,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_ID_DOCUMENTO,
												        ltdp_documento.getNombre()
												    )
												);
										}
									}

									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_N_DOCUMENTO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_N_DOCUMENTO,
									        ld_documento.getNumero()
									    )
									);
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_FECHA_DOCUMENTO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_FECHA_DOCUMENTO,
									        StringUtils.getString(
									            ld_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO
									        )
									    )
									);

									{
										String ls_idOficinaOrigen;

										ls_idOficinaOrigen = ld_documento.getIdOficinaOrigen();

										if(StringUtils.isValidString(ls_idOficinaOrigen))
										{
											OficinaOrigenDAO lood_DAO;
											OficinaOrigen    loo_origen;

											lood_DAO       = DaoCreator.getOficinaOrigenDAO(ldm_manager);
											loo_origen     = lood_DAO.findById(
												    ls_idOficinaOrigen, lood_DAO.findMaxVersion(ls_idOficinaOrigen)
												);

											if(loo_origen != null)
											{
												String ls_entidad;

												ls_entidad = loo_origen.getNombre();

												if(StringUtils.isValidString(ls_entidad))
													lmstvemi_tags.put(
													    CodigoVariablePlantillaCommon.TAG_ENTIDAD_DOCUMENTO,
													    new TipoVariableEMI(
													        TipoParametroCommon.CUERPO,
													        CodigoVariablePlantillaCommon.TAG_ENTIDAD_DOCUMENTO,
													        ls_entidad
													    )
													);
											}
										}
									}
								}
							}

							{
								Collection<Acto> lca_actos;

								lca_actos = DaoCreator.getActoDAO(ldm_manager)
										                  .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

								if(CollectionUtils.isValidCollection(lca_actos))
								{
									StringBuilder lsb_builder;
									TipoActoDAO   ltad_DAO;
									int           li_contador;

									lsb_builder     = new StringBuilder();
									ltad_DAO        = DaoCreator.getTipoActoDAO(ldm_manager);
									li_contador     = 0;

									for(Acto la_acto : lca_actos)
									{
										if(la_acto != null)
										{
											String ls_idTipoActo;

											ls_idTipoActo = la_acto.getIdTipoActo();

											if(StringUtils.isValidString(ls_idTipoActo))
											{
												TipoActo lta_tipoActo;

												lta_tipoActo = ltad_DAO.findById(ls_idTipoActo);

												if(lta_tipoActo != null)
												{
													lsb_builder.append(lta_tipoActo.getIdTipoActo());
													lsb_builder.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
													lsb_builder.append(lta_tipoActo.getNombre());

													if(li_contador < (lca_actos.size() - 1))
														lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);

													li_contador++;
												}
											}
										}
									}

									{
										String ls_texto;

										ls_texto = lsb_builder.toString();

										if(StringUtils.isValidString(ls_texto))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_TIPO_ACTO,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_TIPO_ACTO, ls_texto
											    )
											);
									}
								}
							}

							{
								Collection<TrasladoMatricula> lctm_traslados;

								lctm_traslados = DaoCreator.getTrasladoMatriculaDAO(ldm_manager)
										                       .findByIdSolicitud(ls_idSolicitud, true);

								if(CollectionUtils.isValidCollection(lctm_traslados))
								{
									StringBuilder lsb_solicitudMatriculaOrigen;
									StringBuilder lsb_circuloDestino;
									StringBuilder lsb_circuloOrigen;
									int           li_contador;

									lsb_solicitudMatriculaOrigen     = new StringBuilder();
									lsb_circuloDestino               = new StringBuilder();
									lsb_circuloOrigen                = new StringBuilder();
									li_contador                      = 0;

									for(TrasladoMatricula ltm_temp : lctm_traslados)
									{
										if(ltm_temp != null)
										{
											lsb_solicitudMatriculaOrigen.append(ltm_temp.getIdCirculoOrigen());
											lsb_solicitudMatriculaOrigen.append(
											    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
											);
											lsb_solicitudMatriculaOrigen.append(ltm_temp.getIdMatriculaOrigen());
											lsb_circuloDestino.append(ltm_temp.getIdCirculoDestino());
											lsb_circuloOrigen.append(ltm_temp.getIdCirculoOrigen());

											if(li_contador < (lctm_traslados.size() - 1))
											{
												lsb_solicitudMatriculaOrigen.append(IdentificadoresCommon.SIMBOLO_COMA);
												lsb_circuloDestino.append(IdentificadoresCommon.SIMBOLO_COMA);
												lsb_circuloOrigen.append(IdentificadoresCommon.SIMBOLO_COMA);
											}

											li_contador++;
										}
									}

									{
										String ls_solicitudMatriculaOrigen;
										String ls_circuloDestino;
										String ls_circuloOrigen;

										ls_solicitudMatriculaOrigen     = lsb_solicitudMatriculaOrigen.toString();
										ls_circuloDestino               = lsb_circuloDestino.toString();
										ls_circuloOrigen                = lsb_circuloOrigen.toString();

										if(StringUtils.isValidString(ls_solicitudMatriculaOrigen))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA_ORIGEN,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA_ORIGEN,
											        ls_solicitudMatriculaOrigen
											    )
											);

										if(StringUtils.isValidString(ls_circuloDestino))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_CIRCULO_REGISTRAL_DESTINO,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_CIRCULO_REGISTRAL_DESTINO,
											        ls_circuloDestino
											    )
											);

										if(StringUtils.isValidString(ls_circuloOrigen))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_CIRCULO_REGISTRAL_ORIGEN,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_CIRCULO_REGISTRAL_ORIGEN,
											        ls_circuloOrigen
											    )
											);
									}
								}
							}

							{
								Collection<SoporteTraslado> lcst_soportes;

								lcst_soportes = DaoCreator.getSoporteTrasladoDAO(ldm_manager)
										                      .findByIdSolicitud(ls_idSolicitud);

								if(CollectionUtils.isValidCollection(lcst_soportes))
								{
									SoporteTrasladoMatriculaDAO lstmd_DAO;
									StringBuilder               lsb_builder;
									int                         li_contadorSoportes;

									lstmd_DAO               = DaoCreator.getSoporteTrasladoMatriculaDAO(ldm_manager);
									lsb_builder             = new StringBuilder();
									li_contadorSoportes     = 0;

									for(SoporteTraslado lst_iterador : lcst_soportes)
									{
										if(lst_iterador != null)
										{
											String ls_idSoporte;

											ls_idSoporte = lst_iterador.getIdSoporteTraslado();

											if(StringUtils.isValidString(ls_idSoporte))
											{
												Collection<SoporteTrasladoMatricula> lctm_matriculas;
												int                                  li_contador;

												lctm_matriculas     = lstmd_DAO.findByIdSoporteTraslado(ls_idSoporte);
												li_contador         = 0;

												if(CollectionUtils.isValidCollection(lctm_matriculas))
												{
													li_contadorSoportes++;
													li_contador++;

													for(SoporteTrasladoMatricula lstm_iterador : lctm_matriculas)
													{
														if(lstm_iterador != null)
														{
															lsb_builder.append(lstm_iterador.getIdCirculoDestino());
															lsb_builder.append(
															    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
															);
															lsb_builder.append(lstm_iterador.getMatriculaDestino());

															if(
															    (li_contadorSoportes < lcst_soportes.size())
																    && (li_contador < lctm_matriculas.size())
															)
																lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);
														}
													}
												}
											}
										}
									}

									{
										String ls_texto;

										ls_texto = lsb_builder.toString();

										if(StringUtils.isValidString(ls_texto))
											lmstvemi_tags.put(
											    (TipoParametroCommon.CUERPO
											    + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
											    + CodigoVariablePlantillaCommon.TAG_MATRICULAS_DESTINO_TRASLADOS),
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_MATRICULAS_DESTINO_TRASLADOS,
											        ls_texto
											    )
											);
									}
								}
							}

							{
								Collection<DocumentosSalida> lcds_adjuntos;

								lcds_adjuntos      = null;
								ls_idSolicitud     = ls_solicitud.getIdSolicitud();
								ls_nir             = ls_solicitud.getNir();

								lmstvemi_tags.put(
								    (TipoParametroCommon.ASUNTO + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
								    + CodigoVariablePlantillaCommon.TAG_NIR),
								    new TipoVariableEMI(
								        TipoParametroCommon.ASUNTO, CodigoVariablePlantillaCommon.TAG_NIR, ls_nir
								    )
								);
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_NIR,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NIR, ls_nir
								    )
								);
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_FECHA_CREACION,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_FECHA_CREACION,
								        StringUtils.getString(
								            ls_solicitud.getFechaCreacion(), FormatoFechaCommon.DIA_MES_ANIO
								        )
								    )
								);

								if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idSolicitud))
								{
									String ls_matriculas;

									ls_matriculas = obtenerMatriculasBySolicitudCirculo(
										    ls_idSolicitud, ls_idCirculo, true, ldm_manager
										);

									if(StringUtils.isValidString(ls_matriculas))
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_ID_SOLICITUD_MATRICULA, ls_matriculas
										    )
										);
								}

								{
									String ls_tipoDocumental;

									if(StringUtils.isValidString(ls_idPlantilla))
									{
										if(
										    ls_idPlantilla.equals("30") || ls_idPlantilla.equals("32")
											    || ls_idPlantilla.equals("35") || ls_idPlantilla.equals("37")
										)
											ls_tipoDocumental = TipoDocumentalCommon.RECIBO_LIQUIDACION;
										else
											ls_tipoDocumental = null;
									}
									else
										ls_tipoDocumental = null;

									if(StringUtils.isValidString(ls_tipoDocumental))
										lcds_adjuntos = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
												                      .findDocumentByTurnoTipoDocumentalEstado(
												    new DocumentosSalida(
												        ls_idSolicitud, null, ls_tipoDocumental, EstadoCommon.ACTIVO
												    ), false
												);
									else
									{
										ls_tipoDocumental     = TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD;
										lcds_adjuntos         = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
												                              .findDocumentByTurnoTipoDocumentalEstado(
												    new DocumentosSalida(
												        ls_idSolicitud, null, ls_tipoDocumental, EstadoCommon.ACTIVO
												    ), false
												);
									}

									if(CollectionUtils.isValidCollection(lcds_adjuntos))
									{
										Iterator<DocumentosSalida> lids_iterator;

										lids_iterator = lcds_adjuntos.iterator();

										if((lids_iterator != null) && lids_iterator.hasNext())
											lcds_documentos.add(lids_iterator.next());
									}
								}
							}

							{
								String ls_idPersona;

								ls_idPersona = ls_solicitud.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									Persona lp_persona;

									lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);

									if(lp_persona != null)
									{
										String ls_nombre;

										ls_nombre = lp_persona.getNombreCompleto();

										if(StringUtils.isValidString(ls_nombre))
										{
											if(
											    StringUtils.isValidString(ls_idProceso)
												    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_50)
											)
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS, ls_nombre
												    )
												);

											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_INTERVINIENTE,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_INTERVINIENTE, ls_nombre
											    )
											);
										}

										{
											String ls_numeroDocumento;
											String ls_tipoDocumento;

											ls_numeroDocumento     = lp_persona.getNumeroDocumento();
											ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();

											if(
											    StringUtils.isValidString(ls_tipoDocumento)
												    && StringUtils.isValidString(ls_numeroDocumento)
											)
											{
												StringBuilder lsb_documento;

												lsb_documento = new StringBuilder(ls_tipoDocumento);

												lsb_documento.append(IdentificadoresCommon.ESPACIO_VACIO);
												lsb_documento.append(ls_numeroDocumento);

												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_NUMERO_DOCUMENTO,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_NUMERO_DOCUMENTO,
												        lsb_documento.toString()
												    )
												);
											}
										}
									}
								}
							}

							{
								String ls_idSubProceso;

								ls_idSubProceso = ls_solicitud.getIdSubproceso();

								if(StringUtils.isValidString(ls_idSubProceso))
								{
									Subproceso ls_subProceso;

									ls_subProceso = DaoCreator.getSubprocesoDAO(ldm_manager)
											                      .findById(ls_idProceso, ls_idSubProceso);

									if(ls_subProceso != null)
									{
										String ls_nombre;

										ls_nombre = ls_subProceso.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_SUBPROCESO,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_SUBPROCESO, ls_nombre
											    )
											);
									}
								}
							}

							{
								DatosAntSistema ldas_datos;

								ldas_datos = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
										                   .findByIdSolicitudOne(ls_idSolicitud);

								if(ldas_datos != null)
								{
									String ls_idCirculoGrab;
									Long   ll_idMatriculaGrab;

									ls_idCirculoGrab       = ldas_datos.getIdCirculo();
									ll_idMatriculaGrab     = ldas_datos.getIdMatriculaGrabacion();

									if(
									    StringUtils.isValidString(ls_idCirculoGrab)
										    && NumericUtils.isValidLong(ll_idMatriculaGrab)
									)
									{
										StringBuilder lsb_builder;

										lsb_builder = new StringBuilder(ls_idCirculoGrab);

										lsb_builder.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
										lsb_builder.append(ll_idMatriculaGrab);

										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_ID_MATRICULA_GRABACION,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO,
										        CodigoVariablePlantillaCommon.TAG_ID_MATRICULA_GRABACION,
										        lsb_builder.toString()
										    )
										);
									}
								}
							}

							{
								Proceso lp_proceso;

								lp_proceso = DaoCreator.getProcesoDAO(ldm_manager).findById(ls_idProceso);

								if(lp_proceso != null)
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_PROCESO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_PROCESO,
									        lp_proceso.getNombre()
									    )
									);
							}

							{
								Liquidacion ll_liquidacion;

								ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
										                       .findByIdSolicitudOne(ls_idSolicitud);

								if(ll_liquidacion != null)
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_FECHA_VENCIMIENTO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_FECHA_VENCIMIENTO,
									        StringUtils.getString(ll_liquidacion.getFechaVencimiento())
									    )
									);
							}

							{
								CuentaCupo lcc_cuentaCupo;

								lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(ldm_manager)
										                       .findByIdSolicitud(ls_idSolicitud);

								if(lcc_cuentaCupo != null)
								{
									{
										String ls_idEntidadExterna;

										ls_idEntidadExterna = lcc_cuentaCupo.getIdEntidadExterna();

										if(StringUtils.isValidString(ls_idEntidadExterna))
										{
											AccEntidadExterna laee_entidadExterna;

											laee_entidadExterna = DaoCreator.getAccEntidadExternaDAO(ldm_manager)
													                            .findByIdAccEntidadExterna(
													    ls_idEntidadExterna
													);

											if(laee_entidadExterna != null)
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_NOMBRE_EMPRESA,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_NOMBRE_EMPRESA,
												        StringUtils.getString(laee_entidadExterna.getNombre())
												    )
												);
										}
									}

									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_MONTO_MINIMO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MONTO_MINIMO,
									        StringUtils.getString(lcc_cuentaCupo.getValorMinimo())
									    )
									);
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_MONTO_MAXIMO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MONTO_MAXIMO,
									        StringUtils.getString(lcc_cuentaCupo.getValorMaximo())
									    )
									);
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_SALDO_ACTUAL,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_SALDO_ACTUAL,
									        StringUtils.getString(lcc_cuentaCupo.getSaldo())
									    )
									);
								}
							}
						}

						{
							String ls_caracter;

							ls_caracter = lcd_constantesDAO.findString(ConstanteCommon.TAG_LINK_DESACTIVAR_ALERTAS);

							if(StringUtils.isValidString(ls_caracter))
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_LINK_DESACTIVAR_ALERTAS,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO,
								        CodigoVariablePlantillaCommon.TAG_LINK_DESACTIVAR_ALERTAS, ls_caracter
								    )
								);
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = ConstanteCommon.TAG_LINK_DESACTIVAR_ALERTAS;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
							}
						}

						{
							String ls_caracter;

							ls_caracter = lcd_constantesDAO.findString(
								    ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_LINEA_ATENCION
								);

							if(StringUtils.isValidString(ls_caracter))
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_NUMERO_LINEA,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NUMERO_LINEA,
								        ls_caracter
								    )
								);
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = ConstanteCommon.CONSTANTE_RECIBO_LIQUIDACION_LINEA_ATENCION;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
							}
						}

						{
							String ls_caracter;

							ls_caracter = lcd_constantesDAO.findString(
								    ConstanteCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA
								);

							if(StringUtils.isValidString(ls_caracter))
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO,
								        CodigoVariablePlantillaCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA,
								        ls_caracter
								    )
								);
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = ConstanteCommon.TAG_URL_PAGINA_PRICIPAL_SEDE_ELECTRONICA;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
							}
						}

						{
							Collection<SolicitudCopias> lcsc_solicitudCopias;

							lcsc_solicitudCopias = DaoCreator.getSolicitudCopiasDAO(ldm_manager)
									                             .findByIdSolicitud(ls_idSolicitud);

							if(CollectionUtils.isValidCollection(lcsc_solicitudCopias))
							{
								Iterator<SolicitudCopias> lisc_iterator;

								lisc_iterator = lcsc_solicitudCopias.iterator();

								if((lisc_iterator != null) && lisc_iterator.hasNext())
								{
									SolicitudCopias lsc_solicitudCopias;

									lsc_solicitudCopias = lisc_iterator.next();

									if(lsc_solicitudCopias != null)
									{
										String ls_idTipoDocumental;

										ls_idTipoDocumental = lsc_solicitudCopias.getIdTipoDocumental();

										if(StringUtils.isValidString(ls_idTipoDocumental))
										{
											TipoDocumental ltd_tipoDocumental;

											ltd_tipoDocumental = DaoCreator.getTipoDocumentalDAO(ldm_manager)
													                           .findById(ls_idTipoDocumental);

											if(ltd_tipoDocumental != null)
											{
												String ls_nombre;

												ls_nombre = ltd_tipoDocumental.getNombre();

												if(StringUtils.isValidString(ls_nombre))
													lmstvemi_tags.put(
													    CodigoVariablePlantillaCommon.TAG_SOLICITUD_COPIAS,
													    new TipoVariableEMI(
													        TipoParametroCommon.CUERPO,
													        CodigoVariablePlantillaCommon.TAG_SOLICITUD_COPIAS,
													        ls_nombre
													    )
													);
											}
										}
									}
								}
							}
						}

						if(!StringUtils.isValidString(ls_idCirculo) && (ls_solicitud != null))
						{
							String ls_idUsuarioCreacion;

							ls_idUsuarioCreacion = ls_solicitud.getIdUsuarioCreacion();

							if(StringUtils.isValidString(ls_idUsuarioCreacion))
							{
								UsuarioCirculo luc_usuarioCirculo;

								luc_usuarioCirculo = DaoCreator.getUsuarioCirculoDAO(ldm_manager)
										                           .buscarUsuarioActivoMasAntiguo(
										    new UsuarioCirculo(ls_idUsuarioCreacion, null, EstadoCommon.SI, null, null)
										);

								if(luc_usuarioCirculo != null)
									ls_idCirculo = luc_usuarioCirculo.getIdCirculo();
							}
						}

						if(StringUtils.isValidString(ls_idCirculo))
						{
							CirculoRegistral lcr_circuloRegistral;

							lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(
								    ls_idCirculo
								);

							if(lcr_circuloRegistral != null)
							{
								String ls_nombreCirculoTurno;

								ls_nombreCirculoTurno = lcr_circuloRegistral.getNombre();

								if(StringUtils.isValidString(ls_nombreCirculoTurno))
								{
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP,
									        ls_nombreCirculoTurno
									    )
									);
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP_TURNO,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_NOMBRE_ORIP_TURNO, ls_nombreCirculoTurno
									    )
									);
								}

								{
									String ls_tipoOficina;

									ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

									if(StringUtils.isValidString(ls_tipoOficina))
									{
										String ls_descripcionTipo;

										if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
											ls_descripcionTipo = "PRINCIPAL";
										else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
											ls_descripcionTipo = "SECCIONAL";
										else
											ls_descripcionTipo = null;

										if(ls_descripcionTipo != null)
											lmstvemi_tags.put(
											    CodigoVariablePlantillaCommon.TAG_PRINCIPAL_SECCIONAL,
											    new TipoVariableEMI(
											        TipoParametroCommon.CUERPO,
											        CodigoVariablePlantillaCommon.TAG_PRINCIPAL_SECCIONAL,
											        ls_descripcionTipo
											    )
											);
									}
								}

								{
									OficinaOrigen loo_oficina;

									loo_oficina = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                    .findByIdCirculo(ls_idCirculo);

									if(loo_oficina != null)
									{
										{
											String ls_nombre;

											ls_nombre = loo_oficina.getNombre();

											if(StringUtils.isValidString(ls_nombre))
												lmstvemi_tags.put(
												    CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN,
												    new TipoVariableEMI(
												        TipoParametroCommon.CUERPO,
												        CodigoVariablePlantillaCommon.TAG_OFICINA_ORIGEN, ls_nombre
												    )
												);
										}

										{
											String ls_idPais;
											String ls_idDepartamento;
											String ls_idMunicipio;

											ls_idPais             = loo_oficina.getIdPais();
											ls_idDepartamento     = loo_oficina.getIdDepartamento();
											ls_idMunicipio        = loo_oficina.getIdMunicipio();

											if(
											    StringUtils.isValidString(ls_idDepartamento)
												    && StringUtils.isValidString(ls_idPais)
											)
											{
												Departamento ld_departamento;

												ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
														                        .findById(ls_idPais, ls_idDepartamento);

												if(
												    (ld_departamento != null)
													    && StringUtils.isValidString(ls_idMunicipio)
												)
												{
													Municipio lm_municipio;

													lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
															                     .findById(
															    ls_idPais, ls_idDepartamento, ls_idMunicipio
															);

													if(lm_municipio != null)
													{
														StringBuilder lsb_builder;

														lsb_builder = new StringBuilder(ld_departamento.getNombre());

														lsb_builder.append(
														    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
														);
														lsb_builder.append(lm_municipio.getNombre());

														lmstvemi_tags.put(
														    CodigoVariablePlantillaCommon.TAG_CIUDAD,
														    new TipoVariableEMI(
														        TipoParametroCommon.CUERPO,
														        CodigoVariablePlantillaCommon.TAG_CIUDAD,
														        lsb_builder.toString()
														    )
														);
													}
												}
											}
										}
									}
								}
							}
						}

						{
							Calendar lc_fecha;

							lc_fecha = Calendar.getInstance();

							{
								int li_dia;

								li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

								{
									String ls_dia;

									ls_dia = NumericUtils.convertirLetras(li_dia, false);

									if(StringUtils.isValidString(ls_dia))
										lmstvemi_tags.put(
										    CodigoVariablePlantillaCommon.TAG_DIAS,
										    new TipoVariableEMI(
										        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_DIAS,
										        ls_dia
										    )
										);
								}
							}

							{
								int    li_mes;
								String ls_mes;

								li_mes     = lc_fecha.get(Calendar.MONTH);
								ls_mes     = DateUtils.getMes(li_mes + 1);

								if(StringUtils.isValidString(ls_mes))
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_MES,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_MES, ls_mes
									    )
									);
							}
						}

						{
							Date   ld_fecha;
							String ls_fechaTxt;

							ld_fecha        = new Date();
							ls_fechaTxt     = DateUtils.convertirLetrasLarga(ld_fecha);

							if(StringUtils.isValidString(ls_fechaTxt))
								lmstvemi_tags.put(
								    CodigoVariablePlantillaCommon.TAG_FECHA_LARGA,
								    new TipoVariableEMI(
								        TipoParametroCommon.CUERPO, CodigoVariablePlantillaCommon.TAG_FECHA_LARGA,
								        ls_fechaTxt
								    )
								);
						}
					}

					{
						String ls_idMensajeComunicacion;

						ls_idMensajeComunicacion = amc_comunicado.getIdMensajeComunicaciones();

						if(StringUtils.isValidString(ls_idMensajeComunicacion))
						{
							String ls_idPersonaAlert;

							ls_idPersonaAlert = DaoCreator.getAlertaTitularDAO(ldm_manager)
									                          .findByComunicacion(ls_idMensajeComunicacion);

							if(StringUtils.isValidString(ls_idPersonaAlert))
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersonaAlert);

								if(lp_persona != null)
									lmstvemi_tags.put(
									    CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS,
									    new TipoVariableEMI(
									        TipoParametroCommon.CUERPO,
									        CodigoVariablePlantillaCommon.TAG_TITULAR_DE_DERECHOS,
									        lp_persona.getNombreCompleto()
									    )
									);
							}
						}
					}

					{
						TipoEntradaEnviarMensaje lteem_mensaje;
						TipoDestinatarioEMI      ltd_destinatario;
						TipoVariableEMI[]        ltveemi_variables;
						TipoDocumento[]          ltda_tipoDocumentos;
						String                   ls_canal;

						lteem_mensaje           = null;
						ltd_destinatario        = null;
						ltveemi_variables       = null;
						ltda_tipoDocumentos     = null;
						ls_canal                = StringUtils.getStringNotNull(amc_comunicado.getIdCanal());

						{
							String     ls_correo;
							String     ls_numero;
							BigInteger lbi_numeroCelular;

							ls_correo             = amc_comunicado.getCorreoElectronico();
							ls_numero             = amc_comunicado.getNumeroCelular();
							lbi_numeroCelular     = null;

							if(
							    StringUtils.isValidString(ls_numero)
								    && ExpresionRegular.getExpresionRegular().esTelefono(ls_numero)
								    && (ls_numero.length() == 10)
							)
								lbi_numeroCelular = BigInteger.valueOf(NumericUtils.getLong(ls_numero));

							ltd_destinatario = new TipoDestinatarioEMI(
								    (((StringUtils.isValidString(ls_correo))
									    && (ls_canal.equals(CanalCommon.ELECTRONICO))) ? ls_correo : null), null,
								    (((NumericUtils.isValidBigInteger(lbi_numeroCelular))
									    && (ls_canal.equals(CanalCommon.SMS))) ? lbi_numeroCelular : null)
								);
						}

						if(CollectionUtils.isValidCollection(lmstvemi_tags))
						{
							int li_contador;

							ltveemi_variables     = new TipoVariableEMI[lmstvemi_tags.size()];
							li_contador           = NumericUtils.DEFAULT_INT_VALUE;

							for(Map.Entry<String, TipoVariableEMI> lmstv_actual : lmstvemi_tags.entrySet())
							{
								if(lmstv_actual != null)
									ltveemi_variables[li_contador++] = lmstv_actual.getValue();
							}
						}

						if(CollectionUtils.isValidCollection(lcds_documentos))
						{
							int li_contador;

							ltda_tipoDocumentos     = new TipoDocumento[lcds_documentos.size()];
							li_contador             = 0;

							for(DocumentosSalida lds_actual : lcds_documentos)
							{
								if(lds_actual != null)
									ltda_tipoDocumentos[li_contador++] = new TipoDocumento(
										    NumericUtils.getBigInteger(lds_actual.getIdEcm()),
										    lds_actual.getIdNombreDocumento()
										);
							}
						}

						lteem_mensaje = new TipoEntradaEnviarMensaje(
							    amc_comunicado.getIdOrigen(), null, ClasificacionCommon.COMUNICACION, ls_canal,
							    ls_idCirculo, ls_nir, ls_idTurno, ltd_destinatario, ltda_tipoDocumentos, ls_idPlantilla,
							    ltveemi_variables
							);

						{
							AlertaTitularDAO latd_DAO;
							boolean          lb_intentarEnvio;
							long             ll_tiempoEspera;
							long             ll_limiteIntentos;

							latd_DAO              = DaoCreator.getAlertaTitularDAO(ldm_manager);
							lb_intentarEnvio      = true;
							ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
								    ConstanteCommon.JOB_ENVIO_COMUNICADOS_TIEMPO_ESPERA_INTENTO_ENVIO
								);
							ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
								    ConstanteCommon.JOB_ENVIO_COMUNICADOS_LIMITE_INTENTOS
								);

							while(lb_intentarEnvio && (ll_intentoActual < ll_limiteIntentos))
							{
								try
								{
									TipoSalidaEnviarMensaje ltsem_respuesta;

									ltsem_respuesta = ClienteEnviarMensaje.enviarMensaje(lteem_mensaje, as_endpoint);

									if(ltsem_respuesta != null)
									{
										Date   ld_fechaRespuesta;
										String ls_idMensaje;

										ld_fechaRespuesta     = obtenerDateDesdeCalendar(
											    ltsem_respuesta.getFechaRecepcion()
											);
										ls_idMensaje          = ltsem_respuesta.getIdentificadorGenerado();
										ls_mensaje            = ltsem_respuesta.getDescripcionMensaje();

										if(
										    StringUtils.isValidString(ls_idMensaje) && (ld_fechaRespuesta != null)
											    && StringUtils.isValidString(ls_mensaje)
										)
										{
											latd_DAO.deleteByMensajeComunicacion(
											    amc_comunicado.getIdMensajeComunicaciones()
											);
											DaoCreator.getMensajeComunicacionDAO(ldm_manager).deleteById(
											    amc_comunicado
											);
											DaoCreator.getMensajeComunicacionEnviadoDAO(ldm_manager)
												          .insert(
												    new MensajeComunicacionEnviado(
												        ls_idMensaje, NumericUtils.getLong(ll_idTurnoHistoria),
												        ls_idSolicitud, ld_fechaRespuesta, ls_mensaje, as_userId,
												        as_ipRemota
												    )
												);
											lb_incrementarIntento     = false;
											lb_intentarEnvio          = false;
										}
										else
											throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
								}
								catch(B2BException lb2be_e)
								{
									if((ll_intentoActual) >= ll_limiteIntentos)
										throw lb2be_e;

									ll_intentoActual++;
									lb_incrementarIntento     = true;
									ls_mensaje                = getErrorMessage(lb2be_e);

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
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje                = getErrorMessage(lb2be_e);
				lb_incrementarIntento     = true;

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_COMUNICACIONES, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(amc_comunicado.getIdMensajeComunicaciones())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje                = lie_e.getLocalizedMessage();
				lb_incrementarIntento     = true;

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.ENVIO_COMUNICACIONES, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(amc_comunicado.getIdMensajeComunicaciones())
				);

				Thread.currentThread().interrupt();

				throw new B2BException(ls_mensaje);
			}
			finally
			{
				if(lb_incrementarIntento)
					actualizarIntentosEnvio(amc_comunicado, ll_intentoActual, ls_mensaje, as_ipRemota, as_userId);

				ldm_manager.close();
			}
		}
		else
			throw new B2BException("Parametros no validos");
	}

	/**
	 * Actualizar intentos envio.
	 *
	 * @param amc_comunicado de amc comunicado
	 * @param ll_intentoActual de ll intento actual
	 * @param ls_mensaje de ls mensaje
	 * @param as_ipRemota de as ip remota
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void actualizarIntentosEnvio(
	    MensajeComunicacion amc_comunicado, long ll_intentoActual, String ls_mensaje, String as_ipRemota,
	    String as_userId
	)
	    throws B2BException
	{
		if(amc_comunicado != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				amc_comunicado.setIntentosFallidosEjecucionAutomatica(NumericUtils.getInteger(ll_intentoActual));
				amc_comunicado.setRespuestasEjecucionAutomatica(ls_mensaje);
				amc_comunicado.setIpModificacion(as_ipRemota);
				amc_comunicado.setIdUsuarioModificacion(as_userId);

				DaoCreator.getMensajeComunicacionDAO(ldm_manager).updateIntentoEnvios(amc_comunicado);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();
				clh_LOGGER.error("actualizarIntentosEnvio", lb2be_e);
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}
}
