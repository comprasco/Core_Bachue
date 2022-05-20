package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanProceso.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanProceso")
@SessionScoped
public class BeanProceso extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 457469360572090816L;

	/** Propiedad icp datos auditoria. */
	private Collection<Proceso> icp_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ip parametros. */
	private Proceso ip_parametros;

	/** Propiedad ip proceso. */
	private Proceso ip_proceso;

	/** Propiedad irr reference remote. */
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
	 * @param acp_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Proceso> acp_datosAuditoria)
	{
		icp_datosAuditoria = acp_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Proceso> getDatosAuditoria()
	{
		return icp_datosAuditoria;
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
	 * @param ip_parametros asigna el valor a la propiedad
	 */
	public void setParametros(Proceso ip_parametros)
	{
		this.ip_parametros = ip_parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Proceso getParametros()
	{
		return ip_parametros;
	}

	/**
	 * @param ap_proceso asigna el valor a la propiedad
	 */
	public void setProceso(Proceso ap_proceso)
	{
		ip_proceso = ap_proceso;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Proceso getProceso()
	{
		return ip_proceso;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Proceso
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setProceso(new Proceso());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarProceso");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consuta detallada de proceso
	 * @param acad_cad Con el identificador del proceso
	 * @throws B2BException
	 */
	public void consultaDetallada(Proceso acad_cad)
	    throws B2BException
	{
		if(acad_cad != null)
		{
			Proceso lcad_datos;
			String  ll_s;

			ll_s           = acad_cad.getIdProceso();
			lcad_datos     = null;

			if(StringUtils.isValidString(ll_s))
				lcad_datos = ipr_parameterRemote.findProcesoById(acad_cad);

			if(lcad_datos != null)
			{
				Collection<Proceso> lccad_ccad;
				lccad_ccad = new ArrayList<Proceso>();

				lccad_ccad.add(lcad_datos);
				setProceso(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 *Método de consulta de Proceso para obtener todos los procesos
	 * @return una colección de procesos con la información solicitada
	 */
	public Collection<Proceso> findProcesos()
	{
		Collection<Proceso> lcp_procesos;

		try
		{
			lcp_procesos = irr_referenceRemote.findProcesos(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			lcp_procesos = null;
		}

		return lcp_procesos;
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
			Proceso lma_parametros;

			lma_parametros = getProceso();

			if(lma_parametros != null)
			{
				{
					String ls_idProceso;
					ls_idProceso     = lma_parametros.getIdProceso();

					lb_focus = validateStyles(":fProcesoDetalle:idProceso", lfc_context, ls_idProceso, lb_focus);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_nombre;
					ls_nombre     = lma_parametros.getNombre();

					lb_focus = validateStyles(":fProcesoDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = lma_parametros.getActivo();

					lb_focus = validateStyles(":fProcesoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarProceso(
				    lma_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setProcesosActivosById(null);
					lbr_beanReference.setProcesosActivos(null);
					lbr_beanReference.setProcesos(null);
				}

				setParametros(null);
				setProceso(null);

				ls_result = NavegacionCommon.PROCESO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
