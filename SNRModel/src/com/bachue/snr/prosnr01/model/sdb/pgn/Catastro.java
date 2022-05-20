package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Logica de modelo Catastro siendo una abstracción de la tabla SDB_PGN_CATASTRO en la base de datos.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/08/2020
 */
public class Catastro extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8647059028891669524L;

	/** Propiedad correo electronico. */
	private String is_correoElectronico;

	/** Propiedad direccion. */
	private String is_direccion;

	/** Propiedad id catastro. */
	private String is_idCatastro;

	/** Propiedad id departamento. */
	private String is_idDepartamento;

	/** Propiedad id municipio. */
	private String is_idMunicipio;

	/** Propiedad id pais. */
	private String is_idPais;

	/** Propiedad nombre. */
	private String is_nombre;

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_correoElectronico Modifica el valor de la propiedad correoElectronico
	 */
	public void setCorreoElectronico(String as_correoElectronico)
	{
		is_correoElectronico = as_correoElectronico;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return Retorna el valor de la propiedad correoElectronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de Direccion.
	 *
	 * @param as_direccion Modifica el valor de la propiedad direccion
	 */
	public void setDireccion(String as_direccion)
	{
		is_direccion = as_direccion;
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
	 * Modifica el valor de IdCatastro.
	 *
	 * @param as_idCatastro Modifica el valor de la propiedad idCatastro
	 */
	public void setIdCatastro(String as_idCatastro)
	{
		is_idCatastro = as_idCatastro;
	}

	/**
	 * Retorna Objeto o variable de valor id catastro.
	 *
	 * @return Retorna el valor de la propiedad idCatastro
	 */
	public String getIdCatastro()
	{
		return is_idCatastro;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_idDepartamento Modifica el valor de la propiedad idDepartamento
	 */
	public void setIdDepartamento(String as_idDepartamento)
	{
		is_idDepartamento = as_idDepartamento;
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
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_idMunicipio Modifica el valor de la propiedad idMunicipio
	 */
	public void setIdMunicipio(String as_idMunicipio)
	{
		is_idMunicipio = as_idMunicipio;
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
	 * Modifica el valor de IdPais.
	 *
	 * @param as_idPais Modifica el valor de la propiedad idPais
	 */
	public void setIdPais(String as_idPais)
	{
		is_idPais = as_idPais;
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
	 * Modifica el valor de Nombre.
	 *
	 * @param as_nombre Modifica el valor de la propiedad nombre
	 */
	public void setNombre(String as_nombre)
	{
		is_nombre = as_nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return Retorna el valor de la propiedad nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
