package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades AlertaTComunidad.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 2/04/2020
 */
public class AlertaTComunidad extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2620290313275596322L;

	/** Propiedad il id alerta tierra. */
	private long il_idAlertaTierra;

	/** Propiedad il id comunidad etnica. */
	private long il_idComunidadEtnica;

	/**
	 * Instancia un nuevo objeto alerta T comunidad.
	 */
	public AlertaTComunidad()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tierra.
	 *
	 * @return el valor de id alerta tierra
	 */
	public long getIdAlertaTierra()
	{
		return il_idAlertaTierra;
	}

	/**
	 * Modifica el valor de IdAlertaTierra.
	 *
	 * @param al_l de al l
	 */
	public void setIdAlertaTierra(long al_l)
	{
		il_idAlertaTierra = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id comunidad etnica.
	 *
	 * @return el valor de id comunidad etnica
	 */
	public long getIdComunidadEtnica()
	{
		return il_idComunidadEtnica;
	}

	/**
	 * Modifica el valor de IdComunidadEtnica.
	 *
	 * @param al_l de al l
	 */
	public void setIdComunidadEtnica(long al_l)
	{
		il_idComunidadEtnica = al_l;
	}
}
