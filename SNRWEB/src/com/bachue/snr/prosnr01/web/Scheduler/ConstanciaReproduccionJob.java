package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para la tarea de constancia reproduccion
 *
 * @author Julian Vaca
 */
public class ConstanciaReproduccionJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String ConstanciaReproduccionJOB = "CONSTANCIA_REPRODUCCION";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConstanciaReproduccionJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return ConstanciaReproduccionJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.registro.ConstanciaReproduccionDelegado().getRemote()
				                                                                                  .constanciaReproduccion(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
