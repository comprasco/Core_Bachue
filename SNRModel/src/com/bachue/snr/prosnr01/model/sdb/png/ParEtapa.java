package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de ParEtapa de la tabla SDB_PGN_ETAPA.
 *
 * @author Julian Vaca
 */
public class ParEtapa extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = -2292016374845411114L;
	
	/** Propiedad ibd dias habiles normal. */
	private BigDecimal        ibd_diasHabilesNormal;
	
	/** Propiedad is area etapa mv. */
	private String            is_areaEtapaMv;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad is posibles etapas futuras. */
	private String            is_posiblesEtapasFuturas;
	
	/** Propiedad is posibles etapas gestion. */
	private String            is_posiblesEtapasGestion;
	
	/** Propiedad il etapa. */
	private long              il_etapa;

	/**
	 * Constructor por defecto.
	 */
	public ParEtapa()
	{
	}

	/**
	 * Modifica el valor de AreaEtapaMv.
	 *
	 * @param areaEtapaMv asigna el valor a la propiedad
	 */
	public void setAreaEtapaMv(String areaEtapaMv)
	{
		this.is_areaEtapaMv                            = areaEtapaMv;
	}

	/**
	 * Retorna Objeto o variable de valor area etapa mv.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAreaEtapaMv()
	{
		return is_areaEtapaMv;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de DiasHabilesNormal.
	 *
	 * @param diasHabilesNormal asigna el valor a la propiedad
	 */
	public void setDiasHabilesNormal(BigDecimal diasHabilesNormal)
	{
		this.ibd_diasHabilesNormal = diasHabilesNormal;
	}

	/**
	 * Retorna Objeto o variable de valor dias habiles normal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getDiasHabilesNormal()
	{
		return ibd_diasHabilesNormal;
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
	 * Modifica el valor de Etapa.
	 *
	 * @param etapa asigna el valor a la propiedad
	 */
	public void setEtapa(long etapa)
	{
		this.il_etapa = etapa;
	}

	/**
	 * Retorna Objeto o variable de valor etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getEtapa()
	{
		return il_etapa;
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
	 * Modifica el valor de PosiblesEtapasFuturas.
	 *
	 * @param posiblesEtapasFuturas asigna el valor a la propiedad
	 */
	public void setPosiblesEtapasFuturas(String posiblesEtapasFuturas)
	{
		this.is_posiblesEtapasFuturas = posiblesEtapasFuturas;
	}

	/**
	 * Retorna Objeto o variable de valor posibles etapas futuras.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPosiblesEtapasFuturas()
	{
		return is_posiblesEtapasFuturas;
	}

	/**
	 * Modifica el valor de PosiblesEtapasGestion.
	 *
	 * @param posiblesEtapasGestion asigna el valor a la propiedad
	 */
	public void setPosiblesEtapasGestion(String posiblesEtapasGestion)
	{
		this.is_posiblesEtapasGestion = posiblesEtapasGestion;
	}

	/**
	 * Retorna Objeto o variable de valor posibles etapas gestion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPosiblesEtapasGestion()
	{
		return is_posiblesEtapasGestion;
	}
}
