package com.bachue.snr.prosnr21.web.listeners.scheduler;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.common.constants.Quartz;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr21.common.constants.EstadoCommon;
import com.bachue.snr.prosnr21.common.constants.PeriodicidadCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import com.bachue.snr.prosnr21.web.Scheduler.EnvioGestorDocumentalJob;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos la funcionalidad de ejecución de procesos automáticos de conciliacion.
 *
 * @author eprieto
 */
public class ConciliacionesScheduler extends com.bachue.snr.common.scheduler.SNRClusterScheduler
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConciliacionesScheduler.class);

	/**  EJB Remoto de referencia para consulta de parametros. */
	private ParameterRemote ipr_referenciaRemota;

	/**  Cluster de alta dispoinibilidad de en el cual se debe ejecutar las tarea automaticas de concicilaciones. */
	private String is_clusterConciliaciones;

	/**
	 * Elimina una tarea automatica asociada a un proceso de conciliacion.
	 *
	 * @param apc_proceso Proceso de conciliación asociado a la tarea a eliminar
	 */
	public void borrarJob(ProcesoConciliacion apc_proceso)
	{
		if((apc_proceso != null))
			borrarJob(getClusterConciliaciones(), ConciliacionesJob.getJobName(apc_proceso.getIdProcesoConciliacion()));
	}

	/**
	 * Borrar job reporte.
	 *
	 * @param arptp_proceso de arptp proceso
	 */
	public void borrarJobReporte(RPTPrograma arptp_proceso)
	{
		if((arptp_proceso != null))
			borrarJob(getClusterConciliaciones(), ReportesCanciliacionJob.getJobName(arptp_proceso.getIdReporte()));
	}

	/**
	 * Inicialización de una tarea automática de conciliacion.
	 *
	 * @param apc_proceso Proceso de conciliación asociado a la tarea a iniciar
	 * @param as_nodo Nodo que se está inicializando dentro del cluster
	 */
	public void iniciarJob(ProcesoConciliacion apc_proceso, String as_nodo)
	{
		if((apc_proceso != null) && StringUtils.isValidString(as_nodo))
		{
			Map<String, org.quartz.JobDetail> lmsjd_jobs;
			Map<String, org.quartz.Trigger>   lmst_triggers;

			lmsjd_jobs        = new HashMap<String, JobDetail>();
			lmst_triggers     = new HashMap<String, Trigger>();

			iniciarJob(apc_proceso, lmsjd_jobs, lmst_triggers, getClusterConciliaciones(), as_nodo, true);
		}
	}

	/**
	 * Iniciar job reporte.
	 *
	 * @param arptp_proceso de arptp proceso
	 * @param as_nodo de as nodo
	 */
	public void iniciarJobReporte(RPTPrograma arptp_proceso, String as_nodo)
	{
		if((arptp_proceso != null) && StringUtils.isValidString(as_nodo))
		{
			Map<String, org.quartz.JobDetail> lmsjd_jobs;
			Map<String, org.quartz.Trigger>   lmst_triggers;

			lmsjd_jobs        = new HashMap<String, JobDetail>();
			lmst_triggers     = new HashMap<String, Trigger>();

			iniciarJob(arptp_proceso, lmsjd_jobs, lmst_triggers, getClusterConciliaciones(), as_nodo, true);
		}
	}

	/** {@inheritDoc} */
	protected com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote getReferenciaRemota()
	{
		return getReferenciaRemotaInterna();
	}

	/** {@inheritDoc} */
	protected void iniciarJobs(
	    java.util.Collection<String> acs_nombresJob, Map<String, org.quartz.JobDetail> amsjd_jobs,
	    Map<String, org.quartz.Trigger> amst_triggers, String as_cluster, String as_nodo
	)
	{
		if(
		    com.b2bsg.common.util.CollectionUtils.isValidCollection(acs_nombresJob) && (amsjd_jobs != null)
			    && (amst_triggers != null) && StringUtils.isValidString(as_cluster)
			    && StringUtils.isValidString(as_nodo)
		)
		{
			for(String ls_nombreJob : acs_nombresJob)
			{
				if(StringUtils.isValidString(ls_nombreJob))
				{
					ls_nombreJob = ls_nombreJob.replaceAll(Quartz.PREFIJO_JOB, new String())
							                       .replaceAll(Quartz.SUFIJO_CLUSTER, new String());

					if(ls_nombreJob.equals(ConciliacionesJob.getId()))
					{
						java.util.Collection<ProcesoConciliacion> lcpc_procesos;

						setClusterConciliaciones(as_cluster);

						try
						{
							lcpc_procesos = getReferenciaRemotaInterna()
									                .buscarProcesosConciliacionActivos(null, null, null);
						}
						catch(B2BException lb2b_e)
						{
							lcpc_procesos = null;
						}

						if(CollectionUtils.isValidCollection(lcpc_procesos))
						{
							for(ProcesoConciliacion lpc_proceso : lcpc_procesos)
							{
								if(lpc_proceso != null)
									iniciarJob(lpc_proceso, amsjd_jobs, amst_triggers, as_cluster, as_nodo, false);
							}
						}
					}
					else if(ls_nombreJob.equals(ReportesCanciliacionJob.getId()))
					{
						java.util.Collection<RPTPrograma> lcrprp_reportes;

						setClusterConciliaciones(as_cluster);

						try
						{
							lcrprp_reportes = getReferenciaRemotaInterna()
									                  .buscarProcesosRptProgramasActivos(null, null, null);
						}
						catch(B2BException lb2b_e)
						{
							lcrprp_reportes = null;
						}

						if(CollectionUtils.isValidCollection(lcrprp_reportes))
						{
							for(RPTPrograma lrpt_programa : lcrprp_reportes)
							{
								if(lrpt_programa != null)
									iniciarJob(lrpt_programa, amsjd_jobs, amst_triggers, as_cluster, as_nodo, false);
							}
						}
					}
					else if(ls_nombreJob.equals(EnvioGestorDocumentalJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioGestorDocumentalJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(CRPSJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CRPSJob.class, ls_nombreJob);
				}
			}
		}
	}

	/**
	 * Modifica el valor de ClusterConciliaciones.
	 *
	 * @param as_s de as s
	 * @return Cluster de alta dispoinibilidad de en el cual se debe ejecutar las tarea automaticas de concicilaciones
	 */
	private void setClusterConciliaciones(String as_s)
	{
		if(is_clusterConciliaciones == null)
			is_clusterConciliaciones = StringUtils.getString(as_s);
	}

	/**
	 * Ajusta el valor de id del proceso de conciliacion.
	 *
	 * @return el valor de cluster conciliaciones
	 */
	private String getClusterConciliaciones()
	{
		return is_clusterConciliaciones;
	}

	/**
	 * Retorna Objeto o variable de valor referencia remota interna.
	 *
	 * @return el valor de referencia remota interna
	 * @retorn EJB Remoto de referencia para consulta de parametros
	 */
	private ParameterRemote getReferenciaRemotaInterna()
	{
		if(ipr_referenciaRemota == null)
		{
			try
			{
				ipr_referenciaRemota = new com.bachue.snr.prosnr21.web.services.delegate.ParameterDelegate().getRemote();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
			}
		}

		return ipr_referenciaRemota;
	}

	/**
	 * Programa la hora de inicio de ejecucion de cada intancia de proceso de conciliacion.
	 *
	 * @param amss_jobData Parametros que debe ser enviados a cada Job
	 * @param apc_proceso Proceso de conciliación a programar hora de inico
	 * @return Expresión Cron con el intervalo de ejecución del Job
	 */
	private String iniciarJob(Map<String, String> amss_jobData, ProcesoConciliacion apc_proceso)
	{
		String ls_intervalo;

		ls_intervalo = null;

		if((apc_proceso != null) && (amss_jobData != null))
		{
			String ls_idProcesoConciliacion;

			ls_idProcesoConciliacion = apc_proceso.getIdProcesoConciliacion();

			try
			{
				if(StringUtils.isValidString(ls_idProcesoConciliacion))
				{
					amss_jobData.put(ConciliacionesJob.ID_PROCESO_CONCILIACION, ls_idProcesoConciliacion);

					{
						String        ls_hora;
						String        ls_minuto;
						StringBuilder lsb_intervalo;

						ls_hora           = "hora";
						ls_minuto         = "minuto";
						lsb_intervalo     = new StringBuilder("0 ");

						lsb_intervalo.append(ls_minuto);
						lsb_intervalo.append(" ");
						lsb_intervalo.append(ls_hora);
						lsb_intervalo.append(" * * ? *");

						ls_intervalo = lsb_intervalo.toString()
								                        .replaceAll(
								    ls_minuto,
								    NumericUtils.getLongWrapper(apc_proceso.getMinutoProgramacion()).toString()
								)
								                        .replaceAll(
								    ls_hora, NumericUtils.getLongWrapper(apc_proceso.getHoraProgramacion()).toString()
								);
					}

					{
						Constantes      lce_bloqueo;
						ParameterRemote lpr_parameter;
						String          ls_id;

						{
							StringBuilder lsb_id;

							lsb_id = new StringBuilder(
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.JOB_CONCILIACIONES_BLOQUEO
								);

							lsb_id.append("_");
							lsb_id.append(ls_idProcesoConciliacion);

							ls_id = lsb_id.toString();
						}

						lpr_parameter     = getReferenciaRemotaInterna();
						lce_bloqueo       = lpr_parameter.findConstantById(ls_id);

						if(lce_bloqueo == null)
						{
							lce_bloqueo = new Constantes();

							lce_bloqueo.setCaracter(EstadoCommon.N);
							lce_bloqueo.setIdConstante(ls_id);
							lce_bloqueo.setIdUsuarioCreacion(ConciliacionesJob.JOB_NAME);
							lce_bloqueo.setTipo(EstadoCommon.ACTIVO);
							lce_bloqueo.setActivo(EstadoCommon.S);

							{
								StringBuilder lsb_sb;

								lsb_sb = new StringBuilder();

								lsb_sb.append("CONSTANTE DE BLOQUEO PARA JOB DE CONCILIACION DEL PROCESO ");
								lsb_sb.append(ls_idProcesoConciliacion);
								lsb_sb.append(". N SIGNIFICA QUE NO ESTA BLOQUEADO");

								lce_bloqueo.setDescripcion(lsb_sb.toString());
							}

							lpr_parameter.salvarConstante(lce_bloqueo, true);
						}
					}
				}
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("createJob", le_e);
			}
		}

		return ls_intervalo;
	}

	/**
	 * Programa la hora de inicio de ejecucion de cada intancia de proceso de conciliacion.
	 *
	 * @param amss_jobData Parametros que debe ser enviados a cada Job
	 * @param apc_proceso Proceso de conciliación a programar hora de inico
	 * @return Expresión Cron con el intervalo de ejecución del Job
	 */
	private String iniciarJob(Map<String, String> amss_jobData, RPTPrograma apc_proceso)
	{
		String ls_intervalo;

		ls_intervalo = null;

		if((apc_proceso != null) && (amss_jobData != null))
		{
			String ls_idReporte;

			ls_idReporte = apc_proceso.getIdReporte();

			try
			{
				if(StringUtils.isValidString(ls_idReporte))
				{
					amss_jobData.put(ReportesCanciliacionJob.ID_REPORTES_CONCILIACION, ls_idReporte);

					{
						String        ls_hora;
						String        ls_minuto;
						String        ls_periodo;
						StringBuilder lsb_intervalo;

						ls_periodo        = apc_proceso.getIdPeriodicidad();
						ls_hora           = "hora";
						ls_minuto         = "minuto";
						lsb_intervalo     = new StringBuilder("0 ");

						lsb_intervalo.append(ls_minuto);
						lsb_intervalo.append(IdentificadoresCommon.ESPACIO_VACIO);
						lsb_intervalo.append(ls_hora);

						if(
						    StringUtils.isValidString(ls_periodo)
							    && ls_periodo.equalsIgnoreCase(PeriodicidadCommon.MENSUAL)
						)
							lsb_intervalo.append(" 1");
						else
							lsb_intervalo.append(" *");

						lsb_intervalo.append(" * ? *");

						ls_intervalo = lsb_intervalo.toString()
								                        .replaceAll(
								    ls_minuto,
								    NumericUtils.getLongWrapper(apc_proceso.getMinutosProgramada()).toString()
								)
								                        .replaceAll(
								    ls_hora, NumericUtils.getLongWrapper(apc_proceso.getHoraProgramada()).toString()
								);
					}

					{
						Constantes      lce_bloqueo;
						ParameterRemote lpr_parameter;
						String          ls_id;

						{
							StringBuilder lsb_id;

							lsb_id = new StringBuilder(
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.JOB_REPORTES_CONCILIACIONES_BLOQUEO
								);

							lsb_id.append("_");
							lsb_id.append(ls_idReporte);

							ls_id = lsb_id.toString();
						}

						lpr_parameter     = getReferenciaRemotaInterna();
						lce_bloqueo       = lpr_parameter.findConstantById(ls_id);

						if(lce_bloqueo == null)
						{
							lce_bloqueo = new Constantes();

							lce_bloqueo.setCaracter(EstadoCommon.N);
							lce_bloqueo.setIdConstante(ls_id);
							lce_bloqueo.setIdUsuarioCreacion(ReportesCanciliacionJob.JOB_NAME);
							lce_bloqueo.setTipo(EstadoCommon.ACTIVO);
							lce_bloqueo.setActivo(EstadoCommon.S);

							{
								StringBuilder lsb_sb;

								lsb_sb = new StringBuilder();

								lsb_sb.append("CONSTANTE DE BLOQUEO PARA JOB DE CONCILIACION DEL PROCESO ");
								lsb_sb.append(ls_idReporte);
								lsb_sb.append(". N SIGNIFICA QUE NO ESTA BLOQUEADO");

								lce_bloqueo.setDescripcion(lsb_sb.toString());
							}

							lpr_parameter.salvarConstante(lce_bloqueo, true);
						}
					}
				}
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("createJob", le_e);
			}
		}

		return ls_intervalo;
	}

	/**
	 * Inicialización de una tarea automática de conciliacion.
	 *
	 * @param apc_proceso Proceso de conciliación asociado a la tarea a iniciar
	 * @param amsjd_jobs Contenedor de Jobs donde se alojará el Job inicializado
	 * @param amst_triggers Contenedor de Triggers donde se alojará el Trigger inicializado
	 * @param as_cluster Cluster que se debe inicializar
	 * @param as_nodo Nodo que se está inicializando dentro del cluster
	 * @param ab_agendarJob Indica si la tarea automatica se debe iniciar
	 */
	private void iniciarJob(
	    ProcesoConciliacion apc_proceso, Map<String, org.quartz.JobDetail> amsjd_jobs,
	    Map<String, org.quartz.Trigger> amst_triggers, String as_cluster, String as_nodo, boolean ab_agendarJob
	)
	{
		if(
		    (apc_proceso != null) && (amsjd_jobs != null) && (amst_triggers != null)
			    && StringUtils.isValidString(as_cluster) && StringUtils.isValidString(as_nodo)
		)
		{
			Map<String, String> lmss_jobData;
			String              ls_intervalo;

			lmss_jobData     = new java.util.HashMap<String, String>();
			ls_intervalo     = iniciarJob(lmss_jobData, apc_proceso);

			if(StringUtils.isValidString(ls_intervalo))
				iniciarJob(
				    amsjd_jobs, amst_triggers, lmss_jobData, ls_intervalo, as_cluster, as_nodo, ConciliacionesJob.class,
				    ConciliacionesJob.getJobName(apc_proceso.getIdProcesoConciliacion()), ab_agendarJob
				);
		}
	}

	/**
	 * Inicialización de una tarea automática de conciliacion.
	 *
	 * @param arptp_programa Proceso de conciliación asociado a la tarea a iniciar
	 * @param amsjd_jobs Contenedor de Jobs donde se alojará el Job inicializado
	 * @param amst_triggers Contenedor de Triggers donde se alojará el Trigger inicializado
	 * @param as_cluster Cluster que se debe inicializar
	 * @param as_nodo Nodo que se está inicializando dentro del cluster
	 * @param ab_agendarJob Indica si la tarea automatica se debe iniciar
	 */
	private void iniciarJob(
	    RPTPrograma arptp_programa, Map<String, org.quartz.JobDetail> amsjd_jobs,
	    Map<String, org.quartz.Trigger> amst_triggers, String as_cluster, String as_nodo, boolean ab_agendarJob
	)
	{
		if(
		    (arptp_programa != null) && (amsjd_jobs != null) && (amst_triggers != null)
			    && StringUtils.isValidString(as_cluster) && StringUtils.isValidString(as_nodo)
		)
		{
			Map<String, String> lmss_jobData;
			String              ls_intervalo;

			lmss_jobData     = new java.util.HashMap<String, String>();
			ls_intervalo     = iniciarJob(lmss_jobData, arptp_programa);

			if(StringUtils.isValidString(ls_intervalo))
				iniciarJob(
				    amsjd_jobs, amst_triggers, lmss_jobData, ls_intervalo, as_cluster, as_nodo,
				    ReportesCanciliacionJob.class, ReportesCanciliacionJob.getJobName(arptp_programa.getIdReporte()),
				    ab_agendarJob
				);
		}
	}
}
