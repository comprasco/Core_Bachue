package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_RANGO_CUANTIA.
 *
 * @author Carlos Calderon
 */
public class RangoCuantia extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 6965293208283726565L;
	
	/** Propiedad id fecha desde. */
	private Date              id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date              id_fechaHasta;
	
	/** Propiedad id rango inferior. */
	private Double            id_rangoInferior;
	
	/** Propiedad id rango superior. */
	private Double            id_rangoSuperior;
	
	/** Propiedad ii porcentaje. */
	private Integer           ii_porcentaje;
	
	/** Propiedad ii valor. */
	private Integer           ii_valor;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id rango. */
	private String            is_idRango;
	
	/** Propiedad is operador inferior. */
	private String            is_operadorInferior;
	
	/** Propiedad is operador superior. */
	private String            is_operadorSuperior;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                            = as_s;
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
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		this.id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		this.id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdRango.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRango(String as_s)
	{
		this.is_idRango = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id rango.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRango()
	{
		return is_idRango;
	}

	/**
	 * Modifica el valor de OperadorInferior.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOperadorInferior(String as_s)
	{
		this.is_operadorInferior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor operador inferior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOperadorInferior()
	{
		return is_operadorInferior;
	}

	/**
	 * Modifica el valor de OperadorSuperior.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOperadorSuperior(String as_s)
	{
		this.is_operadorSuperior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor operador superior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOperadorSuperior()
	{
		return is_operadorSuperior;
	}

	/**
	 * Modifica el valor de Porcentaje.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setPorcentaje(Integer ai_i)
	{
		this.ii_porcentaje = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getPorcentaje()
	{
		return ii_porcentaje;
	}

	/**
	 * Modifica el valor de RangoInferior.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setRangoInferior(Double ad_d)
	{
		this.id_rangoInferior = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor rango inferior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getRangoInferior()
	{
		return id_rangoInferior;
	}

	/**
	 * Modifica el valor de RangoSuperior.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setRangoSuperior(Double ad_d)
	{
		this.id_rangoSuperior = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor rango superior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getRangoSuperior()
	{
		return id_rangoSuperior;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setValor(Integer ai_i)
	{
		this.ii_valor = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getValor()
	{
		return ii_valor;
	}
}
