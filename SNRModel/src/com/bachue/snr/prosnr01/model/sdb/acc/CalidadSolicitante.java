package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_CALIDAD_SOLICITANTE.
 *
 * @author Julian Vaca
 */
public class CalidadSolicitante extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7185558199619617819L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id calidad solicitante. */
	private String is_idCalidadSolicitante;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is solo incluir. */
	private String is_soloIncluir;

	/**
	 * Constructor por defecto.
	 */
	public CalidadSolicitante()
	{
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
	 * Modifica el valor de IdCalidadSolicitante.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCalidadSolicitante(String as_s)
	{
		is_idCalidadSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id calidad solicitante.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCalidadSolicitante()
	{
		return is_idCalidadSolicitante;
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
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de SoloIncluir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSoloIncluir(String as_s)
	{
		is_soloIncluir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solo incluir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSoloIncluir()
	{
		return is_soloIncluir;
	}
}
