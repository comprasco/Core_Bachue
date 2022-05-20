package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Entidad Recaudadora.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanEntidadRecaudadora")
@SessionScoped
public class BeanEntidadRecaudadora extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2241794128239578435L;

	/** Propiedad icta datos auditoria. */
	private Collection<EntidadRecaudadora> icta_datosAuditoria;

	/** Propiedad ita entidad recaudadora. */
	private EntidadRecaudadora ita_EntidadRecaudadora;

	/** Propiedad ita parametros. */
	private EntidadRecaudadora ita_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

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
	public void setDatosAuditoria(Collection<EntidadRecaudadora> actr_tr)
	{
		icta_datosAuditoria = actr_tr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<EntidadRecaudadora> getDatosAuditoria()
	{
		return icta_datosAuditoria;
	}

	/**
	 * Modifica el valor de entidad recaudadora.
	 *
	 * @param ata_ta asigna el valor a la propiedad entidad recaudadora
	 */
	public void setEntidadRecaudadora(EntidadRecaudadora ata_ta)
	{
		ita_EntidadRecaudadora = ata_ta;
	}

	/**
	 * Retorna el valor de entidad recaudadora.
	 *
	 * @return el valor de entidad recaudadora
	 */
	public EntidadRecaudadora getEntidadRecaudadora()
	{
		return ita_EntidadRecaudadora;
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
	public void setParametros(EntidadRecaudadora atr_tr)
	{
		ita_parametros = atr_tr;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EntidadRecaudadora getParametros()
	{
		if(ita_parametros == null)
			ita_parametros = new EntidadRecaudadora();

		return ita_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Entidad Recaudadora.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEntidadRecaudadora(new EntidadRecaudadora());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEntidadRecaudadora");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto EntidadRecaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(EntidadRecaudadora ata_ta)
	    throws B2BException
	{
		if(ata_ta != null)
		{
			String ls_idEntidadRecaudadora;

			ls_idEntidadRecaudadora = FacesUtils.getStringFacesParameter("idEntidadRecaudadora");

			if(StringUtils.isValidString(ls_idEntidadRecaudadora))
			{
				EntidadRecaudadora lta_dato;

				lta_dato = null;

				ata_ta.setIdEntidadRecaudadora(ls_idEntidadRecaudadora);

				lta_dato = ipr_parameterRemote.findEntidadRecaudadoraById(ata_ta);

				if(lta_dato != null)
				{
					Collection<EntidadRecaudadora> lcta_ta;

					lcta_ta = new ArrayList<EntidadRecaudadora>();

					lcta_ta.add(lta_dato);

					setEntidadRecaudadora(lta_dato);
					setDatosAuditoria(lcta_ta);
				}

				setInsertar(false);
			}
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EntidadRecaudadora> findAllEntidadRecaudadora()
	{
		Collection<EntidadRecaudadora> lctr_tr;
		lctr_tr = null;

		try
		{
			lctr_tr = ipr_parameterRemote.findAllEntidadRecaudadora();
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
			EntidadRecaudadora lta_parametros;

			lta_parametros = getEntidadRecaudadora();

			if(lta_parametros != null)
			{
				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getIdEntidadRecaudadora();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraDetalle:idEntidadRecaudadora", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_ID_ENTIDAD_RECAUDADORA_INVALIDA);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getNombreEntidadRecaudadora();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraDetalle:idNombreEntidadRecaudadora", lfc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_ENTIDAD_RECAUDADORA);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getCodigoEntidadRecaudadora();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraDetalle:idCodigoEntidadRecaudadora", lfc_context, ls_datoValidar,
						    lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_INVALIDA);
				}

				{
					String ls_datoValidar;
					ls_datoValidar     = lta_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fEntidadRecaudadoraDetalle:idActivo", lfc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEntidadRecaudadora(
				    lta_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setEntidadRecaudadora(null);

				ls_result = NavegacionCommon.ENTIDAD_RECAUDADORA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
