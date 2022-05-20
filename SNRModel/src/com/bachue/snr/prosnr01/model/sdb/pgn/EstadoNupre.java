package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PNG_ESTADO_NUPRE.
 *
 * @author Carlos Calderón
 */
public class EstadoNupre extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7598798528983838525L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id estado nupre. */
	private String            is_idEstadoNupre;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Retorna Objeto o variable de valor serialversionuid.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                         = as_s;
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
	 * Modifica el valor de IdEstadoNupre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoNupre(String as_s)
	{
		this.is_idEstadoNupre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado nupre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoNupre()
	{
		return is_idEstadoNupre;
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
