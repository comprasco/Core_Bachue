package com.bachue.snr.prosnr01.web.delegate.reportes;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.reportes.ReportesRemote;


/**
 * Clase para el acceso a la interfaz remota de reportes delegado.
 *
 * @author Julian Vaca
 *
 */
public class ReportesDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2867547601399136265L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.reportes";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReportesDelegado.class);

	/** The ir remote. */
	private ReportesRemote ir_remote;

	/**
	 * Instancia un nuevo reportes delegado.
	 */
	public ReportesDelegado()
	{
		ir_remote = null;
	}

	/**
	 * Obtiene el valor de remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException el valor de b 2 B exception
	 */
	public ReportesRemote getRemote()
	    throws B2BException
	{
		if(ir_remote == null)
		{
			try
			{
				ir_remote = (ReportesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
