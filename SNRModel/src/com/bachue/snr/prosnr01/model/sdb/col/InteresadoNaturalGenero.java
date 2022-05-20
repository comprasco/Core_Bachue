package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_COL_INTERESADO_NATURAL_GENERO.
 *
 * @author Julian Vaca
 */
public class InteresadoNaturalGenero extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = 6952711126899066693L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id natural genero. */
	private String            is_idNaturalGenero;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                           = as_s;
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
	 * Modifica el valor de IdNaturalGenero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNaturalGenero(String as_s)
	{
		this.is_idNaturalGenero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id natural genero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNaturalGenero()
	{
		return is_idNaturalGenero;
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
