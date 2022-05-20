package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.time.LocalDateTime;



/**
 * Clase para la abstracción de la tabla SDB_ACC_PAGO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class PagoCuentaCupo extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3130212242682870063L;

	/** Propiedad ibd valor. */
	private BigDecimal ibd_valor;

	/**  Propiedad ildt_fechaPago. */
	private LocalDateTime ildt_fechaPago;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Propiedad is id movimiento. */
	private String is_idMovimiento;

	/** Propiedad is id pago cuenta cupo. */
	private String is_idPagoCuentaCupo;

	/**  Propiedad is_idUsuarioCuentaCupo. */
	private String is_idUsuarioCuentaCupo;

	/** Propiedad is referencia pago. */
	private String is_referenciaPago;

	/**
	 * Retorna el valor de ildt_fechaPago.
	 *
	 * @return el valor de ildt_fechaPago
	 */
	public LocalDateTime getFechaPago()
	{
		return ildt_fechaPago;
	}

	/**
	 * Modifica el valor de ildt_fechaPago.
	 *
	 * @param aldt_ldt asigna el valor a la propiedad ildt_fechaPago
	 */
	public void setFechaPago(LocalDateTime aldt_ldt)
	{
		ildt_fechaPago = aldt_ldt;
	}

	/**
	 * Retorna el valor de is_idUsuarioCuentaCupo.
	 *
	 * @return el valor de is_idUsuarioCuentaCupo
	 */
	public String getIdUsuarioCuentaCupo()
	{
		return is_idUsuarioCuentaCupo;
	}

	/**
	 * Modifica el valor de is_idUsuarioCuentaCupo.
	 *
	 * @param as_s asigna el valor a la propiedad is_idUsuarioCuentaCupo
	 */
	public void setIdUsuarioCuentaCupo(String as_s)
	{
		is_idUsuarioCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de id pago cuenta cupo.
	 *
	 * @return el valor de id pago cuenta cupo
	 */
	public String getIdPagoCuentaCupo()
	{
		return is_idPagoCuentaCupo;
	}

	/**
	 * Cambia el valor de id pago cuenta cupo.
	 *
	 * @param as_s el nuevo valor de id pago cuenta cupo
	 */
	public void setIdPagoCuentaCupo(String as_s)
	{
		is_idPagoCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de id cuenta cupo.
	 *
	 * @param as_s el nuevo valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de referencia pago.
	 *
	 * @return el valor de referencia pago
	 */
	public String getReferenciaPago()
	{
		return is_referenciaPago;
	}

	/**
	 * Cambia el valor de referencia pago.
	 *
	 * @param as_s el nuevo valor de referencia pago
	 */
	public void setReferenciaPago(String as_s)
	{
		is_referenciaPago = as_s;
	}

	/**
	 * Obtiene el valor de valor.
	 *
	 * @return el valor de valor
	 */
	public BigDecimal getValor()
	{
		return ibd_valor;
	}

	/**
	 * Cambia el valor de valor.
	 *
	 * @param abd_bd el nuevo valor de valor
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_valor = abd_bd;
	}

	/**
	 * Obtiene el valor de estado.
	 *
	 * @return el valor de estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Cambia el valor de estado.
	 *
	 * @param as_s el nuevo valor de estado
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Obtiene el valor de id movimiento.
	 *
	 * @return el valor de id movimiento
	 */
	public String getIdMovimiento()
	{
		return is_idMovimiento;
	}

	/**
	 * Cambia el valor de id movimiento.
	 *
	 * @param as_s el nuevo valor de id movimiento
	 */
	public void setIdMovimiento(String as_s)
	{
		is_idMovimiento = as_s;
	}
}
