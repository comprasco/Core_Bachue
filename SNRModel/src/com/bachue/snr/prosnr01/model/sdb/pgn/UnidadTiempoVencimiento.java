package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Unidad Tiempo Vencimiento.
 *
 * @author Sebastian Sanchez
 */
public class UnidadTiempoVencimiento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7941007069547203088L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id unidad tiempo. */
	private String is_idUnidadTiempo;

	/**
	 * Constructor por defecto.
	 */
	public UnidadTiempoVencimiento()
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
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad tiempo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUnidadTiempo()
	{
		return is_idUnidadTiempo;
	}

	/**
	 * Modifica el valor de IdUnidadTiempo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUnidadTiempo(String as_s)
	{
		is_idUnidadTiempo = as_s;
	}
}
