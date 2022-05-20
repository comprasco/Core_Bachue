package com.bachue.snr.prosnr01.model.auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase para la abstracción de los campos de control de intentos envio en los jobs.
 *
 * @author Manuel Blanco
 */
public class IntentoEnvioJob extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1344988331430677247L;

	/** Propiedad id fecha exito ejecucion automatica. */
	private Date id_fechaExitoEjecucionAutomatica;

	/** Propiedad id fecha intento ejecucion automatica. */
	private Date id_fechaIntentoEjecucionAutomatica;

	/** Propiedad is respuesta ejecucion automatica. */
	private String is_respuestaEjecucionAutomatica;

	/** Propiedad il intentos fallidos ejecucion automatica. */
	private long il_intentosFallidosEjecucionAutomatica;

	/**
	 * Retorna Objeto o variable de valor intentos fallidos ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad intentosFallidosEjecucionAutomatica
	 */
	public long getIntentosFallidosEjecucionAutomatica()
	{
		return il_intentosFallidosEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de IntentosFallidosEjecucionAutomatica.
	 *
	 * @param al_l de intentos fallidos ejecucion automatica
	 */
	public void setIntentosFallidosEjecucionAutomatica(long al_l)
	{
		il_intentosFallidosEjecucionAutomatica = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor fecha intento ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad fechaIntentoEjecucionAutomatica
	 */
	public Date getFechaIntentoEjecucionAutomatica()
	{
		return id_fechaIntentoEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de FechaIntentoEjecucionAutomatica.
	 *
	 * @param ad_d de fecha intento ejecucion automatica
	 */
	public void setFechaIntentoEjecucionAutomatica(Date ad_d)
	{
		id_fechaIntentoEjecucionAutomatica = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha exito ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad fechaExitoEjecucionAutomatica
	 */
	public Date getFechaExitoEjecucionAutomatica()
	{
		return id_fechaExitoEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de FechaExitoEjecucionAutomatica.
	 *
	 * @param ad_d de fecha exito ejecucion automatica
	 */
	public void setFechaExitoEjecucionAutomatica(Date ad_d)
	{
		id_fechaExitoEjecucionAutomatica = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor respuesta ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad respuestaEjecucionAutomatica
	 */
	public String getRespuestaEjecucionAutomatica()
	{
		return is_respuestaEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de RespuestaEjecucionAutomatica.
	 *
	 * @param as_s de respuesta ejecucion automatica
	 */
	public void setRespuestaEjecucionAutomatica(String as_s)
	{
		is_respuestaEjecucionAutomatica = as_s;
	}
}
