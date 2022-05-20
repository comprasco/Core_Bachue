package com.bachue.snr.prosnr01.web.delegate.reference;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;


/**
 * Clase para el acceso a la interfaz remota del reference
 * 
 * @author Duvan Beltran
 * Fecha de Creacion: 12/09/2019
 */
public class ReferenceDelegate implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 810748427685349884L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.reference";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReferenceDelegate.class);

	/** Propiedad ir remote. */
	private ReferenceRemote ir_remote;

	/**
	 * Instancia un nuevo objeto gestión reference delegado.
	 */
	public ReferenceDelegate()
	{
		ir_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReferenceRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (ReferenceRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
