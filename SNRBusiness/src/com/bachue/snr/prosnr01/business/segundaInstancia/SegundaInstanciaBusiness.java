package com.bachue.snr.prosnr01.business.segundaInstancia;

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
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.CalidadSolicitanteDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.registro.Registro;
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
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
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
import java.util.Map;


/**
 * Clase que contiene todos las propiedades SegundaInstanciaBusiness.
 *
 * @author Luis Chacón Fecha de Creacion: 10/08/2020
 */
public class SegundaInstanciaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SegundaInstanciaBusiness.class);

	/**
	 * Método encargado de consultar las personas vinculadas a una solicitud con
	 * proceso de segunda Instancia.
	 *
	 * @param as_idSolicitud
	 *            Argumento de tipo <code>String</code> que contiene el id solicitud
	 *            a consultar.
	 * @return Retorna objeto de tipo <code>Registro</code> que contiene el listado
	 *         de personas que coincidieron con los criterios de búsqueda.
	 * @throws B2BException
	 *             Señala que se ha generado una excepción
	 */
	public synchronized Registro buscarPersonasSegundaInstancia(String as_idSolicitud)
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
										    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48)
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
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param aaa_parametros
	 *            Argumento de tipo <code>ActuacionesAdministrativas</code> que
	 *            contiene los argumentos necesarios para realizar la búsqueda.
	 * @param as_userId
	 *            de as user id
	 * @param as_remoteIp
	 *            de as remote ip
	 * @return Objeto de tipo <code>TagPlantillaResolucion</code> que contiene los
	 *         datos encontrados para segunda instancia
	 * @throws B2BException
	 *             Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion cargarDatosSegundaInstancia(
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
				Long   ll_idEtapa;
				String ls_idTurno;
				Turno  lt_turno;

				ll_idEtapa     = NumericUtils.getLongWrapper(aaa_parametros.getIdEtapa());
				ls_idTurno     = aaa_parametros.getIdTurno();
				lt_turno       = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

				if(lt_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

							if(ls_solicitud != null)
							{
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
							long ll_idMotivo;

							ll_idMotivo = aaa_parametros.getIdMotivo();

							if(ll_idMotivo > 0)
							{
								TagPlantillaResolucion laa_temp;

								laa_temp = plantillaDatosPorIdMotivoSegundaInstancia(
									    ldm_manager, ll_idMotivo, true, true
									);

								if(laa_temp != null)
								{
									String ls_plantilla;

									ls_plantilla = laa_temp.getIdPlantilla();

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

										llenarOficiosTexto(
										    lot_data, lmss_data, ll_idMotivo, lt_turno,
										    NumericUtils.getLong(aaa_parametros.getIdTurnoHistoria()), ldm_manager
										);

										{
											Collection<PersonaNotificacion> lcpn_resultadosNotificacion;

											lcpn_resultadosNotificacion = DaoCreator.getPersonaNotificacionDAO(
												    ldm_manager
												).findByMaxDecisionPlantilla(ls_idTurno, ll_idEtapa);

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

										laa_actuacionesAdministrativas.setOficiosTexto(lot_data);
										laa_actuacionesAdministrativas.setTipoArchivo(laa_temp.getTipoArchivo());
										laa_actuacionesAdministrativas.setArchivo(
										    generarDocumentosSegundaInstancia(
										        lot_data, as_userId, as_remoteIp, ll_idMotivo, false, false, ldm_manager
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
	 * Método encargado de enviar al responsable de actuaciones administrativas.
	 *
	 * @param aaa_actuacionesAdministrativas
	 *            Objeto que contiene la información para enviar al responsable de
	 *            actuaciones administrativas.
	 * @param as_userId
	 *            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el trámite.
	 * @param as_remoteIp
	 *            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el trámite.
	 * @param al_motivo
	 *            Variable de tipo long que contiene la decision seleccionada.
	 * @throws B2BException
	 *             Se genera cuando se presenta una excepción.
	 */
	public synchronized void enviarAprobador(
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

					if(CollectionUtils.isValidCollection(lctd_tiposDocumentales) && (ls_solicitud != null))
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
					long          ll_idEtapa;

					lth_turnoHistoria     = new TurnoHistoria();
					lmt_motivoTramite     = new MotivoTramite();
					ll_idEtapa            = aaa_actuacionesAdministrativas.getIdEtapa();

					lth_turnoHistoria.setIdTurnoHistoria(
					    NumericUtils.getLongWrapper(aaa_actuacionesAdministrativas.getIdTurnoHistoria())
					);

					lmt_motivoTramite.setIdMotivo(al_motivo);
					lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);

					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
					    EstadoCommon.TERMINADA
					);
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
	 * Método encargado de generar los documentos de segunda instancia
	 *
	 * @param aot_oficioTextoData
	 *            Objeto que contiene la información para generar el pdf.
	 * @param as_userId
	 *            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el trámite.
	 * @param as_remoteIp
	 *            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el trámite.
	 * @param al_motivo
	 *            Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException
	 *             Se genera cuando se presenta una excepción.
	 */
	public synchronized byte[] generarDocumentosSegundaInstancia(
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
				lba_return = generarDocumentosSegundaInstancia(
					    aot_oficioTextoData, as_userId, as_remoteIp, al_motivo, true, false, ldm_manager
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
	 * Método encargado de generar el documento de segunda instancia o resolución.
	 *
	 * @param aot_oficioTextoData
	 *            Objeto que contiene la información para generar el pdf.
	 * @param as_userId
	 *            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el trámite.
	 * @param as_remoteIp
	 *            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el trámite.
	 * @param al_motivo
	 *            Variable de tipo long que contiene la decision seleccionada.
	 * @param ab_salvar
	 *            Variable booleana que indica si se debe salvar el documento y la
	 *            información.
	 * @param ab_firma
	 *            Variable booleana que indica si se debe firmar el documento.
	 * @param adm_manager
	 *            DaoManager que administra las transacciones.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarDocumentosSegundaInstancia(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, long al_motivo, boolean ab_salvar,
	    boolean ab_firma, DAOManager adm_manager
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

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
						                          .findById(aot_oficioTextoData.getIdTurnoHistoria());

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

						if((lt_turno != null) && (al_motivo > 0))
						{
							byte[]                 lba_plantilla;
							TagPlantillaResolucion laa_actuacionesAdministrativas;
							String                 ls_idPlantilla;
							String                 ls_tipoArchivo;
							String                 ls_tipoDocumental;

							laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoSegundaInstancia(
								    adm_manager, al_motivo, true, true
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
								Constantes          lc_usuarioFirma;
								ConstantesDAO       lc_DAO;
								Date                ld_fechaResol;
								Date                ld_fechaOficio;
								DocumentosSalidaDAO lds_DAO;
								ImagenesDAO         li_DAO;
								Long                ll_NumeroResolucion;
								Map<String, String> lmss_datos;
								String              ls_idSolicitud;
								String              ls_resolucion;
								String              ls_codigoVerificacion;
								String              ls_consecutivoOficio;

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
								ls_consecutivoOficio      = null;

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

								lc_usuarioFirma = lc_DAO.findByIdWithImage(lc_usuarioFirma);

								if(!ab_firma)
								{
									String ls_tag;
									String ls_idCirculo;
									String ls_plantilla;

									ls_idCirculo     = lt_turno.getIdCirculo();
									ls_plantilla     = new String(lba_plantilla);

									{
										String ls_encabezado;
										String ls_antecedentes;
										String ls_dispone;
										String ls_notificaciones;
										String ls_pruebasRecaudadas;
										String ls_analisisProbatorio;
										String ls_intervencionInteresados;
										String ls_consideracion;
										String ls_justificacion;
										String ls_resuelve;
										String ls_argumentosRecurrente;
										String ls_consideracionSajr;
										String ls_pertinencia;
										Long   ll_diasSuspension;

										ls_encabezado                  = null;
										ls_antecedentes                = null;
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
										ls_consideracionSajr           = null;
										ls_pertinencia                 = null;

										if(ab_salvar && ab_firma)
										{
											OficiosTexto lot_data;

											lot_data = DaoCreator.getOficiosTextoDAO(adm_manager)
													                 .findByTurnoHistoria(ll_idTurnoHistoria);

											if(lot_data != null)
											{
												ls_encabezado                  = lot_data.getEncabezado();
												ls_antecedentes                = lot_data.getAntecedentes();
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
												ls_consideracionSajr           = lot_data.getConsideracionSajr();
												ls_pertinencia                 = lot_data.getPertinencia();
											}
										}
										else
										{
											ls_encabezado                  = aot_oficioTextoData.getEncabezado();
											ls_antecedentes                = aot_oficioTextoData.getAntecedentes();
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
											ls_consideracionSajr           = aot_oficioTextoData.getConsideracionSajr();
											ls_pertinencia                 = aot_oficioTextoData.getPertinencia();
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

										ls_tag = "<TAG_REFERENCIA>";

										String ls_referencia = aot_oficioTextoData.getReferencia();

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_referencia))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_referencia);

										ls_tag = "<TAG_CONSIDERACIONES_PANTALLA>";

										if((al_motivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS))
										{
											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_pertinencia)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_pertinencia);
										}
										else
										{
											if(
											    ls_plantilla.contains(ls_tag)
												    && StringUtils.isValidString(ls_consideracionSajr)
											)
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideracionSajr);
										}

										ls_tag = "<TAG_OBSERVACIONES_SAJ>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideracion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideracion);

										ls_tag = "<TAG_PRUEBAS_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_dispone))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_dispone);

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

										ls_tag = "<TAG_CONSIDERACIONES_PRIMERA_INSTANCIA_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideracion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideracion);
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
										    adm_manager, ls_plantilla, ls_tag, ls_idSolicitud, ls_idCirculo, false
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
																        ProcesoCommon.ID_PROCESO_48
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

									ls_tag = "<TAG_USUARIO_FIRMA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tag, "<TAG_CARGO_USUARIO_FIRMA>"
												);

									ls_tag = "<TAG_RESOL>";

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

									ls_tag = "<TAG_USUARIO_>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, as_userId);

									ls_tag = "<TAG_FECHA_EXPEDIENTE_SI>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(lt_turno != null)
										{
											String ls_expedienteSi;
											ls_expedienteSi = lt_turno.getExpedienteSI();

											if(StringUtils.isValidString(ls_expedienteSi))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_expedienteSi);
										}
									}

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

												if(lc_usuarioFirma != null)
												{
													int li_incrX;
													int li_incrY;

													li_incrX     = NumericUtils.getInt(lc_usuarioFirma.getEntero());
													li_incrY     = NumericUtils.getInt(lc_usuarioFirma.getReal());

													lba_return = getFirmaMasivaBusiness()
															             .reemplazarBookmarsFirma(
															    lba_return, lc_usuarioFirma.getImagenes(), li_incrX,
															    li_incrY
															);
												}

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
													lba_return = getFirmaMasivaBusiness()
															             .reemplazarBookmarsFirma(
															    lba_return,
															    MarcaAgua.crearImagenConTexto(
															        ls_resolucion, ls_resolucion.length() * li_temp,
															        li_fontSize, li_fontSize
															    ), 0, 0, "RESOLUCION", false
															);

												Date             ld_date;
												SimpleDateFormat formatter;
												String           ls_date;

												ld_date           = new Date();
												ld_fechaResol     = ld_date;
												formatter         = new SimpleDateFormat("dd/MM/yyyy");
												ls_date           = StringUtils.getStringNotNull(
													    formatter.format(ld_date)
													);

												lba_return = getFirmaMasivaBusiness()
														             .reemplazarBookmarsFirma(
														    lba_return,
														    MarcaAgua.crearImagenConTexto(
														        ls_date, ls_date.length() * li_temp, li_fontSize,
														        li_fontSize
														    ), 0, 0, "FECHA_RESOL", false
														);

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

												{
													String ls_idCorrespondencia;

													ls_idCorrespondencia = generarIdCorrespondencia(
														    lth_turnoHistoria, adm_manager, false
														);

													if(StringUtils.isValidString(ls_idCorrespondencia))
														lba_return = getFirmaMasivaBusiness()
																             .reemplazarBookmarsFirma(
																    lba_return,
																    MarcaAgua.crearImagenConTexto(
																        ls_idCorrespondencia,
																        ls_idCorrespondencia.length() * li_temp,
																        li_fontSize, li_fontSize
																    ), 0, 0, "REFERENCIA_SALIDA", false
																);
												}
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
											lds_documentoSalida.setIdEcm(null);
											lds_documentoSalida.setFechaEnvio(null);
											lds_documentoSalida.setIdNombreDocumento(null);
											lds_documentoSalida.setRepositorio(
											    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
											);
											lds_documentoSalida.setIdEcm(null);
											lds_documentoSalida.setIdNombreDocumento(null);
											lds_documentoSalida.setIdUsuarioModificacion(as_userId);
											lds_documentoSalida.setIpModificacion(as_remoteIp);
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
											lds_documentoSalida.setIdUsuarioCreacion(as_userId);
											lds_documentoSalida.setIpCreacion(as_remoteIp);
											lb_b = true;
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
				clh_LOGGER.error("generarDocumentosSegundaInstancia", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}

	/**
	 * Método encargado de generar los documentos de segunda Instancia.
	 *
	 * @param aaa_actuacionesAdministrativas
	 *            Objeto que contiene la información para generar el pdf.
	 * @param as_userId
	 *            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el trámite.
	 * @param as_remoteIp
	 *            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el trámite.
	 * @param al_motivo
	 *            Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException
	 *             Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosSegundaInstancia(
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
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(aaa_actuacionesAdministrativas.getIdTurno());

			if((lt_turno != null) && (aaa_actuacionesAdministrativas != null) && (al_motivo > 0))
			{
				long         ll_idTurnoHistoria;
				OficiosTexto lot_oficiosTexto;

				ll_idTurnoHistoria     = NumericUtils.getLong(aaa_actuacionesAdministrativas.getIdTurnoHistoria());
				lot_oficiosTexto       = aaa_actuacionesAdministrativas.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					String ls_tipoArchivo;

					ls_tipoArchivo = aaa_actuacionesAdministrativas.getTipoArchivo();

					if(StringUtils.isValidString(ls_tipoArchivo))
					{
						OficiosTextoDAO lotd_DAO;

						lotd_DAO = DaoCreator.getOficiosTextoDAO(ldm_manager);

						if(ll_idTurnoHistoria > NumericUtils.DEFAULT_LONG_VALUE)
						{
							TagPlantillaResolucion laa_actuacionesAdministrativas;

							laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoSegundaInstancia(
								    ldm_manager, al_motivo, true, true
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

						lotd_DAO.insertOrUpdate(lot_oficiosTexto, true);
					}

					if(ll_idTurnoHistoria > NumericUtils.DEFAULT_LONG_VALUE)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                          .findById(NumericUtils.getLongWrapper(ll_idTurnoHistoria));

						if(lth_turnoHistoria != null)
						{
							BigDecimal lbd_idEtapa;

							lbd_idEtapa = lth_turnoHistoria.getIdEtapa();

							if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
								aaa_actuacionesAdministrativas.setIdEtapa(NumericUtils.getLong(lbd_idEtapa));
						}
					}

					byte[] lba_archivo;

					lba_archivo = generarDocumentosSegundaInstancia(
						    lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, true, false, ldm_manager
						);

					if(lba_archivo != null)
					{
						Collection<ZipEntryUtil> lczeu_zip;
						aaa_actuacionesAdministrativas.setSegundaInstancia(true);

						lczeu_zip = getActuacionesAdministrativasBusiness()
								            .salvarPersonaNotificacion(
								    ldm_manager, aaa_actuacionesAdministrativas, lot_oficiosTexto, lt_turno.getIdTurno(),
								    lt_turno.getIdSolicitud(), al_motivo, as_userId, as_remoteIp
								);

						if(!CollectionUtils.isValidCollection(lczeu_zip))
							lczeu_zip = new ArrayList<ZipEntryUtil>();

						{
							ZipEntryUtil lzeu_pdf;

							lzeu_pdf = new ZipEntryUtil(
								    ls_tipoArchivo + ExtensionCommon.PDF_PUNTO, new ByteArrayInputStream(lba_archivo)
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

			clh_LOGGER.error("generarDocumentosSegundaInstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return laa_return;
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para
	 * segunda instancia.
	 *
	 * @param adm_manager
	 *            Argumento de tipo <code>DAOManager</code> que permite la conexión
	 *            con la base de datos.
	 * @param al_idMotivo
	 *            Argumento de tipo <code>long</code> que contiene el id motivo a
	 *            consultar.
	 * @param ab_resolucion
	 *            Argumento de tipo <code>boolean</code> que determina si es una
	 *            resolución.
	 * @param ab_consultarConstante
	 *            Argumento de tipo <code>boolean</code> que determina si se debe
	 *            consultar la constante.
	 * @return Objeto de tipo <code>ActuacionesAdministrativas</code> que contiene
	 *         los valores consultados.
	 * @throws B2BException
	 *             Objeto de tipo B2BException, se produce cuando se encuentra algun
	 *             error controlado.
	 */
	public TagPlantillaResolucion plantillaDatosPorIdMotivoSegundaInstancia(
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

			if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_AUTO_APERTURA_RECURSO;
					ls_tipoArchivo        = TipoArchivoCommon.AUTO_APERTURA_PRUEBAS_RECURSO;
					ls_tipoDocumental     = TipoDocumentalCommon.AUTO;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_AUTO_APERTURA_RECURSO;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_CONCEDE_APELACION;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDIENDO_RECURSO_APELACION;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_CONCEDE_RECURSO_APELACION;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NIEGA_APELACION;
					ls_tipoArchivo        = TipoArchivoCommon.NEGANDO_RECURSO_APELACION;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_NIEGA_RECURSO_APELACION;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_QUEJA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_CONCEDE_RECURSO_DE_QUEJA;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDE_RECURSO_QUEJA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_CONCEDE_RECURSO_DE_QUEJA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NIEGA_RECURSO_DE_QUEJA;
					ls_tipoArchivo        = TipoArchivoCommon.NIEGA_RECURSO_DE_QUEJA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_NIEGA_RECURSO_DE_QUEJA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NIEGA_REVOCATORIA_DIRECTA;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUN_NIEGA_REVOCATORIA_DIRECTA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_DE_ACLARATORIA)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NIEGA_REVOCATORIA_DIRECTA;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_COMUNICADO_RES_ACLARATORIA_SEGUNDA_INSTANCIA;
					ls_tipoArchivo        = TipoArchivoCommon.COMUNICADO;
					ls_tipoDocumental     = TipoDocumentalCommon.CITACION_NOTIFICACION;
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.ETAPA_430_AUTO_INHIBITORIO)
			{
				if(ab_resolucion)
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_AUTO_INHIBITORIO;
					ls_tipoArchivo        = TipoArchivoCommon.CONCEDE_REVOCATORIA_DIRECTA;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					// ls_idPlantilla = ConstanteCommon.PLANTILLA_COMUN_NIEGA_REVOCATORIA_DIRECTA;
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

				//
				// lmt_motivoTramite.setIdMotivo(al_motivo);
				// lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);
				//
				// terminarTurnoHistoriaYCrearEtapa(
				// lth_turnoHistoria, ldm_manager, lmt_motivoTramite, as_userId, as_remoteIp,
				// EstadoCommon.TERMINADA
				// );
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
	 * Método encargado de generar el documento de auto.
	 *
	 * @param aaa_parametros
	 *            Objeto que contiene la información para generar el pdf.
	 * @param as_userId
	 *            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el trámite.
	 * @param as_remoteIp
	 *            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el trámite.
	 * @param al_motivo
	 *            Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException
	 *             Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion visualizarCambiosResolucion(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp, long al_motivo
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

				if((lot_oficiosTexto != null) && (al_motivo > 0))
				{
					{
						String ls_antecedentes;

						ls_antecedentes = lot_oficiosTexto.getAntecedentes();

						if(!StringUtils.isValidString(ls_antecedentes))
							throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ANTECEDENTES);
					}

					if(al_motivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS)
					{
						{
							String ls_consideracion;

							ls_consideracion = lot_oficiosTexto.getPertinencia();

							if(!StringUtils.isValidString(ls_consideracion))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
						}

						{
							Long ll_diasSuspencion;

							ll_diasSuspencion = lot_oficiosTexto.getDiasSuspension();

							if(!NumericUtils.isValidLong(ll_diasSuspencion))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TERMINO_PARA_APORTAR_LAS_PRUEBAS);
						}

						{
							String ls_diasLetras;

							ls_diasLetras = lot_oficiosTexto.getDiasLetras();

							if(!StringUtils.isValidString(ls_diasLetras))
								throw new B2BException(ErrorKeys.ERROR_SIN_DIAS_LETRAS);
						}

						{
							String ls_resuelve;

							ls_resuelve = lot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}
					else if(
					    (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_DE_ACLARATORIA)
					)
					{
						{
							String ls_datoAValidar;

							ls_datoAValidar = lot_oficiosTexto.getRazon1();

							if(!StringUtils.isValidString(ls_datoAValidar))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_PRUEBAS);
						}

						{
							String ls_datoAValidar;

							ls_datoAValidar = lot_oficiosTexto.getArgumentosRecurrente();

							if(!StringUtils.isValidString(ls_datoAValidar))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_ARGUMENTOS_RECURRENTE);
						}

						if(
						    (al_motivo != MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA)
							    && (al_motivo != MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA)
							    && (al_motivo != MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA)
						)
						{
							String ls_datoAValidar;

							ls_datoAValidar = lot_oficiosTexto.getConsideracion();

							if(!StringUtils.isValidString(ls_datoAValidar))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_PRIMERA_INSTANCIA);
						}

						{
							String ls_datoAValidar;

							ls_datoAValidar = lot_oficiosTexto.getConsideracionSajr();

							if(!StringUtils.isValidString(ls_datoAValidar))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES_SAJR);
						}

						{
							String ls_resuelve;

							ls_resuelve = lot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}
					else if(
					    (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
						    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION)
					)
					{
						{
							String ls_pertinencia;

							ls_pertinencia = lot_oficiosTexto.getConsideracion();

							if(!StringUtils.isValidString(ls_pertinencia))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
						}

						{
							String ls_resuelve;

							ls_resuelve = lot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}

						{
							String ls_consideracion;

							ls_consideracion = lot_oficiosTexto.getPertinencia();

							if(!StringUtils.isValidString(ls_consideracion))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_CONSIDERACIONES);
						}
					}
					else if(al_motivo == MotivoTramiteCommon.ETAPA_430_AUTO_INHIBITORIO)
					{
						{
							String ls_consideracion;

							ls_consideracion = lot_oficiosTexto.getConsideracion();

							if(!StringUtils.isValidString(ls_consideracion))
								throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
						}

						{
							String ls_resuelve;

							ls_resuelve = lot_oficiosTexto.getResuelve();

							if(!StringUtils.isValidString(ls_resuelve))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_RESUELVE);
						}
					}

					laa_return = new TagPlantillaResolucion();

					String ls_idTurnoHistoria;

					ls_idTurnoHistoria = aaa_parametros.getIdTurnoHistoria();

					if(StringUtils.isValidString(ls_idTurnoHistoria))
						lot_oficiosTexto.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

					laa_return.setArchivo(
					    generarDocumentosSegundaInstancia(
					        lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, false, false, ldm_manager
					    )
					);
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
	 * Método encargado de llenar oficios texto con textos precargados.
	 *
	 * @param adm_manager
	 *            Argumento de tipo <code>DAOManager</code> que permite la conexión
	 *            a la base de datos.
	 * @param aot_oficiosTexto
	 *            Argumento de tipo <code>OficiosTexto</code> que contiene los datos
	 *            a mostrar en la plantilla.
	 * @param amss_data
	 *            Argumento de tipo <code>Map<String, String></code> que contiene
	 *            los textos a mostrar en la plantilla.
	 * @throws B2BException
	 *             Se genera cuando se produce una excepción.
	 */
	private synchronized void llenarOficiosTexto(
	    OficiosTexto aot_oficiosTexto, Map<String, String> amss_data, long ll_idMotivo, Turno at_turno,
	    long al_idTurnoHistoria, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(amss_data) && (aot_oficiosTexto != null))
		{
			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
				{
					if((ll_idMotivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS))
						aot_oficiosTexto.setPertinencia(
						    llenarTagsTextoSegundaInstancia(
						        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
						    )
						);
					else
						aot_oficiosTexto.setConsideracionSajr(
						    llenarTagsTextoSegundaInstancia(
						        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
						    )
						);
				}
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_RESUELVE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setResuelve(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_ANTECEDENTES_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setAntecedentes(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_CONSIDERACIONES_DESPACHO_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setConsideracion(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_DISPONE_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setRazon1(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_PERTINENCIA_PANTALLA;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setPertinencia(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}

			{
				String ls_tag;

				ls_tag = IdentificadoresCommon.TAG_OBSERVACIONES_SAJ;

				if(amss_data.containsKey(ls_tag))
					aot_oficiosTexto.setConsideracion(
					    llenarTagsTextoSegundaInstancia(
					        amss_data.get(ls_tag), at_turno, al_idTurnoHistoria, adm_manager
					    )
					);
			}
		}
	}

	/**
	 * Método encargado de llenar los tags de los textos cargados en pantalla.
	 *
	 * @param as_texto
	 *            Variable que contiene el texto.
	 * @param at_turno
	 *            Objeto que contiene el turno del proceso.
	 * @param al_idTurnoHistoria
	 *            que contiene el id turno historia dle proceso.
	 * @param adm_manager
	 *            DAOManager que controla las transacciones.
	 * @return Texto resultado al llenar los tags.
	 * @throws B2BException
	 *             Objeto de tipo B2BException, se produce cuando se encuentra algun
	 *             error controlado.
	 */
	private synchronized String llenarTagsTextoSegundaInstancia(
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

						lca_actos     = new ArrayList<Acto>();
						lca_actos     = DaoCreator.getActoDAO(adm_manager)
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

									if(StringUtils.isValidString(ls_nombreActo))
										as_texto = as_texto.replace(ls_tag, ls_nombreActo);
								}
							}
						}
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
												    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48)
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
			clh_LOGGER.error("llenarTagsTextoSegundaInstancia", lb2be_e);

			throw lb2be_e;
		}

		return limpiarTags(as_texto);
	}
}
