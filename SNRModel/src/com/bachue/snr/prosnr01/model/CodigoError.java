package com.bachue.snr.prosnr01.model;

import java.io.Serializable;

import java.math.BigInteger;


/**
 * Clase para el manejo de codigos de error por referencia en servicios.
 *
 * @author Manuel Blanco
 */
public class CodigoError implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 8748225712175803120L;

	/**  Constante abi_codigoError. */
	private BigInteger abi_codigoError;

	/** Propiedad is mensaje informativo. */
	private String is_mensajeInformativo;

	/**
	 * Constructor por defecto, que da un valor invalido al codigo de error.
	 */
	public CodigoError()
	{
		abi_codigoError = new BigInteger("-1");
	}

	/**
	 * Retorna el valor de abi_codigoError.
	 *
	 * @return el valor de abi_codigoError
	 */
	public BigInteger getCodigoError()
	{
		return abi_codigoError;
	}

	/**
	 * Modifica el valor de abi_bi.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setCodigoError(BigInteger abi_bi)
	{
		abi_codigoError = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje informativo.
	 *
	 * @return Retorna el valor de la propiedad mensajeInformativo
	 */
	public String getMensajeInformativo()
	{
		return is_mensajeInformativo;
	}

	/**
	 * Modifica el valor de MensajeInformativo.
	 *
	 * @param as_s de as s
	 */
	public void setMensajeInformativo(String as_s)
	{
		is_mensajeInformativo = as_s;
	}
}
