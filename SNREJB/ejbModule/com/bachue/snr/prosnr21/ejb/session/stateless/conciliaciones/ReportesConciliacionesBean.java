package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr21.business.conciliaciones.ReportesConciliacionesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las acciones Reportes Conciliaciones.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "ReportesConciliaciones", mappedName = "reportesConciliacionesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReportesConciliacionesBean implements ReportesConciliacionesRemote
{
	/**  Objeto de negocio de conciliaciones. */
	private ReportesConciliacionesBusiness ircb_business;

	@Override
	public void generarReportes(
	    String as_idReporteConciliacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, Exception
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().generarReportes(as_idReporteConciliacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ReportesConciliacionesBean", "generarReportes", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Retorna el valor de reference business.
	 *
	 * @return el valor de reference business
	 */
	private ReportesConciliacionesBusiness getBusiness()
	{
		if(ircb_business == null)
			ircb_business = new ReportesConciliacionesBusiness();

		return ircb_business;
	}
}
