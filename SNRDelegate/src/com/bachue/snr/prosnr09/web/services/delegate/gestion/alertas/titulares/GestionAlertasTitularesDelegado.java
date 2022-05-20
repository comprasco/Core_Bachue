package com.bachue.snr.prosnr09.web.services.delegate.gestion.alertas.titulares;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr09.ejb.session.stateless.gestion.alertas.titulares.GestionAlertasTitularesRemote;


/**
 * Clase que contiene todos las propiedades GestionAlertasTitularesDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 31/07/2020
 */
public class GestionAlertasTitularesDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 20109240096564632L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.gestionAlertasTitulares";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionAlertasTitularesDelegado.class);

	/** Propiedad igatr remote. */
	private GestionAlertasTitularesRemote igatr_remote;

	/**
	 * Instancia un nuevo objeto gestion alertas titulares delegado.
	 */
	public GestionAlertasTitularesDelegado()
	{
		igatr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public GestionAlertasTitularesRemote getRemote()
	    throws B2BException
	{
		if(igatr_remote == null)
		{
			try
			{
				igatr_remote = (GestionAlertasTitularesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                        .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igatr_remote = null;
			}
		}

		return igatr_remote;
	}
}
