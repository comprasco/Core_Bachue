package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaElectronica206Job.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class EnvioCorrespondenciaElectronica206Job extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EnvioCorrespondenciaElectronica206JOB = "ENVIO_CORRESPONDENCIA_ELECTRONICA_206";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaElectronica206Job.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EnvioCorrespondenciaElectronica206JOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.envioCorrespondenciaElectronica.EnvioCorrespondenciaElectronicaDelegado().getRemote()
				                                                                                                                  .enviarCorrespondenciaElectronica206(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
