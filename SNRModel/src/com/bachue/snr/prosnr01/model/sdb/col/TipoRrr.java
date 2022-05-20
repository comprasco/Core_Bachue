package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla  SDB_COL_TIPO_RRR.
 *
 * @author Julian Vaca
 */
public class TipoRrr extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3793135736064838997L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo rrr. */
	private String            is_idTipoRrr;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

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

	/**
	 * Modifica el valor de Ilicode.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIlicode(String as_s)
	{
		this.is_ilicode = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ilicode.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIlicode()
	{
		return is_ilicode;
	}
}
