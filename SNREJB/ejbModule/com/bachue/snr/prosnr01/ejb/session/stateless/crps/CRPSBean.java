package com.bachue.snr.prosnr01.ejb.session.stateless.crps;

import com.bachue.snr.prosnr01.business.crps.CRPSBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * EJB para exposicion de lógica de negocio de generación de archivos CRPS
 *
 * @author Edgar Prieto
 */
@javax.ejb.Stateless(name = "CRPS", mappedName = "crpsMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CRPSBean implements CRPSRemote
{
	/** Objeto de negocio de CRPS */
	private CRPSBusiness icrpsb_business;

	/** {@inheritdoc} */
	public void generarCRPS(java.util.Date ad_fecha, String as_userId, String as_localIp, String as_remoteIp)
	    throws com.b2bsg.common.exception.B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCRPSBusiness().generarCRPS(ad_fecha, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CRPSBean", "generarCRPS", as_userId, as_localIp, as_remoteIp, null);
	}

	/**
	 * Retorna el objeto de negocio de conciliaciones
	 *
	 * @return objeto de negocio de conciliaciones
	 */
	private CRPSBusiness getCRPSBusiness()
	{
		if(icrpsb_business == null)
			icrpsb_business = new CRPSBusiness();

		return icrpsb_business;
	}
}
