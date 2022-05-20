package com.bachue.snr.prosnr02.model.workflow;

import com.b2bsg.common.util.StringUtils;



/**
 * Clase que contiene todos las propiedades CompuertaExclusiva.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class CompuertaExclusiva extends Compuerta
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1964049406532702818L;
	
	/** Propiedad is flujo defecto. */
	private String            is_flujoDefecto;

	/**
	 * Instancia un nuevo objeto compuerta exclusiva.
	 */
	public CompuertaExclusiva()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto compuerta exclusiva.
	 *
	 * @param as_id de as id
	 */
	public CompuertaExclusiva(String as_id)
	{
		super(as_id);

		setExclusiva(true);
		setTipo(TIPO_COMPUERTA_EXCLUSIVA);
	}

	/**
	 * Modifica el valor de FlujoDefecto.
	 *
	 * @param as_s de as s
	 */
	public void setFlujoDefecto(String as_s)
	{
		is_flujoDefecto                        = StringUtils.getString(as_s);
	}

	/**
	 * Retorna Objeto o variable de valor flujo defecto.
	 *
	 * @return el valor de flujo defecto
	 */
	public String getFlujoDefecto()
	{
		return is_flujoDefecto;
	}
}
