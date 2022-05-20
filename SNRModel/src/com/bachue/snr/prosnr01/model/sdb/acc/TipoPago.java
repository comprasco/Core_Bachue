package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TIPO_PAGO.
 *
 * @author Julian Vaca
 */
public class TipoPago extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long    serialVersionUID = 1889163101793696710L;
	
	/** Propiedad ictp info all. */
	private Collection<TipoPago> ictp_infoAll;
	
	/** Propiedad is descripcion. */
	private String               is_descripcion;
	
	/** Propiedad is id tipo pago. */
	private String               is_idTipoPago;
	
	/** Propiedad is ip. */
	private String               is_ip;
	
	/** Propiedad ib accion. */
	private boolean              ib_accion;

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAccion(boolean ab_b)
	{
		ib_accion                                 = ab_b;
	}

	/**
	 * Valida la propiedad accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAccion()
	{
		return ib_accion;
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
	 * Modifica el valor de IdTipoPago.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPago(String as_s)
	{
		is_idTipoPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPago()
	{
		return is_idTipoPago;
	}

	/**
	 * Modifica el valor de InfoAll.
	 *
	 * @param infoAll asigna el valor a la propiedad
	 */
	public void setInfoAll(Collection<TipoPago> infoAll)
	{
		ictp_infoAll = infoAll;
	}

	/**
	 * Retorna Objeto o variable de valor info all.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoPago> getInfoAll()
	{
		return ictp_infoAll;
	}

	/**
	 * Modifica el valor de Ip.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIp(String as_s)
	{
		is_ip = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ip.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIp()
	{
		return is_ip;
	}
}
