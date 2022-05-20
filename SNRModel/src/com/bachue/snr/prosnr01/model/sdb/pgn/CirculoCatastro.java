package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Logica de modelo CirculoCatastro siendo una abstracción de la tabla SDB_PGN_CIRCULO_CATASTRO en la base de datos.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/08/2020
 */
public class CirculoCatastro extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5557599110277046395L;

	/** Propiedad activo. */
	private String is_activo;

	/** Propiedad id catastro. */
	private String is_idCatastro;

	/** Propiedad id circulo. */
	private String is_idCirculo;

	/** Propiedad id circulo catastro. */
	private String is_idCirculoCatastro;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo Modifica el valor de la propiedad activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdCatastro.
	 *
	 * @param as_idCatastro Modifica el valor de la propiedad idCatastro
	 */
	public void setIdCatastro(String as_idCatastro)
	{
		is_idCatastro = as_idCatastro;
	}

	/**
	 * Retorna Objeto o variable de valor id catastro.
	 *
	 * @return Retorna el valor de la propiedad idCatastro
	 */
	public String getIdCatastro()
	{
		return is_idCatastro;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_idCirculo Modifica el valor de la propiedad idCirculo
	 */
	public void setIdCirculo(String as_idCirculo)
	{
		is_idCirculo = as_idCirculo;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculoCatastro.
	 *
	 * @param as_idCirculoCatastro Modifica el valor de la propiedad idCirculoCatastro
	 */
	public void setIdCirculoCatastro(String as_idCirculoCatastro)
	{
		is_idCirculoCatastro = as_idCirculoCatastro;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo catastro.
	 *
	 * @return Retorna el valor de la propiedad idCirculoCatastro
	 */
	public String getIdCirculoCatastro()
	{
		return is_idCirculoCatastro;
	}
}
