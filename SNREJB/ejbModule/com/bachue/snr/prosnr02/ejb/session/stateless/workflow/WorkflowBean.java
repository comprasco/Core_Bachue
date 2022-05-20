package com.bachue.snr.prosnr02.ejb.session.stateless.workflow;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import com.bachue.snr.prosnr02.business.workflow.WorkflowBusiness;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;
import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las acciones ReferenceBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "Workflow", mappedName = "workflowMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class WorkflowBean implements WorkflowRemote
{
	/** Propiedad irb business. */
	private ReferenceBusiness irb_business;

	/** Propiedad irb business. */
	private WorkflowBusiness iwb_business;

	/** {@inheritdoc} */
	public Collection<ProcesoTrabajo> analizarContenidoDefiniciones(
	    String as_definicion, int ai_version, int ai_modo, boolean ab_nuevaVersion, String as_idUsuarioCreacion,
	    String as_localIp, String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<ProcesoTrabajo> lcpt_proceso;

		lsw_watch        = Logger.getNewStopWatch();
		lcpt_proceso     = getWorkflowBusiness()
				                   .analizarContenidoDefiniciones(
				    as_definicion, ai_version, ai_modo, ab_nuevaVersion, as_idUsuarioCreacion, as_ipCreacion
				);

		Logger.log(
		    lsw_watch, "WorkflowBean", "analizarContenidoDefiniciones", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcpt_proceso
		);

		return lcpt_proceso;
	}

	/** {@inheritdoc} */
	public void aprobar(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getWorkflowBusiness().aprobar(as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "WorkflowBean", "aprobar", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<Trazabilidad> cargarInfoBandejaConsulta(
	    Trazabilidad at_parametros, boolean ab_onlyTurno, String as_idUsuarioCreacion, String as_localIp,
	    String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<Trazabilidad> lcpt_proceso;

		lsw_watch        = Logger.getNewStopWatch();
		lcpt_proceso     = getWorkflowBusiness().cargarInfoBandejaConsulta(at_parametros, ab_onlyTurno);

		Logger.log(
		    lsw_watch, "WorkflowBean", "cargarInfoBandejaConsultaTrazabilidad", as_idUsuarioCreacion, as_localIp,
		    as_ipCreacion, null
		);

		return lcpt_proceso;
	}

	/** {@inheritdoc} */
	public Collection<Opcion> cargarOpcionesMenu(
	    String as_userId, String as_idComponente, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<Opcion> lco_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lco_datos     = getWorkflowBusiness().cargarOpcionesMenu(as_userId, as_idComponente);

		Logger.log(lsw_watch, "WorkflowBean", "cargarOpcionesMenu", as_userId, as_localIp, as_remoteIp, lco_datos);

		return lco_datos;
	}

	/** {@inheritdoc} */
	public void enviarAlAprobador(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getWorkflowBusiness().enviarAlAprobador(as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "WorkflowBean", "enviarAlAprobador", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<EtapaTrabajo> findAllEtapas(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<EtapaTrabajo> lcet_etapas;

		lsw_watch       = Logger.getNewStopWatch();
		lcet_etapas     = getWorkflowBusiness().findAllEtapas();

		Logger.log(
		    lsw_watch, "WorkflowBean", "findAllEtapas", as_idUsuarioCreacion, as_localIp, as_ipCreacion, lcet_etapas
		);

		return lcet_etapas;
	}

	/** {@inheritdoc} */
	public Collection<Fases> findAllFases()
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Fases> lcf_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcf_datos = getWorkflowBusiness().findAllFases();

		Logger.log(lsw_watch, "ParameterBean", "findAllFases", null, null, null, lcf_datos);

		return lcf_datos;
	}

	/** {@inheritdoc} */
	public Collection<ReglaNegocio> findAllReglas(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<ReglaNegocio> lcrn_reglas;

		lsw_watch       = Logger.getNewStopWatch();
		lcrn_reglas     = getWorkflowBusiness().findAllReglas();

		Logger.log(
		    lsw_watch, "WorkflowBean", "findAllReglas", as_idUsuarioCreacion, as_localIp, as_ipCreacion, lcrn_reglas
		);

		return lcrn_reglas;
	}

	/** {@inheritdoc} */
	public Collection<SubProcesoVersionTrabajo> findAllSubProcesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<SubProcesoVersionTrabajo> lcspt_subProcesos;

		lsw_watch             = Logger.getNewStopWatch();
		lcspt_subProcesos     = getWorkflowBusiness().findAllSubProcesos();

		Logger.log(
		    lsw_watch, "WorkflowBean", "findAllSubProcesos", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcspt_subProcesos
		);

		return lcspt_subProcesos;
	}

	/** {@inheritdoc} */
	public Collection<UnidadTiempoVencimiento> findAllUnidadTiempoVencimiento()
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<UnidadTiempoVencimiento> lcutv_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcutv_datos = getWorkflowBusiness().findAllUnidadTiempoVencimiento();

		Logger.log(lsw_watch, "ParameterBean", "findAllUnidadTiempoVencimiento", null, null, null, lcutv_datos);

		return lcutv_datos;
	}

	/** {@inheritdoc} */
	public Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getWorkflowBusiness().findConstantesById(as_parametro);

		Logger.log(lsw_watch, "WorkflowBean", "findConstantesById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritdoc} */
	public String findNewDiagram(String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_dato;

		lsw_watch     = Logger.getNewStopWatch();
		ls_dato       = getWorkflowBusiness().findNewDiagram();

		Logger.log(lsw_watch, "WorkflowBean", "findNewDiagram", as_idUsuarioCreacion, as_localIp, as_ipCreacion, null);

		return ls_dato;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoTrabajo> findProcesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<ProcesoTrabajo> lcpt_procesos;

		lsw_watch         = Logger.getNewStopWatch();
		lcpt_procesos     = getWorkflowBusiness().findProcesos();

		Logger.log(
		    lsw_watch, "WorkflowBean", "findProcesos", as_idUsuarioCreacion, as_localIp, as_ipCreacion, lcpt_procesos
		);

		return lcpt_procesos;
	}

	/** {@inheritdoc} */
	public Collection<ProcesoTrabajo> findProcesosAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<ProcesoTrabajo> lcpt_procesos;

		lsw_watch         = Logger.getNewStopWatch();
		lcpt_procesos     = getWorkflowBusiness().findProcesosAprobacion();

		Logger.log(
		    lsw_watch, "WorkflowBean", "findProcesosAprobacion", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcpt_procesos
		);

		return lcpt_procesos;
	}

	/** {@inheritdoc} */
	public Collection<SubProcesoTrabajo> findSubProcesosAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lsw_watch             = Logger.getNewStopWatch();
		lcspt_subProcesos     = getWorkflowBusiness().findSubProcesosAprobacion(as_idProceso);

		Logger.log(
		    lsw_watch, "WorkflowBean", "findSubProcesosAprobacion", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcspt_subProcesos
		);

		return lcspt_subProcesos;
	}

	/** {@inheritdoc} */
	public Collection<SubProcesoTrabajo> findSubprocesos(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<SubProcesoTrabajo> lcspt_subProcesos;

		lsw_watch             = Logger.getNewStopWatch();
		lcspt_subProcesos     = getWorkflowBusiness().findSubprocesos(as_idProceso);

		Logger.log(
		    lsw_watch, "WorkflowBean", "findSubprocesos", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcspt_subProcesos
		);

		return lcspt_subProcesos;
	}

	/** {@inheritdoc} */
	public Usuario findUserById(Usuario ate_parametros)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Usuario   lte_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lte_datos     = getWorkflowBusiness().findUserById(ate_parametros);

		Logger.log(lsw_watch, "WorkflowBean", "findUserById", null, null, null, null);

		return lte_datos;
	}

	/** {@inheritdoc} */
	public Collection<SubProcesoVersionTrabajo> findVersiones(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso,
	    String as_idSubproceso
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<SubProcesoVersionTrabajo> lcspvt_versiones;

		lsw_watch            = Logger.getNewStopWatch();
		lcspvt_versiones     = getWorkflowBusiness().findVersiones(as_idProceso, as_idSubproceso);

		Logger.log(
		    lsw_watch, "WorkflowBean", "findVersiones", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcspvt_versiones
		);

		return lcspvt_versiones;
	}

	/** {@inheritdoc} */
	public Collection<SubProcesoVersionTrabajo> findVersionesAprobacion(
	    String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion, String as_idProceso,
	    String as_idSubproceso
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<SubProcesoVersionTrabajo> lcsvt_datos;

		lsw_watch       = Logger.getNewStopWatch();
		lcsvt_datos     = getWorkflowBusiness().findVersionesAprobacion(as_idProceso, as_idSubproceso);

		Logger.log(
		    lsw_watch, "WorkflowBean", "findVersionesAprobacion", as_idUsuarioCreacion, as_localIp, as_ipCreacion,
		    lcsvt_datos
		);

		return lcsvt_datos;
	}

	/** {@inheritDoc} */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_constante;

		lsw_watch        = Logger.getNewStopWatch();
		ls_constante     = getReferenceBusiness().obtenerCaracterConstante(as_idConstante);

		Logger.log(lsw_watch, "WorkflowBean", "obtenerCaracterConstante", null, null, null, null);

		return ls_constante;
	}

	/**
	 * Obtener monitoreo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idTurno de as id turno
	 * @param as_idUsuarioCreacion de as id usuario creacion
	 * @param as_localIp de as local ip
	 * @param as_ipCreacion de as ip creacion
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String obtenerMonitoreo(
	    String as_idSolicitud, String as_idTurno, String as_idUsuarioCreacion, String as_localIp, String as_ipCreacion
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_xml;

		lsw_watch     = Logger.getNewStopWatch();
		ls_xml        = getWorkflowBusiness().obtenerMonitoreo(as_idSolicitud, as_idTurno);

		Logger.log(
		    lsw_watch, "WorkflowBean", "obtenerMonitoreo", as_idUsuarioCreacion, as_localIp, as_ipCreacion, null
		);

		return ls_xml;
	}

	/** {@inheritdoc} */
	public void retornarAEstadoDevuelto(
	    String as_idProceso, String as_idSubProceso, int ai_version, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getWorkflowBusiness().retornarAEstadoDevuelto(
		    as_idProceso, as_idSubProceso, ai_version, as_userId, as_remoteIp
		);

		Logger.log(lsw_watch, "WorkflowBean", "retornarAEstadoDevuelto", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public boolean verificarAutenticacionSegundoFactor(String as_parametro)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getReferenceBusiness().verificarAutenticacionSegundoFactor(as_parametro);

		Logger.log(lsw_watch, "WorkflowBean", "verificarAutenticacionSegundoFactor", null, null, null, null);

		return lc_datos;
	}

	/**
	 * Retorna el valor de reference business.
	 *
	 * @return el valor de reference business
	 */
	private ReferenceBusiness getReferenceBusiness()
	{
		if(irb_business == null)
			irb_business = new ReferenceBusiness();

		return irb_business;
	}

	/**
	 * Retorna el valor de reference business.
	 *
	 * @return el valor de reference business
	 */
	private WorkflowBusiness getWorkflowBusiness()
	{
		if(iwb_business == null)
			iwb_business = new WorkflowBusiness();

		return iwb_business;
	}
}
