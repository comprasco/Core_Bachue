package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de generación citatorio
 *
 * @author Manuel Blanco
 */
public class GeneracionCitatorioJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String GeneracionCitatorioJOB = "GENERACION_CITATORIO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GeneracionCitatorioJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return GeneracionCitatorioJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.generacionCitatorio.GeneracionCitatorioDelegado().getRemote()
				                                                                                          .procesarCasos(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
