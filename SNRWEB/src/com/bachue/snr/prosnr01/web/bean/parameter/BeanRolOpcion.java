package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.RolOpcion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanRolOpcion.
 *
 * @author Duvan Beltran
 */
@ManagedBean(name = "beanRolOpcion")
@SessionScoped
public class BeanRolOpcion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8437475468725340123L;

	/** Propiedad icro datos auditoria. */
	private Collection<RolOpcion> icro_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iro rol opcion. */
	private RolOpcion iro_rolOpcion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acro_da asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<RolOpcion> acro_da)
	{
		icro_datosAuditoria = acro_da;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<RolOpcion> getDatosAuditoria()
	{
		return icro_datosAuditoria;
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
	 * @param aro_ro asigna el valor a la propiedad
	 */
	public void setRolOpcion(RolOpcion aro_ro)
	{
		iro_rolOpcion = aro_ro;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public RolOpcion getRolOpcion()
	{
		return iro_rolOpcion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo Campo
	 * Criterio Busqueda
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setRolOpcion((new RolOpcion()));

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarRolOpcion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *
	 * @param aro_ro
	 * @throws B2BException
	 */
	public void consultaDetallada(RolOpcion aro_ro)
	    throws B2BException
	{
		if(aro_ro != null)
		{
			String ls_idRol;
			String ls_idOpcion;

			ls_idRol        = aro_ro.getIdRol();
			ls_idOpcion     = aro_ro.getIdOpcion();

			if(StringUtils.isValidString(ls_idRol))
			{
				aro_ro = ipr_parameterRemote.findRolOpcionById(ls_idRol, ls_idOpcion);

				if(aro_ro != null)
				{
					Collection<RolOpcion> lcro_ro;

					lcro_ro = new ArrayList<RolOpcion>();

					lcro_ro.add(aro_ro);
					setRolOpcion(aro_ro);

					setDatosAuditoria(lcro_ro);
				}

				setInsertar(false);
			}
		}
	}

/**
 *Método de consulta para encontrar todos los Rol Opción
 * @return
 */
	public Collection<RolOpcion> findAllRolOpcion()
	{
		Collection<RolOpcion> lcro_ro;
		lcro_ro = null;

		try
		{
			lcro_ro = ipr_parameterRemote.findAllRolOpcion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcro_ro;
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
			RolOpcion lro_rolOpcion;

			lro_rolOpcion = getRolOpcion();

			if(lro_rolOpcion != null)
			{
				{
					String ls_idRol;

					ls_idRol     = lro_rolOpcion.getIdRol();

					lb_focus = validateStyles(":fRolOpcionDetalle:idRol", lfc_context, ls_idRol, lb_focus);

					if(!StringUtils.isValidString(ls_idRol))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
				}

				{
					String ls_idOpcion;

					ls_idOpcion     = lro_rolOpcion.getIdOpcion();

					lb_focus = validateStyles(":fRolOPcionDetalle:idOpcion", lfc_context, ls_idOpcion, lb_focus);

					if(!StringUtils.isValidString(ls_idOpcion))
						throw new B2BException(ErrorKeys.ERROR_SELECCIONE_OPCION);
				}

				{
					String ls_activo;

					ls_activo     = lro_rolOpcion.getActivo();

					lb_focus = validateStyles(":fRolOpcionDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lro_rolOpcion.getFechaDesde();

					lb_focus = validateStyles(":fRolOpcionDetalle:idFechaDesde", lfc_context, ld_fechaDesde, lb_focus);

					if(!(ld_fechaDesde != null))
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				ipr_parameterRemote.salvarRolOpcion(
				    lro_rolOpcion, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setRolOpcion(null);

				ls_result = NavegacionCommon.ROL_OPCION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoDocumentalDetalle:globalMsg");
		}

		return ls_result;
	}
}
