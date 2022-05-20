package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.correcciones.BeanCorreccionInterna;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanGrabacion.
 *
 * @author dbeltran
 */
@SessionScoped
@ManagedBean(name = "beanGrabacion")
public class BeanGrabacion extends BeanRecepcionDocumentos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7512166333931033525L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanGrabacion.class);

	/** Constante is_formularioDetalleGrabacion. */
	private static final String is_formularioDetalleGrabacion = ":fDetalleGrabacion";

	/** Constante is_formularioGrabacion. */
	private static final String is_formularioGrabacion = ":fGrabacion";

	/** Constante is_formularioGrabarMatricula. */
	private static final String is_formularioGrabarMatricula = ":fGrabarMatricula";

	/** Constante is_formularioGrabacionMatriculas. */
	private static final String is_formularioGrabacionMatriculas = ":fGrabacionMatriculas";

	/** Constante is_formularioDetalleGrabacionMatriculas. */
	private static final String is_formularioDetalleGrabacionMatriculas = ":fDetalleGrabacionMatriculas";

	/** Constante is_formularioProcesosGrabacion. */
	private static final String is_formularioProcesosGrabacion = ":fProcesosGrabacion";

	/** Constante is_globalMsg. */
	private static final String is_globalMsg = ":globalMsg";

	/** Constante is_messageGrabacionMatriculas. */
	private static final String is_messageGrabacionMatriculas = is_formularioGrabacionMatriculas + is_globalMsg;

	/** Constante is_messageDetalleGrabacionMatriculas. */
	private static final String is_messageDetalleGrabacionMatriculas = is_formularioDetalleGrabacionMatriculas
		+ is_globalMsg;

	/** Constante is_messageDetalleGrabacion. */
	private static final String is_messageDetalleGrabacion = is_formularioDetalleGrabacion + is_globalMsg;

	/** Constante is_messageProcesosGrabacion. */
	private static final String is_messageProcesosGrabacion = is_formularioProcesosGrabacion + is_globalMsg
		+ is_globalMsg;

	/** Propiedad is miga pan. */
	String is_migaPan;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/** Propiedad il numero anotaciones. */
	private Long il_numeroAnotaciones;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad is observaciones proceso anterior. */
	private String is_observacionesProcesoAnterior;
	private byte[] iba_archivo;

	/** Propiedad ib bandejaentrada. */
	private boolean ib_bandejaentrada;

	/** Propiedad ib bdatos guardados. */
	private boolean ib_bdatosGuardados;

	/** Propiedad ib consulta individual. */
	private boolean ib_consultaIndividual;
	private boolean ib_generar;

	/** Propiedad ib mostrar grafica. */
	private boolean ib_mostrarGrafica;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/**
	 * Modifica el valor de archivo
	 * @param iba_archivo valor a la propiedad archivo
	 */
	public void setArchivo(byte[] iba_archivo)
	{
		this.iba_archivo = iba_archivo;
	}

	/**
	 * Retorna el valor de archivo
	 *
	 * @return el valor de archivo
	 */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * Modifica el valor de bandejaentrada.
	 *
	 * @param ab_b asigna el valor a la propiedad bandejaentrada
	 */
	public void setBandejaentrada(boolean ab_b)
	{
		ib_bandejaentrada = ab_b;
	}

	/**
	 * Valida la propiedad bandejaentrada.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bandejaentrada
	 */
	public boolean isBandejaentrada()
	{
		return ib_bandejaentrada;
	}

	/**
	 * Modifica el valor de consulta individual.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta individual
	 */
	public void setConsultaIndividual(boolean ab_b)
	{
		ib_consultaIndividual = ab_b;
	}

	/**
	 * Valida la propiedad consulta individual.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta individual
	 */
	public boolean isConsultaIndividual()
	{
		return ib_consultaIndividual;
	}

	/**
	 * Sets the data.
	 *
	 * @param all_ll correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setData(List<Map<String, Object>> all_ll)
	{
		ll_data = all_ll;
	}

	/**
	 * Retorna el valor de data.
	 *
	 * @return el valor de data
	 */
	public List<Map<String, Object>> getData()
	{
		return ll_data;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de datos guardados.
	 *
	 * @param ab_b asigna el valor a la propiedad datos guardados
	 */
	public void setDatosGuardados(boolean ab_b)
	{
		ib_bdatosGuardados = ab_b;
	}

	/**
	 * Valida la propiedad datos guardados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos guardados
	 */
	public boolean isDatosGuardados()
	{
		return ib_bdatosGuardados;
	}

	/**
	 * Modifica el valor de datos tramite cantidad.
	 *
	 * @param adtc_dtc asigna el valor a la propiedad datos tramite cantidad
	 */
	public void setDatosTramiteCantidad(Collection<TramiteCantidad> adtc_dtc)
	{
		ictc_datosTramiteCantidad = adtc_dtc;
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
	 * @param ab_b the generar to set
	 */
	public void setGenerar(boolean ab_b)
	{
		ib_generar = ab_b;
	}

	/**
	 * @return the generar
	 */
	public boolean isGenerar()
	{
		return ib_generar;
	}

	/** {@inheritdoc} */
	public String getIdTurno()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = null;
		lth_turnoHistoria     = getTurnoHistoria();

		if(lth_turnoHistoria != null)
			ls_return = lth_turnoHistoria.getIdTurno();

		return ls_return;
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

	/** {@inheritdoc} */
	public String getIdTurnoHistoria()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = null;
		lth_turnoHistoria     = getTurnoHistoria();

		if(lth_turnoHistoria != null)
		{
			Long ll_idTurnoHistoria;

			ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				ls_return = ll_idTurnoHistoria.toString();
		}

		return ls_return;
	}

	/**
	 * Modifica el valor de miga pan.
	 *
	 * @param as_s asigna el valor a la propiedad miga pan
	 */
	public void setMigaPan(String as_s)
	{
		is_migaPan = as_s;
	}

	/**
	 * Retorna el valor de miga pan.
	 *
	 * @return el valor de miga pan
	 */
	public String getMigaPan()
	{
		return is_migaPan;
	}

	/**
	 * Modifica el valor de mostrar grafica.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar grafica
	 */
	public void setMostrarGrafica(boolean ab_b)
	{
		ib_mostrarGrafica = ab_b;
	}

	/**
	 * Valida la propiedad mostrar grafica.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar grafica
	 */
	public boolean isMostrarGrafica()
	{
		return ib_mostrarGrafica;
	}

	/**
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_s asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
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
	 * Modifica el valor de numero anotaciones.
	 *
	 * @param al_l asigna el valor a la propiedad numero anotaciones
	 */
	public void setNumeroAnotaciones(Long al_l)
	{
		il_numeroAnotaciones = al_l;
	}

	/**
	 * Retorna el valor de numero anotaciones.
	 *
	 * @return el valor de numero anotaciones
	 */
	public Long getNumeroAnotaciones()
	{
		return il_numeroAnotaciones;
	}

	/**
	 * Modifica el valor de observaciones proceso anterior.
	 *
	 * @param as_S asigna el valor a la propiedad observaciones proceso anterior
	 */
	public void setObservacionesProcesoAnterior(String as_S)
	{
		is_observacionesProcesoAnterior = as_S;
	}

	/**
	 * Retorna el valor de observaciones proceso anterior.
	 *
	 * @return el valor de observaciones proceso anterior
	 */
	public String getObservacionesProcesoAnterior()
	{
		return is_observacionesProcesoAnterior;
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
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		setMotivoTramite(null);

		return NavegacionCommon.DETALLE_GRABACION;
	}

	/**
	 * Cargar data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarData()
	    throws B2BException
	{
		consultarPersonaInteresado(false);
		consultarPredioGrabar();
	}

	/**
	 * Consultar persona interesado.
	 *
	 * @param ab_fullData correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPersonaInteresado(boolean ab_fullData)
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = getTurnoHistoria();

		if(lth_turnoHistoria != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Suspension ls_parametros;

				ls_parametros = new Suspension();

				{
					DatosDelInteresado lddi_data;

					lddi_data = igr_grabacionRemote.consultarPersonaInteresado(ls_idSolicitud, ab_fullData);

					if(lddi_data != null)
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
						lbd_beanDireccion.setDeshabilitarResidencia(true);
						lbd_beanDireccion.setHabilitadoPorConsulta(true);
						lbd_beanDireccion.setForm(is_formularioProcesosGrabacion);
						lbd_beanDireccion.setDireccionCorrespondencia(lddi_data.getDireccionCorrespondencia());
						lbd_beanDireccion.setDireccionResidencia(lddi_data.getDireccionResidencia());
					}
					else
						lddi_data = new DatosDelInteresado();

					ls_parametros.setDatosDelInteresado(lddi_data);
				}

				if(ab_fullData)
					ls_parametros.setSolicitud2(new Solicitud());
				else
					ls_parametros.setSolicitud2(
					    irr_registroRemote.findSolicitudById(new Solicitud(ls_idSolicitud), getUserId())
					);

				setParametros(ls_parametros);
			}
		}
	}

	/**
	 * Consultar predio grabar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPredioGrabar()
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = getTurnoHistoria();

		if(lth_turnoHistoria != null)
			setDatosAntSistema(
			    igr_grabacionRemote.consultarPredioGrabar(
			        lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String detalleActoEtapa()
	    throws B2BException
	{
		BeanPredioDocumentoActo lbpdab_bean;
		FacesContext            lfc_context;
		String                  ls_idTurnoHistoria;
		String                  ls_idTurno;
		String                  ls_result;

		lfc_context            = FacesUtils.getFacesContext();
		lbpdab_bean            = (BeanPredioDocumentoActo)lfc_context.getApplication()
				                                                         .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
				);
		ls_idTurnoHistoria     = FacesUtils.getStringFacesParameter("idTurnoHistoria");
		ls_idTurno             = FacesUtils.getStringFacesParameter("idTurno");
		ls_result              = null;

		if(lbpdab_bean != null)
		{
			lbpdab_bean.limpiarDatos();
			lbpdab_bean.setOcultarPaneles(true);
			lbpdab_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS));
			lbpdab_bean.setIdTurno(ls_idTurno);
			lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
			lbpdab_bean.generarDatosDocumento();
			lbpdab_bean.generarDatosTramitesVinculados();
			lbpdab_bean.setMotivoTramite(null);

			{
				BeanGrabacion lbg_bean;

				lbg_bean = (BeanGrabacion)lfc_context.getApplication()
						                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
						);

				if(lbg_bean != null)
				{
					if(StringUtils.isValidString(ls_idTurnoHistoria))
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = new TurnoHistoria();

						lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

						lth_turnoHistoria.setFechaEtapaValida(true);

						lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							lbg_bean.limpiar();
							lbg_bean.setTurnoHistoria(lth_turnoHistoria);
							lbg_bean.setDocumentoGenerado(false);
							lbg_bean.setProceso(ProcesoCommon.ID_PROCESO_37);
							lbg_bean.setEtapa(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS);
							lbg_bean.setMotivoTramite(null);
							lbg_bean.cargarData();

							ls_result = NavegacionCommon.DETALLE_GRABACION;
						}
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
				}
			}
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviaPagina()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		try
		{
			BeanDireccion lbd_beanDireccion;
			boolean       lb_generar;
			long          ll_idEtapa;
			String        ls_motivo;

			lbd_beanDireccion     = getBeanDireccion();
			ll_idEtapa            = getEtapa();
			ls_motivo             = getMotivoTramite();

			if(!StringUtils.isValidString(ls_motivo))
				throw new B2BException(ErrorKeys.ERROR_CONFRONTACION_ACCION);

			lb_generar = ls_motivo.equalsIgnoreCase(
				    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
				);

			setGenerar(lb_generar);

			if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
			{
				if(
				    ls_motivo.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.ANTIGUO_SISTEMA_GRABACION_DE_MATRICULAS)
					    )
				)
				{
					BeanAntSistemaCalificacion lbccpc_bean;

					lbccpc_bean = (BeanAntSistemaCalificacion)lfc_context.getApplication()
							                                                 .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_ANT_SISTEMA_CALIFICACION,
							    BeanAntSistemaCalificacion.class
							);

					if(lbccpc_bean != null)
					{
						lbccpc_bean.clear();
						lbccpc_bean.setGrabacion(true);
						lbccpc_bean.setIdTurnoHistoria(getIdTurnoHistoria());
						lbccpc_bean.setIdTurno(getIdTurno());
						lbccpc_bean.setIdCirculo();
						lbccpc_bean.generarDatosDocumento();

						ls_return = NavegacionCommon.ANT_SISTEMA_CALIFICACION;
					}
				}
				else if(
				    ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION))
					    || lb_generar
				)
				{
					clear(false);
					setNumeroAnotaciones(null);
					consultarPersonaInteresado(false);
					setDocumentoGenerado(false);
					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(false);
					generarMigaPan(ls_motivo);

					{
						Suspension   ls_parametros;
						OficiosTexto lot_data;

						ls_parametros = getParametros();

						if(ls_parametros == null)
							ls_parametros = new Suspension();

						if(
						    ls_motivo.equalsIgnoreCase(
							        StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
							    )
						)
							igr_grabacionRemote.consultarExistenciaPredio(getIdTurno());

						lot_data = igr_grabacionRemote.cargarOficioTexto(getIdTurno(), getMotivoTramite());

						if(lot_data != null)
						{
							if(
							    ls_motivo.equalsIgnoreCase(
								        StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
								    )
							)
								ls_parametros.setConsideracion(lot_data.getConsideracion());
							else if(
							    ls_motivo.equalsIgnoreCase(
								        StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
								    )
							)
								ls_parametros.setConsideracion(lot_data.getPertinencia());

							setNumeroAnotaciones(lot_data.getNumeroAnotaciones());
						}
					}

					ls_return = NavegacionCommon.PROCESOS_GRABACION;
				}
				else if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.ENVIAR_A_CORRECIONES)))
				{
					String ls_idTurnoHistoria;

					ls_idTurnoHistoria = getIdTurnoHistoria();

					if(
					    !igr_grabacionRemote.validarExistenciaMatricula(
						        ls_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						    )
					)
						throw new B2BException(ErrorKeys.ERROR_MATRICULA_GRABACION_NO_EXISTENTE);

					BeanCorreccionInterna lbci_bean;

					lbci_bean = (BeanCorreccionInterna)lfc_context.getApplication()
							                                          .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_CORRECCION_INTERNA, BeanCorreccionInterna.class
							);

					if(lbci_bean != null)
					{
						lbci_bean.clean();
						lbci_bean.setCorreccionesCalificacion(true);
						lbci_bean.setCorreccionesParaGrabacion(true);
						lbci_bean.setIdTurno(getIdTurno());
						lbci_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
						lbci_bean.getDatosUsuarioCalificador();

						ls_return = NavegacionCommon.CORRECCIONES_INTERNAS;
					}
				}
			}
			else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
			{
				if(ls_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA)))
				{
					BeanPredioDocumentoActo lbpdab_bean;

					lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
							                                              .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
							);

					if(lbpdab_bean != null)
					{
						BeanGrabacionMatriculas lb_grabacion;

						lb_grabacion = (BeanGrabacionMatriculas)lfc_context.getApplication()
								                                               .evaluateExpressionGet(
								    lfc_context, BeanNameCommon.BEAN_GRABACION_MATRICULAS, BeanGrabacionMatriculas.class
								);

						if(lb_grabacion != null)
						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = getTurnoHistoria();

							if(lth_turnoHistoria != null)
								lb_grabacion.setIdTurno(lth_turnoHistoria.getIdTurno());

							igr_grabacionRemote.validarPredioGrabar(lth_turnoHistoria);

							lb_grabacion.setPredio(lbpdab_bean.getPredio());
							lb_grabacion.setFormulario(is_formularioGrabarMatricula);
							lb_grabacion.setIdTurnoHistoria(getIdTurnoHistoria());
							lb_grabacion.setIdPais("57");
							lb_grabacion.cargarData();
						}
					}

					ls_return = NavegacionCommon.GRABAR_MATRICULA;
				}
				else if(
				    ls_motivo.equalsIgnoreCase(
					        StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION_104)
					    )
				)
				{
					clear(false);
					setNumeroAnotaciones(null);
					consultarPersonaInteresado(true);
					setDocumentoGenerado(false);
					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(false);
					generarMigaPan(ls_motivo);

					ls_return = NavegacionCommon.PROCESOS_GRABACION;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageDetalleGrabacion);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarAprobador()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			Suspension ls_parametros;
			String     ls_motivo;

			ls_parametros     = getParametros();
			ls_motivo         = getMotivoTramite();

			if((ls_parametros != null) && StringUtils.isValidString(ls_motivo))
			{
				BeanCalificacion lb_beanCalificacion;
				boolean          lb_focus;
				boolean          lb_negacion;
				boolean          lb_resolucion;
				FacesContext     lfc_context;
				long             ll_idEtapa;
				String           ls_consideracion;

				lb_focus             = true;
				lb_negacion          = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					);
				lb_resolucion        = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					);
				lfc_context          = FacesUtils.getFacesContext();
				ll_idEtapa           = getEtapa();
				ls_consideracion     = ls_parametros.getConsideracion();

				lb_focus = validateStyles(
					    is_formularioProcesosGrabacion + ":idItaConsideraciones", lfc_context, ls_consideracion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_consideracion))
				{
					if(lb_negacion)
						throw new B2BException(ErrorKeys.ERROR_RAZONES_NEGACION);
					else if(lb_resolucion)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				ls_parametros.setTurnoHistoria(getTurnoHistoria());
				ls_parametros.setObservacionesProcesoAnterior(getObservaciones());
				ls_parametros.setEtapa(ll_idEtapa);

				if(lb_negacion)
					igr_grabacionRemote.negarSolicitud(
					    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				else if(lb_resolucion)
				{
					Long ll_anotaciones;

					ll_anotaciones = getNumeroAnotaciones();

					if(!NumericUtils.isValidLong(ll_anotaciones, 1L))
						throw new B2BException(ErrorKeys.CANTIDAD_ANOTACION);

					ls_parametros.setAnotaciones(ll_anotaciones);

					igr_grabacionRemote.generarResolucion(
					    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				}

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
				{
					lb_beanCalificacion = (BeanCalificacion)lfc_context.getApplication()
							                                               .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
							);

					if(lb_beanCalificacion != null)
					{
						String ls_idEtapa;

						ls_idEtapa = StringUtils.getString(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);

						lb_beanCalificacion.clear();
						lb_beanCalificacion.setIdEtapa(ls_idEtapa);
						lb_beanCalificacion.generarDatosTramiteCantidad(is_formularioGrabacion, ls_idEtapa, true);

						ls_return = NavegacionCommon.GRABACION;
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
				{
					BeanGrabacion lb_beanGrabacion;

					lb_beanGrabacion = (BeanGrabacion)lfc_context.getApplication()
							                                         .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
							);

					lb_beanGrabacion.setIdTurnoConsulta(null);
					lb_beanGrabacion.setNirConsulta(null);
					lb_beanGrabacion.generarDatosTramiteCantidad();

					ls_return = NavegacionCommon.GRABACION_MATRICULAS;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageProcesosGrabacion);
		}

		return ls_return;
	}

	/**
	 * Generar data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarData()
	    throws B2BException
	{
		List<Map<String, Object>> llmso_lmso;

		llmso_lmso = iasr_antiguoSistemaRemote.findDetailInbox(
			    getUserId(), NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS),
			    getIdTurnoConsulta(), getNirConsulta()
			);

		if(!CollectionUtils.isValidCollection(llmso_lmso))
			addMessage(MessagesKeys.NO_SE_ENCONTRARON_COINCIDENCIAS);

		setData(llmso_lmso);
	}

	/**
	 * Generar datos tramite cantidad.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		try
		{
			int                         li_cantidadPorEtapas;
			Collection<TramiteCantidad> lctc_data;

			li_cantidadPorEtapas     = 0;
			lctc_data                = igr_grabacionRemote.findInboxByUserId(
				    getUserId(), getIdTurnoConsulta(), getNirConsulta()
				);

			if(CollectionUtils.isValidCollection(lctc_data))
			{
				for(TramiteCantidad ltc_actual : lctc_data)
					li_cantidadPorEtapas = li_cantidadPorEtapas
						+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
						? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);

				setTotalBandeja(li_cantidadPorEtapas);
			}

			setDatosTramiteCantidad(lctc_data);

			if(li_cantidadPorEtapas <= 0)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			else
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update(is_messageGrabacionMatriculas);
	}

	/**
	 * Método encargado de generar la descarga.
	 */
	public void generarDescarga()
	{
		setImagen(
		    generarArchivosDescarga(
		        getArchivo(), TipoContenidoCommon.PDF,
		        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
		    )
		);
	}

	/**
	 * Generar documento.
	 */
	public void generarDocumento()
	{
		try
		{
			Suspension ls_parametros;
			String     ls_motivo;

			ls_parametros     = getParametros();
			ls_motivo         = getMotivoTramite();

			if((ls_parametros != null) && StringUtils.isValidString(ls_motivo))
			{
				byte[]       lab_file;
				boolean      lb_negacion;
				boolean      lb_resolucion;
				boolean      lb_focus;
				FacesContext lfc_context;
				String       ls_consideraciones;

				lab_file               = null;
				lb_negacion            = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					);
				lb_resolucion          = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					);
				lb_focus               = true;
				lfc_context            = FacesContext.getCurrentInstance();
				ls_consideraciones     = ls_parametros.getConsideracion();

				lb_focus = validateStyles(
					    is_formularioProcesosGrabacion + ":idItaConsideraciones", lfc_context, ls_consideraciones,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_consideraciones))
				{
					if(lb_negacion)
						throw new B2BException(ErrorKeys.ERROR_RAZONES_NEGACION);
					else if(lb_resolucion)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				ls_parametros.setTurnoHistoria(getTurnoHistoria());
				ls_parametros.setConsideracion(ls_consideraciones);

				if(lb_negacion)
					lab_file = igr_grabacionRemote.generarDocumentoNegacion(
						    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false
						);
				else if(lb_resolucion)
				{
					Long ll_anotaciones;

					ll_anotaciones = getNumeroAnotaciones();

					if(!NumericUtils.isValidLong(ll_anotaciones, 1L))
						throw new B2BException(ErrorKeys.CANTIDAD_ANOTACION);

					ls_parametros.setAnotaciones(ll_anotaciones);

					lab_file = igr_grabacionRemote.generarDocumentoResolucion(
						    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress(), false
						);
				}

				if(lab_file != null)
				{
					setArchivo(lab_file);
					setDocumentoGenerado(true);
					addMessage(MessagesKeys.DOCUMENTO_GENERADO_VISUALIZAR);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageProcesosGrabacion);
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

	/** {@inheritdoc} */
	public void limpiar()
	    throws B2BException
	{
		BeanDireccion lbd_beanDireccion;

		lbd_beanDireccion = getBeanDireccion();

		lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
		lbd_beanDireccion.setDeshabilitarResidencia(true);

		setParametros(null);
		setDatosGuardados(false);
		clear(false);
	}

	/**
	 * Mostrar observaciones proceso anterior.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void mostrarObservacionesProcesoAnterior(String as_s)
	{
		if(StringUtils.isValidString(as_s))
		{
			setObservacionesProcesoAnterior(as_s);
			PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
			PrimeFaces.current().ajax().update("fDialog");
		}
		else
			addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);

		PrimeFaces.current().ajax().update(is_messageDetalleGrabacionMatriculas);
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
	 */
	public String regresar()
	{
		long   ll_idEtapa;
		String ls_return;

		ll_idEtapa     = getEtapa();
		ls_return      = null;

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
			ls_return = NavegacionCommon.TURNOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
			ls_return = NavegacionCommon.DETALLE_GRABACION_MATRICULAS;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param al_idEtapa correspondiente al valor del tipo de objeto Long
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages(Long al_idEtapa)
	    throws B2BException
	{
		if(!NumericUtils.isValidLong(al_idEtapa))
			throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);

		long ll_idEtapa;

		ll_idEtapa = NumericUtils.getLong(al_idEtapa);

		if(ll_idEtapa > 0)
		{
			setBandejaentrada(true);
			setData(null);

			if(StringUtils.isValidString(getIdTurnoConsulta()))
				setConsultaIndividual(true);
			else
				setConsultaIndividual(false);

			generarData();
		}

		return NavegacionCommon.DETALLE_GRABACION_MATRICULAS;
	}

	/**
	 * Salvar grabacion matriculas.
	 */
	public void salvarGrabacionMatriculas()
	{
		try
		{
			Suspension ls_parametros;
			String     ls_motivo;

			ls_parametros     = getParametros();
			ls_motivo         = getMotivoTramite();

			if((ls_parametros != null) && StringUtils.isValidString(ls_motivo))
			{
				BeanDireccion      lbd_beanDireccion;
				boolean            lb_focus;
				boolean            lb_negacion;
				boolean            lb_resolucion;
				DatosDelInteresado lddi_datosDelInteresado;
				FacesContext       lfc_context;
				String             ls_consideraciones;

				lbd_beanDireccion           = getBeanDireccion();
				lb_focus                    = true;
				lb_negacion                 = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					);
				lb_resolucion               = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					);
				lddi_datosDelInteresado     = ls_parametros.getDatosDelInteresado();
				lfc_context                 = FacesContext.getCurrentInstance();
				ls_consideraciones          = ls_parametros.getConsideracion();
				lb_focus                    = validateStyles(
					    is_formularioProcesosGrabacion + ":idItaConsideraciones", lfc_context, ls_consideraciones,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_consideraciones))
				{
					if(lb_negacion)
						throw new B2BException(ErrorKeys.ERROR_RAZONES_NEGACION);
					else if(lb_resolucion)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				if(lb_resolucion)
				{
					Long ll_anotaciones;

					ll_anotaciones = getNumeroAnotaciones();

					if(!NumericUtils.isValidLong(ll_anotaciones, 1L))
						throw new B2BException(ErrorKeys.CANTIDAD_ANOTACION);
				}

				validarDatosBasicos(lddi_datosDelInteresado, lfc_context);

				if(isEditarDireccionResidencia())
					lbd_beanDireccion.validarCamposDireccionResidencia();

				if(isEditarDireccionCorrespondencia())
					lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);

				if(isEditarDatosContacto())
					validarDatosContacto(ls_parametros, lfc_context);

				lddi_datosDelInteresado.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lddi_datosDelInteresado.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lddi_datosDelInteresado.setEditarDatosBasicos(isEditarDatosBasicos());
				lddi_datosDelInteresado.setEditarDatosContacto(isEditarDatosContacto());
				lddi_datosDelInteresado.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
				lddi_datosDelInteresado.setEditarDireccionResidencia(isEditarDireccionResidencia());

				ls_parametros.setDatosDelInteresado(lddi_datosDelInteresado);
				ls_parametros.setTurnoHistoria(getTurnoHistoria());

				if(
				    igr_grabacionRemote.salvarGrabacionMatriculas(
					        ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
				)
				{
					addMessage(MessagesKeys.INFORMACION_GUARDADA_ENVIAR_APROBADOR);

					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(true);
				}
				else
					throw new B2BException(ErrorKeys.GRABACION_SALVAR_NEGACION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageProcesosGrabacion);
		}
	}

	/**
	 * Validar datos basicos.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosBasicos(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			Persona lp_persona;

			lp_persona = addi_data.getPersona();

			if((lp_persona != null) && !isGenerar())
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lp_persona.getIdTipoPersona();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMTipoPersona", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);
				}

				{
					ls_datoValidar     = lp_persona.getIdDocumentoTipo();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMTipoDoc", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					ls_datoValidar     = lp_persona.getNumeroDocumento();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idOlDocumento", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdPais();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMNacionalidad", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerNombre();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idOlPNombre", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerApellido();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idOlPApellido", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdNaturalGenero();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMGenero", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
				}
			}
		}
	}

	/**
	 * Validar datos contacto.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Suspension
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosContacto(Suspension as_parametros, FacesContext afc_context)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			DatosDelInteresado lddi_data;

			lddi_data = as_parametros.getDatosDelInteresado();

			if(lddi_data != null)
			{
				boolean                  lb_focus;
				PersonaCorreoElectronico lpce_correo;
				PersonaTelefono          lpt_fijo;
				PersonaTelefono          lpt_movil;
				String                   ls_datoValidar;

				lb_focus           = true;
				lpce_correo        = lddi_data.getCorreoElectronico();
				lpt_fijo           = lddi_data.getTelefonoFijo();
				lpt_movil          = lddi_data.getTelefonoMovil();
				ls_datoValidar     = null;

				if(lpt_fijo != null)
				{
					String ls_telefono;

					ls_telefono = lpt_fijo.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataFijo())
					{
						{
							ls_datoValidar     = lpt_fijo.getIdPais();

							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idSOMPaisTel", afc_context, ls_datoValidar,
								    lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_FIJO);
						}

						{
							ls_datoValidar     = lpt_fijo.getIdDepartamento();

							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idSOMDepTel", afc_context, ls_datoValidar,
								    lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
						}

						{
							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idItTelefonoFijo", afc_context, ls_telefono,
								    lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_INVALIDO);
						}
					}
				}

				if(lpt_movil != null)
				{
					String ls_telefono;

					ls_telefono = lpt_movil.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataMovil())
					{
						{
							ls_datoValidar     = lpt_movil.getIdPais();

							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idSOMPaisTelMov", afc_context, ls_datoValidar,
								    lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);
						}

						{
							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idItTelefonoMovil", afc_context, ls_telefono,
								    lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_MOVIL);
						}
					}
				}

				if(lpce_correo != null)
				{
					ls_datoValidar = lpce_correo.getCorreoElectronico();

					if(StringUtils.isValidString(ls_datoValidar) || lddi_data.isDataCorreo())
					{
						lb_focus = validateStyles(
							    is_formularioProcesosGrabacion + ":idItEmail", afc_context, ls_datoValidar, lb_focus
							);

						if(!StringUtils.isValidString(ls_datoValidar))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

						if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_datoValidar))
						{
							lb_focus = validateStyles(
								    is_formularioProcesosGrabacion + ":idItEmail", afc_context,
								    IdentificadoresCommon.DATO_INVALIDO, lb_focus
								);

							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
						}
					}
				}
			}
		}
	}

	/**
	 * Validar dir correspondencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirCorrespondencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirCorrespondencia;

			lpd_dirCorrespondencia = addi_data.getDireccionCorrespondencia();

			if(lpd_dirCorrespondencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idsomTipoEjeC", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idItDatoEjePrincipalC", afc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idsomTipoEje1C", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idItDatoEjeSecundarioC", afc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdPais();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMPaisDirCor", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdDepartamento();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMDepDirCor", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdMunicipio();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMMunDirCor", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/**
	 * Validar dir residencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirResidencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirResidencia;

			lpd_dirResidencia = addi_data.getDireccionResidencia();

			if(lpd_dirResidencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idsomTipoEje", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idItDatoEjePrincipal", afc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idsomTipoEje1", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idItDatoEjeSecundario", afc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdPais();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMPaisDirRe", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdDepartamento();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMDepDirRe", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdMunicipio();

					lb_focus = validateStyles(
						    is_formularioProcesosGrabacion + ":idSOMMunDirRe", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;

		ls_return = NavegacionCommon.GRABACION_MATRICULAS;

		try
		{
			generarDatosTramiteCantidad();
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("volver", e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Generar miga pan.
	 *
	 * @param as_motivo correspondiente al valor del tipo de objeto String
	 */
	private void generarMigaPan(String as_motivo)
	{
		if(StringUtils.isValidString(as_motivo))
		{
			String ls_migaPan;

			ls_migaPan = null;

			if(
			    as_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION))
				    || as_motivo.equalsIgnoreCase(
				        StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION_104)
				    )
			)
				ls_migaPan = getMessages().getString(MessagesKeys.LABEL_NEGAR_SOLICITUD);
			else if(
			    as_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION))
			)
				ls_migaPan = getMessages().getString(MessagesKeys.LABEL_GENERAR_RESOLUCION);

			setMigaPan(ls_migaPan);
		}
	}
}
