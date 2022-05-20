package com.bachue.snr.prosnr16.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de SDB_PGN_ORIGEN.
 *
 * @author Sebastian Sanchez
 */
public class Origen extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7727817936581631371L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id origen. */
	private String is_idOrigen;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public Origen()
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
	 * Modifica el valor de IdOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdOrigen(String as_s)
	{
		is_idOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdOrigen()
	{
		return is_idOrigen;
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
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}
}
