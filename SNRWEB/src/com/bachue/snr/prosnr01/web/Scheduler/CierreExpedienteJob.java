package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades CierreExpedienteJob.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 31/03/2020
 */
public class CierreExpedienteJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String CierreExpedienteJOB = "CIERRE_EXPEDIENTE";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CierreExpedienteJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return CierreExpedienteJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.notificaciones.NotificacionesDelegado().getRemote()
				                                                                                .cierreExpediente(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
