package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoRRR.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoRRR")
@SessionScoped
public class BeanTipoRRR extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8510419485664913167L;

	/** Propiedad ictr datos auditoria. */
	private Collection<TipoRrr> ictr_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itr parametros. */
	private TipoRrr itr_parametros;

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
	public void setDatosAuditoria(Collection<TipoRrr> datosAuditoria)
	{
		ictr_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoRrr> getDatosAuditoria()
	{
		return ictr_datosAuditoria;
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
	public void setParametros(TipoRrr parametros)
	{
		itr_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoRrr getParametros()
	{
		if(itr_parametros == null)
			itr_parametros = new TipoRrr();

		return itr_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setParametros(new TipoRrr());

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
			TipoRrr ltr_parametros;
			ltr_parametros = getParametros();

			if(ltr_parametros != null)
			{
				String ls_idTipoRRR;
				ls_idTipoRRR = FacesUtils.getStringFacesParameter("idTipoRRR");

				if(StringUtils.isValidString(ls_idTipoRRR))
				{
					Collection<TipoRrr> lctr_ctr;

					lctr_ctr = new ArrayList<TipoRrr>();

					ltr_parametros.setIdTipoRrr(ls_idTipoRRR);

					setParametros(ipr_parameterRemote.findTipoRRRById(ltr_parametros));
					lctr_ctr.add(getParametros());

					setDatosAuditoria(lctr_ctr);
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
	public Collection<TipoRrr> findAllTipoRRR()
	{
		Collection<TipoRrr> lctr_data;
		lctr_data = null;

		try
		{
			lctr_data = ipr_parameterRemote.findAllTipoRRR();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctr_data;
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
			TipoRrr      ltr_parametros;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus           = true;
			ltr_parametros     = getParametros();
			lfc_context        = FacesContext.getCurrentInstance();

			if(ltr_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = ltr_parametros.getIlicode();

					lb_focus = validateStyles(":ftipoPredioDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_descripcion;
					ls_descripcion     = ltr_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":ftipoPredioDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = ltr_parametros.getActivo();

					lb_focus = validateStyles(":ftipoPredioDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoRRR(
				    ltr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setTipoRrr(null);

				setParametros(null);

				ls_result = NavegacionCommon.TIPO_RRR;

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
			PrimeFaces.current().ajax().update("ftipoRRR:globalMsg");
		}

		return ls_result;
	}
}
