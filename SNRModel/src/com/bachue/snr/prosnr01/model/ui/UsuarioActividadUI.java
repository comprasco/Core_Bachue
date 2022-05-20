package com.bachue.snr.prosnr01.model.ui;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de UsuarioActividadUI.
 *
 * @author garias
 */
public class UsuarioActividadUI extends TurnoHistoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1803094864442755905L;

	/** Propiedad il id etapa actual. */
	private Long il_idEtapaActual;

	/** Propiedad nir. */
	private String nir;

	/** Propiedad proceso. */
	private String proceso;

	/** Propiedad mostrar. */
	private boolean mostrar;

	/** Propiedad il id etapa ant. */
	private long il_idEtapaAnt;

	/** Propiedad il id etapa menu. */
	private long il_idEtapaMenu;

	/**
	 * Modifica el valor de Mostrar.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrar(boolean ab_b)
	{
		this.mostrar = ab_b;
	}

	/**
	 * Valida la propiedad mostrar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrar()
	{
		return mostrar;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		this.nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return nir;
	}

	/**
	 * Modifica el valor de Proceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setProceso(String as_s)
	{
		this.proceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getProceso()
	{
		return proceso;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idEtapaAnt
	 */
	public long getIdEtapaAnt()
	{
		return il_idEtapaAnt;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad il_idEtapaAnt
	 */
	public void setIdEtapaAnt(long al_l)
	{
		il_idEtapaAnt = al_l;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idEtapaMenu
	 */
	public long getIdEtapaMenu()
	{
		return il_idEtapaMenu;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad il_idEtapaMenu
	 */
	public void setIdEtapaMenu(long al_l)
	{
		il_idEtapaMenu = al_l;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idEtapaActual
	 */
	public Long getIdEtapaActual()
	{
		return il_idEtapaActual;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad il_idEtapaActual
	 */
	public void setIdEtapaActual(Long al_l)
	{
		il_idEtapaActual = al_l;
	}
}
