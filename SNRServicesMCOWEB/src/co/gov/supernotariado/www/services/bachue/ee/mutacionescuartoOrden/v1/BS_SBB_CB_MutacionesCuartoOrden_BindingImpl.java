/**
 * BS_SBB_CB_MutacionesCuartoOrden_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden;
import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_MutacionesCuartoOrden_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/03/2020
 */
public class BS_SBB_CB_MutacionesCuartoOrden_BindingImpl extends BaseServices
    implements BS_SBB_CB_MutacionesCuartoOrden_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6387130686683471286L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_MutacionesCuartoOrden_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercco_entrada de atercco entrada
	 * @return TipoSalidaConsultaCambioTercerOrden con el resultado de la consulta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarCambioCuartoOrden registraCambioCuartoOrden(
	    TipoEntradaRegistrarCambioCuartoOrden atercco_entrada
	)
	    throws RemoteException
	{
		TipoSalidaRegistrarCambioCuartoOrden ltsrcto_salida;

		ltsrcto_salida = new TipoSalidaRegistrarCambioCuartoOrden();

		try
		{
			ltsrcto_salida = getMutacionesCuartoOrdenRemote()
					                 .registraCambioCuartoOrden(
					    atercco_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registraCambioCuartoOrden", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registraCambioCuartoOrden", le_e);
		}

		return ltsrcto_salida;
	}
}
