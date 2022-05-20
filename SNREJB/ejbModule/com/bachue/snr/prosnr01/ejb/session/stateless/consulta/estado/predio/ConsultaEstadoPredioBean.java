package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.estado.predio.ConsultaEstadoPredioBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaEstadoPredioRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaEstadoPredioBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "consultaEstadoPredio", mappedName = "consultaEstadoPredioMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaEstadoPredioBean implements ConsultaEstadoPredioRemote
{
	/** Propiedad icep business. */
	private ConsultaEstadoPredioBusiness icep_business;

	/** {@inheritdoc} */
	public byte[] findEstado(
	    SolicitudMatricula asm_parametros, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_return;

		StopWatch lsw_watch;
		lsw_watch     = Logger.getNewStopWatch();

		lba_return = getConsultaEstadoPredioBusiness().findEstado(asm_parametros, as_usuario, as_remoteIp);

		Logger.log(lsw_watch, "ConsultaEstadoPredioBean", "findEstado", as_usuario, as_localIp, as_remoteIp, null);

		return lba_return;
	}

	/**
	 * Retorna el valor de consulta estado predio business.
	 *
	 * @return el valor de consulta estado predio business
	 */
	private ConsultaEstadoPredioBusiness getConsultaEstadoPredioBusiness()
	{
		if(icep_business == null)
			icep_business = new ConsultaEstadoPredioBusiness();

		return icep_business;
	}
}
