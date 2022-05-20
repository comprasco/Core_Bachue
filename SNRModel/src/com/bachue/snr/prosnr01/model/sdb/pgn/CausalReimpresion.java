package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Causal Reimpresion.
 *
 * @author Sebastian Sanchez
 */
public class CausalReimpresion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7941007069547203088L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is grupo causal. */
	private String is_grupoCausal;

	/** Propiedad is id tipo causal. */
	private String is_idTipoCausal;

	/**
	 * Constructor por defecto.
	 */
	public CausalReimpresion()
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
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor grupo causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGrupoCausal()
	{
		return is_grupoCausal;
	}

	/**
	 * Modifica el valor de GrupoCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGrupoCausal(String as_s)
	{
		is_grupoCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCausal()
	{
		return is_idTipoCausal;
	}

	/**
	 * Modifica el valor de IdTipoCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCausal(String as_s)
	{
		is_idTipoCausal = as_s;
	}
}
