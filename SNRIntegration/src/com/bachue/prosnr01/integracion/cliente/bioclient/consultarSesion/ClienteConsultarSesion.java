package com.bachue.prosnr01.integracion.cliente.bioclient.consultarSesion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.biometrico.servicios.ws.BiometriaWS;
import com.bachue.snr.biometrico.servicios.ws.BiometriaWSProxy;
import com.bachue.snr.biometrico.servicios.ws.SesionDTO;
import com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;


/**
 * Clase que contiene todos las propiedades ClienteConsultarSesion.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 13/03/2020
 */
public class ClienteConsultarSesion
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteConsultarSesion.class);

	/**
	 * Consultar sesion.
	 *
	 * @param as_idSesion de as id sesion
	 * @param as_endpoint de as endpoint
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized boolean consultarSesion(String as_idSesion, String as_endpoint)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(!StringUtils.isValidString(as_idSesion))
				throw new B2BException(ErrorKeys.ERROR_ID_SESION_INVALIDA);

			if(StringUtils.isValidString(as_endpoint))
			{
				BiometriaWS      lbwsi_interface;
				BiometriaWSProxy lbwsp_proxy;

				lbwsp_proxy         = new BiometriaWSProxy(as_endpoint);
				lbwsi_interface     = lbwsp_proxy.getBiometriaWS();

				if(lbwsi_interface != null)
				{
					SesionDTO lsdto_response;

					lsdto_response = lbwsi_interface.consultarSesion(new SesionEntradaDTO(as_idSesion));

					if(lsdto_response != null)
					{
						String ls_codigoMensaje;

						ls_codigoMensaje = lsdto_response.getCodigo();

						if(
						    StringUtils.isValidString(ls_codigoMensaje)
							    && (NumericUtils.getInt(ls_codigoMensaje) == CodigoMensajeCommon.CODIGO_200)
						)
							lb_return = BooleanUtils.getBooleanValue(lsdto_response.getResultado());
						else
						{
							String ls_error;

							ls_error = lsdto_response.getMensaje();

							if(!StringUtils.isValidString(ls_error))
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
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
			clh_LOGGER.error("consultarSesion", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarSesion", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lb_return;
	}
}
