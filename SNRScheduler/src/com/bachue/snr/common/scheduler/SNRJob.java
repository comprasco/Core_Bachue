package com.bachue.snr.common.scheduler;


/**
 * Clase base para la ejecución de tareas automaticas Quartz en la aplicacion
 *
 * @author Edgar Prieto
 */
public abstract class SNRJob implements org.quartz.Job
{
	/** Constante RemoteIp. */
	public static final String DATA_IP_REMOTA = "remoteIp";

	/** IP desde  donde se inicia la ejecución de la tarea automática */
	private String is_remoteIp;

	/** {@inheritdoc} */
	public abstract void execute(org.quartz.JobExecutionContext ajec_context)
	    throws org.quartz.JobExecutionException;

	/**
	  * @return Id con el cual se registrará el JOB ante el agendador de tareas;
	  */
	public static String getId()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Ajusta el valor de la IP desde  donde se inicia la ejecución de la tarea automática
	 * @param as_s IP desde donde se inicia la ejecución de la tarea automática
	 */
	public void setRemoteIp(String as_s)
	{
		is_remoteIp = as_s;
	}

	/**
	 * @return IP desde donde se inicia la ejecución de la tarea automática.
	 */
	public String getRemoteIp()
	{
		return is_remoteIp;
	}
}
