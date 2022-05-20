package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanEstadoActividad.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanEstadoActividad")
@SessionScoped
public class BeanEstadoActividad extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2723208082911724069L;

	/** Propiedad id parametros. */
	private EstadoActividad id_parametros;

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
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(EstadoActividad ac_c)
	{
		id_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public EstadoActividad getParametros()
	{
		if(id_parametros == null)
			id_parametros = new EstadoActividad();

		return id_parametros;
	}

	/**
	 * Metodo para  cambiar estado con el fin de saber si se desea insertar un proceso automatico.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));

		setParametros(null);
	}

	/**
	 * Metodo para traer los registros del proceso automatico que coincida con un id_procesoautomaticos
	 * especifico.
	 */
	public void consultaDetallada()
	{
		try
		{
			EstadoActividad ld_parametros;
			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				String ls_idEstadoActividad;

				ls_idEstadoActividad = FacesUtils.getStringFacesParameter("idEstadoActividad");

				if(StringUtils.isValidString(ls_idEstadoActividad))
				{
					ld_parametros.setIdEstadoActividad(ls_idEstadoActividad);
					setParametros(ipr_parameterRemote.findEstadoActividadById(ld_parametros));
					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Metodo para traer todos los registros de la tabla SDB_PGN_ESTADO_ACTIVIDAD.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<EstadoActividad> findAllEstadoActividad()
	{
		Collection<EstadoActividad> lcd_constantes;
		lcd_constantes = null;

		try
		{
			lcd_constantes = ipr_parameterRemote.findAllEstadoActividad(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_constantes;
	}

	/**
	 * Metodo para salvar un festivo nacional ya sea para modificar o insertar registros.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String       ls_result;
		FacesContext lfc_context;
		boolean      lb_focus;

		ls_result       = null;
		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			EstadoActividad ld_parametros;

			ld_parametros = getParametros();

			if(ld_parametros != null)
			{
				{
					String ls_idEstadoActividad;

					ls_idEstadoActividad     = ld_parametros.getIdEstadoActividad();
					lb_focus                 = validateStyles(
						    ":FEstadoActividadDetalle:idEstadoActividad", lfc_context, ls_idEstadoActividad, lb_focus
						);

					if(!StringUtils.isValidString(ls_idEstadoActividad))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ESTADO_ACTIVIDAD);
				}

				{
					String ls_descripcion;

					ls_descripcion     = ld_parametros.getDescripcion();
					lb_focus           = validateStyles(
						    ":FEstadoActividadDetalle:idDescripcion", lfc_context, ls_descripcion, lb_focus
						);

					if(!StringUtils.isValidString(ls_descripcion))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					String ls_estado;

					ls_estado     = ld_parametros.getEstado();
					lb_focus      = validateStyles(
						    ":FEstadoActividadDetalle:idEstado", lfc_context, ls_estado, lb_focus
						);

					if(!StringUtils.isValidString(ls_estado))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ld_parametros.setIdUsuarioCreacion(getUserId());
				ld_parametros.setIdUsuarioModificacion(getUserId());

				ipr_parameterRemote.salvarEstadoActividad(
				    ld_parametros, isInsertar(), getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);
			}

			{
				BeanReference lbr_beanReference;
				lbr_beanReference = getBeanReference();
				lbr_beanReference.setEstadoActividadActivo(null);
				lbr_beanReference.setEstadoActividad(null);
			}

			setParametros(null);

			ls_result = NavegacionCommon.ESTADO_ACTIVIDAD;

			if(isInsertar())
			{
				addMessage(MessagesKeys.INSERCION_EXITOSA);
				PrimeFaces.current().ajax().update("FEstadoActividad");
				PrimeFaces.current().ajax().update("FEstadoActividadDetalle");
			}
			else
			{
				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("FEstadoActividad");
				PrimeFaces.current().ajax().update("FEstadoActividadDetalle");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("FEstadoActividadDetalle");
		}

		return ls_result;
	}
}
