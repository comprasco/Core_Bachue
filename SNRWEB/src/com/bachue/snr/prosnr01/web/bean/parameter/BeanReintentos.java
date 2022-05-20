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

import com.bachue.snr.prosnr16.model.sdb.pgn.Canal;
import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de
 * BeanReintentos.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanReintentos")
@SessionScoped
public class BeanReintentos extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5001469320425328801L;

	/** Propiedad icr datos auditoria. */
	private Collection<Reintentos> icr_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ir parametros. */
	private Reintentos ir_parametros;

	/** Propiedad ir reintentos. */
	private Reintentos ir_reintentos;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Reintentos> getDatosAuditoria()
	{
		return icr_datosAuditoria;
	}

	/**
	 * @param acr_cr asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<Reintentos> acr_cr)
	{
		icr_datosAuditoria = acr_cr;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Reintentos getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new Reintentos();

		return ir_parametros;
	}

	/**
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setParametros(Reintentos ar_r)
	{
		ir_parametros = ar_r;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Reintentos getReintentos()
	{
		return ir_reintentos;
	}

	/**
	 * @param ar_r asigna el valor a la propiedad
	 */
	public void setReintentos(Reintentos ar_r)
	{
		ir_reintentos = ar_r;
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
	 * Reintentos
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setReintentos(new Reintentos());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarReintentos");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_REINTENTOS
	 * @return Collection Reintentos
	 */
	public Collection<Reintentos> findReintentos()
	{
		Collection<Reintentos> lcr_reintentos;

		lcr_reintentos = null;

		try
		{
			lcr_reintentos = ipr_parameterRemote.findAllReintentos();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_reintentos;
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_REINTENTOS
	 * @param ar_r
	 * @throws B2BException
	 */
	public void consultaDetallada(Reintentos ar_r)
	    throws B2BException
	{
		if(ar_r != null)
		{
			Reintentos lr_reintentos;
			lr_reintentos = ipr_parameterRemote.findReintentosById(ar_r);

			if(lr_reintentos != null)
			{
				Collection<Reintentos> lcr_cr;

				lcr_cr = new ArrayList<Reintentos>();

				lcr_cr.add(lr_reintentos);
				setReintentos(lr_reintentos);

				setDatosAuditoria(lcr_cr);
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
		String       ls_result;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_result       = null;

		try
		{
			Reintentos lr_parametros;

			lr_parametros = getReintentos();

			if(lr_parametros != null)
			{
				{
					String ls_idCanal;
					ls_idCanal     = lr_parametros.getIdCanal();

					lb_focus = validateStyles(":fReintentosDetalle:idCanal", lfc_context, ls_idCanal, lb_focus);

					if(!StringUtils.isValidString(ls_idCanal))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CANAL);
				}

				{
					String ls_activo;
					ls_activo     = lr_parametros.getActivo();

					lb_focus = validateStyles(":fReintentosDetalle:idActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}
				
				{
					String ls_maximo;
					
					ls_maximo = lr_parametros.getNumeroMaximoIntentos();


					lb_focus = validateStyles(":fReintentosDetalle:numeroMaximoIntentos", lfc_context, ls_maximo, lb_focus);

					if(!StringUtils.isNumeric(lr_parametros.getNumeroMaximoIntentos()))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO);
				}


				ipr_parameterRemote.salvarReintentos(
				    lr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setParametros(null);

				addMessage(MessagesKeys.PROCESO_COMPLETADO);
				PrimeFaces.current().ajax().update("fReintentosDetalle:globalMsg");
				
				ls_result = NavegacionCommon.REINTENTOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fReintentosDetalle:globalMsg");
		}
		
		return ls_result; 
	}

	/**
	 * Metodo para traer los procesos de la base de datos
	 *
	 * @return Colección de Canal resultante de la consulta
	 */
	public Collection<Canal> cargarCanalOrigen()
	{
		Collection<Canal> lc_canal;

		lc_canal = new ArrayList<Canal>();

		try
		{
			lc_canal = ipr_parameterRemote.findAllCanal(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lc_canal;
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
