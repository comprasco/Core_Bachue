package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_CATALOGO_CONSULTA.
 *
 * @author Julian Vaca
 */
public class CatalogoConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3474494991562421475L;

	/** Propiedad is catalogo. */
	private String is_catalogo;

	/** Componente al que pertenece el catalogo */
	private String is_componente;

	/** Propiedad is procedimiento. */
	private String is_procedimiento;

	/**
	 * Modifica el valor de Catalogo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCatalogo(String as_s)
	{
		is_catalogo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor catalogo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCatalogo()
	{
		return is_catalogo;
	}

	/**
	 * Modifica el valor de Componente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComponente(String as_s)
	{
		is_componente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComponente()
	{
		return is_componente;
	}

	/**
	 * Modifica el valor de Procedimiento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setProcedimiento(String as_s)
	{
		is_procedimiento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor procedimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getProcedimiento()
	{
		return is_procedimiento;
	}
}
