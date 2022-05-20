package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import com.bachue.snr.prosnr16.model.sdb.pgn.Origen;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanOrigen.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanOrigen")
@SessionScoped
public class BeanOrigen extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1493636997596531307L;

	/** Propiedad ico datos auditoria. */
	private Collection<Origen> ico_datosAuditoria;

	/** Propiedad io origen. */
	private Origen io_origen;

	/** Propiedad io parametros. */
	private Origen io_parametros;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Origen> getDatosAuditoria()
	{
		return ico_datosAuditoria;
	}

	/**
	 * @param aco_co asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Origen> aco_co)
	{
		ico_datosAuditoria = aco_co;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Origen getParametros()
	{
		if(io_parametros == null)
			io_parametros = new Origen();

		return io_parametros;
	}

	/**
	 * @param ao_o asigna el valor a la propiedad
	 */
	public void setParametros(Origen ao_o)
	{
		io_parametros = ao_o;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Origen getOrigen()
	{
		return io_origen;
	}

	/**
	 * @param ao_o asigna el valor a la propiedad
	 */
	public void setOrigen(Origen ao_o)
	{
		io_origen = ao_o;
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
	 * Origen
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setOrigen(new Origen());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarOrigen");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ORIGEN
	 * @return Collection Origen
	 */
	public Collection<Origen> findOrigen()
	{
		Collection<Origen> lco_origen;

		lco_origen = null;

		try
		{
			lco_origen = ipr_parameterRemote.findAllOrigen();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lco_origen;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_ORIGEN
	 * @param ao_o
	 * @throws B2BException
	 */
	public void consultaDetallada(Origen ao_o)
	    throws B2BException
	{
		if(ao_o != null)
		{
			Origen lo_origen;
			lo_origen = ipr_parameterRemote.findOrigenById(ao_o);

			if(lo_origen != null)
			{
				Collection<Origen> lco_co;

				lco_co = new ArrayList<Origen>();

				lco_co.add(lo_origen);
				setOrigen(lo_origen);

				setDatosAuditoria(lco_co);
			}

			setInsertar(false);
		}
	}

	/**
	 * Método para salvar la inserción o actualización
	 * @return
	 */
	public String salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_resultado;

		lb_focus         = true;
		lfc_context      = FacesContext.getCurrentInstance();
		ls_resultado     = null;

		try
		{
			Origen lo_parametros;

			lo_parametros = getOrigen();

			if(lo_parametros != null)
			{
				{
					String ls_nombre;
					ls_nombre     = lo_parametros.getNombre();

					lb_focus = validateStyles(":fOrigenDetalle:nombre", lfc_context, ls_nombre, lb_focus);

					if(!StringUtils.isValidString(ls_nombre))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					String ls_activo;
					ls_activo     = lo_parametros.getActivo();

					lb_focus = validateStyles(":fOrigenDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarOrigen(
				    lo_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				ls_resultado = NavegacionCommon.ORIGEN;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fOrigenDetalle:globalMsg");
		}

		return ls_resultado;
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
