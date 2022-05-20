package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades NotificarCatastroSOSPJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/07/2020
 */
public class NotificarCatastroSOSPJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String NotificarCatastroSOSPJOB = "NOTIFICAR_CATASTRO_MSOSP";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarCatastroSOSPJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return NotificarCatastroSOSPJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.notificarCatastro.NotificarCatastroDelegado().getRemote()
				                                                                                      .notificarCatastroSOSP(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
