package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades AlertaMSG.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 6/04/2020
 */
public class AlertaMSG extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7777120747660072306L;

	/** Propiedad id fecha msg. */
	private Date id_fechaMsg;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id seguimiento msg. */
	private String is_idSeguimientoMsg;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is tipo mensaje. */
	private String is_tipoMensaje;

	/** Propiedad is usuario origina. */
	private String is_usuarioOrigina;

	/** Propiedad il id alerta tierra. */
	private long il_idAlertaTierra;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad il secuencia. */
	private long il_secuencia;

	/**
	 * Retorna Objeto o variable de valor id alerta tierra.
	 *
	 * @return Retorna el valor de la propiedad idAlertaTierra
	 */
	public long getIdAlertaTierra()
	{
		return il_idAlertaTierra;
	}

	/**
	 * Modifica el valor de IdAlertaTierra.
	 *
	 * @param al_l de id alerta tierra
	 */
	public void setIdAlertaTierra(long al_l)
	{
		this.il_idAlertaTierra = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor secuencia.
	 *
	 * @return Retorna el valor de la propiedad secuencia
	 */
	public long getSecuencia()
	{
		return il_secuencia;
	}

	/**
	 * Modifica el valor de Secuencia.
	 *
	 * @param al_l de secuencia
	 */
	public void setSecuencia(long al_l)
	{
		this.il_secuencia = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de id matricula
	 */
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s de id turno
	 */
	public void setIdTurno(String as_s)
	{
		this.is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo mensaje.
	 *
	 * @return Retorna el valor de la propiedad tipoMensaje
	 */
	public String getTipoMensaje()
	{
		return is_tipoMensaje;
	}

	/**
	 * Modifica el valor de TipoMensaje.
	 *
	 * @param as_s de tipo mensaje
	 */
	public void setTipoMensaje(String as_s)
	{
		this.is_tipoMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario origina.
	 *
	 * @return Retorna el valor de la propiedad usuarioOrigina
	 */
	public String getUsuarioOrigina()
	{
		return is_usuarioOrigina;
	}

	/**
	 * Modifica el valor de UsuarioOrigina.
	 *
	 * @param as_s de usuario origina
	 */
	public void setUsuarioOrigina(String as_s)
	{
		this.is_usuarioOrigina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje.
	 *
	 * @return Retorna el valor de la propiedad mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de Mensaje.
	 *
	 * @param as_s de mensaje
	 */
	public void setMensaje(String as_s)
	{
		this.is_mensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id seguimiento msg.
	 *
	 * @return Retorna el valor de la propiedad idSeguimientoMsg
	 */
	public String getIdSeguimientoMsg()
	{
		return is_idSeguimientoMsg;
	}

	/**
	 * Modifica el valor de IdSeguimientoMsg.
	 *
	 * @param as_s de id seguimiento msg
	 */
	public void setIdSeguimientoMsg(String as_s)
	{
		this.is_idSeguimientoMsg = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha msg.
	 *
	 * @return Retorna el valor de la propiedad fechaMsg
	 */
	public Date getFechaMsg()
	{
		return id_fechaMsg;
	}

	/**
	 * Modifica el valor de FechaMsg.
	 *
	 * @param ad_d de fecha msg
	 */
	public void setFechaMsg(Date ad_d)
	{
		this.id_fechaMsg = ad_d;
	}
}
