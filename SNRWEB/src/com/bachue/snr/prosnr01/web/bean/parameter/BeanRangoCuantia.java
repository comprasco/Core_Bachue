package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.RangoCuantia;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanRangoCuantia.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanRangoCuantia")
@SessionScoped
public class BeanRangoCuantia extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4353044442942937364L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private RangoCuantia ir_parametros;

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
	public void setParametros(RangoCuantia ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public RangoCuantia getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new RangoCuantia();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de rango cuantia.
	 *
	 * @param acr_cr asigna el valor a la propiedad rango cuantia
	 */
	public void setRangoCuantia(RangoCuantia acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de rango cuantia.
	 *
	 * @return el valor de rango cuantia
	 */
	public RangoCuantia getRangoCuantia()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un rango cuantia.
	 *
	 * @param arc_RangoCuantia correspondiente al valor del tipo de objeto RangoCuantia
	 * @param ab_proceso            indica si se va a insertar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(RangoCuantia arc_RangoCuantia, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_RangoCuantia = new RangoCuantia();

			setRangoCuantia(arc_RangoCuantia);
			setInsertar(true);
		}
		else
		{
			setRangoCuantia(arc_RangoCuantia);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.RANGO_CUANTIA_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<RangoCuantia> cargarRangosCuantia()
	{
		Collection<RangoCuantia> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllRangoCuantia(
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
	 * Metodo para el manejo de inserciones o actualizaciones de Rango Cuantia.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateRangoCuantia(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			RangoCuantia lcr_RangoCuantiaInsertUpdate;

			lcr_RangoCuantiaInsertUpdate = getParametros();

			if(lcr_RangoCuantiaInsertUpdate != null)
			{
				{
					String ls_operadorInferior;
					ls_operadorInferior     = lcr_RangoCuantiaInsertUpdate.getOperadorInferior();

					lb_focus = validateStyles(
						    ":fRangoCuantiaDetalle:idItOperadorInferior", lfc_context, ls_operadorInferior, lb_focus
						);

					if(!StringUtils.isValidString(ls_operadorInferior))
						throw new B2BException(ErrorKeys.ERROR_SIN_OPERADOR_INFERIOR_RANGO_CUANTIA);
				}

				{
					Double ld_rangoInferior;

					ld_rangoInferior     = NumericUtils.getDoubleWrapper(
						    lcr_RangoCuantiaInsertUpdate.getRangoInferior()
						);

					lb_focus = validateStyles(
						    ":fRangoCuantiaDetalle:idItRangoInferior", lfc_context, ld_rangoInferior, lb_focus
						);

					if(!NumericUtils.isValidDouble(ld_rangoInferior))
						throw new B2BException(ErrorKeys.ERROR_SIN_RANGO_INFERIOR_RANGO_CUANTIA);
				}

				{
					Date ls_fechaDesde;
					ls_fechaDesde     = lcr_RangoCuantiaInsertUpdate.getFechaDesde();

					lb_focus = validateStyles(
						    ":fRangoCuantiaDetalle:idItFechaDesde", lfc_context, ls_fechaDesde, lb_focus
						);

					if(ls_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;

					ls_activo     = lcr_RangoCuantiaInsertUpdate.getActivo();
					lb_focus      = validateStyles(
						    ":fRangoCuantiaDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.insertUpdateRangoCuantia(
				    lcr_RangoCuantiaInsertUpdate, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.RANGO_CUANTIA;
	}
}
