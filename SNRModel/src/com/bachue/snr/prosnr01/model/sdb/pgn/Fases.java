package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Logica de modelo Etapa siendo una abstracción de la tabla SDB_PGN_FASES en la base de datos.
 *
 * @author Gabriel Arias
 */
public class Fases extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2554711942440572802L;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad il id fase. */
	private long              il_idFase;

	/**
	 * Instancia un nuevo objeto fases.
	 */
	public Fases()
	{
	}

	/**
	 * Constructor que recibe como parametro el idFase.
	 *
	 * @param il_l de il l
	 */
	public Fases(long il_l)
	{
		this.il_idFase                         = il_l;
	}

	/**
	 * Constructor que recibe como paramétro la descripción y el nombre.
	 *
	 * @param as_descripcion asigna el valor a la propiedad descripcion
	 * @param as_nombre asgina el valor a la propiedad nombre
	 */
	public Fases(String as_descripcion, String as_nombre)
	{
		is_descripcion     = as_descripcion;
		is_nombre          = as_nombre;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param descripcion asigna el valor a la propiedad
	 */
	public void setDescripcion(String descripcion)
	{
		this.is_descripcion = descripcion;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		this.is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de IdFase.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdFase(long al_l)
	{
		this.il_idFase = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id fase.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdFase()
	{
		return il_idFase;
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
