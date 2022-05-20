package com.bachue.snr.prosnr01.web.delegate.certificados;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.certificados.CertificadosRemote;


/**
 * Clase que contiene todos las propiedades CertificadosDelegado.
 * 
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/09/2019
 */
public class CertificadosDelegado implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long          serialVersionUID = 3463691125632623966L;
	
	/** Constante JNDI_NAME. */
	private static final String        JNDI_NAME        = "jndi.certificados";
	
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CertificadosDelegado.class);
	
	/** Propiedad icr remote. */
	private CertificadosRemote         icr_remote;

	/**
	 * Instancia un nuevo objeto certificados delegado.
	 */
	public CertificadosDelegado()
	{
		icr_remote                                      = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CertificadosRemote getRemote()
	    throws B2BException
	{
		if(icr_remote == null)
		{
			try
			{
				icr_remote = (CertificadosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
