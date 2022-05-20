package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TRAMITE_VISITA.
 *
 * @author Bryan Márquez
 */
public class UbicacionSalvado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4332025251998399388L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id ubicacion guardado. */
	private String is_idUbicacionSalvado;

	/** Propiedad is nombre. */
	private String is_nombre;

	/**
	 * @param is_activo Modifica el valor de la propiedad is_activo
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param is_idUbicacionGuardado Modifica el valor de la propiedad is_idUbicacionGuardado
	 */
	public void setIdUbicacionSalvado(String as_ius)
	{
		is_idUbicacionSalvado = as_ius;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUbicacionGuardado
	 */
	public String getIdUbicacionSalvado()
	{
		return is_idUbicacionSalvado;
	}

	/**
	 * @param is_nombre Modifica el valor de la propiedad is_nombre
	 */
	public void setNombre(String as_n)
	{
		is_nombre = as_n;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
