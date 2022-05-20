package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaElectronicaJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class EnvioCorrespondenciaElectronicaJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioCorrespondenciaElectronicaJOB = "ENVIO_CORRESPONDENCIA_ELECTRONICA";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaElectronicaJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioCorrespondenciaElectronicaJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.envioCorrespondenciaElectronica.EnvioCorrespondenciaElectronicaDelegado().getRemote()
				                                                                                                                  .enviarCorrespondenciaElectronica(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
