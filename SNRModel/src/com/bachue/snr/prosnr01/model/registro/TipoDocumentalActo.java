package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades de la tabla SDB_PGN_TIPO_DOCUMENTAL_ACTO.
 *
 * @author Julian Vaca
 */
public class TipoDocumentalActo extends TipoDocumental implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8533010355907573162L;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo documental acto. */
	private long is_idTipoDocumentalActo;

	/**
	 * Modifica el valor de id tipo acto.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo acto
	 */
	public void setIdTipoActo(String as_s)
	{
		is_idTipoActo = as_s;
	}

	/**
	 * Retorna el valor de id tipo acto.
	 *
	 * @return el valor de id tipo acto
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de id tipo documental acto.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo documental acto
	 */
	public void setIdTipoDocumentalActo(long as_s)
	{
		is_idTipoDocumentalActo = as_s;
	}

	/**
	 * Retorna el valor de id tipo documental acto.
	 *
	 * @return el valor de id tipo documental acto
	 */
	public long getIdTipoDocumentalActo()
	{
		return is_idTipoDocumentalActo;
	}
}
