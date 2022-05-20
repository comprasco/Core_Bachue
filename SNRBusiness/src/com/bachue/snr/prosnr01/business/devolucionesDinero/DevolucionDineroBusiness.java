package com.bachue.snr.prosnr01.business.devolucionesDinero;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.MarcaAgua;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;
import com.bachue.snr.prosnr01.common.utils.ConversionTextos;
import com.bachue.snr.prosnr01.common.utils.Convertidor;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDevolucionDineroDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.CalidadSolicitanteDAO;
import com.bachue.snr.prosnr01.dao.acc.DevolucionDineroDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaPresentadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;

import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaPresentada;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.ui.DevolucionDineroUI;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import com.bachue.snr.prosnr04.model.acc.NotificacionPago;
import com.bachue.snr.prosnr04.model.acc.NotificacionPagoRecibida;
import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;
import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase para el manejo del negocio de devoluciones dinero para realizar consultas.
 *
 * @author Carlos Calderon
 */
public class DevolucionDineroBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(DevolucionDineroBusiness.class);

	/**
	 * Método para consultas las personas con el turno y solicitud.
	 *
	 * @param as_idTurno Turno filtro de consulta
	 * @return Collection de Persona con objetos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Persona> bandejaDatosDelInteresado(String as_idTurno)
	    throws B2BException
	{
		Collection<Persona> lcp_return;

		lcp_return = new ArrayList<Persona>();

		if(as_idTurno != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					//BÚSQUEDA ACC_ANOTACION_PREDIO_CIUDADANO
					Collection<Persona> lcp_cllPersonaAnotacionPredio;

					lcp_cllPersonaAnotacionPredio = DaoCreator.getAccAnotacionPredioCiudadanoDAO(ldm_manager)
							                                      .findPersonasByIdTurno(as_idTurno);

					if(CollectionUtils.isValidCollection(lcp_cllPersonaAnotacionPredio))
					{
						for(Persona lp_persona : lcp_cllPersonaAnotacionPredio)
						{
							if(lp_persona != null)
							{
								String ls_idPersona;

								ls_idPersona = lp_persona.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);

									if(lp_persona != null)
										lcp_return.add(lp_persona);
								}
							}
						}
					}    //SI NO EXISTEN REGISTROS, BÚESQUEDA EN ACC_SOLICITUD_INTERVINIENTE 
					else
					{
						String ls_idSolicitud;

						ls_idSolicitud = lt_turno.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Collection<Persona> lcsi_cllPersonaSolicitudInterviniente;

							lcsi_cllPersonaSolicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(
								    ldm_manager
								).findPersonasByIdSolicitud(ls_idSolicitud);

							if(CollectionUtils.isValidCollection(lcsi_cllPersonaSolicitudInterviniente))
							{
								for(Persona lp_persona : lcsi_cllPersonaSolicitudInterviniente)
								{
									if(lp_persona != null)
									{
										String ls_idPersona;

										ls_idPersona = lp_persona.getIdPersona();

										if(StringUtils.isValidString(ls_idPersona))
										{
											lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);

											if(lp_persona != null)
												lcp_return.add(lp_persona);
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

				clh_LOGGER.error("bandejaDatosDelInteresado", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		if(lcp_return.isEmpty())
			lcp_return = null;

		return lcp_return;
	}

	/**
	 * Método que busca si ya existe un documento generado de certificación recaudo.
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @return Si existe ya exixte un documento  generado de certificación recaudo
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public boolean buscarDocumentoCertificacionRecaudo(String as_idSolicitud)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_resultado;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lb_resultado     = false;

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				DevolucionDinero ldd_devolucionDinero;

				ldd_devolucionDinero = DaoCreator.getDevolucionDineroDAO(ldm_manager).findByIdSolicitud(as_idSolicitud);

				if(ldd_devolucionDinero != null)
				{
					String ls_idTurno;

					ls_idTurno = ldd_devolucionDinero.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						DocumentosSalida lds_documento;

						lds_documento     = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
								                          .findByIdTurnoTipoDocumental(
								    ls_idTurno, TipoDocumentalCommon.CERTIFICACION
								);

						lb_resultado = (lds_documento != null);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarDocumentoCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("Exception", le_e);

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarDocumentoCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_resultado;
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
	public synchronized TagPlantillaResolucion cargarDatosActoAdministrativo(
	    TagPlantillaResolucion aaa_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_actoAdministrativo;

		laa_actoAdministrativo = new TagPlantillaResolucion();

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

								laa_actoAdministrativo.setSolicitud(ls_solicitud);
							}
						}

						{
							long ll_idMotivo;

							ll_idMotivo = aaa_parametros.getIdMotivo();

							if(ll_idMotivo > 0)
							{
								TagPlantillaResolucion laa_temp;
								laa_temp = plantillaDatosPorIdMotivoActoAdministrativo(
									    ldm_manager, ll_idMotivo, lt_turno.getIdCirculo(), true
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
										lot_data.setIdSolicitud(ls_idSolicitud);

										llenarOficiosTextoActoAdministrativo(lot_data, lmss_data, ldm_manager);

										{
											Collection<PersonaNotificacion> lcpn_resultadosNotificacion;

											lcpn_resultadosNotificacion = DaoCreator.getPersonaNotificacionDAO(
												    ldm_manager
												)
													                                    .findByMaxDecisionPlantilla(
													    ls_idTurno,
													    NumericUtils.getLongWrapper(aaa_parametros.getIdEtapa())
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

												laa_actoAdministrativo.setResultadosNotificacion(
												    lcpn_resultadosNotificacion
												);
											}
										}

										laa_actoAdministrativo.setOficiosTexto(lot_data);
										laa_actoAdministrativo.setTipoArchivo(laa_temp.getTipoArchivo());
										laa_actoAdministrativo.setArchivo(
										    generarDocumentoActoAdministrativo(
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

		return laa_actoAdministrativo;
	}

	/**
	 * Método para relizar validaciones iniciales de consulta devolución.
	 *
	 * @param as_idTurno correspondiente al valor de id turno
	 * @param ab_consultaNir correspondiente al valor de consulta nir
	 * @param add_devolDinero de add devol dinero
	 * @return DevolucionesDineroUI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI consultaBandejaDevolucion(
	    String as_idTurno, boolean ab_consultaNir, DevolucionDinero add_devolDinero
	)
	    throws B2BException
	{
		DevolucionDineroUI lddui_return;

		lddui_return = new DevolucionDineroUI();

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lt_turno.getIdSolicitud();

					lddui_return.setIdTurno(as_idTurno);

					if(ab_consultaNir)
						lddui_return = consultaNir(as_idTurno, lddui_return, ldm_manager);

					validacionProceso(lt_turno, ldm_manager);

					validacionNuevaEntrada(as_idTurno, ldm_manager);

					lddui_return = validacionEtapaActualYPlazos(lt_turno, lddui_return, ldm_manager);

					if(validacionTurnoDerivadoDevolucion(as_idTurno, ldm_manager))
					{
						Collection<String> lcs_mensajes;

						lcs_mensajes = lddui_return.getMensajesPantalla();

						if(CollectionUtils.isValidCollection(lcs_mensajes))
							lcs_mensajes.add(MessagesKeys.EXISTE_DEVOLUCION_DINERO);
						else
						{
							lcs_mensajes = new ArrayList<String>();

							lcs_mensajes.add(MessagesKeys.EXISTE_DEVOLUCION_DINERO);

							lddui_return.setMensajesPantalla(lcs_mensajes);
						}
					}

					{
						String ls_idProceso;

						ls_idProceso = lt_turno.getIdProceso();

						if(StringUtils.isValidString(ls_idProceso))
						{
							boolean lb_proceso1;
							boolean lb_proceso2;

							lb_proceso1     = ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1);
							lb_proceso2     = ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2);

							if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
							{
								Collection<ActoDevolucionDinero> lcadd_actosDevolucionDinero;

								lcadd_actosDevolucionDinero = DaoCreator.getActoDAO(ldm_manager)
										                                    .findActosDevDineroByTurno(as_idTurno);

								if(CollectionUtils.isValidCollection(lcadd_actosDevolucionDinero))
								{
									if(add_devolDinero != null)
									{
										for(ActoDevolucionDinero ladd_iterador : lcadd_actosDevolucionDinero)
										{
											ActoDevolucionDinero ladd_tmp;

											ladd_iterador.setIdDevolucionDinero(
											    add_devolDinero.getIdDevolucionDinero()
											);
											ladd_iterador.setIdSolicitud(add_devolDinero.getIdSolicitud());

											ladd_tmp = DaoCreator.getActoDevolucionDineroDAO(ldm_manager)
													                 .findByIdActoIdSolicitudAndDevolucion(
													    ladd_iterador
													);

											if(ladd_tmp != null)
												ladd_iterador.setSeleccionado(true);
										}
									}

									lddui_return.setActosDevolucionDinero(lcadd_actosDevolucionDinero);
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
							}
							else if(lb_proceso1)
							{
								LiquidacionItemCertificado llic_lic;

								llic_lic = DaoCreator.getLiquidacionItemCertificadoDAO(ldm_manager)
										                 .findByIdTurnoCertificado(as_idTurno);

								if(llic_lic != null)
									lddui_return.setLiquidacionItemCertificado(llic_lic);
							}
							else if(lb_proceso2 && StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_entidadExenta;

									ls_entidadExenta = ls_solicitud.getEntidadExenta();

									if(StringUtils.isValidString(ls_entidadExenta))
									{
										if(ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S))
											throw new B2BException(ErrorKeys.ERROR_TURNO_NO_GENERO_COBRO);

										if(ls_entidadExenta.equalsIgnoreCase(EstadoCommon.N))
										{
											Liquidacion ll_liquidacion;

											ll_liquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager)
													                       .findByIdSolicitudOne(ls_idSolicitud);

											if(ll_liquidacion != null)
											{
												BigDecimal lbd_valorTotal;

												lbd_valorTotal = ll_liquidacion.getValorTotal();

												if(
												    (lbd_valorTotal != null)
													    && (lbd_valorTotal.compareTo(BigDecimal.valueOf(0D)) < 0)
												)
													throw new B2BException(ErrorKeys.ERROR_TURNO_NO_GENERO_COBRO);
											}
										}
									}
								}
							}

							if((lb_proceso1 || lb_proceso2) && StringUtils.isValidString(ls_idSolicitud))
							{
								Liquidacion ll_liquidacion;

								ll_liquidacion = new Liquidacion();
								ll_liquidacion.setIdSolicitud(ls_idSolicitud);

								lddui_return.setLiquidacion(
								    DaoCreator.getAccLiquidacionDAO(ldm_manager).detalleLiquidacion(
								        ll_liquidacion, true
								    )
								);
							}

							lddui_return.setIdProcesoAnterior(ls_idProceso);
						}
					}

					lddui_return.setIdProceso(ProcesoCommon.ID_PROCESO_4);
				}
				else
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TURNO_VALIDO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultaBandejaDevolucion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lddui_return;
	}

	/**
	 * Método para consultar Nir y pegarselo al UI.
	 *
	 * @param as_idTurno String de idTurno con el filtro a validar
	 * @param addui_devDineroUI de addui dev dinero UI
	 * @param adm_DAOmanager Objeto DAOManager para realizar consultas
	 * @return el valor de devolucion dinero UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI consultaNir(
	    String as_idTurno, DevolucionDineroUI addui_devDineroUI, DAOManager adm_DAOmanager
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idTurno) && (addui_devDineroUI != null))
		{
			String ls_nir;

			ls_nir = DaoCreator.getSolicitudDAO(adm_DAOmanager).findNirByIdTurno(as_idTurno);

			if(StringUtils.isValidString(ls_nir))
				addui_devDineroUI.setNir(ls_nir);
		}

		return addui_devDineroUI;
	}

	/**
	 * Busca los casos en estado aut para que el job les genere el certificado.
	 *
	 * @param as_remoteIp            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void devolucionDinero(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosCertificados;
		DAOManager                ldm_manager;

		lcth_turnosCertificados     = null;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_turnosCertificados = obtenerTurnosJob(
				    ConstanteCommon.JOB_DEVOLUCION_DINERO_WS_INVOKE,
				    ConstanteCommon.JOB_DEVOLUCION_DINERO_LIMITE_INTENTOS,
				    EtapaCommon.CLASIFICACION_LINEA_DE_PRODUCCION_REPARTO_DEVOLUCION_DE_DINERO, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("devolucionDinero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_turnosCertificados))
			devolucionDinero(lcth_turnosCertificados, as_remoteIp);
	}

	/**
	 * Devolucion dinero.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void devolucionDinero(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_DEVOLUCION_DINERO_BLOQUEO;
		ls_userId       = "JOB_DEVOLUCION_DINERO";

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

				clh_LOGGER.error("devolucionDinero", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						String ls_usuarioProceso;

						ls_usuarioProceso = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_manager);

						ldm_manager.setAutoCommit(true);

						if(StringUtils.isValidString(ls_usuarioProceso))
						{
							BitacoraProcesoDAO lbpd_bitacora;

							lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

							for(TurnoHistoria lth_iterador : acth_parametros)
							{
								if(lth_iterador != null)
								{
									String ls_mensaje;

									ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

									try
									{
										devolucionDinero(lbpd_bitacora, lth_iterador, as_remoteIp, ls_usuarioProceso);

										lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
									}
									catch(B2BException lb2be_e)
									{
										clh_LOGGER.error("devolucionDinero", lb2be_e);

										ls_mensaje = getErrorMessage(lb2be_e);
									}

									actualizarIntentoEnvio(
									    lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager
									);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("devolucionDinero", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("devolucionDinero", lb2be_e);

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

					clh_LOGGER.error("devolucionDinero", lb2be_e);

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
	 * Devolucion dinero.
	 *
	 * @param abpd_bitacoraProcesoDAO de abpd bitacora proceso DAO
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param ls_usuarioProceso de ls usuario proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void devolucionDinero(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String ls_usuarioProceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			{
				String ls_idSolicitud;
				long   ll_idMotivo;

				ls_idSolicitud     = ath_turnoHistoria.getIdSolicitud();
				ll_idMotivo        = 0L;

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					DevolucionDinero ldd_devolucion;

					ldd_devolucion = DaoCreator.getDevolucionDineroDAO(ldm_manager).findByIdSolicitud(ls_idSolicitud);

					if(ldd_devolucion != null)
					{
						String ls_extemporaneo;

						ls_extemporaneo = ldd_devolucion.getExtemporaneo();

						if(
						    StringUtils.isValidString(ls_extemporaneo)
							    && ls_extemporaneo.equalsIgnoreCase(IdentificadoresCommon.S)
						)
							ll_idMotivo = MotivoTramiteCommon.SOLICITUD_EXTEMPORANEA;
						else
						{
							String ls_idProceso;
							String ls_idSubProceso;

							ls_idProceso        = ath_turnoHistoria.getIdProceso();
							ls_idSubProceso     = ath_turnoHistoria.getIdSubproceso();

							if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubProceso))
							{
								if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
								{
									switch(ls_idSubProceso)
									{
										case ProcesoCommon.ID_SUBPROCESO_1:

											String ls_idTurno;
											ls_idTurno = ath_turnoHistoria.getIdTurno();

											if(StringUtils.isValidString(ls_idTurno))
											{
												TurnoDAO ltd_DAO;
												Turno    lt_turno;

												ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
												lt_turno     = ltd_DAO.findById(ls_idTurno);

												if(lt_turno != null)
												{
													String ls_idTurnoAnt;

													ls_idTurnoAnt = lt_turno.getIdTurnoAnt();

													if(StringUtils.isValidString(ls_idTurnoAnt))
													{
														Turno lt_turnoAnt;

														lt_turnoAnt = ltd_DAO.findById(ls_idTurnoAnt);

														if(lt_turnoAnt != null)
														{
															String ls_idProcesoAnt;

															ls_idProcesoAnt = lt_turnoAnt.getIdProceso();

															if(StringUtils.isValidString(ls_idProcesoAnt))
															{
																String ls_idSolicitudAnt;

																ls_idSolicitudAnt = lt_turnoAnt.getIdSolicitud();

																if(StringUtils.isValidString(ls_idSolicitudAnt))
																{
																	switch(ls_idProcesoAnt)
																	{
																		case ProcesoCommon.ID_PROCESO_6:
																		case ProcesoCommon.ID_PROCESO_1:
																			ll_idMotivo = MotivoTramiteCommon.PRESUPUESTAL_TURNO_REGISTRAL;

																			break;

																		case ProcesoCommon.ID_PROCESO_2:

																			Collection<CriteriosDeBusqueda> lccdb_criterios;
																			lccdb_criterios = DaoCreator.getCriterioBusquedaDAO(
																				    ldm_manager
																				)
																					                        .buscarCriteriosPorSolicitud(
																					    ls_idSolicitudAnt
																					);

																			if(
																			    CollectionUtils.isValidCollection(
																				        lccdb_criterios
																				    )
																			)
																			{
																				boolean                       lb_encontro;
																				Iterator<CriteriosDeBusqueda> licdb_iterador;

																				licdb_iterador     = lccdb_criterios
																						.iterator();
																				lb_encontro        = false;

																				while(
																				    licdb_iterador.hasNext()
																					    && !lb_encontro
																				)
																				{
																					CriteriosDeBusqueda lcdb_criterio;

																					lcdb_criterio = licdb_iterador.next();

																					if(licdb_iterador != null)
																					{
																						String ls_consultaNacional;

																						ls_consultaNacional     = lcdb_criterio
																								.getConsultaNacional();
																						lb_encontro             = StringUtils
																								.isValidString(
																								    ls_consultaNacional
																								)
																								&& ls_consultaNacional
																								.equalsIgnoreCase(
																								    IdentificadoresCommon.S
																								);
																					}
																				}

																				if(lb_encontro)
																					ll_idMotivo = MotivoTramiteCommon.PRESUPUESTAL_TURNO_REGISTRAL;
																				else
																					ll_idMotivo = MotivoTramiteCommon.PRESUPUESTAL_CONSULTAS_NACIONALES;
																			}
																			else
																			{
																				Object[] loa_args;

																				loa_args        = new String[1];
																				loa_args[0]     = ls_idSolicitudAnt;

																				throw new B2BException(
																				    ErrorKeys.ERROR_NO_SE_ENCONTRARON_CRITERIOS_BUSQUEDA,
																				    loa_args
																				);
																			}

																			break;

																		default:
																			break;
																	}
																}
															}
														}
													}
												}
												else
													throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

											break;

										case ProcesoCommon.ID_SUBPROCESO_2:
											ll_idMotivo = MotivoTramiteCommon.NO_PRESUPUESTAL_CONSIGNACION_ERRADA;

											break;

										case ProcesoCommon.ID_SUBPROCESO_3:
											ll_idMotivo = MotivoTramiteCommon.NO_PRESUPUESTAL_CUENTA_CUPO;

											break;

										default:
											break;
									}
								}
								else
									throw new B2BException(ErrorKeys.ID_PROCESO_INVALIDO);
							}
						}
					}
					else
					{
						Object[] loa_args;

						loa_args        = new String[1];
						loa_args[0]     = ls_idSolicitud;

						throw new B2BException(ErrorKeys.NO_EXISTE_DEVOLUCION_DINERO, loa_args);
					}
				}
				else
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);

				if(ll_idMotivo != 0L)
					terminarTurnoHistoriaYCrearEtapa(
					    ath_turnoHistoria, ldm_manager,
					    new MotivoTramite(
					        EtapaCommon.CLASIFICACION_LINEA_DE_PRODUCCION_REPARTO_DEVOLUCION_DE_DINERO, ll_idMotivo
					    ), ls_usuarioProceso, as_remoteIp, EstadoCommon.TERMINADA
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.DEVOLUCION_DINERO, lb2be_e.getLocalizedMessage(),
			    ls_usuarioProceso, as_remoteIp,
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
	 * Método encargado de enviar al responsable del acto administrativo.
	 *
	 * @param aaa_actoAdministrativo Objeto que contiene la información para enviar al responsable de acto administrativo.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized void enviarResponsableActoAdministrativo(
	    TagPlantillaResolucion aaa_actoAdministrativo, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actoAdministrativo != null) && (al_motivo > 0))
			{
				{
					Collection<TipoDocumental> lctd_tiposDocumentales;
					Solicitud                  ls_solicitud;
					SolicitudDAO               ls_solicitudDAO;

					lctd_tiposDocumentales     = aaa_actoAdministrativo.getTiposDocumentales();
					ls_solicitud               = aaa_actoAdministrativo.getSolicitud();
					ls_solicitudDAO            = DaoCreator.getSolicitudDAO(ldm_manager);

					if(ls_solicitud == null)
						ls_solicitud = ls_solicitudDAO.findSolicitudByIdTurno(aaa_actoAdministrativo.getIdTurno());

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
								lacd_accCompletitudDocumental.setIdTurno(aaa_actoAdministrativo.getIdTurno());
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
							    NumericUtils.getLongWrapper(aaa_actoAdministrativo.getIdTurnoHistoria())
							);

					if(lth_turnoHistoria != null)
					{
						long ll_idEtapa;

						ll_idEtapa = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

						lmt_motivoTramite.setIdMotivo(al_motivo);
						lmt_motivoTramite.setIdEtapaOrigen(ll_idEtapa);

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

			clh_LOGGER.error("enviarResponsableActoAdministrativo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para realizar transacciones con la base de datos para encontrar todos los registros
	 * de Constantes que coincidan con un id especifico para Decision Tramite Registral.
	 * @return devuelve el valor de la Decision Tramite Registral
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Constantes
	 */
	public synchronized Collection<String> findDecisionTramiteRegistral(String as_turnoHistoria)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		Constantes         lc_datos;
		Collection<String> lcs_return;
		String             ls_caracter;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_datos        = null;
		lcs_return      = null;
		ls_caracter     = null;

		try
		{
			if(StringUtils.isValidString(as_turnoHistoria))
			{
				TurnoHistoriaDAO lth_dao;
				lth_dao = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_turnoHistoria));
				lth_turnoHistoria = lth_dao.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idCirculo;
					ls_idCirculo = lth_turnoHistoria.getIdCirculoUsuario();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						if(ls_idCirculo.equals(ConstanteCommon.CIRCULO_CENTRAL_SNR))
							lc_datos = DaoCreator.getConstantesDAO(ldm_manager)
									                 .findById(
									    ConstanteCommon.DECISIONES_TRAMITE_DEVOLUCION_DINERO_CENTRAL
									);
						else
							lc_datos = DaoCreator.getConstantesDAO(ldm_manager)
									                 .findById(ConstanteCommon.DECISIONES_TRAMITE_DEVOLUCION_DINERO);

						if(lc_datos != null)
						{
							ls_caracter = lc_datos.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
								lcs_return = separarPorSimboloComa(ls_caracter, true);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_return;
	}

	/**
	 * Método para consultar devolución dinero por id solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return DevolucionDinero objeto consultado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */

	/**
	 * Método para consultar devolución dinero por id solicitud
	 *
	 * @param as_idSolicitud String con filtro de búsqueda
	 * @return DevolucionDinero objeto consultado
	 * @throws B2BException
	 */
	public DevolucionDinero findDevolucionDineroByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		DevolucionDinero ldd_devolucionDinero;

		ldd_devolucionDinero = new DevolucionDinero();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ldd_devolucionDinero = DaoCreator.getDevolucionDineroDAO(ldm_manager).findByIdSolicitud(as_idSolicitud);

				if(ldd_devolucionDinero != null)
				{
					String ls_idTipoEntidadRecaudadora;
					ls_idTipoEntidadRecaudadora = ldd_devolucionDinero.getIdEntidadRecaudadora();

					if(StringUtils.isValidString(ls_idTipoEntidadRecaudadora))
					{
						EntidadRecaudadora ler_entidadRecaudadora;

						ler_entidadRecaudadora = DaoCreator.getEntidadRecaudadoraDAO(ldm_manager)
								                               .findByCodigo(ls_idTipoEntidadRecaudadora);

						if(ler_entidadRecaudadora != null)
							ldd_devolucionDinero.setNombreEntidadRecaudadora(
							    ler_entidadRecaudadora.getNombreEntidadRecaudadora()
							);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findDevolucionDineroByIdSolicitud", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldd_devolucionDinero;
	}

	/**
	 * Genera el archivo de certificado recaudo.
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @param adm_manager Conexión a la base de datos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param ads_documentoSalida de ads documento salida
	 * @return cadena de bytes contenedora de la información del archivo
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public byte[] generarCertificacionRecaudo(
	    String as_idSolicitud, DAOManager adm_manager, String as_userId, String as_remoteIp,
	    DocumentosSalida ads_documentoSalida
	)
	    throws B2BException
	{
		byte[] lba_documento;

		lba_documento = null;

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp))
			{
				ConstantesDAO lcd_DAO;
				String        ls_plantilla;
				SolicitudDAO  lsd_solicitudDAO;
				Solicitud     ls_solicitud;

				lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(adm_manager);
				lcd_DAO              = DaoCreator.getConstantesDAO(adm_manager);
				ls_solicitud         = lsd_solicitudDAO.findById(as_idSolicitud);
				ls_plantilla         = null;

				if(ls_solicitud == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

				{
					Constantes lc_constante;
					byte[]     lba_plantilla;

					lc_constante = lcd_DAO.findImagen(new Constantes(ConstanteCommon.PLANTILLA_CERTIFICACION_RECAUDO));

					if(lc_constante == null)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION;

						throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
					}

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla != null)
						ls_plantilla = new String(lba_plantilla);
				}

				if(StringUtils.isValidString(ls_plantilla))
				{
					ls_plantilla = resolverTagsSNRReciboLiquidacion(adm_manager, ls_plantilla);

					if(ls_plantilla.contains(TagCommon.TAG_TIPO_DEVOLUCION))
					{
						SubprocesoDAO lspd_DAO;
						Subproceso    lsp_subproceso;

						lspd_DAO           = DaoCreator.getSubprocesoDAO(adm_manager);
						lsp_subproceso     = new Subproceso();
						lsp_subproceso.setIdSubproceso(ls_solicitud.getIdSubproceso());
						lsp_subproceso.setIdProceso(ls_solicitud.getIdProceso());
						lsp_subproceso = lspd_DAO.findById(lsp_subproceso);

						if(lsp_subproceso != null)
							ls_plantilla = reemplazarTagEnPlantilla(
								    ls_plantilla, TagCommon.TAG_TIPO_DEVOLUCION, lsp_subproceso.getNombre()
								);
					}

					if(ls_plantilla.contains(TagCommon.TAG_NIR))
					{
						String ls_nir;

						ls_nir = ls_solicitud.getNir();

						if(StringUtils.isValidString(ls_nir))
							ls_plantilla = ls_plantilla.replace(TagCommon.TAG_NIR, ls_nir);
					}

					{
						DevolucionDinero ldd_devolucionDinero;
						String           ls_nirDevolucion;
						String           ls_turnosAsociados;
						String           ls_procesoDevolucion;

						ldd_devolucionDinero     = DaoCreator.getDevolucionDineroDAO(adm_manager)
								                                 .findByIdSolicitud(as_idSolicitud);
						ls_nirDevolucion         = null;
						ls_turnosAsociados       = null;
						ls_procesoDevolucion     = null;

						if(ldd_devolucionDinero != null)
						{
							TurnoDAO ltd_turnoDAO;
							Turno    lt_turno;

							ltd_turnoDAO     = DaoCreator.getTurnoDAO(adm_manager);
							lt_turno         = ltd_turnoDAO.findById(ldd_devolucionDinero.getIdTurno());

							if(lt_turno != null)
							{
								Solicitud ls_solicitudAnterior;

								ads_documentoSalida.setIdTurno(lt_turno.getIdTurno());
								ls_solicitudAnterior = lsd_solicitudDAO.findById(lt_turno.getIdSolicitud());

								if(ls_solicitudAnterior != null)
								{
									Proceso lp_proceso;

									lp_proceso = DaoCreator.getProcesoDAO(adm_manager)
											                   .findById(ls_solicitudAnterior.getIdProceso());

									if(lp_proceso != null)
									{
										StringBuilder lsb_proceso;

										lsb_proceso = new StringBuilder(lp_proceso.getIdProceso());

										lsb_proceso.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
										lsb_proceso.append(lp_proceso.getNombre());

										ls_procesoDevolucion = lsb_proceso.toString();
									}

									ls_nirDevolucion = ls_solicitudAnterior.getNir();
								}

								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = new CirculoRegistral();

									lcr_circuloRegistral.setIdCirculo(lt_turno.getIdCirculo());

									lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
											                             .findById(lcr_circuloRegistral);

									if(lcr_circuloRegistral != null)
									{
										if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
										{
											String ls_nombre;

											ls_nombre = lcr_circuloRegistral.getNombre();

											if(StringUtils.isValidString(ls_nombre))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_NOMBRE_ORIP, ls_nombre
													);
										}
									}
								}
							}

							{
								TurnoHistoria    lth_turnoHistoria;
								TurnoHistoriaDAO lthd_turnoHistoriaDao;
								String           ls_consecutivoOficio;
								String           ls_referenciaSalida;
								Date             ld_fechaOficio;

								lthd_turnoHistoriaDao     = DaoCreator.getTurnoHistoriaDAO(adm_manager);
								ls_consecutivoOficio      = null;
								ls_referenciaSalida       = null;
								ld_fechaOficio            = null;
								lth_turnoHistoria         = lthd_turnoHistoriaDao.findByIdTurno(
									    ldd_devolucionDinero.getIdTurnoDevolucion()
									);

								if(lth_turnoHistoria != null)
								{
									ads_documentoSalida.setIdTurnoHistoria(
									    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
									);

									if(ls_plantilla.contains(TagCommon.TAG_OFICIO))
									{
										NumeracionOficiosCirculo lnoc_numeracionOficios;
										lnoc_numeracionOficios = findNumeracionOficiosCirculo(
											    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
											);

										if(lnoc_numeracionOficios != null)
										{
											ls_consecutivoOficio     = lnoc_numeracionOficios.getConsecutivoGenerado();

											ls_plantilla     = ls_plantilla.replace(
												    TagCommon.TAG_OFICIO, ls_consecutivoOficio
												);

											ld_fechaOficio = new Date();
										}
									}

									if(ls_plantilla.contains(TagCommon.TAG_REFERENCIA_SALIDA))
									{
										ls_referenciaSalida = generarIdCorrespondencia(
											    lth_turnoHistoria, adm_manager, false
											);

										if(StringUtils.isValidString(ls_referenciaSalida))
											ls_plantilla = ls_plantilla.replace(
												    TagCommon.TAG_REFERENCIA_SALIDA, ls_referenciaSalida
												);
									}
								}

								ads_documentoSalida.setReferenciaSalida(ls_referenciaSalida);
								ads_documentoSalida.setConsecutivoOficio(ls_consecutivoOficio);
								ads_documentoSalida.setFechaOficio(ld_fechaOficio);

								ls_plantilla = reemplazarTagEnPlantilla(
									    ls_plantilla, TagCommon.TAG_FECHA_SISTEMA, ld_fechaOficio
									);
							}

							if(ls_plantilla.contains(TagCommon.TAG_ACTOS_DEV))
							{
								ActoDevolucionDineroDAO laddd_DAO;
								ActoDevolucionDinero    ladd_actoDevolucion;

								laddd_DAO               = DaoCreator.getActoDevolucionDineroDAO(adm_manager);
								ladd_actoDevolucion     = laddd_DAO.findByIdDevoluciondinero(
									    ldd_devolucionDinero.getIdDevolucionDinero()
									);

								if(ladd_actoDevolucion != null)
									ls_plantilla = reemplazarTagEnPlantilla(
										    ls_plantilla, TagCommon.TAG_ACTOS_DEV,
										    ladd_actoDevolucion.getIdActoDevolucionDinero()
										);
							}
						}

						{
							if(StringUtils.isValidString(as_userId))
							{
								UsuarioDAO lud_DAO;
								Usuario    lu_usuario;

								lud_DAO        = DaoCreator.getUsuarioDAO(adm_manager);
								lu_usuario     = new Usuario();
								lu_usuario.setIdUsuario(as_userId);
								lu_usuario = lud_DAO.findById(lu_usuario);

								if(ls_plantilla.contains(TagCommon.TAG_CARGO_USUARIO))
								{
									if(lu_usuario != null)
									{
										if(lu_usuario.getCargo() != null)
											ls_plantilla = reemplazarTagEnPlantilla(
												    ls_plantilla, TagCommon.TAG_CARGO_USUARIO, lu_usuario.getCargo()
												);

										{
											String        ls_nombreUsuario;
											StringBuilder lsb_nombres;

											lsb_nombres = new StringBuilder();

											if(StringUtils.isValidString(lu_usuario.getPrimerNombre()))
											{
												lsb_nombres.append(lu_usuario.getPrimerNombre());
												lsb_nombres.append(IdentificadoresCommon.ESPACIO_VACIO);
											}

											if(StringUtils.isValidString(lu_usuario.getSegundoNombre()))
											{
												lsb_nombres.append(lu_usuario.getSegundoNombre());
												lsb_nombres.append(IdentificadoresCommon.ESPACIO_VACIO);
											}

											if(StringUtils.isValidString(lu_usuario.getPrimerApellido()))
											{
												lsb_nombres.append(lu_usuario.getPrimerApellido());
												lsb_nombres.append(IdentificadoresCommon.ESPACIO_VACIO);
											}

											if(StringUtils.isValidString(lu_usuario.getSegundoApellido()))
												lsb_nombres.append(lu_usuario.getSegundoApellido());

											ls_nombreUsuario = lsb_nombres.toString();

											if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_USUARIO))
												ls_plantilla = reemplazarTagEnPlantilla(
													    ls_plantilla, TagCommon.TAG_NOMBRE_USUARIO, ls_nombreUsuario
													);

											if(ls_plantilla.contains(TagCommon.TAG_CARGO))
												ls_plantilla = reemplazarTagEnPlantilla(
													    ls_plantilla, TagCommon.TAG_CARGO, ls_nombreUsuario
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_LARGA))
						{
							Date   ld_fecha;
							String ls_fechaActual;

							ld_fecha           = new Date();
							ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

							if(StringUtils.isValidString(ls_fechaActual))
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_LARGA, ls_fechaActual.toUpperCase()
									);
						}

						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_NIR_DEVOLUCION, ls_nirDevolucion
							);

						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_TURNOS_ASOC_DEV, ls_turnosAsociados
							);

						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, TagCommon.TAG_PROCESO_DEV, ls_procesoDevolucion
							);

						{
							Map<String, String> lmss_datos;

							lmss_datos = finalizarPlantilla(ls_plantilla, null, true, adm_manager);

							if(CollectionUtils.isValidCollection(lmss_datos))
							{
								ls_plantilla = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

								if(StringUtils.isValidString(ls_plantilla))
									lba_documento = new PDFGenerator().convertirRTFToPDF(
										    ls_plantilla.getBytes(), adm_manager
										);
							}
						}

						if(lba_documento == null)
							throw new B2BException(ErrorKeys.NO_SE_GENERO_CERTIFICACION_RECAUDO);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("Exception", le_e);

			clh_LOGGER.error("generarCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}

		return lba_documento;
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
	public synchronized byte[] generarDocumentoActoAdministrativo(
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

							laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoActoAdministrativo(
								    adm_manager, al_motivo, lth_turnoHistoria.getIdCirculoUsuario(), true
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
								String              ls_idCorrespondencia;
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
								ls_idCorrespondencia      = null;
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
										String ls_consideraciones;
										String ls_consideracion;
										String ls_resuelve;
										String ls_decisionTramiteRegistral;
										Long   ll_diasSuspension;

										ls_consideraciones              = null;
										ls_consideracion                = null;
										ls_resuelve                     = null;
										ll_diasSuspension               = null;
										ls_decisionTramiteRegistral     = null;

										ls_consideraciones              = aot_oficioTextoData.getPertinencia();
										ls_consideracion                = aot_oficioTextoData.getConsideracion();
										ls_resuelve                     = aot_oficioTextoData.getResuelve();
										ll_diasSuspension               = aot_oficioTextoData.getDiasSuspension();
										ls_decisionTramiteRegistral     = aot_oficioTextoData
												.getDecisionTramiteRegistral();

										ls_tag = "<TAG_DECISIONES_TRAMITE_REGISTRAL>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_decisionTramiteRegistral)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_decisionTramiteRegistral);

										ls_tag = "<TAG_DECISIONES_TRAMITE_REGISTRAL_CENTRAL>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_decisionTramiteRegistral)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_decisionTramiteRegistral);

										ls_tag = "<TAG_CONSIDERACIONESPANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_CONSIDERACIONES_PANTALLA>";

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_consideraciones)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideraciones);

										ls_tag = "<TAG_CONSIDERACIONES_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_consideracion))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_consideracion);

										ls_tag = "<TAG_RESUELVE_PANTALLA>";

										if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_resuelve))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_resuelve);

										ls_tag = "<TAG_DIAS_SUSPENSION>";

										if(ls_plantilla.contains(ls_tag) && NumericUtils.isValidLong(ll_diasSuspension))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(ll_diasSuspension)
												);
									}

									ls_tag = "<TAG_SUBPROCESO>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(StringUtils.isValidString(ls_idSolicitud))
										{
											Solicitud ls_solicitud;

											ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
													                     .findById(ls_idSolicitud);

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
													String ls_idSubproceso;
													ls_idSubproceso         = lsp_subproceso.getIdSubproceso();
													ls_nombreSubproceso     = lsp_subproceso.getNombre();

													if(StringUtils.isValidString(ls_nombreSubproceso))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_nombreSubproceso
															);

													if(StringUtils.isValidString(ls_idSubproceso))
													{
														Constantes lc_constanteSubproceso;
														lc_constanteSubproceso = null;

														if(ls_idSubproceso.equals(IdentificadoresCommon.NUM1))
														{
															ls_tag                     = "<TAG_SUBPROCESO1>";
															lc_constanteSubproceso     = lc_DAO.findById(
																    "TAG_SUBPROCESO1"
																);

															if(lc_constanteSubproceso != null)
															{
																if(
																    StringUtils.isValidString(
																	        lc_constanteSubproceso.getCaracter()
																	    )
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, lc_constanteSubproceso.getCaracter()
																		);
															}
														}
														else
														{
															ls_tag                     = "<TAG_SUBPROCESO2>";
															lc_constanteSubproceso     = lc_DAO.findById(
																    "TAG_SUBPROCESO2"
																);

															if(lc_constanteSubproceso != null)
															{
																if(
																    StringUtils.isValidString(
																	        lc_constanteSubproceso.getCaracter()
																	    )
																)
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, lc_constanteSubproceso.getCaracter()
																		);
															}
														}
													}
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

												ls_tag = "<TAG_NOMBRE_ORIP>";

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
											ls_tag = TagCommon.TAG_TIPOS_DOCUMENTALES;

											if(ls_plantilla.contains(ls_tag))
											{
												Collection<AccCompletitudDocumental> lcacd_completitudesDocumentales;

												lcacd_completitudesDocumentales = DaoCreator.getAccCompletitudDocumentalDAO(
													    adm_manager
													).findAllByIdSolicitud(ls_idSolicitud);

												if(CollectionUtils.isValidCollection(lcacd_completitudesDocumentales))
												{
													TipoDocumentalDAO ltdd_DAO;
													StringBuilder     lsb_builder;

													ltdd_DAO        = DaoCreator.getTipoDocumentalDAO(adm_manager);
													lsb_builder     = new StringBuilder();

													for(AccCompletitudDocumental lacd_iterador : lcacd_completitudesDocumentales)
													{
														if(lacd_iterador != null)
														{
															String ls_idTipoDocumental;

															ls_idTipoDocumental = lacd_iterador.getIdTipoDocumental();

															if(StringUtils.isValidString(ls_idTipoDocumental))
															{
																TipoDocumental ltd_tipoDocumetal;

																ltd_tipoDocumetal = ltdd_DAO.findById(
																	    ls_idTipoDocumental
																	);

																if(ltd_tipoDocumetal != null)
																{
																	String ls_nombre;

																	ls_nombre = ltd_tipoDocumetal.getNombre();

																	if(StringUtils.isValidString(ls_nombre))
																	{
																		lsb_builder.append(ls_idTipoDocumental);
																		lsb_builder.append(
																		    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
																		);
																		lsb_builder.append(ls_nombre);
																		lsb_builder.append(
																		    IdentificadoresCommon.SIMBOLO_COMA
																		);
																	}
																}
															}
														}
													}

													{
														String ls_texto;

														ls_texto = lsb_builder.toString();

														if(StringUtils.isValidString(ls_texto))
														{
															String ls_ultimo;

															ls_ultimo = ls_texto.substring(ls_texto.length() - 2);

															if(
															    StringUtils.isValidString(ls_ultimo)
																    && ls_ultimo.equalsIgnoreCase(
																        IdentificadoresCommon.SIMBOLO_COMA
																    )
															)
															{
																ls_texto = ls_texto.substring(0, ls_texto.length() - 2);

																if(StringUtils.isValidString(ls_texto))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_texto
																		);
															}
														}
													}
												}
											}

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

													ls_tag      = "<TAG_PERSONA_INTERESADA>";
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

													ls_tag = "<TAG_PERSONA_INTERVINIENTE>";

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

													{
														DevolucionDinero ld_devolucionDinero;
														ld_devolucionDinero = DaoCreator.getDevolucionDineroDAO(
															    adm_manager
															).findByIdSolicitud(ls_solicitud.getIdSolicitud());

														if(ld_devolucionDinero != null)
														{
															ls_tag = "<TAG_TIPO_CUENTA>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_tipoCuenta;

																ls_tipoCuenta = ld_devolucionDinero.getTipoCuenta();

																if(StringUtils.isValidString(ls_tipoCuenta))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_tipoCuenta
																		);
															}

															ls_tag = "<TAG_NUMERO_CUENTA>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_tipoCuenta;

																ls_tipoCuenta = ld_devolucionDinero.getNumeroCuenta();

																if(StringUtils.isValidString(ls_tipoCuenta))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_tipoCuenta
																		);
															}

															ls_tag = "<TAG_ENTIDAD_RECAUDADORA>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_tipoCuenta;

																ls_tipoCuenta = ld_devolucionDinero
																		.getIdEntidadRecaudadora();

																if(StringUtils.isValidString(ls_tipoCuenta))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_tipoCuenta
																		);
															}
														}
													}
												}
											}
										}
									}

									ls_tag = "<TAG_USUARIO_FIRMA_SUSPENSION>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = getFirmaMasivaBusiness()
												               .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tag,
												    "<TAG_CARGO_FIRMA_SUSPENSION>"
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

												String ls_expediente;

												ls_expediente  = lt_turno.getExpediente();

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

												NumeracionOficiosCirculo lnoc_numeracionOficios;

												lnoc_numeracionOficios = findNumeracionOficiosCirculo(
													    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
													);

												if(lnoc_numeracionOficios != null)
												{
													ls_consecutivoOficio = lnoc_numeracionOficios.getConsecutivoGenerado();

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
	 * Método encargado de generar el documento certificación recaudo.
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public void generarDocumentoCertificacionRecaudo(String as_idSolicitud, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DocumentosSalida lds_documento;
			byte[]           lba_documento;

			lds_documento     = new DocumentosSalida();

			lba_documento = generarCertificacionRecaudo(
				    as_idSolicitud, ldm_manager, as_userId, as_remoteIp, lds_documento
				);
			lds_documento.setIdTipoDocumental(TipoDocumentalCommon.CERTIFICACION);
			lds_documento.setEstado(EstadoCommon.ACTIVO);
			lds_documento.setRepositorio(RepositorioCommon.FINAL);
			lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
			lds_documento.setTipo(TipoArchivoCommon.CERTIFCACION_RECAUDO);
			lds_documento.setIdSolicitud(as_idSolicitud);

			guardarDocumentoOWCC(
			    insertarDocumentoSalida(lba_documento, lds_documento, as_userId, as_remoteIp, ldm_manager), ldm_manager,
			    as_userId, as_remoteIp
			);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("Exception", le_e);

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoCertificacionRecaudo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de generar los documentos de acto administrativo.
	 *
	 * @param aaa_actoAdministrativo Objeto que contiene la información para generar el pdf.
	 * @param as_userId Variable de tipo String que contiene el id del usuario que está realizando el trámite.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que está realizando el trámite.
	 * @param al_motivo Variable de tipo long que contiene la decision seleccionada.
	 * @return Variable de tipo byte array que contiene el documento generado.
	 * @throws B2BException Se genera cuando se presenta una excepción.
	 */
	public synchronized TagPlantillaResolucion generarDocumentosActoAdministrativo(
	    TagPlantillaResolucion aaa_actoAdministrativo, String as_userId, String as_remoteIp, long al_motivo
	)
	    throws B2BException
	{
		TagPlantillaResolucion laa_return;
		DAOManager             ldm_manager;

		laa_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if((aaa_actoAdministrativo != null) && (al_motivo > 0))
			{
				OficiosTexto lot_oficiosTexto;

				lot_oficiosTexto = aaa_actoAdministrativo.getOficiosTexto();

				if(lot_oficiosTexto != null)
				{
					String ls_tipoArchivo;

					ls_tipoArchivo = aaa_actoAdministrativo.getTipoArchivo();

					if(StringUtils.isValidString(ls_tipoArchivo))
					{
						Long             ll_idTurnoHistoria;
						OficiosTextoDAO  lotd_DAO;
						TurnoHistoriaDAO lth_DAO;

						lotd_DAO               = DaoCreator.getOficiosTextoDAO(ldm_manager);
						lth_DAO                = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						ll_idTurnoHistoria     = NumericUtils.getLongWrapper(
							    aaa_actoAdministrativo.getIdTurnoHistoria()
							);

						if(NumericUtils.isValidLong(ll_idTurnoHistoria))
						{
							TurnoHistoria lth_turnoHistoria;
							lth_turnoHistoria = lth_DAO.findById(ll_idTurnoHistoria);

							if(lth_turnoHistoria != null)
							{
								TagPlantillaResolucion laa_actuacionesAdministrativas;

								laa_actuacionesAdministrativas = plantillaDatosPorIdMotivoActoAdministrativo(
									    ldm_manager, al_motivo, lth_turnoHistoria.getIdCirculoUsuario(), true
									);

								if(laa_actuacionesAdministrativas != null)
								{
									String ls_idPlantilla;

									ls_idPlantilla = laa_actuacionesAdministrativas.getIdPlantilla();

									if(StringUtils.isValidString(ls_idPlantilla))
									{
										lotd_DAO.deleteByTurnoHistoriaPlantilla(ll_idTurnoHistoria, ls_idPlantilla);
										DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											          .deleteByIdTurnoHistoriaTipo(
											    NumericUtils.getLong(ll_idTurnoHistoria),
											    laa_actuacionesAdministrativas.getTipoArchivo()
											);
										lot_oficiosTexto.setPlantilla(ls_idPlantilla);
									}
								}

								lot_oficiosTexto.setIdTurnoHistoria(ll_idTurnoHistoria);
								lot_oficiosTexto.setIdUsuarioCreacion(as_userId);
								lot_oficiosTexto.setIpCreacion(as_remoteIp);

								lotd_DAO.insertOrUpdate(lot_oficiosTexto, true);
							}

							byte[] lba_archivo;

							lba_archivo = generarDocumentoActoAdministrativo(
								    lot_oficiosTexto, as_userId, as_remoteIp, al_motivo, true, false, ldm_manager
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
	 *  Método para salvar actos de devolución de dinero y terminar la etapa con su correspondiente id motivo.
	 *
	 * @param addui_datosSalvarUI Objeto contenedor de información a salvar
	 * @param as_userId Usuario del proceso
	 * @param as_remoteIp Ip del proceso
	 * @return el valor de devolucion dinero UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI salvarActosDevolucionDinero(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(addui_datosSalvarUI != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria                    lth_turnoHistoria;
				DevolucionDinero                 ldd_devolucionDinero;
				Collection<ActoDevolucionDinero> lcadd_actos;
				Turno                            lt_turnoDevolucion;

				ldd_devolucionDinero     = addui_datosSalvarUI.getDevolucionDinero();
				lth_turnoHistoria        = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                                 .findById(
						    NumericUtils.getLongWrapper(addui_datosSalvarUI.getIdTurnoHistoria())
						);
				lt_turnoDevolucion       = DaoCreator.getTurnoDAO(ldm_manager)
						                                 .findById(ldd_devolucionDinero.getIdTurnoDevolucion());

				lcadd_actos = addui_datosSalvarUI.getActosDevolucionDinero();

				if((lth_turnoHistoria != null))
				{
					String ls_idDevolucionDinero;
					String ls_procesoTurnoDevolucion;

					ls_procesoTurnoDevolucion     = (lt_turnoDevolucion != null)
						? StringUtils.getStringNotNull(lt_turnoDevolucion.getIdProceso()) : "";
					ls_idDevolucionDinero         = addui_datosSalvarUI.getIdDevolucionDinero();

					if(StringUtils.isValidString(ls_idDevolucionDinero))
					{
						boolean lb_unoSeleccionado;

						lb_unoSeleccionado = false;

						addui_datosSalvarUI.setIdDevolucionDinero(ls_idDevolucionDinero);

						if(ls_procesoTurnoDevolucion.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
						{
							if(CollectionUtils.isValidCollection(lcadd_actos))
							{
								//ACTUALIZACIÓN DE ACTOS SELECCIONADOS
								for(ActoDevolucionDinero ladd_tmp : lcadd_actos)
								{
									if((ladd_tmp != null))
									{
										if(ladd_tmp.isSeleccionado())
										{
											lb_unoSeleccionado = true;

											ActoDevolucionDinero ladd_actoDevTMP;

											ladd_tmp.setIdDevolucionDinero(ls_idDevolucionDinero);
											ladd_tmp.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

											ladd_actoDevTMP = DaoCreator.getActoDevolucionDineroDAO(ldm_manager)
													                        .findByIdActoIdSolicitudAndDevolucion(
													    ladd_tmp
													);

											if(ladd_actoDevTMP != null)
											{
												ladd_tmp.setIdUsuarioModificacion(as_userId);
												ladd_tmp.setIpModificacion(as_remoteIp);

												DaoCreator.getActoDevolucionDineroDAO(ldm_manager).update(ladd_tmp);
											}
											else
											{
												ladd_tmp.setIdUsuarioCreacion(as_userId);
												ladd_tmp.setIpCreacion(as_remoteIp);
												DaoCreator.getActoDevolucionDineroDAO(ldm_manager).insert(ladd_tmp);
											}
										}
										else
										{
											ActoDevolucionDinero ladd_actoDevTMP;

											ladd_tmp.setIdDevolucionDinero(ls_idDevolucionDinero);
											ladd_tmp.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

											ladd_actoDevTMP = DaoCreator.getActoDevolucionDineroDAO(ldm_manager)
													                        .findByIdActoIdSolicitudAndDevolucion(
													    ladd_tmp
													);

											if(ladd_actoDevTMP != null)
												DaoCreator.getActoDevolucionDineroDAO(ldm_manager).delete(ladd_tmp);
										}
									}
								}
							}

							if(!lb_unoSeleccionado)
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_ACTO);
						}

						TurnoHistoria lth_turnoHistoriaAnt;
						lth_turnoHistoriaAnt = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                             .findByIdAnterior(lth_turnoHistoria);

						if(lth_turnoHistoriaAnt != null)
						{
							long ll_idMotivoAnt;
							long ll_idEtapaAnt;

							ll_idMotivoAnt     = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdMotivo());
							ll_idEtapaAnt      = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdEtapa());

							if(
							    ll_idEtapaAnt == EtapaCommon.CLASIFICACION_LINEA_DE_PRODUCCION_REPARTO_DEVOLUCION_DE_DINERO
							)
							{
								long ll_idMotivoFinal;
								ll_idMotivoFinal = 0;

								if(ll_idMotivoAnt == MotivoTramiteCommon.PRESUPUESTAL_TURNO_REGISTRAL)
									ll_idMotivoFinal = MotivoTramiteCommon.REVISION_PRESUPUESTAL_TURNO_REGISTRAL;
								else if(ll_idMotivoAnt == MotivoTramiteCommon.PRESUPUESTAL_CONSULTAS_NACIONALES)
									ll_idMotivoFinal = MotivoTramiteCommon.REVISION_PRESUPUESTAL_CONSULTAS_NACIONALES;
								else if(ll_idMotivoAnt == MotivoTramiteCommon.NO_PRESUPUESTAL_CONSIGNACION_ERRADA)
									ll_idMotivoFinal = MotivoTramiteCommon.REVISION_PRESUPUESTAL_CONSIGNACION_ERRADA;

								terminarTurnoHistoriaYCrearEtapa(
								    lth_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO, ll_idMotivoFinal
								    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
								);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarActosDevolucionDinero", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return addui_datosSalvarUI;
	}

	/**
	 * Método para validar y salvar completitud documental.
	 *
	 * @param acacd_completitudDocumental Colección de  AccCompletitudDocumental
	 * @param aso_solicitud Con información de la solicitud
	 * @param adm_manager Conexión a base de datos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCompletitudDocumental(
	    Collection<AccCompletitudDocumental> acacd_completitudDocumental, Solicitud aso_solicitud,
	    DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acacd_completitudDocumental) && (aso_solicitud != null))
		{
			String ls_idCalidadSolicitante;

			ls_idCalidadSolicitante = aso_solicitud.getIdCalidadSolicitante();

			if(StringUtils.isValidString(ls_idCalidadSolicitante))
			{
				DevolucionDinero ldd_devolucionDinero;
				boolean          lb_validarTipoDocumental3;
				boolean          lb_obtieneTipoDocumental3;
				boolean          lb_obtieneTipoDocumental8;
				boolean          lb_apoderadoPoder;
				boolean          lb_apoderadoCedulaCiudadania;
				boolean          lb_interesadoCedulaCiudadania;

				ldd_devolucionDinero              = DaoCreator.getDevolucionDineroDAO(adm_manager)
						                                          .findByIdSolicitud(aso_solicitud.getIdSolicitud());
				lb_validarTipoDocumental3         = false;
				lb_obtieneTipoDocumental3         = false;
				lb_obtieneTipoDocumental8         = false;
				lb_apoderadoPoder                 = false;
				lb_apoderadoCedulaCiudadania      = false;
				lb_interesadoCedulaCiudadania     = false;

				if(ldd_devolucionDinero != null)
				{
					String ls_idPersonaInterviniente;
					String ls_idPersonaTitularCuenta;

					ls_idPersonaInterviniente     = ldd_devolucionDinero.getIdPersonaInterviniente();
					ls_idPersonaTitularCuenta     = ldd_devolucionDinero.getIdPersonaTitularCuenta();

					if(
					    StringUtils.isValidString(ls_idPersonaInterviniente)
						    && StringUtils.isValidString(ls_idPersonaTitularCuenta)
						    && !ls_idPersonaInterviniente.equalsIgnoreCase(ls_idPersonaTitularCuenta)
					)
						lb_validarTipoDocumental3 = true;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				for(AccCompletitudDocumental lacd_cd : acacd_completitudDocumental)
				{
					if(lacd_cd != null)
					{
						String ls_observaciones;

						ls_observaciones = lacd_cd.getObservaciones();

						if(!StringUtils.isValidString(ls_observaciones))
						{
							String  ls_medioRecepcion;
							boolean lb_seleccionado;

							lb_seleccionado       = lacd_cd.isSeleccionado();
							ls_medioRecepcion     = lacd_cd.getMedioRecepcion();

							if(!lb_seleccionado)
								throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_OBLIGATORIEDAD);
							else
								lacd_cd.setObligatorioTipoDocumental(EstadoCommon.S);

							if(!StringUtils.isValidString(ls_medioRecepcion))
								throw new B2BException(ErrorKeys.ERROR_SIN_MEDIO_RECEPCION);
						}

						{
							String ls_idTipoDocumental;

							ls_idTipoDocumental = lacd_cd.getIdTipoDocumental();

							if(StringUtils.isValidString(ls_idTipoDocumental))
							{
								if(ls_idTipoDocumental.equalsIgnoreCase(TipoDocumentalCommon.CERTIFICACION))
									lb_obtieneTipoDocumental8 = true;

								if(
								    lb_validarTipoDocumental3
									    && ls_idTipoDocumental.equalsIgnoreCase(TipoDocumentalCommon.AUTORIZACION)
								)
									lb_obtieneTipoDocumental3 = true;

								if(ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO))
								{
									if(ls_idTipoDocumental.equalsIgnoreCase(TipoDocumentalCommon.PODER))
										lb_apoderadoPoder = true;

									if(ls_idTipoDocumental.equalsIgnoreCase(TipoDocumentalCommon.CEDULA_CIUDADANIA))
										lb_apoderadoCedulaCiudadania = true;
								}
								else if(
								    ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERESADO)
									    && ls_idTipoDocumental.equalsIgnoreCase(TipoDocumentalCommon.CEDULA_CIUDADANIA)
								)
									lb_interesadoCedulaCiudadania = true;
							}
						}
					}
				}

				if(!lb_obtieneTipoDocumental8)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = IdentificadoresCommon.CERTIFICACION;

					throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO, loa_messageArgs);
				}

				if(lb_validarTipoDocumental3 && !lb_obtieneTipoDocumental3)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = IdentificadoresCommon.AUTORIZACION;

					throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO, loa_messageArgs);
				}

				if(ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO))
				{
					if(!lb_apoderadoPoder)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = IdentificadoresCommon.PODER;

						throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO, loa_messageArgs);
					}

					if(!lb_apoderadoCedulaCiudadania)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = IdentificadoresCommon.CEDULA_CIUDADANIA;

						throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO, loa_messageArgs);
					}
				}
				else if(
				    ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERESADO)
					    && !lb_interesadoCedulaCiudadania
				)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = IdentificadoresCommon.CEDULA_CIUDADANIA;

					throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO, loa_messageArgs);
				}

				for(AccCompletitudDocumental lacd_cd : acacd_completitudDocumental)
				{
					if(lacd_cd != null)
					{
						lacd_cd.setIdUsuarioCreacion(as_userId);
						lacd_cd.setIpCreacion(as_remoteIp);
						lacd_cd.setIdSolicitud(aso_solicitud.getIdSolicitud());
						lacd_cd.setDigitalizado(EstadoCommon.N);

						DaoCreator.getAccCompletitudDocumentalDAO(adm_manager).insert(lacd_cd);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_SOL);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_TIPO_DOCUMENTAL);
	}

	/**
	 * Método encargado de salvar los datos de cuenta bancaria de devolución de dinero.
	 *
	 * @param add_parametros Argumento de tipo <code>DevolucionDinero</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarDatosCuentaBancaria(
	    DevolucionDinero add_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(add_parametros != null)
			{
				Registro lr_registro;

				lr_registro = add_parametros.getRegistro();

				if(lr_registro != null)
				{
					Solicitud ls_solicitudRegistro;

					ls_solicitudRegistro = lr_registro.getSolicitud();

					if(ls_solicitudRegistro != null)
					{
						ls_solicitudRegistro.setParticipaInterviniente(EstadoCommon.N);

						lr_registro = salvarInteresado(ldm_manager, lr_registro, as_userId, false, false, true);

						if(lr_registro != null)
						{
							Persona lp_persona;

							lp_persona = lr_registro.getPersona();

							if(lp_persona != null)
							{
								String ls_idPersona;

								ls_idPersona = lp_persona.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
								{
									String ls_idSolicitud;

									ls_idSolicitud = ls_solicitudRegistro.getIdSolicitud();

									if(StringUtils.isValidString(ls_idSolicitud))
									{
										DevolucionDineroDAO lddd_DAO;
										DevolucionDinero    ldd_devolucionDinero;

										lddd_DAO                 = DaoCreator.getDevolucionDineroDAO(ldm_manager);
										ldd_devolucionDinero     = lddd_DAO.findByIdSolicitud(ls_idSolicitud);

										if(ldd_devolucionDinero != null)
										{
											String ls_entidadRecaudadora;
											String ls_tipoCuenta;
											String ls_numeroCuenta;

											ls_entidadRecaudadora     = add_parametros.getIdEntidadRecaudadora();
											ls_tipoCuenta             = add_parametros.getTipoCuenta();
											ls_numeroCuenta           = add_parametros.getNumeroCuenta();

											if(!StringUtils.isValidString(ls_entidadRecaudadora))
												throw new B2BException(
												    ErrorKeys.DEBE_INGRESAR_NOMBRE_ENTIDAD_RECAUDADORA
												);

											if(!StringUtils.isValidString(ls_tipoCuenta))
												throw new B2BException(ErrorKeys.DEBE_INGRESAR_TIPO_CUENTA);

											if(!StringUtils.isValidString(ls_numeroCuenta))
												throw new B2BException(ErrorKeys.DEBE_INGRESAR_NUMERO_CUENTA);

											ldd_devolucionDinero.setIdEntidadRecaudadora(ls_entidadRecaudadora);
											ldd_devolucionDinero.setTipoCuenta(ls_tipoCuenta);
											ldd_devolucionDinero.setNumeroCuenta(ls_numeroCuenta);
											ldd_devolucionDinero.setIdSolicitud(ls_idSolicitud);
											ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersona);

											{
												PersonaTelefono lpt_personaTelefonoFijo;

												lpt_personaTelefonoFijo = lr_registro.getTelefonoFijo();

												if(lpt_personaTelefonoFijo != null)
													ldd_devolucionDinero.setIdTelefonoFijoTitular(
													    lpt_personaTelefonoFijo.getIdTelefono()
													);
											}

											{
												PersonaTelefono lpt_personaTelefonoMovil;

												lpt_personaTelefonoMovil = lr_registro.getTelefonoMovil();

												if(lpt_personaTelefonoMovil != null)
													ldd_devolucionDinero.setIdTelefonoMovilTitular(
													    lpt_personaTelefonoMovil.getIdTelefono()
													);
											}

											{
												PersonaCorreoElectronico lpce_personaCorreoElectronico;

												lpce_personaCorreoElectronico = lr_registro.getPersonaCorreoElectronico();

												if(lpce_personaCorreoElectronico != null)
													ldd_devolucionDinero.setIdCorreoElectronicoTitular(
													    lpce_personaCorreoElectronico.getIdCorreoElectronico()
													);
											}

											ldd_devolucionDinero.setIdUsuarioModificacion(as_userId);
											ldd_devolucionDinero.setIpModificacion(as_remoteIp);

											lddd_DAO.update(ldd_devolucionDinero);
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
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosCuentaBancaria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los datos del interesado de devolución de dinero.
	 *
	 * @param addu_parametros Argumento de tipo <code>DevolucionDineroUI</code> que contiene los criterios necesarios para salvar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarDatosInteresadoDevDinero(
	    DevolucionDineroUI addu_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(addu_parametros != null)
			{
				String ls_idPersonaRepresentante;
				String ls_idCalidadRepresentante;

				ls_idPersonaRepresentante     = null;
				ls_idCalidadRepresentante     = null;

				{
					Collection<DevolucionDinero> lcdd_personaRepresentante;

					lcdd_personaRepresentante = addu_parametros.getPersonaRepresentante();

					if(CollectionUtils.isValidCollection(lcdd_personaRepresentante))
					{
						DevolucionDinero ldd_personaRepresentante;

						ldd_personaRepresentante = lcdd_personaRepresentante.iterator().next();

						if(ldd_personaRepresentante != null)
						{
							Registro lr_registro;

							lr_registro = ldd_personaRepresentante.getRegistro();

							if(lr_registro != null)
							{
								Solicitud ls_solicitudRegistro;

								ls_solicitudRegistro = lr_registro.getSolicitud();

								if(ls_solicitudRegistro != null)
								{
									SolicitudDAO lsd_DAO;
									Solicitud    ls_solicitud;

									lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
									ls_solicitud     = lsd_DAO.findById(ls_solicitudRegistro);

									if(ls_solicitud != null)
									{
										ls_solicitud.setIdAutorizacionNotificacion(
										    ls_solicitudRegistro.getIdAutorizacionNotificacion()
										);
										ls_solicitud.setIdAutorizacionComunicacion(
										    ls_solicitudRegistro.getIdAutorizacionComunicacion()
										);
										ls_solicitud.setIdCalidadSolicitante(
										    ls_solicitudRegistro.getIdCalidadSolicitante()
										);
										ls_solicitud.setParticipaInterviniente(
										    ls_solicitudRegistro.getParticipaInterviniente()
										);
										ls_solicitud.setEntidadExenta(ls_solicitudRegistro.getEntidadExenta());

										lr_registro.setSolicitud(ls_solicitud);
									}

									lr_registro = salvarInteresado(ldm_manager, lr_registro, as_userId, true);

									if(lr_registro != null)
									{
										Persona lp_persona;

										lp_persona = lr_registro.getPersona();

										if(lp_persona != null)
										{
											ls_idPersonaRepresentante = lp_persona.getIdPersona();

											if(StringUtils.isValidString(ls_idPersonaRepresentante))
											{
												ls_solicitud = lsd_DAO.findById(ls_solicitudRegistro);

												if(ls_solicitud != null)
												{
													ls_idCalidadRepresentante = ls_solicitudRegistro
															.getIdCalidadSolicitante();

													ls_solicitud.setIdPersona(ls_idPersonaRepresentante);
													ls_solicitud.setIdCalidadSolicitante(ls_idCalidadRepresentante);
													ls_solicitud.setIdUsuarioModificacion(as_userId);
													ls_solicitud.setIpModificacion(as_remoteIp);

													lsd_DAO.insertOrUpdate(ls_solicitud, false);
												}
											}
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_PERSONA_REPRESENTANTE);
				}

				if(
				    StringUtils.isValidString(ls_idPersonaRepresentante)
					    && StringUtils.isValidString(ls_idCalidadRepresentante)
				)
				{
					Collection<DevolucionDinero> lcdd_personaARepresentar;

					lcdd_personaARepresentar = addu_parametros.getPersonasARepresentar();

					if(CollectionUtils.isValidCollection(lcdd_personaARepresentar))
					{
						String ls_idSolicitud;

						ls_idSolicitud = addu_parametros.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							PersonaPresentadaDAO lppd_DAO;

							lppd_DAO = DaoCreator.getPersonaPresentadaDAO(ldm_manager);

							lppd_DAO.deleteByIdSolicitud(ls_idSolicitud);

							for(DevolucionDinero ldd_iterador : lcdd_personaARepresentar)
							{
								if(ldd_iterador != null)
								{
									Registro lr_registro;

									lr_registro = ldd_iterador.getRegistro();

									if(lr_registro != null)
									{
										Solicitud ls_solicitudRegistro;

										ls_solicitudRegistro = lr_registro.getSolicitud();

										if(ls_solicitudRegistro != null)
										{
											ls_solicitudRegistro.setParticipaInterviniente(EstadoCommon.N);

											lr_registro = salvarInteresado(
												    ldm_manager, lr_registro, as_userId, false, false
												);

											if(lr_registro != null)
											{
												Persona lp_persona;

												lp_persona = lr_registro.getPersona();

												if(lp_persona != null)
												{
													String ls_idPersonaARepresentar;

													ls_idPersonaARepresentar = lp_persona.getIdPersona();

													if(StringUtils.isValidString(ls_idPersonaARepresentar))
													{
														PersonaPresentada lpp_personaPresentada;

														lpp_personaPresentada = new PersonaPresentada();

														lpp_personaPresentada.setIdSolicitud(
														    ls_solicitudRegistro.getIdSolicitud()
														);
														lpp_personaPresentada.setIdPersonaApoderado(
														    ls_idPersonaRepresentante
														);
														lpp_personaPresentada.setIdCalidadSolicitante(
														    ls_idCalidadRepresentante
														);
														lpp_personaPresentada.setIdPersonaRepresentadaApoderado(
														    ls_idPersonaARepresentar
														);
														lpp_personaPresentada.setIdCalidadPersonaRepresentada(
														    ls_solicitudRegistro.getIdCalidadSolicitante()
														);
														lpp_personaPresentada.setIdUsuarioCreacion(as_userId);
														lpp_personaPresentada.setIpCreacion(as_remoteIp);

														lppd_DAO.insert(lpp_personaPresentada);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_PERSONA_A_REPRESENTAR);
				}

				{
					String ls_titularCuentaDevDinero;

					ls_titularCuentaDevDinero = addu_parametros.getTitularCuentaDevDinero();

					if(StringUtils.isValidString(ls_titularCuentaDevDinero))
					{
						String ls_idSolicitud;

						ls_idSolicitud = addu_parametros.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							DevolucionDineroDAO lddd_DAO;
							DevolucionDinero    ldd_devolucionDinero;

							lddd_DAO                 = DaoCreator.getDevolucionDineroDAO(ldm_manager);
							ldd_devolucionDinero     = lddd_DAO.findByIdSolicitud(ls_idSolicitud);

							if(ldd_devolucionDinero != null)
							{
								if(ls_titularCuentaDevDinero.equalsIgnoreCase(EstadoCommon.S))
									ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersonaRepresentante);

								ldd_devolucionDinero.setTitularCuenta(ls_titularCuentaDevDinero);
								ldd_devolucionDinero.setIdUsuarioModificacion(as_userId);
								ldd_devolucionDinero.setIpModificacion(as_remoteIp);

								lddd_DAO.update(ldd_devolucionDinero);
							}
							else
							{
								ldd_devolucionDinero = new DevolucionDinero();

								if(ls_titularCuentaDevDinero.equalsIgnoreCase(EstadoCommon.S))
									ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersonaRepresentante);

								ldd_devolucionDinero.setTitularCuenta(ls_titularCuentaDevDinero);
								ldd_devolucionDinero.setExtemporaneo(EstadoCommon.N);
								ldd_devolucionDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
								ldd_devolucionDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

								ldd_devolucionDinero.setIdSolicitud(ls_idSolicitud);
								ldd_devolucionDinero.setIdUsuarioCreacion(as_userId);
								ldd_devolucionDinero.setIpCreacion(as_remoteIp);

								lddd_DAO.insert(ldd_devolucionDinero);
							}

							{
								String ls_intervieneTramiteDevDinero;

								ls_intervieneTramiteDevDinero = addu_parametros.getIntervieneTramiteDevDinero();

								if(
								    StringUtils.isValidString(ls_intervieneTramiteDevDinero)
									    && ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.S)
								)
									DaoCreator.getPersonaPresentadaDAO(ldm_manager).deleteByIdSolicitud(ls_idSolicitud);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosInteresadoDevDinero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método para salvar el Tab de datos trámite.
	 *
	 * @param addui_datosSalvarUI Objeto contenedor de información a salvar
	 * @param as_userId Usuario del proceso
	 * @param as_remoteIp Ip del proceso
	 * @return el valor de devolucion dinero UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI salvarTabDatosTramite(
	    DevolucionDineroUI addui_datosSalvarUI, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(addui_datosSalvarUI != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idSubProceso;

				ls_idSubProceso = addui_datosSalvarUI.getIdSubProceso();

				if(StringUtils.isValidString(ls_idSubProceso))
				{
					SolicitudDAO lsd_DAO;

					lsd_DAO = DaoCreator.getSolicitudDAO(ldm_manager);

					if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_TURNO_DE_SERVICIO_REGISTRAL))
					{
						String ls_idSolicitud;

						ls_idSolicitud = String.valueOf(lsd_DAO.findSecuence());

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							//INGRESO DE DEV DE DINERO
							String ls_idProcesoAnterior;

							ls_idProcesoAnterior = addui_datosSalvarUI.getIdProcesoAnterior();

							if(StringUtils.isValidString(ls_idProcesoAnterior))
							{
								//INGRESO DE SOLICITUD
								{
									Solicitud ls_solicitud;

									ls_solicitud = new Solicitud();

									ls_solicitud.setIdSolicitud(ls_idSolicitud);
									ls_solicitud.setEntidadExenta(EstadoCommon.N);
									ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
									ls_solicitud.setDigitalizada(EstadoCommon.N);
									ls_solicitud.setDerechoPeticion(EstadoCommon.N);
									ls_solicitud.setFechaSolicitud(new Date());
									ls_solicitud.setIdProceso(addui_datosSalvarUI.getIdProceso());
									ls_solicitud.setIdSubproceso(addui_datosSalvarUI.getIdSubProceso());
									ls_solicitud.setIdProcesoAnt(ls_idProcesoAnterior);
									ls_solicitud.setIdTurnoAnt(addui_datosSalvarUI.getIdTurno());

									ls_solicitud.setIdUsuarioCreacion(as_userId);
									ls_solicitud.setIpCreacion(as_remoteIp);

									DaoCreator.getSolicitudDAO(ldm_manager).insertOrUpdate(ls_solicitud, true);

									addui_datosSalvarUI.setIdSolicitud(ls_idSolicitud);
								}

								if(ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
								{
									Collection<ActoDevolucionDinero> lcadd_actos;

									lcadd_actos = addui_datosSalvarUI.getActosDevolucionDinero();

									if(CollectionUtils.isValidCollection(lcadd_actos))
									{
										long ll_valorTotalActoDevolucion;

										ll_valorTotalActoDevolucion = 0;

										for(ActoDevolucionDinero ladd_tmp : lcadd_actos)
										{
											if((ladd_tmp != null) && ladd_tmp.isSeleccionado())
												ll_valorTotalActoDevolucion = ll_valorTotalActoDevolucion
													+ ladd_tmp.getValorTotalLiquidado();
										}

										DevolucionDinero ldd_devDinero;
										String           ls_idDevolucionDinero;

										ldd_devDinero = new DevolucionDinero();

										ldd_devDinero.setIdSolicitud(ls_idSolicitud);
										ldd_devDinero.setIdTurnoDevolucion(addui_datosSalvarUI.getIdTurno());
										ldd_devDinero.setValorTotalActoDevolucion(ll_valorTotalActoDevolucion);

										ldd_devDinero.setExtemporaneo(EstadoCommon.N);
										ldd_devDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
										ldd_devDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

										ldd_devDinero.setIdUsuarioCreacion(as_userId);
										ldd_devDinero.setIpCreacion(as_remoteIp);

										ls_idDevolucionDinero = DaoCreator.getDevolucionDineroDAO(ldm_manager)
												                              .insert(ldd_devDinero);

										if(StringUtils.isValidString(ls_idDevolucionDinero))
										{
											addui_datosSalvarUI.setIdDevolucionDinero(ls_idDevolucionDinero);
											//INGRESO DE ACTOS SELECCIONADOS
											{
												for(ActoDevolucionDinero ladd_tmp : lcadd_actos)
												{
													if((ladd_tmp != null) && ladd_tmp.isSeleccionado())
													{
														ladd_tmp.setIdDevolucionDinero(ls_idDevolucionDinero);
														ladd_tmp.setIdSolicitud(ls_idSolicitud);
														ladd_tmp.setIdUsuarioCreacion(as_userId);
														ladd_tmp.setIpCreacion(as_remoteIp);

														DaoCreator.getActoDevolucionDineroDAO(ldm_manager)
															          .insert(ladd_tmp);
													}
												}
											}
										}
									}
								}
								else if(ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
								{
									LiquidacionItemCertificado llic_temp;

									llic_temp = addui_datosSalvarUI.getLiquidacionItemCertificado();

									if(llic_temp != null)
									{
										DevolucionDinero ldd_devDinero;

										ldd_devDinero = new DevolucionDinero();

										ldd_devDinero.setIdSolicitud(ls_idSolicitud);
										ldd_devDinero.setIdTurnoDevolucion(addui_datosSalvarUI.getIdTurno());
										ldd_devDinero.setValorTotalActoDevolucion(llic_temp.getValor());

										ldd_devDinero.setExtemporaneo(EstadoCommon.N);
										ldd_devDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
										ldd_devDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

										ldd_devDinero.setIdUsuarioCreacion(as_userId);
										ldd_devDinero.setIpCreacion(as_remoteIp);

										DaoCreator.getDevolucionDineroDAO(ldm_manager).insert(ldd_devDinero);
									}
								}
								else if(ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2))
								{
									DevolucionDinero ldd_devDinero;

									ldd_devDinero = new DevolucionDinero();

									ldd_devDinero.setIdSolicitud(ls_idSolicitud);
									ldd_devDinero.setIdTurnoDevolucion(addui_datosSalvarUI.getIdTurno());

									ldd_devDinero.setExtemporaneo(EstadoCommon.N);
									ldd_devDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
									ldd_devDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

									ldd_devDinero.setIdUsuarioCreacion(as_userId);
									ldd_devDinero.setIpCreacion(as_remoteIp);

									DaoCreator.getDevolucionDineroDAO(ldm_manager).insert(ldd_devDinero);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_REGISTRAR_SOLICITUD);
					}
					else if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA))
					{
						DevolucionDinero ldd_devolucionDinero;

						ldd_devolucionDinero = addui_datosSalvarUI.getDevolucionDinero();

						if(ldd_devolucionDinero != null)
						{
							Collection<String>  lcs_mensajes;
							DevolucionDineroDAO lddd_DAO;

							lcs_mensajes     = new ArrayList<String>();
							lddd_DAO         = DaoCreator.getDevolucionDineroDAO(ldm_manager);

							{
								String ls_tipoConsignacion;

								ls_tipoConsignacion = ldd_devolucionDinero.getTipoConsignacion();

								if(!StringUtils.isValidString(ls_tipoConsignacion))
									throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_TIPO_CONSIGNACION);
							}

							{
								String ls_numeroConsignacion;

								ls_numeroConsignacion = ldd_devolucionDinero.getNumeroConsignacionErrada();

								if(!StringUtils.isValidString(ls_numeroConsignacion))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NUMERO_CONSIGNACION);

								{
									Collection<DevolucionDinero> lcdd_devDineroexistente;

									lcdd_devDineroexistente = lddd_DAO.findByNumeroConsignacionErrada(
										    ls_numeroConsignacion
										);

									if(CollectionUtils.isValidCollection(lcdd_devDineroexistente))
										lcs_mensajes.add(MessagesKeys.SOLICITUD_DEV_DINERO_EXISTENTE);
								}
							}

							{
								String ls_nombreEntidad;

								ls_nombreEntidad = ldd_devolucionDinero.getCodEntidadConsignacionErrada();

								if(!StringUtils.isValidString(ls_nombreEntidad))
									throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_NOMBRE_DE_LA_ENTIDAD);
							}

							{
								Date ld_fechaConsignacion;

								ld_fechaConsignacion = ldd_devolucionDinero.getFechaConsignacionErrada();

								if(ld_fechaConsignacion == null)
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_FECHA_CONSIGNACION);

								{
									Constantes lcc_constante;

									lcc_constante = DaoCreator.getConstantesDAO(ldm_manager)
											                      .findById(ConstanteCommon.PLAZO_CONSIGNACION_ERRADA);

									if(lcc_constante != null)
									{
										BigInteger lbi_entero;

										lbi_entero = lcc_constante.getEntero();

										if(NumericUtils.isValidBigInteger(lbi_entero))
										{
											LocalDate lld_fechaConsignacion;

											lld_fechaConsignacion = ld_fechaConsignacion.toInstant()
													                                        .atZone(
													    ZoneId.systemDefault()
													).toLocalDate();

											if(lld_fechaConsignacion != null)
											{
												LocalDate lld_fechaConsignacionAdd;
												LocalDate lld_fechaActual;

												lld_fechaConsignacionAdd     = lld_fechaConsignacion.plusYears(
													    NumericUtils.getLong(NumericUtils.getInt(lbi_entero))
													);
												lld_fechaActual              = LocalDate.now();

												if(lld_fechaConsignacionAdd.isBefore(lld_fechaActual))
													lcs_mensajes.add(MessagesKeys.PLAZO_DEV_DINERO_VENCIDO);
											}
										}
									}
								}
							}

							{
								String ls_numeroCuenta;

								ls_numeroCuenta = ldd_devolucionDinero.getNumeroCuentaConsignacionErrada();

								if(!StringUtils.isValidString(ls_numeroCuenta))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NUMERO_CUENTA_BANCARIA);
							}

							{
								Double ld_valor;

								ld_valor = ldd_devolucionDinero.getValorConsignacionErrada();

								if(ld_valor == null)
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_VALOR);

								if(!NumericUtils.isValidDouble(ld_valor, 1D))
									throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_VALOR_VALIDO);
							}

							{
								boolean lb_solicitudCargada;
								String  ls_idSolicitudCargada;
								String  ls_idSolicitud;

								ls_idSolicitudCargada     = addui_datosSalvarUI.getIdSolicitud();
								lb_solicitudCargada       = StringUtils.isValidString(ls_idSolicitudCargada);
								ls_idSolicitud            = lb_solicitudCargada ? ls_idSolicitudCargada
									                                            : StringUtils.getString(
									    lsd_DAO.findSecuence()
									);

								if(!lb_solicitudCargada)
								{
									Solicitud ls_solicitud;

									ls_solicitud = new Solicitud();

									ls_solicitud.setIdSolicitud(ls_idSolicitud);
									ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_4);
									ls_solicitud.setIdSubproceso(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA);
									ls_solicitud.setVersionSubProceso(
									    NumericUtils.getInteger(IdentificadoresCommon.NUM1)
									);
									ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.ORIP_DE_ORIGEN);
									ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
									ls_solicitud.setDigitalizada(EstadoCommon.N);
									ls_solicitud.setDerechoPeticion(EstadoCommon.N);
									ls_solicitud.setFechaSolicitud(new Date());
									ls_solicitud.setIdProcedencia(ProcedenciaCommon.ENTIDADES);
									ls_solicitud.setIdUsuarioCreacion(as_userId);
									ls_solicitud.setIpCreacion(as_remoteIp);

									DaoCreator.getSolicitudDAO(ldm_manager).insertOrUpdate(ls_solicitud, true);

									addui_datosSalvarUI.setIdSolicitud(ls_idSolicitud);
								}

								{
									DevolucionDinero ldd_devDinero;

									ldd_devDinero = lb_solicitudCargada
										? lddd_DAO.findByIdSolicitud(ls_idSolicitudCargada) : new DevolucionDinero();

									if(lb_solicitudCargada && (ldd_devDinero == null))
									{
										ldd_devDinero = new DevolucionDinero();

										ldd_devDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
										ldd_devDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

										lb_solicitudCargada = false;
									}

									ldd_devDinero.setIdSolicitud(ls_idSolicitud);
									ldd_devDinero.setTipoConsignacion(ldd_devolucionDinero.getTipoConsignacion());
									ldd_devDinero.setNumeroConsignacionErrada(
									    ldd_devolucionDinero.getNumeroConsignacionErrada()
									);
									ldd_devDinero.setCodEntidadConsignacionErrada(
									    ldd_devolucionDinero.getCodEntidadConsignacionErrada()
									);
									ldd_devDinero.setFechaConsignacionErrada(
									    ldd_devolucionDinero.getFechaConsignacionErrada()
									);
									ldd_devDinero.setNumeroCuentaConsignacionErrada(
									    ldd_devolucionDinero.getNumeroCuentaConsignacionErrada()
									);
									ldd_devDinero.setValorConsignacionErrada(
									    ldd_devolucionDinero.getValorConsignacionErrada()
									);

									{
										String ls_extemporaneo;

										ls_extemporaneo = ldd_devDinero.getExtemporaneo();

										if(!StringUtils.isValidString(ls_extemporaneo))
											ldd_devDinero.setExtemporaneo(EstadoCommon.N);
									}

									{
										String ls_tipoTelefonoFijoTitular;

										ls_tipoTelefonoFijoTitular = ldd_devDinero.getTipoTelefonoFijoTitular();

										if(!StringUtils.isValidString(ls_tipoTelefonoFijoTitular))
											ldd_devDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
									}

									{
										String ls_tipoTelefonoMovilTitular;

										ls_tipoTelefonoMovilTitular = ldd_devDinero.getTipoTelefonoMovilTitular();

										if(!StringUtils.isValidString(ls_tipoTelefonoMovilTitular))
											ldd_devDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);
									}

									if(lb_solicitudCargada)
									{
										ldd_devDinero.setIdUsuarioModificacion(as_userId);
										ldd_devDinero.setIpModificacion(as_remoteIp);

										lddd_DAO.update(ldd_devDinero);
									}
									else
									{
										ldd_devDinero.setIdUsuarioCreacion(as_userId);
										ldd_devDinero.setIpCreacion(as_remoteIp);

										lddd_DAO.insert(ldd_devDinero);
									}
								}
							}

							addui_datosSalvarUI.setMensajesInformativos(lcs_mensajes);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarTabDatosTramite", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return addui_datosSalvarUI;
	}

	/**
	 * Método para guardar el titular de la cuenta precargado con la solicitud.
	 *
	 * @param add_devolucionDinero Objeto DevolucionDinero con información ingresada en pantalla
	 * @param as_userId de as user id
	 * @param as_remoteIp de remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTitularCuenta(DevolucionDinero add_devolucionDinero, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		if(add_devolucionDinero != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idSolicitud;

				ls_idSolicitud = add_devolucionDinero.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					DevolucionDineroDAO ldd_DAO;
					DevolucionDinero    ldd_ddFromBD;

					ldd_DAO          = DaoCreator.getDevolucionDineroDAO(ldm_manager);
					ldd_ddFromBD     = ldd_DAO.findByIdSolicitud(ls_idSolicitud);

					if(ldd_ddFromBD != null)
					{
						ldd_ddFromBD.setIdEntidadRecaudadora(add_devolucionDinero.getIdEntidadRecaudadora());
						ldd_ddFromBD.setTipoCuenta(add_devolucionDinero.getTipoCuenta());
						ldd_ddFromBD.setNumeroCuenta(add_devolucionDinero.getNumeroCuenta());
						ldd_ddFromBD.setIdPersonaTitularCuenta(add_devolucionDinero.getIdPersonaTitularCuenta());
						ldd_ddFromBD.setIdUsuarioModificacion(as_userId);
						ldd_ddFromBD.setIpModificacion(as_remoteIp);

						ldd_DAO.update(ldd_ddFromBD);
					}
				}
				else
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarTitularCuenta", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método para terminar el proceso de devoluciones.
	 *
	 * @param addu_devolucionDineroUi de addu devolucion dinero ui
	 * @param as_idTurnoAnterior correspondiente al valor de as id turno anterior
	 * @param aso_solicitud correspondiente al valor de aso solicitud
	 * @param acacd_completitudDocumental Colección de completitud documental a salvar
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de remote ip
	 * @param as_userId de as user id
	 * @return Registro Contenedor de información
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro terminarProcesoDevolucionDinero(
	    DevolucionDineroUI addu_devolucionDineroUi, String as_idTurnoAnterior, Solicitud aso_solicitud,
	    Collection<AccCompletitudDocumental> acacd_completitudDocumental, String as_localIp, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Registro   lr_registro;
		boolean    lb_devolucionDineroUi;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lr_registro               = new Registro();
		lb_devolucionDineroUi     = addu_devolucionDineroUi != null;

		try
		{
			if(
			    (StringUtils.isValidString(as_idTurnoAnterior) || lb_devolucionDineroUi) && (aso_solicitud != null)
				    && CollectionUtils.isValidCollection(acacd_completitudDocumental)
			)
			{
				if(lb_devolucionDineroUi)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(aso_solicitud.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_idCalidadSolicitante;

						ls_idCalidadSolicitante = ls_solicitud.getIdCalidadSolicitante();

						if(StringUtils.isValidString(ls_idCalidadSolicitante))
						{
							String ls_titularCuentaDevDinero;
							String ls_intervinieneTramiteDevDinero;
							String ls_constante;

							ls_titularCuentaDevDinero           = StringUtils.getStringNotNull(
								    addu_devolucionDineroUi.getTitularCuentaDevDinero()
								);
							ls_intervinieneTramiteDevDinero     = StringUtils.getStringNotNull(
								    addu_devolucionDineroUi.getIntervieneTramiteDevDinero()
								);

							ls_constante = ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
								? ConstanteCommon.TIPO_DOCUMENTAL_APODERADO_DEV_DINERO
								: (ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERVINIENTE)
								? ConstanteCommon.TIPO_DOCUMENTAL_INTERVINIENTE_DEV_DINERO
								: ((ls_titularCuentaDevDinero.equalsIgnoreCase(EstadoCommon.N)
									&& ls_intervinieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.N))
								? ConstanteCommon.TIPOS_DOCUMENTALES_RESPUESTAS_DEV_DINERO : null));

							if(StringUtils.isValidString(ls_constante))
							{
								Constantes lc_constante;

								lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_constante);

								if(lc_constante != null)
								{
									Collection<String> lcs_codigos;

									lcs_codigos = ListadoCodigosConstantes.generarCodigosCollection(
										    lc_constante.getCaracter()
										);

									if(CollectionUtils.isValidCollection(lcs_codigos))
									{
										for(String ls_iterador : lcs_codigos)
										{
											if(StringUtils.isValidString(ls_iterador))
											{
												Iterator<AccCompletitudDocumental> liacd_iterator;
												boolean                            lb_encontrado;

												liacd_iterator     = acacd_completitudDocumental.iterator();
												lb_encontrado      = false;

												while(liacd_iterator.hasNext() && !lb_encontrado)
												{
													AccCompletitudDocumental lacd_completitudDocumental;

													lacd_completitudDocumental = liacd_iterator.next();

													if(lacd_completitudDocumental != null)
													{
														String ls_idTipoDocumental;

														ls_idTipoDocumental     = lacd_completitudDocumental
																.getIdTipoDocumental();

														lb_encontrado = StringUtils.isValidString(ls_idTipoDocumental)
																&& ls_iterador.equalsIgnoreCase(ls_idTipoDocumental);
													}
												}

												if(!lb_encontrado)
												{
													TipoDocumental ltd_tipoDocFaltante;

													ltd_tipoDocFaltante = DaoCreator.getTipoDocumentalDAO(ldm_manager)
															                            .findById(ls_iterador);

													if(ltd_tipoDocFaltante != null)
													{
														Object[] loa_messageArgs;

														loa_messageArgs        = new String[1];
														loa_messageArgs[0]     = ltd_tipoDocFaltante.getNombre();

														throw new B2BException(
														    ErrorKeys.ERROR_TIPO_DOCUMENTAL_ESPECIFICO_OBLIGATORIO,
														    loa_messageArgs
														);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				String ls_nir;

				ls_nir = crearNir(as_userId, as_remoteIp, ldm_manager);

				if(StringUtils.isValidString(ls_nir))
				{
					aso_solicitud.setNir(ls_nir);
					lr_registro.setNirProceso(ls_nir);

					{
						Solicitud    lso_solicitud;
						SolicitudDAO lsd_DAO;

						lsd_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);
						lso_solicitud     = lsd_DAO.findById(aso_solicitud);

						if(lso_solicitud != null)
						{
							lso_solicitud.setNir(ls_nir);
							lso_solicitud.setComentario(aso_solicitud.getComentario());
							lso_solicitud.setFechaSolicitud(new Date());
							lso_solicitud.setIdUsuarioModificacion(as_userId);
							lso_solicitud.setIpModificacion(as_remoteIp);

							lsd_DAO.insertOrUpdate(lso_solicitud, false);

							if(StringUtils.isValidString(as_idTurnoAnterior))
							{
								Turno lt_turnoAnterior;

								lt_turnoAnterior = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurnoAnterior);

								if(lt_turnoAnterior != null)
								{
									SolicitudAsociada lsa_solicitudAsociada;

									lsa_solicitudAsociada = new SolicitudAsociada();

									lsa_solicitudAsociada.setIdSolicitud(lt_turnoAnterior.getIdSolicitud());
									lsa_solicitudAsociada.setIdSolicitud1(lso_solicitud.getIdSolicitud());
									lsa_solicitudAsociada.setIndVinculado(EstadoCommon.A);
									lsa_solicitudAsociada.setIdUsuarioCreacion(as_userId);
									lsa_solicitudAsociada.setIpCreacion(as_remoteIp);

									DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
										          .insertOrUpdate(lsa_solicitudAsociada, true);
								}
							}

							{
								String ls_idSolicitud;

								ls_idSolicitud = lso_solicitud.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = new TurnoHistoria();

									lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
									lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
									lth_turnoHistoria.setIpModificacion(as_remoteIp);

									DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);

									salvarCompletitudDocumental(
									    acacd_completitudDocumental, aso_solicitud, ldm_manager, as_userId, as_remoteIp
									);

									{
										byte[] lba_return;

										lba_return = generarConstanciaSolicitudDevolucion(
											    ls_idSolicitud, ldm_manager, as_userId, as_remoteIp, true
											);

										if(lba_return != null)
											lr_registro.setPdf(lba_return);
									}
								}
							}
						}
					}

					{
						String ls_urlCapture;

						ls_urlCapture = getDigitalizacionBusiness().construirUrlCapture(ls_nir, null, ldm_manager);

						if(StringUtils.isValidString(ls_urlCapture))
							lr_registro.setUrlCapture(ls_urlCapture);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoDevolucionDinero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lr_registro;
	}

	/**
	 * Método para validar la etapa actual y los plazos de vencimiento.
	 *
	 * @param lt_turno Objeto Turno con la información a validar
	 * @param addui_devDineroUI Interfaz para ingresar mensajes
	 * @param adm_DAOmanager Objeto DAOManager para realizar consultas
	 * @return DevolucionesDineroUI Interfaz modificada
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroUI validacionEtapaActualYPlazos(
	    Turno lt_turno, DevolucionDineroUI addui_devDineroUI, DAOManager adm_DAOmanager
	)
	    throws B2BException
	{
		if((lt_turno != null) && (addui_devDineroUI != null))
		{
			String ls_idEtapaActual;

			ls_idEtapaActual = StringUtils.getString(lt_turno.getIdEtapaActual());

			if(StringUtils.isValidString(ls_idEtapaActual))
			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_DAOmanager)
						                     .findById(ConstanteCommon.ETAPAS_VALIDAS_DEV_DINERO);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						Collection<String> lcs_cs;

						lcs_cs = ListadoCodigosConstantes.generarCodigosCollection(ls_caracter);

						if(CollectionUtils.isValidCollection(lcs_cs) && !lcs_cs.contains(ls_idEtapaActual))
						{
							Object[] loa_args;

							loa_args        = new String[1];
							loa_args[0]     = lt_turno.getIdTurno();

							throw new B2BException(ErrorKeys.TURNO_ETAPAS_INVALIDAS_DEV_DINERO, loa_args);
						}
					}
				}
				//VALIDACIONES PLAZOS
				{
					String ls_idProceso;

					ls_idProceso = lt_turno.getIdProceso();

					if(
					    StringUtils.isValidString(ls_idProceso)
						    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
					)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_DAOmanager)
								                          .findByIdTurnoEtapa(
								    lt_turno.getIdTurno(), NumericUtils.getLong(ls_idEtapaActual)
								);

						if(lth_turnoHistoria != null)
						{
							Date ld_fechaCreacionTH;

							ld_fechaCreacionTH = lth_turnoHistoria.getFechaCreacion();

							if(ld_fechaCreacionTH != null)
							{
								Date ld_fechaVencimiento;

								ld_fechaVencimiento = modificarFecha(
									    ld_fechaCreacionTH, Calendar.MONTH, DateUtils.getMonth(ld_fechaCreacionTH) + 4
									);

								if(ld_fechaVencimiento != null)
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_DAOmanager)
											                     .findById(lt_turno.getIdSolicitud());

									if(ls_solicitud != null)
									{
										Date ld_fechaCreacionSolicitud;

										ld_fechaCreacionSolicitud = ls_solicitud.getFechaCreacion();

										if(
										    (ld_fechaCreacionSolicitud != null)
											    && ld_fechaCreacionSolicitud.after(ld_fechaVencimiento)
										)
										{
											String   ls_mensaje;
											Object[] loa_args;

											loa_args        = new String[1];
											loa_args[0]     = lt_turno.getIdTurno();

											ls_mensaje = getMessages()
													             .getString(
													    MessagesKeys.DEVOLUCION_DINERO_FUERA_TERMINOS, loa_args
													);

											{
												Collection<String> lcs_mensajes;

												lcs_mensajes = addui_devDineroUI.getMensajesPantalla();

												if(CollectionUtils.isValidCollection(lcs_mensajes))
													lcs_mensajes.add(ls_mensaje);
												else
												{
													lcs_mensajes = new ArrayList<String>();

													lcs_mensajes.add(ls_mensaje);

													addui_devDineroUI.setMensajesPantalla(lcs_mensajes);
												}
											}
										}
									}
								}
							}
						}
					}

					if(
					    NumericUtils.getLong(ls_idEtapaActual) == EtapaCommon.ID_ETAPA_PROCESO_DE_CERTIFICADOS_FINALIZADO_NO_APROBADO
					)
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_DAOmanager).findById(lt_turno.getIdSolicitud());

						if(ls_solicitud != null)
						{
							Date ld_fechaCreacion;

							ld_fechaCreacion = ls_solicitud.getFechaCreacion();

							if(ld_fechaCreacion != null)
							{
								Date ld_fechaVencimiento;

								ld_fechaVencimiento = modificarFecha(
									    ld_fechaCreacion, Calendar.MONTH, DateUtils.getMonth(ld_fechaCreacion) + 1
									);

								if(ld_fechaVencimiento != null)
								{
									Date ld_fechaActual;

									ld_fechaActual = new Date();

									if(ld_fechaActual.after(ld_fechaVencimiento))
									{
										String   ls_mensaje;
										Object[] loa_args;

										loa_args        = new String[1];
										loa_args[0]     = lt_turno.getIdTurno();

										ls_mensaje = getMessages()
												             .getString(
												    MessagesKeys.DEVOLUCION_DINERO_FUERA_TERMINOS, loa_args
												);

										{
											Collection<String> lcs_mensajes;

											lcs_mensajes = addui_devDineroUI.getMensajesPantalla();

											if(CollectionUtils.isValidCollection(lcs_mensajes))
												lcs_mensajes.add(ls_mensaje);
											else
											{
												lcs_mensajes = new ArrayList<String>();

												lcs_mensajes.add(ls_mensaje);

												addui_devDineroUI.setMensajesPantalla(lcs_mensajes);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return addui_devDineroUI;
	}

	/**
	 * Método para validar si el objeto de dvolución ha tenido una nueva entrada.
	 *
	 * @param as_idTurno String de idTurno con el filtro a validar
	 * @param adm_DAOmanager Objeto DAOManager para realizar consultas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validacionNuevaEntrada(String as_idTurno, DAOManager adm_DAOmanager)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idTurno))
		{
			Collection<Turno> lct_turnosTMP;

			lct_turnosTMP = DaoCreator.getTurnoDAO(adm_DAOmanager).findNuevaEntradSolDevolucion(as_idTurno);

			if(CollectionUtils.isValidCollection(lct_turnosTMP))
			{
				for(Turno lt_turnoTmp : lct_turnosTMP)
				{
					if(lt_turnoTmp != null)
					{
						long ll_idEtapaActual;

						ll_idEtapaActual = NumericUtils.getLong(lt_turnoTmp.getIdEtapaActual());

						if(
						    !(ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
							    || (ll_idEtapaActual == EtapaCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL)
						)
							throw new B2BException(ErrorKeys.ERROR_TURNO_OBJETO_NUEVA_ENTRADA);
					}
				}
			}
		}
	}

	/**
	 * Método para relizar las validaciones del proceso del turno ingresado.
	 *
	 * @param lt_turno Objeto Turno con la información a validar
	 * @param adm_DAOmanager Objeto DAOManager para realizar consultas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validacionProceso(Turno lt_turno, DAOManager adm_DAOmanager)
	    throws B2BException
	{
		if(lt_turno != null)
		{
			String ls_idProceso;

			ls_idProceso = lt_turno.getIdProceso();

			if(StringUtils.isValidString(ls_idProceso))
			{
				Proceso lp_proceso;

				lp_proceso = DaoCreator.getProcesoDAO(adm_DAOmanager).findById(ls_idProceso);

				if(lp_proceso != null)
				{
					if(
					    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
						    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
					)
					{
						if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
						{
							String ls_idSubproceso;

							ls_idSubproceso = lt_turno.getIdSubProceso();

							if(StringUtils.isValidString(ls_idSubproceso))
							{
								if(
								    ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_5)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_15)
									    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_18)
								)
								{
									Object[] loa_args;

									loa_args        = new String[2];
									loa_args[0]     = lp_proceso.getNombre();
									loa_args[1]     = ls_idSubproceso;

									throw new B2BException(ErrorKeys.ERROR_SUBPROCESO_DEV_DINERO, loa_args);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_ID_PROCESO);
						}
					}
					else
					{
						Object[] loa_args;

						loa_args        = new String[2];
						loa_args[0]     = lp_proceso.getNombre();
						loa_args[1]     = lt_turno.getIdTurno();

						throw new B2BException(ErrorKeys.ERROR_TURNO_NO_VALIDO_TRAMITE_DEV_DINERO, loa_args);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_PROCESO);
		}
	}

	/**
	 * Método para validar si existe un turno derivado con proceso 4.
	 *
	 * @param as_idTurno String contenedor de filtro de búsqueda
	 * @param ldm_manager Conexión a base de datos
	 * @return boolean validación de consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validacionTurnoDerivadoDevolucion(String as_idTurno, DAOManager ldm_manager)
	    throws B2BException
	{
		boolean lb_return;

		if(StringUtils.isValidString(as_idTurno))
			lb_return = CollectionUtils.isValidCollection(
				    DaoCreator.getTurnoDerivadoDAO(ldm_manager)
					              .findByIdPadreIdProcesoHijo(as_idTurno, ProcesoCommon.ID_PROCESO_4)
				);
		else
			throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);

		return lb_return;
	}

	/**
	 * Método encargado de validar los datos a salvar para los interesados de devolución de dinero.
	 *
	 * @param add_parametros Argumento de tipo <code>DevolucionDinero</code> que contiene los criterios necesarios para validar.
	 * @param ab_salvarSolicitud Argumento de tipo <code>boolean</code> que determina si se debe salvar los datos en la solicitud.
	 * @param ab_validarDatosPersona Argumento de tipo <code>boolean</code> que determina si se debe validar los datos de la persona.
	 * @param ab_validarTelefono Argumento de tipo <code>boolean</code> que determina si se debe validar el número telefónico.
	 * @param ab_soloValidar Argumento de tipo <code>boolean</code> que determina si solo se debe validar o no.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void validarDatosDelInteresado(
	    DevolucionDinero add_parametros, boolean ab_salvarSolicitud, boolean ab_validarDatosPersona,
	    boolean ab_validarTelefono, boolean ab_soloValidar, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(add_parametros != null)
			{
				Registro lr_registro;

				lr_registro = add_parametros.getRegistro();

				if(lr_registro != null)
				{
					Solicitud ls_solicitudRegistro;

					ls_solicitudRegistro = lr_registro.getSolicitud();

					if(ls_solicitudRegistro != null)
					{
						ls_solicitudRegistro.setParticipaInterviniente(EstadoCommon.N);

						salvarInteresado(
						    ldm_manager, lr_registro, as_userId, ab_salvarSolicitud, ab_validarDatosPersona,
						    ab_validarTelefono, ab_soloValidar
						);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarDatosDelInteresado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Validar turno seleccionado.
	 *
	 * @param as_idActo de as id acto
	 * @param as_idTurno de as id turno
	 * @return de acto devolucion dinero
	 * @throws B2BException de b 2 B exception
	 */
	public ActoDevolucionDinero validarTurnoSeleccionado(String as_idActo, String as_idTurno)
	    throws B2BException
	{
		ActoDevolucionDinero ladd_return;

		ladd_return = null;

		if(StringUtils.isValidString(as_idActo) && StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					String as_idSolicitudInicial;

					as_idSolicitudInicial = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(as_idSolicitudInicial))
						ladd_return = DaoCreator.getActoDevolucionDineroDAO(ldm_manager)
								                    .findByIdActoIdSolicitudInicial(as_idActo, as_idSolicitudInicial);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("validarTurnoSeleccionado", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ladd_return;
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
					laa_return = new TagPlantillaResolucion();

					lot_oficiosTexto.setIdTurnoHistoria(
					    NumericUtils.getLongWrapper(aaa_parametros.getIdTurnoHistoria())
					);

					laa_return.setArchivo(
					    generarDocumentoActoAdministrativo(
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
	 * Genera el archivo de constancia de solicitud de devolución.
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @param adm_manager Conexión a la base de datos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param ab_salvar de ab salvar
	 * @return cadena de bytes contenedora de la información del archivo
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	private byte[] generarConstanciaSolicitudDevolucion(
	    String as_idSolicitud, DAOManager adm_manager, String as_userId, String as_remoteIp, boolean ab_salvar
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			SolicitudDAO lsd_solicitudDAO;
			PersonaDAO   lpd_personaDAO;
			Solicitud    ls_solicitud;
			String       ls_plantilla;

			lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(adm_manager);
			lpd_personaDAO       = DaoCreator.getPersonaDAO(adm_manager);
			ls_solicitud         = lsd_solicitudDAO.findById(as_idSolicitud);
			ls_plantilla         = null;

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(adm_manager)
						                     .findByIdWithImage(
						    ConstanteCommon.PLANTILLA_CONSTANCIA_SOLICITUD_DEVOLUCION
						);

				if(lc_constante != null)
				{
					byte[] lba_plantilla;

					lba_plantilla = lc_constante.getImagenes();

					if(lba_plantilla == null)
						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE);

					ls_plantilla = new String(lba_plantilla);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);
			}

			if(!StringUtils.isValidString(ls_plantilla))
				throw new B2BException(ErrorKeys.ERROR_PLANTILLA);

			ls_plantilla     = resolverTagsSNRReciboLiquidacion(adm_manager, ls_plantilla);

			ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_OBSERVACIONES>", ls_solicitud.getComentario());

			{
				Persona lp_persona;

				lp_persona     = lpd_personaDAO.findById(ls_solicitud.getIdPersona());

				ls_plantilla = llenarDatosPersona(
					    adm_manager, ls_plantilla, ls_solicitud, lp_persona, "<TAG_NOMBRES_SOL>", "<TAG_TIPO_DOC_SOL>",
					    "<TAG_NUM_DOC_SOL>", "<TAG_CORREO_SOL>", "<TAG_TELEFONO_SOL>"
					);
			}

			{
				DevolucionDinero ldd_devolucionDinero;

				ldd_devolucionDinero = DaoCreator.getDevolucionDineroDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

				if(ldd_devolucionDinero != null)
				{
					ls_plantilla     = reemplazarTagEnPlantilla(
						    ls_plantilla, "<TAG_TIPO_CUENTA_ABONO>", ldd_devolucionDinero.getTipoCuenta()
						);

					ls_plantilla = reemplazarTagEnPlantilla(
						    ls_plantilla, "<TAG_NUMERO_CUENTA_ABONO>", ldd_devolucionDinero.getNumeroCuenta()
						);

					{
						long ll_montoTransaccion;

						ll_montoTransaccion     = ldd_devolucionDinero.getValorTotalActoDevolucion();

						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_VALOR_SOLICITADO>", ll_montoTransaccion
							);

						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_VALOR_SOLIC_LETRAS>",
							    Convertidor.convertirLetras(ll_montoTransaccion)
							);
					}

					{
						Persona lp_persona;

						lp_persona     = lpd_personaDAO.findById(ldd_devolucionDinero.getIdPersonaTitularCuenta());

						ls_plantilla = llenarDatosPersona(
							    adm_manager, ls_plantilla, null, lp_persona, "<TAG_NOMBRE_TITULAR_ABONO>",
							    "<TAG_TIPO_DOC_TITULAR_ABONO>", "<TAG_NUM_DOC_TITULAR_ABONO>", null,
							    "<TAG_TELEFONO_TITULAR_ABONO>"
							);
					}

					{
						String ls_respuestaIntervino;

						ls_respuestaIntervino     = StringUtils.getStringNotNull(
							    ldd_devolucionDinero.getIntervieneTramite()
							);

						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_INTERVINO>",
							    ls_respuestaIntervino.equals(EstadoCommon.S) ? IdentificadoresCommon.SI_TEXTO
							                                                 : IdentificadoresCommon.NO_TEXTO
							);
					}

					{
						String ls_respuestaTitularCuenta;

						ls_respuestaTitularCuenta     = StringUtils.getStringNotNull(
							    ldd_devolucionDinero.getTitularCuenta()
							);

						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_TITULAR_CUENTA>",
							    ls_respuestaTitularCuenta.equals(EstadoCommon.S) ? IdentificadoresCommon.SI_TEXTO
							                                                     : IdentificadoresCommon.NO_TEXTO
							);
					}

					{
						String ls_nirDevolucion;
						String ls_turnosAsociados;
						String ls_procesoDevolucion;

						ls_nirDevolucion         = null;
						ls_turnosAsociados       = null;
						ls_procesoDevolucion     = null;

						{
							TurnoDAO ltd_turnoDAO;
							Turno    lt_turno;

							ltd_turnoDAO     = DaoCreator.getTurnoDAO(adm_manager);
							lt_turno         = ltd_turnoDAO.findById(ldd_devolucionDinero.getIdTurnoDevolucion());

							if(lt_turno != null)
							{
								Solicitud ls_solicitudAnterior;

								ls_solicitudAnterior = lsd_solicitudDAO.findById(lt_turno.getIdSolicitud());

								if(ls_solicitudAnterior != null)
								{
									String  ls_idSolicitudAnterior;
									Proceso lp_proceso;

									ls_idSolicitudAnterior     = ls_solicitudAnterior.getIdSolicitud();
									lp_proceso                 = DaoCreator.getProcesoDAO(adm_manager)
											                                   .findById(
											    ls_solicitudAnterior.getIdProceso()
											);

									if(lp_proceso != null)
									{
										StringBuilder lsb_proceso;

										lsb_proceso = new StringBuilder(lp_proceso.getIdProceso());

										lsb_proceso.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
										lsb_proceso.append(lp_proceso.getNombre());

										ls_procesoDevolucion = lsb_proceso.toString();
									}

									{
										String ls_numeroReferencia;

										ls_numeroReferencia = null;

										{
											Liquidacion ll_liquidacion;

											ll_liquidacion = DaoCreator.getAccLiquidacionDAO(adm_manager)
													                       .findNumeroReferencia(
													    ls_idSolicitudAnterior
													);

											if(ll_liquidacion != null)
												ls_numeroReferencia = ll_liquidacion.getNumeroReferencia();
										}

										ls_plantilla = reemplazarInformacionBancaria(
											    ls_plantilla, ls_numeroReferencia,
											    ldd_devolucionDinero.getIdEntidadRecaudadora()
											);
									}

									ls_nirDevolucion = ls_solicitudAnterior.getNir();
								}

								{
									Collection<Turno> lct_turnosAsociados;

									lct_turnosAsociados = ltd_turnoDAO.findByIdSolicitudProcesoDifTurno(
										    lt_turno.getIdSolicitud(), lt_turno.getIdProceso(), lt_turno.getIdTurno()
										);

									if(CollectionUtils.isValidCollection(lct_turnosAsociados))
									{
										Iterator<Turno> lit_iterador;
										StringBuilder   lsb_turnosAsociados;

										lit_iterador            = lct_turnosAsociados.iterator();
										lsb_turnosAsociados     = new StringBuilder();

										while(lit_iterador.hasNext())
										{
											Turno lt_turnoTmp;

											lt_turnoTmp = lit_iterador.next();

											if(lt_turnoTmp != null)
											{
												lsb_turnosAsociados.append(lt_turnoTmp.getIdTurno());

												if(lit_iterador.hasNext())
													lsb_turnosAsociados.append(IdentificadoresCommon.SIMBOLO_COMA);
											}
										}

										ls_turnosAsociados = lsb_turnosAsociados.toString();
									}
								}
							}
						}

						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_NIR_DEVOLUCION>", ls_nirDevolucion
							);

						ls_plantilla     = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_TURNOS_ASOC_DEV>", ls_turnosAsociados
							);

						ls_plantilla = reemplazarTagEnPlantilla(
							    ls_plantilla, "<TAG_PROCESO_DEV>", ls_procesoDevolucion
							);
					}
				}
			}

			{
				StringBuilder lsb_actos;

				lsb_actos = new StringBuilder();

				{
					Collection<TipoActo> lcta_tiposActo;

					lcta_tiposActo = DaoCreator.getTipoActoDAO(adm_manager)
							                       .findAllBySolicitudDevolucionDinero(as_idSolicitud);

					if(CollectionUtils.isValidCollection(lcta_tiposActo))
					{
						Iterator<TipoActo> lita_iterador;

						lita_iterador = lcta_tiposActo.iterator();

						while(lita_iterador.hasNext())
						{
							TipoActo lta_tipoActo;

							lta_tipoActo = lita_iterador.next();

							if(lta_tipoActo != null)
							{
								lsb_actos.append(lta_tipoActo.getIdTipoActo());
								lsb_actos.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
								lsb_actos.append(lta_tipoActo.getNombre());

								if(lita_iterador.hasNext())
									lsb_actos.append(IdentificadoresCommon.SALTO_LINEA_RTF);
							}
						}
					}
				}

				ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_ACTOS_DEV>", lsb_actos.toString());
			}

			{
				Collection<AccCompletitudDocumental> lcacd_documentos;
				StringBuilder                        lsb_documentosAportados;

				lcacd_documentos            = DaoCreator.getAccCompletitudDocumentalDAO(adm_manager)
						                                    .findAllByIdSolicitud(as_idSolicitud);
				lsb_documentosAportados     = new StringBuilder();

				if(CollectionUtils.isValidCollection(lcacd_documentos))
				{
					Iterator<AccCompletitudDocumental> liacd_iterator;
					TipoDocumentalDAO                  ltdd_tipoDocumentalDAO;

					liacd_iterator             = lcacd_documentos.iterator();
					ltdd_tipoDocumentalDAO     = DaoCreator.getTipoDocumentalDAO(adm_manager);

					while(liacd_iterator.hasNext())
					{
						AccCompletitudDocumental lacd_completitud;

						lacd_completitud = liacd_iterator.next();

						if(lacd_completitud != null)
						{
							TipoDocumental ltd_tipoDocumental;

							ltd_tipoDocumental = ltdd_tipoDocumentalDAO.findById(
								    lacd_completitud.getIdTipoDocumental()
								);

							if(ltd_tipoDocumental != null)
							{
								String ls_obligatorioTipoDocumental;

								ls_obligatorioTipoDocumental = StringUtils.getStringNotNull(
									    lacd_completitud.getObligatorioTipoDocumental()
									);

								lsb_documentosAportados.append(ltd_tipoDocumental.getNombre());
								lsb_documentosAportados.append(IdentificadoresCommon.DOS_PUNTOS_ESPACIO);
								lsb_documentosAportados.append(
								    ls_obligatorioTipoDocumental.equals(EstadoCommon.S)
								    ? IdentificadoresCommon.SI_TEXTO : IdentificadoresCommon.NO_TEXTO
								);

								if(liacd_iterator.hasNext())
									lsb_documentosAportados.append(IdentificadoresCommon.SALTO_LINEA_RTF);
							}
						}
					}
				}

				ls_plantilla = reemplazarTagEnPlantilla(
					    ls_plantilla, "<TAG_DOCUMENTOS_APORTADOS>", lsb_documentosAportados.toString()
					);
			}

			ls_plantilla     = reemplazarTagEnPlantilla(
				    ls_plantilla, "<FECHA_SOL_DEVOLUCION>", ls_solicitud.getFechaCreacion()
				);

			ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_NIR>", ls_solicitud.getNir());

			{
				String ls_nombreSubproceso;

				ls_nombreSubproceso = null;

				{
					Subproceso ls_subproceso;

					ls_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
							                      .findById(
							    ls_solicitud.getIdProceso(), ls_solicitud.getIdSubproceso()
							);

					if(ls_subproceso != null)
						ls_nombreSubproceso = ls_subproceso.getNombre();
				}

				ls_plantilla = reemplazarTagEnPlantilla(ls_plantilla, "<TAG_TIPO_DEVOLUCION>", ls_nombreSubproceso);
			}

			Map<String, String> lmss_datos;

			lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
			ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

			lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

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
				li_imagenes.setCodigoVerificacion(lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION));

				ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

				if(ll_idImagenTemp > 0)
				{
					DocumentosSalida    lds_documentoSalida;
					DocumentosSalidaDAO lds_DAO;
					Long                ll_idImagen;

					lds_documentoSalida     = new DocumentosSalida();
					lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
					ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

					lds_documentoSalida.setIdSolicitud(as_idSolicitud);
					lds_documentoSalida.setTipo(TipoArchivoCommon.SOLICITUD);
					lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
					lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
					lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
					lds_documentoSalida.setIdSolicitud(as_idSolicitud);
					lds_documentoSalida.setIdImagen(ll_idImagen);
					lds_documentoSalida.setIdUsuarioCreacion(as_userId);
					lds_documentoSalida.setIpCreacion(as_remoteIp);

					lds_DAO.insertOrUpdate(lds_documentoSalida, true);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_GENERO_ARCHIVO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaSolicitudDevolucion", lb2be_e);

			throw lb2be_e;
		}

		return lba_return;
	}

	/**
	 * Metodo para guardar un documento en el owcc.
	 *
	 * @param al_salida Objeto de tipo long
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idUsuario Objeto de tipo String
	 * @param as_ipRemota Objeto de tipo String
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	private synchronized void guardarDocumentoOWCC(
	    long al_salida, DAOManager adm_manager, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_bitacora;

		ldm_bitacora = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_endpoint;

			ls_endpoint = DaoCreator.getConstantesDAO(adm_manager)
					                    .findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

			if(StringUtils.isValidString(ls_endpoint))
			{
				BitacoraProcesoDAO lbpd_bitacora;

				lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

				ldm_bitacora.setAutoCommit(true);

				{
					DocumentosSalida lds_documento;

					lds_documento = DaoCreator.getDocumentosSalidaDAO(adm_manager).findById(al_salida);

					if((lds_documento != null) && !lds_documento.isEnviadoOwcc())
					{
						enviarGestorDocumental(
						    lds_documento, lbpd_bitacora, ls_endpoint, as_idUsuario, as_ipRemota, adm_manager
						);

						if(!lds_documento.isEnviadoOwcc())
							throw new B2BException(
							    "Ocurrió un error enviando el documento al OWCC, intentelo nuevamente"
							);
					}
				}
			}
			else
			{
				Object[] loa_params;

				loa_params        = new String[1];
				loa_params[0]     = ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_params);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_bitacora.setRollbackOnly();

			clh_LOGGER.error("guardarDocumentoOWCC", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_bitacora.close();
		}
	}

	/**
	 * Llena la información basica de una persona en una plantilla.
	 *
	 * @param adm_manager Conexión a la base de datos
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_solicitud Objeto contenedor de la información de la solicitud
	 * @param ap_persona Objeto contenedor de la información de la persona
	 * @param as_tagNombres identificador de la plantilla para los nombres de la persona
	 * @param as_tagTipoDoc identificador de la plantilla para el tipo de documento de la persona
	 * @param as_tagNumDoc identificador de la plantilla para el número de documento de la persona
	 * @param as_tagCorreo identificador de la plantilla para el correo electrónico de la persona
	 * @param as_tagTelefono identificador de la plantilla para el teléfono de la persona
	 * @return Plantilla con la información de la persona resuelta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	private String llenarDatosPersona(
	    DAOManager adm_manager, String as_plantilla, Solicitud as_solicitud, Persona ap_persona, String as_tagNombres,
	    String as_tagTipoDoc, String as_tagNumDoc, String as_tagCorreo, String as_tagTelefono
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_plantilla) && (ap_persona != null))
		{
			String  ls_idPersona;
			String  ls_nombresPersona;
			String  ls_tipoDocPersona;
			String  ls_numDocPersona;
			String  ls_correoPersona;
			String  ls_telefonoPersona;
			boolean lb_conSolicitud;

			ls_idPersona           = ap_persona.getIdPersona();
			ls_nombresPersona      = null;
			ls_tipoDocPersona      = null;
			ls_numDocPersona       = null;
			ls_correoPersona       = null;
			ls_telefonoPersona     = null;
			lb_conSolicitud        = as_solicitud != null;

			{
				StringBuilder lsb_nombres;

				lsb_nombres = new StringBuilder();

				agregarParteDeNombre(lsb_nombres, ap_persona.getPrimerNombre());
				agregarParteDeNombre(lsb_nombres, ap_persona.getSegundoNombre());
				agregarParteDeNombre(lsb_nombres, ap_persona.getPrimerApellido());
				agregarParteDeNombre(lsb_nombres, ap_persona.getSegundoApellido());

				ls_nombresPersona = lsb_nombres.toString();
			}

			{
				InteresadoDocumentoTipo lidt_tipoDocumento;

				lidt_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager)
						                           .findById(ap_persona.getIdDocumentoTipo());

				if(lidt_tipoDocumento != null)
					ls_tipoDocPersona = lidt_tipoDocumento.getDescripcion();
			}

			ls_numDocPersona = ap_persona.getNumeroDocumento();

			if(lb_conSolicitud)
			{
				PersonaCorreoElectronico lpce_personaCorreo;
				PersonaTelefono          lpe_personaTelefono;

				lpce_personaCorreo      = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
						                                .findById(ls_idPersona, as_solicitud.getIdCorreoElectronico());
				lpe_personaTelefono     = DaoCreator.getPersonaTelefonoDAO(adm_manager)
						                                .findById(ls_idPersona, as_solicitud.getIdTelefono());

				if(lpce_personaCorreo != null)
					ls_correoPersona = lpce_personaCorreo.getCorreoElectronico();

				if(lpe_personaTelefono != null)
					ls_telefonoPersona = lpe_personaTelefono.getTelefono();
			}
			else
			{
				PersonaTelefono lpe_personaTelefono;

				lpe_personaTelefono = DaoCreator.getPersonaTelefonoDAO(adm_manager).findMaxByIdPersona(ls_idPersona);

				if(lpe_personaTelefono != null)
					ls_telefonoPersona = lpe_personaTelefono.getTelefono();
			}

			as_plantilla     = reemplazarTagEnPlantilla(as_plantilla, as_tagNombres, ls_nombresPersona);
			as_plantilla     = reemplazarTagEnPlantilla(as_plantilla, as_tagTipoDoc, ls_tipoDocPersona);
			as_plantilla     = reemplazarTagEnPlantilla(as_plantilla, as_tagNumDoc, ls_numDocPersona);
			as_plantilla     = reemplazarTagEnPlantilla(as_plantilla, as_tagCorreo, ls_correoPersona);
			as_plantilla     = reemplazarTagEnPlantilla(as_plantilla, as_tagTelefono, ls_telefonoPersona);
		}

		return as_plantilla;
	}

	/**
	 * Método encargado de llenar oficios texto con textos precargados.
	 *
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los datos a mostrar en la plantilla.
	 * @param amss_data Argumento de tipo <code>Map<String, String></code> que contiene los textos a mostrar en la plantilla.
	 * @param adm_manager de adm manager
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private synchronized void llenarOficiosTextoActoAdministrativo(
	    OficiosTexto aot_oficiosTexto, Map<String, String> amss_data, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(amss_data) && (aot_oficiosTexto != null) && (adm_manager != null))
		{
			String ls_idSolicitud;

			ls_idSolicitud = aot_oficiosTexto.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				{
					String ls_tag;

					ls_tag = TagCommon.TAG_CONSIDERACIONES_PANTALLA;

					if(amss_data.containsKey(ls_tag))
					{
						String ls_consideraciones;

						ls_consideraciones = amss_data.get(ls_tag);

						if(StringUtils.isValidString(ls_consideraciones))
						{
							ls_consideraciones = resolverTags(ls_consideraciones, ls_idSolicitud, adm_manager);
							aot_oficiosTexto.setConsideracion(
							    ConversionTextos.convertirTextosFormatosCertificados(ls_consideraciones)
							);
						}
					}
				}

				{
					String ls_tag;

					ls_tag = TagCommon.TAG_RESUELVE_PANTALLA;

					if(amss_data.containsKey(ls_tag))
					{
						String ls_resuelve;

						ls_resuelve = amss_data.get(ls_tag);

						if(StringUtils.isValidString(ls_resuelve))
						{
							ls_resuelve = resolverTags(ls_resuelve, ls_idSolicitud, adm_manager);

							aot_oficiosTexto.setResuelve(ls_resuelve);
						}
					}
				}
			}
		}
	}

	/**
	 * Busca el nombre de la entidad recaudadora por su id.
	 *
	 * @param adm_managerNpa Conexión a la base de datos
	 * @param as_idEntidadRecaudadora id a utilizar como filtro en la busqueda
	 * @return Nombre resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos.
	 */
	private String obtenerEntidadRecaudadora(DAOManager adm_managerNpa, String as_idEntidadRecaudadora)
	    throws B2BException
	{
		String ls_nombreEntidad;

		ls_nombreEntidad = null;

		if(StringUtils.isValidString(as_idEntidadRecaudadora))
		{
			EntidadRecaudadora ler_entidad;

			ler_entidad = DaoCreator.getEntidadRecaudadoraDAO(adm_managerNpa).findById(as_idEntidadRecaudadora);

			if(ler_entidad != null)
				ls_nombreEntidad = ler_entidad.getNombreEntidadRecaudadora();
		}

		return ls_nombreEntidad;
	}

	/**
	 * Metodo encargado de consultar la plantilla de un id motivo en particular para actuaciones administrativas.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión con la base de datos.
	 * @param al_idMotivo Argumento de tipo <code>long</code> que contiene el id motivo a consultar.
	 * @param as_idCirculo Argumento de tipo <code>string</code> que determina el circulo.
	 * @param ab_consultarConstante Argumento de tipo <code>boolean</code> que determina si se debe consultar la constante.
	 * @return Objeto de tipo <code>TagPlantillaResolucion</code> que contiene los valores consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private TagPlantillaResolucion plantillaDatosPorIdMotivoActoAdministrativo(
	    DAOManager adm_manager, long al_idMotivo, String as_idCirculo, boolean ab_consultarConstante
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

			if(al_idMotivo == MotivoTramiteCommon.APROBACION_DEVOLUCION_DINERO)
			{
				if(StringUtils.isValidString(as_idCirculo))
				{
					if(as_idCirculo.equals(ConstanteCommon.CIRCULO_CENTRAL_SNR))
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_APROBACION_DEVOLUCION_DINERO_CENTRAL;
						ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
					else
					{
						ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_APROBACION_DEVOLUCION_DINERO_ORIP;
						ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION;
						ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
					}
				}
			}
			else if(al_idMotivo == MotivoTramiteCommon.NEGACION_DEVOLUCION_DINERO)
			{
				if(as_idCirculo.equals(ConstanteCommon.CIRCULO_CENTRAL_SNR))
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NEGACION_DEVOLUCION_DINERO_CENTRAL;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION;
					ls_tipoDocumental     = TipoDocumentalCommon.RESOLUCION;
				}
				else
				{
					ls_idPlantilla        = ConstanteCommon.PLANTILLA_RESOLUCION_NEGACION_DEVOLUCION_DINERO_ORIP;
					ls_tipoArchivo        = TipoArchivoCommon.RESOLUCION;
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
			clh_LOGGER.error("plantillaDatosPorIdMotivoActoAdministrativo", lb2be_e);

			throw lb2be_e;
		}

		return laa_actuacionesAdministrativas;
	}

	/**
	 * Busca en el esquema de NPA la información del pago a devolver.
	 *
	 * @param as_plantilla Objeto contenedor de la plantilla
	 * @param as_numeroReferencia Número de referencia generado por el sistema para referenciar el pago
	 * @param as_idEntidadRecaudadora id de la entidad bancaria donde se va a realizar el abono de devolución
	 * @return Plantilla con la información hallada resuelta
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	private String reemplazarInformacionBancaria(
	    String as_plantilla, String as_numeroReferencia, String as_idEntidadRecaudadora
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			DAOManager ldm_managerNpa;

			ldm_managerNpa = DaoManagerFactory.getDAOManagerNPA();

			try
			{
				NotificacionPago lnp_notificacionPago;

				lnp_notificacionPago = DaoCreator.getNotificacionPagoDAO(ldm_managerNpa)
						                             .findByNumeroReferencia(as_numeroReferencia);

				if(lnp_notificacionPago != null)
				{
					NotificacionPagoRecibida lnpr_notificacionPagoRecibida;

					lnpr_notificacionPagoRecibida = DaoCreator.getNotificacionPagoRecibidaDAO(ldm_managerNpa)
							                                      .findById(
							    lnp_notificacionPago.getIdNotificacionPagoRecibida()
							);

					if(lnpr_notificacionPagoRecibida != null)
					{
						{
							String ls_tipoRecaudo;

							ls_tipoRecaudo = null;

							{
								TipoRecaudo ltr_tipoRecaudo;

								ltr_tipoRecaudo = DaoCreator.getTipoRecaudoDAO(ldm_managerNpa)
										                        .findById(
										    lnpr_notificacionPagoRecibida.getCodigoTipoRecaudo()
										);

								if(ltr_tipoRecaudo != null)
									ls_tipoRecaudo = ltr_tipoRecaudo.getNombreTipoRecaudo();
							}

							as_plantilla = reemplazarTagEnPlantilla(as_plantilla, "<TAG_MEDIO_PAGO>", ls_tipoRecaudo);
						}

						as_plantilla = reemplazarTagEnPlantilla(
							    as_plantilla, "<TAG_BANCO_DEVOLUCION>",
							    obtenerEntidadRecaudadora(
							        ldm_managerNpa, lnpr_notificacionPagoRecibida.getCodigoEntidadRecaudadora()
							    )
							);

						{
							Date ld_fechaTransaccion;

							ld_fechaTransaccion     = lnp_notificacionPago.getFechaTransaccion();

							as_plantilla = reemplazarTagEnPlantilla(
								    as_plantilla, "<TAG_FECHA_RECAUDO>",
								    (ld_fechaTransaccion != null)
								    ? StringUtils.getString(ld_fechaTransaccion, "dd/MM/yyyy HH:mm") : null
								);
						}

						as_plantilla = reemplazarTagEnPlantilla(
							    as_plantilla, "<TAG_CUS_CONSIGNACION>",
							    lnpr_notificacionPagoRecibida.getCodigoTransaccionRecaudador()
							);
					}
				}

				as_plantilla = reemplazarTagEnPlantilla(
					    as_plantilla, "<TAG_BANCO_ABONO>",
					    obtenerEntidadRecaudadora(ldm_managerNpa, as_idEntidadRecaudadora)
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_managerNpa.setRollbackOnly();

				clh_LOGGER.error("reemplazarInformacionBancaria", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_managerNpa.close();
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags.
	 *
	 * @param as_plantilla de as plantilla
	 * @param as_idSolicitud de as id solicitud
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTags(String as_plantilla, String as_idSolicitud, DAOManager adm_manager)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_idSolicitud)
			    && (adm_manager != null)
		)
		{
			try
			{
				String       ls_tag;
				SolicitudDAO lsd_DAO;
				TurnoDAO     ltd_DAO;

				ls_tag      = TagCommon.TAG_PERSONA_INTERESADA;
				lsd_DAO     = DaoCreator.getSolicitudDAO(adm_manager);
				ltd_DAO     = DaoCreator.getTurnoDAO(adm_manager);

				if(as_plantilla.contains(ls_tag))
				{
					Collection<SolicitudAsociada> lcs_solicitudAsociada;

					lcs_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
							                              .findAllByIdSolicitud(as_idSolicitud, false);

					if(CollectionUtils.isValidCollection(lcs_solicitudAsociada))
					{
						StringBuilder lsb_sb;
						PersonaDAO    lpd_DAO;
						int           li_contador;

						lsb_sb          = new StringBuilder("Que el(la) señor(a) ");
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
											    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
										)
										{
											Persona lp_persona;

											lp_persona = lpd_DAO.findById(ls_solicitud.getIdPersona());

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

													ls_idCalidadSolicitante = ls_solicitud.getIdCalidadSolicitante();

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

						as_plantilla = as_plantilla.replace(ls_tag, lsb_sb.toString());
					}
				}

				as_plantilla = resolverTagsPersona(as_plantilla, as_idSolicitud, adm_manager);

				if(as_plantilla.contains(TagCommon.TAG_PERSONA_INTERVINIENTE))
					as_plantilla = resolverTagsPersona(as_plantilla, as_idSolicitud, adm_manager);

				{
					DevolucionDinero ldd_devolucion;

					ldd_devolucion = DaoCreator.getDevolucionDineroDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

					if(ldd_devolucion != null)
					{
						{
							String ls_idTurnoDevolucion;

							ls_idTurnoDevolucion = ldd_devolucion.getIdTurnoDevolucion();

							if(StringUtils.isValidString(ls_idTurnoDevolucion))
							{
								Turno lt_turno;

								lt_turno = ltd_DAO.findById(ls_idTurnoDevolucion);

								if(lt_turno != null)
								{
									String ls_idSolicitud;

									ls_idSolicitud = lt_turno.getIdSolicitud();

									if(StringUtils.isValidString(ls_idSolicitud))
									{
										Solicitud ls_solicitud;

										ls_solicitud = lsd_DAO.findById(ls_idSolicitud);

										if(ls_solicitud != null)
											as_plantilla = reemplazarTagsDocumento(
												    adm_manager, ls_solicitud, lt_turno, as_plantilla
												);
									}
								}

								ls_tag = TagCommon.TAG_TURNO_DEVOLUCION;

								if(as_plantilla.contains(ls_tag))
									as_plantilla = as_plantilla.replace(ls_tag, ls_idTurnoDevolucion);

								ls_tag = TagCommon.TAG_TIPO_CUENTA;

								if(as_plantilla.contains(ls_tag))
								{
									String ls_tipoCuenta;

									ls_tipoCuenta = ldd_devolucion.getTipoCuenta();

									if(StringUtils.isValidString(ls_tipoCuenta))
										as_plantilla = as_plantilla.replace(ls_tag, ls_tipoCuenta);
								}

								ls_tag = TagCommon.TAG_NUMERO_CUENTA;

								if(as_plantilla.contains(ls_tag))
								{
									String ls_numeroCuenta;

									ls_numeroCuenta = ldd_devolucion.getNumeroCuenta();

									if(StringUtils.isValidString(ls_numeroCuenta))
										as_plantilla = as_plantilla.replace(ls_tag, ls_numeroCuenta);
								}

								ls_tag = TagCommon.TAG_ENTIDAD_RECAUDADORA;

								if(as_plantilla.contains(ls_tag))
								{
									String ls_idEntidad;

									ls_idEntidad = ldd_devolucion.getIdEntidadRecaudadora();

									if(StringUtils.isValidString(ls_idEntidad))
									{
										EntidadRecaudadora ler_entidad;

										ls_idEntidad     = StringUtils.getString(NumericUtils.getInteger(ls_idEntidad));
										ler_entidad      = DaoCreator.getEntidadRecaudadoraDAO(adm_manager)
												                         .findById(ls_idEntidad);

										if(ler_entidad != null)
										{
											String ls_nombre;

											ls_nombre = ler_entidad.getNombreEntidadRecaudadora();

											if(StringUtils.isValidString(ls_nombre))
												as_plantilla = as_plantilla.replace(ls_tag, ls_nombre);
										}
									}
								}
							}
						}
					}
				}

				ls_tag = TagCommon.TAG_TURNO;

				if(as_plantilla.contains(ls_tag))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setIdProceso(ProcesoCommon.ID_PROCESO_4);
					lt_turno.setIdSolicitud(as_idSolicitud);

					lt_turno = ltd_DAO.findByIdSolicitudProceso(lt_turno);

					if(lt_turno != null)
					{
						String ls_idTurno;

						ls_idTurno = lt_turno.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
							as_plantilla = as_plantilla.replace(ls_tag, ls_idTurno);
					}
				}

				ls_tag = TagCommon.TAG_TIPOS_DOCUMENTALES;

				if(as_plantilla.contains(ls_tag))
				{
					Collection<AccCompletitudDocumental> lcacd_completitudesDocumentales;

					lcacd_completitudesDocumentales = DaoCreator.getAccCompletitudDocumentalDAO(adm_manager)
							                                        .findAllByIdSolicitud(as_idSolicitud);

					if(CollectionUtils.isValidCollection(lcacd_completitudesDocumentales))
					{
						TipoDocumentalDAO ltdd_DAO;
						StringBuilder     lsb_builder;

						ltdd_DAO        = DaoCreator.getTipoDocumentalDAO(adm_manager);
						lsb_builder     = new StringBuilder();

						for(AccCompletitudDocumental lacd_iterador : lcacd_completitudesDocumentales)
						{
							if(lacd_iterador != null)
							{
								String ls_idTipoDocumental;

								ls_idTipoDocumental = lacd_iterador.getIdTipoDocumental();

								if(StringUtils.isValidString(ls_idTipoDocumental))
								{
									TipoDocumental ltd_tipoDocumetal;

									ltd_tipoDocumetal = ltdd_DAO.findById(ls_idTipoDocumental);

									if(ltd_tipoDocumetal != null)
									{
										String ls_nombre;

										ls_nombre = ltd_tipoDocumetal.getNombre();

										if(StringUtils.isValidString(ls_nombre))
										{
											lsb_builder.append(ls_idTipoDocumental);
											lsb_builder.append(IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS);
											lsb_builder.append(ls_nombre);
											lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);
										}
									}
								}
							}
						}

						{
							String ls_texto;

							ls_texto = lsb_builder.toString();

							if(StringUtils.isValidString(ls_texto))
							{
								String ls_ultimo;

								ls_ultimo = ls_texto.substring(ls_texto.length() - 2);

								if(
								    StringUtils.isValidString(ls_ultimo)
									    && ls_ultimo.equalsIgnoreCase(IdentificadoresCommon.SIMBOLO_COMA)
								)
								{
									ls_texto = ls_texto.substring(0, ls_texto.length() - 2);

									if(StringUtils.isValidString(ls_texto))
										as_plantilla = as_plantilla.replace(ls_tag, ls_texto);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("resolverTags", lb2be_e);

				throw lb2be_e;
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags persona.
	 *
	 * @param as_plantilla de as plantilla
	 * @param as_idSolicitud de as id solicitud
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagsPersona(String as_plantilla, String as_idSolicitud, DAOManager adm_manager)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_idSolicitud)
			    && (adm_manager != null)
		)
		{
			try
			{
				Persona lp_persona;
				String  ls_tag;
				boolean lb_interviniente;

				lp_persona           = null;
				ls_tag               = TagCommon.TAG_PERSONA_INTERESADA;
				lb_interviniente     = as_plantilla.contains(TagCommon.TAG_PERSONA_INTERESADA);

				if(!lb_interviniente)
					ls_tag = TagCommon.TAG_PERSONA_INTERVINIENTE;

				if(lb_interviniente)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_idSolicitud);

					if(ls_solicitud != null)
					{
						String ls_idPersona;

						ls_idPersona = ls_solicitud.getIdPersona();

						if(StringUtils.isValidString(ls_idPersona))
							lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);
					}
				}
				else
				{
					DevolucionDinero ldd_devolucion;

					ldd_devolucion = DaoCreator.getDevolucionDineroDAO(adm_manager).findByIdSolicitud(as_idSolicitud);

					if(ldd_devolucion != null)
					{
						String ls_idPersona;

						ls_idPersona = ldd_devolucion.getIdPersonaInterviniente();

						if(StringUtils.isValidString(ls_idPersona))
							lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);
					}
				}

				if((lp_persona != null))
				{
					if(as_plantilla.contains(ls_tag))
					{
						String ls_nombreCompleto;

						ls_nombreCompleto = lp_persona.getNombreCompleto();

						if(StringUtils.isValidString(ls_nombreCompleto))
							as_plantilla = as_plantilla.replace(ls_tag, ls_nombreCompleto);
					}

					ls_tag = TagCommon.TAG_DOCUMENTO_TIPO;

					if(as_plantilla.contains(ls_tag))
					{
						String ls_tipoDocumento;

						ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

						if(StringUtils.isValidString(ls_tipoDocumento))
							as_plantilla = as_plantilla.replace(ls_tag, ls_tipoDocumento);
					}

					ls_tag = TagCommon.TAG_NUMERO_DOCUMENTO;

					if(as_plantilla.contains(ls_tag))
					{
						String ls_numeroDocumento;

						ls_numeroDocumento = lp_persona.getNumeroDocumento();

						if(StringUtils.isValidString(ls_numeroDocumento))
							as_plantilla = as_plantilla.replace(ls_tag, ls_numeroDocumento);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("resolverTagsPersona", lb2be_e);

				throw lb2be_e;
			}
		}

		return as_plantilla;
	}
}
