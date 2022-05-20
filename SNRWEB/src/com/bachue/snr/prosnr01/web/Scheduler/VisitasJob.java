package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para la tarea de certificados
 *
 * @author Julian Vaca
 */
public class VisitasJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String VisitasJOB = "APROBACION_EJECUCION_DELEGADO_DE_REGISTRO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(VisitasJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return VisitasJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.visitas.VisitasDelegado().getRemote()
				                                                                  .aprobacionEjecucionVisitas(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
