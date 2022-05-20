package com.bachue.snr.prosnr04.model.npa;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos usados en los tipos de servicio.
 * @author hcastaneda
 *
 */
public class TipoServicio extends DatosLiquidacion implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID      = 466555788515395269L;
	
	/** Propiedad is id tipo servicio notificacion pago. */
	private String                               is_idTipoServicioNotificacionPago;
	
	/**
	 * Obtener id tipo servicio notificacion pago.
	 *
	 * @return el valor de string
	 */
	public String getIdTipoServicioNotificacionPago() {
		return is_idTipoServicioNotificacionPago;
	}

	/**
	 * Modifica el valor de Id tipo servicio notificacion pago.
	 *
	 * @param as_s Objeto o Variable de tipo String que asigna un valor a la propiedad IdTipoServicioNotificacionPago
	 */
	public void setIdTipoServicioNotificacionPago(String as_s) {
		this.is_idTipoServicioNotificacionPago = as_s;
	}

	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id sub proceso. */
	private String            is_idSubProceso;
	
	/** Propiedad id valor total. */
	private double            id_valorTotal;
	
	/** Propiedad id valor unitario. */
	private double            id_valorUnitario;
	
	/** Propiedad il cantidad solicitada. */
	private long              il_cantidadSolicitada;

	/**
	 * Modifica el valor de CantidadSolicitada.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setCantidadSolicitada(long al_l)
	{
		this.il_cantidadSolicitada                  = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad solicitada.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getCantidadSolicitada()
	{
		return il_cantidadSolicitada;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		this.is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		this.is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de ValorTotal.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorTotal(double ad_d)
	{
		this.id_valorTotal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor total.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getValorTotal()
	{
		return id_valorTotal;
	}

	/**
	 * Modifica el valor de ValorUnitario.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setValorUnitario(double ad_d)
	{
		this.id_valorUnitario = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor unitario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getValorUnitario()
	{
		return id_valorUnitario;
	}
}
