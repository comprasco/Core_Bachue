package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de punto recaudo tipo recaudo.
 *
 * @author Julian Vaca
 */
public class PuntoRecaudoTipoRecaudo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID             = 7516635174167099561L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id punto recaudo. */
	private String            is_idPuntoRecaudo;
	
	/** Propiedad is id punto recaudo tipo recaudo. */
	private String            is_idPuntoRecaudoTipoRecaudo;
	
	/** Propiedad is id tipo recaudo. */
	private String            is_idTipoRecaudo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                     = as_s;
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
	 * Modifica el valor de IdPuntoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdPuntoRecaudo(String as_s)
	{
		this.is_idPuntoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id punto recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPuntoRecaudo()
	{
		return is_idPuntoRecaudo;
	}

	/**
	 * Modifica el valor de IdPuntoRecaudoTipoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdPuntoRecaudoTipoRecaudo(String as_s)
	{
		this.is_idPuntoRecaudoTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id punto recaudo tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPuntoRecaudoTipoRecaudo()
	{
		return is_idPuntoRecaudoTipoRecaudo;
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
}
