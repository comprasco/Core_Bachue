package com.bachue.snr.prosnr01.web.util;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades para manejar PropiedadesUI.
 *
 * @author cvargas
 */
public class PropiedadesUI implements Serializable
{
	private static final long serialVersionUID = 158436892730597534L;

	/** Propiedad is nombre propiedad. */
	private String is_nombrePropiedad;

	/** Propiedad ib seleccione. */
	private boolean ib_seleccione;

	/**
	* Modifica el valor de nombre propiedad.
	*
	* @param as_s asigna el valor a la propiedad nombre propiedad
	*/
	public void setNombrePropiedad(String as_s)
	{
		is_nombrePropiedad                     = as_s;
	}

	/**
	* Retorna el valor de nombre propiedad.
	*
	* @return el valor de nombre propiedad
	*/
	public String getNombrePropiedad()
	{
		return is_nombrePropiedad;
	}

	/**
	* Modifica el valor de seleccione.
	*
	* @param ab_b asigna el valor a la propiedad seleccione
	*/
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	* Retorna el valor de seleccione.
	*
	* @return el valor de seleccione
	*/
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}
}
