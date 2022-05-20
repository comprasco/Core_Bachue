package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la surcursal del canal de origen.
 *
 * @author Julian Vaca
 */
public class SucursalCanalOrigenServicio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                     = -2952343226487042594L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is codigo sucursal canal origen servicio. */
	private String            is_codigoSucursalCanalOrigenServicio;
	
	/** Propiedad is id canal origen servicio. */
	private String            is_idCanalOrigenServicio;
	
	/** Propiedad is id sucursal canal origen servicio. */
	private String            is_idSucursalCanalOrigenServicio;
	
	/** Propiedad is nombre sucursal canal origen servicio. */
	private String            is_nombreSucursalCanalOrigenServicio;

	/**
	 * Constructor por defecto.
	 */
	public SucursalCanalOrigenServicio()
	{
	}

	/**
	 * Constructor que recibe por parametro el idCanalOrigenServicio y codigoSucursalCanalOrigenServicio.
	 *
	 * @param as_idCanalOrigenServicio de as id canal origen servicio
	 * @param as_codigoSucursalCanalOrigenServicio de as codigo sucursal canal origen servicio
	 */
	public SucursalCanalOrigenServicio(String as_idCanalOrigenServicio, String as_codigoSucursalCanalOrigenServicio)
	{
		setIdCanalOrigenServicio(as_idCanalOrigenServicio);
		setCodigoSucursalCanalOrigenServicio(as_codigoSucursalCanalOrigenServicio);
	}

	
	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                             = as_s;
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
	 * Modifica el valor de CodigoSucursalCanalOrigenServicio.
	 *
	 * @param as_s de as s
	 */
	public void setCodigoSucursalCanalOrigenServicio(String as_s)
	{
		this.is_codigoSucursalCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo sucursal canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoSucursalCanalOrigenServicio()
	{
		return is_codigoSucursalCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de IdCanalOrigenServicio.
	 *
	 * @param as_s de as s
	 */
	public void setIdCanalOrigenServicio(String as_s)
	{
		this.is_idCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanalOrigenServicio()
	{
		return is_idCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de IdSucursalCanalOrigenServicio.
	 *
	 * @param as_s de as s
	 */
	public void setIdSucursalCanalOrigenServicio(String as_s)
	{
		this.is_idSucursalCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sucursal canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSucursalCanalOrigenServicio()
	{
		return is_idSucursalCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de NombreSucursalCanalOrigenServicio.
	 *
	 * @param as_s de as s
	 */
	public void setNombreSucursalCanalOrigenServicio(String as_s)
	{
		this.is_nombreSucursalCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre sucursal canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreSucursalCanalOrigenServicio()
	{
		return is_nombreSucursalCanalOrigenServicio;
	}
}
