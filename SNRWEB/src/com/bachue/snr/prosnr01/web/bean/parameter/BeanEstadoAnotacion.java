package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;

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
 * Clase para el manejo de la capa web para Grupo Naturaleza Juridica.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanEstadoAnotacion")
@SessionScoped
public class BeanEstadoAnotacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2487285828270934976L;

	/** Propiedad iea datos auditoria. */
	private Collection<EstadoAnotacion> iea_datosAuditoria;

	/** Propiedad iea estado anotacion. */
	private EstadoAnotacion iea_estadoAnotacion;

	/** Propiedad iea parametros. */
	private EstadoAnotacion iea_parametros;

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
	 * @param aea_ea asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<EstadoAnotacion> aea_ea)
	{
		iea_datosAuditoria = aea_ea;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<EstadoAnotacion> getDatosAuditoria()
	{
		return iea_datosAuditoria;
	}

	/**
	 * Modifica el valor de estado anotacion.
	 *
	 * @param aea_ea asigna el valor a la propiedad estado anotacion
	 */
	public void setEstadoAnotacion(EstadoAnotacion aea_ea)
	{
		iea_estadoAnotacion = aea_ea;
	}

	/**
	 * Retorna el valor de estado anotacion.
	 *
	 * @return el valor de estado anotacion
	 */
	public EstadoAnotacion getEstadoAnotacion()
	{
		return iea_estadoAnotacion;
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
	 * @param aea_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(EstadoAnotacion aea_param)
	{
		iea_parametros = aea_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EstadoAnotacion getParametros()
	{
		if(iea_parametros == null)
			iea_parametros = new EstadoAnotacion();

		return iea_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Estado anotación.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEstadoAnotacion(new EstadoAnotacion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEstadoAnotacion");

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
	 * Método para consultar en la base de datos la tabla SDB_ACC_ANOTACION_PREDIO.
	 *
	 * @param aea_ea correspondiente al valor del tipo de objeto EstadoAnotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(EstadoAnotacion aea_ea)
	    throws B2BException
	{
		if(aea_ea != null)
		{
			EstadoAnotacion lea_dato;

			lea_dato     = null;

			lea_dato = ipr_parameterRemote.findEstadoAnotacionById(aea_ea);

			if(lea_dato != null)
			{
				Collection<EstadoAnotacion> lcea_ea;
				lcea_ea = new ArrayList<EstadoAnotacion>();

				lcea_ea.add(lea_dato);
				setEstadoAnotacion(lea_dato);

				setDatosAuditoria(lcea_ea);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_ACC_ANOTACION_PREDIO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoAnotacion> findAllEstadoAnotacion()
	{
		Collection<EstadoAnotacion> lcea_ea;
		lcea_ea = null;

		try
		{
			lcea_ea = ipr_parameterRemote.findAllEstadoAnotacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcea_ea;
	}

	/**
	 * Método para salvar la inserción o actualización de la tabla SDB_ACC_ANOTACION_PREDIO.
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
			EstadoAnotacion lea_parametros;

			lea_parametros = getEstadoAnotacion();

			if(lea_parametros != null)
			{
				{
					String ls_idEstadoAnotacion;
					ls_idEstadoAnotacion     = lea_parametros.getIdEstadoAnotacion();

					lb_focus = validateStyles(
						    ":fEstadoAnotacionDetalle:idEstadoAnotacion", lfc_context, ls_idEstadoAnotacion, lb_focus
						);

					if(!StringUtils.isValidString(ls_idEstadoAnotacion))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ESTADO_ANOTACION);
				}

				{
					String ls_descripcion;
					ls_descripcion     = lea_parametros.getNombre();

					lb_focus = validateStyles(
						    ":fEstadoAnotacionDetalle:idNombre", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = lea_parametros.getActivo();

					lb_focus = validateStyles(":fEstadoAnotacionDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEstadoAnotacion(
				    lea_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setEstadoAnotacion(null);

				setParametros(null);
				setEstadoAnotacion(null);

				ls_result = NavegacionCommon.ESTADO_ANOTACION;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
