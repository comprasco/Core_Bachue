package com.bachue.snr.prosnr01.web.bean.registro;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.TramiteSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.DatosDelPredio;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.MatriculaSegregacionUi;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos la funcionalidad del Bean para retomar la solicitud
 *
 * @author garias
 */
@SessionScoped
@ManagedBean(name = "beanRetomarSolicitud")
public class BeanRetomarSolicitud extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5344798427558801509L;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad datos tramite solicitud. */
	private Collection<TramiteSolicitud> datosTramiteSolicitud;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad numero documento. */
	private String numeroDocumento;

	/** Propiedad tipo documento. */
	private String tipoDocumento;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos tramite solicitud.
	 *
	 * @param acts_cts asigna el valor a la propiedad datos tramite solicitud
	 */
	public void setDatosTramiteSolicitud(Collection<TramiteSolicitud> acts_cts)
	{
		datosTramiteSolicitud = acts_cts;
	}

	/**
	 * Retorna el valor de datos tramite solicitud.
	 *
	 * @return el valor de datos tramite solicitud
	 */
	public Collection<TramiteSolicitud> getDatosTramiteSolicitud()
	{
		return datosTramiteSolicitud;
	}

	/**
	 * Modifica el valor de numero documento.
	 *
	 * @param as_s asigna el valor a la propiedad numero documento
	 */
	public void setNumeroDocumento(String as_s)
	{
		numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de numero documento.
	 *
	 * @return el valor de numero documento
	 */
	public String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setDatosTramiteSolicitud(null);
		setNumeroDocumento(null);
		setTipoDocumento(null);
	}

	/**
	 * Obtener solicitudes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	public void obtenerSolicitudes()
	    throws B2BException, SQLException
	{
		try
		{
			Collection<TramiteSolicitud> lcts_cts;

			lcts_cts = irr_registroRemote.consultaRetomarSolicitud(
				    getTipoDocumento(), getNumeroDocumento(), getUserId()
				);

			if(CollectionUtils.isValidCollection(lcts_cts))
				setDatosTramiteSolicitud(lcts_cts);
			else
			{
				setDatosTramiteSolicitud(null);
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fRetomarSolicitud:idGrowl");
		}
	}

	/**
	 * Método para generar los datos de la solicitud en la pantalla de registro.
	 *
	 * @param as_isSolicitud Objeto que contiene el id de la solicitud para realizar la búsqueda
	 * @return devuelve el valor de String
	 */
	public String returnPages(String as_isSolicitud)
	{
		String       ls_returnPage;
		FacesContext lfc_context;

		ls_returnPage     = NavegacionCommon.REGISTRO;
		lfc_context       = FacesUtils.getFacesContext();

		try
		{
			if(StringUtils.isValidString(as_isSolicitud))
			{
				BeanRegistro  lbrc_bean;
				BeanDireccion lbd_beanDireccion;

				lbrc_bean             = (BeanRegistro)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REGISTRO, BeanRegistro.class
						);
				lbd_beanDireccion     = getBeanDireccion();

				if(lbrc_bean != null)
				{
					lbrc_bean.iniciar();

					Solicitud ls_s;
					String    ls_idPersona;

					ls_s = new Solicitud();

					ls_s.setIdSolicitud(as_isSolicitud);
					ls_s.setDocumento(true);

					ls_s             = irr_registroRemote.findSolicitudById(ls_s, getUserId());
					ls_idPersona     = ls_s.getIdPersona();

					{
						lbrc_bean.limpiarRegistro();
					}

					if(ls_s != null)
					{
						lbrc_bean.setEsRetomarSolicitud(true);

						lbrc_bean.setIdProceso(ls_s.getIdProceso());
						lbrc_bean.setIdSubproceso(ls_s.getIdSubproceso());
						lbrc_bean.setIdSubProcesoAnterior(ls_s.getIdSubproceso());

						Persona lp_p;

						lp_p = new Persona();

						lp_p.setIdPersona(ls_s.getIdPersona());

						lp_p = irr_registroRemote.findPersonaByIdPersona(lp_p, getUserId());

						if(lp_p != null)
							lbrc_bean.setPersona(lp_p);

						lbrc_bean.setSolicitud(ls_s);

						lbrc_bean.cambioDeTramite(true);

						lbrc_bean.cargarMediosNotCom(false);

						PersonaDireccion lpd_direccionResidencia;
						lpd_direccionResidencia = new PersonaDireccion();
						lpd_direccionResidencia.setIdPersona(ls_idPersona);
						lpd_direccionResidencia = irr_registroRemote.findDireccionByIdPersona(
							    lpd_direccionResidencia, EstadoCommon.R
							);

						if(lpd_direccionResidencia != null)
							lbd_beanDireccion.setDireccionResidencia(lpd_direccionResidencia);

						PersonaDireccion lpd_direccionCorrespondencia;
						lpd_direccionCorrespondencia = new PersonaDireccion();
						lpd_direccionCorrespondencia.setIdPersona(ls_idPersona);
						lpd_direccionCorrespondencia = irr_registroRemote.findDireccionByIdPersona(
							    lpd_direccionCorrespondencia, EstadoCommon.C
							);

						if(lpd_direccionCorrespondencia != null)
							lbd_beanDireccion.setDireccionCorrespondencia(lpd_direccionCorrespondencia);

						PersonaTelefono lpt_telFijo;
						lpt_telFijo = new PersonaTelefono();
						lpt_telFijo.setIdPersona(ls_idPersona);
						lpt_telFijo = irr_registroRemote.findTelefonoByIdPersona(lpt_telFijo, "F");

						if(lpt_telFijo != null)
						{
							lbrc_bean.setTelefonoFijo(lpt_telFijo);
							lbrc_bean.findIndicativoDepartamento();
						}

						PersonaTelefono lpt_telMovil;
						lpt_telMovil = new PersonaTelefono();
						lpt_telMovil.setIdPersona(ls_idPersona);
						lpt_telMovil = irr_registroRemote.findTelefonoByIdPersona(lpt_telMovil, "M");

						if(lpt_telMovil != null)
							lbrc_bean.setTelefonoMovil(lpt_telMovil);

						PersonaCorreoElectronico lpce_correo;
						lpce_correo = new PersonaCorreoElectronico();
						lpce_correo.setIdPersona(ls_idPersona);
						lpce_correo = irr_registroRemote.findCorreoByIdPersona(lpce_correo);

						if(lpce_correo != null)
							lbrc_bean.setCorreoElectronico(lpce_correo);
					}

					{
						Documento ld_documento;
						ld_documento     = new Documento();
						ld_documento     = irr_registroRemote.findDocumento(ls_s.getIdSolicitud());

						if(ld_documento != null)
						{
							lbrc_bean.setBgnDocumento(ld_documento);
							lbrc_bean.setIdTipoOficina(ld_documento.getIdTipoOficina());
							lbrc_bean.setIdTipoEntidad(ld_documento.getTipoEntidad());
							lbrc_bean.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());

							OficinaOrigen loo_oficinaOrigen;

							loo_oficinaOrigen = new OficinaOrigen();

							loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
							loo_oficinaOrigen.setVersion(ld_documento.getVersion());

							loo_oficinaOrigen = irr_registroRemote.findOficinaOrigen(loo_oficinaOrigen);

							lbrc_bean.findTipoOficina(null);

							if(loo_oficinaOrigen != null)
							{
								lbrc_bean.setIdPais(loo_oficinaOrigen.getIdPais());
								lbrc_bean.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
								lbrc_bean.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
							}

							lbrc_bean.validarActoEjecutoria(null);
						}
					}

					{
						//Genera los datos del acto
						lbrc_bean.setDatosActo(lbrc_bean.generarDatosActos(ls_s));

						if(!CollectionUtils.isValidCollection(lbrc_bean.getDetailsActo()))
							lbrc_bean.setDetailsActo(ls_s.getActosDetails());
					}

					{
						{
							Collection<DatosDelPredio>     lcddp_datosDelPredio;
							Collection<PredioActoIU>       lcpaiu_paui;
							Collection<SolicitudMatricula> lcsm_sm;
							int                            li_consecutivo;
							SolicitudMatriculaActo         lsma_sma;
							SolicitudMatricula             ltsm_sm;
							DatosDelPredio                 lddp_datosDelPredio;

							lcddp_datosDelPredio     = new ArrayList<DatosDelPredio>();
							lcpaiu_paui              = new ArrayList<PredioActoIU>();
							li_consecutivo           = 1;
							ltsm_sm                  = new SolicitudMatricula();
							lddp_datosDelPredio      = null;

							ltsm_sm.setIdSolicitud(ls_s.getIdSolicitud());

							lcsm_sm = irr_calificacionRemote.findSolicitudMatriculaOrderCirculo(ltsm_sm);

							if(CollectionUtils.isValidCollection(lcsm_sm))
							{
								Collection<PredioActoIU> lcpaiu_actosAsociadosGeneral;
								String                   ls_idCirculoActual;

								lcpaiu_actosAsociadosGeneral     = new ArrayList<PredioActoIU>();
								lcddp_datosDelPredio             = new ArrayList<DatosDelPredio>();
								ls_idCirculoActual               = "";

								for(SolicitudMatricula lsm_actual : lcsm_sm)
								{
									if(lsm_actual != null)
									{
										{
											PredioActoIU lpaiu_paiu;
											String       ls_matriculaConcatenada;
											String       ls_idCirculoIterador;

											lpaiu_paiu               = new PredioActoIU(lbrc_bean.getColumns());
											lsma_sma                 = new SolicitudMatriculaActo();
											ls_idCirculoIterador     = lsm_actual.getIdCirculo();

											if(!ls_idCirculoIterador.equalsIgnoreCase(ls_idCirculoActual))
											{
												if(lddp_datosDelPredio != null)
													lcddp_datosDelPredio.add(lddp_datosDelPredio);

												lcpaiu_actosAsociadosGeneral     = new ArrayList<PredioActoIU>();
												lddp_datosDelPredio              = new DatosDelPredio();
												ls_idCirculoActual               = ls_idCirculoIterador;

												lddp_datosDelPredio.setIdCirculo(ls_idCirculoActual);

												{
													CirculoRegistral lcr_circuloRegistral;

													lcr_circuloRegistral = new CirculoRegistral();

													lcr_circuloRegistral.setIdCirculo(ls_idCirculoActual);

													lcr_circuloRegistral = irr_registroRemote.findCirculoRegistralById(
														    lcr_circuloRegistral, getUserId()
														);

													if(lcr_circuloRegistral != null)
													{
														String ls_nombre;
														String ls_nombreCirculo;

														ls_nombre = lcr_circuloRegistral.getNombre();

														if(StringUtils.isValidString(ls_nombre))
															ls_nombreCirculo = ls_nombre;
														else
															ls_nombreCirculo = "sin información";

														lddp_datosDelPredio.setNombreCirculo(ls_nombreCirculo);
													}
												}
											}

											lsma_sma.setIdSolicitud(lsm_actual.getIdSolicitud());
											lsma_sma.setIdCirculo(ls_idCirculoIterador);
											lsma_sma.setIdMatricula(lsm_actual.getIdMatricula());

											{
												ls_matriculaConcatenada = lsm_actual.getIdCirculo() + "-"
													+ lsm_actual.getIdMatricula();

												lpaiu_paiu.setMatricula(ls_matriculaConcatenada);
												lpaiu_paiu.setIdMatricula(
												    NumericUtils.getLongWrapper(lsm_actual.getIdMatricula())
												);
												lpaiu_paiu.setIdCirculo(lsm_actual.getIdCirculo());

												if(lsm_actual.getExpedirCertificado().equals(EstadoCommon.S))
												{
													lpaiu_paiu.setCertificadoAsociado(true);
													lpaiu_paiu.setCantidad(
													    NumericUtils.getLongWrapper(
													        lsm_actual.getCantidadCertificados()
													    )
													);
												}

												{
													DireccionPredio ld_direccion;

													ld_direccion = new DireccionPredio();

													ld_direccion.setIdCirculo(lsm_actual.getIdCirculo());
													ld_direccion.setIdMatricula(
													    NumericUtils.getLongWrapper(lsm_actual.getIdMatricula())
													);

													ld_direccion = irr_registroRemote.findDireccionPredioById(
														    ld_direccion, getUserId()
														);

													if(ld_direccion != null)
													{
														String ls_address;

														ls_address = ld_direccion.getDireccion();

														if(StringUtils.isValidString(ls_address))
														{
															lpaiu_paiu.setDireccion(ls_address);
															lpaiu_paiu.setConsecutivo(li_consecutivo);
															li_consecutivo = li_consecutivo + 1;
															lddp_datosDelPredio.setFilePredialRendered(true);
														}
													}
												}
											}

											Collection<SolicitudMatriculaActo> lcsma_sma;

											lcsma_sma = irr_calificacionRemote.findSolicitudMatriculaActo(lsma_sma);

											if(CollectionUtils.isValidCollection(lcsma_sma))
											{
												for(SolicitudMatriculaActo lmpa_actual : lcsma_sma)
												{
													if(lmpa_actual != null)
														lpaiu_paiu.changeActoIU(lmpa_actual);
												}
											}

											lcpaiu_paui.add(lpaiu_paiu);
											lcpaiu_actosAsociadosGeneral.add(lpaiu_paiu);

											lddp_datosDelPredio.setActosAsociadosGeneral(lcpaiu_actosAsociadosGeneral);
											lddp_datosDelPredio.setColumns(
											    lbrc_bean.getColumnsActos(lddp_datosDelPredio)
											);
										}
									}
								}

								{
									Collection<MatriculaSegregacionUi> lcms_dataInfoPredial;
									lcms_dataInfoPredial = new ArrayList<MatriculaSegregacionUi>();

									Collection<MatriculaSegregacion> lcms_tmp;
									lcms_tmp = irr_registroRemote.findMatriculaSegregacionByIdSolicitud(
										    as_isSolicitud, null
										);

									if(CollectionUtils.isValidCollection(lcms_tmp))
									{
										long li_cont;
										li_cont = 1;

										for(MatriculaSegregacion lcms_actual : lcms_tmp)
										{
											if(lcms_actual != null)
											{
												MatriculaSegregacionUi lmsu_tmp;
												lmsu_tmp = new MatriculaSegregacionUi();

												lmsu_tmp.setUnidad(NumericUtils.getLongWrapper(li_cont));
												lmsu_tmp.setNombrePredio(lcms_actual.getNombrePredio());
												lmsu_tmp.setDireccion(lcms_actual.getDireccion());
												lmsu_tmp.setCertificadoAsociado(lcms_actual.isCertificadoAsociado());
												lmsu_tmp.setCantidad(lcms_actual.getCantidadCertificados());
												lmsu_tmp.setAreaTerreno(lcms_actual.getAreaTerreno());
												lmsu_tmp.setAreaPrivada(lcms_actual.getAreaPrivada());
												lmsu_tmp.setAreaConstruida(lcms_actual.getAreaConstruida());
												lmsu_tmp.setCoeficiente(lcms_actual.getCoeficiente());
												lmsu_tmp.setIdSolicitud(lcms_actual.getIdSolicitud());
												lmsu_tmp.setIdCirculoMatriz(lcms_actual.getIdCirculoMatriz());
												lmsu_tmp.setMatriculaMatriz(lcms_actual.getMatriculaMatriz());
												lmsu_tmp.setCertificadoAsociado(true);
												lmsu_tmp.setCantidad(NumericUtils.getLongWrapper(1L));

												lcms_dataInfoPredial.add(lmsu_tmp);
												li_cont = li_cont + 1;
											}
										}

										if(CollectionUtils.isValidCollection(lcms_dataInfoPredial))
											lddp_datosDelPredio.setDataInfoPredial(lcms_dataInfoPredial);
									}
								}

								if(lcddp_datosDelPredio != null)
									lcddp_datosDelPredio.add(lddp_datosDelPredio);
							}

							if(CollectionUtils.isValidCollection(lcpaiu_paui))
							{
								lbrc_bean.setActosAsociadosGeneral(lcpaiu_paui);
								lbrc_bean.setDatosDelPredio(lcddp_datosDelPredio);
								lbrc_bean.setSolicitudConTramite("true");

								if((lddp_datosDelPredio != null) && (lcddp_datosDelPredio != null))
								{
									lcddp_datosDelPredio.remove(lddp_datosDelPredio);

									lddp_datosDelPredio.setSeccionTipoMatricula(EstadoCommon.S);

									lcddp_datosDelPredio.add(lddp_datosDelPredio);
								}
							}
						}
					}

					{
						lbrc_bean.setDocumentoInterviniente(null);
						lbrc_bean.setTipoDocInterviniente(null);
						lbrc_bean.setIdTipoEntidadInterviniente(null);
						lbrc_bean.setIdEntidadExternaInterviniente(null);

						lbrc_bean.setIdPersonaSeleccionInter(null);
						lbrc_bean.setIdDirResSeleccionInter(null);
						lbrc_bean.setIdDirCorrSeleccionInter(null);
						lbrc_bean.setIdTelFijoSeleccionInter(null);
						lbrc_bean.setIdTelMovSeleccionInter(null);
						lbrc_bean.setIdCorreoSeleccionInter(null);

						lbrc_bean.setPersonaInter(null);
						lbd_beanDireccion.setDireccionResidencia(null);
						lbrc_bean.setDireccionResidenciaInter(null);
						lbd_beanDireccion.setDireccionCorrespondencia(null);
						lbrc_bean.setDireccionCorrespondenciaInter(null);
						lbrc_bean.setTelefonoFijoInter(null);
						lbrc_bean.setTelefonoMovilInter(null);
						lbrc_bean.setCorreoElectronicoInter(null);
						lbrc_bean.setSolicitudInter(null);

						lbrc_bean.setMismaDireccionCorrespondenciaInter(null);
						lbrc_bean.setRenderedInterviniente(false);
						lbrc_bean.setDeshabilitarDatosBasicosInter(false);
						lbrc_bean.setDeshabilitarCamposPorNitInter(false);
						lbd_beanDireccion.setDeshabilitarResidencia(false);
						lbrc_bean.setDeshabilitarResidenciaInter(false);
						lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
						lbrc_bean.setDeshabilitarCorrespondenciaInter(false);
						lbrc_bean.setDeshabilitarTelFijoInter(false);
						lbrc_bean.setDeshabilitarTelMovilInter(false);
						lbrc_bean.setDeshabilitarCorreoInter(false);
						lbrc_bean.setRegistroDatosConsultadosInter(null);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fRetomarSolicitud:idGrowl");
			ls_returnPage = null;
		}

		return ls_returnPage;
	}
}
