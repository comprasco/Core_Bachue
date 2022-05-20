package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla  SDB_PGN_FESTIVO_NACIONAL.
 *
 * @author nguaneme
 */
public class FestivoNacional extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -8586447445426590932L;
	
	/** Propiedad id fecha. */
	private Date              id_fecha;
	
	/** Propiedad is id festivo nacional. */
	private String            is_idFestivoNacional;

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param fecha asigna el valor a la propiedad
	 */
	public void setFecha(Date fecha)
	{
		this.id_fecha                              = fecha;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de IdFestivoNacional.
	 *
	 * @param idFestivoNacional asigna el valor a la propiedad
	 */
	public void setIdFestivoNacional(String idFestivoNacional)
	{
		this.is_idFestivoNacional = idFestivoNacional;
	}

	/**
	 * Retorna Objeto o variable de valor id festivo nacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdFestivoNacional()
	{
		return is_idFestivoNacional;
	}
}
