package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ALERTA_TURNO_TRAMITE.
 *
 * @author ccalderon
 */
public class AlertaTurnoTramite extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6196413945903055674L;

	/**  Propiedad is fecha inactivación. */
	private Date id_fechaInactivacion;

	/**  Propiedad is_activo. */
	private String is_activo;

	/**  Propiedad is_descripcionAlerta. */
	private String is_descripcionAlerta;

	/**  Propiedad is descripción estado. */
	private String is_descripcionEstado;

	/**  Propiedad is_idAlertaTramite. */
	private String is_idAlertaTramite;

	/**  Propiedad is_idAlertaTurno. */
	private String is_idAlertaTurno;

	/**  Propiedad is id Solicitud. */
	private String is_idSolicitud;

	/**  Propiedad is id Solicitud Vinculada. */
	private String is_idSolicitudVinculada;

	/**  Propiedad is id Turno Afectado. */
	private String is_idTurnoAfectado;

	/**  Propiedad is id Turno asociado. */
	private String is_idTurnoAsociado;
	
	/** Propiedad is matriculas relacionadas. */
	private String is_matriculasRelacionadas;

	/**  Propiedad is nir afectado. */
	private String is_nirAfectado;

	/**  Propiedad is nir vinculado. */
	private String is_nirVinculado;

	/**  Propiedad is turno asociado solicitud. */
	private String is_turnosAsociadosSolicitud;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                              = as_s;
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
	 * Modifica el valor de DescripcionAlerta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionAlerta(String as_s)
	{
		is_descripcionAlerta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion alerta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionAlerta()
	{
		return is_descripcionAlerta;
	}

	/**
	 * Modifica el valor de DescripcionEstado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionEstado(String as_s)
	{
		is_descripcionEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionEstado()
	{
		return is_descripcionEstado;
	}

	/**
	 * Modifica el valor de FechaInactivacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInactivacion(Date ad_d)
	{
		id_fechaInactivacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inactivacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInactivacion()
	{
		return id_fechaInactivacion;
	}

	/**
	 * Modifica el valor de IdAlertaTramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAlertaTramite(String as_s)
	{
		is_idAlertaTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAlertaTramite()
	{
		return is_idAlertaTramite;
	}

	/**
	 * Modifica el valor de IdAlertaTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAlertaTurno(String as_s)
	{
		is_idAlertaTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id alerta turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAlertaTurno()
	{
		return is_idAlertaTurno;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
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
	 * Modifica el valor de IdSolicitudVinculada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitudVinculada(String as_s)
	{
		is_idSolicitudVinculada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud vinculada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitudVinculada()
	{
		return is_idSolicitudVinculada;
	}

	/**
	 * Modifica el valor de IdTurnoAfectado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoAfectado(String as_s)
	{
		is_idTurnoAfectado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno afectado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoAfectado()
	{
		return is_idTurnoAfectado;
	}

	/**
	 * Modifica el valor de IdTurnoAsociado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoAsociado(String as_s)
	{
		is_idTurnoAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno asociado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoAsociado()
	{
		return is_idTurnoAsociado;
	}

	/**
	 * Modifica el valor de MatriculasRelacionadas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMatriculasRelacionadas(String as_s)
	{
		is_matriculasRelacionadas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas relacionadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMatriculasRelacionadas()
	{
		return is_matriculasRelacionadas;
	}

	/**
	 * Modifica el valor de NirAfectado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirAfectado(String as_s)
	{
		is_nirAfectado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir afectado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirAfectado()
	{
		return is_nirAfectado;
	}

	/**
	 * Modifica el valor de NirVinculado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirVinculado(String as_s)
	{
		is_nirVinculado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir vinculado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirVinculado()
	{
		return is_nirVinculado;
	}

	/**
	 * Modifica el valor de TurnosAsociadosSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurnosAsociadosSolicitud(String as_s)
	{
		is_turnosAsociadosSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turnos asociados solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTurnosAsociadosSolicitud()
	{
		return is_turnosAsociadosSolicitud;
	}
}
