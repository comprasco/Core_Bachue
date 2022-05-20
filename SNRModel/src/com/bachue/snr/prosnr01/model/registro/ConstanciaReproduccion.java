package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ConstanciaReproduccion.
 *
 * @author Julian Vaca
 */
public class ConstanciaReproduccion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2593865233743381074L;

	/** Propiedad isr solicitud reproduccion. */
	private SolicitudReproduccion isr_solicitudReproduccion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de solicitud reproduccion.
	 *
	 * @param asr_sr asigna el valor a la propiedad solicitud reproduccion
	 */
	public void setSolicitudReproduccion(SolicitudReproduccion asr_sr)
	{
		isr_solicitudReproduccion = asr_sr;
	}

	/**
	 * Retorna el valor de solicitud reproduccion.
	 *
	 * @return el valor de solicitud reproduccion
	 */
	public SolicitudReproduccion getSolicitudReproduccion()
	{
		return isr_solicitudReproduccion;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}
}
