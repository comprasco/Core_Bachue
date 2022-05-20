package com.bachue.prosnr01.integracion.cliente.owcc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.parametros.ParametrosOWCCCommon;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades ParametrosDocumento.
 *
 * @author  Julian David Vaca
 * Fecha de Creacion: 16/09/2019
 */
public class ParametrosDocumento
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ParametrosDocumento.class);

	/**
	 * Método encargado de listar los parametros para la apertura de un expediente en el OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @return mapa de strings con los codigos a consultar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosAperturaExpedienteOWCC(DocumentoOWCC abdo_OWCC)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new LinkedHashMap<String, String>();

		try
		{
			if(abdo_OWCC != null)
			{
				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getIdOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.CODIGO_ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTurno();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TURNO, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.TURNO;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getCodigoEtapa();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ETAPA, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.CODIGO_ETAPA;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNir();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NIR, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.NIR;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETRO_DOCUMENTOOWCC_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarParametrosAperturaExpedienteOWCC", lb2be_e);
			throw lb2be_e;
		}

		if(!CollectionUtils.isValidCollection(lmss_mss))
			lmss_mss = null;

		return lmss_mss;
	}

	/**
	 *  Método encargado de listar los parametros de busqueda del OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC  Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @param ab_busqueda  Objeto de tipo boolean que define la operacion a realizar (<code>true</code> si es busqueda o <code>false</code> si se va a enviar).
	 * @return mapa de String,String con los codigos listados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosBusquedaOWCC(DocumentoOWCC abdo_OWCC, boolean ab_busqueda)
	    throws B2BException
	{
		return generarParametrosBusquedaOWCC(abdo_OWCC, ab_busqueda, false);
	}

	/**
	 *  Método encargado de listar los parametros de busqueda del OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC  Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @param ab_busqueda  Objeto de tipo boolean que define la operacion a realizar (<code>true</code> si es busqueda o <code>false</code> si se va a enviar).
	 * @return mapa de String,String con los codigos listados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosBusquedaOWCC(
	    DocumentoOWCC abdo_OWCC, boolean ab_busqueda, boolean ab_conciliaciones
	)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new LinkedHashMap<String, String>();

		try
		{
			if(abdo_OWCC != null)
			{
				if(!ab_conciliaciones)
				{
					if(ab_busqueda)
					{
						{
							String ls_tmp;

							ls_tmp = abdo_OWCC.getOperador();

							if(StringUtils.isValidString(ls_tmp))
								lmss_mss.put(ParametrosOWCCCommon.OPERADOR, ls_tmp);
						}

						{
							String ls_tmp;

							ls_tmp     = abdo_OWCC.getiD();
							//TODO Eliminar esta linea una vez de confirme correccion del OWCC en QA 
							ls_tmp     = null;

							if(StringUtils.isValidString(ls_tmp))
								lmss_mss.put(ParametrosOWCCCommon.DID, ls_tmp);
						}

						{
							String ls_tmp;

							ls_tmp = abdo_OWCC.getDocName();

							if(StringUtils.isValidString(ls_tmp))
								lmss_mss.put(ParametrosOWCCCommon.NOMBRE_DOCUMENTO, ls_tmp);
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getDocType();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.TIPO_DOCUMENTAL, ls_tmp);
						else
						{
							if(!ab_busqueda)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ParametrosOWCCCommon.TIPO_DOCUMENTAL;

								throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNombreOrip();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.ORIP, ls_tmp);
						else
						{
							if(!ab_busqueda)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

								throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNir();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NIR, ls_tmp);
						else
						{
							if(!ab_busqueda)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ParametrosOWCCCommon.NIR;

								throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getIdOrip();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_tmp);
						else
						{
							if(!ab_busqueda)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ParametrosOWCCCommon.CODIGO_ORIP;

								throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getTurno();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.TURNO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getDocRegistrado();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.DOCUMENTO_REGISTRADO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNumeroDoc();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NUMERO_DOCUMENTO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getEntidadOrigen();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.ENTIDAD_ORIGEN, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNombrePais();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.PAIS, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNombreDepartamento();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.DEPARTAMENTO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNombreMunicipio();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.MUNICIPIO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getMatriculas();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.MATRICULA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getAnotacion();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.ANOTACION, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNumeroPaginas();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NUMERO_PAGINA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNombresIntervinientes();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NOMBRE_INTERVINIENTE, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getDocumentosIntervinientes();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.IDENTIFICACION_INTERVINIENTE, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(
							    abdo_OWCC.getFechaPublicacion(), FormatoFechaCommon.DIA_MES_ANIO
							);

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.FECHA_PUBLICACION, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getFechaVigencia(), FormatoFechaCommon.DIA_MES_ANIO);

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.FECHA_VIGENCIA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getTipoOficina();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.TIPO_DE_OFICINA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getTipoActo();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.ACTO_NATURALEZA_JURIDICA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getProceso();

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.PROCESO, ls_tmp);
						else
						{
							if(!ab_busqueda)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ParametrosOWCCCommon.PROCESO;

								throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
							}
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getNirVinculado();

						if(StringUtils.isValidString(ls_tmp))
						{
							if(ab_busqueda)
							{
								Collection<String> lcs_nirVinculados;

								lcs_nirVinculados = ListadoCodigosConstantes.generarCodigosCollection(ls_tmp, ";");

								if(CollectionUtils.isValidCollection(lcs_nirVinculados))
								{
									for(String ls_iterador : lcs_nirVinculados)
									{
										if(StringUtils.isValidString(ls_iterador))
											lmss_mss.put(ParametrosOWCCCommon.NIR_VINCULADO, ls_iterador);
									}
								}
							}
							else
								lmss_mss.put(ParametrosOWCCCommon.NIR_VINCULADO, ls_tmp);
						}
					}

					{
						String ls_tmp;

						ls_tmp = abdo_OWCC.getTurnoVinculado();

						if(StringUtils.isValidString(ls_tmp))
						{
							if(ab_busqueda)
							{
								Collection<String> lcs_turnosVinculados;

								lcs_turnosVinculados = ListadoCodigosConstantes.generarCodigosCollection(ls_tmp, ";");

								if(CollectionUtils.isValidCollection(lcs_turnosVinculados))
								{
									for(String ls_iterador : lcs_turnosVinculados)
									{
										if(StringUtils.isValidString(ls_iterador))
											lmss_mss.put(ParametrosOWCCCommon.TURNO_VINCULADO, ls_iterador);
									}
								}
							}
							else
								lmss_mss.put(ParametrosOWCCCommon.TURNO_VINCULADO, ls_tmp);
						}
					}
				}
				else
				{
					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getRutaServidorFTP());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.FTP, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getComprobante());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.COMPROBANTE, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getNumeroSIIF());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NUMERO_SIIF, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getVersion());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.VERSION, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getNumeroCuenta());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NUMERO_CUENTA, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getCodigoBanco());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.CODIGO_BANCO, ls_tmp);
					}

					{
						String ls_tmp;

						ls_tmp = StringUtils.getString(abdo_OWCC.getNombreBanco());

						if(StringUtils.isValidString(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.NOMBRE_BANCO, ls_tmp);
					}

					{
						Long ls_tmp;

						ls_tmp = abdo_OWCC.getPeriodoCorte();

						if(NumericUtils.isValidLong(ls_tmp))
							lmss_mss.put(ParametrosOWCCCommon.PERIODO_CORTE, StringUtils.getString(ls_tmp));
					}
				}

				{
					String ls_tmp;

					ls_tmp = StringUtils.getString(
						    abdo_OWCC.getFechaDocumento(), FormatoFechaCommon.ANIO_MES_DIA_HORA_MINUTO_SEGUNDO_OWCC
						);

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.FECHA_DEL_DOCUMENTO, ls_tmp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarParametrosBusquedaOWCC", lb2be_e);
			throw lb2be_e;
		}

		if(!CollectionUtils.isValidCollection(lmss_mss))
			lmss_mss = null;

		return lmss_mss;
	}

	/**
	 *  Método encargado de listar los parametros para el cierre de un expediente en el OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @return mapa de strings con los codigos a consultar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosCierreExpedienteOWCC(DocumentoOWCC abdo_OWCC)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new LinkedHashMap<String, String>();

		try
		{
			if(abdo_OWCC != null)
			{
				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getIdOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTurno();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TURNO, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getCodigoEtapa();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ETAPA, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarParametrosCierreExpedienteOWCC", lb2be_e);
			throw lb2be_e;
		}

		if(!CollectionUtils.isValidCollection(lmss_mss))
			lmss_mss = null;

		return lmss_mss;
	}

	/**
	 * Método encargado de crear turno en el OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @return mapa de strings con los codigos a consultar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosCrearTurnoOWCC(DocumentoOWCC abdo_OWCC)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new LinkedHashMap<String, String>();

		try
		{
			if(abdo_OWCC != null)
			{
				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getIdOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.CODIGO_ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTurno();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TURNO, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.TURNO;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNir();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NIR, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.NIR;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getMatriculas();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.MATRICULA, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.MATRICULA;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarParametrosCrearTurnoOWCC", lb2be_e);
			throw lb2be_e;
		}

		if(!CollectionUtils.isValidCollection(lmss_mss))
			lmss_mss = null;

		return lmss_mss;
	}

	/**
	 *  Método encargado de listar los parametros de Relacionar Documentos del OWCC (Gestor Documental).
	 *
	 * @param abdo_OWCC  Objeto de tipo DocumentoOWCC que contiene parametros a listar.
	 * @return Map<String, String> con los codigos listados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public static Map<String, String> generarParametrosRelacionDocumentoOWCC(DocumentoOWCC abdo_OWCC)
	    throws B2BException
	{
		Map<String, String> lmss_mss;

		lmss_mss = new LinkedHashMap<String, String>();

		try
		{
			if(abdo_OWCC != null)
			{
				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getDocType();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TIPO_DOCUMENTAL, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.TIPO_DOCUMENTAL;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNir();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NIR, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.NIR;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getIdOrip();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_tmp);
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = ParametrosOWCCCommon.CODIGO_ORIP;

						throw new B2BException(ErrorKeys.ERROR_CAMPO_CRITERIO_OBLIGATORIO, loa_messageArgs);
					}
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTurno();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TURNO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getDocRegistrado();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.DOCUMENTO_REGISTRADO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNumeroDoc();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NUMERO_DOCUMENTO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = StringUtils.getString(abdo_OWCC.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO);

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.FECHA_DEL_DOCUMENTO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getEntidadOrigen();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ENTIDAD_ORIGEN, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombrePais();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.PAIS, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreDepartamento();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.DEPARTAMENTO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombreMunicipio();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.MUNICIPIO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getMatriculas();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.MATRICULA, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getAnotacion();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ANOTACION, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNumeroPaginas();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NUMERO_PAGINA, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNombresIntervinientes();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NOMBRE_INTERVINIENTE, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getDocumentosIntervinientes();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.IDENTIFICACION_INTERVINIENTE, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = StringUtils.getString(abdo_OWCC.getFechaPublicacion(), FormatoFechaCommon.DIA_MES_ANIO);

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.FECHA_PUBLICACION, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = StringUtils.getString(abdo_OWCC.getFechaVigencia(), FormatoFechaCommon.DIA_MES_ANIO);

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.FECHA_VIGENCIA, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTipoOficina();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.TIPO_DE_OFICINA, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getTipoActo();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.ACTO_NATURALEZA_JURIDICA, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getProceso();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.PROCESO, ls_tmp);
				}

				{
					String ls_tmp;

					ls_tmp = abdo_OWCC.getNirVinculado();

					if(StringUtils.isValidString(ls_tmp))
						lmss_mss.put(ParametrosOWCCCommon.NIR_VINCULADO, ls_tmp);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarParametrosRelacionDocumentoOWCC", lb2be_e);
			throw lb2be_e;
		}

		if(!CollectionUtils.isValidCollection(lmss_mss))
			lmss_mss = null;

		return lmss_mss;
	}
}
