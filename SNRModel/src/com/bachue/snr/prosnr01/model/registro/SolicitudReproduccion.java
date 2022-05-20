package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades de la tabla SDB_ACC_SOLICITUD_REPRODUCCION.
 *
 * @author Julian Vaca
 */
public class SolicitudReproduccion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -986743675405010618L;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud reproduccion. */
	private String is_idSolicitudReproduccion;

	/** Propiedad is id turno reproduccion. */
	private String is_idTurnoReproduccion;

	/** Propiedad is secuence. */
	private String is_secuence;

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param idCirculo asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String idCirculo)
	{
		this.is_idCirculo = idCirculo;
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
	 * Modifica el valor de id solicitud.
	 *
	 * @param idSolicitud asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String idSolicitud)
	{
		this.is_idSolicitud = idSolicitud;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id solicitud reproduccion.
	 *
	 * @param idSolicitudReproduccion asigna el valor a la propiedad id solicitud reproduccion
	 */
	public void setIdSolicitudReproduccion(String idSolicitudReproduccion)
	{
		this.is_idSolicitudReproduccion = idSolicitudReproduccion;
	}

	/**
	 * Retorna el valor de id solicitud reproduccion.
	 *
	 * @return el valor de id solicitud reproduccion
	 */
	public String getIdSolicitudReproduccion()
	{
		return is_idSolicitudReproduccion;
	}

	/**
	 * Modifica el valor de id turno reproduccion.
	 *
	 * @param idTurnoReproduccion asigna el valor a la propiedad id turno reproduccion
	 */
	public void setIdTurnoReproduccion(String idTurnoReproduccion)
	{
		this.is_idTurnoReproduccion = idTurnoReproduccion;
	}

	/**
	 * Retorna el valor de id turno reproduccion.
	 *
	 * @return el valor de id turno reproduccion
	 */
	public String getIdTurnoReproduccion()
	{
		return is_idTurnoReproduccion;
	}

	/**
	 * Modifica el valor de secuence.
	 *
	 * @param as_s asigna el valor a la propiedad secuence
	 */
	public void setSecuence(String as_s)
	{
		this.is_secuence = as_s;
	}

	/**
	 * Retorna el valor de secuence.
	 *
	 * @return el valor de secuence
	 */
	public String getSecuence()
	{
		return is_secuence;
	}
}
