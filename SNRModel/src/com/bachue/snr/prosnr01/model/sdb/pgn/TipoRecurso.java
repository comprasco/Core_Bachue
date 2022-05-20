package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_RECURSO.
 *
 * @author Julian Vaca
 */
public class TipoRecurso extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID      = 4308717902590943001L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is dependencia recurso. */
	private String            is_dependenciaRecurso;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo recurso. */
	private String            is_idTipoRecurso;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                              = as_s;
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
	 * Modifica el valor de DependenciaRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDependenciaRecurso(String as_s)
	{
		this.is_dependenciaRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor dependencia recurso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDependenciaRecurso()
	{
		return is_dependenciaRecurso;
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
	 * Modifica el valor de IdTipoRecurso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRecurso(String as_s)
	{
		this.is_idTipoRecurso = as_s;
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
}
