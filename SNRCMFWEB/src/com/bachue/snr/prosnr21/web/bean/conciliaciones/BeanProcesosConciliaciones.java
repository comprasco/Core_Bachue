package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanConfrontacionManual.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanProcesosConciliaciones")
@SessionScoped
public class BeanProcesosConciliaciones extends BaseBean implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -6456637147647176962L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanProcesosConciliaciones.class);

	/**Propiedad Entidad recaaudadoraCuenta*/
	private Collection<EntidadRecaudadoraCuenta> ic_entidadRecaudadoraCuentas;

	/** Propiedad ipr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote ipr_conciliacionesRemote;

	/** Propiedad fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad id cuenta*/
	private String is_idCuenta;

	/** Propiedad id entidad recaudadora*/
	private String is_idEntidadRecaudadora;

	/** Propiedad proceso. */
	private String is_proceso;

	/** Propiedad proceso Crps. */
	private String is_procesoCrps;

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.web.bean.BaseBean#getApplication()
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param Modifica el valor de la propiedad ic_entidadRecaudadoraCuentas por ac_entidadRecaudadoraCuentas
	 */
	public void setEntidadRecaudadoraCuentas(Collection<EntidadRecaudadoraCuenta> ac_entidadRecaudadoraCuentas)
	{
		ic_entidadRecaudadoraCuentas = ac_entidadRecaudadoraCuentas;
	}

	/**
	 * @return Retorna el valor de la propiedad ic_entidadRecaudadoraCuentas
	 */
	public Collection<EntidadRecaudadoraCuenta> getEntidadRecaudadoraCuentas()
	{
		return ic_entidadRecaudadoraCuentas;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param ad_d the new fecha desde
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param ad_d the new fecha hasta
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por as_idCuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idEntidadRecaudadora por as_idEntidadRecaudadora
	 */
	public void setIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	{
		is_idEntidadRecaudadora = as_idEntidadRecaudadora;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Sets the proceso.
	 *
	 * @param as_s the new proceso
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Gets the proceso.
	 *
	 * @return the proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Sets the proceso crps.
	 *
	 * @param as_s the new proceso crps
	 */
	public void setProcesoCrps(String as_s)
	{
		is_procesoCrps = as_s;
	}

	/**
	 * Gets the proceso crps.
	 *
	 * @return the proceso crps
	 */
	public String getProcesoCrps()
	{
		return is_procesoCrps;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asignadas a un usuario.
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
			lcpc_procesoConciliacion = ipr_conciliacionesRemote.findEntidadRecaudadoraConciliacionByUser(getUserId());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asiganadas a un usuario y una entidad.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public void buscarEntidadRecaudadoraCuentaDeUsuario()
	{
		Collection<EntidadRecaudadoraCuenta> lcpc_procesoConciliacion;

		lcpc_procesoConciliacion = null;

		try
		{
			Map<String, Object> lmso_data;

			lmso_data = ipr_conciliacionesRemote.findEntidadRecaudadoraCuentaData(
				    getIdEntidadRecaudadora(), getUserId()
				);

			if(CollectionUtils.isValidCollection(lmso_data))
				lcpc_procesoConciliacion = (Collection<EntidadRecaudadoraCuenta>)lmso_data.get("CUENTAS");

			setEntidadRecaudadoraCuentas(lcpc_procesoConciliacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setProceso(null);
		setIdCuenta(null);
		setFechaDesde(null);
		setFechaHasta(null);
		setProcesoCrps(null);
		setIdEntidadRecaudadora(null);
		setEntidadRecaudadoraCuentas(null);
	}

	/**
	 * Procesar.
	 */
	public void procesar()
	{
		try
		{
			String ls_proceso;

			ls_proceso = getProceso();

			if(StringUtils.isValidString(ls_proceso))
			{
				Date   ld_fechaDesde;
				Date   ld_fechaHasta;
				String ls_procesoConciliacion;
				String ls_idCuenta;

				ld_fechaDesde              = getFechaDesde();
				ld_fechaHasta              = getFechaHasta();
				ls_procesoConciliacion     = null;
				ls_idCuenta                = null;

				if(ld_fechaDesde == null)
					throw new B2BException("Debe seleccionar fecha desde.");

				if(ld_fechaHasta == null)
					throw new B2BException("Debe seleccionar fecha hasta.");

				if(ld_fechaDesde.after(ld_fechaHasta))
					throw new B2BException("La fecha desde no puede ser superior a la fecha hasta.");

				if(ls_proceso.equalsIgnoreCase("C"))
				{
					String ls_procesoCrps;

					ls_procesoCrps             = getProcesoCrps();
					ls_procesoConciliacion     = ls_procesoCrps;

					if(!StringUtils.isValidString(ls_procesoCrps))
						throw new B2BException("Debe elegir un proceso CRPS.");
				}
				else if(ls_proceso.equalsIgnoreCase("M"))
				{
					ls_procesoConciliacion     = ls_proceso;
					ls_idCuenta                = getIdCuenta();

					if(!StringUtils.isValidString(ls_idCuenta))
						throw new B2BException("Debe elegir una cuenta bancaria.");
				}

				if(ls_procesoConciliacion != null)
				{
					ipr_conciliacionesRemote.procesosConciliaciones(
					    ls_procesoConciliacion, ld_fechaDesde, ld_fechaHasta, ls_idCuenta, getUserId(),
					    getRemoteIpAddress()
					);

					if(ls_procesoConciliacion.equalsIgnoreCase("M"))
						addMessage(MessagesKeys.PROCESO_COMPLETADO_M);
					else if(ls_procesoConciliacion.equalsIgnoreCase("E"))
						addMessage(MessagesKeys.PROCESO_COMPLETADO_E);
					else if(ls_procesoConciliacion.equalsIgnoreCase("C"))
						addMessage(MessagesKeys.PROCESO_COMPLETADO_C);

					limpiar();
				}
			}
			else
				throw new B2BException("Debe elegir un proceso.");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("procesar", lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
