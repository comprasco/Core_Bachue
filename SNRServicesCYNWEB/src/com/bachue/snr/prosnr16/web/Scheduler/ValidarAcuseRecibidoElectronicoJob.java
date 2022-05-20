package com.bachue.snr.prosnr16.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoElectronicoJob.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/03/2020
 */
public class ValidarAcuseRecibidoElectronicoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String ValidarAcuseRecibidoElectronicoJOB = "VALIDAR_ACUSE_RECIBIDO_ELECTRONICO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ValidarAcuseRecibidoElectronicoJob.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** {@inheritDoc} */
	public static String getId()
	{
		return ValidarAcuseRecibidoElectronicoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr16.web.services.delegate.AcuseRecibidoElectronicoDelegado().getRemote()
				                                                                                    .validarAcuseRecibidoElectronico(
				    getRemoteIp()
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
