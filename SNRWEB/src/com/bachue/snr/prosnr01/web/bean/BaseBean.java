package com.bachue.snr.prosnr01.web.bean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CampoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;

import com.bachue.snr.prosnr01.web.bean.consulta.SGD.BeanConsultaSGD;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;
import com.bachue.snr.prosnr01.web.util.PredioActoIU;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import org.apache.commons.lang.time.DateUtils;
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

import org.primefaces.component.wizard.Wizard;

import org.primefaces.context.RequestContext;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.model.chart.PieChartModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
	public static final String CONFIG_MESSAGES = "com.bachue.snr.prosnr01.web.bean.resources.Messages";
	

	/** Constante CONFIG_ERRORS. */
	public static final String CONFIG_ERRORS = "com.bachue.snr.prosnr01.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_EN. */
	public static final String CONFIG_ERRORS_EN = "com.bachue.snr.prosnr01.web.bean.resources.Errors_en";

	/** Constante CONFIG_MESSAGES_EN. */
	public static final String CONFIG_MESSAGES_EN = "com.bachue.snr.prosnr01.web.bean.resources.Messages_en";

	/** Constante CONFIG_MESSAGES_OPTIONS. */
	public static final String CONFIG_MESSAGES_OPTIONS = "com.bachue.snr.prosnr01.web.bean.resources.Options";

	/** Constante CONFIG_MESSAGES_OPTIONS_EN. */
	public static final String CONFIG_MESSAGES_OPTIONS_EN = "com.bachue.snr.prosnr01.web.bean.resources.Options_en";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BaseBean.class);

	/** Propiedad im messages. */
	private static Messages im_messages;

	/** Propiedad im error messages. */
	private static Messages im_errorMessages;
	
	/** Propiedad im messages. */
	private static Messages im_messagesOpciones;

	/** Propiedad icr calificacion remote. */
	@EJB
	protected CalificacionRemote icr_calificacionRemote;

	/**  Atributo laar_actuacionesAdministrativasRemote. */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad de opciones para el menu de bachue*/
	private Collection<Opcion> ico_opciones;
	
	/** Propiedad ii tamanio archivo 5 MB. */
	private final int ii_tamanioArchivo5MB = 5_000_000;

	/** Propiedad ii turnos asg. */
	private Integer ii_turnosAsg;

	/** Propiedad ii turnos bloq. */
	private Integer ii_turnosBloq;

	/** Propiedad ii turnos ter. */
	private Integer ii_turnosTer;

	/** Propiedad imsm datos parametricos direccion. */
	private Map<String, Map<String, String>> amsmss_datosParametricosDireccion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipcm pie chart model. */
	private PieChartModel ipcm_pieChartModel;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isr suspension remote. */
	@EJB
	private SuspensionRemote isr_suspensionRemote;
	
	/** Propiedad icdo resultados consulta. */
	private Collection<DocumentoOWCC> icdo_resultadosConsulta;

	/**Propiedad idioma español*/
	private boolean ib_idiomaEspanol;

	/** Propiedad ib mostrar grafica. */
	private boolean ib_mostrarGrafica;

	/**
	 * Retorna el valor de application.
	 *
	 * @return el valor de application
	 */
	public abstract String getApplication();

	/**
	 * @return Retorna el valor de la propiedad ico_opciones
	 */
	public Collection<Opcion> getOpciones()
	{
		return ico_opciones;
	}

	/**
	 * @param Modifica el valor de la propiedad ico_opciones por ac_c
	 */
	public void setOpciones(Collection<Opcion> ac_c)
	{
		ico_opciones = ac_c;
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
	 * Método encargado de obtener la instancia de BeanDireccion.
	 *
	 * @return instancia de BeanDireccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public BeanDireccion getBeanDireccion()
	    throws B2BException
	{
		return (BeanDireccion)obtenerInstanciaBean(BeanDireccion.class, BeanNameCommon.BEAN_DIRECCION);
	}
	
	/**
	 * Método encargado de obtener la instancia de BeanReference.
	 *
	 * @return instancia de BeanReference
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public BeanReference getBeanReference()
	    throws B2BException
	{
		return (BeanReference)obtenerInstanciaBean(BeanReference.class, BeanNameCommon.BEAN_REFERENCE);
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param amsmss_msmss de datosParametricos to set
	 */
	public void setDatosParametricosDireccion(Map<String, Map<String, String>> amsmss_msmss)
	{
		amsmss_datosParametricosDireccion = amsmss_msmss;
	}

	/**
	 * Retorna Objeto o variable de valor datos parametricos direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Map<String, Map<String, String>> getDatosParametricosDireccion()
	{
		return amsmss_datosParametricosDireccion;
	}

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
	 * Retorna el valor de error messages.
	 *
	 * @return el valor de error messages
	 */
	public static Messages getErrorMessages()
	{
		if(im_errorMessages == null)
			im_errorMessages = new Messages(CONFIG_ERRORS);

		return im_errorMessages;
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
	 * Modifica el valor de modelo torta.
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
	 * Modifica el valor de turnos asg.
	 *
	 * @param ai_i asigna el valor a la propiedad turnos asg
	 */
	public void setTurnosAsg(Integer ai_i)
	{
		ii_turnosAsg = ai_i;
	}

	/**
	 * Retorna el valor de turnos asg.
	 *
	 * @return el valor de turnos asg
	 */
	public Integer getTurnosAsg()
	{
		return ii_turnosAsg;
	}

	/**
	 * Modifica el valor de turnos bloq.
	 *
	 * @param ai_i asigna el valor a la propiedad turnos bloq
	 */
	public void setTurnosBloq(Integer ai_i)
	{
		ii_turnosBloq = ai_i;
	}

	/**
	 * Retorna el valor de turnos bloq.
	 *
	 * @return el valor de turnos bloq
	 */
	public Integer getTurnosBloq()
	{
		return ii_turnosBloq;
	}

	/**
	 * Modifica el valor de turnos ter.
	 *
	 * @param ai_i asigna el valor a la propiedad turnos ter
	 */
	public void setTurnosTer(Integer ai_i)
	{
		ii_turnosTer = ai_i;
	}

	/**
	 * Retorna el valor de turnos ter.
	 *
	 * @return el valor de turnos ter
	 */
	public Integer getTurnosTer()
	{
		return ii_turnosTer;
	}
	
	/**
	 * Modifica el valor de resultados consulta.
	 *
	 * @param acdo_cdo asigna el valor a la propiedad resultados consulta
	 */
	public void setResultadosConsulta(Collection<DocumentoOWCC> acdo_cdo)
	{
		icdo_resultadosConsulta = acdo_cdo;
	}

	/**
	 * Retorna el valor de resultados consulta.
	 *
	 * @return el valor de resultados consulta
	 */
	public Collection<DocumentoOWCC> getResultadosConsulta()
	{
		return icdo_resultadosConsulta;
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
	 * Retorna Objeto o variable de valor usuario logueado.
	 *
	 * @return el valor de usuario logueado
	 */
	public Usuario getUsuarioLogueado()
	{
		Usuario lu_usuario;

		lu_usuario = null;

		try
		{
			String ls_idUsuario;

			ls_idUsuario = getUserId();

			if(StringUtils.isValidString(ls_idUsuario))
				lu_usuario = irr_referenceRemote.findUserById(new Usuario(ls_idUsuario));
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("getUsuarioLogueado", le_e);
		}

		return lu_usuario;
	}

	/**
	 * Adds de message.
	 *
	 * @param as_mensaje Argumento de tipo <code>String</code> que contiene el mensaje.
	 * @param aoa_oa Argumento de tipo <code>List</code> de Object  que contiene la información del mensaje.
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
	 * Adds the message.
	 *
	 * @param as_tipo Argumento de tipo <code>String</code> que contiene el tipo de mensaje.
	 * @param as_mensaje Argumento de tipo <code>String</code> que contiene el mensaje.
	 * @param aoa_oa Argumento de tipo <code>List</code> de Object  que contiene la información del mensaje.
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
		{
			addMessage("W", ab2be_exception.getMessage());
		}
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
	 * Metodo encargado de consultar la constante para el tamaño limite de los archivos.
	 *
	 * @return devuelve el valor de int
	 */
	public int cargarConstanteTamanoLimiteArchivo()
	{
		int li_TamanoLimiteArchivo;

		li_TamanoLimiteArchivo = 0;

		try
		{
			String     ls_caracter;
			Constantes lc_constante;

			ls_caracter      = ConstanteCommon.TAMANO_LIMITE_ARCHIVO;
			lc_constante     = ipr_parameterRemote.findConstantById(new Constantes(ls_caracter));

			if(lc_constante != null)
			{
				String ls_caracterExtraer;

				ls_caracterExtraer = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracterExtraer))
					li_TamanoLimiteArchivo = NumericUtils.getInt(ls_caracterExtraer);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return li_TamanoLimiteArchivo;
	}

	/**
	 * Cargar datos parametricos direccion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosParametricosDireccion()
	    throws B2BException
	{
		setDatosParametricosDireccion(irr_referenceRemote.cargarDataDireccion());
	}
	
	/**
	 * Procesa el cargue de archivos pdf para el soporte de rechazar una solicitud.
	 *
	 * @param afue_event Objeto contenedor del arreglo de bytes correspondiente al archivo cargado
	 * @param as_messageIdGrowl Argumento de tipo <code>String</code> que contiene el id del formulario(idForm:idGrowl) donde se van a cargar los documentos
	 * @param acsd_documentosCargados Argumento de tipo <code>Collection</code> de tipo StreamedContent que contiene la colección donde se guardan los documentos generados
	 * @param amsb_archivosCargados de Argumento de tipo <code>Map</code> que contiene el mapa donde se guarda la información de los archivos guardados
	 */
	public void cargarDocumentoPDF(FileUploadEvent afue_event, String as_messageIdGrowl,Collection<StreamedContent> acsd_documentosCargados,Map<String, byte[]> amsb_archivosCargados)
	{

		try
		{
			String       ls_mensaje;
			UploadedFile luf_file;

			ls_mensaje     = null;
			luf_file       = afue_event.getFile();

			if(acsd_documentosCargados != null && amsb_archivosCargados != null)
			{
				if((luf_file != null) && StringUtils.isValidString(as_messageIdGrowl))
				{
					if(luf_file.getSize() <= ii_tamanioArchivo5MB)
					{
						byte[] lba_pdfFile;
						
						lba_pdfFile = luf_file.getContents();
						
						if(
								(lba_pdfFile != null) && StringUtils.isValidString(luf_file.getFileName())
								&& luf_file.getFileName().toUpperCase().endsWith(ExtensionCommon.PDF_MAYUS_PUNTO)
								)
						{
							boolean lb_isEncrypted;
							String  ls_nombreArchivo;
							
							lb_isEncrypted       = false;
							ls_nombreArchivo     = luf_file.getFileName();
							
							if(lba_pdfFile.length < 1)
								throw new B2BException(ErrorKeys.ARCHIVO_DANADO);
							
							{
								String ls_pdfContent;
								int    li_lastTrailerIndex;
								
								ls_pdfContent           = new String(lba_pdfFile);
								li_lastTrailerIndex     = ls_pdfContent.lastIndexOf("trailer");
								
								if((li_lastTrailerIndex >= 0) && (li_lastTrailerIndex < ls_pdfContent.length()))
								{
									String ls_partEncrypted;
									int    ls_firstEOFIndex;
									String ls_trailer;
									
									ls_partEncrypted     = ls_pdfContent.substring(
											li_lastTrailerIndex, ls_pdfContent.length()
											);
									ls_firstEOFIndex     = ls_partEncrypted.indexOf("%%EOF");
									ls_trailer           = ls_partEncrypted.substring(0, ls_firstEOFIndex);
									
									if(ls_trailer.contains("/Encrypt"))
										lb_isEncrypted = true;
								}
							}
							
							if(lb_isEncrypted)
								throw new B2BException(ErrorKeys.ARCHIVO_PROTEGIDO);
							
							{
								Collection<StreamedContent> lcsc_documentosCargados;
								
								lcsc_documentosCargados = acsd_documentosCargados;
								
								if(lcsc_documentosCargados != null)
								{
									Optional<StreamedContent> losc_documento;
									
									//FORMATO Comentar lambda antes de formatear
									losc_documento = lcsc_documentosCargados.stream()
											.filter(lsc_documento -> StringUtils.getStringNotNull(lsc_documento.getName()).equals(ls_nombreArchivo))
											.findFirst();
									
									if(losc_documento.isPresent())
									{
										Object[] loa_args;
										
										loa_args     = new String[1];
										
										loa_args[0] = ls_nombreArchivo;
										
										throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_YA_CARGADO, loa_args);
									}
									
									{
										StreamedContent lsc_uploadedFile;
										
										InputStream     lis_stream = new ByteArrayInputStream(lba_pdfFile);
										
										lsc_uploadedFile = new DefaultStreamedContent(
												lis_stream, luf_file.getContentType(), ls_nombreArchivo
												);
										
										lcsc_documentosCargados.add(lsc_uploadedFile);
									}
								}
							}
							
							{
								Map<String, byte[]> lmsba_archivosCargados;
								
								lmsba_archivosCargados = amsb_archivosCargados;
								
								if(lmsba_archivosCargados != null)
									lmsba_archivosCargados.put(ls_nombreArchivo, lba_pdfFile);
								
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TAMANIO_ARCHIVO_PDF);
				}
				else
					ls_mensaje = "No se encontro un Archivo para procesar.";
				
			}

			if(!StringUtils.isValidString(ls_mensaje))
				ls_mensaje = "Procesamiento de archivo terminado.";

			addMessage("I", ls_mensaje);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
			
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("cargarDocumentoPDF", lb2be_e);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
		}
		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());
			clh_LOGGER.error("cargarDocumentoPDF", lb2be_e);
			PrimeFaces.current().ajax().update(as_messageIdGrowl);
		}
		
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
	 * Eliminar documento PDF cargado.
	 *
	 * @param asc_documento Argumento de tipo <code>StreamedContent</code>
	 * @param acsd_documentosCargados Argumento de tipo <code>Collection</code> de StreamedContent que contiene la colección donde se guardan los documentos generados
	 * @param amsb_archivosCargados de Argumento de tipo <code>Map</code> que contiene el mapa donde se guarda la información de los archivos guardados
	 * @return el valor variable de tipo <code>Collection</code> de StreamedContent que contiene la nueva colección de documentos guardados.
	 */
	public Collection<StreamedContent> eliminarDocumentoPDFCargado(StreamedContent asc_documento,Collection<StreamedContent> acsd_documentosCargados,Map<String, byte[]> amsb_archivosCargados)
	{
		Collection<StreamedContent> lcsc_return;
		
		lcsc_return = null;
		try
		{	
			if(asc_documento == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_DOCUMENTO_PARA_ELIMINAR);
			
			if(CollectionUtils.isValidCollection(acsd_documentosCargados) && amsb_archivosCargados != null)
			{
				String                      ls_nombre;
				Collection<StreamedContent> lcsc_documentosTmp;
				
				ls_nombre              = StringUtils.getStringNotNull(asc_documento.getName());
				lcsc_documentosTmp     = new LinkedList<StreamedContent>();
				
				{
					Collection<StreamedContent> lcsc_documentosCargados;
					
					lcsc_documentosCargados = acsd_documentosCargados;
					
					//FORMATO Comentar lambda antes de formatear
					lcsc_documentosCargados.stream()
					.filter(lsc_documento -> !StringUtils.getStringNotNull(lsc_documento.getName()).equals(ls_nombre))
					.forEach(lsc_documento -> lcsc_documentosTmp.add(lsc_documento));
				}
				
				{
					Map<String, byte[]> lmsba_archivosCargados;
					
					lmsba_archivosCargados = amsb_archivosCargados;
					
					if(CollectionUtils.isValidCollection(lmsba_archivosCargados))
						lmsba_archivosCargados.remove(ls_nombre);
				}
				
				lcsc_return = lcsc_documentosTmp;
				
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("eliminarDocumentoPDFCargado", lb2be_e);
		}
		
		return lcsc_return;
	}

	/**
	 * Método encargado de validar una colección el cual se usa para las pantallas.
	 *
	 * @param acu_collection            <code>Collection</code> la cual se validará
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
	 * Accion SGD.
	 *
	 * @param ado_documento de ado documento
	 * @param as_paginaRetorno de as pagina retorno
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionSGD(DocumentoOWCC ado_documento, String as_paginaRetorno)
	    throws IOException, B2BException
	{
		accionSGD(ado_documento, as_paginaRetorno, false);
	}

	/**
	 * Accion SGD.
	 *
	 * @param ado_documento de ado documento
	 * @param as_paginaRetorno de as pagina retorno
	 * @param ab_newTab de ab new tab
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionSGD(DocumentoOWCC ado_documento, String as_paginaRetorno, boolean ab_newTab)
	    throws IOException, B2BException
	{
		try
		{
			FacesContext    lfc_context;
			BeanConsultaSGD lb_beanConsultaSGD;
			String          ls_url;

			lfc_context            = FacesUtils.getFacesContext();
			lb_beanConsultaSGD     = (BeanConsultaSGD)lfc_context.getApplication()
					                                                 .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CONSULTA_SGD, BeanConsultaSGD.class
					);
			ls_url                 = null;

			if(lb_beanConsultaSGD != null)
			{
				ls_url = lb_beanConsultaSGD.consultaSGD(ado_documento);
				lb_beanConsultaSGD.setPantallaAnterior(as_paginaRetorno);
				lb_beanConsultaSGD.setNewTab(true);
			}

			if(StringUtils.isValidString(ls_url))
			{
				if(ab_newTab)
					PrimeFaces.current().executeScript("window.open('consultaSGD.jsf', '_newtab')");
				else
					FacesContext.getCurrentInstance().getExternalContext().redirect("consultaSGD.jsf");
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
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
	 * Actualizar wizard.
	 *
	 * @param as_tab correspondiente al valor del tipo de objeto String
	 * @param as_wizard de id wizard
	 * @param as_form de id formulario
	 */
	public void actualizarWizard(String as_tab, String as_wizard, String as_form)
	{
		Wizard wizard = (Wizard)FacesContext.getCurrentInstance().getViewRoot().findComponent(as_wizard);

		wizard.setStep(as_tab);

		PrimeFaces.current().ajax().update(as_form);
	}

	/**
	 * Metodo encargado de llenar los datos de los campos multivalor dinámicos.
	 *
	 * @param acc_panel Argumento de tipo CamposConsulta que contiene los criterios para realizar la búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambiarDatosMultivaloresCampoCriterio(CamposConsulta acc_panel)
	    throws B2BException
	{
		if(acc_panel != null)
		{
			try
			{
				Collection<CamposConsulta> lccc_datosCamposConsulta;

				lccc_datosCamposConsulta = acc_panel.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_datosCamposConsulta))
				{
					String ls_idTipoDocumento;
					String ls_idTipoOficina;
					String ls_idPais;
					String ls_idDepartamento;
					String ls_idMunicipio;

					ls_idTipoDocumento     = null;
					ls_idTipoOficina       = null;
					ls_idPais              = null;
					ls_idDepartamento      = null;
					ls_idMunicipio         = null;

					for(CamposConsulta lcc_campo : lccc_datosCamposConsulta)
					{
						if(lcc_campo != null)
						{
							String ls_idTipoCriterioBusqueda;

							ls_idTipoCriterioBusqueda = lcc_campo.getIdTipoCriterioBusqueda();

							if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
							{
								boolean lb_documentoCopias;

								lb_documentoCopias = ls_idTipoCriterioBusqueda.equalsIgnoreCase(
									    TipoCriterioBusquedaCommon.DOCUMENTO_COPIAS
									)
										|| ls_idTipoCriterioBusqueda.equalsIgnoreCase(
										    TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
										);

								if(
								    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DOCUMENTO)
									    || lb_documentoCopias
								)
								{
									String ls_idCampoCriterioBusqueda;

									ls_idCampoCriterioBusqueda = lcc_campo.getIdCampoCriterioBusqueda();

									if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
									{
										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        lb_documentoCopias
											        ? CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DOCUMENTO
											            : CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_DOCUMENTO
											    )
										)
											ls_idTipoDocumento = lcc_campo.getValorCampo();

										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        lb_documentoCopias
											        ? CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_TIPO_DE_OFICINA
											            : CampoCriterioBusquedaCommon.DOCUMENTO_TIPO_DE_OFICINA
											    )
										)
										{
											Documento               ld_documento;
											Collection<TipoOficina> lcto_tiposOficina;

											ld_documento = new Documento();

											ld_documento.setIdTipoDocumento(ls_idTipoDocumento);

											lcto_tiposOficina = irr_referenceRemote.findTipoOficina(ld_documento);

											acc_panel.setTiposOficina(lcto_tiposOficina);

											ls_idTipoOficina = lcc_campo.getValorCampo();
										}
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        lb_documentoCopias
											        ? CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_PAIS
											            : CampoCriterioBusquedaCommon.DOCUMENTO_PAIS
											    )
										)
										{
											ls_idPais     = lcc_campo.getValorCampo();

											ls_idPais = StringUtils.isValidString(ls_idPais) ? ls_idPais
												                                             : IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

											{
												Departamento ld_departamento;

												ld_departamento = new Departamento();

												ld_departamento.setIdPais(ls_idPais);

												acc_panel.setDepartamentos(
												    irr_referenceRemote.findDepartaments(ld_departamento)
												);
											}

											lcc_campo.setValorCampo(ls_idPais);
										}
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        lb_documentoCopias
											        ? CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_DEPARTAMENTO
											            : CampoCriterioBusquedaCommon.DOCUMENTO_DEPARTAMENTO
											    )
										)
										{
											ls_idDepartamento = lcc_campo.getValorCampo();

											if(
											    StringUtils.isValidString(ls_idPais)
												    && StringUtils.isValidString(ls_idDepartamento)
											)
											{
												Municipio lm_municipio;

												lm_municipio = new Departamento();

												lm_municipio.setIdPais(ls_idPais);
												lm_municipio.setIdDepartamento(ls_idDepartamento);

												acc_panel.setMunicipios(
												    irr_referenceRemote.findMunicipios(lm_municipio)
												);
											}
										}
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        lb_documentoCopias
											        ? CampoCriterioBusquedaCommon.DOCUMENTO_COPIAS_MUNICIPIO
											            : CampoCriterioBusquedaCommon.DOCUMENTO_MUNICIPIO
											    )
										)
										{
											ls_idMunicipio = lcc_campo.getValorCampo();

											if(
											    StringUtils.isValidString(ls_idPais)
												    && StringUtils.isValidString(ls_idDepartamento)
												    && StringUtils.isValidString(ls_idMunicipio)
												    && StringUtils.isValidString(ls_idTipoOficina)
											)
											{
												OficinaOrigen loo_oficinaOrigen;

												loo_oficinaOrigen = new OficinaOrigen();

												loo_oficinaOrigen.setIdPais(ls_idPais);
												loo_oficinaOrigen.setIdDepartamento(ls_idDepartamento);
												loo_oficinaOrigen.setIdMunicipio(ls_idMunicipio);
												loo_oficinaOrigen.setIdTipoOficina(ls_idTipoOficina);

												acc_panel.setOficinasOrigen(
												    irr_referenceRemote.findOficinaOrigen(loo_oficinaOrigen)
												);
											}
										}
									}
								}
								else if(
								    ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DIRECCION)
								)
								{
									String ls_idCampoCriterioBusqueda;

									ls_idCampoCriterioBusqueda = lcc_campo.getIdCampoCriterioBusqueda();

									if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
									{
										if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_DEPARTAMENTO
											    )
										)
										{
											ls_idPais             = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;
											ls_idDepartamento     = lcc_campo.getValorCampo();

											if(
											    StringUtils.isValidString(ls_idPais)
												    && StringUtils.isValidString(ls_idDepartamento)
											)
											{
												Municipio lm_municipio;

												lm_municipio = new Departamento();

												lm_municipio.setIdPais(ls_idPais);
												lm_municipio.setIdDepartamento(ls_idDepartamento);

												acc_panel.setMunicipios(
												    irr_referenceRemote.findMunicipios(lm_municipio)
												);
											}
										}
										else if(
										    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
											        CampoCriterioBusquedaCommon.DIRECCION_MUNICIPIO
											    )
										)
										{
											ls_idMunicipio = lcc_campo.getValorCampo();

											if(
											    StringUtils.isValidString(ls_idPais)
												    && StringUtils.isValidString(ls_idDepartamento)
												    && StringUtils.isValidString(ls_idMunicipio)
											)
											{
												Vereda lv_vereda;

												lv_vereda = new Vereda();

												lv_vereda.setIdPais(ls_idPais);
												lv_vereda.setIdDepartamento(ls_idDepartamento);
												lv_vereda.setIdMunicipio(ls_idMunicipio);

												acc_panel.setVeredas(irr_referenceRemote.findVeredas(lv_vereda, false));
											}
										}
									}
								}
								else if(
								    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
									        TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
									    )
								)
								{
									String ls_idCampoCriterioBusqueda;

									ls_idCampoCriterioBusqueda = lcc_campo.getIdCampoCriterioBusqueda();

									if(
									    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
										        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_CIRCULO_REGISTRAL
										    )
									)
									{
										String ls_idCirculo;

										ls_idCirculo = lcc_campo.getValorCampo();

										if(StringUtils.isValidString(ls_idCirculo))
										{
											ZonaRegistral lzr_zonaRegistral;

											lzr_zonaRegistral = irr_registroRemote.findDatosZonaRegistralByCirculo(
												    ls_idCirculo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
												);

											if(lzr_zonaRegistral != null)
												ls_idDepartamento = lzr_zonaRegistral.getIdDepartamento();
										}
									}
									else if(
									    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
										        CampoCriterioBusquedaCommon.ANTIGUO_SISTEMA_MUNICIPIO
										    )
									)
									{
										ls_idPais = IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT;

										if(
										    StringUtils.isValidString(ls_idPais)
											    && StringUtils.isValidString(ls_idDepartamento)
										)
										{
											Municipio lm_municipio;

											lm_municipio = new Departamento();

											lm_municipio.setIdPais(ls_idPais);
											lm_municipio.setIdDepartamento(ls_idDepartamento);

											acc_panel.setMunicipios(irr_referenceRemote.findMunicipios(lm_municipio));
										}
										
										else 
										  acc_panel.setMunicipios(null);
									}
								}
							}
						}
					}

					cargarDireccionCompletaCriteriosBusqueda(acc_panel);
				}
			}
			catch(B2BException lb2be_e)
			{
				throw lb2be_e;
			}
		}
	}

	/**
	 * Metodo encargado de consultar la constante para el largo de la tabla de
	 * turnos.
	 *
	 * @return Variable de tipo <code>int</code> que contiene la cantidad de filas de la tabla.
	 */
	public int cargarConstanteTabla()
	{
		int li_cantidadFilas;

		li_cantidadFilas = 0;

		try
		{
			String     ls_caracter;
			Constantes lc_constante;

			ls_caracter      = ConstanteCommon.CANTIDAD_DE_REGISTROS_TABLA;
			lc_constante     = ipr_parameterRemote.findConstantById(new Constantes(ls_caracter));

			if(lc_constante != null)
			{
				String ls_caracterExtraer;

				ls_caracterExtraer = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracterExtraer))
					li_cantidadFilas = NumericUtils.getInt(ls_caracterExtraer);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return li_cantidadFilas;
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
	 * Cargar direccion completa.
	 *
	 * @param acc_panel correspondiente al valor del tipo de objeto CamposConsulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDireccionCompletaCriteriosBusqueda(CamposConsulta acc_panel)
	    throws B2BException
	{
		try
		{
			if(acc_panel != null)
			{
				Collection<CamposConsulta> lccc_dataCamposConsulta;

				lccc_dataCamposConsulta = acc_panel.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_dataCamposConsulta))
				{
					String ls_direccionCompleta;

					ls_direccionCompleta = irr_registroRemote.cargarDireccionCompleta(
						    acc_panel, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(StringUtils.isValidString(ls_direccionCompleta))
					{
						Iterator<CamposConsulta> licc_iterator;
						boolean                  lb_encontrado;

						licc_iterator     = lccc_dataCamposConsulta.iterator();
						lb_encontrado     = false;

						while(licc_iterator.hasNext() && !lb_encontrado)
						{
							CamposConsulta lcc_iterador;

							lcc_iterador = licc_iterator.next();

							if(lcc_iterador != null)
							{
								String ls_idTipoCriterioBusqueda;

								ls_idTipoCriterioBusqueda = lcc_iterador.getIdTipoCriterioBusqueda();

								if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
								{
									if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.DIRECCION))
									{
										String ls_idCampoCriterioBusqueda;

										ls_idCampoCriterioBusqueda = lcc_iterador.getIdCampoCriterioBusqueda();

										if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
										{
											if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DIRECCION_DIRECCION_COMPLETA
												    )
											)
												lcc_iterador.setValorCampo(ls_direccionCompleta);
											else if(
											    ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        CampoCriterioBusquedaCommon.DIRECCION_TIPO_DE_PREDIO
												    )
											)
												acc_panel.setIdTipoPredio(lcc_iterador.getValorCampo());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de consultar los tipo expediente para la etapa definida.
	 *
	 * @param al_idEtapa Contiene la información del id de la etapa
	 * @return devuelve el valor de la colección de LineaProduccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<LineaProduccion> cargarTipoExpediente(long al_idEtapa)
	    throws B2BException
	{
		Collection<LineaProduccion> lclp_lineasProduccion;

		lclp_lineasProduccion = new LinkedList<LineaProduccion>();

		try
		{
			lclp_lineasProduccion = irr_referenceRemote.findLineasProduccionByEtapa(
				    (al_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
				    ? EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS
				    : ((al_idEtapa == EtapaCommon.ID_ETAPA_460) ? EtapaCommon.ID_ETAPA_430
				                                                : EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS),
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}

		return lclp_lineasProduccion;
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
				String ls_espacio;

				ls_espacio = ";&nbsp;";

				if(as_texto.contains(ls_espacio))
					as_texto = as_texto.replace(ls_espacio, " ");
			}

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
	 * Método encargado de generar <code>DefaultStramedContent</code> cargado con la
	 * imagen de la constante ingresada.
	 *
	 * @param as_constante
	 *            <code>String</code> con la cual se realizará la búsqueda en la BD.
	 * @return <code>DefaultStreamedContent</code>
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 */
	public DefaultStreamedContent descargaPlantillaXLSX(String as_constante)
	    throws B2BException
	{
		DefaultStreamedContent ldsc_streamedContent;

		ldsc_streamedContent = null;

		if(StringUtils.isValidString(as_constante))
		{
			Constantes lc_constante;

			lc_constante = new Constantes();

			lc_constante.setIdConstante(as_constante);

			lc_constante = ipr_parameterRemote.findImageById(lc_constante);

			if(lc_constante != null)
				ldsc_streamedContent = generarArchivosDescarga(
					    lc_constante.getImagenes(), TipoContenidoCommon.XLS_XLSX_XXLS,
					    ConstanteCommon.NOMBRE_ARCHIVO_GENERADO_TXT + IdentificadoresCommon.SIMBOLO_GUION_BAJO
					    + as_constante + lc_constante.getTipoArchivo()
					);
		}

		return ldsc_streamedContent;
	}

	/**
	 * Expande el toggeable en caso de tirar una excepcion.
	 *
	 * @param as_idPanel String de idPanel para expandirlo
	 */
	public void expandirToggeable(String as_idPanel)
	{
		if(StringUtils.isValidString(as_idPanel))
		{
			PrimeFaces    lpf_current;
			StringBuilder lsb_stringBuilder;

			lpf_current           = PrimeFaces.current();
			lsb_stringBuilder     = new StringBuilder();

			lsb_stringBuilder.append("PF('");
			lsb_stringBuilder.append(as_idPanel);
			lsb_stringBuilder.append("').expand();");

			lpf_current.executeScript(lsb_stringBuilder.toString());
		}
	}

	/**
	 * Metodo para encontrar la constante de recepción de documentos.
	 *
	 * @return el valor de collection
	 * @throws B2BException
	 *             Objeto de tipo B2BException, se produce cuando se encuentra algun
	 *             error controlado.
	 */
	public Collection<String> findAllRecepcionDocumento()
	    throws B2BException
	{
		Collection<String> lcs_listado;
		Constantes         lcc_constante;

		lcs_listado       = null;
		lcc_constante     = irr_referenceRemote.findConstantesById(ConstanteCommon.MEDIO_RECEPCION_DOCUMENTOS);

		if(lcc_constante != null)
			lcs_listado = ListadoCodigosConstantes.generarCodigosCollection(lcc_constante.getCaracter());

		return lcs_listado;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Municipio> findMunicipiosByCirculo(String as_idCirculo)
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			try
			{
				lcm_municipios = irr_referenceRemote.findMunicipiosByCirculo(as_idCirculo);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);

				lcm_municipios = null;
			}
		}

		return lcm_municipios;
	}

	/**
	 * Generar grafico de torta.
	 *
	 * @param al_idEtapa de al id etapa
	 * @param ab_allUsuarios de ab all usuarios
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarGraficoDeTorta(long al_idEtapa, boolean ab_allUsuarios)
	    throws B2BException
	{
		Integer li_turnosAsg;
		Integer li_turnosTer;
		Integer li_turnosBloq;
		int     li_turnosTotal;
		String  ls_idUsuario;

		li_turnosTotal     = 0;
		ls_idUsuario       = getUserId();

		li_turnosAsg      = icr_calificacionRemote.findTurnosCantidad(
			    EstadoCommon.ASIGNADA, ls_idUsuario, al_idEtapa, ab_allUsuarios
			);
		li_turnosTer      = icr_calificacionRemote.findTurnosCantidad(
			    EstadoCommon.TERMINADA, ls_idUsuario, al_idEtapa, ab_allUsuarios
			);
		li_turnosBloq     = icr_calificacionRemote.findTurnosCantidad(
			    EstadoCommon.BLOQUEADO, ls_idUsuario, al_idEtapa, ab_allUsuarios
			);

		if(NumericUtils.isValidInteger(li_turnosAsg) && NumericUtils.isValidInteger(li_turnosTer))
		{
			setTurnosAsg(li_turnosAsg);
			setTurnosTer(li_turnosTer);
			setTurnosBloq(li_turnosBloq);
			li_turnosTotal = NumericUtils.getInt(li_turnosAsg) + NumericUtils.getInt(li_turnosTer)
				+ NumericUtils.getInt(li_turnosBloq);
			setMostrarGrafica(true);
		}

		ipcm_pieChartModel = new PieChartModel();

		ipcm_pieChartModel.set(EstadoCommon.ASIGNADA, li_turnosAsg);
		ipcm_pieChartModel.set(EstadoCommon.TERMINADA, li_turnosTer);
		ipcm_pieChartModel.set(EstadoCommon.BLOQUEADO, li_turnosBloq);
		ipcm_pieChartModel.setSeriesColors(ConstanteCommon.CODIGOS_COLORES_GRAFICAS);
		ipcm_pieChartModel.setTitle("Turnos del día: " + li_turnosTotal);
		ipcm_pieChartModel.setLegendPosition("w");

		setModeloTorta(ipcm_pieChartModel);
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
	 * Item select.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSelect(ItemSelectEvent event)
	{
		String ls_mensaje;
		ls_mensaje = null;

		switch(event.getItemIndex())
		{
			case 0:
				ls_mensaje = "Hay " + getTurnosAsg() + " turno(s) asignado(s).";

				break;

			case 1:
				ls_mensaje = "Se ha(n) terminado " + getTurnosTer() + " turno(s).";

				break;

			case 2:
				ls_mensaje = "Hay " + getTurnosBloq() + " turno(s) bloqueado(s).";

				break;

			default:
				break;
		}

		FacesMessage msg = new FacesMessage(
			    FacesMessage.SEVERITY_INFO, "Cantidad de turnos seleccionados:", ls_mensaje
			);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param ls_idTurnoHistoria de ls id turno historia
	 * @return devuelve el valor de String
	 */
	public String observacionesProcesoAnterior(String ls_idTurnoHistoria)
	{
		TurnoHistoria lth_turnoHistoria;
		String        ls_observaciones;

		lth_turnoHistoria     = new TurnoHistoria();
		ls_observaciones      = new String();

		try
		{
			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));
			lth_turnoHistoria = icr_calificacionRemote.findTurnoHistoriaAnteriorById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
				ls_observaciones = lth_turnoHistoria.getObservacionesProcesoAnterior();
		}
		catch(B2BException ab2be_exception)
		{
			addMessage(ab2be_exception);
			PrimeFaces.current().ajax().update("confrontacion:idGrowl");
		}

		return ls_observaciones;
	}

	/**
	 * Método de obtención del turnoHistoria anterior.
	 *
	 * @param as_aTurno con el turno actual de la solicitud
	 * @return Un TurnoHistoria con la solicitud del dato
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria obtenerAnteriorTurnoHistoria(String as_aTurno)
	    throws B2BException
	{
		TurnoHistoria lh_turnoHistoria;
		lh_turnoHistoria = new TurnoHistoria();

		try
		{
			lh_turnoHistoria.setIdTurno(as_aTurno);

			lh_turnoHistoria = irr_referenceRemote.obtenerAnteriorTurnoHistoria(lh_turnoHistoria);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lh_turnoHistoria;
	}

	/**
	 * Método para obtener la instancia de un bean.
	 *
	 * @param acu_bean            <code>Class ?</code> del bean que se quiere obtener.
	 * @param as_beanNameCommon            <code>String</code> que contiene el nombre del bean.
	 * @return <code>Object</code> que contiene la instancia del bean obtenido.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
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
	 * Segunda verificacion.
	 */
	public void segundaVerificacion()
	{
		HttpSession lhs_httpSession;

		lhs_httpSession = FacesUtils.getSession();

		try
		{
			String ls_idSession;

			ls_idSession = null;

			{
				Map<String, String> lmss_params;

				lmss_params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

				if(CollectionUtils.isValidCollection(lmss_params))
					ls_idSession = lmss_params.get("generarId");
			}

			{
				Usuario lu_usuarioLogueado;

				lu_usuarioLogueado = getUsuarioLogueado();

				if((lu_usuarioLogueado != null) && (lhs_httpSession != null))
				{
					StringBuilder lsb_builder;

					lsb_builder = new StringBuilder(IdentificadoresCommon.PROTOCOLO_BACHUE);

					lsb_builder.append(EventoCommon.VERIFY);
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(ls_idSession);
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(lu_usuarioLogueado.getIdUsuario());
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(
					    StringUtils.getStringNotNull(lu_usuarioLogueado.getSegundoFactorAutenticacion())
						               .equalsIgnoreCase("HUELLA") ? "1" : "0"
					);

					PrimeFaces.current().executeScript("abrirURLBioClient('" + lsb_builder.toString() + "');");
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error(le_e);
		}
	}

	/**
	 * Método encargado de validar la cantidad de intervinientes agregados para paginarlos por la constante de cantidad.
	 *
	 * @param lca_param de lca param
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	public boolean validarCantidadIntervinientesAgregados(Collection<Anotacion> lca_param)
	{
		return (lca_param != null) ? (lca_param.size() > cargarConstanteTabla()) : false;
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
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de id turno historia
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarEnvioDocumentosOWCC(String as_idTurnoHistoria)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			lb_return = iaar_actuacionesAdministrativasRemote.documentosEnviadosOWCC(
				    new TagPlantillaResolucion(as_idTurnoHistoria), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC.
	 *
	 * @param as_idSolicitud correspondiente al valor de id turno historia
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarEnvioDocumentosOWCCByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			lb_return = iaar_actuacionesAdministrativasRemote.documentosEnviadosOWCCBySolicitud(
				    as_idSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarEnvioDocumentosOWCCByIdSolicitud", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}

	/**
	 * Método para cargar mensaje si el predio es inconsistente.
	 *
	 * @param acpaui_predioActo objeto contenedor de matriculas
	 * @param as_matriculas string de mensaje final
	 * @param ab_bool de ab bool
	 * @return el valor de string
	 */
	protected String cargarMensajePredioInconsistentePredio(
	    Collection<PredioActoIU> acpaui_predioActo, String as_matriculas, boolean ab_bool
	)
	{
		if(CollectionUtils.isValidCollection(acpaui_predioActo))
		{
			for(PredioActoIU lpaiu_predioActoIU : acpaui_predioActo)
			{
				if(lpaiu_predioActoIU != null)
				{
					if(lpaiu_predioActoIU.isEsPredioInconsistente())
					{
						if(ab_bool)
						{
							String ls_matricula;

							ls_matricula = StringUtils.getStringNotNull(
								    StringUtils.getString(lpaiu_predioActoIU.getMatricula())
								);

							if(!ls_matricula.contains(IdentificadoresCommon.SIMBOLO_GUION))
								ls_matricula = StringUtils.getStringNotNull(lpaiu_predioActoIU.getIdCirculo())
									+ IdentificadoresCommon.SIMBOLO_GUION + ls_matricula;

							if(!StringUtils.isValidString(as_matriculas))
								as_matriculas = ls_matricula;
							else
								as_matriculas = as_matriculas + IdentificadoresCommon.SIMBOLO_COMA + ls_matricula;
						}
						else
						{
							String ls_idCirculo;
							String ls_matricula;

							ls_idCirculo     = StringUtils.getStringNotNull(lpaiu_predioActoIU.getIdCirculo());
							ls_matricula     = StringUtils.getStringNotNull(
								    StringUtils.getString(lpaiu_predioActoIU.getIdMatricula())
								);

							if(!StringUtils.isValidString(as_matriculas))
								as_matriculas = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + ls_matricula;
							else
								as_matriculas = as_matriculas + IdentificadoresCommon.SIMBOLO_COMA + ls_idCirculo
									+ IdentificadoresCommon.SIMBOLO_GUION + ls_matricula;
						}
					}
				}
			}
		}

		return as_matriculas;
	}

	/**
	 * Validar fecha desde fecha hasta.
	 *
	 * @param ad_desde de ad desde
	 * @param ad_hasta de ad hasta
	 * @param as_idComponenteDesde de as id componente desde
	 * @param as_idComponenteHasta de as id componente hasta
	 * @param afc_context de afc context
	 * @param ab_focus de ab focus
	 * @param ab_desdeMayor Bandera que indica si se debe validar que la fecha desde sea mayor a la actual o al revés.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws ParseException 
	 */
	protected void validarFechaDesdeFechaHasta(
	    Date ad_desde, Date ad_hasta, String as_idComponenteDesde, String as_idComponenteHasta, FacesContext afc_context,
	    boolean ab_focus, boolean ab_desdeMayor
	)
	    throws B2BException
	{
		if(ad_desde != null)
		{
			if(StringUtils.isValidString(as_idComponenteDesde) && StringUtils.isValidString(as_idComponenteHasta))
			{
				Date ld_fechaActual;
				Date ld_fechaActualSinHora;
				
				ld_fechaActual = new Date();
				ld_fechaActualSinHora = DateUtils.truncate(ld_fechaActual, java.util.Calendar.DAY_OF_MONTH);

				if(ab_desdeMayor)
				{
					if(ad_desde.after(ld_fechaActual))
					{
						ab_focus = validateStyles(
							    as_idComponenteDesde, afc_context, IdentificadoresCommon.DATO_INVALIDO, ab_focus
							);
						throw new B2BException(ErrorKeys.FECHA_DESDE_SUPERIOR_ACTUAL);
					}
				}
				else
				{
					if(ad_desde.before(ld_fechaActualSinHora))
					{
						ab_focus = validateStyles(
							    as_idComponenteDesde, afc_context, IdentificadoresCommon.DATO_INVALIDO, ab_focus
							);
						throw new B2BException(ErrorKeys.FECHA_DESDE_DEBE_SER_SUPERIOR);
					}
				}

				if((ad_hasta != null) && ad_desde.after(ad_hasta))
				{
					ab_focus     = validateStyles(
						    as_idComponenteDesde, afc_context, IdentificadoresCommon.DATO_INVALIDO, ab_focus
						);
					ab_focus     = validateStyles(
						    as_idComponenteHasta, afc_context, IdentificadoresCommon.DATO_INVALIDO, ab_focus
						);
					throw new B2BException(ErrorKeys.FECHA_DESDE_SUPERIOR);
				}
			}
		}
		else
		{
			ab_focus = validateStyles(as_idComponenteDesde, afc_context, IdentificadoresCommon.DATO_INVALIDO, ab_focus);
			throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
		}
	}

	/**
	 * Validar fecha inicial etapa.
	 *
	 * @param at_turnoHistoria de at turno historia
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void validarFechaInicialEtapa(TurnoHistoria at_turnoHistoria)
	    throws B2BException
	{
		if(at_turnoHistoria != null)
		{
			at_turnoHistoria.setFechaEtapaValida(true);
			irr_referenceRemote.findTurnoHistoriaByid(at_turnoHistoria);
		}
	}

	/**
	 * Validar tipos documentales.
	 *
	 * @param actd_ctd de actd ctd
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarTiposDocumentales(Collection<TipoDocumental> actd_ctd)
	    throws B2BException
	{
		return validarTiposDocumentales(actd_ctd, false);
	}

	/**
	 * Metódo que valida los tipos documentales del sistema.
	 *
	 * @param actd_ctd <code>Collection</code> llena con <code>TipoDocumental</code> la cúal será validada
	 * @param ab_validacionObservacionesRecepcion <code>boolean</code> que indica si son necesarias las
	 *     validaciones de observaciones y medio de recepción de cada tipo documental
	 * @return <code>boolean</code> que indica si todos los tipos documentales son válidos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected boolean validarTiposDocumentales(
	    Collection<TipoDocumental> actd_ctd, boolean ab_validacionObservacionesRecepcion
	)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = true;

		if(CollectionUtils.isValidCollection(actd_ctd))
		{
			Iterator<TipoDocumental> litd_iterator;

			litd_iterator = actd_ctd.iterator();

			if(litd_iterator != null)
			{
				while(litd_iterator.hasNext() && lb_return)
				{
					TipoDocumental ltd_tipoDocumental;

					ltd_tipoDocumental = litd_iterator.next();

					if(ltd_tipoDocumental != null)
					{
						String ls_idTipoDoc;

						ls_idTipoDoc = ltd_tipoDocumental.getIdTipoDocumental();

						if(StringUtils.isValidString(ls_idTipoDoc) && ls_idTipoDoc.equals("50"))
						{
							if(!StringUtils.isValidString(ltd_tipoDocumental.getObservaciones()))
								throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_DOCUMENTO_REGISTRO_PARCIAL);
						}

						if(!ltd_tipoDocumental.isSeleccionado())
							lb_return = StringUtils.isValidString(ltd_tipoDocumental.getObservaciones());

						if(ab_validacionObservacionesRecepcion)
						{
							if(!lb_return)
							{
								ltd_tipoDocumental.setCampoRojo(true);
								throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_DOCUMENTO_FALTANTE);
							}
							else if(ltd_tipoDocumental.isCampoRojo())
								ltd_tipoDocumental.setCampoRojo(false);

							if(!StringUtils.isValidString(ltd_tipoDocumental.getMedioRecepcion()))
							{
								ltd_tipoDocumental.setRecepcionValida(false);
								throw new B2BException(ErrorKeys.ERROR_SIN_MEDIO_RECEPCION);
							}
							else if(!ltd_tipoDocumental.isRecepcionValida())
								ltd_tipoDocumental.setRecepcionValida(true);
						}
					}
				}
			}
		}

		return lb_return;
	}

	/**
	 * Validar tramite suspension.
	 *
	 * @param as_circulo
	 *            correspondiente al valor del tipo de objeto String
	 * @param as_matricula
	 *            correspondiente al valor del tipo de objeto String
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 */
	protected void validarTramiteSuspension(String as_circulo, String as_matricula)
	    throws B2BException
	{
		if(
		    isr_suspensionRemote.validarTramiteSuspension(
			        as_circulo, as_matricula, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
		)
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = as_matricula;

			addMessage(MessagesKeys.MATRICULA_TIENE_SUSPENSION, loa_args);
		}
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
	public void cambiarIdiomaBachue()
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
