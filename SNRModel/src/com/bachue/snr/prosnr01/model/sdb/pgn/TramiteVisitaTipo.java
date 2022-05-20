package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TRAMITE_VISITA_TIPO.
 *
 * @author Bryan Márquez
 */
public class TramiteVisitaTipo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8127620369572978440L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id tramite visita. */
	private String is_idTramiteVisita;

	/** Propiedad is id tramite visita tipo. */
	private String is_idTramiteVisitaTipo;

	/** Propiedad is nombre. */
	private String is_nombre;

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

	/**
	 * @param is_nombre Modifica el valor de la propiedad is_nombre
	 */
	public void setNombre(String as_n)
	{
		is_nombre = as_n;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
