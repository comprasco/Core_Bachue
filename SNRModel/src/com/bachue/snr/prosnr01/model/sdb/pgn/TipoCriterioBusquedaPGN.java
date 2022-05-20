package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 *
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA.
 * @author ssanchez
 *
 */
public class TipoCriterioBusquedaPGN extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2271209042191317187L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is criterio condicion. */
	private String is_criterioCondicion;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id tipo criterio busqueda. */
	private String is_idTipoCriterioBusqueda;

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
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
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
	 * Modifica el valor de IdTipoCriterioBusqueda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCriterioBusqueda(String as_s)
	{
		is_idTipoCriterioBusqueda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCriterioBusqueda()
	{
		return is_idTipoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de CriterioCondicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCriterioCondicion(String as_s)
	{
		is_criterioCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor criterio condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCriterioCondicion()
	{
		return is_criterioCondicion;
	}
}
