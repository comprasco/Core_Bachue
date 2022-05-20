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

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanSubProceso.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanSubProceso")
@SessionScoped
public class BeanSubProceso extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5595348990785147249L;

	/** Propiedad icsp datos auditoria. */
	private Collection<Subproceso> icsp_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad inj parametros. */
	private Subproceso icsp_parametros;

	/** Propiedad isp subproceso. */
	private Subproceso isp_subProceso;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Subproceso> datosAuditoria)
	{
		icsp_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Subproceso> getDatosAuditoria()
	{
		return icsp_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(Subproceso parametros)
	{
		icsp_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Subproceso getParametros()
	{
		if(icsp_parametros == null)
			icsp_parametros = new Subproceso();

		return icsp_parametros;
	}

	/**
	 * @param asp_sp asigna el valor a la propiedad
	 */
	public void setSubProceso(Subproceso asp_sp)
	{
		isp_subProceso = asp_sp;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Subproceso getSubProceso()
	{
		return isp_subProceso;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * SubProceso
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setSubProceso(new Subproceso());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarSubProceso");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de procesos resultante de la consulta
	 */
	public Collection<Proceso> cargarProcesosOrigen()
	{
		Collection<Proceso> lp_proceso;
		lp_proceso = new LinkedList<Proceso>();

		try
		{
			lp_proceso = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lp_proceso;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_ACC_SUBPROCESO
	 * @param asp_sp
	 * @throws B2BException
	 */
	public void consultaDetallada(Subproceso asp_sp)
	    throws B2BException
	{
		if(asp_sp != null)
		{
			Subproceso lcsp_datos;
			lcsp_datos = ipr_parameterRemote.findSubProcesoById(asp_sp);

			if(lcsp_datos != null)
			{
				Collection<Subproceso> lccsp_ccsp;
				lccsp_ccsp = new ArrayList<Subproceso>();

				lccsp_ccsp.add(lcsp_datos);
				setSubProceso(lcsp_datos);

				setDatosAuditoria(lccsp_ccsp);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_ACC_SUBPROCESO
	 *
	 * @return Collection<Subproceso> Colección con objetos tipo Subproceso
	 */
	public Collection<Subproceso> findSubprocesos()
	{
		Collection<Subproceso> lcsp_subProcesos;
		lcsp_subProcesos = null;

		try
		{
			lcsp_subProcesos = ipr_parameterRemote.findSubprocesos(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcsp_subProcesos;
	}

	/**
	 * Método para salvar la inserción o actualización
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
			Subproceso ls_parametros;

			ls_parametros = getSubProceso();

			if(ls_parametros != null)
			{
				{
					String ls_idProceso;
					ls_idProceso     = ls_parametros.getIdProceso();

					lb_focus = validateStyles(":fSubProcesoDetalle:idProceso", lfc_context, ls_idProceso, lb_focus);

					if(!StringUtils.isValidString(ls_idProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PROCESO);
				}

				{
					String ls_idSubProceso;
					ls_idSubProceso     = ls_parametros.getIdSubproceso();

					lb_focus = validateStyles(
						    ":fSubProcesoDetalle:idSubproceso", lfc_context, ls_idSubProceso, lb_focus
						);

					if(!StringUtils.isValidString(ls_idSubProceso))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SUBPROCESO);
				}

				{
					String ls_nombre;
					ls_nombre     = ls_parametros.getNombre();

					lb_focus = validateStyles(":fSubProcesoDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = ls_parametros.getActivo();

					lb_focus = validateStyles(":fSubProcesoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarSubproceso(
				    ls_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setSubprocesosByProceso(null);
					lbr_beanReference.setSubprocesos(null);
					lbr_beanReference.setSubprocesosByProcesoId(null);
				}

				setParametros(null);
				setSubProceso(null);

				ls_result = NavegacionCommon.SUBPROCESO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
