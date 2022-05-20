package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ActoNuevaEntrada.
 *
 * @author Julian Vaca
 */
public class ActoNuevaEntrada implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6132207012489649730L;

	/** Propiedad ia acto. */
	private Acto ia_acto;

	/** Propiedad ib save info all. */
	private boolean ib_saveInfoAll;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param aa_a asigna el valor a la propiedad acto
	 */
	public void setActo(Acto aa_a)
	{
		ia_acto = aa_a;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public Acto getActo()
	{
		return ia_acto;
	}

	/**
	 * Modifica el valor de save info all.
	 *
	 * @param ab_b asigna el valor a la propiedad save info all
	 */
	public void setSaveInfoAll(boolean ab_b)
	{
		ib_saveInfoAll = ab_b;
	}

	/**
	 * Valida la propiedad save info all.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en save info all
	 */
	public boolean isSaveInfoAll()
	{
		return ib_saveInfoAll;
	}
}
