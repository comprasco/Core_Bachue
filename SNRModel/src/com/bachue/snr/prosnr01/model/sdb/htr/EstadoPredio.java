package com.bachue.snr.prosnr01.model.sdb.htr;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_HTR_ESTADO_PREDIO.
 *
 * @author Julian Vaca
 */
public class EstadoPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = -3951202841887940516L;
	
	/** Propiedad ibi id matricula. */
	private BigInteger        ibi_idMatricula;
	
	/** Propiedad id fecha creacion. */
	private Date              id_fechaCreacion;
	
	/** Propiedad id fecha registro. */
	private Date              id_fechaRegistro;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id estado predio. */
	private String            is_idEstadoPredio;
	
	/** Propiedad is id estado predio ant. */
	private String            is_idEstadoPredioAnt;
	
	/** Propiedad is justificacion cambio. */
	private String            is_justificacionCambio;

	/**
	 * Modifica el valor de FechaCreacion.
	 *
	 * @param fechaCreacion asigna el valor a la propiedad
	 */
	public void setFechaCreacion(Date fechaCreacion)
	{
		this.id_fechaCreacion                        = fechaCreacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaCreacion()
	{
		return id_fechaCreacion;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param fechaRegistro asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date fechaRegistro)
	{
		this.id_fechaRegistro = fechaRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
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
	 * Modifica el valor de IdEstadoPredio.
	 *
	 * @param idEstadoPredio asigna el valor a la propiedad
	 */
	public void setIdEstadoPredio(String idEstadoPredio)
	{
		this.is_idEstadoPredio = idEstadoPredio;
	}

	/**
	 * Retorna Objeto o variable de valor id estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoPredio()
	{
		return is_idEstadoPredio;
	}

	/**
	 * Modifica el valor de IdEstadoPredioAnt.
	 *
	 * @param idEstadoPredioAnt asigna el valor a la propiedad
	 */
	public void setIdEstadoPredioAnt(String idEstadoPredioAnt)
	{
		this.is_idEstadoPredioAnt = idEstadoPredioAnt;
	}

	/**
	 * Retorna Objeto o variable de valor id estado predio ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEstadoPredioAnt()
	{
		return is_idEstadoPredioAnt;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param idMatricula asigna el valor a la propiedad
	 */
	public void setIdMatricula(BigInteger idMatricula)
	{
		this.ibi_idMatricula = idMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdMatricula()
	{
		return ibi_idMatricula;
	}

	/**
	 * Modifica el valor de JustificacionCambio.
	 *
	 * @param justificacionCambio asigna el valor a la propiedad
	 */
	public void setJustificacionCambio(String justificacionCambio)
	{
		this.is_justificacionCambio = justificacionCambio;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion cambio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getJustificacionCambio()
	{
		return is_justificacionCambio;
	}
}
