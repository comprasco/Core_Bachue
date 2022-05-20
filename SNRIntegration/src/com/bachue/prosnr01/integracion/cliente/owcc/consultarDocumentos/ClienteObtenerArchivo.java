package com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoEntradaObtenerArchivo;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoSalidaObtenerArchivo;

import co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1.SUT_CO_BusquedaDocumentosProxy;
import co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1.SUT_CO_BusquedaDocumentos_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;


/**
 * Clase que contiene todos las propiedades Cliente Obtener Archivo.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
public class ClienteObtenerArchivo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteObtenerArchivo.class);

	/**
	 * Metodo encargado de obtener un archivo mediantes filtros establecidos al gestor documental (OWCC).
	 *
	 * @param as_did de as did
	 * @param as_docName de as doc name
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized byte[] obtenerArchivo(String as_did, String as_docName, String as_endpoint)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		try
		{
			if(!StringUtils.isValidString(as_did))
				throw new B2BException("dID invalido");

			if(!StringUtils.isValidString(as_docName))
				throw new B2BException("dDocName invalido");

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CO_BusquedaDocumentos_PortType lscbdpt_interface;
				SUT_CO_BusquedaDocumentosProxy     lscbdp_proxy;

				lscbdp_proxy          = new SUT_CO_BusquedaDocumentosProxy(as_endpoint);
				lscbdpt_interface     = lscbdp_proxy.getSUT_CO_BusquedaDocumentos_PortType();

				if(lscbdpt_interface != null)
				{
					TipoSalidaObtenerArchivo ltsoa_salida;

					ltsoa_salida = lscbdpt_interface.obtenerArchivo(new TipoEntradaObtenerArchivo(as_docName, as_did));

					if(ltsoa_salida != null)
					{
						//java.math.BigInteger lbi_codigoMensaje;
						//lbi_codigoMensaje = ltsoa_salida.getCodigoMensaje();
						//if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() == 200))
						{
							byte[] lba_test;

							lba_test = ltsoa_salida.getArchivo();

							if((lba_test != null) && (lba_test.length > NumericUtils.DEFAULT_INT_VALUE))
								lba_return = lba_test;
							else
								throw new B2BException("No se encontro archivo");
						}

						/*else
						    throw new B2BException(ltsoa_salida.getDescripcionMensaje());*/
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
			clh_LOGGER.error("obtenerArchivo", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerArchivo", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_return;
	}
}
