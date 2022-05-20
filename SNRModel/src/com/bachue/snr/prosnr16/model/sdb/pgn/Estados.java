package com.bachue.snr.prosnr16.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de SDB_PGN_ESTADOS.
 *
 * @author Sebastian Sanchez
 */
public class Estados extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3918127732905588261L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id estado. */
	private String is_idEstado;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public Estados()
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
	 * Modifica el valor de IdEstado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstado(String as_s)
	{
		is_idEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstado()
	{
		return is_idEstado;
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
}
