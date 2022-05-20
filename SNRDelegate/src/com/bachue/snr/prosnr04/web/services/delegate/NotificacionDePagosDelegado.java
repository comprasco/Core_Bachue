package com.bachue.snr.prosnr04.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras.NotificacionDePagosRemote;


/**
 * Clase para el acceso a la interfaz remota de notificaciones de pagos.
 *
 * @author Julian Vaca
 *
 */
public class NotificacionDePagosDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2867547601399136265L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.notificacionDePagos";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificacionDePagosDelegado.class);

	/** The ir remote. */
	private NotificacionDePagosRemote ir_remote;

	/**
	 * Instancia un nuevo notificaciones de pagos delegado.
	 */
	public NotificacionDePagosDelegado()
	{
		ir_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public NotificacionDePagosRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (NotificacionDePagosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
