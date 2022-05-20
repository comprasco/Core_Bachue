package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TARIFA_FIJA.
 *
 * @author Julian Vaca
 */
public class TarifaFija extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6649846718909057960L;
	
	/** Propiedad id fecha desde. */
	private Date              id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date              id_fechaHasta;
	
	/** Propiedad ii valor. */
	private Integer           ii_valor;
	
	/** Propiedad il version. */
	private Long              il_version;
	
	/** Propiedad il version acto. */
	private Long              il_versionActo;
	
	/** Propiedad as activo. */
	private String            as_activo;
	
	/** Propiedad as id proceso. */
	private String            as_idProceso;
	
	/** Propiedad as id sub proceso. */
	private String            as_idSubProceso;
	
	/** Propiedad as id tarifa fija. */
	private String            as_idTarifaFija;
	
	/** Propiedad as id tipo acto. */
	private String            as_idTipoActo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.as_activo                         = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return as_activo;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		this.id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		this.id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		this.as_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return as_idProceso;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		this.as_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return as_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTarifaFija.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTarifaFija(String as_s)
	{
		this.as_idTarifaFija = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tarifa fija.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTarifaFija()
	{
		return as_idTarifaFija;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		this.as_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return as_idTipoActo;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setValor(Integer ai_i)
	{
		this.ii_valor = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getValor()
	{
		return ii_valor;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersion(Long al_l)
	{
		this.il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersion()
	{
		return il_version;
	}

	/**
	 * Modifica el valor de VersionActo.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setVersionActo(Long al_l)
	{
		this.il_versionActo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersionActo()
	{
		return il_versionActo;
	}
}
