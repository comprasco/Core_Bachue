package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.UnidadTiempoVencimiento;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanUnidadTiempoVencimiento.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanUnidadTiempoVencimiento")
@SessionScoped
public class BeanUnidadTiempoVencimiento extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 25669969513869347L;

	/** Propiedad icutv datos auditoria. */
	private Collection<UnidadTiempoVencimiento> icutv_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad icutv parametros. */
	private UnidadTiempoVencimiento icutv_parametros;

	/** Propiedad iutv unidad tiempo vencimiento. */
	private UnidadTiempoVencimiento iutv_unidadTiempoVencimiento;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<UnidadTiempoVencimiento> datosAuditoria)
	{
		icutv_datosAuditoria = datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<UnidadTiempoVencimiento> getDatosAuditoria()
	{
		return icutv_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param parametros asigna el valor a la propiedad
	 */
	public void setParametros(UnidadTiempoVencimiento parametros)
	{
		icutv_parametros = parametros;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public UnidadTiempoVencimiento getParametros()
	{
		if(icutv_parametros == null)
			icutv_parametros = new UnidadTiempoVencimiento();

		return icutv_parametros;
	}

	/**
	 * @param autv_utv asigna el valor a la propiedad
	 */
	public void setUnidadTiempoVencimiento(UnidadTiempoVencimiento autv_utv)
	{
		iutv_unidadTiempoVencimiento = autv_utv;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public UnidadTiempoVencimiento getUnidadTiempoVencimiento()
	{
		return iutv_unidadTiempoVencimiento;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Unidad de Tiempo Vencimiento
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setUnidadTiempoVencimiento(new UnidadTiempoVencimiento());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarUnidadTiempoVencimiento");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
	}

	/**
	 * Método para consultar en la base de datos la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO
	 * @param autv_utv
	 * @throws B2BException
	 */
	public void consultaDetallada(UnidadTiempoVencimiento autv_utv)
	    throws B2BException
	{
		if(autv_utv != null)
		{
			UnidadTiempoVencimiento lcutv_datos;
			lcutv_datos = ipr_parameterRemote.findUnidadTiempoVencimientoById(autv_utv);

			if(lcutv_datos != null)
			{
				Collection<UnidadTiempoVencimiento> lccutv_ccutv;
				lccutv_ccutv = new ArrayList<UnidadTiempoVencimiento>();

				lccutv_ccutv.add(lcutv_datos);
				setUnidadTiempoVencimiento(lcutv_datos);

				setDatosAuditoria(lccutv_ccutv);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_UNIDAD_TIEMPO_VENCIMIENTO
	 * @return
	 */
	public Collection<UnidadTiempoVencimiento> findUnidadTiempoVencimiento()
	{
		Collection<UnidadTiempoVencimiento> lcutv_constantes;
		lcutv_constantes = null;

		try
		{
			lcutv_constantes = ipr_parameterRemote.findAllUnidadTiempoVencimiento();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcutv_constantes;
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
			UnidadTiempoVencimiento lutv_parametros;

			lutv_parametros = getUnidadTiempoVencimiento();

			{
				String ls_nombre;
				ls_nombre     = lutv_parametros.getIdUnidadTiempo();

				lb_focus = validateStyles(
					    ":fUnidadTiempoVencimientoDetalle:idUnidadTiempo", lfc_context, ls_nombre, lb_focus
					);

				if(!StringUtils.isValidString(ls_nombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_UNIDAD_TIEMPO_VENCIMIENTO);
			}

			{
				String ls_descripcion;
				ls_descripcion     = lutv_parametros.getDescripcion();

				lb_focus = validateStyles(
					    ":fUnidadTiempoVencimientoDetalle:descripcion", lfc_context, ls_descripcion, lb_focus
					);

				if(!StringUtils.isValidString(ls_descripcion))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
			}

			ipr_parameterRemote.salvarUnidadTiempoVencimiento(
			    lutv_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("fUnidadTiempoVencimientoDetalle:globalMsg");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fUnidadTiempoVencimientoDetalle:globalMsg");
		}
	}
}
