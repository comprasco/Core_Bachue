package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoRegistro;

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
 * Clase para el manejo de la capa web para Estado registro.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanEstadoRegistro")
@SessionScoped
public class BeanEstadoRegistro extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5196177705069986029L;

	/** Propiedad icer datos auditoria. */
	private Collection<EstadoRegistro> icer_datosAuditoria;

	/** Propiedad ier estado registro. */
	private EstadoRegistro ier_estadoRegistro;

	/** Propiedad ier parametros. */
	private EstadoRegistro ier_parametros;

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
	 * @param acer_cer asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<EstadoRegistro> acer_cer)
	{
		icer_datosAuditoria = acer_cer;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<EstadoRegistro> getDatosAuditoria()
	{
		return icer_datosAuditoria;
	}

	/**
	 * Modifica el valor de estado registro.
	 *
	 * @param aer_er asigna el valor a la propiedad estado registro
	 */
	public void setEstadoRegistro(EstadoRegistro aer_er)
	{
		ier_estadoRegistro = aer_er;
	}

	/**
	 * Retorna el valor de estado registro.
	 *
	 * @return el valor de estado registro
	 */
	public EstadoRegistro getEstadoRegistro()
	{
		return ier_estadoRegistro;
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
	 * @param aer_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(EstadoRegistro aer_param)
	{
		ier_parametros = aer_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EstadoRegistro getParametros()
	{
		if(ier_parametros == null)
			ier_parametros = new EstadoRegistro();

		return ier_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Estado Registro.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEstadoRegistro(new EstadoRegistro());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEstadoRegistro");

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
	 * Método para consultar en la base de datos la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @param aer_er correspondiente al valor del tipo de objeto EstadoRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(EstadoRegistro aer_er)
	    throws B2BException
	{
		if(aer_er != null)
		{
			EstadoRegistro ler_dato;

			ler_dato     = null;

			ler_dato = ipr_parameterRemote.findEstadoRegistroById(aer_er);

			if(ler_dato != null)
			{
				Collection<EstadoRegistro> lcta_ta;
				lcta_ta = new ArrayList<EstadoRegistro>();

				lcta_ta.add(ler_dato);
				setEstadoRegistro(ler_dato);

				setDatosAuditoria(lcta_ta);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_ESTADO_REGISTRO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoRegistro> findAllEstadoRegistro()
	{
		Collection<EstadoRegistro> lcer_er;
		lcer_er = null;

		try
		{
			lcer_er = ipr_parameterRemote.findAllEstadoRegistro();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcer_er;
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
			EstadoRegistro ler_parametros;

			ler_parametros = getEstadoRegistro();

			if(ler_parametros != null)
			{
				{
					String ls_idEstadoRegistro;
					ls_idEstadoRegistro     = ler_parametros.getIdEstadoRegistro();

					lb_focus = validateStyles(
						    ":fEstadoRegistroDetalle:idEstadoRegistro", lfc_context, ls_idEstadoRegistro, lb_focus
						);

					if(!StringUtils.isValidString(ls_idEstadoRegistro))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ESTADO_REGISTRO);
				}

				{
					String ls_descripcion;
					ls_descripcion     = ler_parametros.getDescripcion();

					lb_focus = validateStyles(
						    ":fEstadoRegistroDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_activo;
					ls_activo     = ler_parametros.getActivo();

					lb_focus = validateStyles(":fEstadoRegistroDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEstadoRegistro(
				    ler_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setEstadoRegistro(null);

				ls_result = NavegacionCommon.ESTADO_REGISTRO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
