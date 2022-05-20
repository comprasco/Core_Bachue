package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_RESULTADO_CONSULTA.
 *
 * @author Julian Vaca
 */
public class ResultadoConsulta extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8219784316230177656L;
	
	/** Propiedad is hash consulta. */
	private String            is_hashConsulta;
	
	/** Propiedad ib resultado blob. */
	private byte[]            ib_resultadoBlob;
	
	/** Propiedad il id instancia. */
	private long              il_idInstancia;

	/**
	 * Constructor por defecto.
	 */
	public ResultadoConsulta()
	{
	}

	/**
	 * Modifica el valor de HashConsulta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHashConsulta(String as_s)
	{
		this.is_hashConsulta                   = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor hash consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHashConsulta()
	{
		return is_hashConsulta;
	}

	/**
	 * Modifica el valor de IdInstancia.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdInstancia(long al_l)
	{
		this.il_idInstancia = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id instancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdInstancia()
	{
		return il_idInstancia;
	}

	/**
	 * Modifica el valor de ResultadoBlob.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setResultadoBlob(byte[] ab_b)
	{
		this.ib_resultadoBlob = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor resultado blob.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public byte[] getResultadoBlob()
	{
		return ib_resultadoBlob;
	}
}
