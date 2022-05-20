package com.bachue.snr.prosnr01.web.delegate.generarDocumentos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.generarDocumentos.GenerarDocumentosRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota de generar documentos.
 *
 * @author Julian Vaca
 *
 */
public class GenerarDocumentosDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6960200468878205801L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.generarDocumentos";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarDocumentosDelegado.class);

	/** The igd remote. */
	private GenerarDocumentosRemote igd_remote;

	/**
	 * Instancia un nuevo generar documentos delegado.
	 */
	public GenerarDocumentosDelegado()
	{
		igd_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public GenerarDocumentosRemote getRemote()
	    throws B2BException
	{
		if(igd_remote == null)
		{
			try
			{
				igd_remote = (GenerarDocumentosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igd_remote = null;
			}
		}

		return igd_remote;
	}
}
