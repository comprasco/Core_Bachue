package com.bachue.snr.prosnr01.model.antiguoSistema;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades ActualizarDatosAntSistema.
 *
 * @author Julian Vaca
 */
public class ActualizarDatosAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4993870554456017954L;

	/** Propiedad is codigo. */
	private String is_codigo;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Modifica el valor de codigo.
	 *
	 * @param as_s asigna el valor a la propiedad codigo
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna el valor de codigo.
	 *
	 * @return el valor de codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de nombre.
	 *
	 * @param as_s asigna el valor a la propiedad nombre
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna el valor de nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
