package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de LetraEje.
 *
 * @author Carlos Calderón
 */
public class LetraEje extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2883652820769078177L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id letra. */
	private String            is_idLetra;
	
	/** Propiedad is letra. */
	private String            is_letra;

	/**
	 * Constructor por defecto.
	 */
	public LetraEje()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id letra.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLetra()
	{
		return is_idLetra;
	}

	/**
	 * Modifica el valor de IdLetra.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdLetra(String as_s)
	{
		is_idLetra                             = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor letra.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLetra()
	{
		return is_letra;
	}

	/**
	 * Modifica el valor de Letra.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLetra(String as_s)
	{
		is_letra = as_s;
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
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
