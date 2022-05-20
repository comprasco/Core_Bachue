package com.bachue.snr.prosnr01.business.admHistoricosMisional;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemCertificadoDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionItemDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmActos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmLiquidaciones;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmPagos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosFolio;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosSir;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.registro.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.LiquidacionItemCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las funcionalidades para los historicos misionales.
 *
 * @author hcastaneda
 */
public class AdmHistoricosMisionalBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AdmHistoricosMisionalBusiness.class);

	/**
	 * Retorna el valor del objeto de Collection de AdmHomologacion.
	 *
	 * @param as_turnoActualizado correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection de tipo AdmHomologacion
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<AdmHomologacion> consultarAdmHomologacion(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<AdmHomologacion> lca_dataFinal;

		lca_dataFinal = null;

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_turnoActualizado);

				if(lt_turno != null)
				{
					String ls_idCirculo;

					ls_idCirculo = lt_turno.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(ls_idCirculo);

						if(lcr_circuloRegistral != null)
						{
							String ls_sistemaOrigen;

							ls_sistemaOrigen = lcr_circuloRegistral.getSistemaOrigen();

							if(StringUtils.isValidString(ls_sistemaOrigen))
							{
//								lca_dataFinal = DaoCreator.getAdmLiquidacionDAO(ldm_manager)
//					                      .consultarAdmHomologacion(as_turnoActualizado);
								switch(ls_sistemaOrigen)
								{
									case SistemaOrigenCommon.FOLIO:
										lca_dataFinal = DaoCreator.getLiquidacionesHistoricosFolioDAO(ldm_manager)
												                      .findAllByTurnoActualizado(as_turnoActualizado);

										break;

									case SistemaOrigenCommon.SIR:
										lca_dataFinal = DaoCreator.getLiquidacionesHistoricosSirDAO(ldm_manager)
												                      .findAllByTurnoActualizado(as_turnoActualizado);

										break;

									default:
										break;
								}

								if(CollectionUtils.isValidCollection(lca_dataFinal))
								{
									for(AdmHomologacion lah_tmp : lca_dataFinal)
									{
										if(lah_tmp != null)
										{
											AdmLiquidaciones lal_admLiquidaciones;

											lal_admLiquidaciones = lah_tmp.getAdmLiquidaciones();

											if(lal_admLiquidaciones != null)
											{
												String ls_acto;

												ls_acto = lal_admLiquidaciones.getActo();

												if(
												    StringUtils.isValidString(ls_acto)
													    && !StringUtils.isNumeric(ls_acto)
												)
												{
													lah_tmp.setCertificado(true);
													lah_tmp.setHomologado(true);
												}
											}
										}
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_REGISTROS_ACTOS_MIGRAGOS);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarAdmHomologacion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lca_dataFinal;
	}

	/**
	 * Consultar matriculas por solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> consultarMatriculasPorSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_return;

		lcsm_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcsm_return = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
						                    .findAllByIdSolicitudWithCirculoAndDireccion(as_idSolicitud);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarMatriculasPorSolicitud", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcsm_return;
	}

	/**
	 * Consultar pagos.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PagosUI> consultarPagos(String as_turnoActualizado)
	    throws B2BException
	{
		Collection<PagosUI> lcpui_dataFinal;

		lcpui_dataFinal = new ArrayList<PagosUI>();

		if(StringUtils.isValidString(as_turnoActualizado))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_turnoActualizado);

				if(lt_turno != null)
				{
					String ls_idCirculo;

					ls_idCirculo = lt_turno.getIdCirculo();

					if(StringUtils.isValidString(ls_idCirculo))
					{
						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager).findById(ls_idCirculo);

						if(lcr_circuloRegistral != null)
						{
							String ls_sistemaOrigen;

							ls_sistemaOrigen = lcr_circuloRegistral.getSistemaOrigen();

							if(StringUtils.isValidString(ls_sistemaOrigen))
							{
								Collection<PagosFolio> lcpf_pf;
								Collection<PagosSir>   lcps_ps;

								lcpf_pf     = null;
								lcps_ps     = null;

								switch(ls_sistemaOrigen)
								{
									case SistemaOrigenCommon.FOLIO:
										lcpf_pf = DaoCreator.getPagosFolioDAO(ldm_manager)
												                .findAllByTurnoActualizado(as_turnoActualizado);

										break;

									case SistemaOrigenCommon.SIR:
										lcps_ps = DaoCreator.getPagosSirDAO(ldm_manager)
												                .findAllByTurnoActualizado(as_turnoActualizado);

										break;

									default:
										break;
								}

								if(CollectionUtils.isValidCollection(lcpf_pf))
								{
									for(PagosFolio lpf_tmp : lcpf_pf)
									{
										if(lpf_tmp != null)
											lcpui_dataFinal.add(new PagosUI(lpf_tmp));
									}
								}

								if(CollectionUtils.isValidCollection(lcps_ps))
								{
									for(PagosSir lps_tmp : lcps_ps)
									{
										if(lps_tmp != null)
											lcpui_dataFinal.add(new PagosUI(lps_tmp));
									}
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarPagos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		if(lcpui_dataFinal.isEmpty())
			lcpui_dataFinal = null;

		return lcpui_dataFinal;
	}

	/**
	 * Generar datos tramite cantidad turnos homologacion.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramiteCantidad generarDatosTramiteCantidadTurnosHomologacion(String as_idTurno)
	    throws B2BException
	{
		DAOManager      ldm_manager;
		TramiteCantidad lctc_cllTramiteCantidad;

		ldm_manager                 = DaoManagerFactory.getDAOManager();
		lctc_cllTramiteCantidad     = new TramiteCantidad();

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Turno lt_turno;

				lt_turno     = new Turno();

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lt_turno.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_nir;

						ls_nir = ls_solicitud.getNir();

						if(StringUtils.isValidString(ls_nir))
						{
							String ls_turnoMigrado;

							ls_turnoMigrado = lt_turno.getMigrado();

							lctc_cllTramiteCantidad.setNir(ls_nir);

							if(
							    StringUtils.isValidString(ls_turnoMigrado)
								    && ls_turnoMigrado.equalsIgnoreCase(EstadoCommon.S)
							)
							{
								String ls_idProceso;

								ls_idProceso = lt_turno.getIdProceso();

								if(
								    StringUtils.isValidString(ls_idProceso)
									    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
								)
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
											                          .findByIdTurno(as_idTurno);

									if(lth_turnoHistoria != null)
									{
										long                    ls_idEtapa;
										String                  ls_estadoActividad;
										Collection<Acto>        lca_actos;
										Liquidacion             ll_liquidacion;
										Collection<Liquidacion> lcl_liquidacionItem;

										ls_idEtapa              = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
										ls_estadoActividad      = lth_turnoHistoria.getEstadoActividad();
										lca_actos               = DaoCreator.getActoDAO(ldm_manager)
												                                .findActosByIdTurno(as_idTurno);
										ll_liquidacion          = DaoCreator.getAccLiquidacionDAO(ldm_manager)
												                                .findById(
												    lt_turno.getIdLiquidacion(),
												    NumericUtils.getInt(IdentificadoresCommon.NUM1)
												);
										lcl_liquidacionItem     = DaoCreator.getAccLiquidacionItemDAO(ldm_manager)
												                                .findByIdTurno(as_idTurno);

										if(
										    ((ls_idEtapa == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
											    || (ls_idEtapa == EtapaCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL))
											    && (StringUtils.isValidString(ls_estadoActividad)
											    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
											    && (!CollectionUtils.isValidCollection(lca_actos)
											    && (ll_liquidacion == null)
											    && (!CollectionUtils.isValidCollection(lcl_liquidacionItem)))
										)
										{
											Collection<Turno> lct_turnos;

											lct_turnos = DaoCreator.getTurnoDAO(ldm_manager)
													                   .findTurnosRecurrente(lt_turno);

											if(CollectionUtils.isValidCollection(lct_turnos))
											{
												Collection<TramiteCantidad> lctc_turnos;

												lctc_turnos = new ArrayList<TramiteCantidad>();

												for(Turno lt_tmp : lct_turnos)
												{
													if(lt_tmp != null)
													{
														String ls_idTurnoIterator;

														ls_idTurnoIterator = lt_tmp.getIdTurno();

														if(StringUtils.isValidString(ls_idTurnoIterator))
														{
															Turno lt_turnoIterator;

															lt_turnoIterator = DaoCreator.getTurnoDAO(ldm_manager)
																	                         .findById(
																	    ls_idTurnoIterator
																	);

															if(lt_turnoIterator != null)
															{
																TurnoHistoria lth_turnoHistoriaTurnoAsociado;

																lth_turnoHistoriaTurnoAsociado = DaoCreator.getTurnoHistoriaDAO(
																	    ldm_manager
																	).findByIdTurno(ls_idTurnoIterator);

																if(lth_turnoHistoriaTurnoAsociado != null)
																{
																	TramiteCantidad ltc_tc;

																	ltc_tc = new TramiteCantidad();

																	ltc_tc.setIdTurnoHistoria(
																	    StringUtils.getString(
																	        lth_turnoHistoriaTurnoAsociado
																		        .getIdTurnoHistoria()
																	    )
																	);
																	ltc_tc.setIdEtapa(
																	    NumericUtils.getLongWrapper(
																	        lth_turnoHistoriaTurnoAsociado.getIdEtapa()
																	    )
																	);
																	ltc_tc.setIdTurno(lt_turnoIterator.getIdTurno());
																	ltc_tc.setFechaCreacion(
																	    lt_turnoIterator.getFechaCreacion()
																	);
																	ltc_tc.setEstadoActualTurno(
																	    lt_turnoIterator.getDescripcion()
																	);
																	ltc_tc.setIdProceso(
																	    lt_turnoIterator.getIdProceso()
																	);
																	ltc_tc.setIdSubProceso(
																	    lt_turnoIterator.getIdSubProceso()
																	);
																	ltc_tc.setHomologado(
																	    StringUtils.isValidString(
																	        lt_turnoIterator.getIdLiquidacion()
																	    )
																		    && (lt_turnoIterator
																		    .getConsecutivoLiquidacion() > 0)
																	);

																	{
																		String ls_migrado;

																		ls_migrado = lt_turnoIterator.getMigrado();

																		ltc_tc.setMigrado(
																		    StringUtils.isValidString(ls_migrado)
																			    && ls_migrado.equalsIgnoreCase(
																			        EstadoCommon.S
																			    )
																		);
																	}

																	{
																		Collection<SolicitudMatricula> lcsm_solicitudMatricula;
																		StringBuilder                  lsb_sb;

																		lcsm_solicitudMatricula     = DaoCreator.getSolicitudMatriculaDAO(
																			    ldm_manager
																			)
																				                                    .findByIdSolicitud(
																				    lth_turnoHistoriaTurnoAsociado
																				    .getIdSolicitud(), false
																				);
																		lsb_sb                      = new StringBuilder();

																		if(
																		    CollectionUtils.isValidCollection(
																			        lcsm_solicitudMatricula
																			    )
																		)
																		{
																			int li_count;

																			li_count = 0;

																			for(SolicitudMatricula lsm_tmp : lcsm_solicitudMatricula)
																			{
																				if(lsm_tmp != null)
																				{
																					String ls_idCirculo;
																					long   ll_idMatricula;

																					ls_idCirculo       = lsm_tmp
																							.getIdCirculo();
																					ll_idMatricula     = lsm_tmp
																							.getIdMatricula();

																					if(
																					    StringUtils.isValidString(
																						        ls_idCirculo
																						    ) && (ll_idMatricula > 0)
																					)
																					{
																						if(li_count > 0)
																							lsb_sb.append(
																							    IdentificadoresCommon.SIMBOLO_COMA
																							);

																						lsb_sb.append(
																						    ls_idCirculo
																						    + IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
																						    + StringUtils.getString(
																						        ll_idMatricula
																						    )
																						);
																					}

																					li_count++;
																				}
																			}
																		}

																		String ls_matriculas;

																		ls_matriculas = lsb_sb.toString();

																		if(StringUtils.isValidString(ls_matriculas))
																			ltc_tc.setMatriculasRelacionadas(
																			    lsb_sb.toString()
																			);
																		else
																			ltc_tc.setMatriculasRelacionadas(
																			    IdentificadoresCommon.SIN_MATRICULAS
																			);
																	}

																	lctc_turnos.add(ltc_tc);
																}
															}
														}
													}
												}

												lctc_cllTramiteCantidad.setTurnos(lctc_turnos);
											}
											else
												throw new B2BException(
												    ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE
												);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_TURNO_VALIDACIONES_HOMOLOGACION);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_TURNO_VALIDACIONES_HOMOLOGACION);
								}
								else
								{
									Object[] loa_args;

									loa_args        = new String[1];
									loa_args[0]     = lt_turno;

									throw new B2BException(
									    ErrorKeys.ERROR_TURNO_NO_VALIDO_PARA_TRAMITE_HOMOLOGACION_ACTOS_LIQUIDACION,
									    loa_args
									);
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_TURNO_CONSULTADO_MIGRADO);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_NIR_TURNO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_SIN_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_VALIDO);
			}
			else
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_VALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDatosTramiteCantidadTurnosHomologacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_cllTramiteCantidad;
	}

	/**
	 * Guardar matriculas asociadas.
	 *
	 * @param acsm_solicitudMatricula de acsm solicitud matricula
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarMatriculasAsociadas(
	    Collection<SolicitudMatricula> acsm_solicitudMatricula, String as_idTurno, String as_userId,
	    String as_remoteIpAddress
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acsm_solicitudMatricula) && StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<SolicitudMatriculaActo> lcsm_solicitudMatriculaActo;
				SolicitudMatriculaActoDAO          lsmd_DAO;

				lcsm_solicitudMatriculaActo     = new ArrayList<SolicitudMatriculaActo>();
				lsmd_DAO                        = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);

				for(SolicitudMatricula lsm_tmp : acsm_solicitudMatricula)
				{
					if(lsm_tmp != null)
					{
						SolicitudMatriculaActo lsm_matriculaActoInsertar;

						lsm_matriculaActoInsertar = new SolicitudMatriculaActo(lsm_tmp);

						lsm_matriculaActoInsertar.setIdActo(lsm_tmp.getIdActoSeleccionado());
						lsm_matriculaActoInsertar.setFecha(DateUtils.getTimestamp(new Date()));
						lsm_matriculaActoInsertar.setIdTurno(as_idTurno);
						lsm_matriculaActoInsertar.setIdUsuarioCreacion(as_userId);
						lsm_matriculaActoInsertar.setIpCreacion(as_remoteIpAddress);

						lcsm_solicitudMatriculaActo.add(lsm_matriculaActoInsertar);
					}
				}

				for(SolicitudMatriculaActo lsm_tmp : lcsm_solicitudMatriculaActo)
				{
					if(lsm_tmp != null)
					{
						SolicitudMatriculaActo lsm_consulta;

						lsm_consulta = lsmd_DAO.findById(lsm_tmp);

						if(lsm_consulta == null)
							lsmd_DAO.insertOrUpdate(lsm_tmp, true);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("guardarMatriculasAsociadas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Metodo encargado de homologar actos e insertarlos en la tabla actos.
	 *
	 * @param aah_homologacion Argumento de tipo AdmHomologacion que contiene los valores a homologar.
	 * @param as_userId Argumento de tipo String que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip de la maquina del usuario que
	 * realiza la operación.
	 * @return Objeto de tipo Acto que contiene el id del acto insertado.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AdmHomologacion
	 */
	public synchronized AdmHomologacion insertarActoHomologado(
	    AdmHomologacion aah_homologacion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aah_homologacion != null)
			{
				Acto la_acto;

				la_acto = new Acto();

				{
					boolean lb_homologado;

					lb_homologado = aah_homologacion.isHomologado();

					if(lb_homologado)
						la_acto.setTipoAdquisicion(IdentificadoresCommon.NUM1);
					else
						throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_EL_ACTO_ANTES_DE_AGREGAR);
				}

				{
					Turno lt_turno;

					lt_turno = new Turno();

					lt_turno.setIdTurno(aah_homologacion.getIdTurno());

					lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

					if(lt_turno != null)
					{
						Solicitud ls_solicitud;
						String    ls_idSolicitud;

						ls_solicitud       = aah_homologacion.getSolicitud();
						ls_idSolicitud     = (ls_solicitud != null) ? ls_solicitud.getIdSolicitud()
							                                        : lt_turno.getIdSolicitud();

						la_acto.setIdSolicitud(ls_idSolicitud);
						la_acto.setIdCirculo(lt_turno.getIdCirculo());
					}
				}

				{
					TipoActo lta_tipoActo;

					lta_tipoActo = aah_homologacion.getTipoActo();

					if(lta_tipoActo != null)
						la_acto.setCodigo(lta_tipoActo.getIdTipoActo());
				}

				{
					AdmActos laa_admActos;

					laa_admActos = aah_homologacion.getAdmActos();

					if(laa_admActos != null)
						la_acto.setCuantia(NumericUtils.getLongWrapper(laa_admActos.getCuantia()));
				}

				{
					int li_secuence;

					li_secuence = DaoCreator.getTipoActoDAO(ldm_manager).findSecuence();

					if(li_secuence > 0)
					{
						String ls_secuencia;

						ls_secuencia = StringUtils.getString(li_secuence);

						la_acto.setSecuence(ls_secuencia);
						la_acto.setUserId(as_userId);
						la_acto.setIpCreacion(as_remoteIp);

						DaoCreator.getActoDAO(ldm_manager).insertOrUpdate(la_acto, true);

						{
							com.bachue.snr.prosnr01.model.sdb.acc.Acto la_actoReturn;

							la_actoReturn = new com.bachue.snr.prosnr01.model.sdb.acc.Acto();

							la_actoReturn.setIdActo(ls_secuencia);

							aah_homologacion.setActo(la_actoReturn);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertarActoHomologado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return aah_homologacion;
	}

	/**
	 * Metodo encargado de terminar el proceso de homologación de actos.
	 *
	 * @param aah_homologacion Argumento de tipo AdmHomologacion que contiene los criterios para salvar
	 * la homologación de actos y crear la etapa de calificación.
	 * @param ab_administracionHomologacionActosLiquidacion de ab administracion homologacion actos liquidacion
	 * @param as_userId Argumento de tipo String que contiene el id del usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip de la maquina del usuario que
	 * realiza la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void terminarProcesoHomologacion(
	    AdmHomologacion aah_homologacion, boolean ab_administracionHomologacionActosLiquidacion, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aah_homologacion != null)
			{
				Liquidacion ll_liquidacion;
				String      ls_idProceso;
				String      ls_idSubProceso;
				String      ls_idTurno;
				Solicitud   ls_solicitudRegistro;
				boolean     lb_solicitudRegistro;

				ll_liquidacion           = new Liquidacion();
				ls_idProceso             = null;
				ls_idSubProceso          = null;
				ls_idTurno               = aah_homologacion.getIdTurno();
				ls_solicitudRegistro     = aah_homologacion.getSolicitud();
				lb_solicitudRegistro     = ls_solicitudRegistro != null;

				{
					AdmPagos lap_pagos;

					lap_pagos = aah_homologacion.getAdmPagos();

					if(lap_pagos != null)
					{
						Date ld_fechaPago;

						ld_fechaPago = lap_pagos.getFecha();

						if(ld_fechaPago != null)
						{
							ll_liquidacion.setFechaLiquidacion(ld_fechaPago);
							ll_liquidacion.setFechaPago(ld_fechaPago);
						}
					}
				}

				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

				if(lt_turno != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;

						ls_solicitud = new Solicitud();

						ls_solicitud.setIdSolicitud(
						    lb_solicitudRegistro ? ls_solicitudRegistro.getIdSolicitud() : ls_idSolicitud
						);

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							ll_liquidacion.setIdSolicitud(ls_idSolicitud);
							ll_liquidacion.setNir(ls_solicitud.getNir());
							ls_idProceso        = ls_solicitud.getIdProceso();
							ls_idSubProceso     = ls_solicitud.getIdSubproceso();
						}
					}

					ll_liquidacion.setIdCirculo(lt_turno.getIdCirculo());
					ll_liquidacion.setIdTurno(ls_idTurno);
				}

				{
					AdmLiquidaciones lal_liquidaciones;

					lal_liquidaciones = aah_homologacion.getAdmLiquidaciones();

					if(lal_liquidaciones != null)
					{
						ll_liquidacion.setValorTotal(NumericUtils.getBigDecimal(lal_liquidaciones.getLiquidacion()));
						ll_liquidacion.setNumeroReferencia(lal_liquidaciones.getNroradica());
					}
				}

				ll_liquidacion.setConsecutivo(1);
				ll_liquidacion.setPagada(EstadoCommon.S);
				ll_liquidacion.setActivo(EstadoCommon.S);
				ll_liquidacion.setIdUsuarioCreacion(as_userId);
				ll_liquidacion.setIpCreacion(as_remoteIp);

				{
					BigInteger lbi_idLiquidacion;

					lbi_idLiquidacion = DaoCreator.getAccLiquidacionDAO(ldm_manager).insertOrUpdate(
						    ll_liquidacion, true
						);

					if(lbi_idLiquidacion != null)
						ll_liquidacion.setIdLiquidacion(StringUtils.getString(lbi_idLiquidacion));

					{
						lt_turno.setIdLiquidacion(StringUtils.getString(lbi_idLiquidacion));
						lt_turno.setConsecutivoLiquidacion(NumericUtils.getLong(IdentificadoresCommon.NUM1));
						lt_turno.setIdUsuarioModificacion(as_userId);
						lt_turno.setIpModificacion(as_remoteIp);

						DaoCreator.getTurnoDAO(ldm_manager).actualizarLiquidacionConsecutivo(lt_turno);
					}

					{
						Collection<AdmHomologacion> lcah_actosALiquidar;

						lcah_actosALiquidar = aah_homologacion.getActosHomologadosYAgregados();

						if(CollectionUtils.isValidCollection(lcah_actosALiquidar))
						{
							LiquidacionItemDAO            llid_DAO;
							LiquidacionItemCertificadoDAO llicd_DAO;
							ActoDAO                       lad_DAO;
							int                           li_item;
							int                           li_itemCertificado;

							llid_DAO               = DaoCreator.getAccLiquidacionItemDAO(ldm_manager);
							llicd_DAO              = DaoCreator.getLiquidacionItemCertificadoDAO(ldm_manager);
							lad_DAO                = DaoCreator.getActoDAO(ldm_manager);
							li_item                = 1;
							li_itemCertificado     = 1;

							for(AdmHomologacion lah_iterador : lcah_actosALiquidar)
							{
								if(lah_iterador != null)
								{
									{
										boolean lb_homologado;

										lb_homologado = lah_iterador.isHomologado();

										if(!lb_homologado)
											throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_TODOS_LOS_ACTOS);
									}

									BigDecimal lbd_valor;

									lbd_valor = BigDecimal.valueOf(0);

									{
										AdmLiquidaciones lal_liquidaciones;

										lal_liquidaciones = lah_iterador.getAdmLiquidaciones();

										if(lal_liquidaciones != null)
											lbd_valor = NumericUtils.getBigDecimal(lal_liquidaciones.getValor());
									}

									if(!lah_iterador.isCertificado())
									{
										com.bachue.snr.prosnr01.model.sdb.acc.Acto la_acto;

										la_acto = lah_iterador.getActo();

										if(la_acto != null)
										{
											{
												String ls_idActo;

												ls_idActo = la_acto.getIdActo();

												if(!StringUtils.isValidString(ls_idActo))
													throw new B2BException(ErrorKeys.DEBE_AGREGAR_TODOS_LOS_ACTOS);
											}

											la_acto = lad_DAO.findById(la_acto);

											if(la_acto != null)
											{
												ll_liquidacion.setIdItem(StringUtils.getString(li_item++));
												ll_liquidacion.setIdActo(la_acto.getIdActo());
												ll_liquidacion.setIdTipoActo(la_acto.getIdTipoActo());
												ll_liquidacion.setVersion(StringUtils.getString(la_acto.getVersion()));
												ll_liquidacion.setIdProceso(ls_idProceso);
												ll_liquidacion.setIdSubproceso(ls_idSubProceso);
												ll_liquidacion.setValor(lbd_valor);
												ll_liquidacion.setValorTotal(lbd_valor);
												ll_liquidacion.setActivo(EstadoCommon.S);
												ll_liquidacion.setIdTurno(ls_idTurno);
												ll_liquidacion.setIdUsuarioCreacion(as_userId);
												ll_liquidacion.setIpCreacion(as_remoteIp);

												llid_DAO.insert(ll_liquidacion);
											}
										}
									}
									else
									{
										LiquidacionItemCertificado llic_itemCertificado;

										llic_itemCertificado = new LiquidacionItemCertificado(ll_liquidacion);

										llic_itemCertificado.setIdItem(li_itemCertificado);
										llic_itemCertificado.setValor(NumericUtils.getLong(lbd_valor));
										llic_itemCertificado.setValorFinal(NumericUtils.getLong(lbd_valor));
										llic_itemCertificado.setIdCertificado(1);
										llic_itemCertificado.setCantidad(1);
										llic_itemCertificado.setCertificadoObligatorio(EstadoCommon.N);
										llic_itemCertificado.setIdUsuarioCreacion(as_userId);
										llic_itemCertificado.setIpCreacion(as_remoteIp);

										llicd_DAO.insert(llic_itemCertificado);

										li_itemCertificado++;
									}
								}
							}

							if(!lb_solicitudRegistro && !ab_administracionHomologacionActosLiquidacion)
							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria = aah_homologacion.getTurnoHistoria();

								if(lth_turnoHistoria != null)
									terminarTurnoHistoriaYCrearEtapa(
									    lth_turnoHistoria, ldm_manager,
									    new MotivoTramite(
									        EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION,
									        MotivoTramiteCommon.DEVUELTA_A_CALIFICACION_POR_HOMOLOGACION_ACTOS
									    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
									);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("insertarLiquidacionHomologada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
