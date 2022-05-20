package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_PERIODICIDAD.
 *
 * @author Kevin Pulido
 */
public class Periodicidad extends Auditoria implements Serializable
{
	/**  Propiedad serialVersionUID. */
	private static final long serialVersionUID = -8806484985118656933L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id periodicidad. */
	private String is_idPeriodicidad;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is unidad tiempo. */
	private String is_unidadTiempo;

	/** Propiedad il cantidad. */
	private long il_cantidad;

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
	 * Modifica el valor de Cantidad.
	 *
	 * @param al_cantidad de al cantidad
	 */
	public void setCantidad(long al_cantidad)
	{
		il_cantidad = al_cantidad;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad.
	 *
	 * @return el valor de cantidad
	 */
	public long getCantidad()
	{
		return il_cantidad;
	}

	/**
	 * Modifica el valor de IdPeriodicidad.
	 *
	 * @param as_idPeriodicidad de as id periodicidad
	 */
	public void setIdPeriodicidad(String as_idPeriodicidad)
	{
		is_idPeriodicidad = as_idPeriodicidad;
	}

	/**
	 * Retorna Objeto o variable de valor id periodicidad.
	 *
	 * @return el valor de id periodicidad
	 */
	public String getIdPeriodicidad()
	{
		return is_idPeriodicidad;
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

	/**
	 * Modifica el valor de UnidadTiempo.
	 *
	 * @param as_unidadTiempo de as unidad tiempo
	 */
	public void setUnidadTiempo(String as_unidadTiempo)
	{
		is_unidadTiempo = as_unidadTiempo;
	}

	/**
	 * Retorna Objeto o variable de valor unidad tiempo.
	 *
	 * @return el valor de unidad tiempo
	 */
	public String getUnidadTiempo()
	{
		return is_unidadTiempo;
	}
}
