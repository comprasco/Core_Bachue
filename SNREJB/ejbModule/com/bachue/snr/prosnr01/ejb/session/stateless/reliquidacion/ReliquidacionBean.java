package com.bachue.snr.prosnr01.ejb.session.stateless.reliquidacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.liquidacion.ReliquidacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Su función es permitir el acceso a la capa de negocio para los procesos de reliquidacion.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "Reliquidacion", mappedName = "reliquidacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReliquidacionBean implements ReliquidacionRemote
{
	/** Propiedad irb business. */
	private ReliquidacionBusiness irb_business;

	/** {@inheritdoc} */
	@Override
	public void reliquidacion(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().reliquidacion(as_remoteIp);

		Logger.log(lsw_watch, "ReliquidacionBean", "reliquidacion", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private ReliquidacionBusiness getBusiness()
	{
		if(irb_business == null)
			irb_business = new ReliquidacionBusiness();

		return irb_business;
	}
}
