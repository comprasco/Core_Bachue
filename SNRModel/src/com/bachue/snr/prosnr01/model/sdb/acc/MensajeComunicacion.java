package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase de abstracción de la base de datos para la tabla SBD_ACC_MENSAJE_COMUNICACION
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class MensajeComunicacion extends Mensaje implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -100935347595679866L;

	/** Propiedad id fecha exito ejecucion automatica. */
	private Date id_fechaExitoEjecucionAutomatica;

	/** Propiedad id fecha intento ejecucion automatica. */
	private Date id_fechaIntentoEjecucionAutomatica;

	/** Propiedad ii intentos fallidos ejecucion automatica. */
	private Integer ii_intentosFallidosEjecucionAutomatica;

	/** Propiedad is id mensaje comunicaciones. */
	private String is_idMensajeComunicaciones;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is respuestas ejecucion automatica. */
	private String is_respuestasEjecucionAutomatica;

	/**
	 * Instancia un nuevo objeto mensaje comunicacion.
	 */
	public MensajeComunicacion()
	{
	}

	/**
	 * Instancia un nuevo objeto mensaje comunicacion.
	 *
	 * @param as_idMensajeComunicaciones de id mensaje comunicaciones
	 */
	public MensajeComunicacion(String as_idMensajeComunicaciones)
	{
		is_idMensajeComunicaciones = as_idMensajeComunicaciones;
	}

	/**
	 * Modifica el valor de FechaExitoEjecucionAutomatica.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaExitoEjecucionAutomatica(Date ad_d)
	{
		id_fechaExitoEjecucionAutomatica = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha exito ejecucion automatica.
	 *
	 * @return el valor de fecha exito ejecucion automatica
	 */
	public Date getFechaExitoEjecucionAutomatica()
	{
		return id_fechaExitoEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de FechaIntentoEjecucionAutomatica.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaIntentoEjecucionAutomatica(Date ad_d)
	{
		id_fechaIntentoEjecucionAutomatica = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha intento ejecucion automatica.
	 *
	 * @return el valor de fecha intento ejecucion automatica
	 */
	public Date getFechaIntentoEjecucionAutomatica()
	{
		return id_fechaIntentoEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de IdMensajeComunicaciones.
	 *
	 * @param as_s de as s
	 */
	public void setIdMensajeComunicaciones(String as_s)
	{
		is_idMensajeComunicaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje comunicaciones.
	 *
	 * @return el valor de id mensaje comunicaciones
	 */
	public String getIdMensajeComunicaciones()
	{
		return is_idMensajeComunicaciones;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s de as s
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IntentosFallidosEjecucionAutomatica.
	 *
	 * @param ai_i de ai i
	 */
	public void setIntentosFallidosEjecucionAutomatica(Integer ai_i)
	{
		ii_intentosFallidosEjecucionAutomatica = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor intentos fallidos ejecucion automatica.
	 *
	 * @return el valor de intentos fallidos ejecucion automatica
	 */
	public Integer getIntentosFallidosEjecucionAutomatica()
	{
		return ii_intentosFallidosEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de RespuestasEjecucionAutomatica.
	 *
	 * @param as_s de as s
	 */
	public void setRespuestasEjecucionAutomatica(String as_s)
	{
		is_respuestasEjecucionAutomatica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor respuestas ejecucion automatica.
	 *
	 * @return el valor de respuestas ejecucion automatica
	 */
	public String getRespuestasEjecucionAutomatica()
	{
		return is_respuestasEjecucionAutomatica;
	}
}
