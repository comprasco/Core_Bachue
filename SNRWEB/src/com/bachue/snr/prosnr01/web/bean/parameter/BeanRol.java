package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades de BeanRol.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanRol")
@SessionScoped
public class BeanRol extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4751073661457935255L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private Rol ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
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
	 * @param ar_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(Rol ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Rol getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new Rol();

		return ir_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setParametros(new Rol());

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Metodo para traer los componentes de la base de datos
	 *
	 * @return Colección de componentes resultante de la consulta
	 */
	public Collection<Componente> cargarComponentesOrigen()
	{
		Collection<Componente> lc_componente;
		lc_componente = new LinkedList<Componente>();

		try
		{
			lc_componente = ipr_parameterRemote.findAllComponenteActivo(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lc_componente;
	}

	/**
	 * Metodo para traer los componentes de la base de datos y el cambio de pantalla
	 *
	 * @return String Pantalla predeterminada
	 */
	public String cargarPermisosRol()
	{
		String ls_return;

		ls_return = NavegacionCommon.ROL_DETALLE;

		try
		{
			BeanAsignarPermisos lbap_bean;

			lbap_bean = (BeanAsignarPermisos)FacesUtils.obtenerInstanciaBean(
				    BeanAsignarPermisos.class, BeanNameCommon.BEAN_ASIGNAR_PERMISOS
				);

			if(lbap_bean != null)
			{
				lbap_bean.setRolActual(getParametros());
				lbap_bean.cargarPermisos();
				ls_return = NavegacionCommon.ASIGNAR_PERMISOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			Rol lr_parametros;
			lr_parametros = getParametros();

			if(lr_parametros != null)
			{
				String ls_idRol;
				ls_idRol = FacesUtils.getStringFacesParameter("idRol");

				if(StringUtils.isValidString(ls_idRol))
				{
					lr_parametros.setIdRol(ls_idRol);
					setParametros(ipr_parameterRemote.findRolById(lr_parametros));
					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Rol> findAllRols()
	{
		Collection<Rol> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllRols(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
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
			Rol lr_parametros;

			lr_parametros = getParametros();

			if(lr_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre     = lr_parametros.getNombre();

					lb_focus = validateStyles(":fRolDetalle:nombreRol", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					Date ld_fechaDesde;

					ld_fechaDesde     = lr_parametros.getFechaDesde();

					lb_focus = validateStyles(":fRolDetalle:idCalFechaDesde", lfc_context, ld_fechaDesde, lb_focus);

					if(ld_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;

					ls_activo     = lr_parametros.getActivo();

					lb_focus = validateStyles(":fRolDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_idComponente;

					ls_idComponente     = lr_parametros.getIdComponente();

					lb_focus = validateStyles(":fRolDetalle:idComponente", lfc_context, ls_idComponente, lb_focus);

					if(!StringUtils.isValidString(ls_idComponente))
						throw new B2BException(ErrorKeys.ERROR_DEBE_ELEGIR_COMPONENTE);
				}

				lr_parametros = ipr_parameterRemote.salvarRol(
					    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				setInsertar(false);
			}

			setParametros(lr_parametros);

			ls_result = NavegacionCommon.ROL_DETALLE;
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
