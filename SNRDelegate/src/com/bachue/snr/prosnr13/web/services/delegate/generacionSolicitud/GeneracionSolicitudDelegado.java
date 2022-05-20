package com.bachue.snr.prosnr13.web.services.delegate.generacionSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr13.ejb.session.stateless.generacionSolicitud.GeneracionSolicitudRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota del servicio web Generación Solicitud.
 *
 * @author Manuel Blanco
 *
 */
public class GeneracionSolicitudDelegado implements Serializable
{
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 5578020867317404111L;

	/** Constante JNDI_NAME */
	private static final String JNDI_NAME = "jndi.generacionSolicitud";

	/** Constante clh_LOGGER */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GeneracionSolicitudDelegado.class, ProyectosCommon.GENERACION_SOLICITUD_13);

	/** Propiedad igsr_remote */
	private GeneracionSolicitudRemote igsr_remote;

	/**
	 * Constructor por defecto
	 */
	public GeneracionSolicitudDelegado()
	{
		igsr_remote = null;
	}

	/**
	 * Obtiene la interfaz remota del servicio web Generación Solicitud.
	 *
	 * @return Interfaz remota relacionada al JNDI_NAME
	 * @throws B2BException Si ocurre un error en la busqueda de la interfaz remota
	 */
	public GeneracionSolicitudRemote getRemote()
	    throws B2BException
	{
		if(igsr_remote == null)
		{
			try
			{
				igsr_remote = (GeneracionSolicitudRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                   .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igsr_remote = null;
			}
		}

		return igsr_remote;
	}
}
