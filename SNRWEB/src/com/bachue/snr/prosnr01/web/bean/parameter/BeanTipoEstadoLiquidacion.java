package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEstadoLiquidacion;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Tipo Estado Liquidacion.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanTipoEstadoLiquidacion")
@SessionScoped
public class BeanTipoEstadoLiquidacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad ictel datos auditoria. */
	private Collection<TipoEstadoLiquidacion> ictel_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itel parametros. */
	private TipoEstadoLiquidacion itel_parametros;

	/** Propiedad itel tipo estado liquidacion. */
	private TipoEstadoLiquidacion itel_tipoEstadoLiquidacion;

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
	 * @param actel_ctel asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoEstadoLiquidacion> actel_ctel)
	{
		ictel_datosAuditoria = actel_ctel;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoEstadoLiquidacion> getDatosAuditoria()
	{
		return ictel_datosAuditoria;
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
	 * @param atel_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoEstadoLiquidacion atel_param)
	{
		itel_parametros = atel_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoEstadoLiquidacion getParametros()
	{
		if(itel_parametros == null)
			itel_parametros = new TipoEstadoLiquidacion();

		return itel_parametros;
	}

	/**
	 * Modifica el valor de tipo estado liquidacion.
	 *
	 * @param atel_tel asigna el valor a la propiedad tipo estado liquidacion
	 */
	public void setTipoEstadoLiquidacion(TipoEstadoLiquidacion atel_tel)
	{
		itel_tipoEstadoLiquidacion = atel_tel;
	}

	/**
	 * Retorna el valor de tipo estado liquidacion.
	 *
	 * @return el valor de tipo estado liquidacion
	 */
	public TipoEstadoLiquidacion getTipoEstadoLiquidacion()
	{
		return itel_tipoEstadoLiquidacion;
	}

/**
 * Método para cambiar estado para saber si se desea insertar un nuevo
 * Tipo Estado Liquidacion.
 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoEstadoLiquidacion(new TipoEstadoLiquidacion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoEstadoLiquidacion");

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
 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_ESTADO_LIQUIDACION.
 *
 * @param atel_tel correspondiente al valor del tipo de objeto TipoEstadoLiquidacion
 * @throws B2BException Señala que se ha producido una excepción
 */
	public void consultaDetallada(TipoEstadoLiquidacion atel_tel)
	    throws B2BException
	{
		if(atel_tel != null)
		{
			TipoEstadoLiquidacion ltel_dato;

			ltel_dato     = null;

			ltel_dato = ipr_parameterRemote.findTipoEstadoLiquidacionById(atel_tel);

			if(ltel_dato != null)
			{
				Collection<TipoEstadoLiquidacion> lctel_tel;
				lctel_tel = new ArrayList<TipoEstadoLiquidacion>();

				lctel_tel.add(ltel_dato);
				setTipoEstadoLiquidacion(ltel_dato);

				setDatosAuditoria(lctel_tel);
			}

			setInsertar(false);
		}
	}

/**
 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_ESTADO_LIQUIDACION.
 *
 * @return devuelve el valor de Collection
 */
	public Collection<TipoEstadoLiquidacion> findAllTipoEstadoLiquidacion()
	{
		Collection<TipoEstadoLiquidacion> lctel_tel;
		lctel_tel = null;

		try
		{
			lctel_tel = ipr_parameterRemote.findAllTipoEstadoLiquidacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctel_tel;
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
			TipoEstadoLiquidacion ltel_parametros;

			ltel_parametros = getTipoEstadoLiquidacion();

			if(ltel_parametros != null)
			{
				{
					String ls_descripcionEstadoLiquidacion;
					ls_descripcionEstadoLiquidacion     = ltel_parametros.getDescripcionEstadoLiquidacion();

					lb_focus = validateStyles(
						    ":fTipoEstadoLiquidacionDetalle:idDescripcion", lfc_context, ls_descripcionEstadoLiquidacion,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcionEstadoLiquidacion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = ltel_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fTipoEstadoLiquidacionDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoEstadoLiquidacion(
				    ltel_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setTipoEstadoLiquidacion(null);

				ls_result = NavegacionCommon.TIPO_ESTADO_LIQUIDACION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTipoEstadoLiquidacionDetalle");
		}

		return ls_result;
	}
}
