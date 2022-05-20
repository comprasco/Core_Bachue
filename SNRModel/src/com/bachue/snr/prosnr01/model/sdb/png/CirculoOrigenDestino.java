package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades EstadoPredio.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 02/07/2020
 */
public class CirculoOrigenDestino extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1492715791603009623L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id circulo destino. */
	private String is_idCirculoDestino;

	/** Propiedad is id circulo origen. */
	private String is_idCirculoOrigen;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdCirculoDestino.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoDestino(String as_s)
	{
		is_idCirculoDestino = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo destino.
	 *
	 * @return Retorna el valor de la propiedad idCirculoDestino
	 */
	public String getIdCirculoDestino()
	{
		return is_idCirculoDestino;
	}

	/**
	 * Modifica el valor de IdCirculoOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoOrigen(String as_s)
	{
		is_idCirculoOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo origen.
	 *
	 * @return Retorna el valor de la propiedad idCirculoOrigen
	 */
	public String getIdCirculoOrigen()
	{
		return is_idCirculoOrigen;
	}
}
