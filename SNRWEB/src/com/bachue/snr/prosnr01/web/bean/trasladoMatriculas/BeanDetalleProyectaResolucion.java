package com.bachue.snr.prosnr01.web.bean.trasladoMatriculas;

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
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.trasladoMatriculas.TrasladoMatriculasRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
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

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanDetalleProyectaResolucion.
 *
 * @author Sebastian Sanchez
 */
@SessionScoped
@ManagedBean(name = "beanDetalleProyectaResolucion")
public class BeanDetalleProyectaResolucion extends BeanTrasladosDetalle implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1905348128127656476L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleProyectaResolucion.class);

	/** Constante cs id form proyectar. */
	private static final String CS_ID_FORM_PROYECTAR = ":fDetalleProyectaResolucion";

	/** Constante cs id form no procede SI. */
	private static final String CS_ID_FORM_NO_PROCEDE_SI = ":fNoProcedeSegundaInstancia";

	/** Constante cs global msg. */
	private static final String CS_GLOBAL_MSG = ":globalMsg";

	/** Constante cs messages proyectar. */
	private static final String CS_MESSAGES_PROYECTAR = CS_ID_FORM_PROYECTAR + CS_GLOBAL_MSG;

	/** Constante cs messages no procede SI. */
	private static final String CS_MESSAGES_NO_PROCEDE_SI = CS_ID_FORM_NO_PROCEDE_SI + CS_GLOBAL_MSG;

	/**  Propiedad laar_actuacionesAdministrativasRemote. */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

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

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Instancia un nuevo objeto bean detalle antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BeanDetalleProyectaResolucion()
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

				ls_result = NavegacionCommon.PROYECTA_RESOLUCION;
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
			actualizarComponente(isSegundaInstancia() ? CS_ID_FORM_NO_PROCEDE_SI : CS_ID_FORM_PROYECTAR);
		}
	}

	/**
	 * Metodo encargado de enviar a etapa de aprobacion.
	 *
	 * @return el valor de string
	 */
	public String enviarAprobadorProyecta()
	{
		String ls_return;

		ls_return = null;

		try
		{
			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setObservaciones(getObservaciones());
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setIdEtapa(
				    isSegundaInstancia() ? EtapaCommon.ID_ETAPA_460 : EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS
				);

				iaar_actuacionesAdministrativasRemote.enviarResponsableActuacionesAdmin(
				    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			{
				BeanProyectaResolucion lbt_bean;

				lbt_bean = (BeanProyectaResolucion)obtenerInstanciaBean(
					    BeanProyectaResolucion.class, BeanNameCommon.BEAN_PROYECTA_RESOLUCION
					);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarDatosTramiteCantidad();

				ls_return = NavegacionCommon.BANDEJA_PROYECTA_RESOLUCION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(isSegundaInstancia() ? CS_ID_FORM_NO_PROCEDE_SI : CS_ID_FORM_PROYECTAR);
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
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 *
	 * @param ab_b de ab suspension
	 */
	public void generarDocumentos(boolean ab_b)
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;
				long                   ll_idMotivo;
				String                 ls_tipoArchivo;

				laa_actuacionesAdministrativas     = new TagPlantillaResolucion();
				ll_idMotivo                        = NumericUtils.getLong(getMotivoTramite());
				ls_tipoArchivo                     = ab_b ? StringUtils.getStringNotNull(getTipoArchivo2())
					                                      : StringUtils.getStringNotNull(getTipoArchivo());

				laa_actuacionesAdministrativas.setOficiosTexto(
				    convertirOficiosTexto(ab_b ? ls_parametros.getOficiosTexto2() : ls_parametros.getOficiosTexto())
				);
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
				laa_actuacionesAdministrativas.setMasivo(true);
				laa_actuacionesAdministrativas.setProyectar(!isSegundaInstancia());
				laa_actuacionesAdministrativas.setSegundaInstancia(isSegundaInstancia());
				laa_actuacionesAdministrativas.getOficiosTexto().setProyectar(true);

				laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.generarDocumentosActuacionesAdmin(
					    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

				if(laa_actuacionesAdministrativas != null)
				{
					PrimeFaces.current().executeScript("PF('wvPoll').start();");
					setFile(laa_actuacionesAdministrativas.getArchivo());

					if(ab_b)
						setMostrarResolucion(true);
					else
						setMostrarDescargarZip(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentos", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			setImagenDocumento2(
			    generarArchivosDescarga(
			        getArchivo2(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);

			actualizarComponente(isSegundaInstancia() ? CS_ID_FORM_NO_PROCEDE_SI : CS_ID_FORM_PROYECTAR);
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

		PrimeFaces.current().ajax().update("fDetalleProyectaResolucion:idGrowl");
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
				generarData();
				actualizarComponente(
				    (isSegundaInstancia() ? CS_ID_FORM_NO_PROCEDE_SI : CS_ID_FORM_PROYECTAR) + ":idDatTableTurnos"
				);
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
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosPoyectaResolucion()
	{
		try
		{
			Suspension ls_data;

			ls_data = getParametros();

			if(ls_data != null)
			{
				BeanDireccion          lbd_beanDireccion;
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				lbd_beanDireccion                  = getBeanDireccion();
				laa_actuacionesAdministrativas     = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(ls_data.getOficiosTexto()));
				laa_actuacionesAdministrativas.setProyectar(!isSegundaInstancia());
				laa_actuacionesAdministrativas.setSegundaInstancia(isSegundaInstancia());
				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());

				{
					DatosDelInteresado lddi_datosDelInteresado;
					FacesContext       lfc_context;

					lddi_datosDelInteresado     = ls_data.getDatosDelInteresado();
					lfc_context                 = FacesContext.getCurrentInstance();

					validarDatosBasicos(lddi_datosDelInteresado, lfc_context);

					if(isEditarDireccionResidencia())
						lbd_beanDireccion.validarCamposDireccionResidencia();

					if(isEditarDireccionCorrespondencia())
						lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);

					if(isEditarDatosContacto())
						validarDatosContacto(ls_data, lfc_context);

					lddi_datosDelInteresado.setDireccionCorrespondencia(
					    lbd_beanDireccion.getDireccionCorrespondencia()
					);
					lddi_datosDelInteresado.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
					lddi_datosDelInteresado.setEditarDatosBasicos(isEditarDatosBasicos());
					lddi_datosDelInteresado.setEditarDatosContacto(isEditarDatosContacto());
					lddi_datosDelInteresado.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
					lddi_datosDelInteresado.setEditarDireccionResidencia(isEditarDireccionResidencia());

					ls_data.setDatosDelInteresado(lddi_datosDelInteresado);
				}

				ls_data.setTurnoHistoria(getTurnoHistoria());
				laa_actuacionesAdministrativas.setSuspension(ls_data);

				laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.visualizarCambiosResolucion(
					    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
					    getLocalIpAddress(), getRemoteIpAddress()
					);

				if(laa_actuacionesAdministrativas != null)
				{
					setArchivo(laa_actuacionesAdministrativas.getArchivo());

					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(isSegundaInstancia() ? CS_MESSAGES_NO_PROCEDE_SI : CS_MESSAGES_PROYECTAR);

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

		{
			setImagenDocumento2(
			    generarArchivosDescarga(
			        getArchivo2(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
		}

		return isSegundaInstancia() ? NavegacionCommon.NO_PROCEDE_SEGUNDA_INSTANCIA
		                            : NavegacionCommon.DETALLE_BANDEJA_PROYECTA_RESOLUCION;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		String ls_return;
		ls_return = NavegacionCommon.BANDEJA_PROYECTA_RESOLUCION;

		try
		{
			FacesContext           lfc_context;
			BeanProyectaResolucion lbde_bean;

			lfc_context     = FacesUtils.getFacesContext();
			lbde_bean       = lfc_context.getApplication()
					                         .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_PROYECTA_RESOLUCION, BeanProyectaResolucion.class
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
}
