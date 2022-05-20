package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;

import org.primefaces.PrimeFaces;

import org.primefaces.event.RowEditEvent;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoEntidad.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoEntidad")
@SessionScoped
public class BeanTipoEntidad extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad ictp all entidades. */
	private Collection<TipoEntidad> ictp_allEntidades;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iotp all tipos. */
	private TipoEntidad iotp_allTipos;

	/** Propiedad iotp detalle tipo entidad. */
	private TipoEntidad iotp_detalleTipoEntidad;

	/**
	 * Modifica el valor de all entidades.
	 *
	 * @param aoctp_tp asigna el valor a la propiedad all entidades
	 */
	public void setAllEntidades(Collection<TipoEntidad> aoctp_tp)
	{
		ictp_allEntidades = aoctp_tp;
	}

	/**
	 * Retorna el valor de all entidades.
	 *
	 * @return el valor de all entidades
	 */
	public Collection<TipoEntidad> getAllEntidades()
	{
		if(!CollectionUtils.isValidCollection(ictp_allEntidades))
		{
			TipoEntidad lottr_ttr;

			try
			{
				setAllTipos(
				    ipr_parameterRemote.findTipoEntidad(
				        null, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
				lottr_ttr = getAllTipos();
				setDetalleTipoAdquisicion(null);

				if((lottr_ttr != null) && CollectionUtils.isValidCollection(lottr_ttr.getInfoAll()))
					ictp_allEntidades = lottr_ttr.getInfoAll();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictp_allEntidades;
	}

	/**
	 * Modifica el valor de all tipos.
	 *
	 * @param aotp_tp asigna el valor a la propiedad all tipos
	 */
	public void setAllTipos(TipoEntidad aotp_tp)
	{
		iotp_allTipos = aotp_tp;
	}

	/**
	 * Retorna el valor de all tipos.
	 *
	 * @return el valor de all tipos
	 */
	public TipoEntidad getAllTipos()
	{
		if(iotp_allTipos == null)
			iotp_allTipos = new TipoEntidad();

		return iotp_allTipos;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de detalle tipo adquisicion.
	 *
	 * @param aotp_tp asigna el valor a la propiedad detalle tipo adquisicion
	 */
	public void setDetalleTipoAdquisicion(TipoEntidad aotp_tp)
	{
		iotp_detalleTipoEntidad = aotp_tp;
	}

	/**
	 * Retorna el valor de detalle tipo entidad.
	 *
	 * @return el valor de detalle tipo entidad
	 */
	public TipoEntidad getDetalleTipoEntidad()
	{
		if(iotp_detalleTipoEntidad == null)
			iotp_detalleTipoEntidad = new TipoEntidad();

		return iotp_detalleTipoEntidad;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setAllEntidades(null);
		setDetalleTipoAdquisicion(null);
	}

	/**
	 * Insertar registro.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarRegistro()
	    throws B2BException
	{
		TipoEntidad lotp_tp;
		lotp_tp = getDetalleTipoEntidad();

		if(lotp_tp != null)
		{
			lotp_tp.setIdUsuarioCreacion(getUserId());

			try
			{
				lotp_tp.setAccion(true);
				ipr_parameterRemote.gestionTipoEntidad(lotp_tp, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setTipoEntidadActivo(null);
					lbr_beanReference.setDatosTipoEntidad(null);
					lbr_beanReference.setTipoEntidadById(null);
				}

				clean();

				addMessage(MessagesKeys.INSERCION_EXITOSA);
				PrimeFaces.current().executeScript("PF('idDataTipoEntidad').hide();");
				PrimeFaces.current().ajax().update("fTipoEntidad:idDtListado");
			}
			catch(B2BException lbe_lbe)
			{
				PrimeFaces.current().executeScript("PF('idDataTipoEntidad').show();");
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fTipoEntidad:globalMsg");
			}
		}
	}

	/**
	 * On row edit.
	 *
	 * @param event correspondiente al valor del tipo de objeto RowEditEvent
	 */
	public void onRowEdit(RowEditEvent event)
	{
		TipoEntidad lorc_rc;
		lorc_rc = (TipoEntidad)event.getObject();

		if(lorc_rc != null)
		{
			lorc_rc.setIdUsuarioCreacion(getUserId());

			try
			{
				lorc_rc.setAccion(false);
				ipr_parameterRemote.gestionTipoEntidad(lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
				setAllEntidades(null);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setTipoEntidadActivo(null);
					lbr_beanReference.setDatosTipoEntidad(null);
				}

				addMessage(MessagesKeys.MODIFICACION_EXITOSA);
				PrimeFaces.current().ajax().update("fTipoEntidad:globalMsg");
			}
			catch(B2BException lbe_lbe)
			{
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fTipoEntidad:globalMsg");
			}
		}
	}

	/**
	 * Show dialog.
	 */
	public void showDialog()
	{
		clean();
		PrimeFaces.current().executeScript("PF('idDataTipoEntidad').show();");
		PrimeFaces.current().ajax().update("DialogTipoEntidad");
	}
}
