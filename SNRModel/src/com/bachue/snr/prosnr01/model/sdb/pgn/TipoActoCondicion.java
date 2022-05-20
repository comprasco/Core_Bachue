package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_ACTO_CONDICION.
 *
 * @author Julian Vaca
 */
public class TipoActoCondicion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = 67060052707791427L;
	
	/** Propiedad ii orden. */
	private Integer           ii_orden;
	
	/** Propiedad ii version. */
	private Integer           ii_version;
	
	/** Propiedad ii version tarifa fija. */
	private Integer           ii_versionTarifaFija;
	
	/** Propiedad ii version tarifa registral. */
	private Integer           ii_versionTarifaRegistral;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is codigo sql. */
	private String            is_codigoSql;
	
	/** Propiedad is id condicion. */
	private String            is_idCondicion;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id sub proceso. */
	private String            is_idSubProceso;
	
	/** Propiedad is id tipo acto. */
	private String            is_idTipoActo;
	
	/** Propiedad is id tipo acto condicion. */
	private String            is_idTipoActoCondicion;
	
	/** Propiedad is id tipo tarifa fija. */
	private String            is_idTipoTarifaFija;
	
	/** Propiedad is id tipo tarifa registral. */
	private String            is_idTipoTarifaRegistral;
	
	/** Propiedad is tipo acto condicion. */
	private String            is_tipoActoCondicion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                  = as_s;
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
	 * Modifica el valor de CodigoSql.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoSql(String as_s)
	{
		this.is_codigoSql = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo sql.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoSql()
	{
		return is_codigoSql;
	}

	/**
	 * Modifica el valor de IdCondicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCondicion(String as_s)
	{
		this.is_idCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCondicion()
	{
		return is_idCondicion;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		this.is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		this.is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTarifaFija(String as_s)
	{
		this.is_idTipoTarifaFija = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifaFija()
	{
		return is_idTipoTarifaFija;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		this.is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoActoCondicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActoCondicion(String as_s)
	{
		this.is_idTipoActoCondicion = as_s;
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
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoTarifaRegistral(String as_s)
	{
		this.is_idTipoTarifaRegistral = as_s;
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
	 * Modifica el valor de Orden.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setOrden(Integer ai_i)
	{
		this.ii_orden = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getOrden()
	{
		return ii_orden;
	}

	/**
	 * Modifica el valor de TipoActoCondicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoActoCondicion(String as_s)
	{
		this.is_tipoActoCondicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo acto condicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoActoCondicion()
	{
		return is_tipoActoCondicion;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersion(Integer ai_i)
	{
		this.ii_version = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersion()
	{
		return ii_version;
	}

	/**
	 * Modifica el valor de VersionTarifaFija.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersionTarifaFija(Integer ai_i)
	{
		this.ii_versionTarifaFija = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersionTarifaFija()
	{
		return ii_versionTarifaFija;
	}

	/**
	 * Modifica el valor de VersionTarifaRegistral.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersionTarifaRegistral(Integer ai_i)
	{
		this.ii_versionTarifaRegistral = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version tarifa registral.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersionTarifaRegistral()
	{
		return ii_versionTarifaRegistral;
	}
}
