package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_COL_PARTE_INTERESADA.
 *
 * @author Julian Vaca
 */
public class ParteInteresadaRrr extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = 4942731440863206156L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id interesada rrr. */
	private String            is_idInteresadaRrr;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                           = as_s;
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
	 * Modifica el valor de IdInteresadaRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdInteresadaRrr(String as_s)
	{
		this.is_idInteresadaRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id interesada rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdInteresadaRrr()
	{
		return is_idInteresadaRrr;
	}
}
