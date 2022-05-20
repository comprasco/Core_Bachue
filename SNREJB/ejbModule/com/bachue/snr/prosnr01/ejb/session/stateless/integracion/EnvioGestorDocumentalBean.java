package com.bachue.snr.prosnr01.ejb.session.stateless.integracion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.integracion.EnvioGestorDocumentalRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades EnvioGestorDocumentalBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "EnvioGestorDocumental", mappedName = "envioGestorDocumentalMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EnvioGestorDocumentalBean implements EnvioGestorDocumentalRemote
{
	/** Propiedad ifm business. */
	private EnvioGestorDocumentalBusiness ifm_business;

	/** {@inheritdoc} */
	@Override
	public void enviarGestorDocumental(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarGestorDocumental(as_remoteIp);

		Logger.log(lsw_watch, "EnvioGestorDocumentalBean", "enviarGestorDocumental", null, null, null, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private EnvioGestorDocumentalBusiness getBusiness()
	{
		if(ifm_business == null)
			ifm_business = new EnvioGestorDocumentalBusiness();

		return ifm_business;
	}
}
