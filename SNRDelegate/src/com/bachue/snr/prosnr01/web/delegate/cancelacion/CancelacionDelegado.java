package com.bachue.snr.prosnr01.web.delegate.cancelacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.cancelacion.CancelacionRemote;


/**
 * Clase que contiene todos las propiedades CancelacionDelegado.
 *
 * @author Julian Vaca
 * Fecha de Creacion: 12/09/2019
 */
public class CancelacionDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2678337184227186698L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.cancelacion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CancelacionDelegado.class);

	/** The ifm remote. */
	private CancelacionRemote ifm_remote;

	/**
	 * Instancia un nuevo cancelacion delegado.
	 */
	public CancelacionDelegado()
	{
		ifm_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public CancelacionRemote getRemote()
	    throws B2BException
	{
		if(ifm_remote == null)
		{
			try
			{
				ifm_remote = (CancelacionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                          .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ifm_remote = null;
			}
		}

		return ifm_remote;
	}
}
