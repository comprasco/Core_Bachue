package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de TipoCalculo.
 *
 * @author Julian Vaca
 */
public class TipoCalculo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8692594399127036062L;
	
	/** Propiedad is id tipo calculo. */
	private String            is_idTipoCalculo;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TipoCalculo()
	{
	}

	/**
	 * Modifica el valor de IdTipoCalculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCalculo(String as_s)
	{
		is_idTipoCalculo                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo calculo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCalculo()
	{
		return is_idTipoCalculo;
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
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
