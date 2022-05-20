package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.MutacionesTercerOrdenBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MutacionesTercerOrden.
 *
 * @author Carlos Calderon
 *
 */
@javax.ejb.Stateless(name = "MutacionesTercerOrden", mappedName = "mutacionesTercerOrdenMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MutacionesTercerOrdenBean implements MutacionesTercerOrdenRemote
{
	/** Propiedad imtorb business. */
	private MutacionesTercerOrdenBusiness imtorb_business;

	/**
	 * Retorna el valor de mutaciones tercer orden business.
	 *
	 * @return el valor de mutaciones tercer orden business
	 */
	public MutacionesTercerOrdenBusiness getMutacionesTercerOrdenBusiness()
	{
		if(imtorb_business == null)
			imtorb_business = new MutacionesTercerOrdenBusiness();

		return imtorb_business;
	}

	/**
	 * Registrar cambio tercer orden.
	 *
	 * @param ateccto_entrada de ateccto entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return de tipo salida consulta cambio tercer orden
	 * @throws B2BException de b 2 B exception
	 */
	public TipoSalidaConsultaCambioTercerOrden consultaCambioTercerOrden(
	    TipoEntradaConsultaCambioTercerOrden ateccto_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaConsultaCambioTercerOrden ltsccto_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsccto_return     = getMutacionesTercerOrdenBusiness()
				                     .consultaCambioTercerOrden(ateccto_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "MutacionesTercerOrdenBean", "consultaCambioTercerOrden", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsccto_return;
	}
}
