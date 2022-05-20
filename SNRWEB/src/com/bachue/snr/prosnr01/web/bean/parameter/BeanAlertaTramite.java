package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTramite;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Alerta Tramite.
 *
 * @author Jorge Patiño
 */
@ManagedBean(name = "beanAlertaTramite")
@SessionScoped
public class BeanAlertaTramite extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3611452661886961666L;

	/** Propiedad iat alerta tramite. */
	private AlertaTramite iat_alertaTramite;

	/** Propiedad icat parametros. */
	private AlertaTramite icat_parametros;

	/** Propiedad icat datos auditoria. */
	private Collection<AlertaTramite> icat_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * Modifica el valor de alerta tramite.
	 *
	 * @param atr_tr asigna el valor a la propiedad alerta tramite
	 */
	public void setAlertaTramite(AlertaTramite atr_tr)
	{
		iat_alertaTramite = atr_tr;
	}

	/**
	 * Retorna el valor de alerta tramite.
	 *
	 * @return el valor de alerta tramite
	 */
	public AlertaTramite getAlertaTramite()
	{
		return iat_alertaTramite;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param datosAuditoria asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<AlertaTramite> datosAuditoria)
	{
		icat_datosAuditoria = datosAuditoria;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<AlertaTramite> getDatosAuditoria()
	{
		return icat_datosAuditoria;
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
	public void setParametros(AlertaTramite ar_c)
	{
		icat_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public AlertaTramite getParametros()
	{
		if(icat_parametros == null)
			icat_parametros = new AlertaTramite();

		return icat_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Alerta Tramite.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setAlertaTramite(new AlertaTramite());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarAlertaTramite");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_ALERTA_TRAMITE.
	 *
	 * @param aat_at correspondiente al valor del tipo de objeto AlertaTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(AlertaTramite aat_at)
	    throws B2BException
	{
		if(aat_at != null)
		{
			AlertaTramite lcad_datos;
			lcad_datos = ipr_parameterRemote.findAlertaTramiteById(aat_at);

			if(lcad_datos != null)
			{
				Collection<AlertaTramite> lccad_ccad;
				lccad_ccad = new ArrayList<AlertaTramite>();

				lccad_ccad.add(lcad_datos);
				setAlertaTramite(lcad_datos);

				setDatosAuditoria(lccad_ccad);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_ALERTA_TRAMITE
	 * que coincida con un id específico.
	 */
	public void findAlertaTramiteById()
	{
		try
		{
			AlertaTramite ld_parametros;
			ld_parametros = getAlertaTramite();

			if(ld_parametros != null)
			{
				BigDecimal lbd_idAlerta;
				lbd_idAlerta = ld_parametros.getIdAlertaTramite();

				if(NumericUtils.isValidBigDecimal(lbd_idAlerta))
				{
					ld_parametros.setIdAlertaTramite(lbd_idAlerta);
					setParametros(ipr_parameterRemote.findAlertaTramiteById(ld_parametros));
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
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ALERTA_TRAMITE.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<AlertaTramite> findAllAlertaTramite()
	{
		Collection<AlertaTramite> lcr_constantes;
		lcr_constantes = null;

		try
		{
			lcr_constantes = ipr_parameterRemote.findAllAlertaTramite();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización.
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			AlertaTramite lr_parametros;

			lr_parametros = getAlertaTramite();

			{
				String ls_descripcion;
				ls_descripcion     = lr_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fAlertaTramiteDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.ERROR_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lr_parametros.getActivo();

				lb_focus = validateStyles(":fAlertaTramiteDetalle:idActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
			}

			ipr_parameterRemote.salvarAlertaTramite(
			    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fAlertaTramiteDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fAlertaTramiteDetalle:globalMsg");
		}
	}
}
