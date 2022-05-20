package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_COL_PARTE_INTERESADA.
 *
 * @author Julian Vaca
 */
public class ParteInteresada extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -3177263706394593354L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id parte interesada. */
	private String            is_idParteInteresada;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                             = as_s;
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
	 * Modifica el valor de IdParteInteresada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdParteInteresada(String as_s)
	{
		this.is_idParteInteresada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id parte interesada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdParteInteresada()
	{
		return is_idParteInteresada;
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
