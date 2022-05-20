package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de TipoEje.
 *
 * @author nguaneme
 */
public class TipoEje extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4757069696056166395L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is complemento string. */
	private String            is_complementoString;
	
	/** Propiedad is id tipo eje. */
	private String            is_idTipoEje;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad is tipo predio. */
	private String            is_tipoPredio;
	
	/** Propiedad ib complemento. */
	private boolean           ib_complemento;

	/**
	 * Constructor por defecto.
	 */
	public TipoEje()
	{
	}

	/**
	 * Modifica el valor de Complemento.
	 *
	 * @param ab_b de ab b
	 */
	public void setComplemento(boolean ab_b)
	{
		ib_complemento                         = ab_b;
	}

	/**
	 * Valida la propiedad complemento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isComplemento()
	{
		return ib_complemento;
	}

	/**
	 * Modifica el valor de IdTipoEje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoEje(String as_s)
	{
		this.is_idTipoEje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo eje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEje()
	{
		return is_idTipoEje;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
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
	 * Modifica el valor de TipoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoPredio(String as_s)
	{
		is_tipoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
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
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Modifica el valor de ComplementoString.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComplementoString(String as_s)
	{
		is_complementoString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor complemento string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComplementoString()
	{
		return is_complementoString;
	}
}
