package com.bachue.snr.prosnr06.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr06.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota de Consulta de trazabilidad
 * @author dbeltran
 *
 */
public class ConsultaTrazabilidadDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4991921038230657L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.serviceConsultaTrazabilidad";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaTrazabilidadDelegado.class);

	/** Propiedad icrt remote. */
	private ConsultaTrazabilidadRemote ictr_remote;

	/**
	 * Instancia un nuevo objeto consulta trazabilidad delegado.
	 */
	public ConsultaTrazabilidadDelegado()
	{
		ictr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaTrazabilidadRemote getRemote()
	    throws B2BException
	{
		if(ictr_remote == null)
		{
			try
			{
				ictr_remote = (ConsultaTrazabilidadRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                    .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ictr_remote = null;
			}
		}

		return ictr_remote;
	}
}
