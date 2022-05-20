package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades SoporteTrasladoMatricula.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: ago 24, 2020
 */
public class SoporteTrasladoMatricula extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6589284668689796230L;

	/** Propiedad il matricula destino. */
	private Long il_matriculaDestino;

	/** Propiedad il matricula origen. */
	private Long il_matriculaOrigen;

	/** Propiedad is id circulo destino. */
	private String is_idCirculoDestino;

	/** Propiedad is id circulo origen. */
	private String is_idCirculoOrigen;

	/** Propiedad is id soporte traslado. */
	private String is_idSoporteTraslado;

	/** Propiedad is id soporte traslado matricula. */
	private String is_idSoporteTrasladoMatricula;

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
	 * @return el valor de id circulo destino
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
	 * @return el valor de id circulo origen
	 */
	public String getIdCirculoOrigen()
	{
		return is_idCirculoOrigen;
	}

	/**
	 * Modifica el valor de IdSoporteTraslado.
	 *
	 * @param as_s de as s
	 */
	public void setIdSoporteTraslado(String as_s)
	{
		is_idSoporteTraslado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id soporte traslado.
	 *
	 * @return Retorna el valor de la propiedad idSoporteTraslado
	 */
	public String getIdSoporteTraslado()
	{
		return is_idSoporteTraslado;
	}

	/**
	 * Modifica el valor de IdSoporteTrasladoMatricula.
	 *
	 * @param as_s de as s
	 */
	public void setIdSoporteTrasladoMatricula(String as_s)
	{
		is_idSoporteTrasladoMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id soporte traslado matricula.
	 *
	 * @return Retorna el valor de la propiedad IdSoporteTrasladoMatricula
	 */
	public String getIdSoporteTrasladoMatricula()
	{
		return is_idSoporteTrasladoMatricula;
	}

	/**
	 * Modifica el valor de MatriculaDestino.
	 *
	 * @param al_l de al l
	 */
	public void setMatriculaDestino(Long al_l)
	{
		il_matriculaDestino = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor matricula destino.
	 *
	 * @return el valor de matricula destino
	 */
	public Long getMatriculaDestino()
	{
		return il_matriculaDestino;
	}

	/**
	 * Modifica el valor de MatriculaOrigen.
	 *
	 * @param al_l de al l
	 */
	public void setMatriculaOrigen(Long al_l)
	{
		il_matriculaOrigen = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor matricula origen.
	 *
	 * @return el valor de matricula origen
	 */
	public Long getMatriculaOrigen()
	{
		return il_matriculaOrigen;
	}
}
