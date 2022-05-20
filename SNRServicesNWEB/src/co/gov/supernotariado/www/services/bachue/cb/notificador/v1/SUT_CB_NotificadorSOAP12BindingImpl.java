/**
 * SUT_CB_NotificadorSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.LoggingHelper;

import com.bachue.snr.prosnr04.web.services.BaseServices;


/**
 * Clase que contiene todos las propiedades SUT_CB_NotificadorSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
public class SUT_CB_NotificadorSOAP12BindingImpl extends BaseServices implements co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -236372678906926995L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)LoggingHelper.getLogger(
		    SUT_CB_NotificadorSOAP12BindingImpl.class, ProyectosCommon.NOTIFICADOR_20
		);

	/** {@inheritdoc} */
	public TipoSalidaAcusarMensaje acusarMensaje(TipoEntradaAcusarMensaje ateam_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaAcusarMensaje ltsdt_salida = new TipoSalidaAcusarMensaje();

		try
		{
			ltsdt_salida = getNotificadorRemote()
					               .acusarMensaje(
					    ateam_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("acusarMensaje", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("acusarMensaje", le_e);
		}

		return ltsdt_salida;
	}
}
