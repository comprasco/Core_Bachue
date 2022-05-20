package com.bachue.snr.prosnr01.web.delegate.notificarCatastro;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.notificarCatastro.NotificarCatastroRemote;


/**
 * Clase que contiene todos las propiedades NotificarCatastroDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class NotificarCatastroDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -999149188325407161L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.notificarCatastro";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarCatastroDelegado.class);

	/** Propiedad inc remote. */
	private NotificarCatastroRemote inc_remote;

	/**
	 * Instancia un nuevo objeto notificar catastro delegado.
	 */
	public NotificarCatastroDelegado()
	{
		inc_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NotificarCatastroRemote getRemote()
	    throws B2BException
	{
		if(inc_remote == null)
		{
			try
			{
				inc_remote = (NotificarCatastroRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				inc_remote = null;
			}
		}

		return inc_remote;
	}
}
