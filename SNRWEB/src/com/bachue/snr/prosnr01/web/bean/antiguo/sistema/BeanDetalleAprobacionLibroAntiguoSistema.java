package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.trasladoMatriculas.TrasladoMatriculasRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.traslados.BeanTrasladosDetalle;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetalleAprobacionLibroAntiguoSistema.
 *
 * @author Sebastian Sanchez
 */
@SessionScoped
@ManagedBean(name = "beanDetalleAprobacionLibroAntiguoSistema")
public class BeanDetalleAprobacionLibroAntiguoSistema extends BeanTrasladosDetalle implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6614197677086125561L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleAprobacionLibroAntiguoSistema.class);

	/** Constante is_formularioProcesosGrabacion. */
	private static final String CS_ID_FORM = ":fDetalleAprobacionLibroAntiguoSistema";

	/**  Propiedad laar_actuacionesAdministrativasRemote. */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/** Propiedad is consideraciones. */
	private String is_consideraciones;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad itmr traslado matriculas remote. */
	@EJB
	private TrasladoMatriculasRemote itmr_trasladoMatriculasRemote;

	/** Propiedad ib bandejaentrada. */
	private boolean ib_bandejaentrada;

	/** Propiedad ib consulta individual. */
	private boolean ib_consultaIndividual;

	/** Propiedad ib validacion botones. */
	private boolean ib_validacionBotones;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto bean detalle antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BeanDetalleAprobacionLibroAntiguoSistema()
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
	 * Modifica el valor de consideraciones.
	 *
	 * @param as_s asigna el valor a la propiedad consideraciones
	 */
	public void setConsideraciones(String as_s)
	{
		is_consideraciones = as_s;
	}

	/**
	 * Retorna el valor de consideraciones.
	 *
	 * @return el valor de consideraciones
	 */
	public String getConsideraciones()
	{
		return is_consideraciones;
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
	 * Modifica el valor de ValidacionBotones.
	 *
	 * @param ab_b asigna el valor a la propiedad ValidacionBotones
	 */
	public void setValidacionBotones(boolean ab_b)
	{
		ib_validacionBotones = ab_b;
	}

	/**
	 * Valida la propiedad ValidacionBotones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ValidacionBotones
	 */
	public boolean isValidacionBotones()
	{
		return ib_validacionBotones;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolverDetalleActoLibro()
	{
		setMotivoTramite(null);
		setConsideraciones(null);

		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Cargar archivo autorizacion firma.
	 *
	 * @param ab_b de ab b
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarArchivoAutorizacionFirma(boolean ab_b)
	    throws B2BException
	{
		byte[] lab_file;

		lab_file = null;

		try
		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = getTurnoHistoria();

			if(lth_turnoHistoria != null)
			{
				if(ab_b)
				{
					String ls_consideraciones;

					ls_consideraciones = getConsideraciones();

					if(StringUtils.isValidString(ls_consideraciones))
						lth_turnoHistoria.setConsideracionesAntiguoSistema(ls_consideraciones);
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSIDERACIONES);
				}

				lth_turnoHistoria.setMotivoTramite(NumericUtils.getLongWrapper(getMotivoTramite()));

				lab_file = iasr_antiguoSistemaRemote.generarPdfAutorizacionFirma(
					    lth_turnoHistoria, ab_b, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setValidacionBotones(ab_b);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarArchivoSolicitudFirma", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			setImagenDocumento(
			    generarArchivosDescarga(
			        lab_file, TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Carga la información detallada del turno seleccionado en pantalla.
	 *
	 * @param amso_mso correspondiente al valor del tipo de objeto Map de String,Object
	 * @return enlace a la página de detalle del turno
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String detalleActoEtapa(Map<String, Object> amso_mso)
	    throws B2BException
	{
		String       ls_result;
		FacesContext lfc_context;

		ls_result       = null;
		lfc_context     = FacesUtils.getFacesContext();

		if(CollectionUtils.isValidCollection(amso_mso))
		{
			Map<String, Object> lhmso_hmso;

			lhmso_hmso = (amso_mso instanceof HashMap) ? (HashMap<String, Object>)amso_mso
				                                       : new HashMap<String, Object>(amso_mso);

			BeanPredioDocumentoActo lbpda_bean;

			lbpda_bean = lfc_context.getApplication()
					                    .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO, BeanPredioDocumentoActo.class
					);

			if(lbpda_bean != null)
			{
				lbpda_bean.clear();
				lbpda_bean.setPredio(lhmso_hmso);

				ls_result = NavegacionCommon.APROBACION_LIBRO_ANTIGUO_SISTEMA;
			}
		}

		return ls_result;
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			documentosEnviadosOWCC(getIdTurnoHistoria());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Metodo encargado de enviar a etapa de aprobacion.
	 *
	 * @return el valor de string
	 */
	public String enviarAprobadorAntSistemaFirma()
	{
		String ls_return;

		ls_return = null;

		try
		{
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = getTurnoHistoria();

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria.setObservaciones(getObservaciones());

					iasr_antiguoSistemaRemote.enviarResponsableAntSistemaFirma(
					    lth_turnoHistoria, NumericUtils.getLong(getMotivoTramite()), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);
				}
			}

			{
				setConsideraciones(null);

				BeanAprobacionLibroAntiguoSistema lbt_bean;

				lbt_bean = (BeanAprobacionLibroAntiguoSistema)obtenerInstanciaBean(
					    BeanAprobacionLibroAntiguoSistema.class, BeanNameCommon.BEAN_APROBACION_LIBRO_ANTIGUO_SISTEMA
					);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarDatosTramiteCantidad();

				ls_return = NavegacionCommon.APROBACION_LIBRO_ANTIGUO_SISTEMA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_FORM);
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
		try
		{
			List<Map<String, Object>> llmso_lmso;

			llmso_lmso = itmr_trasladoMatriculasRemote.findDetailInbox(
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

		PrimeFaces.current().ajax().update("fAprobacionLibroAntiguoSistema:idGrowl");
	}

	/**
	 * Método encargado de salvar y terminar los procesos seleccionados.
	 */
	public void salvar()
	{
		try
		{
			List<Map<String, Object>> llmso_lmso;

			llmso_lmso = itmr_trasladoMatriculasRemote.saveDetails(
				    getData(), getUserId(), getLocalIpAddress(), getIdEtapa(), getRemoteIpAddress()
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
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;

		ls_return = NavegacionCommon.APROBACION_LIBRO_ANTIGUO_SISTEMA;

		try
		{
			FacesContext                      lfc_context;
			BeanAprobacionLibroAntiguoSistema lbalas_bean;

			lfc_context     = FacesUtils.getFacesContext();
			lbalas_bean     = lfc_context.getApplication()
					                         .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_APROBACION_LIBRO_ANTIGUO_SISTEMA,
					    BeanAprobacionLibroAntiguoSistema.class
					);

			if(lbalas_bean != null)
			{
				setConsideraciones(null);

				lbalas_bean.clear();
				lbalas_bean.limpiarBandeja();
				lbalas_bean.setIdEtapa(StringUtils.getString(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S));
				lbalas_bean.generarDatosTramiteCantidad();
				lbalas_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S, false);
			}
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("volver", e);
			ls_return = null;
		}

		return ls_return;
	}
}
