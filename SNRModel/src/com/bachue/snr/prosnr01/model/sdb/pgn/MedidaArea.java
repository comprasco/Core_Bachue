package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_CAUSAL_MEDIDA_AREA.
 *
 * @author stafur
 */
public class MedidaArea extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8128098632551298325L;
	
	/** Propiedad is codigo. */
	private String            is_codigo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id medida area. */
	private String            is_idMedidaArea;

	/**
	 * Constructor por defecto.
	 */
	public MedidaArea()
	{
	}

	/**
	 * Constructor que recibe como parametro el id del area.
	 *
	 * @param as_idMedidaArea de as id medida area
	 */
	public MedidaArea(String as_idMedidaArea)
	{
		is_idMedidaArea                        = as_idMedidaArea;
	}

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param codigo asigna el valor a la propiedad
	 */
	public void setCodigo(String codigo)
	{
		this.is_codigo = codigo;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
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
	 * Modifica el valor de Estado.
	 *
	 * @param estado asigna el valor a la propiedad
	 */
	public void setEstado(String estado)
	{
		this.is_estado = estado;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de IdMedidaArea.
	 *
	 * @param idMedidaArea asigna el valor a la propiedad
	 */
	public void setIdMedidaArea(String idMedidaArea)
	{
		this.is_idMedidaArea = idMedidaArea;
	}

	/**
	 * Retorna Objeto o variable de valor id medida area.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMedidaArea()
	{
		return is_idMedidaArea;
	}
}
