package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para la tarea de RealizarNotificacion
 *
 * @author Manuel Blanco
 */
public class RealizarNotificacionJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String RealizarNotificacionJOB = "REALIZAR_NOTIFICACION";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RealizarNotificacionJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return RealizarNotificacionJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.RealizarNotificacionDelegado().getRemote()
				                                                                                .realizarNotificacion(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
