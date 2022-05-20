package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CampoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.DatosEtapaAnterior;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.calificacion.ValidacionAlertaTurno;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;
import com.bachue.snr.prosnr01.model.ui.UsuarioActividadUI;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y funcionalidad comun  de BeanTurnos.
 *
 * @author dbeltran
 */
@SessionScoped
@ManagedBean(name = "beanTurnos")
public class BeanTurnos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3049638354872673934L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTurnos.class);

	/** Propiedad irr reference remote. */
	@EJB
	protected ReferenceRemote irr_referenceRemote;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ictc datos calificacion. */
	private Collection<TramiteCantidad> ictc_datosCalificacion;

	/** Propiedad icuaui data bandeja. */
	private Collection<UsuarioActividadUI> icuaui_dataBandeja;

	/** Propiedad iltc filtro calificacion. */
	private List<TramiteCantidad> iltc_filtroCalificacion;

	/** Propiedad llmso data. */
	private List<Map<String, Object>> llmso_data;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad isr suspension remote. */
	@EJB
	private SuspensionRemote isr_suspensionRemote;

	/** Propiedad ib administracion homologacion actos liquidacion. */
	private boolean ib_administracionHomologacionActosLiquidacion;

	/** Propiedad ib analisis traslados. */
	private boolean ib_analisisTraslados;

	/** Propiedad ib analisis traslados oficina destino. */
	private boolean ib_analisisTrasladosOficinaDestino;

	/** Propiedad ib detalle calificador. */
	private boolean ib_detalleCalificador;

	/** Propiedad ib grabacion matriculas. */
	private boolean ib_grabacionMatriculas;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;
	
	/** Propiedad ib ind vinculado. */
	private boolean ib_indProhibicion;

	/** Propiedad ib mostrar grafica. */
	private boolean ib_mostrarGrafica;

	/** Propiedad ib rendered calificacion. */
	private boolean ib_renderedCalificacion;

	/** Propiedad ib rendered correcciones. */
	private boolean ib_renderedCorrecciones;

	/** Propiedad ib rendered digitador masivo. */
	private boolean ib_renderedDigitadorMasivo;

	/** Propiedad ib rendered homologacion actos liquidacion. */
	private boolean ib_renderedHomologacionActosLiquidacion;

	/** Propiedad ib rendered mod datos basicos. */
	private boolean ib_renderedModDatosBasicos;

	/** Propiedad ib rendered regresar. */
	private boolean ib_renderedRegresar;

	/** Propiedad ib reproduccion constancia. */
	private boolean ib_reproduccionConstancia;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/** Propiedad il id etapa ant. */
	private long il_idEtapaAnt;

	/** Propiedad il id etapa menu. */
	private long il_idEtapaMenu;

	/** Propiedad il id motivo. */
	private long il_idMotivo;

	/**
	 * Modifica el valor de AdministracionHomologacionActosLiquidacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setAdministracionHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_administracionHomologacionActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad administracion homologacion actos liquidacion.
	 *
	 * @return Retorna el valor de la propiedad administracionHomologacionActosLiquidacion
	 */
	public boolean isAdministracionHomologacionActosLiquidacion()
	{
		return ib_administracionHomologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de AnalisisTraslados.
	 *
	 * @param ab_b de ab b
	 */
	public void setAnalisisTraslados(boolean ab_b)
	{
		ib_analisisTraslados = ab_b;
	}

	/**
	 * Valida la propiedad analisis traslados.
	 *
	 * @return Retorna el valor de la propiedad traslados
	 */
	public boolean isAnalisisTraslados()
	{
		return ib_analisisTraslados;
	}

	/**
	 * Modifica el valor de AnalisisTrasladosOficinaDestino.
	 *
	 * @param ab_b de ab b
	 */
	public void setAnalisisTrasladosOficinaDestino(boolean ab_b)
	{
		ib_analisisTrasladosOficinaDestino = ab_b;
	}

	/**
	 * Valida la propiedad analisis traslados oficina destino.
	 *
	 * @return Retorna el valor de la propiedad analisisTrasladosOficinaDesitno
	 */
	public boolean isAnalisisTrasladosOficinaDestino()
	{
		return ib_analisisTrasladosOficinaDestino;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Sets the data.
	 *
	 * @param llso_data correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setData(List<Map<String, Object>> llso_data)
	{
		llmso_data = llso_data;
	}

	/**
	 * Retorna el valor de data.
	 *
	 * @return el valor de data
	 */
	public List<Map<String, Object>> getData()
	{
		return llmso_data;
	}

	/**
	 * Modifica el valor de data bandeja.
	 *
	 * @param acth_cth asigna el valor a la propiedad data bandeja
	 */
	public void setDataBandeja(Collection<UsuarioActividadUI> acth_cth)
	{
		icuaui_dataBandeja = acth_cth;
	}

	/**
	 * Retorna el valor de data bandeja.
	 *
	 * @return el valor de data bandeja
	 */
	public Collection<UsuarioActividadUI> getDataBandeja()
	{
		return icuaui_dataBandeja;
	}

	/**
	 * Modifica el valor de datos calificacion.
	 *
	 * @param act_tc asigna el valor a la propiedad datos calificacion
	 */
	public void setDatosCalificacion(Collection<TramiteCantidad> act_tc)
	{
		ictc_datosCalificacion = act_tc;
	}

	/**
	 * Retorna el valor de datos calificacion.
	 *
	 * @return el valor de datos calificacion
	 */
	public Collection<TramiteCantidad> getDatosCalificacion()
	{
		return ictc_datosCalificacion;
	}

	/**
	 * Modifica el valor de detalle calificador.
	 *
	 * @param ab_b asigna el valor a la propiedad detalle calificador
	 */
	public void setDetalleCalificador(boolean ab_b)
	{
		ib_detalleCalificador = ab_b;
	}

	/**
	 * Valida la propiedad detalle calificador.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en detalle calificador
	 */
	public boolean isDetalleCalificador()
	{
		return ib_detalleCalificador;
	}

	/**
	 * Modifica el valor de filtro calificacion.
	 *
	 * @param altc_ltc asigna el valor a la propiedad filtro calificacion
	 */
	public void setFiltroCalificacion(List<TramiteCantidad> altc_ltc)
	{
		iltc_filtroCalificacion = altc_ltc;
	}

	/**
	 * Retorna el valor de filtro calificacion.
	 *
	 * @return el valor de filtro calificacion
	 */
	public List<TramiteCantidad> getFiltroCalificacion()
	{
		return iltc_filtroCalificacion;
	}

	/**
	 * Modifica el valor de grabacion matriculas.
	 *
	 * @param ab_b asigna el valor a la propiedad grabacion matriculas
	 */
	public void setGrabacionMatriculas(boolean ab_b)
	{
		ib_grabacionMatriculas = ab_b;
	}

	/**
	 * Valida la propiedad grabacion matriculas.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en grabacion matriculas
	 */
	public boolean isGrabacionMatriculas()
	{
		return ib_grabacionMatriculas;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		if(il_idEtapa == 0)
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("il_idEtapa");

			if(StringUtils.isValidString(tmp))
				il_idEtapa = Long.parseLong(tmp);
		}

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id etapa ant.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa ant
	 */
	public void setIdEtapaAnt(long al_l)
	{
		il_idEtapaAnt = al_l;
	}

	/**
	 * Retorna el valor de id etapa ant.
	 *
	 * @return el valor de id etapa ant
	 */
	public long getIdEtapaAnt()
	{
		return il_idEtapaAnt;
	}

	/**
	 * Modifica el valor de id etapa menu.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa menu
	 */
	public void setIdEtapaMenu(long al_l)
	{
		il_idEtapaMenu = al_l;
	}

	/**
	 * Retorna el valor de id etapa menu.
	 *
	 * @return el valor de id etapa menu
	 */
	public long getIdEtapaMenu()
	{
		return il_idEtapaMenu;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
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
	 * Modifica el valor de ind vinculado.
	 *
	 * @param ab_b asigna el valor a la propiedad ind vinculado
	 */
	public void setIndVinculado(boolean ab_b)
	{
		ib_indVinculado = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ind vinculado
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_indProhibicion
	 */
	public boolean isIndProhibicion() {
		return ib_indProhibicion;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_indProhibicion por ib_indProhibicion
	 */
	public void setIndProhibicion(boolean ib_indProhibicion) {
		this.ib_indProhibicion = ib_indProhibicion;
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
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de rendered calificacion.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered calificacion
	 */
	public void setRenderedCalificacion(boolean ab_b)
	{
		ib_renderedCalificacion = ab_b;
	}

	/**
	 * Valida la propiedad rendered calificacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered calificacion
	 */
	public boolean isRenderedCalificacion()
	{
		return ib_renderedCalificacion;
	}

	/**
	 * Modifica el valor de rendered correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered correcciones
	 */
	public void setRenderedCorrecciones(boolean ab_b)
	{
		ib_renderedCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad rendered correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered correcciones
	 */
	public boolean isRenderedCorrecciones()
	{
		return ib_renderedCorrecciones;
	}

	/**
	 * Modifica el valor de rendered digitador masivo.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered digitador masivo
	 */
	public void setRenderedDigitadorMasivo(boolean ab_b)
	{
		ib_renderedDigitadorMasivo = ab_b;
	}

	/**
	 * Valida la propiedad rendered digitador masivo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered digitador masivo
	 */
	public boolean isRenderedDigitadorMasivo()
	{
		return ib_renderedDigitadorMasivo;
	}

	/**
	 * Modifica el valor de rendered homologacion actos liquidacion.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered homologacion actos liquidacion
	 */
	public void setRenderedHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_renderedHomologacionActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad rendered homologacion actos liquidacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered homologacion actos liquidacion
	 */
	public boolean isRenderedHomologacionActosLiquidacion()
	{
		return ib_renderedHomologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de rendered mod datos basicos.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered mod datos basicos
	 */
	public void setRenderedModDatosBasicos(boolean ab_b)
	{
		ib_renderedModDatosBasicos = ab_b;
	}

	/**
	 * Valida la propiedad rendered mod datos basicos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered mod datos basicos
	 */
	public boolean isRenderedModDatosBasicos()
	{
		return ib_renderedModDatosBasicos;
	}

	/**
	 * Modifica el valor de rendered regresar.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered regresar
	 */
	public void setRenderedRegresar(boolean ab_b)
	{
		ib_renderedRegresar = ab_b;
	}

	/**
	 * Valida la propiedad rendered regresar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered regresar
	 */
	public boolean isRenderedRegresar()
	{
		return ib_renderedRegresar;
	}

	/**
	 * Modifica el valor de reproduccion constancia.
	 *
	 * @param ab_b asigna el valor a la propiedad reproduccion constancia
	 */
	public void setReproduccionConstancia(boolean ab_b)
	{
		ib_reproduccionConstancia = ab_b;
	}

	/**
	 * Valida la propiedad reproduccion constancia.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reproduccion constancia
	 */
	public boolean isReproduccionConstancia()
	{
		return ib_reproduccionConstancia;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		String ls_return;
		long   ll_idEtapa;

		ls_return      = null;
		ll_idEtapa     = getIdEtapa();

		if(isRenderedModDatosBasicos())
			ls_return = NavegacionCommon.BANDEJA_MOD_DATOS_BASICOS;
		else if(isRenderedHomologacionActosLiquidacion())
			ls_return = NavegacionCommon.HOMOLOGACION_ACTOS_LIQUIDACION;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
			ls_return = NavegacionCommon.ANALISIS_CORRECCION;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
			ls_return = NavegacionCommon.ANALISTA_DE_COPIAS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_380)
			ls_return = NavegacionCommon.ANALISTA_DE_COPIAS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
			ls_return = NavegacionCommon.RESPONSABLE_CORRECCION;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
			ls_return = NavegacionCommon.RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
			ls_return = NavegacionCommon.SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
			ls_return = NavegacionCommon.BANDEJA_RESOLUCION_RECURSOS;
		else if(isGrabacionMatriculas())
			ls_return = NavegacionCommon.GRABACION;
		else if(isAnalisisTraslados())
			ls_return = NavegacionCommon.ANALISIS_TRASLADOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
			ls_return = NavegacionCommon.ANALISTA_DE_CERTIFICADOS_ESPECIALES;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
			ls_return = NavegacionCommon.BANDEJA_RECURSOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_460)
			ls_return = NavegacionCommon.BANDEJA_RECURSOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_430)
			ls_return = NavegacionCommon.BANDEJA_RESOLUCION_RECURSOS;
		else if(ll_idEtapa == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
			ls_return = NavegacionCommon.BANDEJA_PROYECTA_RESOLUCION;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
			ls_return = NavegacionCommon.ANALISIS_TRASLADOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO)
			ls_return = NavegacionCommon.ACTO_ADMINISTRATIVO_BANDEJA;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO)
			ls_return = NavegacionCommon.ANALISTA_DE_DEVOLUCIONES;
		else
			ls_return = NavegacionCommon.CALIFICACION;

		return ls_return;
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
		FacesContext  lfc_context;
		String        ls_idTurno;
		String        ls_idTurnoHistoria;
		String        ls_idProceso;
		String        ls_idSubProceso;
		String        ls_result;
		String        ls_mensaje;
		String        ls_mensajeError;
		String        ls_mens;
		TurnoHistoria lth_th;
		boolean       lb_proceso39;
		boolean       lb_proceso45;
		boolean       lb_actoEjecutoriaMayor;
		boolean       lb_rechazoRecurso;
		boolean       lb_resolucionRecurso;
		long          ll_idEtapa;

		lfc_context                = FacesUtils.getFacesContext();
		ls_idTurno                 = FacesUtils.getStringFacesParameter("idTurno");
		ls_idTurnoHistoria         = FacesUtils.getStringFacesParameter("idTurnoHistoria");
		ls_idProceso               = FacesUtils.getStringFacesParameter("idProceso");
		ls_idSubProceso            = FacesUtils.getStringFacesParameter("idSubProceso");
		ls_result                  = null;
		ls_mensaje                 = null;
		ls_mensajeError            = null;
		ls_mens                    = null;
		lth_th                     = new TurnoHistoria();
		lb_proceso39               = false;
		lb_proceso45               = false;
		lb_actoEjecutoriaMayor     = false;
		ll_idEtapa                 = getIdEtapa();
		lb_rechazoRecurso          = ll_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS;
		lb_resolucionRecurso       = ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS;

		BeanPredioDocumentoActo lbpdab_bean;
		lbpdab_bean                = (BeanPredioDocumentoActo)FacesUtils.obtenerInstanciaBean(
			    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
			);

		if(lbpdab_bean != null)
			lbpdab_bean.limpiarDatos();
		
		lbpdab_bean.setIdProceso(ls_idProceso);

		lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

		lth_th.setFechaEtapaValida(true);

		lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

		if(lth_th == null)
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		setReproduccionConstancia(lth_th.isDetalleRepConstancia());

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
		{
			if(isReproduccionConstancia())
				ls_result = NavegacionCommon.DETALLE_REPRODUCCION_CONSTANCIA;
			else
			{
				boolean lb_tramitesRecepcionDocumentos;
				String  ls_proceso;

				lb_tramitesRecepcionDocumentos     = false;
				ls_proceso                         = null;

				TurnoHistoria lth_tmpAnt;

				lth_tmpAnt                         = new TurnoHistoria();

				lth_tmpAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

				lth_tmpAnt = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmpAnt);

				if(lth_tmpAnt != null)
				{
					BigDecimal lbd_idEtapaAnt;
					long       ll_idEtapaAnt;

					lbd_idEtapaAnt     = lth_tmpAnt.getIdEtapa();
					ll_idEtapaAnt      = NumericUtils.getLong(lbd_idEtapaAnt);

					if(ll_idEtapaAnt != EtapaCommon.ID_ETAPA_ENTREGA_SUSPENSION_DE_TERMINOS)
					{
						SolicitudAsociada lsa_solicitudAsociada;
						Solicitud         ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(lth_tmpAnt.getIdSolicitud());
						ls_solicitud.setIdProceso(ls_idProceso);
						ls_solicitud.setIdSubproceso(ls_idSubProceso);

						lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociadaProcesoSubProceso(
							    ls_solicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lsa_solicitudAsociada != null)
						{
							Solicitud ls_solicitud2;

							ls_solicitud2 = new Solicitud();

							ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
							ls_solicitud2.setDocumento(false);

							ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

							if(ls_solicitud2 != null)
								ls_proceso = ls_solicitud2.getIdProceso();
						}

						if(StringUtils.isValidString(ls_proceso))
						{
							lb_proceso39     = ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
							lb_proceso45     = ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_45);

							if(lb_proceso39 || lb_proceso45)
								lb_tramitesRecepcionDocumentos = true;
						}
					}
				}

				ValidacionAlertaTurno lvat_validacionAlertas;

				lvat_validacionAlertas = irr_calificacionRemote.validarAlertaTurnoTramite(
					    lth_th.getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lvat_validacionAlertas == null)
					lvat_validacionAlertas = new ValidacionAlertaTurno();

				if(lb_tramitesRecepcionDocumentos && lvat_validacionAlertas.isTurnoAfectado())
				{
					BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

					lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
							                                                                .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS, BeanRecepcionDocumentos.class
							);

					if(lbsdt_beanRecepcionDocumentos != null)
					{
						lbsdt_beanRecepcionDocumentos.clean(true);
						lbsdt_beanRecepcionDocumentos.setModificar(false);

						if(StringUtils.isValidString(ls_idTurnoHistoria))
						{
							lbsdt_beanRecepcionDocumentos.setRespuestaSolicitudDesistimiento(null);
							lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
							lbsdt_beanRecepcionDocumentos.setIdTurnoHistoria(
							    StringUtils.getString(lth_th.getIdTurnoHistoria())
							);
							lbsdt_beanRecepcionDocumentos.setIdTurno(ls_idTurno);
							lbsdt_beanRecepcionDocumentos.setParametros(null);
							lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
							lbsdt_beanRecepcionDocumentos.setProceso(ls_proceso);
							lbsdt_beanRecepcionDocumentos.setEtapa(
							    (lb_proceso39 || lb_proceso45) ? EtapaCommon.ID_ETAPA_CALIFICACION : 0L
							);
							lbsdt_beanRecepcionDocumentos.cargarTiposCausalesDesistimiento();
							lbsdt_beanRecepcionDocumentos.setProceso45(lb_proceso45);

							if(lb_proceso45)
								lbsdt_beanRecepcionDocumentos.verificarPropiedad();

							{
								DatosEtapaAnterior ldea_datos;

								ldea_datos = isr_suspensionRemote.buscarEtapa80Anterior(
									    ls_idTurnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

								if(ldea_datos != null)
								{
									OficiosTexto lot_oficio;

									lot_oficio = ldea_datos.getOficiosTexto();

									if(lot_oficio != null)
									{
										Suspension ls_tmp;

										ls_tmp = new Suspension();

										ls_tmp.setConsideracion(lot_oficio.getConsideracion());

										lbsdt_beanRecepcionDocumentos.setCausalDevolucion(lot_oficio.getPertinencia());
									}
								}
							}

							{
								BeanDireccion lbd_beanDireccion;
								Suspension    ls_datos;

								lbd_beanDireccion     = getBeanDireccion();
								ls_datos              = lbsdt_beanRecepcionDocumentos.getParametros();

								lbd_beanDireccion.limpiarBeanDireccion();
								lbd_beanDireccion.setDeshabilitarResidencia(true);
								lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
								lbd_beanDireccion.setHabilitadoPorConsulta(true);
								lbd_beanDireccion.setForm(BeanRecepcionDocumentos.is_idForm);

								if(ls_datos != null)
								{
									DatosDelInteresado lddi_datos;

									lddi_datos = ls_datos.getDatosDelInteresado();

									if(lddi_datos != null)
									{
										lbd_beanDireccion.setDireccionCorrespondencia(
										    lddi_datos.getDireccionCorrespondencia()
										);
										lbd_beanDireccion.setDireccionResidencia(lddi_datos.getDireccionResidencia());
									}
								}
							}

							ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
				}
				else
				{
					if(lvat_validacionAlertas.isTurnoAsociado())
						addMessage(lvat_validacionAlertas.getMensajeTurnoAsociado());

					lbpdab_bean.setCorreccion(false);
					ls_result = NavegacionCommon.DETALLE_ACTO;
				}
			}
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA)
		{
			BeanCorrectivo lbc_bean;
			lbc_bean = (BeanCorrectivo)lfc_context.getApplication()
					                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CORRECTIVOS, BeanCorrectivo.class
					);

			if(lbc_bean != null)
			{
				lbc_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
				lbc_bean.findObservacionesByIdTurnoHistoria(ls_idTurnoHistoria);
				lbc_bean.getTabs(ls_idTurnoHistoria);
				lbc_bean.validarActoEjecutoria();

				RegistroCalificacion lrc_rc;
				lrc_rc = new RegistroCalificacion();

				lrc_rc.setTurno(lth_th.getIdTurno());
				lrc_rc.setIdEtapa(ll_idEtapa);

				lrc_rc = irr_calificacionRemote.turnosVinculados(
					    lrc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lrc_rc != null)
				{
					Collection<RegistroCalificacion> lcrc_infoTurnos;

					lcrc_infoTurnos = lrc_rc.getAllMatriculas();

					if(CollectionUtils.isValidCollection(lcrc_infoTurnos))
					{
						for(RegistroCalificacion lrc_item : lcrc_infoTurnos)
						{
							if(lrc_item != null)
							{
								TurnoHistoria lth_tmp;

								lth_tmp     = new TurnoHistoria(lrc_item.getIdTurnoHistoria());

								lth_tmp = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_tmp);

								if(lth_tmp != null)
								{
									lrc_item.setApertura(obtenerApertura(lth_tmp.getIdSolicitud()));
									lrc_item.setDataConfrontacion(
									    cargaIndicadoresDocumento("" + lth_tmp.getIdTurnoHistoria())
									);
									lbc_bean.getTabs("" + lth_tmp.getIdTurnoHistoria());
								}
							}
						}

						lbc_bean.setInfoTurnos(lcrc_infoTurnos);
						lbc_bean.setIndVinculado(true);
					}
				}
				else
				{
					lbc_bean.setCalificaciones(cargaIndicadoresDocumento(ls_idTurnoHistoria));
					lbc_bean.setApertura(obtenerApertura(lth_th.getIdSolicitud()));
					lbc_bean.validarActoEjecutoria();
					lbc_bean.setIndVinculado(false);
				}
			}

			ls_result = NavegacionCommon.DETALLE_CONFRONTACION_CORRECTIVA;

			{
				BeanDetallePredio lbdp_bean;

				lbdp_bean = (BeanDetallePredio)FacesUtils.obtenerInstanciaBean(
					    BeanDetallePredio.class, BeanNameCommon.BEAN_DETALLE_PREDIO
					);

				if(lbdp_bean != null)
					lbdp_bean.setPantallaAnterior(ls_result);
			}
		}
		else if(
		    (ll_idEtapa == EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION)
			    || isAdministracionHomologacionActosLiquidacion()
		)
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

			lth_turnoHistoria = irr_calificacionRemote.consultarTurnoHistoriaAnterior(
				    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lth_turnoHistoria != null)
				lbpdab_bean.setObservacionesCalificacion(lth_turnoHistoria.getObservaciones());

			lbpdab_bean.setHomologacionActosLiquidacion(true);
			lbpdab_bean.setAdministracionHomologacionActosLiquidacion(isAdministracionHomologacionActosLiquidacion());

			ls_result = NavegacionCommon.DETALLE_ACTO;
		}
		else if(
		    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_460) || (ll_idEtapa == EtapaCommon.ID_ETAPA_430)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_380)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
			    || (ll_idEtapa == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
			    || (ll_idEtapa == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESOLUCION_CREACION_SUPRESION_MODIFICACION)
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO)  
			    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO)
			    || lb_resolucionRecurso || lb_rechazoRecurso
		)
		{
			TurnoHistoria lth_turnoHistoria;
			boolean       lb_tramitesRecepcionDocumentos;

			lb_tramitesRecepcionDocumentos     = false;
			lth_turnoHistoria                  = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

			lth_turnoHistoria = irr_calificacionRemote.consultarTurnoHistoriaAnterior(
				    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lth_turnoHistoria != null)
			{
				lbpdab_bean.setObservacionesCalificacion(lth_turnoHistoria.getObservaciones());
				lbpdab_bean.setObservacionesGrabacionMatriculas(null);
				lbpdab_bean.setDatosAntSistema(null);

				if(ll_idEtapa == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
					lbpdab_bean.setProyectar(true);
				
				if(ll_idEtapa == EtapaCommon.ID_ETAPA_380) {
					if (StringUtils.isValidString(lth_turnoHistoria.getIdSubproceso()))
						lbpdab_bean.setSolicitudPresupuestal(lbpdab_bean.getIdProceso().equals("4") && lth_turnoHistoria.getIdSubproceso().equals("1"));
					else
						lbpdab_bean.setSolicitudPresupuestal(false);
				}
					

				if((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS) || lb_resolucionRecurso)
				{
					if(!lb_resolucionRecurso)
					{
						Solicitud ls_solicitud;
						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(lth_th.getIdSolicitud());

						Collection<CamposConsulta> lccc_dataCampos;

						lccc_dataCampos = irr_registroRemote.camposPorCriterio(
							    ls_solicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(CollectionUtils.isValidCollection(lccc_dataCampos))
						{
							Iterator<CamposConsulta> licdb_iterator;

							licdb_iterator = lccc_dataCampos.iterator();

							if(licdb_iterator != null)
							{
								while(licdb_iterator.hasNext())
								{
									CamposConsulta      lcc_camposConsulta;
									boolean             lb_validCamposConsulta;
									CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

									lcc_camposConsulta           = licdb_iterator.next();
									lb_validCamposConsulta       = lcc_camposConsulta != null;
									lcdb_criteriosDeBusqueda     = lb_validCamposConsulta
										? lcc_camposConsulta.getCriteriosDeBusqueda() : null;

									if(lcc_camposConsulta != null)
									{
										if(lcdb_criteriosDeBusqueda != null)
										{
											Collection<CriteriosDeBusqueda> lccdb_criteriosDeBusqueda;

											lccdb_criteriosDeBusqueda = irr_registroRemote.consultarDetallesAgregados(
												    lcdb_criteriosDeBusqueda, getUserId(), getLocalIpAddress(),
												    getRemoteIpAddress()
												);

											if(CollectionUtils.isValidCollection(lccdb_criteriosDeBusqueda))
											{
												lcc_camposConsulta.setCriteriosAgregados(lccdb_criteriosDeBusqueda);

												Collection<CamposConsulta> lccc_camposConsulta;

												lccc_camposConsulta = lcc_camposConsulta.getDataCamposConsulta();

												if(CollectionUtils.isValidCollection(lccc_camposConsulta))
												{
													//FORMATO Comentar lambda antes de formatear
													lccc_camposConsulta.forEach(lcc_campo ->
													{
													    String ls_idCampoConsulta;
													
													    ls_idCampoConsulta = StringUtils.getStringNotNull(lcc_campo.getIdCampoCriterioBusqueda());
													
													    lccdb_criteriosDeBusqueda.stream()
													        .filter(lcdb_criterio -> StringUtils.getStringNotNull(lcdb_criterio.getIdCampoCriterioBusqueda()).equals(ls_idCampoConsulta))
													        .forEach(lcdb_criterio ->
													        {
													            String ls_idTipoCriterioBusqueda;
													            String ls_idCampoCriterioBusqueda;
													
													            ls_idTipoCriterioBusqueda      = StringUtils.getStringNotNull(
													                    lcdb_criterio.getIdTipoCriterio()
													                );
													            ls_idCampoCriterioBusqueda     = StringUtils.getStringNotNull(
													                    lcdb_criterio.getIdCampoCriterioBusqueda()
													                );
													
													            if(
													                (ls_idTipoCriterioBusqueda.equals(TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS) || 
													                		ls_idTipoCriterioBusqueda.equals(TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA))
													                    && ls_idCampoCriterioBusqueda.equals(
													                        CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_FECHA_DOCUMENTO
													                    )
													            )
													                lcc_campo.setValorCampoFecha(
													                    DateUtils.getDate(
													                        lcdb_criterio.getValorCampo(),
													                        FormatoFechaCommon.DIA_MES_ANIO
													                    )
													                );
													            else
													                lcc_campo.setValorCampo(lcdb_criterio.getValorCampo());
													        });
													});
												}

												lbpdab_bean.setCamposConsulta(lccc_dataCampos);
											}
										}

										if(lb_validCamposConsulta)
											lbpdab_bean.setCriteriosDeConsulta(lcdb_criteriosDeBusqueda);
									}
								}
							}
						}
					}
					else
					{
						String ls_proceso;

						ls_proceso = null;

						SolicitudAsociada lsa_solicitudAsociada;
						Solicitud         ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(lth_th.getIdSolicitud());
						ls_solicitud.setIdProceso(ls_idProceso);
						ls_solicitud.setIdSubproceso(ls_idSubProceso);

						lsa_solicitudAsociada = irr_registroRemote.findSolicitudAsociadaProcesoSubProceso(
							    ls_solicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lsa_solicitudAsociada != null)
						{
							Solicitud ls_solicitud2;

							ls_solicitud2 = new Solicitud();

							ls_solicitud2.setIdSolicitud(lsa_solicitudAsociada.getIdSolicitud1());
							ls_solicitud2.setDocumento(false);

							ls_solicitud2 = irr_registroRemote.findSolicitudById(ls_solicitud2, getUserId());

							if(ls_solicitud2 != null)
								ls_proceso = ls_solicitud2.getIdProceso();
						}

						if(StringUtils.isValidString(ls_proceso))
							lb_tramitesRecepcionDocumentos = ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);

						if(lb_tramitesRecepcionDocumentos)
						{
							BeanRecepcionDocumentos lbsdt_beanRecepcionDocumentos;

							lbsdt_beanRecepcionDocumentos = (BeanRecepcionDocumentos)lfc_context.getApplication()
									                                                                .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS,
									    BeanRecepcionDocumentos.class
									);

							if(lbsdt_beanRecepcionDocumentos != null)
							{
								lbsdt_beanRecepcionDocumentos.clean(true);
								lbsdt_beanRecepcionDocumentos.setModificar(false);

								if(StringUtils.isValidString(ls_idTurnoHistoria))
								{
									lbsdt_beanRecepcionDocumentos.setRespuestaSolicitudDesistimiento(null);
									lbsdt_beanRecepcionDocumentos.setTurnoHistoria(lth_th);
									lbsdt_beanRecepcionDocumentos.setIdTurnoHistoria(
									    StringUtils.getString(lth_th.getIdTurnoHistoria())
									);
									lbsdt_beanRecepcionDocumentos.setParametros(null);
									lbsdt_beanRecepcionDocumentos.setDocumentoGenerado(false);
									lbsdt_beanRecepcionDocumentos.setProceso(ls_proceso);
									lbsdt_beanRecepcionDocumentos.setEtapa(
									    EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
									);
									lbsdt_beanRecepcionDocumentos.cargarTiposCausalesDesistimiento();
									lbsdt_beanRecepcionDocumentos.setProceso45(lb_proceso45);

									{
										DatosEtapaAnterior ldea_datos;

										ldea_datos = isr_suspensionRemote.buscarEtapa80Anterior(
											    ls_idTurnoHistoria, getUserId(), getLocalIpAddress(),
											    getRemoteIpAddress()
											);

										if(ldea_datos != null)
										{
											OficiosTexto lot_oficio;

											lot_oficio = ldea_datos.getOficiosTexto();

											if(lot_oficio != null)
											{
												Suspension ls_tmp;

												ls_tmp = new Suspension();

												ls_tmp.setConsideracion(lot_oficio.getConsideracion());

												lbsdt_beanRecepcionDocumentos.setCausalDevolucion(
												    lot_oficio.getPertinencia()
												);
											}
										}
									}

									{
										BeanDireccion lbd_beanDireccion;
										Suspension    ls_datos;

										lbd_beanDireccion     = getBeanDireccion();
										ls_datos              = lbsdt_beanRecepcionDocumentos.getParametros();

										lbd_beanDireccion.limpiarBeanDireccion();
										lbd_beanDireccion.setDeshabilitarResidencia(true);
										lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
										lbd_beanDireccion.setHabilitadoPorConsulta(true);
										lbd_beanDireccion.setForm(BeanRecepcionDocumentos.is_idForm);

										if(ls_datos != null)
										{
											DatosDelInteresado lddi_datos;

											lddi_datos = ls_datos.getDatosDelInteresado();

											if(lddi_datos != null)
											{
												lbd_beanDireccion.setDireccionCorrespondencia(
												    lddi_datos.getDireccionCorrespondencia()
												);
												lbd_beanDireccion.setDireccionResidencia(
												    lddi_datos.getDireccionResidencia()
												);
											}
										}
									}

									ls_result = NavegacionCommon.RECEPCION_DOCUMENTOS;
								}
								else
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
							}
						}
					}

					{
						FechaVenceTerminosUI lfvtui_fechaVencimiento;
						Object[]             aoa_messageArgs;
						int 				 li_tiempoVencimiento;

						lfvtui_fechaVencimiento     = new FechaVenceTerminosUI();
						aoa_messageArgs             = new String[1];

						lfvtui_fechaVencimiento.setFechaInicial(lth_th.getFechaCreacion());
						lfvtui_fechaVencimiento.setFechaFinal(lth_th.getFechaVencimiento());
						lfvtui_fechaVencimiento.setTipoCalendario(EstadoCommon.H);
						lfvtui_fechaVencimiento.setIdCirculo(lth_th.getIdCirculoUsuario());

						{
							long ll_tiempoVencimiento;

							ll_tiempoVencimiento     = irr_referenceRemote.funcionCalcularDiasFechas(
								    lfvtui_fechaVencimiento
								);

							aoa_messageArgs[0] = StringUtils.getString(ll_tiempoVencimiento);
							li_tiempoVencimiento = NumericUtils.getInt(ll_tiempoVencimiento);
						}

						addMessage(
						    EstadoCommon.W,
						    (li_tiempoVencimiento > 0) ? MessagesKeys.FUNCIONARIO_TRAMITES_RECURSOS_FECHA_VENCIMIENTO
						                         : MessagesKeys.FUNCIONARIO_TRAMITES_EXPEDICION_COPIAS_FECHA_VENCIDA,
						    aoa_messageArgs
						);
					}

					{
						ExternalContext lec_externalContext;

						lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

						if(lec_externalContext != null)
						{
							Flash lf_flash;

							lf_flash = lec_externalContext.getFlash();

							if(lf_flash != null)
								lf_flash.setKeepMessages(true);
						}
					}
				}
				else if(
				    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_460) || (ll_idEtapa == EtapaCommon.ID_ETAPA_430)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_EMISION_ACTO_ADMINISTRATIVO) || lb_resolucionRecurso
				)
				{
					BeanAprobacion lb_beanAprobacion;

					lb_beanAprobacion = (BeanAprobacion)obtenerInstanciaBean(
						    BeanAprobacion.class, BeanNameCommon.BEAN_APROBACION
						);

					if(lb_beanAprobacion != null)
						lb_beanAprobacion.setVieneDeCorrecciones(false);

					if(
					    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					)
						lbpdab_bean.setProcesoTraslados(true);
					
					if((ll_idEtapa == EtapaCommon.ID_ETAPA_460) || (ll_idEtapa == EtapaCommon.ID_ETAPA_430))
					{
						BeanRecepcionDocumentos lbrd_bean;

						lbrd_bean = (BeanRecepcionDocumentos)obtenerInstanciaBean(
							    BeanRecepcionDocumentos.class, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS
							);

						if(lbrd_bean != null)
						{
							lbrd_bean.setTurnoHistoria(lth_th);
							lbrd_bean.cargarParametros();
						}

						lbpdab_bean.setMostrarDatosDelInteresadoANotificar(true);
					}

					if(ll_idEtapa == EtapaCommon.ID_ETAPA_430)
					{
						Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

						lcacd_completitudDocumental = irr_registroRemote.findCompletitudDocumentalByIdTurno(
							    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
						{
							for(AccCompletitudDocumental lacd_iterador : lcacd_completitudDocumental)
							{
								if(lacd_iterador != null)
								{
									String ls_obligatoriedadTipoDoc;

									ls_obligatoriedadTipoDoc = lacd_iterador.getObligatorioTipoDocumental();

									lacd_iterador.setSeleccionado(
									    StringUtils.isValidString(ls_obligatoriedadTipoDoc)
										    && ls_obligatoriedadTipoDoc.equalsIgnoreCase(EstadoCommon.S)
									);
									lacd_iterador.setNoEditable(true);
								}
							}

							lbpdab_bean.setCompletitudDocumental(lcacd_completitudDocumental);
						}

						lbpdab_bean.setMostrarDatosDelInteresadoANotificar(true);
					}
				}
				else if(lb_rechazoRecurso)
				{
					BeanRecepcionDocumentos lbrd_bean;

					lbrd_bean = (BeanRecepcionDocumentos)obtenerInstanciaBean(
						    BeanRecepcionDocumentos.class, BeanNameCommon.BEAN_RECEPCION_DOCUMENTOS
						);

					if(lbrd_bean != null)
					{
						lbrd_bean.setTurnoHistoria(lth_th);
						lbrd_bean.setEsSolicitudDocumentacion(true);
						lbrd_bean.cargarParametros();
					}

					{
						Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

						lcacd_completitudDocumental = irr_registroRemote.findCompletitudDocumentalByIdTurno(
							    ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
						{
							for(AccCompletitudDocumental lacd_iterador : lcacd_completitudDocumental)
							{
								if(lacd_iterador != null)
								{
									String ls_obligatoriedadTipoDoc;

									ls_obligatoriedadTipoDoc = lacd_iterador.getObligatorioTipoDocumental();

									lacd_iterador.setSeleccionado(
									    StringUtils.isValidString(ls_obligatoriedadTipoDoc)
										    && ls_obligatoriedadTipoDoc.equalsIgnoreCase(EstadoCommon.S)
									);
									lacd_iterador.setNoEditable(true);
								}
							}

							lbpdab_bean.setCompletitudDocumental(lcacd_completitudDocumental);
						}
					}

					lbpdab_bean.setMostrarDatosDelInteresadoANotificar(true);
				}

				{
					BeanAprobacion lb_beanAprobacion;

					lb_beanAprobacion = (BeanAprobacion)obtenerInstanciaBean(
						    BeanAprobacion.class, BeanNameCommon.BEAN_APROBACION
						);

					if(lb_beanAprobacion != null)
						lb_beanAprobacion.setVieneDeCorrecciones(false);
				}
			}

			lbpdab_bean.setConfrontacion(false);
			lbpdab_bean.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapa));

			if(!lb_tramitesRecepcionDocumentos)
				ls_result = NavegacionCommon.DETALLE_ACTO;

			if(ll_idEtapa == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
			{
				addMessageInfo(EstadoCommon.W, MessagesKeys.RECURSO_SEGUNDA_INSTANCIA);

				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}
			}
		}
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
		{
			if(lbpdab_bean != null)
			{
				lbpdab_bean.setOcultarPaneles(true);
				lbpdab_bean.setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS));
				lbpdab_bean.setIdTurno(ls_idTurno);
				lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
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

							lth_turnoHistoria = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								lbg_bean.limpiar();
								lbg_bean.setTurnoHistoria(lth_turnoHistoria);
								lbg_bean.setDocumentoGenerado(false);
								lbg_bean.setProceso(ProcesoCommon.ID_PROCESO_37);
								lbg_bean.setEtapa(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);
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
		}

		{
			if(lbpdab_bean != null)
			{
				String       ls_decisionCalificacion;
				Trazabilidad lt_trazaTemp;

				ls_decisionCalificacion     = null;
				lt_trazaTemp                = new Trazabilidad();

				lbpdab_bean.clear();
				lbpdab_bean.setIndVinculado(isIndVinculado());
				lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
				lbpdab_bean.setIdTurno(ls_idTurno);
				lbpdab_bean.setIdProceso(ls_idProceso);
				lbpdab_bean.generarDatosAntSistema();
				lbpdab_bean.obtenerInformacionASEtapa101();
				lbpdab_bean.generarDatosDocumento();
				lbpdab_bean.generarDatosTramitesVinculados();
				lbpdab_bean.validarFechaVencimientoActo();
				lbpdab_bean.setMotivoTramite(null);
				lbpdab_bean.setReproduccionConstancia(isReproduccionConstancia());
				lbpdab_bean.setMostrarDialog(
				    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
					    || (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
				);
				
				if(StringUtils.isValidString(ls_idProceso) && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4))
				{
					lbpdab_bean.consultarPersona();
					lbpdab_bean.consultarTitularCuentaDevolucion();
					
					TurnoHistoria lth_turnoHistoriaAnt;
					lth_turnoHistoriaAnt =  new TurnoHistoria();
					
					lth_turnoHistoriaAnt.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));
					
					lth_turnoHistoriaAnt = irr_calificacionRemote.findTurnoHistoriaById(lth_turnoHistoriaAnt);
					
					if(lth_turnoHistoriaAnt != null)
					{
						long ll_idEtapaAnt;
						long ll_idMotivoAnt;
						
						ll_idEtapaAnt = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdEtapa());
						ll_idMotivoAnt = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdMotivo());
						
						if(ll_idEtapaAnt == EtapaCommon.CLASIFICACION_LINEA_DE_PRODUCCION_REPARTO_DEVOLUCION_DE_DINERO
								&& (ll_idMotivoAnt == MotivoTramiteCommon.PRESUPUESTAL_TURNO_REGISTRAL
								|| ll_idMotivoAnt == MotivoTramiteCommon.PRESUPUESTAL_CONSULTAS_NACIONALES))
						{
							lbpdab_bean.consultaActosDevolucionDinero();
						}
					}
				}

				{
					List<Map<String, Object>> llmso_infoDoc      = lbpdab_bean.getDocumentos();
					Date                      ld_fechaDocumento  = (Date)llmso_infoDoc.get(0).get("FECHA_DOCUMENTO");
					Date                      ld_fechaEjecutoria = (Date)llmso_infoDoc.get(0).get("FECHA_EJECUTORIA");
					Date                      ld_fechaACalcular  = ((ld_fechaEjecutoria != null) ? ld_fechaEjecutoria
						                                                                         : ld_fechaDocumento);

					if(ld_fechaACalcular != null)
					{
						Date                 ld_fechaConDosMesesAdelante;
						Date                 ld_fechaActual = new Date();
						FechaVenceTerminosUI lfvtui_fecha;
						lfvtui_fecha                        = new FechaVenceTerminosUI();

						lfvtui_fecha.setFechaInicial(ld_fechaACalcular);
						lfvtui_fecha.setTipoCalendario("C");
						lfvtui_fecha.setIdCirculo(null);

						if(ld_fechaEjecutoria != null)
						{
							lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(90));
							ld_fechaConDosMesesAdelante = irr_referenceRemote.calcularFechaVencimiento(lfvtui_fecha);

							if(ld_fechaActual.after(ld_fechaConDosMesesAdelante))
								ls_mensaje = getMessages().getString(MessagesKeys.DOCUMENTO_HA_SUPERADO_TRES_MESES);

							lb_actoEjecutoriaMayor = true;
						}
						else if(ld_fechaDocumento != null)
						{
							lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(60));
							ld_fechaConDosMesesAdelante = irr_referenceRemote.calcularFechaVencimiento(lfvtui_fecha);

							if(ld_fechaActual.after(ld_fechaConDosMesesAdelante))
								ls_mensaje = getMessages().getString(MessagesKeys.DOCUMENTO_HA_SUPERADO_DOS_MESES);
						}
					}
				}

				{
					String ls_mError = null;
					ls_mError = irr_calificacionRemote.getMensajeError(
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(StringUtils.isValidString(ls_mError))
						ls_mensajeError = ls_mError;
				}

				lbpdab_bean.getCausales();
				lbpdab_bean.getMatriculasRangos();
				lbpdab_bean.getMatriculasIndividuales();
				lbpdab_bean.getMatriculasTmpRangos();
				lbpdab_bean.getMatriculasTmpIndividuales();
				lbpdab_bean.setRecepcionDeDocumentos(lb_proceso39 || lb_proceso45);

				{
					if(StringUtils.isValidString(ls_idTurno))
					{
						Collection<Acto> lca_actos;

						lca_actos = irr_calificacionRemote.findActosTurnoMatriculas(ls_idTurno);

						if(CollectionUtils.isValidCollection(lca_actos))
						{
							Map<String, TipoActo> lmst_actosRemate;
							Map<String, String>   lc_actosVencimiento;

							lc_actosVencimiento     = ipr_parameterRemote.generarCodigos(
								    ConstanteCommon.ACTOS_VENCIMIENTO_DIAS_HABILES
								);

							lmst_actosRemate = irr_referenceRemote.findAllTiposActoRemanenteActivosMap(
								    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							if(CollectionUtils.isValidCollection(lmst_actosRemate))
							{
								boolean             lb_remate;
								Iterator<Acto>      lia_iterator;
								Map<String, String> lmss_actos;
								boolean             lb_validar;

								lb_remate        = false;
								lia_iterator     = lca_actos.iterator();
								lmss_actos       = new HashMap<String, String>();
								lb_validar       = false;

								while(lia_iterator.hasNext())
								{
									Acto la_acto;

									la_acto = lia_iterator.next();

									if(la_acto != null)
									{
										String ls_idTipoActo;

										ls_idTipoActo = la_acto.getIdTipoActo();

										if(StringUtils.isValidString(ls_idTipoActo))
										{
											if(lc_actosVencimiento != null)
											{
												if(lc_actosVencimiento.containsKey(ls_idTipoActo) && !lb_validar)
												{
													{
														Date                 ld_fechaConTresMesesAdelante;
														Date                 ld_fechaActual    = new Date();
														FechaVenceTerminosUI lfvtui_fecha;
														lfvtui_fecha                           = new FechaVenceTerminosUI();

														List<Map<String, Object>> llmso_infoDoc = lbpdab_bean
																.getDocumentos();
														Date                      ld_fechaDocumento = (Date)llmso_infoDoc.get(
															    0
															).get("FECHA_DOCUMENTO");

														lfvtui_fecha.setFechaInicial(ld_fechaDocumento);
														lfvtui_fecha.setTipoCalendario("C");
														lfvtui_fecha.setIdCirculo(null);
														lfvtui_fecha.setDiasCalcularFecha(NumericUtils.getInteger(90));

														ld_fechaConTresMesesAdelante = irr_referenceRemote
																.calcularFechaVencimiento(lfvtui_fecha);

														if(
														    ld_fechaActual.after(ld_fechaConTresMesesAdelante)
															    && !lb_actoEjecutoriaMayor
														)
														{
															lb_validar     = true;
															ls_mens        = getMessages()
																	                 .getString(
																	    MessagesKeys.DOCUMENTO_VENCIDO_90_DIAS_HABILES
																	);
														}
													}
												}
											}

											if(lmst_actosRemate.containsKey(ls_idTipoActo))
											{
												String ls_matricula;

												ls_matricula = la_acto.getReferencia();

												if(
												    StringUtils.isValidString(ls_matricula)
													    && !lmss_actos.containsKey(ls_idTipoActo)
												)
													lmss_actos.put(ls_matricula, ls_matricula);

												lb_remate = true;
											}
										}
									}
								}

								if(lb_remate)
								{
									B2BException  lb2be_e;
									Object[]      aoa_messageArgs;
									StringBuilder lsb_mensaje;
									long          ll_tamanoActos;
									long          ll_posicion;

									aoa_messageArgs     = new String[1];
									lsb_mensaje         = new StringBuilder();

									if(CollectionUtils.isValidCollection(lmss_actos))
									{
										ll_tamanoActos     = lmss_actos.size();
										ll_posicion        = 1;

										for(Map.Entry<String, String> lm_iterador : lmss_actos.entrySet())
										{
											String ls_separador;
											ls_separador = null;

											if(ll_posicion < ll_tamanoActos)
												ls_separador = ", ";
											else
												ls_separador = "";

											ll_posicion++;

											lsb_mensaje.append(lm_iterador.getValue() + ls_separador);
										}
									}

									aoa_messageArgs[0]     = lsb_mensaje.toString();
									lb2be_e                = new B2BException(
										    ErrorKeys.ERROR_MATRICULAS_EMBARGO_REMANENTE, aoa_messageArgs
										);
									ls_mensaje             = lb2be_e.getMessage();
								}
							}
						}

						{
							Turno lt_turnoTemp;

							lt_turnoTemp = new Turno();

							lt_turnoTemp.setIdTurno(lbpdab_bean.getIdTurno());

							lt_trazaTemp.setTurno(lt_turnoTemp);

							ls_decisionCalificacion = irr_referenceRemote.findDecisionCalificacion(lt_trazaTemp);

							lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);
						}

						{
							List<Map<String, Object>> lmsb_matriculas;
							Collection<Object>        lco_matriculas;

							lmsb_matriculas     = lbpdab_bean.getMatriculasIndividuales();
							lco_matriculas      = new ArrayList<Object>();

							if(CollectionUtils.isValidCollection(lmsb_matriculas))
							{
								for(Map<String, Object> lm_map : lmsb_matriculas)
								{
									if(lm_map != null)
									{
										for(Map.Entry<String, Object> lmesb_entry : lm_map.entrySet())
										{
											if(lmesb_entry != null)
												lco_matriculas.add(lmesb_entry.getValue());
										}
									}
								}

								if(CollectionUtils.isValidCollection(lco_matriculas))
								{
									Collection<String> lcs_matriculasExtraidas;

									lcs_matriculasExtraidas = new ArrayList<String>();

									for(Object lo_object : lco_matriculas)
									{
										if(lo_object != null)
										{
											String ls_temp;

											ls_temp = lo_object.toString();

											if(StringUtils.isValidString(ls_temp))
											{
												for(int i = 0; i < ls_temp.length(); i++)
												{
													char   lc_caracter;
													String ls_guion;

													lc_caracter     = ls_temp.charAt(i);
													ls_guion        = IdentificadoresCommon.SIMBOLO_GUION;

													if(lc_caracter == ls_guion.charAt(0))
														lcs_matriculasExtraidas.add(ls_temp);
												}
											}
										}
									}

									if(CollectionUtils.isValidCollection(lcs_matriculasExtraidas))
									{
										String ls_matriculaValidadaMedidaCautelar;

										ls_matriculaValidadaMedidaCautelar = irr_calificacionRemote
												.validacionMedidaCautelar(
												    lcs_matriculasExtraidas, ls_idTurno, getUserId(),
												    getLocalIpAddress(), getRemoteIpAddress()
												);

										if(StringUtils.isValidString(ls_matriculaValidadaMedidaCautelar))
										{
											Object[] aoa_messageArgs;

											aoa_messageArgs     = new String[1];

											aoa_messageArgs[0] = ls_matriculaValidadaMedidaCautelar;

											addMessage(MessagesKeys.MATRICULA_MEDIDA_CAUTELAR_VIGENTE, aoa_messageArgs);
										}
									}
								}
							}
						}
					}

					{
						boolean lb_mensaje;
						boolean lb_error;
						boolean lb_mens;

						lb_mensaje     = StringUtils.isValidString(ls_mensaje);
						lb_error       = StringUtils.isValidString(ls_mensajeError);
						lb_mens        = StringUtils.isValidString(ls_mens);

						if(lb_mensaje)
							lbpdab_bean.showGrowl(ls_mensaje);

						if(lb_error)
							lbpdab_bean.showGrowl(ls_mensajeError);

						if(lb_mens)
							lbpdab_bean.showGrowl(ls_mens);

						if(lb_mensaje || lb_error || lb_mens)
						{
							ExternalContext lec_externalContext;

							lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
									lf_flash.setKeepMessages(true);
							}
						}
					}
				}
			}
		}

		return ls_result;
	}

	/**
	 * Detalle acto etapa homologacion.
	 *
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String detalleActoEtapaHomologacion()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			setAdministracionHomologacionActosLiquidacion(true);

			ls_return = detalleActoEtapa();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fHomologacionActosLiquidacion:idGrowl");
		}

		return ls_return;
	}

	/**
	 * Generar data.
	 */
	public void generarData()
	{
		generarData(null);
	}

	/**
	 * Generar data.
	 * @param at_turno Argumento de tipo <code>Turno</code> que contiene los
	 * argumentos necesarios para realizar la consulta de recepción de documentos
	 */
	public void generarData(Turno at_turno)
	{
		try
		{
			if(!isDetalleCalificador())
			{
				TramiteCantidad ltc_tc;
				Turno           lt_turno;
				long            ll_idMotivo;

				lt_turno        = new Turno();
				ll_idMotivo     = getIdMotivo();

				lt_turno.setIdUsuarioCreacion(getUserId());
				lt_turno.setNir(getNirConsulta());
				lt_turno.setIdTurno(getIdTurnoConsulta());
				lt_turno.setIdEtapaAnt(getIdEtapaAnt());
				lt_turno.setIdEtapaMenu(getIdEtapaMenu());
				lt_turno.setIndVinculado(isIndVinculado());
				lt_turno.setIndProhibicion(isIndProhibicion());
				lt_turno.setIdEtapaActual(NumericUtils.getLongWrapper(getIdEtapa()));

				if(at_turno != null)
				{
					lt_turno.setIdProceso(at_turno.getIdProceso());
					lt_turno.setIdSubProceso(at_turno.getIdSubProceso());
					lt_turno.setDetalleRecepcionDocumentos(at_turno.isDetalleRecepcionDocumentos());
				}

				ltc_tc = irr_calificacionRemote.findInboxByUserId(lt_turno, ll_idMotivo);

				if(ltc_tc != null)
				{
					Collection<TramiteCantidad> lctc_detalleBandeja;

					lctc_detalleBandeja = ltc_tc.getTurnos();

					if(!CollectionUtils.isValidCollection(lctc_detalleBandeja))
						lctc_detalleBandeja = ltc_tc.getTurnosSuspendidos();

					if(CollectionUtils.isValidCollection(lctc_detalleBandeja))
					{
						boolean lb_mensajeCopias;
						lb_mensajeCopias = false;

						for(TramiteCantidad ltc_tmp : lctc_detalleBandeja)
						{
							if(ltc_tmp != null)
							{
								
								ltc_tmp.setTipificacionTurno(irr_referenceRemote.findTipificacionTurno(
										ltc_tmp.getIdTurno()
									));
								
								if(getIdEtapa() == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
									lb_mensajeCopias = true;
							}
						}

						if(lb_mensajeCopias)
						{
							addMessageInfo(
							    EstadoCommon.W,
							    MessagesKeys.FUNCIONARIO_TRAMITES_EXPEDICION_COPIAS_FECHA_VENCIMIENTO_EN_PANTALLA
							);

							ExternalContext lec_externalContext;

							lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
									lf_flash.setKeepMessages(true);
							}
						}
					}

					setDatosCalificacion(lctc_detalleBandeja);

					if(!CollectionUtils.isValidCollection(getDatosCalificacion()))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
					else
					{
						addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
						PrimeFaces.current().ajax().update("fCalificacion:idGrowl");
					}
				}
				else
				{
					setDatosCalificacion(null);
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}
			}
			else
			{
				UsuarioActividadUI luaiu_usuarioActividad;

				luaiu_usuarioActividad = new UsuarioActividadUI();

				luaiu_usuarioActividad.setIdUsuario(getUserId());
				luaiu_usuarioActividad.setIdTurno(getIdTurnoConsulta());
				luaiu_usuarioActividad.setNir(getNirConsulta());
				luaiu_usuarioActividad.setIdEtapa(NumericUtils.getBigDecimal(getIdEtapa()));
				
				luaiu_usuarioActividad.setIdUsuario(getUserId());
				luaiu_usuarioActividad.setNir(getNirConsulta());
				luaiu_usuarioActividad.setIdTurno(getIdTurnoConsulta());
				luaiu_usuarioActividad.setIdEtapaAnt(getIdEtapaAnt());
				luaiu_usuarioActividad.setIdEtapaMenu(getIdEtapaMenu());
				luaiu_usuarioActividad.setIdEtapaActual(NumericUtils.getLongWrapper(getIdEtapa()));
				luaiu_usuarioActividad.setIdMotivo(NumericUtils.getLongWrapper(getIdMotivo()));

				setDataBandeja(irr_calificacionRemote.findDataBandeja(luaiu_usuarioActividad));

				if(!CollectionUtils.isValidCollection(getDataBandeja()))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCalificacion:idGrowl");
		}
	}

	/**
	 * Metodo sobrecargado para actualizar calificacion o digitalizacion.
	 *
	 * @param atc_tc Tramitecantidad objeto para extraer observaciones
	 */
	public void mostrarObservacionesProcesoAnterior(TramiteCantidad atc_tc)
	{
		mostrarObservacionesProcesoAnterior(atc_tc, false);
	}

	/**
	 * Metodo sobrecargado para actualizar calificacion o digitalizacion.
	 *
	 * @param atc_tc Tramitecantidad objeto para extraer observaciones
	 * @param ab_calificacion correspondiente al valor del tipo de objeto boolean
	 */
	public void mostrarObservacionesProcesoAnterior(TramiteCantidad atc_tc, boolean ab_calificacion)
	{
		mostrarObservacionesProcesoAnterior(atc_tc, ab_calificacion, false);
	}

	/**
	 * Método para poder guardar las observaciones del
	 * proceso anterior.
	 *
	 * @param atc_tc correspondiente al valor del tipo de objeto TramiteCantidad
	 * @param ab_calificacion correspondiente al valor del tipo de objeto boolean
	 * @param ab_coreBachue correspondiente al valor del tipo de objeto boolean
	 */
	public void mostrarObservacionesProcesoAnterior(
	    TramiteCantidad atc_tc, boolean ab_calificacion, boolean ab_coreBachue
	)
	{
		if(atc_tc != null)
		{
			String ls_observacionesProcesoAnterior;

			if(NumericUtils.getLong(atc_tc.getIdEtapa()) == EtapaCommon.ID_ETAPA_460)
				ls_observacionesProcesoAnterior = atc_tc.getObservaciones();
			else
				ls_observacionesProcesoAnterior = observacionesProcesoAnterior(atc_tc.getIdTurnoHistoria());

			if(StringUtils.isValidString(ls_observacionesProcesoAnterior))
			{
				setObservaciones(ls_observacionesProcesoAnterior);
				PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
				PrimeFaces.current().ajax().update("fDialog:id_observacionesProcesoAnterior");
			}
			else
				addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);

			if(!ab_calificacion)
				PrimeFaces.current().ajax().update("fCalificacion:idGrowl");
			else if(ab_coreBachue)
				PrimeFaces.current().ajax().update("fCoreBachue:idGrowl");
			else
				PrimeFaces.current().ajax().update("fLineaDeProduccion:idGrowl");
		}
	}

	/**
	 * Visualizar bandeja.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void visualizarBandeja()
	    throws B2BException
	{
		try
		{
			setNirConsulta(null);
			setIdTurnoConsulta(null);
			setDetalleCalificador(irr_calificacionRemote.visualizarBandeja());
		}
		catch(B2BException lb2b_b2b)
		{
			addMessage(lb2b_b2b);
		}
	}

	/**
	 * Retorna el valor del objeto de Calificacion.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Calificacion
	 */
	private Calificacion cargaIndicadoresDocumento(String as_idTurnoHistoria)
	{
		Calificacion                    lc_calificaion;
		Collection<Map<String, Object>> lcmso_indicadores;

		lc_calificaion = new Calificacion();

		try
		{
			lcmso_indicadores = irr_calificacionRemote.getIndicadoresByIdTurno(as_idTurnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			lcmso_indicadores = null;
		}

		if(lcmso_indicadores != null)
		{
			for(Map<String, Object> lmso_mso : lcmso_indicadores)
			{
				for(Map.Entry<String, Object> lmeso_meso : lmso_mso.entrySet())
				{
					if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.TIPO_DOCUMENTO))
						lc_calificaion.setTipoDocumento(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.NUMERO_DOCUMENTO))
						lc_calificaion.setNumeroDoc(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.FECHA_DOCUMENTO))
						lc_calificaion.setFechaDoc(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.TIPO_OFICINA))
						lc_calificaion.setTipoOficina(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.PAIS_OFICINA))
						lc_calificaion.setPais(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.DEPARTAMENTO_OFICINA))
						lc_calificaion.setDepartamentoOficina(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.MUNICIPIO_OFICINA))
						lc_calificaion.setMunicipioOficina(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.OFICINA_ORIGEN))
						lc_calificaion.setOficinaOrigen(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.FECHA_EJECUTORIA))
						lc_calificaion.setFechaEjecutoria(true);
					else if(lmeso_meso.getValue().toString().contains(IdentificadoresCommon.TIPO_ENTIDAD))
						lc_calificaion.setTipoEntidad(true);
				}
			}

			lc_calificaion.isModDatosDocumento();
		}

		return lc_calificaion;
	}

	/**
	 * Retorna el valor del objeto de Apertura.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Apertura
	 */
	private Apertura obtenerApertura(String as_idSolicitud)
	{
		Apertura la_apertura;

		la_apertura = new Apertura();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Solicitud ls_solicitud;

				ls_solicitud = new Solicitud();

				ls_solicitud.setIdSolicitud(as_idSolicitud);
				ls_solicitud.setDocumento(false);
				ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

				if(ls_solicitud != null)
				{
					Documento ld_documento;

					ld_documento = irr_registroRemote.findDocumento(ls_solicitud.getIdSolicitud());

					if(ld_documento != null)
					{
						OficinaOrigen loo_oficinaOrigen;

						loo_oficinaOrigen = new OficinaOrigen();

						la_apertura.setDocumento(ld_documento);
						la_apertura.setIdTipoOficina(ld_documento.getIdTipoOficina());
						la_apertura.setIdTipoEntidad(ld_documento.getTipoEntidad());

						{
							String     ls_idOficinaOrigen;
							BigDecimal lbd_version;

							ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
							lbd_version            = ld_documento.getVersion();

							la_apertura.setIdOficinaOrigen(ls_idOficinaOrigen);
							la_apertura.setVersion(lbd_version);

							loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
							loo_oficinaOrigen.setVersion(lbd_version);
						}

						loo_oficinaOrigen = irr_registroRemote.findOficinaOrigen(loo_oficinaOrigen);

						if(loo_oficinaOrigen != null)
						{
							la_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
							la_apertura.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
							la_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerApertura", lb2be_e);
			la_apertura = null;
		}

		return la_apertura;
	}
}
