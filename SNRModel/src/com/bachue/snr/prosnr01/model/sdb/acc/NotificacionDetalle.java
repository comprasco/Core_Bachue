package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de tabla SDB_ACC_NOTIFICACION_DETALLE.
 *
 * @author Julian Vaca
 */
public class NotificacionDetalle extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID           = -5424151391212667412L;
	
	/** Propiedad id fecha acuse recibo. */
	private Date              id_fechaAcuseRecibo;
	
	/** Propiedad id fecha notificacion. */
	private Date              id_fechaNotificacion;
	
	/** Propiedad id fecha notificacion envio. */
	private Date              id_fechaNotificacionEnvio;
	
	/** Propiedad id fecha renuncia. */
	private Date              id_fechaRenuncia;
	
	/** Propiedad is id notificacion detalle. */
	private String            is_idNotificacionDetalle;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is id tipo notificacion. */
	private String            is_idTipoNotificacion;
	
	/** Propiedad is id tipo notificacion envio. */
	private String            is_idTipoNotificacionEnvio;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is interpone recurso. */
	private String            is_interponeRecurso;
	
	/** Propiedad is renuncia terminos. */
	private String            is_renunciaTerminos;

	/**
	 * Modifica el valor de FechaAcuseRecibo.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaAcuseRecibo(Date ad_d)
	{
		id_fechaAcuseRecibo                              = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha acuse recibo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaAcuseRecibo()
	{
		return id_fechaAcuseRecibo;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaNotificacion(Date ad_d)
	{
		id_fechaNotificacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaNotificacion()
	{
		return id_fechaNotificacion;
	}

	/**
	 * Modifica el valor de FechaNotificacionEnvio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaNotificacionEnvio(Date ad_d)
	{
		id_fechaNotificacionEnvio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaNotificacionEnvio()
	{
		return id_fechaNotificacionEnvio;
	}

	/**
	 * Modifica el valor de FechaRenuncia.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRenuncia(Date ad_d)
	{
		id_fechaRenuncia = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha renuncia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRenuncia()
	{
		return id_fechaRenuncia;
	}

	/**
	 * Modifica el valor de IdNotificacionDetalle.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNotificacionDetalle(String as_s)
	{
		is_idNotificacionDetalle = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id notificacion detalle.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNotificacionDetalle()
	{
		return is_idNotificacionDetalle;
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
	 * Modifica el valor de IdTipoNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoNotificacion(String as_s)
	{
		is_idTipoNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoNotificacion()
	{
		return is_idTipoNotificacion;
	}

	/**
	 * Modifica el valor de IdTipoNotificacionEnvio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoNotificacionEnvio(String as_s)
	{
		is_idTipoNotificacionEnvio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo notificacion envio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoNotificacionEnvio()
	{
		return is_idTipoNotificacionEnvio;
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
	 * Modifica el valor de InterponeRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInterponeRecurso(String as_s)
	{
		is_interponeRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor interpone recurso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInterponeRecurso()
	{
		return is_interponeRecurso;
	}

	/**
	 * Modifica el valor de RenunciaTerminos.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRenunciaTerminos(String as_s)
	{
		is_renunciaTerminos = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor renuncia terminos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRenunciaTerminos()
	{
		return is_renunciaTerminos;
	}
}
