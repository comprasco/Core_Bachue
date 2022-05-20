package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA_ENTREGA.
 *
 * @author garias
 */
public class PersonaEntrega extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                                 = 2399765181417684481L;
	
	/** Propiedad id fecha entrega. */
	private Date              id_fechaEntrega;
	
	/** Propiedad is id autorizacion comunicacion. */
	private String            is_idAutorizacionComunicacion;
	
	/** Propiedad is id autorizacion notificacion. */
	private String            is_idAutorizacionNotificacion;
	
	/** Propiedad is id calidad persona entrega. */
	private String            is_idCalidadPersonaEntrega;
	
	/** Propiedad is id correo electronico persona entrega. */
	private String            is_idCorreoElectronicoPersonaEntrega;
	
	/** Propiedad is id correo electronico persona entrega comunicacion. */
	private String            is_idCorreoElectronicoPersonaEntregaComunicacion;
	
	/** Propiedad is id direccion persona entrega. */
	private String            is_idDireccionPersonaEntrega;
	
	/** Propiedad is id direccion persona entrega comunicacion. */
	private String            is_idDireccionPersonaEntregaComunicacion;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is id persona entrega. */
	private String            is_idPersonaEntrega;
	
	/** Propiedad is id persona tercero. */
	private String            is_idPersonaTercero;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id telefono persona entrega. */
	private String            is_idTelefonoPersonaEntrega;
	
	/** Propiedad is id telefono persona entrega comunicacion. */
	private String            is_idTelefonoPersonaEntregaComunicacion;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;

	/**
	 * Método constructor por defecto.
	 */
	public PersonaEntrega()
	{
	}

	/**
	 * Método constructor el cúal asigna el valor del id solicitud.
	 *
	 * @param as_solicitud <code>String</code> que contiene el id solicitud a asignar
	 */
	public PersonaEntrega(String as_solicitud)
	{
		is_idSolicitud                                                         = as_solicitud;
	}

	/**
	 * Modifica el valor de FechaEntrega.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaEntrega(Date ad_d)
	{
		id_fechaEntrega = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaEntrega()
	{
		return id_fechaEntrega;
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
	 * Modifica el valor de IdCalidadPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCalidadPersonaEntrega(String as_s)
	{
		is_idCalidadPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id calidad persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCalidadPersonaEntrega()
	{
		return is_idCalidadPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdCorreoElectronicoPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronicoPersonaEntrega(String as_s)
	{
		is_idCorreoElectronicoPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronicoPersonaEntrega()
	{
		return is_idCorreoElectronicoPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdCorreoElectronicoPersonaEntregaComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronicoPersonaEntregaComunicacion(String as_s)
	{
		is_idCorreoElectronicoPersonaEntregaComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico persona entrega comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronicoPersonaEntregaComunicacion()
	{
		return is_idCorreoElectronicoPersonaEntregaComunicacion;
	}

	/**
	 * Modifica el valor de IdDireccionPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccionPersonaEntrega(String as_s)
	{
		is_idDireccionPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccionPersonaEntrega()
	{
		return is_idDireccionPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdDireccionPersonaEntregaComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccionPersonaEntregaComunicacion(String as_s)
	{
		is_idDireccionPersonaEntregaComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion persona entrega comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccionPersonaEntregaComunicacion()
	{
		return is_idDireccionPersonaEntregaComunicacion;
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
	 * Modifica el valor de IdPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersonaEntrega(String as_s)
	{
		is_idPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaEntrega()
	{
		return is_idPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdPersonaTercero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersonaTercero(String as_s)
	{
		is_idPersonaTercero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona tercero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersonaTercero()
	{
		return is_idPersonaTercero;
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
	 * Modifica el valor de IdTelefonoPersonaEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefonoPersonaEntrega(String as_s)
	{
		is_idTelefonoPersonaEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono persona entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefonoPersonaEntrega()
	{
		return is_idTelefonoPersonaEntrega;
	}

	/**
	 * Modifica el valor de IdTelefonoPersonaEntregaComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTelefonoPersonaEntregaComunicacion(String as_s)
	{
		is_idTelefonoPersonaEntregaComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id telefono persona entrega comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTelefonoPersonaEntregaComunicacion()
	{
		return is_idTelefonoPersonaEntregaComunicacion;
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
}
