package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase que contiene todos las propiedades EntidadOrigen.
 *
 * @author Julian Vaca
 */
public class EntidadOrigen implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8670193438478093125L;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is entidad origen. */
	private String is_entidadOrigen;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/**
	 * Modifica el valor de documento.
	 *
	 * @param as_s asigna el valor a la propiedad documento
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public String getDocumento()
	{
		return is_documento;
	}

	/**
	 * Modifica el valor de entidad origen.
	 *
	 * @param as_s asigna el valor a la propiedad entidad origen
	 */
	public void setEntidadOrigen(String as_s)
	{
		is_entidadOrigen = as_s;
	}

	/**
	 * Retorna el valor de entidad origen.
	 *
	 * @return el valor de entidad origen
	 */
	public String getEntidadOrigen()
	{
		return is_entidadOrigen;
	}

	/**
	 * Modifica el valor de fecha documento.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha documento
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna el valor de fecha documento.
	 *
	 * @return el valor de fecha documento
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}
}
