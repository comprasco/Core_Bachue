package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA_DIRECCION.
 *
 * @author Julian Vaca
 */
public class PersonaDireccion extends Direccion implements Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = 1774170678194545275L;

	/**
	 * Propiedad id fecha desde.
	 */
	private Date id_fechaDesde;

	/**
	 * Propiedad id fecha hasta.
	 */
	private Date id_fechaHasta;

	/**
	 * Propiedad is activo.
	 */
	private String is_activo;

	/**
	 * Propiedad is id persona.
	 */
	private String is_idPersona;

	/**
	 * Propiedad is tipo direccion.
	 */
	private String is_tipoDireccion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s the activo to set
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de Id persona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Get id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de Tipo direccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDireccion(String as_s)
	{
		is_tipoDireccion = as_s;
	}

	/**
	 * Get tipo direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDireccion()
	{
		return is_tipoDireccion;
	}
}
