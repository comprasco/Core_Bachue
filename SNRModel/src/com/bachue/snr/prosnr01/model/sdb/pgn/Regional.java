package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_REGIONAL.
 *
 * @author Julian Vaca
 */
public class Regional extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8530809936703686129L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id regional. */
	private String is_idRegional;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de IdRegional.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRegional(String as_s)
	{
		this.is_idRegional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id regional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRegional()
	{
		return is_idRegional;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
