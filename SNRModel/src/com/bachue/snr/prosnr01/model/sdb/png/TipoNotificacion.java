package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Tipo Notificacion.
 *
 * @author Sebastian Sanchez
 */
public class TipoNotificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4998062376303153933L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id tipo notificacion. */
	private String is_idTipoNotificacion;

	/** Propiedad is tipo notificacion. */
	private String is_tipoNotificacion;

	/**
	 * Constructor por defecto.
	 */
	public TipoNotificacion()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Retorna Objeto o variable de valor tipo notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoNotificacion()
	{
		return is_tipoNotificacion;
	}

	/**
	 * Modifica el valor de TipoNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoNotificacion(String as_s)
	{
		is_tipoNotificacion = as_s;
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
	 * Modifica el valor de IdTipoNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoNotificacion(String as_s)
	{
		is_idTipoNotificacion = as_s;
	}
}
