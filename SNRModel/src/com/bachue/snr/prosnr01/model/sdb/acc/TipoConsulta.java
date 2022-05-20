package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_CONSULTA.
 *
 * @author Julian Vaca
 */
public class TipoConsulta extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID  = 8436456864757771317L;
	
	/** Propiedad is id tipo consulta. */
	private String            is_idTipoConsulta;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TipoConsulta()
	{
	}

	/**
	 * Modifica el valor de IdTipoConsulta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoConsulta(String as_s)
	{
		is_idTipoConsulta                       = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoConsulta()
	{
		return is_idTipoConsulta;
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
