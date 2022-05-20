package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo de TipoPartida antiguo sistema.
 *
 * @author hcastaneda
 *
 */
public class TipoPartidaAntiguoSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID               = 5990602099840628897L;
	
	/** Propiedad is id tipo partida antiguo sistema. */
	private String            is_idTipoPartidaAntiguoSistema;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TipoPartidaAntiguoSistema()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public TipoPartidaAntiguoSistema(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de IdTipoPartidaAntiguoSistema.
	 *
	 * @param idTipoPartidaAntiguoSistema asigna el valor a la propiedad
	 */
	public void setIdTipoPartidaAntiguoSistema(String idTipoPartidaAntiguoSistema)
	{
		is_idTipoPartidaAntiguoSistema                       = idTipoPartidaAntiguoSistema;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo partida antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoPartidaAntiguoSistema()
	{
		return is_idTipoPartidaAntiguoSistema;
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
