package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades EstadoPredio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EstadoPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID  = 7342933521527636440L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id estado predio. */
	private String            is_idEstadoPredio;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Instancia un nuevo objeto estado predio.
	 */
	public EstadoPredio()
	{
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo() {
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s) {
		this.is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoPredio() {
		return is_idEstadoPredio;
	}

	/**
	 * Modifica el valor de IdEstadoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoPredio(String as_s) {
		this.is_idEstadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre() {
		return is_nombre;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s) {
		this.is_nombre = as_s;
	}
}
