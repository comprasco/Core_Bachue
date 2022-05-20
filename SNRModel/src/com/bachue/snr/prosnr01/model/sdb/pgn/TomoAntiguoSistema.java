package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo de tomo antiguo sistema.
 *
 * @author hcastaneda
 *
 */
public class TomoAntiguoSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -501218640524761011L;
	
	/** Propiedad is id tomo antiguo sistema. */
	private String            is_idTomoAntiguoSistema;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TomoAntiguoSistema()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public TomoAntiguoSistema(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de IdTomoAntiguoSistema.
	 *
	 * @param idTomoAntiguoSistema asigna el valor a la propiedad
	 */
	public void setIdTomoAntiguoSistema(String idTomoAntiguoSistema)
	{
		this.is_idTomoAntiguoSistema                  = idTomoAntiguoSistema;
	}

	/**
	 * Retorna Objeto o variable de valor id tomo antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTomoAntiguoSistema()
	{
		return is_idTomoAntiguoSistema;
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
