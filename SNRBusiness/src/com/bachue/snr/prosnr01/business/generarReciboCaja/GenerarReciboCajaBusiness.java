package com.bachue.snr.prosnr01.business.generarReciboCaja;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr04.dao.npa.RegistrarPagoDAO;

import com.bachue.snr.prosnr04.model.npa.RegistroPago;

import java.util.Collection;
import java.util.Date;


/**
 * Clase para el manejo del negocio de etapa 21 AUT.
 *
 * @author Julian Vaca
 *
 */
public class GenerarReciboCajaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarReciboCajaBusiness.class);

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar etapas
	 * 21 con estado actividad AUT de Turno Historia
	 *
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarReciboCaja(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = lcd_constantesDAO.findById(ConstanteCommon.JOB_GENERAR_RECIBO_CAJA_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_limiteIntentos;

				ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_GENERAR_RECIBO_CAJA_LIMITE_INTENTOS
					);
				lcth_parametros       = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                              .findDataJobGenerarDocumentos(true, ll_limiteIntentos);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarReciboCaja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			generarReciboCaja(lcth_parametros, as_remoteIp);
	}

	/**
	 * Método de transacciones con la base de datos para procesar casos encontrados
	 * en etapa 21 y estado actividad AUT
	 *
	 * @param acth_parametros
	 *            Objeto de tipo Collection<TurnoHistoria> que contiene casos a ser
	 *            procesados
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarReciboCaja(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_GENERAR_RECIBO_CAJA_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManager();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("generarReciboCaja", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManager();

			ldm_processing.setAutoCommit(true);

			try
			{
				ConstantesDAO lcd_constant;
				Constantes    lce_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lce_constant     = lcd_constant.findById(ls_constant);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("generarReciboCaja", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing && StringUtils.isValidString(ls_userId))
			{
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_manager.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									generarReciboCaja(lth_iterador, lbpd_bitacora, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("generarReciboCaja", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();
					clh_LOGGER.error("generarReciboCaja", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarReciboCaja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManager();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("generarReciboCaja", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}
		}
	}

	/**
	 * Método de transacciones con la base de datos para procesar de manera
	 * individual los casos encontrados
	 *
	 * @param ath_parametros
	 *            Objeto de tipo TurnoHistoria que contiene la etapa actual del caso
	 *            a ser procesado
	 * @param abpd_DAO
	 *            Objeto de tipo BitacoraProcesoDAO que va a registrar la bitacora
	 *            del caso a ser procesado
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarReciboCaja(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros != null)
			{
				long ll_idEtapa;

				ll_idEtapa = NumericUtils.getLong(ath_parametros.getIdEtapa());

				if(!StringUtils.isValidString(as_userId))
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = as_userId;

					throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_ENCONTRADO, loa_arg);
				}

				if((ll_idEtapa > 0L) && StringUtils.isValidString(as_userId))
				{
					String ls_idSolicitud;

					ls_idSolicitud = ath_parametros.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							Liquidacion    ll_liquidacion;
							LiquidacionDAO lld_DAO;

							lld_DAO            = DaoCreator.getAccLiquidacionDAO(ldm_manager);
							ll_liquidacion     = lld_DAO.findByIdTipoMayorValor(
								    ls_idSolicitud, ll_idEtapa == EtapaCommon.PAGO_NOTIFICADO
								);

							if(ll_liquidacion != null)
							{
								if(BooleanUtils.getBooleanValue(ll_liquidacion.getPagada()))
								{
									TurnoDAO          ltd_DAO;
									Turno             lt_turno;
									Collection<Turno> lct_turnos;

									lt_turno     = new Turno();
									ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);

									lt_turno.setIdSolicitud(ls_idSolicitud);

									lct_turnos = ltd_DAO.findByIdSolicitud(lt_turno);

									if(CollectionUtils.isValidCollection(lct_turnos))
									{
										Registro lr_parametros;

										lr_parametros = new Registro();

										lr_parametros.setNirProceso(ls_solicitud.getNir());
										lr_parametros.setSolicitud(ls_solicitud);
										lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_CAJA);
										lr_parametros.setIdTipoMayorValor(ll_liquidacion.getIdTipoMayorValor());

										lr_parametros = getRegistroBusiness()
												                .generarReciboLiquidacion(
												    lr_parametros, false, as_userId, as_remoteIp, as_remoteIp,
												    ldm_manager, true
												);

										if(lr_parametros != null)
										{
											byte[]              lba_pdf;
											DocumentosSalidaDAO lds_DAO;
											ImagenesDAO         li_DAO;

											lba_pdf     = lr_parametros.getPdf();
											lds_DAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
											li_DAO      = DaoCreator.getImagenesDAO(ldm_manager);

											for(Turno lt_actual : lct_turnos)
											{
												if(lt_actual != null)
												{
													DocumentosSalida lds_documentosSalida;
													Imagenes         li_imagenes;
													long             ll_secuencia;

													ll_secuencia             = -1L;
													li_imagenes              = new Imagenes();
													lds_documentosSalida     = new DocumentosSalida();

													li_imagenes.setImagenBlob(lba_pdf);
													li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
													li_imagenes.setIdUsuarioCreacion(as_userId);
													li_imagenes.setIpCreacion(as_remoteIp);

													ll_secuencia = li_DAO.insertOrUpdate(li_imagenes, true);

													if(ll_secuencia <= 0)
														throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

													lds_documentosSalida.setIdImagen(
													    NumericUtils.getLongWrapper(ll_secuencia)
													);
													lds_documentosSalida.setTipo(TipoArchivoCommon.RECIBO_CAJA);
													lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
													lds_documentosSalida.setIdSolicitud(ls_idSolicitud);
													lds_documentosSalida.setIdTurno(lt_actual.getIdTurno());
													lds_documentosSalida.setIdTipoDocumental(
													    TipoDocumentalCommon.RECIBO_CAJA
													);
													lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
													lds_documentosSalida.setDocumentoFinal(EstadoCommon.S);
													lds_documentosSalida.setIdUsuarioCreacion(as_userId);
													lds_documentosSalida.setIpCreacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_documentosSalida, true);
												}
											}

											{
												RegistrarPagoDAO lrpd_DAO;
												RegistroPago     lrp_rp;

												lrpd_DAO     = DaoCreator.getRegistrarPagoDAO(ldm_manager);
												lrp_rp       = lrpd_DAO.findById(ll_liquidacion.getNumeroReferencia());

												if(lrp_rp != null)
												{
													lrp_rp.setEstadoGeneracionReciboCaja(EstadoCommon.GENERADO);
													lrp_rp.setNumeroReciboCaja(lr_parametros.getNumeroReciboCaja());
													lrp_rp.setIdUsuarioModificacion(as_userId);
													lrp_rp.setIpModificacion(as_remoteIp);

													DaoCreator.getRegistroPagoDAO(ldm_manager)
														          .actualizarEstadoPago(lrp_rp);
												}
											}

											terminarEtapa(ath_parametros, as_userId, as_remoteIp, ldm_manager);
										}
										else
										{
											Object[] loa_arg;

											loa_arg        = new String[1];
											loa_arg[0]     = ls_idSolicitud;

											throw new B2BException(ErrorKeys.ERROR_RECIBO_CAJA_SOLICITUD, loa_arg);
										}
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ls_idSolicitud;

										throw new B2BException(ErrorKeys.ERROR_TURNOS_PARA_SOLICITUD, loa_arg);
									}
								}
								else
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = ls_idSolicitud;

									throw new B2BException(ErrorKeys.ERROR_LIQUIDACION_PARA_SOLICITUD, loa_arg);
								}
							}
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_idSolicitud;

								throw new B2BException(ErrorKeys.ERROR_SIN_LIQUIDACION_PARA_SOLICITUD, loa_arg);
							}
						}
						else
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = ls_idSolicitud;

							throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_SOLICITUD_ACC, loa_arg);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_SOLICITUD_VALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_USUARIO_Y_O_ETAPA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.GENERACION_RECIBO_CAJA, lb2be_e.getMessage(), as_userId, as_remoteIp,
			    (ath_parametros != null) ? StringUtils.getString(ath_parametros.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de terminar la etapa actual y avanzar a la siguiente etapa
	 * mediente un procedimiento almacenado.
	 *
	 * @param ath_turnoHistoria Objeto de tipo TurnoHistoria que contiene el id turno historia de
	 *            la etapa actual del caso
	 * @param as_userId            Objeto de tipo String que contiene el usuario que realiza la
	 *            accion en el sistema
	 * @param as_remoteIp            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @param adm_manager            Objeto de tipo DAOManager que contiene la transaccion activa con
	 *            la Base de Datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void terminarEtapa(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if((ath_turnoHistoria != null))
			{
				TurnoHistoriaDAO lthd_DAO;

				lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(adm_manager);

				ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

				if(ath_turnoHistoria != null)
				{
					ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
					ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
					ath_turnoHistoria.setIpModificacion(as_remoteIp);

					lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarEtapa", lb2be_e);

			throw lb2be_e;
		}
	}
}
