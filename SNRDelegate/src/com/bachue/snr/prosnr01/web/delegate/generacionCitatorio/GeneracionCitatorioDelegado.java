package com.bachue.snr.prosnr01.web.delegate.generacionCitatorio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.generacionCitatorio.GeneracionCitatorioRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota de generacion citatorio.
 *
 * @author Manuel Blanco
 *
 */
public class GeneracionCitatorioDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7552291284127193741L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.generacionCitatorio";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GeneracionCitatorioDelegado.class);

	/** The iccr remote. */
	private GeneracionCitatorioRemote iccr_remote;

	/**
	 * Instancia un nuevo generacion citatorio delegado.
	 */
	public GeneracionCitatorioDelegado()
	{
		iccr_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public GeneracionCitatorioRemote getRemote()
	    throws B2BException
	{
		if(iccr_remote == null)
		{
			try
			{
				iccr_remote = (GeneracionCitatorioRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                           .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iccr_remote = null;
			}
		}

		return iccr_remote;
	}
}
