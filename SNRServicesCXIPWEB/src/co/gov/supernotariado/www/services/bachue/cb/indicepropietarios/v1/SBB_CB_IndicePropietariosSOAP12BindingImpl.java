/**
 * SBB_CB_IndicePropietariosSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1;

import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_IndicePropietariosSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class SBB_CB_IndicePropietariosSOAP12BindingImpl extends BaseServices
    implements SBB_CB_IndicePropietarios_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8470229473585434770L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_IndicePropietariosSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * A nivel general, realizar búsqueda sobre las propiedades de una persona.
	 * Explicando con más detalle, se busca por la identificación de una persona Natural o Jurídica, en este orden de ideas, es obligatorio que se reciba un NIT o un tipoDocumentoPersona con su numDocumentoPersona. Los demás campos serán opcionales y si llegan, restringirían la búsqueda como filtros adicionales
	 *
	 * @param ateip_entrada de ateip entrada
	 * @return el valor de tipo salida indice propietarios
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaIndicePropietarios consultar(TipoEntradaIndicePropietarios ateip_entrada)
	    throws RemoteException
	{
		TipoSalidaIndicePropietarios ltip_salida;

		ltip_salida = new TipoSalidaIndicePropietarios();

		try
		{
			ltip_salida = getIndicePropietariosRemote()
					              .consultar(ateip_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultar", le_e);
		}

		return ltip_salida;
	}
}
