package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.HoraEjecucionProceso;
import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de Proceso de Conciliación.
 *
 * @author arocha
 */
@ManagedBean(name = "beanProcesoConciliacion")
@SessionScoped
public class BeanProcesoConciliacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -579114283674019362L;

	/** Constante CS_ID_FORM. */
	private static final String cs_ID_FORM = "fProcesoConciliacionDetalle";

	/** Constante CS_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":globalMsg";

	/** Propiedad ichep hora ejecucion automatica. */
	private Collection<HoraEjecucionProceso> ichep_horaEjecucionAutomatica;

	/** Propiedad icpc colección de procesos de conciliación. */
	private Collection<ProcesoConciliacion> icpc_auditoria;

	/** Propiedad il hora. */
	private Long il_hora;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipc proceso conciliación. */
	private ProcesoConciliacion ipc_procesoConciliacion;

	/** Propiedad ir parametros. */
	private ProcesoConciliacion ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de Auditoria.
	 *
	 * @param acpc_auditoria de acpc auditoria
	 */
	public void setAuditoria(Collection<ProcesoConciliacion> acpc_auditoria)
	{
		icpc_auditoria = acpc_auditoria;
	}

	/**
	 * Retorna Objeto o variable de valor auditoria.
	 *
	 * @return Retorna el valor de la propiedad auditoria
	 */
	public Collection<ProcesoConciliacion> getAuditoria()
	{
		return icpc_auditoria;
	}

	/**
	 * Modifica el valor de Hora.
	 *
	 * @param al_ahora de al ahora
	 */
	public void setHora(Long al_ahora)
	{
		il_hora = al_ahora;
	}

	/**
	 * Retorna Objeto o variable de valor hora.
	 *
	 * @return Retorna el valor de la propiedad hora
	 */
	public Long getHora()
	{
		return il_hora;
	}

	/**
	 * Modifica el valor de HoraEjecucionAutomatica.
	 *
	 * @param ahep_horaEjecucionAutomatica de ahep hora ejecucion automatica
	 */
	public void setHoraEjecucionAutomatica(Collection<HoraEjecucionProceso> ahep_horaEjecucionAutomatica)
	{
		ichep_horaEjecucionAutomatica = ahep_horaEjecucionAutomatica;
	}

	/**
	 * Retorna Objeto o variable de valor hora ejecucion automatica.
	 *
	 * @return Retorna el valor de la propiedad horaEjecucionAutomatica
	 */
	public Collection<HoraEjecucionProceso> getHoraEjecucionAutomatica()
	{
		return ichep_horaEjecucionAutomatica;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param apc_parametro asigna el valor a la propiedad parametros
	 */
	public void setParametros(ProcesoConciliacion apc_parametro)
	{
		ir_parametros = apc_parametro;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public ProcesoConciliacion getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new ProcesoConciliacion();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo documento publico.
	 *
	 * @param apc_parametro asigna el valor a la propiedad tipo documento publico
	 */
	public void setProcesoConciliacion(ProcesoConciliacion apc_parametro)
	{
		ipc_procesoConciliacion = apc_parametro;
	}

	/**
	 * Retorna el valor de tipo documento publico.
	 *
	 * @return el valor de tipo documento publico
	 */
	public ProcesoConciliacion getProcesoConciliacion()
	{
		if(ipc_procesoConciliacion == null)
			ipc_procesoConciliacion = new ProcesoConciliacion();

		return ipc_procesoConciliacion;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param apc_procesoConciliacion correspondiente al valor del tipo de objeto TipoDocumentoPublico
	 * @return devuelve el valor de String
	 */
	public String botonModificar(ProcesoConciliacion apc_procesoConciliacion)
	{
		String ls_return;
		ls_return = null;

		if(apc_procesoConciliacion != null)
		{
			Collection<ProcesoConciliacion> lcpc_auditoria;

			lcpc_auditoria = new ArrayList<ProcesoConciliacion>();

			lcpc_auditoria.add(apc_procesoConciliacion);

			setHora(NumericUtils.getLongWrapper(apc_procesoConciliacion.getHoraProgramacion()));
			calcularHoras();

			setAuditoria(lcpc_auditoria);
			setProcesoConciliacion(apc_procesoConciliacion);

			setInsertar(false);

			ls_return = NavegacionCommon.PROCESO_CONCILIACION_DETALLE;
		}

		return ls_return;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_parameterRemote.buscarEntidadRecaudadoraConciliacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Méotodo que retorna una colección con todos los procesos de conciliación.
	 *
	 *@return Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 */
	public Collection<ProcesoConciliacion> buscarProcesoConciliacion()
	{
		Collection<ProcesoConciliacion> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_parameterRemote.buscarProcesosConciliacion(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Método que calcula horas proceso conciliacion.
	 *
	 *
	 */
	public void calcularHoras()
	{
		try
		{
			setHoraEjecucionAutomatica(
			    ipr_parameterRemote.calcularHorasProcesoConciliacion(
			        Long.valueOf(0L), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Método para  cambiar estado para saber si se desea insertar un nuevo Proceso Conciliación.
	 *
	 * @return Retorna un objeto de tipo <code>String </code> que contiene la regla de navegación correspondiente.
	 */
	public String cambiarEstado()
	{
		setInsertar(true);
		setProcesoConciliacion(new ProcesoConciliacion());

		try
		{
			setHoraEjecucionAutomatica(
			    ipr_parameterRemote.calcularHorasProcesoConciliacion(
			        Long.valueOf(0L), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return NavegacionCommon.PROCESO_CONCILIACION_DETALLE;
	}

	/**
	 * Método encargado de limpiar los atributos de la clase.
	 */
	public void clear()
	{
		setHoraEjecucionAutomatica(null);
		setHora(null);
		setAuditoria(null);
		setAuditoria(null);
		setProcesoConciliacion(null);
		setParametros(null);
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
	 *
	 * @param apc_procesoConciliacion objeto del cual se edxtrae el id para la consulta en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(ProcesoConciliacion apc_procesoConciliacion)
	    throws B2BException
	{
		setProcesoConciliacion(apc_procesoConciliacion);
		setInsertar(false);
	}

	/**
	 * Método encargado de salvar proceso conciliacion.
	 *
	 * @return el valor de la regla de navegación a la cual se redirige elmétodo después de salvar.
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			ProcesoConciliacion lpc_parametros;
			String              ls_activo;

			lpc_parametros     = getProcesoConciliacion();
			ls_activo          = lpc_parametros.getActivo();

			{
				String ls_entidadRecaudadora;

				ls_entidadRecaudadora     = lpc_parametros.getIdEntidadRecaudadora();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idEntidadRecaudadoraProcesoConciliacion", lfc_context,
					    ls_entidadRecaudadora, lb_focus
					);

				if(!StringUtils.isValidString(ls_entidadRecaudadora))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ENTIDAD_RECAUDADORA);
			}

			{
				String ls_horaProceso;

				ls_horaProceso     = lpc_parametros.getHoraProceso();

				lb_focus = validateStyles(
					    ":" + cs_ID_FORM + ":idHorarioEjecucion", lfc_context, ls_horaProceso, lb_focus
					);

				if(!StringUtils.isValidString(ls_horaProceso))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_HORA_EJECUCION);

				{
					String[] lsa_horaProceso;

					lsa_horaProceso = ls_horaProceso.split(":");

					if((lsa_horaProceso != null) && (lsa_horaProceso.length == 2))
					{
						lpc_parametros.setHoraProgramacion(NumericUtils.getLong(lsa_horaProceso[0]));
						lpc_parametros.setMinutoProgramacion(NumericUtils.getLong(lsa_horaProceso[1]));
					}
				}
			}

			{
				lb_focus = validateStyles(":" + cs_ID_FORM + ":idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			lpc_parametros = ipr_parameterRemote.salvarProcesoConciliacion(
				    lpc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lpc_parametros != null)
			{
				com.bachue.snr.prosnr21.web.listeners.scheduler.ConciliacionesScheduler lcs_agendador;

				lcs_agendador = com.bachue.snr.prosnr21.web.util.ContextHelper.getSchedulerContainer(
					    com.bachue.snr.prosnr21.web.util.FacesUtils.getServletContext()
					);

				if(lcs_agendador != null)
				{
					lcs_agendador.borrarJob(lpc_parametros);

					if(ls_activo.equals(com.bachue.snr.prosnr01.common.constants.EstadoCommon.S))
						lcs_agendador.iniciarJob(lpc_parametros, getLocalIpAddress());
				}
			}

			setParametros(null);

			setProcesoConciliacion(null);

			ls_result = NavegacionCommon.PROCESO_CONCILIACION;

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
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
			ls_result = null;
		}

		return ls_result;
	}
}
