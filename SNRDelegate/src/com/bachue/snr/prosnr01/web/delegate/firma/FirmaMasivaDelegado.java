package com.bachue.snr.prosnr01.web.delegate.firma;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.firma.FirmaMasivaRemote;


/**
 * Clase para el acceso a la interfaz remota de firma masiva.
 *
 * @author Julian vaca
 *
 */
public class FirmaMasivaDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8851617766296986409L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.firmaMasiva";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(FirmaMasivaDelegado.class);

	/** The ifm remote. */
	private FirmaMasivaRemote ifm_remote;

	/**
	 * Instancia un nuevo firma masiva delegado.
	 */
	public FirmaMasivaDelegado()
	{
		ifm_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public FirmaMasivaRemote getRemote()
	    throws B2BException
	{
		if(ifm_remote == null)
		{
			try
			{
				ifm_remote = (FirmaMasivaRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
