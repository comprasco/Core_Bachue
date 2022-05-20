package com.bachue.snr.prosnr01.model.cuentaCupos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase para el manejo de presentación de información en la consulta de cuenta cupos.
 *
 * @author Manuel Blanco
 */
public class DetalleConsultaCuentaCupos implements Serializable
{
	/** Propiedad Constant serialVersionUID. */
	private static final long serialVersionUID = -5102455875560982797L;

	/** Propiedad ibd valor consumo. */
	private BigDecimal ibd_valorConsumo;

	/** Propiedad ibd valor recarga. */
	private BigDecimal ibd_valorRecarga;

	/** Propiedad ibd valor saldo. */
	private BigDecimal ibd_valorSaldo;

	/** Propiedad ildt fecha movimiento. */
	private Date id_fechaMovimiento;

	/** Propiedad is estado. */
	private String is_estado;

	/** Propiedad is id nota credito. */
	private String is_idNotaCredito;

	/** Propiedad is recibo caja. */
	private String is_reciboCaja;

	/** Propiedad is valor consumo txt. */
	private String is_valorConsumoTxt;

	/** Propiedad is valor recarga txt. */
	private String is_valorRecargaTxt;

	/**
	 * Obtiene el valor de id nota credito.
	 *
	 * @return el valor de id nota credito
	 */
	public String getIdNotaCredito()
	{
		return is_idNotaCredito;
	}

	/**
	 * Cambia el valor de id nota credito.
	 *
	 * @param as_s el nuevo valor de id nota credito
	 */
	public void setIdNotaCredito(String as_s)
	{
		is_idNotaCredito = as_s;
	}

	/**
	 * Obtiene el valor de fecha movimiento.
	 *
	 * @return el valor de fecha movimiento
	 */
	public Date getFechaMovimiento()
	{
		return id_fechaMovimiento;
	}

	/**
	 * Cambia el valor de fecha movimiento.
	 *
	 * @param ad_d el nuevo valor de fecha movimiento
	 */
	public void setFechaMovimiento(Date ad_d)
	{
		id_fechaMovimiento = ad_d;
	}

	/**
	 * Obtiene el valor de valor consumo.
	 *
	 * @return el valor de valor consumo
	 */
	public BigDecimal getValorConsumo()
	{
		return ibd_valorConsumo;
	}

	/**
	 * Cambia el valor de valor consumo.
	 *
	 * @param abd_bd el nuevo valor de valor consumo
	 */
	public void setValorConsumo(BigDecimal abd_bd)
	{
		ibd_valorConsumo = abd_bd;
	}

	/**
	 * Obtiene el valor de valor recarga.
	 *
	 * @return el valor de valor recarga
	 */
	public BigDecimal getValorRecarga()
	{
		return ibd_valorRecarga;
	}

	/**
	 * Cambia el valor de valor recarga.
	 *
	 * @param abd_bd el nuevo valor de valor recarga
	 */
	public void setValorRecarga(BigDecimal abd_bd)
	{
		ibd_valorRecarga = abd_bd;
	}

	/**
	 * Obtiene el valor de valor saldo.
	 *
	 * @return el valor de valor saldo
	 */
	public BigDecimal getValorSaldo()
	{
		return ibd_valorSaldo;
	}

	/**
	 * Cambia el valor de valor saldo.
	 *
	 * @param abd_bd el nuevo valor de valor saldo
	 */
	public void setValorSaldo(BigDecimal abd_bd)
	{
		ibd_valorSaldo = abd_bd;
	}

	/**
	 * Obtiene el valor de recibo caja.
	 *
	 * @return el valor de recibo caja
	 */
	public String getReciboCaja()
	{
		return is_reciboCaja;
	}

	/**
	 * Cambia el valor de recibo caja.
	 *
	 * @param as_s el nuevo valor de recibo caja
	 */
	public void setReciboCaja(String as_s)
	{
		is_reciboCaja = as_s;
	}

	/**
	 * Obtiene el valor de valor recarga txt.
	 *
	 * @return el valor de valor recarga txt
	 */
	public String getValorRecargaTxt()
	{
		return is_valorRecargaTxt;
	}

	/**
	 * Cambia el valor de valor recarga txt.
	 *
	 * @param as_s el nuevo valor de valor recarga txt
	 */
	public void setValorRecargaTxt(String as_s)
	{
		is_valorRecargaTxt = as_s;
	}

	/**
	 * Obtiene el valor de valor consumo txt.
	 *
	 * @return el valor de valor consumo txt
	 */
	public String getValorConsumoTxt()
	{
		return is_valorConsumoTxt;
	}

	/**
	 * Cambia el valor de valor consumo txt.
	 *
	 * @param as_s el nuevo valor de valor consumo txt
	 */
	public void setValorConsumoTxt(String as_s)
	{
		is_valorConsumoTxt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s de as s
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}
}
