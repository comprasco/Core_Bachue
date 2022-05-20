package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.certificados.CertificadosRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

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


/**
 * Clase para el manejo de la capa web para Analista de certificados especiales.
 *
 * @author Jorge Patiño
 */
@SessionScoped
@ManagedBean(name = "beanAnalistaDeCertificadosEspeciales")
public class BeanAnalistaDeCertificadosEspeciales extends BeanTurnos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7653440933638053963L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAnalistaDeCertificadosEspeciales.class);

	/** Propiedad icr certificados remote. */
	@EJB
	private CertificadosRemote icr_certificadosRemote;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad ictc turnos suspendidos. */
	private Collection<TramiteCantidad> ictc_turnosSuspendidos;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** Propiedad ii total bandeja suspendidos. */
	private int ii_totalBandejaSuspendidos;

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
	 * Modifica el valor de id proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna el valor de id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id sub proceso
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna el valor de id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
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

	/** {@inheritdoc} */
	@Override
	public String accionVolver()
	{
		try
		{
			clear();
			generarDatosBandeja();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setDatosTramiteCantidad(null);
		setIdProceso(null);
		setIdSubProceso(null);
		setTotalBandeja(0);
		setIdEtapa(0);
		setMostrarGrafica(false);
		setNirConsulta(null);
		setIdTurnoConsulta(null);
		setTotalBandeja(0);
	}

	/**
	 * Generar data detalle.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDataDetalle()
	    throws B2BException
	{
		generarDataDetalle(false);
	}

	/**
	 * Generar data detalle.
	 *
	 * @param ab_consultasProcesos correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDataDetalle(boolean ab_consultasProcesos)
	    throws B2BException
	{
		String ls_idProceso;
		String ls_idSubProceso;

		ls_idProceso        = getIdProceso();
		ls_idSubProceso     = getIdSubProceso();

		if(StringUtils.isValidString(ls_idSubProceso) && StringUtils.isValidString(ls_idProceso))
		{
			TramiteCantidad ltc_tc;
			boolean         lb_consultaExitosa;

			ltc_tc                 = new TramiteCantidad();
			lb_consultaExitosa     = false;

			if(ab_consultasProcesos)
				ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_CONSULTAS_PROCESOS));
			else
				ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES));

			ltc_tc.setUsuario(getUserId());
			ltc_tc.setIdProceso(ls_idProceso);
			ltc_tc.setIdSubProceso(ls_idSubProceso);
			ltc_tc.setIdTurno(getIdTurnoConsulta());
			ltc_tc.setNir(getNirConsulta());

			ltc_tc = icr_certificadosRemote.consultaBandeja(
				    ltc_tc, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ltc_tc != null)
			{
				Collection<TramiteCantidad> lctc_turnos;
				lctc_turnos = ltc_tc.getTurnos();

				if(!CollectionUtils.isValidCollection(lctc_turnos))
					lctc_turnos = new ArrayList<TramiteCantidad>();
				else
					lb_consultaExitosa = true;

				setDatosTramiteCantidad(lctc_turnos);
			}

			if(lb_consultaExitosa)
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
		}
	}

	/**
	 * Generar datos bandeja.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosBandeja()
	    throws B2BException
	{
		generarDatosBandeja(false);
	}

	/**
	 * Generar datos bandeja.
	 *
	 * @param ab_consultasProcesos correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosBandeja(boolean ab_consultasProcesos)
	    throws B2BException
	{
		TramiteCantidad lctc_tramitesCantidad;

		lctc_tramitesCantidad = null;

		try
		{
			TramiteCantidad ltc_tc;
			int             li_cantidadPorEtapas;
			int             li_cantidadSuspendidos;

			ltc_tc                     = new TramiteCantidad();
			li_cantidadPorEtapas       = 0;
			li_cantidadSuspendidos     = 0;

			if(ab_consultasProcesos)
				ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_CONSULTAS_PROCESOS));
			else
				ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES));

			ltc_tc.setUsuario(getUserId());
			ltc_tc.setIdTurno(getIdTurnoConsulta());
			ltc_tc.setNir(getNirConsulta());

			lctc_tramitesCantidad = icr_certificadosRemote.consultaBandeja(
				    ltc_tc, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lctc_tramitesCantidad != null)
			{
				boolean                     lb_consultaExitosa;
				Collection<TramiteCantidad> lctc_turnos;

				lctc_turnos            = lctc_tramitesCantidad.getTurnos();
				lb_consultaExitosa     = false;

				if(CollectionUtils.isValidCollection(lctc_turnos))
				{
					for(TramiteCantidad ltc_actual : lctc_turnos)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setDatosTramiteCantidad(lctc_turnos);
					setTotalBandeja(li_cantidadPorEtapas);
				}
				else
				{
					setDatosTramiteCantidad(new ArrayList<TramiteCantidad>());
					setTotalBandeja(li_cantidadPorEtapas);
				}

				Collection<TramiteCantidad> lctc_tramitesSuspendidos;
				lctc_tramitesSuspendidos = lctc_tramitesCantidad.getTurnosSuspendidos();

				if(CollectionUtils.isValidCollection(lctc_tramitesSuspendidos))
				{
					for(TramiteCantidad ltc_actual : lctc_tramitesSuspendidos)
					{
						if(ltc_actual != null)
							li_cantidadSuspendidos = li_cantidadSuspendidos
								+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
								? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
					}

					lb_consultaExitosa = true;
					setTurnosSuspendidos(lctc_tramitesSuspendidos);
					setTotalBandejaSuspendidos(li_cantidadSuspendidos);
				}
				else
				{
					setTurnosSuspendidos(new ArrayList<TramiteCantidad>());
					setTotalBandejaSuspendidos(li_cantidadPorEtapas);
				}

				if(lb_consultaExitosa)
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
			}

			if(li_cantidadPorEtapas <= 0)
			{
				setDatosTramiteCantidad(new ArrayList<TramiteCantidad>());

				setTurnosSuspendidos(new ArrayList<TramiteCantidad>());

				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update("fCalificacion:idGrowl");
	}

	/**
	 * Generar torta.
	 *
	 * @return el valor de pie chart model
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
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
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
		return returnPages(null);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param atc_tc correspondiente al valor del tipo de objeto TramiteCantidad
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages(TramiteCantidad atc_tc)
	    throws B2BException
	{
		String ls_return;
		ls_return = NavegacionCommon.DETALLE_TURNO_ANALISTA_CERTIFICADOS;

		try
		{
			if(atc_tc != null)
			{
				FacesContext                                lfc_context;
				BeanDetalleAnalistaDeCertificadosEspeciales lbdadce_bdadce;
				Long                                        ll_idTurnoHistoria;
				Solicitud                                   ls_solicitud;

				lfc_context            = FacesUtils.getFacesContext();
				lbdadce_bdadce         = (BeanDetalleAnalistaDeCertificadosEspeciales)lfc_context.getApplication()
						                                                                             .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_DETALLE_ANALISTA_CERTIFICADOS,
						    BeanDetalleAnalistaDeCertificadosEspeciales.class
						);
				ll_idTurnoHistoria     = NumericUtils.getLongWrapper(atc_tc.getIdTurnoHistoria());
				ls_solicitud           = new Solicitud();

				ls_solicitud.setNir(atc_tc.getNir());
				lbdadce_bdadce.clear();
				lbdadce_bdadce.setSolicitud(ls_solicitud);
				lbdadce_bdadce.setIdTurnoHistoria(StringUtils.getString(ll_idTurnoHistoria));
				lbdadce_bdadce.setIdEtapa(NumericUtils.getLongWrapper(getIdEtapa()));
				lbdadce_bdadce.setIdTurno(atc_tc.getIdTurno());
				lbdadce_bdadce.getMotivosTramite();
				lbdadce_bdadce.generarData(ll_idTurnoHistoria);

				BeanPredioDocumentoActo lbpdab_bean;
				lbpdab_bean = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
					    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
					);

				if(lbpdab_bean != null)
				{
					Turno        lt_turnoTemp;
					String       ls_decisionCalificacion;
					Trazabilidad lt_trazaTemp;

					ls_decisionCalificacion     = null;
					lt_trazaTemp                = new Trazabilidad();
					lt_turnoTemp                = new Turno();

					lbpdab_bean.limpiarDatos();
					lbpdab_bean.setIdTurnoHistoria(StringUtils.getString(ll_idTurnoHistoria));
					lbpdab_bean.setIdTurnoConsulta(atc_tc.getIdTurno());

					lt_turnoTemp.setIdTurno(atc_tc.getIdTurno());

					lt_trazaTemp.setTurno(lt_turnoTemp);

					ls_decisionCalificacion = irr_referenceRemote.findDecisionCalificacion(lt_trazaTemp);

					lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
				}

				{
					TurnoHistoria lth_th;

					lth_th = new TurnoHistoria();

					lth_th.setIdTurnoHistoria(ll_idTurnoHistoria);

					validarFechaInicialEtapa(lth_th);
				}

				{
					ConsultaDatosDocumento lcdd_consultaDatos;

					lcdd_consultaDatos = lbdadce_bdadce.getConsultaDatosDocumento();

					if(lcdd_consultaDatos != null)
					{
						OficinaOrigen loo_oficinaOrigen;

						loo_oficinaOrigen = lcdd_consultaDatos.getOficinaOrigen();

						if(loo_oficinaOrigen != null)
						{
							String ls_idPais;

							ls_idPais = loo_oficinaOrigen.getIdPais();

							if(!StringUtils.isValidString(ls_idPais))
								loo_oficinaOrigen.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						}
					}
				}

				ls_return = NavegacionCommon.CERTIFICADOS_ESPECIALES;
			}
			else
			{
				setIdProceso(FacesUtils.getStringFacesParameter("idProceso"));
				setIdSubProceso(FacesUtils.getStringFacesParameter("idSubproceso"));
				setIdEtapa(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES);
				generarDataDetalle();
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}
}
