package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades EnvioComunicadoJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/06/2020
 */
public class DevolucionDineroJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/**  Id del JOB. */
	public static final String DevolucionDineroJobJOB = "DEVOLUCION_DINERO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(DevolucionDineroJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return DevolucionDineroJobJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.devolucionDinero.DevolucionDineroDelegado().getRemote()
				                                                                                    .devolucionDinero(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
