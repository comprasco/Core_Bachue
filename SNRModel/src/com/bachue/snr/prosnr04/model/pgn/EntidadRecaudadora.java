package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de entidad recaudadora.
 *
 * @author Julian Vaca
 */
public class EntidadRecaudadora extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID            = 5524081838322190975L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is codigo entidad recaudadora. */
	private String            is_codigoEntidadRecaudadora;
	
	/** Propiedad is id entidad recaudadora. */
	private String            is_idEntidadRecaudadora;
	
	/** Propiedad is nombre entidad recaudadora. */
	private String            is_nombreEntidadRecaudadora;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                    = as_s;
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
	 * Modifica el valor de CodigoEntidadRecaudadora.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoEntidadRecaudadora(String as_s)
	{
		this.is_codigoEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo entidad recaudadora.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoEntidadRecaudadora()
	{
		return is_codigoEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		this.is_idEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudadora.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de NombreEntidadRecaudadora.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		this.is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre entidad recaudadora.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}
}
