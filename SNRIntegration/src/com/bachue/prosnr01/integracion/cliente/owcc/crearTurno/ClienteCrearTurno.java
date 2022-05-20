package com.bachue.prosnr01.integracion.cliente.owcc.crearTurno;

import co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoEntradaCrearTurno;
import co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoParametroCT;
import co.gov.supernotariado.www.schemas.bachue.co.creadorturno.crearturno.v1.TipoSalidaCrearTurno;

import co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurnoProxy;
import co.gov.supernotariado.www.services.bachue.co.creadorturno.v1.SUT_CO_CreadorTurno_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import java.math.BigInteger;

import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene todos las propiedades ClienteCrearTurno.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ClienteCrearTurno
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteCrearTurno.class);

	/**
	 * Metodo encargado de crear turno en el gestor documental (OWCC).
	 *
	 * @param as_sistemaOrigen Variable de tipo String que contiene Componente origen (Bachue, Correspondencia, SE)
	 * @param amss_parametros Variable de tipo Map<String,String> que contiene Metadatos asociados al archivo a buscar en OWCC, Donde la clave de la llave es el metadato a buscar y el valor es el valor de la búsqueda
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void crearTurno(
	    String as_sistemaOrigen, Map<String, String> amss_parametros, String as_endpoint
	)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_sistemaOrigen))
				throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_VALIDO);

			if(!CollectionUtils.isValidCollection(amss_parametros))
				throw new B2BException(ErrorKeys.ERROR_LISTA_PARAMETROS_NO_VALIDA);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CO_CreadorTurno_PortType lscctpt_interface;
				SUT_CO_CreadorTurnoProxy     lscctp_proxy;

				lscctp_proxy          = new SUT_CO_CreadorTurnoProxy(as_endpoint);
				lscctpt_interface     = lscctp_proxy.getSUT_CO_CreadorTurno_PortType();

				if(lscctpt_interface != null)
				{
					if(
					    !(as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORRESPONDENCIA)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);

					TipoSalidaCrearTurno ltsct_response;
					TipoParametroCT[]    ltpct_parametros;
					int                  li_contadorApertura;

					ltpct_parametros        = new TipoParametroCT[amss_parametros.size()];
					li_contadorApertura     = 0;

					for(Entry<String, String> less_apertura : amss_parametros.entrySet())
					{
						if(less_apertura != null)
							ltpct_parametros[li_contadorApertura++] = new TipoParametroCT(
								    less_apertura.getKey(), less_apertura.getValue()
								);
					}

					ltsct_response = lscctpt_interface.crearTurno(
						    new TipoEntradaCrearTurno(as_sistemaOrigen, ltpct_parametros)
						);

					if(ltsct_response != null)
					{
						BigInteger lbi_codigoMensajeApertura;

						lbi_codigoMensajeApertura = ltsct_response.getCodigoMensaje();

						if((lbi_codigoMensajeApertura != null) && (lbi_codigoMensajeApertura.intValue() != 200))
							throw new B2BException(ltsct_response.getDescripcionMensaje());
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearTurno", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("crearTurno", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
