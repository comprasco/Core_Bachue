package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los campos de la tabla SDB_PGN_ZONA_REGISTRAL.
 *
 * @author Julian Vaca
 */
public class ZonaRegistral extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 2635958717604763844L;

	/**  Propiedad is activo. */
	private String is_activo;

	/**  Propiedad is id circulo. */
	private String is_idCirculo;

	/**  Propiedad is id departamento. */
	private String is_idDepartamento;

	/**  Propiedad is id municipio. */
	private String is_idMunicipio;

	/**  Propiedad is id pais. */
	private String is_idPais;

	/**  Propiedad is id vereda. */
	private String is_idVereda;

	/**  Propiedad is id zona registral. */
	private String is_idZonaRegistral;

	/**  Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/**  Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/**  Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/**  Propiedad is nombre pais. */
	private String is_nombrePais;

	/**  Propiedad is nombre vereda. */
	private String is_nombreVereda;

	/**
	 * Constructor por defecto.
	 */
	public ZonaRegistral()
	{
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
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		is_activo = activo;
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
	 * Modifica el valor de IdCirculo.
	 *
	 * @param idCirculo asigna el valor a la propiedad
	 */
	public void setIdCirculo(String idCirculo)
	{
		is_idCirculo = idCirculo;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param nombreCirculo asigna el valor a la propiedad
	 */
	public void setNombreCirculo(String nombreCirculo)
	{
		is_nombreCirculo = nombreCirculo;
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
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param idDepartamento asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String idDepartamento)
	{
		is_idDepartamento = idDepartamento;
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
	 * Modifica el valor de NombreDepartamento.
	 *
	 * @param nombreDepartamento asigna el valor a la propiedad
	 */
	public void setNombreDepartamento(String nombreDepartamento)
	{
		is_nombreDepartamento = nombreDepartamento;
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
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param idMunicipio asigna el valor a la propiedad
	 */
	public void setIdMunicipio(String idMunicipio)
	{
		is_idMunicipio = idMunicipio;
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
	 * Modifica el valor de NombreMunicipio.
	 *
	 * @param nombreMunicipio asigna el valor a la propiedad
	 */
	public void setNombreMunicipio(String nombreMunicipio)
	{
		is_nombreMunicipio = nombreMunicipio;
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
	 * Modifica el valor de IdPais.
	 *
	 * @param idPais asigna el valor a la propiedad
	 */
	public void setIdPais(String idPais)
	{
		is_idPais = idPais;
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

	/**
	 * Modifica el valor de NombrePais.
	 *
	 * @param nombrePais asigna el valor a la propiedad
	 */
	public void setNombrePais(String nombrePais)
	{
		is_nombrePais = nombrePais;
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
	 * Modifica el valor de IdVereda.
	 *
	 * @param idVereda asigna el valor a la propiedad
	 */
	public void setIdVereda(String idVereda)
	{
		is_idVereda = idVereda;
	}

	/**
	 * Retorna Objeto o variable de valor nombre vereda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreVereda()
	{
		return is_nombreVereda;
	}

	/**
	 * Modifica el valor de NombreVereda.
	 *
	 * @param nombreVereda asigna el valor a la propiedad
	 */
	public void setNombreVereda(String nombreVereda)
	{
		is_nombreVereda = nombreVereda;
	}

	/**
	 * Retorna Objeto o variable de valor id zona registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdZonaRegistral()
	{
		return is_idZonaRegistral;
	}

	/**
	 * Modifica el valor de IdZonaRegistral.
	 *
	 * @param idZonaRegistral asigna el valor a la propiedad
	 */
	public void setIdZonaRegistral(String idZonaRegistral)
	{
		is_idZonaRegistral = idZonaRegistral;
	}
}
