package com.bachue.snr.prosnr01.model.view;

import com.bachue.snr.prosnr01.model.sdb.png.EntidadesAlerta;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades EntidadesEspeciales.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/07/2020
 */
public class EntidadesEspeciales extends EntidadesAlerta implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8879733276293149959L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is direccion. */
	private String is_direccion;

	/** Propiedad is entidad exenta. */
	private String is_entidadExenta;

	/** Propiedad is id actividad economica. */
	private String is_idActividadEconomica;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id representante legal. */
	private String is_idRepresentanteLegal;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is id tipo oficina. */
	private String is_idTipoOficina;

	/** Propiedad is telefono. */
	private String is_telefono;

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor direccion.
	 *
	 * @return Retorna el valor de la propiedad direccion
	 */
	public String getDireccion()
	{
		return is_direccion;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_s de as s
	 */
	public void setDireccion(String as_s)
	{
		is_direccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor entidad exenta.
	 *
	 * @return Retorna el valor de la propiedad entidadExenta
	 */
	public String getEntidadExenta()
	{
		return is_entidadExenta;
	}

	/**
	 * Modifica el valor de EntidadExenta.
	 *
	 * @param as_s de as s
	 */
	public void setEntidadExenta(String as_s)
	{
		is_entidadExenta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id actividad economica.
	 *
	 * @return Retorna el valor de la propiedad idActividadEconomica
	 */
	public String getIdActividadEconomica()
	{
		return is_idActividadEconomica;
	}

	/**
	 * Modifica el valor de IdActividadEconomica.
	 *
	 * @param as_s de as s
	 */
	public void setIdActividadEconomica(String as_s)
	{
		is_idActividadEconomica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return Retorna el valor de la propiedad idMunicipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s de as s
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id representante legal.
	 *
	 * @return Retorna el valor de la propiedad idRepresentanteLegal
	 */
	public String getIdRepresentanteLegal()
	{
		return is_idRepresentanteLegal;
	}

	/**
	 * Modifica el valor de IdRepresentanteLegal.
	 *
	 * @param as_s de as s
	 */
	public void setIdRepresentanteLegal(String as_s)
	{
		is_idRepresentanteLegal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo oficina.
	 *
	 * @return Retorna el valor de la propiedad idTipoOficina
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de IdTipoOficina.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOficina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return Retorna el valor de la propiedad idPais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s de as s
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo entidad.
	 *
	 * @return Retorna el valor de la propiedad idTipoEntidad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de IdTipoEntidad.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor telefono.
	 *
	 * @return Retorna el valor de la propiedad telefono
	 */
	public String getTelefono()
	{
		return is_telefono;
	}

	/**
	 * Modifica el valor de Telefono.
	 *
	 * @param as_s de as s
	 */
	public void setTelefono(String as_s)
	{
		is_telefono = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return Retorna el valor de la propiedad idDepartamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s de as s
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}
}
