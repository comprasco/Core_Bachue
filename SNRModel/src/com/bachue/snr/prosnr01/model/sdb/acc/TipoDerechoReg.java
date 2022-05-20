package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de TipoDerechoReg.
 *
 * @author Julian Vaca
 */
public class TipoDerechoReg extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 3334070758815285114L;
	
	/** Propiedad is id tipo derecho reg. */
	private String            is_idTipoDerechoReg;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Modifica el valor de IdTipoDerechoReg.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDerechoReg(String as_s)
	{
		is_idTipoDerechoReg                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo derecho reg.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDerechoReg()
	{
		return is_idTipoDerechoReg;
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
}
