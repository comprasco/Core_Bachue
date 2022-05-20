package com.bachue.snr.prosnr01.web.delegate.visitas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.visitas.VisitasRemote;


/**
 * Clase que contiene todos las propiedades VisitasDelegado.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 3/08/2020
 */
public class VisitasDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5517089417071470002L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.visitas";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(VisitasDelegado.class);

	/** Propiedad icr remote. */
	private VisitasRemote ivr_remote;

	/**
	 * Instancia un nuevo objeto certificados delegado.
	 */
	public VisitasDelegado()
	{
		ivr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public VisitasRemote getRemote()
	    throws B2BException
	{
		if(ivr_remote == null)
		{
			try
			{
				ivr_remote = (VisitasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ivr_remote = null;
			}
		}

		return ivr_remote;
	}
}
