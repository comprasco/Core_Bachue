package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr16.model.sdb.pgn.Canal;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanCanal.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCanal")
@SessionScoped
public class BeanCanal extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7115330414382751180L;

	/** Propiedad ic canal. */
	private Canal ic_canal;

	/** Propiedad ic parametros. */
	private Canal ic_parametros;

	/** Propiedad icc datos auditoria. */
	private Collection<Canal> icc_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Canal> getDatosAuditoria()
	{
		return icc_datosAuditoria;
	}

	/**
	 * @param acc_cc asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Canal> acc_cc)
	{
		icc_datosAuditoria = acc_cc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Canal getParametros()
	{
		if(ic_parametros == null)
			ic_parametros = new Canal();

		return ic_parametros;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setParametros(Canal ac_c)
	{
		ic_parametros = ac_c;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Canal getCanal()
	{
		return ic_canal;
	}

	/**
	 * @param ac_c asigna el valor a la propiedad
	 */
	public void setCanal(Canal ac_c)
	{
		ic_canal = ac_c;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar un nuevo
	 * Canal
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCanal(new Canal());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCanal");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CANAL
	 * @return Collection Canal
	 */
	public Collection<Canal> findCanal()
	{
		Collection<Canal> lcc_canal;

		lcc_canal = null;

		try
		{
			lcc_canal = ipr_parameterRemote.findAllCanal(false);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_canal;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_CANAL
	 * @param ac_c
	 * @throws B2BException
	 */
	public void consultaDetallada(Canal ac_c)
	    throws B2BException
	{
		if(ac_c != null)
		{
			Canal lc_canal;
			lc_canal = ipr_parameterRemote.findCanalById(ac_c);

			if(lc_canal != null)
			{
				Collection<Canal> lcc_cc;

				lcc_cc = new ArrayList<Canal>();

				lcc_cc.add(lc_canal);
				setCanal(lc_canal);

				setDatosAuditoria(lcc_cc);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 * @return
	 */
	public void salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			Canal lc_parametros;

			lc_parametros = getCanal();

			if(lc_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = lc_parametros.getNombre();

					lb_focus = validateStyles(":fCanalDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = lc_parametros.getActivo();

					lb_focus = validateStyles(":fCanalDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCanal(
				    lc_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fCanalDetalle:globalMsg");
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCanalDetalle:globalMsg");
		}
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	@Override
	public String getApplication()
	{
		return null;
	}
}
