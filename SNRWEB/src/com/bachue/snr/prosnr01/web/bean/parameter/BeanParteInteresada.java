package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;

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
 * Clase que contiene todos las propiedades y acciones de BeanParteInteresada.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanParteInteresada")
@SessionScoped
public class BeanParteInteresada extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3192895811407967799L;

	/** Propiedad icpi datos auditoria. */
	private Collection<ParteInteresada> icpi_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ic parametros. */
	private ParteInteresada ic_parametros;

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
	public void setDatosAuditoria(Collection<ParteInteresada> datosAuditoria)
	{
		icpi_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ParteInteresada> getDatosAuditoria()
	{
		return icpi_datosAuditoria;
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
	public void setParametros(ParteInteresada ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public ParteInteresada getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new ParteInteresada();

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
			ParteInteresada lc_parametros;
			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				String ls_idParteInteresada;

				ls_idParteInteresada = FacesUtils.getStringFacesParameter("idParteInteresada");

				if(StringUtils.isValidString(ls_idParteInteresada))
				{
					Collection<ParteInteresada> lcpi_cpi;

					lcpi_cpi = new ArrayList<ParteInteresada>();

					lc_parametros.setIdParteInteresada(ls_idParteInteresada);

					setParametros(ipr_parameterRemote.findParteInteresadaById(lc_parametros));
					lcpi_cpi.add(getParametros());

					setDatosAuditoria(lcpi_cpi);
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
	public Collection<ParteInteresada> findAllParteInteresadas()
	{
		Collection<ParteInteresada> lcr_regional;
		lcr_regional = null;

		try
		{
			lcr_regional = ipr_parameterRemote.findAllParteInteresadas();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_regional;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		FacesContext lfc_context;
		boolean      lb_focus;

		ls_result       = null;
		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			ParteInteresada lc_parametros;

			lc_parametros = getParametros();

			if(lc_parametros != null)
			{
				{
					String ls_ilicode;

					ls_ilicode     = lc_parametros.getIlicode();

					lb_focus = validateStyles(
						    ":fParteInteresadaDetalle:idIlicodeParte", lfc_context, ls_ilicode, lb_focus
						);

					if(!StringUtils.isValidString(ls_ilicode))
						throw new B2BException(ErrorKeys.ERROR_SIN_ILICODE);
				}

				{
					String ls_descripcion;

					ls_descripcion     = lc_parametros.getDescripcion();
					lb_focus           = validateStyles(
						    ":fParteInteresadaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;

					ls_activo     = lc_parametros.getActivo();
					lb_focus      = validateStyles(
						    ":fParteInteresadaDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				PrimeFaces.current().ajax().update("fParteInteresadaDetalle");

				lc_parametros.setIdUsuarioCreacion(getUserId());
				lc_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarParteInteresada(
				    lc_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);
			}

			setParametros(null);

			ls_result = NavegacionCommon.PARTE_INTERESADA;
		}

		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
