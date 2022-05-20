package com.bachue.prosnr16.integracion.cliente.cyn.envioAcuseRecibido;

import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje;

import co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorProxy;
import co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Calendar;


/**
 * Clase que contiene todos las propiedades ClienteAcusarMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ClienteAcusarMensaje
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteAcusarMensaje.class);

	/**
	 * Metodo encargado de enviar el acuse de recibido.
	 *
	 * @param as_identificadorMensaje de identificador mensaje
	 * @param as_guia de guia
	 * @param ac_fechaAcuse de fecha acuse
	 * @param ac_fechaEnvio de fecha envio
	 * @param as_endpoint de endpoint
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void acusarMensaje(
	    String as_identificadorMensaje, String as_guia, Calendar ac_fechaAcuse, Calendar ac_fechaEnvio, String as_endpoint
	)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_identificadorMensaje))
				throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_MENSAJE_INVALIDO);

			if(!StringUtils.isValidString(as_guia))
				throw new B2BException(ErrorKeys.ERROR_GUIA_MENSAJE_INVALIDO);

			if(ac_fechaAcuse == null)
				throw new B2BException(ErrorKeys.ERROR_FECHA_ACUSE_INVALIDO);

			if(ac_fechaEnvio == null)
				throw new B2BException(ErrorKeys.ERROR_FECHA_ENVIO_INVALIDO);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CB_Notificador_PortType lscctpt_interface;
				SUT_CB_NotificadorProxy     lscctp_proxy;

				lscctp_proxy          = new SUT_CB_NotificadorProxy(as_endpoint);
				lscctpt_interface     = lscctp_proxy.getSUT_CB_Notificador_PortType();

				if(lscctpt_interface != null)
				{
					TipoSalidaAcusarMensaje ltsct_response;

					ltsct_response = lscctpt_interface.acusarMensaje(
						    new TipoEntradaAcusarMensaje(
						        as_identificadorMensaje, as_guia, ac_fechaAcuse, ac_fechaEnvio
						    )
						);

					if(ltsct_response != null)
					{
						BigInteger lbi_codigoMensaje;

						lbi_codigoMensaje = ltsct_response.getCodigoMensaje();

						if(lbi_codigoMensaje != null)
						{
							int li_codigoError;

							li_codigoError = lbi_codigoMensaje.intValue();

							if(!((li_codigoError == 200) || (li_codigoError == 409)))
								throw new B2BException(ltsct_response.getDescripcionMensaje());
						}
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
			clh_LOGGER.error("acusarMensaje", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("acusarMensaje", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
