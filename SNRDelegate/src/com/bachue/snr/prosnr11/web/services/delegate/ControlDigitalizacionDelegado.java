package com.bachue.snr.prosnr11.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;
import com.bachue.snr.prosnr11.ejb.session.stateless.controlDigitalizacion.ControlDigitalizacionRemote;


/**
 * Clase que contiene todos las propiedades ControlDigitalizacionDelegado.
 *
 * @author  Heiner Emanuel Castañeda
 * Fecha de Creacion: 07/11/2019
 */
public class ControlDigitalizacionDelegado implements java.io.Serializable
{
	
	/**
	 * Constante serialVersionUID.
	 */
	private static final long            serialVersionUID = -2867547601399136265L;
	
	/**
	 * Constante JNDI_NAME.
	 */
	private static final String          JNDI_NAME        = "jndi.controlDigitalizacion";
	
	/**
	 * Constante clh_LOGGER.
	 */
	private static final LoggerHandler   clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ControlDigitalizacionDelegado.class);
	
	/**
	 * Propiedad ir remote.
	 */
	private ControlDigitalizacionRemote ir_remote;

	/**
	 * Instancia un nuevo objeto control digitalizacion delegado.
	 */
	public ControlDigitalizacionDelegado()
	{
		ir_remote                                         = null;
	}

	/**
	 * Get remote.
	 *
	 * @return el valor de control digitalizacion remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ControlDigitalizacionRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (ControlDigitalizacionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
