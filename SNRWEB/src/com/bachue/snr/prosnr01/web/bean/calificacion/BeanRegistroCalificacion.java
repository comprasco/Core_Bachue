package com.bachue.snr.prosnr01.web.bean.calificacion;

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
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCierreFolioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.EliminarAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroParcialCalificacion;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroAnotacionProhibicion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntSistema;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.ExportFiles;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.MatriculaSegregacionUi;

import com.bachue.snr.prosnr02.common.constants.ScriptsCommon;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.component.commandbutton.CommandButton;

import org.primefaces.component.dashboard.Dashboard;

import org.primefaces.component.panel.Panel;

import org.primefaces.component.wizard.Wizard;

import org.primefaces.context.RequestContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.application.Application;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIColumn;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanRegistroCalificacion.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanRegistroCalificacion")
public class BeanRegistroCalificacion extends BeanAntSistema implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanRegistroCalificacion.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3049638354872673934L;

	/** Constante is_messageIdGrowlDetail. */
	private static final String is_messageIdGrowlDetail = "fDetalleReg:globalMsg";

	/** Constante is_idForm. */
	private static final String is_idForm = "fRegistroCalif";

	/** Constante is_idForm. */
	private static final String is_idForm2 = "fRegistroCalif:";

	/** Constante is_idFormReloteo. */
	private static final String is_idFormReloteo = "calificacionReloteoForm";

	/** Constante is_messageIdGrowlRegistro. */
	private static final String is_messageIdGrowlRegistro = "fRegistro:globalMsg";

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fRegistroCalif:globalMsg";

	/** Propiedad iap anotacion predio detalle. */
	private com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio iap_anotacionPredioDetalle;

	/** Propiedad ioms data info segrec. */
	private AccAreaPredio ioms_dataInfoSegrec;

	/** Propiedad iaaui area UI. */
	private AccAreaUI iaaui_areaUI;

	/** Propiedad ialp lindero venta no cementerio. */
	private AccLinderoPredio ialp_linderoVentaNoCementerio;

	/** Propiedad ia anotacion. */
	private Anotacion ia_anotacion;

	/** Propiedad ia interviniente actual. */
	private Anotacion ia_intervinienteActual;

	/** Propiedad iac anotacion cancelacion. */
	private AnotacionCancelacion iac_anotacionCancelacion;

	/** Propiedad iapc anotacion predio ciudadano. */
	private AnotacionPredioCiudadano iapc_anotacionPredioCiudadano;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ica anotaciones agregadas. */
	private Collection<Anotacion> ica_anotacionesAgregadas;

	/** Propiedad ica intervinientes agregados. */
	private Collection<Anotacion> ica_intervinientesAgregados;

	/** Propiedad ica matriculas segregadas. */
	private Collection<Anotacion> ica_matriculasSegregadas;

	/** Propiedad icap anotaciones predio eliminadas matriculas. */
	private Collection<AnotacionPredio> icap_anotacionesPredioEliminadasMatriculas;

	/** Propiedad icap matriculas informacion. */
	private Collection<AreaPredio> icap_matriculasInformacion;

	/** Propiedad icms data info predial. */
	private Collection<MatriculaSegregacionUi> icms_dataInfoPredial;

	/** Propiedad icp listado intervinientes. */
	private Collection<Persona> icp_listadoIntervinientes;

	/** Propiedad icrc anotacion A cancelar. */
	private Collection<RegistroCalificacion> icrc_anotacionACancelar;

	/** Propiedad icrc info matricula A copiar. */
	private Collection<RegistroCalificacion> icrc_infoMatriculaACopiar;

	/** Propiedad ictr medios comunicar. */
	private Collection<TipoRecepcion> ictr_mediosComunicar;

	/** Propiedad ictr medios notificar. */
	private Collection<TipoRecepcion> ictr_mediosNotificar;

	/** Propiedad iorc anotacion A cancelar nuevo. */
	private Collection<RegistroCalificacion> iorc_anotacionACancelarNuevo;

	/** Propiedad idb data model. */
	private Dashboard idb_dataModel;

	/** Propiedad iadap detalle area terreno. */
	private DetalleAreaPredio iadap_detalleAreaTerreno;

	/** Propiedad idp direccion predio. */
	private DireccionDelPredio idp_direccionPredio;

	/** Propiedad iodp data direccion predio. */
	private DireccionDelPredio iodp_dataDireccionPredio;

	/** Propiedad id documento detalle. */
	private Documento id_documentoDetalle;

	/** Propiedad iea eliminar anotacion. */
	private EliminarAnotacion iea_eliminarAnotacion;

	/** Propiedad ilr liquidacion remote. */
	@EJB
	private LiquidacionRemote ilr_liquidacionRemote;

	/** Propiedad iols panels. */
	private List<String> iols_panels;

	/** Propiedad il matricula direccion predio. */
	private Long il_matriculaDireccionPredio;

	/** Propiedad il numero anotacion. */
	private Long il_numeroAnotacion;

	/** Propiedad ihmso revision matriculas. */
	private Map<String, Boolean> ihmso_revisionMatriculas;

	/** Propiedad ilhm data matriculas parciales. */
	private Map<String, Boolean> ilhm_dataMatriculasParciales;

	/** Propiedad imsl matriculas aperturar. */
	private Map<String, Long> imsl_matriculasAperturar;

	/** Propiedad lhm predio. */
	private Map<String, Object> lhm_predio;

	/** Propiedad ismui info segregacion manual. */
	private MatriculaSegregacionUi ismui_infoSegregacionManual;

	/** Propiedad ioo oficina medida cautelar. */
	private OficinaOrigen ioo_oficinaMedidaCautelar;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona consulta. */
	private Persona ip_personaConsulta;

	/** Propiedad lp persona nota exceso pago. */
	private Persona lp_personaNotaExcesoPago;

	/** Propiedad ipce correo electronico. */
	private PersonaCorreoElectronico ipce_correoElectronico;

	/** Propiedad ipce correo electronico persona. */
	private PersonaCorreoElectronico ipce_correoElectronicoPersona;

	/** Propiedad ipd direccion correspondencia. */
	private PersonaDireccion ipd_direccionCorrespondencia;

	/** Propiedad ipd direccion residencia. */
	private PersonaDireccion ipd_direccionResidencia;

	/** Propiedad ipt telefono fijo. */
	private PersonaTelefono ipt_telefonoFijo;

	/** Propiedad ipt telefono movil. */
	private PersonaTelefono ipt_telefonoMovil;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir registro datos consultados. */
	private Registro ir_registroDatosConsultados;

	/** Propiedad icr detalle calificacion reloteo. */
	private RegistroCalificacion icr_detalleCalificacionReloteo;

	/** Propiedad iorc detalle anotacion. */
	private RegistroCalificacion iorc_detalleAnotacion;

	/** Propiedad iorc detalle matricula. */
	private RegistroCalificacion iorc_detalleMatricula;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad is solicitud interviniente. */
	private SolicitudInterviniente is_solicitudInterviniente;

	/** Propiedad isi solicitud inter. */
	private SolicitudInterviniente isi_solicitudInter;

	/** Propiedad iosc file cancelacion. */
	private StreamedContent iosc_fileCancelacion;

	/** Propiedad isc file. */
	private StreamedContent isc_file;

	/** Propiedad isc file nota informativa. */
	private StreamedContent isc_fileNotaInformativa;

	/** Propiedad isc file predial. */
	private StreamedContent isc_filePredial;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc imagen comunicado. */
	private StreamedContent isc_imagenComunicado;

	/** Propiedad isc imagen comunicado direccion. */
	private StreamedContent isc_imagenComunicadoDireccion;

	/** Propiedad isc imagen comunicado direccion. */
	private StreamedContent isc_imagenNotaExcesoPago;

	/** Propiedad is acto cancelacion. */
	private String is_actoCancelacion;

	/** Propiedad is circulo. */
	private String is_circulo;

	/** Propiedad is circulo direccion predio. */
	private String is_circuloDireccionPredio;

	/** Propiedad is codigo naturaleza juridica seleccionada. */
	private String is_codigoNaturalezaJuridicaSeleccionada;

	/** Propiedad is documento interesado. */
	private String is_documentoInteresado;

	/** Propiedad is encabezado reloteo. */
	private String is_encabezadoReloteo;

	/** Propiedad is estado matricula. */
	private String is_estadoMatricula;

	/** Propiedad is id anotacion predio seleccionado. */
	private String is_idAnotacionPredioSeleccionado;

	/** Propiedad is id correo seleccion. */
	private String is_idCorreoSeleccion;

	/** Propiedad is id dir corr seleccion. */
	private String is_idDirCorrSeleccion;

	/** Propiedad is id dir res seleccion. */
	private String is_idDirResSeleccion;

	/** Propiedad is id naturaleza juridica inicial. */
	private String is_idNaturalezaJuridicaInicial;

	/** Propiedad is id persona seleccion. */
	private String is_idPersonaSeleccion;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tel fijo seleccion. */
	private String is_idTelFijoSeleccion;

	/** Propiedad is id tel mov seleccion. */
	private String is_idTelMovSeleccion;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is justificacion cierre folio. */
	private String is_justificacionCierreFolio;

	/** Propiedad is matricula detalle reloteo. */
	private String is_matriculaDetalleReloteo;

	/** Propiedad is misma direccion correspondencia. */
	private String is_mismaDireccionCorrespondencia;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is msj copiar anotaciones. */
	private String is_msjCopiarAnotaciones;

	/** Propiedad is naturaleza juridica seleccionada. */
	private String is_naturalezaJuridicaAntigua;

	/** Propiedad is naturaleza juridica seleccionada. */
	private String is_naturalezaJuridicaSeleccionada;

	/** Propiedad is numero proceso. */
	private String is_numeroProceso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones nota info. */
	private String is_observacionesNotaInfo;

	/** Propiedad is observaciones temporales. */
	private String is_observacionesTemporales;

	/** Propiedad is opcion cierre folio. */
	private String is_opcionCierreFolio;

	/** Propiedad is opcion copiar. */
	private String is_opcionCopiar;

	/** Propiedad is pregunta segregacion. */
	private String is_preguntaSegregacion;

	/** Propiedad is referencia. */
	private String is_referencia;

	/** Propiedad is sub proceso. */
	private String is_subProceso;

	/** Propiedad is tipo doc interesado. */
	private String is_tipoDocInteresado;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad ls valor A devolver. */
	private String ls_valorADevolver;

	/** Propiedad ab persona exceso guardado. */
	private boolean ab_personaExcesoGuardado;

	/** Propiedad ib acto con englobe. */
	private boolean ib_actoConEnglobe;

	/** Propiedad ib anotacion agregada. */
	private boolean ib_anotacionAgregada;

	/** Propiedad ib_anotacionesPredioEliminadas. */
	private boolean ib_anotacionesPredioEliminadas;

	/** Propiedad ib autoriza secuencia. */
	private boolean ib_autorizaSecuencia;

	/** Propiedad ib bloquear agregar area terreno. */
	private boolean ib_bloquearAgregarAreaTerreno;

	/** Propiedad ib bloquear boton aprobador. */
	private boolean ib_bloquearBotonAprobador;

	/** Propiedad ib bloquear modificar intervenientes. */
	private boolean ib_bloquearModificarIntervenientes;

	/** Propiedad ib btn regresar loteo. */
	private boolean ib_btnRegresarLoteo;

	/** Propiedad ib btn siguiente loteo. */
	private boolean ib_btnSiguienteLoteo;

	/** Propiedad ib btn terminar loteo. */
	private boolean ib_btnTerminarLoteo;

	/** Propiedad ib cantidad aperturar. */
	private boolean ib_cantidadAperturar;

	/** Propiedad ib cargar botones registro. */
	private boolean ib_cargarBotonesRegistro;

	/** Propiedad ib cargar lindero. */
	private boolean ib_cargarLindero;

	/** Propiedad ib cargue matriculas aperturar. */
	private boolean ib_cargueMatriculasAperturar;

	/** Propiedad ib desactivar eliminar parcial. */
	private boolean ib_desactivarEliminarParcial;

	/** Propiedad ib deshabilitar boton confirmar direccion. */
	private boolean ib_deshabilitarBotonConfirmarDireccion;

	/** Propiedad ib deshabilitar campos por nit. */
	private boolean ib_deshabilitarCamposPorNit;

	/** Propiedad ib deshabilitar correo. */
	private boolean ib_deshabilitarCorreo;

	/** Propiedad ib deshabilitar correspondencia. */
	private boolean ib_deshabilitarCorrespondencia;

	/** Propiedad ib deshabilitar datos basicos. */
	private boolean ib_deshabilitarDatosBasicos;

	/** Propiedad ib deshabilitar datos interviniente. */
	private boolean ib_deshabilitarDatosInterviniente;

	/** Propiedad ib deshabilitar residencia. */
	private boolean ib_deshabilitarResidencia;

	/** Propiedad ib deshabilitar tel fijo. */
	private boolean ib_deshabilitarTelFijo;

	/** Propiedad ib deshabilitar tel movil. */
	private boolean ib_deshabilitarTelMovil;

	/** Propiedad ib deshabilitar tipo documento. */
	private boolean ib_deshabilitarTipoDocumento;

	/** Propiedad ib deshabilitar tipo documento inter. */
	private boolean ib_deshabilitarTipoDocumentoInter;

	/** Propiedad ib deshabilitar tipo persona. */
	private boolean ib_deshabilitarTipoPersona;

	/** Propiedad ib deshabilitar tipo persona inter. */
	private boolean ib_deshabilitarTipoPersonaInter;

	/** Propiedad ib devolucion. */
	private boolean ib_devolucion;

	/** Propiedad ib division material. */
	private boolean ib_divisionMaterial;

	/** Propiedad ib editar datos oficina medida cautelar. */
	private boolean ib_editarDatosOficinaMedidaCautelar;

	/** Propiedad ib editar interviniente. */
	private boolean ib_editarInterviniente;

	/** Propiedad ib_esHomologacionActosLiquidacion. */
	private boolean ib_esHomologacionActosLiquidacion;

	/** Propiedad ib Notificacion correspondencia. */
	private boolean ib_esNotificacionCorrespondencia;

	/** Propiedad ib Notificacion residencia. */
	private boolean ib_esNotificacionResidencia;

	/** Propiedad ib file generado. */
	private boolean ib_fileGenerado;

	/** Propiedad ib flujo digitador. */
	private boolean ib_flujoDigitador;

	/** Propiedad ib genero comunicado direccion. */
	private boolean ib_generoComunicadoDireccion;

	/** Propiedad ib genero nota informativa pago exceso. */
	private boolean ib_generoNotaInformativaPagoExceso;

	/** Propiedad ib habilita secuencia. */
	private boolean ib_habilitaSecuencia;

	/** Propiedad ib habilitar correo co. */
	private boolean ib_habilitarCorreoCo;

	/** Propiedad ib habilitar correo no. */
	private boolean ib_habilitarCorreoNo;

	/** Propiedad ib habilitar digitador. */
	private boolean ib_habilitarDigitador;

	/** Propiedad ib habilitar direccion co co. */
	private boolean ib_habilitarDireccionCoCo;

	/** Propiedad ib habilitar direccion co no. */
	private boolean ib_habilitarDireccionCoNo;

	/** Propiedad ib habilitar direccion re co. */
	private boolean ib_habilitarDireccionReCo;

	/** Propiedad ib habilitar direccion re no. */
	private boolean ib_habilitarDireccionReNo;

	/** Propiedad ib habilitar guardar informacion. */
	private boolean ib_habilitarGuardarInformacion;

	/** Propiedad ib habilitar medida cautelar. */
	private boolean ib_habilitarMedidaCautelar;

	/** Propiedad ib habilitar tabs. */
	private boolean ib_habilitarTabs;

	/** Propiedad ib habilitar tel fijo co. */
	private boolean ib_habilitarTelFijoCo;

	/** Propiedad ib habilitar tel fijo no. */
	private boolean ib_habilitarTelFijoNo;

	/** Propiedad ib habilitar tel movil co. */
	private boolean ib_habilitarTelMovilCo;

	/** Propiedad ib habilitar tel movil no. */
	private boolean ib_habilitarTelMovilNo;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;

	/** Propiedad ib manual matriculas aperturar. */
	private boolean ib_manualMatriculasAperturar;

	/** Propiedad ib modificar area segregacion. */
	private boolean ib_modificarAreaSegregacion;

	/** Propiedad ib modificar direccion predio. */
	private boolean ib_modificarDireccionPredio;

	/** Propiedad ib file generado. */
	private boolean ib_modificarInformacionNotificacion;

	/** Propiedad ib mostrar anotacion cancela. */
	private boolean ib_mostrarAnotacionCancela;

	/** Propiedad ib mostrar correo. */
	private boolean ib_mostrarCorreo;

	/** Propiedad ib mostrar datos basicos. */
	private boolean ib_mostrarDatosBasicos;

	/** Propiedad ib mostrar datos consulta. */
	private boolean ib_mostrarDatosConsulta;

	/** Propiedad ib mostrar direcciones correspondencia. */
	private boolean ib_mostrarDireccionesCorrespondencia;

	/** Propiedad ib mostrar direcciones residencia. */
	private boolean ib_mostrarDireccionesResidencia;

	/** Propiedad ib mostrar listado personas. */
	private boolean ib_mostrarListadoPersonas;

	/** Propiedad ib mostrar regresar loteo. */
	private boolean ib_mostrarRegresarLoteo;

	/** Propiedad ib mostrar regresar venta. */
	private boolean ib_mostrarRegresarVenta;

	/** Propiedad ib mostrar siguiente loteo. */
	private boolean ib_mostrarSiguienteLoteo;

	/** Propiedad ib mostrar siguiente venta. */
	private boolean ib_mostrarSiguienteVenta;

	/** Propiedad ib mostrar telefono fijo. */
	private boolean ib_mostrarTelefonoFijo;

	/** Propiedad ib mostrar telefono movil. */
	private boolean ib_mostrarTelefonoMovil;

	/** Propiedad ib nueva linderos. */
	private boolean ib_nuevaLinderos;

	/** Propiedad ib nueva persona. */
	private boolean ib_nuevaPersona;

	/** Propiedad ib ocultar boton aprobador. */
	private boolean ib_ocultarBotonAprobador;

	/** Propiedad ib pais inter residencia. */
	private boolean ib_paisInterResidencia;

	/** Propiedad ib panel cierre folio. */
	private boolean ib_panelCierreFolio;

	/** Propiedad ib pdf cancelacion. */
	private boolean ib_pdfCancelacion;

	/** Propiedad ib pdf generado. */
	private boolean ib_pdfGenerado;

	/** Propiedad ib pdf parcial. */
	private boolean ib_pdfParcial;

	/** Propiedad ib pdf remate. */
	private boolean ib_pdfRemate;

	/** Propiedad ib proceso baldios. */
	private boolean ib_procesoBaldios;

	/** Propiedad ib proceso desenglobe. */
	private boolean ib_procesoDesenglobe;

	/** Propiedad ib proceso englobes. */
	private boolean ib_procesoEnglobes;

	/** Propiedad ib proceso loteo. */
	private boolean ib_procesoLoteo;

	/** Propiedad ib proceso reloteo. */
	private boolean ib_procesoReloteo;

	/** Propiedad ib proceso remanente. */
	private boolean ib_procesoRemanente;

	/** Propiedad ib proceso venta parcial. */
	private boolean ib_procesoVentaParcial;

	/** Propiedad ib rendered. */
	private boolean ib_rendered;

	/** Propiedad ib rendered habilita secuencia. */
	private boolean ib_renderedHabilitaSecuencia;

	/** Propiedad ib requiere cuantia tipo acto. */
	private boolean ib_requiereCuantiaTipoActo;

	/** Propiedad ib salvar. */
	private boolean ib_salvar;

	/** Propiedad ib salvar area. */
	private boolean ib_salvarArea;

	/** Propiedad ib salvar desenglobes. */
	private boolean ib_salvarDesenglobes;

	/** Propiedad ib salvar venta parcial. */
	private boolean ib_salvarVentaParcial;

	/** Propiedad ib salvar venta parcial cementerio. */
	private boolean ib_salvarVentaParcialCementerio;

	/** Propiedad ib salvar venta parcial no cementerio. */
	private boolean ib_salvarVentaParcialNoCementerio;

	/** Propiedad ib seccion matriculas aperturar. */
	private boolean ib_seccionMatriculasAperturar;

	/** Propiedad ib seleccionado es apoderado. */
	private boolean ib_seleccionadoEsApoderado;

	/** Propiedad ib sin segregacion. */
	private boolean ib_sinSegregacion;

	/** Propiedad ib validacion comunicado medida cautelar. */
	private boolean ib_validacionComunicadoMedidaCautelar;

	/** Propiedad ib validar. */
	private boolean ib_validar;

	/** Propiedad ib validar area. */
	private boolean ib_validarArea;

	/** Propiedad ib validar propiedad horizontal. */
	private boolean ib_validarPropiedadHorizontal;

	/** Propiedad ib viene de aprobacion. */
	private boolean ib_vieneDeAprobacion;

	/** Propiedad ib viene de aprobacion secuencia. */
	private boolean ib_vieneDeAprobacionSecuencia;

	/** Propiedad is hay interviniente con secuencia. */
	private boolean is_hayIntervinienteConSecuencia;

	/** Propiedad lb primer selected. */
	private boolean lb_primerSelected;

	/** Propiedad ii cantidad anotaciones. */
	private int ii_cantidadAnotaciones;

	/** Propiedad il contador anotaciones. */
	private long il_contadorAnotaciones;

	/** Propiedad il contador interviniente. */
	private long il_contadorInterviniente;

	/** Propiedad il id anotacion. */
	private long il_idAnotacion;

	/** Propiedad il id anotacion general. */
	private long il_idAnotacionGeneral;

	/**
	 * Instancia un nuevo objeto bean registro calificacion.
	 */
	public BeanRegistroCalificacion()
	{
		setBloquearBotonAprobador(false);
		setRequiereCuantiaTipoActo(true);
		setPrimerSelected(false);
	}

	/**
	 * Modifica el valor de acto cancelacion.
	 *
	 * @param as_s asigna el valor a la propiedad acto cancelacion
	 */
	public void setActoCancelacion(String as_s)
	{
		is_actoCancelacion = as_s;
	}

	/**
	 * Retorna el valor de acto cancelacion.
	 *
	 * @return el valor de acto cancelacion
	 */
	public String getActoCancelacion()
	{
		if(!StringUtils.isValidString(is_actoCancelacion))
		{
			Constantes lc_c;
			lc_c = new Constantes();

			lc_c.setIdConstante(ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_CANCELACION);

			try
			{
				lc_c = irr_registroRemote.findConstante(lc_c);

				if(lc_c != null)
					is_actoCancelacion = lc_c.getCaracter();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}

		return is_actoCancelacion;
	}

	/**
	 * Modifica el valor de acto con englobe.
	 *
	 * @param ab_b asigna el valor a la propiedad acto con englobe
	 */
	public void setActoConEnglobe(boolean ab_b)
	{
		ib_actoConEnglobe = ab_b;
	}

	/**
	 * Valida la propiedad acto con englobe.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en acto con englobe
	 */
	public boolean isActoConEnglobe()
	{
		return ib_actoConEnglobe;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aa_a de aa a
	 */
	public void setAnotacion(Anotacion aa_a)
	{
		ia_anotacion = aa_a;
	}

	/**
	 *  {@inheritdoc}.
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
	 *  {@inheritdoc}.
	 *
	 * @param acrc_rc de acrc rc
	 */
	public void setAnotacionACancelar(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_anotacionACancelar = acrc_rc;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de anotacion A cancelar
	 */
	public Collection<RegistroCalificacion> getAnotacionACancelar()
	{
		return icrc_anotacionACancelar;
	}

	/**
	 * Modifica el valor de anotacion A cancelar nuevo.
	 *
	 * @param acrc_rc asigna el valor a la propiedad anotacion A cancelar nuevo
	 */
	public void setAnotacionACancelarNuevo(Collection<RegistroCalificacion> acrc_rc)
	{
		iorc_anotacionACancelarNuevo = acrc_rc;
	}

	/**
	 * Retorna el valor de anotacion A cancelar nuevo.
	 *
	 * @return el valor de anotacion A cancelar nuevo
	 */
	public Collection<RegistroCalificacion> getAnotacionACancelarNuevo()
	{
		return iorc_anotacionACancelarNuevo;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setAnotacionAgregada(boolean ab_b)
	{
		ib_anotacionAgregada = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en anotacion agregada
	 */
	public boolean isAnotacionAgregada()
	{
		return ib_anotacionAgregada;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aac_ac de aac ac
	 */
	public void setAnotacionCancelacion(AnotacionCancelacion aac_ac)
	{
		iac_anotacionCancelacion = aac_ac;
	}

	/**
	 *  {@inheritdoc}.
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
	 *  {@inheritdoc}.
	 *
	 * @param aapc_apc de aapc apc
	 */
	public void setAnotacionPredioCiudadano(AnotacionPredioCiudadano aapc_apc)
	{
		iapc_anotacionPredioCiudadano = aapc_apc;
	}

	/**
	 *  {@inheritdoc}.
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
	 * Modifica el valor de anotacion predio detalle.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio detalle
	 */
	public void setAnotacionPredioDetalle(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_ap)
	{
		iap_anotacionPredioDetalle = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio detalle.
	 *
	 * @return el valor de anotacion predio detalle
	 */
	public com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio getAnotacionPredioDetalle()
	{
		if(iap_anotacionPredioDetalle == null)
		{
			iap_anotacionPredioDetalle = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
			iap_anotacionPredioDetalle.setFechaRadicacion(new Date());
		}

		return iap_anotacionPredioDetalle;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aca_ca de aca ca
	 */
	public void setAnotacionesAgregadas(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadas = aca_ca;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de anotaciones agregadas
	 */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return ica_anotacionesAgregadas;
	}

	/**
	 * Modifica el valor de AnotacionesPredioEliminadas.
	 *
	 * @param ab_b de ab b
	 */
	public void setAnotacionesPredioEliminadas(boolean ab_b)
	{
		ib_anotacionesPredioEliminadas = ab_b;
	}

	/**
	 * Valida la propiedad anotaciones predio eliminadas.
	 *
	 * @return Retorna el valor de la propiedad anotacionesPredioEliminadas
	 */
	public boolean isAnotacionesPredioEliminadas()
	{
		return ib_anotacionesPredioEliminadas;
	}

	/**
	 * Modifica el valor de AnotacionesPredioEliminadasMatriculas.
	 *
	 * @param ac_c de ac c
	 */
	public void setAnotacionesPredioEliminadasMatriculas(Collection<AnotacionPredio> ac_c)
	{
		icap_anotacionesPredioEliminadasMatriculas = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones predio eliminadas matriculas.
	 *
	 * @return Retorna el valor de la propiedad anotacionesPredioEliminadasMatriculas
	 */
	public Collection<AnotacionPredio> getAnotacionesPredioEliminadasMatriculas()
	{
		return icap_anotacionesPredioEliminadasMatriculas;
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
	 *  {@inheritdoc}.
	 *
	 * @param aaaui_aaui de aaaui aaui
	 */
	public void setAreaUI(AccAreaUI aaaui_aaui)
	{
		iaaui_areaUI = aaaui_aaui;
	}

	/**
	 *  {@inheritdoc}.
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
	 * Modifica el valor de autoriza secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad autoriza secuencia
	 */
	public void setAutorizaSecuencia(boolean ab_b)
	{
		ib_autorizaSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad autoriza secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en autoriza secuencia
	 */
	public boolean isAutorizaSecuencia()
	{
		return ib_autorizaSecuencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setBloquearAgregarAreaTerreno(boolean ab_b)
	{
		ib_bloquearAgregarAreaTerreno = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en bloquear agregar area terreno
	 */
	public boolean isBloquearAgregarAreaTerreno()
	{
		return ib_bloquearAgregarAreaTerreno;
	}

	/**
	 * Modifica el valor de bloquear boton aprobador.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear boton aprobador
	 */
	public void setBloquearBotonAprobador(boolean ab_b)
	{
		ib_bloquearBotonAprobador = ab_b;
	}

	/**
	 * Valida la propiedad bloquear boton aprobador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear boton aprobador
	 */
	public boolean isBloquearBotonAprobador()
	{
		return ib_bloquearBotonAprobador;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setBloquearModificarIntervenientes(boolean ab_b)
	{
		ib_bloquearModificarIntervenientes = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en bloquear modificar intervenientes
	 */
	public boolean isBloquearModificarIntervenientes()
	{
		return ib_bloquearModificarIntervenientes;
	}

	/**
	 * Modifica el valor de btn regresar loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad btn regresar loteo
	 */
	public void setBtnRegresarLoteo(boolean ab_b)
	{
		ib_btnRegresarLoteo = ab_b;
	}

	/**
	 * Valida la propiedad btn regresar loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en btn regresar loteo
	 */
	public boolean isBtnRegresarLoteo()
	{
		return ib_btnRegresarLoteo;
	}

	/**
	 * Modifica el valor de btn siguiente loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad btn siguiente loteo
	 */
	public void setBtnSiguienteLoteo(boolean ab_b)
	{
		ib_btnSiguienteLoteo = ab_b;
	}

	/**
	 * Valida la propiedad btn siguiente loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en btn siguiente loteo
	 */
	public boolean isBtnSiguienteLoteo()
	{
		return ib_btnSiguienteLoteo;
	}

	/**
	 * Modifica el valor de btn terminar loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad btn terminar loteo
	 */
	public void setBtnTerminarLoteo(boolean ab_b)
	{
		ib_btnTerminarLoteo = ab_b;
	}

	/**
	 * Valida la propiedad btn terminar loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en btn terminar loteo
	 */
	public boolean isBtnTerminarLoteo()
	{
		return ib_btnTerminarLoteo;
	}

	/**
	 * Modifica el valor de cantidad anotaciones.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad anotaciones
	 */
	public void setCantidadAnotaciones(int ai_i)
	{
		ii_cantidadAnotaciones = ai_i;
	}

	/**
	 * Retorna el valor de cantidad anotaciones.
	 *
	 * @return el valor de cantidad anotaciones
	 */
	public int getCantidadAnotaciones()
	{
		return ii_cantidadAnotaciones;
	}

	/**
	 * Modifica el valor de cantidad aperturar.
	 *
	 * @param ab_b asigna el valor a la propiedad cantidad aperturar
	 */
	public void setCantidadAperturar(boolean ab_b)
	{
		ib_cantidadAperturar = ab_b;
	}

	/**
	 * Valida la propiedad cantidad aperturar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cantidad aperturar
	 */
	public boolean isCantidadAperturar()
	{
		return ib_cantidadAperturar;
	}

	/**
	 * Modifica el valor de cargar botones registro.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCargarBotonesRegistro(boolean ab_b)
	{
		ib_cargarBotonesRegistro = ab_b;
	}

	/**
	 * Retorna el valor de cargar botones registro.
	 *
	 * @return el calor de la propiedad
	 */
	public boolean isCargarBotonesRegistro()
	{
		return ib_cargarBotonesRegistro;
	}

	/**
	 * Modifica el valor de cargar lindero.
	 *
	 * @param ab_b asigna el valor a la propiedad cargar lindero
	 */
	public void setCargarLindero(boolean ab_b)
	{
		ib_cargarLindero = ab_b;
	}

	/**
	 * Valida la propiedad cargar lindero.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cargar lindero
	 */
	public boolean isCargarLindero()
	{
		return ib_cargarLindero;
	}

	/**
	 * Modifica el valor de cargue matriculas aperturar.
	 *
	 * @param ab_b asigna el valor a la propiedad cargue matriculas aperturar
	 */
	public void setCargueMatriculasAperturar(boolean ab_b)
	{
		ib_cargueMatriculasAperturar = ab_b;
	}

	/**
	 * Valida la propiedad cargue matriculas aperturar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cargue matriculas aperturar
	 */
	public boolean isCargueMatriculasAperturar()
	{
		return ib_cargueMatriculasAperturar;
	}

	/**
	 * Modifica el valor de circulo.
	 *
	 * @param as_s asigna el valor a la propiedad circulo
	 */
	public void setCirculo(String as_s)
	{
		is_circulo = as_s;
	}

	/**
	 * Retorna el valor de circulo.
	 *
	 * @return el valor de circulo
	 */
	public String getCirculo()
	{
		return is_circulo;
	}

	/**
	 * Modifica el valor de circulo direccion predio.
	 *
	 * @param as_s asigna el valor a la propiedad circulo direccion predio
	 */
	public void setCirculoDireccionPredio(String as_s)
	{
		is_circuloDireccionPredio = as_s;
	}

	/**
	 * Retorna el valor de circulo direccion predio.
	 *
	 * @return el valor de circulo direccion predio
	 */
	public String getCirculoDireccionPredio()
	{
		return is_circuloDireccionPredio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setCodigoNaturalezaJuridicaSeleccionada(String as_s)
	{
		is_codigoNaturalezaJuridicaSeleccionada = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de codigo naturaleza juridica seleccionada
	 */
	public String getCodigoNaturalezaJuridicaSeleccionada()
	{
		return is_codigoNaturalezaJuridicaSeleccionada;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param al_l de al l
	 */
	public void setContadorAnotaciones(long al_l)
	{
		il_contadorAnotaciones = al_l;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de contador anotaciones
	 */
	public long getContadorAnotaciones()
	{
		return il_contadorAnotaciones;
	}

	/**
	 * Modifica el valor de contador interviniente.
	 *
	 * @param al_l asigna el valor a la propiedad contador interviniente
	 */
	public void setContadorInterviniente(long al_l)
	{
		il_contadorInterviniente = al_l;
	}

	/**
	 * Retorna el valor de contador interviniente.
	 *
	 * @return el valor de contador interviniente
	 */
	public long getContadorInterviniente()
	{
		return il_contadorInterviniente;
	}

	/**
	 * Modifica el valor de correo electronico.
	 *
	 * @param apce_pce asigna el valor a la propiedad correo electronico
	 */
	public void setCorreoElectronico(PersonaCorreoElectronico apce_pce)
	{
		ipce_correoElectronico = apce_pce;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public PersonaCorreoElectronico getCorreoElectronico()
	{
		if(ipce_correoElectronico == null)
			ipce_correoElectronico = new PersonaCorreoElectronico();

		return ipce_correoElectronico;
	}

	/**
	 * Modifica el valor de correo electronico persona.
	 *
	 * @param apce_pce asigna el valor a la propiedad correo electronico persona
	 */
	public void setCorreoElectronicoPersona(PersonaCorreoElectronico apce_pce)
	{
		ipce_correoElectronicoPersona = apce_pce;
	}

	/**
	 * Retorna el valor de correo electronico persona.
	 *
	 * @return el valor de correo electronico persona
	 */
	public PersonaCorreoElectronico getCorreoElectronicoPersona()
	{
		if(ipce_correoElectronicoPersona == null)
			ipce_correoElectronicoPersona = new PersonaCorreoElectronico();

		return ipce_correoElectronicoPersona;
	}

	/**
	 * Modifica el valor de data direccion predio.
	 *
	 * @param aoddp_odp asigna el valor a la propiedad data direccion predio
	 */
	public void setDataDireccionPredio(DireccionDelPredio aoddp_odp)
	{
		iodp_dataDireccionPredio = aoddp_odp;
	}

	/**
	 * Retorna el valor de data direccion predio.
	 *
	 * @return el valor de data direccion predio
	 */
	public DireccionDelPredio getDataDireccionPredio()
	{
		if(iodp_dataDireccionPredio == null)
			iodp_dataDireccionPredio = new DireccionDelPredio();

		return iodp_dataDireccionPredio;
	}

	/**
	 * Modifica el valor de data info predial.
	 *
	 * @param acms_cms asigna el valor a la propiedad data info predial
	 */
	public void setDataInfoPredial(Collection<MatriculaSegregacionUi> acms_cms)
	{
		icms_dataInfoPredial = acms_cms;
	}

	/**
	 * Retorna el valor de data info predial.
	 *
	 * @return el valor de data info predial
	 */
	public Collection<MatriculaSegregacionUi> getDataInfoPredial()
	{
		return icms_dataInfoPredial;
	}

	/**
	 * Modifica el valor de data info segrec.
	 *
	 * @param aoms_ms asigna el valor a la propiedad data info segrec
	 */
	public void setDataInfoSegrec(AccAreaPredio aoms_ms)
	{
		ioms_dataInfoSegrec = aoms_ms;
	}

	/**
	 * Retorna el valor de data info segrec.
	 *
	 * @return el valor de data info segrec
	 */
	public AccAreaPredio getDataInfoSegrec()
	{
		if(ioms_dataInfoSegrec == null)
			ioms_dataInfoSegrec = new AccAreaPredio();

		return ioms_dataInfoSegrec;
	}

	/**
	 * Sets the data matriculas parciales.
	 *
	 * @param alhm_lhm correspondiente al valor del tipo de objeto Map<String,Boolean>
	 */
	public void setDataMatriculasParciales(Map<String, Boolean> alhm_lhm)
	{
		ilhm_dataMatriculasParciales = alhm_lhm;
	}

	/**
	 * Retorna el valor de data matriculas parciales.
	 *
	 * @return el valor de data matriculas parciales
	 */
	public Map<String, Boolean> getDataMatriculasParciales()
	{
		return ilhm_dataMatriculasParciales;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param adb_adb de adb adb
	 */
	public void setDataModel(Dashboard adb_adb)
	{
		idb_dataModel = adb_adb;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de data model
	 */
	public Dashboard getDataModel()
	{
		return idb_dataModel;
	}

	/**
	 * Modifica el valor de desactivar eliminar parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad desactivar eliminar parcial
	 */
	public void setDesactivarEliminarParcial(boolean ab_b)
	{
		ib_desactivarEliminarParcial = ab_b;
	}

	/**
	 * Valida la propiedad desactivar eliminar parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en desactivar eliminar parcial
	 */
	public boolean isDesactivarEliminarParcial()
	{
		return ib_desactivarEliminarParcial;
	}

	/**
	 * Modifica el valor de deshabilitar boton confirmar direccion.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar boton confirmar direccion
	 */
	public void setDeshabilitarBotonConfirmarDireccion(boolean ab_b)
	{
		ib_deshabilitarBotonConfirmarDireccion = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar boton confirmar direccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar boton confirmar direccion
	 */
	public boolean isDeshabilitarBotonConfirmarDireccion()
	{
		return ib_deshabilitarBotonConfirmarDireccion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setDeshabilitarCamposPorNit(boolean ab_b)
	{
		ib_deshabilitarCamposPorNit = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en deshabilitar campos por nit
	 */
	public boolean isDeshabilitarCamposPorNit()
	{
		return ib_deshabilitarCamposPorNit;
	}

	/**
	 * Modifica el valor de deshabilitar correo.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar correo
	 */
	public void setDeshabilitarCorreo(boolean ab_b)
	{
		ib_deshabilitarCorreo = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar correo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar correo
	 */
	public boolean isDeshabilitarCorreo()
	{
		return ib_deshabilitarCorreo;
	}

	/**
	 * Modifica el valor de deshabilitar correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar correspondencia
	 */
	public void setDeshabilitarCorrespondencia(boolean ab_b)
	{
		ib_deshabilitarCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar correspondencia
	 */
	public boolean isDeshabilitarCorrespondencia()
	{
		return ib_deshabilitarCorrespondencia;
	}

	/**
	 * Modifica el valor de deshabilitar datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar datos basicos
	 */
	public void setDeshabilitarDatosBasicos(boolean ab_b)
	{
		ib_deshabilitarDatosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar datos basicos
	 */
	public boolean isDeshabilitarDatosBasicos()
	{
		return ib_deshabilitarDatosBasicos;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setDeshabilitarDatosInterviniente(boolean ab_b)
	{
		ib_deshabilitarDatosInterviniente = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en deshabilitar datos interviniente
	 */
	public boolean isDeshabilitarDatosInterviniente()
	{
		return ib_deshabilitarDatosInterviniente;
	}

	/**
	 * Modifica el valor de deshabilitar residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar residencia
	 */
	public void setDeshabilitarResidencia(boolean ab_b)
	{
		ib_deshabilitarResidencia = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar residencia
	 */
	public boolean isDeshabilitarResidencia()
	{
		return ib_deshabilitarResidencia;
	}

	/**
	 * Modifica el valor de deshabilitar tel fijo.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tel fijo
	 */
	public void setDeshabilitarTelFijo(boolean ab_b)
	{
		ib_deshabilitarTelFijo = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tel fijo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tel fijo
	 */
	public boolean isDeshabilitarTelFijo()
	{
		return ib_deshabilitarTelFijo;
	}

	/**
	 * Modifica el valor de deshabilitar tel movil.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tel movil
	 */
	public void setDeshabilitarTelMovil(boolean ab_b)
	{
		ib_deshabilitarTelMovil = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tel movil.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tel movil
	 */
	public boolean isDeshabilitarTelMovil()
	{
		return ib_deshabilitarTelMovil;
	}

	/**
	 * Modifica el valor de deshabilitar tipo documento.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tipo documento
	 */
	public void setDeshabilitarTipoDocumento(boolean ab_b)
	{
		ib_deshabilitarTipoDocumento = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tipo documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tipo documento
	 */
	public boolean isDeshabilitarTipoDocumento()
	{
		return ib_deshabilitarTipoDocumento;
	}

	/**
	 * Modifica el valor de deshabilitar tipo documento inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tipo documento inter
	 */
	public void setDeshabilitarTipoDocumentoInter(boolean ab_b)
	{
		ib_deshabilitarTipoDocumentoInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tipo documento inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tipo documento inter
	 */
	public boolean isDeshabilitarTipoDocumentoInter()
	{
		return ib_deshabilitarTipoDocumentoInter;
	}

	/**
	 * Modifica el valor de deshabilitar tipo persona.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tipo persona
	 */
	public void setDeshabilitarTipoPersona(boolean ab_b)
	{
		ib_deshabilitarTipoPersona = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tipo persona.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tipo persona
	 */
	public boolean isDeshabilitarTipoPersona()
	{
		return ib_deshabilitarTipoPersona;
	}

	/**
	 * Modifica el valor de deshabilitar tipo persona inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tipo persona inter
	 */
	public void setDeshabilitarTipoPersonaInter(boolean ab_b)
	{
		ib_deshabilitarTipoPersonaInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tipo persona inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tipo persona inter
	 */
	public boolean isDeshabilitarTipoPersonaInter()
	{
		return ib_deshabilitarTipoPersonaInter;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aorc_rc de aorc rc
	 */
	public void setDetalleAnotacion(RegistroCalificacion aorc_rc)
	{
		iorc_detalleAnotacion = aorc_rc;
	}

	/**
	 *  {@inheritdoc}.
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
	 *  {@inheritdoc}.
	 *
	 * @param aadap_adap de aadap adap
	 */
	public void setDetalleAreaTerreno(DetalleAreaPredio aadap_adap)
	{
		iadap_detalleAreaTerreno = aadap_adap;
	}

	/**
	 *  {@inheritdoc}.
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
	 * Modifica el valor de detalle calificacion reloteo.
	 *
	 * @param arc_rc asigna el valor a la propiedad detalle calificacion reloteo
	 */
	public void setDetalleCalificacionReloteo(RegistroCalificacion arc_rc)
	{
		icr_detalleCalificacionReloteo = arc_rc;
	}

	/**
	 * Retorna el valor de detalle calificacion reloteo.
	 *
	 * @return el valor de detalle calificacion reloteo
	 */
	public RegistroCalificacion getDetalleCalificacionReloteo()
	{
		if(icr_detalleCalificacionReloteo == null)
			icr_detalleCalificacionReloteo = new RegistroCalificacion();

		return icr_detalleCalificacionReloteo;
	}

	/**
	 * Modifica el valor de detalle matricula.
	 *
	 * @param aorc_rc asigna el valor a la propiedad detalle matricula
	 */
	public void setDetalleMatricula(RegistroCalificacion aorc_rc)
	{
		iorc_detalleMatricula = aorc_rc;
	}

	/**
	 * Retorna el valor de detalle matricula.
	 *
	 * @return el valor de detalle matricula
	 */
	public RegistroCalificacion getDetalleMatricula()
	{
		if(iorc_detalleMatricula == null)
			iorc_detalleMatricula = new RegistroCalificacion();

		return iorc_detalleMatricula;
	}

	/**
	 * Modifica el valor de devolucion.
	 *
	 * @param ab_b asigna el valor a la propiedad devolucion
	 */
	public void setDevolucion(boolean ab_b)
	{
		ib_devolucion = ab_b;
	}

	/**
	 * Valida la propiedad devolucion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en devolucion
	 */
	public boolean isDevolucion()
	{
		return ib_devolucion;
	}

	/**
	 * Modifica el valor de direccion correspondencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion correspondencia
	 */
	public void setDireccionCorrespondencia(PersonaDireccion apd_pd)
	{
		ipd_direccionCorrespondencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion correspondencia.
	 *
	 * @return el valor de direccion correspondencia
	 */
	public PersonaDireccion getDireccionCorrespondencia()
	{
		if(ipd_direccionCorrespondencia == null)
		{
			ipd_direccionCorrespondencia = new PersonaDireccion();
			ipd_direccionCorrespondencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionCorrespondencia.setTipoDireccion("C");
		}

		return ipd_direccionCorrespondencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param iddp_d de iddp d
	 */
	public void setDireccionPredio(DireccionDelPredio iddp_d)
	{
		idp_direccionPredio = iddp_d;
	}

	/**
	 *  {@inheritdoc}.
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
	 * Modifica el valor de direccion residencia.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion residencia
	 */
	public void setDireccionResidencia(PersonaDireccion apd_pd)
	{
		ipd_direccionResidencia = apd_pd;
	}

	/**
	 * Retorna el valor de direccion residencia.
	 *
	 * @return el valor de direccion residencia
	 */
	public PersonaDireccion getDireccionResidencia()
	{
		if(ipd_direccionResidencia == null)
		{
			ipd_direccionResidencia = new PersonaDireccion();
			ipd_direccionResidencia.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionResidencia.setTipoDireccion("R");
		}

		return ipd_direccionResidencia;
	}

	/**
	 * Modifica el valor de division material.
	 *
	 * @param ab_b asigna el valor a la propiedad division material
	 */
	public void setDivisionMaterial(boolean ab_b)
	{
		ib_divisionMaterial = ab_b;
	}

	/**
	 * Valida la propiedad division material.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en division material
	 */
	public boolean isDivisionMaterial()
	{
		return ib_divisionMaterial;
	}

	/**
	 * Modifica el valor de documento detalle.
	 *
	 * @param ad_d asigna el valor a la propiedad documento detalle
	 */
	public void setDocumentoDetalle(Documento ad_d)
	{
		id_documentoDetalle = ad_d;
	}

	/**
	 * Retorna el valor de documento detalle.
	 *
	 * @return el valor de documento detalle
	 */
	public Documento getDocumentoDetalle()
	{
		return id_documentoDetalle;
	}

	/**
	 * Modifica el valor de documento interesado.
	 *
	 * @param as_s asigna el valor a la propiedad documento interesado
	 */
	public void setDocumentoInteresado(String as_s)
	{
		is_documentoInteresado = as_s;
	}

	/**
	 * Retorna el valor de documento interesado.
	 *
	 * @return el valor de documento interesado
	 */
	public String getDocumentoInteresado()
	{
		return is_documentoInteresado;
	}

	/**
	 * Modifica el valor de editar datos oficina medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad editar datos oficina medida cautelar
	 */
	public void setEditarDatosOficinaMedidaCautelar(boolean ab_b)
	{
		ib_editarDatosOficinaMedidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad editar datos oficina medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar datos oficina medida cautelar
	 */
	public boolean isEditarDatosOficinaMedidaCautelar()
	{
		return ib_editarDatosOficinaMedidaCautelar;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setEditarInterviniente(boolean ab_b)
	{
		ib_editarInterviniente = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en editar interviniente
	 */
	public boolean isEditarInterviniente()
	{
		return ib_editarInterviniente;
	}

	/**
	 * Modifica el valor de eliminar anotacion.
	 *
	 * @param aea_ea asigna el valor a la propiedad eliminar anotacion
	 */
	public void setEliminarAnotacion(EliminarAnotacion aea_ea)
	{
		iea_eliminarAnotacion = aea_ea;
	}

	/**
	 * Retorna el valor de eliminar anotacion.
	 *
	 * @return el valor de eliminar anotacion
	 */
	public EliminarAnotacion getEliminarAnotacion()
	{
		return iea_eliminarAnotacion;
	}

	/**
	 * Modifica el valor de encabezado reloteo.
	 *
	 * @param as_s asigna el valor a la propiedad encabezado reloteo
	 */
	public void setEncabezadoReloteo(String as_s)
	{
		is_encabezadoReloteo = as_s;
	}

	/**
	 * Retorna el valor de encabezado reloteo.
	 *
	 * @return el valor de encabezado reloteo
	 */
	public String getEncabezadoReloteo()
	{
		return is_encabezadoReloteo;
	}

	/**
	 * Modifica el valor de EsHomologacionActosLiquidacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_esHomologacionActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad es homologacion actos liquidacion.
	 *
	 * @return Retorna el valor de la propiedad esHomologacionActosLiquidacion
	 */
	public boolean isEsHomologacionActosLiquidacion()
	{
		return ib_esHomologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de notificacion residencia o correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad notificacion residencia.
	 */
	public void setEsNotificacionCorrespondencia(boolean ab_b)
	{
		ib_esNotificacionCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad es notificacion correspondencia.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en es notificacion correspondencia
	 */
	public boolean isEsNotificacionCorrespondencia()
	{
		return ib_esNotificacionCorrespondencia;
	}

	/**
	 * Modifica el valor de notificacion residencia o correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad notificacion residencia.
	 */
	public void setEsNotificacionResidencia(boolean ab_b)
	{
		ib_esNotificacionResidencia = ab_b;
	}

	/**
	 * Valida la propiedad notificacion correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en notificacion correspondencia.
	 */
	public boolean isEsNotificacionResidencia()
	{
		return ib_esNotificacionResidencia;
	}

	/**
	 * Modifica el valor de estado matricula.
	 *
	 * @param as_s asigna el valor a la propiedad estado matricula
	 */
	public void setEstadoMatricula(String as_s)
	{
		is_estadoMatricula = as_s;
	}

	/**
	 * Retorna el valor de estado matricula.
	 *
	 * @return el valor de estado matricula
	 */
	public String getEstadoMatricula()
	{
		return is_estadoMatricula;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param asc_file de asc file
	 */
	public void setFile(StreamedContent asc_file)
	{
		isc_file = asc_file;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de file
	 */
	public StreamedContent getFile()
	{
		return isc_file;
	}

	/**
	 * Modifica el valor de file cancelacion.
	 *
	 * @param asc_sc asigna el valor a la propiedad file cancelacion
	 */
	public void setFileCancelacion(StreamedContent asc_sc)
	{
		iosc_fileCancelacion = asc_sc;
	}

	/**
	 * Retorna el valor de file cancelacion.
	 *
	 * @return el valor de file cancelacion
	 */
	public StreamedContent getFileCancelacion()
	{
		return iosc_fileCancelacion;
	}

	/**
	 * Modifica el valor de file generado.
	 *
	 * @param ab_b asigna el valor a la propiedad file generado
	 */
	public void setFileGenerado(boolean ab_b)
	{
		ib_fileGenerado = ab_b;
	}

	/**
	 * Valida la propiedad file generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en file generado
	 */
	public boolean isFileGenerado()
	{
		return ib_fileGenerado;
	}

	/**
	 * Modifica el valor de file nota informativa.
	 *
	 * @param asc_sc asigna el valor a la propiedad file nota informativa
	 */
	public void setFileNotaInformativa(StreamedContent asc_sc)
	{
		isc_fileNotaInformativa = asc_sc;
	}

	/**
	 * Retorna el valor de file nota informativa.
	 *
	 * @return el valor de file nota informativa
	 */
	public StreamedContent getFileNotaInformativa()
	{
		return isc_fileNotaInformativa;
	}

	/**
	 * Modifica el valor de file predial.
	 *
	 * @param asc_sc asigna el valor a la propiedad file predial
	 */
	public void setFilePredial(StreamedContent asc_sc)
	{
		isc_filePredial = asc_sc;
	}

	/**
	 * Retorna el valor de file predial.
	 *
	 * @return el valor de file predial
	 */
	public StreamedContent getFilePredial()
	{
		return isc_filePredial;
	}

	/**
	 * Modifica el valor de FlujoDigitador.
	 *
	 * @param ab_b de ab b
	 */
	public void setFlujoDigitador(boolean ab_b)
	{
		ib_flujoDigitador = ab_b;
	}

	/**
	 * Valida la propiedad flujo digitador.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en flujo digitador
	 */
	public boolean isFlujoDigitador()
	{
		return ib_flujoDigitador;
	}

	/**
	 * Modifica el valor de GeneroComunicadoDireccion.
	 *
	 * @param ab_b de ab b
	 */
	public void setGeneroComunicadoDireccion(boolean ab_b)
	{
		ib_generoComunicadoDireccion = ab_b;
	}

	/**
	 * Valida la propiedad genero comunicado direccion.
	 *
	 * @return Retorna el valor de la propiedad generoComunicadoDireccion
	 */
	public boolean isGeneroComunicadoDireccion()
	{
		return ib_generoComunicadoDireccion;
	}

	/**
	 * Modifica el valor de GeneroNotaInformativaPagoExceso.
	 *
	 * @param ab_b de ab b
	 */
	public void setGeneroNotaInformativaPagoExceso(boolean ab_b)
	{
		ib_generoNotaInformativaPagoExceso = ab_b;
	}

	/**
	 * Valida la propiedad genero nota informativa pago exceso.
	 *
	 * @return Retorna el valor de la propiedad generoNotaInformativaPagoExceso
	 */
	public boolean isGeneroNotaInformativaPagoExceso()
	{
		return ib_generoNotaInformativaPagoExceso;
	}

	/**
	 * Modifica el valor de habilita secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad habilita secuencia
	 */
	public void setHabilitaSecuencia(boolean ab_b)
	{
		ib_habilitaSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad habilita secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilita secuencia
	 */
	public boolean isHabilitaSecuencia()
	{
		return ib_habilitaSecuencia;
	}

	/**
	 * Modifica el valor de habilitar correo co.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar correo co
	 */
	public void setHabilitarCorreoCo(boolean ab_b)
	{
		ib_habilitarCorreoCo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar correo co.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar correo co
	 */
	public boolean isHabilitarCorreoCo()
	{
		return ib_habilitarCorreoCo;
	}

	/**
	 * Modifica el valor de habilitar correo no.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar correo no
	 */
	public void setHabilitarCorreoNo(boolean ab_b)
	{
		ib_habilitarCorreoNo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar correo no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar correo no
	 */
	public boolean isHabilitarCorreoNo()
	{
		return ib_habilitarCorreoNo;
	}

	/**
	 * Modifica el valor de habilitar digitador.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar digitador
	 */
	public void setHabilitarDigitador(boolean ab_b)
	{
		ib_habilitarDigitador = ab_b;
	}

	/**
	 * Valida la propiedad habilitar digitador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar digitador
	 */
	public boolean isHabilitarDigitador()
	{
		return ib_habilitarDigitador;
	}

	/**
	 * Modifica el valor de habilitar direccion co co.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar direccion co co
	 */
	public void setHabilitarDireccionCoCo(boolean ab_b)
	{
		ib_habilitarDireccionCoCo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar direccion co co.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar direccion co co
	 */
	public boolean isHabilitarDireccionCoCo()
	{
		return ib_habilitarDireccionCoCo;
	}

	/**
	 * Modifica el valor de habilitar direccion co no.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar direccion co no
	 */
	public void setHabilitarDireccionCoNo(boolean ab_b)
	{
		ib_habilitarDireccionCoNo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar direccion co no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar direccion co no
	 */
	public boolean isHabilitarDireccionCoNo()
	{
		return ib_habilitarDireccionCoNo;
	}

	/**
	 * Modifica el valor de habilitar direccion re co.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar direccion re co
	 */
	public void setHabilitarDireccionReCo(boolean ab_b)
	{
		ib_habilitarDireccionReCo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar direccion re co.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar direccion re co
	 */
	public boolean isHabilitarDireccionReCo()
	{
		return ib_habilitarDireccionReCo;
	}

	/**
	 * Modifica el valor de habilitar direccion re no.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar direccion re no
	 */
	public void setHabilitarDireccionReNo(boolean ab_b)
	{
		ib_habilitarDireccionReNo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar direccion re no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar direccion re no
	 */
	public boolean isHabilitarDireccionReNo()
	{
		return ib_habilitarDireccionReNo;
	}

	/**
	 * Modifica el valor de habilitar guardar informacion.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar guardar informacion
	 */
	public void setHabilitarGuardarInformacion(boolean ab_b)
	{
		ib_habilitarGuardarInformacion = ab_b;
	}

	/**
	 * Valida la propiedad habilitar guardar informacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar guardar informacion
	 */
	public boolean isHabilitarGuardarInformacion()
	{
		return ib_habilitarGuardarInformacion;
	}

	/**
	 * Modifica el valor de habilitar medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar medida cautelar
	 */
	public void setHabilitarMedidaCautelar(boolean ab_b)
	{
		ib_habilitarMedidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad habilitar medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar medida cautelar
	 */
	public boolean isHabilitarMedidaCautelar()
	{
		return ib_habilitarMedidaCautelar;
	}

	/**
	 * Modifica el valor de habilitar tabs.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tabs
	 */
	public void setHabilitarTabs(boolean ab_b)
	{
		ib_habilitarTabs = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tabs.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tabs
	 */
	public boolean isHabilitarTabs()
	{
		return ib_habilitarTabs;
	}

	/**
	 * Modifica el valor de habilitar tel fijo co.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tel fijo co
	 */
	public void setHabilitarTelFijoCo(boolean ab_b)
	{
		ib_habilitarTelFijoCo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tel fijo co.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tel fijo co
	 */
	public boolean isHabilitarTelFijoCo()
	{
		return ib_habilitarTelFijoCo;
	}

	/**
	 * Modifica el valor de habilitar tel fijo no.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tel fijo no
	 */
	public void setHabilitarTelFijoNo(boolean ab_b)
	{
		ib_habilitarTelFijoNo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tel fijo no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tel fijo no
	 */
	public boolean isHabilitarTelFijoNo()
	{
		return ib_habilitarTelFijoNo;
	}

	/**
	 * Modifica el valor de habilitar tel movil co.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tel movil co
	 */
	public void setHabilitarTelMovilCo(boolean ab_b)
	{
		ib_habilitarTelMovilCo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tel movil co.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tel movil co
	 */
	public boolean isHabilitarTelMovilCo()
	{
		return ib_habilitarTelMovilCo;
	}

	/**
	 * Modifica el valor de habilitar tel movil no.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar tel movil no
	 */
	public void setHabilitarTelMovilNo(boolean ab_b)
	{
		ib_habilitarTelMovilNo = ab_b;
	}

	/**
	 * Valida la propiedad habilitar tel movil no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar tel movil no
	 */
	public boolean isHabilitarTelMovilNo()
	{
		return ib_habilitarTelMovilNo;
	}

	/**
	 * Modifica el valor de hay interviniente con secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad hay interviniente con secuencia
	 */
	public void setHayIntervinienteConSecuencia(boolean ab_b)
	{
		is_hayIntervinienteConSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad hay interviniente con secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en hay interviniente con secuencia
	 */
	public boolean isHayIntervinienteConSecuencia()
	{
		return is_hayIntervinienteConSecuencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param al_l de al l
	 */
	public void setIdAnotacion(long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de id anotacion
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param al_l de al l
	 */
	public void setIdAnotacionGeneral(long al_l)
	{
		il_idAnotacionGeneral = al_l;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de id anotacion general
	 */
	public long getIdAnotacionGeneral()
	{
		return il_idAnotacionGeneral;
	}

	/**
	 * Modifica el valor de id anotacion predio seleccionado.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion predio seleccionado
	 */
	public void setIdAnotacionPredioSeleccionado(String as_s)
	{
		is_idAnotacionPredioSeleccionado = as_s;
	}

	/**
	 * Retorna el valor de id anotacion predio seleccionado.
	 *
	 * @return el valor de id anotacion predio seleccionado
	 */
	public String getIdAnotacionPredioSeleccionado()
	{
		return is_idAnotacionPredioSeleccionado;
	}

	/**
	 * Modifica el valor de id correo seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id correo seleccion
	 */
	public void setIdCorreoSeleccion(String as_s)
	{
		is_idCorreoSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id correo seleccion.
	 *
	 * @return el valor de id correo seleccion
	 */
	public String getIdCorreoSeleccion()
	{
		return is_idCorreoSeleccion;
	}

	/**
	 * Modifica el valor de id dir corr seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id dir corr seleccion
	 */
	public void setIdDirCorrSeleccion(String as_s)
	{
		is_idDirCorrSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id dir corr seleccion.
	 *
	 * @return el valor de id dir corr seleccion
	 */
	public String getIdDirCorrSeleccion()
	{
		return is_idDirCorrSeleccion;
	}

	/**
	 * Modifica el valor de id dir res seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id dir res seleccion
	 */
	public void setIdDirResSeleccion(String as_s)
	{
		is_idDirResSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id dir res seleccion.
	 *
	 * @return el valor de id dir res seleccion
	 */
	public String getIdDirResSeleccion()
	{
		return is_idDirResSeleccion;
	}

	/**
	 * Modifica el valor de id naturaleza juridica inicial.
	 *
	 * @param as_s asigna el valor a la propiedad id naturaleza juridica inicial
	 */
	public void setIdNaturalezaJuridicaInicial(String as_s)
	{
		is_idNaturalezaJuridicaInicial = as_s;
	}

	/**
	 * Retorna el valor de id naturaleza juridica inicial.
	 *
	 * @return el valor de id naturaleza juridica inicial
	 */
	public String getIdNaturalezaJuridicaInicial()
	{
		return is_idNaturalezaJuridicaInicial;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setIdPersonaSeleccion(String as_s)
	{
		is_idPersonaSeleccion = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de id persona seleccion
	 */
	public String getIdPersonaSeleccion()
	{
		return is_idPersonaSeleccion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id tel fijo seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id tel fijo seleccion
	 */
	public void setIdTelFijoSeleccion(String as_s)
	{
		is_idTelFijoSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id tel fijo seleccion.
	 *
	 * @return el valor de id tel fijo seleccion
	 */
	public String getIdTelFijoSeleccion()
	{
		return is_idTelFijoSeleccion;
	}

	/**
	 * Modifica el valor de id tel mov seleccion.
	 *
	 * @param as_s asigna el valor a la propiedad id tel mov seleccion
	 */
	public void setIdTelMovSeleccion(String as_s)
	{
		is_idTelMovSeleccion = as_s;
	}

	/**
	 * Retorna el valor de id tel mov seleccion.
	 *
	 * @return el valor de id tel mov seleccion
	 */
	public String getIdTelMovSeleccion()
	{
		return is_idTelMovSeleccion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param asc_sc de asc sc
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de ImagenComunicado.
	 *
	 * @param as_s de as s
	 */
	public void setImagenComunicado(StreamedContent as_s)
	{
		isc_imagenComunicado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor imagen comunicado.
	 *
	 * @return Retorna el valor de la propiedad isc_imagenComunicado
	 */
	public StreamedContent getImagenComunicado()
	{
		return isc_imagenComunicado;
	}

	/**
	 * Modifica el valor de ImagenComunicadoDireccion.
	 *
	 * @param as_s de as s
	 */
	public void setImagenComunicadoDireccion(StreamedContent as_s)
	{
		isc_imagenComunicadoDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor imagen comunicado direccion.
	 *
	 * @return Retorna el valor de la propiedad imagenComunicadoDireccion
	 */
	public StreamedContent getImagenComunicadoDireccion()
	{
		return isc_imagenComunicadoDireccion;
	}

	/**
	 * Modifica el valor de ImagenNotaExcesoPago.
	 *
	 * @param as_s de as s
	 */
	public void setImagenNotaExcesoPago(StreamedContent as_s)
	{
		isc_imagenNotaExcesoPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor imagen nota exceso pago.
	 *
	 * @return Retorna el valor de la propiedad imagenNotaExcesoPago
	 */
	public StreamedContent getImagenNotaExcesoPago()
	{
		return isc_imagenNotaExcesoPago;
	}

	/**
	 * Modifica el valor de ind vinculado.
	 *
	 * @param ab_b asigna el valor a la propiedad ind vinculado
	 */
	public void setIndVinculado(boolean ab_b)
	{
		ib_indVinculado = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind vinculado
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * Modifica el valor de info matricula A copiar.
	 *
	 * @param acrc_rc asigna el valor a la propiedad info matricula A copiar
	 */
	public void setInfoMatriculaACopiar(Collection<RegistroCalificacion> acrc_rc)
	{
		icrc_infoMatriculaACopiar = acrc_rc;
	}

	/**
	 * Retorna el valor de info matricula A copiar.
	 *
	 * @return el valor de info matricula A copiar
	 */
	public Collection<RegistroCalificacion> getInfoMatriculaACopiar()
	{
		return icrc_infoMatriculaACopiar;
	}

	/**
	 * Modifica el valor de info segregacion manual.
	 *
	 * @param amsui_msui asigna el valor a la propiedad info segregacion manual
	 */
	public void setInfoSegregacionManual(MatriculaSegregacionUi amsui_msui)
	{
		ismui_infoSegregacionManual = amsui_msui;
	}

	/**
	 * Retorna el valor de info segregacion manual.
	 *
	 * @return el valor de info segregacion manual
	 */
	public MatriculaSegregacionUi getInfoSegregacionManual()
	{
		if(ismui_infoSegregacionManual == null)
		{
			ismui_infoSegregacionManual = new MatriculaSegregacionUi();
			ismui_infoSegregacionManual.setUnidad(NumericUtils.getLongWrapper(1L));
		}

		return ismui_infoSegregacionManual;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aa_a de aa a
	 */
	public void setIntervinienteActual(Anotacion aa_a)
	{
		ia_intervinienteActual = aa_a;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de interviniente actual
	 */
	public Anotacion getIntervinienteActual()
	{
		return ia_intervinienteActual;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aca_ca de aca ca
	 */
	public void setIntervinientesAgregados(Collection<Anotacion> aca_ca)
	{
		ica_intervinientesAgregados = aca_ca;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de intervinientes agregados
	 */
	public Collection<Anotacion> getIntervinientesAgregados()
	{
		return ica_intervinientesAgregados;
	}

	/**
	 * Modifica el valor de justificacion cierre folio.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion cierre folio
	 */
	public void setJustificacionCierreFolio(String as_s)
	{
		is_justificacionCierreFolio = as_s;
	}

	/**
	 * Retorna el valor de justificacion cierre folio.
	 *
	 * @return el valor de justificacion cierre folio
	 */
	public String getJustificacionCierreFolio()
	{
		return is_justificacionCierreFolio;
	}

	/**
	 * Modifica el valor de lindero venta no cementerio.
	 *
	 * @param alrc_lrc asigna el valor a la propiedad lindero venta no cementerio
	 */
	public void setLinderoVentaNoCementerio(AccLinderoPredio alrc_lrc)
	{
		ialp_linderoVentaNoCementerio = alrc_lrc;
	}

	/**
	 * Retorna el valor de lindero venta no cementerio.
	 *
	 * @return el valor de lindero venta no cementerio
	 */
	public AccLinderoPredio getLinderoVentaNoCementerio()
	{
		if(ialp_linderoVentaNoCementerio == null)
			ialp_linderoVentaNoCementerio = new AccLinderoPredio();

		return ialp_linderoVentaNoCementerio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param acp_cp de acp cp
	 */
	public void setListadoIntervinientes(Collection<Persona> acp_cp)
	{
		icp_listadoIntervinientes = acp_cp;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de listado intervinientes
	 */
	public Collection<Persona> getListadoIntervinientes()
	{
		return icp_listadoIntervinientes;
	}

	/**
	 * Modifica el valor de manual matriculas aperturar.
	 *
	 * @param ab_b asigna el valor a la propiedad manual matriculas aperturar
	 */
	public void setManualMatriculasAperturar(boolean ab_b)
	{
		ib_manualMatriculasAperturar = ab_b;
	}

	/**
	 * Valida la propiedad manual matriculas aperturar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en manual matriculas aperturar
	 */
	public boolean isManualMatriculasAperturar()
	{
		return ib_manualMatriculasAperturar;
	}

	/**
	 * Modifica el valor de matricula detalle reloteo.
	 *
	 * @param as_s asigna el valor a la propiedad matricula detalle reloteo
	 */
	public void setMatriculaDetalleReloteo(String as_s)
	{
		is_matriculaDetalleReloteo = as_s;
	}

	/**
	 * Retorna el valor de matricula detalle reloteo.
	 *
	 * @return el valor de matricula detalle reloteo
	 */
	public String getMatriculaDetalleReloteo()
	{
		return is_matriculaDetalleReloteo;
	}

	/**
	 * Modifica el valor de matricula direccion predio.
	 *
	 * @param al_l asigna el valor a la propiedad matricula direccion predio
	 */
	public void setMatriculaDireccionPredio(Long al_l)
	{
		il_matriculaDireccionPredio = al_l;
	}

	/**
	 * Retorna el valor de matricula direccion predio.
	 *
	 * @return el valor de matricula direccion predio
	 */
	public Long getMatriculaDireccionPredio()
	{
		return il_matriculaDireccionPredio;
	}

	/**
	 * Sets the matriculas aperturar.
	 *
	 * @param al_l correspondiente al valor del tipo de objeto Map<String,Long>
	 */
	public void setMatriculasAperturar(Map<String, Long> al_l)
	{
		imsl_matriculasAperturar = al_l;
	}

	/**
	 * Retorna el valor de matriculas aperturar.
	 *
	 * @return el valor de matriculas aperturar
	 */
	public Map<String, Long> getMatriculasAperturar()
	{
		if(imsl_matriculasAperturar == null)
			imsl_matriculasAperturar = new HashMap<String, Long>();

		return imsl_matriculasAperturar;
	}

	/**
	 * Modifica el valor de matriculas informacion.
	 *
	 * @param acap_ap asigna el valor a la propiedad matriculas informacion
	 */
	public void setMatriculasInformacion(Collection<AreaPredio> acap_ap)
	{
		icap_matriculasInformacion = acap_ap;
	}

	/**
	 * Retorna el valor de matriculas informacion.
	 *
	 * @return el valor de matriculas informacion
	 */
	public Collection<AreaPredio> getMatriculasInformacion()
	{
		return icap_matriculasInformacion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param lca_ca de lca ca
	 */
	public void setMatriculasSegregadas(Collection<Anotacion> lca_ca)
	{
		ica_matriculasSegregadas = lca_ca;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de matriculas segregadas
	 */
	public Collection<Anotacion> getMatriculasSegregadas()
	{
		return ica_matriculasSegregadas;
	}

	/**
	 * Modifica el valor de medios comunicar.
	 *
	 * @param actr_ctr asigna el valor a la propiedad medios comunicar
	 */
	public void setMediosComunicar(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_mediosComunicar = actr_ctr;
	}

	/**
	 * Retorna el valor de medios comunicar.
	 *
	 * @return el valor de medios comunicar
	 */
	public Collection<TipoRecepcion> getMediosComunicar()
	{
		return ictr_mediosComunicar;
	}

	/**
	 * Modifica el valor de medios notificar.
	 *
	 * @param actr_ctr asigna el valor a la propiedad medios notificar
	 */
	public void setMediosNotificar(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_mediosNotificar = actr_ctr;
	}

	/**
	 * Retorna el valor de medios notificar.
	 *
	 * @return el valor de medios notificar
	 */
	public Collection<TipoRecepcion> getMediosNotificar()
	{
		return ictr_mediosNotificar;
	}

	/**
	 * Modifica el valor de misma direccion correspondencia.
	 *
	 * @param as_s asigna el valor a la propiedad misma direccion correspondencia
	 */
	public void setMismaDireccionCorrespondencia(String as_s)
	{
		is_mismaDireccionCorrespondencia = as_s;
	}

	/**
	 * Retorna el valor de misma direccion correspondencia.
	 *
	 * @return el valor de misma direccion correspondencia
	 */
	public String getMismaDireccionCorrespondencia()
	{
		return is_mismaDireccionCorrespondencia;
	}

	/**
	 * Modifica el valor de modificar area segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar area segregacion
	 */
	public void setModificarAreaSegregacion(boolean ab_b)
	{
		ib_modificarAreaSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad modificar area segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar area segregacion
	 */
	public boolean isModificarAreaSegregacion()
	{
		return ib_modificarAreaSegregacion;
	}

	/**
	 * Modifica el valor de modificar direccion predio.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar direccion predio
	 */
	public void setModificarDireccionPredio(boolean ab_b)
	{
		ib_modificarDireccionPredio = ab_b;
	}

	/**
	 * Valida la propiedad modificar direccion predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar direccion predio
	 */
	public boolean isModificarDireccionPredio()
	{
		return ib_modificarDireccionPredio;
	}

	/**
	 * Modifica el valor de modificar Modificar Informacion Notificacion.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar Modificar Informacion Notificacion
	 */
	public void setModificarInformacionNotificacion(boolean ab_b)
	{
		ib_modificarInformacionNotificacion = ab_b;
	}

	/**
	 * Valida la propiedad modificar Modificar Informacion Notificacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar Modificar Informacion Notificacion
	 */
	public boolean isModificarInformacionNotificacion()
	{
		return ib_modificarInformacionNotificacion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarAnotacionCancela(boolean ab_b)
	{
		ib_mostrarAnotacionCancela = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en mostrar anotacion cancela
	 */
	public boolean isMostrarAnotacionCancela()
	{
		return ib_mostrarAnotacionCancela;
	}

	/**
	 * Modifica el valor de mostrar correo.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar correo
	 */
	public void setMostrarCorreo(boolean ab_b)
	{
		ib_mostrarCorreo = ab_b;
	}

	/**
	 * Valida la propiedad mostrar correo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar correo
	 */
	public boolean isMostrarCorreo()
	{
		return ib_mostrarCorreo;
	}

	/**
	 * Modifica el valor de mostrar datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar datos basicos
	 */
	public void setMostrarDatosBasicos(boolean ab_b)
	{
		ib_mostrarDatosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar datos basicos
	 */
	public boolean isMostrarDatosBasicos()
	{
		return ib_mostrarDatosBasicos;
	}

	/**
	 * Modifica el valor de mostrar datos consulta.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar datos consulta
	 */
	public void setMostrarDatosConsulta(boolean ab_b)
	{
		ib_mostrarDatosConsulta = ab_b;
	}

	/**
	 * Valida la propiedad mostrar datos consulta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar datos consulta
	 */
	public boolean isMostrarDatosConsulta()
	{
		return ib_mostrarDatosConsulta;
	}

	/**
	 * Modifica el valor de mostrar direcciones correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar direcciones correspondencia
	 */
	public void setMostrarDireccionesCorrespondencia(boolean ab_b)
	{
		ib_mostrarDireccionesCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad mostrar direcciones correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar direcciones correspondencia
	 */
	public boolean isMostrarDireccionesCorrespondencia()
	{
		return ib_mostrarDireccionesCorrespondencia;
	}

	/**
	 * Modifica el valor de mostrar direcciones residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar direcciones residencia
	 */
	public void setMostrarDireccionesResidencia(boolean ab_b)
	{
		ib_mostrarDireccionesResidencia = ab_b;
	}

	/**
	 * Valida la propiedad mostrar direcciones residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar direcciones residencia
	 */
	public boolean isMostrarDireccionesResidencia()
	{
		return ib_mostrarDireccionesResidencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarListadoPersonas(boolean ab_b)
	{
		ib_mostrarListadoPersonas = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en mostrar listado personas
	 */
	public boolean isMostrarListadoPersonas()
	{
		return ib_mostrarListadoPersonas;
	}

	/**
	 * Modifica el valor de mostrar regresar loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar regresar loteo
	 */
	public void setMostrarRegresarLoteo(boolean ab_b)
	{
		ib_mostrarRegresarLoteo = ab_b;
	}

	/**
	 * Valida la propiedad mostrar regresar loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar regresar loteo
	 */
	public boolean isMostrarRegresarLoteo()
	{
		return ib_mostrarRegresarLoteo;
	}

	/**
	 * Modifica el valor de mostrar regresar venta.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar regresar venta
	 */
	public void setMostrarRegresarVenta(boolean ab_b)
	{
		ib_mostrarRegresarVenta = ab_b;
	}

	/**
	 * Valida la propiedad mostrar regresar venta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar regresar venta
	 */
	public boolean isMostrarRegresarVenta()
	{
		return ib_mostrarRegresarVenta;
	}

	/**
	 * Modifica el valor de mostrar siguiente loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar siguiente loteo
	 */
	public void setMostrarSiguienteLoteo(boolean ab_b)
	{
		ib_mostrarSiguienteLoteo = ab_b;
	}

	/**
	 * Valida la propiedad mostrar siguiente loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar siguiente loteo
	 */
	public boolean isMostrarSiguienteLoteo()
	{
		return ib_mostrarSiguienteLoteo;
	}

	/**
	 * Modifica el valor de mostrar siguiente venta.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar siguiente venta
	 */
	public void setMostrarSiguienteVenta(boolean ab_b)
	{
		ib_mostrarSiguienteVenta = ab_b;
	}

	/**
	 * Valida la propiedad mostrar siguiente venta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar siguiente venta
	 */
	public boolean isMostrarSiguienteVenta()
	{
		return ib_mostrarSiguienteVenta;
	}

	/**
	 * Modifica el valor de mostrar telefono fijo.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar telefono fijo
	 */
	public void setMostrarTelefonoFijo(boolean ab_b)
	{
		ib_mostrarTelefonoFijo = ab_b;
	}

	/**
	 * Valida la propiedad mostrar telefono fijo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar telefono fijo
	 */
	public boolean isMostrarTelefonoFijo()
	{
		return ib_mostrarTelefonoFijo;
	}

	/**
	 * Modifica el valor de mostrar telefono movil.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar telefono movil
	 */
	public void setMostrarTelefonoMovil(boolean ab_b)
	{
		ib_mostrarTelefonoMovil = ab_b;
	}

	/**
	 * Valida la propiedad mostrar telefono movil.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar telefono movil
	 */
	public boolean isMostrarTelefonoMovil()
	{
		return ib_mostrarTelefonoMovil;
	}

	/**
	 * Modifica el valor de motivo.
	 *
	 * @param as_s asigna el valor a la propiedad motivo
	 */
	public void setMotivo(String as_s)
	{
		is_motivo = as_s;
	}

	/**
	 * Retorna el valor de motivo.
	 *
	 * @return el valor de motivo
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/**
	 * Modifica el valor de msj copiar anotaciones.
	 *
	 * @param as_s asigna el valor a la propiedad msj copiar anotaciones
	 */
	public void setMsjCopiarAnotaciones(String as_s)
	{
		is_msjCopiarAnotaciones = as_s;
	}

	/**
	 * Retorna el valor de msj copiar anotaciones.
	 *
	 * @return el valor de msj copiar anotaciones
	 */
	public String getMsjCopiarAnotaciones()
	{
		return is_msjCopiarAnotaciones;
	}

	/**
	 * Modifica el valor de naturaleza juridica antigua.
	 *
	 * @param as_s de as s
	 */
	public void setNaturalezaJuridicaAntigua(String as_s)
	{
		is_naturalezaJuridicaAntigua = as_s;
	}

	/**
	 * Valida la propiedad naturaleza juridica antigua.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en naturaleza juridica antigua.
	 */
	public String getNaturalezaJuridicaAntigua()
	{
		return is_naturalezaJuridicaAntigua;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setNaturalezaJuridicaSeleccionada(String as_s)
	{
		is_naturalezaJuridicaSeleccionada = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de naturaleza juridica seleccionada
	 */
	public String getNaturalezaJuridicaSeleccionada()
	{
		return is_naturalezaJuridicaSeleccionada;
	}

	/**
	 * Modifica el valor de nueva linderos.
	 *
	 * @param ab_b asigna el valor a la propiedad nueva linderos
	 */
	public void setNuevaLinderos(boolean ab_b)
	{
		ib_nuevaLinderos = ab_b;
	}

	/**
	 * Valida la propiedad nueva linderos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nueva linderos
	 */
	public boolean isNuevaLinderos()
	{
		return ib_nuevaLinderos;
	}

	/**
	 * Modifica el valor de nueva persona.
	 *
	 * @param ab_b asigna el valor a la propiedad nueva persona
	 */
	public void setNuevaPersona(boolean ab_b)
	{
		ib_nuevaPersona = ab_b;
	}

	/**
	 * Valida la propiedad nueva persona.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nueva persona
	 */
	public boolean isNuevaPersona()
	{
		return ib_nuevaPersona;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param al_l de al l
	 */
	public void setNumeroAnotacion(Long al_l)
	{
		il_numeroAnotacion = al_l;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de numero anotacion
	 */
	public Long getNumeroAnotacion()
	{
		return il_numeroAnotacion;
	}

	/**
	 * Modifica el valor de numero proceso.
	 *
	 * @param as_s asigna el valor a la propiedad numero proceso
	 */
	public void setNumeroProceso(String as_s)
	{
		is_numeroProceso = as_s;
	}

	/**
	 * Retorna el valor de numero proceso.
	 *
	 * @return el valor de numero proceso
	 */
	public String getNumeroProceso()
	{
		return is_numeroProceso;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_s de as s
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de observaciones nota info.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones nota info
	 */
	public void setObservacionesNotaInfo(String as_s)
	{
		is_observacionesNotaInfo = as_s;
	}

	/**
	 * Retorna el valor de observaciones nota info.
	 *
	 * @return el valor de observaciones nota info
	 */
	public String getObservacionesNotaInfo()
	{
		return is_observacionesNotaInfo;
	}

	/**
	 * Modifica el valor de observaciones temporales.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones temporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Retorna el valor de observaciones temporales.
	 *
	 * @return el valor de observaciones temporales
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Modifica el valor de ocultar boton aprobador.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar boton aprobador
	 */
	public void setOcultarBotonAprobador(boolean ab_b)
	{
		ib_ocultarBotonAprobador = ab_b;
	}

	/**
	 * Valida la propiedad ocultar boton aprobador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar boton aprobador
	 */
	public boolean isOcultarBotonAprobador()
	{
		return ib_ocultarBotonAprobador;
	}

	/**
	 * Modifica el valor de oficina medida cautelar.
	 *
	 * @param aoo_oo asigna el valor a la propiedad oficina medida cautelar
	 */
	public void setOficinaMedidaCautelar(OficinaOrigen aoo_oo)
	{
		ioo_oficinaMedidaCautelar = aoo_oo;
	}

	/**
	 * Retorna el valor de oficina medida cautelar.
	 *
	 * @return el valor de oficina medida cautelar
	 */
	public OficinaOrigen getOficinaMedidaCautelar()
	{
		if(ioo_oficinaMedidaCautelar == null)
			ioo_oficinaMedidaCautelar = new OficinaOrigen();

		return ioo_oficinaMedidaCautelar;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen()
	{
		return getOficinaOrigen(false);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_desdeBaldios de ab desde baldios
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(boolean ab_desdeBaldios)
	{
		Collection<OficinaOrigen> lcidt_datos;

		lcidt_datos = null;

		try
		{
			if(ab_desdeBaldios)
				super.getOficinaOrigen();
			else
			{
				Documento ld_documento;

				ld_documento = getDocumentoDetalle();

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
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Modifica el valor de opcion cierre folio.
	 *
	 * @param as_s asigna el valor a la propiedad opcion cierre folio
	 */
	public void setOpcionCierreFolio(String as_s)
	{
		is_opcionCierreFolio = as_s;
	}

	/**
	 * Retorna el valor de opcion cierre folio.
	 *
	 * @return el valor de opcion cierre folio
	 */
	public String getOpcionCierreFolio()
	{
		return is_opcionCierreFolio;
	}

	/**
	 * Modifica el valor de opcion copiar.
	 *
	 * @param as_s asigna el valor a la propiedad opcion copiar
	 */
	public void setOpcionCopiar(String as_s)
	{
		is_opcionCopiar = as_s;
	}

	/**
	 * Retorna el valor de opcion copiar.
	 *
	 * @return el valor de opcion copiar
	 */
	public String getOpcionCopiar()
	{
		return is_opcionCopiar;
	}

	/**
	 * Modifica el valor de pais inter residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter residencia
	 */
	public void setPaisInterResidencia(boolean ab_b)
	{
		ib_paisInterResidencia = ab_b;
	}

	/**
	 * Valida la propiedad pais inter residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter residencia
	 */
	public boolean isPaisInterResidencia()
	{
		return ib_paisInterResidencia;
	}

	/**
	 * Modifica el valor de panel cierre folio.
	 *
	 * @param ab_b asigna el valor a la propiedad panel cierre folio
	 */
	public void setPanelCierreFolio(boolean ab_b)
	{
		ib_panelCierreFolio = ab_b;
	}

	/**
	 * Valida la propiedad panel cierre folio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en panel cierre folio
	 */
	public boolean isPanelCierreFolio()
	{
		return ib_panelCierreFolio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aols_ls de aols ls
	 */
	public void setPanels(List<String> aols_ls)
	{
		iols_panels = aols_ls;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de panels
	 */
	public List<String> getPanels()
	{
		return iols_panels;
	}

	/**
	 * Modifica el valor de pdf cancelacion.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf cancelacion
	 */
	public void setPdfCancelacion(boolean ab_b)
	{
		ib_pdfCancelacion = ab_b;
	}

	/**
	 * Valida la propiedad pdf cancelacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf cancelacion
	 */
	public boolean isPdfCancelacion()
	{
		return ib_pdfCancelacion;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param pdfGenerado de pdf generado
	 */
	public void setPdfGenerado(boolean pdfGenerado)
	{
		this.ib_pdfGenerado = pdfGenerado;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en pdf generado
	 */
	public boolean isPdfGenerado()
	{
		return ib_pdfGenerado;
	}

	/**
	 * Modifica el valor de pdf parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf parcial
	 */
	public void setPdfParcial(boolean ab_b)
	{
		ib_pdfParcial = ab_b;
	}

	/**
	 * Valida la propiedad pdf parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf parcial
	 */
	public boolean isPdfParcial()
	{
		return ib_pdfParcial;
	}

	/**
	 * Modifica el valor de pdf remate.
	 *
	 * @param ab_b asigna el valor a la propiedad pdf remate
	 */
	public void setPdfRemate(boolean ab_b)
	{
		ib_pdfRemate = ab_b;
	}

	/**
	 * Valida la propiedad pdf remate.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pdf remate
	 */
	public boolean isPdfRemate()
	{
		return ib_pdfRemate;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ap_p de ap p
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		if(ip_persona == null)
		{
			ip_persona = new Persona();
			ip_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ip_persona;
	}

	/**
	 * Modifica el valor de persona consulta.
	 *
	 * @param ap_p asigna el valor a la propiedad persona consulta
	 */
	public void setPersonaConsulta(Persona ap_p)
	{
		ip_personaConsulta = ap_p;
	}

	/**
	 * Retorna el valor de persona consulta.
	 *
	 * @return el valor de persona consulta
	 */
	public Persona getPersonaConsulta()
	{
		if(ip_personaConsulta == null)
			ip_personaConsulta = new Persona();

		return ip_personaConsulta;
	}

	/**
	 * Modifica el valor de PersonaExcesoGuardado.
	 *
	 * @param ab_b de ab b
	 */
	public void setPersonaExcesoGuardado(boolean ab_b)
	{
		ab_personaExcesoGuardado = ab_b;
	}

	/**
	 * Valida la propiedad persona exceso guardado.
	 *
	 * @return Retorna el valor de la propiedad personaExcesoGuardado
	 */
	public boolean isPersonaExcesoGuardado()
	{
		return ab_personaExcesoGuardado;
	}

	/**
	 * Modifica el valor de PersonaNotaExcesoPago.
	 *
	 * @param ap_p de ap p
	 */
	public void setPersonaNotaExcesoPago(Persona ap_p)
	{
		lp_personaNotaExcesoPago = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor persona nota exceso pago.
	 *
	 * @return Retorna el valor de la propiedad personaNotaExcesoPago
	 */
	public Persona getPersonaNotaExcesoPago()
	{
		return lp_personaNotaExcesoPago;
	}

	/**
	 * Sets the predio.
	 *
	 * @param ahm_predio correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setPredio(Map<String, Object> ahm_predio)
	{
		lhm_predio = ahm_predio;
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @return el valor de predio
	 */
	public Map<String, Object> getPredio()
	{
		return lhm_predio;
	}

	/**
	 * Modifica el valor de pregunta segregacion.
	 *
	 * @param as_s asigna el valor a la propiedad pregunta segregacion
	 */
	public void setPreguntaSegregacion(String as_s)
	{
		is_preguntaSegregacion = as_s;
	}

	/**
	 * Retorna el valor de pregunta segregacion.
	 *
	 * @return el valor de pregunta segregacion
	 */
	public String getPreguntaSegregacion()
	{
		return is_preguntaSegregacion;
	}

	/**
	 * Modifica el valor de primer selected.
	 *
	 * @param ab_b asigna el valor a la propiedad primer selected
	 */
	public void setPrimerSelected(boolean ab_b)
	{
		lb_primerSelected = ab_b;
	}

	/**
	 * Valida la propiedad primer selected.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en primer selected
	 */
	public boolean isPrimerSelected()
	{
		return lb_primerSelected;
	}

	/**
	 * Modifica el valor de proceso baldios.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso baldios
	 */
	public void setProcesoBaldios(boolean ab_b)
	{
		ib_procesoBaldios = ab_b;
	}

	/**
	 * Valida la propiedad proceso baldios.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso baldios
	 */
	public boolean isProcesoBaldios()
	{
		return ib_procesoBaldios;
	}

	/**
	 * Modifica el valor de proceso desenglobes.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso desenglobes
	 */
	public void setProcesoDesenglobes(boolean ab_b)
	{
		ib_procesoDesenglobe = ab_b;
	}

	/**
	 * Valida la propiedad proceso desenglobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso desenglobes
	 */
	public boolean isProcesoDesenglobes()
	{
		return ib_procesoDesenglobe;
	}

	/**
	 * Modifica el valor de proceso englobes.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso englobes
	 */
	public void setProcesoEnglobes(boolean ab_b)
	{
		ib_procesoEnglobes = ab_b;
	}

	/**
	 * Valida la propiedad proceso englobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso englobes
	 */
	public boolean isProcesoEnglobes()
	{
		return ib_procesoEnglobes;
	}

	/**
	 * Modifica el valor de proceso loteo.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso loteo
	 */
	public void setProcesoLoteo(boolean ab_b)
	{
		ib_procesoLoteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso loteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso loteo
	 */
	public boolean isProcesoLoteo()
	{
		return ib_procesoLoteo;
	}

	/**
	 * Modifica el valor de proceso reloteo.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso reloteo
	 */
	public void setProcesoReloteo(boolean ab_b)
	{
		ib_procesoReloteo = ab_b;
	}

	/**
	 * Valida la propiedad proceso reloteo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso reloteo
	 */
	public boolean isProcesoReloteo()
	{
		return ib_procesoReloteo;
	}

	/**
	 * Modifica el valor de proceso remanente.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso remanente
	 */
	public void setProcesoRemanente(boolean ab_b)
	{
		ib_procesoRemanente = ab_b;
	}

	/**
	 * Valida la propiedad proceso remanente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso remanente
	 */
	public boolean isProcesoRemanente()
	{
		return ib_procesoRemanente;
	}

	/**
	 * Modifica el valor de proceso venta parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso venta parcial
	 */
	public void setProcesoVentaParcial(boolean ab_b)
	{
		ib_procesoVentaParcial = ab_b;
	}

	/**
	 * Valida la propiedad proceso venta parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso venta parcial
	 */
	public boolean isProcesoVentaParcial()
	{
		return ib_procesoVentaParcial;
	}

	/**
	 * Modifica el valor de referencia.
	 *
	 * @param as_s asigna el valor a la propiedad referencia
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = as_s;
	}

	/**
	 * Retorna el valor de referencia.
	 *
	 * @return el valor de referencia
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Modifica el valor de registro datos consultados.
	 *
	 * @param ar_r asigna el valor a la propiedad registro datos consultados
	 */
	public void setRegistroDatosConsultados(Registro ar_r)
	{
		ir_registroDatosConsultados = ar_r;
	}

	/**
	 * Retorna el valor de registro datos consultados.
	 *
	 * @return el valor de registro datos consultados
	 */
	public Registro getRegistroDatosConsultados()
	{
		if(ir_registroDatosConsultados == null)
			ir_registroDatosConsultados = new Registro();

		return ir_registroDatosConsultados;
	}

	/**
	 * Modifica el valor de rendered.
	 *
	 * @param ib_b asigna el valor a la propiedad rendered
	 */
	public void setRendered(boolean ib_b)
	{
		ib_rendered = ib_b;
	}

	/**
	 * Valida la propiedad rendered.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered
	 */
	public boolean isRendered()
	{
		return ib_rendered;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderedHabilitaSecuencia(boolean ab_b)
	{
		ib_renderedHabilitaSecuencia = ab_b;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en rendered habilita secuencia
	 */
	public boolean isRenderedHabilitaSecuencia()
	{
		return ib_renderedHabilitaSecuencia;
	}

	/**
	 * Modifica el valor de requiere cuantia tipo acto.
	 *
	 * @param ab_b asigna el valor a la propiedad requiere cuantia tipo acto
	 */
	public void setRequiereCuantiaTipoActo(boolean ab_b)
	{
		ib_requiereCuantiaTipoActo = ab_b;
	}

	/**
	 * Valida la propiedad requiere cuantia tipo acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en requiere cuantia tipo acto
	 */
	public boolean isRequiereCuantiaTipoActo()
	{
		return ib_requiereCuantiaTipoActo;
	}

	/**
	 * Sets the revision matriculas.
	 *
	 * @param ahmso_hmso correspondiente al valor del tipo de objeto Map<String,Boolean>
	 */
	public void setRevisionMatriculas(Map<String, Boolean> ahmso_hmso)
	{
		ihmso_revisionMatriculas = ahmso_hmso;
	}

	/**
	 * Retorna el valor de revision matriculas.
	 *
	 * @return el valor de revision matriculas
	 */
	public Map<String, Boolean> getRevisionMatriculas()
	{
		if(ihmso_revisionMatriculas == null)
			ihmso_revisionMatriculas = new HashMap<String, Boolean>();

		return ihmso_revisionMatriculas;
	}

	/**
	 * Modifica el valor de salvar.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar
	 */
	public void setSalvar(boolean ab_b)
	{
		ib_salvar = ab_b;
	}

	/**
	 * Valida la propiedad salvar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar
	 */
	public boolean isSalvar()
	{
		return ib_salvar;
	}

	/**
	 * Modifica el valor de salvar area.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar area
	 */
	public void setSalvarArea(boolean ab_b)
	{
		ib_salvarArea = ab_b;
	}

	/**
	 * Valida la propiedad salvar area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar area
	 */
	public boolean isSalvarArea()
	{
		return ib_salvarArea;
	}

	/**
	 * Modifica el valor de salvar desenglobes.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar desenglobes
	 */
	public void setSalvarDesenglobes(boolean ab_b)
	{
		ib_salvarDesenglobes = ab_b;
	}

	/**
	 * Valida la propiedad salvar desenglobes.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar desenglobes
	 */
	public boolean isSalvarDesenglobes()
	{
		return ib_salvarDesenglobes;
	}

	/**
	 * Modifica el valor de salvar venta parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar venta parcial
	 */
	public void setSalvarVentaParcial(boolean ab_b)
	{
		ib_salvarVentaParcial = ab_b;
	}

	/**
	 * Valida la propiedad salvar venta parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar venta parcial
	 */
	public boolean isSalvarVentaParcial()
	{
		return ib_salvarVentaParcial;
	}

	/**
	 * Modifica el valor de SalvarVentaParcialCementerio.
	 *
	 * @param ab_b de ab b
	 */
	public void setSalvarVentaParcialCementerio(boolean ab_b)
	{
		ib_salvarVentaParcialCementerio = ab_b;
	}

	/**
	 * Valida la propiedad salvar venta parcial cementerio.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en salvar venta parcial cementerio
	 */
	public boolean isSalvarVentaParcialCementerio()
	{
		return ib_salvarVentaParcialCementerio;
	}

	/**
	 * Modifica el valor de salvar venta parcial no cementerio.
	 *
	 * @param ab_b asigna el valor a la propiedad salvar venta parcial no cementerio
	 */
	public void setSalvarVentaParcialNoCementerio(boolean ab_b)
	{
		ib_salvarVentaParcialNoCementerio = ab_b;
	}

	/**
	 * Valida la propiedad salvar venta parcial no cementerio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en salvar venta parcial no cementerio
	 */
	public boolean isSalvarVentaParcialNoCementerio()
	{
		return ib_salvarVentaParcialNoCementerio;
	}

	/**
	 * Modifica el valor de seccion matriculas aperturar.
	 *
	 * @param ab_b asigna el valor a la propiedad seccion matriculas aperturar
	 */
	public void setSeccionMatriculasAperturar(boolean ab_b)
	{
		ib_seccionMatriculasAperturar = ab_b;
	}

	/**
	 * Valida la propiedad seccion matriculas aperturar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seccion matriculas aperturar
	 */
	public boolean isSeccionMatriculasAperturar()
	{
		return ib_seccionMatriculasAperturar;
	}

	/**
	 * Modifica el valor de seleccionado es apoderado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado es apoderado
	 */
	public void setSeleccionadoEsApoderado(boolean ab_b)
	{
		ib_seleccionadoEsApoderado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado es apoderado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado es apoderado
	 */
	public boolean isSeleccionadoEsApoderado()
	{
		return ib_seleccionadoEsApoderado;
	}

	/**
	 * Modifica el valor de sin segregacion.
	 *
	 * @param ab_b asigna el valor a la propiedad sin segregacion
	 */
	public void setSinSegregacion(boolean ab_b)
	{
		ib_sinSegregacion = ab_b;
	}

	/**
	 * Valida la propiedad sin segregacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en sin segregacion
	 */
	public boolean isSinSegregacion()
	{
		return ib_sinSegregacion;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param aso_so asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud aso_so)
	{
		iso_solicitud = aso_so;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		if(iso_solicitud == null)
		{
			if(StringUtils.isValidString(is_idTurnoHistoria))
			{
				TurnoHistoria lth_temp;
				lth_temp = new TurnoHistoria();

				lth_temp.setIdTurnoHistoria(NumericUtils.getLongWrapper(is_idTurnoHistoria));

				try
				{
					iso_solicitud = irr_calificacionRemote.findSolicitudByTH(
						    lth_temp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(iso_solicitud == null)
						iso_solicitud = new Solicitud();

					iso_solicitud.setIdAutorizacionNotificacion(null);
					iso_solicitud.setIdAutorizacionComunicacion(null);
				}
				catch(B2BException lb2be_e)
				{
					addMessage(lb2be_e);
				}
			}
		}

		return iso_solicitud;
	}

	/**
	 * Modifica el valor de solicitud inter.
	 *
	 * @param asi_si asigna el valor a la propiedad solicitud inter
	 */
	public void setSolicitudInter(SolicitudInterviniente asi_si)
	{
		isi_solicitudInter = asi_si;
	}

	/**
	 * Retorna el valor de solicitud inter.
	 *
	 * @return el valor de solicitud inter
	 */
	public SolicitudInterviniente getSolicitudInter()
	{
		if(isi_solicitudInter == null)
			isi_solicitudInter = new SolicitudInterviniente();

		return isi_solicitudInter;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param asi_si de asi si
	 */
	public void setSolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		is_solicitudInterviniente = asi_si;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de solicitud interviniente
	 */
	public SolicitudInterviniente getSolicitudInterviniente()
	{
		if(is_solicitudInterviniente == null)
			is_solicitudInterviniente = new SolicitudInterviniente();

		return is_solicitudInterviniente;
	}

	/**
	 * Modifica el valor de sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad sub proceso
	 */
	public void setSubProceso(String as_s)
	{
		is_subProceso = as_s;
	}

	/**
	 * Retorna el valor de sub proceso.
	 *
	 * @return el valor de sub proceso
	 */
	public String getSubProceso()
	{
		return is_subProceso;
	}

	/**
	 * Modifica el valor de telefono fijo.
	 *
	 * @param apt_pt asigna el valor a la propiedad telefono fijo
	 */
	public void setTelefonoFijo(PersonaTelefono apt_pt)
	{
		ipt_telefonoFijo = apt_pt;
	}

	/**
	 * Retorna el valor de telefono fijo.
	 *
	 * @return el valor de telefono fijo
	 */
	public PersonaTelefono getTelefonoFijo()
	{
		if(ipt_telefonoFijo == null)
		{
			ipt_telefonoFijo = new PersonaTelefono();
			ipt_telefonoFijo.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoFijo;
	}

	/**
	 * Modifica el valor de telefono movil.
	 *
	 * @param atm_tm asigna el valor a la propiedad telefono movil
	 */
	public void setTelefonoMovil(PersonaTelefono atm_tm)
	{
		ipt_telefonoMovil = atm_tm;
	}

	/**
	 * Retorna el valor de telefono movil.
	 *
	 * @return el valor de telefono movil
	 */
	public PersonaTelefono getTelefonoMovil()
	{
		if(ipt_telefonoMovil == null)
		{
			ipt_telefonoMovil = new PersonaTelefono();
			ipt_telefonoMovil.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoMovil;
	}

	/**
	 * Modifica el valor de tipo doc interesado.
	 *
	 * @param as_s asigna el valor a la propiedad tipo doc interesado
	 */
	public void setTipoDocInteresado(String as_s)
	{
		is_tipoDocInteresado = as_s;
	}

	/**
	 * Retorna el valor de tipo doc interesado.
	 *
	 * @return el valor de tipo doc interesado
	 */
	public String getTipoDocInteresado()
	{
		return is_tipoDocInteresado;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param as_s asigna el valor a la propiedad turno
	 */
	public void setTurno(String as_s)
	{
		is_turno = as_s;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public String getTurno()
	{
		return is_turno;
	}

	/**
	 * Valida la propiedad valid matriculas informacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valid matriculas informacion
	 */
	public boolean isValidMatriculasInformacion()
	{
		return CollectionUtils.isValidCollection(icap_matriculasInformacion);
	}

	/**
	 * Modifica el valor de validacion comunicado medida cautelar.
	 *
	 * @param ab_b asigna el valor a la propiedad validacion comunicado medida cautelar
	 */
	public void setValidacionComunicadoMedidaCautelar(boolean ab_b)
	{
		ib_validacionComunicadoMedidaCautelar = ab_b;
	}

	/**
	 * Valida la propiedad validacion comunicado medida cautelar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validacion comunicado medida cautelar
	 */
	public boolean isValidacionComunicadoMedidaCautelar()
	{
		return ib_validacionComunicadoMedidaCautelar;
	}

	/**
	 * Modifica el valor de validar.
	 *
	 * @param ab_b asigna el valor a la propiedad validar
	 */
	public void setValidar(boolean ab_b)
	{
		ib_validar = ab_b;
	}

	/**
	 * Valida la propiedad validar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar
	 */
	public boolean isValidar()
	{
		return ib_validar;
	}

	/**
	 * Modifica el valor de validar area.
	 *
	 * @param ab_b asigna el valor a la propiedad validar area
	 */
	public void setValidarArea(boolean ab_b)
	{
		ib_validarArea = ab_b;
	}

	/**
	 * Valida la propiedad validar area.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar area
	 */
	public boolean isValidarArea()
	{
		return ib_validarArea;
	}

	/**
	 * Modifica el valor de validar propiedad horizontal.
	 *
	 * @param ab_b asigna el valor a la propiedad validar propiedad horizontal
	 */
	public void setValidarPropiedadHorizontal(boolean ab_b)
	{
		ib_validarPropiedadHorizontal = ab_b;
	}

	/**
	 * Valida la propiedad validar propiedad horizontal.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validar propiedad horizontal
	 */
	public boolean isValidarPropiedadHorizontal()
	{
		return ib_validarPropiedadHorizontal;
	}

	/**
	 * Modifica el valor de ValorADevolver.
	 *
	 * @param as_s de as s
	 */
	public void setValorADevolver(String as_s)
	{
		ls_valorADevolver = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor A devolver.
	 *
	 * @return Retorna el valor de la propiedad valorADevolver
	 */
	public String getValorADevolver()
	{
		return ls_valorADevolver;
	}

	/**
	 * Modifica el valor de viene de aprobacion.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de aprobacion
	 */
	public void setVieneDeAprobacion(boolean ab_b)
	{
		ib_vieneDeAprobacion = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de aprobacion
	 */
	public boolean isVieneDeAprobacion()
	{
		return ib_vieneDeAprobacion;
	}

	/**
	 * Modifica el valor de viene de aprobacion secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de aprobacion secuencia
	 */
	public void setVieneDeAprobacionSecuencia(boolean ab_b)
	{
		ib_vieneDeAprobacionSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de aprobacion secuencia
	 */
	public boolean isVieneDeAprobacionSecuencia()
	{
		return ib_vieneDeAprobacionSecuencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aba_excel de aba excel
	 * @param as_nameFile de as name file
	 * @param as_userId de as user id
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
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
							boolean lb_validarSumatoria;
							double  ld_areaTerrenoTotal;
							int     li_numcol;
							double  ld_areaTerreno;
							String  ls_matricula;

							lb_validarSumatoria     = irr_calificacionRemote.validarSumatoria(getIdTurnoHistoria());
							ld_areaTerrenoTotal     = 0.0;
							ld_areaTerreno          = 0.0;
							li_numcol               = 8;
							ls_matricula            = null;

							if(lb_validarSumatoria || isValidar())
							{
								RegistroCalificacion lrc_data;

								lrc_data = getMatriculas();

								if(lrc_data != null)
								{
									Collection<RegistroCalificacion> lcrc_data;

									lcrc_data = lrc_data.getAllMatriculas();

									if(CollectionUtils.isValidCollection(lcrc_data))
									{
										for(RegistroCalificacion lrc_iterador : lcrc_data)
										{
											if(lrc_iterador != null)
												ls_matricula = lrc_iterador.getIdCirculo();
										}
									}
								}

								if(StringUtils.isValidString(ls_matricula))
									ld_areaTerreno = irr_registroRemote.findAreaTerrenoByMatricula(ls_matricula);
							}

							Collection<AccAreaPredio>      lcaap_areaPredio;
							Collection<DireccionPredioAcc> lcdpacc_direccion;
							StringBuilder                  lsb_mensaje;

							lcaap_areaPredio      = new ArrayList<AccAreaPredio>();
							lcdpacc_direccion     = new ArrayList<DireccionPredioAcc>();
							lsb_mensaje           = new StringBuilder();

							for(int ii_fila = 0; ii_fila <= li_ultimaFila; ii_fila++)
							{
								if(li_primeraFila != 1)
								{
									Row lr_filaActual;

									lr_filaActual = lsh_hoja.getRow(ii_fila);

									if(ExcelUtils.isValidRow(lr_filaActual, li_numcol))
									{
										Double  ld_areaPredio;
										String  ls_circulo;
										Long    ll_matricula;
										Double  ld_coeficiente;
										Double  ld_areaConstruida;
										Double  ld_areaPrivadaConstruida;
										String  ls_complementoDireccion;
										String  ls_tipoUsoSuelo;
										boolean lb_obligatorio;

										ld_areaPredio                = null;
										ls_circulo                   = null;
										ll_matricula                 = null;
										ld_coeficiente               = NumericUtils.getDoubleWrapper(0D);
										ld_areaConstruida            = NumericUtils.getDoubleWrapper(0D);
										ld_areaPrivadaConstruida     = NumericUtils.getDoubleWrapper(0D);
										ls_complementoDireccion      = null;
										ls_tipoUsoSuelo              = null;
										lb_obligatorio               = true;

										if(
										    isProcesoDesenglobes() || isDivisionMaterial() || isProcesoLoteo()
											    || isProcesoReloteo()
										)
											lb_obligatorio = false;

										for(int li_celda = 0; li_celda < li_numcol; li_celda++)
										{
											try
											{
												if(li_celda == 0)
													ls_circulo = ExcelUtils.validarStringCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.CIRCULO, true
														);
												else if(li_celda == 1)
													ll_matricula = ExcelUtils.validarLongCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.MATRICULA, true
														);
												else if(li_celda == 2)
												{
													ld_areaPredio = ExcelUtils.validarDoubleCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.AREA_PREDIO, false
														);
													ld_areaTerrenoTotal += NumericUtils.getDouble(ld_areaPredio);

													if(ld_areaTerrenoTotal > ld_areaTerreno)
														throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
												}
												else if(li_celda == 3)
													ld_areaConstruida = ExcelUtils.validarDoubleCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.AREA_CONSTRUIDA, lb_obligatorio, true
														);
												else if(li_celda == 4)
													ld_areaPrivadaConstruida = ExcelUtils.validarDoubleCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.AREA_PRIVADA_CONSTRUIDA,
														    lb_obligatorio, true
														);
												else if(li_celda == 5)
													ld_coeficiente = ExcelUtils.validarDoubleCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.COEFICIENTE, lb_obligatorio, true
														);
												else if(li_celda == 6)
													ls_complementoDireccion = ExcelUtils.validarStringCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.COMPLEMENTO_DIRECCION, false
														);
												else if(li_celda == 7)
													ls_tipoUsoSuelo = ExcelUtils.validarStringCeldaExcel(
														    lr_filaActual, ii_fila + 1, li_celda,
														    IdentificadoresCommon.TIPO_USO_DEL_SUELO, false
														);
											}
											catch(B2BException ab2be_e)
											{
												lsb_mensaje.append(ab2be_e.getMessage());
												throw ab2be_e;
											}
										}

										if(!StringUtils.isValidString(lsb_mensaje.toString()))
										{
											AccAreaPredio      la_predio    = null;
											DireccionPredioAcc la_direccion = null;

											la_predio                       = new AccAreaPredio();
											la_direccion                    = new DireccionPredioAcc();

											la_predio.setIdCirculo(ls_circulo);
											la_predio.setIdMatricula(ll_matricula);
											la_predio.setCoeficiente(ld_coeficiente);
											la_predio.setAreaConstruida(ld_areaConstruida);
											la_predio.setAreaPrivadaConstruida(ld_areaPrivadaConstruida);
											la_predio.setAreaPredio(ld_areaPredio);
											la_predio.setTipoSuelo(ls_tipoUsoSuelo);
											la_predio.setIdUsuarioModificacion(as_userId);
											la_predio.setIpModificacion(getRemoteIpAddress());

											la_direccion.setIdCirculo(ls_circulo);
											la_direccion.setIdMatricula(ll_matricula);
											la_direccion.setComplementoDireccion(ls_complementoDireccion);
											la_direccion.setIdUsuarioModificacion(as_userId);
											la_direccion.setIpModificacion(getRemoteIpAddress());

											lcaap_areaPredio.add(la_predio);
											lcdpacc_direccion.add(la_direccion);
										}

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

							if(
							    CollectionUtils.isValidCollection(lcaap_areaPredio)
								    && CollectionUtils.isValidCollection(lcdpacc_direccion)
							)
							{
								Iterator<AccAreaPredio>      li_iteradorAreaPredio;
								Iterator<DireccionPredioAcc> li_iteradordireccion;

								li_iteradorAreaPredio     = lcaap_areaPredio.iterator();
								li_iteradordireccion      = lcdpacc_direccion.iterator();

								while(li_iteradorAreaPredio.hasNext() && li_iteradordireccion.hasNext())
								{
									AccAreaPredio      li_areaPredioActual;
									DireccionPredioAcc li_direccionActual;

									li_areaPredioActual     = li_iteradorAreaPredio.next();
									li_direccionActual      = li_iteradordireccion.next();

									if((li_areaPredioActual != null) && (li_direccionActual != null))
										lsb_mensaje.append(
										    processCell(li_areaPredioActual, li_direccionActual, as_userId)
										);
								}
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
			throw lb2be_e;
		}

		return aba_excel;
	}

	/**
	 * Accion digitador.
	 */
	public void accionDigitador()
	{
		setFlujoDigitador(true);

		PrimeFaces.current()
			          .executeScript(
			    "procesar();PrimeFaces.monitorDownload(start, stop);ocultarElemento('fRegistroCalif:idCBDigitadorMasivo');ocultarElemento('fRegistroCalif:idCBAprobador');"
			);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionRegresar()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = NavegacionCommon.DETALLE_ACTO;

		{
			BeanPredioDocumentoActo lbpdab_bean;
			lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
					);

			if(lbpdab_bean != null)
			{
				lbpdab_bean.setOcultarPaneles(false);
				lbpdab_bean.generarDatosAntSistema();
				lbpdab_bean.generarDatosDocumento();
				lbpdab_bean.obtenerInformacionASEtapa101();
				lbpdab_bean.validarFechaVencimientoActo();
				lbpdab_bean.setMotivoTramite(null);

				lbpdab_bean.getMatriculasRangos();
				lbpdab_bean.getMatriculasIndividuales();
				lbpdab_bean.getMatriculasTmpRangos();
				lbpdab_bean.getMatriculasTmpIndividuales();
			}
		}

		return ls_return;
	}

	/**
	 * Accion segundo factor verificado.
	 *
	 * @return el valor de string
	 */
	public String accionSegundoFactorVerificado()
	{
		String ls_return;

		ls_return = null;

		try
		{
			boolean lb_isSalvar;

			lb_isSalvar = isSalvar();

			{
				boolean              lb_isHabilitarMedidaCautelar;
				RegistroCalificacion lrc_matriculas;
				boolean              lb_validMatriculas;
				boolean              lb_mostrarSiguienteEnglobes;
				boolean              lb_isProcesoEnglobes;
				boolean              lb_isProcesoVentaParcial;
				boolean              lb_isProcesoDesenglobes;
				boolean              lb_isHabilitarTabs;

				lb_isHabilitarMedidaCautelar     = isHabilitarMedidaCautelar();
				lrc_matriculas                   = getMatriculas();
				lb_validMatriculas               = lrc_matriculas != null;
				lb_mostrarSiguienteEnglobes      = isMostrarSiguienteEnglobes();
				lb_isProcesoEnglobes             = isProcesoEnglobes();
				lb_isProcesoVentaParcial         = isProcesoVentaParcial();
				lb_isProcesoDesenglobes          = isProcesoDesenglobes();
				lb_isHabilitarTabs               = isHabilitarTabs();

				if(
				    lb_isHabilitarMedidaCautelar
					    || (((!lb_isHabilitarTabs || (isProcesoBaldios() && isProcesoTerminadoCrearGrabar()))
					    && lb_isSalvar && !lb_isHabilitarMedidaCautelar && isOcultarBotonAprobador()
					    && !isProcesoEnglobes() && !isHayIntervinienteConSecuencia()) && !isFlujoDigitador())
				)
					ls_return = guardarArhivo();
				else if(
				    !isMostrarSiguienteVenta() && isProcesoVentaParcial() && lb_isSalvar
					    && isSalvarVentaParcialNoCementerio()
				)
					ls_return = enviarADigitadorEnglobesVenta(false, false);
				else if(isSalvarDesenglobes() && lb_isSalvar)
					ls_return = enviarADigitadorDesenglobe();
				else if(
				    lb_validMatriculas && !lb_mostrarSiguienteEnglobes && lrc_matriculas.isDevolucion()
					    && lrc_matriculas.isEnglobe()
					    && ((lb_isProcesoEnglobes) || lb_isProcesoVentaParcial || lb_isProcesoDesenglobes)
					    && lb_isSalvar
				)
					ls_return = enviarADigitadorEnglobesDevolucion();
				else if(
				    lb_validMatriculas && !lb_mostrarSiguienteEnglobes && lrc_matriculas.isVentaParcial()
					    && lrc_matriculas.isCementerio()
					    && (lb_isProcesoEnglobes || lb_isProcesoVentaParcial || lb_isProcesoDesenglobes) && lb_isSalvar
				)
					ls_return = enviarADigitadorEnglobesVenta(false, true);
				else if(!lb_isHabilitarTabs && lb_isSalvar && !isSinSegregacion() && isFlujoDigitador())
					ls_return = enviarADigitador();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("accionSegundoFactorVerificado", lb2be_e);
		}
		catch(IOException lioe_e)
		{
			addMessage(new B2BException("IOException", lioe_e));
			clh_LOGGER.error("accionSegundoFactorVerificado", lioe_e);
		}
		finally
		{
			PrimeFaces.current().executeScript(ScriptsCommon.STOP);
		}

		return ls_return;
	}

	/**
	 * Acto si requiere cuantia.
	 */
	public void actoSiRequiereCuantia()
	{
		try
		{
			String ls_idTipoActo;
			ls_idTipoActo = getIdNaturalezaJuridicaInicial();

			if(StringUtils.isValidString(ls_idTipoActo))
			{
				TipoActo lta_parametroInicial;

				lta_parametroInicial = new TipoActo();
				lta_parametroInicial.setIdTipoActo(ls_idTipoActo);

				lta_parametroInicial = irr_registroRemote.findTipoActoById(lta_parametroInicial);

				if(lta_parametroInicial != null)
				{
					String ls_idTipoActoNuevo;
					ls_idTipoActoNuevo = getAnotacionPredioDetalle().getIdNaturalezaJuridica();

					if(StringUtils.isValidString(ls_idTipoActoNuevo))
					{
						TipoActo lta_parametros;

						lta_parametros = new TipoActo();
						lta_parametros.setIdTipoActo(ls_idTipoActoNuevo);

						lta_parametros = irr_registroRemote.findTipoActoById(lta_parametros);

						if(lta_parametros != null)
						{
							if(
							    !lta_parametros.getImpuestoRegistro()
								                   .equalsIgnoreCase(lta_parametroInicial.getImpuestoRegistro())
							)
							{
								getAnotacionPredioDetalle().setIdNaturalezaJuridica(getIdNaturalezaJuridicaInicial());
								throw new B2BException(ErrorKeys.ERROR_EL_ACTO_NO_CUMPLE_CON_EL_REQUISITO_IMPUESTO);
							}
						}
					}

					if(lta_parametroInicial.getActoSinCuantia().equalsIgnoreCase(EstadoCommon.S))
						setRequiereCuantiaTipoActo(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setMostrarAnotacionCancela(true);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Actualizar direccion predio data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarDireccionPredioData()
	    throws B2BException
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
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Metodo para recorrer la lista de intervinientes y actualizar el seleccionado.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarListaIntervinientes()
	    throws B2BException
	{
		try
		{
			Persona                lp_personaModificada;
			Collection<Anotacion>  lca_cllAnotacion;
			SolicitudInterviniente ls_solicitudInterviniente;

			lp_personaModificada          = getPersona();
			lca_cllAnotacion              = getIntervinientesAgregados();
			ls_solicitudInterviniente     = getSolicitudInterviniente();

			if(lp_personaModificada != null)
			{
				validacionesCampos(lp_personaModificada);

				if((lca_cllAnotacion != null) && (ls_solicitudInterviniente != null))
				{
					Collection<Anotacion> lca_cllAnotacionesActualizadas;

					lca_cllAnotacionesActualizadas = irr_calificacionRemote.actualizarListaIntervinientes(
						    lp_personaModificada, lca_cllAnotacion, ls_solicitudInterviniente
						);

					if(CollectionUtils.isValidCollection(lca_cllAnotacionesActualizadas))
					{
						setIntervinientesAgregados(lca_cllAnotacionesActualizadas);

						setPersona(null);
						setSolicitudInterviniente(null);
						setAnotacionPredioCiudadano(null);
						setPersonaConsulta(null);
						setListadoIntervinientes(null);
						setDeshabilitarDatosInterviniente(false);
						setMostrarListadoPersonas(false);
						setBloquearModificarIntervenientes(false);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String agregarAnotacionData()
	    throws IOException
	{
		String ls_return;

		ls_return = NavegacionCommon.DETALLE_REGISTRO;

		try
		{
			com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
			lap_anotacionPredio = getAnotacionPredioDetalle();

			if(lap_anotacionPredio != null)
			{
				{
					Date ld_fechaRadicacion;
					ld_fechaRadicacion = lap_anotacionPredio.getFechaRadicacion();

					if(ld_fechaRadicacion == null)
					{
						ls_return = null;
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
					}
				}

				{
					String ls_radicacion;
					ls_radicacion = lap_anotacionPredio.getRadicacion();

					if(!StringUtils.isValidString(ls_radicacion))
					{
						ls_return = null;
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_RADICACION);
					}
				}

				{
					String ls_estadoAnotacion;
					ls_estadoAnotacion = lap_anotacionPredio.getIdEstadoAnotacion();

					if(!StringUtils.isValidString(ls_estadoAnotacion))
					{
						ls_return = null;
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_ANOTACION);
					}
				}

				{
					String ls_tmp;
					ls_tmp = lap_anotacionPredio.getIdNaturalezaJuridica();

					if(!StringUtils.isValidString(ls_tmp))
					{
						ls_return = null;
						throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);
					}
				}

				consultarAnotacionTipoActo();
			}

			{
				RegistroCalificacion             lorc_rc;
				boolean                          lb_tmp;
				Collection<RegistroCalificacion> lcrc_rc;

				lorc_rc     = new RegistroCalificacion();
				lcrc_rc     = getAnotacionACancelar();
				lb_tmp      = false;

				if(!isMostrarAnotacionCancela() && CollectionUtils.isValidCollection(lcrc_rc))
				{
					for(RegistroCalificacion lorc_tmp : lcrc_rc)
					{
						if(!lb_tmp)
							if(lorc_tmp.isRevision())
								lb_tmp = true;
					}

					if(!lb_tmp)
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);

					lorc_rc.setAnotacionesCancelacion(lcrc_rc);
				}

				lorc_rc.setDataAnotacionPredio(lap_anotacionPredio);
				lorc_rc.setModificarAnotacionDocumento(false);
				lorc_rc.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));
				lorc_rc.setUserId(getUserId());
				lorc_rc.setIpAddress(getRemoteIpAddress());

				irr_calificacionRemote.modificarAnotacion(
				    lorc_rc, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				PrimeFaces.current().executeScript("PF('detalleAnotacion').hide();");

				idb_dataModel = new Dashboard();
				consultaDetalleMatricula(false);

				ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();
				lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());

				setAnotacionPredioDetalle(null);
				setDocumentoDetalle(null);
				setAnotacionCancelacion(null);
				setNaturalezaJuridicaSeleccionada(null);
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
	 * Metodo sobrecargado para sobreponer el valor del acto en 0 y un comentario de cancelacion de oficio de medida cautelar.
	 */
	public void agregarAnotacionPredio()
	{
		agregarAnotacionPredio(false);
	}

	/**
	 * Agregar anotacion predio.
	 *
	 * @param ab_cancelacionDeOficioMedidaCautelar correspondiente al valor del tipo de objeto boolean
	 */
	@SuppressWarnings("deprecation")
	public void agregarAnotacionPredio(boolean ab_cancelacionDeOficioMedidaCautelar)
	{
		try
		{
			if(isBloquearModificarIntervenientes())
				throw new B2BException(ErrorKeys.ERROR_MODIFICACION_INTERVINIENTES_INCOMPLETA);

			FacesContext                                          lfc_context;
			com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
			Documento                                             ld_documento;
			String                                                ls_descripcion;
			boolean                                               lb_focus;
			Collection<RegistroCalificacion>                      lcrc_anotacionesACancelar;

			lfc_context                   = FacesContext.getCurrentInstance();
			lap_anotacionPredio           = getAnotacionPredioDetalle();
			ld_documento                  = getDocumentoDetalle();
			ls_descripcion                = null;
			lb_focus                      = true;
			lcrc_anotacionesACancelar     = getAnotacionACancelarNuevo();

			{
				Long ll_numeroAnotacion;
				ll_numeroAnotacion     = NumericUtils.getLongWrapper(getCantidadAnotaciones());

				lb_focus = validateStyles(
					    ":faddAnotacion:idItNumeroAnotacionID", lfc_context, ll_numeroAnotacion, lb_focus
					);

				if(!NumericUtils.isValidLong(ll_numeroAnotacion))
					throw new B2BException(ErrorKeys.ERROR_NUMERO_ANOTACION);
			}

			if(lap_anotacionPredio != null)
			{
				if(ab_cancelacionDeOficioMedidaCautelar)
				{
					int    li_valorActo;
					String ls_comentario;

					li_valorActo      = 0;
					ls_comentario     = getMessages().getString(MessagesKeys.CANCELACION_DE_OFICIO_MEDIDA_CAUTELAR);

					lap_anotacionPredio.setValor(NumericUtils.getBigDecimal(li_valorActo));
					lap_anotacionPredio.setComentario(ls_comentario.toUpperCase());
				}

				{
					Date ld_fechaRadicacion;
					ld_fechaRadicacion     = lap_anotacionPredio.getFechaRadicacion();

					lb_focus = validateStyles(
						    ":faddAnotacion:idCalFechaAnotacion", lfc_context, ld_fechaRadicacion, lb_focus
						);

					if(ld_fechaRadicacion == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_ANOTACION);
				}

				{
					String ls_radicacion;
					ls_radicacion     = lap_anotacionPredio.getRadicacion();

					lb_focus = validateStyles(":faddAnotacion:idItRadicacionID", lfc_context, ls_radicacion, lb_focus);

					if(!StringUtils.isValidString(ls_radicacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_RADICACION);
				}

				{
					String ls_estadoAnotacion;
					ls_estadoAnotacion     = lap_anotacionPredio.getIdEstadoAnotacion();

					lb_focus = validateStyles(
						    ":faddAnotacion:idSOMEstadoAnotacion", lfc_context, ls_estadoAnotacion, lb_focus
						);

					if(!StringUtils.isValidString(ls_estadoAnotacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ESTADO_ANOTACION);
				}
			}

			if(ld_documento != null)
			{
				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdTipoDocumento();

					lb_focus = validateStyles(":faddAnotacion:idSOMEscrituraD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getNumero();

					lb_focus = validateStyles(":faddAnotacion:idItDocuActoD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					Date ld_tmp;
					ld_tmp     = ld_documento.getFechaDocumento();

					lb_focus = validateStyles(":faddAnotacion:idCalFechaDocD", lfc_context, ld_tmp, lb_focus);

					if(ld_tmp == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DOCUMENTO);
					else
					{
						Date ld_fechaActual;
						ld_fechaActual = new Date();

						if(ld_tmp.after(ld_fechaActual))
						{
							RequestContext.getCurrentInstance()
								              .execute("PrimeFaces.focus('faddAnotacion:idCalFechaDocD');");
							throw new B2BException(ErrorKeys.ERROR_FECHA_DOC_LIMITE);
						}
					}
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdTipoOficina();

					lb_focus = validateStyles(":faddAnotacion:idSOMTipoOficinaD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.ERROR_TIPO_OFICINA);
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdPais();

					lb_focus = validateStyles(":faddAnotacion:idPaisDocumentoD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdDepartamento();

					lb_focus = validateStyles(":faddAnotacion:idSOMDepartamentoD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdMunicipio();

					lb_focus = validateStyles(":faddAnotacion:idSOMMunicipioD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO_VALIDO);
				}

				{
					String ls_tmp;
					ls_tmp     = ld_documento.getIdOficinaOrigen();

					lb_focus = validateStyles(":faddAnotacion:idSOMOficinaOrigenD", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN_VALIDO);
				}
			}

			if(lap_anotacionPredio != null)
			{
				String ls_naturalezaJuridicaSeleccionada;
				ls_naturalezaJuridicaSeleccionada     = getNaturalezaJuridicaSeleccionada();

				lb_focus = validateStyles(
					    ":faddAnotacion:idSOMTipoActo", lfc_context, ls_naturalezaJuridicaSeleccionada, lb_focus
					);

				if(!StringUtils.isValidString(ls_naturalezaJuridicaSeleccionada))
					throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);

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

								lb_focus = validateStyles(
									    ":faddAnotacion:idItValorActo", lfc_context, NumericUtils.getDoubleWrapper(1D),
									    lb_focus
									);

								{
									String               ls_grupo;
									boolean              lb_tmp;
									Constantes           lc_parametros;
									Map<String, Boolean> lcs_tmp;

									lcs_tmp           = new HashMap<String, Boolean>();
									lc_parametros     = new Constantes();

									lc_parametros.setIdConstante(ConstanteCommon.GRUPO_NATURALEZA_JURIDICA_CANCELACION);

									lc_parametros     = ipr_parameterRemote.findConstantById(lc_parametros);
									lb_tmp            = false;
									ls_grupo          = lnj_parametros.getIdGrupoNatJur();

									if(lc_parametros != null)
									{
										String ls_entero;
										ls_entero = lc_parametros.getCaracter();

										if(StringUtils.isValidString(ls_entero))
										{
											String[] lsa_sa;
											lsa_sa = ls_entero.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);

											if(CollectionUtils.isValidCollection(lsa_sa))
											{
												for(String ls_tmp : lsa_sa)
													lcs_tmp.put(ls_tmp, new Boolean(true));
											}
										}
									}

									else
										throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);

									if(CollectionUtils.isValidCollection(lcrc_anotacionesACancelar))
									{
										for(RegistroCalificacion lorc_tmp : lcrc_anotacionesACancelar)
										{
											if(lorc_tmp != null)
												if(!lb_tmp)
													if(lorc_tmp.isRevision())
														lb_tmp = true;
										}
									}

									if(
									    StringUtils.isValidString(ls_grupo)
										    && BooleanUtils.getBooleanValue(lcs_tmp.get(ls_grupo))
									)
										if(!lb_tmp)
											throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_A_CANCELAR);
								}
							}
						}
					}
				}
			}

			{
				Collection<Anotacion> lca_intervinientesAgregados;

				long                  ll_idAnotacion;
				long                  li_contador;
				Long                  ll_numeroAnotacion;
				boolean               lb_conAnotacion;
				Collection<Anotacion> lca_datos;
				Anotacion             la_anotacion;

				ll_numeroAnotacion     = getAnotacionPredioDetalle().getIdAnotacion();
				la_anotacion           = getAnotacion();
				ll_idAnotacion         = getIdAnotacionGeneral();
				li_contador            = getContadorAnotaciones();
				lb_conAnotacion        = ll_idAnotacion > 0;
				lca_datos              = getAnotacionesAgregadas();

				lca_intervinientesAgregados = getIntervinientesAgregados();

				if(CollectionUtils.isValidCollection(lca_datos) && !lb_conAnotacion)
				{
					long ll_anotacionFinal;
					long ll_anotacionTmp;

					ll_anotacionFinal     = 0;
					ll_anotacionTmp       = NumericUtils.getLong(ll_numeroAnotacion);

					for(Anotacion la_iterador : lca_datos)
					{
						if(la_iterador != null)
							ll_anotacionFinal = la_iterador.getIdAnotacion();
					}

					ll_anotacionFinal = ll_anotacionFinal + 1;

					if(ll_anotacionTmp == ll_anotacionFinal)
					{
						RequestContext.getCurrentInstance()
							              .execute("PrimeFaces.focus('faddAnotacion:idItNumeroAnotacionID');");
						throw new B2BException(
						    "El número de anotación debe ser consecutivo, el siguiente número de anotación debe ser: "
						    + ll_anotacionFinal
						);
					}
				}

				if(lb_conAnotacion)
					la_anotacion.setIdAnotacion(ll_idAnotacion);
				else
					la_anotacion.setIdAnotacion(++li_contador);

				lap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(getCantidadAnotaciones()));

				if(!CollectionUtils.isValidCollection(lca_intervinientesAgregados))
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_INTERVINIENTE_ANOTACION);

				la_anotacion.setAnotacionPredio(lap_anotacionPredio);
				la_anotacion.setDocumento(ld_documento);
				la_anotacion.setIntervinientesAgregados(lca_intervinientesAgregados);
				la_anotacion.setMatriculasSegregadas(getMatriculasSegregadas());
				la_anotacion.setNaturalezaJuridica(ls_descripcion);
				la_anotacion.setAnotacionACancelarNuevo(lcrc_anotacionesACancelar);

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
						setIdAnotacionGeneral(-1);
					}
				}
				else
				{
					if(!CollectionUtils.isValidCollection(lca_datos))
						lca_datos = new ArrayList<Anotacion>();

					lca_datos.add(la_anotacion);

					setContadorAnotaciones(li_contador);
					setAnotacionesAgregadas(lca_datos);
					setCantidadAnotaciones(getCantidadAnotaciones() + 1);
				}

				{
					if(ab_cancelacionDeOficioMedidaCautelar)
					{
						Collection<RegistroCalificacion> lcrg_cllRegistroCalificacionActualizada;

						lcrg_cllRegistroCalificacionActualizada = new ArrayList<RegistroCalificacion>();

						if(CollectionUtils.isValidCollection(lcrc_anotacionesACancelar))
						{
							for(RegistroCalificacion lrc_registroCalificacion : lcrc_anotacionesACancelar)
							{
								if(lrc_registroCalificacion != null)
								{
									if(!lrc_registroCalificacion.isSelected())
										lcrg_cllRegistroCalificacionActualizada.add(lrc_registroCalificacion);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcrg_cllRegistroCalificacionActualizada))
						{
							setAnotacionACancelarNuevo(lcrg_cllRegistroCalificacionActualizada);
							setPrimerSelected(false);
						}

						{
							com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredioNueva;
							Date                                                  ld_fechaRadicacion;
							String                                                ls_radicacion;
							String                                                ls_idEstadoAnotacion;

							lap_anotacionPredioNueva     = new AnotacionPredio();
							ld_fechaRadicacion           = lap_anotacionPredio.getFechaRadicacion();
							ls_radicacion                = lap_anotacionPredio.getRadicacion();
							ls_idEstadoAnotacion         = lap_anotacionPredio.getIdEstadoAnotacion();

							lap_anotacionPredioNueva.setFechaRadicacion(ld_fechaRadicacion);
							lap_anotacionPredioNueva.setRadicacion(ls_radicacion);
							lap_anotacionPredioNueva.setIdEstadoAnotacion(ls_idEstadoAnotacion);

							setAnotacionPredioDetalle(lap_anotacionPredioNueva);
						}
					}
					else
						setAnotacionPredioDetalle(null);

					setAnotacionCancelacion(null);
					setIntervinientesAgregados(null);
					setMatriculasSegregadas(null);
					setNaturalezaJuridicaSeleccionada(null);
					setCodigoNaturalezaJuridicaSeleccionada(null);
					setPersonaConsulta(null);
					setIdPersonaSeleccion(null);
					setAnotacionAgregada(true);
					setAnotacion(null);

					String ls_mensaje = "Anotación agregada correctamente, si lo desea puede continuar agregando anotaciones.";
					addMessage("I", ls_mensaje);

					PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
					PrimeFaces.current().ajax().update("faddAnotacion:pDatosAnotacion");
					PrimeFaces.current().ajax().update("faddAnotacion:pAnotacionesCancelarNuevo");
					PrimeFaces.current().ajax().update("faddAnotacion:cbTerminar");

					RequestContext.getCurrentInstance()
						              .execute("PrimeFaces.focus('faddAnotacion:idItNumeroAnotacionID');");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Agregar area.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void agregarArea(boolean ab_accion)
	{
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
				String       ls_pantalla;

				lb_focus        = false;
				lbi_count       = laaui_dataArea.getIdDetalleAreaPredio();
				lfc_context     = FacesContext.getCurrentInstance();
				ls_area         = ladap_detalleArea.getAreaLectura();
				ls_pantalla     = ab_accion ? ":predialForm:" : ":fRegistroCalif:";
				lb_focus        = validateStyles(ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_area, lb_focus);

				if(StringUtils.isValidString(ls_area))
				{
					Double ld_area;
					String ls_medidaArea;

					ld_area           = NumericUtils.getDoubleWrapper(ls_area);
					ls_medidaArea     = ladap_detalleArea.getIdUnidadMedida();

					if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
					{
						String ls_areaTerreno;
						ls_areaTerreno     = StringUtils.getString(ld_area);

						lb_focus = validateStyles(
							    ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_areaTerreno, lb_focus
							);
						ladap_detalleArea.setArea(ld_area);
					}
					else
					{
						lb_focus = validateStyles(ls_pantalla + "idITareaTerrenoTabs", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
					}

					lb_focus = validateStyles(
						    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, ls_medidaArea, lb_focus
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
													    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, "",
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Agregar area venta.
	 */
	public void agregarAreaVenta()
	{
		try
		{
			AccAreaUI         laaui_dataArea;
			DetalleAreaPredio ladap_detalleArea;

			laaui_dataArea        = getAreaUI();
			ladap_detalleArea     = getDetalleAreaTerreno();

			if((laaui_dataArea != null) && (ladap_detalleArea != null))
			{
				String       ls_area;
				boolean      lb_focus;
				BigInteger   lbi_count;
				FacesContext lfc_context;

				ls_area         = ladap_detalleArea.getAreaLectura();
				lb_focus        = false;
				lbi_count       = laaui_dataArea.getIdDetalleAreaPredio();
				lfc_context     = FacesContext.getCurrentInstance();
				lb_focus        = validateStyles(
					    ":fRegistroCalif:idITareaTerrenoVenta", lfc_context, StringUtils.getString(ls_area), lb_focus
					);

				if(StringUtils.isValidString(ls_area))
				{
					Double ld_area;
					String ls_medidaArea;

					ld_area           = NumericUtils.getDoubleWrapper(ls_area);
					ls_medidaArea     = ladap_detalleArea.getIdUnidadMedida();

					if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
					{
						lb_focus = validateStyles(
							    ":fRegistroCalif:idITareaTerrenoVenta", lfc_context, ls_area, lb_focus
							);
						ladap_detalleArea.setArea(ld_area);
					}
					else
					{
						lb_focus = validateStyles(":fRegistroCalif:idITareaTerrenoVenta", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
					}

					lb_focus = validateStyles(
						    ":fRegistroCalif:idSOMunidadMedidaTerrenoVenta", lfc_context, ls_medidaArea, lb_focus
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
													    ":fRegistroCalif:idSOMunidadMedidaTerrenoVenta", lfc_context, "",
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarIntervinientes()
	    throws B2BException
	{
		agregarIntervinientes(false);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ab_desdeBaldios de ab desde baldios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarIntervinientes(boolean ab_desdeBaldios)
	    throws B2BException
	{
		if(ab_desdeBaldios)
			super.agregarIntervinientes();
		else
		{
			Persona lp_parametros;

			lp_parametros = getPersona();

			if(lp_parametros != null)
			{
				validacionesCampos(lp_parametros);
				setPersona(null);
				setSolicitudInterviniente(null);
				setAnotacionPredioCiudadano(null);
				setPersonaConsulta(null);
				setListadoIntervinientes(null);
				setDeshabilitarDatosInterviniente(false);
				setMostrarListadoPersonas(false);
				setBloquearModificarIntervenientes(false);
			}
		}
	}

	/**
	 * Agregar matricula apertura.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarMatriculaApertura()
	    throws B2BException
	{
		try
		{
			Collection<MatriculaSegregacionUi> lcms_mstp;
			MatriculaSegregacionUi             lms_oms;
			boolean                            lb_focus;
			FacesContext                       lfc_context;

			lb_focus        = false;
			lfc_context     = FacesContext.getCurrentInstance();
			lcms_mstp       = getDataInfoPredial();
			lms_oms         = getInfoSegregacionManual();

			if(lms_oms != null)
			{
				if(!NumericUtils.isValidLong(lms_oms.getUnidad()))
					throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);

				{
					String ls_nombrePredio;
					String ls_idNombrePredio;

					ls_nombrePredio       = lms_oms.getNombrePredio();
					ls_idNombrePredio     = "calificacionReloteoForm:idITNombreareaSegregacionManual";

					if(!StringUtils.isValidString(ls_nombrePredio))
					{
						lb_focus = validateStyles(ls_idNombrePredio, lfc_context, ls_nombrePredio, lb_focus);

						PrimeFaces.current().ajax().update(ls_idNombrePredio);

						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);
					}

					lb_focus = validateStyles(ls_idNombrePredio, lfc_context, ls_nombrePredio, lb_focus);
					PrimeFaces.current().ajax().update(ls_idNombrePredio);
				}

				{
					String ls_areaTerrenoStr;
					String ls_idCampoAreaTerreno;

					ls_idCampoAreaTerreno     = "calificacionReloteoForm:idITareaTerrenoCompletaSegregacion";
					ls_areaTerrenoStr         = lms_oms.getAreaTerrenoStr();

					if(StringUtils.isValidString(ls_areaTerrenoStr))
					{
						Double ld_area;
						ld_area = NumericUtils.getDoubleWrapper(ls_areaTerrenoStr);

						if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
						{
							lb_focus = validateStyles(ls_idCampoAreaTerreno, lfc_context, ls_areaTerrenoStr, lb_focus);
							lms_oms.setAreaTerreno(ld_area);
							lms_oms.setAreaTerrenoStr(ls_areaTerrenoStr);
						}
						else
						{
							ls_areaTerrenoStr     = null;
							lb_focus              = validateStyles(
								    ls_idCampoAreaTerreno, lfc_context, ls_areaTerrenoStr, lb_focus
								);

							PrimeFaces.current().ajax().update(ls_idCampoAreaTerreno);
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
				}

				lms_oms.setAreaConstruida(NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE));
				lms_oms.setAreaConstruidaStr("0.0");
				lms_oms.setAreaPrivada(NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE));
				lms_oms.setAreaPrivadaStr("0.0");
				lms_oms.setCoeficiente(NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE));

				{
					RegistroCalificacion lrc_tmp;
					Long                 ll_idMatricula;
					String               ls_idCirculo;

					lrc_tmp = getDetalleCalificacionReloteo();

					if(lrc_tmp != null)
					{
						ls_idCirculo       = lrc_tmp.getIdCirculo();
						ll_idMatricula     = NumericUtils.getLongWrapper(0L);

						if(StringUtils.isValidString(ls_idCirculo) && ls_idCirculo.contains("-"))
						{
							String[] las_as;

							las_as = ls_idCirculo.split("-");

							if(las_as != null)
							{
								ls_idCirculo       = las_as[0];
								ll_idMatricula     = NumericUtils.getLongWrapper(las_as[1]);
								lms_oms.setIdCirculoMatriz(ls_idCirculo);
								lms_oms.setCantidad(NumericUtils.getLongWrapper(1L));
								lms_oms.setMatriculaMatriz(ll_idMatricula);
								lms_oms.setIdSolicitud(getIdSolicitud());
							}
						}
					}
				}

				if(!CollectionUtils.isValidCollection(lcms_mstp))
				{
					lcms_mstp = new ArrayList<MatriculaSegregacionUi>();
					lms_oms.setCantidad(NumericUtils.getLongWrapper(1L));
				}
				else
					lms_oms.setCantidad(NumericUtils.getLongWrapper(lcms_mstp.size() + 1));

				lcms_mstp.add(lms_oms);

				setDataInfoPredial(lcms_mstp);
				setInfoSegregacionManual(null);
			}
		}
		catch(B2BException lb2b_b2be)
		{
			addMessage(lb2b_b2be);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Calificacion reloteo.
	 *
	 * @param arc_registroCalificacion correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void calificacionReloteo(RegistroCalificacion arc_registroCalificacion)
	{
		try
		{
			if(arc_registroCalificacion != null)
			{
				Long   ll_matriculasAperturar;
				String ls_idCirculo;

				setMatriculasAperturar(null);
				ll_matriculasAperturar     = arc_registroCalificacion.getMatriculasAperturar();
				ls_idCirculo               = arc_registroCalificacion.getIdCirculo();
				setSeccionMatriculasAperturar(true);
				setMatriculaDetalleReloteo(ls_idCirculo);

				getMatriculasAperturar().put(ls_idCirculo, ll_matriculasAperturar);
				setDetalleCalificacionReloteo(arc_registroCalificacion);
				setEncabezadoReloteo("Matrículas a aperturar para reloteo " + " " + ls_idCirculo);
				setCantidadAperturar(NumericUtils.getLong(ll_matriculasAperturar) <= 10);

				Collection<MatriculaSegregacion>   lcms_cms;
				Collection<MatriculaSegregacionUi> lcmsui_cmsui;
				lcmsui_cmsui     = new ArrayList<MatriculaSegregacionUi>();

				lcms_cms = irr_registroRemote.findMatriculaSegregacionByIdSolicitud(getIdSolicitud(), ls_idCirculo);

				if(CollectionUtils.isValidCollection(lcms_cms))
				{
					MatriculaSegregacionUi lmsui_msui;
					int                    li_contador;
					li_contador = 1;

					for(MatriculaSegregacion lmsui_tmp : lcms_cms)
					{
						if(lmsui_tmp != null)
						{
							lmsui_msui = new MatriculaSegregacionUi();

							lmsui_msui.setUnidad(lmsui_tmp.getUnidad());
							lmsui_msui.setNombrePredio(lmsui_tmp.getNombrePredio());
							lmsui_msui.setDireccion(lmsui_tmp.getDireccion());
							lmsui_msui.setCertificadoAsociado(lmsui_tmp.isCertificadoAsociado());
							lmsui_msui.setCantidad(NumericUtils.getLongWrapper(li_contador++));
							lmsui_msui.setAreaTerreno(lmsui_tmp.getAreaTerreno());
							lmsui_msui.setAreaPrivada(lmsui_tmp.getAreaPrivada());
							lmsui_msui.setAreaConstruida(lmsui_tmp.getAreaConstruida());
							lmsui_msui.setCoeficiente(lmsui_tmp.getCoeficiente());
							lmsui_msui.setIdSolicitud(lmsui_tmp.getIdSolicitud());
							lmsui_msui.setIdCirculoMatriz(lmsui_tmp.getIdCirculoMatriz());
							lmsui_msui.setMatriculaMatriz(lmsui_tmp.getMatriculaMatriz());

							lcmsui_cmsui.add(lmsui_msui);
						}
					}

					if(CollectionUtils.isValidCollection(lcmsui_cmsui))
						setDataInfoPredial(lcmsui_cmsui);
				}
				else
					setDataInfoPredial(lcmsui_cmsui);

				PrimeFaces.current().ajax().update("idDlgCalificacionReloteo");
				PrimeFaces.current().executeScript("PF('idDlgCalificacionReloteo').show();");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void cambiarCodigoTipoActo()
	{
		setCodigoNaturalezaJuridicaSeleccionada(getNaturalezaJuridicaSeleccionada());
		consultarAnotacionCancelacion();
	}

	/**
	 * Cambiar departamento residencia.
	 */
	public void cambiarDepartamentoResidencia()
	{
		findMunicipioResidencia();
	}

	/**
	 * Cambiar pais predio.
	 */
	public void cambiarPaisPredio()
	{
		DireccionDelPredio direccion;
		direccion = getDireccionPredio();

		if(direccion != null)
		{
			String ls_pais;
			ls_pais = direccion.getDatosAntiguoSistema().getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterResidencia(true);
				direccion.getDatosAntiguoSistema().setIdDepartamento(null);
			}
			else
				setPaisInterResidencia(false);
		}

		findDepartamentosPredio();
		findMunicipioPredio();
	}

	/**
	 * Cambiar pais residencia.
	 */
	public void cambiarPaisResidencia()
	{
		OficinaOrigen loo_oficina;
		loo_oficina = getOficinaMedidaCautelar();

		if(loo_oficina != null)
		{
			String ls_pais;
			ls_pais = loo_oficina.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
				loo_oficina.setIdDepartamento(null);
		}

		findDepartamentosResidencia();
		findMunicipioResidencia();
	}

	/**
	 * Cambiar pais telefono.
	 */
	public void cambiarPaisTelefono()
	{
		PersonaTelefono lpt_telefono;
		lpt_telefono = getTelefonoFijo();

		if(lpt_telefono != null)
		{
			String ls_pais;
			ls_pais = lpt_telefono.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
				lpt_telefono.setIdDepartamento(null);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void cambiarTipoActo()
	{
		setNaturalezaJuridicaSeleccionada(getCodigoNaturalezaJuridicaSeleccionada());
		consultarAnotacionCancelacion();
	}

	/**
	 * Actualiza los paneles para el ingreso de datos en interesados dependiendo del medio a comunicar seleccionado.
	 *
	 * @param as_medio Cadena de caracteres con la opción seleccionada en pantalla por el usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambioDeMedioComunicarInteresado(String as_medio)
	    throws B2BException
	{
		limpiarMedioCo();

		if(StringUtils.isValidString(as_medio))
		{
			BeanDireccion lbd_beanDireccion;
			PrimeFaces    lpf_current;
			Solicitud     ls_solicitud;
			boolean       lb_residencia;
			boolean       lb_correspondencia;
			boolean       lb_datosContacto;

			lbd_beanDireccion      = getBeanDireccion();
			lpf_current            = PrimeFaces.current();
			ls_solicitud           = getSolicitud();
			lb_residencia          = false;
			lb_correspondencia     = false;
			lb_datosContacto       = false;

			if(as_medio.equals("4") && (as_medio.equals("2") || as_medio.equals("3")))
			{
				lbd_beanDireccion.setDireccionResidencia(null);
				setDireccionResidencia(null);
			}

			if(as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4") || as_medio.equalsIgnoreCase("5"))
			{
				if(as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4"))
					setHabilitarCorreoCo(true);

				if(as_medio.equalsIgnoreCase("4") || as_medio.equalsIgnoreCase("5"))
				{
					setHabilitarTelFijoCo(true);
					setHabilitarTelMovilCo(true);
				}

				lpf_current.executeScript("PF('wvPanelDatosC').expand();");
				lb_datosContacto = true;
			}
			else if(as_medio.equalsIgnoreCase("2"))
			{
				setHabilitarDireccionReCo(true);

				lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
				lb_residencia = true;
			}

			if(ls_solicitud != null)
			{
				String ls_medioNot;

				ls_medioNot = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionNotificacion());

				if(ls_medioNot.equalsIgnoreCase("1") || ls_medioNot.equalsIgnoreCase("4"))
				{
					lpf_current.executeScript("PF('wvPanelDatosC').expand();");
					lb_datosContacto = true;
				}
				else if(ls_medioNot.equalsIgnoreCase("2"))
				{
					lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
					lb_residencia = true;
				}
				else if(ls_medioNot.equalsIgnoreCase("3"))
				{
					lpf_current.executeScript("PF('wvPanelDireccionC').expand();");
					lb_correspondencia = true;
				}
			}

			if(!lb_residencia)
				lpf_current.executeScript("PF('wvPanelDireccionR').collapse();");

			if(!lb_correspondencia)
				lpf_current.executeScript("PF('wvPanelDireccionC').collapse();");

			if(!lb_datosContacto)
				lpf_current.executeScript("PF('wvPanelDatosC').collapse();");
		}
	}

	/**
	 * Actualiza los paneles para el ingreso de datos en interesados dependiendo del medio a notificar seleccionado.
	 *
	 * @param as_medio Cadena de caracteres con la opción seleccionada en pantalla por el usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambioDeMedioNotificarInteresado(String as_medio)
	    throws B2BException
	{
		limpiarMedioNo();

		if(StringUtils.isValidString(as_medio))
		{
			BeanDireccion lbd_beanDireccion;
			PrimeFaces    lpf_current;
			Solicitud     ls_solicitud;
			boolean       lb_residencia;
			boolean       lb_correspondencia;
			boolean       lb_datosContacto;

			lbd_beanDireccion      = getBeanDireccion();
			lpf_current            = PrimeFaces.current();
			ls_solicitud           = getSolicitud();
			lb_residencia          = false;
			lb_correspondencia     = false;
			lb_datosContacto       = false;

			if(as_medio.equals("4") && (as_medio.equals("2") || as_medio.equals("3")))
			{
				lbd_beanDireccion.setDireccionResidencia(null);
				setDireccionResidencia(null);
			}

			if(as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4"))
			{
				setHabilitarCorreoNo(true);

				if(as_medio.equalsIgnoreCase("4"))
				{
					setHabilitarTelFijoNo(true);
					setHabilitarTelMovilNo(true);
				}

				lpf_current.executeScript("PF('wvPanelDatosC').expand();");
				lb_datosContacto = true;
			}
			else if(as_medio.equalsIgnoreCase("2"))
			{
				setHabilitarDireccionReNo(true);

				lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
				lb_residencia = true;
			}
			else if(as_medio.equalsIgnoreCase("3"))
			{
				setHabilitarDireccionCoNo(true);

				lpf_current.executeScript("PF('wvPanelDireccionC').expand();");
				lb_correspondencia = true;
			}

			if(ls_solicitud != null)
			{
				String ls_medioCom;

				ls_medioCom = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionComunicacion());

				if(
				    ls_medioCom.equalsIgnoreCase("1") || ls_medioCom.equalsIgnoreCase("4")
					    || ls_medioCom.equalsIgnoreCase("5")
				)
				{
					lpf_current.executeScript("PF('wvPanelDatosC').expand();");
					lb_datosContacto = true;
				}
				else if(ls_medioCom.equalsIgnoreCase("2"))
				{
					lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
					lb_residencia = true;
				}
			}

			if(!lb_residencia)
				lpf_current.executeScript("PF('wvPanelDireccionR').collapse();");

			if(!lb_correspondencia)
				lpf_current.executeScript("PF('wvPanelDireccionC').collapse();");

			if(!lb_datosContacto)
				lpf_current.executeScript("PF('wvPanelDatosC').collapse();");
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void cargarComplementacion()
	{
		try
		{
			ComplementacionCalificacion lcc_complementacionCalificacion;
			AccComplementacionPredio    lacp_complementacionPredio;

			lacp_complementacionPredio = new AccComplementacionPredio();

			lacp_complementacionPredio.setIdTurno(getTurno());
			lacp_complementacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));

			lcc_complementacionCalificacion     = getComplementacionCalificacion();
			lacp_complementacionPredio          = irr_calificacionRemote.cargarComplementacion(
				    lacp_complementacionPredio, getUserId()
				);

			if(lacp_complementacionPredio != null)
			{
				ComplementacionPredio lcp_cp;

				lcp_cp = lcc_complementacionCalificacion.getComplementacionPredio();

				lcp_cp.setIdComplementacion(
				    String.valueOf(NumericUtils.getLong(lacp_complementacionPredio.getIdComplementacionOriginal()))
				);
				lcp_cp.setComplementacion(lacp_complementacionPredio.getComplementacion());
				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.EXISTENTE);
			}
			else
				lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.NUEVA);

			setComplementacionCalificacion(lcc_complementacionCalificacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fdetalleDigitadorMasivo:globalMsg");
		}
	}

	/**
	 * Carga el correo electrónico para el interviniente seleccionado.
	 */
	public void cargarCorreoElectronico()
	{
		String ls_idCorreoSeleccion;
		ls_idCorreoSeleccion = getIdCorreoSeleccion();

		if(StringUtils.isValidString(ls_idCorreoSeleccion))
		{
			String[] lsa_tmp;
			lsa_tmp = ls_idCorreoSeleccion.split("-");

			if(CollectionUtils.isValidCollection(lsa_tmp) && (lsa_tmp.length > 1))
			{
				String ls_idPersonaSeleccionada;
				String ls_idCorreoSeleccionada;

				ls_idPersonaSeleccionada     = lsa_tmp[0];
				ls_idCorreoSeleccionada      = lsa_tmp[1];

				if(
				    StringUtils.isValidString(ls_idPersonaSeleccionada)
					    && StringUtils.isValidString(ls_idCorreoSeleccionada)
				)
				{
					Registro lr_datosCalculados;
					lr_datosCalculados = getRegistroDatosConsultados();

					if(lr_datosCalculados != null)
					{
						Collection<PersonaCorreoElectronico> lcpc_correo;
						lcpc_correo = lr_datosCalculados.getListadoCorreoElectronico();

						if(CollectionUtils.isValidCollection(lcpc_correo))
						{
							for(PersonaCorreoElectronico lpce_iterador : lcpc_correo)
							{
								if(lpce_iterador != null)
								{
									String ls_idPersona;
									String ls_idCorreo;

									ls_idPersona     = lpce_iterador.getIdPersona();
									ls_idCorreo      = lpce_iterador.getIdCorreoElectronico();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(ls_idCorreo)
									)
									{
										if(
										    ls_idPersonaSeleccionada.equalsIgnoreCase(ls_idPersona)
											    && ls_idCorreoSeleccionada.equalsIgnoreCase(ls_idCorreo)
										)
										{
											long idEtapa;

											idEtapa = NumericUtils.getLong(getIdEtapa());

											setCorreoElectronicoPersona(lpce_iterador);

											if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(idEtapa)))
											{
												if(
												    (idEtapa != EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION)
													    || (idEtapa != EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
												)
													setDeshabilitarCorreo(false);
												else
													setDeshabilitarCorreo(true);
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
		else
		{
			setCorreoElectronico(null);
			setDeshabilitarCorreo(false);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ap_persona de ap persona
	 */
	public void cargarDatosPersonales(Persona ap_persona)
	{
		cargarDatosPersonales(ap_persona, false);
	}

	/**
	 * Cargar datos personales.
	 *
	 * @param ap_persona correspondiente al valor del tipo de objeto Persona
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarDatosPersonales(Persona ap_persona, boolean ab_desdeBaldios)
	{
		if(ap_persona != null)
		{
			if(ab_desdeBaldios)
				super.cargarDatosPersonales(ap_persona);
			else
			{
				setPersona(ap_persona);
				setDeshabilitarDatosInterviniente(true);
				validarTipoPersonaDocumentoInter(StringUtils.getStringNotNull(ap_persona.getIdTipoPersona()));
			}
		}
		else
		{
			setPersona(null);
			setDeshabilitarDatosInterviniente(false);
		}
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada en la consulta por documento.
	 *
	 * @param aop_op Objeto contenedor de la información de la persona que se va a cargar en pantalla
	 */
	public void cargarDatosPersonalesInteresado(Persona aop_op)
	{
		if(aop_op != null)
		{
			Registro lr_datosCalculados;
			String   ls_idPersonaSeleccion;

			lr_datosCalculados        = getRegistroDatosConsultados();
			ls_idPersonaSeleccion     = aop_op.getIdPersona();

			setSeleccionadoEsApoderado(false);

			if(lr_datosCalculados != null)
			{
				Collection<Persona> lcp_personas;
				lcp_personas = lr_datosCalculados.getListadoPersona();

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
									setPersona(lp_iterador);
									setDeshabilitarDatosBasicos(true);
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

						setDeshabilitarDatosBasicos(false);
					}
					else
						setNuevaPersona(false);
				}
			}

			Persona lp_persona;
			lp_persona = getPersona();

			if(lp_persona != null)
			{
				validarTipoPersonaDocumento(StringUtils.getStringNotNull(lp_persona.getIdTipoPersona()));
				setMostrarDatosConsulta(true);
			}
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void cargarDepartamentoDocumento()
	{
		cargarDepartamentoDocumento(false);
	}

	/**
	 * Cargar departamento documento.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarDepartamentoDocumento(boolean ab_desdeBaldios)
	{
		if(ab_desdeBaldios)
			super.cargarDepartamentoDocumento();
		else
		{
			findMunicipiosDocumento();
			getOficinaOrigen();
		}
	}

	/**
	 * Carga la dirección de correspondencia para el interviniente seleccionado.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDireccionCorrespondencia()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;
		String        ls_idDirCorrSeleccion;

		lbd_beanDireccion         = getBeanDireccion();
		ls_idDirCorrSeleccion     = getIdDirCorrSeleccion();

		if(StringUtils.isValidString(ls_idDirCorrSeleccion))
		{
			String[] lsa_tmp;
			lsa_tmp = ls_idDirCorrSeleccion.split("-");

			if(CollectionUtils.isValidCollection(lsa_tmp) && (lsa_tmp.length > 1))
			{
				String ls_idPersonaSeleccionada;
				String ls_idDirCorrSeleccionada;

				ls_idPersonaSeleccionada     = lsa_tmp[0];
				ls_idDirCorrSeleccionada     = lsa_tmp[1];

				if(
				    StringUtils.isValidString(ls_idPersonaSeleccionada)
					    && StringUtils.isValidString(ls_idDirCorrSeleccionada)
				)
				{
					Registro lr_datosCalculados;
					lr_datosCalculados = getRegistroDatosConsultados();

					if(lr_datosCalculados != null)
					{
						Collection<PersonaDireccion> lcp_direcciones;
						lcp_direcciones = lr_datosCalculados.getListadoDireccionCorrespondencia();

						if(CollectionUtils.isValidCollection(lcp_direcciones))
						{
							for(PersonaDireccion lpd_iterador : lcp_direcciones)
							{
								if(lpd_iterador != null)
								{
									String ls_idPersona;
									String ls_idDireccion;

									ls_idPersona       = lpd_iterador.getIdPersona();
									ls_idDireccion     = lpd_iterador.getIdDireccion();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(ls_idDireccion)
									)
									{
										if(
										    ls_idPersonaSeleccionada.equalsIgnoreCase(ls_idPersona)
											    && ls_idDirCorrSeleccionada.equalsIgnoreCase(ls_idDireccion)
										)
										{
											lpd_iterador.setTipoDireccion("C");
											lbd_beanDireccion.setDireccionCorrespondencia(lpd_iterador);
											setDireccionCorrespondencia(lpd_iterador);
											lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
											setDeshabilitarCorrespondencia(true);
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
		{
			lbd_beanDireccion.setDireccionCorrespondencia(null);
			setDireccionCorrespondencia(null);
			lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
			setDeshabilitarCorrespondencia(false);
		}
	}

	/**
	 * Cargar direccion predio.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */

	/**
	 * Método encargado de cargar los datos de la dirección.
	 *
	 * @throws B2BException
	 */
	public void cargarDireccionFlowLoteo()
	    throws B2BException
	{
		PersonaDireccion lpd_direccionCorrespondencia;
		PersonaDireccion lpd_direccionResidencia;
		BeanDireccion    lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		setDeshabilitarBotonConfirmarDireccion(false);

		lpd_direccionCorrespondencia     = lbd_beanDireccion.getDireccionCorrespondencia();
		lpd_direccionResidencia          = lbd_beanDireccion.getDireccionResidencia();

		lbd_beanDireccion.limpiarBeanDireccion();

		if(isEsNotificacionCorrespondencia())
			lbd_beanDireccion.setDireccionCorrespondencia(lpd_direccionCorrespondencia);

		if(isEsNotificacionResidencia())
			lbd_beanDireccion.setDireccionResidencia(lpd_direccionResidencia);

		lbd_beanDireccion.setDeshabilitarTipoPredio(true);
		lbd_beanDireccion.setModificarDireccionPredio(false);
		lbd_beanDireccion.setForm(isProcesoReloteo() ? is_idFormReloteo : is_idForm);

		{
			DireccionDelPredio ldp_d;

			ldp_d = getDireccionPredio();

			if(ldp_d != null)
			{
				DatosAntSistema    ldas_datosAntSistema;
				DireccionPredioAcc ldp_direccion;
				Direccion          ld_direccion;

				ldas_datosAntSistema     = ldp_d.getDatosAntiguoSistema();
				ldp_direccion            = ldp_d.getDireccionPredio();
				ld_direccion             = null;

				if(ldp_direccion != null)
					ld_direccion = new Direccion(ldp_direccion);

				if(ld_direccion == null)
					ld_direccion = new Direccion();

				lbd_beanDireccion.setDireccionPredio(ld_direccion);

				if(ldas_datosAntSistema != null)
					lbd_beanDireccion.cargarDatosDireccionPredio(ldas_datosAntSistema);
			}
		}
	}

	/**
	 * Cargar direccion predio.
	 *
	 * @param ab_mensaje de ab mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDireccionPredio(boolean ab_mensaje)
	    throws B2BException
	{
		String                           ls_circulo;
		Long                             ll_matricula;
		Collection<RegistroCalificacion> lcrc_matricula;
		DireccionPredio                  ldp_direccionAConsultar;
		DireccionDelPredio               lddl_direccion;
		RegistroCalificacion             lrc_registroCalificacion;

		try
		{
			ll_matricula                 = null;
			ls_circulo                   = null;
			lddl_direccion               = new DireccionDelPredio();
			ldp_direccionAConsultar      = new DireccionPredio();
			lrc_registroCalificacion     = getMatriculas();
			lcrc_matricula               = lrc_registroCalificacion.getAllMatriculas();

			for(RegistroCalificacion lrc_actual : lcrc_matricula)
			{
				if(lrc_actual != null)
					ls_circulo = lrc_actual.getIdCirculo();
			}

			if(StringUtils.isValidString(ls_circulo))
			{
				String[] partes;

				partes     = ls_circulo.split("-");

				ls_circulo       = partes[0];
				ll_matricula     = NumericUtils.getLongWrapper(partes[1]);

				if(!NumericUtils.isValidLong(ll_matricula))
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_INVALIDA);

				ldp_direccionAConsultar.setIdMatricula(ll_matricula);
				ldp_direccionAConsultar.setIdCirculo(ls_circulo);

				if(isDevolucion() && !lrc_registroCalificacion.isEnglobe())
				{
//					lddl_direccion = irr_calificacionRemote
//							                 .obtenerDireccionPredioDigitadoreMasivo(
//							    ldp_direccionAConsultar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
//							);
				}
				else
					lddl_direccion = irr_calificacionRemote.obtenerDireccionPredio(
						    ldp_direccionAConsultar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

				setDireccionPredio(lddl_direccion);
				setMatriculaDireccionPredio(ll_matricula);
				setCirculoDireccionPredio(ls_circulo);
			}
			else
				throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			if(ab_mensaje)
				throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Carga la dirección de residencia para el interviniente seleccionado.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDireccionResidencia()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;
		String        ls_idDirResSeleccion;

		lbd_beanDireccion        = getBeanDireccion();
		ls_idDirResSeleccion     = getIdDirResSeleccion();

		if(StringUtils.isValidString(ls_idDirResSeleccion))
		{
			String[] lsa_tmp;
			lsa_tmp = ls_idDirResSeleccion.split("-");

			if(CollectionUtils.isValidCollection(lsa_tmp) && (lsa_tmp.length > 1))
			{
				String ls_idPersonaSeleccionada;
				String ls_idDirResSeleccionada;

				ls_idPersonaSeleccionada     = lsa_tmp[0];
				ls_idDirResSeleccionada      = lsa_tmp[1];

				if(
				    StringUtils.isValidString(ls_idPersonaSeleccionada)
					    && StringUtils.isValidString(ls_idDirResSeleccionada)
				)
				{
					Registro lr_datosCalculados;
					lr_datosCalculados = getRegistroDatosConsultados();

					if(lr_datosCalculados != null)
					{
						Collection<PersonaDireccion> lcp_direcciones;
						lcp_direcciones = lr_datosCalculados.getListadoDireccionResidencia();

						if(CollectionUtils.isValidCollection(lcp_direcciones))
						{
							for(PersonaDireccion lpd_iterador : lcp_direcciones)
							{
								if(lpd_iterador != null)
								{
									String ls_idPersona;
									String ls_idDireccion;

									ls_idPersona       = lpd_iterador.getIdPersona();
									ls_idDireccion     = lpd_iterador.getIdDireccion();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(ls_idDireccion)
									)
									{
										if(
										    ls_idPersonaSeleccionada.equalsIgnoreCase(ls_idPersona)
											    && ls_idDirResSeleccionada.equalsIgnoreCase(ls_idDireccion)
										)
										{
											lpd_iterador.setTipoDireccion("R");
											lbd_beanDireccion.setDireccionResidencia(lpd_iterador);
											setDireccionResidencia(lpd_iterador);
											lbd_beanDireccion.setDeshabilitarResidencia(true);
											setDeshabilitarResidencia(true);
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
		{
			lbd_beanDireccion.setDireccionResidencia(null);
			setDireccionResidencia(null);
			lbd_beanDireccion.setDeshabilitarResidencia(false);
			setDeshabilitarResidencia(false);
		}
	}

	/**
	 * Cargar linderos.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarLinderos()
	    throws B2BException
	{
		try
		{
			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones;

			lc_anotaciones = generarAnotacionesPredioAcc();

			if(CollectionUtils.isValidCollection(lc_anotaciones))
			{
				LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
				llrc_linderoRegistroCalificacion = irr_calificacionRemote.cargarLinderos(lc_anotaciones);

				if(llrc_linderoRegistroCalificacion != null)
				{
					setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);

					String ls_lindero;
					ls_lindero = llrc_linderoRegistroCalificacion.getLindero();

					if(StringUtils.isValidString(ls_lindero))
						setCargarLindero(true);
					else
						setCargarLindero(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Cargar linderos acc.
	 */
	public void cargarLinderosAcc()
	{
		try
		{
			Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotaciones;

			lcap_anotaciones = generarAnotacionesPredioAcc();

			if(CollectionUtils.isValidCollection(lcap_anotaciones))
			{
				Collection<AccLinderoPredio> lclp_linderos;
				LinderoRegistroCalificacion  llrc_linderoRegistroCalificacion;

				lclp_linderos     = new ArrayList<AccLinderoPredio>();

				llrc_linderoRegistroCalificacion = irr_calificacionRemote.cargarLinderosDigitadorMasivo(
					    lcap_anotaciones
					);

				if(llrc_linderoRegistroCalificacion != null)
				{
					for(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio laap_actual : lcap_anotaciones)
					{
						if(laap_actual != null)
						{
							AccLinderoPredio lalp_linderoPredio;

							lalp_linderoPredio = new AccLinderoPredio();

							lalp_linderoPredio.setIdCirculo(laap_actual.getIdCirculo());
							lalp_linderoPredio.setIdMatricula(laap_actual.getIdMatricula());

							lclp_linderos.add(lalp_linderoPredio);
						}
					}

					llrc_linderoRegistroCalificacion.setLinderoPredios(lclp_linderos);
					setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
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
	 *  {@inheritdoc}.
	 */
	public void cargarListasDesplegablesDocumento()
	{
		findPaises();
		findDepartamentosDocumento();
		findMunicipiosDocumento();
		getOficinaOrigen();
	}

	/**
	 * Carga los medios de comunicación y de notificación dependiendo del tipo de persona seleccionado.
	 *
	 * @param ab_personaJuridica correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarMediosNotCom(boolean ab_personaJuridica)
	{
		try
		{
			TipoRecepcion ltr_tipoRecepcion;
			ltr_tipoRecepcion = new TipoRecepcion();

			ltr_tipoRecepcion.setHabilitadoComunicacion(EstadoCommon.S);
			ltr_tipoRecepcion.setHabilitadoNotificacion(EstadoCommon.S);

			setMediosComunicar(
			    irr_referenceRemote.findByHabilitado(
			        ltr_tipoRecepcion, false, ab_personaJuridica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			setMediosNotificar(
			    irr_referenceRemote.findByHabilitado(
			        ltr_tipoRecepcion, true, ab_personaJuridica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarOficinaOrigen()
	    throws B2BException
	{
		cargarOficinaOrigen(false);
	}

	/**
	 * Cargar oficina origen.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarOficinaOrigen(boolean ab_desdeBaldios)
	    throws B2BException
	{
		try
		{
			if(ab_desdeBaldios)
				super.cargarOficinaOrigen();
			else
			{
				Collection<OficinaOrigen> lc_oficina;
				lc_oficina = getOficinaOrigen();

				if(lc_oficina == null)
					throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Cargar oficina origen medida cautelar.
	 */
	public void cargarOficinaOrigenMedidaCautelar()
	{
		try
		{
			setOficinaMedidaCautelar(
			    irr_calificacionRemote.cargarOficinaOrigenMedidaCautelar(
			        getTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void cargarPaisDocumento()
	{
		cargarPaisDocumento(false);
	}

	/**
	 * Cargar pais documento.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarPaisDocumento(boolean ab_desdeBaldios)
	{
		if(ab_desdeBaldios)
			super.cargarPaisDocumento();
		else
		{
			findDepartamentosDocumento();
			findMunicipiosDocumento();
			getOficinaOrigen();
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_idPanels correspondiente al valor del tipo de objeto List<String>
	 * @param as_IdMove correspondiente al valor del tipo de objeto String
	 * @param ai_position correspondiente al valor del tipo de objeto int
	 * @return devuelve el valor de String
	 */
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

	/**
	 *  {@inheritdoc}.
	 */
	public void clean()
	{
		setGeneroNotaInformativaPagoExceso(false);
		setAnotacionesPredioEliminadas(false);
		setGeneroComunicadoDireccion(false);
		setVieneDeAprobacion(false);
		setIdAnotacionGeneral(0);
		setComplementacionCalificacion(null);
		setComplementacionAnotacion(null);
		setImagen(null);
		setPrimerSelected(false);
		setDetalleMatricula(null);
		setIdTurnoHistoria(null);
		setHabilitarDigitador(false);
		setSalvar(false);
		setMatriculas(null);
		setHabilitarTabs(false);
		setProcesoEnglobes(false);
		setProcesoVentaParcial(false);
		setHabilitarMedidaCautelar(false);
		setValidacionComunicadoMedidaCautelar(false);
		setBloquearModificarIntervenientes(false);
		setReferencia(null);
		setMatriculasInformacion(null);
		setNumeroProceso(null);
		setDevolucion(false);
		setObservaciones(null);
		setFileGenerado(false);
		setDataInfoSegrec(null);
		setBloquearBotonAprobador(false);
		setOcultarBotonAprobador(true);
		setDetalleAreaTerreno(null);
		setAreaUI(null);
		setOficinaMedidaCautelar(null);
		setEditarDatosOficinaMedidaCautelar(false);
		setSalvarVentaParcial(false);
		setSalvarVentaParcialNoCementerio(false);
		setMostrarRegresarEnglobes(false);
		setMostrarRegresarVenta(false);
		setMostrarSiguienteEnglobes(true);
		setMostrarSiguienteVenta(true);
		setLinderoVentaNoCementerio(null);
		setProcesoDesenglobes(false);
		setSalvarDesenglobes(false);
		setPdfRemate(false);
		setIndVinculado(false);
		setObservacionesNotaInfo(null);
		setNuevaLinderos(false);
		setHabilitarComplementacion(false);
		setMatriculasParaGenerarComplementacion(null);
		setPredio(null);
		setCargarBotonesRegistro(false);
		setFlujoDigitador(false);
		setSalvarVentaParcialCementerio(false);
	}

	/**
	 * Confirmar direccion predio.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean confirmarDireccionPredio()
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			DireccionDelPredio ldp_d;

			ldp_d = getDireccionPredio();

			if(ldp_d != null)
			{
				BeanDireccion lbd_beanDireccion;
				Direccion     ld_direccionPredio;

				lbd_beanDireccion      = getBeanDireccion();
				ld_direccionPredio     = lbd_beanDireccion.getDireccionPredio();

				lbd_beanDireccion.validarCamposDireccionPredio(false);

				if(ld_direccionPredio != null)
				{
					DireccionPredioAcc lpda_direccion;

					lpda_direccion = ld_direccionPredio.getDireccionPredioAcc();

					lpda_direccion.setIdTurno(getTurno());
					lpda_direccion.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					lpda_direccion.setIdMatricula(getMatriculaDireccionPredio());
					lpda_direccion.setIdCirculo(getCirculoDireccionPredio());

					if(isModificarDireccionPredio())
						lpda_direccion.setIdDireccion("0");

					ldp_d.setDireccionPredio(lpda_direccion);
					ldp_d.setDireccion(lpda_direccion.getDireccion());

					setDataDireccionPredio(ldp_d);

					if(!isProcesoReloteo())
					{
						addMessage(MessagesKeys.PROCESO_COMPLETADO);
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
					}

					lbd_beanDireccion.setModificarDireccionPredio(false);
					setModificarDireccionPredio(false);
					setDeshabilitarBotonConfirmarDireccion(true);
					setBtnTerminarLoteo(true);

					lb_return = true;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			lb_return = false;

			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			setDeshabilitarBotonConfirmarDireccion(false);
		}

		return lb_return;
	}

	/**
	 * Construye los paneles de detalle de las anotaciones agregadas y muestra la información de las matrículas.
	 *
	 * @param ab_alertaProhibicion identifica si el caso tiene o no alertas de prohibiciones
	 * @return Enlace a la pagina de detalle registro
	 */
	public String consultaDetalleMatricula(boolean ab_alertaProhibicion)
	{
		return consultaDetalleMatricula(ab_alertaProhibicion, false);
	}

	/**
	 * Construye los paneles de detalle de las anotaciones agregadas y muestra la información de las matrículas.
	 *
	 * @param ab_alertaProhibicion identifica si el caso tiene o no alertas de prohibiciones
	 * @param ab_cancelacionEmbargo correspondiente al valor del tipo de objeto boolean
	 * @return Enlace a la pagina de detalle registro
	 */
	public String consultaDetalleMatricula(boolean ab_alertaProhibicion, boolean ab_cancelacionEmbargo)
	{
		String ls_return;

		ls_return = NavegacionCommon.DETALLE_REGISTRO;

		try
		{
			String ls_idCirculo;
			String ls_idTurno;
			String ls_estadoMatricula;

			if(ab_cancelacionEmbargo)
			{
				ls_idCirculo           = getIdCirculo();
				ls_idTurno             = getIdTurno();
				ls_estadoMatricula     = getEstadoMatricula();
			}
			else
			{
				ls_idCirculo           = FacesUtils.getStringFacesParameter("idCirculo");
				ls_idTurno             = FacesUtils.getStringFacesParameter("idTurno");
				ls_estadoMatricula     = FacesUtils.getStringFacesParameter("idEstadoMatricula");
			}

			findSubProceso();

			if(StringUtils.isValidString(ls_idCirculo) || StringUtils.isValidString(getCirculo()))
			{
				if(StringUtils.isValidString(ls_idCirculo))
					setCirculo(ls_idCirculo);
				else if(StringUtils.isValidString(getCirculo()))
					ls_idCirculo = getCirculo();

				if(StringUtils.isValidString(ls_idTurno))
					setTurno(ls_idTurno);
				else if(StringUtils.isValidString(getTurno()))
					ls_idTurno = getTurno();

				if(
				    StringUtils.isValidString(ls_estadoMatricula)
					    && ls_estadoMatricula.equalsIgnoreCase(EstadoCommon.ESTADO_CERRADO)
				)
				{
					Object[] args = new String[1];
					args[0] = ls_idCirculo;
					throw new B2BException(ErrorKeys.ERROR_MATRICULA_CERRADA_REABRIR, args);
				}

				String               ls_circulo;
				Long                 ll_matricula;
				int                  li_tmp;
				RegistroCalificacion liorc_tmp;
				RegistroCalificacion liorc_detail;

				liorc_detail     = new RegistroCalificacion();
				liorc_tmp        = new RegistroCalificacion();
				li_tmp           = ls_idCirculo.indexOf("-");
				ls_circulo       = ls_idCirculo.substring(0, li_tmp);
				ll_matricula     = NumericUtils.getLongWrapper(ls_idCirculo.substring(li_tmp + 1));

				liorc_tmp.setIdCirculo(ls_circulo);
				liorc_tmp.setIdMatricula(ll_matricula);
				liorc_tmp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				liorc_tmp.setIdUsuario(getUserId());
				liorc_tmp.setIpAddress(getLocalIpAddress());
				liorc_tmp.setMatriculaMatriz(true);
				liorc_tmp.setIndVinculado(isIndVinculado());

				liorc_detail = irr_calificacionRemote.findDetalleMatriculas(liorc_tmp);

				if(isIndVinculado())
				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					addMessage(MessagesKeys.REVISAR_ANOTACIONES);

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}

				/* Consulta Matriculas */
				if((liorc_detail != null) && CollectionUtils.isValidCollection(liorc_detail.getAllMatriculas()))
				{
					Application                      la_application;
					Collection<RegistroCalificacion> locri_tmp;
					DashboardColumn                  ldbc_dbc;
					DashboardModel                   ldbm_model;
					FacesContext                     lfc_facesContext;
					int                              li_column;
					int                              li_anotacion;
					Map<String, Object[]>            lmso_alertas;
					String                           ls_lineaSeparadora;

					ldbc_dbc               = new DefaultDashboardColumn();
					lfc_facesContext       = FacesContext.getCurrentInstance();
					la_application         = lfc_facesContext.getApplication();
					li_anotacion           = 0;
					li_column              = 1;
					ls_lineaSeparadora     = "<br/>";
					locri_tmp              = liorc_detail.getAllMatriculas();
					lmso_alertas           = irr_calificacionRemote.generarAlertas(locri_tmp, liorc_tmp);

					if(CollectionUtils.isValidCollection(lmso_alertas))
					{
						for(Map.Entry<String, Object[]> lmso_iterador : lmso_alertas.entrySet())
						{
							if(lmso_iterador != null)
							{
								addMessage(lmso_iterador.getKey(), lmso_iterador.getValue());
								FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
							}
						}
					}

					idb_dataModel = (Dashboard)la_application.createComponent(
						    lfc_facesContext, "org.primefaces.component.Dashboard",
						    "org.primefaces.component.DashboardRenderer"
						);
					idb_dataModel.setId("dashboard");

					ldbm_model = new DefaultDashboardModel();

					ldbm_model.addColumn(ldbc_dbc);

					idb_dataModel.setModel(ldbm_model);

					if(CollectionUtils.isValidCollection(locri_tmp))
					{
						for(RegistroCalificacion lorc_rc : locri_tmp)
						{
							if(lorc_rc != null)
							{
								boolean lb_cancelacion;

								lb_cancelacion = false;

								if(lorc_rc.getIdNaturalezJuridica().equalsIgnoreCase(getActoCancelacion()))
									lb_cancelacion = true;

								/* Creacion panel para agregar al dashboard */
								Panel  lp_panel           = (Panel)la_application.createComponent(
									    lfc_facesContext, "org.primefaces.component.Panel",
									    "org.primefaces.component.PanelRenderer"
									);
								Date   ls_fechaRadicacion;
								String ls_header;
								String ls_orden;
								Long   ll_orden;

								ls_fechaRadicacion        = lorc_rc.getFechaRadicacion();
								ll_orden                  = lorc_rc.getOrden();

								if(NumericUtils.isValidLong(ll_orden))
									ls_orden = StringUtils.getString(ll_orden);
								else
									ls_orden = IdentificadoresCommon.DATO_INVALIDO;

								if(ls_fechaRadicacion != null)
									ls_header = "ANOTACION - Orden " + ls_orden + " - Nro." + lorc_rc.getIdAnotacion()
										+ "  " + " Fecha:"
										+ StringUtils.getString(ls_fechaRadicacion, FormatoFechaCommon.DIA_MES_ANIO)
										+ "  " + "Radicación:" + lorc_rc.getTurno();
								else
									ls_header = "ANOTACION - Orden " + ls_orden + " - Nro." + lorc_rc.getIdAnotacion()
										+ "  " + " Fecha:" + "  " + "Radicación:" + lorc_rc.getTurno();

								ldbc_dbc = ldbm_model.getColumn(li_column % 1);
								lp_panel.setId("ID_" + lorc_rc.getIdAnotacionPredio() + "-" + li_anotacion);
								li_anotacion = li_anotacion + 1;
								ldbc_dbc.addWidget(lp_panel.getId());
								lp_panel.setHeader(ls_header);
								lp_panel.setStyle("font-weight: bold;");
								lp_panel.setToggleable(true);
								lp_panel.setStyle("width:1100px;");

								HtmlOutputText loht_space;
								loht_space = new HtmlOutputText();
								loht_space.setEscape(false);
								loht_space.setValue(ls_lineaSeparadora);

								HtmlOutputText lhot_revision = new HtmlOutputText();
								lhot_revision.setEscape(false);

								lhot_revision.setStyle("font-weight: bold;font-size: 16px;");

								lhot_revision.setValue(" Revisión completa" + ls_lineaSeparadora);

								HtmlSelectBooleanCheckbox locb_loc = new HtmlSelectBooleanCheckbox();
								locb_loc.setValue(new Boolean(lorc_rc.isRevision()));

								RegistroCalificacion             lorc_dataDoc;
								Date                             ls_fechaDoc;
								String                           ls_codDoc;
								String                           ls_notaria;
								String                           ls_municipioDoc;
								Collection<RegistroCalificacion> lcrc_dataIntervinientes;
								String                           ls_nombreActo;

								lorc_dataDoc        = lorc_rc.getDatosDocumento();
								ls_fechaDoc         = lorc_dataDoc.getFechaDocumento();
								ls_codDoc           = lorc_dataDoc.getCodDocumento();
								ls_notaria          = lorc_dataDoc.getNombreOficina();
								ls_municipioDoc     = lorc_dataDoc.getNombreMunicipio();
								ls_nombreActo       = StringUtils.getStringNotNull(lorc_rc.getNombreActo());

								/* Creacion descripcion del campo */
								HtmlOutputText lhot_ot;
								lhot_ot = new HtmlOutputText();
								lhot_ot.setEscape(false);
								lhot_ot.setStyle("padding-right:2em;display: inline-block;");
								lhot_ot.setValue(
								    "Doc:" + lorc_rc.getNombreDoc() + " " + ls_codDoc + " DEL " + " "
								    + StringUtils.getString(ls_fechaDoc, FormatoFechaCommon.DIA_MES_ANIO) + "  "
								    + ls_notaria + " " + ls_municipioDoc
								);

								HtmlOutputText lhot_descripcion = new HtmlOutputText();
								lhot_descripcion.setEscape(false);
								lhot_descripcion.setValue("VALOR ACTO $ : ");

								BigDecimal lbd_valor;
								String     ls_valor;

								lbd_valor     = lorc_rc.getValor();
								ls_valor      = "";

								if(NumericUtils.isValidBigDecimal(lbd_valor, NumericUtils.getBigDecimal(1D)))
									ls_valor = conversionCientifica(NumericUtils.getDoubleWrapper(lbd_valor));

								HtmlOutputText lhot_valor = new HtmlOutputText();
								lhot_valor.setId(
								    "val_" + lorc_rc.getIdAnotacionPredio() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
								    + lorc_rc.getVersion()
								);
								lhot_valor.setTitle("Valor");
								lhot_valor.setValue(ls_valor);

								/* Creacion especificacion del acto */
								HtmlOutputText lhot_esp = new HtmlOutputText();
								lhot_esp.setId("ot_esp" + li_column);
								lhot_esp.setEscape(false);
								lhot_esp.setStyle("padding-right: 5em;");
								lhot_esp.setValue(
								    ls_lineaSeparadora + "ESPECIFICACION:" + lorc_rc.getNombreGrupoActo() + " "
								    + lorc_rc.getCodActo() + " " + ls_nombreActo + " " + ls_lineaSeparadora
								    + "COMENTARIO: " + StringUtils.getStringNotNull(lorc_rc.getComentario())
								);

								HtmlOutputText lhot_cancelacion = new HtmlOutputText();

								if(lb_cancelacion)
								{
									lhot_cancelacion.setEscape(false);
									lhot_cancelacion.setValue(
									    ls_lineaSeparadora + "ANOTACION CANCELADA : "
									    + lorc_rc.getAnotacionCancelacion() + ls_lineaSeparadora
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
											ls_nombrePersona = StringUtils.getStringNotNull(lorc_tmp.getRazonSocial())
													                          .toString();
										else
											ls_nombrePersona = StringUtils.getStringNotNull(
												    lorc_tmp.getNombrePersona()
												).toString();

										lali_ali.add(NumericUtils.getInteger(1));

										lodt_table.setValue(lali_ali);
										lodt_table.setStyleClass("TableContent");
										lodt_table.setHeaderClass("af_column_header-text SomeBorderStyle");
										lodt_table.setColumnClasses("af_column_cell-text OraTableBorder1111");

										// lodt_table.setBorder(1);
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
										lhtm_tipoDoc.setValue(ls_tipoDoc + " " + lorc_tmp.getDocumento());
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

										lodt_table.getChildren().add(luic_rol);
										lodt_table.getChildren().add(luic_nombre);
										lodt_table.getChildren().add(luic_tipoDoc);
										lodt_table.getChildren().add(luic_propietario);
										lodt_table.getChildren().add(luic_porcetaje);
										lodt_table.getChildren().add(luic_interesadaRR);

										lp_panel.getChildren().add(lodt_table);
									}
								}

								/* Agregar boton al panel */
								CommandButton lohmb_button = new CommandButton();
								lohmb_button.setAjax(true);
								lohmb_button.setValue("Guardar anotación");

								((UICommand)lohmb_button).addActionListener(
								    new ActionListener()
									{
										@Override
										public void processAction(ActionEvent aae_ae)
										{
											List<UIComponent>    lui_ui;
											String               ls_idPanel;
											Boolean              lb_b;
											String               ls_codActo;
											RegistroCalificacion lorc_rc;

											lb_b           = new Boolean(false);
											ls_codActo     = "";
											lorc_rc = null;

											HtmlCommandButton lhcb_cb;

											lhcb_cb     = (HtmlCommandButton)aae_ae.getSource();

											lui_ui         = lhcb_cb.getParent().getChildren();
											ls_idPanel = lhcb_cb.getParent().getId();

											try
											{
												for(UIComponent lui_childPanel : lui_ui)
												{
													if(lui_childPanel instanceof HtmlSelectBooleanCheckbox)
													{
														HtmlSelectBooleanCheckbox loiu_uio = (HtmlSelectBooleanCheckbox)lui_childPanel;
														lb_b = (Boolean)loiu_uio.getValue();

														break;
													}
												}

												if((lb_b != null))
												{
													String ls_revision;

													ls_revision     = lb_b.booleanValue() ? "S" : "N";

													lorc_rc = modificarAnotacion(lui_ui, ls_idPanel, ls_revision);
												}

												if(getMatriculas().getAllMatriculas().size() > 1)
												{
													if(lorc_rc != null)
													{
														ls_codActo = StringUtils.getStringNotNull(lorc_rc.getCodActo());
														setIdAnotacionPredioSeleccionado(
														    lorc_rc.getIdAnotacionPredio()
														);
													}

													StringBuilder lsb_mensajeDialog;

													lsb_mensajeDialog = new StringBuilder();

													lsb_mensajeDialog.append(
													    "Señor usuario desea copiar las modificaciones de esta anotación en las demás matrículas relacionadas con el acto de naturaleza jurídica "
													);
													lsb_mensajeDialog.append(ls_codActo);
													lsb_mensajeDialog.append(" ");
													lsb_mensajeDialog.append(ls_nombreActo);

													setMsjCopiarAnotaciones(lsb_mensajeDialog.toString());

													PrimeFaces.current()
														          .executeScript("PF('dlgcopiarAnotaciones').show();");
													PrimeFaces.current().ajax().update("dlgcopiarAnotaciones");
												}
											}
											catch(B2BException lbe_lbe)
											{
												addMessage(lbe_lbe);
												PrimeFaces.current().ajax().update(is_messageIdGrowl);
											}
										}
									}
								);

								CommandButton lohmb_updateInt = new CommandButton();
								lohmb_updateInt.setValue("Modificar interviniente");

								((UICommand)lohmb_updateInt).addActionListener(
								    new ActionListener()
									{
										@Override
										public void processAction(ActionEvent aae_ae)
										{
											String                    ls_IdPanel;
											FacesContext              lfc_context;
											BeanGestionIntervinientes lbrc_bean;
											HtmlCommandButton         lhcb_cb;
											ExternalContext           lec_ec;
											List<UIComponent>         lui_ui;

											lfc_context     = FacesUtils.getFacesContext();
											lbrc_bean     = (BeanGestionIntervinientes)lfc_context.getApplication()
													                                                  .evaluateExpressionGet(
													    lfc_context, BeanNameCommon.BEAN_GESTION_INTERVINIENTES,
													    BeanGestionIntervinientes.class
													);

											lhcb_cb        = (HtmlCommandButton)aae_ae.getSource();
											lui_ui         = lhcb_cb.getParent().getChildren();
											ls_IdPanel = lhcb_cb.getParent().getId();

											try
											{
												modificarAnotacion(lui_ui, ls_IdPanel, null);
											}
											catch(B2BException le_e)
											{
												clh_LOGGER.error("processAction", le_e);
											}

											ls_IdPanel = generateId(ls_IdPanel);

											lbrc_bean.setIdAnotacionPredio(ls_IdPanel);
											lbrc_bean.setDatatable(true);
											lbrc_bean.dataIntervinientes();

											try
											{
												BeanDireccion lbd_beanDireccion;

												lbd_beanDireccion = getBeanDireccion();

												lbd_beanDireccion.limpiarBeanDireccion();
												lbd_beanDireccion.setForm(BeanGestionIntervinientes.is_idForm);
												lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
												lbd_beanDireccion.setDeshabilitarResidencia(false);
												lbd_beanDireccion.setHabilitadoPorConsulta(true);
												lbrc_bean.limpiarIntervinientes();
											}
											catch(B2BException le_e)
											{
												clh_LOGGER.error("processAction", le_e);
											}

											lbrc_bean.setHabilitaSecuencia(isHabilitaSecuencia());
											lbrc_bean.setTurno(getTurno());
											lbrc_bean.setActoConEnglobe(isActoConEnglobe());

											lec_ec = FacesContext.getCurrentInstance().getExternalContext();

											try
											{
												lec_ec.redirect("gestionIntervinientes.jsf");
											}
											catch(IOException le_e)
											{
												clh_LOGGER.error("processAction", le_e);
											}
										}
									}
								);

								CommandButton locb_modificarAnotacion = new CommandButton();
								locb_modificarAnotacion.setAjax(true);
								locb_modificarAnotacion.setValue("Modificar anotación");

								((UICommand)locb_modificarAnotacion).addActionListener(
								    new ActionListener()
									{
										@Override
										public void processAction(ActionEvent aae_ae)
										{
											String               ls_IdPanel;
											RegistroCalificacion lorc_tmp;
											CommandButton        lhcb_cb;

											lhcb_cb        = (CommandButton)aae_ae.getSource();
											ls_IdPanel     = lhcb_cb.getParent().getId();
											ls_IdPanel     = generateId(ls_IdPanel);
											lorc_tmp = new RegistroCalificacion();

											/* Llenar objeto detalle anotacion */
											try
											{
												if(StringUtils.isValidString(ls_IdPanel))
												{
													lorc_tmp.setIdAnotacionPredio(ls_IdPanel);
													lorc_tmp = irr_calificacionRemote.datalleAnotaciones(lorc_tmp);

													setAnotacionPredioDetalle(null);
													setDocumentoDetalle(null);

													if(lorc_tmp != null)
													{
														com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

														lap_anotacionPredio = lorc_tmp.getDataAnotacionPredio();

														if(lap_anotacionPredio != null)
														{
															String ls_idNaturalezJuridica;

															ls_idNaturalezJuridica = lap_anotacionPredio
																	.getIdNaturalezaJuridica();

															if(StringUtils.isValidString(ls_idNaturalezJuridica))
															{
																setIdNaturalezaJuridicaInicial(ls_idNaturalezJuridica);
																setRequiereCuantiaTipoActo(true);

																NaturalezaJuridica lnj_naturalezaJuridica;

																lnj_naturalezaJuridica = irr_referenceRemote
																		.findNaturalezaJuridicaById(
																		    ls_idNaturalezJuridica
																		);

																if(lnj_naturalezaJuridica != null)
																{
																	String ls_grupo;

																	ls_grupo = lnj_naturalezaJuridica.getIdGrupoNatJur();

																	if(StringUtils.isValidString(ls_grupo))
																	{
																		lap_anotacionPredio.setGrupoNaturaleza(
																		    ls_grupo
																		);

																		if(
																		    ls_grupo.equalsIgnoreCase(
																			        getActoCancelacion()
																			    ) || ls_grupo.equalsIgnoreCase("0800")
																		)
																			setMostrarAnotacionCancela(false);
																		else
																			setMostrarAnotacionCancela(true);
																	}
																}
															}

															setNaturalezaJuridicaAntigua(
															    lap_anotacionPredio.getIdNaturalezaJuridica()
															);
														}

														setAnotacionPredioDetalle(lap_anotacionPredio);
														setDocumentoDetalle(lorc_tmp.getDataDocumento());

														if(
														    CollectionUtils.isValidCollection(
															        lorc_tmp.getAnotacionesCancelacion()
															    )
														)
															setAnotacionACancelar(lorc_tmp.getAnotacionesCancelacion());

														PrimeFaces.current()
															          .executeScript("PF('detalleAnotacion').show();");
														PrimeFaces.current().ajax().update("detalleAnotacion");
													}
												}
											}
											catch(B2BException le_e)
											{
												clh_LOGGER.error("processAction", le_e);
											}
										}
									}
								);

								final Collection<Long> lcl_anotacionesParciales = liorc_detail.getAnotacionesParciales();

								CommandButton          locb_eliminarAnotacion = new CommandButton();
								locb_eliminarAnotacion.setAjax(true);
								locb_eliminarAnotacion.setValue("Eliminar anotación");

								((UICommand)locb_eliminarAnotacion).addActionListener(
								    new ActionListener()
									{
										@Override
										public void processAction(ActionEvent aae_ae)
										{
											setEliminarAnotacion(null);

											String        ls_IdPanel;
											CommandButton lhcb_cb;

											lhcb_cb        = (CommandButton)aae_ae.getSource();
											ls_IdPanel     = lhcb_cb.getParent().getId();
											ls_IdPanel = generateId(ls_IdPanel);

											if(StringUtils.isValidString(ls_IdPanel))
											{
												EliminarAnotacion           lea_datosEliminar;
												RegistroParcialCalificacion lrpc_rpc;

												lea_datosEliminar     = new EliminarAnotacion();
												lrpc_rpc = new RegistroParcialCalificacion();

												lrpc_rpc.setMatriculaCompleta(getCirculo());
												lrpc_rpc.setIdAnotacion(lorc_rc.getIdAnotacion());
												lrpc_rpc.setIdAnotacionPredio(ls_IdPanel);
												lrpc_rpc.setIdNaturalezaJuridica(lorc_rc.getCodActo());
												lrpc_rpc.setEspecificacion(
												    lorc_rc.getNombreGrupoActo() + " " + lorc_rc.getCodActo() + " "
												    + lorc_rc.getNombreActo()
												);

												lea_datosEliminar.setAnotaciones(locri_tmp);
												lea_datosEliminar.setIdAnotacionesParciales(lcl_anotacionesParciales);
												lea_datosEliminar.setRegistroParcialCalificacion(lrpc_rpc);
												lea_datosEliminar.setIdAnotacionPredio(ls_IdPanel);

												setEliminarAnotacion(lea_datosEliminar);

												try
												{
													setDesactivarEliminarParcial(
													    irr_calificacionRemote.validarEliminarAnotacionParcial(
													        lea_datosEliminar
													    )
													);
												}
												catch(B2BException le_e)
												{
													clh_LOGGER.error("processAction", le_e);
												}

												PrimeFaces.current().executeScript(
												    "PF('dlgEliminarAnotacion').show();"
												);
												PrimeFaces.current().ajax().update("fDialogEliminarAnotacion");
											}
										}
									}
								);

								lp_panel.getChildren().add(locb_loc);
								lp_panel.getChildren().add(lhot_revision);
								lp_panel.getChildren().add(lohmb_button);
								lp_panel.getChildren().add(lohmb_updateInt);
								lp_panel.getChildren().add(locb_modificarAnotacion);
								lp_panel.getChildren().add(locb_eliminarAnotacion);
								getDataModel().getChildren().add(lp_panel);
								li_column = li_column + 1;
							}
						}
					}

					setPanels(ldbc_dbc.getWidgets());
					setMatriculasInformacion(null);
				}

				if(ab_alertaProhibicion)
				{
					if(StringUtils.isValidString(ls_idTurno))
					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = irr_calificacionRemote.findTurno(ls_idTurno);

						if(lt_turno != null)
						{
							Solicitud                    ls_solcitud;
							RegistroAnotacionProhibicion lrap_rap;

							ls_solcitud = new Solicitud();

							ls_solcitud.setIdSolicitud(lt_turno.getIdSolicitud());

							lrap_rap = irr_registroRemote.findRegistroAnotacionProhibicionByCirMat(
								    ls_solcitud, ls_circulo, NumericUtils.getLong(ll_matricula), getUserId(),
								    getLocalIpAddress(), getRemoteIpAddress()
								);

							if(lrap_rap != null)
							{
								if(lrap_rap.isAlerta())
								{
									String ls_errorKey;

									ls_errorKey = lrap_rap.getErrorKey();

									if(StringUtils.isValidString(ls_errorKey))
									{
										addMessage(
										    "I", getMessages().getString(ls_errorKey, lrap_rap.getMessageArgs())
										);
										FacesContext.getCurrentInstance().getExternalContext().getFlash()
											            .setKeepMessages(true);
									}
								}
							}
						}
					}
				}
			}

			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(B2BException lb2b_2b)
		{
			ls_return = null;

			addMessage(lb2b_2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}

		return ls_return;
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void consultarAnotacionCancelacion()
	{
		try
		{
			String ls_naturaleza;
			ls_naturaleza = getAnotacionPredioDetalle().getIdNaturalezaJuridica();

			if(StringUtils.isValidString(ls_naturaleza))
			{
				NaturalezaJuridica lnj_parametros;

				lnj_parametros = new NaturalezaJuridica();
				lnj_parametros.setIdNaturalezaJuridica(ls_naturaleza);

				lnj_parametros = iasr_antiguoSistemaRemote.findNaturalezaJuridicaById(lnj_parametros);

				if(lnj_parametros != null)
				{
					String ls_grupo;
					String ls_descripcion;

					ls_grupo           = lnj_parametros.getIdGrupoNatJur();
					ls_descripcion     = lnj_parametros.getNombre();

					{
						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_tmp;
						lap_tmp = getAnotacionPredioDetalle();

						if(lap_tmp != null)
							lap_tmp.setEspecificacion(ls_descripcion);
					}

					if(
					    StringUtils.isValidString(ls_grupo)
						    && (ls_grupo.equalsIgnoreCase(getActoCancelacion()) || ls_grupo.equalsIgnoreCase("0800"))
					)
						setMostrarAnotacionCancela(false);
					else
						setMostrarAnotacionCancela(true);
				}
			}
			else
			{
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_tmp;
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

	/**
	 * Consultar anotacion tipo acto.
	 */
	public void consultarAnotacionTipoActo()
	{
		try
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = getAnotacionPredioDetalle();

			if(lap_anotacionPredio != null)
			{
				String ls_idTipoActo;

				ls_idTipoActo = lap_anotacionPredio.getIdNaturalezaJuridica();

				if(StringUtils.isValidString(ls_idTipoActo))
				{
					TipoActo lta_parametros;

					lta_parametros = new TipoActo();
					lta_parametros.setIdTipoActo(ls_idTipoActo);

					lta_parametros = irr_registroRemote.findTipoActoById(lta_parametros);

					if(lta_parametros != null)
					{
						String ls_requiereCuantia;

						ls_requiereCuantia = lta_parametros.getRequiereCuantia();

						if(StringUtils.isValidString(ls_requiereCuantia))
						{
							if(ls_requiereCuantia.equalsIgnoreCase(EstadoCommon.N))
								setRequiereCuantiaTipoActo(false);
							else
								setRequiereCuantiaTipoActo(true);
						}

						String ls_actoSinCuantia;

						ls_actoSinCuantia = lta_parametros.getActoSinCuantia();

						if(
						    StringUtils.isValidString(ls_actoSinCuantia)
							    && ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.S)
						)
							setRequiereCuantiaTipoActo(false);

						String ls_cambioTipoCuantia;

						ls_cambioTipoCuantia = lta_parametros.getCambioTipoCuantia();

						if(ls_cambioTipoCuantia != null)
						{
							if(ls_cambioTipoCuantia.equalsIgnoreCase(EstadoCommon.S))
							{
								if(ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.S))
									PrimeFaces.current().executeScript("PF('dlg3').show();");
								else if(ls_actoSinCuantia.equalsIgnoreCase(EstadoCommon.N))
									PrimeFaces.current().executeScript("PF('dlg4').show();");
							}
						}
						else
						{
							String ls_idTipoActoInicial;

							ls_idTipoActoInicial = getIdNaturalezaJuridicaInicial();

							if(StringUtils.isValidString(ls_idTipoActo))
							{
								TipoActo lta_parametroInicial;

								lta_parametroInicial = new TipoActo();
								lta_parametroInicial.setIdTipoActo(ls_idTipoActoInicial);

								lta_parametroInicial = irr_registroRemote.findTipoActoById(lta_parametroInicial);

								if(lta_parametroInicial != null)
								{
									if(
									    !lta_parametros.getActoSinCuantia()
										                   .equalsIgnoreCase(lta_parametroInicial.getActoSinCuantia())
									)
									{
										getAnotacionPredioDetalle()
											    .setIdNaturalezaJuridica(getIdNaturalezaJuridicaInicial());
										throw new B2BException(
										    ErrorKeys.ERROR_EL_ACTO_NO_CUMPLE_CON_EL_MISMO_TIPO_TARIFA
										);
									}

									if(
									    !lta_parametros.getImpuestoRegistro()
										                   .equalsIgnoreCase(
										        lta_parametroInicial.getImpuestoRegistro()
										    )
									)
									{
										getAnotacionPredioDetalle()
											    .setIdNaturalezaJuridica(getIdNaturalezaJuridicaInicial());
										throw new B2BException(
										    ErrorKeys.ERROR_EL_ACTO_NO_CUMPLE_CON_EL_REQUISITO_IMPUESTO
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void consultarPersonaDocumento()
	{
		consultarPersonaDocumento(false);
	}

	/**
	 * Consultar persona documento.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 */
	public void consultarPersonaDocumento(boolean ab_desdeBaldios)
	{
		try
		{
			if(ab_desdeBaldios)
				super.consultarPersonaDocumento();
			else
			{
				Persona  lp_parametros;
				Registro lr_registro;

				lp_parametros     = getPersonaConsulta();
				lr_registro       = new Registro();

				lr_registro.setPersona(lp_parametros);
				setIdPersonaSeleccion(null);

				Registro resultado = irr_registroRemote.findPersonByDocument(lr_registro, getUserId());

				if(resultado != null)
				{
					setListadoIntervinientes(resultado.getListadoPersona());
					setMostrarListadoPersonas(true);

					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
					PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_INTERVINIENTES);
			}
		}
		catch(B2BException lb2be_e)
		{
			setMostrarListadoPersonas(false);
			setDeshabilitarDatosInterviniente(false);
			setListadoIntervinientes(null);
			setPersona(null);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Busca en base de datos si el tipo de documento y el numero de documento ingresados tienen registros en la base de
	 * datos.
	 *
	 * @param ab_mostrarAlertas Define si durante el proceso de consulta va a mostrar alertas informativas en pantalla.
	 */
	public void consultarPersonaDocumentoInteresado(boolean ab_mostrarAlertas)
	{
		boolean lb_focus;

		lb_focus = true;

		setRendered(false);
		setPersona(null);
		setDeshabilitarDatosBasicos(false);
		setMostrarDatosConsulta(false);

		try
		{
			BeanDireccion lbd_beanDireccion;
			Persona       lp_parametros;
			Registro      lr_registro;
			FacesContext  lfc_context;

			lfc_context           = FacesContext.getCurrentInstance();
			lbd_beanDireccion     = getBeanDireccion();
			lp_parametros         = new Persona();
			lr_registro           = new Registro();

			if(ab_mostrarAlertas)
			{
				limpiarInteresados();
				setMostrarDatosBasicos(false);
			}

			lp_parametros.setNumeroDocumento(getDocumentoInteresado());
			lp_parametros.setTipoDocIdentidad(getTipoDocInteresado());

			{
				String ls_idTipoDocumento;

				ls_idTipoDocumento     = lp_parametros.getTipoDocIdentidad();
				lb_focus               = validateStyles(
					    is_idForm + "idSOMTipoDocIdentidadInteresado", lfc_context, ls_idTipoDocumento, lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoDocumento))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
			}

			{
				String ls_documentoIdentidad;

				ls_documentoIdentidad     = lp_parametros.getNumeroDocumento();
				lb_focus                  = validateStyles(
					    is_idForm + "idItDocumentoIDInteresado", lfc_context, ls_documentoIdentidad, lb_focus
					);

				if(!StringUtils.isValidString(ls_documentoIdentidad))
					throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
			}

			lbd_beanDireccion.setHabilitadoPorConsulta(true);
			lr_registro.setPersona(lp_parametros);
			setRendered(true);

			Registro lr_resultado = irr_registroRemote.findPersonByDocument(lr_registro, getUserId());

			if(lr_resultado != null)
			{
				setRegistroDatosConsultados(lr_resultado);

				Collection<PersonaDireccion>         lcpd_direccionesResidencia;
				Collection<PersonaDireccion>         lcpd_direccionesCorrespondencia;
				Collection<PersonaTelefono>          lcpt_telefonosFijo;
				Collection<PersonaTelefono>          lcpt_telefonosMovil;
				Collection<PersonaCorreoElectronico> lcpce_correos;

				lcpd_direccionesResidencia          = lr_resultado.getListadoDireccionResidencia();
				lcpd_direccionesCorrespondencia     = lr_resultado.getListadoDireccionCorrespondencia();
				lcpt_telefonosFijo                  = lr_resultado.getListadoTelefonoFijo();
				lcpt_telefonosMovil                 = lr_resultado.getListadoTelefonoMovil();
				lcpce_correos                       = lr_resultado.getListadoCorreoElectronico();

				boolean lb_direccionesResidencia;
				boolean lb_direccionesCorrespondencia;
				boolean lb_telefonosFijo;
				boolean lb_telefonosMovil;
				boolean lb_correos;

				lb_direccionesResidencia            = CollectionUtils.isValidCollection(lcpd_direccionesResidencia);
				lb_direccionesCorrespondencia       = CollectionUtils.isValidCollection(
					    lcpd_direccionesCorrespondencia
					);
				lb_telefonosFijo                    = CollectionUtils.isValidCollection(lcpt_telefonosFijo);
				lb_telefonosMovil                   = CollectionUtils.isValidCollection(lcpt_telefonosMovil);
				lb_correos                          = CollectionUtils.isValidCollection(lcpce_correos);

				setMostrarCorreo(lb_correos);
				setMostrarDireccionesResidencia(lb_direccionesResidencia);
				setMostrarDireccionesCorrespondencia(lb_direccionesCorrespondencia);
				setMostrarTelefonoFijo(lb_telefonosFijo);
				setMostrarTelefonoMovil(lb_telefonosMovil);

				if(ab_mostrarAlertas)
				{
					if(
					    !lb_direccionesResidencia || !lb_direccionesCorrespondencia || !lb_telefonosFijo
						    || !lb_telefonosMovil || !lb_correos
					)
					{
						String ls_mensaje_datos = "Para el registro ingresado no existen: \n";

						if(!lb_direccionesResidencia)
							ls_mensaje_datos = ls_mensaje_datos + "direcciones residencia. \n";

						if(!lb_direccionesCorrespondencia)
							ls_mensaje_datos = ls_mensaje_datos + "direcciones correspondencia. \n";

						if(!lb_telefonosFijo)
							ls_mensaje_datos = ls_mensaje_datos + "teléfonos fijos. \n";

						if(!lb_telefonosMovil)
							ls_mensaje_datos = ls_mensaje_datos + "teléfonos moviles. \n";

						if(!lb_correos)
							ls_mensaje_datos = ls_mensaje_datos + "correos electrónicos. \n";

						addMessage("I", ls_mensaje_datos);
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
					}

					{
						String ls_mensaje;

						ls_mensaje = "Consulta exitosa, verifique los datos.";
						addMessage("I", ls_mensaje);
						PrimeFaces.current().ajax().update(is_messageIdGrowl);
					}
				}
			}
			else
			{
				if(ab_mostrarAlertas)
				{
					String ls_mensaje = "No se encontraron registros, por favor diligencie el formulario";
					addMessage("I", ls_mensaje);
					PrimeFaces.current().ajax().update(is_messageIdGrowl);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para contar carácteres.
	 *
	 * @param as_campo String para contar caracteres
	 * @return devuelve el valor de String
	 */
	public String contar(String as_campo)
	{
		char[] lc_arrayChar;
		int    li_contador;
		String ls_result;

		li_contador = 0;

		if(as_campo != null)
		{
			lc_arrayChar     = as_campo.toCharArray();
			li_contador      = lc_arrayChar.length;
		}

		ls_result = Integer.toString(li_contador);

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contarComplementacion()
	{
		String ls_tmp;
		ls_tmp = contar(getLinderoRegistroCalificacion().getLindero());

		return ls_tmp;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contarComplementacionPredio()
	{
		String ls_tmp;
		ls_tmp = contar(getComplementacionCalificacion().getComplementacionPredio().getComplementacion());

		return ls_tmp;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contarLindero()
	{
		String ls_tmp;
		ls_tmp = contar(getLinderoRegistroCalificacion().getLindero());

		return ls_tmp;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contarLinderoObservaciones()
	{
		String ls_tmp;
		ls_tmp = contar(getLinderoRegistroCalificacion().getObservaciones());

		return ls_tmp;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String contarObservaciones()
	{
		String ls_tmp;
		ls_tmp = contar(getComplementacionCalificacion().getObservaciones());

		return ls_tmp;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ad_valor de ad valor
	 * @return el valor de string
	 */
	public String conversionCientifica(Double ad_valor)
	{
		return conversionCientifica(ad_valor, new DecimalFormat("#,###.00"));
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ad_valor de ad valor
	 * @param adf_format de adf format
	 * @return el valor de string
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
	 * Copiar anotaciones.
	 *
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void copiarAnotaciones()
	    throws IOException, B2BException
	{
		String                           ls_revision;
		String                           ls_idTurno;
		boolean                          lb_opcion;
		RegistroCalificacion             lrc_tmp;
		Collection<RegistroCalificacion> lcrc_rc;

		ls_revision     = StringUtils.getStringNotNull(getOpcionCopiar());
		lb_opcion       = ls_revision.equalsIgnoreCase("true");
		ls_idTurno      = getTurno();
		lrc_tmp         = new RegistroCalificacion();
		lcrc_rc         = null;

		try
		{
			if(lb_opcion)
			{
				lcrc_rc = getInfoMatriculaACopiar();

				if(CollectionUtils.isValidCollection(lcrc_rc))
				{
					Iterator<RegistroCalificacion> lirc_rc;
					boolean                        lb_revision;
					lirc_rc         = lcrc_rc.iterator();
					lb_revision     = false;

					while(lirc_rc.hasNext() && !lb_revision)
					{
						RegistroCalificacion lorc_tmp;
						lorc_tmp = lirc_rc.next();

						if(lorc_tmp != null)
							if(lorc_tmp.isRevision())
								lb_revision = true;
					}

					if(!lb_revision)
						throw new B2BException("Debe seleccionar al menos una matrícula.");
				}
			}

			lrc_tmp.setRevision(lb_opcion);
			lrc_tmp.setAllMatriculas(lcrc_rc);
			lrc_tmp.setTurno(ls_idTurno);
			lrc_tmp.setIdUsuario(getUserId());
			lrc_tmp.setIdAnotacionPredio(getIdAnotacionPredioSeleccionado());
			lrc_tmp.setIdUsuarioModificacion(getUserId());
			lrc_tmp.setIpModificacion(getRemoteIpAddress());

			irr_calificacionRemote.procCopiarAnotaciones(lrc_tmp);
		}
		catch(B2BException lbe_b2b)
		{
			addMessage(lbe_b2b);
			PrimeFaces.current().ajax().update("fRegistroCalif:globalMsg");
		}
		finally
		{
			idb_dataModel = new Dashboard();
			consultaDetalleMatricula(false);

			ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();
			lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
		}
	}

	/**
	 * Copiar lindero matricula.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void copiarLinderoMatricula()
	    throws B2BException
	{
		LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
		llrc_linderoRegistroCalificacion = getLinderoRegistroCalificacion();

		try
		{
			if(llrc_linderoRegistroCalificacion != null)
			{
				String ls_lindero;
				ls_lindero = irr_calificacionRemote.findLindero(llrc_linderoRegistroCalificacion, true);

				if(StringUtils.isValidString(ls_lindero))
					llrc_linderoRegistroCalificacion.setLindero(ls_lindero);
			}
		}
		catch(B2BException lb2b_e)
		{
			addMessage(lb2b_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			llrc_linderoRegistroCalificacion.setLindero(null);
		}
	}

	/**
	 * Delete matricula.
	 *
	 * @param ams_ms correspondiente al valor del tipo de objeto MatriculaSegregacionUi
	 */
	public void deleteMatricula(MatriculaSegregacionUi ams_ms)
	{
		try
		{
			if(ams_ms != null)
			{
				Collection<MatriculaSegregacionUi> lcmsui_msui;
				lcmsui_msui = getDataInfoPredial();

				if(CollectionUtils.isValidCollection(lcmsui_msui))
				{
					lcmsui_msui.remove(ams_ms);

					setDataInfoPredial(lcmsui_msui);

					throw new B2BException("Registro Eliminado.");
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
	 *  {@inheritdoc}.
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
	 * Desmarcar cll actos.
	 *
	 * @param arc_anotacionSeleccionada correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void desmarcarCllActos(RegistroCalificacion arc_anotacionSeleccionada)
	    throws B2BException
	{
		if(arc_anotacionSeleccionada != null)
		{
			Collection<RegistroCalificacion> lcrc_cllAnotaciones;

			lcrc_cllAnotaciones = getAnotacionACancelarNuevo();

			if(!isPrimerSelected())
			{
				if(CollectionUtils.isValidCollection(lcrc_cllAnotaciones))
				{
					for(RegistroCalificacion lrc_temp : lcrc_cllAnotaciones)
					{
						if(lrc_temp != null)
							lrc_temp.setSelected(arc_anotacionSeleccionada == lrc_temp);
					}

					setAnotacionACancelarNuevo(lcrc_cllAnotaciones);
					setPrimerSelected(true);
				}

				{
					Documento ld_doc;

					ld_doc = arc_anotacionSeleccionada.getDataDocumento();

					if(ld_doc != null)
						setDocumento(ld_doc);
				}

				{
					Long   ls_idAnotacion;
					String ls_circulo;
					Long   ls_matricula;

					ls_idAnotacion     = arc_anotacionSeleccionada.getIdAnotacion();
					ls_circulo         = arc_anotacionSeleccionada.getIdCirculo();
					ls_matricula       = arc_anotacionSeleccionada.getIdMatricula();

					if((ls_idAnotacion != null) && StringUtils.isValidString(ls_circulo) && (ls_matricula != null))
					{
						Collection<Anotacion> lca_intervinientes;

						lca_intervinientes = irr_calificacionRemote.findIntervinientes(
							    ls_idAnotacion, ls_circulo, ls_matricula
							);

						if(lca_intervinientes != null)
							setIntervinientesAgregados(lca_intervinientes);
					}
				}
			}
			else
			{
				if(CollectionUtils.isValidCollection(lcrc_cllAnotaciones))
				{
					for(RegistroCalificacion lrc_temp : lcrc_cllAnotaciones)
					{
						if(lrc_temp != null)
							lrc_temp.setSelected(false);
					}

					setAnotacionACancelarNuevo(lcrc_cllAnotaciones);
					setPrimerSelected(false);
					setIntervinientesAgregados(null);
				}
			}
		}
	}

	/**
	 * Detalle matricula reloteo.
	 */
	public void detalleMatriculaReloteo()
	{
		try
		{
			RegistroCalificacion lrc_rctmp;
			RegistroCalificacion lrc_rc;
			String               ls_idCirculo;

			lrc_rctmp     = new RegistroCalificacion();
			lrc_rc        = getDetalleCalificacionReloteo();

			if(lrc_rc != null)
			{
				ls_idCirculo = lrc_rc.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					lrc_rctmp = irr_calificacionRemote.findMatriculas(
						    ls_idCirculo, getIdTurnoHistoria(), getTurno(), getUserId(), getRemoteIpAddress(), false,
						    EtapaCommon.ID_ETAPA_CALIFICACION
						);

					if(lrc_rctmp != null)
					{
						setMatriculas(lrc_rctmp);

						matriculasAHeredar();
						cargarDireccionPredio(true);
						actualizarDireccionPredioData();
						cargarLinderos();
						setBtnRegresarLoteo(false);
						setBtnSiguienteLoteo(true);
						limpiarTabsReloteo();
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
	 * Carga la información del detalle de las matrículas.
	 *
	 * @return enlace de la pagina de detalle de matrículas
	 */
	public String detalleMatriculasInformacion()
	{
		String ls_return;

		ls_return = null;

		try
		{
			String       ls_idPredioRegistro;
			FacesContext lfc_context;

			lfc_context             = FacesUtils.getFacesContext();
			ls_idPredioRegistro     = FacesUtils.getStringFacesParameter("idPredioRegistro");

			if(StringUtils.isValidString(ls_idPredioRegistro))
			{
				BeanDetalleRegistroCalificacion lbrc_bean;

				lbrc_bean = (BeanDetalleRegistroCalificacion)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
						    BeanDetalleRegistroCalificacion.class
						);

				if(lbrc_bean != null)
				{
					lbrc_bean.setShowWizard(true);
					lbrc_bean.accionVolver(false);
					lbrc_bean.setIdPredioRegistro(ls_idPredioRegistro);
					lbrc_bean.setIdTurno(getTurno());
					lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbrc_bean.setDevolucion(isDevolucion());
					lbrc_bean.setProcesoLoteo(isProcesoLoteo());
					lbrc_bean.setProcesoReloteo(isProcesoReloteo());
					lbrc_bean.setDivisionMaterial(isDivisionMaterial());
					lbrc_bean.setValidarPropiedadHorizontal(isValidarPropiedadHorizontal());
					lbrc_bean.setNuevaLinderos(isNuevaLinderos());
					lbrc_bean.cargarMatricula(false);
					lbrc_bean.cargarDatosParametricosDireccion();
					lbrc_bean.setEjecucionCorrecciones(false);
					lbrc_bean.setCorreccionAnotacion(false);

					ls_return = NavegacionCommon.DETALLE_REGISTRO_CALIFICACION;
				}
			}
		}
		catch(Exception lb2b_2b)
		{
			addMessage(new B2BException(lb2b_2b.getMessage()));
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Direccion seleccionada.
	 *
	 * @param adp_dp correspondiente al valor del tipo de objeto DireccionPredio
	 * @param arc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void direccionSeleccionada(DireccionPredio adp_dp, RegistroCalificacion arc_rc)
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if((adp_dp != null) && (arc_rc != null) && (lrc_data != null))
		{
			String ls_matricula;
			String ls_idDireccion;

			ls_matricula       = arc_rc.getIdCirculo();
			ls_idDireccion     = adp_dp.getIdDireccion();

			if(StringUtils.isValidString(ls_matricula) && StringUtils.isValidString(ls_idDireccion))
			{
				HashMap<String, DireccionPredio> lhmsdp_direcciones;
				String                           ls_idData;

				lhmsdp_direcciones     = lrc_data.getDireccionesSeleccionadas();
				ls_idData              = ls_matricula + "-" + ls_idDireccion;

				if(!CollectionUtils.isValidCollection(lhmsdp_direcciones))
					lhmsdp_direcciones = new HashMap<String, DireccionPredio>();

				if(adp_dp.isSeleccionado())
				{
					if(!lhmsdp_direcciones.containsKey(ls_idData))
						lhmsdp_direcciones.put(ls_idData, adp_dp);
				}
				else
				{
					if(lhmsdp_direcciones.containsKey(ls_idData))
						lhmsdp_direcciones.remove(ls_idData);
				}

				lrc_data.setDireccionesSeleccionadas(lhmsdp_direcciones);
				setMatriculas(lrc_data);
			}
		}
	}

	/**
	 * Editar copiar de una matricula tabs.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void editarCopiarDeUnaMatriculaTabs()
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Elegir direccion.
	 *
	 * @param adp_arg correspondiente al valor del tipo de objeto DireccionPredio
	 */
	public void elegirDireccion(DireccionPredio adp_arg)
	{
		if(adp_arg != null)
		{
			Collection<DireccionPredio> lcdp_direcciones;

			lcdp_direcciones = getDirecciones();

			if(CollectionUtils.isValidCollection(lcdp_direcciones))
			{
				Iterator<DireccionPredio> lidp_iterator;

				lidp_iterator = lcdp_direcciones.iterator();

				while(lidp_iterator.hasNext())
				{
					DireccionPredio ldp_direccion;

					ldp_direccion = lidp_iterator.next();

					if((ldp_direccion != null) && ldp_direccion.isSeleccionado() && (ldp_direccion != adp_arg))
						ldp_direccion.setSeleccionado(false);
				}
			}
		}
	}

	/**
	 * Eliminar anotacion.
	 *
	 * @param al_idAnotacion correspondiente al valor del tipo de objeto long
	 */
	public void eliminarAnotacion(long al_idAnotacion)
	{
		Collection<Anotacion> lca_datos;
		Collection<Anotacion> lca_datosNuevos;

		lca_datos           = getAnotacionesAgregadas();
		lca_datosNuevos     = new ArrayList<Anotacion>();

		if(CollectionUtils.isValidCollection(lca_datos))
		{
			for(Anotacion la_iterador : lca_datos)
			{
				if(la_iterador != null)
				{
					long ll_idAnotacion;
					ll_idAnotacion = la_iterador.getIdAnotacion();

					if(ll_idAnotacion != al_idAnotacion)
						lca_datosNuevos.add(la_iterador);
				}
			}

			if(!CollectionUtils.isValidCollection(lca_datosNuevos))
				setAnotacionAgregada(false);

			setAnotacionesAgregadas(lca_datosNuevos);
			setCantidadAnotaciones(getCantidadAnotaciones() - 1);

			PrimeFaces lpf_faces;

			lpf_faces = PrimeFaces.current();

			lpf_faces.ajax().update("faddAnotacion:pDatosAnotacion");
			lpf_faces.ajax().update("faddAnotacion:cbTerminar");
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aadap_detalle de aadap detalle
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
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
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String eliminarError()
	    throws B2BException
	{
		PrimeFaces lpf_faces;
		String     ls_return;

		lpf_faces     = PrimeFaces.current();
		ls_return     = null;

		try
		{
			EliminarAnotacion lea_datosEliminar;

			lea_datosEliminar = getEliminarAnotacion();

			if(lea_datosEliminar != null)
			{
				Collection<RegistroCalificacion> lcrc_tmp;
				String                           ls_idAnotacionPredio;

				lcrc_tmp                 = lea_datosEliminar.getAnotaciones();
				ls_idAnotacionPredio     = lea_datosEliminar.getIdAnotacionPredio();

				lea_datosEliminar.setIdUsuarioModificacion(getUserId());
				lea_datosEliminar.setIpModificacion(getRemoteIpAddress());

				if(lcrc_tmp.size() <= 1)
					throw new B2BException(ErrorKeys.ERROR_ANOTACION_BORRAR);
				else
				{
					if(StringUtils.isValidString(ls_idAnotacionPredio))
					{
						RegistroCalificacion lorc_tmp;
						lorc_tmp = new RegistroCalificacion();

						lea_datosEliminar.setIdUsuarioCreacion(getUserId());

						lorc_tmp.setIdAnotacionPredio(ls_idAnotacionPredio);
						lorc_tmp.setRegistroParcialCalificacion(null);
						lorc_tmp.setIdUsuarioModificacion(getUserId());
						lorc_tmp.setIpModificacion(getRemoteIpAddress());

						irr_calificacionRemote.modificarIDS(lea_datosEliminar);
						irr_calificacionRemote.eliminarAnotacion(lorc_tmp);

						idb_dataModel = new Dashboard();
						consultaDetalleMatricula(false);

						lpf_faces.ajax().update("fDetalleReg");

						ls_return = NavegacionCommon.DETALLE_REGISTRO;
					}
				}
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
			setEliminarAnotacion(null);
		}
		finally
		{
			lpf_faces.ajax().update(is_messageIdGrowlDetail);
		}

		return ls_return;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aa_anotacion de aa anotacion
	 */
	public void eliminarInterviniente(Anotacion aa_anotacion)
	{
		Collection<Anotacion> lca_datos;

		lca_datos = getIntervinientesAgregados();

		if((aa_anotacion != null) && CollectionUtils.isValidCollection(lca_datos) && lca_datos.contains(aa_anotacion))
			lca_datos.remove(aa_anotacion);
		else
		{
			String ls_mensaje = "Antes de eliminar un registro debe terminar de modificar el interviniente.";
			addMessage("W", ls_mensaje);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String eliminarParcial()
	    throws B2BException
	{
		EliminarAnotacion lea_datosEliminar;
		String            ls_return;

		lea_datosEliminar     = getEliminarAnotacion();
		ls_return             = null;

		try
		{
			if(lea_datosEliminar != null)
			{
				Collection<RegistroCalificacion> lcrc_tmp;
				Collection<Long>                 lcl_anotacionesParciales;
				FacesContext                     lfc_context;
				RegistroCalificacion             lrc_rc;
				BeanNotaDevolutiva               lbrc_bean;

				lcrc_tmp                     = lea_datosEliminar.getAnotaciones();
				lcl_anotacionesParciales     = lea_datosEliminar.getIdAnotacionesParciales();
				lfc_context                  = FacesUtils.getFacesContext();
				lrc_rc                       = new RegistroCalificacion();

				lea_datosEliminar.setIdUsuarioModificacion(getUserId());
				lea_datosEliminar.setIpModificacion(getRemoteIpAddress());

				if(lcrc_tmp.size() <= 1)
					throw new B2BException(ErrorKeys.ERROR_ANOTACION_REG_PARCIAL_BORRAR);
				else
				{
					if(!CollectionUtils.isValidCollection(lcl_anotacionesParciales))
						throw new B2BException(ErrorKeys.ERROR_ANOTACION_REG_PARCIAL_BORRAR);
				}

				lbrc_bean = (BeanNotaDevolutiva)lfc_context.getApplication()
						                                       .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_NOTA_DEVOLUTIVA, BeanNotaDevolutiva.class
						);

				if(lbrc_bean != null)
				{
					String ls_idAnotacionParcial;

					ls_idAnotacionParcial = lea_datosEliminar.getIdAnotacionPredio();

					if(StringUtils.isValidString(ls_idAnotacionParcial))
					{
						irr_calificacionRemote.modificarIDS(lea_datosEliminar);
						irr_calificacionRemote.inactivarAnotacionParcial(
						    ls_idAnotacionParcial, getUserId(), getRemoteIpAddress()
						);
					}

					lbrc_bean.clear();
					lbrc_bean.setIdMotivo(NumericUtils.getLongWrapper(MotivoTramiteCommon.NOTA_DEVOLUTIVA));
					lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbrc_bean.setRegistroParcial(true);
					lbrc_bean.setPdfGenerado(false);

					lbrc_bean.setRegistroParcialCalificacion(lea_datosEliminar.getRegistroParcialCalificacion());

					{
						TurnoHistoria lth_th;

						lth_th = new TurnoHistoria();

						lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

						lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

						if(getObservaciones() != null)
							lth_th.setObservaciones(getObservaciones());

						if(lth_th != null)
						{
							String ls_s;

							ls_s = lth_th.getIdSolicitud();

							lbrc_bean.setTurno(lth_th.getIdTurno());

							if(StringUtils.isValidString(ls_s))
							{
								Solicitud ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(ls_s);
								ls_solicitud.setDocumento(false);
								ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

								if(ls_solicitud != null)
									lbrc_bean.setNir(ls_solicitud.getNir());
							}
						}
					}

					lrc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

					lbrc_bean.setPdfCancelacion(irr_calificacionRemote.validacionCancelacion(lrc_rc));

					ls_return = NavegacionCommon.NOTA_DEVOLUTIVA;
				}
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
			ls_return = null;
			setEliminarAnotacion(null);
		}

		return ls_return;
	}

	/**
	 * Envia el proceso a etapa de digitador masivo.
	 *
	 * @return Si el proceso fue exitoso retorna el enlace a la pagina principal de turnos de calificación
	 */
	public String enviarADigitador()
	{
		String ls_return;

		ls_return = null;

		try
		{
			boolean                lb_matriculasSegregadas;
			Collection<AreaPredio> lcap_ap;
			Map<String, Boolean>   lhmso_revisionMatriculas;
			RegistroCalificacion   lrc_tmp;

			lcap_ap                      = getMatriculasInformacion();
			lhmso_revisionMatriculas     = getRevisionMatriculas();

			lrc_tmp = getMatriculas();

			if(lrc_tmp == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(lrc_tmp.isCierreFolio())
				validarCierreFolio();

			lrc_tmp.setIdTurnoHistoria(Long.valueOf(getIdTurnoHistoria()));
			lrc_tmp.setUserId(getUserId());
			lrc_tmp.setIpModificacion(getRemoteIpAddress());
			lb_matriculasSegregadas = CollectionUtils.isValidCollection(lcap_ap);
			lrc_tmp.setIndSegregacion(lb_matriculasSegregadas);
			lrc_tmp.setEnvioCalificador(true);

			if(getObservaciones() != null)
				lrc_tmp.setObservaciones(getObservaciones());

			if(lb_matriculasSegregadas)
			{
				lrc_tmp.setTotalMatriculasRevision(lcap_ap.size());
				lrc_tmp.setTotalRevision(lhmso_revisionMatriculas.size());
				lrc_tmp.setMatriculasSegregadas(lcap_ap);
			}

			if(CollectionUtils.isValidCollection(lcap_ap))
			{
				String ls_matriculaErrada;

				ls_matriculaErrada = irr_calificacionRemote.calcularArea(lrc_tmp, lcap_ap);

				if(StringUtils.isValidString(ls_matriculaErrada))
					throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
			}

			if(lrc_tmp.isCierreFolio())
				lrc_tmp = salvarCierreFolio(lrc_tmp, false);

			irr_calificacionRemote.enviarADigitador(lrc_tmp);
			setRevisionMatriculas(null);
			clean();

			BeanTurnos   lobt_bean;
			FacesContext lfc_context;
			lfc_context     = FacesUtils.getFacesContext();
			lobt_bean       = (BeanTurnos)lfc_context.getApplication()
					                                     .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
					);

			if(lobt_bean != null)
			{
				lobt_bean.visualizarBandeja();
				lobt_bean.generarData();
				ls_return = NavegacionCommon.TURNOS;
			}
		}
		catch(B2BException lb2b_b2b)
		{
			PrimeFaces lpf_primeFaces;

			lpf_primeFaces = PrimeFaces.current();

			addMessage(lb2b_b2b);
			actualizarComponente(is_messageIdGrowl);
			ls_return = null;
			lpf_primeFaces.executeScript(
			    "mostrarElemento('fRegistroCalif:idCBDigitadorMasivo');mostrarElemento('fRegistroCalif:idCBAprobador');"
			);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String enviarADigitadorDesenglobe()
	{
		String ls_return;

		ls_return = null;

		try
		{
			RegistroCalificacion lrc_arg;
			String               ls_obervaciones;

			lrc_arg             = getMatriculas();
			ls_obervaciones     = getObservaciones();

			lrc_arg.setUserId(getUserId());
			lrc_arg.setIpAddress(getRemoteIpAddress());

			if(StringUtils.isValidString(ls_obervaciones))
				lrc_arg.setObservaciones(ls_obervaciones);

			if(!isPdfGenerado())
				throw new B2BException(ErrorKeys.DEBE_GENERAR_CONSTANCIA);

			Collection<RegistroCalificacion> lcrc_original;
			RegistroCalificacion             lrc_temp;

			lcrc_original     = lrc_arg.getInfoFile();
			lrc_temp          = new RegistroCalificacion();

			if(CollectionUtils.isValidCollection(lcrc_original))
			{
				if(lrc_arg.isEnglobe() || lrc_arg.isVentaParcial() || lrc_arg.isDesenglobe())
				{
					lrc_temp.setIdCirculo(lrc_arg.getIdCirculo() + "-" + lrc_arg.getIdMatricula());

					lcrc_original.add(lrc_temp);
				}
			}

			irr_calificacionRemote.enviarADigitadorDesenglobe(
			    lrc_arg, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			if(lrc_arg.isEnglobe() || lrc_arg.isVentaParcial() || lrc_arg.isDesenglobe())
				lcrc_original.remove(lrc_temp);

			setRevisionMatriculas(null);
			clean();

			{
				BeanTurnos   lobt_bean;
				FacesContext lfc_context;

				lfc_context     = FacesUtils.getFacesContext();
				lobt_bean       = (BeanTurnos)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
						);

				if(lobt_bean != null)
				{
					lobt_bean.visualizarBandeja();
					lobt_bean.generarData();
					ls_return = NavegacionCommon.TURNOS;
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Envia el proceso a etapa de digitador masivo.
	 *
	 * @return Si el proceso fue exitoso retorna el enlace a la pagina principal de turnos de calificación
	 */
	public String enviarADigitadorEnglobesDevolucion()
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(!isFileGenerado())
				throw new B2BException(ErrorKeys.DEBE_GENERAR_CONSTANCIA);

			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				{
					Collection<DireccionPredioAcc> lcdpa_direcciones;

					lcdpa_direcciones = lrc_data.getDireccionesAcc();

					if(CollectionUtils.isValidCollection(lcdpa_direcciones))
					{
						boolean                      lb_seleccion;
						Iterator<DireccionPredioAcc> lidpa_iterator;

						lb_seleccion       = false;
						lidpa_iterator     = lcdpa_direcciones.iterator();

						while(lidpa_iterator.hasNext() && !lb_seleccion)
						{
							DireccionPredioAcc ldpa_iterador;

							ldpa_iterador = lidpa_iterator.next();

							if(ldpa_iterador != null)
							{
								if(ldpa_iterador.isSeleccionado())
									lb_seleccion = true;
							}
						}

						if(!lb_seleccion)
							throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_CARGUE_DIRECCION_PREDIO);
				}

				{
					RegistroCalificacion lrc_anotacionesHeredar;

					lrc_anotacionesHeredar = getMatriculasAHeredar();

					if(lrc_anotacionesHeredar != null)
					{
						Collection<RegistroCalificacion> lcrc_anotacionesHeredar;

						lcrc_anotacionesHeredar = lrc_anotacionesHeredar.getAllMatriculas();

						if(CollectionUtils.isValidCollection(lcrc_anotacionesHeredar))
						{
							boolean                        lb_tmp;
							Iterator<RegistroCalificacion> lirc_iterator;

							lb_tmp            = false;
							lirc_iterator     = lcrc_anotacionesHeredar.iterator();

							while(lirc_iterator.hasNext() && !lb_tmp)
							{
								RegistroCalificacion lorc_rc;

								lorc_rc = lirc_iterator.next();

								if(lorc_rc != null)
								{
									if(lorc_rc.isRevision())
										lb_tmp = true;
								}
							}

							if(!lb_tmp)
								throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);

							lrc_data.setAnotacionesHeredar(lcrc_anotacionesHeredar);
						}
					}
				}

				if(lrc_data.isCierreFolio())
					lrc_data = salvarCierreFolio(lrc_data, false);

				{
					Collection<RegistroCalificacion> lcrc_original;
					RegistroCalificacion             lrc_temp;

					lcrc_original     = lrc_data.getInfoFile();
					lrc_temp          = new RegistroCalificacion();

					if(CollectionUtils.isValidCollection(lcrc_original))
					{
						if(lrc_data.isEnglobe() || lrc_data.isVentaParcial() || lrc_data.isDesenglobe())
						{
							lrc_temp.setIdCirculo(lrc_data.getIdCirculo() + "-" + lrc_data.getIdMatricula());

							lcrc_original.add(lrc_temp);
						}
					}
				}

				{
					Collection<RegistroCalificacion> lcrc_original;
					RegistroCalificacion             lrc_temp;

					lcrc_original     = lrc_data.getInfoFile();
					lrc_temp          = new RegistroCalificacion();

					if(CollectionUtils.isValidCollection(lcrc_original))
					{
						if(lrc_data.isEnglobe() || lrc_data.isVentaParcial() || lrc_data.isDesenglobe())
						{
							lrc_temp.setIdCirculo(lrc_data.getIdCirculo() + "-" + lrc_data.getIdMatricula());

							lcrc_original.add(lrc_temp);
						}
					}
				}

				irr_calificacionRemote.enviarADigitadorEnglobesDevolucion(
				    lrc_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanTurnos   lobt_bean;
					FacesContext lfc_context;

					lfc_context     = FacesUtils.getFacesContext();
					lobt_bean       = (BeanTurnos)lfc_context.getApplication()
							                                     .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
							);

					if(lobt_bean != null)
					{
						lobt_bean.visualizarBandeja();
						lobt_bean.generarData();
						ls_return = NavegacionCommon.TURNOS;
					}

					clean();
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Envia el proceso a etapa de digitador masivo.
	 *
	 * @param ab_accion indica el tipo de proceso que se va a realizar sobre el tramite
	 * @param ab_salvar indica si se va a guardar en base de datos los cambios realizados al tramite, true
	 * para guardar
	 * @return Si el proceso fue exitoso retorna el enlace a la pagina principal de turnos de calificación
	 */
	public String enviarADigitadorEnglobesVenta(boolean ab_accion, boolean ab_salvar)
	{
		String ls_return;

		ls_return = null;

		try
		{
			RegistroCalificacion lrc_arg;
			RegistroCalificacion lrc_dataAnotacionesHeredar;
			String               ls_obervaciones;

			lrc_arg                        = getMatriculas();
			lrc_dataAnotacionesHeredar     = getMatriculasAHeredar();
			ls_obervaciones                = getObservaciones();

			lrc_arg.setMatriculasAHeredar(lrc_dataAnotacionesHeredar);
			lrc_arg.setUserId(getUserId());
			lrc_arg.setIpAddress(getRemoteIpAddress());

			if(StringUtils.isValidString(ls_obervaciones))
				lrc_arg.setObservaciones(ls_obervaciones);

			if(lrc_dataAnotacionesHeredar != null)
			{
				Collection<RegistroCalificacion> lcrc_anotacionesHeredar;

				lcrc_anotacionesHeredar = lrc_dataAnotacionesHeredar.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_anotacionesHeredar))
				{
					boolean                        lb_tmp;
					Iterator<RegistroCalificacion> lirc_iterator;

					lb_tmp            = false;
					lirc_iterator     = lcrc_anotacionesHeredar.iterator();

					while(lirc_iterator.hasNext() && !lb_tmp)
					{
						RegistroCalificacion lorc_rc;

						lorc_rc = lirc_iterator.next();

						if(lorc_rc != null)
						{
							if(lorc_rc.isRevision())
								lb_tmp = true;
						}
					}

					if(!lb_tmp && ab_accion)
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
				}
			}

			if(!ab_salvar && !ab_accion && !isPdfGenerado())
				throw new B2BException(ErrorKeys.DEBE_GENERAR_CONSTANCIA);

			if(ab_salvar && lrc_arg.isCierreFolio())
				lrc_arg = salvarCierreFolio(lrc_arg, false);

			if(ab_accion)
				irr_calificacionRemote.enviarADigitadorEnglobes(lrc_arg);
			else
			{
				Collection<RegistroCalificacion> lcrc_original;
				RegistroCalificacion             lrc_temp;

				lcrc_original     = lrc_arg.getInfoFile();
				lrc_temp          = new RegistroCalificacion();

				if(CollectionUtils.isValidCollection(lcrc_original))
				{
					if(lrc_arg.isEnglobe() || lrc_arg.isVentaParcial() || lrc_arg.isDesenglobe())
					{
						lrc_temp.setIdCirculo(lrc_arg.getIdCirculo() + "-" + lrc_arg.getIdMatricula());

						lcrc_original.add(lrc_temp);
					}
				}

				irr_calificacionRemote.salvarVentaParcial(
				    lrc_arg, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true, ab_salvar
				);

				if(
				    CollectionUtils.isValidCollection(lcrc_original)
					    && (lrc_arg.isEnglobe() || lrc_arg.isVentaParcial() || lrc_arg.isDesenglobe())
				)
					lcrc_original.remove(lrc_temp);
			}

			setRevisionMatriculas(null);
			clean();

			{
				BeanTurnos   lobt_bean;
				FacesContext lfc_context;

				lfc_context     = FacesUtils.getFacesContext();
				lobt_bean       = (BeanTurnos)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
						);

				if(lobt_bean != null)
				{
					lobt_bean.visualizarBandeja();
					lobt_bean.generarData();
					ls_return = NavegacionCommon.TURNOS;
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Método para enviar aprobador secuencia o a homologación actos.
	 *
	 * @param ab_aprobadorSecuencia de ab aprobador secuencia
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarAprobadorSecuenciaOActosHomologacion(boolean ab_aprobadorSecuencia)
	    throws B2BException
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_return;
		String       ls_observaciones;

		lb_focus             = true;
		lfc_context          = FacesContext.getCurrentInstance();
		ls_return            = null;
		ls_observaciones     = getObservaciones();

		lb_focus = validateStyles(
			    "fRegistroCalif:idObservacionesRegistroCalificacion", lfc_context, ls_observaciones, lb_focus
			);

		try
		{
			if(!StringUtils.isValidString(ls_observaciones))
				throw new B2BException(
				    ab_aprobadorSecuencia ? ErrorKeys.ERROR_SIN_OBSERVACIONES : ErrorKeys.DEBE_DIGITAR_JUSTIFICACION
				);
			else if(ls_observaciones.length() < 100)
			{
				lb_focus = validateStyles(
					    "fRegistroCalif:idObservacionesRegistroCalificacion", lfc_context, "", lb_focus
					);
				throw new B2BException(
				    ab_aprobadorSecuencia ? ErrorKeys.ERROR_OBSERVACIONES_TAM_100 : ErrorKeys.ERROR_JUSTIFICACION_TAM_MIN
				);
			}

			TurnoHistoria lth_th;

			lth_th = new TurnoHistoria();

			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_th.setObservaciones(ls_observaciones);

			irr_calificacionRemote.enviarAprobadorSecuenciaOActosHomologacion(
			    lth_th, ab_aprobadorSecuencia, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			clean();

			{
				BeanTurnos lobt_bean;

				lfc_context     = FacesUtils.getFacesContext();
				lobt_bean       = (BeanTurnos)lfc_context.getApplication()
						                                     .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
						);

				if(lobt_bean != null)
				{
					lobt_bean.visualizarBandeja();
					lobt_bean.generarData();
					ls_return = NavegacionCommon.TURNOS;
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update("fRegistroCalif");
		}

		return ls_return;
	}

	/**
	 * Existen anotaciones inactivas para el turno.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean existenAnotacionesInactivasParaElTurno()
	    throws B2BException
	{
		String ls_idTurno;

		boolean lb_existenAnotacionesInactivasTmp;
		boolean lb_return;

		lb_existenAnotacionesInactivasTmp     = false;
		lb_return                             = false;

		ls_idTurno = getTurno();

		if(StringUtils.isValidString(ls_idTurno))
		{
			lb_existenAnotacionesInactivasTmp = irr_calificacionRemote.existenAnotacionesInactivasByTurno(ls_idTurno);

			if(lb_existenAnotacionesInactivasTmp)
				lb_return = true;
		}

		return lb_return;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistral()
	{
		Collection<CirculoRegistral> lccr_circulos;
		lccr_circulos = null;

		try
		{
			CirculoRegistral lcr_circulo;

			lcr_circulo = new CirculoRegistral();

			lcr_circulo.setIdCirculo(getCirculoDireccionPredio());

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

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
	 */
	public Collection<Departamento> findDepartamentosDocumento()
	{
		return findDepartamentosDocumento(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosDocumento(boolean ab_desdeBaldios)
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		if(ab_desdeBaldios)
			super.findDepartamentosDocumento();
		else
		{
			Documento ld_documento;

			ld_documento = getDocumentoDetalle();

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
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
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

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosResidencia()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			OficinaOrigen loo_oficina;
			loo_oficina = getOficinaMedidaCautelar();

			if(loo_oficina != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(loo_oficina.getIdPais());

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

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Departamento> findDepartamentosTelefono()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			PersonaTelefono lpt_telefono;
			lpt_telefono = getTelefonoFijo();

			if(lpt_telefono != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpt_telefono.getIdPais());

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Find indicativo departamento.
	 */
	public void findIndicativoDepartamento()
	{
		try
		{
			PersonaTelefono lpt_telefono;
			lpt_telefono = getTelefonoFijo();

			if(lpt_telefono != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpt_telefono.getIdPais());
				ld_parametros.setIdDepartamento(lpt_telefono.getIdDepartamento());

				ld_parametros = irr_referenceRemote.findDepartamentById(ld_parametros);

				if(ld_parametros != null)
					lpt_telefono.setIndicativo(ld_parametros.getIndicativoTelefonico());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find matriculas.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param ab_mensaje correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findMatriculas(boolean ab_b, boolean ab_mensaje)
	    throws B2BException
	{
		RegistroCalificacion             locrc_rc;
		Collection<RegistroCalificacion> lcrc_rc;
		String                           ls_nir;
		String                           ls_turno;
		String                           ls_mensaje;

		locrc_rc       = new RegistroCalificacion();
		lcrc_rc        = new ArrayList<RegistroCalificacion>();
		ls_nir         = null;
		ls_turno       = null;
		ls_mensaje     = null;

		setValidarArea(false);

		try
		{
			if(getPredio() != null)
			{
				ls_nir       = getPredio().get(IdentificadoresCommon.NIR).toString();
				ls_turno     = getPredio().get(IdentificadoresCommon.ID_TURNO).toString();
			}

			boolean       lb_baldios;
			String        ls_idTurnoHistoria;
			StringBuilder lsb_matriculas;

			lb_baldios             = false;
			ls_idTurnoHistoria     = getIdTurnoHistoria();
			lsb_matriculas         = new StringBuilder();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
				locrc_rc = irr_calificacionRemote.findMatriculas(
					    null, ls_idTurnoHistoria, getTurno(), getUserId(), getRemoteIpAddress(), ab_b,
					    EtapaCommon.ID_ETAPA_CALIFICACION
					);

			if(locrc_rc != null)
			{
				if(StringUtils.isValidString(ls_nir) && StringUtils.isValidString(ls_turno))
				{
					locrc_rc.setNir(ls_nir);
					locrc_rc.setTurno(ls_turno);
				}

				boolean lb_englobes;
				boolean lb_desenglobes;
				boolean lb_ventaParcial;

				lb_englobes         = locrc_rc.isEnglobe();
				lb_desenglobes      = locrc_rc.isDesenglobe();
				lb_ventaParcial     = locrc_rc.isVentaParcial();
				lb_baldios          = locrc_rc.isBaldios();

				setDevolucion(locrc_rc.isDevolucion());
				setPdfCancelacion(locrc_rc.isCancelacion());
				setProcesoReloteo(locrc_rc.isReloteo());
				setIdSolicitud(locrc_rc.getIdSolicitud());
				setDivisionMaterial(locrc_rc.isDivisionMaterial());
				setPanelCierreFolio(locrc_rc.isCierreFolio());
				setProcesoLoteo(locrc_rc.isLoteo());
				setProcesoRemanente(locrc_rc.isRemanente());
				setProcesoBaldios(lb_baldios);

				if(lb_baldios)
				{
					setSalvar(lb_baldios);
					setDireccionPredio(null);
					cambiarFormulario(false);
					setArchivoPdf(null);
					setArchivoPdfDescarga(null);

					AccAreaUI    laaui_area;
					DatosBasicos ldb_datosBasicos;

					laaui_area           = locrc_rc.getDatosArea();
					ldb_datosBasicos     = locrc_rc.getDatosBasicos();

					if(ldb_datosBasicos != null)
					{
						AccPredioRegistro lapr_predioRegistro;

						lapr_predioRegistro = ldb_datosBasicos.getAccPredioRegistro();

						if(
						    (lapr_predioRegistro != null)
							    && StringUtils.isValidString(lapr_predioRegistro.getIdPredioRegistro())
						)
							cargarMatricula(lapr_predioRegistro);
						else
						{
							UbicacionZonaRegistral luzr_ubicacion;

							luzr_ubicacion = ldb_datosBasicos.getUbicacion();

							if(luzr_ubicacion != null)
								setIdPais(luzr_ubicacion.getIdPais());

							setDatosBasicos(ldb_datosBasicos);
						}
					}

					if(laaui_area != null)
						setAreaUI(laaui_area);

					cambiarMunicipio();
					cargarPaisDocumento();
					cargarDepartamentoDocumento();
					cargarOficinaOrigen();
				}

				if(lb_englobes || lb_ventaParcial || lb_desenglobes)
				{
					locrc_rc.setFechaApertura(new Date());

					setProcesoEnglobes(lb_englobes);
					setProcesoVentaParcial(lb_ventaParcial);
					setProcesoDesenglobes(lb_desenglobes);

					setMostrarSiguienteEnglobes(true);
					setMostrarRegresarEnglobes(false);

					if(lb_ventaParcial || lb_desenglobes)
					{
						Collection<RegistroCalificacion> lcrc_matriculas;

						lcrc_matriculas = locrc_rc.getAllMatriculas();

						if(CollectionUtils.isValidCollection(lcrc_matriculas))
						{
							boolean                        lb_accion;
							Iterator<RegistroCalificacion> lirc_iterator;
							StringBuilder                  lsb_sb;
							lsb_sb     = new StringBuilder();

							lb_accion         = true;
							lirc_iterator     = lcrc_matriculas.iterator();

							while(lirc_iterator.hasNext())
							{
								RegistroCalificacion lrc_itedador;

								lrc_itedador = lirc_iterator.next();

								if(lrc_itedador != null)
								{
									if(lb_accion)
									{
										lrc_itedador.setMatriculaSeleccionada(true);
										cambiarUbicacionEnglobe(lrc_itedador);
										lb_accion = false;
									}

									lsb_sb.append(lrc_itedador.getIdCirculo() + ",");
								}
							}
						}

						if(!locrc_rc.isCementerio())
						{
							setMostrarSiguienteVenta(true);
							setMostrarRegresarVenta(false);
						}
					}

					if(lb_englobes)
						locrc_rc = cargarCierreFolio(locrc_rc);
				}

				boolean              lb_return;
				Map<String, Boolean> llhm_data = null;

				lb_return     = false;
				llhm_data     = locrc_rc.getDatosParcial();

				if(CollectionUtils.isValidCollection(llhm_data))
				{
					setDataMatriculasParciales(llhm_data);
					lb_return = llhm_data.containsValue(new Boolean(true));
				}

				setMatriculas(locrc_rc);
				setPdfParcial(lb_return);

				setAnotacionesPredioEliminadasMatriculas(locrc_rc.getAnotacionesPredioEliminadas());

				if(CollectionUtils.isValidCollection(getAnotacionesPredioEliminadasMatriculas()))
				{
					setAnotacionesPredioEliminadas(true);

					BigDecimal li_valorADevolver;

					li_valorADevolver = irr_calificacionRemote.valorADevolverDeLiquidacion(
						    getAnotacionesPredioEliminadasMatriculas(), getIdTurnoHistoria(), getUserId(),
						    getLocalIpAddress(), getRemoteIpAddress()
						);

					setValorADevolver(conversionCientifica(NumericUtils.getDoubleWrapper(li_valorADevolver, 0D)));
				}
			}

			lcrc_rc = getMatriculas().getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_rc))
			{
				int li_contador;
				setSalvar(true);

				li_contador = 0;

				for(RegistroCalificacion lorc_rc : lcrc_rc)
				{
					if(
					    lorc_rc.getTramite().equalsIgnoreCase(EstadoCommon.ESTADO_ANOTACION_INCOMPLETO)
						    && !isProcesoBaldios()
					)
					{
						setSalvar(false);

						if(lorc_rc.isValidarArea())
						{
							if(!lorc_rc.isAreaValidada())
							{
								setValidarArea(true);
								ls_mensaje = "Debe revisar el área para la matricula: " + lorc_rc.getIdCirculo();
							}
						}

						li_contador++;
					}

					lsb_matriculas.append(lorc_rc.getIdCirculo());
				}

				boolean lb_mostrar;
				lb_mostrar = false;

				if(isSalvar())
				{
					matriculasAHeredar();

					if(!lb_baldios)
						cargarDireccionPredio(ab_mensaje);

					actualizarDireccionPredioData();
					cargarLinderos();

					cargarDatosBasicosEnglobes();
					cargarVentaParcial();

					if(isDevolucion())
						cargarComplementacion();

					String               ls_idTurno;
					Collection<TipoActo> lcta_actos;

					ls_idTurno     = getTurno();
					lcta_actos     = irr_calificacionRemote.findTipoActos(ls_idTurno);

					setHabilitarMedidaCautelar(
					    irr_registroRemote.validarActosDeMedidaCautelar(
					        ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);

					if(CollectionUtils.isValidCollection(lcta_actos))
					{
						for(TipoActo la_acto : lcta_actos)
						{
							if(la_acto != null)
							{
								String ls_generaSegregacion;
								ls_generaSegregacion = la_acto.getGeneraSegregacion();

								if(
								    StringUtils.isValidString(ls_generaSegregacion)
									    && ls_generaSegregacion.equalsIgnoreCase(EstadoCommon.S)
								)
								{
									lb_mostrar = true;

									if(
									    StringUtils.getStringNotNull(la_acto.getInscripcionAdicional())
										               .equalsIgnoreCase(EstadoCommon.S)
										    && StringUtils.getStringNotNull(la_acto.getAperturaMatricula())
										                      .equalsIgnoreCase(EstadoCommon.S)
									)
										setValidarPropiedadHorizontal(true);
									else
										setValidarPropiedadHorizontal(false);
								}
								else
									setValidarPropiedadHorizontal(false);

								if(
								    isHabilitarMedidaCautelar()
									    && (!StringUtils.isValidString(getReferencia())
									    && !StringUtils.isValidString(getNumeroProceso()))
								)
								{
									setReferencia(la_acto.getReferencia());
									setNumeroProceso(la_acto.getNumeroProceso());

									cargarOficinaOrigenMedidaCautelar();
								}
							}
						}
					}

					setSinSegregacion(!lb_mostrar);
					setHabilitarDigitador(lb_mostrar);

					if(lb_mostrar && (li_contador == 0))
					{
						if(isProcesoReloteo())
						{
							addMessage(MessagesKeys.PROCESO_COMPLETADO_RELOTEO);
							PrimeFaces.current().ajax().update(is_messageIdGrowl);
						}
					}

					setHabilitarTabs(lb_mostrar);
				}

				if(isIndVinculado())
				{
					ExternalContext lec_externalContext;
					Object[]        aoa_messageArgs;

					lec_externalContext     = FacesContext.getCurrentInstance().getExternalContext();
					aoa_messageArgs         = new String[2];
					aoa_messageArgs[0]      = locrc_rc.getMensaje();
					aoa_messageArgs[1]      = lsb_matriculas.toString();

					addMessage(MessagesKeys.MATRICULAS_VINCULACION, aoa_messageArgs);

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}

				if(lb_mostrar && isDevolucion())
					guardarInfoRegistro(true, null);
			}
		}
		catch(B2BException lb2be_e)
		{
			locrc_rc = null;
			throw new B2BException(lb2be_e.getMessage());
		}
		finally
		{
			if(StringUtils.isValidString(ls_mensaje))
				addMessage("I", ls_mensaje);

			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioPredio()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DireccionDelPredio lddp_direccion;

			lddp_direccion = getDireccionPredio();

			if(lddp_direccion != null)
			{
				DatosAntSistema ldas_datosAntiguoSistema;

				ldas_datosAntiguoSistema = lddp_direccion.getDatosAntiguoSistema();

				if(ldas_datosAntiguoSistema != null)
				{
					Municipio lm_parametros;

					lm_parametros = new Municipio();

					lm_parametros.setIdPais(ldas_datosAntiguoSistema.getIdPais());
					lm_parametros.setIdDepartamento(ldas_datosAntiguoSistema.getIdDepartamento());

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

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioResidencia()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			OficinaOrigen loo_oficina;

			loo_oficina = getOficinaMedidaCautelar();

			if(loo_oficina != null)
			{
				Municipio lm_parametros;

				lm_parametros = new Municipio();

				lm_parametros.setIdPais(loo_oficina.getIdPais());
				lm_parametros.setIdDepartamento(loo_oficina.getIdDepartamento());

				lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipiosDocumento(boolean ab_desdeBaldios)
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		if(ab_desdeBaldios)
			lcm_municipios = super.findMunicipiosDocumento();
		else
		{
			Documento ld_documento;

			ld_documento = getDocumentoDetalle();

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
		}

		return lcm_municipios;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
	 */
	public Collection<Municipio> findMunicipiosDocumento()
	{
		return findMunicipiosDocumento(false);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
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
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Find sub proceso.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findSubProceso()
	    throws B2BException
	{
		String ls_idTurnoHistoria;

		ls_idTurnoHistoria = getIdTurnoHistoria();

		if(StringUtils.isValidString(ls_idTurnoHistoria))
		{
			Solicitud ls_solicitud;

			ls_solicitud = irr_calificacionRemote.findSolicitudByIdTurnoHistoria(ls_idTurnoHistoria);

			if(ls_solicitud != null)
			{
				String ls_idSubProceso;

				ls_idSubProceso = ls_solicitud.getIdSubproceso();

				if(StringUtils.isValidString(ls_idSubProceso))
					setSubProceso(ls_idSubProceso);
			}
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de collection
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
							BigInteger lbi_temp;

							lbi_temp = lc_constante.getEntero().add(NumericUtils.getBigInteger(1));

							setDocumentoInterviniente(StringUtils.getString(lbi_temp));
							setRenderedHabilitaSecuencia(true);
							getPersona().setIdDocumentoTipo(ls_tipoDocInter);
							getPersona().setNumeroDocumento(StringUtils.getString(lbi_temp));
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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoSecuenciaCalificacion()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		try
		{
			if(isHabilitaSecuencia())
			{
				String ls_tipoDocInter;
				ls_tipoDocInter = getPersonaConsulta().getTipoDocIdentidad();

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
							BigInteger lbi_temp;
							lbi_temp = lc_constante.getEntero().add(NumericUtils.getBigInteger(1));

							getPersonaConsulta().setNumeroDocumento(StringUtils.getString(lbi_temp));
							setRenderedHabilitaSecuencia(true);
							getPersona().setIdDocumentoTipo(ls_tipoDocInter);
							getPersona().setNumeroDocumento(StringUtils.getString(lbi_temp));
						}
					}
					else
					{
						lcidt_datos = irr_referenceRemote.findTipoDocumento();
						getPersonaConsulta().setNumeroDocumento(null);
						setRenderedHabilitaSecuencia(false);
					}
				}
				else
				{
					lcidt_datos = irr_referenceRemote.findTipoDocumento();
					getPersonaConsulta().setNumeroDocumento(null);
					setRenderedHabilitaSecuencia(false);
				}
			}
			else
			{
				lcidt_datos = irr_referenceRemote.findTipoDocumentoActivo();
				getPersonaConsulta().setNumeroDocumento(null);
				setRenderedHabilitaSecuencia(false);
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
	 *  {@inheritdoc}.
	 */
	public void findTipoEntidadDocumento()
	{
		Documento ld_documento;

		ld_documento = getDocumentoDetalle();

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

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActo> findTiposActos()
	{
		Collection<TipoActo> lcta_cta;
		lcta_cta = new ArrayList<TipoActo>();

		try
		{
			lcta_cta = irr_referenceRemote.findAllTiposActoActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true, false, false
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_cta;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afe_event correspondiente al valor del tipo de objeto FlowEvent
	 * @return devuelve el valor de String
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_new;
		String ls_old;

		ls_new     = afe_event.getNewStep();
		ls_old     = afe_event.getOldStep();

		try
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				if(StringUtils.isValidString(ls_old) && StringUtils.isValidString(ls_new))
				{
					boolean      lb_devolucion;
					boolean      lb_ventaParcial;
					boolean      lb_desenglobe;
					final String ls_idDatosBasicos;
					final String ls_idAreaPredio;
					final String ls_idAnotaciones;
					final String ls_idComplementacion;
					final String ls_idComplementacionVenta;
					final String ls_idDireccion;
					final String ls_idDireccionVenta;

					lb_devolucion                 = lrc_data.isDevolucion();
					lb_desenglobe                 = lrc_data.isDesenglobe();
					lb_ventaParcial               = lrc_data.isVentaParcial();
					ls_idDatosBasicos             = "datosBasicos_idTabs";
					ls_idAreaPredio               = "areaPredio_id";
					ls_idAnotaciones              = "anotaciones_id";
					ls_idComplementacion          = "complementacion_id";
					ls_idComplementacionVenta     = "complementacionVenta_id";
					ls_idDireccion                = "direccion_id";
					ls_idDireccionVenta           = "direccionVenta_id";

					if(ls_new.equalsIgnoreCase(ls_idDatosBasicos) && ls_old.equalsIgnoreCase(ls_idAreaPredio))
					{
						setMostrarRegresarEnglobes(false);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idAreaPredio))
					{
						if(ls_old.equalsIgnoreCase(ls_idDatosBasicos))
						{
							salvarDatosBasicosEnglobes();
							cargarDatosAreaEnglobes();
							limpiarAreasEnglobes();
						}

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(true);
					}
					else if(ls_new.equalsIgnoreCase(ls_idAnotaciones))
					{
						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();

						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();
						else if(ls_old.equalsIgnoreCase(ls_idDireccionVenta))
							salvarDireccionVenta();

						if(lb_devolucion && !ls_old.equalsIgnoreCase(ls_idComplementacion))
							cargarAnotacionesEnglobes();

						cargarAnotacionProceso();

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(lb_devolucion);
					}
					else if(
					    ls_new.equalsIgnoreCase(ls_idComplementacion)
						    || ls_new.equalsIgnoreCase(ls_idComplementacionVenta)
					)
					{
						if(ls_old.equalsIgnoreCase(ls_idAreaPredio))
							salvarAreaPredioEnglobes();

						cargarLinderosComplementacionEnglobes(true);

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(lb_devolucion || lb_ventaParcial || lb_desenglobe);
					}
					else if(ls_new.equalsIgnoreCase(ls_idDireccion) || ls_new.equalsIgnoreCase(ls_idDireccionVenta))
					{
						if(
						    ls_old.equalsIgnoreCase(ls_idComplementacion)
							    || ls_old.equalsIgnoreCase(ls_idComplementacionVenta)
						)
							if(isComplementacionSinConstruir())
								throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

						salvarLinderosComplementacionEnglobes();

						if(ls_old.equalsIgnoreCase(ls_idComplementacionVenta))
							cargarDireccionesVentaParcial();
						else
							cargarDireccionesEnglobes();

						setMostrarRegresarEnglobes(true);
						setMostrarSiguienteEnglobes(lb_ventaParcial || lb_desenglobe);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_new = ls_old;
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);

		return ls_new;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afe_event correspondiente al valor del tipo de objeto FlowEvent
	 * @return devuelve el valor de String
	 */
	public String flowListenerLoteo(FlowEvent afe_event)
	{
		String ls_new;
		String ls_old;

		ls_new     = afe_event.getNewStep();
		ls_old     = afe_event.getOldStep();

		try
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				if(StringUtils.isValidString(ls_old) && StringUtils.isValidString(ls_new))
				{
					String ls_idAnotaciones;
					String ls_idComplementacion;
					String ls_idComplementacionVenta;
					String ls_idDireccion;
					String ls_medidasCautelares;
					String ls_idAnotacionesReloteo;
					String ls_idComplementacionReloteo;
					String ls_idDireccionReloteo;

					ls_idAnotaciones                = IdentificadoresCommon.ID_T_ANOTACIONES;
					ls_idComplementacion            = IdentificadoresCommon.ID_T_COMPLEMENTACION;
					ls_idComplementacionVenta       = "complementacionVenta_id";
					ls_idDireccion                  = IdentificadoresCommon.ID_T_DIRECCION_PREDIO_RC;
					ls_medidasCautelares            = "idTMedidasCautelares";
					ls_idAnotacionesReloteo         = "idTAnotacionesAHeredar";
					ls_idComplementacionReloteo     = "idTComplementacionReloteo";
					ls_idDireccionReloteo           = "idTDireccionPredioReloteo";

					/* LOGICA DE TABS*/
					if(ls_old.equalsIgnoreCase(ls_idAnotaciones) || ls_old.equalsIgnoreCase(ls_idAnotacionesReloteo))
					{
						if(
						    ls_new.equalsIgnoreCase(ls_idComplementacion)
							    || ls_new.equalsIgnoreCase(ls_idComplementacionReloteo)
						)
						{
							setBtnRegresarLoteo(true);

							/*SALVAR ANOTACIONES A HEREDAR*/
							guardarInfoRegistro(false, ls_idAnotaciones);
							limpiarComplementacionData(false);
						}
					}
					else if(
					    ls_old.equalsIgnoreCase(ls_idComplementacion)
						    || ls_old.equalsIgnoreCase(ls_idComplementacionReloteo)
					)
					{
						if(ls_new.equalsIgnoreCase(ls_idDireccion) || ls_new.equalsIgnoreCase(ls_idDireccionReloteo))    /*SALVAR LINDEROS */
						{
							if(isComplementacionSinConstruir())
								throw new B2BException(ErrorKeys.DEBE_CONSTRUIR_COMPLEMENTACION);

							guardarInfoRegistro(false, ls_idComplementacion);
							setBtnSiguienteLoteo(false);

							cargarDireccionFlowLoteo();
						}
						else if(
						    ls_new.equalsIgnoreCase(ls_idAnotaciones)
							    || ls_old.equalsIgnoreCase(ls_idAnotacionesReloteo)
						)
						{
							ComplementacionCalificacion lcc_cc;

							lcc_cc = getComplementacionCalificacion();

							if(lcc_cc != null)
								lcc_cc.setTipoComplementacion(null);

							setBtnRegresarLoteo(false);
							setMatriculasParaGenerarComplementacion(null);
						}
					}
					else if(ls_old.equalsIgnoreCase(ls_idComplementacionVenta))
					{
					}
					else if(ls_old.equalsIgnoreCase(ls_idDireccion) || ls_new.equalsIgnoreCase(ls_idDireccionReloteo))
					{
						if(
						    ls_new.equalsIgnoreCase(ls_idComplementacion)
							    || ls_new.equalsIgnoreCase(ls_idComplementacionReloteo)
						)
							setBtnSiguienteLoteo(true);
					}
					else if(ls_old.equalsIgnoreCase(ls_medidasCautelares))
					{
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_new = ls_old;
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);

		return ls_new;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afe_event correspondiente al valor del tipo de objeto FlowEvent
	 * @return devuelve el valor de String
	 */
	public String flowListenerVenta(FlowEvent afe_event)
	{
		String ls_new;
		String ls_old;

		ls_new     = afe_event.getNewStep();
		ls_old     = afe_event.getOldStep();

		try
		{
			if(StringUtils.isValidString(ls_old) && StringUtils.isValidString(ls_new))
			{
				final String ls_idAreaTab;
				final String ls_idLinderosTab;

				ls_idAreaTab         = "actualizarArea_id";
				ls_idLinderosTab     = "actualizarLinderos_id";

				if(ls_new.equalsIgnoreCase(ls_idAreaTab) && ls_old.equalsIgnoreCase(ls_idLinderosTab))
				{
					setMostrarRegresarVenta(false);
					setMostrarSiguienteVenta(true);
				}
				else if(ls_new.equalsIgnoreCase(ls_idLinderosTab) && ls_old.equalsIgnoreCase(ls_idAreaTab))
				{
					salvarAreaPredioEnglobeVenta();

					setMostrarRegresarVenta(true);
					setMostrarSiguienteVenta(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_new = ls_old;
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);

		return ls_new;
	}

	/**
	 * Genera.
	 */
	public void genera()
	{
		byte[]              arch;
		ExportFiles         lef_files;
		Map<String, String> lhm_columns;

		arch            = null;
		lef_files       = new ExportFiles();
		lhm_columns     = new LinkedHashMap<String, String>();

		lhm_columns.put("IdCirculo", "Circulo");
		lhm_columns.put("IdMatricula", "Matricula");
		lhm_columns.put("AreaPredio", "Área predio");
		lhm_columns.put("AreaConstruida", "Área construida");
		lhm_columns.put("AreaPrivadaConstruida", "Área privada construida");
		lhm_columns.put("Coeficiente", "Coeficiente");
		lhm_columns.put("ComplementoDireccion", "Complemento Direccion");
		lhm_columns.put("Description", "Tipo uso del suelo");

		try
		{
			arch = lef_files.getXSLFromCollection(getMatriculasInformacion(), lhm_columns);
		}
		catch(B2BException le_e)
		{
			clh_LOGGER.error("genera", le_e);
		}
		catch(IOException le_e)
		{
			clh_LOGGER.error("genera", le_e);
		}

		InputStream stream = new ByteArrayInputStream(arch);
		isc_file = new DefaultStreamedContent(stream, TipoContenidoCommon.XLS_XLSX_XXLS, "Datos.xlsx");
	}

	/**
	 * Generar anotaciones predio acc.
	 *
	 * @return el valor de collection
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> generarAnotacionesPredioAcc()
	{
		Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones;
		lc_anotaciones = null;

		RegistroCalificacion lrc_registroCalificacion;
		lrc_registroCalificacion = getMatriculas();

		if(lrc_registroCalificacion != null)
		{
			Collection<RegistroCalificacion> lc_registroCalificacion;
			lc_registroCalificacion = lrc_registroCalificacion.getAllMatriculas();

			if(CollectionUtils.isValidCollection(lc_registroCalificacion))
			{
				Long ll_idMatriculaBaldio;

				lc_anotaciones           = new ArrayList<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio>();
				ll_idMatriculaBaldio     = null;

				if(lrc_registroCalificacion.isBaldios())
				{
					AccPredioRegistro lapr_predio;

					lapr_predio = getAccPredioRegistro();

					if(lapr_predio != null)
						ll_idMatriculaBaldio = lapr_predio.getIdMatricula();
				}

				for(RegistroCalificacion iterador : lc_registroCalificacion)
				{
					String ls_matriculaCompleta;
					String ls_idCirculo;
					Long   ll_idMatricula;

					ls_matriculaCompleta     = iterador.getIdCirculo();
					ls_idCirculo             = null;
					ll_idMatricula           = null;

					if(StringUtils.isValidString(ls_matriculaCompleta))
					{
						String[] lsa_matricula;
						lsa_matricula = ls_matriculaCompleta.split("-");

						if(CollectionUtils.isValidCollection(lsa_matricula) && (lsa_matricula.length > 0))
						{
							ls_idCirculo       = lsa_matricula[0];
							ll_idMatricula     = NumericUtils.getLongWrapper(lsa_matricula[1]);

							if(ll_idMatricula.compareTo(NumericUtils.getLongWrapper(0L)) == 0)
								ll_idMatricula = ll_idMatriculaBaldio;
						}

						com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;

						lap_anotacionPredio = new com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio();
						lap_anotacionPredio.setIdMatricula(ll_idMatricula);
						lap_anotacionPredio.setIdCirculo(ls_idCirculo);
						lap_anotacionPredio.setIdTurnoHistoria(NumericUtils.getLong(getIdTurnoHistoria()));
						lap_anotacionPredio.setIdTurno(getTurno());

						lc_anotaciones.add(lap_anotacionPredio);
					}
				}
			}
		}

		return lc_anotaciones;
	}

	/**
	 * Generar nota exceso de pago y pasar de etapa.
	 */
	public void generarNotaExcesoPago()
	{
		byte[] lba_notaInformativa;
		lba_notaInformativa = null;

		try
		{
			if(!isPersonaExcesoGuardado())
				throw new B2BException(ErrorKeys.ERROR_DEBE_DAR_CLICK_EN_BOTON_SALVAR);

			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_turnoHistoria.setNoGuardarNotaExcesoDePago(true);

			{
				boolean      lb_focus;
				FacesContext lfc_context;

				lb_focus        = true;
				lfc_context     = FacesContext.getCurrentInstance();

				String ls_observaciones;

				ls_observaciones = getObservacionesNotaInfo();
				lb_focus        = validateStyles(
					    "fRegistroCalif:idITAobservacionesNotaInfo", lfc_context, ls_observaciones, lb_focus
					);

				if(StringUtils.isValidString(ls_observaciones))
				{
					if(ls_observaciones.length() < 100)
						throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_100);

					lth_turnoHistoria.setObservaciones(ls_observaciones);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
			}

			lba_notaInformativa = ilr_liquidacionRemote.generarPDFNotaInformativaPorPagoEnExceso(
				    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_notaInformativa != null)
			{
				InputStream lis_stream;
				String      ls_nombreArchivo;

				lis_stream     = new ByteArrayInputStream(lba_notaInformativa);

				ls_nombreArchivo = ConstanteCommon.NOMBRE_ARCHIVO_GENERADO;

				setImagenNotaExcesoPago(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.PDF, ls_nombreArchivo + ExtensionCommon.PDF_MAYUS_PUNTO
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

			setGeneroNotaInformativaPagoExceso(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Crea la constancia de inscripción para etapas de calificación.
	 *
	 * @param ab_guardarArchivo Indica si se debe guardar en base de datos el documento generado, true para guardar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFile(boolean ab_guardarArchivo)
	    throws B2BException
	{
		byte[] lab_file;

		try
		{
			RegistroCalificacion             lorc_rc;
			RegistroCalificacion             lorc_tmp;
			Collection<AreaPredio>           lcap_ap;
			boolean                          lb_b;
			Collection<RegistroCalificacion> lcrc_rc;
			Collection<RegistroCalificacion> lcrc_tmp;
			Map<String, Boolean>             lhmso_revisionMatriculas;
			boolean                          lb_matriculasSegregadas;

			lcap_ap                      = getMatriculasInformacion();
			lorc_rc                      = getMatriculas();
			lhmso_revisionMatriculas     = getRevisionMatriculas();
			lorc_tmp                     = new RegistroCalificacion();
			lcrc_tmp                     = new ArrayList<RegistroCalificacion>();

			lb_b = CollectionUtils.isValidCollection(lcap_ap);

			if(lorc_rc != null)
			{
				lcrc_rc = lorc_rc.getAllMatriculas();
				lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lorc_rc.setIdMotivo(getMotivo());
				lorc_rc.setUserId(getUserId());
				lorc_rc.setSalvar(ab_guardarArchivo);

				if(lb_b)
				{
					if(CollectionUtils.isValidCollection(lcrc_rc))
						lcrc_tmp.addAll(lcrc_rc);

					if(!lorc_rc.isMatriculasAdd())
					{
						for(AreaPredio loap_ap : lcap_ap)
						{
							String ls_matricula;

							ls_matricula = loap_ap.getIdCirculo() + "-" + loap_ap.getIdMatricula();

							lorc_tmp.setIdCirculo(ls_matricula);
							lorc_tmp.setIndDummy(true);
							lcrc_tmp.add(lorc_tmp);
							lorc_tmp = new RegistroCalificacion();
						}

						if(CollectionUtils.isValidCollection(lcrc_tmp))
						{
							lorc_rc.setInfoFile(lcrc_tmp);
							lorc_rc.setMatriculasAdd(true);
						}
					}
				}
				else if(CollectionUtils.isValidCollection(lcrc_rc))
					lorc_rc.setInfoFile(lcrc_rc);

				lorc_rc.setActoSegregacion(lb_b);
				lorc_rc.setDevolucion(isDevolucion());

				if(lb_b)
					lorc_rc.setTotalMatriculasRevision(lcap_ap.size());

				lorc_rc.setTotalRevision(lhmso_revisionMatriculas.size());
				lorc_rc.setCancelacion(isPdfCancelacion());

				Map<String, Boolean> llhm_dataParcial;

				llhm_dataParcial = getDataMatriculasParciales();
				lorc_rc.setDatosParcial(llhm_dataParcial);

				if(getObservaciones() != null)
					lorc_rc.setObservaciones(getObservaciones());

				boolean                          lb_englobe;
				boolean                          lb_ventaParcial;
				boolean                          lb_desengoble;
				boolean                          lb_baldios;
				Collection<RegistroCalificacion> lcrc_original;
				RegistroCalificacion             lrc_temp;

				lb_englobe          = lorc_rc.isEnglobe();
				lb_ventaParcial     = lorc_rc.isVentaParcial();
				lb_desengoble       = lorc_rc.isDesenglobe();
				lb_baldios          = lorc_rc.isBaldios();
				lcrc_original       = lorc_rc.getInfoFile();
				lrc_temp            = new RegistroCalificacion();

				if(CollectionUtils.isValidCollection(lcrc_original))
				{
					if(lb_englobe || lb_ventaParcial || lb_desengoble || lb_baldios)
					{
						Long   ll_idMatricula;
						String ls_idCirculo;

						ll_idMatricula     = lorc_rc.getIdMatricula();
						ls_idCirculo       = lorc_rc.getIdCirculo();

						if(!NumericUtils.isValidLong(ll_idMatricula))
						{
							AccPredioRegistro lapr_predio;

							lapr_predio = getAccPredioRegistro();

							if(lapr_predio != null)
								ll_idMatricula = lapr_predio.getIdMatricula();
						}

						lrc_temp.setIdCirculo(ls_idCirculo + "-" + ll_idMatricula);

						lcrc_original.add(lrc_temp);

						if(lb_baldios && StringUtils.isValidString(ls_idCirculo))
						{
							boolean                        lb_eliminado;
							Iterator<RegistroCalificacion> lirc_iterator;

							lb_eliminado      = false;
							lirc_iterator     = lcrc_original.iterator();

							while(lirc_iterator.hasNext() && !lb_eliminado)
							{
								RegistroCalificacion lrc_data;

								lrc_data = lirc_iterator.next();

								if(lrc_data != null)
								{
									String ls_matricula;

									ls_matricula = lrc_data.getIdCirculo();

									if(
									    StringUtils.isValidString(ls_idCirculo)
										    && ls_matricula.equalsIgnoreCase(ls_idCirculo + "-0")
									)
									{
										lb_eliminado = true;
										lcrc_original.remove(lrc_data);
									}
								}
							}
						}
					}
				}

				lb_matriculasSegregadas = CollectionUtils.isValidCollection(lcap_ap);
				lorc_tmp.setIndSegregacion(lb_matriculasSegregadas);

				if(isHabilitarMedidaCautelar())
				{
					if(isEditarDatosOficinaMedidaCautelar())
						throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_PROCESO_MEDIDA_CAUTELAR);

					guardarCambiosOficinaOrigen();

					lorc_rc.setReferencia(getReferencia());
					lorc_rc.setNumeroProceso(getNumeroProceso());
					lorc_rc.setOficinaOrigenMedidaCautelar(getOficinaMedidaCautelar());
					lorc_rc.setMedidaCautelar(true);
				}

				if(isPanelCierreFolio() && ab_guardarArchivo)
					lorc_rc = salvarCierreFolio(lorc_rc, false);

				lorc_rc.setMatriculasSegregadas(lcap_ap);
				lorc_rc.setEnvioCalificador(true);
				lorc_rc.setIndVinculado(isIndVinculado());
				lorc_rc.setGenerarPDFCorrespondenciaRegistro(
				    isEsNotificacionResidencia() || isEsNotificacionCorrespondencia()
				);
				lorc_rc.setGenerarArchivoNotaExcesoPago(isAnotacionesPredioEliminadas());

				lab_file = irr_calificacionRemote.genereteFileRegistro(
					    lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), !isVieneDeAprobacion()
					);

				if(lb_englobe || lb_ventaParcial || lb_desengoble || lb_baldios)
					lcrc_original.remove(lrc_temp);

				if(lab_file != null)
				{
					InputStream lis_stream;
					String      ls_nombreArchivo;

					lis_stream = new ByteArrayInputStream(lab_file);

					if(isIndVinculado())
						setImagen(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.ZIP, ConstanteCommon.NOMBRE_ZIP_CALIFICACION
						    )
						);

					else
					{
						ls_nombreArchivo = ConstanteCommon.CONSTANCIA_INSCRIPCION;

						if(CollectionUtils.isValidCollection(llhm_dataParcial))
						{
							if(llhm_dataParcial.containsValue(new Boolean(true)))
								ls_nombreArchivo = ConstanteCommon.CONSTANCIA_INSCRIPCION_PARCIAL;
						}

						setImagen(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.PDF, ls_nombreArchivo + ExtensionCommon.PDF_MAYUS_PUNTO
						    )
						);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

				ib_pdfGenerado = true;
			}
		}
		catch(B2BException lb2be_e)
		{
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Generate file cancelacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFileCancelacion(String as_plantilla)
	    throws B2BException
	{
		byte[]  lab_file;
		boolean lb_medidaCautelar;

		lb_medidaCautelar = false;

		try
		{
			RegistroCalificacion lorc_rc;

			lorc_rc = new RegistroCalificacion();

			lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lorc_rc.setIdMotivo(getMotivo());
			lorc_rc.setUserId(getUserId());
			lorc_rc.setTurno(getTurno());
			lorc_rc.setCancelacion(isPdfCancelacion());
			lorc_rc.setNotaDevolutiva(false);

			if(as_plantilla.equalsIgnoreCase(IdentificadoresCommon.REGISTRO_MEDIDA_CAUTELAR))
				lb_medidaCautelar = isHabilitarMedidaCautelar();
			else if(as_plantilla.equalsIgnoreCase(IdentificadoresCommon.COMUNICADO_CANCELACIONES))
				lb_medidaCautelar = false;

			if(lb_medidaCautelar)
			{
				if(isEditarDatosOficinaMedidaCautelar())
					throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_PROCESO_MEDIDA_CAUTELAR);

				boolean      lb_focus;
				FacesContext lfc_context;

				lb_focus        = true;
				lfc_context     = FacesContext.getCurrentInstance();

				String ls_referencia;
				ls_referencia   = getReferencia();

				String ls_numeroProceso;
				ls_numeroProceso = getNumeroProceso();

				lb_focus = validateStyles("fRegistroCalif:idItNumeroProceso", lfc_context, ls_numeroProceso, lb_focus);

				if(!StringUtils.isValidString(ls_numeroProceso))
					throw new B2BException(ErrorKeys.ERROR_SIN_NUMERO_PROCESO);

				guardarCambiosOficinaOrigen();

				lorc_rc.setOficinaOrigenMedidaCautelar(getOficinaMedidaCautelar());
				lorc_rc.setReferencia(ls_referencia);
				lorc_rc.setNumeroProceso(ls_numeroProceso);
				lorc_rc.setMedidaCautelar(true);
			}

			lab_file = irr_calificacionRemote.generateFileCancelacion(
				    lorc_rc, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lab_file != null)
			{
				InputStream lis_stream;
				lis_stream = new ByteArrayInputStream(lab_file);

				String ls_nombreArchivo;
				ls_nombreArchivo = (lb_medidaCautelar ? ConstanteCommon.NOMBRE_ARCHIVO_COM_MEDIDA_CAUTELAR
					                                  : ConstanteCommon.NOMBRE_ARCHIVO_COM_CANCELACIONES)
					+ ExtensionCommon.PDF_PUNTO;

				setFileCancelacion(new DefaultStreamedContent(lis_stream, "application/pdf", ls_nombreArchivo));

				if(isHabilitarMedidaCautelar())
				{
					setValidacionComunicadoMedidaCautelar(true);
					PrimeFaces.current().ajax().update("fRegistroCalif:idCBFormularioIns");
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Generate file comunicado.
	 *
	 * @param ab_guardarArchivo correspondiente al valor de ab guardar archivo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generateFileComunicado(boolean ab_guardarArchivo)
	    throws B2BException
	{
		{
			byte[] lab_file;

			try
			{
				RegistroCalificacion             lorc_rc;
				RegistroCalificacion             lorc_tmp;
				Collection<AreaPredio>           lcap_ap;
				boolean                          lb_b;
				Collection<RegistroCalificacion> lcrc_rc;
				Collection<RegistroCalificacion> lcrc_tmp;
				Map<String, Boolean>             lhmso_revisionMatriculas;
				boolean                          lb_matriculasSegregadas;

				lcap_ap                      = getMatriculasInformacion();
				lorc_rc                      = getMatriculas();
				lhmso_revisionMatriculas     = getRevisionMatriculas();
				lorc_tmp                     = new RegistroCalificacion();
				lcrc_tmp                     = new ArrayList<RegistroCalificacion>();

				lb_b = CollectionUtils.isValidCollection(lcap_ap);

				if(lorc_rc != null)
				{
					lcrc_rc = lorc_rc.getAllMatriculas();
					lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					lorc_rc.setIdMotivo(getMotivo());
					lorc_rc.setUserId(getUserId());
					lorc_rc.setSalvar(ab_guardarArchivo);

					if(lb_b)
					{
						if(CollectionUtils.isValidCollection(lcrc_rc))
							lcrc_tmp.addAll(lcrc_rc);

						if(!lorc_rc.isMatriculasAdd())
						{
							for(AreaPredio loap_ap : lcap_ap)
							{
								String ls_matricula;

								ls_matricula = loap_ap.getIdCirculo() + "-" + loap_ap.getIdMatricula();

								lorc_tmp.setIdCirculo(ls_matricula);
								lorc_tmp.setIndDummy(true);
								lcrc_tmp.add(lorc_tmp);
								lorc_tmp = new RegistroCalificacion();
							}

							if(CollectionUtils.isValidCollection(lcrc_tmp))
							{
								lorc_rc.setInfoFile(lcrc_tmp);
								lorc_rc.setMatriculasAdd(true);
							}
						}
					}
					else if(CollectionUtils.isValidCollection(lcrc_rc))
						lorc_rc.setInfoFile(lcrc_rc);

					lorc_rc.setActoSegregacion(lb_b);
					lorc_rc.setDevolucion(isDevolucion());

					if(lb_b)
						lorc_rc.setTotalMatriculasRevision(lcap_ap.size());

					lorc_rc.setTotalRevision(lhmso_revisionMatriculas.size());
					lorc_rc.setCancelacion(isPdfCancelacion());

					Map<String, Boolean> llhm_dataParcial;

					llhm_dataParcial = getDataMatriculasParciales();
					lorc_rc.setDatosParcial(llhm_dataParcial);

					if(getObservaciones() != null)
						lorc_rc.setObservaciones(getObservaciones());

					boolean                          lb_englobe;
					boolean                          lb_ventaParcial;
					boolean                          lb_desengoble;
					boolean                          lb_baldios;
					Collection<RegistroCalificacion> lcrc_original;
					RegistroCalificacion             lrc_temp;

					lb_englobe          = lorc_rc.isEnglobe();
					lb_ventaParcial     = lorc_rc.isVentaParcial();
					lb_desengoble       = lorc_rc.isDesenglobe();
					lb_baldios          = lorc_rc.isBaldios();
					lcrc_original       = lorc_rc.getInfoFile();
					lrc_temp            = new RegistroCalificacion();

					if(CollectionUtils.isValidCollection(lcrc_original))
					{
						if(lb_englobe || lb_ventaParcial || lb_desengoble || lb_baldios)
						{
							Long   ll_idMatricula;
							String ls_idCirculo;

							ll_idMatricula     = lorc_rc.getIdMatricula();
							ls_idCirculo       = lorc_rc.getIdCirculo();

							if(!NumericUtils.isValidLong(ll_idMatricula))
							{
								AccPredioRegistro lapr_predio;

								lapr_predio = getAccPredioRegistro();

								if(lapr_predio != null)
									ll_idMatricula = lapr_predio.getIdMatricula();
							}

							lrc_temp.setIdCirculo(ls_idCirculo + "-" + ll_idMatricula);

							lcrc_original.add(lrc_temp);

							if(lb_baldios && StringUtils.isValidString(ls_idCirculo))
							{
								boolean                        lb_eliminado;
								Iterator<RegistroCalificacion> lirc_iterator;

								lb_eliminado      = false;
								lirc_iterator     = lcrc_original.iterator();

								while(lirc_iterator.hasNext() && !lb_eliminado)
								{
									RegistroCalificacion lrc_data;

									lrc_data = lirc_iterator.next();

									if(lrc_data != null)
									{
										String ls_matricula;

										ls_matricula = lrc_data.getIdCirculo();

										if(
										    StringUtils.isValidString(ls_idCirculo)
											    && ls_matricula.equalsIgnoreCase(ls_idCirculo + "-0")
										)
										{
											lb_eliminado = true;
											lcrc_original.remove(lrc_data);
										}
									}
								}
							}
						}
					}

					lb_matriculasSegregadas = CollectionUtils.isValidCollection(lcap_ap);
					lorc_tmp.setIndSegregacion(lb_matriculasSegregadas);

					if(isHabilitarMedidaCautelar())
					{
						if(isEditarDatosOficinaMedidaCautelar())
							throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_PROCESO_MEDIDA_CAUTELAR);

						guardarCambiosOficinaOrigen();

						lorc_rc.setReferencia(getReferencia());
						lorc_rc.setNumeroProceso(getNumeroProceso());
						lorc_rc.setOficinaOrigenMedidaCautelar(getOficinaMedidaCautelar());
						lorc_rc.setMedidaCautelar(true);
					}

					if(isPanelCierreFolio() && ab_guardarArchivo)
						lorc_rc = salvarCierreFolio(lorc_rc, false);

					lorc_rc.setMatriculasSegregadas(lcap_ap);
					lorc_rc.setEnvioCalificador(true);
					lorc_rc.setIndVinculado(isIndVinculado());
					lorc_rc.setGenerarPDFCorrespondenciaRegistro(
					    isEsNotificacionResidencia() || isEsNotificacionCorrespondencia()
					);
					lorc_rc.setGenerarArchivoNotaExcesoPago(isAnotacionesPredioEliminadas());

					lab_file = irr_calificacionRemote.genereteFileComunicadoDireccion(
						    lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false
						);

					if(lab_file != null)
					{
						InputStream lis_stream;
						String      ls_nombreArchivo;

						lis_stream     = new ByteArrayInputStream(lab_file);

						ls_nombreArchivo = ConstanteCommon.NOMBRE_ARCHIVO_GENERADO;

						setImagenComunicado(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.PDF, ls_nombreArchivo + ExtensionCommon.PDF_MAYUS_PUNTO
						    )
						);
					}

					else
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

					ib_pdfGenerado = true;
				}
			}
			catch(B2BException lb2be_e)
			{
				PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
				addMessage(lb2be_e);
			}

			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Generate file nota informativa.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generateFileNotaInformativa()
	    throws B2BException
	{
		byte[] lab_file;

		try
		{
			RegistroCalificacion lorc_rc;

			lorc_rc = new RegistroCalificacion();

			lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lorc_rc.setIdMotivo(getMotivo());
			lorc_rc.setUserId(getUserId());
			lorc_rc.setTurno(getTurno());

			{
				boolean      lb_focus;
				FacesContext lfc_context;

				lb_focus        = true;
				lfc_context     = FacesContext.getCurrentInstance();

				String ls_observaciones;

				ls_observaciones = getObservacionesNotaInfo();
				lb_focus        = validateStyles(
					    "fRegistroCalif:idITAobservacionesNotaInfo", lfc_context, ls_observaciones, lb_focus
					);

				if(StringUtils.isValidString(ls_observaciones))
				{
					if(ls_observaciones.length() < 100)
						throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_100);

					lorc_rc.setObservaciones(ls_observaciones);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
			}

			lab_file = irr_calificacionRemote.generateFileNotaInformativa(
				    lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lab_file != null)
			{
				InputStream lis_stream;
				String      ls_nombreArchivo;

				lis_stream           = new ByteArrayInputStream(lab_file);
				ls_nombreArchivo     = TipoArchivoCommon.DEVOLUCION_DINERO + IdentificadoresCommon.PUNTO
					+ ExtensionCommon.PDF;

				setFileNotaInformativa(new DefaultStreamedContent(lis_stream, "application/pdf", ls_nombreArchivo));
				setPdfRemate(true);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			setPdfRemate(false);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param as_id de as id
	 * @return el valor de string
	 */
	public String generateId(String as_id)
	{
		String ls_id;
		int    li_i;

		li_i      = as_id.indexOf(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
		ls_id     = as_id.substring(li_i + 1);
		li_i      = ls_id.indexOf("-");
		ls_id     = ls_id.substring(0, li_i);

		return ls_id;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String guardarArhivo()
	    throws B2BException, IOException
	{
		String ls_return;

		ls_return = null;

		if(!isBloquearBotonAprobador())
		{
			setBloquearBotonAprobador(true);

			try
			{
				if(isProcesoRemanente() && !isAnotacionesPredioEliminadas())
				{
					if(!isPdfRemate())
						throw new B2BException(ErrorKeys.ERROR_GENERAR_ARCHIVO_NOTA_INFORMATIVA);
				}

				String ls_idTurnoHistoria;

				ls_idTurnoHistoria = getIdTurnoHistoria();

				if(StringUtils.isValidString(ls_idTurnoHistoria))
				{
					if(isCierreFolio())
						validarCierreFolio();

					if(isAnotacionesPredioEliminadas() && !isGeneroNotaInformativaPagoExceso())
						throw new B2BException(ErrorKeys.DEBE_GENERAR_NOTA_EXCESO_DE_PAGO);

					if(isFileGenerado())
					{
						if(isProcesoBaldios() && (getImagen() == null))
							throw new B2BException(ErrorKeys.NO_GENERO_PDF);

						boolean       lb_procesoTerminado;
						String        ls_observacionesTemp;
						TurnoHistoria lth_turnoHistoriaTemp;

						lb_procesoTerminado       = false;
						ls_observacionesTemp      = null;
						lth_turnoHistoriaTemp     = new TurnoHistoria();

						lth_turnoHistoriaTemp.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

						lth_turnoHistoriaTemp.setIdTurno(getTurno());

						Map<TurnoHistoria, Boolean> lmthb_turnosDerivados;
						lmthb_turnosDerivados = irr_calificacionRemote.consultarTurnosDerivados(
							    lth_turnoHistoriaTemp, getUserId()
							);

						if(CollectionUtils.isValidCollection(lmthb_turnosDerivados))
						{
							for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnosDerivados.entrySet())
							{
								TurnoHistoria lth_turnoHistoriaActual;

								lth_turnoHistoriaActual = lmthb_iterador.getKey();

								if(lth_turnoHistoriaActual != null)
								{
									RegistroCalificacion locrc_rc;
									locrc_rc = new RegistroCalificacion();

									String ls_idTurnoHistoriaTMP;
									String ls_idTurno;

									ls_idTurnoHistoriaTMP     = StringUtils.getString(
										    lth_turnoHistoriaActual.getIdTurnoHistoria()
										);
									ls_idTurno                = lth_turnoHistoriaActual.getIdTurno();

									if(StringUtils.isValidString(ls_idTurnoHistoriaTMP))
										locrc_rc = irr_calificacionRemote.findMatriculas(
											    null, ls_idTurnoHistoriaTMP, ls_idTurno, getUserId(),
											    getRemoteIpAddress(), true, EtapaCommon.ID_ETAPA_CALIFICACION
											);

									if(locrc_rc != null)
									{
										AccPredioRegistro                lapr_predioRegistro;
										Collection<RegistroCalificacion> lcrc_rc;
										Long                             ll_idMatriculaDummy;

										lapr_predioRegistro     = getAccPredioRegistro();
										lcrc_rc                 = locrc_rc.getAllMatriculas();
										ll_idMatriculaDummy     = null;

										if(lapr_predioRegistro != null)
											ll_idMatriculaDummy = lapr_predioRegistro.getIdMatricula();

										if(CollectionUtils.isValidCollection(lcrc_rc))
										{
											boolean lb_noTieneIntervinientes;

											lb_noTieneIntervinientes = false;

											for(RegistroCalificacion lorc_rc : lcrc_rc)
											{
												String[] lsa_matricula;
												Long     lli_idMatricula;

												lsa_matricula       = lorc_rc.getIdCirculo().split("-");
												lli_idMatricula     = null;

												if(lsa_matricula.length > 1)
													lli_idMatricula = NumericUtils.getLongWrapper(lsa_matricula[1]);

												if(!NumericUtils.isValidLong(lli_idMatricula, 1L))
													lli_idMatricula = ll_idMatriculaDummy;

												if(NumericUtils.isValidLong(lli_idMatricula))
												{
													Collection<String> lc_tipoDocumentos;
													lc_tipoDocumentos = irr_calificacionRemote
															.getTipoDocPersonaByMatricula(
															    lli_idMatricula, ls_idTurnoHistoriaTMP
															);

													if(!CollectionUtils.isValidCollection(lc_tipoDocumentos))
														lb_noTieneIntervinientes = true;
												}
											}

											if(isProcesoBaldios() && lb_noTieneIntervinientes)
											{
												RegistroCalificacion lrc_data;

												lrc_data = getMatriculas();

												if(lrc_data != null)
												{
													Long ll_idMatricula;

													ll_idMatricula = lrc_data.getIdMatricula();

													if(NumericUtils.isValidLong(ll_idMatricula))
													{
														Collection<String> lc_tipoDocumentos;
														lc_tipoDocumentos = irr_calificacionRemote
																.getTipoDocPersonaByMatricula(
																    ll_idMatricula, ls_idTurnoHistoriaTMP
																);

														if(CollectionUtils.isValidCollection(lc_tipoDocumentos))
															lb_noTieneIntervinientes = false;
													}
												}
											}

											if(lb_noTieneIntervinientes)
												throw new B2BException(ErrorKeys.DEBE_AGREGAR_INTERVINIENTE_ANOTACION);
										}
									}
								}
							}
						}

						lth_turnoHistoriaTemp = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoriaTemp);

						if(lth_turnoHistoriaTemp != null)
						{
							String ls_etadoActividad;

							ls_etadoActividad = lth_turnoHistoriaTemp.getEstadoActividad();

							if(StringUtils.isValidString(ls_etadoActividad))
							{
								lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
								ls_observacionesTemp     = lth_turnoHistoriaTemp.getObservaciones();
							}
						}

						if(lb_procesoTerminado)
						{
							setObservacionesTemporales(ls_observacionesTemp);

							PrimeFaces.current().executeScript("PF('dlgSuspension').show();");
							PrimeFaces.current().ajax().update("fDialogSuspension:idOTObsTemp");
						}
						else
						{
							Collection<AreaPredio> lcap_matrInfo;
							CalificacionRemote     lcr_remote;
							lcap_matrInfo     = getMatriculasInformacion();
							lcr_remote        = irr_calificacionRemote;

							if(CollectionUtils.isValidCollection(lcap_matrInfo))
							{
								int li_cont;

								li_cont = 0;

								for(AreaPredio lap_areaPredio : lcap_matrInfo)
								{
									if(lap_areaPredio != null)
									{
										if(lap_areaPredio.isRevisado())
											li_cont++;
									}
								}

								BigDecimal lbd_etapa;
								lbd_etapa = lcr_remote.obtenerEtapaActual(
									    ls_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(NumericUtils.isValidBigDecimal(lbd_etapa))
								{
									if(NumericUtils.getLong(lbd_etapa) == EtapaCommon.ID_ETAPA_CALIFICACION)
									{
										Constantes lc_porcentaje;
										String     ls_nombreConstante;

										ls_nombreConstante     = isDivisionMaterial()
											? ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS_DIVISION_MATERIAL
											: ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS;

										lc_porcentaje = lcr_remote.findConstanteById(
											    ls_nombreConstante, getUserId(), getLocalIpAddress(),
											    getRemoteIpAddress()
											);

										if(lc_porcentaje == null)
										{
											Object[] loa_messageArgs;

											loa_messageArgs        = new String[1];
											loa_messageArgs[0]     = ls_nombreConstante;

											throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
										}

										int li_porcentaje;
										li_porcentaje = NumericUtils.getInt(lc_porcentaje.getEntero());

										if(((li_cont * 100) / lcap_matrInfo.size()) < li_porcentaje)
										{
											Object[] loa_messageArgs = new String[1];

											loa_messageArgs[0] = StringUtils.getString(li_porcentaje);

											throw new B2BException(
											    ErrorKeys.ERROR_PORCENTAJE_REVISION_INCOMPLETO, loa_messageArgs
											);
										}
									}

									if(NumericUtils.getLong(lbd_etapa) == EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO)
									{
										Constantes lc_porcentaje;
										lc_porcentaje     = new Constantes();

										lc_porcentaje = lcr_remote.findConstanteById(
											    ConstanteCommon.PORCENTAJE_REVISION_MATRICULAS_MASIVO, getUserId(),
											    getLocalIpAddress(), getRemoteIpAddress()
											);

										if(lc_porcentaje == null)
											throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);

										int li_porcentaje;
										li_porcentaje = lc_porcentaje.getEntero().intValue();

										if(((li_cont * 100) / lcap_matrInfo.size()) < li_porcentaje)
										{
											Object[] loa_messageArgs = new String[1];

											loa_messageArgs[0] = StringUtils.getString(li_porcentaje);

											throw new B2BException(ErrorKeys.ERROR_PORCENTAJE_REVISION_INCOMPLETO);
										}
									}
								}
							}

							{
								BigDecimal lbd_etapaAnterior;
								lbd_etapaAnterior = lcr_remote.obtenerEtapaAnterior(
									    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(NumericUtils.isValidBigDecimal(lbd_etapaAnterior))
								{
									if(NumericUtils.getLong(lbd_etapaAnterior) == EtapaCommon.ID_ETAPA_REANOTACION)
									{
										TurnoHistoria lth_turnoHistoria;

										lth_turnoHistoria = new TurnoHistoria();

										lth_turnoHistoria.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(getIdTurnoHistoria())
										);
										lcr_remote.updateNotaDevolutiva(
										    lth_turnoHistoria, true, getUserId(), getLocalIpAddress(),
										    getRemoteIpAddress()
										);
									}
								}
							}

							{
								Collection<AreaPredio> lcap_ap;
								RegistroCalificacion   lrc_tmp;

								lcap_ap     = getMatriculasInformacion();
								lrc_tmp     = getMatriculas();

								if(CollectionUtils.isValidCollection(lcap_ap) && (lrc_tmp != null))
								{
									String ls_matriculaErrada;

									ls_matriculaErrada = irr_calificacionRemote.calcularArea(lrc_tmp, lcap_ap);

									if(StringUtils.isValidString(ls_matriculaErrada))
										throw new B2BException(ErrorKeys.ERROR_SUMA_AREAS);
								}
							}

							setImagen(null);
							generateFile(true);
							setRevisionMatriculas(null);
							clean();
							ls_return = NavegacionCommon.CALIFICACION;
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_GENERAR_CONSTANCIA);

					BeanCalificacion lbde_bean;

					lbde_bean = (BeanCalificacion)FacesUtils.obtenerInstanciaBean(
						    BeanCalificacion.class, BeanNameCommon.BEAN_CALIFICACION
						);

					if(lbde_bean != null)
					{
						lbde_bean.clear();
						lbde_bean.generarDatosTramiteCantidad();
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				PrimeFaces lpf_primeFaces;

				lpf_primeFaces = PrimeFaces.current();

				setBloquearBotonAprobador(false);
				addMessage(lb2be_e);
				actualizarComponente(is_messageIdGrowl);
				ls_return = null;
				lpf_primeFaces.executeScript(
				    "mostrarElemento('fRegistroCalif:idCBDigitadorMasivo');mostrarElemento('fRegistroCalif:idCBAprobador');"
				);
			}
		}

		return ls_return;
	}

	/**
	 * Guardar calificacion reloteo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarCalificacionReloteo()
	    throws B2BException
	{
		try
		{
			Collection<MatriculaSegregacionUi> lcmui_cmui;
			RegistroCalificacion               lrc_rc;
			String                             ls_idCirculo;
			Long                               ll_idMatricula;

			lcmui_cmui         = getDataInfoPredial();
			lrc_rc             = getDetalleCalificacionReloteo();
			ll_idMatricula     = null;

			if(lrc_rc != null)
			{
				ls_idCirculo = lrc_rc.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					Long ll_matriculasAperturar;
					ll_matriculasAperturar = getMatriculasAperturar().get(ls_idCirculo);

					if(
					    CollectionUtils.isValidCollection(lcmui_cmui)
						    && NumericUtils.isValidLong(ll_matriculasAperturar)
					)
					{
						int                  li_size;
						RegistroCalificacion lrc_tmp;

						li_size     = lcmui_cmui.size();
						lrc_tmp     = new RegistroCalificacion();

						if(li_size != NumericUtils.getInt(ll_matriculasAperturar))
							throw new B2BException(ErrorKeys.CANTIDAD_MATRICULAS_APERTURAR_RELOTEO);

						if(ls_idCirculo.contains("-"))
						{
							String[] las_as;
							las_as = ls_idCirculo.split("-");

							if(las_as != null)
							{
								ls_idCirculo = las_as[0];
								lrc_tmp.setIdCirculo(ls_idCirculo);

								ll_idMatricula = NumericUtils.getLongWrapper(las_as[1]);
								lrc_tmp.setIdMatricula(ll_idMatricula);
							}
						}

						if(CollectionUtils.isValidCollection(lcmui_cmui))
						{
							MatriculaSegregacion             lms_ms;
							Collection<MatriculaSegregacion> lcms_ms;
							double                           ld_sumAreas;

							lcms_ms         = new ArrayList<MatriculaSegregacion>();
							ld_sumAreas     = 0.0;

							for(MatriculaSegregacionUi lmsui_msui : lcmui_cmui)
							{
								if(lmsui_msui != null)
								{
									lms_ms = new MatriculaSegregacion();

									lms_ms.setUnidad(lmsui_msui.getUnidad());
									lms_ms.setNombrePredio(lmsui_msui.getNombrePredio());
									lms_ms.setDireccion(lmsui_msui.getDireccion());
									lms_ms.setCertificadoAsociado(lmsui_msui.isCertificadoAsociado());
									lms_ms.setCantidadCertificados(lmsui_msui.getCantidad());
									lms_ms.setAreaTerreno(lmsui_msui.getAreaTerreno());
									lms_ms.setAreaPrivada(lmsui_msui.getAreaPrivada());
									lms_ms.setAreaConstruida(lmsui_msui.getAreaConstruida());
									lms_ms.setCoeficiente(lmsui_msui.getCoeficiente());
									lms_ms.setIdSolicitud(lmsui_msui.getIdSolicitud());
									lms_ms.setIdCirculoMatriz(ls_idCirculo);
									lms_ms.setMatriculaMatriz(ll_idMatricula);
									ld_sumAreas += NumericUtils.getDouble(lms_ms.getAreaTerreno());

									lcms_ms.add(lms_ms);
								}
							}

							if(ld_sumAreas > 0.0)
								irr_registroRemote.compareAreas(ls_idCirculo, ll_idMatricula, ld_sumAreas);

							if(CollectionUtils.isValidCollection(lcms_ms))
							{
								lrc_tmp.setIdSolicitud(getIdSolicitud());
								lrc_tmp.setInfoMatriculasSegregacion(lcms_ms);

								irr_calificacionRemote.insertarMatriculasSegregacion(
								    lrc_tmp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

								setDataInfoPredial(null);
								setSeccionMatriculasAperturar(false);
								detalleMatriculaReloteo();
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.CANTIDAD_MATRICULAS_APERTURAR_RELOTEO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().executeScript("PF('idDlgCalificacionReloteo').show();");
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar cambios para pdf medida cautelar.
	 */
	public void guardarCambiosParaPdfMedidaCautelar()
	{
		try
		{
			guardarCambiosOficinaOrigen();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageIdGrowl);
	}

	/**
	 * Guardar desenglobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarDesenglobes()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_arg;
			RegistroCalificacion lrc_dataAnotacionesHeredar;

			lrc_arg                        = getMatriculas();
			lrc_dataAnotacionesHeredar     = getMatriculasAHeredar();

			lrc_arg.setMatriculasAHeredar(lrc_dataAnotacionesHeredar);
			lrc_arg.setTurno(getTurno());
			lrc_arg.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			if(lrc_dataAnotacionesHeredar != null)
			{
				Collection<RegistroCalificacion> lcrc_anotacionesHeredar;

				lcrc_anotacionesHeredar = lrc_dataAnotacionesHeredar.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_anotacionesHeredar))
				{
					boolean                        lb_tmp;
					Iterator<RegistroCalificacion> lirc_iterator;

					lb_tmp            = false;
					lirc_iterator     = lcrc_anotacionesHeredar.iterator();

					while(lirc_iterator.hasNext() && !lb_tmp)
					{
						RegistroCalificacion lorc_rc;

						lorc_rc = lirc_iterator.next();

						if(lorc_rc != null)
						{
							if(lorc_rc.isRevision())
							{
								lb_tmp = true;
								setCargarBotonesRegistro(true);
							}
						}
					}

					if(!lb_tmp)
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
				}
			}

			setSalvarDesenglobes(
			    irr_calificacionRemote.salvarDesenglobe(
			        lrc_arg, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			PrimeFaces.current().executeScript("PF('idWPanelProcesos').collapse();");

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
		}
		finally
		{
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @param as_proceso correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String guardarInfoRegistro(boolean ab_b, String as_proceso)
	    throws B2BException
	{
		BeanDireccion          lbd_beanDireccion;
		boolean                lb_validado;
		Collection<AreaPredio> lcap_ap;
		Long                   ll_idTurnoHistoria;
		RegistroCalificacion   lorc_dataFinal;
		RegistroCalificacion   lorc_return;
		String                 ls_tipoComplementacion;
		String                 li_tab;
		String                 ls_idTurno;
		String                 ls_matricula;

		lbd_beanDireccion          = getBeanDireccion();
		lb_validado                = false;
		lorc_dataFinal             = new RegistroCalificacion();
		lorc_return                = new RegistroCalificacion();
		ls_tipoComplementacion     = null;
		li_tab                     = null;
		ls_matricula               = getMatriculaDetalleReloteo();

		if(StringUtils.isValidString(ls_matricula))
			lorc_dataFinal.setIdCirculoMatriz(ls_matricula);
		else
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				Collection<RegistroCalificacion> lcrc_data;

				lcrc_data = lrc_data.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_data))
				{
					String ls_idCirculoMatriz;

					ls_idCirculoMatriz = null;

					for(RegistroCalificacion lrc_iterador : lcrc_data)
					{
						if(lrc_iterador != null)
						{
							String ls_idCirculo;

							ls_idCirculo = lrc_iterador.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
								ls_idCirculoMatriz = ls_idCirculo;
						}
					}

					lorc_dataFinal.setIdCirculoMatriz(ls_idCirculoMatriz);
				}
			}
		}

		ll_idTurnoHistoria = NumericUtils.getLongWrapper(getIdTurnoHistoria());

		if(NumericUtils.isValidLong(ll_idTurnoHistoria))
			lorc_dataFinal.setIdTurnoHistoria(ll_idTurnoHistoria);

		ls_idTurno = getTurno();

		if(StringUtils.isValidString(ls_idTurno))
			lorc_dataFinal.setTurno(ls_idTurno);

		lorc_dataFinal.setDevolucion(isDevolucion());
		lorc_dataFinal.setSalvarDefinitivo(ab_b);
		lorc_dataFinal.setProcesoAValidar(as_proceso);

		if(!ab_b && StringUtils.isValidString(as_proceso))
		{
			String ls_idAnotaciones;
			String ls_idComplementacion;
			String ls_idDireccion;

			ls_idAnotaciones         = IdentificadoresCommon.ID_T_ANOTACIONES;
			ls_idComplementacion     = IdentificadoresCommon.ID_T_COMPLEMENTACION;
			ls_idDireccion           = IdentificadoresCommon.ID_T_DIRECCION_PREDIO_RC;

			/* Validaciones tab matriculas a heredar */
			if(as_proceso.equalsIgnoreCase(ls_idAnotaciones))
			{
				RegistroCalificacion             lorc_tmp;
				Collection<RegistroCalificacion> lcrc_tmp;
				boolean                          lb_tmp;

				lorc_tmp     = getMatriculasAHeredar();
				lb_tmp       = false;

				if(lorc_tmp != null)
				{
					lcrc_tmp = lorc_tmp.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_tmp))
					{
						Iterator<RegistroCalificacion> lirc_iterator;

						lirc_iterator = lcrc_tmp.iterator();

						while(lirc_iterator.hasNext() && !lb_tmp)
						{
							RegistroCalificacion lorc_rc;

							lorc_rc = lirc_iterator.next();

							if(lorc_rc != null)
							{
								if(lorc_rc.isRevision())
									lb_tmp = true;
							}
						}

						if(!lb_tmp)
						{
							li_tab = ls_idAnotaciones;
							throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
						}

						lorc_dataFinal.setDatosDocumento(lorc_tmp);
					}
				}
			}

			/* Validaciones tab Complementacion */
			else if(as_proceso.equalsIgnoreCase(ls_idComplementacion))
			{
				ComplementacionCalificacion lcc_complementacionCalificacion;
				lcc_complementacionCalificacion = getComplementacionCalificacion();

				if(lcc_complementacionCalificacion != null)
				{
					ComplementacionPredio lcp_complementacionPredio;
					lcp_complementacionPredio = lcc_complementacionCalificacion.getComplementacionPredio();

					if(lcp_complementacionPredio != null)
					{
						lcp_complementacionPredio.setIdUsuarioCreacion(getUserId());

						if(isDevolucion())
							lcc_complementacionCalificacion.setTipoComplementacion(TipoComplementacionCommon.EXISTENTE);

						ls_tipoComplementacion = lcc_complementacionCalificacion.getTipoComplementacion();

						if(StringUtils.isValidString(ls_tipoComplementacion))
						{
							if(!ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
							{
								String ls_complementacion;
								ls_complementacion = lcp_complementacionPredio.getComplementacion();

								if(StringUtils.isValidString(ls_complementacion))
								{
									if(!ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.CONSTRUIR))
									{
										if(ls_complementacion.trim().length() < 99)
										{
											li_tab = ls_idComplementacion;
											throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
										}
									}
								}
								else
								{
									li_tab = ls_idComplementacion;
									throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TAM);
								}
							}
							else
							{
								String ls_observaciones;

								ls_observaciones = lcc_complementacionCalificacion.getObservaciones();

								if(!StringUtils.isValidString(ls_observaciones))
								{
									li_tab = ls_idComplementacion;
									throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
								}

								setOcultarBotonAprobador(false);
							}

							lorc_dataFinal.setComplementacionCalificacion(lcc_complementacionCalificacion);
						}
						else
						{
							li_tab = ls_idComplementacion;
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
						}
					}
				}

				LinderoRegistroCalificacion llrc_linderoRegistroCalificacion;
				llrc_linderoRegistroCalificacion = getLinderoRegistroCalificacion();

				if(llrc_linderoRegistroCalificacion != null)
				{
					String ls_lindero;
					ls_lindero = llrc_linderoRegistroCalificacion.getLindero();

					if(isNuevaLinderos())
					{
						if(!StringUtils.isValidString(llrc_linderoRegistroCalificacion.getObservaciones()))
							throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_LINDERO_NUEVA);
						else
							llrc_linderoRegistroCalificacion.setNuevoLindero(true);
					}
					else
					{
						if(StringUtils.isValidString(ls_lindero))
						{
							Collection<AccLinderoPredio> lcalp_accLinderoPredios;
							Collection<AccLinderoPredio> lcalp_return;

							lcalp_accLinderoPredios     = llrc_linderoRegistroCalificacion.getLinderoPredios();
							lcalp_return                = new ArrayList<AccLinderoPredio>();

							for(AccLinderoPredio alp_linderoPredio : lcalp_accLinderoPredios)
							{
								alp_linderoPredio.setIdTurno(ls_idTurno);
								alp_linderoPredio.setIdTurnoHistoria(ll_idTurnoHistoria);
								alp_linderoPredio.setLindero(ls_lindero);
								alp_linderoPredio.setIdUsuarioCreacion(getUserId());
								alp_linderoPredio.setIpCreacion(getRemoteIpAddress());

								lcalp_return.add(alp_linderoPredio);
							}

							llrc_linderoRegistroCalificacion.setLinderoPredios(lcalp_return);
						}
						else
						{
							li_tab = ls_idComplementacion;
							throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
						}

						lorc_dataFinal.setLinderoCargado(true);
						llrc_linderoRegistroCalificacion.setNuevoLindero(false);
					}
				}
				else
				{
					li_tab = ls_idComplementacion;
					throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
				}

				lorc_dataFinal.setLinderoRegistroCalificacion(llrc_linderoRegistroCalificacion);
			}

			/* Validaciones tab direccion predio */
			else if(as_proceso.equalsIgnoreCase(ls_idDireccion))
			{
				DireccionDelPredio lodp_tmp;

				try
				{
					lb_validado = confirmarDireccionPredio();
				}
				catch(B2BException e)
				{
					li_tab          = ls_idDireccion;
					lb_validado     = false;
				}

				lodp_tmp = getDataDireccionPredio();

				if(lodp_tmp != null)
					lorc_dataFinal.setDataDireccionPredio(lodp_tmp);
			}
		}

		if(lorc_dataFinal != null)
			lorc_return = irr_calificacionRemote.guardarInfoRegistro(
				    lorc_dataFinal, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

		if(lorc_return != null)
		{
			lcap_ap = lorc_return.getMatriculasInformacion();

			if(CollectionUtils.isValidCollection(lcap_ap))
			{
				Map<String, Boolean> lmsb_tmp;
				lmsb_tmp = getRevisionMatriculas();

				for(AreaPredio lap_tmp : lcap_ap)
				{
					if(lap_tmp != null)
					{
						if(lap_tmp.isRevisado())
							lmsb_tmp.put(lap_tmp.getIdPredioRegistro(), new Boolean(true));
					}
				}

				setMatriculasInformacion(isProcesoBaldios() ? null : lcap_ap);
				setHabilitarTabs(isProcesoBaldios());
			}

			if(lb_validado)
			{
				limpiarTabsReloteo();
				setDeshabilitarBotonConfirmarDireccion(true);
			}
		}

//		if(StringUtils.getStringNotNull(ls_tipoComplementacion).equalsIgnoreCase(TipoComplementacionCommon.NUEVA))
//		{
//			addMessage(MessagesKeys.NO_COMPLEMENTACION_CERIFICAR_DIGITADOR_MASIVO);
//			PrimeFaces.current().ajax().update(is_messageIdGrowl);
//		}
		setHabilitarDigitador(false);
		lbd_beanDireccion.setModificarDireccionPredio(false);
		setModificarDireccionPredio(false);

		if(!lb_validado)
			setDeshabilitarBotonConfirmarDireccion(false);

		return li_tab;
	}

	/**
	 * Modificar información matricula.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarInformacionNotificacion()
	    throws B2BException
	{
		try
		{
			boolean lb_correspondencia;
			boolean lb_residencia;

			lb_correspondencia     = isEsNotificacionCorrespondencia();
			lb_residencia          = isEsNotificacionResidencia();

			if(lb_correspondencia || lb_residencia)
			{
				BeanDireccion    lbd_beanDireccion;
				PersonaDireccion lpd_direccion;

				lbd_beanDireccion     = getBeanDireccion();
				lpd_direccion         = lb_residencia ? lbd_beanDireccion.getDireccionResidencia()
					                                  : lbd_beanDireccion.getDireccionCorrespondencia();

				lbd_beanDireccion.validarCamposDireccion(lpd_direccion, lb_residencia, false, true);

				PersonaTelefono          lpt_personaTelefono;
				Registro                 lr_registro;
				PersonaCorreoElectronico lpce_personaCorreo;
				String                   is_datoParaValidar;
				boolean                  lb_focus;
				FacesContext             lfc_context;
				PersonaTelefono          lpt_telefonoMovil;
				String                   ls_paisMovil;
				String                   ls_telefono;
				String                   ls_paisFijo;

				lpce_personaCorreo      = getCorreoElectronico();
				lbd_beanDireccion       = getBeanDireccion();
				lr_registro             = new Registro();
				lpt_personaTelefono     = getTelefonoFijo();
				lfc_context             = FacesContext.getCurrentInstance();
				lb_focus                = true;
				lpt_telefonoMovil       = getTelefonoMovil();
				ls_paisMovil            = "";
				ls_telefono             = "";
				ls_paisFijo             = "";

				if(lpt_personaTelefono != null)
				{
					ls_paisFijo = StringUtils.getStringNotNull(lpt_personaTelefono.getIdPais());
					validateStyles(is_idForm + ":idSOMPaisTel", lfc_context, ls_paisFijo, lb_focus);

					ls_telefono = StringUtils.getStringNotNull(lpt_personaTelefono.getTelefono());

					if(StringUtils.isValidString(ls_telefono))
					{
						Constantes lc_digitosFijo;
						lc_digitosFijo = irr_referenceRemote.findConstantesById(ConstanteCommon.DIGITOS_TEL_FIJO_COL);

						if(lc_digitosFijo == null)
							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);

						BigInteger lbi_temp;
						int        li_digitos;

						lbi_temp       = lc_digitosFijo.getEntero();
						li_digitos     = 0;

						if(NumericUtils.isValidBigInteger(lbi_temp))
							li_digitos = lbi_temp.intValue();

						if(
						    ls_paisFijo.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
							    && (ls_telefono.length() != li_digitos)
						)
							lb_focus = validateStyles(is_idForm + ":idItTelefonoFijo", lfc_context, "", lb_focus);
						else
							lb_focus = validateStyles(
								    is_idForm + ":idItTelefonoFijo", lfc_context, ls_telefono, lb_focus
								);
					}
				}

				if(lpt_telefonoMovil != null)
				{
					ls_paisMovil = StringUtils.getStringNotNull(lpt_personaTelefono.getIdPais());
					validateStyles(is_idForm + ":idSOMPaisTelMov", lfc_context, ls_paisMovil, lb_focus);

					ls_telefono = StringUtils.getStringNotNull(lpt_personaTelefono.getTelefono());

					if(StringUtils.isValidString(ls_telefono))
					{
						Constantes lc_digitosFijo;
						lc_digitosFijo = irr_referenceRemote.findConstantesById(ConstanteCommon.DIGITOS_TEL_MOVIL_COL);

						if(lc_digitosFijo == null)
							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);

						BigInteger lbi_temp;
						int        li_digitos;

						lbi_temp       = lc_digitosFijo.getEntero();
						li_digitos     = 0;

						if(NumericUtils.isValidBigInteger(lbi_temp))
							li_digitos = lbi_temp.intValue();

						if(
						    ls_paisMovil.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
							    && (ls_telefono.length() != li_digitos)
						)
							lb_focus = validateStyles(is_idForm + ":idItTelefonoMovil", lfc_context, "", lb_focus);
						else
							lb_focus = validateStyles(
								    is_idForm + ":idItTelefonoMovil", lfc_context, ls_telefono, lb_focus
								);
					}
				}

				if(lpce_personaCorreo != null)
				{
					is_datoParaValidar     = lpce_personaCorreo.getCorreoElectronico();

					lb_focus = validateStyles(is_idForm + ":idItEmail", lfc_context, is_datoParaValidar, lb_focus);

					if(
					    StringUtils.isValidString(is_datoParaValidar)
						    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(is_datoParaValidar)
					)
						lb_focus = validateStyles(is_idForm + ":idItEmail", lfc_context, "", lb_focus);
				}

				lr_registro.setTelefonoFijo(lpt_personaTelefono);
				lr_registro.setTelefonoMovil(lpt_telefonoMovil);
				lr_registro.setPersonaCorreoElectronico(lpce_personaCorreo);

				Solicitud ls_solicitud;
				ls_solicitud = new Solicitud();
				ls_solicitud.setIdSolicitud(getIdSolicitud());

				lr_registro.setSolicitud(ls_solicitud);

				irr_calificacionRemote.guardarInformacionNotificacion(
				    lpd_direccion, lr_registro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			setModificarInformacionNotificacion(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar total registro califacion.
	 */
	public void guardarTotalRegistroCalifacion()
	{
		String ls_tab;
		ls_tab = null;

		try
		{
			ls_tab = guardarInfoRegistro(true, null);
		}
		catch(B2BException lb2be_e)
		{
			if(StringUtils.isValidString(ls_tab))
			{
				Wizard ltv_tv = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
						                                .findComponent("fRegistroCalif:idTVRegistroCalificacion");
				ltv_tv.setStep(ls_tab);
				PrimeFaces.current().ajax().update("fRegistroCalif:idFormTabs");
			}

			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar venta parcial.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarVentaParcial()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_arg;
			RegistroCalificacion lrc_dataAnotacionesHeredar;

			lrc_arg                        = getMatriculas();
			lrc_dataAnotacionesHeredar     = getMatriculasAHeredar();

			lrc_arg.setMatriculasAHeredar(lrc_dataAnotacionesHeredar);
			lrc_arg.setTurno(getTurno());
			lrc_arg.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			if(lrc_dataAnotacionesHeredar != null)
			{
				Collection<RegistroCalificacion> lcrc_anotacionesHeredar;

				lcrc_anotacionesHeredar = lrc_dataAnotacionesHeredar.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_anotacionesHeredar))
				{
					boolean                        lb_tmp;
					Iterator<RegistroCalificacion> lirc_iterator;

					lb_tmp            = false;
					lirc_iterator     = lcrc_anotacionesHeredar.iterator();

					while(lirc_iterator.hasNext() && !lb_tmp)
					{
						RegistroCalificacion lorc_rc;

						lorc_rc = lirc_iterator.next();

						if(lorc_rc != null)
						{
							if(lorc_rc.isRevision())
								lb_tmp = true;
						}
					}

					if(!lb_tmp)
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
				}
			}

			boolean lb_return;

			lb_return = irr_calificacionRemote.salvarVentaParcial(
				    lrc_arg, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false, true
				);

			if(lb_return)
				PrimeFaces.current().executeScript("PF('idWPanelProcesos').collapse();");

			setSalvarVentaParcial(lb_return);
			setSalvarVentaParcialCementerio(lb_return);
			cargarDatosAreaVenta();
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Guardar venta parcial no cementerio.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarVentaParcialNoCementerio()
	    throws B2BException
	{
		try
		{
			AccLinderoPredio     lalp_lindero;
			RegistroCalificacion lrc_data;

			lalp_lindero     = getLinderoVentaNoCementerio();
			lrc_data         = getMatriculas();

			if((lalp_lindero != null) && (lrc_data != null))
			{
				Long   ll_idMatricula;
				String ls_idCirculo;

				ll_idMatricula     = lrc_data.getIdMatriculaMatriz();
				ls_idCirculo       = lrc_data.getIdCirculoMatriz();

				if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
				{
					String ls_lindero;

					ls_lindero = lalp_lindero.getLindero();

					if(StringUtils.isValidString(ls_lindero))
					{
						lalp_lindero.setIdCirculo(ls_idCirculo);
						lalp_lindero.setIdMatricula(ll_idMatricula);
						lalp_lindero.setIdTurno(getTurno());
						lalp_lindero.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

						setSalvarVentaParcialNoCementerio(
						    irr_calificacionRemote.salvarVentaParcialNoCementerio(
						        lalp_lindero, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						    )
						);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);
				}
			}
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			setSalvarVentaParcialNoCementerio(false);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param aedre_re correspondiente al valor del tipo de objeto DashboardReorderEvent
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String handleReorder(DashboardReorderEvent aedre_re)
	    throws B2BException, IOException
	{
		String ls_idWidget;

		List<String> ls_idPanels;
		String ls_change;
		ls_idWidget     = aedre_re.getWidgetId();
		ls_idPanels     = getPanels();
		ls_change       = null;

		if(StringUtils.isValidString(ls_idWidget))
			ls_change = changePosition(ls_idPanels, ls_idWidget, -1);

		try
		{
			if(StringUtils.isValidString(ls_change))
			{
				ls_idWidget     = generateId(ls_idWidget);
				ls_change       = generateId(ls_change);

				irr_calificacionRemote.modifyidAnotacion(ls_idWidget, ls_change, getUserId());
				idb_dataModel = new Dashboard();
				consultaDetalleMatricula(false);

				ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();
				lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
			}
		}
		catch(B2BException lb2b_2b)
		{
			addMessage(lb2b_2b);
		}

		return NavegacionCommon.DETALLE_REGISTRO;
	}

	/**
	 * Intervinientes con secuencia.
	 */
	public void intervinientesConSecuencia()
	{
		try
		{
			TurnoHistoria lth_th;
			lth_th = new TurnoHistoria();
			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lth_th.setIdTurno(getTurno());

			Map<TurnoHistoria, Boolean> lmthb_turnosDerivados;
			lmthb_turnosDerivados = irr_calificacionRemote.consultarTurnosDerivados(lth_th, getUserId());

			if(CollectionUtils.isValidCollection(lmthb_turnosDerivados))
			{
				for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnosDerivados.entrySet())
				{
					TurnoHistoria lth_turnoHistoriaActual;

					lth_turnoHistoriaActual = lmthb_iterador.getKey();

					if(lth_turnoHistoriaActual != null)
					{
						RegistroCalificacion locrc_rc;
						locrc_rc = new RegistroCalificacion();

						String ls_idTurnoHistoria;
						String ls_idTurno;

						ls_idTurnoHistoria     = StringUtils.getString(lth_turnoHistoriaActual.getIdTurnoHistoria());
						ls_idTurno             = lth_turnoHistoriaActual.getIdTurno();

						if(StringUtils.isValidString(ls_idTurnoHistoria))
							locrc_rc = irr_calificacionRemote.findMatriculas(
								    null, ls_idTurnoHistoria, ls_idTurno, getUserId(), getRemoteIpAddress(), true,
								    EtapaCommon.ID_ETAPA_CALIFICACION
								);

						if(locrc_rc != null)
						{
							Collection<RegistroCalificacion> lcrc_rc;
							lcrc_rc = locrc_rc.getAllMatriculas();

							if(CollectionUtils.isValidCollection(lcrc_rc))
							{
								boolean lb_esSecuencia;
								lb_esSecuencia = false;

								for(RegistroCalificacion lorc_rc : lcrc_rc)
								{
									String[] lsa_matricula;
									Long     lli_idMatricula;

									lsa_matricula       = lorc_rc.getIdCirculo().split("-");
									lli_idMatricula     = null;

									if(lsa_matricula.length > 1)
										lli_idMatricula = NumericUtils.getLongWrapper(lsa_matricula[1]);

									if(NumericUtils.isValidLong(lli_idMatricula))
									{
										Collection<String> lc_tipoDocumentos;
										lc_tipoDocumentos = irr_calificacionRemote.getTipoDocPersonaByMatricula(
											    lli_idMatricula, ls_idTurnoHistoria
											);

										if(CollectionUtils.isValidCollection(lc_tipoDocumentos))
										{
											for(String ls_tmp : lc_tipoDocumentos)
											{
												if((ls_tmp != null) && ls_tmp.equalsIgnoreCase(EstadoCommon.SE))
												{
													lb_esSecuencia = true;

													break;
												}
											}
										}

										setHayIntervinienteConSecuencia(lb_esSecuencia);
									}
								}

								if(isHayIntervinienteConSecuencia())
								{
									addMessage(MessagesKeys.EXISTEN_INTERVINIENTES_CON_SECUENCIA);

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

									if(lb_esSecuencia)
										break;
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpia los campos del formulario de interviniente tercero.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void limpiarInteresados()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		setIdPersonaSeleccion(null);
		setIdDirResSeleccion(null);
		setIdDirCorrSeleccion(null);
		setIdTelFijoSeleccion(null);
		setIdTelMovSeleccion(null);
		setIdCorreoSeleccion(null);
		setPersona(null);
		lbd_beanDireccion.setDireccionResidencia(null);
		setDireccionResidencia(null);
		lbd_beanDireccion.setDireccionCorrespondencia(null);
		setDireccionCorrespondencia(null);
		setTelefonoFijo(null);
		setTelefonoMovil(null);
		setCorreoElectronico(null);
		lbd_beanDireccion.setMismaDireccionCorrespondencia(null);
		setMismaDireccionCorrespondencia(null);
		setRendered(false);
		setDeshabilitarDatosBasicos(false);
		setDeshabilitarCamposPorNit(false);
		lbd_beanDireccion.setDeshabilitarResidencia(false);
		setDeshabilitarResidencia(false);
		lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
		setDeshabilitarCorrespondencia(false);
		setDeshabilitarTelFijo(false);
		setDeshabilitarTelMovil(false);
		setDeshabilitarCorreo(false);
		setRegistroDatosConsultados(null);
	}

	/**
	 * Limpiar lindero.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void limpiarLindero()
	    throws B2BException
	{
		LinderoRegistroCalificacion lcc_complementacionCalificacion;
		lcc_complementacionCalificacion = getLinderoRegistroCalificacion();

		if(lcc_complementacionCalificacion != null)
		{
			lcc_complementacionCalificacion.setLindero(null);
			lcc_complementacionCalificacion.setIdMatricula(null);
			lcc_complementacionCalificacion.setIdCirculo(null);
			lcc_complementacionCalificacion.setObservaciones(null);
		}
	}

	/**
	 * Limpiar tabs reloteo.
	 */
	public void limpiarTabsReloteo()
	{
		setBtnTerminarLoteo(false);
		setBtnRegresarLoteo(false);

		Wizard lw_wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
				                                   .findComponent(
				    "calificacionReloteoForm:idTVRegistroCalificacionReloteo"
				);

		lw_wizard.setStep("idTAnotacionesAHeredar");
		PrimeFaces.current().ajax().update("calificacionReloteoForm");
	}

	/**
	 * Llenar campos informacion notificacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void llenarCamposInformacionNotificacion()
	    throws B2BException
	{
		if(!isHabilitarMedidaCautelar())
		{
			boolean          lb_correspondencia;
			boolean          lb_residencia;
			Registro         lr_registro;
			PersonaDireccion lpd_personaDireccion;

			lb_correspondencia     = false;
			lb_residencia          = false;
			lr_registro            = irr_calificacionRemote.cargarDireccionNotificacion(getIdTurnoHistoria());

			if(lr_registro != null)
			{
				lpd_personaDireccion = lr_registro.getDireccionCorrespondencia();
				setTelefonoFijo(lr_registro.getTelefonoFijo());
				setTelefonoMovil(lr_registro.getTelefonoMovil());
				setCorreoElectronico(lr_registro.getPersonaCorreoElectronico());
				findIndicativoDepartamento();

				if(lpd_personaDireccion != null)
				{
					BeanDireccion lbd_beanDireccion;
					String        ls_tipoDireccion;

					lbd_beanDireccion     = getBeanDireccion();
					ls_tipoDireccion      = lpd_personaDireccion.getTipoDireccion();

					lbd_beanDireccion.limpiarBeanDireccion();
					lbd_beanDireccion.setForm(is_idForm);

					if(StringUtils.isValidString(ls_tipoDireccion))
					{
						lb_correspondencia     = ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.C);
						lb_residencia          = ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.R);

						if(lb_correspondencia)
							lbd_beanDireccion.setDireccionCorrespondencia(lpd_personaDireccion);

						if(lb_residencia)
							lbd_beanDireccion.setDireccionResidencia(lpd_personaDireccion);
					}
				}
			}

			setEsNotificacionResidencia(lb_residencia);
			setEsNotificacionCorrespondencia(lb_correspondencia);
			setModificarInformacionNotificacion(false);
		}
	}

	/**
	 * Matriculas A heredar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void matriculasAHeredar()
	    throws B2BException
	{
		RegistroCalificacion locrc_rc;
		RegistroCalificacion locrc_tmp;
		locrc_rc = new RegistroCalificacion();

		try
		{
			locrc_tmp = getMatriculas();

			if((locrc_tmp != null) && CollectionUtils.isValidCollection(locrc_tmp.getAllMatriculas()))
			{
				if(getPredio() != null)
					locrc_tmp.setTurno(getPredio().get(IdentificadoresCommon.ID_TURNO).toString());

				locrc_tmp.setDevolucion(isDevolucion());
				locrc_rc = irr_calificacionRemote.matriculasAHeredar(locrc_tmp);
			}

			if(locrc_rc != null)
			{
				if(CollectionUtils.isValidCollection(locrc_rc.getAllMatriculas()))
					setMatriculasAHeredar(locrc_rc);
				else if(locrc_rc.isActoSegregacion())
				{
					setMatriculasAHeredar(null);
					addMessage(MessagesKeys.NO_EXISTEN_ANOTACIONES_A_HEREDAR);
					PrimeFaces.current().ajax().update(is_messageIdGrowlRegistro);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			locrc_rc = null;
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param aui_childremPanel correspondiente al valor del tipo de objeto List<UIComponent>
	 * @param as_idPanel correspondiente al valor del tipo de objeto String
	 * @param as_revision correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroCalificacion modificarAnotacion(
	    List<UIComponent> aui_childremPanel, String as_idPanel, String as_revision
	)
	    throws B2BException
	{
		RegistroCalificacion lorc_rc;
		as_idPanel     = generateId(as_idPanel);
		lorc_rc        = new RegistroCalificacion();

		try
		{
			lorc_rc.setIdAnotacionPredio(as_idPanel);
			lorc_rc.setUserId(getUserId());
			lorc_rc.setIdUsuario(getUserId());

			if(StringUtils.isValidString(as_revision))
			{
				lorc_rc.setRevision(true);
				lorc_rc.setRevision(as_revision);
			}
			else
				lorc_rc.setRevision(false);

			lorc_rc = irr_calificacionRemote.saveDetailAnotacion(lorc_rc);

			addMessage(MessagesKeys.REGISTRO_MODIFICADO);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return lorc_rc;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aa_anotacion de aa anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void modificarAnotaciones(Anotacion aa_anotacion)
	    throws B2BException
	{
		modificarAnotaciones(aa_anotacion, true);
	}

	/**
	 * Modificar anotaciones.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ab_desdeBaldios correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void modificarAnotaciones(Anotacion aa_anotacion, boolean ab_desdeBaldios)
	    throws B2BException
	{
		if(aa_anotacion != null)
		{
			if(ab_desdeBaldios)
				super.modificarAnotaciones(aa_anotacion);
			else
			{
				com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio lap_anotacionPredio;
				AnotacionCancelacion                                  lac_anotacionCancelacion;
				Documento                                             ld_documento;
				Collection<Anotacion>                                 lca_intervinientesAgregados;
				Collection<Anotacion>                                 lca_matriculasSegregadas;

				lap_anotacionPredio             = aa_anotacion.getAnotacionPredio();
				lac_anotacionCancelacion        = aa_anotacion.getAnotacionCancelacion();
				ld_documento                    = aa_anotacion.getDocumento();
				lca_intervinientesAgregados     = aa_anotacion.getIntervinientesAgregados();
				lca_matriculasSegregadas        = aa_anotacion.getMatriculasSegregadas();

				if(lap_anotacionPredio != null)
				{
					setNaturalezaJuridicaSeleccionada(lap_anotacionPredio.getIdNaturalezaJuridica());
					setAnotacionPredioDetalle(lap_anotacionPredio);
				}

				if(ld_documento != null)
					setDocumentoDetalle(ld_documento);

				if(lac_anotacionCancelacion != null)
					setAnotacionCancelacion(lac_anotacionCancelacion);

				if(CollectionUtils.isValidCollection(lca_intervinientesAgregados))
					setIntervinientesAgregados(lca_intervinientesAgregados);

				if(CollectionUtils.isValidCollection(lca_matriculasSegregadas))
					setMatriculasSegregadas(lca_matriculasSegregadas);

				setIdAnotacionGeneral(aa_anotacion.getIdAnotacion());
			}
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aadap_detalle de aadap detalle
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
	 * Modificar area terreno.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void modificarAreaTerreno(boolean ab_accion)
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
					String       ls_pantalla;

					lb_focus        = false;
					lfc_context     = FacesContext.getCurrentInstance();
					ls_area         = ladap_detalle.getAreaLectura();
					ls_pantalla     = ab_accion ? ":predialForm:" : ":fRegistroCalif:";
					lb_focus        = validateStyles(
						    ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_area, lb_focus
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
								    ls_pantalla + "idITareaTerrenoTabs", lfc_context, ls_area, lb_focus
								);
							ladap_detalle.setArea(ld_area);
						}
						else
						{
							lb_focus = validateStyles(ls_pantalla + "idITareaTerrenoTabs", lfc_context, "", lb_focus);
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}

						lb_focus = validateStyles(
							    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, ls_medidaArea, lb_focus
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
												    ls_pantalla + "idSOMunidadMedidaTerrenoTabs", lfc_context, "",
												    lb_focus
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Modificar area terreno venta.
	 */
	public void modificarAreaTerrenoVenta()
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
						    ":fRegistroCalif:idITareaTerrenoVenta", lfc_context, ls_area, lb_focus
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
								    ":fRegistroCalif:idITareaTerrenoVenta", lfc_context, ls_area, lb_focus
								);
							ladap_detalle.setArea(ld_area);
						}
						else
						{
							lb_focus = validateStyles(
								    ":fRegistroCalif:idITareaTerrenoVenta", lfc_context, "", lb_focus
								);
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
						}

						lb_focus = validateStyles(
							    ":fRegistroCalif:idSOMunidadMedidaTerrenoVenta", lfc_context, ls_medidaArea, lb_focus
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
												    ":fRegistroCalif:idSOMunidadMedidaTerrenoVenta", lfc_context, "",
												    lb_focus
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
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Modificar información Notificación panel.
	 *
	 */
	public void modificarInformacionNotificacionPanel()
	{
		try
		{
			setModificarInformacionNotificacion(true);

			BeanDireccion lbd_beanDireccion;

			lbd_beanDireccion = getBeanDireccion();

			lbd_beanDireccion.setHabilitadoPorConsulta(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param aa_anotacion de aa anotacion
	 */
	public void modificarInterviniente(Anotacion aa_anotacion)
	{
		if(aa_anotacion != null)
		{
			setIntervinienteActual(aa_anotacion);

			Persona                  lp_persona;
			AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
			SolicitudInterviniente   lsi_solicitudInterviniente;

			if(aa_anotacion != null)
			{
				lp_persona                        = aa_anotacion.getPersona();
				lapc_anotacionPredioCiudadano     = aa_anotacion.getAnotacionPredioCiudadano();
				lsi_solicitudInterviniente        = aa_anotacion.getSolicitudInterviniente();

				setEditarInterviniente(true);

				if(lp_persona != null)
					setPersona(lp_persona);

				if(lapc_anotacionPredioCiudadano != null)
					setAnotacionPredioCiudadano(lapc_anotacionPredioCiudadano);

				if(lsi_solicitudInterviniente != null)
					setSolicitudInterviniente(lsi_solicitudInterviniente);

				setIdAnotacion(aa_anotacion.getIdAnotacion());
				setBloquearModificarIntervenientes(true);
			}
		}
	}

	/**
	 * Modificar matricula.
	 *
	 * @param ams_ms correspondiente al valor del tipo de objeto MatriculaSegregacionUi
	 */
	public void modificarMatricula(MatriculaSegregacionUi ams_ms)
	{
		if(ams_ms != null)
		{
			setInfoSegregacionManual(ams_ms);
			setModificarAreaSegregacion(true);
		}
	}

	/**
	 * Modificar matricula segregacion.
	 */
	public void modificarMatriculaSegregacion()
	{
		try
		{
			Collection<MatriculaSegregacionUi> lcmsui_tmp;
			Collection<MatriculaSegregacionUi> lcmsui_tmpUi;
			MatriculaSegregacionUi             lmsui_msui;
			FacesContext                       lfc_context;
			boolean                            lb_focus;
			lfc_context     = FacesContext.getCurrentInstance();

			lcmsui_tmp       = getDataInfoPredial();
			lmsui_msui       = getInfoSegregacionManual();
			lcmsui_tmpUi     = new ArrayList<MatriculaSegregacionUi>();
			lb_focus         = true;

			if((lmsui_msui != null) && CollectionUtils.isValidCollection(lcmsui_tmp))
			{
				for(MatriculaSegregacionUi lmsui_tmp : lcmsui_tmp)
				{
					if(lmsui_tmp != null)
					{
						if(!lmsui_msui.getCantidad().equals(lmsui_tmp.getCantidad()))
							lcmsui_tmpUi.add(lmsui_tmp);
						else
						{
							if(!NumericUtils.isValidLong(lmsui_tmp.getUnidad()))
								throw new B2BException(ErrorKeys.ERROR_UNIDAD_MEDIDA);

							{
								String ls_idNombrePredio;
								String ls_nombrePredio;

								ls_idNombrePredio     = "calificacionReloteoForm:idITNombreareaSegregacionManual";
								ls_nombrePredio       = lmsui_tmp.getNombrePredio();

								if(!StringUtils.isValidString(ls_nombrePredio))
								{
									lb_focus = validateStyles(
										    ls_idNombrePredio, lfc_context, ls_nombrePredio, lb_focus
										);

									PrimeFaces.current().ajax().update(ls_idNombrePredio);

									throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);
								}
							}

							{
								String ls_areaTerrenoStr;
								String ls_idCampoAreaTerreno;

								ls_idCampoAreaTerreno     = "calificacionReloteoForm:idITareaTerrenoCompletaSegregacion";
								ls_areaTerrenoStr         = lmsui_tmp.getAreaTerrenoStr();

								if(StringUtils.isValidString(ls_areaTerrenoStr))
								{
									Double ld_area;
									ld_area = NumericUtils.getDoubleWrapper(ls_areaTerrenoStr);

									if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
									{
										lb_focus = validateStyles(
											    ls_idCampoAreaTerreno, lfc_context, ls_areaTerrenoStr, lb_focus
											);
										lmsui_tmp.setAreaTerreno(ld_area);
										lmsui_tmp.setAreaTerrenoStr(ls_areaTerrenoStr);
									}
									else
									{
										ls_areaTerrenoStr     = null;
										lb_focus              = validateStyles(
											    ls_idCampoAreaTerreno, lfc_context, ls_areaTerrenoStr, lb_focus
											);

										PrimeFaces.current().ajax().update(ls_idCampoAreaTerreno);
										throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO);
							}

							lmsui_tmp.setAreaConstruida(
							    NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE)
							);
							lmsui_tmp.setAreaConstruidaStr("0.0");
							lmsui_tmp.setAreaPrivada(NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE));
							lmsui_tmp.setAreaPrivadaStr("0.0");
							lmsui_tmp.setCoeficiente(NumericUtils.getDoubleWrapper(NumericUtils.DEFAULT_DOUBLE_VALUE));
						}
					}
				}

				lcmsui_tmpUi.add(lmsui_msui);

				setDataInfoPredial(lcmsui_tmpUi);
				setInfoSegregacionManual(null);
				setModificarAreaSegregacion(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Mostrar agregar anotacion.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void mostrarAgregarAnotacion()
	    throws B2BException
	{
		setBloquearModificarIntervenientes(false);
		setPrimerSelected(false);
		setAnotacionPredioDetalle(null);
		setAnotacionCancelacion(null);
		setDocumentoDetalle(null);
		setPersonaConsulta(null);
		setPersona(null);
		setIntervinientesAgregados(null);
		setAnotacionesAgregadas(null);
		setNaturalezaJuridicaSeleccionada(null);
		setCodigoNaturalezaJuridicaSeleccionada(null);
		setAnotacionAgregada(false);

		Date ld_fechaActual;
		ld_fechaActual = new Date();

		try
		{
			String               ls_idTurnoHistoria;
			RegistroCalificacion lrc_registroCalificacion;

			ls_idTurnoHistoria           = getIdTurnoHistoria();
			lrc_registroCalificacion     = new RegistroCalificacion();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
			{
				Long ll_idTurnosHistoria;

				ll_idTurnosHistoria = NumericUtils.getLongWrapper(ls_idTurnoHistoria);

				if(NumericUtils.isValidLong(ll_idTurnosHistoria))
				{
					lrc_registroCalificacion.setIdTurnoHistoria(ll_idTurnosHistoria);

					String ls_matriculaCompleta;

					ls_matriculaCompleta = getCirculo();

					if(StringUtils.isValidString(ls_matriculaCompleta))
					{
						String[] las_matricula;
						String   ls_idCirculo;
						String   ls_idMatricula;

						las_matricula      = ls_matriculaCompleta.split("-");
						ls_idCirculo       = las_matricula[0];
						ls_idMatricula     = las_matricula[1];

						if(StringUtils.isValidString(ls_idMatricula) && StringUtils.isValidString(ls_idCirculo))
						{
							Long ll_idMatricula;

							ll_idMatricula = NumericUtils.getLongWrapper(ls_idMatricula);

							if(NumericUtils.isValidLong(ll_idMatricula))
								lrc_registroCalificacion.setIdMatricula(ll_idMatricula);

							lrc_registroCalificacion.setIdCirculo(ls_idCirculo);
						}
					}

					lrc_registroCalificacion = irr_calificacionRemote.findDocumentoByIdTurnoHistoria(
						    lrc_registroCalificacion, true, false
						);

					if(lrc_registroCalificacion != null)
					{
						Documento ld_documento;
						int       li_totalAnotaciones;

						li_totalAnotaciones = lrc_registroCalificacion.getTotalAnotaciones();

						setCantidadAnotaciones(li_totalAnotaciones + 1);

						ld_documento = lrc_registroCalificacion.getDataDocumento();

						if(ld_documento != null)
							setDocumentoDetalle(ld_documento);

						getAnotacionPredioDetalle().setIdEstadoAnotacion(EstadoCommon.V);

						setAnotacionACancelarNuevo(lrc_registroCalificacion.getAnotacionesCancelacion());
					}

					getAnotacionPredioDetalle().setRadicacion(getTurno());
					getAnotacionPredioDetalle().setFechaRadicacion(ld_fechaActual);
				}
			}

			PrimeFaces.current().executeScript("PF('AddAnotacion').show();");
			PrimeFaces.current().ajax().update("AddAnotacion");
		}
		catch(B2BException lb2b_b2b)
		{
			PrimeFaces.current().executeScript("PF('AddAnotacion').hide();");

			addMessage(lb2b_b2b);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Mostrar copiar anotaciones.
	 */
	public void mostrarCopiarAnotaciones()
	{
		try
		{
			RegistroCalificacion             lorc_tmp;
			Collection<RegistroCalificacion> lcrc_tmp;
			Collection<RegistroCalificacion> lcrc_rc;
			String                           ls_circulo;
			lcrc_rc     = new ArrayList<RegistroCalificacion>();

			lorc_tmp     = irr_calificacionRemote.findMatriculas(
				    null, getIdTurnoHistoria(), getTurno(), getUserId(), getRemoteIpAddress(), false,
				    EtapaCommon.ID_ETAPA_CALIFICACION
				);

			ls_circulo = getCirculo();

			if(lorc_tmp != null)
			{
				if(StringUtils.isValidString(ls_circulo))
				{
					lcrc_tmp = lorc_tmp.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_tmp))
					{
						for(RegistroCalificacion lorc_rc : lcrc_tmp)
						{
							if(
							    (lorc_rc != null)
								    && !StringUtils.getStringNotNull(lorc_rc.getIdCirculo()).equalsIgnoreCase(
								        ls_circulo
								    )
							)
								lcrc_rc.add(lorc_rc);
						}

						setInfoMatriculaACopiar(lcrc_rc);

						PrimeFaces.current().executeScript("PF('copiarMatriculas').show();");

						PrimeFaces.current().ajax().update("copiarMatriculas");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("mostrarCopiarAnotaciones", lb2be_e);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
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
	 * No requiere valor de la cuantia.
	 */
	public void noRequiereValorDeLaCuantia()
	{
		getAnotacionPredioDetalle().setIdNaturalezaJuridica(getIdNaturalezaJuridicaInicial());
		setRequiereCuantiaTipoActo(true);

		try
		{
			throw new B2BException(ErrorKeys.ERROR_EL_ACTO_NO_CUMPLE_CON_EL_MISMO_TIPO_TARIFA);
		}
		catch(B2BException lb2be_e)
		{
			setMostrarAnotacionCancela(true);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Al seleccionar la calidad del interviniente valida si este es apoderado Si es apoderado, oculta el medio a
	 * notificar.
	 */
	public void ocultarMedioNotificaryComunicar()
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			String ls_calidadSolicitante;

			ls_calidadSolicitante = ls_solicitud.getIdCalidadSolicitante();

			if(StringUtils.isValidString(ls_calidadSolicitante))
			{
				ls_solicitud.setIdAutorizacionNotificacion(null);
				ls_solicitud.setIdAutorizacionComunicacion(null);
				setIdDirResSeleccion(null);
				setIdDirCorrSeleccion(null);
				setIdTelFijoSeleccion(null);
				setIdTelMovSeleccion(null);
				setIdCorreoSeleccion(null);

				setSeleccionadoEsApoderado(ls_calidadSolicitante.equalsIgnoreCase("1"));
				setDeshabilitarCorreo(ls_calidadSolicitante.equalsIgnoreCase("1"));
			}

			PrimeFaces.current().ajax().update(is_idForm + ":idPanelMedios");
		}
	}

	/**
	 * Ejecuta el PROCEDIMIENTO PARA PRECALIFICAR anotaciones.
	 */
	public void precalificar()
	{
		try
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
				irr_calificacionRemote.precalificar(
				    ls_idTurnoHistoria, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Proceso terminado.
	 */
	public void procesoTerminado()
	{
		try
		{
			guardarInfoRegistro(false, IdentificadoresCommon.ID_T_DIRECCION_PREDIO_RC);
			setHabilitarGuardarInformacion(true);

			if(isProcesoReloteo())
			{
				setBtnRegresarLoteo(false);
				setBtnSiguienteLoteo(true);
			}

			findMatriculas(false, false);

			PrimeFaces.current().ajax().update("fRegistroCalif");
			PrimeFaces.current().ajax().update("idDlgCalificacionReloteo");
			PrimeFaces.current().executeScript("PF('idDlgCalificacionReloteo').hide();");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param event de event
	 * @return el valor de string
	 */
	public String processFile(FileUploadEvent event)
	{
		try
		{
			String       ls_mensaje = null;
			UploadedFile luf_file;
			luf_file                = event.getFile();

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
				ls_mensaje = MessagesKeys.NO_ENCONTRAR_ARCHIVO_CARGUE_MASIVO;

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = MessagesKeys.PROCESAMINETO_DE_CARGUE_MASIVO_TERMINADO;

			addMessage(ls_mensaje);

			{
				BeanDetalleRegistroCalificacion lbdrc_drc;

				lbdrc_drc = (BeanDetalleRegistroCalificacion)FacesUtils.getFacesContext().getApplication()
						                                                   .evaluateExpressionGet(
						    FacesUtils.getFacesContext(), BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
						    BeanDetalleRegistroCalificacion.class
						);

				lbdrc_drc.setRevisionMatriculas(null);
			}

			PrimeFaces.current().ajax().update("fRegistroCalif:idDtMatriculasInfo");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		{
			Long                 ll_idTurnoHistoria = NumericUtils.getLongWrapper(0L);
			RegistroCalificacion lrc_registro;

			lrc_registro                            = new RegistroCalificacion();

			ll_idTurnoHistoria = NumericUtils.getLongWrapper(getIdTurnoHistoria());

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				lrc_registro.setIdTurnoHistoria(ll_idTurnoHistoria);

			if(getPredio() != null)
				lrc_registro.setTurno(getPredio().get(IdentificadoresCommon.ID_TURNO).toString());

			lrc_registro.setDevolucion(isDevolucion());

			try
			{
				setMatriculasInformacion(
				    irr_calificacionRemote.findMatriculasInformacion(lrc_registro).getMatriculasInformacion()
				);
			}
			catch(B2BException le_e)
			{
				clh_LOGGER.error("processFile", le_e);
			}
		}

		return null;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afue_event correspondiente al valor del tipo de objeto FileUploadEvent
	 * @return devuelve el valor de String
	 */
	public String processFilePredial(FileUploadEvent afue_event)
	{
		try
		{
			Ajax la_ajax;

			la_ajax = PrimeFaces.current().ajax();

			if(afue_event != null)
			{
				String       ls_mensaje   = null;
				UploadedFile luf_file;
				String       ls_solicitud;

				luf_file                  = afue_event.getFile();
				ls_solicitud              = getIdSolicitud();

				if(luf_file != null)
				{
					if(luf_file.getSize() <= 500000)
					{
						setDataInfoPredial(null);

						byte[]       lba_excelFile;
						BeanRegistro lbr_br;
						FacesContext lfc_context;
						lfc_context     = FacesUtils.getFacesContext();

						lbr_br = (BeanRegistro)lfc_context.getApplication()
								                              .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_REGISTRO, BeanRegistro.class
								);
						lbr_br.iniciar();
						lba_excelFile = luf_file.getContents();

						if((lba_excelFile != null) && (luf_file.getFileName() != null) && (lbr_br != null))
							lba_excelFile = lbr_br.FileLoadPredial(
								    lba_excelFile, luf_file.getFileName(), getUserId(), ls_solicitud, null
								);

						if(lba_excelFile != null)
						{
							String ls_nombreArchivo;

							ls_nombreArchivo = luf_file.getFileName();

							if(StringUtils.isValidString(ls_nombreArchivo))
							{
								InputStream stream = new ByteArrayInputStream(lba_excelFile);
								setFilePredial(
								    new DefaultStreamedContent(
								        stream, luf_file.getContentType(), "ARCHIVO_DE_SALIDA.xlsx"
								    )
								);
							}
							else
								setFilePredial(null);
						}
					}
					else
						throw new B2BException("El Archivo de Cargue de Excel no puede exceder los 500 Kb.");
				}
				else
					ls_mensaje = "No se encontro un Archivo de Cargue  para procesar.";

				if(!StringUtils.isValidString(ls_mensaje))
					ls_mensaje = "Procesamiento de Archivo Cargue  terminado. Por favor verifique si hay errores en el archivo de salida";

				addMessage("I", ls_mensaje);

				la_ajax.update("idDtActoAsociadoGeneral");
				la_ajax.update("idDataTablePredioAsociado");
				la_ajax.update("idClnkRespuesta");
				la_ajax.update("idexcelPredial");
				la_ajax.update("idMatriculasAperturar");
				la_ajax.update("idOPLogoExcel");
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return null;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String regresar()
	    throws B2BException
	{
		clean();
		findMatriculas(false, true);

		FacesContext     lfc_context;
		BeanCalificacion lbde_bean;
		lfc_context     = FacesUtils.getFacesContext();
		lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
				                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);

		if(lbde_bean != null)
		{
			lbde_bean.clear();
			lbde_bean.generarDatosTramiteCantidad();
		}

		return NavegacionCommon.CALIFICACION;
	}

	/**
	 * Requiere valor de la cuantia.
	 */
	public void requiereValorDeLaCuantia()
	{
		try
		{
			String ls_idTipoActo;
			ls_idTipoActo = getIdNaturalezaJuridicaInicial();

			if(StringUtils.isValidString(ls_idTipoActo))
			{
				TipoActo lta_parametroInicial;

				lta_parametroInicial = new TipoActo();
				lta_parametroInicial.setIdTipoActo(ls_idTipoActo);

				lta_parametroInicial = irr_registroRemote.findTipoActoById(lta_parametroInicial);

				if(lta_parametroInicial != null)
				{
					String ls_idTipoActoNuevo;
					ls_idTipoActoNuevo = getAnotacionPredioDetalle().getIdNaturalezaJuridica();

					if(StringUtils.isValidString(ls_idTipoActoNuevo))
					{
						TipoActo lta_parametros;

						lta_parametros = new TipoActo();
						lta_parametros.setIdTipoActo(ls_idTipoActoNuevo);

						lta_parametros = irr_registroRemote.findTipoActoById(lta_parametros);

						if(lta_parametros != null)
						{
							if(
							    !lta_parametros.getImpuestoRegistro()
								                   .equalsIgnoreCase(lta_parametroInicial.getImpuestoRegistro())
							)
							{
								getAnotacionPredioDetalle().setIdNaturalezaJuridica(getIdNaturalezaJuridicaInicial());
								throw new B2BException(ErrorKeys.ERROR_EL_ACTO_NO_CUMPLE_CON_EL_REQUISITO_IMPUESTO);
							}
						}
					}

					if(lta_parametroInicial.getActoSinCuantia().equalsIgnoreCase(EstadoCommon.N))
						setRequiereCuantiaTipoActo(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowlDetail);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String retornoBandeja()
	    throws B2BException
	{
		setImagen(null);
		setDetalleMatricula(null);
		findMatriculas(false, true);
		llenarCamposInformacionNotificacion();

		if(!isVieneDeAprobacionSecuencia())
			intervinientesConSecuencia();

		return NavegacionCommon.REGISTRO_CALIFICACION;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnBandeja()
	    throws B2BException
	{
		BeanCalificacion lb_beanCalificacion;
		FacesContext     lfc_context;

		lfc_context             = FacesUtils.getFacesContext();
		lb_beanCalificacion     = (BeanCalificacion)lfc_context.getApplication()
				                                                   .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);

		lb_beanCalificacion.clear();
		lb_beanCalificacion.generarDatosTramiteCantidad();

		return NavegacionCommon.CALIFICACION;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvarAnotacionData()
	{
		String ls_return;

		ls_return = null;

		try
		{
			ls_return = salvarAnotacionData("fDetalleReg", is_messageIdGrowlDetail, true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAnotacionData", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_formulario correspondiente al valor del tipo de objeto String
	 * @param as_growlDetail correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarAnotacionData(String as_formulario, String as_growlDetail)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			ls_return = salvarAnotacionData(as_formulario, as_growlDetail, false);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAnotacionData", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_formulario correspondiente al valor del tipo de objeto String
	 * @param as_growlDetail correspondiente al valor del tipo de objeto String
	 * @param ab_return Variable boolean que valida si debe retornar a alguna pantalla.
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarAnotacionData(String as_formulario, String as_growlDetail, boolean ab_return)
	    throws B2BException
	{
		String ls_return;

		ls_return = ab_return ? NavegacionCommon.DETALLE_REGISTRO : null;

		try
		{
			Anotacion la_anotacion;

			la_anotacion = getAnotacion();

			if(la_anotacion != null)
			{
				AccPredioRegistro lapr_accPredioRegistro;
				Anotacion         la_retorno;
				TurnoHistoria     lth_turnoHistoria;
				String            ls_matriculaCompleta;

				lapr_accPredioRegistro     = getAccPredioRegistro();
				lth_turnoHistoria          = new TurnoHistoria();

				if(lapr_accPredioRegistro == null)
					lapr_accPredioRegistro = new AccPredioRegistro();

				ls_matriculaCompleta = getCirculo();

				if(StringUtils.isValidString(ls_matriculaCompleta))
				{
					String[] ls_matricula;
					String   ls_idCirculo;
					String   ls_idMatricula;

					ls_matricula     = ls_matriculaCompleta.split("-");

					ls_idCirculo       = ls_matricula[0];
					ls_idMatricula     = ls_matricula[1];

					if(StringUtils.isValidString(ls_idMatricula) && StringUtils.isValidString(ls_idCirculo))
					{
						Long ll_idMatricula;

						ll_idMatricula = NumericUtils.getLongWrapper(ls_idMatricula);

						lapr_accPredioRegistro.setIdMatricula(ll_idMatricula);
						lapr_accPredioRegistro.setIdCirculo(ls_idCirculo);
					}
				}

				la_anotacion.setAccPredioRegistro(lapr_accPredioRegistro);
				la_anotacion.setAnotacionesAgregadas(getAnotacionesAgregadas());

				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				la_anotacion.setTurnoHistoria(lth_turnoHistoria);

				la_retorno = irr_calificacionRemote.salvarAnotaciones(
					    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(la_retorno != null)
				{
					if(!StringUtils.isValidString(getIdCirculo()))
						setIdCirculo(
						    lapr_accPredioRegistro.getIdCirculo() + IdentificadoresCommon.SIMBOLO_GUION
						    + lapr_accPredioRegistro.getIdMatricula()
						);

					if(!StringUtils.isValidString(getIdTurno()))
						setIdTurno(lapr_accPredioRegistro.getIdTurno());

					setContadorInterviniente(-1);
					setContadorAnotaciones(-1);

					PrimeFaces.current().executeScript("PF('AddAnotacion').hide();");
					PrimeFaces.current().executeScript("PF('dlg2').hide();");

					idb_dataModel = new Dashboard();
					consultaDetalleMatricula(false, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_return = null;
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(as_growlDetail);
		}
		finally
		{
			PrimeFaces lpf_faces;

			lpf_faces = PrimeFaces.current();

			lpf_faces.ajax().update(as_growlDetail);
			lpf_faces.ajax().update(as_formulario);
		}

		return ls_return;
	}

	/**
	 * Salvar anotaciones englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarAnotacionesEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_dataAnotacionesHeredar;

		lrc_dataAnotacionesHeredar = getMatriculasAHeredar();

		if(lrc_dataAnotacionesHeredar != null)
		{
			boolean                          lb_tmp;
			Collection<RegistroCalificacion> lcrc_anotacionesHeredar;
			RegistroCalificacion             lrc_arg;

			lb_tmp                      = false;
			lcrc_anotacionesHeredar     = lrc_dataAnotacionesHeredar.getAllMatriculas();
			lrc_arg                     = new RegistroCalificacion();

			if(CollectionUtils.isValidCollection(lcrc_anotacionesHeredar))
			{
				Iterator<RegistroCalificacion> lirc_iterator;

				lirc_iterator = lcrc_anotacionesHeredar.iterator();

				while(lirc_iterator.hasNext() && !lb_tmp)
				{
					RegistroCalificacion lorc_rc;

					lorc_rc = lirc_iterator.next();

					if(lorc_rc != null)
					{
						if(lorc_rc.isRevision())
							lb_tmp = true;
					}
				}

				if(lb_tmp)
				{
					lrc_arg.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
					lrc_arg.setUserId(getUserId());
					lrc_arg.setIpAddress(getRemoteIpAddress());
					lrc_arg.setMatriculasAHeredar(lrc_dataAnotacionesHeredar);

					irr_calificacionRemote.salvarAnotacionesEnglobes(lrc_arg);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION);
			}
		}
	}

	/**
	 * Guarda en base de datos la información agregada para el interviniente tercero.
	 */
	public void salvarInteresado()
	{
		String ls_idPanelAToglear;

		ls_idPanelAToglear = null;

		try
		{
			BeanDireccion            lbd_beanDireccion;
			Registro                 lr_registro;
			FacesContext             ifc_context;
			boolean                  lb_focus;
			Solicitud                ls_solicitud;
			Persona                  lp_persona;
			PersonaDireccion         lpd_personaDireccionCorrespondencia;
			PersonaDireccion         lpd_personaDireccionResidencia;
			PersonaTelefono          lpt_personaTelefonoFijo;
			PersonaTelefono          lpt_personaTelefonoMovil;
			PersonaCorreoElectronico lpce_correoElectronico;
			SolicitudInterviniente   ls_solicitudInter;

			ls_solicitudInter                       = getSolicitudInter();
			lbd_beanDireccion                       = getBeanDireccion();
			ifc_context                             = FacesContext.getCurrentInstance();
			lb_focus                                = true;
			lr_registro                             = new Registro();
			ls_solicitud                            = getSolicitud();
			lp_persona                              = getPersona();
			lpd_personaDireccionCorrespondencia     = lbd_beanDireccion.getDireccionCorrespondencia();
			lpd_personaDireccionResidencia          = lbd_beanDireccion.getDireccionResidencia();
			lpt_personaTelefonoFijo                 = getTelefonoFijo();
			lpt_personaTelefonoMovil                = getTelefonoMovil();
			lpce_correoElectronico                  = getCorreoElectronicoPersona();

			// Validacion panel datos basicos
			if(lp_persona != null)
			{
				{
					String ls_datoParaValidar;

					ls_datoParaValidar     = ls_solicitudInter.getRolPersona();
					lb_focus               = validateStyles(
						    is_idForm2 + "idOlRolInterv", ifc_context, ls_datoParaValidar, lb_focus
						);

					if(!lb_focus)
					{
						ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
					}
				}

				if(!isDeshabilitarDatosBasicos())
				{
					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getIdTipoPersona();

						lb_focus = validateStyles(
							    is_idForm2 + "idSOMTipoPersona", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getIdDocumentoTipo();

						lb_focus = validateStyles(
							    is_idForm2 + "idSOMTipoDoc", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_DOCUMENTO_TIPO);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getNumeroDocumento();

						lb_focus = validateStyles(
							    is_idForm2 + "idOlDocumento", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.ERROR_ATTR_USUARIO_E2);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getIdPais();

						lb_focus = validateStyles(
							    is_idForm2 + "idSOMNacionalidad", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getPrimerNombre();

						lb_focus = validateStyles(
							    is_idForm2 + "idOlPNombre", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getPrimerApellido();

						lb_focus = validateStyles(
							    is_idForm2 + "idOlPApellido", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
						}
					}

					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getIdNaturalGenero();

						lb_focus = validateStyles(
							    is_idForm2 + "idSOMGenero", ifc_context, ls_datoParaValidar, lb_focus
							);

						if(!lb_focus)
						{
							ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
						}
					}

					{
						if(isDeshabilitarCamposPorNit())
						{
							String ls_datoParaValidar;

							ls_datoParaValidar     = lp_persona.getRazonSocial();

							lb_focus = validateStyles(
								    is_idForm2 + "idOlRazonSocial", ifc_context, ls_datoParaValidar, lb_focus
								);

							if(!lb_focus)
							{
								ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
							}
						}
					}
				}

				if(ls_solicitud != null)
				{
					String ls_calidad;

					ls_calidad = ls_solicitud.getIdCalidadSolicitante();

					if(StringUtils.isValidString(ls_calidad))
					{
						if(!ls_calidad.equalsIgnoreCase("1"))
						{
							{
								String ls_datoParaValidar;

								ls_datoParaValidar     = ls_solicitud.getIdAutorizacionNotificacion();

								lb_focus = validateStyles(
									    is_idForm2 + "idsomMedioANotificar", ifc_context, ls_datoParaValidar, lb_focus
									);

								if(!lb_focus)
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_NOTIFICAR);
							}

							{
								String ls_datoParaValidar;

								ls_datoParaValidar     = ls_solicitud.getIdAutorizacionComunicacion();

								lb_focus = validateStyles(
									    is_idForm2 + "idsomMedioACom", ifc_context, ls_datoParaValidar, lb_focus
									);

								if(!lb_focus)
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);
							}
						}
					}
				}
			}

			boolean lb_medioComunicar;
			boolean lb_medioNotificar;
			String  ls_medioAComunicar;
			String  ls_medioANotificar;

			if(ls_solicitud != null)
			{
				ls_medioANotificar     = ls_solicitud.getIdAutorizacionNotificacion();
				ls_medioAComunicar     = ls_solicitud.getIdAutorizacionComunicacion();
			}
			else
			{
				ls_medioANotificar     = null;
				ls_medioAComunicar     = null;
			}

			lb_medioComunicar     = StringUtils.isValidString(ls_medioAComunicar);
			lb_medioNotificar     = StringUtils.isValidString(ls_medioANotificar);

			if(lb_medioNotificar && lb_medioComunicar)
			{
				if(
				    (ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
					    || (lb_medioComunicar
					    && ls_medioAComunicar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)))
					    && !isDeshabilitarResidencia()
				)
					lbd_beanDireccion.validarCamposDireccionResidencia();

				if(
				    (ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
					    || (lb_medioComunicar
					    && ls_medioAComunicar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)))
					    && !isDeshabilitarCorrespondencia()
				)
					lbd_beanDireccion.validarCamposDireccionCorrespondencia(true);
			}
			// Validacion panel datos de contacto
			{
				if(!isDeshabilitarTelFijo())
				{
					if(lpt_personaTelefonoFijo != null)
					{
						{
							String ls_paisFijo;

							ls_paisFijo     = StringUtils.getStringNotNull(lpt_personaTelefonoFijo.getIdPais());

							lb_focus = validateStyles(
								    is_idForm2 + "idSOMPaisTelInteresado", ifc_context, ls_paisFijo, lb_focus
								);

							if(!lb_focus)
							{
								ls_idPanelAToglear = "wvPanelDatosC";
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
							}
						}

						{
							String ls_departamentoFijo;

							ls_departamentoFijo     = StringUtils.getStringNotNull(
								    lpt_personaTelefonoFijo.getIdDepartamento()
								);

							lb_focus = validateStyles(
								    is_idForm2 + "idSOMDepTelInteresado", ifc_context, ls_departamentoFijo, lb_focus
								);

							if(!lb_focus)
							{
								ls_idPanelAToglear = "wvPanelDatosC";
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
							}
						}

						{
							String     ls_idConstante;
							Constantes lc_digitosFijo;

							ls_idConstante     = ConstanteCommon.DIGITOS_TEL_FIJO_COL;
							lc_digitosFijo     = irr_referenceRemote.findConstantesById(ls_idConstante);

							if(lc_digitosFijo == null)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ls_idConstante;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
							}

							BigInteger lbi_temp;
							int        li_digitos;
							String     ls_paisFijo;
							String     ls_telefono;

							lbi_temp        = lc_digitosFijo.getEntero();
							li_digitos      = 0;
							ls_paisFijo     = StringUtils.getStringNotNull(lpt_personaTelefonoFijo.getIdPais());
							ls_telefono     = StringUtils.getStringNotNull(lpt_personaTelefonoFijo.getTelefono());

							if(NumericUtils.isValidBigInteger(lbi_temp))
								li_digitos = lbi_temp.intValue();

							if(
							    ls_paisFijo.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
								    && (ls_telefono.length() != li_digitos)
							)
								lb_focus = validateStyles(
									    is_idForm2 + "idItTelefonoFijoInteresado", ifc_context, "", lb_focus
									);
							else
								lb_focus = validateStyles(
									    is_idForm2 + "idItTelefonoFijoInteresado", ifc_context, ls_telefono, lb_focus
									);

							if(!lb_focus)
							{
								ls_idPanelAToglear = "wvPanelDatosC";
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO);
							}
						}
					}
				}

				if(!isDeshabilitarTelMovil())
				{
					if(lpt_personaTelefonoMovil != null)
					{
						{
							{
								String ls_paisMovil;

								ls_paisMovil = StringUtils.getStringNotNull(lpt_personaTelefonoMovil.getIdPais());
								lpt_personaTelefonoMovil.setIdDepartamento(lpt_personaTelefonoFijo.getIdDepartamento());

								lb_focus = validateStyles(
									    is_idForm2 + "idSOMPaisTelMovInteresado", ifc_context, ls_paisMovil, lb_focus
									);

								if(!lb_focus)
								{
									ls_idPanelAToglear = "wvPanelDatosC";
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
								}
							}

							{
								Constantes lc_digitosFijo;
								lc_digitosFijo = irr_referenceRemote.findConstantesById(
									    ConstanteCommon.DIGITOS_TEL_MOVIL_COL
									);

								if(lc_digitosFijo == null)
									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE);

								BigInteger lbi_temp;
								int        li_digitos;
								String     ls_paisMovil;
								String     ls_telefono;

								lbi_temp         = lc_digitosFijo.getEntero();
								li_digitos       = 0;
								ls_paisMovil     = StringUtils.getStringNotNull(lpt_personaTelefonoMovil.getIdPais());
								ls_telefono      = StringUtils.getStringNotNull(lpt_personaTelefonoMovil.getTelefono());

								if(NumericUtils.isValidBigInteger(lbi_temp))
									li_digitos = lbi_temp.intValue();

								if(
								    ls_paisMovil.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
									    && (ls_telefono.length() != li_digitos)
								)
									lb_focus = validateStyles(
										    is_idForm2 + "idItTelefonoMovilInteresado", ifc_context, "", lb_focus
										);
								else
									lb_focus = validateStyles(
										    is_idForm2 + "idItTelefonoMovilInteresado", ifc_context, ls_telefono,
										    lb_focus
										);

								if(!lb_focus)
								{
									ls_idPanelAToglear = "wvPanelDatosC";
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL);
								}
							}

							if(!isDeshabilitarCorreo())
							{
								if(!isSeleccionadoEsApoderado())
								{
									if(lpce_correoElectronico != null)
									{
										String is_datoParaValidar;

										is_datoParaValidar     = lpce_correoElectronico.getCorreoElectronico();

										lb_focus = validateStyles(
											    is_idForm2 + "idItEmailInteresado", ifc_context, is_datoParaValidar,
											    lb_focus
											);

										if(
										    !ExpresionRegular.getExpresionRegular()
											                     .esCorreoElectronico(is_datoParaValidar)
										)
										{
											lb_focus               = validateStyles(
												    is_idForm2 + "idItEmailInteresado", ifc_context, "", lb_focus
												);
											ls_idPanelAToglear     = "wvPanelDatosC";

											throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
										}
									}
								}
							}
						}
					}
				}

				ls_solicitudInter.setIdAutorizacionComunicacion(ls_solicitud.getIdAutorizacionComunicacion());
				ls_solicitudInter.setIdAutorizacionNotificacion(ls_solicitud.getIdAutorizacionNotificacion());

				lr_registro.setPersona(lp_persona);
				lr_registro.setDireccionCorrespondencia(lpd_personaDireccionCorrespondencia);
				lr_registro.setDireccionResidencia(lpd_personaDireccionResidencia);
				lr_registro.setTelefonoFijo(lpt_personaTelefonoFijo);
				lr_registro.setTelefonoMovil(lpt_personaTelefonoMovil);
				lr_registro.setPersonaCorreoElectronico(lpce_correoElectronico);
				lr_registro.setSolicitud(ls_solicitud);
				lr_registro.setSolicitudInterviniente(getSolicitudInterviniente());
				lr_registro.setIdTurno(getIdTurno());
				lr_registro.setSeleccionadoEsApoderado(isSeleccionadoEsApoderado());
				lr_registro.setSolicitudInterviniente(ls_solicitudInter);
				lr_registro.setCalificacion(true);

				lr_registro = irr_registroRemote.salvarInterviniente(
					    lr_registro, getUserId(), false, getLocalIpAddress(), getRemoteIpAddress(), true
					);

				if(lr_registro != null)
				{
					setPersonaNotaExcesoPago(lr_registro.getPersona());
					lbd_beanDireccion.setDireccionResidencia(lr_registro.getDireccionResidencia());
					lbd_beanDireccion.setDireccionCorrespondencia(lr_registro.getDireccionCorrespondencia());
					setTelefonoFijo(lr_registro.getTelefonoFijo());
					setTelefonoMovil(lr_registro.getTelefonoMovil());
					setCorreoElectronico(lr_registro.getPersonaCorreoElectronico());
					setSolicitudInterviniente(lr_registro.getSolicitudInterviniente());
				}
			}

			setPersonaExcesoGuardado(true);
		}
		catch(B2BException lb2be_e)
		{
			expandirToggeable(ls_idPanelAToglear);
			setPersonaNotaExcesoPago(null);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Seccion matriculas segregacion.
	 */
	public void seccionMatriculasSegregacion()
	{
		String ls_respuestaSegregacion;

		ls_respuestaSegregacion = getPreguntaSegregacion();

		if(StringUtils.isValidString(ls_respuestaSegregacion))
		{
			if(ls_respuestaSegregacion.equalsIgnoreCase(EstadoCommon.S))
			{
				setCargueMatriculasAperturar(true);
				setManualMatriculasAperturar(false);
			}

			else
			{
				setModificarAreaSegregacion(false);
				setCargueMatriculasAperturar(false);
				setManualMatriculasAperturar(true);
			}
		}
	}

	/**
	 * Validar area abrir.
	 *
	 * @param arc_registroCalificacion correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void validarAreaAbrir(RegistroCalificacion arc_registroCalificacion)
	{
		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		try
		{
			if(arc_registroCalificacion != null)
			{
				String ls_matriculaCompleta;

				ls_matriculaCompleta = arc_registroCalificacion.getIdCirculo();

				if(StringUtils.isValidString(ls_matriculaCompleta))
				{
					int    li_inicio;
					String ls_idCirculo;
					String ls_idMatricula;

					li_inicio          = ls_matriculaCompleta.lastIndexOf("-");
					ls_idCirculo       = ls_matriculaCompleta.substring(0, li_inicio);
					ls_idMatricula     = ls_matriculaCompleta.substring(li_inicio + 1, ls_matriculaCompleta.length());

					if(StringUtils.isValidString(ls_idCirculo) && StringUtils.isValidString(ls_idMatricula))
					{
						AccAreaPredio laap_areaPredio;
						AccAreaPredio laap_areaPredioArg;
						AccAreaUI     laaui_data;
						boolean       lb_salvarArea;
						Long          ll_idMatricula;

						laap_areaPredioArg     = new AccAreaPredio();
						lb_salvarArea          = false;
						ll_idMatricula         = NumericUtils.getLongWrapper(ls_idMatricula);

						laap_areaPredioArg.setIdCirculo(ls_idCirculo);
						laap_areaPredioArg.setIdMatricula(ll_idMatricula);
						laap_areaPredioArg.setIdTurno(getTurno());

						laaui_data = irr_registroRemote.consultaAreaPredio(laap_areaPredioArg, getUserId(), false);

						if((laaui_data != null) && (laaui_data.isConsulta()))
						{
							if(laaui_data.getAreaPredio() != null)
							{
								laap_areaPredio = laaui_data.getAreaPredio();

								laap_areaPredio.setIdCirculo(ls_idCirculo);
								laap_areaPredio.setIdMatricula(ll_idMatricula);
							}
							else
							{
								laaui_data          = new AccAreaUI();
								laap_areaPredio     = new AccAreaPredio();

								laap_areaPredio.setIdCirculo(ls_idCirculo);
								laap_areaPredio.setIdMatricula(ll_idMatricula);
								laaui_data.setAreaPredio(laap_areaPredio);
							}
						}
						else
						{
							laaui_data = irr_registroRemote.consultaAreaPredio(laap_areaPredioArg, getUserId(), true);

							if(
							    (laaui_data != null) && (laaui_data.getAreaPredio() != null)
								    && (laaui_data.isConsulta())
							)
							{
								PredioRegistro lpr_predioRegistro;

								laap_areaPredio        = laaui_data.getAreaPredio();
								lpr_predioRegistro     = new PredioRegistro();

								lpr_predioRegistro.setIdCirculo(ls_idCirculo);
								lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
								lpr_predioRegistro.setValidMatricula(true);

								lpr_predioRegistro = irr_registroRemote.findPredioRegistroById(
									    lpr_predioRegistro, getUserId()
									);

								if(lpr_predioRegistro != null)
									laap_areaPredio.setTipoSuelo(lpr_predioRegistro.getIdTipoUsoSuelo());

								lb_salvarArea = true;
							}
							else
							{
								laap_areaPredioArg.setIdTurno(null);

								laaui_data = irr_registroRemote.consultaAreaPredio(
									    laap_areaPredioArg, getUserId(), false
									);

								if((laaui_data != null) && (laaui_data.isConsulta()))
								{
									if(laaui_data.getAreaPredio() != null)
									{
										laap_areaPredio = laaui_data.getAreaPredio();

										laap_areaPredio.setIdCirculo(ls_idCirculo);
										laap_areaPredio.setIdMatricula(ll_idMatricula);
									}
									else
									{
										laaui_data          = new AccAreaUI();
										laap_areaPredio     = new AccAreaPredio();

										laap_areaPredio.setIdCirculo(ls_idCirculo);
										laap_areaPredio.setIdMatricula(ll_idMatricula);
										laaui_data.setAreaPredio(laap_areaPredio);
									}
								}
								else
								{
									laaui_data          = new AccAreaUI();
									laap_areaPredio     = new AccAreaPredio();

									laaui_data.setAreaPredio(laap_areaPredio);
								}
							}

							setSalvarArea(lb_salvarArea);
							laap_areaPredio.setIdCirculo(ls_idCirculo);
							laap_areaPredio.setIdMatricula(ll_idMatricula);
						}

						if(laaui_data != null)
						{
							laaui_data.setIdCirculo(ls_idCirculo);
							laaui_data.setIdMatricula(ll_idMatricula);

							AccAreaPredio     laap_area;
							DetalleAreaPredio ldap_privada;
							DetalleAreaPredio ldap_construida;

							laap_area           = laaui_data.getAreaPredio();
							ldap_privada        = laaui_data.getDetalleAreaPrivada();
							ldap_construida     = laaui_data.getDetalleAreaConstruida();

							if(ldap_privada != null)
							{
								Double ld_area;

								ld_area = ldap_privada.getArea();

								if(NumericUtils.isValidDouble(ld_area))
									ldap_privada.setAreaLectura(StringUtils.getString(ld_area));
							}

							if(ldap_construida != null)
							{
								Double ld_area;

								ld_area = ldap_construida.getArea();

								if(NumericUtils.isValidDouble(ld_area))
									ldap_construida.setAreaLectura(StringUtils.getString(ld_area));
							}

							if(laap_area != null)
							{
								Double ld_coeficiente;

								ld_coeficiente = laap_area.getCoeficiente();

								if(NumericUtils.isValidDouble(ld_coeficiente))
									laap_area.setCoeficienteLectura(StringUtils.getString(ld_coeficiente));
							}
						}

						if(isProcesoVentaParcial())
						{
							LinderoRegistroCalificacion llrc_lindero;

							llrc_lindero = new LinderoRegistroCalificacion();

							llrc_lindero.setIdCirculo(ls_idCirculo);
							llrc_lindero.setIdMatricula(ll_idMatricula);

							laaui_data.setLinderos(irr_calificacionRemote.findLindero(llrc_lindero, false));
						}

						setAreaUI(laaui_data);
						actualizarAreaTerreno();

						PrimeFaces.current().ajax().update("idDlgValidarArea");
						PrimeFaces.current().executeScript("PF('idDlgValidarArea').show();");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Validar area guardar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarAreaGuardar()
	    throws B2BException
	{
		PrimeFaces lp_primefaces;

		lp_primefaces = PrimeFaces.current();

		try
		{
			if(!isBloquearAgregarAreaTerreno())
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

						laap_areaPredio     = laaui_data.getAreaPredio();
						lb_focus            = false;
						lfc_context         = FacesContext.getCurrentInstance();

						if(laap_areaPredio != null)
						{
							String ls_tipoUso;
							String ls_coeficiente;

							ls_tipoUso         = laap_areaPredio.getTipoSuelo();
							ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
							lb_focus           = validateStyles(
								    ":predialForm:idSOMtipoUsoSueloTabs", lfc_context, ls_tipoUso, lb_focus
								);

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

							if(StringUtils.isValidString(ls_coeficiente))
							{
								Double ld_coeficiente;

								ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

								if(NumericUtils.isValidDouble(ld_coeficiente))
								{
									laap_areaPredio.setCoeficiente(ld_coeficiente);

									if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100D)) > 0)
									{
										lb_focus = validateStyles(
											    "predialForm:idITcoeficienteTabs", lfc_context, "", lb_focus
											);

										throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
									}
									else
									{
										laap_areaPredio.setCoeficiente(ld_coeficiente);
										lb_focus = validateStyles(
											    "predialForm:idITcoeficienteTabs", lfc_context, ls_coeficiente, lb_focus
											);
									}
								}
								else
								{
									lb_focus = validateStyles(
										    "predialForm:idITcoeficienteTabs", lfc_context, "", lb_focus
										);
									throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
								}
							}
							else
								lb_focus = validateStyles(
									    "predialForm:idITcoeficienteTabs", lfc_context,
									    IdentificadoresCommon.DATO_VALIDO, lb_focus
									);

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

										ld_area      = NumericUtils.getDoubleWrapper(ls_area);
										lb_focus     = validateStyles(
											    "predialForm:idITareaConstruidaTabs", lfc_context, ls_area, lb_focus
											);

										if(NumericUtils.isValidDouble(ld_area))
											ldap_area.setArea(ld_area);
										else
											throw new B2BException(ErrorKeys.ERROR_AREA_CONSTRUIDA);
									}
									else
										lb_focus = validateStyles(
											    "predialForm:idITareaConstruidaTabs", lfc_context, "data", lb_focus
											);
								}
								else
								{
									lb_focus = validateStyles(
										    "predialForm:idITareaConstruidaTabs", lfc_context, "", lb_focus
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

									ls_area = ldap_area.getAreaLectura();

									if(StringUtils.isValidString(ls_area))
									{
										Double ld_area;

										ld_area      = NumericUtils.getDoubleWrapper(ls_area);
										lb_focus     = validateStyles(
											    "predialForm:idITareaPrivadaTabs", lfc_context, ls_area, lb_focus
											);

										if(NumericUtils.isValidDouble(ld_area))
											ldap_area.setArea(ld_area);
										else
											throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
									else
										lb_focus = validateStyles(
											    "predialForm:idITareaPrivadaTabs", lfc_context, "data", lb_focus
											);
								}
								else
								{
									lb_focus = validateStyles(
										    "predialForm:idITareaPrivadaTabs", lfc_context, "", lb_focus
										);
									throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
								}
							}

							{
								String ls_idCirculo;
								Long   ll_idMatricula;

								ls_idCirculo       = laaui_data.getIdCirculo();
								ll_idMatricula     = laaui_data.getIdMatricula();

								if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
								{
									laaui_data.setIdCirculo(ls_idCirculo);
									laaui_data.setIdMatricula(ll_idMatricula);
									laaui_data.setIdTurno(getTurno());
									laaui_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

									irr_calificacionRemote.salvarAreaPredioEnglobes(
									    laaui_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

									addMessage(MessagesKeys.AREA_MODIFICADA_CON_EXITO);

									lp_primefaces.ajax().update("idDlgValidarArea");
									lp_primefaces.ajax().update("fRegistroCalif");
									lp_primefaces.executeScript("PF('idDlgValidarArea').hide();");

									setAreaUI(null);
									findMatriculas(false, false);
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_AGREGAR);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_AREA_DEL_TERRENO_MODIFICAR);
			}
		}
		catch(B2BException lb2be_e)
		{
			lp_primefaces.executeScript("PF('idDlgValidarArea').show();");
			addMessage(lb2be_e);
		}
		finally
		{
			lp_primefaces.ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Valida si al darle click a la matricula, el predio tiene embargos vigentes
	 * Si true, muestra cuadro dialog
	 * Si false, sigue el proceso normal.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String validarEmbargosVigentes()
	    throws B2BException
	{
		String  ls_circulo;
		int     li_tmp;
		String  ls_idCirculo;
		Long    ll_matricula;
		long    ll_idMatricula;
		boolean lb_returnPage;
		String  ls_return;

		setCirculo(FacesUtils.getStringFacesParameter("idCirculo"));
		setIdCirculo(FacesUtils.getStringFacesParameter("idCirculo"));
		setIdTurno(FacesUtils.getStringFacesParameter("idTurno"));
		setEstadoMatricula(FacesUtils.getStringFacesParameter("idEstadoMatricula"));
		ls_return     = null;

		lb_returnPage     = false;
		ls_circulo        = getIdCirculo();
		li_tmp            = ls_circulo.indexOf("-");
		ls_idCirculo      = ls_circulo.substring(0, li_tmp);

		ll_matricula       = NumericUtils.getLongWrapper(ls_circulo.substring(li_tmp + 1));
		ll_idMatricula     = NumericUtils.getLong(ll_matricula);

		if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
			lb_returnPage = irr_calificacionRemote.validarEmbargosVigentes(
				    ls_idCirculo, ll_idMatricula, getIdSolicitud()
				);

		if(lb_returnPage)
			PrimeFaces.current().executeScript("PF('dlg3').show();");
		else
			ls_return = consultaDetalleMatricula(true, true);

		return ls_return;
	}

	/**
	 * Bloquea los SelectOneMenu de Tipo persona y Tipo documento si se selecciona la opción Juridica o NIT,
	 * respectivamente.
	 *
	 * @param as_seleccion Cadena de caracteres con la elección del usuario en pantalla.
	 */
	public void validarTipoPersonaDocumento(String as_seleccion)
	{
		Persona lp_temp;
		lp_temp = getPersona();

		if((as_seleccion != null) && (lp_temp != null))
		{
			if(
			    as_seleccion.equalsIgnoreCase(EstadoCommon.J)
				    || as_seleccion.equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
			{
				setDeshabilitarCamposPorNit(true);

				lp_temp.setIdNaturalGenero(EstadoCommon.N);

				cargarMediosNotCom(true);

				if(as_seleccion.equalsIgnoreCase(EstadoCommon.J))
				{
					setDeshabilitarTipoDocumento(true);

					lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
				}
				else
				{
					setDeshabilitarTipoPersona(true);

					lp_temp.setIdTipoPersona(EstadoCommon.J);
				}
			}
			else
			{
				setDeshabilitarTipoDocumento(false);
				setDeshabilitarTipoPersona(false);
				setDeshabilitarCamposPorNit(false);

				cargarMediosNotCom(false);

				String ls_tipoPersona;
				String ls_tipoDoc;
				String ls_genero;

				ls_tipoPersona     = StringUtils.getStringNotNull(lp_temp.getIdTipoPersona());
				ls_tipoDoc         = StringUtils.getStringNotNull(lp_temp.getIdDocumentoTipo());
				ls_genero          = StringUtils.getStringNotNull(lp_temp.getIdNaturalGenero());

				if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
				{
					lp_temp.setIdTipoPersona("");

					if(ls_genero.equalsIgnoreCase(EstadoCommon.N))
						lp_temp.setIdNaturalGenero("");
				}
				else if(ls_tipoDoc.equalsIgnoreCase(IdentificadoresCommon.NIT))
				{
					lp_temp.setIdDocumentoTipo("");

					if(ls_genero.equalsIgnoreCase(EstadoCommon.N))
						lp_temp.setIdNaturalGenero("");
				}
			}

			setPersona(lp_temp);
		}
	}

	/**
	 * Bloquea los SelectOneMenu de Tipo persona y Tipo documento para los datos de
	 * los intervinientes si se selecciona la opción Juridica o NIT,
	 * respectivamente.
	 *
	 * @param as_seleccion
	 *            Cadena de caracteres con la elección del usuario en pantalla
	 */
	public void validarTipoPersonaDocumentoInter(String as_seleccion)
	{
		Persona lp_temp;
		lp_temp = getPersona();

		if((as_seleccion != null) && (lp_temp != null))
		{
			if(
			    as_seleccion.equalsIgnoreCase(EstadoCommon.J)
				    || as_seleccion.equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
			{
				setDeshabilitarCamposPorNit(true);
				setDeshabilitarTipoDocumentoInter(true);

				lp_temp.setIdNaturalGenero(EstadoCommon.N);

				if(as_seleccion.equalsIgnoreCase(EstadoCommon.J))
				{
					if(lp_temp.getIdDocumentoTipo() == null)
						lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
					else if(!lp_temp.getIdDocumentoTipo().equalsIgnoreCase(EstadoCommon.SE))
						lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
				}
				else
					lp_temp.setIdTipoPersona(EstadoCommon.J);
			}
			else
			{
				setDeshabilitarTipoDocumentoInter(false);
				setDeshabilitarTipoPersonaInter(false);
				setDeshabilitarCamposPorNit(false);

				String ls_tipoPersona;
				String ls_tipoDoc;
				String ls_genero;

				ls_tipoPersona     = StringUtils.getStringNotNull(lp_temp.getIdTipoPersona());
				ls_tipoDoc         = StringUtils.getStringNotNull(lp_temp.getIdDocumentoTipo());
				ls_genero          = StringUtils.getStringNotNull(lp_temp.getIdNaturalGenero());

				if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
				{
					lp_temp.setIdTipoPersona("");

					if(ls_genero.equalsIgnoreCase(EstadoCommon.N))
						lp_temp.setIdNaturalGenero("");
				}
				else if(ls_tipoDoc.equalsIgnoreCase(IdentificadoresCommon.NIT))
				{
					lp_temp.setIdDocumentoTipo("");

					if(ls_genero.equalsIgnoreCase(EstadoCommon.N))
						lp_temp.setIdNaturalGenero("");
				}
			}

			setPersona(lp_temp);
		}
	}

	/**
	 * Visualizar archivo.
	 * Método sobrecargado para guardar el archivo en base de datos.
	 *
	 * @param ab_salvar de ab salvar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void visualizarArchivo(boolean ab_salvar)
	    throws B2BException
	{
		try
		{
			generateFile(ab_salvar);
			setFileGenerado(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Visualizar archivo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void visualizarArchivo()
	    throws B2BException
	{
		visualizarArchivo(false);
	}

	/**
	 * Visualizar archivo comunicado.
	 *
	 * @param ab_salvar de ab salvar
	 */
	public void visualizarArchivoComunicado(boolean ab_salvar)
	{
		try
		{
			generateFileComunicado(ab_salvar);
			setFileGenerado(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

/**
     * Visualizar archivo comunicado.
     *
     * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
     */
	public void visualizarArchivoComunicado()
	    throws B2BException
	{
		visualizarArchivoComunicado(false);
	}

	/**
	 * Método para generar comunicado de dirección.
	 */
	public void visualizarArchivoComunicadoDireccion()
	{
		try
		{
			byte[] lab_file;

			RegistroCalificacion lorc_rc;
			RegistroCalificacion lorc_tmp;
			Collection<AreaPredio> lcap_ap;
			boolean lb_b;
			Collection<RegistroCalificacion> lcrc_rc;
			Collection<RegistroCalificacion> lcrc_tmp;

			lcap_ap      = getMatriculasInformacion();
			lorc_rc      = getMatriculas();
			lorc_tmp     = new RegistroCalificacion();
			lcrc_tmp     = new ArrayList<RegistroCalificacion>();

			lb_b = CollectionUtils.isValidCollection(lcap_ap);

			if(lorc_rc != null)
			{
				lcrc_rc = lorc_rc.getAllMatriculas();
				lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lorc_rc.setIdMotivo(getMotivo());
				lorc_rc.setUserId(getUserId());

				if(lb_b)
				{
					if(CollectionUtils.isValidCollection(lcrc_rc))
						lcrc_tmp.addAll(lcrc_rc);

					if(!lorc_rc.isMatriculasAdd())
					{
						for(AreaPredio loap_ap : lcap_ap)
						{
							String ls_matricula;

							ls_matricula = loap_ap.getIdCirculo() + "-" + loap_ap.getIdMatricula();

							lorc_tmp.setIdCirculo(ls_matricula);
							lorc_tmp.setIndDummy(true);
							lcrc_tmp.add(lorc_tmp);
							lorc_tmp = new RegistroCalificacion();
						}

						if(CollectionUtils.isValidCollection(lcrc_tmp))
						{
							lorc_rc.setInfoFile(lcrc_tmp);
							lorc_rc.setMatriculasAdd(true);
						}
					}
				}
				else if(CollectionUtils.isValidCollection(lcrc_rc))
					lorc_rc.setInfoFile(lcrc_rc);
			}

			lab_file = irr_calificacionRemote.genereteFileComunicadoDireccion(
				    lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false
				);

			if(lab_file != null)
			{
				InputStream lis_stream;
				String      ls_nombreArchivo;

				lis_stream     = new ByteArrayInputStream(lab_file);

				ls_nombreArchivo = ConstanteCommon.NOMBRE_ARCHIVO_GENERADO;

				setImagenComunicadoDireccion(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.PDF, ls_nombreArchivo + ExtensionCommon.PDF_MAYUS_PUNTO
				    )
				);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);

			setGeneroComunicadoDireccion(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;
		ls_return = NavegacionCommon.CALIFICACION;
		clean();

		FacesContext     lfc_context;
		BeanCalificacion lbde_bean;
		lfc_context     = FacesUtils.getFacesContext();
		lbde_bean       = (BeanCalificacion)lfc_context.getApplication()
				                                           .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
				);

		if(lbde_bean != null)
		{
			try
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
			catch(B2BException le_e)
			{
				clh_LOGGER.error("volver", le_e);
				ls_return = null;
			}
		}

		return ls_return;
	}

	/**
	 * Validaciones campos.
	 *
	 * @param ap_persona correspondiente al valor del tipo de objeto Persona
	 * @throws B2BException Señala que se ha producido una excepción
	 */

	/*
	 * Metodo para validar los campos de la anotacion
	 */
	@SuppressWarnings("deprecation")
	protected void validacionesCampos(Persona ap_persona)
	    throws B2BException
	{
		if(ap_persona != null)
		{
			FacesContext lfc_context;
			boolean      lb_focus;
			String       ls_tipoPersona;
			String       ls_tipoDocumento;
			String       ls_idPanel;
			Anotacion    la_anotacion;

			lfc_context          = FacesContext.getCurrentInstance();
			lb_focus             = true;
			ls_tipoPersona       = ap_persona.getIdTipoPersona();
			ls_tipoDocumento     = ap_persona.getIdDocumentoTipo();
			ls_idPanel           = ":faddAnotacion:idOlRolInter";
			la_anotacion         = new Anotacion();

			lb_focus = validateStyles(":faddAnotacion:idSOMTipoPersonaInter", lfc_context, ls_tipoPersona, lb_focus);

			if(!StringUtils.isValidString(ls_tipoPersona))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

			lb_focus = validateStyles(":faddAnotacion:idSOMTipoDocInter", lfc_context, ls_tipoDocumento, lb_focus);

			if(!StringUtils.isValidString(ls_tipoDocumento))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			{
				String ls_documento;
				ls_documento     = ap_persona.getNumeroDocumento();

				lb_focus = validateStyles(":faddAnotacion:idOlDocumentoInter", lfc_context, ls_documento, lb_focus);

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
						ls_nacionalidad     = ap_persona.getIdPais();

						lb_focus = validateStyles(
							    ":faddAnotacion:idSOMNacionalidadInter", lfc_context, ls_nacionalidad, lb_focus
							);

						if(!StringUtils.isValidString(ls_nacionalidad))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
					}

					{
						String ls_primerNombre;
						ls_primerNombre     = ap_persona.getPrimerNombre();

						lb_focus = validateStyles(
							    ":faddAnotacion:idOlPNombreInter", lfc_context, ls_primerNombre, lb_focus
							);

						if(!StringUtils.isValidString(ls_primerNombre))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
					}

					{
						String ls_primerApellido;
						ls_primerApellido     = ap_persona.getPrimerApellido();

						lb_focus = validateStyles(
							    ":faddAnotacion:idOlPApellidoInter", lfc_context, ls_primerApellido, lb_focus
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

							lb_focus = validateStyles(ls_idPanel, lfc_context, ls_rol, lb_focus);

							if(!StringUtils.isValidString(ls_rol))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
						}
					}

					{
						String ls_genero;
						ls_genero     = ap_persona.getIdNaturalGenero();

						lb_focus = validateStyles(":faddAnotacion:idSOMGeneroInter", lfc_context, ls_genero, lb_focus);

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
					{
						String ls_nacionalidad;
						ls_nacionalidad     = ap_persona.getIdPais();

						lb_focus = validateStyles(
							    ":faddAnotacion:idSOMNacionalidadInter", lfc_context, ls_nacionalidad, lb_focus
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

							lb_focus = validateStyles(ls_idPanel, lfc_context, ls_rol, lb_focus);

							if(!StringUtils.isValidString(ls_rol))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
						}
					}

					{
						String ls_razonSocial;
						ls_razonSocial     = ap_persona.getRazonSocial();

						lb_focus = validateStyles(
							    ":faddAnotacion:idOlRazonSocialInter", lfc_context, ls_razonSocial, lb_focus
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

					lb_focus = validateStyles(ls_idPanel, lfc_context, ls_rol, lb_focus);

					if(!StringUtils.isValidString(ls_rol))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
				}
			}

			{
				long                  ll_idAnotacion;
				long                  li_contador;
				boolean               lb_conAnotacion;
				Collection<Anotacion> lca_datos;

				ll_idAnotacion      = getIdAnotacion();
				li_contador         = getContadorInterviniente();
				lb_conAnotacion     = ll_idAnotacion > 0;
				lca_datos           = getIntervinientesAgregados();

				if(lb_conAnotacion)
					la_anotacion.setIdAnotacion(ll_idAnotacion);
				else
					la_anotacion.setIdAnotacion(++li_contador);

				la_anotacion.setPersona(getPersona());
				la_anotacion.setSolicitudInterviniente(getSolicitudInterviniente());

				Anotacion la_temp;
				la_temp = getIntervinienteActual();

				if(CollectionUtils.isValidCollection(lca_datos))
				{
					lca_datos.remove(la_temp);

					Persona                lp_personaTemp;
					SolicitudInterviniente lsi_solicitudTemp;

					lp_personaTemp        = la_anotacion.getPersona();
					lsi_solicitudTemp     = la_anotacion.getSolicitudInterviniente();

					for(Anotacion la_data : lca_datos)
					{
						if(la_data != null)
						{
							Persona                lp_personaTabla;
							SolicitudInterviniente lsi_solicitudTabla;

							lp_personaTabla        = la_data.getPersona();
							lsi_solicitudTabla     = la_data.getSolicitudInterviniente();

							if(
							    (lp_personaTemp != null) && (lp_personaTabla != null) && (lsi_solicitudTemp != null)
								    && (lsi_solicitudTabla != null)
							)
							{
								String ls_idPersonaTemp;
								String ls_idPersonaTabla;
								String ls_rolTemp;
								String ls_rolTabla;

								ls_idPersonaTemp      = lp_personaTemp.getIdPersona();
								ls_idPersonaTabla     = lp_personaTabla.getIdPersona();
								ls_rolTemp            = lsi_solicitudTemp.getRolPersona();
								ls_rolTabla           = lsi_solicitudTabla.getRolPersona();

								if(
								    StringUtils.isValidString(ls_idPersonaTemp)
									    && StringUtils.isValidString(ls_idPersonaTabla)
									    && StringUtils.isValidString(ls_rolTemp)
									    && StringUtils.isValidString(ls_rolTabla)
								)
								{
									if(ls_idPersonaTemp.equals(ls_idPersonaTabla) && ls_rolTemp.equals(ls_rolTabla))
									{
										Object[] loa_messageArgs = new String[1];
										loa_messageArgs[0] = ls_rolTemp;

										if(la_temp != null)
											lca_datos.add(la_temp);

										throw new B2BException(ErrorKeys.ERROR_INTERVINIENTE_REPETIDO, loa_messageArgs);
									}
								}
							}
						}
					}

					if(la_temp != null)
						lca_datos.add(la_temp);
				}

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

						setIntervinientesAgregados(lca_tmp);
						setIdAnotacion(-1);
					}
				}
				else
				{
					if(!CollectionUtils.isValidCollection(lca_datos))
						lca_datos = new ArrayList<Anotacion>();

					lca_datos.add(la_anotacion);

					setContadorInterviniente(li_contador);
					setIntervinientesAgregados(lca_datos);
				}
			}

			{
				AnotacionPredioCiudadano lapc_anotacionPredioCiudadano;
				lapc_anotacionPredioCiudadano = getAnotacionPredioCiudadano();

				if(lapc_anotacionPredioCiudadano != null)
				{
					String ls_porcentajeParticipacion;
					ls_porcentajeParticipacion = lapc_anotacionPredioCiudadano.getPorcentajeParticipacion();

					if(StringUtils.isValidString(ls_porcentajeParticipacion))
					{
						long ld_porcentaje;
						ld_porcentaje = NumericUtils.getLong(ls_porcentajeParticipacion);

						if((ld_porcentaje < 0.0) || (ld_porcentaje > 100.0))
						{
							RequestContext.getCurrentInstance()
								              .execute("PrimeFaces.focus('faddAnotacion:idOlPorcentajePart');");

							throw new B2BException(ErrorKeys.ERROR_RANGO_PORCENTAJE_INVALIDO);
						}
					}
				}
			}
		}
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
	 * Cargar anotacion proceso.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarAnotacionProceso()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			if(!StringUtils.isValidString(lrc_data.getTurno()))
				lrc_data.setTurno(getTurno());

			setAnotacionesProceso(irr_calificacionRemote.cargarAnotacionProceso(lrc_data));
		}
	}

	/**
	 * Cargar anotaciones englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarAnotacionesEnglobes()
	    throws B2BException
	{
		setMatriculasAHeredar(irr_calificacionRemote.cargarAnotacionesEnglobes(getMatriculas()));
	}

	/**
	 * Retorna el valor del objeto de RegistroCalificacion.
	 *
	 * @param arc_rc correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @return devuelve el valor de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion cargarCierreFolio(RegistroCalificacion arc_rc)
	    throws B2BException
	{
		return irr_calificacionRemote.cargarCierreFolio(arc_rc);
	}

	/**
	 * Cargar datos area englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosAreaEnglobes()
	    throws B2BException
	{
		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		try
		{
			AccAreaUI laui_dataArea;

			laui_dataArea = irr_calificacionRemote.cargarDatosAreaEnglobes(getMatriculas());

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
				}
			}

			setAreaUI(laui_dataArea);
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
		finally
		{
			actualizarAreaTerreno();
		}
	}

	/**
	 * Cargar datos area venta.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosAreaVenta()
	    throws B2BException
	{
		setAreaUI(null);
		setDetalleAreaTerreno(null);
		setBloquearAgregarAreaTerreno(false);

		try
		{
			AccAreaUI laui_dataArea;

			laui_dataArea = irr_calificacionRemote.cargarDatosAreaVenta(getMatriculas());

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
				}
			}

			setAreaUI(laui_dataArea);
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
		finally
		{
			actualizarAreaTerreno();
		}
	}

	/**
	 * Cargar datos basicos englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDatosBasicosEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDatosBasicosEnglobes(getMatriculas());

		if(lrc_data != null)
		{
			setMatriculas(lrc_data);

			Collection<RegistroCalificacion> lcrc_matriculas;

			lcrc_matriculas = lrc_data.getAllMatriculas();

			if(CollectionUtils.isValidCollection(lcrc_matriculas))
			{
				Iterator<RegistroCalificacion> lirc_iterator;

				lirc_iterator = lcrc_matriculas.iterator();

				while(lirc_iterator.hasNext())
				{
					RegistroCalificacion lrc_matriculaActual;

					lrc_matriculaActual = lirc_iterator.next();

					if(lrc_matriculaActual != null)
						cambiarUbicacionEnglobe(lrc_matriculaActual);
				}
			}
		}
	}

	/**
	 * Cargar direcciones englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDireccionesEnglobes()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDireccionesEnglobes(getMatriculas(), false);

		if(lrc_data != null)
		{
			setDirecciones(lrc_data.getDirecciones());
			setMatriculas(lrc_data);
		}
	}

	/**
	 * Cargar direcciones venta parcial.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarDireccionesVentaParcial()
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = irr_calificacionRemote.cargarDireccionesEnglobes(getMatriculas(), true);

		if(lrc_data != null)
		{
			setDirecciones(lrc_data.getDirecciones());
			setMatriculas(lrc_data);
		}
	}

	/**
	 * Cargar linderos complementacion englobes.
	 *
	 * @param ab_calificacion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarLinderosComplementacionEnglobes(boolean ab_calificacion)
	    throws B2BException
	{
		RegistroCalificacion lrc_data;

		lrc_data = getMatriculas();

		if(lrc_data != null)
		{
			ComplementacionCalificacion lcc_complementacion;

			lrc_data.setTurno(getTurno());
			lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			setLinderoRegistroCalificacion(irr_calificacionRemote.cargarLinderosEnglobes(lrc_data));

			lcc_complementacion = irr_calificacionRemote.cargarComplementacionEnglobes(lrc_data);

			if(lcc_complementacion != null)
			{
				String ls_tipoComplementacion;

				ls_tipoComplementacion = lcc_complementacion.getTipoComplementacion();

				if(
				    StringUtils.isValidString(ls_tipoComplementacion)
					    && ls_tipoComplementacion.equalsIgnoreCase(TipoComplementacionCommon.NUEVA)
				)
					setHabilitarComplementacion(true);
			}

			setComplementacionCalificacion(lcc_complementacion);

			if(ab_calificacion)
				setHabilitarComplementacion(true);
			else
				limpiarComplementacionData(false);
		}
	}

	/**
	 * Cargar venta parcial.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void cargarVentaParcial()
	    throws B2BException
	{
		if(isProcesoVentaParcial())
		{
			RegistroCalificacion lrc_data;

			lrc_data = irr_calificacionRemote.cargarVentaParcial(getMatriculas());

			if(lrc_data != null)
			{
				if(lrc_data.isSalvarVentaParcial())
					cargarDatosAreaVenta();

				setSalvarVentaParcial(lrc_data.isSalvarVentaParcial());
			}
		}
	}

	/**
	 * Guardar cambios oficina origen.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void guardarCambiosOficinaOrigen()
	    throws B2BException
	{
		boolean          lb_focus;
		ExpresionRegular ler_validador;
		FacesContext     lfc_context;
		OficinaOrigen    loo_oficina;

		lb_focus          = true;
		ler_validador     = ExpresionRegular.getExpresionRegular();
		lfc_context       = FacesContext.getCurrentInstance();
		loo_oficina       = getOficinaMedidaCautelar();

		if(loo_oficina == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		String ls_referencia;
		String ls_numeroProceso;

		ls_referencia        = getReferencia();
		ls_numeroProceso     = getNumeroProceso();

		{
			lb_focus = validateStyles("fRegistroCalif:idItReferencia", lfc_context, ls_referencia, lb_focus);

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_FECHA_REFERENCIA_INVALIDA);
		}

		{
			lb_focus = validateStyles("fRegistroCalif:idItNumeroProceso", lfc_context, ls_numeroProceso, lb_focus);

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_SIN_NUMERO_PROCESO);
		}

		String ls_direccion;
		String ls_idDireccion;

		ls_direccion       = StringUtils.getStringNotEmpty(loo_oficina.getDireccion());
		ls_idDireccion     = "fRegistroCalif:idITDireccionOficina";

		lb_focus = validateStyles(ls_idDireccion, lfc_context, ls_direccion, lb_focus);

		if(!lb_focus)
			throw new B2BException(ErrorKeys.ERROR_SIN_DIRECCION_OFICINA_ORIGEN);

		if(!ler_validador.esDireccion(ls_direccion))
		{
			validateStyles(ls_idDireccion, lfc_context, "", lb_focus);

			throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_MEDIDA_CAUTELAR);
		}
		else
			cleanInputText(ls_idDireccion, lfc_context);

		String ls_telefono;
		String ls_iodTelefono;

		ls_telefono        = StringUtils.getStringNotEmpty(loo_oficina.getTelefono());
		ls_iodTelefono     = "fRegistroCalif:idITTelefonoOficina";

		lb_focus = validateStyles(ls_iodTelefono, lfc_context, ls_telefono, lb_focus);

		if(!lb_focus)
			throw new B2BException(ErrorKeys.ERROR_DIGITE_TELEFONO_VALIDO);

		if(!ler_validador.esTelefono(ls_telefono))
		{
			validateStyles(ls_iodTelefono, lfc_context, "", lb_focus);

			throw new B2BException(ErrorKeys.ERROR_DIGITE_TELEFONO_VALIDO);
		}
		else
			cleanInputText(ls_iodTelefono, lfc_context);

		String ls_correo;
		String ls_idCorreo;

		ls_correo       = StringUtils.getStringNotEmpty(loo_oficina.getCorreoElectronico());
		ls_idCorreo     = "fRegistroCalif:idITEmailOficina";

		lb_focus = validateStyles(ls_idCorreo, lfc_context, ls_correo, lb_focus);

		if(!lb_focus)
			throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

		if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo))
		{
			validateStyles(ls_idCorreo, lfc_context, "", lb_focus);

			throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
		}
		else
			cleanInputText(ls_idCorreo, lfc_context);

		irr_calificacionRemote.guardarInformacionMedidaCautelar(
		    getTurno(), ls_referencia, ls_numeroProceso, loo_oficina, getUserId(), getLocalIpAddress(),
		    getRemoteIpAddress()
		);

		setEditarDatosOficinaMedidaCautelar(false);
		addMessage(MessagesKeys.MODIFICACION_EXITOSA);
	}

	/**
	 * Limpiar areas englobes.
	 */
	private void limpiarAreasEnglobes()
	{
		setBloquearAgregarAreaTerreno(false);
		setDetalleAreaTerreno(null);
	}

	/**
	 * Hace un reset a las variables implicadas en el proceso de selección de medio a comunicar.
	 */
	private void limpiarMedioCo()
	{
		setHabilitarCorreoCo(false);
		setHabilitarDireccionCoCo(false);
		setHabilitarDireccionReCo(false);
		setHabilitarTelFijoCo(false);
		setHabilitarTelMovilCo(false);
	}

	/**
	 * Hace un reset a las variables implicadas en el proceso de selección de medio a notificar.
	 */
	private void limpiarMedioNo()
	{
		setHabilitarCorreoNo(false);
		setHabilitarDireccionCoNo(false);
		setHabilitarDireccionReNo(false);
		setHabilitarTelFijoNo(false);
		setHabilitarTelMovilNo(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param acc_predio correspondiente al valor del tipo de objeto AccAreaPredio
	 * @param la_direccion correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String processCell(AccAreaPredio acc_predio, DireccionPredioAcc la_direccion, String as_userId)
	    throws B2BException
	{
		StringBuilder lsb_msj;
		lsb_msj = new StringBuilder();

		try
		{
			if(acc_predio != null)
			{
				if(!NumericUtils.isValidLong(acc_predio.getIdMatricula()))
					lsb_msj.append(getMessages().getString(MessagesKeys.NUMERO_METRICULA_INVALIDO));

				if(!NumericUtils.isValidDouble(acc_predio.getAreaPredio()))
					lsb_msj.append(getMessages().getString(MessagesKeys.NUMERO_AREA_PREDIO_INVALIDO));

				if(!StringUtils.isValidString(acc_predio.getIdCirculo()))
					lsb_msj.append(getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL_ES_INVALIDO));

				if(!StringUtils.isValidString(lsb_msj.toString()))
				{
					CirculoRegistral lcr_circulo;

					lcr_circulo = new CirculoRegistral();

					lcr_circulo.setIdCirculo(acc_predio.getIdCirculo());

					lcr_circulo = irr_registroRemote.findCirculoRegistralById(lcr_circulo, getUserId());

					if(lcr_circulo != null)
						irr_calificacionRemote.actualizarAreas(
						    acc_predio, la_direccion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
					else
						lsb_msj.append(
						    getMessages().getString(MessagesKeys.EL_CIRCULO_REGISTRAL) + acc_predio.getIdCirculo()
						    + getMessages().getString(MessagesKeys.NO_ENCONTRADO_NO_EXISTE)
						);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			lsb_msj.append(lb2be_e.getMessage());
		}

		return lsb_msj.toString();
	}

	/**
	 * Salvar area predio englobe venta.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarAreaPredioEnglobeVenta()
	    throws B2BException
	{
		try
		{
			if(!isBloquearAgregarAreaTerreno())
			{
				AccAreaUI            laaui_data;
				RegistroCalificacion lrc_data;

				laaui_data     = getAreaUI();
				lrc_data       = getMatriculas();

				if((laaui_data != null) && (lrc_data != null))
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
							String ls_coeficiente;

							ls_tipoUso         = laap_areaPredio.getTipoSuelo();
							ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
							lb_focus           = validateStyles(
								    ":fRegistroCalif:idSOMtipoUsoSueloVenta", lfc_context, ls_tipoUso, lb_focus
								);

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

							if(StringUtils.isValidString(ls_coeficiente))
							{
								Double ld_coeficiente;

								ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

								if(NumericUtils.isValidDouble(ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
								{
									if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100D)) > 0)
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITcoeficienteVenta", lfc_context, "", lb_focus
											);

										throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
									}
									else
									{
										laap_areaPredio.setCoeficiente(ld_coeficiente);
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITcoeficienteVenta", lfc_context, ls_coeficiente,
											    lb_focus
											);
									}
								}
								else
								{
									lb_focus = validateStyles(
										    ":fRegistroCalif:idITcoeficienteVenta", lfc_context, "", lb_focus
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

								ls_area = ldap_area.getAreaLectura();

								if(StringUtils.isValidString(ls_area))
								{
									Double ld_area;

									ld_area = NumericUtils.getDoubleWrapper(ls_area);

									if(NumericUtils.isValidDouble(ld_area, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITareaConstruidaVenta", lfc_context, ls_area,
											    lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITareaConstruidaVenta", lfc_context, "", lb_focus
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
											    ":fRegistroCalif:idITareaPrivadaVenta", lfc_context, ls_area, lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITareaPrivadaVenta", lfc_context, "", lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
								}
							}
						}

						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = lrc_data.getIdCirculoMatriz();
							ll_idMatricula     = lrc_data.getIdMatriculaMatriz();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								laaui_data.setIdCirculo(ls_idCirculo);
								laaui_data.setIdMatricula(ll_idMatricula);
								laaui_data.setIdTurno(lrc_data.getTurno());
								laaui_data.setIdTurnoHistoria(lrc_data.getIdTurnoHistoria());

								irr_calificacionRemote.salvarAreaPredioVenta(
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
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar area predio englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarAreaPredioEnglobes()
	    throws B2BException
	{
		try
		{
			if(!isBloquearAgregarAreaTerreno())
			{
				AccAreaUI            laaui_data;
				RegistroCalificacion lrc_data;

				laaui_data     = getAreaUI();
				lrc_data       = getMatriculas();

				if((laaui_data != null) && (lrc_data != null))
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
							String ls_coeficiente;

							ls_tipoUso         = laap_areaPredio.getTipoSuelo();
							ls_coeficiente     = laap_areaPredio.getCoeficienteLectura();
							lb_focus           = validateStyles(
								    ":fRegistroCalif:idSOMtipoUsoSueloTabs", lfc_context, ls_tipoUso, lb_focus
								);

							if(!StringUtils.isValidString(ls_tipoUso))
								throw new B2BException(ErrorKeys.USO_DEL_PREDIO_INVALIDO_SELECCIONAR);

							if(StringUtils.isValidString(ls_coeficiente))
							{
								Double ld_coeficiente;

								ld_coeficiente = NumericUtils.getDoubleWrapper(ls_coeficiente);

								if(NumericUtils.isValidDouble(ld_coeficiente, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
								{
									if(ld_coeficiente.compareTo(NumericUtils.getDoubleWrapper(100D)) > 0)
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITcoeficienteTabs", lfc_context, "", lb_focus
											);

										throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
									}
									else
									{
										laap_areaPredio.setCoeficiente(ld_coeficiente);
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITcoeficienteTabs", lfc_context, ls_coeficiente,
											    lb_focus
											);
									}
								}
								else
								{
									lb_focus = validateStyles(
										    ":fRegistroCalif:idITcoeficienteTabs", lfc_context, "", lb_focus
										);
									throw new B2BException(ErrorKeys.ERROR_COEFICIENTE);
								}
							}
						}

						{
							Collection<RegistroCalificacion> lcrc_matriculas;
							lcrc_matriculas = lrc_data.getAllMatriculas();

							if(
							    CollectionUtils.isValidCollection(lcrc_matriculas)
								    && CollectionUtils.isValidCollection(lcadap_areasTerreno)
							)
							{
								Iterator<DetalleAreaPredio> ldap_iterador;

								ldap_iterador = lcadap_areasTerreno.iterator();

								if(ldap_iterador.hasNext())
								{
									DetalleAreaPredio ldap_actual;
									Double            ls_area;
									String            ls_unidadMedida;

									ldap_actual         = ldap_iterador.next();
									ls_area             = ldap_actual.getArea();
									ls_unidadMedida     = ldap_actual.getIdUnidadMedida();

									if(
									    NumericUtils.isValidDouble(ls_area)
										    && UnidadMedidaAreaCommon.METROS_CUADRADOS.equals(ls_unidadMedida)
									)
									{
										Iterator<RegistroCalificacion> ldap_iter;
										Collection<String>             lcs_matriculas;

										ldap_iter          = lcrc_matriculas.iterator();
										lcs_matriculas     = new ArrayList<String>();

										while(ldap_iter.hasNext())
										{
											RegistroCalificacion lrc_actual;

											lrc_actual = ldap_iter.next();

											if(lrc_actual != null)
											{
												String ls_matricula;

												ls_matricula = lrc_actual.getIdCirculo();

												if(StringUtils.isValidString(ls_matricula))
													lcs_matriculas.add(ls_matricula);
											}
										}

										if(CollectionUtils.isValidCollection(lcs_matriculas))
										{
											if(!irr_registroRemote.validarSumaAreas(lcs_matriculas, ls_area, true))
												addMessage(MessagesKeys.AREAS_NO_EQUIVALENTES);
										}
									}
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
											    ":fRegistroCalif:idITareaConstruidaTabs", lfc_context, ls_area, lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITareaConstruidaTabs", lfc_context, "", lb_focus
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
											    ":fRegistroCalif:idITareaPrivadaTabs", lfc_context, ls_area, lb_focus
											);
										ldap_area.setArea(ld_area);
									}
									else
									{
										lb_focus = validateStyles(
											    ":fRegistroCalif:idITareaPrivadaTabs", lfc_context, "", lb_focus
											);
										throw new B2BException(ErrorKeys.ERROR_AREA_PRIVADA_CONSTRUIDA);
									}
								}
							}
						}

						{
							String ls_idCirculo;
							Long   ll_idMatricula;

							ls_idCirculo       = lrc_data.getIdCirculo();
							ll_idMatricula     = lrc_data.getIdMatricula();

							if(StringUtils.isValidString(ls_idCirculo) && NumericUtils.isValidLong(ll_idMatricula))
							{
								laaui_data.setIdCirculo(ls_idCirculo);
								laaui_data.setIdMatricula(ll_idMatricula);

								laaui_data.setIdTurno(lrc_data.getTurno());
								laaui_data.setIdTurnoHistoria(lrc_data.getIdTurnoHistoria());

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
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Carga la información relacionada al cierre del folio para que posteriormente sea almacenada
	 * en un registro de la base de datos.
	 *
	 * @param arc_registroCalificacion Objeto contenedor de la información del cierre folio
	 * @param ab_enviarAprobacion correspondiente al valor del tipo de objeto boolean
	 * @return Objeto registro calificación con la información lista para ser almacenada
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private RegistroCalificacion salvarCierreFolio(
	    RegistroCalificacion arc_registroCalificacion, boolean ab_enviarAprobacion
	)
	    throws B2BException
	{
		if(arc_registroCalificacion == null)
			throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

		CambioEstadoPredio lcep_cambioEstadoPredio;

		lcep_cambioEstadoPredio = arc_registroCalificacion.getCambioEstadoPredio();

		if(lcep_cambioEstadoPredio != null)
		{
			String ls_motivoCambio;

			ls_motivoCambio = lcep_cambioEstadoPredio.getMotivoCambioEstado();

			if(ab_enviarAprobacion && !StringUtils.isValidString(ls_motivoCambio))
				throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_OPCION_CIERRE);

			if(StringUtils.isValidString(ls_motivoCambio) && !ls_motivoCambio.equals(EstadoCommon.N))
			{
				boolean      lb_focus;
				boolean      lb_tamMin;
				boolean      lb_tamMax;
				boolean      lb_invalid;
				FacesContext lfc_context;
				String       ls_justificacion;

				lb_focus             = false;
				lb_tamMin            = false;
				lb_tamMax            = false;
				lb_invalid           = false;
				lfc_context          = FacesContext.getCurrentInstance();
				ls_justificacion     = lcep_cambioEstadoPredio.getJustificacionCambio();

				lcep_cambioEstadoPredio.setEstadoPredio(EstadoCommon.C);

				if(StringUtils.isValidString(ls_justificacion))
				{
					if(ls_justificacion.length() < 100)
						lb_tamMin = true;
					else if(ls_justificacion.length() > 4000)
						lb_tamMax = true;
					else
					{
						arc_registroCalificacion.setUserId(getUserId());
						arc_registroCalificacion.setIpAddress(getRemoteIpAddress());
						lcep_cambioEstadoPredio.setActivo(EstadoCommon.S);
					}
				}
				else
					lb_invalid = true;

				if((lb_tamMin || lb_tamMax || lb_invalid) && ab_enviarAprobacion)
				{
					lb_focus = validateStyles(":fRegistroCalif:idITAJustificacionCierre", lfc_context, "", lb_focus);

					if(lb_tamMin)
						throw new B2BException(ErrorKeys.ERROR_JUSTIFICACION_TAM_MIN);
					else if(lb_tamMax)
						throw new B2BException(ErrorKeys.ERROR_JUSTIFICACION_TAM_MAX);
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION_CIERRE_FOLIO);
				}

				arc_registroCalificacion.setSalvarCierreFolio(true);
			}
			else
				arc_registroCalificacion.setSalvarCierreFolio(false);
		}

		return arc_registroCalificacion;
	}

	/**
	 * Salvar datos basicos englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarDatosBasicosEnglobes()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_data;

			lrc_data = getMatriculas();

			if(lrc_data != null)
			{
				Collection<RegistroCalificacion> lcrc_matriculas;

				lcrc_matriculas = lrc_data.getAllMatriculas();

				if(CollectionUtils.isValidCollection(lcrc_matriculas))
				{
					boolean                        lb_matriculaSeleccionada;
					Iterator<RegistroCalificacion> lirc_iterator;

					lb_matriculaSeleccionada     = false;
					lirc_iterator                = lcrc_matriculas.iterator();

					while(lirc_iterator.hasNext())
					{
						RegistroCalificacion lrc_iterador;

						lrc_iterador = lirc_iterator.next();

						if(lrc_iterador != null)
						{
							if(lrc_iterador.isMatriculaSeleccionada())
								lb_matriculaSeleccionada = true;
						}
					}

					if(lb_matriculaSeleccionada)
					{
						lrc_data = irr_calificacionRemote.salvarDatosBasicosEnglobes(
							    lrc_data, getUserId(), getRemoteIpAddress()
							);

						if(lrc_data.isMatriculaSeleccionada())
						{
							PrimeFaces.current().ajax().update("idMatriculaExitosaTabs");
							PrimeFaces.current().executeScript("PF('idMatriculaExitosaTabs').show();");
						}
						else
							lrc_data.setMatriculaSeleccionada(true);

						setMatriculas(lrc_data);

						PrimeFaces.current().ajax().update(":fRegistroCalif:idOPFormsEnbglobes");
						PrimeFaces.current().ajax().update(":fRegistroCalif:idOLMatriculaTemporal");
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_SELECCIONAR_MATRICULA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar direccion venta.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarDireccionVenta()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_rc;

			lrc_rc = getMatriculas();

			if(lrc_rc != null)
			{
				Collection<DireccionPredio> lcdp_direcciones;

				lcdp_direcciones = getDirecciones();

				if(CollectionUtils.isValidCollection(lcdp_direcciones))
				{
					boolean                   lb_seleccion;
					Iterator<DireccionPredio> lidp_iterator;

					lb_seleccion      = false;
					lidp_iterator     = lcdp_direcciones.iterator();

					while(lidp_iterator.hasNext() && !lb_seleccion)
					{
						DireccionPredio ldp_direccion;

						ldp_direccion = lidp_iterator.next();

						if(ldp_direccion != null)
						{
							if(ldp_direccion.isSeleccionado())
							{
								lrc_rc.setDireccionGuardar(ldp_direccion);
								lb_seleccion = true;
							}
						}
					}

					if(!lb_seleccion)
						throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);
				}
				else
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_DIRECCION_PREDIO);

				lrc_rc.setDirecciones(lcdp_direcciones);
				lrc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lrc_rc.setTurno(getTurno());

				irr_calificacionRemote.salvarDireccionVenta(lrc_rc, getUserId(), getRemoteIpAddress());
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Salvar linderos complementacion englobes.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void salvarLinderosComplementacionEnglobes()
	    throws B2BException
	{
		try
		{
			ComplementacionCalificacion lcc_dataComplementacion;
			LinderoRegistroCalificacion llrc_dataLindero;
			RegistroCalificacion        lrc_data;

			lcc_dataComplementacion     = getComplementacionCalificacion();
			llrc_dataLindero            = getLinderoRegistroCalificacion();
			lrc_data                    = getMatriculas();

			if((lcc_dataComplementacion != null) && (llrc_dataLindero != null) && (lrc_data != null))
			{
				String ls_tipoComplementacion;
				String ls_lindero;

				ls_tipoComplementacion     = lcc_dataComplementacion.getTipoComplementacion();
				ls_lindero                 = llrc_dataLindero.getLindero();

				if(!StringUtils.isValidString(ls_lindero))
					throw new B2BException(ErrorKeys.ERROR_LINDERO_INVALIDO);

				if(StringUtils.isValidString(ls_tipoComplementacion))
				{
					ComplementacionPredio lcp_complementacion;

					lcp_complementacion = lcc_dataComplementacion.getComplementacionPredio();

					if(lcp_complementacion != null)
					{
						String ls_complementacion;

						ls_complementacion = lcp_complementacion.getComplementacion();

						if(StringUtils.isValidString(ls_complementacion))
						{
							lrc_data.setTurno(getTurno());
							lrc_data.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							irr_calificacionRemote.salvarLinderosComplementacionEnglobes(
							    lcc_dataComplementacion, llrc_dataLindero, lrc_data, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_COMPLEMENTACION);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_COMPLEMENTACION_TIPO);
			}
		}
		catch(B2BException lb2be_e)
		{
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Validar cierre folio.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void validarCierreFolio()
	    throws B2BException
	{
		try
		{
			RegistroCalificacion lrc_matriculas;

			lrc_matriculas = getMatriculas();

			if((lrc_matriculas != null) && lrc_matriculas.isActoSegregacion())
			{
				CambioEstadoPredio lep_estadoPredio;

				lep_estadoPredio = lrc_matriculas.getCambioEstadoPredio();

				if(lep_estadoPredio != null)
				{
					String ls_motivoCambioEstado;
					String ls_justificacionCambioEstado;

					ls_motivoCambioEstado            = lep_estadoPredio.getMotivoCambioEstado();
					ls_justificacionCambioEstado     = lep_estadoPredio.getJustificacionCambio();

					if(!StringUtils.isValidString(ls_motivoCambioEstado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_UN_TIPO_DE_CIERRE_DE_FOLIO);
					else if(
					    (ls_motivoCambioEstado.equalsIgnoreCase(TipoCierreFolioCommon.AGOTAMIENTO_DE_AREA)
						    || ls_motivoCambioEstado.equalsIgnoreCase(TipoCierreFolioCommon.ENGLOBE))
						    && (!StringUtils.isValidString(ls_justificacionCambioEstado)
						    || (StringUtils.getStringNotNull(ls_justificacionCambioEstado).length() <= 100))
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION_CIERRE_FOLIO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarCierreFolio", lb2be_e);
			throw lb2be_e;
		}
	}
}
