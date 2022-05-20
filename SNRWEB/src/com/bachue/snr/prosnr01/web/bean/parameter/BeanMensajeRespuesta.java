package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.MensajeRespuesta;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Mensaje Respuesta.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanMensajeRespuesta")
@SessionScoped
public class BeanMensajeRespuesta extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3172882772505248128L;

	/** Propiedad icmr datos auditoria. */
	private Collection<MensajeRespuesta> icmr_datosAuditoria;

	/** Propiedad imr mensaje respuesta. */
	private MensajeRespuesta imr_mensajeRespuesta;

	/** Propiedad imr parametros. */
	private MensajeRespuesta imr_parametros;

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
	 * @param acmr_cmr asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<MensajeRespuesta> acmr_cmr)
	{
		icmr_datosAuditoria = acmr_cmr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<MensajeRespuesta> getDatosAuditoria()
	{
		return icmr_datosAuditoria;
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
	 * Modifica el valor de mensaje respuesta.
	 *
	 * @param amr_mr asigna el valor a la propiedad mensaje respuesta
	 */
	public void setMensajeRespuesta(MensajeRespuesta amr_mr)
	{
		imr_mensajeRespuesta = amr_mr;
	}

	/**
	 * Retorna el valor de mensaje respuesta.
	 *
	 * @return el valor de mensaje respuesta
	 */
	public MensajeRespuesta getMensajeRespuesta()
	{
		return imr_mensajeRespuesta;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param amr_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(MensajeRespuesta amr_param)
	{
		imr_parametros = amr_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public MensajeRespuesta getParametros()
	{
		if(imr_parametros == null)
			imr_parametros = new MensajeRespuesta();

		return imr_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Mensaje Respuesta.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setMensajeRespuesta(new MensajeRespuesta());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarMensajeRespuesta");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @param amr_mr correspondiente al valor del tipo de objeto MensajeRespuesta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(MensajeRespuesta amr_mr)
	    throws B2BException
	{
		if(amr_mr != null)
		{
			amr_mr = ipr_parameterRemote.findMensajeRespuestaById(amr_mr.getCodigoMensaje());

			if(amr_mr != null)
			{
				Collection<MensajeRespuesta> lcmr_mr;

				lcmr_mr = new ArrayList<MensajeRespuesta>();

				lcmr_mr.add(amr_mr);
				setMensajeRespuesta(amr_mr);

				setDatosAuditoria(lcmr_mr);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_MENSAJE_RESPUESTA.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<MensajeRespuesta> findAllMensajeRespuesta()
	{
		Collection<MensajeRespuesta> lcmr_ta;

		lcmr_ta = null;

		try
		{
			lcmr_ta = ipr_parameterRemote.findAllMensajeRespuesta();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcmr_ta;
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
			MensajeRespuesta lmr_parametros;

			lmr_parametros = getMensajeRespuesta();

			if(lmr_parametros != null)
			{
				{
					String ls_codigoMensaje;
					ls_codigoMensaje     = lmr_parametros.getCodigoMensaje();

					lb_focus = validateStyles(
						    ":fMensajeRespuestaDetalle:codigoMensaje", lfc_context, ls_codigoMensaje, lb_focus
						);

					if(!StringUtils.isValidString(ls_codigoMensaje))
						throw new B2BException(ErrorKeys.ERROR_SIN_CODIGO_MENSAJE);
				}

				{
					String ls_textoMensaje;
					ls_textoMensaje     = lmr_parametros.getTextoMensaje();

					lb_focus = validateStyles(
						    ":fMensajeRespuestaDetalle:textoMensaje", lfc_context, ls_textoMensaje, lb_focus
						);

					if(!StringUtils.isValidString(ls_textoMensaje))
						throw new B2BException(ErrorKeys.ERROR_SIN_TEXTO_MENSAJE);
				}

				{
					String ls_activo;
					ls_activo     = lmr_parametros.getActivo();

					lb_focus = validateStyles(":fMensajeRespuestaDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarMensajeRespuesta(
				    lmr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setMensajeRespuesta(null);

				ls_result = NavegacionCommon.MENSAJE_RESPUESTA;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
