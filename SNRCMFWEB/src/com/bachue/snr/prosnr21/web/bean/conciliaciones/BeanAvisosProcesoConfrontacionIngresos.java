package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanAvisosProcesoConfrontacionIngresos.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanAvisosProcesoConfrontacionIngresos")
@SessionScoped
public class BeanAvisosProcesoConfrontacionIngresos extends BaseBean implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -4994355945642292350L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAvisosProcesoConfrontacionIngresos.class);

	/** Propiedad ice entidad recaudadora cuentas. */
	private Collection<EntidadRecaudadoraCuenta> ice_entidadRecaudadoraCuentas;

	/** Propiedad icerc lista entidadrecaudadora. */
	private Collection<EntidadRecaudadoraConciliacion> icerc_listaEntidadRecaudadora;

	/** Propiedad ils lista alertas. */
	private Collection<ConInconsistencia> ici_consulta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is numero cuenta. */
	private String is_numeroCuenta;

	/** Propiedad ib mostrar panel. */
	private boolean ib_mostrarPanel;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de Consulta.
	 *
	 * @param consulta de consulta
	 */
	public void setConsulta(Collection<ConInconsistencia> consulta)
	{
		this.ici_consulta = consulta;
	}

	/**
	 * Retorna Objeto o variable de valor consulta.
	 *
	 * @return el valor de consulta
	 */
	public Collection<ConInconsistencia> getConsulta()
	{
		return ici_consulta;
	}

	/**
	 * Modifica el valor de EntidadRecaudadoraCuentas.
	 *
	 * @param entidadRecaudadoraCuentas de entidad recaudadora cuentas
	 */
	public void setEntidadRecaudadoraCuentas(Collection<EntidadRecaudadoraCuenta> entidadRecaudadoraCuentas)
	{
		this.ice_entidadRecaudadoraCuentas = entidadRecaudadoraCuentas;
	}

	/**
	 * Retorna Objeto o variable de valor entidad recaudadora cuentas.
	 *
	 * @return el valor de entidad recaudadora cuentas
	 */
	public Collection<EntidadRecaudadoraCuenta> getEntidadRecaudadoraCuentas()
	{
		return ice_entidadRecaudadoraCuentas;
	}

	/**
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param idEntidadRecaudadora de id entidad recaudadora
	 */
	public void setIdEntidadRecaudadora(String idEntidadRecaudadora)
	{
		this.is_idEntidadRecaudadora = idEntidadRecaudadora;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudadora.
	 *
	 * @return el valor de id entidad recaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de ListaEntidadrecaudadora.
	 *
	 * @param listaEntidadrecaudadora de lista entidadrecaudadora
	 */
	public void setListaEntidadRecaudadora(Collection<EntidadRecaudadoraConciliacion> listaEntidadrecaudadora)
	{
		this.icerc_listaEntidadRecaudadora = listaEntidadrecaudadora;
	}

	/**
	 * Retorna Objeto o variable de valor lista entidadrecaudadora.
	 *
	 * @return el valor de lista entidadrecaudadora
	 */
	public Collection<EntidadRecaudadoraConciliacion> getListaEntidadRecaudadora()
	{
		return icerc_listaEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de MostrarPanel.
	 *
	 * @param ab_mostrarPanel de ab mostrar panel
	 */
	public void setMostrarPanel(boolean ab_mostrarPanel)
	{
		ib_mostrarPanel = ab_mostrarPanel;
	}

	/**
	 * Valida la propiedad mostrar panel.
	 *
	 * @return Retorna el valor de la propiedad ib_mostrarPanel
	 */
	public boolean isMostrarPanel()
	{
		return ib_mostrarPanel;
	}

	/**
	 * Modifica el valor de NumeroCuenta.
	 *
	 * @param numeroCuenta de numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta)
	{
		this.is_numeroCuenta = numeroCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor numero cuenta.
	 *
	 * @return el valor de numero cuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * Buscar entidad recaudadora cuenta.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraCuenta> buscarEntidadRecaudadoraCuenta()
	{
		Collection<EntidadRecaudadoraCuenta> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_parameterRemote.findAllEntidadRecaudadoraCuentaByEntidadRecaudadora(
				    is_idEntidadRecaudadora
				);

			setEntidadRecaudadoraCuentas(lcpc_procesoConciliacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Consultar alertas.
	 */
	public void consultarAlertas()
	{
		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;
			String       ls_idCuenta;
			String       ls_idEntidad;

			lfc_context      = FacesContext.getCurrentInstance();
			lb_focus         = true;
			ls_idCuenta      = getNumeroCuenta();
			ls_idEntidad     = getIdEntidadRecaudadora();
			lb_focus         = validateStyles(
				    ":favisosProcesoConfrontacionIngresos:idEntidadRecaudadoraCuenta", lfc_context, ls_idEntidad,
				    lb_focus
				);
			lb_focus         = validateStyles(
				    ":favisosProcesoConfrontacionIngresos:idNumeroCuenta", lfc_context, ls_idCuenta, lb_focus
				);

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_CAMPOS_OBLIGATORIOS_2);

			ici_consulta = ipr_parameterRemote.buscarAvisosConciliacionIngresos(
				    ls_idCuenta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setMostrarPanel(true);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAlertas", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":favisosProcesoConfrontacionIngresos:idGrowl");
			setConsulta(null);
		}
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions.
	 *
	 * @return una colleccion de tipo EntidadRecaudadoraConciliacion
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_erc;
		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setConsulta(null);
		setEntidadRecaudadoraCuentas(null);
		setIdEntidadRecaudadora(null);
		setListaEntidadRecaudadora(null);
		setNumeroCuenta(null);
		setMostrarPanel(false);
	}
}
