package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase para el manejo de eventos de workflow.
 *
 * @author Duvan Beltrán
 */
@ManagedBean(name = "beanConciliaciones")
@SessionScoped
public class BeanConciliaciones extends BaseBean
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8279908205288832688L;

	/** Propiedad icr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote icr_conciliacionesRemote;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
	}
}
