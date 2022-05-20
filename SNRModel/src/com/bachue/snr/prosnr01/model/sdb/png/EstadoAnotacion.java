package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PNG_ESTADO_ANOTACION.
 *
 * @author Julian Vaca
 */
public class EstadoAnotacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -3731116564932414288L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id estado anotacion. */
	private String            is_idEstadoAnotacion;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Instancia un nuevo objeto estado anotacion.
	 */
	public EstadoAnotacion()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                             = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdEstadoAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoAnotacion(String as_s)
	{
		this.is_idEstadoAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoAnotacion()
	{
		return is_idEstadoAnotacion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
