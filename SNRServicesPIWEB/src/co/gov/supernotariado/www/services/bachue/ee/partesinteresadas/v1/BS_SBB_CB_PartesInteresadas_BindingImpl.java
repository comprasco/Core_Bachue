/**
 * BS_SBB_CB_PartesInteresadas_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_PartesInteresadas_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
public class BS_SBB_CB_PartesInteresadas_BindingImpl extends BaseServices
    implements BS_SBB_CB_PartesInteresadas_PortType
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_PartesInteresadas_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8082875459867218534L;

	/**
	 * Permite consultar la información asociada a las partes interesadas sobre una propiedad inmueble actuales o históricos con Folio de Matrícula Inmobiliaria.
	 *
	 * @param atecpi_entrada de atecpi entrada
	 * @return el valor de tipo salida consultar partes interesadas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    TipoEntradaConsultarPartesInteresadas atecpi_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarPartesInteresadas ltscrrrm_salida;

		ltscrrrm_salida = new TipoSalidaConsultarPartesInteresadas();

		try
		{
			ltscrrrm_salida = getPartesInteresadasRemote()
					                  .consultarPartesInteresadas(
					    atecpi_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPartesInteresadas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarPartesInteresadas", le_e);
		}

		return ltscrrrm_salida;
	}
}
