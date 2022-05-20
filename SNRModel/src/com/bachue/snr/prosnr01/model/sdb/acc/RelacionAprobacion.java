package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_RELACION_APROBACION.
 *
 * @author Julian Vaca
 */
public class RelacionAprobacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -4852051361864728349L;
	
	/** Propiedad id fecha relacion. */
	private Date              id_fechaRelacion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id relacion aprobacion. */
	private String            is_idRelacionAprobacion;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is nir. */
	private String            is_nir;
	
	/** Propiedad is numero relacion. */
	private String            is_numeroRelacion;
	
	/** Propiedad is tipo calificacion. */
	private String            is_tipoCalificacion;

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado                                     = as_s;
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
	 * Modifica el valor de FechaRelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFechaRelacion(Date as_s)
	{
		id_fechaRelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRelacion()
	{
		return id_fechaRelacion;
	}

	/**
	 * Modifica el valor de IdRelacionAprobacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRelacionAprobacion(String as_s)
	{
		is_idRelacionAprobacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id relacion aprobacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRelacionAprobacion()
	{
		return is_idRelacionAprobacion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NumeroRelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroRelacion(String as_s)
	{
		is_numeroRelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroRelacion()
	{
		return is_numeroRelacion;
	}

	/**
	 * Modifica el valor de TipoCalificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoCalificacion(String as_s)
	{
		is_tipoCalificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo calificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoCalificacion()
	{
		return is_tipoCalificacion;
	}
}
