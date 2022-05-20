package com.bachue.snr.prosnr03.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr03.ejb.session.stateless.gestionUsuarios.GestionUsuariosRemote;


/**
 * Class que contiene todos las propiedades Gestión usuarios delegado.
 *
 * @author Andres Rocha
 */
public class GestionUsuariosDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2867547601399136265L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.gestionUsuarios";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionUsuariosDelegado.class);

	/** Propiedad igur remote. */
	private GestionUsuariosRemote igur_remote;

	/**
	 * Instancia un nuevo objeto gestión usuarios delegado.
	 */
	public GestionUsuariosDelegado()
	{
		igur_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public GestionUsuariosRemote getRemote()
	    throws B2BException
	{
		if(igur_remote == null)
		{
			try
			{
				igur_remote = (GestionUsuariosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igur_remote = null;
			}
		}

		return igur_remote;
	}
}
