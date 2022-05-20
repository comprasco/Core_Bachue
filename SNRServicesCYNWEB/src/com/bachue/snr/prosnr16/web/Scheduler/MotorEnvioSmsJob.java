package com.bachue.snr.prosnr16.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;


/**
 * Clase que contiene todos las propiedades MotorEnvioSmsJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class MotorEnvioSmsJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String MotorEnvioSmsJOB = "MOTOR_ENVIO_SMS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorEnvioSmsJob.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** {@inheritDoc} */
	public static String getId()
	{
		return MotorEnvioSmsJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.MotorEnvioSmsDelegado().getRemote()
				                                                                         .motorEnvioSms(getRemoteIp());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
