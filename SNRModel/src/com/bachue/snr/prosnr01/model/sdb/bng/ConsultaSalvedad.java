package com.bachue.snr.prosnr01.model.sdb.bng;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de ConsultaSalvedad.
 *
 * @author Julian Vaca
 */
public class ConsultaSalvedad implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7238070218892485013L;
	
	/** Propiedad ibd orden. */
	private BigDecimal        ibd_orden;
	
	/** Propiedad id fecha registro. */
	private Date              id_fechaRegistro;
	
	/** Propiedad il id anotacion. */
	private Long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad il id salvedad. */
	private Long              il_idSalvedad;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is fecha registro string. */
	private String            is_fechaRegistroString;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is radicacion. */
	private String            is_radicacion;

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion                    = descripcion;
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
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param idAnotacion asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long idAnotacion)
	{
		this.il_idAnotacion = idAnotacion;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}
	
	/**
	 * Modifica el valor de FechaRegistroString.
	 *
	 * @param fechaRegistro asigna el valor a la propiedad
	 */
	public void setFechaRegistroString(String fechaRegistro)
	{
		this.is_fechaRegistroString = fechaRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFechaRegistroString()
	{
		return is_fechaRegistroString;
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
	 * Modifica el valor de Radicacion.
	 *
	 * @param radicacion asigna el valor a la propiedad
	 */
	public void setRadicacion(String radicacion)
	{
		this.is_radicacion = radicacion;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicacion()
	{
		return is_radicacion;
	}
	
	/**
	 * Modifica el valor de Orden.
	 *
	 * @param orden asigna el valor a la propiedad
	 */
	public void setOrden(BigDecimal orden)
	{
		this.ibd_orden = orden;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getOrden()
	{
		return ibd_orden;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param idMatricula asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long idMatricula)
	{
		this.il_idMatricula = idMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdSalvedad.
	 *
	 * @param idSalvedad asigna el valor a la propiedad
	 */
	public void setIdSalvedad(Long idSalvedad)
	{
		this.il_idSalvedad = idSalvedad;
	}

	/**
	 * Retorna Objeto o variable de valor id salvedad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdSalvedad()
	{
		return il_idSalvedad;
	}
}
