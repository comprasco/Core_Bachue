package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Logica de modelo Circulo Festivo siendo una abstracción de la tabla SDB_PGN_CIRCULO_FESTIVO en la base de datos.
 *
 * @author Sebastian Tafur
 */
public class CirculoFestivo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3198256129959162476L;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo festivo. */
	private String is_idCirculoFestivo;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		this.is_activo = activo;
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
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param fecha asigna el valor a la propiedad
	 */
	public void setFecha(Date fecha)
	{
		this.id_fecha = fecha;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param idCirculo asigna el valor a la propiedad
	 */
	public void setIdCirculo(String idCirculo)
	{
		this.is_idCirculo = idCirculo;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculoFestivo.
	 *
	 * @param idCirucloFestivo asigna el valor a la propiedad
	 */
	public void setIdCirculoFestivo(String idCirucloFestivo)
	{
		this.is_idCirculoFestivo = idCirucloFestivo;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo festivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoFestivo()
	{
		return is_idCirculoFestivo;
	}

	/**
	 * Modifica el valor de nombreCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}
}
