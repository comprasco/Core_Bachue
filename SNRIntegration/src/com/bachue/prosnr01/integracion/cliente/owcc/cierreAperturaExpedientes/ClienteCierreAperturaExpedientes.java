package com.bachue.prosnr01.integracion.cliente.owcc.cierreAperturaExpedientes;

import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoEntradaAperturaTurno;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoSalidaAperturaTurno;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoSalidaAperturaTurnoCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoEntradaCierreTurno;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoSalidaCierreTurno;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoSalidaCierreTurnoCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoEntradaObtenerIndiceElectronico;
import co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoSalidaObtenerIndiceElectronico;

import co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpedienteProxy;
import co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import java.math.BigInteger;

import java.util.Map;

import java.util.Map.Entry;


/**
 * Clase que contiene todos las propiedades ClienteCierreAperturaExpedientes.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/09/2019
 */
public class ClienteCierreAperturaExpedientes
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ClienteCierreAperturaExpedientes.class);

	/**
	 * Método encargado de Re-Aperturar o cerrar un expediente en el gestor documental (OWCC).
	 *
	 * @param as_sistemaOrigen Variable de tipo <code>String</code> que contiene Componente origen (Bachue, Correspondencia, SE)
	 * @param amss_parametros Variable de tipo <code>Map<String,String></code> que contiene Metadatos asociados al expediente en el OWCC
	 * @param as_endpoint Variable de tipo <code>String</code> que contiene la dirección de punto final del servicio de destino al gestor documental.
	 * @param ab_apertura Variable de tipo <code>boolean</code> que determina si se apertura un expediente o si se cierra un expediente (<code>true</code> Apertura un expediente y <code>false</code> cierra un expediente).
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static synchronized byte[] CierreOAperturaExpediente(
	    String as_sistemaOrigen, Map<String, String> amss_parametros, String as_endpoint, String as_operacion
	)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		try
		{
			if(!StringUtils.isValidString(as_sistemaOrigen))
				throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_VALIDO);

			if(!CollectionUtils.isValidCollection(amss_parametros))
				throw new B2BException(ErrorKeys.ERROR_LISTA_PARAMETROS_NO_VALIDA);

			if(!StringUtils.isValidString(as_operacion))
				throw new B2BException(ErrorKeys.OPERACION_SOLICITADA_INVALIDA);

			boolean lb_apertura;
			boolean lb_cierre;
			boolean lb_obtenerIndice;

			lb_apertura          = as_operacion.equalsIgnoreCase(IdentificadoresCommon.APERTURA);
			lb_cierre            = as_operacion.equalsIgnoreCase(IdentificadoresCommon.CIERRE);
			lb_obtenerIndice     = as_operacion.equalsIgnoreCase(IdentificadoresCommon.OBTENER_INDICE);

			if(!lb_apertura && !lb_cierre && !lb_obtenerIndice)
				throw new B2BException(ErrorKeys.OPERACION_SOLICITADA_INVALIDA);

			if(StringUtils.isValidString(as_endpoint))
			{
				SUT_CO_CierreAperturaExpediente_PortType lsccaept_interface;
				SUT_CO_CierreAperturaExpedienteProxy     lsccaep_proxy;

				lsccaep_proxy          = new SUT_CO_CierreAperturaExpedienteProxy(as_endpoint);
				lsccaept_interface     = lsccaep_proxy.getSUT_CO_CierreAperturaExpediente_PortType();

				if(lsccaept_interface != null)
				{
					if(
					    !(as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.COMUNICACIONES_NOTIFICACIONES)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORRESPONDENCIA)
						    || as_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);

					if(lb_apertura)
					{
						TipoSalidaAperturaTurno                                                                           ltsat_responseApertura;
						co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.Parametro[] lpa_parametrosApertura;

						ltsat_responseApertura     = null;
						lpa_parametrosApertura     = null;

						int li_contadorApertura;

						li_contadorApertura        = 0;
						lpa_parametrosApertura     = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.Parametro[amss_parametros
								.size()];

						for(Entry<String, String> less_apertura : amss_parametros.entrySet())
						{
							if(less_apertura != null)
								lpa_parametrosApertura[li_contadorApertura++] = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.Parametro(
									    less_apertura.getKey(), less_apertura.getValue()
									);
						}

						ltsat_responseApertura = lsccaept_interface.aperturaTurno(
							    new TipoEntradaAperturaTurno(as_sistemaOrigen, lpa_parametrosApertura)
							);

						if(ltsat_responseApertura != null)
						{
							TipoSalidaAperturaTurnoCodigoMensaje ltsatcm_codigoMensajeApertura;

							ltsatcm_codigoMensajeApertura = ltsat_responseApertura.getCodigoMensaje();

							if((ltsatcm_codigoMensajeApertura != null))
							{
								String ls_codigo;

								ls_codigo = ltsatcm_codigoMensajeApertura.getValue();

								if(StringUtils.isValidString(ls_codigo))
								{
									Integer li_codigo;

									li_codigo = NumericUtils.getInteger(ls_codigo);

									if(NumericUtils.isValidInteger(li_codigo))
									{
										if(li_codigo.intValue() != 200)
											throw new B2BException(ltsat_responseApertura.getDescripcionMensaje());
									}
									else
										throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
					}
					else if(lb_cierre)
					{
						TipoSalidaCierreTurno                                                                           ltsct_responseCierre;
						co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[] lpa_parametrosCierre;

						ltsct_responseCierre     = null;
						lpa_parametrosCierre     = null;

						int li_contadorCierre;

						li_contadorCierre        = 0;
						lpa_parametrosCierre     = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[amss_parametros
								.size()];

						for(Entry<String, String> less_cierre : amss_parametros.entrySet())
						{
							if(less_cierre != null)
								lpa_parametrosCierre[li_contadorCierre++] = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro(
									    less_cierre.getKey(), less_cierre.getValue()
									);
						}

						ltsct_responseCierre = lsccaept_interface.cierreTurno(
							    new TipoEntradaCierreTurno(as_sistemaOrigen, lpa_parametrosCierre)
							);

						if(ltsct_responseCierre != null)
						{
							TipoSalidaCierreTurnoCodigoMensaje ltsctcm_codigoMensajeCierre;

							ltsctcm_codigoMensajeCierre = ltsct_responseCierre.getCodigoMensaje();

							if(ltsctcm_codigoMensajeCierre != null)
							{
								String ls_codigo;

								ls_codigo = ltsctcm_codigoMensajeCierre.getValue();

								if(StringUtils.isValidString(ls_codigo))
								{
									Integer li_codigo;

									li_codigo = NumericUtils.getInteger(ls_codigo);

									if(NumericUtils.isValidInteger(li_codigo))
									{
										if(li_codigo.intValue() != 200)
											throw new B2BException(ltsct_responseCierre.getDescripcionMensaje());
									}
									else
										throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
								}
								else
									throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_NO_SE_ENCONTRO_RESPUESTA_VALIDA);
					}
					else if(lb_obtenerIndice)
					{
						TipoSalidaObtenerIndiceElectronico                                                                               ltsoie_responseObtener;
						co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoParametro[] ltpa_parametrosCierre;

						ltsoie_responseObtener     = null;
						ltpa_parametrosCierre      = null;

						int li_contadorCierre;

						li_contadorCierre          = 0;
						ltpa_parametrosCierre      = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoParametro[amss_parametros
								.size()];

						for(Entry<String, String> less_cierre : amss_parametros.entrySet())
						{
							if(less_cierre != null)
								ltpa_parametrosCierre[li_contadorCierre++] = new co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoParametro(
									    less_cierre.getKey(), less_cierre.getValue()
									);
						}

						ltsoie_responseObtener = lsccaept_interface.obtenerIndiceElectronico(
							    new TipoEntradaObtenerIndiceElectronico(as_sistemaOrigen, ltpa_parametrosCierre)
							);

						if(ltsoie_responseObtener != null)
						{
							BigInteger lbi_codigoMensajeApertura;

							lbi_codigoMensajeApertura = ltsoie_responseObtener.getCodigoMensaje();

							if((lbi_codigoMensajeApertura != null) && (lbi_codigoMensajeApertura.intValue() != 200))
								throw new B2BException(ltsoie_responseObtener.getDescripcionMensaje());
							else
								lba_archivo = ltsoie_responseObtener.getArchivo();
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
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("CierreOAperturaExpediente", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("CierreOAperturaExpediente", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_archivo;
	}
}
