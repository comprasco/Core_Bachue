package com.bachue.snr.prosnr10.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades ServicioActualizacionHaciaCatastros.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/06/2020
 */
public class ServicioActualizacionHaciaCatastros extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 740797118955414245L;

	/** Propiedad fecha notificacion. */
	private Date id_fechaNotificacion;

	/** Propiedad fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad estado registro. */
	private String is_estadoRegistro;

	/** Propiedad id tipo operacion. */
	private String is_idTipoOperacion;

	/** Propiedad id transaccion. */
	private String is_idTransaccion;

	/** Propiedad numero reintentos. */
	private long il_numeroReintentos;

	/**
	 * Instancia un nuevo objeto servicio actualizacion hacia catastros.
	 */
	public ServicioActualizacionHaciaCatastros()
	{
	}

	/**
	 * Instancia un nuevo objeto servicio actualizacion hacia catastros.
	 *
	 * @param as_idTransaccion de as id transaccion
	 * @param as_idTipoOperacion de as id tipo operacion
	 */
	public ServicioActualizacionHaciaCatastros(
	    String as_idTransaccion, String as_idTipoOperacion, String as_userId, String as_remoteIp
	)
	{
		is_idTransaccion       = as_idTransaccion;
		is_idTipoOperacion     = as_idTipoOperacion;
		setIdUsuarioCreacion(as_userId);
		setIpCreacion(as_remoteIp);
	}

	/**
	 * Instancia un nuevo objeto servicio actualizacion hacia catastros.
	 *
	 * @param as_idTransaccion de as id transaccion
	 */
	public ServicioActualizacionHaciaCatastros(String as_idTransaccion)
	{
		is_idTransaccion = as_idTransaccion;
	}

	/**
	 * Modifica el valor de EstadoRegistro.
	 *
	 * @param as_estadoRegistro de as estado registro
	 */
	public void setEstadoRegistro(String as_estadoRegistro)
	{
		is_estadoRegistro = as_estadoRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor estado registro.
	 *
	 * @return Retorna el valor de la propiedad estadoRegistro
	 */
	public String getEstadoRegistro()
	{
		return is_estadoRegistro;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_fechaNotificacion de ad fecha notificacion
	 */
	public void setFechaNotificacion(Date ad_fechaNotificacion)
	{
		id_fechaNotificacion = ad_fechaNotificacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return Retorna el valor de la propiedad fechaNotificacion
	 */
	public Date getFechaNotificacion()
	{
		return id_fechaNotificacion;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_fechaRegistro de ad fecha registro
	 */
	public void setFechaRegistro(Date ad_fechaRegistro)
	{
		id_fechaRegistro = ad_fechaRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return Retorna el valor de la propiedad fechaRegistro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de IdTipoOperacion.
	 *
	 * @param as_idTipoOperacion de as id tipo operacion
	 */
	public void setIdTipoOperacion(String as_idTipoOperacion)
	{
		is_idTipoOperacion = as_idTipoOperacion;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo operacion.
	 *
	 * @return Retorna el valor de la propiedad idTipoOperacion
	 */
	public String getIdTipoOperacion()
	{
		return is_idTipoOperacion;
	}

	/**
	 * Modifica el valor de IdTransaccion.
	 *
	 * @param as_idTransaccion de as id transaccion
	 */
	public void setIdTransaccion(String as_idTransaccion)
	{
		is_idTransaccion = as_idTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor id transaccion.
	 *
	 * @return Retorna el valor de la propiedad idTransaccion
	 */
	public String getIdTransaccion()
	{
		return is_idTransaccion;
	}

	/**
	 * Modifica el valor de NumeroReintentos.
	 *
	 * @param al_numeroReintentos de al numero reintentos
	 */
	public void setNumeroReintentos(long al_numeroReintentos)
	{
		il_numeroReintentos = al_numeroReintentos;
	}

	/**
	 * Retorna Objeto o variable de valor numero reintentos.
	 *
	 * @return Retorna el valor de la propiedad numeroReintentos
	 */
	public long getNumeroReintentos()
	{
		return il_numeroReintentos;
	}
}
