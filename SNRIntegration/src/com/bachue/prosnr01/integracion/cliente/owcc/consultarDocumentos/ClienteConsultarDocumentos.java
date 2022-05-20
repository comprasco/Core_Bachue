package com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultarRepositorio;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoParametro;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;

import co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1.SUT_CO_BusquedaDocumentosProxy;
import co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1.SUT_CO_BusquedaDocumentos_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene todos las propiedades ClienteConsultarDocumentos.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ClienteConsultarDocumentos
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteConsultarDocumentos.class);

	/**
	 * Metodo encargado de consultar los documentos mediantes filtros establecidos al gestor documental (OWCC).
	 *
	 * @param as_sistemaOrigen Variable de tipo String que contiene Componente origen (Bachue, Correspondencia, SE)
	 * @param amss_parametros Variable de tipo Map<String,String> que contiene Metadatos asociados al archivo a buscar en OWCC, Donde la clave de la llave es el metadato a buscar y el valor es el valor de la búsqueda
	 * @param as_repositorioBusqueda Variable de tipo String que contiene Identificador del repositorio en donde se encuentra el documento a buscar.
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized Collection<DocumentoOWCC> consultarDocumentos(
	    String as_sistemaOrigen, Map<String, String> amss_parametros, String as_repositorioBusqueda, String as_endpoint
	)
	    throws B2BException
	{
		Collection<DocumentoOWCC> lcds_return;

		lcds_return = new ArrayList<DocumentoOWCC>();

		try
		{
			if(!StringUtils.isValidString(as_sistemaOrigen))
				throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_VALIDO);

			if(!CollectionUtils.isValidCollection(amss_parametros))
				throw new B2BException(ErrorKeys.ERROR_LISTA_PARAMETROS_NO_VALIDA);

			if(!StringUtils.isValidString(as_repositorioBusqueda))
				throw new B2BException(ErrorKeys.ERROR_REPOSITORIO_BUSQUEDA_NO_VALIDO);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CO_BusquedaDocumentos_PortType lscbdpt_interface;
				SUT_CO_BusquedaDocumentosProxy     lscbdp_proxy;

				lscbdp_proxy          = new SUT_CO_BusquedaDocumentosProxy(as_endpoint);
				lscbdpt_interface     = lscbdp_proxy.getSUT_CO_BusquedaDocumentos_PortType();

				if(lscbdpt_interface != null)
				{
					TipoSalidaConsultar             ltsc_response;
					TipoParametro[]                 ltpa_parametros;
					TipoEntradaConsultarRepositorio ltecr_repositorio;

					ltpa_parametros     = null;
					ltsc_response       = null;

					if(as_repositorioBusqueda.equalsIgnoreCase(TipoEntradaConsultarRepositorio._FINAL))
						ltecr_repositorio = TipoEntradaConsultarRepositorio.FINAL;
					else if(as_repositorioBusqueda.equalsIgnoreCase(TipoEntradaConsultarRepositorio._TEMPORAL))
						ltecr_repositorio = TipoEntradaConsultarRepositorio.TEMPORAL;
					else if(as_repositorioBusqueda.equalsIgnoreCase(TipoEntradaConsultarRepositorio._MIXTO))
						ltecr_repositorio = TipoEntradaConsultarRepositorio.MIXTO;
					else
						ltecr_repositorio = null;

					if(ltecr_repositorio == null)
						throw new B2BException(ErrorKeys.ERROR_REPOSITORIO_BUSQUEDA_NO_PERMITIDO);

					if(
					    !(as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.COMUNICACIONES_NOTIFICACIONES)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORRESPONDENCIA)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);

					int li_contador;

					li_contador         = 0;
					ltpa_parametros     = new TipoParametro[amss_parametros.size()];

					for(Entry<String, String> less_actual : amss_parametros.entrySet())
					{
						if(less_actual != null)
							ltpa_parametros[li_contador++] = new TipoParametro(
								    less_actual.getKey(), less_actual.getValue()
								);
					}

					ltsc_response = lscbdpt_interface.consultar(
						    new TipoEntradaConsultar(as_sistemaOrigen, ltpa_parametros, ltecr_repositorio)
						);

					if(ltsc_response != null)
					{
						BigInteger lbi_codigoMensaje;

						lbi_codigoMensaje = ltsc_response.getCodigoMensaje();

						if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() == 200))
						{
							TipoDocumento[] ltda_response;

							ltda_response = ltsc_response.getDocumentos();

							if(CollectionUtils.isValidCollection(ltda_response))
							{
								int li_tamano;

								li_tamano = ltda_response.length;

								for(int li_i = 0; li_i < li_tamano; li_i++)
									lcds_return.add(new DocumentoOWCC(ltda_response[li_i]));
							}
							else
								throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRARON_DOCUMENTOS_ASOCIADOS);
						}
						else
							throw new B2BException(ltsc_response.getDescripcionMensaje());
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
			clh_LOGGER.error("consultarDocumentos", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarDocumentos", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		if(lcds_return.isEmpty())
			lcds_return = null;

		return lcds_return;
	}
}
