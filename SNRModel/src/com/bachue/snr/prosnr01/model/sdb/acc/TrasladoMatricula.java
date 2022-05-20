package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TRASLADO_MATRICULA.
 *
 * @author  Gabriel Francisco Arias
 * Fecha de Creacion: 23/06/2020
 */
public class TrasladoMatricula extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5100816768175621371L;

	/** Propiedad il id matricula destino tmp. */
	private Long il_idMatriculaDestinoTmp;

	/** Propiedad il id matricula origen. */
	private Long il_idMatriculaOrigen;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is direccion destino. */
	private String is_direccionDestino;

	/** Propiedad is direccion origen. */
	private String is_direccionOrigen;

	/** Propiedad is id circulo destino. */
	private String is_idCirculoDestino;

	/** Propiedad is id circulo origen. */
	private String is_idCirculoOrigen;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id traslado matricula. */
	private String is_idTrasladoMatricula;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad il anotaciones destino. */
	private long il_anotacionesDestino;

	/** Propiedad anotaciones origen. */
	private long il_anotacionesOrigen;

	/**
	 * Modifica el valor de AnotacionesDestino.
	 *
	 * @param al_anotacionesDestino de al anotaciones destino
	 */
	public void setAnotacionesDestino(long al_anotacionesDestino)
	{
		il_anotacionesDestino = al_anotacionesDestino;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones destino.
	 *
	 * @return Retorna el valor de la propiedad anotacionesDestino
	 */
	public long getAnotacionesDestino()
	{
		return il_anotacionesDestino;
	}

	/**
	 * Modifica el valor de AnotacionesOrigen.
	 *
	 * @param al_anotacionesOrigen de al anotaciones origen
	 */
	public void setAnotacionesOrigen(long al_anotacionesOrigen)
	{
		il_anotacionesOrigen = al_anotacionesOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones origen.
	 *
	 * @return Retorna el valor de la propiedad anotacionesOrigen
	 */
	public long getAnotacionesOrigen()
	{
		return il_anotacionesOrigen;
	}

	/**
	 * Modifica el valor de DireccionDestino.
	 *
	 * @param as_direccionDestino de as direccion destino
	 */
	public void setDireccionDestino(String as_direccionDestino)
	{
		is_direccionDestino = as_direccionDestino;
	}

	/**
	 * Retorna Objeto o variable de valor direccion destino.
	 *
	 * @return Retorna el valor de la propiedad direccionDestino
	 */
	public String getDireccionDestino()
	{
		return is_direccionDestino;
	}

	/**
	 * Modifica el valor de DireccionOrigen.
	 *
	 * @param as_direccionOrigen de as direccion origen
	 */
	public void setDireccionOrigen(String as_direccionOrigen)
	{
		is_direccionOrigen = as_direccionOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor direccion origen.
	 *
	 * @return Retorna el valor de la propiedad direccionOrigen
	 */
	public String getDireccionOrigen()
	{
		return is_direccionOrigen;
	}

	/**
	 * Modifica el valor de IdCirculoDestino.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoDestino(String as_s)
	{
		is_idCirculoDestino = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo destino.
	 *
	 * @return Retorna el valor de la propiedad idCirculoDestino
	 */
	public String getIdCirculoDestino()
	{
		return is_idCirculoDestino;
	}

	/**
	 * Modifica el valor de IdCirculoOrigen.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoOrigen(String as_s)
	{
		is_idCirculoOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo origen.
	 *
	 * @return Retorna el valor de la propiedad idCirculoOrigen
	 */
	public String getIdCirculoOrigen()
	{
		return is_idCirculoOrigen;
	}

	/**
	 * Modifica el valor de IdMatriculaDestinoTmp.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatriculaDestinoTmp(Long al_l)
	{
		il_idMatriculaDestinoTmp = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula destino tmp.
	 *
	 * @return Retorna el valor de la propiedad idMatriculaDestinoTmp
	 */
	public Long getIdMatriculaDestinoTmp()
	{
		return il_idMatriculaDestinoTmp;
	}

	/**
	 * Modifica el valor de IdMatriculaOrigen.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatriculaOrigen(Long al_l)
	{
		il_idMatriculaOrigen = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula origen.
	 *
	 * @return Retorna el valor de la propiedad idMatriculaOrigen
	 */
	public Long getIdMatriculaOrigen()
	{
		return il_idMatriculaOrigen;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTrasladoMatricula.
	 *
	 * @param as_s de as s
	 */
	public void setIdTrasladoMatricula(String as_s)
	{
		is_idTrasladoMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id traslado matricula.
	 *
	 * @return Retorna el valor de la propiedad idTrasladoMatricula
	 */
	public String getIdTrasladoMatricula()
	{
		return is_idTrasladoMatricula;
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
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
