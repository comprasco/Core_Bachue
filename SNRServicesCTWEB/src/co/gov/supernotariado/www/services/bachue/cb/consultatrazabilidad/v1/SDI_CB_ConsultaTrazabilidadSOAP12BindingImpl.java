/**
 * SDI_CB_ConsultaTrazabilidadSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.NirPrincipal;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.NirVinculado;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.util.Collection;
import java.util.Iterator;


/**
 * Clase que contiene todos las propiedades SDI_CB_ConsultaTrazabilidadSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SDI_CB_ConsultaTrazabilidadSOAP12BindingImpl extends BaseServices
    implements SDI_CB_ConsultaTrazabilidad_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7155022477773745266L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SDI_CB_ConsultaTrazabilidadSOAP12BindingImpl.class);

	/**
	 * Consulta actos turno.
	 *
	 * @param ateoapr_entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta actos turno.v 1 . tipo salida consulta actos turno
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaActosTurno consultaActosTurno(TipoEntradaConsultaActosTurno ateoapr_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaConsultaActosTurno ltsoapr_salida;

		ltsoapr_salida = new TipoSalidaConsultaActosTurno();

		try
		{
			ltsoapr_salida = getServiceConsultaTrazabilidadRemote()
					                 .consultaActosTurno(
					    ateoapr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaActosTurno", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultaActosTurno", le_e);
		}

		return ltsoapr_salida;
	}

	/**
	 * Consulta detalle certificado.
	 *
	 * @param ateoapr_entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consulta detalle certificado.v 1 . tipo salida consulta detalle certificado
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    TipoEntradaConsultaDetalleCertificado ateoapr_entrada
	)
	    throws java.rmi.RemoteException
	{
		TipoSalidaConsultaDetalleCertificado ltsoapr_salida;

		ltsoapr_salida = new TipoSalidaConsultaDetalleCertificado();

		try
		{
			ltsoapr_salida = getServiceConsultaTrazabilidadRemote()
					                 .consultaDetalleCertificado(
					    ateoapr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaDetalleCertificado", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultaDetalleCertificado", le_e);
		}

		return ltsoapr_salida;
	}

	/**
	 * Permite consultar la trazabilidad de un trámite por nir/turno.
	 *
	 * @param atect_entrada de atect entrada
	 * @return el valor de tipo salida consultar trazabilidad
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarTrazabilidad consultarTrazabilidad(TipoEntradaConsultarTrazabilidad atect_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarTrazabilidad              ltsct_response;
		TipoElementoNir[]                            ltena_turnos;
		TipoSalidaConsultarTrazabilidadCodigoMensaje ltsctcm_codigoMensaje;
		String                                       ls_descripcionMensaje;

		ltsct_response            = new TipoSalidaConsultarTrazabilidad();
		ltsctcm_codigoMensaje     = TipoSalidaConsultarTrazabilidadCodigoMensaje.value1;
		ls_descripcionMensaje     = null;
		ltena_turnos              = null;

		try
		{
			ConsultaTrazabilidad lsct_respuesta;

			lsct_respuesta = getServiceConsultaTrazabilidadRemote()
					                 .consultarTrazabilidad(
					    atect_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if(lsct_respuesta != null)
			{
				{
					NirPrincipal lnp_nirPrincipal;

					lnp_nirPrincipal = lsct_respuesta.getNirPrincipal();

					if(lnp_nirPrincipal != null)
					{
						{
							Solicitud ls_solicitud;

							ls_solicitud = lnp_nirPrincipal.getSolicitud();

							if(ls_solicitud != null)
								ltsct_response.setNir(ls_solicitud.getNir());
						}

						{
							Fases lf_fases;

							lf_fases = lnp_nirPrincipal.getFases();

							if(lf_fases != null)
								ltsct_response.setFaseActualNIR(lf_fases.getNombre());
						}
					}

					{
						Collection<NirVinculado> lcnv_nirVinculados;

						lcnv_nirVinculados = lsct_respuesta.getTurnosVinculadosNirVinculados();

						if(CollectionUtils.isValidCollection(lcnv_nirVinculados))
						{
							Iterator<NirVinculado> linv_iterator;

							linv_iterator = lcnv_nirVinculados.iterator();

							if(linv_iterator != null)
							{
								if(linv_iterator.hasNext())
								{
									NirVinculado lnv_nirVinculado;

									lnv_nirVinculado = linv_iterator.next();

									if(lnv_nirVinculado != null)
									{
										{
											Solicitud ls_solicitudVinculada;

											ls_solicitudVinculada = lnv_nirVinculado.getSolicitud();

											if(ls_solicitudVinculada != null)
												ltsct_response.setNirVinculado(ls_solicitudVinculada.getNir());
										}

										{
											Acto la_tramiteVinculado;

											la_tramiteVinculado = lnv_nirVinculado.getTramiteVinculado();

											if(la_tramiteVinculado != null)
											{
												TipoActo lta_tramiteVinculado;

												lta_tramiteVinculado = la_tramiteVinculado.getTipoActo();

												ltsct_response.setTramiteVinculado(
												    (lta_tramiteVinculado != null) ? lta_tramiteVinculado.getNombre()
												                                   : IdentificadoresCommon.DATO_INVALIDO
												);
											}
										}
									}
								}
							}
						}
					}

					{
						Collection<Trazabilidad> lct_turnos;
						int                      li_contadorTurnos;

						lct_turnos            = lsct_respuesta.getTrazabilidad();
						li_contadorTurnos     = NumericUtils.DEFAULT_INT_VALUE;

						if(
						    CollectionUtils.isValidCollection(lct_turnos)
							    && (lct_turnos.size() > NumericUtils.DEFAULT_INT_VALUE)
						)
						{
							ltena_turnos = new TipoElementoNir[lct_turnos.size()];

							for(Trazabilidad lt_turnoActual : lct_turnos)
							{
								if(lt_turnoActual != null)
								{
									TipoElementoNir lten_turno;

									lten_turno = new TipoElementoNir();

									lten_turno.setDecisionCalificacion(lt_turnoActual.getDecisionCalificacion());

									{
										Turno lt_turno;
										lt_turno = lt_turnoActual.getTurno();

										if(lt_turno != null)
											lten_turno.setTurno(lt_turno.getIdTurno());
									}

									{
										CirculoRegistral lcr_orip;

										lcr_orip = lt_turnoActual.getCirculoRegistral();

										if(lcr_orip != null)
											lten_turno.setOrip(lcr_orip.getNombre());
									}

									{
										Fases lf_fases;

										lf_fases = lt_turnoActual.getFases();

										if(lf_fases != null)
											lten_turno.setFaseActualTurno(lf_fases.getNombre());
									}

									{
										Proceso lp_procesoTurno;

										lp_procesoTurno = lt_turnoActual.getProceso();

										if(lp_procesoTurno != null)
											lten_turno.setProcesoTurno(lp_procesoTurno.getNombre());
									}

									{
										RegistroCalificacion lrc_detalleDocuemnto;

										lrc_detalleDocuemnto = lt_turnoActual.getDetalleDocumento();

										if(lrc_detalleDocuemnto != null)
										{
											TipoDocumento[] ltda_informacionDocumento;
											TipoDocumento   ltd_detalleDocumento;

											ltda_informacionDocumento     = new TipoDocumento[1];
											ltd_detalleDocumento          = new TipoDocumento();

											ltd_detalleDocumento.setTipo(lrc_detalleDocuemnto.getNombreTipoDoc());
											ltd_detalleDocumento.setNumeroDocumento(
											    lrc_detalleDocuemnto.getCodDocumento()
											);
											ltd_detalleDocumento.setFechaDocumento(
											    StringUtils.getString(
											        lrc_detalleDocuemnto.getFechaDocumento(),
											        FormatoFechaCommon.DIA_MES_ANIO
											    )
											);
											ltd_detalleDocumento.setOficinaOrigen(
											    lrc_detalleDocuemnto.getNombreOficina()
											);
											ltd_detalleDocumento.setTipoOficina(
											    lrc_detalleDocuemnto.getNombreTipoOficina()
											);
											ltd_detalleDocumento.setDepartamento(
											    lrc_detalleDocuemnto.getNombreDepartamento()
											);
											ltd_detalleDocumento.setMunicipio(
											    lrc_detalleDocuemnto.getNombreMunicipio()
											);
											ltd_detalleDocumento.setTipoEntidad(
											    lrc_detalleDocuemnto.getNombreTipoEntidad()
											);

											ltda_informacionDocumento[NumericUtils.DEFAULT_INT_VALUE] = ltd_detalleDocumento;
											lten_turno.setDocumentos(ltda_informacionDocumento);
										}
									}

									{
										Collection<SolicitudMatricula> lcsm_matriculasTurno;
										int                            li_contadorMatriculas;

										lcsm_matriculasTurno      = lt_turnoActual.getMatriculasTurno();
										li_contadorMatriculas     = NumericUtils.DEFAULT_INT_VALUE;

										if(
										    CollectionUtils.isValidCollection(lcsm_matriculasTurno)
											    && (lcsm_matriculasTurno.size() > NumericUtils.DEFAULT_INT_VALUE)
										)
										{
											TipoMatricula[] ltma_matriculas;

											ltma_matriculas = new TipoMatricula[lcsm_matriculasTurno.size()];

											for(SolicitudMatricula lsm_actual : lcsm_matriculasTurno)
											{
												if(lsm_actual != null)
												{
													TipoMatricula ltm_matricula;

													ltm_matricula = new TipoMatricula();

													ltm_matricula.setOrip(lsm_actual.getIdCirculo());
													ltm_matricula.setNumeroMatricula("" + lsm_actual.getIdMatricula());
													ltm_matricula.setEstado(lsm_actual.getEstado());
													ltm_matricula.setAlertaVigente(
													    (lsm_actual.isAlerta()) ? EstadoCommon.SI_TXT
													                            : EstadoCommon.NO_TXT
													);

													ltma_matriculas[li_contadorMatriculas++] = ltm_matricula;
												}
											}

											lten_turno.setMatriculas(ltma_matriculas);
										}
									}

									{
										Collection<ConsultaTrazabilidad> lcct_trazabilidadTurno;
										int                              li_contadorTrazabilidad;

										lcct_trazabilidadTurno      = lt_turnoActual.getTrazabilidad();
										li_contadorTrazabilidad     = NumericUtils.DEFAULT_INT_VALUE;

										if(
										    CollectionUtils.isValidCollection(lcct_trazabilidadTurno)
											    && (lcct_trazabilidadTurno.size() > NumericUtils.DEFAULT_INT_VALUE)
										)
										{
											TipoTrazabilidad[] ltta_trazabilidadTurno;

											ltta_trazabilidadTurno = new TipoTrazabilidad[lcct_trazabilidadTurno.size()];

											for(ConsultaTrazabilidad lct_trazaActual : lcct_trazabilidadTurno)
											{
												if(lct_trazaActual != null)
												{
													TipoTrazabilidad ltt_trazabilidad;

													ltt_trazabilidad = new TipoTrazabilidad();

													{
														TurnoHistoria lth_th;

														lth_th = lct_trazaActual.getTurnoHistoria();

														if(lth_th != null)
														{
															{
																BigDecimal lbd_idEtapa;

																lbd_idEtapa = lth_th.getIdEtapa();

																ltt_trazabilidad.setEtapa(
																    (lbd_idEtapa != null) ? lbd_idEtapa.toString() : null
																);
															}

															ltt_trazabilidad.setEstadoActividad(
															    lth_th.getEstadoActividad()
															);
															ltt_trazabilidad.setUsuario(lth_th.getIdUsuario());
															ltt_trazabilidad.setFechaAsignacion(
															    DateUtils.getCalendar(lth_th.getFechaCreacion())
															);
															ltt_trazabilidad.setFechaReparto(
															    DateUtils.getCalendar(lth_th.getFechaReparto())
															);
															ltt_trazabilidad.setFechaInicioEtapa(
															    DateUtils.getCalendar(lth_th.getFechaInicial())
															);
															ltt_trazabilidad.setFechaFinalEtapa(
															    DateUtils.getCalendar(lth_th.getFechaFinal())
															);
														}
													}

													{
														Etapa le_etapa;

														le_etapa = lct_trazaActual.getEtapa();

														if(le_etapa != null)
															ltt_trazabilidad.setNombreEtapa(le_etapa.getNombre());
													}

													ltta_trazabilidadTurno[li_contadorTrazabilidad++] = ltt_trazabilidad;
												}
											}

											lten_turno.setTrazabilidades(ltta_trazabilidadTurno);
										}
									}

									ltena_turnos[li_contadorTurnos++] = lten_turno;
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_OPERACION_NO_EXITOSA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTrazabilidad", lb2be_e);

			ls_descripcionMensaje     = getErrorKey(lb2be_e);
			ltsctcm_codigoMensaje     = TipoSalidaConsultarTrazabilidadCodigoMensaje.value2;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarTrazabilidad", le_e);

			ls_descripcionMensaje     = le_e.getMessage();
			ltsctcm_codigoMensaje     = TipoSalidaConsultarTrazabilidadCodigoMensaje.value2;
		}

		{
			ltsct_response.setElementosnir(ltena_turnos);
			ltsct_response.setCodigoMensaje(ltsctcm_codigoMensaje);
			ltsct_response.setDescripcionMensaje(ls_descripcionMensaje);

			return ltsct_response;
		}
	}
}
