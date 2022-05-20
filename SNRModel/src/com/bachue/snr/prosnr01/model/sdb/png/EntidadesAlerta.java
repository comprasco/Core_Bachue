package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades EntidadesAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 3/04/2020
 */
public class EntidadesAlerta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2533545363575275351L;

	/** Propiedad is email. */
	private String is_email;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is numero documento id. */
	private String is_numeroDocumentoId;

	/** Propiedad is tipo documento id. */
	private String is_tipoDocumentoId;

	/** Propiedad il id entidad. */
	private long il_idEntidad;

	/**
	 * Instancia un nuevo objeto entidades alerta.
	 */
	public EntidadesAlerta()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id entidad.
	 *
	 * @return Retorna el valor de la propiedad idEntidad
	 */
	public long getIdEntidad()
	{
		return il_idEntidad;
	}

	/**
	 * Modifica el valor de IdEntidad.
	 *
	 * @param al_l de al l
	 */
	public void setIdEntidad(long al_l)
	{
		il_idEntidad = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento id.
	 *
	 * @return Retorna el valor de la propiedad tipoDocumentoId
	 */
	public String getTipoDocumentoId()
	{
		return is_tipoDocumentoId;
	}

	/**
	 * Modifica el valor de TipoDocumentoId.
	 *
	 * @param as_s de as s
	 */
	public void setTipoDocumentoId(String as_s)
	{
		is_tipoDocumentoId = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento id.
	 *
	 * @return Retorna el valor de la propiedad numeroDocumentoId
	 */
	public String getNumeroDocumentoId()
	{
		return is_numeroDocumentoId;
	}

	/**
	 * Modifica el valor de NumeroDocumentoId.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroDocumentoId(String as_s)
	{
		is_numeroDocumentoId = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return Retorna el valor de la propiedad nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s de as s
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor email.
	 *
	 * @return Retorna el valor de la propiedad email
	 */
	public String getEmail()
	{
		return is_email;
	}

	/**
	 * Modifica el valor de Email.
	 *
	 * @param as_s de as s
	 */
	public void setEmail(String as_s)
	{
		is_email = as_s;
	}
}
