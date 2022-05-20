package com.bachue.snr.prosnr01.web.bean.consulta.SGD;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.ByteArrayUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.SGD.ConsultaSGDRemote;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanActuacionesAdministrativas;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanResolucionRecurso;
import com.bachue.snr.prosnr01.web.bean.traslados.BeanTrasladosDetalle;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase creada para el manejo de eventos en el proceso de consulta al SGD.
 *
 * @author Jorge Patiño
 */
@ManagedBean(name = "beanConsultaSGD")
@SessionScoped
public class BeanConsultaSGD extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8655989370817564044L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaSGD.class);

	/** Propiedad icsgd consulta SGD remote. */
	@EJB
	private ConsultaSGDRemote icsgd_consultaSGDRemote;

	/** Propiedad is expediente consulta. */
	private String is_expedienteConsulta;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is pantalla anterior. */
	private String is_pantallaAnterior;

	/** Propiedad ib new tab. */
	private boolean ib_newTab;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de ExpedienteConsulta.
	 *
	 * @param as_s de as s
	 */
	public void setExpedienteConsulta(String as_s)
	{
		is_expedienteConsulta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expediente consulta.
	 *
	 * @return Retorna el valor de la propiedad expedienteConsulta
	 */
	public String getExpedienteConsulta()
	{
		return is_expedienteConsulta;
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
	 * Modifica el valor de PantallaAnterior.
	 *
	 * @param as_s de as s
	 */
	public void setPantallaAnterior(String as_s)
	{
		is_pantallaAnterior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor pantalla anterior.
	 *
	 * @return el valor de pantalla anterior
	 */
	public String getPantallaAnterior()
	{
		return is_pantallaAnterior;
	}

	/**
	 * Accion regresar.
	 *
	 * @return el valor de string
	 * @throws B2BException 
	 */
	public String accionRegresar() throws B2BException
	{
		String ls_beforeScreen;

		ls_beforeScreen = getPantallaAnterior();

		try
		{
			if(!StringUtils.isValidString(ls_beforeScreen))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(isNewTab())
				PrimeFaces.current().executeScript("window.close()");

			switchActionForScreen(ls_beforeScreen);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_beforeScreen;
	}

	/**
	 * Switch action for screen.
	 *
	 * @param as_beforeScreen de as before screen
	 */
	private void switchActionForScreen(String as_beforeScreen)
	{
		try
		{
			if(StringUtils.isValidString(as_beforeScreen))
			{
				if(comparePageWithAnotherPage(as_beforeScreen, NavegacionCommon.SOLICITUD_VISITAS))
					PrimeFaces.current().executeScript("window.close()");

				else if(comparePageWithAnotherPage(as_beforeScreen, NavegacionCommon.ANALISIS_TRASLADOS_DETALLE))
				{
					BeanTrasladosDetalle lbtd_bean;

					lbtd_bean = (BeanTrasladosDetalle)obtenerInstanciaBean(
						    BeanTrasladosDetalle.class, BeanNameCommon.BEAN_TRASLADO_DETALLE
						);

					if(lbtd_bean != null)
					{
						byte[] lba_archivo;
						byte[] lba_archivo2;

						lba_archivo      = lbtd_bean.getArchivo();
						lba_archivo2     = lbtd_bean.getArchivo2();

						if(ByteArrayUtils.isValidArray(lba_archivo))
							lbtd_bean.setImagenDocumento(
							    generarArchivosDescarga(
							        lba_archivo, TipoContenidoCommon.PDF,
							        IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
							    )
							);

						if(ByteArrayUtils.isValidArray(lba_archivo2))
							lbtd_bean.setImagenDocumento2(
							    generarArchivosDescarga(
							        lba_archivo2, TipoContenidoCommon.PDF,
							        IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
							    )
							);
					}
				}
				else if(comparePageWithAnotherPage(as_beforeScreen, NavegacionCommon.ACTUACIONES_ADMINISTRATIVAS))
				{
					BeanActuacionesAdministrativas lbaa_bean;

					lbaa_bean = (BeanActuacionesAdministrativas)obtenerInstanciaBean(
						    BeanActuacionesAdministrativas.class, BeanNameCommon.BEAN_ACTUACIONES_ADMINISTRATIVAS
						);

					if(lbaa_bean != null)
					{
						byte[] lba_archivo;

						lba_archivo = lbaa_bean.getArchivo();

						if(ByteArrayUtils.isValidArray(lba_archivo))
							lbaa_bean.setImagenDocumento(
							    generarArchivosDescarga(
							        lba_archivo, TipoContenidoCommon.PDF,
							        IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
							    )
							);
					}
				}
				else if(comparePageWithAnotherPage(as_beforeScreen, NavegacionCommon.RESOLUCION_RECURSO))
				{
					BeanResolucionRecurso lbrr_bean;

					lbrr_bean = (BeanResolucionRecurso)obtenerInstanciaBean(
						    BeanResolucionRecurso.class, BeanNameCommon.BEAN_RESOLUCION_RECURSO
						);

					if(lbrr_bean != null)
					{
						byte[] lba_archivo;

						lba_archivo = lbrr_bean.getArchivo();

						if(ByteArrayUtils.isValidArray(lba_archivo))
							lbrr_bean.setImagenDocumento(
							    generarArchivosDescarga(
							        lba_archivo, TipoContenidoCommon.PDF,
							        IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
							    )
							);
					}
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
	 * Compare page with another page.
	 * DEBE SER EN EL ORDEN INDICADO
	 *
	 * @param as_page1 de URL del request
	 * @param as_page2 de URL del navegacion common
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	private boolean comparePageWithAnotherPage(String as_page1, String as_page2)
	{
		boolean lb_match;

		lb_match = false;

		if(StringUtils.isValidString(as_page1) && StringUtils.isValidString(as_page2))
		{
			String ls_url1;
			String ls_url2;

			if(!as_page1.contains(IdentificadoresCommon.SIMBOLO_INTERROGACION))
				ls_url1 = as_page1.substring(
					    as_page1.lastIndexOf(IdentificadoresCommon.SIMBOLO_SLASH) + 1,
					    as_page1.lastIndexOf(IdentificadoresCommon.PUNTO)
					);
			else
				ls_url1 = as_page1.substring(0, as_page1.lastIndexOf(IdentificadoresCommon.SIMBOLO_INTERROGACION));

			ls_url2      = as_page2.substring(0, as_page2.lastIndexOf(IdentificadoresCommon.SIMBOLO_INTERROGACION));
			lb_match     = StringUtils.isValidString(ls_url1) && StringUtils.isValidString(ls_url2)
					&& ls_url1.equalsIgnoreCase(ls_url2);
		}

		return lb_match;
	}

	/**
	 * Consulta SGD.
	 *
	 * @param as_operador de as operador
	 * @return el valor de string
	 */
	public String consultaSGD(String as_operador)
	{
		String ls_result;

		ls_result = null;

		try
		{
			DocumentoOWCC             ldo_parametro;
			Collection<DocumentoOWCC> lcdo_documentos;

			ldo_parametro = new DocumentoOWCC();

			ldo_parametro.setNir(getNir());
			ldo_parametro.setTurno(getIdTurnoConsulta());
			ldo_parametro.setOperador(StringUtils.isValidString(as_operador) ? as_operador : OperadorCommon.OR);

			lcdo_documentos = icsgd_consultaSGDRemote.consultaSGD(
				    ldo_parametro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcdo_documentos))
			{
				FacesContext    lfc_context;
				BeanConsultaSGD lb_beanConsultaSGD;

				lfc_context            = FacesUtils.getFacesContext();
				lb_beanConsultaSGD     = (BeanConsultaSGD)lfc_context.getApplication()
						                                                 .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_CONSULTA_SGD, BeanConsultaSGD.class
						);

				if(lb_beanConsultaSGD != null)
				{
					lb_beanConsultaSGD.setResultadosConsulta(lcdo_documentos);

					{
						HttpServletRequest lhsr_request;

						lhsr_request = FacesUtils.getRequest();

						if(lhsr_request != null)
						{
							String ls_url;

							ls_url = lhsr_request.getServletPath();

							if(StringUtils.isValidString(ls_url))
								lb_beanConsultaSGD.setPantallaAnterior(ls_url);
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}
					}

					ls_result = NavegacionCommon.CONSULTA_SGD;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSGD", lb2be_e);

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

			{
				HttpServletRequest lhsr_request;

				lhsr_request = FacesUtils.getRequest();

				if(lhsr_request != null)
				{
					String        ls_url;
					StringBuilder lsb_sb;

					ls_url     = lhsr_request.getServletPath();
					lsb_sb     = new StringBuilder(ls_url);

					if(StringUtils.isValidString(ls_url))
						switchActionForScreen(ls_url);

					lsb_sb.append(NavegacionCommon.REDIRECT);

					ls_result = lsb_sb.toString();
				}
			}

			addMessage(new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
		}

		return ls_result;
	}

	/**
	 * Consulta SGD.
	 *
	 * @param ldo_documentoOWCC de ldo documento OWCC
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String consultaSGD(DocumentoOWCC ldo_documentoOWCC)
	    throws B2BException
	{
		String ls_result;

		ls_result = null;

		if(ldo_documentoOWCC != null)
		{
			try
			{
				Collection<DocumentoOWCC> lcdo_documentos;

				lcdo_documentos = icsgd_consultaSGDRemote.consultaSGD(
					    ldo_documentoOWCC, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcdo_documentos))
				{
					setResultadosConsulta(lcdo_documentos);

					ls_result = NavegacionCommon.CONSULTA_SGD;

					if(lcdo_documentos.size() <= 3L)
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

						addMessage(MessagesKeys.NO_EXISTEN_DOCUMENTO_MATRICULA_TEMPORAL);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("consultaSGD", lb2be_e);
				throw lb2be_e;
			}
		}

		return ls_result;
	}

	/**
	 * Envio visor SGD.
	 *
	 * @param ado_documento correspondiente al valor del tipo de objeto DocumentoOWCC
	 */
	public void envioVisorSGD(DocumentoOWCC ado_documento)
	{
		try
		{
			if(ado_documento != null)
			{
				String ls_url;

				ls_url = ado_documento.getURL();

				if(StringUtils.isValidString(ls_url))
					FacesContext.getCurrentInstance().getExternalContext().redirect(ls_url);
			}
		}
		catch(IOException lioe_e)
		{
			clh_LOGGER.error(lioe_e);
			addMessage(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
	}

	/**
	 * Valida la propiedad new tab.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en new tab
	 */
	public boolean isNewTab()
	{
		return ib_newTab;
	}

	/**
	 * Modifica el valor de NewTab.
	 *
	 * @param ab_b de ab b
	 */
	public void setNewTab(boolean ab_b)
	{
		ib_newTab = ab_b;
	}
}
