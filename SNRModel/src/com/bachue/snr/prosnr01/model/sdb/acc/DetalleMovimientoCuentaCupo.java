package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase para la abstracción de la tabla SDB_ACC_DETALLE_MOVIMIENTO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class DetalleMovimientoCuentaCupo extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -7427335896571670023L;

	/**  Propiedad ibd_bdvalor. */
	private BigDecimal ibd_bdvalor;

	/**  Propiedad id_fecha. */
	private Date id_fecha;

	/**  Propiedad is_idDetalleMovimiento. */
	private String is_idDetalleMovimiento;

	/**  Propiedad is_idMovimiento. */
	private String is_idMovimiento;

	/**  Propiedad is_idNotaCredito. */
	private String is_idNotaCredito;

	/**
	 * Retorna el valor de is_idDetalleMovimiento.
	 *
	 * @return el valor de is_idDetalleMovimiento
	 */
	public String getIdDetalleMovimiento()
	{
		return is_idDetalleMovimiento;
	}

	/**
	 * Modifica el valor de is_idDetalleMovimiento.
	 *
	 * @param as_s asigna el valor a la propiedad is_idDetalleMovimiento
	 */
	public void setIdDetalleMovimiento(String as_s)
	{
		is_idDetalleMovimiento = as_s;
	}

	/**
	 * Retorna el valor de is_idMovimiento.
	 *
	 * @return el valor de is_idMovimiento
	 */
	public String getIdMovimiento()
	{
		return is_idMovimiento;
	}

	/**
	 * Modifica el valor de is_idMovimiento.
	 *
	 * @param as_s asigna el valor a la propiedad is_idMovimiento
	 */
	public void setIdMovimiento(String as_s)
	{
		is_idMovimiento = as_s;
	}

	/**
	 * Retorna el valor de is_idNotaCredito.
	 *
	 * @return el valor de is_idNotaCredito
	 */
	public String getIdNotaCredito()
	{
		return is_idNotaCredito;
	}

	/**
	 * Modifica el valor de is_idNotaCredito.
	 *
	 * @param as_s asigna el valor a la propiedad is_idNotaCredito
	 */
	public void setIdNotaCredito(String as_s)
	{
		is_idNotaCredito = as_s;
	}

	/**
	 * Retorna el valor de ibd_bdvalor.
	 *
	 * @return el valor de ibd_bdvalor
	 */
	public BigDecimal getValor()
	{
		return ibd_bdvalor;
	}

	/**
	 * Modifica el valor de ibd_bdvalor.
	 *
	 * @param abd_bd de abd bd
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_bdvalor = abd_bd;
	}

	/**
	 * Retorna el valor de id_fecha.
	 *
	 * @return el valor de id_fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de id_fecha.
	 *
	 * @param ad_d asigna el valor a la propiedad id_fecha
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}
}
