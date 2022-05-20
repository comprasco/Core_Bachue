package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_PROCESO_AUTOMATICO.
 *
 * @author Julian Vaca
 */
public class ProcesoAutomatico extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -5523328945094776265L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id proceso automatico. */
	private String            is_idProcesoAutomatico;
	
	/** Propiedad is nombre proc almacenado. */
	private String            is_nombreProcAlmacenado;
	
	/** Propiedad is nombre proceso. */
	private String            is_nombreProceso;

	/**
	 * Instancia un nuevo objeto proceso automatico.
	 */
	public ProcesoAutomatico()
	{
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion                           = as_s;
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
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
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
	 * Modifica el valor de IdProcesoAutomatico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoAutomatico(String as_s)
	{
		this.is_idProcesoAutomatico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso automatico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoAutomatico()
	{
		return is_idProcesoAutomatico;
	}

	/**
	 * Modifica el valor de NombreProcAlmacenado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreProcAlmacenado(String as_s)
	{
		this.is_nombreProcAlmacenado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proc almacenado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreProcAlmacenado()
	{
		return is_nombreProcAlmacenado;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreProceso(String as_s)
	{
		this.is_nombreProceso = as_s;
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
}
