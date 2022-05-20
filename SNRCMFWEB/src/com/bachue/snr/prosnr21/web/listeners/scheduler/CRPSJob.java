package com.bachue.snr.prosnr21.web.listeners.scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import org.quartz.JobExecutionContext;


/**
 * Ejecucion de un proceso automático de conciliacion
 *
 * @author Julian Vaca
 */
public class CRPSJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CRPSJob.class);

	/** Nombre del JOB */
	public static final String JOB_NAME = "CRPS";

	/** {@inheritDoc} */
	public static String getId()
	{
		return JOB_NAME;
	}

	/** {@inheritDoc} */
	public void execute(JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			String ls_remoteIp;

			ls_remoteIp = getRemoteIp();

			new com.bachue.snr.prosnr21.web.services.delegate.ConciliacionesDelegate().getRemote()
				                                                                          .cargarCRPS(
				    new java.util.Date(), getId(), ls_remoteIp, ls_remoteIp
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
