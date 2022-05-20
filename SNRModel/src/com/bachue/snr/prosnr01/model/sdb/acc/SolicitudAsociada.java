package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_ASOCIADA.
 *
 * @author mblanco
 */
public class SolicitudAsociada extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = -8925502705490016362L;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is id solicitud 1. */
	private String            is_idSolicitud1;
	
	/** Propiedad is id sub proceso. */
	private String            is_idSubProceso;
	
	/** Propiedad is nir solicitud 1. */
	private String            is_nirSolicitud1;
	
	/** Propiedad is nombre proceso. */
	private String            is_nombreProceso;
	
	/** Propiedad is nombre sub proceso. */
	private String            is_nombreSubProceso;
	
	/** Propiedad ib ind vinculado. */
	private String           is_indVinculado;

	/**
	 * Constructor por defecto.
	 */
	public SolicitudAsociada()
	{
	}

	/**
	 * Constructor que recibe como parametro el id de la solicitud y booleano.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param ab_sol1 de ab sol 1
	 */
	public SolicitudAsociada(String as_idSolicitud, boolean ab_sol1)
	{
		if(ab_sol1)
			is_idSolicitud1 = as_idSolicitud;
		else
			is_idSolicitud = as_idSolicitud;
	}

	/**
	 * Constructor que recibe como parametro el id de la solicitud y booleano de solicitud y booleano de vinculado.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param ab_sol1 de ab sol 1
	 * @param as_indVinculado de ab ind vinculado
	 */
	public SolicitudAsociada(String as_idSolicitud, boolean ab_sol1, String as_indVinculado)
	{
		if(ab_sol1)
			is_idSolicitud1 = as_idSolicitud;
		else
			is_idSolicitud = as_idSolicitud;

		is_indVinculado                           = as_indVinculado;
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
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitud1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud1(String as_s)
	{
		is_idSolicitud1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud1()
	{
		return is_idSolicitud1;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IndVinculado.
	 *
	 * @param as_b de ab b
	 */
	public void setIndVinculado(String as_b)
	{
		is_indVinculado = as_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndVinculado()
	{
		return is_indVinculado;
	}

	/**
	 * Modifica el valor de NirSolicitud1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNirSolicitud1(String as_s)
	{
		is_nirSolicitud1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir solicitud 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNirSolicitud1()
	{
		return is_nirSolicitud1;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de NombreSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreSubProceso(String as_s)
	{
		is_nombreSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreSubProceso()
	{
		return is_nombreSubProceso;
	}
}
