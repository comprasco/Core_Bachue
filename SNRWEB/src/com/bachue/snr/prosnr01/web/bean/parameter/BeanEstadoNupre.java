package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoNupre;

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
 * Clase para el manejo de la capa web para Estado Nupre.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanEstadoNupre")
@SessionScoped
public class BeanEstadoNupre extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6573575237467799977L;

	/** Propiedad icen datos auditoria. */
	private Collection<EstadoNupre> icen_datosAuditoria;

	/** Propiedad ien estado nupre. */
	private EstadoNupre ien_estadoNupre;

	/** Propiedad ien parametros. */
	private EstadoNupre ien_parametros;

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
	 * @param acen_cen asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<EstadoNupre> acen_cen)
	{
		icen_datosAuditoria = acen_cen;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<EstadoNupre> getDatosAuditoria()
	{
		return icen_datosAuditoria;
	}

	/**
	 * Modifica el valor de estado nupre.
	 *
	 * @param aen_en asigna el valor a la propiedad estado nupre
	 */
	public void setEstadoNupre(EstadoNupre aen_en)
	{
		ien_estadoNupre = aen_en;
	}

	/**
	 * Retorna el valor de estado nupre.
	 *
	 * @return el valor de estado nupre
	 */
	public EstadoNupre getEstadoNupre()
	{
		return ien_estadoNupre;
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
	 * @param aen_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(EstadoNupre aen_param)
	{
		ien_parametros = aen_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EstadoNupre getParametros()
	{
		if(ien_parametros == null)
			ien_parametros = new EstadoNupre();

		return ien_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Estado Nupre.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEstadoNupre(new EstadoNupre());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEstadoNupre");

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
	 * Método para consultar en la base de datos la tabla SDB_PNG_ESTADO_NUPRE.
	 *
	 * @param aen_en correspondiente al valor del tipo de objeto EstadoNupre
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(EstadoNupre aen_en)
	    throws B2BException
	{
		if(aen_en != null)
		{
			EstadoNupre len_dato;

			len_dato     = null;

			len_dato = ipr_parameterRemote.findEstadoNupreById(aen_en);

			if(len_dato != null)
			{
				Collection<EstadoNupre> lcen_en;
				lcen_en = new ArrayList<EstadoNupre>();

				lcen_en.add(len_dato);
				setEstadoNupre(len_dato);

				setDatosAuditoria(lcen_en);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_ESTADO_NUPRE.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoNupre> findAllEstadoNupre()
	{
		Collection<EstadoNupre> lcen_en;
		lcen_en = null;

		try
		{
			lcen_en = ipr_parameterRemote.findAllEstadoNupre();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcen_en;
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
			EstadoNupre len_parametros;

			len_parametros = getEstadoNupre();

			if(len_parametros != null)
			{
				{
					String ls_idEstadoNupre;
					ls_idEstadoNupre     = len_parametros.getIdEstadoNupre();

					lb_focus = validateStyles(
						    ":fEstadoNupreDetalle:idEstadoNupre", lfc_context, ls_idEstadoNupre, lb_focus
						);

					if(!StringUtils.isValidString(ls_idEstadoNupre))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ESTADO_NUPRE);
				}

				{
					String ls_nombre;
					ls_nombre     = len_parametros.getNombre();

					lb_focus = validateStyles(":fEstadoNupreDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = len_parametros.getActivo();

					lb_focus = validateStyles(":fEstadoNupreDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEstadoNupre(
				    len_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setEstadoNupre(null);

				ls_result = NavegacionCommon.ESTADO_NUPRE;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
