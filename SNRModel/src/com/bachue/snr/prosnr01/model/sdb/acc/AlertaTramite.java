package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_ALERTA_TRAMITE.
 *
 * @author Jorge Patiño
 */
public class AlertaTramite extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID    = 8300635419155487785L;
	
	/** Propiedad ibd id alerta tramite. */
	private BigDecimal        ibd_idAlertaTramite;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                 = as_s;
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
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
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
	 * Modifica el valor de IdAlertaTramite.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdAlertaTramite(BigDecimal abd_bd)
	{
		ibd_idAlertaTramite = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id alerta tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdAlertaTramite()
	{
		return ibd_idAlertaTramite;
	}
}
