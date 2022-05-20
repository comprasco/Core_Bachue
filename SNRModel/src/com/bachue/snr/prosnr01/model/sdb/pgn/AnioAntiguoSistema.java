package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo de Anio antiguo sistema.
 *
 * @author hcastaneda
 *
 */
public class AnioAntiguoSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -4027447960901888914L;
	
	/** Propiedad is id anio antiguo sistema. */
	private String            is_idAnioAntiguoSistema;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public AnioAntiguoSistema()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public AnioAntiguoSistema(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de IdAnioAntiguoSistema.
	 *
	 * @param idAnioAntiguoSistema asigna el valor a la propiedad
	 */
	public void setIdAnioAntiguoSistema(String idAnioAntiguoSistema)
	{
		this.is_idAnioAntiguoSistema                  = idAnioAntiguoSistema;
	}

	/**
	 * Retorna Objeto o variable de valor id anio antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnioAntiguoSistema()
	{
		return is_idAnioAntiguoSistema;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
