package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades Archivo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: sep 11, 2020
 */
public class Archivo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2100128057767072033L;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is url. */
	private String is_url;

	/** Propiedad ib regresar. */
	private boolean ib_regresar;

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_nombre de as nombre
	 */
	public void setNombre(String as_nombre)
	{
		is_nombre = as_nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de Regresar.
	 *
	 * @param ab_b de ab b
	 */
	public void setRegresar(boolean ab_b)
	{
		ib_regresar = ab_b;
	}

	public boolean isRegresar()
	{
		return ib_regresar;
	}

	/**
	 * Modifica el valor de Url.
	 *
	 * @param as_s de as s
	 */
	public void setUrl(String as_s)
	{
		is_url = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor url.
	 *
	 * @return el valor de url
	 */
	public String getUrl()
	{
		return is_url;
	}
}
