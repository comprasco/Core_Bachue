package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades ConvenioCirculoRegistral.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 28/05/2020
 */
public class ConvenioCirculoRegistral extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 141592929109579734L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id convenio circulo registral. */
	private String is_idConvenioCirculoRegistral;

	/** Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/** Propiedad is numero convenio. */
	private String is_numeroConvenio;

	/**
	 * Retorna Objeto o variable de valor id convenio circulo registral.
	 *
	 * @return Retorna el valor de la propiedad idConvenioCirculoRegistral
	 */
	public String getIdConvenioCirculoRegistral()
	{
		return is_idConvenioCirculoRegistral;
	}

	/**
	 * Modifica el valor de IdConvenioCirculoRegistral.
	 *
	 * @param as_s de as s
	 */
	public void setIdConvenioCirculoRegistral(String as_s)
	{
		is_idConvenioCirculoRegistral = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad externa.
	 *
	 * @return Retorna el valor de la propiedad idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de IdEntidadExterna.
	 *
	 * @param as_s de as s
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero convenio.
	 *
	 * @return Retorna el valor de la propiedad numeroConvenio
	 */
	public String getNumeroConvenio()
	{
		return is_numeroConvenio;
	}

	/**
	 * Modifica el valor de NumeroConvenio.
	 *
	 * @param as_s de as s
	 */
	public void setNumeroConvenio(String as_s)
	{
		is_numeroConvenio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
