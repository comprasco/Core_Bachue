package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CAUSAL_NEGACION_COPIAS.
 *
 * @author Luis Chacón
 */
public class CausalNegacionCopias extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                   = 3094544580144239672L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion causal negacion copias. */
	private String            is_descripcionCausalNegacionCopias;
	
	/** Propiedad is id causal negacion copias. */
	private String            is_idCausalNegacionCopias;

	/**
	 * Constructor por defecto.
	 */
	public CausalNegacionCopias()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                                = as_s;
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
	 * Modifica el valor de DescripcionCausalNegacionCopias.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionCausalNegacionCopias(String as_s)
	{
		is_descripcionCausalNegacionCopias = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion causal negacion copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionCausalNegacionCopias()
	{
		return is_descripcionCausalNegacionCopias;
	}

	/**
	 * Modifica el valor de IdCausalNegacionCopias.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCausalNegacionCopias(String as_s)
	{
		is_idCausalNegacionCopias = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal negacion copias.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCausalNegacionCopias()
	{
		return is_idCausalNegacionCopias;
	}
}
