package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;


/**
 * Clase para el manejo de la capa web para Canal Origen Servicio.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanCanalOrigenServicio")
@SessionScoped
public class BeanCanalOrigenServicio extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8203836365082457280L;

	/** Propiedad icos canal origen servicio. */
	private CanalOrigenServicio icos_canalOrigenServicio;

	/** Propiedad icos parametros. */
	private CanalOrigenServicio icos_parametros;

	/** Propiedad iccos datos auditoria. */
	private Collection<CanalOrigenServicio> iccos_datosAuditoria;

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
	 * Modifica el valor de canal origen servicio.
	 *
	 * @param acos_mr asigna el valor a la propiedad canal origen servicio
	 */
	public void setCanalOrigenServicio(CanalOrigenServicio acos_mr)
	{
		icos_canalOrigenServicio = acos_mr;
	}

	/**
	 * Retorna el valor de canal origen servicio.
	 *
	 * @return el valor de canal origen servicio
	 */
	public CanalOrigenServicio getCanalOrigenServicio()
	{
		return icos_canalOrigenServicio;
	}

	/**
	 * Modifica el valor de datos auditoria.
	 *
	 * @param accos_ccos asigna el valor a la propiedad datos auditoria
	 */
	public void setDatosAuditoria(Collection<CanalOrigenServicio> accos_ccos)
	{
		iccos_datosAuditoria = accos_ccos;
	}

	/**
	 * Retorna el valor de datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<CanalOrigenServicio> getDatosAuditoria()
	{
		return iccos_datosAuditoria;
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
	 * @param acos_param asigna el valor a la propiedad parametros
	 */
	public void setParametros(CanalOrigenServicio acos_param)
	{
		icos_parametros = acos_param;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public CanalOrigenServicio getParametros()
	{
		if(icos_parametros == null)
			icos_parametros = new CanalOrigenServicio();

		return icos_parametros;
	}

/**
 * Método para cambiar estado para saber si se desea insertar un nuevo
 * Canal Origen Servicio.
 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCanalOrigenServicio(new CanalOrigenServicio());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCanalOrigenServicio");

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
 * @param amr_mr correspondiente al valor del tipo de objeto CanalOrigenServicio
 * @throws B2BException Señala que se ha producido una excepción
 */
	public void consultaDetallada(CanalOrigenServicio amr_mr)
	    throws B2BException
	{
		if(amr_mr != null)
		{
			amr_mr = ipr_parameterRemote.findCanalOrigenServicioById(amr_mr.getIdCanalOrigenServicio());

			if(amr_mr != null)
			{
				Collection<CanalOrigenServicio> lcmr_mr;
				lcmr_mr = new ArrayList<CanalOrigenServicio>();

				lcmr_mr.add(amr_mr);
				setCanalOrigenServicio(amr_mr);

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
	public Collection<CanalOrigenServicio> findAllCanalOrigenServicio()
	{
		Collection<CanalOrigenServicio> lcmr_mr;
		lcmr_mr = null;

		try
		{
			lcmr_mr = ipr_parameterRemote.findAllCanalOrigenServicio();
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
			CanalOrigenServicio lmr_parametros;

			lmr_parametros = getCanalOrigenServicio();

			if(lmr_parametros != null)
			{

				{
					String ls_nombreCanalOrigenServicio;
					ls_nombreCanalOrigenServicio     = lmr_parametros.getNombreCanalOrigenServicio();

					lb_focus = validateStyles(
						    ":fCanalOrigenServicioDetalle:nombreCanalOrigenServicio", lfc_context,
						    ls_nombreCanalOrigenServicio, lb_focus
						);

					if(!StringUtils.isValidString(ls_nombreCanalOrigenServicio))
						throw new B2BException(ErrorKeys.ERROR_SIN_NOMBRE_MEDIO_RECAUDO);
				}

				{
					String ls_activo;
					ls_activo     = lmr_parametros.getActivo();

					lb_focus = validateStyles(
						    ":fCanalOrigenServicioDetalle:idActivo", lfc_context, ls_activo, lb_focus
						);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCanalOrigenServicio(
				    lmr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);
				setCanalOrigenServicio(null);

				ls_result = NavegacionCommon.CANAL_ORIGEN_SERVICIO;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCanalOrigenServicioDetalle");
		}

		return ls_result;
	}
}
