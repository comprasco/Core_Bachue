package com.bachue.snr.prosnr20.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr20.business.cyn.notificador.NotificadorBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades NotificadorBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
@javax.ejb.Stateless(name = "Notificador", mappedName = "NotificadorMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificadorBean implements NotificadorRemote
{
	/** Propiedad imr business. */
	private NotificadorBusiness inr_business;

	/** {@inheritdoc} */
	public TipoSalidaAcusarMensaje acusarMensaje(
	    TipoEntradaAcusarMensaje ateam_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoSalidaAcusarMensaje ltsem_salida;

		lsw_watch        = Logger.getNewStopWatch();
		ltsem_salida     = getBusiness().acusarMensaje(ateam_entrada, as_userId, as_localIpAddress, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "NotificadorBean", "acusarMensaje", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return ltsem_salida;
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private NotificadorBusiness getBusiness()
	{
		if(inr_business == null)
			inr_business = new NotificadorBusiness();

		return inr_business;
	}
}
