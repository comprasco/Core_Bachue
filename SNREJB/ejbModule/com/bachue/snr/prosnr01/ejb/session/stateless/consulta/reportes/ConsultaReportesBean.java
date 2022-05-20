package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reportes;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.reportes.ConsultaReportesBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reportes.ConsultaReportesRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaReportesBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ConsultaReportes", mappedName = "consultaReportesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaReportesBean implements ConsultaReportesRemote
{
	/** Propiedad ict business. */
	private ConsultaReportesBusiness ict_business;

	/** {@inheritdoc} */
	@Override
	public CamposConsulta findCamposReportes(Consultas aocc_cc)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		CamposConsulta lct_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_datos = getConsultaReportesBusiness().findCamposReportes(aocc_cc);

		Logger.log(lsw_watch, "ConsultaReportesBean", "findCamposReportes", null, null, null, null);

		return lct_datos;
	}

	/** {@inheritdoc} */
	public ResultadoConsulta generacionReporte(CamposConsulta aocc_cc)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		ResultadoConsulta lrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_datos = getConsultaReportesBusiness().generacionReporte(aocc_cc);

		Logger.log(lsw_watch, "ConsultaReportesBean", "generacionReporte", null, null, null, null);

		return lrc_datos;
	}

	/**
	 * Retorna el valor de consulta reportes business.
	 *
	 * @return el valor de consulta reportes business
	 */
	private ConsultaReportesBusiness getConsultaReportesBusiness()
	{
		if(ict_business == null)
			ict_business = new ConsultaReportesBusiness();

		return ict_business;
	}
}
