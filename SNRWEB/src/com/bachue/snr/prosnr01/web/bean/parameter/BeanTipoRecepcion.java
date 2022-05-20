package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoRecepcion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoRecepcion")
@SessionScoped
public class BeanTipoRecepcion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -579114283674019362L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private TipoRecepcion ir_parametros;

	/** Propiedad itr tiporecepcion. */
	private TipoRecepcion itr_tiporecepcion;

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
	public void setParametros(TipoRecepcion ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoRecepcion getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoRecepcion();

		return ir_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		settiporecepcion(new TipoRecepcion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoRecepcion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Consulta detallada.
	 *
	 * @param atr_tiporecepcion correspondiente al valor del tipo de objeto TipoRecepcion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoRecepcion atr_tiporecepcion)
	    throws B2BException
	{
		settiporecepcion(atr_tiporecepcion);
		setInsertar(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoRecepcion> findAllTipoRecepcion()
	{
		Collection<TipoRecepcion> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllTipoRecepcion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Retorna el valor de tiporecepcion.
	 *
	 * @return el valor de tiporecepcion
	 */
	public TipoRecepcion gettiporecepcion()
	{
		return itr_tiporecepcion;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
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
			TipoRecepcion lr_parametros;

			lr_parametros = gettiporecepcion();

			{
				String ls_habilitadoNotificacion;
				ls_habilitadoNotificacion     = lr_parametros.getHabilitadoNotificacion();

				lb_focus = validateStyles(
					    ":fTipoRecepcionDetalle:idHabilitadoNotificacion", lfc_context, ls_habilitadoNotificacion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_habilitadoNotificacion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILITACION_NOTIFICACION);
			}

			{
				String ls_habilitadoComunicacion;
				ls_habilitadoComunicacion     = lr_parametros.getHabilitadoComunicacion();

				lb_focus = validateStyles(
					    ":fTipoRecepcionDetalle:idHabilitadoComunicacion", lfc_context, ls_habilitadoComunicacion,
					    lb_focus
					);

				if(!StringUtils.isValidString(ls_habilitadoComunicacion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILITACION_COMUNICACION);
			}

			ipr_parameterRemote.salvarTipoRecepcion(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanReference lbr_beanReference;
				lbr_beanReference = getBeanReference();

				lbr_beanReference.setHabilitadoNot(null);
				lbr_beanReference.setHabilitadoNotNegat(null);
				lbr_beanReference.setHabilitadoRec(null);
				lbr_beanReference.setProcedencia(null);
			}

			setParametros(null);

			settiporecepcion(null);

			ls_result = NavegacionCommon.TIPORECEPCION;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

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
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoRecepcion:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Modifica el valor de tiporecepcion.
	 *
	 * @param atr_tr asigna el valor a la propiedad tiporecepcion
	 */
	public void settiporecepcion(TipoRecepcion atr_tr)
	{
		itr_tiporecepcion = atr_tr;
	}
}
