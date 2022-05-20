package com.bachue.prosnr01.integracion.cliente.correspondencia;

import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoDestinatario;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaCanal;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoEntradaObtenerEECorrespondenciaClasificacion;
import co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1.TipoVariable;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.parametros.ParametrosCorrespondenciaCommon;

import com.bachue.snr.prosnr01.model.integration.correspondencia.Correspondencia;

import java.util.Arrays;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ParametrosDocumento.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/12/2019
 */
public class ParametrosIDsCorrespondencia
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ParametrosIDsCorrespondencia.class);

	/**
	 * Método encargado de genenrar ID para correspondencia.
	 *
	 * @param ac_c Objeto de tipo Correspondencia que contiene parametros a listar.
	 * @return mapa de strings con los codigos a consultar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public static Correspondencia generacionIDsCorrespondencia(Correspondencia ac_c)
	    throws B2BException
	{
		try
		{
			if(ac_c != null)
			{
				{
					String ls_tmp;

					ls_tmp = ac_c.getClasificacion();

					if(StringUtils.isValidString(ls_tmp))
					{
						if(
						    !(ls_tmp.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaClasificacion._comunicacion)
							    || ls_tmp.equalsIgnoreCase(
							        TipoEntradaObtenerEECorrespondenciaClasificacion._notificacion
							    ))
						)
							throw new B2BException(ErrorKeys.ERROR_VALOR_INVALIDO);
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosCorrespondenciaCommon.CLASIFICACION;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = ac_c.getCanal();

					if(StringUtils.isValidString(ls_tmp))
					{
						if(
						    !(ls_tmp.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._fisico)
							    || ls_tmp.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._electronico)
							    || ls_tmp.equalsIgnoreCase(TipoEntradaObtenerEECorrespondenciaCanal._sms))
						)
							throw new B2BException(ErrorKeys.ERROR_VALOR_INVALIDO);
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosCorrespondenciaCommon.CANAL;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					Collection<String> lctd_tmp;

					lctd_tmp = ac_c.getDocumentos();

					if(CollectionUtils.isValidCollection(lctd_tmp))
					{
						int li_count;

						li_count = 0;

						for(String ltd_tipoDocumentoTmp : lctd_tmp)
						{
							if(ltd_tipoDocumentoTmp != null)
							{
								li_count++;

								if(!StringUtils.isValidString(ltd_tipoDocumentoTmp))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = NumericUtils.getInteger(li_count);

									throw new B2BException(ErrorKeys.ERROR_NOMBRE_DOCUMENTO_INVALIDO, loa_messageArgs);
								}
							}
						}
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosCorrespondenciaCommon.DOCUMENTOS;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					Collection<TipoDestinatario> lctd_tmp;

					lctd_tmp = ac_c.getDestinatarios();

					if(CollectionUtils.isValidCollection(lctd_tmp))
					{
						int li_countDestinatario;

						li_countDestinatario = 0;

						for(TipoDestinatario ltd_tipoDestinatario : lctd_tmp)
						{
							if(ltd_tipoDestinatario != null)
							{
								li_countDestinatario++;

								if(!StringUtils.isValidString(ltd_tipoDestinatario.getTipoDocDestinatario()))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = NumericUtils.getInteger(li_countDestinatario);

									throw new B2BException(
									    ErrorKeys.ERROR_TIPO_DOCUMENTO_DESTINATARIO_INVALIDO, loa_messageArgs
									);
								}

								if(
								    !StringUtils.isValidString(ltd_tipoDestinatario.getNumeroDocDestinatario())
									    || (NumericUtils.getLong(ltd_tipoDestinatario.getNumeroDocDestinatario()) > 0)
								)
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = NumericUtils.getInteger(li_countDestinatario);

									throw new B2BException(
									    ErrorKeys.ERROR_NUMERO_DOCUMENTO_DESTINATARIO_INVALIDO, loa_messageArgs
									);
								}

								if(!StringUtils.isValidString(ltd_tipoDestinatario.getPrimerNombreDestinatario()))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = NumericUtils.getInteger(li_countDestinatario);

									throw new B2BException(
									    ErrorKeys.ERROR_PRIMER_NOMBRE_DESTINATARIO_INVALIDO, loa_messageArgs
									);
								}

								if(!StringUtils.isValidString(ltd_tipoDestinatario.getPrimerApellidoDestinatario()))
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = NumericUtils.getInteger(li_countDestinatario);

									throw new B2BException(
									    ErrorKeys.ERROR_PRIMER_APELLIDO_DESTINATARIO_INVALIDO, loa_messageArgs
									);
								}

								{
									Collection<TipoVariable> lctv_tmp;

									lctv_tmp = Arrays.asList(ltd_tipoDestinatario.getVariables());

									if(CollectionUtils.isValidCollection(lctv_tmp))
									{
										int li_countVariables;

										li_countVariables = 0;

										for(TipoVariable ltv_tipoVariable : lctv_tmp)
										{
											if(ltv_tipoVariable != null)
											{
												li_countVariables++;

												if(!StringUtils.isValidString(ltv_tipoVariable.getLlave()))
												{
													Object[] loa_messageArgs;

													loa_messageArgs        = new String[2];
													loa_messageArgs[0]     = NumericUtils.getInteger(li_countVariables);
													loa_messageArgs[1]     = NumericUtils.getInteger(
														    li_countDestinatario
														);

													throw new B2BException(
													    ErrorKeys.ERROR_LLAVE_VARIABLE_INVALIDO, loa_messageArgs
													);
												}

												if(!StringUtils.isValidString(ltv_tipoVariable.getValor()))
												{
													Object[] loa_messageArgs;

													loa_messageArgs        = new String[2];
													loa_messageArgs[0]     = NumericUtils.getInteger(li_countVariables);
													loa_messageArgs[1]     = NumericUtils.getInteger(
														    li_countDestinatario
														);

													throw new B2BException(
													    ErrorKeys.ERROR_VALOR_VARIABLE_INVALIDO, loa_messageArgs
													);
												}
											}
										}
									}
									else
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ParametrosCorrespondenciaCommon.VARIABLES;

										throw new B2BException(
										    ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs
										);
									}
								}
							}
						}
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosCorrespondenciaCommon.DESTINATARIOS;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETRO_CORRESPONDENCIA_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generacionIDsCorrespondencia", lb2be_e);
			throw lb2be_e;
		}

		return ac_c;
	}
}
