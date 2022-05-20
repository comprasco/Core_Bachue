package com.bachue.snr.prosnr02.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades TopologiaRegla.
 *
 * @author  Juan Sebastian Gómez
 */
public class TopologiaRegla extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2461565547052844877L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id topologia regla. */
	private String is_idTopologiaRegla;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return el valor de descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de IdTopologiaRegla.
	 *
	 * @param as_s de as s
	 */
	public void setIdTopologiaRegla(String as_s)
	{
		is_idTopologiaRegla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id topologia regla.
	 *
	 * @return el valor de id topologia regla
	 */
	public String getIdTopologiaRegla()
	{
		return is_idTopologiaRegla;
	}

	/** {@inheritdoc} */
	@Override
	public String toString()
	{
		return is_idTopologiaRegla;
	}
}
