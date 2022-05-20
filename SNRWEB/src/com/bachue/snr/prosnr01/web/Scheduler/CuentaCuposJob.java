package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de firma masiva
 *
 * @author Manuel Blanco
 */
public class CuentaCuposJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String CuentaCuposJOB = "CUENTA_CUPOS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CuentaCuposJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return CuentaCuposJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.cuentaCupos.CuentaCuposDelegado().getRemote()
				                                                                          .desencolarPago(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
