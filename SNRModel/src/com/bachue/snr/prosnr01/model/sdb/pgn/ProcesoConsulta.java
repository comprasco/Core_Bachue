package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Proceso Consulta.
 *
 * @author Sebastian Sanchez
 */
public class ProcesoConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6534351794117066420L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id proceso consulta. */
	private String is_idProcesoConsulta;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is sentencia sql. */
	private String is_sentenciaSql;

	/**
	 * Constructor por defecto.
	 */
	public ProcesoConsulta()
	{
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
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoConsulta()
	{
		return is_idProcesoConsulta;
	}

	/**
	 * Modifica el valor de IdProcesoConsulta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoConsulta(String as_s)
	{
		is_idProcesoConsulta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor sentencia sql.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSentenciaSql()
	{
		return is_sentenciaSql;
	}

	/**
	 * Modifica el valor de SentenciaSql.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSentenciaSql(String as_s)
	{
		is_sentenciaSql = as_s;
	}
}
