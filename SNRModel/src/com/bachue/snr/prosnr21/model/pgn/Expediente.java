package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_EXPEDIENTE.
 *
 * @author Kevin Pulido
 */
public class Expediente extends Auditoria implements Serializable
{
	/**  Propiedad serialVersionUID. */
	private static final long serialVersionUID = 7239804852839741382L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id expediente. */
	private String is_idExpediente;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo de as activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdExpediente.
	 *
	 * @param as_idExpediente de as id expediente
	 */
	public void setIdExpediente(String as_idExpediente)
	{
		is_idExpediente = as_idExpediente;
	}

	/**
	 * Retorna Objeto o variable de valor id expediente.
	 *
	 * @return el valor de id expediente
	 */
	public String getIdExpediente()
	{
		return is_idExpediente;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_nombre de as nombre
	 */
	public void setNombre(String as_nombre)
	{
		is_nombre = as_nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
