package com.bachue.snr.prosnr01.web.bean.recursos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanActuacionesAdministrativas;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanRecursos.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanRechazaRecurso")
public class BeanRechazaRecurso extends BeanActuacionesAdministrativas implements Serializable
{
	private static final long serialVersionUID = 712487367397333682L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fRechazaRecurso";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanRechazaRecurso.class);

	/** Constante CS_ID_GROWL. */
	private static final String CS_ID_GROWL = CS_ID_FORM + ":globalMsg";

	/** Atributo laar_actuacionesAdministrativasRemote */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad iccrr_causalesRechazaRecurso */
	private Collection<CausalRechazoRecurso> iccrr_causalesRechazaRecurso;

	/** Atributo irr registro remote */
	@EJB
	private RegistroRemote irr_registroRemote;

	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param accrr_ccrr valor de la propiedad.
	 */
	public void setCausalesRechazaRecurso(Collection<CausalRechazoRecurso> accrr_ccrr)
	{
		iccrr_causalesRechazaRecurso           = accrr_ccrr;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public Collection<CausalRechazoRecurso> getCausalesRechazaRecurso()
	{
		return iccrr_causalesRechazaRecurso;
	}

	/**
	 * Método sobrecargado para cargar o no datos personales
	 *
	 * @param ap_pcorrespondiente al valor del tipo de objeto Persona
	 */
	public void accionBotonEditarPersonaRecurso(Persona ap_p)
	{
		accionBotonEditarPersona(ap_p, true);
		setDeshabilitarDatosBasicos(false);
		setMostrarDatosPersona(true);
		setMostrarPanelConsulta(false);
	}

	/**
	 * Se encarga de cargar en pantalla los datos de una persona seleccionada
	 * en la consulta por documento.
	 *
	 * @param ap_persona Objeto contenedor de la información de la persona que se va a cargar
	 * en pantalla
	 */
	public void cargarDatosPersonalesRecursos(Persona ap_persona)
	{
		try
		{
			if(ap_persona != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = ap_persona.getSolicitud();

				if(ls_solicitud != null)
				{
					setMostrarDatosBasicos(false);
					setConsultaSinRegistro(false);
					setPersonaConsultada(true);
					setHabilitadoPorConsulta(true);

					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						if(lbd_beanDireccion != null)
							lbd_beanDireccion.setHabilitadoPorConsulta(true);
					}

					cargarDatosPersonales(ap_persona, TipoRecepcionCommon.ACTUACIONES_ADMINISTRATIVAS);

					setMostrarDatosPersona(true);
					setMostrarPanelConsulta(false);

					{
						String ls_idAutorizacionNotificacion;
						String ls_idAutorizacionComunicacion;

						ls_idAutorizacionNotificacion     = StringUtils.getStringNotNull(
							    ls_solicitud.getIdAutorizacionNotificacion()
							);
						ls_idAutorizacionComunicacion     = StringUtils.getStringNotNull(
							    ls_solicitud.getIdAutorizacionComunicacion()
							);

						cambioDeMedioNotInteresado(ls_idAutorizacionNotificacion);
						cambioDeMedioComInteresado(ls_idAutorizacionComunicacion);

						{
							Registro lr_registro;

							lr_registro = new Registro();

							lr_registro.setSolicitud(ls_solicitud);
							lr_registro.setPersona(ap_persona);

							lr_registro = iaar_actuacionesAdministrativasRemote.buscarDatosPorPersona(
								    lr_registro, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							if(lr_registro != null)
							{
								BeanDireccion lbd_beanDireccion;

								lbd_beanDireccion = getBeanDireccion();

								if(lbd_beanDireccion != null)
								{
									{
										PersonaDireccion lpd_personaDireccion;
										boolean          lb_existe;

										lpd_personaDireccion     = lr_registro.getDireccionResidencia();
										lb_existe                = lpd_personaDireccion != null;

										lbd_beanDireccion.setDireccionResidencia(
										    lb_existe ? lpd_personaDireccion : null
										);
										lbd_beanDireccion.setDeshabilitarResidencia(lb_existe);
										setDeshabilitarResidencia(lb_existe);
									}

									{
										PersonaDireccion lpd_personaDireccion;
										boolean          lb_existe;

										lpd_personaDireccion     = lr_registro.getDireccionCorrespondencia();
										lb_existe                = lpd_personaDireccion != null;

										lbd_beanDireccion.setDireccionCorrespondencia(
										    lb_existe ? lpd_personaDireccion : null
										);
										lbd_beanDireccion.setDeshabilitarCorrespondencia(lb_existe);
										setDeshabilitarCorrespondencia(lb_existe);
									}

									{
										PersonaTelefono lpd_personaTelefono;
										boolean         lb_existe;

										lpd_personaTelefono     = lr_registro.getTelefonoFijo();
										lb_existe               = lpd_personaTelefono != null;

										setTelefonoFijo(lpd_personaTelefono);
										findIndicativoDepartamento();

										setDeshabilitarTelFijo(lb_existe);
									}

									{
										PersonaTelefono lpd_personaTelefono;
										boolean         lb_existe;

										lpd_personaTelefono     = lr_registro.getTelefonoMovil();
										lb_existe               = lpd_personaTelefono != null;

										setTelefonoMovil(lpd_personaTelefono);
										setDeshabilitarTelMovil(lb_existe);
									}

									{
										PersonaCorreoElectronico lpd_personaCorreo;
										boolean                  lb_existe;

										lpd_personaCorreo     = lr_registro.getPersonaCorreoElectronico();
										lb_existe             = lpd_personaCorreo != null;

										setCorreoElectronico(lpd_personaCorreo);
										setDeshabilitarCorreo(lb_existe);
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
			clh_LOGGER.error("cargarDatosRechazaRecursos", lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo encargado de cargar la información de rechaza recurso.
	 * @throws B2BException
	 */
	public void cargarDatosRechazaRecursos()
	    throws B2BException
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setIdMotivo(getIdMotivo());

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.cargarDatosRechazaRecursos(
				    laa_actuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
			{
				byte[] lba_archivo;

				lba_archivo = laa_actuacionesAdministrativas.getArchivo();

				setImagenDocumento(
				    generarArchivosDescarga(
				        lba_archivo, TipoContenidoCommon.PDF,
				        IdentificadoresCommon.RESOLUCION + ExtensionCommon.PDF_PUNTO
				    )
				);

				setArchivo(lba_archivo);
				setSolicitud(laa_actuacionesAdministrativas.getSolicitud());
				setCausalesRechazaRecurso(laa_actuacionesAdministrativas.getCausalesRechazaRecurso());
				setOficiosTexto(laa_actuacionesAdministrativas.getOficiosTexto());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosRechazaRecursos", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Metodo encargado de descargar Zip de actuaciones administrativas.
	 */
	public void descargarZipRechazoRecurso()
	{
		byte[] lba_file;

		lba_file = getFile();

		if(lba_file != null)
		{
			setZip(
			    generarArchivosDescarga(
			        lba_file, TipoContenidoCommon.ZIP, IdentificadoresCommon.RECHAZO_RECURSO
			        + ExtensionCommon.ZIP_PUNTO
			    )
			);

			setMostrarEnviarResponsable(true);

			actualizarComponente(CS_ID_FORM + ":opBotones");
		}
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 */
	public String enviarARecursos()
	{
		return enviarResponsableActuacionesAdmin(
		    true,
		    isEtapa460() ? EtapaCommon.ID_ETAPA_460 : EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS
		);
	}

	/**
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 */
	public void generarDocumentosRechazaRecurso()
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(getOficiosTexto()));
			laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
			laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setResultadosNotificacion(getResultadosNotificacion());
			laa_actuacionesAdministrativas.setCausalesRechazaRecurso(getCausalesRechazaRecurso());
			laa_actuacionesAdministrativas.setIdMotivo(getIdMotivo());
			laa_actuacionesAdministrativas.setSegundaInstancia(isEtapa460());

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.generarDocumentosRechazaRecurso(
				    laa_actuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(laa_actuacionesAdministrativas != null)
			{
				PrimeFaces.current().executeScript("PF('wvPoll').start();");

				setMostrarDescargarZip(true);
				setDocumentosEnviados(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosRechazaRecurso", lb2be_e);

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
	public void mostrarInteresadoIntervinienteRecursos()
	{
		setMostrarConsultarInteresadoInter(true);
		setMostrarDatosPersona(true);
		setMostrarPanelConsulta(true);
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucionRechazaRecurso()
	{
		try
		{
			TagPlantillaResolucion laa_actuacionesAdministrativas;

			laa_actuacionesAdministrativas = new TagPlantillaResolucion();

			laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
			laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
			laa_actuacionesAdministrativas.setSolicitud(getSolicitud());
			laa_actuacionesAdministrativas.setOficiosTexto(convertirOficiosTexto(getOficiosTexto()));
			laa_actuacionesAdministrativas.setCausalesRechazaRecurso(getCausalesRechazaRecurso());

			laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
					.visualizarCambiosResolucionRechazaRecurso(
					    laa_actuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

			if(laa_actuacionesAdministrativas != null)
				setArchivo(laa_actuacionesAdministrativas.getArchivo());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucionRechazaRecurso", lb2be_e);

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

		return NavegacionCommon.RECHAZA_RECURSO;
	}
}
