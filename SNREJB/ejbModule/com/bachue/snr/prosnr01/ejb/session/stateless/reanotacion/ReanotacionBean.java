package com.bachue.snr.prosnr01.ejb.session.stateless.reanotacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.reanotacion.ReanotacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import org.perf4j.StopWatch;


/**
 * Clase que contiene los métodos que se usan para el proceso de reanotación.
 *
 * @author jpatino
 */
@javax.ejb.Stateless(name = "reanotacion", mappedName = "reanotacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReanotacionBean implements ReanotacionRemote
{
	/** Propiedad irb business. */
	private ReanotacionBusiness irb_business;

	/** {@inheritdoc} */
	public String getObservaciones(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getBusiness().getObservaciones(ath_turnoHistoria);

		Logger.log(lsw_watch, "reanotacion", "getObservaciones", as_userId, as_localIp, as_remoteIp, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Long consultaByTurno(String as_turno, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Long      ll_turnoHistoria;

		lsw_watch     = Logger.getNewStopWatch();

		ll_turnoHistoria = getBusiness().consultaByTurno(as_turno);

		Logger.log(lsw_watch, "reanotacion", "consultaTurnosAsociados", as_idUsuario, as_localIp, as_remoteIp, null);

		return ll_turnoHistoria;
	}

	/** {@inheritdoc} */
	public void enviarAEntrega(TurnoHistoria ath_turno, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarAEntrega(ath_turno, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "reanotacion", "enviarAEntrega", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private ReanotacionBusiness getBusiness()
	{
		if(irb_business == null)
			irb_business = new ReanotacionBusiness();

		return irb_business;
	}
}
