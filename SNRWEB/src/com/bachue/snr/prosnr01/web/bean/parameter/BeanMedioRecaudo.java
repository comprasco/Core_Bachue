package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.MedioRecaudo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para Medio Recaudo.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanMedioRecaudo")
@SessionScoped
public class BeanMedioRecaudo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1260560151139597933L;

	/** Propiedad icmr datos auditoria. */
	private Collection<MedioRecaudo> icmr_datosAuditoria;

	/** Propiedad imr medio recaudo. */
	private MedioRecaudo imr_medioRecaudo;

	/** Propiedad imr parametros. */
	private MedioRecaudo imr_parametros;

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
	public void setDatosAuditoria(Collection<MedioRecaudo> acmr_cmr)
	{
		icmr_datosAuditoria = acmr_cmr;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<MedioRecaudo> getDatosAuditoria()
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
	 * Modifica el valor de medio recaudo.
	 *
	 * @param amr_mr asigna el valor a la propiedad medio recaudo
	 */
	public void setMedioRecaudo(MedioRecaudo amr_mr)
	{
		imr_medioRecaudo = amr_mr;
	}

	/**
	 * Retorna el valor de medio recaudo.
	 *
	 * @return el valor de medio recaudo
	 */
	public MedioRecaudo getMedioRecaudo()
	{
		return imr_medioRecaudo;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param amr_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(MedioRecaudo amr_param)
	{
		imr_parametros = amr_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public MedioRecaudo getParametros()
	{
		if(imr_parametros == null)
			imr_parametros = new MedioRecaudo();

		return imr_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Medio Recaudo.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setMedioRecaudo(new MedioRecaudo());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarMedioRecaudo");

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
	 * Método para consultar en la base de datos la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @param amr_mr correspondiente al valor del tipo de objeto MedioRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(MedioRecaudo amr_mr)
	    throws B2BException
	{
		if(amr_mr != null)
		{
			amr_mr = ipr_parameterRemote.findMedioRecaudoById(amr_mr.getIdMedioRecaudo());

			if(amr_mr != null)
			{
				Collection<MedioRecaudo> lcmr_mr;

				lcmr_mr = new ArrayList<MedioRecaudo>();

				lcmr_mr.add(amr_mr);
				setMedioRecaudo(amr_mr);

				setDatosAuditoria(lcmr_mr);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_MEDIO_RECAUDO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<MedioRecaudo> findAllMedioRecaudo()
	{
		Collection<MedioRecaudo> lcmr_mr;
		lcmr_mr = null;

		try
		{
			lcmr_mr = ipr_parameterRemote.findAllMedioRecaudo();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcmr_mr;
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
			MedioRecaudo lmr_parametros;

			lmr_parametros = getMedioRecaudo();

			if(lmr_parametros != null)
			{
				if(!isInsertar())
				{
					String ls_idMedioRecaudo;
					ls_idMedioRecaudo     = lmr_parametros.getIdMedioRecaudo();

					lb_focus = validateStyles(
						    ":fMedioRecaudoDetalle:idMedioRecaudo", lfc_context, ls_idMedioRecaudo, lb_focus
						);

					if(!StringUtils.isValidString(ls_idMedioRecaudo))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_MEDIO_RECAUDO);
				}

				{
					String ls_nombreMedioRecaudo;
					ls_nombreMedioRecaudo     = lmr_parametros.getNombreMedioRecaudo();

					lb_focus = validateStyles(
						    ":fMedioRecaudoDetalle:nombreMedioRecaudo", lfc_context, ls_nombreMedioRecaudo, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreMedioRecaudo))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_MEDIO_RECAUDO);
				}

				{
					String ls_activo;
					ls_activo     = lmr_parametros.getActivo();

					lb_focus = validateStyles(":fMedioRecaudoDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarMedioRecaudo(
				    lmr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setMedioRecaudo(null);

				ls_result = NavegacionCommon.MEDIO_RECAUDO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
