package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA_PRESENTADA.
 *
 * @author hcastaneda
 *
 */
public class PersonaPresentada extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -369455441819681515L;

	/** Propiedad is_idCalidadPersonaRepresentada. */
	private String is_idCalidadPersonaRepresentada;

	/** Propiedad is_idCalidadSolicitante. */
	private String is_idCalidadSolicitante;

	/** Propiedad is_idPersonaApoderado. */
	private String is_idPersonaApoderado;

	/** Propiedad is_idPersonaRepresentada. */
	private String is_idPersonaRepresentada;

	/** Propiedad is_idPersonaRepresentadaApoderado. */
	private String is_idPersonaRepresentadaApoderado;

	/** Propiedad is_idSolicitud. */
	private String is_idSolicitud;

	/** Propiedad is_idTurno. */
	private String is_idTurno;

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdCalidadPersonaRepresentada(String as_s)
	{
		is_idCalidadPersonaRepresentada = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdCalidadPersonaRepresentada()
	{
		return is_idCalidadPersonaRepresentada;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdCalidadSolicitante(String as_s)
	{
		is_idCalidadSolicitante = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdCalidadSolicitante()
	{
		return is_idCalidadSolicitante;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdPersonaApoderado(String as_s)
	{
		is_idPersonaApoderado = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdPersonaApoderado()
	{
		return is_idPersonaApoderado;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdPersonaRepresentada(String as_s)
	{
		is_idPersonaRepresentada = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdPersonaRepresentada()
	{
		return is_idPersonaRepresentada;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdPersonaRepresentadaApoderado(String as_s)
	{
		is_idPersonaRepresentadaApoderado = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdPersonaRepresentadaApoderado()
	{
		return is_idPersonaRepresentadaApoderado;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}
}
