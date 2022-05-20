package com.bachue.snr.prosnr01.model.calificacion;

import java.io.Serializable;



/**
 * Clase para el manejo de validaciones sobre las alertas en trámite de un turno.
 *
 * @author Manuel Blanco
 */
public class ValidacionAlertaTurno implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -5739617442398994220L;

	/**  Propiedad is_mensajeTurnoAsociado. */
	private String is_mensajeTurnoAsociado;

	/**  Propiedad ib_turnoAfectado. */
	private boolean ib_turnoAfectado;

	/**  Propiedad ib_turnoAsociado. */
	private boolean ib_turnoAsociado;

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return Obtiene el valor de la propiedad
	 */
	public boolean isTurnoAfectado()
	{
		return ib_turnoAfectado;
	}

	/**
	 * Asigna el valor a la propiedad.
	 *
	 * @param ab_b Asigna el valor a la propiedad
	 */
	public void setTurnoAfectado(boolean ab_b)
	{
		ib_turnoAfectado = ab_b;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return Obtiene el valor de la propiedad
	 */
	public boolean isTurnoAsociado()
	{
		return ib_turnoAsociado;
	}

	/**
	 * Asigna el valor a la propiedad.
	 *
	 * @param ab_b Asigna el valor a la propiedad
	 */
	public void setTurnoAsociado(boolean ab_b)
	{
		ib_turnoAsociado = ab_b;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return Obtiene el valor de la propiedad
	 */
	public String getMensajeTurnoAsociado()
	{
		return is_mensajeTurnoAsociado;
	}

	/**
	 * Asigna el valor a la propiedad.
	 *
	 * @param as_s Asigna el valor a la propiedad
	 */
	public void setMensajeTurnoAsociado(String as_s)
	{
		is_mensajeTurnoAsociado = as_s;
	}
}
