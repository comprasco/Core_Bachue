package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoArea;

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
 * Clase para el manejo de la capa web para Tipo Area.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanTipoArea")
@SessionScoped
public class BeanTipoArea extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad icta datos auditoria. */
	private Collection<TipoArea> icta_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita parametros. */
	private TipoArea ita_parametros;

	/** Propiedad ita tipo area. */
	private TipoArea ita_tipoArea;

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
	 * @param acta_cta asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoArea> acta_cta)
	{
		icta_datosAuditoria = acta_cta;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoArea> getDatosAuditoria()
	{
		return icta_datosAuditoria;
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
	public void setParametros(TipoArea ata_param)
	{
		ita_parametros = ata_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoArea getParametros()
	{
		if(ita_parametros == null)
			ita_parametros = new TipoArea();

		return ita_parametros;
	}

	/**
	 * Modifica el valor de tipo area.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo area
	 */
	public void setTipoArea(TipoArea ata_ta)
	{
		ita_tipoArea = ata_ta;
	}

	/**
	 * Retorna el valor de tipo area.
	 *
	 * @return el valor de tipo area
	 */
	public TipoArea getTipoArea()
	{
		return ita_tipoArea;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Tipo Area.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoArea(new TipoArea());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoArea");

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
	 * @param ata_ta correspondiente al valor del tipo de objeto TipoArea
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoArea ata_ta)
	    throws B2BException
	{
		if(ata_ta != null)
		{
			TipoArea lta_dato;

			lta_dato     = null;

			lta_dato = ipr_parameterRemote.findTipoAreaById(ata_ta);

			if(lta_dato != null)
			{
				Collection<TipoArea> lcta_ta;
				lcta_ta = new ArrayList<TipoArea>();

				lcta_ta.add(lta_dato);
				setTipoArea(lta_dato);

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
	public Collection<TipoArea> findAllTipoArea()
	{
		Collection<TipoArea> lcta_ta;
		lcta_ta = null;

		try
		{
			lcta_ta = ipr_parameterRemote.findAllTipoArea();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcta_ta;
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
			TipoArea lta_parametros;

			lta_parametros = getTipoArea();

			if(lta_parametros != null)
			{
				if(!isInsertar())
				{
					String ls_idTipoArea;
					ls_idTipoArea     = lta_parametros.getIdTipoArea();

					lb_focus = validateStyles(":fTipoAreaDetalle:idTipoArea", lfc_context, ls_idTipoArea, lb_focus);

					if(!StringUtils.isValidString(ls_idTipoArea))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_TIPO_AREA);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lta_parametros.getDescripcion();

					lb_focus = validateStyles(":fTipoAreaDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = lta_parametros.getActivo();

					lb_focus = validateStyles(":fTipoAreaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoArea(
				    lta_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setTipoArea(null);

				ls_result = NavegacionCommon.TIPO_AREA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
