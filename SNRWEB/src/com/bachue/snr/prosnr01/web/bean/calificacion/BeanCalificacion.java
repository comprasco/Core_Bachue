package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.digitador.masivo.BeanDigitadorMasivo;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCalificacion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanCalificacion")
public class BeanCalificacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2096507104369954298L;

	/** Constante is_idForm. */
	private static final String is_idForm = ":fCalificacion";

	/** Propiedad ip predio registro. */
	private com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro ip_predioRegistro;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad ictc turnos desistimiento. */
	private Collection<TramiteCantidad> ictc_turnosDesistimiento;

	/** Propiedad ictc turnos vinculados. */
	private Collection<TramiteCantidad> ictc_turnosProhibicion;

	/** Propiedad ictc turnos suspendidos. */
	private Collection<TramiteCantidad> ictc_turnosSuspendidos;

	/** Propiedad ictc turnos vinculados. */
	private Collection<TramiteCantidad> ictc_turnosVinculados;

	/** Propiedad is id etapa. */
	private String is_idEtapa;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad ib etapa valid. */
	private boolean ib_etapaValid;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** Propiedad ii total bandeja desistimiento. */
	private int ii_totalBandejaDesistimiento;

	/** Propiedad ii total bandeja vinculados. */
	private int ii_totalBandejaProhibicion;

	/** Propiedad ii total bandeja suspendidos. */
	private int ii_totalBandejaSuspendidos;

	/** Propiedad ii total bandeja vinculados. */
	private int ii_totalBandejaVinculados;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos tramite cantidad.
	 *
	 * @param actc_ctc asigna el valor a la propiedad datos tramite cantidad
	 */
	public void setDatosTramiteCantidad(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_datosTramiteCantidad = actc_ctc;
	}

	/**
	 * Retorna el valor de datos tramite cantidad.
	 *
	 * @return el valor de datos tramite cantidad
	 */
	public Collection<TramiteCantidad> getDatosTramiteCantidad()
	{
		return ictc_datosTramiteCantidad;
	}

	/**
	 * Modifica el valor de etapa valid.
	 *
	 * @param ab_b asigna el valor a la propiedad etapa valid
	 */
	public void setEtapaValid(boolean ab_b)
	{
		ib_etapaValid = ab_b;
	}

	/**
	 * Valida la propiedad etapa valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en etapa valid
	 */
	public boolean isEtapaValid()
	{
		return ib_etapaValid;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param as_s asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(String as_s)
	{
		is_idEtapa = as_s;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public String getIdEtapa()
	{
		return is_idEtapa;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_i asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_i)
	{
		is_idTurnoConsulta = as_i;
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
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_n asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_n)
	{
		is_nirConsulta = as_n;
	}

	/**
	 * Retorna el valor de nir consulta.
	 *
	 * @return el valor de nir consulta
	 */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * Modifica el valor de predio registro.
	 *
	 * @param apr_pr asigna el valor a la propiedad predio registro
	 */
	public void setPredioRegistro(com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro apr_pr)
	{
		ip_predioRegistro = apr_pr;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @return el valor de predio registro
	 */
	public com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro getPredioRegistro()
	{
		if(ip_predioRegistro == null)
			ip_predioRegistro = new com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro();

		return ip_predioRegistro;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * Modifica el valor de total bandeja desistimiento.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja desistimiento
	 */
	public void setTotalBandejaDesistimiento(int ai_i)
	{
		ii_totalBandejaDesistimiento = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja desistimiento.
	 *
	 * @return el valor de total bandeja desistimiento
	 */
	public int getTotalBandejaDesistimiento()
	{
		return ii_totalBandejaDesistimiento;
	}

	/**
	 * Modifica el valor de total bandeja suspendidos.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja suspendidos
	 */
	public void setTotalBandejaSuspendidos(int ai_i)
	{
		ii_totalBandejaSuspendidos = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja suspendidos.
	 *
	 * @return el valor de total bandeja suspendidos
	 */
	public int getTotalBandejaSuspendidos()
	{
		return ii_totalBandejaSuspendidos;
	}

	/**
	 * Modifica el valor de total bandeja vinculados.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja vinculados
	 */
	public void setTotalBandejaVinculados(int ai_i)
	{
		ii_totalBandejaVinculados = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja vinculados.
	 *
	 * @return el valor de total bandeja vinculados
	 */
	public int getTotalBandejaVinculados()
	{
		return ii_totalBandejaVinculados;
	}

	/**
	 * Modifica el valor de total bandeja vinculados.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja vinculados
	 */
	public void setTotalBandejaProhibicion(int ai_i)
	{
		ii_totalBandejaProhibicion = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja vinculados.
	 *
	 * @return el valor de total bandeja vinculados
	 */
	public int getTotalBandejaProhibicion()
	{
		return ii_totalBandejaProhibicion;
	}

	/**
	 * Modifica el valor de turnos desistimiento.
	 *
	 * @param actc_ctc asigna el valor a la propiedad turnos desistimiento
	 */
	public void setTurnosDesistimiento(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_turnosDesistimiento = actc_ctc;
	}

	/**
	 * Retorna el valor de turnos desistimiento.
	 *
	 * @return el valor de turnos desistimiento
	 */
	public Collection<TramiteCantidad> getTurnosDesistimiento()
	{
		return ictc_turnosDesistimiento;
	}

	/**
	 * Modifica el valor de turnos suspendidos.
	 *
	 * @param actc_tc asigna el valor a la propiedad turnos suspendidos
	 */
	public void setTurnosSuspendidos(Collection<TramiteCantidad> actc_tc)
	{
		ictc_turnosSuspendidos = actc_tc;
	}

	/**
	 * Retorna el valor de turnos suspendidos.
	 *
	 * @return el valor de turnos suspendidos
	 */
	public Collection<TramiteCantidad> getTurnosSuspendidos()
	{
		return ictc_turnosSuspendidos;
	}

	/**
	 * Modifica el valor de turnos vinculados.
	 *
	 * @param actc_tc asigna el valor a la propiedad turnos vinculados
	 */
	public void setTurnosVinculados(Collection<TramiteCantidad> actc_tc)
	{
		ictc_turnosVinculados = actc_tc;
	}

	/**
	 * Retorna el valor de turnos vinculados.
	 *
	 * @return el valor de turnos vinculados
	 */
	public Collection<TramiteCantidad> getTurnosVinculados()
	{
		return ictc_turnosVinculados;
	}

	/**
	 * Modifica el valor de turnos vinculados.
	 *
	 * @param actc_tc asigna el valor a la propiedad turnos vinculados
	 */
	public void setTurnosProhibicion(Collection<TramiteCantidad> actc_tc)
	{
		ictc_turnosProhibicion = actc_tc;
	}

	/**
	 * Retorna el valor de turnos vinculados.
	 *
	 * @return el valor de turnos vinculados
	 */
	public Collection<TramiteCantidad> getTurnosProhibicion()
	{
		return ictc_turnosProhibicion;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setIdEtapa(null);
		setNirConsulta(null);
		setIdTurnoConsulta(null);
		setPredioRegistro(null);
		setDatosTramiteCantidad(null);
		setTotalBandeja(0);
	}

	/**
	 * Generar datos tramite cantidad.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		generarDatosTramiteCantidad(is_idForm, null, true);
	}

	/**
	 * Generar datos tramite cantidad.
	 *
	 * @param as_formulario correspondiente al valor del tipo de objeto String
	 * @param as_idEtapa correspondiente al valor del tipo de objeto String
	 * @param ab_showMessage correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramiteCantidad(String as_formulario, String as_idEtapa, boolean ab_showMessage)
	    throws B2BException
	{
		try
		{
			TramiteCantidad ltc_tc;
			Turno           lt_t;

			ltc_tc     = new TramiteCantidad();
			lt_t       = new Turno();

			lt_t.setIdUsuarioCreacion(getUserId());

			String ls_nir;
			ls_nir = getNirConsulta();

			if(StringUtils.isValidString(ls_nir))
				ls_nir = ls_nir.toUpperCase();

			lt_t.setNir(ls_nir);
			lt_t.setIdTurno(getIdTurnoConsulta());

			if(StringUtils.isValidString(as_idEtapa))
				lt_t.setIdEtapaActual(NumericUtils.getLongWrapper(as_idEtapa));

			ltc_tc = irr_calificacionRemote.findInboxByUserId(lt_t, 0);

			if(ltc_tc != null)
			{
				boolean                     lb_consultaExitosa;
				int                         li_cantidadPorEtapas;
				Collection<TramiteCantidad> lctc_tmp;

				lb_consultaExitosa       = false;
				li_cantidadPorEtapas     = 0;
				lctc_tmp                 = ltc_tc.getTurnos();

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteCantidad ltc_actual : lctc_tmp)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setDatosTramiteCantidad(lctc_tmp);
					setTotalBandeja(li_cantidadPorEtapas);
				}
				else
				{
					setDatosTramiteCantidad(new ArrayList<TramiteCantidad>());
					setTotalBandeja(li_cantidadPorEtapas);
				}

				lctc_tmp                 = ltc_tc.getTurnosDesistimiento();
				li_cantidadPorEtapas     = 0;

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteCantidad ltc_actual : lctc_tmp)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setTurnosDesistimiento(lctc_tmp);
					setTotalBandejaDesistimiento(li_cantidadPorEtapas);
				}
				else
				{
					setTurnosDesistimiento(new ArrayList<TramiteCantidad>());
					setTotalBandejaDesistimiento(li_cantidadPorEtapas);
				}

				lctc_tmp                 = ltc_tc.getTurnosVinculados();
				li_cantidadPorEtapas     = 0;

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteCantidad ltc_actual : lctc_tmp)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setTurnosVinculados(lctc_tmp);
					setTotalBandejaVinculados(li_cantidadPorEtapas);
				}
				else
				{
					setTurnosVinculados(new ArrayList<TramiteCantidad>());
					setTotalBandejaVinculados(li_cantidadPorEtapas);
				}

				lctc_tmp                 = ltc_tc.getTurnosProhibicion();
				li_cantidadPorEtapas     = 0;

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteCantidad ltc_actual : lctc_tmp)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setTurnosProhibicion(lctc_tmp);
					setTotalBandejaProhibicion(li_cantidadPorEtapas);
				}
				else
				{
					setTurnosProhibicion(new ArrayList<TramiteCantidad>());
					setTotalBandejaProhibicion(li_cantidadPorEtapas);
				}

				lctc_tmp                 = ltc_tc.getTurnosSuspendidos();
				li_cantidadPorEtapas     = 0;

				if(CollectionUtils.isValidCollection(lctc_tmp))
				{
					for(TramiteCantidad ltc_actual : lctc_tmp)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setTurnosSuspendidos(lctc_tmp);
					setTotalBandejaSuspendidos(li_cantidadPorEtapas);
				}
				else
				{
					setTurnosSuspendidos(new ArrayList<TramiteCantidad>());
					setTotalBandejaSuspendidos(li_cantidadPorEtapas);
				}

				if(lb_consultaExitosa && ab_showMessage)
				{
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);

					PrimeFaces.current().ajax().update(as_formulario);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
			}
		}
		catch(B2BException lb2be_e)
		{
			setDatosTramiteCantidad(null);
			setTurnosDesistimiento(null);
			setTurnosVinculados(null);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(as_formulario + ":idGrowl");
		}
	}

	/**
	 * Generar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Mostrar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages()
	    throws B2BException
	{
		String             ls_return;
		String             ls_idEtapa;
		String             ls_idEtapaAnt;
		Long               ll_idEtapa;
		String             ls_idMotivo;
		String             ls_indVinculado;
		String             ls_indProhibicion;
		FacesContext       lfc_context;
		HttpServletRequest lhsr_httpServletRequest;

		ls_return                   = null;
		ls_idEtapa                  = null;
		ls_idEtapaAnt               = null;
		ls_idMotivo                 = null;
		ls_indVinculado             = null;
		ls_indProhibicion           = null;
		lhsr_httpServletRequest     = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
				                                                          .getRequest();

		if(isEtapaValid())
			ls_idEtapa = getIdEtapa();
		else
			ls_idEtapa = lhsr_httpServletRequest.getParameter("idEtapa");

		ls_idMotivo           = lhsr_httpServletRequest.getParameter("idMotivo");
		ls_idEtapaAnt         = lhsr_httpServletRequest.getParameter("idEtapaAnt");
		ls_indVinculado       = lhsr_httpServletRequest.getParameter("indVinculado");
		ls_indProhibicion     = lhsr_httpServletRequest.getParameter("indProhibicion");

		lfc_context = FacesUtils.getFacesContext();

		BeanPredioDocumentoActo lbpd_bean;
		boolean                 lb_confrontacion;
		boolean                 lb_etapaUsaTurnos;
		boolean                 lb_grabacion;
		boolean                 lb_analisisTraslados;
		boolean                 lb_analisisTrasladosOficinaDestino;
		boolean                 lb_responsableActuacionesAdmin;
		boolean                 lb_sustanciadorActuacionesAdmin;
		boolean                 lb_bandejaResolucionRecursos;
		boolean                 lb_rechazoRecursos;

		lbpd_bean     = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);

		ls_idEtapa     = StringUtils.getStringNotNull(ls_idEtapa);

		lb_responsableActuacionesAdmin     = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
			);

		lb_sustanciadorActuacionesAdmin     = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
			);

		lb_bandejaResolucionRecursos     = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
			);

		lb_rechazoRecursos = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
			);

		lbpd_bean.setOcultarPaneles(false);
		lbpd_bean.setIdEtapa(NumericUtils.getLongWrapper(ls_idEtapa));
		lbpd_bean.obtenerInformacionASEtapa101();
		lbpd_bean.getMatriculasRangos();
		lbpd_bean.getMatriculasIndividuales();
		lbpd_bean.getMatriculasTmpRangos();
		lbpd_bean.getMatriculasTmpIndividuales();

		lb_confrontacion      = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA)
			);
		lb_etapaUsaTurnos     = lb_confrontacion
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_CORRECCIONES))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_460))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_430))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS))
				|| ls_idEtapa.equalsIgnoreCase(
				    String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
				) || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_380))
				|| lb_responsableActuacionesAdmin || lb_sustanciadorActuacionesAdmin || lb_bandejaResolucionRecursos
				|| lb_rechazoRecursos
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS))
				|| ls_idEtapa.equalsIgnoreCase(
				    String.valueOf(EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
				) || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO))
				|| ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO));

		lb_grabacion                           = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
			);
		lb_analisisTraslados                   = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
			);
		lb_analisisTrasladosOficinaDestino     = ls_idEtapa.equalsIgnoreCase(
			    String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
			);

		if(
		    ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_CALIFICACION)) || lb_etapaUsaTurnos
			    || lb_grabacion
			    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES))
			    || ls_idEtapa.equalsIgnoreCase(
			        String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
			    )
		)
		{
			BeanTurnos lbrc_bean;

			lbrc_bean = (BeanTurnos)lfc_context.getApplication()
					                               .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_TURNOS, BeanTurnos.class
					);

			lbrc_bean.setRenderedCalificacion(false);
			lbrc_bean.setRenderedModDatosBasicos(false);
			lbrc_bean.setRenderedHomologacionActosLiquidacion(false);
			lbrc_bean.setGrabacionMatriculas(false);
			lbrc_bean.setAnalisisTraslados(lb_analisisTraslados);
			lbrc_bean.setAnalisisTrasladosOficinaDestino(lb_analisisTrasladosOficinaDestino);

			if(lb_confrontacion)
			{
				lbrc_bean.setIdEtapaMenu(NumericUtils.getLong(ls_idEtapa));
				lbrc_bean.setRenderedModDatosBasicos(true);
			}
			else if(ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION)))
			{
				lbrc_bean.setIdEtapaMenu(NumericUtils.getLong(ls_idEtapa));
				lbrc_bean.setRenderedHomologacionActosLiquidacion(true);
			}
			else if(
			    ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_CORRECCIONES))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_460))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_430))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS))
				    || ls_idEtapa.equalsIgnoreCase(
				        String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
				    ) || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_380))
				    || ls_idEtapa.equalsIgnoreCase(
				        String.valueOf(EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
				    ) || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S))
				    || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA))
				    || ls_idEtapa.equalsIgnoreCase(
				        String.valueOf(EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO)
				    ) || ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO))
			)
				lbrc_bean.setIdEtapaMenu(NumericUtils.getLong(ls_idEtapa));
			else if(lb_grabacion)
			{
				lbrc_bean.setIdEtapaMenu(NumericUtils.getLong(ls_idEtapa));
				lbrc_bean.setGrabacionMatriculas(true);
			}
			else if(
			    !lb_responsableActuacionesAdmin && !lb_sustanciadorActuacionesAdmin && !lb_bandejaResolucionRecursos
				    && !lb_rechazoRecursos
				    && !ls_idEtapa.equalsIgnoreCase(
				        String.valueOf(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
				    )
			)
			{
				lbrc_bean.setIdEtapaMenu(0);
				lbrc_bean.setRenderedCalificacion(true);
			}

			lbrc_bean.setIdEtapa(NumericUtils.getLong(ls_idEtapa));
			lbrc_bean.setIndVinculado(BooleanUtils.getBooleanValue(ls_indVinculado));
			lbrc_bean.setIndProhibicion(BooleanUtils.getBooleanValue(ls_indProhibicion));

			if(ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_CALIFICACION)))
				lbrc_bean.visualizarBandeja();

			lbrc_bean.setNirConsulta(getNirConsulta());
			lbrc_bean.setIdTurnoConsulta(getIdTurnoConsulta());
			lbrc_bean.setRenderedRegresar(true);
			lbrc_bean.setRenderedCorrecciones(false);
			lbrc_bean.setRenderedDigitadorMasivo(false);
			lbrc_bean.setIdMotivo(NumericUtils.getLong(ls_idMotivo));
			lbrc_bean.setIdEtapaAnt(NumericUtils.getLong(ls_idEtapaAnt));

			{
				String  ls_detalleRecepcionDocumentos;
				boolean lb_detalleRecepcionDocumentos;

				ls_detalleRecepcionDocumentos     = lhsr_httpServletRequest.getParameter(
					    "idDetalleRecepcionDocumentos"
					);
				lb_detalleRecepcionDocumentos     = BooleanUtils.getBooleanValue(ls_detalleRecepcionDocumentos);

				if(lb_detalleRecepcionDocumentos && (lhsr_httpServletRequest != null))
				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setDetalleRecepcionDocumentos(lb_detalleRecepcionDocumentos);
					lt_turno.setIdProceso(lhsr_httpServletRequest.getParameter("idProceso"));
					lt_turno.setIdSubProceso(lhsr_httpServletRequest.getParameter("idSubProceso"));

					lbrc_bean.generarData(lt_turno);
				}
				else
					lbrc_bean.generarData();
			}

			ls_return = NavegacionCommon.TURNOS;
		}
		else if(ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_APROBACION)))
		{
			BeanAprobacion lbrc_bean;

			lbrc_bean      = (BeanAprobacion)lfc_context.getApplication()
					                                        .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
					);
			ll_idEtapa     = NumericUtils.getLongWrapper(ls_idEtapa);
			lbrc_bean.setDataBandeja(null);
			lbrc_bean.setIdEtapa(ll_idEtapa);
			lbrc_bean.findDataAprobacion(null);
			ls_return = NavegacionCommon.APROBACION;
		}
		else if(ls_idEtapa.equalsIgnoreCase(String.valueOf(EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO)))
		{
			BeanDigitadorMasivo lbdm_bean;

			lbdm_bean     = (BeanDigitadorMasivo)lfc_context.getApplication()
					                                            .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DIGITADOR_MASIVO, BeanDigitadorMasivo.class
					);

			ll_idEtapa = NumericUtils.getLongWrapper(ls_idEtapa);
			lbdm_bean.setIdEtapa(ll_idEtapa);
			lbdm_bean.clear();
			lbdm_bean.generarData();
			ls_return = NavegacionCommon.TURNOS_DIGITADOR_MASIVO;
		}

		return ls_return;
	}
}
