package com.bachue.prosnr16.integracion.cliente.cyn.mensajero;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;

import co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroProxy;
import co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.math.BigInteger;


/**
 * Clase que contiene todos las propiedades ClienteEnviarMensaje.
 *
 * @author Manuel Blanco
 */
public class ClienteEnviarMensaje
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteEnviarMensaje.class);

	/**
	 * Enviar mensaje.
	 *
	 * @param ateem_mensaje de ateem mensaje
	 * @param as_endpoint de as endpoint
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized TipoSalidaEnviarMensaje enviarMensaje(
	    TipoEntradaEnviarMensaje ateem_mensaje, String as_endpoint
	)
	    throws B2BException
	{
		TipoSalidaEnviarMensaje ltsct_response;

		ltsct_response = null;

		try
		{
			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CN_Mensajero_PortType lscctpt_interface;
				SUT_CN_MensajeroProxy     lscctp_proxy;

				lscctp_proxy          = new SUT_CN_MensajeroProxy(as_endpoint);
				lscctpt_interface     = lscctp_proxy.getSUT_CN_Mensajero_PortType();

				if(lscctpt_interface != null)
				{
					ltsct_response = lscctpt_interface.enviarMensaje(ateem_mensaje);

					if(ltsct_response != null)
					{
						BigInteger lbi_codigoMensaje;

						lbi_codigoMensaje = ltsct_response.getCodMensaje();

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
			clh_LOGGER.error("enviarMensaje", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("enviarMensaje", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return ltsct_response;
	}
}
