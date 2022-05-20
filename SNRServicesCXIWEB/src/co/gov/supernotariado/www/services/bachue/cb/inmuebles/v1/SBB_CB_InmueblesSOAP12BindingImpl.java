/**
 * SBB_CB_InmueblesSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1;

import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_InmueblesSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class SBB_CB_InmueblesSOAP12BindingImpl extends BaseServices implements SBB_CB_Inmuebles_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6002591506697264808L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_InmueblesSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * La operación devuelve un listado de matrículas con sus datos básicos y por cada matrícula un listado de los propietarios, de igual forma, con sus datos básicos.
	 *
	 * @param atedbm_entrada de atedbm entrada
	 * @return el valor de tipo salida datos basicos matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(TipoEntradaDatosBasicosMatriculas atedbm_entrada)
	    throws RemoteException
	{
		TipoSalidaDatosBasicosMatriculas ltsdbm_salida;

		ltsdbm_salida = new TipoSalidaDatosBasicosMatriculas();

		try
		{
			ltsdbm_salida = getInmueblesRemote()
					                .consultarDatosBasicos(
					    atedbm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDatosBasicos", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDatosBasicos", le_e);
		}

		return ltsdbm_salida;
	}

	/**
	 * La operación devuelve los datos básicos para la matrícula ingresada.
	 *
	 * @param atedi_entrada de atedi entrada
	 * @return el valor de tipo salida datos inmueble
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaDatosInmueble consultarDatosInmueble(TipoEntradaDatosInmueble atedi_entrada)
	    throws RemoteException
	{
		TipoSalidaDatosInmueble ltsdi_salida;

		ltsdi_salida = new TipoSalidaDatosInmueble();

		try
		{
			ltsdi_salida = getInmueblesRemote()
					               .consultarDatosInmueble(
					    atedi_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDatosInmueble", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDatosInmueble", le_e);
		}

		return ltsdi_salida;
	}

	/**
	 * La operación devuelve un listado de direcciones anteriores del predio ingresado.
	 *
	 * @param ateda_entrada de ateda entrada
	 * @return el valor de tipo salida direcciones anteriores
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    TipoEntradaDireccionesAnteriores ateda_entrada
	)
	    throws RemoteException
	{
		TipoSalidaDireccionesAnteriores ltsda_salida;

		ltsda_salida = new TipoSalidaDireccionesAnteriores();

		try
		{
			ltsda_salida = getInmueblesRemote()
					               .consultarDireccionesAnteriores(
					    ateda_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDireccionesAnteriores", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDireccionesAnteriores", le_e);
		}

		return ltsda_salida;
	}

	/**
	 *     Con el dato de que identifique una persona natural o jurídica se trae un listado de matrículas donde esta persona se encuentra como propietaria parcial o total.
	 *
	 * @param atecm_entrada de atecm entrada
	 * @return el valor de tipo salida consulta matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultaMatriculas consultarMatriculas(TipoEntradaConsultaMatriculas atecm_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultaMatriculas ltscm_salida;

		ltscm_salida = new TipoSalidaConsultaMatriculas();

		try
		{
			ltscm_salida = getInmueblesRemote()
					               .consultarMatriculas(
					    atecm_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarMatriculas", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarMatriculas", le_e);
		}

		return ltscm_salida;
	}

	/**
	 * La operación devuelve un listado de propietarios para una matricula ingresada.
	 *
	 * @param atedp_entrada de atedp entrada
	 * @return el valor de tipo salida datos propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaDatosPropietario consultarPropietarios(TipoEntradaDatosPropietario atedp_entrada)
	    throws RemoteException
	{
		TipoSalidaDatosPropietario ltsdp_salida;

		ltsdp_salida = new TipoSalidaDatosPropietario();

		try
		{
			ltsdp_salida = getInmueblesRemote()
					               .consultarPropietarios(
					    atedp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPropietarios", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarPropietarios", le_e);
		}

		return ltsdp_salida;
	}
}
