package com.bachue.snr.prosnr01.model.consulta.solicitud;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades Solicitud.
 *
 * @author Julian Vaca
 */
public class Solicitud extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1564345138731059054L;

	/** Propiedad icp listado persona. */
	private Collection<Persona> icp_listadoPersona;

	/**
	 * Modifica el valor de listado persona.
	 *
	 * @param acp_cp asigna el valor a la propiedad listado persona
	 */
	public void setListadoPersona(Collection<Persona> acp_cp)
	{
		icp_listadoPersona = acp_cp;
	}

	/**
	 * Retorna el valor de listado persona.
	 *
	 * @return el valor de listado persona
	 */
	public Collection<Persona> getListadoPersona()
	{
		return icp_listadoPersona;
	}
}
