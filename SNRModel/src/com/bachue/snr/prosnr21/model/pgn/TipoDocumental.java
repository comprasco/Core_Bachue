package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_DOCUMENTAL.
 *
 * @author Kevin Pulido
 */
public class TipoDocumental extends Auditoria implements Serializable
{
	/**  Propiedad serialVersionUID. */
	private static final long serialVersionUID = -1866378781013479116L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id expediente. */
	private String is_idExpediente;

	/** Propiedad is id tipo documental. */
	private String is_idTipoDocumental;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo de activo
	 */
	public void setActivo(String activo)
	{
		is_activo = activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdExpediente.
	 *
	 * @param idExpediente de id expediente
	 */
	public void setIdExpediente(String idExpediente)
	{
		is_idExpediente = idExpediente;
	}

	/**
	 * Retorna Objeto o variable de valor id expediente.
	 *
	 * @return el valor de id expediente
	 */
	public String getIdExpediente()
	{
		return is_idExpediente;
	}

	/**
	 * Modifica el valor de IdTipoDocumental.
	 *
	 * @param idTipoDocumental de id tipo documental
	 */
	public void setIdTipoDocumental(String idTipoDocumental)
	{
		is_idTipoDocumental = idTipoDocumental;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documental.
	 *
	 * @return el valor de id tipo documental
	 */
	public String getIdTipoDocumental()
	{
		return is_idTipoDocumental;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre de nombre
	 */
	public void setNombre(String nombre)
	{
		is_nombre = nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
