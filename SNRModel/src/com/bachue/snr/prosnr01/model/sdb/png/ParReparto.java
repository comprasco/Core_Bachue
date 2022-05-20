package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de ParReparto.
 *
 * @author Julian Vaca
 */
public class ParReparto extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4609489702084068478L;
	
	/** Propiedad ibd cantidad dia. */
	private BigDecimal        ibd_cantidadDia;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is etapa inicial. */
	private String            is_etapaInicial;
	
	/** Propiedad is forma reparto. */
	private String            is_formaReparto;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad is tipo reparto. */
	private String            is_tipoReparto;
	
	/** Propiedad is reparto. */
	private long              is_reparto;

	/**
	 * Instancia un nuevo objeto par reparto.
	 */
	public ParReparto()
	{
	}

	/**
	 * Modifica el valor de CantidadDia.
	 *
	 * @param cantidadDia asigna el valor a la propiedad
	 */
	public void setCantidadDia(BigDecimal cantidadDia)
	{
		this.ibd_cantidadDia                   = cantidadDia;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad dia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getCantidadDia()
	{
		return ibd_cantidadDia;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param estado asigna el valor a la propiedad
	 */
	public void setEstado(String estado)
	{
		this.is_estado = estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de EtapaInicial.
	 *
	 * @param etapaInicial asigna el valor a la propiedad
	 */
	public void setEtapaInicial(String etapaInicial)
	{
		this.is_etapaInicial = etapaInicial;
	}

	/**
	 * Retorna Objeto o variable de valor etapa inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEtapaInicial()
	{
		return is_etapaInicial;
	}

	/**
	 * Modifica el valor de FormaReparto.
	 *
	 * @param formaReparto asigna el valor a la propiedad
	 */
	public void setFormaReparto(String formaReparto)
	{
		this.is_formaReparto = formaReparto;
	}

	/**
	 * Retorna Objeto o variable de valor forma reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFormaReparto()
	{
		return is_formaReparto;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de Reparto.
	 *
	 * @param reparto asigna el valor a la propiedad
	 */
	public void setReparto(long reparto)
	{
		this.is_reparto = reparto;
	}

	/**
	 * Retorna Objeto o variable de valor reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getReparto()
	{
		return is_reparto;
	}

	/**
	 * Modifica el valor de TipoReparto.
	 *
	 * @param tipoReparto asigna el valor a la propiedad
	 */
	public void setTipoReparto(String tipoReparto)
	{
		this.is_tipoReparto = tipoReparto;
	}

	/**
	 * Retorna Objeto o variable de valor tipo reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoReparto()
	{
		return is_tipoReparto;
	}
}
