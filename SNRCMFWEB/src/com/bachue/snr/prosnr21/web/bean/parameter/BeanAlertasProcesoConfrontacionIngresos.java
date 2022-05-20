package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.ConInconsistencia;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanAlertasProcesoConfrontacionIngresos.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 23/06/2020
 */
@ManagedBean(name = "beanAlertasProcesoConfrontacionIngresos")
@SessionScoped
public class BeanAlertasProcesoConfrontacionIngresos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5111142650607465204L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAlertasProcesoConfrontacionIngresos.class);

	/** Propiedad ils lista alertas. */
	private Collection<ConInconsistencia> ici_consulta;

	/** Propiedad ils lista alertas. */
	private Collection<String> ils_listaAlertas;

	/** Propiedad id fecha opcional. */
	private Date id_fechaOpcional;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ls param busqueda. */
	private String is_paramBusqueda;

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
	 * Modifica el valor de FechaOpcional.
	 *
	 * @param ad_D de ad D
	 */
	public void setFechaOpcional(Date ad_D)
	{
		id_fechaOpcional = ad_D;
	}

	/**
	 * Retorna Objeto o variable de valor fecha opcional.
	 *
	 * @return Retorna el valor de la propiedad fechaOpcional
	 */
	public Date getFechaOpcional()
	{
		return id_fechaOpcional;
	}

	/**
	 * Modifica el valor de Ils_listaAlertas.
	 *
	 * @param ils_listaAlertas de ils lista alertas
	 */
	public void setListaAlertas(Collection<String> ils_listaAlertas)
	{
		this.ils_listaAlertas = ils_listaAlertas;
	}

	/**
	 * Retorna Objeto o variable de valor ils lista alertas.
	 *
	 * @return el valor de ils lista alertas
	 */
	public Collection<String> getListaAlertas()
	{
		if(ils_listaAlertas == null)
		{
			String ls_caracter;
			ls_caracter = null;

			try
			{
				ls_caracter = ipr_parameterRemote.obtenerCaracterConstante(ConstanteCommon.BUSQUEDA_PARA_ALERTAS);

				if(StringUtils.isValidString(ls_caracter))
				{
					String[] lsa_temp;
					String[] lsa_lista;

					ls_caracter     = ls_caracter.replaceAll("\\.", "");
					lsa_temp        = ls_caracter.split(IdentificadoresCommon.SIMBOLO_COMA_TXT);
					lsa_lista       = new String[lsa_temp.length];

					for(int i_iterador = 0; i_iterador < lsa_temp.length; i_iterador++)
						lsa_lista[i_iterador] = lsa_temp[i_iterador].trim();

					ils_listaAlertas = Arrays.asList(lsa_lista);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("listaAlertas", lb2be_e);
				addMessage(lb2be_e);
				actualizarComponente(":falertasProcesoConfrontacionIngresos:idGrowl");
			}
		}

		return ils_listaAlertas;
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
	 * Modifica el valor de ParamBusqueda.
	 *
	 * @param as_s de as s
	 */
	public void setParamBusqueda(String as_s)
	{
		is_paramBusqueda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor param busqueda.
	 *
	 * @return Retorna el valor de la propiedad ls_paramBusqueda
	 */
	public String getParamBusqueda()
	{
		return is_paramBusqueda;
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

			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;

			{
				String ls_paramBusqueda;

				ls_paramBusqueda = getParamBusqueda();

				if(StringUtils.isValidString(ls_paramBusqueda) && ls_paramBusqueda.equalsIgnoreCase("BACHUÉ"))
					ls_paramBusqueda = "BACHUE";

				lb_focus = validateStyles(
					    ":falertasProcesoConfrontacionIngresos:idParamBusqueda", lfc_context, ls_paramBusqueda, lb_focus
					);

				if(!lb_focus || !StringUtils.isValidString(ls_paramBusqueda))

					throw new B2BException(getMessages().getString((ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA)));

				Date ld_fechaConsulta;

				ld_fechaConsulta = getFechaOpcional();

				if((ld_fechaConsulta != null) && ld_fechaConsulta.after(new Date()))
					throw new B2BException(getMessages().getString((ErrorKeys.ERROR_FECHA_MAYOR_A_ACTUAL)));

				ici_consulta = ipr_parameterRemote.buscarAlertasConfrontacionIngresos(
					    ls_paramBusqueda, ld_fechaConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setMostrarPanel(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAlertas", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":falertasProcesoConfrontacionIngresos:idGrowl");
			setConsulta(null);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setConsulta(null);
		setFechaOpcional(null);
		setListaAlertas(null);
		setParamBusqueda(null);
		setMostrarPanel(false);
	}
}
