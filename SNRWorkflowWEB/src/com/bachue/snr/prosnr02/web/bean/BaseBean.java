package com.bachue.snr.prosnr02.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.messages.Messages;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr02.web.util.FacesUtils;


/**
 * Clase que contiene todos las propiedades y acciones de BaseBean.
 *
 * @author garias
 */
public abstract class BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6786415929687070202L;

	/** Constante CONFIG_MESSAGES. */
	public static final String cs_CONFIG_MESSAGES = "com.bachue.snr.prosnr02.web.bean.resources.Messages";

	/** Constante CONFIG_ERRORS. */
	public static final String cs_CONFIG_ERRORS = "com.bachue.snr.prosnr02.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_EN. */
	public static final String CONFIG_ERRORS_EN = "com.bachue.snr.prosnr02.web.bean.resources.Errors_en";

	/** Constante CONFIG_MESSAGES_EN. */
	public static final String CONFIG_MESSAGES_EN = "com.bachue.snr.prosnr02.web.bean.resources.Messages_en";

	/** Constante CONFIG_MESSAGES_OPTIONS. */
	public static final String CONFIG_MESSAGES_OPTIONS = "com.bachue.snr.prosnr02.web.bean.resources.Options";

	/** Constante CONFIG_MESSAGES_OPTIONS_EN. */
	public static final String CONFIG_MESSAGES_OPTIONS_EN = "com.bachue.snr.prosnr02.web.bean.resources.Options_en";

	/** Propiedad im messages. */
	private static Messages im_messages;

	/** Propiedad im messages. */
	private static Messages im_messagesOpciones;

	/** Propiedad de opciones para el menu de bachue*/
	private Collection<Opcion> ico_opciones;

	/**Propiedad idioma español*/
	private boolean ib_idiomaEspanol;

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
	 * Retorna el valor de application.
	 *
	 * @return el valor de application
	 */
	protected abstract String getApplication();

	/**
	 * Retorna el valor de local ip address.
	 *
	 * @return el valor de local ip address
	 */
	protected String getLocalIpAddress()
	{
		HttpServletRequest lhsr_request;

		lhsr_request = FacesUtils.getRequest();

		return (lhsr_request != null) ? lhsr_request.getLocalAddr() : null;
	}

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	protected static Messages getMessages()
	{
		if(im_messages == null)
			im_messages = new Messages(cs_CONFIG_MESSAGES);

		return im_messages;
	}

	/**
	 * Retorna el valor de messages.
	 *
	 * @return el valor de messages
	 */
	protected static Messages getMessagesWorkflow()
	{
		if(im_messages == null)
			im_messages = new Messages(cs_CONFIG_MESSAGES);

		return im_messages;
	}

	/**
	 * Retorna el valor de user id.
	 *
	 * @return el valor de user id
	 */
	protected String getUserId()
	{
		return FacesUtils.getSessionAttributeAsString(IdentificadoresCommon.SESION_USUARIO);
	}

	/**
	 * Adds the message.
	 *
	 * @param ab2be_exception correspondiente al valor del tipo de objeto B2BException
	 */
	protected static void addMessage(B2BException ab2be_exception)
	{
		if(ab2be_exception != null)
			addMessage("W", ab2be_exception.getMessage());
	}

	/**
	 * Adds the message.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 */
	protected static void addMessageWorkflow(String as_mensaje)
	{
		if(StringUtils.isValidString(as_mensaje))
			addMessage("I", getMessages().getString(as_mensaje));
	}

	/**
	 * Adds the message.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 */
	protected static void addMessage(String as_mensaje)
	{
		if(StringUtils.isValidString(as_mensaje))
			addMessage("I", getMessages().getString(as_mensaje));
	}

	/**
	 * Adds the message.
	 *
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 * @param aoa_oa correspondiente al valor del tipo de objeto Object[]
	 */
	protected static void addMessage(String as_mensaje, Object[] aoa_oa)
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
	 * Adds the message.
	 *
	 * @param as_tipo correspondiente al valor del tipo de objeto String
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 */
	protected static void addMessage(String as_tipo, String as_mensaje)
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
	 * Adds the message info.
	 *
	 * @param as_tipo correspondiente al valor del tipo de objeto String
	 * @param as_mensaje correspondiente al valor del tipo de objeto String
	 */
	protected static void addMessageInfo(String as_tipo, String as_mensaje)
	{
		if(StringUtils.isValidString(as_tipo) && StringUtils.isValidString(as_mensaje))
			addMessage(as_tipo, getMessages().getString(as_mensaje));
	}

	/**
	 * Retorna el valor de remote ip address.
	 *
	 * @return el valor de remote ip address
	 */
	protected String getRemoteIpAddress()
	{
		HttpServletRequest lhsr_request;

		lhsr_request = FacesUtils.getRequest();

		return (lhsr_request != null) ? lhsr_request.getRemoteAddr() : null;
	}

	/**
	 * Actualizar componente.
	 *
	 * @param as_idComponente correspondiente al valor del tipo de objeto String
	 */
	protected void actualizarComponente(String as_idComponente)
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
	 * Método para el cambio de idioma en bachué
	 * @throws IOException
	 */
	public void cambiarIdiomaWorkflow()
	    throws IOException
	{
		FacesContext lfc_facesContext;
		lfc_facesContext = FacesContext.getCurrentInstance();
		setIdiomaEspanol(!isIdiomaEspanol());

		if(isIdiomaEspanol())
		{
			FacesUtils.getSession().setAttribute("locale", new Locale("ES"));

			if(im_messages != null)
				im_messages.setBundleName(cs_CONFIG_MESSAGES);
			else
				im_messages = new Messages(cs_CONFIG_MESSAGES);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS);

			B2BException.setBundle(cs_CONFIG_ERRORS);
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
				im_messages.setBundleName(cs_CONFIG_MESSAGES);
			else
				im_messages = new Messages(cs_CONFIG_MESSAGES);

			if(im_messagesOpciones != null)
				im_messagesOpciones.setBundleName(CONFIG_MESSAGES_OPTIONS);
			else
				im_messagesOpciones = new Messages(CONFIG_MESSAGES_OPTIONS);

			B2BException.setBundle(cs_CONFIG_ERRORS);
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
