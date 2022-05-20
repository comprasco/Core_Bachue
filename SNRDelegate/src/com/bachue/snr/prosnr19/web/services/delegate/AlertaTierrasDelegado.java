package com.bachue.snr.prosnr19.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr19.ejb.session.stateless.alertaTierras.AlertaTierrasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades AlertaTierrasDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class AlertaTierrasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4495997689127673229L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.alertaTierras";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AlertaTierrasDelegado.class, ProyectosCommon.ALERTA_TIERRAS_19);

	/** Propiedad iatr remote. */
	private AlertaTierrasRemote iatr_remote;

	/**
	 * Instancia un nuevo objeto alerta tierras delegado.
	 */
	public AlertaTierrasDelegado()
	{
		iatr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTierrasRemote getRemote()
	    throws B2BException
	{
		if(iatr_remote == null)
		{
			try
			{
				iatr_remote = (AlertaTierrasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iatr_remote = null;
			}
		}

		return iatr_remote;
	}
}
