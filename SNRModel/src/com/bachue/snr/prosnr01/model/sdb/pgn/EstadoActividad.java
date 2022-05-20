package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Logica de modelo Etapa siendo una abstracción de la tabla
 * SDB_PGN_ESTADO_ACTIVIDAD en la base de datos.
 *
 * @author Gabriel Arias
 */
public class EstadoActividad extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = 2054566782447997198L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id estado actividad. */
	private String            is_idEstadoActividad;

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion                        = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de IdEstadoActividad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoActividad(String as_s)
	{
		this.is_idEstadoActividad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado actividad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoActividad()
	{
		return is_idEstadoActividad;
	}
}
