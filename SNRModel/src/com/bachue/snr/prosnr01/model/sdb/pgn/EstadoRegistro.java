package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PNG_ESTADO_REGISTRO.
 *
 * @author Carlos Calderón
 */
public class EstadoRegistro extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 789841264998860829L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id estado registro. */
	private String            is_idEstadoRegistro;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                            = as_s;
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
	 * Modifica el valor de IdEstadoRegistro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEstadoRegistro(String as_s)
	{
		this.is_idEstadoRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id estado registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoRegistro()
	{
		return is_idEstadoRegistro;
	}
}
