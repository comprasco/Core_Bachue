package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.MotorEnvioElectronicoBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MotorEnvioBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 26/03/2020
 */
@javax.ejb.Stateless(name = " MotorEnvioElectronico", mappedName = "motorEnvioElectronicoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MotorEnvioElectronicoBean implements MotorEnvioElectronicoRemote
{
	/** Propiedad ime business. */
	private MotorEnvioElectronicoBusiness imee_business;

	/** {@inheritdoc} */
	@Override
	public void motorEnvioElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().motorEnvioElectronico(as_remoteIp);

		Logger.log(lsw_watch, "MotorEnvioElectronicoBean", "motorEnvioElectronico", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private MotorEnvioElectronicoBusiness getBusiness()
	{
		if(imee_business == null)
			imee_business = new MotorEnvioElectronicoBusiness();

		return imee_business;
	}
}
