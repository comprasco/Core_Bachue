package com.bachue.snr.prosnr01.web.bean.segundaInstancia;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.segunda.instancia.SegundaInstanciaRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanRecepcionDocumentos;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanGrabacion.
 *
 * @author dbeltran
 */
@SessionScoped
@ManagedBean(name = "beanSegundaInstancia")
public class BeanSegundaInstancia extends BeanRecepcionDocumentos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2740575763003600788L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanSegundaInstancia.class);

	/** Constante is_formularioProcesosGrabacion. */
	private static final String CS_ID_FORM = "fSegundaInstancia";

	/** Constante is_globalMsg. */
	private static final String is_globalMsg = ":globalMsg";

	/** Constante is_messageProcesosGrabacion. */
	private static final String is_messages = CS_ID_FORM + is_globalMsg;

	/** Propiedad is valorBoton. */
	String is_valorBoton;

	/** Atributo laar_actuacionesAdministrativasRemote */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Atributo icpn_personaNotificacion */
	private Collection<PersonaNotificacion> icpn_personaNotificacion;

	/**Propiedad resultados Notificacion*/
	private Collection<PersonaNotificacion> icpn_resultadosNotificacion;

	/** Propiedad ictr medios comunicar inter. */
	private Collection<TipoRecepcion> ictr_mediosComunicarInter;

	/** Propiedad ictr medios notificar inter. */
	private Collection<TipoRecepcion> ictr_mediosNotificarInter;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad persona*/
	private Persona ip_persona;

	/** Propiedad ipce correo electronico. */
	private PersonaCorreoElectronico ipce_correoElectronico;

	/** Propiedad ipt telefono fijo. */
	private PersonaTelefono ipt_telefonoFijo;

	/** Propiedad ipt telefono movil. */
	private PersonaTelefono ipt_telefonoMovil;

	/**  Propiedad irr_referenceRemote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad registro datos consultados*/
	private Registro ir_registroDatosConsultados;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/**  Propiedad laar_actuacionesAdministrativasRemote. */
	@EJB
	private SegundaInstanciaRemote isir_segundaInstanciaRemote;

	/**Propiedad solicitud*/
	private Solicitud is_solicitud;

	/** Propiedad isc_zip. */
	private StreamedContent isc_zip;

	/** Propiedad is id correo seleccion. */
	private String is_idCorreoSeleccion;

	/** Propiedad is id dir corr seleccion. */
	private String is_idDirCorrSeleccion;

	/** Propiedad is id dir res seleccion. */
	private String is_idDirResSeleccion;

	/** Propiedad is id dir res seleccion inter. */
	private String is_idDirResSeleccionInter;

	/** Propiedad is id tel fijo seleccion. */
	private String is_idTelFijoSeleccion;

	/** Propiedad is id tel mov seleccion. */
	private String is_idTelMovSeleccion;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is misma direccion correspondencia. */
	private String is_mismaDireccionCorrespondencia;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad is primer apellido temp. */
	private String is_primerApellidoTemp;

	/** Propiedad is primer nombre temp. */
	private String is_primerNombreTemp;

	/** Propiedad is razon social temp. */
	private String is_razonSocialTemp;

	/** Propiedad is segundo apellido temp. */
	private String is_segundoApellidoTemp;

	/** Propiedad is segundo nombre temp. */
	private String is_segundoNombreTemp;

	/**  Propiedad is_tipoArchivo. */
	private String is_tipoArchivo;

	/** Propiedad iba archivo. */
	private byte[] iba_archivo;

	/** Propiedad iba file. */
	private byte[] iba_file;

	/** Propiedad ib accion btn edit per nit. */
	private boolean ib_accionBtnEditPerNit;

	/** Propiedad ib accion btn edit per normal. */
	private boolean ib_accionBtnEditPerNormal;

	/** Propiedad ib argumentos recurrente */
	private boolean ib_argumentosRecurrente;

	/** Propiedad ib bdatos guardados. */
	private boolean ib_bdatosGuardados;

	/** Propiedad ib consideracion */
	private boolean ib_consideracion;

	/** Propiedad ib consideracion primera instancia */
	private boolean ib_consideracionPrimeraInstancia;

	/** Propiedad ib consideracion sajr */
	private boolean ib_consideracionSajr;

	/** Propiedad ib consulta sin registro. */
	private boolean ib_consultaSinRegistro;

	/** Propiedad ib deshabilitar correo. */
	private boolean ib_deshabilitarCorreo;

	/** Propiedad ib deshabilitar correspondencia. */
	private boolean ib_deshabilitarCorrespondencia;

	/** Propiedad ib deshabilitar datos basicos. */
	private boolean ib_deshabilitarDatosBasicos;

	/** Propiedad ib deshabilitar residencia. */
	private boolean ib_deshabilitarResidencia;

	/** Propiedad ib deshabilitar tel fijo. */
	private boolean ib_deshabilitarTelFijo;

	/** Propiedad ib deshabilitar tel movil. */
	private boolean ib_deshabilitarTelMovil;

	/** Atributo ib_documentosEnviados */
	private boolean ib_documentosEnviados;

	/** Propiedad ib observaciones */
	private boolean ib_esObservaciones;

	/** Propiedad ib fecha*/
	private boolean ib_fecha;

	/** Propiedad ib habilitado por consulta. */
	private boolean ib_habilitadoPorConsulta;

	/** Propiedad ib habilitar panel datos contacto. */
	private boolean ib_habilitarPanelDatosContacto;

	/** Propiedad ib habilitar panel dir correspondencia. */
	private boolean ib_habilitarPanelDirCorrespondencia;

	/** Propiedad ib habilitar panel dir residencia. */
	private boolean ib_habilitarPanelDirResidencia;

	/** Atributo ib_mostrarConsultarInteresadoInter */
	private boolean ib_mostrarConsultarInteresadoInter;

	/** Propiedad ib mostrar datos basicos. */
	private boolean ib_mostrarDatosBasicos;

	/** Propiedad ib mostrar datos basicos interviniente. */
	private boolean ib_mostrarDatosBasicosInterviniente;

	/** Propiedad is mostrarDatosPersona */
	private boolean ib_mostrarDatosPersona;

	/**  Atributo ib_mostrarDescargarZip. */
	private boolean ib_mostrarDescargarZip;

	/** Propiedad is mostrar panel consulta*/
	private boolean ib_mostrarPanelConsulta;

	/** Propiedad ib mostrar resolucion. */
	private boolean ib_mostrarResolucion;

	/** Propiedad ib persona consultada. */
	private boolean ib_personaConsultada;

	/** Propiedad ib perteniencia */
	private boolean ib_pertinencia;

	/** Propiedad ib perteniencia2 */
	private boolean ib_pertinencia2;

	/** Propiedad ib planeacion. */
	private boolean ib_planeacion;

	/** Propiedad ib proyectar. */
	private boolean ib_proyectar;

	/** Propiedad ib proyectar resolucion. */
	private boolean ib_proyectarResolucion;

	/**Propiedad pruebas*/
	private boolean ib_pruebas;

	/** Propiedad ib rendered. */
	private boolean ib_rendered;

	/** Propiedad ib resuelve */
	private boolean ib_resuelve;

	/** Propiedad ib solicitudDocumentacion */
	private boolean ib_solicitudDocumentacion;

	/**
	 * @param Modifica el valor de la propiedad ib_accionBtnEditPerNit por ib_accionBtnEditPerNit
	 */
	public void setAccionBtnEditPerNit(boolean ab_b)
	{
		ib_accionBtnEditPerNit = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_accionBtnEditPerNit
	 */
	public boolean isAccionBtnEditPerNit()
	{
		return ib_accionBtnEditPerNit;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_accionBtnEditPerNormal por ib_accionBtnEditPerNormal
	 */
	public void setAccionBtnEditPerNormal(boolean ab_b)
	{
		ib_accionBtnEditPerNormal = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_accionBtnEditPerNormal
	 */
	public boolean isAccionBtnEditPerNormal()
	{
		return ib_accionBtnEditPerNormal;
	}

	/**
	 * Modifica el valor de Archivo.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor archivo.
	 *
	 * @return el valor de archivo
	 */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * @param Modifica el valor de la propiedad argumentosRecurrente por ab_b
	 */
	public void setArgumentosRecurrente(boolean ab_b)
	{
		ib_argumentosRecurrente = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad argumentosRecurrente
	 */
	public boolean isArgumentosRecurrente()
	{
		return ib_argumentosRecurrente;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_consideracion por ib_consideracion
	 */
	public void setConsideracion(boolean ab_b)
	{
		ib_consideracion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_consideracion
	 */
	public boolean isConsideracion()
	{
		return ib_consideracion;
	}

	/**
	 * @param Modifica el valor de la propiedad consideracionPrimeraInstancia por ab_b
	 */
	public void setConsideracionPrimeraInstancia(boolean ab_b)
	{
		ib_consideracionPrimeraInstancia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad consideracionPrimeraInstancia
	 */
	public boolean isConsideracionPrimeraInstancia()
	{
		return ib_consideracionPrimeraInstancia;
	}

	/**
	 * @param Modifica el valor de la propiedad consideracionSajr por consideracionSajr
	 */
	public void setConsideracionSajr(boolean ab_b)
	{
		ib_consideracionSajr = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad consideracionSajr
	 */
	public boolean isConsideracionSajr()
	{
		return ib_consideracionSajr;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_consultaSinRegistro por ib_consultaSinRegistro
	 */
	public void setConsultaSinRegistro(boolean ab_b)
	{
		ib_consultaSinRegistro = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_consultaSinRegistro
	 */
	public boolean isConsultaSinRegistro()
	{
		return ib_consultaSinRegistro;
	}

	/**
	 * @param Modifica el valor de la propiedad ipce_correoElectronico por ipce_correoElectronico
	 */
	public void setCorreoElectronico(PersonaCorreoElectronico apce_pce)
	{
		ipce_correoElectronico = apce_pce;
	}

	/**
	 * @return Retorna el valor de la propiedad ipce_correoElectronico
	 */
	public PersonaCorreoElectronico getCorreoElectronico()
	{
		if(ipce_correoElectronico == null)
			ipce_correoElectronico = new PersonaCorreoElectronico();

		return ipce_correoElectronico;
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
			idas_datosAntSistema = new DatosAntSistema();

		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de datos guardados.
	 *
	 * @param ab_b asigna el valor a la propiedad datos guardados
	 */
	public void setDatosGuardados(boolean ab_b)
	{
		ib_bdatosGuardados = ab_b;
	}

	/**
	 * Valida la propiedad datos guardados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos guardados
	 */
	public boolean isDatosGuardados()
	{
		return ib_bdatosGuardados;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarCorreo por ib_deshabilitarCorreo
	 */
	public void setDeshabilitarCorreo(boolean ab_b)
	{
		ib_deshabilitarCorreo = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarCorreo
	 */
	public boolean isDeshabilitarCorreo()
	{
		return ib_deshabilitarCorreo;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarCorrespondencia por ib_deshabilitarCorrespondencia
	 */
	public void setDeshabilitarCorrespondencia(boolean ab_b)
	{
		ib_deshabilitarCorrespondencia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarCorrespondencia
	 */
	public boolean isDeshabilitarCorrespondencia()
	{
		return ib_deshabilitarCorrespondencia;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarDatosBasicos por ib_deshabilitarDatosBasicos
	 */
	public void setDeshabilitarDatosBasicos(boolean ab_b)
	{
		ib_deshabilitarDatosBasicos = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarDatosBasicos
	 */
	public boolean isDeshabilitarDatosBasicos()
	{
		return ib_deshabilitarDatosBasicos;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarResidencia por ib_deshabilitarResidencia
	 */
	public void setDeshabilitarResidencia(boolean ab_b)
	{
		ib_deshabilitarResidencia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarResidencia
	 */
	public boolean isDeshabilitarResidencia()
	{
		return ib_deshabilitarResidencia;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarTelFijo por ib_deshabilitarTelFijo
	 */
	public void setDeshabilitarTelFijo(boolean ab_b)
	{
		ib_deshabilitarTelFijo = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarTelFijo
	 */
	public boolean isDeshabilitarTelFijo()
	{
		return ib_deshabilitarTelFijo;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_deshabilitarTelMovil por ib_deshabilitarTelMovil
	 */
	public void setDeshabilitarTelMovil(boolean ab_b)
	{
		ib_deshabilitarTelMovil = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_deshabilitarTelMovil
	 */
	public boolean isDeshabilitarTelMovil()
	{
		return ib_deshabilitarTelMovil;
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
	 * @param Modifica el valor de la propiedad esObservaciones por esObservaciones
	 */
	public void setEsObservaciones(boolean ab_b)
	{
		ib_esObservaciones = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esObservaciones
	 */
	public boolean isEsObservaciones()
	{
		return ib_esObservaciones;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_fecha por ib_fecha
	 */
	public void setFecha(boolean ab_fecha)
	{
		ib_fecha = ab_fecha;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_fecha
	 */
	public boolean isFecha()
	{
		return ib_fecha;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setFile(byte[] aba_ba)
	{
		iba_file = aba_ba;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public byte[] getFile()
	{
		return iba_file;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_habilitadoPorConsulta por ib_habilitadoPorConsulta
	 */
	public void setHabilitadoPorConsulta(boolean ab_b)
	{
		ib_habilitadoPorConsulta = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_habilitadoPorConsulta
	 */
	public boolean isHabilitadoPorConsulta()
	{
		return ib_habilitadoPorConsulta;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_habilitarPanelDatosContacto por ib_habilitarPanelDatosContacto
	 */
	public void setHabilitarPanelDatosContacto(boolean ab_b)
	{
		ib_habilitarPanelDatosContacto = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_habilitarPanelDatosContacto
	 */
	public boolean isHabilitarPanelDatosContacto()
	{
		return ib_habilitarPanelDatosContacto;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_habilitarPanelDirCorrespondencia por ib_habilitarPanelDirCorrespondencia
	 */
	public void setHabilitarPanelDirCorrespondencia(boolean ab_b)
	{
		ib_habilitarPanelDirCorrespondencia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_habilitarPanelDirCorrespondencia
	 */
	public boolean isHabilitarPanelDirCorrespondencia()
	{
		return ib_habilitarPanelDirCorrespondencia;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_habilitarPanelDirResidencia por ib_habilitarPanelDirResidencia
	 */
	public void setHabilitarPanelDirResidencia(boolean ab_b)
	{
		ib_habilitarPanelDirResidencia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_habilitarPanelDirResidencia
	 */
	public boolean isHabilitarPanelDirResidencia()
	{
		return ib_habilitarPanelDirResidencia;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCorreoSeleccion por is_idCorreoSeleccion
	 */
	public void setIdCorreoSeleccion(String as_s)
	{
		is_idCorreoSeleccion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCorreoSeleccion
	 */
	public String getIdCorreoSeleccion()
	{
		return is_idCorreoSeleccion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idDirCorrSeleccion por is_idDirCorrSeleccion
	 */
	public void setIdDirCorrSeleccion(String as_s)
	{
		is_idDirCorrSeleccion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idDirCorrSeleccion
	 */
	public String getIdDirCorrSeleccion()
	{
		return is_idDirCorrSeleccion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idDirResSeleccion por is_idDirResSeleccion
	 */
	public void setIdDirResSeleccion(String as_s)
	{
		is_idDirResSeleccion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idDirResSeleccion
	 */
	public String getIdDirResSeleccion()
	{
		return is_idDirResSeleccion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idDirResSeleccionInter por is_idDirResSeleccionInter
	 */
	public void setIdDirResSeleccionInter(String as_s)
	{
		is_idDirResSeleccionInter = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idDirResSeleccionInter
	 */
	public String getIdDirResSeleccionInter()
	{
		return is_idDirResSeleccionInter;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idTelFijoSeleccion por is_idTelFijoSeleccion
	 */
	public void setIdTelFijoSeleccion(String as_s)
	{
		is_idTelFijoSeleccion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTelFijoSeleccion
	 */
	public String getIdTelFijoSeleccion()
	{
		return is_idTelFijoSeleccion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idTelMovSeleccion por is_idTelMovSeleccion
	 */
	public void setIdTelMovSeleccion(String as_s)
	{
		is_idTelMovSeleccion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTelMovSeleccion
	 */
	public String getIdTelMovSeleccion()
	{
		return is_idTelMovSeleccion;
	}

	/** {@inheritdoc} */
	public String getIdTurno()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = super.getIdTurno();
		lth_turnoHistoria     = getTurnoHistoria();

		if((lth_turnoHistoria != null) && !StringUtils.isValidString(ls_return))
			ls_return = lth_turnoHistoria.getIdTurno();

		return ls_return;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/** {@inheritdoc} */
	public String getIdTurnoHistoria()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = super.getIdTurnoHistoria();
		lth_turnoHistoria     = getTurnoHistoria();

		if((lth_turnoHistoria != null) && !StringUtils.isValidString(ls_return))
		{
			Long ll_idTurnoHistoria;

			ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				ls_return = ll_idTurnoHistoria.toString();
		}

		return ls_return;
	}

	/**
	 * Modifica el valor de imagen documento.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		return idsc_imagenDocumento;
	}

	/**
	 * @param Modifica el valor de la propiedad ictr_mediosComunicarInter por ictr_mediosComunicarInter
	 */
	public void setMediosComunicarInter(Collection<TipoRecepcion> ictr_mediosComunicarInter)
	{
		this.ictr_mediosComunicarInter = ictr_mediosComunicarInter;
	}

	/**
	 * @return Retorna el valor de la propiedad ictr_mediosComunicarInter
	 */
	public Collection<TipoRecepcion> getMediosComunicarInter()
	{
		return ictr_mediosComunicarInter;
	}

	/**
	 * @param Modifica el valor de la propiedad ictr_mediosNotificarInter por ictr_mediosNotificarInter
	 */
	public void setMediosNotificarInter(Collection<TipoRecepcion> ictr_mediosNotificarInter)
	{
		this.ictr_mediosNotificarInter = ictr_mediosNotificarInter;
	}

	/**
	 * @return Retorna el valor de la propiedad ictr_mediosNotificarInter
	 */
	public Collection<TipoRecepcion> getMediosNotificarInter()
	{
		return ictr_mediosNotificarInter;
	}

	/**
	 * @param Modifica el valor de la propiedad is_mismaDireccionCorrespondencia por is_mismaDireccionCorrespondencia
	 */
	public void setMismaDireccionCorrespondencia(String is_mismaDireccionCorrespondencia)
	{
		this.is_mismaDireccionCorrespondencia = is_mismaDireccionCorrespondencia;
	}

	/**
	 * @return Retorna el valor de la propiedad is_mismaDireccionCorrespondencia
	 */
	public String getMismaDireccionCorrespondencia()
	{
		return is_mismaDireccionCorrespondencia;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarConsultarInteresadoInter por ib_mostrarConsultarInteresadoInter
	 */
	public void setMostrarConsultarInteresadoInter(boolean ib_mostrarConsultarInteresadoInter)
	{
		this.ib_mostrarConsultarInteresadoInter = ib_mostrarConsultarInteresadoInter;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarConsultarInteresadoInter
	 */
	public boolean isMostrarConsultarInteresadoInter()
	{
		return ib_mostrarConsultarInteresadoInter;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarDatosBasicos por ib_mostrarDatosBasicos
	 */
	public void setMostrarDatosBasicos(boolean ib_mostrarDatosBasicos)
	{
		this.ib_mostrarDatosBasicos = ib_mostrarDatosBasicos;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarDatosBasicos
	 */
	public boolean isMostrarDatosBasicos()
	{
		return ib_mostrarDatosBasicos;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarDatosBasicosInterviniente por ib_mostrarDatosBasicosInterviniente
	 */
	public void setMostrarDatosBasicosInterviniente(boolean ib_mostrarDatosBasicosInterviniente)
	{
		this.ib_mostrarDatosBasicosInterviniente = ib_mostrarDatosBasicosInterviniente;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarDatosBasicosInterviniente
	 */
	public boolean isMostrarDatosBasicosInterviniente()
	{
		return ib_mostrarDatosBasicosInterviniente;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarDatosPersona por ib_mostrarDatosPersona
	 */
	public void setMostrarDatosPersona(boolean ib_mostrarDatosPersona)
	{
		this.ib_mostrarDatosPersona = ib_mostrarDatosPersona;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarDatosPersona
	 */
	public boolean isMostrarDatosPersona()
	{
		return ib_mostrarDatosPersona;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarDescargarZip(boolean ab_b)
	{
		ib_mostrarDescargarZip = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarDescargarZip()
	{
		return ib_mostrarDescargarZip;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarPanelConsulta por ib_mostrarPanelConsulta
	 */
	public void setMostrarPanelConsulta(boolean ib_mostrarPanelConsulta)
	{
		this.ib_mostrarPanelConsulta = ib_mostrarPanelConsulta;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarPanelConsulta
	 */
	public boolean isMostrarPanelConsulta()
	{
		return ib_mostrarPanelConsulta;
	}

	/**
	 * Modifica el valor de MostrarResolucion.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarResolucion(boolean ab_b)
	{
		ib_mostrarResolucion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar resolucion.
	 *
	 * @return Retorna el valor de la propiedad mostrarResolucion
	 */
	public boolean isMostrarResolucion()
	{
		return ib_mostrarResolucion;
	}

	/**
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_s asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/**
	 * Retorna el valor de nir consultvisa.
	 *
	 * @return el valor de nir consulta
	 */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * @param Modifica el valor de la propiedad ip_persona por ip_persona
	 */
	public void setPersona(Persona ap_persona)
	{
		ip_persona = ap_persona;
	}

	/**
	 * @return Retorna el valor de la propiedad ip_persona
	 */
	public Persona getPersona()
	{
		if(ip_persona == null)
			ip_persona = new Persona();

		return ip_persona;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_personaConsultada por ib_personaConsultada
	 */
	public void setPersonaConsultada(boolean ib_personaConsultada)
	{
		this.ib_personaConsultada = ib_personaConsultada;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_personaConsultada
	 */
	public boolean isPersonaConsultada()
	{
		return ib_personaConsultada;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param al_l Argumento encargado de modificar la propiedad.
	 */
	public void setPersonaNotificacion(Collection<PersonaNotificacion> acpn_pn)
	{
		icpn_personaNotificacion = acpn_pn;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<PersonaNotificacion> getPersonaNotificacion()
	{
		return icpn_personaNotificacion;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_perteniencia por ib_perteniencia
	 */
	public void setPertinencia(boolean ab_pertinencia)
	{
		ib_pertinencia = ab_pertinencia;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_perteniencia
	 */
	public boolean isPertinencia()
	{
		return ib_pertinencia;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_pertinencia2 por ib_pertinencia2
	 */
	public void setPertinencia2(boolean ab_pertinencia2)
	{
		ib_pertinencia2 = ab_pertinencia2;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_pertinencia2
	 */
	public boolean isPertinencia2()
	{
		return ib_pertinencia2;
	}

	/**
	 * @param Modifica el valor de la propiedad planeacion por planeacion
	 */
	public void setPlaneacion(boolean ab_b)
	{
		ib_planeacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad planeacion
	 */
	public boolean isPlaneacion()
	{
		return ib_planeacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_primerApellidoTemp por is_primerApellidoTemp
	 */
	public void setPrimerApellidoTemp(String is_primerApellidoTemp)
	{
		this.is_primerApellidoTemp = is_primerApellidoTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_primerApellidoTemp
	 */
	public String getPrimerApellidoTemp()
	{
		return is_primerApellidoTemp;
	}

	/**
	 * @param Modifica el valor de la propiedad is_primerNombreTemp por is_primerNombreTemp
	 */
	public void setPrimerNombreTemp(String is_primerNombreTemp)
	{
		this.is_primerNombreTemp = is_primerNombreTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_primerNombreTemp
	 */
	public String getPrimerNombreTemp()
	{
		return is_primerNombreTemp;
	}

	/**
	 * @param Modifica el valor de la propiedad proyectar por proyectar
	 */
	public void setProyectar(boolean ab_b)
	{
		ib_proyectar = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad proyectar
	 */
	public boolean isProyectar()
	{
		return ib_proyectar;
	}

	/**
	 * Modifica el valor de ProyectarResolucion.
	 *
	 * @param ab_b de ab b
	 */
	public void setProyectarResolucion(boolean ab_b)
	{
		ib_proyectarResolucion = ab_b;
	}

	/**
	 * Valida la propiedad proyectar resolucion.
	 *
	 * @return Retorna el valor de la propiedad proyectarResolución
	 */
	public boolean isProyectarResolucion()
	{
		return ib_proyectarResolucion;
	}

	/**
	 * @param Modifica el valor de la propiedad pruebas por ab_b
	 */
	public void setPruebas(boolean ab_b)
	{
		ib_pruebas = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad pruebas
	 */
	public boolean isPruebas()
	{
		return ib_pruebas;
	}

	/**
	 * @param Modifica el valor de la propiedad is_razonSocialTemp por is_razonSocialTemp
	 */
	public void setRazonSocialTemp(String is_razonSocialTemp)
	{
		this.is_razonSocialTemp = is_razonSocialTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_razonSocialTemp
	 */
	public String getRazonSocialTemp()
	{
		return is_razonSocialTemp;
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
	 * @param Modifica el valor de la propiedad ib_rendered por ib_rendered
	 */
	public void setRendered(boolean ib_rendered)
	{
		this.ib_rendered = ib_rendered;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_rendered
	 */
	public boolean isRendered()
	{
		return ib_rendered;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_resuelve por ib_resuelve
	 */
	public void setResuelve(boolean ab_resuelve)
	{
		ib_resuelve = ab_resuelve;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_resuelve
	 */
	public boolean isResuelve()
	{
		return ib_resuelve;
	}

	/**
	 * @param Modifica el valor de la propiedad icpn_resultadosNotificacion por icpn_resultadosNotificacion
	 */
	public void setResultadosNotificacion(Collection<PersonaNotificacion> acpn_resultadosNotificacion)
	{
		icpn_resultadosNotificacion = acpn_resultadosNotificacion;
	}

	/**
	 * @return Retorna el valor de la propiedad icpn_resultadosNotificacion
	 */
	public Collection<PersonaNotificacion> getResultadosNotificacion()
	{
		return icpn_resultadosNotificacion;
	}

	/**
	 * @param Modifica el valor de la propiedad is_segundoApellidoTemp por is_segundoApellidoTemp
	 */
	public void setSegundoApellidoTemp(String is_segundoApellidoTemp)
	{
		this.is_segundoApellidoTemp = is_segundoApellidoTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_segundoApellidoTemp
	 */
	public String getSegundoApellidoTemp()
	{
		return is_segundoApellidoTemp;
	}

	/**
	 * @param Modifica el valor de la propiedad is_segundoNombreTemp por is_segundoNombreTemp
	 */
	public void setSegundoNombreTemp(String is_segundoNombreTemp)
	{
		this.is_segundoNombreTemp = is_segundoNombreTemp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_segundoNombreTemp
	 */
	public String getSegundoNombreTemp()
	{
		return is_segundoNombreTemp;
	}

	/**
	 * @param Modifica el valor de la propiedad is_solicitud por is_solicitud
	 */
	public void setSolicitud(Solicitud as_solicitud)
	{
		is_solicitud = as_solicitud;
	}

	/**
	 * @return Retorna el valor de la propiedad is_solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_solicitudDocumentacion por ib_solicitudDocumentacion
	 */
	public void setSolicitudDocumentacion(boolean ib_solicitudDocumentacion)
	{
		this.ib_solicitudDocumentacion = ib_solicitudDocumentacion;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_solicitudDocumentacion
	 */
	public boolean isSolicitudDocumentacion()
	{
		return ib_solicitudDocumentacion;
	}

	/**
	 * @param Modifica el valor de la propiedad ipt_telefonoFijo por ipt_telefonoFijo
	 */
	public void setTelefonoFijo(PersonaTelefono apt_pt)
	{
		ipt_telefonoFijo = apt_pt;
	}

	/**
	 * @return Retorna el valor de la propiedad ipt_telefonoFijo
	 */
	public PersonaTelefono getTelefonoFijo()
	{
		if(ipt_telefonoFijo == null)
			ipt_telefonoFijo = new PersonaTelefono();

		return ipt_telefonoFijo;
	}

	/**
	 * @param Modifica el valor de la propiedad ipt_telefonoMovil por ipt_telefonoMovil
	 */
	public void setTelefonoMovil(PersonaTelefono apt_pt)
	{
		ipt_telefonoMovil = apt_pt;
	}

	/**
	 * @return Retorna el valor de la propiedad ipt_telefonoMovil
	 */
	public PersonaTelefono getTelefonoMovil()
	{
		if(ipt_telefonoMovil == null)
			ipt_telefonoMovil = new PersonaTelefono();

		return ipt_telefonoMovil;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Modifica el valor de miga pan.
	 *
	 * @param as_s asigna el valor a la propiedad valor Boton
	 */
	public void setValorBoton(String as_s)
	{
		is_valorBoton = as_s;
	}

	/**
	 * Retorna el valor boton.
	 *
	 * @return el valor boton
	 */
	public String getValorBoton()
	{
		return is_valorBoton;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param asc_sc Argumento encargado de modificar la propiedad.
	 */
	public void setZip(StreamedContent asc_sc)
	{
		isc_zip = asc_sc;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public StreamedContent getZip()
	{
		return isc_zip;
	}

	/**
	 * Accion boton editar persona.
	 *
	 * @param ap_p correspondiente al valor del tipo de objeto Persona
	 * @param ab_cargarDatosPersonales de ab cargar datos personales
	 */
	public void accionBotonEditarPersona(Persona ap_p, boolean ab_cargarDatosPersonales)
	{
		if(ap_p != null)
		{
			if(ab_cargarDatosPersonales)
				cargarDatosPersonales(ap_p, null);

			Persona lp_persona;
			lp_persona = getPersona();

			if(lp_persona != null)
			{
				String ls_tipoDoc;
				String ls_tipoPersona;

				ls_tipoDoc         = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());
				ls_tipoPersona     = StringUtils.getStringNotNull(lp_persona.getIdTipoPersona());

				if(ls_tipoDoc.equals(IdentificadoresCommon.NIT) || ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
					setAccionBtnEditPerNit(true);
				else
					setAccionBtnEditPerNormal(true);

				setPrimerNombreTemp(lp_persona.getPrimerNombre());
				setSegundoNombreTemp(lp_persona.getSegundoNombre());
				setPrimerApellidoTemp(lp_persona.getPrimerApellido());
				setSegundoApellidoTemp(lp_persona.getSegundoApellido());
				setRazonSocialTemp(lp_persona.getRazonSocial());
			}
		}
	}

	/**
	 * Método sobrecargado para cargar o no datos personales
	 *
	 * @param ap_pcorrespondiente al valor del tipo de objeto Persona
	 */
	public void accionBotonEditarPersonaRecurso(Persona ap_p)
	{
		accionBotonEditarPersona(ap_p, true);
		setDeshabilitarDatosBasicos(false);
		setMostrarDatosPersona(true);
		setMostrarPanelConsulta(false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		setMotivoTramite(null);

		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Metodo encargado de agregar interviniente a resultados de notificación.
	 */
	public void agregarIntervinienteResultadoNotificacion()
	{
		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				BeanDireccion lbd_beanDireccion;
				Registro      lr_registro;
				boolean       lb_personaVinculada;

				lbd_beanDireccion       = getBeanDireccion();
				lr_registro             = new Registro();
				lb_personaVinculada     = !isMostrarPanelConsulta();

				lr_registro.setPersona(lp_persona);
				lr_registro.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lr_registro.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lr_registro.setTelefonoFijo(getTelefonoFijo());
				lr_registro.setTelefonoMovil(getTelefonoMovil());
				lr_registro.setPersonaCorreoElectronico(getCorreoElectronico());
				lr_registro.setPersonaVinculada(lb_personaVinculada);

				{
					Solicitud lso_solicitud;

					lso_solicitud = lb_personaVinculada ? lp_persona.getSolicitud() : getSolicitud();

					if(lso_solicitud != null)
					{
						Solicitud ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(lso_solicitud.getIdSolicitud());
						ls_solicitud.setIdProceso(lso_solicitud.getIdProceso());
						ls_solicitud.setIdAutorizacionNotificacion(lso_solicitud.getIdAutorizacionNotificacion());
						ls_solicitud.setIdAutorizacionComunicacion(lso_solicitud.getIdAutorizacionComunicacion());
						ls_solicitud.setIdCalidadSolicitante(lso_solicitud.getIdCalidadSolicitante());
						ls_solicitud.setParticipaInterviniente(lso_solicitud.getParticipaInterviniente());
						ls_solicitud.setEntidadExenta(lso_solicitud.getEntidadExenta());

						lr_registro.setSolicitud(ls_solicitud);
					}
				}

				boolean lb_insertarPersona;
				boolean lb_editarPorNit;
				boolean lb_editarPorNormal;

				lb_insertarPersona     = false;
				lb_editarPorNit        = isAccionBtnEditPerNit();
				lb_editarPorNormal     = isAccionBtnEditPerNormal();

				if(lb_editarPorNit || lb_editarPorNormal)
				{
					if(lb_editarPorNormal)
					{
						String ls_primerNombreOri;
						String ls_segundoNombreOri;
						String ls_primerApellidoOri;
						String ls_segundoApellidoOri;
						String ls_primerNombreMod;
						String ls_segundoNombreMod;
						String ls_primerApellidoMod;
						String ls_segundoApellidoMod;

						ls_primerNombreOri        = StringUtils.getStringNotNull(getPrimerNombreTemp());
						ls_segundoNombreOri       = StringUtils.getStringNotNull(getSegundoNombreTemp());
						ls_primerApellidoOri      = StringUtils.getStringNotNull(getPrimerApellidoTemp());
						ls_segundoApellidoOri     = StringUtils.getStringNotNull(getSegundoApellidoTemp());
						ls_primerNombreMod        = StringUtils.getStringNotNull(lp_persona.getPrimerNombre());
						ls_segundoNombreMod       = StringUtils.getStringNotNull(lp_persona.getSegundoNombre());
						ls_primerApellidoMod      = StringUtils.getStringNotNull(lp_persona.getPrimerApellido());
						ls_segundoApellidoMod     = StringUtils.getStringNotNull(lp_persona.getSegundoApellido());

						if(
						    !ls_primerNombreOri.equalsIgnoreCase(ls_primerNombreMod)
							    || !ls_segundoNombreOri.equalsIgnoreCase(ls_segundoNombreMod)
							    || !ls_primerApellidoOri.equalsIgnoreCase(ls_primerApellidoMod)
							    || !ls_segundoApellidoOri.equalsIgnoreCase(ls_segundoApellidoMod)
						)
							lb_insertarPersona = true;
					}
					else if(lb_editarPorNit)
					{
						String ls_razonSocialOri;
						String ls_razonSocialMod;

						ls_razonSocialOri     = StringUtils.getStringNotNull(getRazonSocialTemp());
						ls_razonSocialMod     = StringUtils.getStringNotNull(lp_persona.getRazonSocial());

						if(!ls_razonSocialOri.equalsIgnoreCase(ls_razonSocialMod))
							lb_insertarPersona = true;
					}
				}

				{
					Solicitud ls_solicitud;

					ls_solicitud = lr_registro.getSolicitud();

					if(ls_solicitud != null)
					{
						Persona lp_personaTemp;

						lp_personaTemp = lr_registro.getPersona();

						ls_solicitud.setIdPersona((lp_personaTemp != null) ? lp_personaTemp.getIdPersona() : null);
					}

					lr_registro.setPersonaCargada(lb_insertarPersona);
					lr_registro.setDireccionResidenciaCargada(isDeshabilitarResidencia());
					lr_registro.setDireccionCorrespondenciaCargada(isDeshabilitarCorrespondencia());
					lr_registro.setTelefonoFijoCargado(isDeshabilitarTelFijo());
					lr_registro.setTelefonoMovilCargado(isDeshabilitarTelMovil());
					lr_registro.setCorreoCargado(isDeshabilitarCorreo());
					lr_registro.setIpCreacion(getRemoteIpAddress());
					lr_registro.setIpModificacion(getRemoteIpAddress());
					lr_registro.setIdTurno(getIdTurno());
				}

				{
					Collection<PersonaNotificacion> lcpn_personaNotificacion;
					int                             li_identificador;

					lcpn_personaNotificacion     = getResultadosNotificacion();
					li_identificador             = 1;

					if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
					{
						for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
						{
							if(lpn_iterador != null)
								li_identificador++;
						}
					}
					else
						lcpn_personaNotificacion = new ArrayList<PersonaNotificacion>();

					{
						PersonaNotificacion lpn_personaNotificacion;

						lpn_personaNotificacion = new PersonaNotificacion();

						lpn_personaNotificacion.setIdentificador(li_identificador);
						lpn_personaNotificacion.setRegistro(lr_registro);
						lpn_personaNotificacion.setIdPersona(lp_persona.getIdPersona());
						lpn_personaNotificacion.setDocumento(lp_persona.getNumeroDocumento());

						{
							String ls_tipoDocumento;

							ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

							if(StringUtils.isValidString(ls_tipoDocumento))
							{
								String ls_destinatario;

								ls_destinatario = ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
									? lp_persona.getRazonSocial()
									: (ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.AUTO) ? null
									                                                                 : lp_persona
										.getNombreCompleto());

								lpn_personaNotificacion.setDestinatario(ls_destinatario);
								lpn_personaNotificacion.setTipoDocumento(ls_tipoDocumento);
							}
						}

						{
							Solicitud ls_solicitud;

							ls_solicitud = getSolicitud();

							if(ls_solicitud != null)
							{
								CalidadSolicitante lcs_calidadSolicitante;

								lcs_calidadSolicitante = new CalidadSolicitante();

								lcs_calidadSolicitante.setIdCalidadSolicitante(ls_solicitud.getIdCalidadSolicitante());

								lcs_calidadSolicitante = ipr_parameterRemote.findCalidadSolicitanteById(
									    lcs_calidadSolicitante
									);

								if(lcs_calidadSolicitante != null)
									lpn_personaNotificacion.setCalidadEnQueActua(lcs_calidadSolicitante.getNombre());
							}
						}

						lcpn_personaNotificacion.add(lpn_personaNotificacion);
					}

					setResultadosNotificacion(lcpn_personaNotificacion);

					cargarMediosNotComInter(false);
					esconderPanels();
					setMostrarConsultarInteresadoInter(false);
					setMostrarDatosPersona(false);
					setMostrarPanelConsulta(false);

					buscarPersonasAsociadasSegundaInstancia();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarIntervinienteResultadoNotificacion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente("global");
		}
	}

	/**
	 * Metodo encargado de agregar un tercero determinado para actuaciones administrativas.
	 */
	public void agregarTerceroDeterminado()
	{
		Collection<PersonaNotificacion> lcpn_personaNotificacion;
		int                             li_identificador;

		lcpn_personaNotificacion     = getPersonaNotificacion();
		li_identificador             = 1;

		if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
		{
			for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
			{
				if(lpn_iterador != null)
					li_identificador++;
			}
		}
		else
			lcpn_personaNotificacion = new ArrayList<PersonaNotificacion>();

		{
			PersonaNotificacion lpn_personaNotificacion;

			lpn_personaNotificacion = new PersonaNotificacion();

			lpn_personaNotificacion.setIdentificador(li_identificador);

			lcpn_personaNotificacion.add(lpn_personaNotificacion);
		}

		setPersonaNotificacion(lcpn_personaNotificacion);
	}

	/**
	 * Metodo encargado de agregar un tercero determinado para actuaciones administrativas.
	 *
	 * @param apn_resultadoNotificacion Argumento de tipo <code>PersonaNotificacion</code> que contiene los datos a agregar.
	 */
	public void agregarTerceroResultadoNotificacion(PersonaNotificacion apn_resultadoNotificacion)
	{
		if(apn_resultadoNotificacion != null)
		{
			try
			{
				{
					String ls_destinatario;

					ls_destinatario = apn_resultadoNotificacion.getDestinatario();

					if(!StringUtils.isValidString(ls_destinatario))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESTINATARIO_VALIDO);
				}

				{
					String ls_direccion;

					ls_direccion = apn_resultadoNotificacion.getDireccion();

					if(!StringUtils.isValidString(ls_direccion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_VALIDA);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento = apn_resultadoNotificacion.getIdDepartamento();

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;

					ls_idMunicipio = apn_resultadoNotificacion.getIdMunicipio();

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					Collection<PersonaNotificacion> lcpn_resultadosNotificacion;
					int                             li_identificador;
					boolean                         lb_existe;

					lcpn_resultadosNotificacion     = getResultadosNotificacion();
					li_identificador                = 1;
					lb_existe                       = false;

					if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
					{
						Iterator<PersonaNotificacion> lipn_iterator;

						lipn_iterator = lcpn_resultadosNotificacion.iterator();

						while(lipn_iterator.hasNext() && !lb_existe)
						{
							PersonaNotificacion lpn_iterador;

							lpn_iterador = lipn_iterator.next();

							if(lpn_iterador != null)
							{
								String ls_destinatario;
								String ls_destinatarioCargado;
								String ls_direccion;
								String ls_direccionCargada;
								String ls_idDepartamento;
								String ls_idDepartamentoCargado;
								String ls_idMunicipio;
								String ls_idMunicipioCargado;

								ls_destinatario              = lpn_iterador.getDestinatario();
								ls_destinatarioCargado       = apn_resultadoNotificacion.getDestinatario();
								ls_direccion                 = lpn_iterador.getDireccion();
								ls_direccionCargada          = apn_resultadoNotificacion.getDireccion();
								ls_idDepartamento            = lpn_iterador.getIdDepartamento();
								ls_idDepartamentoCargado     = apn_resultadoNotificacion.getIdDepartamento();
								ls_idMunicipio               = lpn_iterador.getIdMunicipio();
								ls_idMunicipioCargado        = apn_resultadoNotificacion.getIdMunicipio();

								lb_existe = StringUtils.isValidString(ls_destinatario)
										&& StringUtils.isValidString(ls_destinatarioCargado)
										&& ls_destinatario.equalsIgnoreCase(ls_destinatarioCargado)
										&& StringUtils.isValidString(ls_direccion)
										&& StringUtils.isValidString(ls_direccionCargada)
										&& ls_direccion.equalsIgnoreCase(ls_direccionCargada)
										&& StringUtils.isValidString(ls_idDepartamento)
										&& StringUtils.isValidString(ls_idDepartamentoCargado)
										&& ls_idDepartamento.equalsIgnoreCase(ls_idDepartamentoCargado)
										&& StringUtils.isValidString(ls_idMunicipio)
										&& StringUtils.isValidString(ls_idMunicipioCargado)
										&& ls_idMunicipio.equalsIgnoreCase(ls_idMunicipioCargado);

								li_identificador++;
							}
						}
					}
					else
						lcpn_resultadosNotificacion = new ArrayList<PersonaNotificacion>();

					if(lb_existe)
					{
						Object[] loa_object;

						loa_object     = new String[1];

						loa_object[0] = CalidadSolicitanteCommon.TERCERO_DETERMINADO;

						throw new B2BException(ErrorKeys.ERROR_INTERESADO_INTERVINIENTE_YA_EXISTE, loa_object);
					}

					{
						PersonaNotificacion lpn_personaNotificacion;

						lpn_personaNotificacion = new PersonaNotificacion();

						lpn_personaNotificacion.setIdentificador(li_identificador);
						lpn_personaNotificacion.setDestinatario(apn_resultadoNotificacion.getDestinatario());
						lpn_personaNotificacion.setDireccion(apn_resultadoNotificacion.getDireccion());
						lpn_personaNotificacion.setIdDepartamento(apn_resultadoNotificacion.getIdDepartamento());
						lpn_personaNotificacion.setIdMunicipio(apn_resultadoNotificacion.getIdMunicipio());
						lpn_personaNotificacion.setCalidadEnQueActua(CalidadSolicitanteCommon.TERCERO_DETERMINADO);

						lcpn_resultadosNotificacion.add(lpn_personaNotificacion);
					}

					setResultadosNotificacion(lcpn_resultadosNotificacion);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("agregarTerceroResultadoNotificacion", lb2be_e);

				addMessage(lb2be_e);
				actualizarComponente(is_messages);
			}
		}
	}

	/**
	 * Método encargado de consultar las personas asociadas a una solicitud con proceso 47.
	 */
	public void buscarPersonasAsociadasSegundaInstancia()
	{
		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = getTurnoHistoria();

			if(lth_turnoHistoria != null)
				setRegistroDatosConsultados(
				    isir_segundaInstanciaRemote.buscarPersonasAsociadasSegundaInstancia(
				        lth_turnoHistoria.getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarPersonasAsociadasSegundaInstancia", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(is_messages);
		}
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada
	 * en la consulta por documento.
	 *
	 * @param ap_persona Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 * @param as_tipoRecepcion Argumento de tipo <code>String</code> que determina si se debe cargar los medios a notificar y comunicar de copias.
	 */
	public void cargarDatosPersonales(Persona ap_persona, String as_tipoRecepcion)
	{
		if(ap_persona != null)
		{
			String ls_idPersonaSeleccion;

			ls_idPersonaSeleccion = ap_persona.getIdPersona();

			Registro lr_datosCalculados;
			lr_datosCalculados = getRegistroDatosConsultados();

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
							String ls_numeroDocumento;
							String ls_tipoDocumento;
							String ls_numeroDocumentoConsultado;
							String ls_tipoDocumentoConsultado;

							ls_numeroDocumento               = ap_persona.getNumeroDocumento();
							ls_tipoDocumento                 = ap_persona.getIdDocumentoTipo();
							ls_numeroDocumentoConsultado     = lp_iterador.getNumeroDocumento();
							ls_tipoDocumentoConsultado       = lp_iterador.getIdDocumentoTipo();

							if(
							    StringUtils.isValidString(ls_numeroDocumento)
								    && StringUtils.isValidString(ls_tipoDocumento)
								    && StringUtils.isValidString(ls_numeroDocumentoConsultado)
								    && StringUtils.isValidString(ls_tipoDocumentoConsultado)
								    && ls_numeroDocumento.equalsIgnoreCase(ls_numeroDocumentoConsultado)
								    && ls_tipoDocumento.equalsIgnoreCase(ls_tipoDocumentoConsultado)
							)
							{
								lp_iterador.setSeleccionado(true);
								setPersona(lp_iterador);
							}
							else
								lp_iterador.setSeleccionado(false);
						}
					}

					if(!StringUtils.isValidString(ls_idPersonaSeleccion))
						setPersona(null);
				}
			}

			setAccionBtnEditPerNit(false);
			setAccionBtnEditPerNormal(false);
		}
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada
	 * en la consulta por documento.
	 *
	 * @param ap_persona Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 */
	public void cargarDatosPersonalesRecursos(Persona ap_persona)
	{
		try
		{
			if(ap_persona != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = ap_persona.getSolicitud();

				if(ls_solicitud != null)
				{
					setMostrarDatosBasicos(false);
					setConsultaSinRegistro(false);
					setPersonaConsultada(true);
					setHabilitadoPorConsulta(true);

					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						if(lbd_beanDireccion != null)
							lbd_beanDireccion.setHabilitadoPorConsulta(true);
					}

					cargarDatosPersonales(ap_persona, TipoRecepcionCommon.ACTUACIONES_ADMINISTRATIVAS);

					setMostrarDatosPersona(true);
					setMostrarPanelConsulta(false);

					{
						String ls_idAutorizacionNotificacion;
						String ls_idAutorizacionComunicacion;

						ls_idAutorizacionNotificacion     = StringUtils.getStringNotNull(
							    ls_solicitud.getIdAutorizacionNotificacion()
							);
						ls_idAutorizacionComunicacion     = StringUtils.getStringNotNull(
							    ls_solicitud.getIdAutorizacionComunicacion()
							);

						cambioDeMedioNotInteresado(ls_idAutorizacionNotificacion);
						cambioDeMedioComInteresado(ls_idAutorizacionComunicacion);

						{
							Registro lr_registro;

							lr_registro = new Registro();

							lr_registro.setSolicitud(ls_solicitud);
							lr_registro.setPersona(ap_persona);

							lr_registro = iaar_actuacionesAdministrativasRemote.buscarDatosPorPersona(
								    lr_registro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							if(lr_registro != null)
							{
								BeanDireccion lbd_beanDireccion;

								lbd_beanDireccion = getBeanDireccion();

								if(lbd_beanDireccion != null)
								{
									{
										PersonaDireccion lpd_personaDireccion;
										boolean          lb_existe;

										lpd_personaDireccion     = lr_registro.getDireccionResidencia();
										lb_existe                = lpd_personaDireccion != null;

										lbd_beanDireccion.setDireccionResidencia(
										    lb_existe ? lpd_personaDireccion : null
										);
										lbd_beanDireccion.setDeshabilitarResidencia(lb_existe);
										setDeshabilitarResidencia(lb_existe);
									}

									{
										PersonaDireccion lpd_personaDireccion;
										boolean          lb_existe;

										lpd_personaDireccion     = lr_registro.getDireccionCorrespondencia();
										lb_existe                = lpd_personaDireccion != null;

										lbd_beanDireccion.setDireccionCorrespondencia(
										    lb_existe ? lpd_personaDireccion : null
										);
										lbd_beanDireccion.setDeshabilitarCorrespondencia(lb_existe);
										setDeshabilitarCorrespondencia(lb_existe);
									}

									{
										PersonaTelefono lpd_personaTelefono;
										boolean         lb_existe;

										lpd_personaTelefono     = lr_registro.getTelefonoFijo();
										lb_existe               = lpd_personaTelefono != null;

										setTelefonoFijo(lpd_personaTelefono);
										findIndicativoDepartamento();

										setDeshabilitarTelFijo(lb_existe);
									}

									{
										PersonaTelefono lpd_personaTelefono;
										boolean         lb_existe;

										lpd_personaTelefono     = lr_registro.getTelefonoMovil();
										lb_existe               = lpd_personaTelefono != null;

										setTelefonoMovil(lpd_personaTelefono);
										setDeshabilitarTelMovil(lb_existe);
									}

									{
										PersonaCorreoElectronico lpd_personaCorreo;
										boolean                  lb_existe;

										lpd_personaCorreo     = lr_registro.getPersonaCorreoElectronico();
										lb_existe             = lpd_personaCorreo != null;

										setCorreoElectronico(lpd_personaCorreo);
										setDeshabilitarCorreo(lb_existe);
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
			clh_LOGGER.error("cargarDatosRechazaRecursos", lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosSegundaInstancia()
	    throws B2BException
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				long                   ll_idEtapa;
				String                 ls_idTurno;
				TagPlantillaResolucion ltpr_segundaInstancia;

				ll_idEtapa                = getEtapa();
				ls_idTurno                = getIdTurno();
				ltpr_segundaInstancia     = new TagPlantillaResolucion();

				ltpr_segundaInstancia.setIdTurno(ls_idTurno);
				ltpr_segundaInstancia.setIdEtapa(ll_idEtapa);
				ltpr_segundaInstancia.setIdTurnoHistoria(getIdTurnoHistoria());
				ltpr_segundaInstancia.setIdMotivo(NumericUtils.getLong(getMotivoTramite()));

				if(ll_idEtapa == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
					ltpr_segundaInstancia = isir_segundaInstanciaRemote.cargarDatosSegundaInstancia(
						    ltpr_segundaInstancia, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

				if(ltpr_segundaInstancia != null)
				{
					byte[] lba_archivo;

					lba_archivo = ltpr_segundaInstancia.getArchivo();

					ls_parametros.setOficiosTexto(ltpr_segundaInstancia.getOficiosTexto());
					setSolicitud(ltpr_segundaInstancia.getSolicitud());

					setTipoArchivo(ltpr_segundaInstancia.getTipoArchivo());
					setIdTurno(ls_idTurno);
					setArchivo(lba_archivo);
					setPersona(ltpr_segundaInstancia.getPersona());

					buscarPersonasAsociadasSegundaInstancia();
					setPersonaNotificacion(ltpr_segundaInstancia.getResultadosNotificacion());
					agregarTerceroDeterminado();

					setImagenDocumento(
					    generarArchivosDescarga(
					        lba_archivo, TipoContenidoCommon.PDF,
					        ltpr_segundaInstancia.getTipoArchivo() + ExtensionCommon.PDF_PUNTO
					    )
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Cargar direccion correspondencia.
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
											lpd_iterador.setTipoDireccion(EstadoCommon.C);
											lbd_beanDireccion.setDireccionCorrespondencia(lpd_iterador);

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
			lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
			setDeshabilitarCorrespondencia(false);
		}
	}

	/**
	 * Cargar medios not com inter.
	 *
	 * @param ab_personaJuridica correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarMediosNotComInter(boolean ab_personaJuridica)
	{
		try
		{
			TipoRecepcion ltr_tipoRecepcion;
			ltr_tipoRecepcion = new TipoRecepcion();

			ltr_tipoRecepcion.setHabilitadoComunicacion(EstadoCommon.S);
			ltr_tipoRecepcion.setHabilitadoNotificacion(EstadoCommon.S);

			setMediosComunicarInter(
			    irr_referenceRemote.findByHabilitado(
			        ltr_tipoRecepcion, false, ab_personaJuridica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);

			setMediosNotificarInter(
			    irr_referenceRemote.findByHabilitado(
			        ltr_tipoRecepcion, true, ab_personaJuridica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public void cargarMunicipios(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			try
			{
				Collection<PersonaNotificacion> lcpn_personaNotificacion;

				lcpn_personaNotificacion = getPersonaNotificacion();

				if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
				{
					Iterator<PersonaNotificacion> lpn_iterator;
					boolean                       lb_encontrado;

					lpn_iterator      = lcpn_personaNotificacion.iterator();
					lb_encontrado     = false;

					while(lpn_iterator.hasNext() && !lb_encontrado)
					{
						PersonaNotificacion lpn_iterador;

						lpn_iterador = lpn_iterator.next();

						if(lpn_iterador != null)
						{
							int li_identificadorParametro;
							int li_identificadorIterado;

							li_identificadorParametro     = apn_parametro.getIdentificador();
							li_identificadorIterado       = lpn_iterador.getIdentificador();

							lb_encontrado = li_identificadorParametro == li_identificadorIterado;

							if(lb_encontrado)
							{
								String ls_idDepartamento;

								ls_idDepartamento = lpn_iterador.getIdDepartamento();

								if(StringUtils.isValidString(ls_idDepartamento))
								{
									Municipio lm_parametros;

									lm_parametros = new Municipio();

									lm_parametros.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
									lm_parametros.setIdDepartamento(ls_idDepartamento);

									lpn_iterador.setMunicipios(irr_referenceRemote.findMunicipios(lm_parametros));
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				actualizarComponente(is_messages);
			}
		}
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setFecha(false);
		setProyectarResolucion(false);
		setMostrarResolucion(false);
		setDocumentosEnviados(false);
		setConsideracion(false);
		setResuelve(false);
		setPertinencia(false);
		setPertinencia2(false);
		setRegistroDatosConsultados(null);
		setArgumentosRecurrente(false);
		setPruebas(false);
		setConsideracionPrimeraInstancia(false);
		setConsideracionSajr(false);
		setAccionBtnEditPerNit(false);
		setAccionBtnEditPerNormal(false);
		setAprobacionVinculados(false);
		setArchivo(null);
		setArgumentosRecurrente(false);
		setParametros(null);
	}

	/**
	 * Consultar persona interesado.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPersonaInteresado()
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = getTurnoHistoria();

		if(lth_turnoHistoria != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Suspension ls_parametros;

				ls_parametros = new Suspension();

				{
					DatosDelInteresado lddi_data;

					lddi_data = igr_grabacionRemote.consultarPersonaInteresado(ls_idSolicitud, true);

					if(lddi_data != null)
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setHabilitadoPorConsulta(true);
						lbd_beanDireccion.setForm(CS_ID_FORM);
						lbd_beanDireccion.setDireccionCorrespondencia(lddi_data.getDireccionCorrespondencia());
						lbd_beanDireccion.setDireccionResidencia(lddi_data.getDireccionResidencia());
					}
					else
						lddi_data = new DatosDelInteresado();

					ls_parametros.setDatosDelInteresado(lddi_data);
				}

				ls_parametros.setSolicitud2(
				    irr_registroRemote.findSolicitudById(new Solicitud(ls_idSolicitud), getUserId())
				);

				setParametros(ls_parametros);
			}
		}
	}

	/**
	 * Metodo encargado de convertir días a letras.
	 */
	public void convertirDiasLetrasOficiosTexto()
	{
		Suspension ls_parametros;

		ls_parametros = getParametros();

		if(ls_parametros != null)
		{
			OficiosTexto lot_oficiosTexto;

			lot_oficiosTexto = ls_parametros.getOficiosTexto();

			if(lot_oficiosTexto != null)
			{
				Long ll_diasSuspension;

				ll_diasSuspension = lot_oficiosTexto.getDiasSuspension();

				if(NumericUtils.isValidLong(ll_diasSuspension))
				{
					String ls_diasLetras;

					ls_diasLetras = NumericUtils.convertirLetras(NumericUtils.getInt(ll_diasSuspension), false);

					if(StringUtils.isValidString(ls_diasLetras))
						lot_oficiosTexto.setDiasLetras(ls_diasLetras);
				}
			}
		}
	}

	/**
	 * Metodo encargado de descargar Zip de actuaciones administrativas.
	 */
	public void descargarZipSegundaInstancia()
	{
		byte[] lba_file;

		lba_file = getFile();

		if(lba_file != null)
		{
			setZip(
			    generarArchivosDescarga(
			        lba_file, TipoContenidoCommon.ZIP,
			        IdentificadoresCommon.SEGUNDA_INSTANCIA + ExtensionCommon.ZIP_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM + ":opBotones");
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
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_FORM + ":idBotonConsultaSGD:");
			actualizarComponente(CS_ID_FORM + ":idBotonEnviarCoordinador:");
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de id turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void documentosEnviadosOWCC(String as_idTurnoHistoria)
	    throws B2BException
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
			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de eliminar un tercero determinado para actuaciones administrativas.
	 */
	public void eliminarResultadoNotificacion(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			Collection<PersonaNotificacion> lcpn_personaNotificacion;

			lcpn_personaNotificacion = getResultadosNotificacion();

			if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
			{
				Collection<PersonaNotificacion> lcpn_nueva;
				int                             li_identificador;

				lcpn_nueva           = new ArrayList<PersonaNotificacion>();
				li_identificador     = 1;

				for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = apn_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcpn_nueva.add(lpn_iterador);
						}
					}
				}

				setResultadosNotificacion(lcpn_nueva);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar un tercero determinado para actuaciones administrativas.
	 */
	public void eliminarTerceroDeterminado(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			Collection<PersonaNotificacion> lcpn_personaNotificacion;

			lcpn_personaNotificacion = getPersonaNotificacion();

			if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
			{
				Collection<PersonaNotificacion> lcpn_nueva;
				int                             li_identificador;

				lcpn_nueva           = new ArrayList<PersonaNotificacion>();
				li_identificador     = 1;

				for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = apn_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcpn_nueva.add(lpn_iterador);
						}
					}
				}

				setPersonaNotificacion(lcpn_nueva);
			}
		}
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 *
	 * @return el valor de string
	 */
	public String enviarASiguienteEtapa()
	{
		String ls_return;

		ls_return = null;

		try
		{
			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setObservaciones(getObservaciones());
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setIdEtapa(EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA);

				isir_segundaInstanciaRemote.enviarAprobador(
				    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			{
				BeanTurnos lbt_bean;

				lbt_bean = (BeanTurnos)obtenerInstanciaBean(BeanTurnos.class, BeanNameCommon.BEAN_TURNOS);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarData();

				ls_return = NavegacionCommon.TURNOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_FORM);
		}

		return ls_return;
	}

	/**
	 * Esconder panels.
	 */
	public void esconderPanels()
	{
		setMostrarDatosBasicos(true);
		setMostrarDatosBasicosInterviniente(true);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitante()
	{
		Collection<CalidadSolicitante> lcidt_datos;

		try
		{
			lcidt_datos = irr_referenceRemote.findCalidadSolicitante(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Generar descarga.
	 */
	public void generarDescarga()
	{
		setImagen(
		    generarArchivosDescarga(
		        getArchivo(), TipoContenidoCommon.PDF,
		        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
		    )
		);
	}

	/**
	 * Metodo encargado de generar los documentos de segunda Instancia.
	 *
	 */
	public void generarDocumentosSegundaInstancia()
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion lsi_segundaInstancia;
				long                   ll_idMotivo;
				String                 ls_tipoArchivo;

				lsi_segundaInstancia     = new TagPlantillaResolucion();
				ll_idMotivo              = NumericUtils.getLong(getMotivoTramite());
				ls_tipoArchivo           = StringUtils.getStringNotNull(getTipoArchivo());

				lsi_segundaInstancia.setOficiosTexto(convertirOficiosTexto(ls_parametros.getOficiosTexto()));
				lsi_segundaInstancia.setIdTurno(getIdTurno());
				lsi_segundaInstancia.setIdTurnoHistoria(getIdTurnoHistoria());
				lsi_segundaInstancia.setTiposDocumentales(getTiposDocumentales());
				lsi_segundaInstancia.setResultadosNotificacion(getResultadosNotificacion());
				lsi_segundaInstancia.setTipoArchivo(ls_tipoArchivo);
				lsi_segundaInstancia.setIdEtapa(getEtapa());

				lsi_segundaInstancia = isir_segundaInstanciaRemote.generarDocumentosSegundaInstancia(
					    lsi_segundaInstancia, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ll_idMotivo
					);

				if(lsi_segundaInstancia != null)
				{
					PrimeFaces.current().executeScript("PF('wvPoll').start();");
					setFile(lsi_segundaInstancia.getArchivo());

					setMostrarDescargarZip(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosSegundaInstancia", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresar()
	{
		String ls_return;

		ls_return     = null;
		ls_return     = NavegacionCommon.TURNOS;

		return ls_return;
	}

	/**
	 * Salvar interesado.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarInteresado()
	    throws B2BException
	{
		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				BeanDireccion lbd_beanDireccion;
				Registro      lr_registro;

				lbd_beanDireccion     = getBeanDireccion();
				lr_registro           = new Registro();

				lr_registro.setPersona(lp_persona);
				lr_registro.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lr_registro.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lr_registro.setSolicitud(getSolicitud());

				boolean lb_insertarPersona;
				boolean lb_editarPorNit;
				boolean lb_editarPorNormal;

				lb_insertarPersona     = false;
				lb_editarPorNit        = isAccionBtnEditPerNit();
				lb_editarPorNormal     = isAccionBtnEditPerNormal();

				if(lb_editarPorNit || lb_editarPorNormal)
				{
					if(lb_editarPorNormal)
					{
						String ls_primerNombreOri;
						String ls_segundoNombreOri;
						String ls_primerApellidoOri;
						String ls_segundoApellidoOri;
						String ls_primerNombreMod;
						String ls_segundoNombreMod;
						String ls_primerApellidoMod;
						String ls_segundoApellidoMod;

						ls_primerNombreOri        = StringUtils.getStringNotNull(getPrimerNombreTemp());
						ls_segundoNombreOri       = StringUtils.getStringNotNull(getSegundoNombreTemp());
						ls_primerApellidoOri      = StringUtils.getStringNotNull(getPrimerApellidoTemp());
						ls_segundoApellidoOri     = StringUtils.getStringNotNull(getSegundoApellidoTemp());
						ls_primerNombreMod        = StringUtils.getStringNotNull(lp_persona.getPrimerNombre());
						ls_segundoNombreMod       = StringUtils.getStringNotNull(lp_persona.getSegundoNombre());
						ls_primerApellidoMod      = StringUtils.getStringNotNull(lp_persona.getPrimerApellido());
						ls_segundoApellidoMod     = StringUtils.getStringNotNull(lp_persona.getSegundoApellido());

						if(
						    !ls_primerNombreOri.equalsIgnoreCase(ls_primerNombreMod)
							    || !ls_segundoNombreOri.equalsIgnoreCase(ls_segundoNombreMod)
							    || !ls_primerApellidoOri.equalsIgnoreCase(ls_primerApellidoMod)
							    || !ls_segundoApellidoOri.equalsIgnoreCase(ls_segundoApellidoMod)
						)
							lb_insertarPersona = true;
					}
					else if(lb_editarPorNit)
					{
						String ls_razonSocialOri;
						String ls_razonSocialMod;

						ls_razonSocialOri     = StringUtils.getStringNotNull(getRazonSocialTemp());
						ls_razonSocialMod     = StringUtils.getStringNotNull(lp_persona.getRazonSocial());

						if(!ls_razonSocialOri.equalsIgnoreCase(ls_razonSocialMod))
							lb_insertarPersona = true;
					}
				}

				{
					Solicitud ls_solicitud;

					ls_solicitud = lr_registro.getSolicitud();

					if(ls_solicitud != null)
					{
						Persona lp_personaTemp;

						lp_personaTemp = lr_registro.getPersona();

						ls_solicitud.setIdPersona((lp_personaTemp != null) ? lp_personaTemp.getIdPersona() : null);
					}

					lr_registro.setPersonaCargada(lb_insertarPersona);
					lr_registro.setIpCreacion(getRemoteIpAddress());
					lr_registro.setIpModificacion(getRemoteIpAddress());
					lr_registro.setIdTurno(getIdTurno());
				}

				lr_registro = irr_registroRemote.salvarInteresado(lr_registro, getUserId());

				if(lr_registro != null)
				{
					setPersona(lr_registro.getPersona());
					lbd_beanDireccion.setDireccionResidencia(lr_registro.getDireccionResidencia());
					lbd_beanDireccion.setDireccionCorrespondencia(lr_registro.getDireccionCorrespondencia());

					Solicitud ls_solicitud;
					ls_solicitud = getSolicitud();

					if(ls_solicitud != null)
						setSolicitud(lr_registro.getSolicitud());
				}

				if(lb_insertarPersona)
				{
					setAccionBtnEditPerNit(false);
					setAccionBtnEditPerNormal(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			throw new B2BException(lb2be_e.getMessage());
		}
	}

	/**
	 * Validar datos basicos.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosBasicos(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			Persona lp_persona;

			lp_persona = addi_data.getPersona();

			if(lp_persona != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lp_persona.getIdTipoPersona();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMTipoPersona", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);
				}

				{
					ls_datoValidar     = lp_persona.getIdDocumentoTipo();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMTipoDoc", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					ls_datoValidar     = lp_persona.getNumeroDocumento();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlDocumento", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMNacionalidad", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerNombre();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlPNombre", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerApellido();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlPApellido", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdNaturalGenero();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMGenero", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
				}
			}
		}
	}

	/**
	 * Validar datos contacto.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Suspension
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosContacto(Suspension as_parametros, FacesContext afc_context)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			DatosDelInteresado lddi_data;

			lddi_data = as_parametros.getDatosDelInteresado();

			if(lddi_data != null)
			{
				boolean                  lb_focus;
				PersonaCorreoElectronico lpce_correo;
				PersonaTelefono          lpt_fijo;
				PersonaTelefono          lpt_movil;
				String                   ls_datoValidar;

				lb_focus           = true;
				lpce_correo        = lddi_data.getCorreoElectronico();
				lpt_fijo           = lddi_data.getTelefonoFijo();
				lpt_movil          = lddi_data.getTelefonoMovil();
				ls_datoValidar     = null;

				if(lpt_fijo != null)
				{
					String ls_telefono;

					ls_telefono = lpt_fijo.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataFijo())
					{
						{
							ls_datoValidar     = lpt_fijo.getIdPais();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMPaisTel", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_FIJO);
						}

						{
							ls_datoValidar     = lpt_fijo.getIdDepartamento();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMDepTel", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
						}

						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItTelefonoFijo", afc_context, ls_telefono, lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_INVALIDO);
						}
					}
				}

				if(lpt_movil != null)
				{
					String ls_telefono;

					ls_telefono = lpt_movil.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataMovil())
					{
						{
							ls_datoValidar     = lpt_movil.getIdPais();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMPaisTelMov", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);
						}

						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItTelefonoMovil", afc_context, ls_telefono, lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_MOVIL);
						}
					}
				}

				if(lpce_correo != null)
				{
					ls_datoValidar = lpce_correo.getCorreoElectronico();

					if(StringUtils.isValidString(ls_datoValidar) || lddi_data.isDataCorreo())
					{
						lb_focus = validateStyles(CS_ID_FORM + ":idItEmail", afc_context, ls_datoValidar, lb_focus);

						if(!StringUtils.isValidString(ls_datoValidar))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

						if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_datoValidar))
						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItEmail", afc_context, IdentificadoresCommon.DATO_INVALIDO,
								    lb_focus
								);

							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
						}
					}
				}
			}
		}
	}

	/**
	 * Validar dir correspondencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirCorrespondencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirCorrespondencia;

			lpd_dirCorrespondencia = addi_data.getDireccionCorrespondencia();

			if(lpd_dirCorrespondencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEjeC", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjePrincipalC", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje1C", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjeSecundarioC", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMPaisDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdDepartamento();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMDepDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdMunicipio();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMMunDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/**
	 * Validar dir residencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirResidencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirResidencia;

			lpd_dirResidencia = addi_data.getDireccionResidencia();

			if(lpd_dirResidencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjePrincipal", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje1", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjeSecundario", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMPaisDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdDepartamento();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMDepDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdMunicipio();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMMunDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/***
	 * Método para la validacion de paneles
	 * @param al_motivo con el motivo del proceso
	 */
	public void validarPanelesSegunMotivo(long al_motivo)
	{
		if(al_motivo == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS)
		{
			setPertinencia(true);
			setFecha(true);
			setResuelve(true);
			setValorBoton(getMessages().getString(MessagesKeys.LABEL_ENVIAR_SAJR));
		}
		else if(
		    (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
			    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION)
		)
		{
			setArgumentosRecurrente(true);
			setPruebas(true);
			setConsideracionPrimeraInstancia(true);
			setConsideracionSajr(true);
			setResuelve(true);
			setValorBoton(getMessages().getString(MessagesKeys.LABEL_ENVIAR_COORDINADOR_JURIDICO));
		}
		else if(
		    (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_QUEJA)
			    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA)
			    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA)
			    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA)
			    || (al_motivo == MotivoTramiteCommon.ETAPA_430_RESOLUCION_DE_ACLARATORIA)
		)
		{
			setArgumentosRecurrente(true);
			setPruebas(true);
			setConsideracionSajr(true);
			setResuelve(true);
			setValorBoton(getMessages().getString(MessagesKeys.LABEL_ENVIAR_COORDINADOR_JURIDICO));
		}
		else if(al_motivo == MotivoTramiteCommon.ETAPA_430_AUTO_INHIBITORIO)
		{
			setEsObservaciones(true);
			setResuelve(true);
			setValorBoton(getMessages().getString(MessagesKeys.LABEL_ENVIAR_COORDINADOR_JURIDICO));
		}
		else if(al_motivo == MotivoTramiteCommon.ETAPA_430_ANTIGUO_SISTEMA)
		{
			setValorBoton(getMessages().getString(MessagesKeys.LABEL_ENVIAR_ANTIGUO_SISTEMA));
			setDocumentosEnviados(true);
			setMostrarDescargarZip(true);
		}
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @param ab_suspension de ab suspension
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucion()
	{
		try
		{
			Suspension ls_data;

			ls_data = getParametros();

			if(ls_data != null)
			{
				BeanDireccion          lbd_beanDireccion;
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				lbd_beanDireccion                  = getBeanDireccion();
				laa_actuacionesAdministrativas     = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(ls_data.getOficiosTexto()));
				laa_actuacionesAdministrativas.setTraslado(true);
				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());

				{
					DatosDelInteresado lddi_datosDelInteresado;
					FacesContext       lfc_context;

					lddi_datosDelInteresado     = ls_data.getDatosDelInteresado();
					lfc_context                 = FacesContext.getCurrentInstance();

					validarDatosBasicos(lddi_datosDelInteresado, lfc_context);

					if(isEditarDireccionResidencia())
						lbd_beanDireccion.validarCamposDireccionResidencia();

					if(isEditarDireccionCorrespondencia())
						lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);

					if(isEditarDatosContacto())
						validarDatosContacto(ls_data, lfc_context);

					lddi_datosDelInteresado.setDireccionCorrespondencia(
					    lbd_beanDireccion.getDireccionCorrespondencia()
					);
					lddi_datosDelInteresado.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
					lddi_datosDelInteresado.setEditarDatosBasicos(isEditarDatosBasicos());
					lddi_datosDelInteresado.setEditarDatosContacto(isEditarDatosContacto());
					lddi_datosDelInteresado.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
					lddi_datosDelInteresado.setEditarDireccionResidencia(isEditarDireccionResidencia());

					ls_data.setDatosDelInteresado(lddi_datosDelInteresado);
				}

				ls_data.setTurnoHistoria(getTurnoHistoria());
				laa_actuacionesAdministrativas.setSuspension(ls_data);

				laa_actuacionesAdministrativas = isir_segundaInstanciaRemote.visualizarCambiosResolucion(
					    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

				if(laa_actuacionesAdministrativas != null)
				{
					setArchivo(laa_actuacionesAdministrativas.getArchivo());

					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(true);
					setMostrarResolucion(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(is_messages);

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
		}

		{
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
		}

		return NavegacionCommon.SEGUNDA_INSTANCIA;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Metodo encargado de convertir los textos capturados por pantalla.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los criterios a convertir.
	 * @return Objeto de tipo <code>OficiosTexto</code> que contiene los criterios convertidos.
	 * @throws B2BException Se genera cuando se presenta una excepcion.
	 */
	protected OficiosTexto convertirOficiosTexto(OficiosTexto aot_oficiosTexto)
	    throws B2BException
	{
		OficiosTexto lot_oficiosTexto;

		lot_oficiosTexto = null;

		try
		{
			if(aot_oficiosTexto != null)
			{
				lot_oficiosTexto = new OficiosTexto();

				lot_oficiosTexto.setEncabezado(convertirTextoParaRtf(aot_oficiosTexto.getEncabezado()));
				lot_oficiosTexto.setAntecedentes(convertirTextoParaRtf(aot_oficiosTexto.getAntecedentes()));
				lot_oficiosTexto.setConsideracion(convertirTextoParaRtf(aot_oficiosTexto.getConsideracion()));
				lot_oficiosTexto.setConsideracionSajr(convertirTextoParaRtf(aot_oficiosTexto.getConsideracionSajr()));
				lot_oficiosTexto.setRazon1(convertirTextoParaRtf(aot_oficiosTexto.getRazon1()));
				lot_oficiosTexto.setRazon2(convertirTextoParaRtf(aot_oficiosTexto.getRazon2()));
				lot_oficiosTexto.setAnalisisProbatorio(convertirTextoParaRtf(aot_oficiosTexto.getAnalisisProbatorio()));
				lot_oficiosTexto.setPertinencia(convertirTextoParaRtf(aot_oficiosTexto.getPertinencia()));
				lot_oficiosTexto.setIntervencionInteresados(
				    convertirTextoParaRtf(aot_oficiosTexto.getIntervencionInteresados())
				);
				lot_oficiosTexto.setPruebasRecaudadas(convertirTextoParaRtf(aot_oficiosTexto.getPruebasRecaudadas()));
				lot_oficiosTexto.setResuelve(convertirTextoParaRtf(aot_oficiosTexto.getResuelve()));
				lot_oficiosTexto.setArgumentosRecurrente(
				    convertirTextoParaRtf(aot_oficiosTexto.getArgumentosRecurrente())
				);
				lot_oficiosTexto.setDiasSuspension(aot_oficiosTexto.getDiasSuspension());
				lot_oficiosTexto.setDiasLetras(aot_oficiosTexto.getDiasLetras());
				lot_oficiosTexto.setIdTurnoHistoria(aot_oficiosTexto.getIdTurnoHistoria());
				lot_oficiosTexto.setFechaInicioTraslado(aot_oficiosTexto.getFechaInicioTraslado());
				lot_oficiosTexto.setFechaFinTraslado(aot_oficiosTexto.getFechaFinTraslado());
				lot_oficiosTexto.setReferencia(convertirTextoParaRtf(aot_oficiosTexto.getReferencia()));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("convertirOficiosTexto", lb2be_e);

			throw lb2be_e;
		}

		return lot_oficiosTexto;
	}
}
