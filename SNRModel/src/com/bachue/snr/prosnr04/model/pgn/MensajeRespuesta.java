package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_MENSAJE_RESPUESTA.
 *
 * @author Carlos Calderón
 */
public class MensajeRespuesta extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4201712916806188732L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is codigo mensaje. */
	private String            is_codigoMensaje;
	
	/** Propiedad is texto mensaje. */
	private String            is_textoMensaje;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                         = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de CodigoMensaje.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de TextoMensaje.
	 *
	 * @param as_s de as s
	 */
	public void setTextoMensaje(String as_s)
	{
		this.is_textoMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor texto mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTextoMensaje()
	{
		return is_textoMensaje;
	}
}
