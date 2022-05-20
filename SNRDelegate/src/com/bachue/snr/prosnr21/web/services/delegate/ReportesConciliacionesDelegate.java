package com.bachue.snr.prosnr21.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ReportesConciliacionesRemote;


/**
 * Referencia remota al EJB de Reportes Conciliaciones.
 *
 * @author Gabriel Arias
 * Fecha de Creacion: 09/11/2021
 */
public class ReportesConciliacionesDelegate implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4377002973612223044L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.reportesConciliaciones";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReportesConciliacionesDelegate.class);

	/**  Referencia al objeto de negocio de conciliaciones. */
	private ReportesConciliacionesRemote ircr_remote;

	/**
	 * Instancia un nuevo objeto reference delegate.
	 */
	public ReportesConciliacionesDelegate()
	{
		ircr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReportesConciliacionesRemote getRemote()
	    throws B2BException
	{
		if(ircr_remote == null)
		{
			try
			{
				ircr_remote = (ReportesConciliacionesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ircr_remote = null;
			}
		}

		return ircr_remote;
	}
}
