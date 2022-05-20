package com.bachue.snr.prosnr01.web.Scheduler;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.common.constants.Quartz;

import java.util.Map;


/**
 * Agendador de tareas automáticas QUARTZ para componente Core Bachue
 *
 * @author Edgar Prieto
 *
 */
public class BachueScheduler extends com.bachue.snr.common.scheduler.SNRClusterScheduler
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BachueScheduler.class);

	/** {@inheritDoc} */
	protected com.bachue.snr.ejb.session.stateless.reference.SchedulerRemote getReferenciaRemota()
	{
		if(isr_referenciaRemota == null)
		{
			try
			{
				isr_referenciaRemota = new com.bachue.snr.prosnr01.web.delegate.reference.ReferenceDelegate().getRemote();
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

					if(ls_nombreJob.equals(CancelacionJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CancelacionJob.class, ls_nombreJob);

					if(ls_nombreJob.equals(ReliquidacionJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, ReliquidacionJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(CertificadosJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CertificadosJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(CierreExpedienteJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, CierreExpedienteJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(ConstanciaReproduccionJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, ConstanciaReproduccionJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(CrearTurnoJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CrearTurnoJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(CuentaCuposJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CuentaCuposJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(DevolucionDineroJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, DevolucionDineroJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(EntregaDocumentosCorreoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EntregaDocumentosCorreoElectronicoJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EntregaDocumentosCorrespondenciaJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EntregaDocumentosCorrespondenciaJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioAvisoJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioAvisoJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(EnvioAvisoWebJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioAvisoWebJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioCitatorioJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioCitatorioJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioComunicadoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioComunicadoJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioCorrespondenciaElectronica206Job.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioCorrespondenciaElectronica206Job.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioCorrespondenciaElectronicaJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioCorrespondenciaElectronicaJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioCorrespondenciaFisicaJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioCorrespondenciaFisicaJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioDocumentoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioDocumentoElectronicoJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(EnvioNotificacionPagoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, EnvioNotificacionPagoJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(FirmaMasivaJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, FirmaMasivaJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(VisitasJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, VisitasJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(FirmarDocumentoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, FirmarDocumentoElectronicoJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(GeneracionCitatorioJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, GeneracionCitatorioJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(GenerarDocumentosJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, GenerarDocumentosJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(GenerarReciboCajaJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, GenerarReciboCajaJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(GobernacionesJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, GobernacionesJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(NotificarCatastroPOJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, NotificarCatastroPOJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(NotificarCatastroSOCPJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, NotificarCatastroSOCPJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(NotificarCatastroSOSPJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, NotificarCatastroSOSPJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(NotificarCatastroTOJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, NotificarCatastroTOJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(RealizarNotificacionJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, RealizarNotificacionJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(RecepcionDocumentosJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, RecepcionDocumentosJob.class, ls_nombreJob
						);
					else if(ls_nombreJob.equals(ReportesJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, ReportesJob.class, ls_nombreJob);
					else if(ls_nombreJob.equals(SolicitarDocumentoElectronicoJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, SolicitarDocumentoElectronicoJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(ValidacionPersonasNotificadasJob.getId()))
						iniciarJob(
						    amsjd_jobs, amst_triggers, as_cluster, as_nodo, ValidacionPersonasNotificadasJob.class,
						    ls_nombreJob
						);
					else if(ls_nombreJob.equals(CRPSJob.getId()))
						iniciarJob(amsjd_jobs, amst_triggers, as_cluster, as_nodo, CRPSJob.class, ls_nombreJob);
				}
			}
		}
	}
}
