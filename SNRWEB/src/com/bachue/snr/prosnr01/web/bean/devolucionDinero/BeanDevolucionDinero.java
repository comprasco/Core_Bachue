package com.bachue.snr.prosnr01.web.bean.devolucionDinero;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.devolucionesDinero.DevolucionDineroRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.ui.DevolucionDineroUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad.BeanConsultaTrazabilidad;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FlowEvent;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos la funcionalidad para el Bean de devoluciones dinero.
 *
 * @author Carlos Calderon
 */
@ManagedBean(name = "beanDevolucionDinero")
@SessionScoped
public class BeanDevolucionDinero extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2562425471392806062L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDevolucionDinero.class);

	/** Constante is_idForm. */
	public static final String is_idForm = "hfdevolucionesDinero";

	/** Constante cs_ID_GROWL. */
	public static final String cs_ID_GROWL = is_idForm + ":globalMsg";

	/** Propiedad icadd actos devolución dinero. */
	private Collection<ActoDevolucionDinero> icadd_actosDevolucionDinero;

	/** Propiedad  icadd Dinero Temporal. */
	private Collection<ActoDevolucionDinero> icadd_devDineroTemporal;

	/** Propiedad icdd persona a representar. */
	private Collection<DevolucionDinero> icdd_personaARepresentar;

	/** Propiedad icdd persona representante. */
	private Collection<DevolucionDinero> icdd_personaRepresentante;

	/** Propiedad lcp intervinientes Dev Dinero. */
	private Collection<Persona> lcp_intervinientesDevDinero;

	/** Propiedad ictr consulta trazabilidad remote. */
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad idd devolucion dinero. */
	private DevolucionDinero idd_devolucionDinero;

	/** Propiedad idd devoluciones dinero remote. */
	@EJB
	private DevolucionDineroRemote idd_devolucionesDineroRemote;

	/** Propiedad iddu_intFinalParaFlujo. */
	private DevolucionDineroUI iddu_intFinalParaFlujo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip persona titular cuenta. */
	private Persona ip_personaTitularCuenta;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad ib mostrar panel consulta interesado. */
	private boolean ib_mostrarPanelConsultaInteresado;

	/** Constante ib mostrar panel interesado. */
	private boolean ib_mostrarPanelInteresado;

	/** Propiedad ib mostrar resumen solicitud. */
	private boolean ib_mostrarResumenSolicitud;

	/** Propiedad ib titular cuenta S. */
	private boolean ib_titularCuentaS;

	/**
	 * @param acddu_ddu Modifica el valor de la propiedad actosDevolucionDinero por acddu_ddu
	 */
	public void setActosDevolucionDinero(Collection<ActoDevolucionDinero> acddu_ddu)
	{
		icadd_actosDevolucionDinero = acddu_ddu;
	}

	/**
	 * @return Retorna el valor de la propiedad actosDevolucionDinero
	 */
	public Collection<ActoDevolucionDinero> getActosDevolucionDinero()
	{
		return icadd_actosDevolucionDinero;
	}

	/**
	 * @param acadd_add Modifica el valor de la propiedad devDineroTemporal por devDineroTemporal
	 */
	public void setDevDineroTemporal(Collection<ActoDevolucionDinero> acadd_add)
	{
		icadd_devDineroTemporal = acadd_add;
	}

	/**
	 * @return Retorna el valor de la propiedad devDineroTemporal
	 */
	public Collection<ActoDevolucionDinero> getDevDineroTemporal()
	{
		return icadd_devDineroTemporal;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param add_dd modifica el valor de la propiedad.
	 */
	public void setDevolucionDinero(DevolucionDinero add_dd)
	{
		idd_devolucionDinero = add_dd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public DevolucionDinero getDevolucionDinero()
	{
		if(idd_devolucionDinero == null)
			idd_devolucionDinero = new DevolucionDinero();

		return idd_devolucionDinero;
	}

	/**
	 * @param addu_ddu Modifica el valor de la propiedad intFinalParaFlujo por intFinalParaFlujo
	 */
	public void setIntFinalParaFlujo(DevolucionDineroUI addu_ddu)
	{
		iddu_intFinalParaFlujo = addu_ddu;
	}

	/**
	 * @return Retorna el valor de la propiedad intFinalParaFlujo
	 */
	public DevolucionDineroUI getIntFinalParaFlujo()
	{
		return iddu_intFinalParaFlujo;
	}

	/**
	 * @param acp_cp Modifica el valor de la propiedad intervinientesDevDinero por intervinientesDevDinero
	 */
	public void setIntervinientesDevDinero(Collection<Persona> acp_cp)
	{
		lcp_intervinientesDevDinero = acp_cp;
	}

	/**
	 * @return Retorna el valor de la propiedad intervinientesDevDinero
	 */
	public Collection<Persona> getIntervinientesDevDinero()
	{
		return lcp_intervinientesDevDinero;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad mostrarPanelConsultaInteresado por mostrarPanelConsultaInteresado
	 */
	public void setMostrarPanelConsultaInteresado(boolean ab_b)
	{
		ib_mostrarPanelConsultaInteresado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad mostrarPanelConsultaInteresado
	 */
	public boolean isMostrarPanelConsultaInteresado()
	{
		return ib_mostrarPanelConsultaInteresado;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad mostrarPanelInteresado por mostrarPanelInteresado
	 */
	public void setMostrarPanelInteresado(boolean ab_b)
	{
		ib_mostrarPanelInteresado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad mostrarPanelInteresado
	 */
	public boolean isMostrarPanelInteresado()
	{
		return ib_mostrarPanelInteresado;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad mostrarResumenSolicitud por mostrarResumenSolicitud
	 */
	public void setMostrarResumenSolicitud(boolean ab_b)
	{
		ib_mostrarResumenSolicitud = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad mostrarResumenSolicitud
	 */
	public boolean isMostrarResumenSolicitud()
	{
		return ib_mostrarResumenSolicitud;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param acdd_cdd modifica el valor de la propiedad.
	 */
	public void setPersonaARepresentar(Collection<DevolucionDinero> acdd_cdd)
	{
		icdd_personaARepresentar = acdd_cdd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DevolucionDinero> getPersonaARepresentar()
	{
		return icdd_personaARepresentar;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param acdd_cdd modifica el valor de la propiedad.
	 */
	public void setPersonaRepresentante(Collection<DevolucionDinero> acdd_cdd)
	{
		icdd_personaRepresentante = acdd_cdd;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public Collection<DevolucionDinero> getPersonaRepresentante()
	{
		return icdd_personaRepresentante;
	}

	/**
	 * @param ap_p Modifica el valor de la propiedad ip_personaTitularCuenta por ap_p
	 */
	public void setPersonaTitularCuenta(Persona ap_p)
	{
		ip_personaTitularCuenta = ap_p;
	}

	/**
	 * @return Retorna el valor de la propiedad ip_personaTitularCuenta
	 */
	public Persona getPersonaTitularCuenta()
	{
		return ip_personaTitularCuenta;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad titularCuentaS por titularCuentaS
	 */
	public void setTitularCuentaS(boolean ab_b)
	{
		ib_titularCuentaS = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad titularCuentaS
	 */
	public boolean isTitularCuentaS()
	{
		return ib_titularCuentaS;
	}

	/**
	 * Método para editar la persona.
	 *
	 * @param ap_p booleana para indicar que no se quiere consultar nuevamente la persona
	 */
	public void accionBotonEditarPersona(Persona ap_p)
	{
		try
		{
			if(ap_p != null)
			{
				String ls_pregunta;

				ls_pregunta = getIntervieneTramiteDevDinero();

				if(StringUtils.isValidString(ls_pregunta))
				{
					boolean lb_tieneInteresados;

					lb_tieneInteresados = false;

					{
						Registro lr_registro;

						lr_registro = getRegistroDatosConsultados();

						if(lr_registro != null)
						{
							Collection<Persona> lcp_personas;

							lcp_personas = lr_registro.getListadoPersona();

							if(CollectionUtils.isValidCollection(lcp_personas) && !lcp_personas.isEmpty())
								lb_tieneInteresados = true;
						}
					}

					if(ls_pregunta.equalsIgnoreCase(EstadoCommon.S) && lb_tieneInteresados)
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.setHabilitadoPorConsulta(true);

						setDeshabilitarDatosBasicos(false);
						setHabilitadoPorConsulta(true);
						accionBotonEditarPersona(ap_p, false);
					}
					else if(
					    (ls_pregunta.equalsIgnoreCase(EstadoCommon.S) && !lb_tieneInteresados)
						    || ls_pregunta.equalsIgnoreCase(EstadoCommon.N)
					)
						accionBotonEditarPersona(ap_p, true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
		}
	}

	/**
	 * Método que se encarga de agregar una persona a representar.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarPersonaARepresentar()
	    throws B2BException
	{
		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				limpiarEstilosDatosInteresado(
				    is_idForm + IdentificadoresCommon.DOS_PUNTOS, FacesContext.getCurrentInstance(), true
				);

				Registro lr_registro;

				lr_registro = new Registro();

				lr_registro.setPersona(lp_persona);

				{
					Solicitud lso_solicitud;

					lso_solicitud = getSolicitud();

					if(lso_solicitud != null)
					{
						Solicitud ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(lso_solicitud.getIdSolicitud());
						ls_solicitud.setIdProceso(lso_solicitud.getIdProceso());
						ls_solicitud.setIdCalidadSolicitante(lso_solicitud.getIdCalidadSolicitante());

						lr_registro.setSolicitud(ls_solicitud);
					}
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
					lr_registro.setIpCreacion(getRemoteIpAddress());
					lr_registro.setIpModificacion(getRemoteIpAddress());
					lr_registro.setIdTurno(getIdTurno());
				}

				{
					Collection<DevolucionDinero> lcdd_personaARepresentar;
					int                          li_identificador;

					lcdd_personaARepresentar     = getPersonaARepresentar();
					li_identificador             = 1;

					if(CollectionUtils.isValidCollection(lcdd_personaARepresentar))
					{
						for(DevolucionDinero ldd_iterador : lcdd_personaARepresentar)
						{
							if(ldd_iterador != null)
								li_identificador++;
						}
					}
					else
						lcdd_personaARepresentar = new ArrayList<DevolucionDinero>();

					{
						DevolucionDinero ldd_personaARepresentar;

						ldd_personaARepresentar = new DevolucionDinero();

						ldd_personaARepresentar.setIdentificador(li_identificador);
						ldd_personaARepresentar.setRegistro(lr_registro);
						ldd_personaARepresentar.setPersona(lp_persona);

						idd_devolucionesDineroRemote.validarDatosDelInteresado(
						    ldd_personaARepresentar, false, false, false, true, getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

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

								if(!StringUtils.isValidString(ls_destinatario))
								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(lp_persona.getPrimerNombre());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getSegundoNombre());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getPrimerApellido());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getSegundoApellido());

									ls_destinatario = lsb_sb.toString();
								}

								lp_persona.setNombreCompleto(ls_destinatario);
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
									ldd_personaARepresentar.setCalidadEnQueActua(lcs_calidadSolicitante.getNombre());
							}
						}

						lcdd_personaARepresentar.add(ldd_personaARepresentar);
					}

					setPersonaARepresentar(lcdd_personaARepresentar);

					limpiarRegistro(false, false);
					setMostrarLimpiarDatos(true);
					cargarMediosNotCom(false);
					esconderPanels();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarPersonaARepresentar", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método que se encarga de agregar un representante.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void agregarRepresentante()
	    throws B2BException
	{
		try
		{
			Persona lp_persona;

			lp_persona = getPersona();

			if(lp_persona != null)
			{
				limpiarEstilosDatosInteresado(
				    is_idForm + IdentificadoresCommon.DOS_PUNTOS, FacesContext.getCurrentInstance(), true, true
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

				{
					Solicitud lso_solicitud;

					lso_solicitud = getSolicitud();

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

				if(isProcesoDevoluciones())
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
					Collection<DevolucionDinero> lcdd_personaRepresentante;
					int                          li_identificador;

					lcdd_personaRepresentante     = getPersonaRepresentante();
					li_identificador              = 1;

					if(CollectionUtils.isValidCollection(lcdd_personaRepresentante))
					{
						for(DevolucionDinero ldd_iterador : lcdd_personaRepresentante)
						{
							if(ldd_iterador != null)
								li_identificador++;
						}
					}
					else
						lcdd_personaRepresentante = new ArrayList<DevolucionDinero>();

					{
						DevolucionDinero ldd_personaRepresentante;

						ldd_personaRepresentante = new DevolucionDinero();

						ldd_personaRepresentante.setIdentificador(li_identificador);
						ldd_personaRepresentante.setRegistro(lr_registro);
						ldd_personaRepresentante.setPersona(lp_persona);

						idd_devolucionesDineroRemote.validarDatosDelInteresado(
						    ldd_personaRepresentante, false, true, false, true, getUserId(), getLocalIpAddress(),
						    getRemoteIpAddress()
						);

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

								if(!StringUtils.isValidString(ls_destinatario))
								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append(lp_persona.getPrimerNombre());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getSegundoNombre());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getPrimerApellido());
									lsb_sb.append(" ");
									lsb_sb.append(lp_persona.getSegundoApellido());

									ls_destinatario = lsb_sb.toString();
								}

								lp_persona.setNombreCompleto(ls_destinatario);
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
									ldd_personaRepresentante.setCalidadEnQueActua(lcs_calidadSolicitante.getNombre());
							}
						}

						lcdd_personaRepresentante.add(ldd_personaRepresentante);
					}

					setPersonaRepresentante(lcdd_personaRepresentante);

					limpiarRegistro(false, false);
					setMostrarLimpiarDatos(true);
					cargarMediosNotCom(false);
					esconderPanels();

					{
						Solicitud ls_solicitud;

						ls_solicitud = getSolicitud();

						if(ls_solicitud != null)
							ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.INTERVINIENTE);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarRepresentante", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método encargado de buscar el documento certificación recaudo
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 * @return si el documento ya se encuentra generado
	 */
	public boolean buscarDocumentoCertificacionRecaudo(String as_idSolicitud)
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				return idd_devolucionesDineroRemote.buscarDocumentoCertificacionRecaudo(
				    as_idSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
			}
		}

		return false;
	}

	/**
	 * Método para bucar turnos con un filtro especifico.
	 */
	public void busquedaTurnos()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Documento ld_docTemp;

			ld_docTemp = getTurnoRecepcion();

			if(ld_docTemp != null)
			{
				String ls_consultaTurnoONir;

				ls_consultaTurnoONir = getConsultaTurnoONir();

				if(ls_consultaTurnoONir != null)
				{
					boolean lb_consultaNir;

					lb_consultaNir = false;

					if(ls_consultaTurnoONir.equalsIgnoreCase(IdentificadoresCommon.NIR))
					{
						String ls_nir;

						ls_nir     = ld_docTemp.getNirTurno();

						lb_focus = validateStyles(
							    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idnirRecepcionTurno", lfc_context,
							    ls_nir, lb_focus
							);

						if(!lb_focus)
							throw new B2BException(ErrorKeys.ERROR_NIR_NO_INGRESADO);

						String ls_idTurnoNIR;

						ls_idTurnoNIR     = ld_docTemp.getIdTurnoNir();

						lb_focus = validateStyles(
							    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idITurnoNirGrabacionMatricula",
							    lfc_context, ls_idTurnoNIR, lb_focus
							);

						if(!lb_focus)
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TURNO_VALIDO);

						ld_docTemp.setIdTurno(ls_idTurnoNIR);
						ld_docTemp.setNir(ls_nir);
					}
					else
					{
						String ls_idTurno;

						ls_idTurno     = ld_docTemp.getIdTurno();

						lb_focus = validateStyles(
							    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idIRadicacionRecepcion", lfc_context,
							    ls_idTurno, lb_focus
							);

						if(!lb_focus)
							throw new B2BException(ErrorKeys.ERROR_INGRESAR_TURNO);

						lb_consultaNir = true;
					}

					String ls_idTurno;

					ls_idTurno = ld_docTemp.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						DevolucionDineroUI lddu_devolucionDineroUi;

						lddu_devolucionDineroUi = idd_devolucionesDineroRemote.consultaBandejaDevolucion(
							    ls_idTurno, lb_consultaNir, null, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lddu_devolucionDineroUi != null)
						{
							Documento ld_documento;

							ld_documento = getTurnoRecepcion();

							if(ld_documento != null)
							{
								String ls_idProcesoAnterior;

								ls_idProcesoAnterior = lddu_devolucionDineroUi.getIdProcesoAnterior();

								if(StringUtils.isValidString(ls_idProcesoAnterior))
								{
									boolean lb_idProceso6;

									lb_idProceso6 = ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6);

									if(lb_idProceso6)
									{
										Collection<ActoDevolucionDinero> lct_cllTurno;

										lct_cllTurno = lddu_devolucionDineroUi.getActosDevolucionDinero();

										if(CollectionUtils.isValidCollection(lct_cllTurno))
										{
											setActosDevolucionDinero(lct_cllTurno);
											addMessage(MessagesKeys.CONSULTA_EXITOSA_4);

											PrimeFaces.current().executeScript(
											    "PF('panelResultadoConsulta').expand();"
											);
										}
										else
										{
											setActosDevolucionDinero(null);
											throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
										}
									}
									else if(ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
									{
										setActosDevolucionDinero(null);
										addMessage(MessagesKeys.TURNO_CERTIFICADOS_DEV_DINERO);

										PrimeFaces.current().executeScript("PF('panelResultadoConsulta').collapse();");
									}

									ld_documento.setNir(lddu_devolucionDineroUi.getNir());

									if(!lb_idProceso6)
									{
										Collection<Liquidacion> lcl_detalleLiquidacion;

										lcl_detalleLiquidacion = lddu_devolucionDineroUi.getLiquidacion();

										if(CollectionUtils.isValidCollection(lcl_detalleLiquidacion))
											llenarValoresDetalleLiquidacion(lcl_detalleLiquidacion);
									}
								}

								{
									Collection<String> lcs_mensajes;

									lcs_mensajes = lddu_devolucionDineroUi.getMensajesPantalla();

									if(CollectionUtils.isValidCollection(lcs_mensajes))
									{
										for(String ls_mensaje : lcs_mensajes)
										{
											if(StringUtils.isValidString(ls_mensaje))
												addMessage(ls_mensaje);
										}
									}
								}

								setIntFinalParaFlujo(lddu_devolucionDineroUi);
							}
						}
					}
				}
				else
				{
					validateStyles(
					    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idNirOTurno", lfc_context, ls_consultaTurnoONir,
					    lb_focus
					);

					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR__CONSULTA__POR_NIR_O_TURNO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			setInfoTurnos(null);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idForm);
		}
	}

	/**
	 * Método para precargar la información de la persona.
	 *
	 * @param ap_persona correspondiente al valor del tipo de objeto Persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosPersonalesDevDinero(Persona ap_persona)
	    throws B2BException
	{
		if(ap_persona != null)
		{
			String ls_pregunta;

			ls_pregunta = getIntervieneTramiteDevDinero();

			if(StringUtils.isValidString(ls_pregunta))
			{
				if(ls_pregunta.equalsIgnoreCase(EstadoCommon.S))
				{
					Registro lr_resultado;

					lr_resultado = new Registro();

					ap_persona.setTipoDocIdentidad(ap_persona.getIdDocumentoTipo());

					lr_resultado.setPersona(ap_persona);

					lr_resultado = irr_registroRemote.findPersonByDocument(lr_resultado, getUserId());

					if(lr_resultado != null)
					{
						setRendered(true);
						setPersonaConsultada(true);
						setDeshabilitarDatosBasicos(false);
						setHabilitadoPorConsulta(true);

						Collection<PersonaDireccion>         lcpd_direccionesResidencia;
						Collection<PersonaDireccion>         lcpd_direccionesCorrespondencia;
						Collection<PersonaTelefono>          lcpt_telefonosFijo;
						Collection<PersonaTelefono>          lcpt_telefonosMovil;
						Collection<PersonaCorreoElectronico> lcpce_correos;

						lcpd_direccionesResidencia          = lr_resultado.getListadoDireccionResidencia();
						lcpd_direccionesCorrespondencia     = lr_resultado.getListadoDireccionCorrespondencia();
						lcpt_telefonosFijo                  = lr_resultado.getListadoTelefonoFijo();
						lcpt_telefonosMovil                 = lr_resultado.getListadoTelefonoMovil();
						lcpce_correos                       = lr_resultado.getListadoCorreoElectronico();

						boolean lb_direccionesResidencia;
						boolean lb_direccionesCorrespondencia;
						boolean lb_telefonosFijo;
						boolean lb_telefonosMovil;
						boolean lb_correos;

						lb_direccionesResidencia            = CollectionUtils.isValidCollection(
							    lcpd_direccionesResidencia
							);
						lb_direccionesCorrespondencia       = CollectionUtils.isValidCollection(
							    lcpd_direccionesCorrespondencia
							);
						lb_telefonosFijo                    = CollectionUtils.isValidCollection(lcpt_telefonosFijo);
						lb_telefonosMovil                   = CollectionUtils.isValidCollection(lcpt_telefonosMovil);
						lb_correos                          = CollectionUtils.isValidCollection(lcpce_correos);

						setMostrarCorreo(lb_correos);
						setMostrarDireccionesResidencia(lb_direccionesResidencia);
						setMostrarDireccionesCorrespondencia(lb_direccionesCorrespondencia);
						setMostrarTelefonoFijo(lb_telefonosFijo);
						setMostrarTelefonoMovil(lb_telefonosMovil);

						if(
						    !lb_direccionesResidencia || !lb_direccionesCorrespondencia || !lb_telefonosFijo
							    || !lb_telefonosMovil || !lb_correos
						)
						{
							String ls_mensaje_datos = "Para el registro ingresado no existen: \n";

							if(!lb_direccionesResidencia)
								ls_mensaje_datos = ls_mensaje_datos + "direcciones residencia. \n";

							if(!lb_direccionesCorrespondencia)
								ls_mensaje_datos = ls_mensaje_datos + "direcciones correspondencia. \n";

							if(!lb_telefonosFijo)
								ls_mensaje_datos = ls_mensaje_datos + "teléfonos fijos. \n";

							if(!lb_telefonosMovil)
								ls_mensaje_datos = ls_mensaje_datos + "teléfonos moviles. \n";

							if(!lb_correos)
								ls_mensaje_datos = ls_mensaje_datos + "correos electrónicos. \n";

							addMessage("I", ls_mensaje_datos);
							PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
						}

						cargarDatosPersonales(ap_persona);
					}
				}
				else if(ls_pregunta.equalsIgnoreCase(EstadoCommon.N))
					cargarDatosPersonales(ap_persona);
			}
		}
	}

	/**
	 * Método encargado de traer los causales devolucion
	 * R
	 */
	public Collection<CausalAprobacionDevolucion> causalesDevolucion()
	{
		Collection<CausalAprobacionDevolucion> lccad_datos;
		lccad_datos = null;

		try
		{
			CausalAprobacionDevolucion lcad_param;

			lcad_param = new CausalAprobacionDevolucion();
			lcad_param.setIdEtapaDevolucion(new BigDecimal(EtapaCommon.ID_ETAPA_377));
			lccad_datos = irr_referenceRemote.obtenerCausalesAprobacionDevolucionActivos(
				    lcad_param, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			lccad_datos = null;
			PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
		}

		return lccad_datos;
	}

	/**
	 * Limpia las variables de instancia de la clase.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void clean()
	    throws B2BException
	{
		limpiarRegistro();
		setConsultaTurnoONir(null);
		setActosDevolucionDinero(null);
		setTitularCuentaDevDinero(null);
		setTitularCuentaS(false);
		setIntervinientesDevDinero(null);
		setMostrarResumenSolicitud(false);
		setProcesoTerminado(false);
		setSolicitud(null);
		setDevolucionDinero(null);
		setPersonaRepresentante(null);

		{
			BeanDireccion lbd_beanDireccion;

			lbd_beanDireccion = getBeanDireccion();

			lbd_beanDireccion.setHabilitadoPorConsulta(false);
		}

		setDeshabilitarDatosBasicos(true);
		setMostrarDatosConsulta(false);
		limpiarMedioNo();
		limpiarMedioCo();
		setPersonaARepresentar(null);
		setRegistroDatosConsultados(null);
		setRegistroDatosConsultadosInter(null);
		setPersonaConsultada(false);
		setPersonaConsultadaInter(false);
		setPersona(null);
		setPersonaInter(null);
	}

	/**
	 * Método para mostrar el detalle de la liquidación.
	 *
	 * @param aadd_add item de detalle
	 */
	public void consultaLiquidacion(ActoDevolucionDinero aadd_add)
	{
		if(aadd_add != null)
		{
			Collection<ActoDevolucionDinero> lcadd_cllTmp;

			lcadd_cllTmp = new ArrayList<ActoDevolucionDinero>();

			lcadd_cllTmp.add(aadd_add);

			setDevDineroTemporal(lcadd_cllTmp);

			PrimeFaces.current().executeScript("PF('dlgdetalleLiquidacion').show();");
			PrimeFaces.current().ajax().update("dlgdetalleLiq");
		}
	}

	/**
	 * Descargar pdf.
	 */
	public void descargarPdf()
	{
		byte[] lba_pdfDevolucion;

		lba_pdfDevolucion = getFile();

		if(lba_pdfDevolucion != null)
			setArchivoPdf(
			    generarArchivosDescarga(
			        lba_pdfDevolucion, TipoContenidoCommon.PDF,
			        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
			    )
			);
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String detalleTurno()
	{
		try
		{
			String ls_idTurno;
			String ls_idTurnoHistoria;
			Long   ll_etapa;
			String ls_decisionCalificacion;

			ls_idTurno             = FacesUtils.getStringFacesParameter("idTurno");
			ls_idTurnoHistoria     = null;
			ll_etapa               = null;

			if(StringUtils.isValidString(ls_idTurno))
			{
				Trazabilidad lt_trazabilidad;

				lt_trazabilidad = ictr_consultaTrazabilidadRemote.detalleTurno(new Turno(ls_idTurno));

				if(lt_trazabilidad != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = lt_trazabilidad.getTurnoHistoria();

					if(lth_turnoHistoria != null)
					{
						ls_idTurnoHistoria     = StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria());
						ll_etapa               = NumericUtils.getLongWrapper(lth_turnoHistoria.getIdEtapa());
					}

					ls_decisionCalificacion = lt_trazabilidad.getDecisionCalificacion();

					{
						FacesContext            lfc_context;
						BeanPredioDocumentoActo lbpdab_bean;

						lfc_context = FacesUtils.getFacesContext();

						{
							lbpdab_bean = (BeanPredioDocumentoActo)lfc_context.getApplication()
									                                              .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO,
									    BeanPredioDocumentoActo.class
									);

							if(lbpdab_bean != null)
							{
								lbpdab_bean.limpiarDatos();
								lbpdab_bean.setIdEtapa(ll_etapa);
								lbpdab_bean.setIdTurno(ls_idTurno);
								lbpdab_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
								lbpdab_bean.generarDatosAntSistema();
								lbpdab_bean.generarDatosDocumento();
								lbpdab_bean.generarDatosTramitesVinculados(true);
								lbpdab_bean.obtenerInformacionASEtapa101();
								lbpdab_bean.validarFechaVencimientoActo();
								lbpdab_bean.setMotivoTramite(null);
								lbpdab_bean.setDecisionCalificacion(ls_decisionCalificacion);

								lbpdab_bean.getMatriculasRangos();
								lbpdab_bean.getMatriculasIndividuales();
								lbpdab_bean.getMatriculasTmpRangos();
								lbpdab_bean.getMatriculasTmpIndividuales();
							}
						}

						{
							BeanConsultaTrazabilidad lbct_bean;

							lbct_bean = (BeanConsultaTrazabilidad)lfc_context.getApplication()
									                                             .evaluateExpressionGet(
									    lfc_context, BeanNameCommon.BEAN_CONSULTA_TRAZABILIDAD,
									    BeanConsultaTrazabilidad.class
									);

							if(lbct_bean != null)
							{
								lbct_bean.setPaginaRetorno(NavegacionCommon.DEVOLUCIONES_DINERO);
								lbct_bean.setConsultaTrazabilidad(lt_trazabilidad.getTrazabilidad());
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.DETALLE_TRAZABILIDAD;
	}

	/**
	 * Metodo encargado de eliminar una persona a representar.
	 *
	 * @param add_parametro Argumento de tipo <code>DevolucionDinero</code> que contiene el criterio a eliminar.
	 */
	public void eliminarPersonaARepresentar(DevolucionDinero add_parametro)
	{
		if(add_parametro != null)
		{
			Collection<DevolucionDinero> lcdd_personaARepresentar;

			lcdd_personaARepresentar = getPersonaARepresentar();

			if(CollectionUtils.isValidCollection(lcdd_personaARepresentar))
			{
				Collection<DevolucionDinero> lcdd_nueva;
				int                          li_identificador;

				lcdd_nueva           = new ArrayList<DevolucionDinero>();
				li_identificador     = 1;

				for(DevolucionDinero lpn_iterador : lcdd_personaARepresentar)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = add_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcdd_nueva.add(lpn_iterador);
						}
					}
				}

				if(!CollectionUtils.isValidCollection(lcdd_nueva))
					lcdd_nueva = null;

				setPersonaARepresentar(lcdd_nueva);
			}
		}
	}

	/**
	 * Metodo encargado de eliminar una persona representante.
	 *
	 * @param add_parametro Argumento de tipo <code>DevolucionDinero</code> que contiene el criterio a eliminar.
	 */
	public void eliminarPersonaRepresentante(DevolucionDinero add_parametro)
	{
		if(add_parametro != null)
		{
			Collection<DevolucionDinero> lcdd_personaRepresentante;

			lcdd_personaRepresentante = getPersonaRepresentante();

			if(CollectionUtils.isValidCollection(lcdd_personaRepresentante))
			{
				Collection<DevolucionDinero> lcdd_nueva;
				int                          li_identificador;

				lcdd_nueva           = new ArrayList<DevolucionDinero>();
				li_identificador     = 1;

				for(DevolucionDinero lpn_iterador : lcdd_personaRepresentante)
				{
					if(lpn_iterador != null)
					{
						int li_identificadorParametro;
						int li_identificadorIterado;

						li_identificadorParametro     = add_parametro.getIdentificador();
						li_identificadorIterado       = lpn_iterador.getIdentificador();

						if(li_identificadorParametro != li_identificadorIterado)
						{
							lpn_iterador.setIdentificador(li_identificador++);

							lcdd_nueva.add(lpn_iterador);
						}
					}
				}

				if(!CollectionUtils.isValidCollection(lcdd_nueva))
					lcdd_nueva = null;

				setPersonaRepresentante(lcdd_nueva);
			}
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EntidadRecaudadora> findAllEntidadRecaudadora()
	{
		Collection<EntidadRecaudadora> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllEntidadRecaudadora();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctr_tr;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 * Filtra para solo traer al desplegable 3 calidad solicitante.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CalidadSolicitante> findCalidadSolicitanteDevDinero()
	{
		Collection<CalidadSolicitante> lcidt_datos;
		Collection<CalidadSolicitante> lcidt_datosReturn;

		lcidt_datos           = findCalidadSolicitante();
		lcidt_datosReturn     = new ArrayList<CalidadSolicitante>();

		if(CollectionUtils.isValidCollection(lcidt_datos))
		{
			String ls_intervieneTramiteDevDinero;

			ls_intervieneTramiteDevDinero = getIntervieneTramiteDevDinero();

			if(StringUtils.isValidString(ls_intervieneTramiteDevDinero))
			{
				for(CalidadSolicitante lcs_temp : lcidt_datos)
				{
					if(lcs_temp != null)
					{
						String ls_idCalidadSolicitante;

						ls_idCalidadSolicitante = lcs_temp.getIdCalidadSolicitante();

						if(StringUtils.isValidString(ls_idCalidadSolicitante))
						{
							String ls_idSubProceso;

							ls_idSubProceso = StringUtils.getStringNotNull(getIdSubproceso());

							if(
							    ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.N)
								    && !CollectionUtils.isValidCollection(getPersonaRepresentante())
								    && (ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
								    || ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.TERCERO)
								    || (ls_idSubProceso.equalsIgnoreCase(
								        SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA
								    )
								    && ls_idCalidadSolicitante.equalsIgnoreCase(
								        CalidadSolicitanteCommon.AGENTE_OFICIOSO
								    )))
							)
								lcidt_datosReturn.add(lcs_temp);
							else if(
							    (ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.S))
								    || ((ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.N)
								    && ls_idSubProceso.equalsIgnoreCase(
								        SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA
								    ) && CollectionUtils.isValidCollection(getPersonaRepresentante()))
								    && (ls_idCalidadSolicitante.equalsIgnoreCase(
								        CalidadSolicitanteCommon.INTERVINIENTE
								    )))
							)
								lcidt_datosReturn.add(lcs_temp);
						}
					}
				}
			}
		}

		return lcidt_datosReturn;
	}

	/**
	 * Método para encontrar los subprocesos a mostrar en pantalla para devolución de dinero.
	 *
	 * @return el valor de collection
	 */
	public Collection<Subproceso> findSubprocesosDevDinero()
	{
		Collection<Subproceso> lcsp_subProcesosFinal;

		lcsp_subProcesosFinal = null;

		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud == null)
				ls_solicitud = new Solicitud();

			ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_4);

			setSolicitud(ls_solicitud);

			{
				Collection<Subproceso> lcsp_subProcesos;

				lcsp_subProcesos = findSubprocesos();

				if(CollectionUtils.isValidCollection(lcsp_subProcesos))
				{
					Constantes lc_constante;

					lc_constante = new Constantes();

					lc_constante.setIdConstante(ConstanteCommon.SUBPROCESOS_VALIDOS_DEV_DINERO_RADICACION);

					lc_constante = irr_registroRemote.findConstante(lc_constante);

					if(lc_constante != null)
					{
						Map<String, String> lmss_codigos;

						lmss_codigos = ListadoCodigosConstantes.generarCodigos(lc_constante.getCaracter());

						if(CollectionUtils.isValidCollection(lmss_codigos))
						{
							lcsp_subProcesosFinal = new ArrayList<Subproceso>();

							for(Subproceso lsp_iterador : lcsp_subProcesos)
							{
								if(lsp_iterador != null)
								{
									if(lmss_codigos.containsKey(lsp_iterador.getIdSubproceso()))
										lcsp_subProcesosFinal.add(lsp_iterador);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findSubprocesosDevDinero", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return lcsp_subProcesosFinal;
	}

	/**
	 * Controla el flujo entre tabs en la pantalla.
	 *
	 * @param afe_event Objeto contenedor de los eventos que ocurren en pantalla
	 * @return Nombre de tab a mostrar en pantalla
	 *
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String  ls_return;
		boolean lb_focus;

		ls_return     = afe_event.getNewStep();
		lb_focus      = true;

		try
		{
			FacesContext lfc_context;
			String       ls_oldStep;
			String       ls_newStep;
			String       ls_idSubProceso;

			lfc_context         = FacesContext.getCurrentInstance();
			ls_oldStep          = afe_event.getOldStep();
			ls_newStep          = afe_event.getNewStep();
			ls_idSubProceso     = getIdSubproceso();

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				if(ls_oldStep.equalsIgnoreCase("datosTramite_id") && ls_newStep.equalsIgnoreCase("datosInteresado_id"))
				{
					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_UN_TIPO_DEVOLUCION);

					validarTabDatosTramite(lfc_context, lb_focus);

					salvarTabDatosTramite();
					setMostrarPanelInteresado(false);
					setIntervieneTramiteDevDinero(null);
					setTitularCuentaDevDinero(null);
				}
				else if(
				    ls_oldStep.equalsIgnoreCase("datosInteresado_id")
					    && ls_newStep.equalsIgnoreCase("datosCuentaBancaria_id")
				)
				{
					String ls_intervieneTramiteDevDinero;
					String ls_titularCuentaDevDinero;

					ls_intervieneTramiteDevDinero     = StringUtils.getStringNotNull(getIntervieneTramiteDevDinero());
					ls_titularCuentaDevDinero         = StringUtils.getStringNotNull(getTitularCuentaDevDinero());

					validarTabDatosInteresado(lfc_context, lb_focus);
					setProcesoDevoluciones(true);

					if(StringUtils.isValidString(ls_idSubProceso))
					{
						boolean lb_consignacionErrada;

						lb_consignacionErrada = ls_idSubProceso.equalsIgnoreCase(
							    SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA
							);

						if(
						    ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_TURNO_DE_SERVICIO_REGISTRAL)
							    || (lb_consignacionErrada
							    && ls_intervieneTramiteDevDinero.equalsIgnoreCase(EstadoCommon.S))
						)
						{
							salvarInteresado();
							limpiarEstilosDatosInteresado(
							    is_idForm + IdentificadoresCommon.DOS_PUNTOS, lfc_context, lb_focus
							);
							precargarMediosNotificar();
							cargarInterviniente();

							if(lb_consignacionErrada)
								setPersonaInter(getPersona());
						}
						else if(lb_consignacionErrada)
						{
							DevolucionDineroUI lddu_devolucionDineroUI;

							lddu_devolucionDineroUI = new DevolucionDineroUI();

							{
								Solicitud ls_solicitud;

								ls_solicitud = getSolicitud();

								if(ls_solicitud != null)
									lddu_devolucionDineroUI.setIdSolicitud(ls_solicitud.getIdSolicitud());
							}

							lddu_devolucionDineroUI.setTitularCuentaDevDinero(getTitularCuentaDevDinero());
							lddu_devolucionDineroUI.setPersonaRepresentante(getPersonaRepresentante());
							lddu_devolucionDineroUI.setPersonasARepresentar(getPersonaARepresentar());

							idd_devolucionesDineroRemote.salvarDatosInteresadoDevDinero(
							    lddu_devolucionDineroUI, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
						}
					}

					validarTitularCuentaS();

					if(ls_titularCuentaDevDinero.equalsIgnoreCase(EstadoCommon.S))
					{
						Solicitud ls_solicitud;

						ls_solicitud = getSolicitud();

						if(ls_solicitud != null)
						{
							ls_solicitud.setIdSolicitud(ls_solicitud.getIdSolicitud());

							ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

							if(ls_solicitud != null)
							{
								Persona lp_persona;

								lp_persona = new Persona();

								lp_persona.setIdPersona(ls_solicitud.getIdPersona());

								lp_persona = irr_registroRemote.findPersonaByIdPersona(lp_persona, getUserId());

								if(lp_persona != null)
								{
									setPersonaInter(lp_persona);

									PrimeFaces.current().executeScript("PF('wVPanelDatosBasicosInter').expand();");
									PrimeFaces.current().executeScript("PF('wvPanelDatosCInter').expand();");
								}
							}
						}
					}
				}
				else if(
				    ls_oldStep.equalsIgnoreCase("datosCuentaBancaria_id")
					    && ls_newStep.equalsIgnoreCase("documentosSoporte_id")
				)
				{
					{
						PersonaTelefono lpt_personaTelefonoMovil;

						lpt_personaTelefonoMovil = getTelefonoMovilInter();

						if(lpt_personaTelefonoMovil != null)
						{
							String ls_idDepartamento;

							ls_idDepartamento = lpt_personaTelefonoMovil.getIdDepartamento();

							if(!StringUtils.isValidString(ls_idDepartamento))
								lpt_personaTelefonoMovil.setIdDepartamento(
								    IdentificadoresCommon.INDICATIVO_BOGOTA_DEPARTAMENTO
								);
						}
					}

					validarTabDatosCuentaBancaria(lfc_context, lb_focus);

					if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_TURNO_DE_SERVICIO_REGISTRAL))
					{
						if(isTitularCuentaS())
							salvarTitularCuenta();
						else
							salvarTitularCuentaIngresado();
					}
					else if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA))
					{
						Persona lp_persona;

						lp_persona = getPersonaInter();

						if(lp_persona != null)
						{
							validarCamposInterviniente(is_idForm, false);

							Registro lr_registro;

							lr_registro = new Registro();

							lr_registro.setPersona(lp_persona);
							lr_registro.setTelefonoFijo(getTelefonoFijoInter());
							lr_registro.setTelefonoMovil(getTelefonoMovilInter());
							lr_registro.setPersonaCorreoElectronico(getCorreoElectronicoInter());

							{
								Solicitud lso_solicitud;

								lso_solicitud = getSolicitud();

								if(lso_solicitud != null)
								{
									Persona lp_personaTemp;

									lp_personaTemp = lr_registro.getPersona();

									lr_registro.setSolicitud(lso_solicitud);
									lso_solicitud.setIdPersona(
									    (lp_personaTemp != null) ? lp_personaTemp.getIdPersona() : null
									);
								}
							}

							lr_registro.setTelefonoFijoCargado(isDeshabilitarTelFijoInter());
							lr_registro.setTelefonoMovilCargado(isDeshabilitarTelMovilInter());
							lr_registro.setCorreoCargado(isDeshabilitarCorreoInter());
							lr_registro.setIpCreacion(getRemoteIpAddress());
							lr_registro.setIpModificacion(getRemoteIpAddress());
							lr_registro.setIdTurno(getIdTurno());

							{
								DevolucionDinero ldd_devolucionDinero;

								ldd_devolucionDinero = new DevolucionDinero();

								ldd_devolucionDinero.setRegistro(lr_registro);
								ldd_devolucionDinero.setPersona(lp_persona);
								ldd_devolucionDinero.setIdEntidadRecaudadora(getIdEntidadRecaudadora());
								ldd_devolucionDinero.setTipoCuenta(getTipoCuenta());
								ldd_devolucionDinero.setNumeroCuenta(getNumeroCuenta());

								idd_devolucionesDineroRemote.salvarDatosCuentaBancaria(
								    ldd_devolucionDinero, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);
							}
						}
					}
				}
				else if(
				    ls_oldStep.equalsIgnoreCase("documentosSoporte_id") && ls_newStep.equalsIgnoreCase("impresion_id")
				)
				{
					if(isProcesoTerminado())
					{
						Solicitud lso_solicitud;

						lso_solicitud = getSolicitud();

						if(lso_solicitud != null)
						{
							ii_indiceImpresion = 0;

							setDocumentosImprimir(
							    irr_registroRemote.cargarDocumentosSolicitud(
							        IdentificadoresCommon.DEVOLUCION_DINERO, lso_solicitud.getIdSolicitud(), getUserId(),
							        getLocalIpAddress(), getRemoteIpAddress()
							    )
							);
							setOcultarSiguiente(true);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TERMINAR_PROCESO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
			ls_return = afe_event.getOldStep();
		}

		return ls_return;
	}

	/**
	 * Método encargado de generar el documento certificación recaudo
	 *
	 * @param as_idSolicitud Id de la solicitud desde la cual se está solicitando la construcción del documento
	 */
	public void generarDocumentoCertificacionRecaudo(String as_idSolicitud)
	{
		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				idd_devolucionesDineroRemote.generarDocumentoCertificacionRecaudo(
				    as_idSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
			}
		}
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		imprimirDocumentos(false, as_pantalla);
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		imprimirDocumentos(ab_boton, as_pantalla, getNirGenerado(), ":botonFinalizar");
	}

	/**
	 * Método para limpiar enviando el formulario.
	 */
	public void limpiarRecepcionDocumentos()
	{
		{
			boolean      lb_focus;
			FacesContext lfc_context;
			String       ls_consultaTurnoONir;

			lb_focus                 = true;
			lfc_context              = FacesContext.getCurrentInstance();
			ls_consultaTurnoONir     = IdentificadoresCommon.DATO_VALIDO;

			validateStyles(
			    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idNirOTurno", lfc_context, ls_consultaTurnoONir,
			    lb_focus
			);
		}

		setActosDevolucionDinero(null);
		limpiarRecepcionDocumentos(is_idForm);
	}

	/**
	 * Método sobrecargado para enviar el formulario.
	 */
	public void listarTurnosDadoElNir()
	{
		listarTurnosDadoElNir(is_idForm);
	}

	/**
	 * Método que se ejecuta en el flowListener para guardar la información de datos del trámite.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTabDatosTramite()
	    throws B2BException
	{
		try
		{
			DevolucionDineroUI lddui_datosSalvarUI;

			lddui_datosSalvarUI = getIntFinalParaFlujo();

			if(lddui_datosSalvarUI == null)
				lddui_datosSalvarUI = new DevolucionDineroUI();

			lddui_datosSalvarUI.setActosDevolucionDinero(getActosDevolucionDinero());
			lddui_datosSalvarUI.setIdSubProceso(getIdSubproceso());
			lddui_datosSalvarUI.setDevolucionDinero(getDevolucionDinero());

			{
				Solicitud ls_solicitud;

				ls_solicitud = getSolicitud();

				if(ls_solicitud != null)
					lddui_datosSalvarUI.setIdSolicitud(ls_solicitud.getIdSolicitud());
			}

			lddui_datosSalvarUI = idd_devolucionesDineroRemote.salvarTabDatosTramite(
				    lddui_datosSalvarUI, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lddui_datosSalvarUI != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = lddui_datosSalvarUI.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					{
						Solicitud ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(ls_idSolicitud);

						ls_solicitud = irr_registroRemote.findSolicitudById(ls_solicitud, getUserId());

						if(ls_solicitud != null)
							setSolicitud(ls_solicitud);
					}

					{
						Collection<String> lcs_mensajes;

						lcs_mensajes = lddui_datosSalvarUI.getMensajesInformativos();

						if(CollectionUtils.isValidCollection(lcs_mensajes))
						{
							for(String ls_iterador : lcs_mensajes)
							{
								if(ls_iterador != null)
									addMessage(ls_iterador);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarTabDatosTramite", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método para salvar el titular de cuenta precargado por la respuesta de la pregunta es titular de la cuenta bancaria.
	 */
	public void salvarTitularCuenta()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = ls_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Persona lp_persona;

					lp_persona = getPersonaTitularCuenta();

					if(lp_persona != null)
					{
						String ls_idPersona;

						ls_idPersona = lp_persona.getIdPersona();

						if(StringUtils.isValidString(ls_idPersona))
						{
							DevolucionDinero ldd_devolucionDinero;

							ldd_devolucionDinero = new DevolucionDinero();

							ldd_devolucionDinero.setIdEntidadRecaudadora(getIdEntidadRecaudadora());
							ldd_devolucionDinero.setTipoCuenta(getTipoCuenta());
							ldd_devolucionDinero.setNumeroCuenta(getNumeroCuenta());
							ldd_devolucionDinero.setIdSolicitud(ls_idSolicitud);
							ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersona);

							idd_devolucionesDineroRemote.salvarTitularCuenta(
							    ldd_devolucionDinero, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idForm);
		}
	}

	/**
	 * Método que reutiliza el panel de intervinientes para actualizar el titular de cuenta del registro de devolución de dinero.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarTitularCuentaIngresado()
	    throws B2BException
	{
		agregarInterviniente(is_idForm, true, null);
	}

	/**
	 * Método para desmarcar los demás intervinientes y guardar en una variable de registro el seleccionado.
	 *
	 * @param lp_persona de lp persona
	 */
	public void seleccionarInterviniente(Persona lp_persona)
	{
		if(lp_persona != null)
		{
			String ls_idPersona;

			ls_idPersona = lp_persona.getIdPersona();

			if(StringUtils.isValidString(ls_idPersona))
			{
				Collection<Persona> lcp_persona;

				lcp_persona = getIntervinientesDevDinero();

				if(CollectionUtils.isValidCollection(lcp_persona))
				{
					for(Persona lp_personaTmp : lcp_persona)
					{
						if(lp_personaTmp != null)
						{
							String ls_idPersonaTmp;

							ls_idPersonaTmp = lp_personaTmp.getIdPersona();

							if(!ls_idPersonaTmp.equalsIgnoreCase(ls_idPersona))
								lp_personaTmp.setSeleccionado(false);
						}
					}

					setIntervinienteDevDinero(lp_persona);
				}
			}
		}
	}

	/**
	 * Método para terminar el proceso de devoluciones de dinero.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void terminarProcesoDevoluciones()
	    throws B2BException
	{
		try
		{
			Documento ld_documento;

			ld_documento = getTurnoRecepcion();

			if(ld_documento != null)
			{
				String  ls_idTurno;
				String  ls_idSubProceso;
				boolean lb_idSubProceso;

				ls_idTurno          = ld_documento.getIdTurno();
				ls_idSubProceso     = getIdSubproceso();
				lb_idSubProceso     = StringUtils.isValidString(ls_idSubProceso)
						&& ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA);

				if(StringUtils.isValidString(ls_idTurno) || lb_idSubProceso)
				{
					Collection<AccCompletitudDocumental> lcaccd_tiposCompletitudDocumental;

					lcaccd_tiposCompletitudDocumental = getTiposCompletitudDocumental();

					if(CollectionUtils.isValidCollection(lcaccd_tiposCompletitudDocumental))
					{
						DevolucionDineroUI lddu_devolucionDineroUi;
						Registro           lr_registroTmp;

						lddu_devolucionDineroUi = new DevolucionDineroUI();

						lddu_devolucionDineroUi.setTitularCuentaDevDinero(getTitularCuentaDevDinero());
						lddu_devolucionDineroUi.setIntervieneTramiteDevDinero(getIntervieneTramiteDevDinero());

						lr_registroTmp = idd_devolucionesDineroRemote.terminarProcesoDevolucionDinero(
							    lb_idSubProceso ? lddu_devolucionDineroUi : null, ls_idTurno, getSolicitud(),
							    lcaccd_tiposCompletitudDocumental, getLocalIpAddress(), getRemoteIpAddress(),
							    getUserId()
							);

						if(lr_registroTmp != null)
						{
							String ls_nir;

							ls_nir = lr_registroTmp.getNirProceso();

							if(StringUtils.isValidString(ls_nir))
							{
								PrimeFaces lp_primefaces;

								lp_primefaces = PrimeFaces.current();

								{
									StringBuilder lsb_sb;

									lsb_sb = new StringBuilder();

									lsb_sb.append("Ha finalizado su solicitud bajo el NIR: ");
									lsb_sb.append(ls_nir);

									if(lb_idSubProceso)
										lsb_sb.append(
										    ", recuerde que tiene 15 días hábiles para tramitar esta solicitud."
										);

									setMensajeTerminarProceso(lsb_sb.toString());
								}

								setNirGenerado(ls_nir);
								lp_primefaces.executeScript("PF('idNirVar').show();");
								lp_primefaces.executeScript("PF('myWizard').backNav.css('visibility', 'hidden');");
								actualizarComponente("idNirGenerado");
								actualizarComponente("fidNir:otMensaje");
								setMostrarResumenSolicitud(true);
								setProcesoTerminado(true);

								{
									String ls_urlCapture;

									ls_urlCapture = lr_registroTmp.getUrlCapture();

									if(StringUtils.isValidString(ls_urlCapture))
										PrimeFaces.current().executeScript(
										    "window.open(href ='" + ls_urlCapture + "')"
										);
								}

								{
									byte[] lb_pdf;

									lb_pdf = lr_registroTmp.getPdf();

									if(lb_pdf != null)
										setFile(lb_pdf);
								}
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_TIPO_DOCUMENTAL);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProcesoDevoluciones", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
		}
	}

	/**
	 * Método para validar y presentar en pantalla registros dependiendo de la respuesta en pantalla.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validacionesIntervinoEnTramitePregunta()
	    throws B2BException
	{
		try
		{
			Registro lr_registro;

			lr_registro = getRegistroDatosConsultados();

			if(lr_registro != null)
			{
				String ls_intervinoEnTramitePregunta;

				ls_intervinoEnTramitePregunta = getIntervieneTramiteDevDinero();

				if(StringUtils.isValidString(ls_intervinoEnTramitePregunta))
				{
					Solicitud ls_solicitud;

					ls_solicitud = getSolicitud();

					if(ls_solicitud != null)
					{
						Collection<Persona> lcp_cllPersona;
						String              ls_idSubProceso;

						lcp_cllPersona      = idd_devolucionesDineroRemote.bandejaDatosDelInteresado(
							    getTurnoRecepcion().getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);
						ls_idSubProceso     = getIdSubproceso();

						if(StringUtils.isValidString(ls_idSubProceso))
						{
							if(ls_intervinoEnTramitePregunta.equalsIgnoreCase(EstadoCommon.S))
							{
								if(
								    CollectionUtils.isValidCollection(lcp_cllPersona)
									    && !ls_idSubProceso.equalsIgnoreCase(
									        SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA
									    )
								)
								{
									lr_registro.setListadoPersona(lcp_cllPersona);
									setRendered(true);
									setMostrarPanelConsultaInteresado(false);
									setPersonaConsultada(true);
								}
								else
								{
									lr_registro.setListadoPersona(null);

									setRendered(false);
									setMostrarPanelConsultaInteresado(true);
									setPersonaConsultada(false);
								}

								BeanDireccion lbd_beanDireccion;

								lbd_beanDireccion = getBeanDireccion();

								ls_solicitud.setIdAutorizacionNotificacion(null);
								ls_solicitud.setIdAutorizacionComunicacion(null);
								//Calidad apoderado por defecto para la opción Si
								ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.INTERVINIENTE);
								lbd_beanDireccion.setHabilitadoPorConsulta(false);
								//Limpia la persona actual
								setPersona(null);
								//Permite editar los datos básicos
								setDeshabilitarDatosBasicos(true);
								setMostrarDatosConsulta(false);
								//Limpia medio notificación
								limpiarMedioNo();
								//Limpia medio comunicación
								limpiarMedioCo();
								setMostrarDatosBasicos(false);
								setConsultaSinRegistro(false);
								setHabilitadoPorConsulta(false);
								cargarMediosNotCom(false);
								setPersonaRepresentante(null);
								setPersonaARepresentar(null);
							}
							else
							{
								if(
								    ls_idSubProceso.equalsIgnoreCase(
									        SubProcesoCommon.DEVOLUCION_TURNO_DE_SERVICIO_REGISTRAL
									    )
								)
								{
									if(CollectionUtils.isValidCollection(lcp_cllPersona))
									{
										//Se setea colección encontrada para seleccionar interviniente
										setIntervinientesDevDinero(lcp_cllPersona);
										//Limpia calidad apoderado opción No
										ls_solicitud.setIdCalidadSolicitante(null);
										//Limpia la lista de personas que se llena despúes de la consulta
										lr_registro.setListadoPersona(null);
										//Limpia la persona actual
										setPersona(null);
										//Indica que la persona no ha sido consultada
										setPersonaConsultada(false);
										//Permite mostrar o no los registros consultados(la colección de personas).
										setRendered(false);
										//Muestra el panel de busqueda del interesado
										setMostrarPanelConsultaInteresado(true);
										//Permite editar los datos básicos
										setDeshabilitarDatosBasicos(true);
										setHabilitadoPorConsulta(false);
										//Limpia medio notificación
										limpiarMedioNo();
										//Limpia medio comunicación
										limpiarMedioCo();
										//Limpia sección Tipo de documento de consulta
										setDocumentoInteresado(null);
										//Limpia sección Número de documento de consulta
										setTipoDocInteresado(null);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_SIN_INTERVINIENTES_DEV_DINERO);
								}
								else if(
								    ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA)
								)
								{
									{
										BeanDireccion lbd_beanDireccion;

										lbd_beanDireccion = getBeanDireccion();

										if(lbd_beanDireccion != null)
											lbd_beanDireccion.setHabilitadoPorConsulta(false);
									}

									ls_solicitud.setIdAutorizacionNotificacion(null);
									ls_solicitud.setIdAutorizacionComunicacion(null);
									ls_solicitud.setIdCalidadSolicitante(null);

									setPersona(null);
									setMostrarPanelConsultaInteresado(true);
									setDeshabilitarDatosBasicos(true);
									setMostrarDatosConsulta(false);
									limpiarMedioNo();
									limpiarMedioCo();
									setMostrarDatosBasicos(false);
									setConsultaSinRegistro(false);
									setHabilitadoPorConsulta(false);
									cargarMediosNotCom(false);
								}
							}
						}

						setMostrarPanelInteresado(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validacionesIntervinoEnTramitePregunta", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método para validar la sección de datos cuenta bancaria.
	 *
	 * @param afc_context Objeto FacesContext de la instancia a validar
	 * @param ab_focus booleana foco
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarTabDatosCuentaBancaria(FacesContext afc_context, boolean ab_focus)
	    throws B2BException
	{
		{
			String ls_entidadRecaudadora;

			ls_entidadRecaudadora     = getIdEntidadRecaudadora();

			ab_focus = validateStyles(
				    is_idForm + ":idEntidadRecaudadora", afc_context, ls_entidadRecaudadora, ab_focus
				);

			if(!ab_focus)
				throw new B2BException(ErrorKeys.DEBE_INGRESAR_NOMBRE_ENTIDAD_RECAUDADORA);
		}

		{
			String ls_tipoCuenta;

			ls_tipoCuenta     = getTipoCuenta();

			ab_focus = validateStyles(is_idForm + ":idTipoCuenta", afc_context, ls_tipoCuenta, ab_focus);

			if(!ab_focus)
				throw new B2BException(ErrorKeys.DEBE_INGRESAR_TIPO_CUENTA);
		}

		{
			String ls_numeroCuenta;

			ls_numeroCuenta     = getNumeroCuenta();

			ab_focus = validateStyles(is_idForm + ":idNumeroCuenta", afc_context, ls_numeroCuenta, ab_focus);

			if(!ab_focus)
				throw new B2BException(ErrorKeys.DEBE_INGRESAR_NUMERO_CUENTA);
		}
	}

	/**
	 * Método para validar la sección de datos del interesado.
	 *
	 * @param afc_context Objeto FacesContext de la instancia a validar
	 * @param ab_focus booleana foco
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarTabDatosInteresado(FacesContext afc_context, boolean ab_focus)
	    throws B2BException
	{
		{
			String ls_intervinoEnTramitePregunta;

			ls_intervinoEnTramitePregunta     = getIntervieneTramiteDevDinero();

			ab_focus = validateStyles(
				    is_idForm + ":idIntervinoEnTramitePregunta", afc_context, ls_intervinoEnTramitePregunta, ab_focus
				);

			if(!ab_focus)
				throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION_PARA_PREGUNTA);

			{
				String ls_preguntaIntervieneTitularCuentaBancaria;

				ls_preguntaIntervieneTitularCuentaBancaria     = getTitularCuentaDevDinero();

				ab_focus = validateStyles(
					    is_idForm + ":idIntervieneTitularCuentaBancaria", afc_context,
					    ls_preguntaIntervieneTitularCuentaBancaria, ab_focus
					);

				if(!ab_focus)
					throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION_PARA_PREGUNTA);
			}

			if(
			    StringUtils.isValidString(ls_intervinoEnTramitePregunta)
				    && ls_intervinoEnTramitePregunta.equalsIgnoreCase(EstadoCommon.N)
			)
			{
				Collection<Persona> lcp_personaIntervinientesRepresentar;

				lcp_personaIntervinientesRepresentar = getIntervinientesDevDinero();

				if(CollectionUtils.isValidCollection(lcp_personaIntervinientesRepresentar))
				{
					int li_countUnoSeleccionado;

					li_countUnoSeleccionado = 0;

					for(Persona lp_temp : lcp_personaIntervinientesRepresentar)
					{
						if((lp_temp != null) && lp_temp.isSeleccionado())
							li_countUnoSeleccionado++;
					}

					if(li_countUnoSeleccionado == 0)
						throw new B2BException(ErrorKeys.ERROR_SELECCION_INTERVINIENTE_REPRESENTAR);
				}
			}
		}
	}

	/**
	 * Método para validar la sección de datos trámite.
	 *
	 * @param afc_context Objeto FacesContext de la instancia a validar
	 * @param ab_focus booleana foco
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarTabDatosTramite(FacesContext afc_context, boolean ab_focus)
	    throws B2BException
	{
		{
			String ls_idSubProceso;

			ls_idSubProceso     = getIdSubproceso();

			ab_focus = validateStyles(is_idForm + ":idseleccionTipoDevolucion", afc_context, ls_idSubProceso, ab_focus);

			if(!ab_focus)
				throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION);

			if(StringUtils.isValidString(ls_idSubProceso))
			{
				if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_TURNO_DE_SERVICIO_REGISTRAL))
				{
					DevolucionDineroUI lddu_ddu;

					lddu_ddu = getIntFinalParaFlujo();

					if(lddu_ddu != null)
					{
						String ls_idProcesoAnterior;

						ls_idProcesoAnterior = lddu_ddu.getIdProcesoAnterior();

						if(StringUtils.isValidString(ls_idProcesoAnterior))
						{
							if(ls_idProcesoAnterior.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
							{
								{
									String ls_consultaTurnoONir;

									ls_consultaTurnoONir     = getConsultaTurnoONir();

									ab_focus = validateStyles(
										    IdentificadoresCommon.DOS_PUNTOS + is_idForm + ":idNirOTurno", afc_context,
										    ls_consultaTurnoONir, ab_focus
										);

									if(!ab_focus)
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR__CONSULTA__POR_NIR_O_TURNO);
								}

								{
									Collection<ActoDevolucionDinero> lcadd_add;

									lcadd_add = getActosDevolucionDinero();

									if(CollectionUtils.isValidCollection(lcadd_add))
									{
										boolean lb_unoSeleccionado;

										lb_unoSeleccionado = false;

										for(ActoDevolucionDinero ladd_tmp : lcadd_add)
										{
											if((ladd_tmp != null) && !lb_unoSeleccionado && ladd_tmp.isSeleccionado())
												lb_unoSeleccionado = true;
										}

										if(!lb_unoSeleccionado)
											throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_ACTO);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DEBE_RALIZAR_BUSQUEDA_REGISTROS);
								}
							}
						}
					}
				}
				else if(ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.DEVOLUCION_CONSIGNACION_ERRADA))
				{
					DevolucionDinero ldd_devolucionDinero;

					ldd_devolucionDinero = getDevolucionDinero();

					if(ldd_devolucionDinero != null)
					{
						{
							String ls_tipoConsignacion;

							ls_tipoConsignacion = ldd_devolucionDinero.getTipoConsignacion();

							validateStyles(
							    is_idForm + ":idSoMTipoConsignacion", afc_context, ls_tipoConsignacion, true
							);

							if(!StringUtils.isValidString(ls_tipoConsignacion))
								throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_TIPO_CONSIGNACION);
						}

						{
							String ls_numeroConsignacion;

							ls_numeroConsignacion = ldd_devolucionDinero.getNumeroConsignacionErrada();

							validateStyles(
							    is_idForm + ":idItNumeroConsignacion", afc_context, ls_numeroConsignacion, true
							);

							if(!StringUtils.isValidString(ls_numeroConsignacion))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NUMERO_CONSIGNACION);
						}

						{
							String ls_nombreEntidad;

							ls_nombreEntidad = ldd_devolucionDinero.getCodEntidadConsignacionErrada();

							validateStyles(is_idForm + ":idSoMNombreEntidad", afc_context, ls_nombreEntidad, true);

							if(!StringUtils.isValidString(ls_nombreEntidad))
								throw new B2BException(ErrorKeys.ERROR_DEBE_SELECCIONAR_NOMBRE_DE_LA_ENTIDAD);
						}

						{
							Date ld_fechaConsignacion;

							ld_fechaConsignacion = ldd_devolucionDinero.getFechaConsignacionErrada();

							validateStyles(
							    is_idForm + ":idCalFechaConsignacion", afc_context, ld_fechaConsignacion, true
							);

							if(ld_fechaConsignacion == null)
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_FECHA_CONSIGNACION);

							if(ld_fechaConsignacion.after(new Date()))
								throw new B2BException(ErrorKeys.ERROR_FECHA_CONSIGNACION_NO_SUPERIOR_FECHA_ACTUAL);
						}

						{
							String ls_numeroCuenta;

							ls_numeroCuenta = ldd_devolucionDinero.getNumeroCuentaConsignacionErrada();

							validateStyles(is_idForm + ":idItNumeroCuentaBancaria", afc_context, ls_numeroCuenta, true);

							if(!StringUtils.isValidString(ls_numeroCuenta))
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_NUMERO_CUENTA_BANCARIA);
						}

						{
							Double ld_valor;
							String ls_idValor;

							ld_valor       = ldd_devolucionDinero.getValorConsignacionErrada();
							ls_idValor     = ":idINValor";

							validateStyles(is_idForm + ls_idValor, afc_context, ld_valor, true);

							if(ld_valor == null)
								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_VALOR);

							if(!NumericUtils.isValidDouble(ld_valor, 1D))
							{
								Double ld_tmp;

								ld_tmp = null;

								validateStyles(is_idForm + ls_idValor, afc_context, ld_tmp, true);

								throw new B2BException(ErrorKeys.ERROR_DEBE_DIGITAR_VALOR_VALIDO);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Validar turno seleccionado.
	 *
	 * @param ladd_acto de ladd acto
	 * @throws B2BException de b 2 B exception
	 */
	public void validarTurnoSeleccionado(ActoDevolucionDinero ladd_acto)
	    throws B2BException
	{
		if((ladd_acto != null) && ladd_acto.isSeleccionado())
		{
			try
			{
				String ls_idActo;

				ls_idActo = ladd_acto.getIdActoDevolucionDinero();

				if(StringUtils.isValidString(ls_idActo))
				{
					Documento ld_documento;

					ld_documento = getTurnoRecepcion();

					if(ld_documento != null)
					{
						String ls_idTurno;

						ls_idTurno = ld_documento.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							ActoDevolucionDinero ladd_temp;

							ladd_temp = idd_devolucionesDineroRemote.validarTurnoSeleccionado(ls_idActo, ls_idTurno);

							if(ladd_temp != null)
							{
								ladd_acto.setSeleccionado(false);
								throw new B2BException(
								    "El acto seleccionado ya tiene un proceso de devolución de dinero."
								);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(is_idForm + ":globalMsg");
			}
		}
	}

	/**
	 * Método para validar si el titular.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void validarTitularCuentaS()
	    throws B2BException
	{
		Solicitud ls_solicitud;

		ls_solicitud = getSolicitud();

		if(ls_solicitud != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = ls_solicitud.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				DevolucionDinero ldd_devolucionDinero;

				ldd_devolucionDinero = idd_devolucionesDineroRemote.findDevolucionDineroByIdSolicitud(ls_idSolicitud);

				if(ldd_devolucionDinero != null)
				{
					String ls_titularCuenta;

					ls_titularCuenta = ldd_devolucionDinero.getTitularCuenta();

					if(StringUtils.isValidString(ls_titularCuenta))
					{
						if(ls_titularCuenta.equalsIgnoreCase(EstadoCommon.S))
						{
							String ls_idPersonaTitular;

							ls_idPersonaTitular = ldd_devolucionDinero.getIdPersonaTitularCuenta();

							if(StringUtils.isValidString(ls_idPersonaTitular))
							{
								Persona lp_personaTitular;

								lp_personaTitular = new Persona();

								lp_personaTitular.setIdPersona(ls_idPersonaTitular);

								lp_personaTitular = irr_registroRemote.findPersonaByIdPersona(
									    lp_personaTitular, getUserId()
									);

								if(lp_personaTitular != null)
								{
									setPersonaTitularCuenta(lp_personaTitular);
									setTitularCuentaS(true);
								}
							}
						}
						else if(ls_titularCuenta.equalsIgnoreCase(EstadoCommon.N))
							setTitularCuentaS(false);
					}
				}
			}
		}
	}
}
