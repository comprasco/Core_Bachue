/**
 * SUT_CB_ControlDigitalizacionSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.controldigitalizacion.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion;
import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.web.services.BaseServices;


public class SUT_CB_ControlDigitalizacionSOAP12BindingImpl extends BaseServices
    implements SUT_CB_ControlDigitalizacion_PortType
{
	private static final long          serialVersionUID = 2431088912395501642L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SUT_CB_ControlDigitalizacionSOAP12BindingImpl.class);

	/**
	 * Operación encargada de notificar la digitalización de los documentos de una solicitud.
	 *
	 * @param entrada Argumento de tipo <code>TipoEntradaNotificarDigitalizacion</code>
	 * que contiene los criterios necesarios para realizar la notificación de digitalización.
	 * @return Objeto de tipo <code>TipoSalidaNotificarDigitalizacion</code>
	 * que contiene el código y descripción de la respuesta de la operación.
	 * @throws B2BException
	 */
	public TipoSalidaNotificarDigitalizacion notificarDigitalizacion(TipoEntradaNotificarDigitalizacion entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaNotificarDigitalizacion ltsnd_response;

		ltsnd_response = new TipoSalidaNotificarDigitalizacion();

		try
		{
			ltsnd_response = getControlDigitalizacionRemote()
					                 .notificarDigitalizacion(
					    entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("notificarDigitalizacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarDigitalizacion", le_e);
		}

		return ltsnd_response;
	}
}
