/**
 * BS_SBB_CB_RRRMatriculas_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RRRMatriculas_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/03/2020
 */
public class BS_SBB_CB_RRRMatriculas_BindingImpl extends BaseServices implements BS_SBB_CB_RRRMatriculas_PortType
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_RRRMatriculas_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1537350753458866329L;

	/**
	 * Permite consultar la información asociada a los derechos, restricciones, responsabilidades, hipotecas o publicidades
	 * sobre una propiedad inmueble con Folio de Matrícula Inmobiliaria.
	 *
	 * @param atecrrrm_entrada de atecrrrm entrada
	 * @return el valor de tipo salida consultar RRR matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarRRRMatriculas consultarRRRMatriculas(TipoEntradaConsultarRRRMatriculas atecrrrm_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarRRRMatriculas ltscrrrm_salida;

		ltscrrrm_salida = new TipoSalidaConsultarRRRMatriculas();

		try
		{
			ltscrrrm_salida = getRRRMatriculasRemote()
					                  .consultaRRRMatriculas(
					    atecrrrm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarRRRMatriculas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarRRRMatriculas", le_e);
		}

		return ltscrrrm_salida;
	}
}
