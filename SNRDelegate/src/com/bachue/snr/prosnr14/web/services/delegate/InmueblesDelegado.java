package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.InmueblesRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades InmueblesDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class InmueblesDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5614123635574885410L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.inmuebles";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(InmueblesDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad iir remote. */
	private InmueblesRemote iir_remote;

	/**
	 * Instancia un nuevo objeto inmuebles delegado.
	 */
	public InmueblesDelegado()
	{
		iir_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public InmueblesRemote getRemote()
	    throws B2BException
	{
		if(iir_remote == null)
		{
			try
			{
				iir_remote = (InmueblesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                        .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iir_remote = null;
			}
		}

		return iir_remote;
	}
}
