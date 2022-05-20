package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Ejecucipon periodica de generación de CRPS
 *
 * @author  Edgar Prieto
 * Fecha de Creacion: 07/31/2020
 */
public class CRPSJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String CRPSJOB = "CRPS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CRPSJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return CRPSJOB;
	}

	/** {@inheritDoc */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			String ls_id;

			ls_id = getId();

			new com.bachue.snr.prosnr01.web.delegate.crps.CRPSDelegado().getRemote()
				                                                            .generarCRPS(
				    new java.util.Date(), ls_id, ls_id, getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
