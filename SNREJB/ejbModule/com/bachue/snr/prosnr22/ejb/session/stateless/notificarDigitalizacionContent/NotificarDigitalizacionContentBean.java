package com.bachue.snr.prosnr22.ejb.session.stateless.notificarDigitalizacionContent;

import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent;
import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr22.business.notificarDigitalizacionContent.NotificarDigitalizacionContentBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades NotificarDigitalizacionContentBean.
 *
 * @author Heiner Castaneda
 */
@javax.ejb.Stateless(name = "NotificarDigitalizacionContent", mappedName = "notificarDigitalizacionContentMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificarDigitalizacionContentBean implements NotificarDigitalizacionContentRemote
{
	/** Propiedad icdb business. */
	private NotificarDigitalizacionContentBusiness indcb_business;

	/**
	 * Retorna el valor de notificar digitalización content business.
	 *
	 * @return el valor de notificar digitalización content business
	 */
	public NotificarDigitalizacionContentBusiness getNotificarDigitalizacionContentBusiness()
	{
		if(indcb_business == null)
			indcb_business = new NotificarDigitalizacionContentBusiness();

		return indcb_business;
	}

	/**
	 * Método encargado de notificar la digitalización de los documentos de una solicitud.
	 *
	 * @param atendc_request Argumento de tipo <code>TipoEntradaNotificarDigitalizacion</code>
	 * que contiene los criterios necesarios para realizar la notificación de digitalización.
	 * @param as_userIdConstant Argumento de tipo <code>String</code> que contiene el usuario que realiza la petición.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la petición.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la petición.
	 * @throws B2BException
	 */
	public TipoSalidaNotificarDigitalizacionContent notificarDigitalizacionContent(
	    TipoEntradaNotificarDigitalizacionContent atendc_request, String as_userId, String as_localIp,
	    String                                    as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaNotificarDigitalizacionContent ltsndc_tipoSalidaNotificarDigitalizacionC;
		StopWatch                                lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ltsndc_tipoSalidaNotificarDigitalizacionC = getNotificarDigitalizacionContentBusiness()
				                                            .notificarDigitalizacionContent(
				    atendc_request, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "notificarDigitalizacionContent", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsndc_tipoSalidaNotificarDigitalizacionC;
	}
}
