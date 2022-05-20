package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.MotorEnvioSmsBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MotorEnvioSmsBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/03/2020
 */
@javax.ejb.Stateless(name = "MotorEnvioSms", mappedName = "motorEnvioSmsMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MotorEnvioSmsBean implements MotorEnvioSmsRemote
{
	/** Propiedad ime business. */
	private MotorEnvioSmsBusiness imesms_business;

	/** {@inheritdoc} */
	@Override
	public void motorEnvioSms(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().motorEnvioSms(as_remoteIp);
		Logger.log(lsw_watch, "MotorEnvioSmsBean", "motorEnvioSms", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private MotorEnvioSmsBusiness getBusiness()
	{
		if(imesms_business == null)
			imesms_business = new MotorEnvioSmsBusiness();

		return imesms_business;
	}
}
