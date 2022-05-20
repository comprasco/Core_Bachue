package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades EliminarAnotacion.
 *
 * @author Julian Vaca
 */
public class EliminarAnotacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1892252772062499572L;

	/** Propiedad icl id anotaciones parciales. */
	private Collection<Long> icl_idAnotacionesParciales;

	/** Propiedad icrc anotaciones. */
	private Collection<RegistroCalificacion> icrc_anotaciones;

	/** Propiedad irpc registro parcial calificacion. */
	private RegistroParcialCalificacion irpc_registroParcialCalificacion;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/**
	 * Modifica el valor de anotaciones.
	 *
	 * @param acrc_crc asigna el valor a la propiedad anotaciones
	 */
	public void setAnotaciones(Collection<RegistroCalificacion> acrc_crc)
	{
		icrc_anotaciones = acrc_crc;
	}

	/**
	 * Retorna el valor de anotaciones.
	 *
	 * @return el valor de anotaciones
	 */
	public Collection<RegistroCalificacion> getAnotaciones()
	{
		return icrc_anotaciones;
	}

	/**
	 * Modifica el valor de id anotacion predio.
	 *
	 * @param idAnotacionPredio asigna el valor a la propiedad id anotacion predio
	 */
	public void setIdAnotacionPredio(String idAnotacionPredio)
	{
		this.is_idAnotacionPredio = idAnotacionPredio;
	}

	/**
	 * Retorna el valor de id anotacion predio.
	 *
	 * @return el valor de id anotacion predio
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de id anotaciones parciales.
	 *
	 * @param acl_cl asigna el valor a la propiedad id anotaciones parciales
	 */
	public void setIdAnotacionesParciales(Collection<Long> acl_cl)
	{
		icl_idAnotacionesParciales = acl_cl;
	}

	/**
	 * Retorna el valor de id anotaciones parciales.
	 *
	 * @return el valor de id anotaciones parciales
	 */
	public Collection<Long> getIdAnotacionesParciales()
	{
		return icl_idAnotacionesParciales;
	}

	/**
	 * Modifica el valor de registro parcial calificacion.
	 *
	 * @param registroParcialCalificacion asigna el valor a la propiedad registro parcial calificacion
	 */
	public void setRegistroParcialCalificacion(RegistroParcialCalificacion registroParcialCalificacion)
	{
		this.irpc_registroParcialCalificacion = registroParcialCalificacion;
	}

	/**
	 * Retorna el valor de registro parcial calificacion.
	 *
	 * @return el valor de registro parcial calificacion
	 */
	public RegistroParcialCalificacion getRegistroParcialCalificacion()
	{
		return irpc_registroParcialCalificacion;
	}
}
