package com.bachue.prosnr01.integracion.cliente.catastro.notificarCambioCatastro;

import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoEntradaNotificarCambioCatastro;
import co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoSalidaNotificarCambioCatastro;

import co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastroProxy;
import co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.catastro.NotificarCatastro;


/**
 * Clase que contiene todos las propiedades ClienteNotificarCambioCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/06/2020
 */
public class ClienteNotificarCambioCatastro
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteNotificarCambioCatastro.class);

	/**
	 * Notificar cambio catastro.
	 *
	 * @param anc_notificarCatastro de notificar catastro
	 * @param as_endpoint de endpoint
	 * @return el valor de tipo salida notificar cambio catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized TipoSalidaNotificarCambioCatastro notificarCambioCatastro(
	    NotificarCatastro anc_notificarCatastro, String as_endpoint
	)
	    throws B2BException
	{
		TipoSalidaNotificarCambioCatastro ltsncc_response;

		ltsncc_response = null;

		try
		{
			if(anc_notificarCatastro == null)
				throw new B2BException(ErrorKeys.ERROR_LISTA_PARAMETROS_NO_VALIDA);

			if(StringUtils.isValidString(as_endpoint))
			{
				BS_SBB_CB_RecepcionNotificacionCatastro_PortType lrncpt_interface;
				BS_SBB_CB_RecepcionNotificacionCatastroProxy     lrncp_proxy;

				lrncp_proxy          = new BS_SBB_CB_RecepcionNotificacionCatastroProxy(as_endpoint);
				lrncpt_interface     = lrncp_proxy.getBS_SBB_CB_RecepcionNotificacionCatastro_PortType();

				if(lrncpt_interface != null)
				{
					ltsncc_response = lrncpt_interface.notificarCambioCatastro(
						    new TipoEntradaNotificarCambioCatastro(
						        anc_notificarCatastro.getCodCatastro(), anc_notificarCatastro.getCodTransaccion(),
						        anc_notificarCatastro.getOperacionRegistro(),
						        anc_notificarCatastro.getFechaNotificacion(), anc_notificarCatastro.getOrips()
						    )
						);

					if(ltsncc_response == null)
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
			clh_LOGGER.error("notificarCambioCatastro", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarCambioCatastro", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return ltsncc_response;
	}
}
