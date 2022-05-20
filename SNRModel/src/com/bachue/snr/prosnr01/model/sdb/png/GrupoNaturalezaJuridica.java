package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
 *
 * @author Carlos Calderón
 */
public class GrupoNaturalezaJuridica extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID             = 789841264998860829L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id grupo naturaleza juridica. */
	private String            is_idGrupoNaturalezaJuridica;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setActivo(String as_S)
	{
		this.is_activo                                     = as_S;
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
	 * Modifica el valor de IdGrupoNatJuridica.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setIdGrupoNatJuridica(String as_S)
	{
		this.is_idGrupoNaturalezaJuridica = as_S;
	}

	/**
	 * Retorna Objeto o variable de valor id grupo nat juridica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGrupoNatJuridica()
	{
		return is_idGrupoNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_S asigna el valor a la propiedad
	 */
	public void setNombre(String as_S)
	{
		this.is_nombre = as_S;
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
