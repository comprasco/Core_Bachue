package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.MotorEnvioElectronicoRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MotorEnvioElectronicoDelegado.
 *
 * @author  Juli�n David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class MotorEnvioElectronicoDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8599665837429942569L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.motorEnvioElectronico";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorEnvioElectronicoDelegado.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** Propiedad ime remote. */
	private MotorEnvioElectronicoRemote imee_remote;

	/**
	 * Instancia un nuevo objeto motor envio delegado.
	 */
	public MotorEnvioElectronicoDelegado()
	{
		imee_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotorEnvioElectronicoRemote getRemote()
	    throws B2BException
	{
		if(imee_remote == null)
		{
			try
			{
				imee_remote = (MotorEnvioElectronicoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imee_remote = null;
			}
		}

		return imee_remote;
	}
}
