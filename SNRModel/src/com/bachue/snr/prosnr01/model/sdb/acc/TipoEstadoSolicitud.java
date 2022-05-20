package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_ESTADO_SOLICITUD.
 *
 * @author Julian Vaca
 */
public class TipoEstadoSolicitud extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = -8902563376055137036L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tipo estado solicitud. */
	private String            is_idTipoEstadoSolicitud;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                      = as_s;
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
	 * Modifica el valor de IdTipoEstadoSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoEstadoSolicitud(String as_s)
	{
		is_idTipoEstadoSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo estado solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEstadoSolicitud()
	{
		return is_idTipoEstadoSolicitud;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
