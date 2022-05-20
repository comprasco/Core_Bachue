package com.bachue.snr.prosnr01.model.sdb.col;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Logica de modelo Etapa siendo una abstracción de la tabla SDB_PGN_ETAPA en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class InteresadoDocumentoTipo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -3192848744608720080L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id documento tipo. */
	private String            is_idDocumentoTipo;
	
	/** Propiedad is ilicode. */
	private String            is_ilicode;

	/**
	 * Instancia un nuevo objeto interesado documento tipo.
	 */
	public InteresadoDocumentoTipo()
	{
	}

	/**
	 * Instancia un nuevo objeto interesado documento tipo.
	 *
	 * @param as_s de as s
	 */
	public InteresadoDocumentoTipo(String as_s)
	{
		setIdDocumentoTipo(as_s);
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
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		this.is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de Ilicode.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIlicode(String as_s)
	{
		this.is_ilicode = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ilicode.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIlicode()
	{
		return is_ilicode;
	}
}
