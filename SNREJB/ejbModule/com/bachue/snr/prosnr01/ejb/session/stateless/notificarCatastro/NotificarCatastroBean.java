package com.bachue.snr.prosnr01.ejb.session.stateless.notificarCatastro;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.notificarCatastro.NotificarCatastroBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades NotificarCatastroBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
@javax.ejb.Stateless(name = "NotificarCatastro", mappedName = "notificarCatastroMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificarCatastroBean implements NotificarCatastroRemote
{
	/** Propiedad incb business. */
	private NotificarCatastroBusiness incb_business;

	/** {@inheritdoc} */
	public void notificarCatastroPO(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().notificarCatastroPO(as_remoteIp);

		Logger.log(lsw_watch, "NotificarCatastroBean", "notificarCatastroPO", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void notificarCatastroSOCP(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().notificarCatastroSOCP(as_remoteIp);

		Logger.log(lsw_watch, "NotificarCatastroBean", "notificarCatastroSOCP", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void notificarCatastroSOSP(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().notificarCatastroSOSP(as_remoteIp);

		Logger.log(lsw_watch, "NotificarCatastroBean", "notificarCatastroSOSP", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void notificarCatastroTO(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().notificarCatastroTO(as_remoteIp);

		Logger.log(lsw_watch, "NotificarCatastroBean", "notificarCatastroTO", null, null, null, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private NotificarCatastroBusiness getBusiness()
	{
		if(incb_business == null)
			incb_business = new NotificarCatastroBusiness();

		return incb_business;
	}
}
