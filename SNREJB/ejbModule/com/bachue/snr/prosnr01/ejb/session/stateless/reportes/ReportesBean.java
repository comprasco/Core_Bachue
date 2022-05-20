package com.bachue.snr.prosnr01.ejb.session.stateless.reportes;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.reportes.ReportesBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.reportes.ReportesRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ReportesBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Reportes", mappedName = "reportesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReportesBean implements ReportesRemote
{
	/** Propiedad irb business. */
	private ReportesBusiness irb_business;

	/** {@inheritdoc} */
	@Override
	public void getEveningReport(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReportesBusiness().reportes(as_remoteIp);

		Logger.log(lsw_watch, "ReportesBean", "getEveningReport", null, null, null, null);
	}

	/**
	 * Retorna el valor de reportes business.
	 *
	 * @return el valor de reportes business
	 */
	public ReportesBusiness getReportesBusiness()
	{
		if(irb_business == null)
			irb_business = new ReportesBusiness();

		return irb_business;
	}
}
