package com.bachue.snr.prosnr01.ejb.session.stateless.cancelacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.registro.CancelacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Su función es permitir el acceso a la capa de negocio para los procesos de cancelaciones.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "Cancelacion", mappedName = "cancelacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CancelacionBean implements CancelacionRemote
{
	/** Propiedad icb business. */
	private CancelacionBusiness icb_business;

	/** {@inheritdoc} */
	@Override
	public void cancelacion(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().cancelacion(as_remoteIp);

		Logger.log(lsw_watch, "CancelacionBean", "cancelacion", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private CancelacionBusiness getBusiness()
	{
		if(icb_business == null)
			icb_business = new CancelacionBusiness();

		return icb_business;
	}
}
