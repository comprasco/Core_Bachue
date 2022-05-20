package com.bachue.prosnr01.integracion.cliente.indicePropietarios.consultar;

import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietariosProxy;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import java.math.BigInteger;

import java.util.Map;


/**
 * Clase que contiene todos las propiedades ClienteIndicePropietarios.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/07/2020
 */
public class ClienteIndicePropietarios
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteIndicePropietarios.class);

	/**
	 * Consultar.
	 *
	 * @param lmss_criterios de lmss criterios
	 * @param as_endpoint de as endpoint
	 * @return el valor de tipo salida indice propietarios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized TipoSalidaIndicePropietarios consultar(
	    Map<String, String> lmss_criterios, String as_endpoint
	)
	    throws B2BException
	{
		TipoSalidaIndicePropietarios ltpip_return;

		ltpip_return = null;

		try
		{
			if(CollectionUtils.isValidCollection(lmss_criterios))
			{
				if(StringUtils.isValidString(as_endpoint))
				{
					SBB_EE_IndicePropietarios_PortType lseippt_interface;
					SBB_EE_IndicePropietariosProxy     lseipp_proxy;

					lseipp_proxy          = new SBB_EE_IndicePropietariosProxy(
						    as_endpoint, "usr_colpatria", "datatools1"
						);
					lseippt_interface     = lseipp_proxy.getSBB_EE_IndicePropietarios_PortType();

					if(lseippt_interface != null)
					{
						String ls_tipoDocumentoPersona;
						String ls_numDocumentoPersona;
						String ls_numNIT;
						String ls_primerNombre;
						String ls_segundoNombre;
						String ls_primerApellido;
						String ls_segundoApellido;
						String ls_razonSocial;
						String ls_entidad;
						String ls_auditoriaEntidad;
						String ls_auditoriaUsuario;

						/**Variables para inserción en SDB_ACC_RESPUESTAS_CONSULTA*/
						long ll_consecutivoConsulta;
						long   ll_consecutivoConsultaDetalle;
						String ls_idProcesoConsulta;

						ls_tipoDocumentoPersona     = null;
						ls_numDocumentoPersona      = null;
						ls_numNIT                   = null;
						ls_primerNombre             = null;
						ls_segundoNombre            = null;
						ls_primerApellido           = null;
						ls_segundoApellido          = null;
						ls_razonSocial              = null;
						ls_entidad                  = null;
						ls_auditoriaEntidad         = null;
						ls_auditoriaUsuario         = null;

						ll_consecutivoConsulta            = 0;
						ll_consecutivoConsultaDetalle     = 0;
						ls_idProcesoConsulta              = null;

						for(Map.Entry<String, String> lme_entry : lmss_criterios.entrySet())
						{
							if(lme_entry != null)
							{
								String ls_llave;

								ls_llave = lme_entry.getKey();

								if(StringUtils.isValidString(ls_llave))
								{
									switch(ls_llave)
									{
										case IdentificadoresCommon.TIPO_DOCUMENTO_PERSONA:
											ls_tipoDocumentoPersona = lme_entry.getValue();

											break;

										case IdentificadoresCommon.NUM_DOCUMENTO_PERSONA:
											ls_numDocumentoPersona = lme_entry.getValue();

											break;

										case IdentificadoresCommon.NUM_NIT:
											ls_numNIT = lme_entry.getValue();

											break;

										case IdentificadoresCommon.PRIMER_NOMBRE:
											ls_primerNombre = lme_entry.getValue();

											break;

										case IdentificadoresCommon.SEGUNDO_NOMBRE:
											ls_segundoNombre = lme_entry.getValue();

											break;

										case IdentificadoresCommon.PRIMER_APELLIDO:
											ls_primerApellido = lme_entry.getValue();

											break;

										case IdentificadoresCommon.SEGUNDO_APELLIDO:
											ls_segundoApellido = lme_entry.getValue();

											break;

										case IdentificadoresCommon.RAZON_SOCIAL:
											ls_razonSocial = lme_entry.getValue();

											break;

										case IdentificadoresCommon.ENTIDAD:
											ls_entidad = lme_entry.getValue();

											break;

										case IdentificadoresCommon.ID_AUDITORIA_ENTIDAD:
											ls_auditoriaEntidad = lme_entry.getValue();

											break;

										case IdentificadoresCommon.ID_AUDITORIA_USUARIO:
											ls_auditoriaUsuario = lme_entry.getValue();

											break;

										case IdentificadoresCommon.CONSECUTIVO_CONSULTA:
											ll_consecutivoConsulta = NumericUtils.getLong(lme_entry.getValue());

											break;

										case IdentificadoresCommon.CONSECUTIVO_CONSULTA_DETALLE:
											ll_consecutivoConsultaDetalle = NumericUtils.getLong(lme_entry.getValue());

											break;

										case IdentificadoresCommon.ID_PROCESO_CONSULTA:
											ls_idProcesoConsulta = lme_entry.getValue();

											break;

										default:
											break;
									}
								}
							}
						}

						TipoEntradaIndicePropietariosTipoDocumentoPersona lteiptdp_tipoDocumentoPersona;

						lteiptdp_tipoDocumentoPersona = null;

						{
							if(StringUtils.isValidString(ls_tipoDocumentoPersona))
							{
								switch(ls_tipoDocumentoPersona)
								{
									case TipoEntradaIndicePropietariosTipoDocumentoPersona._CC:
										lteiptdp_tipoDocumentoPersona = TipoEntradaIndicePropietariosTipoDocumentoPersona.CC;

										break;

									case TipoEntradaIndicePropietariosTipoDocumentoPersona._CE:
										lteiptdp_tipoDocumentoPersona = TipoEntradaIndicePropietariosTipoDocumentoPersona.CE;

										break;

									case TipoEntradaIndicePropietariosTipoDocumentoPersona._PA:
										lteiptdp_tipoDocumentoPersona = TipoEntradaIndicePropietariosTipoDocumentoPersona.PA;

										break;

									case TipoEntradaIndicePropietariosTipoDocumentoPersona._TI:
										lteiptdp_tipoDocumentoPersona = TipoEntradaIndicePropietariosTipoDocumentoPersona.TI;

										break;

									case TipoEntradaIndicePropietariosTipoDocumentoPersona._NUIP:
										lteiptdp_tipoDocumentoPersona = TipoEntradaIndicePropietariosTipoDocumentoPersona.NUIP;

										break;

									default:
										break;
								}
							}
						}

						{
							ltpip_return = lseippt_interface.consultar(
								    new TipoEntradaIndicePropietarios(
								        lteiptdp_tipoDocumentoPersona, ls_numDocumentoPersona, ls_numNIT,
								        ls_primerNombre, ls_segundoNombre, ls_primerApellido, ls_segundoApellido,
								        ls_razonSocial, null, null, null, null, null, null, ls_entidad,
								        ls_auditoriaEntidad, ls_auditoriaUsuario
								    )
								);

							if(ltpip_return != null)
							{
								ltpip_return.setConsecutivoConsulta(ll_consecutivoConsulta);
								ltpip_return.setConsecutivoConsultaDetalle(ll_consecutivoConsultaDetalle);
								ltpip_return.setIdProcesoConsulta(ls_idProcesoConsulta);

								{
									BigInteger lbi_codigoMensaje;

									lbi_codigoMensaje = ltpip_return.getCodMensaje();

									if((lbi_codigoMensaje != null) && (lbi_codigoMensaje.intValue() != 200))
										throw new B2BException(ltpip_return.getDescripcionMensaje());
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_INTERFACE_SERVICIOS_VALIDA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CRITERIOS_DE_CONSULTA_EXISTENTES);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultar", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return ltpip_return;
	}
}
