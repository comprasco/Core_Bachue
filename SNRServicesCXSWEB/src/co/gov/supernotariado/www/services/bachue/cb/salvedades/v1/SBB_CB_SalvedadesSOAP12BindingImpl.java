/**
 * SBB_CB_SalvedadesSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.v1;

import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_SalvedadesSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class SBB_CB_SalvedadesSOAP12BindingImpl extends BaseServices implements SBB_CB_Salvedades_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3612540538912332563L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_SalvedadesSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/** {@inheritdoc} */
	public TipoSalidaSalvedades consultarSalvedades(TipoEntradaSalvedades ates_entrada)
	    throws RemoteException
	{
		TipoSalidaSalvedades ltss_salida;

		ltss_salida = new TipoSalidaSalvedades();

		try
		{
			ltss_salida = getSalvedadesCXRemote()
					              .consultarSalvedades(
					    ates_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSalvedades", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarSalvedades", le_e);
		}

		return ltss_salida;
	}
}
