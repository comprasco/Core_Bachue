package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.sql.Timestamp;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO.
 *
 * @author Julian Vaca
 */
public class TmpSolicitudMatriculaActo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -870964315887887475L;
	
	/** Propiedad is accion. */
	private String            is_accion;
	
	/** Propiedad is id acto. */
	private String            is_idActo;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is usuario. */
	private String            is_usuario;
	
	/** Propiedad id fecha. */
	private Timestamp         id_fecha;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public TmpSolicitudMatriculaActo()
	{
	}

	/**
	 * Constructor que recibe como parametro la solicitud matricula acto, la accion y el id del usuario.
	 *
	 * @param asma_sma solicitud de la matricula acto
	 * @param as_accion accion
	 * @param as_userId id del usuario
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo
	 */
	public TmpSolicitudMatriculaActo(SolicitudMatriculaActo asma_sma, String as_accion, String as_userId)
	{
		is_accion                              = as_accion;
		is_idActo                              = asma_sma.getIdActo();
		is_idCirculo                           = asma_sma.getIdCirculo();
		is_idSolicitud                         = asma_sma.getIdSolicitud();
		is_idTurno                             = asma_sma.getIdTurno();
		is_usuario                             = asma_sma.getUsuario();
		il_idMatricula                         = asma_sma.getIdMatricula();
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccion(String as_s)
	{
		this.is_accion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccion()
	{
		return is_accion;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFecha(Timestamp ad_d)
	{
		this.id_fecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Timestamp getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de IdActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActo(String as_s)
	{
		this.is_idActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		this.is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		this.is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		this.is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}
}
