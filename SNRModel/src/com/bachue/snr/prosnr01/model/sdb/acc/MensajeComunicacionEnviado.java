package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.StringUtils;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase de abstracción de la base de datos para la tabla SBD_ACC_MENSAJE_COMUNICACION_ENVIADO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class MensajeComunicacionEnviado extends MensajeComunicacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7523561291689684473L;

	/** Propiedad is id mensaje comunicacion enviado. */
	private String is_idMensajeComunicacionEnviado;

	/**
	 * Instancia un nuevo objeto mensaje comunicacion enviado.
	 */
	public MensajeComunicacionEnviado()
	{
	}

	/**
	 * Instancia un nuevo objeto mensaje comunicacion enviado.
	 *
	 * @param as_idMensaje de String
	 * @param al_idTurnoHistoria de long
	 * @param as_idSolicitud de String
	 * @param ad_fechaRespuesta de Date
	 * @param as_descripcionMensaje de String
	 * @param as_userId de String
	 * @param as_ipRemota de String
	 */
	public MensajeComunicacionEnviado(
	    String as_idMensaje, long al_idTurnoHistoria, String as_idSolicitud, Date ad_fechaRespuesta,
	    String as_descripcionMensaje, String as_userId, String as_ipRemota
	)
	{
		setIdMensaje(as_idMensaje);
		setIdTurnoHistoria(StringUtils.getString(al_idTurnoHistoria));
		setIdSolicitud(as_idSolicitud);
		setFechaExitoEjecucionAutomatica(ad_fechaRespuesta);
		setRespuestasEjecucionAutomatica(as_descripcionMensaje);
		setIdUsuarioCreacion(as_userId);
		setIpCreacion(as_ipRemota);
	}

	/**
	 * Modifica el valor de IdMensajeComunicacionEnviado.
	 *
	 * @param as_s de as s
	 */
	public void setIdMensajeComunicacionEnviado(String as_s)
	{
		is_idMensajeComunicacionEnviado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje comunicacion enviado.
	 *
	 * @return el valor de id mensaje comunicacion enviado
	 */
	public String getIdMensajeComunicacionEnviado()
	{
		return is_idMensajeComunicacionEnviado;
	}
}
