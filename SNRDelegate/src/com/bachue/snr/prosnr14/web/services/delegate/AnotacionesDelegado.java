package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.AnotacionesRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades AnotacionesDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class AnotacionesDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2857187038685760231L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.anotaciones";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AnotacionesDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad iar remote. */
	private AnotacionesRemote iar_remote;

	/**
	 * Instancia un nuevo objeto anotaciones delegado.
	 */
	public AnotacionesDelegado()
	{
		iar_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionesRemote getRemote()
	    throws B2BException
	{
		if(iar_remote == null)
		{
			try
			{
				iar_remote = (AnotacionesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                          .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iar_remote = null;
			}
		}

		return iar_remote;
	}
}
