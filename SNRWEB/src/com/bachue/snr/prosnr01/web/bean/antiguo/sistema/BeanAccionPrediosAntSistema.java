package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

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
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.certificados.CertificadosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaPredioRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.component.wizard.Wizard;

import org.primefaces.event.FlowEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de
 * BeanAccionPrediosAntSistema.
 *
 * @author garias
 */
@SessionScoped
@ManagedBean(name = "beanAccionPrediosAntSistema")
public class BeanAccionPrediosAntSistema extends BeanAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1735239055770456463L;

	/** Constante cs_MESSAGE_ID_GROWL. */
	private static final String cs_MESSAGE_ID_GROWL = "idFormAntSistema:globalMsg";

	/** Constante cs_ID_FORMULARIO. */
	private static final String cs_ID_FORMULARIO = "idFormAntSistema:";

	/** Constante cs_ID_MATRICULA_POR_DETALLE. */
	private static final String cs_ID_MATRICULA_POR_DETALLE = "idMatriculasPorDetalle";

	/** Constante cs_ID_LIBRO. */
	private static final String cs_ID_LIBRO = ":idFormAntSistema:idSOMLibroBusqueda";

	/** Constante cs_ID_TOMO. */
	private static final String cs_ID_TOMO = ":idFormAntSistema:idSOMTomo";

	/** Constante cs_ID_TIPO_PARTIDA. */
	private static final String cs_ID_TIPO_PARTIDA = ":idFormAntSistema:idSOMPartidaBusqueda";

	/** Constante cs_ID_NUMERO_PARTIDA. */
	private static final String cs_ID_NUMERO_PARTIDA = ":idFormAntSistema:idItNumeroPartida";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAccionPrediosAntSistema.class);

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ic_certificado. */
	private Certificados ic_certificado;

	/** Propiedad icr certificados remote. */
	@EJB
	private CertificadosRemote icr_certificadosRemote;

	/** Propiedad icacd_completitudDocumental. */
	private Collection<AccCompletitudDocumental> icacd_completitudDocumental;

	/** Propiedad icdas matriculas por detalle. */
	private Collection<DatosAntSistema> icdas_matriculasPorDetalle;

	/** Propiedad icdas matriculas seleccionadas. */
	private Collection<DatosAntSistema> icdas_matriculasSeleccionadas;

	/** Propiedad icdasu detalles ant sistema por solicitud. */
	private Collection<DetalleAntSistemaUI> icdasu_detallesAntSistemaPorSolicitud;

	/** Propiedad icdasui detalles ant sistema seleccionados. */
	private Collection<DetalleAntSistemaUI> icdasui_detallesAntSistemaSeleccionados;

	/** Propiedad icsm solicitudes matriculas. */
	private Collection<SolicitudMatricula> icsm_solicitudesMatriculas;

	/** Propiedad idasu detalles ant sistema consulta. */
	private Collection<DetalleAntSistemaUI> idasu_detallesAntSistemaConsulta;

	/** Propiedad lcidt datos comp. */
	private Collection<CirculoRegistral> lcidt_datos_comp;

	/** Propiedad ib consulta realizada. */
	private CompletitudComplementacion icc_completitudComplementacionCirculoDestino;

	/** Propiedad icepe consulta predio remote. */
	@EJB
	private ConsultaPredioRemote icepe_consultaPredioRemote;

	/** Propiedad idas datos predio para crear. */
	private DatosAntSistema idas_datosPredioParaCrear;

	/** Propiedad idasu matricula detalle id. */
	private DetalleAntSistemaUI idasu_matriculaDetalleId;

	/** Propiedad idasui detalle eliminar. */
	private DetalleAntSistemaUI idasui_detalleEliminar;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**
	 * Propiedad irr reference remote.
	 */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr Reference remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen firma. */
	private StreamedContent isc_imagenFirma;

	/** Propiedad justificacion complementacion. */
	private String is_complementacionCirculoDestino;

	/** Propiedad ib consulta realizada. */
	private String is_complementacionInformacionCirculoDestino;

	/** Propiedad mensaje predio inconsistente. */
	private String is_mensajePredioInconsistente;

	/** Propiedad nombre circulo complementacion. */
	private String is_nombreCirculoComplementacion;

	/**
	 * Propiedad is observaciones temporales.
	 */
	private String is_observacionesTemporales;

	/** Propiedad justificacion complementacion. */
	private String justificacionComplementacion;

	/** Propiedad ls id nombre circulo complementacion. */
	private String ls_idNombreCirculoComplementacion;

	/** Propiedad isr suspension remote. */
	@EJB
	private SuspensionRemote isr_suspensionRemote;

	/** Propiedad ib accion asociar. */
	private boolean ib_accionAsociar;

	/** Propiedad ib bloquear crear asociar. */
	private boolean ib_bloquearCrearAsociar;

	/** Propiedad ib deshabilitar cantidad certificados. */
	private boolean ib_cantidadCertificados;

	/** Propiedad ib confirmar siguiente. */
	private boolean ib_confirmarSiguiente;

	/** Propiedad ib consulta realizada. */
	private boolean ib_consultaRealizada;

	/** Propiedad ib mostrar boton guardar complementacion. */
	private boolean ib_definitivo102;

	/** Propiedad ib habilitar campos predio antiguo sistema. */
	private boolean ib_habilitarCamposPredioAntiguoSistema;

	/** Propiedad ib encontro info 102. */
	private boolean ib_info102;

	/** Propiedad ib matricula doc seleccionada para detalle. */
	private boolean ib_matriculaDocSeleccionadaParaDetalle;

	/** Propiedad ib mostrar boton asociar. */
	private boolean ib_mostrarBotonAsociar;

	/** Propiedad ib mostrar boton consulta SGD complementacion. */
	private boolean ib_mostrarBotonConsultaSGDComplementacion;

	/** Propiedad ib mostrar boton crear. */
	private boolean ib_mostrarBotonCrear;

	/** Propiedad ib mostrar boton generar solicitud complementacion. */
	private boolean ib_mostrarBotonGenerarSolicitudComplementacion;

	/** Propiedad ib mostrar boton guardar complementacion. */
	private boolean ib_mostrarBotonGuardarComplementacion;

	/** Propiedad ib mostrar boton justificar. */
	private boolean ib_mostrarBotonJustificar;

	/** Propiedad ib mostrar boton guardar complementacion. */
	private boolean ib_mostrarBotonTerminarConsultaPredios;

	/** Propiedad ib mostrar boton terminar solicitud. */
	private boolean ib_mostrarBotonTerminarSolicitud;

	/** Propiedad ib mostrar panel justificacion firma. */
	private boolean ib_mostrarComplementacionCirculoDestino;

	/** Propiedad ib mostrar creacion predio. */
	private boolean ib_mostrarCreacionPredio;

	/** Propiedad ib mostrar panel justificacion firma. */
	private boolean ib_mostrarPanelJustificacionFirma;

	/** Propiedad mostrar panel otro circulo. */
	private boolean ib_mostrarPanelOtroCirculo;

	/** Propiedad ib mostrar seleccione matriculas. */
	private boolean ib_mostrarSeleccioneMatriculas;

	/** Propiedad ib mostrar wizard predio. */
	private boolean ib_mostrarWizardPredio;

	/** Propiedad ib ocultar boton atras. */
	private boolean ib_ocultarBotonAtras;

	/** Propiedad ib panel siguiente firma libro. */
	private boolean ib_panelSiguienteFirmaLibro;
	private boolean ib_procesoTerminado102;

	/** Propiedad ib solicita complementacion otro circulo. */
	private boolean ib_solicitaComplementacionOtroCirculo;

	/**
	 * Modifica el valor de accion asociar.
	 *
	 * @param ab_b asigna el valor a la propiedad accion asociar
	 */
	public void setAccionAsociar(boolean ab_b)
	{
		ib_accionAsociar = ab_b;
	}

	/**
	 * Valida la propiedad accion asociar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en accion asociar
	 */
	public boolean isAccionAsociar()
	{
		return ib_accionAsociar;
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
	 * Modifica el valor de bloquear crear asociar.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear crear asociar
	 */
	public void setBloquearCrearAsociar(boolean ab_b)
	{
		ib_bloquearCrearAsociar = ab_b;
	}

	/**
	 * Valida la propiedad bloquear crear asociar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear crear asociar
	 */
	public boolean isBloquearCrearAsociar()
	{
		return ib_bloquearCrearAsociar;
	}

	/**
	 * Modifica la propiedad Cantidad Certificados.
	 *
	 * @param ab_b con el nuevo valor de la propiedad
	 */
	public void setCantidadCertificados(boolean ab_b)
	{
		ib_cantidadCertificados = ab_b;
	}

	/**
	 * Valida la propiedad Cantidad Certificados.
	 *
	 * @return El valor de la propiedad
	 */
	public boolean isCantidadCertificados()
	{
		if((isEtapa101() || (NumericUtils.getLong(getIdEtapa()) == 100)))
			ib_cantidadCertificados = false;
		else
			ib_cantidadCertificados = true;

		return ib_cantidadCertificados;
	}

	/**
	 * Modifica el valor de certificado.
	 *
	 * @param ac_c asigna el valor a la propiedad certificado
	 */
	public void setCertificado(Certificados ac_c)
	{
		ic_certificado = ac_c;
	}

	/**
	 * Retorna el valor de certificado.
	 *
	 * @return el valor de certificado
	 */
	public Certificados getCertificado()
	{
		return ic_certificado;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad lcidt_datos_comp por lcidt_datos_comp
	 */
	public void setCirculosRegistrales(Collection<CirculoRegistral> ac_cr)
	{
		lcidt_datos_comp = ac_cr;
	}

	/**
	 * @return Retorna el valor de la propiedad lcidt_datos_comp
	 */
	public Collection<CirculoRegistral> getCirculosRegistrales()
	{
		return lcidt_datos_comp;
	}

	/**
	 * @param Modifica el valor de la propiedad omplementacionCirculoDestino por omplementacionCirculoDestino
	 */
	public void setComplementacionCirculoDestino(String as_complementacionCirculoDestino)
	{
		is_complementacionCirculoDestino = as_complementacionCirculoDestino;
	}

	/**
	 * @return Retorna el valor de la propiedad omplementacionCirculoDestino
	 */
	public String getComplementacionCirculoDestino()
	{
		return is_complementacionCirculoDestino;
	}

	/**
	 * Modifica el valor de ComplementacionInformacionCirculoDestino.
	 *
	 * @param is_complementacionInformacionCirculoDestino correspondiente al valor de is complementacion informacion circulo destino
	 */
	public void setComplementacionInformacionCirculoDestino(String is_complementacionInformacionCirculoDestino)
	{
		this.is_complementacionInformacionCirculoDestino = is_complementacionInformacionCirculoDestino;
	}

	/**
	 * Retorna Objeto o variable de valor complementacion informacion circulo destino.
	 *
	 * @return el valor de complementacion informacion circulo destino
	 */
	public String getComplementacionInformacionCirculoDestino()
	{
		return is_complementacionInformacionCirculoDestino;
	}

	public void setCompletitudComplementacionCirculoDestino(
	    CompletitudComplementacion icc_completitudComplementacionCirculoDestino
	)
	{
		this.icc_completitudComplementacionCirculoDestino = icc_completitudComplementacionCirculoDestino;
	}

	public CompletitudComplementacion getCompletitudComplementacionCirculoDestino()
	{
		return icc_completitudComplementacionCirculoDestino;
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
	 * Modifica el valor de confirmar siguiente.
	 *
	 * @param ab_b asigna el valor a la propiedad confirmar siguiente
	 */
	public void setConfirmarSiguiente(boolean ab_b)
	{
		ib_confirmarSiguiente = ab_b;
	}

	/**
	 * Valida la propiedad confirmar siguiente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en confirmar siguiente
	 */
	public boolean isConfirmarSiguiente()
	{
		return ib_confirmarSiguiente;
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
	 * Modifica el valor de datos predio para crear.
	 *
	 * @param adas_das asigna el valor a la propiedad datos predio para crear
	 */
	public void setDatosPredioParaCrear(DatosAntSistema adas_das)
	{
		idas_datosPredioParaCrear = adas_das;
	}

	/**
	 * Retorna el valor de datos predio para crear.
	 *
	 * @return el valor de datos predio para crear
	 */
	public DatosAntSistema getDatosPredioParaCrear()
	{
		return idas_datosPredioParaCrear;
	}

	/**
	 * Modifica el valor de Definitivo102.
	 *
	 * @param ib_definitivo102 correspondiente al valor de ib definitivo 102
	 */
	public void setDefinitivo102(boolean ib_definitivo102)
	{
		this.ib_definitivo102 = ib_definitivo102;
	}

	/**
	 * Valida la propiedad definitivo 102.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en definitivo 102
	 */
	public boolean isDefinitivo102()
	{
		return ib_definitivo102;
	}

	/**
	 * Modifica el valor de detalle eliminar.
	 *
	 * @param adasui_dasui asigna el valor a la propiedad detalle eliminar
	 */
	public void setDetalleEliminar(DetalleAntSistemaUI adasui_dasui)
	{
		idasui_detalleEliminar = adasui_dasui;
	}

	/**
	 * Retorna el valor de detalle eliminar.
	 *
	 * @return el valor de detalle eliminar
	 */
	public DetalleAntSistemaUI getDetalleEliminar()
	{
		return idasui_detalleEliminar;
	}

	/**
	 * Modifica el valor de detalles ant sistema consulta.
	 *
	 * @param acdasu_cdasu asigna el valor a la propiedad detalles ant sistema consulta
	 */
	public void setDetallesAntSistemaConsulta(Collection<DetalleAntSistemaUI> acdasu_cdasu)
	{
		idasu_detallesAntSistemaConsulta = acdasu_cdasu;
	}

	/**
	 * Retorna el valor de detalles ant sistema consulta.
	 *
	 * @return el valor de detalles ant sistema consulta
	 */
	public Collection<DetalleAntSistemaUI> getDetallesAntSistemaConsulta()
	{
		return idasu_detallesAntSistemaConsulta;
	}

	/**
	 * Modifica el valor de detalles ant sistema por solicitud.
	 *
	 * @param acdasu_dasu asigna el valor a la propiedad detalles ant sistema por solicitud
	 */
	public void setDetallesAntSistemaPorSolicitud(Collection<DetalleAntSistemaUI> acdasu_dasu)
	{
		icdasu_detallesAntSistemaPorSolicitud = acdasu_dasu;
	}

	/**
	 * Retorna el valor de detalles ant sistema por solicitud.
	 *
	 * @return el valor de detalles ant sistema por solicitud
	 */
	public Collection<DetalleAntSistemaUI> getDetallesAntSistemaPorSolicitud()
	{
		return icdasu_detallesAntSistemaPorSolicitud;
	}

	/**
	 * Modifica el valor de detalles ant sistema seleccionados.
	 *
	 * @param acdasui_dasui asigna el valor a la propiedad detalles ant sistema seleccionados
	 */
	public void setDetallesAntSistemaSeleccionados(Collection<DetalleAntSistemaUI> acdasui_dasui)
	{
		icdasui_detallesAntSistemaSeleccionados = acdasui_dasui;
	}

	/**
	 * Retorna el valor de detalles ant sistema seleccionados.
	 *
	 * @return el valor de detalles ant sistema seleccionados
	 */
	public Collection<DetalleAntSistemaUI> getDetallesAntSistemaSeleccionados()
	{
		return icdasui_detallesAntSistemaSeleccionados;
	}

	/**
	 * Modifica el valor de habilitar campos predio antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar campos predio antiguo sistema
	 */
	public void setHabilitarCamposPredioAntiguoSistema(boolean ab_b)
	{
		ib_habilitarCamposPredioAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad habilitar campos predio antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar campos predio antiguo sistema
	 */
	public boolean isHabilitarCamposPredioAntiguoSistema()
	{
		return ib_habilitarCamposPredioAntiguoSistema;
	}

	/**
	 * @param Modifica el valor de la propiedad ls_idNombreCirculoComplementacion por ls_idNombreCirculoComplementacion
	 */
	public void setIdNombreCirculoComplementacion(String as_incc)
	{
		ls_idNombreCirculoComplementacion = as_incc;
	}

	/**
	 * @return Retorna el valor de la propiedad ls_idNombreCirculoComplementacion
	 */
	public String getIdNombreCirculoComplementacion()
	{
		return ls_idNombreCirculoComplementacion;
	}

	/**
	 * Modifica el valor de imagen firma.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen firma
	 */
	public void setImagenFirma(StreamedContent asc_sc)
	{
		isc_imagenFirma = asc_sc;
	}

	/**
	 * Retorna el valor de imagen firma.
	 *
	 * @return el valor de imagen firma
	 */
	public StreamedContent getImagenFirma()
	{
		return isc_imagenFirma;
	}

	/**
	 *
	 * @param ab_info102
	 */
	public void setInfo102(boolean ab_info102)
	{
		ib_info102 = ab_info102;
	}

	/**
	 *
	 * @return
	 */
	public boolean isInfo102()
	{
		return ib_info102;
	}

	/**
	 * @param Modifica el valor de la propiedad justificacionComplementacion por justificacionComplementacion
	 */
	public void setJustificacionComplementacion(String justificacionComplementacion)
	{
		this.justificacionComplementacion = justificacionComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad justificacionComplementacion
	 */
	public String getJustificacionComplementacion()
	{
		return justificacionComplementacion;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad lcidt_datos_comp por lcidt_datos_comp
	 */
	public void setLcidt_datos_comp(Collection<CirculoRegistral> lcidt_datos_comp)
	{
		this.lcidt_datos_comp = lcidt_datos_comp;
	}

	/**
	 * @return Retorna el valor de la propiedad lcidt_datos_comp
	 */
	public Collection<CirculoRegistral> getLcidt_datos_comp()
	{
		return lcidt_datos_comp;
	}

	/**
	 * Modifica el valor de matricula detalle id.
	 *
	 * @param adasu_dasu asigna el valor a la propiedad matricula detalle id
	 */
	public void setMatriculaDetalleId(DetalleAntSistemaUI adasu_dasu)
	{
		idasu_matriculaDetalleId = adasu_dasu;
	}

	/**
	 * Retorna el valor de matricula detalle id.
	 *
	 * @return el valor de matricula detalle id
	 */
	public DetalleAntSistemaUI getMatriculaDetalleId()
	{
		if(idasu_matriculaDetalleId == null)
			idasu_matriculaDetalleId = new DetalleAntSistemaUI();

		return idasu_matriculaDetalleId;
	}

	/**
	 * Modifica el valor de matricula doc seleccionada para detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad matricula doc seleccionada para detalle
	 */
	public void setMatriculaDocSeleccionadaParaDetalle(boolean ab_b)
	{
		ib_matriculaDocSeleccionadaParaDetalle = ab_b;
	}

	/**
	 * Valida la propiedad matricula doc seleccionada para detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en matricula doc seleccionada para detalle
	 */
	public boolean isMatriculaDocSeleccionadaParaDetalle()
	{
		return ib_matriculaDocSeleccionadaParaDetalle;
	}

	/**
	 * Modifica el valor de matriculas por detalle.
	 *
	 * @param acdas_das asigna el valor a la propiedad matriculas por detalle
	 */
	public void setMatriculasPorDetalle(Collection<DatosAntSistema> acdas_das)
	{
		icdas_matriculasPorDetalle = acdas_das;
	}

	/**
	 * Retorna el valor de matriculas por detalle.
	 *
	 * @return el valor de matriculas por detalle
	 */
	public Collection<DatosAntSistema> getMatriculasPorDetalle()
	{
		return icdas_matriculasPorDetalle;
	}

	/**
	 * Modifica el valor de matriculas seleccionadas.
	 *
	 * @param acdas_das asigna el valor a la propiedad matriculas seleccionadas
	 */
	public void setMatriculasSeleccionadas(Collection<DatosAntSistema> acdas_das)
	{
		icdas_matriculasSeleccionadas = acdas_das;
	}

	/**
	 * Retorna el valor de matriculas seleccionadas.
	 *
	 * @return el valor de matriculas seleccionadas
	 */
	public Collection<DatosAntSistema> getMatriculasSeleccionadas()
	{
		return icdas_matriculasSeleccionadas;
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
	 * Modifica el valor de mostrar boton asociar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton asociar
	 */
	public void setMostrarBotonAsociar(boolean ab_b)
	{
		ib_mostrarBotonAsociar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton asociar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton asociar
	 */
	public boolean isMostrarBotonAsociar()
	{
		return ib_mostrarBotonAsociar;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarBotonConsultaSGDComplementacion por ib_mostrarBotonConsultaSGDComplementacion
	 */
	public void setMostrarBotonConsultaSGDComplementacion(boolean ab_mostrarBotonConsultaSGDComplementacion)
	{
		ib_mostrarBotonConsultaSGDComplementacion = ab_mostrarBotonConsultaSGDComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarBotonConsultaSGDComplementacion
	 */
	public boolean isMostrarBotonConsultaSGDComplementacion()
	{
		return ib_mostrarBotonConsultaSGDComplementacion;
	}

	/**
	 * Modifica el valor de mostrar boton crear.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton crear
	 */
	public void setMostrarBotonCrear(boolean ab_b)
	{
		ib_mostrarBotonCrear = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton crear.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton crear
	 */
	public boolean isMostrarBotonCrear()
	{
		return ib_mostrarBotonCrear;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarBotonGenerarSolicitudComplementacion por ib_mostrarBotonGenerarSolicitudComplementacion
	 */
	public void setMostrarBotonGenerarSolicitudComplementacion(boolean ab_mostrarBoton)
	{
		ib_mostrarBotonGenerarSolicitudComplementacion = ab_mostrarBoton;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarBotonGenerarSolicitudComplementacion
	 */
	public boolean isMostrarBotonGenerarSolicitudComplementacion()
	{
		return ib_mostrarBotonGenerarSolicitudComplementacion;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarBotonGuardarComplementacion por ib_mostrarBotonGuardarComplementacion
	 */
	public void setMostrarBotonGuardarComplementacion(boolean ab_mostrarBotonGuardarComplementacion)
	{
		ib_mostrarBotonGuardarComplementacion = ab_mostrarBotonGuardarComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarBotonGuardarComplementacion
	 */
	public boolean isMostrarBotonGuardarComplementacion()
	{
		return ib_mostrarBotonGuardarComplementacion;
	}

	/**
	 * Modifica el valor de mostrar boton justificar.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton justificar
	 */
	public void setMostrarBotonJustificar(boolean ab_b)
	{
		ib_mostrarBotonJustificar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton justificar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton justificar
	 */
	public boolean isMostrarBotonJustificar()
	{
		return ib_mostrarBotonJustificar;
	}

	/**
	 * Modifica el valor de MostrarBotonTerminarConsultaPredios.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 */
	public void setMostrarBotonTerminarConsultaPredios(boolean ab_b)
	{
		ib_mostrarBotonTerminarConsultaPredios = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton terminar consulta predios.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en mostrar boton terminar consulta predios
	 */
	public boolean isMostrarBotonTerminarConsultaPredios()
	{
		return ib_mostrarBotonTerminarConsultaPredios;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarBotonTerminarSolicitud por ib_mostrarBotonTerminarSolicitud
	 */
	public void setMostrarBotonTerminarSolicitud(boolean ab_mbts)
	{
		ib_mostrarBotonTerminarSolicitud = ab_mbts;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarBotonTerminarSolicitud
	 */
	public boolean isMostrarBotonTerminarSolicitud()
	{
		return ib_mostrarBotonTerminarSolicitud;
	}

	/**
	 * Modifica el valor de MostrarComplementacionCirculoDestino.
	 *
	 * @param ib_mostrarComplementacionCirculoDestino correspondiente al valor de ib mostrar complementacion circulo destino
	 */
	public void setMostrarComplementacionCirculoDestino(boolean ib_mostrarComplementacionCirculoDestino)
	{
		this.ib_mostrarComplementacionCirculoDestino = ib_mostrarComplementacionCirculoDestino;
	}

	/**
	 * Valida la propiedad mostrar complementacion circulo destino.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en mostrar complementacion circulo destino
	 */
	public boolean isMostrarComplementacionCirculoDestino()
	{
		return ib_mostrarComplementacionCirculoDestino;
	}

	/**
	 * Modifica el valor de mostrar creacion predio.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar creacion predio
	 */
	public void setMostrarCreacionPredio(boolean ab_b)
	{
		ib_mostrarCreacionPredio = ab_b;
	}

	/**
	 * Valida la propiedad mostrar creacion predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar creacion predio
	 */
	public boolean isMostrarCreacionPredio()
	{
		return ib_mostrarCreacionPredio;
	}

	/**
	 * Modifica el valor de mostrar justificacion firma.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar justificacion firma
	 */
	public void setMostrarPanelJustificacionFirma(boolean ab_b)
	{
		ib_mostrarPanelJustificacionFirma = ab_b;
	}

	/**
	 * Valida la propiedad mostrar justificacion firma.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar justificacion firma
	 */
	public boolean isMostrarPanelJustificacionFirma()
	{
		return ib_mostrarPanelJustificacionFirma;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad mostrarPanelOtroCirculo por
	 *            mostrarPanelOtroCirculo
	 */
	public void setMostrarPanelOtroCirculo(boolean ab_mostrarPanelOtroCirculo)
	{
		ib_mostrarPanelOtroCirculo = ab_mostrarPanelOtroCirculo;
	}

	/**
	 * @return Retorna el valor de la propiedad mostrarPanelOtroCirculo
	 */
	public boolean isMostrarPanelOtroCirculo()
	{
		return ib_mostrarPanelOtroCirculo;
	}

	/**
	 * Modifica el valor de mostrar seleccione matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar seleccione matriculas
	 */
	public void setMostrarSeleccioneMatriculas(boolean ab_b)
	{
		ib_mostrarSeleccioneMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad mostrar seleccione matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar seleccione matriculas
	 */
	public boolean isMostrarSeleccioneMatriculas()
	{
		return ib_mostrarSeleccioneMatriculas;
	}

	/**
	 * Modifica el valor de mostrar wizard predio.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar wizard predio
	 */
	public void setMostrarWizardPredio(boolean ab_b)
	{
		ib_mostrarWizardPredio = ab_b;
	}

	/**
	 * Valida la propiedad mostrar wizard predio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar wizard predio
	 */
	public boolean isMostrarWizardPredio()
	{
		return ib_mostrarWizardPredio;
	}

	/**
	 * @param Modifica
	 *            el valor de la propiedad nombreCirculoComplementacion por
	 *            nombreCirculoComplementacion
	 */
	public void setNombreCirculoComplementacion(String as_nombreCirculoComplementacion)
	{
		is_nombreCirculoComplementacion = as_nombreCirculoComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad nombreCirculoComplementacion
	 */
	public String getNombreCirculoComplementacion()
	{
		return is_nombreCirculoComplementacion;
	}

	/**
	 * Modifica el valor de Observaciones temporales.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad ObservacionesTemporales
	 */
	public void setObservacionesTemporales(String as_s)
	{
		is_observacionesTemporales = as_s;
	}

	/**
	 * Get observaciones temporales.
	 *
	 * @return el valor de string
	 */
	public String getObservacionesTemporales()
	{
		return is_observacionesTemporales;
	}

	/**
	 * Modifica el valor de ocultar boton atras.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar boton atras
	 */
	public void setOcultarBotonAtras(boolean ab_b)
	{
		ib_ocultarBotonAtras = ab_b;
	}

	/**
	 * Valida la propiedad ocultar boton atras.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar boton atras
	 */
	public boolean isOcultarBotonAtras()
	{
		return ib_ocultarBotonAtras;
	}

	/**
	 * Modifica el valor de panel siguiente firma libro.
	 *
	 * @param ab_b asigna el valor a la propiedad panel siguiente firma libro
	 */
	public void setPanelSiguienteFirmaLibro(boolean ab_b)
	{
		ib_panelSiguienteFirmaLibro = ab_b;
	}

	/**
	 * Valida la propiedad panel siguiente firma libro.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en panel siguiente firma libro
	 */
	public boolean isPanelSiguienteFirmaLibro()
	{
		return ib_panelSiguienteFirmaLibro;
	}

	/**
	 * Modifica el valor de ProcesoTerminado102.
	 *
	 * @param ib_procesoTerminado102 correspondiente al valor de ib proceso terminado 102
	 */
	public void setProcesoTerminado102(boolean ib_procesoTerminado102)
	{
		this.ib_procesoTerminado102 = ib_procesoTerminado102;
	}

	/**
	 * Valida la propiedad proceso terminado 102.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en proceso terminado 102
	 */
	public boolean isProcesoTerminado102()
	{
		return ib_procesoTerminado102;
	}

	/**
	 * Modifica el valor de SolicitaComplementacionOtroCirculo.
	 *
	 * @param ib_solicitaComplementacionOtroCirculo correspondiente al valor de ib solicita complementacion otro circulo
	 */
	public void setSolicitaComplementacionOtroCirculo(boolean ib_solicitaComplementacionOtroCirculo)
	{
		this.ib_solicitaComplementacionOtroCirculo = ib_solicitaComplementacionOtroCirculo;
	}

	/**
	 * Valida la propiedad solicita complementacion otro circulo.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en solicita complementacion otro circulo
	 */
	public boolean isSolicitaComplementacionOtroCirculo()
	{
		return ib_solicitaComplementacionOtroCirculo;
	}

	/**
	 * Modifica el valor de solicitudes matriculas.
	 *
	 * @param asm_sm asigna el valor a la propiedad solicitudes matriculas
	 */
	public void setSolicitudesMatriculas(Collection<SolicitudMatricula> asm_sm)
	{
		icsm_solicitudesMatriculas = asm_sm;
	}

	/**
	 * Retorna el valor de solicitudes matriculas.
	 *
	 * @return el valor de solicitudes matriculas
	 */
	public Collection<SolicitudMatricula> getSolicitudesMatriculas()
	{
		return icsm_solicitudesMatriculas;
	}

	/**
	 * Método encargado de habilitar flag que permitirá asociar las matriculas seleccionadas en el tabulador de busqueda a la solicitud actual.
	 *
	 * @param ab_desicion Flag que indica la desicion de asocicar matriculas o no.
	 */
	public void accionAsociar(boolean ab_desicion)
	{
		try
		{
			if(ab_desicion)
			{
				Collection<DatosAntSistema>    lcdas_matriculasSeleccionadas;
				Collection<SolicitudMatricula> lcsm_SolicitudesMatriculas;
				String                         ls_matriculas;

				ls_matriculas                     = null;
				lcdas_matriculasSeleccionadas     = getMatriculasSeleccionadas();
				lcsm_SolicitudesMatriculas        = new ArrayList<SolicitudMatricula>();

				if(CollectionUtils.isValidCollection(lcdas_matriculasSeleccionadas))
				{
					Iterator<DatosAntSistema> lidas_idas;
					DatosAntSistema           ldas_datoCargado;

					lidas_idas           = lcdas_matriculasSeleccionadas.iterator();
					ldas_datoCargado     = getDatoAntSistemaCargado();

					if(ldas_datoCargado == null)
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

					while(lidas_idas.hasNext())
					{
						DatosAntSistema ldas_matriculaSeleccionada;

						ldas_matriculaSeleccionada = lidas_idas.next();

						if(ldas_matriculaSeleccionada != null)
						{
							SolicitudMatricula lsm_solicitudMatricula;

							lsm_solicitudMatricula = new SolicitudMatricula();

							lsm_solicitudMatricula.setIdCirculo(ldas_matriculaSeleccionada.getIdCirculo());
							lsm_solicitudMatricula.setIdMatricula(
							    NumericUtils.getLong(ldas_matriculaSeleccionada.getIdMatricula())
							);
							lsm_solicitudMatricula.setIdDatosAntSistema(ldas_datoCargado.getIdDatosAntSistema());

							{
								Long ll_cantidad;

								ll_cantidad = ldas_datoCargado.getCantidadCertificados();

								if(NumericUtils.isValidLong(ll_cantidad))
									lsm_solicitudMatricula.setCantidadCertificados(
									    NumericUtils.getBigDecimal(NumericUtils.getLong(ll_cantidad))
									);
							}

							lsm_solicitudMatricula.setIdUsuarioCreacion(getUserId());
							lsm_solicitudMatricula.setIdUsuarioModificacion(getUserId());
							lsm_solicitudMatricula.setIpCreacion(getRemoteIpAddress());
							lsm_solicitudMatricula.setIpModificacion(getRemoteIpAddress());

							lcsm_SolicitudesMatriculas.add(lsm_solicitudMatricula);

							if(ldas_matriculaSeleccionada.isEsPredioInconsistente())
							{
								String ls_idCirculo;
								String ls_matricula;

								ls_idCirculo     = StringUtils.getStringNotNull(
									    ldas_matriculaSeleccionada.getIdCirculo()
									);
								ls_matricula     = StringUtils.getStringNotNull(
									    StringUtils.getString(ldas_matriculaSeleccionada.getIdMatricula())
									);

								if(!StringUtils.isValidString(ls_matriculas))
									ls_matriculas = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ls_matricula;
								else
									ls_matriculas = ls_matriculas + IdentificadoresCommon.SIMBOLO_COMA + ls_idCirculo
										+ IdentificadoresCommon.SIMBOLO_GUION + ls_matricula;
							}
						}
					}
				}

				if(lcsm_SolicitudesMatriculas.isEmpty())
					lcsm_SolicitudesMatriculas = null;

				setSolicitudesMatriculas(lcsm_SolicitudesMatriculas);

				setAccionAsociar(ab_desicion);

				setMostrarBotonAsociar(false);

				if(StringUtils.isValidString(ls_matriculas))
				{
					Object[] aoa_messageArgs;

					aoa_messageArgs     = new String[1];

					aoa_messageArgs[0] = ls_matriculas;

					setMensajePredioInconsistente(
					    getMessages()
						        .getString(
						        MessagesKeys.SOLICITUD_TIENE_MATRICULAS_EN_ESTADO_INCONSISTENTE, aoa_messageArgs
						    )
					);

					addMessage(MessagesKeys.PREDIO_INCONSISTENTE, aoa_messageArgs);
					actualizarComponente(cs_ID_FORMULARIO);
				}
				else
					setMensajePredioInconsistente(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionAsociar", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de precargar la pregunta correspondiente a la firma del registrador para antiguo sistema.
	 */
	public void accionFirmaRegistradorAntSistema()
	{
		try
		{
			Map<String, Object> lmso_datosTurno;

			lmso_datosTurno = getDatosDelTurno();

			if(CollectionUtils.isValidCollection(lmso_datosTurno))
			{
				String ls_idTurno;

				ls_idTurno = StringUtils.getString(lmso_datosTurno.get(IdentificadoresCommon.ID_TURNO));

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno lt_turno;

					lt_turno = icr_certificadosRemote.consultarTurno(
						    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lt_turno != null)
					{
						setJustificacionFirma(lt_turno.getFirmaJustificacion());

						PrimeFaces.current().executeScript("PF('dlgFirmaRegistradorAntSistema').show(); ");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionFirmaRegistradorAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de asociar las matriculas seleccionadas si el flag esta habilitado y marcar el caso para su posterior terminación.
	 */
	public void accionRevisarAntSistema()
	{
		accionRevisarAntSistema(false);
	}

	/**
	 * Método encargado de asociar las matriculas seleccionadas si el flag esta
	 * habilitado y marcar el caso para su posterior terminación.
	 */
	public void accionRevisarAntSistema(boolean ab_respuestaNoFirmar)
	{
		try
		{
			DatosAntSistema ldas_dato;

			ldas_dato = getDatoAntSistemaCargado();

			if(
			    isMostrarBotonAsociar() && CollectionUtils.isValidCollection(getMatriculasSeleccionadas())
				    && !isGrabacionDeMatriculas() && !isEtapa105()
			)
				throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA_ANT_SISTEMA);

			if(
			    isMostrarBotonAsociar() && CollectionUtils.isValidCollection(getMatriculasSeleccionadas())
				    && !isGrabacionDeMatriculas() && isEtapa105()
			)
				accionAsociar(true);

			if((ldas_dato != null) && ab_respuestaNoFirmar)
				ldas_dato.setJustificacionFirma(false);

			setRevisadoAntSistema(
			    iasr_antiguoSistemaRemote.revisadoAntSistema(
			        ldas_dato, getDetallesAntSistemaSeleccionados(), getSolicitudesMatriculas(), getIdTurnoHistoria(),
			        getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			if(ldas_dato != null)
				ldas_dato.setRevisadoAntSistema(isRevisadoAntSistema() ? EstadoCommon.S : EstadoCommon.N);

			if(isAccionAsociar())
				elegirAccionAntSistema(EstadoCommon.A);

			seleccionarPredio();
			actualizarObservacionesTurnoHistoria();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionRevisarAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Actualizar observaciones turno historia.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarObservacionesTurnoHistoria()
	    throws B2BException
	{
		try
		{
			String ls_turnoHistoria;

			ls_turnoHistoria = getIdTurnoHistoria();

			if(StringUtils.isValidString(ls_turnoHistoria))
			{
				TurnoHistoria lth_th;
				String        ls_mensajePredioInconsistente;
				String        ls_observacionesProceso;

				ls_mensajePredioInconsistente     = getMensajePredioInconsistente();
				ls_observacionesProceso           = getObservaciones();
				lth_th                            = new TurnoHistoria();

				if(
				    StringUtils.isValidString(ls_mensajePredioInconsistente)
					    && !ls_observacionesProceso.contains(ls_mensajePredioInconsistente)
				)
					setObservaciones(
					    ls_mensajePredioInconsistente + IdentificadoresCommon.SALTO_LINEA + ls_observacionesProceso
					);

				lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_turnoHistoria));
				lth_th.setObservaciones(getObservaciones());

				isr_suspensionRemote.actualizarObservacionesTurnoHistoria(
				    lth_th, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarObservacionesTurnoHistoria", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de agregar una anotación temporal
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarAnotacionTemporal()
	    throws B2BException
	{
		agregarAnotacion(false, null, null);

		Anotacion la_anotacionTemp;

		la_anotacionTemp = getAnotacionTemporal();

		if(la_anotacionTemp != null)
		{
			Anotacion             la_anotacion;
			Collection<Anotacion> lca_data;
			TurnoHistoria         lth_turnoHistoria;

			la_anotacion          = new Anotacion();
			lca_data              = new ArrayList<Anotacion>();
			lth_turnoHistoria     = new TurnoHistoria();

			lca_data.add(la_anotacionTemp);
			la_anotacion.setDatosAntiguoSistema(getDatosAntSistemaAnotacion());
			la_anotacion.setAccPredioRegistro(getAccPredioRegistro());
			la_anotacion.setAnotacionesAgregadas(lca_data);
			la_anotacion.setBloqueo(true);
			la_anotacion.setTemporal(true);

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			la_anotacion.setTurnoHistoria(lth_turnoHistoria);

			iasr_antiguoSistemaRemote.salvarAnotaciones(
			    la_anotacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setAnotacionTemporal(null);
		}
	}

	/**
	 * Método que se encarga de agregar los detalles a Detalle datos antiguo sistema.
	 *
	 * @param adasu_parametros Objeto que contiene los datos necesarios para agregar a la consulta detalle_ant_sistema
	 */
	public void agregarDetalleAntSistema(DetalleAntSistemaUI adasu_parametros)
	{
		try
		{
			if(adasu_parametros != null)
			{
				Collection<DetalleAntSistemaUI> lcdasu_detalle;

				lcdasu_detalle = getDetallesAntSistemaPorSolicitud();
				adasu_parametros.setEliminar(true);

				if(CollectionUtils.isValidCollection(lcdasu_detalle))
				{
					Iterator<DetalleAntSistemaUI> lidasu_iterador;
					boolean                       lb_encontrado;

					lidasu_iterador     = lcdasu_detalle.iterator();
					lb_encontrado       = false;

					while(lidasu_iterador.hasNext() && !lb_encontrado)
					{
						DetalleAntSistemaUI ldasu_detalle;

						ldasu_detalle = lidasu_iterador.next();

						if(ldasu_detalle != null)
						{
							String ls_idDatosAntSistema;
							String ls_idDetalleAntSistema;
							String ls_idDatosAntSistemaAgregar;
							String ls_idDetalleAntSistemaAgregar;

							ls_idDatosAntSistema              = StringUtils.getStringNotNull(
								    ldasu_detalle.getIdDatosAntSistema()
								);
							ls_idDetalleAntSistema            = StringUtils.getStringNotNull(
								    ldasu_detalle.getIdDetalleAntSistema()
								);
							ls_idDatosAntSistemaAgregar       = StringUtils.getStringNotNull(
								    adasu_parametros.getIdDatosAntSistema()
								);
							ls_idDetalleAntSistemaAgregar     = StringUtils.getStringNotNull(
								    adasu_parametros.getIdDetalleAntSistema()
								);

							lb_encontrado = ls_idDatosAntSistema.equalsIgnoreCase(ls_idDatosAntSistemaAgregar)
									&& ls_idDetalleAntSistema.equalsIgnoreCase(ls_idDetalleAntSistemaAgregar);
						}
					}

					if(lb_encontrado)
					{
						adasu_parametros.setEliminar(false);
						throw new B2BException(ErrorKeys.DETALLE_YA_EXISTE);
					}

					validarMatriculas(adasu_parametros.getMatriculas());
					lcdasu_detalle.add(adasu_parametros);
				}
				else
				{
					lcdasu_detalle = new ArrayList<DetalleAntSistemaUI>();
					validarMatriculas(adasu_parametros.getMatriculas());
					lcdasu_detalle.add(adasu_parametros);

					setDetallesAntSistemaPorSolicitud(lcdasu_detalle);
				}

				addMessage(MessagesKeys.DETALLE_AGREGADO_SATISFACTORIAMENTE);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarDetalleAntSistema", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		}
	}

	/**
	 * Sobrecarga de método que se encarga de agregar las matriculas seleccionadas
	 * por pantalla a la colección de detalles UI.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aplicarSeleccion()
	    throws B2BException
	{
		try
		{
			aplicarSeleccion(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("aplicarSeleccion", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().executeScript("PF('" + cs_ID_MATRICULA_POR_DETALLE + "').hide();");
			PrimeFaces.current().ajax().update("idFormAntSistema:globalMsg");
		}

		PrimeFaces.current().executeScript("PF('" + cs_ID_MATRICULA_POR_DETALLE + "').hide();");
	}

	/**
	 * Método que se encarga de agregar las matriculas seleccionadas por pantalla a
	 * la colección de detalles UI.
	 *
	 * @param lb_accion            Variable boolean enviada por pantalla encargada de cerrar el
	 *            pop-up cuando se encuentra en false sin ejecutar operaciones de
	 *            negocio.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void aplicarSeleccion(boolean lb_accion)
	    throws B2BException
	{
		try
		{
			Collection<DetalleAntSistemaUI> lcdas_detalle;

			lcdas_detalle = getDetallesAntSistemaPorSolicitud();

			if(CollectionUtils.isValidCollection(lcdas_detalle) && lb_accion)
			{
				Iterator<DetalleAntSistemaUI> li_iterator;
				DetalleAntSistemaUI           ldasu_seleccion;
				boolean                       lb_finalizar;

				li_iterator         = lcdas_detalle.iterator();
				ldasu_seleccion     = getMatriculaDetalleId();
				lb_finalizar        = false;

				Collection<DatosAntSistema> lcdas_datosAntiguoSistema;
				lcdas_datosAntiguoSistema = getDatosAntSistema();

				while(li_iterator.hasNext() && !lb_finalizar)
				{
					DetalleAntSistemaUI ldas_iterador;

					ldas_iterador = li_iterator.next();

					if(ldas_iterador != null)
					{
						String ls_idDatosAntSistema;
						String ls_idDetalleAntSistema;
						String ls_idDatosAntSistemaSel;
						String ls_idDetalleAntSistemaSel;

						ls_idDatosAntSistema          = StringUtils.getStringNotNull(
							    ldas_iterador.getIdDatosAntSistema()
							);
						ls_idDetalleAntSistema        = StringUtils.getStringNotNull(
							    ldas_iterador.getIdDetalleAntSistema()
							);
						ls_idDatosAntSistemaSel       = StringUtils.getStringNotNull(
							    ldasu_seleccion.getIdDatosAntSistema()
							);
						ls_idDetalleAntSistemaSel     = StringUtils.getStringNotNull(
							    ldasu_seleccion.getIdDetalleAntSistema()
							);

						lb_finalizar = ls_idDatosAntSistema.equalsIgnoreCase(ls_idDatosAntSistemaSel)
								&& ls_idDetalleAntSistema.equalsIgnoreCase(ls_idDetalleAntSistemaSel);

						if(lb_finalizar)
						{
							Collection<DatosAntSistema> lcdas_matriculas;

							lcdas_matriculas = getMatriculasPorDetalle();

							if(CollectionUtils.isValidCollection(lcdas_matriculas))
							{
								Iterator<DatosAntSistema> lidas_matriculas;
								boolean                   lb_matriculaSeleccionada;

								lidas_matriculas             = lcdas_matriculas.iterator();
								lb_matriculaSeleccionada     = false;

								while(lidas_matriculas.hasNext() && !lb_matriculaSeleccionada)
								{
									DatosAntSistema ldas_matriculas;

									ldas_matriculas = lidas_matriculas.next();

									if((ldas_matriculas != null) && (lcdas_datosAntiguoSistema != null))
									{
										for(DatosAntSistema ldas_matriculasDelTurno : lcdas_datosAntiguoSistema)
										{
											String   ls_matriculaInmobiliaria;
											String   ls_circuloMatricula;
											String[] las_cirMat;

											ls_matriculaInmobiliaria     = StringUtils.getString(
												    ldas_matriculas.getIdMatricula()
												);
											ls_circuloMatricula          = ldas_matriculasDelTurno
													.getMatriculasAsociadas();

											if(StringUtils.isValidString(ls_circuloMatricula))
											{
												las_cirMat              = ls_circuloMatricula.split("-");
												ls_circuloMatricula     = las_cirMat[1];

												ls_matriculaInmobiliaria = StringUtils.getString(
													    ldas_matriculas.getIdMatricula()
													);

												if(ls_matriculaInmobiliaria.equals(ls_circuloMatricula))
													throw new B2BException(ErrorKeys.ERROR_MATRICULA_REPETIDA);
											}
										}

										lb_matriculaSeleccionada = ldas_matriculas.isMatriculaSeleccionada();
									}
								}

								ldas_iterador.setDetalleMatriculaSeleccionado(lb_matriculaSeleccionada);
							}

							ldas_iterador.setMatriculas(lcdas_matriculas);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 *
	 */
	public void buscarCompletitudComplementacionByTurno()
	    throws B2BException
	{
		try
		{
			CompletitudComplementacion lcc_completitudDocumentacion;
			lcc_completitudDocumentacion = new CompletitudComplementacion();

			lcc_completitudDocumentacion.setIdTurno(getIdTurno());

			lcc_completitudDocumentacion = iasr_antiguoSistemaRemote.buscarCompletitudComplementacionByTurno(
				    lcc_completitudDocumentacion, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

			if(lcc_completitudDocumentacion != null)
			{
				setJustificacionComplementacion(lcc_completitudDocumentacion.getJjustificacion());
				setCompletitudComplementacionCirculoDestino(lcc_completitudDocumentacion);

				String       ls_circuloDestino = lcc_completitudDocumentacion.getIdCirculoRegistralDestino();
				DatosBasicos dt_basicos;
				dt_basicos                     = getDatosBasicos();

				if(dt_basicos != null)
				{
					UbicacionZonaRegistral uzr_basicos;

					uzr_basicos = dt_basicos.getUbicacion();

					if(uzr_basicos != null)
					{
						findCirculoRegistralByTurnoEtapa();

						CirculoRegistral icr_basicos;

						icr_basicos = uzr_basicos.getCirculoRegistral();

						if(icr_basicos != null)
							icr_basicos.setIdCirculo(lcc_completitudDocumentacion.getIdCirculoRegistralDestino());

						buscarNombreCirculoComplementacion();

						CirculoRegistral lcr_circuloDestino;

						if(StringUtils.isValidString(ls_circuloDestino))
						{
							lcr_circuloDestino = findCodigoNombreCirculoDestino(ls_circuloDestino);
							setIdNombreCirculoComplementacion(lcr_circuloDestino.getCodigoNombre());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarCompletitudComplementacionByTurno", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Buscar nombre circulo complementacion.
	 */
	public void buscarNombreCirculoComplementacion()
	{
		Collection<CirculoRegistral> lcidt_datos;
		DatosBasicos                 dt_circulo_registral;
		UbicacionZonaRegistral       uzr_circulo_registral;
		CirculoRegistral             cr_circulo_registral;
		String                       ls_nombre_circulo1;
		String                       ls_nombre_circulo2;

		ls_nombre_circulo1     = null;
		ls_nombre_circulo2     = null;

		dt_circulo_registral = getDatosBasicos();

		if(dt_circulo_registral != null)
		{
			uzr_circulo_registral = dt_circulo_registral.getUbicacion();

			if(uzr_circulo_registral != null)
			{
				cr_circulo_registral = uzr_circulo_registral.getCirculoRegistral();

				if(cr_circulo_registral != null)
				{
					lcidt_datos = getCirculosRegistrales();

					if(CollectionUtils.isValidCollection(lcidt_datos))
					{
						for(CirculoRegistral icr_circulo_registral : lcidt_datos)
						{
							if(icr_circulo_registral != null)
							{
								ls_nombre_circulo1     = icr_circulo_registral.getIdCirculo();
								ls_nombre_circulo2     = cr_circulo_registral.getIdCirculo();

								if(
								    StringUtils.isValidString(ls_nombre_circulo1)
									    && StringUtils.isValidString(ls_nombre_circulo2)
									    && ls_nombre_circulo1.equals(ls_nombre_circulo2)
								)
								{
									setNombreCirculoComplementacion(icr_circulo_registral.getNombre());
									PrimeFaces.current().ajax().update("idFormAntSistema");
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Verifíca que solamente se seleccione una matrícula de un detalle.
	 *
	 * @param adas_datosAntSistema Objeto contenedor de la matrícula seleccionada
	 */
	public void cambiarSeleccionMatriculaDetalle(DatosAntSistema adas_datosAntSistema)
	{
		if(adas_datosAntSistema != null)
		{
			Collection<DatosAntSistema> lcdas_matriculas;

			lcdas_matriculas = getMatriculasPorDetalle();

			if(CollectionUtils.isValidCollection(lcdas_matriculas))
			{
				for(DatosAntSistema ldas_dato : lcdas_matriculas)
				{
					if((ldas_dato != null) && (ldas_dato != adas_datosAntSistema))
						ldas_dato.setMatriculaSeleccionada(false);
				}
			}
		}
	}

	/**
	 * Método encargado de actualizar la informacion de los municipios relacionados al circulo seleccionado.
	 */
	public void cambioCirculoBusqueda()
	{
		try
		{
			DetalleAntSistemaUI ldasc_dasc;

			ldasc_dasc = getDetalleAntSistemaConsulta();

			if(ldasc_dasc != null)
			{
				String ls_idCirculo;

				ls_idCirculo = ldasc_dasc.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					ZonaRegistral lzr_zonaRegistral;

					lzr_zonaRegistral = irr_registroRemote.findDatosZonaRegistralByCirculo(
						    ls_idCirculo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lzr_zonaRegistral != null)
						ldasc_dasc.setIdDepartamentoTomo(lzr_zonaRegistral.getIdDepartamento());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambioCirculoBusqueda", lb2be_e);
		}
	}

	/**
	 * Hace un reset a las variables del wizard.
	 */
	public void cancelarProcesoWizard()
	{
		PrimeFaces lpf_prime;
		lpf_prime = PrimeFaces.current();

		obtenerDatosAntSistema();

		setDatoAntSistemaCargado(null);
		setMostrarWizardPredio(false);
		setDetallesAntSistema(null);
		setPredioSeleccionado(false);
		setDetalleAntSistemaConsulta(null);
		setDetallesAntSistemaConsulta(null);
		setTipoConsulta(null);
		setOcultarBotonSiguiente(false);

		verificarRevisionPredios();

		lpf_prime.executeScript("PF('wvCancelarProceso').hide(); ");
		lpf_prime.executeScript("PF('wvDatosPredioSeleccionado').collapse();");
	}

	/**
	 * Metodo encargado de cargar las anotaciones de los predios temporales.
	 *
	 * @param aapr_arg Objeto contenedor del predio seleccionado para carga de anotaciones.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarAnotaciones(AccPredioRegistro aapr_arg)
	    throws B2BException
	{
		if(aapr_arg != null)
		{
			try
			{
				ConsultaPredio lcp_cp;
				PredioRegistro lopr_apr;

				lopr_apr = new PredioRegistro();

				lopr_apr.setIdCirculo(aapr_arg.getIdCirculo());
				lopr_apr.setIdMatricula(NumericUtils.getLong(aapr_arg.getIdMatricula()));
				lopr_apr.setTurnoBloqueo(getIdTurno());
				lopr_apr.setIdTipoPredio(IdentificadoresCommon.TEMPORAL);

				lcp_cp = icepe_consultaPredioRemote.findInfoTabs(lopr_apr, getUserId());

				if(lcp_cp != null)
				{
					Map<String, Object> options;

					options = new HashMap<String, Object>();

					setDataModel(null);
					consultarAnotaciones(lcp_cp, 1, ":idFormAntSistema:globalMsg");

					options.put("modal", BooleanUtils.getBoolean(EstadoCommon.S));
					options.put("width", NumericUtils.getInteger(1150));
					options.put("height", NumericUtils.getInteger(500));
					options.put("contentWidth", "100%");
					options.put("contentHeight", "100%");

					PrimeFaces.current().dialog().openDynamic("anotacionesAntSistema", options, null);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarAnotaciones", lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
			}
		}
	}

	/**
	 * Metodos encargado de cargar los datos seleccionados en la pestaña de busqueda
	 * para que puedan ser trabajados en la pestaña Decisión antiguo sistema.
	 */
	public void cargarDatosDecisiones()
	{
		Collection<DetalleAntSistemaUI> lcdasu_detalle;
		Collection<DetalleAntSistemaUI> lcdasu_seleccionados;
		Collection<DatosAntSistema>     lcdas_matriculasSeleccionadas;

		lcdasu_detalle                    = getDetallesAntSistemaPorSolicitud();
		lcdasu_seleccionados              = new ArrayList<DetalleAntSistemaUI>();
		lcdas_matriculasSeleccionadas     = new ArrayList<DatosAntSistema>();

		if(CollectionUtils.isValidCollection(lcdasu_detalle))
		{
			for(DetalleAntSistemaUI ldasu_iterador : lcdasu_detalle)
			{
				if((ldasu_iterador != null) && ldasu_iterador.isDetalleMatriculaSeleccionado())
				{
					Collection<DatosAntSistema> lcdas_matriculas;

					lcdas_matriculas = ldasu_iterador.getMatriculas();

					if(CollectionUtils.isValidCollection(lcdas_matriculas))
					{
						Map<String, DatosAntSistema> lmsd_map;

						lmsd_map = new HashMap<String, DatosAntSistema>();

						for(DatosAntSistema ldas_matriculas : lcdas_matriculas)
						{
							if((ldas_matriculas != null) && ldas_matriculas.isMatriculaSeleccionada())
							{
								StringBuilder lsb_sb;

								lsb_sb = new StringBuilder();

								lsb_sb.append(ldas_matriculas.getIdCirculo());
								lsb_sb.append("-");
								lsb_sb.append(ldas_matriculas.getIdMatricula());

								{
									String ls_tmp;

									ls_tmp = lsb_sb.toString();

									if(!lmsd_map.containsKey(ls_tmp))
										lmsd_map.put(ls_tmp, ldas_matriculas);
								}
							}
						}

						lcdas_matriculasSeleccionadas.addAll(lmsd_map.values());
					}

					lcdasu_seleccionados.add(ldasu_iterador);
				}
			}

			setDetallesAntSistemaSeleccionados(
			    CollectionUtils.isValidCollection(lcdasu_seleccionados) ? lcdasu_seleccionados : null
			);
			setMatriculasSeleccionadas(
			    CollectionUtils.isValidCollection(lcdas_matriculasSeleccionadas) ? lcdas_matriculasSeleccionadas : null
			);
		}
	}

	/**
	 * Método encargado de cargar la información básica para un nuevo predio.
	 */
	public void cargarDatosPredioNuevo()
	{
		try
		{
			Solicitud     ls_solicitud;
			ZonaRegistral lzr_zonaRegistral;
			String        ls_idSolicitud;

			ls_solicitud       = new Solicitud();
			ls_idSolicitud     = getIdSolicitud();

			ls_solicitud.setIdSolicitud(ls_idSolicitud);

			lzr_zonaRegistral = irr_registroRemote.findCirculoRegistralActoAntiguoSistema(ls_solicitud);

			if(lzr_zonaRegistral != null)
			{
				DatosAntSistema ldas_datos;

				ldas_datos = new DatosAntSistema();

				ldas_datos.setIdCirculo(lzr_zonaRegistral.getIdCirculo());
				ldas_datos.setIdPais(lzr_zonaRegistral.getIdPais());
				ldas_datos.setIdDepartamento(lzr_zonaRegistral.getIdDepartamento());
				ldas_datos.setIdSolicitud(ls_idSolicitud);

				setDatosPredioParaCrear(ldas_datos);
			}

			setMostrarCreacionPredio(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosPredioNuevo", lb2be_e);
			addMessage(lb2be_e);
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
		long ll_idEtapa;
		long ll_idMotivo;

		ll_idEtapa      = NumericUtils.getLong(getIdEtapa());
		ll_idMotivo     = NumericUtils.getLong(getMotivoTramite());
	}

	/**
	 * Método encargado de cargar las matriculas asociadas a el detalle_ant_sistema
	 * seleccionados en pantalla.
	 *
	 * @param adasu_parametros
	 *            Objeto que contiene las matriculas de detalle ant sistema.
	 */
	public void cargarMatriculasPorDetalle(DetalleAntSistemaUI adasu_parametros)
	{
		cargarMatriculasPorDetalle(adasu_parametros, true);
	}

	/**
	 * Método encargado de cargar las matriculas asociadas a el detalle_ant_sistema
	 * seleccionados en pantalla.
	 *
	 * @param adasu_parametros            Objeto que contiene las matriculas de detalle ant sistema.
	 * @param ab_mostrarSeleccione            Flag que indica se debe mostrarse la columna de seleccione.
	 */
	public void cargarMatriculasPorDetalle(DetalleAntSistemaUI adasu_parametros, boolean ab_mostrarSeleccione)
	{
		if(adasu_parametros != null)
		{
			setMatriculasPorDetalle(adasu_parametros.getMatriculas());
			setMatriculaDetalleId(adasu_parametros);
			setMostrarSeleccioneMatriculas(ab_mostrarSeleccione);

			{
				PrimeFaces lpf_current;

				lpf_current = PrimeFaces.current();

				lpf_current.executeScript("PF('" + cs_ID_MATRICULA_POR_DETALLE + "').show();");
				lpf_current.ajax().update(cs_ID_MATRICULA_POR_DETALLE);
			}
		}
	}

	/**
	 * Método encargado de carga las observaciones ya almacenadas en el turno historia actual.
	 */
	public void cargarObservacionesTramiteActual()
	{
		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
				setObservaciones(lth_turnoHistoria.getObservaciones());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarObservacionesTramiteActual", lb2be_e);
		}
	}

	/**
	 * Cargar tipo oficina documento ant sistema.
	 *
	 * @return el valor de collection
	 */
	public Collection<TipoOficina> cargarTipoOficinaDocumentoAntSistema()
	{
		return cargarTipoOficinaDocumentoAntSistema(false);
	}

	/**
	 * Método de consulta de las oficinas de origen del documento.
	 *
	 * @param ab_documento de ab documento
	 * @return una collección de tipo oficina
	 */
	public Collection<TipoOficina> cargarTipoOficinaDocumentoAntSistema(boolean ab_documento)
	{
		Collection<TipoOficina> lcto_return;

		lcto_return = null;

		try
		{
			DetalleAntSistemaUI lcdd_cddd;
			lcdd_cddd = getDetalleAntSistemaConsulta();

			Documento ld_d;
			ld_d = ab_documento ? getDocumentoDetalleCargado()
				                : ((lcdd_cddd != null) ? lcdd_cddd.getDocumentoConsulta() : null);

			if(ld_d != null)
				lcto_return = irr_referenceRemote.findTipoOficina(ld_d);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("cargarTipoOficinaDocumento", lb2be_e);
		}

		return lcto_return;
	}

	/**
	 * Modifica flags de la pantalla para mostrar botones de navegación.
	 */
	public void confirmarSigBusqueda()
	{
		setConfirmarSiguiente(true);
	}

	/**
	 * Método que consulta en la tabla detalle_ant_sistema con los datos digitados por pantalla.
	 */
	public void consultaDetalleAntSistema()
	{
		consultaDetalleAntSistema(false, null);
	}

	/**
	 * Método que consulta en la tabla detalle_ant_sistema con los datos digitados por pantalla.
	 *
	 * @param ab_calificacion indica si viene de calificación
	 */
	public void consultaDetalleAntSistema(boolean ab_calificacion)
	{
		consultaDetalleAntSistema(ab_calificacion, null);
	}

	/**
	 * Método que consulta en la tabla detalle_ant_sistema con los datos digitados por pantalla.
	 *
	 * @param ab_calificacion indica si viene de calificación
	 * @param as_idProceso Contiene el id proceso a evaluar.
	 */
	public void consultaDetalleAntSistema(boolean ab_calificacion, String as_idProceso)
	{
		DetalleAntSistemaUI ldasu_parametros;

		ldasu_parametros = getDetalleAntSistemaConsulta();

		Certificados lc_certificados;

		lc_certificados = getCertificado();

		if(lc_certificados != null)
		{
			Subproceso ls_subproceso;
			ls_subproceso = lc_certificados.getSubProceso();

			if(ls_subproceso != null)
			{
				String ls_idSubproceso;
				ls_idSubproceso = ls_subproceso.getIdSubproceso();

				if(
				    ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_12)
					    || ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_14)
				)
					setConsultaRealizada(true);
			}
		}

		if(ldasu_parametros != null)
		{
			try
			{
				FacesContext lfc_context;
				boolean      lb_focus;

				lb_focus        = true;
				lfc_context     = FacesContext.getCurrentInstance();

				lb_focus     = validateStyles(
					    cs_ID_LIBRO, lfc_context, ldasu_parametros.getIdLibroAntSistema(), lb_focus
					);

				lb_focus     = validateStyles(cs_ID_TOMO, lfc_context, ldasu_parametros.getTomo(), lb_focus);

				lb_focus     = validateStyles(cs_ID_TIPO_PARTIDA, lfc_context, ldasu_parametros.getPartida(), lb_focus);

				lb_focus = validateStyles(
					    cs_ID_NUMERO_PARTIDA, lfc_context, ldasu_parametros.getNumeroPartida(), lb_focus
					);

				ldasu_parametros.setIdProceso(as_idProceso);
				ldasu_parametros.setDatosAntSistema(getDatoAntSistemaCargado());

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

					ldasu_parametros.setCirculoRegistral(
					    new CirculoRegistral(ls_idCirculo, IdentificadoresCommon.SIN_INFORMACION)
					);
				}

				{
					Collection<DetalleAntSistemaUI> lcdasu_detallesConsultados;
					boolean                         lb_conRegistros;

					lcdasu_detallesConsultados     = iasr_antiguoSistemaRemote.consultaDetalleAntSistema(
						    ldasu_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					lb_conRegistros = CollectionUtils.isValidCollection(lcdasu_detallesConsultados);

					setMostrarBotonCrear(!lb_conRegistros);

					if(lb_conRegistros)
					{
						setDetallesAntSistemaConsulta(lcdasu_detallesConsultados);

						addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
						setConsultaRealizada(true);
					}
					else if(ab_calificacion)
					{
						setConsultaRealizada(true);
						throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_ANT_SIS_CALIFICACION);
					}
					else
					{
						long ls_etapa;
						ls_etapa = NumericUtils.getLong(getIdEtapa());

						if(
						    (ls_etapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
							    || (ls_etapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
							    || (ls_etapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
						)
						{
							setConsultaRealizada(true);
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
						}
						else
						{
							setConsultaRealizada(true);
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CREAR_DETALLE);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				setDetallesAntSistemaConsulta(null);
				clh_LOGGER.error("consultaDetalleAntSistema", lb2be_e);
				addMessage(lb2be_e);
			}

			PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		}
	}

	/**
	 * Consulta SDG.
	 */
	public void consultaSGD()
	{
		try
		{
			Map<String, Object> lmso_datosTurno;

			lmso_datosTurno = getDatosDelTurno();

			if(CollectionUtils.isValidCollection(lmso_datosTurno))
			{
				Object lo_idTurno;

				lo_idTurno = lmso_datosTurno.get("ID_TURNO");

				if((lo_idTurno != null) && lo_idTurno instanceof String)
				{
					String ls_idturno;

					ls_idturno = StringUtils.getString(lo_idTurno);

					if(StringUtils.isValidString(ls_idturno))
						accionSGD(new DocumentoOWCC(ls_idturno, false), NavegacionCommon.ANT_SISTEMA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSGD", lb2be_e);

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

			addMessage(new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
		}
		catch(IOException lioe_ioe)
		{
			clh_LOGGER.error("consultaSGD", lioe_ioe);

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

			addMessage(new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
		}
	}

	/**
	 * Prepara la pantalla para avanzar un tramite de etapa 101 sin predios relacionados.
	 */
	public void continuarSinPredios101()
	{
		setRevisadoAntSistema(true);
		setBloquearCrearAsociar(true);
		setInformeDeBusquedaAntSistema(true);

		Ajax la_ajax;

		la_ajax = PrimeFaces.current().ajax();

		la_ajax.update("idFormAntSistema:idCBAsociarInforme");
		la_ajax.update("idFormAntSistema:idCBSiguiente");
		la_ajax.update("idFormAntSistema:opBotonesAntSistema");
	}

	/**
	 * Metodo encargado de crear un detalle antiguo sistema cuando no existe.
	 */
	public void crearDetalleAntSistema()
	{
		DetalleAntSistemaUI ldasu_parametros;

		ldasu_parametros = getDetalleAntSistemaConsulta();

		if(ldasu_parametros != null)
		{
			try
			{
				FacesContext    lfc_context;
				boolean         lb_focus;
				DatosAntSistema ldas_datosPredio;

				lb_focus             = true;
				lfc_context          = FacesContext.getCurrentInstance();
				ldas_datosPredio     = getDatoAntSistemaCargado();

				if((ldas_datosPredio == null) || !StringUtils.isValidString(ldas_datosPredio.getIdDatosAntSistema()))
				{
					setMostrarCreacionPredio(true);

					cargarDatosPredioNuevo();

					addMessage(MessagesKeys.SIN_PREDIO_ANT_SISTEMA);
					PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
				}
				else
				{
					lb_focus     = validateStyles(
						    cs_ID_LIBRO, lfc_context, ldasu_parametros.getIdLibroAntSistema(), lb_focus
						);

					lb_focus     = validateStyles(cs_ID_TOMO, lfc_context, ldasu_parametros.getTomo(), lb_focus);

					lb_focus = validateStyles(
						    cs_ID_NUMERO_PARTIDA, lfc_context, ldasu_parametros.getNumeroPartida(), lb_focus
						);

					ldasu_parametros.setDatosAntSistema(getDatoAntSistemaCargado());

					{
						DetalleAntSistemaUI ldasu_detallesCreado;

						ldasu_detallesCreado = iasr_antiguoSistemaRemote.crearDetalleAntSistema(
							    ldasu_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(ldasu_detallesCreado != null)
						{
							Collection<DetalleAntSistemaUI> lcdasu_detalle;

							lcdasu_detalle = getDetallesAntSistemaPorSolicitud();

							if(CollectionUtils.isValidCollection(lcdasu_detalle))
								lcdasu_detalle.add(ldasu_detallesCreado);
							else
							{
								lcdasu_detalle = new ArrayList<DetalleAntSistemaUI>();
								lcdasu_detalle.add(ldasu_detallesCreado);

								setDetallesAntSistemaPorSolicitud(lcdasu_detalle);
							}

							setMostrarBotonCrear(false);

							addMessage(MessagesKeys.DETALLE_CREADO_SATISFACTORIAMENTE);
							PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				setMostrarBotonCrear(true);
				clh_LOGGER.error("consultaDetalleAntSistema", lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
			}
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			boolean lb_enviados;

			lb_enviados = validarEnvioDocumentosOWCC(getIdTurnoHistoria());

			setDocumentosEnviados(lb_enviados);

			if(lb_enviados)
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_FORMULARIO + ":idBotonConsultaSGD1:");
			actualizarComponente(cs_ID_FORMULARIO + ":idBotonConsultaSGD2:");
			actualizarComponente(cs_ID_FORMULARIO + ":idBotonConsultaSGD3:");
			actualizarComponente(cs_ID_FORMULARIO + ":idCBTerminarProcesoAntSistema2:");
			actualizarComponente(cs_ID_FORMULARIO + ":idBotonGenerarDocumentos105:");
			actualizarComponente(cs_ID_FORMULARIO + ":consultaSGDComplementacion:");
			actualizarComponente(cs_ID_FORMULARIO + ":idCBTerminarProcesoAntSistema3:");
		}
	}

	/**
	 * Sobrecarga de metodo encargado de marcar el detalle seleccionado por pantalla para su eliminación.
	 *
	 * @param adasu_parametros objeto contenedor del detalle a marcar.
	 */
	public void eliminarDetalleAntSistema(DetalleAntSistemaUI adasu_parametros)
	{
		if(adasu_parametros != null)
			setDetalleEliminar(adasu_parametros);
	}

	/**
	 * Sobrecarga de metodo encargado de eliminar el detalle seleccionado por pantalla.
	 */
	public void eliminarDetalleAntSistema()
	{
		eliminarDetalleAntSistema(true);
	}

	/**
	 * Sobrecarga de metodo encargado de eliminar el detalle seleccionado por pantalla.
	 *
	 * @param ab_eliminar flag que indica si se elimina el detalle marcado o no.
	 */
	public void eliminarDetalleAntSistema(boolean ab_eliminar)
	{
		Collection<DetalleAntSistemaUI> lcdasu_detalle;

		lcdasu_detalle = getDetallesAntSistemaPorSolicitud();

		try
		{
			DetalleAntSistemaUI adasu_parametros;

			adasu_parametros = getDetalleEliminar();

			if((adasu_parametros != null) && ab_eliminar)
			{
				Collection<DetalleAntSistemaUI> lcdasu_detalleTmp;

				lcdasu_detalleTmp = new ArrayList<DetalleAntSistemaUI>();

				if(CollectionUtils.isValidCollection(lcdasu_detalle))
				{
					String  ls_idDatosAntSistemaDel;
					String  ls_idDetalleAntSistemaDel;
					boolean lb_eliminar;

					ls_idDatosAntSistemaDel       = StringUtils.getStringNotNull(
						    adasu_parametros.getIdDatosAntSistema()
						);
					ls_idDetalleAntSistemaDel     = StringUtils.getStringNotNull(
						    adasu_parametros.getIdDetalleAntSistema()
						);
					lb_eliminar                   = adasu_parametros.isEliminar();

					for(DetalleAntSistemaUI ldasu_iterador : lcdasu_detalle)
					{
						if(ldasu_iterador != null)
						{
							String ls_idDatosAntSistema;
							String ls_idDetalleAntSistema;

							ls_idDatosAntSistema       = StringUtils.getStringNotNull(
								    ldasu_iterador.getIdDatosAntSistema()
								);
							ls_idDetalleAntSistema     = StringUtils.getStringNotNull(
								    ldasu_iterador.getIdDetalleAntSistema()
								);

							if(
							    !ls_idDatosAntSistema.equalsIgnoreCase(ls_idDatosAntSistemaDel)
								    || !ls_idDetalleAntSistema.equalsIgnoreCase(ls_idDetalleAntSistemaDel)
							)
								lcdasu_detalleTmp.add(ldasu_iterador);
						}
					}

					if(!lb_eliminar)
						iasr_antiguoSistemaRemote.eliminarDetalleAntSistema(
						    adasu_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					setDetallesAntSistemaPorSolicitud(lcdasu_detalleTmp);
				}

				addMessage(MessagesKeys.DETALLE_ELIMINADO_SATISFACTORIAMENTE);
			}
			else
				setDetalleEliminar(null);
		}
		catch(B2BException lb2be_b2be)
		{
			setDetallesAntSistemaPorSolicitud(lcdasu_detalle);
			clh_LOGGER.error("eliminarDetalleAntSistema", lb2be_b2be);
			addMessage(lb2be_b2be);
		}
	}

	/**
	 * Find circulo registral by turno etapa.
	 *
	 * @return el valor de collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistralByTurnoEtapa()
	{
		Collection<CirculoRegistral> lcidt_datos;
		String                       ls_idTurno;

		try
		{
			ls_idTurno = getIdTurno();

			if(!StringUtils.isValidString(ls_idTurno))
				throw new B2BException("No existe datos.");

			lcidt_datos = irr_referenceRemote.findCirculoRegistralByTurnoEtapa(
				    ls_idTurno, StringUtils.getString(getIdEtapa()), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcidt_datos))
				setCirculosRegistrales(lcidt_datos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Find circulo registral complementacion.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 * @return el valor de collection
	 */
	public Collection<CirculoRegistral> findCirculoRegistralComplementacion(boolean ab_b)
	{
		Collection<CirculoRegistral> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findAllCirculosRegistralesActivos(
				    ab_b, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			setLcidt_datos_comp(lcidt_datos);
			buscarNombreCirculoComplementacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Find codigo nombre circulo destino.
	 *
	 * @param as_idCirculoDestino correspondiente al valor de as id circulo destino
	 * @return el valor de circulo registral
	 */
	public CirculoRegistral findCodigoNombreCirculoDestino(String as_idCirculoDestino)
	{
		CirculoRegistral lcr_return;

		lcr_return = new CirculoRegistral();

		try
		{
			lcr_return = iasr_antiguoSistemaRemote.findCodigoNombreCirculoDestino(as_idCirculoDestino);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findCodigoNombreCirculoDestino", lb2be_e);
		}

		return lcr_return;
	}

	/**
	 * Metodo encargado de controlar la navegacion de pestañas del componente wizard
	 * 'idWDetallesPredio'.
	 *
	 * @param afe_event Objeto contenedor de navegacion del componente wizard.
	 * @return string con Id de pestaña a la que navega
	 */
	public String flowPrediosAnt(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = null;

		if(afe_event != null)
		{
			try
			{
				String ls_oldStep;
				String ls_newStep;

				ls_oldStep     = afe_event.getOldStep();
				ls_newStep     = afe_event.getNewStep();

				if(StringUtils.isValidString(ls_newStep) && StringUtils.isValidString(ls_oldStep))
				{
					String ls_tab1;
					String ls_tab2;

					ls_tab1     = "idTBusquedaAntSistema";
					ls_tab2     = "idTDecisionesAntSistema";

					if(ls_oldStep.equalsIgnoreCase(ls_tab1) && ls_newStep.equalsIgnoreCase(ls_tab2))
					{
						if(
						    (isPredioSeleccionado() && !isBloquearCrearAsociar())
							    || (isEtapa101() && !isBloquearCrearAsociar())
						)
						{
							DatosAntSistema ldas_datosPredio;

							ldas_datosPredio = getDatoAntSistemaCargado();

							if(
							    (ldas_datosPredio == null)
								    || !StringUtils.isValidString(ldas_datosPredio.getIdDatosAntSistema())
							)
							{
								addMessage(MessagesKeys.SIN_PREDIO_ANT_SISTEMA);
								PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);

								throw new B2BException("");
							}
							else
							{
								if(isConfirmarSiguiente())
								{
									Collection<DatosAntSistema> lcdas_datosMatriculas;

									lcdas_datosMatriculas = getMatriculasSeleccionadas();

									if(CollectionUtils.isValidCollection(lcdas_datosMatriculas))
										setMostrarBotonAsociar(true);

									cargarDatosDecisiones();

									if(!CollectionUtils.isValidCollection(getDetallesAntSistemaSeleccionados()))
									{
										if(isMatriculaDocSeleccionadaParaDetalle())
										{
											DataConsultaPorCriterio ldcpc_dataMatr;

											ldcpc_dataMatr = getMatriculaDocumentoSeleccionada();

											if(ldcpc_dataMatr != null)
											{
												if(lcdas_datosMatriculas == null)
													lcdas_datosMatriculas = new LinkedList<DatosAntSistema>();

												DatosAntSistema ldas_newMatricula;

												ldas_newMatricula = new DatosAntSistema();

												ldas_newMatricula.setIdCirculo(ldcpc_dataMatr.getIdCirculo());
												ldas_newMatricula.setIdMatricula(ldcpc_dataMatr.getIdMatricula());

												lcdas_datosMatriculas.add(ldas_newMatricula);
											}
											else
											{
												setOcultarBotonAtras(false);
												setConfirmarSiguiente(false);

												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DETALLE);
											}
										}
										else
										{
											String lsth_turnoHistoria;
											lsth_turnoHistoria = getIdTurno();

											if(StringUtils.isValidString(lsth_turnoHistoria))
											{
												TurnoHistoria lth_turnoHistoria;
												lth_turnoHistoria = obtenerAnteriorTurnoHistoria(lsth_turnoHistoria);

												if(lth_turnoHistoria != null)
												{
													long ll_etapaTH;

													ll_etapaTH = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

													if(ll_etapaTH != 103)
													{
														setOcultarBotonAtras(false);
														setConfirmarSiguiente(false);

														throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DETALLE);
													}
												}
											}
										}
									}
									else
										setOcultarBotonAtras(true);
								}
								else
								{
									if(!isGrabacionDeMatriculas())
									{
										PrimeFaces.current().executeScript("PF('dConfirmSiguiente').show();");
										throw new B2BException("");
									}
								}
							}
						}

						if(isEtapa105())
							setMostrarPanelMotivos(false);
					}
					else if(ls_oldStep.equalsIgnoreCase(ls_tab2) && ls_newStep.equalsIgnoreCase(ls_tab1))
					{
						limpiarTabuladorDesicion();

						if(isEtapa105())
							setMostrarPanelMotivos(true);

						setOcultarBotonAtras(false);
					}

					actualizarObservacionesTurnoHistoria();

					ls_return = ls_newStep;
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("flowPrediosAnt", lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
				ls_return = afe_event.getOldStep();
			}

			PrimeFaces.current().ajax().update(cs_ID_FORMULARIO + "opBotonesAntSistema");
			PrimeFaces.current().ajax().update(cs_ID_FORMULARIO + "panelTramites105");
		}

		return ls_return;
	}

	/**
	 * Generar archivo solicitud complementacion oficina destino.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarArchivoSolicitudComplementacionOfiDestino()
	    throws B2BException
	{
		byte[] lab_file;

		lab_file = null;

		try
		{
			if(CollectionUtils.isValidCollection(getMatriculasSeleccionadas()) && !isGrabacionDeMatriculas())
				throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA_ANT_SISTEMA);

			if(StringUtils.isValidString(getIdTurnoHistoria()))
			{
				{
					lab_file = iasr_antiguoSistemaRemote.generarPdfSolicitudComplementacionOfiDestino(
						    getIdTurnoHistoria(), true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lab_file != null)
					{
						InputStream lis_stream;

						lis_stream = new ByteArrayInputStream(lab_file);

						setImagenFirma(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.PDF,
						        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
						    )
						);

						setPanelSiguienteFirmaLibro(true);

						addMessage(MessagesKeys.PROCESO_COMPLETADO);

						PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
						PrimeFaces.current().executeScript("PF('wvPoll').start();");
						setMostrarBotonGenerarSolicitudComplementacion(false);
						setMostrarBotonConsultaSGDComplementacion(true);
						PrimeFaces.current().ajax().update("idFormAntSistema");
						PrimeFaces.current().ajax().update("consultaSGDComplementacion");
						PrimeFaces.current().ajax().update("idCCrearDocumentoComplementacion");

						if(isDefinitivo102())
							setProcesoTerminado102(true);
					}
					else
					{
						setImagenFirma(null);
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoSolicitudFirma", lb2be_e);
			addMessage(lb2be_e);
		}

		finally
		{
			actualizarComponente(cs_ID_FORMULARIO);
		}
	}

	/**
	 * Generar archivo solicitud firma.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarArchivoSolicitudFirma()
	    throws B2BException
	{
		byte[] lab_file;

		lab_file = null;

		try
		{
			if(
			    isMostrarBotonAsociar() && CollectionUtils.isValidCollection(getMatriculasSeleccionadas())
				    && !isGrabacionDeMatriculas()
			)
				throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA_ANT_SISTEMA);

			DatosAntSistema     ldas_das;
			Map<String, Object> lmso_mso;

			ldas_das     = getDatoAntSistemaCargado();
			lmso_mso     = getDatosDelTurno();

			if((ldas_das != null) && CollectionUtils.isValidCollection(lmso_mso))
			{
				String ls_justificacionFirma;

				ls_justificacionFirma = getJustificacionFirma();

				if(StringUtils.isValidString(ls_justificacionFirma))
				{
					ldas_das.setObservacionesFirmaLibro(ls_justificacionFirma);

					lab_file = iasr_antiguoSistemaRemote.generarPdfSolicitudFirma(
						    ldas_das, lmso_mso, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lab_file != null)
					{
						InputStream lis_stream;

						lis_stream = new ByteArrayInputStream(lab_file);

						setImagenFirma(
						    new DefaultStreamedContent(
						        lis_stream, TipoContenidoCommon.PDF,
						        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
						    )
						);

						setPanelSiguienteFirmaLibro(true);

						addMessage(MessagesKeys.PROCESO_COMPLETADO);

						PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
					}
					else
					{
						setImagenFirma(null);
						throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
					}
				}
				else
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoSolicitudFirma", lb2be_e);
			addMessage(lb2be_e);
		}

		finally
		{
			actualizarComponente(cs_ID_FORMULARIO);
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void generarDocumentoMatriculaOficio()
	{
		try
		{
			OficiosTexto aot_oficioTextoData;
			aot_oficioTextoData = new OficiosTexto();
			aot_oficioTextoData.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			aot_oficioTextoData.setIdSolicitud(getIdSolicitud());
			aot_oficioTextoData.setConsideracion(convertirTextoParaRtf(getConsideraciones()));
			iasr_antiguoSistemaRemote.generarDocumentoMatriculaOficioAS(
			    aot_oficioTextoData, getUserId(), getRemoteIpAddress(), getRemoteIpAddress(),
			    NumericUtils.getLong(getMotivoTramite(), 0L)
			);
			PrimeFaces.current().executeScript("PF('wvPoll').start();");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor de motivos.
	 *
	 * @return el valor de motivos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> generarMotivos105()
	    throws B2BException
	{
		return generarMotivos105(true, false);
	}

	/**
	 * Retorna el valor de motivos.
	 *
	 * @return el valor de motivos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> generarMotivos105(boolean ab_inicio, boolean ab_asociar)
	    throws B2BException
	{
		Collection<MotivoTramite> lc_motivos;
		Collection<MotivoTramite> lc_motivosReturn;

		lc_motivos           = irr_referenceRemote.findMotivosByEtapa105(
			    StringUtils.getString(getIdEtapa()), getIdTurnoHistoria(), false, true
			);
		lc_motivosReturn     = new LinkedList<MotivoTramite>();

		if(ab_inicio)
		{
			if(CollectionUtils.isValidCollection(lc_motivos))
			{
				for(MotivoTramite lmt_iterador : lc_motivos)
				{
					if(lmt_iterador != null)
					{
						if((lmt_iterador.getIdMotivo() == 10) || (lmt_iterador.getIdMotivo() == 20))
							lc_motivosReturn.add(lmt_iterador);
					}
				}
			}
		}
		else
		{
			Collection<MotivoTramite> lc_motivos2;
			lc_motivos2 = getMotivosTemporales();

			if(CollectionUtils.isValidCollection(lc_motivos2))
				for(MotivoTramite lmt_iterador : lc_motivos2)
				{
					if(lmt_iterador != null)
					{
						if(ab_asociar && (lmt_iterador.getIdMotivo() == 40))
							lc_motivosReturn.add(lmt_iterador);
						else if(!ab_asociar && (lmt_iterador.getIdMotivo() == 30))
							lc_motivosReturn.add(lmt_iterador);
					}
				}
		}

		setMotivosTemporales(lc_motivos);
		setMotivos(lc_motivosReturn);

		return lc_motivosReturn;
	}

	/**
	 * Guardar completitud complementacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarCompletitudComplementacion()
	    throws B2BException
	{
		guardarCompletitudComplementacion(false);
	}

	/**
	 * Guardar completitud complementacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarCompletitudComplementacion(boolean ab_definitivo102)
	    throws B2BException
	{
		try
		{
			CompletitudComplementacion cc_guardar_comp;

			if(!isEtapa102())
			{
				DatosBasicos dt_basicos;
				String       icr_circuloDestino;

				cc_guardar_comp = new CompletitudComplementacion();

				cc_guardar_comp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				dt_basicos             = getDatosBasicos();
				icr_circuloDestino     = null;

				if(dt_basicos != null)
				{
					UbicacionZonaRegistral uzr_basicos;

					uzr_basicos = dt_basicos.getUbicacion();

					if(uzr_basicos != null)
					{
						CirculoRegistral icr_basicos;

						icr_basicos = uzr_basicos.getCirculoRegistral();

						if(icr_basicos != null)
							icr_circuloDestino = icr_basicos.getIdCirculo();
					}
				}

				cc_guardar_comp.setIdCirculoRegistralDestino(getIdCirculo());
				cc_guardar_comp.setIdTurno(getIdTurno());

				cc_guardar_comp.setJustificacion(getJustificacionComplementacion());

				iasr_antiguoSistemaRemote.salvarCompletitudComplementacion(
				    cc_guardar_comp, icr_circuloDestino, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setMostrarBotonGenerarSolicitudComplementacion(true);
				setMostrarBotonGuardarComplementacion(false);
				setMostrarBotonTerminarSolicitud(false);
				PrimeFaces.current().ajax().update("idPInformacionComplementacion");
				PrimeFaces.current().ajax().update("idCCrearDocumentoComplementacion");
				PrimeFaces.current().ajax().update("idFormAntSistema");
				PrimeFaces.current().ajax().update("idCBTerminarProcesoAntSistema");
			}
			else
			{
				cc_guardar_comp = getCompletitudComplementacionCirculoDestino();

				if(cc_guardar_comp != null)
				{
					String ls_validacion;
					ls_validacion = null;

					if(isInfo102())
					{
						cc_guardar_comp.setRespuestaBusqueda(EstadoCommon.SI_TXT);

						ls_validacion = getComplementacionInformacionCirculoDestino();

						if(!StringUtils.isValidString(ls_validacion))
							throw new B2BException(ErrorKeys.ERROR_SIN_COMPLEMENTACION);

						cc_guardar_comp.setInformacionComplementacion(ls_validacion);
					}
					else
					{
						ls_validacion = getComplementacionInformacionCirculoDestino();

						cc_guardar_comp.setRespuestaBusqueda(EstadoCommon.NO_TXT);

						if(!StringUtils.isValidString(ls_validacion))
							throw new B2BException(ErrorKeys.ERROR_SIN_INFORMACION_NO_HALLAZGO);

						cc_guardar_comp.setObservacionesNoHallazgo(ls_validacion);
					}

					iasr_antiguoSistemaRemote.actualizarCompletitudComplementacion(
					    cc_guardar_comp, ab_definitivo102, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					if(isDefinitivo102())
						setMostrarBotonGenerarSolicitudComplementacion(true);
					else if(!isDefinitivo102())
						setDefinitivo102(!ab_definitivo102);

					setOcultarBotonSiguiente(true);

					PrimeFaces.current().ajax().update("idFormAntSistema");
					PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Guarda en base de datos la información del predio agregado por pantalla.
	 */
	public void guardarDatosAntSistema()
	{
		try
		{
			DatosAntSistema             ldas_datosPredio;
			Collection<DatosAntSistema> lcdas_datosAgregados;

			ldas_datosPredio         = getDatosPredioParaCrear();
			lcdas_datosAgregados     = new LinkedList<DatosAntSistema>();

			if(ldas_datosPredio == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			{
				FacesContext lfc_context;
				String       ls_idTipoPredio;
				String       ls_idMunicipio;
				boolean      lb_focus;

				lfc_context         = FacesContext.getCurrentInstance();
				lb_focus            = true;
				ls_idTipoPredio     = ldas_datosPredio.getIdTipoPredio();
				ls_idMunicipio      = ldas_datosPredio.getIdMunicipio();
				ldas_datosPredio.setRevisadoAntSistema(EstadoCommon.N);

				lb_focus = validateStyles(
					    cs_ID_FORMULARIO + "idSOMTipoPredioAntSistema", lfc_context, ls_idTipoPredio, lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoPredio))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);

				lb_focus = validateStyles(
					    cs_ID_FORMULARIO + "idSOMMunAntSistema", lfc_context, ls_idMunicipio, lb_focus
					);

				if(!StringUtils.isValidString(ls_idMunicipio))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
			}

			ldas_datosPredio = irr_registroRemote.guardarDatosAntSistema(
				    ldas_datosPredio, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true
				);

			if(ldas_datosPredio == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			lcdas_datosAgregados.add(ldas_datosPredio);

			setDatoAntSistemaCargado(ldas_datosPredio);

			Collection<DatosAntSistema> lcdas_datos;
			lcdas_datos = getDatosAntSistema();

			if(!CollectionUtils.isValidCollection(lcdas_datos))
				setDatosAntSistema(lcdas_datosAgregados);
			else
			{
				lcdas_datos.add(ldas_datosPredio);
				setDatosAntSistema(lcdas_datos);
			}

			obtenerDatosAntSistema();

			setMostrarCreacionPredio(false);

			verificarRevisionPredios();

			addMessage(MessagesKeys.DATOS_PREDIO_AGREGADO_EXITOSAMENTE);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarDatosAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método que limpia los datos digitados en Búsqueda por antiguo sistema manual.
	 */
	public void limpiarDetalleAntSistema()
	{
		setHabilitarCamposPredioAntiguoSistema(false);
		setMostrarBotonCrear(false);
		setDetalleAntSistemaConsulta(null);
		setConsultaRealizada(false);
		setPanelSiguienteFirmaLibro(false);
		setMensajePredioInconsistente(null);
		setMostrarPanelJustificacionFirma(false);
		setMostrarBotonJustificar(false);
		setImagenFirma(null);
		setSolicitaComplementacionOtroCirculo(false);
		setMostrarPanelOtroCirculo(false);
		setMostrarBotonGenerarSolicitudComplementacion(false);
		setInfo102(false);
		setMostrarBotonTerminarConsultaPredios(false);
		setMostrarComplementacionCirculoDestino(false);
		setCompletitudComplementacionCirculoDestino(null);
		setComplementacionInformacionCirculoDestino(null);
		setDefinitivo102(false);
		setProcesoTerminado102(false);
	}

	/**
	 * Método que limpia el tabulador de la desición cuando se regresan al tabulador
	 * anterior.
	 */
	public void limpiarTabuladorDesicion()
	{
		setMatriculasSeleccionadas(null);
		setDetallesAntSistemaSeleccionados(null);
		setMostrarBotonAsociar(true);
		setMostrarSeleccioneMatriculas(true);
		setConfirmarSiguiente(false);
		setAccionAsociar(false);
		setPanelSiguienteFirmaLibro(false);
		setSolicitudesMatriculas(null);
		setRevisadoAntSistema(false);
		setMensajePredioInconsistente(null);
		setMostrarPanelJustificacionFirma(false);
		setMostrarBotonJustificar(false);
		setImagenFirma(null);
		setSolicitaComplementacionOtroCirculo(false);
		setMostrarPanelOtroCirculo(false);
		setMostrarBotonGenerarSolicitudComplementacion(false);
	}

	/**
	 * Metodo encargado de cambiar flag para mostrar el panel de justificación firma.
	 */
	public void mostrarJustificacionFirma()
	    throws B2BException
	{
		setMostrarPanelJustificacionFirma(true);
		setMostrarBotonJustificar(true);

		DatosAntSistema ldas_dato;

		ldas_dato = getDatoAntSistemaCargado();

		if(ldas_dato != null)
		{
			ldas_dato.setJustificacionFirma(true);

			try
			{
				iasr_antiguoSistemaRemote.firmarLibroAntSistema(
				    ldas_dato.getIdDatosAntSistema(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("mostrarJustificacionFirma", lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Metodo encargado de cambiar flag para mostrar el panel de la etapa 100 y 101.
	 */
	public void mostrarSiguienteJustificacion()
	{
		try
		{
			boolean lb_siguiente;

			lb_siguiente = isPanelSiguienteFirmaLibro();

			if(lb_siguiente)
			{
				accionRevisarAntSistema();
				setPanelSiguienteFirmaLibro(false);
				setMostrarBotonJustificar(false);
			}

			else
				throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_FIRMA_LIBRO);

			PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("mostrarSiguienteJustificacion", lb2be_e);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update("idFormAntSistema");
	}

	/**
	 * Retorno a la bandeja principal.
	 *
	 * @return String con la pagina a redireccionar
	 */
	public String returnBandeja()
	{
		String ls_return;
		ls_return = NavegacionCommon.ANTIGUO_SISTEMA;

		try
		{
			FacesContext       lfc_context;
			BeanAntiguoSistema lbde_bean;
			lfc_context     = FacesUtils.getFacesContext();
			lbde_bean       = (BeanAntiguoSistema)lfc_context.getApplication()
					                                             .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_ANTIGUO_SISTEMA, BeanAntiguoSistema.class
					);

			if(lbde_bean != null)
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}

			clean();
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("returnBandeja", e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de cambiar flag para mostrar el componente Wizard.
	 */
	public void salvarAsociarMatriculas()
	{
		setMostrarWizardPredio(false);
	}

	public void salvarPredioDefinitivo()
	{
		salvarPredioDefinitivo(false);
	}

	/**
	 * Método encargado de salvar el predio de antiguo sistemna y actualizar el estado a definitivo.
	 */
	public void salvarPredioDefinitivo(boolean ab_otroCirculo)
	{
		try
		{
			boolean lb_asociar;
			boolean lb_crear;
			boolean lb_informe;

			lb_asociar     = isAsociarMatriculaAntSistema();
			lb_crear       = isCrearMatriculaAntSistema();
			lb_informe     = isInformeDeBusquedaAntSistema();

			TurnoHistoria lth_turnoHistoriaTemp;
			boolean       lb_procesoTerminado;

			lb_procesoTerminado = false;

			String ls_observacionesTemp;

			lth_turnoHistoriaTemp = new TurnoHistoria();
			ls_observacionesTemp = null;
			lth_turnoHistoriaTemp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
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
				PrimeFaces.current().ajax().update("fDialogSuspension");
			}

			{
				generarDatosActualizar2();

				if(!lb_asociar && !lb_crear && !lb_informe)
					throw new B2BException(ErrorKeys.ERROR_POCESO_ANT_SISTEMA_DEFINITIVO);

				if(lb_informe && !isProcesoTerminadoInformeBusqueda())
					throw new B2BException(ErrorKeys.ERROR_INFORME_BUSQUEDA);

				if(lb_crear && !lb_asociar)
				{
					Collection<AccPredioRegistro> lcapr_predios;

					lcapr_predios = getDatosTurnoMatricula();

					if(CollectionUtils.isValidCollection(lcapr_predios))
					{
						boolean                     lb_estadoAnotacion;
						Iterator<AccPredioRegistro> liapr_iterator;
						String                      ls_matriculaPredio;

						lb_estadoAnotacion     = true;
						liapr_iterator         = lcapr_predios.iterator();
						ls_matriculaPredio     = null;

						while(liapr_iterator.hasNext() && lb_estadoAnotacion)
						{
							AccPredioRegistro lapr_predio;

							lapr_predio = liapr_iterator.next();

							if(lapr_predio != null)
							{
								String ls_estadoGrabacion;

								ls_estadoGrabacion = lapr_predio.getEstadoGrabacion();

								if(
								    StringUtils.isValidString(ls_estadoGrabacion)
									    && !ls_estadoGrabacion.equalsIgnoreCase(EstadoCommon.ESTADO_ANOTACIONES)
								)
								{
									lb_estadoAnotacion     = false;
									ls_matriculaPredio     = lapr_predio.getIdCirculo() + "-"
										+ lapr_predio.getIdMatricula();
								}
							}
						}

						if(!lb_estadoAnotacion)
						{
							String[] lsa_arg;

							lsa_arg        = new String[1];
							lsa_arg[0]     = ls_matriculaPredio;

							throw new B2BException(
							    ErrorKeys.ERROR_POCESO_ANT_SISTEMA_CREAR_MATRICULA_ESTADO_ANOTACION, lsa_arg
							);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_POCESO_ANT_SISTEMA_CREAR_MATRICULA);
				}
				else if(lb_asociar && !lb_crear)
				{
					if(!isProcesoTerminadoAsociarMatricula())
						throw new B2BException(ErrorKeys.ERROR_ASOCIAR_MATRICULA_SALVAR);
				}

				{
					DatosAntSistema ldas_dato;

					ldas_dato = getDatoAntSistemaCargado();

					if(ldas_dato != null)
					{
						ldas_dato.setSolicitarComplementacion(ab_otroCirculo ? EstadoCommon.S : EstadoCommon.N);
						setSolicitaComplementacionOtroCirculo(ab_otroCirculo);
					}

					if(isEtapa101() || isEtapa105())
						setBloquearCrearAsociar(false);

					if(!isBloquearCrearAsociar())
						iasr_antiguoSistemaRemote.revisadoAntSistemaDefinitivo(
						    ldas_dato, getIdTurnoHistoria(), getIdTurno(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);
					else
						getDatoAntSistemaCargado().setRevisadoAntSistema(EstadoCommon.D);

					generarProcesos();
					obtenerDatosAntSistema();

					setDatoAntSistemaCargado(null);
					setMostrarWizardPredio(false);
					setDetallesAntSistema(null);
					setPredioSeleccionado(false);
				}

				if(isCrearMatriculaAntSistema() && isEtapa105())
				{
					setMotivoTramite("30");
					generarMotivos105(false, false);
				}
				else if(isAsociarMatriculaAntSistema() && isEtapa105())
				{
					setMotivoTramite("40");
					generarMotivos105(false, true);
				}

				verificarRevisionPredios();
			}

			setMostrarPanelOtroCirculo(ab_otroCirculo);
			setMostrarBotonGuardarComplementacion(true);

			if(!isEtapa102())
				setMostrarBotonTerminarSolicitud(false);

			PrimeFaces.current().ajax().update("idOPAcciones");
			PrimeFaces.current().ajax().update("idCGuardarComplementacion");
			PrimeFaces.current().ajax().update("idPInformacionComplementacion");
			PrimeFaces.current().ajax().update("idCBTerminarProcesoAntSistema");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarPredioDefinitivo", lb2be_e);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().executeScript("PF('dlg').show();");
	}

	/**
	 * Método encargado de terminar la etapa de antiguo sistema.
	 *
	 * @return Variable de tipo String que contiene la página a la cual se va a redireccionar una vez se termine el proceso.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarProcesoAntSistema()
	    throws B2BException
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoriaTemp;
		boolean       lb_procesoTerminado;

		lb_procesoTerminado     = false;
		ls_return               = null;
		setMostrarComplementacionCirculoDestino(false);
		setNombreCirculoComplementacion(null);
		setJustificacionComplementacion(null);

		String ls_observacionesTemp;

		lth_turnoHistoriaTemp     = new TurnoHistoria();
		ls_observacionesTemp      = null;
		lth_turnoHistoriaTemp.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
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
			PrimeFaces.current().ajax().update("fDialogSuspension");
		}
		else
		{
			try
			{
				boolean                     lb_salvado;
				Collection<DatosAntSistema> lcdas_predios;

				lb_salvado        = false;
				lcdas_predios     = getDatosAntSistema();

				if(CollectionUtils.isValidCollection(lcdas_predios))
				{
					boolean                   lb_valido;
					Iterator<DatosAntSistema> lidas_iterator;
					String                    ls_nombrePredio;

					lb_valido           = true;
					lidas_iterator      = lcdas_predios.iterator();
					ls_nombrePredio     = null;

					while(lidas_iterator.hasNext() && lb_valido)
					{
						DatosAntSistema ldas_iterador;

						ldas_iterador = lidas_iterator.next();

						if(ldas_iterador != null)
						{
							String ls_revisado;

							ls_revisado = ldas_iterador.getRevisadoAntSistema();

							if(StringUtils.isValidString(ls_revisado) && !ls_revisado.equalsIgnoreCase(EstadoCommon.D))
							{
								lb_valido           = false;
								ls_nombrePredio     = ldas_iterador.getNombrePredio();
							}
						}
					}

					if(!lb_valido)
					{
						String[] lsa_arg;

						lsa_arg        = new String[1];
						lsa_arg[0]     = ls_nombrePredio;

						throw new B2BException(ErrorKeys.PREDIO_DEFINITIVO_ANT_SISTEMA_NO, lsa_arg);
					}
				}
				else
				{
					if(!isBloquearCrearAsociar() && !isEtapa101())
						throw new B2BException(ErrorKeys.ERROR_SALVAR_PREDIOS_ANT_SISTEMA);
				}

				if(!isEtapa105() && !isEtapa102())
				{
					lb_salvado = iasr_antiguoSistemaRemote.salvarProcesoAntSistema(
						    getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
						    getObservaciones(), isSolicitaComplementacionOtroCirculo()
						);

					if(lb_salvado)
					{
						FacesContext lfc_context;
						lfc_context = FacesUtils.getFacesContext();

						BeanDetalleAntiguoSistema lbrc_bean;
						lbrc_bean     = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
								                                                  .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA,
								    BeanDetalleAntiguoSistema.class
								);

						ls_return = NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;

						if(lbrc_bean != null)
						{
							lbrc_bean.setBandejaentrada(true);
							lbrc_bean.setData(null);
							lbrc_bean.setNir(null);
							lbrc_bean.setIdTurnoHistoria(null);
							lbrc_bean.generarData();
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ANT_SISTEMA_SALVAR);
				}
				else if(isEtapa105() && !isEtapa102())
				{
					iasr_antiguoSistemaRemote.terminarTurnoHistoria(
					    NumericUtils.getLong(getIdEtapa()), getIdTurnoHistoria(),
					    NumericUtils.getLong(getMotivoTramite()), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					FacesContext lfc_context;
					lfc_context = FacesUtils.getFacesContext();

					BeanDetalleAntiguoSistema lbrc_bean;
					lbrc_bean     = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
							                                                  .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA,
							    BeanDetalleAntiguoSistema.class
							);

					ls_return = NavegacionCommon.ANALISTA_DE_CREACION_MATRICULAS_OFICIO;

					if(lbrc_bean != null)
					{
						lbrc_bean.setBandejaentrada(true);
						lbrc_bean.setData(null);
						lbrc_bean.setNir(null);
						lbrc_bean.setIdTurnoHistoria(null);
						lbrc_bean.generarData();
					}
				}
				else
				{
					if(isInfo102())
						setMotivoTramite("10");
					else
						setMotivoTramite("20");

					iasr_antiguoSistemaRemote.terminarTurnoHistoria(
					    NumericUtils.getLong(getIdEtapa()), getIdTurnoHistoria(),
					    NumericUtils.getLong(getMotivoTramite()), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					FacesContext lfc_context;
					lfc_context = FacesUtils.getFacesContext();

					BeanDetalleAntiguoSistema lbrc_bean;
					lbrc_bean     = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
							                                                  .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA,
							    BeanDetalleAntiguoSistema.class
							);

					ls_return = NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;

					if(lbrc_bean != null)
					{
						lbrc_bean.setBandejaentrada(true);
						lbrc_bean.setData(null);
						lbrc_bean.setNir(null);
						lbrc_bean.setIdTurnoHistoria(null);
						lbrc_bean.generarData();
					}
				}

				verificarRevisionPredios();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("salvarProcesoAntSistema", lb2be_e);
				addMessage(lb2be_e);
			}
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de marcar los detalleAntSistema seleccionados por pantalla para su posterior procesamiento.
	 *
	 * @param adas_data correspondiente al valor del tipo de objeto DetalleAntSistema
	 */
	public void seleccionarDatosAntSistema(DetalleAntSistema adas_data)
	{
		DetalleAntSistema ldas_detalleAnotacion;

		ldas_detalleAnotacion = null;

		if((adas_data != null) && adas_data.isSeleccionado())
		{
			Collection<DetalleAntSistema> lcdas_detalles;

			lcdas_detalles            = getDetallesAntSistema();
			ldas_detalleAnotacion     = adas_data;

			if(CollectionUtils.isValidCollection(lcdas_detalles))
			{
				Iterator<DetalleAntSistema> lidas_iterator;

				lidas_iterator = lcdas_detalles.iterator();

				while(lidas_iterator.hasNext())
				{
					DetalleAntSistema ldas_detalle;

					ldas_detalle = lidas_iterator.next();

					if((ldas_detalle != null) && (ldas_detalle != adas_data))
						ldas_detalle.setSeleccionado(false);
				}
			}
		}

		setDetalleAntSistemaAnotacion(ldas_detalleAnotacion);
	}

	/**
	 * Verifica que se haya seleccionado un predio y habilita el wizard de predios.
	 */
	public void seleccionarPredio()
	{
		try
		{
			DatosAntSistema ldas_dato;
			String          ls_idDatosAntSistema;
			String          ls_idTurno;

			ldas_dato                = getDatoAntSistemaCargado();
			ls_idDatosAntSistema     = getIdDatosAntSistemaActual();
			ls_idTurno               = getIdTurno();

			setArchivosCargados(null);

			if(ldas_dato != null)
			{
				String ls_revisado;

				ls_revisado = ldas_dato.getRevisadoAntSistema();

				if(StringUtils.isValidString(ls_revisado) && !isEtapa102())
				{
					if(ls_revisado.equalsIgnoreCase(EstadoCommon.D))
					{
						String[] lsa_arg;

						lsa_arg        = new String[1];
						lsa_arg[0]     = ldas_dato.getNombrePredio();

						throw new B2BException(ErrorKeys.PREDIO_DEFINITIVO_ANT_SISTEMA, lsa_arg);
					}

					setRevisadoAntSistema(!ls_revisado.equals(EstadoCommon.N));
				}
				else
					setRevisadoAntSistema(false);

				Collection<DetalleAntSistemaUI> lcdasu_dasu;
				lcdasu_dasu = iasr_antiguoSistemaRemote.buscarTodosDetallesPorSolicitud(
					    ldas_dato, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				setDetallesAntSistemaPorSolicitud(lcdasu_dasu);
				setDetalleAntSistemaConsulta(null);

				if(CollectionUtils.isValidCollection(lcdasu_dasu))
				{
					for(DetalleAntSistemaUI idasUi : lcdasu_dasu)
					{
						if(idasUi != null)
							setDetalleAntSistemaConsulta(idasUi);

						;
					}
				}

				setIdCirculo(ldas_dato.getIdCirculo());
			}

			if(!isPredioSeleccionado())
			{
				if(CollectionUtils.isValidCollection(getDatosAntSistema()))
					mostrarErrorSeleccionPredios();
				else
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_MINIMO_UN_PREDIO);
			}

			setMostrarWizardPredio(true);
			setTipoConsulta(null);
			setConsultaDatosDocumento(null);

			Map<String, Boolean> lmsb_data;

			lmsb_data = iasr_antiguoSistemaRemote.findProcesoAntiguoSistema(
				    ls_idTurno, ls_idDatosAntSistema, getIdTurnoHistoria()
				);

			if(CollectionUtils.isValidCollection(lmsb_data))
			{
				setProcesoTerminadoInformeBusqueda(lmsb_data.containsKey(IdentificadoresCommon.INFORME_BUSQUEDA));
				setInformeDeBusquedaAntSistema(lmsb_data.containsKey(IdentificadoresCommon.INFORME_BUSQUEDA));
				setProcesoTerminadoAsociarMatricula(lmsb_data.containsKey(IdentificadoresCommon.ASOCIAR_MATRICULA));
				setAsociarMatriculaAntSistema(lmsb_data.containsKey(IdentificadoresCommon.ASOCIAR_MATRICULA));
				setCrearMatriculaAntSistema(lmsb_data.containsKey(IdentificadoresCommon.CREAR_MATRICULA));

				if(isProcesoTerminadoAsociarMatricula())
				{
					Map<String, Collection<SolicitudMatriculaActo>> lcsma_solicitudMatricula;
					Collection<PredioActoIU>                        lcpaiu_actosAsociados;

					lcsma_solicitudMatricula     = iasr_antiguoSistemaRemote.findMatriculasSolicitudAntSistema(
						    ls_idDatosAntSistema, ls_idTurno
						);
					lcpaiu_actosAsociados        = null;

					if(CollectionUtils.isValidCollection(lcsma_solicitudMatricula))
					{
						lcpaiu_actosAsociados = new ArrayList<PredioActoIU>();

						for(Map.Entry<String, Collection<SolicitudMatriculaActo>> lm_iterator : lcsma_solicitudMatricula
							    .entrySet())
						{
							if(lm_iterator != null)
							{
								String ls_matricula;

								ls_matricula = lm_iterator.getKey();

								if(StringUtils.isValidString(ls_matricula))
								{
									Collection<SolicitudMatriculaActo> lcsma_actos;
									int                                li_inicio;
									PredioActoIU                       lpaui_nuevo;
									String                             ls_idMatricula;

									lcsma_actos        = lm_iterator.getValue();
									li_inicio          = ls_matricula.lastIndexOf("-");
									lpaui_nuevo        = new PredioActoIU(getColumns());
									ls_idMatricula     = ls_matricula.substring(li_inicio + 1, ls_matricula.length());

									if(CollectionUtils.isValidCollection(lcsma_actos))
									{
										Map<String, Boolean> lmsb_actosSolicitud;

										lmsb_actosSolicitud = lpaui_nuevo.getActos();

										if(CollectionUtils.isValidCollection(lmsb_actosSolicitud))
										{
											for(SolicitudMatriculaActo lsma_iterador : lcsma_actos)
											{
												if(lsma_iterador != null)
												{
													String ls_idActo;

													ls_idActo = lsma_iterador.getIdActo();

													if(lmsb_actosSolicitud.containsKey(ls_idActo))
														lmsb_actosSolicitud.replace(ls_idActo, Boolean.TRUE);
												}
											}
										}
									}

									lpaui_nuevo.setMatricula(ls_idMatricula);

									{
										DireccionPredio ldp_direccionPredio;

										ldp_direccionPredio = new DireccionPredio();

										ldp_direccionPredio.setIdCirculo(getIdCirculo());
										ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ls_idMatricula));

										ldp_direccionPredio = irr_registroRemote.findDireccionPredioById(
											    ldp_direccionPredio, getUserId()
											);

										if(ldp_direccionPredio != null)
											lpaui_nuevo.setDireccion(ldp_direccionPredio.getDireccion());
									}

									lcpaiu_actosAsociados.add(lpaui_nuevo);
								}
							}
						}
					}

					setActosAsociadosGeneral(lcpaiu_actosAsociados);
				}

				if(isProcesoTerminadoInformeBusqueda())
				{
					Collection<DocumentosSalida> lci_documentos;
					Collection<StreamedContent>  lcsc_data;
					DocumentosSalida             lds_documentoSalida;

					lds_documentoSalida     = new DocumentosSalida();
					lcsc_data               = new ArrayList<StreamedContent>();

					lds_documentoSalida.setIdDatosAntSistema(ls_idDatosAntSistema);
					lds_documentoSalida.setIdTurno(ls_idTurno);

					lci_documentos = iasr_antiguoSistemaRemote.findInformesBusqueda(lds_documentoSalida);

					if(CollectionUtils.isValidCollection(lci_documentos))
					{
						byte[]                     lba_bytes;
						Iterator<DocumentosSalida> lii_iterator;
						int                        li_count;
						final String               ls_nombreArchivo = "Archivo_";

						lba_bytes        = new byte[0];
						lii_iterator     = lci_documentos.iterator();
						li_count         = 1;

						while(lii_iterator.hasNext())
						{
							DocumentosSalida li_iterador;

							li_iterador = lii_iterator.next();

							if(li_iterador != null)
							{
								StreamedContent lsc_documento;
								InputStream     lis_stream;

								lis_stream        = new ByteArrayInputStream(lba_bytes);
								lsc_documento     = new DefaultStreamedContent(
									    lis_stream, null, ls_nombreArchivo + li_count
									);

								lcsc_data.add(lsc_documento);

								li_count++;
							}
						}
					}

					setArchivosCargados(lcsc_data);
				}

				setMostrarPanelMotivos(false);
				PrimeFaces.current().ajax().update("idFormAntSistema:panelTramites105");
			}
			else
			{
				setProcesoTerminadoInformeBusqueda(false);
				setInformeDeBusquedaAntSistema(false);
				setProcesoTerminadoAsociarMatricula(false);
				setAsociarMatriculaAntSistema(false);
				setCrearMatriculaAntSistema(false);
				setMostrarPanelMotivos(false);
				PrimeFaces.current().ajax().update("idFormAntSistema:panelTramites105");
			}

			setDatosTurnoMatricula(null);

			cargarCrearMatricula(true);

			if(isCrearMatriculaAntSistema())
			{
				setMotivoTramite("30");
				generarMotivos105(false, false);
			}
			else if(isAsociarMatriculaAntSistema())
			{
				setMotivoTramite("40");
				generarMotivos105(false, true);
			}

			setOcultarBotonSiguiente(true);

			{
				Wizard lw_wizard;

				lw_wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
						                            .findComponent("idFormAntSistema:idWDetallesPredio");

				if(lw_wizard != null)
				{
					if(!isRevisadoAntSistema() || isEtapa102())
					{
						setOcultarBotonAtras(false);
						lw_wizard.setStep("idTBusquedaAntSistema");

						if(isEtapa102())
							setMostrarBotonTerminarConsultaPredios(true);
					}
					else
						lw_wizard.setStep("idTDecisionesAntSistema");
				}
			}

			setMostrarCreacionPredio(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("seleccionarPredio", lb2be_e);
			PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		}
	}

	/**
	 * Método para crear o asociar matricula en 105
	 * @throws B2BException
	 */
	public void validacionCirculoDestino()
	    throws B2BException
	{
		Collection<DetalleAntSistemaUI> lcdasui_detalles;

		lcdasui_detalles = getDetallesAntSistemaPorSolicitud();

		if(CollectionUtils.isValidCollection(lcdasui_detalles))
		{
			for(DetalleAntSistemaUI idas_detalle : lcdasui_detalles)
			{
				if(idas_detalle != null)
				{
					if(idas_detalle.isDetalleMatriculaSeleccionado())
					{
						setInfo102(true);
						PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
						obtenerDatosAntSistema();
						setDatoAntSistemaCargado(null);
						setMostrarWizardPredio(false);
						setDetallesAntSistema(null);
						setPredioSeleccionado(false);
						setDetalleAntSistemaConsulta(null);
						setDetallesAntSistemaConsulta(null);
						setTipoConsulta(null);
						setOcultarBotonSiguiente(false);
						verificarRevisionPredios();
						setMostrarComplementacionCirculoDestino(true);
						buscarCompletitudComplementacionByTurno();
					}
					else
					{
						setInfo102(false);
						PrimeFaces.current().executeScript("PF('dlgConfirmarBusquedaPredios').show();");
						PrimeFaces.current().ajax().update("fDlgConfirmarBusquedaPredios");
					}
				}
			}
		}
		else
		{
			setInfo102(false);
			PrimeFaces.current().executeScript("PF('dlgConfirmarBusquedaPredios').show();");
			PrimeFaces.current().ajax().update("fDlgConfirmarBusquedaPredios");
		}

		setMostrarBotonTerminarConsultaPredios(false);
		setOcultarBotonSiguiente(true);
		PrimeFaces.current().ajax().update("idFormAntSistema");
	}

	/**
	 * Validacion circulo destino sin informacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validacionCirculoDestinoSinInformacion()
	    throws B2BException
	{
		setInfo102(false);
		obtenerDatosAntSistema();
		setDatoAntSistemaCargado(null);
		setMostrarWizardPredio(false);
		setDetallesAntSistema(null);
		setPredioSeleccionado(false);
		setDetalleAntSistemaConsulta(null);
		setDetallesAntSistemaConsulta(null);
		setTipoConsulta(null);
		setOcultarBotonSiguiente(false);
		verificarRevisionPredios();
		setMostrarComplementacionCirculoDestino(true);
		setMostrarBotonTerminarConsultaPredios(false);
		PrimeFaces.current().ajax().update("idFormAntSistema");
	}

	/**
	 * Método para crear o asociar matricula en 105
	 *
	 * @throws B2BException
	 */
	public void validacionNAS()
	    throws B2BException
	{
		cargarDatosDecisiones();
		setOcultarBotonAtras(true);
		accionRevisarAntSistema();
		setProcesoTerminadoAsociarMatricula(true);

		if(isAccionAsociar())
		{
			setAsociarMatriculaAntSistema(true);
			salvarPredioDefinitivo();
			setMotivoTramite("40");
			setOcultarBotonSiguiente(true);
			setMostrarPanelMotivos(true);
		}
		else
			setMotivoTramite("30");

		generarMotivos105(false, isAccionAsociar());

		PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		PrimeFaces.current().ajax().update("idFormAntSistema:panelTramites105");

		{
			Wizard lw_wizard;

			lw_wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot()
					                            .findComponent("idFormAntSistema:idWDetallesPredio");

			if(lw_wizard != null)
				lw_wizard.setStep("idTDecisionesAntSistema");
		}

		PrimeFaces.current().ajax().update("idFormAntSistema");
	}

	/**
	 * Verifica que solo se seleccione un detalle a asociar con la matrícula de un documento seleccionado.
	 *
	 * @param adasui_param Objeto contenedor del detalle seleccionado
	 */
	public void validarChecksMatriculasDetallesAgregados(DetalleAntSistemaUI adasui_param)
	{
		Collection<DetalleAntSistemaUI> lcdasui_detalles;

		lcdasui_detalles = getDetallesAntSistemaPorSolicitud();

		if(CollectionUtils.isValidCollection(lcdasui_detalles) && (adasui_param != null))
		{
			String ls_idDatosParam;
			String ls_idDetalleParam;

			ls_idDatosParam       = StringUtils.getStringNotNull(adasui_param.getIdDatosAntSistema());
			ls_idDetalleParam     = StringUtils.getStringNotNull(adasui_param.getIdDetalleAntSistema());

			for(DetalleAntSistemaUI ldasui_data : lcdasui_detalles)
			{
				if(ldasui_data != null)
				{
					String ls_idDatosTmp;
					String ls_idDetalleTmp;

					ls_idDatosTmp       = StringUtils.getStringNotNull(ldasui_data.getIdDatosAntSistema());
					ls_idDetalleTmp     = StringUtils.getStringNotNull(ldasui_data.getIdDetalleAntSistema());

					if(ls_idDatosParam.equals(ls_idDatosTmp) && ls_idDetalleParam.equals(ls_idDetalleTmp))
					{
						boolean lb_asociar;

						lb_asociar = ldasui_data.isAsociarMatriculaDocumento();

						setMatriculaDocSeleccionadaParaDetalle(lb_asociar);
						adasui_param.setDetalleMatriculaSeleccionado(lb_asociar);
					}
					else
						ldasui_data.setAsociarMatriculaDocumento(false);
				}
			}

			PrimeFaces.current().ajax().update("idFormAntSistema:idDTDetallesPredioAntSis");
		}
	}

	/**
	 * Método de validación de las matrículas agregadas en Antiguo sistema
	 * @param ac_dasMatriculas con las matriculas a validar
	 * @throws B2BException
	 */
	public void validarMatriculas(Collection<DatosAntSistema> ac_dasMatriculas)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(ac_dasMatriculas))
			{
				for(DatosAntSistema li_das : ac_dasMatriculas)
				{
					Collection<String> lcs_turnosBloqueo;

					lcs_turnosBloqueo = irr_registroRemote.findTurnosBloqueoPredio(li_das);

					if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
					{
						String ls_turnosBloqueo;
						String ls_error;

						ls_turnosBloqueo     = "";
						ls_error             = null;

						for(String ls_turnoTemp : lcs_turnosBloqueo)
							ls_turnosBloqueo = ls_turnosBloqueo + ",\n" + ls_turnoTemp;

						ls_error = ("El folio de matrícula " + li_das.getIdMatricula()
							+ " se encuentra bloqueado con los turnos: " + ls_turnosBloqueo);

						addMessage("I", ls_error);

						PrimeFaces.current().ajax().update("idFormAntSistema:globalMsg:");
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_MATRICULAS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarDetalleAntSistema", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(cs_MESSAGE_ID_GROWL);
		}
	}

	/**
	 * Validar otro circulo.
	 * @throws B2BException
	 */
	public void validarOtroCirculo()
	    throws B2BException
	{
		//boolean lb_asociar;
		boolean lb_crear;
		boolean lb_informe;
		boolean lb_etapa101;
		boolean lb_idSubProceso;

		lb_etapa101         = isEtapa101();
		//lb_asociar      = isAsociarMatriculaAntSistema();
		lb_crear            = isCrearMatriculaAntSistema();
		lb_informe          = isInformeDeBusquedaAntSistema();
		lb_etapa101         = isEtapa101();
		lb_idSubProceso     = false;
		setMostrarComplementacionCirculoDestino(false);
		setNombreCirculoComplementacion(null);
		setJustificacionComplementacion(null);

		if(StringUtils.isValidString(getIdSolicitud()))
			lb_idSubProceso = irr_registroRemote.validarSubProceso(getIdSolicitud());

		if((lb_crear || lb_informe) && !ib_mostrarPanelJustificacionFirma && lb_etapa101 && lb_idSubProceso)
		{
			PrimeFaces.current().executeScript("PF('dlgPreguntarConfirmacionOtroCirculo').show();");
			PrimeFaces.current().ajax().update("fDlgPreguntarConfirmacionOtroCirculo");
		}
		else
			salvarPredioDefinitivo();
	}

	/**
	 * Selecciona un error a mostrar dependiendo del estado de revisión de los predios del turno.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void mostrarErrorSeleccionPredios()
	    throws B2BException
	{
		if(isRevisionPrediosCompleta())
			throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO_REVISADO);
		else
		{
			Object[]           loa_args;
			Collection<String> lcs_nombrePredios;

			loa_args              = new String[1];
			lcs_nombrePredios     = getNombrePrediosSinRevision();

			if(CollectionUtils.isValidCollection(lcs_nombrePredios))
			{
				StringBuilder    lsb_nombres;
				Iterator<String> lis_iterador;

				lsb_nombres      = new StringBuilder();
				lis_iterador     = lcs_nombrePredios.iterator();

				while(lis_iterador.hasNext())
				{
					String ls_data;

					ls_data = lis_iterador.next();

					if(StringUtils.isValidString(ls_data))
					{
						lsb_nombres.append(ls_data);

						if(lis_iterador.hasNext())
							lsb_nombres.append(", ");
					}
				}

				loa_args[0] = lsb_nombres.toString();
			}
			else
				loa_args[0] = EstadoCommon.SIN_NOMBRE;

			throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_PREDIO, loa_args);
		}
	}
}
