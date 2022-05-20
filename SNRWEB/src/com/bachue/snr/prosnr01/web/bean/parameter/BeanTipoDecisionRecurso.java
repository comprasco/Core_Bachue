package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoDecisionRecurso.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoDecisionRecurso")
@SessionScoped
public class BeanTipoDecisionRecurso extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2977168660700780143L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private TipoDecisionRecurso ir_parametros;

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
	public void setParametros(TipoDecisionRecurso ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoDecisionRecurso getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoDecisionRecurso();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo decision recurso.
	 *
	 * @param acr_cr asigna el valor a la propiedad tipo decision recurso
	 */
	public void setTipoDecisionRecurso(TipoDecisionRecurso acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de tipo decision recurso.
	 *
	 * @return el valor de tipo decision recurso
	 */
	public TipoDecisionRecurso getTipoDecisionRecurso()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un TipoDecisionRecurso.
	 *
	 * @param arc_TipoDecisionRecurso seleccionado
	 *            objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(TipoDecisionRecurso arc_TipoDecisionRecurso, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_TipoDecisionRecurso = new TipoDecisionRecurso();

			setTipoDecisionRecurso(arc_TipoDecisionRecurso);
			setInsertar(true);
		}
		else
		{
			setTipoDecisionRecurso(arc_TipoDecisionRecurso);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.TIPO_DECISION_RECURSO_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoDecisionRecurso> cargarTipoDecisionRecurso()
	{
		Collection<TipoDecisionRecurso> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllTipoDecisionRecurso(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lrc_constantes;
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de TipoDecisionRecurso.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateTipoDecisionRecurso(boolean ab_insertar)
	{
		FacesContext        lfc_context;
		TipoDecisionRecurso lcr_TipoDecisionRecursoInsertUpdate;
		boolean             lb_focus;
		String              ls_result;

		lcr_TipoDecisionRecursoInsertUpdate     = getParametros();
		lfc_context                             = FacesContext.getCurrentInstance();
		lb_focus                                = true;
		ls_result                               = null;

		try
		{
			{
				String ls_descripcion;
				ls_descripcion     = lcr_TipoDecisionRecursoInsertUpdate.getDescripcion();

				lb_focus = validateStyles(
					    ":fTipoDecisionRecursoDetalle:idItDescripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.ERROR_SIN_DESCRIPCION);
			}

			{
				String ls_activo;
				ls_activo     = lcr_TipoDecisionRecursoInsertUpdate.getActivo();

				lb_focus = validateStyles(":fTipoDecisionRecursoDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

				if(!StringUtils.isValidString(ls_activo))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
			}

			ipr_parameterRemote.insertUpdateTipoDecisionRecurso(
			    lcr_TipoDecisionRecursoInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			
			getBeanReference().setTipoDecisionRecurso(null);

			ls_result = NavegacionCommon.TIPO_DECISION_RECURSO;

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

			return null;
		}

		return ls_result;
	}
}
