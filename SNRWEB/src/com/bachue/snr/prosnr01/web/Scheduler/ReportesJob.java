package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para programar los jobs del sistema
 *
 * @author Julian Vaca
 */
public class ReportesJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String ReportesProcessJOB = "REPORTES";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReportesJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return ReportesProcessJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.reportes.ReportesDelegado().getRemote()
				                                                                    .getEveningReport(getRemoteIp());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
