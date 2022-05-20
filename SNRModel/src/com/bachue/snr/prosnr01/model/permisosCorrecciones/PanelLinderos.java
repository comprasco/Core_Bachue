package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelLinderos.
 *
 * @author garias
 */
public class PanelLinderos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1881373559023649784L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.LINDEROS;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib linderos. */
	private boolean ib_linderos;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de copiar.
	 *
	 * @param as_s asigna el valor a la propiedad copiar
	 */
	public void setCopiar(String as_s)
	{
		this.is_copiar = as_s;
	}

	/**
	 * Retorna el valor de copiar.
	 *
	 * @return el valor de copiar
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de copiar seleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad copiar seleccionadas
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		this.is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna el valor de copiar seleccionadas.
	 *
	 * @return el valor de copiar seleccionadas
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad justificacion
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna el valor de justificacion.
	 *
	 * @return el valor de justificacion
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de linderos.
	 *
	 * @param ab_b asigna el valor a la propiedad linderos
	 */
	public void setLinderos(boolean ab_b)
	{
		ib_linderos = ab_b;
	}

	/**
	 * Valida la propiedad linderos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en linderos
	 */
	public boolean isLinderos()
	{
		return ib_linderos;
	}

	/**
	 * Modifica el valor de salvedad.
	 *
	 * @param as_s asigna el valor a la propiedad salvedad
	 */
	public void setSalvedad(String as_s)
	{
		is_salvedad = as_s;
	}

	/**
	 * Retorna el valor de salvedad.
	 *
	 * @return el valor de salvedad
	 */
	public String getSalvedad()
	{
		return is_salvedad;
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
