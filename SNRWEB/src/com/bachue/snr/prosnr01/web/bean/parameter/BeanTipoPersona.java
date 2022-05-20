package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoPersona.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoPersona")
@SessionScoped
public class BeanTipoPersona extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9158135734713321708L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itp parametros. */
	private TipoPersona itp_parametros;

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
	 * @param atp_tp asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoPersona atp_tp)
	{
		itp_parametros = atp_tp;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoPersona getParametros()
	{
		if(itp_parametros == null)
			itp_parametros = new TipoPersona();

		return itp_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
		{
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
			setParametros(null);
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			TipoPersona ltp_parametros;
			ltp_parametros = getParametros();

			if(ltp_parametros != null)
			{
				String ls_idTipoPersona;
				ls_idTipoPersona = FacesUtils.getStringFacesParameter("idTipoPersona");

				if(StringUtils.isValidString(ls_idTipoPersona))
				{
					ltp_parametros.setIdTipoPersona(ls_idTipoPersona);

					setParametros(ipr_parameterRemote.findTipoPersonaById(ltp_parametros));
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
	public Collection<TipoPersona> findAllTipoPersona()
	{
		Collection<TipoPersona> llp_datos;
		llp_datos = null;

		try
		{
			llp_datos = ipr_parameterRemote.findAllTipoPersona();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return llp_datos;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String  ls_return;
		boolean lb_focus;
		ls_return     = NavegacionCommon.TIPO_PERSONA;
		lb_focus      = true;

		try
		{
			TipoPersona  ltp_parametros;
			FacesContext lfc_context;

			ltp_parametros     = getParametros();
			lfc_context        = FacesContext.getCurrentInstance();

			if(ltp_parametros != null)
			{
				{
					String ls_idTipoPersona;
					ls_idTipoPersona     = ltp_parametros.getIdTipoPersona();

					lb_focus = validateStyles(
						    ":fTipoPersonaDetalle:idTipoPersona", lfc_context, ls_idTipoPersona, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoPersona))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA);
				}

				{
					String ls_descripcion;
					ls_descripcion     = ltp_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fTipoPersonaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				ipr_parameterRemote.salvarTipoPersona(
				    ltp_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setTipoPersona(null);

				setParametros(null);
				ls_return = NavegacionCommon.TIPO_PERSONA;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = lfc_context.getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoPersona:globalMsg");
			ls_return = NavegacionCommon.TIPO_PERSONA_DETALLE;
		}

		return ls_return;
	}
}
