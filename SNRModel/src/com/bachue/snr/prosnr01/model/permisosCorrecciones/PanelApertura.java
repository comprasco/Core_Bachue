package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelApertura.
 *
 * @author garias
 */
public class PanelApertura extends PanelDatosDocumento implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8726673668776809582L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.INFORMACION_DE_APERTURA;

	/** Propiedad is copiar. */
	private String is_copiar;

	/** Propiedad is copiar seleccionadas. */
	private String is_copiarSeleccionadas;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is salvedad. */
	private String is_salvedad;

	/** Propiedad ib fecha apertura. */
	private boolean ib_fechaApertura;

	/** Propiedad ib radicacion. */
	private boolean ib_radicacion;

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
	 * Modifica el valor de fecha apertura.
	 *
	 * @param ab_b asigna el valor a la propiedad fecha apertura
	 */
	public void setFechaApertura(boolean ab_b)
	{
		ib_fechaApertura = ab_b;
	}

	/**
	 * Valida la propiedad fecha apertura.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en fecha apertura
	 */
	public boolean isFechaApertura()
	{
		return ib_fechaApertura;
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
}
