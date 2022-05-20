package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de tipo de recaudo.
 *
 * @author Julian Vaca
 */
public class TipoRecaudo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = 4878873717420257955L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tipo recaudo. */
	private String            is_idTipoRecaudo;
	
	/** Propiedad is nombre tipo recaudo. */
	private String            is_nombreTipoRecaudo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                             = as_s;
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
	 * Modifica el valor de IdTipoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoRecaudo(String as_s)
	{
		this.is_idTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRecaudo()
	{
		return is_idTipoRecaudo;
	}

	/**
	 * Modifica el valor de NombreTipoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreTipoRecaudo(String as_s)
	{
		this.is_nombreTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoRecaudo()
	{
		return is_nombreTipoRecaudo;
	}
}
