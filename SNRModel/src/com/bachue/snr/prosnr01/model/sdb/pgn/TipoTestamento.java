package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_TESTAMENTO.
 *
 * @author Julian Vaca
 */
public class TipoTestamento extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 6899814735767411977L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tipo testamento. */
	private String            is_idTipoTestamento;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                            = as_s;
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
	 * Modifica el valor de IdTipoTestamento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoTestamento(String as_s)
	{
		this.is_idTipoTestamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo testamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoTestamento()
	{
		return is_idTipoTestamento;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
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
