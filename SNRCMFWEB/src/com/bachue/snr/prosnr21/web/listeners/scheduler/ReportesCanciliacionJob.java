package com.bachue.snr.prosnr21.web.listeners.scheduler;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import org.quartz.JobExecutionContext;


/**
 * Ejecucion de un proceso automático de conciliacion
 *
 * @author Gabriel Arias
 */
public class ReportesCanciliacionJob extends com.bachue.snr.common.scheduler.SNRJob
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReportesCanciliacionJob.class);

	/** Nombre del JOB */
	public static final String JOB_NAME = "REPORTES_CONCILIACION";

	/** Nombre de propiedad para identificación del proceso */
	public static final String ID_REPORTES_CONCILIACION = "idReportesConciliacion";

	/** Identificación del proceso */
	private String is_idReporteConciliacion;

	/** {@inheritDoc} */
	public static String getId()
	{
		return JOB_NAME;
	}

	/**
	 * Ajusta el valor de id del proceso de conciliacion
	 * @param as_s id del proceso de conciliacion
	 */
	public void setIdReporteConciliacion(String as_s)
	{
		is_idReporteConciliacion = StringUtils.getString(as_s);
	}

	/** @return Id del proceso de conciliación */
	public String getIdReporteConciliacion()
	{
		return is_idReporteConciliacion;
	}

	/**
	 * Obtiene el nombre de un Job de conciliaciones de acuerdo al proceso de conciliacion que se va a ejecutar
	 *
	 * @param as_idProcesoConciliacion Id del proceso de conciliacion que se va a ejecutar
	 */
	public static String getJobName(String as_idReporte)
	{
		String ls_id;
		String ls_nombre;

		ls_id = getId();

		if(StringUtils.isValidString(ls_id) && StringUtils.isValidString(as_idReporte))
		{
			StringBuilder lsb_nombre;

			lsb_nombre = new StringBuilder();

			lsb_nombre.append(ls_id);
			lsb_nombre.append("_");
			lsb_nombre.append(as_idReporte);

			ls_nombre = lsb_nombre.toString();
		}
		else
			ls_nombre = null;

		return ls_nombre;
	}

	/** {@inheritDoc} */
	public void execute(JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException
	{
		try
		{
			String ls_remoteIp;

			ls_remoteIp = getRemoteIp();

			new com.bachue.snr.prosnr21.web.services.delegate.ReportesConciliacionesDelegate().getRemote()
				                                                                                  .generarReportes(
				    getIdReporteConciliacion(), getId(), ls_remoteIp, ls_remoteIp
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
		    "ReportesConciliacionesJob :: interrupt :: " + getIdReporteConciliacion() + " :: " + new java.util.Date()
		);
	}
}
