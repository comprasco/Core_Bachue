package com.bachue.snr.prosnr18.web.services.delegate.solicitud.copias;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr18.ejb.session.stateless.solicitud.copias.SolicitudCopiasRemote;

import java.io.Serializable;

/**
 * Clase que contiene todos las propiedades SolicitudCopiasDelegado.
 * 
 * @author Gabriel Arias
 * Fecha de Creacion: 07/04/2020
 */
public class SolicitudCopiasDelegado implements Serializable
{
	private static final long          serialVersionUID = -8470741433244925681L;
	private static final String        JNDI_NAME        = "jndi.solicitudCopias";
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SolicitudCopiasDelegado.class, ProyectosCommon.SOLICITUD_DE_COPIAS_18);
	private SolicitudCopiasRemote      iscr_remote;

	/**
	 * Método constructor.
	 */
	public SolicitudCopiasDelegado()
	{
		iscr_remote                                     = null;
	}

	/**
	 * Método encargado de consultar el remote.
	 *
	 * @return retorna el remote.
	 * @throws B2BException
	 */
	public SolicitudCopiasRemote getRemote()
	    throws B2BException
	{
		if(iscr_remote == null)
		{
			try
			{
				iscr_remote = (SolicitudCopiasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iscr_remote = null;
			}
		}

		return iscr_remote;
	}
}
