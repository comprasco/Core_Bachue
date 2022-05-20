package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de firma masiva
 *
 * @author Manuel Blanco
 */
public class EnvioAvisoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioAvisoJOB = "ENVIO_AVISO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioAvisoJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioAvisoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.RealizarNotificacionDelegado().getRemote()
				                                                                                .enviarAviso(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
