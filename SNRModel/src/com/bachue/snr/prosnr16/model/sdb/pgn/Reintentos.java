package com.bachue.snr.prosnr16.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de SDB_PGN_REINTENTOS.
 *
 * @author Sebastian Sanchez
 */
public class Reintentos extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3344377314744771759L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id canal. */
	private String is_idCanal;

	/** Propiedad is numero maximo intentos. */
	private String is_numeroMaximoIntentos;

	/**
	 * Constructor por defecto.
	 */
	public Reintentos()
	{
	}

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
	 * Modifica el valor de IdCanal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCanal(String as_s)
	{
		is_idCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanal()
	{
		return is_idCanal;
	}

	/**
	 * Retorna Objeto o variable de valor numero maximo intentos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroMaximoIntentos()
	{
		return is_numeroMaximoIntentos;
	}

	/**
	 * Modifica el valor de NumeroMaximoIntentos.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroMaximoIntentos(String as_s)
	{
		is_numeroMaximoIntentos = as_s;
	}
}
