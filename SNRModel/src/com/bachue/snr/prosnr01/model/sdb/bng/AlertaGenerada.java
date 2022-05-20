package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades AlertaGenerada.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 6/04/2020
 */
public class AlertaGenerada extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2687272299857570894L;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is usuario origina. */
	private String is_usuarioOrigina;

	/** Propiedad il id alerta tierra. */
	private long il_idAlertaTierra;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

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
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
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
	 * @param as_s de as s
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
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
	 * @param al_l de al l
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
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
	 * @param as_s de as s
	 */
	public void setUsuarioOrigina(String as_s)
	{
		is_usuarioOrigina = as_s;
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
	 * @param as_s de as s
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

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
	 * @param al_l de al l
	 */
	public void setIdAlertaTierra(long al_l)
	{
		il_idAlertaTierra = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}
}
