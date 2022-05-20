package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase para la abstracción de la tabla SDB_ACC_NOTA_CREDITO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class NotaCreditoCuentaCupo extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 5123788605780565784L;

	/**  Propiedad ibd_saldo. */
	private BigDecimal ibd_saldo;

	/**  Propiedad ibd_valorRecarga. */
	private BigDecimal ibd_valorRecarga;

	/**  Propiedad id_fecha. */
	private Date id_fecha;

	/**  Propiedad is_codigo. */
	private String is_codigo;

	/**  Propiedad is_idCuentaCupo. */
	private String is_idCuentaCupo;

	/**  Propiedad is_idNotaCredito. */
	private String is_idNotaCredito;

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdNotaCredito()
	{
		return is_idNotaCredito;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s nuevo valor para la propiedad
	 */
	public void setIdNotaCredito(String as_s)
	{
		is_idNotaCredito = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s nuevo valor para la propiedad
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s nuevo valor para la propiedad
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public BigDecimal getValorRecarga()
	{
		return ibd_valorRecarga;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param abd_bd nuevo valor para la propiedad
	 */
	public void setValorRecarga(BigDecimal abd_bd)
	{
		ibd_valorRecarga = abd_bd;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public BigDecimal getSaldo()
	{
		return ibd_saldo;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param abd_bd nuevo valor para la propiedad
	 */
	public void setSaldo(BigDecimal abd_bd)
	{
		ibd_saldo = abd_bd;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param ad_d nuevo valor para la propiedad
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}
}
