package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de firma masiva
 *
 * @author Julian Vaca
 */
public class FirmaMasivaJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String FirmaMasivaJOB = "FIRMA_MASIVA";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(FirmaMasivaJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return FirmaMasivaJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.firma.FirmaMasivaDelegado().getRemote().firmaMasiva(getRemoteIp());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
