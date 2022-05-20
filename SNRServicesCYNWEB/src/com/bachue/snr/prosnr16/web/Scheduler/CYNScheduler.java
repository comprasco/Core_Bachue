package com.bachue.snr.prosnr16.web.Scheduler;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.common.constants.Quartz;

import java.util.Map;


/**
 * Agendador de tareas automáticas QUARTZ para componente CYN
 *
 * @author Edgar Prieto
 *
 */
public class CYNScheduler extends com.bachue.snr.common.scheduler.SNRClusterScheduler
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CYNScheduler.class);

	/** {@inheritDoc} */
	protected com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote getReferenciaRemota()
	{
		if(isr_referenciaRemota == null)
		{
			try
			{
				isr_referenciaRemota = new com.bachue.snr.prosnr16.web.services.delegate.ReferenceDelegate().getRemote();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
			}
		}

		return isr_referenciaRemota;
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

					if(ls_nombreJob.equals(AcuseRecibidoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, AcuseRecibidoElectronicoJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(AcuseRecibidoFisicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, AcuseRecibidoFisicoJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(MotorEnvioElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, MotorEnvioElectronicoJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(MotorEnvioFisicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, MotorEnvioFisicoJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(MotorEnvioSmsJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, MotorEnvioSmsJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(ValidarAcuseRecibidoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, ValidarAcuseRecibidoElectronicoJob.class,
						    ls_nombreJob
						);
				}
			}
		}
	}
}
