package com.bachue.snr.prosnr01.model.sdb.pgn;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_PAIS.
 *
 * @author Julian Vaca
 */
public class Pais extends Departamento implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID            = -2244912676178591671L;
	
	/** Propiedad is codigo pais. */
	private String            is_codigoPais;
	
	/** Propiedad is indicativo telefonico pais. */
	private String            is_indicativoTelefonicoPais;
	
	/** Propiedad is nacionalidad. */
	private String            is_nacionalidad;
	
	/** Propiedad is nombre pais. */
	private String            is_nombrePais;

	/**
	 * Constructor por defecto.
	 */
	public Pais()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public Pais(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de CodigoPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoPais(String as_s)
	{
		is_codigoPais                                     = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoPais()
	{
		return is_codigoPais;
	}

	/**
	 * Modifica el valor de IndicativoTelefonicoPais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicativoTelefonicoPais(String as_s)
	{
		is_indicativoTelefonicoPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicativo telefonico pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicativoTelefonicoPais()
	{
		return is_indicativoTelefonicoPais;
	}

	/**
	 * Modifica el valor de Nacionalidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNacionalidad(String as_s)
	{
		is_nacionalidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nacionalidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNacionalidad()
	{
		return is_nacionalidad;
	}

	/**
	 * Modifica el valor de NombrePais.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePais(String as_s)
	{
		is_nombrePais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePais()
	{
		return is_nombrePais;
	}
}
