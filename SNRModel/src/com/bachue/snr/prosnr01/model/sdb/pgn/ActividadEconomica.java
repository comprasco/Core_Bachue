package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_ACTIVIDAD_ECONOMICA.
 * @author ssanchez
 *
 */
public class ActividadEconomica extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1031421290193742076L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id actividad economica. */
	private String is_idActividadEconomica;

	/** Propiedad is id division. */
	private String is_idDivision;

	/** Propiedad is id grupo. */
	private String is_idGrupo;

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
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
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
	 * Modifica el valor de IdGrupo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdGrupo(String as_s)
	{
		is_idGrupo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id grupo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGrupo()
	{
		return is_idGrupo;
	}

	/**
	 * Modifica el valor de IdActividadEconomica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActividadEconomica(String as_s)
	{
		is_idActividadEconomica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id actividad economica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActividadEconomica()
	{
		return is_idActividadEconomica;
	}

	/**
	 * Modifica el valor de IdDivision.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDivision(String as_s)
	{
		is_idDivision = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id division.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDivision()
	{
		return is_idDivision;
	}
}
