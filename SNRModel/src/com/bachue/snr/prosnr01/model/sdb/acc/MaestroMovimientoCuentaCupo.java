package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;



/**
 * Clase para la abstracción de la tabla SDB_ACC_MAESTRO_MOVIMIENTO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 */
public class MaestroMovimientoCuentaCupo extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -3637549631941092369L;

	/**  Propiedad ibd_valor. */
	private BigDecimal ibd_valor;

	/**  Propiedad id_fecha. */
	private Date id_fecha;

	/**  Propiedad ii_intentoNotificacionPago. */
	private Integer ii_intentoNotificacionPago;

	/**  Propiedad is_estado. */
	private String is_estado;

	/**  Propiedad is_idCuentaCupo. */
	private String is_idCuentaCupo;

	/**  Propiedad is_idMovimiento. */
	private String is_idMovimiento;

	/**  Propiedad is_idUsuario. */
	private String is_idUsuario;

	/**  Propiedad is_referenciaPago. */
	private String is_referenciaPago;

	/**  Propiedad is_tipoMovimiento. */
	private String is_tipoMovimiento;

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdMovimiento()
	{
		return is_idMovimiento;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s el nuevo valor de la propiedad
	 */
	public void setIdMovimiento(String as_s)
	{
		is_idMovimiento = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getReferenciaPago()
	{
		return is_referenciaPago;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s el nuevo valor de la propiedad
	 */
	public void setReferenciaPago(String as_s)
	{
		is_referenciaPago = as_s;
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
	 * @param as_s el nuevo valor de la propiedad
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
	public BigDecimal getValor()
	{
		return ibd_valor;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param abd_bd el nuevo valor de la propiedad
	 */
	public void setValor(BigDecimal abd_bd)
	{
		ibd_valor = abd_bd;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getTipoMovimiento()
	{
		return is_tipoMovimiento;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s el nuevo valor de la propiedad
	 */
	public void setTipoMovimiento(String as_s)
	{
		is_tipoMovimiento = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s el nuevo valor de la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
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
	 * @param ad_d el nuevo valor de la propiedad
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param as_s el nuevo valor de la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Obtiene el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public Integer getIntentoNotificacionPago()
	{
		return ii_intentoNotificacionPago;
	}

	/**
	 * Cambia el valor de la propiedad.
	 *
	 * @param ai_i el nuevo valor de la propiedad
	 */
	public void setIntentoNotificacionPago(Integer ai_i)
	{
		ii_intentoNotificacionPago = ai_i;
	}
}
