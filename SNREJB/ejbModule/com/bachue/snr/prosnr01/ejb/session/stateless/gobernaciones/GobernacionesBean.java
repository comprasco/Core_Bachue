package com.bachue.snr.prosnr01.ejb.session.stateless.gobernaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.gobernaciones.GobernacionesBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades GobernacionesBean.
 *
 * @author Luis Chacón
 */
@javax.ejb.Stateless(name = "Gobernaciones", mappedName = "gobernacionesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GobernacionesBean implements GobernacionesRemote
{
	/** Propiedad icb business. */
	private GobernacionesBusiness igb_business;

	/** {@inheritdoc} */
	public void gobernaciones(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getGobernacionesBusiness().gobernacionesImpuestos(as_remoteIp);

		Logger.log(lsw_watch, "GobernacionesBean", "gobernaciones", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de gobernaciones business.
	 *
	 * @return el valor de gobernaciones business
	 */
	private GobernacionesBusiness getGobernacionesBusiness()
	{
		if(igb_business == null)
			igb_business = new GobernacionesBusiness();

		return igb_business;
	}
}
