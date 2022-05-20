package com.bachue.snr.prosnr01.web.bean.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LibroAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD.ConsultaSGDRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaPredioRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ConsultaSalvedad;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanDetallePredio;
import com.bachue.snr.prosnr01.web.bean.consulta.SGD.BeanConsultaSGD;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.apache.myfaces.custom.div.Div;

import org.primefaces.PrimeFaces;

import org.primefaces.component.commandbutton.CommandButton;

import org.primefaces.component.commandlink.CommandLink;

import org.primefaces.component.dashboard.Dashboard;

import org.primefaces.component.panel.Panel;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import java.io.IOException;
import java.io.Serializable;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.application.Application;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIColumn;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaPredio.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaPredio")
@SessionScoped
public class BeanConsultaPredio extends BeanConsultaSGD implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -418947579265215818L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fConsultaEstadoPredio:globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaPredio.class);

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad ioant alerta naturaleza juridica. */
	private AlertaNaturalezaJuridica ioant_alertaNaturalezaJuridica;

	/** Propiedad ia anotacion. */
	private Anotacion ia_anotacion;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad iap area predio. */
	private AreaPredio iap_areaPredio;

	/** Propiedad icl cabida linderos. */
	private CabidaLinderos icl_cabidaLinderos;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icap area terreno predio. */
	private Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> icap_areaTerrenoPredio;

	/** Propiedad iccp consulta predios listados. */
	private Collection<ConsultaPredio> iccp_consultaPrediosListados;

	/** Propiedad iccs salvedades. */
	private Collection<ConsultaSalvedad> iccs_salvedades;

	/** Propiedad icd paginacion anotacion. */
	private Collection<Dashboard> icd_paginacionAnotacion;

	/** Propiedad icdas detalles ant sistema. */
	private Collection<DetalleAntSistema> icdas_detallesAntSistema;

	/** Propiedad icddp direcciones temporales. */
	private Collection<DireccionDelPredio> icddp_direccionesTemporales;

	/** Propiedad icoo oficinas documento detalle ant sistema. */
	private Collection<OficinaOrigen> icoo_oficinasDocumentoDetalleAntSistema;

	/** Propiedad icps predios segregados. */
	private Collection<PredioSegregado> icps_prediosSegregados;

	/** Propiedad ict turno bloqueo. */
	private Collection<Turno> ict_turnoBloqueo;

	/** Propiedad ict turnos asociados. */
	private Collection<Turno> ict_turnosAsociados;

	/** Propiedad icta all actos. */
	private Collection<NaturalezaJuridica> icta_allActos;

	/** Propiedad icto tipos oficina documento ant sis. */
	private Collection<TipoOficina> icto_tiposOficinaDocumentoAntSis;

	/** Propiedad iddp direcciones predio. */
	private Collection<DireccionDelPredio> iddp_direccionesPredio;

	/** Propiedad icp consulta predio. */
	private ConsultaPredio icp_consultaPredio;

	/** Propiedad icepe consulta predio remote. */
	@EJB
	private ConsultaPredioRemote icepe_consultaPredioRemote;
	@EJB
	private ConsultaSGDRemote     irr_consultaSGDRemote;

	/** Propiedad idb data model. */
	private Dashboard idb_dataModel;

	/** Propiedad idas antiguo sistema data. */
	private DatosAntSistema idas_antiguoSistemaData;

	/** Propiedad idas dato ant sistema cargado. */
	private DatosAntSistema idas_datoAntSistemaCargado;

	/** Propiedad idb datos basicos. */
	private DatosBasicos idb_datosBasicos;

	/** Propiedad idas detalle ant sistema cargado. */
	private DetalleAntSistema idas_detalleAntSistemaCargado;

	/** Propiedad idp direccion predio. */
	private DireccionDelPredio idp_direccionPredio;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad id documento detalle cargado. */
	private Documento id_documentoDetalleCargado;

	/** Propiedad iod documento criterios. */
	private Documento iod_documentoCriterios;

	/** Propiedad iols panels. */
	private List<String> iols_panels;

	/** Propiedad il anotacion criterio str. */
	private Long il_anotacionCriterioStr;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il matricula link. */
	private Long il_matriculaLink;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iop info persona. */
	private Persona iop_infoPersona;

	/** Propiedad iopr predio registro. */
	private PredioRegistro iopr_predioRegistro;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iorc detalle anotacion. */
	private RegistroCalificacion iorc_detalleAnotacion;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is circulo link. */
	private String is_circuloLink;

	/** Propiedad is cod especifiacion. */
	private String is_codEspecifiacion;

	/** Propiedad is descripcion tipo uso suelo. */
	private String is_descripcionTipoUsoSuelo;

	/** Propiedad is estado predio. */
	private String is_estadoPredio;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is imagen mapa. */
	private String is_imagenMapa;

	/** Propiedad is justificacion cambio. */
	private String is_justificacionCambio;

	/** Propiedad is motivo cambio estado. */
	private String is_motivoCambioEstado;

	/** Propiedad is naturaleza criterio. */
	private String is_naturalezaCriterio;

	/** Propiedad is_tipoMatricula */
	private String is_tipoMatricula;

	/** Propiedad ias valor seleccionado. */
	private String[] ias_valorSeleccionado;

	/** Propiedad ib activar detalle. */
	private boolean ib_activarDetalle;

	/** Propiedad ib anotacion criterio. */
	private boolean ib_anotacionCriterio;

	/** Propiedad ib cierre folio. */
	private boolean ib_cierreFolio;

	/** Propiedad ib consulta total criterios. */
	private boolean ib_consultaTotalCriterios;

	/** Propiedad ib documento criterio. */
	private boolean ib_documentoCriterio;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/** Propiedad ib existe mapa geografico. */
	private boolean ib_existeMapaGeografico;

	/** Propiedad ib interviniente criterio. */
	private boolean ib_intervinienteCriterio;

	/** Propiedad ib libro matriculas ant. */
	private boolean ib_libroMatriculasAnt;

	/** Propiedad ib libro segundo ant. */
	private boolean ib_libroSegundoAnt;

	/** Propiedad ib naturaleza juridica criterio. */
	private boolean ib_naturalezaJuridicaCriterio;

	/** Propiedad ib rendered boton volver. */
	private boolean ib_renderedBotonVolver;

	/** Propiedad ib retorno bandejas. */
	private boolean ib_retornoBandejas;

	/** Propiedad ib volver detalle predio. */
	private boolean ib_volverDetallePredio;

	/** Propiedad ii total anotaciones. */
	private int ii_totalAnotaciones;

	/**
	 * Modifica el valor de activar detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad activar detalle
	 */
	public void setActivarDetalle(boolean ab_b)
	{
		ib_activarDetalle = ab_b;
	}

	/**
	 * Valida la propiedad activar detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en activar detalle
	 */
	public boolean isActivarDetalle()
	{
		return ib_activarDetalle;
	}

	/**
	 * Modifica el valor de alerta naturaleza juridica.
	 *
	 * @param aoanj_anj asigna el valor a la propiedad alerta naturaleza juridica
	 */
	public void setAlertaNaturalezaJuridica(AlertaNaturalezaJuridica aoanj_anj)
	{
		ioant_alertaNaturalezaJuridica = aoanj_anj;
	}

	/**
	 * Retorna el valor de alerta naturaleza juridica.
	 *
	 * @return el valor de alerta naturaleza juridica
	 */
	public AlertaNaturalezaJuridica getAlertaNaturalezaJuridica()
	{
		if(ioant_alertaNaturalezaJuridica == null)
			ioant_alertaNaturalezaJuridica = new AlertaNaturalezaJuridica();

		return ioant_alertaNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de all actos.
	 *
	 * @param acc_cta asigna el valor a la propiedad all actos
	 */
	public void setAllActos(Collection<NaturalezaJuridica> acc_cta)
	{
		icta_allActos = acc_cta;
	}

	/**
	 * Retorna el valor de all actos.
	 *
	 * @return el valor de all actos
	 */
	public Collection<NaturalezaJuridica> getAllActos()
	{
		return icta_allActos;
	}

	/**
	 * Modifica el valor de anotacion.
	 *
	 * @param aa_a asigna el valor a la propiedad anotacion
	 */
	public void setAnotacion(Anotacion aa_a)
	{
		ia_anotacion = aa_a;
	}

	/**
	 * Retorna el valor de anotacion.
	 *
	 * @return el valor de anotacion
	 */
	public Anotacion getAnotacion()
	{
		if(ia_anotacion == null)
			ia_anotacion = new Anotacion();

		return ia_anotacion;
	}

	/**
	 * Modifica el valor de anotacion criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion criterio
	 */
	public void setAnotacionCriterio(boolean ab_b)
	{
		ib_anotacionCriterio = ab_b;
	}

	/**
	 * Valida la propiedad anotacion criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion criterio
	 */
	public boolean isAnotacionCriterio()
	{
		return ib_anotacionCriterio;
	}

	/**
	 * Modifica el valor de anotacion criterio str.
	 *
	 * @param as_s asigna el valor a la propiedad anotacion criterio str
	 */
	public void setAnotacionCriterioStr(Long as_s)
	{
		il_anotacionCriterioStr = as_s;
	}

	/**
	 * Retorna el valor de anotacion criterio str.
	 *
	 * @return el valor de anotacion criterio str
	 */
	public Long getAnotacionCriterioStr()
	{
		return il_anotacionCriterioStr;
	}

	/**
	 * Modifica el valor de antiguo sistema data.
	 *
	 * @param aoas_as asigna el valor a la propiedad antiguo sistema data
	 */
	public void setAntiguoSistemaData(DatosAntSistema aoas_as)
	{
		idas_antiguoSistemaData = aoas_as;
	}

	/**
	 * Retorna el valor de antiguo sistema data.
	 *
	 * @return el valor de antiguo sistema data
	 */
	public DatosAntSistema getAntiguoSistemaData()
	{
		if(idas_antiguoSistemaData == null)
			idas_antiguoSistemaData = new DatosAntSistema();

		return idas_antiguoSistemaData;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de area predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad area predio
	 */
	public void setAreaPredio(AreaPredio aap_ap)
	{
		iap_areaPredio = aap_ap;
	}

	/**
	 * Retorna el valor de area predio.
	 *
	 * @return el valor de area predio
	 */
	public AreaPredio getAreaPredio()
	{
		if(iap_areaPredio == null)
			iap_areaPredio = new AreaPredio();

		return iap_areaPredio;
	}

	/**
	 * Modifica el valor de area terreno predio.
	 *
	 * @param acap_cap asigna el valor a la propiedad area terreno predio
	 */
	public void setAreaTerrenoPredio(Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> acap_cap)
	{
		icap_areaTerrenoPredio = acap_cap;
	}

	/**
	 * Retorna el valor de area terreno predio.
	 *
	 * @return el valor de area terreno predio
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> getAreaTerrenoPredio()
	{
		return icap_areaTerrenoPredio;
	}

	/**
	 * Modifica el valor de area UI.
	 *
	 * @param aaui_aaui asigna el valor a la propiedad area UI
	 */
	public void setAreaUI(AccAreaUI aaui_aaui)
	{
		iaaui_areaUI = aaui_aaui;
	}

	/**
	 * Retorna el valor de area UI.
	 *
	 * @return el valor de area UI
	 */
	public AccAreaUI getAreaUI()
	{
		if(iaaui_areaUI == null)
			iaaui_areaUI = new AccAreaUI();

		return iaaui_areaUI;
	}

	/**
	 * Modifica el valor de cabida linderos.
	 *
	 * @param acl_cabidaLinderos asigna el valor a la propiedad cabida linderos
	 */
	public void setCabidaLinderos(CabidaLinderos acl_cabidaLinderos)
	{
		icl_cabidaLinderos = acl_cabidaLinderos;
	}

	/**
	 * Retorna el valor de cabida linderos.
	 *
	 * @return el valor de cabida linderos
	 */
	public CabidaLinderos getCabidaLinderos()
	{
		if(icl_cabidaLinderos == null)
			icl_cabidaLinderos = new CabidaLinderos();

		return icl_cabidaLinderos;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad cierreFolio por cierreFolio
	 */
	public void setCierreFolio(boolean ab_b)
	{
		ib_cierreFolio = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad cierreFolio
	 */
	public boolean isCierreFolio()
	{
		return ib_cierreFolio;
	}

	/**
	 * Modifica el valor de circulo link.
	 *
	 * @param as_s asigna el valor a la propiedad circulo link
	 */
	public void setCirculoLink(String as_s)
	{
		is_circuloLink = as_s;
	}

	/**
	 * Retorna el valor de circulo link.
	 *
	 * @return el valor de circulo link
	 */
	public String getCirculoLink()
	{
		return is_circuloLink;
	}

	/**
	 * Modifica el valor de cod especifiacion.
	 *
	 * @param as_s asigna el valor a la propiedad cod especifiacion
	 */
	public void setCodEspecifiacion(String as_s)
	{
		is_codEspecifiacion = as_s;
	}

	/**
	 * Retorna el valor de cod especifiacion.
	 *
	 * @return el valor de cod especifiacion
	 */
	public String getCodEspecifiacion()
	{
		return is_codEspecifiacion;
	}

	/**
	 * Modifica el valor de consulta predio.
	 *
	 * @param acp_cp asigna el valor a la propiedad consulta predio
	 */
	public void setConsultaPredio(ConsultaPredio acp_cp)
	{
		icp_consultaPredio = acp_cp;
	}

	/**
	 * Retorna el valor de consulta predio.
	 *
	 * @return el valor de consulta predio
	 */
	public ConsultaPredio getConsultaPredio()
	{
		return icp_consultaPredio;
	}

	/**
	 * Modifica el valor de consulta predios listados.
	 *
	 * @param accp_ccp asigna el valor a la propiedad consulta predios listados
	 */
	public void setConsultaPrediosListados(Collection<ConsultaPredio> accp_ccp)
	{
		iccp_consultaPrediosListados = accp_ccp;
	}

	/**
	 * Retorna el valor de consulta predios listados.
	 *
	 * @return el valor de consulta predios listados
	 */
	public Collection<ConsultaPredio> getConsultaPrediosListados()
	{
		return iccp_consultaPrediosListados;
	}

	/**
	 * Modifica el valor de consulta total criterios.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta total criterios
	 */
	public void setConsultaTotalCriterios(boolean ab_b)
	{
		ib_consultaTotalCriterios = ab_b;
	}

	/**
	 * Valida la propiedad consulta total criterios.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta total criterios
	 */
	public boolean isConsultaTotalCriterios()
	{
		return ib_consultaTotalCriterios;
	}

	/**
	 * Modifica el valor de data model.
	 *
	 * @param ad_d asigna el valor a la propiedad data model
	 */
	public void setDataModel(Dashboard ad_d)
	{
		idb_dataModel = ad_d;
	}

	/**
	 * Retorna el valor de data model.
	 *
	 * @return el valor de data model
	 */
	public Dashboard getDataModel()
	{
		return idb_dataModel;
	}

	/**
	 * Modifica el valor de dato ant sistema cargado.
	 *
	 * @param adas_das asigna el valor a la propiedad dato ant sistema cargado
	 */
	public void setDatoAntSistemaCargado(DatosAntSistema adas_das)
	{
		idas_datoAntSistemaCargado = adas_das;
	}

	/**
	 * Retorna el valor de dato ant sistema cargado.
	 *
	 * @return el valor de dato ant sistema cargado
	 */
	public DatosAntSistema getDatoAntSistemaCargado()
	{
		if(idas_datoAntSistemaCargado == null)
			idas_datoAntSistemaCargado = new DatosAntSistema();

		return idas_datoAntSistemaCargado;
	}

	/**
	 * Modifica el valor de datos basicos.
	 *
	 * @param adb_db asigna el valor a la propiedad datos basicos
	 */
	public void setDatosBasicos(DatosBasicos adb_db)
	{
		idb_datosBasicos = adb_db;
	}

	/**
	 * Retorna el valor de datos basicos.
	 *
	 * @return el valor de datos basicos
	 */
	public DatosBasicos getDatosBasicos()
	{
		if(idb_datosBasicos == null)
			idb_datosBasicos = new DatosBasicos();

		return idb_datosBasicos;
	}

	/**
	 * Modifica el valor de descripcion tipo uso suelo.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion tipo uso suelo
	 */
	public void setDescripcionTipoUsoSuelo(String as_s)
	{
		is_descripcionTipoUsoSuelo = as_s;
	}

	/**
	 * Retorna el valor de descripcion tipo uso suelo.
	 *
	 * @return el valor de descripcion tipo uso suelo
	 */
	public String getDescripcionTipoUsoSuelo()
	{
		if(!StringUtils.isValidString(is_descripcionTipoUsoSuelo))
		{
			try
			{
				AccAreaUI laau_areaUI = getAreaUI();

				if(laau_areaUI != null)
				{
					AccAreaPredio laap_areaPredio;

					laap_areaPredio = laau_areaUI.getAreaPredio();

					if(laap_areaPredio != null)
					{
						TipoUsoSuelo ltus_tusTemp;

						ltus_tusTemp = new TipoUsoSuelo();

						ltus_tusTemp.setIdTipoUsoSuelo(laap_areaPredio.getTipoSuelo());

						ltus_tusTemp = ipr_parameterRemote.findTipoUsoPredioById(ltus_tusTemp);

						if(ltus_tusTemp != null)
							setDescripcionTipoUsoSuelo(ltus_tusTemp.getDescription());
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return is_descripcionTipoUsoSuelo;
	}

	/**
	 * Modifica el valor de detalle anotacion.
	 *
	 * @param aorc_rc asigna el valor a la propiedad detalle anotacion
	 */
	public void setDetalleAnotacion(RegistroCalificacion aorc_rc)
	{
		iorc_detalleAnotacion = aorc_rc;
	}

	/**
	 * Retorna el valor de detalle anotacion.
	 *
	 * @return el valor de detalle anotacion
	 */
	public RegistroCalificacion getDetalleAnotacion()
	{
		if(iorc_detalleAnotacion == null)
			iorc_detalleAnotacion = new RegistroCalificacion();

		return iorc_detalleAnotacion;
	}

	/**
	 * Modifica el valor de detalle ant sistema cargado.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sistema cargado
	 */
	public void setDetalleAntSistemaCargado(DetalleAntSistema adas_das)
	{
		idas_detalleAntSistemaCargado = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sistema cargado.
	 *
	 * @return el valor de detalle ant sistema cargado
	 */
	public DetalleAntSistema getDetalleAntSistemaCargado()
	{
		if(idas_detalleAntSistemaCargado == null)
			idas_detalleAntSistemaCargado = new DetalleAntSistema();

		return idas_detalleAntSistemaCargado;
	}

	/**
	 * Modifica el valor de detalles ant sistema.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad detalles ant sistema
	 */
	public void setDetallesAntSistema(Collection<DetalleAntSistema> acdas_cdas)
	{
		icdas_detallesAntSistema = acdas_cdas;
	}

	/**
	 * Retorna el valor de detalles ant sistema.
	 *
	 * @return el valor de detalles ant sistema
	 */
	public Collection<DetalleAntSistema> getDetallesAntSistema()
	{
		return icdas_detallesAntSistema;
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param iddp_d asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(DireccionDelPredio iddp_d)
	{
		idp_direccionPredio = iddp_d;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public DireccionDelPredio getDireccionPredio()
	{
		if(idp_direccionPredio == null)
			idp_direccionPredio = new DireccionDelPredio();

		return idp_direccionPredio;
	}

	/**
	 * Modifica el valor de direcciones predio.
	 *
	 * @param acdp_dp asigna el valor a la propiedad direcciones predio
	 */
	public void setDireccionesPredio(Collection<DireccionDelPredio> acdp_dp)
	{
		iddp_direccionesPredio = acdp_dp;
	}

	/**
	 * Retorna el valor de direcciones predio.
	 *
	 * @return el valor de direcciones predio
	 */
	public Collection<DireccionDelPredio> getDireccionesPredio()
	{
		if(iddp_direccionesPredio == null)
			iddp_direccionesPredio = new LinkedList<DireccionDelPredio>();

		return iddp_direccionesPredio;
	}

	/**
	 * Modifica el valor de direcciones temporales.
	 *
	 * @param acddp_cddp asigna el valor a la propiedad direcciones temporales
	 */
	public void setDireccionesTemporales(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesTemporales = acddp_cddp;
	}

	/**
	 * Retorna el valor de direcciones temporales.
	 *
	 * @return el valor de direcciones temporales
	 */
	public Collection<DireccionDelPredio> getDireccionesTemporales()
	{
		if(!CollectionUtils.isValidCollection(icddp_direccionesTemporales))
			icddp_direccionesTemporales = new LinkedList<DireccionDelPredio>();

		return icddp_direccionesTemporales;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param id_d asigna el valor a la propiedad documento
	 */
	public void setDocumento(Documento id_d)
	{
		id_documento = id_d;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Documento getDocumento()
	{
		if(id_documento == null)
		{
			try
			{
				TurnoHistoria lth_th;

				lth_th = new TurnoHistoria();

				lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

				if(lth_th != null)
				{
					String ls_s;

					ls_s = lth_th.getIdSolicitud();

					if(StringUtils.isValidString(ls_s))
					{
						Documento ld_documento;

						ld_documento = irr_registroRemote.findDocumento(ls_s);

						if(ld_documento != null)
						{
							OficinaOrigen loo_oficinaOrigen;

							loo_oficinaOrigen = new OficinaOrigen();

							loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
							loo_oficinaOrigen.setVersion(ld_documento.getVersion());

							loo_oficinaOrigen = irr_registroRemote.findOficinaOrigen(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								String ls_idTipoOficina;

								ls_idTipoOficina = loo_oficinaOrigen.getIdTipoOficina();

								if(StringUtils.isValidString(ls_idTipoOficina))
								{
									TipoOficina lto_oficina;

									lto_oficina = new TipoOficina();

									lto_oficina.setIdTipoOficina(ls_idTipoOficina);

									lto_oficina = irr_referenceRemote.findTipoOficinaById(lto_oficina);

									if(lto_oficina != null)
										ld_documento.setTipoEntidad(lto_oficina.getIdTipoEntidad());
								}

								ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
								ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
								ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
							}

							id_documento = ld_documento;
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				clh_LOGGER.error("mostrarCopiarAnotaciones", lb2be_e);
			}
			finally
			{
				if(id_documento == null)
				{
					id_documento = new Documento();
					id_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
				}
			}
		}

		return id_documento;
	}

	/**
	 * Modifica el valor de documento criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad documento criterio
	 */
	public void setDocumentoCriterio(boolean ab_b)
	{
		ib_documentoCriterio = ab_b;
	}

	/**
	 * Valida la propiedad documento criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento criterio
	 */
	public boolean isDocumentoCriterio()
	{
		return ib_documentoCriterio;
	}

	/**
	 * Modifica el valor de documento criterios.
	 *
	 * @param aod_od asigna el valor a la propiedad documento criterios
	 */
	public void setDocumentoCriterios(Documento aod_od)
	{
		iod_documentoCriterios = aod_od;
	}

	/**
	 * Retorna el valor de documento criterios.
	 *
	 * @return el valor de documento criterios
	 */
	public Documento getDocumentoCriterios()
	{
		if(iod_documentoCriterios == null)
		{
			iod_documentoCriterios = new Documento();
			iod_documentoCriterios.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return iod_documentoCriterios;
	}

	/**
	 * Modifica el valor de documento detalle cargado.
	 *
	 * @param ad_d asigna el valor a la propiedad documento detalle cargado
	 */
	public void setDocumentoDetalleCargado(Documento ad_d)
	{
		id_documentoDetalleCargado = ad_d;
	}

	/**
	 * Retorna el valor de documento detalle cargado.
	 *
	 * @return el valor de documento detalle cargado
	 */
	public Documento getDocumentoDetalleCargado()
	{
		return id_documentoDetalleCargado;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param ab_b asigna el valor a la propiedad estado
	 */
	public void setEstado(boolean ab_b)
	{
		ib_estado = ab_b;
	}

	/**
	 * Retorna el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public boolean getEstado()
	{
		return ib_estado;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param as_s asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de existe mapa geografico.
	 *
	 * @param ab_b asigna el valor a la propiedad existe mapa geografico
	 */
	public void setExisteMapaGeografico(boolean ab_b)
	{
		ib_existeMapaGeografico = ab_b;
	}

	/**
	 * Valida la propiedad existe mapa geografico.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en existe mapa geografico
	 */
	public boolean isExisteMapaGeografico()
	{
		return ib_existeMapaGeografico;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		if(!StringUtils.isValidString(is_idTurnoHistoria))
		{
			String ls_tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idTurnoHistoria");

			if(StringUtils.isValidString(ls_tmp))
				is_idTurnoHistoria = ls_tmp;
		}

		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de imagen mapa.
	 *
	 * @param as_s asigna el valor a la propiedad imagen mapa
	 */
	public void setImagenMapa(String as_s)
	{
		is_imagenMapa = as_s;
	}

	/**
	 * Retorna el valor de imagen mapa.
	 *
	 * @return el valor de imagen mapa
	 */
	public String getImagenMapa()
	{
		return is_imagenMapa;
	}

	/**
	 * Modifica el valor de info persona.
	 *
	 * @param aop_op asigna el valor a la propiedad info persona
	 */
	public void setInfoPersona(Persona aop_op)
	{
		iop_infoPersona = aop_op;
	}

	/**
	 * Retorna el valor de info persona.
	 *
	 * @return el valor de info persona
	 */
	public Persona getInfoPersona()
	{
		if(iop_infoPersona == null)
			iop_infoPersona = new Persona();

		return iop_infoPersona;
	}

	/**
	 * Modifica el valor de interviniente criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad interviniente criterio
	 */
	public void setIntervinienteCriterio(boolean ab_b)
	{
		ib_intervinienteCriterio = ab_b;
	}

	/**
	 * Valida la propiedad interviniente criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en interviniente criterio
	 */
	public boolean isIntervinienteCriterio()
	{
		return ib_intervinienteCriterio;
	}

	/**justificacionCambio
	 * @param as_s Modifica el valor de la propiedad justificacionCambio por justificacionCambio
	 */
	public void setJustificacionCambio(String as_s)
	{
		is_justificacionCambio = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad justificacionCambio
	 */
	public String getJustificacionCambio()
	{
		return is_justificacionCambio;
	}

	/**
	 * Modifica el valor de libro matriculas ant.
	 *
	 * @param ab_b asigna el valor a la propiedad libro matriculas ant
	 */
	public void setLibroMatriculasAnt(boolean ab_b)
	{
		ib_libroMatriculasAnt = ab_b;
	}

	/**
	 * Valida la propiedad libro matriculas ant.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en libro matriculas ant
	 */
	public boolean isLibroMatriculasAnt()
	{
		return ib_libroMatriculasAnt;
	}

	/**
	 * Modifica el valor de libro segundo ant.
	 *
	 * @param ab_b asigna el valor a la propiedad libro segundo ant
	 */
	public void setLibroSegundoAnt(boolean ab_b)
	{
		ib_libroSegundoAnt = ab_b;
	}

	/**
	 * Valida la propiedad libro segundo ant.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en libro segundo ant
	 */
	public boolean isLibroSegundoAnt()
	{
		return ib_libroSegundoAnt;
	}

	/**
	 * Modifica el valor de matricula link.
	 *
	 * @param al_l asigna el valor a la propiedad matricula link
	 */
	public void setMatriculaLink(Long al_l)
	{
		il_matriculaLink = al_l;
	}

	/**
	 * Retorna el valor de matricula link.
	 *
	 * @return el valor de matricula link
	 */
	public Long getMatriculaLink()
	{
		return il_matriculaLink;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad motivoCambioEstado por motivoCambioEstado
	 */
	public void setMotivoCambioEstado(String as_s)
	{
		is_motivoCambioEstado = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad motivoCambioEstado
	 */
	public String getMotivoCambioEstado()
	{
		return is_motivoCambioEstado;
	}

	/**
	 * Modifica el valor de naturaleza criterio.
	 *
	 * @param as_s asigna el valor a la propiedad naturaleza criterio
	 */
	public void setNaturalezaCriterio(String as_s)
	{
		is_naturalezaCriterio = as_s;
	}

	/**
	 * Retorna el valor de naturaleza criterio.
	 *
	 * @return el valor de naturaleza criterio
	 */
	public String getNaturalezaCriterio()
	{
		return is_naturalezaCriterio;
	}

	/**
	 * Modifica el valor de naturaleza juridica criterio.
	 *
	 * @param ab_b asigna el valor a la propiedad naturaleza juridica criterio
	 */
	public void setNaturalezaJuridicaCriterio(boolean ab_b)
	{
		ib_naturalezaJuridicaCriterio = ab_b;
	}

	/**
	 * Valida la propiedad naturaleza juridica criterio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en naturaleza juridica criterio
	 */
	public boolean isNaturalezaJuridicaCriterio()
	{
		return ib_naturalezaJuridicaCriterio;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;
		Documento                 ld_documento;

		lcidt_datos      = null;
		ld_documento     = getDocumentoCriterios();

		try
		{
			if(ld_documento != null)
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = new OficinaOrigen();

				loo_oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
				loo_oficinaOrigen.setIdPais(ld_documento.getIdPais());
				loo_oficinaOrigen.setIdDepartamento(ld_documento.getIdDepartamento());
				loo_oficinaOrigen.setIdMunicipio(ld_documento.getIdMunicipio());

				lcidt_datos = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Sets the oficina origen apertura.
	 */
	public void setOficinaOrigenApertura()
	{
	}

	/**
	 * Método encargado de consultar la oficina origen para los datos del predio a
	 * aperturar.
	 *
	 * @return el valor de oficina origen apertura
	 */
	public Collection<OficinaOrigen> getOficinaOrigenApertura()
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		try
		{
			Apertura la_apertura;

			la_apertura = getDatosBasicos().getApertura();

			if(la_apertura.getIdPais() != null)
			{
				OficinaOrigen oficina = new OficinaOrigen();
				oficina.setIdTipoOficina(la_apertura.getIdTipoOficina());
				oficina.setIdPais(la_apertura.getIdPais());
				oficina.setIdDepartamento(la_apertura.getIdDepartamento());
				oficina.setIdMunicipio(la_apertura.getIdMunicipio());
				lcidt_datos = irr_referenceRemote.findOficinaOrigen(oficina);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("getOficinaOrigenApertura", lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Modifica el valor de oficinas documento detalle ant sistema.
	 *
	 * @param acoo_coo asigna el valor a la propiedad oficinas documento detalle ant sistema
	 */
	public void setOficinasDocumentoDetalleAntSistema(Collection<OficinaOrigen> acoo_coo)
	{
		icoo_oficinasDocumentoDetalleAntSistema = acoo_coo;
	}

	/**
	 * Retorna el valor de oficinas documento detalle ant sistema.
	 *
	 * @return el valor de oficinas documento detalle ant sistema
	 */
	public Collection<OficinaOrigen> getOficinasDocumentoDetalleAntSistema()
	{
		return icoo_oficinasDocumentoDetalleAntSistema;
	}

	/**
	 * Modifica el valor de paginacion anotacion.
	 *
	 * @param acd_cd asigna el valor a la propiedad paginacion anotacion
	 */
	public void setPaginacionAnotacion(Collection<Dashboard> acd_cd)
	{
		icd_paginacionAnotacion = acd_cd;
	}

	/**
	 * Retorna el valor de paginacion anotacion.
	 *
	 * @return el valor de paginacion anotacion
	 */
	public Collection<Dashboard> getPaginacionAnotacion()
	{
		return icd_paginacionAnotacion;
	}

	/**
	 * Modifica el valor de panels.
	 *
	 * @param aols_ls asigna el valor a la propiedad panels
	 */
	public void setPanels(List<String> aols_ls)
	{
		iols_panels = aols_ls;
	}

	/**
	 * Retorna el valor de panels.
	 *
	 * @return el valor de panels
	 */
	public List<String> getPanels()
	{
		return iols_panels;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param aapr_apr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(PredioRegistro aapr_apr)
	{
		iopr_predioRegistro = aapr_apr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public PredioRegistro getPredioRegistro()
	{
		if(iopr_predioRegistro == null)
			iopr_predioRegistro = new PredioRegistro();

		return iopr_predioRegistro;
	}

	/**
	 * Modifica el valor de predios segregados.
	 *
	 * @param acps_cps asigna el valor a la propiedad predios segregados
	 */
	public void setPrediosSegregados(Collection<PredioSegregado> acps_cps)
	{
		icps_prediosSegregados = acps_cps;
	}

	/**
	 * Retorna el valor de predios segregados.
	 *
	 * @return el valor de predios segregados
	 */
	public Collection<PredioSegregado> getPrediosSegregados()
	{
		return icps_prediosSegregados;
	}

	/**
	 * Modifica el valor de rendered boton volver.
	 *
	 * @param ab_r asigna el valor a la propiedad rendered boton volver
	 */
	public void setRenderedBotonVolver(boolean ab_r)
	{
		ib_renderedBotonVolver = ab_r;
	}

	/**
	 * Valida la propiedad rendered boton volver.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered boton volver
	 */
	public boolean isRenderedBotonVolver()
	{
		return ib_renderedBotonVolver;
	}

	/**
	 * Modifica el valor de retorno bandejas.
	 *
	 * @param ab_b asigna el valor a la propiedad retorno bandejas
	 */
	public void setRetornoBandejas(boolean ab_b)
	{
		ib_retornoBandejas = ab_b;
	}

	/**
	 * Valida la propiedad retorno bandejas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en retorno bandejas
	 */
	public boolean isRetornoBandejas()
	{
		return ib_retornoBandejas;
	}

	/**
	 * Modifica el valor de salvedades.
	 *
	 * @param accs_ccs asigna el valor a la propiedad salvedades
	 */
	public void setSalvedades(Collection<ConsultaSalvedad> accs_ccs)
	{
		iccs_salvedades = accs_ccs;
	}

	/**
	 * Retorna el valor de salvedades.
	 *
	 * @return el valor de salvedades
	 */
	public Collection<ConsultaSalvedad> getSalvedades()
	{
		return iccs_salvedades;
	}

	/**
	 * Asigna el valor a la propiedad is_tipoMatricula
	 *
	 * @param as_s Asigna el valor a la propiedad is_tipoMatricula
	 */
	public void setTipoMatricula(String as_s)
	{
		is_tipoMatricula = as_s;
	}

	/**
	 * Obtiene la propiedad is_tipoMatricula
	 *
	 * @return la propiedad is_tipoMatricula
	 */
	public String getTipoMatricula()
	{
		return is_tipoMatricula;
	}

	/**
	 * Modifica el valor de tipos oficina documento ant sis.
	 *
	 * @param acto_cto asigna el valor a la propiedad tipos oficina documento ant sis
	 */
	public void setTiposOficinaDocumentoAntSis(Collection<TipoOficina> acto_cto)
	{
		icto_tiposOficinaDocumentoAntSis = acto_cto;
	}

	/**
	 * Retorna el valor de tipos oficina documento ant sis.
	 *
	 * @return el valor de tipos oficina documento ant sis
	 */
	public Collection<TipoOficina> getTiposOficinaDocumentoAntSis()
	{
		return icto_tiposOficinaDocumentoAntSis;
	}

	/**
	 * Modifica el valor de total anotaciones.
	 *
	 * @param ai_i asigna el valor a la propiedad total anotaciones
	 */
	public void setTotalAnotaciones(int ai_i)
	{
		ii_totalAnotaciones = ai_i;
	}

	/**
	 * Retorna el valor de total anotaciones.
	 *
	 * @return el valor de total anotaciones
	 */
	public int getTotalAnotaciones()
	{
		return ii_totalAnotaciones;
	}

	/**
	 * Modifica el valor de turno bloqueo.
	 *
	 * @param act_ct asigna el valor a la propiedad turno bloqueo
	 */
	public void setTurnoBloqueo(Collection<Turno> act_ct)
	{
		ict_turnoBloqueo = act_ct;
	}

	/**
	 * Retorna el valor de turno bloqueo.
	 *
	 * @return el valor de turno bloqueo
	 */
	public Collection<Turno> getTurnoBloqueo()
	{
		return ict_turnoBloqueo;
	}

	/**
	 * Modifica el valor de turnos asociados.
	 *
	 * @param act_ct asigna el valor a la propiedad turnos asociados
	 */
	public void setTurnosAsociados(Collection<Turno> act_ct)
	{
		ict_turnosAsociados = act_ct;
	}

	/**
	 * Retorna el valor de turnos asociados.
	 *
	 * @return el valor de turnos asociados
	 */
	public Collection<Turno> getTurnosAsociados()
	{
		return ict_turnosAsociados;
	}

	/**
	 * Modifica el valor de valor seleccionado.
	 *
	 * @param aas_as asigna el valor a la propiedad valor seleccionado
	 */
	public void setValorSeleccionado(String[] aas_as)
	{
		ias_valorSeleccionado = aas_as;
	}

	/**
	 * Retorna el valor de valor seleccionado.
	 *
	 * @return el valor de valor seleccionado
	 */
	public String[] getValorSeleccionado()
	{
		return ias_valorSeleccionado;
	}

	/**
	 * Modifica el valor de VolverDetallePredio.
	 *
	 * @param ab_b de ab b
	 */
	public void setVolverDetallePredio(boolean ab_b)
	{
		ib_volverDetallePredio = ab_b;
	}

	/**
	 * Valida la propiedad volver detalle predio.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en volver detalle predio
	 */
	public boolean isVolverDetallePredio()
	{
		return ib_volverDetallePredio;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		String       ls_return;
		FacesContext lfc_context;

		ls_return       = NavegacionCommon.CONSULTA_PREDIO;
		lfc_context     = FacesUtils.getFacesContext();

		if(isRetornoBandejas())
		{
			BeanCalificacion lbc_bean;

			lbc_bean = (BeanCalificacion)lfc_context.getApplication()
					                                    .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
					);

			try
			{
				if(lbc_bean != null)
				{
					lbc_bean.setEtapaValid(true);
					lbc_bean.setIdEtapa(getIdEtapa().toString());
					ls_return = lbc_bean.returnPages();
				}
			}
			catch(B2BException e)
			{
				clh_LOGGER.error("accionVolver", e);
			}
		}

		{
			BeanDetallePredio lbdp_bean;

			lbdp_bean = (BeanDetallePredio)lfc_context.getApplication()
					                                      .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
					);

			if(lbdp_bean != null)
				ls_return = lbdp_bean.isVieneDeTrazabilidad() ? NavegacionCommon.DETALLE_TRAZABILIDAD : ls_return;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolverCalificacion()
	{
		clean();

		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Cambiar codigo acto.
	 */
	public void cambiarCodigoActo()
	{
		manejoDataActos(false);
	}

	/**
	 * Cambiar especificacion.
	 */
	public void cambiarEspecificacion()
	{
		manejoDataActos(true);
	}

	/**
	 * Cambia las validaciones de la pantalla segun el libro de antiguo sistema
	 * seleccionado.
	 *
	 * @param adas_detalle            Objeto contenedor del id del libro ant sistema seleccionado
	 */
	public void cambiarLibroAntSistema(DetalleAntSistema adas_detalle)
	{
		if(adas_detalle != null)
		{
			String ls_idLibro;

			ls_idLibro = StringUtils.getStringNotNull(StringUtils.getString(adas_detalle.getIdLibroAntSistema()));

			setLibroMatriculasAnt(ls_idLibro.equals(LibroAntSistemaCommon.LIBRO_DE_MATRICULAS));
			setLibroSegundoAnt(ls_idLibro.equals(LibroAntSistemaCommon.LIBRO_SEGUNDO));
		}
	}

	/**
	 * Cargar tabla criterios.
	 */
	public void cargarTablaCriterios()
	{
		String[] las_tmp;

		Map<String, Boolean> lcs_seleccion;
		lcs_seleccion     = new HashMap<String, Boolean>();

		las_tmp = getValorSeleccionado();

		if(CollectionUtils.isValidCollection(las_tmp))
		{
			setInfoPersona(null);
			setDocumentoCriterios(null);

			for(String ls_seleccion : las_tmp)
				lcs_seleccion.put(ls_seleccion, new Boolean(true));

			for(String ls_seleccion : las_tmp)
			{
				if(StringUtils.isValidString(ls_seleccion))
				{
					setConsultaTotalCriterios(BooleanUtils.getBooleanValue(lcs_seleccion.get("CT")));
					setDocumentoCriterio(BooleanUtils.getBooleanValue(lcs_seleccion.get("CD")));
					setIntervinienteCriterio(BooleanUtils.getBooleanValue(lcs_seleccion.get("DI")));
					setAnotacionCriterio(BooleanUtils.getBooleanValue(lcs_seleccion.get("FA")));
					setNaturalezaJuridicaCriterio(BooleanUtils.getBooleanValue(lcs_seleccion.get("NJ")));
				}
			}
		}

		else
		{
			setConsultaTotalCriterios(false);
			setDocumentoCriterio(false);
			setIntervinienteCriterio(false);
			setAnotacionCriterio(false);
			setNaturalezaJuridicaCriterio(false);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String cargarTabsDetalle()
	{
		return cargarTabsDetalle(getIdCirculo(), getIdMatricula());
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_consultaPredio correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String cargarTabsDetalle(boolean ab_consultaPredio)
	{
		return cargarTabsDetalle(getIdCirculo(), getIdMatricula(), false, false, ab_consultaPredio);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_circulo correspondiente al valor del tipo de objeto String
	 * @param al_matricula correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor de String
	 */
	public String cargarTabsDetalle(String as_circulo, Long al_matricula)
	{
		return cargarTabsDetalle(as_circulo, al_matricula, false, false, false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ls_circulo correspondiente al valor del tipo de objeto String
	 * @param ll_matricula correspondiente al valor del tipo de objeto Long
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String cargarTabsDetalle(String ls_circulo, Long ll_matricula, boolean ab_acc)
	{
		return cargarTabsDetalle(ls_circulo, ll_matricula, ab_acc, false, false);
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		clean(false);
	}

	/**
	 * Clean.
	 */
	public void clean(boolean ab_noEliminarCirculoMatricula)
	{
		setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		setCodEspecifiacion(null);
		setAnotacionCriterioStr(null);
		setNaturalezaCriterio(null);
		setMatriculaLink(null);
		setCirculoLink(null);
		setCabidaLinderos(null);
		setDatosBasicos(null);
		setAreaPredio(null);
		setDireccionPredio(null);
		setAlertaNaturalezaJuridica(null);
		setAreaPredio(null);
		setDireccionesPredio(null);
		setAnotacion(null);
		setDetalleAnotacion(null);
		setDireccionesTemporales(null);
		setConsultaPredio(null);
		setDocumentoCriterio(false);
		setIntervinienteCriterio(false);
		setAnotacionCriterio(false);
		setNaturalezaJuridicaCriterio(false);
		setSalvedades(null);
		setPrediosSegregados(null);
		setAreaTerrenoPredio(null);
		setDataModel(null);
		setValorSeleccionado(null);
		setRenderedBotonVolver(true);
//		setImagenMapa(null); //TODO se comenta linea para no borrar mapa obtenido en una consulta previa (21/05/2021 - sesion con oscar)
		setAntiguoSistemaData(null);
		setDescripcionTipoUsoSuelo(null);
		setTipoMatricula(null);

		{
			FacesContext      lfc_context;
			BeanDetallePredio lbdp_beanDetallePredio;

			lfc_context                = FacesUtils.getFacesContext();
			lbdp_beanDetallePredio     = (BeanDetallePredio)lfc_context.getApplication()
					                                                       .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
					);

			if(lbdp_beanDetallePredio != null)
			{
				lbdp_beanDetallePredio.setVieneDeTrazabilidad(false);
				lbdp_beanDetallePredio.setDataModel(null);
			}
		}

		clearConsulta();

		if(!ab_noEliminarCirculoMatricula)
		{
			setIdMatricula(null);
			setIdCirculo(null);
		}

		setTurnoBloqueo(null);
		setTurnosAsociados(null);
		setConsultaPrediosListados(null);
		setActivarDetalle(false);
	}

	/**
	 * Clear consulta.
	 */
	public void clearConsulta()
	{
		setDocumentoCriterios(null);
	}

	/**
	 * Consultar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultar()
	    throws B2BException
	{
		boolean        lb_focus;
		ConsultaPredio lcp_consultaPredio;
		FacesContext   lfc_context;

		lb_focus               = true;
		lcp_consultaPredio     = new ConsultaPredio();
		lfc_context            = FacesContext.getCurrentInstance();

		lcp_consultaPredio.setIdCirculo(getIdCirculo());
		lcp_consultaPredio.setIdMatricula(getIdMatricula());
		setRetornoBandejas(false);

		try
		{
			setConsultaPrediosListados(null);

			String ls_idCirculo;
			Long   ll_idMatricula;

			ls_idCirculo       = lcp_consultaPredio.getIdCirculo();
			ll_idMatricula     = lcp_consultaPredio.getIdMatricula();

			lb_focus     = validateStyles("fConsultaPredio:idSOMcirculoRegistral", lfc_context, ls_idCirculo, lb_focus);

			lb_focus = validateStyles("fConsultaPredio:idITidMatricula", lfc_context, ll_idMatricula, lb_focus);

			if(!StringUtils.isValidString(ls_idCirculo))
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

			if(!NumericUtils.isValidLong(ll_idMatricula))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);

			Collection<ConsultaPredio> lccp_datos;
			ConsultaPredio             locp_datos;
			lccp_datos     = null;

			locp_datos = icepe_consultaPredioRemote.consultar(lcp_consultaPredio);
			setIdCirculo(ls_idCirculo);
			setIdMatricula(ll_idMatricula);

			if(locp_datos != null)
			{
				lccp_datos = locp_datos.getDataConsulta();
				setEstadoPredio(StringUtils.getStringNotNull(locp_datos.getEstadoPredio()));

				if(!CollectionUtils.isValidCollection(lccp_datos))
				{
					ConsultaPredio lcp_consultaTemp;

					lcp_consultaTemp     = new ConsultaPredio();
					lccp_datos           = new ArrayList<ConsultaPredio>();

					lcp_consultaTemp.setIdMatricula(ll_idMatricula);
					lcp_consultaTemp.setIdCirculo(ls_idCirculo);
					lccp_datos.add(lcp_consultaTemp);
				}

				PredioRegistro lpr_tmp;
				lpr_tmp = new PredioRegistro();
				lpr_tmp.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
				lpr_tmp.setIdCirculo(ls_idCirculo);

				Collection<String> lcs_turnosBloqueo;

				lcs_turnosBloqueo = irr_registroRemote.findTurnosBloqueoPredio(lpr_tmp);

				if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
				{
					Collection<Turno> lct_tmp;
					lct_tmp = new ArrayList<Turno>();

					for(String ls_turnoBloqueo : lcs_turnosBloqueo)
					{
						Turno lt_turnoTMP;
						lt_turnoTMP = new Turno();
						lt_turnoTMP.setIdTurno(ls_turnoBloqueo);

						lt_turnoTMP = irr_referenceRemote.findTurnoNombreEtapaById(lt_turnoTMP, getUserId());

						if(lt_turnoTMP != null)
						{
							Date ld_fechaCreacion;

							ld_fechaCreacion = lt_turnoTMP.getFechaCreacion();

							if(ld_fechaCreacion != null)
								lt_turnoTMP.setFechaCreacionString(
								    StringUtils.getString(ld_fechaCreacion, FormatoFechaCommon.DIA_MES_ANIO)
								);

							lct_tmp.add(lt_turnoTMP);
						}
					}

					setTurnosAsociados(lct_tmp);
				}
				else
					setTurnosAsociados(null);

				setConsultaPrediosListados(lccp_datos);
				setActivarDetalle(true);
			}
			else
				setTurnosAsociados(null);

			{
				PredioRegistro     lpr_predioRegistro;
				Collection<String> lcs_turnoBloqueo;
				Collection<Turno>  lct_bloqueante;

				lpr_predioRegistro     = new PredioRegistro();
				lct_bloqueante         = null;

				lpr_predioRegistro.setIdCirculo(ls_idCirculo);
				lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

				lcs_turnoBloqueo = irr_referenceRemote.findActivoByCirculoMatricula(lpr_predioRegistro);

				if(CollectionUtils.isValidCollection(lcs_turnoBloqueo))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					for(String ls_temp : lcs_turnoBloqueo)
					{
						if(StringUtils.isValidString(ls_temp))
							lt_turno.setIdTurno(ls_temp);
					}

					lt_turno = irr_referenceRemote.findTurnoNombreEtapaById(lt_turno, getUserId());

					if(lt_turno != null)
					{
						Date ld_fechaCreacion;

						lct_bloqueante       = new ArrayList<Turno>();
						ld_fechaCreacion     = lt_turno.getFechaCreacion();

						if(ld_fechaCreacion != null)
							lt_turno.setFechaCreacionString(
							    StringUtils.getString(ld_fechaCreacion, FormatoFechaCommon.DIA_MES_ANIO)
							);

						lct_bloqueante.add(lt_turno);
					}
				}

				setTurnoBloqueo(lct_bloqueante);
			}

			addMessage(MessagesKeys.CONSULTA_EXITOSA_3);
		}
		catch(B2BException lb2be_e)
		{
			setTurnosAsociados(null);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String consultarPorCriterios()
	{
		return consultarPorCriterios(
		    ":fConsultaEstadoPredio:TvdetalleRegistroCalif:idAnotaciones",
		    ":fConsultaEstadoPredio:TvdetalleRegistroCalif:Anotaciones_id"
		);
	}

	/**
	 * Departamento.
	 */
	public void departamento()
	{
		Documento lcdd_consultaDatosDocumento;

		lcdd_consultaDatosDocumento = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_idDepartamento;

			ls_idDepartamento = lcdd_consultaDatosDocumento.getIdDepartamento();

			if(StringUtils.isValidString(ls_idDepartamento))
				lcdd_consultaDatosDocumento.setIdDepartamento(ls_idDepartamento);

			lcdd_consultaDatosDocumento.setIdMunicipio(null);
			lcdd_consultaDatosDocumento.setIdOficinaOrigen(null);
		}

		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<NaturalezaJuridica> findAllMaxVersionNaturalezaJuridica(boolean ab_b)
	{
		Collection<NaturalezaJuridica> lcnj_datos;

		try
		{
			lcnj_datos = irr_referenceRemote.findAllMaxVersionNaturalezaJuridica(ab_b);
			setAllActos(lcnj_datos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcnj_datos = null;
		}

		return lcnj_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentoAperturaCorrecciones()
	{
		Collection<Departamento> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				Apertura la_apertura;

				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
				{
					String ls_idPais;

					ls_idPais = la_apertura.getIdPais();

					if(StringUtils.isValidString(ls_idPais))
					{
						Departamento ld_parametros;

						ld_parametros = new Departamento();

						ld_parametros.setIdPais(ls_idPais);

						lcm_municipios = irr_referenceRemote.findDepartaments(ld_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findDepartamentoAperturaCorrecciones", lb2be_e);
			addMessage(lb2be_e);
			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			if(this.is_idPais != null)
			{
				Departamento ld_parametros;

				ld_parametros = new Departamento();

				ld_parametros.setIdPais(this.is_idPais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findDepartamentos", lb2be_e);
			addMessage(lb2be_e);
			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoPredio> findEstadoPredio()
	{
		Collection<EstadoPredio> lcep_estadoPredio;

		try
		{
			lcep_estadoPredio = irr_referenceRemote.findEstadoPredios();

			if(!CollectionUtils.isValidCollection(lcep_estadoPredio))
				lcep_estadoPredio = null;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findEstadoPredio", lb2be_e);
			addMessage(lb2be_e);
			lcep_estadoPredio = null;
		}

		return lcep_estadoPredio;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipio()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			Departamento ld_ubicacion;

			ld_ubicacion = getDatosBasicos().getUbicacion().getDepartamento();

			if(ld_ubicacion != null)
			{
				Municipio lm_parametros;

				lm_parametros = new Municipio();

				lm_parametros.setIdPais(getIdPais());
				lm_parametros.setIdDepartamento(ld_ubicacion.getIdDepartamento());

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findMunicipio", lb2be_e);
			addMessage(lb2be_e);
			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioApertura()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			String ls_ubicacion;

			ls_ubicacion = getDatosBasicos().getApertura().getIdDepartamento();

			if(StringUtils.isValidString(ls_ubicacion))
			{
				Municipio lm_parametros;

				lm_parametros = new Municipio();

				lm_parametros.setIdPais(getIdPais());
				lm_parametros.setIdDepartamento(ls_ubicacion);

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findMunicipioApertura", lb2be_e);
			addMessage(lb2be_e);
			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioAperturaCorrecciones()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				Apertura la_apertura;

				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
				{
					String ls_idDepartamento;

					ls_idDepartamento = la_apertura.getIdDepartamento();

					if(StringUtils.isValidString(ls_idDepartamento))
					{
						Municipio lm_parametros;

						lm_parametros = new Municipio();

						lm_parametros.setIdPais(la_apertura.getIdPais());
						lm_parametros.setIdDepartamento(ls_idDepartamento);

						lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findMunicipioAperturaCorrecciones", lb2be_e);
			addMessage(lb2be_e);
			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;
		Documento             lcdd_consultaDatosDocumento;

		lcm_municipios                  = null;
		lcdd_consultaDatosDocumento     = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_pais;
			String ls_departamento;

			ls_pais             = lcdd_consultaDatosDocumento.getIdPais();
			ls_departamento     = lcdd_consultaDatosDocumento.getIdDepartamento();

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

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Pais> findPaises()
	{
		Collection<Pais> lcp_paises;

		try
		{
			lcp_paises = irr_referenceRemote.findPaises(true, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findPaises", lb2be_e);
			addMessage(lb2be_e);
			lcp_paises = null;
		}

		return lcp_paises;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Vereda> findVereda()
	{
		Collection<Vereda> lcv_veredas;

		lcv_veredas = null;

		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				UbicacionZonaRegistral ld_ubicacion;

				ld_ubicacion = ldb_datosBasicos.getUbicacion();

				if(ld_ubicacion != null)
				{
					Vereda lm_parametros;

					lm_parametros = new Vereda();

					lm_parametros.setIdPais(getIdPais());
					lm_parametros.setIdDepartamento(ld_ubicacion.getDepartamento().getIdDepartamento());
					lm_parametros.setIdMunicipio(ld_ubicacion.getMunicipio().getIdMunicipio());
					lm_parametros.setIdVereda(null);

					if(StringUtils.isValidString(lm_parametros.getIdMunicipio()))
						lcv_veredas = irr_referenceRemote.findVeredas(lm_parametros, false);
					else
						lcv_veredas = null;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findVereda", lb2be_e);
			addMessage(lb2be_e);
			lcv_veredas = null;
		}

		return lcv_veredas;
	}

	/**
	 * Método encargado de generar una <code>Collection</code> cargada con la
	 * respuesta del servicio de búsqueda el OWCC.
	 *
	 * @param ado_infoDoc
	 *            <code>DocumentoOWCC</code> que contiene los parametros que serán
	 *            enviados al servicio.
	 * @return <code>Collection</code> cargada con la respuesta del servicio de
	 *         búsqueda el OWCC
	 * @throws B2BException
	 */
	public Collection<DocumentoOWCC> generarDocumento(DocumentoOWCC ado_infoDoc)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcdo_documentos;

		lcdo_documentos = null;

		try
		{
			if(ado_infoDoc != null)
				lcdo_documentos = irr_consultaSGDRemote.generarDocumento(
					    ado_infoDoc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_b2b)
		{
			clh_LOGGER.error("generarDocumento", lb2be_b2b);
			throw lb2be_b2b;
		}

		return lcdo_documentos;
	}

	/**
	 * Limpiar datos.
	 */
	public void limpiarDatos()
	{
		setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		setCodEspecifiacion(null);
		setAnotacionCriterioStr(null);
		setNaturalezaCriterio(null);
		setMatriculaLink(null);
		setCirculoLink(null);
		setCabidaLinderos(null);
		setDatosBasicos(null);
		setAreaPredio(null);
		setDireccionPredio(null);
		setAlertaNaturalezaJuridica(null);
		setAreaPredio(null);
		setDireccionesPredio(null);
		setAnotacion(null);
		setDetalleAnotacion(null);
		setDireccionesTemporales(null);
		setConsultaPredio(null);
		setDocumentoCriterio(false);
		setIntervinienteCriterio(false);
		setAnotacionCriterio(false);
		setNaturalezaJuridicaCriterio(false);
		setSalvedades(null);
		setPrediosSegregados(null);
		setAreaTerrenoPredio(null);
		setDataModel(null);
		setValorSeleccionado(null);
		setRenderedBotonVolver(true);
		setImagenMapa(null);
		setAntiguoSistemaData(null);
		setDescripcionTipoUsoSuelo(null);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActo> manejoDataActos(boolean ab_b)
	{
		Collection<TipoActo> lcta_actos;
		String               ls_naturalezaCriterio;

		lcta_actos                = null;
		ls_naturalezaCriterio     = ab_b ? getNaturalezaCriterio() : getCodEspecifiacion();

		try
		{
			if(StringUtils.isValidString(ls_naturalezaCriterio))
			{
				if(CollectionUtils.isValidCollection(getAllActos()))
				{
					for(NaturalezaJuridica lo_ta : getAllActos())
					{
						if(lo_ta != null)
						{
							String ls_idTipoActo;

							if(ab_b)
								ls_idTipoActo = lo_ta.getIdNaturalezaJuridica();

							else
								ls_idTipoActo = lo_ta.getLlavePrimaria();

							if(
							    StringUtils.isValidString(ls_idTipoActo)
								    && ls_idTipoActo.equalsIgnoreCase(ls_naturalezaCriterio)
							)
							{
								if(ab_b)
									setCodEspecifiacion(lo_ta.getLlavePrimaria());
								else
									setNaturalezaCriterio(lo_ta.getIdNaturalezaJuridica());

								break;
							}
						}
					}
				}
			}
		}
		catch(Exception lb2be_e)
		{
			lcta_actos = null;
		}

		return lcta_actos;
	}

	/**
	 * Abre el dialogo de detalles de antiguo sistema.
	 *
	 * @param adas_detalle            Objeto contenedor de los detalles a mostrar
	 */
	public void mostrarDialogDetallePredio(DetalleAntSistema adas_detalle)
	{
		try
		{
			if(adas_detalle == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			setDetalleAntSistemaCargado(
			    irr_calificacionRemote.findDetalleAntSistemaById(
			        adas_detalle, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			setDatoAntSistemaCargado(
			    irr_calificacionRemote.findDatosAntSistemaById(
			        adas_detalle.getIdDatosAntSistema(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			Documento ld_documento;

			ld_documento = iasr_antiguoSistemaRemote.obtenerDocumentoAntSistema(
				    adas_detalle, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ld_documento != null)
			{
				OficinaOrigen loo_oficina;
				loo_oficina = new OficinaOrigen();

				loo_oficina.setIdTipoOficina(ld_documento.getIdTipoOficina());
				loo_oficina.setIdPais(ld_documento.getIdPais());
				loo_oficina.setIdDepartamento(ld_documento.getIdDepartamento());
				loo_oficina.setIdMunicipio(ld_documento.getIdMunicipio());

				setOficinasDocumentoDetalleAntSistema(irr_referenceRemote.findOficinaOrigen(loo_oficina));

				setTiposOficinaDocumentoAntSis(irr_referenceRemote.findTipoOficina());
			}
			else
				ld_documento = new Documento();

			setDocumentoDetalleCargado(ld_documento);

			cambiarLibroAntSistema(adas_detalle);

			PrimeFaces.current().executeScript("PF('detallePredio').show();");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Obtener mapa geografico.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void obtenerMapaGeografico()
	    throws B2BException
	{
		PredioRegistro lpr_predioRegistro;
		String         ls_urlMapa;

		lpr_predioRegistro     = getPredioRegistro();
		ls_urlMapa             = null;

		if(lpr_predioRegistro != null)
		{
			String ls_numeroPredial;

			ls_numeroPredial = lpr_predioRegistro.getNumeroPredial();

			if(StringUtils.isValidString(ls_numeroPredial))
				ls_urlMapa = icepe_consultaPredioRemote.obtenerUrlMapaGeografico(
					    ls_numeroPredial, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}

		if(StringUtils.isValidString(ls_urlMapa))
		{
			setImagenMapa(ls_urlMapa);
			setExisteMapaGeografico(true);
		}
		else
		{
			setImagenMapa(null);
			setExisteMapaGeografico(false);
		}
	}

	/**
	 * Oficina origen.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
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
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Pais.
	 */
	public void pais()
	{
		Documento lcdd_consultaDatosDocumento;

		lcdd_consultaDatosDocumento = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_pais;

			ls_pais = lcdd_consultaDatosDocumento.getIdPais();

			if(StringUtils.isValidString(ls_pais))
				lcdd_consultaDatosDocumento.setIdPais(ls_pais);

			lcdd_consultaDatosDocumento.setIdDepartamento(null);
			lcdd_consultaDatosDocumento.setIdMunicipio(null);
		}

		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Tipo oficina.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void tipoOficina()
	    throws B2BException
	{
		Documento lcdd_consultaDatosDocumento;

		lcdd_consultaDatosDocumento = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_tipoOficina;

			ls_tipoOficina = lcdd_consultaDatosDocumento.getIdTipoOficina();

			if(StringUtils.isValidString(ls_tipoOficina))
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(ls_tipoOficina);

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					lcdd_consultaDatosDocumento.setTipoEntidad(lte_te.getIdTipoEntidad());
				}

				lcdd_consultaDatosDocumento.setIdTipoOficina(ls_tipoOficina);
				lcdd_consultaDatosDocumento.setIdDepartamento(null);
				lcdd_consultaDatosDocumento.setIdMunicipio(null);
			}
		}

		findPaises();
		findDepartamentos();
		findMunicipios();
		getOficinaOrigen();
	}

	/**
	 * Tipo oficina apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void tipoOficinaApertura()
	    throws B2BException
	{
		Documento lcdd_consultaDatosDocumento;

		lcdd_consultaDatosDocumento = getDocumentoCriterios();

		if(lcdd_consultaDatosDocumento != null)
		{
			String ls_tipoOficina;

			ls_tipoOficina = lcdd_consultaDatosDocumento.getIdTipoOficina();

			if(StringUtils.isValidString(ls_tipoOficina))
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(ls_tipoOficina);

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					lcdd_consultaDatosDocumento.setTipoEntidad(lte_te.getIdTipoEntidad());
				}

				lcdd_consultaDatosDocumento.setIdTipoOficina(ls_tipoOficina);
				lcdd_consultaDatosDocumento.setIdDepartamento(null);
				lcdd_consultaDatosDocumento.setIdMunicipio(null);
			}
		}
	}

	/**
	 * Validar existencia documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaDocumento()
	    throws B2BException
	{
		validarExistenciaDocumento(true);
	}

	/**
	 * Validar existencia documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarExistenciaDocumento(boolean ab_apertura)
	    throws B2BException
	{
		try
		{
			Documento ld_documento;
			ld_documento = null;

			if(ab_apertura)
			{
				Apertura la_a;

				la_a = getDatosBasicos().getApertura();

				if(la_a != null)
				{
					ld_documento = la_a.getDocumento();
					ld_documento.setIdTipoOficina(la_a.getIdTipoOficina());
					ld_documento.setIdOficinaOrigen(la_a.getIdOficinaOrigen());
				}
			}
			else
				ld_documento = getDocumento();

			ValidacionDocumento lb_retorno;

			lb_retorno = irr_registroRemote.validarExistenciaDocumento(ld_documento);

			if(lb_retorno != null)
			{
				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}

				if(lb_retorno.isValidacion())
				{
					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0] = lb_retorno.getNir();

					throw new B2BException(ErrorKeys.ERROR_DATOS_DOCUMENTO_NIR, aoa_messageArgs);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarExistenciaDocumento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	protected void accionSGD(Collection<DocumentoOWCC> ado_documento, String as_paginaRetorno)
	    throws IOException, B2BException
	{
		try
		{
			if((ado_documento != null))
			{
				FacesContext    lfc_context;
				BeanConsultaSGD lb_beanConsultaSGD;

				lfc_context            = FacesUtils.getFacesContext();
				lb_beanConsultaSGD     = (BeanConsultaSGD)lfc_context.getApplication()
						                                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_SGD, BeanConsultaSGD.class
						);

				if((lb_beanConsultaSGD != null))
				{
					lb_beanConsultaSGD.setResultadosConsulta(ado_documento);
					lb_beanConsultaSGD.setPantallaAnterior(as_paginaRetorno);
					lb_beanConsultaSGD.setNewTab(false);
					FacesContext.getCurrentInstance().getExternalContext().redirect("consultaSGD.jsf");
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionSGD", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ls_circulo correspondiente al valor del tipo de objeto String
	 * @param ll_matricula correspondiente al valor del tipo de objeto Long
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 * @param ab_correcciones correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	protected String cargarTabsDetalle(String ls_circulo, Long ll_matricula, boolean ab_acc, boolean ab_correcciones)
	{
		return cargarTabsDetalle(ls_circulo, ll_matricula, ab_acc, ab_correcciones, false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ls_circulo correspondiente al valor del tipo de objeto String
	 * @param ll_matricula correspondiente al valor del tipo de objeto Long
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 * @param ab_correcciones correspondiente al valor del tipo de objeto boolean
	 * @param ab_consultaPredio correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	protected String cargarTabsDetalle(
	    String ls_circulo, Long ll_matricula, boolean ab_acc, boolean ab_correcciones, boolean ab_consultaPredio
	)
	{
		String ls_return;

		ls_return = NavegacionCommon.CONSULTA_PREDIO_DETALLE;

		try
		{
			ConsultaPredio lcp_cp;
			PredioRegistro lopr_apr;
			long           ll_etapa;
			boolean        lb_noEsCorreciones;
			boolean        lb_vieneDeTrazabilidad;

			lopr_apr                   = new PredioRegistro();
			ll_etapa                   = NumericUtils.getLong(getIdEtapa());
			lb_noEsCorreciones         = (ll_etapa != EtapaCommon.ID_ETAPA_CORRECCIONES)
					&& (ll_etapa != EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES);
			lb_vieneDeTrazabilidad     = false;

			lopr_apr.setIdCirculo(ls_circulo);
			lopr_apr.setIdMatricula(NumericUtils.getLong(ll_matricula));
			lopr_apr.setConsultaPredio(true);

			{
				String ls_tipoMatricula;

				ls_tipoMatricula = getTipoMatricula();

				if(StringUtils.isValidString(ls_tipoMatricula))
					lopr_apr.setIdTipoPredio(ls_tipoMatricula);
			}

			/* Capturar predio registro matricula y circulo */
			if(!ab_acc)
				lcp_cp = icepe_consultaPredioRemote.findInfoTabsBng(
					    lopr_apr, getUserId(), false, lb_noEsCorreciones, ab_consultaPredio
					);
			else
			{
				lopr_apr.setIdTipoPredio(IdentificadoresCommon.TEMPORAL);
				lopr_apr.setEtapa(ll_etapa);

				if(ab_correcciones)
					lopr_apr.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				lcp_cp = icepe_consultaPredioRemote.findInfoTabs(lopr_apr, getUserId(), ab_correcciones);
			}

			{
				FacesContext      lfc_context;
				BeanDetallePredio lbdp_beanDetallePredio;

				lfc_context                = FacesUtils.getFacesContext();
				lbdp_beanDetallePredio     = (BeanDetallePredio)lfc_context.getApplication()
						                                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
						);

				if(lbdp_beanDetallePredio != null)
					lb_vieneDeTrazabilidad = lbdp_beanDetallePredio.isVieneDeTrazabilidad();
			}

			limpiarDatos();

			if(lcp_cp != null)
			{
				setCirculoLink(ls_circulo);
				setMatriculaLink(ll_matricula);
				setTotalAnotaciones(lcp_cp.getTotalAnotaciones());
				setPredioRegistro(lcp_cp.getPredioRegistro());

				{
					DatosBasicos ldb_datosBasicos;

					ldb_datosBasicos = lcp_cp.getDatosbasicos();

					if(ab_acc && (ldb_datosBasicos != null))
					{
						AccPredioRegistro lapr_predioRegistro;
						PredioRegistro    lpr_predioRegistro;

						lpr_predioRegistro      = ldb_datosBasicos.getPredioRegistro();
						lapr_predioRegistro     = ldb_datosBasicos.getAccPredioRegistro();

						if((lpr_predioRegistro != null) && (lapr_predioRegistro != null))
						{
							String ls_idTipoUsoSuelo;
							String ls_idTipoPredio;

							ls_idTipoUsoSuelo     = lpr_predioRegistro.getIdTipoUsoSuelo();
							ls_idTipoPredio       = lpr_predioRegistro.getIdTipoPredio();

							if(!StringUtils.isValidString(ls_idTipoUsoSuelo))
								ls_idTipoUsoSuelo = lapr_predioRegistro.getIdTipoUsoSuelo();

							if(!StringUtils.isValidString(ls_idTipoPredio))
								ls_idTipoPredio = lapr_predioRegistro.getIdTipoPredio();

							lpr_predioRegistro.setIdTipoUsoSuelo(ls_idTipoUsoSuelo);
							lpr_predioRegistro.setIdTipoPredio(ls_idTipoPredio);
						}

						ldb_datosBasicos.setPredioRegistro(lpr_predioRegistro);
					}

					setDatosBasicos(ldb_datosBasicos);
				}

				setAntiguoSistemaData(lcp_cp.getAntiguoSistemaData());
				setDetallesAntSistema(lcp_cp.getDetallesAntSistema());
				setCierreFolio(lcp_cp.isCierreFolio());

				if(lcp_cp.isCierreFolio())
				{
					CambioEstadoPredio lcep_cambioEstadoPredio;

					lcep_cambioEstadoPredio = lcp_cp.getCambioEstadoPredio();

					if(lcep_cambioEstadoPredio != null)
					{
						setJustificacionCambio(lcep_cambioEstadoPredio.getJustificacionCambio());
						setMotivoCambioEstado(lcep_cambioEstadoPredio.getMotivoCambioEstado());
					}
				}

				if(lb_noEsCorreciones)
					obtenerMapaGeografico();

				if((!ab_acc))
				{
					ConsultaPredio locp_datos;

					lcp_cp.setIdCirculo(ls_circulo);
					lcp_cp.setIdMatricula(ll_matricula);
					locp_datos = icepe_consultaPredioRemote.consultar(lcp_cp);

					if(locp_datos != null)
					{
						setEstadoPredio(StringUtils.getStringNotNull(locp_datos.getEstadoPredio()));
						setIdCirculo(getCirculoLink());
						setIdMatricula(getMatriculaLink());
					}
				}

				{
					LinderoPredio         lalp_lp;
					ComplementacionPredio lacp_cp;
					CabidaLinderos        lcl_cl;

					lcl_cl      = getCabidaLinderos();
					lacp_cp     = lcp_cp.getComplementacionPredio();
					lalp_lp     = lcp_cp.getLinderoPredio();

					if(lacp_cp != null)
					{
						Long ll_complementacionOriginal;

						ll_complementacionOriginal = NumericUtils.getLongWrapper(lacp_cp.getIdComplementacion());

						if(NumericUtils.isValidLong(ll_complementacionOriginal))
						{
							if(ab_correcciones)
								lcl_cl.setTipoComplementacion(lacp_cp.getTipoComplementacion());
							else
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.COPIAR);

							lcl_cl.getComplementacion().setComplementacion(lacp_cp.getComplementacion());
							lcl_cl.getComplementacionPredio()
								      .setIdComplementacion(
								    String.valueOf(NumericUtils.getLong(lacp_cp.getIdComplementacion()))
								);
						}
						else
							lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
					}

					if(lalp_lp != null)
						lcl_cl.setLinderos(lalp_lp.getLindero());
				}

				setAreaUI(lcp_cp.getAreaUI());
				setAreaPredio(lcp_cp.getAreaPredio());
				setDireccionesPredio(lcp_cp.getDireccionesDelPredio());
				setAnotacion(lcp_cp.getAnotacion());
				setDireccionesTemporales(getDireccionesPredio());
				setPrediosSegregados(lcp_cp.getPrediosSegregados());
				setAlertaNaturalezaJuridica(lcp_cp.getInfoAlertas());
				setAreaTerrenoPredio(lcp_cp.getDatosAreaPredio());
				getDocumentoCriterios().setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

				if(lb_noEsCorreciones && !lb_vieneDeTrazabilidad)
					consultarAnotaciones(lcp_cp, 0);
				else
				{
					ConsultaPredio lcp_temp;

					lcp_temp     = new ConsultaPredio();

					lcp_temp = icepe_consultaPredioRemote.findInfoTabs(lopr_apr, getUserId(), ab_correcciones);

					consultarAnotaciones(lcp_temp, 1);
				}

				setSalvedades(lcp_cp.getSalvedades());
				setRenderedBotonVolver(false);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);
		}
		catch(B2BException lb2be_e)
		{
			ls_return = null;
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Consultar anotaciones.
	 *
	 * @param acp_cp correspondiente al valor del tipo de objeto ConsultaPredio
	 * @param ai_tipoEjecucion correspondiente al valor del tipo de objeto int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void consultarAnotaciones(ConsultaPredio acp_cp, int ai_tipoEjecucion)
	    throws B2BException
	{
		consultarAnotaciones(acp_cp, ai_tipoEjecucion, is_messageIdGrowl);
	}

	/**
	 * Consultar anotaciones.
	 *
	 * @param acp_cp correspondiente al valor del tipo de objeto ConsultaPredio
	 * @param ai_tipoEjecucion correspondiente al valor del tipo de objeto int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void consultarAnotaciones(ConsultaPredio acp_cp, int ai_tipoEjecucion, String as_form)
	    throws B2BException
	{
		if(acp_cp != null)
		{
			Application                      la_a;
			boolean                          lb_cancelacion;
			Collection<RegistroCalificacion> locri_tmp;
			Collection<Dashboard>            lcdb_dashboard;
			RegistroCalificacion             lrc_detalleAnotacion;
			DashboardColumn                  ldbc_dbc;
			DashboardModel                   ldbm_model;
			Date                             ls_fechaDoc;
			FacesContext                     lfc_fc;
			Collection<RegistroCalificacion> lcrc_dataIntervinientes;

			int li_anotacion;
			int li_column;
			int li_columnCount;

			String ls_codDoc;
			String ls_idTurno;
			String ls_lineaSeparadora;
			String ls_municipioDoc;
			String ls_notaria;
			String ls_fechaDocum;

			lfc_fc                   = FacesContext.getCurrentInstance();
			la_a                     = lfc_fc.getApplication();
			lb_cancelacion           = false;
			ldbc_dbc                 = new DefaultDashboardColumn();
			li_anotacion             = 0;
			li_column                = 1;
			li_columnCount           = 1;
			lrc_detalleAnotacion     = acp_cp.getAnotaciones();
			ls_lineaSeparadora       = "<br/>";
			ls_idTurno               = acp_cp.getIdTurno();
			ls_fechaDocum            = null;
			lcdb_dashboard           = new ArrayList<Dashboard>();

			if(!StringUtils.isValidString(ls_idTurno))
				ls_idTurno = "";

			if(lrc_detalleAnotacion != null)
			{
				locri_tmp = lrc_detalleAnotacion.getAllMatriculas();

				if(CollectionUtils.isValidCollection(locri_tmp))
				{
					List<UIComponent> lluic_hijos;
					int               li_id;
					Dashboard         ld_dataModel;
					DateFormat        lsf_dateFormat;

					ld_dataModel = (Dashboard)la_a.createComponent(
						    lfc_fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer"
						);

					ld_dataModel.setId("dashboard");

					lsf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
					ldbm_model         = new DefaultDashboardModel();
					lluic_hijos        = ld_dataModel.getChildren();
					li_id              = 1;

					ldbm_model.addColumn(ldbc_dbc);

					ld_dataModel.setModel(ldbm_model);
					ld_dataModel.setDisabled(true);

					for(RegistroCalificacion lorc_rc : locri_tmp)
					{
						Date    ld_fechaRegistro;
						Long    ll_orden;
						String  ls_orden;
						String  ls_fecha;
						Long    ll_versionDocumento;
						boolean lb_anotacionInv;

						ld_fechaRegistro        = lorc_rc.getFechaRegistro();
						ll_orden                = lorc_rc.getOrden();
						ll_versionDocumento     = null;
						lb_anotacionInv         = lorc_rc.isAnotacionInvalida();

						if(NumericUtils.isValidLong(ll_orden))
							ls_orden = StringUtils.getString(ll_orden);
						else
							ls_orden = IdentificadoresCommon.DATO_INVALIDO;

						if(ld_fechaRegistro != null)
							ls_fecha = lsf_dateFormat.format(ld_fechaRegistro);
						else
							ls_fecha = IdentificadoresCommon.DATO_INVALIDO;

						lb_cancelacion = StringUtils.isValidString(lorc_rc.getAnotacionCancelacion());

						/* Creacion panel para agregar al dashboard */
						Panel lp_panel = (Panel)la_a.createComponent(
							    lfc_fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer"
							);
						ldbc_dbc = ldbm_model.getColumn(li_column % li_columnCount);
						lp_panel.setId("ID_" + lorc_rc.getIdAnotacion() + "-" + li_anotacion + "-" + li_id++);
						li_anotacion = li_anotacion + 1;
						ldbc_dbc.addWidget(lp_panel.getId());

						lp_panel.setHeader(
						    "ANOTACION - Orden " + ls_orden + " - Nro." + lorc_rc.getIdAnotacion() + "  " + " Fecha: "
						    + ls_fecha + "  " + "Radicación: 	" + lorc_rc.getRadicacion()
						);
						lp_panel.setStyle("font-weight: bold; width:100%;");
						lp_panel.setToggleable(true);

						if(lorc_rc.isAnotacionAntiguoSistema() && (ai_tipoEjecucion == 1))
							lp_panel.setStyleClass("gui-widget-header");

						{
							HtmlOutputText loht_space;

							loht_space = new HtmlOutputText();
							loht_space.setEscape(false);
							loht_space.setValue(ls_lineaSeparadora);
						}

						{
							RegistroCalificacion lorc_dataDoc;

							lorc_dataDoc            = lorc_rc.getDatosDocumento();
							ls_fechaDoc             = lorc_dataDoc.getFechaDocumento();
							ls_codDoc               = lorc_dataDoc.getCodDocumento();
							ls_notaria              = lorc_dataDoc.getNombreOficina();
							ls_municipioDoc         = lorc_dataDoc.getNombreMunicipio();
							ll_versionDocumento     = lorc_dataDoc.getVersion();

							if(ls_fechaDoc != null)
								ls_fechaDocum = StringUtils.getString(ls_fechaDoc, FormatoFechaCommon.DIA_MES_ANIO);
						}

						if(!StringUtils.isValidString(ls_notaria))
						{
							String ls_idDocumento;

							ls_idDocumento = lorc_rc.getIdDocumento();

							if(
							    StringUtils.isValidString(ls_idDocumento)
								    && NumericUtils.isValidLong(ll_versionDocumento)
							)
							{
								Documento ld_documento;

								ld_documento = ipr_parameterRemote.findByIdDocumentoVersion(
									    ls_idDocumento, ll_versionDocumento, getUserId(), getLocalIpAddress(),
									    getRemoteIpAddress()
									);

								if(ld_documento != null)
									ls_notaria = StringUtils.getStringNotNull(ld_documento.getComentario());
							}
							else
								ls_notaria = IdentificadoresCommon.DATO_INVALIDO;
						}

						else if(!StringUtils.isValidString(ls_municipioDoc))
							ls_municipioDoc = "";

						/* Creacion descripcion del campo */
						lp_panel.setStyle("font-weight: normal; width:100%;");

						{
							String ls_valorActo;

							ls_valorActo = null;

							if((lorc_rc.isAnotacionAntiguoSistema() && (ai_tipoEjecucion == 1)) || lb_anotacionInv)
							{
								HtmlOutputText lhot_ot;

								lhot_ot = new HtmlOutputText();
								lhot_ot.setEscape(false);
								lhot_ot.setStyle("color: #BA1918 !important;padding-left: 64em;display: inline-block;");
								lhot_ot.setValue(lb_anotacionInv ? "Anotación inválida" : "Anotación temporal");

								lp_panel.getChildren().add(lhot_ot);
							}

							{
								NumberFormat lnf_numbreFormat;
								lnf_numbreFormat = NumberFormat.getNumberInstance(Locale.getDefault());

								String ls_valor;
								ls_valor     = lnf_numbreFormat.format(lorc_rc.getValor());

								ls_valorActo = " VALOR ACTO $ : " + ls_valor;
							}

							{
								Div ld_divGrande;

								ld_divGrande = new Div();
								ld_divGrande.setStyleClass("ui-g");

								{
									Div            ld_mitadIzquierda;
									CommandLink    lcl_documento;
									HtmlOutputText lhot_valorActo;

									ld_mitadIzquierda     = new Div();
									lcl_documento         = new CommandLink();
									lhot_valorActo        = new HtmlOutputText();

									lcl_documento.setStyle(
									    "padding-right:2em;display: inline-block;text-decoration: underline;"
									);
									lcl_documento.setValue(
									    "Doc:" + lorc_rc.getNombreDoc() + " " + ls_codDoc + " DEL " + " "
									    + ls_fechaDocum + "  " + ls_notaria + " " + ls_municipioDoc
									);
									lhot_valorActo.setStyle("padding-right:2em;display: inline-block;");
									lhot_valorActo.setValue(ls_valorActo);

									((UICommand)lcl_documento).addActionListener(
									    new ActionListener()
										{
											@Override
											public void processAction(ActionEvent aae_ae)
											{
												CommandLink lhcb_cb;
												String      ls_idPanel;

												lhcb_cb        = (CommandLink)aae_ae.getSource();
												ls_idPanel = null;

												try
												{
													UIComponent luc_temp;

													luc_temp = null;

													if(lhcb_cb != null)
														luc_temp = lhcb_cb.getParent().getParent().getParent();

													if(luc_temp != null)
														ls_idPanel = luc_temp.getId();

													ls_idPanel = generateId(ls_idPanel);

													if(StringUtils.isValidString(ls_idPanel))
													{
														DocumentoOWCC ldo_documentoAConsultar;

														ldo_documentoAConsultar = new DocumentoOWCC();

														ldo_documentoAConsultar.setAnotacion(ls_idPanel);
														ldo_documentoAConsultar.setMatriculas(
														    StringUtils.getString(getIdMatricula())
														);
														ldo_documentoAConsultar.setIdOrip(getIdCirculo());

														accionSGD(
														    generarDocumento(ldo_documentoAConsultar),
														    isVolverDetallePredio() ? NavegacionCommon.DETALLE_PREDIO
														                            : NavegacionCommon.CONSULTA_PREDIO_DETALLE
														);
													}
												}
												catch(IOException lio_exception)
												{
													clh_LOGGER.error("processAction", lio_exception);
													addMessage(
													    new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS)
													);
												}
												catch(B2BException lb2be_e)
												{
													clh_LOGGER.error("processAction", lb2be_e);
													addMessage(
													    new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS)
													);
												}
											}
										}
									);
									lcl_documento.setUpdate(as_form);

									ld_mitadIzquierda.getChildren().add(lcl_documento);
									ld_mitadIzquierda.getChildren().add(lhot_valorActo);
									ld_mitadIzquierda.setStyle("text-aling:left");
									ld_mitadIzquierda.setStyleClass("ui-g-10 ui-md-10 ui-lg-10");
									ld_divGrande.getChildren().add(ld_mitadIzquierda);
								}

								{
									Div ld_mitadDerecha;

									ld_mitadDerecha = new Div();

									{
										CommandButton lcb_consultaSGD;

										lcb_consultaSGD = new CommandButton();

										lcb_consultaSGD.setValue(getMessages().getString(MessagesKeys.CONSULTA_SGD));
										lcb_consultaSGD.setIcon("lupa");
										lcb_consultaSGD.setIconPos("center");
										((UICommand)lcb_consultaSGD).addActionListener(
										    new ActionListener()
											{
												@Override
												public void processAction(ActionEvent aae_ae)
												{
													String        ls_idPanel;
													CommandButton lhcb_cb;

													lhcb_cb        = (CommandButton)aae_ae.getSource();
													ls_idPanel = null;

													try
													{
														UIComponent luc_temp;

														luc_temp = null;

														if(lhcb_cb != null)
															luc_temp = lhcb_cb.getParent().getParent().getParent();

														if(luc_temp != null)
															ls_idPanel = luc_temp.getId();

														ls_idPanel = generateId(ls_idPanel);

														if(StringUtils.isValidString(ls_idPanel))
														{
															DocumentoOWCC ldo_documentoAConsultar;

															ldo_documentoAConsultar = new DocumentoOWCC();

															ldo_documentoAConsultar.setAnotacion(ls_idPanel);
															ldo_documentoAConsultar.setMatriculas(
															    StringUtils.getString(getIdMatricula())
															);
															ldo_documentoAConsultar.setIdOrip(getIdCirculo());
															accionSGD(
															    ldo_documentoAConsultar,
															    isVolverDetallePredio()
															    ? NavegacionCommon.DETALLE_PREDIO
															    : NavegacionCommon.CONSULTA_PREDIO_DETALLE
															);
														}
													}
													catch(IOException lio_exception)
													{
														clh_LOGGER.error("consultarAnotaciones", lio_exception);
														addMessage(
														    new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS)
														);
													}
													catch(B2BException lb2be_e)
													{
														clh_LOGGER.error("consultarAnotaciones", lb2be_e);
														addMessage(
														    new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS)
														);
													}
												}
											}
										);
										lcb_consultaSGD.setAjax(true);
										lcb_consultaSGD.setUpdate(as_form);

										ld_mitadDerecha.setStyleClass("ui-g-2 ui-md-2 ui-lg-2");

										if(!as_form.equalsIgnoreCase(":idFormAntSistema:globalMsg"))
										{
											ld_mitadDerecha.getChildren().add(lcb_consultaSGD);
											ld_divGrande.getChildren().add(ld_mitadDerecha);
										}
									}
								}

								lp_panel.getChildren().add(ld_divGrande);
							}
						}

						/* Creacion descripcion antiguo sistema */
						String         ls_valueAntSistema;
						HtmlOutputText lhot_antSistema;

						lhot_antSistema = new HtmlOutputText();
						lhot_antSistema.setEscape(false);
						ls_valueAntSistema = ls_lineaSeparadora + " ";

						/* Creacion descripcion antiguo sistema*/
						if(lorc_rc.isDatosValidosAntSistema())
						{
							DatosAntSistema lodas_das;

							lodas_das = lorc_rc.getAntiguoSistemaData();

							if(lodas_das != null)
							{
								String ls_idDatosAntSistema;

								ls_idDatosAntSistema = lodas_das.getIdDatosAntSistema();

								if(StringUtils.isValidString(ls_idDatosAntSistema))
								{
									Collection<DetalleAntSistema> lcdat_clldetalles;

									lcdat_clldetalles = acp_cp.getDetallesAntSistema();

									if(CollectionUtils.isValidCollection(lcdat_clldetalles))
									{
										String ls_idDetalleAntSistema;

										ls_idDetalleAntSistema = null;

										for(DetalleAntSistema ldas_temp : lcdat_clldetalles)
										{
											if(ldas_temp != null)
												ls_idDetalleAntSistema = ldas_temp.getIdDetalleAntSistema();
										}

										if(StringUtils.isValidString(ls_idDetalleAntSistema))
										{
											DetalleAntSistema ldas_detalleAntSistema;

											ldas_detalleAntSistema = ipr_parameterRemote
													.findDetalleAntSistemaByDetalleYDatosAntSis(
													    ls_idDetalleAntSistema, ls_idDatosAntSistema
													);

											if(ldas_detalleAntSistema != null)
											{
												String ls_libro;
												String ls_folio;
												String ls_tomo;
												String ls_partida;
												String ls_numeroPartida;
												String ls_anio;
												String ls_nombrePredio;

												{
													String ll_tmp;

													ll_tmp = StringUtils.getString(
														    ldas_detalleAntSistema.getIdLibroAntSistema()
														);

													if(StringUtils.isValidString(ll_tmp))
														ls_libro = ll_tmp;
													else
														ls_libro = " ";

													ll_tmp = ldas_detalleAntSistema.getFolio();

													if(StringUtils.isValidString(ll_tmp))
														ls_folio = ll_tmp;
													else
														ls_folio = " ";

													ll_tmp = ldas_detalleAntSistema.getTomo();

													if(StringUtils.isValidString(ll_tmp))
														ls_tomo = ll_tmp;
													else
														ls_tomo = " ";

													ll_tmp = ldas_detalleAntSistema.getAnio();

													if(StringUtils.isValidString(ll_tmp))
														ls_anio = ll_tmp;
													else
														ls_anio = " ";

													ll_tmp = StringUtils.getString(
														    ldas_detalleAntSistema.getNumeroPartida()
														);

													if(StringUtils.isValidString(ll_tmp))
														ls_numeroPartida = ll_tmp;
													else
														ls_numeroPartida = " ";
												}

												ls_partida          = StringUtils.getStringNotNull(
													    ldas_detalleAntSistema.getPartida()
													);
												ls_nombrePredio     = StringUtils.getStringNotNull(
													    lodas_das.getNombrePredio()
													);

												ls_valueAntSistema = ls_lineaSeparadora + "DATOS ANTIGUO SISTEMA: "
													+ "LIBRO: " + ls_libro + " - " + "TOMO: " + ls_tomo + " - "
													+ " FOLIO: " + ls_folio + " - " + "TIPO DE PARTIDA: " + ls_partida
													+ " - " + "NÚMERO DE PARTIDA: " + ls_numeroPartida + " - "
													+ "AÑO: " + ls_anio + " - " + " NOMBRE DEL PREDIO: "
													+ ls_nombrePredio;
											}
										}
									}
								}
							}
						}

						lhot_antSistema.setValue(ls_valueAntSistema);

						/* Creacion especificacion del acto */
						HtmlOutputText lhot_esp = new HtmlOutputText();
						lhot_esp.setId("ot_esp" + li_column + "-" + li_id++);
						lhot_esp.setEscape(false);
						lhot_esp.setStyle("padding-right: 5em;");
						lhot_esp.setValue(
						    ls_lineaSeparadora + "ESPECIFICACION:" + lorc_rc.getNombreGrupoActo() + " "
						    + lorc_rc.getCodActo() + " " + lorc_rc.getNombreActo() + " - "
						    + StringUtils.getStringNotNull(lorc_rc.getComentario())
						);

						HtmlOutputText lhot_cancelacion = new HtmlOutputText();

						if(lb_cancelacion)
						{
							lhot_cancelacion.setEscape(false);

							String ls_anotacionCancelacion;

							ls_anotacionCancelacion = lorc_rc.getAnotacionCancelacion();

							lhot_cancelacion.setValue(
							    ls_lineaSeparadora + "ANOTACION CANCELADA : "
							    + (StringUtils.isValidString(ls_anotacionCancelacion) ? ls_anotacionCancelacion : "")
							    + ls_lineaSeparadora
							);
						}

						HtmlOutputText lhot_d = new HtmlOutputText();
						lhot_d.setEscape(false);
						lhot_d.setValue(
						    ls_lineaSeparadora
						    + "PERSONAS QUE INTERVIENEN EN EL ACTO (X-Titular de derecho real de dominio,I-Titular de dominio incompleto)"
						    + ls_lineaSeparadora
						);
						lhot_d.setStyle("font-weight: bold;");

						lp_panel.getChildren().add(lhot_antSistema);
						//lp_panel.getChildren().add(lhot_valor);
						lp_panel.getChildren().add(lhot_esp);

						//lp_panel.getChildren().add(lhot_comentario);
						if(lb_cancelacion)
							lp_panel.getChildren().add(lhot_cancelacion);

						lp_panel.getChildren().add(lhot_d);
						lcrc_dataIntervinientes = lorc_rc.getAllMatriculas();

						if(CollectionUtils.isValidCollection(lcrc_dataIntervinientes))
						{
							boolean lb_headers;
							int     li_i;
							lb_headers     = true;
							li_i           = 1;

							/* Iteracion de los intervenientes por cada matricula */
							for(RegistroCalificacion lorc_tmp : lcrc_dataIntervinientes)
							{
								if(li_i > 1)
									lb_headers = false;

								li_i = li_i + 1;

								HtmlDataTable      lodt_table;
								ArrayList<Integer> lali_ali;
								String             ls_nombrePersona;
								String             ls_tipoDoc;

								lodt_table     = new HtmlDataTable();
								lali_ali       = new ArrayList<Integer>();
								ls_tipoDoc     = StringUtils.getStringNotNull(lorc_tmp.getTipoDoc());

								if(ls_tipoDoc.equalsIgnoreCase(IdentificadoresCommon.NIT))
									ls_nombrePersona = StringUtils.getStringNotNull(lorc_tmp.getRazonSocial()).toString();
								else
									ls_nombrePersona = StringUtils.getStringNotNull(lorc_tmp.getNombrePersona())
											                          .toString();

								lali_ali.add(new Integer(1));

								lodt_table.setValue(lali_ali);
								lodt_table.setStyleClass("TableContent");
								lodt_table.setHeaderClass("af_column_header-text SomeBorderStyle");
								lodt_table.setColumnClasses("af_column_cell-text OraTableBorder1111");

								UIColumn       luic_rol      = new UIColumn();
								HtmlOutputText lot_headerRol = new HtmlOutputText();
								lot_headerRol.setValue("Rol ");

								if(lb_headers)
									luic_rol.setHeader(lot_headerRol);

								HtmlOutputText lhtm_rol = new HtmlOutputText();
								lhtm_rol.setValue(lorc_tmp.getRolPersona() + " " + " :");

								if(ls_nombrePersona.length() > 72)
									lhtm_rol.setStyle("width: 30px;display: block;padding-bottom: 20px;");
								else
									lhtm_rol.setStyle("width: 30px;display: block;");

								luic_rol.getChildren().add(lhtm_rol);

								UIColumn       luic_porcetaje       = new UIColumn();
								HtmlOutputText lot_headerPorcentaje = new HtmlOutputText();
								lot_headerPorcentaje.setStyle("padding-left: 100px;");
								lot_headerPorcentaje.setValue("%");

								if(lb_headers)
									luic_porcetaje.setHeader(lot_headerPorcentaje);

								HtmlOutputText ls_porcentaje = new HtmlOutputText();

								if(StringUtils.isValidString(lorc_tmp.getPorcentajeStr()))
									ls_porcentaje.setValue(lorc_tmp.getPorcentajeStr() + "% ");

								ls_porcentaje.setStyle("width: 200px;display: block;text-align: center;");
								luic_porcetaje.getChildren().add(ls_porcentaje);

								UIColumn       luic_nombre      = new UIColumn();
								HtmlOutputText lot_headerNombre = new HtmlOutputText();
								lot_headerNombre.setValue("Nombre ");
								lot_headerNombre.setStyle("padding-left: 200px;");

								if(lb_headers)
									luic_nombre.setHeader(lot_headerNombre);

								HtmlOutputText lhtm_nombre = new HtmlOutputText();
								lhtm_nombre.setValue(ls_nombrePersona);
								lhtm_nombre.setStyle("width: 600px;display: block;");
								luic_nombre.getChildren().add(lhtm_nombre);

								UIColumn       luic_tipoDoc      = new UIColumn();
								HtmlOutputText lot_headerTipoDoc = new HtmlOutputText();
								lot_headerTipoDoc.setValue("Identificación");
								lot_headerTipoDoc.setStyle("padding-left: 30px;");

								if(lb_headers)
									luic_tipoDoc.setHeader(lot_headerTipoDoc);

								HtmlOutputText lhtm_tipoDoc = new HtmlOutputText();
								lhtm_tipoDoc.setValue(lorc_tmp.getTipoDoc() + " " + lorc_tmp.getDocumento());
								lhtm_tipoDoc.setStyle("width: 155px;display: block;");
								luic_tipoDoc.getChildren().add(lhtm_tipoDoc);

								UIColumn       luic_propietario      = new UIColumn();
								HtmlOutputText lot_headerPropietario = new HtmlOutputText();
								lot_headerPropietario.setValue("Titular ");

								if(lb_headers)
									luic_propietario.setHeader(lot_headerPropietario);

								HtmlOutputText lhtm_propietario = new HtmlOutputText();
								lhtm_propietario.setValue(lorc_tmp.getValuePropietario() + "  " + " -");
								lhtm_propietario.setStyle("width: 81px;display: block;text-align: center;");
								luic_propietario.getChildren().add(lhtm_propietario);

								UIColumn       luic_interesadaRR = new UIColumn();
								HtmlOutputText lot_headerRRR     = new HtmlOutputText();
								lot_headerRRR.setValue("Calidad interviniente");

								if(lb_headers)
									luic_interesadaRR.setHeader(lot_headerRRR);

								HtmlOutputText lhtm_rrr = new HtmlOutputText();
								lhtm_rrr.setValue(lorc_tmp.getInteresadaRrr());
								lhtm_rrr.setStyle("width: 120px;");
								luic_interesadaRR.getChildren().add(lhtm_rrr);

								lodt_table.getChildren().add(luic_rol);
								lodt_table.getChildren().add(luic_nombre);
								lodt_table.getChildren().add(luic_tipoDoc);
								lodt_table.getChildren().add(luic_propietario);
								lodt_table.getChildren().add(luic_porcetaje);
								lodt_table.getChildren().add(luic_interesadaRR);

								lp_panel.getChildren().add(lodt_table);
							}
						}

						lluic_hijos.add(lp_panel);
						li_column = li_column + 1;

						if(ld_dataModel != null)
							lcdb_dashboard.add(ld_dataModel);
					}

					setPanels(ldbc_dbc.getWidgets());
					setDataModel(ld_dataModel);
					setPaginacionAnotacion(lcdb_dashboard);
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_formulario1 correspondiente al valor del tipo de objeto String
	 * @param as_formulario2 correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	protected String consultarPorCriterios(String as_formulario1, String as_formulario2)
	{
		String         ls_return;
		ConsultaPredio locp_cp;
		FacesContext   lfc_context;
		boolean        lb_focus;

		ls_return       = null;
		locp_cp         = new ConsultaPredio();
		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			if(!CollectionUtils.isValidCollection(getValorSeleccionado()))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_CRITERIOS_DE_ENTRADA);

			locp_cp.setIdMatricula(getMatriculaLink());
			locp_cp.setIdCirculo(getCirculoLink());

			if(isDocumentoCriterio())
				locp_cp.setDocumentoCriterio(getDocumentoCriterios());

			if(isIntervinienteCriterio())
			{
				Persona lp_persona;

				lp_persona = getInfoPersona();

				if(lp_persona != null)
				{
					String           ls_tipoDocumento;
					String           ls_numeroDoc;
					String           ls_primerNombre;
					String           ls_segundoNombre;
					String           ls_primerApellido;
					String           ls_segundoApellido;
					String           ls_razonSocial;
					ExpresionRegular ler_validador;

					ler_validador          = ExpresionRegular.getExpresionRegular();
					ls_numeroDoc           = lp_persona.getIdDocumentoTipo();
					ls_primerNombre        = lp_persona.getPrimerNombre();
					ls_segundoNombre       = lp_persona.getSegundoNombre();
					ls_primerApellido      = lp_persona.getPrimerApellido();
					ls_segundoApellido     = lp_persona.getSegundoApellido();
					ls_tipoDocumento       = lp_persona.getTipoDocIdentidad();
					ls_razonSocial         = lp_persona.getRazonSocial();

					// Validaciones de carácteres
					if(!ler_validador.esDocumentoIdentidad(ls_numeroDoc))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);

					if(!ler_validador.esSoloLetras(ls_primerNombre))
						throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_PNOMBRE);

					if(!ler_validador.esSoloLetras(ls_segundoNombre))
						throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_SNOMBRE);

					if(!ler_validador.esSoloLetras(ls_primerApellido))
						throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_PAPELLIDO);

					if(!ler_validador.esSoloLetras(ls_segundoApellido))
						throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_SAPELLIDO);

					if(!StringUtils.isValidString(ls_razonSocial))
					{
						// Validación Tipo documento y documento
						if(StringUtils.isValidString(ls_tipoDocumento))
						{
							lb_focus = validateStyles(
								    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context,
								    ls_numeroDoc, lb_focus
								);

							if(!StringUtils.isValidString(ls_numeroDoc))
							{
								// Quita rojitos de los demás campos
								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad",
									    lfc_context, "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlRazonSocial", lfc_context,
									    "lleno", lb_focus
									);

								throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
							}
						}

						if(StringUtils.isValidString(ls_numeroDoc))
						{
							lb_focus = validateStyles(
								    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad", lfc_context,
								    ls_tipoDocumento, lb_focus
								);

							if(!StringUtils.isValidString(ls_tipoDocumento))
							{
								// Quita rojitos de los demás campos
								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlRazonSocial", lfc_context,
									    "lleno", lb_focus
									);

								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);
							}
						}

						// Validación Primer nombre y apellido
						if(StringUtils.isValidString(ls_primerNombre))
						{
							lb_focus = validateStyles(
								    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context,
								    ls_primerApellido, lb_focus
								);

							if(!StringUtils.isValidString(ls_primerApellido))
							{
								// Quita rojitos de los demás campos
								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad",
									    lfc_context, "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlRazonSocial", lfc_context,
									    "lleno", lb_focus
									);

								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
							}
						}

						if(StringUtils.isValidString(ls_primerApellido))
						{
							lb_focus = validateStyles(
								    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context,
								    ls_primerNombre, lb_focus
								);

							if(!StringUtils.isValidString(ls_primerNombre))
							{
								// Quita rojitos de los demás campos
								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad",
									    lfc_context, "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context, "lleno",
									    lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus     = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context,
									    "lleno", lb_focus
									);

								lb_focus = validateStyles(
									    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlRazonSocial", lfc_context,
									    "lleno", lb_focus
									);

								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
							}
						}
					}
					else
					{
						// Quita todos los rojitos en caso de tener razón social válida
						lb_focus     = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad", lfc_context,
							    "lleno", lb_focus
							);

						lb_focus     = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context, "lleno",
							    lb_focus
							);

						lb_focus     = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context, "lleno",
							    lb_focus
							);

						lb_focus     = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context, "lleno",
							    lb_focus
							);

						lb_focus     = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context, "lleno",
							    lb_focus
							);

						lb_focus = validateStyles(
							    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context, "lleno",
							    lb_focus
							);
					}

					if(!StringUtils.isValidString(ls_tipoDocumento))
					{
						if(!StringUtils.isValidString(ls_primerNombre) || !StringUtils.isValidString(ls_primerApellido))
						{
							if(!StringUtils.isValidString(ls_primerNombre))
							{
								if(!StringUtils.isValidString(ls_razonSocial))
								{
									// Quita todos los rojitos en caso de no haber parámetros de búsqueda
									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idSOMTipoDocIdentidad",
										    lfc_context, "lleno", lb_focus
										);

									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idDocumento", lfc_context,
										    "lleno", lb_focus
										);

									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPNombre", lfc_context,
										    "lleno", lb_focus
										);

									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSNombre", lfc_context,
										    "lleno", lb_focus
										);

									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlPApellido", lfc_context,
										    "lleno", lb_focus
										);

									lb_focus     = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlSApellido", lfc_context,
										    "lleno", lb_focus
										);

									lb_focus = validateStyles(
										    "fConsultaEstadoPredio:TvdetalleRegistroCalif:idOlRazonSocial", lfc_context,
										    "lleno", lb_focus
										);

									throw new B2BException(ErrorKeys.SIN_PARAMETROS_ENTRADA);
								}
							}
						}
					}
				}

				locp_cp.setPersonaCriterio(lp_persona);
			}

			if(isAnotacionCriterio())
			{
				Long ls_anotacionCriterio;
				ls_anotacionCriterio     = getAnotacionCriterioStr();

				lb_focus = validateStyles(as_formulario1, lfc_context, ls_anotacionCriterio, lb_focus);

				if(!NumericUtils.isValidLong(ls_anotacionCriterio))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ANOTACION);

				locp_cp.setIdAnotacion(getAnotacionCriterioStr());
			}

			if(isNaturalezaJuridicaCriterio())
			{
				String ls_codigoEspecificacion;

				ls_codigoEspecificacion = getCodEspecifiacion();

				if(StringUtils.isValidString(ls_codigoEspecificacion))
					locp_cp.setIdNaturaleza(ls_codigoEspecificacion);
				else
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURALEZA_JURIDICA_ACTO);
			}

			locp_cp.setConsultaTotal(isConsultaTotalCriterios());

			locp_cp = icepe_consultaPredioRemote.consultaCriterios(locp_cp, getUserId());

			if(locp_cp != null)
			{
				consultarAnotaciones(locp_cp, 0);
				PrimeFaces.current().ajax().update(as_formulario2);

				addMessage(MessagesKeys.FILTRO_APLICADO_CONSULTE_ANOTACIONES);
			}

			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_b2b)
		{
			addMessage(lb2be_b2b);
			PrimeFaces.current().ajax().update("fConsultaEstadoPredio:idPanelCriteriosInterviniente");
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}
}
