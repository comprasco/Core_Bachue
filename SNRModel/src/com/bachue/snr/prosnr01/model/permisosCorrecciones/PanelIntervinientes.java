package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelIntervinientes.
 *
 * @author garias
 */
public class PanelIntervinientes implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 410898723327993454L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES;

	/** Propiedad ib agregar. */
	private boolean ib_agregar;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de agregar.
	 *
	 * @param ab_b asigna el valor a la propiedad agregar
	 */
	public void setAgregar(boolean ab_b)
	{
		ib_agregar = ab_b;
	}

	/**
	 * Valida la propiedad agregar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en agregar
	 */
	public boolean isAgregar()
	{
		return ib_agregar;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
