package com.bachue.snr.prosnr01.web.delegate.devolucionDinero;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero.DevolucionDineroRemote;


/**
 * Clase que contiene todos las propiedades CertificadosDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/09/2019
 */
public class DevolucionDineroDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3058155419207642043L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.devolucionDinero";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(DevolucionDineroDelegado.class);

	/** Propiedad icr remote. */
	private DevolucionDineroRemote icr_remote;

	/**
	 * Instancia un nuevo objeto certificados delegado.
	 */
	public DevolucionDineroDelegado()
	{
		icr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DevolucionDineroRemote getRemote()
	    throws B2BException
	{
		if(icr_remote == null)
		{
			try
			{
				icr_remote = (DevolucionDineroRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icr_remote = null;
			}
		}

		return icr_remote;
	}
}
