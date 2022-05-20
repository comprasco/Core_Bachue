package com.bachue.snr.prosnr01.business.actuaciones.administrativas;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.MarcaAgua;
import com.bachue.snr.prosnr01.business.grabacion.GrabacionBusiness;
import com.bachue.snr.prosnr01.business.segundaInstancia.SegundaInstanciaBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDireccionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoTelefonoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCausalRechazoRecursoDAO;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.CalidadSolicitanteDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SoporteTrasladoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.notificaciones.PersonaNotificacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio  para la fase de actuaciones administrativas.
 *
 * @author Gabriel Arias.
 *
 */
public class ActuacionesAdministrativasBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ActuacionesAdministrativasBusiness.class);

	/** Propiedad isib segunda instancia business. */
	private static SegundaInstanciaBusiness isib_segundaInstanciaBusiness;

	/**
	 * Método encargado de consultar los datos de personas vinculadas a una solicitud con proceso de recursos.
	 *
	 * @param ar_registro Argumento de tipo <code>Registro</code> que contiene los criterios para realizar la consulta.
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized Registro buscarDatosPorPersona(Registro ar_registro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = new Registro();

		try
		{
			if(ar_registro != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = ar_registro.getSolicitud();

				if(ls_solicitud != null)
				{
					Persona lp_persona;

					lp_persona = ar_registro.getPersona();

					if(lp_persona != null)
					{
						String ls_idPersona;

						ls_idPersona = lp_persona.getIdPersona();

						if(StringUtils.isValidString(ls_idPersona))
						{
							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
									                     .findById(ls_solicitud.getIdSolicitud());

							if(ls_solicitud != null)
							{
								{
									String ls_idDireccionNotificacion;

									ls_idDireccionNotificacion = ls_solicitud.getIdDireccion();

									if(StringUtils.isValidString(ls_idDireccionNotificacion))
									{
										PersonaDireccion lpd_personaDireccion;

										lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(ldm_manager)
												                             .findById(
												    ls_idPersona, ls_idDireccionNotificacion
												);

										if(lpd_personaDireccion != null)
										{
											String ls_tipoDireccion;

											ls_tipoDireccion = lpd_personaDireccion.getTipoDireccion();

											if(StringUtils.isValidString(ls_tipoDireccion))
											{
												if(ls_tipoDireccion.equalsIgnoreCase(TipoDireccionCommon.RESIDENCIA))
													lr_datos.setDireccionResidencia(lpd_personaDireccion);

												if(
												    ls_tipoDireccion.equalsIgnoreCase(
													        TipoDireccionCommon.CORRESPONDENCIA
													    )
												)
													lr_datos.setDireccionCorrespondencia(lpd_personaDireccion);
											}
										}
									}
								}

								{
									String ls_idDireccionComunicacion;

									ls_idDireccionComunicacion = ls_solicitud.getIdDireccionComunicacion();

									if(StringUtils.isValidString(ls_idDireccionComunicacion))
									{
										PersonaDireccion lpd_personaDireccion;

										lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(ldm_manager)
												                             .findById(
												    ls_idPersona, ls_idDireccionComunicacion
												);

										if(lpd_personaDireccion != null)
										{
											String ls_tipoDireccion;

											ls_tipoDireccion = lpd_personaDireccion.getTipoDireccion();

											if(StringUtils.isValidString(ls_tipoDireccion))
											{
												if(ls_tipoDireccion.equalsIgnoreCase(TipoDireccionCommon.RESIDENCIA))
													lr_datos.setDireccionResidencia(lpd_personaDireccion);

												if(
												    ls_tipoDireccion.equalsIgnoreCase(
													        TipoDireccionCommon.CORRESPONDENCIA
													    )
												)
													lr_datos.setDireccionCorrespondencia(lpd_personaDireccion);
											}
										}
									}
								}

								{
									String ls_idTelefonoNotificacion;

									ls_idTelefonoNotificacion = ls_solicitud.getIdTelefono();

									if(StringUtils.isValidString(ls_idTelefonoNotificacion))
									{
										PersonaTelefono lpt_personaTelefono;

										lpt_personaTelefono = DaoCreator.getPersonaTelefonoDAO(ldm_manager)
												                            .findById(
												    ls_idPersona, ls_idTelefonoNotificacion
												);

										if(lpt_personaTelefono != null)
										{
											String ls_tipoTelefono;

											ls_tipoTelefono = lpt_personaTelefono.getTipoTelefono();

											if(StringUtils.isValidString(ls_tipoTelefono))
											{
												if(ls_tipoTelefono.equalsIgnoreCase(TipoTelefonoCommon.FIJO))
													lr_datos.setTelefonoFijo(lpt_personaTelefono);

												if(ls_tipoTelefono.equalsIgnoreCase(TipoTelefonoCommon.MOVIL))
													lr_datos.setTelefonoMovil(lpt_personaTelefono);
											}
										}
									}
								}

								{
									String ls_idCorreoNotificacion;
									String ls_idCorreoComunicacion;
									String ls_idCorreo;

									ls_idCorreoNotificacion     = ls_solicitud.getIdCorreoElectronico();
									ls_idCorreoComunicacion     = ls_solicitud.getIdCorreoElectronicoComunicacion();
									ls_idCorreo                 = StringUtils.isValidString(ls_idCorreoNotificacion)
										? ls_idCorreoNotificacion
										: (StringUtils.isValidString(ls_idCorreoComunicacion) ? ls_idCorreoComunicacion
										                                                      : null);

									if(StringUtils.isValidString(ls_idCorreo))
									{
										PersonaCorreoElectronico lpce_personaCorreoElectronico;

										lpce_personaCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(
											    ldm_manager
											).findById(ls_idPersona, ls_idCorreo);

										if(lpce_personaCorreoElectronico != null)
											lr_datos.setPersonaCorreoElectronico(lpce_personaCorreoElectronico);
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

			clh_LOGGER.error("buscarDatosPorPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Método encargado de consultar las personas vinculadas a una solicitud con proceso de recursos.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id solicitud a consultar.
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized Registro buscarPersonasAsociadasRecursos(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lr_datos        = null;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Collection<SolicitudAsociada> lcs_solicitudAsociada;

				lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
						                              .findAllByIdSolicitud(as_idSolicitud, false);

				if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
				{
					Collection<Persona> lcp_personas;
					SolicitudDAO        lsd_DAO;
					PersonaDAO          lpd_DAO;

					lcp_personas     = new ArrayList<Persona>();
					lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
					lpd_DAO          = DaoCreator.getPersonaDAO(ldm_manager);

					for(SolicitudAsociada lsa_iterador : lcs_solicitudAsociada)
					{
						if(lsa_iterador != null)
						{
							String ls_idSolicitudAsociada;

							ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

							if(StringUtils.isValidString(ls_idSolicitudAsociada))
							{
								Solicitud ls_solicitud;

								ls_solicitud = lsd_DAO.findById(ls_idSolicitudAsociada);

								if(ls_solicitud != null)
								{
									String ls_idProceso;

									ls_idProceso = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idProceso)
										    && (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
										    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48))
									)
									{
										Persona lp_persona;

										lp_persona = lpd_DAO.findById(ls_solicitud.getIdPersona());

										if(lp_persona != null)
										{
											lp_persona.setSolicitud(ls_solicitud);

											lcp_personas.add(lp_persona);
										}
									}
								}
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcp_personas))
					{
						lr_datos = new Registro();

						lr_datos.setListadoPersona(lcp_personas);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarPersonasAsociadasRecursos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_datos;
	}

	/**
	 * Cargar datos actuaciones administrativas.
	 *
	 * @param aaa_parametros de aaa parametros
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tag plantilla resolucion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		return cargarDatosActuacionesAdministrativas(aaa_parametros, false, as_userId, as_remoteIp);
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encontrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion cargarDatosActuacionesAdministrativas(
	    TagPlantillaResolucion aaa_parametros, boolean ab_etapa651, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = new TagPlantillaResolucion();

		if(aaa_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idTurno;
				Turno  lt_turno;

				ls_idTurno     = aaa_parametros.getIdTurno();
				lt_turno       = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

				if(lt_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						boolean lb_recurso;
						boolean lb_traslado;
						boolean lb_individual;
						String  ls_idProceso;

						lb_recurso        = aaa_parametros.isRecurso();
						lb_traslado       = aaa_parametros.isTraslado();
						lb_individual     = true;
						ls_idProceso      = null;

						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

							if(ls_solicitud != null)
							{
								String ls_idSubProceso;

								ls_idSubProceso     = ls_solicitud.getIdSubproceso();
								ls_idProceso        = ls_solicitud.getIdProceso();

								if(StringUtils.isValidString(ls_idSubProceso))
								{
									lb_individual = !(ls_idSubProceso.equalsIgnoreCase(IdentificadoresCommon.NUM2));

									if(
									    !lb_traslado
										    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CORRECCION_EXTERNA)
									)
									{
										Persona lp_persona;

										lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
												                   .findById(ls_solicitud.getIdPersona());

										laa_actuacionesAdministrativas.setPersona(lp_persona);
									}
								}

								{
									String ls_idCalidadSolicitante;

									ls_idCalidadSolicitante = ls_solicitud.getIdCalidadSolicitante();

									if(StringUtils.isValidString(ls_idCalidadSolicitante))
									{
										CalidadSolicitante lcs_calidadSolicitante;

										lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(ldm_manager)
												                               .findById(ls_idCalidadSolicitante);

										if(lcs_calidadSolicitante != null)
											ls_solicitud.setNombreCalidadSolicitante(
											    lcs_calidadSolicitante.getNombre()
											);
									}
								}

								laa_actuacionesAdministrativas.setSolicitud(ls_solicitud);
							}
						}

						{
							long    ll_idMotivo;
							boolean lb_proyectar        = aaa_parametros.isProyectar();
							boolean lb_planeacion       = aaa_parametros.isPlaneacion();
							boolean lb_segundaInstancia = aaa_parametros.isSegundaInstancia();

							ll_idMotivo                 = aaa_parametros.getIdMotivo();

							if(ll_idMotivo > 0)
							{
								TagPlantillaResolucion laa_temp;

								if(lb_proyectar || lb_planeacion)
									laa_temp = plantillaDatosPorIdMotivoTraslados(
										    ldm_manager, ll_idMotivo, false, true, false, lb_planeacion
										);
								else if(lb_segundaInstancia)
									laa_temp = plantillaDatosPorIdMotivoSegundaInstancia(
										    ldm_manager, ll_idMotivo, true, true
										);
								else
									laa_temp = lb_recurso
										? plantillaDatosPorIdMotivoRecursos(
										    ldm_manager, ls_idProceso, ll_idMotivo, true
										)
										: (lb_traslado
										? plantillaDatosPorIdMotivoTraslados(
										    ldm_manager, ll_idMotivo, lb_individual, true, ab_etapa651, false
										) : plantillaDatosPorIdMotivo(ldm_manager, ll_idMotivo, true));

								if(laa_temp != null)
								{
									String ls_plantilla;

									ls_plantilla = laa_temp.getIdPlantilla();

									if(StringUtils.isValidString(ls_plantilla))
									{
										OficiosTexto        lot_data;
										Map<String, String> lmss_data;
										long                ll_idEtapa;

										lot_data      = new OficiosTexto();
										lmss_data     = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
												                      .findByIdPlantillaMap(ls_plantilla);

										lot_data.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(aaa_parametros.getIdTurnoHistoria())
										);
										ll_idEtapa = lb_recurso
											? EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
											: EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;

										if(lb_recurso)
											llenarOficiosTextoRecursos(
											    lot_data, lmss_data, lt_turno,
											    NumericUtils.getLong(aaa_parametros.getIdTurnoHistoria()), ldm_manager
											);
										else if(lb_traslado || lb_segundaInstancia)
											llenarOficiosTextoTraslados(lot_data, lmss_data);
										else
											llenarOficiosTexto(
											    ldm_manager, lot_data, lmss_data, lt_turno, ll_idMotivo,
											    NumericUtils.getLongWrapper(ll_idEtapa)
											);

										{
											Collection<PersonaNotificacion> lcpn_resultadosNotificacion;

											lcpn_resultadosNotificacion = DaoCreator.getPersonaNotificacionDAO(
												    ldm_manager
												)
													                                    .findByMaxDecisionPlantilla(
													    ls_idTurno, NumericUtils.getLongWrapper(ll_idEtapa)
													);

											if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
											{
												PersonaDAO            lpd_DAO;
												SolicitudDAO          lsd_DAO;
												CalidadSolicitanteDAO lcsd_DAO;

												lpd_DAO      = DaoCreator.getPersonaDAO(ldm_manager);
												lsd_DAO      = DaoCreator.getSolicitudDAO(ldm_manager);
												lcsd_DAO     = DaoCreator.getCalidadSolicitanteDAO(ldm_manager);

												for(PersonaNotificacion lpn_iterador : lcpn_resultadosNotificacion)
												{
													if(lpn_iterador != null)
													{
														{
															Persona lp_persona;

															lp_persona = lpd_DAO.findById(lpn_iterador.getIdPersona());

															if(lp_persona != null)
															{
																lpn_iterador.setTipoDocumento(
																    lp_persona.getIdDocumentoTipo()
																);
																lpn_iterador.setDocumento(
																    lp_persona.getNumeroDocumento()
																);
															}
														}

														{
															Solicitud ls_solicitud;

															ls_solicitud = lsd_DAO.findById(ls_idSolicitud);

															if(ls_solicitud != null)
															{
																CalidadSolicitante lcs_calidadSolicitante;

																lcs_calidadSolicitante = lcsd_DAO.findById(
																	    ls_solicitud.getIdCalidadSolicitante()
																	);

																if(lcs_calidadSolicitante != null)
																	lpn_iterador.setCalidadEnQueActua(
																	    lcs_calidadSolicitante.getNombre()
																	);
															}
														}

														lpn_iterador.setRegistroPrecargado(true);
													}
												}

												laa_actuacionesAdministrativas.setResultadosNotificacion(
												    lcpn_resultadosNotificacion
												);
											}
										}

										if(ll_idMotivo == MotivoTramiteCommon.AUTO_DE_PRUEBAS)
										{
											String     ls_idConstante;
											Constantes lc_constante;
											BigInteger lbi_entero;

											ls_idConstante     = ConstanteCommon.DIAS_APORTAR_PRUEBAS_AUTO_PRUEBAS;
											lc_constante       = DaoCreator.getConstantesDAO(ldm_manager)
													                           .findById(ls_idConstante);

											if(lc_constante == null)
											{
												Object[] loa_messageArgs;

												loa_messageArgs        = new String[1];
												loa_messageArgs[0]     = ls_idConstante;

												throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
											}

											lbi_entero = lc_constante.getEntero();

											if(NumericUtils.isValidBigInteger(lbi_entero))
											{
												int li_dias;

												li_dias = NumericUtils.getInt(lbi_entero);

												if(li_dias > 0)
												{
													Collection<Long> lcl_dias;

													lcl_dias = new ArrayList<Long>();

													for(int li_contador = 1; li_contador <= li_dias; li_contador++)
														lcl_dias.add(NumericUtils.getLongWrapper(li_contador));

													laa_actuacionesAdministrativas.setListadoDias(lcl_dias);
												}
											}
										}

										lot_data.setProyectar(lb_proyectar);
										lot_data.setPlaneacion(lb_planeacion);
										lot_data.setSegundaInstancia(lb_segundaInstancia);
										laa_actuacionesAdministrativas.setOficiosTexto(lot_data);
										laa_actuacionesAdministrativas.setTipoArchivo(laa_temp.getTipoArchivo());
										laa_actuacionesAdministrativas.setArchivo(
										    generarDocumentoAutoResolucion(
										        lot_data, as_userId, as_remoteIp, ll_idMotivo, false, false, lb_recurso,
										        lb_traslado, lb_individual, ab_etapa651, ldm_manager
										    )
										);
									}
								}
								else
								{
									Object[] loa_messageArgs = new String[1];

									loa_messageArgs[0] = StringUtils.getString(ll_idMotivo);

									throw new B2BException(
									    ErrorKeys.ERROR_NO_SE_ENCONTRARON_DATOS_ID_MOTIVO, loa_messageArgs
									);
								}

								if(
								    lb_traslado
									    && (ll_idMotivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA)
								)
								{
									TagPlantillaResolucion laa_temp2;

									laa_temp2 = plantillaDatosPorIdMotivoTraslados(ldm_manager, ll_idMotivo, false);

									if(laa_temp2 != null)
									{
										String ls_plantilla;

										ls_plantilla = laa_temp2.getIdPlantilla();

										if(StringUtils.isValidString(ls_plantilla))
										{
											OficiosTexto        lot_data;
											Map<String, String> lmss_data;

											lot_data      = new OficiosTexto();
											lmss_data     = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
													                      .findByIdPlantillaMap(ls_plantilla);

											lot_data.setIdTurnoHistoria(
											    NumericUtils.getLongWrapper(aaa_parametros.getIdTurnoHistoria())
											);

											llenarOficiosTextoTraslados(lot_data, lmss_data);

											laa_actuacionesAdministrativas.setOficiosTexto2(lot_data);
											laa_actuacionesAdministrativas.setTipoArchivo2(laa_temp2.getTipoArchivo());
											laa_actuacionesAdministrativas.setArchivo2(
											    generarDocumentoAutoResolucion(
											        lot_data, as_userId, as_remoteIp, ll_idMotivo, false, false,
											        lb_recurso, lb_traslado, false, ab_etapa651, ldm_manager
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
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Metodo encargado de cargar la información de rechaza recursos.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encontrados para rechaza recursos.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion cargarDatosRechazaRecursos(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = new TagPlantillaResolucion();

		if(aaa_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idTurno;
				Turno  lt_turno;

				ls_idTurno     = aaa_parametros.getIdTurno();
				lt_turno       = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

				if(lt_turno != null)
				{
					Collection<CausalRechazoRecurso> lccrr_causalesRechazoRecurso;
					boolean                          lb_registrosCargados;

					lccrr_causalesRechazoRecurso     = DaoCreator.getCausalRechazoRecursoDAO(ldm_manager).findAll();
					lb_registrosCargados             = false;

					if(CollectionUtils.isValidCollection(lccrr_causalesRechazoRecurso))
					{
						AccCausalRechazoRecursoDAO lacrrd_DAO;

						lacrrd_DAO = DaoCreator.getAccCausalRechazoRecursoDAO(ldm_manager);

						for(CausalRechazoRecurso lcrr_iterador : lccrr_causalesRechazoRecurso)
						{
							if(lcrr_iterador != null)
							{
								String ls_idCausalRechazoRecurso;

								ls_idCausalRechazoRecurso = lcrr_iterador.getIdCausalRechazoRecurso();

								if(StringUtils.isValidString(ls_idCausalRechazoRecurso))
								{
									int     li_contador;
									boolean lb_seleccionado;

									li_contador         = lacrrd_DAO.findCountByIdCausalIdTurno(
										    ls_idCausalRechazoRecurso, ls_idTurno
										);
									lb_seleccionado     = li_contador > 0;

									if(lb_seleccionado)
										lb_registrosCargados = true;

									lcrr_iterador.setSeleccionado(lb_seleccionado);
								}
							}
						}
					}

					{
						Long                ll_idTurnoHistoria;
						OficiosTexto        lot_oficiosTexto;
						Map<String, String> lmss_data;

						ll_idTurnoHistoria     = NumericUtils.getLongWrapper(aaa_parametros.getIdTurnoHistoria());
						lot_oficiosTexto       = DaoCreator.getOficiosTextoDAO(ldm_manager)
								                               .findByTurnoHistoria(ll_idTurnoHistoria);

						if(lot_oficiosTexto == null)
							lot_oficiosTexto = new OficiosTexto();

						lmss_data = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
								                  .findByIdPlantillaMap(
								    ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_RECURSO
								);

						lot_oficiosTexto.setIdTurnoHistoria(ll_idTurnoHistoria);

						llenarOficiosTextoRecursos(
						    lot_oficiosTexto, lmss_data, lt_turno,
						    NumericUtils.getLong(aaa_parametros.getIdTurnoHistoria()), ldm_manager
						);

						aaa_parametros.setOficiosTexto(lot_oficiosTexto);
						laa_actuacionesAdministrativas.setOficiosTexto(lot_oficiosTexto);
					}

					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lt_turno.getIdSolicitud());

						laa_actuacionesAdministrativas.setSolicitud(ls_solicitud);
					}

					laa_actuacionesAdministrativas.setCausalesRechazaRecurso(lccrr_causalesRechazoRecurso);

					if(lb_registrosCargados)
						aaa_parametros.setCausalesRechazaRecurso(lccrr_causalesRechazoRecurso);

					laa_actuacionesAdministrativas.setArchivo(
					    generarResolucionRechazaRecurso(
					        aaa_parametros, as_userId, as_remoteIp, false, false, ldm_manager
					    )
					);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Método encargado de validar si se enviaron documentos al OWCC por id turno historia
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para realizar la consulta.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo <code>boolean</code> que determina si se enviaron los documentos al OWCC o no.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized boolean documentosEnviadosOWCC(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		boolean    lb_enviados;
		DAOManager ldm_manager;

		lb_enviados     = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = aaa_actuacionesAdministrativas.getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
			{
				Collection<DocumentosSalida> lcds_documentosSalida;

				lcds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                              .findByIdTurnoHistoria(
						    NumericUtils.getInteger(ls_idTurnoHistoria), true
						);

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
				{
					Iterator<DocumentosSalida> lids_documentoSalida;
					boolean                    lb_tmp;

					lids_documentoSalida     = lcds_documentosSalida.iterator();
					lb_tmp                   = true;

					while(lids_documentoSalida.hasNext() && lb_tmp)
					{
						DocumentosSalida lds_iterador;

						lds_iterador = lids_documentoSalida.next();

						if(lds_iterador != null)
						{
							lb_enviados     = StringUtils.isValidString(lds_iterador.getIdEcm())
									&& StringUtils.isValidString(lds_iterador.getIdNombreDocumento());

							lb_tmp = lb_enviados;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_enviados;
	}

	/**
	 * Método encargado de validar si se enviaron documentos al OWCC por id solicitud.
	 *
	 * @param ls_idSolicitud de ls id solicitud
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo <code>boolean</code> que determina si se enviaron los documentos al OWCC o no.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized boolean documentosEnviadosOWCCBySolicitud(
	    String ls_idSolicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		boolean    lb_enviados;
		DAOManager ldm_manager;

		lb_enviados     = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Collection<DocumentosSalida> lcds_documentosSalida;
				DocumentosSalida             lds_docTmp;

				lds_docTmp = new DocumentosSalida();
				lds_docTmp.setIdSolicitud(ls_idSolicitud);
				lds_docTmp.setEstado(EstadoCommon.A);

				lcds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						                              .findByIdSolicitudEstado(lds_docTmp);

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
				{
					Iterator<DocumentosSalida> lids_documentoSalida;
					boolean                    lb_tmp;

					lids_documentoSalida     = lcds_documentosSalida.iterator();
					lb_tmp                   = true;

					while(lids_documentoSalida.hasNext() && lb_tmp)
					{
						DocumentosSalida lds_iterador;

						lds_iterador = lids_documentoSalida.next();

						if(lds_iterador != null)
						{
							lb_enviados     = StringUtils.isValidString(lds_iterador.getIdEcm())
									&& StringUtils.isValidString(lds_iterador.getIdNombreDocumento());

							lb_tmp = lb_enviados;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("documentosEnviadosOWCCBySolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_enviados;
	}

	/**
	 * Método encargado de enviar al aprobador rechazo recurso.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para enviar al aprobador rechazo recurso.
	 * @param ab_etapa460 Variable tipo boolean que indica si proviene de la etapa 460.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized void enviarAlAprobadorRechazoRecurso(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, boolean ab_etapa460, String as_userId, String as_remoteIp,
	    long al_motivo
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actuacionesAdministrativas != null) && (al_motivo > 0))
			{
				TurnoHistoria lth_turnoHistoria;
				MotivoTramite lmt_motivoTramite;

				lth_turnoHistoria     = new TurnoHistoria();
				lmt_motivoTramite     = new MotivoTramite();

				lth_turnoHistoria.setIdTurnoHistoria(
				    NumericUtils.getLongWrapper(aaa_actuacionesAdministrativas.getIdTurnoHistoria())
				);

				lmt_motivoTramite.setIdMotivo(al_motivo);
				lmt_motivoTramite.setIdEtapaOrigen(
				    ab_etapa460 ? EtapaCommon.ID_ETAPA_460
				                : EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS
				);

				terminarTurnoHistoriaYCrearEtapa(
				    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp, EstadoCommon.TERMINADA
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAlAprobadorRechazoRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de aprobar la prorroga de documentación.
	 *
	 * @param ath_th Objeto que contiene la información del proceso.
	 * @param as_userId Variable String que contiene el usuario que realiza la operación
	 * @param as_remoteIp Variable String que contiene la ip remota desde donde se realiza la operación
	 * @return boolean que válida si el proceso terminó exitosamente.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean enviarAprobarProrrogaDocumentacion(
	    TurnoHistoria ath_th, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				TurnoHistoriaDAO lth_DAO;

				lth_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ath_th      = lth_DAO.findById(ath_th);

				if(ath_th != null)
				{
					Long ll_idMotivo;

					ll_idMotivo = NumericUtils.getLongWrapper(MotivoTramiteCommon.APRUEBA_PRORROGA_DOCUMENTACION);

					if(NumericUtils.isValidLong(ll_idMotivo))
					{
						MotivoTramite lmt_motivo;

						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdMotivo(NumericUtils.getLong(ll_idMotivo));
						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							Map<TurnoHistoria, Boolean> lmthb_turnos;

							lmthb_turnos = getFirmaMasivaBusiness()
									               .turnosDerivados(
									    ath_th, EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS,
									    ldm_manager, true
									);

							if(CollectionUtils.isValidCollection(lmthb_turnos))
							{
								ProcedimientosDAO lp_DAO;
								String            ls_descripcionMotivo;
								String            ls_idUsuarioModificacion;
								String            ls_ipModificacion;

								lp_DAO                       = DaoCreator.getProcedimientosDAO(ldm_manager);
								ls_descripcionMotivo         = lmt_motivo.getDescripcion();
								ls_idUsuarioModificacion     = as_userId;
								ls_ipModificacion            = as_remoteIp;

								for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnos.entrySet())
								{
									TurnoHistoria lth_actual;

									lth_actual = lmthb_iterador.getKey();

									if(lth_actual != null)
									{
										lth_actual.setObservaciones(ath_th.getObservaciones());
										lth_actual.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_actual.setMotivo(ls_descripcionMotivo);
										lth_actual.setIdMotivo(ll_idMotivo);
										lth_actual.setIdUsuarioModificacion(ls_idUsuarioModificacion);
										lth_actual.setIpModificacion(ls_ipModificacion);

										lth_DAO.insertOrUpdate(lth_actual, false);

										lp_DAO.spCreateStage(lth_actual);

										lb_return = true;
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

			clh_LOGGER.error("enviarAprobarProrrogaDocumentacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de enviar al responsable de actuaciones administrativas.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para enviar al responsable de actuaciones administrativas.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized void enviarResponsableActuacionesAdmin(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actuacionesAdministrativas != null) && (al_motivo > 0))
			{
				{
					Collection<TipoDocumental> lctd_tiposDocumentales;
					Solicitud                  ls_solicitud;
					SolicitudDAO               ls_solicitudDAO;

					lctd_tiposDocumentales     = aaa_actuacionesAdministrativas.getTiposDocumentales();
					ls_solicitud               = aaa_actuacionesAdministrativas.getSolicitud();
					ls_solicitudDAO            = DaoCreator.getSolicitudDAO(ldm_manager);

					if(ls_solicitud == null)
						ls_solicitud = ls_solicitudDAO.findSolicitudByIdTurno(
							    aaa_actuacionesAdministrativas.getIdTurno()
							);

					if(
					    CollectionUtils.isValidCollection(lctd_tiposDocumentales) && (ls_solicitud != null)
						    && !aaa_actuacionesAdministrativas.isNoSalvarCompletitudDocumental()
					)
					{
						AccCompletitudDocumentalDAO lacdd_DAO;

						lacdd_DAO = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);

						for(TipoDocumental ltd_iterador : lctd_tiposDocumentales)
						{
							if(ltd_iterador != null)
							{
								AccCompletitudDocumental lacd_accCompletitudDocumental;

								lacd_accCompletitudDocumental = new AccCompletitudDocumental();

								lacd_accCompletitudDocumental.setIdSolicitud(ls_solicitud.getIdSolicitud());
								lacd_accCompletitudDocumental.setIdTurno(aaa_actuacionesAdministrativas.getIdTurno());
								lacd_accCompletitudDocumental.setIdProceso(ls_solicitud.getIdProceso());
								lacd_accCompletitudDocumental.setIdSubproceso(ls_solicitud.getIdSubproceso());
								lacd_accCompletitudDocumental.setIdTipoDocumental(ltd_iterador.getIdTipoDocumental());
								lacd_accCompletitudDocumental.setObservaciones(ltd_iterador.getObservaciones());
								lacd_accCompletitudDocumental.setEntregado(EstadoCommon.N);
								lacd_accCompletitudDocumental.setObligatorioTipoDocumental(
								    ltd_iterador.isSeleccionado() ? EstadoCommon.S : EstadoCommon.N
								);
								lacd_accCompletitudDocumental.setIdUsuarioCreacion(as_userId);
								lacd_accCompletitudDocumental.setIpCreacion(as_remoteIp);
								lacd_accCompletitudDocumental.setDigitalizado(EstadoCommon.N);

								lacdd_DAO.insert(lacd_accCompletitudDocumental);
							}
						}
					}
				}

				{
					TurnoHistoria lth_turnoHistoria;
					MotivoTramite lmt_motivoTramite;

					lth_turnoHistoria     = new TurnoHistoria();
					lmt_motivoTramite     = new MotivoTramite();

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                          .findById(
							    NumericUtils.getLongWrapper(aaa_actuacionesAdministrativas.getIdTurnoHistoria())
							);

					if(lth_turnoHistoria != null)
					{
						long ll_idEtapa;

						ll_idEtapa = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

						{
							String ls_observaciones;

							ls_observaciones = aaa_actuacionesAdministrativas.getObservaciones();

							if(
							    ((al_motivo == MotivoTramiteCommon.ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS)
								    && (ll_idEtapa != EtapaCommon.ID_ETAPA_460))
								    && !StringUtils.isValidString(ls_observaciones)
							)
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);

							lth_turnoHistoria.setObservaciones(ls_observaciones);
						}

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
						{
							String ls_idSubProceso;

							ls_idSubProceso = lth_turnoHistoria.getIdSubproceso();

							if(StringUtils.isValidString(ls_idSubProceso))
							{
								if(ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2))
									lmt_motivoTramite.setIdMotivo(
									    MotivoTramiteCommon.ACEPTACION_TRASLADO_PROYECTAR_RESOLUCION_20
									);
								else
									lmt_motivoTramite.setIdMotivo(
									    MotivoTramiteCommon.ACEPTACION_TRASLADO_PROYECTAR_RESOLUCION
									);
							}
							else
								lmt_motivoTramite.setIdMotivo(
								    MotivoTramiteCommon.ACEPTACION_TRASLADO_PROYECTAR_RESOLUCION
								);

							lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);

							generarComunicadoResolucionTraslado(lth_turnoHistoria, as_userId, as_remoteIp, true);
						}
						else
						{
							lmt_motivoTramite.setIdMotivo(al_motivo);
							lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);
						}

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
							    EstadoCommon.TERMINADA, true, true, true, true
							);
						else
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
							    EstadoCommon.TERMINADA
							);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarResponsableActuacionesAdmin", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de generar el documento comunicado.
	 *@param as_parametros con los parametros del turno Historia
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarComunicadoResolucionTraslado(
	    TurnoHistoria as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((as_parametros != null))
				lba_return = generarComunicadoResolucionTraslado(
					    as_parametros, as_userId, as_remoteIp, ab_salvar, false, ldm_manager
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarComunicadoResolucionTraslado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método para la generación del documento fijacion ó desfijacion en etapa 190
	 * @param as_parametros Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia de la transacción.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene usuario de la transacción.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario de la transacción.
	 * @param ab_salvar Argumento de tipo <code>Boolean</code> que indica si se desea guardar en BD.
	 * @param ab_firma Argumento de tipo <code>Boolean</code> que indica si viene del JOB de aprobación.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que contiene  el manager del manejo de la transacción.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException
	 */
	public byte[] generarComunicadoResolucionTraslado(
	    TurnoHistoria as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(as_parametros != null)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(as_parametros);

			if(lth_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = lth_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_COMUNICADO_RESOL_ACEPTACION_TRASLADO;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
						}
						else
						{
							Map<String, String> lmss_datos;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;
							CirculoRegistral    lcr_circuloRegistral;

							lmss_datos               = null;
							ls_idCirculo             = lt_turno.getIdCirculo();
							ls_idSolicitud           = lt_turno.getIdSolicitud();
							ls_plantilla             = new String(lba_plantilla);
							lcr_circuloRegistral     = null;

							if(StringUtils.isValidString(ls_idCirculo))
							{
								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(lcr_circuloRegistral != null)
								{
									ls_tag = "<TAG_NOMBRE_ORIP>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_tipoOficina;

										ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

										if(StringUtils.isValidString(ls_tipoOficina))
										{
											if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
												ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
											else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
												ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
										}
									}

									ls_tag = "<TAG_MUN_OFI_ORIGEN>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
									{
										Municipio lm_municipio;

										lm_municipio     = new Municipio();

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
												                     .findByIdCirculo(ls_idCirculo);

										if(lm_municipio != null)
										{
											String ls_munOficinaOrigen;
											ls_munOficinaOrigen = lm_municipio.getNombre();

											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_munOficinaOrigen)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
										}
									}

									ls_plantilla = escribirTagFechaLarga(ls_plantilla);
								}
							}

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idPersona;

									ls_idPersona = ls_solicitud.getIdPersona();

									if(StringUtils.isValidString(ls_idPersona))
									{
										String ls_idDireccion;

										ls_idDireccion     = ls_solicitud.getIdDireccion();
										ls_tag             = "<TAG_NOMBRE_INTERESADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											Persona lp_persona;

											lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

											if(lp_persona != null)
											{
												StringBuilder lsb_nombre;
												String        ls_nombre;
												String        ls_segundoApellido;
												String        ls_segundoNombre;

												lsb_nombre             = new StringBuilder();
												ls_segundoApellido     = lp_persona.getSegundoApellido();
												ls_segundoNombre       = lp_persona.getSegundoNombre();

												lsb_nombre.append(lp_persona.getPrimerNombre());

												if(StringUtils.isValidString(ls_segundoNombre))
													lsb_nombre.append(
													    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoNombre
													);

												lsb_nombre.append(
												    IdentificadoresCommon.ESPACIO_VACIO
												    + lp_persona.getPrimerApellido()
												);

												if(StringUtils.isValidString(ls_segundoApellido))
													lsb_nombre.append(
													    IdentificadoresCommon.ESPACIO_VACIO + ls_segundoApellido
													);

												ls_nombre = lsb_nombre.toString();

												if(StringUtils.isValidString(ls_nombre))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
											}
										}

										ls_tag = "<TAG_CORREO_ELECTRONICO>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_idCorreo;

											ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

											if(StringUtils.isValidString(ls_idCorreo))
											{
												PersonaCorreoElectronico lpce_correo;

												lpce_correo = new PersonaCorreoElectronico();

												lpce_correo.setIdPersona(ls_idPersona);
												lpce_correo.setIdCorreoElectronico(ls_idCorreo);

												lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
														                    .findById(lpce_correo);

												if(lpce_correo != null)
												{
													String ls_correo;

													ls_correo = lpce_correo.getCorreoElectronico();

													if(StringUtils.isValidString(ls_correo))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);
												}
											}
										}

										if(StringUtils.isValidString(ls_idDireccion))
										{
											PersonaDireccion lpd_personaDireccion;

											lpd_personaDireccion = new PersonaDireccion();

											lpd_personaDireccion.setIdPersona(ls_idPersona);
											lpd_personaDireccion.setIdDireccion(ls_idDireccion);

											lpd_personaDireccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
													                             .findById(lpd_personaDireccion);

											if(lpd_personaDireccion != null)
											{
												ls_tag = "<TAG_DIR_INTERESADO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_direccion;

													ls_direccion = lpd_personaDireccion.getDireccion();

													if(StringUtils.isValidString(ls_direccion))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccion);
												}

												ls_tag = "<TAG_DEPAR_INTERESADO>";

												if(
												    ls_plantilla.contains(ls_tag)
													    && ls_plantilla.contains("<TAG_MUNICIPIO_INTERESADO>")
												)
												{
													Departamento ld_departamento;
													Municipio    lm_municipio;

													ld_departamento     = new Departamento();
													lm_municipio        = new Municipio();

													ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
													ld_departamento.setIdDepartamento(
													    lpd_personaDireccion.getIdDepartamento()
													);
													lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
													lm_municipio.setIdDepartamento(
													    lpd_personaDireccion.getIdDepartamento()
													);
													lm_municipio.setIdMunicipio(lpd_personaDireccion.getIdMunicipio());

													lm_municipio     = DaoCreator.getMunicipioDAO(adm_manager)
															                         .findById(lm_municipio);

													ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
															                        .findById(ld_departamento);

													if((ld_departamento != null) && (lm_municipio != null))
													{
														ld_departamento.setNombreDepartamento(
														    ld_departamento.getNombre()
														);

														if(
														    StringUtils.isValidString(
															        ld_departamento.getNombreDepartamento()
															    )
															    && StringUtils.isValidString(lm_municipio.getNombre())
														)
															ls_plantilla = escribirDepartamentoMunicipioInteresado(
																    ls_plantilla, "<TAG_MUNICIPIO_INTERESADO>",
																    lm_municipio.getNombre(), ls_tag,
																    ld_departamento.getNombreDepartamento()
																);
													}
												}
												else if(ls_plantilla.contains(ls_tag))
												{
													Departamento ld_departamento;

													ld_departamento = new Departamento();

													ld_departamento.setIdPais(lpd_personaDireccion.getIdPais());
													ld_departamento.setIdDepartamento(
													    lpd_personaDireccion.getIdDepartamento()
													);

													ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
															                        .findById(ld_departamento);

													if(ld_departamento != null)
													{
														String ls_nombre;

														ls_nombre = ld_departamento.getNombre();

														if(StringUtils.isValidString(ls_nombre))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
													}
												}
												else
												{
													ls_tag = "<TAG_MUNICIPIO_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														Municipio lm_municipio;

														lm_municipio = new Municipio();

														lm_municipio.setIdPais(lpd_personaDireccion.getIdPais());
														lm_municipio.setIdDepartamento(
														    lpd_personaDireccion.getIdDepartamento()
														);
														lm_municipio.setIdMunicipio(
														    lpd_personaDireccion.getIdMunicipio()
														);

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(lm_municipio);

														if(lm_municipio != null)
														{
															String ls_nombre;

															ls_nombre = lm_municipio.getNombre();

															if(StringUtils.isValidString(ls_nombre))
																ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
														}
													}
												}
											}
										}
									}
								}
							}

							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idTelefonoInteresado;
									String ls_idPersona;

									ls_idTelefonoInteresado     = ls_solicitud.getIdTelefono();
									ls_idPersona                = ls_solicitud.getIdPersona();

									if(
									    StringUtils.isValidString(ls_idTelefonoInteresado)
										    && StringUtils.isValidString(ls_idPersona)
									)
									{
										String          ls_telefonoInteresado;
										PersonaTelefono lpt_personaTelefono;

										lpt_personaTelefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
												                            .findById(
												    ls_idPersona, ls_idTelefonoInteresado
												);

										if(lpt_personaTelefono != null)
										{
											ls_telefonoInteresado = lpt_personaTelefono.getTelefono();

											if(StringUtils.isValidString(ls_telefonoInteresado))
											{
												ls_tag = "<TAG_TEL_INTERESADO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_telefonoInteresado);
											}
										}
									}
								}
							}

							{
								if(StringUtils.isValidString(ls_idSolicitud))
								{
									TrasladoMatricula ltm_tm;
									ls_tag     = "<TAG_MATRICULA>";

									ltm_tm     = new TrasladoMatricula();

									ltm_tm = DaoCreator.getTrasladoMatriculaDAO(adm_manager)
											               .findOneByIdSolicitud(ls_idSolicitud);

									if((ltm_tm != null) && ls_plantilla.contains(ls_tag))
									{
										Long   ll_matriculaOrigen;
										String ls_idCirculoOrigen;

										ll_matriculaOrigen     = ltm_tm.getIdMatriculaOrigen();
										ls_idCirculoOrigen     = ltm_tm.getIdCirculoOrigen();

										if(NumericUtils.isValidLong(ll_matriculaOrigen))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getString(
												        ls_idCirculoOrigen + " - " + ll_matriculaOrigen
												    )
												);

										ls_tag = "<TAG_CIRCULO_ORIGEN>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_circuloOrigen;

											ls_circuloOrigen = ltm_tm.getIdCirculoOrigen();

											if(StringUtils.isValidString(ls_circuloOrigen))
											{
												CirculoRegistral lcr_cr;
												String           ls_nombreCirculo;

												lcr_cr = DaoCreator.getCirculoRegistralDAO(adm_manager)
														               .findById(ls_circuloOrigen);

												if(lcr_cr != null)
												{
													ls_nombreCirculo     = lcr_cr.getNombreCirculoRegistral();
													ls_plantilla         = ls_plantilla.replace(
														    ls_tag, ls_nombreCirculo
														);
												}
											}
										}

										ls_tag = "<TAG_CIRCULO_DESTINO>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_circuloDestino;

											ls_circuloDestino = ltm_tm.getIdCirculoDestino();

											if(StringUtils.isValidString(ls_circuloDestino))
											{
												CirculoRegistral lcr_cr;
												String           ls_nombreCirculo;

												lcr_cr = DaoCreator.getCirculoRegistralDAO(adm_manager)
														               .findById(ls_circuloDestino);

												if(lcr_cr != null)
												{
													ls_nombreCirculo     = lcr_cr.getNombreCirculoRegistral();
													ls_plantilla         = ls_plantilla.replace(
														    ls_tag, ls_nombreCirculo
														);
												}
											}
										}
									}
								}
							}

							ls_tag = "<TAG_ANTECEDENTES_PANTALLA>";

							if(ls_plantilla.contains(ls_tag))
							{
								OficiosTexto lot_oficiosTexto;

								lot_oficiosTexto = DaoCreator.getOficiosTextoDAO(adm_manager)
										                         .findByTurnoHistoriaOrderByFechaCreacion(
										    ll_idTurnoHistoria
										);

								if(lot_oficiosTexto != null)
								{
									String ls_antecedentesPantalla;

									ls_antecedentesPantalla = lot_oficiosTexto.getAntecedentes();

									if(StringUtils.isValidString(ls_antecedentesPantalla))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_antecedentesPantalla);
								}
							}

							String     ls_tagUsuarioFirma;
							Constantes lc_usuarioFirma;

							ls_tagUsuarioFirma     = null;
							lc_usuarioFirma        = null;

							{
								ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>";

								if(ls_plantilla.contains(ls_tagUsuarioFirma))
								{
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
											                            .findByIdWithImage(lc_usuarioFirma);

									ls_plantilla = getFirmaMasivaBusiness()
											               .reemplazarUsuarioFirmaCargo(
											    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
											    "<TAG_CARGO_FIRMA_ACTUACIONES_ADMINISTRATIVAS"
											);
								}
							}

							String ls_consecutivoOficio;
							String ls_referenciaSalida;
							Date   ld_fechaOficio;

							ls_consecutivoOficio     = null;
							ls_referenciaSalida      = null;
							ld_fechaOficio           = null;

							if(ab_firma)
							{
								{
									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
									{
										NumeracionOficiosCirculo lnoc_numeracionOficios;
										lnoc_numeracionOficios = findNumeracionOficiosCirculo(
											    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
											);

										if(lnoc_numeracionOficios != null)
										{
											ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

											ls_plantilla     = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);

											ld_fechaOficio = new Date();
										}
									}
								}

								{
									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
									{
										ls_referenciaSalida     = generarIdCorrespondencia(
											    lth_turnoHistoria, adm_manager, false
											);

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_return       = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_return == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

							if(ab_firma)
							{
								byte[] lba_grafo;

								lba_grafo = null;

								int li_incrX = 0;
								int li_incrY = 0;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_return = getFirmaMasivaBusiness()
										             .reemplazarBookmarsFirma(
										    lba_return, lba_grafo, li_incrX, li_incrY
										);
							}

							if(ab_salvar)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_return);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setRepositorio(
									    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
									);
									lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.FIJACION_AVISO);
									lds_documentoSalida.setTipo(TipoArchivoCommon.FIJACION_AVISO);
									lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
									lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
									lds_documentoSalida.setFechaOficio(ld_fechaOficio);
									lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setIdImagen(ll_idImagen);
									lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
									lds_documentoSalida.setIdUsuarioCreacion(as_userId);
									lds_documentoSalida.setIpCreacion(as_remoteIp);

									lds_DAO.insertOrUpdate(lds_documentoSalida, true);
								}
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar el documento de auto apertura.
	 *
	 * @param aot_oficioTextoData Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized byte[] generarDocumentoAutoResolucion(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aot_oficioTextoData != null) && (al_motivo > 0))
				lba_return = generarDocumentoAutoResolucion(
					    aot_oficioTextoData, as_userId, as_remoteIp, al_motivo, true, true, false, false, true,
					    ldm_manager
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoAutoResolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método de sobrecarga de generarDocumentoAutoResolucion
	 */
	public synchronized byte[] generarDocumentoAutoResolucion(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo, boolean ab_salvar,
	    boolean ab_firma, boolean ab_recurso, boolean ab_traslado, boolean ab_individual, DAOManager adm_manager
	)
	    throws B2BException
	{
		return generarDocumentoAutoResolucion(
		    aot_oficioTextoData, as_userId, as_remoteIp, al_motivo, ab_salvar, ab_firma, ab_recurso, ab_traslado,
		    ab_individual, false, adm_manager
		);
	}

	/**
	 * Método encargado de generar el documento de auto o resolución.
	 *
	 * @param aot_oficioTextoData Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarDocumentoAutoResolucion(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo, boolean ab_salvar,
	    boolean ab_firma, boolean ab_recurso, boolean ab_traslado, boolean ab_individual, boolean ab_651,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(aot_oficioTextoData != null)
		{
			try
			{
				TurnoHistoria lth_turnoHistoria;
				boolean       lb_proyectar;
				boolean       lb_planeacion;
				boolean       lb_segundaInstancia;

				lb_planeacion           = aot_oficioTextoData.isPlaneacion();
				lb_proyectar            = aot_oficioTextoData.isProyectar();
				lb_segundaInstancia     = aot_oficioTextoData.isSegundaInstancia();
				lth_turnoHistoria       = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                                .findById(aot_oficioTextoData.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;

					ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = lth_turnoHistoria.getIdTurno();

					String ls_idPlantilla;

					if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						Turno     lt_turno;
						Solicitud ls_solicitudM;

						lt_turno           = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);
						ls_solicitudM      = DaoCreator.getSolicitudDAO(adm_manager)
								                           .findById(lth_turnoHistoria.getIdSolicitud());

						if((lt_turno != null) && (al_motivo > 0) && (ls_solicitudM != null))
						{
							byte[]                 lba_plantilla;
							TagPlantillaResolucion laa_actuacionesAdministrativas;
							String                 ls_tipoArchivo;
							String                 ls_tipoDocumental;

							if((ls_solicitudM != null) && ab_651)
							{
								if(!ls_solicitudM.getIdSubproceso().equalsIgnoreCase("1"))
									ab_individual = false;
								else
									ab_individual = true;
							}

							if(lb_segundaInstancia)
								laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoSegundaInstancia(
									    adm_manager, al_motivo, true, true
									);
							else if(lb_proyectar || lb_planeacion)
								laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoTraslados(
									    adm_manager, al_motivo, false, true, false, lb_planeacion
									);
							else
								laa_actuacionesAdministrativas = ab_recurso
									? plantillaDatosPorIdMotivoRecursos(
									    adm_manager, ls_solicitudM.getIdProceso(), al_motivo, true
									)
									: (ab_traslado
									? plantillaDatosPorIdMotivoTraslados(
									    adm_manager, al_motivo, ab_individual, true, ab_651, false
									) : plantillaDatosPorIdMotivo(adm_manager, al_motivo, true));

							if(laa_actuacionesAdministrativas == null)
							{
								Object[] loa_messageArgs = new String[1];

								loa_messageArgs[0] = StringUtils.getString(al_motivo);

								throw new B2BException(
								    ErrorKeys.ERROR_NO_SE_ENCONTRARON_DATOS_ID_MOTIVO, loa_messageArgs
								);
							}

							ls_idPlantilla        = laa_actuacionesAdministrativas.getIdPlantilla();
							ls_tipoArchivo        = laa_actuacionesAdministrativas.getTipoArchivo();
							ls_tipoDocumental     = laa_actuacionesAdministrativas.getTipoDocumental();
							lba_plantilla         = laa_actuacionesAdministrativas.getArchivo();

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = ls_idPlantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								Constantes          lc_usuarioFirma;
								ConstantesDAO       lc_DAO;
								Date                ld_fechaResol;
								Date                ld_fechaOficio;
								DocumentosSalidaDAO lds_DAO;
								ImagenesDAO         li_DAO;
								Long                ll_NumeroResolucion;
								Map<String, String> lmss_datos;
								String              ls_idSolicitud;
								String              ls_idCirculo;
								String              ls_resolucion;
								String              ls_codigoVerificacion;
								String              ls_idCorrespondencia;
								String              ls_consecutivoOficio;
								CirculoRegistral    lcr_circuloRegistral;

								lc_usuarioFirma           = new Constantes();
								lc_DAO                    = DaoCreator.getConstantesDAO(adm_manager);
								ld_fechaResol             = null;
								ld_fechaOficio            = null;
								lds_DAO                   = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								li_DAO                    = DaoCreator.getImagenesDAO(adm_manager);
								ll_NumeroResolucion       = null;
								lmss_datos                = null;
								ls_idSolicitud            = lt_turno.getIdSolicitud();
								ls_resolucion             = null;
								ls_codigoVerificacion     = null;
								ls_idCorrespondencia      = null;
								ls_consecutivoOficio      = null;
								ls_idCirculo              = lt_turno.getIdCirculo();

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

								lc_usuarioFirma     = lc_DAO.findByIdWithImage(lc_usuarioFirma);

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(!ab_firma)
								{
									String ls_tag;
									String ls_plantilla;

									ls_plantilla = new String(lba_plantilla);

									{
										String ls_encabezado;
										String ls_antecedentes;
										String ls_consideraciones;
										String ls_dispone;
										String ls_notificaciones;
										String ls_pruebasRecaudadas;
										String ls_analisisProbatorio;
										String ls_intervencionInteresados;
										String ls_consideracion;
										String ls_justificacion;
										String ls_resuelve;
										String ls_argumentosRecurrente;
										Long   ll_diasSuspension;

										ls_encabezado                  = null;
										ls_antecedentes                = null;
										ls_consideraciones             = null;
										ls_dispone                     = null;
										ls_notificaciones              = null;
										ls_pruebasRecaudadas           = null;
										ls_analisisProbatorio          = null;
										ls_intervencionInteresados     = null;
										ls_consideracion               = null;
										ls_justificacion               = null;
										ls_resuelve                    = null;
										ls_argumentosRecurrente        = null;
										ll_diasSuspension              = null;

										if(ab_salvar && ab_firma)
										{
											OficiosTexto lot_data;

											lot_data = DaoCreator.getOficiosTextoDAO(adm_manager)
													                 .findByTurnoHistoria(ll_idTurnoHistoria);

											if(lot_data != null)
											{
												ls_encabezado                  = lot_data.getEncabezado();
												ls_antecedentes                = lot_data.getAntecedentes();
												ls_consideraciones             = ab_traslado
													? lot_data.getConsideracion() : lot_data.getPertinencia();
												ls_dispone                     = lot_data.getRazon1();
												ls_notificaciones              = lot_data.getRazon2();
												ls_pruebasRecaudadas           = lot_data.getPruebasRecaudadas();
												ls_analisisProbatorio          = lot_data.getAnalisisProbatorio();
												ls_intervencionInteresados     = lot_data.getIntervencionInteresados();
												ls_consideracion               = lot_data.getConsideracion();
												ls_justificacion               = lot_data.getConsideracion();
												ls_resuelve                    = lot_data.getResuelve();
												ls_argumentosRecurrente        = lot_data.getArgumentosRecurrente();
												ll_diasSuspension              = lot_data.getDiasSuspension();
											}
										}
										else
										{
											ls_encabezado                  = aot_oficioTextoData.getEncabezado();
											ls_antecedentes                = aot_oficioTextoData.getAntecedentes();
											ls_consideraciones             = (ab_traslado || lb_segundaInstancia)
												? aot_oficioTextoData.getConsideracion()
												: aot_oficioTextoData.getPertinencia();
											ls_dispone                     = aot_oficioTextoData.getRazon1();
											ls_notificaciones              = aot_oficioTextoData.getRazon2();
											ls_pruebasRecaudadas           = aot_oficioTextoData.getPruebasRecaudadas();
											ls_analisisProbatorio          = aot_oficioTextoData.getAnalisisProbatorio();
											ls_intervencionInteresados     = aot_oficioTextoData
													.getIntervencionInteresados();
											ls_consideracion               = aot_oficioTextoData.getConsideracion();
											ls_justificacion               = aot_oficioTextoData.getConsideracion();
											ls_resuelve                    = aot_oficioTextoData.getResuelve();
											ls_argumentosRecurrente        = aot_oficioTextoData.getArgumentosRecurrente();
											ll_diasSuspension              = aot_oficioTextoData.getDiasSuspension();
										}

										ls_tag = "<TAG_ENCABEZADO>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_encabezado))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_encabezado);

										ls_tag = "<TAG_ANTECEDENTES_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_antecedentes))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_antecedentes);

										ls_tag = "<TAG_HECHOS_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_antecedentes))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_antecedentes);

										ls_tag = "<TAG_CONSIDERACIONESPANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_REFERENCIA>";

										String ls_referencia = aot_oficioTextoData.getReferencia();

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_referencia))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_referencia);

										ls_tag = "<TAG_CONSIDERACIONES_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_PERTINENCIA_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_REFERENCIA_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_NOTIFICACIONES_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_notificaciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_notificaciones);

										ls_tag = "<TAG_DISPONE_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_dispone))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_dispone);

										ls_tag = "<TAG_PRUEBAS_RECAUDADAS_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_pruebasRecaudadas)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_pruebasRecaudadas);

										ls_tag = "<TAG_ANALISIS_PROBATORIO_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_analisisProbatorio)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_analisisProbatorio);

										ls_tag = "<TAG_INTERVENCION_INTERESADOS_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_intervencionInteresados)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_intervencionInteresados);

										ls_tag = "<TAG_CONSIDERACIONES_DESPACHO_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideracion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideracion);

										ls_tag = "<TAG_JUSTIFICACION_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_justificacion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_justificacion);

										ls_tag = "<TAG_RESUELVE_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_resuelve))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_resuelve);

										ls_tag = "<TAG_DIAS_SUSPENSION>";

										if(ls_plantilla.contains(ls_tag) && NumericUtils.isValidLong(ll_diasSuspension))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(ll_diasSuspension)
												);

										ls_tag = "<TAG_ARGUMENTOS_DEL_RECURRENTE_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_argumentosRecurrente)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_argumentosRecurrente);
									}

									ls_tag = "<TAG_TURNO_CORRECCIONES>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

									ls_tag = "<TAG_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

									ls_tag = "<TAG_CAUSAL_DEVOLUCION_NOTA_DEVOLUTIVA>";

									if(ls_plantilla.contains(ls_tag))
									{
										Collection<TipoCausal> lctc_tipoCausal;

										lctc_tipoCausal = DaoCreator.getNotaDevolutivaDAO(adm_manager)
												                        .findCausalesByTurno(ls_idTurno);

										if(CollectionUtils.isValidCollection(lctc_tipoCausal))
										{
											StringBuilder lsb_sb;

											lsb_sb = new StringBuilder();

											for(TipoCausal ltc_iterador : lctc_tipoCausal)
											{
												if(ltc_iterador != null)
												{
													lsb_sb.append(ltc_iterador.getNombre());
													lsb_sb.append(IdentificadoresCommon.SALTO_LINEA_RTF);
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									ls_tag = "<TAG_TIPO_RECURSO>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_idSolicitud1;

										ls_idSolicitud1 = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
												                        .findMaxByIdSolicitud(ls_idSolicitud);

										if(StringUtils.isValidString(ls_idSolicitud1))
										{
											Solicitud ls_solicitud;

											ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
													                     .findById(ls_idSolicitud1);

											if(ls_solicitud != null)
											{
												Subproceso lsp_subproceso;

												lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
														                       .findById(
														    ls_solicitud.getIdProceso(), ls_solicitud.getIdSubproceso()
														);

												if(lsp_subproceso != null)
												{
													String ls_nombreSubproceso;

													ls_nombreSubproceso = lsp_subproceso.getNombre();

													if(StringUtils.isValidString(ls_nombreSubproceso))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_nombreSubproceso
															);
												}
											}
										}
									}

									ls_tag     = "<TAG_ID_SOLICITUD_MATRICULA>";

									ls_plantilla     = resolverTagMatricula(
										    adm_manager, ls_plantilla, ls_tag, ls_idSolicitud, ls_idCirculo, ab_traslado
										);

									ls_tag = " <TAG_IDENTIFICACION_INTERVINIENTE_RECURSO>";

									if(ls_plantilla.contains(ls_tag))
									{
										Collection<SolicitudAsociada> lcs_solicitudAsociada;

										lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
												                              .findAllByIdSolicitud(
												    ls_idSolicitud, false
												);

										if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
										{
											StringBuilder lsb_sb;
											SolicitudDAO  lsd_DAO;
											PersonaDAO    lpd_DAO;
											int           li_contador;

											lsb_sb          = new StringBuilder("Que el(la) señor(a) ");
											lsd_DAO         = DaoCreator.getSolicitudDAO(adm_manager);
											lpd_DAO         = DaoCreator.getPersonaDAO(adm_manager);
											li_contador     = 1;

											for(SolicitudAsociada lsa_iterador : lcs_solicitudAsociada)
											{
												if(lsa_iterador != null)
												{
													String ls_idSolicitudAsociada;

													ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

													if(StringUtils.isValidString(ls_idSolicitudAsociada))
													{
														Solicitud ls_solicitud;

														ls_solicitud = lsd_DAO.findById(ls_idSolicitudAsociada);

														if(ls_solicitud != null)
														{
															String ls_idProceso;

															ls_idProceso = ls_solicitud.getIdProceso();

															if(
															    StringUtils.isValidString(ls_idProceso)
																    && ls_idProceso.equalsIgnoreCase(
																        ProcesoCommon.ID_PROCESO_47
																    )
															)
															{
																Persona lp_persona;

																lp_persona = lpd_DAO.findById(
																	    ls_solicitud.getIdPersona()
																	);

																if(lp_persona != null)
																{
																	if(li_contador > 1)
																		lsb_sb.append(", el(la) señor(a) ");

																	lsb_sb.append(lp_persona.getNombreCompleto());
																	lsb_sb.append(" identificado(a) con ");
																	lsb_sb.append(lp_persona.getIdDocumentoTipo());
																	lsb_sb.append(" número ");
																	lsb_sb.append(lp_persona.getNumeroDocumento());
																	lsb_sb.append(", en calidad de ");

																	{
																		String ls_idCalidadSolicitante;

																		ls_idCalidadSolicitante = ls_solicitud
																				.getIdCalidadSolicitante();

																		if(
																		    StringUtils.isValidString(
																			        ls_idCalidadSolicitante
																			    )
																		)
																		{
																			CalidadSolicitante lcs_calidadSolicitante;

																			lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(
																				    adm_manager
																				).findById(ls_idCalidadSolicitante);

																			if(lcs_calidadSolicitante != null)
																				lsb_sb.append(
																				    lcs_calidadSolicitante.getNombre()
																				);
																		}
																	}
																}
															}
														}
													}

													li_contador++;
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									ls_tag = TagCommon.TAG_FECHA_NOTA_DEVOLUTIVA;

									if(ls_plantilla.contains(ls_tag))
									{
										Collection<DocumentosSalida> lcds_documentoSalida;

										lcds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
												                             .findByIdTurnoHistoriaTipo(
												    NumericUtils.getInteger(ll_idTurnoHistoria),
												    TipoArchivoCommon.NOTA_DEVOLUTIVA
												);

										if(CollectionUtils.isValidCollection(lcds_documentoSalida))
										{
											DocumentosSalida lds_documentoSalida;

											lds_documentoSalida = lcds_documentoSalida.iterator().next();

											if(lds_documentoSalida != null)
												ls_plantilla = reemplazarTagEnPlantilla(
													    ls_plantilla, ls_tag, lds_documentoSalida.getFechaCreacion()
													);
										}
									}

									{
										Solicitud ls_solicitud;

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
												                     .findById(lth_turnoHistoria.getIdSolicitud());

										if(ls_solicitud != null)
											ls_plantilla = reemplazarTagsDocumento(
												    adm_manager, ls_solicitud, lt_turno, ls_plantilla
												);
									}

									if(StringUtils.isValidString(ls_idCirculo))
									{
										if(lcr_circuloRegistral != null)
										{
											String ls_nombre;

											ls_nombre = lcr_circuloRegistral.getNombre();

											if(StringUtils.isValidString(ls_nombre))
											{
												ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

												ls_tag = "<TAG_MUN_OFI_ORIGEN>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

												ls_tag = "<TAG_DEPARTAMENTO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idDepartamento;
													String ls_idPais;

													ls_idDepartamento     = lcr_circuloRegistral.getIdDepartamento();
													ls_idPais             = lcr_circuloRegistral.getIdPais();

													if(
													    StringUtils.isValidString(ls_idDepartamento)
														    && StringUtils.isValidString(ls_idPais)
													)
													{
														Departamento ld_departamento;

														ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
																                        .findById(
																    ls_idPais, ls_idDepartamento
																);

														if(ld_departamento != null)
														{
															String ls_nombreDepartamento;

															ls_nombreDepartamento = ld_departamento.getNombre();

															if(StringUtils.isValidString(ls_nombreDepartamento))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreDepartamento
																	);
														}
													}
												}
											}

											ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_tipoOficina;

												ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

												if(StringUtils.isValidString(ls_tipoOficina))
												{
													if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
														ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
													else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
														ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

													ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
												}
											}
										}
									}

									{
										Solicitud ls_solicitud;

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

										if(ls_solicitud != null)
										{
											String ls_idPersona;

											ls_tag           = "<TAG_NIR>";
											ls_idPersona     = ls_solicitud.getIdPersona();

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_nir;

												ls_nir = ls_solicitud.getNir();

												if(StringUtils.isValidString(ls_nir))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
											}

											if(StringUtils.isValidString(ls_idPersona))
											{
												Persona lp_persona;

												lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
													    ls_idPersona
													);

												if(lp_persona != null)
												{
													String ls_tag1;
													String ls_tag2;

													ls_tag      = "<TAG_NOMBRE_INTERESADO>";
													ls_tag1     = "<TAG_DEPAR_INTERESADO>";
													ls_tag2     = "<TAG_MUNICIPIO_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_nombreCompleto;

														ls_nombreCompleto = lp_persona.getNombreCompleto();

														if(StringUtils.isValidString(ls_nombreCompleto))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, ls_nombreCompleto
																);
													}

													ls_tag = "<TAG_CORREO_ELECTRONICO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_idCorreo;

														ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

														if(!StringUtils.isValidString(ls_idCorreo))
															ls_idCorreo = ls_solicitud
																	.getIdCorreoElectronicoComunicacion();

														if(StringUtils.isValidString(ls_idCorreo))
														{
															PersonaCorreoElectronico lpce_correo;

															lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idCorreo);

															if(lpce_correo != null)
															{
																String ls_correo;

																ls_correo = lpce_correo.getCorreoElectronico();

																if(StringUtils.isValidString(ls_correo))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_correo
																		);
															}
														}
													}

													ls_tag = "<TAG_DIR_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag1)
														    || ls_plantilla.contains(ls_tag2)
													)
													{
														String ls_idDireccion;

														ls_idDireccion = ls_solicitud.getIdDireccion();

														if(!StringUtils.isValidString(ls_idDireccion))
															ls_idDireccion = ls_solicitud.getIdDireccionComunicacion();

														if(StringUtils.isValidString(ls_idDireccion))
														{
															PersonaDireccion lpd_direccion;

															lpd_direccion = DaoCreator.getPersonaDireccionDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idDireccion);

															if(lpd_direccion != null)
															{
																String ls_direccion;
																String ls_idDepartamento;
																String ls_idPais;

																ls_direccion          = lpd_direccion.getDireccion();
																ls_idDepartamento     = lpd_direccion.getIdDepartamento();
																ls_idPais             = lpd_direccion.getIdPais();

																if(
																    StringUtils.isValidString(ls_direccion)
																	    && ls_plantilla.contains(ls_tag)
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_direccion
																		);

																if(StringUtils.isValidString(ls_idDepartamento))
																{
																	Departamento ld_departamento;

																	ld_departamento = DaoCreator.getDepartamentoDAO(
																		    adm_manager
																		).findById(ls_idPais, ls_idDepartamento);

																	if(ld_departamento != null)
																	{
																		String ls_idMunicipio;
																		String ls_nombreDepartamento;

																		ls_idMunicipio            = lpd_direccion
																				.getIdMunicipio();
																		ls_nombreDepartamento     = ld_departamento
																				.getNombre();

																		if(
																		    StringUtils.isValidString(
																			        ls_nombreDepartamento
																			    ) && ls_plantilla.contains(ls_tag1)
																		)
																			ls_plantilla = ls_plantilla.replace(
																				    ls_tag1, ls_nombreDepartamento
																				);

																		if(ls_idMunicipio != null)
																		{
																			Municipio lm_municipio;

																			lm_municipio = DaoCreator.getMunicipioDAO(
																				    adm_manager
																				)
																					                     .findById(
																					    ls_idPais, ls_idDepartamento,
																					    ls_idMunicipio
																					);

																			if(lm_municipio != null)
																			{
																				String ls_nombreMunicipio;

																				ls_nombreMunicipio = lm_municipio
																						.getNombre();

																				if(
																				    StringUtils.isValidString(
																					        ls_nombreMunicipio
																					    )
																					    && ls_plantilla.contains(
																					        ls_tag2
																					    )
																				)
																					ls_plantilla = ls_plantilla.replace(
																						    ls_tag2, ls_nombreMunicipio
																						);
																			}
																		}
																	}
																}
															}
														}
													}

													ls_tag = "<TAG_TEL_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_idTel;

														ls_idTel = ls_solicitud.getIdTelefono();

														if(!StringUtils.isValidString(ls_idTel))
															ls_idTel = ls_solicitud.getIdTelefonoComunicacion();

														if(StringUtils.isValidString(ls_idTel))
														{
															PersonaTelefono lpt_telefono;

															lpt_telefono = DaoCreator.getPersonaTelefonoDAO(
																    adm_manager
																).findById(ls_idPersona, ls_idTel);

															if(lpt_telefono != null)
															{
																String ls_telefono;

																ls_telefono = lpt_telefono.getTelefono();

																if(
																    StringUtils.isValidString(ls_telefono)
																	    && ls_plantilla.contains(ls_tag)
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_telefono
																		);
															}
														}
													}
												}
											}
										}
									}

									ls_tag = "<TAG_USUARIO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tag,
												    "<TAG_CARGO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>"
												);

									ls_tag = "<TAG_NUMERO_RESOLUCION>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "RESOLUCION",
											    lc_DAO.findById(ConstanteCommon.TAG_RESOL)
											);

									ls_tag = "<TAG_RESOLUCION>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "RESOLUCION",
											    lc_DAO.findById(ConstanteCommon.TAG_RESOL)
											);

									ls_tag = "<TAG_RESOL>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "RESOLUCION",
											    lc_DAO.findById(ConstanteCommon.TAG_RESOL)
											);

									ls_tag = "<TAG_FECHA_RESOL>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "FECHA_RESOL",
											    lc_DAO.findById(ConstanteCommon.TAG_FECHA_RESOL)
											);

									ls_tag = "<TAG_EXPEDIENTE>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "EXPEDIENTE",
											    lc_DAO.findById(ConstanteCommon.TAG_EXPEDIENTE)
											);

									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "OFICIO",
											    lc_DAO.findById(ConstanteCommon.TAG_OFICIO)
											);

									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "REFERENCIA_SALIDA",
											    lc_DAO.findById(ConstanteCommon.TAG_REFERENCIA_SALIDA)
											);

									{
										Calendar lc_fecha;

										lc_fecha = Calendar.getInstance();

										{
											int li_dia;

											li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

											if(ls_plantilla.contains("<TAG_DIAS_LETRAS>"))
											{
												String ls_dia;

												ls_dia = NumericUtils.convertirLetras(li_dia, false);

												if(StringUtils.isValidString(ls_dia))
													ls_plantilla = ls_plantilla.replace("<TAG_DIAS_LETRAS>", ls_dia);
											}

											if(ls_plantilla.contains("<TAG_DIAS>") && (li_dia > 0))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_DIAS>", StringUtils.getString(li_dia)
													);
										}

										if(ls_plantilla.contains("<TAG_MES_LETRAS>"))
										{
											int    li_mes;
											String ls_mes;

											li_mes     = lc_fecha.get(Calendar.MONTH);
											ls_mes     = DateUtils.getMes(li_mes + 1);

											if(StringUtils.isValidString(ls_mes))
												ls_plantilla = ls_plantilla.replace("<TAG_MES_LETRAS>", ls_mes);
										}

										{
											int li_anio;

											li_anio = lc_fecha.get(Calendar.YEAR);

											if(ls_plantilla.contains("<TAG_ANIO_LETRAS>"))
											{
												String ls_anio;

												ls_anio = NumericUtils.convertirLetras(li_anio, false);

												if(StringUtils.isValidString(ls_anio))
													ls_plantilla = ls_plantilla.replace("<TAG_ANIO_LETRAS>", ls_anio);
											}

											if(ls_plantilla.contains("<TAG_ANIO>") && (li_anio > 0))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_ANIO>", StringUtils.getString(li_anio)
													);
										}
									}

									ls_tag = "<TAG_USUARIO_410>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

									ls_tag = "<TAG_USUARIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

									ls_tag = "<TAG_FECHA>";

									if(ls_plantilla.contains(ls_tag))
									{
										Date       ld_fechaActual = new Date();
										DateFormat lsf_dateFormat = new SimpleDateFormat(
											    FormatoFechaCommon.DIA_MES_ANIO
											);
										String     ls_fecha       = lsf_dateFormat.format(ld_fechaActual);
										ls_plantilla              = ls_plantilla.replace(ls_tag, ls_fecha);

										ls_tag = "<TAG_FECHA_FIJACION>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);

										ls_tag = "<TAG_FECHA_DESFIJACION>";

										if(ls_plantilla.contains(ls_tag))
										{
											Calendar lc_c = Calendar.getInstance();
											lc_c.setTime(ld_fechaActual);

											if(lc_c.get(Calendar.DAY_OF_WEEK) == 2)
												lc_c.add(Calendar.DATE, 5);
											else
												lc_c.add(Calendar.DATE, 6);

											Date                 ld_fechaModificada      = lc_c.getTime();
											FechaVenceTerminosUI lfvtui_fechaVencimiento = new FechaVenceTerminosUI();
											lfvtui_fechaVencimiento.setFechaFinal(ld_fechaModificada);
											lfvtui_fechaVencimiento.setTipoCalendario(EstadoCommon.H);
											lfvtui_fechaVencimiento.setIdCirculo(lt_turno.getIdCirculo());

											long ll_dias = DaoCreator.getUtilDAO(adm_manager)
													                     .funcionCalcularDiasFechas(
													    lfvtui_fechaVencimiento
													);

											if(ll_dias == 5)
												ls_fecha = lsf_dateFormat.format(ld_fechaModificada);

											else if(ll_dias == 4)
											{
												lc_c.setTime(ld_fechaModificada);
												lc_c.add(Calendar.DATE, 1);

												ld_fechaModificada     = lc_c.getTime();
												ls_fecha               = lsf_dateFormat.format(ld_fechaModificada);
											}
											else
												ls_fecha = null;

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
										}
									}

									ls_tag = "<TAG_USUARIO_FIRMA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "USUARIO_FIRMA",
											    lc_DAO.findById(ConstanteCommon.USUARIO_FIRMA_SUSPENSION), false
											);

									ls_plantilla              = escribirTagFechaLarga(ls_plantilla);
									lmss_datos                = finalizarPlantilla(
										    ls_plantilla, ll_idTurnoHistoria, adm_manager
										);
									ls_plantilla              = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
									ls_codigoVerificacion     = lmss_datos.get(
										    IdentificadoresCommon.CODIGO_VERIFICACION
										);

									lba_return = new PDFGenerator().convertirRTFToPDF(
										    ls_plantilla.getBytes(), adm_manager
										);

									if(lba_return == null)
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
								}
								else
								{
									DocumentosSalida lds_documentoSalida;

									lds_documentoSalida = new DocumentosSalida();

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setTipo(ls_tipoArchivo);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

									lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
										    lds_documentoSalida, false
										);

									if(lds_documentoSalida != null)
									{
										Imagenes li_imagen;

										li_imagen = li_DAO.findById(
											    NumericUtils.getLong(lds_documentoSalida.getIdImagen())
											);

										if(li_imagen != null)
										{
											lba_return                = li_imagen.getImagenBlob();
											ls_codigoVerificacion     = li_imagen.getCodigoVerificacion();

											if(lba_return != null)
											{
												int li_fontSize;
												int li_temp;

												li_fontSize     = 12;
												li_temp         = 9;

												if(
												    ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_PROYECTAR_RESOLUCION_TRASLADO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_CONSTANCIA_PUBLICACION
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_652
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_MASIVA
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_SUSPENSION_TRASLADOS
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_ACEPTACION_TRASLADO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_INDIVIDUAL
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_AUTO_APERTURA_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_CONCEDIENDO_APELACION_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_PARCIALMENTE_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_RECURSO
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_AUTO_APERTURA
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_AUTO_ACLARACION
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_AUTO_APERTURA_A_PRUEBAS
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_DECISION
													    )
													    || ls_idPlantilla.equals(
													        ConstanteCommon.PLANTILLA_RESOLUCION_ACLARACION_DECISION
													    )
												)
												{
													NumeracionResolucionCirculo lnrc_numeracionCirculo;

													lnrc_numeracionCirculo = findNumeracionResolucionCirculo(
														    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
														);

													if(lnrc_numeracionCirculo != null)
													{
														ls_resolucion           = lnrc_numeracionCirculo
																.getConsecutivogenerado();
														ll_NumeroResolucion     = lnrc_numeracionCirculo
																.getConsecutivoResolucion();
													}

													if(StringUtils.isValidString(ls_resolucion))
													{
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_resolucion, ls_resolucion.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "RESOLUCION", false
																);
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_resolucion, ls_resolucion.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "NUM_RES", false
																);
													}

													Date             ld_date;
													SimpleDateFormat formatter;
													String           ls_date;

													ld_date           = new Date();
													ld_fechaResol     = ld_date;
													formatter         = new SimpleDateFormat("dd/MM/yyyy");
													ls_date           = StringUtils.getStringNotNull(
														    formatter.format(ld_date)
														);

													lba_return     = getFirmaMasivaBusiness()
															                 .reemplazarBookmarsFirma(
															    lba_return,
															    MarcaAgua.crearImagenConTexto(
															        ls_date, ls_date.length() * li_temp, li_fontSize,
															        li_fontSize
															    ), 0, 0, "FECHA_RESOL", false
															);
													lba_return     = getFirmaMasivaBusiness()
															                 .reemplazarBookmarsFirma(
															    lba_return,
															    MarcaAgua.crearImagenConTexto(
															        ls_date, ls_date.length() * li_temp, li_fontSize,
															        li_fontSize
															    ), 0, 0, "FECHA_RES", false
															);
												}
												else
												{
													NumeracionOficiosCirculo lnoc_numeracionOficios;

													lnoc_numeracionOficios = findNumeracionOficiosCirculo(
														    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
														);

													if(lnoc_numeracionOficios != null)
													{
														ls_consecutivoOficio = lnoc_numeracionOficios
																.getConsecutivoGenerado();

														if(StringUtils.isValidString(ls_consecutivoOficio))
														{
															ld_fechaOficio     = new Date();
															lba_return         = getFirmaMasivaBusiness()
																	                     .reemplazarBookmarsFirma(
																	    lba_return,
																	    MarcaAgua.crearImagenConTexto(
																	        ls_consecutivoOficio,
																	        ls_consecutivoOficio.length() * li_temp,
																	        li_fontSize, li_fontSize
																	    ), 0, 0, "OFICIO", false
																	);
															lba_return         = getFirmaMasivaBusiness()
																	                     .reemplazarBookmarsFirma(
																	    lba_return,
																	    MarcaAgua.crearImagenConTexto(
																	        ls_consecutivoOficio,
																	        ls_consecutivoOficio.length() * li_temp,
																	        li_fontSize, li_fontSize
																	    ), 0, 0, "NUM_OFI", false
																	);
														}
													}

													ls_idCorrespondencia = generarIdCorrespondencia(
														    lth_turnoHistoria, adm_manager, false
														);

													if(StringUtils.isValidString(ls_idCorrespondencia))
													{
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_idCorrespondencia,
																        ls_idCorrespondencia.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "REFERENCIA_SALIDA", false
																);
														lba_return     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_idCorrespondencia,
																        ls_idCorrespondencia.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "REF_SAL", false
																);
													}
												}

												String ls_expediente;

												ls_expediente = lt_turno.getExpediente();

												if(!StringUtils.isValidString(ls_expediente))
													ls_expediente = aot_oficioTextoData.getExpediente();

												if(StringUtils.isValidString(ls_expediente))
													lba_return = getFirmaMasivaBusiness()
															             .reemplazarBookmarsFirma(
															    lba_return,
															    MarcaAgua.crearImagenConTexto(
															        ls_expediente, ls_expediente.length() * li_temp,
															        li_fontSize, li_fontSize
															    ), 0, 0, "EXPEDIENTE", false
															);

												byte[] lba_grafo;
												int    li_incrX;
												int    li_incrY;

												lba_grafo     = null;
												li_incrX      = 0;
												li_incrY      = 0;

												li_fontSize         = 12;
												li_temp             = 9;
												lc_usuarioFirma     = new Constantes();

												lc_usuarioFirma.setIdConstante(
												    ConstanteCommon.USUARIO_FIRMA_SUSPENSION
												);

												lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
														                        .findByIdWithImage(lc_usuarioFirma);

												if(lc_usuarioFirma != null)
												{
													lba_grafo     = lc_usuarioFirma.getImagenes();
													li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
													li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
												}

												lba_return = getFirmaMasivaBusiness()
														             .reemplazarBookmarsFirma(
														    lba_return, lba_grafo, li_incrX, li_incrY, "USUARIO_FIRMA",
														    false
														);
											}
										}
									}
								}

								if(ab_salvar)
								{
									Imagenes li_imagenes;
									long     ll_idImagenTemp;

									li_imagenes = new Imagenes();

									li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagenes.setIdUsuarioCreacion(as_userId);
									li_imagenes.setIpCreacion(as_remoteIp);
									li_imagenes.setImagenBlob(lba_return);
									li_imagenes.setCodigoVerificacion(ls_codigoVerificacion);

									ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

									if(ll_idImagenTemp > 0)
									{
										DocumentosSalida lds_documentoSalida;
										Long             ll_idImagen;
										boolean          lb_b = false;

										lds_documentoSalida     = new DocumentosSalida();
										ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setTipo(ls_tipoArchivo);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

										lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
											    lds_documentoSalida, false
											);

										if(lds_documentoSalida != null)
										{
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setConsecutivoResolucion(ll_NumeroResolucion);
											lds_documentoSalida.setFechaResolucion(ld_fechaResol);
											lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
											lds_documentoSalida.setFechaOficio(ld_fechaOficio);
											lds_documentoSalida.setReferenciaSalida(ls_idCorrespondencia);
											lds_documentoSalida.setIdEcm(null);
											lds_documentoSalida.setFechaEnvio(null);
											lds_documentoSalida.setIdNombreDocumento(null);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setIdUsuarioModificacion(as_userId);
											lds_documentoSalida.setIpModificacion(as_remoteIp);

											if(lth_turnoHistoria != null)
											{
												BigDecimal lbd_etapa;
												lbd_etapa = lth_turnoHistoria.getIdEtapa();

												if(
												    (NumericUtils.isValidBigDecimal(lbd_etapa))
													    && ((lbd_etapa.longValue() == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
													    || (lbd_etapa.longValue() == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS))
													    && (NumericUtils.isValidLong(ll_NumeroResolucion))
												)
												{
													Collection<TrasladoMatricula> lc_trasladoMatricula;
													String                        ls_circuloTraslado;
													CirculoRegistral              lcr_circuloRegistralTraslado;

													lc_trasladoMatricula             = DaoCreator.getTrasladoMatriculaDAO(
														    adm_manager
														).findByIdSolicitud(ls_idSolicitud);
													ls_circuloTraslado               = null;
													lcr_circuloRegistralTraslado     = new CirculoRegistral();

													if(CollectionUtils.isValidCollection(lc_trasladoMatricula))
													{
														for(TrasladoMatricula li_traslado : lc_trasladoMatricula)
														{
															if(li_traslado != null)
															{
																if(
																    lbd_etapa.longValue() == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS
																)
																	ls_circuloTraslado = li_traslado.getIdCirculoOrigen();
																else
																	ls_circuloTraslado = li_traslado.getIdCirculoDestino();
															}
														}
													}

													lcr_circuloRegistralTraslado.setIdCirculo(ls_idCirculo);

													lcr_circuloRegistralTraslado = DaoCreator.getCirculoRegistralDAO(
														    adm_manager
														).findById(ls_circuloTraslado);

													if(lcr_circuloRegistralTraslado != null)
													{
														SoporteTraslado lst_soporteTraslado;
														lst_soporteTraslado = new SoporteTraslado();
														lst_soporteTraslado.setIdTurno(ls_idTurno);
														lst_soporteTraslado.setIdSolicitud(ls_idSolicitud);

														lst_soporteTraslado.setIdOficinaOrigen(
														    lcr_circuloRegistralTraslado.getIdOficinaOrigen()
														);
														lst_soporteTraslado.setIdTipoDocumento("16");
														lst_soporteTraslado.setIdTurnoHistoria(
														    lth_turnoHistoria.getIdTurnoHistoria()
														);
														lst_soporteTraslado.setFechaDocumento(ld_fechaResol);
														lst_soporteTraslado.setIdVersion(
														    NumericUtils.getLongWrapper(
														        lcr_circuloRegistralTraslado.getVersion()
														    )
														);
														lst_soporteTraslado.setNumero(
														    StringUtils.getString(ll_NumeroResolucion)
														);
														lst_soporteTraslado.setIdUsuarioCreacion(as_userId);
														lst_soporteTraslado.setIpCreacion(as_remoteIp);

														SoporteTrasladoDAO lstDAO = DaoCreator.getSoporteTrasladoDAO(
															    adm_manager
															);
														lstDAO.insert(lst_soporteTraslado);
													}
												}
											}
										}
										else
										{
											lds_documentoSalida = new DocumentosSalida();

											lds_documentoSalida.setIdTurno(ls_idTurno);
											lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setTipo(ls_tipoArchivo);
											lds_documentoSalida.setIdTipoDocumental(ls_tipoDocumental);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
											lds_documentoSalida.setConsecutivoResolucion(ll_NumeroResolucion);
											lds_documentoSalida.setFechaResolucion(ld_fechaResol);
											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
											lds_documentoSalida.setFechaOficio(ld_fechaOficio);
											lds_documentoSalida.setReferenciaSalida(ls_idCorrespondencia);
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);
											lb_b = true;
										}

										if(lb_proyectar)
										{
											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setDocumentoAutomatico(EstadoCommon.SI);
											lds_documentoSalida.setDocumentoFinal(EstadoCommon.NO);

											Solicitud ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
													                               .findById(ls_idSolicitud);

											if(ls_solicitud != null)
											{
												Persona lp_persona = DaoCreator.getPersonaDAO(adm_manager)
														                           .findById(
														    ls_solicitud.getIdPersona()
														);

												if(lp_persona != null)
												{
													lds_documentoSalida.setDestinatario(lp_persona.getNombreCompleto());

													String           ls_idPersona = lp_persona.getIdPersona();
													PersonaDireccion lpd_pd       = new PersonaDireccion();
													lpd_pd.setIdPersona(ls_idPersona);

													PersonaDireccionDAO lpdDAO = DaoCreator.getPersonaDireccionDAO(
														    adm_manager
														);
													int                 li_i   = lpdDAO.findIdDireccion(lpd_pd);

													if(li_i > 0)
													{
														lpd_pd = lpdDAO.findById(ls_idPersona, String.valueOf(li_i));

														if(lpd_pd != null)
														{
															lds_documentoSalida.setDireccion(lpd_pd.getDireccion());
															lds_documentoSalida.setIdDepartamento(
															    lpd_pd.getIdDepartamento()
															);
															lds_documentoSalida.setIdPais(lpd_pd.getIdPais());
															lds_documentoSalida.setIdMunicipio(lpd_pd.getIdMunicipio());
														}
													}

													{
														PersonaCorreoElectronico lpce_pce = new PersonaCorreoElectronico();
														lpce_pce.setIdPersona(ls_solicitud.getIdPersona());
														lpce_pce.setIdCorreoElectronico(
														    ls_solicitud.getIdCorreoElectronico()
														);
														lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(
															    adm_manager
															).findById(lpce_pce);

														if(lpce_pce != null)
															lds_documentoSalida.setCorreoElectronico(
															    lpce_pce.getCorreoElectronico()
															);
													}

													{
														PersonaTelefono lpt_pt = new PersonaTelefono();
														lpt_pt.setIdPersona(ls_idPersona);
														lpt_pt.setIdTelefono(ls_solicitud.getIdTelefono());

														lpt_pt = DaoCreator.getPersonaTelefonoDAO(adm_manager)
																               .findById(lpt_pt);

														if(lpt_pt != null)
															lds_documentoSalida.setTelefono(lpt_pt.getTelefono());
													}
												}
											}
										}

										lds_DAO.insertOrUpdate(lds_documentoSalida, lb_b);
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarDocumentoAutoResolucion", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar el documento comunicado.
	 *
	 * @param aot_oficioTextoData Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param apn_personaNotificacion Objeto que contiene la información para generar el pdf.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarDocumentoComunicado(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo,
	    PersonaNotificacion apn_personaNotificacion
	)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aot_oficioTextoData != null) && (apn_personaNotificacion != null) && (al_motivo > 0))
				lba_return = generarDocumentoComunicado(
					    aot_oficioTextoData, as_userId, as_remoteIp, al_motivo, apn_personaNotificacion, false, true,
					    true, ldm_manager
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoComunicado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método para la generación del documento fijacion desfijacion en etapa 190
	 * @param as_parametros con el turno historia
	 * @param as_userId con el usuario de la transacción
	 * @param as_remoteIp con la ip del usuario de la transacción
	 * @param ab_salvar si se desea guardar en BD
	 * @param ab_firma si viene del JOB de aprobación
	 * @param adm_manager con el manager del manejo de la transacción
	 * @return de tipo byte array con la información de la plantilla
	 * @throws B2BException
	 */
	public byte[] generarDocumentoFijacionDesfijacion(
	    TurnoHistoria as_parametros, String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(as_parametros != null)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(as_parametros);

			if(lth_turnoHistoria != null)
			{
				Long   ll_idTurnoHistoria;
				String ls_idTurno;

				ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
				ls_idTurno             = lth_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

					if(lt_turno != null)
					{
						Constantes lc_constante;
						String     ls_constante;

						byte[] lba_plantilla;

						ls_constante     = ConstanteCommon.PLANTILLA_FIJACION_DESFIJACION_TRASLADOS;
						lc_constante     = new Constantes();

						lc_constante.setIdConstante(ls_constante);

						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0] = ls_constante;

							throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
						}
						else
						{
							Map<String, String> lmss_datos;
							String              ls_idCirculo;
							String              ls_idSolicitud;
							String              ls_plantilla;
							String              ls_tag;
							Date                ld_fechaFijacion;
							Date                ld_fechaDesfijacion;

							lmss_datos              = null;
							ls_idCirculo            = lt_turno.getIdCirculo();
							ls_idSolicitud          = lt_turno.getIdSolicitud();
							ls_plantilla            = new String(lba_plantilla);
							ld_fechaFijacion        = null;
							ld_fechaDesfijacion     = null;

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = new CirculoRegistral();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(lcr_circuloRegistral);

								if(lcr_circuloRegistral != null)
								{
									ls_tag = "<TAG_NOMBRE_ORIP>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_tipoOficina;

										ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

										if(StringUtils.isValidString(ls_tipoOficina))
										{
											if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
												ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
											else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
												ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
										}
									}

									ls_tag = "<TAG_MUN_OFI_ORIGEN>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
									{
										Municipio lm_municipio;

										lm_municipio     = new Municipio();

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
												                     .findByIdCirculo(ls_idCirculo);

										if(lm_municipio != null)
										{
											String ls_munOficinaOrigen;
											ls_munOficinaOrigen = lm_municipio.getNombre();

											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_munOficinaOrigen)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
										}
									}

									ls_tag = "<TAG_PUBLICACION_AVISO_WEB>";

									if(ls_plantilla.contains(ls_tag))
									{
										String           ls_fechaResol;
										SimpleDateFormat formatter;

										ld_fechaFijacion     = new Date();
										formatter            = new SimpleDateFormat("dd/MM/yyyy");

										if(ld_fechaFijacion != null)
										{
											ls_fechaResol     = StringUtils.getStringNotNull(
												    formatter.format(ld_fechaFijacion)
												);

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaResol);
										}
									}

									ls_tag = "<TAG_DESFIJACION_AVISO_WEB>";

									if(ls_plantilla.contains(ls_tag))
									{
										FechaVenceTerminosUI lfvtui_fecha;

										lfvtui_fecha = new FechaVenceTerminosUI();

										lfvtui_fecha.setFechaInicial(ld_fechaFijacion);
										lfvtui_fecha.setTipoCalendario(EstadoCommon.H);
										lfvtui_fecha.setIdCirculo(ls_idCirculo);
										lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(5));

										ld_fechaDesfijacion = calcularFechaVencimiento(lfvtui_fecha);

										String           ls_fechaDesfijacion;
										SimpleDateFormat formatter;

										formatter = new SimpleDateFormat("dd/MM/yyyy");

										if(ld_fechaDesfijacion != null)
										{
											ls_fechaDesfijacion     = StringUtils.getStringNotNull(
												    formatter.format(ld_fechaDesfijacion)
												);

											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaDesfijacion);
										}
									}

									ls_plantilla = escribirTagFechaLarga(ls_plantilla);
								}
							}

							ls_plantilla = getFirmaMasivaBusiness()
									               .reemplazarUsuarioFirmaCargo(
									    ls_plantilla,
									    DaoCreator.getConstantesDAO(adm_manager)
									                  .findByIdWithImage(ConstanteCommon.USUARIO_FIRMA_SUSPENSION),
									    TagCommon.TAG_USUARIO_FIRMA_SUSPENSION, TagCommon.TAG_CARGO_FIRMA_SUSPENSION
									);

							String ls_consecutivoResolucion;
							Long   ll_consecutivoResolucion;
							Date   ld_dateResol;

							ls_consecutivoResolucion     = null;
							ll_consecutivoResolucion     = null;
							ld_dateResol                 = null;

							if(ab_firma)
							{
								ls_tag = "<TAG_NUMERO_RESOLUCION>";

								if(ls_plantilla.contains(ls_tag))
								{
									DocumentosSalida ldsr_documentosSalidaResolucion;

									ldsr_documentosSalidaResolucion = DaoCreator.getDocumentosSalidaDAO(adm_manager)
											                                        .findByIdTurnoTipo(
											    lth_turnoHistoria.getIdTurno(),
											    TipoArchivoCommon.PROYECCION_RESOLUCION_TRASLADO_MATRICULA, true,
											    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
											);

									if(ldsr_documentosSalidaResolucion == null)
										ldsr_documentosSalidaResolucion = DaoCreator.getDocumentosSalidaDAO(
											    adm_manager
											)
												                                        .findByIdTurnoTipo(
												    lth_turnoHistoria.getIdTurno(),
												    TipoArchivoCommon.RESOLUCION_TRASLADO, true,
												    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
												);

									if(ldsr_documentosSalidaResolucion != null)
									{
										ls_consecutivoResolucion     = String.format(
											    "%06d", ldsr_documentosSalidaResolucion.getConsecutivoResolucion()
											);
										ll_consecutivoResolucion     = ldsr_documentosSalidaResolucion
												.getConsecutivoResolucion();

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoResolucion);
									}
								}
							}

							{
								ls_tag = "<TAG_FECHA_RESOLUCION>";

								if(ls_plantilla.contains(ls_tag))
								{
									String           ls_fechaResol;
									SimpleDateFormat formatter;

									ld_dateResol     = new Date();
									formatter        = new SimpleDateFormat("dd/MM/yyyy");

									if(ld_dateResol != null)
									{
										ls_fechaResol     = StringUtils.getStringNotNull(
											    formatter.format(ld_dateResol)
											);

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaResol);
									}
								}
							}

							lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
							ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_return       = new PDFGenerator().convertirRTFToPDF(
								    ls_plantilla.getBytes(), adm_manager
								);

							if(lba_return == null)
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

							if(ab_firma)
							{
								Constantes lc_usuarioFirma;
								int        li_incrX = 0;
								int        li_incrY = 0;

								lc_usuarioFirma     = new Constantes();

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

								lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
										                        .findByIdWithImage(lc_usuarioFirma);

								byte[] lba_grafo;

								lba_grafo = null;

								if(lc_usuarioFirma != null)
								{
									lba_grafo     = lc_usuarioFirma.getImagenes();
									li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
									li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
								}

								lba_return = getFirmaMasivaBusiness()
										             .reemplazarBookmarsFirma(
										    lba_return, lba_grafo, li_incrX, li_incrY
										);
							}

							if(ab_salvar)
							{
								Imagenes    li_imagenes;
								ImagenesDAO li_DAO;
								long        ll_idImagenTemp;

								li_imagenes     = new Imagenes();
								li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdUsuarioCreacion(as_userId);
								li_imagenes.setIpCreacion(as_remoteIp);
								li_imagenes.setImagenBlob(lba_return);
								li_imagenes.setCodigoVerificacion(
								    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
								);

								ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

								if(ll_idImagenTemp > 0)
								{
									DocumentosSalida    lds_documentoSalida;
									DocumentosSalidaDAO lds_DAO;
									Long                ll_idImagen;

									lds_documentoSalida     = new DocumentosSalida();
									lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
									ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lds_documentoSalida.setConsecutivoResolucion(ll_consecutivoResolucion);
									lds_documentoSalida.setFechaResolucion(ld_dateResol);
									lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
									lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.FIJACION_AVISO);
									lds_documentoSalida.setTipo(TipoArchivoCommon.FIJACION_AVISO);
									lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
									lds_documentoSalida.setFechaPublicacionAvisoWeb(ld_fechaFijacion);
									lds_documentoSalida.setFechaDesfijacionAviso(ld_fechaDesfijacion);
									lds_documentoSalida.setIdTurno(ls_idTurno);
									lds_documentoSalida.setIdImagen(ll_idImagen);
									lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(ll_idTurnoHistoria));
									lds_documentoSalida.setIdUsuarioCreacion(as_userId);
									lds_documentoSalida.setIpCreacion(as_remoteIp);

									lds_DAO.insertOrUpdate(lds_documentoSalida, true);
								}
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar los documentos de actuaciones administrativas.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosActuacionesAdmin(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		return generarDocumentosActuacionesAdmin(
		    aaa_actuacionesAdministrativas, as_userId, as_remoteIp, al_motivo, false
		);
	}

	/**
	 * Método encargado de generar los documentos de actuaciones administrativas.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param ab_651 para saber si viene de 651
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosActuacionesAdmin(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp, long al_motivo,
	    boolean                ab_651
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;
		DAOManager             ldm_manager;

		laa_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actuacionesAdministrativas != null) && (al_motivo > 0))
			{
				OficiosTexto lot_oficiosTexto;
				boolean      lb_proyectar;
				boolean      lb_planeacion;
				boolean      lb_segundaInstancia;

				lb_proyectar            = aaa_actuacionesAdministrativas.isProyectar();
				lb_planeacion           = aaa_actuacionesAdministrativas.isPlaneacion();
				lb_segundaInstancia     = aaa_actuacionesAdministrativas.isSegundaInstancia();
				lot_oficiosTexto        = aaa_actuacionesAdministrativas.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					String ls_tipoArchivo;

					ls_tipoArchivo = aaa_actuacionesAdministrativas.getTipoArchivo();

					if(StringUtils.isValidString(ls_tipoArchivo))
					{
						Solicitud       ls_solicitud;
						boolean         lb_solicitud;
						boolean         lb_recurso;
						boolean         lb_traslado;
						boolean         lb_trasladoMasivo;
						boolean         lb_masivo;
						long            ll_idTurnoHistoria;
						OficiosTextoDAO lotd_DAO;
						String          ls_idSolicitud;
						String          ls_idTurno;

						ls_solicitud           = aaa_actuacionesAdministrativas.getSolicitud();
						lb_solicitud           = ls_solicitud != null;
						lb_recurso             = aaa_actuacionesAdministrativas.isRecurso();
						lb_traslado            = aaa_actuacionesAdministrativas.isTraslado();
						lb_masivo              = aaa_actuacionesAdministrativas.isMasivo();
						lb_trasladoMasivo      = lb_traslado
								&& (al_motivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA)
								&& !lb_masivo;
						ls_idSolicitud         = lb_solicitud ? ls_solicitud.getIdSolicitud() : null;
						ls_idTurno             = aaa_actuacionesAdministrativas.getIdTurno();
						lotd_DAO               = DaoCreator.getOficiosTextoDAO(ldm_manager);
						ll_idTurnoHistoria     = NumericUtils.getLong(
							    aaa_actuacionesAdministrativas.getIdTurnoHistoria()
							);

						if(lb_proyectar || lb_planeacion)
						{
							if(lb_planeacion)
							{
								if(!StringUtils.isValidString(lot_oficiosTexto.getReferencia()))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_REFERENCIA);
							}
							else
							{
								if(!StringUtils.isValidString(lot_oficiosTexto.getAntecedentes()))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);

								if(!StringUtils.isValidString(lot_oficiosTexto.getResuelve()))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
							}
						}
						else if(lb_segundaInstancia)
						{
							if(!StringUtils.isValidString(lot_oficiosTexto.getConsideracion()))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
						}
						else
						{
							if(lb_recurso)
								validarCamposResolucionRecursos(lot_oficiosTexto, al_motivo);
							else if(lb_traslado)
								validarCamposResolucionTraslados(lot_oficiosTexto, al_motivo, lb_masivo, ab_651);
							else
								validarCamposResolucion(lot_oficiosTexto, al_motivo);
						}

						if(ll_idTurnoHistoria > 0L)
						{
							if(!lb_traslado && !lb_proyectar && !lb_planeacion && !lb_segundaInstancia)
							{
								lotd_DAO.deleteByTurnoHistoria(NumericUtils.getLongWrapper(ll_idTurnoHistoria));
								DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									          .deleteByIdTurnoHistoria(ll_idTurnoHistoria);
							}
							else
							{
								TagPlantillaResolucion laa_actuacionesAdministrativas;

								laa_actuacionesAdministrativas = lb_segundaInstancia
									? plantillaDatosPorIdMotivoSegundaInstancia(ldm_manager, al_motivo, true, true)
									: plantillaDatosPorIdMotivoTraslados(
									    ldm_manager, al_motivo, !lb_masivo, true, false, lb_planeacion
									);

								if(laa_actuacionesAdministrativas != null)
								{
									String ls_idPlantilla;

									ls_idPlantilla = laa_actuacionesAdministrativas.getIdPlantilla();

									if(StringUtils.isValidString(ls_idPlantilla))
									{
										lotd_DAO.deleteByTurnoHistoriaPlantilla(
										    NumericUtils.getLongWrapper(ll_idTurnoHistoria), ls_idPlantilla
										);
										DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											          .deleteByIdTurnoHistoriaTipo(
											    ll_idTurnoHistoria, laa_actuacionesAdministrativas.getTipoArchivo()
											);
										lot_oficiosTexto.setPlantilla(ls_idPlantilla);
									}
								}
							}

							lot_oficiosTexto.setIdTurnoHistoria(NumericUtils.getLongWrapper(ll_idTurnoHistoria));
							lot_oficiosTexto.setIdUsuarioCreacion(as_userId);
							lot_oficiosTexto.setIpCreacion(as_remoteIp);
							lot_oficiosTexto.setSegundaInstancia(lb_segundaInstancia);

							lotd_DAO.insertOrUpdate(lot_oficiosTexto, true);
						}

						byte[] lba_archivo;

						lba_archivo = generarDocumentoAutoResolucion(
							    lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, true, false, lb_recurso,
							    lb_traslado, !lb_masivo, ab_651, ldm_manager
							);

						if(lba_archivo != null)
						{
							Collection<ZipEntryUtil> lczeu_zip;

							if(!lb_proyectar && !lb_planeacion && !lb_segundaInstancia && !lb_traslado)
							{
								long ll_idEtapa;

								ll_idEtapa = lb_recurso
									? EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
									: EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;

								aaa_actuacionesAdministrativas.setIdEtapa(ll_idEtapa);

								lczeu_zip = salvarPersonaNotificacion(
									    ldm_manager, aaa_actuacionesAdministrativas, lot_oficiosTexto, ls_idTurno,
									    ls_idSolicitud, al_motivo, as_userId, as_remoteIp
									);
							}
							else
								lczeu_zip = null;

							if(!CollectionUtils.isValidCollection(lczeu_zip))
								lczeu_zip = new ArrayList<ZipEntryUtil>();

							{
								ZipEntryUtil lzeu_pdf;

								lzeu_pdf = new ZipEntryUtil(
									    ls_tipoArchivo + ExtensionCommon.PDF_PUNTO,
									    new ByteArrayInputStream(lba_archivo)
									);
								lczeu_zip.add(lzeu_pdf);
							}

							if(lb_trasladoMasivo)
							{
								byte[]       lba_archivo2;
								OficiosTexto lot_data;

								lot_data = new OficiosTexto();

								lot_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(ll_idTurnoHistoria));
								lot_data.setPlantilla(ConstanteCommon.PLANTILLA_SUSPENSION_TRASLADOS);

								lot_data = lotd_DAO.findByTurnoHistoriaPlantilla(lot_data);

								if(lot_data != null)
								{
									lba_archivo2 = generarDocumentoAutoResolucion(
										    lot_data, as_userId, as_remoteIp, al_motivo, true, false,
										    aaa_actuacionesAdministrativas.isRecurso(), lb_traslado, false, ab_651,
										    ldm_manager
										);

									if(lba_archivo2 != null)
									{
										ZipEntryUtil lzeu_pdf;

										lzeu_pdf = new ZipEntryUtil(
											    TipoArchivoCommon.PROYECCION_RESOLUCION_TRASLADO_MASIVA
											    + ExtensionCommon.PDF_PUNTO, new ByteArrayInputStream(lba_archivo2)
											);
										lczeu_zip.add(lzeu_pdf);
									}
								}
							}

							if(CollectionUtils.isValidCollection(lczeu_zip))
							{
								laa_return = new TagPlantillaResolucion();

								laa_return.setArchivo(ZipUtils.generateZip(lczeu_zip));
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosActuacionesAdmin", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laa_return;
	}

	/**
	 * Método encargado de generar los documentos de rechaza recursos.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosRechazaRecursos(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;
		DAOManager             ldm_manager;

		laa_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(aaa_actuacionesAdministrativas != null)
			{
				OficiosTexto lot_oficiosTexto;

				lot_oficiosTexto = aaa_actuacionesAdministrativas.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					Solicitud ls_solicitud;
					Long      lL_idTurnoHistoria;
					long      ll_idTurnoHistoria;
					String    ls_idSolicitud;

					ls_solicitud           = aaa_actuacionesAdministrativas.getSolicitud();
					lL_idTurnoHistoria     = NumericUtils.getLongWrapper(
						    aaa_actuacionesAdministrativas.getIdTurnoHistoria()
						);
					ll_idTurnoHistoria     = NumericUtils.getLong(lL_idTurnoHistoria);
					ls_idSolicitud         = (ls_solicitud != null) ? ls_solicitud.getIdSolicitud() : null;

					validarCamposResolucionRechazaRecurso(lot_oficiosTexto);

					if(ll_idTurnoHistoria > 0L)
					{
						OficiosTextoDAO lotd_DAO;

						lotd_DAO = DaoCreator.getOficiosTextoDAO(ldm_manager);

						lotd_DAO.deleteByTurnoHistoria(NumericUtils.getLongWrapper(ll_idTurnoHistoria));

						lot_oficiosTexto.setIdUsuarioCreacion(as_userId);
						lot_oficiosTexto.setIpCreacion(as_remoteIp);

						lotd_DAO.insertOrUpdate(lot_oficiosTexto, true);

						DaoCreator.getDocumentosSalidaDAO(ldm_manager).deleteByIdTurnoHistoria(ll_idTurnoHistoria);
					}

					{
						Collection<CausalRechazoRecurso> lccrr_causalesRechazoRecurso;
						int                              li_count;

						lccrr_causalesRechazoRecurso     = aaa_actuacionesAdministrativas.getCausalesRechazaRecurso();
						li_count                         = 0;

						if(CollectionUtils.isValidCollection(lccrr_causalesRechazoRecurso))
						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(
								    lL_idTurnoHistoria
								);

							if(lth_turnoHistoria != null)
							{
								AccCausalRechazoRecursoDAO lacrrd_DAO;
								BigDecimal                 lbd_idEtapa;

								lacrrd_DAO      = DaoCreator.getAccCausalRechazoRecursoDAO(ldm_manager);
								lbd_idEtapa     = lth_turnoHistoria.getIdEtapa();

								if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
									aaa_actuacionesAdministrativas.setIdEtapa(NumericUtils.getLong(lbd_idEtapa));

								lacrrd_DAO.deleteByIdTurnoHistoria(lL_idTurnoHistoria);

								for(CausalRechazoRecurso lcrr_iterador : lccrr_causalesRechazoRecurso)
								{
									if((lcrr_iterador != null) && lcrr_iterador.isSeleccionado())
									{
										AccCausalRechazoRecurso lacrr_causalRechazoRecurso;

										lacrr_causalRechazoRecurso = new AccCausalRechazoRecurso();

										lacrr_causalRechazoRecurso.setIdCausal(
										    lcrr_iterador.getIdCausalRechazoRecurso()
										);
										lacrr_causalRechazoRecurso.setIdSolicitud(ls_idSolicitud);
										lacrr_causalRechazoRecurso.setIdTurnoHistoria(
										    lth_turnoHistoria.getIdTurnoHistoria()
										);
										lacrr_causalRechazoRecurso.setIdTurno(lth_turnoHistoria.getIdTurno());
										lacrr_causalRechazoRecurso.setIdUsuarioCreacion(as_userId);
										lacrr_causalRechazoRecurso.setIpCreacion(as_remoteIp);

										lacrrd_DAO.insertOrUpdate(lacrr_causalRechazoRecurso, true);

										li_count++;
									}
								}
							}
						}

						if(li_count == 0)
							throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_UNA_CAUSAL_A_RECHAZAR);
					}

					byte[] lba_archivo;

					lba_archivo = generarResolucionRechazaRecurso(
						    aaa_actuacionesAdministrativas, as_userId, as_remoteIp, true, false, ldm_manager
						);

					if(lba_archivo != null)
					{
						Collection<ZipEntryUtil> lczeu_zip;

						lczeu_zip = new ArrayList<ZipEntryUtil>();

						aaa_actuacionesAdministrativas.setIdEtapa(
						    EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
						);

						salvarPersonaNotificacion(
						    ldm_manager, aaa_actuacionesAdministrativas, lot_oficiosTexto,
						    aaa_actuacionesAdministrativas.getIdTurno(), ls_idSolicitud,
						    aaa_actuacionesAdministrativas.getIdMotivo(), as_userId, as_remoteIp
						);

						{
							ZipEntryUtil lzeu_pdf;

							lzeu_pdf = new ZipEntryUtil(
								    IdentificadoresCommon.RESOLUCION + ExtensionCommon.PDF_PUNTO,
								    new ByteArrayInputStream(lba_archivo)
								);
							lczeu_zip.add(lzeu_pdf);
						}

						if(CollectionUtils.isValidCollection(lczeu_zip))
						{
							laa_return = new TagPlantillaResolucion();

							laa_return.setArchivo(ZipUtils.generateZip(lczeu_zip));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosActuacionesAdmin", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laa_return;
	}

	/**
	 * Método encargado de generar los documentos de traslados.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosTraslados(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;
		DAOManager             ldm_manager;

		laa_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actuacionesAdministrativas != null) && (al_motivo > 0))
			{
				OficiosTexto lot_oficiosTexto;

				lot_oficiosTexto = aaa_actuacionesAdministrativas.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					String ls_tipoArchivo;

					ls_tipoArchivo = aaa_actuacionesAdministrativas.getTipoArchivo();

					if(StringUtils.isValidString(ls_tipoArchivo))
					{
						boolean lb_traslado;
						boolean lb_masivo;

						lb_traslado     = aaa_actuacionesAdministrativas.isTraslado();
						lb_masivo       = aaa_actuacionesAdministrativas.isMasivo();

						if(lb_traslado)
							validarCamposResolucionTraslados(lot_oficiosTexto, al_motivo, lb_masivo);
						else
							validarCamposResolucion(lot_oficiosTexto, al_motivo);

						{
							long ll_idTurnoHistoria;

							ll_idTurnoHistoria = NumericUtils.getLong(
								    aaa_actuacionesAdministrativas.getIdTurnoHistoria()
								);

							if(ll_idTurnoHistoria > 0L)
							{
								OficiosTextoDAO lotd_DAO;

								lotd_DAO = DaoCreator.getOficiosTextoDAO(ldm_manager);

								lotd_DAO.deleteByTurnoHistoria(NumericUtils.getLongWrapper(ll_idTurnoHistoria));

								lot_oficiosTexto.setIdUsuarioCreacion(as_userId);
								lot_oficiosTexto.setIpCreacion(as_remoteIp);

								lotd_DAO.insertOrUpdate(lot_oficiosTexto, true);

								DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									          .deleteByIdTurnoHistoria(ll_idTurnoHistoria);
							}
						}

						byte[] lba_archivo;

						lba_archivo = generarDocumentoAutoResolucion(
							    lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, true, false,
							    aaa_actuacionesAdministrativas.isRecurso(), lb_traslado, !lb_masivo, ldm_manager
							);

						if(lba_archivo != null)
						{
							Collection<ZipEntryUtil> lczeu_zip;

							lczeu_zip = new ArrayList<ZipEntryUtil>();

							{
								ZipEntryUtil lzeu_pdf;

								lzeu_pdf = new ZipEntryUtil(
									    ls_tipoArchivo + ExtensionCommon.PDF_PUNTO,
									    new ByteArrayInputStream(lba_archivo)
									);
								lczeu_zip.add(lzeu_pdf);
							}

							if(CollectionUtils.isValidCollection(lczeu_zip))
							{
								laa_return = new TagPlantillaResolucion();

								laa_return.setArchivo(ZipUtils.generateZip(lczeu_zip));
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosTraslados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laa_return;
	}

	/**
	 * Método encargado de generar el documento de resolución de rechaza recurso.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarResolucionRechazaRecurso(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, String as_userId, String as_remoteIp, boolean ab_salvar,
	    boolean ab_firma, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(aaa_actuacionesAdministrativas != null)
		{
			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aaa_actuacionesAdministrativas.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;

					ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if(lt_turno != null)
						{
							byte[] lba_plantilla;
							String ls_idPlantilla;

							ls_idPlantilla     = ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_RECURSO;
							lba_plantilla      = DaoCreator.getConstantesDAO(adm_manager).findImagenes(ls_idPlantilla);

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = ls_idPlantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								Map<String, String> lmss_datos;
								String              ls_idCirculo;
								String              ls_idSolicitud;
								String              ls_plantilla;
								String              ls_tag;

								lmss_datos         = null;
								ls_idCirculo       = lt_turno.getIdCirculo();
								ls_idSolicitud     = lt_turno.getIdSolicitud();
								ls_plantilla       = new String(lba_plantilla);

								ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

								if(StringUtils.isValidString(ls_idCirculo))
								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = new CirculoRegistral();

									lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(lcr_circuloRegistral);

									if(lcr_circuloRegistral != null)
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
										{
											ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

											ls_tag = "<TAG_MUN_OFI_ORIGEN>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
										}

										if(StringUtils.isValidString(ls_idSolicitud))
										{
											ls_tag     = "<TAG_ID_SOLICITUD_MATRICULA>";

											ls_plantilla     = resolverTagMatricula(
												    adm_manager, ls_plantilla, ls_tag, ls_idSolicitud, ls_idCirculo,
												    false
												);

											ls_tag = "<TAG_TIPO_ACTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Collection<Acto> lca_actos;

												lca_actos     = new ArrayList<Acto>();
												lca_actos     = DaoCreator.getActoDAO(adm_manager)
														                      .findByIdSolicitudCirculo(
														    ls_idSolicitud, ls_idCirculo
														);

												if(CollectionUtils.isValidCollection(lca_actos))
												{
													Acto la_acto;

													la_acto = lca_actos.iterator().next();

													if(la_acto != null)
													{
														TipoActo lta_tipoActo;

														lta_tipoActo = DaoCreator.getTipoActoDAO(adm_manager)
																                     .findById(la_acto.getIdTipoActo());

														if(lta_tipoActo != null)
														{
															String ls_nombreActo;

															ls_nombreActo = lta_tipoActo.getNombre();

															if(StringUtils.isValidString(ls_nombreActo))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreActo
																	);
														}
													}
												}
											}

											ls_tag = "<TAG_TIPO_RECURSO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_idSolicitud1;

												ls_idSolicitud1 = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
														                        .findMaxByIdSolicitud(ls_idSolicitud);

												if(StringUtils.isValidString(ls_idSolicitud1))
												{
													Solicitud ls_solicitud;

													ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
															                     .findById(ls_idSolicitud1);

													if(ls_solicitud != null)
													{
														Subproceso lsp_subproceso;

														lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
																                       .findById(
																    ls_solicitud.getIdProceso(),
																    ls_solicitud.getIdSubproceso()
																);

														if(lsp_subproceso != null)
														{
															String ls_nombreSubproceso;

															ls_nombreSubproceso = lsp_subproceso.getNombre();

															if(StringUtils.isValidString(ls_nombreSubproceso))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreSubproceso
																	);
														}
													}
												}
											}
										}

										ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tipoOficina;

											ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

											if(StringUtils.isValidString(ls_tipoOficina))
											{
												if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
													ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
												else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
													ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
											}
										}

										ls_tag = "<TAG_NUMERO_RESOLUCION>";

										if(ls_plantilla.contains(ls_tag))
										{
											// TODO resolver TAG_NUMERO_RESOLUCION
										}

										ls_tag = "<TAG_NOMBRE_ORIP>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(lcr_circuloRegistral != null)
											{
												String ls_nombreOrip;

												ls_nombreOrip = lcr_circuloRegistral.getNombre();

												if(StringUtils.isValidString(ls_nombreOrip))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreOrip);
											}
										}
									}
								}

								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_idPersona;

										ls_tag           = "<TAG_NIR>";
										ls_idPersona     = ls_solicitud.getIdPersona();

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_nir;

											ls_nir = ls_solicitud.getNir();

											if(StringUtils.isValidString(ls_nir))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
										}

										ls_plantilla = reemplazarTagsDocumento(
											    adm_manager, ls_solicitud, lt_turno, ls_plantilla
											);

										if(StringUtils.isValidString(ls_idPersona))
										{
											Persona lp_persona;

											lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

											if(lp_persona != null)
											{
												String ls_tag1;
												String ls_tag2;

												ls_tag      = "<TAG_NOMBRE_INTERESADO>";
												ls_tag1     = "<TAG_DEPAR_INTERESADO>";
												ls_tag2     = "<TAG_MUNICIPIO_INTERESADO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_nombreCompleto;

													ls_nombreCompleto = lp_persona.getNombreCompleto();

													if(StringUtils.isValidString(ls_nombreCompleto))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreCompleto);
												}

												ls_tag = "<TAG_DOCUMENTO_INTERESADO>";

												{
													String ls_numeroDocumento;

													ls_numeroDocumento     = null;

													ls_numeroDocumento = lp_persona.getNumeroDocumento();

													if(ls_plantilla.contains(ls_tag))
													{
														if(StringUtils.isValidString(ls_numeroDocumento))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, ls_numeroDocumento
																);
														else
															ls_plantilla = ls_plantilla.replace(ls_tag, " ");
													}
												}

												ls_tag = "<TAG_CORREO_ELECTRONICO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idCorreo;

													ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

													if(!StringUtils.isValidString(ls_idCorreo))
														ls_idCorreo = ls_solicitud.getIdCorreoElectronicoComunicacion();

													if(StringUtils.isValidString(ls_idCorreo))
													{
														PersonaCorreoElectronico lpce_correo;

														lpce_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
															    adm_manager
															).findById(ls_idPersona, ls_idCorreo);

														if(lpce_correo != null)
														{
															String ls_correo;

															ls_correo = lpce_correo.getCorreoElectronico();

															if(StringUtils.isValidString(ls_correo))
																ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);
														}
													}
												}

												ls_tag = "<TAG_DIR_INTERESADO>";

												if(
												    ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag1)
													    || ls_plantilla.contains(ls_tag2)
												)
												{
													String ls_idDireccion;

													ls_idDireccion = ls_solicitud.getIdDireccion();

													if(!StringUtils.isValidString(ls_idDireccion))
														ls_idDireccion = ls_solicitud.getIdDireccionComunicacion();

													if(StringUtils.isValidString(ls_idDireccion))
													{
														PersonaDireccion lpd_direccion;

														lpd_direccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
																                      .findById(
																    ls_idPersona, ls_idDireccion
																);

														if(lpd_direccion != null)
														{
															String ls_direccion;
															String ls_idDepartamento;
															String ls_idPais;

															ls_direccion          = lpd_direccion.getDireccion();
															ls_idDepartamento     = lpd_direccion.getIdDepartamento();
															ls_idPais             = lpd_direccion.getIdPais();

															if(
															    StringUtils.isValidString(ls_direccion)
																    && ls_plantilla.contains(ls_tag)
															)
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_direccion
																	);

															if(StringUtils.isValidString(ls_idDepartamento))
															{
																Departamento ld_departamento;

																ld_departamento = DaoCreator.getDepartamentoDAO(
																	    adm_manager
																	).findById(ls_idPais, ls_idDepartamento);

																if(ld_departamento != null)
																{
																	String ls_idMunicipio;
																	String ls_nombreDepartamento;

																	ls_idMunicipio            = lpd_direccion
																			.getIdMunicipio();
																	ls_nombreDepartamento     = ld_departamento
																			.getNombre();

																	if(
																	    StringUtils.isValidString(
																		        ls_nombreDepartamento
																		    ) && ls_plantilla.contains(ls_tag1)
																	)
																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag1, ls_nombreDepartamento
																			);

																	if(ls_idMunicipio != null)
																	{
																		Municipio lm_municipio;

																		lm_municipio = DaoCreator.getMunicipioDAO(
																			    adm_manager
																			)
																				                     .findById(
																				    ls_idPais, ls_idDepartamento,
																				    ls_idMunicipio
																				);

																		if(lm_municipio != null)
																		{
																			String ls_nombreMunicipio;

																			ls_nombreMunicipio = lm_municipio.getNombre();

																			if(
																			    StringUtils.isValidString(
																				        ls_nombreMunicipio
																				    ) && ls_plantilla.contains(ls_tag2)
																			)
																				ls_plantilla = ls_plantilla.replace(
																					    ls_tag2, ls_nombreMunicipio
																					);
																		}
																	}
																}
															}
														}
													}
												}

												ls_tag = "<TAG_TEL_INTERESADO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idTel;

													ls_idTel = ls_solicitud.getIdTelefono();

													if(!StringUtils.isValidString(ls_idTel))
														ls_idTel = ls_solicitud.getIdTelefonoComunicacion();

													if(StringUtils.isValidString(ls_idTel))
													{
														PersonaTelefono lpt_telefono;

														lpt_telefono = DaoCreator.getPersonaTelefonoDAO(adm_manager)
																                     .findById(ls_idPersona, ls_idTel);

														if(lpt_telefono != null)
														{
															String ls_telefono;

															ls_telefono = lpt_telefono.getTelefono();

															if(
															    StringUtils.isValidString(ls_telefono)
																    && ls_plantilla.contains(ls_tag)
															)
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_telefono
																	);
														}
													}
												}
											}
										}
									}
								}

								{
									ConstantesDAO lcd_DAO;

									lcd_DAO     = DaoCreator.getConstantesDAO(adm_manager);

									ls_tag = "<TAG_RESOLUCION>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "RESOLUCION",
											    lcd_DAO.findById(ConstanteCommon.TAG_RESOL)
											);

									ls_tag = "<TAG_FECHA_RESOL>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = putCustomBookMark(
											    ls_plantilla, ls_tag, "FECHA_RESOL",
											    lcd_DAO.findById(ConstanteCommon.TAG_FECHA_RESOL)
											);
								}

								ls_tag = "<TAG_EXPEDIENTE>";

								if(ls_plantilla.contains(ls_tag))
								{
									if(ab_firma)
									{
										String ls_expediente;

										ls_expediente = lt_turno.getExpediente();

										if(StringUtils.isValidString(ls_expediente))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_expediente);
									}
								}

								ls_tag = "<TAG_OFICIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									// TODO Poner BookMark	
								}

								ls_tag = "<TAG_CAUSALES_RECHAZO>";

								if(ls_plantilla.contains(ls_tag))
								{
									Collection<CausalRechazoRecurso> lccrr_causalesRechazoRecurso;

									lccrr_causalesRechazoRecurso = aaa_actuacionesAdministrativas
											.getCausalesRechazaRecurso();

									if(CollectionUtils.isValidCollection(lccrr_causalesRechazoRecurso))
									{
										StringBuilder lsb_sb;

										lsb_sb = new StringBuilder();

										for(CausalRechazoRecurso lcrr_iterador : lccrr_causalesRechazoRecurso)
										{
											if((lcrr_iterador != null) && lcrr_iterador.isSeleccionado())
											{
												lsb_sb.append(lcrr_iterador.getIdCausalRechazoRecurso());
												lsb_sb.append(IdentificadoresCommon.PUNTO);
												lsb_sb.append(" ");
												lsb_sb.append(lcrr_iterador.getNombre());
												lsb_sb.append(IdentificadoresCommon.SALTO_LINEA_RTF);
											}
										}

										ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
									}
								}

								Constantes lc_usuarioFirma;

								lc_usuarioFirma = null;

								{
									String ls_tagUsuarioFirma;

									ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_RECHAZO_RECURSO>";

									if(ls_plantilla.contains(ls_tagUsuarioFirma))
									{
										lc_usuarioFirma = new Constantes();

										lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

										lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
												                            .findByIdWithImage(lc_usuarioFirma);

										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
												    "<TAG_CARGO_FIRMA_RECHAZO_RECURSO>"
												);
									}
								}

								{
									Calendar lc_fecha;

									lc_fecha = Calendar.getInstance();

									{
										int li_dia;

										li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

										if(ls_plantilla.contains("<TAG_DIAS_LETRAS>"))
										{
											String ls_dia;

											ls_dia = NumericUtils.convertirLetras(li_dia, false);

											if(StringUtils.isValidString(ls_dia))
												ls_plantilla = ls_plantilla.replace("<TAG_DIAS_LETRAS>", ls_dia);
										}

										if(ls_plantilla.contains("<TAG_DIAS>") && (li_dia > 0))
											ls_plantilla = ls_plantilla.replace(
												    "<TAG_DIAS>", StringUtils.getString(li_dia)
												);
									}

									String ls_tag2;
									String ls_tag3;

									ls_tag      = "<TAG_BQN_DOC_ID_TIPO_DOC>";
									ls_tag2     = "<TAG_BQN_DOC_NUMERO>";
									ls_tag3     = "<TAG_BQN_DOC_FECHA>";

									if(
									    (ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag2)
										    || ls_plantilla.contains(ls_tag3))
										    && StringUtils.isValidString(ls_idSolicitud)
									)
									{
										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(ls_idSolicitud);

										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

										if(ls_solicitud != null)
										{
											String ls_idDocumento;

											ls_idDocumento = ls_solicitud.getIdDocumento();

											if(StringUtils.isValidString(ls_idDocumento))
											{
												Documento ld_documento;

												ld_documento = new Documento();

												ld_documento.setIdDocumento(ls_idDocumento);

												ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
														                     .findById(ld_documento);

												if(ld_documento != null)
												{
													Date   ld_fechaDocumento;
													String ls_tipoDocumento;
													String ls_numero;

													ld_fechaDocumento     = ld_documento.getFechaDocumento();
													ls_tipoDocumento      = ld_documento.getIdTipoDocumento();
													ls_numero             = ld_documento.getNumero();

													if(ld_fechaDocumento != null)
														ls_plantilla = escribirTagFechaLarga(
															    ls_plantilla, ls_tag3, ld_fechaDocumento
															);

													if(
													    ls_plantilla.contains(ls_tag2)
														    && StringUtils.isValidString(ls_numero)
													)
														ls_plantilla = ls_plantilla.replace(ls_tag2, ls_numero);

													if(StringUtils.isValidString(ls_tipoDocumento))
													{
														TipoDocumentoPublico ltdp_tipoDocumento;

														ltdp_tipoDocumento = new TipoDocumentoPublico();

														ltdp_tipoDocumento.setIdTipoDocumento(ls_tipoDocumento);

														ltdp_tipoDocumento = DaoCreator.getTipoDocumentoPublicoDAO(
															    adm_manager
															).findById(ltdp_tipoDocumento);

														if(ltdp_tipoDocumento != null)
														{
															String ls_nombre;

															ls_nombre = ltdp_tipoDocumento.getNombre();

															if(
															    ls_plantilla.contains(ls_tag)
																    && StringUtils.isValidString(ls_nombre)
															)
																ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
														}
													}
												}
											}
										}
									}

									if(ls_plantilla.contains("<TAG_MES_LETRAS>"))
									{
										int    li_mes;
										String ls_mes;

										li_mes     = lc_fecha.get(Calendar.MONTH);
										ls_mes     = DateUtils.getMes(li_mes + 1);

										if(StringUtils.isValidString(ls_mes))
											ls_plantilla = ls_plantilla.replace("<TAG_MES_LETRAS>", ls_mes);
									}

									{
										int li_anio;

										li_anio = lc_fecha.get(Calendar.YEAR);

										if(ls_plantilla.contains("<TAG_ANIO_LETRAS>"))
										{
											String ls_anio;

											ls_anio = NumericUtils.convertirLetras(li_anio, false);

											if(StringUtils.isValidString(ls_anio))
												ls_plantilla = ls_plantilla.replace("<TAG_ANIO_LETRAS>", ls_anio);
										}

										if(ls_plantilla.contains("<TAG_ANIO>") && (li_anio > 0))
											ls_plantilla = ls_plantilla.replace(
												    "<TAG_ANIO>", StringUtils.getString(li_anio)
												);
									}
								}

								{
									String ls_antecedentes;
									String ls_dispone;
									String ls_argumentosRecurrente;
									String ls_consideraciones;
									String ls_resuelve;
									Long   ll_diasSuspension;

									ls_antecedentes             = null;
									ls_dispone                  = null;
									ls_argumentosRecurrente     = null;
									ls_consideraciones          = null;
									ls_resuelve                 = null;
									ll_diasSuspension           = null;

									if(ab_salvar && ab_firma)
									{
										OficiosTexto lot_data;

										lot_data = DaoCreator.getOficiosTextoDAO(adm_manager)
												                 .findByTurnoHistoria(ll_idTurnoHistoria);

										if(lot_data != null)
										{
											ls_antecedentes             = lot_data.getAntecedentes();
											ls_dispone                  = lot_data.getRazon1();
											ls_argumentosRecurrente     = lot_data.getArgumentosRecurrente();
											ls_consideraciones          = lot_data.getConsideracion();
											ls_resuelve                 = lot_data.getResuelve();
											ll_diasSuspension           = lot_data.getDiasSuspension();
										}
									}
									else
									{
										OficiosTexto lot_oficiosTexto;

										lot_oficiosTexto = aaa_actuacionesAdministrativas.getOficiosTexto();

										if(lot_oficiosTexto != null)
										{
											ls_antecedentes             = lot_oficiosTexto.getAntecedentes();
											ls_dispone                  = lot_oficiosTexto.getRazon1();
											ls_argumentosRecurrente     = lot_oficiosTexto.getArgumentosRecurrente();
											ls_consideraciones          = lot_oficiosTexto.getConsideracion();
											ls_resuelve                 = lot_oficiosTexto.getResuelve();
											ll_diasSuspension           = lot_oficiosTexto.getDiasSuspension();
										}
									}

									ls_tag = " <TAG_IDENTIFICACION_INTERVINIENTE_RECURSO>";

									if(ls_plantilla.contains(ls_tag))
									{
										Collection<SolicitudAsociada> lcs_solicitudAsociada;

										lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
												                              .findAllByIdSolicitud(
												    ls_idSolicitud, false
												);

										if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
										{
											StringBuilder lsb_sb;
											SolicitudDAO  lsd_DAO;
											PersonaDAO    lpd_DAO;
											int           li_contador;

											lsb_sb          = new StringBuilder("Que el(la) señor(a) ");
											lsd_DAO         = DaoCreator.getSolicitudDAO(adm_manager);
											lpd_DAO         = DaoCreator.getPersonaDAO(adm_manager);
											li_contador     = 1;

											for(SolicitudAsociada lsa_iterador : lcs_solicitudAsociada)
											{
												if(lsa_iterador != null)
												{
													String ls_idSolicitudAsociada;

													ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

													if(StringUtils.isValidString(ls_idSolicitudAsociada))
													{
														Solicitud ls_solicitud;

														ls_solicitud = lsd_DAO.findById(ls_idSolicitudAsociada);

														if(ls_solicitud != null)
														{
															String ls_idProceso;

															ls_idProceso = ls_solicitud.getIdProceso();

															if(
															    StringUtils.isValidString(ls_idProceso)
																    && ls_idProceso.equalsIgnoreCase(
																        ProcesoCommon.ID_PROCESO_47
																    )
															)
															{
																Persona lp_persona;

																lp_persona = lpd_DAO.findById(
																	    ls_solicitud.getIdPersona()
																	);

																if(lp_persona != null)
																{
																	if(li_contador > 1)
																		lsb_sb.append(", el(la) señor(a) ");

																	lsb_sb.append(lp_persona.getNombreCompleto());
																	lsb_sb.append(" identificado(a) con ");
																	lsb_sb.append(lp_persona.getIdDocumentoTipo());
																	lsb_sb.append(" número ");
																	lsb_sb.append(lp_persona.getNumeroDocumento());
																	lsb_sb.append(", en calidad de ");

																	{
																		String ls_idCalidadSolicitante;

																		ls_idCalidadSolicitante = ls_solicitud
																				.getIdCalidadSolicitante();

																		if(
																		    StringUtils.isValidString(
																			        ls_idCalidadSolicitante
																			    )
																		)
																		{
																			CalidadSolicitante lcs_calidadSolicitante;

																			lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(
																				    adm_manager
																				).findById(ls_idCalidadSolicitante);

																			if(lcs_calidadSolicitante != null)
																				lsb_sb.append(
																				    lcs_calidadSolicitante.getNombre()
																				);
																		}
																	}
																}
															}
														}
													}

													li_contador++;
												}
											}

											ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
										}
									}

									ls_tag = "<TAG_USUARIO_410>";

									if(StringUtils.isValidString(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

									ls_tag = "<TAG_ANTECEDENTES_PANTALLA>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_antecedentes))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_antecedentes);

									ls_tag = "<TAG_DISPONE_PANTALLA>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_dispone))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_dispone);

									ls_tag = "<TAG_ARGUMENTOS_DEL_RECURRENTE_PANTALLA>";

									if(
									    ls_plantilla.contains(ls_tag)
										    && StringUtils.isValidString(ls_argumentosRecurrente)
									)
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_argumentosRecurrente);

									ls_tag = "<TAG_CONSIDERACIONES_PANTALLA>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideraciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

									ls_tag = "<TAG_RESUELVE_PANTALLA>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_resuelve))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_resuelve);

									ls_tag = "<TAG_DIAS_SUSPENSION>";

									if(ls_plantilla.contains(ls_tag) && NumericUtils.isValidLong(ll_diasSuspension))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ll_diasSuspension)
											);
								}

								ls_plantilla     = escribirTagFechaLarga(ls_plantilla);
								lmss_datos       = finalizarPlantilla(ls_plantilla, ll_idTurnoHistoria, adm_manager);
								ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
								lba_return       = new PDFGenerator().convertirRTFToPDF(
									    ls_plantilla.getBytes(), adm_manager
									);

								if(lba_return == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);

								if(ab_firma)
								{
									byte[] lba_grafo;
									int    li_incrX;
									int    li_incrY;

									lba_grafo     = null;
									li_incrX      = 0;
									li_incrY      = 0;

									if(lc_usuarioFirma != null)
									{
										lba_grafo     = lc_usuarioFirma.getImagenes();
										li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
										li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
									}

									lba_return = getFirmaMasivaBusiness()
											             .reemplazarBookmarsFirma(
											    lba_return, lba_grafo, li_incrX, li_incrY
											);
								}

								if(ab_salvar)
								{
									Imagenes    li_imagenes;
									ImagenesDAO li_DAO;
									long        ll_idImagenTemp;

									li_imagenes     = new Imagenes();
									li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

									li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagenes.setIdUsuarioCreacion(as_userId);
									li_imagenes.setIpCreacion(as_remoteIp);
									li_imagenes.setImagenBlob(lba_return);
									li_imagenes.setCodigoVerificacion(
									    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
									);
									li_imagenes.setIdTurno(ls_idTurno);

									ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

									if(ll_idImagenTemp > 0)
									{
										DocumentosSalida             lds_documentoSalida;
										Collection<DocumentosSalida> lcds_documentoSalida;
										DocumentosSalidaDAO          lds_DAO;
										Long                         ll_idImagen;

										lds_documentoSalida     = new DocumentosSalida();
										lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
										ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

										lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
										lds_documentoSalida.setIdTurno(ls_idTurno);
										lds_documentoSalida.setTipo(TipoArchivoCommon.RESOLUCION_RECHAZA_RECURSO);
										lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);
										lds_documentoSalida.setRepositorio(
										    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
										);
										lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
										lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

										lcds_documentoSalida = lds_DAO.findAllDocumentByTurnoHistoriaTipoEstado(
											    lds_documentoSalida
											);

										if(CollectionUtils.isValidCollection(lcds_documentoSalida))
										{
											lds_documentoSalida = lcds_documentoSalida.iterator().next();

											if(lds_documentoSalida != null)
											{
												lds_documentoSalida.setIdImagen(ll_idImagen);
												lds_documentoSalida.setIdUsuarioModificacion(as_userId);
												lds_documentoSalida.setIpModificacion(as_remoteIp);

												lds_DAO.updateImagen(lds_documentoSalida);
											}
										}
										else
										{
											lds_documentoSalida = new DocumentosSalida();

											lds_documentoSalida.setIdTurno(ls_idTurno);
											lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setTipo(TipoArchivoCommon.RESOLUCION_RECHAZA_RECURSO);
											lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);
											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);
											lds_DAO.insertOrUpdate(lds_documentoSalida, true);
										}
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarResolucionRechazaRecurso", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de llenar oficios texto con textos precargados.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los datos a mostrar en la plantilla.
	 * @param amss_data Argumento de tipo <code>Map<String, String></code> que contiene los textos a mostrar en la plantilla.
	 * @param at_turno Argumento de tipo <code>Turno</code> que contiene el turno a consultar.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el motivo a consultar.
	 * @param al_idEtapa Argumento de tipo <code>Long</code> que contiene el motivo a consultar.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public synchronized void llenarOficiosTexto(
	    DAOManager adm_manager, OficiosTexto aot_oficiosTexto, Map<String, String> amss_data, Turno at_turno,
	    long al_idMotivo, Long al_idEtapa
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(amss_data) && (aot_oficiosTexto != null))
			{
				{
					String ls_tag;

					ls_tag = IdentificadoresCommon.TAG_ANALISIS_PROBATORIO_PANTALLA;

					if(amss_data.containsKey(ls_tag))
						aot_oficiosTexto.setAnalisisProbatorio(
						    llenarTagsTexto(amss_data.get(ls_tag), at_turno, al_idMotivo, adm_manager, al_idEtapa)
						);
				}

				{
					String ls_tag;

					ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_DESPACHO_PANTALLA;

					if(amss_data.containsKey(ls_tag))
						aot_oficiosTexto.setConsideracion(
						    llenarTagsTexto(amss_data.get(ls_tag), at_turno, al_idMotivo, adm_manager, al_idEtapa)
						);
				}

				{
					String ls_tag;

					ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_PANTALLA;

					if(amss_data.containsKey(ls_tag))
						aot_oficiosTexto.setPertinencia(
						    llenarTagsTexto(amss_data.get(ls_tag), at_turno, al_idMotivo, adm_manager, al_idEtapa)
						);
				}

				{
					String ls_tag;

					ls_tag = IdentificadoresCommon.TAG_DISPONE_PANTALLA;

					if(amss_data.containsKey(ls_tag))
						aot_oficiosTexto.setRazon1(
						    llenarTagsTexto(amss_data.get(ls_tag), at_turno, al_idMotivo, adm_manager, al_idEtapa)
						);
				}

				{
					String ls_tag;

					ls_tag = IdentificadoresCommon.TAG_ENCABEZADO;

					if(amss_data.containsKey(ls_tag))
						aot_oficiosTexto.setEncabezado(
						    llenarTagsTexto(amss_data.get(ls_tag), at_turno, al_idMotivo, adm_manager, al_idEtapa)
						);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarOficiosTexto", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Válida que el tramite haga parte de una prorroga de documentación.
	 *
	 * @param as_idTurnoHistoria Variable que contiene el id del turno historia en trámite.
	 * @return boolean que válida que el tramite haga parte de una prorroga de documentación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean prorrogaDocumentacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findById(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						SolicitudAsociada lsa_solicitudAsociada;

						lsa_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
								                              .findByIdSolicitud(
								    new SolicitudAsociada(ls_idSolicitud, false)
								);

						if(lsa_solicitudAsociada != null)
						{
							String ls_idSolicitud1;

							ls_idSolicitud1 = lsa_solicitudAsociada.getIdSolicitud1();

							if(StringUtils.isValidString(ls_idSolicitud1))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud1);

								if(ls_solicitud != null)
								{
									String ls_idProceso;

									ls_idProceso     = ls_solicitud.getIdProceso();
									lb_return        = StringUtils.isValidString(ls_idProceso)
											&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
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

			clh_LOGGER.error("prorrogaDocumentacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método encargado de salvar los datos de persona notificación.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param aaa_actuacionesAdministrativas Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los datos a salvar.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los datos a salvar para oficios texto.
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el turno a salvar.
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene la solicitud a salvar.
	 * @param al_motivo Argumento de tipo <code>long</code> que contiene el motivo a salvar.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public Collection<ZipEntryUtil> salvarPersonaNotificacion(
	    DAOManager adm_manager, TagPlantillaResolucion aaa_actuacionesAdministrativas, OficiosTexto aot_oficiosTexto,
	    String as_idTurno, String as_idSolicitud, long al_motivo, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<ZipEntryUtil> lczeu_zip;

		lczeu_zip = new ArrayList<ZipEntryUtil>();

		try
		{
			Collection<PersonaNotificacion> lcpn_resultadosNotificacion;

			lcpn_resultadosNotificacion = aaa_actuacionesAdministrativas.getResultadosNotificacion();

			if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
			{
				long                   ll_idEtapa;
				PersonaNotificacionDAO lpnd_DAO;
				DocumentosSalidaDAO    lds_DAO;
				SolicitudDAO           lsd_DAO;
				String                 ls_tipoArchivo;

				ll_idEtapa         = aaa_actuacionesAdministrativas.getIdEtapa();
				lpnd_DAO           = DaoCreator.getPersonaNotificacionDAO(adm_manager);
				lsd_DAO            = DaoCreator.getSolicitudDAO(adm_manager);
				lds_DAO            = DaoCreator.getDocumentosSalidaDAO(adm_manager);
				ls_tipoArchivo     = aaa_actuacionesAdministrativas.getTipoArchivo();

				if(StringUtils.isValidString(as_idTurno))
				{
					Collection<PersonaNotificacion> lcpn_personaNotificacion;

					if(ll_idEtapa <= 0L)
						throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);

					lcpn_personaNotificacion = DaoCreator.getPersonaNotificacionDAO(adm_manager)
							                                 .findByIdTurnoDecision(
							    as_idTurno, al_motivo, NumericUtils.getLongWrapper(ll_idEtapa)
							);

					if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
					{
						for(PersonaNotificacion lpn_personaNotif : lcpn_personaNotificacion)
						{
							if(lpn_personaNotif != null)
							{
								String ls_idPersonaNotificacion;

								ls_idPersonaNotificacion = lpn_personaNotif.getIdPersonaNotificacion();

								if(StringUtils.isValidString(ls_idPersonaNotificacion))
									lds_DAO.deleteByIdTurnoYPersonaNotificacion(as_idTurno, ls_idPersonaNotificacion);
							}
						}

						lpnd_DAO.deleteByIdTurnoDecision(
						    as_idTurno, NumericUtils.getLongWrapper(al_motivo), NumericUtils.getLongWrapper(ll_idEtapa)
						);
					}
				}

				for(PersonaNotificacion lpn_iterador : lcpn_resultadosNotificacion)
				{
					if(lpn_iterador != null)
					{
						String ls_idPersona;

						ls_idPersona = lpn_iterador.getIdPersona();

						if(StringUtils.isValidString(ls_idPersona))
						{
							Registro  lr_registro;
							boolean   lb_registro;
							Solicitud ls_solicitudRegistro;

							lr_registro              = lpn_iterador.getRegistro();
							lb_registro              = lr_registro != null;
							ls_solicitudRegistro     = lb_registro ? lr_registro.getSolicitud()
								                                   : lsd_DAO.findById(as_idSolicitud);

							if(ls_solicitudRegistro != null)
							{
								String ls_idAutorizaComunicacion;
								String ls_idAutorizaNotificacion;

								boolean lb_registroPrecargado;
								boolean lb_autoriza;
								boolean lb_direccionCorrespondenciaComunicacion;
								boolean lb_direccionCorrespondenciaNotificacion;
								boolean lb_direccionResidenciaComunicacion;
								boolean lb_direccionResidenciaNotificacion;
								boolean lb_correoElectronicoComunicacion;
								boolean lb_correoElectronicoNotificacion;
								boolean lb_telefonoMovilComunicacion;
								boolean lb_telefonoFijoComunicacion;

								lb_registroPrecargado     = lpn_iterador.isRegistroPrecargado();

								ls_idAutorizaComunicacion     = StringUtils.getStringNotNull(
									    lb_registroPrecargado ? lpn_iterador.getIdAutorizacionComunicacion()
									                          : ls_solicitudRegistro.getIdAutorizacionComunicacion()
									);
								ls_idAutorizaNotificacion     = StringUtils.getStringNotNull(
									    lb_registroPrecargado ? lpn_iterador.getIdAutorizacionNotificacion()
									                          : ls_solicitudRegistro.getIdAutorizacionNotificacion()
									);

								lpn_iterador.setIdAutorizacionComunicacion(ls_idAutorizaComunicacion);
								lpn_iterador.setIdAutorizacionNotificacion(ls_idAutorizaNotificacion);

								lb_autoriza                                 = StringUtils.isValidString(
									    ls_idAutorizaComunicacion
									) && StringUtils.isValidString(ls_idAutorizaNotificacion);
								lb_direccionCorrespondenciaComunicacion     = ls_idAutorizaComunicacion.equalsIgnoreCase(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
								lb_direccionCorrespondenciaNotificacion     = ls_idAutorizaNotificacion.equalsIgnoreCase(
									    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									);
								lb_direccionResidenciaComunicacion          = ls_idAutorizaComunicacion.equalsIgnoreCase(
									    MedioNotificarCommon.DIRECCION_RESIDENCIA
									);
								lb_direccionResidenciaNotificacion          = ls_idAutorizaNotificacion.equalsIgnoreCase(
									    MedioNotificarCommon.DIRECCION_RESIDENCIA
									);

								lb_correoElectronicoComunicacion     = ls_idAutorizaComunicacion.equalsIgnoreCase(
									    MedioNotificarCommon.CORREO_ELECTRONICO
									) || ls_idAutorizaComunicacion.equalsIgnoreCase(MedioNotificarCommon.ORIP);
								lb_correoElectronicoNotificacion     = ls_idAutorizaNotificacion.equalsIgnoreCase(
									    MedioNotificarCommon.CORREO_ELECTRONICO
									) || ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP);
								lb_telefonoMovilComunicacion         = ls_idAutorizaComunicacion.equalsIgnoreCase(
									    MedioNotificarCommon.SMS
									);
								lb_telefonoFijoComunicacion          = ls_idAutorizaComunicacion.equalsIgnoreCase(
									    MedioNotificarCommon.ORIP
									);

								if(lb_registro)
								{
									ls_solicitudRegistro.setParticipaInterviniente(EstadoCommon.N);

									lr_registro = salvarInteresado(
										    adm_manager, lr_registro, as_userId, lr_registro.isPersonaVinculada()
										);

									if(lr_registro != null)
									{
										if(lb_autoriza)
										{
											if(
											    lb_direccionCorrespondenciaComunicacion
												    || lb_direccionCorrespondenciaNotificacion
											)
											{
												PersonaDireccion lpd_personaDireccion;

												lpd_personaDireccion = lr_registro.getDireccionCorrespondencia();

												if(lpd_personaDireccion != null)
												{
													String ls_idDireccion;

													ls_idDireccion = lpd_personaDireccion.getIdDireccion();

													if(StringUtils.isValidString(ls_idDireccion))
													{
														if(lb_direccionCorrespondenciaComunicacion)
															lpn_iterador.setIdDireccionComunicacion(ls_idDireccion);

														if(lb_direccionCorrespondenciaNotificacion)
															lpn_iterador.setIdDireccion(ls_idDireccion);
													}
												}
											}

											if(lb_direccionResidenciaComunicacion || lb_direccionResidenciaNotificacion)
											{
												PersonaDireccion lpd_personaDireccion;

												lpd_personaDireccion = lr_registro.getDireccionResidencia();

												if(lpd_personaDireccion != null)
												{
													String ls_idDireccion;

													ls_idDireccion = lpd_personaDireccion.getIdDireccion();

													if(StringUtils.isValidString(ls_idDireccion))
													{
														if(lb_direccionResidenciaComunicacion)
															lpn_iterador.setIdDireccionComunicacion(ls_idDireccion);

														if(lb_direccionResidenciaNotificacion)
															lpn_iterador.setIdDireccion(ls_idDireccion);
													}
												}
											}

											if(lb_correoElectronicoComunicacion || lb_correoElectronicoNotificacion)
											{
												PersonaCorreoElectronico lpce_personaCorreoElectronico;

												lpce_personaCorreoElectronico = lr_registro.getPersonaCorreoElectronico();

												if(lpce_personaCorreoElectronico != null)
												{
													String ls_idCorreoElectronico;

													ls_idCorreoElectronico = lpce_personaCorreoElectronico
															.getIdCorreoElectronico();

													if(StringUtils.isValidString(ls_idCorreoElectronico))
													{
														if(lb_correoElectronicoComunicacion)
															lpn_iterador.setIdCorreoElectronicoComunicacion(
															    ls_idCorreoElectronico
															);

														if(lb_correoElectronicoNotificacion)
															lpn_iterador.setIdCorreoElectronico(ls_idCorreoElectronico);
													}
												}
											}

											if(lb_telefonoMovilComunicacion)
											{
												PersonaTelefono lpt_personaTelefono;

												lpt_personaTelefono = lr_registro.getTelefonoMovil();

												if(lpt_personaTelefono != null)
												{
													String ls_idTelefono;

													ls_idTelefono = lpt_personaTelefono.getIdTelefono();

													if(StringUtils.isValidString(ls_idTelefono))
														lpn_iterador.setIdTelefonoComunicacion(ls_idTelefono);
												}
											}

											if(lb_telefonoFijoComunicacion)
											{
												PersonaTelefono lpt_personaTelefono;

												lpt_personaTelefono = lr_registro.getTelefonoFijo();

												if(lpt_personaTelefono != null)
												{
													String ls_idTelefono;

													ls_idTelefono = lpt_personaTelefono.getIdTelefono();

													if(StringUtils.isValidString(ls_idTelefono))
														lpn_iterador.setIdTelefonoComunicacion(ls_idTelefono);
												}
											}
										}

										{
											Persona lp_persona;

											lp_persona = lr_registro.getPersona();

											if(lp_persona != null)
												lpn_iterador.setIdPersona(lp_persona.getIdPersona());
										}
									}
								}
								else if(lb_autoriza)
								{
									if(lb_direccionCorrespondenciaComunicacion)
										lpn_iterador.setIdDireccionComunicacion(
										    lb_registroPrecargado ? lpn_iterador.getIdDireccionComunicacion()
										                          : ls_solicitudRegistro.getIdDireccionComunicacion()
										);

									if(lb_direccionCorrespondenciaNotificacion)
										lpn_iterador.setIdDireccion(
										    lb_registroPrecargado ? lpn_iterador.getIdDireccion()
										                          : ls_solicitudRegistro.getIdDireccion()
										);

									if(lb_direccionResidenciaComunicacion)
										lpn_iterador.setIdDireccionComunicacion(
										    lb_registroPrecargado ? lpn_iterador.getIdDireccionComunicacion()
										                          : ls_solicitudRegistro.getIdDireccionComunicacion()
										);

									if(lb_direccionResidenciaNotificacion)
										lpn_iterador.setIdDireccion(
										    lb_registroPrecargado ? lpn_iterador.getIdDireccion()
										                          : ls_solicitudRegistro.getIdDireccion()
										);

									if(lb_correoElectronicoComunicacion)
										lpn_iterador.setIdCorreoElectronicoComunicacion(
										    lb_registroPrecargado ? lpn_iterador.getIdCorreoElectronicoComunicacion()
										                          : ls_solicitudRegistro
											    .getIdCorreoElectronicoComunicacion()
										);

									if(lb_correoElectronicoNotificacion)
										lpn_iterador.setIdCorreoElectronico(
										    lb_registroPrecargado ? lpn_iterador.getIdCorreoElectronico()
										                          : ls_solicitudRegistro.getIdCorreoElectronico()
										);

									if(lb_telefonoMovilComunicacion || lb_telefonoFijoComunicacion)
										lpn_iterador.setIdTelefonoComunicacion(
										    lb_registroPrecargado ? lpn_iterador.getIdTelefonoComunicacion()
										                          : ls_solicitudRegistro.getIdTelefonoComunicacion()
										);
								}
							}
						}
						else if(
						    StringUtils.isValidString(lpn_iterador.getIdDepartamento())
							    && StringUtils.isValidString(lpn_iterador.getIdMunicipio())
						)
							lpn_iterador.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

						lpn_iterador.setIdDecisionPlantilla(StringUtils.getString(al_motivo));
						lpn_iterador.setIdTurno(as_idTurno);
						lpn_iterador.setIdSolicitud(as_idSolicitud);
						lpn_iterador.setIdUsuarioCreacion(as_userId);
						lpn_iterador.setIpCreacion(as_remoteIp);
						lpn_iterador.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapa));

						lpn_iterador = lpnd_DAO.insertOrUpdate(lpn_iterador, true);

						if(lpn_iterador != null)
						{
							byte[]  lba_comunicado;
							boolean lb_segundaInstancia;

							lba_comunicado          = null;
							lb_segundaInstancia     = aaa_actuacionesAdministrativas.isSegundaInstancia();

							if(aaa_actuacionesAdministrativas.isRecurso())
							{
								ls_tipoArchivo     = "RECHAZA_RECURSO";
								lba_comunicado     = generarComunicadoRechazaRecurso(
									    aaa_actuacionesAdministrativas, lpn_iterador, as_userId, as_remoteIp, true,
									    false, adm_manager
									);
							}
							else if(lb_segundaInstancia)
								lba_comunicado = generarDocumentoComunicado(
									    aot_oficiosTexto, as_userId, as_remoteIp, al_motivo, lpn_iterador,
									    lb_segundaInstancia, true, false, adm_manager
									);
							else if(al_motivo != MotivoTramiteCommon.NEGACION_APERTURA)
								lba_comunicado = generarDocumentoComunicado(
									    aot_oficiosTexto, as_userId, as_remoteIp, al_motivo, lpn_iterador, false, true,
									    false, adm_manager
									);

							if(lba_comunicado != null)
							{
								String       ls_destinatario;
								ZipEntryUtil lzeu_pdf;

								ls_destinatario     = StringUtils.getStringNotNull(lpn_iterador.getDestinatario());
								ls_destinatario     = ls_destinatario.replace(" ", "_");

								lzeu_pdf = new ZipEntryUtil(
									    IdentificadoresCommon.COMUNICADO + "_"
									    + (StringUtils.isValidString(ls_tipoArchivo) ? ls_tipoArchivo : "") + "_"
									    + ls_destinatario + ExtensionCommon.PDF_PUNTO,
									    new ByteArrayInputStream(lba_comunicado)
									);
								lczeu_zip.add(lzeu_pdf);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_AL_MENOS_UN_DESTINATARIO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosActuacionesAdmin", lb2be_e);

			throw lb2be_e;
		}

		return lczeu_zip;
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param aaa_parametros Argumento de tipo <code>ActuacionesAdministrativas</code> que contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los datos encontrados para actuaciones administrativas.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion validarCondicionesDecision(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = null;

		if(aaa_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idTurno;

				ls_idTurno = aaa_parametros.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					long ll_idMotivo;

					ll_idMotivo = aaa_parametros.getIdMotivo();

					if(ll_idMotivo > 0)
					{
						TurnoHistoria lth_turnoHistoria;
						long          ll_idMotivoValidar;

						lth_turnoHistoria      = new TurnoHistoria();
						ll_idMotivoValidar     = 0L;

						lth_turnoHistoria.setIdTurno(ls_idTurno);
						lth_turnoHistoria.setIdEtapa(
						    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
						);
						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);

						ll_idMotivoValidar = ((ll_idMotivo == MotivoTramiteCommon.AUTO_DE_ACLARACION)
								|| (ll_idMotivo == MotivoTramiteCommon.AUTO_DE_PRUEBAS))
							? MotivoTramiteCommon.AUTO_DE_APERTURA
							: ((ll_idMotivo == MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION)
							? MotivoTramiteCommon.AUTO_DE_PRUEBAS
							: ((ll_idMotivo == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION)
							? MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION : 0L));

						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(ll_idMotivoValidar));

						{
							int li_contador;

							li_contador = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
									                    .findCountByTurnoEtapaMotivo(lth_turnoHistoria);

							if(li_contador <= 0)
							{
								laa_actuacionesAdministrativas = plantillaDatosPorIdMotivo(
									    ldm_manager, ll_idMotivoValidar, true, false
									);

								if(laa_actuacionesAdministrativas != null)
								{
									TagPlantillaResolucion laa_tmp;

									laa_tmp = plantillaDatosPorIdMotivo(ldm_manager, ll_idMotivo, true, false);

									if(laa_tmp != null)
										laa_actuacionesAdministrativas.setTexto(laa_tmp.getTipoArchivo());
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarCondicionesDecision", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Validar datos interesado.
	 *
	 * @param ar_registro de ar registro
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarDatosInteresado(Registro ar_registro, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			salvarInteresado(ldm_manager, ar_registro, as_userId, false);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de generar el documento de auto.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		return visualizarCambiosResolucion(aaa_parametros, as_userId, as_remoteIp, al_motivo, false);
	}

	/**
	 * Método encargado de generar el documento de auto.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param ab_etapa651 correspondiente al valor de etapa 651
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp, long al_motivo, boolean ab_etapa651
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;

		laa_return = null;

		if(aaa_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				OficiosTexto lot_oficiosTexto;

				lot_oficiosTexto = aaa_parametros.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					boolean lb_recurso;
					boolean lb_traslado;
					boolean lb_masivo;
					boolean lb_proyectar;
					boolean lb_planeacion;
					boolean lb_segundaInstancia;

					lb_recurso              = aaa_parametros.isRecurso();
					lb_traslado             = aaa_parametros.isTraslado();
					lb_masivo               = aaa_parametros.isMasivo();
					lb_proyectar            = aaa_parametros.isProyectar();
					lb_planeacion           = aaa_parametros.isPlaneacion();
					lb_segundaInstancia     = aaa_parametros.isSegundaInstancia();

					if(lb_segundaInstancia)
					{
						if(!StringUtils.isValidString(lot_oficiosTexto.getConsideracion()))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
					}
					else if(lb_proyectar || lb_planeacion)
					{
						if(lb_planeacion)
						{
							if(!StringUtils.isValidString(lot_oficiosTexto.getReferencia()))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_REFERENCIA);
						}
						else
						{
							if(!StringUtils.isValidString(lot_oficiosTexto.getAntecedentes()))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);

							if(!StringUtils.isValidString(lot_oficiosTexto.getResuelve()))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}
					else
					{
						if(lb_recurso)
							validarCamposResolucionRecursos(lot_oficiosTexto, al_motivo);
						else if(lb_traslado)
							validarCamposResolucionTraslados(lot_oficiosTexto, al_motivo, lb_masivo, ab_etapa651);
						else
							validarCamposResolucion(lot_oficiosTexto, al_motivo);
					}

					laa_return = new TagPlantillaResolucion();

					lot_oficiosTexto.setIdTurnoHistoria(
					    NumericUtils.getLongWrapper(aaa_parametros.getIdTurnoHistoria())
					);
					lot_oficiosTexto.setProyectar(lb_proyectar);
					lot_oficiosTexto.setPlaneacion(lb_planeacion);
					lot_oficiosTexto.setSegundaInstancia(lb_segundaInstancia);

					laa_return.setArchivo(
					    generarDocumentoAutoResolucion(
					        lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, false, false, lb_recurso, lb_traslado,
					        !lb_masivo, ab_etapa651, ldm_manager
					    )
					);

					{
						GrabacionBusiness lgb_business;

						lgb_business = getGrabacionBusiness();

						lgb_business.salvarGrabacionMatriculas(
						    aaa_parametros.getSuspension(), as_userId, as_remoteIp, ldm_manager
						);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_return;
	}

	/**
	 * Método encargado de generar el documento de la resolución.
	 *
	 * @param aaa_parametros Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion visualizarCambiosResolucionRechazaRecurso(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;

		laa_return = null;

		if(aaa_parametros != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				OficiosTexto lot_oficiosTexto;

				lot_oficiosTexto = aaa_parametros.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					validarCamposResolucionRechazaRecurso(lot_oficiosTexto);

					laa_return = new TagPlantillaResolucion();

					laa_return.setArchivo(
					    generarResolucionRechazaRecurso(
					        aaa_parametros, as_userId, as_remoteIp, false, false, ldm_manager
					    )
					);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("visualizarCambiosResolucionRechazaRecurso", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return laa_return;
	}

	/**
	 * Método que permite extraer funcionalidades de la clase SegundaInstanciaBusiness.
	 *
	 * @return el valor de SegundaInstanciaBusiness
	 */
	protected SegundaInstanciaBusiness getSegundaInstanciaBusiness()
	{
		if(isib_segundaInstanciaBusiness == null)
			isib_segundaInstanciaBusiness = new SegundaInstanciaBusiness();

		return isib_segundaInstanciaBusiness;
	}

	/**
	 * Método encargado de generar el documento de comunicado.
	 *
	 * @param aaa_actuacionesAdministrativas Objeto que contiene la información para generar el pdf.
	 * @param apn_personaNotificacion Objeto que contiene la información para generar el pdf con nombre de destinatario.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized byte[] generarComunicadoRechazaRecurso(
	    TagPlantillaResolucion aaa_actuacionesAdministrativas, PersonaNotificacion apn_personaNotificacion,
	    String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if((aaa_actuacionesAdministrativas != null) && StringUtils.isValidString(as_userId))
		{
			try
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                          .findById(
						    NumericUtils.getLongWrapper(aaa_actuacionesAdministrativas.getIdTurnoHistoria())
						);

				if(lth_turnoHistoria != null)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;

					ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if(lt_turno != null)
						{
							byte[] lba_plantilla;
							String ls_idPlantilla;
							String ls_tipoDocumental;

							ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RECHAZO_RECURSO;
							ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
							lba_plantilla         = DaoCreator.getConstantesDAO(adm_manager).findImagenes(
								    ls_idPlantilla
								);

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = ls_idPlantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								Constantes          lc_usuarioFirma;
								DocumentosSalidaDAO lds_DAO;
								ImagenesDAO         li_DAO;
								Map<String, String> lmss_datos;
								String              ls_idCirculo;
								String              ls_idSolicitud;
								String              ls_plantilla;
								String              ls_tag;

								lc_usuarioFirma     = new Constantes();
								lds_DAO             = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								li_DAO              = DaoCreator.getImagenesDAO(adm_manager);
								lmss_datos          = null;
								ls_idCirculo        = lt_turno.getIdCirculo();
								ls_idSolicitud      = lt_turno.getIdSolicitud();
								ls_plantilla        = new String(lba_plantilla);

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

								lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
										                        .findByIdWithImage(lc_usuarioFirma);

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String  ls_idPersona;
										Persona lp_persona;

										ls_idPersona     = ls_solicitud.getIdPersona();
										lp_persona       = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

										if(lp_persona != null)
										{
											String ls_nombreCompleto;

											ls_nombreCompleto = (apn_personaNotificacion != null)
												? apn_personaNotificacion.getDestinatario()
												: lp_persona.getNombreCompleto();

											if(!ab_firma)
											{
												ls_tag = "<TAG_OFICIO>";

												if(ls_plantilla.contains(ls_tag))
												{
													if(ls_plantilla.contains(ls_tag))
													{
														int li_finalTag;

														li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

														if(li_finalTag > 0)
														{
															String ls_textoMitadSuperior;
															String ls_textoMitadInferior;
															String ls_tagNew;

															ls_textoMitadSuperior     = ls_plantilla.substring(
																    0, li_finalTag
																);
															ls_textoMitadInferior     = ls_plantilla.substring(
																    li_finalTag + ls_tag.length()
																);

															ls_tagNew     = "{\\*\\bkmkstart OFICIO}{\\*\\bkmkend OFICIO} \\line "
																+ ls_tag;

															ls_plantilla = ls_textoMitadSuperior
																+ IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
																+ IdentificadoresCommon.ESPACIO_VACIO
																+ ls_textoMitadInferior;
														}
													}
												}

												if(StringUtils.isValidString(ls_idCirculo))
												{
													CirculoRegistral lcr_circuloRegistral;

													lcr_circuloRegistral = new CirculoRegistral();

													lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

													lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(
														    adm_manager
														).findById(lcr_circuloRegistral);

													if(lcr_circuloRegistral != null)
													{
														String ls_nombre;

														ls_nombre = lcr_circuloRegistral.getNombre();

														if(StringUtils.isValidString(ls_nombre))
														{
															ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

															ls_tag = "<TAG_MUN_OFI_ORIGEN>";

															if(ls_plantilla.contains(ls_tag))
																ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
														}

														ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

														if(ls_plantilla.contains(ls_tag))
														{
															String ls_tipoOficina;

															ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

															if(StringUtils.isValidString(ls_tipoOficina))
															{
																if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
																	ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
																else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
																	ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_tipoOficina
																	);
															}
														}

														ls_tag = "<TAG_DIREECCION_CIRCULO_REGISTRAL>";

														if(ls_plantilla.contains(ls_tag))
														{
															String ls_idOficinaOrigen;

															ls_idOficinaOrigen = lcr_circuloRegistral.getIdOficinaOrigen();

															if(StringUtils.isValidString(ls_idOficinaOrigen))
															{
																OficinaOrigen loo_oficina;

																loo_oficina = DaoCreator.getOficinaOrigenDAO(
																	    adm_manager
																	)
																		                    .findById(
																		    ls_idOficinaOrigen,
																		    lcr_circuloRegistral.getVersion()
																		);

																if(loo_oficina != null)
																{
																	String ls_direccion;

																	ls_direccion = loo_oficina.getDireccion();

																	if(StringUtils.isValidString(ls_direccion))
																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag, ls_direccion
																			);
																}
															}
														}
													}
												}

												ls_tag = "<TAG_NOMBRE_INTERESADO>";

												if(ls_plantilla.contains(ls_tag))
												{
													if(StringUtils.isValidString(ls_nombreCompleto))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreCompleto);
												}

												{
													String ls_direccion;
													String ls_correo;
													String ls_telefono;

													ls_direccion     = null;
													ls_correo        = null;
													ls_telefono      = null;

													{
														String ls_medioComunicar;

														ls_medioComunicar = ls_solicitud.getIdAutorizacionComunicacion();

														if(StringUtils.isValidString(ls_medioComunicar))
														{
															if(
															    ls_medioComunicar.equalsIgnoreCase(
																        MedioNotificarCommon.CORREO_ELECTRONICO
																    )
															)
															{
																String ls_idCorreo;

																ls_idCorreo = ls_solicitud
																		.getIdCorreoElectronicoComunicacion();

																if(StringUtils.isValidString(ls_idCorreo))
																{
																	PersonaCorreoElectronico lpc_correo;

																	lpc_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
																		    adm_manager
																		).findById(ls_idPersona, ls_idCorreo);

																	if(lpc_correo != null)
																		ls_correo = lpc_correo.getCorreoElectronico();
																}
															}
															else if(
															    ls_medioComunicar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
																    )
																    || ls_medioComunicar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_RESIDENCIA
																    )
															)
															{
																String ls_idDireccion;

																ls_idDireccion = ls_solicitud.getIdDireccionComunicacion();

																if(StringUtils.isValidString(ls_idDireccion))
																{
																	PersonaDireccion ld_direccion;

																	ld_direccion = DaoCreator.getPersonaDireccionDAO(
																		    adm_manager
																		).findById(ls_idPersona, ls_idDireccion);

																	if(ld_direccion != null)
																		ls_direccion = ld_direccion.getDireccion();
																}
															}
															else if(
															    ls_medioComunicar.equalsIgnoreCase(
																        MedioNotificarCommon.ORIP
																    )
															)
															{
																String ls_idTelefono;

																ls_idTelefono = ls_solicitud.getIdTelefonoComunicacion();

																if(StringUtils.isValidString(ls_idTelefono))
																{
																	PersonaTelefono lpt_telefono;

																	lpt_telefono = DaoCreator.getPersonaTelefonoDAO(
																		    adm_manager
																		).findById(ls_idPersona, ls_idTelefono);

																	if(lpt_telefono != null)
																		ls_telefono = lpt_telefono.getTelefono();
																}
															}
														}
													}

													ls_tag = "<TAG_CORREO_ELECTRONICO>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_correo)
													)
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);

													ls_tag = "<TAG_DIR_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_direccion)
													)
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccion);

													ls_tag = "<TAG_TEL_INTERESADO>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_telefono)
													)
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_telefono);

													ls_tag = "<TAG_TURNO_CORRECCIONES>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

													ls_tag = "<TAG_NIR>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_nir;

														ls_nir = ls_solicitud.getNir();

														if(StringUtils.isValidString(ls_nir))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
													}

													ls_tag = "<TAG_MATRICULAS>";

													if(
													    StringUtils.isValidString(ls_idCirculo)
														    && ls_plantilla.contains(ls_tag)
													)
													{
														Collection<SolicitudMatricula> lcsm_matriculas;

														lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(
															    adm_manager
															).findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

														if(CollectionUtils.isValidCollection(lcsm_matriculas))
														{
															int           li_tam;
															int           li_count;
															StringBuilder lsb_sb;
															String        ls_espacio;

															li_tam         = lcsm_matriculas.size();
															li_count       = 1;
															lsb_sb         = new StringBuilder();
															ls_espacio     = IdentificadoresCommon.SIMBOLO_COMA
																+ IdentificadoresCommon.ESPACIO_VACIO;

															for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
															{
																if(lsm_iterador != null)
																{
																	lsb_sb.append(
																	    lsm_iterador.getIdCirculo()
																	    + IdentificadoresCommon.SIMBOLO_GUION
																	    + lsm_iterador.getIdMatricula()
																	);

																	if(li_count < li_tam)
																		lsb_sb.append(ls_espacio);
																}

																li_count++;
															}

															{
																String ls_s;

																ls_s = lsb_sb.toString();

																if(StringUtils.isValidString(ls_s))
																	ls_plantilla = ls_plantilla.replace(ls_tag, ls_s);
															}
														}
													}

													ls_tag = "<TAG_FECHA_AUTO_PRUEBAS>";

													if(ls_plantilla.contains(ls_tag))
													{
														// TODO Pendiente BookMark
													}

													ls_tag = "<TAG_FECHA_AUTO_ACLARACION>";

													if(ls_plantilla.contains(ls_tag))
													{
														// TODO Pendiente BookMark
													}

													ls_tag = "<TAG_FECHA_AUTO_APERTURA>";

													if(ls_plantilla.contains(ls_tag))
													{
														// TODO Pendiente BookMark
													}

													ls_tag = "<TAG_TERMINO_PRUEBAS>";

													{
														Long ll_termino;

														ll_termino = lt_turno.getTermino();

														if(
														    ls_plantilla.contains(ls_tag)
															    && NumericUtils.isValidLong(ll_termino)
														)
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, StringUtils.getString(ll_termino)
																);
													}

													ls_tag = "<TAG_USUARIO_SUSTANCIADOR>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

													ls_tag = "<TAG_APROBADOR>";

													if(ls_plantilla.contains(ls_tag))
													{
														// TODO Poner BookMark
													}

													{
														String ls_tagUsuarioFirma;

														ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>";

														if(ls_plantilla.contains(ls_tagUsuarioFirma))
															ls_plantilla = getFirmaMasivaBusiness()
																	               .reemplazarUsuarioFirmaCargo(
																	    ls_plantilla, lc_usuarioFirma,
																	    ls_tagUsuarioFirma,
																	    "<TAG_CARGO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>"
																	);
													}

													ls_plantilla     = escribirTagFechaLarga(ls_plantilla);
													lmss_datos       = finalizarPlantilla(
														    ls_plantilla, ll_idTurnoHistoria, adm_manager
														);
													ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
													lba_return       = new PDFGenerator().convertirRTFToPDF(
														    ls_plantilla.getBytes(), adm_manager
														);

													if(lba_return == null)
														throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
												}
											}
											else
											{
												DocumentosSalida lds_documentoSalida;

												lds_documentoSalida = new DocumentosSalida();

												lds_documentoSalida.setIdTurno(ls_idTurno);
												lds_documentoSalida.setTipo(IdentificadoresCommon.COMUNICADO);
												lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
												lds_documentoSalida.setDestinatario(ls_nombreCompleto);
												lds_documentoSalida.setRepositorio(RepositorioCommon.TEMPORAL);

												lds_documentoSalida = lds_DAO.findByTurnoTipoEstadoDestinatario(
													    lds_documentoSalida
													);

												if(lds_documentoSalida != null)
												{
													Imagenes li_imagen;

													li_imagen = li_DAO.findById(
														    NumericUtils.getLong(lds_documentoSalida.getIdImagen())
														);

													if(li_imagen != null)
													{
														lmss_datos     = new HashMap<String, String>();
														lba_return     = li_imagen.getImagenBlob();

														lmss_datos.put(
														    IdentificadoresCommon.CODIGO_VERIFICACION,
														    li_imagen.getCodigoVerificacion()
														);

														if(lba_return != null)
														{
															byte[] lba_grafo;
															int    li_incrX;
															int    li_incrY;

															lba_grafo     = null;
															li_incrX      = 0;
															li_incrY      = 0;

															if(lc_usuarioFirma != null)
															{
																lba_grafo     = lc_usuarioFirma.getImagenes();
																li_incrX      = NumericUtils.getInt(
																	    lc_usuarioFirma.getEntero()
																	);
																li_incrY      = NumericUtils.getInt(
																	    lc_usuarioFirma.getReal()
																	);
															}

															lba_return     = getFirmaMasivaBusiness()
																	                 .reemplazarBookmarsFirma(
																	    lba_return, lba_grafo, li_incrX, li_incrY
																	);
															lba_return     = MarcaAgua.estamparMarcaDeAgua(
																    lba_return, lt_turno.getExpediente(), 100, 40
																);
														}
													}
												}
											}

											if(ab_salvar)
											{
												Imagenes li_imagenes;
												long     ll_idImagenTemp;

												li_imagenes = new Imagenes();

												li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												li_imagenes.setIdUsuarioCreacion(as_userId);
												li_imagenes.setIpCreacion(as_remoteIp);
												li_imagenes.setImagenBlob(lba_return);
												li_imagenes.setCodigoVerificacion(
												    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
												);

												ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

												if(ll_idImagenTemp > 0)
												{
													DocumentosSalida lds_documentoSalida;
													Long             ll_idImagen;

													lds_documentoSalida     = new DocumentosSalida();
													ll_idImagen             = NumericUtils.getLongWrapper(
														    ll_idImagenTemp
														);

													lds_documentoSalida.setIdTurno(ls_idTurno);
													lds_documentoSalida.setTipo(
													    IdentificadoresCommon.COMUNICADO_RECHAZO_RECURSOS
													);
													lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
													lds_documentoSalida.setDestinatario(ls_nombreCompleto);
													lds_documentoSalida.setRepositorio(
													    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
													);

													lds_documentoSalida = lds_DAO.findByTurnoTipoEstadoDestinatario(
														    lds_documentoSalida
														);

													{
														boolean lb_insertar;

														lb_insertar = lds_documentoSalida == null;

														if(lb_insertar)
														{
															lds_documentoSalida = new DocumentosSalida();

															lds_documentoSalida.setIdUsuarioCreacion(as_userId);
															lds_documentoSalida.setIpCreacion(as_remoteIp);
														}
														else
														{
															lds_documentoSalida.setIdUsuarioModificacion(as_userId);
															lds_documentoSalida.setIpModificacion(as_remoteIp);
														}

														lds_documentoSalida.setIdTurnoHistoria(
														    NumericUtils.getInteger(ll_idTurnoHistoria)
														);
														lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
														lds_documentoSalida.setIdTurno(ls_idTurno);
														lds_documentoSalida.setIdImagen(ll_idImagen);
														lds_documentoSalida.setDestinatario(ls_nombreCompleto);
														lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
														lds_documentoSalida.setIdTipoDocumental(ls_tipoDocumental);
														lds_documentoSalida.setTipo(
														    IdentificadoresCommon.COMUNICADO_RECHAZO_RECURSOS
														);
														lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
														lds_documentoSalida.setDocumentoAutomatico(EstadoCommon.S);
														lds_documentoSalida.setRepositorio(
														    ab_firma ? RepositorioCommon.FINAL
														             : RepositorioCommon.TEMPORAL
														);
														lds_documentoSalida.setDocumentoFinal(EstadoCommon.S);

														lds_DAO.insertOrUpdate(lds_documentoSalida, lb_insertar);
													}
												}
												else
													throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
											}
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
				clh_LOGGER.error("generarDocumentoAutoResolucion", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar el documento de comunicado.
	 *
	 * @param aot_oficioTextoData Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @param apn_personaNotificacion Objeto que contiene la información de la persona a la cual va dirigido el comunicado.
	 * @param ab_salvar Variable booleana que indica si se debe salvar el documento y la información.
	 * @param ab_firma Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private synchronized byte[] generarDocumentoComunicado(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo,
	    PersonaNotificacion apn_personaNotificacion, boolean ab_esSegundaInstancia, boolean ab_salvar, boolean ab_firma,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if((aot_oficioTextoData != null) && StringUtils.isValidString(as_userId))
		{
			try
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lthd_thDAO;
				lthd_thDAO     = DaoCreator.getTurnoHistoriaDAO(adm_manager);

				lth_turnoHistoria = lthd_thDAO.findById(aot_oficioTextoData.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					Long   ll_idTurnoHistoria;
					String ls_idTurno;

					ll_idTurnoHistoria     = lth_turnoHistoria.getIdTurnoHistoria();
					ls_idTurno             = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno) && NumericUtils.isValidLong(ll_idTurnoHistoria))
					{
						Turno lt_turno;

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);

						if((lt_turno != null) && (al_motivo > 0L))
						{
							byte[]                 lba_plantilla;
							TagPlantillaResolucion laa_actuacionesAdministrativas;
							String                 ls_idPlantilla;
							String                 ls_tipoArchivo;
							String                 ls_tipoDocumental;

							if(ab_esSegundaInstancia)
								laa_actuacionesAdministrativas = getSegundaInstanciaBusiness()
										                                 .plantillaDatosPorIdMotivoSegundaInstancia(
										    adm_manager, al_motivo, false, true
										);
							else
								laa_actuacionesAdministrativas = plantillaDatosPorIdMotivo(
									    adm_manager, al_motivo, false
									);

							if(laa_actuacionesAdministrativas == null)
							{
								Object[] loa_messageArgs = new String[1];

								loa_messageArgs[0] = StringUtils.getString(al_motivo);

								throw new B2BException(
								    ErrorKeys.ERROR_NO_SE_ENCONTRARON_DATOS_ID_MOTIVO, loa_messageArgs
								);
							}

							ls_idPlantilla        = laa_actuacionesAdministrativas.getIdPlantilla();
							ls_tipoArchivo        = laa_actuacionesAdministrativas.getTipoArchivo();
							ls_tipoDocumental     = laa_actuacionesAdministrativas.getTipoDocumental();
							lba_plantilla         = laa_actuacionesAdministrativas.getArchivo();

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = ls_idPlantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								DocumentosSalidaDAO lds_DAO;
								ImagenesDAO         li_DAO;

								String ls_idCirculo;
								String ls_idSolicitud;
								String ls_plantilla;
								String ls_tag;
								String ls_tag2;
								String ls_correo;

								lds_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								li_DAO      = DaoCreator.getImagenesDAO(adm_manager);

								ls_idCirculo       = lt_turno.getIdCirculo();
								ls_idSolicitud     = lt_turno.getIdSolicitud();
								ls_plantilla       = new String(lba_plantilla);
								ls_correo          = null;

								String ls_consecutivoOficio;
								String ls_consecutivoResolucion;
								String ls_referenciaSalida;
								String ls_expediente;
								Long   ll_consecutivoResolucion;
								Date   ld_dateResol;
								Date   ld_dateOficio;

								ls_consecutivoOficio = null;
								ls_consecutivoResolucion = null;
								ls_referenciaSalida = null;
								ld_dateResol       = null;
								ll_consecutivoResolucion = null;
								ls_expediente      = lt_turno.getExpediente();
								ld_dateOficio      = new Date();

								if(ab_firma)
								{
									if(!StringUtils.isValidString(ls_expediente))
										ls_expediente = aot_oficioTextoData.getExpediente();

									ls_tag = "<TAG_EXPEDIENTE>";

									if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_expediente))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_expediente);

									ls_tag = "<TAG_OFICIO>";

									if(ls_plantilla.contains(ls_tag))
									{
										NumeracionOficiosCirculo lnoc_numeracionOficios;

										lnoc_numeracionOficios = findNumeracionOficiosCirculo(
											    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
											);

										if(lnoc_numeracionOficios != null)
										{
											ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

											if(StringUtils.isValidString(ls_consecutivoOficio))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoOficio);
										}
									}

									ls_tag = "<TAG_REFERENCIA_SALIDA>";

									if(ls_plantilla.contains(ls_tag))
									{
										ls_referenciaSalida = generarIdCorrespondencia(
											    lth_turnoHistoria, adm_manager, false
											);

										if(StringUtils.isValidString(ls_referenciaSalida))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_referenciaSalida);
									}

									ls_tag = "<TAG_RESOL>";

									if(ls_plantilla.contains(ls_tag))
									{
										DocumentosSalida ldsr_documentosSalidaResolucion;

										ldsr_documentosSalidaResolucion = DaoCreator.getDocumentosSalidaDAO(
											    adm_manager
											)
												                                        .findByIdTurnoHistoriaNoComunicado(
												    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
												);

										if(ldsr_documentosSalidaResolucion != null)
										{
											ll_consecutivoResolucion     = ldsr_documentosSalidaResolucion
													.getConsecutivoResolucion();
											ls_consecutivoResolucion     = String.format(
												    "%06d", ll_consecutivoResolucion
												);

											if(StringUtils.isValidString(ls_consecutivoResolucion))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_consecutivoResolucion);
										}
									}

									ls_tag = "<TAG_FECHA_RESOL>";

									if(ls_plantilla.contains(ls_tag))
									{
										String           ls_fechaResol;
										SimpleDateFormat formatter;

										ld_dateResol     = new Date();
										formatter        = new SimpleDateFormat("dd/MM/yyyy");

										if(ld_dateResol != null)
										{
											ls_fechaResol = StringUtils.getStringNotNull(
												    formatter.format(ld_dateResol)
												);

											if(StringUtils.isValidString(ls_fechaResol))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaResol);
										}
									}
								}

								ls_tag      = "<TAG_NUMERO_RESOLUCION_DECISION>";
								ls_tag2     = "<TAG_FECHA_RESOLUCION_DECISION>";

								if(ls_plantilla.contains(ls_tag) || ls_plantilla.contains(ls_tag2))
								{
									DocumentosSalida lds_temp;

									lds_temp = lds_DAO.findByIdTurnoTipo(
										    ls_idTurno, TipoArchivoCommon.RESOLUCION_DECISION
										);

									if(lds_temp != null)
									{
										if(ls_plantilla.contains(ls_tag))
										{
											Long ll_consecutivo;

											ll_consecutivo = lds_temp.getConsecutivoResolucion();

											if(NumericUtils.isValidLong(ll_consecutivo))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, String.format("%06d", ll_consecutivo)
													);
										}

										if(ls_plantilla.contains(ls_tag2))
										{
											Date ld_fechaResolucion;

											ld_fechaResolucion = lds_temp.getFechaResolucion();

											if(ld_fechaResolucion != null)
											{
												SimpleDateFormat formatter;

												formatter        = new SimpleDateFormat("dd/MM/yyyy");
												ls_plantilla     = ls_plantilla.replace(
													    ls_tag,
													    StringUtils.getStringNotNull(
													        formatter.format(ld_fechaResolucion)
													    )
													);
											}
										}
									}
								}

								if(StringUtils.isValidString(ls_idCirculo))
								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = new CirculoRegistral();

									lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(lcr_circuloRegistral);

									if(lcr_circuloRegistral != null)
									{
										String ls_nombre;

										ls_nombre = lcr_circuloRegistral.getNombre();

										if(StringUtils.isValidString(ls_nombre))
										{
											ls_tag = "<TAG_NOMBRE_CIRCULO_REGISTRAL>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);

											ls_tag = "<TAG_MUN_OFI_ORIGEN>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
										}

										ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tipoOficina;

											ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

											if(StringUtils.isValidString(ls_tipoOficina))
											{
												if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
													ls_tipoOficina = IdentificadoresCommon.PRINCIPAL;
												else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
													ls_tipoOficina = IdentificadoresCommon.SECCIONAL;

												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
											}
										}

										ls_tag = "<TAG_DIREECCION_CIRCULO_REGISTRAL>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_idOficinaOrigen;

											ls_idOficinaOrigen = lcr_circuloRegistral.getIdOficinaOrigen();

											if(StringUtils.isValidString(ls_idOficinaOrigen))
											{
												OficinaOrigen loo_oficina;

												loo_oficina = DaoCreator.getOficinaOrigenDAO(adm_manager)
														                    .findById(
														    ls_idOficinaOrigen, lcr_circuloRegistral.getVersion()
														);

												if(loo_oficina != null)
												{
													String ls_direccion;

													ls_direccion = loo_oficina.getDireccion();

													if(StringUtils.isValidString(ls_direccion))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccion);
												}
											}
										}
									}
								}

								if(apn_personaNotificacion != null)
								{
									ls_tag = "<TAG_NOMBRE_INTERESADO>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_nombre;

										ls_nombre = apn_personaNotificacion.getDestinatario();

										if(StringUtils.isValidString(ls_nombre))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
									}

									{
										String ls_idPersona;
										String ls_direccion;

										ls_idPersona     = apn_personaNotificacion.getIdPersona();
										ls_direccion     = null;

										if(StringUtils.isValidString(ls_idPersona))
										{
											boolean lb_medioComunicar;
											boolean lb_medioNotificar;
											String  ls_medioComunicar;
											String  ls_medioNotificar;

											ls_medioComunicar     = apn_personaNotificacion
													.getIdAutorizacionComunicacion();
											ls_medioNotificar     = apn_personaNotificacion
													.getIdAutorizacionNotificacion();

											lb_medioComunicar     = StringUtils.isValidString(ls_medioComunicar);
											lb_medioNotificar     = StringUtils.isValidString(ls_medioNotificar);

											if(lb_medioNotificar || lb_medioComunicar)
											{
												if(
												    ((lb_medioNotificar
													    && ls_medioNotificar.equalsIgnoreCase(
													        MedioNotificarCommon.CORREO_ELECTRONICO
													    ))
													    || ls_medioNotificar.equalsIgnoreCase(
													        MedioNotificarCommon.ORIP
													    ))
													    || ((lb_medioComunicar
													    && ls_medioComunicar.equalsIgnoreCase(
													        MedioNotificarCommon.CORREO_ELECTRONICO
													    ))
													    || ls_medioComunicar.equalsIgnoreCase(
													        MedioNotificarCommon.ORIP
													    ))
												)
												{
													String ls_idCorreo;

													ls_idCorreo = apn_personaNotificacion.getIdCorreoElectronico();

													if(StringUtils.isValidString(ls_idCorreo))
													{
														PersonaCorreoElectronico lpc_correo;

														lpc_correo = DaoCreator.getPersonaCorreoElectronicoDAO(
															    adm_manager
															).findById(ls_idPersona, ls_idCorreo);

														if(lpc_correo != null)
															ls_correo = lpc_correo.getCorreoElectronico();
													}
												}
												else if(
												    ls_medioNotificar.equalsIgnoreCase(
													        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
													    )
													    || ls_medioNotificar.equalsIgnoreCase(
													        MedioNotificarCommon.DIRECCION_RESIDENCIA
													    )
												)
												{
													String ls_idDireccion;

													ls_idDireccion = apn_personaNotificacion.getIdDireccion();

													if(StringUtils.isValidString(ls_idDireccion))
													{
														PersonaDireccion ld_direccion;

														ld_direccion = DaoCreator.getPersonaDireccionDAO(adm_manager)
																                     .findById(
																    ls_idPersona, ls_idDireccion
																);

														if(ld_direccion != null)
															ls_direccion = ld_direccion.getDireccion();
													}
												}
											}
										}
										else
											ls_direccion = apn_personaNotificacion.getDireccion();

										ls_tag = "<TAG_CORREO_ELECTRONICO>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_correo))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);

										ls_tag = "<TAG_DIR_INTERESADO>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_direccion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_direccion);
									}

									ls_tag = "<TAG_DEPAR_INTERESADO>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_idDepartamento;
										String ls_idPais;

										ls_idDepartamento     = apn_personaNotificacion.getIdDepartamento();
										ls_idPais             = apn_personaNotificacion.getIdPais();

										if(
										    StringUtils.isValidString(ls_idDepartamento)
											    && StringUtils.isValidString(ls_idPais)
										)
										{
											Departamento lp_departamento;

											lp_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
													                        .findById(ls_idPais, ls_idDepartamento);

											if(lp_departamento != null)
											{
												String ls_nombreDepartamento;

												ls_nombreDepartamento     = lp_departamento.getNombre();
												ls_tag                    = "<TAG_MUNICIPIO_INTERESADO>";

												if(StringUtils.isValidString(ls_nombreDepartamento))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreDepartamento);

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idMunicipio;

													ls_idMunicipio = apn_personaNotificacion.getIdMunicipio();

													if(StringUtils.isValidString(ls_idMunicipio))
													{
														Municipio lm_municipio;

														lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																                     .findById(
																    ls_idPais, ls_idDepartamento, ls_idMunicipio
																);

														if(lm_municipio != null)
														{
															String ls_nombreMunicipio;

															ls_nombreMunicipio = lm_municipio.getNombre();

															if(StringUtils.isValidString(ls_nombreMunicipio))
																ls_plantilla = ls_plantilla.replace(
																	    ls_tag, ls_nombreMunicipio
																	);
														}
													}
												}
											}
										}
									}
								}

								ls_tag = "<TAG_TURNO_CORRECCIONES>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);

								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										ls_tag = "<TAG_NIR>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_nir;

											ls_nir = ls_solicitud.getNir();

											if(StringUtils.isValidString(ls_nir))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
										}

										ls_tag = "<TAG_MATRICULAS>";

										if(StringUtils.isValidString(ls_idCirculo) && ls_plantilla.contains(ls_tag))
										{
											Collection<SolicitudMatricula> lcsm_matriculas;

											lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
													                        .findByIdSolicitudCirculo(
													    ls_idSolicitud, ls_idCirculo
													);

											if(CollectionUtils.isValidCollection(lcsm_matriculas))
											{
												int           li_tam;
												int           li_count;
												StringBuilder lsb_sb;
												String        ls_espacio;

												li_tam         = lcsm_matriculas.size();
												li_count       = 1;
												lsb_sb         = new StringBuilder();
												ls_espacio     = IdentificadoresCommon.SIMBOLO_COMA
													+ IdentificadoresCommon.ESPACIO_VACIO;

												for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
												{
													if(lsm_iterador != null)
													{
														lsb_sb.append(
														    lsm_iterador.getIdCirculo()
														    + IdentificadoresCommon.SIMBOLO_GUION
														    + lsm_iterador.getIdMatricula()
														);

														if(li_count < li_tam)
															lsb_sb.append(ls_espacio);
													}

													li_count++;
												}

												{
													String ls_s;

													ls_s = lsb_sb.toString();

													if(StringUtils.isValidString(ls_s))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_s);
												}
											}
										}
									}
								}

								ls_tag = "<TAG_TERMINO_PRUEBAS>";

								{
									Long ll_termino;

									ll_termino = lt_turno.getTermino();

									if(ls_plantilla.contains(ls_tag) && NumericUtils.isValidLong(ll_termino))
										ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ll_termino));
								}

								ls_tag = "<TAG_USUARIO_SUSTANCIADOR>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

								ls_tag = "<TAG_APROBADOR>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

								{
									Constantes          lc_usuarioFirma;
									Map<String, String> lmss_datos;
									boolean             lb_contieneDatos;

									lc_usuarioFirma      = new Constantes();
									lmss_datos           = null;
									lb_contieneDatos     = false;

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
											                        .findByIdWithImage(lc_usuarioFirma);

									if(lc_usuarioFirma != null)
									{
										String ls_tagUsuarioFirma;

										ls_tagUsuarioFirma = "<TAG_USUARIO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>";

										if(ls_plantilla.contains(ls_tagUsuarioFirma))
											ls_plantilla = getFirmaMasivaBusiness()
													               .reemplazarUsuarioFirmaCargo(
													    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
													    "<TAG_CARGO_FIRMA_ACTUACIONES_ADMINISTRATIVAS>"
													);

										ls_plantilla     = escribirTagFechaLarga(ls_plantilla);
										lmss_datos       = finalizarPlantilla(
											    ls_plantilla, ll_idTurnoHistoria, adm_manager
											);

										lb_contieneDatos     = lmss_datos != null;
										ls_plantilla         = lb_contieneDatos
											? lmss_datos.get(IdentificadoresCommon.PLANTILLA) : null;

										if(StringUtils.isValidString(ls_plantilla))
											lba_return = new PDFGenerator().convertirRTFToPDF(
												    ls_plantilla.getBytes(), adm_manager
												);

										if(lba_return == null)
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
										else if(ab_firma)
										{
											int li_incrX;
											int li_incrY;

											li_incrX     = 0;
											li_incrY     = 0;

											li_incrX     = NumericUtils.getInt(lc_usuarioFirma.getEntero());
											li_incrY     = NumericUtils.getInt(lc_usuarioFirma.getReal());

											lba_return = getFirmaMasivaBusiness()
													             .reemplazarBookmarsFirma(
													    lba_return, lc_usuarioFirma.getImagenes(), li_incrX, li_incrY
													);
										}
									}

									if(ab_salvar)
									{
										Imagenes li_imagenes;
										long     ll_idImagenTemp;

										li_imagenes = new Imagenes();

										li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
										li_imagenes.setIdUsuarioCreacion(as_userId);
										li_imagenes.setIpCreacion(as_remoteIp);
										li_imagenes.setImagenBlob(lba_return);
										li_imagenes.setCodigoVerificacion(
										    lb_contieneDatos
										    ? lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION) : null
										);

										ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

										if(ll_idImagenTemp > 0)
										{
											DocumentosSalida lds_documentoSalida;
											Long             ll_idImagen;

											lds_documentoSalida     = new DocumentosSalida();
											ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

											lds_documentoSalida.setIdTurnoHistoria(
											    NumericUtils.getInteger(ll_idTurnoHistoria)
											);
											lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
											lds_documentoSalida.setIdTurno(ls_idTurno);
											lds_documentoSalida.setIdImagen(ll_idImagen);
											lds_documentoSalida.setDestinatario(
											    apn_personaNotificacion.getDestinatario()
											);
											lds_documentoSalida.setDireccion(apn_personaNotificacion.getDireccion());
											lds_documentoSalida.setCorreoElectronico(ls_correo);
											lds_documentoSalida.setIdPais(apn_personaNotificacion.getIdPais());
											lds_documentoSalida.setIdDepartamento(
											    apn_personaNotificacion.getIdDepartamento()
											);
											lds_documentoSalida.setIdMunicipio(
											    apn_personaNotificacion.getIdMunicipio()
											);
											lds_documentoSalida.setIdPersonaNotificacion(
											    apn_personaNotificacion.getIdPersonaNotificacion()
											);
											lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
											lds_documentoSalida.setIdTipoDocumental(ls_tipoDocumental);

											{
												String ls_terceroIndeterminado;

												ls_terceroIndeterminado = StringUtils.getStringNotNull(
													    apn_personaNotificacion.getTerceroIndeterminado()
													);

												lds_documentoSalida.setTipo(
												    ls_terceroIndeterminado.equalsIgnoreCase(EstadoCommon.S)
												    ? TipoArchivoCommon.COMUNICADO_INDETERMINADO : ls_tipoArchivo
												);
											}

											lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);
											lds_documentoSalida.setDocumentoAutomatico(EstadoCommon.S);
											lds_documentoSalida.setConsecutivoResolucion(ll_consecutivoResolucion);
											lds_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
											lds_documentoSalida.setFechaResolucion(ld_dateResol);
											lds_documentoSalida.setFechaOficio(ld_dateOficio);
											lds_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setDocumentoFinal(EstadoCommon.S);

											lds_DAO.insertOrUpdate(lds_documentoSalida, true);
										}
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
									}
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarDocumentoAutoResolucion", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de llenar oficios texto con textos precargados.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los datos a mostrar en la plantilla.
	 * @param amss_data Argumento de tipo <code>Map<String, String></code> que contiene los textos a mostrar en la plantilla.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private synchronized void llenarOficiosTextoRecursos(
	    OficiosTexto aot_oficiosTexto, Map<String, String> amss_data, Turno at_turno, long al_idTurnoHistoria,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(amss_data) && (aot_oficiosTexto != null))
		{
			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setPertinencia(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_RESUELVE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setResuelve(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_ANTECEDENTES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setAntecedentes(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_DESPACHO_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setConsideracion(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_DISPONE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setRazon1(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_PERTINENCIA_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setPertinencia(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_ARGUMENTOS_DEL_RECURRENTE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setArgumentosRecurrente(
					    llenarTagsTextoRecursos(amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager)
					);
			}
		}
	}

	/**
	 * Método encargado de llenar oficios texto con textos precargados.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los datos a mostrar en la plantilla.
	 * @param amss_data Argumento de tipo <code>Map<String, String></code> que contiene los textos a mostrar en la plantilla.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private synchronized void llenarOficiosTextoTraslados(OficiosTexto aot_oficiosTexto, Map<String, String> amss_data)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(amss_data) && (aot_oficiosTexto != null))
		{
			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setConsideracion(amss_data.get(ls_tag));
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_RESUELVE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setResuelve(amss_data.get(ls_tag));
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_ANTECEDENTES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setAntecedentes(amss_data.get(ls_tag));
			}
		}
	}

	/**
	 * Método encargado de llenar los tags de los textos cargados en pantalla.
	 *
	 * @param as_texto Variable que contiene el texto.
	 * @param at_turno Objeto que contiene el turno del proceso.
	 * @param al_idMotivo Variable que contiene el motivo o decisión del proceso.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @param al_idEtapa Variable que contiene la etapa a consultar.
	 * @return Texto resultado al llenar los tags.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized String llenarTagsTexto(
	    String as_texto, Turno at_turno, long al_idMotivo, DAOManager adm_manager, Long al_idEtapa
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_texto) && (at_turno != null))
			{
				String ls_espacio;
				String ls_idCirculo;
				String ls_tag;

				ls_espacio       = IdentificadoresCommon.SIMBOLO_COMA + IdentificadoresCommon.ESPACIO_VACIO;
				ls_idCirculo     = at_turno.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ls_tag = "<TAG_NOMBRE_ORIP>";

					if(as_texto.contains(ls_tag))
					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

						if(lcr_circuloRegistral != null)
						{
							String ls_nombre;

							ls_nombre = lcr_circuloRegistral.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								as_texto = as_texto.replace(ls_tag, ls_nombre);
						}
					}

					ls_tag = "<TAG_MATRICULAS>";

					if(as_texto.contains(ls_tag))
					{
						String ls_idSolicitud;

						ls_idSolicitud = at_turno.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Collection<SolicitudMatricula> lcsm_matriculas;

							lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
									                        .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

							if(CollectionUtils.isValidCollection(lcsm_matriculas))
							{
								int           li_tam;
								int           li_count;
								StringBuilder lsb_sb;

								li_tam       = lcsm_matriculas.size();
								li_count     = 1;
								lsb_sb       = new StringBuilder();

								for(SolicitudMatricula lsm_iterador : lcsm_matriculas)
								{
									if(lsm_iterador != null)
									{
										lsb_sb.append(
										    lsm_iterador.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
										    + lsm_iterador.getIdMatricula()
										);

										if(li_count < li_tam)
											lsb_sb.append(ls_espacio);
									}

									li_count++;
								}

								if(StringUtils.isValidString(lsb_sb.toString()))
									as_texto = as_texto.replace(ls_tag, lsb_sb.toString());
							}
						}
					}
				}

				ls_tag = "<TAG_DESTINATARIOS>";

				if(as_texto.contains(ls_tag))
				{
					Collection<PersonaNotificacion> lcpn_personas;

					lcpn_personas = DaoCreator.getPersonaNotificacionDAO(adm_manager)
							                      .findByIdTurnoDecision(
							    at_turno.getIdTurno(), al_idMotivo, al_idEtapa
							);

					if(CollectionUtils.isValidCollection(lcpn_personas))
					{
						int           li_tam;
						int           li_count;
						StringBuilder lsb_sb;

						li_tam       = lcpn_personas.size();
						li_count     = 1;
						lsb_sb       = new StringBuilder();

						for(PersonaNotificacion lpn_iterador : lcpn_personas)
						{
							if(lpn_iterador != null)
							{
								lsb_sb.append(lpn_iterador.getDestinatario());

								if(li_count < li_tam)
									lsb_sb.append(ls_espacio);
							}

							li_count++;
						}

						if(StringUtils.isValidString(lsb_sb.toString()))
							as_texto = as_texto.replace(ls_tag, lsb_sb.toString());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarTagsTexto", lb2be_e);

			throw lb2be_e;
		}

		return limpiarTags(as_texto);
	}

	/**
	 * Método encargado de llenar los tags de los textos cargados en pantalla.
	 *
	 * @param as_texto Variable que contiene el texto.
	 * @param at_turno Objeto que contiene el turno del proceso.
	 * @param al_idTurnoHistoria que contiene el id turno historia dle proceso.
	 * @param adm_manager DAOManager que controla las transacciones.
	 * @return Texto resultado al llenar los tags.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized String llenarTagsTextoRecursos(
	    String as_texto, Turno at_turno, long al_idTurnoHistoria, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_texto) && (at_turno != null))
			{
				String ls_idCirculo;
				String ls_tag;

				ls_idCirculo = at_turno.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ls_tag = "<TAG_NOMBRE_ORIP>";

					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

					if(as_texto.contains(ls_tag))
					{
						if(lcr_circuloRegistral != null)
						{
							String ls_nombre;

							ls_nombre = lcr_circuloRegistral.getNombre();

							if(StringUtils.isValidString(ls_nombre))
								as_texto = as_texto.replace(ls_tag, ls_nombre);
						}
					}

					if(lcr_circuloRegistral != null)
					{
						String     ls_idOficinaOrigen;
						BigDecimal lbd_version;

						ls_idOficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
						lbd_version            = lcr_circuloRegistral.getVersion();

						ls_tag = "<TAG_ID_OFI_ORIGEN>";

						if(
						    as_texto.contains(ls_tag) && StringUtils.isValidString(ls_idOficinaOrigen)
							    && (lbd_version != null)
						)
						{
							OficinaOrigen loo_oficinaOrigen;
							String        ls_nombreOficinaOrigen;

							loo_oficinaOrigen = new OficinaOrigen();
							loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
							loo_oficinaOrigen.setVersion(lbd_version);

							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager).findById(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								ls_nombreOficinaOrigen = loo_oficinaOrigen.getNombre();

								if(StringUtils.isValidString(ls_nombreOficinaOrigen))
									as_texto = as_texto.replace(ls_tag, ls_nombreOficinaOrigen);
							}
						}
					}

					String ls_idTurno;

					ls_idTurno     = at_turno.getIdTurno();
					ls_tag         = "<TAG_ACC_TUR_ID_TURNO>";

					if(as_texto.contains(ls_tag) && StringUtils.isValidString(ls_idTurno))
						as_texto = as_texto.replace(ls_tag, ls_idTurno);

					ls_tag = "<TAG_TURNO>";

					if(as_texto.contains(ls_tag) && StringUtils.isValidString(ls_idTurno))
						as_texto = as_texto.replace(ls_tag, ls_idTurno);

					String ls_tag2;
					String ls_tag3;
					String ls_idSolicitud;

					ls_idSolicitud     = at_turno.getIdSolicitud();

					ls_tag      = "<TAG_BQN_DOC_ID_TIPO_DOC>";
					ls_tag2     = "<TAG_BQN_DOC_NUMERO>";
					ls_tag3     = "<TAG_BQN_DOC_FECHA>";

					if(
					    (as_texto.contains(ls_tag) || as_texto.contains(ls_tag2) || as_texto.contains(ls_tag3))
						    && StringUtils.isValidString(ls_idSolicitud)
					)
					{
						Solicitud ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							String ls_idDocumento;

							ls_idDocumento = ls_solicitud.getIdDocumento();

							if(StringUtils.isValidString(ls_idDocumento))
							{
								Documento ld_documento;

								ld_documento = new Documento();

								ld_documento.setIdDocumento(ls_idDocumento);

								ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

								if(ld_documento != null)
								{
									Date   ld_fechaDocumento;
									String ls_tipoDocumento;
									String ls_numero;

									ld_fechaDocumento     = ld_documento.getFechaDocumento();
									ls_tipoDocumento      = ld_documento.getIdTipoDocumento();
									ls_numero             = ld_documento.getNumero();

									if(ld_fechaDocumento != null)
										as_texto = escribirTagFechaLarga(as_texto, ls_tag3, ld_fechaDocumento);

									if(as_texto.contains(ls_tag2) && StringUtils.isValidString(ls_numero))
										as_texto = as_texto.replace(ls_tag2, ls_numero);

									if(StringUtils.isValidString(ls_tipoDocumento))
									{
										TipoDocumentoPublico ltdp_tipoDocumento;

										ltdp_tipoDocumento = new TipoDocumentoPublico();

										ltdp_tipoDocumento.setIdTipoDocumento(ls_tipoDocumento);

										ltdp_tipoDocumento = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
												                           .findById(ltdp_tipoDocumento);

										if(ltdp_tipoDocumento != null)
										{
											String ls_nombre;

											ls_nombre = ltdp_tipoDocumento.getNombre();

											if(as_texto.contains(ls_tag) && StringUtils.isValidString(ls_nombre))
												as_texto = as_texto.replace(ls_tag, ls_nombre);
										}
									}
								}
							}
						}
					}

					ls_tag = "<TAG_ACTO>";

					if(as_texto.contains(ls_tag))
					{
						Collection<Acto> lca_actos;
						String           ls_nombresActos;

						lca_actos           = new ArrayList<Acto>();
						ls_nombresActos     = null;
						lca_actos           = DaoCreator.getActoDAO(adm_manager)
								                            .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

						if(CollectionUtils.isValidCollection(lca_actos))
						{
							Acto la_acto;

							la_acto = lca_actos.iterator().next();

							if(la_acto != null)
							{
								TipoActo lta_tipoActo;

								lta_tipoActo = DaoCreator.getTipoActoDAO(adm_manager).findById(la_acto.getIdTipoActo());

								if(lta_tipoActo != null)
								{
									String ls_nombreActo;

									ls_nombreActo = lta_tipoActo.getNombre();

									if(!StringUtils.isValidString(ls_nombresActos))
										ls_nombresActos = ls_nombreActo;
									else
										ls_nombresActos = ls_nombresActos + IdentificadoresCommon.SIMBOLO_COMA
											+ ls_nombreActo;
								}
							}
						}

						if(StringUtils.isValidString(ls_nombresActos))
							as_texto = as_texto.replace(ls_tag, ls_nombresActos);
					}

					ls_tag     = "<TAG_ID_SOLICITUD_MATRICULA>";

					as_texto     = resolverTagMatricula(
						    adm_manager, as_texto, ls_tag, ls_idSolicitud, ls_idCirculo, false
						);

					ls_tag = TagCommon.TAG_FECHA_NOTA_DEVOLUTIVA;

					if(as_texto.contains(ls_tag))
					{
						Collection<DocumentosSalida> lcds_documentoSalida;

						lcds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
								                             .findByIdTurnoHistoriaTipo(
								    NumericUtils.getInteger(al_idTurnoHistoria), TipoArchivoCommon.NOTA_DEVOLUTIVA
								);

						if(CollectionUtils.isValidCollection(lcds_documentoSalida))
						{
							DocumentosSalida lds_documentoSalida;

							lds_documentoSalida = lcds_documentoSalida.iterator().next();

							if(lds_documentoSalida != null)
								as_texto = reemplazarTagEnPlantilla(
									    as_texto, ls_tag, lds_documentoSalida.getFechaCreacion()
									);
						}
					}

					ls_tag = "<TAG_CAUSAL_DEVOLUCION_NOTA_DEVOLUTIVA>";

					if(as_texto.contains(ls_tag))
					{
						Collection<TipoCausal> lctc_tipoCausal;

						lctc_tipoCausal = DaoCreator.getNotaDevolutivaDAO(adm_manager).findCausalesByTurno(ls_idTurno);

						if(CollectionUtils.isValidCollection(lctc_tipoCausal))
						{
							StringBuilder lsb_sb;

							lsb_sb = new StringBuilder();

							for(TipoCausal ltc_iterador : lctc_tipoCausal)
							{
								if(ltc_iterador != null)
								{
									lsb_sb.append(ltc_iterador.getNombre());
									lsb_sb.append(IdentificadoresCommon.SALTO_LINEA_RTF);
								}
							}

							as_texto = as_texto.replace(ls_tag, lsb_sb.toString());
						}
					}

					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

					if(ls_solicitud != null)
					{
						ls_tag = "<TAG_NIR>";

						if(as_texto.contains(ls_tag))
						{
							String ls_nir;

							ls_nir = ls_solicitud.getNir();

							if(StringUtils.isValidString(ls_nir))
								as_texto = as_texto.replace(ls_tag, ls_nir);
						}

						ls_tag = "<TAG_FECHA_CORTA>";

						if(as_texto.contains(ls_tag))
						{
							Date             ld_dateSolicitud;
							String           ls_fechaResol;
							SimpleDateFormat formatter;

							ld_dateSolicitud     = ls_solicitud.getFechaCreacion();
							formatter            = new SimpleDateFormat("dd/MM/yyyy");

							if(ld_dateSolicitud != null)
							{
								ls_fechaResol     = StringUtils.getStringNotNull(formatter.format(ld_dateSolicitud));

								as_texto = as_texto.replace(ls_tag, ls_fechaResol);
							}
						}
					}

					ls_tag = " <TAG_IDENTIFICACION_INTERVINIENTE_RECURSO>";

					if(as_texto.contains(ls_tag))
					{
						Collection<SolicitudAsociada> lcs_solicitudAsociada;

						lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
								                              .findAllByIdSolicitud(ls_idSolicitud, false);

						if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
						{
							StringBuilder lsb_sb;
							SolicitudDAO  lsd_DAO;
							PersonaDAO    lpd_DAO;
							int           li_contador;

							lsb_sb          = new StringBuilder("Que el(la) señor(a) ");
							lsd_DAO         = DaoCreator.getSolicitudDAO(adm_manager);
							lpd_DAO         = DaoCreator.getPersonaDAO(adm_manager);
							li_contador     = 1;

							for(SolicitudAsociada lsa_iterador : lcs_solicitudAsociada)
							{
								if(lsa_iterador != null)
								{
									String ls_idSolicitudAsociada;

									ls_idSolicitudAsociada = lsa_iterador.getIdSolicitud1();

									if(StringUtils.isValidString(ls_idSolicitudAsociada))
									{
										Solicitud ls_solicitudAsociada;

										ls_solicitudAsociada = lsd_DAO.findById(ls_idSolicitudAsociada);

										if(ls_solicitudAsociada != null)
										{
											String ls_idProceso;

											ls_idProceso = ls_solicitudAsociada.getIdProceso();

											if(
											    StringUtils.isValidString(ls_idProceso)
												    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
											)
											{
												Persona lp_persona;

												lp_persona = lpd_DAO.findById(ls_solicitudAsociada.getIdPersona());

												if(lp_persona != null)
												{
													if(li_contador > 1)
														lsb_sb.append(", el(la) señor(a) ");

													lsb_sb.append(lp_persona.getNombreCompleto());
													lsb_sb.append(" identificado(a) con ");
													lsb_sb.append(lp_persona.getIdDocumentoTipo());
													lsb_sb.append(" número ");
													lsb_sb.append(lp_persona.getNumeroDocumento());
													lsb_sb.append(", en calidad de ");

													{
														String ls_idCalidadSolicitante;

														ls_idCalidadSolicitante = ls_solicitudAsociada
																.getIdCalidadSolicitante();

														if(StringUtils.isValidString(ls_idCalidadSolicitante))
														{
															CalidadSolicitante lcs_calidadSolicitante;

															lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(
																    adm_manager
																).findById(ls_idCalidadSolicitante);

															if(lcs_calidadSolicitante != null)
																lsb_sb.append(lcs_calidadSolicitante.getNombre());
														}
													}
												}
											}
										}
									}

									li_contador++;
								}
							}

							as_texto = as_texto.replace(ls_tag, lsb_sb.toString());
						}
					}

					ls_tag = "<TAG_TIPO_RECURSO>";

					if(as_texto.contains(ls_tag))
					{
						String ls_idSolicitud1;

						ls_idSolicitud1 = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
								                        .findMaxByIdSolicitud(ls_idSolicitud);

						if(StringUtils.isValidString(ls_idSolicitud1))
						{
							Solicitud ls_solicitudAsociada;

							ls_solicitudAsociada = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud1);

							if(ls_solicitudAsociada != null)
							{
								Subproceso lsp_subproceso;

								lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
										                       .findById(
										    ls_solicitudAsociada.getIdProceso(), ls_solicitudAsociada.getIdSubproceso()
										);

								if(lsp_subproceso != null)
								{
									String ls_nombreSubproceso;

									ls_nombreSubproceso = lsp_subproceso.getNombre();

									if(StringUtils.isValidString(ls_nombreSubproceso))
										as_texto = as_texto.replace(ls_tag, ls_nombreSubproceso);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarTagsTextoRecursos", lb2be_e);

			throw lb2be_e;
		}

		return limpiarTags(as_texto);
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para actuaciones administrativas.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion de ab resolucion
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivo(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion
	)
	    throws B2BException
	{
		return plantillaDatosPorIdMotivo(adm_manager, al_idMotivo, ab_resolucion, true);
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para actuaciones administrativas.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion Argumento de tipo <code>boolean</code> que determina si es una resolución.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivo(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion, boolean ab_consultarConstante
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = null;

		try
		{
			String ls_idPlantilla;
			String ls_tipoArchivo;
			String ls_tipoDocumental;

			ls_idPlantilla        = null;
			ls_tipoArchivo        = null;
			ls_tipoDocumental     = null;

			if(al_idMotivo == MotivoTramiteCommon.AUTO_DE_APERTURA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_AUTO_APERTURA;
					ls_tipoArchivo        = TipoArchivoCommon.AUTO_APERTURA;
					ls_tipoDocumental     = TipoDocumentalCommon.AUTO;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_AUTO_APERTURA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.AUTO_DE_ACLARACION)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_AUTO_ACLARACION;
					ls_tipoArchivo        = TipoArchivoCommon.AUTO_ACLARACION;
					ls_tipoDocumental     = TipoDocumentalCommon.AUTO;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_AUTO_ACLARACION;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.AUTO_DE_PRUEBAS)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_AUTO_APERTURA_A_PRUEBAS;
					ls_tipoArchivo        = TipoArchivoCommon.AUTO_APERTURA_PRUEBAS;
					ls_tipoDocumental     = TipoDocumentalCommon.AUTO;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_AUTO_APERTURA_A_PRUEBAS;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_DECISION;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_DECISION;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RESOLUCION_DECISION;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_ACLARACION_DECISION;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_ACLARACION_DECISION;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RESOLUCION_ACLARACION_DECISION;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.NEGACION_APERTURA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_NEGACION_APERTURA_ACTUACION_ADMINISTRATIVA;
					ls_tipoArchivo        = TipoArchivoCommon.NEGACION_APERTURA_ACTUACION_ADMINISTRATIVA;
					ls_tipoDocumental     = TipoDocumentalCommon.COMUNICACION;
				}
			}

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setIdPlantilla(ls_idPlantilla);
			laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
			laa_actuacionesAdministrativas.setTipoDocumental(ls_tipoDocumental);

			if(ab_consultarConstante)
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_idPlantilla);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

				if(lc_constante == null)
				{
					Object[] loa_messageArgs = new String[1];

					loa_messageArgs[0] = ls_idPlantilla;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				laa_actuacionesAdministrativas.setArchivo(lc_constante.getImagenes());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("plantillaPorIdMotivo", lb2be_e);

			throw lb2be_e;
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para recursos.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion de ab resolucion
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoRecursos(
	    DAOManager adm_manager, String as_idProceso, long al_idMotivo, boolean ab_resolucion
	)
	    throws B2BException
	{
		return plantillaDatosPorIdMotivoRecursos(adm_manager, as_idProceso, al_idMotivo, ab_resolucion, true);
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para actuaciones administrativas.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion Argumento de tipo <code>boolean</code> que determina si es una resolución.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoRecursos(
	    DAOManager adm_manager, String as_idProceso, long al_idMotivo, boolean ab_resolucion,
	    boolean ab_consultarConstante
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = null;

		try
		{
			if(StringUtils.isValidString(as_idProceso))
			{
				String  ls_idPlantilla;
				String  ls_tipoArchivo;
				String  ls_tipoDocumental;
				boolean lb_idProceso3;
				boolean lb_idProceso4;

				ls_idPlantilla        = null;
				ls_tipoArchivo        = null;
				ls_tipoDocumental     = null;
				lb_idProceso3         = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3);
				lb_idProceso4         = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4);

				if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_AUTO_DE_APERTURA_DE_PRUEBAS)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4
							? ConstanteCommon.PLANTILLA_AUTO_APERTURA_PRUEBAS_DEV_DINERO
							: (lb_idProceso3 ? ConstanteCommon.PLANTILLA_AUTO_APERTURA_PRUEBAS_ACT_ADM
							                 : ConstanteCommon.PLANTILLA_AUTO_APERTURA_RECURSO);
						ls_tipoArchivo        = lb_idProceso4 ? TipoArchivoCommon.AUTO_APERTURA_PRUEBAS_DEV_DINERO
							                                  : (lb_idProceso3
							? TipoArchivoCommon.AUTO_APERTURA_PRUEBAS_ACT_ADM
							: TipoArchivoCommon.AUTO_APERTURA_PRUEBAS_RECURSO);
						ls_tipoDocumental     = TipoDocumentalCommon.AUTO;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_AUTO_APERTURA_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4
							? ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_DEV_DINERO
							: (lb_idProceso3 ? ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_ACT_ADM
							                 : ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_RECURSO);
						ls_tipoArchivo        = TipoArchivoCommon.CONCEDIENDO_RECURSO_REPOSICION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_CONCEDIENDO_REPOSICION_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_NEGANDO_RECURSO_DE_REPOSICION)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4
							? ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_DEV_DINERO
							: (lb_idProceso3 ? ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_ACT_ADM
							                 : ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_RECURSO);
						ls_tipoArchivo        = TipoArchivoCommon.NEGANDO_RECURSO_REPOSICION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_NEGANDO_REPOSICION_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(
				    al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_NEGANDO_RECURSO_REPOSICION_CONCEDIENDO_APELACION
				)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4
							? ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_CONCEDIENDO_APELACION_DEV_DINERO
							: (lb_idProceso3
							? ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_CONCEDIENDO_APELACION_ACT_ADM
							: ConstanteCommon.PLANTILLA_RESOL_NEGANDO_REPOSICION_CONCEDIENDO_APELACION_RECURSO);
						ls_tipoArchivo        = TipoArchivoCommon.NEGANDO_RECURSO_REPOSICION_CONCEDE_AP;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_NEGANDO_REPOSICION_RECURSO_CONCEDE_AP;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(
				    al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_CONCEDIENDO_PARCIALMENTE_RECURSO_REPOSICION
				)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOL_CONCEDIENDO_REPOSICION_PARCIALMENTE_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.CONCEDIENDO_PARCIAL_RECURSO_REPOSICION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_CONCEDIENDO_PARCIAL_REPOSICION_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_RECHAZANDO_RECURSO)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4 ? ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_DEV_DINERO
							                                  : (lb_idProceso3
							? ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_ACT_ADM
							: ConstanteCommon.PLANTILLA_RESOLUCION_RECHAZA_RECURSO);
						ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_RECHAZA_RECURSO;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RECHAZO_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_APROBANDO_RECURSO_APELACION)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = lb_idProceso4
							? ConstanteCommon.PLANTILLA_CONCEDIENDO_APELACION_DEV_DINERO
							: (lb_idProceso3 ? ConstanteCommon.PLANTILLA_CONCEDIENDO_APELACION_ACT_ADM
							                 : ConstanteCommon.PLANTILLA_CONCEDIENDO_APELACION);
						ls_tipoArchivo        = TipoArchivoCommon.CONCEDIENDO_RECURSO_APELACION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RECHAZO_RECURSO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
					}
				}

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdPlantilla(ls_idPlantilla);
				laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
				laa_actuacionesAdministrativas.setTipoDocumental(ls_tipoDocumental);

				if(ab_consultarConstante)
				{
					Constantes lc_constante;

					lc_constante = new Constantes();

					lc_constante.setIdConstante(ls_idPlantilla);

					lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

					if(lc_constante == null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_idPlantilla;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
					}

					laa_actuacionesAdministrativas.setArchivo(lc_constante.getImagenes());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("plantillaPorIdMotivo", lb2be_e);

			throw lb2be_e;
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para segunda instancia.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion Argumento de tipo <code>boolean</code> que determina si es una resolución.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoSegundaInstancia(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion, boolean ab_consultarConstante
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = null;

		try
		{
			String ls_idPlantilla;
			String ls_tipoArchivo;
			String ls_tipoDocumental;

			ls_idPlantilla        = null;
			ls_tipoArchivo        = null;
			ls_tipoDocumental     = null;

			if(al_idMotivo == MotivoTramiteCommon.NO_PROCEDE_SEGUNDA_INSTANCIA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_OFICIO_NOPROCEDE_SEGUNDA_INSTANCIA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO_NO_PRODECE;
					ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
				}
				else
				{
				}
			}

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setIdPlantilla(ls_idPlantilla);
			laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
			laa_actuacionesAdministrativas.setTipoDocumental(ls_tipoDocumental);

			if(ab_consultarConstante)
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_idPlantilla);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

				if(lc_constante == null)
				{
					Object[] loa_messageArgs = new String[1];

					loa_messageArgs[0] = ls_idPlantilla;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				laa_actuacionesAdministrativas.setArchivo(lc_constante.getImagenes());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("plantillaDatosPorIdMotivoSegundaInstancia", lb2be_e);

			throw lb2be_e;
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para traslados.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion de ab resolucion
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoTraslados(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion
	)
	    throws B2BException
	{
		return plantillaDatosPorIdMotivoTraslados(adm_manager, al_idMotivo, ab_resolucion, true);
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para traslados.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion Argumento de tipo <code>boolean</code> que determina si es una resolución.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoTraslados(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion, boolean ab_consultarConstante
	)
	    throws B2BException
	{
		return plantillaDatosPorIdMotivoTraslados(
		    adm_manager, al_idMotivo, ab_resolucion, ab_consultarConstante, false, false
		);
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para traslados.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param ab_resolucion Argumento de tipo <code>boolean</code> que determina si es una resolución.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @param ab_etapa651 correspondiente al valor de ab etapa 651
	 * @param ab_planeacion correspondiente al valor de ab etapa 236
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoTraslados(
	    DAOManager adm_manager, long al_idMotivo, boolean ab_resolucion, boolean ab_consultarConstante,
	    boolean ab_etapa651, boolean ab_planeacion
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actuacionesAdministrativas;

		laa_actuacionesAdministrativas = null;

		try
		{
			String ls_idPlantilla;
			String ls_tipoArchivo;
			String ls_tipoDocumental;

			ls_idPlantilla        = null;
			ls_tipoArchivo        = null;
			ls_tipoDocumental     = null;

			if(!ab_etapa651)
			{
				if(al_idMotivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_PROYECTAR_RESOLUCION_TRASLADO;
						ls_tipoArchivo        = TipoArchivoCommon.PROYECCION_RESOLUCION_TRASLADO_MATRICULA;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						if(ab_planeacion)
						{
							ls_idPlantilla        = ConstanteCommon.PLANTILLA_CONSTANCIA_PUBLICACION;
							ls_tipoArchivo        = TipoArchivoCommon.CONSTANCIA_PUBLICACION;
							ls_tipoDocumental     = TipoDocumentalCommon.CONSTANCIA_PUBLICACION;
						}

						else
						{
							ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_652;
							ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_TRASLADO;
							ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
						}
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_SOLICITUD_DOCUMENTOS_TRASLADO;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_NEGACION_TRASLADO_MATRICULA;
						ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
						ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA)
				{
					if(ab_resolucion)
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_MASIVA;
						ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_TRASLADO;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_SUSPENSION_TRASLADOS;
						ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_SUSP_TRASLADO;
						ls_tipoDocumental     = TipoDocumentalCommon.OFICIO;
					}
				}
			}
			else
			{
				if(!ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_ACEPTACION_TRASLADO;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_ACEPTACION_TRASLADO;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_TRASLADO_INDIVIDUAL;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION_TRASLADO_INDIVIDUAL;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
			}

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setIdPlantilla(ls_idPlantilla);
			laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
			laa_actuacionesAdministrativas.setTipoDocumental(ls_tipoDocumental);

			if(ab_consultarConstante)
			{
				Constantes lc_constante;

				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_idPlantilla);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

				if(lc_constante == null)
				{
					Object[] loa_messageArgs = new String[1];

					loa_messageArgs[0] = ls_idPlantilla;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				laa_actuacionesAdministrativas.setArchivo(lc_constante.getImagenes());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("plantillaDatosPorIdMotivoTraslados", lb2be_e);

			throw lb2be_e;
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Metodo encargado de validar los campos de la resolución de actuaciones administrativas.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>String</code> que contiene los criterios a ser validados.
	 * @param al_idMotivo Argumento de tipo <code>al_idMotivo</code> que contiene el id motivo a ser validado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private void validarCamposResolucion(OficiosTexto aot_oficiosTexto, long al_idMotivo)
	    throws B2BException
	{
		try
		{
			if((aot_oficiosTexto != null) && (al_idMotivo > 0))
			{
				boolean lb_autoPruebas;
				boolean lb_resolucionAclaracionDecision;

				lb_autoPruebas                      = al_idMotivo == MotivoTramiteCommon.AUTO_DE_PRUEBAS;
				lb_resolucionAclaracionDecision     = al_idMotivo == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION;

				if(al_idMotivo != MotivoTramiteCommon.NEGACION_APERTURA)
				{
					String ls_encabezado;

					ls_encabezado = aot_oficiosTexto.getEncabezado();

					if(!StringUtils.isValidString(ls_encabezado))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ENCABEZADO);
				}

				if(
				    (al_idMotivo == MotivoTramiteCommon.AUTO_DE_APERTURA)
					    || (al_idMotivo == MotivoTramiteCommon.AUTO_DE_ACLARACION) || lb_autoPruebas
					    || lb_resolucionAclaracionDecision
				)
				{
					{
						String ls_antecedentes;

						ls_antecedentes = aot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
					}

					{
						String ls_consideracion;

						ls_consideracion = lb_resolucionAclaracionDecision ? aot_oficiosTexto.getConsideracion()
							                                               : aot_oficiosTexto.getPertinencia();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
					}

					if(lb_autoPruebas)
					{
						{
							Long ll_diasSuspencion;

							ll_diasSuspencion = aot_oficiosTexto.getDiasSuspension();

							if(!NumericUtils.isValidLong(ll_diasSuspencion))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TERMINO_PARA_APORTAR_LAS_PRUEBAS);
						}

						{
							String ls_diasLetras;

							ls_diasLetras = aot_oficiosTexto.getDiasLetras();

							if(!StringUtils.isValidString(ls_diasLetras))
								throw new B2BException(ErrorKeys.ERROR_SIN_DIAS_LETRAS);
						}
					}

					{
						String ls_razon1;

						ls_razon1 = aot_oficiosTexto.getRazon1();

						if(!StringUtils.isValidString(ls_razon1))
							throw new B2BException(
							    (lb_autoPruebas || lb_resolucionAclaracionDecision)
							    ? ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE : ErrorKeys.ERROR_DEBE_DIGITAR_DISPONE
							);
					}
				}

				if(al_idMotivo == MotivoTramiteCommon.NEGACION_APERTURA)
				{
					String ls_consideracion;

					ls_consideracion = aot_oficiosTexto.getConsideracion();

					if(!StringUtils.isValidString(ls_consideracion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				if(al_idMotivo == MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION)
				{
					{
						String ls_antecedentes;

						ls_antecedentes = aot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
					}

					{
						String ls_pruebasRecaudadas;

						ls_pruebasRecaudadas = aot_oficiosTexto.getPruebasRecaudadas();

						if(!StringUtils.isValidString(ls_pruebasRecaudadas))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_PRUEBAS_RECAUDADAS);
					}

					{
						String ls_analisisProbatorio;

						ls_analisisProbatorio = aot_oficiosTexto.getAnalisisProbatorio();

						if(!StringUtils.isValidString(ls_analisisProbatorio))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANALISIS_PROBATORIO);
					}

					{
						String ls_intervencionInteresados;

						ls_intervencionInteresados = aot_oficiosTexto.getIntervencionInteresados();

						if(!StringUtils.isValidString(ls_intervencionInteresados))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_INTERVENCION_INTERESADOS);
					}

					{
						String ls_consideracion;

						ls_consideracion = aot_oficiosTexto.getConsideracion();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_DEPACHO);
					}

					{
						String ls_razon2;

						ls_razon2 = aot_oficiosTexto.getRazon2();

						if(!StringUtils.isValidString(ls_razon2))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_FUNDAMENTOS_JURIDICOS);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCamposResolucion", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de validar los campos de la resolución de rechaza recurso.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>String</code> que contiene los criterios a ser validados.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private void validarCamposResolucionRechazaRecurso(OficiosTexto aot_oficiosTexto)
	    throws B2BException
	{
		try
		{
			if(aot_oficiosTexto != null)
			{
				{
					String ls_antecedentes;

					ls_antecedentes = aot_oficiosTexto.getAntecedentes();

					if(!StringUtils.isValidString(ls_antecedentes))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
				}

				{
					String ls_argumentosRecurrente;

					ls_argumentosRecurrente = aot_oficiosTexto.getArgumentosRecurrente();

					if(!StringUtils.isValidString(ls_argumentosRecurrente))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ARGUMENTOS_RECURRENTE);
				}

				{
					String ls_consideracion;

					ls_consideracion = aot_oficiosTexto.getConsideracion();

					if(!StringUtils.isValidString(ls_consideracion))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
				}

				{
					String ls_resuelve;

					ls_resuelve = aot_oficiosTexto.getResuelve();

					if(!StringUtils.isValidString(ls_resuelve))
						throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCamposResolucionRechazaRecurso", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de validar los campos de la resolución de recursos.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>String</code> que contiene los criterios a ser validados.
	 * @param al_idMotivo Argumento de tipo <code>al_idMotivo</code> que contiene el id motivo a ser validado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private void validarCamposResolucionRecursos(OficiosTexto aot_oficiosTexto, long al_idMotivo)
	    throws B2BException
	{
		try
		{
			if((aot_oficiosTexto != null) && (al_idMotivo > 0))
			{
				if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_AUTO_DE_APERTURA_DE_PRUEBAS)
				{
					{
						String ls_antecedentes;

						ls_antecedentes = aot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
					}

					{
						String ls_consideracion;

						ls_consideracion = aot_oficiosTexto.getPertinencia();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
					}

					{
						{
							Long ll_diasSuspencion;

							ll_diasSuspencion = aot_oficiosTexto.getDiasSuspension();

							if(!NumericUtils.isValidLong(ll_diasSuspencion))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TERMINO_PARA_APORTAR_LAS_PRUEBAS);
						}

						{
							String ls_diasLetras;

							ls_diasLetras = aot_oficiosTexto.getDiasLetras();

							if(!StringUtils.isValidString(ls_diasLetras))
								throw new B2BException(ErrorKeys.ERROR_SIN_DIAS_LETRAS);
						}
					}

					{
						String ls_resuelve;

						ls_resuelve = aot_oficiosTexto.getResuelve();

						if(!StringUtils.isValidString(ls_resuelve))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION)
				{
					{
						String ls_pertinencia;

						ls_pertinencia = aot_oficiosTexto.getPertinencia();

						if(!StringUtils.isValidString(ls_pertinencia))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
					}

					{
						String ls_razon1;

						ls_razon1 = aot_oficiosTexto.getRazon1();

						if(!StringUtils.isValidString(ls_razon1))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_COPIAR_TEXTO_NOTA_DEVOLUTIVA);
					}

					{
						String ls_antecedentes;

						ls_antecedentes = aot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_HECHOS);
					}

					{
						String ls_argumentosRecurrente;

						ls_argumentosRecurrente = aot_oficiosTexto.getArgumentosRecurrente();

						if(!StringUtils.isValidString(ls_argumentosRecurrente))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ARGUMENTOS_RECURRENTE);
					}

					{
						String ls_notificaciones;

						ls_notificaciones = aot_oficiosTexto.getPertinencia();

						if(!StringUtils.isValidString(ls_notificaciones))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NOTIFICACIONES);
					}

					{
						String ls_consideracion;

						ls_consideracion = aot_oficiosTexto.getConsideracion();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_DEPACHO);
					}

					{
						String ls_resuelve;

						ls_resuelve = aot_oficiosTexto.getResuelve();

						if(!StringUtils.isValidString(ls_resuelve))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
					}
				}
				else if(al_idMotivo == MotivoTramiteCommon.ETAPA_410_RESOLUCION_APROBANDO_RECURSO_APELACION)
				{
					{
						String ls_antecedentes;

						ls_antecedentes = aot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
					}

					{
						String ls_argumentosRecurrente;

						ls_argumentosRecurrente = aot_oficiosTexto.getArgumentosRecurrente();

						if(!StringUtils.isValidString(ls_argumentosRecurrente))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ARGUMENTOS_RECURRENTE);
					}

					{
						String ls_consideracion;

						ls_consideracion = aot_oficiosTexto.getConsideracion();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_DEPACHO);
					}

					{
						String ls_resuelve;

						ls_resuelve = aot_oficiosTexto.getResuelve();

						if(!StringUtils.isValidString(ls_resuelve))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCamposResolucionRecursos", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de validar los campos de la resolución de traslados.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>String</code> que contiene los criterios a ser validados.
	 * @param al_idMotivo Argumento de tipo <code>al_idMotivo</code> que contiene el id motivo a ser validado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private void validarCamposResolucionTraslados(
	    OficiosTexto aot_oficiosTexto, long al_idMotivo, boolean ab_suspension
	)
	    throws B2BException
	{
		validarCamposResolucionTraslados(aot_oficiosTexto, al_idMotivo, ab_suspension, false);
	}

	/**
	 * Metodo encargado de validar los campos de la resolución de traslados.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>String</code> que contiene los criterios a ser validados.
	 * @param al_idMotivo Argumento de tipo <code>al_idMotivo</code> que contiene el id motivo a ser validado.
	 * @param ab_suspension correspondiente al valor de ab suspension
	 * @param ab_651 correspondiente al valor de boolean 651
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	private void validarCamposResolucionTraslados(
	    OficiosTexto aot_oficiosTexto, long al_idMotivo, boolean ab_suspension, boolean ab_651
	)
	    throws B2BException
	{
		try
		{
			if((aot_oficiosTexto != null) && (al_idMotivo > 0))
			{
				if(!ab_651)
				{
					if(al_idMotivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)
					{
						{
							String ls_antecedentes;

							ls_antecedentes = aot_oficiosTexto.getAntecedentes();

							if(!StringUtils.isValidString(ls_antecedentes))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
						}

						{
							String ls_consideracion;

							ls_consideracion = aot_oficiosTexto.getConsideracion();

							if(!StringUtils.isValidString(ls_consideracion))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
						}

						{
							String ls_resuelve;

							ls_resuelve = aot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}
					else if(
					    (al_idMotivo == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)
						    || (al_idMotivo == MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
					)
					{
						String ls_consideracion;

						ls_consideracion = aot_oficiosTexto.getConsideracion();

						if(!StringUtils.isValidString(ls_consideracion))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_DEPACHO);
					}
					else if((al_idMotivo == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA))
					{
						if(ab_suspension)
						{
							{
								Date ld_fecha;

								ld_fecha = aot_oficiosTexto.getFechaInicioTraslado();

								if(ld_fecha == null)
									throw new B2BException(ErrorKeys.ERROR_FECHA_INICIAL_NO_VALIDA);
							}

							{
								Date ld_fecha;

								ld_fecha = aot_oficiosTexto.getFechaFinTraslado();

								if(ld_fecha == null)
									throw new B2BException(ErrorKeys.ERROR_FECHA_FINAL_NO_VALIDA);
							}

							{
								Date ld_fechaInicial;
								Date ld_fechaFinal;

								ld_fechaInicial     = aot_oficiosTexto.getFechaInicioTraslado();
								ld_fechaFinal       = aot_oficiosTexto.getFechaFinTraslado();

								if(ld_fechaInicial.compareTo(ld_fechaFinal) > 0)
									throw new B2BException(ErrorKeys.FECHA_INICIAL_SUPERIOR);
							}

							{
								String ls_consideracion;

								ls_consideracion = aot_oficiosTexto.getConsideracion();

								if(!StringUtils.isValidString(ls_consideracion))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_DEPACHO);
							}

							{
								String ls_resuelve;

								ls_resuelve = aot_oficiosTexto.getResuelve();

								if(!StringUtils.isValidString(ls_resuelve))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
							}
						}
						else
						{
							{
								String ls_antecedentes;

								ls_antecedentes = aot_oficiosTexto.getAntecedentes();

								if(!StringUtils.isValidString(ls_antecedentes))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
							}

							{
								String ls_resuelve;

								ls_resuelve = aot_oficiosTexto.getResuelve();

								if(!StringUtils.isValidString(ls_resuelve))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
							}
						}
					}
					else
					{
						{
							String ls_antecedentes;

							ls_antecedentes = aot_oficiosTexto.getAntecedentes();

							if(!StringUtils.isValidString(ls_antecedentes))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
						}

						{
							String ls_resuelve;

							ls_resuelve = aot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCamposResolucionTraslados", lb2be_e);

			throw lb2be_e;
		}
	}
}
