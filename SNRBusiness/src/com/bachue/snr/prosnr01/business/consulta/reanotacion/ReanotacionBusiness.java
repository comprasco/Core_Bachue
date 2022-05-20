package com.bachue.snr.prosnr01.business.consulta.reanotacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraBloqueoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;


/**
 * Clase para el manejo del negocio de entrega para hacer el proceso correspondiente para la reanotacion.
 *
 * @author Jorge Patiño
 */
public class ReanotacionBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReanotacionBusiness.class);

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar el turno historia con el id anterior, para obtener las observaciones del mismo.
	 *
	 * @param ath_turnoHistoria objeto de clase TurnoHistoria para obtener el idAnterior
	 * @return el valor de observaciones
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized String getObservaciones(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			TurnoHistoriaDAO lthd_DAO;
			TurnoHistoria    lth_turnoHistoria;

			lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			lth_turnoHistoria = lthd_DAO.findByIdTurno(ath_turnoHistoria, true);

			if(lth_turnoHistoria != null)
			{
				lth_turnoHistoria = lthd_DAO.findByIdAnterior(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
					ls_return = lth_turnoHistoria.getObservaciones();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getObservaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar el turno historia con el id anterior.
	 *
	 * @param as_turno Objeto contenedor del turno con el cual se realizará la busqueda de registros
	 * @return devuelve el valor de Long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Long consultaByTurno(String as_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Long       ll_idTurnoAnterior;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		ll_idTurnoAnterior     = null;

		try
		{
			if(StringUtils.isValidString(as_turno))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurno(as_turno);
				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(lth_turnoHistoria, true);

				if(lth_turnoHistoria != null)
				{
					SolicitudMatricula lsm_solicitudMatricula;
					Turno              lt_turno;
					long               lbd_etapa;
					String             ls_idTurno;

					lsm_solicitudMatricula     = new SolicitudMatricula();
					lt_turno                   = new Turno();
					lbd_etapa                  = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
					ls_idTurno                 = lth_turnoHistoria.getIdTurno();

					if(StringUtils.isValidString(ls_idTurno))
					{
						lt_turno.setIdTurno(ls_idTurno);

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

						if(lt_turno != null)
						{
							String ls_solicitud;

							ls_solicitud = lt_turno.getIdSolicitud();

							if(StringUtils.isValidString(ls_solicitud))
							{
								String ls_circulo;
								ls_circulo = lt_turno.getIdCirculo();

								lsm_solicitudMatricula.setIdSolicitud(ls_solicitud);

								if(StringUtils.isValidString(ls_circulo))
								{
									Collection<SolicitudMatricula> lcsm_solicitudes;

									lsm_solicitudMatricula.setIdCirculo(ls_circulo);

									lcsm_solicitudes = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
											                         .findByIdSolicitudCirculo(lsm_solicitudMatricula);

									if(CollectionUtils.isValidCollection(lcsm_solicitudes))
									{
										BitacoraBloqueoDAO lbbd_DAO;

										lbbd_DAO = DaoCreator.getBitacoraBloqueoDAO(ldm_manager);

										for(SolicitudMatricula lsm_temp : lcsm_solicitudes)
										{
											if(lsm_temp != null)
											{
												Collection<BitacoraBloqueo> lcbb_bitacorasBloqueo;
												BitacoraBloqueo             lbb_bitacoraConsulta;
												BigInteger                  lbi_matricula;

												lbb_bitacoraConsulta     = new BitacoraBloqueo();
												lbi_matricula            = NumericUtils.getBigInteger(
													    lsm_temp.getIdMatricula()
													);

												if(NumericUtils.isValidBigInteger(lbi_matricula))
												{
													String ls_circuloBitacora;

													ls_circuloBitacora = lsm_temp.getIdCirculo();

													if(StringUtils.isValidString(ls_circuloBitacora))
													{
														lbb_bitacoraConsulta.setIdMatricula(lbi_matricula);
														lbb_bitacoraConsulta.setIdCirculo(ls_circuloBitacora);
														lcbb_bitacorasBloqueo = lbbd_DAO.findByCirculoMatricula(
															    lbb_bitacoraConsulta
															);

														if(CollectionUtils.isValidCollection(lcbb_bitacorasBloqueo))
														{
															for(BitacoraBloqueo lbb_temp : lcbb_bitacorasBloqueo)
															{
																if(lbb_temp != null)
																{
																	Date ld_fechaCreacionBitacora;
																	Date ld_fechaCreacionTurno;

																	ld_fechaCreacionBitacora = lbb_temp
																			.getFechaInsercion();

																	if(ld_fechaCreacionBitacora != null)
																	{
																		ld_fechaCreacionTurno = lt_turno
																				.getFechaCreacion();

																		if(ld_fechaCreacionTurno != null)
																		{
																			if(
																			    ld_fechaCreacionBitacora.after(
																				        ld_fechaCreacionTurno
																				    )
																			)
																				throw new B2BException(
																				    ErrorKeys.ERROR_EXISTE_REGISTRO_MAYOR_BITACORA_BLOQUEO
																				);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}

						if(NumericUtils.isValidLong(NumericUtils.getLongWrapper(lbd_etapa)))
						{
							if(
							    (lbd_etapa != EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
								    && (lbd_etapa != EtapaCommon.ID_ETAPA_ENTREGA_CORRESPONDENCIA_NOTA_DEVOLUTIVA)
								    && (lbd_etapa != EtapaCommon.ID_ETAPA_ENTREGA_CORREO_ELECTRONICO_NOTA_DEVOLUTIVA)
							)
							{
								Object[] aoa_messageArgs = new String[1];
								aoa_messageArgs[0] = as_turno;

								if(lbd_etapa > EtapaCommon.ID_ETAPA_PROCESO_DE_REGISTRO_FINALIZADO)
									throw new B2BException(ErrorKeys.ID_ETAPA_MAYOR_A_500, aoa_messageArgs);
								else
									throw new B2BException(
									    ErrorKeys.ERROR_TURNO_NO_PUEDE_SER_REANOTADO, aoa_messageArgs
									);
							}
							else
							{
								ll_idTurnoAnterior = lth_turnoHistoria.getIdTurnoHistoria();

								if(!NumericUtils.isValidLong(ll_idTurnoAnterior))
								{
									Object[] aoa_messageArgs = new String[1];
									aoa_messageArgs[0] = as_turno;

									throw new B2BException(
									    ErrorKeys.ERROR_SIN_REGISTROS_TURNO_HISTORIA, aoa_messageArgs
									);
								}
							}
						}
					}
				}
				else
				{
					Object[] aoa_messageArgs = new String[1];
					aoa_messageArgs[0] = as_turno;

					throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_TURNO_HISTORIA, aoa_messageArgs);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_idTurnoAnterior;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para terminar la etapa del TurnoHistoria y crear una etapa 250.
	 *
	 * @param ath_th Objeto de clase TurnoHistoria con el cúal se realizará la búsqueda de registros
	 * @param as_idUsuario Objeto contenedor de el usuario que realiza la operación.
	 * @param as_remoteIp Objeto contenedor de la dirección IP que realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void enviarAEntrega(TurnoHistoria ath_th, String as_idUsuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				TurnoHistoria    lth_dataTurn;
				TurnoHistoriaDAO lothd_DAO;

				lth_dataTurn     = new TurnoHistoria();
				lothd_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_dataTurn.setIdTurnoHistoria(ath_th.getIdTurnoHistoria());

				lth_dataTurn = lothd_DAO.findById(lth_dataTurn);

				if(lth_dataTurn != null)
				{
					MotivoTramite lmt_motivo;

					lmt_motivo = new MotivoTramite();

					lmt_motivo.setIdEtapaOrigen(NumericUtils.getLong(lth_dataTurn.getIdEtapa()));

					lmt_motivo.setIdMotivo(MotivoTramiteCommon.TRAMITE_DE_REANOTACION);

					lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						String ls_circulo;

						ls_circulo = lth_dataTurn.getIdCirculoUsuario();

						if(StringUtils.isValidString(ls_circulo))
						{
							lth_dataTurn.setIdCirculo(ls_circulo);
							lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
							lth_dataTurn.setObservaciones(ath_th.getObservaciones());
							lth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_dataTurn.setIdUsuarioModificacion(as_idUsuario);
							lth_dataTurn.setIdUsuarioAsignacion(as_idUsuario);
							lth_dataTurn.setIpModificacion(as_remoteIp);

							lothd_DAO.insertOrUpdate(lth_dataTurn, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_dataTurn);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("enviarAEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
