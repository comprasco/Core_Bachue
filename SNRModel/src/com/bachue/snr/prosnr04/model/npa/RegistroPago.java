package com.bachue.snr.prosnr04.model.npa;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase abstraccion de la tabla SDB_ACC_REGISTRO_PAGO.
 *
 * @author Julian Vaca
 */
public class RegistroPago extends DatosLiquidacion implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID               = 2709941859172989765L;
	
	/** Propiedad id fecha bancaria. */
	private Date              id_fechaBancaria;
	
	/** Propiedad id fecha generacion recibo. */
	private Date              id_fechaGeneracionRecibo;
	
	/** Propiedad id fecha transaccion. */
	private Date              id_fechaTransaccion;
	
	/** Propiedad is codigo transaccion recaudador. */
	private String            is_codigoTransaccionRecaudador;
	
	/** Propiedad is estado generacion recibo caja. */
	private String            is_estadoGeneracionReciboCaja;
	
	/** Propiedad is estado pago. */
	private String            is_estadoPago;
	
	/** Propiedad is id entidad recaudo. */
	private String            is_idEntidadRecaudo;
	
	/** Propiedad is id liquidacion. */
	private String            is_idLiquidacion;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id punto recaudo entidad. */
	private String            is_idPuntoRecaudoEntidad;
	
	/** Propiedad is id registro pago. */
	private String            is_idRegistroPago;
	
	/** Propiedad is id sucursal recaudo. */
	private String            is_idSucursalRecaudo;
	
	/** Propiedad is id tipo canal. */
	private String            is_idTipoCanal;
	
	/** Propiedad is id tipo recaudo. */
	private String            is_idTipoRecaudo;
	
	/** Propiedad is nir. */
	private String            is_nir;
	
	/** Propiedad is numero cuenta recaudo. */
	private String            is_numeroCuentaRecaudo;
	
	/** Propiedad is numero recibo caja. */
	private String            is_numeroReciboCaja;
	
	/** Propiedad is numero referencia. */
	private String            is_numeroReferencia;
	
	/** Propiedad id monto transaccion. */
	private double            id_montoTransaccion;
	
	/** Propiedad ii consecutivo liquidacion. */
	private int               ii_consecutivoLiquidacion;

	/**
	 * Instancia un nuevo objeto registro pago.
	 */
	public RegistroPago()
	{
		super();
	}

	/**
	 * Instancia un nuevo objeto registro pago.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 */
	public RegistroPago(String as_numeroReferencia)
	{
		super(as_numeroReferencia);
	}

	/**
	 * Modifica el valor de CodigoTransaccionRecaudador.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoTransaccionRecaudador(String as_s)
	{
		is_codigoTransaccionRecaudador                       = as_s;
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
	 * Modifica el valor de EstadoGeneracionReciboCaja.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoGeneracionReciboCaja(String as_s)
	{
		is_estadoGeneracionReciboCaja = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado generacion recibo caja.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoGeneracionReciboCaja()
	{
		return is_estadoGeneracionReciboCaja;
	}

	/**
	 * Modifica el valor de FechaBancaria.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaBancaria(Date ad_d)
	{
		id_fechaBancaria = ad_d;
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
		id_fechaTransaccion = ad_d;
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
	 * Modifica el valor de IdEntidadRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdEntidadRecaudo(String as_s)
	{
		is_idEntidadRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdEntidadRecaudo()
	{
		return is_idEntidadRecaudo;
	}

	/**
	 * Modifica el valor de IdPuntoRecaudoEntidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPuntoRecaudoEntidad(String as_s)
	{
		is_idPuntoRecaudoEntidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id punto recaudo entidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPuntoRecaudoEntidad()
	{
		return is_idPuntoRecaudoEntidad;
	}

	/**
	 * Modifica el valor de IdTipoRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoRecaudo(String as_s)
	{
		is_idTipoRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoRecaudo()
	{
		return is_idTipoRecaudo;
	}

	/**
	 * Modifica el valor de MontoTransaccion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setMontoTransaccion(double ad_d)
	{
		id_montoTransaccion = ad_d;
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
	 * Modifica el valor de NumeroReciboCaja.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReciboCaja(String as_s)
	{
		is_numeroReciboCaja = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero recibo caja.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReciboCaja()
	{
		return is_numeroReciboCaja;
	}

	/**
	 * Retorna Objeto o variable de valor id registro pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdRegistroPago()
	{
		return is_idRegistroPago;
	}

	/**
	 * Modifica el valor de IdRegistroPago.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdRegistroPago(String as_s)
	{
		is_idRegistroPago = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero referencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * Modifica el valor de NumeroReferencia.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroReferencia(String as_s)
	{
		is_numeroReferencia = as_s;
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
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo canal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCanal()
	{
		return is_idTipoCanal;
	}

	/**
	 * Modifica el valor de IdTipoCanal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCanal(String as_s)
	{
		is_idTipoCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sucursal recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSucursalRecaudo()
	{
		return is_idSucursalRecaudo;
	}

	/**
	 * Modifica el valor de IdSucursalRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSucursalRecaudo(String as_s)
	{
		is_idSucursalRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero cuenta recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroCuentaRecaudo()
	{
		return is_numeroCuentaRecaudo;
	}

	/**
	 * Modifica el valor de NumeroCuentaRecaudo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroCuentaRecaudo(String as_s)
	{
		is_numeroCuentaRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha generacion recibo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaGeneracionRecibo()
	{
		return id_fechaGeneracionRecibo;
	}

	/**
	 * Modifica el valor de FechaGeneracionRecibo.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaGeneracionRecibo(Date ad_d)
	{
		id_fechaGeneracionRecibo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor id liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Modifica el valor de IdLiquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo liquidacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getConsecutivoLiquidacion()
	{
		return ii_consecutivoLiquidacion;
	}

	/**
	 * Modifica el valor de ConsecutivoLiquidacion.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setConsecutivoLiquidacion(int ai_i)
	{
		ii_consecutivoLiquidacion = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor estado pago.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoPago()
	{
		return is_estadoPago;
	}

	/**
	 * Modifica el valor de EstadoPago.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoPago(String as_s)
	{
		is_estadoPago = as_s;
	}
}
