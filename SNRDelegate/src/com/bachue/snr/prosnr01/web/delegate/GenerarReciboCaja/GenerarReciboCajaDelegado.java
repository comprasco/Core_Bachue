package com.bachue.snr.prosnr01.web.delegate.GenerarReciboCaja;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.generarReciboCaja.GenerarReciboCajaRemote;

import java.io.Serializable;

/**
 * Clase para el acceso a la interfaz remota de generar documentos.
 *
 * @author Julian Vaca
 *
 */
public class GenerarReciboCajaDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long          serialVersionUID = -2233245827531929971L;
	
	/** Constante JNDI_NAME. */
	private static final String        JNDI_NAME        = "jndi.generarReciboCaja";
	
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarReciboCajaDelegado.class);
	
	/** The igrc remote. */
	private GenerarReciboCajaRemote    igrc_remote;

	/**
	 * Instancia un nuevo gerenera recibo caja delegado.
	 */
	public GenerarReciboCajaDelegado()
	{
		igrc_remote                                     = null;
	}
	
	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public GenerarReciboCajaRemote getRemote()
	    throws B2BException
	{
		if(igrc_remote == null)
		{
			try
			{
				igrc_remote = (GenerarReciboCajaRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igrc_remote = null;
			}
		}

		return igrc_remote;
	}
}
