/**
 * SUT_CB_ConsultaMigracionSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1;

import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SUT_CB_ConsultaMigracionSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class SUT_CB_ConsultaMigracionSOAP12BindingImpl extends BaseServices implements SUT_CB_ConsultaMigracion_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 600301574879422779L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SUT_CB_ConsultaMigracionSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consulta que permite conocer si la información de varias matriculas ha sido migrada a Bachué.
	 *
	 * @param atecmm_entrada de atecmm entrada
	 * @return el valor de tipo salida consulta migracion matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    TipoEntradaConsultaMigracionMatriculas atecmm_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultaMigracionMatriculas ltscmp_salida;

		ltscmp_salida = new TipoSalidaConsultaMigracionMatriculas();

		try
		{
			ltscmp_salida = getConsultaMigracionRemote()
					                .consultarMigracionMatriculas(
					    atecmm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMigracionMatriculas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMigracionMatriculas", le_e);
		}

		return ltscmp_salida;
	}

	/**
	 * La condición consiste en evaluar la fecha actual del sistema, es decir la fecha en la que se consuma esta operación comparándola con la fecha de migración de la ORIP (FECHA_PRODUCCION_BACHUE de tabla SDB_PGN_CIRCULO_REGISTRAL), de tal forma que al ser mayor o igual a la fecha de migración devolvería true en el campo “informacionMigrada”. Cualquier otro caso, false.
	 *
	 * @param atecmo_entrada de atecmo entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultar migracion ORI p.v 1 . tipo salida consulta migracion ORIP
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    TipoEntradaConsultaMigracionORIP atecmo_entrada
	)
	    throws RemoteException
	{
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP ltscmp_salida;

		ltscmp_salida = new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP();

		try
		{
			ltscmp_salida = getConsultaMigracionRemote()
					                .consultarMigracionORIP(
					    atecmo_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMigracionORIP", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMigracionORIP", le_e);
		}

		return ltscmp_salida;
	}

	/**
	 * Se valida el predio bajo la condición de si la información de este ha sido o no migrada, de tal forma que se brinde a las diferentes operaciones de Coexistencia la indicación para saber dónde buscar: Nodo Central o Bachué.
	 *
	 * @param atecmp_entrada de atecmp entrada
	 * @return el valor de tipo salida consulta migracion predio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    TipoEntradaConsultaMigracionPredio atecmp_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultaMigracionPredio ltscmp_salida;

		ltscmp_salida = new TipoSalidaConsultaMigracionPredio();

		try
		{
			ltscmp_salida = getConsultaMigracionRemote()
					                .consultarMigracionPredio(
					    atecmp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMigracionPredio", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMigracionPredio", le_e);
		}

		return ltscmp_salida;
	}
}
