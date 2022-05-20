package com.bachue.snr.prosnr01.web.delegate.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.cuentaCupos.CuentaCuposRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota de Cuenta Cupos.
 *
 * @author Manuel Blanco
 *
 */
public class CuentaCuposDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7552291284127193741L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.cuentaCupos";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CuentaCuposDelegado.class);

	/** The iccr remote. */
	private CuentaCuposRemote iccr_remote;

	/**
	 * Instancia un nuevo cuenta cupos delegado.
	 */
	public CuentaCuposDelegado()
	{
		iccr_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public CuentaCuposRemote getRemote()
	    throws B2BException
	{
		if(iccr_remote == null)
		{
			try
			{
				iccr_remote = (CuentaCuposRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
