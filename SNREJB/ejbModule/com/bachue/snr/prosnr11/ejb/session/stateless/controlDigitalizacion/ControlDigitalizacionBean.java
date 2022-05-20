package com.bachue.snr.prosnr11.ejb.session.stateless.controlDigitalizacion;

import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion;
import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr11.business.controlDigitalizacion.ControlDigitalizacionBusiness;

import com.bachue.snr.prosnr11.ejb.session.stateless.controlDigitalizacion.ControlDigitalizacionRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ControlDigitalizacionBean.
 *
 * @author Heiner Castaneda
 */
@javax.ejb.Stateless(name = "ControlDigitalizacion", mappedName = "controlDigitalizacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ControlDigitalizacionBean implements ControlDigitalizacionRemote
{
	/** Propiedad icdb business. */
	private ControlDigitalizacionBusiness icdb_business;

	/**
	 * Retorna el valor de control digitalización business.
	 *
	 * @return el valor de control digitalización business
	 */
	public ControlDigitalizacionBusiness getControlDigitalizacionBusiness()
	{
		if(icdb_business == null)
			icdb_business = new ControlDigitalizacionBusiness();

		return icdb_business;
	}

	/**
	 * Método encargado de notificar la digitalización de los documentos de una solicitud.
	 *
	 * @param atend_request Argumento de tipo <code>TipoEntradaNotificarDigitalizacion</code>
	 * que contiene los criterios necesarios para realizar la notificación de digitalización.
	 * @param as_userIdConstant Argumento de tipo <code>String</code> que contiene el usuario que realiza la petición.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la petición.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la petición.
	 * @throws B2BException
	 */
	public TipoSalidaNotificarDigitalizacion notificarDigitalizacion(
	    TipoEntradaNotificarDigitalizacion atend_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaNotificarDigitalizacion ltsnd_tipoSalidaNotificarDigitalizacion;
		StopWatch                         lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ltsnd_tipoSalidaNotificarDigitalizacion = getControlDigitalizacionBusiness()
				                                          .notificarDigitalizacion(
				    atend_request, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "notificarDigitalizacion", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsnd_tipoSalidaNotificarDigitalizacion;
	}
}
