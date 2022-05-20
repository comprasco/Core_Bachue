package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.recibo.liquidacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.recibo.liquidacion.ConsultaReciboLiquidacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ConsultaReciboLiquidacionBean.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "consultaReciboLiquidacion", mappedName = "consultaReciboLiquidacionoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaReciboLiquidacionBean implements ConsultaReciboLiquidacionRemote
{
	/** Propiedad icrl business. */
	private ConsultaReciboLiquidacionBusiness icrl_business;

	/** {@inheritdoc} */
	public void confirmarEliminarLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().confirmarEliminarLiquidacion(al_liquidacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConsultaReciboLiquidacionBean", "confirmarEliminarLiquidacion", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Collection<Liquidacion> consultarBandeja(String as_numeroReferencia)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_return;
		StopWatch               lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lcl_return     = getBusiness().consultarBandejaRecibos(as_numeroReferencia);

		Logger.log(lsw_watch, "ConsultaReciboLiquidacionBean", "consultarBandeja", null, null, null, null);

		return lcl_return;
	}

	/** {@inheritdoc} */
	public void terminarProceso(Liquidacion al_liquidacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().terminarProcesoRecibo(al_liquidacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConsultaReciboLiquidacionBean", "terminarProceso", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean validarEliminarLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		boolean   lcl_return;
		StopWatch lsw_watch;

		lsw_watch      = Logger.getNewStopWatch();
		lcl_return     = getBusiness().validarEliminarLiquidacion(al_liquidacion);

		Logger.log(lsw_watch, "ConsultaReciboLiquidacionBean", "validarEliminarLiquidacion", null, null, null, null);

		return lcl_return;
	}

	/**
	 * Retorna el valor de consulta recibo liquidacion business.
	 *
	 * @return el valor de consulta recibo liquidacion business
	 */
	private ConsultaReciboLiquidacionBusiness getBusiness()
	{
		if(icrl_business == null)
			icrl_business = new ConsultaReciboLiquidacionBusiness();

		return icrl_business;
	}
}
