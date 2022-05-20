package com.bachue.snr.prosnr01.ejb.session.stateless.generarReciboCaja;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.generarReciboCaja.GenerarReciboCajaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades GenerarReciboCajaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "GenerarReciboCaja", mappedName = "generarReciboCajaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GenerarReciboCajaBean implements GenerarReciboCajaRemote
{
	/** Propiedad igrc business. */
	private GenerarReciboCajaBusiness igrc_business;

	/** {@inheritdoc} */
	public void generarReciboCaja(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().generarReciboCaja(as_remoteIp);

		Logger.log(lsw_watch, "GenerarDocumentosBean", "generarReciboCaja", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private GenerarReciboCajaBusiness getBusiness()
	{
		if(igrc_business == null)
			igrc_business = new GenerarReciboCajaBusiness();

		return igrc_business;
	}
}
