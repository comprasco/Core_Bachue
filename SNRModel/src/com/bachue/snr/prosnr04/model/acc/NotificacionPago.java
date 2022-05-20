package com.bachue.snr.prosnr04.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de notificacion pago.
 *
 * @author Julian Vaca
 */
public class NotificacionPago extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID                 = -1633676326673971856L;
	
	/** Propiedad id fecha bancaria. */
	private Date              id_fechaBancaria;
	
	/** Propiedad id fecha liquidacion. */
	private Date              id_fechaLiquidacion;
	
	/** Propiedad id fecha recibo caja. */
	private Date              id_fechaReciboCaja;
	
	/** Propiedad id fecha transaccion. */
	private Date              id_fechaTransaccion;
	
	/** Propiedad id fecha vencimiento. */
	private Date              id_fechaVencimiento;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id notificacion pago recibida. */
	private String            is_idNotificacionPagoRecibida;
	
	/** Propiedad is id punto recaudo tipo recaudo. */
	private String            is_idPuntoRecaudoTipoRecaudo;
	
	/** Propiedad is id sucursal canal origen servicio. */
	private String            is_idSucursalCanalOrigenServicio;
	
	/** Propiedad is numero referencia SNR. */
	private String            is_numeroReferenciaSNR;
	
	/** Propiedad is numero transaccion recaudador. */
	private String            is_numeroTransaccionRecaudador;
	
	/** Propiedad id monto transaccion. */
	private double            id_montoTransaccion;

	/**
	 * Constructor por defecto.
	 */
	public NotificacionPago()
	{
	}

	/**
	 * Constructor que recibe como parametro el numero de referencia.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 */
	public NotificacionPago(String as_numeroReferencia)
	{
		setNumeroReferenciaSNR(as_numeroReferencia);
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado                                         = as_s;
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
	 * Modifica el valor de FechaBancaria.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaBancaria(Date ad_d)
	{
		this.id_fechaBancaria = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha bancaria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaBancaria()
	{
		return id_fechaBancaria;
	}

	/**
	 * Modifica el valor de FechaLiquidacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaLiquidacion(Date ad_d)
	{
		this.id_fechaLiquidacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaLiquidacion()
	{
		return id_fechaLiquidacion;
	}

	/**
	 * Modifica el valor de FechaReciboCaja.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaReciboCaja(Date ad_d)
	{
		this.id_fechaReciboCaja = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha recibo caja.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaReciboCaja()
	{
		return id_fechaReciboCaja;
	}

	/**
	 * Modifica el valor de FechaTransaccion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaTransaccion(Date ad_d)
	{
		this.id_fechaTransaccion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha transaccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaTransaccion()
	{
		return id_fechaTransaccion;
	}

	/**
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		this.id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de IdNotificacionPagoRecibida.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNotificacionPagoRecibida(String as_s)
	{
		this.is_idNotificacionPagoRecibida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id notificacion pago recibida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNotificacionPagoRecibida()
	{
		return is_idNotificacionPagoRecibida;
	}

	/**
	 * Modifica el valor de IdPuntoRecaudoTipoRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPuntoRecaudoTipoRecaudo(String as_s)
	{
		this.is_idPuntoRecaudoTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id punto recaudo tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPuntoRecaudoTipoRecaudo()
	{
		return is_idPuntoRecaudoTipoRecaudo;
	}

	/**
	 * Modifica el valor de IdSucursalCanalOrigenServicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSucursalCanalOrigenServicio(String as_s)
	{
		this.is_idSucursalCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sucursal canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSucursalCanalOrigenServicio()
	{
		return is_idSucursalCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de MontoTransaccion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setMontoTransaccion(double ad_d)
	{
		this.id_montoTransaccion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor monto transaccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public double getMontoTransaccion()
	{
		return id_montoTransaccion;
	}

	/**
	 * Modifica el valor de NumeroReferenciaSNR.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReferenciaSNR(String as_s)
	{
		this.is_numeroReferenciaSNR = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero referencia SNR.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReferenciaSNR()
	{
		return is_numeroReferenciaSNR;
	}

	/**
	 * Modifica el valor de NumeroTransaccionRecaudador.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroTransaccionRecaudador(String as_s)
	{
		this.is_numeroTransaccionRecaudador = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero transaccion recaudador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroTransaccionRecaudador()
	{
		return is_numeroTransaccionRecaudador;
	}
}
