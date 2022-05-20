package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstracción de la tabla SDB_PGN_TIPO_REQUIERE_MATRICULA para el manejo de sus
 * registros y transacciones.
 *
 * @author Manuel Blanco
 *
 */
public class TipoRequiereMatricula extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID           = -859854327510760889L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id subproceso. */
	private String            is_idSubproceso;
	
	/** Propiedad is id tipo requiere matricula. */
	private String            is_idTipoRequiereMatricula;
	
	/** Propiedad ib desactivar. */
	private boolean           ib_desactivar;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                   = as_s;
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
	 * Modifica el valor de Desactivar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDesactivar(boolean ab_b)
	{
		this.ib_desactivar = ab_b;
	}

	/**
	 * Valida la propiedad desactivar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDesactivar()
	{
		return ib_desactivar;
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
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		this.is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		this.is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTipoRequiereMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRequiereMatricula(String as_s)
	{
		this.is_idTipoRequiereMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo requiere matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRequiereMatricula()
	{
		return is_idTipoRequiereMatricula;
	}
}
