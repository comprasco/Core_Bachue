package com.bachue.snr.prosnr01.web.delegate.envioComunicado;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;
import com.bachue.snr.prosnr01.ejb.session.stateless.envioComunicado.EnvioComunicadoRemote;


/**
 * Clase que contiene todos las propiedades EnvioComunicadoDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/06/2020
 */
public class EnvioComunicadoDelegado implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2991769162700887369L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.envioComunicado";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioComunicadoDelegado.class);

	/** Propiedad iegdr remote. */
	private EnvioComunicadoRemote iecr_remote;

	/**
	 * Instancia un nuevo objeto envio gestor documental delegado.
	 */
	public EnvioComunicadoDelegado()
	{
		iecr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de EnvioComunicadoRemote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EnvioComunicadoRemote getRemote()
	    throws B2BException
	{
		if(iecr_remote == null)
		{
			try
			{
				iecr_remote = (EnvioComunicadoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iecr_remote = null;
			}
		}

		return iecr_remote;
	}
}
