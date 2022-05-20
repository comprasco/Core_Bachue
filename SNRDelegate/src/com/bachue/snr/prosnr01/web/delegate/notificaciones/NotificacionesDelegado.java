package com.bachue.snr.prosnr01.web.delegate.notificaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.notificaciones.NotificacionesRemote;


/**
 * Clase que contiene todos las propiedades NotificacionesDelegado.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/03/2020
 */
public class NotificacionesDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5925235588392008489L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.notificaciones";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificacionesDelegado.class);

	/** Propiedad inr remote. */
	private NotificacionesRemote inr_remote;

	/**
	 * Instancia un nuevo objeto notificaciones delegado.
	 */
	public NotificacionesDelegado()
	{
		inr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public NotificacionesRemote getRemote()
	    throws B2BException
	{
		if(inr_remote == null)
		{
			try
			{
				inr_remote = (NotificacionesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				inr_remote = null;
			}
		}

		return inr_remote;
	}
}
