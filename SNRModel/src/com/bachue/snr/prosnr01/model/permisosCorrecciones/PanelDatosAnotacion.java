package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelDatosAnotacion.
 *
 * @author hcastaneda
 */
public class PanelDatosAnotacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8356183312065892932L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.DATOS_ANOTACION;

	/** Propiedad ib estado anotacion. */
	private boolean ib_estadoAnotacion;

	/** Propiedad ib fecha anotacion. */
	private boolean ib_fechaAnotacion;

	/** Propiedad ib numero anotacion. */
	private boolean ib_numeroAnotacion;

	/** Propiedad ib radicacion. */
	private boolean ib_radicacion;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de estado anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad estado anotacion
	 */
	public void setEstadoAnotacion(boolean ab_b)
	{
		ib_estadoAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad estado anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado anotacion
	 */
	public boolean isEstadoAnotacion()
	{
		return ib_estadoAnotacion;
	}

	/**
	 * Modifica el valor de fecha anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad fecha anotacion
	 */
	public void setFechaAnotacion(boolean ab_b)
	{
		ib_fechaAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad fecha anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en fecha anotacion
	 */
	public boolean isFechaAnotacion()
	{
		return ib_fechaAnotacion;
	}

	/**
	 * Modifica el valor de numero anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad numero anotacion
	 */
	public void setNumeroAnotacion(boolean ab_b)
	{
		ib_numeroAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad numero anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en numero anotacion
	 */
	public boolean isNumeroAnotacion()
	{
		return ib_numeroAnotacion;
	}

	/**
	 * Modifica el valor de radicacion.
	 *
	 * @param ab_b asigna el valor a la propiedad radicacion
	 */
	public void setRadicacion(boolean ab_b)
	{
		ib_radicacion = ab_b;
	}

	/**
	 * Valida la propiedad radicacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en radicacion
	 */
	public boolean isRadicacion()
	{
		return ib_radicacion;
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
