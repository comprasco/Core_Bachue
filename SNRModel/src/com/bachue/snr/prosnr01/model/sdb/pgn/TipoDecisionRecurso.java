package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_DECISION_RECURSO.
 *
 * @author Julian Vaca
 */
public class TipoDecisionRecurso extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = 8226145341538259430L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo decision recurso. */
	private String            is_idTipoDecisionRecurso;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                 = as_s;
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
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
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
	 * Modifica el valor de IdTipoDecisionRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDecisionRecurso(String as_s)
	{
		this.is_idTipoDecisionRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo decision recurso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDecisionRecurso()
	{
		return is_idTipoDecisionRecurso;
	}
}
