package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene los atributos de la tabla SDB_PGN_DEPENDENCIA_SNR.
 *
 * @author Sebastian Sanchez
 */
public class DependenciaSNR extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1588524864490858424L;

	/**  Propiedad is_activo. */
	private String is_activo;

	/**  Propiedad is_idDependencia. */
	private String is_idDependencia;

	/**  Propiedad is_indVisitas. */
	private String is_indVisitas;

	/**  Propiedad is_nombreDependencia. */
	private String is_nombreDependencia;

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIndVisitas(String as_s)
	{
		is_indVisitas = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getIndVisitas()
	{
		return is_indVisitas;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombreDependencia(String as_s)
	{
		is_nombreDependencia = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Valor de la propiedad.
	 */
	public String getNombreDependencia()
	{
		return is_nombreDependencia;
	}
}
