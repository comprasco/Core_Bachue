package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_PERSONA.
 *
 * @author nguaneme
 */
public class TipoPersona extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2392163614569698625L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo persona. */
	private String            is_idTipoPersona;

	/**
	 * Constructor por defecto.
	 */
	public TipoPersona()
	{
	}

	/**
	 * Constructor con argumento de id persona.
	 *
	 * @param as_idTipoPersona de as id tipo persona
	 */
	public TipoPersona(String as_idTipoPersona)
	{
		setIdTipoPersona(as_idTipoPersona);
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion() {
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s) {
		this.is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPersona() {
		return is_idTipoPersona;
	}

	/**
	 * Modifica el valor de IdTipoPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoPersona(String as_s) {
		this.is_idTipoPersona = as_s;
	}

	
}
