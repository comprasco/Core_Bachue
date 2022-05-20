package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.excel.ExcelUtils;

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
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoActoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.ActualizarDatosAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.AmpliacionHistoriaRegistral;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistema;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleIntervinientes;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanDetallePredio;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanDetalleRegistroCalificacion;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.ColumnModel;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;
import com.bachue.snr.prosnr01.web.util.UIUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.component.tabview.TabView;

import org.primefaces.component.wizard.Wizard;

import org.primefaces.context.RequestContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAntSistema.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanAntSistema")
public class BeanAntSistema extends BeanDetallePredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4184038563309609837L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAntSistema.class);

	/** Constante is_idFormulario. */
	private static final String is_idFormulario = "idFormAntSistema";

	/** Constante is_idFormularioGrabar. */
	private static final String is_idFormularioGrabar = "fGrabarMatricula";

	/** Constante is_idFormularioDetalleGrabar. */
	private static final String is_idFormularioDetalleGrabar = "fDetalleGrabacion";

	/** Constante is_idFormulario_calificacion. */
	private static final String is_idFormulario_calificacion = "fRegistroCalif";

	/** Constante is_mensajes. */
	private static final String is_mensajes = ":globalMsg";

	/** Constante cs_idPanelMatriculasSegregadas. */
	private static final String cs_idPanelMatriculasSegregadas = "idPanelMatriculasSegregadas";

	/** Constante cs_idPanelInterviniente. */
	private static final String cs_idPanelInterviniente = "idPanelInterviniente";

	/** Constante cs_idPanelEspecificacion. */
	private static final String cs_idPanelEspecificacion = "idPanelEspecificacion";

	/** Constante cs_idPanelDatosAntSistema. */
	private static final String cs_idPanelDatosAntSistema = "idPanelDatosAntSistema";

	/** Constante cs_idPanelDatosDocumento. */
	private static final String cs_idPanelDatosDocumento = "idPanelDatosDocumento";

	/** Constante cs_idPanelDatosAnotacion. */
	private static final String cs_idPanelDatosAnotacion = "idPanelDatosAnotacion";

	/** Constante is_idFormulario_ant_sistema. */
	private static final String is_idFormulario_ant_sistema = is_idFormulario + ":idProcesoAntiguoSistema";

	/** Constante is_mensajesCalificacionGrowl. */
	private static final String is_mensajesCalificacionGrowl = is_idFormulario_calificacion + is_mensajes;

	/** Constante is_mensajesIdGrowl. */
	private static final String is_mensajesIdGrowl = is_idFormulario + is_mensajes;

	/** Constante is_mensajesGrabarIdGrowl. */
	private static final String is_mensajesGrabarIdGrowl = is_idFormularioGrabar + is_mensajes;

	/** Constante cs_nombreTabDatosBasicos. */
	private static final String cs_nombreTabDatosBasicos = "datosBasicos_id";

	/** Propiedad ier entrega remote. */
	@EJB
	protected EntregaRemote ier_entregaRemote;

	/** Propiedad iacp complementacion predio. */
	private AccComplementacionPredio iacp_complementacionPredio;

	/** Propiedad iahr ampliacion historia registral. */
	private AmpliacionHistoriaRegistral iahr_ampliacionHistoriaRegistral;

	/** Propiedad ia anotacion bng. */
	private Anotacion ia_anotacionBng;

	/** Propiedad ia anotacion temporal. */
	private Anotacion ia_anotacionTemporal;

	/** Propiedad ia interviniente actual. */
	private Anotacion ia_intervinienteActual;

	/** Propiedad iac anotacion cancelacion. */
	private AnotacionCancelacion iac_anotacionCancelacion;

	/** Propiedad iap anotacion predio. */
	private AnotacionPredio iap_anotacionPredio;

	/** Propiedad iapc anotacion predio ciudadano. */
	private AnotacionPredioCiudadano iapc_anotacionPredioCiudadano;

	/** Propiedad iapc anotacion predio ciudadano bng. */
	private AnotacionPredioCiudadano iapc_anotacionPredioCiudadanoBng;

	/** Propiedad iasd antiguo sistema data. */
	private AntiguoSistemaData iasd_antiguoSistemaData;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad lbr bean registro. */
	private BeanRegistro lbr_beanRegistro;

	/** Propiedad ibd orden anotacion. */
	private BigDecimal ibd_ordenAnotacion;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ic motivos. */
	private Collection<MotivoTramite> ic_motivos;

	/** Propiedad ic motivos. */
	private Collection<MotivoTramite> ic_motivosTemporales;

	/** Propiedad ica anotaciones agregadas. */
	private Collection<Anotacion> ica_anotacionesAgregadas;

	/** Propiedad ica anotaciones agregadas bng. */
	private Collection<Anotacion> ica_anotacionesAgregadasBng;

	/** Propiedad ica intervinientes agregados. */
	private Collection<Anotacion> ica_intervinientesAgregados;

	/** Propiedad ica intervinientes agregados bng. */
	private Collection<Anotacion> ica_intervinientesAgregadosBng;

	/** Propiedad ica matriculas segregadas. */
	private Collection<Anotacion> ica_matriculasSegregadas;

	/** Propiedad icadas actualizar datos ant sistema. */
	private Collection<ActualizarDatosAntSistema> icadas_actualizarDatosAntSistema;

	/** Propiedad icadas actualizar datos ant sistema 2. */
	private Collection<ActualizarDatosAntSistema> icadas_actualizarDatosAntSistema2;

	/** Propiedad icadas procesos. */
	private Collection<ActualizarDatosAntSistema> icadas_procesos;

	/** Propiedad icadas tipos actualizar datos ant sistema 101. */
	private Collection<ActualizarDatosAntSistema> icadas_tiposActualizarDatosAntSistema101;

	/** Propiedad icapr datos turno matricula. */
	private Collection<AccPredioRegistro> icapr_datosTurnoMatricula;

	/** Propiedad icca complementacion anotacion. */
	private Collection<ComplementacionAnotacion> icca_complementacionAnotacion;

	/** Propiedad icdas datos ant sistema. */
	private Collection<DatosAntSistema> icdas_datosAntSistema;

	/** Propiedad icdas detalles ant sistema bng. */
	private Collection<DetalleAntSistema> icdas_detallesAntSistemaBng;

	/** Propiedad icdcpc data consulta por criterio ant sistema. */
	private Collection<DataConsultaPorCriterio> icdcpc_dataConsultaPorCriterioAntSistema;

	/** Propiedad icdcpc data consulta por criterio documento. */
	private Collection<DataConsultaPorCriterio> icdcpc_dataConsultaPorCriterioDocumento;

	/** Propiedad icdp direcciones. */
	private Collection<DireccionPredio> icdp_direcciones;

	/** Propiedad icds documentos salida. */
	private Collection<DocumentosSalida> icds_documentosSalida;

	/** Propiedad icmb matriculas para generar complementacion. */
	private Collection<MatriculaBase> icmb_matriculasParaGenerarComplementacion;

	/** Propiedad icp listado intervinientes. */
	private Collection<Persona> icp_listadoIntervinientes;

	/** Propiedad icpaui cpui. */
	private Collection<PredioActoIU> icpaui_cpui;

	/** Propiedad icrc anotacion A cancelar. */
	private Collection<RegistroCalificacion> icrc_anotacionACancelar;

	/** Propiedad icrc anotaciones proceso. */
	private Collection<RegistroCalificacion> icrc_anotacionesProceso;

	/** Propiedad ics nombre predios sin revision. */
	private Collection<String> ics_nombrePrediosSinRevision;

	/** Propiedad icsc archivos cargados. */
	private Collection<StreamedContent> icsc_archivosCargados;

	/** Propiedad icsc matriculas correccion. */
	private Collection<SolicitudMatricula> icsc_matriculasCorreccion;

	/** Propiedad ictc detalle tipo causal. */
	private Collection<TipoCausal> ictc_detalleTipoCausal;

	/** Propiedad icto tipos oficina doc. */
	private Collection<TipoOficina> icto_tiposOficinaDoc;

	/** Propiedad icto tipos oficina doc detalle ant. */
	private Collection<TipoOficina> icto_tiposOficinaDocDetalleAnt;

	/** Propiedad illlhmso matriculas. */
	private Collection<Map<String, Object>> illlhmso_matriculasValidacion;

	/** Propiedad icc complementacion calificacion. */
	private ComplementacionCalificacion icc_complementacionCalificacion;

	/** Propiedad icccd consulta criterios calificacion documento. */
	private ConsultaCriteriosCalificacionDocumento icccd_consultaCriteriosCalificacionDocumento;

	/** Propiedad icdd consulta datos documento. */
	private ConsultaDatosDocumento icdd_consultaDatosDocumento;

	/** Propiedad idcpc matricula documento seleccionada. */
	private DataConsultaPorCriterio idcpc_matriculaDocumentoSeleccionada;

	/** Propiedad idas consulta datos ant sistema. */
	private DatosAntSistema idas_consultaDatosAntSistema;

	/** Propiedad idas datos ant sistema anotacion. */
	private DatosAntSistema idas_datosAntSistemaAnotacion;

	/** Propiedad idas datos antiguo sistema. */
	private DatosAntSistema idas_datosAntiguoSistema;

	/** Propiedad idas detalle ant sistema anotacion. */
	private DetalleAntSistema idas_detalleAntSistemaAnotacion;

	/** Propiedad idasu detalle ant sistema consulta. */
	private DetalleAntSistemaUI idasu_detalleAntSistemaConsulta;

	/** Propiedad iadap detalle area terreno. */
	private DetalleAreaPredio iadap_detalleAreaTerreno;

	/** Propiedad idas digitador antiguo sistema remote. */
	@EJB
	private DigitadorAntiguoSistemaRemote idas_digitadorAntiguoSistemaRemote;

	/** Propiedad idd documento data. */
	private DocumentoData idd_documentoData;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad ilrc lindero registro calificacion. */
	private LinderoRegistroCalificacion ilrc_linderoRegistroCalificacion;

	/** Propiedad ilmso info antiguo sistema. */
	private List<Map<String, Object>> ilmso_infoAntiguoSistema;

	/** Propiedad ilmso info documentos. */
	private List<Map<String, Object>> ilmso_infoDocumentos;

	/** Propiedad ilmso matriculas. */
	private List<Map<String, Object>> ilmso_matriculas;

	/** Propiedad ilmso predio documento. */
	private List<Map<String, Object>> ilmso_predioDocumento;

	/** Propiedad iltc causales final. */
	private List<TipoCausal> iltc_causalesFinal;

	/** Propiedad iltc filtro tipos causales. */
	private List<TipoCausal> iltc_filtroTiposCausales;

	/** Propiedad iltc tipos causales filtro. */
	private List<TipoCausal> iltc_tiposCausalesFiltro;

	/** Propiedad il numero anotacion. */
	private Long il_numeroAnotacion;

	/** Propiedad il numero orden. */
	private Long il_numeroOrden;

	/** Propiedad imsb bytes cargados. */
	private Map<String, byte[]> imsb_bytesCargados;

	/** Propiedad imso datos del turno. */
	private Map<String, Object> imso_datosDelTurno;

	/** Propiedad ipdi panel detalle intervinientes. */
	private PanelDetalleIntervinientes ipdi_panelDetalleIntervinientes;

	/** Propiedad ipdi panel detalle intervinientes bng. */
	private PanelDetalleIntervinientes ipdi_panelDetalleIntervinientesBng;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona bng. */
	private Persona ip_personaBng;

	/** Propiedad ipaui matricula seleccionada para eliminar. */
	private PredioActoIU ipaui_matriculaSeleccionadaParaEliminar;

	/** Propiedad ips predio segregado. */
	private PredioSegregado ips_predioSegregado;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad iorc matriculas. */
	private RegistroCalificacion iorc_matriculas;

	/** Propiedad iorc matriculas A heredar. */
	private RegistroCalificacion iorc_matriculasAHeredar;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isi solicitud interviniente. */
	private SolicitudInterviniente isi_solicitudInterviniente;

	/** Propiedad isi solicitud interviniente bng. */
	private SolicitudInterviniente isi_solicitudIntervinienteBng;

	/** Propiedad isc archivo pdf. */
	private StreamedContent isc_archivoPdf;

	/** Propiedad isc file. */
	private StreamedContent isc_file;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc imagen visor. */
	private StreamedContent isc_imagenVisor;

	/** Propiedad isc resultado cargue intervinientes. */
	private StreamedContent isc_resultadoCargueIntervinientes;

	/** Propiedad is codigo naturaleza juridica seleccionada. */
	private String is_codigoNaturalezaJuridicaSeleccionada;

	/** Propiedad is complementacion. */
	private String is_complementacion;

	/** Propiedad is consideraciones. */
	private String is_consideraciones;

	/** Propiedad is documento interviniente. */
	private String is_documentoInterviniente;

	/** Propiedad is formulario. */
	private String is_formulario;

	/** Propiedad is habilitar cargue resultados. */
	private String is_habilitarCargueResultados;

	/** Propiedad is id circulo proceso. */
	private String is_idCirculoProceso;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id persona seleccion. */
	private String is_idPersonaSeleccion;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is justificacion firma. */
	private String is_justificacionFirma;

	/** Propiedad is motivo tramite. */
	private String is_motivoTramite;

	/** Propiedad is naturaleza juridica seleccionada. */
	private String is_naturalezaJuridicaSeleccionada;

	/** Propiedad is numero matricula eliminar. */
	private String is_numeroMatriculaEliminar;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones calificador. */
	private String is_observacionesCalificador;

	/** Propiedad is observaciones rechazo. */
	private String is_observacionesRechazo;

	/** Propiedad is pregunta masivo intervinientes. */
	private String is_preguntaMasivoIntervinientes;

	/** Propiedad is tipo actualizar. */
	private String is_tipoActualizar;

	/** Propiedad is tipo consulta. */
	private String is_tipoConsulta;

	/** Propiedad is tipo consulta manual. */
	private String is_tipoConsultaManual;

	/** Propiedad is tipo doc interviniente. */
	private String is_tipoDocInterviniente;

	/** Propiedad ls rango fin. */
	private String ls_rangoFin;

	/** Propiedad ls rango inicio. */
	private String ls_rangoInicio;

	/** Propiedad ls seccion tipo matricula. */
	private String ls_seccionTipoMatricula;

	/** Propiedad iba archivo pdf descarga. */
	private byte[] iba_archivoPdfDescarga;

	/** propiedad i actoConEjecutoria. */
	private boolean ib_actoConEjecutoria;

	/** Propiedad ib actualizar. */
	private boolean ib_actualizar;

	/** Propiedad ib actualizar datos ant sistema. */
	private boolean ib_actualizarDatosAntSistema;

	/** Propiedad ib anotacion agregada. */
	private boolean ib_anotacionAgregada;

	/** Propiedad ib anotacion apertura. */
	private boolean ib_anotacionApertura;

	/** Propiedad ib anotacion englobe. */
	private boolean ib_anotacionEnglobe;

	/** Propiedad ib ant sistema consulta. */
	private boolean ib_antSistemaConsulta;

	/** Propiedad ib ant sistema consulta exitosa. */
	private boolean ib_antSistemaConsultaExitosa;

	/** Propiedad ib asociar matricula ant sistema. */
	private boolean ib_asociarMatriculaAntSistema;

	/** Propiedad ib bloquear agregar area terreno. */
	private boolean ib_bloquearAgregarAreaTerreno;

	/** Propiedad ib bloquear modificar anotaciones. */
	private boolean ib_bloquearModificarAnotaciones;

	/** Propiedad ib bloquear modificar intervenientes. */
	private boolean ib_bloquearModificarIntervenientes;

	/** Propiedad ib complementacion sin construir. */
	private boolean ib_complementacionSinConstruir;

	/** Propiedad ib consulta realizada. */
	private boolean ib_consultaRealizada;

	/** Propiedad ib crear matricula ant sistema. */
	private boolean ib_crearMatriculaAntSistema;

	/** Propiedad ib crear matriculas existentes. */
	private boolean ib_crearMatriculasExistentes;

	/** Propiedad ib datos ant sistema iguales. */
	private boolean ib_datosAntSistemaIguales;

	/** Propiedad ib deshabilitar calidad interviniente. */
	private boolean ib_deshabilitarCalidadInterviniente;

	/** Propiedad ib deshabilitar campos por nit. */
	private boolean ib_deshabilitarCamposPorNit;

	/** Propiedad ib deshabilitar datos antiguo sistema. */
	private boolean ib_deshabilitarDatosAntiguoSistema;

	/** Propiedad ib deshabilitar datos doc. */
	private boolean ib_deshabilitarDatosDoc;

	/** Propiedad ib deshabilitar datos interviniente. */
	private boolean ib_deshabilitarDatosInterviniente;

	/** Propiedad ib deshabilitar numero anotacion. */
	private boolean ib_deshabilitarNumeroAnotacion;

	/** Propiedad ib documento consulta. */
	private boolean ib_documentoConsulta;

	/** Propiedad ib documento consulta exitosa. */
	private boolean ib_documentoConsultaExitosa;

	/** Atributo ib_documentosEnviados */
	private boolean ib_documentosEnviados;

	/** Propiedad ib editar interviniente. */
	private boolean ib_editarInterviniente;

	/** Propiedad ib edito complementacion. */
	private boolean ib_editoComplementacion;

	/** Propiedad ib etapa 101. */
	private boolean ib_etapa101;

	/** Propiedad ib etapa 102. */
	private boolean ib_etapa102;

	/** Propiedad ib etapa 105. */
	private boolean ib_etapa105;

	/** Propiedad ib grabacion de matriculas. */
	private boolean ib_grabacionDeMatriculas;

	/** Propiedad ib habilitar cargues. */
	private boolean ib_habilitarCargues;

	/** Propiedad ib habilitar complementacion. */
	private boolean ib_habilitarComplementacion;

	/** Propiedad ib habilitar completitud. */
	private boolean ib_habilitarCompletitud;

	/** Propiedad ib habilitar matriculas complementacion anotacion. */
	private boolean ib_habilitarMatriculasComplementacionAnotacion;

	/** Propiedad ib habilitar secuencia. */
	private boolean ib_habilitarSecuencia;

	/** Propiedad ib informe de busqueda ant sistema. */
	private boolean ib_informeDeBusquedaAntSistema;

	/** Propiedad ib informe de busqueda ant sistema 2. */
	private boolean ib_informeDeBusquedaAntSistema2;

	/** Propiedad ib libro justificacion requiere. */
	private boolean ib_libroJustificacionRequiere;

	/** Propiedad ib modificar complementacion. */
	private boolean ib_modificarComplementacion;

	/** Propiedad ib mostrar. */
	private boolean ib_mostrar;

	/** Propiedad ib mostrar anotacion cancela. */
	private boolean ib_mostrarAnotacionCancela;

	/** Propiedad ib mostrar atras crear grabar. */
	private boolean ib_mostrarAtrasCrearGrabar;

	/** Propiedad ib mostrar bandeja. */
	private boolean ib_mostrarBandeja;

	/** Propiedad ib mostrar listado personas. */
	private boolean ib_mostrarListadoPersonas;

	/** Propiedad ib mostrar panel actos. */
	private boolean ib_mostrarPanelMotivos;

	/** Propiedad ib mostrar rechazar. */
	private boolean ib_mostrarRechazar;

	/** Propiedad ib mostrar regresar englobes. */
	private boolean ib_mostrarRegresarEnglobes;

	/** Propiedad ib mostrar siguiente englobes. */
	private boolean ib_mostrarSiguienteEnglobes;

	/** Propiedad ib no informe busqueda. */
	private boolean ib_noInformeBusqueda;

	/** Propiedad ib ocultar boton siguiente. */
	private boolean ib_ocultarBotonSiguiente;

	/** Propiedad ib ocultar siguiente crear grabar. */
	private boolean ib_ocultarSiguienteCrearGrabar;

	/** Propiedad ib pdf generado. */
	private boolean ib_pdfGenerado;

	/** Propiedad ib predio seleccionado. */
	private boolean ib_predioSeleccionado;

	/** Propiedad ib primer vez. */
	private boolean ib_primerVez;

	/** Propiedad ib proceso certificados. */
	private boolean ib_procesoCertificados;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_procesoCorrecciones;

	/** Propiedad ib proceso terminado asociar matricula. */
	private boolean ib_procesoTerminadoAsociarMatricula;

	/** Propiedad ib proceso terminado crear grabar. */
	private boolean ib_procesoTerminadoCrearGrabar;

	/** Propiedad ib proceso terminado informe busqueda. */
	private boolean ib_procesoTerminadoInformeBusqueda;

	/** Propiedad ib rendered habilita secuencia. */
	private boolean ib_renderedHabilitaSecuencia;

	/** Propiedad ib rendered interviniente acto. */
	private boolean ib_renderedIntervinienteActo;

	/** Propiedad ib revisado ant sistema. */
	private boolean ib_revisadoAntSistema;

	/** Propiedad ib revision definitiva completa. */
	private boolean ib_revisionDefinitivaCompleta;

	/** Propiedad ib revision predios completa. */
	private boolean ib_revisionPrediosCompleta;

	/** Propiedad ib secuencia agregada. */
	private boolean ib_secuenciaAgregada;

	/** Propiedad is ant sistema consulta no. */
	private boolean is_antSistemaConsultaNo;

	/** Propiedad is devolucion etapa 110. */
	private boolean is_devolucionEtapa110;

	/** Propiedad il contador anotaciones. */
	private long il_contadorAnotaciones;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/** Propiedad il id anotacion general. */
	private long il_idAnotacionGeneral;

	/** Propiedad il id anotacion para eliminar. */
	private long il_idAnotacionParaEliminar;

	/**
	 * Instancia un nuevo objeto bean ant sistema.
	 */
	public BeanAntSistema()
	{
		ib_deshabilitarNumeroAnotacion = false;
	}

	/**
	 * Método de asignación del valor de is_actoConEjecutoria.
	 *
	 * @param ab_aAtoConEjecutoria de ab a ato con ejecutoria
	 */
	public void setActoConEjecutoria(boolean ab_aAtoConEjecutoria)
	{
		ib_actoConEjecutoria = ab_aAtoConEjecutoria;
	}

	/**
	 * metodo de obtención del valor de ib_actoConEjecutoria.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en acto con ejecutoria
	 */
	public boolean isActoConEjecutoria()
	{
		return ib_actoConEjecutoria;
	}

	/**
	 * Modifica el valor de actos asociados general.
	 *
	 * @param aaagcpaui_aagcpaui asigna el valor a la propiedad actos asociados general
	 */
	public void setActosAsociadosGeneral(Collection<PredioActoIU> aaagcpaui_aagcpaui)
	{
		icpaui_cpui = aaagcpaui_aagcpaui;
	}

	/**
	 * Retorna el valor de actos asociados general.
	 *
	 * @return el valor de actos asociados general
	 */
	public Collection<PredioActoIU> getActosAsociadosGeneral()
	{
		return icpaui_cpui;
	}

	/**
	 * Modifica el valor de actualizar.
	 *
	 * @param ab_b asigna el valor a la propiedad actualizar
	 */
	public void setActualizar(boolean ab_b)
	{
		ib_actualizar = ab_b;
	}

	/**
	 * Valida la propiedad actualizar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en actualizar
	 */
	public boolean isActualizar()
	{
		return ib_actualizar;
	}

	/**
	 * Modifica el valor de actualizar datos ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad actualizar datos ant sistema
	 */
	public void setActualizarDatosAntSistema(boolean ab_b)
	{
		ib_actualizarDatosAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad actualizar datos ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en actualizar datos ant sistema
	 */
	public boolean isActualizarDatosAntSistema()
	{
		return ib_actualizarDatosAntSistema;
	}

	/**
	 * Retorna el valor de ampliacion historia registral.
	 *
	 * @return el valor de ampliacion historia registral
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public AmpliacionHistoriaRegistral getAmpliacionHistoriaRegistral()
	    throws B2BException
	{
		if(iahr_ampliacionHistoriaRegistral == null)
		{
			iahr_ampliacionHistoriaRegistral = new AmpliacionHistoriaRegistral();

			Long ll_idTurnoHistoria;
			ll_idTurnoHistoria = NumericUtils.getLongWrapper(getIdTurnoHistoria());

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
			{
				iahr_ampliacionHistoriaRegistral.setIdTurnoHistoria(ll_idTurnoHistoria);

				AccPredioRegistro lapr_accPredioRegistro;
				lapr_accPredioRegistro = new AccPredioRegistro();
				lapr_accPredioRegistro.setIdTurnoHistoria(ll_idTurnoHistoria);

				lapr_accPredioRegistro = iasr_antiguoSistemaRemote.findAccPredioRegistroByTurnoHistoria(
					    lapr_accPredioRegistro
					);

				if(lapr_accPredioRegistro != null)
				{
					iahr_ampliacionHistoriaRegistral.setAccPredioRegistro(lapr_accPredioRegistro);

					Long   ll_idMatricula;
					String ls_idCirculo;

					ll_idMatricula     = lapr_accPredioRegistro.getIdMatricula();
					ls_idCirculo       = lapr_accPredioRegistro.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
						iahr_ampliacionHistoriaRegistral.setDataMatriculaCreadaGrabada(
						    ls_idCirculo + " - " + ll_idMatricula
						);
				}
			}
		}

		return iahr_ampliacionHistoriaRegistral;
	}

	/**
	 * Modifica el valor de anotacion A cancelar.
	 *
	 * @param acrc_rc asigna el valor a la propiedad anotacion A cancelar
	 */
	public void setAnotacionACancelar(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_anotacionACancelar = acrc_rc;
	}

	/**
	 * Retorna el valor de anotacion A cancelar.
	 *
	 * @return el valor de anotacion A cancelar
	 */
	public Collection<RegistroCalificacion> getAnotacionACancelar()
	{
		return icrc_anotacionACancelar;
	}

	/**
	 * Modifica el valor de anotacion agregada.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion agregada
	 */
	public void setAnotacionAgregada(boolean ab_b)
	{
		ib_anotacionAgregada = ab_b;
	}

	/**
	 * Valida la propiedad anotacion agregada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion agregada
	 */
	public boolean isAnotacionAgregada()
	{
		return ib_anotacionAgregada;
	}

	/**
	 * @param Modifica el valor de la propiedad anotacionApertura por anotacionApertura
	 */
	public void setAnotacionApertura(boolean ab_b)
	{
		ib_anotacionApertura = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad anotacionApertura
	 */
	public boolean isAnotacionApertura()
	{
		return ib_anotacionApertura;
	}

	/**
	 * Modifica el valor de anotacion bng.
	 *
	 * @param ia_a asigna el valor a la propiedad anotacion bng
	 */
	public void setAnotacionBng(Anotacion ia_a)
	{
		ia_anotacionBng = ia_a;
	}

	/**
	 * Retorna el valor de anotacion bng.
	 *
	 * @return el valor de anotacion bng
	 */
	public Anotacion getAnotacionBng()
	{
		return ia_anotacionBng;
	}

	/**
	 * Modifica el valor de anotacion cancelacion.
	 *
	 * @param aac_ac asigna el valor a la propiedad anotacion cancelacion
	 */
	public void setAnotacionCancelacion(AnotacionCancelacion aac_ac)
	{
		iac_anotacionCancelacion = aac_ac;
	}

	/**
	 * Retorna el valor de anotacion cancelacion.
	 *
	 * @return el valor de anotacion cancelacion
	 */
	public AnotacionCancelacion getAnotacionCancelacion()
	{
		if(iac_anotacionCancelacion == null)
			iac_anotacionCancelacion = new AnotacionCancelacion();

		return iac_anotacionCancelacion;
	}

	/**
	 * @param Modifica el valor de la propiedad anotacionEnglobe por anotacionEnglobe
	 */
	public void setAnotacionEnglobe(boolean ab_b)
	{
		ib_anotacionEnglobe = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad anotacionEnglobe
	 */
	public boolean isAnotacionEnglobe()
	{
		return ib_anotacionEnglobe;
	}

	/**
	 * Modifica el valor de anotacion predio.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio
	 */
	public void setAnotacionPredio(AnotacionPredio aap_ap)
	{
		iap_anotacionPredio = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio.
	 *
	 * @return el valor de anotacion predio
	 */
	public AnotacionPredio getAnotacionPredio()
	{
		if(iap_anotacionPredio == null)
		{
			iap_anotacionPredio = new AnotacionPredio();
			iap_anotacionPredio.setIdEstadoAnotacion(EstadoCommon.V);
		}

		return iap_anotacionPredio;
	}

	/**
	 * Modifica el valor de anotacion predio ciudadano.
	 *
	 * @param aapc_apc asigna el valor a la propiedad anotacion predio ciudadano
	 */
	public void setAnotacionPredioCiudadano(AnotacionPredioCiudadano aapc_apc)
	{
		iapc_anotacionPredioCiudadano = aapc_apc;
	}

	/**
	 * Retorna el valor de anotacion predio ciudadano.
	 *
	 * @return el valor de anotacion predio ciudadano
	 */
	public AnotacionPredioCiudadano getAnotacionPredioCiudadano()
	{
		if(iapc_anotacionPredioCiudadano == null)
			iapc_anotacionPredioCiudadano = new AnotacionPredioCiudadano();

		return iapc_anotacionPredioCiudadano;
	}

	/**
	 * Modifica el valor de anotacion predio ciudadano bng.
	 *
	 * @param aapc_apc asigna el valor a la propiedad anotacion predio ciudadano bng
	 */
	public void setAnotacionPredioCiudadanoBng(AnotacionPredioCiudadano aapc_apc)
	{
		iapc_anotacionPredioCiudadanoBng = aapc_apc;
	}

	/**
	 * Retorna el valor de anotacion predio ciudadano bng.
	 *
	 * @return el valor de anotacion predio ciudadano bng
	 */
	public AnotacionPredioCiudadano getAnotacionPredioCiudadanoBng()
	{
		if(iapc_anotacionPredioCiudadanoBng == null)
			iapc_anotacionPredioCiudadanoBng = new AnotacionPredioCiudadano();

		return iapc_anotacionPredioCiudadanoBng;
	}

	/**
	 * Modifica el valor de AnotacionTemporal.
	 *
	 * @param aa_a de aa a
	 */
	public void setAnotacionTemporal(Anotacion aa_a)
	{
		ia_anotacionTemporal = aa_a;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion temporal.
	 *
	 * @return Retorna el valor de la propiedad anotacionTemporal
	 */
	public Anotacion getAnotacionTemporal()
	{
		return ia_anotacionTemporal;
	}

	/**
	 * Modifica el valor de anotaciones agregadas.
	 *
	 * @param aca_ca asigna el valor a la propiedad anotaciones agregadas
	 */
	public void setAnotacionesAgregadas(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadas = aca_ca;
	}

	/**
	 * Retorna el valor de anotaciones agregadas.
	 *
	 * @return el valor de anotaciones agregadas
	 */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return ica_anotacionesAgregadas;
	}

	/**
	 * Modifica el valor de anotaciones agregadas bng.
	 *
	 * @param aca_ca asigna el valor a la propiedad anotaciones agregadas bng
	 */
	public void setAnotacionesAgregadasBng(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadasBng = aca_ca;
	}

	/**
	 * Retorna el valor de anotaciones agregadas bng.
	 *
	 * @return el valor de anotaciones agregadas bng
	 */
	public Collection<Anotacion> getAnotacionesAgregadasBng()
	{
		return ica_anotacionesAgregadasBng;
	}

	/**
	 * Modifica el valor de anotaciones proceso.
	 *
	 * @param acrc_crc asigna el valor a la propiedad anotaciones proceso
	 */
	public void setAnotacionesProceso(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_anotacionesProceso = acrc_crc;
	}

	/**
	 * Retorna el valor de anotaciones proceso.
	 *
	 * @return el valor de anotaciones proceso
	 */
	public Collection<RegistroCalificacion> getAnotacionesProceso()
	{
		return icrc_anotacionesProceso;
	}

	/**
	 * Valida la propiedad anotaciones validas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotaciones validas
	 */
	public boolean isAnotacionesValidas()
	{
		return CollectionUtils.isValidCollection(getAnotacionesAgregadas());
	}

	/**
	 * Modifica el valor de ant sistema consulta.
	 *
	 * @param ab_b asigna el valor a la propiedad ant sistema consulta
	 */
	public void setAntSistemaConsulta(boolean ab_b)
	{
		ib_antSistemaConsulta = ab_b;
	}

	/**
	 * Valida la propiedad ant sistema consulta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ant sistema consulta
	 */
	public boolean isAntSistemaConsulta()
	{
		return ib_antSistemaConsulta;
	}

	/**
	 * Modifica el valor de ant sistema consulta exitosa.
	 *
	 * @param ab_b asigna el valor a la propiedad ant sistema consulta exitosa
	 */
	public void setAntSistemaConsultaExitosa(boolean ab_b)
	{
		ib_antSistemaConsultaExitosa = ab_b;
	}

	/**
	 * Valida la propiedad ant sistema consulta exitosa.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ant sistema consulta exitosa
	 */
	public boolean isAntSistemaConsultaExitosa()
	{
		return ib_antSistemaConsultaExitosa;
	}

	/**
	 * Modifica el valor de ant sistema consulta no.
	 *
	 * @param ab_b asigna el valor a la propiedad ant sistema consulta no
	 */
	public void setAntSistemaConsultaNo(boolean ab_b)
	{
		is_antSistemaConsultaNo = ab_b;
	}

	/**
	 * Valida la propiedad ant sistema consulta no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ant sistema consulta no
	 */
	public boolean isAntSistemaConsultaNo()
	{
		return is_antSistemaConsultaNo;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de archivo pdf.
	 *
	 * @param asc_sc asigna el valor a la propiedad archivo pdf
	 */
	public void setArchivoPdf(StreamedContent asc_sc)
	{
		isc_archivoPdf = asc_sc;
	}

	/**
	 * Retorna el valor de archivo pdf.
	 *
	 * @return el valor de archivo pdf
	 */
	public StreamedContent getArchivoPdf()
	{
		return isc_archivoPdf;
	}

	/**
	 * Modifica el valor de archivo pdf descarga.
	 *
	 * @param aba_ba asigna el valor a la propiedad archivo pdf descarga
	 */
	public void setArchivoPdfDescarga(byte[] aba_ba)
	{
		iba_archivoPdfDescarga = aba_ba;
	}

	/**
	 * Retorna el valor de archivo pdf descarga.
	 *
	 * @return el valor de archivo pdf descarga
	 */
	public byte[] getArchivoPdfDescarga()
	{
		return iba_archivoPdfDescarga;
	}

	/**
	 * Modifica el valor de archivos cargados.
	 *
	 * @param acsc_csc asigna el valor a la propiedad archivos cargados
	 */
	public void setArchivosCargados(Collection<StreamedContent> acsc_csc)
	{
		icsc_archivosCargados = acsc_csc;
	}

	/**
	 * Retorna el valor de archivos cargados.
	 *
	 * @return el valor de archivos cargados
	 */
	public Collection<StreamedContent> getArchivosCargados()
	{
		return icsc_archivosCargados;
	}

	/**
	 * Modifica el valor de asociar matricula ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad asociar matricula ant sistema
	 */
	public void setAsociarMatriculaAntSistema(boolean ab_b)
	{
		ib_asociarMatriculaAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad asociar matricula ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en asociar matricula ant sistema
	 */
	public boolean isAsociarMatriculaAntSistema()
	{
		return ib_asociarMatriculaAntSistema;
	}

	/**
	 * Retorna el valor de bean registro.
	 *
	 * @return el valor de bean registro
	 */
	public BeanRegistro getBeanRegistro()
	{
		if(lbr_beanRegistro == null)
		{
			FacesContext lfc_context;

			lfc_context     = FacesContext.getCurrentInstance();

			lbr_beanRegistro = (BeanRegistro)lfc_context.getApplication()
					                                        .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_REGISTRO, BeanRegistro.class
					);
			lbr_beanRegistro.iniciar();
		}

		return lbr_beanRegistro;
	}

	/**
	 * Modifica el valor de bloquear agregar area terreno.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear agregar area terreno
	 */
	public void setBloquearAgregarAreaTerreno(boolean ab_b)
	{
		ib_bloquearAgregarAreaTerreno = ab_b;
	}

	/**
	 * Valida la propiedad bloquear agregar area terreno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear agregar area terreno
	 */
	public boolean isBloquearAgregarAreaTerreno()
	{
		return ib_bloquearAgregarAreaTerreno;
	}

	/**
	 * Modifica el valor de bloquear modificar anotaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear modificar anotaciones
	 */
	public void setBloquearModificarAnotaciones(boolean ab_b)
	{
		ib_bloquearModificarAnotaciones = ab_b;
	}

	/**
	 * Valida la propiedad bloquear modificar anotaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear modificar anotaciones
	 */
	public boolean isBloquearModificarAnotaciones()
	{
		return ib_bloquearModificarAnotaciones;
	}

	/**
	 * Modifica el valor de bloquear modificar intervenientes.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear modificar intervenientes
	 */
	public void setBloquearModificarIntervenientes(boolean ab_b)
	{
		ib_bloquearModificarIntervenientes = ab_b;
	}

	/**
	 * Valida la propiedad bloquear modificar intervenientes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear modificar intervenientes
	 */
	public boolean isBloquearModificarIntervenientes()
	{
		return ib_bloquearModificarIntervenientes;
	}

	/**
	 * Sets the bytes cargados.
	 *
	 * @param amsb_mlb correspondiente al valor del tipo de objeto Map<String,byte[]>
	 */
	public void setBytesCargados(Map<String, byte[]> amsb_mlb)
	{
		imsb_bytesCargados = amsb_mlb;
	}

	/**
	 * Retorna el valor de bytes cargados.
	 *
	 * @return el valor de bytes cargados
	 */
	public Map<String, byte[]> getBytesCargados()
	{
		return imsb_bytesCargados;
	}

	/**
	 * Modifica el valor de causales final.
	 *
	 * @param altc_ltc asigna el valor a la propiedad causales final
	 */
	public void setCausalesFinal(List<TipoCausal> altc_ltc)
	{
		iltc_causalesFinal = altc_ltc;
	}

	/**
	 * Retorna el valor de causales final.
	 *
	 * @return el valor de causales final
	 */
	public List<TipoCausal> getCausalesFinal()
	{
		return iltc_causalesFinal;
	}

	/**
	 * Modifica el valor de codigo naturaleza juridica seleccionada.
	 *
	 * @param as_s asigna el valor a la propiedad codigo naturaleza juridica seleccionada
	 */
	public void setCodigoNaturalezaJuridicaSeleccionada(String as_s)
	{
		is_codigoNaturalezaJuridicaSeleccionada = as_s;
	}

	/**
	 * Retorna el valor de codigo naturaleza juridica seleccionada.
	 *
	 * @return el valor de codigo naturaleza juridica seleccionada
	 */
	public String getCodigoNaturalezaJuridicaSeleccionada()
	{
		return is_codigoNaturalezaJuridicaSeleccionada;
	}

	/**
	 * Método encargado de consultar los actos asociados a la solicitud actual y cargarlos de forma dinamica en pantalla.
	 *
	 * @return el valor de columns
	 */
	public List<ColumnModel> getColumns()
	{
		Collection<TipoActo> lca_actosSolicitud;
		List<ColumnModel>    llcm_columns;
		String               ls_solicitud;

		llcm_columns     = new ArrayList<ColumnModel>();
		ls_solicitud     = findSolicitudByIdTurnoHistoria(getIdTurnoHistoria());

		if(StringUtils.isValidString(ls_solicitud))
		{
			Acto la_param;

			la_param = new Acto();

			la_param.setIdSolicitud(ls_solicitud);
			la_param.setIdCirculo(getIdCirculo());
			la_param.setVieneDeAntiguoSistema(true);

			lca_actosSolicitud = findActosByIdSolicitudCirculo(la_param, getUserId());

			if(CollectionUtils.isValidCollection(lca_actosSolicitud))
			{
				Iterator<TipoActo> lia_actos;

				lia_actos = lca_actosSolicitud.iterator();

				while(lia_actos.hasNext())
				{
					TipoActo la_actos;

					la_actos = lia_actos.next();

					if(la_actos != null)
						llcm_columns.add(
						    new ColumnModel(
						        la_actos.getIdTipoActo(), Boolean.FALSE, la_actos.getNombre(),
						        conversionCientifica(NumericUtils.getDoubleWrapper(la_actos.getIdTipoCalculo())),
						        la_actos.getIdUsuarioCreacion(), ls_solicitud
						    )
						);
				}
			}
		}

		return llcm_columns;
	}

	/**
	 * Modifica el valor de complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad complementacion
	 */
	public void setComplementacion(String as_s)
	{
		is_complementacion = as_s;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @return el valor de complementacion
	 */
	public String getComplementacion()
	{
		return is_complementacion;
	}

	/**
	 * Modifica el valor de complementacion anotacion.
	 *
	 * @param complementacionAnotacion asigna el valor a la propiedad complementacion anotacion
	 */
	public void setComplementacionAnotacion(Collection<ComplementacionAnotacion> complementacionAnotacion)
	{
		icca_complementacionAnotacion = complementacionAnotacion;
	}

	/**
	 * Retorna el valor de complementacion anotacion.
	 *
	 * @return el valor de complementacion anotacion
	 */
	public Collection<ComplementacionAnotacion> getComplementacionAnotacion()
	{
		return icca_complementacionAnotacion;
	}

	/**
	 * Modifica el valor de complementacion calificacion.
	 *
	 * @param complementacionCalificacion asigna el valor a la propiedad complementacion calificacion
	 */
	public void setComplementacionCalificacion(ComplementacionCalificacion complementacionCalificacion)
	{
		icc_complementacionCalificacion = complementacionCalificacion;
	}

	/**
	 * Retorna el valor de complementacion calificacion.
	 *
	 * @return el valor de complementacion calificacion
	 */
	public ComplementacionCalificacion getComplementacionCalificacion()
	{
		if(icc_complementacionCalificacion == null)
			icc_complementacionCalificacion = new ComplementacionCalificacion();

		return icc_complementacionCalificacion;
	}

	/**
	 * Modifica el valor de complementacion predio.
	 *
	 * @param aacp_acp asigna el valor a la propiedad complementacion predio
	 */
	public void setComplementacionPredio(AccComplementacionPredio aacp_acp)
	{
		iacp_complementacionPredio = aacp_acp;
	}

	/**
	 * Retorna el valor de complementacion predio.
	 *
	 * @return el valor de complementacion predio
	 */
	public AccComplementacionPredio getComplementacionPredio()
	{
		return iacp_complementacionPredio;
	}

	/**
	 * Modifica el valor de complementacion sin construir.
	 *
	 * @param ab_b asigna el valor a la propiedad complementacion sin construir
	 */
	public void setComplementacionSinConstruir(boolean ab_b)
	{
		ib_complementacionSinConstruir = ab_b;
	}

	/**
	 * Valida la propiedad complementacion sin construir.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en complementacion sin construir
	 */
	public boolean isComplementacionSinConstruir()
	{
		return ib_complementacionSinConstruir;
	}

	/**
	 * @param Modifica el valor de la propiedad is_consideraciones por is_consideraciones
	 */
	public void setConsideraciones(String is_consideraciones)
	{
		this.is_consideraciones = is_consideraciones;
	}

	/**
	 * @return Retorna el valor de la propiedad is_consideraciones
	 */
	public String getConsideraciones()
	{
		return is_consideraciones;
	}

	/**
	 * Modifica el valor de consulta criterios calificacion documento.
	 *
	 * @param acccd_cccd asigna el valor a la propiedad consulta criterios calificacion documento
	 */
	public void setConsultaCriteriosCalificacionDocumento(ConsultaCriteriosCalificacionDocumento acccd_cccd)
	{
		icccd_consultaCriteriosCalificacionDocumento = acccd_cccd;
	}

	/**
	 * Retorna el valor de consulta criterios calificacion documento.
	 *
	 * @return el valor de consulta criterios calificacion documento
	 */
	public ConsultaCriteriosCalificacionDocumento getConsultaCriteriosCalificacionDocumento()
	{
		if(icccd_consultaCriteriosCalificacionDocumento == null)
			icccd_consultaCriteriosCalificacionDocumento = new ConsultaCriteriosCalificacionDocumento();

		return icccd_consultaCriteriosCalificacionDocumento;
	}

	/**
	 * Modifica el valor de consulta datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad consulta datos ant sistema
	 */
	public void setConsultaDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_consultaDatosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de consulta datos ant sistema.
	 *
	 * @return el valor de consulta datos ant sistema
	 */
	public DatosAntSistema getConsultaDatosAntSistema()
	{
		if(idas_consultaDatosAntSistema == null)
			idas_consultaDatosAntSistema = new DatosAntSistema();

		return idas_consultaDatosAntSistema;
	}

	/**
	 * Modifica el valor de consulta datos documento.
	 *
	 * @param acdd_cdd asigna el valor a la propiedad consulta datos documento
	 */
	public void setConsultaDatosDocumento(ConsultaDatosDocumento acdd_cdd)
	{
		icdd_consultaDatosDocumento = acdd_cdd;
	}

	/**
	 * Retorna el valor de consulta datos documento.
	 *
	 * @return el valor de consulta datos documento
	 */
	public ConsultaDatosDocumento getConsultaDatosDocumento()
	{
		if(icdd_consultaDatosDocumento == null)
			icdd_consultaDatosDocumento = new ConsultaDatosDocumento();

		return icdd_consultaDatosDocumento;
	}

	/**
	 * Modifica la propiedad Consulta realizada.
	 *
	 * @param ab_b con el nuevo valor de la propiedad
	 */
	public void setConsultaRealizada(boolean ab_b)
	{
		ib_consultaRealizada = ab_b;
	}

	/**
	 * Valida la propiedad Consulta Realizada.
	 *
	 * @return El valor de la propiedad
	 */
	public boolean isConsultaRealizada()
	{
		return ib_consultaRealizada;
	}

	/**
	 * Modifica el valor de contador anotaciones.
	 *
	 * @param al_l asigna el valor a la propiedad contador anotaciones
	 */
	public void setContadorAnotaciones(long al_l)
	{
		il_contadorAnotaciones = al_l;
	}

	/**
	 * Retorna el valor de contador anotaciones.
	 *
	 * @return el valor de contador anotaciones
	 */
	public long getContadorAnotaciones()
	{
		return il_contadorAnotaciones;
	}

	/**
	 * Modifica el valor de crear matricula ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad crear matricula ant sistema
	 */
	public void setCrearMatriculaAntSistema(boolean ab_b)
	{
		ib_crearMatriculaAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad crear matricula ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en crear matricula ant sistema
	 */
	public boolean isCrearMatriculaAntSistema()
	{
		return ib_crearMatriculaAntSistema;
	}

	/**
	 * Modifica el valor de crear matriculas existentes.
	 *
	 * @param ab_b asigna el valor a la propiedad crear matriculas existentes
	 */
	public void setCrearMatriculasExistentes(boolean ab_b)
	{
		ib_crearMatriculasExistentes = ab_b;
	}

	/**
	 * Valida la propiedad crear matriculas existentes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en crear matriculas existentes
	 */
	public boolean isCrearMatriculasExistentes()
	{
		return ib_crearMatriculasExistentes;
	}

	/**
	 * Modifica el valor de data antiguo sistema.
	 *
	 * @param antiguoSistemaData asigna el valor a la propiedad data antiguo sistema
	 */
	public void setDataAntiguoSistema(AntiguoSistemaData antiguoSistemaData)
	{
		iasd_antiguoSistemaData = antiguoSistemaData;
	}

	/**
	 * Retorna el valor de data antiguo sistema.
	 *
	 * @return el valor de data antiguo sistema
	 */
	public AntiguoSistemaData getDataAntiguoSistema()
	{
		return iasd_antiguoSistemaData;
	}

	/**
	 * Modifica el valor de data consulta por criterio ant sistema.
	 *
	 * @param acdcpc_cdcpc asigna el valor a la propiedad data consulta por criterio ant sistema
	 */
	public void setDataConsultaPorCriterioAntSistema(Collection<DataConsultaPorCriterio> acdcpc_cdcpc)
	{
		icdcpc_dataConsultaPorCriterioAntSistema = acdcpc_cdcpc;
	}

	/**
	 * Retorna el valor de data consulta por criterio ant sistema.
	 *
	 * @return el valor de data consulta por criterio ant sistema
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaPorCriterioAntSistema()
	{
		return icdcpc_dataConsultaPorCriterioAntSistema;
	}

	/**
	 * Modifica el valor de data consulta por criterio documento.
	 *
	 * @param acdcpc_cdcpc asigna el valor a la propiedad data consulta por criterio documento
	 */
	public void setDataConsultaPorCriterioDocumento(Collection<DataConsultaPorCriterio> acdcpc_cdcpc)
	{
		icdcpc_dataConsultaPorCriterioDocumento = acdcpc_cdcpc;
	}

	/**
	 * Retorna el valor de data consulta por criterio documento.
	 *
	 * @return el valor de data consulta por criterio documento
	 */
	public Collection<DataConsultaPorCriterio> getDataConsultaPorCriterioDocumento()
	{
		return icdcpc_dataConsultaPorCriterioDocumento;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(Collection<DatosAntSistema> acdas_cdas)
	{
		icdas_datosAntSistema = acdas_cdas;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public Collection<DatosAntSistema> getDatosAntSistema()
	{
		return icdas_datosAntSistema;
	}

	/**
	 * Modifica el valor de datos ant sistema anotacion.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema anotacion
	 */
	public void setDatosAntSistemaAnotacion(DatosAntSistema adas_das)
	{
		idas_datosAntSistemaAnotacion = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema anotacion.
	 *
	 * @return el valor de datos ant sistema anotacion
	 */
	public DatosAntSistema getDatosAntSistemaAnotacion()
	{
		if(idas_datosAntSistemaAnotacion == null)
		{
			try
			{
				String          ls_idTurnoHistoria;
				DatosAntSistema ldas_datosAntSistema;

				ls_idTurnoHistoria = getIdTurnoHistoria();

				if(StringUtils.isValidString(ls_idTurnoHistoria))
				{
					ldas_datosAntSistema = iasr_antiguoSistemaRemote.findDatosAntSistema(
						    NumericUtils.getLongWrapper(ls_idTurnoHistoria)
						);

					if(ldas_datosAntSistema != null)
						idas_datosAntSistemaAnotacion = ldas_datosAntSistema;
					else
						idas_datosAntSistemaAnotacion = new DatosAntSistema();
				}
				else
					idas_datosAntSistemaAnotacion = new DatosAntSistema();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("getDatosAntSistemaAnotacion", lb2be_e);
			}
		}

		return idas_datosAntSistemaAnotacion;
	}

	/**
	 * Modifica el valor de datos ant sistema iguales.
	 *
	 * @param ab_b asigna el valor a la propiedad datos ant sistema iguales
	 */
	public void setDatosAntSistemaIguales(boolean ab_b)
	{
		ib_datosAntSistemaIguales = ab_b;
	}

	/**
	 * Valida la propiedad datos ant sistema iguales.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos ant sistema iguales
	 */
	public boolean isDatosAntSistemaIguales()
	{
		return ib_datosAntSistemaIguales;
	}

	/**
	 * Modifica el valor de datos antiguo sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos antiguo sistema
	 */
	public void setDatosAntiguoSistema(DatosAntSistema adas_das)
	{
		idas_datosAntiguoSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos antiguo sistema.
	 *
	 * @return el valor de datos antiguo sistema
	 */
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
	 * Sets the datos del turno.
	 *
	 * @param ahm_hm correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setDatosDelTurno(Map<String, Object> ahm_hm)
	{
		imso_datosDelTurno = ahm_hm;
	}

	/**
	 * Retorna el valor de datos del turno.
	 *
	 * @return el valor de datos del turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<String, Object> getDatosDelTurno()
	    throws B2BException
	{
		if(imso_datosDelTurno == null)
			getPredioDocumento();

		return imso_datosDelTurno;
	}

	/**
	 * Modifica el valor de datos turno matricula.
	 *
	 * @param acapr_capr asigna el valor a la propiedad datos turno matricula
	 */
	public void setDatosTurnoMatricula(Collection<AccPredioRegistro> acapr_capr)
	{
		icapr_datosTurnoMatricula = acapr_capr;
	}

	/**
	 * Retorna el valor de datos turno matricula.
	 *
	 * @return el valor de datos turno matricula
	 */
	public Collection<AccPredioRegistro> getDatosTurnoMatricula()
	{
		try
		{
			Collection<AccPredioRegistro> lcpr_pr;
			AccPredioRegistro             lpr_pr;

			lpr_pr = new AccPredioRegistro();

			lpr_pr.setIdTurno(getIdTurno());
			lpr_pr.setIdDatosAntSistema(getIdDatosAntSistemaActual());

			lcpr_pr = idas_digitadorAntiguoSistemaRemote.findAccMatriculasByTurnoAntSistema(lpr_pr, getUserId());

			if(
			    CollectionUtils.isValidCollection(lcpr_pr)
				    && !CollectionUtils.isValidCollection(icapr_datosTurnoMatricula)
			)
			{
				icapr_datosTurnoMatricula = lcpr_pr;
				setMostrarBandeja(true);
				setMostrar(false);
			}
			else if(!CollectionUtils.isValidCollection(lcpr_pr))
			{
				icapr_datosTurnoMatricula = null;
				setMostrarBandeja(false);
				setMostrar(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getDatosTurnoMatricula", lb2be_e);
			icapr_datosTurnoMatricula = null;
		}

		return icapr_datosTurnoMatricula;
	}

	/**
	 * Modifica el valor de deshabilitar calidad interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar calidad interviniente
	 */
	public void setDeshabilitarCalidadInterviniente(boolean ab_b)
	{
		ib_deshabilitarCalidadInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar calidad interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar calidad interviniente
	 */
	public boolean isDeshabilitarCalidadInterviniente()
	{
		return ib_deshabilitarCalidadInterviniente;
	}

	/**
	 * Modifica el valor de deshabilitar campos por nit.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar campos por nit
	 */
	public void setDeshabilitarCamposPorNit(boolean ab_b)
	{
		ib_deshabilitarCamposPorNit = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar campos por nit.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar campos por nit
	 */
	public boolean isDeshabilitarCamposPorNit()
	{
		return ib_deshabilitarCamposPorNit;
	}

	/**
	 * Modifica el valor de deshabilitar datos antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar datos antiguo sistema
	 */
	public void setDeshabilitarDatosAntiguoSistema(boolean ab_b)
	{
		ib_deshabilitarDatosAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar datos antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar datos antiguo sistema
	 */
	public boolean isDeshabilitarDatosAntiguoSistema()
	{
		return ib_deshabilitarDatosAntiguoSistema;
	}

	/**
	 * Modifica el valor de deshabilitar datos doc.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar datos doc
	 */
	public void setDeshabilitarDatosDoc(boolean ab_b)
	{
		ib_deshabilitarDatosDoc = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar datos doc.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar datos doc
	 */
	public boolean isDeshabilitarDatosDoc()
	{
		return ib_deshabilitarDatosDoc;
	}

	/**
	 * Modifica el valor de deshabilitar datos interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar datos interviniente
	 */
	public void setDeshabilitarDatosInterviniente(boolean ab_b)
	{
		ib_deshabilitarDatosInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar datos interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar datos interviniente
	 */
	public boolean isDeshabilitarDatosInterviniente()
	{
		return ib_deshabilitarDatosInterviniente;
	}

	/**
	 * Modifica el valor de deshabilitar numero anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar numero anotacion
	 */
	public void setDeshabilitarNumeroAnotacion(boolean ab_b)
	{
		ib_deshabilitarNumeroAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar numero anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar numero anotacion
	 */
	public boolean isDeshabilitarNumeroAnotacion()
	{
		return ib_deshabilitarNumeroAnotacion;
	}

	/**
	 * Modifica el valor de detalle ant sistema anotacion.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sistema anotacion
	 */
	public void setDetalleAntSistemaAnotacion(DetalleAntSistema adas_das)
	{
		idas_detalleAntSistemaAnotacion = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sistema anotacion.
	 *
	 * @return el valor de detalle ant sistema anotacion
	 */
	public DetalleAntSistema getDetalleAntSistemaAnotacion()
	{
		return idas_detalleAntSistemaAnotacion;
	}

	/**
	 * Modifica el valor de detalle ant sistema consulta.
	 *
	 * @param adasu_dasu asigna el valor a la propiedad detalle ant sistema consulta
	 */
	public void setDetalleAntSistemaConsulta(DetalleAntSistemaUI adasu_dasu)
	{
		idasu_detalleAntSistemaConsulta = adasu_dasu;
	}

	/**
	 * Retorna el valor de detalle ant sistema consulta.
	 *
	 * @return el valor de detalle ant sistema consulta
	 */
	public DetalleAntSistemaUI getDetalleAntSistemaConsulta()
	{
		try
		{
			if(idasu_detalleAntSistemaConsulta == null)
			{
				idasu_detalleAntSistemaConsulta = new DetalleAntSistemaUI();

				Documento     ld_documento;
				OficinaOrigen loo_oficina;
				String        ls_idCirculo;

				ld_documento     = new Documento();
				loo_oficina      = new OficinaOrigen();
				ls_idCirculo     = getIdCirculoProceso();

				loo_oficina.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
				ld_documento.setOficinaOrigen(loo_oficina);
				idasu_detalleAntSistemaConsulta.setDocumentoConsulta(ld_documento);

				if(!StringUtils.isValidString(ls_idCirculo))
				{
					String ls_idTurno;

					ls_idTurno = getIdTurno();

					if(ls_idTurno != null)
					{
						Turno lt_turno;

						lt_turno = irr_calificacionRemote.findTurno(ls_idTurno);

						if(lt_turno != null)
							ls_idCirculo = lt_turno.getIdCirculo();
					}
				}

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ZonaRegistral lzr_zonaRegistral;

					idasu_detalleAntSistemaConsulta.setIdCirculo(ls_idCirculo);

					lzr_zonaRegistral = irr_registroRemote.findDatosZonaRegistralByCirculo(
						    ls_idCirculo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lzr_zonaRegistral != null)
						idasu_detalleAntSistemaConsulta.setIdDepartamentoTomo(lzr_zonaRegistral.getIdDepartamento());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			idasu_detalleAntSistemaConsulta = new DetalleAntSistemaUI();
			clh_LOGGER.error("getDetalleAntSistemaConsulta", lb2be_e);
		}

		return idasu_detalleAntSistemaConsulta;
	}

	/**
	 * Modifica el valor de detalle area terreno.
	 *
	 * @param aadap_adap asigna el valor a la propiedad detalle area terreno
	 */
	public void setDetalleAreaTerreno(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaTerreno = aadap_adap;
	}

	/**
	 * Retorna el valor de detalle area terreno.
	 *
	 * @return el valor de detalle area terreno
	 */
	public DetalleAreaPredio getDetalleAreaTerreno()
	{
		if(iadap_detalleAreaTerreno == null)
			iadap_detalleAreaTerreno = new DetalleAreaPredio();

		return iadap_detalleAreaTerreno;
	}

	/**
	 * Modifica el valor de detalle tipo causal.
	 *
	 * @param actc_ctc asigna el valor a la propiedad detalle tipo causal
	 */
	public void setDetalleTipoCausal(Collection<TipoCausal> actc_ctc)
	{
		ictc_detalleTipoCausal = actc_ctc;
	}

	/**
	 * Retorna el valor de detalle tipo causal.
	 *
	 * @return el valor de detalle tipo causal
	 */
	public Collection<TipoCausal> getDetalleTipoCausal()
	{
		return ictc_detalleTipoCausal;
	}

	/**
	 * Modifica el valor de detalles ant sistema bng.
	 *
	 * @param acdas_cdas asigna el valor a la propiedad detalles ant sistema bng
	 */
	public void setDetallesAntSistemaBng(Collection<DetalleAntSistema> acdas_cdas)
	{
		icdas_detallesAntSistemaBng = acdas_cdas;
	}

	/**
	 * Retorna el valor de detalles ant sistema bng.
	 *
	 * @return el valor de detalles ant sistema bng
	 */
	public Collection<DetalleAntSistema> getDetallesAntSistemaBng()
	{
		return icdas_detallesAntSistemaBng;
	}

	/**
	 * Modifica el valor de devolucion etapa 110.
	 *
	 * @param ab_b asigna el valor a la propiedad devolucion etapa 110
	 */
	public void setDevolucionEtapa110(boolean ab_b)
	{
		is_devolucionEtapa110 = ab_b;
	}

	/**
	 * Valida la propiedad devolucion etapa 110.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en devolucion etapa 110
	 */
	public boolean isDevolucionEtapa110()
	{
		return is_devolucionEtapa110;
	}

	/**
	 * Modifica el valor de direcciones.
	 *
	 * @param acdp_cdp asigna el valor a la propiedad direcciones
	 */
	public void setDirecciones(Collection<DireccionPredio> acdp_cdp)
	{
		icdp_direcciones = acdp_cdp;
	}

	/**
	 * Retorna el valor de direcciones.
	 *
	 * @return el valor de direcciones
	 */
	public Collection<DireccionPredio> getDirecciones()
	{
		if(!CollectionUtils.isValidCollection(icdp_direcciones))
			icdp_direcciones = new ArrayList<DireccionPredio>();

		return icdp_direcciones;
	}

	/**
	 * Modifica el valor de documento consulta.
	 *
	 * @param ab_b asigna el valor a la propiedad documento consulta
	 */
	public void setDocumentoConsulta(boolean ab_b)
	{
		ib_documentoConsulta = ab_b;
	}

	/**
	 * Valida la propiedad documento consulta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento consulta
	 */
	public boolean isDocumentoConsulta()
	{
		return ib_documentoConsulta;
	}

	/**
	 * Modifica el valor de documento consulta exitosa.
	 *
	 * @param ab_b asigna el valor a la propiedad documento consulta exitosa
	 */
	public void setDocumentoConsultaExitosa(boolean ab_b)
	{
		ib_documentoConsultaExitosa = ab_b;
	}

	/**
	 * Valida la propiedad documento consulta exitosa.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento consulta exitosa
	 */
	public boolean isDocumentoConsultaExitosa()
	{
		return ib_documentoConsultaExitosa;
	}

	/**
	 * Modifica el valor de documento data.
	 *
	 * @param add_dd asigna el valor a la propiedad documento data
	 */
	public void setDocumentoData(DocumentoData add_dd)
	{
		idd_documentoData = add_dd;
	}

	/**
	 * Retorna el valor de documento data.
	 *
	 * @return el valor de documento data
	 */
	public DocumentoData getDocumentoData()
	{
		return idd_documentoData;
	}

	/**
	 * Modifica el valor de documento interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad documento interviniente
	 */
	public void setDocumentoInterviniente(String as_s)
	{
		is_documentoInterviniente = as_s;
	}

	/**
	 * Retorna el valor de documento interviniente.
	 *
	 * @return el valor de documento interviniente
	 */
	public String getDocumentoInterviniente()
	{
		return is_documentoInterviniente;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param alllhmso_llhm correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setDocumentos(List<Map<String, Object>> alllhmso_llhm)
	{
		ilmso_infoDocumentos = alllhmso_llhm;
	}

	/**
	 * Retorna el valor de documentos.
	 *
	 * @return el valor de documentos
	 */
	public List<Map<String, Object>> getDocumentos()
	{
		return ilmso_infoDocumentos;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * Modifica el valor de documentos salida.
	 *
	 * @param acds_cds asigna el valor a la propiedad documentos salida
	 */
	public void setDocumentosSalida(Collection<DocumentosSalida> acds_cds)
	{
		icds_documentosSalida = acds_cds;
	}

	/**
	 * Retorna el valor de documentos salida.
	 *
	 * @return el valor de documentos salida
	 */
	public Collection<DocumentosSalida> getDocumentosSalida()
	{
		return icds_documentosSalida;
	}

	/**
	 * Modifica el valor de editar interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad editar interviniente
	 */
	public void setEditarInterviniente(boolean ab_b)
	{
		ib_editarInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad editar interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar interviniente
	 */
	public boolean isEditarInterviniente()
	{
		return ib_editarInterviniente;
	}

	/**
	 * Modifica el valor de edito complementacion.
	 *
	 * @param ab_b asigna el valor a la propiedad edito complementacion
	 */
	public void setEditoComplementacion(boolean ab_b)
	{
		ib_editoComplementacion = ab_b;
	}

	/**
	 * Valida la propiedad edito complementacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en edito complementacion
	 */
	public boolean isEditoComplementacion()
	{
		return ib_editoComplementacion;
	}

	/**
	 * Modifica el valor de etapa 101.
	 *
	 * @param ab_b asigna el valor a la propiedad etapa 101
	 */
	public void setEtapa101(boolean ab_b)
	{
		ib_etapa101 = ab_b;
	}

	/**
	 * Valida la propiedad etapa 101.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en etapa 101
	 */
	public boolean isEtapa101()
	{
		return ib_etapa101;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_etapa102 por ab_b
	 */
	public void setEtapa102(boolean ab_b)
	{
		ib_etapa102 = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_etapa102
	 */
	public boolean isEtapa102()
	{
		return ib_etapa102;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_etapa105 por ab_b
	 */
	public void setEtapa105(boolean ab_b)
	{
		ib_etapa105 = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_etapa105
	 */
	public boolean isEtapa105()
	{
		return ib_etapa105;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param asc_sc asigna el valor a la propiedad file
	 */
	public void setFile(StreamedContent asc_sc)
	{
		isc_file = asc_sc;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public StreamedContent getFile()
	{
		return isc_file;
	}

	/**
	 * Modifica el valor de filtro tipos causales.
	 *
	 * @param altc_ltc asigna el valor a la propiedad filtro tipos causales
	 */
	public void setFiltroTiposCausales(List<TipoCausal> altc_ltc)
	{
		iltc_filtroTiposCausales = altc_ltc;
	}

	/**
	 * Retorna el valor de filtro tipos causales.
	 *
	 * @return el valor de filtro tipos causales
	 */
	public List<TipoCausal> getFiltroTiposCausales()
	{
		return iltc_filtroTiposCausales;
	}

	/**
	 * Retorna el valor de folios.
	 *
	 * @return el valor de folios
	 */
	public Map<Long, String> getFolios()
	{
		Map<Long, String> lmls_mls;

		lmls_mls = new HashMap<Long, String>();

		for(long ll_folioActual = 1; ll_folioActual <= 500L; ll_folioActual++)
			lmls_mls.put(NumericUtils.getLongWrapper(ll_folioActual), "" + ll_folioActual);

		return lmls_mls;
	}

	/**
	 * Modifica el valor de formulario.
	 *
	 * @param as_s asigna el valor a la propiedad formulario
	 */
	public void setFormulario(String as_s)
	{
		is_formulario = as_s;
	}

	/**
	 * Retorna el valor de formulario.
	 *
	 * @return el valor de formulario
	 */
	public String getFormulario()
	{
		if(!StringUtils.isValidString(is_formulario))
			is_formulario = is_idFormulario_ant_sistema;

		return is_formulario;
	}

	/**
	 * Modifica el valor de grabacion de matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad grabacion de matriculas
	 */
	public void setGrabacionDeMatriculas(boolean ab_b)
	{
		ib_grabacionDeMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad grabacion de matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en grabacion de matriculas
	 */
	public boolean isGrabacionDeMatriculas()
	{
		return ib_grabacionDeMatriculas;
	}

	/**
	 * Modifica el valor de habilitar cargue resultados.
	 *
	 * @param as_s asigna el valor a la propiedad habilitar cargue resultados
	 */
	public void setHabilitarCargueResultados(String as_s)
	{
		is_habilitarCargueResultados = as_s;
	}

	/**
	 * Retorna el valor de habilitar cargue resultados.
	 *
	 * @return el valor de habilitar cargue resultados
	 */
	public String getHabilitarCargueResultados()
	{
		return is_habilitarCargueResultados;
	}

	/**
	 * Modifica el valor de habilitar cargues.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar cargues
	 */
	public void setHabilitarCargues(boolean ab_b)
	{
		ib_habilitarCargues = ab_b;
	}

	/**
	 * Valida la propiedad habilitar cargues.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar cargues
	 */
	public boolean isHabilitarCargues()
	{
		return ib_habilitarCargues;
	}

	/**
	 * Modifica el valor de habilitar complementacion.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar complementacion
	 */
	public void setHabilitarComplementacion(boolean ab_b)
	{
		ib_habilitarComplementacion = ab_b;
	}

	/**
	 * Valida la propiedad habilitar complementacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar complementacion
	 */
	public boolean isHabilitarComplementacion()
	{
		return ib_habilitarComplementacion;
	}

	/**
	 * Modifica el valor de habilitar completitud.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar completitud
	 */
	public void setHabilitarCompletitud(boolean ab_b)
	{
		ib_habilitarCompletitud = ab_b;
	}

	/**
	 * Valida la propiedad habilitar completitud.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar completitud
	 */
	public boolean isHabilitarCompletitud()
	{
		return ib_habilitarCompletitud;
	}

	/**
	 * Modifica el valor de habilitar matriculas complementacion anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar matriculas complementacion anotacion
	 */
	public void setHabilitarMatriculasComplementacionAnotacion(boolean ab_b)
	{
		ib_habilitarMatriculasComplementacionAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad habilitar matriculas complementacion anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar matriculas complementacion anotacion
	 */
	public boolean isHabilitarMatriculasComplementacionAnotacion()
	{
		return ib_habilitarMatriculasComplementacionAnotacion;
	}

	/**
	 * Modifica el valor de habilitar secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar secuencia
	 */
	public void setHabilitarSecuencia(boolean ab_b)
	{
		ib_habilitarSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad habilitar secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar secuencia
	 */
	public boolean isHabilitarSecuencia()
	{
		return ib_habilitarSecuencia;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de id anotacion general.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion general
	 */
	public void setIdAnotacionGeneral(long al_l)
	{
		il_idAnotacionGeneral = al_l;
	}

	/**
	 * Retorna el valor de id anotacion general.
	 *
	 * @return el valor de id anotacion general
	 */
	public long getIdAnotacionGeneral()
	{
		return il_idAnotacionGeneral;
	}

	/**
	 * Modifica el valor de id anotacion para eliminar.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion para eliminar
	 */
	public void setIdAnotacionParaEliminar(long al_l)
	{
		il_idAnotacionParaEliminar = al_l;
	}

	/**
	 * Retorna el valor de id anotacion para eliminar.
	 *
	 * @return el valor de id anotacion para eliminar
	 */
	public long getIdAnotacionParaEliminar()
	{
		return il_idAnotacionParaEliminar;
	}

	/**
	 * Modifica el valor de id circulo proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo proceso
	 */
	public void setIdCirculoProceso(String as_s)
	{
		is_idCirculoProceso = as_s;
	}

	/**
	 * Retorna el valor de id circulo proceso.
	 *
	 * @return el valor de id circulo proceso
	 */
	public String getIdCirculoProceso()
	{
		return is_idCirculoProceso;
	}

	/**
	 * Retorna el valor de id datos ant sistema actual.
	 *
	 * @return el valor de id datos ant sistema actual
	 */
	public String getIdDatosAntSistemaActual()
	{
		DatosAntSistema ldas_datosAntSistema;
		String          ls_idDatosAntSistema;

		ldas_datosAntSistema     = getDatoAntSistemaCargado();
		ls_idDatosAntSistema     = null;

		if(ldas_datosAntSistema != null)
			ls_idDatosAntSistema = ldas_datosAntSistema.getIdDatosAntSistema();

		return ls_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de id documento.
	 *
	 * @param as_s asigna el valor a la propiedad id documento
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna el valor de id documento.
	 *
	 * @return el valor de id documento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de id persona seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id persona seleccion
	 */
	public void setIdPersonaSeleccion(String as_s)
	{
		is_idPersonaSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id persona seleccion.
	 *
	 * @return el valor de id persona seleccion
	 */
	public String getIdPersonaSeleccion()
	{
		return is_idPersonaSeleccion;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id tipo entidad.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo entidad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna el valor de id tipo entidad.
	 *
	 * @return el valor de id tipo entidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de imagen visor.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen visor
	 */
	public void setImagenVisor(StreamedContent asc_sc)
	{
		isc_imagenVisor = asc_sc;
	}

	/**
	 * Retorna el valor de imagen visor.
	 *
	 * @return el valor de imagen visor
	 */
	public StreamedContent getImagenVisor()
	{
		return isc_imagenVisor;
	}

	/**
	 * Sets the info antiguo sistema.
	 *
	 * @param alllhmso_lhm correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setInfoAntiguoSistema(List<Map<String, Object>> alllhmso_lhm)
	{
		ilmso_infoAntiguoSistema = alllhmso_lhm;
	}

	/**
	 * Retorna el valor de info antiguo sistema.
	 *
	 * @return el valor de info antiguo sistema
	 */
	public List<Map<String, Object>> getInfoAntiguoSistema()
	{
		return ilmso_infoAntiguoSistema;
	}

	/**
	 * Modifica el valor de informe de busqueda ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad informe de busqueda ant sistema
	 */
	public void setInformeDeBusquedaAntSistema(boolean ab_b)
	{
		ib_informeDeBusquedaAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad informe de busqueda ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en informe de busqueda ant sistema
	 */
	public boolean isInformeDeBusquedaAntSistema()
	{
		return ib_informeDeBusquedaAntSistema;
	}

	/**
	 * Modifica el valor de informe de busqueda ant sistema 2.
	 *
	 * @param ab_b asigna el valor a la propiedad informe de busqueda ant sistema 2
	 */
	public void setInformeDeBusquedaAntSistema2(boolean ab_b)
	{
		ib_informeDeBusquedaAntSistema2 = ab_b;
	}

	/**
	 * Valida la propiedad informe de busqueda ant sistema 2.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en informe de busqueda ant sistema 2
	 */
	public boolean isInformeDeBusquedaAntSistema2()
	{
		return ib_informeDeBusquedaAntSistema2;
	}

	/**
	 * Modifica el valor de interviniente actual.
	 *
	 * @param aa_a asigna el valor a la propiedad interviniente actual
	 */
	public void setIntervinienteActual(Anotacion aa_a)
	{
		ia_intervinienteActual = aa_a;
	}

	/**
	 * Retorna el valor de interviniente actual.
	 *
	 * @return el valor de interviniente actual
	 */
	public Anotacion getIntervinienteActual()
	{
		return ia_intervinienteActual;
	}

	/**
	 * Modifica el valor de intervinientes agregados.
	 *
	 * @param aca_ca asigna el valor a la propiedad intervinientes agregados
	 */
	public void setIntervinientesAgregados(Collection<Anotacion> aca_ca)
	{
		ica_intervinientesAgregados = aca_ca;
	}

	/**
	 * Retorna el valor de intervinientes agregados.
	 *
	 * @return el valor de intervinientes agregados
	 */
	public Collection<Anotacion> getIntervinientesAgregados()
	{
		return ica_intervinientesAgregados;
	}

	/**
	 * Modifica el valor de intervinientes agregados bng.
	 *
	 * @param aca_ca asigna el valor a la propiedad intervinientes agregados bng
	 */
	public void setIntervinientesAgregadosBng(Collection<Anotacion> aca_ca)
	{
		ica_intervinientesAgregadosBng = aca_ca;
	}

	/**
	 * Retorna el valor de intervinientes agregados bng.
	 *
	 * @return el valor de intervinientes agregados bng
	 */
	public Collection<Anotacion> getIntervinientesAgregadosBng()
	{
		return ica_intervinientesAgregadosBng;
	}

	/**
	 * Modifica el valor de justificacion firma.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion firma
	 */
	public void setJustificacionFirma(String as_s)
	{
		is_justificacionFirma = as_s;
	}

	/**
	 * Retorna el valor de justificacion firma.
	 *
	 * @return el valor de justificacion firma
	 */
	public String getJustificacionFirma()
	{
		return is_justificacionFirma;
	}

	/**
	 * Modifica el valor de libro justificacion requiere.
	 *
	 * @param as_s asigna el valor a la propiedad libro justificacion requiere
	 */
	public void setLibroJustificacionRequiere(boolean as_s)
	{
		ib_libroJustificacionRequiere = as_s;
	}

	/**
	 * Retorna el valor de libro justificacion requiere.
	 *
	 * @return el valor de libro justificacion requiere
	 */
	public boolean isLibroJustificacionRequiere()
	{
		return ib_libroJustificacionRequiere;
	}

	/**
	 * Modifica el valor de lindero registro calificacion.
	 *
	 * @param linderoRegistroCalificacion asigna el valor a la propiedad lindero registro calificacion
	 */
	public void setLinderoRegistroCalificacion(LinderoRegistroCalificacion linderoRegistroCalificacion)
	{
		ilrc_linderoRegistroCalificacion = linderoRegistroCalificacion;
	}

	/**
	 * Retorna el valor de lindero registro calificacion.
	 *
	 * @return el valor de lindero registro calificacion
	 */
	public LinderoRegistroCalificacion getLinderoRegistroCalificacion()
	{
		if(ilrc_linderoRegistroCalificacion == null)
			ilrc_linderoRegistroCalificacion = new LinderoRegistroCalificacion();

		return ilrc_linderoRegistroCalificacion;
	}

	/**
	 * Modifica el valor de listado intervinientes.
	 *
	 * @param acp_cp asigna el valor a la propiedad listado intervinientes
	 */
	public void setListadoIntervinientes(Collection<Persona> acp_cp)
	{
		icp_listadoIntervinientes = acp_cp;
	}

	/**
	 * Retorna el valor de listado intervinientes.
	 *
	 * @return el valor de listado intervinientes
	 */
	public Collection<Persona> getListadoIntervinientes()
	{
		return icp_listadoIntervinientes;
	}

	/**
	 * Modifica el valor de matricula documento seleccionada.
	 *
	 * @param adcpc_dcpc asigna el valor a la propiedad matricula documento seleccionada
	 */
	public void setMatriculaDocumentoSeleccionada(DataConsultaPorCriterio adcpc_dcpc)
	{
		idcpc_matriculaDocumentoSeleccionada = adcpc_dcpc;
	}

	/**
	 * Retorna el valor de matricula documento seleccionada.
	 *
	 * @return el valor de matricula documento seleccionada
	 */
	public DataConsultaPorCriterio getMatriculaDocumentoSeleccionada()
	{
		return idcpc_matriculaDocumentoSeleccionada;
	}

	/**
	 * Modifica el valor de matricula seleccionada para eliminar.
	 *
	 * @param apaui_paui asigna el valor a la propiedad matricula seleccionada para eliminar
	 */
	public void setMatriculaSeleccionadaParaEliminar(PredioActoIU apaui_paui)
	{
		ipaui_matriculaSeleccionadaParaEliminar = apaui_paui;
	}

	/**
	 * Retorna el valor de matricula seleccionada para eliminar.
	 *
	 * @return el valor de matricula seleccionada para eliminar
	 */
	public PredioActoIU getMatriculaSeleccionadaParaEliminar()
	{
		return ipaui_matriculaSeleccionadaParaEliminar;
	}

	/**
	 * Modifica el valor de matriculas.
	 *
	 * @param aorc_rc asigna el valor a la propiedad matriculas
	 */
	public void setMatriculas(RegistroCalificacion aorc_rc)
	{
		iorc_matriculas = aorc_rc;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public RegistroCalificacion getMatriculas()
	{
		if(iorc_matriculas == null)
			iorc_matriculas = new RegistroCalificacion();

		return iorc_matriculas;
	}

	/**
	 * Modifica el valor de matriculas A heredar.
	 *
	 * @param aorc_rc asigna el valor a la propiedad matriculas A heredar
	 */
	public void setMatriculasAHeredar(RegistroCalificacion aorc_rc)
	{
		iorc_matriculasAHeredar = aorc_rc;
	}

	/**
	 * Retorna el valor de matriculas A heredar.
	 *
	 * @return el valor de matriculas A heredar
	 */
	public RegistroCalificacion getMatriculasAHeredar()
	{
		if(iorc_matriculasAHeredar == null)
			iorc_matriculasAHeredar = new RegistroCalificacion();

		return iorc_matriculasAHeredar;
	}

	/**
	 * @param Modifica el valor de la propiedad matriculasCorreccion por matriculasCorreccion
	 */
	public void setMatriculasCorreccion(Collection<SolicitudMatricula> acsc_csc)
	{
		icsc_matriculasCorreccion = acsc_csc;
	}

	/**
	 * @return Retorna el valor de la propiedad matriculasCorreccion
	 */
	public Collection<SolicitudMatricula> getMatriculasCorreccion()
	{
		return icsc_matriculasCorreccion;
	}

	/**
	 * Retorna el valor de matriculas data.
	 *
	 * @return el valor de matriculas data
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasData()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_matriculas))
			ilmso_matriculas = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
				);

		return ilmso_matriculas;
	}

	/**
	 * Modifica el valor de matriculas para generar complementacion.
	 *
	 * @param acmb_cmb asigna el valor a la propiedad matriculas para generar complementacion
	 */
	public void setMatriculasParaGenerarComplementacion(Collection<MatriculaBase> acmb_cmb)
	{
		icmb_matriculasParaGenerarComplementacion = acmb_cmb;
	}

	/**
	 * Retorna el valor de matriculas para generar complementacion.
	 *
	 * @return el valor de matriculas para generar complementacion
	 */
	public Collection<MatriculaBase> getMatriculasParaGenerarComplementacion()
	{
		return icmb_matriculasParaGenerarComplementacion;
	}

	/**
	 * Modifica el valor de matriculas segregadas.
	 *
	 * @param aca_ca asigna el valor a la propiedad matriculas segregadas
	 */
	public void setMatriculasSegregadas(Collection<Anotacion> aca_ca)
	{
		ica_matriculasSegregadas = aca_ca;
	}

	/**
	 * Retorna el valor de matriculas segregadas.
	 *
	 * @return el valor de matriculas segregadas
	 */
	public Collection<Anotacion> getMatriculasSegregadas()
	{
		return ica_matriculasSegregadas;
	}

	/**
	 * Sets the matriculas.
	 *
	 * @param alllhsmo_lllhsmo correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 */
	public void setMatriculasValidacion(Collection<Map<String, Object>> alllhsmo_lllhsmo)
	{
		illlhmso_matriculasValidacion = alllhsmo_lllhsmo;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public Collection<Map<String, Object>> getMatriculasValidacion()
	{
		return illlhmso_matriculasValidacion;
	}

	/**
	 * Modifica el valor de modificar complementacion.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar complementacion
	 */
	public void setModificarComplementacion(boolean ab_b)
	{
		ib_modificarComplementacion = ab_b;
	}

	/**
	 * Valida la propiedad modificar complementacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar complementacion
	 */
	public boolean isModificarComplementacion()
	{
		return ib_modificarComplementacion;
	}

	/**
	 * Modifica el valor de mostrar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar
	 */
	public void setMostrar(boolean ab_b)
	{
		ib_mostrar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar
	 */
	public boolean isMostrar()
	{
		return ib_mostrar;
	}

	/**
	 * Modifica el valor de mostrar anotacion cancela.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar anotacion cancela
	 */
	public void setMostrarAnotacionCancela(boolean ab_b)
	{
		ib_mostrarAnotacionCancela = ab_b;
	}

	/**
	 * Valida la propiedad mostrar anotacion cancela.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar anotacion cancela
	 */
	public boolean isMostrarAnotacionCancela()
	{
		return ib_mostrarAnotacionCancela;
	}

	/**
	 * Modifica el valor de mostrar atras crear grabar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar atras crear grabar
	 */
	public void setMostrarAtrasCrearGrabar(boolean ab_b)
	{
		ib_mostrarAtrasCrearGrabar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar atras crear grabar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar atras crear grabar
	 */
	public boolean isMostrarAtrasCrearGrabar()
	{
		return ib_mostrarAtrasCrearGrabar;
	}

	/**
	 * Modifica el valor de mostrar bandeja.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar bandeja
	 */
	public void setMostrarBandeja(boolean ab_b)
	{
		ib_mostrarBandeja = ab_b;
	}

	/**
	 * Valida la propiedad mostrar bandeja.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar bandeja
	 */
	public boolean isMostrarBandeja()
	{
		return ib_mostrarBandeja;
	}

	/**
	 * Modifica el valor de mostrar listado personas.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar listado personas
	 */
	public void setMostrarListadoPersonas(boolean ab_b)
	{
		ib_mostrarListadoPersonas = ab_b;
	}

	/**
	 * Valida la propiedad mostrar listado personas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar listado personas
	 */
	public boolean isMostrarListadoPersonas()
	{
		return ib_mostrarListadoPersonas;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarPanelActos por ab_b
	 */
	public void setMostrarPanelMotivos(boolean ab_b)
	{
		ib_mostrarPanelMotivos = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarPanelMotivos
	 */
	public boolean isMostrarPanelMotivos()
	{
		return ib_mostrarPanelMotivos;
	}

	/**
	 * Modifica el valor de mostrar rechazar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar rechazar
	 */
	public void setMostrarRechazar(boolean ab_b)
	{
		ib_mostrarRechazar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar rechazar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar rechazar
	 */
	public boolean isMostrarRechazar()
	{
		return ib_mostrarRechazar;
	}

	/**
	 * Modifica el valor de mostrar regresar englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar regresar englobes
	 */
	public void setMostrarRegresarEnglobes(boolean ab_b)
	{
		ib_mostrarRegresarEnglobes = ab_b;
	}

	/**
	 * Valida la propiedad mostrar regresar englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar regresar englobes
	 */
	public boolean isMostrarRegresarEnglobes()
	{
		return ib_mostrarRegresarEnglobes;
	}

	/**
	 * Modifica el valor de mostrar siguiente englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar siguiente englobes
	 */
	public void setMostrarSiguienteEnglobes(boolean ab_b)
	{
		ib_mostrarSiguienteEnglobes = ab_b;
	}

	/**
	 * Valida la propiedad mostrar siguiente englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar siguiente englobes
	 */
	public boolean isMostrarSiguienteEnglobes()
	{
		return ib_mostrarSiguienteEnglobes;
	}

	/**
	 * Modifica el valor de motivo tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo tramite
	 */
	public void setMotivoTramite(String as_s)
	{
		is_motivoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo tramite.
	 *
	 * @return el valor de motivo tramite
	 */
	public String getMotivoTramite()
	{
		return is_motivoTramite;
	}

	/**
	 * @param Modifica el valor de la propiedad ic_motivos por ic_motivos
	 */
	public void setMotivos(Collection<MotivoTramite> ac_m)
	{
		ic_motivos = ac_m;
	}

	/**
	 * @return Retorna el valor de la propiedad ic_motivos
	 */
	public Collection<MotivoTramite> getMotivos()
	{
		return ic_motivos;
	}

	/**
	 * @param Modifica el valor de la propiedad ic_motivosTemporales por ic_motivosTemporales
	 */
	public void setMotivosTemporales(Collection<MotivoTramite> ac_c)
	{
		ic_motivosTemporales = ac_c;
	}

	/**
	 * @return Retorna el valor de la propiedad ic_motivosTemporales
	 */
	public Collection<MotivoTramite> getMotivosTemporales()
	{
		return ic_motivosTemporales;
	}

	/**
	 * Modifica el valor de naturaleza juridica seleccionada.
	 *
	 * @param as_s asigna el valor a la propiedad naturaleza juridica seleccionada
	 */
	public void setNaturalezaJuridicaSeleccionada(String as_s)
	{
		is_naturalezaJuridicaSeleccionada = as_s;
	}

	/**
	 * Retorna el valor de naturaleza juridica seleccionada.
	 *
	 * @return el valor de naturaleza juridica seleccionada
	 */
	public String getNaturalezaJuridicaSeleccionada()
	{
		return is_naturalezaJuridicaSeleccionada;
	}

	/**
	 * Modifica el valor de no informe busqueda.
	 *
	 * @param ab_b asigna el valor a la propiedad no informe busqueda
	 */
	public void setNoInformeBusqueda(boolean ab_b)
	{
		ib_noInformeBusqueda = ab_b;
	}

	/**
	 * Valida la propiedad no informe busqueda.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en no informe busqueda
	 */
	public boolean isNoInformeBusqueda()
	{
		return ib_noInformeBusqueda;
	}

	/**
	 * Modifica el valor de nombre predios sin revision.
	 *
	 * @param acs_cs asigna el valor a la propiedad nombre predios sin revision
	 */
	public void setNombrePrediosSinRevision(Collection<String> acs_cs)
	{
		ics_nombrePrediosSinRevision = acs_cs;
	}

	/**
	 * Retorna el valor de nombre predios sin revision.
	 *
	 * @return el valor de nombre predios sin revision
	 */
	public Collection<String> getNombrePrediosSinRevision()
	{
		return ics_nombrePrediosSinRevision;
	}

	/**
	 * Modifica el valor de numero anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad numero anotacion
	 */
	public void setNumeroAnotacion(Long al_l)
	{
		il_numeroAnotacion = al_l;
	}

	/**
	 * Retorna el valor de numero anotacion.
	 *
	 * @return el valor de numero anotacion
	 */
	public Long getNumeroAnotacion()
	{
		return il_numeroAnotacion;
	}

	/**
	 * Modifica el valor de numero matricula eliminar.
	 *
	 * @param as_s asigna el valor a la propiedad numero matricula eliminar
	 */
	public void setNumeroMatriculaEliminar(String as_s)
	{
		is_numeroMatriculaEliminar = as_s;
	}

	/**
	 * Retorna el valor de numero matricula eliminar.
	 *
	 * @return el valor de numero matricula eliminar
	 */
	public String getNumeroMatriculaEliminar()
	{
		return is_numeroMatriculaEliminar;
	}

	/**
	 * Modifica el valor de numero orden.
	 *
	 * @param al_l asigna el valor a la propiedad numero orden
	 */
	public void setNumeroOrden(Long al_l)
	{
		il_numeroOrden = al_l;
	}

	/**
	 * Retorna el valor de numero orden.
	 *
	 * @return el valor de numero orden
	 */
	public Long getNumeroOrden()
	{
		return il_numeroOrden;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de observaciones calificador.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones calificador
	 */
	public void setObservacionesCalificador(String as_s)
	{
		is_observacionesCalificador = as_s;
	}

	/**
	 * Retorna el valor de observaciones calificador.
	 *
	 * @return el valor de observaciones calificador
	 */
	public String getObservacionesCalificador()
	{
		return is_observacionesCalificador;
	}

	/**
	 * Modifica el valor de observaciones rechazo.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones rechazo
	 */
	public void setObservacionesRechazo(String as_s)
	{
		is_observacionesRechazo = as_s;
	}

	/**
	 * Retorna el valor de observaciones rechazo.
	 *
	 * @return el valor de observaciones rechazo
	 */
	public String getObservacionesRechazo()
	{
		return is_observacionesRechazo;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_ocultarBotonSiguiente por ib_ocultarBotonSiguiente
	 */
	public void setOcultarBotonSiguiente(boolean ib_ocultarBotonSiguiente)
	{
		this.ib_ocultarBotonSiguiente = ib_ocultarBotonSiguiente;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_ocultarBotonSiguiente
	 */
	public boolean isOcultarBotonSiguiente()
	{
		return ib_ocultarBotonSiguiente;
	}

	/**
	 * Modifica el valor de ocultar siguiente crear grabar.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar siguiente crear grabar
	 */
	public void setOcultarSiguienteCrearGrabar(boolean ab_b)
	{
		ib_ocultarSiguienteCrearGrabar = ab_b;
	}

	/**
	 * Valida la propiedad ocultar siguiente crear grabar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar siguiente crear grabar
	 */
	public boolean isOcultarSiguienteCrearGrabar()
	{
		return ib_ocultarSiguienteCrearGrabar;
	}

	/**
	 * Método de Sobrecarga obtención de la propiedad de oficinas origen.
	 *
	 * @param ab_accion de ab accion
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(boolean ab_accion)
	{
		Collection<OficinaOrigen> lc_oo;

		lc_oo = getOficinaOrigen(ab_accion, false);

		return lc_oo;
	}

	/**
	 * Método encargado de consultar el valor de oficina origen.
	 *
	 * @param ab_datosDocumento Argumento de tipo <code>boolean</code> que determina si se deben usar los datos precargados de documento.
	 * @param ab_docLibroSegundo Argumento de tipo <code>boolean</code> que determina si se deben usar los datos precargados de documento libro segundo.
	 * @return Listado de oficinas origen.
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(boolean ab_datosDocumento, boolean ab_docLibroSegundo)
	{
		return getOficinaOrigen(ab_datosDocumento, ab_docLibroSegundo, false);
	}

	/**
	 * Método encargado de consultar la oficina origen para los datos del documento registrados en pantalla.
	 *
	 * @param ab_datosDocumento de ab datos documento
	 * @param ab_docLibroSegundo de ab doc libro segundo
	 * @param ab_documento de ab documento
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(
	    boolean ab_datosDocumento, boolean ab_docLibroSegundo, boolean ab_documento
	)
	{
		Collection<OficinaOrigen> lcidt_datos;
		OficinaOrigen             loo_oficinaOrigen;

		lcidt_datos           = new ArrayList<OficinaOrigen>();
		loo_oficinaOrigen     = null;

		try
		{
			if(ab_datosDocumento && !ab_docLibroSegundo)
			{
				ConsultaDatosDocumento lcdd_consultaDatosDocumento;
				lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

				if((lcdd_consultaDatosDocumento != null) && !ab_docLibroSegundo)
					loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();
			}
			else if(ab_docLibroSegundo)
			{
				DetalleAntSistemaUI ldas_das;
				ldas_das = getDetalleAntSistemaConsulta();

				Documento ld_documento;
				ld_documento = ab_documento ? getDocumentoDetalleCargado()
					                        : ((ldas_das != null) ? ldas_das.getDocumentoConsulta() : null);

				if(ld_documento != null)
				{
					loo_oficinaOrigen = ld_documento.getOficinaOrigen();
					loo_oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
					ld_documento.setIdTipoOficina(loo_oficinaOrigen.getIdTipoOficina());
					ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
					ld_documento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
					ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
				}
			}

			if(loo_oficinaOrigen != null)
				lcidt_datos = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("getOficinaOrigen", lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Método encargado de consultar la oficina origen para los datos del documento seleccionados.
	 *
	 * @return Colección resultante de la busqueda
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		return getOficinaOrigen(getDocumento());
	}

	/**
	 * Método encargado de consultar la oficina origen para los datos del documento seleccionados.
	 *
	 * @param ad_documento Objeto contenedor de la información de la oficina origen
	 * @return Colección resultante de la busqueda
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(Documento ad_documento)
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		if(ad_documento != null)
		{
			try
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = new OficinaOrigen();

				loo_oficinaOrigen.setIdTipoOficina(ad_documento.getIdTipoOficina());
				loo_oficinaOrigen.setIdPais(ad_documento.getIdPais());
				loo_oficinaOrigen.setIdDepartamento(ad_documento.getIdDepartamento());
				loo_oficinaOrigen.setIdMunicipio(ad_documento.getIdMunicipio());

				lcidt_datos = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				clh_LOGGER.error("getOficinaOrigen", lb2be_e);
				lcidt_datos = null;
			}
		}

		return lcidt_datos;
	}

	/**
	 * Modifica el valor de orden anotacion.
	 *
	 * @param abd_bd asigna el valor a la propiedad orden anotacion
	 */
	public void setOrdenAnotacion(BigDecimal abd_bd)
	{
		ibd_ordenAnotacion = abd_bd;
	}

	/**
	 * Retorna el valor de orden anotacion.
	 *
	 * @return el valor de orden anotacion
	 */
	public BigDecimal getOrdenAnotacion()
	{
		return ibd_ordenAnotacion;
	}

	/**
	 * Modifica el valor de panel detalle intervinientes.
	 *
	 * @param apdi_pdi asigna el valor a la propiedad panel detalle intervinientes
	 */
	public void setPanelDetalleIntervinientes(PanelDetalleIntervinientes apdi_pdi)
	{
		ipdi_panelDetalleIntervinientes = apdi_pdi;
	}

	/**
	 * Retorna el valor de panel detalle intervinientes.
	 *
	 * @return el valor de panel detalle intervinientes
	 */
	public PanelDetalleIntervinientes getPanelDetalleIntervinientes()
	{
		if(ipdi_panelDetalleIntervinientes == null)
			ipdi_panelDetalleIntervinientes = new PanelDetalleIntervinientes();

		return ipdi_panelDetalleIntervinientes;
	}

	/**
	 * Modifica el valor de panel detalle intervinientes bng.
	 *
	 * @param apdi_pdi asigna el valor a la propiedad panel detalle intervinientes bng
	 */
	public void setPanelDetalleIntervinientesBng(PanelDetalleIntervinientes apdi_pdi)
	{
		ipdi_panelDetalleIntervinientesBng = apdi_pdi;
	}

	/**
	 * Retorna el valor de panel detalle intervinientes bng.
	 *
	 * @return el valor de panel detalle intervinientes bng
	 */
	public PanelDetalleIntervinientes getPanelDetalleIntervinientesBng()
	{
		if(ipdi_panelDetalleIntervinientesBng == null)
			ipdi_panelDetalleIntervinientesBng = new PanelDetalleIntervinientes();

		return ipdi_panelDetalleIntervinientesBng;
	}

	/**
	 * Modifica el valor de pdf generado.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf generado
	 */
	public void setPdfGenerado(boolean ab_b)
	{
		ib_pdfGenerado = ab_b;
	}

	/**
	 * Valida la propiedad pdf generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf generado
	 */
	public boolean isPdfGenerado()
	{
		return ib_pdfGenerado;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		if(ip_persona == null)
			ip_persona = new Persona();

		return ip_persona;
	}

	/**
	 * Modifica el valor de persona bng.
	 *
	 * @param ap_p asigna el valor a la propiedad persona bng
	 */
	public void setPersonaBng(Persona ap_p)
	{
		ip_personaBng = ap_p;
	}

	/**
	 * Retorna el valor de persona bng.
	 *
	 * @return el valor de persona bng
	 */
	public Persona getPersonaBng()
	{
		if(ip_personaBng == null)
			ip_personaBng = new Persona();

		return ip_personaBng;
	}

	/**
	 * Sets the predio documento.
	 *
	 * @param all_ll correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setPredioDocumento(List<Map<String, Object>> all_ll)
	{
		ilmso_predioDocumento = all_ll;
	}

	/**
	 * Modifica el valor de predio segregado.
	 *
	 * @param aps_ps asigna el valor a la propiedad predio segregado
	 */
	public void setPredioSegregado(PredioSegregado aps_ps)
	{
		ips_predioSegregado = aps_ps;
	}

	/**
	 * Retorna el valor de predio segregado.
	 *
	 * @return el valor de predio segregado
	 */
	public PredioSegregado getPredioSegregado()
	{
		if(ips_predioSegregado == null)
		{
			ips_predioSegregado = new PredioSegregado();

			ips_predioSegregado.setIdCirculo(getIdCirculo());
		}

		return ips_predioSegregado;
	}

	/**
	 * Modifica el valor de predio seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad predio seleccionado
	 */
	public void setPredioSeleccionado(boolean ab_b)
	{
		ib_predioSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad predio seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en predio seleccionado
	 */
	public boolean isPredioSeleccionado()
	{
		return ib_predioSeleccionado;
	}

	/**
	 * Modifica el valor de pregunta masivo intervinientes.
	 *
	 * @param as_s asigna el valor a la propiedad pregunta masivo intervinientes
	 */
	public void setPreguntaMasivoIntervinientes(String as_s)
	{
		is_preguntaMasivoIntervinientes = as_s;
	}

	/**
	 * Retorna el valor de pregunta masivo intervinientes.
	 *
	 * @return el valor de pregunta masivo intervinientes
	 */
	public String getPreguntaMasivoIntervinientes()
	{
		return is_preguntaMasivoIntervinientes;
	}

	/**
	 * Modifica el valor de primer vez.
	 *
	 * @param ab_b asigna el valor a la propiedad primer vez
	 */
	public void setPrimerVez(boolean ab_b)
	{
		ib_primerVez = ab_b;
	}

	/**
	 * Valida la propiedad primer vez.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en primer vez
	 */
	public boolean isPrimerVez()
	{
		return ib_primerVez;
	}

	/**
	 * Modifica el valor de proceso certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso certificados
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ib_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso certificados
	 */
	public boolean isProcesoCertificados()
	{
		return ib_procesoCertificados;
	}

	/**
	 * Modifica el valor de proceso correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso correcciones
	 */
	public void setProcesoCorrecciones(boolean ab_b)
	{
		ib_procesoCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad proceso correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso correcciones
	 */
	public boolean isProcesoCorrecciones()
	{
		return ib_procesoCorrecciones;
	}

	/**
	 * Modifica el valor de proceso terminado asociar matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso terminado asociar matricula
	 */
	public void setProcesoTerminadoAsociarMatricula(boolean ab_b)
	{
		ib_procesoTerminadoAsociarMatricula = ab_b;
	}

	/**
	 * Valida la propiedad proceso terminado asociar matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado asociar matricula
	 */
	public boolean isProcesoTerminadoAsociarMatricula()
	{
		return ib_procesoTerminadoAsociarMatricula;
	}

	/**
	 * Modifica el valor de proceso terminado crear grabar.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso terminado crear grabar
	 */
	public void setProcesoTerminadoCrearGrabar(boolean ab_b)
	{
		ib_procesoTerminadoCrearGrabar = ab_b;
	}

	/**
	 * Valida la propiedad proceso terminado crear grabar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado crear grabar
	 */
	public boolean isProcesoTerminadoCrearGrabar()
	{
		return ib_procesoTerminadoCrearGrabar;
	}

	/**
	 * Valida la propiedad proceso terminado crear matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado crear matricula
	 */
	public boolean isProcesoTerminadoCrearMatricula()
	{
		Collection<AccPredioRegistro> lcapr_predios;

		lcapr_predios = getDatosTurnoMatricula();

		return CollectionUtils.isValidCollection(lcapr_predios);
	}

	/**
	 * Modifica el valor de proceso terminado informe busqueda.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso terminado informe busqueda
	 */
	public void setProcesoTerminadoInformeBusqueda(boolean ab_b)
	{
		ib_procesoTerminadoInformeBusqueda = ab_b;
	}

	/**
	 * Valida la propiedad proceso terminado informe busqueda.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado informe busqueda
	 */
	public boolean isProcesoTerminadoInformeBusqueda()
	{
		return ib_procesoTerminadoInformeBusqueda;
	}

	/**
	 * Modifica el valor de procesos.
	 *
	 * @param acadas_cadas asigna el valor a la propiedad procesos
	 */
	public void setProcesos(Collection<ActualizarDatosAntSistema> acadas_cadas)
	{
		icadas_procesos = acadas_cadas;
	}

	/**
	 * Retorna el valor de procesos.
	 *
	 * @return el valor de procesos
	 */
	public Collection<ActualizarDatosAntSistema> getProcesos()
	{
		return icadas_procesos;
	}

	/**
	 * Modifica el valor de rango fin.
	 *
	 * @param as_s asigna el valor a la propiedad rango fin
	 */
	public void setRangoFin(String as_s)
	{
		ls_rangoFin = as_s;
	}

	/**
	 * Retorna el valor de rango fin.
	 *
	 * @return el valor de rango fin
	 */
	public String getRangoFin()
	{
		return ls_rangoFin;
	}

	/**
	 * Modifica el valor de rango inicio.
	 *
	 * @param as_s asigna el valor a la propiedad rango inicio
	 */
	public void setRangoInicio(String as_s)
	{
		ls_rangoInicio = as_s;
	}

	/**
	 * Retorna el valor de rango inicio.
	 *
	 * @return el valor de rango inicio
	 */
	public String getRangoInicio()
	{
		return ls_rangoInicio;
	}

	/**
	 * Modifica el valor de rendered habilita secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered habilita secuencia
	 */
	public void setRenderedHabilitaSecuencia(boolean ab_b)
	{
		ib_renderedHabilitaSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad rendered habilita secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered habilita secuencia
	 */
	public boolean isRenderedHabilitaSecuencia()
	{
		return ib_renderedHabilitaSecuencia;
	}

	/**
	 * Modifica el valor de rendered interviniente acto.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered interviniente acto
	 */
	public void setRenderedIntervinienteActo(boolean ab_b)
	{
		ib_renderedIntervinienteActo = ab_b;
	}

	/**
	 * Valida la propiedad rendered interviniente acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered interviniente acto
	 */
	public boolean isRenderedIntervinienteActo()
	{
		return ib_renderedIntervinienteActo;
	}

	/**
	 * Modifica el valor de resultado cargue intervinientes.
	 *
	 * @param asc_sc asigna el valor a la propiedad resultado cargue intervinientes
	 */
	public void setResultadoCargueIntervinientes(StreamedContent asc_sc)
	{
		isc_resultadoCargueIntervinientes = asc_sc;
	}

	/**
	 * Retorna el valor de resultado cargue intervinientes.
	 *
	 * @return el valor de resultado cargue intervinientes
	 */
	public StreamedContent getResultadoCargueIntervinientes()
	{
		return isc_resultadoCargueIntervinientes;
	}

	/**
	 * Modifica el valor de revisado ant sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad revisado ant sistema
	 */
	public void setRevisadoAntSistema(boolean ab_b)
	{
		ib_revisadoAntSistema = ab_b;
	}

	/**
	 * Valida la propiedad revisado ant sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en revisado ant sistema
	 */
	public boolean isRevisadoAntSistema()
	{
		return ib_revisadoAntSistema;
	}

	/**
	 * Modifica el valor de revision definitiva completa.
	 *
	 * @param ab_b asigna el valor a la propiedad revision definitiva completa
	 */
	public void setRevisionDefinitivaCompleta(boolean ab_b)
	{
		ib_revisionDefinitivaCompleta = ab_b;
	}

	/**
	 * Valida la propiedad revision definitiva completa.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en revision definitiva completa
	 */
	public boolean isRevisionDefinitivaCompleta()
	{
		return ib_revisionDefinitivaCompleta;
	}

	/**
	 * Modifica el valor de revision predios completa.
	 *
	 * @param ab_b asigna el valor a la propiedad revision predios completa
	 */
	public void setRevisionPrediosCompleta(boolean ab_b)
	{
		ib_revisionPrediosCompleta = ab_b;
	}

	/**
	 * Valida la propiedad revision predios completa.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en revision predios completa
	 */
	public boolean isRevisionPrediosCompleta()
	{
		return ib_revisionPrediosCompleta;
	}

	/**
	 * Modifica el valor de seccion tipo matricula.
	 *
	 * @param as_s asigna el valor a la propiedad seccion tipo matricula
	 */
	public void setSeccionTipoMatricula(String as_s)
	{
		ls_seccionTipoMatricula = as_s;
	}

	/**
	 * Retorna el valor de seccion tipo matricula.
	 *
	 * @return el valor de seccion tipo matricula
	 */
	public String getSeccionTipoMatricula()
	{
		return ls_seccionTipoMatricula;
	}

	/**
	 * Modifica el valor de secuencia agregada.
	 *
	 * @param ab_b asigna el valor a la propiedad secuencia agregada
	 */
	public void setSecuenciaAgregada(boolean ab_b)
	{
		ib_secuenciaAgregada = ab_b;
	}

	/**
	 * Valida la propiedad secuencia agregada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en secuencia agregada
	 */
	public boolean isSecuenciaAgregada()
	{
		return ib_secuenciaAgregada;
	}

	/**
	 * Modifica el valor de solicitud interviniente.
	 *
	 * @param asi_si asigna el valor a la propiedad solicitud interviniente
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		isi_solicitudInterviniente = asi_si;
	}

	/**
	 * Retorna el valor de solicitud interviniente.
	 *
	 * @return el valor de solicitud interviniente
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		if(isi_solicitudInterviniente == null)
			isi_solicitudInterviniente = new SolicitudInterviniente();

		return isi_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de solicitud interviniente bng.
	 *
	 * @param asi_si asigna el valor a la propiedad solicitud interviniente bng
	 */
	public void setSolicitudIntervinienteBng(SolicitudInterviniente asi_si)
	{
		isi_solicitudIntervinienteBng = asi_si;
	}

	/**
	 * Retorna el valor de solicitud interviniente bng.
	 *
	 * @return el valor de solicitud interviniente bng
	 */
	public SolicitudInterviniente getSolicitudIntervinienteBng()
	{
		if(isi_solicitudIntervinienteBng == null)
			isi_solicitudIntervinienteBng = new SolicitudInterviniente();

		return isi_solicitudIntervinienteBng;
	}

	/**
	 * Modifica el valor de tipo actualizar.
	 *
	 * @param as_s asigna el valor a la propiedad tipo actualizar
	 */
	public void setTipoActualizar(String as_s)
	{
		is_tipoActualizar = as_s;
	}

	/**
	 * Retorna el valor de tipo actualizar.
	 *
	 * @return el valor de tipo actualizar
	 */
	public String getTipoActualizar()
	{
		return is_tipoActualizar;
	}

	/**
	 * Modifica el valor de tipo consulta.
	 *
	 * @param as_s asigna el valor a la propiedad tipo consulta
	 */
	public void setTipoConsulta(String as_s)
	{
		is_tipoConsulta = as_s;
	}

	/**
	 * Retorna el valor de tipo consulta.
	 *
	 * @return el valor de tipo consulta
	 */
	public String getTipoConsulta()
	{
		return is_tipoConsulta;
	}

	/**
	 * Modifica el valor de tipo consulta manual.
	 *
	 * @param as_s asigna el valor a la propiedad tipo consulta manual
	 */
	public void setTipoConsultaManual(String as_s)
	{
		is_tipoConsultaManual = as_s;
	}

	/**
	 * Retorna el valor de tipo consulta manual.
	 *
	 * @return el valor de tipo consulta manual
	 */
	public String getTipoConsultaManual()
	{
		return is_tipoConsultaManual;
	}

	/**
	 * Modifica el valor de tipo doc interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad tipo doc interviniente
	 */
	public void setTipoDocInterviniente(String as_s)
	{
		is_tipoDocInterviniente = as_s;
	}

	/**
	 * Retorna el valor de tipo doc interviniente.
	 *
	 * @return el valor de tipo doc interviniente
	 */
	public String getTipoDocInterviniente()
	{
		return is_tipoDocInterviniente;
	}

	/**
	 * Modifica el valor de tipos actualizar datos ant sistema.
	 *
	 * @param acadas_cadas asigna el valor a la propiedad tipos actualizar datos ant sistema
	 */
	public void setTiposActualizarDatosAntSistema(Collection<ActualizarDatosAntSistema> acadas_cadas)
	{
		icadas_actualizarDatosAntSistema = acadas_cadas;
	}

	/**
	 * Retorna el valor de tipos actualizar datos ant sistema.
	 *
	 * @return el valor de tipos actualizar datos ant sistema
	 */
	public Collection<ActualizarDatosAntSistema> getTiposActualizarDatosAntSistema()
	{
		return icadas_actualizarDatosAntSistema;
	}

	/**
	 * Modifica el valor de tipos actualizar datos ant sistema 101.
	 *
	 * @param acadas_tiposActualizarDatosAntSistema101 asigna el valor a la propiedad tipos actualizar datos ant sistema 101
	 */
	public void setTiposActualizarDatosAntSistema101(
	    Collection<ActualizarDatosAntSistema> acadas_tiposActualizarDatosAntSistema101
	)
	{
		icadas_tiposActualizarDatosAntSistema101 = acadas_tiposActualizarDatosAntSistema101;
	}

	/**
	 * Retorna el valor de tipos actualizar datos ant sistema 101.
	 *
	 * @return el valor de tipos actualizar datos ant sistema 101
	 */
	public Collection<ActualizarDatosAntSistema> getTiposActualizarDatosAntSistema101()
	{
		return icadas_tiposActualizarDatosAntSistema101;
	}

	/**
	 * Modifica el valor de tipos actualizar datos ant sistema 2.
	 *
	 * @param icadas_cadas asigna el valor a la propiedad tipos actualizar datos ant sistema 2
	 */
	public void setTiposActualizarDatosAntSistema2(Collection<ActualizarDatosAntSistema> icadas_cadas)
	{
		icadas_actualizarDatosAntSistema2 = icadas_cadas;
	}

	/**
	 * Retorna el valor de tipos actualizar datos ant sistema 2.
	 *
	 * @return el valor de tipos actualizar datos ant sistema 2
	 */
	public Collection<ActualizarDatosAntSistema> getTiposActualizarDatosAntSistema2()
	{
		return icadas_actualizarDatosAntSistema2;
	}

	/**
	 * Modifica el valor de tipos causales filtro.
	 *
	 * @param altc_ltc asigna el valor a la propiedad tipos causales filtro
	 */
	public void setTiposCausalesFiltro(List<TipoCausal> altc_ltc)
	{
		iltc_tiposCausalesFiltro = altc_ltc;
	}

	/**
	 * Retorna el valor de tipos causales filtro.
	 *
	 * @return el valor de tipos causales filtro
	 */
	public List<TipoCausal> getTiposCausalesFiltro()
	{
		return iltc_tiposCausalesFiltro;
	}

	/**
	 * Modifica el valor de tipos oficina doc.
	 *
	 * @param acto_cto asigna el valor a la propiedad tipos oficina doc
	 */
	public void setTiposOficinaDoc(Collection<TipoOficina> acto_cto)
	{
		icto_tiposOficinaDoc = acto_cto;
	}

	/**
	 * Retorna el valor de tipos oficina doc.
	 *
	 * @return el valor de tipos oficina doc
	 */
	public Collection<TipoOficina> getTiposOficinaDoc()
	{
		return icto_tiposOficinaDoc;
	}

	/**
	 * Modifica el valor de tipos oficina doc detalle ant.
	 *
	 * @param acto_cto asigna el valor a la propiedad tipos oficina doc detalle ant
	 */
	public void setTiposOficinaDocDetalleAnt(Collection<TipoOficina> acto_cto)
	{
		icto_tiposOficinaDocDetalleAnt = acto_cto;
	}

	/**
	 * Retorna el valor de tipos oficina doc detalle ant.
	 *
	 * @return el valor de tipos oficina doc detalle ant
	 */
	public Collection<TipoOficina> getTiposOficinaDocDetalleAnt()
	{
		return icto_tiposOficinaDocDetalleAnt;
	}

	/**
	 * Método encargado de retornar el valor de tomos.
	 *
	 * @return el valor de tomos
	 */
	public Map<Long, String> getTomos()
	{
		Map<Long, String> lmls_mls;

		lmls_mls = new HashMap<Long, String>();

		for(long ll_tomoActual = 1; ll_tomoActual <= 100L; ll_tomoActual++)
			lmls_mls.put(NumericUtils.getLongWrapper(ll_tomoActual), "" + ll_tomoActual);

		return lmls_mls;
	}

	/**
	 * Método encargado de calcular un rango de años.
	 *
	 * @return Listado con rango de años.
	 */
	public Map<String, String> getYearsAgo()
	{
		return UIUtils.getYearsAgo();
	}

	/**
	 * Método encargado de procesar y cargar una estructura de excel especifica (carga de matriculas masivas).
	 *
	 * @param aba_excel Objeto de tipo  byte[] que contiene el archivo a ser procesado
	 * @param as_nameFile Objeto de tipo  String que contiene el nombre del archivo a procesar
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza el cargue
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public byte[] FileLoad(byte[] aba_excel, String as_nameFile, String as_userId)
	    throws B2BException, IOException
	{
		try
		{
			if(aba_excel != null)
			{
				byte[] aba_archivo;

				aba_archivo = aba_excel;

				if(aba_archivo != null)
				{
					Sheet    lsh_hoja;
					Workbook lw_libro;
					String   ls_nombreArchivoOriginal;
					ls_nombreArchivoOriginal = as_nameFile;

					if(StringUtils.isValidString(ls_nombreArchivoOriginal))
					{
						InputStream lis_archivoExcel;

						lis_archivoExcel = new ByteArrayInputStream(aba_archivo);

						if(ls_nombreArchivoOriginal.toUpperCase().endsWith(ExtensionCommon.XLS_PUNTO))
							lw_libro = new HSSFWorkbook(lis_archivoExcel);
						else
							lw_libro = new XSSFWorkbook(lis_archivoExcel);

						lsh_hoja = lw_libro.getSheetAt(0);
					}
					else
						throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE);

					if(lsh_hoja != null)
					{
						int li_primeraFila;
						int li_ultimaFila;

						li_primeraFila     = 1;
						li_ultimaFila      = lsh_hoja.getLastRowNum();

						if(!(li_ultimaFila > 1002))
						{
							int li_numcol;

							li_numcol = 2;

							for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
							{
								if(li_primeraFila != 1)
								{
									Row lr_filaActual;

									lr_filaActual = lsh_hoja.getRow(ii_fila);

									if(ExcelUtils.isValidRow(lr_filaActual, li_numcol))
									{
										StringBuilder lsb_mensaje;
										Long          ll_numeroMatricula;
										String        ls_circuloRegistral;

										lsb_mensaje             = new StringBuilder();
										ll_numeroMatricula      = null;
										ls_circuloRegistral     = null;

										for(int li_celda = 0; li_celda < li_numcol; li_celda++)
										{
											try
											{
												switch(li_celda)
												{
													case 0:
														ls_circuloRegistral = ExcelUtils.validarStringCeldaExcel(
															    lr_filaActual, ii_fila + 1, li_celda,
															    IdentificadoresCommon.CIRCULO_REGISTRAL, true
															);

														break;

													case 1:
														ll_numeroMatricula = ExcelUtils.validarLongCeldaExcel(
															    lr_filaActual, ii_fila + 1, li_celda,
															    IdentificadoresCommon.NUMERO_MATRICULA, true
															);

														break;

													default:
														break;
												}
											}
											catch(B2BException ab2be_e)
											{
												lsb_mensaje.append(ab2be_e.getMessage());
											}
										}

										if(!StringUtils.isValidString(lsb_mensaje.toString()))
											lsb_mensaje.append(
											    processCell(ll_numeroMatricula, ls_circuloRegistral, as_userId)
											);

										{
											CellStyle lcs_estiloCelda;

											lcs_estiloCelda = lw_libro.createCellStyle();

											lcs_estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);

											ExcelUtils.creaCelda(
											    lr_filaActual, li_numcol, lsb_mensaje.toString(), lcs_estiloCelda
											);

											lsh_hoja.autoSizeColumn(li_numcol);
										}
									}
								}

								li_primeraFila++;
							}

							{
								ByteArrayOutputStream lbaos_output;

								lbaos_output = new ByteArrayOutputStream();

								ExcelUtils.write(lw_libro, lbaos_output);

								aba_excel = lbaos_output.toByteArray();
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_LIMITE_REGISTROS);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("FileLoad", lb2be_e);
			throw lb2be_e;
		}

		return aba_excel;
	}

	/**
	 * Método encargado consultar y descargar todos los documentos cargados para el ID_TURNO actual en un ZIP.
	 */
	public void abrirVisor()
	{
		try
		{
			DocumentosSalida lds_parametros;
			byte[]           lba_zipfinal;

			lds_parametros = new DocumentosSalida();
			lds_parametros.setIdTurno(getDatosDelTurno().get(IdentificadoresCommon.ID_TURNO).toString());

			lba_zipfinal = ier_entregaRemote.generarArchivoZip(
				    lds_parametros, getUserId(), false, getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_zipfinal != null)
			{
				InputStream lis_stream;

				lis_stream = new ByteArrayInputStream(lba_zipfinal);

				setImagenVisor(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.ZIP, ConstanteCommon.NOMBRE_ZIP_GENERADO
				    )
				);
			}
			else
			{
				setImagenVisor(null);

				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = getIdTurno();
				throw new B2BException(ErrorKeys.NO_DOCUMENTOS_ACTIVOS, aoa_messageArgs);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirVisor", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de limpiar el formulario para insertar una nueva matricula.
	 *
	 * @param ab_crear correspondiente al valor del tipo de objeto boolean
	 */
	public void accionNuevo(boolean ab_crear)
	{
		try
		{
			if(ab_crear)
			{
				Collection<AccPredioRegistro> lcapr_matriculas;

				lcapr_matriculas = getDatosTurnoMatricula();

				if(CollectionUtils.isValidCollection(lcapr_matriculas) && (lcapr_matriculas.size() >= 1))
					throw new B2BException(ErrorKeys.ERROR_MAXIMO_MATRICULAS_ALCANZADO);
			}

			setAccPredioRegistro(null);

			{
				setAnotacionPredio(null);
				setDocumento(null);
				setAnotacionCancelacion(null);
				setIntervinientesAgregados(null);
				setMatriculasSegregadas(null);
				setPredioSegregado(null);
				setIdMatricula(null);
				setNaturalezaJuridicaSeleccionada(null);
				setTipoDocInterviniente(null);
				setDocumentoInterviniente(null);
				setIdPersonaSeleccion(null);
				setNumeroAnotacion(null);
				setNumeroOrden(null);
				setListadoIntervinientes(null);
				setAnotacionAgregada(true);
				setDatosAntiguoSistema(null);
				setAnotacion(null);
				setCodigoNaturalezaJuridicaSeleccionada(null);
				setDeshabilitarNumeroAnotacion(false);
				setProcesoTerminadoCrearGrabar(false);
			}

			setAreaPredio(null);
			setCabidaLinderos(null);
			setComplementacionCalificacion(null);
			setDatosBasicos(null);
			setDireccionesPredio(null);
			setPdfGenerado(false);

			TurnoHistoria lth_th;

			lth_th = new TurnoHistoria();

			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

			if(lth_th != null)
			{
				String ls_s;
				String ls_justificacion;

				ls_s                 = lth_th.getIdSolicitud();
				ls_justificacion     = lth_th.getObservacionesNoTramite();

				if(StringUtils.isValidString(ls_justificacion))
					setJustificacionFirma(ls_justificacion);

				if(StringUtils.isValidString(ls_s))
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();

					ls_solicitud.setIdSolicitud(ls_s);
					ls_solicitud.setDocumento(false);
					ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

					if(ls_solicitud != null)
					{
						Documento    ld_documento;
						DatosBasicos ldb_db;
						Apertura     lap_apertura;

						ld_documento     = new Documento();
						ldb_db           = getDatosBasicos();
						lap_apertura     = ldb_db.getApertura();

						lap_apertura.setFechaApertura(new Date());
						lap_apertura.setRadicacion(getIdTurno());

						ld_documento = irr_registroRemote.findDocumento(ls_solicitud.getIdSolicitud());

						if(ld_documento != null)
						{
							lap_apertura.setIdTipoOficina(ld_documento.getIdTipoOficina());
							lap_apertura.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());

							OficinaOrigen loo_oficinaOrigen;
							loo_oficinaOrigen = new OficinaOrigen();
							loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
							loo_oficinaOrigen.setVersion(ld_documento.getVersion());

							loo_oficinaOrigen = irr_registroRemote.findOficinaOrigen(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								lap_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
								lap_apertura.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
								lap_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
							}

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

									lap_apertura.setIdTipoEntidad(lte_te.getIdTipoEntidad());

									ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());

									lap_apertura.setDocumento(ld_documento);
								}
							}
						}

						{
							String ls_idCirculo;

							ls_idCirculo = lth_th.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral       lcr_circuloRegistral;
								UbicacionZonaRegistral luzr_ubicacion;
								MatriculaBase          lmb_matrBase;

								luzr_ubicacion           = new UbicacionZonaRegistral();
								lcr_circuloRegistral     = new CirculoRegistral();

								lmb_matrBase = ldb_db.getMatriculaBase();

								lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

								lcr_circuloRegistral = ipr_parameterRemote.findCirculoRegistralById(
									    lcr_circuloRegistral, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(lcr_circuloRegistral != null)
								{
									ZonaRegistral lzr_zonaRegistral;

									lzr_zonaRegistral = new ZonaRegistral();

									lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

									lzr_zonaRegistral = iasr_antiguoSistemaRemote.findZonaRegistralCirculo(
										    lzr_zonaRegistral
										);

									if(lzr_zonaRegistral != null)
									{
										String ls_idDepartamento;

										ls_idDepartamento = lzr_zonaRegistral.getIdDepartamento();

										if(StringUtils.isValidString(ls_idDepartamento))
										{
											Departamento ld_departamento;

											ld_departamento = new Departamento();

											ld_departamento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
											ld_departamento.setIdDepartamento(ls_idDepartamento);

											ld_departamento = ipr_parameterRemote.findDepartamentoById(ld_departamento);

											if(ld_departamento != null)
												luzr_ubicacion.setDepartamento(ld_departamento);
										}

										luzr_ubicacion.setCirculoRegistral(lcr_circuloRegistral);
									}
								}

								luzr_ubicacion.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

								{
									EstadoPredio lep_estadoPredio;

									lep_estadoPredio = new EstadoPredio();

									lep_estadoPredio.setIdEstadoPredio(EstadoCommon.S);

									luzr_ubicacion.setEstadoPredio(lep_estadoPredio);
								}

								ldb_db.setUbicacion(luzr_ubicacion);

								if(lmb_matrBase == null)
									lmb_matrBase = new MatriculaBase();

								lmb_matrBase.setIdCirculo(ls_idCirculo);

								ldb_db.setMatriculaBase(lmb_matrBase);
							}
						}

						if(lap_apertura != null)
							ldb_db.setApertura(lap_apertura);

						{
							AccPredioRegistro lapr_predRegistro;
							DatosAntSistema   ldas_datos;
							String            ls_nombrePredio;

							lapr_predRegistro     = ldb_db.getAccPredioRegistro();
							ldas_datos            = getDatoAntSistemaCargado();
							ls_nombrePredio       = null;

							if(ldas_datos != null)
								ls_nombrePredio = ldas_datos.getNombrePredio();

							if(lapr_predRegistro == null)
								lapr_predRegistro = new AccPredioRegistro();

							lapr_predRegistro.setNombrePredio(ls_nombrePredio);

							ldb_db.setAccPredioRegistro(lapr_predRegistro);
						}

						setDatosBasicos(ldb_db);
					}
				}
			}

			setMostrarBandeja(false);
			setMostrar(true);

			PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema:idPanelDetalleListados");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionNuevo", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de limpiar la informacion diligenciada cuando el usuario se regresa de pantalla.
	 *
	 * @return devuelve el valor de String
	 */
	@Override
	public String accionVolver()
	{
		clear();

		String ls_return;

		ls_return = NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;

		BeanDetalleAntiguoSistema lobdas_bean;
		FacesContext              lfc_context;

		lfc_context     = FacesUtils.getFacesContext();
		lobdas_bean     = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
				                                                    .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA, BeanDetalleAntiguoSistema.class
				);

		if(lobdas_bean != null)
		{
			try
			{
				lobdas_bean.generarData();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("accionVolver", lb2be_e);
				ls_return = null;
			}
		}

		return ls_return;
	}

	/**
	 * Método encargado de limpiar la informacion diligenciada en el wizard de crear matricula cuando el usuario se
	 * regresa al resumen de matriculas.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolverCrear()
	{
		setAccPredioRegistro(null);
		setAnotacion(null);
		setAnotacionesAgregadas(null);
		setAreaPredio(null);
		setCabidaLinderos(null);
		setComplementacionCalificacion(null);
		setDatosBasicos(null);
		setDireccionesPredio(null);
		setDatosAntiguoSistema(null);
		setMostrarBandeja(true);
		setMostrarAtrasCrearGrabar(false);
		setOcultarSiguienteCrearGrabar(false);
		setArchivoPdf(null);
		setArchivoPdfDescarga(null);
		setMostrar(false);
		setPdfGenerado(false);

		icapr_datosTurnoMatricula = null;

		cargarCrearMatricula(false);
		setMostrarBandeja(true);
		generarDatosActualizar2();

		Wizard lw_wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
				                                   .findComponent("idFormAntSistema:idProcesoAntiguoSistema:wizardId");

		lw_wizard.setStep(cs_nombreTabDatosBasicos);

		PrimeFaces.current().ajax().update("idFormAntSistema");
		PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema:idPanelDetalleListados");

		return null;
	}

	/**
	 * Método encargado de activar complementacion.
	 */
	public void activarComplementacion()
	{
		if(isModificarComplementacion())
			setModificarComplementacion(false);
		else
			setModificarComplementacion(true);
	}

	/**
	 * Método encargado de concatenar los 5 diferentes campos de la base de datos en un solo dato para ser mostrado en pantalla.
	 */
	public void actualizarDireccionPredio()
	{
		try
		{
			DireccionDelPredio lddp_direccion;

			lddp_direccion = getDireccionPredio();

			if(lddp_direccion != null)
			{
				StringBuilder lsb_direccionCompleta;

				lsb_direccionCompleta = new StringBuilder();

				{
					String ls_tipoEje;

					ls_tipoEje = lddp_direccion.getDireccionPredio().getIdTipoEjePrincipal();

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

				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getDatoEjePrincipal()) + " "
				);

				{
					String ls_tipoEje1;

					ls_tipoEje1 = lddp_direccion.getDireccionPredio().getIdTipoEjeSecundario();

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

				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getDatoEjeSecundario()) + " "
				);
				lsb_direccionCompleta.append(
				    StringUtils.getStringNotNull(lddp_direccion.getDireccionPredio().getComplementoDireccion()) + " "
				);

				{
					String ls_tmp;

					ls_tmp = lsb_direccionCompleta.toString();

					if(StringUtils.isValidString(ls_tmp))
						lddp_direccion.setDireccion(ls_tmp.trim());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarDireccionPredio", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update("idItdireccionPredio");
		}
	}

	/**
	 * Método de actualización del pais de antiguo sistema.
	 */
	public void actualizarPais()
	{
		Documento ld_documento;

		ld_documento = getDocumentoDetalleCargado();

		if(ld_documento != null)
		{
			Pais lp_pais;

			lp_pais = ld_documento.getPais();

			if(lp_pais == null)
			{
				lp_pais = new Pais();

				lp_pais.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			}
			else
				lp_pais.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}
		else
		{
			Pais lp_pais;

			ld_documento     = new Documento();
			lp_pais          = new Pais();

			lp_pais.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ld_documento.setPais(lp_pais);

			setDocumentoDetalleCargado(ld_documento);
		}
	}

	/**
	 * Método para la actualización de los componentes de tipo oficina y tipo entidad.
	 *
	 * @return una colección del tipo de oficina con los datos solicitados.
	 */
	public Collection<TipoOficina> actualizarTipoOficinaDocumentoAS()
	{
		return actualizarTipoOficinaDocumentoAS(false);
	}

	/**
	 * Método para la actualización de los componentes de tipo oficina y tipo entidad.
	 *
	 * @param ab_documento Argumento de tipo <code>boolean</code> que determina si se deben usar los datos precargados de documento.
	 * @return una colección del tipo de oficina con los datos solicitados
	 */
	public Collection<TipoOficina> actualizarTipoOficinaDocumentoAS(boolean ab_documento)
	{
		Collection<TipoOficina> lcto_datos;

		lcto_datos = null;

		try
		{
			DetalleAntSistemaUI ldas_das;

			ldas_das = getDetalleAntSistemaConsulta();

			Documento ld_d;

			ld_d = ab_documento ? getDocumentoDetalleCargado()
				                : ((ldas_das != null) ? ldas_das.getDocumentoConsulta() : null);

			if(ld_d != null)
			{
				lcto_datos = irr_referenceRemote.findTipoOficina(ld_d);

				if(CollectionUtils.isValidCollection(lcto_datos))
				{
					if(lcto_datos.size() == 1)
					{
						for(TipoOficina lto_tmp : lcto_datos)
						{
							if(lto_tmp != null)
							{
								ld_d.setIdTipoOficina(lto_tmp.getIdTipoOficina());
								ld_d.setTipoEntidad(lto_tmp.getIdTipoEntidad());
							}
						}
					}
					else if(lcto_datos.size() >= 2)
					{
						Iterator<TipoOficina> lito_oficinas;

						lito_oficinas = lcto_datos.iterator();

						if(lito_oficinas.hasNext())
						{
							TipoOficina lto_oficina;

							lto_oficina = lito_oficinas.next();

							if(lto_oficina != null)
							{
								String ls_idTipoOficina;

								ls_idTipoOficina = lto_oficina.getIdTipoOficina();

								if(StringUtils.isValidString(ls_idTipoOficina))
									ld_d.setIdTipoOficina(ls_idTipoOficina);

								ld_d.setTipoEntidad(lto_oficina.getIdTipoEntidad());
							}
						}
					}
				}
				else
				{
					TipoOficina ltf_seleccione;

					ltf_seleccione     = new TipoOficina();
					lcto_datos         = new ArrayList<TipoOficina>();

					ld_d.setIdTipoOficina(ltf_seleccione.getIdTipoEntidad());
					ld_d.setTipoEntidad(ltf_seleccione.getIdTipoEntidad());

					ltf_seleccione.setNombre(ConstanteCommon.SELECCIONE);

					lcto_datos.add(ltf_seleccione);
				}

				{
					String ls_idPais;

					ls_idPais = ld_d.getIdPais();

					if(!StringUtils.isValidString(ls_idPais))
						ld_d.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lcto_datos = null;
		}

		return lcto_datos;
	}

	/**
	 * Método encargado de agregar un nuevo registro en la tabla de matrículas para posteriormente ser diligenciada por el usuario.
	 *
	 * @param aae_ae Objeto de tipo ActionEvent que permite identificar la peticion ajax hecha por el usuario
	 */
	public void agregarActosIndividual(ActionEvent aae_ae)
	{
		try
		{
			Collection<PredioActoIU> lcpaui_tmp;

			lcpaui_tmp = getActosAsociadosGeneral();

			if(!CollectionUtils.isValidCollection(lcpaui_tmp))
				lcpaui_tmp = new ArrayList<PredioActoIU>();
			else
			{
				for(PredioActoIU lpaui_actual : lcpaui_tmp)
				{
					if(lpaui_actual != null)
					{
						String ls_matriculaActual;

						ls_matriculaActual = lpaui_actual.getMatricula();

						if(!StringUtils.isValidString(ls_matriculaActual))
							throw new B2BException(ErrorKeys.ERROR_TABLA_MATRICULA_INGRESADA);
					}
				}
			}

			{
				PredioActoIU lpaui_nuevo;

				lpaui_nuevo = new PredioActoIU(getColumns());

				lcpaui_tmp.add(lpaui_nuevo);
			}

			setActosAsociadosGeneral(lcpaui_tmp);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActosIndividual", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de agregar matriculas de forma masiva mediante rengos para un mismo circulo.
	 *
	 * @param aae_ae Objeto de tipo ActionEvent que permite identificar la peticion ajax hecha por el usuario
	 */
	public void agregarActosMasivos(ActionEvent aae_ae)
	{
		StringBuilder lsb_Errors;

		lsb_Errors = new StringBuilder();

		try
		{
			Collection<PredioActoIU> lcpaui_tmp;
			String                   ls_rangoInicio;
			String                   ls_rangoFin;

			lcpaui_tmp         = getActosAsociadosGeneral();
			ls_rangoInicio     = getRangoInicio();
			ls_rangoFin        = getRangoFin();

			if(StringUtils.isValidString(ls_rangoInicio) && StringUtils.isValidString(ls_rangoFin))
			{
				if(!CollectionUtils.isValidCollection(lcpaui_tmp))
					lcpaui_tmp = new ArrayList<PredioActoIU>();
				else
				{
					for(PredioActoIU lpaui_actual : lcpaui_tmp)
					{
						if(lpaui_actual != null)
						{
							String ls_matriculaActual;

							ls_matriculaActual = lpaui_actual.getMatricula();

							if(!StringUtils.isValidString(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_TABLA_MATRICULA_INGRESADA);

							if(ls_rangoInicio.equalsIgnoreCase(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_INICIAL);

							if(ls_rangoFin.equalsIgnoreCase(ls_matriculaActual))
								throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_FINAL);
						}
					}
				}

				{
					long ll_desde;
					long ll_hasta;

					ll_desde     = NumericUtils.getLong(ls_rangoInicio);
					ll_hasta     = NumericUtils.getLong(ls_rangoFin);

					if(ll_desde > ll_hasta)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS);

					if(ll_desde < 1)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_INICIAL_FORMATO);

					if(ll_hasta < 1)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGO_FINAL_FORMATO);

					{
						long ll_rangoMaximo;

						ll_rangoMaximo = ll_hasta - ll_desde;

						if(ll_rangoMaximo > 1000)
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS_LIMITE);
					}

					{
						CirculoRegistral lcr_circulo;

						lcr_circulo = new CirculoRegistral();

						lcr_circulo.setIdCirculo(getIdCirculo());

						lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

						if(lcr_circulo != null)
						{
							long ll_contador;

							ll_contador = 0L;

							for(long ll_numeroMatricula = ll_desde; ll_numeroMatricula <= ll_hasta;
								    ll_numeroMatricula++
							)
							{
								String       ls_matriculaConcatenada;
								String       ls_error;
								PredioActoIU lpaui_nuevo;

								ls_error        = null;
								lpaui_nuevo     = new PredioActoIU(getColumns());

								{
									PredioRegistro lpr_predio;

									lpr_predio = new PredioRegistro();

									String ls_idCirculo;
									ls_idCirculo = lcr_circulo.getIdCirculo();

									lpr_predio.setIdCirculo(ls_idCirculo);
									lpr_predio.setIdMatricula(ll_numeroMatricula);

									lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

									if(lpr_predio != null)
									{
										int li_contarMatriculasIguales;

										boolean lb_matriculaRepetidaTurno;
										lb_matriculaRepetidaTurno = false;

										{
											li_contarMatriculasIguales     = 0;

											lpaui_nuevo     = new PredioActoIU(getColumns());

											ls_matriculaConcatenada = StringUtils.getStringNotNull(
												    lpr_predio.getIdMatricula() + ""
												);

											lpaui_nuevo.setMatricula(ls_matriculaConcatenada);

											{
												DireccionPredio ld_direccion;

												ld_direccion = new DireccionPredio();

												ld_direccion.setIdCirculo(lpr_predio.getIdCirculo());
												ld_direccion.setIdMatricula(
												    NumericUtils.getLongWrapper(lpr_predio.getIdMatricula())
												);

												ld_direccion = irr_registroRemote.findDireccionPredioById(
													    ld_direccion, getUserId()
													);

												if(ld_direccion != null)
													lpaui_nuevo.setDireccion(ld_direccion.getDireccion());
												else
													ls_error = (getMessages()
															            .getString(
															    MessagesKeys.LA_MATRICULA_INMOBILIARIA
															) + ls_matriculaConcatenada
														+ getMessages().getString(MessagesKeys.NO_RELACIONA_DIRECCION));
											}

											List<Map<String, Object>> llllhm_matriculas;

											llllhm_matriculas = getMatriculasData();

											if(CollectionUtils.isValidCollection(llllhm_matriculas))
											{
												String ls_numeroMatricula;
												ls_numeroMatricula = StringUtils.getString(ll_numeroMatricula);

												for(Map<String, Object> llhm_data : llllhm_matriculas)
												{
													if(llhm_data != null)
													{
														String ls_circuloMatricula;
														ls_circuloMatricula = llhm_data.get(
															    IdentificadoresCommon.MATRICULA
															).toString();

														String[] las_cirMat;
														las_cirMat = ls_circuloMatricula.split("-");

														String ls_circulo;
														ls_circulo = las_cirMat[0];

														String ls_matricula;
														ls_matricula = las_cirMat[1];

														if(
														    ls_circulo.equals(ls_idCirculo)
															    && ls_numeroMatricula.equals(ls_matricula)
														)
														{
															lb_matriculaRepetidaTurno = true;

															break;
														}
													}
												}
											}

											if(CollectionUtils.isValidCollection(lcpaui_tmp))
											{
												for(PredioActoIU lpaui_actual : lcpaui_tmp)
												{
													if(lpaui_actual != null)
													{
														String ls_matriculaCargada;

														ls_matriculaCargada = lpaui_actual.getMatricula();

														if(
														    ls_matriculaConcatenada.equalsIgnoreCase(
															        ls_matriculaCargada
															    )
														)
															li_contarMatriculasIguales++;
													}
												}
											}

											if(li_contarMatriculasIguales >= 1)
												ls_error = (getMessages()
														            .getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
													+ ls_matriculaConcatenada
													+ getMessages().getString(MessagesKeys.YA_SE_ENCUENTRA_INSERTADA));
											else if(!lb_matriculaRepetidaTurno)
												lcpaui_tmp.add(lpaui_nuevo);
										}

										{
											String ls_estadoPredio;

											ls_estadoPredio = lpr_predio.getIdEstadoPredio();

											if(StringUtils.isValidString(ls_estadoPredio))
											{
												EstadoPredio lep_estado;

												lep_estado = new EstadoPredio();

												lep_estado.setIdEstadoPredio(ls_estadoPredio);

												lep_estado = irr_registroRemote.findEstadoPredioById(
													    lep_estado, getUserId()
													);

												if(lep_estado != null)
												{
													String ls_estado;

													ls_estado = lep_estado.getNombre();

													if(
													    StringUtils.isValidString(ls_estado)
														    && ls_estado.toUpperCase()
														                    .equalsIgnoreCase(
														        EstadoCommon.ESTADO_CERRADO
														    )
													)
														ls_error = (getMessages()
																            .getString(
																    MessagesKeys.LA_MATRICULA_INMOBILIARIA
																) + getIdCirculo() + "-" + ll_numeroMatricula
															+ getMessages()
																      .getString(
																    MessagesKeys.A_INCLUIR_ESTA_ESTADO_CERRADO
																));
												}
											}
										}

										{
											String ls_turnoBloqueo;

											ls_turnoBloqueo = lpr_predio.getTurnoBloqueo();

											if(StringUtils.isValidString(ls_turnoBloqueo))
												ls_error = (getMessages().getString(MessagesKeys.EL_FOLIO_DE_MATRICULA)
													+ getIdCirculo() + "-" + ll_numeroMatricula
													+ getMessages()
														      .getString(
														    MessagesKeys.SE_ENCUENTRA_BLOQUEADO_CON_EL_TUNRO
														) + ls_turnoBloqueo + ". ");
										}

										if(lb_matriculaRepetidaTurno)
											ls_error = getMessages()
													           .getString(
													    MessagesKeys.NO_SE_PUEDE_AGREGAR_LA_MATRICULA
													) + ll_numeroMatricula
												+ getMessages()
													      .getString(MessagesKeys.PORQUE_YA_SE_ENCUENTRA_RELACIONADA);
									}
									else
									{
										ls_error = (getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
											+ getIdCirculo() + "-" + ll_numeroMatricula
											+ getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE));
										ll_contador++;
									}
								}

								if(StringUtils.isValidString(ls_error))
									lsb_Errors.append(ls_error);
							}

							{
								long ll_rango;

								ll_rango = 0L;

								for(long cont = ll_desde; cont <= ll_hasta; cont++)
									ll_rango++;

								if((ll_rango == ll_contador))
								{
									lsb_Errors = new StringBuilder();

									lsb_Errors.append(
									    getMessages().getString(MessagesKeys.PARA_RANGO_INGRESADO_NO_EXISTEN_PREDIOS)
									);
								}
							}
						}
						else
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = getIdCirculo();

							throw new B2BException(ErrorKeys.ERROR_CIRCULO_NO_EXISTE, aoa_messageArgs);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_MATRICULA_RANGOS_2);

			setActosAsociadosGeneral(lcpaui_tmp);

			if(StringUtils.isValidString(lsb_Errors.toString()))
			{
				addMessage("I", lsb_Errors.toString());
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActosMasivos", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 *  Metodo encargado de agrega todo el formulario diligenciado en la tabla Anotaciones agregadas.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarAnotacion()
	    throws B2BException
	{
		agregarAnotacion(false, null, null);
	}

	/**
	 * Metodo encargado de agrega todo el formulario diligenciado en la tabla Anotaciones agregadas.
	 *
	 * @param ab_salvarAnotacion Argumento de tipo <code>boolean</code> que define <code>true</code> si se debe salvar directamente el registro en la base de datos de lo contrario <code>false</code>.
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene el id circulo.
	 * @param al_idMatricula Argumento de tipo <code>Long</code> que contiene el id matricula.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarAnotacion(boolean ab_salvarAnotacion, String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		agregarAnotacion(ab_salvarAnotacion, true, as_idCirculo, al_idMatricula);
	}

	/**
	 * Metodo encargado de agrega todo el formulario diligenciado en la tabla Anotaciones agregadas.
	 *
	 * @param ab_salvarAnotacion Argumento de tipo <code>boolean</code> que define <code>true</code> si se debe salvar directamente el registro en la base de datos de lo contrario <code>false</code>.
	 * @param ab_noCorreccion Argumento de tipo <code>boolean</code> que define <code>true</code> si se debe validar el campo anotacion1, de lo contrario <code>false</code>.
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene el id circulo.
	 * @param al_idMatricula Argumento de tipo <code>Long</code> que contiene el id matricula.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarAnotacion(
	    boolean ab_salvarAnotacion, boolean ab_noCorreccion, String as_idCirculo, Long al_idMatricula
	)
	    throws B2BException
	{
		try
		{
			if(isBloquearModificarIntervenientes())
				throw new B2BException(ErrorKeys.ERROR_MODIFICACION_INTERVINIENTES_INCOMPLETA);

			AnotacionPredio   lap_anotacionPredio;
			boolean           lb_bloqueo;
			boolean           lb_focus;
			boolean           lb_validar;
			Documento         ld_documento;
			DetalleAntSistema ldas_detalleAnotacion;
			FacesContext      lfc_context;
			String            ls_descripcion;
			String            ls_idFormulario;

			lap_anotacionPredio       = getAnotacionPredio();
			lb_focus                  = true;
			lb_bloqueo                = (lap_anotacionPredio != null) ? lap_anotacionPredio.isBloqueo() : false;
			ld_documento              = getDocumento();
			ldas_detalleAnotacion     = getDetalleAntSistemaAnotacion();
			lfc_context               = FacesContext.getCurrentInstance();
			ls_descripcion            = null;
			ls_idFormulario           = getFormulario();
			lb_validar                = StringUtils.isValidString(ls_idFormulario)
					&& ls_idFormulario.equalsIgnoreCase(is_idFormulario_ant_sistema);

			validarPanelDatosAnotacion(lap_anotacionPredio, ab_salvarAnotacion, lb_focus, ls_idFormulario, lfc_context);
			validarPanelDatosDocumento(ld_documento, lb_focus, ls_idFormulario, lfc_context);
			validarFechaDocumentoRadicacion(false);
			validarPanelDetalleAnotacion(ldas_detalleAnotacion, lb_validar);
			ls_descripcion = validarPanelEspecificacion(
				    lap_anotacionPredio, ab_noCorreccion, lb_focus, ls_idFormulario, lfc_context
				);

			{
				Collection<Anotacion> lca_intervinientesAgregados;
				Collection<Anotacion> lca_datos;
				Anotacion             la_anotacion;
				boolean               lb_conAnotacion;
				long                  ll_anotacionTmp;
				long                  ll_idAnotacion;
				long                  li_contador;
				Long                  ll_numeroAnotacion;

				lca_intervinientesAgregados     = getIntervinientesAgregados();
				lca_datos                       = getAnotacionesAgregadas();
				la_anotacion                    = getAnotacion();
				ll_numeroAnotacion              = getNumeroAnotacion();
				ll_idAnotacion                  = getIdAnotacionGeneral();
				lb_conAnotacion                 = ll_idAnotacion > 0;
				ll_anotacionTmp                 = NumericUtils.getLong(ll_numeroAnotacion);
				li_contador                     = getContadorAnotaciones();

				cargarPrediosEnglobeCorreccion(as_idCirculo, al_idMatricula);

				// TODO Mover
				// validarCierreFolioAnotacion(la_anotacion);
				validarPanelIntervinientes(
				    lca_datos, lca_intervinientesAgregados, lap_anotacionPredio, la_anotacion, lb_conAnotacion,
				    lb_bloqueo, ll_anotacionTmp, ll_numeroAnotacion, ls_idFormulario, lfc_context
				);

				if(ab_salvarAnotacion)
					lap_anotacionPredio.setOrden(NumericUtils.getBigDecimal(ll_anotacionTmp));

				la_anotacion.setAnotacionPredio(lap_anotacionPredio);
				la_anotacion.setDocumento(ld_documento);
				la_anotacion.setAnotacionCancelacion(getAnotacionCancelacion());
				la_anotacion.setDatosAntiguoSistema(getDatosAntSistemaAnotacion());
				la_anotacion.setIntervinientesAgregados(lca_intervinientesAgregados);
				la_anotacion.setMatriculasSegregadas(getMatriculasSegregadas());
				la_anotacion.setNaturalezaJuridica(ls_descripcion);
				la_anotacion.setDetalleAntSistema(ldas_detalleAnotacion);
				la_anotacion.setBloqueo(lb_bloqueo || isGrabacion());

				if(lb_conAnotacion)
				{
					if(CollectionUtils.isValidCollection(lca_datos))
					{
						Collection<Anotacion> lca_tmp;
						lca_tmp = new ArrayList<Anotacion>();

						for(Anotacion la_iterador : lca_datos)
						{
							if(la_iterador != null)
							{
								long ll_idAnotacionDatos;
								ll_idAnotacionDatos = la_iterador.getIdAnotacion();

								if(ll_idAnotacionDatos == ll_idAnotacion)
									lca_tmp.add(la_anotacion);
								else
									lca_tmp.add(la_iterador);
							}
						}

						setAnotacionesAgregadas(lca_tmp);
						setAnotacionApertura(false);
						setAnotacionEnglobe(false);
						setIdAnotacionGeneral(-1);
					}
				}
				else
				{
					if(
					    ab_salvarAnotacion && StringUtils.isValidString(as_idCirculo)
						    && NumericUtils.isValidLong(al_idMatricula)
					)
					{
						{
							AccPredioRegistro lapr_accPredioRegistro;

							lapr_accPredioRegistro = new AccPredioRegistro();

							lapr_accPredioRegistro.setIdCirculo(as_idCirculo);
							lapr_accPredioRegistro.setIdMatricula(al_idMatricula);

							la_anotacion.setAccPredioRegistro(lapr_accPredioRegistro);
						}

						{
							Collection<Anotacion> lca_anotacionAgregada;

							lca_anotacionAgregada = new ArrayList<Anotacion>();

							lca_anotacionAgregada.add(la_anotacion);

							la_anotacion.setAnotacionesAgregadas(lca_anotacionAgregada);
						}

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							la_anotacion.setTurnoHistoria(lth_turnoHistoria);
						}

						la_anotacion.setBloqueo(true);
						la_anotacion.setAnotacionIndividual(ab_salvarAnotacion);

						la_anotacion = iasr_antiguoSistemaRemote.salvarAnotaciones(
							    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
					}

					if(!CollectionUtils.isValidCollection(lca_datos))
						lca_datos = new ArrayList<Anotacion>();

					lca_datos.add(la_anotacion);

					CollectionUtils.sort(lca_datos);

					setContadorAnotaciones(li_contador);
					setAnotacionesAgregadas(lca_datos);
				}

				setAnotacionTemporal(la_anotacion);

				if(isGrabacion())
				{
					AnotacionPredio lap_anotacion;

					lap_anotacion = new AnotacionPredio();

					if(lap_anotacion != null)
					{
						lap_anotacion.setRadicacion(getIdTurno());
						lap_anotacion.setIdEstadoAnotacion(EstadoCommon.V);

						setAnotacionPredio(lap_anotacion);
					}
				}
				else
					setAnotacionPredio(null);

				setDocumento(null);
				setAnotacionCancelacion(null);
				setIntervinientesAgregados(null);
				setMatriculasSegregadas(null);
				setPredioSegregado(null);

				if(!ab_salvarAnotacion)
					setIdMatricula(null);

				setNaturalezaJuridicaSeleccionada(null);
				setDatosAntSistemaAnotacion(null);
				setTipoDocInterviniente(null);
				setDocumentoInterviniente(null);
				setIdPersonaSeleccion(null);
				setListadoIntervinientes(null);
				setAnotacionAgregada(true);
				setDatosAntiguoSistema(null);
				setAnotacion(null);
				setCodigoNaturalezaJuridicaSeleccionada(null);
				setDeshabilitarNumeroAnotacion(false);

				if(lb_bloqueo)
				{
					setNumeroAnotacion(null);
					setNumeroOrden(null);
				}
				else
					setNumeroAnotacion(NumericUtils.getLongWrapper(++ll_anotacionTmp));

				setDetalleAntSistemaAnotacion(null);
				setPersona(new Persona());
				setAnotacionPredioCiudadano(new AnotacionPredioCiudadano());
				setSolicitudInterviniente(new SolicitudInterviniente());

				if(ab_salvarAnotacion)
					addMessage(MessagesKeys.ANOTACION_AGREGADA_CORRECTAMENTE);
				else
				{
					int li_numeroAnotacion;

					li_numeroAnotacion = lca_datos.size();

					setNumeroAnotacion(NumericUtils.getLongWrapper(++li_numeroAnotacion));

					addMessage(MessagesKeys.ANOTACION_MODIFICADA_CORRECTAMENTE);
				}

				PrimeFaces.current().ajax().update("idFormAntSistema:globalMsg");
			}

			{
				Collection<DetalleAntSistema> lcdas_detalles;

				lcdas_detalles = getDetallesAntSistema();

				if(CollectionUtils.isValidCollection(lcdas_detalles))
				{
					Iterator<DetalleAntSistema> lidas_iterator;

					lidas_iterator = lcdas_detalles.iterator();

					while(lidas_iterator.hasNext())
					{
						DetalleAntSistema ldas_detalle;

						ldas_detalle = lidas_iterator.next();

						if((ldas_detalle != null))
							ldas_detalle.setSeleccionado(false);
					}
				}

				setDetalleAntSistemaAnotacion(null);
			}

			setBloquearModificarAnotaciones(false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarAnotacion", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);

			if(ab_salvarAnotacion)
				throw lb2be_e;
		}
	}

	/**
	 * Método encargado de agregar un detalle de area a un predio especifico.
	 */
	public void agregarArea()
	{
		String ls_idForm;

		ls_idForm = getFormulario();

		try
		{
			AccAreaUI         laaui_dataArea;
			DetalleAreaPredio ladap_detalleArea;

			laaui_dataArea        = getAreaUI();
			ladap_detalleArea     = getDetalleAreaTerreno();

			if((laaui_dataArea != null) && (ladap_detalleArea != null))
			{
				boolean      lb_focus;
				BigInteger   lbi_count;
				FacesContext lfc_context;
				String       ls_area;

				lb_focus        = false;
				lbi_count       = laaui_dataArea.getIdDetalleAreaPredio();
				lfc_context     = FacesContext.getCurrentInstance();
				ls_area         = ladap_detalleArea.getAreaLectura();
				lb_focus        = validateStyles(ls_idForm + ":idITareaTerreno", lfc_context, ls_area, lb_focus);

				if(StringUtils.isValidString(ls_area))
				{
					Double ld_area;
					String ls_medidaArea;

					ld_area           = NumericUtils.getDoubleWrapper(ls_area);
					ls_medidaArea     = ladap_detalleArea.getIdUnidadMedida();

					if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
					{
						lb_focus = validateStyles(ls_idForm + ":idITareaTerreno", lfc_context, ls_area, lb_focus);
						ladap_detalleArea.setArea(ld_area);
					}
					else
					{
						lb_focus = validateStyles(ls_idForm + ":idITareaTerreno", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
					}

					lb_focus = validateStyles(
						    ls_idForm + ":idSOMunidadMedidaTerreno", lfc_context, ls_medidaArea, lb_focus
						);

					if(StringUtils.isValidString(ls_medidaArea))
					{
						Collection<DetalleAreaPredio> lcadap_areas;

						lcadap_areas = laaui_dataArea.getAreasTerreno();

						if(CollectionUtils.isValidCollection(lcadap_areas))
						{
							Iterator<DetalleAreaPredio> liadap_iterator;

							liadap_iterator = lcadap_areas.iterator();

							while(liadap_iterator.hasNext())
							{
								DetalleAreaPredio ladap_areaTerreno;

								ladap_areaTerreno = liadap_iterator.next();

								if(ladap_areaTerreno != null)
								{
									String ls_idDetalleAreaPredio;

									ls_idDetalleAreaPredio = ladap_areaTerreno.getIdDetalleAreaPredio();

									if(StringUtils.isValidString(ls_idDetalleAreaPredio))
									{
										BigInteger lbi_idDetalleAreaPredio;
										String     ls_medidaAreaIterador;

										ls_medidaAreaIterador       = ladap_areaTerreno.getIdUnidadMedida();
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
													    ls_idForm + ":idSOMunidadMedidaTerreno", lfc_context, "",
													    lb_focus
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

						ladap_detalleArea.setIdTipoArea(TipoAreaCommon.TERRENO);
						ladap_detalleArea.setIdDetalleAreaPredio(StringUtils.getString(lbi_count));
						lcadap_areas.add(ladap_detalleArea);
						laaui_dataArea.setIdDetalleAreaPredio(lbi_count);
						laaui_dataArea.setAreasTerreno(lcadap_areas);

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
			clh_LOGGER.error("agregarArea", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(ls_idForm + is_mensajes);
		}
	}

	/**
	 * Método encargado de verificar la información suministrada para un interviniente y lo agrega al proceso de anotaciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarIntervinientes()
	    throws B2BException
	{
		try
		{
			agregarIntervinientesData(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarIntervinientes", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de verificar la información suministrada para un interviniente y lo agrega al proceso de anotaciones.
	 *
	 * @param ab_validarPais Argumento de tipo <code>boolean</code> que indica si se debe validar el país.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarIntervinientesData(boolean ab_validarPais)
	    throws B2BException
	{
		agregarIntervinientesData(ab_validarPais, false);
	}

	/**
	 * Método encargado de verificar la información suministrada para un interviniente y lo agrega al proceso de anotaciones.
	 *
	 * @param ab_validarPais Argumento de tipo <code>boolean</code> que indica si se debe validar el país.
	 * @param ab_noValidarGenero de ab no validar genero
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	public void agregarIntervinientesData(boolean ab_validarPais, boolean ab_noValidarGenero)
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacion;
			Long      ll_numeroAnotacion;
			Persona   lp_parametros;
			boolean   lb_focus;
			String    ls_idFormulario;
			String    ls_idPersona;

			la_anotacion           = new Anotacion();
			ll_numeroAnotacion     = getNumeroAnotacion();
			lp_parametros          = getPersona();
			lb_focus               = true;
			ls_idFormulario        = getFormulario();
			ls_idPersona           = null;

			if(lp_parametros != null)
			{
				String       ls_tipoPersona;
				String       ls_tipoDocumento;
				FacesContext lfc_context;

				ls_tipoPersona       = lp_parametros.getIdTipoPersona();
				ls_tipoDocumento     = lp_parametros.getIdDocumentoTipo();
				lfc_context          = FacesContext.getCurrentInstance();
				ls_idPersona         = lp_parametros.getIdPersona();

				lb_focus = validateStyles(
					    ls_idFormulario + ":idItNumeroAnotacionID", lfc_context, ll_numeroAnotacion, lb_focus
					);

				if(!NumericUtils.isValidLong(ll_numeroAnotacion))
					throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION);

				lb_focus = validateStyles(
					    ls_idFormulario + ":idSOMTipoPersonaInter", lfc_context, ls_tipoPersona, lb_focus
					);

				if(!StringUtils.isValidString(ls_tipoPersona))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

				lb_focus = validateStyles(
					    ls_idFormulario + ":idSOMTipoDocInter", lfc_context, ls_tipoDocumento, lb_focus
					);

				if(!StringUtils.isValidString(ls_tipoDocumento))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

				{
					String ls_documento;
					ls_documento     = lp_parametros.getNumeroDocumento();

					lb_focus = validateStyles(
						    ls_idFormulario + ":idOlDocumentoInter", lfc_context, ls_documento, lb_focus
						);

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
						if(ab_validarPais)
						{
							String ls_nacionalidad;
							ls_nacionalidad     = lp_parametros.getIdPais();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idSOMNacionalidadInter", lfc_context, ls_nacionalidad, lb_focus
								);

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							String ls_primerNombre;
							ls_primerNombre     = lp_parametros.getPrimerNombre();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idOlPNombreInter", lfc_context, ls_primerNombre, lb_focus
								);

							if(!StringUtils.isValidString(ls_primerNombre))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
						}

						{
							String ls_primerApellido;
							ls_primerApellido     = lp_parametros.getPrimerApellido();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idOlPApellidoInter", lfc_context, ls_primerApellido, lb_focus
								);

							if(!StringUtils.isValidString(ls_primerApellido))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
						}

						{
							SolicitudInterviniente lsi_parametros;
							lsi_parametros = getSolicitudInterviniente();

							if(lsi_parametros != null)
							{
								String ls_rol;
								ls_rol     = lsi_parametros.getRolPersona();

								lb_focus = validateStyles(
									    ls_idFormulario + ":idOlRolInter", lfc_context, ls_rol, lb_focus
									);

								if(!StringUtils.isValidString(ls_rol))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
							}
						}

						if(!ab_noValidarGenero)
						{
							String ls_genero;
							ls_genero     = lp_parametros.getIdNaturalGenero();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idSOMGeneroInter", lfc_context, ls_genero, lb_focus
								);

							if(!StringUtils.isValidString(ls_genero))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
				}
				else if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
				{
					if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					{
						if(ab_validarPais)
						{
							String ls_nacionalidad;
							ls_nacionalidad     = lp_parametros.getIdPais();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idSOMNacionalidadInter", lfc_context, ls_nacionalidad, lb_focus
								);

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							SolicitudInterviniente lsi_parametros;
							lsi_parametros = getSolicitudInterviniente();

							if(lsi_parametros != null)
							{
								String ls_rol;
								ls_rol     = lsi_parametros.getRolPersona();

								lb_focus = validateStyles(
									    ls_idFormulario + ":idOlRolInter", lfc_context, ls_rol, lb_focus
									);

								if(!StringUtils.isValidString(ls_rol))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
							}
						}

						{
							String ls_razonSocial;
							ls_razonSocial     = lp_parametros.getRazonSocial();

							lb_focus = validateStyles(
								    ls_idFormulario + ":idOlRazonSocialInter", lfc_context, ls_razonSocial, lb_focus
								);

							if(!StringUtils.isValidString(ls_razonSocial))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
				}
				else
				{
					SolicitudInterviniente lsi_parametros;
					lsi_parametros = getSolicitudInterviniente();

					if(lsi_parametros != null)
					{
						String ls_rol;
						ls_rol     = lsi_parametros.getRolPersona();

						lb_focus = validateStyles(ls_idFormulario + ":idOlRolInter", lfc_context, ls_rol, lb_focus);

						if(!StringUtils.isValidString(ls_rol))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
					}
				}
			}

			{
				long                  ll_idAnotacion;
				long                  ll_contadorInterviniente;
				boolean               lb_conAnotacion;
				Anotacion             la_temp;
				Collection<Anotacion> lca_datos;

				lca_datos           = getIntervinientesAgregados();
				la_temp             = getIntervinienteActual();
				ll_idAnotacion      = getIdAnotacion();
				lb_conAnotacion     = ll_idAnotacion > 0;

				if(!lb_conAnotacion)
				{
					ll_contadorInterviniente     = CollectionUtils.isValidCollection(lca_datos) ? (lca_datos.size() + 1)
						                                                                        : 1L;
					ll_idAnotacion               = NumericUtils.getLong(ll_numeroAnotacion);
				}
				else
					ll_contadorInterviniente = (la_temp != null) ? la_temp.getContadorInterviniente() : 1L;

				la_anotacion.setIdAnotacion(ll_idAnotacion);
				la_anotacion.setContadorInterviniente(ll_contadorInterviniente);
				la_anotacion.setPersona(getPersona());
				la_anotacion.setSolicitudInterviniente(getSolicitudInterviniente());
				la_anotacion.setPanelDetalleIntervinientes(getPanelDetalleIntervinientes());

				{
					AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

					lapc_anotacionPredioCiudadano = getAnotacionPredioCiudadano();

					if(lapc_anotacionPredioCiudadano != null)
					{
						String ls_idInteresadaRRR;
						String ls_porcentajeParticipacion;

						ls_idInteresadaRRR             = lapc_anotacionPredioCiudadano.getIdInteresadaRrr();
						ls_porcentajeParticipacion     = lapc_anotacionPredioCiudadano.getPorcentajeParticipacion();

						if(StringUtils.isValidString(ls_idInteresadaRRR))
						{
							CalidadSolicitante lcs_calidadSolicitante;

							lcs_calidadSolicitante = new CalidadSolicitante();

							lcs_calidadSolicitante.setIdCalidadSolicitante(ls_idInteresadaRRR);

							lcs_calidadSolicitante = ipr_parameterRemote.findCalidadSolicitanteById(
								    lcs_calidadSolicitante
								);

							if(lcs_calidadSolicitante != null)
								lapc_anotacionPredioCiudadano.setNombreInteresadaRRR(
								    lcs_calidadSolicitante.getNombre()
								);
							else
								lapc_anotacionPredioCiudadano.setNombreInteresadaRRR(null);
						}
						else
							lapc_anotacionPredioCiudadano.setNombreInteresadaRRR(null);

						if(StringUtils.isValidString(ls_porcentajeParticipacion))
						{
							long ld_porcentaje;
							ld_porcentaje = NumericUtils.getLong(ls_porcentajeParticipacion);

							if((ld_porcentaje < 0.0) || (ld_porcentaje > 100.0))
							{
								RequestContext.getCurrentInstance()
									              .execute(
									    "PrimeFaces.focus('idFormAntSistema:idProcesoAntiguoSistema:idOlPorcentajePart');"
									);

								throw new B2BException(ErrorKeys.ERROR_RANGO_PORCENTAJE_INVALIDO);
							}
						}

						la_anotacion.setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);
					}
				}

				{
					if(
					    StringUtils.isValidString(ls_idPersona)
						    && ls_idFormulario.equalsIgnoreCase(is_idFormulario_calificacion)
					)
					{
						AnotacionPredio lap_anotacionPredio;

						lap_anotacionPredio = getAnotacionPredio();

						if(lap_anotacionPredio != null)
						{
							if(
							    StringUtils.isValidString(lap_anotacionPredio.getIdNaturalezaJuridica())
								    && lap_anotacionPredio.getIdNaturalezaJuridica()
								                              .equalsIgnoreCase(TipoActoCommon.BALDIOS)
							)
								alertasIntervinientesBaldios(ls_idPersona, true);
						}
					}

					if(CollectionUtils.isValidCollection(lca_datos))
					{
						Persona                lp_personaTemp;
						SolicitudInterviniente lsi_solicitudTemp;
						boolean                lb_agregar;

						lp_personaTemp        = (la_temp != null) ? la_temp.getPersona() : la_anotacion.getPersona();
						lsi_solicitudTemp     = (la_temp != null) ? la_temp.getSolicitudInterviniente()
							                                      : la_anotacion.getSolicitudInterviniente();
						lb_agregar            = true;

						for(Anotacion la_data : lca_datos)
						{
							if(la_data != null)
							{
								Persona                lp_personaTabla;
								SolicitudInterviniente lsi_solicitudTabla;

								lp_personaTabla        = la_data.getPersona();
								lsi_solicitudTabla     = la_data.getSolicitudInterviniente();

								if(
								    (lp_personaTemp != null) && (lp_personaTabla != null)
									    && (lsi_solicitudTemp != null) && (lsi_solicitudTabla != null)
								)
								{
									String ls_idPersonaTemp;
									String ls_idPersonaTabla;

									ls_idPersonaTemp      = StringUtils.getStringNotNull(lp_personaTemp.getIdPersona());
									ls_idPersonaTabla     = StringUtils.getStringNotNull(
										    lp_personaTabla.getIdPersona()
										);

									if(ls_idPersonaTemp.equalsIgnoreCase(ls_idPersonaTabla))
									{
										String ls_rolTemp;
										String ls_rolTabla;

										ls_rolTemp      = StringUtils.getStringNotNull(
											    lsi_solicitudTemp.getRolPersona()
											);
										ls_rolTabla     = StringUtils.getStringNotNull(
											    lsi_solicitudTabla.getRolPersona()
											);

										if(ls_rolTemp.equalsIgnoreCase(ls_rolTabla))
										{
											long ll_contadorInterTabla;

											ll_contadorInterTabla = la_data.getContadorInterviniente();

											if(ll_contadorInterTabla != ll_contadorInterviniente)
											{
												Object[] loa_messageArgs = new String[1];
												loa_messageArgs[0] = ls_rolTemp;

												throw new B2BException(
												    ErrorKeys.ERROR_INTERVINIENTE_REPETIDO, loa_messageArgs
												);
											}
											else
												lb_agregar = false;
										}
										else if(!isBloquearModificarIntervenientes())
											lb_agregar = true;
									}
								}
							}
						}

						if(lb_agregar && !lca_datos.contains(la_anotacion))
							lca_datos.add(la_anotacion);
					}
					else
					{
						lca_datos = new ArrayList<Anotacion>();

						lca_datos.add(la_anotacion);
					}

					Collection<Anotacion> lca_tmp;
					lca_tmp = new ArrayList<Anotacion>();

					for(Anotacion la_iterador : lca_datos)
					{
						if(la_iterador != null)
						{
							long ll_idAnotacionDatos;
							long ll_contadorInter;

							ll_idAnotacionDatos     = la_iterador.getIdAnotacion();
							ll_contadorInter        = la_iterador.getContadorInterviniente();

							if(
							    ((ll_idAnotacionDatos == ll_idAnotacion)
								    && (ll_contadorInter == ll_contadorInterviniente))
								    && !lca_tmp.contains(la_anotacion)
							)
								lca_tmp.add(la_anotacion);
							else if(!lca_tmp.contains(la_iterador))
								lca_tmp.add(la_iterador);
						}
					}

					setIntervinientesAgregados(lca_tmp);
				}

				setPersona(null);
				setSolicitudInterviniente(null);
				setAnotacionPredioCiudadano(null);
				setTipoDocInterviniente(null);
				setDocumentoInterviniente(null);
				setListadoIntervinientes(null);
				setDeshabilitarDatosInterviniente(false);
				setMostrarListadoPersonas(false);
				setIntervinienteActual(null);
				setPanelDetalleIntervinientes(null);
				setIdAnotacion(-1L);
			}

			addMessage(
			    isBloquearModificarIntervenientes() ? MessagesKeys.INTERVINIENTE_MODIFICADO
			                                        : MessagesKeys.INTERVINIENTE_AGREGADO
			);

			setBloquearModificarIntervenientes(false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarIntervinientes", lb2be_e);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
	}

	/**
	 * Método encargado de agregar una matricula base para una matricula de antiguo sistema.
	 */
	public void agregarMatriculaBase()
	{
		String ls_idFormulario;

		ls_idFormulario = getFormulario();

		try
		{
			boolean       lb_focus;
			MatriculaBase lmb_matricula;

			lb_focus          = true;
			lmb_matricula     = getDatosBasicos().getMatriculaBase();

			if(lmb_matricula != null)
			{
				FacesContext lfc_context;
				lfc_context = FacesContext.getCurrentInstance();

				String ls_idCirculo;
				long   ll_idMatricula;

				ls_idCirculo       = lmb_matricula.getIdCirculo();
				ll_idMatricula     = NumericUtils.isValidLong(lmb_matricula.getIdMatricula(), 1)
					? NumericUtils.getLong(lmb_matricula.getIdMatricula()) : (-1L);

				if(!validateStyles(ls_idFormulario + ":circuloRegistralBase", lfc_context, ls_idCirculo, lb_focus))
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

				String ls_validator;

				if(ll_idMatricula > 0)
					ls_validator = StringUtils.getString(ll_idMatricula);
				else
					ls_validator = "";

				if(!validateStyles(ls_idFormulario + ":idItMatricula", lfc_context, ls_validator, lb_focus))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

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

										ldp_direccionPredio.setAgregado(true);

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
										ldp_direccionPredio.setAgregado(true);

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

						validarTramiteSuspension(ls_idCirculo, StringUtils.getString(ll_idMatricula));
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
			clh_LOGGER.error("agregarMatriculaBase", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(ls_idFormulario + is_mensajes);
		}
	}

	/**
	 * Metodo encargado de agregar matriculas para el proceso de complementacion copiada de matriculas.
	 *
	 * @param ab_cargar bandera para saber el flujo con el cual se mostrará la información en pantalla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarMatriculaComplementacion(boolean ab_cargar)
	    throws B2BException
	{
		agregarMatriculaComplementacion(ab_cargar, false, false, false, false);
	}

	/**
	 * Metodo encargado de agregar matriculas para el proceso de complementacion copiada de matriculas.
	 *
	 * @param ab_cargar bandera para saber el flujo con el cual se mostrará la información en pantalla
	 * @param ab_apartirDeAnotaciones bandera para cambiar el flujo a la opcion de construir apartir de anotaciones
	 * @param ab_desdeDigitadorMasivo correspondiente al valor del tipo de objeto boolean
	 * @param ab_primerVezDigMasivo correspondiente al valor del tipo de objeto boolean
	 * @param ab_antSistema correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarMatriculaComplementacion(
	    boolean ab_cargar, boolean ab_apartirDeAnotaciones, boolean ab_desdeDigitadorMasivo,
	    boolean ab_primerVezDigMasivo, boolean ab_antSistema
	)
	    throws B2BException
	{
		try
		{
			if(ab_antSistema)
			{
				ComplementacionCalificacion lcc_complementacion;

				lcc_complementacion = getComplementacionCalificacion();

				if(lcc_complementacion != null)
				{
					Complementacion lc_complementacion;

					lc_complementacion = lcc_complementacion.getComplementacion();

					if(lc_complementacion != null)
					{
						AccPredioRegistro lapr_predio;

						lapr_predio = getAccPredioRegistro();

						if(lapr_predio != null)
							lc_complementacion.setIdCirculo(lapr_predio.getIdCirculo());
					}
				}
			}

			if(!ab_cargar)
			{
				ComplementacionCalificacion lcc_complementacion;

				lcc_complementacion = getComplementacionCalificacion();

				if(lcc_complementacion != null)
				{
					Complementacion lc_complementacion;

					lc_complementacion = lcc_complementacion.getComplementacion();

					if(lc_complementacion != null)
					{
						Long ll_idMatricula;

						ll_idMatricula = lc_complementacion.getIdMatricula();

						if(NumericUtils.isValidLong(ll_idMatricula))
						{
							setMatriculasParaGenerarComplementacion(
							    irr_calificacionRemote.agregarMatriculaComplementacion(
							        getMatriculasParaGenerarComplementacion(), getComplementacionCalificacion(),
							        getMatriculas(), StringUtils.getString(ll_idMatricula), isPrimerVez(), getUserId(),
							        getLocalIpAddress(), getRemoteIpAddress()
							    )
							);
							lc_complementacion.setIdMatricula(null);
						}
						else
							throw new B2BException(ErrorKeys.INGRESE_MATRICULA_VALIDA);
					}
				}
			}
			else if(!isPrimerVez())
			{
				RegistroCalificacion lrc_registroCalificacion;

				lrc_registroCalificacion = getMatriculas();

				{
					if(ab_antSistema)
					{
						AccPredioRegistro lapr_predioRegistro;

						lapr_predioRegistro = getAccPredioRegistro();

						if(lapr_predioRegistro != null)
						{
							Collection<RegistroCalificacion> lcrc_allMatriculas;
							RegistroCalificacion             lrc_matricula;

							lcrc_allMatriculas           = new ArrayList<RegistroCalificacion>();
							lrc_registroCalificacion     = new RegistroCalificacion();
							lrc_matricula                = new RegistroCalificacion();

							lrc_matricula.setIdCirculo(
							    lapr_predioRegistro.getIdCirculo() + "-" + lapr_predioRegistro.getIdMatricula()
							);

							lcrc_allMatriculas.add(lrc_matricula);
							lrc_registroCalificacion.setAllMatriculas(lcrc_allMatriculas);
						}
					}
				}

				setMatriculasParaGenerarComplementacion(
				    irr_calificacionRemote.agregarMatriculaComplementacion(
				        getMatriculasParaGenerarComplementacion(), getComplementacionCalificacion(),
				        lrc_registroCalificacion, null, isPrimerVez(), getUserId(), getLocalIpAddress(),
				        getRemoteIpAddress()
				    )
				);
				setPrimerVez(true);
			}

			setComplementacionSinConstruir(true);
			setHabilitarMatriculasComplementacionAnotacion(true);

			if(ab_apartirDeAnotaciones)
				construirComplementacion();
			else
			{
				if(ab_cargar && !ab_primerVezDigMasivo)
					validarCopiarDeUnaMatricula(ab_desdeDigitadorMasivo, false);
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
			PrimeFaces.current().ajax().update(getFormulario() + is_mensajes);
		}
	}

	/**
	 * Método encargado de agregar un predio segregado para una matricula de antiguo sistema.
	 */
	public void agregarMatriculas()
	{
		try
		{
			PredioSegregado lps_predioSegregado;
			lps_predioSegregado = getPredioSegregado();

			if(lps_predioSegregado != null)
			{
				PredioRegistro lpr_predioRegistro;
				Anotacion      la_parametros;
				long           ll_idMatricula;
				String         ls_idCirculo;

				lpr_predioRegistro     = new PredioRegistro();
				la_parametros          = new Anotacion();
				ll_idMatricula         = NumericUtils.isValidLong(getIdMatricula())
					? NumericUtils.getLong(getIdMatricula()) : 0;

				ls_idCirculo = null;

				if(ll_idMatricula <= 0)
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

				ls_idCirculo = lps_predioSegregado.getIdCirculo();
				lpr_predioRegistro.setIdMatricula(ll_idMatricula);
				lpr_predioRegistro.setIdCirculo(ls_idCirculo);

				la_parametros.setPredioRegistro(lpr_predioRegistro);

				la_parametros = iasr_antiguoSistemaRemote.findPredioRegistroById(la_parametros, getUserId());

				if(la_parametros != null)
				{
					Collection<Anotacion> lca_matriculasSegregadas;
					DireccionPredio       ldp_direccionPredio;

					lca_matriculasSegregadas     = getMatriculasSegregadas();
					ldp_direccionPredio          = la_parametros.getDireccionPredio();

					if(!CollectionUtils.isValidCollection(lca_matriculasSegregadas))
						lca_matriculasSegregadas = new ArrayList<Anotacion>();

					if(ldp_direccionPredio != null)
					{
						StringBuilder lsb_direccionCompleta;
						String        ls_direccionCompleta;

						lsb_direccionCompleta     = new StringBuilder();
						ls_direccionCompleta      = null;

						{
							String ls_tipoEje;
							ls_tipoEje = ldp_direccionPredio.getIdTipoEjePrincipal();

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
						    StringUtils.getStringNotNull(ldp_direccionPredio.getDatoEjePrincipal()) + " "
						);

						{
							String ls_tipoEje1;
							ls_tipoEje1 = ldp_direccionPredio.getIdTipoEjeSecundario();

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
						    StringUtils.getStringNotNull(ldp_direccionPredio.getDatoEjeSecundario()) + " "
						);
						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(ldp_direccionPredio.getComplementoDireccion()) + " "
						);

						{
							String ls_tmp;
							ls_tmp = lsb_direccionCompleta.toString();

							if(StringUtils.isValidString(ls_tmp))
								ls_direccionCompleta = ls_tmp.trim();

							la_parametros.setDireccion(ls_direccionCompleta);
						}
					}

					if(CollectionUtils.isValidCollection(lca_matriculasSegregadas))
					{
						for(Anotacion ia_iterador : lca_matriculasSegregadas)
						{
							if(ia_iterador != null)
							{
								PredioRegistro lipr_predioRegistro;
								lipr_predioRegistro = ia_iterador.getPredioRegistro();

								if(lipr_predioRegistro != null)
								{
									long   lli_idMatricula;
									String ls_idCirculoIt;
									lli_idMatricula     = lipr_predioRegistro.getIdMatricula();
									ls_idCirculoIt      = lipr_predioRegistro.getIdCirculo();

									if(StringUtils.isValidString(ls_idCirculoIt))
										if((lli_idMatricula == ll_idMatricula) && (ls_idCirculoIt.equals(ls_idCirculo)))
										{
											Object[] aoa_messageArgs = new String[2];
											aoa_messageArgs[0]     = ls_idCirculoIt;
											aoa_messageArgs[1]     = StringUtils.getString(ll_idMatricula);
											throw new B2BException(
											    ErrorKeys.MATRICULA_SEGREGADA_INGRESADA, aoa_messageArgs
											);
										}
								}
							}
						}
					}

					lca_matriculasSegregadas.add(la_parametros);

					setMatriculasSegregadas(lca_matriculasSegregadas);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarMatriculas", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de mostrar las alertas de intervinientes baldios.
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void alertasIntervinientesBaldios(String as_idPersona, boolean ab_accion)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_idPersona))
			{
				Map<String, Object[]> lmso_alertas;

				lmso_alertas = irr_calificacionRemote.alertasIntervinientesBaldios(
					    as_idPersona, ab_accion, TipoActoCommon.BALDIOS
					);

				if(CollectionUtils.isValidCollection(lmso_alertas))
				{
					for(Map.Entry<String, Object[]> lmso_iterador : lmso_alertas.entrySet())
					{
						if(lmso_iterador != null)
						{
							if(ab_accion)
								throw new B2BException(lmso_iterador.getKey(), lmso_iterador.getValue());
							else
								addMessage(lmso_iterador.getKey(), lmso_iterador.getValue());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);

			if(ab_accion)
				throw lb2be_e;
			else
				addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(is_mensajesCalificacionGrowl);
		}
	}

	/**
	 * Método encargado de borrar un archivo cargado para un predio de antiguo sistema.
	 *
	 * @param asc_file Objeto de tipo StreamedContent que contiene el archivo a borrar
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public void borrarArchivoCargado(StreamedContent asc_file)
	    throws IOException
	{
		try
		{
			if(asc_file != null)
			{
				InputStream lis_stream;

				lis_stream = asc_file.getStream();

				if(lis_stream != null)
				{
					Collection<StreamedContent> lcsc_content;
					Map<String, byte[]>         lmsb_archivos;
					String                      ls_nombreArchivo;

					lcsc_content         = getArchivosCargados();
					lmsb_archivos        = getBytesCargados();
					ls_nombreArchivo     = asc_file.getName();

					if(StringUtils.isValidString(ls_nombreArchivo) && CollectionUtils.isValidCollection(lmsb_archivos))
						lmsb_archivos.remove(ls_nombreArchivo);

					if(CollectionUtils.isValidCollection(lcsc_content))
						lcsc_content.remove(asc_file);

					if(isDevolucionEtapa110())
						iasr_antiguoSistemaRemote.inactivarInformeResultados(
						    getIdTurno(), getIdDatosAntSistemaActual(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

					addMessage(MessagesKeys.ARCHIVO_ELIMINADO_SATISFACTORIAMENTE);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("borrarArchivoCargado", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de borrar una direccion cargada para un predio de antiguo sistema.
	 *
	 * @param ad_direccion Objeto de tipo DirecionDelPredio que contiene la direccion a eliminar
	 */
	public void borrarDireccionAgregada(Direccion ad_direccion)
	{
		try
		{
			BeanDireccion         lbd_beanDireccion;
			Collection<Direccion> lcd_direcciones;
			DireccionPredioAcc    ldp_dp;

			lbd_beanDireccion     = getBeanDireccion();
			lcd_direcciones       = lbd_beanDireccion.getDireccionesPredio();
			ldp_dp                = ad_direccion.getDireccionPredioAcc();

			ldp_dp = iasr_antiguoSistemaRemote.findAccDireccionById(ldp_dp);

			if(ldp_dp != null)
				iasr_antiguoSistemaRemote.deleteById(ldp_dp);

			if(CollectionUtils.isValidCollection(lcd_direcciones))
				lcd_direcciones.remove(ad_direccion);

			lbd_beanDireccion.setDireccionesPredio(lcd_direcciones);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("borrarDireccionAgregada", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de validar el formulario de direccion del predio a ser agregada.
	 */
	public void botonAgregarDireccion()
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
			lbd_beanDireccion.agregarDireccion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("botonAgregarDireccion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(getFormulario() + is_mensajes);
		}
	}

	/**
	 * Método encargado de preguntarle al usuario si desea realizar una busqueda manual de antiguo sistema
	 * cuando la busqueda por documento no encuentra datos.
	 */
	public void busquedaManual()
	{
		try
		{
			String ls_tipoConsultaManual;

			ls_tipoConsultaManual = getTipoConsultaManual();

			setInformeDeBusquedaAntSistema(false);

			if(StringUtils.isValidString(ls_tipoConsultaManual))
			{
				if(ls_tipoConsultaManual.equalsIgnoreCase(EstadoCommon.A))
				{
					setActualizar(true);

					if(!isEtapa101())
					{
						boolean         lb_datosAntSistemaIguales;
						DatosAntSistema ldas_temp;

						lb_datosAntSistemaIguales     = false;
						ldas_temp                     = getConsultaDatosAntSistema();

						validarDatosAntSistema(ldas_temp);

						lb_datosAntSistemaIguales = iasr_antiguoSistemaRemote.validarDatosAntSistemaIguales(
							    ldas_temp, getIdTurnoHistoria()
							);

						setDatosAntSistemaIguales(lb_datosAntSistemaIguales);

						PrimeFaces.current().executeScript("PF('idDlgConfirmacion').show();");
						PrimeFaces.current().ajax().update("idDlgConfirmacion");
						PrimeFaces.current().ajax().update("fDlgConfirmacion");
					}
					else
					{
						PrimeFaces.current().executeScript("PF('idDlgConfirmacion101').show();");
						PrimeFaces.current().ajax().update("idDlgConfirmacion101");
						PrimeFaces.current().ajax().update("fDlgConfirmacion101");
					}
				}
				else if(ls_tipoConsultaManual.equalsIgnoreCase("R"))
				{
					String ls_tipoConsulta;

					ls_tipoConsulta = getTipoConsulta();

					if(StringUtils.isValidString(ls_tipoConsulta))
					{
						if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.A))
							validarDatosAntSistema(getConsultaDatosAntSistema());
						else if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.D))
							validarDatosDocumento(is_idFormulario, true);

						setMostrarRechazar(true);
					}
				}
				else if(ls_tipoConsultaManual.equalsIgnoreCase("I"))
				{
					String ls_tipoConsulta;

					ls_tipoConsulta = getTipoConsulta();

					if(StringUtils.isValidString(ls_tipoConsulta))
					{
						if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.A))
							validarDatosAntSistema(getConsultaDatosAntSistema());

						setInformeDeBusquedaAntSistema2(true);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_METODO_BUSQUEDA_MANUAL);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_METODO_BUSQUEDA_MANUAL);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("busquedaManual", lb2be_e);
			setActualizar(false);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de Actualizar la informacion cuando se cambia el circulo registral de los datos basicos
	 * de un predio de antiguo sistema.
	 */
	public void cambiarCirculoRegistral()
	{
		try
		{
			filtrarPorCirculo();

			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				UbicacionZonaRegistral luzr_ubicacion;

				luzr_ubicacion = ldb_datosBasicos.getUbicacion();

				if(luzr_ubicacion != null)
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = luzr_ubicacion.getCirculoRegistral();

					{
						Municipio lm_municipio;

						lm_municipio = luzr_ubicacion.getMunicipio();

						if(lm_municipio != null)
						{
							lm_municipio.setIdMunicipio(null);
							luzr_ubicacion.setMunicipio(lm_municipio);
						}
					}

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

								ldb_datosBasicos.setUbicacion(luzr_ubicacion);
								setDatosBasicos(ldb_datosBasicos);
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
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambiarCirculoRegistral", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de Actualizar la informacion cuando se cambia el circulo registral de la anotacion
	 * de un predio de antiguo sistema.
	 */
	public void cambiarCirculoRegistralAnotaciones()
	{
		filtrarPorCirculoAnotacion();

		DatosAntSistema ldas_datosAntSistema;

		ldas_datosAntSistema = getDatosAntSistemaAnotacion();

		if(ldas_datosAntSistema != null)
		{
			String ls_idCirculo;

			ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				CirculoRegistral lcr_circuloRegistral;

				lcr_circuloRegistral = new CirculoRegistral();

				lcr_circuloRegistral.setIdCirculo(ls_idCirculo);
				ldas_datosAntSistema.setIdMunicipio(null);
			}
		}
	}

	/**
	 * Método encargado de actualizar la informacion cuando se cambia el codigoTipoActo de una anotacion para un predio de antiguo sistema.
	 */
	public void cambiarCodigoTipoActo()
	{
		setCodigoNaturalezaJuridicaSeleccionada(getNaturalezaJuridicaSeleccionada());
		consultarAnotacionCancelacion();
	}

	/**
	 * Método encargado de actualizar la informacion de los municipios y veredas cuando de selecciona un departamento.
	 */
	public void cambiarDepartamento()
	{
		findMunicipio();
		findVereda();
	}

	/**
	 * Cambiar formulario.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void cambiarFormulario(boolean ab_accion)
	{
		if(ab_accion)
			setFormulario(is_idFormulario_ant_sistema);
		else
			setFormulario(is_idFormulario_calificacion);
	}

	/**
	 * Método encargado de actualizar la informacion de las veredas cuando de selecciona un municipio.
	 */
	public void cambiarMunicipio()
	{
		findVereda();
	}

	/**
	 * Método encargado de actualizar la informacion cuando se cambia el TipoActo de una anotacion para un predio de antiguo sistema.
	 */
	public void cambiarTipoActo()
	{
		setNaturalezaJuridicaSeleccionada(getCodigoNaturalezaJuridicaSeleccionada());
		consultarAnotacionCancelacion();
	}

	/**
	 * Cambiar ubicacion englobe.
	 *
	 * @param lrc_item correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void cambiarUbicacionEnglobe(RegistroCalificacion lrc_item)
	{
		if(lrc_item != null)
		{
			String  ls_matricula;
			boolean lb_accion;

			ls_matricula     = lrc_item.getIdCirculo();
			lb_accion        = lrc_item.isMatriculaSeleccionada();

			if(StringUtils.isValidString(ls_matricula) && lb_accion)
			{
				RegistroCalificacion lrc_data;

				lrc_data = getMatriculas();

				if(lrc_data != null)
				{
					Collection<RegistroCalificacion> lcrc_matriculas;

					lcrc_matriculas = lrc_data.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_matriculas))
					{
						Iterator<RegistroCalificacion> lirc_iterador;

						lirc_iterador = lcrc_matriculas.iterator();

						while(lirc_iterador.hasNext())
						{
							RegistroCalificacion lrc_dato;

							lrc_dato = lirc_iterador.next();

							if(lrc_dato != null)
							{
								String ls_matriculaIterador;

								ls_matriculaIterador = lrc_dato.getIdCirculo();

								if(StringUtils.isValidString(ls_matriculaIterador))
								{
									if(!ls_matriculaIterador.equalsIgnoreCase(ls_matricula))
										lrc_dato.setMatriculaSeleccionada(false);
								}
								else
									lrc_dato.setMatriculaSeleccionada(false);
							}
						}

						lrc_data.setZonaRegistral(lrc_item.getZonaRegistral());
					}
				}
			}
		}
	}

	/**
	 * Método encargado de actualizar la informacion del tab de busqueda cuando se selecciona el tipo de busqueda de antiguo sistema.
	 */
	public void cambioBusqueda()
	{
		try
		{
			setAntSistemaConsulta(false);
			setAntSistemaConsultaNo(false);
			setAntSistemaConsultaExitosa(false);
			setDatosAntSistemaIguales(false);
			setPdfGenerado(false);
			setCrearMatriculasExistentes(false);

			setInformeDeBusquedaAntSistema(false);
			setInformeDeBusquedaAntSistema2(false);

			setDocumentoConsulta(false);
			setDocumentoConsultaExitosa(false);

			setActualizarDatosAntSistema(false);

			setTipoConsultaManual(null);
			setSeccionTipoMatricula(null);

			setDataConsultaPorCriterioAntSistema(null);
			setDataConsultaPorCriterioDocumento(null);

			generarDatosDocumento();
			generarDatosAntSistema();

			cargarTiposOficinaAnotacionesPorTipoDoc(EstadoCommon.DE);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambioBusqueda", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de inicializar variables por cambio manual.
	 */
	public void cambioManual()
	{
		setMostrarRechazar(false);
		setActualizar(false);
	}

	/**
	 * Método encargado de cargar un archivo a un predio de antiguo sistema en informe de resultado.
	 *
	 * @param afue_event Objeto de tipo  FileUploadEvent que contiene el archivo a ser cargado
	 * @return devuelve el valor de String
	 */
	public String cargarArchivoPdf(FileUploadEvent afue_event)
	{
		try
		{
			UploadedFile luf_file;
			String       ls_mensaje;

			luf_file       = afue_event.getFile();
			ls_mensaje     = null;

			if(luf_file != null)
			{
				if(luf_file.getSize() <= 500000)
				{
					byte[] lba_pdfFile;
					String ls_nombreArchivo;

					lba_pdfFile          = luf_file.getContents();
					ls_nombreArchivo     = luf_file.getFileName();

					if(
					    (lba_pdfFile != null) && StringUtils.isValidString(ls_nombreArchivo)
						    && ls_nombreArchivo.toUpperCase().endsWith(ExtensionCommon.PDF_MAYUS_PUNTO)
					)
					{
						String              ls_pdfContent;
						boolean             lb_isEncrypted;
						int                 li_lastTrailerIndex;
						Map<String, byte[]> lmsb_archivos;

						lb_isEncrypted     = false;
						lmsb_archivos      = getBytesCargados();

						if(lba_pdfFile.length < 1)
							throw new B2BException(ErrorKeys.ARCHIVO_DANADO);

						ls_pdfContent           = new String(lba_pdfFile);
						li_lastTrailerIndex     = ls_pdfContent.lastIndexOf("trailer");

						if((li_lastTrailerIndex >= 0) && (li_lastTrailerIndex < ls_pdfContent.length()))
						{
							String ls_partEncrypted;
							String ls_trailer;
							int    ls_firstEOFIndex;

							ls_partEncrypted     = ls_pdfContent.substring(li_lastTrailerIndex, ls_pdfContent.length());
							ls_firstEOFIndex     = ls_partEncrypted.indexOf("%%EOF");
							ls_trailer           = ls_partEncrypted.substring(0, ls_firstEOFIndex);

							if(ls_trailer.contains("/Encrypt"))
								lb_isEncrypted = true;
						}

						if(lb_isEncrypted)
						{
							isc_file = null;
							throw new B2BException(ErrorKeys.ARCHIVO_PROTEGIDO);
						}

						{
							InputStream lis_stream = new ByteArrayInputStream(lba_pdfFile);

							isc_file = new DefaultStreamedContent(
								    lis_stream, luf_file.getContentType(), ls_nombreArchivo
								);

							{
								Collection<StreamedContent> lcsc_archivosCargados;

								lcsc_archivosCargados = getArchivosCargados();

								if(!CollectionUtils.isValidCollection(lcsc_archivosCargados))
									lcsc_archivosCargados = new LinkedList<StreamedContent>();
								else if(lcsc_archivosCargados.size() >= 1)
									throw new B2BException(ErrorKeys.ERROR_LIMITE_DOCUMENTOS_INFORME_ASOCIAR);

								lcsc_archivosCargados.add(isc_file);

								setArchivosCargados(lcsc_archivosCargados);
							}
						}

						lmsb_archivos = new HashMap<String, byte[]>();

						lmsb_archivos.put(ls_nombreArchivo, lba_pdfFile);

						setBytesCargados(lmsb_archivos);
					}
					else
						isc_file = null;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TAMANIO_ARCHIVO_PDF);
			}
			else
				ls_mensaje = MessagesKeys.NO_ENCONTRAR_ARCHIVO_CARGUE;

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = MessagesKeys.PROCESAMINETO_DE_CARGUE_TERMINADO;

			addMessage(ls_mensaje);

			PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema::idDtArchivosCargadosReporte");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarArchivoPdf", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("cargarArchivoPdf", le_e);
			addMessage("E", le_e.getMessage());
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}

		return null;
	}

	/**
	 * Método encargado de cargar el area para un predio especifico de antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarArea()
	    throws B2BException
	{
		AccPredioRegistro lapr_predioRegistro;

		lapr_predioRegistro = getAccPredioRegistro();

		if(lapr_predioRegistro != null)
		{
			Long   ll_idMatricula;
			String ls_idCirculo;

			ll_idMatricula     = lapr_predioRegistro.getIdMatricula();
			ls_idCirculo       = lapr_predioRegistro.getIdCirculo();

			if(NumericUtils.isValidLong(ll_idMatricula) && StringUtils.isValidString(ls_idCirculo))
			{
				AccAreaPredio laap_areaPredioArg;
				AccAreaUI     laaui_data;

				laap_areaPredioArg = new AccAreaPredio();

				laap_areaPredioArg.setIdCirculo(ls_idCirculo);
				laap_areaPredioArg.setIdMatricula(ll_idMatricula);

				laaui_data = irr_registroRemote.consultaAreaPredio(laap_areaPredioArg, getUserId(), false);

				if(laaui_data == null)
				{
					AccAreaPredio laap_areaPredio;

					laap_areaPredio     = new AccAreaPredio();
					laaui_data          = new AccAreaUI();

					laap_areaPredio.setIdCirculo(ls_idCirculo);
					laap_areaPredio.setIdMatricula(ll_idMatricula);

					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = getDatosBasicos();

						if(ldb_datosBasicos != null)
						{
							PredioRegistro lpr_predioRegistro;

							lpr_predioRegistro = ldb_datosBasicos.getPredioRegistro();

							if(lpr_predioRegistro != null)
								laap_areaPredio.setTipoSuelo(lpr_predioRegistro.getIdTipoUsoSuelo());
						}
					}

					laaui_data.setIdCirculo(ls_idCirculo);
					laaui_data.setIdMatricula(ll_idMatricula);

					laaui_data.setAreaPredio(laap_areaPredio);
				}

				setAreaUI(laaui_data);
				setBloquearAgregarAreaTerreno(false);
			}
		}
	}

	/**
	 * Métitodo encargado de cargar la complementacion para un predio especifico de antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarComplementacion()
	    throws B2BException
	{
		try
		{
			String ls_turno;
			ls_turno = getIdTurno();

			Long ll_th;
			ll_th = NumericUtils.getLongWrapper(NumericUtils.getLong(getIdTurnoHistoria()));

			if(!StringUtils.isValidString(ls_turno) || !NumericUtils.isValidLong(ll_th))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			AccComplementacionPredio lacp_complementacion;

			lacp_complementacion = iasr_antiguoSistemaRemote.cargarComplementacionPredio(ls_turno, ll_th);

			if(lacp_complementacion != null)
			{
				setComplementacionPredio(lacp_complementacion);
				setComplementacion(lacp_complementacion.getComplementacion());
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_COMPLEMENTACION);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarComplementacion", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de cargar o crear los datos basicos de un predio de antiguo sistema.
	 *
	 * @param ab_accion Objeto de tipo boolean que indica la accion a realizar
	 */
	public void cargarCrearMatricula(boolean ab_accion)
	{
		cargarCrearMatricula(ab_accion, null);
	}

	/**
	 * Método encargado de cargar o crear los datos basicos de un predio de antiguo sistema.
	 *
	 * @param ab_accion Objeto de tipo boolean que indica la accion a realizar
	 * @param as_opcion Objeto de tipo String que indica si se va a crear, asociar o agregar un informe
	 */
	public void cargarCrearMatricula(boolean ab_accion, String as_opcion)
	{
		try
		{
			boolean lb_accion;

			lb_accion = false;

			if(isAsociarMatriculaAntSistema())
			{
				TurnoHistoria lth_th;

				lth_th = new TurnoHistoria();

				lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

				if(lth_th != null)
					setIdCirculo(lth_th.getIdCirculo());

				setMostrarBandeja(true);
			}
			else if(isCrearMatriculaAntSistema())
			{
				lb_accion = iasr_antiguoSistemaRemote.cargarCrearMatricula(
					    getIdTurnoHistoria(), getIdDatosAntSistemaActual()
					);
				setMostrarBandeja(false);

				resetWizard();
			}

			if(lb_accion && ab_accion)
			{
				setTipoConsulta(EstadoCommon.A);
				setTipoConsultaManual(EstadoCommon.A);
				setAntSistemaConsultaExitosa(false);
				setAntSistemaConsultaNo(false);
				setAntSistemaConsulta(true);
				setActualizar(true);
				setCrearMatriculaAntSistema(true);
				setDatosAntSistemaIguales(true);
				setActualizarDatosAntSistema(true);
				accionNuevo(false);
				setMostrarBandeja(true);
				setMostrarAtrasCrearGrabar(false);
				setOcultarSiguienteCrearGrabar(false);
				setMostrar(false);

				PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema");
			}
			else
				accionNuevo(false);

			setCrearMatriculasExistentes(lb_accion);

			cambiarTabProceso(as_opcion);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarCrearMatricula", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de cargar datos anotaciones A cancelar.
	 */
	public void cargarDatosAnotacionesACancelar()
	{
		try
		{
			AnotacionCancelacion lac_anotacionCancelacion;

			lac_anotacionCancelacion = new AnotacionCancelacion();

			lac_anotacionCancelacion.setIdCirculo(getIdCirculo());
			lac_anotacionCancelacion.setIdMatricula(NumericUtils.getLong(getIdMatricula()));
			lac_anotacionCancelacion.setIdSolicitud(getIdSolicitud());
			lac_anotacionCancelacion.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

			{
				Anotacion la_antotacion;

				la_antotacion = getAnotacion();

				if(la_antotacion != null)
					lac_anotacionCancelacion.setIdAnotacion(
					    NumericUtils.getLongWrapper(la_antotacion.getIdAnotacion())
					);
			}

			setAnotacionACancelar(
			    irr_calificacionRemote.cargarDatosAnotacionesACancelar(
			        lac_anotacionCancelacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosAnotacionesACancelar", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(is_mensajesIdGrowl);
		}
	}

	/**
	 * Metodo encargado de cargar los datos personales de un interviniente
	 * consultado en la pestaña de anotaciones.
	 *
	 * @param ap_persona Objeto de tipo Persona que contiene los datos de la persona seleccionada
	 */
	public void cargarDatosPersonales(Persona ap_persona)
	{
		if(ap_persona != null)
		{
			Collection<Persona> lcp_personas;
			String              ls_idPersonaSeleccion;

			lcp_personas              = getListadoIntervinientes();
			ls_idPersonaSeleccion     = ap_persona.getIdPersona();

			if(CollectionUtils.isValidCollection(lcp_personas))
			{
				for(Persona lp_iterador : lcp_personas)
				{
					if(lp_iterador != null)
					{
						if(StringUtils.isValidString(ls_idPersonaSeleccion))
						{
							String ls_idPersona;
							ls_idPersona = lp_iterador.getIdPersona();

							if(
							    StringUtils.isValidString(ls_idPersona)
								    && ls_idPersonaSeleccion.equalsIgnoreCase(ls_idPersona)
							)
							{
								setPersona(ap_persona);
								setDeshabilitarDatosInterviniente(true);
							}
							else
								lp_iterador.setSeleccionado(false);
						}
						else
							lp_iterador.setSeleccionado(false);
					}
				}

				if(!StringUtils.isValidString(ls_idPersonaSeleccion))
				{
					setPersona(null);

					setDeshabilitarDatosInterviniente(false);
				}
			}
		}
	}

	/**
	 * Método encargado de cargar el departamento del circulo seelccionado para la busqueda manual de antiguo sistema.
	 */
	public void cargarDepartamentoAntSistema()
	{
		try
		{
			DatosAntSistema ldas_datosAntSistema;

			ldas_datosAntSistema = getConsultaDatosAntSistema();

			if(ldas_datosAntSistema != null)
			{
				String ls_idCirculo;

				ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ZonaRegistral lzr_zonaRegistral;

					lzr_zonaRegistral = new ZonaRegistral();

					lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

					lzr_zonaRegistral = iasr_antiguoSistemaRemote.findZonaRegistralCirculo(lzr_zonaRegistral);

					if(lzr_zonaRegistral != null)
					{
						String ls_idDepartamento;
						String ls_idPais;

						ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
						ls_idPais             = lzr_zonaRegistral.getIdPais();

						if(StringUtils.isValidString(ls_idDepartamento))
							ldas_datosAntSistema.setIdDepartamento(ls_idDepartamento);

						if(StringUtils.isValidString(ls_idPais))
							ldas_datosAntSistema.setIdPais(ls_idPais);

						setConsultaDatosAntSistema(ldas_datosAntSistema);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDepartamentoAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cargar el departamento para la busqueda manual de documento de antiguo sistema.
	 */
	public void cargarDepartamentoDocumento()
	{
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Carga en pantalla la información del detalle de un predio.
	 *
	 * @param adas_dato objeto contenedor de la información a utilizar para buscar los datos del predio
	 */
	public void cargarDetallePredio(DatosAntSistema adas_dato)
	{
		try
		{
			cargarDetallePredio(adas_dato, true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDetallePredio", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cargar los datos de la dirección.
	 *
	 * @param as_form Argumento de tipo <code>String</code> que contiene el id del formulario desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDireccionFlowCrearGrabar(String as_form)
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		lbd_beanDireccion.limpiarBeanDireccion();
		lbd_beanDireccion.setAgregarDireccionPredio(true);
		lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
		lbd_beanDireccion.setForm(as_form);

		validarDirecciones(false);

		{
			DireccionDelPredio ldp_d;

			ldp_d = getDireccionPredio();

			if(ldp_d != null)
			{
				DireccionPredioAcc ldp_direccion;
				Direccion          ld_direccion;

				ldp_direccion     = ldp_d.getDireccionPredio();
				ld_direccion      = null;

				if(ldp_direccion != null)
					ld_direccion = new Direccion(ldp_direccion);

				if(ld_direccion == null)
					ld_direccion = new Direccion();

				lbd_beanDireccion.setDireccionPredio(ld_direccion);
				lbd_beanDireccion.cargarDatosDireccionPredio(getDatosBasicos());
			}
		}
	}

	/**
	 * Método encargado de cargar la direccion de una matricula a ser ingresada en pantalla.
	 *
	 * @param apaiu_predioActo correspondiente al valor del tipo de objeto PredioActoIU
	 */
	public void cargarDireccionPredio(PredioActoIU apaiu_predioActo)
	{
		try
		{
			String ls_matriculaInmobiliaria;
			String ls_errors;
			String ls_idCirculo;

			if(apaiu_predioActo == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ls_matriculaInmobiliaria     = apaiu_predioActo.getMatricula();
			ls_idCirculo                 = getIdCirculo();

			if(StringUtils.isValidString(ls_matriculaInmobiliaria) && StringUtils.isValidString(ls_idCirculo))
				ls_errors = cargarDireccionPredio(apaiu_predioActo, ls_matriculaInmobiliaria, ls_idCirculo);
			else
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = ls_matriculaInmobiliaria;
				throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
			}

			if(StringUtils.isValidString(ls_errors))
			{
				if(
				    ls_errors.contains(EstadoCommon.ESTADO_CERRADO)
					    || ls_errors.contains(
					        getMessages().getString(MessagesKeys.SE_ENCUENTRA_BLOQUEADO_CON_EL_TUNRO)
					    ) || ls_errors.contains(getMessages().getString(MessagesKeys.PREDIO_INCONSISTENTE_COMPARAR))
				)
				{
					addMessage("I", ls_errors);
					PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
				}
				else
					throw new B2BException(ls_errors);
			}

			validarTramiteSuspension(ls_idCirculo, StringUtils.getString(ls_matriculaInmobiliaria));
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDireccionPredio", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Metodo encargado de validar la matricula ingresada y traer la direccion de la
	 * misma.
	 *
	 * @param apaiu_predioActo Objeto de tipo PredioActoUI que contiene la informacion de la matricula cargada
	 * @param as_matricula Objeto de tipo String que contiene el id_matricula a ser consultado
	 * @param as_idCirculo Objeto de tipo String que contiene el id_circulo a ser consultado
	 * @return String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String cargarDireccionPredio(PredioActoIU apaiu_predioActo, String as_matricula, String as_idCirculo)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			String ls_matriculaInmobiliaria;

			ls_matriculaInmobiliaria = as_matricula;

			if(StringUtils.isValidString(ls_matriculaInmobiliaria))
			{
				List<Map<String, Object>> llllhm_matriculas;

				llllhm_matriculas = getMatriculasData();

				if(llllhm_matriculas != null)
				{
					for(Map<String, Object> llhm_data : llllhm_matriculas)
					{
						String   ls_circuloMatricula;
						String   ls_matricula;
						String[] las_cirMat;

						ls_circuloMatricula     = llhm_data.get(IdentificadoresCommon.MATRICULA).toString();
						las_cirMat              = ls_circuloMatricula.split("-");
						ls_matricula            = las_cirMat[1];

						if(ls_matriculaInmobiliaria.equals(ls_matricula))
						{
							getActosAsociadosGeneral().remove(apaiu_predioActo);
							throw new B2BException(ErrorKeys.ERROR_MATRICULA_REPETIDA);
						}
					}
				}

				if(StringUtils.isValidString(ls_matriculaInmobiliaria))
				{
					int                      li_contarMatriculasIguales;
					Collection<PredioActoIU> lcpaui_tmp;

					lcpaui_tmp                     = getActosAsociadosGeneral();
					li_contarMatriculasIguales     = 0;

					if(CollectionUtils.isValidCollection(lcpaui_tmp))
					{
						for(PredioActoIU lpaui_actual : lcpaui_tmp)
						{
							if(lpaui_actual != null)
							{
								String ls_matriculaCargada;

								ls_matriculaCargada = lpaui_actual.getMatricula();

								if(ls_matriculaInmobiliaria.equalsIgnoreCase(ls_matriculaCargada))
									li_contarMatriculasIguales++;
							}
						}
					}

					if(StringUtils.isValidString(as_idCirculo))
						ls_matriculaInmobiliaria = as_idCirculo + "-" + ls_matriculaInmobiliaria;

					if(li_contarMatriculasIguales <= 1)
					{
						int    li_inicio;
						String ls_circuloRegistral;
						String ls_matricula;

						ls_matriculaInmobiliaria     = ls_matriculaInmobiliaria.toUpperCase();

						li_inicio               = ls_matriculaInmobiliaria.lastIndexOf("-");
						ls_circuloRegistral     = ls_matriculaInmobiliaria.substring(0, li_inicio);
						ls_matricula            = ls_matriculaInmobiliaria.substring(
							    li_inicio + 1, ls_matriculaInmobiliaria.length()
							);

						if(StringUtils.isValidString(ls_matricula) && StringUtils.isValidString(ls_circuloRegistral))
						{
							long             ll_matricula;
							CirculoRegistral lcr_circulo;

							lcr_circulo = new CirculoRegistral();
							lcr_circulo.setIdCirculo(ls_circuloRegistral);

							lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

							if(lcr_circulo != null)
							{
								ll_matricula = NumericUtils.getLong(ls_matricula);

								if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_matricula), 1))
								{
									PredioRegistro lpr_predio;

									lpr_predio = new PredioRegistro();

									lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
									lpr_predio.setIdMatricula(ll_matricula);

									lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

									if(lpr_predio != null)
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
											String ls_address;

											ls_address = ldp_direccionPredio.getDireccion();

											if(StringUtils.isValidString(ls_address))
											{
												Collection<PredioActoIU> lcpaui_cargado;

												lcpaui_cargado = getActosAsociadosGeneral();

												if(CollectionUtils.isValidCollection(lcpaui_cargado))
												{
													for(PredioActoIU lpaui_actual : lcpaui_cargado)
													{
														if(lpaui_actual != null)
														{
															String ls_matriculaCargada;

															ls_matriculaCargada = lpaui_actual.getMatricula();

															if(ls_matricula.equalsIgnoreCase(ls_matriculaCargada))
																lpaui_actual.setDireccion(ls_address);
														}
													}
												}

												setActosAsociadosGeneral(lcpaui_cargado);
											}
										}

										{
											String ls_estadoPredio;

											ls_estadoPredio = lpr_predio.getIdEstadoPredio();

											if(StringUtils.isValidString(ls_estadoPredio))
											{
												EstadoPredio lep_estado;

												lep_estado = new EstadoPredio();

												lep_estado.setIdEstadoPredio(ls_estadoPredio);

												lep_estado = irr_registroRemote.findEstadoPredioById(
													    lep_estado, getUserId()
													);

												if(lep_estado != null)
												{
													String ls_estado;

													ls_estado = lep_estado.getNombre();

													if(
													    StringUtils.isValidString(ls_estado)
														    && ls_estado.toUpperCase()
														                    .equalsIgnoreCase(
														        EstadoCommon.ESTADO_CERRADO
														    )
													)
														ls_return = (getMessages()
																             .getString(
																    MessagesKeys.LA_MATRICULA_INMOBILIARIA
																) + ls_matriculaInmobiliaria
															+ getMessages()
																      .getString(
																    MessagesKeys.A_INCLUIR_ESTA_ESTADO_CERRADO
																));
												}
											}
										}

										{
											String ls_turnoBloqueo;

											ls_turnoBloqueo = lpr_predio.getTurnoBloqueo();

											if(StringUtils.isValidString(ls_turnoBloqueo))
											{
												Collection<String> lcs_turnosBloqueo;

												lcs_turnosBloqueo = irr_registroRemote.findTurnosBloqueoPredio(
													    lpr_predio
													);

												if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
												{
													Iterator<String> lis_turnosBloqueo;
													String           ls_turnosBloqueo;
													String           ls_error;

													lis_turnosBloqueo     = lcs_turnosBloqueo.iterator();
													ls_turnosBloqueo      = "";
													ls_error              = null;

													while(lis_turnosBloqueo.hasNext())
													{
														String ls_turnoTemp;
														String ls_espacio;

														ls_turnoTemp     = lis_turnosBloqueo.next();
														ls_espacio       = lis_turnosBloqueo.hasNext() ? ",\n" : "\n";

														if(StringUtils.isValidString(ls_turnoTemp))
															ls_turnosBloqueo = ls_turnosBloqueo + ls_espacio
																+ ls_turnoTemp;
													}

													ls_error = ("El folio de matrícula " + ls_matriculaInmobiliaria
														+ " se encuentra bloqueado con los turnos: " + ls_turnosBloqueo);

													if(StringUtils.isValidString(ls_error))
														ls_return = ls_error;
												}
											}
										}

										if(
										    StringUtils.getStringNotNull(lpr_predio.getPredioInconsistente())
											               .equalsIgnoreCase(EstadoCommon.S)
										)
											ls_return = ("El predio con matrícula inmobiliaria "
												+ ls_matriculaInmobiliaria + " es un predio inconsistente.");
									}
									else
									{
										Collection<PredioActoIU> lcpaui_cargado;

										lcpaui_cargado = getActosAsociadosGeneral();

										if(CollectionUtils.isValidCollection(lcpaui_cargado))
										{
											for(PredioActoIU lpaui_actual : lcpaui_cargado)
											{
												if(lpaui_actual != null)
												{
													String ls_matriculaCargada;

													ls_matriculaCargada = lpaui_actual.getMatricula();

													if(ls_matriculaInmobiliaria.equalsIgnoreCase(ls_matriculaCargada))
													{
														lpaui_actual.setDireccion(null);
														lpaui_actual.setCantidad(NumericUtils.getLongWrapper(0L));
														lpaui_actual.setCertificadoAsociado(false);

														{
															List<ColumnModel> lcm_lcm;

															lcm_lcm = getColumns();

															if(CollectionUtils.isValidCollection(lcm_lcm))
															{
																Map<String, Boolean> lmsb_actos;

																lmsb_actos = new HashMap<String, Boolean>(
																	    lpaui_actual.getActos()
																	);

																for(ColumnModel lcm_tmp : lcm_lcm)
																{
																	if(lcm_tmp != null)
																	{
																		boolean lb_actoAsociado;
																		String  ls_idActo;

																		lb_actoAsociado     = false;
																		ls_idActo           = lcm_tmp.getIdActo();

																		if(StringUtils.isValidString(ls_idActo))
																			lb_actoAsociado = BooleanUtils
																					.getBooleanValue(
																					    lmsb_actos.get(ls_idActo)
																					);

																		if(lb_actoAsociado)
																			lmsb_actos.replace(
																			    ls_idActo, Boolean.FALSE
																			);
																	}
																}

																lpaui_actual.setActos(lmsb_actos);
															}
														}
													}
												}
											}
										}

										setActosAsociadosGeneral(lcpaui_cargado);

										Object[] aoa_messageArgs = new String[1];

										aoa_messageArgs[0] = ls_circuloRegistral + "-" + ls_matricula;

										throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PREDIO, aoa_messageArgs);
									}
								}
								else
									throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA_FORMATO);
							}
							else
								throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);
						}
						else
							throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA);
					}
					else
						throw new B2BException(ErrorKeys.MATRICULA_INGRESADA);
				}
				else
					throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDireccionPredio", lb2be_e);
			ls_return = lb2be_e.getMessage();
		}

		return ls_return;
	}

	/**
	 * Método encargado de de realizar al precarga de las listas deplegables del docuemtno de antiguo sistema.
	 */
	public void cargarListasDesplegablesDocumento()
	{
		findTipoEntidadDocumento();
		findPaises();
		findDepartamentosDocumento();
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Método encargado de cargar matricula.
	 *
	 * @param aapr_apr correspondiente al valor del tipo de objeto AccPredioRegistro
	 */
	public void cargarMatricula(AccPredioRegistro aapr_apr)
	{
		cargarMatricula(aapr_apr, true);
	}

	/**
	 * Método encargado de cargar la lista desplegable de oficina origen.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarOficinaOrigen()
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
			clh_LOGGER.error("cargarOficinaOrigen", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de precargar la informacion de las listas deplegable de departamento, municipio y oficinaOrigen
	 * cuando se selecciona un pais.
	 */
	public void cargarPaisDocumento()
	{
		findDepartamentosDocumento();
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Método encargado de cargar las matrículas para englobe en correcciones.
	 *
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarPrediosEnglobeCorreccion(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		try
		{
			AnotacionPredio lap_anotacion;
			String          ls_idTurno;

			lap_anotacion     = new AnotacionPredio();
			ls_idTurno        = getIdTurno();

			if(!StringUtils.isValidString(ls_idTurno))
			{
				AnotacionPredio lap_anotacionPredio;

				lap_anotacionPredio = getAnotacionPredio();

				if(lap_anotacionPredio != null)
				{
					ls_idTurno = lap_anotacionPredio.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
						setIdTurno(ls_idTurno);
				}
			}

			lap_anotacion.setIdNaturalezaJuridica(getCodigoNaturalezaJuridicaSeleccionada());
			lap_anotacion.setIdCirculo(as_idCirculo);
			lap_anotacion.setIdMatricula(al_idMatricula);
			lap_anotacion.setIdTurno(ls_idTurno);

			setMatriculasCorreccion(
			    irr_calificacionRemote.cargarPrediosEnglobeCorreccion(
			        lap_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarPrediosEnglobeCorreccion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de consultar las entidades correspondientes al tipo de documento.
	 *
	 * @return Listado de tipo entidad.
	 */
	public Collection<TipoEntidad> cargarTipoEntidadDocumentoAntSistema()
	{
		return cargarTipoEntidadDocumentoAntSistema(false);
	}

	/**
	 * Método encargado de consultar las entidades correspondientes al tipo de documento.
	 *
	 * @param ab_documento Argumento de tipo <code>boolean</code> que determina si se deben usar los datos precargados de documento.
	 *
	 * @return una collection con el tipo de entidad
	 */
	public Collection<TipoEntidad> cargarTipoEntidadDocumentoAntSistema(boolean ab_documento)
	{
		Collection<TipoEntidad> lcto_return;
		lcto_return = new ArrayList<TipoEntidad>();

		try
		{
			DetalleAntSistemaUI ldas_das;
			ldas_das = getDetalleAntSistemaConsulta();

			Documento ld_documento;
			ld_documento = ab_documento ? getDocumentoDetalleCargado()
				                        : ((ldas_das != null) ? ldas_das.getDocumentoConsulta() : null);

			if(ld_documento != null)
			{
				TipoEntidad la_entidad;

				la_entidad = new TipoEntidad();

				if(StringUtils.isValidString(ld_documento.getTipoEntidad()))
				{
					la_entidad.setIdTipoEntidad(ld_documento.getTipoEntidad());

					if(la_entidad != null)
					{
						la_entidad = irr_referenceRemote.findTipoEntidadById(la_entidad);

						lcto_return.add(la_entidad);
					}
				}
				else
				{
					la_entidad.setNombre(IdentificadoresCommon.DATO_INVALIDO);
					lcto_return.add(la_entidad);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("cargarTipoEntidadDocumento", lb2be_e);
		}

		return lcto_return;
	}

	/**
	 * Método encargado de cargar los tipos de oficina que se encuentren relacionadas a un tipo de documento específico.
	 *
	 * @param as_pantalla correspondiente al valor del tipo de objeto String
	 */
	public void cargarTiposOficinaAnotaciones(String as_pantalla)
	{
		try
		{
			cargarTiposOficinaAnotacionesPorTipoDoc(as_pantalla);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarTiposOficinaAnotaciones", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Métoto encargado de limpiar la complementacion de un predio.
	 */
	public void cleanComplementacion()
	{
		setComplementacion("");
	}

	/**
	 * Método encargado de eliminar los datos que puedan estar contenidos en las variables de la clase.
	 *
	 */
	public void clear()
	{
		setDataAntiguoSistema(null);

		setConsultaDatosAntSistema(null);
		setConsultaDatosDocumento(null);
		setInfoAntiguoSistema(null);
		setIdCirculo(null);
		setIdCirculoProceso(null);
		setPdfGenerado(false);
		setBloquearModificarIntervenientes(false);
		setBloquearModificarAnotaciones(false);
		setEtapa101(false);
		setEtapa105(false);
		setDocumentosEnviados(false);
		setActualizar(false);
		setInformeDeBusquedaAntSistema(false);
		setInformeDeBusquedaAntSistema2(false);
		setNoInformeBusqueda(false);
		setDatosAntSistema(null);
		setDetallesAntSistema(null);
		setDatoAntSistemaCargado(null);
		setDetalleAntSistemaCargado(null);
		setDocumentoDetalleCargado(null);
		setOficinasDocumentoDetalleAntSistema(null);
		setPredioSeleccionado(false);
		setTiposOficinaDoc(null);
		setFormulario(null);
		setMatriculaSeleccionadaParaEliminar(null);
		setMostrarPanelMotivos(false);
		setMotivoTramite(null);
		setGrabacionDeMatriculas(false);
		setProcesoCorrecciones(false);
		setProcesoCertificados(false);
		setTiposOficinaDocDetalleAnt(null);
		setDataModel(null);
		setDeshabilitarCalidadInterviniente(false);

		setDetalleAntSistemaAnotacion(null);
		setDetalleAntSistemaConsulta(null);
		idasu_detalleAntSistemaConsulta = null;

		setTiposActualizarDatosAntSistema(null);
		setTiposActualizarDatosAntSistema2(null);
		setTiposActualizarDatosAntSistema101(null);
		setProcesos(null);

		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		setObservaciones(null);
		setObservacionesRechazo(null);
		setObservacionesCalificador(null);

		cambioBusqueda();

		setDatosDelTurno(null);
		setIdTurno(null);
		setTipoConsulta(null);

		setDeshabilitarDatosDoc(true);
		setDeshabilitarDatosAntiguoSistema(true);

		setMatriculas(new ArrayList<Map<String, Object>>());
		setAnotacion(null);
		setDatosBasicos(null);
		setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		setIdTurnoHistoria(null);
		setNumeroAnotacion(null);
		setNumeroOrden(null);
		setListadoIntervinientes(null);
		setMostrarAtrasCrearGrabar(false);
		setOcultarSiguienteCrearGrabar(false);
		setOcultarBotonSiguiente(false);
		setIdSolicitud(null);
		setMostrarListadoPersonas(false);
		setDeshabilitarDatosInterviniente(false);
		setListadoIntervinientes(null);
		setActosAsociadosGeneral(null);
		setPredioDocumento(null);
		setCabidaLinderos(null);
		setComplementacionCalificacion(null);
		setAreaPredio(null);
		setDireccionesPredio(null);
		setDireccionPredio(null);
		setAccPredioRegistro(null);
		setMostrarBandeja(true);
		setMostrar(false);

		setArchivosCargados(new ArrayList<StreamedContent>());
		setHabilitarCargueResultados(null);
		setHabilitarCargues(false);
		setHabilitarCompletitud(false);
		setModificarComplementacion(false);
		setComplementacion(null);
		setBytesCargados(new HashMap<String, byte[]>());
		setComplementacionPredio(new AccComplementacionPredio());
		setJustificacionFirma(null);

		findTiposCausales();
	}

	/**
	 * Método encargado de limpiar la consulta antiguo sistema y documento.
	 */
	public void clearConsulta()
	{
		clearConsultaAntSistema();
		clearConsultaDocumento();
	}

	/**
	 * Método encargado de limpiar la consulta antiguo sistema.
	 */
	public void clearConsultaAntSistema()
	{
		setDataConsultaPorCriterioAntSistema(null);
		setConsultaDatosAntSistema(null);
	}

	/**
	 * Método encargado de limpiar la consulta de documento.
	 */
	public void clearConsultaDocumento()
	{
		setDataConsultaPorCriterioDocumento(null);
		setConsultaDatosDocumento(null);
		setTiposOficinaDoc(null);

		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;

			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;

				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
					loo_oficinaOrigen.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			}
		}
	}

	/**
	 * Método encargado de construir los datos de complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void construirComplementacion()
	    throws B2BException
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> lc_anotaciones;

		lc_anotaciones = generarAnotacionesPredio();

		if(CollectionUtils.isValidCollection(lc_anotaciones))
		{
			Collection<ComplementacionAnotacion> lc_dataComplementacionAnotacion;

			lc_dataComplementacionAnotacion = irr_calificacionRemote.construirComplementacionAnotaciones(
				    lc_anotaciones
				);

			List ll_list;
			ll_list = Collections.synchronizedList(new java.util.ArrayList(lc_dataComplementacionAnotacion));

			Collections.sort(
			    ll_list,
			    new Comparator<ComplementacionAnotacion>()
				{
					@Override
					public int compare(
					    ComplementacionAnotacion lca_complementacion1, ComplementacionAnotacion lca_complementacion2
					)
					{
						Date ld_fecha1;
						Date ld_fecha2;

						ld_fecha1     = (lca_complementacion1.getAnotacionPredio() != null)
							? lca_complementacion1.getAnotacionPredio().getFechaRegistro() : null;
						ld_fecha2 = (lca_complementacion1.getAnotacionPredio() != null)
							? lca_complementacion2.getAnotacionPredio().getFechaRegistro() : null;

						if((ld_fecha1 == null) || (ld_fecha2 == null))
							return 0;

						return ld_fecha1.compareTo(ld_fecha2);
					}
				}
			);

			setComplementacionAnotacion(ll_list);
		}
	}

	/**
	 * Método encargado de consultar los datos de antiguo sistema.
	 */
	public void consultaAntiguoSistema()
	{
		String ls_idPantalla;

		ls_idPantalla = ":idFormAntSistema:";

		try
		{
			DatosAntSistema ldas_temp;

			ldas_temp = getConsultaDatosAntSistema();

			validarDatosAntSistema(ldas_temp);

			Collection<DataConsultaAntSistema> lc_dataAntSistema;
			Collection<DataConsultaAntSistema> lc_dataAntSistemaReturn;

			lc_dataAntSistema           = iasr_antiguoSistemaRemote.findByDatosAntSistema(ldas_temp);
			lc_dataAntSistemaReturn     = new ArrayList<DataConsultaAntSistema>();

			if(CollectionUtils.isValidCollection(lc_dataAntSistema))
			{
				boolean lb_hayData;

				lb_hayData = false;

				for(DataConsultaAntSistema iterator : lc_dataAntSistema)
				{
					Collection<DataConsultaPorCriterio> lc_data;
					Collection<DataConsultaPorCriterio> ll_returnData;

					lc_data           = iterator.getDataConsultaPorCriterio();
					ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

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
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " "
						);

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
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " "
						);
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

					if(CollectionUtils.isValidCollection(ll_returnData))
					{
						setDataConsultaPorCriterioAntSistema(ll_returnData);
						lb_hayData = true;
						lc_dataAntSistemaReturn.add(iterator);
					}
				}

				if(lb_hayData)
				{
					setAntSistemaConsultaExitosa(true);
					setAntSistemaConsultaNo(false);
					setAntSistemaConsulta(false);

					addMessage(MessagesKeys.BUSQUEDA_EXITOSA_ANT_SISTEMA_100);
					PrimeFaces.current().ajax().update(ls_idPantalla + "idGrowl");
				}
				else
				{
					setAntSistemaConsultaExitosa(false);

					PrimeFaces.current().executeScript("PF('idDlgBusquedaAntSistema').show();");
					PrimeFaces.current().ajax().update("idDlgBusquedaAntSistema");
				}
			}
			else
			{
				setAntSistemaConsultaExitosa(false);

				PrimeFaces.current().executeScript("PF('idDlgBusquedaAntSistema').show();");
				PrimeFaces.current().ajax().update("idDlgBusquedaAntSistema");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaAntiguoSistema", lb2be_e);
			addMessage(lb2be_e);
			setDataConsultaPorCriterioAntSistema(null);
		}
	}

	/**
	 * Método encargado de validar la información ingresada por pantalla del documento.
	 *
	 * @param as_idForm Id del formulario desde donde se valida la información
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDatosDocumento(String as_idForm)
	    throws B2BException
	{
		try
		{
			setConsultaRealizada(true);
			validarDatosDocumento(as_idForm, true);

			Collection<DataConsultaDatosDocumento> lcdcdd_consultaDcoumentos;

			lcdcdd_consultaDcoumentos = iasr_antiguoSistemaRemote.findByDatosDocumento(getConsultaDatosDocumento());

			if(CollectionUtils.isValidCollection(lcdcdd_consultaDcoumentos))
			{
				for(DataConsultaDatosDocumento ldcdd_datosDocumento : lcdcdd_consultaDcoumentos)
				{
					Collection<DataConsultaPorCriterio> lcdcpc_consultaCriterios;
					Collection<DataConsultaPorCriterio> lcdcpc_tmp;

					lcdcpc_consultaCriterios     = ldcdd_datosDocumento.getDataConsultaPorCriterio();
					lcdcpc_tmp                   = new ArrayList<DataConsultaPorCriterio>();

					for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_consultaCriterios)
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
								lcdcpc_tmp.add(ldcpc_iterador);
							}
						}
					}

					setDataConsultaPorCriterioDocumento(lcdcpc_tmp);
				}

				if(CollectionUtils.isValidCollection(getDataConsultaPorCriterioDocumento()))
				{
					setDocumentoConsultaExitosa(true);
					setDocumentoConsulta(false);

					addMessage(MessagesKeys.BUSQUEDA_EXITOSA_ANT_SISTEMA_100);
					PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
				}
				else
					addMessageInfo(EstadoCommon.W, MessagesKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaDatosDocumento", lb2be_e);
			addMessage(lb2be_e);
			setDataConsultaPorCriterioDocumento(null);
		}
	}

	/**
	 * Método encargado de verificar la naturaleza jurídica de los actos seleccionados en el proceso de anotaciones.
	 */
	public void consultarAnotacionCancelacion()
	{
		try
		{
			String  ls_naturaleza;
			boolean lb_mostrarItervinientesMasivos;

			ls_naturaleza                      = getNaturalezaJuridicaSeleccionada();
			lb_mostrarItervinientesMasivos     = false;

			if(StringUtils.isValidString(ls_naturaleza))
			{
				String[] lsa_datos;

				lsa_datos = ls_naturaleza.split("-");

				if((lsa_datos != null) && (lsa_datos.length > 0))
				{
					String             ls_idNaturalezaJuridica;
					String             ls_version;
					NaturalezaJuridica lnj_parametros;

					ls_idNaturalezaJuridica     = lsa_datos[0];
					ls_version                  = (lsa_datos.length > 1) ? lsa_datos[1] : null;
					lnj_parametros              = new NaturalezaJuridica();

					lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
					lnj_parametros.setVersion(NumericUtils.getLong(ls_version));

					lnj_parametros = iasr_antiguoSistemaRemote.findNaturalezaJuridicaById(lnj_parametros);

					if(lnj_parametros != null)
					{
						String ls_grupo;
						String ls_descripcion;
						String ls_rrr;

						ls_grupo           = lnj_parametros.getIdGrupoNatJur();
						ls_descripcion     = lnj_parametros.getNombre();
						ls_rrr             = lnj_parametros.getIdtipoRrr();

						if(StringUtils.isValidString(ls_rrr))
							setDeshabilitarCalidadInterviniente(false);
						else
							setDeshabilitarCalidadInterviniente(true);

						setHabilitarSecuencia(lnj_parametros.isHabilitaSecuencia());

						{
							AnotacionPredio lap_tmp;
							lap_tmp = getAnotacionPredio();

							if(lap_tmp != null)
								lap_tmp.setEspecificacion(ls_descripcion);
						}

						{
							boolean lb_mostrarAnotacionCancela;

							lb_mostrarAnotacionCancela = StringUtils.isValidString(ls_grupo)
									&& (ls_grupo.contains("700") || ls_grupo.contains("800"));

							if(lb_mostrarAnotacionCancela)
								cargarDatosAnotacionesACancelar();

							setMostrarAnotacionCancela(lb_mostrarAnotacionCancela);
						}

						lb_mostrarItervinientesMasivos = irr_registroRemote.mostrarCargueIntervinientes(
							    ls_idNaturalezaJuridica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						setRenderedIntervinienteActo(lb_mostrarItervinientesMasivos);
					}
					else
						setAnotacionACancelar(null);
				}
			}
			else
			{
				AnotacionPredio lap_tmp;
				lap_tmp = getAnotacionPredio();

				if(lap_tmp != null)
					lap_tmp.setEspecificacion(null);

				setMostrarAnotacionCancela(false);
				setAnotacionACancelar(null);
			}

			setRenderedIntervinienteActo(lb_mostrarItervinientesMasivos);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAnotacionCancelacion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de consultar los intervinientes existentes en la base de datos con un documento de identidad específico.
	 */
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

				addMessage(MessagesKeys.BUSQUEDA_EXITOSA_VERIFIQUE_DATOS);
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_INTERVINIENTES);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPersonaDocumento", lb2be_e);
			setMostrarListadoPersonas(false);
			setDeshabilitarDatosInterviniente(false);
			setListadoIntervinientes(null);
			setPersona(null);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().executeScript("PF('wvCampoDocumento').focus()");
	}

	/**
	 * Método encargado de contar la cantidad de caracteres del campo complementación.
	 *
	 * @return devuelve el valor de String
	 */
	public String contar()
	{
		String ls_complementacion;
		char[] arrayChar;
		int    contador;
		String result;

		ls_complementacion     = getComplementacion();
		contador               = 0;

		if(ls_complementacion != null)
		{
			arrayChar     = ls_complementacion.toCharArray();
			contador      = arrayChar.length;
		}

		result = Integer.toString(contador);

		return result;
	}

	/**
	 * Método encargado de realizar una converción científica para datos numéricos.
	 *
	 * @param ad_valor correspondiente al valor del tipo de objeto Double
	 * @return devuelve el valor de String
	 */
	public String conversionCientifica(Double ad_valor)
	{
		return conversionCientifica(ad_valor, new DecimalFormat("#,###.00"));
	}

	/**
	 * Método encargado de realizar una converción científica para datos numéricos.
	 *
	 * @param ad_valor correspondiente al valor del tipo de objeto Double
	 * @param adf_format correspondiente al valor del tipo de objeto DecimalFormat
	 * @return devuelve el valor de String
	 */
	public String conversionCientifica(Double ad_valor, DecimalFormat adf_format)
	{
		String ls_conversion;

		ls_conversion = null;

		if((ad_valor != null) && (adf_format != null))
			ls_conversion = adf_format.format(ad_valor);

		return ls_conversion;
	}

	/**
	 * Método encargado de eliminar una matrícula seleccionada en pantalla en la sección de asociar matrículas.
	 *
	 * @param apaui_paui Objeto contenedor de la matrícula seleccionada
	 * @return null
	 */
	public String deleteActoSelected(PredioActoIU apaui_paui)
	{
		getActosAsociadosGeneral().remove(apaui_paui);

		if(isDevolucionEtapa110() && (apaui_paui != null))
		{
			try
			{
				DatosAntSistema ldas_datos;

				ldas_datos = new DatosAntSistema();

				ldas_datos.setIdMatricula(NumericUtils.getLongWrapper(apaui_paui.getMatricula()));
				ldas_datos.setIdSolicitud(getIdSolicitud());
				ldas_datos.setIdCirculo(getIdCirculo());
				ldas_datos.setIdDatosAntSistema(getIdDatosAntSistemaActual());

				iasr_antiguoSistemaRemote.eliminarMatriculasAsociadas(
				    ldas_datos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setProcesoTerminadoAsociarMatricula(false);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("deleteActoSelected", lb2be_e);
				addMessage(lb2be_e);
			}
		}

		return null;
	}

	/**
	 * Método encargado de eliminar la matricula base.
	 *
	 * @param adp_direccionBase correspondiente al valor del tipo de objeto DireccionPredio
	 * @return devuelve el valor de String
	 */
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
			clh_LOGGER.error("deleteMatriculaBase", lb2be_e);
			addMessage(lb2be_e);
		}

		getDatosBasicos().getMatriculaBase().getDireccionPredio().remove(adp_direccionBase);

		return null;
	}

	/**
	 * Método encargado de cargar los datos de departamento.
	 */
	public void departamento()
	{
		DatosBasicos ldb_datosBasicos;
		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			Apertura la_apertura;
			la_apertura = ldb_datosBasicos.getApertura();
			la_apertura.setIdMunicipio(null);
			la_apertura.setIdOficinaOrigen(null);
			findMunicipio();
			getOficinaOrigen();
		}
	}

	/**
	 * Método encargado de consultar los departamentos, sus municipios y oficinas orígen.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void departamento(boolean ab_accion)
	    throws B2BException
	{
		if(ab_accion)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_idDepartamento;
					ls_idDepartamento = loo_oficinaOrigen.getIdDepartamento();

					if(StringUtils.isValidString(ls_idDepartamento))
						loo_oficinaOrigen.setIdDepartamento(ls_idDepartamento);

					loo_oficinaOrigen.setIdMunicipio(null);
					loo_oficinaOrigen.setIdOficinaOrigen(null);
				}
			}
		}

		findMunicipios(ab_accion);
		getOficinaOrigen(ab_accion);
	}

	/**
	 * Método encargado de deshabilitar los campos con relación al nit.
	 */
	public void deshabilitarCamposNit()
	{
		Persona lp_persona;
		lp_persona = getPersona();

		if(lp_persona != null)
		{
			String ls_tipoDocumento;
			ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

			if(
			    StringUtils.isValidString(ls_tipoDocumento)
				    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
				setDeshabilitarCamposPorNit(true);
			else
				setDeshabilitarCamposPorNit(false);
		}
	}

	/**
	 * Método encargado de borrar una dirección.
	 *
	 * @param ad_direccion correspondiente al valor del tipo de objeto DireccionDelPredio
	 * @param ab_delete correspondiente al valor del tipo de objeto boolean
	 */
	public void direccionBorrar(Direccion ad_direccion, boolean ab_delete)
	{
		if(ad_direccion != null)
			ad_direccion.setEliminar(ab_delete);
	}

	/**
	 * Método encargado de editar copias de una matrícula.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void editarCopiarDeUnaMatricula()
	    throws B2BException
	{
		try
		{
			ComplementacionCalificacion lcc_complementacionCalificacion;

			lcc_complementacionCalificacion = getComplementacionCalificacion();

			if(lcc_complementacionCalificacion != null)
			{
				ComplementacionPredio complementacionPredio;

				complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

				if(complementacionPredio != null)
				{
					String ls_complementacion;

					ls_complementacion = complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
					{
						setHabilitarComplementacion(true);
						lcc_complementacionCalificacion.setCopiarEditar(true);
						setEditoComplementacion(true);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_COPIA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("editarCopiarDeUnaMatricula", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de realizar las validaciones respectivas dependiendo del proceso seleccionado por el usuario.
	 *
	 * @param as_opcion correspondiente al valor del tipo de objeto String
	 */
	public void elegirAccionAntSistema(String as_opcion)
	{
		if(StringUtils.isValidString(as_opcion))
		{
			if(as_opcion.equalsIgnoreCase(EstadoCommon.C))
			{
				Collection<PredioActoIU> lcpaiu_matriculas;

				lcpaiu_matriculas = getActosAsociadosGeneral();

				if(CollectionUtils.isValidCollection(lcpaiu_matriculas))
				{
					setCrearMatriculaAntSistema(false);

					StringBuilder          lsb_matricula;
					Iterator<PredioActoIU> lipaiu_iterador;

					lsb_matricula       = new StringBuilder();
					lipaiu_iterador     = lcpaiu_matriculas.iterator();

					while(lipaiu_iterador.hasNext())
					{
						PredioActoIU lpaiu_matr;

						lpaiu_matr = lipaiu_iterador.next();

						if(lpaiu_matr != null)
						{
							lsb_matricula.append(getMessages().getString(MessagesKeys.ELIMINAR_MATRICULA_ASOCIADA));
							lsb_matricula.append(getIdCirculo());
							lsb_matricula.append(IdentificadoresCommon.SIMBOLO_GUION);
							lsb_matricula.append(lpaiu_matr.getMatricula());
							lsb_matricula.append(getMessages().getString(MessagesKeys.SIGNO_INTERROGACION));

							if(lipaiu_iterador.hasNext())
								lsb_matricula.append(IdentificadoresCommon.SIMBOLO_COMA);
						}
					}

					setNumeroMatriculaEliminar(lsb_matricula.toString());

					PrimeFaces.current().executeScript("PF('dlgEliminarRegistrosAsociados').show();");
				}
				else
					prepararPantallaParaCrear();
			}
			else if(as_opcion.equalsIgnoreCase(EstadoCommon.A))
			{
				setAsociarMatriculaAntSistema(false);

				Collection<AccPredioRegistro> lcapr_predios;

				lcapr_predios = getDatosTurnoMatricula();

				if(CollectionUtils.isValidCollection(lcapr_predios))
				{
					StringBuilder               lsb_matricula;
					Iterator<AccPredioRegistro> liapr_iterador;

					lsb_matricula      = new StringBuilder();
					liapr_iterador     = lcapr_predios.iterator();

					while(liapr_iterador.hasNext())
					{
						AccPredioRegistro lapr_matr;

						lapr_matr = liapr_iterador.next();

						if(lapr_matr != null)
						{
							lsb_matricula.append(getMessages().getString(MessagesKeys.ELIMINAR_MATRICULA_CREADA));
							lsb_matricula.append(lapr_matr.getIdCirculo());
							lsb_matricula.append(IdentificadoresCommon.SIMBOLO_GUION);
							lsb_matricula.append(lapr_matr.getIdMatricula());
							lsb_matricula.append(getMessages().getString(MessagesKeys.SIGNO_INTERROGACION));

							if(liapr_iterador.hasNext())
								lsb_matricula.append(IdentificadoresCommon.SIMBOLO_COMA);
						}
					}

					setNumeroMatriculaEliminar(lsb_matricula.toString());

					PrimeFaces.current().executeScript("PF('dlgEliminarRegistrosCreados').show();");
				}
				else
					prepararPantallaParaAsociar();
			}
			else
				cargarCrearMatricula(true, as_opcion);
		}
	}

	/**
	 * Método encargado de eliminar una anotación.
	 */
	public void eliminarAnotacion()
	{
		try
		{
			Collection<Anotacion> lca_datos;
			Collection<Anotacion> lca_datosNuevos;
			long                  al_idAnotacion;

			lca_datos           = getAnotacionesAgregadas();
			lca_datosNuevos     = new ArrayList<Anotacion>();
			al_idAnotacion      = getIdAnotacionParaEliminar();

			if(CollectionUtils.isValidCollection(lca_datos) && (al_idAnotacion > 0L))
			{
				Collection<AnotacionPredio> lcap_anotacionesActualizadas;

				lcap_anotacionesActualizadas = null;

				for(Anotacion la_iterador : lca_datos)
				{
					if(la_iterador != null)
					{
						long ll_idAnotacion;
						ll_idAnotacion = la_iterador.getIdAnotacion();

						if(ll_idAnotacion != al_idAnotacion)
							lca_datosNuevos.add(la_iterador);
						else
							lcap_anotacionesActualizadas = iasr_antiguoSistemaRemote.eliminarAnotacioPredio(
								    la_iterador.getAnotacionPredio(), getUserId(), getLocalIpAddress(),
								    getRemoteIpAddress()
								);
					}
				}

				if(!CollectionUtils.isValidCollection(lca_datosNuevos))
					setAnotacionAgregada(false);
				else if(CollectionUtils.isValidCollection(lcap_anotacionesActualizadas))
				{
					for(Anotacion la_anotacion : lca_datosNuevos)
					{
						if(la_anotacion != null)
						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = la_anotacion.getAnotacionPredio();

							if(lap_anotacionPredio != null)
							{
								String                    ls_idAnotacionPredio;
								boolean                   lb_encontro;
								Iterator<AnotacionPredio> liap_iterador;

								ls_idAnotacionPredio     = StringUtils.getStringNotNull(
									    lap_anotacionPredio.getIdAnotacionPredio()
									);
								lb_encontro              = false;
								liap_iterador            = lcap_anotacionesActualizadas.iterator();

								while(liap_iterador.hasNext() && !lb_encontro)
								{
									AnotacionPredio lap_temp;

									lap_temp = liap_iterador.next();

									if(lap_temp != null)
									{
										String ls_idAnotacionActualizada;

										ls_idAnotacionActualizada = StringUtils.getStringNotNull(
											    lap_temp.getIdAnotacionPredio()
											);

										if(ls_idAnotacionPredio.equalsIgnoreCase(ls_idAnotacionActualizada))
										{
											la_anotacion.setAnotacionPredio(lap_temp);

											lb_encontro = true;
										}
									}
								}
							}
						}
					}
				}
				else
				{
					long ll_cont;

					ll_cont = 1L;

					for(Anotacion la_anotacion : lca_datosNuevos)
					{
						if(la_anotacion != null)
						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = la_anotacion.getAnotacionPredio();

							if(lap_anotacionPredio != null)
							{
								lap_anotacionPredio.setOrden(NumericUtils.getBigDecimal(ll_cont));
								lap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(ll_cont));

								la_anotacion.setIdAnotacion(ll_cont);

								ll_cont++;
							}
						}
					}
				}

				{
					Long ll_numeroAnotacionActual;

					ll_numeroAnotacionActual = getNumeroAnotacion();

					if(NumericUtils.isValidLong(ll_numeroAnotacionActual, 1))
					{
						int li_cont;

						li_cont = ll_numeroAnotacionActual.intValue();

						if(li_cont != 1)
						{
							li_cont--;

							setNumeroAnotacion(NumericUtils.getLongWrapper(li_cont));
						}
					}
				}

				setAnotacionesAgregadas(lca_datosNuevos);

				setIdAnotacionParaEliminar(0L);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de eliminar un área.
	 *
	 * @param aadap_detalle correspondiente al valor del tipo de objeto DetalleAreaPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
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

	/**
	 * Método encargado de eliminar un interviniente.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 */
	public void eliminarInterviniente(Anotacion aa_anotacion)
	{
		if(aa_anotacion != null)
		{
			long                  ll_idAnotacion;
			Collection<Anotacion> lca_datos;
			Collection<Anotacion> lca_datosNuevos;

			lca_datos           = getIntervinientesAgregados();
			lca_datosNuevos     = new ArrayList<Anotacion>();
			ll_idAnotacion      = aa_anotacion.getIdAnotacion();

			if(CollectionUtils.isValidCollection(lca_datos))
			{
				for(Anotacion la_iterador : lca_datos)
				{
					if(la_iterador != null)
					{
						long ll_idAnotacioniterador;
						ll_idAnotacioniterador = la_iterador.getIdAnotacion();

						if(ll_idAnotacioniterador == ll_idAnotacion)
						{
							Persona lp_personaActual;
							Persona lp_personaEliminar;

							lp_personaActual       = la_iterador.getPersona();
							lp_personaEliminar     = aa_anotacion.getPersona();

							if((lp_personaActual != null) && (lp_personaEliminar != null))
							{
								if(
								    StringUtils.getStringNotNull(lp_personaActual.getIdPersona())
									               .equalsIgnoreCase(lp_personaEliminar.getIdPersona())
								)
								{
									SolicitudInterviniente lsi_solicitudIntervinienteActual;
									SolicitudInterviniente lsi_solicitudIntervinienteEliminar;

									lsi_solicitudIntervinienteActual       = la_iterador.getSolicitudInterviniente();
									lsi_solicitudIntervinienteEliminar     = aa_anotacion.getSolicitudInterviniente();

									if(
									    (lsi_solicitudIntervinienteActual != null)
										    && (lsi_solicitudIntervinienteEliminar != null)
									)
									{
										if(
										    !(StringUtils.getStringNotNull(
											        lsi_solicitudIntervinienteActual.getRolPersona()
											    ).equalsIgnoreCase(lsi_solicitudIntervinienteEliminar.getRolPersona()))
										)
											lca_datosNuevos.add(la_iterador);
									}
								}
								else
									lca_datosNuevos.add(la_iterador);
							}
						}
						else
							lca_datosNuevos.add(la_iterador);
					}
				}

				setIntervinientesAgregados(lca_datosNuevos);
			}
		}
	}

	/**
	 * Método encargado de eliminar una matrícula segregada agregada enviada como parametro.
	 *
	 * @param aa_parametro Objeto contenedor de la matrícula a eliminar
	 */
	public void eliminarMatricula(Anotacion aa_parametro)
	{
		if(aa_parametro != null)
		{
			Collection<Anotacion> lcpr_datos;
			Collection<Anotacion> lcpr_datosNuevos;

			lcpr_datos           = getMatriculasSegregadas();
			lcpr_datosNuevos     = new ArrayList<Anotacion>();

			if(CollectionUtils.isValidCollection(lcpr_datos))
			{
				for(Anotacion la_iterador : lcpr_datos)
				{
					if(la_iterador != null)
					{
						PredioRegistro lpr_predioRegistro;
						PredioRegistro lpr_predioRegistroParam;

						lpr_predioRegistro          = la_iterador.getPredioRegistro();
						lpr_predioRegistroParam     = aa_parametro.getPredioRegistro();

						if((lpr_predioRegistro != null) && (lpr_predioRegistroParam != null))
						{
							long ll_idMatricula;
							long ll_idMatriculaParam;

							ll_idMatricula          = lpr_predioRegistro.getIdMatricula();
							ll_idMatriculaParam     = lpr_predioRegistroParam.getIdMatricula();

							if((ll_idMatricula != ll_idMatriculaParam))
								lcpr_datosNuevos.add(la_iterador);
						}
					}
				}

				setMatriculasSegregadas(lcpr_datosNuevos);
			}
		}
	}

	/**
	 * Método encargado de eliminar la matrícula asociada a un predio y prepara la pantalla para crear.
	 */
	public void eliminarMatriculaAsociada()
	{
		try
		{
			iasr_antiguoSistemaRemote.eliminarMatriculasAsociadas(
			    getDatoAntSistemaCargado(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			prepararPantallaParaCrear();

			setActosAsociadosGeneral(null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarMatriculaAsociada", lb2be_e);

			addMessage(lb2be_e);
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
		eliminarMatriculaComplementacion(amb_matricula, false, false);
	}

	/**
	 * Metodo encargado de eliminar matriculas para el proceso de complementacion copiada de matriculas.
	 *
	 * @param amb_matricula correspondiente al valor del tipo de objeto MatriculaBase
	 * @param ab_apartirDeAnotaciones bandera con la cual se cambia el flujo para el proceso de copiar aparatir de anotaciones
	 * @param ab_desdeDigitadorMasivo bandera con la cual se indica si el proceso desde el cual se invoca es digitador masivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void eliminarMatriculaComplementacion(
	    MatriculaBase amb_matricula, boolean ab_apartirDeAnotaciones, boolean ab_desdeDigitadorMasivo
	)
	    throws B2BException
	{
		Collection<MatriculaBase> lcmb_matriculas;
		boolean                   lb_validCollection;

		lcmb_matriculas        = getMatriculasParaGenerarComplementacion();
		lb_validCollection     = CollectionUtils.isValidCollection(lcmb_matriculas);

		if(lb_validCollection)
			lcmb_matriculas.remove(amb_matricula);

		if(ab_apartirDeAnotaciones)
		{
			construirComplementacion();
			validarConstruir(true);
		}
		else
			validarCopiarDeUnaMatricula(ab_desdeDigitadorMasivo, false);

		if(ab_desdeDigitadorMasivo)
		{
			{
				if(lb_validCollection)
					setHabilitarMatriculasComplementacionAnotacion(lcmb_matriculas.size() > 0);
			}
		}
	}

	/**
	 * Método encargado de eliminar la matrícula creada a un predio y prepara la pantalla para asociar.
	 */
	public void eliminarMatriculaCreada()
	{
		try
		{
			iasr_antiguoSistemaRemote.eliminarMatriculasCreadas(
			    getIdTurno(), getIdDatosAntSistemaActual(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			prepararPantallaParaAsociar();

			setDatosTurnoMatricula(null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarMatriculaCreada", lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de filtrar por circulo.
	 */
	public void filtrarPorCirculo()
	{
		DatosBasicos ldb_datosbasicos;

		ldb_datosbasicos = getDatosBasicos();

		if(ldb_datosbasicos != null)
		{
			UbicacionZonaRegistral luzr_ubicacion;

			luzr_ubicacion = ldb_datosbasicos.getUbicacion();

			if(luzr_ubicacion != null)
			{
				CirculoRegistral lcr_circuloRegistral;

				lcr_circuloRegistral = luzr_ubicacion.getCirculoRegistral();

				if(lcr_circuloRegistral != null)
				{
					String ls_idCirculo;

					ls_idCirculo = lcr_circuloRegistral.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						Collection<Departamento> lcd_temp;
						ZonaRegistral            lzr_zonaRegistral;

						lcd_temp              = findDepartamentosAntSistema();
						lzr_zonaRegistral     = new ZonaRegistral();

						lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

						try
						{
							lzr_zonaRegistral = irr_registroRemote.findZonaRegistralById(
								    lzr_zonaRegistral, getUserId()
								);
						}
						catch(B2BException lb2be_e)
						{
							clh_LOGGER.error("filtrarPorCirculo", lb2be_e);
							addMessage(lb2be_e);

							lzr_zonaRegistral = null;
						}

						for(Departamento ld_dept : lcd_temp)
						{
							if(ld_dept != null)
							{
								String ls_idDept;

								ls_idDept = StringUtils.getStringNotNull(ld_dept.getIdDepartamento());

								if(lzr_zonaRegistral != null)
								{
									String ls_zonaRegIdDept;

									ls_zonaRegIdDept = lzr_zonaRegistral.getIdDepartamento();

									if(ls_idDept.equals(ls_zonaRegIdDept))
									{
										Departamento ld_departamento;

										ld_departamento = luzr_ubicacion.getDepartamento();

										if(ld_departamento != null)
										{
											ld_departamento.setIdDepartamento(ls_idDept);
											luzr_ubicacion.setDepartamento(ld_departamento);
										}

										break;
									}
								}
							}
						}

						Collection<Municipio> lcm_mun;

						lcm_mun = findMunicipioAntSistema();

						if(lcm_mun != null)
						{
							for(Municipio lm_actual : lcm_mun)
							{
								if(lm_actual != null)
								{
									String ls_idMun;

									ls_idMun = StringUtils.getStringNotNull(lm_actual.getIdMunicipio());

									if(lzr_zonaRegistral != null)
									{
										String ls_zonaRegIdMun;

										ls_zonaRegIdMun = lzr_zonaRegistral.getIdMunicipio();

										if(ls_idMun.equals(ls_zonaRegIdMun))
										{
											Municipio lm_municipio;

											lm_municipio = luzr_ubicacion.getMunicipio();

											if(lm_municipio != null)
											{
												lm_municipio.setIdMunicipio(lm_actual.getIdMunicipio());
												luzr_ubicacion.setMunicipio(lm_municipio);
											}

											break;
										}
									}
								}
							}
						}

						ldb_datosbasicos.setUbicacion(luzr_ubicacion);
						setDatosBasicos(ldb_datosbasicos);
					}
				}
			}
		}
	}

	/**
	 * Método encargado de filtrar por circulo y anotación.
	 */
	public void filtrarPorCirculoAnotacion()
	{
		DatosAntSistema ldas_datosAntSistema;

		ldas_datosAntSistema = getDatosAntSistemaAnotacion();

		if(ldas_datosAntSistema != null)
		{
			String ls_idCirculo;

			ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				Collection<Departamento> lcd_temp;
				ZonaRegistral            lzr_zonaRegistral;

				lcd_temp              = findDepartamentosAntSistema();
				lzr_zonaRegistral     = new ZonaRegistral();

				lzr_zonaRegistral.setIdCirculo(ls_idCirculo);

				try
				{
					lzr_zonaRegistral = irr_registroRemote.findZonaRegistralById(lzr_zonaRegistral, getUserId());
				}
				catch(B2BException lb2be_e)
				{
					clh_LOGGER.error("filtrarPorCirculoAnotacion", lb2be_e);
					addMessage(lb2be_e);

					lzr_zonaRegistral = null;
				}

				if(CollectionUtils.isValidCollection(lcd_temp))
				{
					for(Departamento ld_dept : lcd_temp)
					{
						if(ld_dept != null)
						{
							String ls_idDept;

							ls_idDept = StringUtils.getStringNotNull(ld_dept.getIdDepartamento());

							if(lzr_zonaRegistral != null)
							{
								String ls_zonaRegIdDept;

								ls_zonaRegIdDept = lzr_zonaRegistral.getIdDepartamento();

								if(ls_idDept.equals(ls_zonaRegIdDept))
								{
									ldas_datosAntSistema.setIdDepartamento(ls_idDept);

									break;
								}
							}
						}
					}
				}

				Collection<Municipio> lcm_mun;

				lcm_mun = findMunicipioAntSistema();

				if(CollectionUtils.isValidCollection(lcm_mun))
				{
					for(Municipio lm_municipio : lcm_mun)
					{
						if(lm_municipio != null)
						{
							String ls_idMun;

							ls_idMun = StringUtils.getStringNotNull(lm_municipio.getIdMunicipio());

							if(lzr_zonaRegistral != null)
							{
								String ls_zonaRegIdMun;

								ls_zonaRegIdMun = lzr_zonaRegistral.getIdMunicipio();

								if(ls_idMun.equals(ls_zonaRegIdMun))
								{
									ldas_datosAntSistema.setIdMunicipio(lm_municipio.getIdMunicipio());

									break;
								}
							}
						}
					}
				}

				setDatosAntSistemaAnotacion(ldas_datosAntSistema);
			}
		}
	}

	/**
	 * Método encargado de filtrar por departamento Y municipio.
	 */
	public void filtrarPorDepYMun()
	{
		DatosAntSistema ldas_datosAntSistema;
		String          ls_dept;
		String          ls_mun;

		ldas_datosAntSistema     = null;
		ls_dept                  = null;
		ls_mun                   = null;

		ldas_datosAntSistema = getConsultaDatosAntSistema();

		if(ldas_datosAntSistema != null)
		{
			Collection<ZonaRegistral> lcidt_datos;

			ls_dept         = ldas_datosAntSistema.getIdDepartamento();
			ls_mun          = ldas_datosAntSistema.getIdMunicipio();
			lcidt_datos     = findAllZonaRegistrales();

			if(lcidt_datos != null)
			{
				for(ZonaRegistral lzr_zonaRegistral : lcidt_datos)
				{
					if(lzr_zonaRegistral != null)
					{
						if(
						    lzr_zonaRegistral.getIdDepartamento().equals(ls_dept)
							    && lzr_zonaRegistral.getIdMunicipio().equals(ls_mun)
						)
						{
							ldas_datosAntSistema.setIdCirculo(lzr_zonaRegistral.getIdCirculo());

							setConsultaDatosAntSistema(ldas_datosAntSistema);

							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Método encargado de consultar los actos de una solicitud y un circulo.
	 *
	 * @param ap_parametros correspondiente al valor del tipo de objeto Acto
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActo> findActosByIdSolicitudCirculo(Acto ap_parametros, String as_userId)
	{
		Collection<TipoActo> lca_actos = null;

		try
		{
			lca_actos = irr_registroRemote.findActosById(ap_parametros, as_userId, false, true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findActosByIdSolicitudCirculo", lb2be_e);
			lca_actos = null;
		}

		return lca_actos;
	}

	/**
	 * Método encargado de consultar las zonas registrales de un pais.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<ZonaRegistral> findAllZonaRegistrales()
	{
		Collection<ZonaRegistral> lczd_zonaRegistral;
		lczd_zonaRegistral = null;

		try
		{
			String ls_idPais;
			ls_idPais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

			if(ls_idPais != null)
				lczd_zonaRegistral = irr_registroRemote.findAllZonaRegistral();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findAllZonaRegistrales", lb2be_e);
			addMessage(lb2be_e);

			lczd_zonaRegistral = null;
		}

		return lczd_zonaRegistral;
	}

	/**
	 * Método encargado de consultar los departamentos de antiguo sistema.
	 *
	 * @param ab_accion Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @return devuelve los departamentos consultados.
	 */
	public Collection<Departamento> findDepartamentos(boolean ab_accion)
	{
		return findDepartamentos(ab_accion, false);
	}

	/**
	 * Método encargado de consultar los departamentos de antiguo sistema.
	 *
	 * @param ab_datosDocumento Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @param ab_docLibroSegundo Argumento de tipo <code>boolean</code> que determina si se es un caso de documento libro segundo.
	 * @return devuelve los departamentos consultados.
	 */
	public Collection<Departamento> findDepartamentos(boolean ab_datosDocumento, boolean ab_docLibroSegundo)
	{
		return findDepartamentos(ab_datosDocumento, ab_docLibroSegundo, false);
	}

	/**
	 * Método encargado de consultar los departamentos de antiguo sistema.
	 *
	 * @param ab_datosDocumento Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @param ab_docLibroSegundo Argumento de tipo <code>boolean</code> que determina si se es un caso de documento libro segundo.
	 * @param ab_documentoBusqueda Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del documento.
	 * @return devuelve los departamentos consultados.
	 */
	public Collection<Departamento> findDepartamentos(
	    boolean ab_datosDocumento, boolean ab_docLibroSegundo, boolean ab_documentoBusqueda
	)
	{
		Collection<Departamento> lcd_departamentos;
		Departamento             ld_parametros;

		lcd_departamentos     = null;
		ld_parametros         = new Departamento();

		try
		{
			if(ab_datosDocumento && !ab_docLibroSegundo)
			{
				ConsultaDatosDocumento lcdd_onsultaDatosDocumento;

				lcdd_onsultaDatosDocumento = getConsultaDatosDocumento();

				if(lcdd_onsultaDatosDocumento != null)
				{
					OficinaOrigen loo_oficinaOrigen;
					loo_oficinaOrigen = lcdd_onsultaDatosDocumento.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						ls_pais = loo_oficinaOrigen.getIdPais();

						if(StringUtils.isValidString(ls_pais))
							ld_parametros.setIdPais(ls_pais);
					}
				}
			}
			else
			{
				DetalleAntSistemaUI ldas_dasUI;
				ldas_dasUI = getDetalleAntSistemaConsulta();

				Documento ld_d;
				ld_d = ab_documentoBusqueda ? getDocumentoDetalleCargado()
					                        : ((ldas_dasUI != null) ? ldas_dasUI.getDocumentoConsulta() : null);

				if(ld_d != null)
				{
					OficinaOrigen loo_oficinaOrigen;
					loo_oficinaOrigen = ld_d.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						ls_pais = loo_oficinaOrigen.getIdPais();

						if(StringUtils.isValidString(ls_pais))
							ld_parametros.setIdPais(ls_pais);
					}
				}
			}

			lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
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
	 * Método encargado de consultar los departamentos de antiguo sistema.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosAntSistema()
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			DatosAntSistema ldas_datosAntSistema;
			String          ls_pais;

			ldas_datosAntSistema     = null;
			ls_pais                  = null;

			ldas_datosAntSistema = getConsultaDatosAntSistema();

			if(ldas_datosAntSistema != null)
				ls_pais = ldas_datosAntSistema.getIdPais();

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
			clh_LOGGER.error("findDepartamentosAntSistema", lb2be_e);
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Método encargado de buscar los departamentos para la información del documento.
	 *
	 * @return Colección resultante de la busqueda
	 */
	public Collection<Departamento> findDepartamentosDocumento()
	{
		return findDepartamentosDocumento(getDocumento());
	}

	/**
	 * Método encargado de buscar los departamentos para la información del documento.
	 *
	 * @param ad_documento documento contenedor del departamento
	 * @return Colección resultante de la busqueda
	 */
	public Collection<Departamento> findDepartamentosDocumento(Documento ad_documento)
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			if(ad_documento != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ad_documento.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findDepartamentosDocumento", lb2be_e);
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Método encargado de consultar los municipios de antiguo sistema.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioAntSistema()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DatosAntSistema ldas_datosAntSistema;

			ldas_datosAntSistema     = null;

			ldas_datosAntSistema = getConsultaDatosAntSistema();

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
			clh_LOGGER.error("findMunicipioAntSistema", lb2be_e);
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Método encargado de consultar los municipios de un departamento.
	 *
	 * @param ab_accion Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @return devuelve los municipios consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findMunicipios(boolean ab_accion)
	    throws B2BException
	{
		return findMunicipios(ab_accion, false);
	}

	/**
	 * Método encargado de consultar los municipios de un departamento.
	 *
	 * @param ab_datosDocumento Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @param ab_docLibroSegundo Argumento de tipo <code>boolean</code> que determina si se es un caso de documento libro segundo.
	 * @return devuelve los municipios consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findMunicipios(boolean ab_datosDocumento, boolean ab_docLibroSegundo)
	    throws B2BException
	{
		return findMunicipios(ab_datosDocumento, ab_docLibroSegundo, false);
	}

	/**
	 * Método encargado de consultar los municipios de un departamento.
	 *
	 * @param ab_datosDocumento Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del la consulta de documento.
	 * @param ab_docLibroSegundo Argumento de tipo <code>boolean</code> que determina si se es un caso de documento libro segundo.
	 * @param ab_documento Argumento de tipo <code>boolean</code> que determina si se deben tomar los datos precargados del documento.
	 * @return devuelve los municipios consultados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findMunicipios(
	    boolean ab_datosDocumento, boolean ab_docLibroSegundo, boolean ab_documento
	)
	    throws B2BException
	{
		Collection<Municipio> lcm_municipios;
		Municipio             lm_parametros;

		lcm_municipios     = null;
		lm_parametros      = new Municipio();

		try
		{
			if(ab_datosDocumento && !ab_docLibroSegundo)
			{
				ConsultaDatosDocumento lcdd_consultaDatosDocumento;
				lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

				if(lcdd_consultaDatosDocumento != null)
				{
					OficinaOrigen loo_oficinaOrigen;

					loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						String ls_departamento;

						ls_pais             = loo_oficinaOrigen.getIdPais();
						ls_departamento     = loo_oficinaOrigen.getIdDepartamento();

						if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
						{
							lm_parametros.setIdPais(ls_pais);
							lm_parametros.setIdDepartamento(ls_departamento);
						}
					}
				}
			}
			else
			{
				DetalleAntSistemaUI ldas_das;
				ldas_das = getDetalleAntSistemaConsulta();

				Documento ld_documento;
				ld_documento = ab_documento ? getDocumentoDetalleCargado()
					                        : ((ldas_das != null) ? ldas_das.getDocumentoConsulta() : null);

				if(ld_documento != null)
				{
					OficinaOrigen loo_oficinaOrigen;

					loo_oficinaOrigen = ld_documento.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						String ls_departamento;

						ls_pais             = loo_oficinaOrigen.getIdPais();
						ls_departamento     = loo_oficinaOrigen.getIdDepartamento();

						if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
						{
							lm_parametros.setIdPais(ls_pais);
							lm_parametros.setIdDepartamento(ls_departamento);
						}
					}
				}
			}

			lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findMunicipios", lb2be_e);
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Método encargado de buscar los municipios para la información del documento.
	 *
	 * @return Colección resultante de la busqueda
	 */
	public Collection<Municipio> findMunicipiosDocumento()
	{
		return findMunicipiosDocumento(getDocumento());
	}

	/**
	 * Método encargado de buscar los municipios para la información del documento.
	 *
	 * @param ad_documento documento contenedor del municipio
	 * @return Colección resultante de la busqueda
	 */
	public Collection<Municipio> findMunicipiosDocumento(Documento ad_documento)
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		if(ad_documento != null)
		{
			try
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(ad_documento.getIdPais());
				lm_parametros.setIdDepartamento(ad_documento.getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("findMunicipiosDocumento", lb2be_e);
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Método encargado de consultarla naturaleza jurídica rrr.
	 *
	 * @return retorna los tipos de documento que coincidieron con los criterios de búsqueda.
	 */
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
			clh_LOGGER.error("findRrr", lb2be_e);
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Método encargado de buscar un turno historia por id solicitud y id turno historia.
	 *
	 * @param as_turnoHistoria Argumento de tipo <code>String</code> que contiene los criterios de búsqueda.
	 * @return devuelve el valor de String
	 */
	public String findSolicitudByIdTurnoHistoria(String as_turnoHistoria)
	{
		String ls_solicitud;

		try
		{
			ls_solicitud = irr_calificacionRemote.findIdSolicitudByIdTurnoHistoria(as_turnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findSolicitudByIdTurnoHistoria", lb2be_e);
			ls_solicitud = null;
		}

		return ls_solicitud;
	}

	/**
	 * Método encargado de consultar los tipos de documentos cuando hay una secuencia activa.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoSecuencia()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		try
		{
			if(isHabilitarSecuencia())
			{
				String ls_tipoDocInter;
				ls_tipoDocInter = getTipoDocInterviniente();

				if(ls_tipoDocInter != null)
				{
					if(ls_tipoDocInter.equalsIgnoreCase(EstadoCommon.SE))
					{
						lcidt_datos = irr_referenceRemote.findTipoDocumento();

						Constantes lc_constante;
						lc_constante = new Constantes();
						lc_constante.setIdConstante(ConstanteCommon.CONSECUTIVO_TIPO_DOCUMENTO_SECUENCIA);
						lc_constante = irr_registroRemote.findConstante(lc_constante);

						if(lc_constante != null)
						{
							setDocumentoInterviniente(
							    StringUtils.getString(lc_constante.getEntero().add(NumericUtils.getBigInteger(1)))
							);
							setRenderedHabilitaSecuencia(true);
							getPersona().setIdDocumentoTipo(ls_tipoDocInter);
							getPersona().setNumeroDocumento(StringUtils.getString(lc_constante.getEntero()));
						}
					}
					else
					{
						lcidt_datos = irr_referenceRemote.findTipoDocumento();
						setDocumentoInterviniente(null);
						setRenderedHabilitaSecuencia(false);
					}
				}
				else
				{
					lcidt_datos = irr_referenceRemote.findTipoDocumento();
					setDocumentoInterviniente(null);
					setRenderedHabilitaSecuencia(false);
				}
			}
			else
			{
				lcidt_datos = irr_referenceRemote.findTipoDocumentoActivo();
				setDocumentoInterviniente(null);
				setRenderedHabilitaSecuencia(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("findTipoDocumentoSecuencia", lb2be_e);
			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 *  Método encargado de buscar el tipo de entidad en Antiguo sistema.
	 */
	public void findTipoEntidadAS()
	{
		findTipoEntidadAS(false);
	}

	/**
	 * Método de actualización de el Tipo entidad en Antiguo sistema.
	 *
	 * @param ab_documento de ab documento
	 */
	public void findTipoEntidadAS(boolean ab_documento)
	{
		try
		{
			DetalleAntSistemaUI ldas_das;
			ldas_das = getDetalleAntSistemaConsulta();

			Documento ld_documento;
			ld_documento = ab_documento ? getDocumentoDetalleCargado()
				                        : ((ldas_das != null) ? ldas_das.getDocumentoConsulta() : null);

			if(ld_documento != null)
			{
				if(StringUtils.isValidString(ld_documento.getIdTipoDocumento()))
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
				else
				{
					ld_documento.setIdTipoOficina(null);
					ld_documento.setTipoEntidad(null);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findTipoEntidadApertura", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de buscar el tipo entidad apertura.
	 */
	public void findTipoEntidadApertura()
	{
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
					Documento ld_documento;
					ld_documento = la_apertura.getDocumento();

					if(ld_documento != null)
					{
						if(StringUtils.isValidString(ld_documento.getIdTipoDocumento()))
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
						else
						{
							la_apertura.setIdTipoOficina(null);
							la_apertura.setIdTipoEntidad(null);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findTipoEntidadApertura", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de buscar tipo entidad documento.
	 */
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
			clh_LOGGER.error("findTipoEntidadDocumento", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método de búsqueda de el tipo Oficina Correspondiente al tipo de Documento.
	 *
	 * @return una collección de Tipo oficina
	 */
	public Collection<TipoOficina> findTipoOficinaAntSistema()
	{
		Collection<TipoOficina> lcto_datos;
		lcto_datos = new ArrayList<TipoOficina>();

		try
		{
			DetalleAntSistemaUI ldas_das;
			ldas_das = getDetalleAntSistemaConsulta();

			if(ldas_das != null)
			{
				Documento ld_d;
				ld_d = ldas_das.getDocumentoConsulta();

				if(ld_d != null)
				{
					lcto_datos = irr_referenceRemote.findTipoOficina(ld_d);

					if(CollectionUtils.isValidCollection(lcto_datos))
					{
						if(lcto_datos.size() == 1)
						{
							for(TipoOficina lto_tmp : lcto_datos)
							{
								if(lto_tmp != null)
									ld_d.setIdTipoOficina(lto_tmp.getIdTipoOficina());
							}
						}
						else if(lcto_datos.size() >= 2)
						{
							Iterator<TipoOficina> lito_oficinas;

							lito_oficinas = lcto_datos.iterator();

							if(lito_oficinas.hasNext())
							{
								TipoOficina lto_oficina;

								lto_oficina = lito_oficinas.next();

								if(lto_oficina != null)
								{
									String ls_idTipoOficina;

									ls_idTipoOficina = lto_oficina.getIdTipoOficina();

									if(StringUtils.isValidString(ls_idTipoOficina))
										ld_d.setIdTipoOficina(ls_idTipoOficina);
								}
							}
						}
					}
					else
					{
						TipoOficina ltf_seleccione;

						ltf_seleccione = new TipoOficina();

						ltf_seleccione.setNombre(ConstanteCommon.SELECCIONE);
						lcto_datos.add(ltf_seleccione);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lcto_datos = null;
		}

		return lcto_datos;
	}

	/**
	 * Método encargado de consultar los tipos de causales.
	 *
	 * @return devuelve los tipos de causales.
	 */
	public Collection<TipoCausal> findTiposCausales()
	{
		Collection<TipoCausal> lcta_cta;
		lcta_cta = new ArrayList<TipoCausal>();

		try
		{
			lcta_cta = irr_referenceRemote.findAllTiposCausalesActivos(
				    null, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false, false
				);

			if(CollectionUtils.isValidCollection(lcta_cta))
			{
				iltc_tiposCausalesFiltro = new ArrayList<TipoCausal>();
				setDetalleTipoCausal(lcta_cta);

				for(TipoCausal lo_tc : lcta_cta)
					iltc_tiposCausalesFiltro.add(lo_tc);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findTiposCausales", lb2be_e);
			addMessage(lb2be_e);
		}

		return lcta_cta;
	}

	/**
	 * Método encargado de controlar los tabuladores del proceso de creación de matrículas.
	 *
	 * @param afe_event Objeto contenedor de las acciones que realiza el usuario en pantalla
	 * @return Nombre de la pestaña que se va a mostrar en pantalla
	 */
	public String flowListenerCrearGrabar(FlowEvent afe_event)
	{
		boolean lb_antSistema;
		String  ls_return;
		String  ls_idFormulario;
		Ajax    la_ajax;

		ls_return           = afe_event.getNewStep();
		ls_idFormulario     = getFormulario();
		lb_antSistema       = (StringUtils.isValidString(ls_idFormulario)
				&& (ls_idFormulario.equalsIgnoreCase(is_idFormulario_ant_sistema)
				|| ls_idFormulario.equalsIgnoreCase(":" + is_idFormularioGrabar)));
		la_ajax             = PrimeFaces.current().ajax();

		try
		{
			String ls_oldStep;
			String ls_newStep;
			String ls_datosBasicos_id;
			String ls_cabidaYLinderos_id;
			String ls_areaPredio_id;
			String ls_direccionPredio_id;
			String ls_anotaciones_id;

			ls_datosBasicos_id        = cs_nombreTabDatosBasicos;
			ls_cabidaYLinderos_id     = "CabidaYLinderos_id";
			ls_areaPredio_id          = "AreaPredio_id";
			ls_direccionPredio_id     = "DireccionPredio_id";
			ls_anotaciones_id         = "Anotaciones_id";

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				if(ls_oldStep.equalsIgnoreCase(ls_datosBasicos_id))
				{
					salvarDatosBasicos(isGrabacion());
					setMostrarAtrasCrearGrabar(true);
					cargarComplementacionData();
					generarDatosActualizar2();
					actualizarNombrePredio();

					if(lb_antSistema)
						la_ajax.update("idFormAntSistema:idOPAcciones");
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_cabidaYLinderos_id))
				{
					if(!ls_newStep.equalsIgnoreCase(ls_datosBasicos_id))
					{
						salvarLinderosComplementacion();
						cargarDatosArea();
						setMostrarAtrasCrearGrabar(true);
					}
					else
						setMostrarAtrasCrearGrabar(false);
				}
				else if(
				    ls_oldStep.equalsIgnoreCase(ls_areaPredio_id)
					    && !ls_newStep.equalsIgnoreCase(ls_cabidaYLinderos_id)
				)
				{
					salvarAreaPredio();
					cargarDireccionFlowCrearGrabar(ls_idFormulario);
				}
				else if(
				    ls_oldStep.equalsIgnoreCase(ls_direccionPredio_id)
					    && !ls_newStep.equalsIgnoreCase(ls_areaPredio_id)
				)
				{
					salvarDireccionPredio();
					cargarAnotacion(false);

					if(isGrabacion())
						cargarTiposOficinaAnotacionesPorTipoDoc(EstadoCommon.AG);
					else
						cargarTiposOficinaAnotacionesPorTipoDoc(EstadoCommon.A);

					setOcultarSiguienteCrearGrabar(true);
					setArchivoPdf(null);
					setArchivoPdfDescarga(null);

					if(!lb_antSistema)
						crearAnotacionBaldios();
				}
				else if(
				    ls_oldStep.equalsIgnoreCase(ls_anotaciones_id)
					    && ls_newStep.equalsIgnoreCase(ls_direccionPredio_id)
				)
					setOcultarSiguienteCrearGrabar(false);

				if(ls_oldStep.equalsIgnoreCase(ls_direccionPredio_id) && ls_newStep.equalsIgnoreCase(ls_anotaciones_id))
					setDeshabilitarDatosDoc(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("flowListenerCrearGrabar", lb2be_e);
			addMessage(lb2be_e);

			ls_return = afe_event.getOldStep();
		}
		finally
		{
			if(isGrabacion())
				la_ajax.update(is_mensajesGrabarIdGrowl);
			else if(lb_antSistema)
				la_ajax.update(is_mensajesIdGrowl);
			else
				la_ajax.update(is_mensajesCalificacionGrowl);

			la_ajax.update(is_mensajesCalificacionGrowl);
		}

		return ls_return;
	}

	/**
	 * Método encargado de cargar las anotaciones de un predio.
	 *
	 * @return devuelve las anotaciones de un predio.
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> generarAnotacionesPredio()
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> lc_anotaciones;
		Collection<MatriculaBase>                                         lcmb_matriculas;

		lc_anotaciones      = null;
		lcmb_matriculas     = getMatriculasParaGenerarComplementacion();

		if(CollectionUtils.isValidCollection(lcmb_matriculas))
		{
			lc_anotaciones = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio>();

			for(MatriculaBase lmb_iterador : lcmb_matriculas)
			{
				if(lmb_iterador != null)
				{
					com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

					lap_anotacionPredio = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();
					lap_anotacionPredio.setIdMatricula(lmb_iterador.getIdMatricula());
					lap_anotacionPredio.setIdCirculo(lmb_iterador.getIdCirculo());

					lc_anotaciones.add(lap_anotacionPredio);
				}
			}
		}

		return lc_anotaciones;
	}

	/**
	 * Método encargado de generar archivo rechazo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarArchivoRechazo()
	    throws B2BException
	{
		TipoCausal                  ltc_rechazarSolicitud;
		byte[]                      lab_file;
		AmpliacionHistoriaRegistral lahr_ampliacionHistoriaRegistral;

		lahr_ampliacionHistoriaRegistral     = getAmpliacionHistoriaRegistral();
		ltc_rechazarSolicitud                = new TipoCausal();
		lab_file                             = null;

		try
		{
			String ls_th;
			ls_th = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_th))
				ltc_rechazarSolicitud.setIdTurno(ls_th);
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			if(lahr_ampliacionHistoriaRegistral == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_AMPLIACION_HISTORIA_REGISTRAL);

			if(CollectionUtils.isValidCollection(getTiposCausalesFiltro()))
			{
				ltc_rechazarSolicitud.setListTiposCausales(getTiposCausalesFiltro());
				ltc_rechazarSolicitud.setObservaciones(getObservacionesRechazo());

				lab_file = iasr_antiguoSistemaRemote.generarArchivoRechazo(
					    ltc_rechazarSolicitud, ls_th, getUserId(), lahr_ampliacionHistoriaRegistral,
					    getRemoteIpAddress(), getLocalIpAddress()
					);

				if(lab_file != null)
				{
					InputStream lis_stream;
					lis_stream = new ByteArrayInputStream(lab_file);

					setImagen(
					    new DefaultStreamedContent(
					        lis_stream, TipoContenidoCommon.PDF,
					        ConstanteCommon.NOMBRE_ARCHIVO_RECHAZO_SOLICITUD + ExtensionCommon.PDF_PUNTO
					    )
					);
					setPdfGenerado(true);
				}
				else
				{
					setPdfGenerado(false);
					setImagen(null);
					throw new B2BException(ErrorKeys.ERROR_VALIDACION_RECHAZAR_SOLICITUD);
				}

				setCausalesFinal(getFiltroTiposCausales());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoRechazo", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de generar datos actualizar.
	 */
	public void generarDatosActualizar()
	{
		Collection<ActualizarDatosAntSistema> lcadas_actualizar;

		lcadas_actualizar = new ArrayList<ActualizarDatosAntSistema>();

		if(!isAntSistemaConsultaNo())
		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.A);
			ladas_dato.setNombre("Actualizar datos antiguo sistema");

			lcadas_actualizar.add(ladas_dato);
		}

		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo("R");
			ladas_dato.setNombre("Rechazar solicitud");

			lcadas_actualizar.add(ladas_dato);
		}

		setTiposActualizarDatosAntSistema(lcadas_actualizar);
	}

	/**
	 * Método encargado de generar datos actualizar 101.
	 */
	public void generarDatosActualizar101()
	{
		Collection<ActualizarDatosAntSistema> lcadas_actualizar;

		lcadas_actualizar = new ArrayList<ActualizarDatosAntSistema>();

		if(!isAntSistemaConsultaNo())
		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.A);
			ladas_dato.setNombre("Actualizar datos antiguo sistema");

			lcadas_actualizar.add(ladas_dato);
		}

		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo("I");
			ladas_dato.setNombre("Informe de búsqueda");

			lcadas_actualizar.add(ladas_dato);
		}

		setTiposActualizarDatosAntSistema101(lcadas_actualizar);
	}

	/**
	 * Método encargado de generar datos actualizar.
	 */
	public void generarDatosActualizar2()
	{
		Collection<ActualizarDatosAntSistema> lcadas_actualizar;

		lcadas_actualizar = new ArrayList<ActualizarDatosAntSistema>();

		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.C);
			ladas_dato.setNombre("Crear matrícula");

			lcadas_actualizar.add(ladas_dato);
		}

		if(!isCrearMatriculasExistentes())
		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.A);
			ladas_dato.setNombre("Asociar matrícula");

			lcadas_actualizar.add(ladas_dato);
		}

		setTiposActualizarDatosAntSistema2(lcadas_actualizar);
	}

	/**
	 * Método encargado de carga la información de los predios asociados al turno en tramite.
	 */
	public void generarDatosAntSistema()
	{
		try
		{
			DatosAntSistema ldas_datosAntSistema;

			ldas_datosAntSistema = iasr_antiguoSistemaRemote.findDatosAntSistema(
				    NumericUtils.getLongWrapper(getIdTurnoHistoria())
				);

			if(ldas_datosAntSistema != null)
			{
				setConsultaDatosAntSistema(ldas_datosAntSistema);
				setIdCirculo(ldas_datosAntSistema.getIdCirculo());
				setIdCirculoProceso(ldas_datosAntSistema.getIdCirculo());
			}

			if(!CollectionUtils.isValidCollection(getInfoAntiguoSistema()))
				setInfoAntiguoSistema(
				    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ANT_SISTEMA
				    )
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDatosAntSistema", lb2be_e);
		}
	}

	/**
	 * Método encargado de generar datos documento.
	 */
	public void generarDatosDocumento()
	{
		try
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = iasr_antiguoSistemaRemote.findDatosDocumento(
				    NumericUtils.getLongWrapper(getIdTurnoHistoria())
				);

			if(lcdd_consultaDatosDocumento != null)
			{
				DatosAntSistema ldas_datosAntSistema;
				ldas_datosAntSistema = getConsultaDatosAntSistema();

				if(ldas_datosAntSistema != null)
				{
					String ls_idCirculo;

					ls_idCirculo = getIdCirculoProceso();

					if(!StringUtils.isValidString(ls_idCirculo))
					{
						String ls_idTurno;

						ls_idTurno = getIdTurno();

						if(ls_idTurno != null)
						{
							Turno lt_turno;

							lt_turno = irr_calificacionRemote.findTurno(ls_idTurno);

							if(lt_turno != null)
								ls_idCirculo = lt_turno.getIdCirculo();
						}
					}

					if(StringUtils.isValidString(ls_idCirculo))
					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = new CirculoRegistral();
						lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

						lcdd_consultaDatosDocumento.setCirculoRegistral(lcr_circuloRegistral);
					}
				}

				setConsultaDatosDocumento(lcdd_consultaDatosDocumento);

				setDocumentoData(iasr_antiguoSistemaRemote.findDocumentoData(getUserId(), lcdd_consultaDatosDocumento));
			}

			if(!CollectionUtils.isValidCollection(getDocumentos()))
			{
				setDocumentos(
				    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.DOCUMENTO
				    )
				);

				Map<String, Object> llhm_data;

				if(getDocumentos() != null)
				{
					llhm_data = getDocumentos().get(0);

					if(CollectionUtils.isValidCollection(llhm_data))
						setIdDocumento((String)llhm_data.get("ID_DOCUMENTO"));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDatosDocumento", lb2be_e);
		}
	}

	/**
	 * Método encargado de generar observaciones calificador.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarObservacionesCalificador()
	    throws B2BException
	{
		setObservacionesCalificador(iasr_antiguoSistemaRemote.findObservacionesCalificador(getIdTurnoHistoria()));
	}

	/**
	 * Método encargado de generar procesos.
	 */
	public void generarProcesos()
	{
		Collection<ActualizarDatosAntSistema> lcadas_procesos;

		lcadas_procesos = new ArrayList<ActualizarDatosAntSistema>();

		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.A);
			ladas_dato.setNombre("Antiguo sistema");

			lcadas_procesos.add(ladas_dato);
		}

		{
			ActualizarDatosAntSistema ladas_dato;

			ladas_dato = new ActualizarDatosAntSistema();

			ladas_dato.setCodigo(EstadoCommon.D);
			ladas_dato.setNombre("Documento");

			lcadas_procesos.add(ladas_dato);
		}

		setProcesos(lcadas_procesos);
	}

	/**
	 * Método encargado de guardar informe de busqueda.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarInformeDeBusqueda()
	    throws B2BException
	{
		try
		{
			DocumentosSalida ld_documento;

			ld_documento = new DocumentosSalida();

			ld_documento.setIdTurnoHistoria(NumericUtils.getInteger(getIdTurnoHistoria()));
			ld_documento.setIdUsuarioCreacion(getUserId());
			ld_documento.setIdDatosAntSistema(getIdDatosAntSistemaActual());

			iasr_antiguoSistemaRemote.guardarInformeDeBusqueda(
			    getObservaciones(), ld_documento, getBytesCargados(), getUserId(), getLocalIpAddress(),
			    getRemoteIpAddress()
			);

			addMessage(MessagesKeys.PROCESO_EXITOSO);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);

			setProcesoTerminadoInformeBusqueda(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarInformeDeBusqueda", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de habilitar cargues reporte resultados.
	 */
	public void habilitarCarguesReporteResultados()
	{
		String ls_cargueAMostrar;
		ls_cargueAMostrar = getHabilitarCargueResultados();

		if(BooleanUtils.getBooleanValue(ls_cargueAMostrar))
		{
			setHabilitarCargues(true);
			setHabilitarCompletitud(false);
			setComplementacion(null);
			setModificarComplementacion(false);
		}
		else
		{
			setHabilitarCompletitud(true);
			setHabilitarCargues(true);

			try
			{
				cargarComplementacion();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
			}
		}
	}

	/**
	 * Método de JS para iniciar un Poll
	 */
	public void iniciarPoll()
	{
		PrimeFaces.current().executeScript("PF('wvPoll').start();");
	}

	/**
	 * Método para limpiar la anotacion
	 */
	public void limpiarAnotacion()
	{
	}

	/**
	 * Método encargado de limpiar apertura.
	 */
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
	 * Método encargado de limpiar los datos de complementacion.
	 *
	 * @param ab_antSistema correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarComplementacionData(boolean ab_antSistema)
	    throws B2BException
	{
		limpiarComplementacionData(false, false, ab_antSistema);
	}

	/**
	 * Método encargado de limpiar los datos de complementación.
	 *
	 * @param ab_desdeDigitadorMasivo correspondiente al valor del tipo de objeto boolean
	 * @param ab_primerVezDigMasivo correspondiente al valor del tipo de objeto boolean
	 * @param ab_antSistema correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarComplementacionData(
	    boolean ab_desdeDigitadorMasivo, boolean ab_primerVezDigMasivo, boolean ab_antSistema
	)
	    throws B2BException
	{
		ComplementacionCalificacion lcc_complementacionCalificacion;

		lcc_complementacionCalificacion = getComplementacionCalificacion();

		if(lcc_complementacionCalificacion != null)
		{
			String ls_tipoComplementacion;

			ls_tipoComplementacion = lcc_complementacionCalificacion.getTipoComplementacion();

			if(!ab_primerVezDigMasivo && !ab_antSistema)
			{
				lcc_complementacionCalificacion.setComplementacionPredio(null);
				lcc_complementacionCalificacion.setComplementacion(null);
			}

			lcc_complementacionCalificacion.setObservaciones(null);
			lcc_complementacionCalificacion.setCopiarEditar(false);
			setHabilitarComplementacion(false);
			setMatriculasParaGenerarComplementacion(null);
			setEditoComplementacion(false);
			setPrimerVez(false);

			if(StringUtils.isValidString(ls_tipoComplementacion))
			{
				if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR))
				{
					setHabilitarComplementacion(true);
					setComplementacionAnotacion(null);

					if(!ab_antSistema && !ab_desdeDigitadorMasivo)
						agregarMatriculaComplementacion(
						    true, true, ab_desdeDigitadorMasivo, ab_primerVezDigMasivo, ab_antSistema
						);
				}
				else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
				{
					setHabilitarComplementacion(true);
					setComplementacionSinConstruir(false);

					if(!ab_antSistema && !ab_desdeDigitadorMasivo)
						addMessage(MessagesKeys.NO_COMPLEMENTACION_CERIFICAR_DIGITADOR_MASIVO);
				}
				else if(
				    ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR) && !ab_antSistema
					    && !ab_desdeDigitadorMasivo
				)
					agregarMatriculaComplementacion(
					    true, false, ab_desdeDigitadorMasivo, ab_primerVezDigMasivo, ab_antSistema
					);

				if((ab_antSistema || ab_desdeDigitadorMasivo))
				{
					ComplementacionPredio lcp_complementacionPredio;

					lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

					if(
					    (lcp_complementacionPredio != null)
						    && StringUtils.isValidString(lcp_complementacionPredio.getComplementacion())
					)
						setComplementacionSinConstruir(false);
				}
			}

			if(ab_antSistema)
			{
				ComplementacionCalificacion lcc_complementacion;

				lcc_complementacion = getComplementacionCalificacion();

				if(lcc_complementacion != null)
				{
					Complementacion lc_complementacion;

					lc_complementacion = lcc_complementacion.getComplementacion();

					if(lc_complementacion != null)
					{
						AccPredioRegistro lapr_predio;

						lapr_predio = getAccPredioRegistro();

						if(lapr_predio != null)
						{
							String ls_idCirculo;

							ls_idCirculo = lapr_predio.getIdCirculo();

							if(!StringUtils.isValidString(ls_idCirculo))
								ls_idCirculo = getIdCirculo();

							lc_complementacion.setIdCirculo(ls_idCirculo);
						}
					}
				}
			}
		}
	}

	/**
	 * Método encargado de borra la información que pueda estar contenida en el objeto
	 * datosAntSistemaAnotacion y habilita la escritura en los campos del panel de
	 * Datos de antiguo sistema.
	 */
	public void limpiarDatosAntSistemaAnotacion()
	{
		setDeshabilitarDatosAntiguoSistema(false);
		setDatosAntSistemaAnotacion(new DatosAntSistema());
	}

	/**
	 * Método encargado de borra la información que pueda estar contenida en el objeto documento y
	 * habilita la escritura en los campos del panel de Datos del documento.
	 */
	public void limpiarDatosDocumento()
	{
		Documento ld_documento;

		ld_documento = new Documento();

		ld_documento.setTipoEntidad(IdentificadoresCommon.POSICION_INICIAL);
		ld_documento.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

		setDeshabilitarDatosDoc(false);
		setDocumento(ld_documento);
	}

	/**
	 * Método encargado de borrar la información que pueda estar contenida en el formulario de anotaciones.
	 */
	public void limpiarDetalleAnotaciones()
	{
		setAnotacionPredio(null);
		setDocumento(null);
		setAnotacionCancelacion(null);
		setIntervinientesAgregados(null);
		setMatriculasSegregadas(null);
		setPredioSegregado(null);
		setNaturalezaJuridicaSeleccionada(null);
		setTipoDocInterviniente(null);
		setDocumentoInterviniente(null);
		setIdPersonaSeleccion(null);
		setNumeroAnotacion(null);
		setNumeroOrden(null);
		setListadoIntervinientes(null);
		setAnotacionAgregada(true);
		setDatosAntiguoSistema(null);
		setAnotacion(null);
		setCodigoNaturalezaJuridicaSeleccionada(null);
		setDeshabilitarNumeroAnotacion(false);
		setPanelDetalleIntervinientes(null);
		setLibroJustificacionRequiere(false);
		setSolicitudInterviniente(null);
		setPersona(null);
	}

	/**
	 * Metodo encargado de cargar la informacion de la anotacion seleccionada en el
	 * tab de anotaciones.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotaciones(Anotacion aa_anotacion)
	    throws B2BException
	{
		modificarAnotaciones(aa_anotacion, false, false);
	}

	/**
	 * Metodo encargado de cargar la informacion de la anotacion seleccionada en el
	 * tab de anotaciones.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ab_bng correspondiente al valor del tipo de objeto boolean
	 * @param ab_desdeDividida correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void modificarAnotaciones(Anotacion aa_anotacion, boolean ab_bng, boolean ab_desdeDividida)
	    throws B2BException
	{
		if(!ab_desdeDividida)
			limpiarDetalleAnotaciones();

		if(aa_anotacion != null)
		{
			AnotacionPredio       lap_anotacionPredio;
			AnotacionCancelacion  lac_anotacionCancelacion;
			DetalleAntSistema     ldas_detalleAntSistema;
			Documento             ld_documento;
			Collection<Anotacion> lca_intervinientesAgregados;
			Collection<Anotacion> lca_matriculasSegregadas;

			lap_anotacionPredio             = aa_anotacion.getAnotacionPredio();
			lac_anotacionCancelacion        = aa_anotacion.getAnotacionCancelacion();
			ldas_detalleAntSistema          = aa_anotacion.getDetalleAntSistema();
			ld_documento                    = aa_anotacion.getDocumento();
			lca_intervinientesAgregados     = aa_anotacion.getIntervinientesAgregados();
			lca_matriculasSegregadas        = aa_anotacion.getMatriculasSegregadas();

			if(lap_anotacionPredio != null)
			{
				lap_anotacionPredio.setBloqueo(aa_anotacion.isBloqueo());
				setNumeroAnotacion(lap_anotacionPredio.getIdAnotacion());
				setNaturalezaJuridicaSeleccionada(lap_anotacionPredio.getIdNaturalezaJuridica());
				setCodigoNaturalezaJuridicaSeleccionada(lap_anotacionPredio.getIdNaturalezaJuridica());
				setAnotacionPredio(lap_anotacionPredio);
			}

			if(ldas_detalleAntSistema != null)
			{
				String ls_idDetalle;

				ls_idDetalle = ldas_detalleAntSistema.getIdDetalleAntSistema();

				if(StringUtils.isValidString(ls_idDetalle))
				{
					Collection<DetalleAntSistema> lcdas_detalles;

					lcdas_detalles = getDetallesAntSistema();

					if(CollectionUtils.isValidCollection(lcdas_detalles))
					{
						boolean                     lb_continuar;
						Iterator<DetalleAntSistema> lidas_iterator;

						lb_continuar       = true;
						lidas_iterator     = lcdas_detalles.iterator();

						while(lidas_iterator.hasNext() && lb_continuar)
						{
							DetalleAntSistema ldas_detalle;

							ldas_detalle = lidas_iterator.next();

							if(ldas_detalle != null)
							{
								String ls_idDetalleTemp;

								ls_idDetalleTemp = ldas_detalle.getIdDetalleAntSistema();

								if(
								    StringUtils.isValidString(ls_idDetalleTemp)
									    && ls_idDetalleTemp.equalsIgnoreCase(ls_idDetalle)
								)
								{
									ldas_detalle.setSeleccionado(true);
									setDetalleAntSistemaAnotacion(ldas_detalle);
								}
							}
						}
					}
				}
			}

			if(ld_documento != null)
				setDocumento(ld_documento);

			cargarTiposOficinaAnotaciones(EstadoCommon.A);
			cargarListasDesplegablesDocumento();

			if(lac_anotacionCancelacion != null)
				setAnotacionCancelacion(lac_anotacionCancelacion);

			if(CollectionUtils.isValidCollection(lca_intervinientesAgregados))
			{
				if(ab_bng)
					setIntervinientesAgregadosBng(lca_intervinientesAgregados);
				else
					setIntervinientesAgregados(lca_intervinientesAgregados);
			}

			if(CollectionUtils.isValidCollection(lca_matriculasSegregadas))
				setMatriculasSegregadas(lca_matriculasSegregadas);

			setIdAnotacionGeneral(aa_anotacion.getIdAnotacion());
			setBloquearModificarAnotaciones(true);
			setDeshabilitarNumeroAnotacion(true);
			setAnotacionApertura(
			    irr_calificacionRemote.validarAnotacionApertura(
			        lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			{
				boolean lb_anotacionEnglobe;

				lb_anotacionEnglobe = irr_calificacionRemote.validarAnotacionEnglobe(
					    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lb_anotacionEnglobe)
				{
					cargarPrediosEnglobeCorreccion(getIdCirculo(), getIdMatricula());

					aa_anotacion.setMatriculasCorreccion(getMatriculasCorreccion());
				}

				setAnotacionEnglobe(lb_anotacionEnglobe);
			}
		}
	}

	/**
	 * Método encargado de modificar el area.
	 *
	 * @param aadap_detalle correspondiente al valor del tipo de objeto DetalleAreaPredio
	 */
	public void modificarArea(DetalleAreaPredio aadap_detalle)
	{
		if(aadap_detalle != null)
		{
			DetalleAreaPredio ladap_nuevo;

			ladap_nuevo = new DetalleAreaPredio();

			ladap_nuevo.setIdTipoArea(aadap_detalle.getIdTipoArea());
			ladap_nuevo.setAreaLectura(StringUtils.getString(aadap_detalle.getArea()));
			ladap_nuevo.setIdUnidadMedida(aadap_detalle.getIdUnidadMedida());
			ladap_nuevo.setIdDetalleAreaPredio(aadap_detalle.getIdDetalleAreaPredio());

			setDetalleAreaTerreno(ladap_nuevo);
			setBloquearAgregarAreaTerreno(true);
		}
	}

	/**
	 * Método encargado de modificar el area terreno.
	 */
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
					boolean      lb_focus;
					FacesContext lfc_context;
					String       ls_area;

					lb_focus        = false;
					lfc_context     = FacesContext.getCurrentInstance();
					ls_area         = ladap_detalle.getAreaLectura();
					lb_focus        = validateStyles(
						    ":idFormAntSistema:idProcesoAntiguoSistema:idITareaTerreno", lfc_context, ls_area, lb_focus
						);

					if(StringUtils.isValidString(ls_area))
					{
						Double ld_area;
						String ls_medidaArea;

						ld_area           = NumericUtils.getDoubleWrapper(ls_area);
						ls_medidaArea     = ladap_detalle.getIdUnidadMedida();

						if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
						{
							lb_focus = validateStyles(
								    ":idFormAntSistema:idProcesoAntiguoSistema:idITareaTerreno", lfc_context, ls_area,
								    lb_focus
								);
							ladap_detalle.setArea(ld_area);
						}
						else
						{
							lb_focus = validateStyles(":predialForm:idITareaTerreno", lfc_context, "", lb_focus);
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}

						lb_focus = validateStyles(
							    ":idFormAntSistema:idProcesoAntiguoSistema:idSOMunidadMedidaTerreno", lfc_context,
							    ls_medidaArea, lb_focus
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
												    ":idFormAntSistema:idProcesoAntiguoSistema:idSOMunidadMedidaTerreno",
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
			clh_LOGGER.error("modificarAreaTerreno", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de modificar un interviniente para una anotación.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 */
	public void modificarInterviniente(Anotacion aa_anotacion)
	{
		modificarInterviniente(aa_anotacion, false);
	}

	/**
	 * Método encargado de modificar un interviniente para una anotación.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ab_bng correspondiente al valor del tipo de objeto boolean
	 */
	public void modificarInterviniente(Anotacion aa_anotacion, boolean ab_bng)
	{
		if(aa_anotacion != null)
		{
			Anotacion la_anotacion;

			la_anotacion = new Anotacion(aa_anotacion);

			setIntervinienteActual(la_anotacion);

			Persona                  lp_persona;
			AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
			SolicitudInterviniente   lsi_solicitudInterviniente;

			lp_persona                        = la_anotacion.getPersona();
			lapc_anotacionPredioCiudadano     = la_anotacion.getAnotacionPredioCiudadano();
			lsi_solicitudInterviniente        = la_anotacion.getSolicitudInterviniente();

			setEditarInterviniente(true);

			if(lp_persona != null)
			{
				if(ab_bng)
					setPersonaBng(lp_persona);
				else
					setPersona(lp_persona);
			}

			if(lapc_anotacionPredioCiudadano != null)
			{
				if(ab_bng)
					setAnotacionPredioCiudadanoBng(lapc_anotacionPredioCiudadano);
				else
					setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);
			}

			if(lsi_solicitudInterviniente != null)
			{
				if(ab_bng)
					setSolicitudIntervinienteBng(lsi_solicitudInterviniente);
				else
					setSolicitudInterviniente(lsi_solicitudInterviniente);
			}

			setIdAnotacion(la_anotacion.getIdAnotacion());

			if(ab_bng)
				setPanelDetalleIntervinientesBng(la_anotacion.getPanelDetalleIntervinientes());
			else
				setPanelDetalleIntervinientes(la_anotacion.getPanelDetalleIntervinientes());

			setBloquearModificarIntervenientes(true);
		}
	}

	/**
	 * Método encargado de mostrar editar intervinientes.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public boolean mostrarEditarIntervinientes()
	{
		boolean lb_bool;
		lb_bool = false;

		if(isEditarInterviniente())
		{
			if(CollectionUtils.isValidCollection(findRrr()))
				lb_bool = true;
		}

		return lb_bool;
	}

	/**
	 * Método encargado de cargar los datos de antiguo sistema ingresados en la fase de registro.
	 */
	public void obtenerDatosAntSistema()
	{
		try
		{
			Collection<DatosAntSistema> lcdas_datos;
			lcdas_datos = iasr_antiguoSistemaRemote.obtenerDatosAntSistema(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcdas_datos))
			{
				boolean                   lb_revisado;
				Iterator<DatosAntSistema> li_iterador;

				lb_revisado     = true;
				li_iterador     = lcdas_datos.iterator();

				while(li_iterador.hasNext() && lb_revisado)
				{
					DatosAntSistema ldas_datoAnt;
					ldas_datoAnt = li_iterador.next();

					if(ldas_datoAnt != null)
					{
						String ls_revisadoAntSistema;
						ls_revisadoAntSistema = StringUtils.getStringNotNull(ldas_datoAnt.getRevisadoAntSistema());

						if(ls_revisadoAntSistema.equalsIgnoreCase(EstadoCommon.N))
							lb_revisado = false;
					}
				}

				setRevisadoAntSistema(lb_revisado);
				setDatosAntSistema(lcdas_datos);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerDatosAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cargar los datos de oficina origen.
	 *
	 * @param lb_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void oficinaOrigen(boolean lb_accion)
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lc_oficina;

			lc_oficina = getOficinaOrigen(lb_accion);

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("oficinaOrigen", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de cargar los datos de oficina origen apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void oficinaOrigenApertura()
	    throws B2BException
	{
		try
		{
			Collection<OficinaOrigen> lc_oficina;
			lc_oficina = getOficinaOrigenApertura();

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("oficinaOrigenApertura", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de cargar los datos de pais para apertura.
	 */
	public void pais()
	{
		DatosBasicos ldb_datosBasicos;
		ldb_datosBasicos = getDatosBasicos();

		if(ldb_datosBasicos != null)
		{
			Apertura la_apertura;
			la_apertura = ldb_datosBasicos.getApertura();

			if(la_apertura != null)
			{
				la_apertura.setIdDepartamento(null);
				la_apertura.setIdMunicipio(null);
			}
		}

		findDepartamentos();
		findMunicipio();
		getOficinaOrigen();
	}

	/**
	 * Método encargado de consultar el país por defecto.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void pais(boolean ab_accion)
	    throws B2BException
	{
		pais(ab_accion, false);
	}

	/**
	 * Método encargado de consultar el país por defecto.
	 *
	 * @param ab_datosDocumento correspondiente al valor del tipo de objeto boolean
	 * @param ab_docLibroSegundo para identificar si corresponde a una búsqueda de libro segundo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void pais(boolean ab_datosDocumento, boolean ab_docLibroSegundo)
	    throws B2BException
	{
		pais(ab_datosDocumento, ab_docLibroSegundo, false);
	}

	/**
	 * Método encargado de consultar el país por defecto.
	 *
	 * @param ab_datosDocumento correspondiente al valor del tipo de objeto boolean
	 * @param ab_docLibroSegundo para identificar si corresponde a una búsqueda de libro segundo
	 * @param ab_consulta Argumento de tipo <code>boolean</code> que determina si se debe consultar el dato precargado o no, para el documento.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void pais(boolean ab_datosDocumento, boolean ab_docLibroSegundo, boolean ab_consulta)
	    throws B2BException
	{
		if(ab_datosDocumento)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;

			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if((lcdd_consultaDatosDocumento != null) && !ab_docLibroSegundo)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_pais;
					ls_pais = loo_oficinaOrigen.getIdPais();

					if(StringUtils.isValidString(ls_pais))
						loo_oficinaOrigen.setIdPais(ls_pais);
					else
						loo_oficinaOrigen.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

					loo_oficinaOrigen.setIdDepartamento(null);
					loo_oficinaOrigen.setIdMunicipio(null);
				}
			}
			else
			{
				DetalleAntSistemaUI ldas_dasUI;
				Documento           ld_d;
				OficinaOrigen       loo_oficinaOrigen;

				ldas_dasUI     = getDetalleAntSistemaConsulta();

				ld_d     = ab_consulta ? getDocumentoDetalleCargado()
					                   : ((ldas_dasUI != null) ? ldas_dasUI.getDocumentoConsulta() : null);

				loo_oficinaOrigen = (ld_d != null) ? ld_d.getOficinaOrigen() : null;

				if(loo_oficinaOrigen != null)
				{
					String ls_pais;

					ls_pais = loo_oficinaOrigen.getIdPais();

					loo_oficinaOrigen.setIdPais(
					    StringUtils.isValidString(ls_pais) ? ls_pais : IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
					);

					loo_oficinaOrigen.setIdDepartamento(null);
					loo_oficinaOrigen.setIdMunicipio(null);
				}
			}
		}

		findDepartamentos(ab_datosDocumento, ab_docLibroSegundo, ab_consulta);
		findMunicipios(ab_datosDocumento, ab_docLibroSegundo, ab_consulta);
		getOficinaOrigen(ab_datosDocumento, ab_docLibroSegundo, ab_consulta);
	}

	/**
	 * Método encargado de crear el pdf a descargar.
	 */
	public void pdfCrear()
	{
		setArchivoPdf(
		    generarArchivosDescarga(
		        getArchivoPdfDescarga(), ExtensionCommon.PDF,
		        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
		    )
		);
	}

	/**
	 * Método encargado de prepara la pantalla y variables para crear matrículas.
	 */
	public void prepararPantallaParaAsociar()
	{
		setCrearMatriculaAntSistema(false);
		setAsociarMatriculaAntSistema(true);

		cargarCrearMatricula(true, EstadoCommon.A);
	}

	/**
	 * Método encargado de preparar la pantalla y variables para crear matrículas.
	 */
	public void prepararPantallaParaCrear()
	{
		setAsociarMatriculaAntSistema(false);
		setCrearMatriculaAntSistema(true);

		cargarCrearMatricula(true, EstadoCommon.C);
	}

	/**
	 * Método sobrecargado para realizar un cargue masivo desde la implementación de BeanEjecucionCorrecciones.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @param ab_propagarExcepcion Argumento de tipo <code>boolean</code> que indica si se debe propagar excepción <code>true</code> o no <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelIntervinientes(FileUploadEvent afue_event, boolean ab_propagarExcepcion)
	    throws B2BException
	{
		procesarExcelIntervinientes(afue_event, ab_propagarExcepcion, null);
	}

	/**
	 * Metodo encargado de procesar el cargue de excel de intervinientes.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @param ab_propagarExcepcion Argumento de tipo <code>boolean</code> que indica si se debe propagar excepción <code>true</code> o no <code>false</code>.
	 * @param aca_intervinientesAgregados Coleccion de tipo Anotacion con intervinientes actuales
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelIntervinientes(
	    FileUploadEvent afue_event, boolean ab_propagarExcepcion, Collection<Anotacion> aca_intervinientesAgregados
	)
	    throws B2BException
	{
		try
		{
			if(afue_event != null)
			{
				BeanRegistro lbr_beanRegistro;

				lbr_beanRegistro = getBeanRegistro();

				if(lbr_beanRegistro != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();

					ls_solicitud.setIdSolicitud(getIdSolicitud());

					ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

					if(ls_solicitud != null)
					{
						Collection<SolicitudInterviniente> lcsi_solicitudIntervinienteIngresados;

						lbr_beanRegistro.setSolicitud(ls_solicitud);
						lbr_beanRegistro.procesarExcelIntervinientes(afue_event, null, true);

						lcsi_solicitudIntervinienteIngresados = lbr_beanRegistro.getTablaSolicitudInterviniente();

						if(CollectionUtils.isValidCollection(lcsi_solicitudIntervinienteIngresados))
						{
							Collection<SolicitudInterviniente> lcsi_cllSolicitudIntervinienteActual;

							lcsi_cllSolicitudIntervinienteActual = new ArrayList<SolicitudInterviniente>();

							if(CollectionUtils.isValidCollection(aca_intervinientesAgregados))
							{
								for(Anotacion la_anotacionTemp : aca_intervinientesAgregados)
								{
									if(la_anotacionTemp != null)
									{
										Persona lp_personaActual;

										lp_personaActual = la_anotacionTemp.getPersona();

										if(lp_personaActual != null)
										{
											SolicitudInterviniente lsi_solicitudIntervinienteActual;

											lsi_solicitudIntervinienteActual = la_anotacionTemp
													.getSolicitudInterviniente();

											if(lsi_solicitudIntervinienteActual != null)
											{
												lsi_solicitudIntervinienteActual.setPersona(lp_personaActual);

												lcsi_cllSolicitudIntervinienteActual.add(
												    lsi_solicitudIntervinienteActual
												);
											}
										}
									}
								}
							}

							Collection<Anotacion> lca_intervinientesAgregados;

							lca_intervinientesAgregados = new ArrayList<Anotacion>();

							for(SolicitudInterviniente lsi_iterador : lcsi_solicitudIntervinienteIngresados)
							{
								if(lsi_iterador != null)
								{
									if(CollectionUtils.isValidCollection(lcsi_cllSolicitudIntervinienteActual))
									{
										lcsi_cllSolicitudIntervinienteActual = irr_registroRemote
												.enlistarPorMapaIntervinientes(
												    lcsi_cllSolicitudIntervinienteActual, lsi_iterador,
												    NumericUtils.DEFAULT_LONG_VALUE, getUserId(), getLocalIpAddress(),
												    getRemoteIpAddress()
												);

										if(CollectionUtils.isValidCollection(lcsi_cllSolicitudIntervinienteActual))
										{
											Anotacion la_anotacion;

											la_anotacion = new Anotacion();

											la_anotacion.setSolicitudInterviniente(lsi_iterador);
											la_anotacion.setPersona(lsi_iterador.getPersona());

											{
												AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

												lapc_anotacionPredioCiudadano = new AnotacionPredioCiudadano();

												lapc_anotacionPredioCiudadano.setPorcentajeParticipacion(
												    lsi_iterador.getPorcentaje()
												);

												la_anotacion.setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);
											}

											aca_intervinientesAgregados.add(la_anotacion);
										}
									}
									else
									{
										Anotacion la_anotacion;

										la_anotacion = new Anotacion();

										la_anotacion.setSolicitudInterviniente(lsi_iterador);
										la_anotacion.setPersona(lsi_iterador.getPersona());

										{
											AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;

											lapc_anotacionPredioCiudadano = new AnotacionPredioCiudadano();

											lapc_anotacionPredioCiudadano.setPorcentajeParticipacion(
											    lsi_iterador.getPorcentaje()
											);

											la_anotacion.setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);
										}

										lca_intervinientesAgregados.add(la_anotacion);
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcsi_cllSolicitudIntervinienteActual))
								setIntervinientesAgregados(aca_intervinientesAgregados);
							else
								setIntervinientesAgregados(lca_intervinientesAgregados);
						}

						setResultadoCargueIntervinientes(lbr_beanRegistro.getResultadoCargueIntervinientes());
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesarExcelIntervinientes", lb2be_e);
			addMessage(lb2be_e);

			if(ab_propagarExcepcion)
				throw lb2be_e;
		}
		finally
		{
			actualizarComponente(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de procesar el cargue masivo de intervinientes.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 */
	public String processFile(FileUploadEvent afue_event)
	{
		try
		{
			String       ls_mensaje = null;
			UploadedFile luf_file;

			luf_file                = afue_event.getFile();

			if(luf_file != null)
			{
				if(luf_file.getSize() <= 500000)
				{
					byte[] lba_excelFile;

					lba_excelFile = luf_file.getContents();

					if((lba_excelFile != null) && (luf_file.getFileName() != null))
						lba_excelFile = FileLoad(lba_excelFile, luf_file.getFileName(), getUserId());

					if(lba_excelFile != null)
					{
						String ls_nombreArchivo;

						ls_nombreArchivo = luf_file.getFileName();

						if(StringUtils.isValidString(ls_nombreArchivo))
						{
							InputStream stream = new ByteArrayInputStream(lba_excelFile);
							isc_file = new DefaultStreamedContent(
								    stream, luf_file.getContentType(), "Resultado_del_Cargue.xlsx"
								);
						}
					}
					else
						isc_file = null;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_ARCHIVO_PESO);
			}
			else
				ls_mensaje = getMessages().getString(MessagesKeys.NO_ENCONTRAR_ARCHIVO_CARGUE_MASIVO);

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = getMessages().getString(MessagesKeys.PROCESAMINETO_DE_CARGUE_MASIVO_TERMINADO);

			addMessage("I", ls_mensaje);

			PrimeFaces.current().ajax().update("idFormAntSistema:idDtArchivosCargadosReporte");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("processFile", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("processFile", le_e);
			addMessage("E", le_e.getMessage());
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}

		return null;
	}

	/**
	 * Método encargado de salvar o asociar individual.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String respuestaAsociarMatricula(boolean ab_accion)
	    throws B2BException
	{
		boolean lb_return;
		String  ls_return;

		lb_return     = false;
		ls_return     = null;

		try
		{
			if(ab_accion)
				lb_return = salvarAsociarIndividual(getDataConsultaPorCriterioAntSistema());
			else
				lb_return = salvarAsociarIndividual(getDataConsultaPorCriterioDocumento());

			setMostrarBandeja(true);
			ls_return = NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("respuestaAsociarMatricula", lb2be_e);
			addMessage(lb2be_e);
		}

		if(lb_return)
		{
			FacesContext lfc_context;
			lfc_context = FacesUtils.getFacesContext();

			BeanDetalleAntiguoSistema lbrc_bean;
			lbrc_bean = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA, BeanDetalleAntiguoSistema.class
					);

			if(lbrc_bean != null)
			{
				lbrc_bean.setBandejaentrada(true);
				lbrc_bean.setData(null);
				lbrc_bean.generarData();
			}
		}

		return ls_return;
	}

	/**
	 * Método encargado de cargar la respuesta a busqueda ant sistema.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void respuestaBusquedaAntSistema(boolean ab_accion)
	{
		setAntSistemaConsultaNo(!ab_accion);
		setAntSistemaConsulta(ab_accion);
		setInformeDeBusquedaAntSistema2(false);
		setTipoConsultaManual(null);
		generarDatosActualizar();
		generarDatosActualizar101();
	}

	/**
	 * Método encargado de cargar la respuesta de busqueda documento.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void respuestaBusquedaDocumento(boolean ab_accion)
	{
		if(ab_accion)
		{
			setTipoConsulta(EstadoCommon.A);

			setTipoConsultaManual(null);
			generarDatosActualizar();
			generarDatosActualizar101();
		}
		else
			setDocumentoConsulta(true);

		setAntSistemaConsulta(ab_accion);
		setAntSistemaConsultaNo(false);
	}

	/**
	 * Método encargado de salvar una anotacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarAnotacion()
	    throws B2BException
	{
		salvarAnotacion(false);
	}

	/**
	 * Método encargado de salvar una anotacion.
	 *
	 * @param ab_borrar de ab borrar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarAnotacion(boolean ab_borrar)
	    throws B2BException
	{
		boolean lb_calificacion;
		String  ls_idFormulario;

		ls_idFormulario     = getFormulario();
		lb_calificacion     = StringUtils.isValidString(ls_idFormulario)
				&& ls_idFormulario.contentEquals(is_idFormulario_calificacion);

		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				TurnoHistoria lth_turnoHistoria;
				Anotacion     la_retorno;

				lth_turnoHistoria = new TurnoHistoria();

				la_anotacion.setDatosAntiguoSistema(getDatosAntSistemaAnotacion());
				la_anotacion.setAccPredioRegistro(getAccPredioRegistro());
				la_anotacion.setAnotacionesAgregadas(getAnotacionesAgregadas());
				la_anotacion.setBloqueo(true);
				la_anotacion.setBorrar(ab_borrar);

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				la_anotacion.setTurnoHistoria(lth_turnoHistoria);

				la_retorno = iasr_antiguoSistemaRemote.salvarAnotaciones(
					    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(la_retorno != null)
				{
					byte[] lba_pdf;

					lba_pdf = la_retorno.getArchivoGenerado();

					if(lba_pdf == null)
						throw new B2BException(ErrorKeys.NO_GENERO_PDF);

					setArchivoPdfDescarga(lba_pdf);
					setProcesoTerminadoCrearGrabar(true);
					setContadorAnotaciones(-1);

					abrirCerrarPanel(false, cs_idPanelMatriculasSegregadas);
					abrirCerrarPanel(false, cs_idPanelInterviniente);
					abrirCerrarPanel(false, cs_idPanelEspecificacion);
					abrirCerrarPanel(false, cs_idPanelDatosAntSistema);
					abrirCerrarPanel(false, cs_idPanelDatosDocumento);
					abrirCerrarPanel(false, cs_idPanelDatosAnotacion);

					{
						Ajax la_ajax;

						la_ajax = PrimeFaces.current().ajax();

						if(la_ajax != null)
						{
							if(!lb_calificacion)
							{
								addMessage(MessagesKeys.VOLVER_RESUMEN_MATRICULAS_CREADAS);

								la_ajax.update("idFormAntSistema:opPanelTerminarProceso");
								la_ajax.update(is_mensajesIdGrowl);
							}
							else
							{
								addMessage(MessagesKeys.PROCESO_TERMINADO_BALDIOS);
								la_ajax.update(is_idFormulario_calificacion + "opPanelTerminarProceso");
								la_ajax.update(is_idFormulario_calificacion + "opBtnGenerateFile");
								la_ajax.update(is_mensajesCalificacionGrowl);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAnotacion", lb2be_e);
			addMessage(lb2be_e);

			if(lb_calificacion)
				PrimeFaces.current().ajax().update(is_mensajesCalificacionGrowl);
			else
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de salvar area predio.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarAreaPredio()
	    throws B2BException
	{
		try
		{
			if(!isBloquearAgregarAreaTerreno())
			{
				AccAreaUI         laaui_data;
				AccPredioRegistro lapr_predioRegistro;

				laaui_data              = validarPanelAreaPredi();
				lapr_predioRegistro     = getAccPredioRegistro();

				if((laaui_data != null) && (lapr_predioRegistro != null))
				{
					String ls_idCirculo;
					Long   ll_idMatricula;

					ls_idCirculo       = lapr_predioRegistro.getIdCirculo();
					ll_idMatricula     = lapr_predioRegistro.getIdMatricula();

					if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
					{
						String ls_idTurno;

						ls_idTurno = getIdTurno();

						if(!StringUtils.isValidString(ls_idTurno))
						{
							AccPredioRegistro lapr_predioRegistroTemp;

							lapr_predioRegistroTemp = getAccPredioRegistro();

							if(lapr_predioRegistroTemp != null)
								ls_idTurno = lapr_predioRegistroTemp.getIdTurno();
						}

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
				throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAreaPredio", lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método encargado de salvar y asociar actos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarAsociar()
	    throws B2BException
	{
		try
		{
			Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosInsertar;
			Collection<SolicitudMatricula>     lcsm_datosBandejaPrediosInsertar;
			Collection<PredioActoIU>           lcpaui_bandeja;

			lcsma_datosBandejaActosInsertar      = new ArrayList<SolicitudMatriculaActo>();
			lcsm_datosBandejaPrediosInsertar     = new ArrayList<SolicitudMatricula>();
			lcpaui_bandeja                       = getActosAsociadosGeneral();

			if(!CollectionUtils.isValidCollection(lcpaui_bandeja))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS);
			else
			{
				String        ls_accion;
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

				lth_turnoHistoria     = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

				ls_accion = EstadoCommon.A;

				if(lth_turnoHistoria != null)
				{
					long    ll_cantidadCertificadosSolicitud;
					boolean lb_procesoSinActos;

					ll_cantidadCertificadosSolicitud     = 0L;
					lb_procesoSinActos                   = false;

					{
						Constantes lc_procesosSinActos;

						lc_procesosSinActos = irr_referenceRemote.findConstantesById(
							    ConstanteCommon.PROCESOS_SIN_ACTOS
							);

						if(lc_procesosSinActos != null)
						{
							Map<String, String> lmss_codigoProcesosSinActos;

							lmss_codigoProcesosSinActos = ListadoCodigosConstantes.generarCodigos(
								    lc_procesosSinActos.getCaracter()
								);

							if(
							    CollectionUtils.isValidCollection(lmss_codigoProcesosSinActos)
								    && lmss_codigoProcesosSinActos.containsKey(lth_turnoHistoria.getIdProceso())
							)
								lb_procesoSinActos = true;
						}
					}

					for(PredioActoIU lpaui_actual : lcpaui_bandeja)
					{
						if(lpaui_actual != null)
						{
							SolicitudMatricula lsm_matricula;
							String             ls_matricula;

							lsm_matricula     = new SolicitudMatricula();

							ls_matricula = getIdCirculo() + "-" + lpaui_actual.getMatricula();

							if(StringUtils.isValidString(ls_matricula))
							{
								String ls_circuloRegistral;
								long   ll_matricula;

								if(!ls_matricula.contains("-"))
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = ls_matricula;
									throw new B2BException(ErrorKeys.MATRICULA_INMOBILIARIA_INVALIDA, aoa_messageArgs);
								}

								int li_inicio = 0;

								li_inicio     = ls_matricula.lastIndexOf("-");

								ll_matricula     = NumericUtils.getLong(
									    ls_matricula.substring(li_inicio + 1, ls_matricula.length())
									);

								ls_circuloRegistral = ls_matricula.substring(0, li_inicio);

								{
									boolean lb_certificadoAsociado;

									lb_certificadoAsociado = false;

									CirculoRegistral lcr_circulo;

									lcr_circulo = new CirculoRegistral();

									lcr_circulo.setIdCirculo(ls_circuloRegistral);

									lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

									if(lcr_circulo != null)
									{
										PredioRegistro lpr_predio;

										lpr_predio = new PredioRegistro();

										lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
										lpr_predio.setIdMatricula(ll_matricula);

										lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

										if(lpr_predio != null)
										{
											lsm_matricula.setIdMatricula(ll_matricula);
											lsm_matricula.setIdCirculo(ls_circuloRegistral);
											lsm_matricula.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
											lb_certificadoAsociado = lpaui_actual.isCertificadoAsociado();

											lsm_matricula.setExpedirCertificado(lb_certificadoAsociado ? "S" : "N");

											if(lb_certificadoAsociado)
											{
												long ll_cantidadCertificados;

												ll_cantidadCertificados     = NumericUtils.getLong(
													    lpaui_actual.getCantidad()
													);

												ll_cantidadCertificadosSolicitud = ll_cantidadCertificadosSolicitud
													+ ll_cantidadCertificados;

												if(
												    NumericUtils.isValidLong(
													        NumericUtils.getLongWrapper(ll_cantidadCertificados), 1
													    )
												)
													lsm_matricula.setCantidadCertificados(
													    NumericUtils.getBigDecimal(ll_cantidadCertificados)
													);
												else
												{
													Object[] aoa_messageArgs = new String[1];

													aoa_messageArgs[0] = ls_matricula;

													throw new B2BException(
													    ErrorKeys.ERROR_CANTIDAD_CERTIFICADOS_NO_VALIDA_MATRICULA,
													    aoa_messageArgs
													);
												}
											}
											else
											{
												BigDecimal      lbd_cantidadCertificados;
												DatosAntSistema ldas_datos;

												lbd_cantidadCertificados     = new BigDecimal(0);
												ldas_datos                   = getDatoAntSistemaCargado();

												if(ldas_datos != null)
												{
													Long ll_cantidadCertificados;

													ll_cantidadCertificados = ldas_datos.getCantidadCertificados();

													if(NumericUtils.isValidLong(ll_cantidadCertificados))
														lbd_cantidadCertificados = NumericUtils.getBigDecimal(
															    NumericUtils.getLong(ll_cantidadCertificados)
															);
												}

												lsm_matricula.setCantidadCertificados(lbd_cantidadCertificados);
											}

											lsm_matricula.setEstado(ls_accion);
											lsm_matricula.setIdDatosAntSistema(getIdDatosAntSistemaActual());
											lsm_matricula.setIdUsuarioCreacion(getUserId());
											lsm_matricula.setIdUsuarioModificacion(getUserId());
											lsm_matricula.setIpCreacion(getLocalIpAddress());
											lsm_matricula.setIpModificacion(getLocalIpAddress());
										}
										else
										{
											Object[] aoa_messageArgs = new String[1];

											aoa_messageArgs[0] = ls_circuloRegistral + "-" + ll_matricula;

											throw new B2BException(
											    ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, aoa_messageArgs
											);
										}
									}
									else
									{
										Object[] aoa_messageArgs = new String[1];

										aoa_messageArgs[0] = ls_circuloRegistral;

										throw new B2BException(
										    ErrorKeys.ERROR_NO_CIRCULO_REGISTRAL_ENCONTRADO, aoa_messageArgs
										);
									}
								}

								{
									List<ColumnModel> lcm_lcm;

									lcm_lcm = getColumns();

									if(CollectionUtils.isValidCollection(lcm_lcm))
									{
										Map<String, Boolean> lmsb_actos;
										boolean              lb_ActoAsocido;

										lb_ActoAsocido     = false;

										lmsb_actos = lpaui_actual.getActos();

										for(ColumnModel lcm_tmp : lcm_lcm)
										{
											if(lcm_tmp != null)
											{
												boolean lb_actoAsociado;
												String  ls_idActo;

												lb_actoAsociado     = false;
												ls_idActo           = lcm_tmp.getIdActo();

												if(StringUtils.isValidString(ls_idActo))
													lb_actoAsociado = BooleanUtils.getBooleanValue(
														    lmsb_actos.get(ls_idActo)
														);

												if(lb_actoAsociado)
												{
													SolicitudMatriculaActo lsma_matriculaActo;

													lsma_matriculaActo = new SolicitudMatriculaActo();

													lsma_matriculaActo.setIdSolicitud(
													    lth_turnoHistoria.getIdSolicitud()
													);
													lsma_matriculaActo.setIdCirculo(ls_circuloRegistral);
													lsma_matriculaActo.setIdMatricula(ll_matricula);
													lsma_matriculaActo.setIdActo(lcm_tmp.getIdActo());
													lsma_matriculaActo.setIdTurno(lth_turnoHistoria.getIdTurno());
													lsma_matriculaActo.setEstado(ls_accion);
													lsma_matriculaActo.setUsuario(getUserId());
													lsma_matriculaActo.setIdUsuarioCreacion(getUserId());
													lsma_matriculaActo.setIdUsuarioModificacion(getUserId());
													lsma_matriculaActo.setIpCreacion(getLocalIpAddress());
													lsma_matriculaActo.setIpModificacion(getLocalIpAddress());

													lcsma_datosBandejaActosInsertar.add(lsma_matriculaActo);

													lb_ActoAsocido = true;
												}
											}
										}

										if(!lb_ActoAsocido)
										{
											Object[] aoa_messageArgs = new String[1];

											aoa_messageArgs[0] = ls_circuloRegistral + "-" + ll_matricula;

											throw new B2BException(
											    ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS_MATRICULA, aoa_messageArgs
											);
										}
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.MATRICULAS_FORMATO);

							if(
							    (lsm_matricula != null)
								    && ((lcsma_datosBandejaActosInsertar != null) || lb_procesoSinActos)
							)
								lcsm_datosBandejaPrediosInsertar.add(lsm_matricula);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.MATRICULA_TURNO_INVALIDO);
			}

			iasr_antiguoSistemaRemote.antSistemaSalvarAsociarMatricula(
			    lcsma_datosBandejaActosInsertar, lcsm_datosBandejaPrediosInsertar, getUserId(), getLocalIpAddress(),
			    getRemoteIpAddress(), getIdTurnoHistoria(), getObservaciones()
			);

			addMessage(MessagesKeys.MATRICULAS_ASOCIADAS_EXITO);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);

			setProcesoTerminadoAsociarMatricula(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAsociar", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de salvar asociar individual.
	 *
	 * @param acdcpc_data correspondiente al valor del tipo de objeto Collection<DataConsultaPorCriterio>
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean salvarAsociarIndividual(Collection<DataConsultaPorCriterio> acdcpc_data)
	    throws B2BException
	{
		boolean lb_continuarProceso;
		boolean lb_return;

		lb_continuarProceso     = false;
		lb_return               = false;

		if(CollectionUtils.isValidCollection(acdcpc_data))
		{
			for(DataConsultaPorCriterio ldcpc_iterador : acdcpc_data)
			{
				if(ldcpc_iterador != null)
				{
					if(ldcpc_iterador.isAsociarMatricula())
						lb_continuarProceso = true;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);
			}
		}
		else
			throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_MATRICULA_ANT_SIS);

		if(lb_continuarProceso)
		{
			if(!isEtapa101())
				iasr_antiguoSistemaRemote.antSistemaSalvarAsociarMatriculaIndividual(
				    acdcpc_data, getUserId(), getRemoteIpAddress(), getIdTurnoHistoria(), getObservaciones()
				);
			else
			{
				Map<String, byte[]> lmlb_archivos;
				DocumentosSalida    ld_documento;
				String              ls_observaciones;

				lmlb_archivos        = getBytesCargados();
				ld_documento         = new DocumentosSalida();
				ls_observaciones     = getObservaciones();

				ld_documento.setIdTurnoHistoria(NumericUtils.getInteger(getIdTurnoHistoria()));
				ld_documento.setIdUsuarioCreacion(getUserId());

				if(isInformeDeBusquedaAntSistema())
				{
					if(!CollectionUtils.isValidCollection(lmlb_archivos))
						throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);
				}

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

				iasr_antiguoSistemaRemote.antSistemaSalvarAsociarMatriculaIndividual101(
				    lmlb_archivos, ld_documento, acdcpc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
				    ls_observaciones, getIdTurnoHistoria()
				);

				lb_return = true;
			}

			lb_return = true;
		}
		else
		{
			if(isEtapa101())
			{
				Map<String, byte[]> lmlb_archivos;
				DocumentosSalida    ld_documento;
				String              ls_observaciones;

				lmlb_archivos        = getBytesCargados();
				ld_documento         = new DocumentosSalida();
				ls_observaciones     = getObservaciones();

				ld_documento.setIdTurnoHistoria(NumericUtils.getInteger(getIdTurnoHistoria()));
				ld_documento.setIdUsuarioCreacion(getUserId());

				if(isInformeDeBusquedaAntSistema())
				{
					if(!StringUtils.isValidString(ls_observaciones))
						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

					if(!CollectionUtils.isValidCollection(lmlb_archivos))
						throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);

					iasr_antiguoSistemaRemote.antsistemaSalvarInformeBusqueda(
					    lmlb_archivos, ld_documento, getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
					    getObservaciones(), getIdTurnoHistoria()
					);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA);
		}

		return lb_return;
	}

	/**
	 * Método encargado de salvar cabida Y linderos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarCabidaYLinderos()
	    throws B2BException
	{
		try
		{
			FacesContext             lfc_context;
			AccPredioRegistro        lapr_pr;
			CabidaLinderos           lcl_cabidaLinderos;
			Complementacion          lc_complementacion;
			AccComplementacionPredio lacp_complementacionPredio;
			AccLinderoPredio         llp_linderoPredio;
			ComplementacionPredio    lcp_complementacionPredio;
			DatosAntSistema          datosAntSistema;
			String                   ls_tipoComplementacion;
			boolean                  lb_focus;
			boolean                  lb_accionNueva;
			String                   ls_idFormulario;

			lfc_context                    = FacesContext.getCurrentInstance();
			lapr_pr                        = getAccPredioRegistro();
			lcl_cabidaLinderos             = getCabidaLinderos();
			datosAntSistema                = getDatosAntiguoSistema();
			ls_tipoComplementacion         = lcl_cabidaLinderos.getTipoComplementacion();
			lacp_complementacionPredio     = new AccComplementacionPredio();
			llp_linderoPredio              = new AccLinderoPredio();
			lc_complementacion             = lcl_cabidaLinderos.getComplementacion();
			lcp_complementacionPredio      = lcl_cabidaLinderos.getComplementacionPredio();
			lb_focus                       = true;
			lb_accionNueva                 = false;
			ls_idFormulario                = getFormulario();

			if(
			    !validateStyles(
				        ls_idFormulario + ":idITALinderos", lfc_context, lcl_cabidaLinderos.getLinderos(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idcomplementacionComplementacion", lfc_context,
				        lc_complementacion.getComplementacion(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);

			llp_linderoPredio.setLindero(lcl_cabidaLinderos.getLinderos());
			llp_linderoPredio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			llp_linderoPredio.setIdUsuarioCreacion(getUserId());
			llp_linderoPredio.setIpCreacion(getLocalIpAddress());
			llp_linderoPredio.setIdUsuarioModificacion(getUserId());
			llp_linderoPredio.setIpModificacion(getLocalIpAddress());
			llp_linderoPredio.setIdCirculo(lapr_pr.getIdCirculo());
			llp_linderoPredio.setIdMatricula(lapr_pr.getIdMatricula());
			llp_linderoPredio.setIdTurno(lapr_pr.getIdTurno());

			lacp_complementacionPredio.setComplementacion(lcl_cabidaLinderos.getComplementacion().getComplementacion());
			lacp_complementacionPredio.setIdUsuarioCreacion(getUserId());
			lacp_complementacionPredio.setIpCreacion(getLocalIpAddress());
			lacp_complementacionPredio.setIdUsuarioModificacion(getUserId());
			lacp_complementacionPredio.setIpModificacion(getLocalIpAddress());
			lacp_complementacionPredio.setIdTurno(lapr_pr.getIdTurno());
			lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

			if(StringUtils.isValidString(ls_tipoComplementacion))
			{
				if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
					lb_accionNueva = true;
				else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.COPIAR))
				{
					if(lc_complementacion.isCopiarEditar())
						lb_accionNueva = true;
				}

				if(lb_accionNueva)
				{
					lcp_complementacionPredio.setComplementacion(lc_complementacion.getComplementacion());
					lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());
					lcp_complementacionPredio.setIpCreacion(getLocalIpAddress());
				}

				if(datosAntSistema != null)
					lcp_complementacionPredio.setIdDatosAntSistema(datosAntSistema.getIdDatosAntSistema());
			}
			else
				throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);

			llp_linderoPredio.setDigitadorMasivo(false);

			iasr_antiguoSistemaRemote.salvarCabidaYLinderos(
			    llp_linderoPredio, lacp_complementacionPredio, lb_accionNueva, lcp_complementacionPredio, getUserId(),
			    getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarCabidaYLinderos", lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método encargado de salvar y crear la anotación.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean salvarCrear()
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		if(!isMostrarBandeja())
			throw new B2BException(ErrorKeys.ERROR_CONNTIUAR_PROCESO_MAT_ACT);
		else
		{
			Collection<AccPredioRegistro> lcapr_capr;

			lcapr_capr = getDatosTurnoMatricula();

			if(CollectionUtils.isValidCollection(lcapr_capr))
			{
				for(AccPredioRegistro lapr_actual : lcapr_capr)
				{
					if(lapr_actual != null)
					{
						String ls_estadograbacion;

						ls_estadograbacion = lapr_actual.getEstadoGrabacion();

						if(StringUtils.isValidString(ls_estadograbacion))
						{
							if(!ls_estadograbacion.equalsIgnoreCase(EstadoCommon.ESTADO_ANOTACIONES))
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = lapr_actual.getIdCirculo() + "-" + lapr_actual.getIdMatricula();
								throw new B2BException(
								    ErrorKeys.DEBE_SELECCIONAR_ESTADO_GRABACION_PREDIO, aoa_messageArgs
								);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_DILIGENCIAR_MATRICULA_ANT_SIS);

			Map<String, Boolean> lhm_documentosSalidaCrearMatricula;

			lhm_documentosSalidaCrearMatricula = iasr_antiguoSistemaRemote.validarDocuentosCrearMatricula(
				    lcapr_capr, getIdTurno()
				);

			if(CollectionUtils.isValidCollection(lhm_documentosSalidaCrearMatricula))
			{
				for(Map.Entry<String, Boolean> lhm_iterador : lhm_documentosSalidaCrearMatricula.entrySet())
				{
					boolean lb_validar;

					lb_validar = BooleanUtils.getBooleanValue(lhm_iterador.getValue());

					if(!lb_validar)
					{
						Object[] aoa_messageArgs = new String[1];
						String   ls_matricula;

						ls_matricula             = lhm_iterador.getKey();
						aoa_messageArgs[0]       = ls_matricula;

						throw new B2BException(ErrorKeys.ERROR_DEVOLUCION_APROBADOR_MATRICULA, aoa_messageArgs);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DEVOLUCION_APROBADOR_MATRICULAS);

			if(!isEtapa101())
				iasr_antiguoSistemaRemote.antSistemaSalvarCrear(
				    lcapr_capr, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), getIdTurnoHistoria(),
				    getObservaciones()
				);
			else
			{
				Map<String, byte[]> lmsb_archivos;
				DocumentosSalida    ld_documento;

				lmsb_archivos     = getBytesCargados();
				ld_documento      = new DocumentosSalida();

				ld_documento.setIdTurnoHistoria(NumericUtils.getInteger(getIdTurnoHistoria()));
				ld_documento.setIdUsuarioCreacion(getUserId());

				if(isInformeDeBusquedaAntSistema())
				{
					if(!CollectionUtils.isValidCollection(lmsb_archivos))
						throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);
				}

				iasr_antiguoSistemaRemote.antSistemaSalvarCrear101(
				    lmsb_archivos, ld_documento, lcapr_capr, getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
				    getObservaciones(), getIdTurnoHistoria()
				);
			}

			lb_return = true;
		}

		return lb_return;
	}

	/**
	 * Método encargado de salvar datos basicos.
	 *
	 * @param ab_grabar correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarDatosBasicos(boolean ab_grabar)
	    throws B2BException
	{
		try
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				validarPanelDatosBasicosUbicacion();
				validarPanelDatosBasicosApertura();

				boolean lb_creadMatricula;

				lb_creadMatricula = false;

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

					if(StringUtils.isValidString(ls_idTurno))
					{
						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = ier_entregaRemote.findTurnoById(lt_turno, getUserId());

						if(lt_turno != null)
							ldb_datosBasicos.setTurno(lt_turno);
						else
							ldb_datosBasicos.getTurno().setIdTurno(ls_idTurno);
					}
				}

				AccPredioRegistro lapr_predioRegistro;

				lapr_predioRegistro = getAccPredioRegistro();

				if((lapr_predioRegistro != null) && !lapr_predioRegistro.isValidDummy())
					lb_creadMatricula = true;

				ldb_datosBasicos.setIdDatosAntSistema(getIdDatosAntSistemaActual());

				if(ab_grabar)
				{
					ldb_datosBasicos = igr_grabacionRemote.generarPredio(
						    ldb_datosBasicos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(
					    (ldb_datosBasicos != null)
						    && !StringUtils.isValidString(ldb_datosBasicos.getIdDatosAntSistema())
					)
					{
						DatosAntSistema ldas_datosAntSistema;

						ldas_datosAntSistema = ldb_datosBasicos.getDatosAntSistema();

						if(ldas_datosAntSistema != null)
							ldb_datosBasicos.setIdDatosAntSistema(ldas_datosAntSistema.getIdDatosAntSistema());
					}
				}

				ldb_datosBasicos = iasr_antiguoSistemaRemote.salvarDatosBasicos(
					    ldb_datosBasicos, getUserId(), getRemoteIpAddress()
					);

				if(ldb_datosBasicos != null)
				{
					setAccPredioRegistro(ldb_datosBasicos.getAccPredioRegistro());
					setDatosBasicos(ldb_datosBasicos);

					if(lb_creadMatricula || ldb_datosBasicos.isMatriculaGrabada())
					{
						PrimeFaces.current().ajax().update("idMatriculaExitosa");
						PrimeFaces.current().executeScript("PF('idMatriculaExitosa').show();");
					}

					setCrearMatriculasExistentes(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDatosBasicos", lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método encargado de salvar los datos de dirección predio.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DireccionDelPredio> salvarDireccionPredio()
	    throws B2BException
	{
		try
		{
			validarDirecciones(true);

			Collection<DireccionDelPredio> lcddp_temp;

			lcddp_temp = iasr_antiguoSistemaRemote.salvarDireccionPredio(
				    getDireccionesPredio(), getIdTurnoHistoria(), getUserId(), getAccPredioRegistro(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);

			setDireccionesPredio(lcddp_temp);

			validarDirecciones(false);

			return lcddp_temp;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarDireccionPredio", lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método encargado de salvar linderos complementacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarLinderosComplementacion()
	    throws B2BException
	{
		ComplementacionCalificacion lcc_complementacionCalificacion;
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
		RegistroCalificacion        lorc_dataFinal;

		llrc_linderoRegistroCalificacion     = getLinderoRegistroCalificacion();
		lorc_dataFinal                       = new RegistroCalificacion();

		validarPanelLinderos();
		lcc_complementacionCalificacion = validarPanelComplementacion();

		lorc_dataFinal.setComplementacionCalificacion(lcc_complementacionCalificacion);

		if(llrc_linderoRegistroCalificacion != null)
		{
			CabidaLinderos lcl_linderos;

			lcl_linderos = getCabidaLinderos();

			if(lcl_linderos != null)
			{
				AccPredioRegistro            lapr_predio;
				Collection<AccLinderoPredio> lcalp_accLinderoPredios;
				Collection<AccLinderoPredio> lcalp_return;
				String                       ls_idTurno;
				String                       ls_lindero;

				lapr_predio                 = getAccPredioRegistro();
				lcalp_accLinderoPredios     = llrc_linderoRegistroCalificacion.getLinderoPredios();
				lcalp_return                = new ArrayList<AccLinderoPredio>();
				ls_idTurno                  = getIdTurno();
				ls_lindero                  = lcl_linderos.getLinderos();

				llrc_linderoRegistroCalificacion.setLindero(ls_lindero);

				if(!StringUtils.isValidString(ls_idTurno) && (lapr_predio != null))
					ls_idTurno = lapr_predio.getIdTurno();

				if(CollectionUtils.isValidCollection(lcalp_accLinderoPredios))
				{
					for(AccLinderoPredio alp_linderoPredio : lcalp_accLinderoPredios)
					{
						Long ll_idMatricula;

						ll_idMatricula = alp_linderoPredio.getIdMatricula();

						if(!NumericUtils.isValidLong(ll_idMatricula, 1L) && (lapr_predio != null))
							alp_linderoPredio.setIdMatricula(lapr_predio.getIdMatricula());

						alp_linderoPredio.setIdTurno(ls_idTurno);
						alp_linderoPredio.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						alp_linderoPredio.setLindero(ls_lindero);
						alp_linderoPredio.setIdUsuarioCreacion(getUserId());
						alp_linderoPredio.setIpCreacion(getRemoteIpAddress());

						lcalp_return.add(alp_linderoPredio);
					}
				}
				else
				{
					AccLinderoPredio lalp_lindero;

					lalp_lindero = new AccLinderoPredio();

					if(lapr_predio != null)
					{
						lalp_lindero.setIdMatricula(lapr_predio.getIdMatricula());
						lalp_lindero.setIdCirculo(lapr_predio.getIdCirculo());
					}

					lalp_lindero.setIdTurno(ls_idTurno);
					lalp_lindero.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					lalp_lindero.setLindero(ls_lindero);
					lalp_lindero.setIdUsuarioCreacion(getUserId());
					lalp_lindero.setIpCreacion(getRemoteIpAddress());

					lcalp_return.add(lalp_lindero);
				}

				llrc_linderoRegistroCalificacion.setLinderoPredios(lcalp_return);
			}

			lorc_dataFinal.setLinderoCargado(true);
			llrc_linderoRegistroCalificacion.setNuevoLindero(false);
			lorc_dataFinal.setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);

		lorc_dataFinal.setProcesoAValidar(IdentificadoresCommon.ID_T_COMPLEMENTACION);

		if(lorc_dataFinal != null)
		{
			AccPredioRegistro lapr_predio;
			String            ls_idTurno;

			lapr_predio     = getAccPredioRegistro();
			ls_idTurno      = getIdTurno();

			if(lapr_predio != null)
			{
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_idCirculo       = lapr_predio.getIdCirculo();
				ll_idMatricula     = lapr_predio.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
					lorc_dataFinal.setIdCirculoMatriz(ls_idCirculo + "-" + ll_idMatricula);

				if(!StringUtils.isValidString(ls_idTurno))
					ls_idTurno = lapr_predio.getIdTurno();
			}

			lorc_dataFinal.setTurno(ls_idTurno);
			lorc_dataFinal.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lorc_dataFinal.setDatosValidosAntSistema(true);
			lorc_dataFinal.setIdDatosAntSistema(getIdDatosAntSistemaActual());

			irr_calificacionRemote.guardarInfoRegistro(
			    lorc_dataFinal, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
	}

	/**
	 * Método encargado de guardar la respuesta de la pregunta para etapa 100.
	 */
	public void salvarPregunta()
	{
		try
		{
			String ls_tipoConsulta;

			ls_tipoConsulta = getTipoConsulta();

			if(StringUtils.isValidString(ls_tipoConsulta))
			{
				if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.A))
				{
					if(!isDatosAntSistemaIguales())
					{
						DatosAntSistema ldas_temp;

						ldas_temp = getConsultaDatosAntSistema();

						validarDatosAntSistema(ldas_temp);

						ldas_temp.setIdUsuarioCreacion(getUserId());
						ldas_temp.setIpCreacion(getLocalIpAddress());

						iasr_antiguoSistemaRemote.insertUpdateDatosAntSistemaSolicitud(
						    ldas_temp, getIdTurnoHistoria(), getObservaciones(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);
					}
				}
				else if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.D))
					validarDatosDocumento(is_idFormulario);

				setActualizarDatosAntSistema(true);
				accionNuevo(false);

				setMostrarBandeja(true);
				setMostrarAtrasCrearGrabar(false);
				setOcultarSiguienteCrearGrabar(false);
				setMostrar(false);

				PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarPregunta", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de guardar la respuesta de la pregunta para etapa 101.
	 */
	public void salvarPregunta101()
	{
		try
		{
			String ls_tipoConsulta;

			ls_tipoConsulta = getTipoConsulta();

			if(StringUtils.isValidString(ls_tipoConsulta))
			{
				if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.A))
				{
					DatosAntSistema ldas_temp;

					ldas_temp = getConsultaDatosAntSistema();

					validarDatosAntSistema101(ldas_temp);

					ldas_temp.setIdUsuarioCreacion(getUserId());
					ldas_temp.setIpCreacion(getLocalIpAddress());

					iasr_antiguoSistemaRemote.antSistemaSalvarActualizar101(
					    ldas_temp, getIdTurnoHistoria(), getObservaciones(), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);
				}
				else if(ls_tipoConsulta.equalsIgnoreCase(EstadoCommon.D))
					validarDatosDocumento(is_idFormulario);

				setActualizarDatosAntSistema(true);
				accionNuevo(false);

				setMostrarBandeja(true);
				setMostrarAtrasCrearGrabar(false);
				setOcultarSiguienteCrearGrabar(false);
				setMostrar(false);

				setInformeDeBusquedaAntSistema(false);

				PrimeFaces.current().ajax().update("idFormAntSistema:idProcesoAntiguoSistema");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarPregunta101", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cargar los datos de tipo de oficina.
	 */
	public void tipoOficina()
	{
		DatosBasicos ld_datosBasicos;
		ld_datosBasicos = getDatosBasicos();

		Apertura la_apertura;
		la_apertura = ld_datosBasicos.getApertura();

		la_apertura.setIdPais(null);
		la_apertura.setIdDepartamento(null);
		la_apertura.setIdMunicipio(null);
		findTipoEntidadApertura();
		findTipoEntidadAS();
		findPaises();
		findDepartamentos();
		findMunicipioApertura();
		getOficinaOrigen();
	}

	/**
	 * Método encargado de consultar los valores para tipo de oficina.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void tipoOficina(boolean ab_accion)
	    throws B2BException
	{
		if(ab_accion)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_tipoOficina;
					ls_tipoOficina = loo_oficinaOrigen.getIdTipoOficina();

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

							loo_oficinaOrigen.setIdTipoEntidad(lte_te.getIdTipoEntidad());
						}

						loo_oficinaOrigen.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						loo_oficinaOrigen.setIdDepartamento(null);
						loo_oficinaOrigen.setIdMunicipio(null);
					}
				}
			}
		}

		findPaises();
		findDepartamentos(ab_accion);
		findMunicipios(ab_accion);
		getOficinaOrigen(ab_accion);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_turnoHistoria correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public void validarActo0463()
	    throws B2BException
	{
		irr_calificacionRemote.validarActo0463(getMatriculasValidacion());
	}

	/**
	 * Método encargado de validar si un acto es de ejecutoria.
	 *
	 * @param ad_docAntSis de ad doc ant sis
	 */
	public void validarActoEjecutoria(Documento ad_docAntSis)
	{
		try
		{
			String  ls_idTipoDoc;
			boolean lb_isActoEjecutoria;
			lb_isActoEjecutoria = false;

			if(ad_docAntSis != null)
			{
				ls_idTipoDoc = ad_docAntSis.getIdTipoDocumento();

				if(StringUtils.isValidString(ls_idTipoDoc))
					lb_isActoEjecutoria = iasr_antiguoSistemaRemote.validarActoEjecutoria(ls_idTipoDoc);

				setActoConEjecutoria(lb_isActoEjecutoria);
				PrimeFaces.current().ajax().update("fRegistro:idCalFechaEjecutoria");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de validar los datos a asociar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarAsociar()
	    throws B2BException
	{
		try
		{
			Collection<PredioActoIU> lcpaui_bandeja;

			lcpaui_bandeja = getActosAsociadosGeneral();

			if(CollectionUtils.isValidCollection(lcpaui_bandeja))
			{
				if(lcpaui_bandeja.size() > 1)
					throw new B2BException(ErrorKeys.ERROR_LIMITE_MATRICULAS_ASOCIAR);

				PrimeFaces.current().executeScript("PF('dlgConfirmacionAsociar').show();");
				PrimeFaces.current().ajax().update("dlgConfirmacionAsociar");
			}
			else
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTOS_ASOCIADOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarAsociar", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de validar el cierre de folio para la anotación.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCierreFolioAnotacion(Anotacion aa_anotacion)
	    throws B2BException
	{
		if(aa_anotacion != null)
		{
			try
			{
				if(aa_anotacion.isCierreFolio())
				{
					String ls_motivo;

					ls_motivo = aa_anotacion.getMotivoCambioEstado();

					if(!StringUtils.isValidString(ls_motivo))
						throw new B2BException(ErrorKeys.ERROR_MOTIVO_CAMBIO_ESTADO);
					else
					{
						if(
						    !ls_motivo.equalsIgnoreCase(EstadoCommon.N)
							    && !StringUtils.isValidString(aa_anotacion.getJustificacionCambio())
						)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarCierreFolioAnotacion", lb2be_e);
				throw lb2be_e;
			}
		}
	}

	/**
	 * Método encargado de validar los datos de complementación anotación.
	 */
	public void validarConstruir()
	{
		validarConstruir(false);
	}

	/**
	 * Método encargado de validar los datos de complementación anotación.
	 *
	 * @param ab_eliminar correspondiente al valor del tipo de objeto boolean
	 */
	public void validarConstruir(boolean ab_eliminar)
	{
		try
		{
			Collection<ComplementacionAnotacion> lc_anotaciones;

			lc_anotaciones = getComplementacionAnotacion();

			if(CollectionUtils.isValidCollection(lc_anotaciones))
			{
				boolean       lb_valido;
				StringBuilder lsb_complementacion;

				lb_valido               = false;
				lsb_complementacion     = new StringBuilder();

				for(ComplementacionAnotacion anotacionActual : lc_anotaciones)
				{
					if(anotacionActual.isSeleccionado())
					{
						lb_valido = true;

						String ls_complementacion;
						ls_complementacion = anotacionActual.getComplementacion();

						if(StringUtils.isValidString(ls_complementacion))
							lsb_complementacion.append(ls_complementacion + " -- ");
					}
				}

				if(!lb_valido && !ab_eliminar)
					throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_UNA_ANOTACION);

				ComplementacionCalificacion lcc_complementacionCalificacion;
				lcc_complementacionCalificacion = getComplementacionCalificacion();

				if(lcc_complementacionCalificacion != null)
				{
					ComplementacionPredio lcp_complementacionPredio;
					lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

					if(lcp_complementacionPredio != null)
						lcp_complementacionPredio.setComplementacion(lsb_complementacion.toString());
				}
			}
			else if(!ab_eliminar)
				throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_UNA_ANOTACION);
			else
			{
				ComplementacionCalificacion lcc_complementacionCalificacion;
				lcc_complementacionCalificacion = getComplementacionCalificacion();

				if(lcc_complementacionCalificacion != null)
				{
					ComplementacionPredio lcp_complementacionPredio;
					lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

					if(lcp_complementacionPredio != null)
						lcp_complementacionPredio.setComplementacion(null);
				}
			}

			if(ab_eliminar)
				setComplementacionSinConstruir(true);
			else
				setComplementacionSinConstruir(false);
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de validar los datos a copiar de una matricula.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCopiarDeUnaMatricula()
	    throws B2BException
	{
		validarCopiarDeUnaMatricula(false, false);
	}

	/**
	 * Método encargado de validar los datos a copiar de una matricula.
	 *
	 * @param ab_desdeDigitadorMasivo correspondiente al valor del tipo de objeto boolean
	 * @param ab_desdeAntSistema correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCopiarDeUnaMatricula(boolean ab_desdeDigitadorMasivo, boolean ab_desdeAntSistema)
	    throws B2BException
	{
		try
		{
			Collection<MatriculaBase>   lcmb_matriculasComplementacion;
			ComplementacionCalificacion lcc_complementacionCalificacion;
			FacesContext                lfc_context;
			ComplementacionPredio       lc_complementacionPredio;

			lcmb_matriculasComplementacion      = null;
			lcc_complementacionCalificacion     = getComplementacionCalificacion();
			lfc_context                         = FacesUtils.getFacesContext();

			if(ab_desdeDigitadorMasivo)
			{
				BeanDetalleRegistroCalificacion lbdrc_bdrc;
				lbdrc_bdrc = (BeanDetalleRegistroCalificacion)lfc_context.getApplication()
						                                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
						    BeanDetalleRegistroCalificacion.class
						);

				if(lbdrc_bdrc != null)
					lcmb_matriculasComplementacion = lbdrc_bdrc.getMatriculasParaGenerarComplementacion();
			}
			else
				lcmb_matriculasComplementacion = getMatriculasParaGenerarComplementacion();

			lc_complementacionPredio = new ComplementacionPredio();

			if(CollectionUtils.isValidCollection(lcmb_matriculasComplementacion))
			{
				StringBuilder lsb_complementacion;

				lsb_complementacion = new StringBuilder();

				for(MatriculaBase lmb_temp : lcmb_matriculasComplementacion)
				{
					if(lmb_temp != null)
					{
						Complementacion lc_complementacion;

						lc_complementacion = new Complementacion();

						lc_complementacion.setIdCirculo(lmb_temp.getIdCirculo());

						lc_complementacion.setIdMatricula(lmb_temp.getIdMatricula());

						lc_complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

						if(lc_complementacionPredio != null)
						{
							lsb_complementacion.append(lc_complementacionPredio.getComplementacion());
							lsb_complementacion.append(" -- ");
						}
					}
				}

				{
					String ls_complementacionFinal;

					ls_complementacionFinal = lsb_complementacion.toString();

					if(lc_complementacionPredio != null)
						lc_complementacionPredio.setComplementacion(ls_complementacionFinal);

					lcc_complementacionCalificacion.setComplementacionPredio(lc_complementacionPredio);
					lcc_complementacionCalificacion.setCopiarEditar(
					    isEditoComplementacion() ? true : (lcmb_matriculasComplementacion.size() > 1)
					);
					setHabilitarComplementacion(false);
				}

				setComplementacionSinConstruir(false);
			}
			else
			{
				lc_complementacionPredio.setComplementacion(null);
				lcc_complementacionCalificacion.setComplementacionPredio(lc_complementacionPredio);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_mensajesCalificacionGrowl);
		}
	}

	/**
	 * Método encargado de validar los datos a copiar de una matricula.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCopiarDeUnaMatriculaData()
	    throws B2BException
	{
		boolean         lb_focus;
		boolean         lb_calificacion;
		CabidaLinderos  cabidaLinderos;
		Complementacion lc_complementacion;
		FacesContext    lfc_context;
		String          ls_idFormulario;

		lb_focus               = false;
		cabidaLinderos         = null;
		lc_complementacion     = null;
		lfc_context            = FacesContext.getCurrentInstance();
		ls_idFormulario        = getFormulario();
		lb_calificacion        = StringUtils.isValidString(ls_idFormulario)
				&& ls_idFormulario.equalsIgnoreCase(is_idFormulario_calificacion);

		try
		{
			cabidaLinderos         = getCabidaLinderos();
			lc_complementacion     = cabidaLinderos.getComplementacion();

			String ls_complementacion;

			if(lc_complementacion != null)
			{
				ComplementacionPredio lcp_complementacionPredio;
				Long                  ll_idMatricula;

				ll_idMatricula     = lc_complementacion.getIdMatricula();
				lb_focus           = validateStyles(
					    ls_idFormulario + ":cabidaLinderosIdMatricula", lfc_context, ll_idMatricula, lb_focus
					);

				if(!NumericUtils.isValidLong(ll_idMatricula))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

				lcp_complementacionPredio = iasr_antiguoSistemaRemote.findComplementacion(lc_complementacion);

				if(lcp_complementacionPredio != null)
				{
					ls_complementacion = lcp_complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
					{
						lc_complementacion.setCopiarEditar(false);
						lc_complementacion.setComplementacion(ls_complementacion);
						cabidaLinderos.setComplementacionPredio(lcp_complementacionPredio);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCopiarDeUnaMatricula", lb2be_e);
			addMessage(lb2be_e);

			if(lb_calificacion)
				PrimeFaces.current().ajax().update(is_mensajesCalificacionGrowl);
			else
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de validar datos ant sistema.
	 *
	 * @param adas_temp correspondiente al valor del tipo de objeto DatosAntSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosAntSistema(DatosAntSistema adas_temp)
	    throws B2BException
	{
		boolean      lb_focus;
		boolean      lb_validarLibro;
		FacesContext lfc_context;
		Long         ll_idLibro;
		String       ls_adquisicion;
		String       ls_idCirculo;

		lb_focus            = true;
		lb_validarLibro     = true;
		lfc_context         = FacesContext.getCurrentInstance();
		ls_adquisicion      = adas_temp.getAdquisicionPredio();
		ll_idLibro          = adas_temp.getIdLibroAntSistema();
		ls_idCirculo        = adas_temp.getIdCirculo();

		try
		{
			if(StringUtils.isValidString(ls_adquisicion))
			{
				if(ls_adquisicion.equalsIgnoreCase("Datos del predio"))
					lb_validarLibro = false;
			}

			lb_focus = validateStyles(
				    ":idFormAntSistema:idSOMCirculoRegistralAntSistema", lfc_context, ls_idCirculo, lb_focus
				);

			if(lb_validarLibro)
				lb_focus = validateStyles(":idFormAntSistema:idSOMLibro", lfc_context, ll_idLibro, lb_focus);

			if(!StringUtils.isValidString(ls_idCirculo))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			if(!NumericUtils.isValidLong(ll_idLibro) && lb_validarLibro)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDatosAntSistema", lb2be_e);
			setDataConsultaPorCriterioDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar datos ant sistema 101.
	 *
	 * @param adas_temp correspondiente al valor del tipo de objeto DatosAntSistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosAntSistema101(DatosAntSistema adas_temp)
	    throws B2BException
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		Long         ll_idLibro;
		String       ls_idCirculo;
		String       ls_tipoPredio;

		lb_focus          = true;
		lfc_context       = FacesContext.getCurrentInstance();
		ll_idLibro        = adas_temp.getIdLibroAntSistema();
		ls_idCirculo      = adas_temp.getIdCirculo();
		ls_tipoPredio     = adas_temp.getIdTipoPredio();

		adas_temp.setAdquisicionPredio("Antes de 1978");

		setDatosAntiguoSistema(adas_temp);

		try
		{
			lb_focus     = validateStyles(
				    ":idFormAntSistema:idSOMCirculoRegistralAntSistema", lfc_context, ls_idCirculo, lb_focus
				);

			lb_focus     = validateStyles(":idFormAntSistema:idSOMLibro", lfc_context, ll_idLibro, lb_focus);

			lb_focus = validateStyles(
				    ":idFormAntSistema:idSOMTipoPredioAntSistema", lfc_context, ls_tipoPredio, lb_focus
				);

			if(!StringUtils.isValidString(ls_idCirculo))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			if(!NumericUtils.isValidLong(ll_idLibro))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);

			if(!StringUtils.isValidString(ls_tipoPredio))
				throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDatosAntSistema101", lb2be_e);
			setDataConsultaPorCriterioDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de verificar la información ingresada en los campos del formulario y cambia el estilo de los campos
	 * dependiendo del resultado de la validación.
	 *
	 * @param as_idForm Id del formulario desde el cual se ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosDocumento(String as_idForm)
	    throws B2BException
	{
		validarDatosDocumento(as_idForm, false);
	}

	/**
	 * Método encargado de verificar la información ingresada en los campos del formulario y cambia el estilo de los campos
	 * dependiendo del resultado de la validación.
	 *
	 * @param as_idForm Id del formulario desde el cual se ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosDocumento(String as_idForm, boolean ab_consulta)
	    throws B2BException
	{
		boolean                lb_focus;
		ConsultaDatosDocumento lcdd_temp;
		Documento              ld_documento;
		FacesContext           lfc_context;
		OficinaOrigen          loo_oficinaOrigen;
		String                 ls_dosPuntos;

		lb_focus              = true;
		lcdd_temp             = getConsultaDatosDocumento();
		ld_documento          = lcdd_temp.getDocumento();
		lfc_context           = FacesContext.getCurrentInstance();
		loo_oficinaOrigen     = lcdd_temp.getOficinaOrigen();
		ls_dosPuntos          = ":";

		try
		{
			CirculoRegistral lcr_circulo;
			lcr_circulo = lcdd_temp.getCirculoRegistral();

			if(lcr_circulo == null)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			String ls_idCirculoValidator;
			ls_idCirculoValidator     = lcr_circulo.getIdCirculo();

			lb_focus = validateStyles(
				    ls_dosPuntos + as_idForm + ":circuloRegistralDocumento", lfc_context, ls_idCirculoValidator,
				    lb_focus
				);

			String ls_tipoDoc;
			ls_tipoDoc     = ld_documento.getIdTipoDocumento();

			lb_focus = validateStyles(
				    ls_dosPuntos + as_idForm + ":idSOMEscrituraDocumento", lfc_context, ls_tipoDoc, lb_focus
				);

			String ls_numDoc;
			ls_numDoc     = ld_documento.getNumero();

			lb_focus = validateStyles(
				    ls_dosPuntos + as_idForm + ":idItDocuActoDocumento", lfc_context, ls_numDoc, lb_focus
				);

			Date ld_fechaDocumento;
			ld_fechaDocumento     = ld_documento.getFechaDocumento();

			lb_focus = validateStyles(
				    ls_dosPuntos + as_idForm + ":idCalFechaDocDocumento", lfc_context, ld_fechaDocumento, lb_focus
				);

			if(!ab_consulta)
			{
				String ls_idTipoOficina;
				ls_idTipoOficina     = loo_oficinaOrigen.getIdTipoOficina();

				lb_focus = validateStyles(
					    ls_dosPuntos + as_idForm + ":idSOMTipoOficinaDocumento", lfc_context, ls_idTipoOficina, lb_focus
					);

				String ls_idPais;
				ls_idPais     = loo_oficinaOrigen.getIdPais();

				lb_focus = validateStyles(
					    ls_dosPuntos + as_idForm + ":idPaisDocumento1", lfc_context, ls_idPais, lb_focus
					);

				String ls_idDepartamento;
				ls_idDepartamento     = loo_oficinaOrigen.getIdDepartamento();

				lb_focus = validateStyles(
					    ls_dosPuntos + as_idForm + ":idSOMDepartamentoDocumento", lfc_context, ls_idDepartamento,
					    lb_focus
					);

				String ls_idMunicipio;
				ls_idMunicipio     = loo_oficinaOrigen.getIdMunicipio();

				lb_focus = validateStyles(
					    ls_dosPuntos + as_idForm + ":idSOMMunicipioDocumento", lfc_context, ls_idMunicipio, lb_focus
					);

				String ls_idOficinaOrigen;
				ls_idOficinaOrigen     = loo_oficinaOrigen.getIdOficinaOrigen();

				lb_focus = validateStyles(
					    ls_dosPuntos + as_idForm + ":idSOMOficinaOrigenDocumento", lfc_context, ls_idOficinaOrigen,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoOficina))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

				if(!StringUtils.isValidString(ls_idPais))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

				if(!StringUtils.isValidString(ls_idDepartamento))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

				if(!StringUtils.isValidString(ls_idMunicipio))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

				if(!StringUtils.isValidString(ls_idOficinaOrigen))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
			}

			if(!StringUtils.isValidString(ls_idCirculoValidator))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			if(!StringUtils.isValidString(ls_tipoDoc))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			if(!StringUtils.isValidString(ls_numDoc))
				throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

			if(ld_fechaDocumento == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDatosDocumento", lb2be_e);
			setDataConsultaPorCriterioDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar fecha documento radicacion.
	 *
	 * @param lb_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarFechaDocumentoRadicacion(boolean lb_accion)
	    throws B2BException
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;
			Documento       ld_documento;

			lap_anotacionPredio     = getAnotacionPredio();
			ld_documento            = getDocumento();

			if((ld_documento != null) && (lap_anotacionPredio != null))
			{
				Date ld_fechaAnotacion;
				Date ld_fechaDocumento;
				Date ld_fechaActual;

				ld_fechaAnotacion     = lap_anotacionPredio.getFechaRadicacion();
				ld_fechaDocumento     = ld_documento.getFechaDocumento();
				ld_fechaActual        = new Date();

				if((ld_fechaDocumento != null) && (ld_fechaAnotacion != null))
				{
					if(ld_fechaDocumento.compareTo(ld_fechaAnotacion) > 0)
						throw new B2BException(ErrorKeys.ERROR_FECHA_DOC_RADICACION);

					if(ld_fechaAnotacion.after(ld_fechaActual))
						throw new B2BException(ErrorKeys.ERROR_FECHA_ANOTACION_SUPERIOR_ACTUAL);
				}
				else
				{
					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

					if(ld_fechaAnotacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
				}
			}
			else
			{
				if(ld_documento == null)
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

				if(lap_anotacionPredio == null)
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarFechaDocumentoRadicacion", lb2be_e);

			if(lb_accion)
			{
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
			}
			else
				throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar informe de busqueda.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarInformeDeBusqueda()
	    throws B2BException
	{
		try
		{
			Map<String, byte[]> lmsb_archivos;

			lmsb_archivos = getBytesCargados();

			if(!CollectionUtils.isValidCollection(lmsb_archivos) && !StringUtils.isValidString(getObservaciones()))
				throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF_Y_OBSERVACIONES);
			else
			{
				PrimeFaces.current().executeScript("PF('dlgConfirmacionInforme').show();");
				PrimeFaces.current().ajax().update("dlgConfirmacionInforme");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarInformeDeBusqueda", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de verificar si el turno en proceso proviene de la etapa de aprobación de antiguo sistema.
	 */
	public void validarSiEsDevolucion()
	{
		try
		{
			setDevolucionEtapa110(
			    iasr_antiguoSistemaRemote.validarSiEsDevolucion(
			        getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSiEsDevolucion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de verificar si ya todos los predios asociados al turno fueron revisados.
	 */
	public void verificarRevisionPredios()
	{
		Collection<DatosAntSistema> ldas_datosAnt;

		ldas_datosAnt = getDatosAntSistema();

		if(CollectionUtils.isValidCollection(ldas_datosAnt))
		{
			boolean            lb_estadoRevision;
			boolean            lb_definitivo;
			Collection<String> lcs_nombrePrediosSinRevision;

			lb_estadoRevision                = true;
			lb_definitivo                    = true;
			lcs_nombrePrediosSinRevision     = new LinkedList<String>();

			for(DatosAntSistema ldas_predio : ldas_datosAnt)
			{
				if(ldas_predio != null)
				{
					String ls_revisado;

					ls_revisado = StringUtils.getStringNotNull(ldas_predio.getRevisadoAntSistema());

					if(ls_revisado.equals(EstadoCommon.N))
					{
						if(lb_estadoRevision)
							lb_estadoRevision = false;

						lcs_nombrePrediosSinRevision.add(ldas_predio.getNombrePredio());
					}

					if(!ls_revisado.equals(EstadoCommon.D))
						lb_definitivo = false;
				}
			}

			if(!CollectionUtils.isValidCollection(lcs_nombrePrediosSinRevision))
				lcs_nombrePrediosSinRevision = null;

			setNombrePrediosSinRevision(lcs_nombrePrediosSinRevision);
			setRevisionPrediosCompleta(lb_estadoRevision);
			setRevisionDefinitivaCompleta(lb_definitivo);

			if(isEtapa102())
			{
				setRevisionPrediosCompleta(false);
				setRevisionDefinitivaCompleta(false);
			}
		}
	}

	/**
	 * Metodo encargado de recuperar todos los datos de base de datos de la
	 * matricula seleccionada y mostrala al cargar la pagina.
	 *
	 * @param aapr_apr Objeto de tipo AccPredioRegistro que contiene los datos de la matricula a ser cargada
	 * @param ab_wizard Variable de tipo boolean que indica si se debe cargar wizard en pantalla
	 * <code>true</code>, de lo contrario <code>false</code>.
	 */
	protected void cargarMatricula(AccPredioRegistro aapr_apr, boolean ab_wizard)
	{
		try
		{
			if(ab_wizard)
				resetWizard();

			if(aapr_apr != null)
			{
				setMostrar(true);
				setPdfGenerado(false);

				DigitadorAntiguoSistema ldas_das;

				ldas_das = idas_digitadorAntiguoSistemaRemote.findDigitadorAntiguoSistema(
					    aapr_apr, EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA, getUserId()
					);

				if(ldas_das != null)
				{
					setAccPredioRegistro(ldas_das.getAccPredioRegistro());

					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = ldas_das.getDatosbasicos();

						if(ldb_datosBasicos != null)
						{
							UbicacionZonaRegistral luzr_ubicacion;

							luzr_ubicacion = ldb_datosBasicos.getUbicacion();

							if((luzr_ubicacion != null) && !StringUtils.isValidString(getIdPais()))
								setIdPais(luzr_ubicacion.getIdPais());

							{
								AccPredioRegistro lapr_predioRegistro;

								lapr_predioRegistro = getAccPredioRegistro();

								if(lapr_predioRegistro != null)
								{
									PredioRegistro lpr_predio;

									lpr_predio = (ldb_datosBasicos.getPredioRegistro() != null)
										? ldb_datosBasicos.getPredioRegistro() : new PredioRegistro();

									lpr_predio.setIdTipoUsoSuelo(lapr_predioRegistro.getIdTipoUsoSuelo());
									lpr_predio.setIdTipoPredio(lapr_predioRegistro.getIdTipoPredio());

									ldb_datosBasicos.setPredioRegistro(lpr_predio);
								}
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
								String ls_idTipoComplementacion;

								ls_idTipoComplementacion = lacp_cp.getTipoComplementacion();

								if(StringUtils.isValidString(ls_idTipoComplementacion))
								{
									String ls_tipoComplementacion;

									ls_tipoComplementacion = ls_idTipoComplementacion.equalsIgnoreCase(
										    TipoComplementacionCommon.C
										) ? TipoComplementacionCommon.COPIAR : TipoComplementacionCommon.NUEVA;

									lcl_cl.setTipoComplementacion(ls_tipoComplementacion);
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
						}

						{
							AccLinderoPredio lalp_lp;

							lalp_lp = ldas_das.getAccLinderoPredio();

							if(lalp_lp != null)
								lcl_cl.setLinderos(lalp_lp.getLindero());
						}
					}

					setAreaPredio(ldas_das.getAreaPredio());
					setAreaUI(ldas_das.getAreaUI());
					setDireccionesPredio(ldas_das.getDireccionesDelPredio());

					cargarArea();

					{
						Anotacion la_anotacion;

						la_anotacion = ldas_das.getAnotacion();

						if(la_anotacion != null)
						{
							setAnotacion(la_anotacion);
							setDatosAntSistemaAnotacion(la_anotacion.getDatosAntiguoSistema());
							setAnotacionesAgregadas(la_anotacion.getAnotacionesAgregadas());
						}
					}

					setMostrarBandeja(false);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);

			setMostrarAtrasCrearGrabar(false);
			setOcultarSiguienteCrearGrabar(false);
			setProcesoTerminadoCrearGrabar(false);

			setCrearMatriculasExistentes(true);

			setAnotacionPredio(null);
			setDocumento(null);
			setAnotacionCancelacion(null);
			setIntervinientesAgregados(null);
			setMatriculasSegregadas(null);
			setPredioSegregado(null);
			setIdMatricula(null);
			setNaturalezaJuridicaSeleccionada(null);
			setTipoDocInterviniente(null);
			setDocumentoInterviniente(null);
			setIdPersonaSeleccion(null);
			setNumeroAnotacion(null);
			setNumeroOrden(null);
			setListadoIntervinientes(null);
			setAnotacionAgregada(true);
			setDatosAntiguoSistema(null);
			setAnotacion(null);
			setCodigoNaturalezaJuridicaSeleccionada(null);
			setDeshabilitarNumeroAnotacion(false);

			if(isGrabacion())
				cargarTiposOficinaAnotaciones(EstadoCommon.G);
			else
				cargarTiposOficinaAnotaciones(EstadoCommon.D);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarMatricula", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cargar los tipos de oficina que se encuentren relacionadas a un tipo de documento específico.
	 *
	 * @param as_pantalla correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void cargarTiposOficinaAnotacionesPorTipoDoc(String as_pantalla)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_pantalla))
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		Apertura                la_apertura;
		Collection<TipoOficina> lcto_datos;
		Documento               ld_documento;
		boolean                 lb_documentoConsulta;

		la_apertura              = null;
		ld_documento             = null;
		lb_documentoConsulta     = false;

		if(as_pantalla.equalsIgnoreCase(EstadoCommon.D) || as_pantalla.equalsIgnoreCase(EstadoCommon.G))
		{
			DatosBasicos ldb_datosBasicos;

			ldb_datosBasicos = getDatosBasicos();

			if(ldb_datosBasicos != null)
			{
				la_apertura = ldb_datosBasicos.getApertura();

				if(la_apertura != null)
					ld_documento = la_apertura.getDocumento();
			}
		}
		else if(as_pantalla.equalsIgnoreCase(EstadoCommon.A) || as_pantalla.equalsIgnoreCase(EstadoCommon.AG))
			ld_documento = getDocumento();
		else if(as_pantalla.equalsIgnoreCase(EstadoCommon.C))
		{
			ConsultaCriteriosCalificacionDocumento lcccd_consulta;

			lcccd_consulta = getConsultaCriteriosCalificacionDocumento();

			if(lcccd_consulta != null)
				ld_documento = lcccd_consulta.getDocumento();
		}
		else if(as_pantalla.equalsIgnoreCase(EstadoCommon.DE))
		{
			lb_documentoConsulta = true;

			ConsultaDatosDocumento lcdd_consulta;

			lcdd_consulta = getConsultaDatosDocumento();

			if(lcdd_consulta != null)
				ld_documento = lcdd_consulta.getDocumento();
		}
		else if(as_pantalla.equalsIgnoreCase(EstadoCommon.EC))
			ld_documento = getDocumentoDetalleCargado();

		if(
		    (ld_documento != null) && !as_pantalla.equalsIgnoreCase(EstadoCommon.G)
			    && !as_pantalla.equalsIgnoreCase(EstadoCommon.AG)
		)
		{
			lcto_datos = irr_referenceRemote.findTipoOficina(ld_documento);

			if(CollectionUtils.isValidCollection(lcto_datos))
			{
				if(lcto_datos.size() == 1)
				{
					if(la_apertura != null)
					{
						String ls_paisApertura;

						ls_paisApertura = la_apertura.getIdPais();

						if(!StringUtils.isValidString(ls_paisApertura))
							la_apertura.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					}

					for(TipoOficina lto_tmp : lcto_datos)
					{
						if(lto_tmp != null)
						{
							String ls_idTipoOficina;

							ls_idTipoOficina = lto_tmp.getIdTipoOficina();

							if(lb_documentoConsulta)
							{
								ConsultaDatosDocumento lcdd_consulta;

								lcdd_consulta = getConsultaDatosDocumento();

								if(lcdd_consulta != null)
								{
									OficinaOrigen loo_oficina;

									loo_oficina = lcdd_consulta.getOficinaOrigen();

									if(loo_oficina != null)
										loo_oficina.setIdTipoOficina(ls_idTipoOficina);
								}
							}
							else
							{
								ld_documento.setIdTipoOficina(ls_idTipoOficina);
								ld_documento.setTipoEntidad(lto_tmp.getIdTipoEntidad());
							}
						}
					}
				}
				else if(la_apertura != null)
				{
					Iterator<TipoOficina> lito_oficinas;

					lito_oficinas = lcto_datos.iterator();

					if(lito_oficinas.hasNext())
					{
						TipoOficina lto_oficina;

						lto_oficina = lito_oficinas.next();

						if(lto_oficina != null)
						{
							String ls_tipoOficina;

							ls_tipoOficina = lto_oficina.getIdTipoOficina();

							if(as_pantalla.equalsIgnoreCase(EstadoCommon.D))
								la_apertura.setIdTipoOficina(ls_tipoOficina);

							ld_documento.setIdTipoOficina(ls_tipoOficina);
							ld_documento.setTipoEntidad(lto_oficina.getIdTipoEntidad());
							la_apertura.setIdTipoEntidad(lto_oficina.getIdTipoEntidad());
							la_apertura.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						}
					}
				}
				else if(lcto_datos.size() > 1)
				{
					Iterator<TipoOficina> lito_oficinas;

					lito_oficinas = lcto_datos.iterator();

					if(lito_oficinas.hasNext())
					{
						TipoOficina lto_oficina;

						lto_oficina = lito_oficinas.next();

						if(lto_oficina != null)
						{
							String ls_idTipoOficina;

							ls_idTipoOficina = lto_oficina.getIdTipoOficina();

							if(StringUtils.isValidString(ls_idTipoOficina))
							{
								if(
								    lb_documentoConsulta || as_pantalla.equalsIgnoreCase(EstadoCommon.C)
									    || as_pantalla.equalsIgnoreCase(EstadoCommon.A)
								)
								{
									ConsultaDatosDocumento lcdd_consulta;

									lcdd_consulta = getConsultaDatosDocumento();

									if(lcdd_consulta != null)
									{
										OficinaOrigen loo_oficina;

										loo_oficina = lcdd_consulta.getOficinaOrigen();

										if(loo_oficina != null)
										{
											if(
											    as_pantalla.equalsIgnoreCase(EstadoCommon.C)
												    || as_pantalla.equalsIgnoreCase(EstadoCommon.A)
											)
											{
												ld_documento.setIdTipoOficina(ls_idTipoOficina);
												ld_documento.setTipoEntidad(lto_oficina.getIdTipoEntidad());
											}
											else
												loo_oficina.setIdTipoOficina(ls_idTipoOficina);
										}
									}
								}
							}
						}
					}
				}
				else
					ld_documento.setIdTipoOficina(null);

				if(as_pantalla.equalsIgnoreCase(EstadoCommon.EC))
					setTiposOficinaDocDetalleAnt(lcto_datos);
				else
					setTiposOficinaDoc(lcto_datos);
			}
			else
				setTiposOficinaDoc(new ArrayList<TipoOficina>());
		}
		else if(as_pantalla.equalsIgnoreCase(EstadoCommon.AG))
			setTiposOficinaDoc(irr_referenceRemote.findAllTipoOficinaActivo(EstadoCommon.S, false));
	}

	/**
	 * Método encargado de validar las direcciones agregadas.
	 *
	 * @param ab_validar Variable de tipo boolean que identifica si se agregan las direcciones o si se obtienen.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected void validarDirecciones(boolean ab_validar)
	    throws B2BException
	{
		BeanDireccion         lbd_beanDireccion;
		Collection<Direccion> lcd_direcciones;

		lbd_beanDireccion = getBeanDireccion();

		if(ab_validar)
		{
			lcd_direcciones = lbd_beanDireccion.getDireccionesPredio();

			if(CollectionUtils.isValidCollection(lcd_direcciones))
			{
				Collection<DireccionDelPredio> lcddp_direcciones;

				lcddp_direcciones = new ArrayList<DireccionDelPredio>();

				for(Direccion ld_iterador : lcd_direcciones)
				{
					if(ld_iterador != null)
					{
						String ls_idTipoPredio;

						ls_idTipoPredio = ld_iterador.getIdTipoPredio();

						if(!StringUtils.isValidString(ls_idTipoPredio))
							ld_iterador = lbd_beanDireccion.cargarDatosDireccionPredio(getDatosBasicos(), ld_iterador);

						lcddp_direcciones.add(new DireccionDelPredio(ld_iterador, false));
					}
				}

				setDireccionesPredio(lcddp_direcciones);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
		}
		else
		{
			Collection<DireccionDelPredio> lcddp_direcciones;

			lcddp_direcciones     = getDireccionesPredio();
			lcd_direcciones       = new ArrayList<Direccion>();

			if(CollectionUtils.isValidCollection(lcddp_direcciones))
			{
				for(DireccionDelPredio lddp_iterador : lcddp_direcciones)
				{
					if(lddp_iterador != null)
					{
						Direccion ld_direccion;

						ld_direccion     = new Direccion(lddp_iterador.getDireccionPredio());
						ld_direccion     = lbd_beanDireccion.cargarDatosDireccionPredio(
							    lddp_iterador.getDatosAntiguoSistema(), ld_direccion
							);

						lcd_direcciones.add(ld_direccion);
					}
				}
			}

			lbd_beanDireccion.setDireccionesPredio(lcd_direcciones);
		}
	}

	/**
	 * Método encargado de validar los datos del panl de area predio.
	 *
	 * @return devuelve el valor de AccAreaUI
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected AccAreaUI validarPanelAreaPredi()
	    throws B2BException
	{
		AccAreaUI laaui_data;

		laaui_data = getAreaUI();

		if(laaui_data != null)
		{
			Collection<DetalleAreaPredio> lcadap_areasTerreno;

			lcadap_areasTerreno = laaui_data.getAreasTerreno();

			if(CollectionUtils.isValidCollection(lcadap_areasTerreno))
			{
				AccAreaPredio laap_areaPredio;
				boolean       lb_focus;
				FacesContext  lfc_context;
				String        ls_idFormulario;

				laap_areaPredio     = laaui_data.getAreaPredio();
				lb_focus            = false;
				lfc_context         = FacesContext.getCurrentInstance();
				ls_idFormulario     = getFormulario();

				if(laap_areaPredio != null)
				{
					String ls_tipoUso;
					String ls_coeficiente;

					ls_tipoUso         = laap_areaPredio.getTipoSuelo();
					ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
					lb_focus           = validateStyles(
						    ls_idFormulario + ":idSOMtipoUsoSuelo", lfc_context, ls_tipoUso, lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoUso))
						throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

					if(StringUtils.isValidString(ls_coeficiente))
					{
						Double ld_coeficiente;

						ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

						if(NumericUtils.isValidDouble(ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, true))
						{
							laap_areaPredio.setCoeficiente(ld_coeficiente);

							if(ld_coeficiente.compareTo(new Double(100)) > 0)
							{
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITcoeficiente", lfc_context, "", lb_focus
									);

								throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
							}
							else
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITcoeficiente", lfc_context, ls_coeficiente, lb_focus
									);
						}
						else
						{
							lb_focus = validateStyles(ls_idFormulario + ":idITcoeficiente", lfc_context, "", lb_focus);
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

						ls_area = ldap_area.getAreaLectura();

						if(StringUtils.isValidString(ls_area))
						{
							Double ld_area;

							ld_area = NumericUtils.getDoubleWrapper(ls_area);

							if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
							{
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITareaConstruida", lfc_context, ls_area, lb_focus
									);
								ldap_area.setArea(ld_area);
							}
							else
							{
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITareaConstruida", lfc_context, "", lb_focus
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

						ls_area = ldap_area.getAreaLectura();

						if(StringUtils.isValidString(ls_area))
						{
							Double ld_area;

							ld_area = NumericUtils.getDoubleWrapper(ls_area);

							if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
							{
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITareaPrivada", lfc_context, ls_area, lb_focus
									);
								ldap_area.setArea(ld_area);
							}
							else
							{
								lb_focus = validateStyles(
									    ls_idFormulario + ":idITareaPrivada", lfc_context, "", lb_focus
									);
								throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
		}

		return laaui_data;
	}

	/**
	 * Método encargado de validar los datos del panel de complementación.
	 *
	 * @return devuelve el valor de ComplementacionCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected ComplementacionCalificacion validarPanelComplementacion()
	    throws B2BException
	{
		boolean                     lb_focus;
		ComplementacionCalificacion lcc_complementacionCalificacion;
		FacesContext                lfc_context;
		String                      as_idFormulario;

		lb_focus                            = false;
		lcc_complementacionCalificacion     = getComplementacionCalificacion();
		lfc_context                         = FacesUtils.getFacesContext();
		as_idFormulario                     = getFormulario();

		if(lcc_complementacionCalificacion != null)
		{
			ComplementacionPredio lcp_complementacionPredio;

			lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

			if(lcp_complementacionPredio != null)
			{
				String ls_tipoComplementacion;

				ls_tipoComplementacion = lcc_complementacionCalificacion.getTipoComplementacion();

				lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());

				if(StringUtils.isValidString(ls_tipoComplementacion))
				{
					String ls_complementacion;

					ls_complementacion = lcp_complementacionPredio.getComplementacion();

					if(StringUtils.isValidString(ls_complementacion))
					{
						if(ls_complementacion.trim().length() < 100)
						{
							lb_focus = validateStyles(
								    as_idFormulario + ":idcomplementacionComplementacion", lfc_context,
								    IdentificadoresCommon.DATO_INVALIDO, lb_focus
								);

							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
						}
					}
					else
					{
						lb_focus = validateStyles(
							    as_idFormulario + ":idcomplementacionComplementacion", lfc_context, ls_complementacion,
							    lb_focus
							);

						throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
			}
		}

		return lcc_complementacionCalificacion;
	}

	/**
	 * Metodo encargado de validar y de cambiar estilo de borde en campos
	 * obligatorios para el panel de Datos anotación.
	 *
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que
	 * contiene los criterios a validar.
	 * @param ab_salvar correspondiente al valor del tipo de objeto boolean
	 * @param ab_focus Argumento de tipo <code>boolean</code> que indica si se debe
	 * dejar la el foco en un componente indicado.
	 * @param as_idFormulario Argumento de tipo <code>String</code> que contiene el
	 * id del formulario desde donde se realiza la petición.
	 * @param afc_context Argumento de tipo <code>FacesContext</code> que contiene
	 * el contexto de la petición realizada.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	protected void validarPanelDatosAnotacion(
	    AnotacionPredio aap_anotacionPredio, boolean ab_salvar, boolean ab_focus, String as_idFormulario,
	    FacesContext afc_context
	)
	    throws B2BException
	{
		try
		{
			{
				Long   ll_numeroAnotacion;
				String ls_idNumeroAnotacion;

				ll_numeroAnotacion       = getNumeroAnotacion();
				ls_idNumeroAnotacion     = as_idFormulario + ":idItNumeroAnotacionID";

				ab_focus = validateStyles(ls_idNumeroAnotacion, afc_context, ll_numeroAnotacion, ab_focus);

				if(!NumericUtils.isValidLong(ll_numeroAnotacion))
					throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION);

				if(ab_salvar)
				{
					AnotacionPredio lap_anotacionPredio;

					lap_anotacionPredio = new AnotacionPredio();

					lap_anotacionPredio.setIdAnotacion(ll_numeroAnotacion);
					lap_anotacionPredio.setIdCirculo(getIdCirculo());
					lap_anotacionPredio.setIdMatricula(getIdMatricula());
					lap_anotacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

					lap_anotacionPredio = iasr_antiguoSistemaRemote.findByCirculoMatriculaTurnoAnotacion(
						    lap_anotacionPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lap_anotacionPredio != null)
					{
						Object[] loa_object;

						loa_object     = new String[2];

						loa_object[0]     = StringUtils.getString(ll_numeroAnotacion);
						loa_object[1]     = lap_anotacionPredio.getIdTurno();

						RequestContext.getCurrentInstance().execute(
						    "PrimeFaces.focus('" + ls_idNumeroAnotacion + "');"
						);

						throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION_EXISTE_PARA_EL_TURNO, loa_object);
					}
				}
			}

			if(aap_anotacionPredio != null)
			{
				{
					Date ld_fechaRadicacion;
					ld_fechaRadicacion     = aap_anotacionPredio.getFechaRadicacion();

					ab_focus = validateStyles(
						    as_idFormulario + ":idCalFechaAnotacion", afc_context, ld_fechaRadicacion, ab_focus
						);

					if(ld_fechaRadicacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
				}

				{
					String ls_radicacion;
					ls_radicacion     = aap_anotacionPredio.getRadicacion();

					ab_focus = validateStyles(
						    as_idFormulario + ":idItRadicacionID", afc_context, ls_radicacion, ab_focus
						);

					if(!StringUtils.isValidString(ls_radicacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_RADICACION);
				}

				{
					String ls_estadoAnotacion;
					ls_estadoAnotacion     = aap_anotacionPredio.getIdEstadoAnotacion();

					ab_focus = validateStyles(
						    as_idFormulario + ":idSOMEstadoAnotacion", afc_context, ls_estadoAnotacion, ab_focus
						);

					if(!StringUtils.isValidString(ls_estadoAnotacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_ANOTACION);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPanelDatosAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar panel datos basicos apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarPanelDatosBasicosApertura()
	    throws B2BException
	{
		boolean      lb_focus;
		DatosBasicos ldb_datosBasicos;
		FacesContext lfc_context;
		String       ls_idFormulario;

		lb_focus             = false;
		ldb_datosBasicos     = getDatosBasicos();
		lfc_context          = FacesUtils.getFacesContext();
		ls_idFormulario      = getFormulario();

		if(ldb_datosBasicos != null)
		{
			Apertura la_apertura;

			la_apertura = ldb_datosBasicos.getApertura();

			if(la_apertura == null)
				throw new B2BException(ErrorKeys.ERROR_FECHA_APERTURA);

			Documento ld_documento;

			ld_documento = la_apertura.getDocumento();

			if(ld_documento == null)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idCalFechaApertura", lfc_context, la_apertura.getFechaApertura(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.ERROR_FECHA_APERTURA);

			if(!validateStyles(ls_idFormulario + ":idItRadicacion", lfc_context, la_apertura.getRadicacion(), lb_focus))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_RADICADO_APERTURA);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idSOMEscritura", lfc_context, ld_documento.getIdTipoDocumento(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			if(!validateStyles(ls_idFormulario + ":idItDocuActo", lfc_context, ld_documento.getNumero(), lb_focus))
				throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idCalFechaDoc", lfc_context, ld_documento.getFechaDocumento(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idSOMTipoOficina", lfc_context, la_apertura.getIdTipoOficina(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

			if(!validateStyles(ls_idFormulario + ":idPaisDocumento", lfc_context, la_apertura.getIdPais(), lb_focus))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idSOMDepartamento", lfc_context, la_apertura.getIdDepartamento(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idSOMMunicipio", lfc_context, la_apertura.getIdMunicipio(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

			if(
			    !validateStyles(
				        ls_idFormulario + ":idSOMOficinaOrigen", lfc_context, la_apertura.getIdOficinaOrigen(), lb_focus
				    )
			)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
		}
	}

	/**
	 * Método encargado de validar panel datos basicos ubicacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarPanelDatosBasicosUbicacion()
	    throws B2BException
	{
		boolean      lb_focus;
		DatosBasicos ldb_datosBasicos;
		FacesContext lfc_context;
		String       ls_idFormulario;

		lb_focus             = false;
		ldb_datosBasicos     = getDatosBasicos();
		lfc_context          = FacesUtils.getFacesContext();
		ls_idFormulario      = getFormulario();

		if(ldb_datosBasicos != null)
		{
			UbicacionZonaRegistral lu_ubicacion;
			PredioRegistro         lpr_predioRegistro;

			lu_ubicacion           = ldb_datosBasicos.getUbicacion();
			lpr_predioRegistro     = ldb_datosBasicos.getPredioRegistro();

			if(lu_ubicacion == null)
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_UBICACION_SELECCIONADO_INVALIDO);

			if(lpr_predioRegistro == null)
				throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = lu_ubicacion.getCirculoRegistral();

				if(lcr_circulo == null)
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_UBICACION_SELECCIONADO_INVALIDO);

				if(
				    !validateStyles(
					        ls_idFormulario + ":circuloRegistral", lfc_context, lcr_circulo.getIdCirculo(), lb_focus
					    )
				)
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_UBICACION_SELECCIONADO_INVALIDO);
			}

			{
				Municipio lm_municipio;

				lm_municipio = lu_ubicacion.getMunicipio();

				if(lm_municipio == null)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_UBICACION);

				if(
				    !validateStyles(
					        ls_idFormulario + ":idSOMMunDirRe", lfc_context, lm_municipio.getIdMunicipio(), lb_focus
					    )
				)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_UBICACION);
			}

			{
				Vereda lv_vereda;

				lv_vereda = lu_ubicacion.getVereda();

				if(lv_vereda == null)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_VER_UBICACION);

				if(!validateStyles(ls_idFormulario + ":idSOMVerDirRe", lfc_context, lv_vereda.getIdVereda(), lb_focus))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_VER_UBICACION);
			}

			{
				EstadoPredio lep_estadoPredio;

				lep_estadoPredio = lu_ubicacion.getEstadoPredio();

				if(lep_estadoPredio == null)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_PREDIO_UBICACION);

				if(
				    !validateStyles(
					        ls_idFormulario + ":idSOMestadoPredio", lfc_context, lep_estadoPredio.getIdEstadoPredio(),
					        lb_focus
					    )
				)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_PREDIO_UBICACION);
			}

			{
				String ls_idTipoUsoSuelo;

				ls_idTipoUsoSuelo = lpr_predioRegistro.getIdTipoUsoSuelo();

				if(!StringUtils.isValidString(ls_idTipoUsoSuelo))
					throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

				if(!validateStyles(ls_idFormulario + ":idSOMusoDelPredio", lfc_context, ls_idTipoUsoSuelo, lb_focus))
					throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);
			}

			{
				String ls_idTipoPredio;

				ls_idTipoPredio = lpr_predioRegistro.getIdTipoPredio();

				if(!StringUtils.isValidString(ls_idTipoPredio))
				{
					AccPredioRegistro lapr_predioRegistro;

					lapr_predioRegistro = ldb_datosBasicos.getAccPredioRegistro();

					if(lapr_predioRegistro != null)
					{
						String ls_idPredio;

						ls_idPredio = lapr_predioRegistro.getIdTipoPredio();

						if(!StringUtils.isValidString(ls_idPredio))
							throw new B2BException(ErrorKeys.ERROR_TIPO_PREDIO);
						else
							lpr_predioRegistro.setIdTipoPredio(ls_idPredio);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TIPO_PREDIO);
				}
			}
		}
	}

	/**
	 * Metodo encargado de validar y de cambiar estilo de borde en campos
	 * obligatorios para el panel de Datos anotación.
	 *
	 * @param ad_documento Argumento de tipo <code>Documento</code> que
	 * contiene los criterios a validar.
	 * @param ab_focus Argumento de tipo <code>boolean</code> que indica si se debe
	 * dejar la el foco en un componente indicado.
	 * @param as_idFormulario Argumento de tipo <code>String</code> que contiene el
	 * id del formulario desde donde se realiza la petición.
	 * @param afc_context Argumento de tipo <code>FacesContext</code> que contiene
	 * el contexto de la petición realizada.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	protected void validarPanelDatosDocumento(
	    Documento ad_documento, boolean ab_focus, String as_idFormulario, FacesContext afc_context
	)
	    throws B2BException
	{
		try
		{
			if(ad_documento != null)
			{
				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdTipoDocumento();

					ab_focus = validateStyles(as_idFormulario + ":idSOMEscrituraD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getNumero();

					ab_focus = validateStyles(as_idFormulario + ":idItDocuActoD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					Date   ld_tmp;
					String ls_idFechaDocumento;

					ld_tmp                  = ad_documento.getFechaDocumento();
					ls_idFechaDocumento     = as_idFormulario + ":idCalFechaDocD";

					ab_focus = validateStyles(ls_idFechaDocumento, afc_context, ld_tmp, ab_focus);

					if(ld_tmp == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DOCUMENTO);
					else
					{
						Date ld_fechaActual;
						ld_fechaActual = new Date();

						if(ld_tmp.after(ld_fechaActual))
						{
							RequestContext.getCurrentInstance()
								              .execute(
								    "PrimeFaces.focus('" + ls_idFechaDocumento + ":idCalFechaDocD');"
								);
							throw new B2BException(ErrorKeys.ERROR_FECHA_DOC_LIMITE);
						}
					}
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdTipoOficina();

					ab_focus = validateStyles(as_idFormulario + ":idSOMTipoOficinaD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_OFICINA);
				}

				{
					String ls_tmp;

					ls_tmp     = ad_documento.getTipoEntidad();

					ab_focus = validateStyles(as_idFormulario + ":idSOMTipoEntidadD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ENTIDAD);
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdPais();

					ab_focus = validateStyles(as_idFormulario + ":idPaisDocumentoD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdDepartamento();

					ab_focus = validateStyles(as_idFormulario + ":idSOMDepartamentoD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdMunicipio();

					ab_focus = validateStyles(as_idFormulario + ":idSOMMunicipioD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_tmp;
					ls_tmp     = ad_documento.getIdOficinaOrigen();

					ab_focus = validateStyles(as_idFormulario + ":idSOMOficinaOrigenD", afc_context, ls_tmp, ab_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPanelDatosDocumento", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de validar campos obligatorios para el panel de detalle
	 * anotación.
	 *
	 * @param ldas_detalleAnotacion Argumento de tipo <code>DetalleAntSistema</code> que
	 * contiene los criterios a validar.
	 * @param lb_validar Argumento de tipo <code>boolean</code> que indica si se debe
	 * realizar la validación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarPanelDetalleAnotacion(DetalleAntSistema ldas_detalleAnotacion, boolean lb_validar)
	    throws B2BException
	{
		try
		{
			if((ldas_detalleAnotacion == null) && lb_validar)
				throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_DETALLE_ANT_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPanelDetalleAnotacion", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar panel direcciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarPanelDirecciones()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(getDireccionesPredio()))
			throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
	}

	/**
	 * Metodo encargado de validar y de cambiar estilo de borde en campos
	 * obligatorios para el panel de Especificación.
	 *
	 * @param lap_anotacionPredio correspondiente al valor del tipo de objeto AnotacionPredio
	 * @param ab_validarAnotacionACancelar correspondiente al valor del tipo de objeto boolean
	 * @param lb_focus correspondiente al valor del tipo de objeto boolean
	 * @param ls_idFormulario correspondiente al valor del tipo de objeto String
	 * @param lfc_context correspondiente al valor del tipo de objeto FacesContext
	 * @return Variable de tipo <code>String</code> que contiene la descripción de
	 * la naturaleza juridica.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	protected String validarPanelEspecificacion(
	    AnotacionPredio lap_anotacionPredio, boolean ab_validarAnotacionACancelar, boolean lb_focus,
	    String ls_idFormulario, FacesContext lfc_context
	)
	    throws B2BException
	{
		String ls_descripcion;

		ls_descripcion = null;

		try
		{
			if(lap_anotacionPredio != null)
			{
				String ls_naturalezaJuridicaSeleccionada;
				ls_naturalezaJuridicaSeleccionada     = getNaturalezaJuridicaSeleccionada();

				lb_focus = validateStyles(
					    ls_idFormulario + ":idSOMTipoActo", lfc_context, ls_naturalezaJuridicaSeleccionada, lb_focus
					);

				if(!StringUtils.isValidString(ls_naturalezaJuridicaSeleccionada))
					throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

				{
					String ls_tmp;
					ls_tmp     = lap_anotacionPredio.getEspecificacion();

					lb_focus = validateStyles(ls_idFormulario + ":idItDescripcion", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String[] lsa_datos;
					lsa_datos = ls_naturalezaJuridicaSeleccionada.split("-");
					lap_anotacionPredio.setIdNaturalezaJuridica(ls_naturalezaJuridicaSeleccionada);

					if((lsa_datos != null) && (lsa_datos.length > 1))
					{
						String ls_idNaturalezaJuridica;
						String ls_version;

						ls_idNaturalezaJuridica     = lsa_datos[0];
						ls_version                  = lsa_datos[1];

						if(StringUtils.isValidString(ls_idNaturalezaJuridica) && StringUtils.isValidString(ls_version))
						{
							NaturalezaJuridica lnj_parametros;

							lnj_parametros = new NaturalezaJuridica();

							lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
							lnj_parametros.setVersion(NumericUtils.getLong(ls_version));

							lnj_parametros = iasr_antiguoSistemaRemote.findNaturalezaJuridicaById(lnj_parametros);

							if(lnj_parametros != null)
							{
								ls_descripcion     = lnj_parametros.getNombre();

								lb_focus     = validateStyles(
									    ls_idFormulario + ":idItValorActo", lfc_context,
									    NumericUtils.getDoubleWrapper(1D), lb_focus
									);

								lb_focus = validateStyles(
									    ls_idFormulario + ":idItAnotacionCancela", lfc_context, EstadoCommon.V, lb_focus
									);

								{
									TipoActo lta_tipoActo;
									lta_tipoActo = new TipoActo();

									lta_tipoActo.setIdTipoActo(ls_idNaturalezaJuridica);

									lta_tipoActo = iasr_antiguoSistemaRemote.findTipoActoById(lta_tipoActo);

									if(lta_tipoActo != null)
									{
										String ls_actoSinCuantia;

										ls_actoSinCuantia = lta_tipoActo.getActoSinCuantia();

										if(
										    StringUtils.isValidString(ls_actoSinCuantia)
											    && ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.N)
										)
										{
											BigDecimal lbd_tmp;
											Double     ld_tmp;

											lbd_tmp     = lap_anotacionPredio.getValor();
											ld_tmp      = (lbd_tmp != null) ? NumericUtils.getDoubleWrapper(lbd_tmp)
												                            : null;

											lb_focus = validateStyles(
												    ls_idFormulario + ":idItValorActo", lfc_context, ld_tmp, lb_focus
												);

											if(!NumericUtils.isValidDouble(ld_tmp))
												throw new B2BException(ErrorKeys.ERROR_VALOR_ACTO_INVALIDO);
										}
									}
								}

								{
									String ls_grupo;
									ls_grupo = lnj_parametros.getIdGrupoNatJur();

									if(
									    StringUtils.isValidString(ls_grupo)
										    && (ls_grupo.contains("700") || ls_grupo.contains("800"))
										    && ab_validarAnotacionACancelar
									)
									{
										AnotacionCancelacion lac_anotacionCancelacion;
										lac_anotacionCancelacion = getAnotacionCancelacion();

										if(lac_anotacionCancelacion != null)
										{
											Long   ll_tmp;
											String ls_tmp;

											ll_tmp     = lac_anotacionCancelacion.getIdAnotacion1();
											ls_tmp     = NumericUtils.isValidLong(ll_tmp)
												? StringUtils.getString(NumericUtils.getLong(ll_tmp)) : "";

											lb_focus = validateStyles(
												    ls_idFormulario + ":idItAnotacionCancela", lfc_context, ls_tmp,
												    lb_focus
												);

											if(!StringUtils.isValidString(ls_tmp))
												throw new B2BException(ErrorKeys.ERROR_SIN_ANOTACION_CANCELACION);
										}
									}
								}
							}
						}
					}
				}
			}

			{
				AnotacionCancelacion lac_anotacionCancelacion;
				lac_anotacionCancelacion = getAnotacionCancelacion();

				if((lac_anotacionCancelacion != null) && ab_validarAnotacionACancelar)
				{
					Long lL_anotacionCancelacion;
					long ll_anotacionCancelacion;

					lL_anotacionCancelacion     = lac_anotacionCancelacion.getIdAnotacion1();
					ll_anotacionCancelacion     = NumericUtils.getLong(lL_anotacionCancelacion);

					if(lL_anotacionCancelacion != null)
					{
						Collection<Anotacion> lca_datos;
						boolean               lb_existe;

						lca_datos     = getAnotacionesAgregadas();
						lb_existe     = false;

						if(CollectionUtils.isValidCollection(lca_datos))
						{
							for(Anotacion la_iterador : lca_datos)
							{
								if(la_iterador != null)
								{
									lb_existe = ll_anotacionCancelacion == la_iterador.getIdAnotacion();

									if(lb_existe)
										break;
								}
							}
						}

						if(!lb_existe)
						{
							RequestContext.getCurrentInstance()
								              .execute(
								    "PrimeFaces.focus('" + ls_idFormulario + ":idItAnotacionCancela');"
								);
							throw new B2BException(ErrorKeys.ERROR_ANOTACION_CANCELAR);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPanelEspecificacion", lb2be_e);
			throw lb2be_e;
		}

		return ls_descripcion;
	}

	/**
	 * Metodo encargado de validar y de cambiar estilo de borde en campos obligatorios para el panel de Especificación.
	 *
	 * @param aca_datos Argumento de tipo <code>Collection<Anotacion></code> que contiene las anotaciones agregadas.
	 * @param aca_intervinientesAgregados Argumento de tipo <code>Collection<Anotacion></code> que contiene los intervinientes agregados.
	 * @param aap_anotacionPredio Argumento de tipo <code>AnotacionPredio</code> que contiene datos para ser validados.
	 * @param aa_anotacion Argumento de tipo <code>Anotacion</code> que contiene los datos de la anotación a validar.
	 * @param ab_conAnotacion Argumento de tipo <code>boolean</code> que determina si existen anotaciones agregadas.
	 * @param ab_bloqueo Argumento de tipo <code>boolean</code> que determina si anotación predio esta bloqueado.
	 * @param al_anotacionTmp Argumento de tipo <code>long</code> que contiene el id anotación temporal.
	 * @param al_numeroAnotacion Argumento de tipo <code>Long</code> que contiene el id anotación.
	 * @param as_idFormulario Argumento de tipo <code>String</code> que contiene el id del formulario.
	 * @param afc_context Argumento de tipo <code>FacesContext</code> que contiene el contexto de la petición realizada.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@SuppressWarnings("deprecation")
	protected void validarPanelIntervinientes(
	    Collection<Anotacion> aca_datos, Collection<Anotacion> aca_intervinientesAgregados,
	    AnotacionPredio aap_anotacionPredio, Anotacion aa_anotacion, boolean ab_conAnotacion, boolean ab_bloqueo,
	    long al_anotacionTmp, Long al_numeroAnotacion, String as_idFormulario, FacesContext afc_context
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(aca_datos) && !ab_conAnotacion)
			{
				long ll_idAnotacionActual;

				ll_idAnotacionActual = NumericUtils.DEFAULT_LONG_VALUE;

				for(Anotacion la_iterador : aca_datos)
				{
					if(la_iterador != null)
					{
						long li_numeroAnotacion;

						li_numeroAnotacion = la_iterador.getIdAnotacion();

						if(li_numeroAnotacion > ll_idAnotacionActual)
							ll_idAnotacionActual = li_numeroAnotacion;
					}
				}

				ll_idAnotacionActual++;

				if(al_anotacionTmp != ll_idAnotacionActual)
				{
					RequestContext.getCurrentInstance()
						              .execute("PrimeFaces.focus('" + as_idFormulario + ":idItNumeroAnotacionID');");

					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0] = StringUtils.getString(ll_idAnotacionActual);

					throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION_CONSECUTIVO, aoa_messageArgs);
				}
			}
			else if(!CollectionUtils.isValidCollection(aca_datos))
			{
				long ll_anotacionFinal;

				ll_anotacionFinal = 1;

				if(al_anotacionTmp != ll_anotacionFinal)
				{
					RequestContext.getCurrentInstance()
						              .execute("PrimeFaces.focus('" + as_idFormulario + ":idItNumeroAnotacionID');");

					Object[] aoa_messageArgs = new String[1];

					aoa_messageArgs[0] = StringUtils.getString(ll_anotacionFinal);

					throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION_DEBE_SER, aoa_messageArgs);
				}
			}

			aa_anotacion.setIdAnotacion(NumericUtils.getLong(al_numeroAnotacion));

			aap_anotacionPredio.setIdAnotacion(getNumeroAnotacion());

			if(CollectionUtils.isValidCollection(aca_intervinientesAgregados))
			{
				boolean lb_validarAlertas;

				lb_validarAlertas = StringUtils.isValidString(aap_anotacionPredio.getIdNaturalezaJuridica())
						&& aap_anotacionPredio.getIdNaturalezaJuridica().equalsIgnoreCase(TipoActoCommon.BALDIOS);

				for(Anotacion la_intervinienteActual : aca_intervinientesAgregados)
				{
					if(la_intervinienteActual != null)
					{
						if(ab_bloqueo)
						{
							AnotacionPredioCiudadano lapc_anotacionCuidadano;

							lapc_anotacionCuidadano = la_intervinienteActual.getAnotacionPredioCiudadano();

							if(lapc_anotacionCuidadano != null)
							{
								String ls_idPersona;

								ls_idPersona = lapc_anotacionCuidadano.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona) && lb_validarAlertas)
									alertasIntervinientesBaldios(ls_idPersona, true);
							}
						}

						la_intervinienteActual.setIdAnotacion(NumericUtils.getLong(al_numeroAnotacion));
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_AGREGAR_INTERVINIENTE_ANOTACION);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPanelIntervinientes", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar el panel de linderos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarPanelLinderos()
	    throws B2BException
	{
		boolean        lb_focus;
		CabidaLinderos lcl_linderos;
		FacesContext   lfc_context;
		String         ls_idFormulario;

		lb_focus            = false;
		lcl_linderos        = getCabidaLinderos();
		lfc_context         = FacesUtils.getFacesContext();
		ls_idFormulario     = getFormulario();

		if(lcl_linderos != null)
		{
			String ls_lindero;

			ls_lindero     = lcl_linderos.getLinderos();
			lb_focus       = validateStyles(ls_idFormulario + ":idITALinderos", lfc_context, ls_lindero, lb_focus);

			if(StringUtils.isValidString(ls_lindero))
			{
				int li_cantidadCaracteres;

				li_cantidadCaracteres = ls_lindero.length();

				if((li_cantidadCaracteres > 0) && (li_cantidadCaracteres < 100))
				{
					lb_focus = validateStyles(
						    ls_idFormulario + ":idITALinderos", lfc_context, IdentificadoresCommon.DATO_INVALIDO,
						    lb_focus
						);

					throw new B2BException(ErrorKeys.ERROR_LINDERO_TAM);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
		}
		else
		{
			lb_focus = validateStyles(
				    ls_idFormulario + ":idITALinderos", lfc_context, IdentificadoresCommon.DATO_INVALIDO, lb_focus
				);

			throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
		}
	}

	/**
	 * Método encargado de validar la propiedad grabacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en grabacion
	 */
	private boolean isGrabacion()
	{
		Map<String, String> lmss_params;
		String              ls_grabar;
		String              ls_detalle;

		lmss_params     = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		ls_grabar       = lmss_params.get(is_idFormularioGrabar);
		ls_detalle      = lmss_params.get(is_idFormularioDetalleGrabar);

		return StringUtils.isValidString(ls_grabar) || StringUtils.isValidString(ls_detalle);
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 *
	 * @param almso_lllhmso Argumento que modifica el valor de la propiedad.
	 */
	private void setMatriculas(List<Map<String, Object>> almso_lllhmso)
	{
		ilmso_matriculas = almso_lllhmso;
	}

	/**
	 * Método encargado de retornar el valor de predio documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void getPredioDocumento()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_predioDocumento))
			ilmso_predioDocumento = iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.PREDIO
				);

		if(CollectionUtils.isValidCollection(ilmso_predioDocumento))
		{
			imso_datosDelTurno = ilmso_predioDocumento.get(0);
			setIdSolicitud((String)imso_datosDelTurno.get("ID_SOLICITUD"));
		}
	}

	/**
	 * Método encargado de actualizar el area terreno.
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
			}
			else
				laaui_data.setAreaTerreno(null);
		}
	}

	/**
	 * Método encargado de actualizar el nombre del predio.
	 */
	private void actualizarNombrePredio()
	{
		AccPredioRegistro lapr_predioRegistro;
		DatosAntSistema   ldas_datosAnt;

		lapr_predioRegistro     = getAccPredioRegistro();
		ldas_datosAnt           = getDatoAntSistemaCargado();

		if((lapr_predioRegistro != null) && (ldas_datosAnt != null))
			ldas_datosAnt.setNombrePredio(lapr_predioRegistro.getNombrePredio());
	}

	/**
	 * Método encargado de mostrar en pantalla la pestaña del proceso de antiguo sistema seleccionada.
	 *
	 * @param as_tabIndex Identificador de la pestaña seleccionada: C para crear, A para asociar e
	 * I para informe
	 */
	private void cambiarTabProceso(String as_tabIndex)
	{
		TabView ltv_tabView;

		ltv_tabView = (TabView)FacesContext.getCurrentInstance().getViewRoot()
				                               .findComponent("idFormAntSistema:idProcesoAntiguoSistema");

		if((ltv_tabView != null) && StringUtils.isValidString(as_tabIndex))
		{
			if(as_tabIndex.equalsIgnoreCase(EstadoCommon.C))
			{
				if(isCrearMatriculaAntSistema() || isAsociarMatriculaAntSistema() || isInformeDeBusquedaAntSistema())
					ltv_tabView.setActiveIndex(0);
			}
			else if(as_tabIndex.equalsIgnoreCase(EstadoCommon.A))
			{
				if(isAsociarMatriculaAntSistema())
				{
					if(isCrearMatriculaAntSistema())
						ltv_tabView.setActiveIndex(1);
					else
						ltv_tabView.setActiveIndex(0);
				}
				else if(isCrearMatriculaAntSistema() || isInformeDeBusquedaAntSistema())
					ltv_tabView.setActiveIndex(0);
			}
			else if(as_tabIndex.equalsIgnoreCase(EstadoCommon.I))
			{
				if(isInformeDeBusquedaAntSistema())
				{
					if(isCrearMatriculaAntSistema())
					{
						if(isAsociarMatriculaAntSistema())
							ltv_tabView.setActiveIndex(2);
						else
							ltv_tabView.setActiveIndex(1);
					}
					else if(isAsociarMatriculaAntSistema())
						ltv_tabView.setActiveIndex(1);
					else
						ltv_tabView.setActiveIndex(0);
				}
				else if(isCrearMatriculaAntSistema() || isAsociarMatriculaAntSistema())
					ltv_tabView.setActiveIndex(0);
			}
		}
	}

	/**
	 * Método encargado de recopilar la información necesaria para mostrar de manera correcta la pestaña de anotaciones.
	 *
	 * @param ab_baldios correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarAnotacion(boolean ab_baldios)
	    throws B2BException
	{
		Collection<Anotacion> lca_anotaciones;
		long                  ll_numeroAnotacion;
		String                ls_formulario;

		lca_anotaciones        = getAnotacionesAgregadas();
		ll_numeroAnotacion     = 0;
		ls_formulario          = getFormulario();

		if(CollectionUtils.isValidCollection(lca_anotaciones))
		{
			Anotacion           la_anotacionBaldios;
			Iterator<Anotacion> lia_iterator;

			la_anotacionBaldios     = null;
			lia_iterator            = lca_anotaciones.iterator();

			while(lia_iterator.hasNext())
			{
				Anotacion la_anotacion;

				la_anotacion = lia_iterator.next();

				if(la_anotacion != null)
				{
					if(ab_baldios)
					{
						AnotacionPredio lap_anotacionPredio;

						lap_anotacionPredio = la_anotacion.getAnotacionPredio();

						if(lap_anotacionPredio != null)
						{
							String ls_idNaturalezaJuridica;

							ls_idNaturalezaJuridica = lap_anotacionPredio.getIdNaturalezaJuridica();

							if(StringUtils.isValidString(ls_idNaturalezaJuridica))
							{
								String[] lsa_datos;
								lsa_datos = ls_idNaturalezaJuridica.split("-");

								if((lsa_datos != null) && (lsa_datos.length > 1))
								{
									ls_idNaturalezaJuridica = lsa_datos[0];

									if(irr_registroRemote.validarTipoActoBaldio(ls_idNaturalezaJuridica))
									{
										la_anotacionBaldios = la_anotacion;

										la_anotacion.setBloqueo(true);
									}
								}
							}
						}
					}
					else
					{
						long       ll_idAnotacion;
						BigDecimal lbd_orden;

						ll_idAnotacion         = la_anotacion.getIdAnotacion();
						ll_numeroAnotacion     = (ll_idAnotacion > ll_numeroAnotacion) ? ll_idAnotacion
							                                                           : ll_numeroAnotacion;
						lbd_orden              = la_anotacion.getOrden();
						setOrdenAnotacion(lbd_orden);
					}

					if(isGrabacion())
						la_anotacion.setBloqueo(true);
				}
			}

			if(ab_baldios && (la_anotacionBaldios != null))
				modificarAnotaciones(la_anotacionBaldios);
		}

		ll_numeroAnotacion++;

		setNumeroAnotacion(NumericUtils.getLongWrapper(ll_numeroAnotacion));
		setDetalleAntSistemaAnotacion(null);
		setDeshabilitarNumeroAnotacion(true);
		cargarDetallePredio(getDatoAntSistemaCargado(), false);

		if(StringUtils.isValidString(ls_formulario) && ls_formulario.equalsIgnoreCase(":" + is_idFormularioGrabar))
		{
			AnotacionPredio lap_anotacion;

			lap_anotacion = getAnotacionPredio();

			if(lap_anotacion != null)
				lap_anotacion.setRadicacion(getIdTurno());
		}
	}

	/**
	 * Método encargado de cargar la información de la complementación y linderos.
	 */
	private void cargarComplementacionData()
	{
		try
		{
			ComplementacionCalificacion lcc_complementacionCalificacion;
			AccComplementacionPredio    lacp_complementacionPredio;
			String                      ls_idTurno;
			ComplementacionPredio       lcp_cp;

			lacp_complementacionPredio          = new AccComplementacionPredio();
			ls_idTurno                          = getIdTurno();
			lcc_complementacionCalificacion     = getComplementacionCalificacion();
			lcp_cp                              = lcc_complementacionCalificacion.getComplementacionPredio();

			if(!StringUtils.isValidString(ls_idTurno))
			{
				AccPredioRegistro lapr_predio;

				lapr_predio = getAccPredioRegistro();

				if(lapr_predio != null)
					ls_idTurno = lapr_predio.getIdTurno();
			}

			{
				Collection<AccPredioRegistro> lcapr_matriculas;

				lcapr_matriculas = getDatosTurnoMatricula();

				if(CollectionUtils.isValidCollection(lcapr_matriculas))
				{
					RegistroCalificacion        lrc_registroCal;
					LinderoRegistroCalificacion llrc_linderos;

					lrc_registroCal = new RegistroCalificacion();

					{
						boolean                     lb_found;
						Iterator<AccPredioRegistro> liapr_iterador;

						lb_found           = false;
						liapr_iterador     = lcapr_matriculas.iterator();

						while(liapr_iterador.hasNext() && !lb_found)
						{
							AccPredioRegistro lapr_matricula;

							lapr_matricula = liapr_iterador.next();

							if(lapr_matricula != null)
							{
								lb_found = true;

								lrc_registroCal.setIdCirculo(lapr_matricula.getIdCirculo());
								lrc_registroCal.setIdMatricula(lapr_matricula.getIdMatricula());
								lrc_registroCal.setTurno(getIdTurno());
							}
						}
					}

					llrc_linderos = irr_calificacionRemote.cargarLinderosEnglobes(lrc_registroCal);

					if(llrc_linderos != null)
					{
						CabidaLinderos lcl_cabidaLinderos;

						lcl_cabidaLinderos = getCabidaLinderos();

						if(lcl_cabidaLinderos == null)
							lcl_cabidaLinderos = new CabidaLinderos();

						lcl_cabidaLinderos.setLinderos(llrc_linderos.getLindero());
					}
				}
			}

			lacp_complementacionPredio.setIdTurno(ls_idTurno);
			lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));
			lacp_complementacionPredio.setIdDatosAntSistema(getIdDatosAntSistemaActual());

			lacp_complementacionPredio = irr_calificacionRemote.cargarComplementacion(
				    lacp_complementacionPredio, getUserId()
				);

			if(lacp_complementacionPredio != null)
			{
				String ls_tipoComplementacion;

				ls_tipoComplementacion = lacp_complementacionPredio.getTipoComplementacion();

				if(StringUtils.isValidString(ls_tipoComplementacion))
				{
					if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.C))
						lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
					else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.A))
						lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.CONSTRUIR);
					else if(ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.N))
					{
						lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
						setHabilitarComplementacion(true);
					}

					limpiarComplementacionData(true);
				}

				lcp_cp.setIdComplementacion(
				    String.valueOf(NumericUtils.getLong(lacp_complementacionPredio.getIdComplementacionOriginal()))
				);
				lcp_cp.setComplementacion(lacp_complementacionPredio.getComplementacion());

				lcc_complementacionCalificacion.setComplementacionPredio(lcp_cp);
			}
			else
			{
				lcp_cp.setComplementacion(null);
				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
				setHabilitarComplementacion(true);
			}

			setComplementacionCalificacion(lcc_complementacionCalificacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de cargar los datos del area.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosArea()
	    throws B2BException
	{
		boolean lb_validar;
		String  ls_idFormulario;

		ls_idFormulario     = getFormulario();
		lb_validar          = StringUtils.isValidString(ls_idFormulario)
				&& (ls_idFormulario.equalsIgnoreCase(is_idFormulario_calificacion)
				|| ls_idFormulario.equalsIgnoreCase(":" + is_idFormularioGrabar));

		if(!lb_validar)
			setAreaUI(null);

		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		AccPredioRegistro lapr_predioRegistro;

		lapr_predioRegistro = getAccPredioRegistro();

		if(lapr_predioRegistro != null)
		{
			try
			{
				AccAreaUI            laui_dataArea;
				RegistroCalificacion lrc_data;

				lrc_data = new RegistroCalificacion();

				lrc_data.setIdCirculo(lapr_predioRegistro.getIdCirculo());
				lrc_data.setIdMatricula(lapr_predioRegistro.getIdMatricula());
				lrc_data.setIdCirculoMatriz(lapr_predioRegistro.getIdCirculo());
				lrc_data.setIdMatriculaMatriz(lapr_predioRegistro.getIdMatricula());
				lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_data.setTurno(getIdTurno());

				laui_dataArea = irr_calificacionRemote.cargarDatosAreaEnglobes(lrc_data);

				if(laui_dataArea != null)
				{
					AccAreaPredio     laap_area;
					DetalleAreaPredio ldap_areaPrivada;
					DetalleAreaPredio ldap_areaConstruida;

					laap_area               = laui_dataArea.getAreaPredio();
					ldap_areaPrivada        = laui_dataArea.getDetalleAreaPrivada();
					ldap_areaConstruida     = laui_dataArea.getDetalleAreaConstruida();

					if(ldap_areaPrivada != null)
					{
						Double ld_area;

						ld_area = ldap_areaPrivada.getArea();

						if(NumericUtils.isValidDouble(ld_area))
							ldap_areaPrivada.setAreaLectura(StringUtils.getString(ld_area));
					}

					if(ldap_areaConstruida != null)
					{
						Double ld_area;

						ld_area = ldap_areaConstruida.getArea();

						if(NumericUtils.isValidDouble(ld_area))
							ldap_areaConstruida.setAreaLectura(StringUtils.getString(ld_area));
					}

					if(laap_area != null)
					{
						Double ld_coeficiente;

						ld_coeficiente = laap_area.getCoeficiente();

						if(NumericUtils.isValidDouble(ld_coeficiente))
							laap_area.setCoeficienteLectura(StringUtils.getString(ld_coeficiente));

						{
							DatosBasicos ldb_datosBasicos;

							ldb_datosBasicos = getDatosBasicos();

							if(ldb_datosBasicos != null)
							{
								PredioRegistro lpr_predioRegistro;

								lpr_predioRegistro = ldb_datosBasicos.getPredioRegistro();

								if(lpr_predioRegistro != null)
									laap_area.setTipoSuelo(lpr_predioRegistro.getIdTipoUsoSuelo());
							}
						}
					}
				}

				if(!lb_validar)
					setAreaUI(laui_dataArea);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDatosArea", lb2be_e);
				throw new B2BException(lb2be_e.getMessage());
			}
			finally
			{
				actualizarAreaTerreno();
			}
		}
	}

	/**
	 * Método encargado de cargar en pantalla la información del detalle de un predio.
	 *
	 * @param adas_dato objeto contenedor de la información a utilizar para buscar los datos del predio
	 * @param ab_mostrarMensaje correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDetallePredio(DatosAntSistema adas_dato, boolean ab_mostrarMensaje)
	    throws B2BException
	{
		if(adas_dato == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		setDatoAntSistemaCargado(adas_dato);

		Map<String, Object> lmso_datosTurno;

		lmso_datosTurno = getDatosDelTurno();

		if(CollectionUtils.isValidCollection(lmso_datosTurno))
		{
			String ls_idTurno;

			ls_idTurno = StringUtils.getString(lmso_datosTurno.get(IdentificadoresCommon.ID_TURNO));

			if(StringUtils.isValidString(ls_idTurno))
				adas_dato.setIdTurno(ls_idTurno);
		}

		setDataAntiguoSistema(iasr_antiguoSistemaRemote.findAntiguoSistemaData(getUserId(), adas_dato));

		setDetallesAntSistema(
		    iasr_antiguoSistemaRemote.obtenerDetallesAntSistema(
		        adas_dato, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
		    )
		);

		{
			String ls_requiereFirma;
			String ls_revisadoAntSistema;

			ls_requiereFirma          = adas_dato.getRequiereFirmaLibro();
			ls_revisadoAntSistema     = adas_dato.getRevisadoAntStr();

			if(
			    StringUtils.isValidString(ls_requiereFirma) && ls_requiereFirma.equalsIgnoreCase(EstadoCommon.S)
				    && StringUtils.isValidString(ls_revisadoAntSistema)
				    && !ls_revisadoAntSistema.equalsIgnoreCase(EstadoCommon.SIN_REVISAR)
			)
			{
				AntiguoSistemaData lasd_antiguoSistema;

				lasd_antiguoSistema = getDataAntiguoSistema();

				if(lasd_antiguoSistema != null)
				{
					String ls_observacionesFirma;

					ls_observacionesFirma = lasd_antiguoSistema.getObservacionesFirmaLibro();

					if(StringUtils.isValidString(ls_observacionesFirma))
					{
						setJustificacionFirma(ls_observacionesFirma);
						setLibroJustificacionRequiere(true);
					}
				}
			}
			else
			{
				setLibroJustificacionRequiere(false);
				setJustificacionFirma(null);
			}
		}

		setPredioSeleccionado(true);

		if(ab_mostrarMensaje)
		{
			PrimeFaces lpf_prime;
			lpf_prime = PrimeFaces.current();

			lpf_prime.executeScript("PF('wvDatosPredioSeleccionado').expand();");

			addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			lpf_prime.ajax().update(is_mensajesIdGrowl);
		}
	}

	/**
	 * Método encargado de crear anotacion baldios.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void crearAnotacionBaldios()
	    throws B2BException
	{
		try
		{
			AccPredioRegistro lapr_predioRegistro;

			lapr_predioRegistro = getAccPredioRegistro();

			if(lapr_predioRegistro != null)
			{
				Anotacion la_data;

				la_data = irr_calificacionRemote.crearAnotacionBaldios(
					    lapr_predioRegistro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(la_data != null)
				{
					String ls_idNaturalezaJuridica;

					ls_idNaturalezaJuridica = la_data.getNaturalezaJuridica();

					if(
					    StringUtils.isValidString(ls_idNaturalezaJuridica)
						    && ls_idNaturalezaJuridica.equalsIgnoreCase(TipoActoCommon.BALDIOS)
					)
					{
						Collection<Anotacion> lca_intervinientes;

						lca_intervinientes = la_data.getIntervinientesAgregados();

						if(CollectionUtils.isValidCollection(lca_intervinientes))
						{
							for(Anotacion la_anotacionInterviniente : lca_intervinientes)
							{
								if(la_anotacionInterviniente != null)
								{
									AnotacionPredioCiudadano lapc_data;

									lapc_data = la_anotacionInterviniente.getAnotacionPredioCiudadano();

									if(lapc_data != null)
										alertasIntervinientesBaldios(lapc_data.getIdPersona(), false);
								}
							}
						}
					}
				}

				cargarMatricula(lapr_predioRegistro);
				cargarAnotacion(true);
				setMostrarAtrasCrearGrabar(true);
				setOcultarSiguienteCrearGrabar(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearAnotacionBaldios", lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Método encargado de cargar los actos a asociar para una matrícula.
	 *
	 * @param al_numeroMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_circuloRegistral correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String processCell(Long al_numeroMatricula, String as_circuloRegistral, String as_userId)
	    throws B2BException
	{
		StringBuilder lsb_msj;

		lsb_msj = new StringBuilder();

		try
		{
			{
				Long ll_tmp;

				ll_tmp = al_numeroMatricula;

				if(!NumericUtils.isValidLong(ll_tmp))
					lsb_msj.append("El Numero Matrícula es inválido. ");

				List<Map<String, Object>> llllhm_matriculas;

				llllhm_matriculas = getMatriculasData();

				if(CollectionUtils.isValidCollection(llllhm_matriculas))
				{
					String ls_numeroMatricula;
					ls_numeroMatricula = StringUtils.getString(ll_tmp);

					for(Map<String, Object> llhm_data : llllhm_matriculas)
					{
						if(llhm_data != null)
						{
							String ls_circuloMatricula;
							ls_circuloMatricula = llhm_data.get(IdentificadoresCommon.MATRICULA).toString();

							String[] las_cirMat;
							las_cirMat = ls_circuloMatricula.split("-");

							String ls_circulo;
							ls_circulo = las_cirMat[0];

							String ls_matricula;
							ls_matricula = las_cirMat[1];

							if(ls_circulo.equals(as_circuloRegistral) && ls_numeroMatricula.equals(ls_matricula))
							{
								lsb_msj.append(
								    "No se puede agregar una matrícula relacionada con el turno que se está trabajando. "
								);

								break;
							}
						}
					}
				}
			}

			{
				String ls_tmp;

				ls_tmp = as_circuloRegistral;

				if(!StringUtils.isValidString(ls_tmp))
					lsb_msj.append("El circulo Registral es invalido. ");
				else
				{
					if(!ls_tmp.contentEquals(getIdCirculo()))
						lsb_msj.append(
						    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + ls_tmp
						    + " no pertenece al circulo registral del turno. "
						);
				}
			}

			if(!StringUtils.isValidString(lsb_msj.toString()))
			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = new CirculoRegistral();

				lcr_circulo.setIdCirculo(as_circuloRegistral);

				lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

				if(lcr_circulo != null)
				{
					PredioRegistro lpr_predio;

					lpr_predio = new PredioRegistro();

					lpr_predio.setIdCirculo(lcr_circulo.getIdCirculo());
					lpr_predio.setIdMatricula(NumericUtils.getLong(al_numeroMatricula));

					lpr_predio = irr_registroRemote.findPredioRegistroById(lpr_predio, getUserId());

					if(lpr_predio != null)
					{
						int                      li_contarMatriculasIguales;
						String                   ls_matriculaConcatenada;
						PredioActoIU             lpaui_nuevo;
						Collection<PredioActoIU> lcpaui_tmp;

						lcpaui_tmp = getActosAsociadosGeneral();

						if(!CollectionUtils.isValidCollection(lcpaui_tmp))
							lcpaui_tmp = new ArrayList<PredioActoIU>();

						{
							li_contarMatriculasIguales     = 0;

							lpaui_nuevo     = new PredioActoIU(getColumns());

							ls_matriculaConcatenada = StringUtils.getStringNotNull(al_numeroMatricula + "");
							lpaui_nuevo.setMatricula(ls_matriculaConcatenada);

							{
								DireccionPredio ld_direccion;

								ld_direccion = new DireccionPredio();

								ld_direccion.setIdCirculo(lpr_predio.getIdCirculo());
								ld_direccion.setIdMatricula(NumericUtils.getLongWrapper(lpr_predio.getIdMatricula()));

								ld_direccion = irr_registroRemote.findDireccionPredioById(ld_direccion, getUserId());

								if(ld_direccion != null)
									lpaui_nuevo.setDireccion(ld_direccion.getDireccion());
								else
									lsb_msj.append(
									    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
									    + ls_matriculaConcatenada + " No tiene Relacionada una dirección. "
									);
							}

							if(CollectionUtils.isValidCollection(lcpaui_tmp))
							{
								for(PredioActoIU lpaui_actual : lcpaui_tmp)
								{
									if(lpaui_actual != null)
									{
										String ls_matriculaCargada;

										ls_matriculaCargada = lpaui_actual.getMatricula();

										if(ls_matriculaConcatenada.equalsIgnoreCase(ls_matriculaCargada))
											li_contarMatriculasIguales++;
									}
								}
							}

							if((li_contarMatriculasIguales < 1))
							{
								lcpaui_tmp.add(lpaui_nuevo);
								lsb_msj.append(
								    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
								    + ls_matriculaConcatenada + " Insertada correctamente. "
								);
								setActosAsociadosGeneral(lcpaui_tmp);

								{
									String ls_estadoPredio;

									ls_estadoPredio = lpr_predio.getIdEstadoPredio();

									if(StringUtils.isValidString(ls_estadoPredio))
									{
										EstadoPredio lep_estado;

										lep_estado = new EstadoPredio();

										lep_estado.setIdEstadoPredio(ls_estadoPredio);

										lep_estado = irr_registroRemote.findEstadoPredioById(lep_estado, getUserId());

										if(lep_estado != null)
										{
											String ls_estado;

											ls_estado = lep_estado.getNombre();

											if(
											    StringUtils.isValidString(ls_estado)
												    && ls_estado.toUpperCase()
												                    .equalsIgnoreCase(EstadoCommon.ESTADO_CERRADO)
											)
												lsb_msj.append(
												    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
												    + ls_matriculaConcatenada + " a incluir esta en estado 'CERRADO'. "
												);
										}
									}
								}

								{
									String ls_turnoBloqueo;

									ls_turnoBloqueo = lpr_predio.getTurnoBloqueo();

									if(StringUtils.isValidString(ls_turnoBloqueo))
										lsb_msj.append(
										    "El folio de Matrícula " + ls_matriculaConcatenada
										    + " se encuentra Bloqueado con el turno " + ls_turnoBloqueo + ". "
										);
								}
							}
							else
								lsb_msj.append(
								    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA)
								    + ls_matriculaConcatenada + " Ya se encuentra insertada en la tabla. "
								);
						}
					}
					else
						lsb_msj.append(
						    getMessages().getString(MessagesKeys.LA_MATRICULA_INMOBILIARIA) + as_circuloRegistral + "-"
						    + al_numeroMatricula + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
						);
				}
				else
					lsb_msj.append(
					    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + as_circuloRegistral
					    + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("processCell", lb2be_e);
			throw lb2be_e;
		}

		return lsb_msj.toString();
	}

	/**
	 * Método encargado de dejar el tab activo del wizard en datos basicos.
	 */
	private void resetWizard()
	{
		String ls_idFormulario;
		Wizard wizard;

		ls_idFormulario     = getFormulario();
		wizard              = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
				                                      .findComponent(ls_idFormulario + ":wizardId");

		if(wizard != null)
			wizard.setStep(cs_nombreTabDatosBasicos);

		PrimeFaces.current().ajax().update(ls_idFormulario + ":idPanelDetalleListados");
	}
}
