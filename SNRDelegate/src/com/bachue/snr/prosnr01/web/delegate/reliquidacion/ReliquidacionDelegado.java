package com.bachue.snr.prosnr01.web.delegate.reliquidacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.reliquidacion.ReliquidacionRemote;


/**
 * Clase que contiene todos las propiedades ReliquidacionDelegado.
 *
 * @author Gabriel Arias
 * Fecha de Creacion: 14/12/2021
 */
public class ReliquidacionDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6492477821154239575L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.reliquidacion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReliquidacionDelegado.class);

	/** The ifm remote. */
	private ReliquidacionRemote irrm_remote;

	/**
	 * Instancia un nuevo cancelacion delegado.
	 */
	public ReliquidacionDelegado()
	{
		irrm_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public ReliquidacionRemote getRemote()
	    throws B2BException
	{
		if(irrm_remote == null)
		{
			try
			{
				irrm_remote = (ReliquidacionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				irrm_remote = null;
			}
		}

		return irrm_remote;
	}
}
