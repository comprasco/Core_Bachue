/**
 * BS_SBB_CB_RecepcionNotificacionBachue_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1;

import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio;
import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionBachue_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class BS_SBB_CB_RecepcionNotificacionBachue_BindingImpl extends BaseServices
    implements BS_SBB_CB_RecepcionNotificacionBachue_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1406272036128271115L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_RecepcionNotificacionBachue_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite actualizar el estado de las solicitudes de notificación de información desde Core Bachué hacia los Catastros.
	 *
	 * @param aterrs_entrada de aterrs entrada
	 * @return el valor de tipo salida registrar reintento servicio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarReintentoServicio registrarReintentoServicio(
	    TipoEntradaRegistrarReintentoServicio aterrs_entrada
	)
	    throws RemoteException
	{
		TipoSalidaRegistrarReintentoServicio ltsrrs_salida;

		ltsrrs_salida = new TipoSalidaRegistrarReintentoServicio();

		try
		{
			ltsrrs_salida = getRegistrarReintentoServicioRemote()
					                .registrarReintentoServicio(
					    aterrs_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarReintentoServicio", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarReintentoServicio", le_e);
		}

		return ltsrrs_salida;
	}
}
