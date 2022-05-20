package com.bachue.snr.prosnr01.ejb.session.stateless.envioNotificacionPago;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.envioNotificacionPago.EnvioNotificacionPagoBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades EnvioNotificacionPagoBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "EnvioNotificacionPago", mappedName = "envioNotificacionPagoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EnvioNotificacionPagoBean implements EnvioNotificacionPagoRemote
{
	/** Propiedad ifm business. */
	private EnvioNotificacionPagoBusiness ifm_business;

	/** {@inheritdoc} */
	@Override
	public void enviarNotificacionPago(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarNotificacionPago(as_remoteIp);

		Logger.log(lsw_watch, "EnvioNotificacionPagoBean", "enviarNotificacionPago", null, null, null, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private EnvioNotificacionPagoBusiness getBusiness()
	{
		if(ifm_business == null)
			ifm_business = new EnvioNotificacionPagoBusiness();

		return ifm_business;
	}
}
