package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_INTERVINIENTE.
 *
 * @author lchacon
 */
public class SolicitudInterviniente extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3068715207067894494L;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad is error interviniente masivo. */
	private String is_errorIntervinienteMasivo;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id autorizacion comunicacion. */
	private String is_idAutorizacionComunicacion;

	/** Propiedad is id autorizacion notificacion. */
	private String is_idAutorizacionNotificacion;

	/** Propiedad is id correo electronico. */
	private String is_idCorreoElectronico;

	/** Propiedad is id correo electronico comunicacion. */
	private String is_idCorreoElectronicoComunicacion;

	/** Propiedad is id dependencia. */
	private String is_idDependencia;

	/** Propiedad is id direccion. */
	private String is_idDireccion;

	/** Propiedad is id direccion comunicacion. */
	private String is_idDireccionComunicacion;

	/** Propiedad is id documento tipo. */
	private String is_idDocumentoTipo;

	/** Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id telefono. */
	private String is_idTelefono;

	/** Propiedad is id telefono comunicacion. */
	private String is_idTelefonoComunicacion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is id usuario reasignado. */
	private String is_idUsuarioReasignado;

	/** Propiedad is interesada rrr. */
	private String is_interesadaRrr;

	/** Propiedad is interesada rrr nombre. */
	private String is_interesadaRrrNombre;

	/** Propiedad is nombre interviniente. */
	private String is_nombreInterviniente;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is porcentaje. */
	private String is_porcentaje;

	/** Propiedad is reasignado. */
	private String is_reasignado;

	/** Propiedad is rol persona. */
	private String is_rolPersona;

	/** Propiedad is valor propietario. */
	private String is_valorPropietario;

	/** Propiedad is visitador principal. */
	private String is_visitadorPrincipal;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto solicitud interviniente.
	 */
	public SolicitudInterviniente()
	{
	}

	public SolicitudInterviniente(Persona ap_persona, String as_idSolicitud)
	{
		if((ap_persona != null) && StringUtils.isValidString(as_idSolicitud))
		{
			ip_persona     = ap_persona;

			is_idSolicitud     = as_idSolicitud;
			is_idPersona       = ap_persona.getIdPersona();
		}
	}

	/**
	 * Instancia un nuevo objeto solicitud interviniente.
	 *
	 * @param as_idSolicitud correspondiente al valor de id solicitud
	 */
	public SolicitudInterviniente(String as_idSolicitud)
	{
		is_idSolicitud = as_idSolicitud;
	}

	/**
	 * Instancia un nuevo objeto solicitud interviniente.
	 *
	 * @param arg_rg correspondiente al valor de RegistroCalificacion
	 */
	public SolicitudInterviniente(RegistroCalificacion arg_rg)
	{
		if(arg_rg != null)
		{
			is_rolPersona              = arg_rg.getRolPersona();
			is_idPersona               = arg_rg.getIdPersona();
			ip_persona                 = arg_rg.getDataPersona();
			is_interesadaRrrNombre     = arg_rg.getInteresadaRrrNombre();
			is_idSolicitud             = arg_rg.getIdSolicitud();
			is_idAnotacionPredio       = arg_rg.getIdAnotacionPredio();
			is_valorPropietario        = arg_rg.getValuePropietario();
			is_porcentaje              = arg_rg.getPorcentajeStr();
			is_interesadaRrr           = arg_rg.getInteresadaRrr();
		}
	}

	/**
	 * Instancia un nuevo objeto solicitud interviniente.
	 *
	 * @param asi_si correspondiente al valor de SolicitudInterviniente
	 */
	public SolicitudInterviniente(SolicitudInterviniente asi_si)
	{
		if(asi_si != null)
		{
			ip_persona                             = asi_si.getPersona();
			is_idAutorizacionComunicacion          = asi_si.getIdAutorizacionComunicacion();
			is_idAutorizacionNotificacion          = asi_si.getIdAutorizacionNotificacion();
			is_idCorreoElectronico                 = asi_si.getIdCorreoElectronico();
			is_idDireccion                         = asi_si.getIdDireccion();
			is_idDocumentoTipo                     = asi_si.getIdDocumentoTipo();
			is_idPersona                           = asi_si.getIdPersona();
			is_idSolicitud                         = asi_si.getIdSolicitud();
			is_idTelefono                          = asi_si.getIdTelefono();
			is_idTurno                             = asi_si.getIdTurno();
			is_idTurnoHistoria                     = asi_si.getIdTurnoHistoria();
			is_nombreInterviniente                 = asi_si.getNombreInterviniente();
			is_numeroDocumento                     = asi_si.getNumeroDocumento();
			is_rolPersona                          = asi_si.getRolPersona();
			ib_seleccionado                        = asi_si.isSeleccionado();
			il_idEtapa                             = asi_si.getIdEtapa();
			is_idCorreoElectronicoComunicacion     = asi_si.getIdCorreoElectronicoComunicacion();
			is_idDireccionComunicacion             = asi_si.getIdDireccionComunicacion();
			is_idTelefonoComunicacion              = asi_si.getIdTelefonoComunicacion();
		}
	}

	/**
	 * Modifica el valor de ErrorIntervinienteMasivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setErrorIntervinienteMasivo(String as_s)
	{
		is_errorIntervinienteMasivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor error interviniente masivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getErrorIntervinienteMasivo()
	{
		return is_errorIntervinienteMasivo;
	}

	/**
	 * Modifica el valor de IdAnotacionPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de IdAutorizacionComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAutorizacionComunicacion(String as_s)
	{
		is_idAutorizacionComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id autorizacion comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAutorizacionComunicacion()
	{
		return is_idAutorizacionComunicacion;
	}

	/**
	 * Modifica el valor de IdAutorizacionNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAutorizacionNotificacion(String as_s)
	{
		is_idAutorizacionNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id autorizacion notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAutorizacionNotificacion()
	{
		return is_idAutorizacionNotificacion;
	}

	/**
	 * Modifica el valor de IdCorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronico(String as_s)
	{
		is_idCorreoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronico()
	{
		return is_idCorreoElectronico;
	}

	/**
	 * Modifica el valor de IdCorreoElectronicoComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronicoComunicacion(String as_s)
	{
		is_idCorreoElectronicoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronicoComunicacion()
	{
		return is_idCorreoElectronicoComunicacion;
	}

	/**
	 * Modifica el valor de IdDependencia.
	 *
	 * @param as_s de as s
	 */
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dependencia.
	 *
	 * @return el valor de id dependencia
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
	}

	/**
	 * Modifica el valor de IdDireccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de IdDireccionComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccionComunicacion(String as_s)
	{
		is_idDireccionComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccionComunicacion()
	{
		return is_idDireccionComunicacion;
	}

	/**
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de IdEntidadExterna.
	 *
	 * @param as_s de as s
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad externa.
	 *
	 * @return Retorna el valor de la propiedad idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTelefono.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefono(String as_s)
	{
		is_idTelefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefono()
	{
		return is_idTelefono;
	}

	/**
	 * Modifica el valor de IdTelefonoComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefonoComunicacion(String as_s)
	{
		is_idTelefonoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefonoComunicacion()
	{
		return is_idTelefonoComunicacion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * @param is_idUsuarioReasignado Modifica el valor de la propiedad is_idUsuarioReasignado
	 */
	public void setIdUsuarioReasignado(String as_idUsuarioReasignado)
	{
		is_idUsuarioReasignado = as_idUsuarioReasignado;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUsuarioReasignado
	 */
	public String getIdUsuarioReasignado()
	{
		return is_idUsuarioReasignado;
	}

	/**
	 * Modifica el valor de InteresadaRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInteresadaRrr(String as_s)
	{
		is_interesadaRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor interesada rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInteresadaRrr()
	{
		return is_interesadaRrr;
	}

	/**
	 * Modifica el valor de InteresadaRrrNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInteresadaRrrNombre(String as_s)
	{
		is_interesadaRrrNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor interesada rrr nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInteresadaRrrNombre()
	{
		return is_interesadaRrrNombre;
	}

	/**
	 * Modifica el valor de NombreInterviniente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreInterviniente(String as_s)
	{
		is_nombreInterviniente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre interviniente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreInterviniente()
	{
		return is_nombreInterviniente;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de Persona.
	 *
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de Porcentaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPorcentaje(String as_s)
	{
		is_porcentaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPorcentaje()
	{
		return is_porcentaje;
	}

	/**
	 * @param is_reasignado Modifica el valor de la propiedad is_reasignado
	 */
	public void setReasignado(String as_reasignado)
	{
		is_reasignado = as_reasignado;
	}

	/**
	 * @return Retorna el valor de la propiedad is_reasignado
	 */
	public String getReasignado()
	{
		return is_reasignado;
	}

	/**
	 * Modifica el valor de RolPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRolPersona(String as_s)
	{
		is_rolPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor rol persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRolPersona()
	{
		return is_rolPersona;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de ValorPropietario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setValorPropietario(String as_s)
	{
		is_valorPropietario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor propietario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValorPropietario()
	{
		return is_valorPropietario;
	}

	/**
	 * @param is_visitadorPrincipal Modifica el valor de la propiedad is_visitadorPrincipal
	 */
	public void setVisitadorPrincipal(String is_vp)
	{
		is_visitadorPrincipal = is_vp;
	}

	/**
	 * @return Retorna el valor de la propiedad is_visitadorPrincipal
	 */
	public String getVisitadorPrincipal()
	{
		return is_visitadorPrincipal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object ao_object)
	{
		boolean lb_isEquals;

		lb_isEquals = false;

		if((ao_object != null) && ao_object instanceof SolicitudInterviniente)
		{
			SolicitudInterviniente lsi_solicitudIntervinienteAComparar;
			String                 ls_idPersonaAComparar;

			lsi_solicitudIntervinienteAComparar     = (SolicitudInterviniente)ao_object;
			ls_idPersonaAComparar                   = lsi_solicitudIntervinienteAComparar.getIdPersona();

			lb_isEquals = StringUtils.isValidString(ls_idPersonaAComparar) && StringUtils.isValidString(is_idPersona)
					&& ls_idPersonaAComparar.equalsIgnoreCase(is_idPersona);
		}

		return lb_isEquals;
	}
}
