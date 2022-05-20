package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de Coordenada.
 *
 * @author Carlos Calderón
 */
public class Coordenada extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1634050833965064783L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id coordenada. */
	private String            is_idCoordenada;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public Coordenada()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id coordenada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCoordenada()
	{
		return is_idCoordenada;
	}

	/**
	 * Modifica el valor de IdCoordenada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCoordenada(String as_s)
	{
		is_idCoordenada                        = as_s;
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

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
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
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
