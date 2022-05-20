package com.bachue.snr.prosnr21.web.bean;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import org.primefaces.component.calendar.Calendar;

import org.primefaces.component.inputnumber.InputNumber;

import org.primefaces.component.inputtext.InputText;

import org.primefaces.component.inputtextarea.InputTextarea;

import org.primefaces.component.outputlabel.OutputLabel;

import org.primefaces.component.repeat.UIRepeat;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import org.primefaces.component.selectonemenu.SelectOneMenu;

import org.primefaces.component.selectoneradio.SelectOneRadio;

import org.primefaces.component.tabview.TabView;

import org.primefaces.context.RequestContext;

import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.model.chart.PieChartModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BaseBean.
 *
 * @author garias
 */
public abstract class BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4906972207931572077L;

	/** Constante CONFIG_MESSAGES. */
	public static final String CONFIG_MESSAGES = "com.bachue.snr.prosnr21.web.bean.resources.Messages";

	/** Constante CONFIG_ERRORS. */
	public static final String CONFIG_ERRORS = "com.bachue.snr.prosnr21.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_EN. */
	public static final String CONFIG_ERRORS_EN = "com.bachue.snr.prosnr21.web.bean.resources.Errors_en";

	/** Constante CONFIG_MESSAGES_EN. */
	public static final String CONFIG_MESSAGES_EN = "com.bachue.snr.prosnr21.web.bean.resources.Messages_en";

	/** Constante CONFIG_MESSAGES_OPTIONS. */
	public static final String CONFIG_MESSAGES_OPTIONS = "com.bachue.snr.prosnr21.web.bean.resources.Options";

	/** Constante CONFIG_MESSAGES_OPTIONS_EN. */
	public static final String CONFIG_MESSAGES_OPTIONS_EN = "com.bachue.snr.prosnr21.web.bean.resources.Options_en";

	/** Propiedad im messages. */
	private static Messages im_messagesOpciones;

	/** Propiedad im messages. */
	private static Messages im_messages;

	/** Propiedad de opciones para el menu de bachue*/
	private Collection<Opcion> ico_opciones;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipcm pie chart model. */
	private PieChartModel ipcm_pieChartModel;

	/**Propiedad idioma español*/
	private boolean ib_idiomaEspanol;

	/** Propiedad ib mostrar grafica. */
	private boolean ib_mostrarGrafica;

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	public static Messages getMessages()
	{
		if(im_messages == null)
			im_messages = new Messages(CONFIG_MESSAGES);

		return im_messages;
	}

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	public static Messages getMessagesOpciones()
	{
		if(im_messagesOpciones == null)
			im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS);

		return im_messagesOpciones;
	}

	/**
	 * Adds de message.
	 *
	 * @param as_mensaje
	 *            correspondiente al valor del tipo de objeto String
	 */
	public static void addMessage(String as_mensaje)
	{
		if(StringUtils.isValidString(as_mensaje))
			addMessage("I", getMessages().getString(as_mensaje));
	}

	/**
	 * Adds de message.
	 *
	 * @param as_mensaje
	 *            correspondiente al valor del tipo de objeto String
	 * @param aoa_oa
	 *            correspondiente al valor del tipo de objeto Object[]
	 */
	public static void addMessage(String as_mensaje, Object[] aoa_oa)
	{
		if(StringUtils.isValidString(as_mensaje))
		{
			if(aoa_oa != null)
				addMessage("I", getMessages().getString(as_mensaje, aoa_oa));
			else
				addMessage(as_mensaje);
		}
	}

	/**
	 * Método para agregar un mensajde al growl de la pantalla
	 * @param as_tipo tipo de mensaje
	 * @param as_mensaje el mensaje a agregar
	 * @param aoa_oa con el parametro del mensaje a mostrar
	 */
	public static void addMessage(String as_tipo, String as_mensaje, Object[] aoa_oa)
	{
		if(StringUtils.isValidString(as_mensaje))
		{
			if(aoa_oa != null)
				addMessage(as_tipo, getMessages().getString(as_mensaje, aoa_oa));
			else
				addMessage(as_mensaje);
		}
	}

	/**
	 * Adds de message.
	 *
	 * @param as_tipo
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_mensaje
	 *            correspondiente al valor del tipo de objeto String
	 */
	public static void addMessage(String as_tipo, String as_mensaje)
	{
		FacesContext lfc_context;
		lfc_context = FacesContext.getCurrentInstance();

		if(StringUtils.isValidString(as_tipo) && StringUtils.isValidString(as_mensaje))
		{
			FacesMessage.Severity lfs_severity;
			lfs_severity = null;

			if(as_tipo.equalsIgnoreCase("E"))
			{
				as_tipo          = "Error: ";
				lfs_severity     = FacesMessage.SEVERITY_ERROR;
			}
			else if(as_tipo.equalsIgnoreCase("I"))
			{
				as_tipo          = "Información: ";
				lfs_severity     = FacesMessage.SEVERITY_INFO;
			}
			else if(as_tipo.equalsIgnoreCase("F"))
			{
				as_tipo          = "Error Fatal: ";
				lfs_severity     = FacesMessage.SEVERITY_FATAL;
			}
			else if(as_tipo.equalsIgnoreCase("W"))
			{
				as_tipo          = "Advertencia: ";
				lfs_severity     = FacesMessage.SEVERITY_WARN;
			}
			else
				lfc_context.addMessage(null, new FacesMessage(as_tipo, as_mensaje));

			if(lfs_severity != null)
				lfc_context.addMessage(null, new FacesMessage(lfs_severity, as_tipo, as_mensaje));
		}
	}

	/**
	 * Adds de message.
	 *
	 * @param ab2be_exception
	 *            correspondiente al valor del tipo de objeto B2BException
	 */
	public static void addMessage(B2BException ab2be_exception)
	{
		if(ab2be_exception != null)
			addMessage("W", ab2be_exception.getMessage());
	}

	/**
	 * Adds de message info.
	 *
	 * @param as_tipo
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_mensaje
	 *            correspondiente al valor del tipo de objeto String
	 */
	public static void addMessageInfo(String as_tipo, String as_mensaje)
	{
		if(StringUtils.isValidString(as_tipo) && StringUtils.isValidString(as_mensaje))
			addMessage(as_tipo, getMessages().getString(as_mensaje));
	}

	/**
	 * Clean input text.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 */
	public static void cleanInputText(String as_idComponente, FacesContext afc_pantallaActual)
	{
		InputText lsom_som;
		lsom_som = (InputText)afc_pantallaActual.getViewRoot().findComponent(as_idComponente);

		if(lsom_som != null)
			lsom_som.setStyle("border-color:null");
	}

	/**
	 * Clean output label.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 */
	public static void cleanOutputLabel(String as_idComponente, FacesContext afc_pantallaActual)
	{
		OutputLabel lsom_som;
		lsom_som = (OutputLabel)afc_pantallaActual.getViewRoot().findComponent(as_idComponente);

		if(lsom_som != null)
			lsom_som.setStyle("color:#4f4f4f");
	}

	/**
	 * Retorna el valor de application.
	 *
	 * @return el valor de application
	 */
	public abstract String getApplication();

	/**
	 * Retorna el valor de local ip address.
	 *
	 * @return el valor de local ip address
	 */
	public String getLocalIpAddress()
	{
		HttpServletRequest lhsr_request;

		lhsr_request = FacesUtils.getRequest();

		return (lhsr_request != null) ? lhsr_request.getLocalAddr() : null;
	}

	/**
	 * Modifica el valor de modelo torta
	 *
	 * @param apcm_pcm asigna el valor a la propiedad turnos asg
	 */
	public void setModeloTorta(PieChartModel apcm_pcm)
	{
		ipcm_pieChartModel = apcm_pcm;
	}

	/**
	 * Retorna el valor de modelo torta.
	 *
	 * @return el valor de modelo torta
	 */
	public PieChartModel getModeloTorta()
	{
		return ipcm_pieChartModel;
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
	 * Retorna el valor de remote ip address.
	 *
	 * @return el valor de remote ip address
	 */
	public String getRemoteIpAddress()
	{
		HttpServletRequest lhsr_request;

		lhsr_request = FacesUtils.getRequest();

		return (lhsr_request != null) ? lhsr_request.getRemoteAddr() : null;
	}

	/**
	 * Retorna el valor de user id.
	 *
	 * @return el valor de user id
	 */
	public String getUserId()
	{
		return FacesUtils.getSessionAttributeAsString(IdentificadoresCommon.SESION_USUARIO);
	}

	/**
	 * @return Retorna el valor de la propiedad ico_opciones
	 */
	public Collection<Opcion> getOpciones()
	{
		return ico_opciones;
	}

	/**
	 * @param Modifica el valor de la propiedad ico_opciones por ico_opciones
	 */
	public void setOpciones(Collection<Opcion> ico_opciones)
	{
		this.ico_opciones = ico_opciones;
	}

	/**
	 * Método encargado de validar una colección el cual se usa para las pantallas
	 *
	 * @param acu_collection
	 *            <code>Collection</code> la cual se validará
	 * @return <code>boolean</code> que indica sí la colección es valida
	 */
	public boolean isValidCollection(Collection<?> acu_collection)
	{
		return CollectionUtils.isValidCollection(acu_collection);
	}

	/**
	 * Abrir cerrar panel.
	 *
	 * @param ab_abrir
	 *            correspondiente al valor del tipo de objeto boolean
	 * @param as_panel
	 *            correspondiente al valor del tipo de objeto String
	 */
	public void abrirCerrarPanel(boolean ab_abrir, String as_panel)
	{
		if(StringUtils.isValidString(as_panel))
		{
			PrimeFaces lpf_current;

			lpf_current = PrimeFaces.current();

			if(ab_abrir)
				lpf_current.executeScript("PF('" + as_panel + "').expand();");
			else
				lpf_current.executeScript("PF('" + as_panel + "').collapse();");
		}
	}

	/**
	 * Metodo encargado de abrir dialogos de tipo modal.
	 *
	 * @param as_widget
	 *            Argumento de tipo <code>String</code> que contiene el id del
	 *            widget con el que se abre el dialogo.
	 * @param as_idForm
	 *            Argumento de tipo <code>String</code> que contiene el id del
	 *            formulario del dialogo a actualizar.
	 */
	public void abrirDialogo(String as_widget, String as_idForm)
	{
		if(StringUtils.isValidString(as_widget))
		{
			PrimeFaces.current().executeScript("PF('" + as_widget + "').show();");
			actualizarComponente(as_idForm);
		}
	}

	/**
	 * Actualizar componente.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 */
	public void actualizarComponente(String as_idComponente)
	{
		if(StringUtils.isValidString(as_idComponente))
		{
			Ajax la_ajax;

			la_ajax = PrimeFaces.current().ajax();

			if(la_ajax != null)
				la_ajax.update(as_idComponente);
		}
	}

	/**
	 * Metodo encargado de cerrar dialogos de tipo modal.
	 *
	 * @param as_widget
	 *            Argumento de tipo <code>String</code> que contiene el id del
	 *            widget con el que se abre el dialogo.
	 * @param as_form
	 *            correspondiente al valor del tipo de objeto String
	 */
	public void cerrarDialogo(String as_widget, String as_form)
	{
		if(StringUtils.isValidString(as_widget))
		{
			PrimeFaces lpf_faces;

			lpf_faces = PrimeFaces.current();

			lpf_faces.executeScript("PF('" + as_widget + "').hide();");
			lpf_faces.ajax().update(as_form);
		}
	}

	/**
	 * Clean select one menu.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 */
	public static void cleanSelectOneMenu(String as_idComponente, FacesContext afc_pantallaActual)
	{
		SelectOneMenu lsom_som;
		lsom_som = (SelectOneMenu)afc_pantallaActual.getViewRoot().findComponent(as_idComponente);

		if(lsom_som != null)
			lsom_som.setStyle("border-color:null; width:92%;");
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @param as_campo
	 *            correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de int
	 */
	public int contarCaracteres(String as_campo)
	{
		int li_contador;

		li_contador = 0;

		if(as_campo != null)
		{
			char[] lca_arrayChar;

			lca_arrayChar = as_campo.toCharArray();

			if(lca_arrayChar != null)
				li_contador = lca_arrayChar.length;
		}

		return li_contador;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_texto
	 *            correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 */
	public String convertirTextoParaRtf(String as_texto)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_texto))
		{
			if(as_texto.contains("<img "))
				throw new B2BException(ErrorKeys.ERROR_IMAGENES_EN_TEXTO);

			if(as_texto.contains("<p>"))
				as_texto = as_texto.replace("<p>", "{\\pard\\qj ");

			if(as_texto.contains("</p>"))
				as_texto = as_texto.replace("</p>", "\\par}");

			{
				int    li_inicioTag;
				int    li_finalTag;
				String ls_tagSinCerrar;

				while(as_texto.contains("<") && as_texto.contains(">"))
				{
					li_inicioTag        = as_texto.indexOf("<");
					li_finalTag         = as_texto.substring(li_inicioTag).indexOf(">");
					li_finalTag         = li_finalTag + li_inicioTag + 1;
					ls_tagSinCerrar     = as_texto.substring(li_inicioTag, li_finalTag);

					if(StringUtils.isValidString(ls_tagSinCerrar))
						as_texto = as_texto.replace(ls_tagSinCerrar, "");
				}
			}
		}

		return as_texto;
	}

	/**
	 * Retorna el valor del objeto de DefaultStreamedContent.
	 *
	 * @param aba_archivo
	 *            correspondiente al valor del tipo de objeto byte[]
	 * @param as_tipoArchivo
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_nombreArchivo
	 *            correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de DefaultStreamedContent
	 */
	public static DefaultStreamedContent generarArchivosDescarga(
	    byte[] aba_archivo, String as_tipoArchivo, String as_nombreArchivo
	)
	{
		DefaultStreamedContent ldsc_streamedContent;

		ldsc_streamedContent = null;

		if(aba_archivo != null)
		{
			InputStream lis_stream;

			lis_stream     = new ByteArrayInputStream(aba_archivo);

			ldsc_streamedContent = new DefaultStreamedContent(lis_stream, as_tipoArchivo, as_nombreArchivo);
		}

		return ldsc_streamedContent;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_id
	 *            correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de String
	 */
	public String generateId(String as_id)
	{
		String ls_id;

		ls_id = null;

		if(StringUtils.isValidString(as_id))
		{
			int li_i;

			li_i      = as_id.indexOf("_");
			ls_id     = as_id.substring(li_i + 1);
			li_i      = ls_id.indexOf("-");
			ls_id     = ls_id.substring(0, li_i);
		}

		return ls_id;
	}

	/**
	 * Método para obtener la instancia de un bean.
	 *
	 * @param acu_bean
	 *            <code>Class ?</code> del bean que se quiere obtener.
	 * @param as_beanNameCommon
	 *            <code>String</code> que contiene el nombre del bean.
	 * @return <code>Object</code> que contiene la instancia del bean obtenido.
	 * @throws B2BException
	 */
	public Object obtenerInstanciaBean(Class<?> acu_bean, String as_beanNameCommon)
	    throws B2BException
	{
		Object lb_bean;

		lb_bean = null;

		if((acu_bean != null) && StringUtils.isValidString(as_beanNameCommon))
		{
			FacesContext lfc_context;

			lfc_context     = FacesUtils.getFacesContext();
			lb_bean         = (Object)lfc_context.getApplication()
					                                 .evaluateExpressionGet(lfc_context, as_beanNameCommon, acu_bean);

			if((lb_bean == null) || !acu_bean.isInstance(lb_bean))
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}

		return lb_bean;
	}

	/**
	 * Validate styles.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param ad_fecha
	 *            correspondiente al valor del tipo de objeto Date
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStyles(
	    String as_idComponente, FacesContext afc_pantallaActual, Date ad_fecha, boolean ab_focus
	)
	{
		return validateStyles(
		    as_idComponente, afc_pantallaActual,
		    (ad_fecha == null) ? IdentificadoresCommon.DATO_INVALIDO : IdentificadoresCommon.DATO_VALIDO, ab_focus
		);
	}

	/**
	 * Validate styles.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param as_datoParaValidar
	 *            correspondiente al valor del tipo de objeto String
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStyles(
	    String as_idComponente, FacesContext afc_pantallaActual, String as_datoParaValidar, boolean ab_focus
	)
	{
		UIComponent luic_uic;
		boolean     lb_return;

		lb_return     = false;
		luic_uic      = afc_pantallaActual.getViewRoot().findComponent(as_idComponente);

		if(luic_uic != null)
		{
			String ls_style;

			if(luic_uic instanceof InputText)
			{
				InputText lit_it;

				lit_it = (InputText)luic_uic;

				if(lit_it != null)
				{
					ls_style     = lit_it.getStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lit_it.setStyle(ls_style);
				}
			}
			else if(luic_uic instanceof InputNumber)
			{
				InputNumber lin_in;

				lin_in = (InputNumber)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getInputStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lin_in.setInputStyle(ls_style);
				}
			}
			else if(luic_uic instanceof InputTextarea)
			{
				InputTextarea lin_in;

				lin_in = (InputTextarea)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lin_in.setStyle(ls_style);
				}
			}
			else if(luic_uic instanceof Calendar)
			{
				Calendar lin_in;

				lin_in = (Calendar)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getInputStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lin_in.setInputStyle(ls_style);
				}
			}
			else if(luic_uic instanceof OutputLabel)
			{
				OutputLabel lin_in;

				lin_in = (OutputLabel)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getStyle();

					ls_style      = changeStyleComponent(as_idComponente, as_datoParaValidar, ls_style, ab_focus, true);
					lb_return     = ls_style.contains("color:black;");

					lin_in.setStyle(ls_style);
				}
			}
			else if(luic_uic instanceof SelectBooleanCheckbox)
			{
				SelectBooleanCheckbox lin_in;

				lin_in = (SelectBooleanCheckbox)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lin_in.setStyle(ls_style);
				}
			}
			else if(luic_uic instanceof SelectOneMenu)
			{
				SelectOneMenu lin_in;

				lin_in = (SelectOneMenu)luic_uic;

				if(lin_in != null)
				{
					ls_style     = lin_in.getStyle();

					ls_style      = changeStyleComponent(
						    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
						);
					lb_return     = ls_style.contains("border-color:null");

					lin_in.setStyle(ls_style);
				}
			}
			else if(luic_uic instanceof SelectOneRadio)
			{
				SelectOneRadio lsor_sor;

				lsor_sor = (SelectOneRadio)luic_uic;

				if(lsor_sor != null)
				{
					ls_style     = lsor_sor.getStyle();

					ls_style      = changeStyleComponent(as_idComponente, as_datoParaValidar, ls_style, ab_focus, true);
					lb_return     = ls_style.contains("color:black;");

					lsor_sor.setStyle(ls_style);
				}
			}
		}

		return lb_return;
	}

	/**
	 * Validate styles.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param al_dato
	 *            correspondiente al valor del tipo de objeto Long
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStyles(
	    String as_idComponente, FacesContext afc_pantallaActual, Long al_dato, boolean ab_focus
	)
	{
		return validateStyles(
		    as_idComponente, afc_pantallaActual,
		    NumericUtils.isValidLong(al_dato) ? IdentificadoresCommon.DATO_VALIDO : IdentificadoresCommon.DATO_INVALIDO,
		    ab_focus
		);
	}

	/**
	 * Validate styles.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param abd_dato
	 *            correspondiente al valor del tipo de objeto BigDecimal
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStyles(
	    String as_idComponente, FacesContext afc_pantallaActual, BigDecimal abd_dato, boolean ab_focus
	)
	{
		return validateStyles(
		    as_idComponente, afc_pantallaActual,
		    NumericUtils.isValidBigDecimal(abd_dato) ? IdentificadoresCommon.DATO_VALIDO
		                                             : IdentificadoresCommon.DATO_INVALIDO, ab_focus
		);
	}

	/**
	 * Validate styles.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param ad_dato
	 *            correspondiente al valor del tipo de objeto Double
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStyles(
	    String as_idComponente, FacesContext afc_pantallaActual, Double ad_dato, boolean ab_focus
	)
	{
		return validateStyles(
		    as_idComponente, afc_pantallaActual,
		    NumericUtils.isValidDouble(ad_dato) ? IdentificadoresCommon.DATO_VALIDO : IdentificadoresCommon.DATO_INVALIDO,
		    ab_focus
		);
	}

	/**
	 * Validate styles repeat tab.
	 *
	 * @param as_idRepeat
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param as_idTabView
	 *            correspondiente al valor del tipo de objeto String
	 * @param ad_datoParaValidar
	 *            correspondiente al valor del tipo de objeto Date
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStylesRepeatTab(
	    String as_idRepeat, String as_idComponente, FacesContext afc_pantallaActual, String as_idTabView,
	    Date ad_datoParaValidar, boolean ab_focus
	)
	{
		return validateStylesRepeatTab(
		    as_idRepeat, as_idComponente, afc_pantallaActual, as_idTabView,
		    (ad_datoParaValidar == null) ? IdentificadoresCommon.DATO_INVALIDO : IdentificadoresCommon.DATO_VALIDO,
		    ab_focus
		);
	}

	/**
	 * Validate styles repeat tab.
	 *
	 * @param as_idRepeat
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param afc_pantallaActual
	 *            correspondiente al valor del tipo de objeto FacesContext
	 * @param as_idTabView
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_datoParaValidar
	 *            correspondiente al valor del tipo de objeto String
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 */
	public static boolean validateStylesRepeatTab(
	    String as_idRepeat, String as_idComponente, FacesContext afc_pantallaActual, String as_idTabView,
	    String as_datoParaValidar, boolean ab_focus
	)
	{
		UIRepeat          luir_repeat;
		List<UIComponent> lluic_list;
		boolean           lb_return;

		luir_repeat     = (UIRepeat)afc_pantallaActual.getViewRoot().findComponent(as_idRepeat);
		lb_return       = false;

		if(luir_repeat != null)
		{
			lluic_list = luir_repeat.getChildren();

			TabView ltv_tab;

			ltv_tab = null;

			if(lluic_list != null)
			{
				for(UIComponent luic_iterator : lluic_list)
				{
					if((luic_iterator != null))
					{
						ltv_tab = (TabView)luic_iterator.findComponent(as_idTabView);

						if(ltv_tab != null)
							break;
					}
				}
			}

			if(ltv_tab != null)
			{
				UIComponent luic_uic;

				luic_uic = ltv_tab.findComponent(as_idComponente);

				if(luic_uic != null)
				{
					String ls_style;

					if(luic_uic instanceof InputText)
					{
						InputText lit_it;

						lit_it = (InputText)luic_uic;

						if(lit_it != null)
						{
							ls_style     = lit_it.getStyle();

							ls_style     = changeStyleComponent(
								    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
								);
							ab_focus     = ls_style.contains("border-color:null");

							lit_it.setStyle(ls_style);
						}
					}
					else if(luic_uic instanceof SelectOneMenu)
					{
						SelectOneMenu lit_it;

						lit_it = (SelectOneMenu)luic_uic;

						if(lit_it != null)
						{
							ls_style     = lit_it.getStyle();

							ls_style     = changeStyleComponent(
								    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
								);
							ab_focus     = ls_style.contains("border-color:null");

							lit_it.setStyle(ls_style);
						}
					}
					else if(luic_uic instanceof InputNumber)
					{
						InputNumber lin_in;

						lin_in = (InputNumber)luic_uic;

						if(lin_in != null)
						{
							ls_style     = lin_in.getStyle();

							ls_style     = changeStyleComponent(
								    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
								);
							ab_focus     = ls_style.contains("border-color:null");

							lin_in.setInputStyle(ls_style);
						}
					}
					else if(luic_uic instanceof Calendar)
					{
						Calendar lin_in;

						lin_in = (Calendar)luic_uic;

						if(lin_in != null)
						{
							ls_style     = lin_in.getInputStyle();

							ls_style     = changeStyleComponent(
								    as_idComponente, as_datoParaValidar, ls_style, ab_focus, false
								);
							ab_focus     = ls_style.contains("border-color:null");

							lin_in.setInputStyle(ls_style);
						}
					}
				}
			}
		}

		return lb_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param as_idComponente
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_datoParaValidar
	 *            correspondiente al valor del tipo de objeto String
	 * @param ls_style
	 *            correspondiente al valor del tipo de objeto String
	 * @param ab_focus
	 *            correspondiente al valor del tipo de objeto boolean
	 * @param ab_outputLabel
	 *            correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 */
	@SuppressWarnings("deprecation")
	private static String changeStyleComponent(
	    String as_idComponente, String as_datoParaValidar, String ls_style, boolean ab_focus, boolean ab_outputLabel
	)
	{
		if(!StringUtils.isValidString(as_datoParaValidar))
		{
			if(StringUtils.isValidString(ls_style))
			{
				if(ls_style.contains(ab_outputLabel ? "color:" : "border-color:"))
					ls_style = ls_style.replace(ab_outputLabel ? ":black;" : ":null;", ":red;");
				else
					ls_style = ls_style + ((!ls_style.contains(";")) ? ";" : "")
						+ (ab_outputLabel ? "color:red;" : "border-color:red;");
			}
			else
				ls_style = ab_outputLabel ? "color:red;" : "border-color:red;";

			if(ab_focus)
			{
				if(StringUtils.isValidString(as_idComponente) && as_idComponente.startsWith(":"))
				{
					StringBuilder lsb_sb;

					lsb_sb = new StringBuilder(as_idComponente);

					lsb_sb.deleteCharAt(0);

					as_idComponente = lsb_sb.toString();
				}

				RequestContext.getCurrentInstance().execute("PrimeFaces.focus('" + as_idComponente + "');");
			}
		}
		else
		{
			if(StringUtils.isValidString(ls_style))
			{
				if(ls_style.contains(ab_outputLabel ? "color:" : "border-color:"))
					ls_style = ls_style.replace(":red;", ab_outputLabel ? ":black;" : ":null;");
				else
					ls_style = ls_style + ((!ls_style.contains(";")) ? ";" : "")
						+ (ab_outputLabel ? "color:black;" : "border-color:null;");
			}
			else
				ls_style = ab_outputLabel ? "color:black;" : "border-color:null;";
		}

		return ls_style;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_idiomaEspañol
	 */
	public boolean isIdiomaEspanol()
	{
		return ib_idiomaEspanol;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_idiomaEspañol por ib_idiomaEspañol
	 */
	public void setIdiomaEspanol(boolean ib_idiomaEspanol)
	{
		this.ib_idiomaEspanol = ib_idiomaEspanol;
	}

	/**
	 * Método para el cambio de idioma en bachué
	 * @throws IOException
	 */
	public void cambiarIdiomaConciliaciones()
	    throws IOException
	{
		FacesContext lfc_facesContext;
		lfc_facesContext = FacesContext.getCurrentInstance();
		setIdiomaEspanol(!isIdiomaEspanol());

		if(isIdiomaEspanol())
		{
			FacesUtils.getSession().setAttribute("locale", new Locale("ES"));

			if(im_messages != null)
				im_messages.setBundleName(CONFIG_MESSAGES);
			else
				im_messages = new Messages(CONFIG_MESSAGES);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS);

			B2BException.setBundle(CONFIG_ERRORS);
		}
		else
		{
			if(im_messages != null)
				im_messages.setBundleName(CONFIG_MESSAGES_EN);
			else
				im_messages = new Messages(CONFIG_MESSAGES_EN);

			B2BException.setBundle(CONFIG_ERRORS_EN);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS_EN);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS_EN);

			FacesUtils.getSession().setAttribute("locale", Locale.ENGLISH);
		}

		lfc_facesContext.getExternalContext().redirect("principal.jsf");
	}

	/**
	 * Método de validación del idioma de bachué
	 * @throws IOException
	 */
	public void validarIdioma()
	    throws IOException
	{
		if(isIdiomaEspanol())
		{
			FacesUtils.getSession().setAttribute("locale", new Locale("ES"));

			if(im_messages != null)
				im_messages.setBundleName(CONFIG_MESSAGES);
			else
				im_messages = new Messages(CONFIG_MESSAGES);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS);

			B2BException.setBundle(CONFIG_ERRORS);
		}
		else
		{
			FacesUtils.getSession().setAttribute("locale", Locale.ENGLISH);

			if(im_messages != null)
				im_messages.setBundleName(CONFIG_MESSAGES_EN);
			else
				im_messages = new Messages(CONFIG_MESSAGES_EN);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS_EN);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS_EN);

			B2BException.setBundle(CONFIG_ERRORS_EN);
		}
	}
}
