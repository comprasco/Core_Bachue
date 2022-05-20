package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades NotificarCatastroSOCPJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/07/2020
 */
public class NotificarCatastroSOCPJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String NotificarCatastroSOCPJOB = "NOTIFICAR_CATASTRO_MSOCP";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarCatastroSOCPJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return NotificarCatastroSOCPJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.notificarCatastro.NotificarCatastroDelegado().getRemote()
				                                                                                      .notificarCatastroSOCP(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
