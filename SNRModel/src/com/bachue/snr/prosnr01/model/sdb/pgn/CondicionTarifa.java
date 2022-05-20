package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_CONDICION_TARIFA.
 *
 * @author stafur
 */
public class CondicionTarifa extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = 1006953602080950612L;
	
	/** Propiedad is activa. */
	private String            is_activa;
	
	/** Propiedad is codigo sql validacion. */
	private String            is_codigoSqlValidacion;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id condicion tarifa. */
	private String            is_idCondicionTarifa;
	
	/** Propiedad is id tarifa fija. */
	private String            is_idTarifaFija;
	
	/** Propiedad is id tipo acto condicion. */
	private String            is_idTipoActoCondicion;
	
	/** Propiedad is id tipo tarifa registral. */
	private String            is_idTipoTarifaRegistral;
	
	/** Propiedad is version tarifa fija. */
	private String            is_versionTarifaFija;
	
	/** Propiedad is version tarifa registral. */
	private String            is_versionTarifaRegistral;

	/**
	 * Modifica el valor de Activa.
	 *
	 * @param activa asigna el valor a la propiedad
	 */
	public void setActiva(String activa)
	{
		this.is_activa                                  = activa;
	}

	/**
	 * Retorna Objeto o variable de valor activa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActiva()
	{
		return is_activa;
	}

	/**
	 * Modifica el valor de CodigoSqlValidacion.
	 *
	 * @param codigoSqlValidacion asigna el valor a la propiedad
	 */
	public void setCodigoSqlValidacion(String codigoSqlValidacion)
	{
		this.is_codigoSqlValidacion = codigoSqlValidacion;
	}

	/**
	 * Retorna Objeto o variable de valor codigo sql validacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoSqlValidacion()
	{
		return is_codigoSqlValidacion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de IdCondicionTarifa.
	 *
	 * @param idCondicionTarifa asigna el valor a la propiedad
	 */
	public void setIdCondicionTarifa(String idCondicionTarifa)
	{
		this.is_idCondicionTarifa = idCondicionTarifa;
	}

	/**
	 * Retorna Objeto o variable de valor id condicion tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCondicionTarifa()
	{
		return is_idCondicionTarifa;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param idTarifaFija asigna el valor a la propiedad
	 */
	public void setIdTarifaFija(String idTarifaFija)
	{
		this.is_idTarifaFija = idTarifaFija;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifaFija()
	{
		return is_idTarifaFija;
	}

	/**
	 * Modifica el valor de IdTipoActoCondicion.
	 *
	 * @param idTipoActoCondicion asigna el valor a la propiedad
	 */
	public void setIdTipoActoCondicion(String idTipoActoCondicion)
	{
		this.is_idTipoActoCondicion = idTipoActoCondicion;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActoCondicion()
	{
		return is_idTipoActoCondicion;
	}

	/**
	 * Modifica el valor de IdTipoTarifaRegistral.
	 *
	 * @param idTipoTarifaRegistral asigna el valor a la propiedad
	 */
	public void setIdTipoTarifaRegistral(String idTipoTarifaRegistral)
	{
		this.is_idTipoTarifaRegistral = idTipoTarifaRegistral;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo tarifa registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTarifaRegistral()
	{
		return is_idTipoTarifaRegistral;
	}

	/**
	 * Modifica el valor de VersionTarifaFija.
	 *
	 * @param versionTarifaFija asigna el valor a la propiedad
	 */
	public void setVersionTarifaFija(String versionTarifaFija)
	{
		this.is_versionTarifaFija = versionTarifaFija;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVersionTarifaFija()
	{
		return is_versionTarifaFija;
	}

	/**
	 * Modifica el valor de VersionTarifaRegistral.
	 *
	 * @param versionTarifaRegistral asigna el valor a la propiedad
	 */
	public void setVersionTarifaRegistral(String versionTarifaRegistral)
	{
		this.is_versionTarifaRegistral = versionTarifaRegistral;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getVersionTarifaRegistral()
	{
		return is_versionTarifaRegistral;
	}
}
