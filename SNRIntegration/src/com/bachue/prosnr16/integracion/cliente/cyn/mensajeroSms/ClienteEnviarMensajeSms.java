package com.bachue.prosnr16.integracion.cliente.cyn.mensajeroSms;

import co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoEntradaEnviarMensajeTexto;
import co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1.TipoSalidaEnviarMensajeTexto;

import co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSmsProxy;
import co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.math.BigInteger;


/**
 * Clase que contiene todos las propiedades ClienteEnviarMensajeSms.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ClienteEnviarMensajeSms
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteEnviarMensajeSms.class);

	/**
	 * Metodo encargado de enviar un mensaje de texto.
	 *
	 * @param as_contenidoMensaje de as contenido mensaje
	 * @param as_numeroMovil de as numero movil
	 * @param as_endpoint de as endpoint
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void enviarMensajeTexto(
	    String as_contenidoMensaje, String as_numeroMovil, String as_endpoint
	)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_contenidoMensaje))
				throw new B2BException(ErrorKeys.ERROR_CONTENIDO_MENSAJE);

			if(!StringUtils.isValidString(as_numeroMovil))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL_VALIDO);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CE_MensajeroSms_PortType lscctpt_interface;
				SUT_CE_MensajeroSmsProxy     lscctp_proxy;

				lscctp_proxy          = new SUT_CE_MensajeroSmsProxy(as_endpoint);
				lscctpt_interface     = lscctp_proxy.getSUT_CE_MensajeroSms_PortType();

				if(lscctpt_interface != null)
				{
					TipoSalidaEnviarMensajeTexto ltsct_response;

					ltsct_response = lscctpt_interface.enviarMensajeTexto(
						    new TipoEntradaEnviarMensajeTexto(as_numeroMovil, as_contenidoMensaje)
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
			clh_LOGGER.error("enviarMensajeTexto", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("enviarMensajeTexto", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
