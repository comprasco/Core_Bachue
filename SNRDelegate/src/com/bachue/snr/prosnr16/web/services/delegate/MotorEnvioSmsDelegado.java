package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.MotorEnvioSmsRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MotorEnvioElectronicoDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class MotorEnvioSmsDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6484663899156738938L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.motorEnvioSms";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorEnvioSmsDelegado.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** Propiedad ime remote. */
	private MotorEnvioSmsRemote imes_remote;

	/**
	 * Instancia un nuevo objeto motor envio delegado.
	 */
	public MotorEnvioSmsDelegado()
	{
		imes_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotorEnvioSmsRemote getRemote()
	    throws B2BException
	{
		if(imes_remote == null)
		{
			try
			{
				imes_remote = (MotorEnvioSmsRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imes_remote = null;
			}
		}

		return imes_remote;
	}
}
