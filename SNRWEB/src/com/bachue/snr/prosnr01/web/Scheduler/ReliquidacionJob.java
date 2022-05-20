package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad de la tarea de reliquidación
 *
 * @author Gabriel Arias
 */
public class ReliquidacionJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String ReliquidacionJOB = "RELIQUIDACION";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReliquidacionJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return ReliquidacionJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.reliquidacion.ReliquidacionDelegado().getRemote()
				                                                                              .reliquidacion(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
