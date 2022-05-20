package com.bachue.snr.prosnr04.model.npa;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades TipoCriterio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoCriterio extends TipoServicio implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1411221314172743104L;
	
	/** Propiedad is codigo. */
	private String            is_codigo;
	
	/** Propiedad is valor. */
	private String            is_valor;

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigo(String as_s)
	{
		this.is_codigo                         = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setValor(String as_s)
	{
		this.is_valor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValor()
	{
		return is_valor;
	}
}
