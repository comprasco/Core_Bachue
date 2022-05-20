package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos la funcionalidad para el job de recepcion documentos
 *
 * @author Gabriel Arias
 */
public class RecepcionDocumentosJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String RecepcionDocumentosJOB = "RECEPCION_DOCUMENTOS";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RecepcionDocumentosJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return RecepcionDocumentosJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.recepcion.RecepcionDocumentosDelegado().getRemote()
				                                                                                .recepcionDocumentos(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
