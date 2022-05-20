/**
 * BS_SBB_CB_Salvedades_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoEntradaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarIdentificadoresCatastrales.v1.TipoSalidaRegistrarIdentificadoresCatastrales;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedades;
import co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoSalidaregistrarCambioSalvedades;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_Salvedades_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/03/2020
 */
public class BS_SBB_CB_Salvedades_BindingImpl extends BaseServices implements BS_SBB_CB_Salvedades_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2288462186511665421L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_Salvedades_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite generar una salvedad en el folio de matrícula inmobiliaria por cambio de nomenclatura, soportada por una resolución emitida
	 * por el actor que genera el cambio de nomenclatura (Catastros y/o Secretarías de Planeación).
	 *
	 * @param atercs_entrada de atercs entrada
	 * @return el valor de tipo salidaregistrar cambio salvedades
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaregistrarCambioSalvedades registraCambioSalvedades(
	    TipoEntradaregistrarCambioSalvedades atercs_entrada
	)
	    throws RemoteException
	{
		TipoSalidaregistrarCambioSalvedades ltsrcs_salida;

		ltsrcs_salida = new TipoSalidaregistrarCambioSalvedades();

		try
		{
			ltsrcs_salida = getSalvedadesRemote()
					                .registraCambioSalvedades(
					    atercs_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registraCambioSalvedades", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registraCambioSalvedades", le_e);
		}

		return ltsrcs_salida;
	}

	/**
	 * Permite informar a Bachué los nuevos identificadores catastrales, para las matrículas inmobiliarias reportadas previamente a los catastros
	 * a través del servicio “Datos Registrales por nuevas matrículas”.
	 *
	 * @param ateric_entrada de ateric entrada
	 * @return el valor de tipo salida registrar identificadores catastrales
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarIdentificadoresCatastrales registraIdentificadoresCatastrales(
	    TipoEntradaRegistrarIdentificadoresCatastrales ateric_entrada
	)
	    throws RemoteException
	{
		TipoSalidaRegistrarIdentificadoresCatastrales ltsric_salida;

		ltsric_salida = new TipoSalidaRegistrarIdentificadoresCatastrales();

		try
		{
			ltsric_salida = getSalvedadesRemote()
					                .registraIdentificadoresCatastrales(
					    ateric_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registraIdentificadoresCatastrales", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registraIdentificadoresCatastrales", le_e);
		}

		return ltsric_salida;
	}
}
