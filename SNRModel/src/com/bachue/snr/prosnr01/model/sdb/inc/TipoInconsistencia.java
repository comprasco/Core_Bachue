package com.bachue.snr.prosnr01.model.sdb.inc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla  SDB_INC_TIPO_INCONSISTENCIA.
 *
 * @author Julian Vaca
 */
public class TipoInconsistencia extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -7489159760123072726L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo inconsistencia. */
	private String            is_idTipoInconsistencia;

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion                           = as_s;
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
	 * Modifica el valor de IdTipoInconsistencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoInconsistencia(String as_s)
	{
		this.is_idTipoInconsistencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo inconsistencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoInconsistencia()
	{
		return is_idTipoInconsistencia;
	}
}
