package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanResponsableCorreccion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobacion.AprobacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobador.antiguo.sistema.AprobadorAntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero.DevolucionDineroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.testamentos.TestamentosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.visitas.VisitasRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroAnotacionProhibicion;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoRegionalOrip;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.ui.DevolucionDineroUI;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanActuacionesAdministrativas;
import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanSustanciadorActuacionesAdmin;
import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanBuscarAntiguoSistema;
import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanDetalleAprobacionLibroAntiguoSistema;
import com.bachue.snr.prosnr01.web.bean.consulta.estado.predio.BeanConsultaPredio;
import com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad.BeanConsultaTrazabilidad;
import com.bachue.snr.prosnr01.web.bean.copias.BeanDigitalizacionCopias;
import com.bachue.snr.prosnr01.web.bean.copias.BeanLiquidacionCopias;
import com.bachue.snr.prosnr01.web.bean.correcciones.BeanCorreccionInterna;
import com.bachue.snr.prosnr01.web.bean.correcciones.BeanEjecucionCorrecciones;
import com.bachue.snr.prosnr01.web.bean.correcciones.BeanPermisosCorrecciones;
import com.bachue.snr.prosnr01.web.bean.correcciones.BeanVisualizacionCorrecciones;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanActoAdministrativoDetalle;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanAnalistaDeDevolucion;
import com.bachue.snr.prosnr01.web.bean.devolucionDinero.BeanDevolucionDinero;
import com.bachue.snr.prosnr01.web.bean.homologacionActosLiquidacion.BeanHomologacionActosLiquidacion;
import com.bachue.snr.prosnr01.web.bean.mayorValor.BeanMayorValor;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanUsuarioLineas;
import com.bachue.snr.prosnr01.web.bean.publicacionAvisosWeb.BeanPublicacionAvisosWeb;
import com.bachue.snr.prosnr01.web.bean.reasignar.BeanAsignarTurno;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanRechazaRecurso;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanResolucionRecurso;
import com.bachue.snr.prosnr01.web.bean.segundaInstancia.BeanNoProcedeSegundaInstancia;
import com.bachue.snr.prosnr01.web.bean.segundaInstancia.BeanSegundaInstancia;
import com.bachue.snr.prosnr01.web.bean.trasladoMatriculas.BeanDetalleProyectaResolucion;
import com.bachue.snr.prosnr01.web.bean.trasladoMatriculas.BeanResolucionPlaneacion;
import com.bachue.snr.prosnr01.web.bean.traslados.BeanTrasladosDetalle;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIComponent;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanPredioDocumentoActo.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanPredioDocumentoActo")
public class BeanPredioDocumentoActo extends BeanConsultaTrazabilidad implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1326424469258586188L;

	/** Constante cs_ID_FORM. */
	public static final String cs_ID_FORM = "confrontacionForm";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPredioDocumentoActo.class);

	/** Propiedad iaar_actuacionesAdministrativasRemote. */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad iasd antiguo sistema data. */
	private AntiguoSistemaData iasd_antiguoSistemaData;

	/** Propiedad idas informacion AS etapa anterior 101. */
	private AntiguoSistemaData idas_informacionASEtapaAnterior101;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad ia aprobacion. */
	private Aprobacion ia_aprobacion;

	/** Propiedad irr aprobacion remote. */
	@EJB
	private AprobacionRemote irr_aprobacionRemote;

	/** Propiedad iaas aprobador antiguo sistema remote. */
	@EJB
	private AprobadorAntiguoSistemaRemote iaas_aprobadorAntiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ic motivos. */
	private Collection<MotivoTramite> ic_motivos;

	/** Propiedad icacd_completitudDocumental. */
	private Collection<AccCompletitudDocumental> icacd_completitudDocumental;

	/** Propiedad ii menu. */
	private Collection<AccCompletitudDocumental> icacd_documentosAportados;

	/** Propiedad icadd actos devolución dinero. */
	private Collection<ActoDevolucionDinero> icadd_actosDevolucionDinero;

	/** Propiedad icadd Dinero Temporal. */
	private Collection<ActoDevolucionDinero> icadd_devDineroTemporal;

	/** Propiedad icc causales. */
	private Collection<CausalCorreccion> icc_causales;

	/** Propiedad iccad causales devolucion. */
	private Collection<CausalAprobacionDevolucion> iccad_causalesDevolucion;

	/** Propiedad iccc campos consulta. */
	private Collection<CamposConsulta> iccc_camposConsulta;

	/** Propiedad icd listado documentos. */
	private Collection<Documento> icd_listadoDocumentos;

	/** Propiedad icl liquidacion item. */
	private Collection<Liquidacion> icl_liquidacionItem;

	/** Propiedad icl resumen cobro mayor valor. */
	private Collection<Liquidacion> icl_resumenCobroMayorValor;

	/** Propiedad icnd causales devoluciones panel. */
	private Collection<NotaDevolutiva> icnd_causalesDevolucionesPanel;

	/** Propiedad turno historia. */
	private Collection<TurnoHistoria> icth_turnoHistorias;

	/** Propiedad ictm ejecucion matriculas traslado. */
	private Collection<TrasladoMatricula> ictm_ejecucionMatriculasTraslado;

	/** Propiedad lcr regionales. */
	private Collection<Regional> lcr_regionales;

	/** Propiedad lcsan solicitudes apoyo nacional. */
	private Collection<SolicitudApoyoNacional> lcsan_solicitudesApoyoNacional;

	/** Propiedad lsaro solicitud regional orip. */
	private Collection<SolicitudApoyoRegionalOrip> lsaro_solicitudRegionalOrip;

	/** Propiedad icdd consulta datos documento. */
	private ConsultaDatosDocumento icdd_consultaDatosDocumento;

	/** Propiedad icdb criterios de consulta. */
	private CriteriosDeBusqueda icdb_criteriosDeConsulta;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad idas consulta datos ant sistema. */
	private DatosAntSistema idas_consultaDatosAntSistema;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad idd devoluciones dinero. */
	private DevolucionDinero idd_devolucionDinero;

	/** Propiedad idd devoluciones dinero remote. */
	@EJB
	private DevolucionDineroRemote idd_devolucionesDineroRemote;

	/** Propiedad idd documento data. */
	private DocumentoData idd_documentoData;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad ilr liquidacion remote. */
	@EJB
	private LiquidacionRemote ilr_liquidacionRemote;

	/** Propiedad icmso matriculas individuales. */
	private List<Map<String, Object>> icmso_matriculasIndividuales;

	/** Propiedad icmso matriculas rangos. */
	private List<Map<String, Object>> icmso_matriculasRangos;

	/** Propiedad icmso matriculas traslados. */
	private List<Map<String, Object>> icmso_matriculasTraslados;

	/** Propiedad icmso solicitud visitas persona. */
	private List<Map<String, Object>> icmso_solicitudVisitasPersona;

	/** Propiedad id info documento. */
	private List<Map<String, Object>> id_infoDocumento;

	/** Propiedad ilmso actos. */
	private List<Map<String, Object>> ilmso_actos;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_actosFechas;

	/** Propiedad ilmso actos mayor valor. */
	private List<Map<String, Object>> ilmso_actosMayorValor;

	/** Propiedad ilmso documentos. */
	private List<Map<String, Object>> ilmso_documentos;

	/** Propiedad ilmso info antiguo sistema. */
	private List<Map<String, Object>> ilmso_infoAntiguoSistema;

	/** Propiedad ilmso matriculas tmp individuales. */
	private List<Map<String, Object>> ilmso_matriculasTmpIndividuales;

	/** Propiedad ilmso matriculas tmp rangos. */
	private List<Map<String, Object>> ilmso_matriculasTmpRangos;

	/** Propiedad ilmso predio documento. */
	private List<Map<String, Object>> ilmso_predioDocumento;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_turnosEnCurso;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_turnosFechas;

	/** Propiedad ll id etapa. */
	private Long ll_idEtapa;

	/** Propiedad imso data Acto. */
	private Map<String, Object> imso_dataActo;

	/** Propiedad lm predio. */
	private Map<String, Object> lm_predio;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona titular cuenta. */
	private Persona ip_personaTitularCuenta;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad lrpe_registroPagoExceso. */
	private RegistroPagoExceso irpe_registroPagoExceso;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc imagen zip. */
	private StreamedContent isc_imagenZip;

	/** Propiedad C S dlg correcciones. */
	private String CS_dlgCorrecciones = "dlgCorrecciones";

	/** Propiedad is alerta ejecutoria. */
	private String is_alertaEjecutoria;

	/** propiedad is categoria testamento. */
	private String is_categoriaTestamento;

	/** Propiedad is causal devolucion. */
	private String is_causalDevolucion;

	/** Propiedad is decision aprobacion. */
	private String is_decisionAprobacion;

	/** Propiedad is decision calificacion. */
	private String is_decisionCalificacion;

	/** Propiedad is expediente SI. */
	private String is_expedienteSI;

	/** Propiedad is id circulo orip solicitante. */
	private String is_idCirculoOripSolicitante;

	/** Propiedad is id etapa turno hist ant. */
	private String is_idEtapaTurnoHistAnt;

	/** Propiedad is id motivo turno hist ant. */
	private String is_idMotivoTurnoHistAnt;

	/** Propiedad is_idProceso. */
	private String is_idProceso;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is justificacion cierre folio. */
	private String is_justificacionCierreFolio;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is mensaje confirmacion. */
	private String is_mensajeConfirmacion;

	/** Propiedad is mensaje motivo tramite. */
	private String is_mensajeMotivoTramite;

	/** Propiedad mensaje predio inconsistente. */
	private String is_mensajePredioInconsistente;

	/** Propiedad is mensaje terminar proceso. */
	private String is_mensajeTerminarProceso;

	/** Propiedad is motivo homologacion actos liquidacion. */
	private String is_motivoHomologacionActosLiquidacion;

	/** Propiedad is motivo tramite. */
	private String is_motivoTramite;

	/** Propiedad is nombre motivo tramite. */
	private String is_nombreMotivoTramite;

	/** propiedad is nombre tipo Testamento. */
	private String is_nombreTipoTestamento;

	/** Propiedad is nomenclatura expediente SI. */
	private String is_nomenclaturaExpedienteSI;

	/** Propiedad is observaciones aprobador. */
	private String is_observacionesAprobador;

	/** Propiedad is observaciones calificacion. */
	private String is_observacionesCalificacion;

	/** Propiedad is observaciones etapa 130. */
	private String is_observacionesEtapa130;

	/** Propiedad is observaciones grabacion matriculas. */
	private String is_observacionesGrabacionMatriculas;

	/** Propiedad is observaciones temporales. */
	private String is_observacionesTemporales;

	/** Propiedad is responsable publicación. */
	private String is_responsablePublicacion;

	/** Propiedad is tipo expediente SI. */
	private String is_tipoExpedienteSI;

	/** Propiedad is parametros. */
	private Suspension is_parametros;

	/** Propiedad itr_testamentoRemote. */
	@EJB
	private TestamentosRemote itr_testamentoRemote;

	/** Propiedad seleccion traslado matricula. */
	private TrasladoMatricula itm_seleccionTrasladoMatricula;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad visitas remote. */
	@EJB
	private VisitasRemote ivr_visitasRemote;

	/** Propiedad ib zip final. */
	private byte[] ib_zipFinal;

	/** Propiedad is selected confrontacion. */
	private String[] is_selectedConfrontacion;

	/**  Propiedad ib actuaciones administrativas. */
	private boolean ib_actuacionesAdministrativas;

	/** Propiedad ib administracion homologacion actos liquidacion. */
	private boolean ib_administracionHomologacionActosLiquidacion;

	/**  Propiedad ib antiguo sistema actuaciones administrativas. */
	private boolean ib_antiguoSistemaActuacionesAdministrativas;

	/** Propiedad ib baldios. */
	private boolean ib_baldios;

	/** Propiedad ib confirmar traslados 660. */
	private boolean ib_confirmarTraslados660;

	/** Propiedad ib confirmar traslados 660 dialog. */
	private boolean ib_confirmarTraslados660Dialog;

	/** Propiedad ib confrontacion. */
	private boolean ib_confrontacion = false;

	/** Propiedad ib correccion. */
	private boolean ib_correccion = false;

	/** Propiedad ib datos basicos. */
	private boolean ib_datosBasicos;

	/** Propiedad ib devolucion etapa 111. */
	private boolean ib_devolucionEtapa111 = true;

	/** Propiedad ib documento generado. */
	private boolean ib_documentoGenerado;

	/** Propiedad ib ejecutar validar fecha vencimiento acto. */
	private boolean ib_ejecutarValidarFechaVencimientoActo;

	/** Propiedad ib elimina matricula. */
	private boolean ib_eliminaMatricula;

	/** Propiedad ib eliminar matricula correccion. */
	private boolean ib_eliminarMatriculaCorreccion;

	/** Propiedad ib_esActuacionesAdministrativas. */
	private boolean ib_esActuacionesAdministrativas;

	/** Propiedad ib_esActuacionesAdministrativasDialog. */
	private boolean ib_esActuacionesAdministrativasDialog;

	/** Propiedad ib es aprobador secuencia. */
	private boolean ib_esAprobadorSecuencia;

	/** Propiedad ib es grabacion matriculas. */
	private boolean ib_esGrabacionMatriculas;

	/** Propiedad is es prorroga entrega pruebas. */
	private boolean ib_esProrrogaEntregaPruebas;

	/** Propiedad ib es reasignar turnos apoyo nac. */
	private boolean ib_esReasignarTurnosApoyoNac;

	/** Propiedad ib_esSegundaInstancia. */
	private boolean ib_esSegundaInstancia;

	/** Propiedad ib_esSegundaInstanciaDialog. */
	private boolean ib_esSegundaInstanciaDialog;

	/** Propiedad ib expediente generado. */
	private boolean ib_expedienteGenerado;

	/** Propiedad ib habilitar apoyo nacional. */
	private boolean ib_habilitarApoyoNacional;

	/** Propiedad ib homologacion actos liquidacion. */
	private boolean ib_homologacionActosLiquidacion;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;

	/** Propiedad ib inserta matricula. */
	private boolean ib_insertaMatricula;

	/** Propiedad ib inserta matricula correccion. */
	private boolean ib_insertaMatriculaCorreccion;

	/** Propiedad ib lectura. */
	private boolean ib_lectura;

	/** Propiedad ib_mostrarActo. */
	private boolean ib_mostrarActo;

	/** Propiedad ib mostrar datos 130 grabacion. */
	private boolean ib_mostrarDatos130Grabacion;

	/** Propiedad ib mostrar datos 460 procede SI. */
	private boolean ib_mostrarDatos460ProcedeSI;

	/** Propiedad ib_mostrarDatosDelInteresadoANotificar. */
	private boolean ib_mostrarDatosDelInteresadoANotificar;

	/** Propiedad ib mostrar dialog. */
	private boolean ib_mostrarDialog;

	/** Propiedad ib mostrar liquidacion item. */
	private boolean ib_mostrarLiquidacionItem;

	/** Propiedad ib mostrar mensaje fecha. */
	private boolean ib_mostrarMensajeFecha;

	/** Propiedad ib mostrar mensaje fecha no. */
	private boolean ib_mostrarMensajeFechaNo;

	/** Propiedad ib negar solicitud grabacion. */
	private boolean ib_negarSolicitudGrabacion;

	/** Propiedad ib ocultar paneles. */
	private boolean ib_ocultarPaneles;

	/** Propiedad ib ocultar paneles traslados. */
	private boolean ib_ocultarPanelesTraslados;

	/** Propiedad ib proceso traslados. */
	private boolean ib_procesoTraslados;

	/** Propiedad proyectar. */
	private boolean ib_proyectar;

	/** Propiedad ib rango definitivas. */
	private boolean ib_rangoDefinitivas;

	/** Propiedad ib rango temporales. */
	private boolean ib_rangoTemporales;

	/** Propiedad ib recepcion de documentos. */
	private boolean ib_recepcionDeDocumentos;

	/** Propiedad ib reproduccion constancia. */
	private boolean ib_reproduccionConstancia;

	/** Propiedad ib solicitud presupuestal. */
	private boolean ib_solicitudPresupuestal;

	/** Propiedad ib solicitudes regional orip guardadas. */
	private boolean ib_solicitudesRegionalOripGuardadas;

	/** Propiedad ib boolean testamentos. */
	private boolean ib_testamentos;

	/** Propiedad ib validacion datos etapa 101. */
	private boolean ib_validacionDatosEtapa101;

	/** Propiedad ib verifica folio cerrado. */
	private boolean ib_verificaFolioCerrado;

	/** Propiedad ib verifica folio cerrado correccion. */
	private boolean ib_verificaFolioCerradoCorreccion;

	/** Propiedad ib boolean viene de fijación visitas. */
	private boolean ib_vieneDeFijacionAvisos;

	/** Propiedad ib boolean viene de visitas. */
	private boolean ib_vieneDeVisitas;

	/** Propiedad ib viene trazabilidad. */
	private boolean ib_vieneTrazabilidad;

	/** Propiedad lb alerta ejecutoria. */
	private boolean lb_alertaEjecutoria;

	/** Propiedad lb es analista traslados oficina origen. */
	private boolean lb_esAnalistaTrasladosOficinaOrigen;

	/** Propiedad lb es antiguo sistema. */
	private boolean lb_esAntiguoSistema;

	/** Propiedad lb es aprobacion direccion tecnica registro oficina destino. */
	private boolean lb_esAprobacionDireccionTecnicaRegistroOficinaDestino;

	/** Propiedad lb es aprobacion traslados. */
	private boolean lb_esAprobacionTraslados;

	/** Propiedad lb es suspension tramite. */
	private boolean lb_esSuspensionTramite;

	/** Propiedad lb es suspension tramite dialog. */
	private boolean lb_esSuspensionTramiteDialog;

	/** Propiedad ii menu. */
	private int ii_menu;

	/**
	 * Sets the actos.
	 *
	 * @param alllhmso_lllhmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setActos(List<Map<String, Object>> alllhmso_lllhmso)
	{
		ilmso_actos = alllhmso_lllhmso;
	}

	/**
	 * Retorna el valor de actos.
	 *
	 * @return el valor de actos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getActos()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_actos))
		{
			String ls_identificador;
			String ls_parametro;
			ls_identificador     = IdentificadoresCommon.ACTOS;
			ls_parametro         = getIdTurnoHistoria();

			if(isIndVinculado())
			{
				ls_identificador     = IdentificadoresCommon.ACTOS_VINCULADOS;
				ls_parametro         = getIdTurno();
			}

			ilmso_actos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), ls_parametro, ls_identificador, getFechaDocumento()
				);

			if(CollectionUtils.isValidCollection(ilmso_actos))
			{
				for(Map<String, Object> lms_m : ilmso_actos)
				{
					if(CollectionUtils.isValidCollection(lms_m))
					{
						String ls_alerta;

						ls_alerta = StringUtils.getString(lms_m.get("I_MESSAGE"));

						if(StringUtils.isValidString(ls_alerta))
						{
							addMessage(EstadoCommon.I, ls_alerta);
							FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
						}

						{
							Date ld_fechaVencimiento;

							ld_fechaVencimiento = (Date)lms_m.get(IdentificadoresCommon.FECHA_VENCIMIENTO);

							if(ld_fechaVencimiento != null)
								lms_m.put(
								    "FECHA_VENCIMIENTO_STRING",
								    StringUtils.getString(ld_fechaVencimiento, FormatoFechaCommon.DIA_MES_ANIO)
								);
						}
					}
				}
			}
		}

		return ilmso_actos;
	}

	/**
	 * Modifica el valor de ActosDevolucionDinero.
	 *
	 * @param acddu_ddu Modifica el valor de la propiedad actosDevolucionDinero por acddu_ddu
	 */
	public void setActosDevolucionDinero(Collection<ActoDevolucionDinero> acddu_ddu)
	{
		icadd_actosDevolucionDinero = acddu_ddu;
	}

	/**
	 * Retorna Objeto o variable de valor actos devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad actosDevolucionDinero
	 */
	public Collection<ActoDevolucionDinero> getActosDevolucionDinero()
	{
		return icadd_actosDevolucionDinero;
	}

	/**
	 * Sets the actos mayor valor.
	 *
	 * @param almso_lmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setActosMayorValor(List<Map<String, Object>> almso_lmso)
	{
		ilmso_actosMayorValor = almso_lmso;
	}

	/**
	 * Retorna el valor de actos mayor valor.
	 *
	 * @return el valor de actos mayor valor
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getActosMayorValor()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_actosMayorValor))
			ilmso_actosMayorValor = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ACTOS_MAYOR_VALOR
				);

		return ilmso_actosMayorValor;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setActuacionesAdministrativas(boolean ab_b)
	{
		ib_actuacionesAdministrativas = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public boolean isActuacionesAdministrativas()
	{
		return ib_actuacionesAdministrativas;
	}

	/**
	 * Modifica el valor de AdministracionHomologacionActosLiquidacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setAdministracionHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_administracionHomologacionActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad administracion homologacion actos liquidacion.
	 *
	 * @return Retorna el valor de la propiedad administracionHomologacionActosLiquidacion
	 */
	public boolean isAdministracionHomologacionActosLiquidacion()
	{
		return ib_administracionHomologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de alerta ejecutoria.
	 *
	 * @param ab_b asigna el valor a la propiedad alerta ejecutoria
	 */
	public void setAlertaEjecutoria(boolean ab_b)
	{
		lb_alertaEjecutoria = ab_b;
	}

	/**
	 * Modifica el valor de alerta ejecutoria.
	 *
	 * @param as_s asigna el valor a la propiedad alerta ejecutoria
	 */
	public void setAlertaEjecutoria(String as_s)
	{
		is_alertaEjecutoria = as_s;
	}

	/**
	 * Retorna el valor de alerta ejecutoria.
	 *
	 * @return el valor de alerta ejecutoria
	 */
	public String getAlertaEjecutoria()
	{
		return is_alertaEjecutoria;
	}

	/**
	 * Valida la propiedad alerta ejecutoria.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en alerta ejecutoria
	 */
	public boolean isAlertaEjecutoria()
	{
		return lb_alertaEjecutoria;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAntiguoSistemaActuacionesAdministrativas(boolean ab_b)
	{
		ib_antiguoSistemaActuacionesAdministrativas = ab_b;
	}

	/**
	 * Retorna el valor de alerta ejecutoria.
	 *
	 * @return el valor de alerta ejecutoria
	 */
	public boolean isAntiguoSistemaActuacionesAdministrativas()
	{
		return ib_antiguoSistemaActuacionesAdministrativas;
	}

	/**
	 * Modifica el valor de antiguo sistema data.
	 *
	 * @param aasd_asd asigna el valor a la propiedad antiguo sistema data
	 */
	public void setAntiguoSistemaData(AntiguoSistemaData aasd_asd)
	{
		iasd_antiguoSistemaData = aasd_asd;
	}

	/**
	 * Retorna el valor de antiguo sistema data.
	 *
	 * @return el valor de antiguo sistema data
	 */
	public AntiguoSistemaData getAntiguoSistemaData()
	{
		return iasd_antiguoSistemaData;
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
	 * Modifica el valor de aprobacion.
	 *
	 * @param aa_a asigna el valor a la propiedad aprobacion
	 */
	public void setAprobacion(Aprobacion aa_a)
	{
		ia_aprobacion = aa_a;
	}

	/**
	 * Retorna el valor de aprobacion.
	 *
	 * @return el valor de aprobacion
	 */
	public Aprobacion getAprobacion()
	{
		return ia_aprobacion;
	}

	/**
	 * Modifica el valor de baldios.
	 *
	 * @param ab_b asigna el valor a la propiedad baldios
	 */
	public void setBaldios(boolean ab_b)
	{
		ib_baldios = ab_b;
	}

	/**
	 * Valida la propiedad baldios.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en baldios
	 */
	public boolean isBaldios()
	{
		return ib_baldios;
	}

	/**
	 * Modifica el valor de campos consulta.
	 *
	 * @param accc_ccc asigna el valor a la propiedad campos consulta
	 */
	public void setCamposConsulta(Collection<CamposConsulta> accc_ccc)
	{
		iccc_camposConsulta = accc_ccc;
	}

	/**
	 * Retorna el valor de campos consulta.
	 *
	 * @return el valor de campos consulta
	 */
	public Collection<CamposConsulta> getCamposConsulta()
	{
		return iccc_camposConsulta;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s de as s
	 */
	public void setCategoriaTestamento(String as_s)
	{
		is_categoriaTestamento = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getCategoriaTestamento()
	{
		return is_categoriaTestamento;
	}

	/**
	 * Modifica el valor de causal devolucion.
	 *
	 * @param as_s asigna el valor a la propiedad causal devolucion
	 */
	public void setCausalDevolucion(String as_s)
	{
		is_causalDevolucion = as_s;
	}

	/**
	 * Retorna el valor de causal devolucion.
	 *
	 * @return el valor de causal devolucion
	 */
	public String getCausalDevolucion()
	{
		if(is_causalDevolucion == null)
			is_causalDevolucion = "";

		return is_causalDevolucion;
	}

	/**
	 * Modifica el valor de causales.
	 *
	 * @param acc_cc asigna el valor a la propiedad causales
	 */
	public void setCausales(Collection<CausalCorreccion> acc_cc)
	{
		icc_causales = acc_cc;
	}

	/**
	 * Retorna el valor de causales.
	 *
	 * @return el valor de causales
	 */
	public Collection<CausalCorreccion> getCausales()
	{
		return icc_causales;
	}

	/**
	 * Modifica el valor de causales devolucion.
	 *
	 * @param accad_ccad asigna el valor a la propiedad causales devolucion
	 */
	public void setCausalesDevolucion(Collection<CausalAprobacionDevolucion> accad_ccad)
	{
		iccad_causalesDevolucion = accad_ccad;
	}

	/**
	 * Retorna el valor de causales devolucion.
	 *
	 * @return el valor de causales devolucion
	 */
	public Collection<CausalAprobacionDevolucion> getCausalesDevolucion()
	{
		return iccad_causalesDevolucion;
	}

	/**
	 * Modifica el valor de CausalesDevolucionesPanel.
	 *
	 * @param acnd_cnd de acnd cnd
	 */
	public void setCausalesDevolucionesPanel(Collection<NotaDevolutiva> acnd_cnd)
	{
		icnd_causalesDevolucionesPanel = acnd_cnd;
	}

	/**
	 * Retorna Objeto o variable de valor causales devoluciones panel.
	 *
	 * @return Retorna el valor de la propiedad causalesDevolucionesPanel
	 */
	public Collection<NotaDevolutiva> getCausalesDevolucionesPanel()
	{
		return icnd_causalesDevolucionesPanel;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param acacd_cacd asigna el valor a la propiedad
	 */
	public void setCompletitudDocumental(Collection<AccCompletitudDocumental> acacd_cacd)
	{
		icacd_completitudDocumental = acacd_cacd;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Collection<AccCompletitudDocumental> getCompletitudDocumental()
	{
		return icacd_completitudDocumental;
	}

	/**
	 * Modifica el valor de ConfirmarTraslados660.
	 *
	 * @param ab_confirmarTraslados660 de ab confirmar traslados 660
	 */
	public void setConfirmarTraslados660(boolean ab_confirmarTraslados660)
	{
		ib_confirmarTraslados660 = ab_confirmarTraslados660;
	}

	/**
	 * Valida la propiedad confirmar traslados 660.
	 *
	 * @return Retorna el valor de la propiedad confirmarTraslados660
	 */
	public boolean isConfirmarTraslados660()
	{
		return ib_confirmarTraslados660;
	}

	/**
	 * Modifica el valor de ConfirmarTraslados660Dialog.
	 *
	 * @param ab_confirmarTraslados660Dialog de ab confirmar traslados 660 dialog
	 */
	public void setConfirmarTraslados660Dialog(boolean ab_confirmarTraslados660Dialog)
	{
		ib_confirmarTraslados660Dialog = ab_confirmarTraslados660Dialog;
	}

	/**
	 * Valida la propiedad confirmar traslados 660 dialog.
	 *
	 * @return Retorna el valor de la propiedad confirmarTraslados660Dialog
	 */
	public boolean isConfirmarTraslados660Dialog()
	{
		return ib_confirmarTraslados660Dialog;
	}

	/**
	 * Modifica el valor de confrontacion.
	 *
	 * @param ab_b asigna el valor a la propiedad confrontacion
	 */
	public void setConfrontacion(boolean ab_b)
	{
		ib_confrontacion = ab_b;
	}

	/**
	 * Valida la propiedad confrontacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en confrontacion
	 */
	public boolean isConfrontacion()
	{
		return ib_confrontacion;
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
	 * Modifica el valor de correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad correccion
	 */
	public void setCorreccion(boolean ab_b)
	{
		ib_correccion = ab_b;
	}

	/**
	 * Valida la propiedad correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en correccion
	 */
	public boolean isCorreccion()
	{
		return ib_correccion;
	}

	/**
	 * Modifica el valor de criterios de consulta.
	 *
	 * @param acdb_cdb asigna el valor a la propiedad criterios de consulta
	 */
	public void setCriteriosDeConsulta(CriteriosDeBusqueda acdb_cdb)
	{
		icdb_criteriosDeConsulta = acdb_cdb;
	}

	/**
	 * Retorna el valor de criterios de consulta.
	 *
	 * @return el valor de criterios de consulta
	 */
	public CriteriosDeBusqueda getCriteriosDeConsulta()
	{
		if(icdb_criteriosDeConsulta == null)
			icdb_criteriosDeConsulta = new CriteriosDeBusqueda();

		return icdb_criteriosDeConsulta;
	}

	/**
	 * Sets the data acto.
	 *
	 * @param amso_mso de amso mso
	 */
	public void setDataActo(Map<String, Object> amso_mso)
	{
		imso_dataActo = amso_mso;
	}

	/**
	 * Retorna Objeto o variable de valor data acto.
	 *
	 * @return Retorna el valor de la propiedad dataActo
	 */
	public Map<String, Object> getDataActo()
	{
		return imso_dataActo;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		if(idas_datosAntSistema == null)
		{
			idas_datosAntSistema = new DatosAntSistema();
			idas_datosAntSistema.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad datos basicos
	 */
	public void setDatosBasicos(boolean ab_b)
	{
		ib_datosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos basicos
	 */
	public boolean isDatosBasicos()
	{
		return ib_datosBasicos;
	}

	/**
	 * Modifica el valor de decision aprobacion.
	 *
	 * @param as_s asigna el valor a la propiedad decision aprobacion
	 */
	public void setDecisionAprobacion(String as_s)
	{
		is_decisionAprobacion = as_s;
	}

	/**
	 * Retorna el valor de decision aprobacion.
	 *
	 * @return el valor de decision aprobacion
	 */
	public String getDecisionAprobacion()
	{
		if(is_decisionAprobacion == null)
			is_decisionAprobacion = "";

		return is_decisionAprobacion;
	}

	/**
	 * Modifica el valor de decision calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad decision calificacion
	 */
	public void setDecisionCalificacion(String as_s)
	{
		is_decisionCalificacion = as_s;
	}

	/**
	 * Retorna el valor de decision calificacion.
	 *
	 * @return el valor de decision calificacion
	 */
	public String getDecisionCalificacion()
	{
		return is_decisionCalificacion;
	}

	/**
	 * Modifica el valor de DevDineroTemporal.
	 *
	 * @param acadd_add Modifica el valor de la propiedad devDineroTemporal por devDineroTemporal
	 */
	public void setDevDineroTemporal(Collection<ActoDevolucionDinero> acadd_add)
	{
		icadd_devDineroTemporal = acadd_add;
	}

	/**
	 * Retorna Objeto o variable de valor dev dinero temporal.
	 *
	 * @return Retorna el valor de la propiedad devDineroTemporal
	 */
	public Collection<ActoDevolucionDinero> getDevDineroTemporal()
	{
		return icadd_devDineroTemporal;
	}

	/**
	 * Modifica el valor de DevolucionDinero.
	 *
	 * @param ad_d de ad d
	 */
	public void setDevolucionDinero(DevolucionDinero ad_d)
	{
		idd_devolucionDinero = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor devolucion dinero.
	 *
	 * @return Retorna el valor de la propiedad devolucionDinero
	 */
	public DevolucionDinero getDevolucionDinero()
	{
		return idd_devolucionDinero;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_devolucionEtapa111 por ib_devolucionEtapa111
	 */
	public void setDevolucionEtapa111(boolean ab_d)
	{
		ib_devolucionEtapa111 = ab_d;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_devolucionEtapa111
	 */
	public boolean isDevolucionEtapa111()
	{
		return ib_devolucionEtapa111;
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
	 * Modifica el valor de documento generado.
	 *
	 * @param ab_b asigna el valor a la propiedad documento generado
	 */
	public void setDocumentoGenerado(boolean ab_b)
	{
		ib_documentoGenerado = ab_b;
	}

	/**
	 * Valida la propiedad documento generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento generado
	 */
	public boolean isDocumentoGenerado()
	{
		return ib_documentoGenerado;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param alllhmso_lllhmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setDocumentos(List<Map<String, Object>> alllhmso_lllhmso)
	{
		ilmso_documentos = alllhmso_lllhmso;
	}

	/**
	 * Retorna el valor de documentos.
	 *
	 * @return el valor de documentos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getDocumentos()
	    throws B2BException
	{
		return ilmso_documentos;
	}

	/**
	 * Modifica el valor de DocumentosAportados.
	 *
	 * @param acacd_documentosAportados de acacd documentos aportados
	 */
	public void setDocumentosAportados(Collection<AccCompletitudDocumental> acacd_documentosAportados)
	{
		icacd_documentosAportados = acacd_documentosAportados;
	}

	/**
	 * Retorna Objeto o variable de valor documentos aportados.
	 *
	 * @return Retorna el valor de la propiedad completitudDocumentales
	 */
	public Collection<AccCompletitudDocumental> getDocumentosAportados()
	{
		return icacd_documentosAportados;
	}

	/**
	 * Modifica el valor de EjecucionMatriculasTraslado.
	 *
	 * @param actm_ejecucionMatriculasTraslado de actm ejecucion matriculas traslado
	 */
	public void setEjecucionMatriculasTraslado(Collection<TrasladoMatricula> actm_ejecucionMatriculasTraslado)
	{
		ictm_ejecucionMatriculasTraslado = actm_ejecucionMatriculasTraslado;
	}

	/**
	 * Retorna Objeto o variable de valor ejecucion matriculas traslado.
	 *
	 * @return Retorna el valor de la propiedad ejecucionMatriculasTraslado
	 */
	public Collection<TrasladoMatricula> getEjecucionMatriculasTraslado()
	{
		if(ictm_ejecucionMatriculasTraslado == null)
		{
			try
			{
				long ll_idEtapa;

				ll_idEtapa = NumericUtils.getLong(getIdEtapa());

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS)
					ictm_ejecucionMatriculasTraslado = irr_aprobacionRemote.consultarTrasladosMatriculas(
						    NumericUtils.getLongWrapper(getIdTurnoHistoria()), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update("confrontacion:idGrowl");
			}
		}

		return ictm_ejecucionMatriculasTraslado;
	}

	/**
	 * Modifica el valor de ejecutar validar fecha vencimiento acto.
	 *
	 * @param ab_b asigna el valor a la propiedad ejecutar validar fecha vencimiento acto
	 */
	public void setEjecutarValidarFechaVencimientoActo(boolean ab_b)
	{
		ib_ejecutarValidarFechaVencimientoActo = ab_b;
	}

	/**
	 * Valida la propiedad ejecutar validar fecha vencimiento acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ejecutar validar fecha vencimiento acto
	 */
	public boolean isEjecutarValidarFechaVencimientoActo()
	{
		return ib_ejecutarValidarFechaVencimientoActo;
	}

	/**
	 * Modifica el valor de elimina matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad elimina matricula
	 */
	public void setEliminaMatricula(boolean ab_b)
	{
		ib_eliminaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad elimina matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en elimina matricula
	 */
	public boolean isEliminaMatricula()
	{
		return ib_eliminaMatricula;
	}

	/**
	 * Modifica el valor de eliminar matricula correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad eliminar matricula correccion
	 */
	public void setEliminarMatriculaCorreccion(boolean ab_b)
	{
		ib_eliminarMatriculaCorreccion = ab_b;
	}

	/**
	 * Valida la propiedad eliminar matricula correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en eliminar matricula correccion
	 */
	public boolean isEliminarMatriculaCorreccion()
	{
		return ib_eliminarMatriculaCorreccion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEsActuacionesAdministrativas(boolean ab_b)
	{
		ib_esActuacionesAdministrativas = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public boolean isEsActuacionesAdministrativas()
	{
		return ib_esActuacionesAdministrativas;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEsActuacionesAdministrativasDialog(boolean ab_b)
	{
		ib_esActuacionesAdministrativasDialog = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public boolean isEsActuacionesAdministrativasDialog()
	{
		return ib_esActuacionesAdministrativasDialog;
	}

	/**
	 * Modifica el valor de EsAnalistaTrasladosOficinaOrigen.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsAnalistaTrasladosOficinaOrigen(boolean ab_b)
	{
		lb_esAnalistaTrasladosOficinaOrigen = ab_b;
	}

	/**
	 * Valida la propiedad es analista traslados oficina origen.
	 *
	 * @return Retorna el valor de la propiedad esAnalistaTrasladosOficinaOrigen
	 */
	public boolean isEsAnalistaTrasladosOficinaOrigen()
	{
		return lb_esAnalistaTrasladosOficinaOrigen;
	}

	/**
	 * Modifica el valor de es antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad es antiguo sistema
	 */
	public void setEsAntiguoSistema(boolean ab_b)
	{
		lb_esAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad es antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es antiguo sistema
	 */
	public boolean isEsAntiguoSistema()
	{
		return lb_esAntiguoSistema;
	}

	/**
	 * Modifica el valor de EsAprobacionDireccionTecnicaRegistroOficinaDestino.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsAprobacionDireccionTecnicaRegistroOficinaDestino(boolean ab_b)
	{
		lb_esAprobacionDireccionTecnicaRegistroOficinaDestino = ab_b;
	}

	/**
	 * Valida la propiedad es aprobacion direccion tecnica registro oficina destino.
	 *
	 * @return Retorna el valor de la propiedad esAprobacionDireccionTecnicaRegistroOficinaDestino
	 */
	public boolean isEsAprobacionDireccionTecnicaRegistroOficinaDestino()
	{
		return lb_esAprobacionDireccionTecnicaRegistroOficinaDestino;
	}

	/**
	 * Modifica el valor de EsAprobacionTraslados.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsAprobacionTraslados(boolean ab_b)
	{
		lb_esAprobacionTraslados = ab_b;
	}

	/**
	 * Valida la propiedad es aprobacion traslados.
	 *
	 * @return Retorna el valor de la propiedad esAprobacionTraslados
	 */
	public boolean isEsAprobacionTraslados()
	{
		return lb_esAprobacionTraslados;
	}

	/**
	 * Modifica el valor de es aprobador secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad es aprobador secuencia
	 */
	public void setEsAprobadorSecuencia(boolean ab_b)
	{
		ib_esAprobadorSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad es aprobador secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es aprobador secuencia
	 */
	public boolean isEsAprobadorSecuencia()
	{
		return ib_esAprobadorSecuencia;
	}

	/**
	 * Modifica el valor de es grabacion matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad es grabacion matriculas
	 */
	public void setEsGrabacionMatriculas(boolean ab_b)
	{
		ib_esGrabacionMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad es grabacion matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es grabacion matriculas
	 */
	public boolean isEsGrabacionMatriculas()
	{
		return ib_esGrabacionMatriculas;
	}

	/**
	 * Modifica el valor de EsProrrogaEntregaPruebas.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsProrrogaEntregaPruebas(boolean ab_b)
	{
		ib_esProrrogaEntregaPruebas = ab_b;
	}

	/**
	 * Valida la propiedad es prorroga entrega pruebas.
	 *
	 * @return Retorna el valor de la propiedad esProrrogaEntregaPruebas
	 */
	public boolean isEsProrrogaEntregaPruebas()
	{
		return ib_esProrrogaEntregaPruebas;
	}

	/**
	 * Modifica el valor de EsReasignarTurnosApoyoNac.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsReasignarTurnosApoyoNac(boolean ab_b)
	{
		ib_esReasignarTurnosApoyoNac = ab_b;
	}

	/**
	 * Valida la propiedad es reasignar turnos apoyo nac.
	 *
	 * @return Retorna el valor de la propiedad esReasignarTurnosApoyoNac
	 */
	public boolean isEsReasignarTurnosApoyoNac()
	{
		return ib_esReasignarTurnosApoyoNac;
	}

	/**
	 * Modifica el valor de EsSegundaInstancia.
	 *
	 * @param ib_esSegundaInstancia de ib es segunda instancia
	 */
	public void setEsSegundaInstancia(boolean ib_esSegundaInstancia)
	{
		this.ib_esSegundaInstancia = ib_esSegundaInstancia;
	}

	/**
	 * Valida la propiedad es segunda instancia.
	 *
	 * @return Retorna el valor de la propiedad ib_esSegundaInstancia
	 */
	public boolean isEsSegundaInstancia()
	{
		return ib_esSegundaInstancia;
	}

	/**
	 * Modifica el valor de EsSegundaInstanciaDialog.
	 *
	 * @param ib_esSegundaInstanciaDialog de ib es segunda instancia dialog
	 */
	public void setEsSegundaInstanciaDialog(boolean ib_esSegundaInstanciaDialog)
	{
		this.ib_esSegundaInstanciaDialog = ib_esSegundaInstanciaDialog;
	}

	/**
	 * Valida la propiedad es segunda instancia dialog.
	 *
	 * @return Retorna el valor de la propiedad ib_esSegundaInstanciaDialog
	 */
	public boolean isEsSegundaInstanciaDialog()
	{
		return ib_esSegundaInstanciaDialog;
	}

	/**
	 * Modifica el valor de es suspension tramite.
	 *
	 * @param ab_b asigna el valor a la propiedad es suspension tramite
	 */
	public void setEsSuspensionTramite(boolean ab_b)
	{
		lb_esSuspensionTramite = ab_b;
	}

	/**
	 * Valida la propiedad es suspension tramite.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es suspension tramite
	 */
	public boolean isEsSuspensionTramite()
	{
		return lb_esSuspensionTramite;
	}

	/**
	 * Modifica el valor de es suspension tramite dialog.
	 *
	 * @param ab_b asigna el valor a la propiedad es suspension tramite dialog
	 */
	public void setEsSuspensionTramiteDialog(boolean ab_b)
	{
		lb_esSuspensionTramiteDialog = ab_b;
	}

	/**
	 * Valida la propiedad es suspension tramite dialog.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es suspension tramite dialog
	 */
	public boolean isEsSuspensionTramiteDialog()
	{
		return lb_esSuspensionTramiteDialog;
	}

	/**
	 * Modifica el valor de ExpedienteGenerado.
	 *
	 * @param ab_b de ab b
	 */
	public void setExpedienteGenerado(boolean ab_b)
	{
		ib_expedienteGenerado = ab_b;
	}

	/**
	 * Valida la propiedad expediente generado.
	 *
	 * @return Retorna el valor de la propiedad expedienteGenerado
	 */
	public boolean isExpedienteGenerado()
	{
		return ib_expedienteGenerado;
	}

	/**
	 * Modifica el valor de ExpedienteSI.
	 *
	 * @param as_s de as s
	 */
	public void setExpedienteSI(String as_s)
	{
		is_expedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expediente SI.
	 *
	 * @return Retorna el valor de la propiedad expedienteSI
	 */
	public String getExpedienteSI()
	{
		return is_expedienteSI;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param ad_fd asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(Date ad_fd)
	{
		id_fechaDocumento = ad_fd;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_habilitarApoyoNacional por ab_b
	 */
	public void setHabilitarApoyoNacional(boolean ab_b)
	{
		ib_habilitarApoyoNacional = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_habilitarApoyoNacional
	 */
	public boolean isHabilitarApoyoNacional()
	{
		return ib_habilitarApoyoNacional;
	}

	/**
	 * Modifica el valor de homologacion actos liquidacion.
	 *
	 * @param ab_b asigna el valor a la propiedad homologacion actos liquidacion
	 */
	public void setHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_homologacionActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad homologacion actos liquidacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en homologacion actos liquidacion
	 */
	public boolean isHomologacionActosLiquidacion()
	{
		return ib_homologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de IdCirculoOripSolicitante.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoOripSolicitante(String as_s)
	{
		is_idCirculoOripSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo orip solicitante.
	 *
	 * @return Retorna el valor de la propiedad idCirculoOripSolicitante
	 */
	public String getIdCirculoOripSolicitante()
	{
		return is_idCirculoOripSolicitante;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		ll_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		if((ll_idEtapa == null) || (NumericUtils.getLong(ll_idEtapa) == 0))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idEtapa");

			if(StringUtils.isValidString(tmp))
				ll_idEtapa = NumericUtils.getLongWrapper(tmp);
		}

		return ll_idEtapa;
	}

	/**
	 * Modifica el valor de id etapa turno hist ant.
	 *
	 * @param as_s asigna el valor a la propiedad id etapa turno hist ant
	 */
	public void setIdEtapaTurnoHistAnt(String as_s)
	{
		is_idEtapaTurnoHistAnt = as_s;
	}

	/**
	 * Retorna el valor de id etapa turno hist ant.
	 *
	 * @return el valor de id etapa turno hist ant
	 */
	public String getIdEtapaTurnoHistAnt()
	{
		return is_idEtapaTurnoHistAnt;
	}

	/**
	 * Modifica el valor de id motivo turno hist ant.
	 *
	 * @param as_s asigna el valor a la propiedad id motivo turno hist ant
	 */
	public void setIdMotivoTurnoHistAnt(String as_s)
	{
		is_idMotivoTurnoHistAnt = as_s;
	}

	/**
	 * Retorna el valor de id motivo turno hist ant.
	 *
	 * @return el valor de id motivo turno hist ant
	 */
	public String getIdMotivoTurnoHistAnt()
	{
		return is_idMotivoTurnoHistAnt;
	}

	/**
	 * Método que modifíca el valor de la propiedad.
	 *
	 * @param as_s modifíca el valor de la propiedad.
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String getIdTurno()
	    throws B2BException
	{
		if(!StringUtils.isValidString(is_idTurno))
		{
			Map<String, Object> lmso_predio;

			lmso_predio = getPredio();

			if(CollectionUtils.isValidCollection(lmso_predio))
			{
				Object lo_identificador;

				lo_identificador = lmso_predio.get(IdentificadoresCommon.ID_TURNO);

				if(lo_identificador != null)
					is_idTurno = lo_identificador.toString();
			}
		}

		return is_idTurno;
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
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("is_idTurnoHistoria");

			if(StringUtils.isValidString(tmp))
				is_idTurnoHistoria = tmp;
		}

		return is_idTurnoHistoria;
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
	 * Modifica el valor de imagen zip.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen zip
	 */
	public void setImagenZip(StreamedContent asc_sc)
	{
		isc_imagenZip = asc_sc;
	}

	/**
	 * Retorna el valor de imagen zip.
	 *
	 * @return el valor de imagen zip
	 */
	public StreamedContent getImagenZip()
	{
		return isc_imagenZip;
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
	 * Sets the info antiguo sistema.
	 *
	 * @param alllhmso_llhmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setInfoAntiguoSistema(List<Map<String, Object>> alllhmso_llhmso)
	{
		ilmso_infoAntiguoSistema = alllhmso_llhmso;
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
	 * Sets the info documento.
	 *
	 * @param ad_d correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setInfoDocumento(List<Map<String, Object>> ad_d)
	{
		id_infoDocumento = ad_d;
	}

	/**
	 * Retorna el valor de info documento.
	 *
	 * @return el valor de info documento
	 */
	public List<Map<String, Object>> getInfoDocumento()
	{
		return id_infoDocumento;
	}

	/**
	 * Modifica el valor de informacion AS etapa anterior 101.
	 *
	 * @param adas_das asigna el valor a la propiedad informacion AS etapa anterior 101
	 */
	public void setInformacionASEtapaAnterior101(AntiguoSistemaData adas_das)
	{
		idas_informacionASEtapaAnterior101 = adas_das;
	}

	/**
	 * Retorna el valor de informacion AS etapa anterior 101.
	 *
	 * @return el valor de informacion AS etapa anterior 101
	 */
	public AntiguoSistemaData getInformacionASEtapaAnterior101()
	{
		if(idas_informacionASEtapaAnterior101 == null)
			idas_informacionASEtapaAnterior101 = new AntiguoSistemaData();

		return idas_informacionASEtapaAnterior101;
	}

	/**
	 * Modifica el valor de inserta matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad inserta matricula
	 */
	public void setInsertaMatricula(boolean ab_b)
	{
		ib_insertaMatricula = ab_b;
	}

	/**
	 * Valida la propiedad inserta matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inserta matricula
	 */
	public boolean isInsertaMatricula()
	{
		return ib_insertaMatricula;
	}

	/**
	 * Modifica el valor de inserta matricula correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad inserta matricula correccion
	 */
	public void setInsertaMatriculaCorreccion(boolean ab_b)
	{
		ib_insertaMatriculaCorreccion = ab_b;
	}

	/**
	 * Valida la propiedad inserta matricula correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en inserta matricula correccion
	 */
	public boolean isInsertaMatriculaCorreccion()
	{
		return ib_insertaMatriculaCorreccion;
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
	 * Modifica el valor de lectura.
	 *
	 * @param ab_b asigna el valor a la propiedad lectura
	 */
	public void setLectura(boolean ab_b)
	{
		ib_lectura = ab_b;
	}

	/**
	 * Valida la propiedad lectura.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en lectura
	 */
	public boolean isLectura()
	{
		String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("isLectura");

		if(StringUtils.isValidString(tmp))
			ib_lectura = tmp.equals("1") ? true : false;

		return ib_lectura;
	}

	/**
	 * Modifica el valor de liquidacion item.
	 *
	 * @param acl_cl asigna el valor a la propiedad liquidacion item
	 */
	public void setLiquidacionItem(Collection<Liquidacion> acl_cl)
	{
		icl_liquidacionItem = acl_cl;
	}

	/**
	 * Retorna el valor de liquidacion item.
	 *
	 * @return el valor de liquidacion item
	 */
	public Collection<Liquidacion> getLiquidacionItem()
	{
		return icl_liquidacionItem;
	}

	/**
	 * Modifica el valor de listado documentos.
	 *
	 * @param acd_cd asigna el valor a la propiedad listado documentos
	 */
	public void setListadoDocumentos(Collection<Documento> acd_cd)
	{
		icd_listadoDocumentos = acd_cd;
	}

	/**
	 * Retorna el valor de listado documentos.
	 *
	 * @return el valor de listado documentos
	 */
	public Collection<Documento> getListadoDocumentos()
	{
		return icd_listadoDocumentos;
	}

	/**
	 * Sets the matriculas individuales.
	 *
	 * @param acmso_cmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setMatriculasIndividuales(List<Map<String, Object>> acmso_cmso)
	{
		icmso_matriculasIndividuales = acmso_cmso;
	}

	/**
	 * Retorna el valor de matriculas individuales.
	 *
	 * @return el valor de matriculas individuales
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasIndividuales()
	    throws B2BException
	{
		boolean lb_validacion;

		lb_validacion = isEjecutarValidarFechaVencimientoActo();

		if(lb_validacion)
			validarFechaVencimientoActo();

		if(!CollectionUtils.isValidCollection(icmso_matriculasIndividuales) && isRangoDefinitivas())
		{
			List<Map<String, Object>> lcmso_rangos;

			lcmso_rangos     = new LinkedList<Map<String, Object>>();

			lcmso_rangos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
				);

			if(
			    CollectionUtils.isValidCollection(icmso_matriculasRangos)
				    && CollectionUtils.isValidCollection(lcmso_rangos)
			)
				icmso_matriculasIndividuales = calculoMatriculasIndividuales(icmso_matriculasRangos, lcmso_rangos);
		}

		PrimeFaces.current().ajax().update("confrontacion:idOpNoDefinitivas");
		PrimeFaces.current().ajax().update("confrontacion:idOpSiDefinitivas");

		return icmso_matriculasIndividuales;
	}

	/**
	 * Sets the matriculas rangos.
	 *
	 * @param acmso_cmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setMatriculasRangos(List<Map<String, Object>> acmso_cmso)
	{
		icmso_matriculasRangos = acmso_cmso;
	}

	/**
	 * Retorna el valor de matriculas rangos.
	 *
	 * @return el valor de matriculas rangos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasRangos()
	    throws B2BException
	{
		boolean lb_validacion;

		lb_validacion = isEjecutarValidarFechaVencimientoActo();

		if(lb_validacion)
			validarFechaVencimientoActo();

		if(!CollectionUtils.isValidCollection(icmso_matriculasRangos))
		{
			List<Map<String, Object>> lcmso_rangos;

			lcmso_rangos     = new LinkedList<Map<String, Object>>();

			lcmso_rangos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
				);

			if(CollectionUtils.isValidCollection(lcmso_rangos))
			{
				if(lcmso_rangos.size() > 10)
				{
					icmso_matriculasRangos = calculoRangosMatriculas(lcmso_rangos);
					setRangoDefinitivas(true);
				}
				else
				{
					icmso_matriculasIndividuales = lcmso_rangos;
					setRangoDefinitivas(false);
				}
			}
		}

		PrimeFaces.current().ajax().update("confrontacion:idOpNoDefinitivas");
		PrimeFaces.current().ajax().update("confrontacion:idOpSiDefinitivas");

		return icmso_matriculasRangos;
	}

	/**
	 * Sets the matriculas tmp individuales.
	 *
	 * @param acapr_capr correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setMatriculasTmpIndividuales(List<Map<String, Object>> acapr_capr)
	{
		ilmso_matriculasTmpIndividuales = acapr_capr;
	}

	/**
	 * Retorna el valor de matriculas tmp individuales.
	 *
	 * @return el valor de matriculas tmp individuales
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasTmpIndividuales()
	    throws B2BException
	{
		List<Map<String, Object>> lcmso_rangos;

		lcmso_rangos = new LinkedList<Map<String, Object>>();

		if(!CollectionUtils.isValidCollection(ilmso_matriculasTmpIndividuales) && isRangoTemporales())
			lcmso_rangos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS_TEMPORALES
				);

		if(
		    CollectionUtils.isValidCollection(ilmso_matriculasTmpRangos)
			    && CollectionUtils.isValidCollection(lcmso_rangos)
		)
			ilmso_matriculasTmpIndividuales = calculoMatriculasIndividuales(ilmso_matriculasTmpRangos, lcmso_rangos);

		return ilmso_matriculasTmpIndividuales;
	}

	/**
	 * Sets the matriculas tmp rangos.
	 *
	 * @param acapr_capr correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setMatriculasTmpRangos(List<Map<String, Object>> acapr_capr)
	{
		ilmso_matriculasTmpRangos = acapr_capr;
	}

	/**
	 * Retorna el valor de matriculas tmp rangos.
	 *
	 * @return el valor de matriculas tmp rangos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasTmpRangos()
	    throws B2BException
	{
		List<Map<String, Object>> lcmso_rangos;

		lcmso_rangos = new LinkedList<Map<String, Object>>();

		if(!CollectionUtils.isValidCollection(ilmso_matriculasTmpRangos))
			lcmso_rangos = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS_TEMPORALES
				);

		if(CollectionUtils.isValidCollection(lcmso_rangos))
		{
			if(lcmso_rangos.size() > 10)
			{
				ilmso_matriculasTmpRangos = calculoRangosMatriculas(lcmso_rangos);
				setRangoTemporales(true);
			}
			else
			{
				ilmso_matriculasTmpIndividuales = lcmso_rangos;
				setRangoTemporales(false);
			}
		}

		return ilmso_matriculasTmpRangos;
	}

	/**
	 * Sets the matriculas traslados.
	 *
	 * @param acmso_cmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setMatriculasTraslados(List<Map<String, Object>> acmso_cmso)
	{
		icmso_matriculasTraslados = acmso_cmso;
	}

	/**
	 * Retorna el valor de matriculas traslados.
	 *
	 * @return el valor de matriculas traslados
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getMatriculasTraslados()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(icmso_matriculasTraslados))
			icmso_matriculasTraslados = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.TRASLADOS
				);

		return icmso_matriculasTraslados;
	}

	/**
	 * Modifica el valor de mensaje.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna el valor de mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de mensaje confirmacion.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje confirmacion
	 */
	public void setMensajeConfirmacion(String as_s)
	{
		is_mensajeConfirmacion = as_s;
	}

	/**
	 * Retorna el valor de mensaje confirmacion.
	 *
	 * @return el valor de mensaje confirmacion
	 */
	public String getMensajeConfirmacion()
	{
		return is_mensajeConfirmacion;
	}

	/**
	 * Modifica el valor de mensaje motivo tramite.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje motivo tramite
	 */
	public void setMensajeMotivoTramite(String as_s)
	{
		is_mensajeMotivoTramite = as_s;
	}

	/**
	 * Retorna el valor de mensaje motivo tramite.
	 *
	 * @return el valor de mensaje motivo tramite
	 */
	public String getMensajeMotivoTramite()
	{
		return is_mensajeMotivoTramite;
	}

	/**
	 * Modifica el valor de MensajePredioInconsistente.
	 *
	 * @param as_s de as s
	 */
	public void setMensajePredioInconsistente(String as_s)
	{
		is_mensajePredioInconsistente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad mensajePredioInconsistente
	 */
	public String getMensajePredioInconsistente()
	{
		return is_mensajePredioInconsistente;
	}

	/**
	 * Modifica el valor de MensajeTerminarProceso.
	 *
	 * @param as_s de as s
	 */
	public void setMensajeTerminarProceso(String as_s)
	{
		is_mensajeTerminarProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje terminar proceso.
	 *
	 * @return Retorna el valor de la propiedad mensajeTerminarProceso
	 */
	public String getMensajeTerminarProceso()
	{
		return is_mensajeTerminarProceso;
	}

	/**
	 * Modifica el valor de menu.
	 *
	 * @param ai_i asigna el valor a la propiedad menu
	 */
	public void setMenu(int ai_i)
	{
		ii_menu = ai_i;
	}

	/**
	 * Retorna el valor de menu.
	 *
	 * @return el valor de menu
	 */
	public int getMenu()
	{
		return ii_menu;
	}

	/**
	 * Método de asignacion del valor de la propiedad.
	 *
	 * @param ab_b con el valor a asignar
	 */
	public void setMostrarActo(boolean ab_b)
	{
		ib_mostrarActo = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public boolean isMostrarActo()
	{
		return ib_mostrarActo;
	}

	/**
	 * Modifica el valor de MostrarDatos130Grabacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarDatos130Grabacion(boolean ab_b)
	{
		ib_mostrarDatos130Grabacion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar datos 130 grabacion.
	 *
	 * @return Retorna el valor de la propiedad mostrarDatos130Grabacion
	 */
	public boolean isMostrarDatos130Grabacion()
	{
		return ib_mostrarDatos130Grabacion;
	}

	/**
	 * Modifica el valor de MostrarDatos460ProcedeSI.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarDatos460ProcedeSI(boolean ab_b)
	{
		ib_mostrarDatos460ProcedeSI = ab_b;
	}

	/**
	 * Valida la propiedad mostrar datos 460 procede SI.
	 *
	 * @return Retorna el valor de la propiedad mostrarDatos460ProcedeSI
	 */
	public boolean isMostrarDatos460ProcedeSI()
	{
		return ib_mostrarDatos460ProcedeSI;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrarDatosDelInteresadoANotificar(boolean ab_b)
	{
		ib_mostrarDatosDelInteresadoANotificar = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return ab_b Retorna el valor a la propiedad
	 */
	public boolean isMostrarDatosDelInteresadoANotificar()
	{
		return ib_mostrarDatosDelInteresadoANotificar;
	}

	/**
	 * Modifica el valor de mostrar dialog.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar dialog
	 */
	public void setMostrarDialog(boolean ab_b)
	{
		ib_mostrarDialog = ab_b;
	}

	/**
	 * Valida la propiedad mostrar dialog.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar dialog
	 */
	public boolean isMostrarDialog()
	{
		return ib_mostrarDialog;
	}

	/**
	 * Modifica el valor de mostrar liquidacion item.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar liquidacion item
	 */
	public void setMostrarLiquidacionItem(boolean ab_b)
	{
		ib_mostrarLiquidacionItem = ab_b;
	}

	/**
	 * Valida la propiedad mostrar liquidacion item.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar liquidacion item
	 */
	public boolean isMostrarLiquidacionItem()
	{
		return ib_mostrarLiquidacionItem;
	}

	/**
	 * Modifica el valor de mostrar mensaje fecha.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar mensaje fecha
	 */
	public void setMostrarMensajeFecha(boolean ab_b)
	{
		ib_mostrarMensajeFecha = ab_b;
	}

	/**
	 * Valida la propiedad mostrar mensaje fecha.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar mensaje fecha
	 */
	public boolean isMostrarMensajeFecha()
	{
		return ib_mostrarMensajeFecha;
	}

	/**
	 * Modifica el valor de mostrar mensaje fecha no.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar mensaje fecha no
	 */
	public void setMostrarMensajeFechaNo(boolean ab_b)
	{
		ib_mostrarMensajeFechaNo = ab_b;
	}

	/**
	 * Valida la propiedad mostrar mensaje fecha no.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar mensaje fecha no
	 */
	public boolean isMostrarMensajeFechaNo()
	{
		return ib_mostrarMensajeFechaNo;
	}

	/**
	 * Modifica el valor de motivo homologacion actos liquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad motivo homologacion actos liquidacion
	 */
	public void setMotivoHomologacionActosLiquidacion(String as_s)
	{
		is_motivoHomologacionActosLiquidacion = as_s;
	}

	/**
	 * Retorna el valor de motivo homologacion actos liquidacion.
	 *
	 * @return el valor de motivo homologacion actos liquidacion
	 */
	public String getMotivoHomologacionActosLiquidacion()
	{
		if(is_motivoHomologacionActosLiquidacion == null)
			is_motivoHomologacionActosLiquidacion = StringUtils.getString(
				    MotivoTramiteCommon.HOMOLOGACION_ACTOS_LIQUIDACION_MAYOR_VALOR
				);

		return is_motivoHomologacionActosLiquidacion;
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
	 * Retorna el valor de motivos.
	 *
	 * @return el valor de motivos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> getMotivos()
	    throws B2BException
	{
		boolean                   lb_tieneTipoActoConstanciaRep;
		List<Map<String, Object>> lmso_actos;
		Collection<MotivoTramite> lcc_motivos;
		lcc_motivos     = null;

		lb_tieneTipoActoConstanciaRep     = false;
		lmso_actos                        = getActos();

		boolean lb_acto0463;
		lb_acto0463                       = false;

		if(CollectionUtils.isValidCollection(lmso_actos))
		{
			for(Map<String, Object> lm_tmp : lmso_actos)
			{
				if(lm_tmp != null)
				{
					if(lm_tmp.get("ID_TIPO_ACTO").equals("12"))
						lb_tieneTipoActoConstanciaRep = true;

					if(lm_tmp.get("ID_TIPO_ACTO").equals("0463"))
						lb_acto0463 = true;
				}
			}
		}

		ic_motivos = irr_referenceRemote.findMotivosByEtapa(
			    StringUtils.getString(getIdEtapa()), getIdTurnoHistoria(), lb_tieneTipoActoConstanciaRep, true , lmso_actos
			);

		if(CollectionUtils.isValidCollection(ic_motivos) && lb_acto0463)
		{
			lcc_motivos = new LinkedList<MotivoTramite>();

			for(MotivoTramite lmt_iterador : ic_motivos)
			{
				if(lmt_iterador != null)
				{
					String ls_motivo;
					ls_motivo = StringUtils.getString(lmt_iterador.getIdMotivo());

					if(StringUtils.isValidString(ls_motivo))
						if(!ls_motivo.equalsIgnoreCase("30") && !ls_motivo.equalsIgnoreCase("50"))
							lcc_motivos.add(lmt_iterador);
				}
			}

			ic_motivos = lcc_motivos;
		}

		return ic_motivos;
	}

	/**
	 * Modifica el valor de negar solicitud grabacion.
	 *
	 * @param ab_b asigna el valor a la propiedad negar solicitud grabacion
	 */
	public void setNegarSolicitudGrabacion(boolean ab_b)
	{
		ib_negarSolicitudGrabacion = ab_b;
	}

	/**
	 * Valida la propiedad negar solicitud grabacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en negar solicitud grabacion
	 */
	public boolean isNegarSolicitudGrabacion()
	{
		return ib_negarSolicitudGrabacion;
	}

	/**
	 * Modifica el valor de nombre motivo tramite.
	 *
	 * @param as_s asigna el valor a la propiedad nombre motivo tramite
	 */
	public void setNombreMotivoTramite(String as_s)
	{
		is_nombreMotivoTramite = as_s;
	}

	/**
	 * Retorna el valor de nombre motivo tramite.
	 *
	 * @return el valor de nombre motivo tramite
	 */
	public String getNombreMotivoTramite()
	{
		return is_nombreMotivoTramite;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setNombreTipoTestamento(String as_s)
	{
		is_nombreTipoTestamento = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getNombreTipoTestamento()
	{
		return is_nombreTipoTestamento;
	}

	/**
	 * Modifica el valor de NomenclaturaExpedienteSI.
	 *
	 * @param as_s de as s
	 */
	public void setNomenclaturaExpedienteSI(String as_s)
	{
		is_nomenclaturaExpedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nomenclatura expediente SI.
	 *
	 * @return Retorna el valor de la propiedad nomenclaturaExpedienteSI
	 */
	public String getNomenclaturaExpedienteSI()
	{
		return is_nomenclaturaExpedienteSI;
	}

	/**
	 * Modifica el valor de observaciones aprobador.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones aprobador
	 */
	public void setObservacionesAprobador(String as_s)
	{
		is_observacionesAprobador = as_s;
	}

	/**
	 * Retorna el valor de observaciones aprobador.
	 *
	 * @return el valor de observaciones aprobador
	 */
	public String getObservacionesAprobador()
	{
		return is_observacionesAprobador;
	}

	/**
	 * Modifica el valor de observaciones calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones calificacion
	 */
	public void setObservacionesCalificacion(String as_s)
	{
		is_observacionesCalificacion = as_s;
	}

	/**
	 * Retorna el valor de observaciones calificacion.
	 *
	 * @return el valor de observaciones calificacion
	 */
	public String getObservacionesCalificacion()
	{
		return is_observacionesCalificacion;
	}

	/**
	 * Modifica el valor de observaciones etapa 130.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones etapa 130
	 */
	public void setObservacionesEtapa130(String as_s)
	{
		is_observacionesEtapa130 = as_s;
	}

	/**
	 * Retorna el valor de observaciones etapa 130.
	 *
	 * @return el valor de observaciones etapa 130
	 */
	public String getObservacionesEtapa130()
	{
		return is_observacionesEtapa130;
	}

	/**
	 * Modifica el valor de ObservacionesGrabacionMatriculas.
	 *
	 * @param as_s de as s
	 */
	public void setObservacionesGrabacionMatriculas(String as_s)
	{
		is_observacionesGrabacionMatriculas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones grabacion matriculas.
	 *
	 * @return Retorna el valor de la propiedad observacionesGrabacionMatriculas
	 */
	public String getObservacionesGrabacionMatriculas()
	{
		return is_observacionesGrabacionMatriculas;
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
	 * Modifica el valor de ocultar paneles.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar paneles
	 */
	public void setOcultarPaneles(boolean ab_b)
	{
		ib_ocultarPaneles = ab_b;
	}

	/**
	 * Valida la propiedad ocultar paneles.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar paneles
	 */
	public boolean isOcultarPaneles()
	{
		return ib_ocultarPaneles;
	}

	/**
	 * Modifica el valor de OcultarPanelesTraslados.
	 *
	 * @param ab_ocultarPanelesTraslados de ab ocultar paneles traslados
	 */
	public void setOcultarPanelesTraslados(boolean ab_ocultarPanelesTraslados)
	{
		ib_ocultarPanelesTraslados = ab_ocultarPanelesTraslados;
	}

	/**
	 * Valida la propiedad ocultar paneles traslados.
	 *
	 * @return Retorna el valor de la propiedad ocultarPanelesTraslados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean isOcultarPanelesTraslados()
	    throws B2BException
	{
		if(!ib_ocultarPanelesTraslados)
		{
			String ls_idTurno;

			ls_idTurno = getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
				ib_ocultarPanelesTraslados = irr_referenceRemote.verificarProcesoTrasladoPorIdTurno(ls_idTurno);
		}

		return ib_ocultarPanelesTraslados;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param as_s asigna el valor a la propiedad parametros
	 */
	public void setParametros(Suspension as_s)
	{
		is_parametros = as_s;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Suspension getParametros()
	{
		if(is_parametros == null)
			is_parametros = new Suspension();

		return is_parametros;
	}

	/**
	 * Modifica el valor de Persona.
	 *
	 * @param ap_persona de ap persona
	 */
	public void setPersona(Persona ap_persona)
	{
		ip_persona = ap_persona;
	}

	/**
	 * Retorna Objeto o variable de valor persona.
	 *
	 * @return Retorna el valor de la propiedad persona
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de PersonaTitularCuenta.
	 *
	 * @param ap_p Modifica el valor de la propiedad ip_personaTitularCuenta por ap_p
	 */
	public void setPersonaTitularCuenta(Persona ap_p)
	{
		ip_personaTitularCuenta = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor persona titular cuenta.
	 *
	 * @return Retorna el valor de la propiedad ip_personaTitularCuenta
	 */
	public Persona getPersonaTitularCuenta()
	{
		return ip_personaTitularCuenta;
	}

	/**
	 * Sets the predio.
	 *
	 * @param ahmso_hmso correspondiente al valor del tipo de objeto HashMap<String,Object>
	 */
	public void setPredio(Map<String, Object> ahmso_hmso)
	{
		lm_predio = ahmso_hmso;
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @return el valor de predio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<String, Object> getPredio()
	{
		try
		{
			ic_motivos                            = null;
			ib_datosBasicos                       = false;
			ib_insertaMatricula                   = false;
			ib_insertaMatriculaCorreccion         = false;
			ib_eliminaMatricula                   = false;
			ib_eliminarMatriculaCorreccion        = false;
			ib_verificaFolioCerrado               = false;
			ib_verificaFolioCerradoCorreccion     = false;

			if(lm_predio == null)
				getPredioDocumento();

			if(isMostrarMensajeFecha() && !isMostrarMensajeFechaNo() && !isVieneTrazabilidad())
			{
				String   ls_mensaje;
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = getAlertaEjecutoria();
				ls_mensaje             = getMessages()
						                         .getString(
						    MessagesKeys.FECHA_SUPERIOR_GENERACION_TURNO, loa_messageArgs
						);

				setMostrarMensajeFechaNo(true);

				addMessage("I", ls_mensaje);
				FacesUtils.getFacesContext().getExternalContext().getFlash().setKeepMessages(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getPredio", lb2be_e);
			addMessage(lb2be_e);
			FacesUtils.getFacesContext().getExternalContext().getFlash().setKeepMessages(true);
		}

		return lm_predio;
	}

	/**
	 * Sets the predio documento.
	 *
	 * @param alllhmso_lllhmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setPredioDocumento(List<Map<String, Object>> alllhmso_lllhmso)
	{
		ilmso_predioDocumento = alllhmso_lllhmso;
	}

	/**
	 * Retorna el valor de predio documento.
	 *
	 * @return el valor de predio documento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void getPredioDocumento()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_predioDocumento))
			ilmso_predioDocumento = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.PREDIO
				);

		if(CollectionUtils.isValidCollection(ilmso_predioDocumento))
			lm_predio = ilmso_predioDocumento.get(0);
	}

	/**
	 * Modifica el valor de ProcesoTraslados.
	 *
	 * @param ab_b de ab b
	 */
	public void setProcesoTraslados(boolean ab_b)
	{
		ib_procesoTraslados = ab_b;
	}

	/**
	 * Valida la propiedad proceso traslados.
	 *
	 * @return Retorna el valor de la propiedad procesoTraslados
	 */
	public boolean isProcesoTraslados()
	{
		return ib_procesoTraslados;
	}

	/**
	 * Modifica el valor de Proyectar.
	 *
	 * @param ab_b de ab b
	 */
	public void setProyectar(boolean ab_b)
	{
		ib_proyectar = ab_b;
	}

	/**
	 * Valida la propiedad proyectar.
	 *
	 * @return Retorna el valor de la propiedad proyectar
	 */
	public boolean isProyectar()
	{
		return ib_proyectar;
	}

	/**
	 * Modifica el valor de rango definitivas.
	 *
	 * @param ab_b asigna el valor a la propiedad rango definitivas
	 */
	public void setRangoDefinitivas(boolean ab_b)
	{
		ib_rangoDefinitivas = ab_b;
	}

	/**
	 * Valida la propiedad rango definitivas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rango definitivas
	 */
	public boolean isRangoDefinitivas()
	{
		return ib_rangoDefinitivas;
	}

	/**
	 * Modifica el valor de rango temporales.
	 *
	 * @param ab_b asigna el valor a la propiedad rango temporales
	 */
	public void setRangoTemporales(boolean ab_b)
	{
		ib_rangoTemporales = ab_b;
	}

	/**
	 * Valida la propiedad rango temporales.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rango temporales
	 */
	public boolean isRangoTemporales()
	{
		return ib_rangoTemporales;
	}

	/**
	 * Modifica el valor de recepcion de documentos.
	 *
	 * @param ab_b asigna el valor a la propiedad recepcion de documentos
	 */
	public void setRecepcionDeDocumentos(boolean ab_b)
	{
		ib_recepcionDeDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad recepcion de documentos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en recepcion de documentos
	 */
	public boolean isRecepcionDeDocumentos()
	{
		return ib_recepcionDeDocumentos;
	}

	/**
	 * Modifica el valor de Regionales.
	 *
	 * @param ac_c de ac c
	 */
	public void setRegionales(Collection<Regional> ac_c)
	{
		lcr_regionales = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor regionales.
	 *
	 * @return Retorna el valor de la propiedad regionales
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Regional> getRegionales()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(lcr_regionales))
		{
			String ls_idCirculoSolicitante;
			ls_idCirculoSolicitante = getIdCirculoOripSolicitante();

			if(StringUtils.isValidString(ls_idCirculoSolicitante))
				lcr_regionales = irr_referenceRemote.findAllRegionalesActivosConCirculo(
					    ls_idCirculoSolicitante, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}

		return lcr_regionales;
	}

	/**
	 * Modifica el valor de RegistroPagoExceso.
	 *
	 * @param ar_r de ar r
	 */
	public void setRegistroPagoExceso(RegistroPagoExceso ar_r)
	{
		irpe_registroPagoExceso = ar_r;
	}

	/**
	 * Método para consultar valor registro pago exceso con el turno especifico.
	 *
	 * @return Retorna el valor de la propiedad registroPagoExceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroPagoExceso getRegistroPagoExceso()
	    throws B2BException
	{
		if(irpe_registroPagoExceso == null)
		{
			Turno lt_turno;
			lt_turno = getTurno();

			if(lt_turno != null)
			{
				String ls_turno;
				ls_turno                    = lt_turno.getIdTurno();
				irpe_registroPagoExceso     = irr_calificacionRemote.findRegistroPagoExcesoByTurno(ls_turno);
			}
		}

		return irpe_registroPagoExceso;
	}

	/**
	 * Modifica el valor de reproduccion constancia.
	 *
	 * @param ab_b asigna el valor a la propiedad reproduccion constancia
	 */
	public void setReproduccionConstancia(boolean ab_b)
	{
		ib_reproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad reproduccion constancia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reproduccion constancia
	 */
	public boolean isReproduccionConstancia()
	{
		return ib_reproduccionConstancia;
	}

	/**
	 * @param Modifica el valor de la propiedad responsablePublicacion por responsablePublicacion
	 */
	public void setResponsablePublicacion(String as_s)
	{
		is_responsablePublicacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad responsablePublicacion
	 */
	public String getResponsablePublicacion()
	{
		return is_responsablePublicacion;
	}

	/**
	 * Modifica el valor de resumen cobro mayor valor.
	 *
	 * @param acl_cl asigna el valor a la propiedad resumen cobro mayor valor
	 */
	public void setResumenCobroMayorValor(Collection<Liquidacion> acl_cl)
	{
		icl_resumenCobroMayorValor = acl_cl;
	}

	/**
	 * Retorna el valor de resumen cobro mayor valor.
	 *
	 * @return el valor de resumen cobro mayor valor
	 */
	public Collection<Liquidacion> getResumenCobroMayorValor()
	{
		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = new Liquidacion();

			ll_liquidacion.setIdTurno(getIdTurno());
			ll_liquidacion.setConsecutivo(1);
			ll_liquidacion.setIdTipoMayorValor(EstadoCommon.M);

			icl_resumenCobroMayorValor = ilr_liquidacionRemote.buscarLiquidacionItem(
				    ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("getResumenCobroMayorValor", lb2be_e);

			addMessage(lb2be_e);
		}

		return icl_resumenCobroMayorValor;
	}

	/**
	 * Modifica el valor de SeleccionTrasladoMatricula.
	 *
	 * @param atm_seleccionTrasladoMatricula de atm seleccion traslado matricula
	 */
	public void setSeleccionTrasladoMatricula(TrasladoMatricula atm_seleccionTrasladoMatricula)
	{
		itm_seleccionTrasladoMatricula = atm_seleccionTrasladoMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor seleccion traslado matricula.
	 *
	 * @return Retorna el valor de la propiedad seleccionTrasladoMatricula
	 */
	public TrasladoMatricula getSeleccionTrasladoMatricula()
	{
		return itm_seleccionTrasladoMatricula;
	}

	/**
	 * Modifica el valor de selected confrontacion.
	 *
	 * @param as_s asigna el valor a la propiedad selected confrontacion
	 */
	public void setSelectedConfrontacion(String[] as_s)
	{
		is_selectedConfrontacion = as_s;
	}

	/**
	 * Retorna el valor de selected confrontacion.
	 *
	 * @return el valor de selected confrontacion
	 */
	public String[] getSelectedConfrontacion()
	{
		return is_selectedConfrontacion;
	}

	/**
	 * Asigna el valor de la propiedad solicitud presupuestal.
	 *
	 * @param ib_b            el nuevo valor para la propiedad solicitud presupuestal
	 */
	public void setSolicitudPresupuestal(boolean ib_b)
	{
		ib_solicitudPresupuestal = ib_b;
	}

	/**
	 * Retonrna el valor de la propiedad solicitud presupuestal.
	 *
	 * @return el valor de la propiedad solicitud presupuestal.
	 */
	public boolean isSolicitudPresupuestal()
	{
		return ib_solicitudPresupuestal;
	}

	/**
	 * Modifica el valor de SolicitudRegionalOrip.
	 *
	 * @param ac_c de ac c
	 */
	public void setSolicitudRegionalOrip(Collection<SolicitudApoyoRegionalOrip> ac_c)
	{
		lsaro_solicitudRegionalOrip = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud regional orip.
	 *
	 * @return Retorna el valor de la propiedad solicitudRegionalOrip
	 */
	public Collection<SolicitudApoyoRegionalOrip> getSolicitudRegionalOrip()
	{
		return lsaro_solicitudRegionalOrip;
	}

	/**
	 * Sets the solicitud visitas persona.
	 *
	 * @param acmso_solicitudVisitasPersona de acmso solicitud visitas persona
	 */
	public void setSolicitudVisitasPersona(List<Map<String, Object>> acmso_solicitudVisitasPersona)
	{
		icmso_solicitudVisitasPersona = acmso_solicitudVisitasPersona;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud visitas persona.
	 *
	 * @return Retorna el valor de la propiedad solicitudVisitasPersona
	 */
	public List<Map<String, Object>> getSolicitudVisitasPersona()
	{
		return icmso_solicitudVisitasPersona;
	}

	/**
	 * Modifica el valor de SolicitudApoyoNacional.
	 *
	 * @param ac_c de ac c
	 */
	public void setSolicitudesApoyoNacional(Collection<SolicitudApoyoNacional> ac_c)
	{
		lcsan_solicitudesApoyoNacional = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad solicitudApoyoNacional
	 */
	public Collection<SolicitudApoyoNacional> getSolicitudesApoyoNacional()
	{
		return lcsan_solicitudesApoyoNacional;
	}

	/**
	 * Modifica el valor de SolicitudesRegionalOripGuardadas.
	 *
	 * @param ab_b de ab b
	 */
	public void setSolicitudesRegionalOripGuardadas(boolean ab_b)
	{
		ib_solicitudesRegionalOripGuardadas = ab_b;
	}

	/**
	 * Valida la propiedad solicitudes regional orip guardadas.
	 *
	 * @return Retorna el valor de la propiedad solicitudesRegionalOripGuardadas
	 */
	public boolean isSolicitudesRegionalOripGuardadas()
	{
		return ib_solicitudesRegionalOripGuardadas;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param ab_b con el valor a asignar
	 */
	public void setTestamentos(boolean ab_b)
	{
		ib_testamentos = ab_b;
	}

	/**
	 * Método de obtención del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public boolean isTestamentos()
	{
		return ib_testamentos;
	}

	/**
	 * Modifica el valor de TipoExpedienteSI.
	 *
	 * @param as_s de as s
	 */
	public void setTipoExpedienteSI(String as_s)
	{
		is_tipoExpedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo expediente SI.
	 *
	 * @return Retorna el valor de la propiedad tipoExpedienteSI
	 */
	public String getTipoExpedienteSI()
	{
		return is_tipoExpedienteSI;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param at_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Turno getTurno()
	    throws B2BException
	{
		String ls_idTurno;

		ls_idTurno = getIdTurno();

		if(StringUtils.isValidString(ls_idTurno))
		{
			it_turno = irr_calificacionRemote.findTurno(ls_idTurno);

			if(it_turno != null)
			{
				Date ld_fechaCreacion;

				ld_fechaCreacion = it_turno.getFechaCreacion();

				if(ld_fechaCreacion != null)
					it_turno.setFechaCreacionString(
					    StringUtils.getString(ld_fechaCreacion, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
					);
			}
		}

		if(it_turno == null)
			it_turno = new Turno();

		return it_turno;
	}

	/**
	 * Modifica el valor de TurnoHistoria.
	 *
	 * @param ath_th de ath th
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna Objeto o variable de valor turno historia.
	 *
	 * @return Retorna el valor de la propiedad turnoHistoria
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}

	/**
	 * @param Modifica el valor de la propiedad turnoHistorias por turnoHistorias
	 */
	public void setTurnoHistorias(Collection<TurnoHistoria> acth_cth)
	{
		icth_turnoHistorias = acth_cth;
	}

	/**
	 * @return Retorna el valor de la propiedad turnoHistorias
	 */
	public Collection<TurnoHistoria> getTurnoHistorias()
	{
		return icth_turnoHistorias;
	}

	/**
	 * Sets the turnos en curso.
	 *
	 * @param almso_lmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setTurnosEnCurso(List<Map<String, Object>> almso_lmso)
	{
		ilmso_turnosEnCurso = almso_lmso;
	}

	/**
	 * Retorna el valor de turnos en curso.
	 *
	 * @return el valor de turnos en curso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getTurnosEnCurso()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_turnosEnCurso))
			ilmso_turnosEnCurso = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.TURNO_CON_TRAMITES_EN_CURSO
				);

		return ilmso_turnosEnCurso;
	}

	/**
	 * Retorna el valor de turnos en fechas.
	 *
	 * @return el valor de turnos en curso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getTurnosFechas()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_turnosFechas))
			ilmso_turnosFechas = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.TURNOS_FECHAS
				);

		return ilmso_turnosFechas;
	}

	/**
	 * Sets the turnos en curso.
	 *
	 * @param almso_lmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setTurnosFechas(List<Map<String, Object>> almso_lmso)
	{
		ilmso_turnosFechas = almso_lmso;
	}

	/**
	 * Retorna el valor de actos fechas.
	 *
	 * @return el valor de turnos en curso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getActosFechas()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_actosFechas))
			ilmso_actosFechas = irr_calificacionRemote.findPredioDocumentoByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ACTOS_FECHAS
				);

		return ilmso_actosFechas;
	}

	/**
	 * Sets the turnos en curso.
	 *
	 * @param almso_lmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setActosFechas(List<Map<String, Object>> almso_lmso)
	{
		ilmso_actosFechas = almso_lmso;
	}

	/**
	 * Valida la propiedad valid matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valid matriculas
	 */
	public boolean isValidMatriculas()
	{
		return CollectionUtils.isValidCollection(icmso_matriculasIndividuales);
	}

	/**
	 * Valida la propiedad valid matriculas tmp.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valid matriculas tmp
	 */
	public boolean isValidMatriculasTmp()
	{
		return CollectionUtils.isValidCollection(ilmso_matriculasTmpIndividuales);
	}

	/**
	 * Modifica el valor de validacion datos etapa 101.
	 *
	 * @param ab_b asigna el valor a la propiedad validacion datos etapa 101
	 */
	public void setValidacionDatosEtapa101(boolean ab_b)
	{
		ib_validacionDatosEtapa101 = ab_b;
	}

	/**
	 * Valida la propiedad validacion datos etapa 101.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validacion datos etapa 101
	 */
	public boolean isValidacionDatosEtapa101()
	{
		return ib_validacionDatosEtapa101;
	}

	/**
	 * Retorna el valor boolean basado en las etapas de grabacion.
	 *
	 * @return el valor boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean getValidasEtapasGrabacion()
	    throws B2BException
	{
		Turno   lt_turno;
		boolean lt_return;
		lt_return     = false;
		lt_turno      = irr_calificacionRemote.validarEtapaGrabacion(getIdTurnoHistoria());

		if(lt_turno != null)
			lt_return = true;

		return lt_return;
	}

	/**
	 * Modifica el valor de verifica folio cerrado.
	 *
	 * @param ab_b asigna el valor a la propiedad verifica folio cerrado
	 */
	public void setVerificaFolioCerrado(boolean ab_b)
	{
		ib_verificaFolioCerrado = ab_b;
	}

	/**
	 * Valida la propiedad verifica folio cerrado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en verifica folio cerrado
	 */
	public boolean isVerificaFolioCerrado()
	{
		return ib_verificaFolioCerrado;
	}

	/**
	 * Modifica el valor de verifica folio cerrado correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad verifica folio cerrado correccion
	 */
	public void setVerificaFolioCerradoCorreccion(boolean ab_b)
	{
		ib_verificaFolioCerradoCorreccion = ab_b;
	}

	/**
	 * Valida la propiedad verifica folio cerrado correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en verifica folio cerrado correccion
	 */
	public boolean isVerificaFolioCerradoCorreccion()
	{
		return ib_verificaFolioCerradoCorreccion;
	}

	/**
	 * Modifica el valor de VieneDeFijacionAvisos.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeFijacionAvisos(boolean ab_b)
	{
		ib_vieneDeFijacionAvisos = ab_b;
	}

	/**
	 * Valida la propiedad viene de fijacion avisos.
	 *
	 * @return Retorna el valor de la propiedad vieneDeFijacionAvisos
	 */
	public boolean isVieneDeFijacionAvisos()
	{
		return ib_vieneDeFijacionAvisos;
	}

	/**
	 * Modifica el valor de VieneDeVisitas.
	 *
	 * @param ab_vieneDeVisitas de ab viene de visitas
	 */
	public void setVieneDeVisitas(boolean ab_vieneDeVisitas)
	{
		ib_vieneDeVisitas = ab_vieneDeVisitas;
	}

	/**
	 * Valida la propiedad viene de visitas.
	 *
	 * @return Retorna el valor de la propiedad vieneDeVisitas
	 */
	public boolean isVieneDeVisitas()
	{
		return ib_vieneDeVisitas;
	}

	/**
	 * Modifica el valor de viene trazabilidad.
	 *
	 * @param ab_b asigna el valor a la propiedad viene trazabilidad
	 */
	public void setVieneTrazabilidad(boolean ab_b)
	{
		ib_vieneTrazabilidad = ab_b;
	}

	/**
	 * Valida la propiedad viene trazabilidad.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene trazabilidad
	 */
	public boolean isVieneTrazabilidad()
	{
		return ib_vieneTrazabilidad;
	}

	/**
	 * Modifica el valor de zip final.
	 *
	 * @param ab_b asigna el valor a la propiedad zip final
	 */
	public void setZipFinal(byte[] ab_b)
	{
		ib_zipFinal = ab_b;
	}

	/**
	 * Retorna el valor de zip final.
	 *
	 * @return el valor de zip final
	 */
	public byte[] getZipFinal()
	{
		return ib_zipFinal;
	}

	/**
	 * Envia el tramite en proceso a la etapa anterior a la actual.
	 *
	 * @param ab_salvar true para indicar que se debe salvar el trámite
	 * @return pagina de detalle de turnos de aprobación
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public String accionDevolverAprobacion(boolean ab_salvar)
	    throws Exception
	{
		String ls_return;

		ls_return = null;

		try
		{
			Aprobacion la_aprobacion;

			la_aprobacion = getAprobacion();

			if(la_aprobacion != null)
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				if(ab_salvar)
				{
					TurnoHistoria lth_turnoHistoria;
					TurnoHistoria lth_turnoHistoriaAnt;
					BigDecimal    lbd_idEtapaTurno;
					boolean       lb_etapaAnt105;

					lb_etapaAnt105        = false;
					lth_turnoHistoria     = la_aprobacion.getTurnoHistoria();

					if(lth_turnoHistoria == null)
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

					lbd_idEtapaTurno     = lth_turnoHistoria.getIdEtapa();

					lth_turnoHistoriaAnt = irr_calificacionRemote.findTurnoHistoriaAnteriorById(lth_turnoHistoria);

					if(lth_turnoHistoriaAnt != null)
					{
						BigDecimal lbd_idEtapaTurnoAnt;

						lbd_idEtapaTurnoAnt     = lth_turnoHistoriaAnt.getIdEtapa();

						lb_etapaAnt105 = NumericUtils.getLong(lbd_idEtapaTurnoAnt) == EtapaCommon.ANALISIS_CREACION_MATRICULA_OFICIO;
					}

					if(la_aprobacion.isVieneAprobacionApoyoNacional())
						la_aprobacion.setObservaciones(getObservacionesAprobador());

					if(!NumericUtils.isValidBigDecimal(lbd_idEtapaTurno))
						throw new B2BException(ErrorKeys.ERROR_SIN_ETAPA);

					if((NumericUtils.getInt(lbd_idEtapaTurno) == 110) && !lb_etapaAnt105)
					{
						Collection<Aprobacion> lca_aprobaciones;

						lca_aprobaciones = new LinkedList<Aprobacion>();

						la_aprobacion.setFirmar(IdentificadoresCommon.ANALISTA);

						lca_aprobaciones.add(la_aprobacion);

						iaas_aprobadorAntiguoSistemaRemote.salvarAprobacion(
						    lca_aprobaciones, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
					}
					else
						irr_aprobacionRemote.devolverAprobacion(
						    la_aprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					BeanAprobacion lba_beanAprobacion;

					lba_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
							                                            .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
							);

					if(lba_beanAprobacion != null)
					{
						Collection<Aprobacion> lca_aprobaciones;

						lca_aprobaciones = lba_beanAprobacion.getDataAprobacion();

						if(CollectionUtils.isValidCollection(lca_aprobaciones))
						{
							lca_aprobaciones.remove(la_aprobacion);

							ls_return = NavegacionCommon.APROBACION;
						}
						else
						{
							lba_beanAprobacion.clean();
							lba_beanAprobacion.limpiarBanderaProcesos();
							lba_beanAprobacion.findDetalleAprobacion();
							lba_beanAprobacion.setMotivoTramiteSeleccionado(null);
							lba_beanAprobacion.setIdProcesoSeleccionado(null);

							ls_return = NavegacionCommon.BANDEJA_ENTRADA;
						}

						if(NumericUtils.getLong(lbd_idEtapaTurno) == EtapaCommon.ID_ETAPA_380)
						{
							lba_beanAprobacion.clean();
							lba_beanAprobacion.limpiarBanderaProcesos();
							lba_beanAprobacion.setVieneDeResponsableTesoreria(true);
							lba_beanAprobacion.findDetalleAprobacion();
							lba_beanAprobacion.setMotivoTramiteSeleccionado(null);
							lba_beanAprobacion.setIdProcesoSeleccionado(null);
							lba_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_380, false);
							ls_return = NavegacionCommon.BANDEJA_ENTRADA;
						}
					}

					limpiarDatos();
				}
				else
				{
					CausalAprobacionDevolucion lcad_causalDevolucion;
					String                     ls_causalDevolucion;
					String                     ls_observaciones;
					boolean                    lb_focus;

					ls_causalDevolucion       = getCausalDevolucion();
					lcad_causalDevolucion     = null;

					if(StringUtils.isValidString(ls_causalDevolucion))
					{
						lcad_causalDevolucion = new CausalAprobacionDevolucion();

						lcad_causalDevolucion.setCodigo(ls_causalDevolucion);

						lcad_causalDevolucion = ipr_parameterRemote.findCausalAprobacionDevolucionById(
							    lcad_causalDevolucion
							);
					}

					ls_observaciones     = getObservacionesAprobador();

					lb_focus     = true;

					lb_focus = validateStyles(
						    ":confrontacionForm:idSOMMotivosDevolucion", lfc_context, ls_causalDevolucion, lb_focus
						);

					if(!StringUtils.isValidString(ls_causalDevolucion))
						throw new B2BException(ErrorKeys.ERROR_CAUSAL_DEVOLUCION_APROBACION);

					lb_focus = validateStyles(
						    ":confrontacionForm:idITAObsAprobador", lfc_context, ls_observaciones, lb_focus
						);

					if(!StringUtils.isValidString(ls_observaciones))
					{
						Object[] loa_messageArgs = new String[1];
						loa_messageArgs[0] = la_aprobacion.getIdTurno();

						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_APROBADOR, loa_messageArgs);
					}

					if(ls_observaciones.length() < 100)
					{
						lb_focus = validateStyles(":confrontacionForm:idITAObsAprobador", lfc_context, "", lb_focus);
						throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_100);
					}

					la_aprobacion.setMotivoNoTramite(
					    (lcad_causalDevolucion != null) ? lcad_causalDevolucion.getCausalDevolucion()
					                                    : IdentificadoresCommon.DATO_INVALIDO
					);
					la_aprobacion.setObservaciones(ls_observaciones);
					la_aprobacion.setCausalDevolucion(lcad_causalDevolucion);

					PrimeFaces.current().executeScript("PF('dlgConfirmarDevolucion').show();");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);

			addMessage(lb2be_e);

			PrimeFaces.current().ajax().update("confrontacionForm:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Aprobar reasignacion apoyo nacional y ejecución de procedimiento reasignar turnos apoyo nacional.
	 */
	public void aprobarReasignacionApoyoNacional()
	{
		try
		{
			if(isSolicitudesRegionalOripGuardadas())
			{
				SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;
				lsanui_solicitudApoyoNacional = new SolicitudApoyoNacionalUI();

				validarSolicitudesApoyoRegionalOrip();

				lsanui_solicitudApoyoNacional.setIdTurnoHistoria(getIdTurnoHistoria());

				lsanui_solicitudApoyoNacional = irr_calificacionRemote.aprobarReasignacionApoyoNacional(
					    lsanui_solicitudApoyoNacional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lsanui_solicitudApoyoNacional != null)
					setSolicitudesApoyoNacional(lsanui_solicitudApoyoNacional.getSolicitudesApoyoNacional());
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_CAMBIOS_REALIZADOS);

			setEsReasignarTurnosApoyoNac(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}
	}

	/**
	 * Avanzar etapa.
	 *
	 * @return el valor de string
	 */
	public String avanzarEtapa()
	{
		String ls_return;

		ls_return = null;

		try
		{
			Collection<TurnoHistoria> lcth_turnoHistorias;

			lcth_turnoHistorias = getTurnoHistorias();

			if(CollectionUtils.isValidCollection(lcth_turnoHistorias))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = lcth_turnoHistorias.iterator().next();

				if(lth_turnoHistoria != null)
				{
//					TODO arreglar responsable publicación
					{
						boolean      lb_focus;
						FacesContext lfc_context;

						lb_focus        = true;
						lfc_context     = FacesContext.getCurrentInstance();

						String ls_responsablePublicacion;

						ls_responsablePublicacion = lth_turnoHistoria.getResponsablePublicacion();

						lb_focus = validateStyles(
							    ":confrontacionForm:idDtPublicacionesWeb:0:idResponsablePublicacion", lfc_context,
							    ls_responsablePublicacion, lb_focus
							);

						if(!StringUtils.isValidString(ls_responsablePublicacion))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_RESPONSABLE_PUBLICACION);

						lth_turnoHistoria.setResponsablePublicacion(ls_responsablePublicacion);
					}

					BeanPublicacionAvisosWeb lbpdab_bean;
					lbpdab_bean     = (BeanPublicacionAvisosWeb)FacesUtils.obtenerInstanciaBean(
						    BeanPublicacionAvisosWeb.class, BeanNameCommon.BEAN_PUBLICACION_AVISOS_WEB
						);

					ls_return = lbpdab_bean.avanzarEtapa(lth_turnoHistoria);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("avanzarEtapa", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente("confrontacionForm:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Método encargado de buscar los documentos aportados.
	 *
	 * @return el valor de collection
	 */
	public Collection<AccCompletitudDocumental> buscarDocumentosAportados()
	{
		Collection<AccCompletitudDocumental> lcacd_documentosAportados;

		lcacd_documentosAportados = null;

		try
		{
			Turno lt_turno;

			lt_turno = getTurno();

			if(lt_turno != null)
				lcacd_documentosAportados = ipr_parameterRemote.buscarDocumentosAportados(
					    lt_turno.getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcacd_documentosAportados;
	}

	/**
	 * Metodo encargado de consultar todos los registros de LIQUIDACION_ITEM para un ID_LIQUIDACION específico.
	 */
	public void buscarLiquidacionItem()
	{
		try
		{
			Liquidacion      ll_liquidacion;
			DevolucionDinero ldd_devolucionDinero;

			ll_liquidacion           = new Liquidacion();
			ldd_devolucionDinero     = getDevolucionDinero();

			if(NumericUtils.getLong(getIdEtapa()) == EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO)
				ll_liquidacion.setIdTurno(ldd_devolucionDinero.getIdTurnoDevolucion());
			else
				ll_liquidacion.setIdTurno(getIdTurno());

			ll_liquidacion.setConsecutivo(1);

			setLiquidacionItem(
			    ilr_liquidacionRemote.buscarLiquidacionItem(
			        ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			setMostrarLiquidacionItem(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de buscar la información de una solicitud visita con el nombre de la persona que la realiza.
	 */
	public void buscarSolicitudConPersona()
	{
		List<Map<String, Object>> llmso_solicitudVisitas;

		llmso_solicitudVisitas = null;

		try
		{
			Turno lt_turno;

			lt_turno = getTurno();

			if(lt_turno != null)
				llmso_solicitudVisitas = ivr_visitasRemote.buscarSolicitudConPersona(
					    lt_turno.getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if(llmso_solicitudVisitas != null)
				setSolicitudVisitasPersona(llmso_solicitudVisitas);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de cambiar el tipo expediente si con base a la linea de producción.
	 *
	 * @param as_idLineaProduccion Contiene el id de la linea de producción seleccionada.
	 */
	public void cambiarTipoExpedienteSI(String as_idLineaProduccion)
	{
		if(StringUtils.isValidString(as_idLineaProduccion))
		{
			Collection<LineaProduccion> lclp_lineasProduccion;

			lclp_lineasProduccion = cargarTipoExpediente();

			if(CollectionUtils.isValidCollection(lclp_lineasProduccion))
			{
				boolean                   lb_validar;
				Iterator<LineaProduccion> lilp_iterator;

				lb_validar        = true;
				lilp_iterator     = lclp_lineasProduccion.iterator();

				while(lilp_iterator.hasNext() && lb_validar)
				{
					LineaProduccion llp_iterador;

					llp_iterador = lilp_iterator.next();

					if(llp_iterador != null)
					{
						String ls_idLineaProduccion;

						ls_idLineaProduccion = llp_iterador.getIdLineaProduccion();

						if(
						    StringUtils.isValidString(ls_idLineaProduccion)
							    && as_idLineaProduccion.equalsIgnoreCase(ls_idLineaProduccion)
						)
						{
							lb_validar = false;

							setNomenclaturaExpedienteSI(llp_iterador.getNomenclatura());
						}
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de validar la decisión seleccionada y efectuar una acción.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDecision()
	    throws B2BException
	{
		boolean lb_correcciones;
		long    ll_idEtapa;
		long    ll_idMotivo;

		ll_idEtapa          = NumericUtils.getLong(getIdEtapa());
		ll_idMotivo         = NumericUtils.getLong(getMotivoTramite());
		lb_correcciones     = ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES;

		setActuacionesAdministrativas(
		    (lb_correcciones) && (ll_idMotivo == MotivoTramiteCommon.ACTUACIONES_ADMINISTRATIVAS)
		);

		setAntiguoSistemaActuacionesAdministrativas(
		    (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
			    && (ll_idMotivo == MotivoTramiteCommon.ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS)
		);

		if(lb_correcciones && (ll_idMotivo == MotivoTramiteCommon.ENVIAR_A_GRABACION_MATRICULAS))
		{
			Turno lt_turno;

			lt_turno = getTurno();

			if(lt_turno != null)
			{
				DatosAntSistema ldas_datosAntSistema;

				ldas_datosAntSistema = getDatosAntSistema();

				if(ldas_datosAntSistema != null)
				{
					ldas_datosAntSistema.setIdCirculoGrabacion(lt_turno.getIdCirculo());
					setMostrarDatos130Grabacion(true);
				}
			}
		}
		else
			setMostrarDatos130Grabacion(false);

		setNomenclaturaExpedienteSI(null);
		setTipoExpedienteSI(null);
		setExpedienteSI(null);
		setExpedienteGenerado(false);

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_460)
			setMostrarDatos460ProcedeSI(ll_idMotivo == MotivoTramiteCommon.PROCEDE_SEGUNDA_INSTANCIA);
		else
			setMostrarDatos460ProcedeSI(false);

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_430)
			setEsSegundaInstanciaDialog(false);
	}

	/**
	 * Cargar justificacion cierre folio.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarJustificacionCierreFolio()
	    throws B2BException
	{
		String ls_idTurno;

		ls_idTurno = getIdTurno();

		if(StringUtils.isValidString(ls_idTurno))
		{
			CambioEstadoPredio lcep_justificacion;

			lcep_justificacion = new CambioEstadoPredio();

			lcep_justificacion.setIdTurno(ls_idTurno);

			lcep_justificacion = irr_aprobacionRemote.cargarJustificacionCierreFolio(lcep_justificacion);

			if(lcep_justificacion != null)
				setJustificacionCierreFolio(lcep_justificacion.getJustificacionCambio());
		}
	}

	/**
	 * Cargar parametrica usuario linea.
	 *
	 * @param lsaro_solicitudApoyoRegionalOrip de lsaro solicitud apoyo regional orip
	 * @return el valor de string
	 */
	public String cargarParametricaUsuarioLinea(SolicitudApoyoRegionalOrip lsaro_solicitudApoyoRegionalOrip)
	{
		String ls_returnPage;
		ls_returnPage = null;

		if(lsaro_solicitudApoyoRegionalOrip != null)
		{
			String ls_idCirculo;
			ls_idCirculo = lsaro_solicitudApoyoRegionalOrip.getIdCirculoRegional();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				BeanUsuarioLineas lbul_ul;
				FacesContext      lfc_context;

				lfc_context     = FacesUtils.getFacesContext();
				lbul_ul         = (BeanUsuarioLineas)lfc_context.getApplication()
						                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_USUARIO_LINEAS, BeanUsuarioLineas.class
						);

				lbul_ul.clear();
				lbul_ul.setVieneDeAprobacionApoyoNacional(true);
				lbul_ul.setIdCirculoOrip(ls_idCirculo);
				lbul_ul.cargarUsuarioLineasOripCalificador();

				ls_returnPage = NavegacionCommon.USUARIO_LINEAS;
			}
		}

		return ls_returnPage;
	}

	/**
	 * Cargar solicitudes regional orip en la pantalla.
	 */
	public void cargarSolicitudRegionalOrip()
	{
		Collection<SolicitudApoyoRegionalOrip> lcsaro_solicitudesRegionalOrip;

		lcsaro_solicitudesRegionalOrip = new ArrayList<SolicitudApoyoRegionalOrip>();

		try
		{
			Collection<Regional> lcr_regionales;
			long                 ll_contadorOrden;
			boolean              lb_seleccionado;

			lcr_regionales       = getRegionales();
			ll_contadorOrden     = 1;
			lb_seleccionado      = false;

			if(CollectionUtils.isValidCollection(lcr_regionales))
			{
				for(Regional lr_regional : lcr_regionales)
				{
					if((lr_regional != null) && lr_regional.isSeleccionado())
					{
						lb_seleccionado = true;

						CirculoRegistral             lcr_parametros;
						Collection<CirculoRegistral> lcr_circuloRegistral;
						lcr_parametros = new CirculoRegistral();

						lcr_parametros.setIdRegional(lr_regional.getIdRegional());
						lcr_circuloRegistral = irr_referenceRemote.findByIdRegionalOnly(
							    lcr_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(CollectionUtils.isValidCollection(lcr_circuloRegistral))
						{
							for(CirculoRegistral lcr_tmp : lcr_circuloRegistral)
							{
								if(lcr_tmp != null)
								{
									String ls_idCirculoOrip;

									ls_idCirculoOrip = StringUtils.getStringNotNull(lcr_tmp.getIdCirculo());

									if(
									    !ls_idCirculoOrip.equalsIgnoreCase(
										        StringUtils.getStringNotNull(getIdCirculoOripSolicitante())
										    )
									)
									{
										SolicitudApoyoRegionalOrip lsaro_regionalOrip;
										lsaro_regionalOrip = new SolicitudApoyoRegionalOrip();

										lsaro_regionalOrip.setIdCirculoRegional(ls_idCirculoOrip);
										lsaro_regionalOrip.setOrden(ll_contadorOrden);

										lsaro_regionalOrip.setIdRegional(lr_regional.getIdRegional());
										lsaro_regionalOrip.setNombreRegional(lr_regional.getNombre());
										lsaro_regionalOrip.setNombreOrip(lcr_tmp.getNombre());

										lcsaro_solicitudesRegionalOrip.add(lsaro_regionalOrip);

										ll_contadorOrden = ll_contadorOrden + 1;
									}
								}
							}
						}
					}
				}
			}

			if(!lb_seleccionado)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_REGIONAL);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		setSolicitudRegionalOrip(lcsaro_solicitudesRegionalOrip);
	}

	/**
	 * Método encargado de consultar los tipo expediente para la etapa actual.
	 *
	 * @return devuelve el valor de la colección de LineaProduccion
	 *
	 */
	public Collection<LineaProduccion> cargarTipoExpediente()
	{
		Collection<LineaProduccion> lclp_lineasProduccion;

		lclp_lineasProduccion = new LinkedList<LineaProduccion>();

		try
		{
			lclp_lineasProduccion = cargarTipoExpediente(NumericUtils.getLong(getIdEtapa()));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lclp_lineasProduccion;
	}

	/**
	      * Metodo encargado de cargar el panel de turnos tramite asociados.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarTurnosTramiteAsociados()
	    throws B2BException
	{
		List<Map<String, Object>> llmso_lmso;

		llmso_lmso = getTurnosEnCurso();

		if(CollectionUtils.isValidCollection(llmso_lmso))
			setTurnosEnCurso(llmso_lmso);
	}

	/**
	 * Cerrar grabacion correcciones.
	 *
	 * @return el valor de string
	 */
	public String cerrarGrabacionCorrecciones()
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		try
		{
			BeanCorreccion lbc_bean;

			lbc_bean = (BeanCorreccion)lfc_context.getApplication()
					                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
					);

			lbc_bean.setIdTurnoConsulta(null);
			lbc_bean.setNirConsulta(null);
			lbc_bean.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.ANALISIS_CORRECCION;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 *  {@inheritdoc}.
	 */
	public void clear()
	{
		setRegionales(null);
		setSolicitudRegionalOrip(null);
		setEsReasignarTurnosApoyoNac(false);
		setSolicitudesApoyoNacional(null);
		setSolicitudesRegionalOripGuardadas(false);
		setPersona(null);
		setHabilitarApoyoNacional(false);
		setPersonaTitularCuenta(null);
		setIdProceso(null);
		setEsSegundaInstanciaDialog(false);
		setEsSegundaInstancia(false);
		setEsProrrogaEntregaPruebas(false);
		setObservacionesEtapa130(null);
		setEsAntiguoSistema(false);
		setTurno(null);
		setDatosBasicos(false);
		setEliminaMatricula(false);
		setIdTurno(null);
		setInsertaMatricula(false);
		setSelectedConfrontacion(null);
		setVerificaFolioCerrado(false);
		setDocumentoGenerado(false);
		setBaldios(false);
		setZipFinal(null);
		setImagen(null);
		setPredio(null);
		setPredioDocumento(null);
		setDocumentos(null);
		setActos(null);
		setActosMayorValor(null);
		setAprobacion(null);
		setObservacionesAprobador(null);
		setDecisionAprobacion(null);
		setCausalesDevolucionesPanel(null);
		icmso_matriculasIndividuales        = null;
		icmso_matriculasRangos              = null;
		ilmso_matriculasTmpIndividuales     = null;
		ilmso_matriculasTmpRangos           = null;
		setTurnosEnCurso(null);
		setTurnosFechas(null);
		setActosFechas(null);
		setIndVinculado(false);
		setCausales(null);
		ib_mostrarDialog = false;
		setNegarSolicitudGrabacion(false);
		limpiarDiferencias();
		setTestamentos(false);
		setNombreTipoTestamento(null);
		setCategoriaTestamento(null);
	}

	/**
	 * Retorna el valor del objeto de List.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de List
	 */
	public List<String> completeText(String as_s)
	{
		List<String> lls_results;
		UIComponent  luc_uc;
		Long         ll_base;
		Long         ll_rangoInicio;
		Long         ll_rangoFin;
		String       ls_idMatricula;

		lls_results        = new ArrayList<String>();
		ls_idMatricula     = "";
		luc_uc             = UIComponent.getCurrentComponent(FacesUtils.getFacesContext());
		ll_base            = (Long)luc_uc.getAttributes().get("base");
		ll_rangoInicio     = (Long)luc_uc.getAttributes().get("rangoInicio");
		ll_rangoFin        = (Long)luc_uc.getAttributes().get("rangoFin");

		long ll_idMatriculaInicio;
		long ll_idMatriculaFin;

		if(NumericUtils.isValidLong(ll_rangoInicio, -1L))
			ll_idMatriculaInicio = NumericUtils.getLong(ll_rangoInicio);
		else
			ll_idMatriculaInicio = NumericUtils.DEFAULT_LONG_VALUE;

		ll_idMatriculaFin  = (ll_rangoFin != null) ? NumericUtils.getLong(ll_rangoFin) : NumericUtils.DEFAULT_LONG_VALUE;
		ls_idMatricula     = NumericUtils.getLongWrapper(ll_idMatriculaInicio).toString();

		if(StringUtils.isValidString(ls_idMatricula))
		{
			String ls_base;

			ls_base = (NumericUtils.isValidLong(ll_base) ? ll_base.toString() : "") + as_s;

			for(int i = NumericUtils.getInt(ll_idMatriculaInicio); i <= NumericUtils.getInt(ll_idMatriculaFin); i++)
			{
				String ls_matriculaActual;

				ls_matriculaActual = NumericUtils.getLongWrapper(i).toString();

				if(ls_matriculaActual.startsWith(ls_base))
					lls_results.add(ls_matriculaActual);
			}
		}

		return lls_results;
	}

	/**
	 * Confirmar traslados 660.
	 *
	 * @param ab_boolean de confirmacion del dialogo
	 * @return el valor de navegacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String confirmarTraslados660(boolean ab_boolean)
	    throws B2BException, IOException
	{
		setConfirmarTraslados660Dialog(ab_boolean);

		String ls_return;
		ls_return = enviaPagina();

		return ls_return;
	}

	/**
	 * Metodo encargado de consultar actos de devoluciones dinero.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaActosDevolucionDinero()
	    throws B2BException
	{
		String           ls_idTurno;
		DevolucionDinero ldd_devolucionDinero;

		ldd_devolucionDinero = getDevolucionDinero();

		if(ldd_devolucionDinero != null)
		{
			ls_idTurno = ldd_devolucionDinero.getIdTurnoDevolucion();

			if(StringUtils.isValidString(ls_idTurno))
			{
				DevolucionDineroUI lddui;

				lddui = idd_devolucionesDineroRemote.consultaBandejaDevolucion(
					    ls_idTurno, true, ldd_devolucionDinero, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lddui != null)
				{
					Collection<ActoDevolucionDinero> lcadd_actosDevDinero;

					lcadd_actosDevDinero = lddui.getActosDevolucionDinero();

					setActosDevolucionDinero(lcadd_actosDevDinero);

					if(!CollectionUtils.isValidCollection(lcadd_actosDevDinero))
						buscarLiquidacionItem();
				}
				else
					setActosDevolucionDinero(null);
			}
		}
	}

	/**
	 * Método para mostrar el detalle de la liquidación.
	 *
	 * @param aadd_add item de detalle
	 */
	public void consultaLiquidacion(ActoDevolucionDinero aadd_add)
	{
		if(aadd_add != null)
		{
			Collection<ActoDevolucionDinero> lcadd_cllTmp;

			lcadd_cllTmp = new ArrayList<ActoDevolucionDinero>();

			lcadd_cllTmp.add(aadd_add);

			setDevDineroTemporal(lcadd_cllTmp);

			PrimeFaces.current().executeScript("PF('dlgdetalleLiquidacion').show();");
			PrimeFaces.current().ajax().update("dlgdetalleLiq");
		}
	}

	/**
	 * Metodo encargado de consultar el SGD para traer los documentos del OWCC.
	 */
	public String consultaSGD()
	{
		String        ls_url;
		TurnoHistoria lth_historia;

		ls_url           = null;
		lth_historia     = getTurnoHistoria();

		if(lth_historia != null)
		{
			setNir(lth_historia.getNir());
			setIdTurnoConsulta(null);
		}
		else
		{
			Map<String, Object> lmso_predio;

			lmso_predio = getPredio();

			if(CollectionUtils.isValidCollection(lmso_predio))
			{
				setNir(StringUtils.getString(lmso_predio.get("NIR")));
				setIdTurnoConsulta(null);
			}
		}

		ls_url = consultaSGD(OperadorCommon.OR);

		return ls_url;
	}

	/**
	 * Metodo encargado de consultar el SGD para traer los documentos del OWCC.
	 */
	public void consultaSGDAbrirPestana()
	{
		consultaSGD(OperadorCommon.OR);
	}

	/**
	 * Consultar causales correcciones.
	 *
	 * @param lmso_param correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void consultarCausalesCorrecciones(Map<String, Object> lmso_param)
	{
		try
		{
			if(CollectionUtils.isValidCollection(lmso_param))
			{
				Collection<CausalCorreccion> lcc_causales;
				SolicitudCorreccion          lsc_solicitudCorreccion;
				String                       ls_idSolicitud;
				String                       ls_idCirculo;
				long                         ll_idMatricula;

				lsc_solicitudCorreccion     = new SolicitudCorreccion();
				ls_idSolicitud              = irr_registroRemote.findIdSolicitudByIdTurno(
					    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				ls_idCirculo                = null;
				ll_idMatricula              = 0;

				{
					Object lo_o;

					lo_o = lmso_param.get("MATRICULA");

					if(lo_o != null)
					{
						String ls_matricula;

						ls_matricula = StringUtils.getString(lo_o);

						if(StringUtils.isValidString(ls_matricula))
						{
							String[] lsa_data;

							lsa_data = StringUtils.getStringArray(ls_matricula, IdentificadoresCommon.SIMBOLO_GUION);

							if(CollectionUtils.isValidCollection(lsa_data) && (lsa_data.length > 1))
							{
								ls_idCirculo       = lsa_data[0];
								ll_idMatricula     = NumericUtils.getLong(lsa_data[1]);
							}
						}
					}
				}

				if(StringUtils.isValidString(ls_idSolicitud))
					lsc_solicitudCorreccion.setIdSolicitud(ls_idSolicitud);

				if(StringUtils.isValidString(ls_idCirculo))
					lsc_solicitudCorreccion.setIdCirculo(ls_idCirculo);

				if(ll_idMatricula > NumericUtils.DEFAULT_LONG_VALUE)
					lsc_solicitudCorreccion.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));

				lcc_causales = irr_registroRemote.findAllCausales(lsc_solicitudCorreccion);

				if(CollectionUtils.isValidCollection(lcc_causales))
					setCausales(lcc_causales);

				abrirDialogo(CS_dlgCorrecciones, CS_dlgCorrecciones);
			}
		}
		catch(B2BException lb2be_e)
		{
			cerrarDialogo(CS_dlgCorrecciones, CS_dlgCorrecciones);

			clh_LOGGER.error("consultarCausalesCorrecciones", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para consultar una persona con el id turno historia.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarPersona()
	    throws B2BException
	{
		setPersona(irr_calificacionRemote.findPersona(getIdTurnoHistoria()));
	}

	/**
	 * Método para consultar el titular cuenta bancaria devolución.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarTitularCuentaDevolucion()
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;
		lth_turnoHistoria = new TurnoHistoria();

		lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

		lth_turnoHistoria = irr_calificacionRemote.findTurnoHistoriaById(lth_turnoHistoria);

		if(lth_turnoHistoria != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				DevolucionDinero ldd_devolucionDinero;

				ldd_devolucionDinero = idd_devolucionesDineroRemote.findDevolucionDineroByIdSolicitud(ls_idSolicitud);

				setDevolucionDinero(ldd_devolucionDinero);

				if(ldd_devolucionDinero != null)
				{
					String ls_idPersonaTitular;

					ls_idPersonaTitular = ldd_devolucionDinero.getIdPersonaTitularCuenta();

					if(StringUtils.isValidString(ls_idPersonaTitular))
					{
						Persona lp_personaTitular;

						lp_personaTitular = new Persona();

						lp_personaTitular.setIdPersona(ls_idPersonaTitular);

						lp_personaTitular = irr_registroRemote.findPersonaByIdPersona(lp_personaTitular, getUserId());

						setPersonaTitularCuenta(lp_personaTitular);
					}
				}
			}
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
		char[] arrayChar;
		int    contador;
		String result;

		contador = 0;

		if(as_campo != null)
		{
			arrayChar     = as_campo.toCharArray();
			contador      = arrayChar.length;
		}

		result = Integer.toString(contador);

		return result;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_param correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	public String decisionResponsableCorrecciones(boolean ab_param)
	{
		String ls_return;

		ls_return = null;

		try
		{
			TurnoHistoria lth_turnoHistoria;
			String        ls_observaciones;
			MotivoTramite lmt_motivo;
			long          ll_idMotivo;

			lth_turnoHistoria     = new TurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			ls_observaciones      = getObservacionesAprobador();
			ll_idMotivo           = 0;

			if(!StringUtils.isValidString(ls_observaciones))
				throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
			else
				lth_turnoHistoria.setObservaciones(getObservacionesAprobador());

			if(ab_param)
				ll_idMotivo = MotivoTramiteCommon.DEVOLVER_AL_ANALISTA_CORRECCIONES;
			else
				ll_idMotivo = MotivoTramiteCommon.APROBAR_SECUENCIAS_132;

			lmt_motivo = new MotivoTramite(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES, ll_idMotivo);

			irr_aprobacionRemote.actualizarEtapaYCrearSiguiente(
			    lth_turnoHistoria, lmt_motivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				FacesContext              lfc_context;
				BeanResponsableCorreccion lbrc_bean;

				lfc_context     = FacesUtils.getFacesContext();
				lbrc_bean       = (BeanResponsableCorreccion)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_RESPONSABLE_CORRECCION, BeanResponsableCorreccion.class
						);

				lbrc_bean.clear();
				lbrc_bean.generarDatosTramiteCantidad();
			}

			ls_return = NavegacionCommon.RESPONSABLE_CORRECCION;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacionForm:idGrowl");
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Descargar archivo zip.
	 */
	public void descargarArchivoZip()
	{
		try
		{
			DocumentosSalida lds_parametros;
			byte[]           lba_zipfinal;

			lds_parametros = new DocumentosSalida();
			lds_parametros.setIdTurno(getPredio().get(IdentificadoresCommon.ID_TURNO).toString());

			lba_zipfinal = ier_entregaRemote.generarArchivoZip(
				    lds_parametros, getUserId(), false, getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_zipfinal != null)
			{
				InputStream lis_stream;

				lis_stream = new ByteArrayInputStream(lba_zipfinal);

				setImagen(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.ZIP, ConstanteCommon.NOMBRE_ZIP_GENERADO
				    )
				);
			}
			else
			{
				Object[] aoa_messageArgs = new String[1];
				aoa_messageArgs[0] = getIdTurno();
				throw new B2BException(ErrorKeys.NO_DOCUMENTOS_ACTIVOS, aoa_messageArgs);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleDescarga()
	{
		String ls_return;

		ls_return = null;

		try
		{
			boolean            lb_consultaTraza;
			FacesContext       lfc_context;
			Long               ll_idMatricula;
			String             ls_idCirculo;
			String             ls_idTurno;
			String             ls_tipoMatricula;
			BeanConsultaPredio lbcp_beanConsultaPredio;
			BeanDetallePredio  lbdp_beanDetallePredio;
			boolean            lb_validBeanConsultaPredio;
			boolean            lb_validDetallePredioBean;
			boolean            lb_definitiva;

			lb_consultaTraza               = false;
			lfc_context                    = FacesUtils.getFacesContext();
			ll_idMatricula                 = NumericUtils.getLongWrapper(
				    FacesUtils.getStringFacesParameter("matricula")
				);
			ls_idCirculo                   = FacesUtils.getStringFacesParameter("circulo");
			ls_idTurno                     = FacesUtils.getStringFacesParameter("idTurno");
			ls_tipoMatricula               = FacesUtils.getStringFacesParameter("tipoMatricula");
			lbcp_beanConsultaPredio        = (BeanConsultaPredio)lfc_context.getApplication()
					                                                            .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CONSULTA_PREDIO, BeanConsultaPredio.class
					);
			lbdp_beanDetallePredio         = (BeanDetallePredio)lfc_context.getApplication()
					                                                           .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
					);
			lb_validBeanConsultaPredio     = lbcp_beanConsultaPredio != null;
			lb_validDetallePredioBean      = lbdp_beanDetallePredio != null;
			lb_definitiva                  = StringUtils.isValidString(ls_tipoMatricula)
				? ls_tipoMatricula.equalsIgnoreCase("DEFINITIVA") : false;

			if(lbdp_beanDetallePredio != null)
				lb_consultaTraza = lbdp_beanDetallePredio.isVieneDeTrazabilidad();

			if(lb_consultaTraza)
			{
				if(lb_definitiva && lb_validBeanConsultaPredio && lb_validDetallePredioBean)
				{
					lbcp_beanConsultaPredio.setIdCirculo(ls_idCirculo);
					lbcp_beanConsultaPredio.setIdMatricula(ll_idMatricula);
					lbcp_beanConsultaPredio.setTipoMatricula(ls_tipoMatricula);
					lbcp_beanConsultaPredio.cargarTabsDetalle(true);

					ls_return = NavegacionCommon.CONSULTA_PREDIO_DETALLE;
				}
				else
				{
					if(lb_validDetallePredioBean)
					{
						Aprobacion la_aprobacion;

						la_aprobacion = getAprobacion();

						if((la_aprobacion == null) && !isRecepcionDeDocumentos())
						{
							la_aprobacion = new Aprobacion();

							la_aprobacion.setVieneDeCalificacion(true);
						}

						lbdp_beanDetallePredio.setIdEtapa(getIdEtapa());
						lbdp_beanDetallePredio.setRetornoBandejas(true);
						lbdp_beanDetallePredio.setIdCirculo(ls_idCirculo);
						lbdp_beanDetallePredio.setIdMatricula(ll_idMatricula);
						lbdp_beanDetallePredio.setIdTurno(ls_idTurno);
						lbdp_beanDetallePredio.setTipoMatricula(null);
						lbdp_beanDetallePredio.setRenderedBotonVolver(true);
						lbdp_beanDetallePredio.cargarTabsDetalle(ls_idCirculo, ll_idMatricula, true);
						lbdp_beanDetallePredio.setAprobacion(la_aprobacion);

						ls_return = lbdp_beanDetallePredio.cargarTabsDetalle(ls_tipoMatricula);
					}
				}
			}
			else if(lb_validBeanConsultaPredio && lb_validDetallePredioBean)
			{
				if(lb_definitiva)
				{
					Aprobacion la_aprobacion;

					la_aprobacion = getAprobacion();

					if((la_aprobacion == null) && !isRecepcionDeDocumentos())
					{
						la_aprobacion = new Aprobacion();

						la_aprobacion.setVieneDeCalificacion(true);
					}

					lbdp_beanDetallePredio.setIdEtapa(getIdEtapa());
					lbdp_beanDetallePredio.setRetornoBandejas(true);
					lbdp_beanDetallePredio.setIdCirculo(ls_idCirculo);
					lbdp_beanDetallePredio.setIdMatricula(ll_idMatricula);
					lbdp_beanDetallePredio.setIdTurno(ls_idTurno);
					lbdp_beanDetallePredio.setTipoMatricula(null);
					lbdp_beanDetallePredio.setRenderedBotonVolver(true);
					lbdp_beanDetallePredio.cargarTabsDetalle();
					lbdp_beanDetallePredio.setAprobacion(la_aprobacion);

					{
						String ls_idProceso;
						Long   ll_idEtapa;

						ls_idProceso     = getIdProceso();
						ll_idEtapa       = getIdEtapa();

						if(StringUtils.isValidString(ls_idProceso) && NumericUtils.isValidLong(ll_idEtapa))
						{
							long ll_idEtapaPrimitivo;

							ll_idEtapaPrimitivo = ll_idEtapa.longValue();

							lbdp_beanDetallePredio.setVolverDetallePredio(
							    (!isOcultarPaneles() || !isOcultarPanelesTraslados())
								    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
								    && (ll_idEtapaPrimitivo != EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL)
							);
						}
					}

					ls_return = lbdp_beanDetallePredio.cargarTabsDetalle(ls_tipoMatricula);
				}
				else
				{
					Aprobacion la_aprobacion;

					la_aprobacion = getAprobacion();

					if((la_aprobacion == null) && !isRecepcionDeDocumentos())
					{
						la_aprobacion = new Aprobacion();

						la_aprobacion.setVieneDeCalificacion(true);
					}

					lbdp_beanDetallePredio.setIdEtapa(getIdEtapa());
					lbdp_beanDetallePredio.setRetornoBandejas(true);
					lbdp_beanDetallePredio.setIdCirculo(ls_idCirculo);
					lbdp_beanDetallePredio.setIdMatricula(ll_idMatricula);
					lbdp_beanDetallePredio.setIdTurno(ls_idTurno);
					lbdp_beanDetallePredio.setTipoMatricula(null);
					lbdp_beanDetallePredio.setRenderedBotonVolver(true);
					lbdp_beanDetallePredio.cargarTabsDetalle(ls_idCirculo, ll_idMatricula, true);
					lbdp_beanDetallePredio.setAprobacion(la_aprobacion);

					ls_return = lbdp_beanDetallePredio.cargarTabsDetalle(ls_tipoMatricula);
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("detalleDescarga", le_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleDescargaRango()
	{
		String ls_return;
		ls_return = null;

		try
		{
			String ls_idTurno;
			String ls_llave;
			String ls_tipoMatricula;

			ls_llave             = FacesUtils.getStringFacesParameter("llave");
			ls_idTurno           = FacesUtils.getStringFacesParameter("idTurno");
			ls_tipoMatricula     = FacesUtils.getStringFacesParameter("tipoMatricula");

			if(StringUtils.isValidString(ls_llave))
			{
				if(StringUtils.isValidString(ls_tipoMatricula))
				{
					Collection<Map<String, Object>> llllhmso_rangos;

					llllhmso_rangos = null;

					if(ls_tipoMatricula.equalsIgnoreCase(IdentificadoresCommon.TEMPORAL))
						llllhmso_rangos = ilmso_matriculasTmpRangos;
					else if(ls_tipoMatricula.equalsIgnoreCase(IdentificadoresCommon.DEFINITIVA))
						llllhmso_rangos = icmso_matriculasRangos;

					if(CollectionUtils.isValidCollection(llllhmso_rangos))
					{
						boolean                       lb_llaveObtenida;
						long                          ll_default;
						long                          ll_llave;
						long                          ll_rangoInicio;
						long                          ll_filtro;
						long                          ll_rangoFin;
						Iterator<Map<String, Object>> li_rangos;
						String                        ls_idCirculo;

						lb_llaveObtenida     = false;
						ll_llave             = NumericUtils.getLong(ls_llave);
						ll_default           = -1L;
						ll_rangoInicio       = ll_default;
						ll_filtro            = ll_default;
						ll_rangoFin          = ll_default;
						li_rangos            = llllhmso_rangos.iterator();
						ls_idCirculo         = null;

						while(li_rangos.hasNext() && !lb_llaveObtenida)
						{
							Map<String, Object> lmso_rango;

							lmso_rango = li_rangos.next();

							if(lmso_rango != null)
							{
								if(lmso_rango.containsKey(IdentificadoresCommon.LLAVE))
								{
									long ll_llaveActual;

									ll_llaveActual = ll_default;

									{
										Object lo_llaveActual;
										lo_llaveActual = lmso_rango.get(IdentificadoresCommon.LLAVE);

										if((lo_llaveActual != null) && lo_llaveActual instanceof Long)
											ll_llaveActual = NumericUtils.getLong(((Long)lo_llaveActual));
									}

									lb_llaveObtenida = (ll_llaveActual > ll_default) && (ll_llaveActual == ll_llave);

									if(lb_llaveObtenida)
									{
										{
											Object lo_inicio;
											lo_inicio = lmso_rango.get(IdentificadoresCommon.RANGO_INICIO);

											if((lo_inicio != null) && lo_inicio instanceof Long)
												ll_rangoInicio = NumericUtils.getLong(((Long)lo_inicio));
										}

										{
											Object lo_final;
											lo_final = lmso_rango.get(IdentificadoresCommon.RANGO_FIN);

											if((lo_final != null) && lo_final instanceof Long)
												ll_rangoFin = NumericUtils.getLong(((Long)lo_final));
										}

										{
											Object lo_idCirculo;

											lo_idCirculo = lmso_rango.get(IdentificadoresCommon.ID_CIRCULO);

											if((lo_idCirculo != null) && lo_idCirculo instanceof String)
												ls_idCirculo = ((String)lo_idCirculo).toString();
										}

										{
											Object lo_filtro;

											lo_filtro = lmso_rango.get(IdentificadoresCommon.FILTRO_MATRICULA);

											if((lo_filtro != null) && lo_filtro instanceof String)
												ll_filtro = NumericUtils.getLong(
													    (NumericUtils.getLongWrapper((String)lo_filtro))
													);
										}
									}
								}
							}
						}

						if(lb_llaveObtenida)
						{
							if(ll_filtro > ll_default)
							{
								if(ll_filtro > ll_default)
								{
									if((ll_filtro >= ll_rangoInicio) && (ll_filtro <= ll_rangoFin))
									{
										FacesContext      lfc_context;
										BeanDetallePredio lobcp_bean;

										lfc_context     = FacesUtils.getFacesContext();

										lobcp_bean = (BeanDetallePredio)lfc_context.getApplication()
												                                       .evaluateExpressionGet(
												    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO,
												    BeanDetallePredio.class
												);

										if(lobcp_bean != null)
										{
											lobcp_bean.setIdEtapa(getIdEtapa());
											lobcp_bean.setRetornoBandejas(true);
											lobcp_bean.setIdCirculo(ls_idCirculo);
											lobcp_bean.setIdMatricula(NumericUtils.getLongWrapper(ll_filtro));
											lobcp_bean.setIdTurno(ls_idTurno);
											lobcp_bean.setTipoMatricula(null);
											lobcp_bean.setRenderedBotonVolver(true);

											ls_return = lobcp_bean.cargarTabsDetalle(ls_tipoMatricula);
										}
									}
									else
										throw new B2BException(
										    "la matricula ingresada no se encuentra dentro del rango establecido"
										);
								}
								else
									throw new B2BException("la matricula no es valida");
							}
							else
								throw new B2BException("no se encontro la matricula");
						}
					}
				}
			}
			else
				throw new B2BException("la matricula no debe estar vacia");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Detalle documento.
	 */
	public void detalleDocumento()
	{
		try
		{
			String ls_idTurno;

			ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

			if(StringUtils.isValidString(ls_idTurno))
			{
				setInfoDocumento(
				    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				        getUserId(), ls_idTurno, IdentificadoresCommon.DETALLE_DOCUMENTO
				    )
				);

				PrimeFaces.current().executeScript("PF('idDatosDocumento').show();");
				PrimeFaces.current().ajax().update("idDatosDocumento");
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("detalleDocumento", le_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String dirigirTrazabilidad()
	{
		String                   ls_return;
		FacesContext             lfc_context;
		BeanConsultaTrazabilidad lba_beanConsultaTrazabilidad;

		ls_return                        = NavegacionCommon.CONSULTA_TRAZABILIDAD;
		lfc_context                      = FacesUtils.getFacesContext();
		lba_beanConsultaTrazabilidad     = (BeanConsultaTrazabilidad)lfc_context.getApplication()
				                                                                    .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_CONSULTA_TRAZABILIDAD, BeanConsultaTrazabilidad.class
				);

		if((lba_beanConsultaTrazabilidad != null))
		{
			if(getPredio() != null)
			{
				String ls_idTurno;
				ls_idTurno = (String)getPredio().get("ID_TURNO");

				lba_beanConsultaTrazabilidad.setIdTurnoConsulta(ls_idTurno);
				lba_beanConsultaTrazabilidad.findAll();
			}
		}

		return ls_return;
	}

	/**
	 * Ejecutar traslados 660.
	 */
	public void ejecutarTraslados660()
	{
		setConfirmarTraslados660Dialog(true);
		setConfirmarTraslados660(true);
		setSeleccionTrasladoMatricula(null);
		setEjecucionMatriculasTraslado(null);
	}

	/**
	 * Método para poder redirigir al usuario al tramite y pagina correspondiente.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String enviaPagina()
	    throws B2BException, IOException
	{
		String               ls_result;
		FacesContext         lfc_context;
		RegistroCalificacion lorc_rc;

		ls_result       = null;
		lfc_context     = FacesUtils.getFacesContext();
		lorc_rc         = new RegistroCalificacion();

		try
		{
			String ls_motivo;

			ls_motivo = getMotivoTramite();

			if(!StringUtils.isValidString(ls_motivo))
				throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);
			else
			{
				long ll_idEtapa;
				long ll_idMotivo;

				MotivoTramite lmt_motivoTramite;
				String        ls_idTurno;

				ll_idEtapa      = NumericUtils.getLong(getIdEtapa());
				ll_idMotivo     = NumericUtils.getLong(ls_motivo);

				lmt_motivoTramite     = new MotivoTramite(ll_idEtapa, ll_idMotivo);
				ls_idTurno            = getIdTurno();

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
				{
					if(ll_idMotivo == MotivoTramiteCommon.ETAPA_415_ASIGNAR_A)
						ls_result = asignarA(ll_idEtapa);
					else if(ll_idMotivo == MotivoTramiteCommon.ETAPA_415_RECHAZA_RECURSO)
					{
						BeanRechazaRecurso lbrr_beanRechazaRecurso;

						lbrr_beanRechazaRecurso = (BeanRechazaRecurso)obtenerInstanciaBean(
							    BeanRechazaRecurso.class, BeanNameCommon.BEAN_RECHAZA_RECURSO
							);

						if(lbrr_beanRechazaRecurso != null)
						{
							lbrr_beanRechazaRecurso.clean();
							lbrr_beanRechazaRecurso.setEtapa460(false);
							lbrr_beanRechazaRecurso.setIdTurnoHistoria(getIdTurnoHistoria());
							lbrr_beanRechazaRecurso.setIdTurno(ls_idTurno);
							lbrr_beanRechazaRecurso.setIdMotivo(ll_idMotivo);
							lbrr_beanRechazaRecurso.limpiarInteresados();
							lbrr_beanRechazaRecurso.cargarDatosRechazaRecursos();
							lbrr_beanRechazaRecurso.buscarPersonasAsociadasRecursos();
							lbrr_beanRechazaRecurso.setRecursos(true);

							ls_result = NavegacionCommon.RECHAZA_RECURSO;
						}
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
				{
					if(ll_idMotivo == MotivoTramiteCommon.ASOCIAR_O_DESASOCIAR_MATRICULAS)
						ls_result = irModificarMatriculas(lmt_motivoTramite, lfc_context, ll_idEtapa);
					else
					{
						boolean lb_autoPruebas;

						lb_autoPruebas = ll_idMotivo == MotivoTramiteCommon.AUTO_DE_PRUEBAS;

						if(
						    (ll_idMotivo == MotivoTramiteCommon.AUTO_DE_ACLARACION) || lb_autoPruebas
							    || (ll_idMotivo == MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION)
							    || (ll_idMotivo == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION)
						)
						{
							TagPlantillaResolucion laa_actuacionesAdministrativas;

							laa_actuacionesAdministrativas = new TagPlantillaResolucion();

							laa_actuacionesAdministrativas.setIdTurno(ls_idTurno);
							laa_actuacionesAdministrativas.setIdMotivo(ll_idMotivo);

							laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
									.validarCondicionesDecision(
									    laa_actuacionesAdministrativas, getUserId(), getLocalIpAddress(),
									    getRemoteIpAddress()
									);

							if(laa_actuacionesAdministrativas != null)
							{
								String ls_tipoArchivo;
								String ls_texto;

								ls_tipoArchivo     = StringUtils.getStringNotNull(
									    laa_actuacionesAdministrativas.getTipoArchivo()
									);
								ls_texto           = StringUtils.getStringNotNull(
									    laa_actuacionesAdministrativas.getTexto()
									);

								if(ls_tipoArchivo.contains("_") && ls_texto.contains("_"))
								{
									Object[] loa_object;

									loa_object     = new String[2];

									loa_object[0]     = ls_tipoArchivo.replace("_", " ");
									loa_object[1]     = ls_texto.replace("_", " ");

									throw new B2BException(
									    ErrorKeys.ERROR_NO_EXISTE_DECISION_PARA_REALIZAR_TRAMITE, loa_object
									);
								}
							}
						}

						if(lb_autoPruebas && !isEsActuacionesAdministrativasDialog())
							PrimeFaces.current().executeScript("PF('dlgActuacionesAdministrativas').show();");

						if(ll_idMotivo == MotivoTramiteCommon.APRUEBA_PRORROGA_DOCUMENTACION)
						{
							if(iaar_actuacionesAdministrativasRemote.prorrogaDocumentacion(getIdTurnoHistoria()))
							{
								TurnoHistoria lth_param;

								lth_param = new TurnoHistoria();

								lth_param.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

								if(
								    iaar_actuacionesAdministrativasRemote.enviarAprobarProrrogaDocumentacion(
									        lth_param, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									    )
								)
								{
									BeanSustanciadorActuacionesAdmin lbc_bean;

									lbc_bean = (BeanSustanciadorActuacionesAdmin)obtenerInstanciaBean(
										    BeanSustanciadorActuacionesAdmin.class,
										    BeanNameCommon.BEAN_SUSTANCIADOR_ACTUACIONES_ADMIN
										);

									lbc_bean.clear();
									lbc_bean.generarDatosTramiteCantidad();
									lbc_bean.generarGraficoDeTorta(
									    EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS, false
									);

									ls_result = NavegacionCommon.SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_PRORROGA_APROBAR);
						}
						else if(
						    (!lb_autoPruebas || isEsActuacionesAdministrativasDialog())
							    && (ll_idMotivo != MotivoTramiteCommon.ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS)
						)
						{
							BeanActuacionesAdministrativas lbaa_beanActuacionesAdministrativas;

							lbaa_beanActuacionesAdministrativas = (BeanActuacionesAdministrativas)obtenerInstanciaBean(
								    BeanActuacionesAdministrativas.class,
								    BeanNameCommon.BEAN_ACTUACIONES_ADMINISTRATIVAS
								);

							if(lbaa_beanActuacionesAdministrativas != null)
							{
								lbaa_beanActuacionesAdministrativas.clean();
								lbaa_beanActuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
								lbaa_beanActuacionesAdministrativas.setIdTurno(ls_idTurno);
								lbaa_beanActuacionesAdministrativas.setIdMotivo(ll_idMotivo);
								lbaa_beanActuacionesAdministrativas.cargarDatosActuacionesAdministrativas();
								lbaa_beanActuacionesAdministrativas.agregarTerceroDeterminado();

								{
									boolean lb_esActuacionesAdministrativas;

									lb_esActuacionesAdministrativas = isEsActuacionesAdministrativas();

									if(lb_esActuacionesAdministrativas)
										lbaa_beanActuacionesAdministrativas.cargarInfoTiposDocumentalActuacionesAdmin();

									lbaa_beanActuacionesAdministrativas.setMostrarSolicitudDocumentacion(
									    lb_esActuacionesAdministrativas
									);
								}

								ls_result = NavegacionCommon.ACTUACIONES_ADMINISTRATIVAS;
							}
						}

						if(ll_idMotivo == MotivoTramiteCommon.ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS)
						{
							TagPlantillaResolucion laa_actuacionesAdministrativas;

							laa_actuacionesAdministrativas = new TagPlantillaResolucion();

							laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
							laa_actuacionesAdministrativas.setIdTurno(ls_idTurno);

							{
								String ls_observaciones;

								ls_observaciones = getObservacionesEtapa130();

								validateStyles(
								    "confrontacionForm:idObservacionesEtapa130", lfc_context, ls_observaciones, true
								);
								laa_actuacionesAdministrativas.setObservaciones(ls_observaciones);
							}

							iaar_actuacionesAdministrativasRemote.enviarResponsableActuacionesAdmin(
							    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);

							{
								BeanTurnos lbt_bean;

								lbt_bean = (BeanTurnos)obtenerInstanciaBean(
									    BeanTurnos.class, BeanNameCommon.BEAN_TURNOS
									);

								lbt_bean.setNirConsulta(null);
								lbt_bean.setIdTurnoConsulta(null);
								lbt_bean.generarData();

								ls_result = NavegacionCommon.TURNOS;
							}
						}
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_460)
				{
					if(ll_idMotivo == MotivoTramiteCommon.ASOCIAR_Y_O_DESASOCIAR_MATRICULAS)
						ls_result = irModificarMatriculas(lmt_motivoTramite, lfc_context, ll_idEtapa);
					else if(ll_idMotivo == MotivoTramiteCommon.ASIGNAR_A_460)
						ls_result = asignarA(ll_idEtapa);
					else if(ll_idMotivo == MotivoTramiteCommon.PROCEDE_SEGUNDA_INSTANCIA)
					{
						String ls_tipoExpedienteSI;

						ls_tipoExpedienteSI = getTipoExpedienteSI();

						if(!StringUtils.isValidString(ls_tipoExpedienteSI))
							throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_EXPENDIENTE);

						setExpedienteGenerado(false);
						PrimeFaces.current().executeScript("PF('dlgProcedeSI').show();");
					}
					else if(ll_idMotivo == MotivoTramiteCommon.RECHAZO_DE_RECURSO_POR_LEY)
					{
						BeanRechazaRecurso lbrr_beanRechazaRecurso;

						lbrr_beanRechazaRecurso = (BeanRechazaRecurso)obtenerInstanciaBean(
							    BeanRechazaRecurso.class, BeanNameCommon.BEAN_RECHAZA_RECURSO
							);

						if(lbrr_beanRechazaRecurso != null)
						{
							lbrr_beanRechazaRecurso.clean();
							lbrr_beanRechazaRecurso.setEtapa460(true);
							lbrr_beanRechazaRecurso.setIdTurnoHistoria(getIdTurnoHistoria());
							lbrr_beanRechazaRecurso.setIdTurno(ls_idTurno);
							lbrr_beanRechazaRecurso.setIdMotivo(ll_idMotivo);
							lbrr_beanRechazaRecurso.limpiarInteresados();
							lbrr_beanRechazaRecurso.cargarDatosRechazaRecursos();
							lbrr_beanRechazaRecurso.buscarPersonasAsociadasRecursos();

							ls_result = NavegacionCommon.RECHAZA_RECURSO;
						}
					}
					else if(ll_idMotivo == MotivoTramiteCommon.NO_PROCEDE_SEGUNDA_INSTANCIA)
					{
						BeanNoProcedeSegundaInstancia lbnpsi_bean;

						lbnpsi_bean = (BeanNoProcedeSegundaInstancia)lfc_context.getApplication()
								                                                    .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_NO_PROCEDE_SEGUNDA_INSTANCIA,
								    BeanNoProcedeSegundaInstancia.class
								);

						if(lbnpsi_bean != null)
						{
							TurnoHistoria lth_turnoHistoria = new TurnoHistoria();
							BeanDireccion lbd_beanDireccion = getBeanDireccion();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
							lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								lbnpsi_bean.setTurnoHistoria(lth_turnoHistoria);
								lbnpsi_bean.setBandejaentrada(true);
								lbnpsi_bean.setConsultaIndividual(StringUtils.isValidString(getIdTurnoConsulta()));
								lbnpsi_bean.setData(null);
								lbnpsi_bean.setIdTurnoHistoria(String.valueOf(lth_turnoHistoria.getIdTurnoHistoria()));
								lbnpsi_bean.consultarPersonaInteresado();
								lbnpsi_bean.setDocumentoGenerado(false);
								lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
								lbd_beanDireccion.setDeshabilitarResidencia(true);
								lbnpsi_bean.setDatosGuardados(false);
								lbnpsi_bean.generarMigaPan(ls_motivo);
								lbnpsi_bean.setMotivoTramite(ls_motivo);
								lbnpsi_bean.setIdTurno(ls_idTurno);
								lbnpsi_bean.setEtapa(ll_idEtapa);
								lbnpsi_bean.setIdEtapa(ll_idEtapa);
								lbnpsi_bean.setMostrarDescargarZip(false);
								lbnpsi_bean.setObservaciones(getObservacionesEtapa130());
								lbnpsi_bean.setSegundaInstancia(true);
								lbnpsi_bean.cargarDatosActuacionesAdministrativas();

								String ls_idSubproceso = lth_turnoHistoria.getIdSubproceso();

								Suspension ls_parametros;
								OficiosTexto lot_data;

								ls_parametros          = getParametros();

								if(ls_parametros == null)
									ls_parametros = new Suspension();

								lot_data = icr_calificacionRemote.cargarOficioTextoTraslados(
									    getIdTurno(), getMotivoTramite()
									);

								if(lot_data != null)
									ls_parametros.setOficiosTexto(lot_data);

								if(StringUtils.isValidString(ls_idSubproceso))
									lbnpsi_bean.setProyectarResolucion(true);

								lbnpsi_bean.generarData();

								ls_result = NavegacionCommon.NO_PROCEDE_SEGUNDA_INSTANCIA;
							}
						}
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
				{
					boolean lb_autoPruebas;
					boolean lb_esActuacionesAdministrativasDialog;

					lb_autoPruebas                            = ll_idMotivo == MotivoTramiteCommon.ETAPA_410_AUTO_DE_APERTURA_DE_PRUEBAS;
					lb_esActuacionesAdministrativasDialog     = isEsActuacionesAdministrativasDialog();

					if(lb_autoPruebas && !lb_esActuacionesAdministrativasDialog)
						PrimeFaces.current().executeScript("PF('dlgActuacionesAdministrativas').show();");
					else if(
					    (!lb_autoPruebas || lb_esActuacionesAdministrativasDialog)
						    && (ll_idMotivo != MotivoTramiteCommon.ANTIGUO_SISTEMA_ACTUACIONES_ADMINISTRATIVAS)
					)
					{
						BeanResolucionRecurso lbrr_beanResolucionRecurso;

						lbrr_beanResolucionRecurso = (BeanResolucionRecurso)obtenerInstanciaBean(
							    BeanResolucionRecurso.class, BeanNameCommon.BEAN_RESOLUCION_RECURSO
							);

						if(lbrr_beanResolucionRecurso != null)
						{
							lbrr_beanResolucionRecurso.clean();
							lbrr_beanResolucionRecurso.setIdTurnoHistoria(getIdTurnoHistoria());
							lbrr_beanResolucionRecurso.setIdTurno(ls_idTurno);
							lbrr_beanResolucionRecurso.setIdMotivo(ll_idMotivo);
							lbrr_beanResolucionRecurso.limpiarInteresados();
							lbrr_beanResolucionRecurso.cargarDatosResolucionRecursos();
							lbrr_beanResolucionRecurso.buscarPersonasAsociadasRecursos();
							lbrr_beanResolucionRecurso.agregarTerceroDeterminado();
							lbrr_beanResolucionRecurso.setRecursos(true);

							{
								boolean lb_esActuacionesAdministrativas;

								lb_esActuacionesAdministrativas = isEsActuacionesAdministrativas();

								if(lb_esActuacionesAdministrativas)
									lbrr_beanResolucionRecurso.cargarInfoTiposDocumentalActuacionesAdmin();

								lbrr_beanResolucionRecurso.setMostrarSolicitudDocumentacion(
								    lb_esActuacionesAdministrativas
								);
							}

							ls_result = NavegacionCommon.RESOLUCION_RECURSO;
						}
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO)
				{
					BeanActoAdministrativoDetalle lbaad_beanActoAdministrativoDetalle;

					lbaad_beanActoAdministrativoDetalle = (BeanActoAdministrativoDetalle)obtenerInstanciaBean(
						    BeanActoAdministrativoDetalle.class, BeanNameCommon.BEAN_ACTO_ADMINISTRATIVO_DETALLE
						);

					if(lbaad_beanActoAdministrativoDetalle != null)
					{
						lbaad_beanActoAdministrativoDetalle.clean();
						lbaad_beanActoAdministrativoDetalle.setIdTurnoHistoria(getIdTurnoHistoria());
						lbaad_beanActoAdministrativoDetalle.setIdTurno(ls_idTurno);
						lbaad_beanActoAdministrativoDetalle.setIdMotivo(NumericUtils.getLongWrapper(ll_idMotivo));
						lbaad_beanActoAdministrativoDetalle.setMotivoTramite(StringUtils.getString(ll_idMotivo));
						lbaad_beanActoAdministrativoDetalle.consultarPersonaInteresadoActo();
						lbaad_beanActoAdministrativoDetalle.cargarDatosActoAdministrativo();

						ls_result = NavegacionCommon.ACTO_ADMINISTRATIVO_DETALLE;
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
				{
					if(ll_idMotivo == MotivoTramiteCommon.ASOCIAR_O_DESASOCIAR_MATRICULAS)
						ls_result = irModificarMatriculas(lmt_motivoTramite, lfc_context, ll_idEtapa);
					else if(ll_idMotivo == MotivoTramiteCommon.ASIGNAR_A)
						ls_result = asignarA(ll_idEtapa);
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
				{
					TurnoHistoria lth_th1;
					lth_th1 = new TurnoHistoria();

					lth_th1.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

					lth_th1 = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th1);

					if(lth_th1 != null)
					{
						TurnoHistoria lth_tmpAnt;

						lth_tmpAnt = new TurnoHistoria();

						lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th1.getIdAnterior()));

						lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

						if(lth_tmpAnt != null)
						{
							SolicitudAsociada lsa_solicitudAsociada;
							Solicitud         ls_solicitud;
							String            ls_proceso;

							ls_solicitud     = new Solicitud();
							ls_proceso       = null;

							ls_solicitud.setIdSolicitud(lth_tmpAnt.getIdSolicitud());

							lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociada(ls_solicitud);

							if(lsa_solicitudAsociada != null)
							{
								Solicitud ls_solicitud2;

								ls_solicitud2 = new Solicitud();

								ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
								ls_solicitud2.setDocumento(false);

								ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

								if(ls_solicitud2 != null)
									ls_proceso = ls_solicitud2.getIdProceso();
							}
							else
							{
								ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

								if(ls_solicitud != null)
									ls_proceso = ls_solicitud.getIdProceso();
							}

							if(StringUtils.isValidString(ls_proceso))
							{
								String  ls_idTurnoHistoria;
								boolean lb_antiguoSistema;
								boolean lb_motivoDigitalizacion;
								boolean lb_motivoLiquidacion;
								boolean lb_motivoAntiguoSistema;

								ls_idTurnoHistoria          = getIdTurnoHistoria();
								lb_antiguoSistema           = irr_registroRemote.validarCriterioPorTipoCriterio(
									    NumericUtils.getLongWrapper(ls_idTurnoHistoria),
									    TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA, getUserId(), getRemoteIpAddress(),
									    getLocalIpAddress()
									);
								lb_motivoDigitalizacion     = ll_idMotivo == MotivoTramiteCommon.ETAPA_350_DIGITALIZACION;
								lb_motivoLiquidacion        = ll_idMotivo == MotivoTramiteCommon.ETAPA_350_GENERAR_LIQUIDACION;
								lb_motivoAntiguoSistema     = ll_idMotivo == MotivoTramiteCommon.ETAPA_350_BUSCAR_EN_ANTIGUO_SISTEMA;

								if((lb_motivoDigitalizacion || lb_motivoLiquidacion) && lb_antiguoSistema)
									throw new B2BException(ErrorKeys.ERROR_CRITERIO_ANTIGUO_SISTEMA);

								if(lb_motivoAntiguoSistema && !lb_antiguoSistema)
									throw new B2BException(ErrorKeys.ERROR_CRITERIO_NO_ES_ANTIGUO_SISTEMA);

								if(lb_motivoDigitalizacion)
								{
									BeanDigitalizacionCopias lbdc_bean;

									lbdc_bean = (BeanDigitalizacionCopias)obtenerInstanciaBean(
										    BeanDigitalizacionCopias.class, BeanNameCommon.BEAN_DIGITALIZACION_COPIAS
										);

									if(lbdc_bean != null)
									{
										lbdc_bean.clear();
										lbdc_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
										lbdc_bean.setIdTurno(getIdTurno());
										lbdc_bean.setIdEtapa(
										    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
										);
										lbdc_bean.setIdMotivo(ll_idMotivo);
										lbdc_bean.cargarInformacionDigitalizacionCopias();

										ls_result = NavegacionCommon.DIGITALIZACION_COPIAS;
									}
								}
								else if(lb_motivoLiquidacion)
								{
									BeanLiquidacionCopias lblc_bean;

									lblc_bean = (BeanLiquidacionCopias)obtenerInstanciaBean(
										    BeanLiquidacionCopias.class, BeanNameCommon.BEAN_LIQUIDACION_COPIAS
										);

									if(lblc_bean != null)
									{
										lblc_bean.clear();
										lblc_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
										lblc_bean.setIdTurno(getIdTurno());
										lblc_bean.setIdEtapa(
										    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
										);
										lblc_bean.setIdMotivo(ll_idMotivo);
										lblc_bean.cargarInformacionDigitalizacionCopias();
										lblc_bean.setDocumentosOWCC(null);

										ls_result = NavegacionCommon.LIQUIDACION_COPIAS;
									}
								}
								else if(ll_idMotivo == MotivoTramiteCommon.ETAPA_350_NOTA_DE_NEGACION_COPIAS)
								{
									BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

									lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
											                                                                .evaluateExpressionGet(
											    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
											    BeanRecepcionDocumentos.class
											);

									if(lbsdt_beanRecepcionDocumentos != null)
									{
										lbsdt_beanRecepcionDocumentos.clear(true);
										lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th1);
										lbsdt_beanRecepcionDocumentos.setParametros(null);
										lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
										lbsdt_beanRecepcionDocumentos.setProceso(ls_proceso);
										lbsdt_beanRecepcionDocumentos.setEtapa(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS);
										lbsdt_beanRecepcionDocumentos.setEsAnalistaCopias(true);
										lbsdt_beanRecepcionDocumentos.setIdTurno(ls_idTurno);
										ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
									}
								}
								else if(lb_motivoAntiguoSistema)
								{
									BeanBuscarAntiguoSistema lbccpc_bean;

									lbccpc_bean = (BeanBuscarAntiguoSistema)obtenerInstanciaBean(
										    BeanBuscarAntiguoSistema.class, BeanNameCommon.BEAN_BUSCAR_ANTIGUO_SISTEMA
										);

									if(lbccpc_bean != null)
									{
										lbccpc_bean.clear();
										lbccpc_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
										lbccpc_bean.setIdTurno(getIdTurno());
										lbccpc_bean.setIdEtapa(
										    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
										);
										lbccpc_bean.setIdCirculo();
										lbccpc_bean.setIdMotivo(ll_idMotivo);
										lbccpc_bean.cargarInformacionAntiguoSistema();
										lbccpc_bean.generarDatosDocumento();

										ls_result = NavegacionCommon.BUSCAR_ANTIGUO_SISTEMA;
									}
								}
							}
						}
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
				{
					TurnoHistoria lth_th;

					lth_th = new TurnoHistoria();

					lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

					lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

					if(ll_idMotivo == MotivoTramiteCommon.ACTUACIONES_ADMINISTRATIVAS)
					{
						String ls_observaciones130;

						ls_observaciones130 = getObservacionesEtapa130();

						validateStyles(cs_ID_FORM + ":idObservacionesEtapa130", lfc_context, ls_observaciones130, true);

						lth_th.setObservaciones(ls_observaciones130);

						irr_calificacionRemote.enviarResponsableActuacionesAdministrativas(
						    lth_th, lmt_motivoTramite, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

						{
							BeanTurnos lbt_beanTurnos;

							lbt_beanTurnos = (BeanTurnos)lfc_context.getApplication()
									                                    .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
									);

							lbt_beanTurnos.generarData();

							ls_result = NavegacionCommon.TURNOS;
						}
					}
					else if(
					    (ll_idMotivo == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS)
						    || (ll_idMotivo == MotivoTramiteCommon.APROBAR_PRORROGA_DOCUMENTOS)
						    || (ll_idMotivo == MotivoTramiteCommon.PROCESO_CORRECCIONES)
					)
					{
						if(lth_th != null)
						{
							TurnoHistoria lth_tmpAnt;
							String        ls_proceso;

							ls_proceso     = null;
							lth_tmpAnt     = new TurnoHistoria();

							lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

							lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

							if(lth_tmpAnt != null)
							{
								SolicitudAsociada lsa_solicitudAsociada;
								Solicitud         ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(lth_tmpAnt.getIdSolicitud());

								lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociada(ls_solicitud);

								if(lsa_solicitudAsociada != null)
								{
									Solicitud ls_solicitud2;

									ls_solicitud2 = new Solicitud();

									ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
									ls_solicitud2.setDocumento(false);

									ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

									if(ls_solicitud2 != null)
										ls_proceso = ls_solicitud2.getIdProceso();
								}
								else
								{
									ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

									if(ls_solicitud != null)
										ls_proceso = ls_solicitud.getIdProceso();
								}

								if(
								    StringUtils.isValidString(ls_proceso)
									    && ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41)
								)
								{
									if(
									    (ll_idMotivo == MotivoTramiteCommon.APROBAR_PRORROGA_DOCUMENTOS)
										    || (ll_idMotivo == MotivoTramiteCommon.PROCESO_CORRECCIONES)
									)
									{
										boolean lb_observaciones;
										boolean lb_focus;
										String  ls_observaciones;

										lb_observaciones     = true;
										lb_focus             = true;
										ls_observaciones     = getObservacionesEtapa130();

										{
											BeanAprobacion lba_beanAprobacion;

											lba_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
													                                            .evaluateExpressionGet(
													    lfc_context, BeanNameCommon.BEAN_APROBACION,
													    BeanAprobacion.class
													);

											if(lba_beanAprobacion != null)
												lb_observaciones = lba_beanAprobacion.isVieneDeCorrecciones();
										}

										if(!lb_observaciones)
										{
											lb_focus = validateStyles(
												    "confrontacionForm:idObservacionesEtapa130", lfc_context,
												    ls_observaciones, lb_focus
												);

											if(!StringUtils.isValidString(ls_observaciones))
												throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
											else if(ls_observaciones.length() < 100)
											{
												lb_focus = validateStyles(
													    "confrontacionForm:idObservacionesEtapa130", lfc_context, "",
													    lb_focus
													);
												throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_100);
											}
										}

										if(ls_observaciones != null)
											lth_th.setObservaciones(ls_observaciones);

										irr_calificacionRemote.enviarAprobarProrrogaDocumentos(
										    lth_th, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
										);

										{
											BeanCorreccion lbc_bean;

											lbc_bean = (BeanCorreccion)lfc_context.getApplication()
													                                  .evaluateExpressionGet(
													    lfc_context, BeanNameCommon.BEAN_CORRECCION,
													    BeanCorreccion.class
													);

											if(lbc_bean != null)
											{
												lbc_bean.setIdTurnoConsulta(null);
												lbc_bean.setNirConsulta(null);
												lbc_bean.generarDatosTramiteCantidad();
												ls_result = NavegacionCommon.ANALISIS_CORRECCION;
											}
										}
									}
									else if(ll_idMotivo == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS)
									{
										BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

										lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
												                                                                .evaluateExpressionGet(
												    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
												    BeanRecepcionDocumentos.class
												);

										if(lbsdt_beanRecepcionDocumentos != null)
										{
											lbsdt_beanRecepcionDocumentos.clear(true);
											lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
											lbsdt_beanRecepcionDocumentos.setParametros(null);
											lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
											lbsdt_beanRecepcionDocumentos.setProceso(ls_proceso);
											lbsdt_beanRecepcionDocumentos.setEtapa(EtapaCommon.ID_ETAPA_CORRECCIONES);
											ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
										}
									}
								}
								else
									throw new B2BException(
									    ErrorKeys.OPCION_SELECCIONADA_NO_VALIDA_PARA_PRORROGA_ENTREGA_DOC
									);
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
					else if(ll_idMotivo == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION)
					{
						if(lth_th != null)
						{
							TurnoHistoria lth_tmpAnt;
							String        ls_proceso;

							ls_proceso     = null;
							lth_tmpAnt     = new TurnoHistoria();

							lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

							lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

							if(lth_tmpAnt != null)
							{
								SolicitudAsociada lsa_solicitudAsociada;
								Solicitud         ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(lth_tmpAnt.getIdSolicitud());

								lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociada(ls_solicitud);

								if(lsa_solicitudAsociada != null)
								{
									Solicitud ls_solicitud2;

									ls_solicitud2 = new Solicitud();

									ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
									ls_solicitud2.setDocumento(false);

									ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

									if(ls_solicitud2 != null)
										ls_proceso = ls_solicitud2.getIdProceso();
								}
								else
								{
									ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

									if(ls_solicitud != null)
										ls_proceso = ls_solicitud.getIdProceso();
								}

								if(StringUtils.isValidString(ls_proceso))
								{
									BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

									lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
											                                                                .evaluateExpressionGet(
											    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
											    BeanRecepcionDocumentos.class
											);

									if(lbsdt_beanRecepcionDocumentos != null)
									{
										lbsdt_beanRecepcionDocumentos.clear(true);
										lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
										lbsdt_beanRecepcionDocumentos.setParametros(null);
										lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
										lbsdt_beanRecepcionDocumentos.setEtapa(EtapaCommon.ID_ETAPA_CORRECCIONES);
										lbsdt_beanRecepcionDocumentos.setEsProcedeNoCorreccion(true);
										ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
					else if(ll_idMotivo == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES)
					{
						if(lth_th != null)
						{
							TurnoHistoria lth_tmpAnt;

							lth_tmpAnt = new TurnoHistoria();

							lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

							lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

							if(lth_tmpAnt != null)
							{
								BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

								lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
										                                                                .evaluateExpressionGet(
										    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
										    BeanRecepcionDocumentos.class
										);

								if(lbsdt_beanRecepcionDocumentos != null)
								{
									lbsdt_beanRecepcionDocumentos.clear(true);
									lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
									lbsdt_beanRecepcionDocumentos.setParametros(null);
									lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
									lbsdt_beanRecepcionDocumentos.setEsSolicitudDocumentacion(true);
									lbsdt_beanRecepcionDocumentos.setEtapa(EtapaCommon.ID_ETAPA_CORRECCIONES);
									ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
					else if(ll_idMotivo == MotivoTramiteCommon.MODIFICAR_MATRICULAS)
						ls_result = irModificarMatriculas(lmt_motivoTramite, lfc_context, ll_idEtapa);
					else if(ll_idMotivo == MotivoTramiteCommon.PERMISOS_PARA_CORRECCIONES)
					{
						BeanPermisosCorrecciones lbpc_bean;
						lbpc_bean = (BeanPermisosCorrecciones)lfc_context.getApplication()
								                                             .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_PERMISOS_CORRECCIONES,
								    BeanPermisosCorrecciones.class
								);

						if(lbpc_bean != null)
						{
							lbpc_bean.clean();
							lbpc_bean.setIdMotivo(ll_idMotivo);
							lbpc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
							lbpc_bean.setIdTurno(getIdTurno());
							lbpc_bean.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapa));
							lbpc_bean.setMostrarBandeja(true);
							lbpc_bean.setMostrarDetalle(false);
							lbpc_bean.datosCorrecciones();
							lbpc_bean.setPredio(getPredio());

							ls_result = NavegacionCommon.PERMISOS_CONFRONTACION;
						}
					}
					else if(ll_idMotivo == MotivoTramiteCommon.ANTIGUO_SISTEMA_CORRECCIONES)
						ls_result = iniciarAntSistemaCalificacion(lfc_context, true);
					else if(ll_idMotivo == MotivoTramiteCommon.ENVIAR_A_GRABACION_MATRICULAS)
					{
						DatosAntSistema ldas_data;

						ldas_data = getDatosAntSistema();

						if(ldas_data != null)
						{
							Long     ll_idMatricula;
							Registro lr_data;
							String   ls_idCirculo;
							String   ls_tipoPredio;
							String   ls_departamento;
							String   ls_idMunicipio;
							String   ls_nombrePredio;
							String   ls_observaciones;

							ll_idMatricula       = ldas_data.getIdMatriculaGrabacion();
							ls_idCirculo         = ldas_data.getIdCirculoGrabacion();
							ls_tipoPredio        = ldas_data.getIdTipoPredio();
							ls_departamento      = ldas_data.getIdDepartamento();
							ls_idMunicipio       = ldas_data.getIdMunicipio();
							ls_nombrePredio      = ldas_data.getNombrePredio();
							ls_observaciones     = getObservacionesGrabacionMatriculas();
							lr_data              = new Registro();

							if(!StringUtils.isValidString(ls_idCirculo))
								throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

							if(!NumericUtils.isValidLong(ll_idMatricula))
								throw new B2BException(ErrorKeys.INGRESE_MATRICULA_VALIDA);

							if(!StringUtils.isValidString(ls_tipoPredio))
								throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

							if(!StringUtils.isValidString(ls_departamento))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);

							if(!StringUtils.isValidString(ls_idMunicipio))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

							if(!StringUtils.isValidString(ls_nombrePredio))
								throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_PREDIO);

							if(!StringUtils.isValidString(ls_observaciones))
								throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

							lr_data.setDatosAntSistema(ldas_data);
							lr_data.setObservacionesProceso(ls_observaciones);
							lr_data.setIdTurnoHistoria(getIdTurnoHistoria());

							lr_data = irr_registroRemote.generarSolicitudGrabacionMatriculasCorrecciones(
								    lr_data, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
								);

							if(lr_data != null)
							{
								String ls_nir;

								ls_nir = lr_data.getNirProceso();

								if(StringUtils.isValidString(ls_nir))
								{
									setMensajeTerminarProceso("Ha finalizado su solicitud bajo el NIR: " + ls_nir);
									PrimeFaces.current().executeScript("PF('idDialogNir').show();");
									PrimeFaces.current().ajax().update("fDialogNir:otMensaje");
								}
							}
						}
					}
					else if(
					    ls_motivo.equalsIgnoreCase(
						        StringUtils.getString(MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR_CORRECCIONES)
						    )
					)
					{
						if(lth_th != null)
						{
							Solicitud ls_solicitud;

							ls_solicitud = new Solicitud();

							ls_solicitud.setIdSolicitud(lth_th.getIdSolicitud());
							ls_solicitud.setDocumento(false);

							ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

							if(ls_solicitud != null)
							{
								String ls_idProceso;
								String ls_idSubProceso;

								ls_idProceso        = ls_solicitud.getIdProceso();
								ls_idSubProceso     = ls_solicitud.getIdSubproceso();

								if(
								    StringUtils.isValidString(ls_idProceso)
									    && StringUtils.isValidString(ls_idSubProceso)
									    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
									    && ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CORRECCION_INTERNA)
								)
									throw new B2BException(ErrorKeys.ERROR_CORRECCION_INTERNA_MAYOR_VALOR);

								ls_result = iniciarMayorValor(lfc_context, true, false);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);
				}
				else if(
				    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
				)
				{
					BeanTrasladosDetalle lbtd_beanTrasladoDetalle;

					lbtd_beanTrasladoDetalle = (BeanTrasladosDetalle)lfc_context.getApplication()
							                                                        .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_TRASLADO_DETALLE, BeanTrasladosDetalle.class
							);

					if(lbtd_beanTrasladoDetalle != null)
					{
						BeanDireccion lbd_beanDireccion;
						String        ls_idSubproceso;

						lbd_beanDireccion     = getBeanDireccion();
						ls_idSubproceso       = null;

						lbtd_beanTrasladoDetalle.clean();
						lbtd_beanTrasladoDetalle.clear(false);
						lbtd_beanTrasladoDetalle.setEtapa(ll_idEtapa);

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							lth_turnoHistoria     = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

							ls_idSubproceso = lth_turnoHistoria.getIdSubproceso();

							lbtd_beanTrasladoDetalle.setTurnoHistoria(lth_turnoHistoria);
						}

						{
							Suspension   ls_parametros;
							OficiosTexto lot_data;

							ls_parametros = getParametros();

							if(ls_parametros == null)
								ls_parametros = new Suspension();    //ELIMINAR ls_parametros 

							lot_data = icr_calificacionRemote.cargarOficioTextoTraslados(
								    getIdTurno(), getMotivoTramite()
								);

							if(lot_data != null)
								lbtd_beanTrasladoDetalle.getParametros().setOficiosTexto(lot_data);

							if(StringUtils.isValidString(ls_idSubproceso))
							{
								if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
								{
									if(
									    (ls_motivo.equalsIgnoreCase(
										        StringUtils.getString(MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
										    )
										    || ls_motivo.equalsIgnoreCase(
										        StringUtils.getString(
										            MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS
										        )
										    )
										    || ls_motivo.equalsIgnoreCase(
										        StringUtils.getString(MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)
										    )) && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
									)
										throw new B2BException(ErrorKeys.ERROR_TRASLADO_SUBPROCESO_1);

									if(
									    ls_motivo.equalsIgnoreCase(
										        StringUtils.getString(
										            MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA
										        )
										    ) && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
									)
										throw new B2BException(ErrorKeys.ERROR_TRASLADO_SUBPROCESO_2);
								}

								if(
								    ls_motivo.equalsIgnoreCase(
									        StringUtils.getString(MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
									    )
								)
									lbtd_beanTrasladoDetalle.setNegacionSolicitud(true);
								else if(
								    ls_motivo.equalsIgnoreCase(
									        StringUtils.getString(MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)
									    )
								)
									lbtd_beanTrasladoDetalle.setProyectarResolucion(true);
								else if(
								    ls_motivo.equalsIgnoreCase(
									        StringUtils.getString(MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)
									    )
								)
									lbtd_beanTrasladoDetalle.setSolicitudDocumentacion(true);
								else if(
								    ls_motivo.equalsIgnoreCase(
									        StringUtils.getString(
									            MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA
									        )
									    )
								)
									lbtd_beanTrasladoDetalle.setResolucionMasivos(true);
							}
						}

						lbtd_beanTrasladoDetalle.consultarPersonaInteresado();
						lbtd_beanTrasladoDetalle.setDocumentoGenerado(false);
						lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
						lbd_beanDireccion.setDeshabilitarResidencia(true);
						lbtd_beanTrasladoDetalle.setDatosGuardados(false);
						lbtd_beanTrasladoDetalle.generarMigaPan(ls_motivo);
						lbtd_beanTrasladoDetalle.setMotivoTramite(ls_motivo);
						lbtd_beanTrasladoDetalle.setIdTurnoHistoria(getIdTurnoHistoria());
						lbtd_beanTrasladoDetalle.setIdTurno(ls_idTurno);
						lbtd_beanTrasladoDetalle.setObservaciones(getObservacionesEtapa130());
						lbtd_beanTrasladoDetalle.setMostrarDescargarZip(false);
						lbtd_beanTrasladoDetalle.cargarDatosActuacionesAdministrativas();

						ls_result = NavegacionCommon.ANALISIS_TRASLADOS_DETALLE;
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS)
				{
					if(!isConfirmarTraslados660Dialog())
					{
						setConfirmarTraslados660(false);
						setSeleccionTrasladoMatricula(null);
						PrimeFaces.current().executeScript("PF('dlgtraslados660').show();");
					}
					else if(isConfirmarTraslados660Dialog() && !isConfirmarTraslados660())
					{
						irr_aprobacionRemote.ejecutarTrasladoProcedimiento(
						    NumericUtils.getLongWrapper(getIdTurnoHistoria()), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

						PrimeFaces.current().executeScript("PF('dlgEjecutartraslados660').show();");
					}
					else if(isConfirmarTraslados660())
					{
						irr_aprobacionRemote.aprobarTrasladoEtapa660(
						    NumericUtils.getLong(getIdTurnoHistoria()), getObservacionesAprobador(), getUserId(),
						    getLocalIpAddress(), getRemoteIpAddress()
						);

						{
							BeanAprobacion lb_beanAprobacion;
							lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
									                                           .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
									);

							if(lb_beanAprobacion != null)
							{
								lb_beanAprobacion.clean();
								lb_beanAprobacion.limpiarBanderaProcesos();
								lb_beanAprobacion.setVieneDeEjecucionTraslado(true);
								lb_beanAprobacion.findDetalleAprobacion();
								lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
								lb_beanAprobacion.setIdProcesoSeleccionado(null);
								lb_beanAprobacion.generarGraficoDeTorta(
								    EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS, false
								);
							}

							ls_result = NavegacionCommon.BANDEJA_ENTRADA;
						}

						setConfirmarTraslados660Dialog(false);
						setConfirmarTraslados660(false);
						setSeleccionTrasladoMatricula(null);
						setEjecucionMatriculasTraslado(null);
					}
				}
				else if((ll_idEtapa == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS))
				{
					BeanDetalleProyectaResolucion lbdpr_bean;
					lbdpr_bean = (BeanDetalleProyectaResolucion)lfc_context.getApplication()
							                                                   .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DETALLE_PROYECTA_RESOLUCION,
							    BeanDetalleProyectaResolucion.class
							);

					if(lbdpr_bean != null)
					{
						TurnoHistoria lth_turnoHistoria = new TurnoHistoria();
						BeanDireccion lbd_beanDireccion = getBeanDireccion();

						lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							lbdpr_bean.setTurnoHistoria(lth_turnoHistoria);
							lbdpr_bean.setBandejaentrada(true);
							lbdpr_bean.setConsultaIndividual(StringUtils.isValidString(getIdTurnoConsulta()));
							lbdpr_bean.setData(null);
							lbdpr_bean.setIdTurnoHistoria(String.valueOf(lth_turnoHistoria.getIdTurnoHistoria()));
							lbdpr_bean.consultarPersonaInteresado();
							lbdpr_bean.setDocumentoGenerado(false);
							lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
							lbd_beanDireccion.setDeshabilitarResidencia(true);
							lbdpr_bean.setDatosGuardados(false);
							lbdpr_bean.generarMigaPan(ls_motivo);
							lbdpr_bean.setMotivoTramite(ls_motivo);
							lbdpr_bean.setIdTurno(ls_idTurno);
							lbdpr_bean.setEtapa(ll_idEtapa);
							lbdpr_bean.setIdEtapa(ll_idEtapa);
							lbdpr_bean.setMostrarDescargarZip(false);
							lbdpr_bean.setProyectar(true);
							lbdpr_bean.setObservaciones(getObservacionesEtapa130());
							lbdpr_bean.cargarDatosActuacionesAdministrativas();

							String ls_idSubproceso = lth_turnoHistoria.getIdSubproceso();

							Suspension ls_parametros;
							OficiosTexto lot_data;

							ls_parametros          = getParametros();

							if(ls_parametros == null)
								ls_parametros = new Suspension();

							lot_data = icr_calificacionRemote.cargarOficioTextoTraslados(
								    getIdTurno(), getMotivoTramite()
								);

							if(lot_data != null)
								ls_parametros.setOficiosTexto(lot_data);

							if(StringUtils.isValidString(ls_idSubproceso))
								lbdpr_bean.setProyectarResolucion(true);

							lbdpr_bean.generarData();
							ls_result = NavegacionCommon.DETALLE_BANDEJA_PROYECTA_RESOLUCION;
						}
					}
				}
				else if((ll_idEtapa == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA))
				{
					boolean lb_autoPruebas;
					boolean lb_esSegundaInstanciaDialog;

					lb_autoPruebas                  = ll_idMotivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS;
					lb_esSegundaInstanciaDialog     = isEsSegundaInstanciaDialog();

					if(lb_autoPruebas && !lb_esSegundaInstanciaDialog)
						PrimeFaces.current().executeScript("PF('dlgSegundaInstancia').show();");
					else if(!lb_autoPruebas || lb_esSegundaInstanciaDialog)
					{
						BeanSegundaInstancia lbdpr_bean;
						lbdpr_bean = (BeanSegundaInstancia)lfc_context.getApplication()
								                                          .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_SEGUNDA_INSTANCIA, BeanSegundaInstancia.class
								);

						if(lbdpr_bean != null)
						{
							TurnoHistoria lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
							lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								lbdpr_bean.clean();
								lbdpr_bean.setTurnoHistoria(lth_turnoHistoria);
								lbdpr_bean.setIdTurnoHistoria(String.valueOf(lth_turnoHistoria.getIdTurnoHistoria()));
								lbdpr_bean.consultarPersonaInteresado();
								lbdpr_bean.setDocumentoGenerado(false);
								lbdpr_bean.setDatosGuardados(false);
								lbdpr_bean.setMotivoTramite(ls_motivo);
								lbdpr_bean.setIdTurno(ls_idTurno);
								lbdpr_bean.setEtapa(ll_idEtapa);
								lbdpr_bean.setMostrarDescargarZip(false);
								lbdpr_bean.setObservaciones(getObservacionesEtapa130());
								lbdpr_bean.cargarDatosSegundaInstancia();
								lbdpr_bean.setSolicitudDocumentacion(isEsSegundaInstancia());
								lbdpr_bean.validarPanelesSegunMotivo(NumericUtils.getLong(ls_motivo));

								ls_result = NavegacionCommon.SEGUNDA_INSTANCIA;
							}
						}
					}
				}
				else if((ll_idEtapa == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S))
				{
					BeanDetalleAprobacionLibroAntiguoSistema lbdpr_bean;

					lbdpr_bean = (BeanDetalleAprobacionLibroAntiguoSistema)lfc_context.getApplication()
							                                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DETALLE_APROBACION_lIBRO_ANTIGUO_SISTEMA,
							    BeanDetalleAprobacionLibroAntiguoSistema.class
							);

					if(lbdpr_bean != null)
					{
						TurnoHistoria lth_turnoHistoria = new TurnoHistoria();
						BeanDireccion lbd_beanDireccion = getBeanDireccion();

						lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							lbdpr_bean.setTurnoHistoria(lth_turnoHistoria);
							lbdpr_bean.setBandejaentrada(true);
							lbdpr_bean.setConsultaIndividual(StringUtils.isValidString(getIdTurnoConsulta()));
							lbdpr_bean.setData(null);
							lbdpr_bean.setIdTurnoHistoria(String.valueOf(lth_turnoHistoria.getIdTurnoHistoria()));
							lbdpr_bean.consultarPersonaInteresado();
							lbdpr_bean.setDocumentoGenerado(false);
							lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
							lbd_beanDireccion.setDeshabilitarResidencia(true);
							lbdpr_bean.setDatosGuardados(false);
							lbdpr_bean.setMotivoTramite(ls_motivo);
							lbdpr_bean.setIdTurno(ls_idTurno);
							lbdpr_bean.setEtapa(ll_idEtapa);
							lbdpr_bean.setIdEtapa(ll_idEtapa);
							lbdpr_bean.setMostrarDescargarZip(false);
							lbdpr_bean.setProyectar(true);
							lbdpr_bean.cargarArchivoAutorizacionFirma(false);

							String ls_idSubproceso = lth_turnoHistoria.getIdSubproceso();

							Suspension ls_parametros;
							OficiosTexto lot_data;

							ls_parametros          = getParametros();

							if(ls_parametros == null)
								ls_parametros = new Suspension();

							lot_data = icr_calificacionRemote.cargarOficioTextoTraslados(
								    getIdTurno(), getMotivoTramite()
								);

							if(lot_data != null)
								ls_parametros.setOficiosTexto(lot_data);

							if(StringUtils.isValidString(ls_idSubproceso))
								lbdpr_bean.setProyectarResolucion(true);

							lbdpr_bean.generarData();
							ls_result = NavegacionCommon.DETALLE_APROBACION_LIBRO_ANTIGUO_SISTEMA;
						}
					}
				}
				else if((ll_idEtapa == EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION))
				{
					BeanResolucionPlaneacion lbdpr_bean;
					lbdpr_bean = (BeanResolucionPlaneacion)lfc_context.getApplication()
							                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_RESOLUCION_PLANEACION, BeanResolucionPlaneacion.class
							);

					if(lbdpr_bean != null)
					{
						TurnoHistoria lth_turnoHistoria = new TurnoHistoria();
						BeanDireccion lbd_beanDireccion = getBeanDireccion();

						lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
						lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							lbdpr_bean.setTurnoHistoria(lth_turnoHistoria);
							lbdpr_bean.setBandejaentrada(true);
							lbdpr_bean.setConsultaIndividual(StringUtils.isValidString(getIdTurnoConsulta()));
							lbdpr_bean.setData(null);
							lbdpr_bean.setIdTurnoHistoria(String.valueOf(lth_turnoHistoria.getIdTurnoHistoria()));
							lbdpr_bean.consultarPersonaInteresado();
							lbdpr_bean.setDocumentoGenerado(false);
							lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
							lbd_beanDireccion.setDeshabilitarResidencia(true);
							lbdpr_bean.setDatosGuardados(false);
							lbdpr_bean.generarMigaPan(ls_motivo);
							lbdpr_bean.setMotivoTramite(ls_motivo);
							lbdpr_bean.setIdTurno(ls_idTurno);
							lbdpr_bean.setEtapa(ll_idEtapa);
							lbdpr_bean.setIdEtapa(ll_idEtapa);
							lbdpr_bean.setMostrarDescargarZip(false);
							lbdpr_bean.setPlaneacion(true);
							lbdpr_bean.setObservaciones(getObservacionesEtapa130());
							lbdpr_bean.cargarDatosActuacionesAdministrativas();

							String ls_idSubproceso = lth_turnoHistoria.getIdSubproceso();

							Suspension ls_parametros;
							OficiosTexto lot_data;

							ls_parametros          = getParametros();

							if(ls_parametros == null)
								ls_parametros = new Suspension();

							lot_data = icr_calificacionRemote.cargarOficioTextoTraslados(
								    getIdTurno(), getMotivoTramite()
								);

							if(lot_data != null)
								ls_parametros.setOficiosTexto(lot_data);

							if(StringUtils.isValidString(ls_idSubproceso))
								lbdpr_bean.setProyectarResolucion(true);

							lbdpr_bean.generarData();
							ls_result = NavegacionCommon.DETALLE_RESOLUCION_PLANEACION;
						}
					}
				}
				else if((ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES))
				{
					if(ll_idMotivo == MotivoTramiteCommon.REALIZAR_CORRECCIONES)
					{
						String ls_idTurnoHistoria;

						ls_idTurnoHistoria = getIdTurnoHistoria();

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

							irr_calificacionRemote.procCopiaDefinitivoTemporal(
							    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
						}

						{
							BeanEjecucionCorrecciones lbec_bean;

							lbec_bean = (BeanEjecucionCorrecciones)lfc_context.getApplication()
									                                              .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_EJECUCION_CORRECCIONES,
									    BeanEjecucionCorrecciones.class
									);

							if(lbec_bean != null)
							{
								lbec_bean.clean();
								lbec_bean.setIdMotivo(ll_idMotivo);
								lbec_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
								lbec_bean.setIdTurno(getIdTurno());
								lbec_bean.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapa));
								lbec_bean.setMostrarBandeja(true);
								lbec_bean.setMostrarDetalle(false);
								lbec_bean.datosCorrecciones();
								lbec_bean.setPredio(getPredio());

								ls_result = NavegacionCommon.EJECUCION_CORRECCIONES;
							}
						}
					}
					else if(ll_idMotivo == MotivoTramiteCommon.DEVOLVER_AL_ANALISTA_CORRECCIONES)
						abrirDialogo("dlgDevolverAnalistaCorrecciones", "dlgDevolverAnalistaCorrecciones");
					else if(ll_idMotivo == MotivoTramiteCommon.APROBAR_SECUENCIAS_132)
						abrirDialogo("dlgAprobarSecuencias", "dlgAprobarSecuencias");
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.REPRODUCCION_CONSTANCIA)))
				{
					BeanReproduccionConstancia lbrc_bean;
					lbrc_bean = (BeanReproduccionConstancia)lfc_context.getApplication()
							                                               .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_REPRODUCCION_CONSTANCIA,
							    BeanReproduccionConstancia.class
							);

					if(lbrc_bean != null)
					{
						lbrc_bean.getIdTurnoHistoria();

						{
							Turno lt_turno;

							lt_turno = new Turno();
							lt_turno.setIdTurno(getIdTurno());

							lt_turno = iasr_antiguoSistemaRemote.findIdCirculoOrigen(lt_turno);

							if(lt_turno != null)
								lbrc_bean.setIdCirculo(lt_turno.getIdCirculo());
						}

						lbrc_bean.setIdMotivo(NumericUtils.getLongWrapper(ls_motivo));
						lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
						lbrc_bean.setTurno(getIdTurno());
						lbrc_bean.setModificarValores(false);
						ls_result = NavegacionCommon.REPRODUCCION_CONSTANCIA;
					}
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.NOTA_DEVOLUTIVA)))
				{
					BeanNotaDevolutiva lbnd_bean;
					lbnd_bean = (BeanNotaDevolutiva)lfc_context.getApplication()
							                                       .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_NOTA_DEVOLUTIVA, BeanNotaDevolutiva.class
							);

					if(lbnd_bean != null)
					{
						lbnd_bean.clear();
						lbnd_bean.setIdMotivo(NumericUtils.getLongWrapper(ls_motivo));
						lbnd_bean.setIdTurnoHistoria(getIdTurnoHistoria());
						lbnd_bean.setRegistroParcial(false);
						lbnd_bean.setTurno(getIdTurno());
						lbnd_bean.cargarInfoMedidasCautelares();
						lbnd_bean.setObservaciones(null);
						lbnd_bean.setComplementacionCausales(null);
						lbnd_bean.setReproduccionConstancia(isReproduccionConstancia());
						lbnd_bean.setIndVinculado(isIndVinculado());
						lbnd_bean.setTurno(getIdTurno());
						lbnd_bean.setTiposCausalesFiltro(lbnd_bean.findTiposCausales());

						{
							TurnoHistoria lth_th;

							lth_th = new TurnoHistoria();

							lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

							if(lth_th != null)
							{
								String ls_s;

								ls_s = lth_th.getIdSolicitud();

								lbnd_bean.setTurno(lth_th.getIdTurno());

								if(StringUtils.isValidString(ls_s))
								{
									Solicitud ls_solicitud;

									ls_solicitud = new Solicitud();

									ls_solicitud.setIdSolicitud(ls_s);
									ls_solicitud.setDocumento(false);
									ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

									if(ls_solicitud != null)
									{
										lbnd_bean.setNir(ls_solicitud.getNir());

										if(StringUtils.isValidString(ls_idTurno))
										{
											Turno lt_turno;

											lt_turno = new Turno();

											lt_turno.setIdTurno(ls_idTurno);

											lt_turno = irr_calificacionRemote.findTurno(ls_idTurno);

											if(lt_turno != null)
											{
												Collection<RegistroAnotacionProhibicion> lcrap_crap;

												lcrap_crap = irr_registroRemote.findRegistroAnotacionProhibicionByTurno(
													    lt_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
													);

												if(CollectionUtils.isValidCollection(lcrap_crap))
												{
													StringBuilder lsb_sb;

													lsb_sb = new StringBuilder();

													for(RegistroAnotacionProhibicion lrap_rap : lcrap_crap)
													{
														if(lrap_rap != null)
														{
															if(lrap_rap.isAlerta())
															{
																String ls_errorKey;

																ls_errorKey = lrap_rap.getErrorKey();

																if(StringUtils.isValidString(ls_errorKey))
																	lsb_sb.append(
																	    getMessages()
																		        .getString(
																		        ls_errorKey, lrap_rap.getMessageArgs()
																		    ) + ",\n"
																	);
															}
														}
													}

													addMessage("I", lsb_sb.toString());
													FacesContext.getCurrentInstance().getExternalContext().getFlash()
														            .setKeepMessages(true);
												}

												{
													TurnoHistoria lth_turnoHistoriaObservaciones;

													lth_turnoHistoriaObservaciones = irr_referenceRemote
															.findTurnoHistoriaByIdTurno(lth_th);

													if(lth_turnoHistoriaObservaciones != null)
														lbnd_bean.setObservaciones(
														    lth_turnoHistoriaObservaciones.getObservaciones()
														);
												}
											}
										}
									}
								}
							}
						}

						if(!isIndVinculado())
						{
							lorc_rc.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

							lbnd_bean.setPdfCancelacion(irr_calificacionRemote.validacionCancelacion(lorc_rc));
							lbnd_bean.setIdTurnoHistoria(getIdTurnoHistoria());

							ls_result = NavegacionCommon.NOTA_DEVOLUTIVA;
						}
						else
						{    // cargar pantalla intermedia

							RegistroCalificacion lrc_rc;
							lrc_rc = new RegistroCalificacion();

							lrc_rc.setTurno(getIdTurno());
							lrc_rc.setIdEtapa(ll_idEtapa);

							lrc_rc = irr_calificacionRemote.turnosVinculados(
								    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							if(lrc_rc != null)
							{
								lbnd_bean.setInfoTurnos(lrc_rc.getAllMatriculas());
								ls_result = NavegacionCommon.CARGAR_NOTAS_DEVOLUTIVAS;
							}
							else
								ls_result = null;
						}
					}
					else
						ls_result = null;
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.CONFRONTACION_CORRECTIVA)))
				{
					if(
					    !(isDatosBasicos()) && (!isInsertaMatricula()) && (!isEliminaMatricula())
						    && (!isVerificaFolioCerrado())
					)
					{
						setLectura(true);
						setConfrontacion(true);
						ls_result = null;

						setMenu((getMenu() + 1));

						if(getMenu() > 2)
							throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);

						setBaldios(irr_calificacionRemote.esProcesoBaldios(getIdTurno()));
					}
					else
					{
						BeanConfrontacion lbrc_bean;
						lbrc_bean = (BeanConfrontacion)lfc_context.getApplication()
								                                      .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_CONFRONTACION, BeanConfrontacion.class
								);

						if(lbrc_bean != null)
						{
							lbrc_bean.clear();
							lbrc_bean.validarActoEjecutoria();
							lbrc_bean.setDatosBasicos(ib_datosBasicos);
							lbrc_bean.setInsertaMatricula(ib_insertaMatricula);
							lbrc_bean.setEliminaMatricula(ib_eliminaMatricula);
							lbrc_bean.setVerificaFolioCerrado(ib_verificaFolioCerrado);
							lbrc_bean.setMotivoTramite(is_motivoTramite);
							lbrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
							lbrc_bean.setMatriculas(
							    irr_calificacionRemote.findPredioDocumentoByTurno(
							        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
							    )
							);

							lbrc_bean.validarActo0463();

							{
								RegistroCalificacion lrc_rc;
								lrc_rc = new RegistroCalificacion();

								lrc_rc.setTurno(getIdTurno());
								lrc_rc.setIdEtapa(ll_idEtapa);

								lrc_rc = irr_calificacionRemote.turnosVinculados(
									    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(lrc_rc != null)
								{
									lbrc_bean.setInfoTurnos(lrc_rc.getAllMatriculas());
									lbrc_bean.setIndVinculado(isIndVinculado());
								}
							}

							lbrc_bean.findObservacionesByIdTurnoHistoria(getIdTurnoHistoria());

							ls_result = NavegacionCommon.REVISION_CONFRONTACION;
						}

						setLectura(false);
						setConfrontacion(false);
						setMotivoTramite(null);
					}
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.SUSPENSION_DE_TERMINOS)))
				{
					if(!isEsSuspensionTramiteDialog())
					{
						setEsSuspensionTramiteDialog(true);
						PrimeFaces.current().executeScript("PF('dlgSuspensionTramite').show();");
					}
					else
					{
						BeanSuspension lbs_beanSuspension;
						lbs_beanSuspension = (BeanSuspension)lfc_context.getApplication()
								                                            .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_SUSPENSION, BeanSuspension.class
								);

						if(lbs_beanSuspension != null)
						{
							setEsSuspensionTramiteDialog(false);

							String ls_idTurnoHistoria;
							ls_idTurnoHistoria = getIdTurnoHistoria();

							if(StringUtils.isValidString(ls_idTurnoHistoria))
							{
								TurnoHistoria lth_turnoHistoria;
								lth_turnoHistoria = new TurnoHistoria();

								lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

								{
									RegistroCalificacion lrc_rc;
									lrc_rc = new RegistroCalificacion();

									lrc_rc.setTurno(getIdTurno());
									lrc_rc.setIdEtapa(ll_idEtapa);

									lrc_rc = irr_calificacionRemote.turnosVinculados(
										    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
										);

									if(lrc_rc != null)
									{
										lbs_beanSuspension.setInfoTurnos(lrc_rc.getAllMatriculas());
										lbs_beanSuspension.setIndVinculado(true);
									}
								}

								lbs_beanSuspension.setParametros(null);
								lbs_beanSuspension.setDocumentoGenerado(false);
								lbs_beanSuspension.setTurnoHistoria(lth_turnoHistoria);
								lbs_beanSuspension.setEsSuspensionSolicitudDocumentacion(isEsSuspensionTramite());
								lbs_beanSuspension.setIdTurno(getIdTurno());
								lbs_beanSuspension.cargarOficiosTexto();

								if(isEsSuspensionTramite())
									lbs_beanSuspension.cargarInfoTiposDocumentalSuspension();

								ls_result = NavegacionCommon.SUSPENSION;
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
						}
					}
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.REALIZAR_REGISTRO)))
				{
					BeanRegistroCalificacion lbrc_bean;

					lbrc_bean = (BeanRegistroCalificacion)lfc_context.getApplication()
							                                             .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
							);

					TurnoHistoria lth_tmp;

					lth_tmp = new TurnoHistoria();

					lth_tmp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

					lth_tmp = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmp);

					if(lth_tmp != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_tmp.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							boolean lb_existenAnotacionesInactivasTmp;

							lb_existenAnotacionesInactivasTmp = irr_calificacionRemote.existenAnotacionesInactivas(
								    ls_idSolicitud
								);

							if(lb_existenAnotacionesInactivasTmp)
								PrimeFaces.current().executeScript("PF('cdrRehacerAnotacionesEliminadas').show();");
							else
								ls_result = realizarRegistro(lbrc_bean, ls_motivo);
						}
					}
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.ANTIGUO_SISTEMA)))
				{
					setEsAntiguoSistema(true);
					ls_result = iniciarAntSistemaCalificacion(lfc_context, false);
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.CORRECCIONES)))
					ls_result = iniciarCorreccionesInternas(lfc_context);
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.MAYOR_VALOR)))
					ls_result = iniciarMayorValor(lfc_context, false);
				else if(
				    ls_motivo.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.HOMOLOGACION_ACTOS_LIQUIDACION_MAYOR_VALOR)
					    )
				)
				{
					BeanHomologacionActosLiquidacion lhal_bean;

					lhal_bean = (BeanHomologacionActosLiquidacion)lfc_context.getApplication()
							                                                     .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_HOMOLOGACION_ACTOS_LIQUIDA,
							    BeanHomologacionActosLiquidacion.class
							);

					if(lhal_bean != null)
					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = iasr_antiguoSistemaRemote.findIdCirculoOrigen(lt_turno);

						if(lt_turno != null)
						{
							lhal_bean.setIdCirculo(lt_turno.getIdCirculo());
							lhal_bean.setIdSolicitud(lt_turno.getIdSolicitud());
							lhal_bean.setIdTurno(ls_idTurno);
							lhal_bean.setIdTurnoHistoria(getIdTurnoHistoria());

							{
								boolean lb_administracionHomologacionActosLiquidacion;

								lb_administracionHomologacionActosLiquidacion = isAdministracionHomologacionActosLiquidacion();

								lhal_bean.setAdministracionHomologacionActosLiquidacion(
								    lb_administracionHomologacionActosLiquidacion
								);

								if(!lb_administracionHomologacionActosLiquidacion)
									lhal_bean.consultarMatriculasPorSolicitud(lt_turno.getIdSolicitud());
							}

							lhal_bean.generarDatosTramiteCantidad();
							lhal_bean.consultarAdmHomologacion();
							lhal_bean.consultarPagos();
							lhal_bean.consultarActosPorIdSolicitudCirculo();

							ls_result = NavegacionCommon.DETALLE_HOMOLOGACION_ACTOS_LIQUIDACION;
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_result;
	}

	/**
	 * Generar datos ant sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosAntSistema()
	    throws B2BException
	{
		DatosAntSistema ldas_datosAntSistema;
		ldas_datosAntSistema = iasr_antiguoSistemaRemote.findDatosAntSistema(
			    NumericUtils.getLongWrapper(getIdTurnoHistoria())
			);

		if(ldas_datosAntSistema != null)
		{
			setConsultaDatosAntSistema(ldas_datosAntSistema);
			setAntiguoSistemaData(iasr_antiguoSistemaRemote.findAntiguoSistemaData(getUserId(), ldas_datosAntSistema));
		}

		if(!CollectionUtils.isValidCollection(getInfoAntiguoSistema()))
			setInfoAntiguoSistema(
			    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
			        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ANT_SISTEMA
			    )
			);
	}

	/**
	 * Generar datos documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosDocumento()
	    throws B2BException
	{
		ConsultaDatosDocumento lcdd_consultaDatosDocumento;
		lcdd_consultaDatosDocumento = iasr_antiguoSistemaRemote.findDatosDocumento(
			    NumericUtils.getLongWrapper(getIdTurnoHistoria())
			);

		if(lcdd_consultaDatosDocumento != null)
		{
			DatosAntSistema ldas_datosAntSistema;
			DocumentoData   ldd_documentoData;

			ldas_datosAntSistema     = getConsultaDatosAntSistema();
			ldd_documentoData        = getDocumentoData();

			if(ldas_datosAntSistema != null)
			{
				String ls_idCirculo;
				ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = new CirculoRegistral();
					lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

					lcdd_consultaDatosDocumento.setCirculoRegistral(lcr_circuloRegistral);
				}
			}

			setConsultaDatosDocumento(lcdd_consultaDatosDocumento);
			lcdd_consultaDatosDocumento.setIndVinculacion(isIndVinculado());
			lcdd_consultaDatosDocumento.setIdTurno(getIdTurno());

			ldd_documentoData = iasr_antiguoSistemaRemote.findDocumentoData(getUserId(), lcdd_consultaDatosDocumento);

			if(ldd_documentoData != null)
			{
				setListadoDocumentos(ldd_documentoData.getListadoDocumentos());
				setDocumentoData(ldd_documentoData);
			}

			if(ldd_documentoData != null)
				setListadoDocumentos(ldd_documentoData.getListadoDocumentos());
		}

		if(!CollectionUtils.isValidCollection(getDocumentos()))
		{
			List<Map<String, Object>> llmso_infoDoc;

			llmso_infoDoc = iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.DOCUMENTO
				);

			if(llmso_infoDoc != null)
			{
				setFechaDocumento((Date)llmso_infoDoc.get(0).get("FECHA_DOCUMENTO"));
				setDocumentos(llmso_infoDoc);
			}
		}
	}

	/**
	 * Generar datos tramites vinculados.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramitesVinculados()
	    throws B2BException
	{
		{
			FacesContext      lfc_context;
			BeanDetallePredio lbdp_beanDetallePredio;

			lfc_context                = FacesUtils.getFacesContext();
			lbdp_beanDetallePredio     = (BeanDetallePredio)lfc_context.getApplication()
					                                                       .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
					);

			if(lbdp_beanDetallePredio != null)
				lbdp_beanDetallePredio.setVieneDeTrazabilidad(false);
		}

		setIdTurnoConsulta(getIdTurno());
		findAll();
	}

	/**
	 * Generar datos tramites vinculados.
	 *
	 * @param ab_consultaTraza correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramitesVinculados(boolean ab_consultaTraza)
	    throws B2BException
	{
		{
			FacesContext      lfc_context;
			BeanDetallePredio lbdp_beanDetallePredio;

			lfc_context                = FacesUtils.getFacesContext();
			lbdp_beanDetallePredio     = (BeanDetallePredio)lfc_context.getApplication()
					                                                       .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_PREDIO, BeanDetallePredio.class
					);

			if(lbdp_beanDetallePredio != null)
				lbdp_beanDetallePredio.setVieneDeTrazabilidad(ab_consultaTraza);
		}

		setIdTurnoConsulta(getIdTurno());
		findAll();
	}

	/**
	 * Método encargado de generar el número de expediente para segunda instancia.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarExpedienteSI()
	    throws B2BException
	{
		Turno lt_turno;

		lt_turno = getTurno();

		lt_turno.setNomemclaturaExpedienteAA(getNomenclaturaExpedienteSI());
		lt_turno.setTipoExpedienteSI(getTipoExpedienteSI());

		setExpedienteSI(
		    irr_referenceRemote.generarExpediente(getUserId(), getRemoteIpAddress(), getLocalIpAddress(), lt_turno)
		);
		setExpedienteGenerado(true);
	}

	/**
	 * Guardar solicitudes apoyo regional orip.
	 */
	public void guardarSolicitudesApoyoRegionalOrip()
	{
		try
		{
			SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;
			lsanui_solicitudApoyoNacional = new SolicitudApoyoNacionalUI();

			validarSolicitudesApoyoRegionalOrip();

			lsanui_solicitudApoyoNacional.setSolicitudesRegionalOrip(getSolicitudRegionalOrip());
			lsanui_solicitudApoyoNacional.setIdTurnoHistoria(getIdTurnoHistoria());

			irr_calificacionRemote.guardarSolicitudesApoyoRegionalOrip(
			    lsanui_solicitudApoyoNacional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setSolicitudesRegionalOripGuardadas(true);

			addMessage(MessagesKeys.SALVAR_CHECKS_PERMISOS_CORRECCION);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}
	}

	/**
	 * Metodo encargado de inactivar registro traslado.
	 *
	 */
	public void inactivarRegistrotraslado()
	{
		try
		{
			if((getEjecucionMatriculasTraslado() != null) && (getEjecucionMatriculasTraslado().size() > 1))
			{
				TrasladoMatricula ltm_matriculaSeleccionada;

				ltm_matriculaSeleccionada = getSeleccionTrasladoMatricula();

				if(ltm_matriculaSeleccionada != null)
					irr_aprobacionRemote.inactivarMatriculaTrasladoById(
					    ltm_matriculaSeleccionada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setEjecucionMatriculasTraslado(null);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_MATRICULA_TRASLADO_MINIMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}
	}

	/**
	 * Limpiar datos.
	 */
	public void limpiarDatos()
	{
		setRegionales(null);
		setSolicitudRegionalOrip(null);
		setEsReasignarTurnosApoyoNac(false);
		setSolicitudesApoyoNacional(null);
		setPersona(null);
		setPersonaTitularCuenta(null);
		setIdProceso(null);
		setProcesoTraslados(false);
		setMostrarDatos460ProcedeSI(false);
		setObservacionesEtapa130(null);
		setEsAntiguoSistema(false);
		setEsSuspensionTramite(false);
		setConfirmarTraslados660Dialog(false);
		setConfirmarTraslados660(false);
		setEjecucionMatriculasTraslado(null);
		setSeleccionTrasladoMatricula(null);
		setOcultarPanelesTraslados(false);
		setPredioDocumento(null);
		setPredio(null);
		setDocumentos(null);
		setActos(null);
		setActosMayorValor(null);
		setInfoAntiguoSistema(null);
		setOcultarPaneles(false);
		setVieneDeFijacionAvisos(false);
		ic_motivos                            = null;
		ib_datosBasicos                       = false;
		ib_insertaMatricula                   = false;
		ib_insertaMatriculaCorreccion         = false;
		ib_eliminaMatricula                   = false;
		ib_eliminarMatriculaCorreccion        = false;
		ib_verificaFolioCerrado               = false;
		ib_verificaFolioCerradoCorreccion     = false;
		ib_confrontacion                      = false;
		ib_correccion                         = false;
		ib_mostrarDialog                      = false;
		ib_documentoGenerado                  = false;
		lb_alertaEjecutoria                   = false;
		ib_vieneDeFijacionAvisos              = false;
		ii_menu                               = 1;
		setValidacionDatosEtapa101(false);
		setInformacionASEtapaAnterior101(null);
		setEsAprobadorSecuencia(false);
		setCausalesDevolucion(null);
		setCausalDevolucion(null);
		icmso_matriculasIndividuales        = null;
		icmso_matriculasRangos              = null;
		ilmso_matriculasTmpIndividuales     = null;
		ilmso_matriculasTmpRangos           = null;
		setTurnosEnCurso(null);
		setTurnosFechas(null);
		setActosFechas(null);
		setTrazabilidadInfo(null);
		setEstado(false);
		setTrazabilidad(null);
		setNirPrincipal(null);
		setTurnosVinculadosNirPrincipal(null);
		setNirVinculados(null);
		setTurnosVinculadosNirVinculados(null);
		setNir(null);
		setIdTurnoConsulta(null);
		setExpedienteConsulta(null);
		setMostrarLiquidacionItem(false);
		setLiquidacionItem(null);
		limpiarDiferencias();
		setZipFinal(null);
		setImagen(null);
		setMostrarDatosDelInteresadoANotificar(false);
		setCompletitudDocumental(null);
		setTurnoHistoria(null);
		setVieneTrazabilidad(false);
	}

	/**
	 * Mensaje confirmacion.
	 */
	public void mensajeConfirmacion()
	{
		try
		{
			List<Map<String, Object>> lmso_tmp;
			String                    ls_mensaje;
			String                    ls_mensajeFinal;
			String                    ls_idTurnoHistoria;

			lmso_tmp               = getTurnosEnCurso();
			ls_mensaje             = "";
			ls_mensajeFinal        = "";
			ls_idTurnoHistoria     = getIdTurnoHistoria();

			ls_mensaje = irr_calificacionRemote.encontrarTurnosActuales(ls_idTurnoHistoria);

			if(CollectionUtils.isValidCollection(lmso_tmp))
			{
				int li_cont;
				li_cont = 1;

				for(Map<String, Object> mso_datos : lmso_tmp)
				{
					if(mso_datos != null)
					{
						if((li_cont == 1) && (ls_mensaje == null))
							ls_mensaje = ls_mensaje + (String)mso_datos.get("ID_TURNO_BLOQUEO");
						else
							ls_mensaje = ls_mensaje + IdentificadoresCommon.SIMBOLO_COMA
								+ (String)mso_datos.get("ID_TURNO_BLOQUEO");

						li_cont = li_cont + 1;
					}
				}
			}

			if(StringUtils.isValidString(ls_mensaje))
				ls_mensajeFinal = getMessages().getString(MessagesKeys.MODIFICACION_AFECTA_SIGUIENTES_TURNOS) + " "
					+ ls_mensaje + " " + getMessages().getString(MessagesKeys.ESTA_SEGURO_DE_REALIZAR_EL_CAMBIO);
			else
				ls_mensajeFinal = getMessages().getString(MessagesKeys.ESTA_SEGURO_DE_REALIZAR_EL_CAMBIO);

			setMensajeConfirmacion(ls_mensajeFinal);
			PrimeFaces.current().executeScript("PF('dlg').show();");
			PrimeFaces.current().ajax().update("fDialogActo:idMensaje");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacionForm:idGrowl");
			addMessage(lb2be_e);
		}
	}

	/**
	 * Muestra mensaje de aviso.
	 */
	public void mostrarMensajePredioInconsistente()
	{
		String   ls_idCirculo;
		String   ls_idMatricula;
		Object[] loa_arg;

		ls_idCirculo       = FacesUtils.getStringFacesParameter("circulo");
		ls_idMatricula     = FacesUtils.getStringFacesParameter("matricula");
		loa_arg            = new String[1];
		loa_arg[0]         = ls_idCirculo + "-" + ls_idMatricula;

		addMessage(MessagesKeys.PREDIO_INCONSISTENTE_NO_ES_POSIBLE_MOSTRAR_INFO, loa_arg);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String observacionesNoTramite()
	{
		TurnoHistoria lth_turnoHistoria;
		String        ls_observaciones;

		lth_turnoHistoria     = new TurnoHistoria();
		ls_observaciones      = new String();

		try
		{
			lth_turnoHistoria.setIdTurnoHistoria(
			    NumericUtils.getLongWrapper(StringUtils.getStringNotNull(getIdTurnoHistoria()))
			);
			lth_turnoHistoria = irr_calificacionRemote.findTurnoHistoriaAnteriorById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				String ls_obNoTramite;
				ls_obNoTramite = lth_turnoHistoria.getObservacionesNoTramite();

				if(StringUtils.isValidString(ls_obNoTramite))
					ls_observaciones = ls_obNoTramite + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
						+ lth_turnoHistoria.getIdEtapa() + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
						+ lth_turnoHistoria.getNombreEtapa();
			}
		}
		catch(B2BException ab2be_exception)
		{
			addMessage(ab2be_exception);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_observaciones;
	}

	/**
	 * Obtener informacion AS etapa 101.
	 */
	public void obtenerInformacionASEtapa101()
	{
		try
		{
			AntiguoSistemaData asd_antiguoSistemaData;
			asd_antiguoSistemaData = null;

			DatosAntSistema ldas_temp;
			ldas_temp = irr_calificacionRemote.obtenerInformacionAntiguoSistema(
				    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ldas_temp != null)
			{
				asd_antiguoSistemaData = iasr_antiguoSistemaRemote.findAntiguoSistemaData(getUserId(), ldas_temp);

				if(asd_antiguoSistemaData != null)
					setValidacionDatosEtapa101(true);
			}

			setInformacionASEtapaAnterior101(asd_antiguoSistemaData);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Ocultar volver.
	 */
	public void ocultarVolver()
	{
		if(!(ib_datosBasicos) && (!ib_insertaMatricula) && (!ib_eliminaMatricula) && (!ib_verificaFolioCerrado))
		{
			ib_lectura           = false;
			ib_confrontacion     = false;
		}

		setMenu(1);
	}

	/**
	 * Ocultar volver correccion.
	 */
	public void ocultarVolverCorreccion()
	{
		if(
		    (!ib_insertaMatriculaCorreccion) && (!ib_eliminarMatriculaCorreccion)
			    && (!ib_verificaFolioCerradoCorreccion)
		)
		{
			ib_lectura        = false;
			ib_correccion     = false;
		}

		setMenu(1);
	}

	/**
	 * Metodo encargado de ejecutar las acciones necesarias para cargar la pantalla de asignar turnos.
	 *
	 * @return Regla de navegación.
	 * @throws B2BException Se ejecuta si se propaga una excepción.
	 */
	public String procedeSIAignarA()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			ls_return = asignarA(NumericUtils.getLong(getIdEtapa()));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_rehacerAnotaciones correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String realizarRegistro(boolean ab_rehacerAnotaciones)
	    throws B2BException
	{
		FacesContext             lfc_context;
		BeanRegistroCalificacion lbrc_bean;

		lfc_context     = FacesUtils.getFacesContext();
		lbrc_bean       = (BeanRegistroCalificacion)lfc_context.getApplication()
				                                                   .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
				);

		return realizarRegistro(
		    lbrc_bean, StringUtils.getString(MotivoTramiteCommon.REALIZAR_REGISTRO), ab_rehacerAnotaciones
		);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresarAprobacion()
	{
		BeanAprobacion lba_beanAprobacion;
		boolean        lb_procesoTerminado;
		FacesContext   lfc_context;
		String         ls_decision;
		String         ls_return;

		lb_procesoTerminado     = false;
		lfc_context             = FacesUtils.getFacesContext();
		lba_beanAprobacion      = (BeanAprobacion)lfc_context.getApplication()
				                                                 .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
				);
		ls_decision             = getDecisionAprobacion();
		ls_return               = null;

		try
		{
			{
				BeanDevolucionDinero lbdd_bean;

				lbdd_bean = (BeanDevolucionDinero)lfc_context.getApplication()
						                                         .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DEVOLUCION_DINERO, BeanDevolucionDinero.class
						);

				if(
				    (NumericUtils.getLong(getIdEtapa()) == EtapaCommon.ID_ETAPA_380)
					    && !lbdd_bean.buscarDocumentoCertificacionRecaudo(getDevolucionDinero().getIdSolicitud())
				)
					throw new B2BException(ErrorKeys.DEBE_GENERAR_CERTIFICADO_RECAUDO);
			}

			if((lba_beanAprobacion != null) && StringUtils.isValidString(ls_decision))
			{
				String ls_idTurnoHistoria;
				String ls_observacionesTemp;

				ls_idTurnoHistoria       = getIdTurnoHistoria();
				ls_observacionesTemp     = null;

				BigDecimal lbd_idEtapa;

				lbd_idEtapa              = null;

				setObservacionesTemporales(null);

				if(StringUtils.isValidString(ls_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = new TurnoHistoria();

					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

					lth_turnoHistoria     = irr_referenceRemote.findTurnoHistoriaByid(lth_turnoHistoria);
					lbd_idEtapa           = lth_turnoHistoria.getIdEtapa();

					if(lth_turnoHistoria != null)
					{
						String ls_etadoActividad;

						ls_etadoActividad = lth_turnoHistoria.getEstadoActividad();

						if(StringUtils.isValidString(ls_etadoActividad))
						{
							lb_procesoTerminado      = ls_etadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA);
							ls_observacionesTemp     = lth_turnoHistoria.getObservaciones();
						}
					}
				}

				if(lb_procesoTerminado)
				{
					setObservacionesTemporales(ls_observacionesTemp);

					PrimeFaces.current().executeScript("PF('dlg').show();");
					PrimeFaces.current().ajax().update("fDialog");
				}
				else if(ls_decision.equalsIgnoreCase(EstadoCommon.A) || ls_decision.equalsIgnoreCase(EstadoCommon.N))
				{
					TurnoHistoria lth_turnoHistoria;
					String        ls_observacionesAprobador;
					MotivoTramite lmt_motivo;
					long          ll_idMotivo;

					lth_turnoHistoria             = new TurnoHistoria(
						    NumericUtils.getLongWrapper(getIdTurnoHistoria())
						);
					ls_observacionesAprobador     = getObservacionesAprobador();
					ll_idMotivo                   = 0;

					if(!StringUtils.isValidString(ls_observacionesAprobador))
						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
					else
						lth_turnoHistoria.setObservaciones(getObservacionesAprobador());

					if(ls_decision.equalsIgnoreCase(EstadoCommon.A))
						ll_idMotivo = MotivoTramiteCommon.APROBAR_PRORROGA_DOCUMENTACION;
					else if(ls_decision.equalsIgnoreCase(EstadoCommon.N))
						ll_idMotivo = MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTACION;

					lmt_motivo = new MotivoTramite(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS, ll_idMotivo);

					irr_aprobacionRemote.actualizarEtapaYCrearSiguiente(
					    lth_turnoHistoria, lmt_motivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					{
						BeanAprobacion lb_beanAprobacion;
						lb_beanAprobacion = (BeanAprobacion)lfc_context.getApplication()
								                                           .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
								);

						if(lb_beanAprobacion != null)
						{
							lb_beanAprobacion.clean();
							lb_beanAprobacion.limpiarBanderaProcesos();
							lb_beanAprobacion.setVieneDeCoordinadorJuridico(true);
							lb_beanAprobacion.findDetalleAprobacion();
							lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
							lb_beanAprobacion.setIdProcesoSeleccionado(null);
							lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS, false);
						}

						ls_return = NavegacionCommon.BANDEJA_ENTRADA;
					}
				}
				else
				{
					Aprobacion la_temp;

					ls_return     = NavegacionCommon.APROBACION;
					la_temp       = getAprobacion();

					if(la_temp != null)
					{
						Collection<Aprobacion> lca_aprobaciones;

						lca_aprobaciones = lba_beanAprobacion.getDataAprobacion();

						if(lca_aprobaciones != null)
						{
							String ls_mensajePredioConsistente;
							String ls_observacionesAprobador;

							ls_mensajePredioConsistente     = getMensajePredioInconsistente();
							ls_observacionesAprobador       = StringUtils.getStringNotNull(getObservacionesAprobador());

							if(
							    StringUtils.isValidString(ls_mensajePredioConsistente)
								    && !ls_observacionesAprobador.contains(ls_mensajePredioConsistente)
							)
								ls_observacionesAprobador = ls_mensajePredioConsistente
									+ IdentificadoresCommon.SALTO_LINEA + ls_observacionesAprobador;

							lca_aprobaciones.remove(la_temp);

							la_temp.setBloquearCheck(true);
							la_temp.setGenerarRelacion(true);
							la_temp.setObservacionesAprobador(ls_observacionesAprobador);

							irr_aprobacionRemote.actualizarTurnoHistoriaRelacion(
							    la_temp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
							lca_aprobaciones.add(la_temp);

							if(
							    NumericUtils.isValidBigDecimal(lbd_idEtapa)
								    && (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_380)
							)
							{
								lba_beanAprobacion.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_380));

								if(ls_decision.equalsIgnoreCase(EstadoCommon.F))
									lba_beanAprobacion.setIdMotivo380(MotivoTramiteCommon.APROBACION_DEVOLUCION_DINERO);
								else
									lba_beanAprobacion.setIdMotivo380(
									    MotivoTramiteCommon.GENERAR_RESOLUCION_DE_NEGACION
									);
							}
						}
					}
				}
			}

			limpiarDatos();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnBandeja()
	{
		BeanAprobacion lba_beanAprobacion;
		FacesContext   lfc_context;

		lfc_context            = FacesUtils.getFacesContext();
		lba_beanAprobacion     = (BeanAprobacion)lfc_context.getApplication()
				                                                .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
				);

		if(lba_beanAprobacion != null)
		{
			lba_beanAprobacion.clean();
			lba_beanAprobacion.findDetalleAprobacion();
		}

		return NavegacionCommon.BANDEJA_ENTRADA;
	}

	/**
	 * Método para salvar actos de devolución de dinero y terminar la etapa con su correspondiente id motivo.
	 *
	 * @return el valor de string donde va a retornar
	 */
	public String salvarActosDevolucionDinero()
	{
		String ls_result;
		ls_result = null;

		try
		{
			Collection<ActoDevolucionDinero> lcadd_actosDevolucionDinero;
			DevolucionDineroUI               lddui_datosSalvarUI;

			lddui_datosSalvarUI             = new DevolucionDineroUI();
			lcadd_actosDevolucionDinero     = getActosDevolucionDinero();

			lddui_datosSalvarUI.setIdTurnoHistoria(getIdTurnoHistoria());
			lddui_datosSalvarUI.setActosDevolucionDinero(lcadd_actosDevolucionDinero);
			lddui_datosSalvarUI.setIdDevolucionDinero(
			    (getDevolucionDinero() != null) ? getDevolucionDinero().getIdDevolucionDinero() : null
			);
			lddui_datosSalvarUI.setDevolucionDinero(getDevolucionDinero());

			lddui_datosSalvarUI = idd_devolucionesDineroRemote.salvarActosDevolucionDinero(
				    lddui_datosSalvarUI, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lddui_datosSalvarUI != null)
			{
				FacesContext             lfc_context;
				BeanAnalistaDeDevolucion lbc_bean;

				lfc_context     = FacesUtils.getFacesContext();

				lbc_bean = (BeanAnalistaDeDevolucion)lfc_context.getApplication()
						                                            .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_ANALISTA_DE_DEVOLUCION, BeanAnalistaDeDevolucion.class
						);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();

				ls_result = NavegacionCommon.ANALISTA_DE_DEVOLUCIONES;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarActosDevolucionDinero", lb2be_e);

			addMessage(lb2be_e);
		}

		return ls_result;
	}

	/**
	 * Show growl.
	 */
	public void showGrowl()
	{
		showGrowl(MessagesKeys.INFORMACION);
	}

	/**
	 * Show growl.
	 *
	 * @param as_string correspondiente al valor del tipo de objeto String
	 */
	public void showGrowl(String as_string)
	{
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(
		    null,
		    new FacesMessage(FacesMessage.SEVERITY_INFO, getMessages().getString(MessagesKeys.INFORMACION), as_string)
		);
	}

	/**
	 * Terminar proceso apoyo nacional.
	 *
	 * @return el valor de string
	 */
	public String terminarProcesoApoyoNacional()
	{
		String ls_returnPage;

		ls_returnPage = null;

		try
		{
			if(isSolicitudesRegionalOripGuardadas() && isEsReasignarTurnosApoyoNac())
			{
				SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;
				lsanui_solicitudApoyoNacional = new SolicitudApoyoNacionalUI();

				validarSolicitudesApoyoRegionalOrip();

				lsanui_solicitudApoyoNacional.setIdTurnoHistoria(getIdTurnoHistoria());

				lsanui_solicitudApoyoNacional = irr_calificacionRemote.terminarProcesoApoyoNacionalAprobacion(
					    lsanui_solicitudApoyoNacional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lsanui_solicitudApoyoNacional == null)
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

				FacesContext   lfc_context;
				BeanAprobacion lb_beanAprobacion;

				lfc_context           = FacesUtils.getFacesContext();
				lb_beanAprobacion     = (BeanAprobacion)lfc_context.getApplication()
						                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneAprobacionApoyoNacional(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL, false);
				}

				ls_returnPage = NavegacionCommon.BANDEJA_ENTRADA;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_GUARDAR_CAMBIOS_REALIZADOS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_returnPage;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_boolean correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String vaParaActuacionesAdministrativas(boolean ab_boolean)
	    throws B2BException, IOException
	{
		setEsActuacionesAdministrativas(ab_boolean);
		setEsActuacionesAdministrativasDialog(true);

		return enviaPagina();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String vaParaAntiguoSistema()
	    throws B2BException, IOException
	{
		setEsAntiguoSistema(true);

		String ls_return;
		ls_return = enviaPagina();

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_boolean correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String vaParaSegundaInstancia(boolean ab_boolean)
	    throws B2BException, IOException
	{
		setEsSegundaInstancia(ab_boolean);
		setEsSegundaInstanciaDialog(true);

		return enviaPagina();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ab_boolean correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public String vaParaSuspensionTramite(boolean ab_boolean)
	    throws B2BException, IOException
	{
		setEsSuspensionTramite(ab_boolean);

		String ls_return;
		ls_return = enviaPagina();

		return ls_return;
	}

	/**
	 * Calcula los motivos de devolución dependiendo de la etapa de la que provenga
	 * el caso en proceso.
	 *
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public void validacionEtapaAnteriorAprobacion()
	    throws Exception
	{
		try
		{
			Aprobacion la_aprobacion;

			la_aprobacion = getAprobacion();

			if(la_aprobacion != null)
			{
				BigDecimal                 lbd_etapaAnterior;
				int                        li_etapa;
				int                        ll_etapaActual;
				CausalAprobacionDevolucion lcad_causal;

				lbd_etapaAnterior = irr_aprobacionRemote.validacionEtapaAnteriorAprobacionAS(
					    la_aprobacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(!NumericUtils.isValidBigDecimal(lbd_etapaAnterior))
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_ETAPA);

				ll_etapaActual     = 0;
				li_etapa           = lbd_etapaAnterior.intValue();

				if(li_etapa == 110)
					la_aprobacion.setDevolucionAntiguoSistema(true);

				lcad_causal = new CausalAprobacionDevolucion();

				{
					long          ll_etapaAnterior;
					TurnoHistoria lth_th;

					ll_etapaAnterior     = NumericUtils.getLong(lbd_etapaAnterior);
					lth_th               = la_aprobacion.getTurnoHistoria();

					if(lth_th != null)
					{
						String ls_idProceso;
						String ls_idSubproceso;

						ls_idProceso        = lth_th.getIdProceso();
						ls_idSubproceso     = lth_th.getIdSubproceso();
						ll_etapaActual      = lth_th.getIdEtapa().intValue();

						if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso))
						{
							if(
							    (ll_etapaAnterior == EtapaCommon.APROBACION_RESOLUCION)
								    || (ll_etapaAnterior == EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS)
							)
							{
								if(
								    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
									    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
								)
								{
									lbd_etapaAnterior = NumericUtils.getBigDecimal(
										    EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS
										);
									lcad_causal.setEsAprobadorResolucion(true);
								}
							}
							else if(ll_etapaAnterior == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
							{
								if(
								    StringUtils.isValidString(ls_idSubproceso)
									    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
									    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
								)
									lcad_causal.setEsAprobadorAsesoriaJuridica(true);
							}
						}
					}
				}

				if((li_etapa != 132) && (li_etapa != 175))
					lcad_causal.setEsAprobadorSecuencia(isEsAprobadorSecuencia());

				if(li_etapa == 377)
					lbd_etapaAnterior = NumericUtils.getBigDecimal(385);

				lcad_causal.setIdEtapaDevolucion(lbd_etapaAnterior);

				if((ll_etapaActual == 111) && (li_etapa == 110))
					setCausalesDevolucion(
					    irr_referenceRemote.findByEtapaDevolucion111(
					        lcad_causal, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);

				else
					setCausalesDevolucion(
					    irr_referenceRemote.obtenerCausalesAprobacionDevolucionActivos(
					        lcad_causal, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);

				setAprobacion(la_aprobacion);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Validar decision.
	 */
	public void validarDecision()
	{
		validarDecision(true);
	}

	/**
	 * Validar decision.
	 *
	 * @param ab_decision correspondiente al valor del tipo de objeto boolean
	 */
	public void validarDecision(boolean ab_decision)
	{
		if(!ab_decision)
		{
			setInsertaMatriculaCorreccion(false);
			setEliminarMatriculaCorreccion(false);
		}
		else
			setVerificaFolioCerradoCorreccion(false);
	}

	/**
	 * Validar fecha vencimiento acto.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarFechaVencimientoActo()
	    throws B2BException
	{
		int                       li_count;
		String                    ls_alertaEjecutoria;
		boolean                   lb_mensajeVencimientoExcedido;
		List<Map<String, Object>> llmso_actos;
		llmso_actos = getActos();

		FacesContext lfc_context;

		lfc_context     = FacesUtils.getFacesContext();

		li_count                          = 0;
		ls_alertaEjecutoria               = "";
		lb_mensajeVencimientoExcedido     = false;

		if(CollectionUtils.isValidCollection(llmso_actos))
		{
			for(Map<String, Object> llhm_tmp : llmso_actos)
			{
				if(llhm_tmp.containsKey("FECHA_VENCIMIENTO"))
				{
					Date    ld_fechaVencimiento;
					Date    ld_fechaCreacionTurno;
					Boolean lb_fechaVencido;

					ld_fechaVencimiento       = (Date)llhm_tmp.get("FECHA_VENCIMIENTO");
					ld_fechaCreacionTurno     = (Date)llhm_tmp.get("FECHA_CREACION_TURNO");
					lb_fechaVencido           = Boolean.TRUE;

					if((ld_fechaVencimiento != null) && (ld_fechaCreacionTurno != null))
					{
						if(ld_fechaCreacionTurno.before(ld_fechaVencimiento))
						{
							{
								String ls_codigo;
								ls_codigo = (String)llhm_tmp.get("ID_TIPO_ACTO");

								if(li_count == 0)
								{
									lb_mensajeVencimientoExcedido     = true;
									lb_fechaVencido                   = Boolean.FALSE;
									ls_alertaEjecutoria               = ls_alertaEjecutoria + ls_codigo;
								}
								else
								{
									ls_alertaEjecutoria     = ls_alertaEjecutoria + ", " + ls_codigo;
									lb_fechaVencido         = Boolean.FALSE;
								}

								li_count++;
							}
						}

						if(ld_fechaVencimiento.before(new Date()) && !isVieneTrazabilidad())
						{
							String ls_codigo;
							ls_codigo = (String)llhm_tmp.get("ID_TIPO_ACTO");

							String   ls_mensaje;
							Object[] loa_messageArgs = new String[1];
							loa_messageArgs[0]     = ls_codigo;
							ls_mensaje             = getMessages()
									                         .getString(
									    MessagesKeys.FECHA_INFERIOR_ACTUAL_COBRO_MORA, loa_messageArgs
									);

							ExternalContext lec_externalContext;

							lec_externalContext    = lfc_context.getExternalContext();

							addMessage("I", ls_mensaje);

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
									lf_flash.setKeepMessages(true);
							}
						}
					}
					else
						lb_fechaVencido = null;

					llhm_tmp.put("VENCIO_ACTO", lb_fechaVencido);

					if(lb_mensajeVencimientoExcedido)
					{
						setMostrarMensajeFecha(lb_mensajeVencimientoExcedido);
						setMostrarMensajeFechaNo(false);
						setAlertaEjecutoria(ls_alertaEjecutoria);
					}
				}
			}
		}
	}

	/**
	 * Validar matricula existencia.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarMatricula()
	    throws B2BException
	{
		DatosAntSistema ldas_datosAntSistema;

		ldas_datosAntSistema = getDatosAntSistema();

		if(ldas_datosAntSistema != null)
		{
			Long   ll_matricula;
			String ls_idCirculo;

			ll_matricula     = ldas_datosAntSistema.getIdMatriculaGrabacion();
			ls_idCirculo     = ldas_datosAntSistema.getIdCirculoGrabacion();

			if(NumericUtils.isValidLong(ll_matricula) && StringUtils.isValidString(ls_idCirculo))
			{
				Collection<DatosAntSistema> lcdas_datosAntSis;
				Object[]                    loa_arg;
				PredioRegistro              lpr_predioRegistro;

				loa_arg                = new String[1];
				loa_arg[0]             = ls_idCirculo + "-" + StringUtils.getString(ll_matricula);
				lpr_predioRegistro     = new PredioRegistro();

				lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_matricula));
				lpr_predioRegistro.setIdCirculo(ls_idCirculo);

				lpr_predioRegistro = irr_registroRemote.findPredioRegistroByCirculoMatricula(lpr_predioRegistro);

				if(lpr_predioRegistro != null)
					addMessage(MessagesKeys.MATRICULA_EXISTE_EN_ESTADO_DEFINITIVO, loa_arg);
				else
					addMessage(MessagesKeys.MATRICULA_NO_EXISTE_EN_ESTADO_DEFINITIVO, loa_arg);

				lcdas_datosAntSis = irr_registroRemote.findByIdMatriculaGrabacion(
					    ldas_datosAntSistema, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lcdas_datosAntSis != null)
					addMessage(MessagesKeys.MATRICULA_PROCESO_GRABACION, loa_arg);
			}
		}
	}

	/**
	 * Método para validar si es una Prorroga entrega de pruebas.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarProrrogaEntregaDePruebas()
	    throws B2BException
	{
		boolean lb_bool;
		String  ls_idTurnoHistoria;

		lb_bool                = false;
		ls_idTurnoHistoria     = getIdTurnoHistoria();

		if(StringUtils.isValidString(ls_idTurnoHistoria))
			lb_bool = irr_calificacionRemote.validarProrrogaEntregaDePruebas(
				    ls_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

		setEsProrrogaEntregaPruebas(lb_bool);
	}

	/**
	 * Validar solicitudes apoyo regional orip.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarSolicitudesApoyoRegionalOrip()
	    throws B2BException
	{
		Collection<SolicitudApoyoRegionalOrip> lcsaro_solicitudesApoyoRegionalOrip;
		int                                    li_contador;

		li_contador                             = 0;
		lcsaro_solicitudesApoyoRegionalOrip     = getSolicitudRegionalOrip();

		if(CollectionUtils.isValidCollection(lcsaro_solicitudesApoyoRegionalOrip))
		{
			for(SolicitudApoyoRegionalOrip lsaro_tmp : lcsaro_solicitudesApoyoRegionalOrip)
			{
				if(lsaro_tmp != null)
				{
					String ls_habilitar;
					String ls_parametrizacionCalificadores;

					ls_habilitar                        = StringUtils.getStringNotNull(lsaro_tmp.getHabilitar());
					ls_parametrizacionCalificadores     = StringUtils.getStringNotNull(
						    lsaro_tmp.getParametrizacionCalificadores()
						);

					if(ls_habilitar.equalsIgnoreCase(IdentificadoresCommon.S))
					{
						li_contador = li_contador + 1;

						if(!ls_parametrizacionCalificadores.equalsIgnoreCase(IdentificadoresCommon.S))
							throw new B2BException(ErrorKeys.ERROR_HABILITAR_CALIFICADOR_LINEA_APOYO_NACIONAL);
						else
							setHabilitarApoyoNacional(true);
					}
				}
			}
		}

		if(li_contador <= 0)
			throw new B2BException(ErrorKeys.ERROR_HABILITAR_AL_MENOS_UNA_ORIP);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String verificarVersionDocumento()
	{
		List<Map<String, Object>> lmso_datosDocumento;
		String                    ls_mensaje;
		ls_mensaje = new String();

		try
		{
			lmso_datosDocumento = getDocumentos();

			if(CollectionUtils.isValidCollection(lmso_datosDocumento))
				ls_mensaje = irr_calificacionRemote.validarDocumento(lmso_datosDocumento);

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = getMessages().getString(MessagesKeys.DOCUMENTO_SOLICITUD_NO_MODIFICACION);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_mensaje;
	}

	/**
	 * Método encargado de consultar y mostrar la información del acto.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void visualizarEspecificacion()
	    throws B2BException
	{
		String ls_idActo;

		ls_idActo = FacesUtils.getStringFacesParameter("idActo");

		if(StringUtils.isValidString(ls_idActo))
		{
			setDataActo(irr_referenceRemote.findDetalleActo(ls_idActo));
			abrirDialogo("dlgDataAnotacion", "dlgDataAnotacion");
		}
	}

	/**
	 * Metodo encargado de ejecutar las acciones necesarias para cargar la pantalla de asignar turnos.
	 *
	 * @param al_idEtapa de al id etapa
	 * @return Regla de navegación.
	 * @throws B2BException Se ejecuta si se propaga una excepción.
	 */
	private String asignarA(long al_idEtapa)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		try
		{
			Turno lt_turno;

			lt_turno = irr_calificacionRemote.findTurno(getIdTurno());

			if(lt_turno != null)
			{
				BeanAsignarTurno lbat_beanAsignarTurno;

				lbat_beanAsignarTurno = (BeanAsignarTurno)obtenerInstanciaBean(
					    BeanAsignarTurno.class, BeanNameCommon.BEAN_ASIGNAR_TURNO
					);

				Collection<PredioRegistro> lcpr_prediosRegistro;
				Collection<PredioActoIU>   lcpr_predioActoUI;

				lcpr_prediosRegistro     = irr_calificacionRemote.findPredioRegistro(getIdTurnoHistoria());
				lcpr_predioActoUI        = new ArrayList<PredioActoIU>();

				if(CollectionUtils.isValidCollection(lcpr_prediosRegistro))
				{
					for(PredioRegistro lpr_tmp : lcpr_prediosRegistro)
					{
						if(lpr_tmp != null)
						{
							PredioActoIU lpaiu_tmp;
							lpaiu_tmp = new PredioActoIU();

							lpaiu_tmp.setIdMatricula(NumericUtils.getLongWrapper(lpr_tmp.getIdMatricula()));
							lpaiu_tmp.setIdCirculo(lpr_tmp.getIdCirculo());
							lpaiu_tmp.setEsPredioInconsistente(
							    StringUtils.getStringNotNull(lpr_tmp.getPredioInconsistente())
								               .equalsIgnoreCase(EstadoCommon.S) ? true : false
							);

							lcpr_predioActoUI.add(lpaiu_tmp);
						}
					}
				}

				lbat_beanAsignarTurno.limpiarAsignarTurno();

				if(al_idEtapa == EtapaCommon.ID_ETAPA_460)
				{
					String ls_tipoExpedienteSi;
					String ls_expedienteSi;

					ls_tipoExpedienteSi     = lt_turno.getTipoExpedienteSI();
					ls_expedienteSi         = lt_turno.getExpedienteSI();

					if(StringUtils.isValidString(ls_tipoExpedienteSi) && StringUtils.isValidString(ls_expedienteSi))
					{
						lbat_beanAsignarTurno.setTipoExpediente(ls_tipoExpedienteSi);
						lbat_beanAsignarTurno.setExpediente(ls_expedienteSi);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TIPO_EXPEDIENTE_SEGUNDA_INSTANCIA_Y_O_EXPEDIENTE_SI);
				}

				if(CollectionUtils.isValidCollection(lcpr_predioActoUI))
				{
					String ls_matriculas;
					ls_matriculas     = null;

					ls_matriculas = cargarMensajePredioInconsistentePredio(lcpr_predioActoUI, ls_matriculas, false);

					if(StringUtils.isValidString(ls_matriculas))
					{
						Object[] aoa_messageArgs;

						aoa_messageArgs     = new String[1];

						aoa_messageArgs[0] = ls_matriculas;

						lbat_beanAsignarTurno.setMensajePredioInconsistente(
						    getMessages()
							        .getString(
							        MessagesKeys.SOLICITUD_TIENE_MATRICULAS_EN_ESTADO_INCONSISTENTE, aoa_messageArgs
							    )
						);
					}
					else
						lbat_beanAsignarTurno.setMensajePredioInconsistente(null);
				}

				lbat_beanAsignarTurno.setIdTurno(getIdTurno());
				lbat_beanAsignarTurno.setIdTurnoHistoria(getIdTurnoHistoria());
				lbat_beanAsignarTurno.setIdEtapaTurno(al_idEtapa);

				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = new CirculoRegistral();

					lcr_circuloRegistral.setIdCirculo(lt_turno.getIdCirculo());

					lcr_circuloRegistral = ipr_parameterRemote.findCirculoRegistralById(
						    lcr_circuloRegistral, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lcr_circuloRegistral != null)
					{
						lbat_beanAsignarTurno.setIdCirculo(lcr_circuloRegistral.getIdCirculo());
						lbat_beanAsignarTurno.setNombreCirculo(lcr_circuloRegistral.getNombre());
					}
				}

				{
					Constantes lc_constante;

					lc_constante = new Constantes();

					lc_constante.setIdConstante(
					    (al_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
					    ? ConstanteCommon.ROL_SUSTANCIADOR_RECURSOS : ConstanteCommon.ROL_SUSTANCIADOR_ACTUACIONES_ADMIN
					);

					lc_constante = ipr_parameterRemote.findConstantById(lc_constante);

					if(lc_constante != null)
					{
						Rol lr_rol;

						lr_rol = new Rol();

						lr_rol.setIdRol(lc_constante.getCaracter());
						lr_rol.setActuacionesAdministrativas(true);

						lr_rol = ipr_parameterRemote.findRolById(lr_rol);

						if(lr_rol != null)
						{
							lbat_beanAsignarTurno.setIdRol(lr_rol.getIdRol());
							lbat_beanAsignarTurno.setNombreRol(lr_rol.getNombre());
						}
					}
				}

				ls_result = NavegacionCommon.ASIGNAR_TURNOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("asignarA", lb2be_e);

			addMessage(lb2be_e);

			throw lb2be_e;
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de List.
	 *
	 * @param acmso_rangos correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 * @param acmso_matriculas correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 * @return devuelve el valor de List
	 */
	private List<Map<String, Object>> calculoMatriculasIndividuales(
	    Collection<Map<String, Object>> acmso_rangos, Collection<Map<String, Object>> acmso_matriculas
	)
	{
		List<Map<String, Object>> llllhmso_rangos;

		llllhmso_rangos = new LinkedList<Map<String, Object>>();

		if(CollectionUtils.isValidCollection(acmso_rangos))
		{
			Collection<Map<String, Object>> lcmso_rangosRemove;

			lcmso_rangosRemove = new ArrayList<Map<String, Object>>();

			for(Map<String, Object> lmso_tmp : acmso_rangos)
			{
				if(lmso_tmp != null)
				{
					long ll_inicio;
					long ll_final;

					ll_inicio     = -1L;
					ll_final      = -1L;

					{
						Object lo_inicio;

						lo_inicio = lmso_tmp.get(IdentificadoresCommon.RANGO_INICIO);

						if((lo_inicio != null) && lo_inicio instanceof Long)
							ll_inicio = NumericUtils.getLong(((Long)lo_inicio));
					}

					{
						Object lo_final;
						lo_final = lmso_tmp.get(IdentificadoresCommon.RANGO_FIN);

						if((lo_final != null) && lo_final instanceof Long)
							ll_final = NumericUtils.getLong(((Long)lo_final));
					}

					if(ll_inicio == ll_final)
					{
						lcmso_rangosRemove.add(lmso_tmp);

						if(CollectionUtils.isValidCollection(acmso_matriculas))
						{
							boolean                       lb_b;
							Iterator<Map<String, Object>> limso_imso;

							lb_b           = false;
							limso_imso     = acmso_matriculas.iterator();

							while(limso_imso.hasNext() && !lb_b)
							{
								Map<String, Object> lmso_tmpActual;

								lmso_tmpActual = limso_imso.next();

								if(lmso_tmpActual != null)
								{
									if(lmso_tmpActual.containsKey(IdentificadoresCommon.ID_MATRICULA))
									{
										long   ll_idMatriculaActual;
										Object lo_idMatricula;

										lo_idMatricula           = lmso_tmpActual.get(
											    IdentificadoresCommon.ID_MATRICULA
											);
										ll_idMatriculaActual     = (lo_idMatricula != null)
											? NumericUtils.getLong(lo_idMatricula.toString())
											: NumericUtils.DEFAULT_LONG_VALUE;

										if(ll_inicio == ll_idMatriculaActual)
										{
											llllhmso_rangos.add(lmso_tmpActual);
											lb_b = true;
										}
									}
								}
							}
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(lcmso_rangosRemove))
			{
				for(Map<String, Object> lmso_remove : lcmso_rangosRemove)
				{
					if(lmso_remove != null)
						acmso_rangos.remove(lmso_remove);
				}
			}
		}

		return llllhmso_rangos;
	}

	/**
	 * Retorna el valor del objeto de List.
	 *
	 * @param acmso_matriculas correspondiente al valor del tipo de objeto Collection<Map<String,Object>>
	 * @return devuelve el valor de List
	 */
	private List<Map<String, Object>> calculoRangosMatriculas(Collection<Map<String, Object>> acmso_matriculas)
	{
		List<Map<String, Object>> llllhmso_rangos;

		llllhmso_rangos = new LinkedList<Map<String, Object>>();

		{
			long                ll_contador;
			long                ll_llave;
			long                ll_inicioRango;
			Map<String, Object> llhmso_rango;

			ll_contador        = NumericUtils.DEFAULT_LONG_VALUE;
			ll_inicioRango     = NumericUtils.DEFAULT_LONG_VALUE;
			ll_llave           = 1L;
			llhmso_rango       = new LinkedHashMap<String, Object>();

			for(Map<String, Object> lhm_tmp : acmso_matriculas)
			{
				if(lhm_tmp != null)
				{
					if(lhm_tmp.containsKey(IdentificadoresCommon.ID_MATRICULA))
					{
						long   ll_idMatriculaActual;
						Object lo_idMatricula;
						String ls_idCirculo;
						Object lo_idCirculo;

						lo_idMatricula           = lhm_tmp.get(IdentificadoresCommon.ID_MATRICULA);
						ll_idMatriculaActual     = (lo_idMatricula != null)
							? NumericUtils.getLong(lo_idMatricula.toString()) : NumericUtils.DEFAULT_LONG_VALUE;

						lo_idCirculo     = lhm_tmp.get(IdentificadoresCommon.ID_CIRCULO);
						ls_idCirculo     = (lo_idCirculo != null) ? lo_idCirculo.toString() : "";

						if(ll_contador == NumericUtils.DEFAULT_LONG_VALUE)
						{
							ll_inicioRango = ll_idMatriculaActual;
							llhmso_rango.put(
							    IdentificadoresCommon.RANGO_INICIO, NumericUtils.getLongWrapper(ll_inicioRango)
							);
							llhmso_rango.put(IdentificadoresCommon.ID_CIRCULO, ls_idCirculo);
							llhmso_rango.put(IdentificadoresCommon.FILTRO_MATRICULA, new String());
							llhmso_rango.put(
							    IdentificadoresCommon.RANGO_FIN, NumericUtils.getLongWrapper(ll_inicioRango)
							);
						}
						else
						{
							if((ll_inicioRango + 1) == ll_idMatriculaActual)
							{
								ll_inicioRango = ll_idMatriculaActual;
								llhmso_rango.put(
								    IdentificadoresCommon.RANGO_FIN, NumericUtils.getLongWrapper(ll_inicioRango)
								);
								llhmso_rango.put(IdentificadoresCommon.LLAVE, NumericUtils.getLongWrapper(ll_llave));
							}
							else
							{
								llhmso_rango.put(
								    IdentificadoresCommon.RANGO_FIN, NumericUtils.getLongWrapper(ll_inicioRango)
								);
								llhmso_rango.put(IdentificadoresCommon.LLAVE, NumericUtils.getLongWrapper(ll_llave));
								llllhmso_rangos.add(llhmso_rango);

								ll_inicioRango     = ll_idMatriculaActual;
								llhmso_rango       = new LinkedHashMap<String, Object>();

								llhmso_rango.put(
								    IdentificadoresCommon.RANGO_INICIO, NumericUtils.getLongWrapper(ll_inicioRango)
								);
								llhmso_rango.put(IdentificadoresCommon.ID_CIRCULO, ls_idCirculo);
								llhmso_rango.put(IdentificadoresCommon.FILTRO_MATRICULA, new String());

								llhmso_rango.put(
								    IdentificadoresCommon.RANGO_FIN, NumericUtils.getLongWrapper(ll_inicioRango)
								);

								ll_llave++;
							}
						}

						ll_contador++;
					}
				}
			}

			llllhmso_rangos.add(llhmso_rango);

			if(CollectionUtils.isValidCollection(llllhmso_rangos))
			{
				for(Map<String, Object> llhmso_actual : llllhmso_rangos)
				{
					long ll_inicio;
					long ll_final;
					long ll_diferencia;

					ll_inicio     = -1L;
					ll_final      = -1L;

					{
						Object lo_inicio;
						lo_inicio = llhmso_actual.get(IdentificadoresCommon.RANGO_INICIO);

						if((lo_inicio != null) && lo_inicio instanceof Long)
							ll_inicio = NumericUtils.getLong(((Long)lo_inicio));
					}

					{
						Object lo_final;
						lo_final = llhmso_actual.get(IdentificadoresCommon.RANGO_FIN);

						if((lo_final != null) && lo_final instanceof Long)
							ll_final = NumericUtils.getLong(((Long)lo_final));
					}

					if((ll_inicio >= NumericUtils.DEFAULT_LONG_VALUE) && (ll_final >= NumericUtils.DEFAULT_LONG_VALUE))
					{
						int li_length;

						ll_diferencia     = ll_final - ll_inicio;
						li_length         = (NumericUtils.getLongWrapper(ll_diferencia).toString().length() + 1);

						llhmso_actual.put(IdentificadoresCommon.DIFERENCIA, NumericUtils.getLongWrapper(li_length));

						{
							String ls_base;
							Long   ll_base;

							ls_base = NumericUtils.getLongWrapper(ll_inicio).toString();

							if(li_length >= ls_base.length())
								ll_base = null;
							else
								ll_base = NumericUtils.getLongWrapper(
									    ls_base.substring(0, (ls_base.length() - NumericUtils.getInt(li_length)))
									);

							llhmso_actual.put(IdentificadoresCommon.BASE, ll_base);
						}
					}
				}
			}
		}

		return llllhmso_rangos;
	}

	/**
	 * Carga la información necesaria para llevar un tramite a antiguo sistema.
	 *
	 * @param afc_context            Objeto contenedor de la información de las peticiones de sesión de
	 *            la aplicación
	 * @param ab_procesoCorrecciones correspondiente al valor del tipo de objeto boolean
	 * @return Enlace a la pagina de antiguo sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String iniciarAntSistemaCalificacion(FacesContext afc_context, boolean ab_procesoCorrecciones)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(afc_context != null)
		{
			BeanAntSistemaCalificacion lbccpc_bean;
			lbccpc_bean = (BeanAntSistemaCalificacion)afc_context.getApplication()
					                                                 .evaluateExpressionGet(
					    afc_context, BeanNameCommon.BEAN_ANT_SISTEMA_CALIFICACION, BeanAntSistemaCalificacion.class
					);

			if(lbccpc_bean != null)
			{
				lbccpc_bean.clear();
				lbccpc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
				lbccpc_bean.setMatriculasValidacion(
				    irr_calificacionRemote.findPredioDocumentoByTurno(
				        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
				    )
				);

				lbccpc_bean.validarActo0463();
				lbccpc_bean.setIdTurno(getIdTurno());
				lbccpc_bean.setIdCirculo();
				lbccpc_bean.generarDatosDocumento();
				lbccpc_bean.setProcesoCorrecciones(ab_procesoCorrecciones);

				ls_return = NavegacionCommon.ANT_SISTEMA_CALIFICACION;
			}
		}

		return ls_return;
	}

	/**
	 * Prepara la aplicación para iniciar el proceso de correcciones internas sobre
	 * el turno seleccionado.
	 *
	 * @param afc_context            Objeto contenedor de la información de sesion de la aplicación
	 * @return Enlace a la pantalla de correcciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String iniciarCorreccionesInternas(FacesContext afc_context)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		if(afc_context != null)
		{
			BeanCorreccionInterna lbci_bean;

			lbci_bean = (BeanCorreccionInterna)afc_context.getApplication()
					                                          .evaluateExpressionGet(
					    afc_context, BeanNameCommon.BEAN_CORRECCION_INTERNA, BeanCorreccionInterna.class
					);

			if(lbci_bean != null)
			{
				lbci_bean.clean();
				lbci_bean.setCorreccionesCalificacion(true);
				lbci_bean.setIdTurno(getIdTurno());
				lbci_bean.setIdTurnoHistoria(getIdTurnoHistoria());
				lbci_bean.getDatosUsuarioCalificador();

				ls_result = NavegacionCommon.CORRECCIONES_INTERNAS;
			}
		}

		return ls_result;
	}

	/**
	 * Prepara la aplicación para mostrar la pantalla de mayor valor con la
	 * información del caso seleccionado.
	 *
	 * @param afc_context            Objeto contenedor de la información de sesion de la aplicación
	 * @param ab_correcciones            true si el proceso viene de correcciones, false para calificación
	 * @return Enlace a la pagina de Mayor Valor
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String iniciarMayorValor(FacesContext afc_context, boolean ab_correcciones)
	    throws B2BException
	{
		return iniciarMayorValor(afc_context, ab_correcciones, true);
	}

	/**
	 * Prepara la aplicación para mostrar la pantalla de mayor valor con la
	 * información del caso seleccionado.
	 *
	 * @param afc_context            Objeto contenedor de la información de sesion de la aplicación
	 * @param ab_correcciones            true si el proceso viene de correcciones, false para calificación
	 * @param ab_cargarInfo              true si se debe cargar información, false si no se debe cargar información.
	 * @return Enlace a la pagina de Mayor Valor
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String iniciarMayorValor(FacesContext afc_context, boolean ab_correcciones, boolean ab_cargarInfo)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		if(afc_context != null)
		{
			BeanMayorValor lbmv_bean;

			lbmv_bean = (BeanMayorValor)afc_context.getApplication()
					                                   .evaluateExpressionGet(
					    afc_context, BeanNameCommon.BEAN_MAYOR_VALOR, BeanMayorValor.class
					);

			if(lbmv_bean != null)
			{
				boolean lb_turnoMigrado;
				String  ls_idTurno;
				String  ls_idTurnoHistoria;

				lb_turnoMigrado        = false;
				ls_idTurno             = getIdTurno();
				ls_idTurnoHistoria     = getIdTurnoHistoria();

				lbmv_bean.limpiarMayorValor();
				lbmv_bean.setIdTurnoHistoria(ls_idTurnoHistoria);

				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = new TurnoHistoria();
					lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));
					lth_turnoHistoria.setIdTurno(ls_idTurno);

					lb_turnoMigrado = irr_calificacionRemote.validarTurnoMigrado(
						    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
				}

				if(lb_turnoMigrado)
				{
					lbmv_bean.setMigrado(lb_turnoMigrado);

					ExternalContext lec_externalContext;

					lec_externalContext = afc_context.getExternalContext();

					addMessage(MessagesKeys.TURNO_MIGRADO);

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
				else
				{
					Turno  lt_turno;
					String ls_idCirculo;

					lt_turno         = new Turno();
					ls_idCirculo     = null;

					lt_turno.setIdTurno(ls_idTurno);

					lt_turno = iasr_antiguoSistemaRemote.findIdCirculoOrigen(lt_turno);

					if(lt_turno != null)
						ls_idCirculo = lt_turno.getIdCirculo();

					lbmv_bean.setIdCirculo(ls_idCirculo);
					lbmv_bean.setIdTurno(ls_idTurno);

					if(StringUtils.isValidString(ls_idCirculo))
					{
						Acto la_acto;

						la_acto = lbmv_bean.getActo();

						if(la_acto != null)
							la_acto.setIdCirculo(ls_idCirculo);
					}
				}

				lbmv_bean.setMostrarContinuar(true);
				lbmv_bean.setProcesoCorrecciones(ab_correcciones);

				if(ab_cargarInfo)
				{
					lbmv_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbmv_bean.consultarSolicitudPorIdTurno();
					lbmv_bean.consultarCausalesMayorValor();
					lbmv_bean.consultarSolicitudMatriculaPorSolicitud();
					lbmv_bean.cargarMediosNotComInter(false);
					lbmv_bean.buscarPersonasPorSolicitudInterviniente();
					lbmv_bean.validarActosMayorValor();
					lbmv_bean.cargarDatosMayorValor();
					lbmv_bean.documentosEnviadosOWCC();
				}

				ls_result = NavegacionCommon.MAYOR_VALOR;
			}
		}

		return ls_result;
	}

	/**
	 * Metodo encargado de realizar la busqueda de casos para modificar matriculas.
	 *
	 * @param amt_mt de amt mt
	 * @param afc_context Argumento de tipo <code>FacesContext</code> que contiene contexto de Faces.
	 * @param al_idEtapa Argumento de tipo <code>String</code> que contiene la etapa.
	 * @return as_result Resultado de tipo <code>String</code> que retorna la regla de navegación requerida.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String irModificarMatriculas(MotivoTramite amt_mt, FacesContext afc_context, long al_idEtapa)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		try
		{
			if(
			    (!ib_insertaMatriculaCorreccion) && (!ib_eliminarMatriculaCorreccion)
				    && (!ib_verificaFolioCerradoCorreccion)
			)
			{
				ib_lectura        = true;
				ib_correccion     = true;
				ls_result         = null;

				if(amt_mt != null)
				{
					amt_mt = ipr_parameterRemote.findMotivoTramiteById(amt_mt);

					setNombreMotivoTramite((amt_mt != null) ? amt_mt.getDescripcion() : null);
				}

				setMenu((ii_menu + 1));

				if(ii_menu > 2)
					throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);
			}
			else
			{
				BeanDetalleCorreccion lbdc_bean;
				lbdc_bean = (BeanDetalleCorreccion)afc_context.getApplication()
						                                          .evaluateExpressionGet(
						    afc_context, BeanNameCommon.BEAN_DETALLE_CORRECCION, BeanDetalleCorreccion.class
						);

				if(lbdc_bean != null)
				{
					lbdc_bean.clear();
					lbdc_bean.setInsertaMatricula(ib_insertaMatriculaCorreccion);
					lbdc_bean.setEliminaMatricula(ib_eliminarMatriculaCorreccion);
					lbdc_bean.setVerificaFolioCerrado(ib_verificaFolioCerradoCorreccion);
					lbdc_bean.setMotivoTramite(is_motivoTramite);
					lbdc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
					lbdc_bean.setEsEtapaCorrecciones130(true);
					lbdc_bean.setIdEtapa(al_idEtapa);
					lbdc_bean.setMatriculas(
					    irr_calificacionRemote.findPredioDocumentoByTurno(
					        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.MATRICULAS
					    )
					);
					lbdc_bean.setIdEtapa(al_idEtapa);

					{
						RegistroCalificacion lrc_rc;
						lrc_rc = new RegistroCalificacion();

						lrc_rc.setTurno(getIdTurno());
						lrc_rc.setIdEtapa(al_idEtapa);

						lrc_rc = irr_calificacionRemote.turnosVinculados(
							    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lrc_rc != null)
						{
							lbdc_bean.setInfoTurnos(lrc_rc.getAllMatriculas());
							lbdc_bean.setIndVinculado(isIndVinculado());
						}
					}

					lbdc_bean.findObservacionesByIdTurnoHistoria(getIdTurnoHistoria());

					ls_result = NavegacionCommon.DETALLE_CORRECCION;
				}

				setLectura(false);
				setCorreccion(false);
				setMotivoTramite(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("modificarMatriculas", lb2be_e);

			throw lb2be_e;
		}

		return ls_result;
	}

	/**
	 * Limpiar diferencias.
	 */
	private void limpiarDiferencias()
	{
		FacesContext                  lfc_context;
		BeanVisualizacionCorrecciones lbvc_bean;

		lfc_context     = FacesUtils.getFacesContext();
		lbvc_bean       = (BeanVisualizacionCorrecciones)lfc_context.getApplication()
				                                                        .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_VISUALIZACION_CORRECCIONES, BeanVisualizacionCorrecciones.class
				);

		if(lbvc_bean != null)
			lbvc_bean.clean();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param abrc_bean correspondiente al valor del tipo de objeto BeanRegistroCalificacion
	 * @param as_motivo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String realizarRegistro(BeanRegistroCalificacion abrc_bean, String as_motivo)
	    throws B2BException
	{
		return realizarRegistro(abrc_bean, as_motivo, false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param abrc_bean correspondiente al valor del tipo de objeto BeanRegistroCalificacion
	 * @param as_motivo correspondiente al valor del tipo de objeto String
	 * @param ab_rehacerAnotaciones correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private String realizarRegistro(
	    BeanRegistroCalificacion abrc_bean, String as_motivo, boolean ab_rehacerAnotaciones
	)
	    throws B2BException
	{
		if(ab_rehacerAnotaciones)
		{
			String ls_idTurnoHistoria;

			ls_idTurnoHistoria = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_idTurnoHistoria))
				irr_calificacionRemote.precalificar(
				    ls_idTurnoHistoria, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}

		if(abrc_bean != null)
		{
			abrc_bean.clean();
			abrc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
			abrc_bean.setTurno(getIdTurno());
			abrc_bean.setMotivo(as_motivo);
			abrc_bean.setBtnRegresarLoteo(false);
			abrc_bean.setBtnSiguienteLoteo(true);
			abrc_bean.setIndVinculado(isIndVinculado());
			abrc_bean.setBtnTerminarLoteo(false);
			abrc_bean.setRevisionMatriculas(null);
			abrc_bean.setMatriculasInformacion(null);
			abrc_bean.setHabilitarGuardarInformacion(false);
			abrc_bean.findMatriculas(true, true);
			abrc_bean.setPredio(getPredio());
			abrc_bean.setMostrarAtrasCrearGrabar(false);
			abrc_bean.setOcultarSiguienteCrearGrabar(false);
			abrc_bean.setActoConEnglobe(false);
			abrc_bean.llenarCamposInformacionNotificacion();

			String  ls_observacionesNoTramite;
			boolean lb_noAproboSecuencia;
			ls_observacionesNoTramite     = observacionesNoTramite();
			lb_noAproboSecuencia          = false;
			abrc_bean.setHabilitaSecuencia(false);

			if(ls_observacionesNoTramite != null)
			{
				if(ls_observacionesNoTramite.equalsIgnoreCase(EstadoCommon.AUTORIZAR_SECUENCIAS))
				{
					abrc_bean.setVieneDeAprobacionSecuencia(true);
					abrc_bean.setAutorizaSecuencia(true);
				}
				else if(ls_observacionesNoTramite.equalsIgnoreCase(EstadoCommon.NO_AUTORIZAR_SECUENCIAS))
				{
					abrc_bean.setVieneDeAprobacionSecuencia(true);
					abrc_bean.setAutorizaSecuencia(true);
					lb_noAproboSecuencia = true;
				}
			}

			if(!abrc_bean.isAutorizaSecuencia() || lb_noAproboSecuencia)
			{
				boolean lb_habilitaSecuencia;
				lb_habilitaSecuencia = false;

				abrc_bean.setVieneDeAprobacionSecuencia(false);
				abrc_bean.intervinientesConSecuencia();

				TurnoHistoria lth_th;
				lth_th = new TurnoHistoria();
				lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lth_th.setIdTurno(getIdTurno());

				lth_th = irr_calificacionRemote.consultarTurnoMigrado(lth_th, getUserId());

				if(lth_th != null)
					abrc_bean.setEsHomologacionActosLiquidacion(lth_th.isEsHomologarActosLiquidacion());

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
							String                    ls_identificador;
							String                    ls_parametro;
							List<Map<String, Object>> llmso_mso;

							ls_identificador     = IdentificadoresCommon.ACTOS;
							ls_parametro         = StringUtils.getString(lth_turnoHistoriaActual.getIdTurnoHistoria());

							if(isIndVinculado())
							{
								ls_identificador     = IdentificadoresCommon.ACTOS_VINCULADOS;
								ls_parametro         = lth_turnoHistoriaActual.getIdTurno();
							}

							llmso_mso = irr_calificacionRemote.findPredioDocumentoByTurno(
								    getUserId(), ls_parametro, ls_identificador
								);

							if(CollectionUtils.isValidCollection(llmso_mso))
							{
								for(Map<String, Object> lm_tmp : llmso_mso)
								{
									if(lm_tmp != null)
									{
										Object lo_o;

										lo_o = lm_tmp.get("HABILITA_SECUENCIA");

										if(lm_tmp.get("ID_TIPO_ACTO").equals("0915"))
											abrc_bean.setActoConEnglobe(true);

										if((lo_o != null) && lo_o.equals(EstadoCommon.S))
										{
											lb_habilitaSecuencia = true;

											break;
										}
										else
											lb_habilitaSecuencia = false;
									}
								}

								if(lb_habilitaSecuencia)
									break;
							}
						}
					}
				}

				abrc_bean.setHabilitaSecuencia(lb_habilitaSecuencia);
			}
		}

		return NavegacionCommon.REGISTRO_CALIFICACION;
	}
}
