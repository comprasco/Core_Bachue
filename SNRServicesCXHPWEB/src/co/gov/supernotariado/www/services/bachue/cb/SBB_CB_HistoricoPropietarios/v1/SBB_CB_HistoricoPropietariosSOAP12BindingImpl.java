/**
 * SBB_CB_HistoricoPropietariosSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.v1;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_HistoricoPropietariosSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class SBB_CB_HistoricoPropietariosSOAP12BindingImpl extends BaseServices
    implements SBB_CB_HistoricoPropietarios_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8752996750077644579L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_HistoricoPropietariosSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Lista las propiedades donde una persona natural o jurídica ha sido o continúa siendo propietario indicando su porcentaje de participación.
	 *
	 * @param atechp_entrada de atechp entrada
	 * @return el valor de tipo salida consultar historico propiedades
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(
	    TipoEntradaConsultarHistoricoPropiedades atechp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarHistoricoPropiedades ltchp_salida;

		ltchp_salida = new TipoSalidaConsultarHistoricoPropiedades();

		try
		{
			ltchp_salida = getHistoricoPropietariosRemote()
					               .consultarHistoricoPropiedades(
					    atechp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarHistoricoPropiedades", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarHistoricoPropiedades", le_e);
		}

		return ltchp_salida;
	}

	/**
	 * Lista los propietarios que ha tenido un predio, devolviendo el periodo en el cual ha sido o continúa siendo propietario indicando su porcentaje de participación.
	 *
	 * @param atechp_entrada de atechp entrada
	 * @return el valor de tipo salida consultar historico propietarios
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(
	    TipoEntradaConsultarHistoricoPropietarios atechp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarHistoricoPropietarios ltschp_salida;

		ltschp_salida = new TipoSalidaConsultarHistoricoPropietarios();

		try
		{
			ltschp_salida = getHistoricoPropietariosRemote()
					                .consultarHistoricoPropietarios(
					    atechp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarHistoricoPropietarios", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarHistoricoPropietarios", le_e);
		}

		return ltschp_salida;
	}
}
