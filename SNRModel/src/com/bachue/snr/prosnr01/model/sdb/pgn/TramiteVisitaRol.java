package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TRAMITE_VISITA_ROL.
 *
 * @author Bryan Márquez
 */
public class TramiteVisitaRol extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -671771327147107720L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id rol. */
	private String is_idRol;

	/** Propiedad is id tramite visita. */
	private String is_idTramiteVisita;

	/** Propiedad is id tramite visital rol. */
	private String is_idTramiteVisitaRol;

	/** Propiedad is id tramite visita tipo. */
	private String is_idTramiteVisitaTipo;

	/**
	 * @param is_activo Modifica el valor de la propiedad is_activo
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param is_idRol Modifica el valor de la propiedad is_idRol
	 */
	public void setIdRol(String as_ir)
	{
		is_idRol = as_ir;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idRol
	 */
	public String getIdRol()
	{
		return is_idRol;
	}

	/**
	 * @param is_idTramiteVisita Modifica el valor de la propiedad is_idTramiteVisita
	 */
	public void setIdTramiteVisita(String as_itv)
	{
		is_idTramiteVisita = as_itv;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTramiteVisita
	 */
	public String getIdTramiteVisita()
	{
		return is_idTramiteVisita;
	}

	/**
	 * @param is_idTramiteVisitalRol Modifica el valor de la propiedad is_idTramiteVisitalRol
	 */
	public void setIdTramiteVisitaRol(String as_itvr)
	{
		is_idTramiteVisitaRol = as_itvr;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTramiteVisitalRol
	 */
	public String getIdTramiteVisitaRol()
	{
		return is_idTramiteVisitaRol;
	}

	/**
	 * @param is_idTramiteVisitaTipo Modifica el valor de la propiedad is_idTramiteVisitaTipo
	 */
	public void setIdTramiteVisitaTipo(String as_itvt)
	{
		is_idTramiteVisitaTipo = as_itvt;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTramiteVisitaTipo
	 */
	public String getIdTramiteVisitaTipo()
	{
		return is_idTramiteVisitaTipo;
	}
}
