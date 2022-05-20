package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_LIBRO_ANT_SISTEMA.
 *
 * @author jpatino
 */
public class LibroAntiguoSistema extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4747645916802542399L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad il id libro antiguo sistema. */
	private long il_idLibroAntiguoSistema;

	/**
	 * Constructor por defecto.
	 */
	public LibroAntiguoSistema()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public LibroAntiguoSistema(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
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
	 * Modifica el valor de IdLibroAntiguoSistema.
	 *
	 * @param idLibroAntiguoSistema asigna el valor a la propiedad
	 */
	public void setIdLibroAntiguoSistema(long idLibroAntiguoSistema)
	{
		this.il_idLibroAntiguoSistema = idLibroAntiguoSistema;
	}

	/**
	 * Retorna Objeto o variable de valor id libro antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdLibroAntiguoSistema()
	{
		return il_idLibroAntiguoSistema;
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
