package com.bachue.snr.prosnr01.web.delegate.registro;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.registro.ConstanciaReproduccionRemote;


/**
 * Clase para el acceso a la interfaz remota de constancia reproduccion.
 *
 * @author Julian Vaca
 *
 */
public class ConstanciaReproduccionDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 614926233079380191L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.constanciaReproduccion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConstanciaReproduccionDelegado.class);

	/** Propiedad ifm remote. */
	private ConstanciaReproduccionRemote ifm_remote;

	/**
	 * Instancia un nuevo costancia reproduccion delegado.
	 */
	public ConstanciaReproduccionDelegado()
	{
		ifm_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public ConstanciaReproduccionRemote getRemote()
	    throws B2BException
	{
		if(ifm_remote == null)
		{
			try
			{
				ifm_remote = (ConstanciaReproduccionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
