package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de validación personas notificadas
 *
 * @author Manuel Blanco
 */
public class ValidacionPersonasNotificadasJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String ValidacionPersonasNotificadasJOB = "VALIDACION_PERSONAS_NOTIFICADAS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ValidacionPersonasNotificadasJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return ValidacionPersonasNotificadasJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.RealizarNotificacionDelegado().getRemote()
				                                                                                .validarPersonasNotificadas(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
