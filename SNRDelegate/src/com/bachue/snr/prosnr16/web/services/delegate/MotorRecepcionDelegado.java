package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.MotorRecepcionRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MotorRecepcionDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class MotorRecepcionDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1543666899613349006L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.motorRecepcion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorRecepcionDelegado.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** Propiedad ime remote. */
	private MotorRecepcionRemote imr_remote;

	/**
	 * Instancia un nuevo objeto motor envio delegado.
	 */
	public MotorRecepcionDelegado()
	{
		imr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotorRecepcionRemote getRemote()
	    throws B2BException
	{
		if(imr_remote == null)
		{
			try
			{
				imr_remote = (MotorRecepcionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imr_remote = null;
			}
		}

		return imr_remote;
	}
}
