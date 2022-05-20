package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo que contiene todos los atributos de TipoAnotacion.java
 * @author Julian Vaca
 *
 */
public class TipoAnotacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -6828375224942465946L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tipo anotacion. */
	private String            is_idTipoAnotacion;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TipoAnotacion()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.is_activo                           = activo;
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
	 * Modifica el valor de IdTipoAnotacion.
	 *
	 * @param idTipoAnotacion asigna el valor a la propiedad
	 */
	public void setIdTipoAnotacion(String idTipoAnotacion)
	{
		this.is_idTipoAnotacion = idTipoAnotacion;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoAnotacion()
	{
		return is_idTipoAnotacion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
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
