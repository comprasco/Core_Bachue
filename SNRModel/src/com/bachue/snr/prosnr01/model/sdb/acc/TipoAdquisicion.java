package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_ADQUISICION.
 *
 * @author Julian Vaca
 */
public class TipoAdquisicion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long           serialVersionUID     = -5806150779614342485L;
	
	/** Propiedad icta info all. */
	private Collection<TipoAdquisicion> icta_infoAll;
	
	/** Propiedad is activo. */
	private String                      is_activo;
	
	/** Propiedad is id tipo adquisicion. */
	private String                      is_idTipoAdquisicion;
	
	/** Propiedad is ip. */
	private String                      is_ip;
	
	/** Propiedad is nombre. */
	private String                      is_nombre;
	
	/** Propiedad ib accion. */
	private boolean                     ib_accion;

	/**
	 * Constructor por defecto.
	 */
	public TipoAdquisicion()
	{
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAccion(boolean ab_b)
	{
		ib_accion                                            = ab_b;
	}

	/**
	 * Valida la propiedad accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAccion()
	{
		return ib_accion;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de IdTipoAdquisicion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoAdquisicion(String as_s)
	{
		is_idTipoAdquisicion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo adquisicion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoAdquisicion()
	{
		return is_idTipoAdquisicion;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param acta_ta asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<TipoAdquisicion> acta_ta)
	{
		icta_infoAll = acta_ta;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoAdquisicion> getInfoAll()
	{
		return icta_infoAll;
	}

	/**
	 * Modifica el valor de Ip.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIp(String as_s)
	{
		is_ip = as_s;
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
		is_nombre = as_s;
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
}
