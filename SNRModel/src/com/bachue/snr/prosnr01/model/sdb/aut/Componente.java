package com.bachue.snr.prosnr01.model.sdb.aut;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_AUT_COMPONENTE.
 *
 * @author Sebastian Sanchez
 */
public class Componente extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8222227421917343880L;

	/** Propiedad ico opciones rol. */
	private Collection<Opcion> ico_opcionesRol;

	/** Propiedad ico opciones rol target. */
	private Collection<Opcion> ico_opcionesRolTarget;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is boton componente. */
	private String is_botonComponente;

	/** Propiedad is id componente. */
	private String is_idComponente;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is url. */
	private String is_url;

	/** Propiedad ib imagen. */
	private byte[] ib_imagen;

	/**  Propiedad ib_esParte. */
	private boolean ib_esParte;

	/**  Propiedad ib_valorEncontrado. */
	private boolean ib_valorEncontrado;

	/**
	 * Constructor por defecto.
	 */
	public Componente()
	{
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
	 * Get activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Get opciones rol.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Opcion> getOpcionesRol()
	{
		return ico_opcionesRol;
	}

	/**
	 * Modifica el valor de opciones rol.
	 *
	 * @param aco_co asigna el valor a la propiedad
	 */
	public void setOpcionesRol(Collection<Opcion> aco_co)
	{
		ico_opcionesRol = aco_co;
	}

	/**
	 * Get opciones rol target.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Opcion> getOpcionesRolTarget()
	{
		return ico_opcionesRolTarget;
	}

	/**
	 * Modifica el valor de opciones rol target.
	 *
	 * @param aco_co asigna el valor a la propiedad
	 */
	public void setOpcionesRolTarget(Collection<Opcion> aco_co)
	{
		ico_opcionesRolTarget = aco_co;
	}

	/**
	 * Modifica el valor de Fecha desde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Get fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de Fecha hasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Get fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de Id componente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdComponente(String as_s)
	{
		is_idComponente = as_s;
	}

	/**
	 * Get id componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdComponente()
	{
		return is_idComponente;
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
	 * Get nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de url.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUrl(String as_s)
	{
		is_url = as_s;
	}

	/**
	 * Get url.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUrl()
	{
		return is_url;
	}

	/**
	 * Get Imagen.
	 *
	 * @param imagenBlob asigna el valor a la propiedad
	 */
	public void setImagen(byte[] imagenBlob)
	{
		ib_imagen = imagenBlob;
	}

	/**
	 * Retorna Objeto o variable de valor imagen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getImagen()
	{
		return ib_imagen;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return con el valor
	 */
	public boolean isEsParte()
	{
		return ib_esParte;
	}

	/**
	 * Asigna el valor a la propiedad.
	 *
	 * @param ib_esParte con el valor a asignar
	 */
	public void setEsParte(boolean ib_esParte)
	{
		this.ib_esParte = ib_esParte;
	}

	/**
	 * Retorna Objeto o variable de valor boton componente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getBotonComponente()
	{
		return is_botonComponente;
	}

	/**
	 * Get Boton Componente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setBotonComponente(String as_s)
	{
		is_botonComponente = as_s;
	}

	
	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return con el valor
	 */
	public boolean isValorEncontrado()
	{
		return ib_valorEncontrado;
	}

	/**
	 * Asigna el valor a la propiedad.
	 *
	 * @param ab_b de ab b
	 */
	public void setValorEncontrado(boolean ab_b)
	{
		ib_valorEncontrado = ab_b;
	}
}
