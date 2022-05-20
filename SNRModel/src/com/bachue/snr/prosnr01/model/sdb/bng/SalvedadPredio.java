package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_SALVEDAD_PREDIO.
 *
 * @author garias
 */
public class SalvedadPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 3612867893582767821L;
	
	/** Propiedad id fecha registro. */
	private Date              id_fechaRegistro;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad il id salvedad predio. */
	private Long              il_idSalvedadPredio;
	
	/** Propiedad il ilid. */
	private Long              il_ilid;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is radicacion. */
	private String            is_radicacion;

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion                       = as_s;
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
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		this.id_fechaRegistro = ad_d;
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
	 * Modifica el valor de Id.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setId(Long al_l)
	{
		this.il_ilid = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getId()
	{
		return il_ilid;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
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
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		this.il_idMatricula = al_l;
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
	 * Modifica el valor de IdSalvedadPredio.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdSalvedadPredio(Long al_l)
	{
		this.il_idSalvedadPredio = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id salvedad predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdSalvedadPredio()
	{
		return il_idSalvedadPredio;
	}

	/**
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicacion(String as_s)
	{
		this.is_radicacion = as_s;
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
}
