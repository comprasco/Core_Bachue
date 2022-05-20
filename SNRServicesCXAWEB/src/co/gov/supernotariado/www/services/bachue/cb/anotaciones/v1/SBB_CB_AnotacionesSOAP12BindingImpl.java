/**
 * SBB_CB_AnotacionesSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1;

import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_AnotacionesSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class SBB_CB_AnotacionesSOAP12BindingImpl extends BaseServices implements SBB_CB_Anotaciones_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 340916281892120409L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_AnotacionesSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * El servicio permite consultar las anotaciones de un predio, la consulta se puede realizar teniendo en cuenta si son anotaciones normales o anotaciones de medidas cautelares.
	 *
	 * @param ateda_entrada de TipoEntradaDatosAnotacion
	 * @return el valor de tipo anotacion[]
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoAnotacion[] consultarAnotaciones(TipoEntradaDatosAnotacion ateda_entrada)
	    throws RemoteException
	{
		TipoAnotacion[] lta_salida;

		lta_salida = null;

		try
		{
			lta_salida = getAnotacionesRemote()
					             .consultarAnotaciones(
					    ateda_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAnotaciones", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarAnotaciones", le_e);
		}

		return lta_salida;
	}
}
