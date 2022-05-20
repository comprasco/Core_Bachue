package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

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
 * Clase para el manejo de la capa web para Estado Predio.
 *
 * @author Luis Chacón
 */
@ManagedBean(name = "beanEstadoPredio")
@SessionScoped
public class BeanEstadoPredio extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1067649096499669078L;

	/** Propiedad icep datos auditoria. */
	private Collection<EstadoPredio> icep_datosAuditoria;

	/** Propiedad iep estado predio. */
	private EstadoPredio iep_estadoPredio;

	/** Propiedad iep parametros. */
	private EstadoPredio iep_parametros;

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
	 * @param acep_cep asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<EstadoPredio> acep_cep)
	{
		icep_datosAuditoria = acep_cep;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<EstadoPredio> getDatosAuditoria()
	{
		return icep_datosAuditoria;
	}

	/**
	 * Modifica el valor de estado predio.
	 *
	 * @param cep_cep asigna el valor a la propiedad estado predio
	 */
	public void setEstadoPredio(EstadoPredio cep_cep)
	{
		iep_estadoPredio = cep_cep;
	}

	/**
	 * Retorna el valor de estado predio.
	 *
	 * @return el valor de estado predio
	 */
	public EstadoPredio getEstadoPredio()
	{
		return iep_estadoPredio;
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
	 * @param aep_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(EstadoPredio aep_param)
	{
		iep_parametros = aep_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EstadoPredio getParametros()
	{
		if(iep_parametros == null)
			iep_parametros = new EstadoPredio();

		return iep_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Estado Predio.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setEstadoPredio(new EstadoPredio());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarEstadoPredio");

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
	 * Método para consultar en la base de datos la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @param aep_ep correspondiente al valor del tipo de objeto EstadoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaDetallada(EstadoPredio aep_ep)
	    throws B2BException
	{
		if(aep_ep != null)
		{
			EstadoPredio lep_dato;

			lep_dato     = null;

			lep_dato = ipr_parameterRemote.findEstadoPredioById(aep_ep);

			if(lep_dato != null)
			{
				Collection<EstadoPredio> lcep_ep;
				lcep_ep = new ArrayList<EstadoPredio>();

				lcep_ep.add(lep_dato);
				setEstadoPredio(lep_dato);

				setDatosAuditoria(lcep_ep);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoPredio> findAllEstadoPredio()
	{
		Collection<EstadoPredio> lcep_en;
		lcep_en = null;

		try
		{
			lcep_en = ipr_parameterRemote.findAllEstadoPredio();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcep_en;
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
			EstadoPredio lep_parametros;

			lep_parametros = getEstadoPredio();

			if(lep_parametros != null)
			{
				{
					String ls_idEstadoPredio;
					ls_idEstadoPredio     = lep_parametros.getIdEstadoPredio();

					lb_focus = validateStyles(
						    ":fEstadoPredioDetalle:idEstadoPredio", lfc_context, ls_idEstadoPredio, lb_focus
						);

					if(!StringUtils.isValidString(ls_idEstadoPredio))
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ESTADO_PREDIO);
				}

				{
					String ls_nombre;
					ls_nombre     = lep_parametros.getNombre();

					lb_focus = validateStyles(":fEstadoPredioDetalle:idNombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = lep_parametros.getActivo();

					lb_focus = validateStyles(":fEstadoPredioDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarEstadoPredio(
				    lep_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				
				getBeanReference().setEstadoPredio(null);

				setParametros(null);
				setEstadoPredio(null);

				ls_result = NavegacionCommon.ESTADO_PREDIO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_result;
	}
}
