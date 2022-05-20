package com.bachue.snr.prosnr21.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Class que contiene todos las propiedades de Envio Gestor Documental Job.
 *
 * @author Andres Rocha
 */
public class EnvioGestorDocumentalJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioGestorDocumentalJOB = "ENVIO_GESTOR_DOCUMENTAL";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioGestorDocumentalJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioGestorDocumentalJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr21.web.services.delegate.EnvioGestorDocumentalDelegado().getRemote()
				                                                                                   .enviarGestorDocumental(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
