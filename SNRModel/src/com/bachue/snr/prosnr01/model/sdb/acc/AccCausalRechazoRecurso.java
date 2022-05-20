package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene los atributos de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO.
 *
 * @author hcastaneda
 */
public class AccCausalRechazoRecurso extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2578456435012045412L;

	/**  Propiedad il_idTurnoHistoria. */
	private Long il_idTurnoHistoria;

	/**  Propiedad is_idCausal. */
	private String is_idCausal;

	/**  Propiedad is_idCausalRechazoRecurso. */
	private String is_idCausalRechazoRecurso;

	/**  Propiedad is_idSolicitud. */
	private String is_idSolicitud;

	/**  Propiedad is_idTurno. */
	private String is_idTurno;

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdCausal(String as_s)
	{
		is_idCausal                            = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal.
	 *
	 * @return el valor de id causal
	 */
	public String getIdCausal()
	{
		return is_idCausal;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdCausalRechazoRecurso(String as_s)
	{
		is_idCausalRechazoRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal rechazo recurso.
	 *
	 * @return el valor de id causal rechazo recurso
	 */
	public String getIdCausalRechazoRecurso()
	{
		return is_idCausalRechazoRecurso;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}
}
