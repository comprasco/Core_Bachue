package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;

import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanGestionIntervinientes.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanGestionIntervinientes")
public class BeanGestionIntervinientes extends BeanGestionIntervinientesMasivo implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanGestionIntervinientes.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 435195204969309988L;

	/** Constante is_idForm. */
	public static final String is_idForm = "fDetailInter";

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ics data aprobacion. */
	private Collection<SolicitudInterviniente> icsi_dataAprobacion;

	/** Propiedad ictr medios comunicar inter. */
	private Collection<TipoRecepcion> ictr_mediosComunicarInter;

	/** Propiedad ictr medios notificar inter. */
	private Collection<TipoRecepcion> ictr_mediosNotificarInter;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona inter. */
	private Persona ip_personaInter;

	/** Propiedad ipce correo electronico inter. */
	private PersonaCorreoElectronico ipce_correoElectronicoInter;

	/** Propiedad ipd direccion correspondencia inter. */
	private PersonaDireccion ipd_direccionCorrespondenciaInter;

	/** Propiedad ipd direccion residencia inter. */
	private PersonaDireccion ipd_direccionResidenciaInter;

	/** Propiedad ipt telefono fijo inter. */
	private PersonaTelefono ipt_telefonoFijoInter;

	/** Propiedad ipt telefono movil inter. */
	private PersonaTelefono ipt_telefonoMovilInter;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir registro datos consultados inter. */
	private Registro ir_registroDatosConsultadosInter;

	/** Propiedad irc data interviniente. */
	private RegistroCalificacion irc_dataInterviniente;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isi detalle interviniente. */
	private SolicitudInterviniente isi_detalleInterviniente;

	/** Propiedad isi solicitud inter. */
	private SolicitudInterviniente isi_solicitudInter;

	/** Propiedad is documento interviniente. */
	private String is_documentoInterviniente;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id correo seleccion inter. */
	private String is_idCorreoSeleccionInter;

	/** Propiedad is id dir corr seleccion inter. */
	private String is_idDirCorrSeleccionInter;

	/** Propiedad is id dir res seleccion inter. */
	private String is_idDirResSeleccionInter;

	/** Propiedad is id persona seleccion inter. */
	private String is_idPersonaSeleccionInter;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tel fijo seleccion inter. */
	private String is_idTelFijoSeleccionInter;

	/** Propiedad is id tel mov seleccion inter. */
	private String is_idTelMovSeleccionInter;

	/** Propiedad is medio comunicar interviniente. */
	private String is_medioComunicarInterviniente;

	/** Propiedad is medio notificar interviniente. */
	private String is_medioNotificarInterviniente;

	/** Propiedad is misma direccion correspondencia inter. */
	private String is_mismaDireccionCorrespondenciaInter;

	/** Propiedad is primer apellido inter temp. */
	private String is_primerApellidoInterTemp;

	/** Propiedad is primer nombre inter temp. */
	private String is_primerNombreInterTemp;

	/** Propiedad is razon social inter temp. */
	private String is_razonSocialInterTemp;

	/** Propiedad is segundo apellido inter temp. */
	private String is_segundoApellidoInterTemp;

	/** Propiedad is segundo nombre inter temp. */
	private String is_segundoNombreInterTemp;

	/** Propiedad is tipo doc interviniente. */
	private String is_tipoDocInterviniente;

	/** Propiedad is turno. */
	private String is_turno;

	/** Propiedad ib accion btn edit per nit inter. */
	private boolean ib_accionBtnEditPerNitInter;

	/** Propiedad ib accion btn edit per normal inter. */
	private boolean ib_accionBtnEditPerNormalInter;

	/** Propiedad ib acto con englobe. */
	private boolean ib_actoConEnglobe;

	/** Propiedad ib datatable. */
	private boolean ib_datatable;

	/** Propiedad ib des habilitar razon. */
	private boolean ib_desHabilitarRazon;

	/** Propiedad ib deshabilitar campos por nit inter. */
	private boolean ib_deshabilitarCamposPorNitInter;

	/** Propiedad ib deshabilitar correo inter. */
	private boolean ib_deshabilitarCorreoInter;

	/** Propiedad ib deshabilitar correspondencia inter. */
	private boolean ib_deshabilitarCorrespondenciaInter;

	/** Propiedad ib deshabilitar datos basicos inter. */
	private boolean ib_deshabilitarDatosBasicosInter;

	/** Propiedad ib deshabilitar residencia inter. */
	private boolean ib_deshabilitarResidenciaInter;

	/** Propiedad ib deshabilitar tel fijo inter. */
	private boolean ib_deshabilitarTelFijoInter;

	/** Propiedad ib deshabilitar tel movil inter. */
	private boolean ib_deshabilitarTelMovilInter;

	/** Propiedad ib deshabilitar tipo documento inter. */
	private boolean ib_deshabilitarTipoDocumentoInter;

	/** Propiedad ib deshabilitar tipo persona inter. */
	private boolean ib_deshabilitarTipoPersonaInter;

	/** Propiedad ib editar interviniente. */
	private boolean ib_editarInterviniente;

	/** Propiedad ib habilita secuencia. */
	private boolean ib_habilitaSecuencia;

	/** Propiedad ib habilitar panel dir correspondencia inter. */
	private boolean ib_habilitarPanelDirCorrespondenciaInter;

	/** Propiedad ib habilitar panel dir residencia inter. */
	private boolean ib_habilitarPanelDirResidenciaInter;

	/** Propiedad ib mostrar correo. */
	private boolean ib_mostrarCorreo;

	/** Propiedad ib mostrar datos consulta. */
	private boolean ib_mostrarDatosConsulta;

	/** Propiedad ib mostrar desde detalle. */
	private boolean ib_mostrarDesdeDetalle;

	/** Propiedad ib mostrar direcciones correspondencia. */
	private boolean ib_mostrarDireccionesCorrespondencia;

	/** Propiedad ib mostrar direcciones residencia. */
	private boolean ib_mostrarDireccionesResidencia;

	/** Propiedad ib mostrar telefono fijo. */
	private boolean ib_mostrarTelefonoFijo;

	/** Propiedad ib mostrar telefono movil. */
	private boolean ib_mostrarTelefonoMovil;

	/** Propiedad ib nuevo interviniente. */
	private boolean ib_nuevoInterviniente;

	/** Propiedad ib pais inter correspondencia inter. */
	private boolean ib_paisInterCorrespondenciaInter;

	/** Propiedad ib pais inter residencia inter. */
	private boolean ib_paisInterResidenciaInter;

	/** Propiedad ib rendered interviniente. */
	private boolean ib_renderedInterviniente;

	/** Propiedad ib rendered mod interviniente. */
	private boolean ib_renderedModInterviniente;

	/** Propiedad ib rrr existe. */
	private boolean ib_rrrExiste;

	/** Propiedad is rendered habilita secuencia. */
	private boolean is_renderedHabilitaSecuencia;

	/**
	 * Modifica el valor de accion btn edit per nit inter.
	 *
	 * @param ab_b asigna el valor a la propiedad accion btn edit per nit inter
	 */
	public void setAccionBtnEditPerNitInter(boolean ab_b)
	{
		ib_accionBtnEditPerNitInter = ab_b;
	}

	/**
	 * Valida la propiedad accion btn edit per nit inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en accion btn edit per nit inter
	 */
	public boolean isAccionBtnEditPerNitInter()
	{
		return ib_accionBtnEditPerNitInter;
	}

	/**
	 * Modifica el valor de accion btn edit per normal inter.
	 *
	 * @param ab_b asigna el valor a la propiedad accion btn edit per normal inter
	 */
	public void setAccionBtnEditPerNormalInter(boolean ab_b)
	{
		ib_accionBtnEditPerNormalInter = ab_b;
	}

	/**
	 * Valida la propiedad accion btn edit per normal inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en accion btn edit per normal inter
	 */
	public boolean isAccionBtnEditPerNormalInter()
	{
		return ib_accionBtnEditPerNormalInter;
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

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de correo electronico inter.
	 *
	 * @param apce_pce asigna el valor a la propiedad correo electronico inter
	 */
	public void setCorreoElectronicoInter(PersonaCorreoElectronico apce_pce)
	{
		ipce_correoElectronicoInter = apce_pce;
	}

	/**
	 * Retorna el valor de correo electronico inter.
	 *
	 * @return el valor de correo electronico inter
	 */
	public PersonaCorreoElectronico getCorreoElectronicoInter()
	{
		if(ipce_correoElectronicoInter == null)
			ipce_correoElectronicoInter = new PersonaCorreoElectronico();

		return ipce_correoElectronicoInter;
	}

	/**
	 * Modifica el valor de data interviniente.
	 *
	 * @param arc_rc asigna el valor a la propiedad data interviniente
	 */
	public void setDataInterviniente(RegistroCalificacion arc_rc)
	{
		irc_dataInterviniente = arc_rc;
	}

	/**
	 * Retorna el valor de data interviniente.
	 *
	 * @return el valor de data interviniente
	 */
	public RegistroCalificacion getDataInterviniente()
	{
		if(irc_dataInterviniente == null)
			irc_dataInterviniente = new RegistroCalificacion();

		return irc_dataInterviniente;
	}

	/**
	 * Modifica el valor de datatable.
	 *
	 * @param ab_b asigna el valor a la propiedad datatable
	 */
	public void setDatatable(boolean ab_b)
	{
		ib_datatable = ab_b;
	}

	/**
	 * Valida la propiedad datatable.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datatable
	 */
	public boolean isDatatable()
	{
		return ib_datatable;
	}

	/**
	 * Valida la propiedad des habilitar razon.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en des habilitar razon
	 */
	public boolean isDesHabilitarRazon()
	{
		return ib_desHabilitarRazon;
	}

	/**
	 * Modifica el valor de deshabilitar campos por nit inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar campos por nit inter
	 */
	public void setDeshabilitarCamposPorNitInter(boolean ab_b)
	{
		ib_deshabilitarCamposPorNitInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar campos por nit inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar campos por nit inter
	 */
	public boolean isDeshabilitarCamposPorNitInter()
	{
		return ib_deshabilitarCamposPorNitInter;
	}

	/**
	 * Modifica el valor de deshabilitar correo inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar correo inter
	 */
	public void setDeshabilitarCorreoInter(boolean ab_b)
	{
		ib_deshabilitarCorreoInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar correo inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar correo inter
	 */
	public boolean isDeshabilitarCorreoInter()
	{
		return ib_deshabilitarCorreoInter;
	}

	/**
	 * Modifica el valor de deshabilitar correspondencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar correspondencia inter
	 */
	public void setDeshabilitarCorrespondenciaInter(boolean ab_b)
	{
		ib_deshabilitarCorrespondenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar correspondencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar correspondencia inter
	 */
	public boolean isDeshabilitarCorrespondenciaInter()
	{
		return ib_deshabilitarCorrespondenciaInter;
	}

	/**
	 * Modifica el valor de deshabilitar datos basicos inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar datos basicos inter
	 */
	public void setDeshabilitarDatosBasicosInter(boolean ab_b)
	{
		ib_deshabilitarDatosBasicosInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar datos basicos inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar datos basicos inter
	 */
	public boolean isDeshabilitarDatosBasicosInter()
	{
		return ib_deshabilitarDatosBasicosInter;
	}

	/**
	 * Modifica el valor de deshabilitar razon.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar razon
	 */
	public void setDeshabilitarRazon(boolean ab_b)
	{
		ib_desHabilitarRazon = ab_b;
	}

	/**
	 * Modifica el valor de deshabilitar residencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar residencia inter
	 */
	public void setDeshabilitarResidenciaInter(boolean ab_b)
	{
		ib_deshabilitarResidenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar residencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar residencia inter
	 */
	public boolean isDeshabilitarResidenciaInter()
	{
		return ib_deshabilitarResidenciaInter;
	}

	/**
	 * Modifica el valor de deshabilitar tel fijo inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tel fijo inter
	 */
	public void setDeshabilitarTelFijoInter(boolean ab_b)
	{
		ib_deshabilitarTelFijoInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tel fijo inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tel fijo inter
	 */
	public boolean isDeshabilitarTelFijoInter()
	{
		return ib_deshabilitarTelFijoInter;
	}

	/**
	 * Modifica el valor de deshabilitar tel movil inter.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar tel movil inter
	 */
	public void setDeshabilitarTelMovilInter(boolean ab_b)
	{
		ib_deshabilitarTelMovilInter = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar tel movil inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar tel movil inter
	 */
	public boolean isDeshabilitarTelMovilInter()
	{
		return ib_deshabilitarTelMovilInter;
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
	 * Modifica el valor de detalle interviniente.
	 *
	 * @param asi_si asigna el valor a la propiedad detalle interviniente
	 */
	public void setDetalleInterviniente(SolicitudInterviniente asi_si)
	{
		isi_detalleInterviniente = asi_si;
	}

	/**
	 * Retorna el valor de detalle interviniente.
	 *
	 * @return el valor de detalle interviniente
	 */
	public SolicitudInterviniente getDetalleInterviniente()
	{
		if(isi_detalleInterviniente == null)
			isi_detalleInterviniente = new SolicitudInterviniente();

		return isi_detalleInterviniente;
	}

	/**
	 * Modifica el valor de direccion correspondencia inter.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion correspondencia inter
	 */
	public void setDireccionCorrespondenciaInter(PersonaDireccion apd_pd)
	{
		ipd_direccionCorrespondenciaInter = apd_pd;
	}

	/**
	 * Retorna el valor de direccion correspondencia inter.
	 *
	 * @return el valor de direccion correspondencia inter
	 */
	public PersonaDireccion getDireccionCorrespondenciaInter()
	{
		if(ipd_direccionCorrespondenciaInter == null)
		{
			ipd_direccionCorrespondenciaInter = new PersonaDireccion();
			ipd_direccionCorrespondenciaInter.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionCorrespondenciaInter.setTipoDireccion(EstadoCommon.C);
		}

		return ipd_direccionCorrespondenciaInter;
	}

	/**
	 * Modifica el valor de direccion residencia inter.
	 *
	 * @param apd_pd asigna el valor a la propiedad direccion residencia inter
	 */
	public void setDireccionResidenciaInter(PersonaDireccion apd_pd)
	{
		ipd_direccionResidenciaInter = apd_pd;
	}

	/**
	 * Retorna el valor de direccion residencia inter.
	 *
	 * @return el valor de direccion residencia inter
	 */
	public PersonaDireccion getDireccionResidenciaInter()
	{
		if(ipd_direccionResidenciaInter == null)
		{
			ipd_direccionResidenciaInter = new PersonaDireccion();
			ipd_direccionResidenciaInter.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
			ipd_direccionResidenciaInter.setTipoDireccion("R");
		}

		return ipd_direccionResidenciaInter;
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
	 * Modifica el valor de habilitar panel dir correspondencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar panel dir correspondencia inter
	 */
	public void setHabilitarPanelDirCorrespondenciaInter(boolean ab_b)
	{
		ib_habilitarPanelDirCorrespondenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad habilitar panel dir correspondencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar panel dir correspondencia inter
	 */
	public boolean isHabilitarPanelDirCorrespondenciaInter()
	{
		return ib_habilitarPanelDirCorrespondenciaInter;
	}

	/**
	 * Modifica el valor de habilitar panel dir residencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar panel dir residencia inter
	 */
	public void setHabilitarPanelDirResidenciaInter(boolean ab_b)
	{
		ib_habilitarPanelDirResidenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad habilitar panel dir residencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar panel dir residencia inter
	 */
	public boolean isHabilitarPanelDirResidenciaInter()
	{
		return ib_habilitarPanelDirResidenciaInter;
	}

	/**
	 * Modifica el valor de id acto.
	 *
	 * @param as_s asigna el valor a la propiedad id acto
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna el valor de id acto.
	 *
	 * @return el valor de id acto
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de id anotacion predio.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion predio
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna el valor de id anotacion predio.
	 *
	 * @return el valor de id anotacion predio
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de id correo seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id correo seleccion inter
	 */
	public void setIdCorreoSeleccionInter(String as_s)
	{
		is_idCorreoSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id correo seleccion inter.
	 *
	 * @return el valor de id correo seleccion inter
	 */
	public String getIdCorreoSeleccionInter()
	{
		return is_idCorreoSeleccionInter;
	}

	/**
	 * Modifica el valor de id dir corr seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id dir corr seleccion inter
	 */
	public void setIdDirCorrSeleccionInter(String as_s)
	{
		is_idDirCorrSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id dir corr seleccion inter.
	 *
	 * @return el valor de id dir corr seleccion inter
	 */
	public String getIdDirCorrSeleccionInter()
	{
		return is_idDirCorrSeleccionInter;
	}

	/**
	 * Modifica el valor de id dir res seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id dir res seleccion inter
	 */
	public void setIdDirResSeleccionInter(String as_s)
	{
		is_idDirResSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id dir res seleccion inter.
	 *
	 * @return el valor de id dir res seleccion inter
	 */
	public String getIdDirResSeleccionInter()
	{
		return is_idDirResSeleccionInter;
	}

	/**
	 * Modifica el valor de id persona seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id persona seleccion inter
	 */
	public void setIdPersonaSeleccionInter(String as_s)
	{
		is_idPersonaSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id persona seleccion inter.
	 *
	 * @return el valor de id persona seleccion inter
	 */
	public String getIdPersonaSeleccionInter()
	{
		return is_idPersonaSeleccionInter;
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
	 * Modifica el valor de id tel fijo seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id tel fijo seleccion inter
	 */
	public void setIdTelFijoSeleccionInter(String as_s)
	{
		is_idTelFijoSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id tel fijo seleccion inter.
	 *
	 * @return el valor de id tel fijo seleccion inter
	 */
	public String getIdTelFijoSeleccionInter()
	{
		return is_idTelFijoSeleccionInter;
	}

	/**
	 * Modifica el valor de id tel mov seleccion inter.
	 *
	 * @param as_s asigna el valor a la propiedad id tel mov seleccion inter
	 */
	public void setIdTelMovSeleccionInter(String as_s)
	{
		is_idTelMovSeleccionInter = as_s;
	}

	/**
	 * Retorna el valor de id tel mov seleccion inter.
	 *
	 * @return el valor de id tel mov seleccion inter
	 */
	public String getIdTelMovSeleccionInter()
	{
		return is_idTelMovSeleccionInter;
	}

	/**
	 * Modifica el valor de medio comunicar interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad medio comunicar interviniente
	 */
	public void setMedioComunicarInterviniente(String as_s)
	{
		is_medioComunicarInterviniente = as_s;
	}

	/**
	 * Retorna el valor de medio comunicar interviniente.
	 *
	 * @return el valor de medio comunicar interviniente
	 */
	public String getMedioComunicarInterviniente()
	{
		return is_medioComunicarInterviniente;
	}

	/**
	 * Modifica el valor de medio notificar interviniente.
	 *
	 * @param as_s asigna el valor a la propiedad medio notificar interviniente
	 */
	public void setMedioNotificarInterviniente(String as_s)
	{
		is_medioNotificarInterviniente = as_s;
	}

	/**
	 * Retorna el valor de medio notificar interviniente.
	 *
	 * @return el valor de medio notificar interviniente
	 */
	public String getMedioNotificarInterviniente()
	{
		return is_medioNotificarInterviniente;
	}

	/**
	 * Modifica el valor de medios comunicar inter.
	 *
	 * @param actr_ctr asigna el valor a la propiedad medios comunicar inter
	 */
	public void setMediosComunicarInter(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_mediosComunicarInter = actr_ctr;
	}

	/**
	 * Retorna el valor de medios comunicar inter.
	 *
	 * @return el valor de medios comunicar inter
	 */
	public Collection<TipoRecepcion> getMediosComunicarInter()
	{
		return ictr_mediosComunicarInter;
	}

	/**
	 * Modifica el valor de medios notificar inter.
	 *
	 * @param actr_ctr asigna el valor a la propiedad medios notificar inter
	 */
	public void setMediosNotificarInter(Collection<TipoRecepcion> actr_ctr)
	{
		ictr_mediosNotificarInter = actr_ctr;
	}

	/**
	 * Retorna el valor de medios notificar inter.
	 *
	 * @return el valor de medios notificar inter
	 */
	public Collection<TipoRecepcion> getMediosNotificarInter()
	{
		return ictr_mediosNotificarInter;
	}

	/**
	 * Modifica el valor de misma direccion correspondencia inter.
	 *
	 * @param as_s asigna el valor a la propiedad misma direccion correspondencia inter
	 */
	public void setMismaDireccionCorrespondenciaInter(String as_s)
	{
		is_mismaDireccionCorrespondenciaInter = as_s;
	}

	/**
	 * Retorna el valor de misma direccion correspondencia inter.
	 *
	 * @return el valor de misma direccion correspondencia inter
	 */
	public String getMismaDireccionCorrespondenciaInter()
	{
		return is_mismaDireccionCorrespondenciaInter;
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
	 * Modifica el valor de mostrar desde detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar desde detalle
	 */
	public void setMostrarDesdeDetalle(boolean ab_b)
	{
		ib_mostrarDesdeDetalle = ab_b;
	}

	/**
	 * Valida la propiedad mostrar desde detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar desde detalle
	 */
	public boolean isMostrarDesdeDetalle()
	{
		return ib_mostrarDesdeDetalle;
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
	 * Modifica el valor de nuevo interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad nuevo interviniente
	 */
	public void setNuevoInterviniente(boolean ab_b)
	{
		ib_nuevoInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad nuevo interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nuevo interviniente
	 */
	public boolean isNuevoInterviniente()
	{
		return ib_nuevoInterviniente;
	}

	/**
	 * Modifica el valor de pais inter correspondencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter correspondencia inter
	 */
	public void setPaisInterCorrespondenciaInter(boolean ab_b)
	{
		ib_paisInterCorrespondenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad pais inter correspondencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter correspondencia inter
	 */
	public boolean isPaisInterCorrespondenciaInter()
	{
		return ib_paisInterCorrespondenciaInter;
	}

	/**
	 * Modifica el valor de pais inter residencia inter.
	 *
	 * @param ab_b asigna el valor a la propiedad pais inter residencia inter
	 */
	public void setPaisInterResidenciaInter(boolean ab_b)
	{
		ib_paisInterResidenciaInter = ab_b;
	}

	/**
	 * Valida la propiedad pais inter residencia inter.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en pais inter residencia inter
	 */
	public boolean isPaisInterResidenciaInter()
	{
		return ib_paisInterResidenciaInter;
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
	 * Modifica el valor de persona inter.
	 *
	 * @param ap_p asigna el valor a la propiedad persona inter
	 */
	public void setPersonaInter(Persona ap_p)
	{
		ip_personaInter = ap_p;
	}

	/**
	 * Retorna el valor de persona inter.
	 *
	 * @return el valor de persona inter
	 */
	public Persona getPersonaInter()
	{
		if(ip_personaInter == null)
			ip_personaInter = new Persona();

		ip_personaInter.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

		return ip_personaInter;
	}

	/**
	 * Modifica el valor de primer apellido inter temp.
	 *
	 * @param as_s asigna el valor a la propiedad primer apellido inter temp
	 */
	public void setPrimerApellidoInterTemp(String as_s)
	{
		is_primerApellidoInterTemp = as_s;
	}

	/**
	 * Retorna el valor de primer apellido inter temp.
	 *
	 * @return el valor de primer apellido inter temp
	 */
	public String getPrimerApellidoInterTemp()
	{
		return is_primerApellidoInterTemp;
	}

	/**
	 * Modifica el valor de primer nombre inter temp.
	 *
	 * @param as_s asigna el valor a la propiedad primer nombre inter temp
	 */
	public void setPrimerNombreInterTemp(String as_s)
	{
		is_primerNombreInterTemp = as_s;
	}

	/**
	 * Retorna el valor de primer nombre inter temp.
	 *
	 * @return el valor de primer nombre inter temp
	 */
	public String getPrimerNombreInterTemp()
	{
		return is_primerNombreInterTemp;
	}

	/**
	 * Modifica el valor de razon social inter temp.
	 *
	 * @param as_s asigna el valor a la propiedad razon social inter temp
	 */
	public void setRazonSocialInterTemp(String as_s)
	{
		is_razonSocialInterTemp = as_s;
	}

	/**
	 * Retorna el valor de razon social inter temp.
	 *
	 * @return el valor de razon social inter temp
	 */
	public String getRazonSocialInterTemp()
	{
		return is_razonSocialInterTemp;
	}

	/**
	 * Modifica el valor de registro datos consultados inter.
	 *
	 * @param ardci_rdci asigna el valor a la propiedad registro datos consultados inter
	 */
	public void setRegistroDatosConsultadosInter(Registro ardci_rdci)
	{
		ir_registroDatosConsultadosInter = ardci_rdci;
	}

	/**
	 * Retorna el valor de registro datos consultados inter.
	 *
	 * @return el valor de registro datos consultados inter
	 */
	public Registro getRegistroDatosConsultadosInter()
	{
		if(ir_registroDatosConsultadosInter == null)
			ir_registroDatosConsultadosInter = new Registro();

		return ir_registroDatosConsultadosInter;
	}

	/**
	 * Modifica el valor de rendered habilita secuencia.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered habilita secuencia
	 */
	public void setRenderedHabilitaSecuencia(boolean ab_b)
	{
		is_renderedHabilitaSecuencia = ab_b;
	}

	/**
	 * Valida la propiedad rendered habilita secuencia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered habilita secuencia
	 */
	public boolean isRenderedHabilitaSecuencia()
	{
		return is_renderedHabilitaSecuencia;
	}

	/**
	 * Modifica el valor de rendered interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered interviniente
	 */
	public void setRenderedInterviniente(boolean ab_b)
	{
		ib_renderedInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad rendered interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered interviniente
	 */
	public boolean isRenderedInterviniente()
	{
		return ib_renderedInterviniente;
	}

	/**
	 * Modifica el valor de rendered mod interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered mod interviniente
	 */
	public void setRenderedModInterviniente(boolean ab_b)
	{
		ib_renderedModInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad rendered mod interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered mod interviniente
	 */
	public boolean isRenderedModInterviniente()
	{
		return ib_renderedModInterviniente;
	}

	/**
	 * Modifica el valor de rrr existe.
	 *
	 * @param rrrExiste asigna el valor a la propiedad rrr existe
	 */
	public void setRrrExiste(boolean rrrExiste)
	{
		ib_rrrExiste = rrrExiste;
	}

	/**
	 * Valida la propiedad rrr existe.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rrr existe
	 */
	public boolean isRrrExiste()
	{
		return ib_rrrExiste;
	}

	/**
	 * Modifica el valor de segundo apellido inter temp.
	 *
	 * @param as_s asigna el valor a la propiedad segundo apellido inter temp
	 */
	public void setSegundoApellidoInterTemp(String as_s)
	{
		is_segundoApellidoInterTemp = as_s;
	}

	/**
	 * Retorna el valor de segundo apellido inter temp.
	 *
	 * @return el valor de segundo apellido inter temp
	 */
	public String getSegundoApellidoInterTemp()
	{
		return is_segundoApellidoInterTemp;
	}

	/**
	 * Modifica el valor de segundo nombre inter temp.
	 *
	 * @param as_s asigna el valor a la propiedad segundo nombre inter temp
	 */
	public void setSegundoNombreInterTemp(String as_s)
	{
		is_segundoNombreInterTemp = as_s;
	}

	/**
	 * Retorna el valor de segundo nombre inter temp.
	 *
	 * @return el valor de segundo nombre inter temp
	 */
	public String getSegundoNombreInterTemp()
	{
		return is_segundoNombreInterTemp;
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
	 * Modifica el valor de telefono fijo inter.
	 *
	 * @param apt_pt asigna el valor a la propiedad telefono fijo inter
	 */
	public void setTelefonoFijoInter(PersonaTelefono apt_pt)
	{
		ipt_telefonoFijoInter = apt_pt;
	}

	/**
	 * Retorna el valor de telefono fijo inter.
	 *
	 * @return el valor de telefono fijo inter
	 */
	public PersonaTelefono getTelefonoFijoInter()
	{
		if(ipt_telefonoFijoInter == null)
		{
			ipt_telefonoFijoInter = new PersonaTelefono();
			ipt_telefonoFijoInter.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoFijoInter;
	}

	/**
	 * Modifica el valor de telefono movil inter.
	 *
	 * @param apt_pt asigna el valor a la propiedad telefono movil inter
	 */
	public void setTelefonoMovilInter(PersonaTelefono apt_pt)
	{
		ipt_telefonoMovilInter = apt_pt;
	}

	/**
	 * Retorna el valor de telefono movil inter.
	 *
	 * @return el valor de telefono movil inter
	 */
	public PersonaTelefono getTelefonoMovilInter()
	{
		if(ipt_telefonoMovilInter == null)
		{
			ipt_telefonoMovilInter = new PersonaTelefono();
			ipt_telefonoMovilInter.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ipt_telefonoMovilInter;
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
	 * Accion boton editar persona inter.
	 *
	 * @param ap_p correspondiente al valor del tipo de objeto Persona
	 */
	public void accionBotonEditarPersonaInter(Persona ap_p)
	{
		if(ap_p != null)
		{
			cargarDatosPersonalesInter(ap_p);

			Persona lp_persona;
			lp_persona = getPersonaInter();

			if(lp_persona != null)
			{
				String ls_tipoDoc;
				String ls_tipoPersona;

				ls_tipoDoc         = StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo());
				ls_tipoPersona     = StringUtils.getStringNotNull(lp_persona.getIdTipoPersona());

				if(ls_tipoDoc.equals(IdentificadoresCommon.NIT) || ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J))
					setAccionBtnEditPerNitInter(true);
				else
					setAccionBtnEditPerNormalInter(true);

				setDeshabilitarCorreoInter(false);
				setPrimerNombreInterTemp(lp_persona.getPrimerNombre());
				setSegundoNombreInterTemp(lp_persona.getSegundoNombre());
				setPrimerApellidoInterTemp(lp_persona.getPrimerApellido());
				setSegundoApellidoInterTemp(lp_persona.getSegundoApellido());
				setRazonSocialInterTemp(lp_persona.getRazonSocial());
			}
		}
	}

	/**
	 * Accion salvar.
	 * @throws B2BException
	 */
	public void accionSalvar()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		try
		{
			Registro lr_registro;
			boolean  lb_individual;

			lr_registro       = new Registro();
			lb_individual     = getPreguntaMasivoIntervinientes().equalsIgnoreCase("ind");

			if(lb_individual)
			{
				lr_registro.setPersona(getPersonaInter());
				lr_registro.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lr_registro.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lr_registro.setTelefonoFijo(getTelefonoFijoInter());
				lr_registro.setTelefonoMovil(getTelefonoMovilInter());
				lr_registro.setPersonaCorreoElectronico(getCorreoElectronicoInter());
				lr_registro.setSolicitudInterviniente(getSolicitudInter());

				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(getIdSolicitud());

					lr_registro.setSolicitud(ls_solicitud);
				}

				validarCamposInterviniente(lr_registro);

				boolean lb_insertarPersona;
				boolean lb_editarPorNit;
				boolean lb_editarPorNormal;
				Persona lp_persona;

				lb_insertarPersona     = false;
				lb_editarPorNit        = isAccionBtnEditPerNitInter();
				lb_editarPorNormal     = isAccionBtnEditPerNormalInter();
				lp_persona             = getPersonaInter();

				if(lp_persona != null)
				{
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

							ls_primerNombreOri        = StringUtils.getStringNotNull(getPrimerNombreInterTemp());
							ls_segundoNombreOri       = StringUtils.getStringNotNull(getSegundoNombreInterTemp());
							ls_primerApellidoOri      = StringUtils.getStringNotNull(getPrimerApellidoInterTemp());
							ls_segundoApellidoOri     = StringUtils.getStringNotNull(getSegundoApellidoInterTemp());
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

							ls_razonSocialOri     = StringUtils.getStringNotNull(getRazonSocialInterTemp());
							ls_razonSocialMod     = StringUtils.getStringNotNull(lp_persona.getRazonSocial());

							if(!ls_razonSocialOri.equalsIgnoreCase(ls_razonSocialMod))
								lb_insertarPersona = true;
						}
					}
				}

				lr_registro.setPersonaCargada(lb_insertarPersona);

				lr_registro = irr_registroRemote.salvarInterviniente(
					    lr_registro, getUserId(), false, getLocalIpAddress(), getRemoteIpAddress(), false
					);

				if(lr_registro != null)
				{
					setPersonaInter(lr_registro.getPersona());
					lbd_beanDireccion.setDireccionResidencia(lr_registro.getDireccionResidencia());
					setDireccionResidenciaInter(lr_registro.getDireccionResidencia());
					lbd_beanDireccion.setDireccionCorrespondencia(lr_registro.getDireccionCorrespondencia());
					setDireccionCorrespondenciaInter(lr_registro.getDireccionCorrespondencia());
					setTelefonoFijoInter(lr_registro.getTelefonoFijo());
					setTelefonoMovilInter(lr_registro.getTelefonoMovil());
					setCorreoElectronicoInter(lr_registro.getPersonaCorreoElectronico());
					setSolicitudInter(lr_registro.getSolicitudInterviniente());

					RegistroCalificacion lrc_registroCalificacion;

					lrc_registroCalificacion = getDataInterviniente();

					if(lrc_registroCalificacion != null)
					{
						lrc_registroCalificacion.setIdPersona(lr_registro.getIdPersona());
						lrc_registroCalificacion.setUserId(getUserId());

						SolicitudInterviniente lsi_solRol;
						lsi_solRol = lr_registro.getSolicitudInterviniente();

						if(lsi_solRol != null)
							lrc_registroCalificacion.setRolPersona(
							    StringUtils.getStringNotNull(lsi_solRol.getRolPersona())
							);

						lrc_registroCalificacion.setTurno(getTurno());

						irr_calificacionRemote.gestionPredioCiudadano(
						    lrc_registroCalificacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);
					}

					limpiarIntervinientes();
					limpiarCamposIntervinientes();
					clear();
				}

				if(lb_insertarPersona)
				{
					setAccionBtnEditPerNitInter(false);
					setAccionBtnEditPerNormalInter(false);
				}
			}
			else
			{
				if(getResultadoCargueIntervinientes() == null)
				{
					Collection<SolicitudInterviniente> lcsi_datos;

					lcsi_datos = getTablaSolicitudInterviniente();

					if(CollectionUtils.isValidCollection(lcsi_datos))
					{
						for(SolicitudInterviniente lsi_si : lcsi_datos)
						{
							if(lsi_si != null)
							{
								Persona lp_persona;

								lp_persona = lsi_si.getPersona();

								if(lp_persona != null)
								{
									if(getDataInterviniente() != null)
									{
										RegistroCalificacion lrc_registroCalificacion;

										lrc_registroCalificacion = new RegistroCalificacion(lsi_si);

										lrc_registroCalificacion.setUserId(getUserId());
										lrc_registroCalificacion.setTurno(getTurno());
										lrc_registroCalificacion.setIdAnotacion(
										    getDataInterviniente().getIdAnotacion()
										);
										lrc_registroCalificacion.setIdAnotacionPredio(
										    getDataInterviniente().getIdAnotacionPredio()
										);
										lrc_registroCalificacion.setIdCirculo(getDataInterviniente().getIdCirculo());
										lrc_registroCalificacion.setIdMatricula(
										    getDataInterviniente().getIdMatricula()
										);

										irr_calificacionRemote.gestionPredioCiudadano(
										    lrc_registroCalificacion, getUserId(), getLocalIpAddress(),
										    getRemoteIpAddress()
										);
									}
								}
							}
						}

						limpiarIntervinientes();
						limpiarCamposIntervinientes();
						clear();
					}
					else
						throw new B2BException(ErrorKeys.DEBE_AGREGAR_INTERVINIENTE);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			setDeshabilitarCorrespondenciaInter(false);
			lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Accion volver.
	 * @throws B2BException
	 */
	public void accionVolver()
	    throws B2BException
	{
		limpiarCamposIntervinientes();
		clear();
	}

	/**
	 * Actualizar direccion correspondencia inter.
	 */
	public void actualizarDireccionCorrespondenciaInter()
	{
	}

	/**
	 * Actualizar direccion residencia inter.
	 */
	public void actualizarDireccionResidenciaInter()
	{
	}

	/**
	 * Actulizar interviniente.
	 */
	public void actualizarInterviniente()
	{
		SolicitudInterviniente lrc_rc;

		lrc_rc = getDetalleInterviniente();

		if(lrc_rc != null)
		{
			RegistroCalificacion   lrg_registroCalificacion;
			SolicitudInterviniente lsi_solicitud;

			lrg_registroCalificacion     = new RegistroCalificacion(lrc_rc);
			lsi_solicitud                = getSolicitudInter();

			lrg_registroCalificacion.setUserId(getUserId());
			lrg_registroCalificacion.setIpAddress(getRemoteIpAddress());

			if(lsi_solicitud != null)
				lrc_rc.setIdSolicitud(lsi_solicitud.getIdSolicitud());

			try
			{
				irr_calificacionRemote.modificarCiudadano(lrg_registroCalificacion);
				setRenderedModInterviniente(false);
				setDetalleInterviniente(null);
				dataIntervinientes();
				setEditarInterviniente(false);

				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("fDetailInter:idGrowl");
			}
			catch(B2BException lbe_lbe)
			{
				dataIntervinientes();
				addMessage(lbe_lbe);
			}
		}
	}

	/**
	 * Cambiar departamento correspondencia inter.
	 */
	public void cambiarDepartamentoCorrespondenciaInter()
	{
		findMunicipioCorrespondenciaInter();
	}

	/**
	 * Cambiar departamento residencia inter.
	 */
	public void cambiarDepartamentoResidenciaInter()
	{
		findMunicipioResidenciaInter();
	}

	/**
	 * Cambiar direccion correspondencia inter.
	 * @throws B2BException
	 */
	public void cambiarDireccionCorrespondenciaInter()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;
		String        ls_mismaDir;

		lbd_beanDireccion     = getBeanDireccion();
		ls_mismaDir           = getMismaDireccionCorrespondenciaInter();

		setMismaDireccionCorrespondenciaInter(ls_mismaDir);

		lbd_beanDireccion.setMismaDireccionCorrespondencia(ls_mismaDir);
		lbd_beanDireccion.cambiarDireccionCorrespondencia();
	}

	/**
	 * Cambiar pais correspondencia inter.
	 */
	public void cambiarPaisCorrespondenciaInter()
	{
	}

	/**
	 * Cambiar pais residencia inter.
	 */
	public void cambiarPaisResidenciaInter()
	{
	}

	/**
	 * Cambiar pais telefono inter.
	 */
	public void cambiarPaisTelefonoInter()
	{
		PersonaTelefono lpt_telefono;
		lpt_telefono = getTelefonoFijoInter();

		if(lpt_telefono != null)
		{
			String ls_pais;
			ls_pais = lpt_telefono.getIdPais();

			if(
			    !StringUtils.isValidString(ls_pais)
				    || !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
			)
			{
				setPaisInterCorrespondenciaInter(true);
				lpt_telefono.setIdDepartamento(null);
			}
		}

		findDepartamentosCorrespondenciaInter();
		findMunicipioCorrespondenciaInter();
	}

	/**
	 * Cambio de medio com interviniente.
	 *
	 * @param as_medio correspondiente al valor del tipo de objeto String
	 * @throws B2BException
	 */
	public void cambioDeMedioComInterviniente(String as_medio)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_medio))
		{
			String ls_temp;

			ls_temp = getMedioComunicarInterviniente();

			if(!StringUtils.isValidString(ls_temp))
				ls_temp = as_medio;

			if(as_medio.equals("4") && (ls_temp.equals("2") || ls_temp.equals("3")))
			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				lbd_beanDireccion.setDireccionResidencia(null);
				setDireccionResidenciaInter(null);
			}

			setMedioComunicarInterviniente(ls_temp);
		}
	}

	/**
	 * Cambio de medio not interviniente.
	 *
	 * @param as_medio correspondiente al valor del tipo de objeto String
	 * @throws B2BException
	 */
	public void cambioDeMedioNotInterviniente(String as_medio)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_medio))
		{
			String ls_temp;

			ls_temp = getMedioNotificarInterviniente();

			if(!StringUtils.isValidString(ls_temp))
				ls_temp = as_medio;

			if(as_medio.equals("4") && (ls_temp.equals("2") || ls_temp.equals("3")))
			{
				BeanDireccion lbd_beanDireccion;

				lbd_beanDireccion = getBeanDireccion();

				lbd_beanDireccion.setDireccionResidencia(null);
				setDireccionResidenciaInter(null);
			}

			setMedioNotificarInterviniente(ls_temp);
		}
	}

	/**
	 * Cargar correo electronico inter.
	 */
	public void cargarCorreoElectronicoInter()
	{
		String ls_idCorreoSeleccion;
		ls_idCorreoSeleccion = getIdCorreoSeleccionInter();

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
					lr_datosCalculados = getRegistroDatosConsultadosInter();

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
											setCorreoElectronicoInter(lpce_iterador);
											setDeshabilitarCorreoInter(true);
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
			setCorreoElectronicoInter(null);
			setDeshabilitarCorreoInter(false);
		}
	}

	/**
	 * Cargar datos personales inter.
	 *
	 * @param aop_op correspondiente al valor del tipo de objeto Persona
	 */
	public void cargarDatosPersonalesInter(Persona aop_op)
	{
		if(aop_op != null)
		{
			Registro lr_datosCalculados;
			String   ls_idPersonaSeleccion;

			lr_datosCalculados        = getRegistroDatosConsultadosInter();
			ls_idPersonaSeleccion     = aop_op.getIdPersona();

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
									setPersonaInter(lp_iterador);
									setDeshabilitarDatosBasicosInter(true);
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
						setPersonaInter(null);
						setDeshabilitarDatosBasicosInter(false);
					}
					else
						setNuevoInterviniente(false);
				}
			}

			Persona lp_persona;
			lp_persona = getPersonaInter();

			if(lp_persona != null)
			{
				validarTipoPersonaDocumentoInter(StringUtils.getStringNotNull(lp_persona.getIdTipoPersona()));
				setMostrarDatosConsulta(true);
			}

			setAccionBtnEditPerNitInter(false);
			setAccionBtnEditPerNormalInter(false);
		}
	}

	/**
	 * Cargar datos personales inter.
	 */
	public void cargarDatosPersonalesInter()
	{
		String ls_idPersonaSeleccion;
		ls_idPersonaSeleccion = getIdPersonaSeleccionInter();

		if(StringUtils.isValidString(ls_idPersonaSeleccion))
		{
			Registro lr_datosCalculados;
			lr_datosCalculados = getRegistroDatosConsultadosInter();

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
							String ls_idPersona;
							ls_idPersona = lp_iterador.getIdPersona();

							if(
							    StringUtils.isValidString(ls_idPersona)
								    && ls_idPersonaSeleccion.equalsIgnoreCase(ls_idPersona)
							)
							{
								setPersonaInter(lp_iterador);
								setDeshabilitarDatosBasicosInter(true);
							}
						}
					}
				}
			}
		}
		else
		{
			setPersonaInter(null);
			setDeshabilitarDatosBasicosInter(false);
		}
	}

	/**
	 * Cargar direccion correspondencia inter.
	 * @throws B2BException
	 */
	public void cargarDireccionCorrespondenciaInter()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;
		String        ls_idDirCorrSeleccion;

		lbd_beanDireccion         = getBeanDireccion();
		ls_idDirCorrSeleccion     = getIdDirCorrSeleccionInter();

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
					lr_datosCalculados = getRegistroDatosConsultadosInter();

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
											setDireccionCorrespondenciaInter(lpd_iterador);
											lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
											setDeshabilitarCorrespondenciaInter(true);
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
			setDireccionCorrespondenciaInter(null);
			lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
			setDeshabilitarCorrespondenciaInter(false);
		}
	}

	/**
	 * Cargar direccion residencia inter.
	 * @throws B2BException
	 */
	public void cargarDireccionResidenciaInter()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;
		String        ls_idDirResSeleccion;

		lbd_beanDireccion        = getBeanDireccion();
		ls_idDirResSeleccion     = getIdDirResSeleccionInter();

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
					lr_datosCalculados = getRegistroDatosConsultadosInter();

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
											setDireccionResidenciaInter(lpd_iterador);
											lbd_beanDireccion.setDeshabilitarResidencia(true);
											setDeshabilitarResidenciaInter(true);
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
			setDireccionResidenciaInter(null);
			lbd_beanDireccion.setDeshabilitarResidencia(false);
			setDeshabilitarResidenciaInter(false);
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
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar telefono fijo inter.
	 */
	public void cargarTelefonoFijoInter()
	{
		String ls_idTelFijoSeleccion;
		ls_idTelFijoSeleccion = getIdTelFijoSeleccionInter();

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
					lr_datosCalculados = getRegistroDatosConsultadosInter();

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
											setTelefonoFijoInter(lpt_iterador);
											findIndicativoDepartamentoInter();
											setDeshabilitarTelFijoInter(true);
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
			setTelefonoFijoInter(null);
			setDeshabilitarTelFijoInter(false);
		}
	}

	/**
	 * Cargar telefono movil inter.
	 */
	public void cargarTelefonoMovilInter()
	{
		String ls_idTelMovilSeleccion;
		ls_idTelMovilSeleccion = getIdTelMovSeleccionInter();

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
					lr_datosCalculados = getRegistroDatosConsultadosInter();

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
											setTelefonoMovilInter(lpt_iterador);
											setDeshabilitarTelMovilInter(true);
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
			setTelefonoMovilInter(null);
			setDeshabilitarTelMovilInter(false);
		}
	}

	/**
	 * Clear.
	 * @throws B2BException
	 */
	public void clear()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		setPersona(null);
		setPersonaInter(null);
		setCorreoElectronicoInter(null);
		lbd_beanDireccion.setDireccionCorrespondencia(null);
		setDireccionCorrespondenciaInter(null);
		lbd_beanDireccion.setDireccionResidencia(null);
		setDireccionResidenciaInter(null);
		setTelefonoFijoInter(null);
		setTelefonoMovilInter(null);
		setSolicitudInter(null);
		setMismaDireccionCorrespondenciaInter(null);
		setDeshabilitarCamposPorNitInter(false);
		setDeshabilitarCorreoInter(false);
		setDeshabilitarCorrespondenciaInter(false);
		lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
		setDeshabilitarDatosBasicosInter(false);
		setDeshabilitarResidenciaInter(false);
		lbd_beanDireccion.setDeshabilitarResidencia(false);
		setDeshabilitarTelFijoInter(false);
		setDeshabilitarTelMovilInter(false);
		setPaisInterCorrespondenciaInter(false);
		setPaisInterResidenciaInter(false);
		setRenderedInterviniente(false);
		setDocumentoInterviniente(null);
		setTipoDocInterviniente(null);
		setIdCorreoSeleccionInter(null);
		setIdDirCorrSeleccionInter(null);
		setIdDirResSeleccionInter(null);
		setIdPersonaSeleccionInter(null);
		setIdTelFijoSeleccionInter(null);
		setIdTelMovSeleccionInter(null);
		setRegistroDatosConsultadosInter(null);
		setDatatable(true);
	}

	/**
	 * Método para poder consultar los datos de una persona para traerlos como interviniente.
	 */
	public void consultarPersonaDocumentoInter()
	{
		setRenderedInterviniente(false);
		setPersona(null);
		setMostrarDatosConsulta(false);

		try
		{
			Persona  lp_parametros;
			Registro lr_registro;

			lp_parametros     = new Persona();
			lr_registro       = new Registro();

			limpiarIntervinientesConsulta();

			lp_parametros.setNumeroDocumento(getDocumentoInterviniente());
			lp_parametros.setTipoDocIdentidad(getTipoDocInterviniente());
			lr_registro.setPersona(lp_parametros);

			lr_registro = irr_registroRemote.findPersonByDocument(lr_registro, getUserId());

			if(lr_registro != null)
			{
				Collection<PersonaDireccion>         lcpd_direccionesResidencia;
				Collection<PersonaDireccion>         lcpd_direccionesCorrespondencia;
				Collection<PersonaTelefono>          lcpt_telefonosFijo;
				Collection<PersonaTelefono>          lcpt_telefonosMovil;
				Collection<PersonaCorreoElectronico> lcpce_correos;

				lcpd_direccionesResidencia          = lr_registro.getListadoDireccionResidencia();
				lcpd_direccionesCorrespondencia     = lr_registro.getListadoDireccionCorrespondencia();
				lcpt_telefonosFijo                  = lr_registro.getListadoTelefonoFijo();
				lcpt_telefonosMovil                 = lr_registro.getListadoTelefonoMovil();
				lcpce_correos                       = lr_registro.getListadoCorreoElectronico();

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

				if(
				    !lb_direccionesCorrespondencia || !lb_direccionesResidencia || !lb_telefonosFijo
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
					PrimeFaces.current().ajax().update("fDetailInter:globalMsg");
				}

				if(isActoConEnglobe())
					getSolicitudInter().setRolPersona(EstadoCommon.A);

				String ls_mensaje = "Consulta exitosa, verifique los datos.";
				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update("fDetailInter:idGrowl");

				setRenderedInterviniente(true);
				setRegistroDatosConsultadosInter(lr_registro);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String dataIntervinientes()
	{
		String               ls_idAnotacion;
		RegistroCalificacion lrc_rc;
		RegistroCalificacion lrc_dataInterviniente;
		lrc_rc                    = new RegistroCalificacion();
		lrc_dataInterviniente     = new RegistroCalificacion();

		try
		{
			ls_idAnotacion = getIdAnotacionPredio();

			if(StringUtils.isValidString(ls_idAnotacion))
			{
				lrc_rc.setIdAnotacionPredio(ls_idAnotacion);
				lrc_rc.setIdUsuario(getUserId());

				lrc_dataInterviniente = irr_calificacionRemote.detalleIntervinientes(lrc_rc);

				if(lrc_dataInterviniente != null)
				{
					Collection<SolicitudInterviniente> lcsi_cllSolicitudInterviniente;

					lcsi_cllSolicitudInterviniente = lrc_dataInterviniente.getIntervinientesIngresados();

					setIdSolicitud(lrc_dataInterviniente.getIdSolicitud());
					setDataInterviniente(lrc_dataInterviniente);
					setIdActo(lrc_dataInterviniente.getIdNaturalezJuridica());

					if(CollectionUtils.isValidCollection(lcsi_cllSolicitudInterviniente))
						setdataAprobacion(lcsi_cllSolicitudInterviniente);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_idAnotacion = null;
		}

		return ls_idAnotacion;
	}

	/**
	 * Metodo que se encarga de eliminar una anotación de registro calificación.
	 * @param asi_si Objeto que contiene el registro a eliminar de la anotación.
	 */
	public void deleteInterviniente(SolicitudInterviniente asi_si)
	{
		try
		{
			if(asi_si != null)
			{
				Collection<SolicitudInterviniente> lcrc_dataAprobacion;

				lcrc_dataAprobacion = getdataAprobacion();

				if(CollectionUtils.isValidCollection(lcrc_dataAprobacion))
				{
					if(lcrc_dataAprobacion.size() == 1)
						throw new B2BException(ErrorKeys.ERROR_ANOTACION_SIN_INTERVINIENTES);

					if(isMostrarDesdeDetalle())
					{
						int li_contador;

						li_contador = 0;

						for(SolicitudInterviniente lorc_iterador : lcrc_dataAprobacion)
						{
							if(lorc_iterador != null)
							{
								String ls_rolPersona;

								ls_rolPersona = lorc_iterador.getRolPersona();

								if(
								    StringUtils.isValidString(ls_rolPersona)
									    && ls_rolPersona.equalsIgnoreCase(EstadoCommon.A)
								)
									li_contador++;
							}
						}

						{
							String ls_rolPersonaEliminar;

							ls_rolPersonaEliminar = asi_si.getRolPersona();

							if(
							    StringUtils.isValidString(ls_rolPersonaEliminar)
								    && ls_rolPersonaEliminar.equalsIgnoreCase(EstadoCommon.A) && (li_contador == 1)
							)
								throw new B2BException(ErrorKeys.ERROR_NO_SE_PUEDE_ELIMINAR_INTERVINIENTE_A);
						}
					}

					lcrc_dataAprobacion.remove(asi_si);

					asi_si.setIdSolicitud(getIdSolicitud());
					irr_calificacionRemote.eliminarDataPersona(asi_si, getUserId());

					throw new B2BException("Registro eliminado.");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Deshabilitar campos nit inter.
	 */
	public void deshabilitarCamposNitInter()
	{
		Persona lp_persona;
		lp_persona = getPersonaInter();

		if(lp_persona != null)
		{
			String ls_tipoDocumento;
			ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

			if(
			    StringUtils.isValidString(ls_tipoDocumento)
				    && ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
				setDeshabilitarCamposPorNitInter(true);
			else
				setDeshabilitarCamposPorNitInter(false);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosCorrespondenciaInter()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosResidenciaInter()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosTelefonoInter()
	{
		Collection<Departamento> lcd_departamentos;
		lcd_departamentos = null;

		try
		{
			PersonaTelefono lpt_telefono;
			lpt_telefono = getTelefonoFijoInter();

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
	 * Find indicativo departamento inter.
	 */
	public void findIndicativoDepartamentoInter()
	{
		try
		{
			PersonaTelefono lpt_telefono;
			lpt_telefono = getTelefonoFijoInter();

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
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioCorrespondenciaInter()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioResidenciaInter()
	{
		return null;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findPropietario()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		lcidt_datos = new ArrayList<InteresadoDocumentoTipo>();

		InteresadoDocumentoTipo loit_tmp;
		loit_tmp = new InteresadoDocumentoTipo();

		loit_tmp.setDescripcion("I");
		loit_tmp.setIdDocumentoTipo("I");

		lcidt_datos.add(loit_tmp);
		loit_tmp = new InteresadoDocumentoTipo();
		loit_tmp.setDescripcion("X");
		loit_tmp.setIdDocumentoTipo("X");

		lcidt_datos.add(loit_tmp);

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findRol()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		lcidt_datos = new ArrayList<InteresadoDocumentoTipo>();

		InteresadoDocumentoTipo loit_tmp;
		loit_tmp = new InteresadoDocumentoTipo();

		loit_tmp.setDescripcion(EstadoCommon.A);
		loit_tmp.setIdDocumentoTipo(EstadoCommon.A);

		lcidt_datos.add(loit_tmp);
		loit_tmp = new InteresadoDocumentoTipo();
		loit_tmp.setDescripcion("De");
		loit_tmp.setIdDocumentoTipo("De");

		lcidt_datos.add(loit_tmp);

		return lcidt_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<InteresadoDocumentoTipo> findRrr()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;
		lcidt_datos = new ArrayList<InteresadoDocumentoTipo>();

		try
		{
			if(StringUtils.isValidString(getIdActo()))
				lcidt_datos = irr_referenceRemote.findRrr(getIdActo());
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
	public Collection<InteresadoDocumentoTipo> findTipoDocumentoSecuenciaCalificacion()
	{
		Collection<InteresadoDocumentoTipo> lcidt_datos;

		try
		{
			if(isHabilitaSecuencia())
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
							BigInteger lbi_bi;

							lbi_bi = lc_constante.getEntero().add(NumericUtils.getBigInteger(1));

							if(NumericUtils.isValidBigInteger(lbi_bi))
								setDocumentoInterviniente(StringUtils.getString(lbi_bi));

							setRenderedHabilitaSecuencia(true);
							getPersona().setIdDocumentoTipo(ls_tipoDocInter);
							getPersonaInter().setIdDocumentoTipo(ls_tipoDocInter);
							getPersonaInter().setNumeroDocumento(StringUtils.getString(lbi_bi));
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

			lcidt_datos = null;
		}

		return lcidt_datos;
	}

	/**
	 * Retorna el valor de data aprobacion.
	 *
	 * @return el valor de data aprobacion
	 */
	public Collection<SolicitudInterviniente> getdataAprobacion()
	{
		return icsi_dataAprobacion;
	}

	/**
	 * Método para poder ocultar la tabla y poder insertar un interviniente.
	 * @throws B2BException
	 */
	public void insertarRemitente()
	    throws B2BException
	{
		setDatatable(false);

		if(isActoConEnglobe())
			getSolicitudInter().setRolPersona(EstadoCommon.A);

		setPreguntaMasivoIntervinientes("ind");
		setTablaSolicitudInterviniente(null);
		setRenderedIntervinienteActo(
		    irr_registroRemote.validacionActosContraConstante(
		        getIdSolicitud(), getIdAnotacionPredio(), ConstanteCommon.CODIGO_DE_ACTO_CON_MAS_DE_UN_INTERVINIENTE,
		        getUserId()
		    )
		);
	}

	/**
	 * Limpiar campos intervinientes.
	 */
	public void limpiarCamposIntervinientes()
	{
		FacesContext lfc_context;

		lfc_context = FacesContext.getCurrentInstance();

		cleanInputText(":fDetailInter:idOlPNombreInter", lfc_context);

		cleanInputText(":fDetailInter:idOlPApellidoInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMGeneroInter", lfc_context);

		cleanInputText(":fDetailInter:idOlRazonSocialInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMTipoPersonaInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMTipoDocInter", lfc_context);

		cleanInputText(":fDetailInter:idOlDocumentoInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMNacionalidadInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idOlRolInter", lfc_context);

		// Validacion de campos en direccion de residencia
		cleanSelectOneMenu(":fDetailInter:idsomTipoEjeInter", lfc_context);

		cleanInputText(":fDetailInter:idItDatoEjePrincipalInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idsomTipoEje1Inter", lfc_context);

		cleanInputText(":fDetailInter:idItDatoEjeSecundarioInter", lfc_context);

		cleanInputText(":fDetailInter:idItComplementoDirInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMPaisDirReInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMDepDirReInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMMunDirReInter", lfc_context);

		// Validacion campos direccion de correspondencia
		cleanOutputLabel(":fDetailInter:idOLMismaDireccionInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idsomTipoEjeCInter", lfc_context);

		cleanInputText(":fDetailInter:idItDatoEjePrincipalCInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idsomTipoEje1CInter", lfc_context);

		cleanInputText(":fDetailInter:idItDatoEjeSecundarioCInter", lfc_context);

		cleanInputText(":fDetailInter:idItComplementoDirCInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMPaisDirCorInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMDepDirCorInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMMunDirCorInter", lfc_context);

		// Validacion de datos de contacto
		cleanSelectOneMenu(":fDetailInter:idSOMPaisTelInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMDepTelInter", lfc_context);

		cleanInputText(":fDetailInter:idItTelefonoFijoInter", lfc_context);

		cleanSelectOneMenu(":fDetailInter:idSOMPaisTelMovInter", lfc_context);

		cleanInputText(":fDetailInter:idItTelefonoMovilInter", lfc_context);

		cleanInputText(":fDetailInter:idItEmailInter", lfc_context);
	}

	/**
	 * Limpiar intervinientes.
	 * @throws B2BException
	 */
	public void limpiarIntervinientes()
	    throws B2BException
	{
		limpiarIntervinientesConsulta();
	}

	/**
	 * Limpiar intervinientes consulta.
	 * @throws B2BException
	 */
	public void limpiarIntervinientesConsulta()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		setPersonaInter(null);
		setDireccionResidenciaInter(null);
		lbd_beanDireccion.setDireccionResidencia(null);
		setDireccionCorrespondenciaInter(null);
		lbd_beanDireccion.setDireccionCorrespondencia(null);
		setTelefonoFijoInter(null);
		setTelefonoMovilInter(null);
		setCorreoElectronicoInter(null);
		setSolicitudInter(null);
		setMediosNotificarInter(null);
		setMediosComunicarInter(null);
		setMismaDireccionCorrespondenciaInter(null);
		setPrimerNombreInterTemp(null);
		setPrimerApellidoInterTemp(null);
		setSegundoApellidoInterTemp(null);
		setSegundoNombreInterTemp(null);
		setRazonSocialInterTemp(null);

		setDeshabilitarTipoDocumentoInter(false);
		setDeshabilitarTipoPersonaInter(false);
		setDeshabilitarDatosBasicosInter(false);
		setDeshabilitarCamposPorNitInter(false);
		setDeshabilitarResidenciaInter(false);
		lbd_beanDireccion.setDeshabilitarResidencia(false);
		setDeshabilitarCorrespondenciaInter(false);
		lbd_beanDireccion.setDeshabilitarCorrespondencia(false);
		setDeshabilitarTelFijoInter(false);
		setHabilitarPanelDirCorrespondenciaInter(false);
		setHabilitarPanelDirResidenciaInter(false);
		setDeshabilitarTelMovilInter(false);
		setEditarInterviniente(false);
		setDeshabilitarCorreoInter(false);
		setAccionBtnEditPerNitInter(false);
		setAccionBtnEditPerNormalInter(false);
		setMostrarCorreo(false);
		setMostrarDireccionesResidencia(false);
		setMostrarDireccionesCorrespondencia(false);
		setMostrarTelefonoFijo(false);
		setMostrarDatosConsulta(false);
		setMostrarTelefonoMovil(false);
		dataIntervinientes();
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<SolicitudInterviniente> listarSolInter()
	{
		Collection<SolicitudInterviniente> lcsi_datos;
		lcsi_datos = null;

		try
		{
			SolicitudInterviniente lsi_parametros;
			lsi_parametros = new SolicitudInterviniente();

			lsi_parametros.setIdSolicitud(getIdSolicitud());

			lcsi_datos = irr_registroRemote.findSolicitudIntervinienteBySolicitud(lsi_parametros, getUserId());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcsi_datos;
	}

	/**
	 * Modificar interviniente.
	 *
	 * @param asi_solicitudInterviniente correspondiente al valor del tipo de objeto SolicitudInterviniente
	 */
	public void modificarInterviniente(SolicitudInterviniente asi_solicitudInterviniente)
	{
		if(asi_solicitudInterviniente != null)
		{
			if(
			    !StringUtils.getStringNotNull(asi_solicitudInterviniente.getIdDocumentoTipo())
				                .equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
				setDeshabilitarRazon(true);
			else
				setDeshabilitarRazon(false);

			setRenderedModInterviniente(true);
			setEditarInterviniente(true);

			String ls_porcentaje;
			ls_porcentaje = asi_solicitudInterviniente.getPorcentaje();

			if(!StringUtils.isValidString(ls_porcentaje))
				asi_solicitudInterviniente.setPorcentaje("");

			setDetalleInterviniente(asi_solicitudInterviniente);
		}
	}

	/**
	 * Mostrar editar intervinientes.
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
	 * Método sobrecargado para enviar solicitud
	 *
	 * @param afue_event
	 * @throws B2BException
	 */
	public void procesarExcelIntervinientesSolicitud(FileUploadEvent afue_event)
	    throws B2BException
	{
		procesarExcelIntervinientes(afue_event, getIdSolicitud(), false);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String regresarDashboard()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = NavegacionCommon.DETALLE_REGISTRO;

		try
		{
			if(isRenderedModInterviniente())
			{
				setDetalleInterviniente(null);
				setRenderedModInterviniente(false);

				addMessage(MessagesKeys.MODIFICACION_CANCELADA);
				PrimeFaces.current().ajax().update("fDetailInter:idGrowl");
			}
			else if(isMostrarDesdeDetalle())
			{
				BeanDetalleRegistroCalificacion lbrc_bean;

				lbrc_bean = (BeanDetalleRegistroCalificacion)lfc_context.getApplication()
						                                                    .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_REGISTRO_CALIFICACION,
						    BeanDetalleRegistroCalificacion.class
						);

				if(lbrc_bean != null)
				{
					lbrc_bean.cargarMatricula(true);
					lbrc_bean.consultaDetalleMatricula();
					lbrc_bean.cargarDatosParametricosDireccion();
					lbrc_bean.setEjecucionCorrecciones(false);
					lbrc_bean.setCorreccionAnotacion(false);

					ls_return = NavegacionCommon.DETALLE_REGISTRO_CALIFICACION;
				}
			}
			else if(getDataInterviniente() != null)
			{
				BeanRegistroCalificacion lbrc_bean;

				lbrc_bean = (BeanRegistroCalificacion)lfc_context.getApplication()
						                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_REGISTRO_CALIFICACION, BeanRegistroCalificacion.class
						);

				lbrc_bean.consultaDetalleMatricula(false);
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("regresarDashboard", le_e);
		}

		return ls_return;
	}

	/**
	 * Modifica el valor de data aprobacion.
	 *
	 * @param acsi_si asigna el valor a la propiedad data aprobacion
	 */
	public void setdataAprobacion(Collection<SolicitudInterviniente> acsi_si)
	{
		icsi_dataAprobacion = acsi_si;
	}

	/**
	 * Validar campos interviniente.
	 *
	 * @param ar_registro correspondiente al valor del tipo de objeto Registro
	 * @throws B2BException
	 */
	public void validarCamposInterviniente(Registro ar_registro)
	    throws B2BException
	{
		if(ar_registro != null)
		{
			BeanDireccion lbd_beanDireccion;
			FacesContext  lfc_context;
			boolean       lb_focus;
			String        ls_datoParaValidar;

			lbd_beanDireccion      = getBeanDireccion();
			lfc_context            = FacesContext.getCurrentInstance();
			lb_focus               = true;
			ls_datoParaValidar     = "";

			PersonaDireccion         lpd_dirCor;
			PersonaDireccion         lpd_dirRe;
			PersonaTelefono          lpt_telFi;
			PersonaTelefono          lpt_telMo;
			PersonaCorreoElectronico lpc_correo;
			String                   ls_dirCor;
			String                   ls_dirRe;
			String                   ls_telFi;
			String                   ls_telMo;
			String                   ls_correo1;

			lpd_dirCor             = ar_registro.getDireccionCorrespondencia();
			lpd_dirRe              = ar_registro.getDireccionResidencia();
			ls_dirCor              = lpd_dirCor.getDireccion();
			ls_dirRe               = lpd_dirRe.getDireccion();
			lpt_telFi              = ar_registro.getTelefonoFijo();
			lpt_telMo              = ar_registro.getTelefonoMovil();
			lpc_correo             = ar_registro.getPersonaCorreoElectronico();
			ls_telFi               = lpt_telFi.getTelefono();
			ls_telMo               = lpt_telMo.getTelefono();
			ls_correo1             = lpc_correo.getCorreoElectronico();

			if(!isDeshabilitarDatosBasicosInter())
			{
				if(!isDeshabilitarCamposPorNitInter())
				{
					ls_datoParaValidar     = getPersonaInter().getPrimerNombre();
					lb_focus               = validateStyles(
						    ":fDetailInter:idOlPNombreInter", lfc_context, ls_datoParaValidar, lb_focus
						);

					ls_datoParaValidar     = getPersonaInter().getPrimerApellido();
					lb_focus               = validateStyles(
						    ":fDetailInter:idOlPApellidoInter", lfc_context, ls_datoParaValidar, lb_focus
						);

					ls_datoParaValidar     = getPersonaInter().getIdNaturalGenero();
					lb_focus               = validateStyles(
						    ":fDetailInter:idSOMGeneroInter", lfc_context, ls_datoParaValidar, lb_focus
						);
				}
				else
				{
					ls_datoParaValidar     = getPersonaInter().getRazonSocial();
					lb_focus               = validateStyles(
						    ":fDetailInter:idOlRazonSocialInter", lfc_context, ls_datoParaValidar, lb_focus
						);
				}

				ls_datoParaValidar     = getPersonaInter().getIdTipoPersona();
				lb_focus               = validateStyles(
					    ":fDetailInter:idSOMTipoPersonaInter", lfc_context, ls_datoParaValidar, lb_focus
					);

				ls_datoParaValidar     = getPersonaInter().getIdDocumentoTipo();
				lb_focus               = validateStyles(
					    ":fDetailInter:idSOMTipoDocInter", lfc_context, ls_datoParaValidar, lb_focus
					);

				ls_datoParaValidar     = getPersonaInter().getNumeroDocumento();
				lb_focus               = validateStyles(
					    ":fDetailInter:idOlDocumentoInter", lfc_context, ls_datoParaValidar, lb_focus
					);

				ls_datoParaValidar     = getPersonaInter().getIdPais();
				lb_focus               = validateStyles(
					    ":fDetailInter:idSOMNacionalidadInter", lfc_context, ls_datoParaValidar, lb_focus
					);
			}

			ls_datoParaValidar     = getSolicitudInter().getRolPersona();
			lb_focus               = validateStyles(
				    ":fDetailInter:idOlRolInter", lfc_context, ls_datoParaValidar, lb_focus
				);

			/*ls_datoParaValidar     = getSolicitudInter().getIdAutorizacionNotificacion();
			lb_focus               = validateStyles(
			        ":fDetailInter:idsomMedioANotificarInter", lfc_context, ls_datoParaValidar, lb_focus
			    );
			
			ls_datoParaValidar     = getSolicitudInter().getIdAutorizacionComunicacion();
			lb_focus               = validateStyles(
			        ":fDetailInter:idsomMedioAComInter", lfc_context, ls_datoParaValidar, lb_focus
			    );*/

			// Validacion de campos en direccion de residencia
			if(!isDeshabilitarResidenciaInter() && StringUtils.isValidString(ls_dirRe))
				lbd_beanDireccion.validarCamposDireccionResidencia();

			// Validacion campos direccion de correspondencia
			if(!isDeshabilitarCorrespondenciaInter() && StringUtils.isValidString(ls_dirCor))
				lbd_beanDireccion.validarCamposDireccionCorrespondencia(true);

			if(
			    !StringUtils.isValidString(ls_telFi) && !StringUtils.isValidString(ls_telMo)
				    && !StringUtils.isValidString(ls_correo1)
			)
			{
				// Validacion de datos de contacto
				if(!isDeshabilitarTelFijoInter())
				{
					PersonaTelefono lpt_personaTelefono;
					lpt_personaTelefono = getTelefonoFijoInter();

					if(lpt_personaTelefono != null)
					{
						ls_datoParaValidar     = lpt_personaTelefono.getIdPais();
						lb_focus               = validateStyles(
							    ":fDetailInter:idSOMPaisTelInter", lfc_context, ls_datoParaValidar, lb_focus
							);

						ls_datoParaValidar     = lpt_personaTelefono.getIdDepartamento();
						lb_focus               = validateStyles(
							    ":fDetailInter:idSOMDepTelInter", lfc_context, ls_datoParaValidar, lb_focus
							);

						ls_datoParaValidar     = lpt_personaTelefono.getTelefono();
						lb_focus               = validateStyles(
							    ":fDetailInter:idItTelefonoFijoInter", lfc_context, ls_datoParaValidar, lb_focus
							);
					}
				}

				if(!isDeshabilitarTelMovilInter())
				{
					PersonaTelefono lpt_personaTelefono;
					lpt_personaTelefono = getTelefonoMovilInter();

					if(lpt_personaTelefono != null)
					{
						ls_datoParaValidar     = lpt_personaTelefono.getIdPais();
						lb_focus               = validateStyles(
							    ":fDetailInter:idSOMPaisTelMovInter", lfc_context, ls_datoParaValidar, lb_focus
							);

						ls_datoParaValidar     = lpt_personaTelefono.getTelefono();
						lb_focus               = validateStyles(
							    ":fDetailInter:idItTelefonoMovilInter", lfc_context, ls_datoParaValidar, lb_focus
							);
					}
				}

				if(!isDeshabilitarCorreoInter())
				{
					ls_datoParaValidar     = getCorreoElectronicoInter().getCorreoElectronico();
					lb_focus               = validateStyles(
						    ":fDetailInter:idItEmailInter", lfc_context, ls_datoParaValidar, lb_focus
						);
				}
			}
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
		lp_temp = getPersonaInter();

		if((as_seleccion != null) && (lp_temp != null))
		{
			if(
			    as_seleccion.equalsIgnoreCase(EstadoCommon.J)
				    || as_seleccion.equalsIgnoreCase(IdentificadoresCommon.NIT)
			)
			{
				setDeshabilitarCamposPorNitInter(true);
				setDeshabilitarTipoDocumentoInter(true);

				lp_temp.setIdNaturalGenero(EstadoCommon.N);

				cargarMediosNotComInter(true);

				if(as_seleccion.equalsIgnoreCase(EstadoCommon.J))
					if(lp_temp.getIdDocumentoTipo() == null)
						lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
					else if(!lp_temp.getIdDocumentoTipo().equalsIgnoreCase(EstadoCommon.SE))
						lp_temp.setIdDocumentoTipo(IdentificadoresCommon.NIT);
					else
						lp_temp.setIdTipoPersona(EstadoCommon.J);
			}
			else
			{
				setDeshabilitarTipoDocumentoInter(false);
				setDeshabilitarTipoPersonaInter(false);
				setDeshabilitarCamposPorNitInter(false);

				cargarMediosNotComInter(false);

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

			setPersonaInter(lp_temp);
		}
	}
}
