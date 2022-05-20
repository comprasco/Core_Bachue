package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para programar la tarea de generar recibo de caja
 *
 * @author Julian Vaca
 */
public class GenerarReciboCajaJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String GenerarReciboCajaJOB = "GENERAR_RECIBO_CAJA";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarReciboCajaJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return GenerarReciboCajaJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.GenerarReciboCaja.GenerarReciboCajaDelegado().getRemote()
				                                                                                      .generarReciboCaja(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
