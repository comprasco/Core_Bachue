package com.bachue.snr.prosnr01.ejb.session.stateless.firma;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.firma.FirmaMasivaRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades FirmaMasivaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "FirmaMasiva", mappedName = "firmaMasivaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class FirmaMasivaBean implements FirmaMasivaRemote
{
	/** Propiedad ifm business. */
	private FirmaMasivaBusiness ifm_business;

	/** {@inheritdoc} */
	@Override
	public void firmaMasiva(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().firmaMasiva(as_remoteIp);

		Logger.log(lsw_watch, "FirmaMasivaBean", "firmaMasiva", null, null, null, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private FirmaMasivaBusiness getBusiness()
	{
		if(ifm_business == null)
			ifm_business = new FirmaMasivaBusiness();

		return ifm_business;
	}
}
