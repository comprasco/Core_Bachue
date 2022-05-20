package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades GenerarDocumentosJob.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 23/07/2019
 */
public class GenerarDocumentosJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String GenerarDocumentosJOB = "GENERAR_DOCUMENTOS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarDocumentosJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return GenerarDocumentosJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.generarDocumentos.GenerarDocumentosDelegado().getRemote()
				                                                                                      .generarDocumentos(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
