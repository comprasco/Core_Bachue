package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.acuseRecibido.AcuseRecibidoFisicoBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoFisicoBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 26/03/2020
 */
@javax.ejb.Stateless(name = "AcuseRecibidoFisico", mappedName = "acuseRecibidoFisicoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AcuseRecibidoFisicoBean implements AcuseRecibidoFisicoRemote
{
	/** Propiedad iar business. */
	private AcuseRecibidoFisicoBusiness iar_business;

	/** {@inheritdoc} */
	@Override
	public void acuseRecibidoFisico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().acuseRecibidoFisico(as_remoteIp);

		Logger.log(lsw_watch, "AcuseRecibidoBean", "acuseRecibidoFisico", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private AcuseRecibidoFisicoBusiness getBusiness()
	{
		if(iar_business == null)
			iar_business = new AcuseRecibidoFisicoBusiness();

		return iar_business;
	}
}
