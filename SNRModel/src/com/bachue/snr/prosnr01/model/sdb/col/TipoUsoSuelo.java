package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la talba SDB_COL_TIPO_USO_SUELO.
 *
 * @author Julian Vaca
 */
public class TipoUsoSuelo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID  = -2621007388440345661L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is description. */
	private String            is_description;
	
	/** Propiedad is id tipo uso suelo. */
	private String            is_idTipoUsoSuelo;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                          = as_s;
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
	 * Modifica el valor de Description.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescription(String as_s)
	{
		this.is_description = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor description.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescription()
	{
		return is_description;
	}

	/**
	 * Modifica el valor de IdTipoUsoSuelo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoUsoSuelo(String as_s)
	{
		this.is_idTipoUsoSuelo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo uso suelo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoUsoSuelo()
	{
		return is_idTipoUsoSuelo;
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
