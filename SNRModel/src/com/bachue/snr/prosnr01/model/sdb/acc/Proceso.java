package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PROCESO.
 *
 * @author mblanco
 */
public class Proceso extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3356103145956614106L;

	/** Propiedad il recepcion documento. */
	private String il_recepcionDocumento;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public Proceso()
	{
	}

	/**
	 * Constructor que recibe como parametro el id del proceso.
	 *
	 * @param as_idProceso de as id proceso
	 */
	public Proceso(String as_idProceso)
	{
		is_idProceso = as_idProceso;
	}

	/**
	 * Constructor que recibe como parametro el id del proceso y el nombre.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_nombre de as nombre
	 */
	public Proceso(String as_idProceso, String as_nombre)
	{
		is_idProceso     = as_idProceso;
		is_nombre        = as_nombre;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
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

	/**
	 * Modifica el valor de RecepcionDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRecepcionDocumento(String as_s)
	{
		il_recepcionDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor recepcion documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRecepcionDocumento()
	{
		return il_recepcionDocumento;
	}
}
