package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoUsoSuelo.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoUsoSuelo")
@SessionScoped
public class BeanTipoUsoSuelo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8569753680255812564L;

	/** Propiedad ictus datos auditoria. */
	private Collection<TipoUsoSuelo> ictus_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itus parametros. */
	private TipoUsoSuelo itus_parametros;

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
	public void setDatosAuditoria(Collection<TipoUsoSuelo> datosAuditoria)
	{
		ictus_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoUsoSuelo> getDatosAuditoria()
	{
		return ictus_datosAuditoria;
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
	public void setParametros(TipoUsoSuelo parametros)
	{
		this.itus_parametros = parametros;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoUsoSuelo getParametros()
	{
		if(itus_parametros == null)
			itus_parametros = new TipoUsoSuelo();

		return itus_parametros;
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		setParametros(new TipoUsoSuelo());

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
			TipoUsoSuelo ltus_parametros;
			ltus_parametros = getParametros();

			if(ltus_parametros != null)
			{
				String ls_idTipoUsoSuelo;
				ls_idTipoUsoSuelo = FacesUtils.getStringFacesParameter("idUsoSuelo");

				if(StringUtils.isValidString(ls_idTipoUsoSuelo))
				{
					Collection<TipoUsoSuelo> lctus_ctus;

					lctus_ctus = new ArrayList<TipoUsoSuelo>();

					ltus_parametros.setIdTipoUsoSuelo(ls_idTipoUsoSuelo);

					setParametros(ipr_parameterRemote.findTipoUsoPredioById(ltus_parametros));
					lctus_ctus.add(getParametros());

					setDatosAuditoria(lctus_ctus);
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
	public Collection<TipoUsoSuelo> findAllTipoUsoSuelo()
	{
		Collection<TipoUsoSuelo> lctus_data;
		lctus_data = null;

		try
		{
			lctus_data = ipr_parameterRemote.findAllTipoUsoSuelo();
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
			TipoUsoSuelo ltus_parametros;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus            = true;
			ltus_parametros     = getParametros();
			lfc_context         = FacesContext.getCurrentInstance();

			if(ltus_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = ltus_parametros.getIlicode();

					lb_focus = validateStyles(":ftipoUsoSuelolDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_descripcion;
					ls_descripcion     = ltus_parametros.getDescription();

					lb_focus = validateStyles(
						    ":ftipoUsoSuelolDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = ltus_parametros.getActivo();

					lb_focus = validateStyles(":ftipoUsoSuelolDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoUsoSuelo(
				    ltus_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setTipoUsoSuelo(null);

				setParametros(null);

				ls_result = NavegacionCommon.TIPO_USO_SUELO;

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
			PrimeFaces.current().ajax().update("fTipoUsoSuelo:globalMsg");
		}

		return ls_result;
	}
}
