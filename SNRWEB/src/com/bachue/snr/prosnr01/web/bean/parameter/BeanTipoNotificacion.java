package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.TipoNotificacion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTipoNotificacion.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanTipoNotificacion")
@SessionScoped
public class BeanTipoNotificacion extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7996739055218569694L;

	/** Propiedad ictn datos auditoria. */
	private Collection<TipoNotificacion> ictn_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ictn parametros. */
	private TipoNotificacion ictn_parametros;

	/** Propiedad itn tipo notificacion. */
	private TipoNotificacion itn_tipoNotificacion;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoNotificacion> getDatosAuditoria()
	{
		return ictn_datosAuditoria;
	}

	/**
	 * @param datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoNotificacion> datosAuditoria)
	{
		ictn_datosAuditoria = datosAuditoria;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(TipoNotificacion parametros)
	{
		ictn_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoNotificacion getParametros()
	{
		if(ictn_parametros == null)
			ictn_parametros = new TipoNotificacion();

		return ictn_parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoNotificacion getTipoNotificacion()
	{
		return itn_tipoNotificacion;
	}

	/**
	 * @param acs_cs asigna el valor a la propiedad
	 */
	public void setTipoNotificacion(TipoNotificacion acs_cs)
	{
		itn_tipoNotificacion = acs_cs;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nuevo
	 * Tipo Notificacion
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoNotificacion(new TipoNotificacion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoNotificacion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_TIPO_NOTIFICACION
	 * @return
	 */
	public Collection<TipoNotificacion> findTipoNotificacion()
	{
		Collection<TipoNotificacion> lctn_constantes;
		lctn_constantes = null;

		try
		{
			lctn_constantes = ipr_parameterRemote.findTipoNotificacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctn_constantes;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PNG_TIPO_NOTIFICACION
	 * @param atn_tn
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoNotificacion atn_tn)
	    throws B2BException
	{
		if(atn_tn != null)
		{
			TipoNotificacion lctn_datos;
			lctn_datos = ipr_parameterRemote.findTipoNotificacionById(atn_tn);

			if(lctn_datos != null)
			{
				Collection<TipoNotificacion> lcctn_cctn;
				lcctn_cctn = new ArrayList<TipoNotificacion>();

				lcctn_cctn.add(lctn_datos);
				setTipoNotificacion(lctn_datos);

				setDatosAuditoria(lcctn_cctn);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 * @return
	 */
	public String salvar()
	{
		String       ls_result;
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_result       = null;

		try
		{
			TipoNotificacion ltn_parametros;

			ltn_parametros = getTipoNotificacion();

			if(ltn_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = ltn_parametros.getTipoNotificacion();

					lb_focus = validateStyles(
						    ":fTipoNotificacionDetalle:tipoNotificacion", lfc_context, ls_nombre, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_TIPO_NOTIFICACION);
				}

				{
					String ls_activo;

					ls_activo     = ltn_parametros.getActivo();
					lb_focus      = validateStyles(
						    ":fTipoNotificacionDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoNotificacion(
				    ltn_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				ls_result = NavegacionCommon.TIPO_NOTIFICACION;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fTipoNotificacionDetalle:globalMsg");
				
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
			PrimeFaces.current().ajax().update("fTipoNotificacionDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}
}
