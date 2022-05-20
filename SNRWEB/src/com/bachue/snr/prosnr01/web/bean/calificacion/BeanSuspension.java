package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanAnalistaCopias;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanDesistimiento;
import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanTurnosRecepcion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanSuspension.
 *
 * @author garias
 */
@ManagedBean(name = "beanSuspension")
@SessionScoped
public class BeanSuspension extends BeanNotaDevolutiva implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanSuspension.class);

	/** Propiedad actd tipos documentales. */
	private Collection<TipoDocumental> actd_tiposDocumentales;

	/** Propiedad ictr medios comunicar. */
	private Collection<TipoRecepcion> ictr_mediosComunicar;

	/** Propiedad ictr medios notificar. */
	private Collection<TipoRecepcion> ictr_mediosNotificar;

	/** Propiedad irr registro remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad isc pdf prev. */
	private StreamedContent isc_pdfPrev;

	/** Propiedad is causal devolucion. */
	private String is_causalDevolucion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is medio comunicar interesado. */
	private String is_medioComunicarInteresado;

	/** Propiedad is medio notificar interesado. */
	private String is_medioNotificarInteresado;

	/** Propiedad is motivo tramite. */
	private String is_motivoTramite;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is proceso. */
	private String is_proceso;

	/** Propiedad is razon A negar. */
	private String is_razonANegar;

	/** Propiedad is respuesta solicitud desistimiento. */
	private String is_respuestaSolicitudDesistimiento;

	/** Propiedad is parametros. */
	private Suspension is_parametros;

	/** Propiedad isr suspension remote. */
	@EJB
	private SuspensionRemote isr_suspensionRemote;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad iba zip pdf. */
	private byte[] iba_zipPdf;

	/** Propiedad ib articulo 18. */
	private boolean ib_articulo18;

	/** Propiedad ib articulo 19. */
	private boolean ib_articulo19;

	/** Propiedad ib calidad actua. */
	private boolean ib_calidadActua;

	/** Propiedad ib deshabilitar campos por nit. */
	private boolean ib_deshabilitarCamposPorNit;

	/** Propiedad ib deshabilitar tipo documento. */
	private boolean ib_deshabilitarTipoDocumento;

	/** Propiedad ib deshabilitar tipo persona. */
	private boolean ib_deshabilitarTipoPersona;

	/** Propiedad ib documento. */
	private boolean ib_documento;

	/** Propiedad ib documento generado. */
	private boolean ib_documentoGenerado;

	/** Propiedad ib editar datos basicos. */
	private boolean ib_editarDatosBasicos;

	/** Propiedad ib editar datos contacto. */
	private boolean ib_editarDatosContacto;

	/** Propiedad ib editar direccion correspondencia. */
	private boolean ib_editarDireccionCorrespondencia;

	/** Propiedad ib editar direccion residencia. */
	private boolean ib_editarDireccionResidencia;

	/** Propiedad ib es analista copias. */
	private boolean ib_esAnalistaCopias;

	/** Propiedad ib es negar solicitud certificados. */
	private boolean ib_esNegarSolicitudCertificados;

	/** Propiedad ib es persona entidad juridica. */
	private boolean ib_esPersonaEntidadJuridica;

	/** Propiedad ib es procede no correccion. */
	private boolean ib_esProcedeNoCorreccion;

	/** Propiedad ib es solicitud documentacion. */
	private boolean ib_esSolicitudDocumentacion;

	/** Propiedad ib es suspension solicitud documentacion. */
	private boolean ib_esSuspensionSolicitudDocumentacion;

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

	/** Propiedad ib habilitar tel fijo co. */
	private boolean ib_habilitarTelFijoCo;

	/** Propiedad ib habilitar tel fijo no. */
	private boolean ib_habilitarTelFijoNo;

	/** Propiedad ib habilitar tel movil co. */
	private boolean ib_habilitarTelMovilCo;

	/** Propiedad ib habilitar tel movil no. */
	private boolean ib_habilitarTelMovilNo;

	/** Propiedad ib modificar. */
	private boolean ib_modificar;

	/** Propiedad ib proceso 45. */
	private boolean ib_proceso45;

	/** Propiedad ib razon social. */
	private boolean ib_razonSocial;

	/** Propiedad ib terminar. */
	private boolean ib_terminar;

	/** Propiedad ib zip generado. */
	private boolean ib_zipGenerado;

	/** Propiedad il etapa. */
	private long il_etapa;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de articulo 18.
	 *
	 * @param ab_b asigna el valor a la propiedad articulo 18
	 */
	public void setArticulo18(boolean ab_b)
	{
		ib_articulo18 = ab_b;
	}

	/**
	 * Valida la propiedad articulo 18.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en articulo 18
	 */
	public boolean isArticulo18()
	{
		return ib_articulo18;
	}

	/**
	 * Modifica el valor de articulo 19.
	 *
	 * @param ab_b asigna el valor a la propiedad articulo 19
	 */
	public void setArticulo19(boolean ab_b)
	{
		ib_articulo19 = ab_b;
	}

	/**
	 * Valida la propiedad articulo 19.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en articulo 19
	 */
	public boolean isArticulo19()
	{
		return ib_articulo19;
	}

	/**
	 * Modifica el valor de calidad actua.
	 *
	 * @param ab_b asigna el valor a la propiedad calidad actua
	 */
	public void setCalidadActua(boolean ab_b)
	{
		ib_calidadActua = ab_b;
	}

	/**
	 * Valida la propiedad calidad actua.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en calidad actua
	 */
	public boolean isCalidadActua()
	{
		return ib_calidadActua;
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
		return is_causalDevolucion;
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
	 * Modifica el valor de documento.
	 *
	 * @param ab_b asigna el valor a la propiedad documento
	 */
	public void setDocumento(boolean ab_b)
	{
		ib_documento = ab_b;
	}

	/**
	 * Valida la propiedad documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento
	 */
	public boolean isDocumento()
	{
		return ib_documento;
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
	 * Modifica el valor de editar datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad editar datos basicos
	 */
	public void setEditarDatosBasicos(boolean ab_b)
	{
		ib_editarDatosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad editar datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar datos basicos
	 */
	public boolean isEditarDatosBasicos()
	{
		return ib_editarDatosBasicos;
	}

	/**
	 * Modifica el valor de editar datos contacto.
	 *
	 * @param ab_b asigna el valor a la propiedad editar datos contacto
	 */
	public void setEditarDatosContacto(boolean ab_b)
	{
		ib_editarDatosContacto = ab_b;
	}

	/**
	 * Valida la propiedad editar datos contacto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar datos contacto
	 */
	public boolean isEditarDatosContacto()
	{
		return ib_editarDatosContacto;
	}

	/**
	 * Modifica el valor de editar direccion correspondencia.
	 *
	 * @param ab_b asigna el valor a la propiedad editar direccion correspondencia
	 */
	public void setEditarDireccionCorrespondencia(boolean ab_b)
	{
		ib_editarDireccionCorrespondencia = ab_b;
	}

	/**
	 * Valida la propiedad editar direccion correspondencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar direccion correspondencia
	 */
	public boolean isEditarDireccionCorrespondencia()
	{
		return ib_editarDireccionCorrespondencia;
	}

	/**
	 * Modifica el valor de editar direccion residencia.
	 *
	 * @param ab_b asigna el valor a la propiedad editar direccion residencia
	 */
	public void setEditarDireccionResidencia(boolean ab_b)
	{
		ib_editarDireccionResidencia = ab_b;
	}

	/**
	 * Valida la propiedad editar direccion residencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en editar direccion residencia
	 */
	public boolean isEditarDireccionResidencia()
	{
		return ib_editarDireccionResidencia;
	}

	/**
	 * Modifica el valor de es analista copias.
	 *
	 * @param ab_b asigna el valor a la propiedad es analista copias.
	 */
	public void setEsAnalistaCopias(boolean ab_b)
	{
		ib_esAnalistaCopias = ab_b;
	}

	/**
	 * Modifica el valor de es analista copias.
	 *
	 * @param ab_b asigna el valor a la propiedad es analista copias.
	 */
	public boolean isEsAnalistaCopias()
	{
		return ib_esAnalistaCopias;
	}

	/**
	 * @param Modifica el valor de la propiedad esNegarSolicitudCertificados por ab_b
	 */
	public void setEsNegarSolicitudCertificados(boolean ab_b)
	{
		this.ib_esNegarSolicitudCertificados = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esNegarSolicitudCertificados
	 */
	public boolean isEsNegarSolicitudCertificados()
	{
		return ib_esNegarSolicitudCertificados;
	}

	/**
	 * Modifica el valor de es persona entidad juridica.
	 *
	 * @param ab_b asigna el valor a la propiedad es persona entidad juridica
	 */
	public void setEsPersonaEntidadJuridica(boolean ab_b)
	{
		ib_esPersonaEntidadJuridica = ab_b;
	}

	/**
	 * Valida la propiedad es persona entidad juridica.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es persona entidad juridica
	 */
	public boolean isEsPersonaEntidadJuridica()
	{
		return ib_esPersonaEntidadJuridica;
	}

	/**
	 * Modifica el valor de es procede no correccion.
	 *
	 * @param ab_b asigna el valor a la propiedad es procede no correccion
	 */
	public void setEsProcedeNoCorreccion(boolean ab_b)
	{
		ib_esProcedeNoCorreccion = ab_b;
	}

	/**
	 * Valida la propiedad es procede no correccion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es procede no correccion
	 */
	public boolean isEsProcedeNoCorreccion()
	{
		return ib_esProcedeNoCorreccion;
	}

	/**
	 * Modifica el valor de es solicitud documentacion.
	 *
	 * @param ab_b asigna el valor a la propiedad es solicitud documentacion
	 */
	public void setEsSolicitudDocumentacion(boolean ab_b)
	{
		ib_esSolicitudDocumentacion = ab_b;
	}

	/**
	 * Valida la propiedad es solicitud documentacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es solicitud documentacion
	 */
	public boolean isEsSolicitudDocumentacion()
	{
		return ib_esSolicitudDocumentacion;
	}

	/**
	 * Modifica el valor de es suspension solicitud documentacion.
	 *
	 * @param ab_b asigna el valor a la propiedad es suspension solicitud documentacion
	 */
	public void setEsSuspensionSolicitudDocumentacion(boolean ab_b)
	{
		ib_esSuspensionSolicitudDocumentacion = ab_b;
	}

	/**
	 * Valida la propiedad es suspension solicitud documentacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en es suspension solicitud documentacion
	 */
	public boolean isEsSuspensionSolicitudDocumentacion()
	{
		return ib_esSuspensionSolicitudDocumentacion;
	}

	/**
	 * Modifica el valor de etapa.
	 *
	 * @param al_l asigna el valor a la propiedad etapa
	 */
	public void setEtapa(long al_l)
	{
		il_etapa = al_l;
	}

	/**
	 * Retorna el valor de etapa.
	 *
	 * @return el valor de etapa
	 */
	public long getEtapa()
	{
		return il_etapa;
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

	/** {@inheritdoc} */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/** {@inheritdoc} */
	public StreamedContent getImagen()
	{
		return isc_imagen;
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
	 * Modifica el valor de modificar.
	 *
	 * @param ab_b asigna el valor a la propiedad modificar
	 */
	public void setModificar(boolean ab_b)
	{
		ib_modificar = ab_b;
	}

	/**
	 * Valida la propiedad modificar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en modificar
	 */
	public boolean isModificar()
	{
		return ib_modificar;
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

	/** {@inheritdoc} */
	public void setObservaciones(String as_o)
	{
		is_observaciones = as_o;
	}

	/** {@inheritdoc} */
	public String getObservaciones()
	{
		return is_observaciones;
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
	 * Metodo encargado de cargar datos del interesado a notificar.
	 */
	public void cargarParametros()
	{
		setParametros(cargarDatosDelInteresadoANotificar());
	}

	/**
	 * Metodo encargado de cargar datos del interesado a notificar.
	 *
	 * @return Objeto de tipo <code>Suspension</code> que contiene los datos cargados.
	 */
	public Suspension cargarDatosDelInteresadoANotificar()
	{
		Suspension ls_return;

		ls_return = null;

		try
		{
			ls_return = findDataSuspensionOfTheProcedure(getTurnoHistoria());

			if(ls_return != null)
			{
				DatosDelInteresado lddi_datosInteresado;

				lddi_datosInteresado = ls_return.getDatosDelInteresado();

				if(lddi_datosInteresado != null)
				{
					PersonaDireccion lpd_correspondencia;
					PersonaDireccion lpd_residencia;

					lpd_correspondencia     = lddi_datosInteresado.getDireccionCorrespondencia();
					lpd_residencia          = lddi_datosInteresado.getDireccionResidencia();

					if((lpd_correspondencia != null) || (lpd_residencia != null))
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setForm(BeanRecepcionDocumentos.is_idForm);
						lbd_beanDireccion.setHabilitadoPorConsulta(true);
						lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
						lbd_beanDireccion.setDeshabilitarResidencia(true);
						lbd_beanDireccion.setDireccionCorrespondencia(lpd_correspondencia);
						lbd_beanDireccion.setDireccionResidencia(lpd_residencia);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			is_parametros = new Suspension();
			clh_LOGGER.error("getParametros", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Suspension getParametros()
	{
		if(is_parametros == null)
			is_parametros = cargarDatosDelInteresadoANotificar();

		return is_parametros;
	}

	/**
	 * Modifica el valor de pdf prev.
	 *
	 * @param asc_sc asigna el valor a la propiedad pdf prev
	 */
	public void setPdfPrev(StreamedContent asc_sc)
	{
		isc_pdfPrev = asc_sc;
	}

	/**
	 * Retorna el valor de pdf prev.
	 *
	 * @return el valor de pdf prev
	 */
	public StreamedContent getPdfPrev()
	{
		return isc_pdfPrev;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param as_s asigna el valor a la propiedad proceso
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Modifica el valor de Proceso 45.
	 *
	 * @param ab_b Objeto o Variable de tipo boolean que asigna un valor a la propiedad Proceso45
	 */
	public void setProceso45(boolean ab_b)
	{
		ib_proceso45 = ab_b;
	}

	/**
	 * Valida la propiedad proceso 45.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso 45
	 */
	public boolean isProceso45()
	{
		return ib_proceso45;
	}

	/**
	 * Modifica el valor de razon A negar.
	 *
	 * @param as_s asigna el valor a la propiedad razon A negar
	 */
	public void setRazonANegar(String as_s)
	{
		is_razonANegar = as_s;
	}

	/**
	 * Retorna el valor de razon A negar.
	 *
	 * @return el valor de razon A negar
	 */
	public String getRazonANegar()
	{
		return is_razonANegar;
	}

	/**
	 * Modifica el valor de razon social.
	 *
	 * @param ab_b asigna el valor a la propiedad razon social
	 */
	public void setRazonSocial(boolean ab_b)
	{
		ib_razonSocial = ab_b;
	}

	/**
	 * Valida la propiedad razon social.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en razon social
	 */
	public boolean isRazonSocial()
	{
		return ib_razonSocial;
	}

	/**
	 * Modifica el valor de respuesta solicitud desistimiento.
	 *
	 * @param as_s asigna el valor a la propiedad respuesta solicitud desistimiento
	 */
	public void setRespuestaSolicitudDesistimiento(String as_s)
	{
		is_respuestaSolicitudDesistimiento = as_s;
	}

	/**
	 * Retorna el valor de respuesta solicitud desistimiento.
	 *
	 * @return el valor de respuesta solicitud desistimiento
	 */
	public String getRespuestaSolicitudDesistimiento()
	{
		return is_respuestaSolicitudDesistimiento;
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
	 * Modifica el valor de tipos documentales.
	 *
	 * @param ac_c asigna el valor a la propiedad tipos documentales
	 */
	public void setTiposDocumentales(Collection<TipoDocumental> ac_c)
	{
		actd_tiposDocumentales = ac_c;
	}

	/**
	 * Retorna el valor de tipos documentales.
	 *
	 * @return el valor de tipos documentales
	 */
	public Collection<TipoDocumental> getTiposDocumentales()
	{
		if(!CollectionUtils.isValidCollection(actd_tiposDocumentales))
		{
			try
			{
				TipoDocumental lo_td;

				actd_tiposDocumentales     = new ArrayList<TipoDocumental>();
				lo_td                      = new TipoDocumental();

				if(isProceso45())
				{
					Collection<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> lctd_tiposDocumentales;

					lctd_tiposDocumentales = irr_referenceRemote.findAllTiposDocumentales(
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
						    ConstanteCommon.TIPO_DOC_ART19_CALIFICACION
						);

					if(CollectionUtils.isValidCollection(lctd_tiposDocumentales))
					{
						Iterator<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> litd_iterator;
						litd_iterator = lctd_tiposDocumentales.iterator();

						while(litd_iterator.hasNext())
						{
							com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_tipoDoc;
							ltd_tipoDoc = litd_iterator.next();

							lo_td.setIdTipoDocumental(ltd_tipoDoc.getIdTipoDocumental());

							actd_tiposDocumentales.add(lo_td);
						}
					}
				}
				else
					actd_tiposDocumentales.add(lo_td);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("getTiposDocumentales", lb2be_e);
				addMessage(lb2be_e);
			}
		}

		return actd_tiposDocumentales;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		if(ith_turnoHistoria == null)
			ith_turnoHistoria = new TurnoHistoria();

		return ith_turnoHistoria;
	}

	/**
	 * Modifica el valor de zip generado.
	 *
	 * @param ab_b asigna el valor a la propiedad zip generado
	 */
	public void setZipGenerado(boolean ab_b)
	{
		ib_zipGenerado = ab_b;
	}

	/**
	 * Valida la propiedad zip generado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en zip generado
	 */
	public boolean isZipGenerado()
	{
		return ib_zipGenerado;
	}

	/**
	 * Modifica el valor de zip pdf.
	 *
	 * @param aba_ba asigna el valor a la propiedad zip pdf
	 */
	public void setZipPdf(byte[] aba_ba)
	{
		iba_zipPdf = aba_ba;
	}

	/**
	 * Retorna el valor de zip pdf.
	 *
	 * @return el valor de zip pdf
	 */
	public byte[] getZipPdf()
	{
		return iba_zipPdf;
	}

	/**
	 * Método encargado de limpiar el formulario y dejar los valores por defecto cuando el usuario quiere regresar a la pantalla anterior.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionRegresar()
	    throws B2BException
	{
		FacesContext lfc_context;
		long         ll_idEtapa;
		String       ls_return;

		ll_idEtapa      = getEtapa();
		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
		{
			BeanTurnosRecepcion lbtr_bean;

			lbtr_bean = (BeanTurnosRecepcion)lfc_context.getApplication()
					                                        .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_TURNOS_RECEPCION, BeanTurnosRecepcion.class
					);

			lbtr_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS));
			lbtr_bean.clear();
			lbtr_bean.generarData();

			ls_return = NavegacionCommon.TURNOS_RESTITUCION;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
		{
			BeanDesistimiento lbd_bean;

			lbd_bean = (BeanDesistimiento)lfc_context.getApplication()
					                                     .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DESISTIMIENTO, BeanDesistimiento.class
					);

			lbd_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO));
			lbd_bean.clear();
			lbd_bean.generarData();

			ls_return = NavegacionCommon.DESISTIMIENTO;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
		{
			BeanDetalleAnalistaDeCertificadosEspeciales lbt_bean;
			lbt_bean = (BeanDetalleAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
					                                                               .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_ANALISTA_CERTIFICADOS,
					    BeanDetalleAnalistaDeCertificadosEspeciales.class
					);

			if(lbt_bean != null)
			{
				lbt_bean.setConfrontacion(false);
				lbt_bean.setInsertaMatricula(false);
				lbt_bean.setEliminaMatricula(false);
			}

			ls_return = NavegacionCommon.CERTIFICADOS_ESPECIALES;
		}
		else
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

			if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
				ls_return = NavegacionCommon.TURNOS;
			else
				ls_return = NavegacionCommon.DETALLE_ACTO;
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de habilitar checks de turnos vinculados en pantalla de suspension de tramites Art.18
	 *
	 * @param lrc_item correspondiente al valor del tipo de objeto RegistroCalificacion
	 */
	public void accionSeleccionTurno(RegistroCalificacion lrc_item)
	{
		if(lrc_item != null)
		{
			boolean lb_disable;
			String  ls_tipoTurno;

			lb_disable       = false;
			ls_tipoTurno     = lrc_item.getEstadoPredio();

			if(
			    StringUtils.isValidString(ls_tipoTurno)
				    && ls_tipoTurno.equalsIgnoreCase(IdentificadoresCommon.TURNO_INICIAL)
			)
			{
				if(lrc_item.isMatriculaSeleccionada())
					lb_disable = true;

				{
					Collection<RegistroCalificacion> lcrc_crc;

					lcrc_crc = getInfoTurnos();

					if(CollectionUtils.isValidCollection(lcrc_crc))
					{
						for(RegistroCalificacion lrc_iterator : lcrc_crc)
						{
							if(lrc_iterator != null)
							{
								String ls_tipoTurnoIterator;

								ls_tipoTurnoIterator = lrc_iterator.getEstadoPredio();

								if(
								    StringUtils.isValidString(ls_tipoTurnoIterator)
									    && ls_tipoTurnoIterator.equalsIgnoreCase(IdentificadoresCommon.TURNO_VINCULADO)
								)
								{
									lrc_iterator.setMatriculaSeleccionada(false);
									lrc_iterator.setMatriculasAdd(lb_disable);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Método encargado de realizar la logica de navegacion de suspension de tramites ART 18.
	 *
	 * @return Variable de tipo String que indica regla de navegación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String accionTerminarProceso()
	    throws B2BException
	{
		String ls_return;

		ls_return = accionTerminarProceso(getEtapa());
		clean(true);

		return ls_return;
	}

	/**
	 * Adds the tipo documental.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void addTipoDocumental()
	    throws B2BException
	{
		Collection<TipoDocumental> lctau_ctd;
		TipoDocumental             lo_td;

		lo_td         = new TipoDocumental();
		lctau_ctd     = getTiposDocumentales();

		if(!CollectionUtils.isValidCollection(lctau_ctd))
			lctau_ctd = new ArrayList<TipoDocumental>();

		lo_td.setAgregadoPantalla(true);
		lctau_ctd.add(lo_td);

		setTiposDocumentales(lctau_ctd);

		addMessage(MessagesKeys.REGISTRO_INSERTADO);

		PrimeFaces.current().ajax().update("fSuspension:globalMsg");
	}

	/**
	 * Actualiza los paneles para el ingreso de datos en interesados dependiendo del medio
	 * a comunicar seleccionado.
	 *
	 * @param as_medio Cadena de caracteres con la opción seleccionada en pantalla por el
	 * usuario
	 */
	public void cambioDeMedioComInteresado(String as_medio)
	{
		try
		{
			limpiarMedioCo();

			if(StringUtils.isValidString(as_medio))
			{
				Suspension ls_suspension;

				ls_suspension = isr_suspensionRemote.findDataSuspensionOfTheProcedure(
					    getTurnoHistoria(), isEsSolicitudDocumentacion(), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

				if(ls_suspension != null)
				{
					if(as_medio.equals("4") && (as_medio.equals("2") || as_medio.equals("3")))
						ls_suspension.getDatosDelInteresado().setDireccionResidencia(null);

					setMedioComunicarInteresado(as_medio);

					PrimeFaces lpf_current;
					lpf_current = PrimeFaces.current();

					if(
					    as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4")
						    || as_medio.equalsIgnoreCase("5")
					)
					{
						if(as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4"))
							setHabilitarCorreoCo(true);

						if(as_medio.equalsIgnoreCase("4") || as_medio.equalsIgnoreCase("5"))
						{
							setHabilitarTelFijoCo(true);
							setHabilitarTelMovilCo(true);
						}

						lpf_current.executeScript("PF('wvPanelDatosC').expand();");
					}
					else if(as_medio.equalsIgnoreCase("2"))
					{
						setHabilitarDireccionReCo(true);
						lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
					}

					Solicitud ls_solicitud;

					ls_solicitud = ls_suspension.getSolicitud2();

					if(ls_solicitud != null)
					{
						String ls_medioNot;
						ls_medioNot = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionNotificacion());

						if(ls_medioNot.equalsIgnoreCase("1") || ls_medioNot.equalsIgnoreCase("4"))
							lpf_current.executeScript("PF('wvPanelDatosC').expand();");
						else if(ls_medioNot.equalsIgnoreCase("2"))
							lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
						else if(ls_medioNot.equalsIgnoreCase("3"))
							lpf_current.executeScript("PF('wvPanelDireccionC').expand();");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambioDeMedioComInteresado", lb2be_e);
		}
	}

	/**
	 * Actualiza los paneles para el ingreso de datos en interesados dependiendo del medio
	 * a notificar seleccionado.
	 *
	 * @param as_medio Cadena de caracteres con la opción seleccionada en pantalla por el
	 * usuario
	 */
	public void cambioDeMedioNotInteresado(String as_medio)
	{
		try
		{
			limpiarMedioNo();

			if(StringUtils.isValidString(as_medio))
			{
				Suspension ls_suspension;

				ls_suspension = isr_suspensionRemote.findDataSuspensionOfTheProcedure(
					    getTurnoHistoria(), isEsSolicitudDocumentacion(), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

				if(ls_suspension != null)
				{
					if(as_medio.equals("4") && (as_medio.equals("2") || as_medio.equals("3")))
						ls_suspension.getDatosDelInteresado().setDireccionResidencia(null);

					setMedioNotificarInteresado(as_medio);

					PrimeFaces lpf_current;
					lpf_current = PrimeFaces.current();

					if(as_medio.equalsIgnoreCase("1") || as_medio.equalsIgnoreCase("4"))
					{
						setHabilitarCorreoNo(true);

						if(as_medio.equalsIgnoreCase("4"))
						{
							setHabilitarTelFijoNo(true);
							setHabilitarTelMovilNo(true);
						}

						lpf_current.executeScript("PF('wvPanelDatosC').expand();");
					}
					else if(as_medio.equalsIgnoreCase("2"))
					{
						setHabilitarDireccionReNo(true);
						lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
					}
					else if(as_medio.equalsIgnoreCase("3"))
					{
						setHabilitarDireccionCoNo(true);
						lpf_current.executeScript("PF('wvPanelDireccionC').expand();");
					}

					Solicitud ls_solicitud;

					ls_solicitud = ls_suspension.getSolicitud2();

					if(ls_solicitud != null)
					{
						String ls_medioCom;
						ls_medioCom = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionComunicacion());

						if(
						    ls_medioCom.equalsIgnoreCase("1") || ls_medioCom.equalsIgnoreCase("4")
							    || ls_medioCom.equalsIgnoreCase("5")
						)
							lpf_current.executeScript("PF('wvPanelDatosC').expand();");
						else if(ls_medioCom.equalsIgnoreCase("2"))
							lpf_current.executeScript("PF('wvPanelDireccionR').expand();");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cambioDeMedioComInteresado", lb2be_e);
		}
	}

	/**
	 * Cargar completitud documentales del turno respectivo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarInfoTiposDocumentalSuspension()
	    throws B2BException
	{
		try
		{
			setTiposDocumentales(null);

			Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

			lcacd_completitudDocumental = irr_registroRemote.findCompletitudDocumentalByIdTurno(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
			{
				for(AccCompletitudDocumental lacd_tmp : lcacd_completitudDocumental)
				{
					TipoDocumental ltd_tiposDocumentales;
					ltd_tiposDocumentales = new TipoDocumental();

					if(lacd_tmp != null)
					{
						String ls_idTipoDoc;
						String ls_observaciones;
						String ls_obligatoriedadTipoDoc;

						ls_observaciones             = lacd_tmp.getObservaciones();
						ls_idTipoDoc                 = lacd_tmp.getIdTipoDocumental();
						ls_obligatoriedadTipoDoc     = lacd_tmp.getObligatorioTipoDocumental();

						if(StringUtils.isValidString(ls_idTipoDoc))
							ltd_tiposDocumentales.setIdTipoDocumental(ls_idTipoDoc);

						if(StringUtils.isValidString(ls_observaciones))
							ltd_tiposDocumentales.setObservaciones(ls_observaciones);

						if(StringUtils.isValidString(ls_obligatoriedadTipoDoc))
						{
							if(ls_obligatoriedadTipoDoc.equalsIgnoreCase(EstadoCommon.S))
								ltd_tiposDocumentales.setSeleccionado(true);
							else
								ltd_tiposDocumentales.setSeleccionado(false);
						}
					}

					getTiposDocumentales().add(ltd_tiposDocumentales);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encaragdo de cargar los medios a notificar y a comunicar.
	 *
	 * @param ab_personaJuridica variable de tipo boolean que indica si la persona seleccionada es una persona juridica o no.
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
			clh_LOGGER.error("cargarMediosNotCom", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar oficios texto del turno respectivo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarOficiosTexto()
	    throws B2BException
	{
		String ls_idTurno;
		ls_idTurno = getIdTurno();

		if(StringUtils.isValidString(ls_idTurno))
		{
			OficiosTexto lot_oficiosTexto;
			lot_oficiosTexto = isr_suspensionRemote.buscarOficiosTextoPorTurno(
				    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lot_oficiosTexto != null)
			{
				Suspension ls_suspension;
				ls_suspension = getParametros();

				if(ls_suspension != null)
				{
					ls_suspension.setArticulo(lot_oficiosTexto.getArticulo());
					ls_suspension.setConsideracion(lot_oficiosTexto.getConsideracion());
					ls_suspension.setPertinencia(lot_oficiosTexto.getPertinencia());
					ls_suspension.setRazon1(lot_oficiosTexto.getRazon1());
					ls_suspension.setRazon2(lot_oficiosTexto.getRazon2());
				}
			}
		}
	}

	/**
	 * Método encargado de limpiar la informacion y dejarla por defecto.
	 *
	 * @param ab_clearAll correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException
	 */
	public void clean(boolean ab_clearAll)
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		if(ab_clearAll)
		{
			setTurnoHistoria(null);
			setMotivoTramite(null);
		}

		setEsProcedeNoCorreccion(false);
		setTiposDocumentales(null);
		setEsSolicitudDocumentacion(false);
		setRazonANegar(null);
		setMediosComunicar(null);
		setMediosNotificar(null);
		setImagen(null);
		setPdfPrev(null);
		setMedioComunicarInteresado(null);
		setMedioNotificarInteresado(null);
		setObservaciones(null);
		setProceso(null);
		setParametros(null);
		setZipPdf(null);
		setArticulo18(false);
		setArticulo19(false);
		setDocumentoGenerado(false);
		setEditarDatosBasicos(false);
		setEditarDatosContacto(false);
		lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
		setEditarDireccionCorrespondencia(false);
		lbd_beanDireccion.setDeshabilitarResidencia(true);
		setEditarDireccionResidencia(false);
		setHabilitarCorreoCo(false);
		setHabilitarCorreoNo(false);
		setHabilitarDireccionCoCo(false);
		setHabilitarDireccionCoNo(false);
		setHabilitarDireccionReCo(false);
		setHabilitarDireccionReNo(false);
		setHabilitarTelFijoCo(false);
		setHabilitarTelFijoNo(false);
		setHabilitarTelMovilCo(false);
		setHabilitarTelMovilNo(false);
		setTerminar(false);
		setIndVinculado(false);
		setInfoTurnos(null);
	}

	/**
	 * Método para eliminar un tipo documental.
	 *
	 * @param atd_tipoDoc Tipo documental a remover
	 */
	public void deleteTipoDocumental(com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental atd_tipoDoc)
	{
		deleteTipoDocumental(atd_tipoDoc, false);
	}

	/**
	 * Método para eliminar un tipo documental.
	 *
	 * @param atd_tipoDoc Tipo documental a remover
	 */
	public void deleteTipoDocumental(
	    com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental atd_tipoDoc, boolean ab_entrega
	)
	{
		try
		{
			Collection<TipoDocumental> lctd_tiposDocumentales;

			lctd_tiposDocumentales = getTiposDocumentales();

			if((lctd_tiposDocumentales != null) && (atd_tipoDoc != null))
			{
				if(ab_entrega && StringUtils.isValidString(atd_tipoDoc.getIdCompletitud()))
					ier_entregaRemote.eliminarTipoDocumental(
					    atd_tipoDoc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				lctd_tiposDocumentales.remove(atd_tipoDoc);
				addMessage(MessagesKeys.PROCESO_COMPLETADO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("deleteTipoDocumental", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de descargar documentos.
	 */
	public void descargarDocumentos()
	{
		try
		{
			byte[] lba_zip;

			lba_zip = getZipPdf();

			if(lba_zip != null)
			{
				InputStream lis_stream;

				lis_stream = new ByteArrayInputStream(lba_zip);

				setImagen(
				    new DefaultStreamedContent(
				        lis_stream, TipoContenidoCommon.ZIP, ConstanteCommon.NOMBRE_ZIP_GENERADO
				    )
				);

				setZipGenerado(true);
			}
			else
				throw new B2BException(ErrorKeys.NO_GENERO_ZIP);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("descargarDocumentos", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Editar datos contacto.
	 */
	public void editarDatosContacto()
	{
		setEditarDatosContacto(true);
	}

	/**
	 * Editar direccion correspondencia.
	 * @throws B2BException
	 */
	public void editarDireccionCorrespondencia()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		lbd_beanDireccion.setDeshabilitarCorrespondencia(false);

		setEditarDireccionCorrespondencia(true);
	}

	/**
	 * Editar direccion residencia.
	 * @throws B2BException
	 */
	public void editarDireccionResidencia()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		lbd_beanDireccion.setDeshabilitarResidencia(false);

		setEditarDireccionResidencia(true);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosTelefono()
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				DatosDelInteresado ldi_datosDelInteresado;

				ldi_datosDelInteresado = ls_parametros.getDatosDelInteresado();

				if(ldi_datosDelInteresado != null)
				{
					PersonaTelefono lpt_telefono;

					lpt_telefono = ldi_datosDelInteresado.getTelefonoFijo();

					if(lpt_telefono != null)
					{
						Departamento ld_parametros;

						ld_parametros = new Departamento();

						ld_parametros.setIdPais(lpt_telefono.getIdPais());

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

	/**
	 * Método encragado de generar los documentos solicitados.
	 */
	public void generarDocumentos()
	{
		try
		{
			generarDocumentos(false, ":fSuspension");
		}
		catch(B2BException lb2be_e)
		{
			setDocumentoGenerado(false);
			clh_LOGGER.error("generarDocumentos", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String generateRandomIdForNotCaching()
	{
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * Método encargado de ocultar el tipo de articulo a generar ART_18 o ART_19.
	 */
	public void ocultarPorTipo()
	{
		Suspension ls_suspension;
		ls_suspension = getParametros();

		if(ls_suspension != null)
		{
			String ls_tipo;
			ls_tipo = ls_suspension.getTipo();

			if(StringUtils.isValidString(ls_tipo))
			{
				if(ls_tipo.equalsIgnoreCase(Suspension.ART_18))
				{
					setArticulo18(true);
					setArticulo19(false);
				}
				else if(ls_tipo.equalsIgnoreCase(Suspension.ART_19))
				{
					setArticulo18(false);
					setArticulo19(true);
				}
				else
				{
					setArticulo18(false);
					setArticulo19(false);
				}
			}
			else
			{
				setArticulo18(false);
				setArticulo19(false);
			}

			ls_suspension.setArticulo(null);
			ls_suspension.setPertinencia(null);
			ls_suspension.setRazon1(null);
			ls_suspension.setRazon2(null);
			ls_suspension.setConsideracion(null);
		}
	}

	/**
	 * Método encargado de terminar el proceso para ART_18.
	 */
	public void terminarProceso()
	{
		terminarProceso(false, ":fSuspension");
	}

	/**
	 * Bloquea los SelectOneMenu de Tipo persona y Tipo documento si se selecciona
	 * la opción Juridica o NIT, respectivamente.
	 */
	public void validarTipoPersonaDocumento()
	{
		Suspension ls_parametros;

		ls_parametros = getParametros();

		if(ls_parametros != null)
		{
			DatosDelInteresado lddi_datosDelInteresado;

			lddi_datosDelInteresado = ls_parametros.getDatosDelInteresado();

			if(lddi_datosDelInteresado != null)
			{
				Persona lp_temp;

				lp_temp = lddi_datosDelInteresado.getPersona();

				if(lp_temp != null)
				{
					String ls_param;

					ls_param = lp_temp.getIdTipoPersona();

					if(ls_param != null)
					{
						boolean lb_j;

						lb_j = ls_param.equalsIgnoreCase(EstadoCommon.J);

						if(lb_j || ls_param.equalsIgnoreCase(IdentificadoresCommon.NIT))
						{
							setDeshabilitarCamposPorNit(true);

							lp_temp.setIdNaturalGenero(EstadoCommon.N);

							if(lb_j)
								lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
							else
							{
								setDeshabilitarTipoPersona(true);

								lp_temp.setIdTipoPersona(EstadoCommon.J);
							}
						}

						setDeshabilitarTipoDocumento(true);
						setEditarDatosBasicos(false);
						setRazonSocial(false);
						setCalidadActua(false);
						setRazonSocial(false);
						setDocumento(false);

						if(isModificar())
						{
							if(lb_j)
							{
								setDeshabilitarTipoDocumento(true);
								setEditarDatosBasicos(false);
								setRazonSocial(true);
								setCalidadActua(true);
								setDocumento(true);
							}
							else if(ls_param.equalsIgnoreCase(EstadoCommon.I))
							{
								setDocumento(true);
								setEditarDatosBasicos(true);
								setRazonSocial(true);
								setDeshabilitarTipoDocumento(false);
								setCalidadActua(true);
							}
							else if(ls_param.equalsIgnoreCase(EstadoCommon.N))
							{
								setDocumento(true);
								setEditarDatosBasicos(true);
								setRazonSocial(false);
								setDeshabilitarTipoDocumento(false);
								setCalidadActua(true);
							}
						}
					}
					else
					{
						String ls_tipoPersona;
						String ls_tipoDoc;
						String ls_genero;

						ls_tipoPersona     = StringUtils.getStringNotNull(ls_param);
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
				}
			}
		}
	}

	/**
	* Visualizar modificar.
	*/
	public void visualizarModificar()
	{
		setModificar(true);
		validarTipoPersonaDocumento();
	}

	/**
	 * Método encargado de realizar la logica de navegacion de suspension de tramites ART 18, ART 19 y proceso de recepcion de documentos.
	 *
	 * @param al_idEtapa correspondiente al valor del tipo de objeto long
	 * @return Variable de tipo String que indica regla de navegación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected String accionTerminarProceso(long al_idEtapa)
	    throws B2BException
	{
		FacesContext lfc_context;
		long         ll_idEtapa;
		String       ls_return;

		ll_idEtapa      = al_idEtapa;
		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
		{
			BeanTurnosRecepcion lbtr_bean;

			lbtr_bean = (BeanTurnosRecepcion)lfc_context.getApplication()
					                                        .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_TURNOS_RECEPCION, BeanTurnosRecepcion.class
					);

			lbtr_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS));
			lbtr_bean.clear();
			lbtr_bean.generarData();

			ls_return = NavegacionCommon.TURNOS_RESTITUCION;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
		{
			BeanDesistimiento lbd_bean;

			lbd_bean = (BeanDesistimiento)lfc_context.getApplication()
					                                     .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DESISTIMIENTO, BeanDesistimiento.class
					);

			lbd_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO));
			lbd_bean.clear();
			lbd_bean.generarData();

			ls_return = NavegacionCommon.DESISTIMIENTO;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
		{
			BeanCorreccion lbc_bean;

			lbc_bean = (BeanCorreccion)lfc_context.getApplication()
					                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
					);

			lbc_bean.clear();
			lbc_bean.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.ANALISIS_CORRECCION;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
		{
			BeanAnalistaCopias lbc_bean;

			lbc_bean = (BeanAnalistaCopias)lfc_context.getApplication()
					                                      .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_ANALISTA_COPIAS, BeanAnalistaCopias.class
					);

			lbc_bean.clear();
			lbc_bean.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.ANALISTA_DE_COPIAS;
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
		{
			BeanDetalleAnalistaDeCertificadosEspeciales lbt_bean;
			lbt_bean = (BeanDetalleAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
					                                                               .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_ANALISTA_CERTIFICADOS,
					    BeanDetalleAnalistaDeCertificadosEspeciales.class
					);

			if(lbt_bean != null)
			{
				lbt_bean.setConfrontacion(false);
				lbt_bean.setInsertaMatricula(false);
				lbt_bean.setEliminaMatricula(false);
			}

			ls_return = NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES;
		}
		else
		{
			BeanCalificacion lb_beanCalificacion;

			lb_beanCalificacion = (BeanCalificacion)lfc_context.getApplication()
					                                               .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
					);

			lb_beanCalificacion.clear();
			lb_beanCalificacion.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.CALIFICACION;
		}

		return ls_return;
	}

	/**
	 * Método encargado de generar los documentos para el proceso para ART_18, ART_19 y recepcion de documentos.
	 *
	 * @param ab_recepcionDocumentos Variable de tipo boolean que indica si el proceso es recepcion de documentos o no.
	 * @param as_idFormulario variable de tipo String que indica el formulario al cual se pintara color rojo si hay errores
	 * @throws B2BException
	 */
	protected void generarDocumentos(boolean ab_recepcionDocumentos, String as_idFormulario)
	    throws B2BException
	{
		try
		{
			Collection<Suspension> lcs_parametros;
			FacesContext           lfc_context;
			String                 ls_proceso;

			lcs_parametros     = new ArrayList<Suspension>();
			lfc_context        = FacesContext.getCurrentInstance();
			ls_proceso         = StringUtils.getStringNotNull(getProceso());

			if(ls_proceso.equals(ProcesoCommon.ID_PROCESO_39))
			{
				String ls_respuestaDesistimiento;

				ls_respuestaDesistimiento = getRespuestaSolicitudDesistimiento();

				if(!StringUtils.isValidString(ls_respuestaDesistimiento))
					throw new B2BException(ErrorKeys.ERROR_SIN_RESPUESTA_DESISTIMIENTO);
			}

			if(isIndVinculado())
			{
				Collection<RegistroCalificacion> lcrc_crc;

				lcrc_crc = getInfoTurnos();

				if(CollectionUtils.isValidCollection(lcrc_crc))
				{
					boolean lb_seleccionValida;

					lb_seleccionValida = false;

					for(RegistroCalificacion lrc_item : lcrc_crc)
					{
						if((lrc_item != null) && lrc_item.isMatriculaSeleccionada())
						{
							lcs_parametros.add(
							    validarSuspension(
							        ab_recepcionDocumentos, as_idFormulario,
							        new TurnoHistoria(lrc_item.getIdTurnoHistoria()), lfc_context, false
							    )
							);
							lb_seleccionValida = true;
						}
					}

					if(!lb_seleccionValida)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TURNO_SUSPENSION);
				}
			}
			else
				lcs_parametros.add(
				    validarSuspension(ab_recepcionDocumentos, as_idFormulario, getTurnoHistoria(), lfc_context, false)
				);

			if(CollectionUtils.isValidCollection(lcs_parametros))
			{
				Suspension ls_return;

				ls_return = isr_suspensionRemote.generarDocumentosSuspensiones(
					    lcs_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(ls_return != null)
				{
					setZipPdf(ls_return.getZipPdf());
					setDocumentoGenerado(true);
					setZipGenerado(false);

					PrimeFaces.current().ajax().update(as_idFormulario + ":globalMsg");
				}
			}

			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria = getTurnoHistoria();

			if(lth_turnoHistoria != null)
				setIdTurno(lth_turnoHistoria.getIdTurno());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSuspension", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de terminar el proceso para ART_18, ART_19 y recepcion de documentos.
	 *
	 * @param ab_recepcionDocumentos Variable de tipo boolean que indica si el proceso es recepcion de documentos o no.
	 * @param as_idFormulario variable de tipo String que indica el formulario al cual se pintara color rojo si hay errores
	 */
	protected void terminarProceso(boolean ab_recepcionDocumentos, String as_idFormulario)
	{
		try
		{
			Collection<Suspension> lcs_parametros;
			FacesContext           lfc_context;

			lcs_parametros     = new ArrayList<Suspension>();
			lfc_context        = FacesContext.getCurrentInstance();

			if(isIndVinculado())
			{
				Collection<RegistroCalificacion> lcrc_crc;

				lcrc_crc = getInfoTurnos();

				if(CollectionUtils.isValidCollection(lcrc_crc))
				{
					boolean lb_seleccionValida;

					lb_seleccionValida = false;

					for(RegistroCalificacion lrc_item : lcrc_crc)
					{
						if((lrc_item != null) && lrc_item.isMatriculaSeleccionada())
						{
							lcs_parametros.add(
							    validarSuspension(
							        ab_recepcionDocumentos, as_idFormulario,
							        new TurnoHistoria(lrc_item.getIdTurnoHistoria()), lfc_context, true
							    )
							);
							lb_seleccionValida = true;
						}
					}

					if(!lb_seleccionValida)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TURNO_SUSPENSION);
				}
			}
			else
				lcs_parametros.add(
				    validarSuspension(ab_recepcionDocumentos, as_idFormulario, getTurnoHistoria(), lfc_context, true)
				);

			if(CollectionUtils.isValidCollection(lcs_parametros))
			{
				Suspension lsu_datos;

				lsu_datos = isr_suspensionRemote.terminarProcesoSuspensiones(
					    lcs_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lsu_datos != null)
					clean(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDocumentoGenerado(false);
			clh_LOGGER.error("terminarProceso", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	private boolean isArt18()
	{
		boolean lb_proceso;
		boolean lb_motivoTramite;
		String  ls_proceso;
		String  ls_motivoTramite;

		ls_proceso           = getProceso();
		ls_motivoTramite     = getMotivoTramite();
		lb_proceso           = StringUtils.isValidString(ls_proceso);
		lb_motivoTramite     = StringUtils.isValidString(ls_motivoTramite);

		return !isEsAnalistaCopias()
			&& (!(((lb_proceso && ls_proceso.equalsIgnoreCase("43"))
			&& (lb_motivoTramite && ls_motivoTramite.equalsIgnoreCase("10")))
			|| (lb_proceso && ls_proceso.equalsIgnoreCase("39")))
			|| (StringUtils.isValidString(getRespuestaSolicitudDesistimiento())));
	}

	/**
	 * Método encargado de consultar la informacion referente a un caso de recepcion de documentos o ART_19.
	 *
	 * @param ath_param Objeto de tipo TurnoHistoria que contiene el turno historia a consultar
	 * @return devuelve el valor de Suspension
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private Suspension findDataSuspensionOfTheProcedure(TurnoHistoria ath_param)
	    throws B2BException
	{
		Suspension ls_s;

		ls_s = null;

		try
		{
			if(ath_param != null)
			{
				ls_s = isr_suspensionRemote.findDataSuspensionOfTheProcedure(
					    ath_param, isEsSolicitudDocumentacion() || isEsNegarSolicitudCertificados() || isArt18(),
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if((ls_s != null) && !isIndVinculado())
				{
					{
						Persona lp_p;

						lp_p = ls_s.getPersona();

						if(lp_p != null)
						{
							String ls_personaJuridica;
							String ls_idDocumentoTipo;

							ls_personaJuridica     = lp_p.getIdTipoPersona();
							ls_idDocumentoTipo     = lp_p.getIdDocumentoTipo();

							if(StringUtils.isValidString(ls_personaJuridica))
								cargarMediosNotCom(ls_personaJuridica.equalsIgnoreCase(EstadoCommon.J));

							if(StringUtils.isValidString(ls_idDocumentoTipo))
							{
								if(ls_idDocumentoTipo.equalsIgnoreCase(IdentificadoresCommon.NIT))
									setEsPersonaEntidadJuridica(true);
								else
									setEsPersonaEntidadJuridica(false);
							}
						}
					}

					{
						Solicitud ls_solicitud;

						ls_solicitud = ls_s.getSolicitud2();

						if(ls_solicitud != null)
						{
							String ls_idAutorizacionComunicacion;
							String ls_idAutorizacionNotificacion;

							ls_idAutorizacionComunicacion     = ls_solicitud.getIdAutorizacionComunicacion();
							ls_idAutorizacionNotificacion     = ls_solicitud.getIdAutorizacionNotificacion();

							if(StringUtils.isValidString(ls_idAutorizacionComunicacion))
								cambioDeMedioComInteresado(ls_idAutorizacionComunicacion);

							if(StringUtils.isValidString(ls_idAutorizacionNotificacion))
								cambioDeMedioNotInteresado(ls_idAutorizacionNotificacion);
						}
					}

					setEditarDatosBasicos(false);
					setEditarDireccionResidencia(false);
					setEditarDireccionCorrespondencia(false);
					setEditarDatosContacto(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_s = new Suspension();
			clh_LOGGER.error("findDataSuspensionOfTheProcedure", lb2be_e);
			throw lb2be_e;
		}

		return ls_s;
	}

	/**
	 * Método encargado de limpoar el medio a comunicar.
	 */
	private void limpiarMedioCo()
	{
		setHabilitarCorreoCo(false);
		setHabilitarTelFijoCo(false);
		setHabilitarTelMovilCo(false);
		setHabilitarDireccionReCo(false);
		setHabilitarDireccionCoCo(false);
	}

	/**
	 * Método encargado de limpoar el medio a notificar.
	 */
	private void limpiarMedioNo()
	{
		setHabilitarCorreoNo(false);
		setHabilitarTelFijoNo(false);
		setHabilitarTelMovilNo(false);
		setHabilitarDireccionReNo(false);
		setHabilitarDireccionCoNo(false);
	}

	/**
	 * Metodo encargado de validar el formulario de suspension de tramtes para ART_18, ART_19 y recepcion de documentos.
	 *
	 * @param ab_recepcionDocumentos Variable de tipo boolean que indica si el proceso es recepcion de documentos o no.
	 * @param as_idFormulario variable de tipo String que indica el formulario al cual se pintara color rojo si hay errores.
	 * @param ath_turnoHistoria Objeto de tipo TurnoHistoria que contiene el turno historia a consultar.
	 * @param afc_context Objeto de tipo FacesContext que premite repintar componentes de primefaces y estilos.
	 * @param ab_save Variable de tipo boolean que permite identificar la accion que el usuario intenta ejecutar.
	 * @return devuelve el valor de Suspension
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private Suspension validarSuspension(
	    boolean ab_recepcionDocumentos, String as_idFormulario, TurnoHistoria ath_turnoHistoria,
	    FacesContext afc_context, boolean ab_save
	)
	    throws B2BException
	{
		Suspension ls_return;

		ls_return = getParametros();

		try
		{
			if((ath_turnoHistoria != null) && (NumericUtils.isValidLong(ath_turnoHistoria.getIdTurnoHistoria(), 1L)))
			{
				if(ls_return != null)
				{
					ls_return.setEsSolicitudDocumentos(isEsSolicitudDocumentacion());
					ls_return.setEsSuspensionSolicitudDocumentos(isEsSuspensionSolicitudDocumentacion());
					ls_return.setEsProcedeNoCorreccion(isEsProcedeNoCorreccion());
					ls_return.setEsProceso45(isProceso45());
					ls_return.setEsAnalistaCopias(isEsAnalistaCopias());
					ls_return.setEsNegarSolicitudCertificados(isEsNegarSolicitudCertificados());

					String ls_observaciones;
					String ls_proceso;

					ls_proceso           = getProceso();
					ls_observaciones     = getObservaciones();

					if(StringUtils.isValidString(ls_observaciones))
					{
						ath_turnoHistoria.setIdUsuarioModificacion(getUserId());
						ath_turnoHistoria.setIpModificacion(getRemoteIpAddress());
						ath_turnoHistoria.setObservaciones(ls_observaciones);
					}

					ls_return.setTurnoHistoria(ath_turnoHistoria);

					if(ab_recepcionDocumentos)
					{
						if(StringUtils.isValidString(ls_proceso) && !isEsAnalistaCopias())
						{
							if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39))
							{
								ls_return.setEtapa(getEtapa());
								ls_return.setRespuestaDesistimiento(getRespuestaSolicitudDesistimiento());
								ls_return.setPertinencia(getCausalDevolucion());
							}
							else if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43))
							{
								String ls_idMotivoTramite;

								ls_idMotivoTramite = getMotivoTramite();

								if(StringUtils.isValidString(ls_idMotivoTramite))
								{
									ls_return.setEtapa(getEtapa());
									ls_return.setMotivoTramite(NumericUtils.getLong(ls_idMotivoTramite));
								}
								else
									throw new B2BException(ErrorKeys.ERROR_DESICION_ETAPA);
							}
							else if(
							    StringUtils.isValidString(ls_proceso)
								    && ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41)
							)
							{
								String ls_razonNegacion;

								ls_razonNegacion = getRazonANegar();

								validateStyles(
								    as_idFormulario + ":idRazonNegacion", afc_context, ls_razonNegacion, true
								);

								if(StringUtils.isValidString(ls_razonNegacion))
									ls_return.setRazonANegar(ls_razonNegacion);
								else
									throw new B2BException(ErrorKeys.ERROR_SELECCIONAR_RAZON_A_NEGAR_PRORROGA);
							}
							else
								ls_proceso = Suspension.ART_19;

							ls_return.setTipo(ls_proceso);
						}

						if(isEsSolicitudDocumentacion() || isProceso45())
						{
							Collection<TipoDocumental> lctd_tmp;

							lctd_tmp = getTiposDocumentales();

							if(CollectionUtils.isValidCollection(lctd_tmp))
							{
								for(TipoDocumental ltd_tipoDocumental : lctd_tmp)
								{
									if(ltd_tipoDocumental != null)
									{
										if(ltd_tipoDocumental.isSeleccionado())
										{
											String ls_observacionesDoc;
											ls_observacionesDoc = ltd_tipoDocumental.getObservaciones();

											ltd_tipoDocumental.setCampoRojo(
											    !StringUtils.isValidString(ls_observacionesDoc)
											);

											if(!StringUtils.isValidString(ls_observacionesDoc))
											{
												String ls_idTipoDoc;
												ls_idTipoDoc = ltd_tipoDocumental.getIdTipoDocumental();

												com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_tmp;
												ltd_tmp = new com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental();
												ltd_tmp.setIdTipoDocumental(ls_idTipoDoc);

												ltd_tmp = irr_referenceRemote.findByIdTipoDocumental(
													    ltd_tmp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
													);

												if(ltd_tmp != null)
												{
													Object[] loa_messageArgs;

													loa_messageArgs        = new String[1];
													loa_messageArgs[0]     = ltd_tmp.getNombre();

													throw new B2BException(
													    ErrorKeys.ERROR_OBSERVACIONES_DOCUMENTO_SELECCIONADO_OBLIGATORIAS,
													    loa_messageArgs
													);
												}
											}
										}
									}
								}

								ls_return.setTiposDocumentales(lctd_tmp);
							}
							else
								throw new B2BException(ErrorKeys.DEBE_AGREGAR_UN_TIPO_DOCUMENTAL);
						}

						{
							DatosDelInteresado lddi_ddi;

							lddi_ddi = ls_return.getDatosDelInteresado();

							if(lddi_ddi != null)
							{
								BeanDireccion lbd_beanDireccion;
								boolean       lb_focus;
								FacesContext  lfc_context;

								lbd_beanDireccion     = getBeanDireccion();
								lb_focus              = true;
								lfc_context           = FacesContext.getCurrentInstance();

								lddi_ddi.setEditarDatosBasicos(isEditarDatosBasicos());
								lddi_ddi.setEditarDireccionResidencia(isEditarDireccionResidencia());
								lddi_ddi.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
								lddi_ddi.setEditarDatosContacto(isEditarDatosContacto());

								ls_return.setDatosDelInteresado(lddi_ddi);

								if(isEditarDatosBasicos())
								{
									Persona lp_persona;
									lp_persona = lddi_ddi.getPersona();

									if(lp_persona != null)
									{
										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getIdTipoPersona();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idSOMTipoPersona", lfc_context,
												    ls_datoValidar, lb_focus
												);
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getIdDocumentoTipo();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idSOMTipoDoc", lfc_context, ls_datoValidar,
												    lb_focus
												);
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getNumeroDocumento();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idSOMNacionalidad", lfc_context,
												    ls_datoValidar, lb_focus
												);
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getIdPais();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idSOMNacionalidad", lfc_context,
												    ls_datoValidar, lb_focus
												);
										}

										{
											Solicitud ls_solicitud2;
											ls_solicitud2 = ls_return.getSolicitud2();

											if(ls_solicitud2 != null)
											{
												String ls_datoValidar;
												ls_datoValidar     = ls_solicitud2.getIdCalidadSolicitante();

												lb_focus = validateStyles(
													    ":fRecepcionDocumentos:idITCalidad", lfc_context, ls_datoValidar,
													    lb_focus
													);
											}
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getPrimerNombre();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idOlPNombre", lfc_context, ls_datoValidar,
												    lb_focus
												);
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getPrimerApellido();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idOlPApellido", lfc_context, ls_datoValidar,
												    lb_focus
												);
										}

										{
											String ls_datoValidar;
											ls_datoValidar     = lp_persona.getIdNaturalGenero();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idSOMGenero", lfc_context, ls_datoValidar,
												    lb_focus
												);
										}
									}
								}

								if(isEditarDireccionResidencia())
								{
									PersonaDireccion lpd_pd;

									lpd_pd = lddi_ddi.getDireccionResidencia();

									if(lpd_pd != null)
										lbd_beanDireccion.validarCamposDireccionResidencia();
								}

								if(isEditarDireccionCorrespondencia())
								{
									PersonaDireccion lpd_pd;

									lpd_pd = lddi_ddi.getDireccionCorrespondencia();

									if(lpd_pd != null)
										lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);
								}

								if(isEditarDatosContacto())
								{
									PersonaCorreoElectronico lpce_pcr;
									lpce_pcr = lddi_ddi.getCorreoElectronico();

									if(lpce_pcr != null)
									{
										{
											String ls_datoValidar;
											ls_datoValidar     = lpce_pcr.getCorreoElectronico();

											lb_focus = validateStyles(
												    ":fRecepcionDocumentos:idItEmail", lfc_context, ls_datoValidar,
												    lb_focus
												);
										}
									}
								}
							}
						}
					}
					else
						ls_return.setTipo(Suspension.ART_18);

					if(ab_save)
					{
						ls_proceso = getProceso();

						if(
						    StringUtils.isValidString(ls_proceso)
							    && ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43)
						)
						{
							String ls_idMotivoTramite;

							ls_idMotivoTramite = getMotivoTramite();

							if(StringUtils.isValidString(ls_idMotivoTramite))
								ls_return.setMotivoTramite(NumericUtils.getLong(ls_idMotivoTramite));
							else
								throw new B2BException(ErrorKeys.ERROR_DESICION_ETAPA);
						}

						ls_return.setProceso(ls_proceso);
						ls_return.setEtapa(getEtapa());
						ls_return.setEsPersonaEntidadJuridica(isEsPersonaEntidadJuridica());
					}

					{
						boolean lb_focus;

						lb_focus = true;

						if(ab_recepcionDocumentos)
						{
							if(StringUtils.isValidString(ls_proceso))
							{
								if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43))
								{
									String ls_idMotivoTramite;

									ls_idMotivoTramite = getMotivoTramite();

									if(StringUtils.isValidString(ls_idMotivoTramite))
									{
										long ll_motivoTramite;

										ll_motivoTramite = NumericUtils.getLong(ls_idMotivoTramite);

										if(ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO)
										{
											String ls_tmp;

											ls_tmp     = ls_return.getConsideracion();

											lb_focus = validateStyles(
												    as_idFormulario + ":idItaMotivoDevolucion", afc_context, ls_tmp,
												    lb_focus
												);

											if(!StringUtils.isValidString(ls_tmp))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_MOTIVO_DEVOLUCION);
											else
												ls_return.setConsideracion(ls_tmp);
										}
										else
										{
											String ls_tmp;

											ls_tmp     = ls_return.getConsideracion();

											lb_focus = validateStyles(
												    as_idFormulario + ":idItaCausalDevolucion", afc_context, ls_tmp,
												    lb_focus
												);

											if(!StringUtils.isValidString(ls_tmp))
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_CAUSAL_DEVOLUCION);
											else
												ls_return.setConsideracion(ls_tmp);
										}
									}
									else
									{
										String ls_tmp;

										ls_tmp     = ls_return.getConsideracion();

										lb_focus = validateStyles(
											    as_idFormulario + ":idItaConsideraciones", afc_context, ls_tmp, lb_focus
											);

										if(!StringUtils.isValidString(ls_tmp))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSIDERACIONES);
										else
											ls_return.setConsideracion(ls_tmp);
									}
								}
								else
								{
									String ls_tmp;

									ls_tmp     = ls_return.getConsideracion();

									lb_focus = validateStyles(
										    as_idFormulario + ":idItaConsideraciones", afc_context, ls_tmp, lb_focus
										);

									if(!StringUtils.isValidString(ls_tmp))
										throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSIDERACIONES);
									else
									{
										if(ls_tmp.length() < 100)
										{
											lb_focus = validateStyles(
												    as_idFormulario + ":idItaConsideraciones", afc_context, "", lb_focus
												);

											throw new B2BException(ErrorKeys.ERROR_CONSIDERACIONES_TAM_100);
										}

										ls_return.setConsideracion(ls_tmp);
									}

									if(isEsAnalistaCopias())
									{
										String ls_tmp2;

										ls_tmp2     = ls_return.getIdCausalNegacionCopias();

										lb_focus = validateStyles(
											    as_idFormulario + ":idSOMCausalNegacionCopias", afc_context, ls_tmp2,
											    lb_focus
											);

										if(!StringUtils.isValidString(ls_tmp2))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CAUSAL_NEGACION_COPIAS);
										else
											ls_return.setIdCausalNegacionCopias(ls_tmp2);
									}
								}
							}
							else if(
							    isEsSolicitudDocumentacion() || isEsNegarSolicitudCertificados()
								    || isEsProcedeNoCorreccion()
							)
							{
								String ls_tmp;

								ls_tmp     = ls_return.getConsideracion();

								lb_focus = validateStyles(
									    as_idFormulario + ":idItaConsideraciones", afc_context, ls_tmp, lb_focus
									);

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSIDERACIONES);
								else
									ls_return.setConsideracion(ls_tmp);

								if(isEsProcedeNoCorreccion())
									ls_return.setRazon1(ls_tmp);
							}
						}
						else
						{
							if(isEsSuspensionSolicitudDocumentacion())
							{
								Collection<TipoDocumental> lctd_tmp;

								lctd_tmp = getTiposDocumentales();

								if(CollectionUtils.isValidCollection(lctd_tmp))
								{
									for(TipoDocumental ltd_tipoDocumental : lctd_tmp)
									{
										if(ltd_tipoDocumental != null)
										{
											if(ltd_tipoDocumental.isSeleccionado())
											{
												String ls_observacionesDoc;
												ls_observacionesDoc = ltd_tipoDocumental.getObservaciones();

												ltd_tipoDocumental.setCampoRojo(
												    !StringUtils.isValidString(ls_observacionesDoc)
												);

												if(!StringUtils.isValidString(ls_observacionesDoc))
												{
													String ls_idTipoDoc;
													ls_idTipoDoc = ltd_tipoDocumental.getIdTipoDocumental();

													com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_tmp;
													ltd_tmp = new com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental();
													ltd_tmp.setIdTipoDocumental(ls_idTipoDoc);

													ltd_tmp = irr_referenceRemote.findByIdTipoDocumental(
														    ltd_tmp, getUserId(), getLocalIpAddress(),
														    getRemoteIpAddress()
														);

													if(ltd_tmp != null)
													{
														Object[] loa_messageArgs;

														loa_messageArgs        = new String[1];
														loa_messageArgs[0]     = ltd_tmp.getNombre();

														throw new B2BException(
														    ErrorKeys.ERROR_OBSERVACIONES_DOCUMENTO_SELECCIONADO_OBLIGATORIAS,
														    loa_messageArgs
														);
													}
												}
											}
										}
									}

									ls_return.setTiposDocumentales(lctd_tmp);
								}
							}

							{
								String ls_tmp;

								ls_tmp       = ls_return.getArticulo();
								lb_focus     = validateStyles(
									    as_idFormulario + ":idItaArticulo", afc_context, ls_tmp, lb_focus
									);

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_ARTICULO_VALIDO);
								else
									ls_return.setArticulo(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp       = ls_return.getPertinencia();
								lb_focus     = validateStyles(
									    as_idFormulario + ":idItaPertinencia", afc_context, ls_tmp, lb_focus
									);

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_PERTINENCIA_VALIDO);
								else
									ls_return.setPertinencia(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp       = ls_return.getRazon1();
								lb_focus     = validateStyles(
									    as_idFormulario + ":idItaRazon1", afc_context, ls_tmp, lb_focus
									);

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_1);
								else
									ls_return.setRazon1(ls_tmp);
							}

							{
								String ls_tmp;

								ls_tmp = ls_return.getRazon2();

								if(StringUtils.isValidString(ls_tmp))
									ls_return.setRazon2(ls_tmp);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSuspension", lb2be_e);
			throw lb2be_e;
		}

		return ls_return;
	}
}
