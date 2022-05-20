package com.bachue.snr.prosnr01.web.delegate.crps;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.crps.CRPSRemote;


/**
 * Objeto para ubicar proxy de interaccion con EJB de CRPS
 *
 * @author Edgar Prieto
 */
public class CRPSDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3635445083933750166L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.crps";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CRPSDelegado.class);

	/** Propiedad inc remote. */
	private CRPSRemote icrpsr_remote;

	/**
	 * Instancia un nuevo objeto notificar catastro delegado.
	 */
	public CRPSDelegado()
	{
		icrpsr_remote = null;
	}

	/**
	 * Retorna proxy de interaccion con EJB de CRPS
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CRPSRemote getRemote()
	    throws B2BException
	{
		if(icrpsr_remote == null)
		{
			try
			{
				icrpsr_remote = (CRPSRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icrpsr_remote = null;
			}
		}

		return icrpsr_remote;
	}
}
