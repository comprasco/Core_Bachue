package com.bachue.snr.prosnr21.web.services.delegate;


import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.EnvioGestorDocumentalRemote;


/**
 * Clase que contiene todos las propiedades EnvioGestorDocumentalDelegado.
 *
 * @author Julian Vaca
 */
public class EnvioGestorDocumentalDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2110058378877841158L;

		/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.envioGestorDocumentalConciliaciones";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioGestorDocumentalDelegado.class);

	/** Propiedad iegdr remote. */
	private EnvioGestorDocumentalRemote iegdr_remote;
	

	/**
	 * Instancia un nuevo objeto envio gestor documental delegado.
	 */
	public EnvioGestorDocumentalDelegado()
	{
		iegdr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EnvioGestorDocumentalRemote getRemote()
	    throws B2BException
	{
		if(iegdr_remote == null)
		{
			try
			{
				iegdr_remote = (EnvioGestorDocumentalRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iegdr_remote = null;
			}
		}

		return iegdr_remote;
	}
}
