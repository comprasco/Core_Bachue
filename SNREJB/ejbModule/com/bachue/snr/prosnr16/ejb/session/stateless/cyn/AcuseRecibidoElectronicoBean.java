package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.acuseRecibido.AcuseRecibidoElectronicoBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoElectronicoBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/03/2020
 */
@javax.ejb.Stateless(name = "AcuseRecibidoElectronico", mappedName = "acuseRecibidoElectronicoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AcuseRecibidoElectronicoBean implements AcuseRecibidoElectronicoRemote
{
	/** Propiedad iareb business. */
	private AcuseRecibidoElectronicoBusiness iareb_business;

	/** {@inheritdoc} */
	@Override
	public void acuseRecibidoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().acuseRecibidoElectronico(as_remoteIp);

		Logger.log(lsw_watch, "AcuseRecibidoBean", "acuseRecibidoElectronico", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void validarAcuseRecibidoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().validarAcuseRecibidoElectronico(as_remoteIp);

		Logger.log(lsw_watch, "AcuseRecibidoBean", "validarAcuseRecibidoElectronico", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private AcuseRecibidoElectronicoBusiness getBusiness()
	{
		if(iareb_business == null)
			iareb_business = new AcuseRecibidoElectronicoBusiness();

		return iareb_business;
	}
}
