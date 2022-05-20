/**
 * BS_SBB_CB_CambioLinderosPredios_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiolinderospredios.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_CambioLinderosPredios_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
public class BS_SBB_CB_CambioLinderosPredios_BindingImpl extends BaseServices
    implements BS_SBB_CB_CambioLinderosPredios_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 764005986286922107L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_CambioLinderosPredios_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar en Bachué la actualización de la delimitación de un predio por agregación o desagregación de varios predios con cambio de propietario.
	 *
	 * @param atecsccp_entrada de atecsccp entrada
	 * @return el valor de tipo salida consulta segregacion con cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    TipoEntradaConsultaSegregacionConCambioPropietario atecsccp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultaSegregacionConCambioPropietario ltscsccp_salida;

		ltscsccp_salida = new TipoSalidaConsultaSegregacionConCambioPropietario();

		try
		{
			ltscsccp_salida = getCambioLinderosPredioRemote()
					                  .consultaSegregacionConCambioPropietario(
					    atecsccp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSegregacionConCambioPropietario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultaSegregacionConCambioPropietario", le_e);
		}

		return ltscsccp_salida;
	}

	/**
	 * Permite consultar en Bachué la actualización de la delimitación de un predio por agregación o desagregación de varios predios sin cambio de propietario.
	 *
	 * @param atecsscp_entrada de atecsscp entrada
	 * @return el valor de tipo salida consulta segregacion sin cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    TipoEntradaConsultaSegregacionSinCambioPropietario atecsscp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultaSegregacionSinCambioPropietario ltscsccp_salida;

		ltscsccp_salida = new TipoSalidaConsultaSegregacionSinCambioPropietario();

		try
		{
			ltscsccp_salida = getCambioLinderosPredioRemote()
					                  .consultaSegregacionSinCambioPropietario(
					    atecsscp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaSegregacionSinCambioPropietario", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultaSegregacionSinCambioPropietario", le_e);
		}

		return ltscsccp_salida;
	}
}
