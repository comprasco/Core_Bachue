/**
 * SUT_CN_NotificadorCorrespondenciaSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1;

import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia;
import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.LoggingHelper;

import com.bachue.snr.prosnr04.web.services.BaseServices;


/**
 * Clase que contiene todos las propiedades SUT_CN_NotificadorCorrespondenciaSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class SUT_CN_NotificadorCorrespondenciaSOAP12BindingImpl extends BaseServices implements co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3441439923371843913L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)LoggingHelper.getLogger(
		    SUT_CN_NotificadorCorrespondenciaSOAP12BindingImpl.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16
		);

	/**
	 * Servicio mediante el cual Correspondencia podrá notificar el acuse de recibido de los mensajes físicos al componente de comunicaciones y notificaciones comunicándose a través del OSB.
	 *
	 * @param ltenc_entrada de ltenc entrada
	 * @return el valor de tipo salida notificar correspondencia
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    TipoEntradaNotificarCorrespondencia ltenc_entrada
	)
	    throws java.rmi.RemoteException
	{
		TipoSalidaNotificarCorrespondencia ltsnc_salida = new TipoSalidaNotificarCorrespondencia();

		try
		{
			ltsnc_salida = getNotificadorCorrespondenciaRemote()
					               .notificarCorrespondencia(
					    ltenc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("notificarCorrespondencia", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarCorrespondencia", le_e);
		}

		return ltsnc_salida;
	}
}
