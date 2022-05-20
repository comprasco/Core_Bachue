package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades NotificarCatastroJob.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class NotificarCatastroPOJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String NotificarCatastroPOJOB = "NOTIFICAR_CATASTRO_MPO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificarCatastroPOJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return NotificarCatastroPOJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.notificarCatastro.NotificarCatastroDelegado().getRemote()
				                                                                                      .notificarCatastroPO(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
