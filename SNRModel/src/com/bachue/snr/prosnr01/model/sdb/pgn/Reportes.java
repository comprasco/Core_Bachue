package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_REPORTES.
 *
 * @author nguaneme
 */
public class Reportes extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID  = 7330422831245229085L;
	
	/** Propiedad ibd id reporte. */
	private BigDecimal        ibd_idReporte;
	
	/** Propiedad il orden ejecucion. */
	private Long              il_ordenEjecucion;
	
	/** Propiedad is codigo. */
	private String            is_codigo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is orden consulta. */
	private String            is_ordenConsulta;

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigo(String as_s)
	{
		this.is_codigo                          = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
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
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
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
	 * Modifica el valor de IdReporte.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdReporte(BigDecimal abd_bd)
	{
		this.ibd_idReporte = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id reporte.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdReporte()
	{
		return ibd_idReporte;
	}

	/**
	 * Modifica el valor de OrdenConsulta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOrdenConsulta(String as_s)
	{
		this.is_ordenConsulta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor orden consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOrdenConsulta()
	{
		return is_ordenConsulta;
	}

	/**
	 * Modifica el valor de OrdenEjecucion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setOrdenEjecucion(Long al_l)
	{
		this.il_ordenEjecucion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor orden ejecucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getOrdenEjecucion()
	{
		return il_ordenEjecucion;
	}
}
