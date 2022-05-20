package com.bachue.snr.prosnr01.web.delegate.envioNotificacionPago;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.envioNotificacionPago.EnvioNotificacionPagoRemote;


/**
 * Clase que contiene todos las propiedades EnvioNotificacionPagoDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class EnvioNotificacionPagoDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2217237832890952038L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.envioNotificacionPago";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioNotificacionPagoDelegado.class);

	/** Propiedad ifm remote. */
	private EnvioNotificacionPagoRemote ifm_remote;

	/**
	 * Instancia un nuevo objeto envio notificacion pago delegado.
	 */
	public EnvioNotificacionPagoDelegado()
	{
		ifm_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EnvioNotificacionPagoRemote getRemote()
	    throws B2BException
	{
		if(ifm_remote == null)
		{
			try
			{
				ifm_remote = (EnvioNotificacionPagoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                    .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ifm_remote = null;
			}
		}

		return ifm_remote;
	}
}
