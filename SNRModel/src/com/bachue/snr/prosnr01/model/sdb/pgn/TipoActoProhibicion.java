package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase que contiene todos las propiedades TipoActoProhibicion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoActoProhibicion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long               serialVersionUID          = 9034569064506186786L;
	
	/** Propiedad ictap tipo acto prohibicion. */
	private Collection<TipoActoProhibicion> ictap_tipoActoProhibicion;
	
	/** Propiedad is activo. */
	private String                          is_activo;
	
	/** Propiedad is cancelacion automatica. */
	private String                          is_cancelacionAutomatica;
	
	/** Propiedad is id tipo acto. */
	private String                          is_idTipoActo;
	
	/** Propiedad is id tipo acto prohibicion. */
	private String                          is_idTipoActoProhibicion;
	
	/** Propiedad is id tipo adquisicion. */
	private String                          is_idTipoAdquisicion;
	
	/** Propiedad is id unidad tiempo. */
	private String                          is_idUnidadTiempo;
	
	/** Propiedad il tiempo vencimiento. */
	private Long                         il_tiempoVencimiento;
	
	/** Propiedad is version acto. */
	private String                          is_versionActo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo de as activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo                                                     = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de CancelacionAutomatica.
	 *
	 * @param as_cancelacionAutomatica de as cancelacion automatica
	 */
	public void setCancelacionAutomatica(String as_cancelacionAutomatica)
	{
		is_cancelacionAutomatica = as_cancelacionAutomatica;
	}

	/**
	 * Retorna Objeto o variable de valor cancelacion automatica.
	 *
	 * @return el valor de cancelacion automatica
	 */
	public String getCancelacionAutomatica()
	{
		return is_cancelacionAutomatica;
	}

	/**
	 * Modifica el valor de TipoActoProhibicion.
	 *
	 * @param actap_tipoActoProhibicion de actap tipo acto prohibicion
	 */
	public void setTipoActoProhibicion(Collection<TipoActoProhibicion> actap_tipoActoProhibicion)
	{
		ictap_tipoActoProhibicion = actap_tipoActoProhibicion;
	}

	/**
	 * Retorna Objeto o variable de valor ictap tipo acto prohibicion.
	 *
	 * @return el valor de ictap tipo acto prohibicion
	 */
	public Collection<TipoActoProhibicion> getIctap_tipoActoProhibicion()
	{
		return ictap_tipoActoProhibicion;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 */
	public void setIdTipoActo(String as_idTipoActo)
	{
		is_idTipoActo = as_idTipoActo;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return el valor de id tipo acto
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoActoProhibicion.
	 *
	 * @param as_idTipoActoProhibicion de as id tipo acto prohibicion
	 */
	public void setIdTipoActoProhibicion(String as_idTipoActoProhibicion)
	{
		is_idTipoActoProhibicion = as_idTipoActoProhibicion;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto prohibicion.
	 *
	 * @return el valor de id tipo acto prohibicion
	 */
	public String getIdTipoActoProhibicion()
	{
		return is_idTipoActoProhibicion;
	}

	/**
	 * Modifica el valor de IdTipoAdquisicion.
	 *
	 * @param as_idTipoAdquisicion de as id tipo adquisicion
	 */
	public void setIdTipoAdquisicion(String as_idTipoAdquisicion)
	{
		is_idTipoAdquisicion = as_idTipoAdquisicion;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo adquisicion.
	 *
	 * @return el valor de id tipo adquisicion
	 */
	public String getIdTipoAdquisicion()
	{
		return is_idTipoAdquisicion;
	}

	/**
	 * Modifica el valor de IdUnidadTiempo.
	 *
	 * @param as_idUnidadTiempo de as id unidad tiempo
	 */
	public void setIdUnidadTiempo(String as_idUnidadTiempo)
	{
		is_idUnidadTiempo = as_idUnidadTiempo;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad tiempo.
	 *
	 * @return el valor de id unidad tiempo
	 */
	public String getIdUnidadTiempo()
	{
		return is_idUnidadTiempo;
	}

	/**
	 * Modifica el valor de TiempoVencimiento.
	 *
	 * @param al_tiempoVencimiento de al tiempo vencimiento
	 */
	public void setTiempoVencimiento(Long al_tiempoVencimiento)
	{
		il_tiempoVencimiento = al_tiempoVencimiento;
	}

	/**
	 * Retorna Objeto o variable de valor tiempo vencimiento.
	 *
	 * @return el valor de tiempo vencimiento
	 */
	public Long getTiempoVencimiento()
	{
		return il_tiempoVencimiento;
	}

	/**
	 * Modifica el valor de VersionActo.
	 *
	 * @param as_versionActo de as version acto
	 */
	public void setVersionActo(String as_versionActo)
	{
		this.is_versionActo = as_versionActo;
	}

	/**
	 * Retorna Objeto o variable de valor version acto.
	 *
	 * @return el valor de version acto
	 */
	public String getVersionActo()
	{
		return is_versionActo;
	}
}
