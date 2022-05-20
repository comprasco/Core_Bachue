/**
 * SUT_CN_MensajeroSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.LoggingHelper;

import com.bachue.snr.prosnr04.web.services.BaseServices;


/**
 * Clase que contiene todos las propiedades SUT_CN_MensajeroSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 22/03/2020
 */
public class SUT_CN_MensajeroSOAP12BindingImpl extends BaseServices implements co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6879049412557292016L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)LoggingHelper.getLogger(
		    SUT_CN_MensajeroSOAP12BindingImpl.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16
		);

	/**
	 * El canal originador se encarga de enviar la información necesaria hacia el componente para notificar o
	 * comunicar al destinatario.
	 *
	 * @param ltece_entrada de entrada
	 * @return el valor de tipo salida consultar envio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarEnvio consultarEnvio(TipoEntradaConsultarEnvio ltece_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaConsultarEnvio ltsdt_salida = new TipoSalidaConsultarEnvio();

		try
		{
			ltsdt_salida = getMotorRecepcionRemote()
					               .consultarEnvio(
					    ltece_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarEnvio", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarEnvio", le_e);
		}

		return ltsdt_salida;
	}

	/**
	 * El canal originador se encarga de enviar la información necesaria hacia el componente para notificar o
	 * comunicar al destinatario.
	 *
	 * @param lteem_entrada de lteem entrada
	 * @return el valor de tipo salida enviar mensaje
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaEnviarMensaje enviarMensaje(TipoEntradaEnviarMensaje lteem_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaEnviarMensaje ltsdt_salida = new TipoSalidaEnviarMensaje();

		try
		{
			ltsdt_salida = getMotorRecepcionRemote()
					               .enviarMensaje(
					    lteem_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("enviarMensaje", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("enviarMensaje", le_e);
		}

		return ltsdt_salida;
	}
}
