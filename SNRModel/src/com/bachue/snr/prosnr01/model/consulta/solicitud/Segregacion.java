package com.bachue.snr.prosnr01.model.consulta.solicitud;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades Segregacion.
 *
 * @author Julian Vaca
 */
public class Segregacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1874169435864073131L;

	/** Propiedad is area construccion. */
	private String is_areaConstruccion;

	/** Propiedad is area privada. */
	private String is_areaPrivada;

	/** Propiedad is area terreno. */
	private String is_areaTerreno;

	/** Propiedad is coeficiente. */
	private String is_coeficiente;

	/** Propiedad is direccion predio. */
	private String is_direccionPredio;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/**
	 * Modifica el valor de area construccion.
	 *
	 * @param as_areaConstruccion asigna el valor a la propiedad area construccion
	 */
	public void setAreaConstruccion(String as_areaConstruccion)
	{
		this.is_areaConstruccion = as_areaConstruccion;
	}

	/**
	 * Retorna el valor de area construccion.
	 *
	 * @return el valor de area construccion
	 */
	public String getAreaConstruccion()
	{
		return is_areaConstruccion;
	}

	/**
	 * Modifica el valor de area privada.
	 *
	 * @param as_areaPrivada asigna el valor a la propiedad area privada
	 */
	public void setAreaPrivada(String as_areaPrivada)
	{
		this.is_areaPrivada = as_areaPrivada;
	}

	/**
	 * Retorna el valor de area privada.
	 *
	 * @return el valor de area privada
	 */
	public String getAreaPrivada()
	{
		return is_areaPrivada;
	}

	/**
	 * Modifica el valor de area terreno.
	 *
	 * @param as_areaTerreno asigna el valor a la propiedad area terreno
	 */
	public void setAreaTerreno(String as_areaTerreno)
	{
		this.is_areaTerreno = as_areaTerreno;
	}

	/**
	 * Retorna el valor de area terreno.
	 *
	 * @return el valor de area terreno
	 */
	public String getAreaTerreno()
	{
		return is_areaTerreno;
	}

	/**
	 * Modifica el valor de coeficiente.
	 *
	 * @param as_coeficiente asigna el valor a la propiedad coeficiente
	 */
	public void setCoeficiente(String as_coeficiente)
	{
		this.is_coeficiente = as_coeficiente;
	}

	/**
	 * Retorna el valor de coeficiente.
	 *
	 * @return el valor de coeficiente
	 */
	public String getCoeficiente()
	{
		return is_coeficiente;
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param as_direccionPredio asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(String as_direccionPredio)
	{
		this.is_direccionPredio = as_direccionPredio;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public String getDireccionPredio()
	{
		return is_direccionPredio;
	}

	/**
	 * Modifica el valor de nombre predio.
	 *
	 * @param as_nombrePredio asigna el valor a la propiedad nombre predio
	 */
	public void setNombrePredio(String as_nombrePredio)
	{
		this.is_nombrePredio = as_nombrePredio;
	}

	/**
	 * Retorna el valor de nombre predio.
	 *
	 * @return el valor de nombre predio
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}
}
