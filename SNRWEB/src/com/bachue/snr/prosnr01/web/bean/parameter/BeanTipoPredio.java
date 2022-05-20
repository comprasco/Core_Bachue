package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoPredio.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoPredio")
@SessionScoped
public class BeanTipoPredio extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8510400638245281599L;

	/** Propiedad icpt datos auditoria. */
	private Collection<PredioTipo> icpt_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipt parametros. */
	private PredioTipo ipt_parametros;

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
	public void setDatosAuditoria(Collection<PredioTipo> datosAuditoria)
	{
		icpt_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<PredioTipo> getDatosAuditoria()
	{
		return icpt_datosAuditoria;
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
	 * @param parametros asigna el valor a la propiedad parametros
	 */
	public void setParametros(PredioTipo parametros)
	{
		this.ipt_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public PredioTipo getParametros()
	{
		if(ipt_parametros == null)
			ipt_parametros = new PredioTipo();

		return ipt_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setParametros(new PredioTipo());

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			PredioTipo lpt_parametros;
			lpt_parametros = getParametros();

			if(lpt_parametros != null)
			{
				String ls_idTipoPredio;
				ls_idTipoPredio = FacesUtils.getStringFacesParameter("idTipoPredio");

				if(StringUtils.isValidString(ls_idTipoPredio))
				{
					Collection<PredioTipo> lcpt_cpt;

					lcpt_cpt = new ArrayList<PredioTipo>();

					lpt_parametros.setIdTipoPredio(ls_idTipoPredio);

					setParametros(ipr_parameterRemote.findTipoPredioById(lpt_parametros));
					lcpt_cpt.add(getParametros());

					setDatosAuditoria(lcpt_cpt);
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
	public Collection<PredioTipo> findAllTipoPredio()
	{
		Collection<PredioTipo> lctus_data;
		lctus_data = null;

		try
		{
			lctus_data = ipr_parameterRemote.findAllTipoPredio();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctus_data;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			PredioTipo   lpt_parametros;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus           = true;
			lpt_parametros     = getParametros();
			lfc_context        = FacesContext.getCurrentInstance();

			if(lpt_parametros != null)
			{
				{
					String ls_idTipoPredio;
					ls_idTipoPredio     = lpt_parametros.getIdTipoPredio();

					lb_focus = validateStyles(
						    ":ftipoPredioDetalle:idPredioTipo", lfc_context, ls_idTipoPredio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoPredio))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_CODIGO_TIPO_PREDIO);
				}

				{
					String ls_nombre;
					ls_nombre     = lpt_parametros.getIlicode();

					lb_focus = validateStyles(":ftipoPredioDetalle:idIlicode", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ILICODE);
				}

				{
					String ls_activo;
					ls_activo     = lpt_parametros.getActivo();

					lb_focus = validateStyles(":ftipoPredioDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lpt_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":ftipoPredioDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				ipr_parameterRemote.salvarTipoPredio(
				    lpt_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setTipoPredio(null);

				setParametros(null);

				ls_result = NavegacionCommon.TIPO_PREDIO;

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
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoPredio:globalMsg");
		}

		return ls_result;
	}
}
