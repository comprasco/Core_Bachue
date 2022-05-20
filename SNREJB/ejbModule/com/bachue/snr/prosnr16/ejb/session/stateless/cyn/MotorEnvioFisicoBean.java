package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.MotorEnvioFisicoBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MotorEnvioFisicoBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/03/2020
 */
@javax.ejb.Stateless(name = "MotorEnvioFisico", mappedName = "motorEnvioFisicoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MotorEnvioFisicoBean implements MotorEnvioFisicoRemote
{
	/** Propiedad ime business. */
	private MotorEnvioFisicoBusiness ime_business;

	/** {@inheritdoc} */
	@Override
	public void motorEnvioFisico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().motorEnvioFisico(as_remoteIp);

		Logger.log(lsw_watch, "MotorEnvioFisicoBean", "motorEnvioFisico", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private MotorEnvioFisicoBusiness getBusiness()
	{
		if(ime_business == null)
			ime_business = new MotorEnvioFisicoBusiness();

		return ime_business;
	}
}
