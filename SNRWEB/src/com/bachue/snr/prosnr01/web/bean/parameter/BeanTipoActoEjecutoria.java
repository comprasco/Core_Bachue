package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoEjecutoria;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoActoEjecutoria.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoActoEjecutoria")
@SessionScoped
public class BeanTipoActoEjecutoria extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2380090356007668573L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTipoActoEjecutoria.class);

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir parametros. */
	private TipoActoEjecutoria ir_parametros;

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
	public void setParametros(TipoActoEjecutoria ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoActoEjecutoria getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TipoActoEjecutoria();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tipo acto ejecutoria.
	 *
	 * @param acr_cr asigna el valor a la propiedad tipo acto ejecutoria
	 */
	public void setTipoActoEjecutoria(TipoActoEjecutoria acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de tipo acto ejecutoria.
	 *
	 * @return el valor de tipo acto ejecutoria
	 */
	public TipoActoEjecutoria getTipoActoEjecutoria()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un TipoActoEjecutoria.
	 *
	 * @param arc_tipoActoEjecutoria            seleccionado objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(TipoActoEjecutoria arc_tipoActoEjecutoria, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_tipoActoEjecutoria = new TipoActoEjecutoria();

			setTipoActoEjecutoria(arc_tipoActoEjecutoria);
			setInsertar(true);
		}
		else
		{
			setTipoActoEjecutoria(arc_tipoActoEjecutoria);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.TIPO_ACTO_EJECUTORIA_DETALLE;

		return ls_return;
	}

	/**
	 * Método para actualizar la versión del acto seleccionado.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoActoEjecutoria> cargarTipoActoEjecutoria()
	{
		Collection<TipoActoEjecutoria> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllTipoActoEjecutoria(
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
	 * Metodo para el manejo de inserciones o actualizaciones de TipoActoEjecutoria.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateTipoActoEjecutoria(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;
		String       ls_return;

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;
		ls_return       = null;

		try
		{
			TipoActoEjecutoria lae_tipoActoEjecutoria;

			lae_tipoActoEjecutoria = getParametros();

			if(lae_tipoActoEjecutoria != null)
			{
				{
					String ls_idTipoDocumento;
					ls_idTipoDocumento     = lae_tipoActoEjecutoria.getIdTipoDocumento();

					lb_focus = validateStyles(
						    ":fsTipoActoEjecutoriaDetalle:idTipoDocumento", lfc_context, ls_idTipoDocumento, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					String ls_habiles;
					ls_habiles     = lae_tipoActoEjecutoria.getHabiles();

					lb_focus = validateStyles(
						    ":fsTipoActoEjecutoriaDetalle:idHabiles", lfc_context, ls_habiles, lb_focus
						);

					if(!StringUtils.isValidString(ls_habiles))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_HABILES);
				}

				{
					String ls_activo;
					ls_activo     = lae_tipoActoEjecutoria.getActivo();

					lb_focus = validateStyles(
						    ":fsTipoActoEjecutoriaDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				{
					String li_tipoActo;

					li_tipoActo     = lae_tipoActoEjecutoria.getIdTipoActo();
					lb_focus        = validateStyles(
						    ":fsTipoActoEjecutoriaDetalle:idItTipoActo", lfc_context, li_tipoActo, lb_focus
						);

					if(!StringUtils.isValidString(li_tipoActo))
						throw new B2BException(ErrorKeys.ERROR_TIPO_ACTO_INVALIDO);
				}

				ipr_parameterRemote.insertUpdateTipoActoEjecutoria(
				    lae_tipoActoEjecutoria, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				ls_return = NavegacionCommon.TIPO_ACTO_EJECUTORIA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Traer maxima version.
	 */
	public void traerMaximaVersion()
	{
		try
		{
			TipoActoEjecutoria ltae_tipoActo;

			ltae_tipoActo = getParametros();

			if(ltae_tipoActo != null)
			{
				String ls_tipoActo;

				ls_tipoActo = ltae_tipoActo.getIdTipoActo();

				if(StringUtils.isValidString(ls_tipoActo))
					ltae_tipoActo.setVersionActo(
					    NumericUtils.getInteger(irr_referenceRemote.findAllMaxVersionTipoActoEjecutoria(ls_tipoActo))
					);
			}
		}
		catch(B2BException e)
		{
			clh_LOGGER.error("traerMaximaVersion", e);
		}
	}
}
