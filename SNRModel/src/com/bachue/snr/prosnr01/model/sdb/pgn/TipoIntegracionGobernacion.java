package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Logica de modelo Tipo Integración Gobernación siendo una abstracción de la tabla SDB_PGN_TIPO_INTEGRACION_GOBERNACION en la base de datos.
 *
 * @author Luis Chacón
 */
public class TipoIntegracionGobernacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = 7810203929635315009L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id tipo integracion. */
	private String            is_idTipoIntegracion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                             = as_s;
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
		this.is_descripcion = as_s;
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
	 * Modifica el valor de IdTipoIntegracion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoIntegracion(String as_s)
	{
		this.is_idTipoIntegracion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo integracion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoIntegracion()
	{
		return is_idTipoIntegracion;
	}
}
