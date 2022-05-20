package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_ENTIDAD.
 *
 * @author Julian Vaca
 */
public class TipoEntidad extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8181234706473806578L;

	/** Propiedad icte infoAll. */
	private Collection<TipoEntidad> icte_infoAll;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id tipo entidad. */
	private String is_idTipoEntidad;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad ib accion. */
	private boolean ib_accion;

	/**
	 * Constructor por defecto.
	 */
	public TipoEntidad()
	{
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAccion(boolean ab_b)
	{
		ib_accion = ab_b;
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
	 * Modifica el valor de IdTipoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoEntidad(String as_s)
	{
		is_idTipoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEntidad()
	{
		return is_idTipoEntidad;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param ac_s asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<TipoEntidad> ac_s)
	{
		icte_infoAll = ac_s;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoEntidad> getInfoAll()
	{
		return icte_infoAll;
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
}
