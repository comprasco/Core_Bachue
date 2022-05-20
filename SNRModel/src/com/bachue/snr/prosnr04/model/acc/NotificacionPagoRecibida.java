package com.bachue.snr.prosnr04.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de notificacion pago recibida.
 *
 * @author Julian Vaca
 */
public class NotificacionPagoRecibida extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID               = -8569402194579523920L;
	
	/** Propiedad id fecha bancaria. */
	private Date              id_fechaBancaria;
	
	/** Propiedad id fecha transaccion. */
	private Date              id_fechaTransaccion;
	
	/** Propiedad is codigo entidad recaudadora. */
	private String            is_codigoEntidadRecaudadora;
	
	/** Propiedad is codigo mensaje. */
	private String            is_codigoMensaje;
	
	/** Propiedad is codigo punto recaudo entidad. */
	private String            is_codigoPuntoRecaudoEntidad;
	
	/** Propiedad is codigo tipo recaudo. */
	private String            is_codigoTipoRecaudo;
	
	/** Propiedad is codigo transaccion recaudador. */
	private String            is_codigoTransaccionRecaudador;
	
	/** Propiedad is id notificacion pago recibida. */
	private String            is_idNotificacionPagoRecibida;
	
	/** Propiedad is numero referencia bachue. */
	private String            is_numeroReferenciaBachue;
	
	/** Propiedad id monto transaccion. */
	private double            id_montoTransaccion;

	/**
	 * Constructor por defecto.
	 */
	public NotificacionPagoRecibida()
	{
	}

	/**
	 * Constructor con un argumento de numero de referencia.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 */
	public NotificacionPagoRecibida(String as_numeroReferencia)
	{
		setNumeroReferenciaBachue(as_numeroReferencia);
	}

	/**
	 * Modifica el valor de CodigoEntidadRecaudadora.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoEntidadRecaudadora(String as_s)
	{
		this.is_codigoEntidadRecaudadora                     = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo entidad recaudadora.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoEntidadRecaudadora()
	{
		return is_codigoEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de CodigoMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoMensaje(String as_s)
	{
		this.is_codigoMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoMensaje()
	{
		return is_codigoMensaje;
	}

	/**
	 * Modifica el valor de CodigoPuntoRecaudoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoPuntoRecaudoEntidad(String as_s)
	{
		this.is_codigoPuntoRecaudoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo punto recaudo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoPuntoRecaudoEntidad()
	{
		return is_codigoPuntoRecaudoEntidad;
	}

	/**
	 * Modifica el valor de CodigoTipoRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoTipoRecaudo(String as_s)
	{
		this.is_codigoTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoTipoRecaudo()
	{
		return is_codigoTipoRecaudo;
	}

	/**
	 * Modifica el valor de CodigoTransaccionRecaudador.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoTransaccionRecaudador(String as_s)
	{
		this.is_codigoTransaccionRecaudador = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo transaccion recaudador.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoTransaccionRecaudador()
	{
		return is_codigoTransaccionRecaudador;
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
	 * Modifica el valor de NumeroReferenciaBachue.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReferenciaBachue(String as_s)
	{
		this.is_numeroReferenciaBachue = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero referencia bachue.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReferenciaBachue()
	{
		return is_numeroReferenciaBachue;
	}
}
