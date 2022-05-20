package com.bachue.snr.prosnr02.bean.workflow;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import com.bachue.snr.prosnr02.common.constants.ErrorKeys;
import com.bachue.snr.prosnr02.common.constants.EstadoFlujoCommon;
import com.bachue.snr.prosnr02.common.constants.MessagesKeys;
import com.bachue.snr.prosnr02.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr02.common.constants.ScriptsCommon;

import com.bachue.snr.prosnr02.ejb.session.stateless.workflow.WorkflowRemote;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;
import com.bachue.snr.prosnr02.model.acc.ProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoTrabajo;
import com.bachue.snr.prosnr02.model.acc.SubProcesoVersionTrabajo;
import com.bachue.snr.prosnr02.model.pgn.ReglaNegocio;

import com.bachue.snr.prosnr02.web.bean.BaseBean;

import com.google.gson.Gson;

import org.primefaces.PrimeFaces;

import org.primefaces.context.PrimeRequestContext;

import org.primefaces.event.FlowEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de eventos de workflow.
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanWorkflow")
@SessionScoped
public class BeanWorkflow extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8279908205288832688L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanWorkflow.class, ProyectosCommon.WORKFLOW_02);

	/** Propiedad icet etapas. */
	private Collection<EtapaTrabajo> icet_etapas;

	/** Propiedad icf fases. */
	private Collection<Fases> icf_fases;

	/** Propiedad icpt procesos. */
	private Collection<ProcesoTrabajo> icpt_procesos;

	/** Propiedad icrn reglas. */
	private Collection<ReglaNegocio> icrn_reglas;

	/** Propiedad icrn reglas agregadas. */
	private Collection<ReglaNegocio> icrn_reglasAgregadas;

	/** Propiedad icst all sub procesos. */
	private Collection<SubProcesoVersionTrabajo> icspvt_allSubProcesos;

	/** Propiedad icst sub procesos filtrados. */
	private Collection<SubProcesoVersionTrabajo> icspvt_subProcesosFiltrados;

	/** Propiedad icspvt versiones. */
	private Collection<SubProcesoVersionTrabajo> icspvt_versiones;

	/** Propiedad icst sub procesos. */
	private Collection<SubProcesoTrabajo> icst_subProcesos;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_turnos;

	/** Propiedad ipt proceso trabajo seleccionado. */
	private ProcesoTrabajo ipt_procesoTrabajoSeleccionado;

	/** Propiedad cs BOTONES. */
	private final String cs_BOTONES = "botonesBandeja";

	/** Propiedad cs FORM. */
	private final String cs_FORM = "formDialogOpenDiagram:";

	/** Propiedad cs FOR M WORKFLOW. */
	private final String cs_FORM_WORKFLOW = "fPrincipal:";

	/** Propiedad cs GROWL. */
	private final String cs_GROWL = "globalMsg";

	/** Propiedad cs PARAMETRO MONITOREO. */
	private final String cs_PARAMETRO_MONITOREO = "monitoreo";

	/** Propiedad cs PARAMETR O REGLAS. */
	private final String cs_PARAMETRO_REGLAS = "reglas";

	/** Propiedad cs PARAMETRO XML. */
	private final String cs_PARAMETRO_XML = "xml";

	/** Propiedad cs TAB PROCESO. */
	private final String cs_TAB_PROCESO = "procesoTab";

	/** Propiedad cs TAB SUBPROCESO. */
	private final String cs_TAB_SUBPROCESO = "subprocesoTab";

	/** Propiedad cs TAB VERSION. */
	private final String cs_TAB_VERSION = "versionTab";

	/** Propiedad is id etapa. */
	private String is_idEtapa;

	/** Propiedad is id proceso seleccionado. */
	private String is_idProcesoSeleccionado;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre proceso seleccionado. */
	private String is_nombreProcesoSeleccionado;

	/** Propiedad is nombre sub proceso seleccionado. */
	private String is_nombreSubProcesoSeleccionado;

	/** Propiedad is tab actual. */
	private String is_tabActual;

	/** Propiedad is xml. */
	private String is_xml;

	/** Propiedad ispt sub proceso trabajo seleccionado. */
	private SubProcesoTrabajo ispt_subProcesoTrabajoSeleccionado;

	/** Propiedad ispvt subproceso version trabajo seleccionado. */
	private SubProcesoVersionTrabajo ispvt_subprocesoVersionTrabajoSeleccionado;

	/** Propiedad iwr workflow remote. */
	@EJB
	private WorkflowRemote iwr_workflowRemote;

	/** Propiedad ab salvar aprobador. */
	private boolean ab_salvarAprobador;

	/** Propiedad iwr workflow remote. */
	private boolean ib_bloquearDatosProceso;

	/** Propiedad ib cargar datos proceso. */
	private boolean ib_cargarDatosProceso;

	/** Propiedad ib cargar datos subproceso. */
	private boolean ib_cargarDatosSubproceso;

	/** Propiedad ib desde bandeja. */
	private boolean ib_desdeBandeja;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/** Propiedad ib monitoreo. */
	private boolean ib_monitoreo;

	/** Propiedad ib nueva version. */
	private boolean ib_nuevaVersion;

	/** Propiedad ii step. */
	private int ii_step;

	/**
	 * Modifica el valor de AllSubProcesos.
	 *
	 * @param acspvt_acspvt de acspvt acspvt
	 */
	public void setAllSubProcesos(Collection<SubProcesoVersionTrabajo> acspvt_acspvt)
	{
		icspvt_allSubProcesos = acspvt_acspvt;
	}

	/**
	 * Retorna Objeto o variable de valor all sub procesos.
	 *
	 * @return el valor de all sub procesos
	 */
	public Collection<SubProcesoVersionTrabajo> getAllSubProcesos()
	{
		return icspvt_allSubProcesos;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Da valor de bloquear datos proceso.
	 *
	 * @param ab_b nuevo valor de bloquear datos proceso
	 */
	public void setBloquearDatosProceso(boolean ab_b)
	{
		ib_bloquearDatosProceso = ab_b;
	}

	/**
	 * Retorna is bloquear datos proceso.
	 *
	 * @return true, sí is bloquear datos proceso
	 */
	public boolean isBloquearDatosProceso()
	{
		return ib_bloquearDatosProceso;
	}

	/**
	 * Da valor de cargar datos proceso.
	 *
	 * @param ab_b nuevo valor de cargar datos proceso
	 */
	public void setCargarDatosProceso(boolean ab_b)
	{
		ib_cargarDatosProceso = ab_b;
	}

	/**
	 * Retorna is cargar datos proceso.
	 *
	 * @return true, sí is cargar datos proceso
	 */
	public boolean isCargarDatosProceso()
	{
		return ib_cargarDatosProceso;
	}

	/**
	 * Da valor de cargar datos subproceso.
	 *
	 * @param ab_b nuevo valor de cargar datos subproceso
	 */
	public void setCargarDatosSubproceso(boolean ab_b)
	{
		ib_cargarDatosSubproceso = ab_b;
	}

	/**
	 * Retorna is cargar datos subproceso.
	 *
	 * @return true, sí is cargar datos subproceso
	 */
	public boolean isCargarDatosSubproceso()
	{
		return ib_cargarDatosSubproceso;
	}

	/**
	 * Da valor de desde bandeja.
	 *
	 * @param ab_b nuevo valor de desde bandeja
	 */
	public void setDesdeBandeja(boolean ab_b)
	{
		ib_desdeBandeja = ab_b;
	}

	/**
	 * Retorna is desde bandeja.
	 *
	 * @return true, sí is desde bandeja
	 */
	public boolean isDesdeBandeja()
	{
		return ib_desdeBandeja;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param ab_b asigna el valor a la propiedad estado
	 */
	public void setEstado(boolean ab_b)
	{
		ib_estado = ab_b;
	}

	/**
	 * Valida la propiedad estado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado
	 */
	public boolean isEstado()
	{
		return ib_estado;
	}

	/**
	 * Da valor de etapas.
	 *
	 * @param acet_cet nuevo valor de etapas
	 */
	public void setEtapas(Collection<EtapaTrabajo> acet_cet)
	{
		icet_etapas = acet_cet;
	}

	/**
	 * Obtiene de etapas.
	 *
	 * @return de etapas
	 */
	public Collection<EtapaTrabajo> getEtapas()
	{
		return icet_etapas;
	}

	/**
	 * Modifica el valor de Fases.
	 *
	 * @param acf_fases de icf fases
	 */
	public void setFases(Collection<Fases> acf_fases)
	{
		icf_fases = acf_fases;
	}

	/**
	 * Retorna Objeto o variable de valor fases.
	 *
	 * @return el valor de fases
	 */
	public Collection<Fases> getFases()
	{
		return icf_fases;
	}

	/**
	 * Da valor de id etapa.
	 *
	 * @param as_s nuevo valor de id etapa
	 */
	public void setIdEtapa(String as_s)
	{
		is_idEtapa = as_s;
	}

	/**
	 * Obtiene de id etapa.
	 *
	 * @return de id etapa
	 */
	public String getIdEtapa()
	{
		return is_idEtapa;
	}

	/**
	 * Da valor de Id proceso seleccionado.
	 *
	 * @param as_s nuevo valor de Id proceso seleccionado
	 */
	public void setIdProcesoSeleccionado(String as_s)
	{
		is_idProcesoSeleccionado = as_s;
	}

	/**
	 * Obtiene de Id proceso seleccionado.
	 *
	 * @return de Id proceso seleccionado
	 */
	public String getIdProcesoSeleccionado()
	{
		return is_idProcesoSeleccionado;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/**
	 * Modifica el valor de Monitoreo.
	 *
	 * @param ab_b de ab b
	 */
	public void setMonitoreo(boolean ab_b)
	{
		ib_monitoreo = ab_b;
	}

	/**
	 * Valida la propiedad monitoreo.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en
	 * monitoreo
	 */
	public boolean isMonitoreo()
	{
		return ib_monitoreo;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_nir asigna el valor a la propiedad nir
	 */
	public void setNir(String as_nir)
	{
		is_nir = as_nir;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Da valor de nombre proceso seleccionado.
	 *
	 * @param as_s nuevo valor de nombre proceso seleccionado
	 */
	public void setNombreProcesoSeleccionado(String as_s)
	{
		is_nombreProcesoSeleccionado = as_s;
	}

	/**
	 * Obtiene de nombre proceso seleccionado.
	 *
	 * @return de nombre proceso seleccionado
	 */
	public String getNombreProcesoSeleccionado()
	{
		return is_nombreProcesoSeleccionado;
	}

	/**
	 * Da valor de nombre sub proceso seleccionado.
	 *
	 * @param as_s nuevo valor de nombre sub proceso seleccionado
	 */
	public void setNombreSubProcesoSeleccionado(String as_s)
	{
		is_nombreSubProcesoSeleccionado = as_s;
	}

	/**
	 * Obtiene de nombre sub proceso seleccionado.
	 *
	 * @return de nombre sub proceso seleccionado
	 */
	public String getNombreSubProcesoSeleccionado()
	{
		return is_nombreSubProcesoSeleccionado;
	}

	/**
	 * Da valor de nueva version.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setNuevaVersion(boolean ab_b)
	{
		ib_nuevaVersion = ab_b;
	}

	/**
	 * Retorna is nueva version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isNuevaVersion()
	{
		return ib_nuevaVersion;
	}

	/**
	 * Da valor de proceso trabajo seleccionado.
	 *
	 * @param apt_pt nuevo valor de proceso trabajo seleccionado
	 */
	public void setProcesoTrabajoSeleccionado(ProcesoTrabajo apt_pt)
	{
		ipt_procesoTrabajoSeleccionado = apt_pt;
	}

	/**
	 * Obtiene de proceso trabajo seleccionado.
	 *
	 * @return de proceso trabajo seleccionado
	 */
	public ProcesoTrabajo getProcesoTrabajoSeleccionado()
	{
		return ipt_procesoTrabajoSeleccionado;
	}

	/**
	 * Da valor de procesos.
	 *
	 * @param acpt_cpt nuevo valor de procesos
	 */
	public void setProcesos(Collection<ProcesoTrabajo> acpt_cpt)
	{
		icpt_procesos = acpt_cpt;
	}

	/**
	 * Obtiene de procesos.
	 *
	 * @return de procesos
	 */
	public Collection<ProcesoTrabajo> getProcesos()
	{
		return icpt_procesos;
	}

	/**
	 * Modifica el valor de Reglas.
	 *
	 * @param acrn_crn de acrn crn
	 */
	public void setReglas(Collection<ReglaNegocio> acrn_crn)
	{
		icrn_reglas = acrn_crn;
	}

	/**
	 * Retorna Objeto o variable de valor reglas.
	 *
	 * @return el valor de reglas
	 */
	public Collection<ReglaNegocio> getReglas()
	{
		return icrn_reglas;
	}

	/**
	 * Modifica el valor de ReglasAgregadas.
	 *
	 * @param acrn_crn de acrn crn
	 */
	public void setReglasAgregadas(Collection<ReglaNegocio> acrn_crn)
	{
		icrn_reglasAgregadas = acrn_crn;
	}

	/**
	 * Retorna Objeto o variable de valor reglas agregadas.
	 *
	 * @return el valor de reglas agregadas
	 */
	public Collection<ReglaNegocio> getReglasAgregadas()
	{
		return icrn_reglasAgregadas;
	}

	/**
	 * Da valor de salvar aprobador.
	 *
	 * @param ab_b nuevo valor de salvar aprobador
	 */
	public void setSalvarAprobador(boolean ab_b)
	{
		ab_salvarAprobador = ab_b;
	}

	/**
	 * Retorna is salvar aprobador.
	 *
	 * @return true, sí is salvar aprobador
	 */
	public boolean isSalvarAprobador()
	{
		return ab_salvarAprobador;
	}

	/**
	 * Modifica el valor de SubProcesosFiltrados.
	 *
	 * @param acspvt_cspvt de acspvt cspvt
	 */
	public void setSubProcesosFiltrados(Collection<SubProcesoVersionTrabajo> acspvt_cspvt)
	{
		icspvt_subProcesosFiltrados = acspvt_cspvt;
	}

	/**
	 * Retorna Objeto o variable de valor sub procesos filtrados.
	 *
	 * @return el valor de sub procesos filtrados
	 */
	public Collection<SubProcesoVersionTrabajo> getSubProcesosFiltrados()
	{
		return icspvt_subProcesosFiltrados;
	}

	/**
	 * Da valor de subproceso trabajo seleccionado.
	 *
	 * @param aspt_spt nuevo valor de subproceso trabajo seleccionado
	 */
	public void setSubprocesoTrabajoSeleccionado(SubProcesoTrabajo aspt_spt)
	{
		ispt_subProcesoTrabajoSeleccionado = aspt_spt;
	}

	/**
	 * Obtiene de subproceso trabajo seleccionado.
	 *
	 * @return de subproceso trabajo seleccionado
	 */
	public SubProcesoTrabajo getSubprocesoTrabajoSeleccionado()
	{
		return ispt_subProcesoTrabajoSeleccionado;
	}

	/**
	 * Da valor de subproceso version trabajo seleccionado.
	 *
	 * @param aspvt_spvt nuevo valor de subproceso version trabajo seleccionado
	 */
	public void setSubprocesoVersionTrabajoSeleccionado(SubProcesoVersionTrabajo aspvt_spvt)
	{
		ispvt_subprocesoVersionTrabajoSeleccionado = aspvt_spvt;
	}

	/**
	 * Obtiene de subproceso version trabajo seleccionado.
	 *
	 * @return de subproceso version trabajo seleccionado
	 */
	public SubProcesoVersionTrabajo getSubprocesoVersionTrabajoSeleccionado()
	{
		return ispvt_subprocesoVersionTrabajoSeleccionado;
	}

	/**
	 * Da valor de subprocesos.
	 *
	 * @param acspt_cspt nuevo valor de subprocesos
	 */
	public void setSubprocesos(Collection<SubProcesoTrabajo> acspt_cspt)
	{
		icst_subProcesos = acspt_cspt;
	}

	/**
	 * Obtiene de subprocesos.
	 *
	 * @return de subprocesos
	 */
	public Collection<SubProcesoTrabajo> getSubprocesos()
	{
		return icst_subProcesos;
	}

	/**
	 * Da valor de tab actual.
	 *
	 * @param as_s nuevo valor de tab actual
	 */
	public void setTabActual(String as_s)
	{
		is_tabActual = as_s;
	}

	/**
	 * Obtiene de tab actual.
	 *
	 * @return de tab actual
	 */
	public String getTabActual()
	{
		return is_tabActual;
	}

	/**
	 * Modifica el valor de Turnos.
	 *
	 * @param act_ct de act ct
	 */
	public void setTurnos(Collection<Trazabilidad> act_ct)
	{
		ict_turnos = act_ct;
	}

	/**
	 * Retorna Objeto o variable de valor turnos.
	 *
	 * @return el valor de turnos
	 */
	public Collection<Trazabilidad> getTurnos()
	{
		return ict_turnos;
	}

	/**
	 * Da valor de versiones.
	 *
	 * @param acspvt_cspvt nuevo valor de versiones
	 */
	public void setVersiones(Collection<SubProcesoVersionTrabajo> acspvt_cspvt)
	{
		icspvt_versiones = acspvt_cspvt;
	}

	/**
	 * Obtiene de versiones.
	 *
	 * @return de versiones
	 */
	public Collection<SubProcesoVersionTrabajo> getVersiones()
	{
		return icspvt_versiones;
	}

	/**
	 * Da valor de xml.
	 *
	 * @param as_s nuevo valor de xml
	 */
	public void setXml(String as_s)
	{
		is_xml = as_s;
	}

	/**
	 * Obtiene de xml.
	 *
	 * @return de xml
	 */
	public String getXml()
	{
		return is_xml;
	}

	/**
	 * Accion aprobar.
	 *
	 * @return de string
	 */
	public String accionAprobar()
	{
		String ls_return;

		ls_return = NavegacionCommon.WORKFLOW_APROBADOR;

		try
		{
			SubProcesoVersionTrabajo lspvt_version;

			lspvt_version = getSubprocesoVersionTrabajoSeleccionado();

			if(lspvt_version != null)
			{
				if(lspvt_version != null)
					iwr_workflowRemote.aprobar(
					    lspvt_version.getIdProceso(), lspvt_version.getIdSubproceso(), lspvt_version.getVersion(),
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				ls_return = retornarBandeja();
			}
			else
				throw new B2BException("Ocurrió un error cargado la versión de subproceso, contacte con soporte");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionAprobar", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Accion enviar al aprobador.
	 *
	 * @return de string
	 */
	public String accionEnviarAlAprobador()
	{
		String ls_return;

		ls_return = NavegacionCommon.WORKFLOW;

		try
		{
			ProcesoTrabajo lpt_procesoTrabajo;

			lpt_procesoTrabajo = getProcesoTrabajoSeleccionado();

			if(lpt_procesoTrabajo != null)
			{
				SubProcesoTrabajo lspt_subproceso;

				lspt_subproceso = lpt_procesoTrabajo.getSubProceso();

				if(lspt_subproceso != null)
				{
					SubProcesoVersionTrabajo lspvt_version;

					lspvt_version = lspt_subproceso.getVersion();

					if(lspvt_version != null)
						iwr_workflowRemote.enviarAlAprobador(
						    lpt_procesoTrabajo.getId(), lspt_subproceso.getIdSubproceso(), lspvt_version.getVersion(),
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					ls_return = NavegacionCommon.BANDEJA_DIAGRAMAS_EXISTENTES;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionEnviarAlAprobador", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Accion nuevo.
	 *
	 * @return de string
	 */
	public String accionNuevo()
	{
		String ls_return;
		String ls_tabActual;

		ls_return        = null;
		ls_tabActual     = getTabActual();

		try
		{
			if(StringUtils.isValidString(ls_tabActual))
			{
				switch(ls_tabActual)
				{
					case cs_TAB_PROCESO:
						setBloquearDatosProceso(false);
						setCargarDatosProceso(false);
						setCargarDatosSubproceso(false);

						break;

					case cs_TAB_SUBPROCESO:
						setBloquearDatosProceso(false);
						setCargarDatosProceso(true);
						setCargarDatosSubproceso(false);

						break;

					case cs_TAB_VERSION:
						setBloquearDatosProceso(false);
						setCargarDatosProceso(true);
						setCargarDatosSubproceso(true);

						break;

					default:
						break;
				}

				ls_return = NavegacionCommon.WORKFLOW;
				cargarSubProcesos();
				cargarEtapas();
				cargarReglasNegocio();
				cargarFases();
				enviarParametros();
				setDesdeBandeja(false);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CARGA_XML);
		}
		catch(B2BException lb2be_e)
		{
			ls_return = NavegacionCommon.BANDEJA_DIAGRAMAS_EXISTENTES;
			clh_LOGGER.error("accionNuevo", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Accion proceso.
	 *
	 * @param apt_pt de apt pt
	 */
	public void accionProceso(ProcesoTrabajo apt_pt)
	{
		if(apt_pt != null)
		{
			setProcesoTrabajoSeleccionado(apt_pt);
			PrimeFaces.current().executeScript(ScriptsCommon.WIZARD_NEXT);
		}
	}

	/**
	 * Accion regresar.
	 *
	 * @return de string
	 */
	public String accionRegresar()
	{
		boolean lb_monitoreo;
		String  ls_return;

		lb_monitoreo     = isMonitoreo();
		ls_return        = lb_monitoreo ? NavegacionCommon.MONITOREO : NavegacionCommon.BANDEJA_DIAGRAMAS_EXISTENTES;

		if(!lb_monitoreo)
			setTabActual(cs_TAB_PROCESO);

		return ls_return;
	}

	/**
	 * Accion subproceso.
	 *
	 * @param aspt_spt de aspt spt
	 */
	public void accionSubproceso(SubProcesoTrabajo aspt_spt)
	{
		if(aspt_spt != null)
		{
			setSubprocesoTrabajoSeleccionado(aspt_spt);
			PrimeFaces.current().executeScript(ScriptsCommon.WIZARD_NEXT);
		}
	}

	/**
	 * Accion version.
	 *
	 * @param aspvt_version de aspvt version
	 * @return de string
	 */
	public String accionVersion(SubProcesoVersionTrabajo aspvt_version)
	{
		return accionVersion(aspvt_version, false, false);
	}

	/**
	 * Accion version.
	 *
	 * @param aspvt_version de aspvt version
	 * @param ab_aprobacion de ab aprobacion
	 * @return de string
	 */
	public String accionVersion(SubProcesoVersionTrabajo aspvt_version, boolean ab_aprobacion)
	{
		return accionVersion(aspvt_version, ab_aprobacion, false);
	}

	/**
	 * Accion version.
	 *
	 * @param aspvt_version de aspvt version
	 * @param ab_aprobacion de ab aprobacion
	 * @param ab_nuevaVersion de ab nueva version
	 * @return de string
	 */
	public String accionVersion(SubProcesoVersionTrabajo aspvt_version, boolean ab_aprobacion, boolean ab_nuevaVersion)
	{
		String ls_return;

		ls_return = null;

		if(aspvt_version != null)
		{
			String ls_xml;

			ls_xml = aspvt_version.getXml();

			setSubprocesoVersionTrabajoSeleccionado(aspvt_version);
			setXml(ls_xml);
			setDesdeBandeja(true);

			{
				String ls_estado;

				ls_estado = aspvt_version.getEstadoFlujo();
				setBloquearDatosProceso(
				    !StringUtils.isValidString(ls_xml) && StringUtils.isValidString(ls_estado)
					    && ls_estado.equalsIgnoreCase(EstadoFlujoCommon.DEFINITIVO)
				);
				setCargarDatosProceso(true);
				setCargarDatosSubproceso(true);
				cargarEtapas();
			}

			if(!ab_nuevaVersion)
				setNuevaVersion(false);

			ls_return = ab_aprobacion ? NavegacionCommon.WORKFLOW_APROBADOR : NavegacionCommon.WORKFLOW;
			cargarSubProcesos();
			cargarReglasNegocio();
			cargarFases();
			setSalvarAprobador(false);
		}

		return ls_return;
	}

	/**
	 * Agregar regla.
	 */
	public void agregarRegla()
	{
		try
		{
			Collection<ReglaNegocio> lcrn_reglasAgregadas;

			lcrn_reglasAgregadas = getReglasAgregadas();

			if(!CollectionUtils.isValidCollection(lcrn_reglasAgregadas))
			{
				lcrn_reglasAgregadas = new ArrayList<ReglaNegocio>(1);

				lcrn_reglasAgregadas.add(new ReglaNegocio());
			}
			else
			{
				Collection<ReglaNegocio> lcrn_reglas;

				lcrn_reglas = getReglas();

				if(CollectionUtils.isValidCollection(lcrn_reglas))
				{
					if(lcrn_reglasAgregadas.size() < lcrn_reglas.size())
					{
						lcrn_reglasAgregadas.add(new ReglaNegocio());
						addMessage(MessagesKeys.PROCESO_COMPLETADO);
					}
					else
						throw new B2BException("Todas las reglas de negocio han sido agregadas");
				}
			}

			setReglasAgregadas(lcrn_reglasAgregadas);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarRegla", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Bloquear aprobador.
	 */
	public void bloquearAprobador()
	{
		setSalvarAprobador(false);
	}

	/**
	 * Cargar diagrama.
	 */
	public void cargarDiagrama()
	{
		try
		{
			boolean lb_monitoreo;

			lb_monitoreo = isMonitoreo();

			if(!lb_monitoreo)
			{
				String ls_xml;

				ls_xml = null;

				if(isDesdeBandeja())
				{
					ls_xml = getXml();

					if(!StringUtils.isValidString(ls_xml))
					{
						PrimeRequestContext.getCurrentInstance().getCallbackParams()
							                   .put(
							    cs_PARAMETRO_XML,
							    iwr_workflowRemote.findNewDiagram(
							        getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							    )
							);
						enviarParametros();
						setSalvarAprobador(false);
						throw new B2BException(ErrorKeys.ERROR_CARGA_XML);
					}
				}
				else
				{
					ls_xml = iwr_workflowRemote.findNewDiagram(getUserId(), getLocalIpAddress(), getRemoteIpAddress());
					setSalvarAprobador(true);
				}

				if(StringUtils.isValidString(ls_xml))
					PrimeRequestContext.getCurrentInstance().getCallbackParams().put(cs_PARAMETRO_XML, ls_xml);
				else
					throw new B2BException(ErrorKeys.ERROR_CARGA_XML);

				if(getProcesoTrabajoSeleccionado() == null)
					setProcesoTrabajoSeleccionado(new ProcesoTrabajo());
			}
			else
			{
				Boolean lb_isMonitoreo;

				lb_isMonitoreo = new Boolean(isMonitoreo());

				PrimeRequestContext.getCurrentInstance().getCallbackParams().put(cs_PARAMETRO_XML, getXml());
				PrimeRequestContext.getCurrentInstance().getCallbackParams().put(
				    cs_PARAMETRO_MONITOREO, lb_isMonitoreo
				);
			}

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			enviarParametros();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDiagrama", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar diagramas aprobacion.
	 */
	public void cargarDiagramasAprobacion()
	{
		try
		{
			setProcesos(
			    iwr_workflowRemote.findProcesosAprobacion(getUserId(), getLocalIpAddress(), getRemoteIpAddress())
			);
			setTabActual(cs_TAB_PROCESO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirDiagrama", lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar etapas.
	 */
	public void cargarEtapas()
	{
		try
		{
			setEtapas(iwr_workflowRemote.findAllEtapas(getUserId(), getLocalIpAddress(), getRemoteIpAddress()));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar procesos.
	 */
	public void cargarProcesos()
	{
		try
		{
			setProcesos(iwr_workflowRemote.findProcesos(getUserId(), getLocalIpAddress(), getRemoteIpAddress()));
			setTabActual(cs_TAB_PROCESO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abrirDiagrama", lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar reglas negocio.
	 */
	public void cargarReglasNegocio()
	{
		try
		{
			setReglas(iwr_workflowRemote.findAllReglas(getUserId(), getLocalIpAddress(), getRemoteIpAddress()));
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar reglas negocio motivo.
	 */
	public void cargarReglasNegocioMotivo()
	{
		String ls_param;

		ls_param = null;

		{
			Map<String, String> lmss_params;

			lmss_params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			if(CollectionUtils.isValidCollection(lmss_params))
			{
				ls_param = lmss_params.get(cs_PARAMETRO_REGLAS);

				if(StringUtils.isValidString(ls_param))
				{
					Collection<ReglaNegocio> lcrn_reglasNegocio;

					lcrn_reglasNegocio = getReglas();

					if(CollectionUtils.isValidCollection(lcrn_reglasNegocio))
					{
						Collection<String> lcs_reglasParametro;

						lcs_reglasParametro = ListadoCodigosConstantes.generarCodigosCollection(ls_param);

						if(CollectionUtils.isValidCollection(lcs_reglasParametro))
						{
							Collection<ReglaNegocio> lcrn_reglasMotivo;

							lcrn_reglasMotivo = new ArrayList<ReglaNegocio>(1);

							for(String ls_idRegla : lcs_reglasParametro)
							{
								if(StringUtils.isValidString(ls_idRegla))
								{
									Optional<ReglaNegocio> lorn_regla;

									//FORMATO 
									lorn_regla = lcrn_reglasNegocio.stream()
											.filter(lrn_reglaTemp -> lrn_reglaTemp != null
													&& lrn_reglaTemp.getIdReglaNegocio() != null
													&& lrn_reglaTemp.getIdReglaNegocio().equalsIgnoreCase(ls_idRegla))
											.findFirst();
									
									if(lorn_regla.isPresent())
										lcrn_reglasMotivo.add(lorn_regla.get());
								}
							}

							setReglasAgregadas(lcrn_reglasMotivo);
						}
					}
				}
				else
					setReglasAgregadas(null);
			}
		}
	}

	/**
	 * Cargar unidad tiempo vencimiento.
	 *
	 * @return de collection
	 */
	public Collection<UnidadTiempoVencimiento> cargarUnidadTiempoVencimiento()
	{
		Collection<UnidadTiempoVencimiento> lcutv_unidadTiempoVencimiento;
		lcutv_unidadTiempoVencimiento = null;

		try
		{
			lcutv_unidadTiempoVencimiento = iwr_workflowRemote.findAllUnidadTiempoVencimiento();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcutv_unidadTiempoVencimiento;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		// TODO revisar que esten todas las variables del BEAN
		setProcesos(null);
		setVersiones(null);
		setSubprocesos(null);
		setProcesoTrabajoSeleccionado(null);
		setIdEtapa(null);
		setNombreProcesoSeleccionado(null);
		setNombreSubProcesoSeleccionado(null);
		setTabActual(null);
		setXml(null);
		setSubprocesoTrabajoSeleccionado(null);
		setSubprocesoVersionTrabajoSeleccionado(null);
		setBloquearDatosProceso(false);
		setCargarDatosProceso(false);
		setCargarDatosSubproceso(false);
		setDesdeBandeja(false);
		setNuevaVersion(false);
		setSalvarAprobador(false);
		setAllSubProcesos(null);
		setEstado(false);
		setNir(null);
		setTurnos(null);
		setIdTurnoConsulta(null);
		setMonitoreo(false);
		setReglasAgregadas(null);
		setReglas(null);
		ii_step = 0;
	}

	/**
	 * Eliminar regla.
	 *
	 * @param arn_regla de arn regla
	 */
	public void eliminarRegla(ReglaNegocio arn_regla)
	{
		if(arn_regla != null)
		{
			Collection<ReglaNegocio> lcrn_reglas;

			lcrn_reglas = getReglasAgregadas();

			if(CollectionUtils.isValidCollection(lcrn_reglas) && lcrn_reglas.contains(arn_regla))
				lcrn_reglas.remove(arn_regla);
		}
	}

	/**
	 * Enviar parametros.
	 */
	public void enviarParametros()
	{
		enviarParametros(false);
	}

	/**
	 * Enviar parametros.
	 *
	 * @param ab_onlyVersion de ab only version
	 */
	public void enviarParametros(boolean ab_onlyVersion)
	{
		Gson                lg_gson;
		Map<String, Object> lmso_callbackParams;

		lg_gson                 = new Gson();
		lmso_callbackParams     = PrimeRequestContext.getCurrentInstance().getCallbackParams();

		if(!ab_onlyVersion)
		{
			{
				String ls_procesoJSON;

				ls_procesoJSON = lg_gson.toJson(getProcesoTrabajoSeleccionado());

				if(StringUtils.isValidString(ls_procesoJSON))
					lmso_callbackParams.put("proceso", ls_procesoJSON);
			}

			{
				String ls_etapasJSON;

				ls_etapasJSON = lg_gson.toJson(getEtapas());

				if(StringUtils.isValidString(ls_etapasJSON))
					lmso_callbackParams.put("etapas", ls_etapasJSON);
			}

			{
				String ls_procesosJSON;

				ls_procesosJSON = lg_gson.toJson(getProcesos());

				if(StringUtils.isValidString(ls_procesosJSON))
					lmso_callbackParams.put("procesos", ls_procesosJSON);
			}

			{
				String ls_subProcesosJSON;

				ls_subProcesosJSON = lg_gson.toJson(getAllSubProcesos());

				if(StringUtils.isValidString(ls_subProcesosJSON))
					lmso_callbackParams.put("subProcesos", ls_subProcesosJSON);
			}
		}

		{
			String ls_subProcesoJSON;

			ls_subProcesoJSON = lg_gson.toJson(getSubprocesoVersionTrabajoSeleccionado());

			if(StringUtils.isValidString(ls_subProcesoJSON))
				lmso_callbackParams.put("subproceso", ls_subProcesoJSON);
		}

		{
			String ls_nirJSON;

			ls_nirJSON = lg_gson.toJson(getNir());

			if(StringUtils.isValidString(ls_nirJSON))
				lmso_callbackParams.put("nir", ls_nirJSON);
		}

		{
			String ls_turnoJSON;

			ls_turnoJSON = lg_gson.toJson(getIdTurnoConsulta());

			if(StringUtils.isValidString(ls_turnoJSON))
				lmso_callbackParams.put("turno", ls_turnoJSON);
		}
	}

	/**
	 * Filtrar sub procesos.
	 */
	public void filtrarSubProcesos()
	{
		String ls_idProcesoSeleccionado;

		ls_idProcesoSeleccionado = getIdProcesoSeleccionado();

		if(StringUtils.isValidString(ls_idProcesoSeleccionado))
		{
			Collection<SubProcesoVersionTrabajo> lcspt_subprocesos;

			lcspt_subprocesos = getAllSubProcesos();

			if(CollectionUtils.isValidCollection(lcspt_subprocesos))
			{
				Collection<SubProcesoVersionTrabajo> lcspvt_temporales;

				lcspvt_temporales = new ArrayList<SubProcesoVersionTrabajo>();

				// FORMATO Comentar lambda antes de formatear
				lcspt_subprocesos.stream()
						.filter(lspt_temp -> lspt_temp != null && lspt_temp.getIdProceso() != null
								&& lspt_temp.getIdProceso().equalsIgnoreCase(ls_idProcesoSeleccionado))
						.forEach(lspt_add -> lcspvt_temporales.add(lspt_add));
				
				setSubProcesosFiltrados(lcspvt_temporales);
			}
		}
		else
			setSubProcesosFiltrados(null);
	}

	/**
	 * Find all.
	 */
	public void findDataTurnos()
	{
		try
		{
			String ls_nir;
			String ls_idTurno;

			Solicitud    ls_solicitud;
			Turno        lt_turno;
			Trazabilidad lt_trazabilidad;

			ls_nir              = getNir();
			ls_idTurno          = getIdTurnoConsulta();
			ls_solicitud        = new Solicitud();
			lt_turno            = new Turno();
			lt_trazabilidad     = new Trazabilidad();

			ls_nir         = StringUtils.isValidString(ls_nir) ? StringUtils.getStringUpperCase(ls_nir) : null;
			ls_idTurno     = StringUtils.isValidString(ls_idTurno) ? ls_idTurno.trim() : null;

			ls_solicitud.setNir(ls_nir);
			lt_trazabilidad.setSolicitud(ls_solicitud);
			lt_turno.setIdTurno(ls_idTurno);
			lt_trazabilidad.setTurno(lt_turno);

			setTurnos(
			    iwr_workflowRemote.cargarInfoBandejaConsulta(
			        lt_trazabilidad, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
			setNir(ls_nir);
			setIdTurnoConsulta(ls_idTurno);
			setEstado(true);
			addMessage(MessagesKeys.CONSULTA_EXITOSA);
		}
		catch(B2BException lb2be_e)
		{
			setEstado(false);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find monitoreo.
	 *
	 * @param at_item de at item
	 * @param ab_nir de ab nir
	 * @return el valor de string
	 */
	public String findMonitoreo(Trazabilidad at_item, boolean ab_nir)
	{
		String ls_return;

		ls_return = NavegacionCommon.MONITOREO;

		if(at_item != null)
		{
			try
			{
				Solicitud ls_solicitud;

				ls_solicitud = at_item.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						String ls_xml;
						String ls_idTurno;

						ls_xml         = null;
						ls_idTurno     = null;

						if(!ab_nir)
						{
							Turno lt_turno;

							lt_turno = at_item.getTurno();

							if(lt_turno != null)
								ls_idTurno = lt_turno.getIdTurno();
						}

						ls_xml = iwr_workflowRemote.obtenerMonitoreo(
							    ls_idSolicitud, ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(StringUtils.isValidString(ls_xml))
						{
							setXml(ls_xml);
							ls_return = NavegacionCommon.WORKFLOW_APROBADOR;
						}
					}
				}

				{
					TurnoHistoria lth_historia;

					lth_historia = at_item.getTurnoHistoria();

					if(lth_historia != null)
					{
						SubProcesoVersionTrabajo lspvt_version;

						lspvt_version = new SubProcesoVersionTrabajo();

						lspvt_version.setIdProceso(lth_historia.getIdProceso());
						lspvt_version.setIdSubproceso(lth_historia.getIdSubproceso());
						lspvt_version.setVersion(NumericUtils.getInt(lth_historia.getVersionSubproceso()));

						setSubprocesoVersionTrabajoSeleccionado(lspvt_version);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				actualizarComponente("fMonitoreo:globalMsg");
			}
		}

		return ls_return;
	}

	/**
	 * Controla el flujo entre tabs en la pantalla.
	 *
	 * @param afe_event Objeto contenedor de los eventos que ocurren en pantalla
	 * @return Nombre de tab a mostrar en pantalla
	 *
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = null;

		if(afe_event != null)
		{
			String ls_oldStep;
			String ls_nextStep;

			ls_oldStep      = afe_event.getOldStep();
			ls_nextStep     = afe_event.getNewStep();

			try
			{
				if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_nextStep))
				{
					ProcesoTrabajo lpt_proceso;

					lpt_proceso = getProcesoTrabajoSeleccionado();

					if(lpt_proceso != null)
					{
						String ls_idProceso;

						ls_idProceso = lpt_proceso.getId();

						switch(ls_oldStep)
						{
							case cs_TAB_PROCESO:

								if(StringUtils.isValidString(ls_idProceso))
								{
									setSubprocesos(
									    iwr_workflowRemote.findSubprocesos(
									        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso
									    )
									);
									ii_step = 1;
								}

								break;

							case cs_TAB_SUBPROCESO:

								if(ls_nextStep.equalsIgnoreCase(cs_TAB_PROCESO))
								{
									cargarProcesos();
									ii_step = 0;
								}
								else
								{
									SubProcesoTrabajo lspt_subProceso;

									lspt_subProceso = getSubprocesoTrabajoSeleccionado();

									if(lspt_subProceso != null)
									{
										setVersiones(
										    iwr_workflowRemote.findVersiones(
										        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso,
										        lspt_subProceso.getIdSubproceso()
										    )
										);
										ii_step = 2;
										setNombreProcesoSeleccionado(lpt_proceso.getNombre());
										setNombreSubProcesoSeleccionado(lspt_subProceso.getNombre());
									}
								}

								break;

							case cs_TAB_VERSION:

								if(ls_nextStep.equalsIgnoreCase(cs_TAB_SUBPROCESO))
								{
									if(StringUtils.isValidString(ls_idProceso))
									{
										setSubprocesos(
										    iwr_workflowRemote.findSubprocesos(
										        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso
										    )
										);
										ii_step = 1;
									}
								}

								break;

							default:
								break;
						}

						ls_return = ls_nextStep;
						setTabActual(ls_nextStep);
						actualizarComponente(cs_FORM + cs_BOTONES);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				ls_return = ls_oldStep;
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarComponente(cs_FORM + cs_GROWL);
			}
		}

		return ls_return;
	}

	/**
	 * Controla el flujo entre tabs en la pantalla.
	 *
	 * @param afe_event Objeto contenedor de los eventos que ocurren en pantalla
	 * @return Nombre de tab a mostrar en pantalla
	 *
	 */
	public String flowListenerAprobacion(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = null;

		if(afe_event != null)
		{
			String ls_oldStep;
			String ls_nextStep;

			ls_oldStep      = afe_event.getOldStep();
			ls_nextStep     = afe_event.getNewStep();

			try
			{
				if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_nextStep))
				{
					ProcesoTrabajo lpt_proceso;

					lpt_proceso = getProcesoTrabajoSeleccionado();

					if(lpt_proceso != null)
					{
						String ls_idProceso;

						ls_idProceso = lpt_proceso.getId();

						switch(ls_oldStep)
						{
							case cs_TAB_PROCESO:

								if(StringUtils.isValidString(ls_idProceso))
									setSubprocesos(
									    iwr_workflowRemote.findSubProcesosAprobacion(
									        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso
									    )
									);

								break;

							case cs_TAB_SUBPROCESO:

								if(ls_nextStep.equalsIgnoreCase(cs_TAB_PROCESO))
									cargarDiagramasAprobacion();
								else
								{
									SubProcesoTrabajo lspt_subProceso;

									lspt_subProceso = getSubprocesoTrabajoSeleccionado();

									if(lspt_subProceso != null)
									{
										setVersiones(
										    iwr_workflowRemote.findVersionesAprobacion(
										        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso,
										        lspt_subProceso.getIdSubproceso()
										    )
										);
										setNombreProcesoSeleccionado(lpt_proceso.getNombre());
										setNombreSubProcesoSeleccionado(lspt_subProceso.getNombre());
									}
								}

								break;

							case cs_TAB_VERSION:

								if(ls_nextStep.equalsIgnoreCase(cs_TAB_SUBPROCESO))
								{
									if(StringUtils.isValidString(ls_idProceso))
										setSubprocesos(
										    iwr_workflowRemote.findSubProcesosAprobacion(
										        getUserId(), getLocalIpAddress(), getRemoteIpAddress(), ls_idProceso
										    )
										);
								}

								break;

							default:
								break;
						}

						ls_return = ls_nextStep;
						setTabActual(ls_nextStep);
						actualizarComponente(cs_FORM + cs_BOTONES);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				ls_return = ls_oldStep;
				addMessage(lb2be_e);
			}
			finally
			{
				actualizarComponente(cs_FORM + cs_GROWL);
			}
		}

		return ls_return;
	}

	/**
	 * Guardar reglas negocio.
	 */
	public void guardarReglasNegocio()
	{
		try
		{
			Collection<ReglaNegocio> lcrn_reglas;

			lcrn_reglas = getReglasAgregadas();

			if(CollectionUtils.isValidCollection(lcrn_reglas))
			{
				String ls_reglas;

				ls_reglas = lcrn_reglas.toString();

				if(StringUtils.isValidString(ls_reglas))
				{
					if(ls_reglas.contains(IdentificadoresCommon.LLAVE_INICIAL))
						ls_reglas = ls_reglas.replace(
							    IdentificadoresCommon.LLAVE_INICIAL, IdentificadoresCommon.DATO_INVALIDO
							);

					if(ls_reglas.contains(IdentificadoresCommon.LLAVE_FINAL))
						ls_reglas = ls_reglas.replace(
							    IdentificadoresCommon.LLAVE_FINAL, IdentificadoresCommon.DATO_INVALIDO
							);

					if(ls_reglas.contains(IdentificadoresCommon.ESPACIO_VACIO))
						ls_reglas = ls_reglas.replace(
							    IdentificadoresCommon.ESPACIO_VACIO, IdentificadoresCommon.DATO_INVALIDO
							);

					PrimeFaces.current()
						          .executeScript(
						    ScriptsCommon.GUARDAR_CAMBIOS_REGLA_NEGOCIO + ls_reglas + ScriptsCommon.FIN_FUNCION
						);
					addMessage(MessagesKeys.PROCESO_COMPLETADO);
					setReglasAgregadas(null);
				}
			}
			else
				throw new B2BException("Debe agregar al menos una regla de negocio.");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("guardarReglasNegocio", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_FORM_WORKFLOW + cs_GROWL);
		}
	}

	/**
	 * Mover regla abajo.
	 *
	 * @param arn_regla de arn regla
	 */
	public void moverReglaAbajo(ReglaNegocio arn_regla)
	{
		if(arn_regla != null)
		{
			Collection<ReglaNegocio> lcrn_reglas;

			lcrn_reglas = getReglasAgregadas();

			if(CollectionUtils.isValidCollection(lcrn_reglas) && lcrn_reglas.contains(arn_regla))
			{
				ArrayList<ReglaNegocio> lalrn_reglasLista;

				lalrn_reglasLista = new ArrayList<ReglaNegocio>(lcrn_reglas);

				if(CollectionUtils.isValidCollection(lalrn_reglasLista))
				{
					Collection<ReglaNegocio> lcrn_reglasOrdenadas;

					lcrn_reglasOrdenadas = new ArrayList<ReglaNegocio>(1);

					{
						int li_posicionObjeto;

						li_posicionObjeto = lalrn_reglasLista.indexOf(arn_regla);

						if(li_posicionObjeto < (lalrn_reglasLista.size() - 1))
						{
							for(int ai_i = 0; ai_i < li_posicionObjeto; ai_i++)
								lcrn_reglasOrdenadas.add(lalrn_reglasLista.get(ai_i));

							{
								ReglaNegocio lrn_reglaSiguiente;

								lrn_reglaSiguiente = lalrn_reglasLista.get(li_posicionObjeto + 1);

								if(lrn_reglaSiguiente != null)
									lcrn_reglasOrdenadas.add(lrn_reglaSiguiente);

								lcrn_reglasOrdenadas.add(arn_regla);

								for(int ai_i = li_posicionObjeto; ai_i < lalrn_reglasLista.size(); ai_i++)
								{
									ReglaNegocio lrn_temp;

									lrn_temp = lalrn_reglasLista.get(ai_i);

									if(
									    (lrn_temp != null) && !lrn_temp.equals(arn_regla)
										    && !lrn_temp.equals(lrn_reglaSiguiente)
									)
										lcrn_reglasOrdenadas.add(lrn_temp);
								}
							}

							setReglasAgregadas(lcrn_reglasOrdenadas);
						}
					}
				}
			}
		}
	}

	/**
	 * Mover regla arriba.
	 *
	 * @param arn_regla de arn regla
	 */
	public void moverReglaArriba(ReglaNegocio arn_regla)
	{
		if(arn_regla != null)
		{
			Collection<ReglaNegocio> lcrn_reglas;

			lcrn_reglas = getReglasAgregadas();

			if(CollectionUtils.isValidCollection(lcrn_reglas) && lcrn_reglas.contains(arn_regla))
			{
				ArrayList<ReglaNegocio> lalrn_reglasLista;

				lalrn_reglasLista = new ArrayList<ReglaNegocio>(lcrn_reglas);

				if(CollectionUtils.isValidCollection(lalrn_reglasLista))
				{
					Collection<ReglaNegocio> lcrn_reglasOrdenadas;

					lcrn_reglasOrdenadas = new ArrayList<ReglaNegocio>(1);

					{
						int li_posicionObjeto;

						li_posicionObjeto = lalrn_reglasLista.indexOf(arn_regla);

						if(li_posicionObjeto > 0)
						{
							for(int ai_i = 0; ai_i < (li_posicionObjeto - 1); ai_i++)
								lcrn_reglasOrdenadas.add(lalrn_reglasLista.get(ai_i));

							lcrn_reglasOrdenadas.add(arn_regla);

							for(int ai_i = li_posicionObjeto - 1; ai_i < lalrn_reglasLista.size(); ai_i++)
							{
								ReglaNegocio lrn_temp;

								lrn_temp = lalrn_reglasLista.get(ai_i);

								if((lrn_temp != null) && !lrn_temp.equals(arn_regla))
									lcrn_reglasOrdenadas.add(lrn_temp);
							}

							setReglasAgregadas(lcrn_reglasOrdenadas);
						}
					}
				}
			}
		}
	}

	/**
	 * Procesar.
	 */
	public void procesar()
	{
		try
		{
			String ls_param;

			ls_param = null;

			{
				Map<String, String> lmss_params;

				lmss_params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

				if(CollectionUtils.isValidCollection(lmss_params))
					ls_param = lmss_params.get(cs_PARAMETRO_XML);
			}

			if(StringUtils.isValidString(ls_param))
			{
				int li_version;

				li_version = 0;

				{
					SubProcesoVersionTrabajo lspvt_version;

					lspvt_version = getSubprocesoVersionTrabajoSeleccionado();

					if(lspvt_version != null)
						li_version = NumericUtils.getInt(lspvt_version.getVersion());
				}

				{
					Collection<ProcesoTrabajo> lcpt_procesos;

					lcpt_procesos = iwr_workflowRemote.analizarContenidoDefiniciones(
						    ls_param, li_version, 2, isNuevaVersion(), getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

					if(CollectionUtils.isValidCollection(lcpt_procesos))
					{
						Iterator<ProcesoTrabajo> lipt_iterator;

						lipt_iterator = lcpt_procesos.iterator();

						if(lipt_iterator != null)
						{
							ProcesoTrabajo lpt_proceso;

							lpt_proceso = lipt_iterator.next();

							if(lpt_proceso != null)
							{
								setProcesoTrabajoSeleccionado(lpt_proceso);
								setSubprocesoTrabajoSeleccionado(lpt_proceso.getSubProceso());

								{
									SubProcesoVersionTrabajo lspvt_version;

									lspvt_version = getSubprocesoVersionTrabajoSeleccionado();

									if(lspvt_version != null)
										lspvt_version.setVersion(lpt_proceso.getSubProcesoVersion());
								}
							}
						}
					}
				}

				PrimeFaces.current().executeScript(ScriptsCommon.GUARDADO_EXITOSO);
				setSalvarAprobador(true);
				setNuevaVersion(false);
				enviarParametros(true);
				addMessage(MessagesKeys.PROCESO_COMPLETADO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesar", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_FORM_WORKFLOW + cs_GROWL);
		}
	}

	/**
	 * Retorna el estado de un subproceso version a devuelto.
	 *
	 * @return String que contiene el cambio de pantalla
	 * @throws B2BException de b 2 B exception
	 */
	public String retornarAEstadoDevuelto()
	    throws B2BException
	{
		String ls_return;

		ls_return = NavegacionCommon.WORKFLOW_APROBADOR;

		try
		{
			ProcesoTrabajo lpt_procesoTrabajo;

			lpt_procesoTrabajo = getProcesoTrabajoSeleccionado();

			if(lpt_procesoTrabajo != null)
			{
				SubProcesoTrabajo lspt_subproceso;

				lspt_subproceso = getSubprocesoTrabajoSeleccionado();

				if(lspt_subproceso != null)
				{
					SubProcesoVersionTrabajo lspvt_version;

					lspvt_version = getSubprocesoVersionTrabajoSeleccionado();

					if(lspvt_version != null)
						iwr_workflowRemote.retornarAEstadoDevuelto(
						    lpt_procesoTrabajo.getId(), lspt_subproceso.getIdSubproceso(), lspvt_version.getVersion(),
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					ls_return = retornarBandeja();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionEnviarAlAprobador", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retornar bandeja.
	 *
	 * @return the string
	 */
	public String retornarBandeja()
	{
		clear();
		cargarDiagramasAprobacion();

		return NavegacionCommon.BANDEJA_APROBACION_DIAGRAMAS;
	}

	/**
	 * Metodo para validar una nueva version.
	 *
	 * @param aspvt_version de aspvt version
	 * @return de string
	 */
	public String validarNuevaVesion(SubProcesoVersionTrabajo aspvt_version)
	{
		setNuevaVersion(true);

		return accionVersion(aspvt_version, false, true);
	}

	/**
	 * Cargar fases.
	 */
	private void cargarFases()
	{
		try
		{
			setFases(iwr_workflowRemote.findAllFases());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarSubProcesos", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar sub procesos.
	 */
	private void cargarSubProcesos()
	{
		try
		{
			setAllSubProcesos(
			    iwr_workflowRemote.findAllSubProcesos(getUserId(), getLocalIpAddress(), getRemoteIpAddress())
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarSubProcesos", lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
