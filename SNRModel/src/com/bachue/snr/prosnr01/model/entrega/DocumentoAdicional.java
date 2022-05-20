package com.bachue.snr.prosnr01.model.entrega;

import java.io.Serializable;



/**
 * Modelo utilizado para el manejo de documentos adicionales en la etapa de entrega.
 *
 * @author Manuel Blanco
 */
public class DocumentoAdicional implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1831873418229025254L;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad iba bytes documento. */
	private byte[] iba_bytesDocumento;

	/**
	 * Modifica el valor de bytes documento.
	 *
	 * @param aba_ba asigna el valor a la propiedad bytes documento
	 */
	public void setBytesDocumento(byte[] aba_ba)
	{
		iba_bytesDocumento = aba_ba;
	}

	/**
	 * Retorna el valor de bytes documento.
	 *
	 * @return el valor de bytes documento
	 */
	public byte[] getBytesDocumento()
	{
		return iba_bytesDocumento;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}
}
