package com.bachue.snr.prosnr01.model.aprobacion;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades FirmaAprobacion.
 *
 * @author Julian Vaca
 */
public class FirmaAprobacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8792268528199690728L;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is tipo. */
	private String is_tipo;

	/**
	 * Instancia un nuevo objeto firma aprobacion.
	 *
	 * @param as_nommbre de as nommbre
	 * @param as_tipo de as tipo
	 */
	public FirmaAprobacion(String as_nommbre, String as_tipo)
	{
		is_nombre     = as_nommbre;
		is_tipo       = as_tipo;
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

	/**
	 * Modifica el valor de tipo.
	 *
	 * @param as_s asigna el valor a la propiedad tipo
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna el valor de tipo.
	 *
	 * @return el valor de tipo
	 */
	public String getTipo()
	{
		return is_tipo;
	}
}
