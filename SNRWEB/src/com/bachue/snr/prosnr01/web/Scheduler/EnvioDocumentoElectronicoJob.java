package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades EnvioDocumentoElectronicoJob.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/03/2020
 */
public class EnvioDocumentoElectronicoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioDocumentoElectronicoJOB = "ENVIAR_DOCUMENTO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioDocumentoElectronicoJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioDocumentoElectronicoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.notificaciones.NotificacionesDelegado().getRemote()
				                                                                                .envioDocumentoElectronico(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
