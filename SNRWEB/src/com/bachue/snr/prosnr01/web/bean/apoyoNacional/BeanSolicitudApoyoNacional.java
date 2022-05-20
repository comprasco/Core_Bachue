package com.bachue.snr.prosnr01.web.bean.apoyoNacional;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.OperadorCommon;
import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.apoyoNacional.ApoyoNacionalRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanPredioDocumentoActo;
import com.bachue.snr.prosnr01.web.bean.entrega.BeanDetalleEntrega;

import org.primefaces.PrimeFaces;

import org.primefaces.PrimeFaces.Ajax;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanSolicitudApoyoNacional.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 1/09/2020
 */
@SessionScoped
@ManagedBean(name = "beanSolicitudApoyoNacional")
public class BeanSolicitudApoyoNacional extends BeanDetalleEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6408551996561163109L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanSolicitudApoyoNacional.class);

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fSolicitudApoyoNacional";

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_GROWL = "fSolicitudApoyoNacional:idGrowl";

	/** Propiedad ianr apoyo nacional remote. */
	@EJB
	private ApoyoNacionalRemote ianr_apoyoNacionalRemote;

	/** Propiedad lct solicitud apoyo nacional. */
	private Collection<SolicitudApoyoNacional> icsan_solicitudesApoyoNacional;

	/** Propiedad lct turnos apoyo nacional. */
	private Collection<Turno> lct_turnosApoyoNacional;

	/** Propiedad lsanui solicitud apoyo nacional. */
	private SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;

	/** Propiedad ls id circulo. */
	private String is_idCirculo;

	/** Propiedad ls id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is mensaje terminar proceso. */
	private String is_mensajeTerminarProceso;

	/** Propiedad ls nir. */
	private String is_nir;

	/** Propiedad is nir generado. */
	private String is_nirGenerado;

	/** Propiedad ls nir. */
	private String is_nombreProceso;

	/** Propiedad ib documentos enviados. */
	private boolean ib_documentosEnviados;

	/** Propiedad ii total bandeja. */
	private boolean ib_finalizada;

	/** Propiedad ib proceso terminado. */
	private boolean ib_procesoTerminado;

	/** Propiedad ib rendered PDF terminado. */
	private boolean ib_renderedPDFTerminado;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/**
	 * Modifica el valor de DocumentosEnviados.
	 *
	 * @param ab_b de ab b
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Valida la propiedad documentos enviados.
	 *
	 * @return Retorna el valor de la propiedad documentosEnviados
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * Modifica el valor de finalizada.
	 *
	 * @param lb_b the finalizada to set
	 */
	public void setFinalizada(boolean lb_b)
	{
		ib_finalizada = lb_b;
	}

	/**
	 * Retorna Objeto o variable de valor finalizada.
	 *
	 * @return the finalizada
	 */
	public boolean isFinalizada()
	{
		return ib_finalizada;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de id solicitud
	 *
	 * @param as_s de as id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de mensaje terminar proceso.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje terminar proceso
	 */
	public void setMensajeTerminarProceso(String as_s)
	{
		is_mensajeTerminarProceso = as_s;
	}

	/**
	 * Retorna el valor de mensaje terminar proceso.
	 *
	 * @return el valor de mensaje terminar proceso
	 */
	public String getMensajeTerminarProceso()
	{
		return is_mensajeTerminarProceso;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s de as nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return Retorna el valor de la propiedad nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de nir generado.
	 *
	 * @param as_s asigna el valor a la propiedad nir generado
	 */
	public void setNirGenerado(String as_s)
	{
		is_nirGenerado = as_s;
	}

	/**
	 * Retorna el valor de nir generado.
	 *
	 * @return el valor de nir generado
	 */
	public String getNirGenerado()
	{
		return is_nirGenerado;
	}

	/**
	 * Modifica el valor de nombre proceso.
	 *
	 * @param as_s the nombre proceso. to set
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso
	 *
	 * @return the nombre proceso.
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de proceso terminado.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso terminado
	 */
	public void setProcesoTerminado(boolean ab_b)
	{
		ib_procesoTerminado = ab_b;
	}

	/**
	 * Valida la propiedad proceso terminado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado
	 */
	public boolean isProcesoTerminado()
	{
		return ib_procesoTerminado;
	}

	/**
	 * Modifica el valor de rendered PDF terminado.
	 *
	 * @param as_s asigna el valor a la propiedad rendered PDF terminado
	 */
	public void setRenderedPDFTerminado(boolean as_s)
	{
		ib_renderedPDFTerminado = as_s;
	}

	/**
	 * Valida la propiedad rendered PDF terminado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered PDF terminado
	 */
	public boolean isRenderedPDFTerminado()
	{
		return ib_renderedPDFTerminado;
	}

	/**
	 * Modifica el valor de SolicitudApoyoNacional.
	 *
	 * @param as_s de as s
	 */
	public void setSolicitudApoyoNacional(SolicitudApoyoNacionalUI as_s)
	{
		lsanui_solicitudApoyoNacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad solicitudApoyoNacional
	 */
	public SolicitudApoyoNacionalUI getSolicitudApoyoNacional()
	{
		return lsanui_solicitudApoyoNacional;
	}

	/**
	 * Modifica el valor de solicitud apoyo nacional.
	 *
	 * @param lcsan_csan de as solicitud apoyo nacional.
	 */
	public void setSolicitudesApoyoNacional(Collection<SolicitudApoyoNacional> lcsan_csan)
	{
		icsan_solicitudesApoyoNacional = lcsan_csan;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad solicitud apoyo nacional.
	 */
	public Collection<SolicitudApoyoNacional> getSolicitudesApoyoNacional()
	{
		return icsan_solicitudesApoyoNacional;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * Modifica el valor de TurnosApoyoNacional.
	 *
	 * @param ac_c de ac c
	 */
	public void setTurnosApoyoNacional(Collection<Turno> ac_c)
	{
		lct_turnosApoyoNacional = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor turnos apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad turnosApoyoNacional
	 */
	public Collection<Turno> getTurnosApoyoNacional()
	{
		return lct_turnosApoyoNacional;
	}

	/**
	 * Método para limpiar los parametros de la pantalla.
	 */
	public void clear()
	{
		setDocumentosEnviados(false);
		setTurnosApoyoNacional(null);
		setSolicitudesApoyoNacional(null);
		setNombreProceso(null);
		setIdSolicitud(null);
		setNir(null);
		setFinalizada(false);
		setNirGenerado(null);
		setIdCirculo(null);
		setProcesoTerminado(false);
		setRenderedPDFTerminado(false);
		setTotalBandeja(0);
		setSolicitudApoyoNacional(null);
	}

	/**
	 * Metodo encargado de consultar el SGD para traer los documentos del OWCC.
	 */
	public String consultaSGDAbrirPestana()
	{
		String ls_return;

		ls_return = null;

		try
		{
			BeanPredioDocumentoActo lbpda_bean;

			lbpda_bean = (BeanPredioDocumentoActo)obtenerInstanciaBean(
				    BeanPredioDocumentoActo.class, BeanNameCommon.BEAN_PREDIO_DOCUMENTO_ACTO
				);

			if(lbpda_bean != null)
			{
				lbpda_bean.clear();

				SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;

				lsanui_solicitudApoyoNacional = getSolicitudApoyoNacional();

				if(lsanui_solicitudApoyoNacional != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = lsanui_solicitudApoyoNacional.getSolicitud();

					if(ls_solicitud != null)
					{
						lbpda_bean.setNir(ls_solicitud.getNir());
						ls_return = lbpda_bean.consultaSGD(OperadorCommon.OR);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSGDAbrirPestana", lb2be_e);

			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Consultar solicitudes apoyo nacional.
	 */
	public void consultarSolicitudesApoyoNacional()
	{
		try
		{
			Collection<Turno> lct_turnosApoyoNacional;
			String            ls_idCirculo;

			lct_turnosApoyoNacional     = null;
			ls_idCirculo                = getIdCirculo();

			if(StringUtils.isValidString(ls_idCirculo))
			{
				int li_cantidadPorEtapas;
				li_cantidadPorEtapas        = 0;
				lct_turnosApoyoNacional     = ianr_apoyoNacionalRemote.consultarSolicitudesApoyoNacional(
					    ls_idCirculo, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setTurnosApoyoNacional(lct_turnosApoyoNacional);

				if(CollectionUtils.isValidCollection(lct_turnosApoyoNacional))
				{
					for(Turno ltc_actual : lct_turnosApoyoNacional)
					{
						if(ltc_actual != null)
							li_cantidadPorEtapas = li_cantidadPorEtapas + 1;
					}

					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);

				setTotalBandeja(li_cantidadPorEtapas);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			PrimeFaces.current().ajax().update(CS_ID_FORM);
		}
		catch(B2BException lb2be_e)
		{
			setTurnosApoyoNacional(null);
			setTotalBandeja(0);
			clh_LOGGER.error("consultarSolicitudesApoyoNacional", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(CS_ID_GROWL);
		}
	}

	/**
	 * Consultar solicitudes apoyo nacional.
	 */
	public void consultarTurnosReasignadosApoyoNacional()
	{
		try
		{
			Collection<SolicitudApoyoNacional> lct_turnosApoyoNacional;
			String                             ls_nir;

			lct_turnosApoyoNacional     = null;
			ls_nir                      = getNir();

			if(StringUtils.isValidString(ls_nir))
			{
				int li_cantidadPorEtapas;
				li_cantidadPorEtapas        = 0;
				lct_turnosApoyoNacional     = ianr_apoyoNacionalRemote.findByNir(
					    ls_nir, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setSolicitudesApoyoNacional(lct_turnosApoyoNacional);

				if(CollectionUtils.isValidCollection(lct_turnosApoyoNacional))
				{
					boolean lb_finalizada;

					lb_finalizada = true;

					for(SolicitudApoyoNacional ltc_actual : lct_turnosApoyoNacional)
					{
						if(ltc_actual != null)
						{
							li_cantidadPorEtapas = li_cantidadPorEtapas + 1;
							setNombreProceso(ltc_actual.getNombreProceso());
							setIdSolicitud(ltc_actual.getIdSolicitud());
							lb_finalizada = (ltc_actual.isFinalizada()) ? lb_finalizada : false;
						}
					}

					setFinalizada(lb_finalizada);
					addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);

				setTotalBandeja(li_cantidadPorEtapas);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

			PrimeFaces.current().ajax().update(CS_ID_FORM);
		}
		catch(B2BException lb2be_e)
		{
			setTurnosApoyoNacional(null);
			setTotalBandeja(0);
			clh_LOGGER.error("consultarSolicitudesApoyoNacional", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(CS_ID_GROWL);
		}
	}

	/**
	 * Deshabilitar descarga PDF.
	 */
	public void deshabilitarDescargaPDF()
	{
		setRenderedPDFTerminado(true);
		PrimeFaces.current().ajax().update(CS_ID_FORM);
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC.
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			boolean                  lb_enviados;
			SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;

			lb_enviados                       = false;
			lsanui_solicitudApoyoNacional     = getSolicitudApoyoNacional();

			if(lsanui_solicitudApoyoNacional != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = lsanui_solicitudApoyoNacional.getSolicitud();

				if(ls_solicitud != null)
					lb_enviados = validarEnvioDocumentosOWCCByIdSolicitud(ls_solicitud.getIdSolicitud());
			}

			setDocumentosEnviados(lb_enviados);

			if(lb_enviados)
			{
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");
				actualizarComponente(CS_ID_FORM);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para enviar a direccion tecnica.
	 *
	 * @return el valor de la pagina a navegar
	 */
	public String enviarADireccionTecnica()
	{
		String ls_returnPage;
		ls_returnPage = null;

		try
		{
			SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;
			lsanui_solicitudApoyoNacional     = getSolicitudApoyoNacional();

			lsanui_solicitudApoyoNacional = ianr_apoyoNacionalRemote.enviarADireccionTecnica(
				    lsanui_solicitudApoyoNacional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lsanui_solicitudApoyoNacional != null)
				ls_returnPage = NavegacionCommon.PRINCIPAL;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarADireccionTecnica", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(CS_ID_GROWL);
		}

		return ls_returnPage;
	}

	/**
	 * Generar solicitud de apoyo nacional.
	 */
	public void generarSolicitudApoyoNacional()
	{
		SolicitudApoyoNacionalUI lsanui_solicitudApoyoNacional;
		Collection               lct_turnosApoyoNacional;
		lct_turnosApoyoNacional = getTurnosApoyoNacional();

		try
		{
			if(CollectionUtils.isValidCollection(lct_turnosApoyoNacional) && (lct_turnosApoyoNacional.size() >= 11))
			{
				lsanui_solicitudApoyoNacional = new SolicitudApoyoNacionalUI();

				lsanui_solicitudApoyoNacional.setTurnos(lct_turnosApoyoNacional);
				lsanui_solicitudApoyoNacional.setIdCirculo(getIdCirculo());

				lsanui_solicitudApoyoNacional = ianr_apoyoNacionalRemote.generarSolicitudApoyoNacional(
					    lsanui_solicitudApoyoNacional, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lsanui_solicitudApoyoNacional != null)
				{
					Ajax       la_ajax;
					PrimeFaces lp_primeFaces;
					String     ls_nir;

					ls_nir = lsanui_solicitudApoyoNacional.getNirProceso();

					if(StringUtils.isValidString(ls_nir))
					{
						lp_primeFaces     = PrimeFaces.current();
						la_ajax           = lp_primeFaces.ajax();

						if(la_ajax != null)
						{
							setProcesoTerminado(true);
							setNirGenerado(ls_nir);

							setMensajeTerminarProceso("Ha finalizado su solicitud bajo el NIR: " + ls_nir);
							lp_primeFaces.executeScript("PF('idDialogNir').show();");
							la_ajax.update("fDialog2:otMensaje");
							la_ajax.update(CS_ID_FORM);
							PrimeFaces.current().executeScript("PF('wvPoll').start();");
						}
					}

					setSolicitudApoyoNacional(lsanui_solicitudApoyoNacional);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudApoyo", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(CS_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de terminar la solicitud de apoyo nacional
	 */
	public void terminarSolicitudApoyoNacional()
	{
		String ls_idSolicitud;

		ls_idSolicitud = getIdSolicitud();

		try
		{
			if(StringUtils.isValidString(ls_idSolicitud))
				ianr_apoyoNacionalRemote.terminarSolicitudApoyoNacional(
				    ls_idSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarSolicitudApoyoNacional", lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(CS_ID_GROWL);
		}
	}
}
