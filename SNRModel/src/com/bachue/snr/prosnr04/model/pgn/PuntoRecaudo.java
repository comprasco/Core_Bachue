package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de punto recaudo.
 *
 * @author Julian Vaca
 */
public class PuntoRecaudo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1619794393697290520L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is codigo punto recaudo. */
	private String is_codigoPuntoRecaudo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is id medio recaudo. */
	private String is_idMedioRecaudo;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id punto recaudo. */
	private String is_idPuntoRecaudo;

	/**  Propiedad is nombre departamento. */
	private String is_nombreDepartamento;

	/** Propiedad is nombre entidad recaudadora. */
	private String is_nombreEntidadRecaudadora;

	/** Propiedad is nombre medio recaudo. */
	private String is_nombreMedioRecaudo;

	/**  Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/**  Propiedad is nombre pais. */
	private String is_nombrePais;

	/** Propiedad is nombre punto recaudo. */
	private String is_nombrePuntoRecaudo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de CodigoPuntoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setCodigoPuntoRecaudo(String as_s)
	{
		this.is_codigoPuntoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo punto recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoPuntoRecaudo()
	{
		return is_codigoPuntoRecaudo;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s de as s
	 */
	public void setIdDepartamento(String as_s)
	{
		this.is_idDepartamento = as_s;
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
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de IdMedioRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdMedioRecaudo(String as_s)
	{
		this.is_idMedioRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id medio recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMedioRecaudo()
	{
		return is_idMedioRecaudo;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s de as s
	 */
	public void setIdMunicipio(String as_s)
	{
		this.is_idMunicipio = as_s;
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
	 * @param as_s de as s
	 */
	public void setIdPais(String as_s)
	{
		this.is_idPais = as_s;
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
	 * Modifica el valor de IdPuntoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdPuntoRecaudo(String as_s)
	{
		this.is_idPuntoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id punto recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPuntoRecaudo()
	{
		return is_idPuntoRecaudo;
	}

	/**
	 * Modifica el valor de NombrePuntoRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setNombrePuntoRecaudo(String as_s)
	{
		this.is_nombrePuntoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre punto recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePuntoRecaudo()
	{
		return is_nombrePuntoRecaudo;
	}

	/**
	 * Modifica el valor de NombreEntidadRecaudadora.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
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
	 * Modifica el valor de NombreMedioRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreMedioRecaudo(String as_s)
	{
		this.is_nombreMedioRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre medio recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMedioRecaudo()
	{
		return is_nombreMedioRecaudo;
	}
}
