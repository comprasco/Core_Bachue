package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene los atributos de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO.
 *
 * @author Hcastaneda
 */
public class CausalRechazoRecurso extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3319190199635082011L;

	/**  Propiedad is_activo. */
	private String is_activo;

	/**  Propiedad is_idCausalRechazoRecurso. */
	private String is_idCausalRechazoRecurso;

	/**  Propiedad is_nombre. */
	private String is_nombre;

	/**  Propiedad ib_seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setActivo(String as_s)
	{
		is_activo                              = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdCausalRechazoRecurso(String as_s)
	{
		is_idCausalRechazoRecurso = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getIdCausalRechazoRecurso()
	{
		return is_idCausalRechazoRecurso;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b Argumento que modifica el valor de la propiedad.
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
