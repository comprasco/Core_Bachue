package com.bachue.snr.prosnr01.business.crps;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.utils.FTPUtils;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr21.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr21.common.constants.ErrorKeys;

import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraConciliacionDAO;
import com.bachue.snr.prosnr21.dao.png.EntidadRecaudadoraCuentaDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashCabeceraDAO;
import com.bachue.snr.prosnr21.dao.png.MulticashDetalleDAO;

import com.bachue.snr.prosnr21.model.pgn.CuentaAnalista;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.MulticashCabecera;
import com.bachue.snr.prosnr21.model.pgn.MulticashDetalle;

import com.jcraft.jsch.ChannelSftp;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase la generación de archivos MultiCash
 *
 * @author Gabriel Arias
 */
public class MultiCashBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MultiCashBusiness.class);

	public synchronized void generarMultiCash(Date ad_fecha, String as_idCuenta, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager  ldm_managerSnr;
		DAOManager  ldm_manager;
		ChannelSftp lcs_canal;

		ldm_managerSnr     = DaoManagerFactory.getDAOManager();
		ldm_manager        = DaoManagerFactory.getDAOManagerConciliacion();
		lcs_canal          = null;

		try
		{
			if(StringUtils.isValidString(as_idCuenta))
			{
				EntidadRecaudadoraCuenta    lerc_cuentaBancaria;
				EntidadRecaudadoraCuentaDAO lenrcd_DAO;

				lenrcd_DAO              = DaoCreator.getEntidadRecaudadoraCuentaDAO(ldm_manager);
				lerc_cuentaBancaria     = lenrcd_DAO.findById(as_idCuenta);

				if(lerc_cuentaBancaria != null)
				{
					String ls_idEntidadRecaudadora;

					ls_idEntidadRecaudadora = lerc_cuentaBancaria.getIdEntidadRecaudadora();

					if(StringUtils.isValidString(ls_idEntidadRecaudadora))
					{
						EntidadRecaudadoraConciliacionDAO lercd_DAO;
						EntidadRecaudadoraConciliacion    lerc_entidad;

						lercd_DAO        = DaoCreator.getEntidadRecaudadoraConciliacionDAO(ldm_manager);
						lerc_entidad     = lercd_DAO.findById(ls_idEntidadRecaudadora);

						if(lerc_entidad != null)
						{
							Collection<MulticashCabecera> lcmc_data;
							MulticashCabeceraDAO          lmcd_DAO;
							Double                        ld_saldoInicial;
							String                        ls_numeroCuenta;
							String                        ls_claveBanco;

							lcmc_data           = null;
							ld_saldoInicial     = null;
							lmcd_DAO            = DaoCreator.getMulticashCabeceraDAO(ldm_managerSnr);
							ls_numeroCuenta     = lerc_cuentaBancaria.getNumeroCuenta();
							ls_claveBanco       = lerc_entidad.getCodigoEntidadRecaudadora();
							lcmc_data           = lmcd_DAO.findAllVista(ad_fecha, ls_claveBanco, ls_numeroCuenta);

							{
								CuentaAnalista lca_cuentaAnalista;

								lca_cuentaAnalista = DaoCreator.getCuentaAnalistaDAO(ldm_manager)
										                           .findByIdCuenta(as_idCuenta);

								if(lca_cuentaAnalista != null)
									ld_saldoInicial = lca_cuentaAnalista.getSaldoInicial();
							}

							if(!CollectionUtils.isValidCollection(lcmc_data))
								lmcd_DAO.crearMulticash(
								    ls_idEntidadRecaudadora, ls_numeroCuenta, lerc_cuentaBancaria.getTipoCuenta(),
								    ad_fecha, ld_saldoInicial
								);

							lcmc_data = lmcd_DAO.findAllVista(ad_fecha, ls_claveBanco, ls_numeroCuenta);

							if(CollectionUtils.isValidCollection(lcmc_data))
							{
								ConstantesDAO       lcd_DAO;
								String              ls_muPlantillaRuta;
								String              ls_rutaBase;
								Map<String, String> lmss_catalogo;
								MulticashDetalleDAO lmdd_DAO;

								lmss_catalogo          = new HashMap<String, String>();
								lcd_DAO                = DaoCreator.getConstantesDAO(ldm_manager);
								lmdd_DAO               = DaoCreator.getMulticashDetalleDAO(ldm_managerSnr);
								ls_muPlantillaRuta     = obtenerConstanteCaracter(
									    lcd_DAO, ConstanteCommon.SERVIDOR_SFTP_RUTA_MULTICASH
									);
								ls_rutaBase            = obtenerConstanteCaracter(
									    lcd_DAO, ConstanteCommon.SERVIDOR_SFTP_RUTA_BASE
									);
								lcs_canal              = abrirConexionFTP(
									    lcd_DAO, null, ConstanteCommon.SERVIDOR_SFTP_CLAVE,
									    ConstanteCommon.SERVIDOR_SFTP_USUARIO, ConstanteCommon.SERVIDOR_SFTP_IP,
									    ConstanteCommon.SERVIDOR_SFTP_PUERTO
									);

								lmss_catalogo.put(
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_CABECERA,
								    obtenerConstanteCaracter(
								        lcd_DAO,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_CABECERA
								    )
								);
								lmss_catalogo.put(
								    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_DETALLE,
								    obtenerConstanteCaracter(
								        lcd_DAO,
								        com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_DETALLE
								    )
								);

								for(MulticashCabecera lmc_iterador : lcmc_data)
								{
									if(lmc_iterador != null)
									{
										ls_rutaBase = obtenerRutaCompleta(
											    ls_rutaBase,
											    resolver(
											        ls_muPlantillaRuta, lerc_entidad, lerc_cuentaBancaria, ad_fecha
											    )
											);

										FTPUtils.crearDirectorioFTP(
										    lcs_canal, ls_rutaBase,
										    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_SEPARADOR_DIRECTORIO
										);

										cargarArchivoFTP(
										    lcs_canal, lmss_catalogo, lmc_iterador.getFila(), ad_fecha,
										    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_CABECERA,
										    ls_rutaBase, lerc_entidad, lerc_cuentaBancaria
										);

										Collection<MulticashDetalle> lcmd_data;

										lcmd_data = lmdd_DAO.findVista(ad_fecha, ls_claveBanco, ls_numeroCuenta);

										if(CollectionUtils.isValidCollection(lcmd_data))
										{
											StringBuilder lsb_sb;

											lsb_sb = new StringBuilder();

											for(MulticashDetalle lmd_iterador : lcmd_data)
											{
												lsb_sb.append(lmd_iterador.getFila());
												lsb_sb.append("\n");
											}

											cargarArchivoFTP(
											    lcs_canal, lmss_catalogo, lsb_sb.toString(), ad_fecha,
											    com.bachue.snr.prosnr21.common.constants.ConstanteCommon.SERVIDOR_SFTP_ARCHIVO_MULTICASH_DETALLE,
											    ls_rutaBase, lerc_entidad, lerc_cuentaBancaria
											);
										}
									}
								}
							}
							else
								throw new B2BException("No hay información para generar el archivo");
						}
						else
							throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_managerSnr.setRollbackOnly();
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarMultiCash", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_managerSnr.close();
			ldm_manager.close();

			FTPUtils.cerrarConexionFTP(lcs_canal);
		}
	}

	private void cargarArchivoFTP(
	    ChannelSftp acs_canal, Map<String, String> amss_catalogo, String as_archivo, Date ad_fecha,
	    String as_idPlantilla, String as_rutaBase, EntidadRecaudadoraConciliacion aerc_entidad,
	    EntidadRecaudadoraCuenta aerc_cuenta
	)
	    throws B2BException
	{
		String ls_nombreArchivo;
		String ls_rutaArchivo;
		String ls_plantillaNombreArchivo;

		ls_plantillaNombreArchivo     = obtenerValorCatalogo(amss_catalogo, as_idPlantilla);
		ls_nombreArchivo              = resolver(ls_plantillaNombreArchivo, aerc_entidad, aerc_cuenta, ad_fecha);
		ls_rutaArchivo                = obtenerRutaCompleta(as_rutaBase, ls_nombreArchivo);

		FTPUtils.cargarArchivoFTP(acs_canal, as_archivo, ls_rutaArchivo);
	}

	private String obtenerValorCatalogo(Map<String, String> amss_catalogo, String as_llaveCatalogo)
	    throws B2BException
	{
		String ls_valorCatalogo;

		ls_valorCatalogo = (amss_catalogo != null) ? amss_catalogo.get(as_llaveCatalogo) : null;

		if(!StringUtils.isValidString(ls_valorCatalogo))
		{
			Object[] loa_messageArgs;

			loa_messageArgs        = new String[1];
			loa_messageArgs[0]     = as_llaveCatalogo;

			throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
		}

		return ls_valorCatalogo;
	}
}
