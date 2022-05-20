package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_RECEPCION.
 *
 * @author Julian Vaca
 */
public class TipoRecepcion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9081094714611001462L;

	/** Propiedad is habilitado comunicacion. */
	private String is_habilitadoComunicacion;

	/** Propiedad is habilitado notificacion. */
	private String is_habilitadoNotificacion;

	/** Propiedad is id tipo recepcion. */
	private String is_idTipoRecepcion;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is procedencia. */
	private String is_procedencia;

	/** Propiedad is solo incluir. */
	private String is_soloIncluir;

	/**
	 * Instancia un nuevo objeto tipo recepcion.
	 */
	public TipoRecepcion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo recepcion.
	 *
	 * @param as_idTipoRecepcion correspondiente al valor de as id tipo recepcion
	 */
	public TipoRecepcion(String as_idTipoRecepcion)
	{
		is_idTipoRecepcion = as_idTipoRecepcion;
	}

	/**
	 * Modifica el valor de HabilitadoComunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitadoComunicacion(String as_s)
	{
		this.is_habilitadoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitado comunicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitadoComunicacion()
	{
		return is_habilitadoComunicacion;
	}

	/**
	 * Modifica el valor de HabilitadoNotificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitadoNotificacion(String as_s)
	{
		this.is_habilitadoNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitado notificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitadoNotificacion()
	{
		return is_habilitadoNotificacion;
	}

	/**
	 * Modifica el valor de IdTipoRecepcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRecepcion(String as_s)
	{
		this.is_idTipoRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recepcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRecepcion()
	{
		return is_idTipoRecepcion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
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

	/**
	 * Modifica el valor de Procedencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setProcedencia(String as_s)
	{
		this.is_procedencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor procedencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getProcedencia()
	{
		return is_procedencia;
	}

	/**
	 * Modifica el valor de SoloIncluir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSoloIncluir(String as_s)
	{
		is_soloIncluir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solo incluir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSoloIncluir()
	{
		return is_soloIncluir;
	}
}
