package com.bachue.snr.prosnr21.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;


/**
 * Referencia remota al EJB de Conciliaciones
 *
 * @author  Edgar Prieto
 * Fecha de Creacion: 20/07/2020
 */
public class ConciliacionesDelegate implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4408483704434656361L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.conciliaciones";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConciliacionesDelegate.class);

	/** Referencia al objeto de negocio de conciliaciones */
	private ConciliacionesRemote icr_remote;

	/**
	 * Instancia un nuevo objeto reference delegate.
	 */
	public ConciliacionesDelegate()
	{
		icr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConciliacionesRemote getRemote()
	    throws B2BException
	{
		if(icr_remote == null)
		{
			try
			{
				icr_remote = (ConciliacionesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                             .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icr_remote = null;
			}
		}

		return icr_remote;
	}
}
