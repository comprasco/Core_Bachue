package com.bachue.snr.prosnr21.web.listeners.scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import org.quartz.JobExecutionContext;


/**
 * Ejecucion de un proceso automático de conciliacion
 *
 * @author Julian Vaca
 */
public class ConciliacionesJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConciliacionesJob.class);

	/** Nombre del JOB */
	public static final String JOB_NAME = "CONCILIACIONES";

	/** Nombre de propiedad para identificación del proceso */
	public static final String ID_PROCESO_CONCILIACION = "idProcesoConciliacion";

	/** Identificación del proceso */
	private String is_idProcesoConciliacion;

	/** {@inheritDoc} */
	public static String getId()
	{
		return JOB_NAME;
	}

	/**
	 * Obtiene el nombre de un Job de conciliaciones de acuerdo al proceso de conciliacion que se va a ejecutar
	 *
	 * @param as_idProcesoConciliacion Id del proceso de conciliacion que se va a ejecutar
	 */
	public static String getJobName(String as_idProcesoConciliacion)
	{
		String ls_id;
		String ls_nombre;

		ls_id = getId();

		if(StringUtils.isValidString(ls_id) && StringUtils.isValidString(as_idProcesoConciliacion))
		{
			StringBuilder lsb_nombre;

			lsb_nombre = new StringBuilder();

			lsb_nombre.append(ls_id);
			lsb_nombre.append("_");
			lsb_nombre.append(as_idProcesoConciliacion);

			ls_nombre = lsb_nombre.toString();
		}
		else
			ls_nombre = null;

		return ls_nombre;
	}

	/**
	 * Ajusta el valor de id del proceso de conciliacion
	 * @param as_s id del proceso de conciliacion
	 */
	public void setIdProcesoConciliacion(String as_s)
	{
		is_idProcesoConciliacion = StringUtils.getString(as_s);
	}

	/** @return Id del proceso de conciliación */
	public String getIdProcesoConciliacion()
	{
		return is_idProcesoConciliacion;
	}

	/** {@inheritDoc} */
	public void execute(JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			String ls_remoteIp;

			ls_remoteIp = getRemoteIp();

			new com.bachue.snr.prosnr21.web.services.delegate.ConciliacionesDelegate().getRemote()
				                                                                          .cargarArchivos(
				    getIdProcesoConciliacion(), null, ls_remoteIp, ls_remoteIp
				);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("execute", le_e);
		}
	}

	/** {@inheritDoc} */
	public void interrupt()
	    throws org.quartz.UnableToInterruptJobException
	{
		System.err.println(
		    "ConciliacionesJob :: interrupt :: " + getIdProcesoConciliacion() + " :: " + new java.util.Date()
		);
	}
}
