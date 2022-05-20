package com.bachue.snr.prosnr17.web.services.delegate.solicitud.correccion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr17.ejb.session.stateless.solicitud.correccion.SolicitudCorreccionRemote;

import java.io.Serializable;


/**
 * Class que contiene todos las propiedades Solicitud Correciones Delegado.
 *
 * @author Andres Rocha
 */
public class SolicitudCorreccionDelegado implements Serializable
{
	private static final long          serialVersionUID = -6980260431735511128L;
	private static final String        JNDI_NAME        = "jndi.solicitudCorreccion";
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SolicitudCorreccionDelegado.class, ProyectosCommon.SOLICITUD_DE_CORRECCION_17);
	private SolicitudCorreccionRemote  iscr_remote;

	/**
	 * Método constructor.
	 */
	public SolicitudCorreccionDelegado()
	{
		iscr_remote                                     = null;
	}

	/**
	 * Método encargado de consultar el remote.
	 *
	 * @return retorna el remote.
	 * @throws B2BException
	 */
	public SolicitudCorreccionRemote getRemote()
	    throws B2BException
	{
		if(iscr_remote == null)
		{
			try
			{
				iscr_remote = (SolicitudCorreccionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
