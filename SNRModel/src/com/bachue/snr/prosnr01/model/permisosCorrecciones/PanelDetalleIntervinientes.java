package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelDetalleIntervinientes.
 *
 * @author garias
 */
public class PanelDetalleIntervinientes implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2430407981513876796L;

	/** Propiedad ib interviniente modificado. */
	private boolean ib_intervinienteModificado;

	/** Propiedad ibl calidad interviniente. */
	private boolean ibl_calidadInterviniente;

	/** Propiedad ibl datos persona. */
	private boolean ibl_datosPersona;

	/** Propiedad ibl porcentaje. */
	private boolean ibl_porcentaje;

	/** Propiedad ibl propietario. */
	private boolean ibl_propietario;

	/** Propiedad ibl rol. */
	private boolean ibl_rol;

	/**
	 * Modifica el valor de calidad interviniente.
	 *
	 * @param ab_b asigna el valor a la propiedad calidad interviniente
	 */
	public void setCalidadInterviniente(boolean ab_b)
	{
		ibl_calidadInterviniente = ab_b;
	}

	/**
	 * Valida la propiedad calidad interviniente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en calidad interviniente
	 */
	public boolean isCalidadInterviniente()
	{
		return ibl_calidadInterviniente;
	}

	/**
	 * Modifica el valor de datos persona.
	 *
	 * @param ab_b asigna el valor a la propiedad datos persona
	 */
	public void setDatosPersona(boolean ab_b)
	{
		ibl_datosPersona = ab_b;
	}

	/**
	 * Valida la propiedad datos persona.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos persona
	 */
	public boolean isDatosPersona()
	{
		return ibl_datosPersona;
	}

	/**
	 * Modifica el valor de interviniente modificado.
	 *
	 * @param ab_b asigna el valor a la propiedad interviniente modificado
	 */
	public void setIntervinienteModificado(boolean ab_b)
	{
		ib_intervinienteModificado = ab_b;
	}

	/**
	 * Valida la propiedad interviniente modificado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en interviniente modificado
	 */
	public boolean isIntervinienteModificado()
	{
		return ib_intervinienteModificado;
	}

	/**
	 * Modifica el valor de porcentaje.
	 *
	 * @param ab_b asigna el valor a la propiedad porcentaje
	 */
	public void setPorcentaje(boolean ab_b)
	{
		ibl_porcentaje = ab_b;
	}

	/**
	 * Valida la propiedad porcentaje.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en porcentaje
	 */
	public boolean isPorcentaje()
	{
		return ibl_porcentaje;
	}

	/**
	 * Modifica el valor de propietario.
	 *
	 * @param ab_b asigna el valor a la propiedad propietario
	 */
	public void setPropietario(boolean ab_b)
	{
		ibl_propietario = ab_b;
	}

	/**
	 * Valida la propiedad propietario.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en propietario
	 */
	public boolean isPropietario()
	{
		return ibl_propietario;
	}

	/**
	 * Modifica el valor de rol.
	 *
	 * @param ab_b asigna el valor a la propiedad rol
	 */
	public void setRol(boolean ab_b)
	{
		ibl_rol = ab_b;
	}

	/**
	 * Valida la propiedad rol.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rol
	 */
	public boolean isRol()
	{
		return ibl_rol;
	}
}
