package com.bachue.snr.prosnr10.model.catastro;

import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades NotificarCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/06/2020
 */
public class NotificarCatastro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2995093778870412543L;

	/** Propiedad is cod catastro. */
	private String is_codCatastro;

	/** Propiedad is cod transaccion. */
	private String is_codTransaccion;

	/** Propiedad is fecha notificacion. */
	private String is_fechaNotificacion;

	/** Propiedad is operacion registro. */
	private String is_operacionRegistro;

	/** Propiedad itoa orips. */
	private TipoORP[] itoa_orips;

	/**
	 * Instancia un nuevo objeto notificar catastro.
	 */
	public NotificarCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto notificar catastro.
	 *
	 * @param as_codCatastro de as cod catastro
	 * @param as_codTransaccion de as cod transaccion
	 * @param as_operacionRegistro de as operacion registro
	 * @param as_fechaNotificacion de as fecha notificacion
	 * @param atoa_orips de atoa orips
	 */
	public NotificarCatastro(
	    String as_codCatastro, String as_codTransaccion, String as_operacionRegistro, String as_fechaNotificacion,
	    TipoORP[] atoa_orips
	)
	{
		is_codCatastro           = as_codCatastro;
		is_codTransaccion        = as_codTransaccion;
		is_operacionRegistro     = as_operacionRegistro;
		is_fechaNotificacion     = as_fechaNotificacion;
		itoa_orips               = atoa_orips;
	}

	/**
	 * Modifica el valor de CodCatastro.
	 *
	 * @param as_s de as s
	 */
	public void setCodCatastro(String as_s)
	{
		is_codCatastro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod catastro.
	 *
	 * @return Retorna el valor de la propiedad codCatastro
	 */
	public String getCodCatastro()
	{
		return is_codCatastro;
	}

	/**
	 * Modifica el valor de CodTransaccion.
	 *
	 * @param as_s de as s
	 */
	public void setCodTransaccion(String as_s)
	{
		is_codTransaccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod transaccion.
	 *
	 * @return Retorna el valor de la propiedad codTransaccion
	 */
	public String getCodTransaccion()
	{
		return is_codTransaccion;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param as_s de as s
	 */
	public void setFechaNotificacion(String as_s)
	{
		is_fechaNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return Retorna el valor de la propiedad fechaNotificacion
	 */
	public String getFechaNotificacion()
	{
		return is_fechaNotificacion;
	}

	/**
	 * Modifica el valor de OperacionRegistro.
	 *
	 * @param as_s de as s
	 */
	public void setOperacionRegistro(String as_s)
	{
		is_operacionRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor operacion registro.
	 *
	 * @return Retorna el valor de la propiedad operacionRegistro
	 */
	public String getOperacionRegistro()
	{
		return is_operacionRegistro;
	}

	/**
	 * Modifica el valor de Orips.
	 *
	 * @param atoa_toa de atoa toa
	 */
	public void setOrips(TipoORP[] atoa_toa)
	{
		itoa_orips = atoa_toa;
	}

	/**
	 * Retorna Objeto o variable de valor orips.
	 *
	 * @return Retorna el valor de la propiedad orips
	 */
	public TipoORP[] getOrips()
	{
		return itoa_orips;
	}
}
