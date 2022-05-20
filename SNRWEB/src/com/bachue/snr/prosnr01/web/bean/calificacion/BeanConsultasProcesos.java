package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CampoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;

import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Analista de certificados especiales.
 *
 * @author Carlos Calderón
 */
@SessionScoped
@ManagedBean(name = "beanConsultasProcesos")
public class BeanConsultasProcesos extends BeanAnalistaDeCertificadosEspeciales implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2126826783814743842L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultasProcesos.class);

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/** {@inheritdoc} */
	@Override
	public String accionVolver()
	{
		try
		{
			clear();
			generarDatosBandeja(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.CONSULTAS_PROCESOS;
	}

	/** {@inheritdoc} */
	public void generarDataDetalle()
	    throws B2BException
	{
		try
		{
			generarDataDetalle(false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Generar torta.
	 *
	 * @return el valor de pie chart model
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Mostrar torta.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/** {@inheritdoc} */
	public String returnPages()
	    throws B2BException
	{
		return returnPages(null);
	}

	/** {@inheritdoc} */
	@Override
	public String returnPages(TramiteCantidad atc_tc)
	    throws B2BException
	{
		try
		{
			if(atc_tc != null)
			{
				String ls_idTurno;
				Turno  lt_turno;

				ls_idTurno     = atc_tc.getIdTurno();
				lt_turno       = new Turno(ls_idTurno);

				lt_turno = ier_entregaRemote.findTurnoById(lt_turno, getUserId());

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud(lt_turno.getIdSolicitud());

					{
						FacesContext                 lfc_context;
						BeanAnalisisConsultasExentas lbace_bace;

						lfc_context     = FacesUtils.getFacesContext();
						lbace_bace      = (BeanAnalisisConsultasExentas)lfc_context.getApplication()
								                                                       .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_ANALISIS_CONSULTAS_EXENTAS,
								    BeanAnalisisConsultasExentas.class
								);

						if(lbace_bace != null)
						{
							BeanPredioDocumentoActo lbpdab_bean;
							lbpdab_bean = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
								    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
								);

							if(lbpdab_bean != null)
							{
								Turno        lt_turnoTemp;
								String       ls_decisionCalificacion;
								Trazabilidad lt_trazaTemp;

								ls_decisionCalificacion     = null;
								lt_trazaTemp                = new Trazabilidad();
								lt_turnoTemp                = new Turno();

								lbpdab_bean.limpiarDatos();
								lbpdab_bean.setIdTurnoHistoria(atc_tc.getIdTurnoHistoria());
								lbpdab_bean.setIdTurnoConsulta(atc_tc.getIdTurno());

								lt_turnoTemp.setIdTurno(atc_tc.getIdTurno());

								lt_trazaTemp.setTurno(lt_turnoTemp);

								ls_decisionCalificacion = irr_referenceRemote.findDecisionCalificacion(lt_trazaTemp);

								lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
							}

							lbace_bace.limpiarAnalisisConsultasExentas();
							lbace_bace.setSolicitud(ls_solicitud);
							lbace_bace.setIdTurno(ls_idTurno);
							lbace_bace.mostrarPaneles(null);

							{
								String                     ls_user;
								String                     ls_remoteIp;
								String                     ls_localIp;
								Collection<CamposConsulta> lccc_dataCampos;

								ls_user             = getUserId();
								ls_remoteIp         = getRemoteIpAddress();
								ls_localIp          = getLocalIpAddress();
								lccc_dataCampos     = irr_registroRemote.camposPorCriterio(
									    ls_solicitud, ls_user, ls_localIp, ls_remoteIp
									);

								if(CollectionUtils.isValidCollection(lccc_dataCampos))
								{
									Iterator<CamposConsulta> licdb_iterator;

									licdb_iterator = lccc_dataCampos.iterator();

									if(licdb_iterator != null)
									{
										while(licdb_iterator.hasNext())
										{
											CamposConsulta      lcc_camposConsulta;
											boolean             lb_validCamposConsulta;
											CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

											lcc_camposConsulta           = licdb_iterator.next();
											lb_validCamposConsulta       = lcc_camposConsulta != null;
											lcdb_criteriosDeBusqueda     = lb_validCamposConsulta
												? lcc_camposConsulta.getCriteriosDeBusqueda() : null;

											if(lcdb_criteriosDeBusqueda != null)
											{
												Collection<CriteriosDeBusqueda> lccdb_criteriosDeBusqueda;

												lccdb_criteriosDeBusqueda = irr_registroRemote
														.consultarDetallesAgregados(
														    lcdb_criteriosDeBusqueda, ls_user, ls_localIp, ls_remoteIp
														);

												if(CollectionUtils.isValidCollection(lccdb_criteriosDeBusqueda))
												{
													lcc_camposConsulta.setCriteriosAgregados(lccdb_criteriosDeBusqueda);
													lbace_bace.setCamposConsulta(lccc_dataCampos);
												}
											}

											if(lb_validCamposConsulta)
												lbace_bace.setCriteriosDeConsulta(lcdb_criteriosDeBusqueda);
										}
									}

									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = new TurnoHistoria();

									lth_turnoHistoria.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(atc_tc.getIdTurnoHistoria())
									);

									validarFechaInicialEtapa(lth_turnoHistoria);
								}

								{
									Iterator<CamposConsulta> licc_iterator;
									boolean                  lb_camposConsulta;

									lb_camposConsulta     = false;
									licc_iterator         = lccc_dataCampos.iterator();

									while(licc_iterator.hasNext() && !lb_camposConsulta)
									{
										CamposConsulta lcc_camposConsulta;

										lcc_camposConsulta = licc_iterator.next();

										if(lcc_camposConsulta != null)
										{
											String ls_idTipoCriterioBusqueda;

											ls_idTipoCriterioBusqueda = lcc_camposConsulta.getIdTipoCriterioBusqueda();

											if(
											    StringUtils.isValidString(ls_idTipoCriterioBusqueda)
												    && ls_idTipoCriterioBusqueda.equalsIgnoreCase(
												        TipoCriterioBusquedaCommon.DOCUMENTO
												    )
											)
											{
												Collection<CamposConsulta> lccc_dataCamposConsulta;

												lccc_dataCamposConsulta = lcc_camposConsulta.getDataCamposConsulta();

												if(CollectionUtils.isValidCollection(lccc_dataCamposConsulta))
												{
													Iterator<CamposConsulta> licc_iteratorData;
													boolean                  lb_dataCamposConsulta;

													lb_dataCamposConsulta     = false;
													licc_iteratorData         = lccc_dataCamposConsulta.iterator();

													while(licc_iteratorData.hasNext() && !lb_dataCamposConsulta)
													{
														CamposConsulta lcc_camposConsultaCampos;

														lcc_camposConsultaCampos = licc_iteratorData.next();

														if(lcc_camposConsultaCampos != null)
														{
															String ls_campoCriterioBusqueda;

															ls_campoCriterioBusqueda = lcc_camposConsultaCampos
																	.getIdCampoCriterioBusqueda();

															if(
															    StringUtils.isValidString(ls_campoCriterioBusqueda)
																    && ls_campoCriterioBusqueda.equalsIgnoreCase(
																        CampoCriterioBusquedaCommon.DOCUMENTO_PAIS
																    )
															)
															{
																lcc_camposConsultaCampos.setValorCampo(
																    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
																);
																lb_dataCamposConsulta = true;
															}
														}
													}
												}

												lb_camposConsulta = true;
											}
										}
									}
								}
							}
						}
					}
				}

				return NavegacionCommon.ANALISIS_CONSULTAS_EXENTAS;
			}
			else
			{
				setIdProceso(FacesUtils.getStringFacesParameter("idProceso"));
				setIdSubProceso(FacesUtils.getStringFacesParameter("idSubproceso"));
				setIdEtapa(EtapaCommon.ID_ETAPA_CONSULTAS_PROCESOS);
				generarDataDetalle(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.DETALLE_CONSULTAS_PROCESOS;
	}
}
