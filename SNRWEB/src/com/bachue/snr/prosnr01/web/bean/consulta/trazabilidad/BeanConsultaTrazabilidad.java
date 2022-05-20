package com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.NirPrincipal;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.NirVinculado;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.ui.FoliosSirUI;
import com.bachue.snr.prosnr01.model.ui.LiquidacionesHistoricosUI;
import com.bachue.snr.prosnr01.model.ui.PagosUI;
import com.bachue.snr.prosnr01.model.ui.TrazabilidadTurnoUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.consulta.SGD.BeanConsultaSGD;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaTrazabilidad.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaTrazabilidad")
@SessionScoped
public class BeanConsultaTrazabilidad extends BeanConsultaSGD implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaTrazabilidad.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1010478764899305900L;

	/** Propiedad icct consulta trazabilidad. */
	private Collection<ConsultaTrazabilidad> icct_consultaTrazabilidad;

	/** Propiedad icct consulta trazabilidad NIR. */
	private Collection<ConsultaTrazabilidad> icct_consultaTrazabilidadNir;

	/** Propiedad iclhui liquidacionesHistoricosUI. */
	private Collection<LiquidacionesHistoricosUI> iclhui_liquidacionesHistoricosUI;

	/** Propiedad icpui pagosUI. */
	private Collection<PagosUI> icpui_pagosUI;

	/** Propiedad icsm matriculas. */
	private Collection<SolicitudMatricula> icsm_matriculas;

	/** Propiedad ict data asociada. */
	private Collection<Trazabilidad> ict_dataAsociada;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_trazabilidad;

	/** Propiedad icttui trazabilidad turno ui folio. */
	private Collection<TrazabilidadTurnoUI> icttui_trazabilidadTurnoUIFolio;

	/** Propiedad icttui trazabilidad turno ui sir. */
	private Collection<TrazabilidadTurnoUI> icttui_trazabilidadTurnoUISir;

	/** Propiedad inv nir vinculados. */
	private Collection<NirVinculado> inv_nirVinculados;

	/** Propiedad inv turnos vinculados nir vinculados. */
	private Collection<NirVinculado> inv_turnosVinculadosNirVinculados;

	/** Propiedad ictr consulta trazabilidad remote. */
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad iid informacion documento. */
	private InformacionDocumento iid_informacionDocumento;

	/** Propiedad inp nir principal. */
	private NirPrincipal inp_nirPrincipal;

	/** Propiedad inp turnos vinculados nir principal. */
	private NirPrincipal inp_turnosVinculadosNirPrincipal;

	/** Propiedad irt reasignar turnos. */
	private ReasignarTurnos irt_reasignarTurnos;

	/** Propiedad is pagina retorno. */
	private String is_paginaRetorno;

	/** Propiedad is_turnoAfectado */
	private String is_turnoAfectado;

	/** Propiedad it trazabilidad info. */
	private Trazabilidad it_trazabilidadInfo;

	/** Propiedad ib bandeja historicos. */
	private boolean ib_bandejaHistoricos;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/** Propiedad ib render consulta SGD. */
	private boolean ib_renderConsultaSGD;

	/** Propiedad ib tramites asociados. */
	private boolean ib_tramitesAsociados;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de bandeja historicos.
	 *
	 * @param ab_b asigna el valor a la propiedad bandeja historicos
	 */
	public void setBandejaHistoricos(boolean ab_b)
	{
		ib_bandejaHistoricos = ab_b;
	}

	/**
	 * Valida la propiedad bandeja historicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado
	 */
	public boolean isBandejaHistoricos()
	{
		return ib_bandejaHistoricos;
	}

	/**
	 * Modifica el valor de consulta trazabilidad.
	 *
	 * @param acct_cct asigna el valor a la propiedad consulta trazabilidad
	 */
	public void setConsultaTrazabilidad(Collection<ConsultaTrazabilidad> acct_cct)
	{
		icct_consultaTrazabilidad = acct_cct;
	}

	/**
	 * Retorna el valor de consulta trazabilidad.
	 *
	 * @return el valor de consulta trazabilidad
	 */
	public Collection<ConsultaTrazabilidad> getConsultaTrazabilidad()
	{
		return icct_consultaTrazabilidad;
	}

	/**
	 * Modifica el valor de ConsultaTrazabilidadNir.
	 *
	 * @param acct_cct de acct cct
	 */
	public void setConsultaTrazabilidadNir(Collection<ConsultaTrazabilidad> acct_cct)
	{
		icct_consultaTrazabilidadNir = acct_cct;
	}

	/**
	 * Retorna Objeto o variable de valor consulta trazabilidad nir.
	 *
	 * @return el valor de consulta trazabilidad nir
	 */
	public Collection<ConsultaTrazabilidad> getConsultaTrazabilidadNir()
	{
		return icct_consultaTrazabilidadNir;
	}

	/**
	 * Modifica el valor de data asociada.
	 *
	 * @param act_ct asigna el valor a la propiedad data asociada
	 */
	public void setDataAsociada(Collection<Trazabilidad> act_ct)
	{
		ict_dataAsociada = act_ct;
	}

	/**
	 * Retorna el valor de data asociada.
	 *
	 * @return el valor de data asociada
	 */
	public Collection<Trazabilidad> getDataAsociada()
	{
		return ict_dataAsociada;
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
	 * Modifica el valor de informacion documento.
	 *
	 * @param aid_id asigna el valor a la propiedad informacion documento
	 */
	public void setInformacionDocumento(InformacionDocumento aid_id)
	{
		iid_informacionDocumento = aid_id;
	}

	/**
	 * Retorna el valor de informacion documento.
	 *
	 * @return el valor de informacion documento
	 */
	public InformacionDocumento getInformacionDocumento()
	{
		return iid_informacionDocumento;
	}

	/**
	 * Modifica el valor de liquidaciones historicos ui.
	 *
	 * @param aclhui_clhui asigna el valor a la propiedad liquidaciones historicos ui
	 */
	public void setLiquidacionesHistoricosUI(Collection<LiquidacionesHistoricosUI> aclhui_clhui)
	{
		iclhui_liquidacionesHistoricosUI = aclhui_clhui;
	}

	/**
	 * Retorna el valor de liquidaciones historicos ui.
	 *
	 * @return el valor de liquidaciones historicos ui
	 */
	public Collection<LiquidacionesHistoricosUI> getLiquidacionesHistoricosUI()
	{
		return iclhui_liquidacionesHistoricosUI;
	}

	/**
	 * Modifica el valor de matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas
	 */
	public void setMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculas = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas.
	 *
	 * @return el valor de matriculas
	 */
	public Collection<SolicitudMatricula> getMatriculas()
	{
		return icsm_matriculas;
	}

	/**
	 * Modifica el valor de nir principal.
	 *
	 * @param anp_np asigna el valor a la propiedad nir principal
	 */
	public void setNirPrincipal(NirPrincipal anp_np)
	{
		inp_nirPrincipal = anp_np;
	}

	/**
	 * Retorna el valor de nir principal.
	 *
	 * @return el valor de nir principal
	 */
	public NirPrincipal getNirPrincipal()
	{
		return inp_nirPrincipal;
	}

	/**
	 * Modifica el valor de nir vinculados.
	 *
	 * @param anr_nr asigna el valor a la propiedad nir vinculados
	 */
	public void setNirVinculados(Collection<NirVinculado> anr_nr)
	{
		inv_nirVinculados = anr_nr;
	}

	/**
	 * Retorna el valor de nir vinculados.
	 *
	 * @return el valor de nir vinculados
	 */
	public Collection<NirVinculado> getNirVinculados()
	{
		return inv_nirVinculados;
	}

	/**
	 * Modifica el valor de pagina retorno.
	 *
	 * @param as_s asigna el valor a la propiedad pagina retorno
	 */
	public void setPaginaRetorno(String as_s)
	{
		is_paginaRetorno = as_s;
	}

	/**
	 * Retorna el valor de pagina retorno.
	 *
	 * @return el valor de pagina retorno
	 */
	public String getPaginaRetorno()
	{
		return is_paginaRetorno;
	}

	/**
	 * Modifica el valor de pagos ui.
	 *
	 * @param acpui_cpui asigna el valor a la propiedad pagos ui
	 */
	public void setPagosUI(Collection<PagosUI> acpui_cpui)
	{
		icpui_pagosUI = acpui_cpui;
	}

	/**
	 * Retorna el valor de pagos ui.
	 *
	 * @return el valor de pagos ui
	 */
	public Collection<PagosUI> getPagosUI()
	{
		return icpui_pagosUI;
	}

	/**
	 * Modifica el valor de reasignar turnos.
	 *
	 * @param art_rt asigna el valor a la propiedad reasignar turnos
	 */
	public void setReasignarTurnos(ReasignarTurnos art_rt)
	{
		irt_reasignarTurnos = art_rt;
	}

	/**
	 * Retorna el valor de reasignar turnos.
	 *
	 * @return el valor de reasignar turnos
	 */
	public ReasignarTurnos getReasignarTurnos()
	{
		return irt_reasignarTurnos;
	}

	/**
	 * Modifica el valor de render consulta SGD.
	 *
	 * @param ab_b asigna el valor a la propiedad render consulta SGD
	 */
	public void setRenderConsultaSGD(boolean ab_b)
	{
		ib_renderConsultaSGD = ab_b;
	}

	/**
	 * Valida la propiedad render consulta SGD.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en render consulta SGD
	 */
	public boolean isRenderConsultaSGD()
	{
		return ib_renderConsultaSGD;
	}

	/**
	 * Modifica el valor de tramites asociados.
	 *
	 * @param ab_b asigna el valor a la propiedad tramites asociados
	 */
	public void setTramitesAsociados(boolean ab_b)
	{
		ib_tramitesAsociados = ab_b;
	}

	/**
	 * Valida la propiedad tramites asociados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en tramites asociados
	 */
	public boolean isTramitesAsociados()
	{
		return ib_tramitesAsociados;
	}

	/**
	 * Modifica el valor de trazabilidad.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidad = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<Trazabilidad> getTrazabilidad()
	{
		return ict_trazabilidad;
	}

	/**
	 * Modifica el valor de trazabilidad info.
	 *
	 * @param at_t asigna el valor a la propiedad trazabilidad info
	 */
	public void setTrazabilidadInfo(Trazabilidad at_t)
	{
		it_trazabilidadInfo = at_t;
	}

	/**
	 * Retorna el valor de trazabilidad info.
	 *
	 * @return el valor de trazabilidad info
	 */
	public Trazabilidad getTrazabilidadInfo()
	{
		return it_trazabilidadInfo;
	}

	/**
	 * Modifica el valor de trazabilidad turno ui folio.
	 *
	 * @param acttui_cttui asigna el valor a la propiedad trazabilidad turno ui folio
	 */
	public void setTrazabilidadTurnoUIFolio(Collection<TrazabilidadTurnoUI> acttui_cttui)
	{
		icttui_trazabilidadTurnoUIFolio = acttui_cttui;
	}

	/**
	 * Retorna el valor de trazabilidad turno ui folio.
	 *
	 * @return el valor de trazabilidad turno ui folio
	 */
	public Collection<TrazabilidadTurnoUI> getTrazabilidadTurnoUIFolio()
	{
		return icttui_trazabilidadTurnoUIFolio;
	}

	/**
	 * Modifica el valor de trazabilidad turno ui sir.
	 *
	 * @param acttui_cttui asigna el valor a la propiedad trazabilidad turno ui sir
	 */
	public void setTrazabilidadTurnoUISir(Collection<TrazabilidadTurnoUI> acttui_cttui)
	{
		icttui_trazabilidadTurnoUISir = acttui_cttui;
	}

	/**
	 * Retorna el valor de trazabilidad turno ui sir.
	 *
	 * @return el valor de trazabilidad turno ui sir
	 */
	public Collection<TrazabilidadTurnoUI> getTrazabilidadTurnoUISir()
	{
		return icttui_trazabilidadTurnoUISir;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTurnoAfectado(String as_s)
	{
		is_turnoAfectado = as_s;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String getTurnoAfectado()
	{
		return is_turnoAfectado;
	}

	/**
	 * Modifica el valor de turnos vinculados nir principal.
	 *
	 * @param anp_np asigna el valor a la propiedad turnos vinculados nir principal
	 */
	public void setTurnosVinculadosNirPrincipal(NirPrincipal anp_np)
	{
		inp_turnosVinculadosNirPrincipal = anp_np;
	}

	/**
	 * Retorna el valor de turnos vinculados nir principal.
	 *
	 * @return el valor de turnos vinculados nir principal
	 */
	public NirPrincipal getTurnosVinculadosNirPrincipal()
	{
		return inp_turnosVinculadosNirPrincipal;
	}

	/**
	 * Modifica el valor de turnos vinculados nir vinculados.
	 *
	 * @param anv_nv asigna el valor a la propiedad turnos vinculados nir vinculados
	 */
	public void setTurnosVinculadosNirVinculados(Collection<NirVinculado> anv_nv)
	{
		inv_turnosVinculadosNirVinculados = anv_nv;
	}

	/**
	 * Retorna el valor de turnos vinculados nir vinculados.
	 *
	 * @return el valor de turnos vinculados nir vinculados
	 */
	public Collection<NirVinculado> getTurnosVinculadosNirVinculados()
	{
		return inv_turnosVinculadosNirVinculados;
	}

	/**
	 *  Método para encontrar todos los registros de las vistas SDB_VW_LIQUIDACIONES_HISTORICOS_SIR ,SDB_VW_TRAZABILIDAD_TURNO_SIR ,SDB_VW_PAGOS_SIR
	 *  o SDB_VW_LIQUIDACIONES_HISTORICOS_FOLIO ,SDB_VW_TRAZABILIDAD_TURNO_FOLIO ,SDB_VW_PAGOS_FOLIO acorde con el turno.
	 *
	 * @param at_turno correspondiente al valor del tipo de objeto Turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarPanelHistorico(Turno at_turno)
	{
		try
		{
			if(at_turno != null)
			{
				FoliosSirUI lfsui_foliosSirUI;

				lfsui_foliosSirUI = ictr_consultaTrazabilidadRemote.cargarBandejaHistoricos(
					    at_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lfsui_foliosSirUI != null)
				{
					setLiquidacionesHistoricosUI(lfsui_foliosSirUI.getLiquidacionesHistoricosUI());
					setPagosUI(lfsui_foliosSirUI.getPagosUI());
					setTrazabilidadTurnoUIFolio(at_turno.isFolio() ? lfsui_foliosSirUI.getTrazabilidadTurnoUI() : null);
					setTrazabilidadTurnoUISir(at_turno.isSir() ? lfsui_foliosSirUI.getTrazabilidadTurnoUI() : null);
					setBandejaHistoricos(true);

					PrimeFaces.current().ajax().update("fConsultaTrazabilidad:consultaTrazaPorNir");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de limpiar la información contenida en el bean.
	 */
	public void clear()
	{
		setTrazabilidad(null);
		setConsultaTrazabilidad(null);
		setInformacionDocumento(null);
		setIdTurnoConsulta(null);
		setNir(null);
		setExpedienteConsulta(null);
		setTrazabilidadInfo(null);
		setEstado(false);
		setRenderConsultaSGD(false);
		setPaginaRetorno(null);
		setLiquidacionesHistoricosUI(null);
		setPagosUI(null);
		setTrazabilidadTurnoUIFolio(null);
		setTrazabilidadTurnoUISir(null);
		setBandejaHistoricos(false);
	}

	/**
	 * Consulta detallada.
	 *
	 * @param as_pagina correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(String as_pagina)
	    throws B2BException
	{
		setPaginaRetorno(as_pagina);
		detalleTurno();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleTurno()
	{
		try
		{
			String                     ls_idTurno;
			String                     ls_idTurnoHistoria;
			Long                       ll_etapa;
			String                     ls_decisionCalificacion;
			Collection<NotaDevolutiva> lcnd_notaDevolutiva;

			ls_idTurno                  = FacesUtils.getStringFacesParameter("idTurno");
			ls_idTurnoHistoria          = null;
			ll_etapa                    = null;
			ls_decisionCalificacion     = null;
			lcnd_notaDevolutiva         = null;

			if(StringUtils.isValidString(ls_idTurno))
			{
				Trazabilidad lt_trazabilidad;

				lt_trazabilidad = ictr_consultaTrazabilidadRemote.detalleTurno(new Turno(ls_idTurno));

				if(lt_trazabilidad != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = lt_trazabilidad.getTurnoHistoria();

					if(lth_turnoHistoria != null)
					{
						ls_idTurnoHistoria     = StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria());
						ll_etapa               = NumericUtils.getLongWrapper(lth_turnoHistoria.getIdEtapa());
					}

					setConsultaTrazabilidad(lt_trazabilidad.getTrazabilidad());
					ls_decisionCalificacion     = lt_trazabilidad.getDecisionCalificacion();
					lcnd_notaDevolutiva         = lt_trazabilidad.getNotaDevolutiva();
				}
			}

			{
				FacesContext            lfc_context;
				BeanPredioDocumentoActo lbpdab_bean;

				lfc_context = FacesUtils.getFacesContext();

				{
					lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
							                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
							);

					if(lbpdab_bean != null)
					{
						lbpdab_bean.limpiarDatos();
						lbpdab_bean.setVieneTrazabilidad(true);
						lbpdab_bean.setIdEtapa(ll_etapa);
						lbpdab_bean.setIdTurno(ls_idTurno);
						lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
						lbpdab_bean.generarDatosAntSistema();
						lbpdab_bean.generarDatosDocumento();
						lbpdab_bean.generarDatosTramitesVinculados(true);
						lbpdab_bean.obtenerInformacionASEtapa101();
						lbpdab_bean.validarFechaVencimientoActo();
						lbpdab_bean.setMotivoTramite(null);
						lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
						lbpdab_bean.setCausalesDevolucionesPanel(lcnd_notaDevolutiva);
						lbpdab_bean.getMatriculasRangos();
						lbpdab_bean.getMatriculasIndividuales();
						lbpdab_bean.getMatriculasTmpRangos();
						lbpdab_bean.getMatriculasTmpIndividuales();
						lbpdab_bean.cargarTurnosTramiteAsociados();

						PrimeFaces.current().ajax().update("fConsultaTrazabilidadDetalle:globalMsg");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.DETALLE_TRAZABILIDAD;
	}

	/**
	 * Find all.
	 */
	public void findAll()
	{
		try
		{
			String               ls_nir;
			String               ls_idTurno;
			String               ls_expediente;
			Solicitud            ls_solicitud;
			Turno                lt_turno;
			Trazabilidad         lt_trazabilidad;
			ConsultaTrazabilidad lct_trazabilidad;

			ls_nir              = getNir();
			ls_idTurno          = getIdTurnoConsulta();
			ls_expediente       = getExpedienteConsulta();
			ls_solicitud        = new Solicitud();
			lt_turno            = new Turno();
			lt_trazabilidad     = new Trazabilidad();
			ls_nir              = StringUtils.isValidString(ls_nir) ? StringUtils.getStringUpperCase(ls_nir) : null;
			ls_idTurno          = StringUtils.isValidString(ls_idTurno) ? ls_idTurno.trim() : null;
			ls_expediente       = StringUtils.isValidString(ls_expediente)
				? StringUtils.getStringUpperCase(ls_expediente) : null;

			ls_solicitud.setNir(ls_nir);
			lt_turno.setIdTurno(ls_idTurno);
			lt_turno.setExpediente(ls_expediente);
			lt_trazabilidad.setSolicitud(ls_solicitud);
			lt_trazabilidad.setTurno(lt_turno);

			setBandejaHistoricos(false);

			lct_trazabilidad = ictr_consultaTrazabilidadRemote.cargarInfoBandejaConsultaTrazabilidad(
				    lt_trazabilidad, false
				);

			if(lct_trazabilidad != null)
			{
				setTrazabilidadInfo(lct_trazabilidad.getTrazabilidadInfo());
				setEstado(lct_trazabilidad.isEstado());
				setTrazabilidad(lct_trazabilidad.getTrazabilidad());
				setNirPrincipal(lct_trazabilidad.getNirPrincipal());
				setTurnosVinculadosNirPrincipal(lct_trazabilidad.getTurnosVinculadosNirPrincipal());
				setTurnosVinculadosNirVinculados(lct_trazabilidad.getTurnosVinculadosNirVinculados());
				setNir(ls_nir);
				setIdTurnoConsulta(ls_idTurno);
				setExpedienteConsulta(ls_expediente);
				setTurnoAfectado(lct_trazabilidad.getTurnoAfectado());

				{
					Collection<NirVinculado> lcnv_nirVinculado;

					lcnv_nirVinculado = lct_trazabilidad.getNirVinculados();

					if(CollectionUtils.isValidCollection(lcnv_nirVinculado))
					{
						for(NirVinculado lnv_tmp : lcnv_nirVinculado)
						{
							if(lnv_tmp != null)
							{
								Solicitud ls_solicitudNirVinculado;

								ls_solicitudNirVinculado = lnv_tmp.getSolicitud();

								if(ls_solicitudNirVinculado != null)
								{
									Date ld_fechaCreacion;

									ld_fechaCreacion = ls_solicitudNirVinculado.getFechaCreacion();

									if(ld_fechaCreacion != null)
										ls_solicitudNirVinculado.setFechaCreacionString(
										    StringUtils.getString(
										        ld_fechaCreacion, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
										    )
										);
								}
							}
						}
					}

					setNirVinculados(lcnv_nirVinculado);
				}

				{
					String ls_mensaje;

					ls_mensaje = lct_trazabilidad.getMensaje();

					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);

					setRenderConsultaSGD(StringUtils.isValidString(ls_mensaje));

					if(isRenderConsultaSGD())
						addMessage(EstadoCommon.I, ls_mensaje);
				}
			}

			{
				PrimeFaces lpf_current;
				lpf_current = PrimeFaces.current();

				if(getNirPrincipal() != null)
					lpf_current.executeScript("PF('idDataTramiteRecepcionDocumentos').expand();");
				else
					lpf_current.executeScript("PF('idDataTramiteRecepcionDocumentos').collapse();");

				if(getTurnosVinculadosNirPrincipal() != null)
					lpf_current.executeScript("PF('idDataTurnosVinculados').expand();");
				else
					lpf_current.executeScript("PF('idDataTurnosVinculados').collapse();");
			}
		}
		catch(B2BException lb2be_e)
		{
			setTrazabilidadInfo(null);
			setEstado(false);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Find consulta traza.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findConsultaTraza()
	    throws B2BException
	{
		Turno  lt_t;
		String ls_idTurno;

		lt_t           = new Turno();
		ls_idTurno     = FacesUtils.getStringFacesParameter("idTurno");

		if(StringUtils.isValidString(ls_idTurno))
		{
			lt_t.setIdTurno(ls_idTurno);
			setIdTurnoConsulta(ls_idTurno);
			setConsultaTrazabilidad(ictr_consultaTrazabilidadRemote.findConsultaTraza(lt_t));
		}
	}

	/**
	 *  Método para encontrar todos los registros de la trazabilidad por medio del idSolicitud
	 * @return Collection de Consulta Trazabilidad
	 */
	public String findConsultaTrazabilidadSolicitud()
	{
		try
		{
			String ls_consultaTrazaSol;

			ls_consultaTrazaSol = FacesUtils.getStringFacesParameter("nirTraza");

			if(StringUtils.isValidString(ls_consultaTrazaSol))
			{
				Collection<ConsultaTrazabilidad> lcct_consultaTraza;

				lcct_consultaTraza = ictr_consultaTrazabilidadRemote.findConsultaTrazaSolicitud(ls_consultaTrazaSol);

				if(CollectionUtils.isValidCollection(lcct_consultaTraza))
					setConsultaTrazabilidadNir(lcct_consultaTraza);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.DETALLE_TRAZABILIDAD_SOLICITUD;
	}

	/**
	 * Find data asociada.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findDataAsociada()
	    throws B2BException
	{
		String ls_idTurno;

		ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

		if(StringUtils.isValidString(ls_idTurno))
		{
			Collection<Trazabilidad> lct_data;

			lct_data = ictr_consultaTrazabilidadRemote.findDataAsociada(ls_idTurno);

			setTramitesAsociados(CollectionUtils.isValidCollection(lct_data));
			setDataAsociada(lct_data);
		}
	}

	/**
	 * Find info doc.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void findInfoDoc()
	    throws B2BException
	{
		Turno  lt_t;
		String ls_idTurno;

		lt_t           = new Turno();
		ls_idTurno     = FacesUtils.getStringFacesParameter("idTurno");

		if(StringUtils.isValidString(ls_idTurno))
		{
			lt_t.setIdTurno(ls_idTurno);
			setInformacionDocumento(ictr_consultaTrazabilidadRemote.findInfoDoc(lt_t));
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setTrazabilidad(null);
		setInformacionDocumento(null);
		setConsultaTrazabilidad(null);
		setConsultaTrazabilidadNir(null);
	}

	/**
	 * Método para mostrar datos de Reasignación / Tipificación de turno y obtener datos.
	 *
	 * @param at_t correspondiente al valor del tipo de objeto Trazabilidad
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 */
	public void mostrarCuadroDatosReasignacion(Trazabilidad at_t, boolean ab_b)
	{
		try
		{
			if(ab_b)
			{
				if(at_t != null)
				{
					String ls_turno;

					ls_turno = at_t.getTurno().getIdTurno();

					if(ls_turno != null)
					{
						ReasignarTurnos lrt_rt;

						lrt_rt = new ReasignarTurnos();

						lrt_rt.setIdTurno(ls_turno);

						lrt_rt = ictr_consultaTrazabilidadRemote.findDatosReasignacion(lrt_rt);

						if(lrt_rt != null)
						{
							String ls_turnosAsociados;

							ls_turnosAsociados = ictr_consultaTrazabilidadRemote
									.findTurnosDerivadosConIndicadorVinculado(ls_turno);

							if(StringUtils.isValidString(ls_turnosAsociados))
							{
								lrt_rt.setIdTurnoHijo(ls_turnosAsociados);
								setReasignarTurnos(lrt_rt);
							}
						}
					}
				}

				PrimeFaces.current().executeScript("PF('cdrDatosReasignacion').show();");
				PrimeFaces.current().ajax().update("fDialog:idTableTip");
			}
			else
				PrimeFaces.current().executeScript("PF('cdrDatosReasignacion').hide();");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("reasignarTurnos:globalMsg");
		}

		PrimeFaces.current().ajax().update("fCalificacion:idGrowl");
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresar()
	{
		String ls_paginaRetorno;

		ls_paginaRetorno = getPaginaRetorno();

		return StringUtils.isValidString(ls_paginaRetorno) ? ls_paginaRetorno : NavegacionCommon.CONSULTA_TRAZABILIDAD;
	}
}
