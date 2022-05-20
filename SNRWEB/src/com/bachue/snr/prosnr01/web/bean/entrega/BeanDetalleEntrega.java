package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion.DigitalizacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.notificaciones.NotificacionesRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reanotacion.ReanotacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.entrega.DocumentoAdicional;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanSuspension;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.EntregaUI;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr02.common.constants.ScriptsCommon;

import org.apache.commons.io.IOUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanDetalleEntrega.
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanDetalleEntrega")
public class BeanDetalleEntrega extends BeanSuspension implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5181494024433140137L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fDetalleEntrega:idGrowl";

	/** Constante isc_formId. */
	private static final String isc_formId = ":fDetalleEntrega:";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleEntrega.class);

	/** Propiedad ier entrega remote. */
	@EJB
	protected EntregaRemote ier_entregaRemote;

	/** Propiedad inr notificaciones remote. */
	@EJB
	protected NotificacionesRemote inr_notificacionesRemote;

	/** Propiedad irr reference remote. */
	@EJB
	protected ReferenceRemote irr_referenceRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ics correos intervinientes. */
	private Collection<String> ics_correosIntervinientes;

	/** Propiedad icsc datos documentos cargados. */
	private Collection<StreamedContent> icsc_datosDocumentosCargados;

	/** Propiedad icsc datos documentos nota devolutiva. */
	private Collection<StreamedContent> icsc_datosDocumentosNotaDevolutiva;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteTurno> ictc_datosTramiteCantidad = null;

	/** Propiedad ictr medios comunicar. */
	private Collection<TipoRecepcion> ictr_mediosComunicar;

	/** Propiedad ictr medios notificar. */
	private Collection<TipoRecepcion> ictr_mediosNotificar;

	/** Propiedad idr digitalizacion remote. */
	@EJB
	private DigitalizacionRemote idr_digitalizacionRemote;

	/** Propiedad ieui entrega. */
	private EntregaUI ieui_entrega;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_actosFechas;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_turnosFechas;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoriaEntrega;

	/** Propiedad imsda documentos adicionales. */
	private Map<String, DocumentoAdicional> imsda_documentosAdicionales;

	/** Propiedad in info notificacion. */
	private Notificacion in_infoNotificacion;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona tercero. */
	private Persona ip_personaTercero;

	/** Propiedad ipce correo electronico persona. */
	private PersonaCorreoElectronico ipce_correoElectronicoPersona;

	/** Propiedad ipd direccion correspondencia. */
	private PersonaDireccion ipd_direccionCorrespondencia;

	/** Propiedad ipd direccion residencia. */
	private PersonaDireccion ipd_direccionResidencia;

	/** Propiedad ipe persona entrega cargada. */
	private PersonaEntrega ipe_personaEntregaCargada;

	/** Propiedad ipt telefono fijo. */
	private PersonaTelefono ipt_telefonoFijo;

	/** Propiedad ipt telefono movil. */
	private PersonaTelefono ipt_telefonoMovil;

	/** Propiedad irr reanotacion remote. */
	@EJB
	private ReanotacionRemote irr_reanotacionRemote;

	/** Propiedad ir registro datos consultados. */
	private Registro ir_registroDatosConsultados;

	/** Propiedad ir registro tercero. */
	private Registro ir_registroTercero;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad isc documento cedula cargado. */
	private StreamedContent isc_documentoCedulaCargado;

	/** Propiedad isc documento entrega descarga. */
	private StreamedContent isc_documentoEntregaDescarga;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is correo electronico ingresado. */
	private String is_correoElectronicoIngresado;

	/** Propiedad is decision entrega. */
	private String is_decisionEntrega;

	/** Propiedad is documento interesado. */
	private String is_documentoInteresado;

	/** Propiedad is entrega correo. */
	private String is_entregaCorreo;

	/** Propiedad is id correo seleccion. */
	private String is_idCorreoSeleccion;

	/** Propiedad is id dir corr seleccion. */
	private String is_idDirCorrSeleccion;

	/** Propiedad is id dir res seleccion. */
	private String is_idDirResSeleccion;

	/** Propiedad is id firma. */
	private String is_idFirma;

	/** Propiedad is id persona seleccion. */
	private String is_idPersonaSeleccion;

	/** Propiedad is id tel fijo seleccion. */
	private String is_idTelFijoSeleccion;

	/** Propiedad is id tel mov seleccion. */
	private String is_idTelMovSeleccion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is interpone recurso. */
	private String is_interponeRecurso;

	/** Propiedad is interviene en entrega. */
	private String is_intervieneEnEntrega;

	/** Propiedad is medio comunicar interesado. */
	private String is_medioComunicarInteresado;

	/** Propiedad is medio notificar interesado. */
	private String is_medioNotificarInteresado;

	/** Propiedad is misma direccion correspondencia. */
	private String is_mismaDireccionCorrespondencia;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is observaciones documentos cargados. */
	private String is_observacionesDocumentosCargados;

	/** Propiedad is renuncia terminos. */
	private String is_renunciaTerminos;

	/** Propiedad is tipo doc interesado. */
	private String is_tipoDocInteresado;

	/** Propiedad is tipo documental. */
	private String is_tipoDocumental;

	/** Propiedad is tipo entrega. */
	private String is_tipoEntrega;

	/** Propiedad iuf file notificacion. */
	private UploadedFile iuf_fileNotificacion;

	/** Propiedad iuf file recursos. */
	private UploadedFile iuf_fileRecursos;

	/** Propiedad iuf file terminos. */
	private UploadedFile iuf_fileTerminos;

	/** Propiedad iba bytes carta cargado. */
	private byte[] iba_documentoEntrega;

	/** Propiedad ib bandejaentrada. */
	private boolean ib_bandejaentrada;

	/** Propiedad ib carta autorizacion cargada. */
	private boolean ib_cartaAutorizacionCargada;

	/** Propiedad ib cedula ciudadania cargada. */
	private boolean ib_cedulaCiudadaniaCargada;

	/** Propiedad ib deshabilitar campos por nit. */
	private boolean ib_deshabilitarCamposPorNit;

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

	/** Propiedad ib deshabilitar tipo documento. */
	private boolean ib_deshabilitarTipoDocumento;

	/** Propiedad ib deshabilitar tipo persona. */
	private boolean ib_deshabilitarTipoPersona;

	/** Propiedad ib deshabilitar residencia. */
	private boolean ib_documentoEnOwcc;

	/** Propiedad ib documento generado. */
	private boolean ib_documentoGenerado;

	/** Propiedad ib habilitar correo co. */
	private boolean ib_habilitarCorreoCo;

	/** Propiedad ib habilitar correo no. */
	private boolean ib_habilitarCorreoNo;

	/** Propiedad ib habilitar direccion co co. */
	private boolean ib_habilitarDireccionCoCo;

	/** Propiedad ib habilitar direccion co no. */
	private boolean ib_habilitarDireccionCoNo;

	/** Propiedad ib habilitar direccion re co. */
	private boolean ib_habilitarDireccionReCo;

	/** Propiedad ib habilitar direccion re no. */
	private boolean ib_habilitarDireccionReNo;

	/** Propiedad ib habilitar panel datos contacto. */
	private boolean ib_habilitarPanelDatosContacto;

	/** Propiedad ib habilitar panel dir correspondencia. */
	private boolean ib_habilitarPanelDirCorrespondencia;

	/** Propiedad ib habilitar panel dir residencia. */
	private boolean ib_habilitarPanelDirResidencia;

	/** Propiedad ib habilitar tel fijo co. */
	private boolean ib_habilitarTelFijoCo;

	/** Propiedad ib habilitar tel fijo no. */
	private boolean ib_habilitarTelFijoNo;

	/** Propiedad ib habilitar tel movil co. */
	private boolean ib_habilitarTelMovilCo;

	/** Propiedad ib habilitar tel movil no. */
	private boolean ib_habilitarTelMovilNo;

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

	/** Propiedad ib mostrar telefono fijo. */
	private boolean ib_mostrarTelefonoFijo;

	/** Propiedad ib mostrar telefono movil. */
	private boolean ib_mostrarTelefonoMovil;

	/** Propiedad ib nueva persona. */
	private boolean ib_nuevaPersona;

	/** Propiedad ib proceso nota devolutiva. */
	private boolean ib_procesoNotaDevolutiva;

	/** Propiedad ib render Botones BioClient. */
	private boolean ib_renderBotonesBioClient;

	/** Propiedad ib render digitalizar. */
	private boolean ib_renderDigitalizar;

	/** Propiedad ib rendered. */
	private boolean ib_rendered;

	/** Propiedad ib seleccionado es apoderado. */
	private boolean ib_seleccionadoEsApoderado;

	/** Propiedad ib tercero apoderado. */
	private boolean ib_terceroApoderado;

	/** Propiedad ib tercero guardado. */
	private boolean ib_terceroGuardado;

	/** Propiedad ib_yaDigitalizo. */
	private boolean ib_yaDigitalizo;

	/** Propiedad is interviniente cargado. */
	private boolean is_intervinienteCargado;

	/** Propiedad lb correos intervinientes rendered. */
	private boolean lb_correosIntervinientesRendered;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de bandejaentrada.
	 *
	 * @param ab_b asigna el valor a la propiedad bandejaentrada
	 */
	public void setBandejaentrada(boolean ab_b)
	{
		ib_bandejaentrada = ab_b;
	}

	/**
	 * Valida la propiedad bandejaentrada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bandejaentrada
	 */
	public boolean isBandejaentrada()
	{
		return ib_bandejaentrada;
	}

	/**
	 * Modifica el valor de carta autorizacion cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad carta autorizacion cargada
	 */
	public void setCartaAutorizacionCargada(boolean ab_b)
	{
		ib_cartaAutorizacionCargada = ab_b;
	}

	/**
	 * Valida la propiedad carta autorizacion cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en carta autorizacion cargada
	 */
	public boolean isCartaAutorizacionCargada()
	{
		return ib_cartaAutorizacionCargada;
	}

	/**
	 * Modifica el valor de cedula ciudadania cargada.
	 *
	 * @param ab_b asigna el valor a la propiedad cedula ciudadania cargada
	 */
	public void setCedulaCiudadaniaCargada(boolean ab_b)
	{
		ib_cedulaCiudadaniaCargada = ab_b;
	}

	/**
	 * Valida la propiedad cedula ciudadania cargada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cedula ciudadania cargada
	 */
	public boolean isCedulaCiudadaniaCargada()
	{
		return ib_cedulaCiudadaniaCargada;
	}

	/**
	 * Modifica el valor de correo electronico.
	 *
	 * @param as_s asigna el valor a la propiedad correo electronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de correo electronico ingresado.
	 *
	 * @param as_s asigna el valor a la propiedad correo electronico ingresado
	 */
	public void setCorreoElectronicoIngresado(String as_s)
	{
		is_correoElectronicoIngresado = as_s;
	}

	/**
	 * Retorna el valor de correo electronico ingresado.
	 *
	 * @return el valor de correo electronico ingresado
	 */
	public String getCorreoElectronicoIngresado()
	{
		return is_correoElectronicoIngresado;
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
	 * Modifica el valor de correos intervinientes.
	 *
	 * @param acs_cs asigna el valor a la propiedad correos intervinientes
	 */
	public void setCorreosIntervinientes(Collection<String> acs_cs)
	{
		ics_correosIntervinientes = acs_cs;
	}

	/**
	 * Retorna el valor de correos intervinientes.
	 *
	 * @return el valor de correos intervinientes
	 */
	public Collection<String> getCorreosIntervinientes()
	{
		return ics_correosIntervinientes;
	}

	/**
	 * Modifica el valor de correos intervinientes rendered.
	 *
	 * @param lb_b asigna el valor a la propiedad correos intervinientes rendered
	 */
	public void setCorreosIntervinientesRendered(boolean lb_b)
	{
		lb_correosIntervinientesRendered = lb_b;
	}

	/**
	 * Valida la propiedad correos intervinientes rendered.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en correos intervinientes rendered
	 */
	public boolean isCorreosIntervinientesRendered()
	{
		return lb_correosIntervinientesRendered;
	}

	/**
	 * Modifica el valor de datos documentos cargados.
	 *
	 * @param acsc_csc asigna el valor a la propiedad datos documentos cargados
	 */
	public void setDatosDocumentosCargados(Collection<StreamedContent> acsc_csc)
	{
		icsc_datosDocumentosCargados = acsc_csc;
	}

	/**
	 * Retorna el valor de datos documentos cargados.
	 *
	 * @return el valor de datos documentos cargados
	 */
	public Collection<StreamedContent> getDatosDocumentosCargados()
	{
		if(icsc_datosDocumentosCargados == null)
			icsc_datosDocumentosCargados = new LinkedList<StreamedContent>();

		return icsc_datosDocumentosCargados;
	}

	/**
	 * Modifica el valor de datos documentos nota devolutiva.
	 *
	 * @param acsc_csc asigna el valor a la propiedad datos documentos nota devolutiva
	 */
	public void setDatosDocumentosNotaDevolutiva(Collection<StreamedContent> acsc_csc)
	{
		icsc_datosDocumentosNotaDevolutiva = acsc_csc;
	}

	/**
	 * Retorna el valor de datos documentos nota devolutiva.
	 *
	 * @return el valor de datos documentos nota devolutiva
	 */
	public Collection<StreamedContent> getDatosDocumentosNotaDevolutiva()
	{
		if(icsc_datosDocumentosNotaDevolutiva == null)
			icsc_datosDocumentosNotaDevolutiva = new LinkedList<StreamedContent>();

		return icsc_datosDocumentosNotaDevolutiva;
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
				    getUserId(), StringUtils.getString(getIdTurnoHistoriaEntrega()), IdentificadoresCommon.TURNOS_FECHAS
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
				    getUserId(), StringUtils.getString(getIdTurnoHistoriaEntrega()), IdentificadoresCommon.ACTOS_FECHAS
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
	 * Modifica el valor de datos tramite turno.
	 *
	 * @param actt_ctt asigna el valor a la propiedad datos tramite turno
	 */
	public void setDatosTramiteTurno(Collection<TramiteTurno> actt_ctt)
	{
		ictc_datosTramiteCantidad = actt_ctt;
	}

	/**
	 * Retorna el valor de datos tramite turno.
	 *
	 * @return el valor de datos tramite turno
	 */
	public Collection<TramiteTurno> getDatosTramiteTurno()
	{
		return ictc_datosTramiteCantidad;
	}

	/**
	 * Modifica el valor de decision entrega.
	 *
	 * @param as_s asigna el valor a la propiedad decision entrega
	 */
	public void setDecisionEntrega(String as_s)
	{
		is_decisionEntrega = as_s;
	}

	/**
	 * Retorna el valor de decision entrega.
	 *
	 * @return el valor de decision entrega
	 */
	public String getDecisionEntrega()
	{
		return is_decisionEntrega;
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
	 * Valida la propiedad deshabilitar radicado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar radicado
	 */
	public boolean isDeshabilitarRadicado()
	{
		boolean   lb_b;
		Solicitud ls_solicitud;

		lb_b             = true;
		ls_solicitud     = getEntrega().getSolicitud();

		if(ls_solicitud != null)
		{
			String ls_idTipoRecepcion;
			ls_idTipoRecepcion     = ls_solicitud.getIdTipoRecepcion();

			lb_b = !(StringUtils.isValidString(ls_idTipoRecepcion) && ls_idTipoRecepcion.equalsIgnoreCase("6"));
		}

		return lb_b;
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
	 * Modifica el valor de documento cedula cargado.
	 *
	 * @param asc_sc asigna el valor a la propiedad documento cedula cargado
	 */
	public void setDocumentoCedulaCargado(StreamedContent asc_sc)
	{
		isc_documentoCedulaCargado = asc_sc;
	}

	/**
	 * Retorna el valor de documento cedula cargado.
	 *
	 * @return el valor de documento cedula cargado
	 */
	public StreamedContent getDocumentoCedulaCargado()
	{
		return isc_documentoCedulaCargado;
	}

	/**
	 * Modifica el valor de DocumentoEnOwcc.
	 *
	 * @param ib_documentoEnOwcc de ib documento en owcc
	 */
	public void setDocumentoEnOwcc(boolean ib_documentoEnOwcc)
	{
		this.ib_documentoEnOwcc = ib_documentoEnOwcc;
	}

	/**
	 * Valida la propiedad documento en owcc.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en documento en owcc
	 */
	public boolean isDocumentoEnOwcc()
	{
		return ib_documentoEnOwcc;
	}

	/**
	 * Modifica el valor de documento entrega.
	 *
	 * @param aba_ba asigna el valor a la propiedad documento entrega
	 */
	public void setDocumentoEntrega(byte[] aba_ba)
	{
		iba_documentoEntrega = aba_ba;
	}

	/**
	 * Retorna el valor de documento entrega.
	 *
	 * @return el valor de documento entrega
	 */
	public byte[] getDocumentoEntrega()
	{
		return iba_documentoEntrega;
	}

	/**
	 * Modifica el valor de propiedad documento entrega descarga.
	 *
	 * @param asc_sc asigna el valor a la propiedad documento entrega descarga
	 */
	public void setDocumentoEntregaDescarga(StreamedContent asc_sc)
	{
		isc_documentoEntregaDescarga = asc_sc;
	}

	/**
	 * Retorna el valor propiedad documento entrega descarga.
	 *
	 * @return el valor propiedad documento entrega descarga
	 */
	public StreamedContent getDocumentoEntregaDescarga()
	{
		return isc_documentoEntregaDescarga;
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
	 * Sets the documentos adicionales.
	 *
	 * @param amsba_msba correspondiente al valor del tipo de objeto Map<String,DocumentoAdicional>
	 */
	public void setDocumentosAdicionales(Map<String, DocumentoAdicional> amsba_msba)
	{
		imsda_documentosAdicionales = amsba_msba;
	}

	/**
	 * Retorna el valor de documentos adicionales.
	 *
	 * @return el valor de documentos adicionales
	 */
	public Map<String, DocumentoAdicional> getDocumentosAdicionales()
	{
		if(!CollectionUtils.isValidCollection(imsda_documentosAdicionales))
			imsda_documentosAdicionales = new LinkedHashMap<String, DocumentoAdicional>();

		return imsda_documentosAdicionales;
	}

	/**
	 * Modifica el valor de entrega.
	 *
	 * @param at_t asigna el valor a la propiedad entrega
	 */
	public void setEntrega(EntregaUI at_t)
	{
		ieui_entrega = at_t;
	}

	/**
	 * Retorna el valor de entrega.
	 *
	 * @return el valor de entrega
	 */
	public EntregaUI getEntrega()
	{
		if(ieui_entrega == null)
			ieui_entrega = new EntregaUI();

		return ieui_entrega;
	}

	/**
	 * Modifica el valor de entrega correo.
	 *
	 * @param as_s asigna el valor a la propiedad entrega correo
	 */
	public void setEntregaCorreo(String as_s)
	{
		is_entregaCorreo = as_s;
	}

	/**
	 * Retorna el valor de entrega correo.
	 *
	 * @return el valor de entrega correo
	 */
	public String getEntregaCorreo()
	{
		return is_entregaCorreo;
	}

	/**
	 * Modifica el valor de fecha entrega.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha entrega
	 */
	public void setFechaEntrega(Date ad_d)
	{
	}

	/**
	 * Retorna el valor de fecha entrega.
	 *
	 * @return el valor de fecha entrega
	 */
	public Date getFechaEntrega()
	{
		return new Date();
	}

	/**
	 * Modifica el valor de file notificacion.
	 *
	 * @param auf_uf asigna el valor a la propiedad file notificacion
	 */
	public void setFileNotificacion(UploadedFile auf_uf)
	{
		iuf_fileNotificacion = auf_uf;
	}

	/**
	 * Retorna el valor de file notificacion.
	 *
	 * @return el valor de file notificacion
	 */
	public UploadedFile getFileNotificacion()
	{
		return iuf_fileNotificacion;
	}

	/**
	 * Modifica el valor de file recursos.
	 *
	 * @param auf_ur asigna el valor a la propiedad file recursos
	 */
	public void setFileRecursos(UploadedFile auf_ur)
	{
		iuf_fileRecursos = auf_ur;
	}

	/**
	 * Retorna el valor de file recursos.
	 *
	 * @return el valor de file recursos
	 */
	public UploadedFile getFileRecursos()
	{
		return iuf_fileRecursos;
	}

	/**
	 * Modifica el valor de file terminos.
	 *
	 * @param auf_uf asigna el valor a la propiedad file terminos
	 */
	public void setFileTerminos(UploadedFile auf_uf)
	{
		iuf_fileTerminos = auf_uf;
	}

	/**
	 * Retorna el valor de file terminos.
	 *
	 * @return el valor de file terminos
	 */
	public UploadedFile getFileTerminos()
	{
		return iuf_fileTerminos;
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
	 * Modifica el valor de habilitar panel datos contacto.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar panel datos contacto
	 */
	public void setHabilitarPanelDatosContacto(boolean ab_b)
	{
		ib_habilitarPanelDatosContacto = ab_b;
	}

	/**
	 * Valida la propiedad habilitar panel datos contacto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar panel datos contacto
	 */
	public boolean isHabilitarPanelDatosContacto()
	{
		return ib_habilitarPanelDatosContacto;
	}

	/**
	 * Modifica el valor de habilitar panel dir correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar panel dir correspondencia
	 */
	public void setHabilitarPanelDirCorrespondencia(boolean ab_b)
	{
		ib_habilitarPanelDirCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad habilitar panel dir correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar panel dir correspondencia
	 */
	public boolean isHabilitarPanelDirCorrespondencia()
	{
		return ib_habilitarPanelDirCorrespondencia;
	}

	/**
	 * Modifica el valor de habilitar panel dir residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar panel dir residencia
	 */
	public void setHabilitarPanelDirResidencia(boolean ab_b)
	{
		ib_habilitarPanelDirResidencia = ab_b;
	}

	/**
	 * Valida la propiedad habilitar panel dir residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar panel dir residencia
	 */
	public boolean isHabilitarPanelDirResidencia()
	{
		return ib_habilitarPanelDirResidencia;
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
	 * Valida la propiedad habilitar turno ynir.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar turno ynir
	 */
	public boolean isHabilitarTurnoYnir()
	{
		boolean lb_flag               = true;
		String  ls_idTramiteSeleccion = getEntrega().getSolicitud().getIdSubproceso();

		if(ls_idTramiteSeleccion != null)
			lb_flag = !(StringUtils.isValidString(ls_idTramiteSeleccion)
					&& (ls_idTramiteSeleccion.equalsIgnoreCase("3") || ls_idTramiteSeleccion.equalsIgnoreCase("5")));

		return lb_flag;
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
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		if(il_idEtapa == 0)
		{
			String tmp = FacesUtils.getStringFacesParameter("il_idEtapa");

			if(StringUtils.isValidString(tmp))
				il_idEtapa = Long.parseLong(tmp);
		}

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id firma.
	 *
	 * @param as_s asigna el valor a la propiedad id firma
	 */
	public void setIdFirma(String as_s)
	{
		is_idFirma = as_s;
	}

	/**
	 * Retorna el valor de id firma.
	 *
	 * @return el valor de id firma
	 */
	public String getIdFirma()
	{
		return is_idFirma;
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
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoriaEntrega(Long al_l)
	{
		il_idTurnoHistoriaEntrega = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoriaEntrega()
	{
		return il_idTurnoHistoriaEntrega;
	}

	/**
	 * Modifica el valor de InfoNotificacion.
	 *
	 * @param an_n de an n
	 */
	public void setInfoNotificacion(Notificacion an_n)
	{
		in_infoNotificacion = an_n;
	}

	/**
	 * Retorna Objeto o variable de valor info notificacion.
	 *
	 * @return el valor de info notificacion
	 */
	public Notificacion getInfoNotificacion()
	{
		return in_infoNotificacion;
	}

	/**
	 * Modifica el valor de interpone recurso.
	 *
	 * @param as_s asigna el valor a la propiedad interpone recurso
	 */
	public void setInterponeRecurso(String as_s)
	{
		is_interponeRecurso = as_s;
	}

	/**
	 * Retorna el valor de interpone recurso.
	 *
	 * @return el valor de interpone recurso
	 */
	public String getInterponeRecurso()
	{
		return is_interponeRecurso;
	}

	/**
	 * Modifica el valor de interviene en entrega.
	 *
	 * @param as_s asigna el valor a la propiedad interviene en entrega
	 */
	public void setIntervieneEnEntrega(String as_s)
	{
		is_intervieneEnEntrega = as_s;
	}

	/**
	 * Retorna el valor de interviene en entrega.
	 *
	 * @return el valor de interviene en entrega
	 */
	public String getIntervieneEnEntrega()
	{
		return is_intervieneEnEntrega;
	}

	/**
	 * Modifica el valor de interviniente cargado.
	 *
	 * @param ab_b asigna el valor a la propiedad interviniente cargado
	 */
	public void setIntervinienteCargado(boolean ab_b)
	{
		is_intervinienteCargado = ab_b;
	}

	/**
	 * Valida la propiedad interviniente cargado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en interviniente cargado
	 */
	public boolean isIntervinienteCargado()
	{
		return is_intervinienteCargado;
	}

	/**
	 * Modifica el valor de medio comunicar interesado.
	 *
	 * @param as_s asigna el valor a la propiedad medio comunicar interesado
	 */
	public void setMedioComunicarInteresado(String as_s)
	{
		is_medioComunicarInteresado = as_s;
	}

	/**
	 * Retorna el valor de medio comunicar interesado.
	 *
	 * @return el valor de medio comunicar interesado
	 */
	public String getMedioComunicarInteresado()
	{
		return is_medioComunicarInteresado;
	}

	/**
	 * Modifica el valor de medio notificar interesado.
	 *
	 * @param as_s asigna el valor a la propiedad medio notificar interesado
	 */
	public void setMedioNotificarInteresado(String as_s)
	{
		is_medioNotificarInteresado = as_s;
	}

	/**
	 * Retorna el valor de medio notificar interesado.
	 *
	 * @return el valor de medio notificar interesado
	 */
	public String getMedioNotificarInteresado()
	{
		return is_medioNotificarInteresado;
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
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
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
	 * Modifica el valor de observaciones documentos cargados.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones documentos cargados
	 */
	public void setObservacionesDocumentosCargados(String as_s)
	{
		is_observacionesDocumentosCargados = as_s;
	}

	/**
	 * Retorna el valor de observaciones documentos cargados.
	 *
	 * @return el valor de observaciones documentos cargados
	 */
	public String getObservacionesDocumentosCargados()
	{
		return is_observacionesDocumentosCargados;
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
		{
			ip_persona = new Persona();
			ip_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ip_persona;
	}

	/**
	 * Modifica el valor de persona entrega cargada.
	 *
	 * @param ape_pe asigna el valor a la propiedad persona entrega cargada
	 */
	public void setPersonaEntregaCargada(PersonaEntrega ape_pe)
	{
		ipe_personaEntregaCargada = ape_pe;
	}

	/**
	 * Retorna el valor de persona entrega cargada.
	 *
	 * @return el valor de persona entrega cargada
	 */
	public PersonaEntrega getPersonaEntregaCargada()
	{
		if(ipe_personaEntregaCargada == null)
			ipe_personaEntregaCargada = new PersonaEntrega();

		return ipe_personaEntregaCargada;
	}

	/**
	 * Modifica el valor de persona tercero.
	 *
	 * @param ap_p asigna el valor a la propiedad persona tercero
	 */
	public void setPersonaTercero(Persona ap_p)
	{
		ip_personaTercero = ap_p;
	}

	/**
	 * Retorna el valor de persona tercero.
	 *
	 * @return el valor de persona tercero
	 */
	public Persona getPersonaTercero()
	{
		return ip_personaTercero;
	}

	/**
	 * Modifica el valor de ProcesoNotaDevolutiva.
	 *
	 * @param ab_b de ab b
	 */
	public void setProcesoNotaDevolutiva(boolean ab_b)
	{
		ib_procesoNotaDevolutiva = ab_b;
	}

	/**
	 * Valida la propiedad proceso nota devolutiva.
	 *
	 * @return Retorna el valor de la propiedad procesoNotaDevolutiva
	 */
	public boolean isProcesoNotaDevolutiva()
	{
		return ib_procesoNotaDevolutiva;
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
	 * Modifica el valor de registro tercero.
	 *
	 * @param ar_r asigna el valor a la propiedad registro tercero
	 */
	public void setRegistroTercero(Registro ar_r)
	{
		ir_registroTercero = ar_r;
	}

	/**
	 * Retorna el valor de registro tercero.
	 *
	 * @return el valor de registro tercero
	 */
	public Registro getRegistroTercero()
	{
		return ir_registroTercero;
	}

	/**
	 * Modifica el valor de RenderBotonesBioClient.
	 *
	 * @param ib_renderBotonesBioClient de ib render botones bio client
	 */
	public void setRenderBotonesBioClient(boolean ib_renderBotonesBioClient)
	{
		this.ib_renderBotonesBioClient = ib_renderBotonesBioClient;
	}

	/**
	 * Valida la propiedad render botones bio client.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render botones bio client
	 */
	public boolean isRenderBotonesBioClient()
	{
		return ib_renderBotonesBioClient;
	}

	/**
	 * Modifica el valor de render digitalizar.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderDigitalizar(boolean ab_b)
	{
		ib_renderDigitalizar = ab_b;
	}

	/**
	 * Retorna el valor de render digitalizar.
	 *
	 * @return el valor de render digitalizar
	 */
	public boolean isRenderDigitalizar()
	{
		return ib_renderDigitalizar;
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
	 * Modifica el valor de renuncia terminos.
	 *
	 * @param as_s asigna el valor a la propiedad renuncia terminos
	 */
	public void setRenunciaTerminos(String as_s)
	{
		is_renunciaTerminos = as_s;
	}

	/**
	 * Retorna el valor de renuncia terminos.
	 *
	 * @return el valor de renuncia terminos
	 */
	public String getRenunciaTerminos()
	{
		return is_renunciaTerminos;
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
			if(NumericUtils.isValidLong(il_idTurnoHistoriaEntrega))
			{
				TurnoHistoria lth_temp;
				lth_temp = new TurnoHistoria();

				lth_temp.setIdTurnoHistoria(il_idTurnoHistoriaEntrega);

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
	 * Modifica el valor de tercero apoderado.
	 *
	 * @param terceroApoderado asigna el valor a la propiedad tercero apoderado
	 */
	public void setTerceroApoderado(boolean terceroApoderado)
	{
		this.ib_terceroApoderado = terceroApoderado;
	}

	/**
	 * Valida la propiedad tercero apoderado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tercero apoderado
	 */
	public boolean isTerceroApoderado()
	{
		return ib_terceroApoderado;
	}

	/**
	 * Modifica el valor de tercero guardado.
	 *
	 * @param ab_b asigna el valor a la propiedad tercero guardado
	 */
	public void setTerceroGuardado(boolean ab_b)
	{
		ib_terceroGuardado = ab_b;
	}

	/**
	 * Valida la propiedad tercero guardado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tercero guardado
	 */
	public boolean isTerceroGuardado()
	{
		return ib_terceroGuardado;
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
	 * Modifica el valor de tipo documental.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documental
	 */
	public void setTipoDocumental(String as_s)
	{
		is_tipoDocumental = as_s;
	}

	/**
	 * Retorna el valor de tipo documental.
	 *
	 * @return el valor de tipo documental
	 */
	public String getTipoDocumental()
	{
		return is_tipoDocumental;
	}

	/**
	 * Modifica el valor de tipo entrega.
	 *
	 * @param as_s asigna el valor a la propiedad tipo entrega
	 */
	public void setTipoEntrega(String as_s)
	{
		is_tipoEntrega = as_s;
	}

	/**
	 * Retorna el valor de tipo entrega.
	 *
	 * @return el valor de tipo entrega
	 */
	public String getTipoEntrega()
	{
		return is_tipoEntrega;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad yaDigitalizo por ab_b
	 */
	public void setYaDigitalizo(boolean ab_b)
	{
		ib_yaDigitalizo = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad yaDigitalizo
	 */
	public boolean isYaDigitalizo()
	{
		return ib_yaDigitalizo;
	}

	/**
	 * Abrir pad firmas.
	 */
	public void abrirPadFirmas()
	{
		boolean lb_interviene;
		String  ls_tipoDocumento;
		String  ls_numeroDocumento;

		lb_interviene          = intervieneEnEntrega();
		ls_tipoDocumento       = null;
		ls_numeroDocumento     = null;

		if(lb_interviene)
		{
			EntregaUI le_entrega;
			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				SolicitudInterviniente lsi_interviniente;

				lsi_interviniente = le_entrega.getDatosInterSelect();

				if(lsi_interviniente != null)
				{
					Persona lp_persona;

					lp_persona = lsi_interviniente.getPersona();

					if(lp_persona != null)
					{
						ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();
						ls_numeroDocumento     = lp_persona.getNumeroDocumento();
					}
				}
			}
		}
		else
		{
			Persona lpt_personaEntrega;

			lpt_personaEntrega = getPersonaTercero();

			if(lpt_personaEntrega != null)
			{
				ls_tipoDocumento       = lpt_personaEntrega.getIdDocumentoTipo();
				ls_numeroDocumento     = lpt_personaEntrega.getNumeroDocumento();
			}
		}

		if(StringUtils.isValidString(ls_numeroDocumento) && StringUtils.isValidString(ls_tipoDocumento))
		{
			String ls_idTurno;

			ls_idTurno = obtenerIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				String ls_url;

				ls_url = generarURLBioClient(ls_idTurno, ls_numeroDocumento, ls_tipoDocumento);

				if(StringUtils.isValidString(ls_url))
					PrimeFaces.current().executeScript("abrirURLBioClient('" + ls_url + "');");
			}
		}
	}

	/**
	 * Termina el proceso de entrega para el turno seleccionado y avanza el tramite a la siguiente etapa.
	 *
	 * @return Cadena de caracteres con el enlace a la bandeja de turnos
	 */
	public String accionSalvar()
	{
		String ls_return;

		ls_return = null;

		try
		{
			long ll_etapaActual;

			ll_etapaActual = getIdEtapa();

			if(ll_etapaActual > 0)
			{
				if(ll_etapaActual == EtapaCommon.ID_ETAPA_REANOTACION)
				{
					String ls_decisionEntrega;
					ls_decisionEntrega = getDecisionEntrega();

					if(
					    StringUtils.isValidString(ls_decisionEntrega)
						    && ls_decisionEntrega.equalsIgnoreCase(EstadoCommon.C)
					)
						ls_return = devolverACalificacion();
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_UNA_DECISION);
				}
				else
				{
					EntregaUI              le_entregaUI;
					SolicitudInterviniente lsi_interSelected;
					String                 ls_entregaCorreo;
					String                 ls_interviene;
					Entrega                le_entrega;

					le_entregaUI         = getEntrega();
					le_entrega           = new Entrega();
					ls_entregaCorreo     = "";
					ls_interviene        = StringUtils.getStringNotNull(getIntervieneEnEntrega());

					if(le_entregaUI == null)
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

					lsi_interSelected = le_entregaUI.getDatosInterSelect();

					le_entrega.setTurno(le_entregaUI.getTurno());
					le_entrega.setTurnoHistoria(getIdTurnoHistoriaEntrega());
					le_entrega.setIdEtapa(ll_etapaActual);

					if(ll_etapaActual == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION)
					{
						ls_entregaCorreo = getEntregaCorreo();

						if(StringUtils.isValidString(ls_entregaCorreo))
						{
							if(ls_entregaCorreo.equalsIgnoreCase(EstadoCommon.SI))
							{
								if(ls_interviene.equals(EstadoCommon.SI))
								{
									String ls_correoElectronico;
									String ls_correoElectronicoIngresado;

									ls_correoElectronico              = null;
									ls_correoElectronicoIngresado     = null;

									ls_correoElectronico              = getCorreoElectronico();
									ls_correoElectronicoIngresado     = getCorreoElectronicoIngresado();

									if(
									    (ll_etapaActual == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION)
										    || (ll_etapaActual == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
									)
									{
										if(isCorreosIntervinientesRendered())
											validateStyles(
											    isc_formId + "idITCorreosIntervinientes",
											    FacesContext.getCurrentInstance(), ls_correoElectronico, true
											);
										else
											validateStyles(
											    isc_formId + "idITCorreosIntervinientes2",
											    FacesContext.getCurrentInstance(), ls_correoElectronicoIngresado, true
											);

										if(
										    (StringUtils.isValidString(ls_correoElectronico)
											    && ExpresionRegular.getExpresionRegular()
											                           .esCorreoElectronico(ls_correoElectronico))
										)
											le_entrega.setCorreoElectronico(ls_correoElectronico);
										else if(
										    StringUtils.isValidString(ls_correoElectronicoIngresado)
											    && ExpresionRegular.getExpresionRegular()
											                           .esCorreoElectronico(
											        ls_correoElectronicoIngresado
											    )
										)
											le_entrega.setCorreoElectronico(ls_correoElectronicoIngresado);
										else
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_RESPUESTA);
					}

					if(ls_interviene.equals(EstadoCommon.SI))
					{
						if(lsi_interSelected == null)
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_INTERVINIENTE);

						le_entrega.setIntervinienteEntrega(lsi_interSelected);
						le_entrega.setTercero(null);
					}
					else if(ls_interviene.equals(EstadoCommon.NO))
					{
						PersonaEntrega lpe_personaEntrega;

						lpe_personaEntrega = getPersonaEntregaCargada();

						if((lpe_personaEntrega == null))
							throw new B2BException(ErrorKeys.ERROR_SIN_TERCERO);

						le_entrega.setTercero(lpe_personaEntrega);
						le_entrega.setIntervinienteEntrega(null);

						if(isTerceroApoderado())
						{
							Collection<SolicitudInterviniente> lcsi_intervinientes;
							lcsi_intervinientes = le_entregaUI.getDatosIntervinientes();

							if(CollectionUtils.isValidCollection(lcsi_intervinientes))
							{
								boolean lb_validacion;
								lb_validacion = false;

								for(SolicitudInterviniente lsi_data : lcsi_intervinientes)
								{
									if((lsi_data != null) && lsi_data.isSeleccionado())
									{
										lb_validacion = true;

										break;
									}
								}

								if(!lb_validacion)
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_INTERVINIENTE);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_SIN_INTERVINIENTES_TURNO);

							le_entrega.setIntervinientesApoderado(lcsi_intervinientes);
						}

						if(!isTerceroGuardado())
							throw new B2BException(ErrorKeys.ERROR_DEBE_DAR_CLICK_EN_BOTON_SALVAR);

						le_entrega.setDocumentosAdicionales(getDocumentosAdicionales());
						le_entrega.setObservacionesDocumentos(getObservacionesDocumentosCargados());
					}
					else if(!StringUtils.isValidString(ls_interviene))
					{
						if(!ls_entregaCorreo.equals(EstadoCommon.SI))
							throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_INTERVIENE);
					}

					if(
					    (ll_etapaActual >= EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
						    && (ll_etapaActual <= EtapaCommon.ID_ETAPA_ENTREGA_CORRESPONDENCIA_NOTA_DEVOLUTIVA)
					)
					{
						String ls_renuncia;
						ls_renuncia = getRenunciaTerminos();

						if(CollectionUtils.isValidCollection(getDatosDocumentosNotaDevolutiva()))
							le_entrega.setDocumentosAdicionales(getDocumentosAdicionales());
						else
							throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_PDF);

						if(!StringUtils.isValidString(ls_renuncia))
							throw new B2BException(ErrorKeys.ERROR_SIN_RENUNCIA_TERMINOS);

						le_entrega.setRenunciaTerminos(ls_renuncia);
					}

					ier_entregaRemote.salvarEntrega(le_entrega, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

					clear(false);

					ls_return = regresar();
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
		}
		catch(B2BException lb2be)
		{
			addMessage(lb2be);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Regresa al usuario a la bandeja de turnos de entrega.
	 *
	 * @return Cadena de caracteres con el enlace de la bandeja de turnos de entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String accionVolver()
	    throws B2BException
	{
		String      ls_return;
		BeanEntrega lbe_bean;

		ls_return     = NavegacionCommon.ENTREGA;
		lbe_bean      = (BeanEntrega)FacesUtils.obtenerInstanciaBean(BeanEntrega.class, BeanNameCommon.BEAN_ENTREGA);

		if(lbe_bean != null)
		{
			try
			{
				lbe_bean.generarDatosTramiteCantidad();
				clear(false);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("accionVolver", lb2be_e);
				ls_return = null;
				addMessage(lb2be_e);
			}
		}

		return ls_return;
	}

	/**
	 * Carga nuevamente los turnos asignados y actualiza el gráfico de torta.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionVolverDetalle()
	    throws B2BException
	{
		accionVolverDetalle(false);
	}

	/**
	 * Carga nuevamente los turnos asignados y actualiza el gráfico de torta.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionVolverDetalle(boolean ab_impresionCorresp)
	    throws B2BException
	{
		try
		{
			Collection<TramiteTurno> lctt_tmp;
			lctt_tmp = getDatosTramiteTurno();

			clear(true);

			if(ab_impresionCorresp)
				setDatosTramiteTurno(lctt_tmp);
			else
				obtenerTurnos();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Actualizar estado digitalizacion.
	 */
	public void actualizarEstadoDigitalizacion()
	{
		try
		{
			Collection<TipoDocumental> lctd_tiposDocumentales;

			lctd_tiposDocumentales = getTiposDocumentales();

			if(CollectionUtils.isValidCollection(lctd_tiposDocumentales))
			{
				Entrega le_entrega;

				le_entrega = getEntrega();

				if(le_entrega != null)
				{
					String ls_url;

					ls_url = idr_digitalizacionRemote.construirUrlCapture(
						    le_entrega.getNir(), le_entrega.getIdTurno(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

					if(StringUtils.isValidString(ls_url))
					{
						PrimeFaces.current().executeScript("window.open(href ='" + ls_url + "')");
						PrimeFaces.current().executeScript(ScriptsCommon.ACCION_DIGITALIZACION);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_GENERAR_URL_CAPTURE);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}

			setYaDigitalizo(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarEstadoDigitalizacion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Actualizar interviene turno.
	 */
	public void actualizarIntervieneTurno()
	{
		try
		{
			String ls_interviene;

			ls_interviene = StringUtils.getString(intervieneEnEntrega());

			ier_entregaRemote.actualizarInterviene(
			    ls_interviene, obtenerIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Actualizar tipos documentales.
	 */
	public void actualizarTiposDocumentales()
	{
		try
		{
			setTiposDocumentales(
			    ier_entregaRemote.actualizarTiposDocumentales(
			        getSolicitud(), getTiposDocumentales(), ConstanteCommon.TIPOS_DOCUMENTALES_APODERADO_ENTREGA,
			        ConstanteCommon.TIPOS_DOCUMENTALES_TERCERO_ENTREGA, getUserId(), getLocalIpAddress(),
			        getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarTiposDocumentales", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Busca los municipios de residencia en base de datos, dependiendo de un id país e id departamento seleccionados
	 * previamente.
	 */
	public void cambiarDepartamentoResidencia()
	{
		findMunicipioResidencia();
	}

	/**
	 * Se encarga de dejar la misma direccion tanto para correspondencia como para residencia.
	 *
	 * @param as_mismaDireccionCorrespondencia correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cambiarDireccionCorrespondencia(String as_mismaDireccionCorrespondencia)
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		lbd_beanDireccion.setMismaDireccionCorrespondencia(as_mismaDireccionCorrespondencia);
		lbd_beanDireccion.cambiarDireccionCorrespondencia();
	}

	/**
	 * Guarda el interviniente seleccionado en la sección de entrega donde el usuario selecciona que si interviene en el
	 * tramite.
	 *
	 * @param asi_interSelected Objeto contenedor del interviniente seleccionado
	 */
	public void cambiarInterviniente(SolicitudInterviniente asi_interSelected)
	{
		try
		{
			if(asi_interSelected != null)
			{
				EntregaUI le_entregaUI;

				le_entregaUI = getEntrega();

				if(le_entregaUI != null)
				{
					boolean                            lb_seleccionado;
					Collection<SolicitudInterviniente> lcsi_datosIntervinientes;

					lb_seleccionado              = asi_interSelected.isSeleccionado();
					lcsi_datosIntervinientes     = le_entregaUI.getDatosIntervinientes();

					if(CollectionUtils.isValidCollection(lcsi_datosIntervinientes))
					{
						for(SolicitudInterviniente lsi_iterador : lcsi_datosIntervinientes)
						{
							if(lsi_iterador != null)
							{
								if(lsi_iterador != asi_interSelected)
									lsi_iterador.setSeleccionado(false);
							}
						}

						le_entregaUI.setDatosIntervinientes(lcsi_datosIntervinientes);
					}

					if(lb_seleccionado)
					{
						Collection<String> lcpc_correo;

						lcpc_correo = ier_entregaRemote.findCorreosByIdPersona(asi_interSelected, getUserId());

						if(!lcpc_correo.isEmpty())
						{
							setCorreosIntervinientes(lcpc_correo);
							setCorreosIntervinientesRendered(true);
						}
						else
							setCorreosIntervinientesRendered(false);

						renderBotones();
						le_entregaUI.setDatosInterSelect(asi_interSelected);
					}
					else
					{
						le_entregaUI.setDatosInterSelect(null);
						setCorreosIntervinientes(null);
						setCorreosIntervinientesRendered(false);
						PrimeFaces.current().executeScript(ScriptsCommon.OCULTAR_PAD_FIRMAS);
						PrimeFaces.current().executeScript(ScriptsCommon.OCULTAR_DIGITALIZAR);
					}

					PrimeFaces.current().ajax().update("fDetalleEntrega:idSeccionCorreoInterviniente");
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_INTERVIENE);

				setEntrega(le_entregaUI);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
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

			setMedioComunicarInteresado(as_medio);

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

			setMedioNotificarInteresado(as_medio);

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
	 * Ejecuta el cargue de un archivo PDf para la carta de permiso de un interviniente agregado como tercero.
	 *
	 * @param afue_event Objeto contenedor de el arreglo de bytes perteneciente al documento cargado
	 */
	public void cargarCartaPdf(FileUploadEvent afue_event)
	{
		cargarDocumentoPdf(afue_event, "CA");
	}

	/**
	 * Ejecuta el cargue de un archivo PDf para la cedula de ciudadanía de un interviniente agregado como tercero.
	 *
	 * @param afue_event Objeto contenedor de el arreglo de bytes perteneciente al documento cargado
	 */
	public void cargarCedulaPdf(FileUploadEvent afue_event)
	{
		cargarDocumentoPdf(afue_event, "CC");
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

											idEtapa = getIdEtapa();

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
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada en la consulta por documento.
	 *
	 * @param aop_op Objeto contenedor de la información de la persona que se va a cargar en pantalla
	 */
	public void cargarDatosPersonales(Persona aop_op)
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
	 * Ejecuta el cargue de un archivo PDf para el proceso de nota devolutiva en entrega.
	 *
	 * @param afue_event Objeto contenedor de el arreglo de bytes perteneciente al documento cargado
	 */
	public void cargarDocumentoNotaDev(FileUploadEvent afue_event)
	{
		cargarDocumentoPdf(afue_event, "ND");
	}

	/**
	 * Ejecuta el cargue de un archivo PDf para los documentos adicionales de un interviniente agregado como tercero.
	 *
	 * @param afue_event Objeto contenedor de el arreglo de bytes perteneciente al documento cargado
	 */
	public void cargarDocumentoPdf(FileUploadEvent afue_event)
	{
		PrimeFaces.current().ajax().update("idITAObsDocs");

		cargarDocumentoPdf(afue_event, null);
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
	 * Carga el telefono fijo en los datos de contacto del interviniente.
	 */
	public void cargarTelefonoFijo()
	{
		String ls_idTelFijoSeleccion;
		ls_idTelFijoSeleccion = getIdTelFijoSeleccion();

		if(StringUtils.isValidString(ls_idTelFijoSeleccion))
		{
			String[] lsa_tmp;
			lsa_tmp = ls_idTelFijoSeleccion.split("-");

			if(CollectionUtils.isValidCollection(lsa_tmp) && (lsa_tmp.length > 1))
			{
				String ls_idPersonaSeleccionada;
				String ls_idTelFijoSeleccionada;

				ls_idPersonaSeleccionada     = lsa_tmp[0];
				ls_idTelFijoSeleccionada     = lsa_tmp[1];

				if(
				    StringUtils.isValidString(ls_idPersonaSeleccionada)
					    && StringUtils.isValidString(ls_idTelFijoSeleccionada)
				)
				{
					Registro lr_datosCalculados;
					lr_datosCalculados = getRegistroDatosConsultados();

					if(lr_datosCalculados != null)
					{
						Collection<PersonaTelefono> lcp_telefonos;
						lcp_telefonos = lr_datosCalculados.getListadoTelefonoFijo();

						if(CollectionUtils.isValidCollection(lcp_telefonos))
						{
							for(PersonaTelefono lpt_iterador : lcp_telefonos)
							{
								if(lpt_iterador != null)
								{
									String ls_idPersona;
									String ls_idTelefono;

									ls_idPersona      = lpt_iterador.getIdPersona();
									ls_idTelefono     = lpt_iterador.getIdTelefono();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(ls_idTelefono)
									)
									{
										if(
										    ls_idPersonaSeleccionada.equalsIgnoreCase(ls_idPersona)
											    && ls_idTelFijoSeleccionada.equalsIgnoreCase(ls_idTelefono)
										)
										{
											setTelefonoFijo(lpt_iterador);
											findIndicativoDepartamento();

											setDeshabilitarTelFijo(true);
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
			setTelefonoFijo(null);
			setDeshabilitarTelFijo(false);
		}
	}

	/**
	 * Carga el telefono movil en los datos de contacto del interviniente.
	 */
	public void cargarTelefonoMovil()
	{
		String ls_idTelMovilSeleccion;
		ls_idTelMovilSeleccion = getIdTelMovSeleccion();

		if(StringUtils.isValidString(ls_idTelMovilSeleccion))
		{
			String[] lsa_tmp;
			lsa_tmp = ls_idTelMovilSeleccion.split("-");

			if(CollectionUtils.isValidCollection(lsa_tmp) && (lsa_tmp.length > 1))
			{
				String ls_idPersonaSeleccionada;
				String ls_idTelMovilSeleccionada;

				ls_idPersonaSeleccionada      = lsa_tmp[0];
				ls_idTelMovilSeleccionada     = lsa_tmp[1];

				if(
				    StringUtils.isValidString(ls_idPersonaSeleccionada)
					    && StringUtils.isValidString(ls_idTelMovilSeleccionada)
				)
				{
					Registro lr_datosCalculados;
					lr_datosCalculados = getRegistroDatosConsultados();

					if(lr_datosCalculados != null)
					{
						Collection<PersonaTelefono> lcp_telefonos;
						lcp_telefonos = lr_datosCalculados.getListadoTelefonoMovil();

						if(CollectionUtils.isValidCollection(lcp_telefonos))
						{
							for(PersonaTelefono lpt_iterador : lcp_telefonos)
							{
								if(lpt_iterador != null)
								{
									String ls_idPersona;
									String ls_idTelefono;

									ls_idPersona      = lpt_iterador.getIdPersona();
									ls_idTelefono     = lpt_iterador.getIdTelefono();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && StringUtils.isValidString(ls_idTelefono)
									)
									{
										if(
										    ls_idPersonaSeleccionada.equalsIgnoreCase(ls_idPersona)
											    && ls_idTelMovilSeleccionada.equalsIgnoreCase(ls_idTelefono)
										)
										{
											setTelefonoMovil(lpt_iterador);
											setDeshabilitarTelMovil(true);
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
			setTelefonoMovil(null);
			setDeshabilitarTelMovil(false);
		}
	}

	/**
	 * Consulta en base de datos si para el turno seleccionado ya existe un interviniente agregado como tercero.
	 */
	public void cargarTercero()
	{
		try
		{
			Solicitud                  ls_solicitud;
			PersonaEntrega             lpe_personaEntrega;
			Collection<PersonaEntrega> lcpe_personas;

			ls_solicitud           = getSolicitud();
			lpe_personaEntrega     = new PersonaEntrega();

			if(ls_solicitud != null)
				lpe_personaEntrega.setIdSolicitud(ls_solicitud.getIdSolicitud());

			lcpe_personas = ier_entregaRemote.findPersonaEntregaBySolicitud(
				    lpe_personaEntrega, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcpe_personas))
			{
				setIntervinienteCargado(true);

				for(PersonaEntrega lpe_iterador : lcpe_personas)
				{
					if(lpe_iterador != null)
					{
						setPersonaEntregaCargada(lpe_iterador);

						setPersonaTercero(
						    ier_entregaRemote.findDatosPersonaTercero(
						        lpe_iterador.getIdPersona(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						    )
						);

						String ls_calidad;
						ls_calidad = StringUtils.getStringNotNull(lpe_iterador.getIdCalidadPersonaEntrega());

						if(ls_calidad.equals(CalidadSolicitanteCommon.APODERADO))
						{
							setTerceroApoderado(true);

							break;
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
	 * Consulta y carga en pantalla toda la información relacionada a un turno seleccionado.
	 *
	 * @param att_param Objeto contenedor del id turno seleccionado.
	 * @param ab_impresionDocumentosCorrespondencia de ab impresion documentos correspondencia
	 */
	public void cargarTurno(TramiteTurno att_param, boolean ab_impresionDocumentosCorrespondencia)
	{
		cargarTurno(att_param, ab_impresionDocumentosCorrespondencia, false);
	}

	/**
	 * Cargar turno.
	 *
	 * @param att_param de att param
	 * @param ab_impresionDocumentosCorrespondencia de ab impresion documentos correspondencia
	 * @param ab_notificaciones de ab notificaciones
	 */
	public void cargarTurno(
	    TramiteTurno att_param, boolean ab_impresionDocumentosCorrespondencia, boolean ab_notificaciones
	)
	{
		if(att_param != null)
		{
			try
			{
				Entrega le_entrega;
				String  ls_idTurno;
				Long    ll_idTurnoHistoria;

				le_entrega             = ier_entregaRemote.cargarTurno(
					    att_param, getIdEtapa(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				ls_idTurno             = att_param.getIdTurno();
				ll_idTurnoHistoria     = att_param.getIdTurnoHistoria();

				if(le_entrega != null)
				{
					EntregaUI                  leui_entrega;
					Collection<TipoDocumental> lctd_tiposDocumentales;

					leui_entrega               = new EntregaUI(le_entrega);
					lctd_tiposDocumentales     = leui_entrega.getTiposDocumentales();

					setEntrega(leui_entrega);
					setTiposDocumentales(leui_entrega.getTiposDocumentales());
					setIdTurnoHistoriaEntrega(
					    NumericUtils.getLongWrapper(StringUtils.getString(ll_idTurnoHistoria), 0L)
					);
					setIdTurno(ls_idTurno);
					setSolicitud(leui_entrega.getSolicitud());
					setBandejaentrada(false);
					setRenderDigitalizar(isListoParaDigitalizar(lctd_tiposDocumentales));

					if(leui_entrega.isProcesoVinculado47o48())
						setRenunciaTerminos(EstadoCommon.NO);

					cargarTercero();
					cargarMediosNotCom(false);
				}
				else
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = ls_idTurno;
					throw new B2BException(ErrorKeys.ERROR_CARGUE_ENTREGA, loa_messageArgs);
				}

				if(ab_impresionDocumentosCorrespondencia)
				{
					Collection<DocumentosSalida> lcds_documentoSalida;
					lcds_documentoSalida = ier_entregaRemote.cargarDocumentosImpresion(
						    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					setDocumentosImprimir(lcds_documentoSalida);

					if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
						addMessage(MessagesKeys.TODOS_LOS_DOCUMENTOS_FUERON_IMPRESOS);
				}

				if(ab_notificaciones)
				{
					Entrega le_entregaParam;

					le_entregaParam = getEntrega();

					if(le_entregaParam != null)
					{
						le_entregaParam.setTurnoHistoria(ll_idTurnoHistoria);

						setInfoNotificacion(
						    inr_notificacionesRemote.cargarDatosNotificaciones(
						        le_entregaParam, getIdEtapa(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						    )
						);
					}
				}
			}
			catch(B2BException lb2be)
			{
				addMessage(lb2be);
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
				setBandejaentrada(true);
			}
		}
	}

	/**
	 * Cargar turno.
	 *
	 * @param att_param de att param
	 */
	public void cargarTurno(TramiteTurno att_param)
	{
		cargarTurno(att_param, false);
	}

	/**
	 * Hace un reset a todas las variables implicadas en el proceso de entrega.
	 *
	 * @param ab_parcial de ab parcial
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void clear(boolean ab_parcial)
	    throws B2BException
	{
		{
			BeanDireccion lbd_beanDireccion;

			lbd_beanDireccion = getBeanDireccion();

			lbd_beanDireccion.setDireccionResidencia(null);
			lbd_beanDireccion.setDireccionCorrespondencia(null);
			lbd_beanDireccion.setMismaDireccionCorrespondencia(null);
			lbd_beanDireccion.setDeshabilitarResidencia(false);
			lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
		}

		setYaDigitalizo(false);
		setSeleccionadoEsApoderado(false);
		setBandejaentrada(true);
		setDocumentoGenerado(false);
		setRendered(false);
		setDeshabilitarDatosBasicos(false);
		setMostrarDatosConsulta(false);
		setNuevaPersona(false);
		setHabilitarPanelDirResidencia(false);
		setHabilitarPanelDirCorrespondencia(false);
		setHabilitarPanelDatosContacto(false);
		setMedioNotificarInteresado(null);
		setCartaAutorizacionCargada(false);
		setCedulaCiudadaniaCargada(false);
		setDocumentoEntrega(null);
		setDatosDocumentosCargados(null);
		setMedioComunicarInteresado(null);
		setIntervinienteCargado(false);
		setRegistroTercero(null);
		setTerceroApoderado(false);
		setObservacionesDocumentosCargados(null);
		setDatosTramiteTurno(null);
		setEntrega(null);
		setRenunciaTerminos(null);
		setInterponeRecurso(null);
		setIdTurnoHistoriaEntrega(null);
		setEntregaCorreo(null);
		setCorreoElectronico(null);
		setIntervieneEnEntrega(null);
		setTipoDocInteresado(null);
		setDocumentoInteresado(null);
		setPersona(null);
		setIdPersonaSeleccion(null);
		setIdDirResSeleccion(null);
		setIdDirCorrSeleccion(null);
		setIdTelFijoSeleccion(null);
		setIdTelMovSeleccion(null);
		setIdCorreoSeleccion(null);
		setDeshabilitarTipoDocumento(false);
		setDeshabilitarTipoPersona(false);
		setDatosDocumentosNotaDevolutiva(null);
		setCorreosIntervinientesRendered(false);
		setDireccionResidencia(null);
		setDireccionCorrespondencia(null);
		setTelefonoFijo(null);
		setTelefonoMovil(null);
		setMismaDireccionCorrespondencia(null);
		setDeshabilitarDatosBasicos(false);
		setDeshabilitarCamposPorNit(false);
		setDeshabilitarResidencia(false);
		setDeshabilitarCorrespondencia(false);
		setDeshabilitarTelFijo(false);
		setDeshabilitarTelMovil(false);
		setDeshabilitarCorreo(false);
		setRegistroDatosConsultados(null);
		setMediosComunicar(null);
		setMediosNotificar(null);
		setSolicitud(null);
		setPersonaTercero(null);
		setTipoDocumental(null);
		setDocumentosAdicionales(null);
		setActosFechas(null);
		setTurnosFechas(null);
		setMostrarDatosBasicos(true);
		setTerceroGuardado(false);
		setTiposDocumentales(null);
		setIdFirma(null);
		setDocumentoEnOwcc(false);
		setDocumentosImprimir(null);
		setRenderBotonesBioClient(false);
		setProcesoNotaDevolutiva(false);
		ii_indiceImpresion = 0;

		if(!ab_parcial)
		{
			setIdTurno(null);
			setNir(null);
			setIdEtapa(0);
		}
	}

	/**
	 * Busca en base de datos si el tipo de documento y el numero de documento ingresados tienen registros en la base de
	 * datos.
	 *
	 * @param ab_mostrarAlertas Define si durante el proceso de consulta va a mostrar alertas informativas en pantalla.
	 */
	public void consultarPersonaDocumento(boolean ab_mostrarAlertas)
	{
		boolean lb_focus;

		lb_focus = true;

		setRendered(false);
		setPersona(null);
		setDeshabilitarDatosBasicos(false);
		setMostrarDatosConsulta(false);
		setYaDigitalizo(false);

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
					    isc_formId + "idSOMTipoDocIdentidad", lfc_context, ls_idTipoDocumento, lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoDocumento))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
			}

			{
				String ls_documentoIdentidad;

				ls_documentoIdentidad     = lp_parametros.getNumeroDocumento();
				lb_focus                  = validateStyles(
					    isc_formId + "idItDocumentoID", lfc_context, ls_documentoIdentidad, lb_focus
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
	 * Se encarga de contar la cantidad de caracteres escrita en un campo de texto.
	 *
	 * @param ls_campo Cadena de caracteres con la información ingresada en pantalla por el usuario
	 * @return Cadena de caracteres con el valor numérico del total de caracteres de la cadena ingresada.
	 */
	public String contar(String ls_campo)
	{
		char[] lca_arrayChar;
		int    li_contador;
		String ls_result;

		li_contador = 0;

		if(ls_campo != null)
		{
			lca_arrayChar     = ls_campo.toCharArray();
			li_contador       = lca_arrayChar.length;
		}

		ls_result = Integer.toString(li_contador);

		return ls_result;
	}

	/**
	 * Devuelve un tramite a la etapa de calificación y redirige a la pantalla de turnos de entrega.
	 *
	 * @return Cadena de caracteres con el enlace de la bandeja de turnos de entrega.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String devolverACalificacion()
	    throws B2BException
	{
		String ls_return;
		ls_return = null;

		try
		{
			String ls_decisionEntrega;

			ls_decisionEntrega = getDecisionEntrega();

			if(StringUtils.isValidString(ls_decisionEntrega))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurno(getIdTurno());
				lth_turnoHistoria.setIdTurnoHistoria(getIdTurnoHistoriaEntrega());
				lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(getIdEtapa()));
				lth_turnoHistoria.setIdUsuarioModificacion(getUserId());
				lth_turnoHistoria.setIpModificacion(getRemoteIpAddress());

				ier_entregaRemote.devolverACalificacion(
				    lth_turnoHistoria, ls_decisionEntrega, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			else
				throw new B2BException("Debe seleccionar decision de entrega");

			ls_return = regresar();
		}
		catch(B2BException lb2be_e)
		{
			ls_return = NavegacionCommon.DETALLE_ENTREGA;
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fReanotacion:idGrowl");
		}

		clear(false);

		return ls_return;
	}

	/**
	 * Elimina el arreglo de bytes de un documento agregado.
	 *
	 * @param asc_sc Objeto contenedor del documento que se va a eliminar
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void eliminarDocumentoCargado(StreamedContent asc_sc)
	    throws IOException
	{
		if(asc_sc != null)
		{
			Collection<StreamedContent> lsc_datosCargados;
			Collection<StreamedContent> lsc_datosCargadosNotaDev;
			StreamedContent             lsc_guardadoCedula;
			String                      ls_nombreBorrar;
			InputStream                 lis_data;

			lsc_datosCargados            = getDatosDocumentosCargados();
			lsc_datosCargadosNotaDev     = getDatosDocumentosNotaDevolutiva();
			lsc_guardadoCedula           = getDocumentoCedulaCargado();
			ls_nombreBorrar              = StringUtils.getStringNotNull(asc_sc.getName());
			lis_data                     = asc_sc.getStream();

			if(lsc_guardadoCedula != null)
			{
				String ls_nombreCedula;
				ls_nombreCedula = StringUtils.getStringNotNull(lsc_guardadoCedula.getName());

				if(ls_nombreBorrar.equalsIgnoreCase(ls_nombreCedula))
				{
					setDocumentoCedulaCargado(null);
					setCedulaCiudadaniaCargada(false);
				}
			}

			if(lis_data != null)
			{
				byte[]                          lba_datos;
				Map<String, DocumentoAdicional> lmsba_adicionalesCargados;

				lba_datos                     = IOUtils.toByteArray(lis_data);
				lmsba_adicionalesCargados     = getDocumentosAdicionales();

				if(CollectionUtils.isValidCollection(lmsba_adicionalesCargados))
					lmsba_adicionalesCargados.remove(lba_datos);
			}

			if(CollectionUtils.isValidCollection(lsc_datosCargados))
				lsc_datosCargados.remove(asc_sc);

			if(CollectionUtils.isValidCollection(lsc_datosCargadosNotaDev))
				lsc_datosCargadosNotaDev.remove(asc_sc);
		}
	}

	/**
	 * Elimina de base de datos el interviniente agregado cómo tercero y hace un reset a las variables implicadas en
	 * este proceso.
	 */
	public void eliminarTerceroCargado()
	{
		try
		{
			BeanDireccion lbd_beanDireccion;

			lbd_beanDireccion = getBeanDireccion();

			ier_entregaRemote.eliminarDatosPersonaTercero(
			    getPersonaEntregaCargada(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setPersonaEntregaCargada(null);
			setIntervinienteCargado(false);
			setCedulaCiudadaniaCargada(false);
			setCartaAutorizacionCargada(false);
			setDocumentoEntrega(null);
			setDocumentoCedulaCargado(null);
			setTerceroApoderado(false);
			setTipoDocInteresado(null);
			setDocumentoInteresado(null);
			setRendered(false);
			setPersona(null);
			setSolicitud(null);
			setDeshabilitarCamposPorNit(false);
			setDeshabilitarDatosBasicos(false);
			setDeshabilitarTipoDocumento(false);
			setDeshabilitarTipoPersona(false);
			lbd_beanDireccion.setDireccionResidencia(null);
			setDireccionResidencia(null);
			lbd_beanDireccion.setDireccionCorrespondencia(null);
			setDireccionCorrespondencia(null);
			setTelefonoFijo(null);
			setTelefonoMovil(null);
			setCorreoElectronicoPersona(null);
			setTipoDocumental(null);
			setDocumentosAdicionales(null);
			setActosFechas(null);
			setTurnosFechas(null);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Consulta los departamentos de correspondencia dependiendo de un id país selecionado previamente.
	 *
	 * @return Colección de departamentos resultante de la consulta
	 */
	public Collection<Departamento> findDepartamentosCorrespondencia()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			PersonaDireccion lpd_correspondencia;
			lpd_correspondencia = getDireccionCorrespondencia();

			if(lpd_correspondencia != null)
			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(lpd_correspondencia.getIdPais());

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
	 * Consulta los departamentos de residencia dependiendo de un id país selecionado previamente.
	 *
	 * @return Colección de departamentos resultante de la consulta
	 */
	public Collection<Departamento> findDepartamentosResidencia()
	{
		return null;
	}

	/**
	 * Consulta los departamentso disponibles para un numero telefónico, dependiendo de un id país.
	 *
	 * @return Colección de departamentos resultante de la consulta
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
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Consulta el indicativo telefónico de un departamento previamente seleccionado.
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
			addMessage(lb2be_e);
		}
	}

	/**
	 * Busca los municipios de correspondencia en base de datos, dependiendo de un id país e id departamento
	 * seleccionados previamente.
	 *
	 * @return Colección de municipios resultante de la consulta.
	 */
	public Collection<Municipio> findMunicipioCorrespondencia()
	{
		Collection<Municipio> lcm_municipios;
		lcm_municipios = null;

		try
		{
			PersonaDireccion lpd_correspondencia;
			lpd_correspondencia = getDireccionCorrespondencia();

			if(lpd_correspondencia != null)
			{
				Municipio lm_parametros;
				lm_parametros = new Municipio();

				lm_parametros.setIdPais(lpd_correspondencia.getIdPais());
				lm_parametros.setIdDepartamento(lpd_correspondencia.getIdDepartamento());

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
	 * Busca los municipios de residencia en base de datos, dependiendo de un id país e id departamento seleccionados
	 * previamente.
	 *
	 * @return Colección de municipios resultante de la consulta.
	 */
	public Collection<Municipio> findMunicipioResidencia()
	{
		return null;
	}

	/** {@inheritdoc} */
	public void firma()
	{
		String    ls_numeroDocumento;
		EntregaUI le_entrega;

		le_entrega     = getEntrega();

		ls_numeroDocumento = null;

		if(le_entrega != null)
		{
			if(intervieneEnEntrega())
			{
				SolicitudInterviniente lsi_interviniente;

				lsi_interviniente = le_entrega.getDatosInterSelect();

				if(lsi_interviniente != null)
				{
					Persona lp_persona;

					lp_persona = lsi_interviniente.getPersona();

					if(lp_persona != null)
						ls_numeroDocumento = lp_persona.getNumeroDocumento();
				}
			}
			else
			{
				Persona lpt_personaEntrega;

				lpt_personaEntrega = getPersonaTercero();

				if(lpt_personaEntrega != null)
					ls_numeroDocumento = lpt_personaEntrega.getNumeroDocumento();
			}

			if(StringUtils.isValidString(ls_numeroDocumento))
			{
				String ls_idTurno;

				ls_idTurno = le_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_numeroDocumento))
				{
					StringBuilder lsb_builder;

					lsb_builder = new StringBuilder(ls_idTurno);

					lsb_builder.append(ls_numeroDocumento);
					setIdFirma(lsb_builder.toString());
					PrimeFaces.current().executeScript("accionBotonPadFirmas();PF('statusDispositivo').hide();");
				}
			}
		}
	}

	/**
	 * Generar documento entrega.
	 */
	public void generarDocumentoEntrega()
	{
		try
		{
			EntregaUI le_entrega;

			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				Persona lp_persona;

				lp_persona = null;

				if(intervieneEnEntrega())
				{
					SolicitudInterviniente lsi_interviniente;

					lsi_interviniente = le_entrega.getDatosInterSelect();

					if(lsi_interviniente != null)
						lp_persona = lsi_interviniente.getPersona();

					if((lp_persona != null) && (lp_persona.getIdPersona() == null))
						lp_persona.setIdPersona(lsi_interviniente.getIdPersona());
				}
				else
					lp_persona = getPersonaTercero();

				{
					ObtenerFirmaDTO lefg_param;

					lefg_param = new ObtenerFirmaDTO(getUserId(), getIdFirma());

					setDocumentoEntrega(
					    ier_entregaRemote.generarDocumentoEntrega(
					        lefg_param, le_entrega.getIdTurno(), lp_persona, intervieneEnEntrega(), getUserId(),
					        getLocalIpAddress(), getRemoteIpAddress()
					    )
					);
					PrimeFaces.current().executeScript("accionBotonGenerarDocumentoEntrega()");
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Guardar documento entrega.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDocumentoEntrega()
	    throws B2BException
	{
		try
		{
			Entrega le_entrega;

			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				String ls_idTurno;

				ls_idTurno = le_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					ier_entregaRemote.guardarDocumentoOWCC(
					    ls_idTurno, new ObtenerFirmaDTO(getUserId(), getIdFirma()), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);
					setDocumentosImprimir(
					    ier_entregaRemote.cargarDocumentosImpresion(
					        ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);

					if(getDocumentosImprimir() == null)
						mostrarEnviar();

					setDocumentoEnOwcc(true);
					PrimeFaces.current().executeScript("accionGuardarDocumentoEntrega();");
					addMessage(MessagesKeys.PROCESO_COMPLETADO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("guardarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		imprimirDocumentos(false, as_pantalla);
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		Entrega le_entrega;

		le_entrega = getEntrega();

		if(le_entrega != null)
			imprimirDocumentos(ab_boton, as_pantalla, le_entrega.getIdTurno(), ":idCBEnviar");
	}

	/**
	 * Método que invoca a clear y a getIdEtapa.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void iniciar()
	    throws B2BException
	{
		clear(false);
		getIdEtapa();
	}

	/**
	 * Limpia los campos del formulario de entrega por correo.
	 */
	public void limpiarCorreo()
	{
		setIntervieneEnEntrega(null);
		setCorreoElectronico(null);
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
	 * Hace un reset a las variables implicadas en el proceso de agregar un interviniente tercero.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void limpiarIntervinientes()
	    throws B2BException
	{
		limpiarInteresados();

		setCedulaCiudadaniaCargada(false);
		setCartaAutorizacionCargada(false);
		setDocumentoEntrega(null);
		setDocumentoCedulaCargado(null);
		setDocumentosAdicionales(null);
		setActosFechas(null);
		setTurnosFechas(null);
		setObservacionesDocumentosCargados(null);
		setRenderBotonesBioClient(true);
		setDocumentosImprimir(null);
		renderBotones(true);

		{
			EntregaUI leui_entrega;

			leui_entrega = getEntrega();

			if(leui_entrega != null)
				leui_entrega.setDatosInterSelect(null);
		}
	}

	/**
	 * Mostrar.
	 */
	public void mostrar()
	{
		PrimeFaces.current().executeScript("PF('statusDownload').show();");
	}

	/**
	 * Mostrar enviar.
	 */
	public void mostrarEnviar()
	{
		PrimeFaces.current().executeScript("mostrarElementos('fDetalleEntrega:idCBEnviar')");
	}

	/**
	 * Consulta en la base de datos las observaciones del proceso.
	 *
	 * @return cadena de caracteres con las observaciones recuperadas
	 */
	public String obtenerObservaciones()
	{
		String ls_observaciones;
		ls_observaciones = null;

		try
		{
			TurnoHistoria lth_thTemp;
			lth_thTemp = new TurnoHistoria();
			lth_thTemp.setIdTurno(getIdTurno());
			ls_observaciones = irr_reanotacionRemote.getObservaciones(
				    lth_thTemp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}

		return ls_observaciones;
	}

	/**
	 * Busca en la base de datos los turnos asignados a un usuario específico en una etapa determinada.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void obtenerTurnos()
	    throws B2BException
	{
		obtenerTurnos(false);
	}

	/**
	 * Busca en la base de datos los turnos asignados a un usuario específico en una etapa determinada.
	 *
	 * @param ab_notificaciones Variable de tipo boolean.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void obtenerTurnos(boolean ab_notificaciones)
	    throws B2BException
	{
		try
		{
			Collection<TramiteTurno> lctt_temp;

			lctt_temp = ier_entregaRemote.findDetailDelivery(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), getIdEtapa(), getNir(), getIdTurno(),
				    ab_notificaciones
				);

			if(CollectionUtils.isValidCollection(lctt_temp))
			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				lbd_beanDireccion.limpiarBeanDireccion();

				setDatosTramiteTurno(lctt_temp);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			setDatosTramiteTurno(null);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Ocultar.
	 */
	public void ocultar()
	{
		PrimeFaces.current().executeScript("PF('statusDownload').show();");
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

			PrimeFaces.current().ajax().update(":fDetalleEntrega:idPanelMedios");
		}
	}

	/**
	 * Regresa al usuario a la bandeja de turnos de entrega.
	 *
	 * @return Cadena de caracteres con el enlace de la bandeja de turnos de entrega
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String regresar()
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.ENTREGA;

		FacesContext lfc_context;
		lfc_context = FacesUtils.getFacesContext();

		BeanEntrega lb_beanEntrega;
		lb_beanEntrega = (BeanEntrega)lfc_context.getApplication()
				                                     .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_ENTREGA, BeanEntrega.class
				);

		lb_beanEntrega.clean();
		lb_beanEntrega.generarDatosTramiteCantidad();

		return ls_return;
	}

	/**
	 * Render botones.
	 *
	 * @param ab_noInterviene de ab no interviene
	 */
	public void renderBotones(boolean ab_noInterviene)
	{
		try
		{
			boolean lb_seleccionado;

			lb_seleccionado = false;

			if(ab_noInterviene)
				lb_seleccionado = validarIntervinienteSeleccionado();

			if(lb_seleccionado || !ab_noInterviene)
			{
				PrimeFaces lp_primefaces;
				boolean    lb_interviene;

				lp_primefaces     = PrimeFaces.current();
				lb_interviene     = intervieneEnEntrega();

				lp_primefaces.executeScript(
				    lb_interviene ? ScriptsCommon.MOSTRAR_PAD_FIRMAS : ScriptsCommon.MOSTRAR_DIGITALIZAR
				);
				lp_primefaces.executeScript(
				    lb_interviene ? ScriptsCommon.OCULTAR_DIGITALIZAR : ScriptsCommon.OCULTAR_PAD_FIRMAS
				);
			}
			else
			{
				PrimeFaces.current().executeScript(ScriptsCommon.OCULTAR_PAD_FIRMAS);
				PrimeFaces.current().executeScript(ScriptsCommon.OCULTAR_DIGITALIZAR);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Guarda en base de datos la información agregada para el interviniente tercero.
	 */
	public void salvarTercero()
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
				if(ls_solicitud != null)
				{
					String ls_datoParaValidar;

					ls_datoParaValidar     = ls_solicitud.getIdCalidadSolicitante();

					lb_focus = validateStyles(isc_formId + "idSOMCalidad", ifc_context, ls_datoParaValidar, lb_focus);

					if(!lb_focus)
					{
						ls_idPanelAToglear = "idPPanelDatosBasicosWVar";
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_INTERESADO);
					}
				}

				if(!isDeshabilitarDatosBasicos())
				{
					{
						String ls_datoParaValidar;

						ls_datoParaValidar     = lp_persona.getIdTipoPersona();

						lb_focus = validateStyles(
							    isc_formId + "idSOMTipoPersona", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idSOMTipoDoc", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idOlDocumento", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idSOMNacionalidad", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idOlPNombre", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idOlPApellido", ifc_context, ls_datoParaValidar, lb_focus
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
							    isc_formId + "idSOMGenero", ifc_context, ls_datoParaValidar, lb_focus
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
								    isc_formId + "idOlRazonSocial", ifc_context, ls_datoParaValidar, lb_focus
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
									    isc_formId + "idsomMedioANotificar", ifc_context, ls_datoParaValidar, lb_focus
									);

								if(!lb_focus)
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_NOTIFICAR);
							}

							{
								String ls_datoParaValidar;

								ls_datoParaValidar     = ls_solicitud.getIdAutorizacionComunicacion();

								lb_focus = validateStyles(
									    isc_formId + "idsomMedioACom", ifc_context, ls_datoParaValidar, lb_focus
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

							lb_focus = validateStyles(isc_formId + "idSOMPaisTel", ifc_context, ls_paisFijo, lb_focus);

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
								    isc_formId + "idSOMDepTel", ifc_context, ls_departamentoFijo, lb_focus
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
								lb_focus = validateStyles(isc_formId + "idItTelefonoFijo", ifc_context, "", lb_focus);
							else
								lb_focus = validateStyles(
									    isc_formId + "idItTelefonoFijo", ifc_context, ls_telefono, lb_focus
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

								ls_paisMovil     = StringUtils.getStringNotNull(lpt_personaTelefonoMovil.getIdPais());

								lb_focus = validateStyles(
									    isc_formId + "idSOMPaisTelMov", ifc_context, ls_paisMovil, lb_focus
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
										    isc_formId + "idItTelefonoMovil", ifc_context, "", lb_focus
										);
								else
									lb_focus = validateStyles(
										    isc_formId + "idItTelefonoMovil", ifc_context, ls_telefono, lb_focus
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
											    isc_formId + "idItEmail", ifc_context, is_datoParaValidar, lb_focus
											);

										if(
										    !ExpresionRegular.getExpresionRegular()
											                     .esCorreoElectronico(is_datoParaValidar)
										)
										{
											lb_focus               = validateStyles(
												    isc_formId + "idItEmail", ifc_context, "", lb_focus
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

				PersonaEntrega lpe_personaEntrega;

				lr_registro.setPersona(lp_persona);
				lr_registro.setDireccionCorrespondencia(lpd_personaDireccionCorrespondencia);
				lr_registro.setDireccionResidencia(lpd_personaDireccionResidencia);
				lr_registro.setTelefonoFijo(lpt_personaTelefonoFijo);
				lr_registro.setTelefonoMovil(lpt_personaTelefonoMovil);
				lr_registro.setPersonaCorreoElectronico(lpce_correoElectronico);
				lr_registro.setSolicitud(ls_solicitud);
				lr_registro.setIdTurno(getIdTurno());
				lr_registro.setSeleccionadoEsApoderado(isSeleccionadoEsApoderado());

				lpe_personaEntrega = ier_entregaRemote.salvarDatosIntervininete(
					    lr_registro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setPersonaTercero(lp_persona);

				if(lpe_personaEntrega != null)
				{
					String ls_calidad;

					setPersonaEntregaCargada(lpe_personaEntrega);
					setIntervinienteCargado(true);

					ls_calidad = StringUtils.getStringNotNull(lpe_personaEntrega.getIdCalidadPersonaEntrega());

					if(ls_calidad.equals(CalidadSolicitanteCommon.APODERADO))
						setTerceroApoderado(true);
				}
			}

			setTerceroGuardado(true);
			renderBotones(true);
		}
		catch(B2BException lb2be_e)
		{
			expandirToggeable(ls_idPanelAToglear);
			setPersonaTercero(null);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Salvar tipos documentales.
	 */
	public void salvarTiposDocumentales()
	{
		try
		{
			Collection<TipoDocumental> lctd_tiposDocumentales;

			lctd_tiposDocumentales = getTiposDocumentales();

			if(CollectionUtils.isValidCollection(lctd_tiposDocumentales))
				validarTiposDocumentales(lctd_tiposDocumentales, true);
			else
				throw new B2BException(ErrorKeys.DEBE_AGREGAR_UN_TIPO_DOCUMENTAL);

			setTiposDocumentales(
			    ier_entregaRemote.salvarTiposDocumentales(
			        lctd_tiposDocumentales, getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
			PrimeFaces.current().executeScript("accionGuardarTipoDocumentalEntrega();");
			addMessage(MessagesKeys.PROCESO_COMPLETADO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarTiposDocumentales", lb2be_e);
			addMessage(lb2be_e);
		}
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
	 * Visualizar documento entrega.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void visualizarDocumentoEntrega()
	    throws B2BException
	{
		try
		{
			byte[] lba_documento;

			lba_documento = getDocumentoEntrega();

			if(lba_documento != null)
				setDocumentoEntregaDescarga(
				    generarArchivosDescarga(
				        lba_documento, TipoContenidoCommon.PDF,
				        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
				    )
				);
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("visualizarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Metodo que construye la URL para el BioClient.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_numeroDocumento de as numero documento
	 * @param as_tipoDocumento de as tipo documento
	 * @return devuelve el valor de String
	 */
	protected String generarURLBioClient(String as_idTurno, String as_numeroDocumento, String as_tipoDocumento)
	{
		StringBuilder lsb_builder;
		String        ls_url;

		ls_url          = null;
		lsb_builder     = new StringBuilder(IdentificadoresCommon.PROTOCOLO_BACHUE);
		lsb_builder.append(EventoCommon.SIGN);
		lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
		lsb_builder.append(as_idTurno);
		lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
		lsb_builder.append(as_numeroDocumento);
		lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
		lsb_builder.append(as_tipoDocumento);
		lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
		lsb_builder.append(getUserId());

		ls_url = lsb_builder.toString();

		return ls_url;
	}

	/**
	 * Obtener id turno.
	 *
	 * @return el valor de string
	 */
	protected String obtenerIdTurno()
	{
		String  ls_idTurno;
		Entrega le_entrega;

		ls_idTurno     = null;
		le_entrega     = getEntrega();

		if(le_entrega != null)
			ls_idTurno = le_entrega.getIdTurno();

		return ls_idTurno;
	}

	/**
	 * Valida la propiedad listo para digitalizar.
	 *
	 * @param actd_tiposDocumentales de actd tipos documentales
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en listo para digitalizar
	 */
	private boolean isListoParaDigitalizar(Collection<TipoDocumental> actd_tiposDocumentales)
	{
		boolean lb_return;

		lb_return = CollectionUtils.isValidCollection(actd_tiposDocumentales);

		if(lb_return)
		{
			Iterator<TipoDocumental> litd_iterator;

			litd_iterator = actd_tiposDocumentales.iterator();

			if(litd_iterator != null)
			{
				while(litd_iterator.hasNext() && lb_return)
				{
					TipoDocumental ltd_temp;

					ltd_temp = litd_iterator.next();

					if(ltd_temp != null)
						lb_return = StringUtils.isValidString(ltd_temp.getIdCompletitud());
				}
			}
		}

		return lb_return;
	}

	/**
	 * Procesa el cargue de archivos pdf para el detalle de la entrega.
	 *
	 * @param afue_event Objeto contenedor del arreglo de bytes correspondiente al archivo cargado
	 * @param as_documento Identificador que permite separar el cargue de un archivo dependiendo de un proceso
	 * determinado
	 */
	private void cargarDocumentoPdf(FileUploadEvent afue_event, String as_documento)
	{
		try
		{
			String       ls_mensaje;
			UploadedFile luf_file;

			ls_mensaje     = null;
			luf_file       = afue_event.getFile();

			if(luf_file != null)
			{
				if(luf_file.getSize() <= 500000)
				{
					Collection<StreamedContent> lcsc_documentosTemp;
					Collection<StreamedContent> lcsc_documentosNotaDev;
					byte[]                      lba_pdfFile;
					StreamedContent             isc_uploadedFile;
					boolean                     lb_notaDevolutiva;

					lcsc_documentosTemp        = getDatosDocumentosCargados();
					lcsc_documentosNotaDev     = getDatosDocumentosNotaDevolutiva();
					lba_pdfFile                = luf_file.getContents();
					isc_uploadedFile           = null;
					lb_notaDevolutiva          = false;

					if(
					    (lba_pdfFile != null) && StringUtils.isValidString(luf_file.getFileName())
						    && luf_file.getFileName().toUpperCase().endsWith(ExtensionCommon.PDF_MAYUS_PUNTO)
					)
					{
						boolean lb_isEncrypted;
						int     li_lastTrailerIndex;
						String  ls_pdfContent;

						lb_isEncrypted = false;

						if(lba_pdfFile.length < 1)
							throw new B2BException(ErrorKeys.ARCHIVO_DANADO);

						for(StreamedContent lsc_streamed : lcsc_documentosTemp)
						{
							if(lsc_streamed != null)
							{
								if(luf_file.getFileName().equalsIgnoreCase(lsc_streamed.getName()))
									throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE_EXISTE);
							}
						}

						for(StreamedContent lsc_streamed : lcsc_documentosNotaDev)
						{
							if(lsc_streamed != null)
							{
								if(luf_file.getFileName().equalsIgnoreCase(lsc_streamed.getName()))
									throw new B2BException(ErrorKeys.ARCHIVO_NOMBRE_EXISTE);
							}
						}

						ls_pdfContent           = new String(lba_pdfFile);
						li_lastTrailerIndex     = ls_pdfContent.lastIndexOf("trailer");

						if((li_lastTrailerIndex >= 0) && (li_lastTrailerIndex < ls_pdfContent.length()))
						{
							String ls_partEncrypted;
							int    ls_firstEOFIndex;
							String ls_trailer;

							ls_partEncrypted     = ls_pdfContent.substring(li_lastTrailerIndex, ls_pdfContent.length());
							ls_firstEOFIndex     = ls_partEncrypted.indexOf("%%EOF");
							ls_trailer           = ls_partEncrypted.substring(0, ls_firstEOFIndex);

							if(ls_trailer.contains("/Encrypt"))
								lb_isEncrypted = true;
						}

						if(lb_isEncrypted)
						{
							isc_uploadedFile = null;
							throw new B2BException(ErrorKeys.ARCHIVO_PROTEGIDO);
						}

						InputStream lis_stream = new ByteArrayInputStream(lba_pdfFile);
						isc_uploadedFile = new DefaultStreamedContent(
							    lis_stream, luf_file.getContentType(), luf_file.getFileName()
							);

						if(StringUtils.isValidString(as_documento))
						{
							if(as_documento.equalsIgnoreCase("ND"))
							{
								Map<String, DocumentoAdicional> lmsba_temp;
								DocumentoAdicional              lda_documento;

								lmsba_temp        = getDocumentosAdicionales();
								lda_documento     = new DocumentoAdicional();

								lda_documento.setObservaciones(getObservacionesDocumentosCargados());
								lda_documento.setBytesDocumento(lba_pdfFile);

								lmsba_temp.put(TipoDocumentalCommon.NOTIFICACION, lda_documento);

								lb_notaDevolutiva = true;
							}
						}
						else
						{
							String ls_tipoDoc;
							ls_tipoDoc = getTipoDocumental();

							Map<String, DocumentoAdicional> lmsba_temp;
							DocumentoAdicional              lda_documento;

							lmsba_temp        = getDocumentosAdicionales();
							lda_documento     = new DocumentoAdicional();

							lda_documento.setObservaciones(getObservacionesDocumentosCargados());
							lda_documento.setBytesDocumento(lba_pdfFile);

							lmsba_temp.put(ls_tipoDoc, lda_documento);

							setDocumentosAdicionales(lmsba_temp);

							setTipoDocumental(null);
							setObservacionesDocumentosCargados(null);
						}

						if(!lb_notaDevolutiva)
						{
							lcsc_documentosTemp.add(isc_uploadedFile);

							setDatosDocumentosCargados(lcsc_documentosTemp);
						}
						else
						{
							lcsc_documentosNotaDev.add(isc_uploadedFile);

							setDatosDocumentosNotaDevolutiva(lcsc_documentosNotaDev);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_TAMANIO_ARCHIVO_PDF);
			}
			else
				ls_mensaje = "No se encontro un Archivo para procesar.";

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = "Procesamiento de archivo terminado.";

			addMessage("I", ls_mensaje);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
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
	}

	/**
	 * Interviene en entrega.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	private boolean intervieneEnEntrega()
	{
		String ls_interviene;

		ls_interviene = getIntervieneEnEntrega();

		return StringUtils.isValidString(ls_interviene) && ls_interviene.equalsIgnoreCase(EstadoCommon.S);
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
	 * Render botones.
	 */
	private void renderBotones()
	{
		renderBotones(false);
	}

	/**
	 * Validar interviniente seleccionado.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private boolean validarIntervinienteSeleccionado()
	    throws B2BException
	{
		Entrega le_entrega;
		boolean lb_intervinienteSeleccionado;

		le_entrega                       = getEntrega();
		lb_intervinienteSeleccionado     = false;

		try
		{
			if(le_entrega != null)
			{
				Collection<SolicitudInterviniente> lcsi_intervinientes;

				lcsi_intervinientes = le_entrega.getDatosIntervinientes();

				if(CollectionUtils.isValidCollection(lcsi_intervinientes))
				{
					Iterator<SolicitudInterviniente> lisi_iterator;

					lisi_iterator = lcsi_intervinientes.iterator();

					if(lisi_iterator != null)
					{
						while(lisi_iterator.hasNext() && !lb_intervinienteSeleccionado)
						{
							SolicitudInterviniente lsi_interviniente;

							lsi_interviniente                = lisi_iterator.next();
							lb_intervinienteSeleccionado     = (lsi_interviniente != null)
									&& lsi_interviniente.isSeleccionado();
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lb_intervinienteSeleccionado;
	}
}
