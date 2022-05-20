package com.bachue.snr.prosnr22.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr22.ejb.session.stateless.notificarDigitalizacionContent.NotificarDigitalizacionContentRemote;


/**
 * Clase que contiene todos las propiedades NotificarDigitalizacionContentDelegado.
 *
 * @author  hcastaneda
 */
public class NotificarDigitalizacionContentDelegado implements java.io.Serializable
{
	/**
	 * Constante serialVersionUID.
	 */
	private static final long serialVersionUID = -2867547601399136265L;

	/**
	 * Constante JNDI_NAME.
	 */
	private static final String JNDI_NAME = "jndi.notificarDigitalizacionContent";

	/**
	 * Constante clh_LOGGER.
	 */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarDigitalizacionContentDelegado.class);

	/**
	 * Propiedad ir remote.
	 */
	private NotificarDigitalizacionContentRemote indcr_remote;

	/**
	 * Instancia un nuevo objeto notificar digitalizacion content delegado.
	 */
	public NotificarDigitalizacionContentDelegado()
	{
		indcr_remote = null;
	}

	/**
	 * Método encargado de obtener el ejb client de NotificarDigitalizacionContentRemote
	 *
	 * @return el valor de notificar digitalizacion content remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NotificarDigitalizacionContentRemote getRemote()
	    throws B2BException
	{
		if(indcr_remote == null)
		{
			try
			{
				indcr_remote = (NotificarDigitalizacionContentRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				indcr_remote = null;
			}
		}

		return indcr_remote;
	}
}
