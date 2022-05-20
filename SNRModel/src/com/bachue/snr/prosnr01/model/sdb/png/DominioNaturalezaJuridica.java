package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Logica de modelo Dominio Naturaleza Juridica  siendo una abstracción de la tabla SDB_PGN_DEPARTAMENTO en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class DominioNaturalezaJuridica extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = 375964663440844278L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id dominio nat jur. */
	private String            is_idDominioNatJur;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Instancia un nuevo objeto dominio naturaleza juridica.
	 */
	public DominioNaturalezaJuridica()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                           = as_s;
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
	 * Modifica el valor de IdDominioNatJur.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDominioNatJur(String as_s)
	{
		this.is_idDominioNatJur = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dominio nat jur.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDominioNatJur()
	{
		return is_idDominioNatJur;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
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
