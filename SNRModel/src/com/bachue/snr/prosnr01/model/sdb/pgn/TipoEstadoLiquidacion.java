package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_TIPO_AREA.
 *
 * @author Carlos Calderón
 */
public class TipoEstadoLiquidacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                = -7598798528983838525L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion estado liquidacion. */
	private String            is_descripcionEstadoLiquidacion;
	
	/** Propiedad is id tipo estado liquidacion. */
	private String            is_idTipoEstadoLiquidacion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                        = as_s;
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
	 * Modifica el valor de DescripcionEstadoLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionEstadoLiquidacion(String as_s)
	{
		is_descripcionEstadoLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion estado liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionEstadoLiquidacion()
	{
		return is_descripcionEstadoLiquidacion;
	}

	/**
	 * Modifica el valor de IdTipoEstadoLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoEstadoLiquidacion(String as_s)
	{
		is_idTipoEstadoLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo estado liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoEstadoLiquidacion()
	{
		return is_idTipoEstadoLiquidacion;
	}
}
