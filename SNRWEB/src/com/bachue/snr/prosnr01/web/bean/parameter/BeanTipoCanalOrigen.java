package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCanalOrigen;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Tipo canal origen.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanTipoCanalOrigen")
@SessionScoped
public class BeanTipoCanalOrigen extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad ictco datos auditoria. */
	private Collection<TipoCanalOrigen> ictco_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itco parametros. */
	private TipoCanalOrigen itco_parametros;

	/** Propiedad itco tipo canal origen. */
	private TipoCanalOrigen itco_tipoCanalOrigen;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param actco_ctco asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoCanalOrigen> actco_ctco)
	{
		ictco_datosAuditoria = actco_ctco;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoCanalOrigen> getDatosAuditoria()
	{
		return ictco_datosAuditoria;
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
	 * @param ata_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoCanalOrigen ata_param)
	{
		itco_parametros = ata_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoCanalOrigen getParametros()
	{
		if(itco_parametros == null)
			itco_parametros = new TipoCanalOrigen();

		return itco_parametros;
	}

	/**
	 * Modifica el valor de tipo canal origen.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo canal origen
	 */
	public void setTipoCanalOrigen(TipoCanalOrigen ata_ta)
	{
		itco_tipoCanalOrigen = ata_ta;
	}

	/**
	 * Retorna el valor de tipo canal origen.
	 *
	 * @return el valor de tipo canal origen
	 */
	public TipoCanalOrigen getTipoCanalOrigen()
	{
		return itco_tipoCanalOrigen;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Tipo canal origen.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoCanalOrigen(new TipoCanalOrigen());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoCanalOrigen");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
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
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @param atco_ta correspondiente al valor del tipo de objeto TipoCanalOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoCanalOrigen atco_ta)
	    throws B2BException
	{
		if(atco_ta != null)
		{
			TipoCanalOrigen ltco_dato;

			ltco_dato     = null;

			ltco_dato = ipr_parameterRemote.findTipoCanalOrigenById(atco_ta);

			if(ltco_dato != null)
			{
				Collection<TipoCanalOrigen> lcta_ta;
				lcta_ta = new ArrayList<TipoCanalOrigen>();

				lcta_ta.add(ltco_dato);
				setTipoCanalOrigen(ltco_dato);

				setDatosAuditoria(lcta_ta);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_AREA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoCanalOrigen> findAllTipoCanalOrigen()
	{
		Collection<TipoCanalOrigen> lctco_tco;
		lctco_tco = null;

		try
		{
			lctco_tco = ipr_parameterRemote.findAllTipoCanalOrigen();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctco_tco;
	}

	/**
	 * Método para salvar la inserción o actualización.
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
			TipoCanalOrigen ltco_parametros;

			ltco_parametros = getTipoCanalOrigen();

			if(ltco_parametros != null)
			{
				if(!isInsertar())
				{
					String ls_idTipoCanalOrigen;
					ls_idTipoCanalOrigen     = ltco_parametros.getIdTipoCanalOrigen();

					lb_focus = validateStyles(
						    ":fTipoCanalOrigenDetalle:idTipoCanalOrigen", lfc_context, ls_idTipoCanalOrigen, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTipoCanalOrigen))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_TIPO_CANAL_ORIGEN_DETALLE);
				}

				{
					String ls_descripcionCanalOrigen;
					ls_descripcionCanalOrigen     = ltco_parametros.getDescripcionCanalOrigen();

					lb_focus = validateStyles(
						    ":fTipoCanalOrigenDetalle:idDescripcionCanalOrigen", lfc_context, ls_descripcionCanalOrigen,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcionCanalOrigen))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = ltco_parametros.getActivo();

					lb_focus = validateStyles(":fTipoCanalOrigenDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoCanalOrigen(
				    ltco_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setTipoCanalOrigen(null);

				ls_result = NavegacionCommon.TIPO_CANAL_ORIGEN;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
