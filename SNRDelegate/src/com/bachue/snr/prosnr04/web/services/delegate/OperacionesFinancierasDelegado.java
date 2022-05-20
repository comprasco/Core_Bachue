package com.bachue.snr.prosnr04.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras.OperacionesFinancierasRemote;


/**
 * Clase para el acceso a la interfaz remota de operaciones financieras.
 *
 * @author Julian Vaca
 *
 */
public class OperacionesFinancierasDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2867547601399136265L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.operacionesFinancieras";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(OperacionesFinancierasDelegado.class);

	/** The ir remote. */
	private OperacionesFinancierasRemote ir_remote;

	/**
	 * Instancia un nuevo operaciones financieras delegado.
	 */
	public OperacionesFinancierasDelegado()
	{
		ir_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public OperacionesFinancierasRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (OperacionesFinancierasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                    .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ir_remote = null;
			}
		}

		return ir_remote;
	}
}
