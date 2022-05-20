package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 *
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CAMPOS_CERTIFICADO.
 * @author ssanchez
 *
 */
public class CamposCertificado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6969833900958487530L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is campo. */
	private String is_campo;

	/** Propiedad is plantilla. */
	private String is_plantilla;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de Campo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCampo(String as_s)
	{
		is_campo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCampo()
	{
		return is_campo;
	}

	/**
	 * Modifica el valor de Plantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPlantilla(String as_s)
	{
		is_plantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor plantilla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPlantilla()
	{
		return is_plantilla;
	}
}
