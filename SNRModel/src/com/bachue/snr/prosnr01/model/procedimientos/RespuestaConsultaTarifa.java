package com.bachue.snr.prosnr01.model.procedimientos;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase Modelo de respuesta Proc Consulta Tarifa.
 *
 * @author Julian Vaca
 */
public class RespuestaConsultaTarifa implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7684052420154349238L;

	/** Propiedad ibd valor producto. */
	private BigDecimal ibd_valorProducto;

	/** Propiedad ii retorno. */
	private Integer ii_retorno;

	/** Propiedad is direccion predio. */
	private String is_direccionPredio;

	/** Propiedad is estado predio. */
	private String is_estadoPredio;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/**
	 * Instancia un nuevo objeto respuesta consulta tarifa.
	 */
	public RespuestaConsultaTarifa()
	{
	}

	/**
	 * Instancia un nuevo objeto respuesta consulta tarifa.
	 *
	 * @param abd_valorProducto de abd valor producto
	 * @param as_direccionPredio de as direccion predio
	 * @param as_estadoPredio de as estado predio
	 * @param ai_retorno de ai retorno
	 * @param as_mensaje de as mensaje
	 */
	public RespuestaConsultaTarifa(
	    BigDecimal abd_valorProducto, String as_direccionPredio, String as_estadoPredio, Integer ai_retorno,
	    String as_mensaje
	)
	{
		setValorProducto(abd_valorProducto);
		setDireccionPredio(as_direccionPredio);
		setEstadoPredio(as_estadoPredio);
		setRetorno(ai_retorno);
		setMensaje(as_mensaje);
	}

	/**
	 * Modifica el valor de direccion predio.
	 *
	 * @param as_s asigna el valor a la propiedad direccion predio
	 */
	public void setDireccionPredio(String as_s)
	{
		is_direccionPredio = as_s;
	}

	/**
	 * Retorna el valor de direccion predio.
	 *
	 * @return el valor de direccion predio
	 */
	public String getDireccionPredio()
	{
		return is_direccionPredio;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param as_s asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de mensaje.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna el valor de mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de retorno.
	 *
	 * @param ai_i asigna el valor a la propiedad retorno
	 */
	public void setRetorno(Integer ai_i)
	{
		ii_retorno = ai_i;
	}

	/**
	 * Retorna el valor de retorno.
	 *
	 * @return el valor de retorno
	 */
	public Integer getRetorno()
	{
		return ii_retorno;
	}

	/**
	 * Modifica el valor de valor producto.
	 *
	 * @param abd_bd asigna el valor a la propiedad valor producto
	 */
	public void setValorProducto(BigDecimal abd_bd)
	{
		ibd_valorProducto = abd_bd;
	}

	/**
	 * Retorna el valor de valor producto.
	 *
	 * @return el valor de valor producto
	 */
	public BigDecimal getValorProducto()
	{
		return ibd_valorProducto;
	}
}
