package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Punto Recaudo Tipo Recaudo.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanPuntoRecaudoTipoRecaudo")
@SessionScoped
public class BeanPuntoRecaudoTipoRecaudo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7296738389723858429L;

	/** Propiedad icta datos auditoria. */
	private Collection<PuntoRecaudoTipoRecaudo> icta_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita punto recaudo tipo recaudo. */
	private PuntoRecaudoTipoRecaudo ita_PuntoRecaudoTipoRecaudo;

	/** Propiedad ita parametros. */
	private PuntoRecaudoTipoRecaudo ita_parametros;

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
	 * @param actr_tr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<PuntoRecaudoTipoRecaudo> actr_tr)
	{
		icta_datosAuditoria = actr_tr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<PuntoRecaudoTipoRecaudo> getDatosAuditoria()
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
	public void setParametros(PuntoRecaudoTipoRecaudo atr_tr)
	{
		ita_parametros = atr_tr;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public PuntoRecaudoTipoRecaudo getParametros()
	{
		if(ita_parametros == null)
			ita_parametros = new PuntoRecaudoTipoRecaudo();

		return ita_parametros;
	}

	/**
	 * Modifica el valor de punto recaudo tipo recaudo.
	 *
	 * @param ata_ta asigna el valor a la propiedad punto recaudo tipo recaudo
	 */
	public void setPuntoRecaudoTipoRecaudo(PuntoRecaudoTipoRecaudo ata_ta)
	{
		ita_PuntoRecaudoTipoRecaudo = ata_ta;
	}

	/**
	 * Retorna el valor de punto recaudo tipo recaudo.
	 *
	 * @return el valor de punto recaudo tipo recaudo
	 */
	public PuntoRecaudoTipoRecaudo getPuntoRecaudoTipoRecaudo()
	{
		return ita_PuntoRecaudoTipoRecaudo;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Punto Recaudo Tipo Recaudo.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setPuntoRecaudoTipoRecaudo(new PuntoRecaudoTipoRecaudo());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarPuntoRecaudoTipoRecaudo");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto PuntoRecaudoTipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(PuntoRecaudoTipoRecaudo ata_ta)
	    throws B2BException
	{
		if(ata_ta != null)
		{
			PuntoRecaudoTipoRecaudo lta_dato;

			lta_dato     = null;

			lta_dato = ipr_parameterRemote.findPuntoRecaudoTipoRecaudoById(ata_ta);

			if(lta_dato != null)
			{
				Collection<PuntoRecaudoTipoRecaudo> lcta_ta;
				lcta_ta = new ArrayList<PuntoRecaudoTipoRecaudo>();

				lcta_ta.add(lta_dato);
				setPuntoRecaudoTipoRecaudo(lta_dato);

				setDatosAuditoria(lcta_ta);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<PuntoRecaudoTipoRecaudo> findAllPuntoRecaudoTipoRecaudo()
	{
		Collection<PuntoRecaudoTipoRecaudo> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllPuntoRecaudoTipoRecaudo();
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
			PuntoRecaudoTipoRecaudo lta_parametros;

			lta_parametros = getPuntoRecaudoTipoRecaudo();

			if(lta_parametros != null)
			{
				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdPuntoRecaudoTipoRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoTipoRecaudoDetalle:idPuntoRecaudoTipoRecaudo", lfc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_CODIGO_PUNTO_RECAUDO_TIPO_RECAUDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdPuntoRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoTipoRecaudoDetalle:idPuntoRecaudo", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_CODIGO_PUNTO_RECAUDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdTipoRecaudo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoTipoRecaudoDetalle:idTipoRecaudo", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_CODIGO_TIPO_RECAUDO_NO_VALIDO);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fPuntoRecaudoTipoRecaudoDetalle:idActivo", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarPuntoRecaudoTipoRecaudo(
				    lta_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setPuntoRecaudoTipoRecaudo(null);

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
