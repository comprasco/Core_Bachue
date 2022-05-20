package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el jod de envio de notificaciones de pago
 *
 * @author Julian Vaca
 * Fecha de Creacion: 30/08/2019
 */
public class EnvioNotificacionPagoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioNotificacionPagoJOB = "ENVIO_NOTIFICACION_PAGO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioNotificacionPagoJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioNotificacionPagoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.envioNotificacionPago.EnvioNotificacionPagoDelegado().getRemote()
				                                                                                              .enviarNotificacionPago(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
