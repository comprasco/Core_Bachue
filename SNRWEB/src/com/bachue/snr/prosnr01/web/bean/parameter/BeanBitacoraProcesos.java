package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraProceso;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanBitacoraProcesos.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanBitacoraProcesos")
@SessionScoped
public class BeanBitacoraProcesos extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1175981189363634806L;

	/** Propiedad icbp datos bitacora. */
	private Collection<BitacoraProceso> icbp_datosBitacora;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos bitacora.
	 *
	 * @param ab_b asigna el valor a la propiedad datos bitacora
	 */
	public void setDatosBitacora(Collection<BitacoraProceso> ab_b)
	{
		icbp_datosBitacora = ab_b;
	}

	/**
	 * Retorna el valor de datos bitacora.
	 *
	 * @return el valor de datos bitacora
	 */
	public Collection<BitacoraProceso> getDatosBitacora()
	{
		return icbp_datosBitacora;
	}

	/**
	 * Modifica el valor de descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
	}

	/**
	 * Retorna el valor de descripcion.
	 *
	 * @return el valor de descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de fecha desde.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha desde
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna el valor de fecha desde.
	 *
	 * @return el valor de fecha desde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de fecha hasta.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha hasta
	 */
	public void setFechaHasta(Date ad_d)
	{
		this.id_fechaHasta = ad_d;
	}

	/**
	 * Retorna el valor de fecha hasta.
	 *
	 * @return el valor de fecha hasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setDatosBitacora(null);
		setFechaDesde(null);
		setFechaHasta(null);
		setDescripcion(null);
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		Date         ld_fechaDesde;
		Date         ld_fechaHasta;
		String       ls_descripcion;
		boolean      lb_focus;
		FacesContext lfc_context;
		lfc_context     = FacesContext.getCurrentInstance();

		ld_fechaDesde      = getFechaDesde();
		ld_fechaHasta      = getFechaHasta();
		ls_descripcion     = getDescripcion();
		lb_focus           = true;

		lb_focus     = validateStyles("fBitacoraProceso:idDescripcion", lfc_context, ls_descripcion, lb_focus);
		lb_focus     = validateStyles("fBitacoraProceso:idFechaDesde", lfc_context, ld_fechaDesde, lb_focus);
		lb_focus     = validateStyles(":fBitacoraProceso:idFechaHasta", lfc_context, ld_fechaHasta, lb_focus);

		try
		{
			if(!StringUtils.isValidString(ls_descripcion))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);

			if(ld_fechaDesde == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);

			if(ld_fechaHasta == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_HASTA);

			if((ld_fechaDesde.compareTo(ld_fechaHasta) > 0))
				throw new B2BException(ErrorKeys.FECHA_DESDE_SUPERIOR);

			if((ld_fechaDesde.compareTo(new Date()) > 0))
				throw new B2BException(ErrorKeys.FECHA_DESDE_SUPERIOR_ACTUAL);

			Collection<BitacoraProceso> lcbp_tmp;
			lcbp_tmp = ipr_parameterRemote.filtroBitacoraProceso(ls_descripcion, ld_fechaDesde, ld_fechaHasta, true);

			if(lcbp_tmp != null)
				setDatosBitacora(lcbp_tmp);
			else
			{
				setDatosBitacora(null);
				addMessage(MessagesKeys.CONSULTA_SIN_DATOS);
				PrimeFaces.current().ajax().update("fBitacoraProceso:idGrowl");
			}

			PrimeFaces.current().ajax().update("fBitacoraProceso:idTablaBitacora");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fBitacoraProceso:idGrowl");
		}
	}
}
