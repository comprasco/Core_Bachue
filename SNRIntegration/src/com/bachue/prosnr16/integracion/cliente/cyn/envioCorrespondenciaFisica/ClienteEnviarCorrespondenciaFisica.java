package com.bachue.prosnr16.integracion.cliente.cyn.envioCorrespondenciaFisica;

import co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoEntradaEnviarCorrespondenciaFisica;
import co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1.TipoSalidaEnviarCorrespondenciaFisica;

import co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisicaProxy;
import co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.math.BigInteger;


/**
 * Clase que contiene todos las propiedades ClienteEnviarCorrespondenciaFisica.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ClienteEnviarCorrespondenciaFisica
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteEnviarCorrespondenciaFisica.class);

	/**
	 * Metodo encargado de enviar correspondencia Física.
	 *
	 * @param as_identificadorMensaje de as identificador mensaje
	 * @param as_endpoint de as endpoint
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void enviarCorrespondenciaFisica(String as_identificadorMensaje, String as_endpoint)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_identificadorMensaje))
				throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_MENSAJE_INVALIDO);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CR_EnvioCorrespondenciaFisica_PortType lscctpt_interface;
				SUT_CR_EnvioCorrespondenciaFisicaProxy     lscctp_proxy;

				lscctp_proxy          = new SUT_CR_EnvioCorrespondenciaFisicaProxy(as_endpoint);
				lscctpt_interface     = lscctp_proxy.getSUT_CR_EnvioCorrespondenciaFisica_PortType();

				if(lscctpt_interface != null)
				{
					TipoSalidaEnviarCorrespondenciaFisica ltsct_response;

					ltsct_response = lscctpt_interface.enviarCorrespondenciaFisica(
						    new TipoEntradaEnviarCorrespondenciaFisica(as_identificadorMensaje)
						);

					if(ltsct_response != null)
					{
						BigInteger lbi_codigoMensaje;

						lbi_codigoMensaje = ltsct_response.getCodigoMensaje();

						if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() != 200))
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
			clh_LOGGER.error("enviarCorrespondenciaFisica", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("enviarCorrespondenciaFisica", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
