package com.bachue.snr.prosnr21.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;


/**
 * Referencia remota al EJB de Parametros
 *
 * @author  Edgar Prieto
 * Fecha de Creacion: 19/07/2020
 */
public class ParameterDelegate implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 810748427685349884L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.parametros";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ParameterDelegate.class);

	/** Propiedad ir remote. */
	private ParameterRemote ipr_remote;

	/**
	 * Instancia un nuevo objeto reference delegate.
	 */
	public ParameterDelegate()
	{
		ipr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ParameterRemote getRemote()
	    throws B2BException
	{
		if(ipr_remote == null)
		{
			try
			{
				ipr_remote = (ParameterRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                        .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ipr_remote = null;
			}
		}

		return ipr_remote;
	}
}
