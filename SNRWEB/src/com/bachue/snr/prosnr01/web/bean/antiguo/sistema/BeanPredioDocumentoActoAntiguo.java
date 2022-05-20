package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.predio.documento.acto.antiguo.PredioDocumentoActoAntiguoRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.CalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.UIUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanPredioDocumentoActoAntiguo.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "predioDocumentoActoAntiguo")
public class BeanPredioDocumentoActoAntiguo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8822583704935223936L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPredioDocumentoActoAntiguo.class);

	/** Propiedad iasd antiguo sistema data. */
	private AntiguoSistemaData iasd_antiguoSistemaData;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad icas datos etapa 101. */
	private CalificacionAntiguoSistema icas_datosEtapa101;

	/** Propiedad icdcas data consulta ant sistema. */
	private Collection<DataConsultaAntSistema> icdcas_dataConsultaAntSistema;

	/** Propiedad icdcdd data consulta datos documento. */
	private Collection<DataConsultaDatosDocumento> icdcdd_dataConsultaDatosDocumento;

	/** Propiedad icdd consulta datos documento. */
	private ConsultaDatosDocumento icdd_consultaDatosDocumento;

	/** Propiedad idas consulta datos ant sistema. */
	private DatosAntSistema idas_consultaDatosAntSistema;

	/** Propiedad idd documento data. */
	private DocumentoData idd_documentoData;

	/** Propiedad ilmso actos. */
	private List<Map<String, Object>> ilmso_actos;

	/** Propiedad ilmso info antiguo sistema. */
	private List<Map<String, Object>> ilmso_infoAntiguoSistema;

	/** Propiedad ilmso info documentos. */
	private List<Map<String, Object>> ilmso_infoDocumentos;

	/** Propiedad ilmso predio documento. */
	private List<Map<String, Object>> ilmso_predioDocumento;

	/** Propiedad imso predio. */
	private Map<String, Object> imso_predio;

	/** Propiedad iaas predio docmuento acto antiguo remote. */
	@EJB
	private PredioDocumentoActoAntiguoRemote iaas_predioDocmuentoActoAntiguoRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is observaciones calificacion. */
	private String is_observacionesCalificacion;

	/** Propiedad ib asociar matricula. */
	private boolean ib_asociarMatricula;

	/** Propiedad ib crear matricula. */
	private boolean ib_crearMatricula;

	/** Propiedad ib grabar matricula. */
	private boolean ib_grabarMatricula;

	/** Propiedad ib rechazar solicitud. */
	private boolean ib_rechazarSolicitud;

	/** Propiedad ib reporte resultados. */
	private boolean ib_reporteResultados;

	/** Propiedad ib solicitud informacion. */
	private boolean ib_solicitudInformacion;

	/**
	 * Sets the actos.
	 *
	 * @param almso_lmp correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setActos(List<Map<String, Object>> almso_lmp)
	{
		ilmso_actos = almso_lmp;
	}

	/**
	 * Retorna el valor de actos.
	 *
	 * @return el valor de actos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getActos()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_actos))
			ilmso_actos = iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ACTOS
				);

		return ilmso_actos;
	}

	/**
	 * Modifica el valor de antiguo sistema data.
	 *
	 * @param aasd_asd asigna el valor a la propiedad antiguo sistema data
	 */
	public void setAntiguoSistemaData(AntiguoSistemaData aasd_asd)
	{
		iasd_antiguoSistemaData = aasd_asd;
	}

	/**
	 * Retorna el valor de antiguo sistema data.
	 *
	 * @return el valor de antiguo sistema data
	 */
	public AntiguoSistemaData getAntiguoSistemaData()
	{
		return iasd_antiguoSistemaData;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de asociar matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad asociar matricula
	 */
	public void setAsociarMatricula(boolean ab_b)
	{
		ib_asociarMatricula = ab_b;
	}

	/**
	 * Valida la propiedad asociar matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en asociar matricula
	 */
	public boolean isAsociarMatricula()
	{
		return ib_asociarMatricula;
	}

	/**
	 * Modifica el valor de consulta datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad consulta datos ant sistema
	 */
	public void setConsultaDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_consultaDatosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de consulta datos ant sistema.
	 *
	 * @return el valor de consulta datos ant sistema
	 */
	public DatosAntSistema getConsultaDatosAntSistema()
	{
		if(idas_consultaDatosAntSistema == null)
			idas_consultaDatosAntSistema = new DatosAntSistema();

		return idas_consultaDatosAntSistema;
	}

	/**
	 * Modifica el valor de consulta datos documento.
	 *
	 * @param acdd_cdd asigna el valor a la propiedad consulta datos documento
	 */
	public void setConsultaDatosDocumento(ConsultaDatosDocumento acdd_cdd)
	{
		icdd_consultaDatosDocumento = acdd_cdd;
	}

	/**
	 * Retorna el valor de consulta datos documento.
	 *
	 * @return el valor de consulta datos documento
	 */
	public ConsultaDatosDocumento getConsultaDatosDocumento()
	{
		if(icdd_consultaDatosDocumento == null)
			icdd_consultaDatosDocumento = new ConsultaDatosDocumento();

		return icdd_consultaDatosDocumento;
	}

	/**
	 * Modifica el valor de crear matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad crear matricula
	 */
	public void setCrearMatricula(boolean ab_b)
	{
		ib_crearMatricula = ab_b;
	}

	/**
	 * Valida la propiedad crear matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en crear matricula
	 */
	public boolean isCrearMatricula()
	{
		return ib_crearMatricula;
	}

	/**
	 * Modifica el valor de data consulta ant sistema.
	 *
	 * @param acdcas_cdcas asigna el valor a la propiedad data consulta ant sistema
	 */
	public void setDataConsultaAntSistema(Collection<DataConsultaAntSistema> acdcas_cdcas)
	{
		icdcas_dataConsultaAntSistema = acdcas_cdcas;
	}

	/**
	 * Retorna el valor de data consulta ant sistema.
	 *
	 * @return el valor de data consulta ant sistema
	 */
	public Collection<DataConsultaAntSistema> getDataConsultaAntSistema()
	{
		return icdcas_dataConsultaAntSistema;
	}

	/**
	 * Modifica el valor de data consulta datos documento.
	 *
	 * @param adcdc_dcdc asigna el valor a la propiedad data consulta datos documento
	 */
	public void setDataConsultaDatosDocumento(Collection<DataConsultaDatosDocumento> adcdc_dcdc)
	{
		icdcdd_dataConsultaDatosDocumento = adcdc_dcdc;
	}

	/**
	 * Retorna el valor de data consulta datos documento.
	 *
	 * @return el valor de data consulta datos documento
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DataConsultaDatosDocumento> getDataConsultaDatosDocumento()
	    throws B2BException
	{
		return icdcdd_dataConsultaDatosDocumento;
	}

	/**
	 * Modifica el valor de datos etapa 101.
	 *
	 * @param acas_cas asigna el valor a la propiedad datos etapa 101
	 */
	public void setDatosEtapa101(CalificacionAntiguoSistema acas_cas)
	{
		icas_datosEtapa101 = acas_cas;
	}

	/**
	 * Retorna el valor de datos etapa 101.
	 *
	 * @return el valor de datos etapa 101
	 */
	public CalificacionAntiguoSistema getDatosEtapa101()
	{
		if(icas_datosEtapa101 == null)
			icas_datosEtapa101 = new CalificacionAntiguoSistema();

		return icas_datosEtapa101;
	}

	/**
	 * Modifica el valor de documento data.
	 *
	 * @param add_dd asigna el valor a la propiedad documento data
	 */
	public void setDocumentoData(DocumentoData add_dd)
	{
		idd_documentoData = add_dd;
	}

	/**
	 * Retorna el valor de documento data.
	 *
	 * @return el valor de documento data
	 */
	public DocumentoData getDocumentoData()
	{
		return idd_documentoData;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param almso_llhm correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setDocumentos(List<Map<String, Object>> almso_llhm)
	{
		ilmso_infoDocumentos = almso_llhm;
	}

	/**
	 * Retorna el valor de documentos.
	 *
	 * @return el valor de documentos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getDocumentos()
	    throws B2BException
	{
		return ilmso_infoDocumentos;
	}

	/**
	 * Retorna el valor de folios.
	 *
	 * @return el valor de folios
	 */
	public Map<Long, String> getFolios()
	{
		Map<Long, String> lmls_mls;

		lmls_mls = new HashMap<Long, String>();

		for(long ll_folioActual = 1; ll_folioActual <= 500L; ll_folioActual++)
			lmls_mls.put(NumericUtils.getLongWrapper(ll_folioActual), "" + ll_folioActual);

		return lmls_mls;
	}

	/**
	 * Modifica el valor de grabar matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad grabar matricula
	 */
	public void setGrabarMatricula(boolean ab_b)
	{
		ib_grabarMatricula = ab_b;
	}

	/**
	 * Valida la propiedad grabar matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en grabar matricula
	 */
	public boolean isGrabarMatricula()
	{
		return ib_grabarMatricula;
	}

	/**
	 * Modifica el valor de id documento.
	 *
	 * @param as_s asigna el valor a la propiedad id documento
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna el valor de id documento.
	 *
	 * @return el valor de id documento
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		if(!StringUtils.isValidString(is_idTurno))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idTurno");

			if(StringUtils.isValidString(tmp))
				is_idTurno = tmp;
		}

		return is_idTurno;
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
		if(!StringUtils.isValidString(is_idTurnoHistoria))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idTurnoHistoria");

			if(StringUtils.isValidString(tmp))
				is_idTurnoHistoria = tmp;
		}

		return is_idTurnoHistoria;
	}

	/**
	 * Sets the info antiguo sistema.
	 *
	 * @param almso_lhm correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setInfoAntiguoSistema(List<Map<String, Object>> almso_lhm)
	{
		ilmso_infoAntiguoSistema = almso_lhm;
	}

	/**
	 * Retorna el valor de info antiguo sistema.
	 *
	 * @return el valor de info antiguo sistema
	 */
	public List<Map<String, Object>> getInfoAntiguoSistema()
	{
		return ilmso_infoAntiguoSistema;
	}

	/**
	 * Modifica el valor de observaciones calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones calificacion
	 */
	public void setObservacionesCalificacion(String as_s)
	{
		is_observacionesCalificacion = as_s;
	}

	/**
	 * Retorna el valor de observaciones calificacion.
	 *
	 * @return el valor de observaciones calificacion
	 */
	public String getObservacionesCalificacion()
	{
		return is_observacionesCalificacion;
	}

	/**
	 * Retorna el valor de oficina origen.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return el valor de oficina origen
	 */
	public Collection<OficinaOrigen> getOficinaOrigen(boolean ab_accion)
	{
		Collection<OficinaOrigen> lcoo_datos;

		lcoo_datos = null;

		try
		{
			OficinaOrigen loo_oficinaOrigen;

			loo_oficinaOrigen = new OficinaOrigen();

			if(ab_accion)
			{
				ConsultaDatosDocumento lcdd_consultaDatosDocumento;
				lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

				if(lcdd_consultaDatosDocumento != null)
					loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();
			}
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
				{
					Documento ld_documento;

					ld_documento = lcas_datos101.getDocumento();

					if(ld_documento != null)
					{
						loo_oficinaOrigen.setIdTipoOficina(ld_documento.getIdTipoOficina());
						loo_oficinaOrigen.setIdPais(ld_documento.getIdPais());
						loo_oficinaOrigen.setIdDepartamento(ld_documento.getIdDepartamento());
						loo_oficinaOrigen.setIdMunicipio(ld_documento.getIdMunicipio());
					}
				}
			}

			lcoo_datos = irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcoo_datos = null;
		}

		return lcoo_datos;
	}

	/**
	 * Sets the predio.
	 *
	 * @param amso_hm correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setPredio(Map<String, Object> amso_hm)
	{
		imso_predio = amso_hm;
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @return el valor de predio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Map<String, Object> getPredio()
	    throws B2BException
	{
		setAsociarMatricula(false);
		setCrearMatricula(false);
		setGrabarMatricula(false);
		setRechazarSolicitud(false);
		setReporteResultados(false);
		setSolicitudInformacion(false);

		if(imso_predio == null)
			getPredioDocumento();

		return imso_predio;
	}

	/**
	 * Sets the predio documento.
	 *
	 * @param almso_lhm correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setPredioDocumento(List<Map<String, Object>> almso_lhm)
	{
		ilmso_predioDocumento = almso_lhm;
	}

	/**
	 * Modifica el valor de rechazar solicitud.
	 *
	 * @param ib_b asigna el valor a la propiedad rechazar solicitud
	 */
	public void setRechazarSolicitud(boolean ib_b)
	{
		ib_rechazarSolicitud = ib_b;
	}

	/**
	 * Valida la propiedad rechazar solicitud.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rechazar solicitud
	 */
	public boolean isRechazarSolicitud()
	{
		return ib_rechazarSolicitud;
	}

	/**
	 * Modifica el valor de reporte resultados.
	 *
	 * @param ib_b asigna el valor a la propiedad reporte resultados
	 */
	public void setReporteResultados(boolean ib_b)
	{
		ib_reporteResultados = ib_b;
	}

	/**
	 * Valida la propiedad reporte resultados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en reporte resultados
	 */
	public boolean isReporteResultados()
	{
		return ib_reporteResultados;
	}

	/**
	 * Modifica el valor de solicitud informacion.
	 *
	 * @param ib_b asigna el valor a la propiedad solicitud informacion
	 */
	public void setSolicitudInformacion(boolean ib_b)
	{
		ib_solicitudInformacion = ib_b;
	}

	/**
	 * Valida la propiedad solicitud informacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en solicitud informacion
	 */
	public boolean isSolicitudInformacion()
	{
		return ib_solicitudInformacion;
	}

	/**
	 * Retorna el valor de tomos.
	 *
	 * @return el valor de tomos
	 */
	public Map<Long, String> getTomos()
	{
		Map<Long, String> lmls_mls;

		lmls_mls = new HashMap<Long, String>();

		for(long ll_tomoActual = 1; ll_tomoActual <= 100L; ll_tomoActual++)
			lmls_mls.put(NumericUtils.getLongWrapper(ll_tomoActual), "" + ll_tomoActual);

		return lmls_mls;
	}

	/**
	 * Retorna el valor de years ago.
	 *
	 * @return el valor de years ago
	 */
	public Map<String, String> getYearsAgo()
	{
		return UIUtils.getYearsAgo();
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		clear();

		String ls_return;

		ls_return = NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;

		BeanDetalleAntiguoSistema lobdas_bean;
		FacesContext              lfc_context;

		lfc_context     = FacesUtils.getFacesContext();
		lobdas_bean     = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
				                                                    .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA, BeanDetalleAntiguoSistema.class
				);

		if(lobdas_bean != null)
		{
			try
			{
				lobdas_bean.generarData();
			}
			catch(B2BException e)
			{
				clh_LOGGER.error("accionVolver", e);
				ls_return = null;
			}
		}

		return ls_return;
	}

	/**
	 * Cargar etapa 101.
	 */
	public void cargarEtapa101()
	{
		CalificacionAntiguoSistema lccas_datos101;
		String                     ls_observaciones;
		String                     ls_turnoHistoria;

		lccas_datos101       = new CalificacionAntiguoSistema();
		ls_observaciones     = null;
		ls_turnoHistoria     = getIdTurnoHistoria();

		lccas_datos101.getConsultaCriterioAntSistema().setIdTurnoHistoria(NumericUtils.getLong(ls_turnoHistoria));

		try
		{
			lccas_datos101     = iaas_predioDocmuentoActoAntiguoRemote.datosEtapa101(
				    lccas_datos101, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			ls_observaciones = iaas_predioDocmuentoActoAntiguoRemote.getObservacionesCalificacion(
				    ls_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lccas_datos101 == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			else
			{
				CirculoRegistral lcr_circuloRegistra;
				DatosAntSistema  ldas_datosAntSistema;
				Documento        ld_documento;

				lcr_circuloRegistra      = new CirculoRegistral();
				ldas_datosAntSistema     = lccas_datos101.getDatosAntiguoSistema();
				ld_documento             = lccas_datos101.getDocumento();

				if(ldas_datosAntSistema != null)
				{
					setConsultaDatosAntSistema(ldas_datosAntSistema);
					lcr_circuloRegistra.setIdCirculo(ldas_datosAntSistema.getIdCirculo());
				}

				if(ld_documento != null)
				{
					ConsultaDatosDocumento lcdd_datosDocumento;

					lcdd_datosDocumento = new ConsultaDatosDocumento();

					lcdd_datosDocumento.setDocumento(ld_documento);
					lcdd_datosDocumento.setCirculoRegistral(lcr_circuloRegistra);

					setConsultaDatosDocumento(lcdd_datosDocumento);
				}

				setDatosEtapa101(lccas_datos101);
			}

			setObservacionesCalificacion(ls_observaciones);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fcalificacionAntiguoSistema:idGrowl");
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setIdTurnoHistoria(null);
		setDocumentos(null);
		setPredio(null);
		setPredioDocumento(null);
		setActos(null);
		setInfoAntiguoSistema(null);
		setDataConsultaDatosDocumento(null);
		setConsultaDatosDocumento(null);
	}

	/**
	 * Clear consulta.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void clearConsulta(String as_s)
	{
		switch(as_s)
		{
			case "AntSistema":
				setDataConsultaAntSistema(null);
				setConsultaDatosAntSistema(null);

				break;

			case "Documento":
				setDataConsultaDatosDocumento(null);
				setConsultaDatosDocumento(null);

				break;

			case "CalificacionDocumento":
				getDatosEtapa101().setDocumento(null);
				setDataConsultaDatosDocumento(null);
				setConsultaDatosDocumento(null);

				break;

			case "CalificacionAntSistema":
				getDatosEtapa101().setDatosAntiguoSistema(null);
				setDataConsultaAntSistema(null);
				setConsultaDatosAntSistema(null);

				break;

			default:
				break;
		}
	}

	/**
	 * Consulta antiguo sistema.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void consultaAntiguoSistema(boolean ab_accion)
	{
		String ls_idPantalla;

		ls_idPantalla = ab_accion ? ":idDetalleActoAntiguoSistema:" : ":fcalificacionAntiguoSistema:";

		try
		{
			boolean         lb_focus;
			DatosAntSistema ldas_temp;

			lb_focus      = true;
			ldas_temp     = null;

			if(ab_accion)
				ldas_temp = getConsultaDatosAntSistema();
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
					ldas_temp = lcas_datos101.getDatosAntiguoSistema();
			}

			{
				FacesContext lfc_context;
				Long         ll_idLibro;
				String       ls_validator;

				lfc_context      = FacesContext.getCurrentInstance();
				ll_idLibro       = ldas_temp.getIdLibroAntSistema();
				ls_validator     = ldas_temp.getIdCirculo();

				lb_focus     = validateStyles(
					    ls_idPantalla + "idSOMCirculoRegistralAntSistema", lfc_context, ls_validator, lb_focus
					);

				lb_focus = validateStyles(ls_idPantalla + "idSOMLibro", lfc_context, ll_idLibro, lb_focus);

				if(!StringUtils.isValidString(ls_validator))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

				if(!NumericUtils.isValidLong(ll_idLibro))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_LIBRO);
			}

			Collection<DataConsultaAntSistema> lc_dataAntSistema;
			Collection<DataConsultaAntSistema> lc_dataAntSistemaReturn;

			lc_dataAntSistema           = iasr_antiguoSistemaRemote.findByDatosAntSistema(ldas_temp);
			lc_dataAntSistemaReturn     = new ArrayList<DataConsultaAntSistema>();

			if(CollectionUtils.isValidCollection(lc_dataAntSistema))
			{
				boolean lb_hayData;

				lb_hayData = false;

				for(DataConsultaAntSistema iterator : lc_dataAntSistema)
				{
					Collection<DataConsultaPorCriterio> lc_data;
					Collection<DataConsultaPorCriterio> ll_returnData;

					lc_data           = iterator.getDataConsultaPorCriterio();
					ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

					for(DataConsultaPorCriterio iterador : lc_data)
					{
						StringBuilder lsb_direccionCompleta;
						lsb_direccionCompleta = new StringBuilder();

						{
							String ls_tipoEje;
							ls_tipoEje = iterador.getIdTipoEjePrincipal();

							if(StringUtils.isValidString(ls_tipoEje))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " "
						);

						{
							String ls_tipoEje1;
							ls_tipoEje1 = iterador.getIdTipoEjeSecundario();

							if(StringUtils.isValidString(ls_tipoEje1))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje1);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " "
						);
						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getComplementoDireccion()) + " "
						);

						{
							String ls_tmp;
							ls_tmp = lsb_direccionCompleta.toString();

							if(StringUtils.isValidString(ls_tmp))
							{
								iterador.setDireccion(ls_tmp.trim());
								ll_returnData.add(iterador);
							}
						}
					}

					if(CollectionUtils.isValidCollection(ll_returnData))
					{
						iterator.setDataConsultaPorCriterio(ll_returnData);
						lb_hayData = true;
						lc_dataAntSistemaReturn.add(iterator);
					}
				}

				setDataConsultaAntSistema(lc_dataAntSistemaReturn);

				String ls_mensaje;

				ls_mensaje = null;

				if(lb_hayData)
					ls_mensaje = "Consulta exitosa, verifique los datos.";
				else
					ls_mensaje = "No hay datos para mostrar.";

				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update(ls_idPantalla + "idGrowl");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setDataConsultaAntSistema(null);
		}
	}

	/**
	 * Consulta datos documento.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDatosDocumento(boolean ab_accion)
	    throws B2BException
	{
		String ls_idPantalla;

		ls_idPantalla = ab_accion ? ":idDetalleActoAntiguoSistema:" : ":fcalificacionAntiguoSistema:";

		try
		{
			Collection<DataConsultaDatosDocumento> lc_dataDocumento;
			Collection<DataConsultaDatosDocumento> lc_dataDocuemntoReturn;
			ConsultaDatosDocumento                 lcdd_temp;

			if(ab_accion)
			{
				validarDatosDocumento();
				lcdd_temp = getConsultaDatosDocumento();
			}
			else
			{
				validarDatosDocumento101();

				CirculoRegistral           lcr_circuloRegistra;
				CalificacionAntiguoSistema lcas_datos101;
				DatosAntSistema            ldas_datosAntSistema;
				Documento                  ld_documento;
				String                     ls_idCirculo;
				OficinaOrigen              loo_oficinaOrigen;

				lcdd_temp                = new ConsultaDatosDocumento();
				lcr_circuloRegistra      = new CirculoRegistral();
				lcas_datos101            = getDatosEtapa101();
				ldas_datosAntSistema     = null;
				ld_documento             = null;
				ls_idCirculo             = null;
				loo_oficinaOrigen        = new OficinaOrigen();

				if(lcas_datos101 != null)
				{
					String ls_idOficinaOrigen;
					String ls_idTipOficina;

					ldas_datosAntSistema     = lcas_datos101.getDatosAntiguoSistema();
					ld_documento             = lcas_datos101.getDocumento();

					if(ldas_datosAntSistema != null)
					{
						ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

						lcr_circuloRegistra.setIdCirculo(ls_idCirculo);
					}

					if(ld_documento != null)
					{
						ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();
						ls_idTipOficina        = ld_documento.getIdTipoOficina();

						loo_oficinaOrigen.setIdTipoOficina(ls_idTipOficina);
						loo_oficinaOrigen.setIdOficinaOrigen(ls_idOficinaOrigen);
					}
				}

				lcdd_temp.setCirculoRegistral(lcr_circuloRegistra);
				lcdd_temp.setOficinaOrigen(loo_oficinaOrigen);
				lcdd_temp.setDocumento(ld_documento);
			}

			lc_dataDocumento           = iasr_antiguoSistemaRemote.findByDatosDocumento(lcdd_temp);
			lc_dataDocuemntoReturn     = new ArrayList<DataConsultaDatosDocumento>();

			if(CollectionUtils.isValidCollection(lc_dataDocumento))
			{
				for(DataConsultaDatosDocumento iterator : lc_dataDocumento)
				{
					Collection<DataConsultaPorCriterio> lc_data;
					Collection<DataConsultaPorCriterio> ll_returnData;

					lc_data           = iterator.getDataConsultaPorCriterio();
					ll_returnData     = new ArrayList<DataConsultaPorCriterio>();

					for(DataConsultaPorCriterio iterador : lc_data)
					{
						StringBuilder lsb_direccionCompleta;
						lsb_direccionCompleta = new StringBuilder();

						{
							String ls_tipoEje;
							ls_tipoEje = iterador.getIdTipoEjePrincipal();

							if(StringUtils.isValidString(ls_tipoEje))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjePrincipal()) + " "
						);

						{
							String ls_tipoEje1;
							ls_tipoEje1 = iterador.getIdTipoEjeSecundario();

							if(StringUtils.isValidString(ls_tipoEje1))
							{
								TipoEje lte_tmp;
								lte_tmp = new TipoEje();
								lte_tmp.setIdTipoEje(ls_tipoEje1);

								lte_tmp = irr_referenceRemote.findTipoEjeById(lte_tmp);

								if(lte_tmp != null)
									lsb_direccionCompleta.append(
									    StringUtils.getStringNotNull(lte_tmp.getNombre()) + " "
									);
							}
						}

						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getDatoEjeSecundario()) + " "
						);
						lsb_direccionCompleta.append(
						    StringUtils.getStringNotNull(iterador.getComplementoDireccion()) + " "
						);

						{
							String ls_tmp;
							ls_tmp = lsb_direccionCompleta.toString();

							if(StringUtils.isValidString(ls_tmp))
							{
								iterador.setDireccion(ls_tmp.trim());
								ll_returnData.add(iterador);
							}
						}
					}

					iterator.setDataConsultaPorCriterio(ll_returnData);
					lc_dataDocuemntoReturn.add(iterator);
				}

				setDataConsultaDatosDocumento(lc_dataDocuemntoReturn);

				String ls_mensaje = "Consulta exitosa, verifique los datos.";
				addMessage("I", ls_mensaje);
				PrimeFaces.current().ajax().update(ls_idPantalla + "idGrowl");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			setDataConsultaDatosDocumento(null);
		}
	}

	/**
	 * Departamento.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void departamento(boolean ab_accion)
	    throws B2BException
	{
		if(ab_accion)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_idDepartamento;
					ls_idDepartamento = loo_oficinaOrigen.getIdDepartamento();

					if(StringUtils.isValidString(ls_idDepartamento))
						loo_oficinaOrigen.setIdDepartamento(ls_idDepartamento);

					loo_oficinaOrigen.setIdMunicipio(null);
					loo_oficinaOrigen.setIdOficinaOrigen(null);
				}
			}
		}
		else
		{
			CalificacionAntiguoSistema lcas_datos101;

			lcas_datos101 = getDatosEtapa101();

			if(lcas_datos101 != null)
			{
				Documento ld_documento;

				ld_documento = lcas_datos101.getDocumento();

				if(ld_documento != null)
				{
					String ls_idDepartamento;

					ls_idDepartamento = ld_documento.getIdDepartamento();

					if(StringUtils.isValidString(ls_idDepartamento))
						ld_documento.setIdDepartamento(ls_idDepartamento);

					ld_documento.setIdMunicipio(null);
					ld_documento.setIdOficinaOrigen(null);
				}
			}
		}

		findMunicipios(ab_accion);
		getOficinaOrigen(ab_accion);
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
		return null;
	}

	/**
	 * Filtrar por circulo.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void filtrarPorCirculo(boolean ab_accion)
	{
		CalificacionAntiguoSistema lcas_datos101;
		DatosAntSistema            ldas_datosAntSistema;
		String                     ls_idCirculo;
		ZonaRegistral              lcr_zonaRegistral;

		lcas_datos101            = getDatosEtapa101();
		ldas_datosAntSistema     = null;
		ls_idCirculo             = null;
		lcr_zonaRegistral        = new ZonaRegistral();

		if(ab_accion)
		{
			ldas_datosAntSistema = getConsultaDatosAntSistema();

			if(ldas_datosAntSistema != null)
				ls_idCirculo = ldas_datosAntSistema.getIdCirculo();
		}
		else
		{
			if(lcas_datos101 != null)
			{
				ldas_datosAntSistema = lcas_datos101.getDatosAntiguoSistema();

				if(ldas_datosAntSistema != null)
					ls_idCirculo = ldas_datosAntSistema.getIdCirculo();
			}
		}

		if(StringUtils.isValidString(ls_idCirculo) && (ldas_datosAntSistema != null))
		{
			lcr_zonaRegistral.setIdCirculo(ls_idCirculo);

			try
			{
				lcr_zonaRegistral = irr_registroRemote.findZonaRegistralById(lcr_zonaRegistral, getUserId());
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcr_zonaRegistral = null;
			}

			if(lcr_zonaRegistral != null)
			{
				Collection<Departamento> lcd_temp;
				Collection<Municipio>    lcm_mun;
				String                   ls_idDepartamento;
				String                   ls_idMunicipio;

				lcd_temp              = findDepartamentosAntSistema(ab_accion);
				ls_idDepartamento     = lcr_zonaRegistral.getIdDepartamento();
				ls_idMunicipio        = lcr_zonaRegistral.getIdMunicipio();

				if(CollectionUtils.isValidCollection(lcd_temp))
				{
					for(Departamento ld_actual : lcd_temp)
					{
						if(ld_actual != null)
						{
							String ls_idDep;

							ls_idDep = ld_actual.getIdDepartamento();

							if(StringUtils.isValidString(ls_idDep) && ls_idDep.equals(ls_idDepartamento))
							{
								ldas_datosAntSistema.setIdDepartamento(ls_idDepartamento);

								break;
							}
						}
					}
				}

				lcm_mun = findMunicipioAntSistema(ab_accion);

				if(lcm_mun != null)
				{
					for(Municipio lm_actual : lcm_mun)
					{
						if(lm_actual != null)
						{
							String ls_mun;

							ls_mun = lm_actual.getIdMunicipio();

							if(StringUtils.isValidString(ls_mun) && ls_mun.equals(ls_idMunicipio))
							{
								ldas_datosAntSistema.setIdMunicipio(ls_idMunicipio);

								break;
							}
						}
					}
				}
			}
		}

		if(ab_accion)
			setConsultaDatosAntSistema(ldas_datosAntSistema);
		else
		{
			if(lcas_datos101 != null)
				lcas_datos101.setDatosAntiguoSistema(ldas_datosAntSistema);

			setDatosEtapa101(lcas_datos101);
		}
	}

	/**
	 * Filtrar por dep Y mun.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 */
	public void filtrarPorDepYMun(boolean ab_accion)
	{
		CalificacionAntiguoSistema lcas_datos101;
		DatosAntSistema            ldas_datosAntSistema;
		String                     ls_dept;
		String                     ls_mun;

		lcas_datos101            = getDatosEtapa101();
		ldas_datosAntSistema     = null;
		ls_dept                  = null;
		ls_mun                   = null;

		if(ab_accion)
			ldas_datosAntSistema = getConsultaDatosAntSistema();
		else
		{
			if(lcas_datos101 != null)
				ldas_datosAntSistema = lcas_datos101.getDatosAntiguoSistema();
		}

		if(ldas_datosAntSistema != null)
		{
			Collection<ZonaRegistral> lcidt_datos;

			ls_dept         = ldas_datosAntSistema.getIdDepartamento();
			ls_mun          = ldas_datosAntSistema.getIdMunicipio();
			lcidt_datos     = findAllZonaRegistrales();

			if(lcidt_datos != null)
			{
				for(ZonaRegistral lzr_zonaRegistral : lcidt_datos)
				{
					if(lzr_zonaRegistral != null)
					{
						if(
						    lzr_zonaRegistral.getIdDepartamento().equals(ls_dept)
							    && lzr_zonaRegistral.getIdMunicipio().equals(ls_mun)
						)
						{
							ldas_datosAntSistema.setIdCirculo(lzr_zonaRegistral.getIdCirculo());

							if(ab_accion)
								setConsultaDatosAntSistema(ldas_datosAntSistema);
							else
							{
								lcas_datos101.setDatosAntiguoSistema(ldas_datosAntSistema);
								setDatosEtapa101(lcas_datos101);
							}

							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<ZonaRegistral> findAllZonaRegistrales()
	{
		Collection<ZonaRegistral> lczd_zonaRegistral;
		lczd_zonaRegistral = null;

		try
		{
			String ls_idPais;
			ls_idPais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

			if(ls_idPais != null)
				lczd_zonaRegistral = irr_registroRemote.findAllZonaRegistral();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lczd_zonaRegistral = null;
		}

		return lczd_zonaRegistral;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos(boolean ab_accion)
	{
		Collection<Departamento> lcd_departamentos;
		Departamento             ld_parametros;

		lcd_departamentos     = null;
		ld_parametros         = new Departamento();

		try
		{
			if(ab_accion)
			{
				ConsultaDatosDocumento lcdd_onsultaDatosDocumento;

				lcdd_onsultaDatosDocumento = getConsultaDatosDocumento();

				if(lcdd_onsultaDatosDocumento != null)
				{
					OficinaOrigen loo_oficinaOrigen;
					loo_oficinaOrigen = lcdd_onsultaDatosDocumento.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						ls_pais = loo_oficinaOrigen.getIdPais();

						if(StringUtils.isValidString(ls_pais))
							ld_parametros.setIdPais(ls_pais);
					}
				}
			}
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
				{
					Documento ld_documento;

					ld_documento = lcas_datos101.getDocumento();

					if(ld_documento != null)
					{
						String ls_idPais;

						ls_idPais = ld_documento.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
							ld_parametros.setIdPais(ls_idPais);
					}
				}
			}

			lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentosAntSistema(boolean ab_accion)
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			DatosAntSistema ldas_datosAntSistema;
			String          ls_pais;

			ldas_datosAntSistema     = null;
			ls_pais                  = null;

			if(ab_accion)
				ldas_datosAntSistema = getConsultaDatosAntSistema();
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
					ldas_datosAntSistema = lcas_datos101.getDatosAntiguoSistema();
			}

			if(ldas_datosAntSistema != null)
				ls_pais = ldas_datosAntSistema.getIdPais();

			if(!StringUtils.isValidString(ls_pais))
				ls_pais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

			{
				Departamento ld_parametros;
				ld_parametros = new Departamento();

				ld_parametros.setIdPais(ls_pais);

				lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcd_departamentos = null;
		}

		return lcd_departamentos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipioAntSistema(boolean ab_accion)
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			DatosAntSistema ldas_datosAntSistema;

			ldas_datosAntSistema = null;

			if(ab_accion)
				ldas_datosAntSistema = getConsultaDatosAntSistema();
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
					ldas_datosAntSistema = lcas_datos101.getDatosAntiguoSistema();
			}

			if(ldas_datosAntSistema != null)
			{
				String ls_departamento;
				String ls_pais;

				ls_departamento     = ldas_datosAntSistema.getIdDepartamento();
				ls_pais             = ldas_datosAntSistema.getIdPais();

				if(!StringUtils.isValidString(ls_pais))
					ls_pais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

				if(StringUtils.isValidString(ls_departamento))
				{
					Municipio lm_parametros;
					lm_parametros = new Municipio();

					lm_parametros.setIdPais(ls_pais);
					lm_parametros.setIdDepartamento(ls_departamento);

					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Municipio> findMunicipios(boolean ab_accion)
	    throws B2BException
	{
		Collection<Municipio> lcm_municipios;
		Municipio             lm_parametros;

		lcm_municipios     = null;
		lm_parametros      = new Municipio();

		try
		{
			if(ab_accion)
			{
				ConsultaDatosDocumento lcdd_consultaDatosDocumento;
				lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

				if(lcdd_consultaDatosDocumento != null)
				{
					OficinaOrigen loo_oficinaOrigen;

					loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

					if(loo_oficinaOrigen != null)
					{
						String ls_pais;
						String ls_departamento;

						ls_pais             = loo_oficinaOrigen.getIdPais();
						ls_departamento     = loo_oficinaOrigen.getIdDepartamento();

						if(StringUtils.isValidString(ls_pais) && StringUtils.isValidString(ls_departamento))
						{
							lm_parametros.setIdPais(ls_pais);
							lm_parametros.setIdDepartamento(ls_departamento);
						}
					}
				}
			}
			else
			{
				CalificacionAntiguoSistema lcas_datos101;

				lcas_datos101 = getDatosEtapa101();

				if(lcas_datos101 != null)
				{
					Documento ld_documento;

					ld_documento = lcas_datos101.getDocumento();

					if(ld_documento != null)
					{
						String ls_idPais;
						String ls_idDepartamento;

						ls_idPais             = ld_documento.getIdPais();
						ls_idDepartamento     = ld_documento.getIdDepartamento();

						if(StringUtils.isValidString(ls_idPais) && StringUtils.isValidString(ls_idDepartamento))
						{
							lm_parametros.setIdPais(ls_idPais);
							lm_parametros.setIdDepartamento(ls_idDepartamento);
						}
					}
				}
			}

			lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcm_municipios = null;
		}

		return lcm_municipios;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Pais> findPaises()
	{
		Collection<Pais> lcp_paises;

		try
		{
			lcp_paises = irr_referenceRemote.findPaises(true, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcp_paises = null;
		}

		return lcp_paises;
	}

	/**
	 * Generar datos ant sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosAntSistema()
	    throws B2BException
	{
		DatosAntSistema ldas_datosAntSistema;
		ldas_datosAntSistema = iasr_antiguoSistemaRemote.findDatosAntSistema(
			    NumericUtils.getLongWrapper(getIdTurnoHistoria())
			);

		if(ldas_datosAntSistema != null)
		{
			setConsultaDatosAntSistema(ldas_datosAntSistema);
			setAntiguoSistemaData(iasr_antiguoSistemaRemote.findAntiguoSistemaData(getUserId(), ldas_datosAntSistema));
		}

		if(!CollectionUtils.isValidCollection(getInfoAntiguoSistema()))
			setInfoAntiguoSistema(
			    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
			        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.ANT_SISTEMA
			    )
			);
	}

	/**
	 * Generar datos documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosDocumento()
	    throws B2BException
	{
		ConsultaDatosDocumento lcdd_consultaDatosDocumento;
		lcdd_consultaDatosDocumento = iasr_antiguoSistemaRemote.findDatosDocumento(
			    NumericUtils.getLongWrapper(getIdTurnoHistoria())
			);

		if(lcdd_consultaDatosDocumento != null)
		{
			DatosAntSistema ldas_datosAntSistema;
			ldas_datosAntSistema = getConsultaDatosAntSistema();

			if(ldas_datosAntSistema != null)
			{
				String ls_idCirculo;
				ls_idCirculo = ldas_datosAntSistema.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = new CirculoRegistral();
					lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

					lcdd_consultaDatosDocumento.setCirculoRegistral(lcr_circuloRegistral);
				}
			}

			setConsultaDatosDocumento(lcdd_consultaDatosDocumento);

			setDocumentoData(iasr_antiguoSistemaRemote.findDocumentoData(getUserId(), lcdd_consultaDatosDocumento));
		}

		if(!CollectionUtils.isValidCollection(getDocumentos()))
		{
			setDocumentos(
			    iasr_antiguoSistemaRemote.findDatosPredioByTurno(
			        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.DOCUMENTO
			    )
			);

			Map<String, Object> llhm_data;

			if(getDocumentos() != null)
			{
				llhm_data = getDocumentos().get(0);

				if(CollectionUtils.isValidCollection(llhm_data))
					setIdDocumento((String)llhm_data.get("ID_DOCUMENTO"));
			}
		}
	}

	/**
	 * Oficina origen.
	 *
	 * @param lb_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void oficinaOrigen(boolean lb_accion)
	    throws B2BException
	{
		String ls_idPantalla;

		ls_idPantalla = lb_accion ? "idDetalleActoAntiguoSistema" : "fcalificacionAntiguoSistema";

		try
		{
			Collection<OficinaOrigen> lc_oficina;

			lc_oficina = getOficinaOrigen(lb_accion);

			if(lc_oficina == null)
				throw new B2BException(ErrorKeys.NO_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(ls_idPantalla + ":idGrowl");
		}
	}

	/**
	 * Pais.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void pais(boolean ab_accion)
	    throws B2BException
	{
		if(ab_accion)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_pais;
					ls_pais = loo_oficinaOrigen.getIdPais();

					if(StringUtils.isValidString(ls_pais))
						loo_oficinaOrigen.setIdPais(ls_pais);

					loo_oficinaOrigen.setIdDepartamento(null);
					loo_oficinaOrigen.setIdMunicipio(null);
				}
			}
		}
		else
		{
			CalificacionAntiguoSistema lcas_datos101;

			lcas_datos101 = getDatosEtapa101();

			if(lcas_datos101 != null)
			{
				Documento ld_documento;

				ld_documento = lcas_datos101.getDocumento();

				if(ld_documento != null)
				{
					String ls_idPais;

					ls_idPais = ld_documento.getIdPais();

					if(StringUtils.isValidString(ls_idPais))
						ld_documento.setIdPais(ls_idPais);

					ld_documento.setIdDepartamento(null);
					ld_documento.setIdMunicipio(null);
				}
			}
		}

		findDepartamentos(ab_accion);
		findMunicipios(ab_accion);
		getOficinaOrigen(ab_accion);
	}

	/**
	 * Tipo oficina.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void tipoOficina(boolean ab_accion)
	    throws B2BException
	{
		if(ab_accion)
		{
			ConsultaDatosDocumento lcdd_consultaDatosDocumento;
			lcdd_consultaDatosDocumento = getConsultaDatosDocumento();

			if(lcdd_consultaDatosDocumento != null)
			{
				OficinaOrigen loo_oficinaOrigen;
				loo_oficinaOrigen = lcdd_consultaDatosDocumento.getOficinaOrigen();

				if(loo_oficinaOrigen != null)
				{
					String ls_tipoOficina;
					ls_tipoOficina = loo_oficinaOrigen.getIdTipoOficina();

					if(StringUtils.isValidString(ls_tipoOficina))
					{
						TipoOficina lto_to;

						lto_to = new TipoOficina();

						lto_to.setIdTipoOficina(ls_tipoOficina);

						lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

						if(lto_to != null)
						{
							TipoEntidad lte_te;

							lte_te = new TipoEntidad();

							lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

							lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

							loo_oficinaOrigen.setIdTipoEntidad(lte_te.getIdTipoEntidad());
						}

						loo_oficinaOrigen.setIdPais(null);
						loo_oficinaOrigen.setIdDepartamento(null);
						loo_oficinaOrigen.setIdMunicipio(null);
					}
				}
			}
		}
		else
		{
			CalificacionAntiguoSistema lcas_datos101;

			lcas_datos101 = getDatosEtapa101();

			if(lcas_datos101 != null)
			{
				Documento ld_documento;

				ld_documento = lcas_datos101.getDocumento();

				if(ld_documento != null)
				{
					String ls_tipoOficina;

					ls_tipoOficina = ld_documento.getIdTipoOficina();

					if(StringUtils.isValidString(ls_tipoOficina))
					{
						TipoOficina lto_to;

						lto_to = new TipoOficina();

						lto_to.setIdTipoOficina(ls_tipoOficina);

						lto_to = irr_referenceRemote.findTipoOficinaById(lto_to);

						if(lto_to != null)
						{
							TipoEntidad lte_te;

							lte_te = new TipoEntidad();

							lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

							lte_te = irr_referenceRemote.findTipoEntidadById(lte_te);

							ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
						}

						ld_documento.setIdPais(null);
						ld_documento.setIdDepartamento(null);
						ld_documento.setIdMunicipio(null);
					}
				}
			}
		}

		findPaises();
		findDepartamentos(ab_accion);
		findMunicipios(ab_accion);
		getOficinaOrigen(ab_accion);
	}

	/**
	 * Validar datos documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosDocumento()
	    throws B2BException
	{
		boolean                lb_focus;
		ConsultaDatosDocumento lcdd_temp;
		Documento              ld_documento;
		FacesContext           lfc_context;
		OficinaOrigen          loo_oficinaOrigen;

		lb_focus              = true;
		lcdd_temp             = getConsultaDatosDocumento();
		ld_documento          = lcdd_temp.getDocumento();
		lfc_context           = FacesContext.getCurrentInstance();
		loo_oficinaOrigen     = lcdd_temp.getOficinaOrigen();

		try
		{
			CirculoRegistral lcr_circulo;
			lcr_circulo = lcdd_temp.getCirculoRegistral();

			if(lcr_circulo == null)
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			String ls_idCirculoValidator;
			ls_idCirculoValidator     = lcr_circulo.getIdCirculo();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:circuloRegistral", lfc_context, ls_idCirculoValidator, lb_focus
				);

			String ls_tipoDoc;
			ls_tipoDoc     = ld_documento.getIdTipoDocumento();

			lb_focus = validateStyles(":idDetalleActoAntiguoSistema:idSOMEscritura", lfc_context, ls_tipoDoc, lb_focus);

			String ls_numDoc;
			ls_numDoc     = ld_documento.getNumero();

			lb_focus = validateStyles(":idDetalleActoAntiguoSistema:idItDocuActo", lfc_context, ls_numDoc, lb_focus);

			Date ld_fechaDocumento;
			ld_fechaDocumento     = ld_documento.getFechaDocumento();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:idCalFechaDoc", lfc_context, ld_fechaDocumento, lb_focus
				);

			String ls_idTipoOficina;
			ls_idTipoOficina     = loo_oficinaOrigen.getIdTipoOficina();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:idSOMTipoOficina", lfc_context, ls_idTipoOficina, lb_focus
				);

			String ls_idPais;
			ls_idPais     = loo_oficinaOrigen.getIdPais();

			lb_focus = validateStyles(":idDetalleActoAntiguoSistema:idPaisDocumento", lfc_context, ls_idPais, lb_focus);

			String ls_idDepartamento;
			ls_idDepartamento     = loo_oficinaOrigen.getIdDepartamento();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:idSOMDepartamento", lfc_context, ls_idDepartamento, lb_focus
				);

			String ls_idMunicipio;
			ls_idMunicipio     = loo_oficinaOrigen.getIdMunicipio();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:idSOMMunicipio", lfc_context, ls_idMunicipio, lb_focus
				);

			String ls_idOficinaOrigen;
			ls_idOficinaOrigen     = loo_oficinaOrigen.getIdOficinaOrigen();

			lb_focus = validateStyles(
				    ":idDetalleActoAntiguoSistema:idSOMOficinaOrigen", lfc_context, ls_idOficinaOrigen, lb_focus
				);

			if(!StringUtils.isValidString(ls_idCirculoValidator))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			if(!StringUtils.isValidString(ls_tipoDoc))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			if(!StringUtils.isValidString(ls_numDoc))
				throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

			if(ld_fechaDocumento == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

			if(!StringUtils.isValidString(ls_idTipoOficina))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);

			if(!StringUtils.isValidString(ls_idPais))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);

			if(!StringUtils.isValidString(ls_idDepartamento))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);

			if(!StringUtils.isValidString(ls_idMunicipio))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);

			if(!StringUtils.isValidString(ls_idOficinaOrigen))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaDatosDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Validar datos documento 101.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosDocumento101()
	    throws B2BException
	{
		boolean                    lb_focus;
		CalificacionAntiguoSistema lcas_datos101;
		DatosAntSistema            ldas_datosAntSistema;
		Documento                  ld_documento;
		FacesContext               lfc_context;

		lb_focus                 = true;
		lcas_datos101            = getDatosEtapa101();
		ldas_datosAntSistema     = null;
		ld_documento             = null;
		lfc_context              = FacesContext.getCurrentInstance();

		if(lcas_datos101 != null)
		{
			ldas_datosAntSistema     = lcas_datos101.getDatosAntiguoSistema();
			ld_documento             = lcas_datos101.getDocumento();
		}

		try
		{
			if(ldas_datosAntSistema != null)
			{
				String ls_idCirculoValidator;

				ls_idCirculoValidator     = ldas_datosAntSistema.getIdCirculo();

				lb_focus = validateStyles(
					    ":fcalificacionAntiguoSistema:circuloRegistral", lfc_context, ls_idCirculoValidator, lb_focus
					);

				if(!StringUtils.isValidString(ls_idCirculoValidator))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			if(ld_documento != null)
			{
				{
					String ls_tipoDoc;

					ls_tipoDoc     = ld_documento.getIdTipoDocumento();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idSOMEscritura", lfc_context, ls_tipoDoc, lb_focus
						);

					if(!StringUtils.isValidString(ls_tipoDoc))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					String ls_numDoc;

					ls_numDoc     = ld_documento.getNumero();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idItDocuActo", lfc_context, ls_numDoc, lb_focus
						);

					if(!StringUtils.isValidString(ls_numDoc))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					Date ld_fechaDocumento;

					ld_fechaDocumento     = ld_documento.getFechaDocumento();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idCalFechaDoc", lfc_context, ld_fechaDocumento, lb_focus
						);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
				}

				{
					String ls_idTipoOficina;

					ls_idTipoOficina     = ld_documento.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idSOMTipoOficina", lfc_context, ls_idTipoOficina, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
				}

				{
					String ls_idPais;

					ls_idPais     = ld_documento.getIdPais();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idPaisDocumento", lfc_context, ls_idPais, lb_focus
						);

					if(!StringUtils.isValidString(ls_idPais))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento     = ld_documento.getIdDepartamento();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idSOMDepartamento", lfc_context, ls_idDepartamento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;
					ls_idMunicipio     = ld_documento.getIdMunicipio();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idSOMMunicipio", lfc_context, ls_idMunicipio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					String ls_idOficinaOrigen;
					ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();

					lb_focus = validateStyles(
						    ":fcalificacionAntiguoSistema:idSOMOficinaOrigen", lfc_context, ls_idOficinaOrigen, lb_focus
						);

					if(!StringUtils.isValidString(ls_idOficinaOrigen))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);
				}
			}
			else
				throw new B2BException(ErrorKeys.DEBE_REVISAR_DATOS_DOCUMENTO);
		}
		catch(B2BException lb2be_e)
		{
			setDataConsultaDatosDocumento(null);
			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor de predio documento.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void getPredioDocumento()
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(ilmso_predioDocumento))
			ilmso_predioDocumento = iasr_antiguoSistemaRemote.findDatosPredioByTurno(
				    getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.PREDIO
				);

		if(CollectionUtils.isValidCollection(ilmso_predioDocumento))
		{
			imso_predio = ilmso_predioDocumento.get(0);

			if(imso_predio != null)
				setIdSolicitud((String)imso_predio.get(IdentificadoresCommon.ID_SOLICITUD));
		}
	}
}
