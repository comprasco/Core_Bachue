package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoTarifaRegistral;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoTarifaRegistral.
 *
 * @author
 */
@ManagedBean(name = "beanTipoTarifaRegistral")
@SessionScoped
public class BeanTipoTarifaRegistral extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad icttr all info. */
	private Collection<TipoTarifaRegistral> icttr_allInfo;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iottr all tipos. */
	private TipoTarifaRegistral iottr_allTipos;

	/** Propiedad iottr detalle tipo. */
	private TipoTarifaRegistral iottr_detalleTipo;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * Modifica el valor de all info.
	 *
	 * @param acttr_ttr asigna el valor a la propiedad all info
	 */
	public void setAllInfo(Collection<TipoTarifaRegistral> acttr_ttr)
	{
		icttr_allInfo = acttr_ttr;
	}

	/**
	 * Retorna el valor de all info.
	 *
	 * @return el valor de all info
	 */
	public Collection<TipoTarifaRegistral> getAllInfo()
	{
		if(!CollectionUtils.isValidCollection(icttr_allInfo))
		{
			TipoTarifaRegistral lottr_ttr;

			try
			{
				setAllTipos(
				    ipr_parameterRemote.findTiposTarifas(
				        null, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
				lottr_ttr = getAllTipos();

				if((lottr_ttr != null) && CollectionUtils.isValidCollection(lottr_ttr.getInfoAll()))
					icttr_allInfo = lottr_ttr.getInfoAll();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return icttr_allInfo;
	}

	/**
	 * Modifica el valor de all tipos.
	 *
	 * @param aottr_ttr asigna el valor a la propiedad all tipos
	 */
	public void setAllTipos(TipoTarifaRegistral aottr_ttr)
	{
		iottr_allTipos = aottr_ttr;
	}

	/**
	 * Retorna el valor de all tipos.
	 *
	 * @return el valor de all tipos
	 */
	public TipoTarifaRegistral getAllTipos()
	{
		if(iottr_allTipos == null)
			iottr_allTipos = new TipoTarifaRegistral();

		return iottr_allTipos;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de detalle tipo.
	 *
	 * @param aottr_ttr asigna el valor a la propiedad detalle tipo
	 */
	public void setDetalleTipo(TipoTarifaRegistral aottr_ttr)
	{
		iottr_detalleTipo = aottr_ttr;
	}

	/**
	 * Retorna el valor de detalle tipo.
	 *
	 * @return el valor de detalle tipo
	 */
	public TipoTarifaRegistral getDetalleTipo()
	{
		if(iottr_detalleTipo == null)
			iottr_detalleTipo = new TipoTarifaRegistral();

		return iottr_detalleTipo;
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
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setDetalleTipo(null);

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setAllInfo(null);
		setDetalleTipo(null);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String consultaDetallada()
	{
		String ls_return;
		ls_return = NavegacionCommon.TIPO_TARIFA_DETALLE;

		try
		{
			TipoTarifaRegistral lr_parametros;
			String              ls_idTipoTarifa;
			Long                ll_version;

			lr_parametros = getDetalleTipo();

			if(lr_parametros != null)
			{
				ls_idTipoTarifa     = FacesUtils.getStringFacesParameter("idTipoTarifa");
				ll_version          = FacesUtils.getLongWrapperFacesParameter("version");

				if(StringUtils.isValidString(ls_idTipoTarifa) && NumericUtils.isValidLong(ll_version))
				{
					lr_parametros.setIdTipoTarifa(ls_idTipoTarifa);
					lr_parametros.setVersion(ll_version);
					lr_parametros.setValidTipoId(true);
					lr_parametros.setValidversion(true);

					lr_parametros = ipr_parameterRemote.findTiposTarifas(
						    lr_parametros, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if((lr_parametros != null) && CollectionUtils.isValidCollection(lr_parametros.getInfoAll()))
						setDetalleTipo(lr_parametros.getInfoAll().iterator().next());

					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String              ls_result;
		TipoTarifaRegistral lr_parametros;

		ls_result = null;

		try
		{
			lr_parametros = getDetalleTipo();

			ipr_parameterRemote.gestionTiposTarifas(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			
			getBeanReference().setTipoTarifaRegistral(null);

			clean();

			ls_result = NavegacionCommon.TIPO_TARIFA;

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
			PrimeFaces.current().ajax().update("fTipoTarifa:globalMsg");
		}

		return ls_result;
	}
}
