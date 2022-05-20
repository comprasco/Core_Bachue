/**
 * BS_SBB_CB_CambioPropietario_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_CambioPropietario_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public class BS_SBB_CB_CambioPropietario_BindingImpl extends BaseServices implements co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1.BS_SBB_CB_CambioPropietario_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2400030967590650438L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_CambioPropietario_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar la mutación de primer orden por cambio de propietario o poseedor.
	 *
	 * @param ateccp_entrada de ateccp entrada
	 * @return el valor de tipo salida consultar cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    TipoEntradaConsultarCambioPropietario ateccp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarCambioPropietario ltscsccp_salida;

		ltscsccp_salida = new TipoSalidaConsultarCambioPropietario();

		try
		{
			ltscsccp_salida = getCambioPropietarioRemote()
					                  .consultarCambioPropietario(
					    ateccp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarCambioPropietario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarCambioPropietario", le_e);
		}

		return ltscsccp_salida;
	}
}
