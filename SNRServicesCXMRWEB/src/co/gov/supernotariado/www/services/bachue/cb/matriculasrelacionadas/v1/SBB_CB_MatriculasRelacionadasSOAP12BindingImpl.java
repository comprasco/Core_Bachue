/**
 * SBB_CB_MatriculasRelacionadasSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.v1;

import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoEntradaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoSalidaDatosMatriculasDerivadas;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoEntradaDatosMatriculasMatriz;
import co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoSalidaDatosMatriculasMatriz;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_MatriculasRelacionadasSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
public class SBB_CB_MatriculasRelacionadasSOAP12BindingImpl extends BaseServices implements SBB_CB_Matriculas_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1843306239851773127L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_MatriculasRelacionadasSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/** {@inheritdoc} */
	public TipoSalidaDatosMatriculasDerivadas consultarMatriculasDerivadas(
	    TipoEntradaDatosMatriculasDerivadas atedmd_entrada
	)
	    throws RemoteException
	{
		TipoSalidaDatosMatriculasDerivadas ltsdmd_salida;

		ltsdmd_salida = new TipoSalidaDatosMatriculasDerivadas();

		try
		{
			ltsdmd_salida = getMatriculasRelacionadasRemote()
					                .consultarMatriculasDerivadas(
					    atedmd_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculasDerivadas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculasDerivadas", le_e);
		}

		return ltsdmd_salida;
	}

	/** {@inheritdoc} */
	public TipoSalidaDatosMatriculasMatriz consultarMatriculasMatriz(TipoEntradaDatosMatriculasMatriz atedmm_entrada)
	    throws RemoteException
	{
		TipoSalidaDatosMatriculasMatriz ltsdmm_salida;

		ltsdmm_salida = new TipoSalidaDatosMatriculasMatriz();

		try
		{
			ltsdmm_salida = getMatriculasRelacionadasRemote()
					                .consultarMatriculasMatriz(
					    atedmm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculasMatriz", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculasMatriz", le_e);
		}

		return ltsdmm_salida;
	}
}
