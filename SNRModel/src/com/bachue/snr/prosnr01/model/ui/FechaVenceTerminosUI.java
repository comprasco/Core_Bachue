package com.bachue.snr.prosnr01.model.ui;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades FechaVenceTerminosUI.
 *
 * @author Luis Chacón
 */
public class FechaVenceTerminosUI implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -2353306002142469365L;
	
	/** Propiedad id fecha final. */
	private Date              id_fechaFinal;
	
	/** Propiedad id fecha inicial. */
	private Date              id_fechaInicial;
	
	/** Propiedad ii dias calcular fecha. */
	private Integer           ii_diasCalcularFecha;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is tipo calendario. */
	private String            is_tipoCalendario;

	/**
	 * Modifica el valor de DiasCalcularFecha.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setDiasCalcularFecha(Integer ai_i)
	{
		ii_diasCalcularFecha                       = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor dias calcular fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getDiasCalcularFecha()
	{
		return ii_diasCalcularFecha;
	}

	/**
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaFinal(Date ad_d)
	{
		id_fechaFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return Retorna el valor de la propiedad fechaFinal
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Modifica el valor de FechaInicial.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInicial(Date ad_d)
	{
		id_fechaInicial = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de TipoCalendario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoCalendario(String as_s)
	{
		is_tipoCalendario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo calendario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoCalendario()
	{
		return is_tipoCalendario;
	}
}
