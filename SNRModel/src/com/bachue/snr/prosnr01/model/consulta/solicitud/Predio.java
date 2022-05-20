package com.bachue.snr.prosnr01.model.consulta.solicitud;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades Predio.
 *
 * @author Julian Vaca
 */
public class Predio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -182808452325348115L;

	/** Propiedad is acto. */
	private String is_acto;

	/** Propiedad is matricula. */
	private String is_matricula;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param as_a asigna el valor a la propiedad acto
	 */
	public void setActo(String as_a)
	{
		is_acto = as_a;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public String getActo()
	{
		return is_acto;
	}

	/**
	 * Modifica el valor de matricula.
	 *
	 * @param as_a asigna el valor a la propiedad matricula
	 */
	public void setMatricula(String as_a)
	{
		is_matricula = as_a;
	}

	/**
	 * Retorna el valor de matricula.
	 *
	 * @return el valor de matricula
	 */
	public String getMatricula()
	{
		return is_matricula;
	}
}
