package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_GOBERNACION.
 *
 * @author julian Vaca
 */
public class Gobernacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID              = -77506107129294036L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id departamento. */
	private String            is_idDepartamento;
	
	/** Propiedad is id gobernacion. */
	private String            is_idGobernacion;
	
	/** Propiedad is id pais. */
	private String            is_idPais;
	
	/** Propiedad is nombre gobernacion. */
	private String            is_nombreGobernacion;
	
	/** Propiedad is tipo integracion gobernacion. */
	private String            is_tipoIntegracionGobernacion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.is_activo                                      = activo;
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
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param idDepartamento asigna el valor a la propiedad
	 */
	public void setIdDepartamento(String idDepartamento)
	{
		this.is_idDepartamento = idDepartamento;
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
	 * Modifica el valor de IdGobernacion.
	 *
	 * @param idGobernacion asigna el valor a la propiedad
	 */
	public void setIdGobernacion(String idGobernacion)
	{
		this.is_idGobernacion = idGobernacion;
	}

	/**
	 * Retorna Objeto o variable de valor id gobernacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGobernacion()
	{
		return is_idGobernacion;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param idPais asigna el valor a la propiedad
	 */
	public void setIdPais(String idPais)
	{
		this.is_idPais = idPais;
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
	 * Modifica el valor de NombreGobernacion.
	 *
	 * @param nombreGobernacion asigna el valor a la propiedad
	 */
	public void setNombreGobernacion(String nombreGobernacion)
	{
		this.is_nombreGobernacion = nombreGobernacion;
	}

	/**
	 * Retorna Objeto o variable de valor nombre gobernacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreGobernacion()
	{
		return is_nombreGobernacion;
	}

	/**
	 * Modifica el valor de TipoIntegracionGobernacion.
	 *
	 * @param tipoIntegracionGobernacion asigna el valor a la propiedad
	 */
	public void setTipoIntegracionGobernacion(String tipoIntegracionGobernacion)
	{
		this.is_tipoIntegracionGobernacion = tipoIntegracionGobernacion;
	}

	/**
	 * Retorna Objeto o variable de valor tipo integracion gobernacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoIntegracionGobernacion()
	{
		return is_tipoIntegracionGobernacion;
	}
}
