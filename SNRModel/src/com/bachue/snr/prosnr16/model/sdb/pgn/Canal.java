package com.bachue.snr.prosnr16.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de SDB_PGN_CANAL.
 *
 * @author Sebastian Sanchez
 */
public class Canal extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5314399693609126862L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id canal. */
	private String is_idCanal;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public Canal()
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
	 * Modifica el valor de IdCanal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCanal(String as_s)
	{
		is_idCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanal()
	{
		return is_idCanal;
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
