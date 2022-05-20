package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_DESBORDE.
 *
 * @author mblanco
 */
public class Desborde extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -1898410726940180626L;
	
	/** Propiedad ibd orden. */
	private BigDecimal        ibd_orden;
	
	/** Propiedad is habilitada. */
	private String            is_habilitada;
	
	/** Propiedad is id circulo desborde. */
	private String            is_idCirculoDesborde;
	
	/** Propiedad is id circulo origen. */
	private String            is_idCirculoOrigen;
	
	/** Propiedad is observaciones. */
	private String            is_observaciones;

	/**
	 * Modifica el valor de Habilitada.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabilitada(String as_s)
	{
		is_habilitada                              = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habilitada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabilitada()
	{
		return is_habilitada;
	}

	/**
	 * Modifica el valor de IdCirculoDesborde.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoDesborde(String as_s)
	{
		is_idCirculoDesborde = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo desborde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoDesborde()
	{
		return is_idCirculoDesborde;
	}

	/**
	 * Modifica el valor de IdCirculoOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoOrigen(String as_s)
	{
		is_idCirculoOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoOrigen()
	{
		return is_idCirculoOrigen;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal abd_bd)
	{
		ibd_orden = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
	}
}
