package com.bachue.snr.prosnr16.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoFisicoJob.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 26/03/2020
 */
public class AcuseRecibidoFisicoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String AcuseRecibidoFisicoJOB = "ACUSE_RECIBIDO_FISICO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AcuseRecibidoFisicoJob.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** {@inheritDoc} */
	public static String getId()
	{
		return AcuseRecibidoFisicoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.AcuseRecibidoFisicoDelegado().getRemote()
				                                                                               .acuseRecibidoFisico(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
