package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad de la tarea de cancelacion
 *
 * @author Julian Vaca
 */
public class CancelacionJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String CancelacionJOB = "CANCELACION";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CancelacionJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return CancelacionJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.cancelacion.CancelacionDelegado().getRemote()
				                                                                          .cancelacion(getRemoteIp());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
