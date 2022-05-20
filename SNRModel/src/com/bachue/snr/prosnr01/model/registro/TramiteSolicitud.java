package com.bachue.snr.prosnr01.model.registro;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades TramiteSolicitud.
 *
 * @author Julian Vaca
 */
public class TramiteSolicitud extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8399938714628927176L;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is nombre sub proceso. */
	private String is_nombreSubProceso;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/**
	 * Instancia un nuevo objeto tramite solicitud.
	 */
	public TramiteSolicitud()
	{
	}

	/**
	 * Modifica el valor de comentario.
	 *
	 * @param as_s asigna el valor a la propiedad comentario
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna el valor de comentario.
	 *
	 * @return el valor de comentario
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de nombre proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre proceso
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna el valor de nombre proceso.
	 *
	 * @return el valor de nombre proceso
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de nombre sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre sub proceso
	 */
	public void setNombreSubProceso(String as_s)
	{
		is_nombreSubProceso = as_s;
	}

	/**
	 * Retorna el valor de nombre sub proceso.
	 *
	 * @return el valor de nombre sub proceso
	 */
	public String getNombreSubProceso()
	{
		return is_nombreSubProceso;
	}

	/**
	 * Modifica el valor de numero documento.
	 *
	 * @param as_s asigna el valor a la propiedad numero documento
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de numero documento.
	 *
	 * @return el valor de numero documento
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
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
