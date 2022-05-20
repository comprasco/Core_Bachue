package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_OFICINA.
 *
 * @author Julian Vaca
 */
public class TipoOficina extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long       serialVersionUID          = -3657601039179759383L;
	
	/** Propiedad icto info all. */
	private Collection<TipoOficina> icto_infoAll;
	
	/** Propiedad is activa. */
	private String                  is_activa;
	
	/** Propiedad is descripcion tipo entidad. */
	private String                  is_descripcionTipoEntidad;
	
	/** Propiedad is id tipo entidad. */
	private String                  is_idTipoEntidad;
	
	/** Propiedad is id tipo oficina. */
	private String                  is_idTipoOficina;
	
	/** Propiedad is nombre. */
	private String                  is_nombre;
	
	/** Propiedad ib accion. */
	private boolean                 ib_accion;

	/**
	 * Constructor por defecto.
	 */
	public TipoOficina()
	{
	}

	/**
	 * Constructor con argumento de informacion de la oficina.
	 *
	 * @param as_sinInformacion correspondiente al tipo de oficina
	 */
	public TipoOficina(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param accion asigna el valor a la propiedad
	 */
	public void setAccion(boolean accion)
	{
		this.ib_accion                                        = accion;
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
	 * Modifica el valor de Activa.
	 *
	 * @param activa asigna el valor a la propiedad
	 */
	public void setActiva(String activa)
	{
		this.is_activa = activa;
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
	 * Modifica el valor de DescripcionTipoEntidad.
	 *
	 * @param descripcionTipoEntidad asigna el valor a la propiedad
	 */
	public void setDescripcionTipoEntidad(String descripcionTipoEntidad)
	{
		this.is_descripcionTipoEntidad = descripcionTipoEntidad;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion tipo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionTipoEntidad()
	{
		return is_descripcionTipoEntidad;
	}

	/**
	 * Modifica el valor de IdTipoEntidad.
	 *
	 * @param idTipoEntidad asigna el valor a la propiedad
	 */
	public void setIdTipoEntidad(String idTipoEntidad)
	{
		this.is_idTipoEntidad = idTipoEntidad;
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
	 * Modifica el valor de IdTipoOficina.
	 *
	 * @param idTipoOficina asigna el valor a la propiedad
	 */
	public void setIdTipoOficina(String idTipoOficina)
	{
		this.is_idTipoOficina = idTipoOficina;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param infoAll asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<TipoOficina> infoAll)
	{
		this.icto_infoAll = infoAll;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoOficina> getInfoAll()
	{
		return icto_infoAll;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
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
