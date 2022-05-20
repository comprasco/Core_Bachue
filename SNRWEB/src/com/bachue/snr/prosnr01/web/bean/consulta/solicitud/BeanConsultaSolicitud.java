package com.bachue.snr.prosnr01.web.bean.consulta.solicitud;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.solicitud.ConsultaSolicitudRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.consulta.solicitud.Predio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Segregacion;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Solicitud;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Tramite;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.ToggleEvent;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaSolicitud.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanConsultaSolicitud")
@SessionScoped
public class BeanConsultaSolicitud extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -733710052651754693L;

	/** Constante is_messageIdGrowl. */
	private static final String is_messageIdGrowl = "fSolicitud:globalMsg";

	/** Propiedad ica datos acto. */
	private Collection<com.bachue.snr.prosnr01.model.registro.Acto> ica_datosActo;

	/** Propiedad icp predios. */
	private Collection<Predio> icp_predios;

	/** Propiedad icps personas solicitud. */
	private Collection<PersonaSolicitud> icps_personasSolicitud;

	/** Propiedad ics segregacion. */
	private Collection<Segregacion> ics_segregacion;

	/** Propiedad ict tramites. */
	private Collection<Tramite> ict_tramites;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_trazabilidad;

	/** Propiedad ict trazabilidad vinculados. */
	private Collection<Trazabilidad> ict_trazabilidadVinculados;

	/** Propiedad ictd tipos documentales. */
	private Collection<TipoDocumental> ictd_tiposDocumentales;

	/** Propiedad icsr remote. */
	@EJB
	private ConsultaSolicitudRemote icsr_remote;

	/** Propiedad ictr consulta trazabilidad remote. */
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad is datos documento. */
	private Documento is_datosDocumento;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is solicitud datos consultados. */
	private Solicitud is_solicitudDatosConsultados;

	/** Propiedad is solicitud datos consultados int. */
	private Solicitud is_solicitudDatosConsultadosInt;

	/** Propiedad is codigo. */
	private String is_codigo;

	/** Propiedad is id acto. */
	private String is_idActo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir consultado. */
	private String is_nirConsultado;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/** Propiedad ib mostrar atras. */
	private boolean ib_mostrarAtras;

	/** Propiedad ib ocultar retorno. */
	private boolean ib_ocultarRetorno;

	/** Propiedad ib ocultar siguiente. */
	private boolean ib_ocultarSiguiente;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de codigo.
	 *
	 * @param as_s asigna el valor a la propiedad codigo
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna el valor de codigo.
	 *
	 * @return el valor de codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de datos acto.
	 *
	 * @param aca_ca asigna el valor a la propiedad datos acto
	 */
	public void setDatosActo(Collection<com.bachue.snr.prosnr01.model.registro.Acto> aca_ca)
	{
		ica_datosActo = aca_ca;
	}

	/**
	 * Retorna el valor de datos acto.
	 *
	 * @return el valor de datos acto
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> getDatosActo()
	{
		return ica_datosActo;
	}

	/**
	 * Modifica el valor de datos documento.
	 *
	 * @param as_d asigna el valor a la propiedad datos documento
	 */
	public void setDatosDocumento(Documento as_d)
	{
		is_datosDocumento = as_d;
	}

	/**
	 * Retorna el valor de datos documento.
	 *
	 * @return el valor de datos documento
	 */
	public Documento getDatosDocumento()
	{
		return is_datosDocumento;
	}

	/**
	 * Modifica el valor de datos tramite.
	 *
	 * @param act_ct asigna el valor a la propiedad datos tramite
	 */
	public void setDatosTramite(Collection<Tramite> act_ct)
	{
		ict_tramites = act_ct;
	}

	/**
	 * Retorna el valor de datos tramite.
	 *
	 * @return el valor de datos tramite
	 */
	public Collection<Tramite> getDatosTramite()
	{
		if(ict_tramites == null)
			ict_tramites = new ArrayList<Tramite>();

		return ict_tramites;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param ab_b asigna el valor a la propiedad estado
	 */
	public void setEstado(boolean ab_b)
	{
		ib_estado = ab_b;
	}

	/**
	 * Valida la propiedad estado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado
	 */
	public boolean isEstado()
	{
		return ib_estado;
	}

	/**
	 * Modifica el valor de id acto.
	 *
	 * @param as_s asigna el valor a la propiedad id acto
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna el valor de id acto.
	 *
	 * @return el valor de id acto
	 */
	public String getIdActo()
	{
		return is_idActo;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de mostrar atras.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar atras
	 */
	public void setMostrarAtras(boolean ab_b)
	{
		ib_mostrarAtras = ab_b;
	}

	/**
	 * Valida la propiedad mostrar atras.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar atras
	 */
	public boolean isMostrarAtras()
	{
		return ib_mostrarAtras;
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
	 * Modifica el valor de nir consultado.
	 *
	 * @param nirConsultado asigna el valor a la propiedad nir consultado
	 */
	public void setNirConsultado(String nirConsultado)
	{
		this.is_nirConsultado = nirConsultado;
	}

	/**
	 * Retorna el valor de nir consultado.
	 *
	 * @return el valor de nir consultado
	 */
	public String getNirConsultado()
	{
		return is_nirConsultado;
	}

	/**
	 * Modifica el valor de ocultar retorno.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar retorno
	 */
	public void setOcultarRetorno(boolean ab_b)
	{
		ib_ocultarRetorno = ab_b;
	}

	/**
	 * Valida la propiedad ocultar retorno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar retorno
	 */
	public boolean isOcultarRetorno()
	{
		return ib_ocultarRetorno;
	}

	/**
	 * Modifica el valor de ocultar siguiente.
	 *
	 * @param ab_b asigna el valor a la propiedad ocultar siguiente
	 */
	public void setOcultarSiguiente(boolean ab_b)
	{
		ib_ocultarSiguiente = ab_b;
	}

	/**
	 * Valida la propiedad ocultar siguiente.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ocultar siguiente
	 */
	public boolean isOcultarSiguiente()
	{
		return ib_ocultarSiguiente;
	}

	/**
	 * Modifica el valor de personas solicitud.
	 *
	 * @param acps_personasSolicitud asigna el valor a la propiedad personas solicitud
	 */
	public void setPersonasSolicitud(Collection<PersonaSolicitud> acps_personasSolicitud)
	{
		icps_personasSolicitud = acps_personasSolicitud;
	}

	/**
	 * Retorna el valor de personas solicitud.
	 *
	 * @return el valor de personas solicitud
	 */
	public Collection<PersonaSolicitud> getPersonasSolicitud()
	{
		return icps_personasSolicitud;
	}

	/**
	 * Modifica el valor de predios.
	 *
	 * @param acp_cp asigna el valor a la propiedad predios
	 */
	public void setPredios(Collection<Predio> acp_cp)
	{
		icp_predios = acp_cp;
	}

	/**
	 * Retorna el valor de predios.
	 *
	 * @return el valor de predios
	 */
	public Collection<Predio> getPredios()
	{
		if(icp_predios == null)
			icp_predios = new ArrayList<Predio>();

		return icp_predios;
	}

	/**
	 * Modifica el valor de segregacion.
	 *
	 * @param acs_cs asigna el valor a la propiedad segregacion
	 */
	public void setSegregacion(Collection<Segregacion> acs_cs)
	{
		ics_segregacion = acs_cs;
	}

	/**
	 * Retorna el valor de segregacion.
	 *
	 * @return el valor de segregacion
	 */
	public Collection<Segregacion> getSegregacion()
	{
		if(!CollectionUtils.isValidCollection(ics_segregacion))
			ics_segregacion = new ArrayList<Segregacion>();

		return ics_segregacion;
	}

	/**
	 * Modifica el valor de solicitud datos consultados.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud datos consultados
	 */
	public void setSolicitudDatosConsultados(Solicitud as_s)
	{
		is_solicitudDatosConsultados = as_s;
	}

	/**
	 * Retorna el valor de solicitud datos consultados.
	 *
	 * @return el valor de solicitud datos consultados
	 */
	public Solicitud getSolicitudDatosConsultados()
	{
		if(is_solicitudDatosConsultados == null)
			is_solicitudDatosConsultados = new Solicitud();

		return is_solicitudDatosConsultados;
	}

	/**
	 * Modifica el valor de solicitud datos consultados int.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud datos consultados int
	 */
	public void setSolicitudDatosConsultadosInt(Solicitud as_s)
	{
		is_solicitudDatosConsultadosInt = as_s;
	}

	/**
	 * Retorna el valor de solicitud datos consultados int.
	 *
	 * @return el valor de solicitud datos consultados int
	 */
	public Solicitud getSolicitudDatosConsultadosInt()
	{
		if(is_solicitudDatosConsultadosInt == null)
			is_solicitudDatosConsultadosInt = new Solicitud();

		return is_solicitudDatosConsultadosInt;
	}

	/**
	 * Modifica el valor de tipos documentales.
	 *
	 * @param actd_ctd asigna el valor a la propiedad tipos documentales
	 */
	public void setTiposDocumentales(Collection<TipoDocumental> actd_ctd)
	{
		ictd_tiposDocumentales = actd_ctd;
	}

	/**
	 * Retorna el valor de tipos documentales.
	 *
	 * @return el valor de tipos documentales
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoDocumental> getTiposDocumentales()
	    throws B2BException
	{
		return ictd_tiposDocumentales;
	}

	/**
	 * Modifica el valor de trazabilidad.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidad = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<Trazabilidad> getTrazabilidad()
	{
		return ict_trazabilidad;
	}

	/**
	 * Modifica el valor de trazabilidad vinculados.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad vinculados
	 */
	public void setTrazabilidadVinculados(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidadVinculados = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad vinculados.
	 *
	 * @return el valor de trazabilidad vinculados
	 */
	public Collection<Trazabilidad> getTrazabilidadVinculados()
	{
		return ict_trazabilidadVinculados;
	}

	/**
	 * Cargar actos.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarActos(String as_idSolicitud)
	{
		try
		{
			Collection<com.bachue.snr.prosnr01.model.registro.Acto> lca_actos;

			lca_actos = icsr_remote.findByIdSolicitud(as_idSolicitud);

			if(lca_actos != null)
				setDatosActo(lca_actos);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_ACTOS));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar documento.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarDocumento(String as_idSolicitud)
	{
		try
		{
			Documento ls_resultado;

			ls_resultado = icsr_remote.findDocumentoByIdSolicitud(as_idSolicitud);

			if(ls_resultado != null)
				setDatosDocumento(ls_resultado);

			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARO_DOCUMENTO));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar interesado.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarInteresado(String as_idSolicitud)
	{
		try
		{
			Solicitud ls_resultado;

			ls_resultado = icsr_remote.findInteresados(as_idSolicitud, true);

			if(ls_resultado != null)
				setSolicitudDatosConsultados(ls_resultado);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_SOLICITUDES));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar interviniente.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarInterviniente(String as_idSolicitud)
	{
		try
		{
			Solicitud ls_resultado;

			ls_resultado = icsr_remote.findInteresados(as_idSolicitud, false);

			if(ls_resultado != null)
				setSolicitudDatosConsultadosInt(ls_resultado);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_SOLICITUDES));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar matricula.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarMatricula(String as_idSolicitud)
	{
		try
		{
			Collection<Predio> lcp_predios;

			lcp_predios = icsr_remote.findMatriculaBySolicitud(as_idSolicitud);

			if(lcp_predios != null)
				setPredios(lcp_predios);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_PREDIOS));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar segregacion.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarSegregacion(String as_idSolicitud)
	{
		try
		{
			Collection<Segregacion> lcs_segregacion;

			lcs_segregacion = icsr_remote.findSegregacion(as_idSolicitud);

			if(lcs_segregacion != null)
				setSegregacion(lcs_segregacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar solicitud.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarSolicitud(String as_idSolicitud)
	{
		try
		{
			Collection<PersonaSolicitud> ls_resultado;

			ls_resultado = icsr_remote.findPersonasByIdSolicitud(as_idSolicitud);

			if(CollectionUtils.isValidCollection(ls_resultado))
				setPersonasSolicitud(ls_resultado);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_SOLICITUDES));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar tramites.
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 */
	public void cargarTramites(String as_idSolicitud)
	{
		try
		{
			Collection<Tramite> lct_resultado;

			lct_resultado = icsr_remote.findDatosDelTramiteByidSolicitud(as_idSolicitud);

			if(lct_resultado != null)
				setDatosTramite(lct_resultado);
			else
			{
				addMessage("E", getMessages().getString(MessagesKeys.NO_SE_ENCONTRARON_TRAMITES));
				PrimeFaces.current().ajax().update(is_messageIdGrowl);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setCodigo(null);
		setEstado(false);
		setTrazabilidadVinculados(null);
		setDatosActo(null);
		setDatosDocumento(null);
		setDatosTramite(null);
		setIdActo(null);
		setIdSolicitud(null);
		setIdTurno(null);
		setMostrarAtras(false);
		setNir(null);
		setOcultarRetorno(false);
		setOcultarSiguiente(false);
		setPredios(null);
		setSegregacion(null);
		setSolicitudDatosConsultados(null);
		setSolicitudDatosConsultadosInt(null);
		setTiposDocumentales(null);
		setTrazabilidad(null);
		setNirConsultado(null);
	}

	/**
	 * Consulta la información correspondiente al nir o turno ingresados por pantalla.
	 */
	public void findAll()
	{
		try
		{
			String                                          ls_nir;
			String                                          ls_idTurno;
			String                                          ls_decisionCalifiacion;
			com.bachue.snr.prosnr01.model.sdb.acc.Solicitud ls_solicitud;
			Turno                                           lt_turno;
			Fases                                           lf_fases;
			Trazabilidad                                    lt_trazabilidad;
			Collection<Trazabilidad>                        lct_trazabilidad;

			ls_decisionCalifiacion     = null;
			ls_nir                     = getNir();
			ls_idTurno                 = getIdTurno();
			lf_fases                   = new Fases();
			ls_solicitud               = new com.bachue.snr.prosnr01.model.sdb.acc.Solicitud();
			lt_turno                   = new Turno();
			lt_trazabilidad            = new Trazabilidad();

			ls_solicitud.setNir(ls_nir);
			lt_turno.setIdTurno(ls_idTurno);

			lt_trazabilidad.setSolicitud(ls_solicitud);
			lt_trazabilidad.setTurno(lt_turno);
			lt_trazabilidad.setFases(lf_fases);

			lct_trazabilidad = ictr_consultaTrazabilidadRemote.findTrazabilidadVinculados(lt_trazabilidad);

			if(CollectionUtils.isValidCollection(lct_trazabilidad))
			{
				for(Trazabilidad lt_temp : lct_trazabilidad)
				{
					if(lt_temp != null)
					{
						ls_decisionCalifiacion = irr_referenceRemote.findDecisionCalificacion(lt_temp);

						lt_temp.setDecisionCalificacion(ls_decisionCalifiacion);
					}
				}

				setTrazabilidadVinculados(lct_trazabilidad);
				setEstado(true);
			}
			else
				setEstado(false);

			lct_trazabilidad = ictr_consultaTrazabilidadRemote.findTrazabilidad(lt_trazabilidad, false);

			if(CollectionUtils.isValidCollection(lct_trazabilidad))
			{
				boolean lb_sinNir;

				lb_sinNir            = true;
				lct_trazabilidad     = ictr_consultaTrazabilidadRemote.findTrazabilidad(lt_trazabilidad, false);

				for(Trazabilidad lt_temp : lct_trazabilidad)
				{
					if(lt_temp != null)
					{
						ls_decisionCalifiacion = irr_referenceRemote.findDecisionCalificacion(lt_temp);

						lt_temp.setDecisionCalificacion(ls_decisionCalifiacion);

						if(lb_sinNir)
						{
							com.bachue.snr.prosnr01.model.sdb.acc.Solicitud ls_solTmp;

							ls_solTmp = lt_temp.getSolicitud();

							if(ls_solTmp != null)
							{
								setNirConsultado(ls_solTmp.getNir());

								lb_sinNir = false;
							}
						}
					}
				}

				setTrazabilidad(lct_trazabilidad);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param afe_event correspondiente al valor del tipo de objeto FlowEvent
	 * @return devuelve el valor de String
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;
		ls_return = afe_event.getNewStep();

		try
		{
			String ls_oldStep;
			String ls_newStep;
			String ls_trazabilidad;
			String ls_datosInteresado_id;
			String ls_datosTramite;
			String ls_datosDocumento;
			String ls_datosActo_id;
			String ls_datosPredio;
			String ls_datosInterviniente_id;
			String ls_datosEntrega_id;

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();

			ls_trazabilidad              = "trazabilidad";
			ls_datosInteresado_id        = "datosInteresado_id";
			ls_datosTramite              = "datosTramite";
			ls_datosDocumento            = "datosDocumento";
			ls_datosActo_id              = "datosActo_id";
			ls_datosPredio               = "datosPredio";
			ls_datosInterviniente_id     = "datosInterviniente_id";
			ls_datosEntrega_id           = "datosEntrega_id";

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				if(ls_oldStep.equalsIgnoreCase(ls_trazabilidad))
				{
					Collection<Trazabilidad> lct_trazabilidad;
					lct_trazabilidad     = null;
					lct_trazabilidad     = getTrazabilidad();

					if(CollectionUtils.isValidCollection(lct_trazabilidad))
					{
						Iterator<Trazabilidad> lit_it;

						lit_it = lct_trazabilidad.iterator();

						if(lit_it.hasNext())
						{
							Trazabilidad lt_t;

							lt_t = lit_it.next();

							if(lt_t != null)
							{
								com.bachue.snr.prosnr01.model.sdb.acc.Solicitud ls_solicitud;

								ls_solicitud = lt_t.getSolicitud();

								setIdSolicitud(ls_solicitud.getIdSolicitud());
							}
						}

						cargarInteresado(getIdSolicitud());
						setMostrarAtras(true);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NO_EXISTEN_DATOS);
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_datosInteresado_id))
				{
					if(ls_newStep.equalsIgnoreCase(ls_trazabilidad))
					{
						setMostrarAtras(false);
						setOcultarRetorno(false);
					}
					else
						cargarTramites(getIdSolicitud());
				}

				else if(ls_oldStep.equalsIgnoreCase(ls_datosTramite))
					cargarDocumento(getIdSolicitud());
				else if(ls_oldStep.equalsIgnoreCase(ls_datosDocumento))
					cargarActos(getIdSolicitud());
				else if(ls_oldStep.equalsIgnoreCase(ls_datosActo_id))
				{
					cargarMatricula(getIdSolicitud());
					cargarSegregacion(getIdSolicitud());
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_datosPredio))
					cargarInterviniente(getIdSolicitud());
				else if(ls_oldStep.equalsIgnoreCase(ls_datosInterviniente_id))
				{
					cargarSolicitud(getIdSolicitud());

					if(ls_newStep.equalsIgnoreCase(ls_datosPredio))
					{
						setOcultarSiguiente(false);
						setOcultarRetorno(false);
					}
					else
					{
						setOcultarSiguiente(true);
						setOcultarRetorno(true);
					}
				}
				else if(ls_oldStep.equalsIgnoreCase(ls_datosEntrega_id))
				{
					setOcultarSiguiente(false);
					setOcultarRetorno(false);
				}

				if(ls_oldStep.equalsIgnoreCase(ls_datosEntrega_id) && ls_newStep.equalsIgnoreCase(ls_trazabilidad))
					setNirConsultado(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fConsultaSolicitud:globalMsg");
			ls_return = afe_event.getOldStep();
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoDocumental> obtenerTiposDocActo()
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_ctd;
		Collection<TipoDocumental> lctd_cts;

		lctd_ctd     = null;
		lctd_cts     = new ArrayList<TipoDocumental>();

		try
		{
			String ls_l;
			String ls_codigo;

			ls_codigo     = getCodigo();
			ls_l          = getIdActo();

			if(
			    NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_l))
				    && NumericUtils.isValidLong(NumericUtils.getLongWrapper(ls_codigo))
			)
			{
				lctd_ctd = irr_registroRemote.findAllDocumentales(
					    StringUtils.getString(ls_l), StringUtils.getString(ls_codigo), true, getIdSolicitud()
					);

				if(!CollectionUtils.isValidCollection(lctd_ctd))
					lctd_ctd = new ArrayList<TipoDocumental>();
				else
				{
					for(TipoDocumental lo_td : lctd_ctd)
					{
						lo_td.setTiposDoc(lctd_ctd);
						lctd_cts.add(lo_td);
					}

					lctd_ctd = lctd_cts;
				}
			}
		}

		catch(Exception e)
		{
			lctd_ctd = new ArrayList<TipoDocumental>();
		}

		return lctd_ctd;
	}

	/**
	 * On row toggle.
	 *
	 * @param ate_event correspondiente al valor del tipo de objeto ToggleEvent
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void onRowToggle(ToggleEvent ate_event)
	    throws B2BException
	{
		com.bachue.snr.prosnr01.model.registro.Acto lo_acto;

		lo_acto = ((com.bachue.snr.prosnr01.model.registro.Acto)ate_event.getData());

		if(ate_event.getVisibility().toString().equalsIgnoreCase("VISIBLE"))
		{
			if(lo_acto != null)
			{
				if(lo_acto.getCodigo() != getCodigo())
					setTiposDocumentales(null);

				setCodigo(lo_acto.getCodigo());
				setIdActo(lo_acto.getIdActoDb());

				setTiposDocumentales(obtenerTiposDocActo());
			}
		}
		else
			setCodigo(null);
	}

	/**
	 * Regresar.
	 *
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public void regresar()
	    throws IOException
	{
		setOcultarSiguiente(false);
		setOcultarRetorno(false);
		setTrazabilidad(null);
		setIdSolicitud(null);
		setNir(null);
		setMostrarAtras(false);
		setIdTurno(null);
		setNirConsultado(null);

		ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();
		lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
	}
}
