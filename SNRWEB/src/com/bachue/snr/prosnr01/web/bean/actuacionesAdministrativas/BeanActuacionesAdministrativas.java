package com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanActuacionesAdministrativas.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanActuacionesAdministrativas")
public class BeanActuacionesAdministrativas extends BeanRegistro implements Serializable
{
	private static final long serialVersionUID = 3377071060813406006L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanActuacionesAdministrativas.class);

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fActuacionesAdministrativas";

	/** Constante CS_ID_GROWL. */
	private static final String CS_ID_GROWL = CS_ID_FORM + ":globalMsg";

	/** Atributo laar_actuacionesAdministrativasRemote */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Atributo icl_listadoDias */
	private Collection<Long> icl_listadoDias;

	/** Atributo icpn_personaNotificacion */
	private Collection<PersonaNotificacion> icpn_personaNotificacion;

	/** Atributo icrn_resultadosNotificacion */
	private Collection<PersonaNotificacion> icrn_resultadosNotificacion;

	/** Atributo idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Atributo ier_entregaRemote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Atributo is_oficiosTexto */
	private OficiosTexto is_oficiosTexto;

	/** Atributo ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Atributo ip_personaCargada */
	private Persona ip_personaCargada;

	/** Atributo irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Atributo irr_registroRemote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc_zip. */
	private StreamedContent isc_zip;

	/** Atributo is_idSolicitud */
	private String is_idSolicitud;

	/** Atributo is_idTurno */
	private String is_idTurno;

	/** Atributo is_idTurnoHistoria */
	private String is_idTurnoHistoria;

	/** Atributo is_observaciones */
	private String is_observaciones;

	/** Atributo il_idMotivo */
	private String is_requiereTercerosIndeterminados;

	/** Atributo is_tipoArchivo */
	private String is_tipoArchivo;

	/** Atributo iaa_actuacionesAdministrativas */
	private TagPlantillaResolucion iaa_actuacionesAdministrativas;

	/** Atributo iba_archivo */
	private byte[] iba_archivo;

	/** Atributo ib_documentosEnviados */
	private boolean ib_documentosEnviados;

	/** Propiedad ib etapa 460 */
	private boolean ib_etapa460;

	/** Atributo ib_mostrarConsultarInteresadoInter */
	private boolean ib_mostrarConsultarInteresadoInter;

	/** Propiedad is mostrarDatosPersona */
	private boolean ib_mostrarDatosPersona;

	/** Atributo ib_mostrarDescargarZip */
	private boolean ib_mostrarDescargarZip;

	/** Atributo ib_mostrarEnviarResponsable */
	private boolean ib_mostrarEnviarResponsable;

	/** Propiedad is mostrar panel consulta*/
	private boolean ib_mostrarPanelConsulta;

	/** Atributo ib_mostrarSolicitudDocumentacion */
	private boolean ib_mostrarSolicitudDocumentacion;

	/** Atributo ib_recursos */
	private boolean ib_recursos;

	/** Atributo il_idMotivo */
	private long il_idMotivo;

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aaa_aa Argumento encargado de modificar la propiedad.
	 */
	public void setActuacionesAdministrativas(TagPlantillaResolucion aaa_aa)
	{
		iaa_actuacionesAdministrativas         = aaa_aa;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public TagPlantillaResolucion getActuacionesAdministrativas()
	{
		if(iaa_actuacionesAdministrativas == null)
			iaa_actuacionesAdministrativas = new TagPlantillaResolucion();

		return iaa_actuacionesAdministrativas;
	}

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param aba_ba Argumento encargado de modificar la propiedad.
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo = aba_ba;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad etapa460 por etapa460
	 */
	public void setEtapa460(boolean ab_b)
	{
		ib_etapa460 = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad etapa460
	 */
	public boolean isEtapa460()
	{
		return ib_etapa460;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param al_l Argumento encargado de modificar la propiedad.
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de imagen documento.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		return idsc_imagenDocumento;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acl_cl Argumento encargado de modificar la propiedad.
	 */
	public void setListadoDias(Collection<Long> acl_cl)
	{
		icl_listadoDias = acl_cl;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<Long> getListadoDias()
	{
		return icl_listadoDias;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarConsultarInteresadoInter(boolean ab_b)
	{
		ib_mostrarConsultarInteresadoInter = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarConsultarInteresadoInter()
	{
		return ib_mostrarConsultarInteresadoInter;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b valor de la propiedad.
	 */
	public void setMostrarDatosPersona(boolean ab_b)
	{
		ib_mostrarDatosPersona = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isMostrarDatosPersona()
	{
		return ib_mostrarDatosPersona;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarDescargarZip(boolean ab_b)
	{
		ib_mostrarDescargarZip = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarDescargarZip()
	{
		return ib_mostrarDescargarZip;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarEnviarResponsable(boolean ab_b)
	{
		ib_mostrarEnviarResponsable = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarEnviarResponsable()
	{
		return ib_mostrarEnviarResponsable;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b valor de la propiedad.
	 */
	public void setMostrarPanelConsulta(boolean ab_b)
	{
		ib_mostrarPanelConsulta = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isMostrarPanelConsulta()
	{
		return ib_mostrarPanelConsulta;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarSolicitudDocumentacion(boolean ab_b)
	{
		ib_mostrarSolicitudDocumentacion = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarSolicitudDocumentacion()
	{
		return ib_mostrarSolicitudDocumentacion;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setOficiosTexto(OficiosTexto as_s)
	{
		is_oficiosTexto = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public OficiosTexto getOficiosTexto()
	{
		if(is_oficiosTexto == null)
			is_oficiosTexto = new OficiosTexto();

		return is_oficiosTexto;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ap_p Argumento encargado de modificar la propiedad.
	 */
	public void setPersonaCargada(Persona ap_p)
	{
		ip_personaCargada = ap_p;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Persona getPersonaCargada()
	{
		if(ip_personaCargada == null)
			ip_personaCargada = new Persona();

		return ip_personaCargada;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acpn_pn Argumento encargado de modificar la propiedad.
	 */
	public void setPersonaNotificacion(Collection<PersonaNotificacion> acpn_pn)
	{
		icpn_personaNotificacion = acpn_pn;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<PersonaNotificacion> getPersonaNotificacion()
	{
		return icpn_personaNotificacion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ab_b modifica el valor de la propiedad.
	 */
	public void setRecursos(boolean ab_b)
	{
		ib_recursos = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isRecursos()
	{
		return ib_recursos;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setRequiereTercerosIndeterminados(String as_s)
	{
		is_requiereTercerosIndeterminados = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getRequiereTercerosIndeterminados()
	{
		return is_requiereTercerosIndeterminados;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param acrn_crn Argumento encargado de modificar la propiedad.
	 */
	public void setResultadosNotificacion(Collection<PersonaNotificacion> acrn_crn)
	{
		icrn_resultadosNotificacion = acrn_crn;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<PersonaNotificacion> getResultadosNotificacion()
	{
		return icrn_resultadosNotificacion;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param asc_sc Argumento encargado de modificar la propiedad.
	 */
	public void setZip(StreamedContent asc_sc)
	{
		isc_zip = asc_sc;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public StreamedContent getZip()
	{
		return isc_zip;
	}

	/**
	 * Adds the tipo documental.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void addTipoDocumental()
	    throws B2BException
	{
		Collection<TipoDocumental> lctau_ctd;
		TipoDocumental             lo_td;

		lo_td         = new TipoDocumental();
		lctau_ctd     = getTiposDocumentales();

		if(!CollectionUtils.isValidCollection(lctau_ctd))
			lctau_ctd = new ArrayList<TipoDocumental>();

		lo_td.setAgregadoPantalla(true);
		lctau_ctd.add(lo_td);

		setTiposDocumentales(lctau_ctd);

		addMessage(MessagesKeys.REGISTRO_INSERTADO);
		actualizarComponente(CS_ID_GROWL);
	}

	/**
	 * Metodo encargado de agregar interesado o interviniente a la tabla de resultados notficación.
	 */
	public void agregarAResultadosNotificacion()
	{
		try
		{
			Persona lp_personaCargada;

			lp_personaCargada = getPersonaCargada();

			if(lp_personaCargada != null)
			{
				Collection<PersonaNotificacion> lcpn_resultadosNotificacion;
				String                          ls_idPersonaCargada;
				String                          ls_tipoDocumento;
				String                          ls_destinatario;
				int                             li_identificador;
				boolean                         lb_existe;

				lcpn_resultadosNotificacion     = getResultadosNotificacion();
				ls_tipoDocumento                = lp_personaCargada.getIdDocumentoTipo();
				ls_idPersonaCargada             = lp_personaCargada.getIdPersona();
				li_identificador                = 1;
				lb_existe                       = false;

				ls_destinatario = (StringUtils.isValidString(ls_tipoDocumento)
						&& ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					? lp_personaCargada.getRazonSocial()
					: (ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.AUTO) ? null
					                                                                 : lp_personaCargada
						.getNombreCompleto());

				if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
				{
					Iterator<PersonaNotificacion> lipn_iterator;

					lipn_iterator = lcpn_resultadosNotificacion.iterator();

					while(lipn_iterator.hasNext() && !lb_existe)
					{
						PersonaNotificacion lpn_iterador;

						lpn_iterador = lipn_iterator.next();

						if(lpn_iterador != null)
						{
							String ls_idPersonaIterada;

							ls_idPersonaIterada     = lpn_iterador.getIdPersona();

							lb_existe = StringUtils.isValidString(ls_idPersonaCargada)
									&& StringUtils.isValidString(ls_idPersonaIterada)
									&& ls_idPersonaCargada.equalsIgnoreCase(ls_idPersonaIterada);

							li_identificador++;
						}
					}
				}
				else
					lcpn_resultadosNotificacion = new ArrayList<PersonaNotificacion>();

				if(lb_existe)
				{
					Object[] loa_object;

					loa_object     = new String[1];

					loa_object[0] = StringUtils.isValidString(ls_destinatario) ? ls_destinatario
						                                                       : IdentificadoresCommon.INTERVINIENTE;

					throw new B2BException(ErrorKeys.ERROR_INTERESADO_INTERVINIENTE_YA_EXISTE, loa_object);
				}

				{
					PersonaNotificacion lpn_personaNotificacion;

					lpn_personaNotificacion = new PersonaNotificacion();

					lpn_personaNotificacion.setIdentificador(li_identificador);
					lpn_personaNotificacion.setIdPersona(ls_idPersonaCargada);
					lpn_personaNotificacion.setDocumento(lp_personaCargada.getNumeroDocumento());
					lpn_personaNotificacion.setDestinatario(ls_destinatario);
					lpn_personaNotificacion.setTipoDocumento(ls_tipoDocumento);

					{
						Solicitud ls_solicitud;

						ls_solicitud = getSolicitud();

						if(ls_solicitud != null)
						{
							CalidadSolicitante lcs_calidadSolicitante;

							lcs_calidadSolicitante = new CalidadSolicitante();

							lcs_calidadSolicitante.setIdCalidadSolicitante(ls_solicitud.getIdCalidadSolicitante());

							lcs_calidadSolicitante = ipr_parameterRemote.findCalidadSolicitanteById(
								    lcs_calidadSolicitante
								);

							if(lcs_calidadSolicitante != null)
								lpn_personaNotificacion.setCalidadEnQueActua(lcs_calidadSolicitante.getNombre());
						}
					}

					lcpn_resultadosNotificacion.add(lpn_personaNotificacion);

					setResultadosNotificacion(lcpn_resultadosNotificacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarAResultadosNotificacion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de agregar interviniente a resultados de notificación.
	 */
	public void agregarIntervinienteResultadoNotificacion()
	{
		agregarIntervinienteResultadoNotificacion(CS_ID_FORM);
	}

	/**
	 * Metodo encargado de agregar interviniente a resultados de notificación.
	 *
	 * @param as_idForm Argumento de tipo <code>String</code> que contiene el id del formulario a actualizar.
	 */
	public void agregarIntervinienteResultadoNotificacion(String as_idForm)
	{
		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				boolean   lb_personaVinculada;
				Solicitud lso_solicitud;

				lb_personaVinculada     = isRecursos() && !isMostrarPanelConsulta();
				lso_solicitud           = lb_personaVinculada ? lp_persona.getSolicitud() : getSolicitud();

				limpiarEstilosDatosInteresado(
				    as_idForm + IdentificadoresCommon.DOS_PUNTOS, FacesContext.getCurrentInstance(), true, false,
				    lso_solicitud
				);

				BeanDireccion lbd_beanDireccion;
				Registro      lr_registro;

				lbd_beanDireccion     = getBeanDireccion();
				lr_registro           = new Registro();

				lr_registro.setPersona(lp_persona);
				lr_registro.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lr_registro.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lr_registro.setTelefonoFijo(getTelefonoFijo());
				lr_registro.setTelefonoMovil(getTelefonoMovil());
				lr_registro.setPersonaCorreoElectronico(getCorreoElectronico());
				lr_registro.setPersonaVinculada(lb_personaVinculada);

				if(lso_solicitud != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = new Solicitud();

					ls_solicitud.setIdSolicitud(lso_solicitud.getIdSolicitud());
					ls_solicitud.setIdProceso(lso_solicitud.getIdProceso());
					ls_solicitud.setIdAutorizacionNotificacion(lso_solicitud.getIdAutorizacionNotificacion());
					ls_solicitud.setIdAutorizacionComunicacion(lso_solicitud.getIdAutorizacionComunicacion());
					ls_solicitud.setIdCalidadSolicitante(lso_solicitud.getIdCalidadSolicitante());
					ls_solicitud.setParticipaInterviniente(lso_solicitud.getParticipaInterviniente());
					ls_solicitud.setEntidadExenta(lso_solicitud.getEntidadExenta());

					lr_registro.setSolicitud(ls_solicitud);
				}

				boolean lb_insertarPersona;
				boolean lb_editarPorNit;
				boolean lb_editarPorNormal;

				lb_insertarPersona     = false;
				lb_editarPorNit        = isAccionBtnEditPerNit();
				lb_editarPorNormal     = isAccionBtnEditPerNormal();

				if(lb_editarPorNit || lb_editarPorNormal)
				{
					if(lb_editarPorNormal)
					{
						String ls_primerNombreOri;
						String ls_segundoNombreOri;
						String ls_primerApellidoOri;
						String ls_segundoApellidoOri;
						String ls_primerNombreMod;
						String ls_segundoNombreMod;
						String ls_primerApellidoMod;
						String ls_segundoApellidoMod;

						ls_primerNombreOri        = StringUtils.getStringNotNull(getPrimerNombreTemp());
						ls_segundoNombreOri       = StringUtils.getStringNotNull(getSegundoNombreTemp());
						ls_primerApellidoOri      = StringUtils.getStringNotNull(getPrimerApellidoTemp());
						ls_segundoApellidoOri     = StringUtils.getStringNotNull(getSegundoApellidoTemp());
						ls_primerNombreMod        = StringUtils.getStringNotNull(lp_persona.getPrimerNombre());
						ls_segundoNombreMod       = StringUtils.getStringNotNull(lp_persona.getSegundoNombre());
						ls_primerApellidoMod      = StringUtils.getStringNotNull(lp_persona.getPrimerApellido());
						ls_segundoApellidoMod     = StringUtils.getStringNotNull(lp_persona.getSegundoApellido());

						if(
						    !ls_primerNombreOri.equalsIgnoreCase(ls_primerNombreMod)
							    || !ls_segundoNombreOri.equalsIgnoreCase(ls_segundoNombreMod)
							    || !ls_primerApellidoOri.equalsIgnoreCase(ls_primerApellidoMod)
							    || !ls_segundoApellidoOri.equalsIgnoreCase(ls_segundoApellidoMod)
						)
							lb_insertarPersona = true;
					}
					else if(lb_editarPorNit)
					{
						String ls_razonSocialOri;
						String ls_razonSocialMod;

						ls_razonSocialOri     = StringUtils.getStringNotNull(getRazonSocialTemp());
						ls_razonSocialMod     = StringUtils.getStringNotNull(lp_persona.getRazonSocial());

						if(!ls_razonSocialOri.equalsIgnoreCase(ls_razonSocialMod))
							lb_insertarPersona = true;
					}
				}

				{
					Solicitud ls_solicitud;

					ls_solicitud = lr_registro.getSolicitud();

					if(ls_solicitud != null)
					{
						Persona lp_personaTemp;

						lp_personaTemp = lr_registro.getPersona();

						ls_solicitud.setIdPersona((lp_personaTemp != null) ? lp_personaTemp.getIdPersona() : null);
					}

					lr_registro.setPersonaCargada(lb_insertarPersona);
					lr_registro.setDireccionResidenciaCargada(isDeshabilitarResidencia());
					lr_registro.setDireccionCorrespondenciaCargada(isDeshabilitarCorrespondencia());
					lr_registro.setTelefonoFijoCargado(isDeshabilitarTelFijo());
					lr_registro.setTelefonoMovilCargado(isDeshabilitarTelMovil());
					lr_registro.setCorreoCargado(isDeshabilitarCorreo());
					lr_registro.setIpCreacion(getRemoteIpAddress());
					lr_registro.setIpModificacion(getRemoteIpAddress());
					lr_registro.setIdTurno(getIdTurno());
				}

				if(isProcesoCertificados())
					lr_registro.setProcesoCertificados(true);
				else if(isCorreccionesCalificacion())
					lr_registro.setProcesoCorrecciones(true);
				else if(isProcesoDevoluciones())
				{
					String ls_intervieneTramiteDevDinero;
					String ls_titularCuentaDevDinero;

					ls_intervieneTramiteDevDinero     = getIntervieneTramiteDevDinero();
					ls_titularCuentaDevDinero         = getTitularCuentaDevDinero();

					if(
					    StringUtils.isValidString(ls_intervieneTramiteDevDinero)
						    && StringUtils.isValidString(ls_titularCuentaDevDinero)
					)
					{
						DevolucionDinero ldd_devolucionDinero;

						ldd_devolucionDinero = new DevolucionDinero();

						ldd_devolucionDinero.setIntervieneTramite(ls_intervieneTramiteDevDinero);
						ldd_devolucionDinero.setTitularCuenta(ls_titularCuentaDevDinero);

						if(ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.N))
						{
							Persona lp_intervinienteDevDinero;

							lp_intervinienteDevDinero = getIntervinienteDevDinero();

							if(lp_intervinienteDevDinero != null)
							{
								String ls_idPersona;

								ls_idPersona = lp_intervinienteDevDinero.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
									ldd_devolucionDinero.setIdPersonaInterviniente(ls_idPersona);
							}
						}

						lr_registro.setProcesoDevoluciones(true);
						lr_registro.setDevolucionDinero(ldd_devolucionDinero);
					}
				}

				{
					Collection<PersonaNotificacion> lcpn_personaNotificacion;
					int                             li_identificador;

					lcpn_personaNotificacion     = getResultadosNotificacion();
					li_identificador             = 1;

					if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
					{
						for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
						{
							if(lpn_iterador != null)
								li_identificador++;
						}
					}
					else
						lcpn_personaNotificacion = new ArrayList<PersonaNotificacion>();

					{
						PersonaNotificacion lpn_personaNotificacion;

						lpn_personaNotificacion = new PersonaNotificacion();

						lpn_personaNotificacion.setIdentificador(li_identificador);
						lpn_personaNotificacion.setRegistro(lr_registro);
						lpn_personaNotificacion.setIdPersona(lp_persona.getIdPersona());
						lpn_personaNotificacion.setDocumento(lp_persona.getNumeroDocumento());

						{
							String ls_tipoDocumento;

							ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

							if(StringUtils.isValidString(ls_tipoDocumento))
							{
								String ls_destinatario;

								ls_destinatario = ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
									? lp_persona.getRazonSocial()
									: (ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.AUTO) ? null
									                                                                 : lp_persona
										.getNombreCompleto());

								lpn_personaNotificacion.setDestinatario(ls_destinatario);
								lpn_personaNotificacion.setTipoDocumento(ls_tipoDocumento);
							}
						}

						{
							Solicitud ls_solicitud;

							ls_solicitud = getSolicitud();

							if(ls_solicitud != null)
							{
								CalidadSolicitante lcs_calidadSolicitante;

								lcs_calidadSolicitante = new CalidadSolicitante();

								lcs_calidadSolicitante.setIdCalidadSolicitante(ls_solicitud.getIdCalidadSolicitante());

								lcs_calidadSolicitante = ipr_parameterRemote.findCalidadSolicitanteById(
									    lcs_calidadSolicitante
									);

								if(lcs_calidadSolicitante != null)
									lpn_personaNotificacion.setCalidadEnQueActua(lcs_calidadSolicitante.getNombre());
							}
						}

						lcpn_personaNotificacion.add(lpn_personaNotificacion);
					}

					setResultadosNotificacion(lcpn_personaNotificacion);

					limpiarRegistro(false);
					setMostrarLimpiarDatos(true);
					cargarMediosNotComActuacionesAdmin(false);
					cargarMediosNotComInter(false);
					esconderPanels();
					setMostrarConsultarInteresadoInter(false);
					setMostrarDatosPersona(false);
					setMostrarPanelConsulta(false);

					buscarPersonasAsociadasRecursos();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarIntervinienteResultadoNotificacion", lb2be_e);

			addMessage(lb2be_e);

			actualizarComponente(as_idForm + ":globalMsg");
		}
	}

	/**
	 * Metodo encargado de agregar un tercero determinado para actuaciones administrativas.
	 */
	public void agregarTerceroDeterminado()
	{
		Collection<PersonaNotificacion> lcpn_personaNotificacion;
		int                             li_identificador;

		lcpn_personaNotificacion     = getPersonaNotificacion();
		li_identificador             = 1;

		if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
		{
			for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
			{
				if(lpn_iterador != null)
					li_identificador++;
			}
		}
		else
			lcpn_personaNotificacion = new ArrayList<PersonaNotificacion>();

		{
			PersonaNotificacion lpn_personaNotificacion;

			lpn_personaNotificacion = new PersonaNotificacion();

			lpn_personaNotificacion.setIdentificador(li_identificador);

			lcpn_personaNotificacion.add(lpn_personaNotificacion);
		}

		setPersonaNotificacion(lcpn_personaNotificacion);
	}

	/**
	 * Metodo encargado de agregar un tercero indeterminado para actuaciones administrativas.
	 */
	public void agregarTerceroIndeterminado()
	{
		String ls_requiereTerceroIndeterminado;

		ls_requiereTerceroIndeterminado = getRequiereTercerosIndeterminados();

		if(
		    StringUtils.isValidString(ls_requiereTerceroIndeterminado)
			    && ls_requiereTerceroIndeterminado.equalsIgnoreCase(EstadoCommon.S)
		)
		{
			Collection<PersonaNotificacion> lcpn_resultadosNotificacion;
			int                             li_identificador;
			boolean                         lb_existe;

			lcpn_resultadosNotificacion     = getResultadosNotificacion();
			li_identificador                = 1;
			lb_existe                       = false;

			if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
			{
				Iterator<PersonaNotificacion> lipn_iterator;

				lipn_iterator = lcpn_resultadosNotificacion.iterator();

				while(lipn_iterator.hasNext() && !lb_existe)
				{
					PersonaNotificacion lpn_iterador;

					lpn_iterador = lipn_iterator.next();

					if(lpn_iterador != null)
					{
						String ls_terceroIndeterminado;

						ls_terceroIndeterminado     = lpn_iterador.getTerceroIndeterminado();

						lb_existe = StringUtils.isValidString(ls_terceroIndeterminado)
								&& ls_terceroIndeterminado.equalsIgnoreCase(EstadoCommon.S);

						li_identificador++;
					}
				}
			}
			else
				lcpn_resultadosNotificacion = new ArrayList<PersonaNotificacion>();

			if(!lb_existe)
			{
				PersonaNotificacion lpn_personaNotificacion;

				lpn_personaNotificacion = new PersonaNotificacion();

				lpn_personaNotificacion.setIdentificador(li_identificador);
				lpn_personaNotificacion.setDestinatario(CalidadSolicitanteCommon.TERCERO_INDETERMINADO);
				lpn_personaNotificacion.setTerceroIndeterminado(ls_requiereTerceroIndeterminado);

				lcpn_resultadosNotificacion.add(lpn_personaNotificacion);

				setResultadosNotificacion(lcpn_resultadosNotificacion);
			}
		}
	}

	/**
	 * Metodo encargado de agregar un tercero determinado para actuaciones administrativas.
	 *
	 * @param apn_resultadoNotificacion Argumento de tipo <code>PersonaNotificacion</code> que contiene los datos a agregar.
	 */
	public void agregarTerceroResultadoNotificacion(PersonaNotificacion apn_resultadoNotificacion)
	{
		if(apn_resultadoNotificacion != null)
		{
			try
			{
				{
					String ls_destinatario;

					ls_destinatario = apn_resultadoNotificacion.getDestinatario();

					if(!StringUtils.isValidString(ls_destinatario))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESTINATARIO_VALIDO);
				}

				{
					String ls_direccion;

					ls_direccion = apn_resultadoNotificacion.getDireccion();

					if(!StringUtils.isValidString(ls_direccion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_VALIDA);
				}

				{
					String ls_idDepartamento;

					ls_idDepartamento = apn_resultadoNotificacion.getIdDepartamento();

					if(!StringUtils.isValidString(ls_idDepartamento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					String ls_idMunicipio;

					ls_idMunicipio = apn_resultadoNotificacion.getIdMunicipio();

					if(!StringUtils.isValidString(ls_idMunicipio))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}

				{
					Collection<PersonaNotificacion> lcpn_resultadosNotificacion;
					int                             li_identificador;
					boolean                         lb_existe;

					lcpn_resultadosNotificacion     = getResultadosNotificacion();
					li_identificador                = 1;
					lb_existe                       = false;

					if(CollectionUtils.isValidCollection(lcpn_resultadosNotificacion))
					{
						Iterator<PersonaNotificacion> lipn_iterator;

						lipn_iterator = lcpn_resultadosNotificacion.iterator();

						while(lipn_iterator.hasNext() && !lb_existe)
						{
							PersonaNotificacion lpn_iterador;

							lpn_iterador = lipn_iterator.next();

							if(lpn_iterador != null)
							{
								String ls_destinatario;
								String ls_destinatarioCargado;
								String ls_direccion;
								String ls_direccionCargada;
								String ls_idDepartamento;
								String ls_idDepartamentoCargado;
								String ls_idMunicipio;
								String ls_idMunicipioCargado;

								ls_destinatario              = lpn_iterador.getDestinatario();
								ls_destinatarioCargado       = apn_resultadoNotificacion.getDestinatario();
								ls_direccion                 = lpn_iterador.getDireccion();
								ls_direccionCargada          = apn_resultadoNotificacion.getDireccion();
								ls_idDepartamento            = lpn_iterador.getIdDepartamento();
								ls_idDepartamentoCargado     = apn_resultadoNotificacion.getIdDepartamento();
								ls_idMunicipio               = lpn_iterador.getIdMunicipio();
								ls_idMunicipioCargado        = apn_resultadoNotificacion.getIdMunicipio();

								lb_existe = StringUtils.isValidString(ls_destinatario)
										&& StringUtils.isValidString(ls_destinatarioCargado)
										&& ls_destinatario.equalsIgnoreCase(ls_destinatarioCargado)
										&& StringUtils.isValidString(ls_direccion)
										&& StringUtils.isValidString(ls_direccionCargada)
										&& ls_direccion.equalsIgnoreCase(ls_direccionCargada)
										&& StringUtils.isValidString(ls_idDepartamento)
										&& StringUtils.isValidString(ls_idDepartamentoCargado)
										&& ls_idDepartamento.equalsIgnoreCase(ls_idDepartamentoCargado)
										&& StringUtils.isValidString(ls_idMunicipio)
										&& StringUtils.isValidString(ls_idMunicipioCargado)
										&& ls_idMunicipio.equalsIgnoreCase(ls_idMunicipioCargado);

								li_identificador++;
							}
						}
					}
					else
						lcpn_resultadosNotificacion = new ArrayList<PersonaNotificacion>();

					if(lb_existe)
					{
						Object[] loa_object;

						loa_object     = new String[1];

						loa_object[0] = CalidadSolicitanteCommon.TERCERO_DETERMINADO;

						throw new B2BException(ErrorKeys.ERROR_INTERESADO_INTERVINIENTE_YA_EXISTE, loa_object);
					}

					{
						PersonaNotificacion lpn_personaNotificacion;

						lpn_personaNotificacion = new PersonaNotificacion();

						lpn_personaNotificacion.setIdentificador(li_identificador);
						lpn_personaNotificacion.setDestinatario(apn_resultadoNotificacion.getDestinatario());
						lpn_personaNotificacion.setDireccion(apn_resultadoNotificacion.getDireccion());
						lpn_personaNotificacion.setIdDepartamento(apn_resultadoNotificacion.getIdDepartamento());
						lpn_personaNotificacion.setIdMunicipio(apn_resultadoNotificacion.getIdMunicipio());
						lpn_personaNotificacion.setCalidadEnQueActua(CalidadSolicitanteCommon.TERCERO_DETERMINADO);

						lcpn_resultadosNotificacion.add(lpn_personaNotificacion);
					}

					setResultadosNotificacion(lcpn_resultadosNotificacion);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("agregarTerceroResultadoNotificacion", lb2be_e);

				addMessage(lb2be_e);
				actualizarComponente(CS_ID_GROWL);
			}
		}
	}

	/**
	 * Método encargado de consultar las personas asociadas a una solicitud con proceso 47.
	 */
	public void buscarPersonasAsociadasRecursos()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
				setRegistroDatosConsultados(
				    iaar_actuacionesAdministrativasRemote.buscarPersonasAsociadasRecursos(
				        ls_solicitud.getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarPersonasAsociadasRecursos", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 * @throws B2BException
	 */
	public void cargarDatosActuacionesAdministrativas()
	    throws B2BException
	{
		cargarDatosActuacionesAdministrativas(false);
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @param ab_recurso Argumento de tipo <code>boolean</code> que determina si es recurso o no.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosActuacionesAdministrativas(boolean ab_recurso)
	    throws B2BException
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;
			String                 ls_idTurno;

			laa_actuacionesAdministrativas     = new TagPlantillaResolucion();
			ls_idTurno                         = getIdTurno();

			laa_actuacionesAdministrativas.setIdTurno(ls_idTurno);
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setIdMotivo(getIdMotivo());
			laa_actuacionesAdministrativas.setRecurso(ab_recurso);

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.cargarDatosActuacionesAdministrativas(
				    laa_actuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
			{
				byte[] lba_archivo;

				lba_archivo = laa_actuacionesAdministrativas.getArchivo();

				{
					BeanDireccion lb_beanDireccion;

					lb_beanDireccion = (BeanDireccion)obtenerInstanciaBean(
						    BeanDireccion.class, BeanNameCommon.BEAN_DIRECCION
						);

					lb_beanDireccion.limpiarBeanDireccion();
					lb_beanDireccion.setForm(CS_ID_FORM);
				}

				setIdProceso(ProcesoCommon.ID_PROCESO_3);
				limpiarRegistro();
				setMostrarLimpiarDatos(true);
				cargarMediosNotComActuacionesAdmin(false);
				cargarMediosNotComInter(false);
				esconderPanels();

				setSolicitud(laa_actuacionesAdministrativas.getSolicitud());
				setPersonaCargada(laa_actuacionesAdministrativas.getPersona());

				{
					Collection<PersonaNotificacion> lcpn_personaNotificacion;
					Collection<PersonaNotificacion> lcpn_tmp;
					int                             li_identificador;

					lcpn_personaNotificacion     = laa_actuacionesAdministrativas.getResultadosNotificacion();
					lcpn_tmp                     = new ArrayList<PersonaNotificacion>();
					li_identificador             = 1;

					if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
					{
						for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
						{
							if(lpn_iterador != null)
							{
								lpn_iterador.setIdentificador(li_identificador++);

								lcpn_tmp.add(lpn_iterador);
							}
						}
					}
					else
						lcpn_personaNotificacion = new ArrayList<PersonaNotificacion>();

					setResultadosNotificacion(lcpn_tmp);
				}

				setOficiosTexto(laa_actuacionesAdministrativas.getOficiosTexto());
				setTipoArchivo(laa_actuacionesAdministrativas.getTipoArchivo());
				setListadoDias(laa_actuacionesAdministrativas.getListadoDias());
				setIdTurno(ls_idTurno);
				setArchivo(lba_archivo);

				setImagenDocumento(
				    generarArchivosDescarga(
				        lba_archivo, TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada
	 * en la consulta por documento.
	 *
	 * @param aop_op Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 */
	public void cargarDatosPersonalesActuacionesAdmin(Persona aop_op)
	{
		cargarDatosPersonales(aop_op, TipoRecepcionCommon.ACTUACIONES_ADMINISTRATIVAS);
	}

	/**
	 * Cargar completitud documentales del turno respectivo.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void cargarInfoTiposDocumentalActuacionesAdmin()
	    throws B2BException
	{
		try
		{
			Collection<TipoDocumental>           lctd_return;
			Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

			lctd_return                     = new ArrayList<TipoDocumental>();
			lcacd_completitudDocumental     = irr_registroRemote.findCompletitudDocumentalByIdTurno(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
			{
				for(AccCompletitudDocumental lacd_tmp : lcacd_completitudDocumental)
				{
					TipoDocumental ltd_tiposDocumentales;

					ltd_tiposDocumentales = new TipoDocumental();

					if(lacd_tmp != null)
					{
						String ls_idTipoDoc;
						String ls_observaciones;
						String ls_obligatoriedadTipoDoc;

						ls_observaciones             = lacd_tmp.getObservaciones();
						ls_idTipoDoc                 = lacd_tmp.getIdTipoDocumental();
						ls_obligatoriedadTipoDoc     = lacd_tmp.getObligatorioTipoDocumental();

						if(StringUtils.isValidString(ls_idTipoDoc))
							ltd_tiposDocumentales.setIdTipoDocumental(ls_idTipoDoc);

						if(StringUtils.isValidString(ls_observaciones))
							ltd_tiposDocumentales.setObservaciones(ls_observaciones);

						if(StringUtils.isValidString(ls_obligatoriedadTipoDoc))
						{
							if(ls_obligatoriedadTipoDoc.equalsIgnoreCase(EstadoCommon.S))
								ltd_tiposDocumentales.setSeleccionado(true);
							else
								ltd_tiposDocumentales.setSeleccionado(false);
						}
					}

					lctd_return.add(ltd_tiposDocumentales);
				}
			}

			if(lctd_return.isEmpty())
				lctd_return = null;

			setTiposDocumentales(lctd_return);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public void cargarMunicipios(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			try
			{
				Collection<PersonaNotificacion> lcpn_personaNotificacion;

				lcpn_personaNotificacion = getPersonaNotificacion();

				if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
				{
					Iterator<PersonaNotificacion> lpn_iterator;
					boolean                       lb_encontrado;

					lpn_iterator      = lcpn_personaNotificacion.iterator();
					lb_encontrado     = false;

					while(lpn_iterator.hasNext() && !lb_encontrado)
					{
						PersonaNotificacion lpn_iterador;

						lpn_iterador = lpn_iterator.next();

						if(lpn_iterador != null)
						{
							int li_identificadorParametro;
							int li_identificadorIterado;

							li_identificadorParametro     = apn_parametro.getIdentificador();
							li_identificadorIterado       = lpn_iterador.getIdentificador();

							lb_encontrado = li_identificadorParametro == li_identificadorIterado;

							if(lb_encontrado)
							{
								String ls_idDepartamento;

								ls_idDepartamento = lpn_iterador.getIdDepartamento();

								if(StringUtils.isValidString(ls_idDepartamento))
								{
									Municipio lm_parametros;

									lm_parametros = new Municipio();

									lm_parametros.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
									lm_parametros.setIdDepartamento(ls_idDepartamento);

									lpn_iterador.setMunicipios(irr_referenceRemote.findMunicipios(lm_parametros));
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				actualizarComponente(CS_ID_GROWL);
			}
		}
	}

	/**
	 * Metodo encargado de limpiar las variables de sesión.
	 */
	public void clean()
	{
		setActuacionesAdministrativas(null);
		setIdSolicitud(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
		setPersonaCargada(null);
		setPersonaNotificacion(null);
		setIdMotivo(0L);
		setObservaciones(null);
		setMostrarConsultarInteresadoInter(false);
		setMostrarDescargarZip(false);
		setMostrarEnviarResponsable(false);
		setTipoArchivo(null);
		setMostrarSolicitudDocumentacion(false);
		setTiposDocumentales(null);
		setArchivo(null);
		setRecursos(false);
		setRequiereTercerosIndeterminados(null);
	}

	/**
	 * Consultar persona documento actuaciones administrativas.
	 */
	public void consultarPersonaDocumentoActuacionesAdmin()
	{
		try
		{
			consultarPersonaDocumento(true, TipoRecepcionCommon.ACTUACIONES_ADMINISTRATIVAS, CS_ID_FORM);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPersonaDocumentoActuacionesAdmin", lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de convertir días a letras.
	 */
	public void convertirDiasLetrasOficiosTexto()
	{
		OficiosTexto lot_oficiosTexto;

		lot_oficiosTexto = getOficiosTexto();

		if(lot_oficiosTexto != null)
		{
			Long ll_diasSuspension;

			ll_diasSuspension = lot_oficiosTexto.getDiasSuspension();

			if(NumericUtils.isValidLong(ll_diasSuspension))
			{
				String ls_diasLetras;

				ls_diasLetras = NumericUtils.convertirLetras(NumericUtils.getInt(ll_diasSuspension), false);

				if(StringUtils.isValidString(ls_diasLetras))
					lot_oficiosTexto.setDiasLetras(ls_diasLetras);
			}
		}
	}

	/**
	 * Método para eliminar un tipo documental.
	 *
	 * @param atd_tipoDoc Tipo documental a remover
	 */
	public void deleteTipoDocumental(com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental atd_tipoDoc)
	{
		deleteTipoDocumental(atd_tipoDoc, false);
	}

	/**
	 * Método para eliminar un tipo documental.
	 *
	 * @param atd_tipoDoc Tipo documental a remover
	 */
	public void deleteTipoDocumental(
	    com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental atd_tipoDoc, boolean ab_entrega
	)
	{
		try
		{
			Collection<TipoDocumental> lctd_tiposDocumentales;

			lctd_tiposDocumentales = getTiposDocumentales();

			if((lctd_tiposDocumentales != null) && (atd_tipoDoc != null))
			{
				if(ab_entrega && StringUtils.isValidString(atd_tipoDoc.getIdCompletitud()))
					ier_entregaRemote.eliminarTipoDocumental(
					    atd_tipoDoc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				lctd_tiposDocumentales.remove(atd_tipoDoc);
				addMessage(MessagesKeys.PROCESO_COMPLETADO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("deleteTipoDocumental", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de descargar Zip de actuaciones administrativas.
	 */
	public void descargarZipActuacionesAdmin()
	{
		byte[] lba_file;

		lba_file = getFile();

		if(lba_file != null)
		{
			setZip(
			    generarArchivosDescarga(
			        lba_file, TipoContenidoCommon.ZIP,
			        IdentificadoresCommon.ACTUACIONES_ADMINISTRATIVAS + ExtensionCommon.ZIP_PUNTO
			    )
			);

			setMostrarEnviarResponsable(true);

			actualizarComponente(CS_ID_FORM + ":opBotones");
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			boolean lb_enviados;

			lb_enviados = validarEnvioDocumentosOWCC(getIdTurnoHistoria());

			setDocumentosEnviados(lb_enviados);

			if(lb_enviados)
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");
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
	 * Metodo encargado de eliminar un tercero determinado para actuaciones administrativas.
	 */
	public void eliminarResultadoNotificacion(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			Collection<PersonaNotificacion> lcpn_personaNotificacion;

			lcpn_personaNotificacion = getResultadosNotificacion();

			if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
			{
				Collection<PersonaNotificacion> lcpn_nueva;
				int                             li_identificador;

				lcpn_nueva           = new ArrayList<PersonaNotificacion>();
				li_identificador     = 1;

				for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = apn_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcpn_nueva.add(lpn_iterador);
						}
					}
				}

				setResultadosNotificacion(lcpn_nueva);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar un tercero determinado para actuaciones administrativas.
	 */
	public void eliminarTerceroDeterminado(PersonaNotificacion apn_parametro)
	{
		if(apn_parametro != null)
		{
			Collection<PersonaNotificacion> lcpn_personaNotificacion;

			lcpn_personaNotificacion = getPersonaNotificacion();

			if(CollectionUtils.isValidCollection(lcpn_personaNotificacion))
			{
				Collection<PersonaNotificacion> lcpn_nueva;
				int                             li_identificador;

				lcpn_nueva           = new ArrayList<PersonaNotificacion>();
				li_identificador     = 1;

				for(PersonaNotificacion lpn_iterador : lcpn_personaNotificacion)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = apn_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcpn_nueva.add(lpn_iterador);
						}
					}
				}

				setPersonaNotificacion(lcpn_nueva);
			}
		}
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public String enviarAlAprobadorRechazoRecurso()
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(!isMostrarEnviarResponsable())
				throw new B2BException(ErrorKeys.ERROR_NO_HA_DOCUMENTOS_RECHAZO_RECURSO);

			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setObservaciones(getObservaciones());
				laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());

				iaar_actuacionesAdministrativasRemote.enviarAlAprobadorRechazoRecurso(
				    laa_actuacionesAdministrativas, isEtapa460(), getIdMotivo(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
			}

			{
				BeanTurnos lbt_bean;

				lbt_bean = (BeanTurnos)obtenerInstanciaBean(BeanTurnos.class, BeanNameCommon.BEAN_TURNOS);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarData();

				ls_return = NavegacionCommon.TURNOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_GROWL);
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public String enviarResponsableActuacionesAdmin()
	{
		return enviarResponsableActuacionesAdmin(false, EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS);
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 * @param ab_recurso Argumento de tipo <code>boolean</code> que determina si es recurso o no.
	 */
	public String enviarResponsableActuacionesAdmin(boolean ab_recurso, long al_idEtapa)
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(!ab_recurso && !isMostrarEnviarResponsable())
			{
				Object[] loa_object;
				String   ls_tipoArchivo;

				loa_object         = new String[1];
				ls_tipoArchivo     = StringUtils.getStringNotNull(getTipoArchivo());

				loa_object[0] = ls_tipoArchivo.replace("_", " ");

				throw new B2BException(ErrorKeys.ERROR_NO_HA_GENERADO_AUTO, loa_object);
			}

			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setObservaciones(getObservaciones());
				laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setIdEtapa(al_idEtapa);

				iaar_actuacionesAdministrativasRemote.enviarResponsableActuacionesAdmin(
				    laa_actuacionesAdministrativas, getIdMotivo(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
			}

			{
				BeanTurnos lbt_bean;

				lbt_bean = (BeanTurnos)obtenerInstanciaBean(BeanTurnos.class, BeanNameCommon.BEAN_TURNOS);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarData();

				ls_return = NavegacionCommon.TURNOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_GROWL);
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 */
	public void generarDocumentosActuacionesAdmin()
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;
			long                   ll_idMotivo;
			String                 ls_tipoArchivo;

			laa_actuacionesAdministrativas     = new TagPlantillaResolucion();
			ll_idMotivo                        = getIdMotivo();
			ls_tipoArchivo                     = StringUtils.getStringNotNull(getTipoArchivo());

			laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(getOficiosTexto()));
			laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
			laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setResultadosNotificacion(getResultadosNotificacion());
			laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
			laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.generarDocumentosActuacionesAdmin(
				    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
			{
				setFile(laa_actuacionesAdministrativas.getArchivo());
				setMostrarDescargarZip(true);

				{
					Object[] loa_object;

					loa_object     = new String[1];

					loa_object[0] = ls_tipoArchivo.replace("_", " ");

					addMessage(
					    EstadoCommon.I,
					    (ll_idMotivo == MotivoTramiteCommon.NEGACION_APERTURA)
					    ? MessagesKeys.SE_GENERO_CORRECTAMENTE_NEGACION_APERTURA_AA
					    : MessagesKeys.SE_GENERO_CORRECTAMENTE_AUTO, loa_object
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosActuacionesAdmin", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado mostrar el panel de consulta interesado o interviniente.
	 */
	public void mostrarInteresadoInterviniente()
	{
		setMostrarConsultarInteresadoInter(true);
	}

	/**
	 * Metodo encargado de retornar a la pantalla anterior.
	 *
	 * @return Regla de navegación de pantalla anterior.
	 */
	public String regresar()
	{
		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucion()
	{
		return visualizarCambiosResolucion(false);
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucion(boolean ab_recurso)
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(getOficiosTexto()));
			laa_actuacionesAdministrativas.setRecurso(ab_recurso);
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.visualizarCambiosResolucion(
				    laa_actuacionesAdministrativas, getIdMotivo(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
				setArchivo(laa_actuacionesAdministrativas.getArchivo());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(CS_ID_GROWL);

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

		setImagenDocumento(
		    generarArchivosDescarga(
		        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
		    )
		);

		return ab_recurso ? NavegacionCommon.RESOLUCION_RECURSO : NavegacionCommon.ACTUACIONES_ADMINISTRATIVAS;
	}

	/**
	 * Metodo encargado de convertir los textos capturados por pantalla.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los criterios a convertir.
	 * @return Objeto de tipo <code>OficiosTexto</code> que contiene los criterios convertidos.
	 * @throws B2BException Se genera cuando se presenta una excepcion.
	 */
	protected OficiosTexto convertirOficiosTexto(OficiosTexto aot_oficiosTexto)
	    throws B2BException
	{
		OficiosTexto lot_oficiosTexto;

		lot_oficiosTexto = null;

		try
		{
			if(aot_oficiosTexto != null)
			{
				lot_oficiosTexto = new OficiosTexto();

				lot_oficiosTexto.setEncabezado(convertirTextoParaRtf(aot_oficiosTexto.getEncabezado()));
				lot_oficiosTexto.setAntecedentes(convertirTextoParaRtf(aot_oficiosTexto.getAntecedentes()));
				lot_oficiosTexto.setConsideracion(convertirTextoParaRtf(aot_oficiosTexto.getConsideracion()));
				lot_oficiosTexto.setRazon1(convertirTextoParaRtf(aot_oficiosTexto.getRazon1()));
				lot_oficiosTexto.setRazon2(convertirTextoParaRtf(aot_oficiosTexto.getRazon2()));
				lot_oficiosTexto.setAnalisisProbatorio(convertirTextoParaRtf(aot_oficiosTexto.getAnalisisProbatorio()));
				lot_oficiosTexto.setPertinencia(convertirTextoParaRtf(aot_oficiosTexto.getPertinencia()));
				lot_oficiosTexto.setIntervencionInteresados(
				    convertirTextoParaRtf(aot_oficiosTexto.getIntervencionInteresados())
				);
				lot_oficiosTexto.setPruebasRecaudadas(convertirTextoParaRtf(aot_oficiosTexto.getPruebasRecaudadas()));
				lot_oficiosTexto.setResuelve(convertirTextoParaRtf(aot_oficiosTexto.getResuelve()));
				lot_oficiosTexto.setArgumentosRecurrente(
				    convertirTextoParaRtf(aot_oficiosTexto.getArgumentosRecurrente())
				);
				lot_oficiosTexto.setDiasSuspension(aot_oficiosTexto.getDiasSuspension());
				lot_oficiosTexto.setDiasLetras(aot_oficiosTexto.getDiasLetras());
				lot_oficiosTexto.setIdTurnoHistoria(aot_oficiosTexto.getIdTurnoHistoria());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("convertirOficiosTexto", lb2be_e);

			throw lb2be_e;
		}

		return lot_oficiosTexto;
	}
}
