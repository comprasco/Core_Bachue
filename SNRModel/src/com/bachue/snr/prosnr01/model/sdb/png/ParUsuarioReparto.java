package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * 
 * Clase modelo que contiene todos los atributos de ParUsuarioReparto.java
 * @author Julian Vaca
 *
 */
public class ParUsuarioReparto extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1033061755063370524L;
	
	/** Propiedad is habilitar. */
	private String            is_habilitar;
	
	/** Propiedad is usuario. */
	private String            is_usuario;
	
	/** Propiedad il reparto. */
	private long              il_reparto;

	/**
	 * Instancia un nuevo objeto par usuario reparto.
	 */
	public ParUsuarioReparto()
	{
	}

	/**
	 * Modifica el valor de Habilitar.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitar(String as_s)
	{
		this.is_habilitar                      = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitar()
	{
		return is_habilitar;
	}

	/**
	 * Modifica el valor de Reparto.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setReparto(long al_l)
	{
		this.il_reparto = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getReparto()
	{
		return il_reparto;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		this.is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}
}
