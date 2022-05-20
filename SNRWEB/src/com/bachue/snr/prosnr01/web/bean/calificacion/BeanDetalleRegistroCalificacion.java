package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistema;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.ValidacionDocumento;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr01.web.bean.correcciones.BeanEjecucionCorrecciones;
import com.bachue.snr.prosnr01.web.bean.digitador.masivo.BeanDetalleDigitadorMasivo;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.AgregarBotonAnotacion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.component.commandbutton.CommandButton;

import org.primefaces.component.dashboard.Dashboard;

import org.primefaces.component.panel.Panel;

import org.primefaces.component.wizard.Wizard;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.application.Application;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetalleRegistroCalificacion.
 *
 * @author jpatino
 */
@ManagedBean(name = "beanDetalleRegistroCalificacion")
@SessionScoped
public class BeanDetalleRegistroCalificacion extends BeanRegistroCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2426525639302631677L;

	/** Constante is_idForm. */
	private static final String is_idForm = "fDetalleRegistroCalificacion";

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fDetalleRegistroCalificacion:globalMsg";

	/** Constante is_wizardId. */
	private static final String is_wizardId = "fDetalleRegistroCalificacion:WdetalleRegistroCalif";

	/** Constante is_tabAnotacionesWizardId. */
	private static final String is_tabAnotacionesWizardId = "Anotaciones_id";

	/** Constante cs_DATOS_BASICOS_WIZARD_ID. */
	private static final String cs_DATOS_BASICOS_WIZARD_ID = "datosBasicos_id";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleRegistroCalificacion.class);

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad ioapr acc predio registro. */
	private AccPredioRegistro ioapr_accPredioRegistro;

	/** Propiedad ioapr acc predio registro view. */
	private AccPredioRegistro ioapr_accPredioRegistroView;

	/** Propiedad ia anotacion. */
	private Anotacion ia_anotacion;

	/** Propiedad iac anotacion cancelacion. */
	private AnotacionCancelacion iac_anotacionCancelacion;

	/** Propiedad iap anotacion predio detalle. */
	private AnotacionPredio iap_anotacionPredioDetalle;

	/** Propiedad iapc anotacion predio ciudadano. */
	private AnotacionPredioCiudadano iapc_anotacionPredioCiudadano;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad iap area predio. */
	private AreaPredio iap_areaPredio;

	/** Propiedad iap area predio view. */
	private AreaPredio iap_areaPredioView;

	/** Propiedad icl cabida linderos. */
	private CabidaLinderos icl_cabidaLinderos;

	/** Propiedad icl cabida linderos view. */
	private CabidaLinderos icl_cabidaLinderosView;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ica anotaciones agregadas. */
	private Collection<Anotacion> ica_anotacionesAgregadas;

	/** Propiedad ica intervinientes agregados. */
	private Collection<Anotacion> ica_intervinientesAgregados;

	/** Propiedad icap matriculas informacion. */
	private Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> icap_matriculasInformacion;

	/** Propiedad icddp direcciones temporales. */
	private Collection<DireccionDelPredio> icddp_direccionesTemporales;

	/** Propiedad icddp direcciones temporales view. */
	private Collection<DireccionDelPredio> icddp_direccionesTemporalesView;

	/** Propiedad icp data personas. */
	private Collection<Persona> icp_dataPersonas;

	/** Propiedad icp listado intervinientes. */
	private Collection<Persona> icp_listadoIntervinientes;

	/** Propiedad iddp direcciones predio. */
	private Collection<DireccionDelPredio> iddp_direccionesPredio;

	/** Propiedad iddp direcciones predio view. */
	private Collection<DireccionDelPredio> iddp_direccionesPredioView;

	/** Propiedad idb data model. */
	private Dashboard idb_dataModel;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad idas datos antiguo sistema view. */
	private DatosAntSistema idas_datosAntiguoSistemaView;

	/** Propiedad iodb datos basicos. */
	private DatosBasicos iodb_datosBasicos;

	/** Propiedad idap detalle area terreno. */
	private DetalleAreaPredio idap_detalleAreaTerreno;

	/** Propiedad idas digitador antiguo sistema remote. */
	@EJB
	private DigitadorAntiguoSistemaRemote idas_digitadorAntiguoSistemaRemote;

	/** Propiedad iddp direccion seleccionada. */
	private DireccionDelPredio iddp_direccionSeleccionada;

	/** Propiedad iddp direccion seleccionada view. */
	private DireccionDelPredio iddp_direccionSeleccionadaView;

	/** Propiedad idp direccion predio. */
	private DireccionDelPredio idp_direccionPredio;

	/** Propiedad idp direccion predio view. */
	private DireccionDelPredio idp_direccionPredioView;

	/** Propiedad id documento. */
	private Documento id_documento;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad ill predio documento. */
	private List<Map<String, Object>> ill_predioDocumento;

	/** Propiedad iols panels. */
	private List<String> iols_panels;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il numero anotacion. */
	private Long il_numeroAnotacion;

	/** Propiedad ihmsb revision matriculas. */
	private Map<String, Boolean> ihmsb_revisionMatriculas;

	/** Propiedad imsl rangos definitivos. */
	private Map<String, Long> imsl_rangosDefinitivos;

	/** Propiedad lhm predio detalle. */
	private Map<String, Object> lhm_predioDetalle;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iorc detalle anotacion. */
	private RegistroCalificacion iorc_detalleAnotacion;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is solicitud interviniente. */
	private SolicitudInterviniente is_solicitudInterviniente;

	/** Propiedad is copiar complementacion. */
	private String is_copiarComplementacion;

	/** Propiedad is copiar direccion. */
	private String is_copiarDireccion;

	/** Propiedad is copiar linderos. */
	private String is_copiarLinderos;

	/** Propiedad is desde. */
	private String is_desde;

	/** Propiedad is documento interviniente. */
	private String is_documentoInterviniente;

	/** Propiedad is hasta. */
	private String is_hasta;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id persona seleccion. */
	private String is_idPersonaSeleccion;

	/** Propiedad is id predio registro. */
	private String is_idPredioRegistro;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is naturaleza juridica seleccionada. */
	private String is_naturalezaJuridicaSeleccionada;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tab actual. */
	private String is_tabActual;

	/** Propiedad is tipo doc interviniente. */
	private String is_tipoDocInterviniente;

	/** Propiedad ib anotacion agregada. */
	private boolean ib_anotacionAgregada;

	/** Propiedad ib bloquear agregar area terreno. */
	private boolean ib_bloquearAgregarAreaTerreno;

	/** Propiedad ib complementacion guardada. */
	private boolean ib_complementacionGuardada;

	/** Propiedad ib copiar editar. */
	private boolean ib_copiarEditar;

	/** Propiedad ib correccion anotacion. */
	private boolean ib_correccionAnotacion;

	/** Propiedad ib deshabilitar campos por nit. */
	private boolean ib_deshabilitarCamposPorNit;

	/** Propiedad ib deshabilitar datos interviniente. */
	private boolean ib_deshabilitarDatosInterviniente;

	/** Propiedad ib devolucion. */
	private boolean ib_devolucion;

	/** Propiedad ib division material. */
	private boolean ib_divisionMaterial;

	/** Propiedad ib editor tabla direcciones. */
	private boolean ib_editorTablaDirecciones;

	/** Propiedad ib editor tabla direcciones view. */
	private boolean ib_editorTablaDireccionesView;

	/** Propiedad ib ejecucion correcciones. */
	private boolean ib_ejecucionCorrecciones;

	/** Propiedad ib englobes. */
	private boolean ib_englobes;

	/** Propiedad ib genera segregacion. */
	private boolean ib_generaSegregacion;

	/** Propiedad ib lindero disabled. */
	private boolean ib_linderoDisabled;

	/** Propiedad ib miga digitador masivo. */
	private boolean ib_migaDigitadorMasivo;

	/** Propiedad ib mostrar anotacion cancela. */
	private boolean ib_mostrarAnotacionCancela;

	/** Propiedad ib mostrar atras crear grabar. */
	private boolean ib_mostrarAtrasCrearGrabar;

	/** Propiedad ib mostrar check nueva linderos. */
	private boolean ib_mostrarCheckNuevaLinderos;

	/** Propiedad ib mostrar listado personas. */
	private boolean ib_mostrarListadoPersonas;

	/** Propiedad ib nueva linderos. */
	private boolean ib_nuevaLinderos;

	/** Propiedad ib nueva linderos dig masivo. */
	private boolean ib_nuevaLinderosDigMasivo;

	/** Propiedad ib nuevo lindero. */
	private boolean ib_nuevoLindero;

	/** Propiedad ib ocultar siguiente crear grabar. */
	private boolean ib_ocultarSiguienteCrearGrabar;

	/** Propiedad ib pais inter residencia. */
	private boolean ib_paisInterResidencia;

	/** Propiedad ib proceso loteo. */
	private boolean ib_procesoLoteo;

	/** Propiedad ib proceso reloteo. */
	private boolean ib_procesoReloteo;

	/** Propiedad ib proceso terminado crear grabar. */
	private boolean ib_procesoTerminadoCrearGrabar;

	/** Propiedad ib rango complementacion. */
	private boolean ib_rangoComplementacion;

	/** Propiedad ib rango direccion. */
	private boolean ib_rangoDireccion;

	/** Propiedad ib rango linderos. */
	private boolean ib_rangoLinderos;

	/** Propiedad ib regresar. */
	private boolean ib_regresar;

	/** Propiedad ib show dashboard. */
	private boolean ib_showDashboard;

	/** Propiedad ib show wizard. */
	private boolean ib_showWizard;

	/** Propiedad ib siguiente. */
	private boolean ib_siguiente;

	/** Propiedad ib terminar. */
	private boolean ib_terminar;

	/** Propiedad ib terminar proceso. */
	private boolean ib_terminarProceso;

	/** Propiedad ib validar propiedad horizontal. */
	private boolean ib_validarPropiedadHorizontal;

	/** Propiedad il contador anotaciones. */
	private long il_contadorAnotaciones;

	/** Propiedad il contador interviniente. */
	private long il_contadorInterviniente;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/** Propiedad il id anotacion general. */
	private long il_idAnotacionGeneral;

	/**
	 * Instancia un nuevo objeto bean detalle registro calificacion.
	 */
	public BeanDetalleRegistroCalificacion()
	{
		setEditorTablaDirecciones(false);
		clear();
	}

	/** {@inheritdoc} */
	public void setAccPredioRegistro(AccPredioRegistro aapr_apr)
	{
		ioapr_accPredioRegistro = aapr_apr;
	}

	/** {@inheritdoc} */
	public AccPredioRegistro getAccPredioRegistro()
	{
		if(ioapr_accPredioRegistro == null)
			ioapr_accPredioRegistro = new AccPredioRegistro();

		return ioapr_accPredioRegistro;
	}

	/**
	 * Modifica el valor de acc predio registro view.
	 *
	 * @param aapr_apr asigna el valor a la propiedad acc predio registro view
	 */
	public void setAccPredioRegistroView(AccPredioRegistro aapr_apr)
	{
		ioapr_accPredioRegistroView = aapr_apr;
	}

	/**
	 * Retorna el valor de acc predio registro view.
	 *
	 * @return el valor de acc predio registro view
	 */
	public AccPredioRegistro getAccPredioRegistroView()
	{
		if(ioapr_accPredioRegistroView == null)
			ioapr_accPredioRegistroView = new AccPredioRegistro();

		return ioapr_accPredioRegistroView;
	}

	/** {@inheritdoc} */
	public void setAnotacion(Anotacion aa_a)
	{
		ia_anotacion = aa_a;
	}

	/** {@inheritdoc} */
	public Anotacion getAnotacion()
	{
		if(ia_anotacion == null)
			ia_anotacion = new Anotacion();

		return ia_anotacion;
	}

	/** {@inheritdoc} */
	public void setAnotacionAgregada(boolean ab_b)
	{
		ib_anotacionAgregada = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isAnotacionAgregada()
	{
		return ib_anotacionAgregada;
	}

	/** {@inheritdoc} */
	public void setAnotacionCancelacion(AnotacionCancelacion aac_ac)
	{
		iac_anotacionCancelacion = aac_ac;
	}

	/** {@inheritdoc} */
	public AnotacionCancelacion getAnotacionCancelacion()
	{
		if(iac_anotacionCancelacion == null)
			iac_anotacionCancelacion = new AnotacionCancelacion();

		return iac_anotacionCancelacion;
	}

	/** {@inheritdoc} */
	public void setAnotacionPredioCiudadano(AnotacionPredioCiudadano aapc_apc)
	{
		iapc_anotacionPredioCiudadano = aapc_apc;
	}

	/** {@inheritdoc} */
	public AnotacionPredioCiudadano getAnotacionPredioCiudadano()
	{
		if(iapc_anotacionPredioCiudadano == null)
			iapc_anotacionPredioCiudadano = new AnotacionPredioCiudadano();

		return iapc_anotacionPredioCiudadano;
	}

	/** {@inheritdoc} */
	public void setAnotacionPredioDetalle(AnotacionPredio aap_ap)
	{
		iap_anotacionPredioDetalle = aap_ap;
	}

	/** {@inheritdoc} */
	public AnotacionPredio getAnotacionPredioDetalle()
	{
		if(iap_anotacionPredioDetalle == null)
		{
			iap_anotacionPredioDetalle = new AnotacionPredio();
			iap_anotacionPredioDetalle.setIdEstadoAnotacion(EstadoCommon.V);
		}

		return iap_anotacionPredioDetalle;
	}

	/** {@inheritdoc} */
	public void setAnotacionesAgregadas(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadas = aca_ca;
	}

	/** {@inheritdoc} */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return ica_anotacionesAgregadas;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/** {@inheritdoc} */
	public void setAreaPredio(AreaPredio aap_ap)
	{
		iap_areaPredio = aap_ap;
	}

	/** {@inheritdoc} */
	public AreaPredio getAreaPredio()
	{
		if(iap_areaPredio == null)
			iap_areaPredio = new AreaPredio();

		return iap_areaPredio;
	}

	/**
	 * Modifica el valor de area predio view.
	 *
	 * @param aap_ap asigna el valor a la propiedad area predio view
	 */
	public void setAreaPredioView(AreaPredio aap_ap)
	{
		iap_areaPredioView = aap_ap;
	}

	/**
	 * Retorna el valor de area predio view.
	 *
	 * @return el valor de area predio view
	 */
	public AreaPredio getAreaPredioView()
	{
		if(iap_areaPredioView == null)
			iap_areaPredioView = new AreaPredio();

		return iap_areaPredioView;
	}

	/** {@inheritdoc} */
	public void setAreaUI(AccAreaUI aaaui_aaui)
	{
		iaaui_areaUI = aaaui_aaui;
	}

	/** {@inheritdoc} */
	public AccAreaUI getAreaUI()
	{
		if(iaaui_areaUI == null)
			iaaui_areaUI = new AccAreaUI();

		return iaaui_areaUI;
	}

	/** {@inheritdoc} */
	public void setBloquearAgregarAreaTerreno(boolean ab_b)
	{
		ib_bloquearAgregarAreaTerreno = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isBloquearAgregarAreaTerreno()
	{
		return ib_bloquearAgregarAreaTerreno;
	}

	/** {@inheritdoc} */
	public void setCabidaLinderos(CabidaLinderos acl_cabidaLinderos)
	{
		icl_cabidaLinderos = acl_cabidaLinderos;
	}

	/** {@inheritdoc} */
	public CabidaLinderos getCabidaLinderos()
	{
		if(icl_cabidaLinderos == null)
			icl_cabidaLinderos = new CabidaLinderos();

		return icl_cabidaLinderos;
	}

	/**
	 * Modifica el valor de cabida linderos view.
	 *
	 * @param acl_cabidaLinderos asigna el valor a la propiedad cabida linderos view
	 */
	public void setCabidaLinderosView(CabidaLinderos acl_cabidaLinderos)
	{
		icl_cabidaLinderosView = acl_cabidaLinderos;
	}

	/**
	 * Retorna el valor de cabida linderos view.
	 *
	 * @return el valor de cabida linderos view
	 */
	public CabidaLinderos getCabidaLinderosView()
	{
		if(icl_cabidaLinderosView == null)
			icl_cabidaLinderosView = new CabidaLinderos();

		return icl_cabidaLinderosView;
	}

	/**
	 * Modifica el valor de complementacion guardada.
	 *
	 * @param ab_b asigna el valor a la propiedad complementacion guardada
	 */
	public void setComplementacionGuardada(boolean ab_b)
	{
		ib_complementacionGuardada = ab_b;
	}

	/**
	 * Valida la propiedad complementacion guardada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en complementacion guardada
	 */
	public boolean isComplementacionGuardada()
	{
		return ib_complementacionGuardada;
	}

	/** {@inheritdoc} */
	public void setContadorAnotaciones(long al_l)
	{
		il_contadorAnotaciones = al_l;
	}

	/** {@inheritdoc} */
	public long getContadorAnotaciones()
	{
		return il_contadorAnotaciones;
	}

	/** {@inheritdoc} */
	public void setContadorInterviniente(long al_l)
	{
		il_contadorInterviniente = al_l;
	}

	/** {@inheritdoc} */
	public long getContadorInterviniente()
	{
		return il_contadorInterviniente;
	}

	/**
	 * Modifica el valor de copiar complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad copiar complementacion
	 */
	public void setCopiarComplementacion(String as_s)
	{
		is_copiarComplementacion = as_s;
	}

	/**
	 * Retorna el valor de copiar complementacion.
	 *
	 * @return el valor de copiar complementacion
	 */
	public String getCopiarComplementacion()
	{
		return is_copiarComplementacion;
	}

	/**
	 * Modifica el valor de copiar direccion.
	 *
	 * @param as_s asigna el valor a la propiedad copiar direccion
	 */
	public void setCopiarDireccion(String as_s)
	{
		is_copiarDireccion = as_s;
	}

	/**
	 * Retorna el valor de copiar direccion.
	 *
	 * @return el valor de copiar direccion
	 */
	public String getCopiarDireccion()
	{
		return is_copiarDireccion;
	}

	/**
	 * Modifica el valor de copiar editar.
	 *
	 * @param copiarEditar asigna el valor a la propiedad copiar editar
	 */
	public void setCopiarEditar(boolean copiarEditar)
	{
		ib_copiarEditar = copiarEditar;
	}

	/**
	 * Valida la propiedad copiar editar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en copiar editar
	 */
	public boolean isCopiarEditar()
	{
		return ib_copiarEditar;
	}

	/**
	 * Modifica el valor de copiar linderos.
	 *
	 * @param as_s asigna el valor a la propiedad copiar linderos
	 */
	public void setCopiarLinderos(String as_s)
	{
		is_copiarLinderos = as_s;
	}

	/**
	 * Retorna el valor de copiar linderos.
	 *
	 * @return el valor de copiar linderos
	 */
	public String getCopiarLinderos()
	{
		return is_copiarLinderos;
	}

	/**
	 * @param Modifica el valor de la propiedad correccionAnotacion por correccionAnotacion
	 */
	public void setCorreccionAnotacion(boolean ab_b)
	{
		ib_correccionAnotacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad correccionAnotacion
	 */
	public boolean isCorreccionAnotacion()
	{
		return ib_correccionAnotacion;
	}

	/** {@inheritdoc} */
	public void setDataModel(Dashboard adb_adb)
	{
		idb_dataModel = adb_adb;
	}

	/** {@inheritdoc} */
	public Dashboard getDataModel()
	{
		return idb_dataModel;
	}

	/**
	 * Modifica el valor de data personas.
	 *
	 * @param acp_cp asigna el valor a la propiedad data personas
	 */
	public void setDataPersonas(Collection<Persona> acp_cp)
	{
		icp_dataPersonas = acp_cp;
	}

	/**
	 * Retorna el valor de data personas.
	 *
	 * @return el valor de data personas
	 */
	public Collection<Persona> getDataPersonas()
	{
		return icp_dataPersonas;
	}

	/** {@inheritdoc} */
	public void setDatosAntiguoSistema(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistema = adas_das;
	}

	/** {@inheritdoc} */
	public DatosAntSistema getDatosAntiguoSistema()
	{
		if(idas_datosAntiguoSistema == null)
		{
			idas_datosAntiguoSistema = new DatosAntSistema();
			idas_datosAntiguoSistema.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return idas_datosAntiguoSistema;
	}

	/**
	 * Modifica el valor de datos antiguo sistema view.
	 *
	 * @param adas_das asigna el valor a la propiedad datos antiguo sistema view
	 */
	public void setDatosAntiguoSistemaView(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistemaView = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema view.
	 *
	 * @return el valor de datos antiguo sistema view
	 */
	public DatosAntSistema getDatosAntiguoSistemaView()
	{
		if(idas_datosAntiguoSistemaView == null)
		{
			idas_datosAntiguoSistemaView = new DatosAntSistema();
			idas_datosAntiguoSistemaView.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return idas_datosAntiguoSistemaView;
	}

	/** {@inheritdoc} */
	public void setDatosBasicos(DatosBasicos adb_db)
	{
		iodb_datosBasicos = adb_db;
	}

	/** {@inheritdoc} */
	public DatosBasicos getDatosBasicos()
	{
		if(iodb_datosBasicos == null)
			iodb_datosBasicos = new DatosBasicos();

		return iodb_datosBasicos;
	}

	/**
	 * Modifica el valor de desde.
	 *
	 * @param as_s asigna el valor a la propiedad desde
	 */
	public void setDesde(String as_s)
	{
		is_desde = as_s;
	}

	/**
	 * Retorna el valor de desde.
	 *
	 * @return el valor de desde
	 */
	public String getDesde()
	{
		return is_desde;
	}

	/** {@inheritdoc} */
	public void setDeshabilitarCamposPorNit(boolean ab_b)
	{
		ib_deshabilitarCamposPorNit = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isDeshabilitarCamposPorNit()
	{
		return ib_deshabilitarCamposPorNit;
	}

	/** {@inheritdoc} */
	public void setDeshabilitarDatosInterviniente(boolean ab_b)
	{
		ib_deshabilitarDatosInterviniente = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isDeshabilitarDatosInterviniente()
	{
		return ib_deshabilitarDatosInterviniente;
	}

	/** {@inheritdoc} */
	public void setDetalleAnotacion(RegistroCalificacion arc_rc)
	{
		iorc_detalleAnotacion = arc_rc;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion getDetalleAnotacion()
	{
		if(iorc_detalleAnotacion == null)
			iorc_detalleAnotacion = new RegistroCalificacion();

		return iorc_detalleAnotacion;
	}

	/** {@inheritdoc} */
	public void setDetalleAreaTerreno(DetalleAreaPredio adap_dap)
	{
		idap_detalleAreaTerreno = adap_dap;
	}

	/** {@inheritdoc} */
	public DetalleAreaPredio getDetalleAreaTerreno()
	{
		if(idap_detalleAreaTerreno == null)
			idap_detalleAreaTerreno = new DetalleAreaPredio();

		return idap_detalleAreaTerreno;
	}

	/** {@inheritdoc} */
	public void setDevolucion(boolean ab_b)
	{
		ib_devolucion = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isDevolucion()
	{
		return ib_devolucion;
	}

	/** {@inheritdoc} */
	public void setDireccionPredio(DireccionDelPredio addp_ddp)
	{
		idp_direccionPredio = addp_ddp;
	}

	/** {@inheritdoc} */
	public DireccionDelPredio getDireccionPredio()
	{
		if(idp_direccionPredio == null)
			idp_direccionPredio = new DireccionDelPredio();

		return idp_direccionPredio;
	}

	/**
	 * Modifica el valor de direccion predio view.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direccion predio view
	 */
	public void setDireccionPredioView(DireccionDelPredio addp_ddp)
	{
		idp_direccionPredioView = addp_ddp;
	}

	/**
	 * Retorna el valor de direccion predio view.
	 *
	 * @return el valor de direccion predio view
	 */
	public DireccionDelPredio getDireccionPredioView()
	{
		if(idp_direccionPredioView == null)
			idp_direccionPredioView = new DireccionDelPredio();

		return idp_direccionPredioView;
	}

	/**
	 * Modifica el valor de direccion seleccionada.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direccion seleccionada
	 */
	public void setDireccionSeleccionada(DireccionDelPredio addp_ddp)
	{
		iddp_direccionSeleccionada = addp_ddp;
	}

	/**
	 * Retorna el valor de direccion seleccionada.
	 *
	 * @return el valor de direccion seleccionada
	 */
	public DireccionDelPredio getDireccionSeleccionada()
	{
		if(iddp_direccionSeleccionada == null)
			iddp_direccionSeleccionada = new DireccionDelPredio();

		return iddp_direccionSeleccionada;
	}

	/**
	 * Modifica el valor de direccion seleccionada view.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direccion seleccionada view
	 */
	public void setDireccionSeleccionadaView(DireccionDelPredio addp_ddp)
	{
		iddp_direccionSeleccionadaView = addp_ddp;
	}

	/**
	 * Retorna el valor de direccion seleccionada view.
	 *
	 * @return el valor de direccion seleccionada view
	 */
	public DireccionDelPredio getDireccionSeleccionadaView()
	{
		if(iddp_direccionSeleccionadaView == null)
			iddp_direccionSeleccionadaView = new DireccionDelPredio();

		return iddp_direccionSeleccionadaView;
	}

	/** {@inheritdoc} */
	public void setDireccionesPredio(Collection<DireccionDelPredio> acddp_cddp)
	{
		iddp_direccionesPredio = acddp_cddp;
	}

	/** {@inheritdoc} */
	public Collection<DireccionDelPredio> getDireccionesPredio()
	{
		if(iddp_direccionesPredio == null)
			iddp_direccionesPredio = new LinkedList<DireccionDelPredio>();

		return iddp_direccionesPredio;
	}

	/**
	 * Modifica el valor de direcciones predio view.
	 *
	 * @param acddp_cddp asigna el valor a la propiedad direcciones predio view
	 */
	public void setDireccionesPredioView(Collection<DireccionDelPredio> acddp_cddp)
	{
		iddp_direccionesPredioView = acddp_cddp;
	}

	/**
	 * Retorna el valor de direcciones predio view.
	 *
	 * @return el valor de direcciones predio view
	 */
	public Collection<DireccionDelPredio> getDireccionesPredioView()
	{
		if(iddp_direccionesPredioView == null)
			iddp_direccionesPredioView = new LinkedList<DireccionDelPredio>();

		return iddp_direccionesPredioView;
	}

	/** {@inheritdoc} */
	public void setDireccionesTemporales(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesTemporales = acddp_cddp;
	}

	/** {@inheritdoc} */
	public Collection<DireccionDelPredio> getDireccionesTemporales()
	{
		if(!CollectionUtils.isValidCollection(icddp_direccionesTemporales))
			icddp_direccionesTemporales = new LinkedList<DireccionDelPredio>();

		return icddp_direccionesTemporales;
	}

	/**
	 * Modifica el valor de direcciones temporales view.
	 *
	 * @param acddp_cddp asigna el valor a la propiedad direcciones temporales view
	 */
	public void setDireccionesTemporalesView(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesTemporalesView = acddp_cddp;
	}

	/**
	 * Retorna el valor de direcciones temporales view.
	 *
	 * @return el valor de direcciones temporales view
	 */
	public Collection<DireccionDelPredio> getDireccionesTemporalesView()
	{
		if(!CollectionUtils.isValidCollection(icddp_direccionesTemporalesView))
			icddp_direccionesTemporalesView = new LinkedList<DireccionDelPredio>();

		return icddp_direccionesTemporalesView;
	}

	/** {@inheritdoc} */
	public void setDivisionMaterial(boolean ab_b)
	{
		ib_divisionMaterial = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isDivisionMaterial()
	{
		return ib_divisionMaterial;
	}

	/** {@inheritdoc} */
	public void setDocumento(Documento id_d)
	{
		id_documento = id_d;
	}

	/** {@inheritdoc} */
	public Documento getDocumento()
	{
		if(id_documento == null)
		{
			id_documento = new Documento();
			id_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return id_documento;
	}

	/** {@inheritdoc} */
	public void setDocumentoInterviniente(String as_s)
	{
		is_documentoInterviniente = as_s;
	}

	/** {@inheritdoc} */
	public String getDocumentoInterviniente()
	{
		return is_documentoInterviniente;
	}

	/**
	 * Modifica el valor de editor tabla direcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad editor tabla direcciones
	 */
	public void setEditorTablaDirecciones(boolean ab_b)
	{
		ib_editorTablaDirecciones = ab_b;
	}

	/**
	 * Valida la propiedad editor tabla direcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editor tabla direcciones
	 */
	public boolean isEditorTablaDirecciones()
	{
		return ib_editorTablaDirecciones;
	}

	/**
	 * Modifica el valor de editor tabla direcciones view.
	 *
	 * @param ab_b asigna el valor a la propiedad editor tabla direcciones view
	 */
	public void setEditorTablaDireccionesView(boolean ab_b)
	{
		ib_editorTablaDireccionesView = ab_b;
	}

	/**
	 * Valida la propiedad editor tabla direcciones view.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editor tabla direcciones view
	 */
	public boolean isEditorTablaDireccionesView()
	{
		return ib_editorTablaDireccionesView;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad ejecucionCorrecciones por ejecucionCorrecciones
	 */
	public void setEjecucionCorrecciones(boolean ab_b)
	{
		ib_ejecucionCorrecciones = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ejecucionCorrecciones
	 */
	public boolean isEjecucionCorrecciones()
	{
		return ib_ejecucionCorrecciones;
	}

	/**
	 * Modifica el valor de englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad englobes
	 */
	public void setEnglobes(boolean ab_b)
	{
		ib_englobes = ab_b;
	}

	/**
	 * Valida la propiedad englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en englobes
	 */
	public boolean isEnglobes()
	{
		return ib_englobes;
	}

	/**
	 * Modifica el valor de genera segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad genera segregacion
	 */
	public void setGeneraSegregacion(boolean ab_b)
	{
		ib_generaSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad genera segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en genera segregacion
	 */
	public boolean isGeneraSegregacion()
	{
		return ib_generaSegregacion;
	}

	/**
	 * Modifica el valor de hasta.
	 *
	 * @param as_s asigna el valor a la propiedad hasta
	 */
	public void setHasta(String as_s)
	{
		is_hasta = as_s;
	}

	/**
	 * Retorna el valor de hasta.
	 *
	 * @return el valor de hasta
	 */
	public String getHasta()
	{
		return is_hasta;
	}

	/** {@inheritdoc} */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/** {@inheritdoc} */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/** {@inheritdoc} */
	public void setIdAnotacionGeneral(long al_l)
	{
		il_idAnotacionGeneral = al_l;
	}

	/** {@inheritdoc} */
	public long getIdAnotacionGeneral()
	{
		return il_idAnotacionGeneral;
	}

	/** {@inheritdoc} */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/** {@inheritdoc} */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/** {@inheritdoc} */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/** {@inheritdoc} */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/** {@inheritdoc} */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/** {@inheritdoc} */
	public String getIdPais()
	{
		return is_idPais;
	}

	/** {@inheritdoc} */
	public void setIdPersonaSeleccion(String as_s)
	{
		is_idPersonaSeleccion = as_s;
	}

	/** {@inheritdoc} */
	public String getIdPersonaSeleccion()
	{
		return is_idPersonaSeleccion;
	}

	/**
	 * Modifica el valor de id predio registro.
	 *
	 * @param as_s asigna el valor a la propiedad id predio registro
	 */
	public void setIdPredioRegistro(String as_s)
	{
		is_idPredioRegistro = as_s;
	}

	/**
	 * Retorna el valor de id predio registro.
	 *
	 * @return el valor de id predio registro
	 */
	public String getIdPredioRegistro()
	{
		return is_idPredioRegistro;
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
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/** {@inheritdoc} */
	public void setIntervinientesAgregados(Collection<Anotacion> aca_ca)
	{
		ica_intervinientesAgregados = aca_ca;
	}

	/** {@inheritdoc} */
	public Collection<Anotacion> getIntervinientesAgregados()
	{
		return ica_intervinientesAgregados;
	}

	/**
	 * Modifica el valor de lindero disabled.
	 *
	 * @param ab_b asigna el valor a la propiedad lindero disabled
	 */
	public void setLinderoDisabled(boolean ab_b)
	{
		ib_linderoDisabled = ab_b;
	}

	/**
	 * Valida la propiedad lindero disabled.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en lindero disabled
	 */
	public boolean isLinderoDisabled()
	{
		return ib_linderoDisabled;
	}

	/** {@inheritdoc} */
	public void setListadoIntervinientes(Collection<Persona> acp_cp)
	{
		icp_listadoIntervinientes = acp_cp;
	}

	/** {@inheritdoc} */
	public Collection<Persona> getListadoIntervinientes()
	{
		return icp_listadoIntervinientes;
	}

	/** {@inheritdoc} */
	public void setMatriculasInformacion(Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> acap_cap)
	{
		icap_matriculasInformacion = acap_cap;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 * Modifica el valor de miga digitador masivo.
	 *
	 * @param ab_s asigna el valor a la propiedad miga digitador masivo
	 */
	public void setMigaDigitadorMasivo(boolean ab_s)
	{
		ib_migaDigitadorMasivo = ab_s;
	}

	/**
	 * Valida la propiedad miga digitador masivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en miga digitador masivo
	 */
	public boolean isMigaDigitadorMasivo()
	{
		return ib_migaDigitadorMasivo;
	}

	/** {@inheritdoc} */
	public void setMostrarAnotacionCancela(boolean ab_b)
	{
		ib_mostrarAnotacionCancela = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isMostrarAnotacionCancela()
	{
		return ib_mostrarAnotacionCancela;
	}

	/** {@inheritdoc} */
	public void setMostrarAtrasCrearGrabar(boolean ab_b)
	{
		ib_mostrarAtrasCrearGrabar = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isMostrarAtrasCrearGrabar()
	{
		return ib_mostrarAtrasCrearGrabar;
	}

	/**
	 * Modifica el valor de mostrar check nueva linderos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar check nueva linderos
	 */
	public void setMostrarCheckNuevaLinderos(boolean ab_b)
	{
		ib_mostrarCheckNuevaLinderos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar check nueva linderos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar check nueva linderos
	 */
	public boolean isMostrarCheckNuevaLinderos()
	{
		return ib_mostrarCheckNuevaLinderos;
	}

	/** {@inheritdoc} */
	public void setMostrarListadoPersonas(boolean ab_b)
	{
		ib_mostrarListadoPersonas = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isMostrarListadoPersonas()
	{
		return ib_mostrarListadoPersonas;
	}

	/** {@inheritdoc} */
	public void setNaturalezaJuridicaSeleccionada(String as_s)
	{
		is_naturalezaJuridicaSeleccionada = as_s;
	}

	/** {@inheritdoc} */
	public String getNaturalezaJuridicaSeleccionada()
	{
		return is_naturalezaJuridicaSeleccionada;
	}

	/** {@inheritdoc} */
	public void setNuevaLinderos(boolean ab_b)
	{
		ib_nuevaLinderos = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isNuevaLinderos()
	{
		return ib_nuevaLinderos;
	}

	/**
	 * Modifica el valor de nueva linderos dig masivo.
	 *
	 * @param ab_b asigna el valor a la propiedad nueva linderos dig masivo
	 */
	public void setNuevaLinderosDigMasivo(boolean ab_b)
	{
		ib_nuevaLinderosDigMasivo = ab_b;
	}

	/**
	 * Valida la propiedad nueva linderos dig masivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nueva linderos dig masivo
	 */
	public boolean isNuevaLinderosDigMasivo()
	{
		return ib_nuevaLinderosDigMasivo;
	}

	/**
	 * Modifica el valor de nuevo lindero.
	 *
	 * @param ab_b asigna el valor a la propiedad nuevo lindero
	 */
	public void setNuevoLindero(boolean ab_b)
	{
		ib_nuevoLindero = ab_b;
	}

	/**
	 * Valida la propiedad nuevo lindero.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nuevo lindero
	 */
	public boolean isNuevoLindero()
	{
		return ib_nuevoLindero;
	}

	/** {@inheritdoc} */
	public void setNumeroAnotacion(Long al_l)
	{
		il_numeroAnotacion = al_l;
	}

	/** {@inheritdoc} */
	public Long getNumeroAnotacion()
	{
		return il_numeroAnotacion;
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
	public void setOcultarSiguienteCrearGrabar(boolean ab_b)
	{
		ib_ocultarSiguienteCrearGrabar = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isOcultarSiguienteCrearGrabar()
	{
		return ib_ocultarSiguienteCrearGrabar;
	}

	/** {@inheritdoc} */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		Collection<OficinaOrigen> lcidt_datos;
		Documento                 ld_documento;

		lcidt_datos      = null;
		ld_documento     = getDocumento();

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
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/** {@inheritdoc} */
	public void setOficinaOrigenApertura()
	{
	}

	/** {@inheritdoc} */
	public Collection<OficinaOrigen> getOficinaOrigenApertura()
	{
		Collection<OficinaOrigen> lcoo_datos;

		lcoo_datos = null;

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
				lcoo_datos = irr_referenceRemote.findOficinaOrigen(oficina);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcoo_datos = null;
		}

		return lcoo_datos;
	}

	/** {@inheritdoc} */
	public void setPaisInterResidencia(boolean ab_b)
	{
		ib_paisInterResidencia = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isPaisInterResidencia()
	{
		return ib_paisInterResidencia;
	}

	/** {@inheritdoc} */
	public void setPanels(List<String> aols_ls)
	{
		iols_panels = aols_ls;
	}

	/** {@inheritdoc} */
	public List<String> getPanels()
	{
		return iols_panels;
	}

	/** {@inheritdoc} */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/** {@inheritdoc} */
	public Persona getPersona()
	{
		if(ip_persona == null)
			ip_persona = new Persona();

		return ip_persona;
	}

	/**
	 * Sets the predio detalle.
	 *
	 * @param ahmso_hmso correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setPredioDetalle(Map<String, Object> ahmso_hmso)
	{
		lhm_predioDetalle = ahmso_hmso;
	}

	/**
	 * Retorna el valor de predio detalle.
	 *
	 * @return el valor de predio detalle
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<String, Object> getPredioDetalle()
	    throws B2BException
	{
		if(lhm_predioDetalle == null)
			getPredioDocumento();

		return lhm_predioDetalle;
	}

	/** {@inheritdoc} */
	public void setPredioDocumento(List<Map<String, Object>> alllhmso_lllhmso)
	{
		ill_predioDocumento = alllhmso_lllhmso;
	}

	/** {@inheritdoc} */
	public void setProcesoLoteo(boolean ab_b)
	{
		ib_procesoLoteo = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isProcesoLoteo()
	{
		return ib_procesoLoteo;
	}

	/** {@inheritdoc} */
	public void setProcesoReloteo(boolean ab_b)
	{
		ib_procesoReloteo = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isProcesoReloteo()
	{
		return ib_procesoReloteo;
	}

	/** {@inheritdoc} */
	public void setProcesoTerminadoCrearGrabar(boolean ab_b)
	{
		ib_procesoTerminadoCrearGrabar = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isProcesoTerminadoCrearGrabar()
	{
		return ib_procesoTerminadoCrearGrabar;
	}

	/**
	 * Modifica el valor de rango complementacion.
	 *
	 * @param ab_b asigna el valor a la propiedad rango complementacion
	 */
	public void setRangoComplementacion(boolean ab_b)
	{
		ib_rangoComplementacion = ab_b;
	}

	/**
	 * Valida la propiedad rango complementacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rango complementacion
	 */
	public boolean isRangoComplementacion()
	{
		return ib_rangoComplementacion;
	}

	/**
	 * Modifica el valor de rango direccion.
	 *
	 * @param ab_b asigna el valor a la propiedad rango direccion
	 */
	public void setRangoDireccion(boolean ab_b)
	{
		ib_rangoDireccion = ab_b;
	}

	/**
	 * Valida la propiedad rango direccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rango direccion
	 */
	public boolean isRangoDireccion()
	{
		return ib_rangoDireccion;
	}

	/**
	 * Modifica el valor de rango linderos.
	 *
	 * @param ab_b asigna el valor a la propiedad rango linderos
	 */
	public void setRangoLinderos(boolean ab_b)
	{
		ib_rangoLinderos = ab_b;
	}

	/**
	 * Valida la propiedad rango linderos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rango linderos
	 */
	public boolean isRangoLinderos()
	{
		return ib_rangoLinderos;
	}

	/**
	 * Sets the rangos definitivos.
	 *
	 * @param amsl_msl correspondiente al valor del tipo de objeto Map<String,Long>
	 */
	public void setRangosDefinitivos(Map<String, Long> amsl_msl)
	{
		imsl_rangosDefinitivos = amsl_msl;
	}

	/**
	 * Retorna el valor de rangos definitivos.
	 *
	 * @return el valor de rangos definitivos
	 */
	public Map<String, Long> getRangosDefinitivos()
	{
		return imsl_rangosDefinitivos;
	}

	/**
	 * Modifica el valor de regresar.
	 *
	 * @param ab_b asigna el valor a la propiedad regresar
	 */
	public void setRegresar(boolean ab_b)
	{
		ib_regresar = ab_b;
	}

	/**
	 * Valida la propiedad regresar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en regresar
	 */
	public boolean isRegresar()
	{
		return ib_regresar;
	}

	/** {@inheritdoc} */
	public void setRevisionMatriculas(Map<String, Boolean> ahmso_hmso)
	{
		ihmsb_revisionMatriculas = ahmso_hmso;
	}

	/** {@inheritdoc} */
	public Map<String, Boolean> getRevisionMatriculas()
	{
		if(ihmsb_revisionMatriculas == null)
			ihmsb_revisionMatriculas = new HashMap<String, Boolean>();

		return ihmsb_revisionMatriculas;
	}

	/**
	 * Modifica el valor de show dashboard.
	 *
	 * @param ab_b asigna el valor a la propiedad show dashboard
	 */
	public void setShowDashboard(boolean ab_b)
	{
		ib_showDashboard = ab_b;
	}

	/**
	 * Valida la propiedad show dashboard.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en show dashboard
	 */
	public boolean isShowDashboard()
	{
		return ib_showDashboard;
	}

	/**
	 * Modifica el valor de show wizard.
	 *
	 * @param ab_b asigna el valor a la propiedad show wizard
	 */
	public void setShowWizard(boolean ab_b)
	{
		ib_showWizard = ab_b;
	}

	/**
	 * Valida la propiedad show wizard.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en show wizard
	 */
	public boolean isShowWizard()
	{
		return ib_showWizard;
	}

	/**
	 * Modifica el valor de siguiente.
	 *
	 * @param ab_b asigna el valor a la propiedad siguiente
	 */
	public void setSiguiente(boolean ab_b)
	{
		ib_siguiente = ab_b;
	}

	/**
	 * Valida la propiedad siguiente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en siguiente
	 */
	public boolean isSiguiente()
	{
		return ib_siguiente;
	}

	/** {@inheritdoc} */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		is_solicitudInterviniente = asi_si;
	}

	/** {@inheritdoc} */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		if(is_solicitudInterviniente == null)
			is_solicitudInterviniente = new SolicitudInterviniente();

		return is_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de tab actual.
	 *
	 * @param as_s asigna el valor a la propiedad tab actual
	 */
	public void setTabActual(String as_s)
	{
		is_tabActual = as_s;
	}

	/**
	 * Retorna el valor de tab actual.
	 *
	 * @return el valor de tab actual
	 */
	public String getTabActual()
	{
		if(!StringUtils.isValidString(is_tabActual))
			is_tabActual = cs_DATOS_BASICOS_WIZARD_ID;

		return is_tabActual;
	}

	/**
	 * Modifica el valor de terminar.
	 *
	 * @param ab_b asigna el valor a la propiedad terminar
	 */
	public void setTerminar(boolean ab_b)
	{
		ib_terminar = ab_b;
	}

	/**
	 * Valida la propiedad terminar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en terminar
	 */
	public boolean isTerminar()
	{
		return ib_terminar;
	}

	/**
	 * Modifica el valor de terminar proceso.
	 *
	 * @param ab_b asigna el valor a la propiedad terminar proceso
	 */
	public void setTerminarProceso(boolean ab_b)
	{
		ib_terminarProceso = ab_b;
	}

	/**
	 * Valida la propiedad terminar proceso.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en terminar proceso
	 */
	public boolean isTerminarProceso()
	{
		return ib_terminarProceso;
	}

	/** {@inheritdoc} */
	public void setTipoDocInterviniente(String as_s)
	{
		is_tipoDocInterviniente = as_s;
	}

	/** {@inheritdoc} */
	public String getTipoDocInterviniente()
	{
		return is_tipoDocInterviniente;
	}

	/** {@inheritdoc} */
	public void setValidarPropiedadHorizontal(boolean ab_b)
	{
		ib_validarPropiedadHorizontal = ab_b;
	}

	/** {@inheritdoc} */
	public boolean isValidarPropiedadHorizontal()
	{
		return ib_validarPropiedadHorizontal;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionSalvarView()
	{
		Collection<DireccionDelPredio> lcddp_direccionesPredio;
		String                         ls_return;

		lcddp_direccionesPredio     = getDireccionesPredioView();
		ls_return                   = NavegacionCommon.DETALLE_DIGITADOR_MASIVO;

		accionVolverView();

		try
		{
			BeanDetalleDigitadorMasivo                                   lbdm_bean;
			Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> locap_tmp;
			FacesContext                                                 lfc_context;
			Map<String, Boolean>                                         lcs_tmp;

			lfc_context     = FacesUtils.getFacesContext();
			lbdm_bean       = (BeanDetalleDigitadorMasivo)lfc_context.getApplication()
					                                                     .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_DIGITADOR_MASIVO, BeanDetalleDigitadorMasivo.class
					);
			lcs_tmp         = getRevisionMatriculas();

			if(lbdm_bean != null)
			{
				AccPredioRegistro    lapr_pr;
				AccPredioRegistro    lapr_data;
				RegistroCalificacion lrc_data;
				String               ls_idPredioRegistro;
				String               ls_remoteIp;
				String               ls_userId;

				lapr_data               = getAccPredioRegistroView();
				lapr_pr                 = new AccPredioRegistro();
				ls_idPredioRegistro     = getIdPredioRegistro();
				lrc_data                = null;
				ls_remoteIp             = getRemoteIpAddress();
				ls_userId               = getUserId();

				lbdm_bean.infoMatriculasDigitador(getIdTurno());

				locap_tmp = lbdm_bean.getMatriculasInformacion();

				lcs_tmp.put(ls_idPredioRegistro, new Boolean(true));

				if(CollectionUtils.isValidCollection(lcs_tmp))
				{
					for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio loap_ap : locap_tmp)
					{
						if(loap_ap != null)
							loap_ap.setRevisadoDigitador(
							    BooleanUtils.getBooleanValue(lcs_tmp.get(loap_ap.getIdPredioRegistro()))
							);
					}
				}

				if(lapr_data != null)
				{
					BeanDireccion                  lb_beanDireccion;
					Collection<DireccionDelPredio> lcddp_direcciones;
					Long                           ll_idMatricula;
					String                         ls_idCirculo;

					lb_beanDireccion      = getBeanDireccion();
					lcddp_direcciones     = lb_beanDireccion.getDireccionesPredio2();
					ll_idMatricula        = lapr_data.getIdMatricula();
					ls_idCirculo          = lapr_data.getIdCirculo();

					if(CollectionUtils.isValidCollection(lcddp_direcciones))
					{
						HashMap<String, DireccionPredio> lhmsdp_direcciones;
						int                              li_idDireccion;

						lhmsdp_direcciones     = new HashMap<String, DireccionPredio>();
						li_idDireccion         = 1;
						lrc_data               = new RegistroCalificacion();

						if(CollectionUtils.isValidCollection(lcddp_direccionesPredio))
							li_idDireccion = lcddp_direccionesPredio.size() + 1;

						for(DireccionDelPredio lddp_iterador : lcddp_direcciones)
						{
							if(lddp_iterador != null)
							{
								DireccionPredioAcc ldpa_iterador;

								ldpa_iterador = lddp_iterador.getDireccionPredio();

								if(ldpa_iterador != null)
								{
									DireccionPredio ldp_direccion;

									ldp_direccion = new DireccionPredio();

									ldp_direccion.setComplementoDireccion(ldpa_iterador.getComplementoDireccion());
									ldp_direccion.setIdTipoEjePrincipal(ldpa_iterador.getIdTipoEjePrincipal());
									ldp_direccion.setIdTipoEjeSecundario(ldpa_iterador.getIdTipoEjeSecundario());
									ldp_direccion.setDatoEjePrincipal(ldpa_iterador.getDatoEjePrincipal());
									ldp_direccion.setDatoEjeSecundario(ldpa_iterador.getDatoEjeSecundario());
									ldp_direccion.setDireccion(ldpa_iterador.getDireccion());
									ldp_direccion.setIdDireccion(StringUtils.getString(li_idDireccion));

									lhmsdp_direcciones.put(
									    ll_idMatricula + IdentificadoresCommon.SIMBOLO_GUION + li_idDireccion,
									    ldp_direccion
									);

									li_idDireccion++;
								}
							}
						}

						lrc_data.setDireccionesSeleccionadas(lhmsdp_direcciones);
						lrc_data.setIdTurno(getIdTurno());
						lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lrc_data.setIdCirculo(ls_idCirculo);
						lrc_data.setIdMatricula(ll_idMatricula);
						lrc_data.setIdUsuario(ls_userId);
						lrc_data.setIpAddress(ls_remoteIp);
					}
				}

				lapr_pr.setIdUsuarioModificacion(ls_userId);
				lapr_pr.setIpModificacion(ls_remoteIp);
				lapr_pr.setRevision(
				    BooleanUtils.getBooleanValue(lcs_tmp.get(ls_idPredioRegistro)) ? EstadoCommon.S : EstadoCommon.N
				);
				lapr_pr.setIdPredioRegistro(ls_idPredioRegistro);

				irr_calificacionRemote.actualizarRevision(
				    lapr_pr, lrc_data, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				lbdm_bean.setMatriculasInformacion(locap_tmp);
				lbdm_bean.setRevisionMatriculas(lcs_tmp);
			}
		}

		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_matriculaRevisada correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String accionVolver(boolean ab_matriculaRevisada)
	{
		boolean lb_ejecucionCorrecciones;

		lb_ejecucionCorrecciones = isEjecucionCorrecciones();

		try
		{
			Anotacion                                                    la_anotacion;
			boolean                                                      lb_correccionAnotacion;
			BeanRegistroCalificacion                                     lbrc_bean;
			BeanEjecucionCorrecciones                                    lbec_bean;
			Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lap_tmp;
			FacesContext                                                 lfc_context;
			RegistroCalificacion                                         lrc_registro;

			lb_correccionAnotacion     = isCorreccionAnotacion();
			lfc_context                = FacesUtils.getFacesContext();
			lrc_registro               = new RegistroCalificacion();
			lap_tmp                    = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio>();
			idb_dataModel              = new Dashboard();
			ill_predioDocumento        = null;

			if(lb_correccionAnotacion)
				la_anotacion = getAnotacion();
			else
				la_anotacion = null;

			setAccPredioRegistro(null);
			setAnotacion(null);
			setAreaPredio(null);
			setCabidaLinderos(null);
			setDatosBasicos(null);
			setDireccionesPredio(null);
			setMostrarAtrasCrearGrabar(false);
			setOcultarSiguienteCrearGrabar(false);
			setProcesoTerminadoCrearGrabar(false);
			setPanels(null);
			setDetalleAnotacion(null);
			setPredioDetalle(null);
			setObservaciones(null);

			lbrc_bean     = (BeanRegistroCalificacion)lfc_context.getApplication()
					                                                 .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
					);
			lbec_bean     = (BeanEjecucionCorrecciones)lfc_context.getApplication()
					                                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_EJECUCION_CORRECCIONES, BeanEjecucionCorrecciones.class
					);

			lrc_registro.setTurno(getIdTurno());

			if(StringUtils.isValidString(getIdTurnoHistoria()))
				lrc_registro.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));

			lrc_registro.setDevolucion(isDevolucion());

			lrc_registro = irr_calificacionRemote.findMatriculasInformacion(lrc_registro);

			if(lrc_registro != null)
			{
				Map<String, Boolean> lcs_tmp;

				lcs_tmp     = getRevisionMatriculas();
				lap_tmp     = lrc_registro.getMatriculasInformacion();

				if(CollectionUtils.isValidCollection(lap_tmp))
				{
					String ls_idPredioRegistro;

					ls_idPredioRegistro = getIdPredioRegistro();

					if(CollectionUtils.isValidCollection(lcs_tmp))
					{
						for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio loap_ap : lap_tmp)
						{
							if(loap_ap != null)
							{
								if(ab_matriculaRevisada)
									lcs_tmp.put(ls_idPredioRegistro, new Boolean(ab_matriculaRevisada));

								loap_ap.setRevisado(
								    BooleanUtils.getBooleanValue(lcs_tmp.get(loap_ap.getIdPredioRegistro()))
								);
							}
						}
					}
					else
					{
						lcs_tmp.put(ls_idPredioRegistro, new Boolean(ab_matriculaRevisada));

						for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio loap_ap : lap_tmp)
						{
							if(loap_ap != null)
								loap_ap.setRevisado(
								    BooleanUtils.getBooleanValue(lcs_tmp.get(loap_ap.getIdPredioRegistro()))
								);
						}
					}

					AccPredioRegistro lapr_pr;

					lapr_pr = new AccPredioRegistro();

					lapr_pr.setIdUsuarioModificacion(getUserId());
					lapr_pr.setIpModificacion(getRemoteIpAddress());
					lapr_pr.setRevision(
					    BooleanUtils.getBooleanValue(lcs_tmp.get(ls_idPredioRegistro)) ? EstadoCommon.S : EstadoCommon.N
					);
					lapr_pr.setIdPredioRegistro(ls_idPredioRegistro);

					if(ab_matriculaRevisada)
						irr_calificacionRemote.actualizarRevision(
						    lapr_pr, null, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lb_ejecucionCorrecciones)
					{
						lbec_bean.salvarCausalesCorreccion();

						if(lb_correccionAnotacion)
						{
							Collection<Anotacion> lca_anotaciones;

							lca_anotaciones = lbec_bean.getAnotacionesAgregadas();

							if(CollectionUtils.isValidCollection(lca_anotaciones))
							{
								Iterator<Anotacion> lia_iterator;

								lia_iterator = lca_anotaciones.iterator();

								if(lia_iterator != null)
								{
									boolean lb_continuar;
									long    ll_idAnotacion;

									lb_continuar       = true;
									ll_idAnotacion     = la_anotacion.getIdAnotacion();

									while(lia_iterator.hasNext() && lb_continuar)
									{
										Anotacion la_iterador;

										la_iterador = lia_iterator.next();

										if((la_iterador != null) && (la_iterador.getIdAnotacion() == ll_idAnotacion))
										{
											la_anotacion     = la_iterador;
											lb_continuar     = false;
										}
									}
								}
							}

							lbec_bean.consultarAnotacion(la_anotacion);
							lbec_bean.actualizarFormulario();
							lbec_bean.setTabActual(2);
						}
					}

					lbrc_bean.setMatriculasInformacion(lap_tmp);
					lbrc_bean.setRevisionMatriculas(lcs_tmp);
				}

				setTabActual(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return lb_ejecucionCorrecciones ? NavegacionCommon.EJECUCION_CORRECCIONES : NavegacionCommon.REGISTRO_CALIFICACION;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolverView()
	{
		setAccPredioRegistro(null);
		setAreaPredioView(null);
		setCabidaLinderosView(null);
		setDireccionesPredioView(null);
		setDireccionesTemporalesView(null);
		setEditorTablaDireccionesView(false);

		{
			BeanDetalleDigitadorMasivo                                   lbdm_bean;
			Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lap_tmp;
			FacesContext                                                 lfc_context;
			Map<String, Boolean>                                         lmsb_tmp;

			lfc_context     = FacesUtils.getFacesContext();
			lbdm_bean       = (BeanDetalleDigitadorMasivo)lfc_context.getApplication()
					                                                     .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_DIGITADOR_MASIVO, BeanDetalleDigitadorMasivo.class
					);
			lmsb_tmp        = getRevisionMatriculas();

			lbdm_bean.infoMatriculasDigitador(getIdTurno());

			lap_tmp = lbdm_bean.getMatriculasInformacion();

			if(CollectionUtils.isValidCollection(lap_tmp))
			{
				if(CollectionUtils.isValidCollection(lmsb_tmp))
				{
					for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio loap_ap : lap_tmp)
					{
						if(loap_ap != null)
							loap_ap.setRevisado(
							    BooleanUtils.getBooleanValue(lmsb_tmp.get(loap_ap.getIdPredioRegistro()))
							);
					}
				}

				lbdm_bean.setMatriculasInformacion(lap_tmp);
				lbdm_bean.setRevisionMatriculas(lmsb_tmp);
			}
		}

		return NavegacionCommon.DETALLE_DIGITADOR_MASIVO;
	}

	/**
	 * Método encargado de actualizar la dirección del predio.
	 *
	 * @param apd_direccion Objeto que contiene la información de la dirección.
	 * @return Objeto que contiene la información de la dirección.
	 * @throws B2BException
	 */
	public DireccionPredioAcc actualizarDireccion(DireccionPredioAcc apd_direccion)
	    throws B2BException
	{
		if(apd_direccion != null)
			apd_direccion.setDireccion(construirDireccionCompleta(apd_direccion));

		return apd_direccion;
	}

	/**
	 * Agregar anotacion detalle.
	 */
	public void agregarAnotacionDetalle()
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;
			Documento       ld_documento;
			lap_anotacionPredio     = getAnotacionPredioDetalle();
			ld_documento            = getDocumento();

			if(lap_anotacionPredio != null)
			{
				{
					Date ld_fechaRadicacion;
					ld_fechaRadicacion = lap_anotacionPredio.getFechaRadicacion();

					if(ld_fechaRadicacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
				}

				{
					String ls_radicacion;
					ls_radicacion = lap_anotacionPredio.getRadicacion();

					if(!StringUtils.isValidString(ls_radicacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_RADICACION);
				}

				{
					String ls_estadoAnotacion;
					ls_estadoAnotacion = lap_anotacionPredio.getIdEstadoAnotacion();

					if(!StringUtils.isValidString(ls_estadoAnotacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_ANOTACION);
				}
			}

			if(ld_documento != null)
			{
				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdTipoDocumento();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getNumero();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdTipoOficina();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_OFICINA);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getTipoEntidad();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ENTIDAD);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdPais();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdDepartamento();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdMunicipio();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp = ld_documento.getIdOficinaOrigen();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN_VALIDO);
				}
			}

			if(lap_anotacionPredio != null)
			{
				{
					String ls_tmp;
					ls_tmp = lap_anotacionPredio.getIdNaturalezaJuridica();

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);
				}

				{
					BigDecimal lbd_tmp;
					Double     ld_tmp;

					lbd_tmp     = lap_anotacionPredio.getValor();
					ld_tmp      = (lbd_tmp != null) ? NumericUtils.getDoubleWrapper(lbd_tmp) : null;

					if(!NumericUtils.isValidDouble(ld_tmp))
						throw new B2BException(ErrorKeys.ERROR_SIN_VALOR);
				}
			}

			{
				RegistroCalificacion lorc_rc;
				RegistroCalificacion liorc_tmp;

				lorc_rc       = new RegistroCalificacion();
				liorc_tmp     = new RegistroCalificacion();

				lorc_rc.setDataAnotacionPredio(lap_anotacionPredio);
				lorc_rc.setDataDocumento(ld_documento);
				lorc_rc.setDataPersona(getPersona());
				lorc_rc.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));
				lorc_rc.setUserId(getUserId());
				lorc_rc.setIpAddress(getRemoteIpAddress());
				lorc_rc.setIntervinientes(getIntervinientesAgregados());

				irr_calificacionRemote.modificarAnotacion(
				    lorc_rc, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				liorc_tmp.setIdCirculo(getAccPredioRegistro().getIdCirculo());
				liorc_tmp.setIdMatricula(getAccPredioRegistro().getIdMatricula());
				liorc_tmp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				liorc_tmp.setIdUsuario(getUserId());

				setDetalleAnotacion(irr_calificacionRemote.findDetalleMatriculas(liorc_tmp));

				consultaDetalleMatricula();

				PrimeFaces.current().executeScript("PF('detalleAnotacion').hide();");

				Wizard wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot().findComponent(is_wizardId);

				wizard.setStep(is_tabAnotacionesWizardId);
				PrimeFaces.current().ajax().update(is_wizardId);

				setAnotacionPredioDetalle(null);
				setDocumento(null);
				setAnotacionCancelacion(null);
				setIdMatricula(null);
				setNaturalezaJuridicaSeleccionada(null);
				setTipoDocInterviniente(null);
				setDocumentoInterviniente(null);
				setIdPersonaSeleccion(null);
				setNumeroAnotacion(null);
				setAnotacionAgregada(true);
				setIntervinientesAgregados(null);
				setPersona(null);
				setAnotacionPredioCiudadano(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			PrimeFaces.current().executeScript("PF('detalleAnotacion').show();");

			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void agregarArea()
	{
		try
		{
			AccAreaUI         laaui_dataArea;
			DetalleAreaPredio ldap_detalleArea;

			laaui_dataArea       = getAreaUI();
			ldap_detalleArea     = getDetalleAreaTerreno();

			if((laaui_dataArea != null) && (ldap_detalleArea != null))
			{
				Double       ld_area;
				boolean      lb_focus;
				BigInteger   lbi_count;
				FacesContext lfc_context;

				ld_area         = ldap_detalleArea.getArea();
				lb_focus        = false;
				lbi_count       = laaui_dataArea.getIdDetalleAreaPredio();
				lfc_context     = FacesContext.getCurrentInstance();
				lb_focus        = validateStyles(
					    ":fDetalleRegistroCalificacion:idITareaTerreno", lfc_context, StringUtils.getString(ld_area),
					    lb_focus
					);

				if(NumericUtils.isValidDouble(ld_area))
				{
					String ls_medidaArea;

					ls_medidaArea     = ldap_detalleArea.getIdUnidadMedida();
					lb_focus          = validateStyles(
						    ":fDetalleRegistroCalificacion:idSOMunidadMedidaTerreno", lfc_context, ls_medidaArea,
						    lb_focus
						);

					if(StringUtils.isValidString(ls_medidaArea))
					{
						Collection<DetalleAreaPredio> lcdap_areas;

						lcdap_areas = laaui_dataArea.getAreasTerreno();

						if(CollectionUtils.isValidCollection(lcdap_areas))
						{
							Iterator<DetalleAreaPredio> lidap_iterator;

							lidap_iterator = lcdap_areas.iterator();

							while(lidap_iterator.hasNext())
							{
								DetalleAreaPredio ldap_areaTerreno;

								ldap_areaTerreno = lidap_iterator.next();

								if(ldap_areaTerreno != null)
								{
									String ls_idDetalleAreaPredio;

									ls_idDetalleAreaPredio = ldap_areaTerreno.getIdDetalleAreaPredio();

									if(StringUtils.isValidString(ls_idDetalleAreaPredio))
									{
										BigInteger lbi_idDetalleAreaPredio;
										String     ls_medidaAreaIterador;

										ls_medidaAreaIterador       = ldap_areaTerreno.getIdUnidadMedida();
										lbi_idDetalleAreaPredio     = NumericUtils.getBigInteger(
											    ls_idDetalleAreaPredio
											);

										lbi_count = (lbi_idDetalleAreaPredio.compareTo(lbi_count) > 0)
											? lbi_idDetalleAreaPredio : lbi_count;

										if(StringUtils.isValidString(ls_medidaAreaIterador))
										{
											if(ls_medidaAreaIterador.equalsIgnoreCase(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;
												Object[]   aoa_messageArgs;
												String     ls_medidaAreaNombre;

												lma_medidaArea = ipr_parameterRemote.findMedidaAreaById(
													    ls_medidaAreaIterador
													);

												if(lma_medidaArea == null)
													throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);

												ls_medidaAreaNombre     = lma_medidaArea.getDescripcion();
												aoa_messageArgs         = new String[1];
												aoa_messageArgs[0]      = ls_medidaAreaNombre;
												lb_focus                = validateStyles(
													    ":fDetalleRegistroCalificacion:idSOMunidadMedidaTerreno",
													    lfc_context, "", lb_focus
													);

												throw new B2BException(
												    ErrorKeys.ERROR_UNIDAD_MEDIDA_DUPLICADO, aoa_messageArgs
												);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
									}
								}
							}
						}

						lbi_count = lbi_count.add(BigInteger.ONE);

						ldap_detalleArea.setIdTipoArea(TipoAreaCommon.TERRENO);
						ldap_detalleArea.setIdDetalleAreaPredio(StringUtils.getString(lbi_count));
						lcdap_areas.add(ldap_detalleArea);
						laaui_dataArea.setIdDetalleAreaPredio(lbi_count);
						laaui_dataArea.setAreasTerreno(lcdap_areas);

						setDetalleAreaTerreno(null);
						setAreaUI(laaui_dataArea);
						actualizarAreaTerreno();
					}
					else
						throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Método encargado de validar el formulario de direccion del predio a ser agregada.
	 */
	public void agregarDireccionApertura()
	{
		try
		{
			BeanDireccion lbd_beanDireccion;
			Direccion     ld_direccion;

			lbd_beanDireccion     = getBeanDireccion();
			ld_direccion          = lbd_beanDireccion.getDireccionPredio();

			if(ld_direccion != null)
			{
				AccPredioRegistro lapr_predio;
				Long              ll_idMatricula;
				String            ls_idCirculo;

				lapr_predio        = getAccPredioRegistro();
				ll_idMatricula     = null;
				ls_idCirculo       = null;

				if(lapr_predio != null)
				{
					ls_idCirculo       = lapr_predio.getIdCirculo();
					ll_idMatricula     = lapr_predio.getIdMatricula();
				}

				if(!NumericUtils.isValidLong(ll_idMatricula))
					ll_idMatricula = getIdMatricula();

				if(!StringUtils.isValidString(ls_idCirculo))
					ls_idCirculo = getIdCirculo();

				ld_direccion.setIdCirculo(ls_idCirculo);
				ld_direccion.setIdMatricula(ll_idMatricula);
				ld_direccion.setIpCreacion(getLocalIpAddress());
				ld_direccion.setIpModificacion(getLocalIpAddress());
			}

			lbd_beanDireccion.setDireccionPredio(ld_direccion);
			lbd_beanDireccion.agregarDireccionApertura();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarDireccionApertura", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Método encargado de validar el formulario de direccion del predio a ser agregada.
	 */
	public void agregarDireccionPredio()
	{
		try
		{
			BeanDireccion lbd_beanDireccion;
			Direccion     ld_direccion;
			Long          ll_idMatricula;
			String        ls_idCirculo;

			lbd_beanDireccion     = getBeanDireccion();
			ld_direccion          = lbd_beanDireccion.getDireccionPredio();
			ll_idMatricula        = null;
			ls_idCirculo          = null;

			if(ld_direccion != null)
			{
				AccPredioRegistro lapr_data;

				lapr_data = getAccPredioRegistroView();

				if(lapr_data != null)
				{
					ls_idCirculo       = lapr_data.getIdCirculo();
					ll_idMatricula     = lapr_data.getIdMatricula();
				}

				if(!NumericUtils.isValidLong(ll_idMatricula))
					ll_idMatricula = getIdMatricula();

				if(!StringUtils.isValidString(ls_idCirculo))
					ls_idCirculo = getIdCirculo();

				ld_direccion.setIdCirculo(ls_idCirculo);
				ld_direccion.setIdMatricula(ll_idMatricula);
				ld_direccion.setIpCreacion(getLocalIpAddress());
				ld_direccion.setIpModificacion(getLocalIpAddress());
			}

			{
				RegistroCalificacion lrc_registroCalificacion;

				lrc_registroCalificacion = new RegistroCalificacion();

				ld_direccion.setDireccion(construirDireccionCompleta(ld_direccion));
				lrc_registroCalificacion.setDireccionGuardar(new DireccionPredio(ld_direccion, true));
				lrc_registroCalificacion.setIdCirculo(ls_idCirculo);
				lrc_registroCalificacion.setIdMatricula(ll_idMatricula);
				lrc_registroCalificacion.setIdTurno(getIdTurno());
				lrc_registroCalificacion.setCalificacion(true);
				lrc_registroCalificacion.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				{
					DireccionPredioAcc ldpa_direccion;

					ldpa_direccion = irr_calificacionRemote.salvarDireccionVenta(
						    lrc_registroCalificacion, getUserId(), getRemoteIpAddress()
						);

					if(ldpa_direccion != null)
					{
						Collection<DireccionDelPredio> lcddp_direcciones;

						lcddp_direcciones = getDireccionesPredioView();

						if(!CollectionUtils.isValidCollection(lcddp_direcciones))
							lcddp_direcciones = new ArrayList<DireccionDelPredio>(1);

						if(ld_direccion != null)
						{
							ldpa_direccion.setIdTipoPredio(ld_direccion.getIdTipoPredio());
							ldpa_direccion.setComplementoDireccion(ld_direccion.getComplementoDireccion());
							ldpa_direccion.setIdComplementoDireccion(ld_direccion.getIdComplementoDireccion());
						}

						lcddp_direcciones.add(new DireccionDelPredio(ldpa_direccion, true));

						setDireccionesPredioView(lcddp_direcciones);

						ld_direccion.setIdDireccionPredio(ldpa_direccion.getIdDireccionPredio());

						lbd_beanDireccion.setDireccionPredio(ld_direccion);
						lbd_beanDireccion.agregarDireccionApertura();
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarDireccionPredio", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(is_idForm);
		}
	}

	/** {@inheritdoc} */
	public void agregarIntervinientes()
	{
		try
		{
			Anotacion                la_anotacion;
			Persona                  lp_parametros;
			AnotacionPredioCiudadano lapc_anotacionPredio;
			Anotacion                loap_tmp;
			String                   ls_mensaje;
			loap_tmp     = new Anotacion();

			la_anotacion             = new Anotacion();
			lp_parametros            = getPersona();
			lapc_anotacionPredio     = getAnotacionPredioCiudadano();

			if(lp_parametros != null)
			{
				String ls_tipoPersona;
				String ls_tipoDocumento;
				ls_tipoPersona       = lp_parametros.getIdTipoPersona();
				ls_tipoDocumento     = lp_parametros.getIdDocumentoTipo();

				if(!StringUtils.isValidString(ls_tipoPersona))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

				if(!StringUtils.isValidString(ls_tipoDocumento))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

				{
					String ls_documento;
					ls_documento = lp_parametros.getNumeroDocumento();

					if(!StringUtils.isValidString(ls_documento))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.N))
				{
					if(
					    !ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
						    && !ls_tipoDocumento.equalsIgnoreCase("-1")
					)
					{
						{
							String ls_nacionalidad;
							ls_nacionalidad = lp_parametros.getIdPais();

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							String ls_primerNombre;
							ls_primerNombre = lp_parametros.getPrimerNombre();

							if(!StringUtils.isValidString(ls_primerNombre))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
						}

						{
							String ls_primerApellido;
							ls_primerApellido = lp_parametros.getPrimerApellido();

							if(!StringUtils.isValidString(ls_primerApellido))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
						}

						{
							String ls_genero;
							ls_genero = lp_parametros.getIdNaturalGenero();

							if(!StringUtils.isValidString(ls_genero))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
						}

						{
							if(lapc_anotacionPredio != null)
							{
								String ls_rol;
								ls_rol = lapc_anotacionPredio.getRolPersona();

								if(!StringUtils.isValidString(ls_rol))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
				}
				else if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
				{
					if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					{
						{
							String ls_nacionalidad;
							ls_nacionalidad = lp_parametros.getIdPais();

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							String ls_razonSocial;
							ls_razonSocial = lp_parametros.getRazonSocial();

							if(!StringUtils.isValidString(ls_razonSocial))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
				}

				if(lapc_anotacionPredio != null)
				{
					{
						String ls_rol;
						ls_rol = lapc_anotacionPredio.getRolPersona();

						if(!StringUtils.isValidString(ls_rol))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
					}
				}

				{
					Collection<Anotacion> lca_intervinientes;
					Collection<Anotacion> lca_tmp;
					boolean               lb_b;
					boolean               lb_exist;

					lca_intervinientes     = getIntervinientesAgregados();
					lca_tmp                = new ArrayList<Anotacion>();
					lb_b                   = false;
					lb_exist               = true;

					if(CollectionUtils.isValidCollection(lca_intervinientes))
					{
						for(Anotacion loap_apc : lca_intervinientes)
						{
							if(!lb_b)
							{
								if(!lp_parametros.getIdPersona().equalsIgnoreCase(loap_apc.getPersona().getIdPersona()))
									lb_b = false;
								else
									lb_b = true;

								if(lb_b)
								{
									if(lapc_anotacionPredio != null)
									{
										if(
										    !StringUtils.getStringNotNull(lapc_anotacionPredio.getRolPersona())
											                .toUpperCase()
											                .equalsIgnoreCase(
											        StringUtils.getStringNotNull(
											            loap_apc.getAnotacionPredioCiudadano().getRolPersona()
											        ).toUpperCase()
											    )
										)
										{
											lb_exist     = false;
											lb_b         = false;
										}
									}
								}
							}
						}

						if(!lb_b)
							lb_exist = lb_b;

						if(!lb_exist)
						{
							lb_b = true;

							for(Anotacion loap_apc : lca_intervinientes)
							{
								lca_tmp.add(loap_apc);

								if(lb_b)
								{
									la_anotacion.setPersona(lp_parametros);
									la_anotacion.setAnotacionPredioCiudadano(lapc_anotacionPredio);
									lb_b = false;
									lca_tmp.add(la_anotacion);
								}

								loap_apc = new Anotacion();
							}
						}
					}

					else
					{
						loap_tmp.setPersona(lp_parametros);
						loap_tmp.setAnotacionPredioCiudadano(lapc_anotacionPredio);

						lca_tmp.add(loap_tmp);
						lca_intervinientes = lca_tmp;
					}

					if(lca_tmp.size() > 0)
					{
						setIntervinientesAgregados(lca_tmp);
						ls_mensaje = getMessages().getString(MessagesKeys.INTERVINIENTE_AGREGADO);
					}
					else
						ls_mensaje = getMessages().getString(MessagesKeys.INTERVINIENTE_CON_MISMO_ROL_Y_DOCUMENTO);

					setPersona(null);
					setSolicitudInterviniente(null);
					setAnotacionPredioCiudadano(null);
					setTipoDocInterviniente(null);
					setDocumentoInterviniente(null);
					setListadoIntervinientes(null);
					setDeshabilitarDatosInterviniente(false);
					setMostrarListadoPersonas(false);

					addMessage("I", ls_mensaje);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void agregarMatriculaBase()
	{
		try
		{
			MatriculaBase lmb_matricula;

			lmb_matricula = getDatosBasicos().getMatriculaBase();

			if(lmb_matricula != null)
			{
				String ls_idCirculo;
				long   ll_idMatricula;

				ls_idCirculo       = lmb_matricula.getIdCirculo();
				ll_idMatricula     = NumericUtils.isValidLong(lmb_matricula.getIdMatricula(), 1)
					? NumericUtils.getLong(lmb_matricula.getIdMatricula()) : (-1L);

				if(StringUtils.isValidString(ls_idCirculo))
				{
					if(ll_idMatricula > 0)
					{
						CirculoRegistral lcr_circulo;

						lcr_circulo = new CirculoRegistral();
						lcr_circulo.setIdCirculo(ls_idCirculo);

						lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

						if(lcr_circulo != null)
						{
							PredioRegistro lpr_predio;

							lpr_predio = new PredioRegistro();

							lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
							lpr_predio.setIdMatricula(ll_idMatricula);

							lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

							if(lpr_predio != null)
							{
								String ls_predioDefinitivo;

								ls_predioDefinitivo = lpr_predio.getPredioDefinitivo();

								if(
								    StringUtils.isValidString(ls_predioDefinitivo)
									    && ls_predioDefinitivo.equalsIgnoreCase(EstadoCommon.D)
								)
								{
									DireccionPredio ldp_direccionPredio;

									ldp_direccionPredio = new DireccionPredio();

									ldp_direccionPredio.setIdCirculo(lpr_predio.getIdCirculo());
									ldp_direccionPredio.setIdMatricula(
									    NumericUtils.getLongWrapper(lpr_predio.getIdMatricula())
									);

									ldp_direccionPredio = irr_registroRemote.findDireccionPredioById(
										    ldp_direccionPredio, getUserId()
										);

									if(ldp_direccionPredio != null)
									{
										Collection<DireccionPredio> lcdp_cdp;

										lcdp_cdp = lmb_matricula.getDireccionPredio();

										if(CollectionUtils.isValidCollection(lcdp_cdp))
										{
											boolean lb_entradaNueva;

											lb_entradaNueva = true;

											for(DireccionPredio ldp_dpActual : lcdp_cdp)
											{
												if(ldp_dpActual != null)
												{
													long   ls_matriculaCargada;
													String ls_circuloCargado;

													ls_matriculaCargada     = NumericUtils.getLong(
														    ldp_dpActual.getIdMatricula()
														);
													ls_circuloCargado       = ldp_dpActual.getIdCirculo();

													if(
													    (ls_circuloCargado.equalsIgnoreCase(ls_idCirculo))
														    && (ls_matriculaCargada == ll_idMatricula)
													)
														lb_entradaNueva = false;
												}
											}

											if(lb_entradaNueva)
												lcdp_cdp.add(ldp_direccionPredio);
										}
										else
										{
											lcdp_cdp = new ArrayList<DireccionPredio>();

											lcdp_cdp.add(ldp_direccionPredio);
										}

										lmb_matricula.setDireccionPredio(lcdp_cdp);
									}
									else
									{
										Collection<DireccionPredio> lcdp_cdp;

										ldp_direccionPredio     = new DireccionPredio();
										lcdp_cdp                = lmb_matricula.getDireccionPredio();

										ldp_direccionPredio.setIdCirculo(ls_idCirculo);
										ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

										if(CollectionUtils.isValidCollection(lcdp_cdp))
										{
											boolean lb_entradaNueva;

											lb_entradaNueva = true;

											for(DireccionPredio ldp_dpActual : lcdp_cdp)
											{
												if(ldp_dpActual != null)
												{
													long   ls_matriculaCargada;
													String ls_circuloCargado;

													ls_matriculaCargada     = NumericUtils.getLong(
														    ldp_dpActual.getIdMatricula()
														);
													ls_circuloCargado       = ldp_dpActual.getIdCirculo();

													if(
													    (ls_circuloCargado.equalsIgnoreCase(ls_idCirculo))
														    && (ls_matriculaCargada == ll_idMatricula)
													)
														lb_entradaNueva = false;
												}
											}

											if(lb_entradaNueva)
												lcdp_cdp.add(ldp_direccionPredio);
										}
										else
										{
											lcdp_cdp = new ArrayList<DireccionPredio>();

											lcdp_cdp.add(ldp_direccionPredio);
										}

										lmb_matricula.setDireccionPredio(lcdp_cdp);
									}
								}
								else
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;
									throw new B2BException(ErrorKeys.ERROR_PREDIO_DEFINITIVO, aoa_messageArgs);
								}
							}
							else
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;
								throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, aoa_messageArgs);
							}
						}
						else
						{
							Object[] aoa_messageArgs = new String[1];
							aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;
							throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
				}
				else
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

				lmb_matricula.setIdMatricula(null);
			}
			else
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void cambiarCirculoRegistral()
	{
		try
		{
			filtrarPorCirculo();

			UbicacionZonaRegistral luzr_ubicacion;
			CirculoRegistral       lcr_circuloRegistral;

			luzr_ubicacion           = getDatosBasicos().getUbicacion();
			lcr_circuloRegistral     = luzr_ubicacion.getCirculoRegistral();

			if(lcr_circuloRegistral != null)
			{
				String ls_idCirculo;

				ls_idCirculo = lcr_circuloRegistral.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					lcr_circuloRegistral = irr_registroRemote.findCirculoRegistralById(
						    lcr_circuloRegistral, getUserId()
						);

					if(lcr_circuloRegistral != null)
					{
						MatriculaBase lmb_matricula;

						lmb_matricula = getDatosBasicos().getMatriculaBase();

						luzr_ubicacion.setCirculoRegistral(lcr_circuloRegistral);
						luzr_ubicacion.setIdPais(getIdPais());
						lmb_matricula.setIdCirculo(lcr_circuloRegistral.getIdCirculo());
					}
					else
						throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);
			}
			else
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void cambiarDepartamento()
	{
		findMunicipio();
		findVereda();
	}

	/**
	 * Cambiar departamento predio.
	 */
	public void cambiarDepartamentoPredio()
	{
		findMunicipioPredio();
	}

	/** {@inheritdoc} */
	public void cambiarMunicipio()
	{
		findVereda();
	}

	/** {@inheritdoc} */
	public void cambiarPaisPredio()
	{
		DireccionDelPredio lddp_direccion;
		lddp_direccion = getDireccionPredio();

		if(lddp_direccion != null)
		{
			String ls_pais;
			ls_pais = lddp_direccion.getDatosAntiguoSistema().getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterResidencia(true);
				lddp_direccion.getDatosAntiguoSistema().setIdDepartamento(null);
			}
			else
				setPaisInterResidencia(false);
		}

		findDepartamentosPredio();
		findMunicipioPredio();
	}

	/**
	 * Cargar datos personales.
	 */
	public void cargarDatosPersonales()
	{
		String ls_idPersonaSeleccion;
		ls_idPersonaSeleccion = getIdPersonaSeleccion();

		if(StringUtils.isValidString(ls_idPersonaSeleccion))
		{
			Collection<Persona> lcp_personas;
			lcp_personas = getListadoIntervinientes();

			if(CollectionUtils.isValidCollection(lcp_personas))
			{
				for(Persona lp_iterador : lcp_personas)
				{
					if(lp_iterador != null)
					{
						String ls_idPersona;
						ls_idPersona = lp_iterador.getIdPersona();

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && ls_idPersonaSeleccion.equalsIgnoreCase(ls_idPersona)
						)
						{
							setPersona(lp_iterador);
							setDeshabilitarDatosInterviniente(true);
						}
					}
				}
			}
		}
		else
		{
			setPersona(null);
			setDeshabilitarDatosInterviniente(false);
		}
	}

	/** {@inheritdoc} */
	public void cargarDepartamentoDocumento()
	{
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public void cargarListasDesplegablesDocumento()
	{
		findTipoEntidadDocumento();
		findPaises();
		findDepartamentosDocumento();
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Cargar matricula.
	 *
	 * @param ab_desdeInterviniente correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarMatricula(boolean ab_desdeInterviniente)
	{
		try
		{
			AccPredioRegistro       lapr_apr;
			DigitadorAntiguoSistema ldas_das;

			lapr_apr = new AccPredioRegistro();

			if(StringUtils.isValidString(getIdPredioRegistro()))
			{
				lapr_apr.setIdPredioRegistro(getIdPredioRegistro());

				if(StringUtils.isValidString(getIdTurnoHistoria()))
					lapr_apr.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));

				lapr_apr.setDevolucion(isDevolucion());
				ldas_das = idas_digitadorAntiguoSistemaRemote.findDigitadorAntiguoSistema(
					    lapr_apr, EtapaCommon.ID_ETAPA_CALIFICACION, getUserId()
					);

				if(ldas_das != null)
				{
					setAccPredioRegistro(ldas_das.getAccPredioRegistro());
					setObservaciones(ldas_das.getMotivoNoTramite());

					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = ldas_das.getDatosbasicos();

						if(ldb_datosBasicos != null)
						{
							AccPredioRegistro lapr_accPredioRegistro;
							PredioRegistro    lpr_predioRegistro;

							lapr_accPredioRegistro     = ldb_datosBasicos.getAccPredioRegistro();
							lpr_predioRegistro         = ldb_datosBasicos.getPredioRegistro();

							if((lpr_predioRegistro != null) && (lapr_accPredioRegistro != null))
							{
								String ls_idTipoPredio;
								String ls_idTipoUsoSuelo;

								ls_idTipoPredio       = lpr_predioRegistro.getIdTipoPredio();
								ls_idTipoUsoSuelo     = lpr_predioRegistro.getIdTipoUsoSuelo();

								if(!StringUtils.isValidString(ls_idTipoPredio))
									lpr_predioRegistro.setIdTipoPredio(lapr_accPredioRegistro.getIdTipoPredio());

								if(!StringUtils.isValidString(ls_idTipoUsoSuelo))
									lpr_predioRegistro.setIdTipoUsoSuelo(lapr_accPredioRegistro.getIdTipoUsoSuelo());

								ldb_datosBasicos.setPredioRegistro(lpr_predioRegistro);
							}

							setDatosBasicos(ldb_datosBasicos);
						}
					}

					{
						CabidaLinderos lcl_cl;

						lcl_cl = getCabidaLinderos();

						{
							AccComplementacionPredio lacp_cp;

							lacp_cp = ldas_das.getAccComplementacionPredio();

							if(lacp_cp != null)
							{
								Long ll_complementacionOriginal;

								ll_complementacionOriginal = lacp_cp.getIdComplementacionOriginal();

								if(NumericUtils.isValidLong(ll_complementacionOriginal))
								{
									lcl_cl.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
									lcl_cl.getComplementacion().setComplementacion(lacp_cp.getComplementacion());
									lcl_cl.getComplementacionPredio()
										      .setIdComplementacion(
										    String.valueOf(
										        NumericUtils.getLong(lacp_cp.getIdComplementacionOriginal())
										    )
										);
								}
								else
									lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
							}
							else
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
						}

						{
							AccLinderoPredio lalp_lp;

							lalp_lp = ldas_das.getAccLinderoPredio();

							if(lalp_lp != null)
								lcl_cl.setLinderos(lalp_lp.getLindero());
							else
								setNuevoLindero(
								    (isValidarPropiedadHorizontal() || isProcesoLoteo()) || isProcesoReloteo()
									    || isDivisionMaterial()
								);
						}
					}

					setAreaPredio(ldas_das.getAreaPredio());
					setDireccionesPredio(ldas_das.getDireccionesDelPredio());
					setAnotacion(ldas_das.getAnotacion());
					setDetalleAnotacion(ldas_das.getAnotaciones());
					setDireccionesTemporales(getDireccionesPredio());

					if(!ab_desdeInterviniente)
					{
						setTerminarProceso(false);
						setMostrarAtrasCrearGrabar(false);
						setOcultarSiguienteCrearGrabar(false);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Cargar matricula view.
	 */
	public void cargarMatriculaView(boolean ab_observaciones)
	{
		try
		{
			AccPredioRegistro       lapr_apr;
			DigitadorAntiguoSistema ldas_das;

			limpiarLoteo();

			lapr_apr = new AccPredioRegistro();

			if(StringUtils.isValidString(getIdPredioRegistro()))
			{
				lapr_apr.setIdPredioRegistro(getIdPredioRegistro());
				lapr_apr.setProcesoDivisionMaterial(isDivisionMaterial());
				lapr_apr.setProcesoReloteo(isProcesoLoteo());
				lapr_apr.setProcesoReloteo(isProcesoReloteo());
				lapr_apr.setPropiedadHorizontal(isValidarPropiedadHorizontal());

				if(StringUtils.isValidString(getIdTurnoHistoria()))
					lapr_apr.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));

				ldas_das = idas_digitadorAntiguoSistemaRemote.findDigitadorAntiguoSistema(
					    lapr_apr, EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO, getUserId()
					);

				if(ldas_das != null)
				{
					setAccPredioRegistroView(ldas_das.getAccPredioRegistro());

					if(getAccPredioRegistroView() != null)
						setIdCirculo(getAccPredioRegistroView().getIdCirculo());

					{
						CabidaLinderos lcl_cl;

						lcl_cl = getCabidaLinderosView();

						{
							AccComplementacionPredio lacp_cp;

							lacp_cp = ldas_das.getAccComplementacionPredio();

							if(lacp_cp != null)
							{
								{
									ComplementacionCalificacion lcc_cc;

									lcc_cc = getComplementacionCalificacion();

									if(lcc_cc != null)
									{
										ComplementacionPredio lcp_cp;
										String                ls_tipoComplementacion;

										lcp_cp                     = new ComplementacionPredio(lacp_cp);
										ls_tipoComplementacion     = lacp_cp.getTipoComplementacion();

										lcc_cc.setTipoComplementacion(
										    StringUtils.isValidString(ls_tipoComplementacion)
										    ? (ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.C)
										    ? TipoComplementacionCommon.COPIAR
										    : (ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.A)
										    ? TipoComplementacionCommon.CONSTRUIR : TipoComplementacionCommon.NUEVA))
										    : TipoComplementacionCommon.NUEVA
										);
										lcc_cc.setComplementacionPredio(lcp_cp);
									}
								}

								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
								lcl_cl.getComplementacion().setComplementacion(lacp_cp.getComplementacion());
								lcl_cl.getComplementacionPredio()
									      .setIdComplementacion(
									    String.valueOf(NumericUtils.getLong(lacp_cp.getIdComplementacionOriginal()))
									);
								setComplementacionSinConstruir(false);
							}
							else
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
						}

						{
							AccLinderoPredio lalp_lp;
							String           ls_texto;

							lalp_lp      = ldas_das.getAccLinderoPredio();
							ls_texto     = StringUtils.getStringNotNull(ldas_das.getTextoLinderos());

							if(lalp_lp != null)
								lcl_cl.setLinderos(
								    ls_texto + IdentificadoresCommon.ESPACIO_VACIO + lalp_lp.getLindero()
								);
							else
							{
								if(
								    (isValidarPropiedadHorizontal() || isProcesoLoteo()) || isProcesoReloteo()
									    || isDivisionMaterial()
								)
								{
									setNuevoLindero(true);
									lcl_cl.setTipoLindero("nueva");
									lcl_cl.setLinderos(ls_texto);
								}
								else
									setNuevoLindero(false);
							}
						}
					}

					cargarDatosArea(false);
					setDireccionesPredioView(ldas_das.getDireccionesDelPredio());
					setDireccionesTemporalesView(getDireccionesPredioView());

					if(!ab_observaciones)
						setObservaciones(ldas_das.getMotivoNoTramite());

					limpiarComplementacion(true);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);

				{
					Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lcap_cap;
					lcap_cap = getMatriculasInformacion();

					if(CollectionUtils.isValidCollection(lcap_cap))
					{
						Map<String, Long> lmsl_rangos;
						int               li_contador;
						lmsl_rangos     = new HashMap<String, Long>();
						li_contador     = 0;

						for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio lap_tmp : lcap_cap)
						{
							if(lap_tmp != null)
							{
								if(li_contador == 0)
								{
									lmsl_rangos.put(
									    "RANGO_MENOR", NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
									);
									lmsl_rangos.put(
									    "RANGO_MAYOR", NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
									);
								}
								else
								{
									if(lap_tmp.getIdMatricula() < NumericUtils.getLong(lmsl_rangos.get("RANGO_MENOR")))
										lmsl_rangos.put(
										    "RANGO_MENOR", NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
										);

									if(lap_tmp.getIdMatricula() > NumericUtils.getLong(lmsl_rangos.get("RANGO_MAYOR")))
										lmsl_rangos.put(
										    "RANGO_MAYOR", NumericUtils.getLongWrapper(lap_tmp.getIdMatricula())
										);
								}

								li_contador++;
							}
						}

						setRangosDefinitivos(lmsl_rangos);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de List.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de List
	 */
	public List<String> cargarMatriculas(String as_s)
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lcap_cap;
		List<String>                                                 lls_results;

		lcap_cap        = getMatriculasInformacion();
		lls_results     = new ArrayList<String>();

		if(CollectionUtils.isValidCollection(lcap_cap))
		{
			Long   ll_rangoInicio;
			Long   ll_rangoFin;
			String ls_idMatricula;

			ls_idMatricula     = "";
			ll_rangoInicio     = getRangosDefinitivos().get("RANGO_MENOR");
			ll_rangoFin        = getRangosDefinitivos().get("RANGO_MAYOR");

			long ll_idMatriculaInicio;
			long ll_idMatriculaFin;

			if(NumericUtils.isValidLong(ll_rangoInicio, -1L))
				ll_idMatriculaInicio = NumericUtils.getLong(ll_rangoInicio);
			else
				ll_idMatriculaInicio = NumericUtils.DEFAULT_LONG_VALUE;

			ll_idMatriculaFin  = (ll_rangoFin != null) ? NumericUtils.getLong(ll_rangoFin)
				                                       : NumericUtils.DEFAULT_LONG_VALUE;
			ls_idMatricula     = NumericUtils.getLongWrapper(ll_idMatriculaInicio).toString();

			if(StringUtils.isValidString(ls_idMatricula))
			{
				for(int i = NumericUtils.getInt(ll_idMatriculaInicio); i <= NumericUtils.getInt(ll_idMatriculaFin);
					    i++
				)
				{
					String ls_matriculaActual;

					ls_matriculaActual = NumericUtils.getLongWrapper(i).toString();

					if(ls_matriculaActual.startsWith(as_s))
						lls_results.add(ls_matriculaActual);
				}
			}
		}

		return lls_results;
	}

	/** {@inheritdoc} */
	public void cargarOficinaOrigen()
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lcoo_oficina;
			lcoo_oficina = getOficinaOrigen();

			if(lcoo_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void cargarPaisDocumento()
	{
		findDepartamentosDocumento();
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Cargar rangos.
	 *
	 * @param as_rango correspondiente al valor del tipo de objeto String
	 */
	public void cargarRangos(String as_rango)
	{
		if(StringUtils.isValidString(as_rango))
		{
			String ls_tmp;

			if(as_rango.equalsIgnoreCase("LINDEROS"))
			{
				ls_tmp = getCopiarLinderos();

				setRangoLinderos(StringUtils.isValidString(ls_tmp) && ls_tmp.equalsIgnoreCase(EstadoCommon.S));
			}

			else if(as_rango.equalsIgnoreCase("COMPLEMENTACION"))
			{
				ls_tmp = getCopiarComplementacion();

				setRangoComplementacion(StringUtils.isValidString(ls_tmp) && ls_tmp.equalsIgnoreCase(EstadoCommon.S));
			}
			else if(as_rango.equalsIgnoreCase("DIRECCION"))
			{
				ls_tmp = getCopiarDireccion();

				setRangoDireccion(StringUtils.isValidString(ls_tmp) && ls_tmp.equalsIgnoreCase(EstadoCommon.S));
			}

			setDesde(null);
			setHasta(null);
		}
	}

	/** {@inheritdoc} */
	public String changePosition(List<String> as_idPanels, String as_IdMove, int ai_position)
	{
		String ls_changePosition;
		String ls_tmpValue;
		int    li_i;
		int    li_character;
		String ls_id;
		ls_changePosition     = null;
		li_i                  = 0;

		if(ai_position == -1)
		{
			for(String ls_tmp : as_idPanels)
			{
				if(ls_tmp.equalsIgnoreCase(as_IdMove))
				{
					ls_changePosition = changePosition(as_idPanels, as_IdMove, li_i);

					break;
				}

				li_i = li_i + 1;
			}
		}
		else if(ai_position >= 0)
		{
			ls_tmpValue = String.valueOf(ai_position);

			for(String ls_tmp : as_idPanels)
			{
				ls_id            = ls_tmp;
				li_character     = ls_tmp.indexOf("-");

				ls_tmp = ls_tmp.substring(li_character + 1);

				if(ls_tmp.equalsIgnoreCase(ls_tmpValue))
				{
					ls_changePosition = ls_id;

					break;
				}
			}
		}

		return ls_changePosition;
	}

	/** {@inheritdoc} */
	public void clear()
	{
		setMostrarAtrasCrearGrabar(false);
		setOcultarSiguienteCrearGrabar(false);
		setEditorTablaDirecciones(false);
		setNumeroAnotacion(null);
		setAnotacion(null);
		setContadorInterviniente(0);
		setDatosBasicos(null);
		setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		setIdTurnoHistoria(null);
		setNumeroAnotacion(null);
		setMostrarAtrasCrearGrabar(false);
		setOcultarSiguienteCrearGrabar(false);
		setMostrarListadoPersonas(false);
		setDeshabilitarDatosInterviniente(false);
		setListadoIntervinientes(null);
		setPredioDetalle(null);
		setPredioDocumento(null);
		setCabidaLinderos(null);
		setAreaPredio(null);
		setDireccionesPredio(null);
		setDireccionPredio(null);
		setAccPredioRegistro(null);
		setDetalleAreaTerreno(null);
		setAreaUI(null);
		setBloquearAgregarAreaTerreno(false);
		setNuevaLinderos(false);
		setNuevaLinderosDigMasivo(false);
		setHabilitarMatriculasComplementacionAnotacion(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String consultaDetalleMatricula()
	{
		try
		{
			RegistroCalificacion             lrc_tmp;
			Collection<RegistroCalificacion> lcrc_tmp;
			String                           ls_lineaSeparadora;
			FacesContext                     lfc_fc;
			Application                      la_a;
			DashboardColumn                  ldbc_dbc;
			DashboardModel                   ldbm_model;
			int                              li_columnCount;
			int                              li_column;
			int                              li_anotacion;
			HtmlOutputText                   loht_space;
			RegistroCalificacion             lorc_dataDoc;
			Date                             ld_fechaDoc;
			String                           ls_codDoc;
			String                           ls_notaria;
			String                           ls_municipioDoc;
			HtmlOutputText                   lhot_ot;

			lrc_tmp            = getDetalleAnotacion();
			ldbc_dbc           = new DefaultDashboardColumn();
			li_columnCount     = 1;
			li_column          = 1;
			li_anotacion       = 0;

			if(lrc_tmp != null)
			{
				lcrc_tmp = lrc_tmp.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_tmp))
				{
					Constantes lc_constante;
					String     ls_actoDivision;
					String     ls_actoAdjudicacion;

					lc_constante            = irr_referenceRemote.findConstantesById(
						    ConstanteCommon.ACTOS_DIVISION_MATERIAL
						);
					ls_actoDivision         = null;
					ls_actoAdjudicacion     = null;

					if(lc_constante != null)
					{
						String ls_caracter;

						ls_caracter = lc_constante.getCaracter();

						if(StringUtils.isValidString(ls_caracter))
						{
							String[] lsa_actosDivisionM;

							lsa_actosDivisionM = ls_caracter.split(",");

							if((lsa_actosDivisionM != null) && (lsa_actosDivisionM.length > 1))
							{
								int li_contador;

								li_contador     = 0;

								ls_actoDivision         = lsa_actosDivisionM[li_contador++];
								ls_actoAdjudicacion     = lsa_actosDivisionM[li_contador++];
							}
						}
					}

					lfc_fc     = FacesContext.getCurrentInstance();
					la_a       = lfc_fc.getApplication();

					ls_lineaSeparadora     = "<br/>";

					idb_dataModel = (Dashboard)la_a.createComponent(
						    lfc_fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer"
						);
					idb_dataModel.setId("dashboard");

					ldbm_model = new DefaultDashboardModel();

					ldbm_model.addColumn(ldbc_dbc);

					idb_dataModel.setModel(ldbm_model);

					for(RegistroCalificacion lorc_rc : lcrc_tmp)
					{
						if(lorc_rc != null)
						{
							Panel  lp_panel;
							String ls_codigoActo;

							lp_panel          = (Panel)la_a.createComponent(
								    lfc_fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer"
								);
							ls_codigoActo     = lorc_rc.getCodActo();

							ldbc_dbc = ldbm_model.getColumn(li_column % li_columnCount);
							lp_panel.setId("ID_" + lorc_rc.getIdAnotacionPredio() + "-" + li_anotacion);
							li_anotacion = li_anotacion + 1;
							ldbc_dbc.addWidget(lp_panel.getId());
							lp_panel.setHeader(
							    "ANOTACION - Orden " + lorc_rc.getOrden() + " - Nro." + lorc_rc.getIdAnotacion() + "  "
							    + " Fecha:"
							    + StringUtils.getString(
							        lorc_rc.getFechaRegistro(), FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
							    ) + "  " + "Radicación:" + getIdTurno()
							);
							lp_panel.setStyle("font-weight: bold;");
							lp_panel.setToggleable(true);
							lp_panel.setStyle("width:1100px;");

							loht_space = new HtmlOutputText();
							loht_space.setEscape(false);
							loht_space.setValue(ls_lineaSeparadora);

							lorc_dataDoc        = lorc_rc.getDatosDocumento();
							ld_fechaDoc         = lorc_dataDoc.getFechaDocumento();
							ls_codDoc           = lorc_dataDoc.getCodDocumento();
							ls_notaria          = lorc_dataDoc.getNombreOficina();
							ls_municipioDoc     = lorc_dataDoc.getNombreMunicipio();

							/* Creacion descripcion del campo */
							lhot_ot             = new HtmlOutputText();

							String ls_dataDoc;
							lhot_ot.setEscape(false);
							lhot_ot.setStyle("padding-right:2em;display: inline-block;");

							ls_dataDoc          = ls_notaria + " " + ls_municipioDoc;

							if(!StringUtils.isValidString(ls_notaria) && !StringUtils.isValidString(ls_municipioDoc))
								ls_dataDoc = "SIN INFORMACIÓN";

							lhot_ot.setValue(
							    "Doc:" + lorc_rc.getNombreDoc() + " " + ls_codDoc + " DEL " + " "
							    + StringUtils.getString(
							        ld_fechaDoc, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
							    ) + "  " + ls_dataDoc
							);

							HtmlOutputText lhot_descripcion = new HtmlOutputText();
							lhot_descripcion.setEscape(false);
							lhot_descripcion.setValue("VALOR ACTO $ : ");

							HtmlOutputText lhot_valor = new HtmlOutputText();
							lhot_valor.setId("val_" + lorc_rc.getIdAnotacionPredio());
							lhot_valor.setTitle("Valor");
							lhot_valor.setValue(lorc_rc.getValor());

							/* Creacion especificacion del acto */
							HtmlOutputText lhot_esp = new HtmlOutputText();
							lhot_esp.setId("ot_esp" + li_column);
							lhot_esp.setEscape(false);
							lhot_esp.setStyle("padding-right: 5em;");
							lhot_esp.setValue(
							    ls_lineaSeparadora + "ESPECIFICACION:" + lorc_rc.getNombreGrupoActo() + " "
							    + ls_codigoActo + " " + lorc_rc.getNombreActo() + " " + ls_lineaSeparadora
							    + "COMENTARIO: " + StringUtils.getStringNotNull(lorc_rc.getComentario())
							);

							HtmlOutputText lhot_d = new HtmlOutputText();
							lhot_d.setEscape(false);
							lhot_d.setValue(
							    ls_lineaSeparadora
							    + "PERSONAS QUE INTERVIENEN EN EL ACTO (X-Titular de derecho real de dominio,I-Titular de dominio incompleto)"
							    + ls_lineaSeparadora
							);
							lhot_d.setStyle("font-weight: bold;");

							HtmlOutputText lhot_titles = new HtmlOutputText();
							lhot_titles.setEscape(false);
							lhot_titles.setStyle("width:600px;padding-left:44em;font-weight: bold;");
							lhot_titles.setValue(
							    "Propietario - Porcentaje - Calidad Interviniente" + ls_lineaSeparadora
							);

							/* Adicion de htmlouputtext al panel */
							lp_panel.getChildren().add(lhot_ot);
							lp_panel.getChildren().add(lhot_descripcion);
							lp_panel.getChildren().add(lhot_valor);
							lp_panel.getChildren().add(lhot_esp);
							lp_panel.getChildren().add(lhot_d);
							lp_panel.getChildren().add(lhot_titles);

							Collection<RegistroCalificacion> lcrc_dataIntervinientes;
							lcrc_dataIntervinientes = lorc_rc.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_dataIntervinientes))
							{
								boolean lb_headers;
								int     li_i;
								lb_headers     = true;
								li_i           = 1;

								for(RegistroCalificacion lorc_tmp : lcrc_dataIntervinientes)
								{
									if(li_i > 1)
										lb_headers = false;

									li_i = li_i + 1;

									HtmlDataTable      lodt_table;
									ArrayList<Integer> lali_ali;
									String             ls_nombrePersona;

									lodt_table     = new HtmlDataTable();
									lali_ali       = new ArrayList<Integer>();

									if(lorc_tmp.getTipoDoc().equalsIgnoreCase(IdentificadoresCommon.NIT))
										ls_nombrePersona = StringUtils.getStringNotNull(lorc_tmp.getRazonSocial())
												                          .toString();
									else
										ls_nombrePersona = StringUtils.getStringNotNull(lorc_tmp.getNombrePersona())
												                          .toString();

									lali_ali.add(NumericUtils.getInteger(1));

									lodt_table.setValue(lali_ali);
									lodt_table.setStyleClass("TableContent");
									lodt_table.setHeaderClass("af_column_header-text SomeBorderStyle");
									lodt_table.setColumnClasses("af_column_cell-text OraTableBorder1111");

//								lodt_table.setBorder(1);
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
									lot_headerPorcentaje.setStyle("padding-left: 30px;");
									lot_headerPorcentaje.setValue("%");

									if(lb_headers)
										luic_porcetaje.setHeader(lot_headerPorcentaje);

									HtmlOutputText ls_porcentaje = new HtmlOutputText();

									if(StringUtils.isValidString(lorc_tmp.getPorcentajeStr()))
										ls_porcentaje.setValue(lorc_tmp.getPorcentajeStr() + "% ");

									ls_porcentaje.setStyle("width: 74px;display: block;text-align: center;");
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
									lot_headerPropietario.setValue("Propietario ");

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

									{
										List<UIComponent> lluc_tableChildren;

										lluc_tableChildren = lodt_table.getChildren();

										if(lluc_tableChildren != null)
										{
											lluc_tableChildren.add(luic_rol);
											lluc_tableChildren.add(luic_nombre);
											lluc_tableChildren.add(luic_tipoDoc);
											lluc_tableChildren.add(luic_propietario);
											lluc_tableChildren.add(luic_porcetaje);
											lluc_tableChildren.add(luic_interesadaRR);
										}

										lp_panel.getChildren().add(lodt_table);
									}
								}

								if(
								    StringUtils.isValidString(ls_actoAdjudicacion)
									    && StringUtils.isValidString(ls_actoDivision)
									    && ls_actoAdjudicacion.equalsIgnoreCase(ls_codigoActo)
								)
								{
									Iterator<RegistroCalificacion> lirc_iterador;
									boolean                        lb_divisionMaterial;

									lirc_iterador           = lcrc_tmp.iterator();
									lb_divisionMaterial     = false;

									while(lirc_iterador.hasNext() && !lb_divisionMaterial)
									{
										RegistroCalificacion lrc_registroCalificacion;

										lrc_registroCalificacion = lirc_iterador.next();

										if(lrc_registroCalificacion != null)
										{
											String ls_codActoTmp;

											ls_codActoTmp     = lrc_registroCalificacion.getCodActo();

											lb_divisionMaterial = StringUtils.isValidString(ls_codActoTmp)
													&& ls_codActoTmp.equalsIgnoreCase(ls_actoDivision);
										}
									}

									if(lb_divisionMaterial)
									{
										AgregarBotonAnotacion laba_agregarBotonAnotacion;
										CommandButton         lcb_botonModificar;

										laba_agregarBotonAnotacion     = new AgregarBotonAnotacion();
										lcb_botonModificar             = laba_agregarBotonAnotacion
												.agregarBotonModificacion();

										if(lcb_botonModificar != null)
											lp_panel.getChildren().add(lcb_botonModificar);
									}
								}
							}

							idb_dataModel.getChildren().add(lp_panel);
							li_column = li_column + 1;
						}
					}

					setPanels(ldbc_dbc.getWidgets());
					PrimeFaces.current().ajax()
						          .update("fDetalleRegistroCalificacion:WdetalleRegistroCalif:Anotaciones_id");
				}
			}
		}
		catch(Exception le_e)
		{
			addMessage(new B2BException(le_e.getMessage()));
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return null;
	}

	/** {@inheritdoc} */
	public void consultarAnotacionCancelacion()
	{
		try
		{
			String ls_naturaleza;
			ls_naturaleza = getNaturalezaJuridicaSeleccionada();

			if(StringUtils.isValidString(ls_naturaleza))
			{
				String[] lsa_datos;
				lsa_datos = ls_naturaleza.split("-");

				if((lsa_datos != null) && (lsa_datos.length > 1))
				{
					String             ls_idNaturalezaJuridica;
					String             ls_version;
					NaturalezaJuridica lnj_parametros;

					ls_idNaturalezaJuridica     = lsa_datos[0];
					ls_version                  = lsa_datos[1];
					lnj_parametros              = new NaturalezaJuridica();
					lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
					lnj_parametros.setVersion(NumericUtils.getLong(ls_version));

					lnj_parametros = iasr_antiguoSistemaRemote.findNaturalezaJuridicaById(lnj_parametros);

					if(lnj_parametros != null)
					{
						String ls_grupo;
						String ls_descripcion;

						ls_grupo           = lnj_parametros.getIdGrupoNatJur();
						ls_descripcion     = lnj_parametros.getNombre();

						{
							AnotacionPredio lap_tmp;
							lap_tmp = getAnotacionPredioDetalle();

							if(lap_tmp != null)
								lap_tmp.setEspecificacion(ls_descripcion);
						}

						setMostrarAnotacionCancela(
						    StringUtils.isValidString(ls_grupo)
							    && (ls_grupo.contains("700") || ls_grupo.contains("800"))
						);
					}
				}
			}
			else
			{
				AnotacionPredio lap_tmp;
				lap_tmp = getAnotacionPredioDetalle();

				if(lap_tmp != null)
					lap_tmp.setEspecificacion(null);

				setMostrarAnotacionCancela(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public void consultarPersonaDocumento()
	{
		try
		{
			Persona  lp_parametros;
			Registro lr_registro;

			lp_parametros     = new Persona();
			lr_registro       = new Registro();

			lp_parametros.setNumeroDocumento(getDocumentoInterviniente());
			lp_parametros.setTipoDocIdentidad(getTipoDocInterviniente());
			lr_registro.setPersona(lp_parametros);
			setIdPersonaSeleccion(null);

			Registro resultado = irr_registroRemote.findPersonByDocument(lr_registro, getUserId());

			if(resultado != null)
			{
				setListadoIntervinientes(resultado.getListadoPersona());
				setMostrarListadoPersonas(true);

				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_INTERVINIENTES);
		}
		catch(B2BException lb2be_e)
		{
			setMostrarListadoPersonas(false);
			setDeshabilitarDatosInterviniente(false);
			setListadoIntervinientes(null);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Copia masiva.
	 *
	 * @param ai_i correspondiente al valor del tipo de objeto int
	 */
	public void copiaMasiva(int ai_i)
	{
		try
		{
			String ls_rangoInicio;
			String ls_rangoFin;

			ls_rangoInicio     = getDesde();
			ls_rangoFin        = getHasta();

			if(StringUtils.isValidString(ls_rangoInicio) && StringUtils.isValidString(ls_rangoFin))
			{
				Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lcap_cap;
				Collection<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio> lcap_tmp;
				AccPredioRegistro                                            lapr_pr;
				lapr_pr     = getAccPredioRegistroView();

				lcap_cap     = getMatriculasInformacion();
				lcap_tmp     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio>();

				if(CollectionUtils.isValidCollection(lcap_cap))
				{
					long ll_rangoInicio;
					long ll_rangoFin;

					ll_rangoInicio     = NumericUtils.getLong(ls_rangoInicio);
					ll_rangoFin        = NumericUtils.getLong(ls_rangoFin);

					if(ll_rangoInicio > ll_rangoFin)
						throw new B2BException(ErrorKeys.ERROR_CAMPO_DESDE);

					for(com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio lap_tmp : lcap_cap)
					{
						if(lap_tmp != null)
						{
							long ll_idMatricula;
							ll_idMatricula = lap_tmp.getIdMatricula();

							if((ll_idMatricula >= ll_rangoInicio) && (ll_idMatricula <= ll_rangoFin))
								lcap_tmp.add(lap_tmp);
						}
					}
				}

				lapr_pr.setInfoMatriculas(lcap_tmp);
				lapr_pr.setIdProceso(ai_i);
				lapr_pr.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lapr_pr.setIdUsuarioCreacion(getUserId());
				lapr_pr.setIpCreacion(getRemoteIpAddress());

				if(ai_i == 0)
				{
					//copiar linderos
					CabidaLinderos lcl_cabidaLinderos;

					lcl_cabidaLinderos = getCabidaLinderosView();

					if(lcl_cabidaLinderos != null)
					{
						AccLinderoPredio llp_linderoPredio;

						llp_linderoPredio = new AccLinderoPredio();

						llp_linderoPredio.setLindero(lcl_cabidaLinderos.getLinderos());
						llp_linderoPredio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						llp_linderoPredio.setIdUsuarioCreacion(getUserId());
						llp_linderoPredio.setIpCreacion(getLocalIpAddress());
						llp_linderoPredio.setIdUsuarioModificacion(getUserId());
						llp_linderoPredio.setIpModificacion(getLocalIpAddress());
						llp_linderoPredio.setIdCirculo(lapr_pr.getIdCirculo());
						llp_linderoPredio.setIdTurno(lapr_pr.getIdTurno());

						lapr_pr.setLinderoPredio(llp_linderoPredio);

						setLinderoDisabled(true);
					}
				}
				else if(ai_i == 1)
				{
					//copiar complementacion
					ComplementacionCalificacion lcc_complementacionCalificacion;

					lcc_complementacionCalificacion = getComplementacionCalificacion();

					if(isComplementacionSinConstruir())
						throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

					if(lcc_complementacionCalificacion != null)
					{
						Complementacion       lc_complementacion;
						ComplementacionPredio lcp_complementacionPredio;

						lc_complementacion            = lcc_complementacionCalificacion.getComplementacion();
						lcp_complementacionPredio     = lcc_complementacionCalificacion.getComplementacionPredio();

						if((lc_complementacion != null) && (lcp_complementacionPredio != null))
						{
							lc_complementacion.setComplementacion(lcp_complementacionPredio.getComplementacion());
							lc_complementacion.setTipoComplementacion(
							    lcc_complementacionCalificacion.getTipoComplementacion()
							);
							lc_complementacion.setIdComplementacion(lcp_complementacionPredio.getIdComplementacion());
						}

						lapr_pr.setInfoComplementacion(lc_complementacion);
					}
				}
				else
				{
					//copiar direcciones
					lapr_pr.setDireccionesPredio(getDireccionesPredioView());
					lapr_pr.setCalificacion(true);
				}

				irr_calificacionRemote.actualizacionMasiva(
				    lapr_pr, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DATOS_INVALIDOS_DESDE);

			limpiarLoteo();
			addMessage(MessagesKeys.MODIFICACION_EXITOSA);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Copiar lindero matricula.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void copiarLinderoMatricula(boolean ab_b)
	    throws B2BException
	{
		CabidaLinderos lcl_cl;
		lcl_cl = getCabidaLinderosView();

		try
		{
			if(lcl_cl != null)
			{
				Long   il_idMatricula;
				String ls_idCirculo;

				il_idMatricula     = lcl_cl.getIdMatricula();
				ls_idCirculo       = lcl_cl.getIdCirculo();

				if(ab_b)
				{
					String                      ls_lindero;
					LinderoRegistroCalificacion llrc_lrc;

					llrc_lrc = new LinderoRegistroCalificacion();
					llrc_lrc.setIdMatricula(il_idMatricula);
					llrc_lrc.setIdCirculo(ls_idCirculo);

					ls_lindero = irr_calificacionRemote.findLindero(llrc_lrc, true);

					if(StringUtils.isValidString(ls_lindero))
						lcl_cl.setLinderos(ls_lindero);
				}
				else
				{
					Complementacion       lc_complementacion;
					ComplementacionPredio lcp_complementacionPredio;

					lc_complementacion = new Complementacion();
					lc_complementacion.setIdCirculo(ls_idCirculo);
					lc_complementacion.setIdMatricula(il_idMatricula);

					lcp_complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

					if(lcp_complementacionPredio != null)
					{
						lcl_cl.getComplementacion().setComplementacion(lcp_complementacionPredio.getComplementacion());
						lcl_cl.getComplementacionPredio()
							      .setIdComplementacion(lcp_complementacionPredio.getIdComplementacion());
						setCopiarEditar(false);
					}
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public String deleteMatriculaBase(DireccionPredio adp_direccionBase)
	{
		String ls_idCirculo;
		long   ll_idMatricula;

		ls_idCirculo       = adp_direccionBase.getIdCirculo();
		ll_idMatricula     = NumericUtils.getLong(adp_direccionBase.getIdMatricula());

		try
		{
			PredioSegregado lps_ps;

			lps_ps = new PredioSegregado();

			lps_ps.setIdCirculo(ls_idCirculo);
			lps_ps.setIdMatricula(ll_idMatricula);
			lps_ps.setIdTurno(getIdTurno());

			lps_ps = iasr_antiguoSistemaRemote.findByCirculoMatriculaTurno(lps_ps);

			if(lps_ps != null)
				iasr_antiguoSistemaRemote.deleteByCirculoMatriculaTurno(lps_ps);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		getDatosBasicos().getMatriculaBase().getDireccionPredio().remove(adp_direccionBase);

		return null;
	}

	/** {@inheritdoc} */
	public void departamento()
	{
		Apertura la_apertura;

		la_apertura = getDatosBasicos().getApertura();

		la_apertura.setIdMunicipio(null);
		la_apertura.setIdOficinaOrigen(null);
		findMunicipio();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public void deshabilitarCamposNit()
	{
		Persona lp_persona;
		lp_persona = getPersona();

		if(lp_persona != null)
		{
			String ls_tipoDocumento;
			ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

			setDeshabilitarCamposPorNit(
			    StringUtils.isValidString(ls_tipoDocumento)
				    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
			);
		}
	}

	/**
	 * Editar complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void editarComplementacion()
	    throws B2BException
	{
		try
		{
			CabidaLinderos lcl_cl;
			lcl_cl = getCabidaLinderosView();

			if(lcl_cl != null)
			{
				Complementacion lc_c;
				lc_c = lcl_cl.getComplementacion();

				if(lc_c != null)
				{
					if(StringUtils.isValidString(lc_c.getComplementacion()))
						setCopiarEditar(true);
					else
					{
						setCopiarEditar(false);
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_COPIA);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void eliminarArea(DetalleAreaPredio aadap_detalle)
	    throws B2BException
	{
		AccAreaUI laaui_dataArea;

		laaui_dataArea = getAreaUI();

		if((aadap_detalle != null) && (laaui_dataArea != null))
		{
			Collection<DetalleAreaPredio> lcadap_areas;

			lcadap_areas = laaui_dataArea.getAreasTerreno();

			if(CollectionUtils.isValidCollection(lcadap_areas))
			{
				lcadap_areas.remove(aadap_detalle);

				laaui_dataArea.setAreasTerreno(lcadap_areas);
				setAreaUI(laaui_dataArea);
				actualizarAreaTerreno();
			}
		}
	}

	/** {@inheritdoc} */
	public void eliminarInterviniente(Anotacion al_idAnotacion)
	{
		Collection<Anotacion> lca_apc;

		if(getPersona() != null)
		{
			lca_apc = getIntervinientesAgregados();

			if((al_idAnotacion != null) && CollectionUtils.isValidCollection(lca_apc))
			{
				for(Anotacion loapc_apc : lca_apc)
				{
					if(
					    al_idAnotacion.getPersona().getIdPersona()
						                  .equalsIgnoreCase(loapc_apc.getPersona().getIdPersona())
					)
					{
						lca_apc.remove(al_idAnotacion);

						break;
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de eliminar matriculas para el proceso de complementacion copiada de matriculas.
	 *
	 * @param amb_matricula correspondiente al valor del tipo de objeto MatriculaBase
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void eliminarMatriculaComplementacion(MatriculaBase amb_matricula)
	    throws B2BException
	{
		eliminarMatriculaComplementacion(amb_matricula, false, true);
	}

	/** {@inheritdoc} */
	public void filtrarPorCirculo()
	{
		String ls_idCirculo;

		ls_idCirculo = getDatosBasicos().getUbicacion().getCirculoRegistral().getIdCirculo();

		ZonaRegistral lcr_zonaRegistral;

		lcr_zonaRegistral = new ZonaRegistral();

		lcr_zonaRegistral.setIdCirculo(ls_idCirculo);

		if(ls_idCirculo != null)
		{
			try
			{
				lcr_zonaRegistral = irr_registroRemote.findZonaRegistralById(lcr_zonaRegistral, getUserId());

				if(lcr_zonaRegistral != null)
				{
					Collection<Departamento> lcd_temp;

					lcd_temp = findDepartamentosAntSistema();

					if(CollectionUtils.isValidCollection(lcd_temp))
					{
						for(Departamento l_dept : lcd_temp)
						{
							if(l_dept.getIdDepartamento().equals(lcr_zonaRegistral.getIdDepartamento()))
							{
								getDatosBasicos().getUbicacion().getDepartamento()
									    .setIdDepartamento(l_dept.getIdDepartamento());

								break;
							}
						}
					}

					Collection<Municipio> lcm_mun;

					lcm_mun = findMunicipioAntSistema();

					if(CollectionUtils.isValidCollection(lcm_mun))
					{
						for(Municipio l_municipio : lcm_mun)
						{
							if(l_municipio.getIdMunicipio().equals(lcr_zonaRegistral.getIdMunicipio()))
							{
								getDatosBasicos().getUbicacion().getMunicipio()
									    .setIdMunicipio(l_municipio.getIdMunicipio());

								break;
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);

				lcr_zonaRegistral = null;
			}
		}
	}

	/** {@inheritdoc} */
	@Override
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lccr_circulos;
		lccr_circulos = null;

		try
		{
			CirculoRegistral lcr_circulo;

			lcr_circulo = new CirculoRegistral();

			lcr_circulo.setIdCirculo(getIdCirculo());

			lcr_circulo = ipr_parameterRemote.findCirculoRegistralById(
				    lcr_circulo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcr_circulo != null)
			{
				lccr_circulos = new ArrayList<CirculoRegistral>();
				lccr_circulos.add(lcr_circulo);

				{
					ComplementacionCalificacion lcc_complementacion;

					lcc_complementacion = getComplementacionCalificacion();

					if(lcc_complementacion != null)
					{
						Complementacion lc_complementacion;

						lc_complementacion = lcc_complementacion.getComplementacion();

						if(lc_complementacion != null)
							lc_complementacion.setIdCirculo(lcr_circulo.getIdCirculo());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lccr_circulos = null;
		}

		return lccr_circulos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		DatosBasicos ldb_datos;
		ldb_datos = getDatosBasicos();

		try
		{
			if(ldb_datos != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ldb_datos.getUbicacion().getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartamentosAntSistema()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			String ls_pais;

			ls_pais = getDatosAntiguoSistema().getIdPais();

			if(!StringUtils.isValidString(ls_pais))
				ls_pais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ls_pais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/** {@inheritdoc} */
	public Collection<Departamento> findDepartamentosDocumento()
	{
		Collection<Departamento> lcd_departamentos;
		Documento                ld_documento;

		lcd_departamentos     = null;
		ld_documento          = getDocumento();

		try
		{
			if(ld_documento != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ld_documento.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
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
	public Collection<Departamento> findDepartamentosPredio()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			DireccionDelPredio direccion;
			direccion = getDireccionPredio();

			if(direccion != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(direccion.getDatosAntiguoSistema().getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/** {@inheritdoc} */
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcep_estadoPredio = null;
		}

		return lcep_estadoPredio;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipio()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				UbicacionZonaRegistral luzr_ubicacionZonaRegistral;

				luzr_ubicacionZonaRegistral = ldb_datosBasicos.getUbicacion();

				if(luzr_ubicacionZonaRegistral != null)
				{
					Departamento ld_departamento;

					ld_departamento = luzr_ubicacionZonaRegistral.getDepartamento();

					if(ld_departamento != null)
					{
						Municipio lm_parametros;

						lm_parametros = new Municipio();

						lm_parametros.setIdPais(luzr_ubicacionZonaRegistral.getIdPais());
						lm_parametros.setIdDepartamento(ld_departamento.getIdDepartamento());

						if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
							lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
						else
							lcm_municipios = null;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipioAntSistema()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			DatosAntSistema lda_datosAntSistema;
			lda_datosAntSistema = getDatosAntiguoSistema();

			if(lda_datosAntSistema != null)
			{
				String ls_departamento;
				String ls_pais;

				ls_departamento     = lda_datosAntSistema.getIdDepartamento();
				ls_pais             = lda_datosAntSistema.getIdPais();

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
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipioApertura()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			String ld_ubicacion;
			ld_ubicacion = getDatosBasicos().getApertura().getIdDepartamento();

			if(ld_ubicacion != null)
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(getIdPais());
				lm_parametros.setIdDepartamento(ld_ubicacion);

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipioPredio()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			DireccionDelPredio direccion;
			direccion = getDireccionPredio();

			if(direccion != null)
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(direccion.getDatosAntiguoSistema().getIdPais());
				lm_parametros.setIdDepartamento(direccion.getDatosAntiguoSistema().getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/** {@inheritdoc} */
	public Collection<Municipio> findMunicipiosDocumento()
	{
		Collection<Municipio> lcm_municipios;
		Documento             ld_documento;

		lcm_municipios     = null;
		ld_documento       = getDocumento();

		if(ld_documento != null)
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(ld_documento.getIdPais());
				lm_parametros.setIdDepartamento(ld_documento.getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcm_municipios = null;
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
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/** {@inheritdoc} */
	public Collection<InteresadoDocumentoTipo> findRrr()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		String                              ls_naturalezaJuridica;

		lcidt_datos               = new ArrayList<InteresadoDocumentoTipo>();
		ls_naturalezaJuridica     = getNaturalezaJuridicaSeleccionada();

		try
		{
			if(StringUtils.isValidString(ls_naturalezaJuridica))
			{
				String[] lsa_datos;
				lsa_datos = ls_naturalezaJuridica.split("-");

				if((lsa_datos != null) && (lsa_datos.length > 0))
					lcidt_datos = irr_referenceRemote.findRrr(lsa_datos[0]);
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
	public void findTipoEntidadApertura()
	{
		Apertura la_apertura;

		la_apertura = getDatosBasicos().getApertura();

		try
		{
			if(la_apertura != null)
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(la_apertura.getIdTipoOficina());

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					la_apertura.setIdTipoEntidad(lte_te.getIdTipoEntidad());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public void findTipoEntidadDocumento()
	{
		Documento ld_documento;

		ld_documento = getDocumento();

		try
		{
			if(ld_documento != null)
			{
				TipoOficina lto_to;

				lto_to = new TipoOficina();

				lto_to.setIdTipoOficina(ld_documento.getIdTipoOficina());

				lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

				if(lto_to != null)
				{
					TipoEntidad lte_te;

					lte_te = new TipoEntidad();

					lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

					lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

					ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public Collection<Vereda> findVereda()
	{
		Collection<Vereda> lcv_veredas;
		lcv_veredas = null;

		try
		{
			UbicacionZonaRegistral ld_ubicacion;
			ld_ubicacion = getDatosBasicos().getUbicacion();

			if(ld_ubicacion != null)
			{
				Vereda lv_parametros;
				lv_parametros = new Vereda();

				lv_parametros.setIdPais(ld_ubicacion.getIdPais());
				lv_parametros.setIdDepartamento(ld_ubicacion.getDepartamento().getIdDepartamento());
				lv_parametros.setIdMunicipio(ld_ubicacion.getMunicipio().getIdMunicipio());

				if(StringUtils.isValidString(lv_parametros.getIdMunicipio()))
					lcv_veredas = irr_referenceRemote.findVeredas(lv_parametros, false);
				else
					lcv_veredas = null;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			lcv_veredas = null;
		}

		return lcv_veredas;
	}

	/** {@inheritdoc} */
	public String flowListenerCrearGrabar(FlowEvent ae_event)
	{
		String ls_return;

		ls_return = ae_event.getNewStep();

		try
		{
			String ls_oldStep;
			String ls_newStep;
			String ls_datosBasicos_id;
			String ls_CabidaYLinderos_id;
			String ls_AreaPredio_id;
			String ls_DireccionPredio_id;

			ls_oldStep                = ae_event.getOldStep();
			ls_newStep                = ae_event.getNewStep();
			ls_datosBasicos_id        = "datosBasicos_id";
			ls_CabidaYLinderos_id     = "CabidaYLinderos_id";
			ls_AreaPredio_id          = "AreaPredio_id";
			ls_DireccionPredio_id     = "DireccionPredio_id";

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				if(ls_oldStep.equalsIgnoreCase(ls_datosBasicos_id))
				{
					salvarDatosBasicos();
					setMostrarAtrasCrearGrabar(true);
					setTerminarProceso(false);
					setProcesoTerminadoCrearGrabar(false);
					setMostrarCheckNuevaLinderos(mostrarNuevaLinderos());
				}

				else if(ls_oldStep.equalsIgnoreCase(ls_CabidaYLinderos_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_AreaPredio_id))
					{
						salvarCabidaYLinderos();
						cargarDatosArea(true);
					}
					else if(ls_newStep.equalsIgnoreCase(ls_datosBasicos_id))
					{
						setOcultarSiguienteCrearGrabar(false);
						setMostrarAtrasCrearGrabar(false);
					}
				}

				else if(ls_oldStep.equalsIgnoreCase(ls_AreaPredio_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_DireccionPredio_id))
					{
						salvarAreaPredio();

						if(ls_newStep.equalsIgnoreCase(ls_DireccionPredio_id) && isEjecucionCorrecciones())
						{
							BeanDireccion lbd_beanDireccion;

							lbd_beanDireccion = getBeanDireccion();

							lbd_beanDireccion.limpiarBeanDireccion();
							lbd_beanDireccion.setAgregarDireccionPredio(true);
							lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
							lbd_beanDireccion.setForm(is_idForm);
							lbd_beanDireccion.cargarDatosDireccionPredio(getDatosBasicos());
							lbd_beanDireccion.setDireccionesPredio2(getDireccionesPredio());
							lbd_beanDireccion.setDireccionesTemporales(getDireccionesPredio());
						}
					}
				}

				else if(ls_oldStep.equalsIgnoreCase(ls_DireccionPredio_id))
				{
					if(ls_newStep.equalsIgnoreCase(is_tabAnotacionesWizardId))
					{
						if(isEjecucionCorrecciones())
						{
							BeanDireccion lbd_beanDireccion;

							lbd_beanDireccion = getBeanDireccion();

							setDireccionesPredio(lbd_beanDireccion.getDireccionesPredio2());
							setDireccionesTemporales(lbd_beanDireccion.getDireccionesTemporales());
						}

						salvarDireccionPredio();
						setOcultarSiguienteCrearGrabar(true);
						setTerminarProceso(true);
						consultaDetalleMatricula();
					}
					else if(ls_newStep.equalsIgnoreCase(ls_AreaPredio_id))
					{
					}
				}

				else if(ls_oldStep.equalsIgnoreCase(is_tabAnotacionesWizardId))
				{
					if(ls_newStep.equalsIgnoreCase(ls_DireccionPredio_id))
					{
						setOcultarSiguienteCrearGrabar(false);
						setTerminarProceso(false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			ls_return = ae_event.getOldStep();
		}

		setTabActual(ls_return);
		PrimeFaces.current().ajax().update(is_messageIdGrowl);

		return ls_return;
	}

	/** {@inheritdoc} */
	public String flowListenerLoteo(FlowEvent ae_event)
	{
		String ls_return;
		String ls_oldStep;
		String ls_newStep;

		ls_oldStep     = ae_event.getOldStep();
		ls_newStep     = ae_event.getNewStep();
		ls_return      = ls_newStep;

		try
		{
			String ls_CabidaYLinderos_id;
			String ls_AreaPredio_id;
			String ls_DireccionPredio_id;

			ls_CabidaYLinderos_id     = "CabidaYLinderos_idView";
			ls_AreaPredio_id          = "AreaPredio_idView";
			ls_DireccionPredio_id     = "DireccionPredio_idView";

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				AccPredioRegistro lapr_pr;

				lapr_pr = getAccPredioRegistroView();

				if(ls_oldStep.equalsIgnoreCase(ls_CabidaYLinderos_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_AreaPredio_id))
					{
						if(isComplementacionSinConstruir())
							throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

						AccLinderoPredio llp_linderoPredio;
						CabidaLinderos   lcl_cabidaLinderos;

						lcl_cabidaLinderos     = getCabidaLinderosView();
						llp_linderoPredio      = new AccLinderoPredio();

						llp_linderoPredio.setLindero(lcl_cabidaLinderos.getLinderos());
						llp_linderoPredio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						llp_linderoPredio.setIdUsuarioCreacion(getUserId());
						llp_linderoPredio.setIpCreacion(getLocalIpAddress());
						llp_linderoPredio.setIdUsuarioModificacion(getUserId());
						llp_linderoPredio.setIpModificacion(getLocalIpAddress());
						llp_linderoPredio.setIdCirculo(lapr_pr.getIdCirculo());
						llp_linderoPredio.setIdMatricula(lapr_pr.getIdMatricula());
						llp_linderoPredio.setIdTurno(lapr_pr.getIdTurno());

						irr_calificacionRemote.accionSalvarView(
						    llp_linderoPredio, null, null, null, lapr_pr, null, null, getIdTurnoHistoria(), getUserId(),
						    getRemoteIpAddress(), getLocalIpAddress()
						);
						guardarComplementacion(true);
						setRegresar(true);
						setSiguiente(true);
					}
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_AreaPredio_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_DireccionPredio_id))
					{
						AccAreaUI laaui_data;

						laaui_data = getAreaUI();

						if(!isBloquearAgregarAreaTerreno())
						{
							AccPredioRegistro lapr_predioRegistro;

							lapr_predioRegistro = getAccPredioRegistroView();

							if((laaui_data != null) && (lapr_predioRegistro != null))
							{
								Collection<DetalleAreaPredio> lcadap_areasTerreno;

								lcadap_areasTerreno = laaui_data.getAreasTerreno();

								if(CollectionUtils.isValidCollection(lcadap_areasTerreno))
								{
									AccAreaPredio laap_areaPredio;
									boolean       lb_focus;
									FacesContext  lfc_context;

									laap_areaPredio     = laaui_data.getAreaPredio();
									lb_focus            = false;
									lfc_context         = FacesContext.getCurrentInstance();

									if(laap_areaPredio != null)
									{
										String ls_tipoUso;

										ls_tipoUso     = laap_areaPredio.getTipoSuelo();
										lb_focus       = validateStyles(
											    ":fDetalleRegistroCalificacion:idSOMtipoUsoSuelo", lfc_context,
											    ls_tipoUso, lb_focus
											);

										if(!StringUtils.isValidString(ls_tipoUso))
											throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

										Double ld_coeficiente;

										ld_coeficiente = laap_areaPredio.getCoeficiente();

										if(NumericUtils.isValidDouble(ld_coeficiente))
										{
											String ls_coeficiente;

											ls_coeficiente = null;

											if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100d)) > 0)
											{
												lb_focus = validateStyles(
													    ":fDetalleRegistroCalificacion:idITcoeficiente", lfc_context,
													    ls_coeficiente, lb_focus
													);

												throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
											}
											else
											{
												ls_coeficiente     = StringUtils.getString(ld_coeficiente);
												lb_focus           = validateStyles(
													    ":fDetalleRegistroCalificacion:idITcoeficiente", lfc_context,
													    ls_coeficiente, lb_focus
													);
											}
										}
									}

									{
										String ls_idCirculo;
										Long   ll_idMatricula;

										ls_idCirculo       = lapr_predioRegistro.getIdCirculo();
										ll_idMatricula     = lapr_predioRegistro.getIdMatricula();

										if(
										    StringUtils.isValidString(ls_idCirculo)
											    && NumericUtils.isValidLong(ll_idMatricula)
										)
										{
											laaui_data.setIdCirculo(ls_idCirculo);
											laaui_data.setIdMatricula(ll_idMatricula);
											laaui_data.setIdTurno(getIdTurno());
											laaui_data.setIdTurnoHistoria(
											    NumericUtils.getLongWrapper(getIdTurnoHistoria())
											);
										}
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);

						irr_calificacionRemote.accionSalvarView(
						    null, null, null, null, lapr_pr, laaui_data, null, getIdTurnoHistoria(), getUserId(),
						    getRemoteIpAddress(), getLocalIpAddress()
						);

						{
							AccPredioRegistro    lapr_data;
							BeanDireccion        lbd_beanDireccion;
							RegistroCalificacion lrc_data;
							String               ls_idCirculo;
							String               ls_idTurno;
							Long                 ll_idMatricula;

							lbd_beanDireccion     = getBeanDireccion();
							lrc_data              = new RegistroCalificacion();
							lapr_data             = getAccPredioRegistroView();

							ls_idCirculo       = lapr_data.getIdCirculo();
							ls_idTurno         = getIdTurno();
							ll_idMatricula     = lapr_data.getIdMatricula();

							lrc_data.setIdTurno(ls_idTurno);

							if(lapr_data != null)
							{
								lrc_data.setIdCirculo(lapr_data.getIdCirculo());
								lrc_data.setIdMatricula(lapr_data.getIdMatricula());
							}

							lbd_beanDireccion.limpiarBeanDireccion();
							lbd_beanDireccion.setAgregarDireccionPredio(true);
							lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
							lbd_beanDireccion.setForm(is_idForm);
							lbd_beanDireccion.setDireccionPredio(
							    irr_calificacionRemote.findDireccionPredioByIdCirculoMatriculaTurno(
							        ll_idMatricula, ls_idCirculo, ls_idTurno, getUserId(), getLocalIpAddress(),
							        getRemoteIpAddress()
							    )
							);
							lbd_beanDireccion.cargarDatosDireccionPredio(
							    irr_calificacionRemote.cargarDatosBasicosDigitadorMasivo(lrc_data)
							);
							lbd_beanDireccion.setDireccionesPredio2(getDireccionesPredio());
							lbd_beanDireccion.setDireccionesTemporales(getDireccionesPredio());
						}

						setRegresar(true);
						setSiguiente(false);
						setTerminar(true);
					}

					else if(ls_newStep.equalsIgnoreCase(ls_CabidaYLinderos_id))
					{
						setRegresar(false);
						setSiguiente(true);
					}
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_DireccionPredio_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_AreaPredio_id))
					{
						setRegresar(true);
						setSiguiente(true);
						setTerminar(false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = ls_oldStep;
		}

		return ls_return;
	}

	/**
	 * Guardar complementacion.
	 *
	 * @param ab_ajax correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarComplementacion(boolean ab_ajax)
	{
		try
		{
			ComplementacionCalificacion lcc_complementacionCalificacion;

			lcc_complementacionCalificacion = getComplementacionCalificacion();

			if(lcc_complementacionCalificacion != null)
			{
				ComplementacionPredio lcp_complementacionPredio;

				lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

				if(lcp_complementacionPredio != null)
				{
					String                   ls_complementacion;
					String                   ls_tipoComplementacion;
					boolean                  lb_nuevaComplementacion;
					AccComplementacionPredio lacp_complementacionPredio;

					ls_complementacion         = lcp_complementacionPredio.getComplementacion();
					ls_tipoComplementacion     = lcc_complementacionCalificacion.getTipoComplementacion();

					if(StringUtils.isValidString(ls_tipoComplementacion))
						lb_nuevaComplementacion = ls_tipoComplementacion.equalsIgnoreCase(
							    TipoComplementacionCommon.NUEVA
							) || ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR)
								|| ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR);
					else
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_COMPLEMENTACION);

					lacp_complementacionPredio = new AccComplementacionPredio();

					lacp_complementacionPredio.setComplementacion(ls_complementacion);

					lacp_complementacionPredio.setIdComplementacion(
					    NumericUtils.getLongWrapper(
					        lcc_complementacionCalificacion.getComplementacionPredio().getIdComplementacion()
					    )
					);
					lacp_complementacionPredio.setIdUsuarioCreacion(getUserId());
					lacp_complementacionPredio.setIpCreacion(getLocalIpAddress());
					lacp_complementacionPredio.setIdUsuarioModificacion(getUserId());
					lacp_complementacionPredio.setIpModificacion(getLocalIpAddress());
					lacp_complementacionPredio.setIdTurno(getIdTurno());
					lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

					if(StringUtils.isValidString(ls_tipoComplementacion))
					{
						DatosAntSistema           lda_datosAntSistema;
						Collection<MatriculaBase> lcmb_cmb;

						lda_datosAntSistema     = getDatosAntiguoSistemaView();
						lcmb_cmb                = getMatriculasParaGenerarComplementacion();

						lcp_complementacionPredio.setComplementacion(ls_complementacion);
						lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());
						lcp_complementacionPredio.setIpCreacion(getLocalIpAddress());
						lcp_complementacionPredio.setCopiarEditar(
						    isCopiarEditar() ? true
						                     : (CollectionUtils.isValidCollection(lcmb_cmb) ? (lcmb_cmb.size() > 1)
						                                                                    : false)
						);

						if(lda_datosAntSistema != null)
							lcp_complementacionPredio.setIdDatosAntSistema(lda_datosAntSistema.getIdDatosAntSistema());
					}

					{
						AccPredioRegistro lapr_predioRegistro;
						int               li_secuenciaBng;

						lapr_predioRegistro = getAccPredioRegistroView();

						if(lapr_predioRegistro != null)
						{
							li_secuenciaBng = irr_calificacionRemote.accionSalvarView(
								    null, lacp_complementacionPredio, ls_tipoComplementacion, lcp_complementacionPredio,
								    lapr_predioRegistro, null, null, getIdTurnoHistoria(), getUserId(),
								    getRemoteIpAddress(), getLocalIpAddress()
								);

							if(lb_nuevaComplementacion)
							{
								if(li_secuenciaBng > 0)
								{
									{
										ComplementacionPredio lcp_complementacion;

										lcp_complementacion = lcc_complementacionCalificacion.getComplementacionPredio();

										if(lcp_complementacion != null)
											lcp_complementacion.setIdComplementacion(String.valueOf(li_secuenciaBng));
									}

									lapr_predioRegistro.setIdComplementacion(
									    NumericUtils.getLongWrapper(li_secuenciaBng)
									);
								}
								else
									throw new B2BException(ErrorKeys.COMPLEMENTACION_PREDIO_INSERT);
							}

							setComplementacionGuardada(true);

							if(ab_ajax)
								setComplementacionSinConstruir(false);
						}

						addMessage(MessagesKeys.INSERCION_EXITOSA);
					}
				}
			}
		}
		catch(B2BException lb2b_2b)
		{
			clh_LOGGER.error(lb2b_2b);
			addMessage(lb2b_2b);
		}
		finally
		{
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarComplementacion()
	    throws B2BException
	{
		guardarComplementacion(false);
	}

	/** {@inheritdoc} */
	public String handleReorder(DashboardReorderEvent aedre_re)
	    throws B2BException, IOException
	{
		List<String>         lls_idPanels;
		RegistroCalificacion lrc_tmp;
		String               ls_idWidget;
		String               ls_change;

		ls_idWidget      = aedre_re.getWidgetId();
		lls_idPanels     = getPanels();
		ls_change        = null;
		lrc_tmp          = new RegistroCalificacion();

		if(StringUtils.isValidString(ls_idWidget))
			ls_change = changePosition(lls_idPanels, ls_idWidget, -1);

		cargarDatosParametricosDireccion();

		try
		{
			if(StringUtils.isValidString(ls_change))
			{
				ls_idWidget     = generateId(ls_idWidget);
				ls_change       = generateId(ls_change);

				irr_calificacionRemote.modifyidAnotacion(ls_idWidget, ls_change, getUserId());

				idb_dataModel = new Dashboard();

				lrc_tmp.setIdCirculo(getAccPredioRegistro().getIdCirculo());
				lrc_tmp.setIdMatricula(getAccPredioRegistro().getIdMatricula());
				lrc_tmp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_tmp.setIdUsuario(getUserId());

				setDetalleAnotacion(irr_calificacionRemote.findDetalleMatriculas(lrc_tmp));

				consultaDetalleMatricula();

				Wizard wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot().findComponent(is_wizardId);

				wizard.setStep(is_tabAnotacionesWizardId);
				PrimeFaces.current().ajax().update(is_wizardId);
			}
		}
		catch(B2BException lb2b_2b)
		{
			clh_LOGGER.error(lb2b_2b);
			addMessage(lb2b_2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return NavegacionCommon.DETALLE_REGISTRO_CALIFICACION;
	}

	/** {@inheritdoc} */
	public void limpiarApertura()
	{
		Apertura la_apertura;

		la_apertura = getDatosBasicos().getApertura();

		la_apertura.setDocumento(null);
		la_apertura.setIdDepartamento(null);
		la_apertura.setIdMunicipio(null);
		la_apertura.setIdOficinaOrigen(null);
		la_apertura.setIdPais(null);
		la_apertura.setIdTipoEntidad(null);
		la_apertura.setIdTipoOficina(null);

		getDatosBasicos().setApertura(la_apertura);
	}

	/**
	 * Limpiar complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarComplementacion()
	    throws B2BException
	{
		limpiarComplementacion(false);
	}

	/**
	 * Limpiar complementacion.
	 *
	 * @param ab_primerVezDigitadorMasivo correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarComplementacion(boolean ab_primerVezDigitadorMasivo)
	    throws B2BException
	{
		AccPredioRegistro lapr_apr;

		lapr_apr = getAccPredioRegistroView();

		if(lapr_apr != null)
		{
			Collection<RegistroCalificacion> lcrc_allMatriculas;
			RegistroCalificacion             lrc_rc;

			lcrc_allMatriculas     = new ArrayList<RegistroCalificacion>();
			lrc_rc                 = new RegistroCalificacion();
			lrc_rc.setIdCirculo(lapr_apr.getIdCirculo() + "-" + lapr_apr.getIdMatricula());
			lcrc_allMatriculas.add(lrc_rc);
			lrc_rc.setAllMatriculas(lcrc_allMatriculas);

			setMatriculas(lrc_rc);
			super.limpiarComplementacionData(true, ab_primerVezDigitadorMasivo, false);
		}
	}

	/**
	 * Limpiar complementacion detalle.
	 */
	public void limpiarComplementacionDetalle()
	{
		getCabidaLinderos().setComplementacion(null);
	}

	/**
	 * Limpiar complementacion view.
	 */
	public void limpiarComplementacionView()
	{
		CabidaLinderos lcl_cl;
		lcl_cl = getCabidaLinderosView();

		if(lcl_cl != null)
		{
			lcl_cl.setComplementacion(null);
			lcl_cl.setIdCirculo(null);
			lcl_cl.setIdMatricula(null);
			lcl_cl.getComplementacionPredio().setIdComplementacion(null);
		}
	}

	/** {@inheritdoc} */
	public void limpiarLindero()
	    throws B2BException
	{
		CabidaLinderos lcl_cl;
		lcl_cl = getCabidaLinderosView();

		if(lcl_cl != null)
		{
			lcl_cl.setLinderos(null);
			lcl_cl.setIdCirculo(null);
			lcl_cl.setIdMatricula(null);
		}
	}

	/**
	 * Limpiar loteo.
	 */
	public void limpiarLoteo()
	{
		setCopiarComplementacion(null);
		setCopiarLinderos(null);
		setCopiarDireccion(null);
		setRangoComplementacion(false);
		setRangoDireccion(false);
		setRangoLinderos(false);
		setLinderoDisabled(false);
	}

	/**
	 * Modificar anotacion detalle.
	 *
	 * @param aui_childremPanel correspondiente al valor del tipo de objeto List<UIComponent>
	 * @param as_idPanel correspondiente al valor del tipo de objeto String
	 * @param as_revision correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotacionDetalle(List<UIComponent> aui_childremPanel, String as_idPanel, String as_revision)
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lorc_rc;

			lorc_rc = new RegistroCalificacion();

			lorc_rc.setIdAnotacionPredio(generateId(as_idPanel));
			lorc_rc.setUserId(getUserId());
			lorc_rc.setIdUsuario(getUserId());
			lorc_rc.setRevision(EstadoCommon.S);
			lorc_rc.setIdUsuarioModificacion(getUserId());

			irr_calificacionRemote.saveDetailAnotacion(lorc_rc);

			addMessage(MessagesKeys.REGISTRO_MODIFICADO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/** {@inheritdoc} */
	public void modificarArea(DetalleAreaPredio aadap_detalle)
	{
		if(aadap_detalle != null)
		{
			DetalleAreaPredio ladap_nuevo;

			ladap_nuevo = new DetalleAreaPredio();

			ladap_nuevo.setIdTipoArea(aadap_detalle.getIdTipoArea());
			ladap_nuevo.setArea(aadap_detalle.getArea());
			ladap_nuevo.setIdUnidadMedida(aadap_detalle.getIdUnidadMedida());
			ladap_nuevo.setIdDetalleAreaPredio(aadap_detalle.getIdDetalleAreaPredio());

			setDetalleAreaTerreno(ladap_nuevo);
			setBloquearAgregarAreaTerreno(true);
		}
	}

	/** {@inheritdoc} */
	public void modificarAreaTerreno()
	{
		try
		{
			AccAreaUI         laaui_dataArea;
			DetalleAreaPredio ladap_detalle;

			laaui_dataArea     = getAreaUI();
			ladap_detalle      = getDetalleAreaTerreno();

			if((laaui_dataArea != null) && (ladap_detalle != null))
			{
				String ls_idDetalleAreaPredio;

				ls_idDetalleAreaPredio = ladap_detalle.getIdDetalleAreaPredio();

				if(StringUtils.isValidString(ls_idDetalleAreaPredio))
				{
					Double       ld_area;
					boolean      lb_focus;
					FacesContext lfc_context;

					ld_area         = ladap_detalle.getArea();
					lb_focus        = false;
					lfc_context     = FacesContext.getCurrentInstance();
					lb_focus        = validateStyles(
						    ":fDetalleRegistroCalificacion:idITareaTerreno", lfc_context, StringUtils.getString(
						        ld_area
						    ), lb_focus
						);

					if(NumericUtils.isValidDouble(ld_area))
					{
						String ls_medidaArea;

						ls_medidaArea     = ladap_detalle.getIdUnidadMedida();
						lb_focus          = validateStyles(
							    ":fDetalleRegistroCalificacion:idSOMunidadMedidaTerreno", lfc_context, ls_medidaArea,
							    lb_focus
							);

						if(StringUtils.isValidString(ls_medidaArea))
						{
							Collection<DetalleAreaPredio> lcadap_areas;

							lcadap_areas = laaui_dataArea.getAreasTerreno();

							if(CollectionUtils.isValidCollection(lcadap_areas))
							{
								DetalleAreaPredio           ladap_areaEditar;
								HashMap<String, String>     lhmss_unidadesDeMedida;
								Iterator<DetalleAreaPredio> liadap_iterator;

								ladap_areaEditar           = null;
								lhmss_unidadesDeMedida     = new HashMap<String, String>();
								liadap_iterator            = lcadap_areas.iterator();

								while(liadap_iterator.hasNext())
								{
									DetalleAreaPredio ladap_areaTerreno;

									ladap_areaTerreno = liadap_iterator.next();

									if(ladap_areaTerreno != null)
									{
										String ls_idDetalleAreaPredioIterador;

										ls_idDetalleAreaPredioIterador = ladap_areaTerreno.getIdDetalleAreaPredio();

										if(StringUtils.isValidString(ls_idDetalleAreaPredioIterador))
										{
											String ls_medidaAreaIterador;

											ls_medidaAreaIterador = ladap_areaTerreno.getIdUnidadMedida();

											if(StringUtils.isValidString(ls_medidaAreaIterador))
											{
												if(
												    !ls_idDetalleAreaPredioIterador.equalsIgnoreCase(
													        ls_idDetalleAreaPredio
													    )
												)
													lhmss_unidadesDeMedida.put(
													    ls_idDetalleAreaPredioIterador, ls_medidaAreaIterador
													);
												else
													ladap_areaEditar = ladap_areaTerreno;
											}
											else
												throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
										}
									}
								}

								if(ladap_areaEditar != null)
								{
									if(lhmss_unidadesDeMedida.containsValue(ls_medidaArea))
									{
										MedidaArea lma_medidaArea;
										Object[]   aoa_messageArgs;
										String     ls_medidaAreaNombre;

										lma_medidaArea = ipr_parameterRemote.findMedidaAreaById(ls_medidaArea);

										if(lma_medidaArea != null)
										{
											ls_medidaAreaNombre     = lma_medidaArea.getDescripcion();
											aoa_messageArgs         = new String[1];
											aoa_messageArgs[0]      = ls_medidaAreaNombre;
											lb_focus                = validateStyles(
												    ":fDetalleRegistroCalificacion:idSOMunidadMedidaTerreno",
												    lfc_context, "", lb_focus
												);

											throw new B2BException(
											    ErrorKeys.ERROR_UNIDAD_MEDIDA_DUPLICADO, aoa_messageArgs
											);
										}
									}
									else
									{
										ladap_areaEditar.setIdUnidadMedida(ls_medidaArea);
										ladap_areaEditar.setArea(ld_area);
									}
								}
							}

							laaui_dataArea.setAreasTerreno(lcadap_areas);
							setAreaUI(laaui_dataArea);
							setDetalleAreaTerreno(null);
							setBloquearAgregarAreaTerreno(false);
							actualizarAreaTerreno();

							// Agregar mensaje de modificación exitosa.
							// addMessage(lb2be_e);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void modificarInterviniente(Anotacion aa_anotacion)
	{
		if(aa_anotacion != null)
		{
			Persona                  lp_persona;
			AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
			SolicitudInterviniente   lsi_solicitudInterviniente;

			lp_persona                        = aa_anotacion.getPersona();
			lapc_anotacionPredioCiudadano     = aa_anotacion.getAnotacionPredioCiudadano();
			lsi_solicitudInterviniente        = aa_anotacion.getSolicitudInterviniente();

			if(lp_persona != null)
				setPersona(lp_persona);

			if(lapc_anotacionPredioCiudadano != null)
				setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);

			if(lsi_solicitudInterviniente != null)
				setSolicitudInterviniente(lsi_solicitudInterviniente);
		}
	}

	/**
	 * Mostrar nueva linderos.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public boolean mostrarNuevaLinderos()
	{
		boolean lb_mostrarNueva;

		lb_mostrarNueva = false;

		try
		{
			TurnoHistoria  lth_turnoHstoria;
			CabidaLinderos lcl_cabida;

			lth_turnoHstoria     = new TurnoHistoria();
			lcl_cabida           = getCabidaLinderos();

			lth_turnoHstoria.setIdTurno(getIdTurno());

			if(lcl_cabida != null)
				lb_mostrarNueva = irr_calificacionRemote.esSegundaVezCalificacionLindero(lth_turnoHstoria)
					? (StringUtils.isValidString(lcl_cabida.getLinderos()) ? true : false) : false;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return lb_mostrarNueva;
	}

	/**
	 * On row edit.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 */
	public void onRowEdit(boolean ab_b)
	{
		DireccionDelPredio lddp_direccionSeleccionada;

		lddp_direccionSeleccionada = ab_b ? getDireccionSeleccionada() : getDireccionSeleccionadaView();

		if(lddp_direccionSeleccionada != null)
		{
			try
			{
				BeanDireccion lbd_beanDireccion;
				boolean       lb_correcciones;

				lbd_beanDireccion     = getBeanDireccion();
				lb_correcciones       = isEjecucionCorrecciones();

				lddp_direccionSeleccionada.setDireccionPredio(
				    actualizarDireccion(lddp_direccionSeleccionada.getDireccionPredio())
				);

				lddp_direccionSeleccionada = iasr_antiguoSistemaRemote.actualizarDireccionPredio(
					    lddp_direccionSeleccionada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				Collection<DireccionDelPredio> lcddp_coleccionTemp;
				Collection<DireccionDelPredio> lcddp_coleccionActual;

				lcddp_coleccionTemp       = new LinkedList<DireccionDelPredio>();
				lcddp_coleccionActual     = lb_correcciones ? lbd_beanDireccion.getDireccionesTemporales()
					                                        : (ab_b ? getDireccionesTemporales()
					                                                : getDireccionesTemporalesView());

				if(!CollectionUtils.isValidCollection(lcddp_coleccionActual))
					throw new B2BException(ErrorKeys.DIRECCION_PREDIO_NO_ENCONTRADA);

				for(DireccionDelPredio lddp_iterador : lcddp_coleccionActual)
				{
					if(
					    lddp_direccionSeleccionada.getDireccionPredio().getIdDireccionPredio()
						                              .equalsIgnoreCase(
						        lddp_iterador.getDireccionPredio().getIdDireccionPredio()
						    )
					)
						lcddp_coleccionTemp.add(lddp_direccionSeleccionada);
					else
						lcddp_coleccionTemp.add(lddp_iterador);
				}

				if(ab_b)
				{
					if(lb_correcciones)
					{
						lbd_beanDireccion.setDireccionesPredio2(lcddp_coleccionTemp);
						lbd_beanDireccion.setDireccionesTemporales(lbd_beanDireccion.getDireccionesPredio2());
					}

					setDireccionesPredio(lcddp_coleccionTemp);
					setDireccionesTemporales(getDireccionesPredio());
					setEditorTablaDirecciones(false);
				}
				else
				{
					setDireccionesPredioView(lcddp_coleccionTemp);
					setDireccionesTemporalesView(getDireccionesPredioView());
					setEditorTablaDireccionesView(false);
				}

				addMessage(MessagesKeys.DIRECCION_EDITADA);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update("fDetalleRegistroCalificacion");
			}
		}
	}

	/** {@inheritdoc} */
	public void pais()
	{
		getDatosBasicos().getApertura().setIdDepartamento(null);
		getDatosBasicos().getApertura().setIdMunicipio(null);
		findDepartamentos();
		findMunicipio();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public String regresar()
	{
		String           ls_return;
		FacesContext     lfc_context;
		BeanCalificacion lbde_bean;
		lfc_context     = FacesUtils.getFacesContext();
		lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
				                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);
		ls_return       = NavegacionCommon.CALIFICACION;

		if(lbde_bean != null)
		{
			try
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
			catch(B2BException e)
			{
				clh_LOGGER.error("regresar", e);
				ls_return = null;
			}
		}

		clear();

		return ls_return;
	}

	/**
	 * Método encargado de remover una dirección del predio.
	 *
	 * @param addp_direccion Objeto que contiene la dirección a remover.
	 * @throws B2BException
	 */
	public void removerDireccionPredio(DireccionDelPredio addp_direccion)
	    throws B2BException
	{
		try
		{
			if(addp_direccion != null)
			{
				BeanDireccion                  lbd_beanDireccion;
				Collection<DireccionDelPredio> lcd_direcciones;
				DireccionPredioAcc             ldpa_direccion;

				lbd_beanDireccion     = getBeanDireccion();
				lcd_direcciones       = lbd_beanDireccion.getDireccionesPredio2();
				ldpa_direccion        = addp_direccion.getDireccionPredio();

				if(ldpa_direccion != null)
					irr_calificacionRemote.eliminarDireccionPredioAcc(
					    ldpa_direccion.getIdDireccionPredio(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				lcd_direcciones.remove(addp_direccion);
				lbd_beanDireccion.setDireccionesPredio2(lcd_direcciones);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/** {@inheritdoc} */
	public void salvarAreaPredio()
	    throws B2BException
	{
		try
		{
			if(!isBloquearAgregarAreaTerreno())
			{
				AccAreaUI         laaui_data;
				AccPredioRegistro lapr_predioRegistro;

				laaui_data              = getAreaUI();
				lapr_predioRegistro     = getAccPredioRegistro();

				if((laaui_data != null) && (lapr_predioRegistro != null))
				{
					Collection<DetalleAreaPredio> lcadap_areasTerreno;

					lcadap_areasTerreno = laaui_data.getAreasTerreno();

					if(CollectionUtils.isValidCollection(lcadap_areasTerreno))
					{
						AccAreaPredio laap_areaPredio;
						boolean       lb_focus;
						FacesContext  lfc_context;
						String        ls_idTurno;

						laap_areaPredio     = laaui_data.getAreaPredio();
						lb_focus            = false;
						lfc_context         = FacesContext.getCurrentInstance();
						ls_idTurno          = getIdTurno();

						if(!StringUtils.isValidString(ls_idTurno))
							ls_idTurno = lapr_predioRegistro.getIdTurno();

						if(laap_areaPredio != null)
						{
							String ls_tipoUso;
							String ls_idCoeficiente;
							String ls_idAreaConstruida;
							String ls_idAreaPrivada;

							ls_tipoUso              = laap_areaPredio.getTipoSuelo();
							lb_focus                = validateStyles(
								    ":fDetalleRegistroCalificacion:idSOMtipoUsoSuelo", lfc_context, ls_tipoUso, lb_focus
								);
							ls_idCoeficiente        = ":fDetalleRegistroCalificacion:idITcoeficiente";
							ls_idAreaConstruida     = ":fDetalleRegistroCalificacion:idITareaConstruida";
							ls_idAreaPrivada        = ":fDetalleRegistroCalificacion:idITareaPrivada";

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

							if(irr_calificacionRemote.validarDatosArea(ls_idTurno))
							{
								{
									String ls_coeficiente;

									ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
									lb_focus           = validateStyles(
										    ls_idCoeficiente, lfc_context, ls_coeficiente, lb_focus
										);

									if(StringUtils.isValidString(ls_coeficiente))
									{
										Double ld_coeficiente;

										ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

										if(
										    NumericUtils.isValidDouble(
											        ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, false
											    )
										)
										{
											laap_areaPredio.setCoeficiente(ld_coeficiente);

											if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100d)) > 0)
											{
												lb_focus = validateStyles(
													    ls_idCoeficiente, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);

												throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
											}
										}
										else
										{
											lb_focus = validateStyles(
												    ls_idCoeficiente, lfc_context, IdentificadoresCommon.DATO_INVALIDO,
												    lb_focus
												);
											throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
								}

								{
									DetalleAreaPredio ldap_area;

									ldap_area = laaui_data.getDetalleAreaConstruida();

									if(ldap_area != null)
									{
										String ls_area;

										ls_area      = ldap_area.getAreaLectura();
										lb_focus     = validateStyles(
											    ls_idAreaConstruida, lfc_context, ls_area, lb_focus
											);

										if(StringUtils.isValidString(ls_area))
										{
											Double ld_area;

											ld_area = NumericUtils.getDoubleWrapper(ls_area);

											if(
											    NumericUtils.isValidDouble(
												        ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false
												    )
											)
												ldap_area.setArea(ld_area);
											else
											{
												lb_focus = validateStyles(
													    ls_idAreaConstruida, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);
												throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
									}
									else
									{
										lb_focus = validateStyles(
											    ls_idAreaConstruida, lfc_context, IdentificadoresCommon.DATO_INVALIDO,
											    lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
									}
								}

								{
									DetalleAreaPredio ldap_area;

									ldap_area = laaui_data.getDetalleAreaPrivada();

									if(ldap_area != null)
									{
										String ls_area;

										ls_area      = ldap_area.getAreaLectura();
										lb_focus     = validateStyles(ls_idAreaPrivada, lfc_context, ls_area, lb_focus);

										if(StringUtils.isValidString(ls_area))
										{
											Double ld_area;

											ld_area = NumericUtils.getDoubleWrapper(ls_area);

											if(
											    NumericUtils.isValidDouble(
												        ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false
												    )
											)
												ldap_area.setArea(ld_area);
											else
											{
												lb_focus = validateStyles(
													    ls_idAreaPrivada, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);
												throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
									else
									{
										lb_focus = validateStyles(
											    ls_idAreaPrivada, lfc_context, IdentificadoresCommon.DATO_INVALIDO,
											    lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
								}
							}
							else
							{
								{
									String ls_coeficiente;

									ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
									lb_focus           = validateStyles(
										    ls_idCoeficiente, lfc_context, ls_coeficiente, lb_focus
										);

									if(StringUtils.isValidString(ls_coeficiente))
									{
										Double ld_coeficiente;

										ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

										if(
										    NumericUtils.isValidDouble(
											        ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, false
											    )
										)
										{
											laap_areaPredio.setCoeficiente(ld_coeficiente);

											if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100d)) > 0)
											{
												lb_focus = validateStyles(
													    ls_idCoeficiente, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);

												throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
											}
										}
										else
										{
											lb_focus = validateStyles(
												    ls_idCoeficiente, lfc_context, IdentificadoresCommon.DATO_INVALIDO,
												    lb_focus
												);
											throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
										}
									}
								}

								{
									DetalleAreaPredio ldap_area;

									ldap_area = laaui_data.getDetalleAreaConstruida();

									if(ldap_area != null)
									{
										String ls_area;

										ls_area      = ldap_area.getAreaLectura();
										lb_focus     = validateStyles(
											    ls_idAreaConstruida, lfc_context, ls_area, lb_focus
											);

										if(StringUtils.isValidString(ls_area))
										{
											Double ld_area;

											ld_area = NumericUtils.getDoubleWrapper(ls_area);

											if(
											    NumericUtils.isValidDouble(
												        ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false
												    )
											)
												ldap_area.setArea(ld_area);
											else
											{
												lb_focus = validateStyles(
													    ls_idAreaConstruida, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);
												throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
											}
										}
									}
								}

								{
									DetalleAreaPredio ldap_area;

									ldap_area = laaui_data.getDetalleAreaPrivada();

									if(ldap_area != null)
									{
										String ls_area;

										ls_area      = ldap_area.getAreaLectura();
										lb_focus     = validateStyles(ls_idAreaPrivada, lfc_context, ls_area, lb_focus);

										if(StringUtils.isValidString(ls_area))
										{
											Double ld_area;

											ld_area = NumericUtils.getDoubleWrapper(ls_area);

											if(
											    NumericUtils.isValidDouble(
												        ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false
												    )
											)
												ldap_area.setArea(ld_area);
											else
											{
												lb_focus = validateStyles(
													    ls_idAreaPrivada, lfc_context,
													    IdentificadoresCommon.DATO_INVALIDO, lb_focus
													);
												throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
											}
										}
									}
								}
							}
						}

						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = lapr_predioRegistro.getIdCirculo();
							ll_idMatricula     = lapr_predioRegistro.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								laaui_data.setIdCirculo(ls_idCirculo);
								laaui_data.setIdMatricula(ll_idMatricula);
								laaui_data.setIdTurno(ls_idTurno);
								laaui_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

								irr_calificacionRemote.salvarAreaPredioEnglobes(
								    laaui_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/** {@inheritdoc} */
	public void salvarCabidaYLinderos()
	    throws B2BException
	{
		try
		{
			AccPredioRegistro        lapr_pr;
			boolean                  lb_accionNueva;
			boolean                  lb_correcciones;
			CabidaLinderos           lcl_cabidaLinderos;
			AccComplementacionPredio lacp_complementacionPredio;
			AccLinderoPredio         lalp_linderoPredio;
			ComplementacionPredio    lcp_complementacionPredio;
			DatosAntSistema          ldas_datosAntSistema;
			String                   ls_tipoComplementacion;

			lapr_pr                        = getAccPredioRegistro();
			lb_accionNueva                 = false;
			lb_correcciones                = isEjecucionCorrecciones();
			lcl_cabidaLinderos             = getCabidaLinderos();
			ldas_datosAntSistema           = getDatosAntiguoSistema();
			ls_tipoComplementacion         = lcl_cabidaLinderos.getTipoComplementacion();
			lacp_complementacionPredio     = new AccComplementacionPredio();
			lalp_linderoPredio             = new AccLinderoPredio();
			lcp_complementacionPredio      = lcl_cabidaLinderos.getComplementacionPredio();

			lalp_linderoPredio.setLindero(lcl_cabidaLinderos.getLinderos());
			lalp_linderoPredio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lalp_linderoPredio.setIdUsuarioCreacion(getUserId());
			lalp_linderoPredio.setIpCreacion(getLocalIpAddress());
			lalp_linderoPredio.setIdUsuarioModificacion(getUserId());
			lalp_linderoPredio.setIpModificacion(getLocalIpAddress());
			lalp_linderoPredio.setIdCirculo(lapr_pr.getIdCirculo());
			lalp_linderoPredio.setIdMatricula(lapr_pr.getIdMatricula());
			lalp_linderoPredio.setIdTurno(lapr_pr.getIdTurno());

			lacp_complementacionPredio.setComplementacion(lcl_cabidaLinderos.getComplementacion().getComplementacion());
			lacp_complementacionPredio.setIdUsuarioCreacion(getUserId());
			lacp_complementacionPredio.setIpCreacion(getLocalIpAddress());
			lacp_complementacionPredio.setIdUsuarioModificacion(getUserId());
			lacp_complementacionPredio.setIpModificacion(getLocalIpAddress());
			lacp_complementacionPredio.setIdTurno(getIdTurno());
			lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));
			lacp_complementacionPredio.setDevolucionDigitadorMasivo(isDevolucion() || lb_correcciones);

			if(StringUtils.isValidString(ls_tipoComplementacion))
			{
				if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
					lb_accionNueva = true;

				if(lb_accionNueva)
				{
					String ls_complementacion;

					ls_complementacion = lacp_complementacionPredio.getComplementacion();

					if(!StringUtils.isValidString(ls_complementacion) && lb_correcciones)
					{
						ls_complementacion = getObservaciones();

						lacp_complementacionPredio.setComplementacion(ls_complementacion);
					}

					lcp_complementacionPredio.setComplementacion(ls_complementacion);
					lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());
					lcp_complementacionPredio.setIpCreacion(getLocalIpAddress());
				}

				if(ldas_datosAntSistema != null)
					lcp_complementacionPredio.setIdDatosAntSistema(ldas_datosAntSistema.getIdDatosAntSistema());
			}

			lalp_linderoPredio.setDigitadorMasivo(true);
			lalp_linderoPredio.setLinderoNuevo(isNuevaLinderos());
			iasr_antiguoSistemaRemote.salvarCabidaYLinderos(
			    lalp_linderoPredio, lacp_complementacionPredio, lb_accionNueva, lcp_complementacionPredio, getUserId(),
			    getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar datos basicos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarDatosBasicos()
	    throws B2BException
	{
		try
		{
			DatosBasicos ldb_datosBasicos;
			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				{
					TurnoHistoria lth_turnoHistoria;
					Long          ll_idTurnoHistoria;

					lth_turnoHistoria      = new TurnoHistoria();
					ll_idTurnoHistoria     = NumericUtils.getLongWrapper(getIdTurnoHistoria());

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
						ldb_datosBasicos.setTurnoHistoria(lth_turnoHistoria);
					else
						ldb_datosBasicos.getTurnoHistoria().setIdTurnoHistoria(ll_idTurnoHistoria);
				}

				{
					Turno  lt_turno;
					String ls_idTurno;

					lt_turno       = new Turno();
					ls_idTurno     = getIdTurno();

					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = ier_entregaRemote.findTurnoById(lt_turno, getUserId());

					if(lt_turno != null)
						ldb_datosBasicos.setTurno(lt_turno);
					else
						ldb_datosBasicos.getTurno().setIdTurno(ls_idTurno);
				}

				ldb_datosBasicos = iasr_antiguoSistemaRemote.salvarDatosBasicos(
					    ldb_datosBasicos, getUserId(), getRemoteIpAddress()
					);

				setAccPredioRegistro(ldb_datosBasicos.getAccPredioRegistro());
				setDatosBasicos(ldb_datosBasicos);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/** {@inheritdoc} */
	public Collection<DireccionDelPredio> salvarDireccionPredio()
	    throws B2BException
	{
		Collection<DireccionDelPredio> lcddp_temp;

		lcddp_temp = getDireccionesPredio();

		try
		{
			if(CollectionUtils.isValidCollection(lcddp_temp))
				lcddp_temp = iasr_antiguoSistemaRemote.salvarDireccionPredio(
					    lcddp_temp, getIdTurnoHistoria(), getUserId(), getAccPredioRegistro(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);
			else
				throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);

			setDireccionesPredio(lcddp_temp);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}

		return lcddp_temp;
	}

	/** {@inheritdoc} */
	public void tipoOficina()
	{
		getDatosBasicos().getApertura().setIdPais(null);
		getDatosBasicos().getApertura().setIdDepartamento(null);
		getDatosBasicos().getApertura().setIdMunicipio(null);
		findTipoEntidadApertura();
		findPaises();
		findDepartamentos();
		findMunicipioApertura();
		getOficinaOrigen();
	}

	/** {@inheritdoc} */
	public void validarConstruir(boolean ab_eliminar)
	{
		try
		{
			super.validarConstruir(ab_eliminar);

			if(!ab_eliminar)
				guardarComplementacion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void validarCopiarDeUnaMatricula()
	    throws B2BException
	{
		super.validarCopiarDeUnaMatricula(true, false);
		guardarComplementacion();
	}

	/**
	 * Validar copiar de una matricula detalle.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCopiarDeUnaMatriculaDetalle()
	    throws B2BException
	{
		Complementacion lc_complementacion;
		lc_complementacion = getCabidaLinderos().getComplementacion();

		try
		{
			String ls_complementacion;

			if(lc_complementacion != null)
			{
				ComplementacionPredio lcp_complementacionPredio;
				lcp_complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

				if(lcp_complementacionPredio != null)
				{
					ls_complementacion = lcp_complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
						lc_complementacion.setComplementacion(ls_complementacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			lc_complementacion.setComplementacion(null);
		}
	}

	/**
	 * Validar copiar de una matricula view.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCopiarDeUnaMatriculaView()
	    throws B2BException
	{
		Complementacion lc_complementacion;
		lc_complementacion = getCabidaLinderosView().getComplementacion();

		try
		{
			String ls_complementacion;

			if(lc_complementacion != null)
			{
				ComplementacionPredio lcp_complementacionPredio;
				lcp_complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

				if(lcp_complementacionPredio != null)
				{
					ls_complementacion = lcp_complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
						lc_complementacion.setComplementacion(ls_complementacion);
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			clh_LOGGER.error(lb2b_e);
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			lc_complementacion.setComplementacion(null);
		}
	}

	/**
	 * Validar datos basicos.
	 *
	 * @param adb_datosBasicos correspondiente al valor del tipo de objeto DatosBasicos
	 */
	public void validarDatosBasicos(DatosBasicos adb_datosBasicos)
	{
		try
		{
			FacesContext lfc_context;
			lfc_context = FacesContext.getCurrentInstance();

			{
				String ll_idTipoPredio;
				ll_idTipoPredio = adb_datosBasicos.getUbicacion().getDepartamento().getIdDepartamento();

				if(!validateStyles(":fDetalleRegistroCalificacion:idSOMDepDirRe", lfc_context, ll_idTipoPredio, true))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/** {@inheritdoc} */
	public void validarExistenciaDocumento()
	    throws B2BException
	{
		try
		{
			ValidacionDocumento lvd_retorno;
			Documento           ld_documento;

			ld_documento = getDatosBasicos().getApertura().getDocumento();
			ld_documento.setIdTipoOficina(getDatosBasicos().getApertura().getIdTipoOficina());
			ld_documento.setIdOficinaOrigen(getDatosBasicos().getApertura().getIdOficinaOrigen());

			lvd_retorno = irr_registroRemote.validarExistenciaDocumento(ld_documento);

			if(lvd_retorno != null)
			{
				if(lvd_retorno.isValidacion())
				{
					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0] = lvd_retorno.getNir();

					throw new B2BException(ErrorKeys.ERROR_DATOS_DOCUMENTO_NIR, aoa_messageArgs);
				}
			}
		}
		catch(B2BException lb2b_e)
		{
			clh_LOGGER.error(lb2b_e);
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor de predio documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void getPredioDocumento()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ill_predioDocumento))
			ill_predioDocumento = iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.PREDIO
				);

		if(CollectionUtils.isValidCollection(ill_predioDocumento))
			lhm_predioDetalle = ill_predioDocumento.get(0);
	}

	public void consultaSGDAbrirPestana()
	{
		consultaSGD(OperadorCommon.OR);
	}
	
	/**
	 * Actualizar area terreno.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void actualizarAreaTerreno()
	    throws B2BException
	{
		AccAreaUI laaui_data;

		laaui_data = getAreaUI();

		if(laaui_data != null)
		{
			Collection<DetalleAreaPredio> lcadap_areas;

			lcadap_areas = laaui_data.getAreasTerreno();

			if(CollectionUtils.isValidCollection(lcadap_areas))
			{
				Map<String, String>         lhmss_medidasArea;
				Iterator<DetalleAreaPredio> liadap_iterator;
				StringBuilder               lsb_sb;

				lhmss_medidasArea     = irr_referenceRemote.findAllMedidaAreaNombres();
				liadap_iterator       = lcadap_areas.iterator();
				lsb_sb                = new StringBuilder();

				while(liadap_iterator.hasNext())
				{
					DetalleAreaPredio lacap_area;

					lacap_area = liadap_iterator.next();

					if(lacap_area != null)
					{
						String ls_separador;

						ls_separador = liadap_iterator.hasNext() ? ", " : IdentificadoresCommon.PUNTO;

						lsb_sb.append(
						    lacap_area.getArea() + " " + lhmss_medidasArea.get(lacap_area.getIdUnidadMedida())
						    + ls_separador
						);
					}
				}

				laaui_data.setAreaTerreno(lsb_sb.toString());
				setAreaUI(laaui_data);
			}
		}
	}

	/**
	 * Cargar datos area.
	 *
	 * @param lb_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosArea(boolean lb_accion)
	    throws B2BException
	{
		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		AccPredioRegistro lapr_predioRegistro;

		if(lb_accion)
			lapr_predioRegistro = getAccPredioRegistro();
		else
			lapr_predioRegistro = getAccPredioRegistroView();

		if(lapr_predioRegistro != null)
		{
			try
			{
				RegistroCalificacion lrc_data;
				String               ls_idTurno;

				lrc_data       = new RegistroCalificacion();
				ls_idTurno     = getIdTurno();

				if(!StringUtils.isValidString(ls_idTurno))
				{
					ls_idTurno = lapr_predioRegistro.getIdTurno();

					setIdTurno(ls_idTurno);
				}

				lrc_data.setIdCirculo(lapr_predioRegistro.getIdCirculo());
				lrc_data.setIdMatricula(lapr_predioRegistro.getIdMatricula());
				lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_data.setTurno(ls_idTurno);

				setAreaUI(irr_calificacionRemote.cargarDatosAreaEnglobes(lrc_data));
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				throw new B2BException(lb2be_e.getMessage());
			}
			finally
			{
				actualizarAreaTerreno();
			}
		}
	}

	private String construirDireccionCompleta(Direccion ad_direccion)
	{
		String ls_direccion;

		ls_direccion = null;

		if(ad_direccion != null)
		{
			Map<String, Map<String, String>> lmsmss_data;

			lmsmss_data = getDatosParametricosDireccion();

			if(CollectionUtils.isValidCollection(lmsmss_data))
			{
				boolean             lb_coordenada;
				boolean             lb_eje;
				boolean             lb_letra;
				Map<String, String> lmss_coordenada;
				Map<String, String> lmss_eje;
				Map<String, String> lmss_letra;

				lmss_coordenada     = lmsmss_data.get(IdentificadoresCommon.COORDENADA);
				lmss_eje            = lmsmss_data.get(IdentificadoresCommon.EJE);
				lmss_letra          = lmsmss_data.get(IdentificadoresCommon.LETRA);
				lb_coordenada       = CollectionUtils.isValidCollection(lmss_coordenada);
				lb_eje              = CollectionUtils.isValidCollection(lmss_eje);
				lb_letra            = CollectionUtils.isValidCollection(lmss_letra);

				if(lb_coordenada || lb_eje || lb_letra)
				{
					StringBuilder lsb_direccionCompleta;
					String        ls_espacio;

					lsb_direccionCompleta     = new StringBuilder();
					ls_espacio                = IdentificadoresCommon.ESPACIO_VACIO;

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdTipoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getDatoEjePrincipal();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdLetraEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdComplementoEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdCoordenadaEjePrincipal();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdTipoEjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getDatoEjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdLetra1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdComplemento1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdCoordenada1EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getDato2EjeSecundario();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdLetra2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_letra && lmss_letra.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_letra.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdComplemento2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdCoordenada2EjeSecundario();

						if(StringUtils.isValidString(ls_dato) && lb_coordenada && lmss_coordenada.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_coordenada.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getIdComplementoDireccion();

						if(StringUtils.isValidString(ls_dato) && lb_eje && lmss_eje.containsKey(ls_dato))
							lsb_direccionCompleta.append(lmss_eje.get(ls_dato) + ls_espacio);
					}

					{
						String ls_dato;

						ls_dato = ad_direccion.getComplementoDireccion();

						if(StringUtils.isValidString(ls_dato))
							lsb_direccionCompleta.append(ls_dato + ls_espacio);
					}

					ls_direccion = lsb_direccionCompleta.toString();
				}
			}
		}

		return ls_direccion;
	}
}
