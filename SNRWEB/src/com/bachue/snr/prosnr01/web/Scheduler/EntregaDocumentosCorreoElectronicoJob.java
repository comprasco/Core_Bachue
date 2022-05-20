package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades EntregaDocumentosCorreoElectronicoJob.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 29/03/2020
 */
public class EntregaDocumentosCorreoElectronicoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String EntregaDocumentosCorreoElectronicoJOB = "ENTREGA_DOCUMENTOS_CORREO_ELECTRONICO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EntregaDocumentosCorreoElectronicoJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return EntregaDocumentosCorreoElectronicoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.envioCorrespondenciaFisica.EnvioCorrespondenciaFisicaDelegado().getRemote()
				                                                                                                        .entregaDocumentosCorreoElectronico(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
