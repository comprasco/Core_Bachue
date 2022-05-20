package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetalleAntiguoSistema.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanDetalleAntiguoSistema")
public class BeanDetalleAntiguoSistema extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2776661923365775090L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleAntiguoSistema.class);

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad iccr circulo registral filtrado. */
	private Collection<CirculoRegistral> iccr_circuloRegistralFiltrado;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/**
	 * Propiedad irr reference remote.
	 */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre circulo registral filtrado. */
	private String is_nombreCirculoRegistralFiltrado;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad ib bandejaentrada. */
	private boolean ib_bandejaentrada;

	/** Propiedad ib consulta individual. */
	private boolean ib_consultaIndividual;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto bean detalle antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BeanDetalleAntiguoSistema()
	    throws B2BException
	{
		getIdEtapa();
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
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
	 * @param Modifica el valor de la propiedad iccr_circuloRegistralFiltrado por iccr_circuloRegistralFiltrado
	 */
	public void setCirculoRegistralFiltrado(Collection<CirculoRegistral> ac_cr)
	{
		iccr_circuloRegistralFiltrado = ac_cr;
	}

	/**
	 * @return Retorna el valor de la propiedad iccr_circuloRegistralFiltrado
	 */
	public Collection<CirculoRegistral> getCirculoRegistralFiltrado()
	{
		return iccr_circuloRegistralFiltrado;
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
			String tmp = FacesUtils.getStringFacesParameter("il_idEtapa");

			if(StringUtils.isValidString(tmp))
				il_idEtapa = Long.parseLong(tmp);
		}

		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
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
	 * @param Modifica el valor de la propiedad is_nombreCirculoRegistralFiltrado por is_nombreCirculoRegistralFiltrado
	 */
	public void setNombreCirculoRegistralFiltrado(String as_ncrf)
	{
		is_nombreCirculoRegistralFiltrado = as_ncrf;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreCirculoRegistralFiltrado
	 */
	public String getNombreCirculoRegistralFiltrado()
	{
		return is_nombreCirculoRegistralFiltrado;
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
	 * Carga la información detallada del turno seleccionado en pantalla.
	 *
	 * @return enlace a la página de detalle del turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String detalleActoEtapa()
	    throws B2BException
	{
		String                       ls_result;
		FacesContext                 lfc_context;
		Collection<CirculoRegistral> lccr_circuloRegistral;

		ls_result       = null;
		lfc_context     = FacesUtils.getFacesContext();

		String ls_idTurnoHistoria = FacesUtils.getStringFacesParameter("idTurnoHistoria");
		String ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

		BeanAccionPrediosAntSistema lbapas_bean;

		lbapas_bean = lfc_context.getApplication()
				                     .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_ACCION_PREDIOS_ANT_SISTEMA, BeanAccionPrediosAntSistema.class
				);

		if(lbapas_bean != null)
		{
			lbapas_bean.clear();
			lbapas_bean.limpiarTabuladorDesicion();

			calcularProcesoTurno(lbapas_bean, ls_idTurnoHistoria);

			lbapas_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
			lbapas_bean.setIdTurno(ls_idTurno);
			lbapas_bean.generarDatosAntSistema();
			lbapas_bean.generarDatosDocumento();
			lbapas_bean.generarDatosActualizar();
			lbapas_bean.cargarCrearMatricula(true);
			lbapas_bean.setMostrarWizardPredio(false);
			lbapas_bean.generarDatosActualizar2();
			lbapas_bean.validarSiEsDevolucion();
			lbapas_bean.cambiarFormulario(true);
			lbapas_bean.setEtapa102(false);
			lbapas_bean.setEtapa105(false);
			lbapas_bean.setEtapa101(false);

			ls_result = NavegacionCommon.ANT_SISTEMA;

			if(getIdEtapa() == 101)
			{
				lbapas_bean.setEtapa101(true);
				lbapas_bean.generarObservacionesCalificador();
				lbapas_bean.generarDatosActualizar101();
				lccr_circuloRegistral = irr_referenceRemote.findCirculoRegistralByTurnoEtapa(
					    ls_idTurno, Long.toString(EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lccr_circuloRegistral))
					setCirculoRegistralFiltrado(lccr_circuloRegistral);
			}
			else if(getIdEtapa() == EtapaCommon.ID_ETAPA_CREACION_MATRICULAS_OFICIO)
			{
				lbapas_bean.setEtapa105(true);
				lbapas_bean.setMostrarPanelMotivos(true);
				lbapas_bean.setMotivoTramite(null);

				Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

				lcacd_completitudDocumental = irr_registroRemote.findCompletitudDocumentalByIdTurnoSolicitud(
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

					lbapas_bean.generarMotivos105();
					lbapas_bean.setCompletitudDocumental(lcacd_completitudDocumental);
				}
			}
			else if(getIdEtapa() == EtapaCommon.ID_ETAPA_BUSCADOR_AS_CIRCULO_DESTINO)
			{
				lbapas_bean.setEtapa102(true);
				lbapas_bean.limpiarDetalleAntSistema();
				lbapas_bean.buscarCompletitudComplementacionByTurno();
			}

			lbapas_bean.setIdEtapa(NumericUtils.getLongWrapper(getIdEtapa()));
			lbapas_bean.generarProcesos();
			lbapas_bean.obtenerDatosAntSistema();
			lbapas_bean.cargarObservacionesTramiteActual();
			lbapas_bean.verificarRevisionPredios();
			lbapas_bean.generarMotivos105();
		}

		return ls_result;
	}

	/**
	 * Generar data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarData()
	    throws B2BException
	{
		try
		{
			List<Map<String, Object>> llmso_lmso;

			llmso_lmso = iasr_antiguoSistemaRemote.findDetailInbox(
				    getUserId(), NumericUtils.getLongWrapper(getIdEtapa()), getIdTurnoHistoria(), getNir()
				);

			if(CollectionUtils.isValidCollection(llmso_lmso))
			{
				setData(llmso_lmso);
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para poder guardar las observaciones del
	 * proceso anterior.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void mostrarObservacionesProcesoAnterior(String as_s)
	{
		if(StringUtils.isValidString(as_s))
		{
			setObservaciones(as_s);
			PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
			PrimeFaces.current().ajax().update("fDialog:id_observacionesProcesoAnterior");
		}
		else
			addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);

		PrimeFaces.current().ajax().update("fDdetalleAntiguoSistema:idGrowl");
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;
		ls_return = NavegacionCommon.ANTIGUO_SISTEMA;

		try
		{
			FacesContext       lfc_context;
			BeanAntiguoSistema lbde_bean;

			lfc_context     = FacesUtils.getFacesContext();
			lbde_bean       = lfc_context.getApplication()
					                         .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_ANTIGUO_SISTEMA, BeanAntiguoSistema.class
					);

			if(lbde_bean != null)
			{
				lbde_bean.clear();
				lbde_bean.generarDatosTramiteCantidad();
			}
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("volver", e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Consulta en base de datos el proceso actual al cual pertenece el tramite en proceso
	 *
	 * @param abapas_bean Objeto contenedor de la información de la pantalla
	 * @param as_idTurnoHistoria Identificador del tramite en curso
	 * @throws B2BException Si sucede un error en la conexión o consulta en base de datos
	 */
	private void calcularProcesoTurno(BeanAccionPrediosAntSistema abapas_bean, String as_idTurnoHistoria)
	    throws B2BException
	{
		if((abapas_bean != null) && StringUtils.isValidString(as_idTurnoHistoria))
		{
			TurnoHistoria lth_th;

			lth_th = new TurnoHistoria();

			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lth_th.setFechaEtapaValida(true);

			lth_th = iasr_antiguoSistemaRemote.findTurnoHistoriaById(lth_th);

			if(lth_th != null)
			{
				String ls_idProceso;

				ls_idProceso = StringUtils.getStringNotNull(lth_th.getIdProceso());

				if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_37))
					abapas_bean.setGrabacionDeMatriculas(true);
				else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
					abapas_bean.setProcesoCorrecciones(true);
				else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
					abapas_bean.setProcesoCertificados(true);
			}
		}
	}
}
