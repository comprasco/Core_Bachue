package com.bachue.snr.prosnr01.model.sdb.bng;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_ANOTACION_CANCELACION.
 *
 * @author garias
 */
public class AnotacionCancelacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4769648285846276266L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad il id anotacion. */
	private long              il_idAnotacion;
	
	/** Propiedad il id anotacion 1. */
	private long              il_idAnotacion1;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionCancelacion()
	{
	}

	/**
	 * Constructor que recibe como parametro id circulo, id matricula y id anotacion.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_idMatricula id matricula
	 * @param al_idAnotacion id anotacion
	 */
	public AnotacionCancelacion(String as_idCirculo, long al_idMatricula, long al_idAnotacion)
	{
		setIdCirculo(as_idCirculo);
		setIdMatricula(al_idMatricula);
		setIdAnotacion(al_idAnotacion);
	}

	/**
	 * Constructor que recibe como parametro un objeto de tipo anotacion cancelacion.
	 *
	 * @param aac_ac anotacion cancelacion
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion
	 */
	public AnotacionCancelacion(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion aac_ac)
	{
		if(aac_ac != null)
		{
			setIdCirculo(aac_ac.getIdCirculo());
			setIdMatricula(aac_ac.getIdMatricula());
			setIdAnotacion(NumericUtils.getLong(aac_ac.getIdAnotacion()));
			setIdAnotacion1(NumericUtils.getLong(aac_ac.getIdAnotacion1()));
			setDescripcion(aac_ac.getDescripcion());
		}
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion                    = as_s;
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
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(long al_l)
	{
		this.il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacion1.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion1(long al_l)
	{
		this.il_idAnotacion1 = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion1()
	{
		return il_idAnotacion1;
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
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}
}
