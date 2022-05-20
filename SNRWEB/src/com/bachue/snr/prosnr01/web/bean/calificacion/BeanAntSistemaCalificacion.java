package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAccionPrediosAntSistema;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAntSistemaCalificacion.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanAntSistemaCalificacion")
public class BeanAntSistemaCalificacion extends BeanAccionPrediosAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1421979217837187090L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icdcpc data consulta ant sistema. */
	private Collection<DataConsultaPorCriterio> icdcpc_dataConsultaAntSistema;

	/** Propiedad icdcpc data consulta datos documento. */
	private Collection<DataConsultaPorCriterio> icdcpc_dataConsultaDatosDocumento;

	/** Propiedad icccas consulta criterios calificacion antiguo sistema. */
	private ConsultaCriteriosCalificacionAntiguoSistema icccas_consultaCriteriosCalificacionAntiguoSistema;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tipo consulta. */
	private String is_tipoConsulta;

	/** Propiedad ib grabacion. */
	private boolean ib_grabacion;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_procesoCorrecciones;

	/** {@inheritdoc} */
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de consulta criterios calificacion antiguo sistema.
	 *
	 * @param acccas_cccas asigna el valor a la propiedad consulta criterios calificacion antiguo sistema
	 */
	public void setConsultaCriteriosCalificacionAntiguoSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema acccas_cccas
	)
	{
		icccas_consultaCriteriosCalificacionAntiguoSistema = acccas_cccas;
	}

	/**
	 * Retorna el valor de consulta criterios calificacion antiguo sistema.
	 *
	 * @return el valor de consulta criterios calificacion antiguo sistema
	 */
	public ConsultaCriteriosCalificacionAntiguoSistema getConsultaCriteriosCalificacionAntiguoSistema()
	{
		if(icccas_consultaCriteriosCalificacionAntiguoSistema == null)
			icccas_consultaCriteriosCalificacionAntiguoSistema = new ConsultaCriteriosCalificacionAntiguoSistema();

		return icccas_consultaCriteriosCalificacionAntiguoSistema;
	}

	/**
	 * Modifica el valor de data consulta ant sistema.
	 *
	 * @param acdcpc_cdcpc asigna el valor a la propiedad data consulta ant sistema
	 */
	public void setDataConsultaAntSistema(Collection<DataConsultaPorCriterio> acdcpc_cdcpc)
	{
		icdcpc_dataConsultaAntSistema = acdcpc_cdcpc;
	}

	/**
	 * Retorna el valor de data consulta ant sistema.
	 *
	 * @return el valor de data consulta ant sistema
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaAntSistema()
	{
		return icdcpc_dataConsultaAntSistema;
	}

	/**
	 * Modifica el valor de data consulta datos documento.
	 *
	 * @param acdcpc_cdcpc asigna el valor a la propiedad data consulta datos documento
	 */
	public void setDataConsultaDatosDocumento(Collection<DataConsultaPorCriterio> acdcpc_cdcpc)
	{
		icdcpc_dataConsultaDatosDocumento = acdcpc_cdcpc;
	}

	/**
	 * Retorna el valor de data consulta datos documento.
	 *
	 * @return el valor de data consulta datos documento
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaDatosDocumento()
	{
		return icdcpc_dataConsultaDatosDocumento;
	}

	/**
	 * Modifica el valor de grabacion.
	 *
	 * @param ab_b asigna el valor a la propiedad grabacion
	 */
	public void setGrabacion(boolean ab_b)
	{
		ib_grabacion = ab_b;
	}

	/**
	 * Valida la propiedad grabacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en grabacion
	 */
	public boolean isGrabacion()
	{
		return ib_grabacion;
	}

	/**
	 * Sets the id circulo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void setIdCirculo()
	    throws B2BException
	{
		ConsultaCriteriosCalificacionAntiguoSistema lcccas_dataAntSistema;
		ConsultaCriteriosCalificacionDocumento      lcccd_dataDocumento;
		Turno                                       lt_turno;

		lcccas_dataAntSistema     = new ConsultaCriteriosCalificacionAntiguoSistema();
		lcccd_dataDocumento       = new ConsultaCriteriosCalificacionDocumento();
		lt_turno                  = irr_calificacionRemote.findTurno(getIdTurno());

		if(lt_turno != null)
		{
			String ls_idCirculo;
			ls_idCirculo = lt_turno.getIdCirculo();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				if(lcccas_dataAntSistema != null)
				{
					DatosAntSistema datosAntSistema;
					ZonaRegistral   lzr_zonaRegistral;

					datosAntSistema       = new DatosAntSistema();
					lzr_zonaRegistral     = new ZonaRegistral();

					datosAntSistema.setIdCirculo(ls_idCirculo);
					lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

					lzr_zonaRegistral = iasr_antiguoSistemaRemote.findZonaRegistralCirculo(lzr_zonaRegistral);

					if(lzr_zonaRegistral != null)
					{
						String ls_idDepartamento;
						String ls_idPais;

						ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
						ls_idPais             = lzr_zonaRegistral.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
							datosAntSistema.setIdPais(ls_idPais);

						if(StringUtils.isValidString(ls_idDepartamento))
							datosAntSistema.setIdDepartamento(ls_idDepartamento);
					}

					lcccas_dataAntSistema.setDatosAntSistema(datosAntSistema);
					setConsultaCriteriosCalificacionAntiguoSistema(lcccas_dataAntSistema);
				}

				if(lcccd_dataDocumento != null)
				{
					AnotacionPredio anotacionPredio;

					anotacionPredio = new AnotacionPredio();
					anotacionPredio.setIdCirculo(ls_idCirculo);

					lcccd_dataDocumento.setAnotacionPredio(anotacionPredio);
					setConsultaCriteriosCalificacionDocumento(lcccd_dataDocumento);
				}
			}
		}
	}

	/** {@inheritdoc} */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/** {@inheritdoc} */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/** {@inheritdoc} */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/** {@inheritdoc} */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;
		lcidt_datos = null;

		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
			lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					OficinaOrigen oficinaOrigen;

					oficinaOrigen = new OficinaOrigen();
					oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
					oficinaOrigen.setIdPais(ld_documento.getIdPais());
					oficinaOrigen.setIdDepartamento(ld_documento.getIdDepartamento());
					oficinaOrigen.setIdMunicipio(ld_documento.getIdMunicipio());

					lcidt_datos = irr_referenceRemote.findOficinaOrigen(oficinaOrigen);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public void setProcesoCorrecciones(boolean ab_b)
	{
		ib_procesoCorrecciones = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isProcesoCorrecciones()
	{
		return ib_procesoCorrecciones;
	}

	/** {@inheritdoc} */
	public void setTipoConsulta(String as_s)
	{
		is_tipoConsulta = as_s;
	}

	/** {@inheritdoc} */
	public String getTipoConsulta()
	{
		return is_tipoConsulta;
	}

	/**
	 * Carga la información de la pantalla de detalles del turno seleccionado.
	 *
	 * @return Enlace a la pantalla de detalle
	 */
	public String accionVolver()
	{
		String ls_return;

		ls_return = null;

		try
		{
			FacesContext            lfc_context;
			BeanPredioDocumentoActo lbpda_bean;

			lfc_context     = FacesUtils.getFacesContext();
			lbpda_bean      = (BeanPredioDocumentoActo)lfc_context.getApplication()
					                                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
					);

			if(lbpda_bean != null)
			{
				if(isGrabacion())
				{
					String ls_idTurnoHistoria;

					ls_idTurnoHistoria = getIdTurnoHistoria();

					lbpda_bean.limpiarDatos();
					lbpda_bean.setIdTurno(getIdTurno());
					lbpda_bean.generarDatosDocumento();
					lbpda_bean.generarDatosTramitesVinculados();
					lbpda_bean.setOcultarPaneles(true);
					lbpda_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS));
					lbpda_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
					lbpda_bean.setMotivoTramite(null);

					{
						BeanGrabacion lbg_bean;

						lbg_bean = (BeanGrabacion)lfc_context.getApplication()
								                                 .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
								);

						if(lbg_bean != null)
						{
							if(StringUtils.isValidString(ls_idTurnoHistoria))
							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria = new TurnoHistoria();

								lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

								lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

								if(lth_turnoHistoria != null)
								{
									lbg_bean.limpiar();
									lbg_bean.setTurnoHistoria(lth_turnoHistoria);
									lbg_bean.setDocumentoGenerado(false);
									lbg_bean.setProceso(ProcesoCommon.ID_PROCESO_37);
									lbg_bean.setEtapa(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);
									lbg_bean.setMotivoTramite(null);
									lbg_bean.cargarData();

									ls_return = NavegacionCommon.DETALLE_GRABACION;
								}
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
						}
					}
				}
				else
				{
					lbpda_bean.setEsAntiguoSistema(false);
					lbpda_bean.setOcultarPaneles(false);
					lbpda_bean.setIdTurno(getIdTurno());
					lbpda_bean.generarDatosAntSistema();
					lbpda_bean.generarDatosDocumento();
					lbpda_bean.generarDatosTramitesVinculados();
					lbpda_bean.obtenerInformacionASEtapa101();
					lbpda_bean.validarFechaVencimientoActo();
					lbpda_bean.setMotivoTramite(null);

					lbpda_bean.getMatriculasRangos();
					lbpda_bean.getMatriculasIndividuales();
					lbpda_bean.getMatriculasTmpRangos();
					lbpda_bean.getMatriculasTmpIndividuales();

					ls_return = NavegacionCommon.DETALLE_ACTO;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/** {@inheritdoc} */
	public void clear()
	{
		setDataConsultaAntSistema(null);
		setDataConsultaDatosDocumento(null);
		setConsultaCriteriosCalificacionAntiguoSistema(null);
		setConsultaCriteriosCalificacionDocumento(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
		setTipoConsulta(null);
		setGrabacion(false);
		setProcesoCorrecciones(false);
		setDetalleAntSistemaConsulta(null);
		setDetallesAntSistemaConsulta(null);

		super.clear();
	}

	/**
	 * Clear consulta.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void clearConsulta(String as_s)
	{
		switch(as_s)
		{
			case "AntSistema":
			{
				setDataConsultaAntSistema(null);

				ConsultaCriteriosCalificacionAntiguoSistema lcccas_antiguoSistema;
				lcccas_antiguoSistema = getConsultaCriteriosCalificacionAntiguoSistema();

				if(lcccas_antiguoSistema != null)
				{
					DatosAntSistema ldas_datosAntSistema;
					ldas_datosAntSistema = lcccas_antiguoSistema.getDatosAntSistema();

					if(ldas_datosAntSistema != null)
					{
						ConsultaCriteriosCalificacionAntiguoSistema lcccas_nuevo;
						DatosAntSistema                             ldas_nuevo;
						String                                      ls_idCirculo;
						String                                      ls_idPais;
						String                                      ls_idDepartamento;

						lcccas_nuevo          = new ConsultaCriteriosCalificacionAntiguoSistema();
						ldas_nuevo            = new DatosAntSistema();
						ls_idCirculo          = ldas_datosAntSistema.getIdCirculo();
						ls_idPais             = ldas_datosAntSistema.getIdPais();
						ls_idDepartamento     = ldas_datosAntSistema.getIdDepartamento();

						ldas_nuevo.setIdCirculo(ls_idCirculo);
						ldas_nuevo.setIdPais(ls_idPais);
						ldas_nuevo.setIdDepartamento(ls_idDepartamento);
						lcccas_nuevo.setDatosAntSistema(ldas_nuevo);

						setConsultaCriteriosCalificacionAntiguoSistema(lcccas_nuevo);
					}
				}
			}

			break;

			case "Documento":
			{
				setDataConsultaDatosDocumento(null);

				ConsultaCriteriosCalificacionDocumento lcccd_documento;
				lcccd_documento = getConsultaCriteriosCalificacionDocumento();

				if(lcccd_documento != null)
				{
					AnotacionPredio lap_anotacionPredio;
					lap_anotacionPredio = lcccd_documento.getAnotacionPredio();

					if(lap_anotacionPredio != null)
					{
						String ls_idCirculo;
						ls_idCirculo = lap_anotacionPredio.getIdCirculo();

						ConsultaCriteriosCalificacionDocumento lcccd_nuevo;
						lcccd_nuevo = new ConsultaCriteriosCalificacionDocumento();

						AnotacionPredio lap_nuevo;
						lap_nuevo = new AnotacionPredio();

						lap_nuevo.setIdCirculo(ls_idCirculo);
						lcccd_nuevo.setAnotacionPredio(lap_nuevo);

						setConsultaCriteriosCalificacionDocumento(lcccd_nuevo);
					}
				}
			}

			break;

			default:
				break;
		}
	}

	/**
	 * Consulta antiguo sistema.
	 *
	 * @param lcccas_consultaCriteriosAntiguoSistema correspondiente al valor del tipo de objeto ConsultaCriteriosCalificacionAntiguoSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaAntiguoSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		try
		{
			Collection<DataConsultaPorCriterio> lc_data;
			Collection<DataConsultaPorCriterio> ll_returnData;

			lc_data           = irr_calificacionRemote.findByDatosAntSistemaCalificacion(
				    lcccas_consultaCriteriosAntiguoSistema
				);
			ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

			if(CollectionUtils.isValidCollection(lc_data))
			{
				for(DataConsultaPorCriterio iterador : lc_data)
				{
					StringBuilder lsb_direccionCompleta;

					lsb_direccionCompleta = new StringBuilder();

					{
						String ls_tipoEje;

						ls_tipoEje = iterador.getIdTipoEjePrincipal();

						if(StringUtils.isValidString(ls_tipoEje))
						{
							TipoEje lte_tmp;

							lte_tmp = new TipoEje();

							lte_tmp.setIdTipoEje(ls_tipoEje);

							lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

							if(lte_tmp != null)
								lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + " ");
						}
					}

					lsb_direccionCompleta.append(StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " ");

					{
						String ls_tipoEje1;

						ls_tipoEje1 = iterador.getIdTipoEjeSecundario();

						if(StringUtils.isValidString(ls_tipoEje1))
						{
							TipoEje lte_tmp;

							lte_tmp = new TipoEje();

							lte_tmp.setIdTipoEje(ls_tipoEje1);

							lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

							if(lte_tmp != null)
								lsb_direccionCompleta.append(StringUtils.getStringNotNull(lte_tmp.getNombre()) + " ");
						}
					}

					lsb_direccionCompleta.append(StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " ");
					lsb_direccionCompleta.append(
					    StringUtils.getStringNotNull(iterador.getComplementoDireccion()) + " "
					);

					{
						String ls_tmp;

						ls_tmp = lsb_direccionCompleta.toString();

						if(StringUtils.isValidString(ls_tmp))
						{
							iterador.setDireccion(ls_tmp.trim());
							ll_returnData.add(iterador);
						}
					}
				}

				setDataConsultaAntSistema(ll_returnData);

				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaAntSistema(null);
			throw lb2be_e;
		}
	}

	/**
	 * Consulta la información ingresada por pantalla de un documento.
	 *
	 * @param acccd_cccd Objeto contenedor de los datos ingresados para la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDatosDocumento(ConsultaCriteriosCalificacionDocumento acccd_cccd)
	    throws B2BException
	{
		try
		{
			setConsultaRealizada(true);

			Collection<DataConsultaDatosDocumento> lcdcdd_dataDocumento;
			Collection<DataConsultaDatosDocumento> lcdcdd_dataDocuemntoReturn;

			lcdcdd_dataDocumento           = irr_calificacionRemote.findByDatosDocumento(acccd_cccd);
			lcdcdd_dataDocuemntoReturn     = new ArrayList<DataConsultaDatosDocumento>();

			if(CollectionUtils.isValidCollection(lcdcdd_dataDocumento))
			{
				acccd_cccd.setConsultado(true);

				for(DataConsultaDatosDocumento ldcdd_iterator : lcdcdd_dataDocumento)
				{
					Collection<DataConsultaPorCriterio> lcdcpc_data;
					Collection<DataConsultaPorCriterio> lcdcpc_returnData;

					lcdcpc_data           = ldcdd_iterator.getDataConsultaPorCriterio();
					lcdcpc_returnData     = new ArrayList<DataConsultaPorCriterio>();

					if(CollectionUtils.isValidCollection(lcdcpc_data))
					{
						for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_data)
						{
							StringBuilder lsb_direccionCompleta;
							lsb_direccionCompleta = new StringBuilder();

							{
								String ls_tipoEje;
								ls_tipoEje = ldcpc_iterador.getIdTipoEjePrincipal();

								if(StringUtils.isValidString(ls_tipoEje))
								{
									TipoEje lte_tmp;
									lte_tmp = new TipoEje();
									lte_tmp.setIdTipoEje(ls_tipoEje);

									lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

									if(lte_tmp != null)
										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
										);
								}
							}

							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(ldcpc_iterador.getDatoEjePrincipal()) + " "
							);

							{
								String ls_tipoEje1;
								ls_tipoEje1 = ldcpc_iterador.getIdTipoEjeSecundario();

								if(StringUtils.isValidString(ls_tipoEje1))
								{
									TipoEje lte_tmp;
									lte_tmp = new TipoEje();
									lte_tmp.setIdTipoEje(ls_tipoEje1);

									lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

									if(lte_tmp != null)
										lsb_direccionCompleta.append(
										    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
										);
								}
							}

							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(ldcpc_iterador.getDatoEjeSecundario()) + " "
							);
							lsb_direccionCompleta.append(
							    StringUtils.getStringNotNull(ldcpc_iterador.getComplementoDireccion()) + " "
							);

							{
								String ls_tmp;
								ls_tmp = lsb_direccionCompleta.toString();

								if(StringUtils.isValidString(ls_tmp))
								{
									ldcpc_iterador.setDireccion(ls_tmp.trim());
									lcdcpc_returnData.add(ldcpc_iterador);
								}
							}
						}
					}

					ldcdd_iterator.setDataConsultaPorCriterio(lcdcpc_returnData);
					setDataConsultaDatosDocumento(lcdcpc_returnData);
					lcdcdd_dataDocuemntoReturn.add(ldcdd_iterator);
				}

				Long                       ll_maxVersion;
				DataConsultaDatosDocumento dcdd_data;

				ll_maxVersion     = NumericUtils.getLongWrapper(0);
				dcdd_data         = null;

				for(DataConsultaDatosDocumento iterador : lcdcdd_dataDocuemntoReturn)
				{
					Long ll_version;
					ll_version = iterador.getVersion();

					if(ll_version.compareTo(ll_maxVersion) >= 0)
					{
						dcdd_data         = iterador;
						ll_maxVersion     = ll_version;
					}
				}

				lcdcdd_dataDocuemntoReturn = new ArrayList<DataConsultaDatosDocumento>();
				lcdcdd_dataDocuemntoReturn.add(dcdd_data);
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaDatosDocumento(null);
			throw lb2be_e;
		}
	}

	/** {@inheritdoc} */
	public String contar()
	{
		String ls_observaciones;
		char[] lca_arrayChar;
		int    li_contador;
		String as_result;

		ls_observaciones     = getObservaciones();
		li_contador          = 0;

		if(ls_observaciones != null)
		{
			lca_arrayChar     = ls_observaciones.toCharArray();
			li_contador       = lca_arrayChar.length;
		}

		as_result = Integer.toString(li_contador) + "/400";

		return as_result;
	}

	/** {@inheritdoc} */
	public void departamento()
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_idDepartamento;
				ls_idDepartamento = ld_documento.getIdDepartamento();

				if(StringUtils.isValidString(ls_idDepartamento))
					ld_documento.setIdDepartamento(ls_idDepartamento);

				ld_documento.setIdMunicipio(null);
				ld_documento.setIdOficinaOrigen(null);
			}
		}

		findMunicipios();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
			lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					String ls_pais;
					ls_pais = ld_documento.getIdPais();

					if(StringUtils.isValidString(ls_pais))
					{
						Departamento ld_parametros;

						ld_parametros = new Departamento();
						ld_parametros.setIdPais(ls_pais);
						lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipioAntSistema()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntSistema;
			DatosAntSistema                             ldas_datosAntSistema;

			lcccas_consultaCriteriosAntSistema     = getConsultaCriteriosCalificacionAntiguoSistema();
			ldas_datosAntSistema                   = null;

			if(lcccas_consultaCriteriosAntSistema != null)
				ldas_datosAntSistema = lcccas_consultaCriteriosAntSistema.getDatosAntSistema();

			if(ldas_datosAntSistema != null)
			{
				String ls_departamento;
				String ls_pais;

				ls_departamento     = ldas_datosAntSistema.getIdDepartamento();
				ls_pais             = ldas_datosAntSistema.getIdPais();

				if(!StringUtils.isValidString(ls_pais))
					ls_pais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

				if(StringUtils.isValidString(ls_departamento))
				{
					Municipio lm_parametros;
					lm_parametros = new Municipio();

					lm_parametros.setIdPais(ls_pais);
					lm_parametros.setIdDepartamento(ls_departamento);

					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_pais;
				String ls_departamento;

				ls_pais             = ld_documento.getIdPais();
				ls_departamento     = ld_documento.getIdDepartamento();

				if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
				{
					try
					{
						Municipio lm_parametros;
						lm_parametros = new Municipio();

						lm_parametros.setIdPais(ls_pais);
						lm_parametros.setIdDepartamento(ls_departamento);

						lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
					}
					catch(B2BException lb2be_e)
					{
						addMessage(lb2be_e);

						lcm_municipios = null;
					}
				}
			}
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Pais> findPaises()
	{
		Collection<Pais> lcp_paises;

		try
		{
			lcp_paises = irr_referenceRemote.findPaises(true, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/**
	 * Find tipo entidad documento.
	 *
	 * @param ab_boolean correspondiente al valor del tipo de objeto boolean
	 */
	public void findTipoEntidadDocumento(boolean ab_boolean)
	{
		boolean       lb_documento;
		Documento     ld_documento;
		OficinaOrigen loo_oficinaOrigen;
		String        ls_idTipoOficina;

		lb_documento          = false;
		ld_documento          = null;
		ls_idTipoOficina      = null;
		loo_oficinaOrigen     = null;

		if(ab_boolean)
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;

			lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consultaCriteriosCalificacionDocumento != null)
			{
				ld_documento     = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();
				lb_documento     = ld_documento != null;

				if(lb_documento)
					ls_idTipoOficina = ld_documento.getIdTipoOficina();
			}
		}
		else
		{
			ConsultaDatosDocumento lcdd_consultaDatosDoc;

			lcdd_consultaDatosDoc = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDoc != null)
			{
				ld_documento          = lcdd_consultaDatosDoc.getDocumento();
				lb_documento          = ld_documento != null;
				loo_oficinaOrigen     = lcdd_consultaDatosDoc.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
					ls_idTipoOficina = loo_oficinaOrigen.getIdTipoOficina();
			}
		}

		try
		{
			if(StringUtils.isValidString(ls_idTipoOficina))
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(ls_idTipoOficina);

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					if(lte_te != null)
					{
						if(ab_boolean && lb_documento)
							ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
						else if(loo_oficinaOrigen != null)
							loo_oficinaOrigen.setIdTipoEntidad(lte_te.getIdTipoEntidad());
					}
				}
			}

			if(lb_documento && (ld_documento.getIdTipoDocumento() == null))
				ld_documento.setTipoEntidad(null);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public void oficinaOrigen()
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lc_oficina;
			lc_oficina = getOficinaOrigen();

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fantSistemaCalificacion:idGrowl");
		}
	}

	/** {@inheritdoc} */
	public void pais()
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if(lcccd_consultaCriteriosCalificacionDocumento != null)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_pais;
				ls_pais = ld_documento.getIdPais();

				if(StringUtils.isValidString(ls_pais))
					ld_documento.setIdPais(ls_pais);
				else
					ld_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

				ld_documento.setIdDepartamento(null);
				ld_documento.setIdMunicipio(null);
			}
		}

		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}
	
	/**
	 * Método que a partir de la variable de grabación mostrara un dialog o enviará a antiguo sistema
	 * @return
	 */
	public String mostrarDialog()
	{
		String ls_result;

		ls_result = null;
		
		if(isGrabacion())
			ls_result = salvarAntSistema();
		else
			PrimeFaces.current().executeScript("PF('dlgAntiguoSistema').show();");
		
		return ls_result;
	}
	
	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvarAntSistema()
	{
		String ls_result;

		ls_result = null;

		try
		{
			FacesContext  lfc_context;
			String        ls_observaciones;
			TurnoHistoria lth_turnoHistoria;

			lfc_context           = FacesUtils.getFacesContext();
			ls_observaciones      = getObservaciones();
			lth_turnoHistoria     = new TurnoHistoria();

			if(!isConsultaRealizada())
				throw new B2BException(ErrorKeys.DEBE_REALIZAR_MINIMO_UNA_BUSQUEDA);

			if(!StringUtils.isValidString(ls_observaciones))
			{
				validateStyles(
				    "fantSistemaCalificacion:idObservacionesAntSistema", lfc_context, ls_observaciones, true
				);
				throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_AS);
			}

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_turnoHistoria.setObservaciones(ls_observaciones);
			lth_turnoHistoria.setIdUsuarioModificacion(getUserId());
			lth_turnoHistoria.setIpModificacion(getRemoteIpAddress());
			lth_turnoHistoria.setIpCreacion(getRemoteIpAddress());
			lth_turnoHistoria.setIdUsuarioCreacion(getUserId());

			if(isProcesoCorrecciones())
			{
				BeanCorreccion lbc_bean;
				lbc_bean = (BeanCorreccion)lfc_context.getApplication()
						                                  .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
						);

				if(lbc_bean != null)
				{
					lth_turnoHistoria.setIdEtapaSalvar(EtapaCommon.ID_ETAPA_CORRECCIONES);
					lth_turnoHistoria.setIdMotivoSalvar(MotivoTramiteCommon.ANTIGUO_SISTEMA_CORRECCIONES);

					irr_calificacionRemote.salvarAntSistemaCalificacion(lth_turnoHistoria);

					lbc_bean.clear();
					lbc_bean.generarDatosTramiteCantidad();

					ls_result = NavegacionCommon.ANALISIS_CORRECCION;
				}
			}
			else
			{
				BeanCalificacion lbc_bean;
				lbc_bean = (BeanCalificacion)lfc_context.getApplication()
						                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
						);

				if(lbc_bean != null)
				{
					if(isGrabacion())
					{
						String ls_idEtapa;

						ls_idEtapa = StringUtils.getString(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);

						lth_turnoHistoria.setIdEtapaSalvar(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);
						lth_turnoHistoria.setIdMotivoSalvar(
						    MotivoTramiteCommon.ANTIGUO_SISTEMA_GRABACION_DE_MATRICULAS
						);

						irr_calificacionRemote.salvarAntSistemaCalificacion(lth_turnoHistoria);

						lbc_bean.clear();
						lbc_bean.setIdEtapa(ls_idEtapa);
						lbc_bean.generarDatosTramiteCantidad(":fGrabacion", ls_idEtapa, true);

						ls_result = NavegacionCommon.GRABACION;
					}
					else
					{
						lth_turnoHistoria.setIdEtapaSalvar(EtapaCommon.ID_ETAPA_CALIFICACION);
						lth_turnoHistoria.setIdMotivoSalvar(MotivoTramiteCommon.ANTIGUO_SISTEMA);

						irr_calificacionRemote.salvarAntSistemaCalificacion(lth_turnoHistoria);

						lbc_bean.clear();
						lbc_bean.generarDatosTramiteCantidad();

						ls_result = NavegacionCommon.CALIFICACION;
					}

					setObservaciones(null);
					clearConsulta("AntSistema");
					clearConsulta("Documento");

					lbc_bean.clear();
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update("fantSistemaCalificacion:idGrowl");
		}

		return ls_result;
	}

	/**
	 * Metodo encargado de cargar los datos de tipo de oficina para documento antiguo sistema.
	 *
	 * @param ab_boolean Argumento de tipo <code>boolean</code> que determina si se debe cargar el tipo de entidad o no.
	 */
	public void tipoOficina(boolean ab_boolean)
	{
		tipoOficina(ab_boolean, true);
	}

	/**
	 * Metodo encargado de cargar los datos de tipo de oficina para documento antiguo sistema.
	 *
	 * @param ab_boolean Argumento de tipo <code>boolean</code> que determina si se debe cargar el tipo de entidad o no.
	 * @param ab_limpiarDatos Argumento de tipo <code>boolean</code> que determina si se debe limpiar los datos del documento o no.
	 */
	public void tipoOficina(boolean ab_boolean, boolean ab_limpiarDatos)
	{
		ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosCalificacionDocumento;
		lcccd_consultaCriteriosCalificacionDocumento = getConsultaCriteriosCalificacionDocumento();

		if((lcccd_consultaCriteriosCalificacionDocumento != null) && ab_limpiarDatos)
		{
			Documento ld_documento;
			ld_documento = lcccd_consultaCriteriosCalificacionDocumento.getDocumento();

			if(ld_documento != null)
			{
				String ls_tipoOficina;
				ls_tipoOficina = ld_documento.getIdTipoOficina();

				if(StringUtils.isValidString(ls_tipoOficina))
				{
					ld_documento.setIdTipoOficina(ls_tipoOficina);
					ld_documento.setIdPais(null);
					ld_documento.setIdDepartamento(null);
					ld_documento.setIdMunicipio(null);
				}
			}
		}

		findTipoEntidadDocumento(ab_boolean);
		findPaises();
		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Validar existencia antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaAntiguoSistema()
	    throws B2BException
	{
		try
		{
			ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema;
			FacesContext                                lfc_context;
			boolean                                     lb_focus;

			lcccas_consultaCriteriosAntiguoSistema     = getConsultaCriteriosCalificacionAntiguoSistema();
			lfc_context                                = FacesContext.getCurrentInstance();
			lb_focus                                   = true;

			if(lcccas_consultaCriteriosAntiguoSistema != null)
			{
				AnotacionPredio anotacionPredio;

				anotacionPredio = lcccas_consultaCriteriosAntiguoSistema.getAnotacionPredio();

				if(anotacionPredio != null)
				{
					DatosAntSistema ldas_datosAntSistema;

					ldas_datosAntSistema = lcccas_consultaCriteriosAntiguoSistema.getDatosAntSistema();

					if(ldas_datosAntSistema != null)
					{
						Long   ll_libro;
						String ls_idTipoPredio;

						ll_libro            = ldas_datosAntSistema.getIdLibroAntSistema();
						ls_idTipoPredio     = ldas_datosAntSistema.getIdTipoPredio();

						lb_focus     = validateStyles(
							    "fantSistemaCalificacion:idTipoPredioAntSistema", lfc_context, ls_idTipoPredio, lb_focus
							);

						lb_focus = validateStyles(
							    "fantSistemaCalificacion:idConsultaCriteriosAntSistemaLibro", lfc_context, ll_libro,
							    lb_focus
							);

						if(!NumericUtils.isValidLong(ll_libro))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

						if(!StringUtils.isValidString(ls_idTipoPredio))
							throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

						lcccas_consultaCriteriosAntiguoSistema.setConsultaVacia(true);
						consultaAntiguoSistema(lcccas_consultaCriteriosAntiguoSistema);
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}
	}

	/** {@inheritdoc} */
	public void validarExistenciaDocumento()
	    throws B2BException
	{
		try
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consultaCriteriosDocuemento;
			FacesContext                           lfc_context;
			boolean                                lb_focus;

			lcccd_consultaCriteriosDocuemento     = getConsultaCriteriosCalificacionDocumento();
			lfc_context                           = FacesContext.getCurrentInstance();
			lb_focus                              = true;

			if(lcccd_consultaCriteriosDocuemento != null)
			{
				Documento ld_documento;

				ld_documento = lcccd_consultaCriteriosDocuemento.getDocumento();

				if(ld_documento != null)
				{
					String ls_idDocumento;
					ls_idDocumento     = ld_documento.getIdTipoDocumento();

					lb_focus = validateStyles(
						    ":fantSistemaCalificacion:idConsultaCriteriosDocumentoTipoDocumento", lfc_context,
						    ls_idDocumento, lb_focus
						);

					String ls_numeroDocumento;
					ls_numeroDocumento     = ld_documento.getNumero();

					lb_focus = validateStyles(
						    ":fantSistemaCalificacion:idConsultaCriteriosDocumentoNumeroDocumento", lfc_context,
						    ls_numeroDocumento, lb_focus
						);

					Date ld_fechaDocumento;
					ld_fechaDocumento     = ld_documento.getFechaDocumento();

					lb_focus = validateStyles(
						    ":fantSistemaCalificacion:idConsultaCriteriosDocumentoFechaDocumento", lfc_context,
						    ld_fechaDocumento, lb_focus
						);

					String ls_tipoOficina;
					ls_tipoOficina     = ld_documento.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":fantSistemaCalificacion:idConsultaCriteriosDocumentoTipoOficina", lfc_context,
						    ls_tipoOficina, lb_focus
						);

					String ls_tipoEntidad;
					ls_tipoEntidad     = ld_documento.getTipoEntidad();

					lb_focus = validateStyles(
						    "fantSistemaCalificacion:idConsultaCriteriosDocumentoTipoEntidad", lfc_context,
						    ls_tipoEntidad, lb_focus
						);

					String ls_idpais;
					ls_idpais     = ld_documento.getIdPais();

					lb_focus = validateStyles(
						    "fantSistemaCalificacion:idConsultaCriteriosDocumentoPaisDocumento", lfc_context, ls_idpais,
						    lb_focus
						);

					String ls_idDepartamento;
					ls_idDepartamento     = ld_documento.getIdDepartamento();

					lb_focus = validateStyles(
						    "fantSistemaCalificacion:idConsultaCriteriosDocumentoDepartamentoDocumento", lfc_context,
						    ls_idDepartamento, lb_focus
						);

					String ls_idMunicipio;
					ls_idMunicipio     = ld_documento.getIdMunicipio();

					lb_focus = validateStyles(
						    "fantSistemaCalificacion:idConsultaCriteriosDocumentoMunicipioDocumento", lfc_context,
						    ls_idMunicipio, lb_focus
						);

					String ls_oficinaOrigen;
					ls_oficinaOrigen     = ld_documento.getIdOficinaOrigen();

					lb_focus = validateStyles(
						    "fantSistemaCalificacion:idConsultaCriteriosDocumentoOficinaOrigen", lfc_context,
						    ls_oficinaOrigen, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					if(!StringUtils.isValidString(ls_numeroDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NUMERO_DOC);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

					if(!StringUtils.isValidString(ls_tipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

					if(!StringUtils.isValidString(ls_tipoEntidad))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);

					if(!StringUtils.isValidString(ls_idpais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

					if(!StringUtils.isValidString(ls_oficinaOrigen))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
				}

				lcccd_consultaCriteriosDocuemento.setConsultaVacia(true);
				setConsultaCriteriosCalificacionDocumento(lcccd_consultaCriteriosDocuemento);
				consultaDatosDocumento(lcccd_consultaCriteriosDocuemento);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
		}
	}
}
