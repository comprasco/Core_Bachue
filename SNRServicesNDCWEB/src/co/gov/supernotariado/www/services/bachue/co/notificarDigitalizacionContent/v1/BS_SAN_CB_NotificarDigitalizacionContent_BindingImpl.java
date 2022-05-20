/**
 * BS_SAN_CB_NotificarDigitalizacionContent_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1;

import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent;
import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;


public class BS_SAN_CB_NotificarDigitalizacionContent_BindingImpl extends BaseServices implements co.gov.supernotariado.www.services.bachue.co.notificarDigitalizacionContent.v1.BS_SAN_CB_NotificarDigitalizacionContent_PortType
{
	private static final long          serialVersionUID = -8652510898887506532L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(
			    BS_SAN_CB_NotificarDigitalizacionContent_BindingImpl.class,
			    ProyectosCommon.NOTIFICAR_DIGITALIZACION_CONTENT_22
			);

	public TipoSalidaNotificarDigitalizacionContent notificarDigitalizacionContent(
	    TipoEntradaNotificarDigitalizacionContent atendc_entrada
	)
	    throws java.rmi.RemoteException
	{
		TipoSalidaNotificarDigitalizacionContent ltsnd_response;

		ltsnd_response = new TipoSalidaNotificarDigitalizacionContent();

		try
		{
			ltsnd_response = getNotificarDigitalizacionContentRemote()
					                 .notificarDigitalizacionContent(
					    atendc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("notificarDigitalizacionContent", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarDigitalizacionContent", le_e);
		}

		return ltsnd_response;
	}
}
