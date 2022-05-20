package com.bachue.prosnr01.integracion.cliente.bioclient.padFirmas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.biometrico.servicios.ws.BiometriaWS;
import com.bachue.snr.biometrico.servicios.ws.BiometriaWSProxy;
import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;
import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.util.Base64;

/**
 * Clase que contiene todos las propiedades ClientePadFirmas.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 13/03/2020
 */
public class ClientePadFirmas
{
	
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClientePadFirmas.class);

	/**
	 * Metodo encargado de consultar los documentos mediantes filtros establecidos al gestor documental (OWCC).
	 *
	 * @param aofdto_param de aofdto param
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized byte[] obtenerFirma(ObtenerFirmaDTO aofdto_param, String as_endpoint)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(aofdto_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(StringUtils.isValidString(as_endpoint))
			{
				BiometriaWS      lbwsi_interface;
				BiometriaWSProxy lbwsp_proxy;

				lbwsp_proxy         = new BiometriaWSProxy(as_endpoint);
				lbwsi_interface     = lbwsp_proxy.getBiometriaWS();

				if(lbwsi_interface != null)
				{
					ObtenerFirmaSalidaDTO lofsdto_response;

					lofsdto_response = lbwsi_interface.obtenerFirma(aofdto_param);

					if(lofsdto_response != null)
					{
						String ls_codigoMensaje;

						ls_codigoMensaje = lofsdto_response.getResultado();

						if(
						    StringUtils.isValidString(ls_codigoMensaje)
							    && (NumericUtils.getInt(ls_codigoMensaje) == CodigoMensajeCommon.CODIGO_200)
						)
						{
							String ls_respuesta;

							ls_respuesta = lofsdto_response.getFirma();

							if(!StringUtils.isValidString(ls_respuesta))
								throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
							else
								lba_return = Base64.getDecoder().decode(ls_respuesta);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
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
			clh_LOGGER.error("obtenerFirma", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerFirma", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_return;
	}

	/**
	 * Eliminar firma.
	 *
	 * @param aofdto_param de aofdto param
	 * @param as_endpoint de as endpoint
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized byte[] eliminarFirma(ObtenerFirmaDTO aofdto_param, String as_endpoint)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(aofdto_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(StringUtils.isValidString(as_endpoint))
			{
				BiometriaWS      lbwsi_interface;
				BiometriaWSProxy lbwsp_proxy;

				lbwsp_proxy         = new BiometriaWSProxy(as_endpoint);
				lbwsi_interface     = lbwsp_proxy.getBiometriaWS();

				if(lbwsi_interface != null)
					lbwsi_interface.eliminarFirma(aofdto_param);
				else
					throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarFirma", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("eliminarFirma", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_return;
	}
}
