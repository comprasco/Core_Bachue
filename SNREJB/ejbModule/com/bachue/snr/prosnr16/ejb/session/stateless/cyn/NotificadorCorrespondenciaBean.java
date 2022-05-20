package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia;
import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.envio.NotificadorCorrespondencia.NotificadorCorrespondenciaBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades NotificadorCorrespondenciaBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 1/04/2020
 */
@javax.ejb.Stateless(name = "NotificadorCorrespondencia", mappedName = "NotificadorCorrespondenciaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificadorCorrespondenciaBean implements NotificadorCorrespondenciaRemote
{
	/** Propiedad incb business. */
	private NotificadorCorrespondenciaBusiness incb_business;

	/** {@inheritdoc} */
	public TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    TipoEntradaNotificarCorrespondencia atenc_entrada, String as_userId, String as_localIpAddress,
	    String                              as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaNotificarCorrespondencia ltsem_salida;

		lsw_watch        = Logger.getNewStopWatch();
		ltsem_salida     = getBusiness()
				                   .notificarCorrespondencia(
				    atenc_entrada, as_userId, as_localIpAddress, as_remoteIpAddress
				);

		Logger.log(
		    lsw_watch, "NotificadorCorrespondenciaBean", "notificarCorrespondencia", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return ltsem_salida;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private NotificadorCorrespondenciaBusiness getBusiness()
	{
		if(incb_business == null)
			incb_business = new NotificadorCorrespondenciaBusiness();

		return incb_business;
	}
}
