package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_TIPO_CANAL_ORIGEN.
 *
 * @author Carlos Calderón
 */
public class TipoCanalOrigen extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = 7092035572375982534L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion canal origen. */
	private String            is_descripcionCanalOrigen;
	
	/** Propiedad is id tipo canal origen. */
	private String            is_idTipoCanalOrigen;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                                  = as_s;
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
	 * Modifica el valor de DescripcionCanalOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionCanalOrigen(String as_s)
	{
		this.is_descripcionCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion canal origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionCanalOrigen()
	{
		return is_descripcionCanalOrigen;
	}

	/**
	 * Modifica el valor de IdTipoCanalOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCanalOrigen(String as_s)
	{
		this.is_idTipoCanalOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo canal origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCanalOrigen()
	{
		return is_idTipoCanalOrigen;
	}
}
