/**
 * BS_SBB_CB_NuevasMatriculas_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_NuevasMatriculas_BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/02/2020
 */
public class BS_SBB_CB_NuevasMatriculas_BindingImpl extends BaseServices implements BS_SBB_CB_NuevasMatriculas_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7809375820548984920L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SBB_CB_NuevasMatriculas_BindingImpl.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar los datos registrales asociados a la creación de nuevas matrículas por
	 * círculo registral en un determinado periodo de tiempo.
	 *
	 * @param atecnm_entrada de atecnm entrada
	 * @return el valor de tipo salida consultar nuevas matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarNuevasMatriculas consultarNuevasMatriculas(
	    TipoEntradaConsultarNuevasMatriculas atecnm_entrada
	)
	    throws java.rmi.RemoteException
	{
		TipoSalidaConsultarNuevasMatriculas ltscnm_salida;

		ltscnm_salida = new TipoSalidaConsultarNuevasMatriculas();

		try
		{
			ltscnm_salida = getNuevasMatriculasRemote()
					                .consultaNuevasMatriculas(
					    atecnm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarNuevasMatriculas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarNuevasMatriculas", le_e);
		}

		return ltscnm_salida;
	}
}
