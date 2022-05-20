package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_ROL.
 *
 * @author Julian Vaca
 */
public class Rol extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3462209735181425382L;
	
	/** Propiedad id fecha desde. */
	private Date              id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date              id_fechaHasta;
	
	/** Propiedad is activo. */
	private String            is_activo;

	/**  Propiedad is id componente. */
	private String is_idComponente;

	/**  Propiedad is id rol. */
	private String is_idRol;

	/**  Propiedad is id nombre. */
	private String is_nombre;

	/**  Propiedad is nombre componente. */
	private String is_nombreComponente;

	/**  Propiedad is id nombre. */
	private boolean ib_actuacionesAdministrativas;

	/**
	 * Constructor por defecto.
	 */
	public Rol()
	{
	}

	/**
	 * Constructor que recibe como parametro el id del rol asociado al usuario.
	 *
	 * @param as_idRol id rol del usuario
	 */
	public Rol(String as_idRol)
	{
		setIdRol(as_idRol);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                              = as_s;
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
	 * Modifica el valor de ActuacionesAdministrativas.
	 *
	 * @param ab_b de ab b
	 */
	public void setActuacionesAdministrativas(boolean ab_b)
	{
		this.ib_actuacionesAdministrativas = ab_b;
	}

	/**
	 * Valida la propiedad actuaciones administrativas.
	 *
	 * @return Retorna el valor de la propiedad actuacionesAdministrativas
	 */
	public boolean isActuacionesAdministrativas()
	{
		return ib_actuacionesAdministrativas;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_D asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_D)
	{
		id_fechaDesde = ad_D;
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
		id_fechaHasta = ad_d;
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
	 * Modifica el valor de IdComponente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdComponente(String as_s)
	{
		is_idComponente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComponente()
	{
		return is_idComponente;
	}

	/**
	 * Modifica el valor de IdRol.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRol(String as_s)
	{
		is_idRol = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id rol.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRol()
	{
		return is_idRol;
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
	 * Modifica el valor de NombreComponente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreComponente(String as_s)
	{
		is_nombreComponente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreComponente()
	{
		return is_nombreComponente;
	}
}
