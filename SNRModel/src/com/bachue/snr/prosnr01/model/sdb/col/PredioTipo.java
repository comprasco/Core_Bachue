package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_COL_PREDIO_TIPO.
 *
 * @author jpatino
 */
public class PredioTipo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -159877561668520746L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo predio. */
	private String            is_idTipoPredio;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

	/**
	 * Constructor por defecto.
	 */
	public PredioTipo()
	{
	}

	/**
	 * Constructor que recibe como parametro el tipo de predio.
	 *
	 * @param as_s de as s
	 */
	public PredioTipo(String as_s)
	{
		setDescripcion(as_s);
	}

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
	 * Modifica el valor de IdTipoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPredio(String as_s)
	{
		this.is_idTipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPredio()
	{
		return is_idTipoPredio;
	}

	/**
	 * Modifica el valor de Ilicode.
	 *
	 * @param ilicode asigna el valor a la propiedad
	 */
	public void setIlicode(String ilicode)
	{
		this.is_ilicode = ilicode;
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
