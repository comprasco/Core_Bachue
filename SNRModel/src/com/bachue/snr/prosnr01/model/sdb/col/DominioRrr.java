package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_COL_DOMINIO_RRR.
 *
 * @author JHulian Vaca
 */
public class DominioRrr extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1382893549406206449L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id dominio rrr. */
	private String            is_idDominioRrr;
	
	/** Propiedad is id tipo rrr. */
	private String            is_idTipoRrr;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                         = as_s;
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
		this.is_descripcion = as_s;
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
	 * Modifica el valor de IdDominioRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDominioRrr(String as_s)
	{
		this.is_idDominioRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dominio rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDominioRrr()
	{
		return is_idDominioRrr;
	}

	/**
	 * Modifica el valor de IdTipoRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRrr(String as_s)
	{
		this.is_idTipoRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRrr()
	{
		return is_idTipoRrr;
	}
}
