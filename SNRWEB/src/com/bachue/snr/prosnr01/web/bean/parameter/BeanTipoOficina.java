package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoOficina.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoOficina")
@SessionScoped
public class BeanTipoOficina extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad ictp all oficinas. */
	private Collection<TipoOficina> ictp_allOficinas;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iotp all tipos. */
	private TipoOficina iotp_allTipos;

	/** Propiedad iotp detalle tipo oficina. */
	private TipoOficina iotp_detalleTipoOficina;

	/**
	 * Modifica el valor de all oficinas.
	 *
	 * @param aoctp_tp asigna el valor a la propiedad all oficinas
	 */
	public void setAllOficinas(Collection<TipoOficina> aoctp_tp)
	{
		ictp_allOficinas = aoctp_tp;
	}

	/**
	 * Retorna el valor de all oficinas.
	 *
	 * @return el valor de all oficinas
	 */
	public Collection<TipoOficina> getAllOficinas()
	{
		if(!CollectionUtils.isValidCollection(ictp_allOficinas))
		{
			TipoOficina lottr_ttr;

			try
			{
				setAllTipos(
				    ipr_parameterRemote.findTipoOficina(
				        null, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);
				lottr_ttr = getAllTipos();
				setDetalleTipoOficina(null);

				if((lottr_ttr != null) && CollectionUtils.isValidCollection(lottr_ttr.getInfoAll()))
					ictp_allOficinas = lottr_ttr.getInfoAll();
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return ictp_allOficinas;
	}

	/**
	 * Modifica el valor de all tipos.
	 *
	 * @param aotp_tp asigna el valor a la propiedad all tipos
	 */
	public void setAllTipos(TipoOficina aotp_tp)
	{
		iotp_allTipos = aotp_tp;
	}

	/**
	 * Retorna el valor de all tipos.
	 *
	 * @return el valor de all tipos
	 */
	public TipoOficina getAllTipos()
	{
		if(iotp_allTipos == null)
			iotp_allTipos = new TipoOficina();

		return iotp_allTipos;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de detalle tipo oficina.
	 *
	 * @param aotp_tp asigna el valor a la propiedad detalle tipo oficina
	 */
	public void setDetalleTipoOficina(TipoOficina aotp_tp)
	{
		iotp_detalleTipoOficina = aotp_tp;
	}

	/**
	 * Retorna el valor de detalle tipo oficina.
	 *
	 * @return el valor de detalle tipo oficina
	 */
	public TipoOficina getDetalleTipoOficina()
	{
		if(iotp_detalleTipoOficina == null)
			iotp_detalleTipoOficina = new TipoOficina();

		return iotp_detalleTipoOficina;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setAllOficinas(null);
		setDetalleTipoOficina(null);
	}

	/**
	 * Insertar registro.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertarRegistro()
	    throws B2BException
	{
		TipoOficina lotp_tp;
		lotp_tp = getDetalleTipoOficina();

		if(lotp_tp != null)
		{
			lotp_tp.setIdUsuarioCreacion(getUserId());

			try
			{
				lotp_tp.setAccion(true);
				ipr_parameterRemote.gestionTipoOficina(lotp_tp, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setTipoOficinaActivo(null);
					lbr_beanReference.setTipoOficinaActivoById(null);
					lbr_beanReference.setDatosTipoOficina(null);
					lbr_beanReference.setTipoOficinaById(null);
				}

				clean();

				PrimeFaces.current().executeScript("PF('idDataTipoOficina').hide();");
				PrimeFaces.current().ajax().update("fTipoOficina:idDtListado");

				throw new B2BException("Registro Insertado.");
			}
			catch(B2BException lbe_lbe)
			{
				PrimeFaces.current().executeScript("PF('idDataTipoOficina').show();");
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fTipoOficina:globalMsg");
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
		TipoOficina lorc_rc;
		lorc_rc = (TipoOficina)event.getObject();

		if(lorc_rc != null)
		{
			lorc_rc.setIdUsuarioCreacion(getUserId());

			try
			{
				lorc_rc.setAccion(false);
				ipr_parameterRemote.gestionTipoOficina(lorc_rc, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
				setAllOficinas(null);

				{
					BeanReference lbr_beanReference;
					lbr_beanReference = getBeanReference();

					lbr_beanReference.setTipoOficinaActivo(null);
					lbr_beanReference.setTipoOficinaActivoById(null);
					lbr_beanReference.setDatosTipoOficina(null);
					lbr_beanReference.setTipoOficinaById(null);
				}

				throw new B2BException("Registro Modificado.");
			}
			catch(B2BException lbe_lbe)
			{
				addMessage(lbe_lbe);
				PrimeFaces.current().ajax().update("fTipoOficina:globalMsg");
			}
		}
	}

	/**
	 * Show dialog.
	 */
	public void showDialog()
	{
		clean();
		PrimeFaces.current().executeScript("PF('idDataTipoOficina').show();");
		PrimeFaces.current().ajax().update("DialogTipoOficina");
	}
}
