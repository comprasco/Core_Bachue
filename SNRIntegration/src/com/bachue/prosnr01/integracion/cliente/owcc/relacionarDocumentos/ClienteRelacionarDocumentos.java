package com.bachue.prosnr01.integracion.cliente.owcc.relacionarDocumentos;

import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoSalidaRelacionarDocumento;

import co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1.SUT_CO_RelacionesDocumentoProxy;
import co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1.SUT_CO_RelacionesDocumento_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.parametros.ParametrosOWCCCommon;

import java.math.BigInteger;

import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene todos las propiedades ClienteRelacionarDocumentos.
 *
 * @author  Julian David Vaca R
 * Fecha de Creacion: 25/11/2019
 */
public class ClienteRelacionarDocumentos
{
	/**
	 * Constante clh_LOGGER.
	 */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteRelacionarDocumentos.class);

	/**
	 * Este servicio se crea con el fin de relacionar a un nuevo NIR un documento existente en el SGD.
	 *
	 * @param as_sistemaOrigen Variable de tipo <code>String</code> que contiene Componente origen (Bachue, Correspondencia, SE)
	 * @param amss_parametros Variable de tipo <code>Map<String,String></code> que contiene Metadatos asociados al documento existente en el OWCC a relacionar.
	 * @param as_docName Variable de tipo <code>String</code> que contiene Identificador del documento en Owcc (Si es temporal retorna el prefijo: TMP o Si es el final retorna el prefijo: SNR).
	 * @param as_dId Variable de tipo <code>String</code> que contiene el identificador del archivo en Owcc.
	 * @param as_endpoint Variable de tipo <code>String</code> que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized void RelacionarDocumento(
	    String as_sistemaOrigen, Map<String, String> amss_parametros, String as_docName, String as_dId,
	    String as_endpoint
	)
	    throws B2BException
	{
		try
		{
			if(!StringUtils.isValidString(as_sistemaOrigen))
				throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_VALIDO);

			if(!CollectionUtils.isValidCollection(amss_parametros))
				throw new B2BException(ErrorKeys.ERROR_LISTA_PARAMETROS_NO_VALIDA);

			if(!StringUtils.isValidString(as_docName))
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ParametrosOWCCCommon.NOMBRE_DOCUMENTO;

				throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
			}

			if(!StringUtils.isValidString(as_dId))
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ParametrosOWCCCommon.DID;

				throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
			}

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CO_RelacionesDocumento_PortType lscrdpt_interface;
				SUT_CO_RelacionesDocumentoProxy     lscrdp_proxy;

				lscrdp_proxy          = new SUT_CO_RelacionesDocumentoProxy(as_endpoint);
				lscrdpt_interface     = lscrdp_proxy.getSUT_CO_RelacionesDocumento_PortType();

				if(lscrdpt_interface != null)
				{
					if(
					    !(as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.COMUNICACIONES_NOTIFICACIONES)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORRESPONDENCIA)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);

					TipoSalidaRelacionarDocumento ltsrd_response;
					TipoParametroRD[]             ltprd_parametros;
					int                           li_contadorApertura;

					ltsrd_response          = null;
					ltprd_parametros        = new TipoParametroRD[amss_parametros.size()];
					li_contadorApertura     = 0;

					for(Entry<String, String> less_apertura : amss_parametros.entrySet())
					{
						if(less_apertura != null)
							ltprd_parametros[li_contadorApertura++] = new TipoParametroRD(
								    less_apertura.getKey(), less_apertura.getValue()
								);
					}

					ltsrd_response = lscrdpt_interface.relacionarDocumento(
						    new TipoEntradaRelacionarDocumento(as_sistemaOrigen, ltprd_parametros, as_docName, as_dId)
						);

					if(ltsrd_response != null)
					{
						BigInteger lbi_codigoMensajeApertura;

						lbi_codigoMensajeApertura = ltsrd_response.getCodigoMensaje();

						if((lbi_codigoMensajeApertura != null) && (lbi_codigoMensajeApertura.intValue() != 200))
							throw new B2BException(ltsrd_response.getDescripcionMensaje());
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
			clh_LOGGER.error("RelacionarDocumento", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("RelacionarDocumento", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}
	}
}
