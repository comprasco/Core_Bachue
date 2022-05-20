package com.bachue.snr.prosnr20.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr20.ejb.session.stateless.cyn.NotificadorRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades NotificadorDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
public class NotificadorDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1602283252341208732L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.notificador";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificadorDelegado.class, ProyectosCommon.NOTIFICADOR_20);

	/** Propiedad inr remote. */
	private NotificadorRemote inr_remote;

	/**
	 * Instancia un nuevo objeto notificador delegado.
	 */
	public NotificadorDelegado()
	{
		inr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NotificadorRemote getRemote()
	    throws B2BException
	{
		if(inr_remote == null)
		{
			try
			{
				inr_remote = (NotificadorRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
