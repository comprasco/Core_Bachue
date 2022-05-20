package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.MapUtils;
import org.primefaces.PrimeFaces;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.logging.LoggerHandler;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;
import com.bachue.snr.prosnr21.web.bean.BaseBean;


// TODO: Auto-generated Javadoc
/**
 * Clase que contiene todos las propiedades BeanArchivoIngresosING.
 *
 * @author  Kevin Pulido
 */
@ManagedBean(name = "beanArchivoIngresosING")
@SessionScoped
public class BeanArchivoIngresosING extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2234159017023682430L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanArchivoIngresosING.class);

	/** Propiedad mostrar Panel. */
	boolean ib_mostrarPanel;

	/** Propiedad icsd siif detalle. */
	private Collection<Object> icsd_SiifDetalle;

	/** Propiedad icsm siif maestro. */
	private Collection<Object> icsm_SiifMaestro;

	/** Propiedad fecha concfrontacion. */
	private Date id_fechaConsulta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.web.bean.BaseBean#getApplication()
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de FechaConsulta.
	 *
	 * @param ad_fechaConsulta de ad fecha consulta
	 */
	public void setFechaConsulta(Date ad_fechaConsulta)
	{
		id_fechaConsulta = ad_fechaConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor fecha consulta.
	 *
	 * @return Retorna el valor de la propiedad id_fechaMovimiento
	 */
	public Date getFechaConsulta()
	{
		return id_fechaConsulta;
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
	 * Retorna Objeto o variable de valor mostrar panel.
	 *
	 * @return Retorna el valor de la propiedad ib_mostrarPanel
	 */
	public boolean getMostrarPanel()
	{
		return ib_mostrarPanel;
	}

	/**
	 * Modifica el valor de SiifDetalle.
	 *
	 * @param lcsd_siifDetalle de lcsd siif detalle
	 */
	public void setSiifDetalle(Collection<Object> lcsd_siifDetalle)
	{
		icsd_SiifDetalle = lcsd_siifDetalle;
	}

	/**
	 * Retorna Objeto o variable de valor siif detalle.
	 *
	 * @return el valor de siif detalle
	 */
	public Collection<Object> getSiifDetalle()
	{
		return icsd_SiifDetalle;
	}

	/**
	 * Modifica el valor de SiifMaestro.
	 *
	 * @param lcsm_siifMaestro de lcsm siif maestro
	 */
	public void setSiifMaestro(Collection<Object> lcsm_siifMaestro)
	{
		icsm_SiifMaestro = lcsm_siifMaestro;
	}

	/**
	 * Retorna Objeto o variable de valor siif maestro.
	 *
	 * @return el valor de siif maestro
	 */
	public Collection<Object> getSiifMaestro()
	{
		return icsm_SiifMaestro;
	}

	/**
	 * Consultar.
	 */
	public void consultar()
	{
		try
		{
			FacesContext                    lfc_context;
			boolean                         lb_focus;
			Map<String, Collection<Object>> lmap_return;
			Collection<Object>              lc_maestro;
			Collection<Object>              lc_detalle;

			lmap_return     = new HashMap<String, Collection<Object>>();
			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;
			lc_maestro      = new ArrayList<Object>();
			lc_detalle      = new ArrayList<Object>();

			Date ls_paramBusqueda;

			ls_paramBusqueda = getFechaConsulta();

			if(ls_paramBusqueda == null)
				lb_focus = validateStyles(":fIngresosING:idFechaConsulta", lfc_context, ls_paramBusqueda, lb_focus);
			else if(ls_paramBusqueda.after(new Date()))
			{
				lb_focus = validateStyles(":fIngresosING:idFechaConsulta", lfc_context, ls_paramBusqueda, lb_focus);
				addMessageInfo("W", MessagesKeys.ERROR_FECHA_MAYOR_A_ACTUAL);
				lb_focus = false;
			}
			else
				lb_focus = validateStyles(":fIngresosING:idFechaConsulta", lfc_context, ls_paramBusqueda, lb_focus);

			PrimeFaces.current().ajax().update("fIngresosING");

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);

			lmap_return = ipr_parameterRemote.findArchivoIngresos(ls_paramBusqueda);

			if(MapUtils.isNotEmpty(lmap_return))
			{
				for(Entry<String, Collection<Object>> le_entry : lmap_return.entrySet())
				{
					String ls_clave = le_entry.getKey();

					if(ls_clave.equalsIgnoreCase(IdentificadoresCommon.MAESTRO))
						lc_maestro.addAll(le_entry.getValue());
					else
						lc_detalle.addAll(le_entry.getValue());
				}

				setSiifMaestro(lc_maestro);
				setSiifDetalle(lc_detalle);

				setMostrarPanel(true);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fIngresosING:idGrowl");
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setFechaConsulta(null);
		setMostrarPanel(false);
		setSiifDetalle(null);
		setSiifMaestro(null);
	}
	
	
}
