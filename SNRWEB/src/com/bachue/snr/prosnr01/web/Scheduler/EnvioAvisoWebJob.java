package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de firma masiva
 *
 * @author Manuel Blanco
 */
public class EnvioAvisoWebJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioAvisoWebJOB = "ENVIO_AVISO_WEB";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioAvisoWebJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioAvisoWebJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.RealizarNotificacionDelegado().getRemote()
				                                                                                .enviarAvisoWeb(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
