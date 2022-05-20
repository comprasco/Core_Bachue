package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades Recurso.
 *
 * @author  Andrés Rocha
 * Fecha de Creacion: 19/06/2020
 */
public class Recurso extends Auditoria implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4191383900283690028L;

	/** Propiedad if fecha recurso. */
	private Date if_fechaRecurso;

	/** Propiedad ii version documento. */
	private Long il_versionDocumento;

	/** Propiedad is digitalizado. */
	private String is_digitalizado;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is recurso. */
	private String is_idRecurso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud recurso. */
	private String is_idSolicitudRecurso;

	/** Propiedad is turno. */
	private String is_idTurno;

	/**
	 * Modifica el valor de Digitalizado.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el valor de digitalizado.
	 */
	public void setDigitalizado(String as_s)
	{
		is_digitalizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor digitalizado.
	 *
	 * @return Retorna el valor de la propiedad digitalizado
	 */
	public String getDigitalizado()
	{
		return is_digitalizado;
	}

	/**
	 * Modifica el valor de FechaRecurso.
	 *
	 * @param ad_d Argumento de tipo <code>Date</code> que contiene el valor de la fecha del recurso.
	 */
	public void setFechaRecurso(Date ad_d)
	{
		if_fechaRecurso = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha recurso.
	 *
	 * @return Retorna el valor de la propiedad fechaRecurso
	 */
	public Date getFechaRecurso()
	{
		return if_fechaRecurso;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el id del documento.
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return Retorna el valor de la propiedad idDocumento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdRecurso.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el id del recurso.
	 */
	public void setIdRecurso(String as_s)
	{
		is_idRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id recurso.
	 *
	 * @return Retorna el valor de la propiedad idRecurso
	 */
	public String getIdRecurso()
	{
		return is_idRecurso;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el id de la solicitud.
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
	 * Modifica el valor de IdSolicitudRecurso.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el id de la solictud del recurso.
	 */
	public void setIdSolicitudRecurso(String as_s)
	{
		is_idSolicitudRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud recurso.
	 *
	 * @return el valor de id solicitud recurso
	 */
	public String getIdSolicitudRecurso()
	{
		return is_idSolicitudRecurso;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s Argumento de tipo <code>String</code> que contiene el id del turno.
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
	 * Modifica el valor de VersionDocumento.
	 *
	 * @param ai_i Argumento de tipo <code>Long</code> que contiene la versión del documento.
	 */
	public void setVersionDocumento(Long al_l)
	{
		il_versionDocumento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version documento.
	 *
	 * @return Retorna el valor de la propiedad versionDocumento
	 */
	public Long getVersionDocumento()
	{
		return il_versionDocumento;
	}
}
