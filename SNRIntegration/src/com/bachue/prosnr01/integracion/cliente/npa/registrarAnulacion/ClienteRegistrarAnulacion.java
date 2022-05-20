package com.bachue.prosnr01.integracion.cliente.npa.registrarAnulacion;

import co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion;

import co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasProxy;
import co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import java.math.BigInteger;


/**
 * Clase que contiene todos las propiedades ClienteRegistrarAnulacion.
 *
 * @author  Gabriel Francisco Arias
 * Fecha de Creacion: 04/03/2020
 */
public class ClienteRegistrarAnulacion
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteRegistrarAnulacion.class);

	/**
	 * Registrar anulacion.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 * @param as_endpoint de as endpoint
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void registrarAnulacion(String as_numeroReferencia, String as_endpoint)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_numeroReferencia))
				throw new B2BException("Numero de referencia invalido");

			if(StringUtils.isValidString(as_endpoint))
			{
				SBB_CI_OperacionesFinancieras_PortType lsnof_interface;
				SBB_CI_OperacionesFinancierasProxy     lsnofp_proxy;

				lsnofp_proxy        = new SBB_CI_OperacionesFinancierasProxy(as_endpoint);
				lsnof_interface     = lsnofp_proxy.getSBB_CI_OperacionesFinancieras_PortType();

				if(lsnof_interface != null)
				{
					TipoSalidaRegistrarAnulacion ltsra_response;

					ltsra_response = lsnof_interface.registrarAnulacion(
						    new TipoEntradaRegistrarAnulacion(as_numeroReferencia)
						);

					if(ltsra_response != null)
					{
						BigInteger lbi_codigoMensaje;

						lbi_codigoMensaje = ltsra_response.getCodigoMensaje();

						if(
						    (lbi_codigoMensaje == null)
							    || ((lbi_codigoMensaje != null)
							    && (lbi_codigoMensaje.longValue() != CodigoMensajeCommon.CODIGO_200))
						)
						{
							String ls_error;

							ls_error = ltsra_response.getDescripcionMensaje();

							if(!StringUtils.isValidString(ls_error))
								ls_error = ErrorKeys.ERROR_INTERNO_SISTEMA;

							throw new B2BException(ls_error);
						}
					}
					else
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = "registrarAnulacion";

						throw new B2BException(ErrorKeys.ERROR_SIN_RESPUESTA_OPERACION, loa_arg);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarDocumentos", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDocumentos", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
