package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_VEREDA.
 *
 * @author Julian Vaca
 */
public class Vereda extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8742306457810810326L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is cabecera municipal. */
	private String is_cabeceraMunicipal;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id vereda. */
	private String is_idVereda;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/** Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/** Propiedad is nombre pais. */
	private String is_nombrePais;

	/**
	 * Constructor por defecto.
	 */
	public Vereda()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setActivo(String as_S)
	{
		this.is_activo = as_S;
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
	 * Modifica el valor de CabeceraMunicipal.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setCabeceraMunicipal(String as_S)
	{
		this.is_cabeceraMunicipal = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor cabecera municipal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCabeceraMunicipal()
	{
		return is_cabeceraMunicipal;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String as_S)
	{
		this.is_idDepartamento = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String as_S)
	{
		this.is_idMunicipio = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setIdPais(String as_S)
	{
		this.is_idPais = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdVereda.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setIdVereda(String as_S)
	{
		this.is_idVereda = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor id vereda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdVereda()
	{
		return is_idVereda;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setNombre(String as_S)
	{
		this.is_nombre = as_S;
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
	 * Modifica el valor de NombreDepartamento.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setNombreDepartamento(String as_S)
	{
		is_nombreDepartamento = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDepartamento()
	{
		return is_nombreDepartamento;
	}

	/**
	 * Modifica el valor de NombreMunicipio.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setNombreMunicipio(String as_S)
	{
		is_nombreMunicipio = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor nombre municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Modifica el valor de NombrePais.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setNombrePais(String as_S)
	{
		is_nombrePais = as_S;
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
