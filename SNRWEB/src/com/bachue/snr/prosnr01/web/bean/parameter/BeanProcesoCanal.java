package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.png.ProcesoCanal;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanProcesoCanal.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanProcesoCanal")
@SessionScoped
public class BeanProcesoCanal extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8373196072191191212L;

	/** Propiedad icpc datos auditoria. */
	private Collection<ProcesoCanal> icpc_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote irr_parameterRemote;

	/** Propiedad ipc proceso canal. */
	private ProcesoCanal ipc_procesoCanal;

	/** Propiedad reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param actd_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<ProcesoCanal> actd_datosAuditoria)
	{
		icpc_datosAuditoria = actd_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ProcesoCanal> getDatosAuditoria()
	{
		return icpc_datosAuditoria;
	}

	/**
	 * @param ab_a asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_a)
	{
		ib_insertar = ab_a;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param atd_td asigna el valor a la propiedad
	 */
	public void setProcesoCanal(ProcesoCanal atd_td)
	{
		ipc_procesoCanal = atd_td;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public ProcesoCanal getProcesoCanal()
	{
		return ipc_procesoCanal;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * ProcesoCanal
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setProcesoCanal((new ProcesoCanal()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarProcesoCanal");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *Metodo para realizar una consulta detallada por ID de proceso canal
	 * @param atd_td
	 * @throws B2BException
	 */
	public void consultaDetallada(ProcesoCanal atd_td)
	    throws B2BException
	{
		if(atd_td != null)
		{
			String ls_idProceso;
			String ls_idSubProceso;
			String ls_idTipoCanalOrigen;

			ls_idProceso             = atd_td.getIdProceso();
			ls_idSubProceso          = atd_td.getIdSubProceso();
			ls_idTipoCanalOrigen     = atd_td.getIdTipoCanalOrigen();

			if(
			    StringUtils.isValidString(ls_idProceso)
				    && (StringUtils.isValidString(ls_idSubProceso) && (StringUtils.isValidString(ls_idTipoCanalOrigen)))
			)
			{
				atd_td = irr_parameterRemote.findAllProcesoCanalById(
					    ls_idProceso, ls_idSubProceso, ls_idTipoCanalOrigen
					);

				if(atd_td != null)
				{
					Collection<ProcesoCanal> lctd_td;

					lctd_td = new ArrayList<ProcesoCanal>();

					lctd_td.add(atd_td);
					setProcesoCanal(atd_td);

					setDatosAuditoria(lctd_td);
				}

				setInsertar(false);
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public Collection<ProcesoCanal> findAllProcesoCanal()
	{
		Collection<ProcesoCanal> lcpc_pc;
		lcpc_pc = null;

		try
		{
			lcpc_pc = irr_parameterRemote.findAllProcesoCanal();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_pc;
	}

	/**
	 * Método para salvar la inserción o actualización
	 *
	 * @return
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
			ProcesoCanal lpc_procesoCanal;

			lpc_procesoCanal = getProcesoCanal();

			if(lpc_procesoCanal != null)
			{
				{
					String ls_idProceso;

					ls_idProceso     = lpc_procesoCanal.getIdProceso();

					lb_focus = validateStyles(":fProcesoCanalDetalle:idProceso", lfc_context, ls_idProceso, lb_focus);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_idSubProceso;

					ls_idSubProceso     = lpc_procesoCanal.getIdSubProceso();

					lb_focus = validateStyles(
						    ":fProcesoCanalDetalle:idSubproceso", lfc_context, ls_idSubProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}

				{
					String ls_idTipoCanalorigen;

					ls_idTipoCanalorigen     = lpc_procesoCanal.getIdTipoCanalOrigen();

					lb_focus = validateStyles(
						    ":fProcesoCanalDetalle:idTipoCanalOrigen", lfc_context, ls_idTipoCanalorigen, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoCanalorigen))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_TIPO_CANAL_ORIGEN);
				}

				{
					String ls_activo;

					ls_activo     = lpc_procesoCanal.getActivo();

					lb_focus = validateStyles(":fProcesoCanalDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				irr_parameterRemote.salvarProcesoCanal(
				    lpc_procesoCanal, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setProcesoCanal(null);

				ls_result = NavegacionCommon.PROCESO_CANAL;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fProcesoCanalDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método de consulta para obtener todos los registros de procesos
	 * @return
	 */
	public Collection<Proceso> findAllProcesos()
	{
		Collection<Proceso> lcp_datos;
		lcp_datos = null;

		try
		{
			lcp_datos = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_datos;
	}

	/**
	 * Método para encontrar a todos los subprocesos de un proceso
	 * @param as_idProceso indicativo del proceso
	 * @return
	 */
	public Collection<Subproceso> findAllSubprocesos(String as_idProceso)
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			if(StringUtils.isValidString(as_idProceso))
			{
				Subproceso ls_subproceso;

				ls_subproceso = new Subproceso();

				ls_subproceso.setIdProceso(as_idProceso);

				lcs_datos = irr_referenceRemote.findSubprocesosByProceso(
					    ls_subproceso, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}
}
