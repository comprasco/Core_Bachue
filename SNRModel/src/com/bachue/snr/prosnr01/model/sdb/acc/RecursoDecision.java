package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_RECURSO_DECISION.
 *
 * @author Luis Chacón
 * Fecha de Creacion: 19/06/2020
 */
public class RecursoDecision extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -37034098140283958L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id recurso. */
	private String is_idRecurso;

	/** Propiedad is id recurso decision. */
	private String is_idRecursoDecision;

	/** Propiedad is id tipo decision recurso. */
	private String is_idTipoDecisionRecurso;

	/** Propiedad is id tipo recurso. */
	private String is_idTipoRecurso;

	/** Propiedad is id turno. */
	private String is_idTurno;

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
	 * @param Modifica el valor de la propiedad idRecurso por as_s
	 */
	public void setIdRecurso(String as_s)
	{
		is_idRecurso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idRecurso
	 */
	public String getIdRecurso()
	{
		return is_idRecurso;
	}

	/**
	 * @param Modifica el valor de la propiedad idRecursoDecision por as_s
	 */
	public void setIdRecursoDecision(String as_s)
	{
		is_idRecursoDecision = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idRecursoDecision
	 */
	public String getIdRecursoDecision()
	{
		return is_idRecursoDecision;
	}

	/**
	 * Modifica el valor de IdTipoDecisionRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDecisionRecurso(String as_s)
	{
		is_idTipoDecisionRecurso = as_s;
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

	/**
	 * Modifica el valor de IdTipoRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRecurso(String as_s)
	{
		is_idTipoRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recurso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRecurso()
	{
		return is_idTipoRecurso;
	}

	/**
	 * @param Modifica el valor de la propiedad idTurno por as_s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}
}
