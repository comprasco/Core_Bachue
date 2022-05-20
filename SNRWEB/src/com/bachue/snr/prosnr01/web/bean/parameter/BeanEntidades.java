package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.ActividadEconomica;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.ExportFiles;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad para el Bean Entidades
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanEntidades")
@SessionScoped
public class BeanEntidades extends BaseBean
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanEntidades.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1087661089809652174L;

	/** Propiedad iaee entidad. */
	private AccEntidadExterna iaee_entidad;

	/** Propiedad icaee entidad externa */
	private Collection<AccEntidadExterna> icaee_entidadExterna;

	/** Propiedad icp persona asociar */
	private Collection<Persona> icp_personaAsociar;

	/** Propiedad icp persona entidad */
	private Collection<Persona> icp_personaEntidad;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad ip persona a eliminar. */
	private Persona ip_personaAEliminar;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir registro datos consultados */
	private Registro ir_registroDatosConsultados;

	/** Propiedad irr reference remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc imagen plantilla persona masivos. */
	private StreamedContent isc_imagenPlantillaPersonaMasivos;

	/** Propiedad isc resultado cargue persona. */
	private StreamedContent isc_resultadoCarguePersona;

	/** Propiedad is asignar personas */
	private String is_asignarPersonas;

	/** Propiedad is documento identidad */
	private String is_documentoIdentidad;

	/** Propiedad is nombre entidad */
	private String is_nombreEntidad;

	/** Propiedad is tipo documento identidad */
	private String is_tipoDocumentoIdentidad;

	/** Propiedad is tipo entidad */
	private String is_tipoEntidad;

	/** Propiedad ib agregar persona modificar*/
	private boolean ib_agregarPersonaModificar;

	/** Propiedad ib correo valido*/
	private boolean ib_correoValido;

	/** Propiedad ib crear entidad */
	private boolean ib_crearEntidad;

	/** Propiedad ib editado*/
	private boolean ib_editado;

	/** Propiedad ib lista persona */
	private boolean ib_listaPersona;

	/** Propiedad ib mostrar datos basicos */
	private boolean ib_mostrarDatosBasicos;

	/** Propiedad ib persona existe */
	private boolean ib_personaExiste;

	/** Propiedad ib representante legal mostrar */
	private boolean ib_representanteLegalMostrar;

	/** Propiedad ib tabla activa */
	private boolean ib_tablaActiva;

	/** Propiedad ib tabla persona */
	private boolean ib_tablaPersona;

	/** Propiedad ib tabla persona asociada*/
	private boolean ib_tablaPersonaAsociada;

	/** Propiedad ib tabla persona asociada masiva*/
	private boolean ib_tablaPersonaAsociadaMasiva;

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setAgregarPersonaModificar(boolean ab_b)
	{
		ib_agregarPersonaModificar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isAgregarPersonaModificar()
	{
		return ib_agregarPersonaModificar;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAsignarPersonas(String as_s)
	{
		is_asignarPersonas = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getAsignarPersonas()
	{
		return is_asignarPersonas;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCorreoValido(boolean ab_b)
	{
		ib_correoValido = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCorreoValido()
	{
		return ib_correoValido;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setCrearEntidad(boolean ab_b)
	{
		ib_crearEntidad = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isCrearEntidad()
	{
		return ib_crearEntidad;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDocumentoIdentidad(String as_s)
	{
		is_documentoIdentidad = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocumentoIdentidad()
	{
		return is_documentoIdentidad;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEditado(boolean ab_b)
	{
		ib_editado = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isEditado()
	{
		return ib_editado;
	}

	/**
	 * @param aaee_aee asigna el valor a la propiedad
	 */
	public void setEntidad(AccEntidadExterna aaee_aee)
	{
		iaee_entidad = aaee_aee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public AccEntidadExterna getEntidad()
	{
		if(iaee_entidad == null)
			iaee_entidad = new AccEntidadExterna();

		return iaee_entidad;
	}

	/**
	 * @param acaee_caee asigna el valor a la propiedad
	 */
	public void setEntidadExterna(Collection<AccEntidadExterna> acaee_caee)
	{
		icaee_entidadExterna = acaee_caee;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<AccEntidadExterna> getEntidadExterna()
	{
		return icaee_entidadExterna;
	}

	/**
	 * @param asc_sc asigna el valor a la propiedad
	 */
	public void setImagenPlantillaPersonaMasivos(StreamedContent asc_sc)
	{
		isc_imagenPlantillaPersonaMasivos = asc_sc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public StreamedContent getImagenPlantillaPersonaMasivos()
	{
		return isc_imagenPlantillaPersonaMasivos;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setListaPersona(boolean ab_b)
	{
		ib_listaPersona = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isListaPersona()
	{
		return ib_listaPersona;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setMostrarDatosBasicos(boolean ab_b)
	{
		ib_mostrarDatosBasicos = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isMostrarDatosBasicos()
	{
		return ib_mostrarDatosBasicos;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEntidad(String as_s)
	{
		is_nombreEntidad = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEntidad()
	{
		return is_nombreEntidad;
	}

	/**
	 * Modifica el valor de persona.
	 *
	 * @param ap_p asigna el valor a la propiedad persona
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna el valor de persona.
	 *
	 * @return el valor de persona
	 */
	public Persona getPersona()
	{
		if(ip_persona == null)
		{
			ip_persona = new Persona();
			ip_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		}

		return ip_persona;
	}

	/**
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setPersonaAEliminar(Persona ap_p)
	{
		ip_personaAEliminar = ap_p;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Persona getPersonaAEliminar()
	{
		if(ip_personaAEliminar == null)
			ip_personaAEliminar = new Persona();

		return ip_personaAEliminar;
	}

	/**
	 * @param acp_cp asigna el valor a la propiedad
	 */
	public void setPersonaAsociar(Collection<Persona> acp_cp)
	{
		icp_personaAsociar = acp_cp;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Persona> getPersonaAsociar()
	{
		return icp_personaAsociar;
	}

	/**
	 * @param acp_cp asigna el valor a la propiedad
	 */
	public void setPersonaEntidad(Collection<Persona> acp_cp)
	{
		icp_personaEntidad = acp_cp;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Persona> getPersonaEntidad()
	{
		return icp_personaEntidad;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setPersonaExiste(boolean ab_b)
	{
		ib_personaExiste = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isPersonaExiste()
	{
		return ib_personaExiste;
	}

	/**
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setRegistroDatosConsultados(Registro ar_r)
	{
		ir_registroDatosConsultados = ar_r;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Registro getRegistroDatosConsultados()
	{
		return ir_registroDatosConsultados;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRepresentanteLegalMostrar(boolean ab_b)
	{
		ib_representanteLegalMostrar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRepresentanteLegalMostrar()
	{
		return ib_representanteLegalMostrar;
	}

	/**
	 * @param asc_sc asigna el valor a la propiedad
	 */
	public void setResultadoCarguePersona(StreamedContent asc_sc)
	{
		isc_resultadoCarguePersona = asc_sc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public StreamedContent getResultadoCarguePersona()
	{
		return isc_resultadoCarguePersona;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaActiva(boolean ab_b)
	{
		ib_tablaActiva = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaActiva()
	{
		return ib_tablaActiva;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaPersona(boolean ab_b)
	{
		ib_tablaPersona = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaPersona()
	{
		return ib_tablaPersona;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaPersonaAsociada(boolean ab_b)
	{
		ib_tablaPersonaAsociada = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaPersonaAsociada()
	{
		return ib_tablaPersonaAsociada;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTablaPersonaAsociadaMasiva(boolean ab_b)
	{
		ib_tablaPersonaAsociadaMasiva = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTablaPersonaAsociadaMasiva()
	{
		return ib_tablaPersonaAsociadaMasiva;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocumentoIdentidad(String as_s)
	{
		is_tipoDocumentoIdentidad = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocumentoIdentidad()
	{
		return is_tipoDocumentoIdentidad;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoEntidad(String as_s)
	{
		is_tipoEntidad = as_s;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoEntidad()
	{
		return is_tipoEntidad;
	}

	/**
	 * Retorna el valor del objeto de byte[].
	 *
	 * @param aba_excel correspondiente al valor del tipo de objeto byte[]
	 * @param as_nameFile correspondiente al valor del tipo de objeto String
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción
	 */
	public void FileLoadPersonas(byte[] aba_excel, String as_nameFile, String as_userId)
	    throws B2BException, IOException
	{
		try
		{
			if(aba_excel != null)
			{
				Collection<Persona> lcp_resultadoPersonas;
				String              ls_mensaje;

				ls_mensaje     = null;

				lcp_resultadoPersonas = ipr_parameterRemote.validarExcelPersonaEntidad(
					    aba_excel, as_nameFile, as_userId, getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcp_resultadoPersonas))
				{
					Collection<Persona> lcp_persona;

					lcp_persona = new ArrayList<Persona>();

					for(Persona lp_temp : lcp_resultadoPersonas)
					{
						if(lp_temp != null)
						{
							String ls_idTipoDocumento;
							String ls_idNumeroDocumento;
							String ls_correoElectronico;
							String ls_primerNombre;
							String ls_segundoNombre;
							String ls_primerApellido;
							String ls_segundoApellido;
							String ls_tipoPersona;

							ls_idTipoDocumento       = lp_temp.getIdDocumentoTipo();
							ls_idNumeroDocumento     = lp_temp.getNumeroDocumento();
							ls_correoElectronico     = lp_temp.getCorreoElectronico();
							ls_primerNombre          = lp_temp.getPrimerNombre();
							ls_segundoNombre         = lp_temp.getSegundoNombre();
							ls_primerApellido        = lp_temp.getPrimerApellido();
							ls_segundoApellido       = lp_temp.getSegundoApellido();
							ls_tipoPersona           = lp_temp.getIdTipoPersona();

							if(
							    StringUtils.isValidString(ls_idTipoDocumento)
								    || StringUtils.isValidString(ls_idNumeroDocumento)
								    || StringUtils.isValidString(ls_correoElectronico)
								    || StringUtils.isValidString(ls_primerNombre)
								    || StringUtils.isValidString(ls_segundoNombre)
								    || StringUtils.isValidString(ls_primerApellido)
								    || StringUtils.isValidString(ls_segundoApellido)
								    || StringUtils.isValidString(ls_tipoPersona)
							)
							{
								String ls_idPais;

								ls_idPais = lp_temp.getIdPais();

								if(!StringUtils.isValidString(ls_idPais))
									lp_temp.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);

								lcp_persona.add(lp_temp);
							}
						}
					}

					if(CollectionUtils.isValidCollection(lcp_persona))
					{
						boolean             lb_cargue;
						Collection<Persona> lcp_personasCargadasMasiva;

						lb_cargue                      = false;
						lcp_personasCargadasMasiva     = new ArrayList<Persona>();

						for(Persona lc_temp : lcp_persona)
						{
							if(lc_temp != null)
							{
								boolean lb_cargueFallido;

								lb_cargueFallido = lc_temp.isErrorCargue();

								if(!lb_cargue)
									lb_cargue = lb_cargueFallido;

								if(!lb_cargueFallido)
									lcp_personasCargadasMasiva.add(lc_temp);
							}
						}

						setPersonaAsociar(lcp_personasCargadasMasiva);
						setTablaPersonaAsociadaMasiva(true);

						byte[]              lba_arch;
						ExportFiles         lef_files;
						Map<String, String> lhm_columns;

						lba_arch        = null;
						lef_files       = new ExportFiles();
						lhm_columns     = new LinkedHashMap<String, String>();

						lhm_columns.put("IdDocumentoTipo", "Tipo de documento");
						lhm_columns.put("NumeroDocumento", "Numero de documento");
						lhm_columns.put("CorreoElectronico", "Correo Electronico");
						lhm_columns.put("PrimerNombre", "Primer Nombre");
						lhm_columns.put("SegundoNombre", "Segundo Nombre");
						lhm_columns.put("PrimerApellido", "Primer Apellido");
						lhm_columns.put("SegundoApellido", "Segundo Apellido");
						lhm_columns.put("ErrorPersonaMasiva", lb_cargue ? "Error" : "Resultado del Cargue");

						try
						{
							lba_arch = lef_files.getXSLFromCollection(lcp_persona, lhm_columns);
						}
						catch(IOException le_e)
						{
							clh_LOGGER.error("genera", le_e);
						}

						InputStream stream = new ByteArrayInputStream(lba_arch);

						isc_resultadoCarguePersona     = new DefaultStreamedContent(
							    stream, TipoContenidoCommon.XLS_XLSX_XXLS, "Resultado_del_Cargue.xlsx"
							);

						ls_mensaje = getMessages().getString(MessagesKeys.PROCESAMINETO_DE_CARGUE_MASIVO_TERMINADO);
						addMessage("I", ls_mensaje);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidadesModificarDetalle");
		}
	}

	/**
	 * Método encargado de agregar un registro en la pantalla.
	 *
	 * @param ab_listaPersona Objeto de tipo boolean que indica la persona a eliminar
	 * de una lista
	 */
	public void agregarRegistroPersona(boolean ab_listaPersona)
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				String ls_detalle;

				ls_detalle = ab_listaPersona ? "fEntidadesDetalle" : "fEntidadesModificarDetalle";

				if(StringUtils.isValidString(ls_detalle))
				{
					{
						String ls_idTipoPersona;

						ls_idTipoPersona     = lp_persona.getIdTipoPersona();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idSOMTipoPersona", lfc_context, ls_idTipoPersona, lb_focus
							);

						if(!StringUtils.isValidString(ls_idTipoPersona))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);
					}

					{
						String ls_idDocumentoTipo;

						ls_idDocumentoTipo     = lp_persona.getIdDocumentoTipo();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idSOMTipoDoc", lfc_context, ls_idDocumentoTipo, lb_focus
							);

						if(!StringUtils.isValidString(ls_idDocumentoTipo))
							throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);
					}

					{
						String ls_documento;

						ls_documento     = lp_persona.getNumeroDocumento();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idOlDocumento", lfc_context, ls_documento, lb_focus
							);

						if(!StringUtils.isValidString(ls_documento))
							throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
					}

					{
						String ls_nacionalidad;

						ls_nacionalidad     = lp_persona.getIdPais();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idSOMNacionalidad", lfc_context, ls_nacionalidad, lb_focus
							);

						if(!StringUtils.isValidString(ls_nacionalidad))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
					}

					{
						String ls_primerNombre;

						ls_primerNombre     = lp_persona.getPrimerNombre();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idOlPNombre", lfc_context, ls_primerNombre, lb_focus
							);

						if(!StringUtils.isValidString(ls_primerNombre))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
					}

					{
						String ls_primerApellido;

						ls_primerApellido     = lp_persona.getPrimerApellido();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idOlPApellido", lfc_context, ls_primerApellido, lb_focus
							);

						if(!StringUtils.isValidString(ls_primerApellido))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
					}

					{
						String ls_correoElectronico;

						ls_correoElectronico     = lp_persona.getCorreoElectronico();

						lb_focus = validateStyles(
							    ":" + ls_detalle + ":idCorreoElectronicoInter", lfc_context, ls_correoElectronico,
							    lb_focus
							);

						if(
						    !StringUtils.isValidString(ls_correoElectronico)
							    || !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correoElectronico)
						)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
					}

					{
						String ls_nombreCompleto;

						ls_nombreCompleto = lp_persona.getNombreCompleto();

						if(!StringUtils.isValidString(ls_nombreCompleto))
						{
							String ls_llenarNombre;

							ls_llenarNombre = llenarNombre(lp_persona);

							if(StringUtils.isValidString(ls_llenarNombre))
								lp_persona.setNombreCompleto(ls_llenarNombre);
						}
					}

					Collection<Persona> lcp_persona;

					lcp_persona = ab_listaPersona ? getPersonaAsociar() : getPersonaEntidad();

					if(CollectionUtils.isValidCollection(lcp_persona))
					{
						String ls_idPersonaInsertar;

						ls_idPersonaInsertar = lp_persona.getIdPersona();

						if(StringUtils.isValidString(ls_idPersonaInsertar))
						{
							for(Persona lp_temp : lcp_persona)
							{
								if(lp_temp != null)
								{
									String ls_idPersona;

									ls_idPersona = lp_temp.getIdPersona();

									if(
									    StringUtils.isValidString(ls_idPersona)
										    && ls_idPersona.equalsIgnoreCase(ls_idPersonaInsertar)
									)
										throw new B2BException(ErrorKeys.ERROR_PERSONA_ASOCIADA);
								}
							}
						}

						lcp_persona.add(lp_persona);
					}
					else
					{
						lcp_persona = new ArrayList<Persona>();

						lcp_persona.add(lp_persona);
					}

					if(ab_listaPersona)
						setPersonaAsociar(lcp_persona);
					else
					{
						setPersonaEntidad(lcp_persona);
						setAgregarPersonaModificar(false);
					}

					setTablaPersonaAsociada(true);
					setRepresentanteLegalMostrar(false);
					addMessage(MessagesKeys.PERSONA_AGREGADA);

					addMessage(
					    ab_listaPersona ? MessagesKeys.PERSONA_AGREGADA_CREAR : MessagesKeys.PERSONA_AGREGADA_MODIFICAR
					);

					PrimeFaces.current().ajax().update(ls_detalle);
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
	 * Método encargado de cambiar el responsable de la persona
	 * @param ap_p objeto de tipo Persona con el nuevo responsable
	 * @param ab_param objeto de tipo boolean con la tabla a cargar
	 */
	public void cambioResponsablePersona(Persona ap_p, boolean ab_param)
	{
		if(ap_p != null)
		{
			String              ls_detalle;
			Collection<Persona> lcp_persona;

			lcp_persona     = ab_param ? getPersonaAsociar() : getPersonaEntidad();
			ls_detalle      = ab_param ? "fEntidadesDetalle" : "fEntidadesModificarDetalle";

			if(CollectionUtils.isValidCollection(lcp_persona))
			{
				for(Persona lp_temp : lcp_persona)
				{
					if((lp_temp != null) && (lp_temp != ap_p))
						lp_temp.setRepresentanteLegal(EstadoCommon.N);
				}
			}

			PrimeFaces.current().ajax().update(ls_detalle);
		}
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada
	 * en la consulta por documento.
	 *
	 * @param ap_persona Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 * @param ab_tabla Objeto de tipo boolean contenedor de la información
	 */
	public void cargarDatosPersonales(Persona ap_persona, boolean ab_tabla)
	{
		try
		{
			if(ap_persona != null)
			{
				String ls_idPersona;

				ls_idPersona = ap_persona.getIdPersona();

				if(StringUtils.isValidString(ls_idPersona))
				{
					PersonaCorreoElectronico lpce_personaCorreo;

					lpce_personaCorreo = ipr_parameterRemote.findPersonaCorreoById(ls_idPersona);

					if(lpce_personaCorreo != null)
					{
						setCorreoValido(true);

						String ls_correo;

						ls_correo = ap_persona.getCorreoElectronico();

						if(!StringUtils.isValidString(ls_correo))
							ap_persona.setCorreoElectronico(lpce_personaCorreo.getCorreoElectronico());
					}
				}

				cargarNombreUsuario(ap_persona, ab_tabla);

				setPersona(ap_persona);
				setPersonaExiste(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidades");
		}
	}

	/**
	 * Metodo para traer los registros de la base de datos SDB_COL_INTERESADO_DOCUMENTO_TIPO
	 *
	 * @return Colección de InteresadoDocumentoTipo resultante de la consulta
	 */
	public Collection<InteresadoDocumentoTipo> cargarInteresadoDocumentoTipo()
	{
		Collection<InteresadoDocumentoTipo> lcidt_interesadoDocumentoTipo;

		lcidt_interesadoDocumentoTipo = new ArrayList<InteresadoDocumentoTipo>();

		try
		{
			lcidt_interesadoDocumentoTipo = irr_referenceRemote.findTipoDocumentoActivo();

			if(CollectionUtils.isValidCollection(lcidt_interesadoDocumentoTipo))
			{
				AccEntidadExterna laee_entidadExterna;

				laee_entidadExterna = getEntidad();

				if(laee_entidadExterna != null)
					laee_entidadExterna.setIdDocumentoTipo(IdentificadoresCommon.NIT);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcidt_interesadoDocumentoTipo;
	}

	/**
	 * Se encarga de cargar en pantalla el usuario de una parsona.
	 *
	 * @param ap_persona Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 * @param ab_tabla Objeto de tipo boolean contenedor de la información
	 */
	public void cargarNombreUsuario(Persona ap_persona, boolean ab_tabla)
	{
		try
		{
			if(ap_persona != null)
			{
				{
					Collection<Persona> lcp_persona;
					String              ls_idEntidad;
					String              ls_usuarioPersona;

					lcp_persona           = ab_tabla ? getPersonaAsociar() : getPersonaEntidad();
					ls_idEntidad          = getEntidad().getIdEntidadExterna();
					ls_usuarioPersona     = ipr_parameterRemote.findUsuarioPersona(
						    ap_persona, lcp_persona, ls_idEntidad
						);

					if(StringUtils.isValidString(ls_usuarioPersona))
						ap_persona.setUsuario(ls_usuarioPersona);
				}

				PrimeFaces.current().ajax().update("fEntidades");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidades");
		}
	}

	/**
	 * Método encargado de cargar el panel para agregar personas
	 * al modificar la entidad.
	 *
	 */
	public void cargarPanelAgregarPersona()
	{
		setAgregarPersonaModificar(true);
		setListaPersona(false);
		setMostrarDatosBasicos(false);
		setPersona(null);
		setEditado(false);
		PrimeFaces.current().ajax().update("fEntidadesModificarDetalle");
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de TipoEntidad resultante de la consulta
	 */
	public Collection<TipoEntidad> cargarTipoEntidad()
	{
		Collection<TipoEntidad> lcte_tipoEntidad;

		lcte_tipoEntidad = new ArrayList<TipoEntidad>();

		try
		{
			lcte_tipoEntidad = irr_referenceRemote.findTipoEntidad(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcte_tipoEntidad;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setTablaPersonaAsociada(false);
		setTablaPersona(false);
		setTablaActiva(false);
		setRepresentanteLegalMostrar(false);
		setPersonaExiste(false);
		setMostrarDatosBasicos(false);
		setListaPersona(false);
		setCrearEntidad(false);
		setTipoEntidad(null);
		setTipoDocumentoIdentidad(null);
		setNombreEntidad(null);
		setDocumentoIdentidad(null);
		setAsignarPersonas(null);
		setPersonaAEliminar(null);
		setPersona(null);
		setRegistroDatosConsultados(null);
		setPersonaAsociar(null);
		setPersonaEntidad(null);
		setEntidadExterna(null);
		setEntidad(null);
		setEditado(false);
		setAgregarPersonaModificar(false);
		setImagenPlantillaPersonaMasivos(null);
		setResultadoCarguePersona(null);
		setTablaPersonaAsociadaMasiva(false);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_ACC_ENTIDAD_EXTERNA
	 * @param aaee_aee parametro a buscar
	 * @throws B2BException
	 */
	public void consultaDetallada(AccEntidadExterna aaee_aee)
	    throws B2BException
	{
		if(aaee_aee != null)
		{
			Collection<Persona> lcp_datos;

			lcp_datos = ipr_parameterRemote.findPersonaByEntidad(aaee_aee);

			if(CollectionUtils.isValidCollection(lcp_datos))
				setPersonaEntidad(lcp_datos);

			setEntidad(aaee_aee);
		}
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 */
	public void consultarEntidades()
	{
		try
		{
			String                        ls_tipoEntidad;
			String                        ls_nombreEntidad;
			Collection<AccEntidadExterna> lcaee_entidades;

			ls_nombreEntidad     = getNombreEntidad();
			ls_tipoEntidad       = getTipoEntidad();
			lcaee_entidades      = new ArrayList<AccEntidadExterna>();

			if(StringUtils.isValidString(ls_tipoEntidad) && StringUtils.isValidString(ls_nombreEntidad))
			{
				Collection<AccEntidadExterna> lcaee_entidadesNombre;

				lcaee_entidadesNombre = getEntidadExterna();

				if(CollectionUtils.isValidCollection(lcaee_entidadesNombre))
				{
					for(AccEntidadExterna laee_temp : lcaee_entidadesNombre)
					{
						if(laee_temp != null)
						{
							String ls_idTipoEntidad;

							ls_idTipoEntidad = laee_temp.getIdTipoEntidad();

							if(
							    StringUtils.isValidString(ls_idTipoEntidad)
								    && ls_idTipoEntidad.equalsIgnoreCase(ls_tipoEntidad)
							)
								lcaee_entidades.add(laee_temp);
						}
					}
				}

				else
					lcaee_entidades = irr_referenceRemote.findEntidadExternaByNombreLike(
						    ls_nombreEntidad.toUpperCase()
						);
			}

			else if(StringUtils.isValidString(ls_tipoEntidad) && (!StringUtils.isValidString(ls_nombreEntidad)))
				lcaee_entidades = irr_referenceRemote.findTipoEntidadExterna(ls_tipoEntidad, true, false);

			else
				lcaee_entidades = ipr_parameterRemote.findAllAccEntidadExternas();

			if(!CollectionUtils.isValidCollection(lcaee_entidades))
			{
				setCrearEntidad(true);
				addMessage(MessagesKeys.ENTIDADES_NO_ENCONTRADAS);
			}

			else
				setCrearEntidad(false);

			setEntidadExterna(lcaee_entidades);
			setTablaActiva(true);
			PrimeFaces.current().ajax().update("fEntidades");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidades");
		}
	}

	/**
	 * Método encargado de consultar los registros de la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @param as_nombreEntidad Objeto de tipo String con el parametro a consultar
	 *
	 */
	public void consultarEntidadesNombre(String as_nombreEntidad)
	{
		try
		{
			if(StringUtils.isValidString(as_nombreEntidad))
			{
				int li_tamNombreEntidad;

				li_tamNombreEntidad = as_nombreEntidad.length();

				if(li_tamNombreEntidad >= 3)
				{
					Collection<AccEntidadExterna> lcaee_entidadExterna;

					lcaee_entidadExterna = irr_referenceRemote.findEntidadExternaByNombreLike(
						    as_nombreEntidad.toUpperCase()
						);

					if(CollectionUtils.isValidCollection(lcaee_entidadExterna))
						setCrearEntidad(false);

					else
						setCrearEntidad(true);

					setEntidadExterna(lcaee_entidadExterna);
					setTablaActiva(true);
				}

				else
					setTablaActiva(false);
			}
			else
				setTablaActiva(false);

			PrimeFaces.current().ajax().update("fEntidades");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidades");
		}
	}

	/**
	 * Consultar persona documento.
	 *
	 * @param ab_pantalla Objeto de tipo boolean que indica el formulario de la
	 * pantalla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPersonaDocumento(boolean ab_pantalla)
	    throws B2BException
	{
		boolean lb_focus;
		String  ls_detalle;

		lb_focus       = true;
		ls_detalle     = ab_pantalla ? "fEntidadesDetalle" : "fEntidadesModificarDetalle";
		setPersona(null);

		try
		{
			if(StringUtils.isValidString(ls_detalle))
			{
				BeanDireccion lbd_beanDireccion;
				Persona       lp_parametros;
				Registro      lr_registro;
				FacesContext  lfc_context;

				lbd_beanDireccion     = getBeanDireccion();
				lfc_context           = FacesContext.getCurrentInstance();
				lp_parametros         = new Persona();
				lr_registro           = new Registro();

				lp_parametros.setNumeroDocumento(getDocumentoIdentidad());
				lp_parametros.setTipoDocIdentidad(getTipoDocumentoIdentidad());

				String ls_idTipoDocumento;

				ls_idTipoDocumento     = lp_parametros.getTipoDocIdentidad();

				lb_focus = validateStyles(
					    ls_detalle + ":idSOMTipoDocIdentidad", lfc_context, ls_idTipoDocumento, lb_focus
					);

				String ls_documentoIdentidad;
				ls_documentoIdentidad     = lp_parametros.getNumeroDocumento();

				lb_focus = validateStyles(
					    ls_detalle + ":idItDocumentoID", lfc_context, ls_documentoIdentidad, lb_focus
					);

				if(!StringUtils.isValidString(ls_idTipoDocumento))
					throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);

				if(StringUtils.isValidString(ls_documentoIdentidad))
				{
					if(
					    !ExpresionRegular.getExpresionRegular()
						                     .esDocumentoIdentidad(StringUtils.getString(ls_documentoIdentidad))
					)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
				}
				else
					throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

				lr_registro.setPersona(lp_parametros);

				lbd_beanDireccion.setHabilitadoPorConsulta(true);

				{
					Collection<Persona> lcp_persona;

					lcp_persona = ab_pantalla ? getPersonaAsociar() : getPersonaEntidad();

					if(!CollectionUtils.isValidCollection(lcp_persona))
						setRepresentanteLegalMostrar(true);
					else
					{
						for(Persona lc_temp : lcp_persona)
						{
							if(lc_temp != null)
							{
								String ls_representante;

								ls_representante = lc_temp.getRepresentanteLegal();

								if(
								    StringUtils.isValidString(ls_representante)
									    && ls_representante.equalsIgnoreCase(EstadoCommon.S)
								)
									setRepresentanteLegalMostrar(false);
								else
									setRepresentanteLegalMostrar(true);
							}
						}
					}

					setMostrarDatosBasicos(true);
				}

				{
					Registro lr_resultado;

					lr_resultado = irr_registroRemote.findPersonByDocument(lr_registro, getUserId());

					if(lr_resultado != null)
					{
						setRegistroDatosConsultados(lr_resultado);
						setListaPersona(true);
						setTablaPersona(true);
						setPersonaExiste(false);
						addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
						PrimeFaces.current().ajax().update(ls_detalle);
					}

					else
						throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_CONSULTA);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);

			setPersonaExiste(false);
			setTablaPersona(false);
			setCorreoValido(false);

			PrimeFaces.current().ajax().update(ls_detalle);
			PrimeFaces.current().ajax().update(ls_detalle + ":globalMsg");
		}
	}

	/**
	 * Método encargado de generar un <code>DefaultStreamedContent</code> con la
	 * imagen resultante de la consulta en la BD.
	 * @throws B2BException
	 *
	 */
	public void descargarPlantillaCargueMasivoPersonas()
	    throws B2BException
	{
		Constantes lc_constante;

		lc_constante = irr_registroRemote.descargarPlantilla(
			    ConstanteCommon.CARGUE_EXCEL_PERSONAS_MASIVAS, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

		if(lc_constante != null)
			setImagenPlantillaPersonaMasivos(
			    generarArchivosDescarga(
			        lc_constante.getImagenes(), TipoContenidoCommon.XLS_XLSX_XXLS,
			        lc_constante.getIdConstante() + lc_constante.getTipoArchivo()
			    )
			);
	}

	/**
	 * Metodo para traer los registros de la base de datos
	 *
	 * @return Colección de ActividadEconomica resultante de la consulta
	 */
	public Collection<ActividadEconomica> findActividadEconomica()
	{
		Collection<ActividadEconomica> lcae_actividadEconomica;

		lcae_actividadEconomica = new ArrayList<ActividadEconomica>();

		try
		{
			lcae_actividadEconomica = ipr_parameterRemote.findAllActividadEconomicaActivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcae_actividadEconomica;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Departamento> findDepartamentos()
	{
		Collection<Departamento> lcd_departamentos;

		lcd_departamentos = null;

		try
		{
			AccEntidadExterna laee_entidadExterna;

			laee_entidadExterna = getEntidad();

			if(laee_entidadExterna != null)
			{
				String       ls_pais;
				Departamento ld_parametros;

				ld_parametros     = new Departamento();
				ls_pais           = laee_entidadExterna.getIdPais();

				if(StringUtils.isValidString(ls_pais))
				{
					ld_parametros.setIdPais(ls_pais);

					lcd_departamentos = irr_referenceRemote.findDepartaments(ld_parametros);
				}
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
	 * @return devuelve el valor de Collection
	 */
	public Collection<Municipio> findMunicipios()
	{
		Collection<Municipio> lcm_municipios;

		lcm_municipios = null;

		try
		{
			Municipio         lm_parametros;
			AccEntidadExterna laee_entidadExterna;

			laee_entidadExterna     = getEntidad();
			lm_parametros           = new Municipio();

			if(laee_entidadExterna != null)
			{
				lm_parametros.setIdPais(laee_entidadExterna.getIdPais());
				lm_parametros.setIdDepartamento(laee_entidadExterna.getIdDepartamento());

				if(StringUtils.isValidString(lm_parametros.getIdDepartamento()))
					lcm_municipios = irr_referenceRemote.findMunicipios(lm_parametros);
				else
					lcm_municipios = null;
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
	 * Retorna el valor del objeto de String.
	 *
	 * @param au_u correspondiente al valor del tipo de objeto Usuario
	 * @return devuelve el valor de String
	 */
	public String llenarNombre(Persona ap_p)
	{
		String ls_nombreCompleto;

		ls_nombreCompleto = IdentificadoresCommon.DATO_INVALIDO;

		if(ap_p != null)
		{
			String ls_primerNombre;
			String ls_segundoNombre;
			String ls_primerApellido;
			String ls_segundoApellido;

			ls_primerNombre        = ap_p.getPrimerNombre();
			ls_segundoNombre       = ap_p.getSegundoNombre();
			ls_primerApellido      = ap_p.getPrimerApellido();
			ls_segundoApellido     = ap_p.getSegundoApellido();

			if(StringUtils.isValidString(ls_primerNombre))
				ls_nombreCompleto = ls_nombreCompleto + ls_primerNombre + IdentificadoresCommon.ESPACIO_VACIO;

			if(StringUtils.isValidString(ls_segundoNombre))
				ls_nombreCompleto = ls_nombreCompleto + ls_segundoNombre + IdentificadoresCommon.ESPACIO_VACIO;

			if(StringUtils.isValidString(ls_primerApellido))
				ls_nombreCompleto = ls_nombreCompleto + ls_primerApellido + IdentificadoresCommon.ESPACIO_VACIO;

			if(StringUtils.isValidString(ls_segundoApellido))
				ls_nombreCompleto = ls_nombreCompleto + ls_segundoApellido + IdentificadoresCommon.ESPACIO_VACIO;
		}

		return ls_nombreCompleto;
	}

	/**
	 * Método encargado de prender booleana del registro para editarlo
	 *
	 * @param ap_p Objeto Persona a editar
	 */
	public void modificarRegistro(Persona ap_p)
	{
		if(ap_p != null)
		{
			setPersona(ap_p);
			setMostrarDatosBasicos(true);
			setPersonaExiste(false);
			setAgregarPersonaModificar(true);
			setEditado(true);
			cargarNombreUsuario(ap_p, false);
			PrimeFaces.current().ajax().update("fEntidadesModificarDetalle");
		}
	}

	/**
	 * Método encargado de agregar un registro y modificar los demás.
	 *
	 * @param ab_listaPersona Objeto de tipo boolean que indica la persona a eliminar
	 * de una lista
	 *
	 */
	public void personaEliminada(boolean ab_listaPersona)
	{
		Persona lp_p;
		String  ls_detalle;

		lp_p           = getPersonaAEliminar();
		ls_detalle     = ab_listaPersona ? "fEntidadesDetalle" : "fEntidadesModificarDetalle";

		if((lp_p != null) && StringUtils.isValidString(ls_detalle))
		{
			Collection<Persona> lcp_personaActual;

			lcp_personaActual = ab_listaPersona ? getPersonaAsociar() : getPersonaEntidad();

			if(CollectionUtils.isValidCollection(lcp_personaActual))
			{
				Collection<Persona> lcp_personaEliminar;

				lcp_personaEliminar = new ArrayList<Persona>();

				for(Persona lcp_temp : lcp_personaActual)
				{
					if(lcp_temp != null)
					{
						if(lcp_temp != lp_p)
							lcp_personaEliminar.add(lcp_temp);
					}
				}

				if(ab_listaPersona)
					setPersonaAsociar(lcp_personaEliminar);
				else
					setPersonaEntidad(lcp_personaEliminar);
			}
		}

		addMessage(MessagesKeys.REGISTRO_ELIMINADO_CORRECTAMENTE);
		PrimeFaces.current().ajax().update(ls_detalle);
	}

	/**
	 * Metodo que se encarga de cargar la persona a eliminar
	 *
	 * @param ap_persona Objeto de tipo Persona a eliminar
	 *
	 */
	public void personaEliminar(Persona ap_persona)
	{
		setPersonaAEliminar(ap_persona);
	}

	/**
	 * Metodo encargado de procesar el cargue de excel de intervinientes.
	 *
	 * @param afue_event Argumento de tipo <code>FileUploadEvent</code> que contiene los bytes del archivo.
	 * @param ab_propagarExcepcion Argumento de tipo <code>boolean</code> que indica si se debe propagar excepción <code>true</code> o no <code>false</code>.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void procesarExcelPersonas(FileUploadEvent afue_event)
	    throws B2BException
	{
		try
		{
			if(afue_event != null)
			{
				String       ls_mensaje = null;
				UploadedFile luf_file;

				luf_file                = afue_event.getFile();

				if(luf_file != null)
				{
					byte[] lba_excelFile;
					String ls_fileName;

					lba_excelFile     = luf_file.getContents();
					ls_fileName       = luf_file.getFileName();

					if((lba_excelFile != null) && (StringUtils.isValidString(ls_fileName)))
						FileLoadPersonas(lba_excelFile, ls_fileName, getUserId());
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_ARCHIVOS_EXCEL);

				if(!StringUtils.isValidString(ls_mensaje))
					ls_mensaje = MessagesKeys.ARCHIVO_CARGUE_MASIVO_TERMINADO;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fEntidadesModificarDetalle");
		}

		catch(Exception lb2be_e)
		{
			addMessage("E", lb2be_e.getMessage());
		}
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.ENTIDADES;

		clear();

		return ls_return;
	}

	/**
	 * Consultar persona documento.
	 *
	 * @param ab_param correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvar(boolean ab_param)
	    throws B2BException
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_detalle;

		ls_detalle      = ab_param ? "fEntidadesDetalle" : "fEntidadesModificarDetalle";
		lb_focus        = true;
		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			AccEntidadExterna laee_entidadExterna;

			laee_entidadExterna = getEntidad();

			if(laee_entidadExterna != null)
			{
				String ls_idPais;
				String ls_idDepartamento;
				String ls_idMunicipio;

				ls_idMunicipio        = laee_entidadExterna.getIdMunicipio();
				ls_idDepartamento     = laee_entidadExterna.getIdDepartamento();
				ls_idPais             = laee_entidadExterna.getIdPais();

				{
					String ls_idTipoDocumento;

					ls_idTipoDocumento     = laee_entidadExterna.getIdDocumentoTipo();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":idTipoDocumento", lfc_context, ls_idTipoDocumento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					String ls_numeroDocumento;

					ls_numeroDocumento     = laee_entidadExterna.getNumeroDocumento();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":numeroDocumento", lfc_context, ls_numeroDocumento, lb_focus
						);

					if(!StringUtils.isValidString(ls_numeroDocumento))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);
				}

				{
					String ls_nombre;

					ls_nombre     = laee_entidadExterna.getNombre();

					lb_focus = validateStyles(":" + ls_detalle + ":nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_idActividadEconomica;

					ls_idActividadEconomica     = laee_entidadExterna.getIdActividadEconomica();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":idActividadEconomica", lfc_context, ls_idActividadEconomica, lb_focus
						);

					if(!StringUtils.isValidString(ls_idActividadEconomica))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVIDAD_ECONOMICA);
				}

				{
					String ls_idTipoOficina;

					ls_idTipoOficina     = laee_entidadExterna.getIdTipoOficina();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":idTipoOficina", lfc_context, ls_idTipoOficina, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoOficina))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_OFICINA);
				}

				{
					String ls_idTipoEntidad;

					ls_idTipoEntidad     = laee_entidadExterna.getIdTipoEntidad();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":idTipoEntidad", lfc_context, ls_idTipoEntidad, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoEntidad))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_ENTIDAD);
				}

				{
					String ls_entidadExenta;

					ls_entidadExenta     = laee_entidadExterna.getEntidadExenta();

					lb_focus = validateStyles(
						    ":" + ls_detalle + ":entidadExenta", lfc_context, ls_entidadExenta, lb_focus
						);

					if(!StringUtils.isValidString(ls_entidadExenta))
						throw new B2BException(ErrorKeys.ERROR_SIN_ENTIDAD_EXENTA);
				}

				{
					if(StringUtils.isValidString(ls_idPais))
					{
						if(StringUtils.isValidString(ls_idDepartamento))
						{
							if(!StringUtils.isValidString(ls_idMunicipio))
							{
								lb_focus = validateStyles(
									    ":" + ls_detalle + ":idMunicipio", lfc_context, ls_idMunicipio, lb_focus
									);

								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO_VALIDO);
							}
						}
						else
						{
							lb_focus = validateStyles(
								    ":" + ls_detalle + ":idDepartamento", lfc_context, ls_idDepartamento, lb_focus
								);

							throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPARTAMENTO);
						}
					}
					else
					{
						lb_focus = validateStyles(":" + ls_detalle + ":idPais", lfc_context, ls_idPais, lb_focus);

						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS);
					}
				}

				{
					String ls_indicativoTelefono;
					String ls_numeroTelefono;

					ls_numeroTelefono         = laee_entidadExterna.getTelefono();
					ls_indicativoTelefono     = laee_entidadExterna.getIndicativoTelefonico();

					if(StringUtils.isValidString(ls_indicativoTelefono) || StringUtils.isValidString(ls_numeroTelefono))
					{
						if(!ls_indicativoTelefono.equalsIgnoreCase(ls_idPais))
						{
							lb_focus = validateStyles(
								    ":" + ls_detalle + ":idItIndicativo", lfc_context, ls_indicativoTelefono, lb_focus
								);

							throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO_TELEFONICO);
						}
						else
						{
							lb_focus = validateStyles(
								    ":" + ls_detalle + ":telefono", lfc_context, ls_numeroTelefono, lb_focus
								);

							if(!StringUtils.isValidString(ls_numeroTelefono))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO);
						}
					}
				}

				{
					String ls_activo;

					ls_activo     = laee_entidadExterna.getActivo();

					lb_focus = validateStyles(":" + ls_detalle + ":idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				Collection<Persona> lcp_persona;

				lcp_persona = ab_param ? getPersonaAsociar() : getPersonaEntidad();

				if(ab_param)
					ipr_parameterRemote.salvarEntidades(
					    laee_entidadExterna, lcp_persona, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				else
					ipr_parameterRemote.salvarEntidadesModificadas(
					    laee_entidadExterna, lcp_persona, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();
					lbr_beanReference.setEntidadExterna(null);
					lbr_beanReference.setTipoEntidadExterna(null);
				}

				ls_result = NavegacionCommon.ENTIDADES;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

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

				clear();
			}
		}

		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);

			setPersonaExiste(false);
			setTablaPersona(false);

			PrimeFaces.current().ajax().update(ls_detalle);
			PrimeFaces.current().ajax().update(ls_detalle + ":globalMsg");
		}

		return ls_result;
	}
}
