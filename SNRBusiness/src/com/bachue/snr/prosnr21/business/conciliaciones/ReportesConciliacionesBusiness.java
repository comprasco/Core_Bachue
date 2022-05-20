package com.bachue.snr.prosnr21.business.conciliaciones;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.utils.FTPUtils;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.EstadoCommon;
import com.bachue.snr.prosnr21.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr21.common.constants.PeriodicidadCommon;

import com.bachue.snr.prosnr21.dao.png.RPTProgramaDAO;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import com.jcraft.jsch.ChannelSftp;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase para el manejo del negocio de reportes conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ReportesConciliacionesBusiness extends EnvioGestorDocumentalConciliacionesBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConciliacionesBusiness.class);

	/**
	 * Generar reportes.
	 *
	 * @param as_idReporteConciliacion the as id reporte conciliacion
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @throws Exception the exception
	 */
	public synchronized void generarReportes(String as_idReporteConciliacion, String as_userId, String as_remoteIp)
	    throws Exception
	{
		DAOManager  ldm_manager;
		RPTPrograma lrptp_reporte;

		ldm_manager       = DaoManagerFactory.getDAOManagerConciliacion();
		lrptp_reporte     = null;

		if(StringUtils.isValidString(as_idReporteConciliacion))
		{
			try
			{
				lrptp_reporte = DaoCreator.getRPTProgramaDAO(ldm_manager).findById(as_idReporteConciliacion);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarReportes", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		generarReportes(lrptp_reporte, as_userId, as_remoteIp, null);
	}

	/**
	 * Generar reportes.
	 *
	 * @param arptp_reporte the arptp reporte
	 * @param as_userId the as user id
	 * @param as_remoteIp the as remote ip
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 * @throws ParseException the parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public synchronized int generarReportes(
	    RPTPrograma arptp_reporte, String as_userId, String as_remoteIp, Calendar ac_fecha
	)
	    throws B2BException, ParseException, IOException
	{
		int    li_return;
		String ls_detalle;

		li_return      = -1;
		ls_detalle     = null;

		if(arptp_reporte != null)
		{
			boolean lb_alreadyProcessing;
			String  ls_idConstanteBloqueo;

			if(!StringUtils.isValidString(as_userId))
				throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_VALIDO);

			{
				StringBuilder lsb_idConstanteBloqueo;

				lsb_idConstanteBloqueo = new StringBuilder(ConstanteCommon.JOB_REPORTES_CONCILIACIONES_BLOQUEO);

				lsb_idConstanteBloqueo.append("_");
				lsb_idConstanteBloqueo.append(arptp_reporte.getIdReporte());

				ls_idConstanteBloqueo = lsb_idConstanteBloqueo.toString();
			}

			{
				DAOManager ldm_processing;

				ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

				ldm_processing.setAutoCommit(true);

				try
				{
					ConstantesDAO lcd_constant;

					lcd_constant             = DaoCreator.getConstantesDAO(ldm_processing);
					lb_alreadyProcessing     = BooleanUtils.getBooleanValue(
						    obtenerConstanteCaracter(lcd_constant, ls_idConstanteBloqueo)
						);

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_idConstanteBloqueo, EstadoCommon.S, as_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("cargarArchivos", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}

			{
				DAOManager ldm_manager;
				DAOManager ldm_bitacora;

				ldm_manager      = DaoManagerFactory.getDAOManagerConciliacion();
				ldm_bitacora     = DaoManagerFactory.getDAOManager();

				try
				{
					if(!lb_alreadyProcessing)
					{
						boolean lb_activo;

						lb_activo = BooleanUtils.getBooleanValue(arptp_reporte.getActivo());

						if(lb_activo)
						{
							String ls_query;
							String ls_proc;

							ls_query     = arptp_reporte.getSqlEjecucion();
							ls_proc      = arptp_reporte.getProcedimiento();

							if(StringUtils.isValidString(ls_query))
							{
								ConstantesDAO                   lcd_constante;
								ChannelSftp                     lcs_canal;
								String                          ls_rutaBase;
								Map<String, Collection<String>> lmscs_data;
								String                          ls_solicitaCTA;
								String                          ls_nombrePrograma;
								String                          ls_fechaInicial;
								String                          ls_fechaFinal;
								SimpleDateFormat                lsdf_formato;

								lcd_constante         = DaoCreator.getConstantesDAO(ldm_manager);
								lmscs_data            = new HashMap<String, Collection<String>>(1);
								ls_solicitaCTA        = arptp_reporte.getSolicitaCTA();
								ls_nombrePrograma     = arptp_reporte.getTiposDocumentales();
								lsdf_formato          = new SimpleDateFormat("dd/MM/YYYY");
								lcs_canal             = abrirConexionFTP(
									    lcd_constante, null, ConstanteCommon.SERVIDOR_SFTP_CLAVE,
									    ConstanteCommon.SERVIDOR_SFTP_USUARIO, ConstanteCommon.SERVIDOR_SFTP_IP,
									    ConstanteCommon.SERVIDOR_SFTP_PUERTO
									);
								ls_rutaBase           = obtenerConstanteCaracter(
									    lcd_constante, ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE
									);

								if(ac_fecha == null)
									ac_fecha = Calendar.getInstance();

								{
									SimpleDateFormat lsdf_formato2;

									lsdf_formato2 = new SimpleDateFormat("YYYY/MM/dd");
									ls_rutaBase += (lsdf_formato2.format(ac_fecha.getTime()) + "/Reportes/");
								}

								ls_fechaInicial     = lsdf_formato.format(ac_fecha.getTime());
								ls_fechaFinal       = lsdf_formato.format(ac_fecha.getTime());

								{
									String ls_idPeriodicidad;

									ls_idPeriodicidad = arptp_reporte.getIdPeriodicidad();

									if(
									    StringUtils.isValidString(ls_idPeriodicidad)
										    && ls_idPeriodicidad.equalsIgnoreCase(PeriodicidadCommon.MENSUAL)
									)
									{
										StringBuilder lsb_fechaBase;
										String        ls_fechaBase;

										lsb_fechaBase = new StringBuilder();

										lsb_fechaBase.append(ac_fecha.get(Calendar.YEAR));
										lsb_fechaBase.append(IdentificadoresCommon.SIMBOLO_GUION);
										lsb_fechaBase.append(ac_fecha.get(Calendar.MONTH) + 1);
										lsb_fechaBase.append(IdentificadoresCommon.SIMBOLO_GUION);

										ls_fechaBase        = lsb_fechaBase.toString();
										ls_fechaInicial     = ls_fechaBase
											+ ac_fecha.getActualMinimum(Calendar.DAY_OF_MONTH);
										ls_fechaFinal       = ls_fechaBase
											+ ac_fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
									}
								}

								if(BooleanUtils.getBooleanValue(ls_solicitaCTA))
								{
									Collection<EntidadRecaudadoraCuenta> lcerc_cuentas;

									lcerc_cuentas = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager)
											                      .findAllActive();

									if(CollectionUtils.isValidCollection(lcerc_cuentas))
									{
										for(EntidadRecaudadoraCuenta lerc_cuenta : lcerc_cuentas)
										{
											if(lerc_cuenta != null)
											{
												String ls_nombreEntidad;
												String ls_numeroCuenta;
												String ls_idEntidad;

												ls_nombreEntidad     = lerc_cuenta.getNombreEntidadRecaudadora();
												ls_numeroCuenta      = lerc_cuenta.getNumeroCuenta();
												ls_idEntidad         = lerc_cuenta.getIdEntidadRecaudadora();

												if(
												    StringUtils.isValidString(ls_nombreEntidad)
													    && StringUtils.isValidString(ls_numeroCuenta)
													    && StringUtils.isValidString(ls_idEntidad)
												)
												{
													Collection<String> lcs_dataReporte;

													lcs_dataReporte = DaoCreator.getUtilDAO(ldm_manager)
															                        .queryReportes(
															    ls_query, ls_fechaInicial, ls_fechaFinal,
															    lerc_cuenta.getIdCuenta(), ls_proc
															);

													lmscs_data.put(
													    generarNombreReporte(
													        ls_nombrePrograma, ls_idEntidad, ls_nombreEntidad,
													        ls_numeroCuenta, ac_fecha
													    ), lcs_dataReporte
													);
												}
											}
										}
									}
								}
								else
								{
									Collection<String> lcs_dataReporte;

									lcs_dataReporte = DaoCreator.getUtilDAO(ldm_manager)
											                        .queryReportes(
											    ls_query, ls_fechaInicial, ls_fechaFinal, null, ls_proc
											);

									lmscs_data.put(generarNombreReporte(ls_nombrePrograma, ac_fecha), lcs_dataReporte);
								}

								if(CollectionUtils.isValidCollection(lmscs_data))
								{
									BitacoraProcesoDAO lbpd_bitacora;
									String             ls_endpoint;

									lbpd_bitacora     = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
									ls_endpoint       = DaoCreator.getConstantesDAO(ldm_manager)
											                          .findString(
											    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
											);

									ldm_bitacora.setAutoCommit(true);

									for(Map.Entry<String, Collection<String>> lme_entry : lmscs_data.entrySet())
									{
										Collection<String> lcs_dataReporte;
										String             ls_nombreReporte;

										lcs_dataReporte      = lme_entry.getValue();
										ls_nombreReporte     = lme_entry.getKey();

										if(
										    CollectionUtils.isValidCollection(lcs_dataReporte)
											    && StringUtils.isValidString(ls_nombreReporte)
										)
										{
											Map<String, String> lmss_extensiones;

											lmss_extensiones = generarCodigos(
												    ConstanteCommon.TIPOS_EXTENSION_ARCHIVO_REPORTES, ldm_manager
												);

											if(CollectionUtils.isValidCollection(lmss_extensiones))
											{
												String ls_tiposArchivo;

												ls_tiposArchivo = arptp_reporte.getTipoArchivo();

												if(StringUtils.isValidString(ls_tiposArchivo))
												{
													Map<String, String> lmss_tiposArchivo;

													lmss_tiposArchivo = ListadoCodigosConstantes.generarCodigos(
														    ls_tiposArchivo
														);

													if(
													    CollectionUtils.isValidCollection(lmss_tiposArchivo)
														    && StringUtils.isValidString(ls_nombreReporte)
													)
													{
														byte[] lba_excel;
														byte[] lba_csv;
														byte[] lba_txt;

														lba_excel     = null;
														lba_csv       = null;
														lba_txt       = null;

														for(Map.Entry<String, String> lmess_entry : lmss_tiposArchivo
															    .entrySet())
														{
															String ls_tipoArchivo;

															ls_tipoArchivo = lmess_entry.getKey();

															if(
															    StringUtils.isValidString(ls_tipoArchivo)
																    && lmss_extensiones.containsKey(ls_tipoArchivo)
															)
															{
																if(
																    ls_tipoArchivo.equalsIgnoreCase(
																	        ExtensionCommon.EXCEL
																	    )
																)
																	lba_excel = getXSLFromStringCollection(
																		    lcs_dataReporte
																		);
																else if(
																    ls_tipoArchivo.equalsIgnoreCase(
																	        ExtensionCommon.CSV
																	    )
																)
																	lba_csv = getTXTFromStringCollection(
																		    lcs_dataReporte
																		);
																else if(
																    ls_tipoArchivo.equalsIgnoreCase(
																	        ExtensionCommon.TEXTO_PLANO
																	    )
																)
																	lba_txt = getTXTFromStringCollection(
																		    lcs_dataReporte
																		);
															}
														}

														if(lba_excel != null)
														{
															StringBuilder lsb_ruta;

															lsb_ruta = new StringBuilder(ls_rutaBase);

															guardarDocumento(
															    ls_nombreReporte, ExtensionCommon.XLSX, lba_excel,
															    as_userId, as_remoteIp, ldm_manager, lbpd_bitacora,
															    ls_endpoint, null
															);

															lsb_ruta.append(ls_nombreReporte);
															lsb_ruta.append(ExtensionCommon.XLSX_PUNTO);

															guardarArchivoFTP(
															    lcs_canal, lba_excel, lsb_ruta.toString(), ls_rutaBase
															);
														}

														if(lba_csv != null)
														{
															StringBuilder lsb_ruta;

															lsb_ruta = new StringBuilder(ls_rutaBase);

															guardarDocumento(
															    ls_nombreReporte, ExtensionCommon.CSV, lba_csv,
															    as_userId, as_remoteIp, ldm_manager, lbpd_bitacora,
															    ls_endpoint, null
															);

															lsb_ruta.append(ls_nombreReporte);
															lsb_ruta.append(ExtensionCommon.CSV_PUNTO);

															guardarArchivoFTP(
															    lcs_canal, lba_csv, lsb_ruta.toString(), ls_rutaBase
															);
														}

														if(lba_txt != null)
														{
															StringBuilder lsb_ruta;

															lsb_ruta = new StringBuilder(ls_rutaBase);

															guardarDocumento(
															    ls_nombreReporte, ExtensionCommon.TEXTO_PLANO, lba_txt,
															    as_userId, as_remoteIp, ldm_manager, lbpd_bitacora,
															    ls_endpoint, null
															);

															lsb_ruta.append(ls_nombreReporte);
															lsb_ruta.append(ExtensionCommon.TXT_PUNTO);

															guardarArchivoFTP(
															    lcs_canal, lba_txt, lsb_ruta.toString(), ls_rutaBase
															);
														}
													}
												}
											}
										}
									}
								}
							}
							else
								throw new B2BException("Error");
						}
						else
							throw new B2BException("Error");
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("generarReportes", lb2be_e);

					ls_detalle = lb2be_e.getMessage();

					throw lb2be_e;
				}
				finally
				{
					if(!lb_alreadyProcessing)
					{
						DAOManager ldm_processing;

						ldm_processing = DaoManagerFactory.getDAOManagerConciliacion();

						ldm_processing.setAutoCommit(true);

						try
						{
							DaoCreator.getConstantesDAO(ldm_processing)
								          .updateString(ls_idConstanteBloqueo, EstadoCommon.N, as_userId);
						}
						catch(B2BException lb2be_e)
						{
							ldm_processing.setRollbackOnly();

							clh_LOGGER.error("generarReportes", lb2be_e);

							throw lb2be_e;
						}
						finally
						{
							ldm_processing.close();
						}
					}

					{
						RPTProgramaDAO lrptpd_DAO;

						lrptpd_DAO = DaoCreator.getRPTProgramaDAO(ldm_manager);

						arptp_reporte.setResultado(NumericUtils.getLong(li_return));
						arptp_reporte.setResultadoDetalle(ls_detalle);
						arptp_reporte.setFechaUltimaEjecucion(new Date());
						arptp_reporte.setIdUsuarioModificacion(as_userId);
						arptp_reporte.setIpModificacion(as_remoteIp);

						lrptpd_DAO.insertOrUpdate(arptp_reporte, false);
					}

					if(ldm_manager != null)
						ldm_manager.close();

					ldm_bitacora.close();
				}
			}
		}

		return li_return;
	}

	/**
	 * Generar nombre reporte.
	 *
	 * @param as_tipoDocumental the as tipo documental
	 * @return the string
	 */
	private String generarNombreReporte(String as_tipoDocumental, Calendar ac_fecha)
	{
		return generarNombreReporte(as_tipoDocumental, null, null, null, ac_fecha);
	}

	/**
	 * Generar nombre reporte.
	 *
	 * @param as_tipoDocumental the as tipo documental
	 * @param as_idEntidad the as id entidad
	 * @param as_NombreBanco the as nombre banco
	 * @param as_numeroCuenta the as numero cuenta
	 * @return the string
	 */
	private String generarNombreReporte(
	    String as_tipoDocumental, String as_idEntidad, String as_NombreBanco, String as_numeroCuenta, Calendar ac_fecha
	)
	{
		SimpleDateFormat lsdf_formato;
		String           ls_fechaString;
		StringBuilder    lsb_return;

		lsdf_formato       = new SimpleDateFormat("ddMMYYYY");
		ls_fechaString     = lsdf_formato.format(ac_fecha.getTime());
		lsb_return         = new StringBuilder(as_tipoDocumental);

		if(StringUtils.isValidString(as_idEntidad))
		{
			lsb_return.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
			lsb_return.append(as_idEntidad);
		}

		if(StringUtils.isValidString(as_NombreBanco))
		{
			lsb_return.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
			lsb_return.append(as_NombreBanco);
		}

		if(StringUtils.isValidString(as_numeroCuenta))
		{
			lsb_return.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
			lsb_return.append(as_numeroCuenta);
		}

		lsb_return.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
		lsb_return.append(ls_fechaString);

		return lsb_return.toString();
	}

	/**
	 * Guardar archivo FTP.
	 *
	 * @param acs_canal the acs canal
	 * @param ab_archivo the ab archivo
	 * @param as_rutaArchivo the as ruta
	 * @throws B2BException the b 2 B exception
	 */
	private void guardarArchivoFTP(ChannelSftp acs_canal, byte[] ab_archivo, String as_rutaArchivo, String as_rutaBase)
	    throws B2BException
	{
		try
		{
			acs_canal.cd(as_rutaArchivo);
		}
		catch(Exception le_e)
		{
			FTPUtils.crearDirectorioFTP(acs_canal, as_rutaBase, ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO);
		}

		FTPUtils.cargarArchivoFTP(acs_canal, ab_archivo, as_rutaArchivo);
	}
}
