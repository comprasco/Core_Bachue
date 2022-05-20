package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.logging.LoggerHandler;


/**
 * Clase que contiene todos las propiedades CrearTurnoJob.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class CrearTurnoJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Id del JOB */
	public static final String CrearTurnoJOB = "CREAR_TURNO";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CrearTurnoJob.class);

	/** {@inheritDoc} */
	public static String getId()
	{
		return CrearTurnoJOB;
	}

	/** {@inheritDoc} */
	public void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			new com.bachue.snr.prosnr01.web.delegate.crearTurno.CrearTurnoDelegado().getRemote()
				                                                                        .crearTurno(getRemoteIp());
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}
}
