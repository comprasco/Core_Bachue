package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de TipoTarifaRegistral.
 *
 * @author Julian Vaca
 */
public class TipoTarifaRegistral extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long               serialVersionUID = 1889163101793696710L;
	
	/** Propiedad info all. */
	private Collection<TipoTarifaRegistral> infoAll;
	
	/** Propiedad id fecha desde. */
	private Date                            id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date                            id_fechaHasta;
	
	/** Propiedad id porcentaje. */
	private Double                          id_porcentaje;
	
	/** Propiedad il version. */
	private Long                            il_version;
	
	/** Propiedad is activo. */
	private String                          is_activo;
	
	/** Propiedad is id tipo tarifa. */
	private String                          is_idTipoTarifa;
	
	/** Propiedad is ip. */
	private String                          is_ip;
	
	/** Propiedad is nombre. */
	private String                          is_nombre;
	
	/** Propiedad ib valid tipo id. */
	private boolean                         ib_validTipoId;
	
	/** Propiedad ib validversion. */
	private boolean                         ib_validversion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                       = as_s;
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
	 * Modifica el valor de IdTipoTarifa.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoTarifa(String as_s)
	{
		this.is_idTipoTarifa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo tarifa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTarifa()
	{
		return is_idTipoTarifa;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param infoAll asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<TipoTarifaRegistral> infoAll)
	{
		this.infoAll = infoAll;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoTarifaRegistral> getInfoAll()
	{
		return infoAll;
	}

	/**
	 * Modifica el valor de Ip.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIp(String as_s)
	{
		this.is_ip = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ip.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIp()
	{
		return is_ip;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
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
	 * Modifica el valor de Porcentaje.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setPorcentaje(Double ad_d)
	{
		this.id_porcentaje = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Double getPorcentaje()
	{
		return id_porcentaje;
	}

	/**
	 * Modifica el valor de ValidTipoId.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidTipoId(boolean ab_b)
	{
		this.ib_validTipoId = ab_b;
	}

	/**
	 * Valida la propiedad valid tipo id.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidTipoId()
	{
		return ib_validTipoId;
	}

	/**
	 * Modifica el valor de Validversion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidversion(boolean ab_b)
	{
		this.ib_validversion = ab_b;
	}

	/**
	 * Valida la propiedad validversion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidversion()
	{
		return ib_validversion;
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
}
