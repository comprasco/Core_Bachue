package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;
import com.bachue.snr.prosnr01.ejb.session.stateless.realizarNotificacion.RealizarNotificacionRemote;


/**
 * Clase que contiene todos las propiedades RealizarNotificacionDelegado.
 *
 * @author  Manuel Blanco
 */
public class RealizarNotificacionDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3463691125632623966L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.realizarNotificacion";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RealizarNotificacionDelegado.class);

	/** Propiedad igr remote. */
	private RealizarNotificacionRemote igr_remote;

	/**
	 * Instancia un nuevo objeto notificacion delegado.
	 */
	public RealizarNotificacionDelegado()
	{
		igr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RealizarNotificacionRemote getRemote()
	    throws B2BException
	{
		if(igr_remote == null)
		{
			try
			{
				igr_remote = (RealizarNotificacionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                           .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igr_remote = null;
			}
		}

		return igr_remote;
	}
}
