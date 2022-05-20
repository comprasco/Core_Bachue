package com.bachue.prosnr01.integracion.cliente.correspondencia.generacionIDsCorrespondencia;

import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondencia;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoSalidaObtenerEECorrespondencia;

import co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondenciaProxy;
import co.gov.supernotariado.www.services.bachue.cr.generacionidscorrespondencia.v1.SUT_CR_GeneracionIDsCorrespondencia_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.correspondencia.ParametrosIDsCorrespondencia;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.integration.correspondencia.Correspondencia;

import java.math.BigInteger;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ClienteGeneracionIDsCorrespondencia.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 10/12/2019
 */
public class ClienteGeneracionIDsCorrespondencia
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteGeneracionIDsCorrespondencia.class);

	/**
	 * Metodo encargado de generar IDs correspondencia.
	 *
	 * @param ac_c Variable Objeto de tipo Correspondencia que contiene Componente
	 * @param as_endpoint Variable de tipo String que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized String generacionIDsCorrespondencia(Correspondencia ac_c, String as_endpoint)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CR_GeneracionIDsCorrespondencia_PortType lscgicpt_interface;
				SUT_CR_GeneracionIDsCorrespondenciaProxy     lscgicp_proxy;

				lscgicp_proxy          = new SUT_CR_GeneracionIDsCorrespondenciaProxy(as_endpoint);
				lscgicpt_interface     = lscgicp_proxy.getSUT_CR_GeneracionIDsCorrespondencia_PortType();

				if(lscgicpt_interface != null)
				{
					TipoSalidaObtenerEECorrespondencia ltsoeec_response;

					ParametrosIDsCorrespondencia.generacionIDsCorrespondencia(ac_c);

					TipoEntradaObtenerEECorrespondenciaClasificacion lteoeecc_clasificacion;

					lteoeecc_clasificacion = null;

					String ls_clasificacion;

					ls_clasificacion = ac_c.getClasificacion();

					if(StringUtils.isValidString(ls_clasificacion))
					{
						if(
						    ls_clasificacion.equalsIgnoreCase(
							        TipoEntradaObtenerEECorrespondenciaClasificacion._comunicacion
							    )
						)
							lteoeecc_clasificacion = TipoEntradaObtenerEECorrespondenciaClasificacion.comunicacion;
						else if(
						    ls_clasificacion.equalsIgnoreCase(
							        TipoEntradaObtenerEECorrespondenciaClasificacion._notificacion
							    )
						)
							lteoeecc_clasificacion = TipoEntradaObtenerEECorrespondenciaClasificacion.notificacion;
					}

					TipoEntradaObtenerEECorrespondenciaCanal lteoeecc_canal;

					lteoeecc_canal = null;

					String ls_canal;

					ls_canal = ac_c.getCanal();

					if(StringUtils.isValidString(ls_canal))
					{
						if(ls_canal.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._fisico))
							lteoeecc_canal = TipoEntradaObtenerEECorrespondenciaCanal.fisico;
						else if(ls_canal.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._electronico))
							lteoeecc_canal = TipoEntradaObtenerEECorrespondenciaCanal.electronico;
						else if(ls_canal.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._sms))
							lteoeecc_canal = TipoEntradaObtenerEECorrespondenciaCanal.sms;
					}

					Collection<String> lcs_documentos;
					String[]           lsa_documentos;

					lcs_documentos     = ac_c.getDocumentos();
					lsa_documentos     = null;

					if(CollectionUtils.isValidCollection(lcs_documentos))
					{
						int li_contador;

						li_contador        = 0;
						lsa_documentos     = new String[lcs_documentos.size()];

						for(String ls_actual : lcs_documentos)
						{
							if(ls_actual != null)
								lsa_documentos[li_contador++] = ls_actual;
						}
					}

					Collection<TipoDestinatario> lcs_destinatarios;
					TipoDestinatario[]           ltda_destinatarios;

					lcs_destinatarios      = ac_c.getDestinatarios();
					ltda_destinatarios     = null;

					if(CollectionUtils.isValidCollection(lcs_destinatarios))
					{
						int li_contador;

						li_contador            = 0;
						ltda_destinatarios     = new TipoDestinatario[lcs_destinatarios.size()];

						for(TipoDestinatario ltd_actual : lcs_destinatarios)
						{
							if(ltd_actual != null)
								ltda_destinatarios[li_contador++] = ltd_actual;
						}
					}

					ltsoeec_response = lscgicpt_interface.obtenerEECorrespondencia(
						    new TipoEntradaObtenerEECorrespondencia(
						        lteoeecc_clasificacion, lteoeecc_canal, ac_c.getIdOrip(), ac_c.getNir(), ac_c.getTurno(),
						        ac_c.getNumeroFolios(), lsa_documentos, ltda_destinatarios
						    )
						);

					if(ltsoeec_response != null)
					{
						BigInteger lbi_codigoMensajeApertura;

						lbi_codigoMensajeApertura = ltsoeec_response.getCodigoMensaje();

						if((lbi_codigoMensajeApertura != null) && (lbi_codigoMensajeApertura.intValue() == 200))
							ls_return = ltsoeec_response.getIdentificador();
						else
							throw new B2BException(ltsoeec_response.getDescripcionMensaje());
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
			clh_LOGGER.error("generacionIDsCorrespondencia", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generacionIDsCorrespondencia", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return ls_return;
	}
}
