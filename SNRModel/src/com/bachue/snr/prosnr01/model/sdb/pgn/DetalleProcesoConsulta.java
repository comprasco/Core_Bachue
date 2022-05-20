package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Detalle Proceso Consulta.
 *
 * @author Sebastian Sanchez
 */
public class DetalleProcesoConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 45432953290807084L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id proceso consulta. */
	private String is_idProcesoConsulta;

	/** Propiedad is id tipo criterio busqueda. */
	private String is_idTipoCriterioBusqueda;

	/**
	 * Constructor por defecto.
	 */
	public DetalleProcesoConsulta()
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
	 * Retorna Objeto o variable de valor id tipo criterio busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoCriterioBusqueda()
	{
		return is_idTipoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de IdTipoCriterioBusqueda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoCriterioBusqueda(String as_s)
	{
		is_idTipoCriterioBusqueda = as_s;
	}
}
