package com.bachue.snr.prosnr21.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;

import com.bachue.snr.prosnr21.common.constants.MessagesKeys;
import com.bachue.snr.prosnr21.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.Rubro;
import com.bachue.snr.prosnr21.model.pgn.RubroHomologacion;

import com.bachue.snr.prosnr21.web.bean.BaseBean;
import com.bachue.snr.prosnr21.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.IOException;
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
 * Clase para el manejo de la capa web para Rubro
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanRubroHomologacion")
@SessionScoped
public class BeanRubroHomologacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 647210238496087170L;

	/** Propiedad iccmv datos auditoria. */
	private Collection<RubroHomologacion> icrh_datosAuditoria;

	/** Propiedad ic subprocesos*/
	private Collection<Subproceso> ics_subprocesos;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irh rubro homologacion. */
	private RubroHomologacion irh_rubroHomologacion;

	/** propiedad is id Proceso*/
	private String is_idProceso;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param arh_rubroHomologacion asigna el valor a la propiedad
	 */
	public void setRubroHomologacion(RubroHomologacion arh_rubroHomologacion)
	{
		irh_rubroHomologacion = arh_rubroHomologacion;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public RubroHomologacion getRubroHomologacion()
	{
		return irh_rubroHomologacion;
	}

	/**
	 * @param acrh_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<RubroHomologacion> acrh_datosAuditoria)
	{
		icrh_datosAuditoria = acrh_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<RubroHomologacion> getDatosAuditoria()
	{
		return icrh_datosAuditoria;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idProceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idProceso por is_idProceso
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad ics_subprocesos
	 */
	public Collection<Subproceso> getSubprocesos()
	{
		return ics_subprocesos;
	}

	/**
	 * @param Modifica el valor de la propiedad ics_subprocesos por ics_subprocesos
	 */
	public void setSubprocesos(Collection<Subproceso> acs_cs)
	{
		ics_subprocesos = acs_cs;
	}

	/**
	 * @param ab_insertar asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_insertar)
	{
		ib_insertar = ab_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setRubroHomologacion((new RubroHomologacion()));
		setIdProceso(null);

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarRubro");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *
	 * @param arh_td
	 * @throws B2BException
	 */
	public void consultaDetallada(RubroHomologacion arh_td)
	    throws B2BException
	{
		if(arh_td != null)
		{
			String            ls_idRubrosHomologacion;
			RubroHomologacion latd_td;
			latd_td                     = new RubroHomologacion();
			ls_idRubrosHomologacion     = arh_td.getIdRubrosHomologacion();
			latd_td.setIdRubrosHomologacion(ls_idRubrosHomologacion);

			latd_td = ipr_parameterRemote.findRubroHomologacionById(latd_td);

			if(latd_td != null)
			{
				Collection<RubroHomologacion> lctd_td;

				lctd_td = new ArrayList<RubroHomologacion>();

				lctd_td.add(latd_td);
				setRubroHomologacion(latd_td);
				setIdProceso(latd_td.getIdProceso());
				findAllSubproceso();
				setDatosAuditoria(lctd_td);
			}

			setInsertar(false);
		}
	}

	/**
	 *
	 * @return
	 */
	public Collection<RubroHomologacion> findAllRubroHomologacion()
	{
		Collection<RubroHomologacion> lcrh_rh;
		lcrh_rh = null;

		try
		{
			lcrh_rh = ipr_parameterRemote.findAllRubroHomologacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcrh_rh;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
	 * @throws IOException
	 */
	public String salvar()
	    throws IOException
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			RubroHomologacion lcsmv_cmv;

			lcsmv_cmv = getRubroHomologacion();

			if(lcsmv_cmv != null)
			{
				String ls_dato;
				lcsmv_cmv.setIdProceso(getIdProceso());

				{
					ls_dato     = lcsmv_cmv.getIdRubro();

					lb_focus = validateStyles(":fRubroHomologacionDetalle:idRubro", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato     = lcsmv_cmv.getActivo();

					lb_focus = validateStyles(":fRubroHomologacionDetalle:idActivo", lfc_context, ls_dato, lb_focus);
				}

				{
					ls_dato     = lcsmv_cmv.getTipoOperacion();

					lb_focus = validateStyles(
						    ":fRubroHomologacionDetalle:idTipoOperacion", lfc_context, ls_dato, lb_focus
						);
				}

				ipr_parameterRemote.salvarRubroHomologacion(
				    lcsmv_cmv, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				if(isInsertar())
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				setRubroHomologacion(null);

				ls_result = NavegacionCommon.RUBRO_HOMOLOGACION;
			}

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

				FacesContext.getCurrentInstance().getExternalContext().redirect("rubroHomologacion.jsf");
				PrimeFaces.current().ajax().update(":frubroHomologacion:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(":frubroHomologacion:globalMsg");
		}

		return ls_result;
	}

	/**
	 *método para encontrar todos los procesos.
	 * @return Colección con datos solicitados
	 */
	public Collection<Proceso> findAllProceso()
	{
		Collection<Proceso> lcuo_datos;

		try
		{
			lcuo_datos = ipr_parameterRemote.findAllProcesosConciliaciones();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		return lcuo_datos;
	}

	/**
	 *método para encontrar todos los subprocesos.
	 * @return Colección con datos solicitados
	 */
	public Collection<Subproceso> findAllSubproceso()
	{
		Collection<Subproceso> lcuo_datos;
		String                 ls_idProceso;
		ls_idProceso = getIdProceso();

		try
		{
			lcuo_datos = ipr_parameterRemote.findAllSubprocesosConciliaciones(ls_idProceso);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		setSubprocesos(lcuo_datos);

		return lcuo_datos;
	}

	/**
	 *método para encontrar todos los rubros.
	 * @return Colección con datos solicitados
	 */
	public Collection<Rubro> findAllRubros()
	{
		Collection<Rubro> lcuo_datos;

		try
		{
			lcuo_datos = ipr_parameterRemote.findAllRubroActivo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcuo_datos = null;
		}

		return lcuo_datos;
	}
}
