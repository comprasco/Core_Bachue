package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades TipoOperacion.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 14/05/2020
 */
public class TipoOperacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2089931266448242087L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id tipo operacion. */
	private String is_idTipoOperacion;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public TipoOperacion()
	{
	}

	/**
	 * Constructor con argumento de informacion de la operacion.
	 *
	 * @param as_idTipoOperacion correspondiente al tipo de operacion
	 */
	public TipoOperacion(String as_idTipoOperacion)
	{
		setNombre(as_idTipoOperacion);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de IdTipoOperacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoOperacion(String as_s)
	{
		is_idTipoOperacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo operacion.
	 *
	 * @return Retorna el valor de la propiedad idTipoOperacion
	 */
	public String getIdTipoOperacion()
	{
		return is_idTipoOperacion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s de as s
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return Retorna el valor de la propiedad nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
