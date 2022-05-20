package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Tipo Recaudo.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanTipoRecaudo")
@SessionScoped
public class BeanTipoRecaudo extends BaseBean implements Serializable
{

	/** Constante serialVersionUID. */
	private static final long       serialVersionUID    = 2241794128239578435L;

	/** Propiedad icta datos auditoria. */
	private Collection<TipoRecaudo> icta_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote         ipr_parameterRemote;

	/** Propiedad ita tipo recaudo. */
	private TipoRecaudo             ita_TipoRecaudo;

	/** Propiedad ita parametros. */
	private TipoRecaudo             ita_parametros;

	/** Propiedad ib insertar. */
	private boolean                 ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param actr_tr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<TipoRecaudo> actr_tr)
	{
		icta_datosAuditoria                             = actr_tr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TipoRecaudo> getDatosAuditoria()
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
	 * @param atr_tr asigna el valor a la propiedad parametros
	 */
	public void setParametros(TipoRecaudo atr_tr)
	{
		ita_parametros = atr_tr;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TipoRecaudo getParametros()
	{
		if(ita_parametros == null)
			ita_parametros = new TipoRecaudo();

		return ita_parametros;
	}

	/**
	 * Modifica el valor de tipo recaudo.
	 *
	 * @param ata_ta asigna el valor a la propiedad tipo recaudo
	 */
	public void setTipoRecaudo(TipoRecaudo ata_ta)
	{
		ita_TipoRecaudo = ata_ta;
	}

	/**
	 * Retorna el valor de tipo recaudo.
	 *
	 * @return el valor de tipo recaudo
	 */
	public TipoRecaudo getTipoRecaudo()
	{
		return ita_TipoRecaudo;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Tipo Recaudo.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoRecaudo(new TipoRecaudo());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoRecaudo");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto TipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(TipoRecaudo ata_ta)
			throws B2BException
	{
		if(ata_ta != null)
		{
			TipoRecaudo lta_dato;

			lta_dato     = null;

			lta_dato = ipr_parameterRemote.findTipoRecaudoById(ata_ta);

			if(lta_dato != null)
			{
				Collection<TipoRecaudo> lcta_ta;
				lcta_ta = new ArrayList<TipoRecaudo>();

				lcta_ta.add(lta_dato);
				setTipoRecaudo(lta_dato);

				setDatosAuditoria(lcta_ta);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TipoRecaudo> findAllTipoRecaudo()
	{
		Collection<TipoRecaudo> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllTipoRecaudo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lctr_tr;
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
			TipoRecaudo lta_parametros;

			lta_parametros = getTipoRecaudo();

			if(lta_parametros != null)
			{
				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdTipoRecaudo();

					lb_focus = validateStyles(
							":fTipoRecaudoDetalle:idTipoRecaudo", lfc_context, ls_datoValidar, lb_focus
							);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_CODIGO_TIPO_RECAUDO_NO_VALIDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getNombreTipoRecaudo();

					lb_focus = validateStyles(
							":fTipoRecaudoDetalle:idNombreTipoRecaudo", lfc_context, ls_datoValidar, lb_focus
							);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_TIPO_RECAUDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getActivo();

					lb_focus = validateStyles(":fTipoRecaudoDetalle:idActivo", lfc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoRecaudo(
						lta_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

				setParametros(null);
				setTipoRecaudo(null);

				ls_result = NavegacionCommon.TIPO_RECAUDO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
