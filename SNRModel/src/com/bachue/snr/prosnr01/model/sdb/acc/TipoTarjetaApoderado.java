package com.bachue.snr.prosnr01.model.sdb.acc;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_TARJETA_APODERADO.
 *
 * @author Julian Vaca
 */
public class TipoTarjetaApoderado implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = -9081094714611001462L;
	
	/** Propiedad fecha creacion. */
	private Date              fechaCreacion;
	
	/** Propiedad fecha modificacion. */
	private Date              fechaModificacion;
	
	/** Propiedad activo. */
	private String            activo;
	
	/** Propiedad descripcion. */
	private String            descripcion;
	
	/** Propiedad id tipo tarjeta apoderado. */
	private String            idTipoTarjetaApoderado;
	
	/** Propiedad id usuario creacion. */
	private String            idUsuarioCreacion;
	
	/** Propiedad id usuario modificacion. */
	private String            idUsuarioModificacion;
	
	/** Propiedad ip creacion. */
	private String            ipCreacion;
	
	/** Propiedad ip modificacion. */
	private String            ipModificacion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.activo                                  = activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return activo;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return descripcion;
	}

	/**
	 * Modifica el valor de FechaCreacion.
	 *
	 * @param fechaCreacion asigna el valor a la propiedad
	 */
	public void setFechaCreacion(Date fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaCreacion()
	{
		return fechaCreacion;
	}

	/**
	 * Modifica el valor de FechaModificacion.
	 *
	 * @param fechaModificacion asigna el valor a la propiedad
	 */
	public void setFechaModificacion(Date fechaModificacion)
	{
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaModificacion()
	{
		return fechaModificacion;
	}

	/**
	 * Modifica el valor de IdTipoTarjetaApoderado.
	 *
	 * @param idTipoTarjetaApoderado asigna el valor a la propiedad
	 */
	public void setIdTipoTarjetaApoderado(String idTipoTarjetaApoderado)
	{
		this.idTipoTarjetaApoderado = idTipoTarjetaApoderado;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo tarjeta apoderado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTarjetaApoderado()
	{
		return idTipoTarjetaApoderado;
	}

	/**
	 * Modifica el valor de IdUsuarioCreacion.
	 *
	 * @param idUsuarioCreacion asigna el valor a la propiedad
	 */
	public void setIdUsuarioCreacion(String idUsuarioCreacion)
	{
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuarioCreacion()
	{
		return idUsuarioCreacion;
	}

	/**
	 * Modifica el valor de IdUsuarioModificacion.
	 *
	 * @param idUsuarioModificacion asigna el valor a la propiedad
	 */
	public void setIdUsuarioModificacion(String idUsuarioModificacion)
	{
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuarioModificacion()
	{
		return idUsuarioModificacion;
	}

	/**
	 * Modifica el valor de IpCreacion.
	 *
	 * @param ipCreacion asigna el valor a la propiedad
	 */
	public void setIpCreacion(String ipCreacion)
	{
		this.ipCreacion = ipCreacion;
	}

	/**
	 * Retorna Objeto o variable de valor ip creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpCreacion()
	{
		return ipCreacion;
	}

	/**
	 * Modifica el valor de IpModificacion.
	 *
	 * @param ipModificacion asigna el valor a la propiedad
	 */
	public void setIpModificacion(String ipModificacion)
	{
		this.ipModificacion = ipModificacion;
	}

	/**
	 * Retorna Objeto o variable de valor ip modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIpModificacion()
	{
		return ipModificacion;
	}
}
