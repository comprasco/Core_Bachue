package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanRegional.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanRegional")
@SessionScoped
public class BeanRegional extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3433228716433988196L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ic parametros. */
	private Regional ic_parametros;

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
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(Regional ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public Regional getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Regional();

		return ic_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		setParametros(null);
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			Regional lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idZonaRegional;

				ls_idZonaRegional = FacesUtils.getStringFacesParameter("idRegional");

				if(StringUtils.isValidString(ls_idZonaRegional))
				{
					lc_parametros.setIdRegional(ls_idZonaRegional);
					setParametros(ipr_parameterRemote.findRegionalById(lc_parametros));
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
	public Collection<Regional> findAllRegionales()
	{
		Collection<Regional> lcr_regional;
		lcr_regional = null;

		try
		{
			lcr_regional = ipr_parameterRemote.findAllRegionales();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_regional;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		FacesContext lfc_context;
		boolean      lb_focus;

		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			Regional lc_parametros;

			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				{
					String ls_nombre;

					ls_nombre = lc_parametros.getNombre();
					validateStyles(":fRegionalesDetalle:idNombre", lfc_context, ls_nombre, true);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;

					ls_activo     = lc_parametros.getActivo();
					lb_focus      = validateStyles(":fRegionalesDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				lc_parametros.setIdUsuarioCreacion(getUserId());
				lc_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarRegional(
				    lc_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				getBeanReference().setRecepcionDocumento(null);

				setParametros(null);

				ls_result = NavegacionCommon.REGIONAL;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fRegionalesDetalle");
		}

		return ls_result;
	}
}
