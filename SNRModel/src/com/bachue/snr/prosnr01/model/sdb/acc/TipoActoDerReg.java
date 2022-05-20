package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de TipoActoDerReg.
 *
 * @author Julian Vaca
 */
public class TipoActoDerReg extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 8701222444710789565L;
	
	/** Propiedad is id tipo acto. */
	private String            is_idTipoActo;
	
	/** Propiedad is id tipo derecho reg. */
	private String            is_idTipoDerechoReg;

	/**
	 * Constructor por defecto.
	 */
	public TipoActoDerReg()
	{
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo                             = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoDerechoReg.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDerechoReg(String as_s)
	{
		is_idTipoDerechoReg = as_s;
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
}
