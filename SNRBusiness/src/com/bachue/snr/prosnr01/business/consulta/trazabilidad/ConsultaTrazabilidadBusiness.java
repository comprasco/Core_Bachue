package com.bachue.snr.prosnr01.business.consulta.trazabilidad;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AlertaTurnoTramiteDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudAsociadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoCausalDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDerivadoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.consulta.trazabilidad.ConsultaTrazabilidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosFolio;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.PagosSir;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.InformacionDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.NirVinculado;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaDevolutiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.FoliosSirUI;
import com.bachue.snr.prosnr01.model.ui.LiquidacionesHistoricosUI;
import com.bachue.snr.prosnr01.model.ui.PagosUI;
import com.bachue.snr.prosnr01.model.ui.TrazabilidadTurnoUI;
import com.bachue.snr.prosnr01.model.view.LiquidacionesHistoricosFolio;
import com.bachue.snr.prosnr01.model.view.LiquidacionesHistoricosSir;
import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoFolio;
import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoFolioNotaDevolutiva;
import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoSir;
import com.bachue.snr.prosnr01.model.view.TrazabilidadTurnoSirNotaDevolutiva;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Clase que contiene los métodos de negocio para la consulta de trazabilidad de
 * un caso determinado.
 *
 * @author Gabriel Arias
 */
public class ConsultaTrazabilidadBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaTrazabilidadBusiness.class);

	/**Propeidad reference business*/
	private ReferenceBusiness irb_business;

	/**
	 * Método de sobreescritura deñ método getReferenceBusiness
	 */
	public ReferenceBusiness getReferenceBusiness()
	{
		if(irb_business == null)
			irb_business = new ReferenceBusiness();

		return irb_business;
	}

	/**
	 * Cargar bandeja historicos.
	 *
	 * @param at_turnoActualizado correspondiente al valor de turno actualizado
	 * @return el valor de FoliosSirUI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized FoliosSirUI cargarBandejaHistoricos(Turno at_turnoActualizado)
	    throws B2BException
	{
		FoliosSirUI lfsui_dataFinal;

		lfsui_dataFinal = null;

		if(at_turnoActualizado != null)
		{
			String ls_idTurno;

			ls_idTurno = at_turnoActualizado.getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					boolean                               lb_folio;
					boolean                               lb_sir;
					Collection<PagosUI>                   lcpui_pagosUI;
					Collection<LiquidacionesHistoricosUI> lclhui_liquidacionesHistoricosUI;
					Collection<TrazabilidadTurnoUI>       lcttui_trazabilidadTurnoUI;

					lb_folio                             = at_turnoActualizado.isFolio();
					lb_sir                               = at_turnoActualizado.isSir();
					lcpui_pagosUI                        = new ArrayList<PagosUI>();
					lclhui_liquidacionesHistoricosUI     = new ArrayList<LiquidacionesHistoricosUI>();
					lcttui_trazabilidadTurnoUI           = new ArrayList<TrazabilidadTurnoUI>();

					if(lb_folio)
					{
						Collection<PagosFolio>                   lcpf_pagosFolio;
						Collection<LiquidacionesHistoricosFolio> lclhf_liquidacionesHistoricosFolio;
						Collection<TrazabilidadTurnoFolio>       lcttf_trazabilidadTurnoFolio;

						lcpf_pagosFolio                        = DaoCreator.getPagosFolioDAO(ldm_manager)
								                                               .findAllByTurnoActualizado(ls_idTurno);
						lclhf_liquidacionesHistoricosFolio     = DaoCreator.getLiquidacionesHistoricosFolioDAO(
							    ldm_manager
							).findAllByTurnoActualizadoFolio(ls_idTurno);
						lcttf_trazabilidadTurnoFolio           = DaoCreator.getTrazabilidadTurnoFolioDAO(ldm_manager)
								                                               .findAllByTurnoActualizado(ls_idTurno);

						if(CollectionUtils.isValidCollection(lcpf_pagosFolio))
						{
							for(PagosFolio lpf_temp : lcpf_pagosFolio)
							{
								if(lpf_temp != null)
									lcpui_pagosUI.add(new PagosUI(lpf_temp));
							}
						}

						if(CollectionUtils.isValidCollection(lclhf_liquidacionesHistoricosFolio))
						{
							for(LiquidacionesHistoricosFolio llhf_temp : lclhf_liquidacionesHistoricosFolio)
							{
								if(llhf_temp != null)
									lclhui_liquidacionesHistoricosUI.add(new LiquidacionesHistoricosUI(llhf_temp));
							}
						}

						if(CollectionUtils.isValidCollection(lcttf_trazabilidadTurnoFolio))
						{
							for(TrazabilidadTurnoFolio lttf_temp : lcttf_trazabilidadTurnoFolio)
							{
								if(lttf_temp != null)
									lcttui_trazabilidadTurnoUI.add(new TrazabilidadTurnoUI(lttf_temp));
							}
						}
					}
					else if(lb_sir)
					{
						Collection<PagosSir>                   lcps_pagosSir;
						Collection<LiquidacionesHistoricosSir> lclhs_liquidacionesHistoricosSir;
						Collection<TrazabilidadTurnoSir>       lctts_trazabilidadTurnoSir;

						lcps_pagosSir                        = DaoCreator.getPagosSirDAO(ldm_manager)
								                                             .findAllByTurnoActualizado(ls_idTurno);
						lclhs_liquidacionesHistoricosSir     = DaoCreator.getLiquidacionesHistoricosSirDAO(ldm_manager)
								                                             .findAllByTurnoActualizadoSir(ls_idTurno);
						lctts_trazabilidadTurnoSir           = DaoCreator.getTrazabilidadTurnoSirDAO(ldm_manager)
								                                             .findAllByTurnoActualizado(ls_idTurno);

						if(CollectionUtils.isValidCollection(lcps_pagosSir))
						{
							for(PagosSir lps_temp : lcps_pagosSir)
							{
								if(lps_temp != null)
									lcpui_pagosUI.add(new PagosUI(lps_temp));
							}
						}

						if(CollectionUtils.isValidCollection(lclhs_liquidacionesHistoricosSir))
						{
							for(LiquidacionesHistoricosSir llhs_temp : lclhs_liquidacionesHistoricosSir)
							{
								if(llhs_temp != null)
									lclhui_liquidacionesHistoricosUI.add(new LiquidacionesHistoricosUI(llhs_temp));
							}
						}

						if(CollectionUtils.isValidCollection(lctts_trazabilidadTurnoSir))
						{
							for(TrazabilidadTurnoSir ltts_temp : lctts_trazabilidadTurnoSir)
							{
								if(ltts_temp != null)
									lcttui_trazabilidadTurnoUI.add(new TrazabilidadTurnoUI(ltts_temp));
							}
						}
					}

					lfsui_dataFinal = new FoliosSirUI(
						    lcpui_pagosUI, lclhui_liquidacionesHistoricosUI, lcttui_trazabilidadTurnoUI
						);
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("cargarBandejaHistoricos", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}

		return lfsui_dataFinal;
	}

	/**
	 * Método encargado de precargar la bandeja de consulta de trazabilidad
	 *
	 * @param at_parametros Objeto de tipo Trazabilidad que contiene parametros de busqueda
	 * @param ab_onlyTurno Variable de tipo boolean que contienen flag que intica el tipo de consulta
	 * @return Objeto de tipo ConsultaTrazabilidad que contiene la informacion relacionada con la bandeja de consultar trazabilidad
	 * @throws B2BException
	 */
	public synchronized ConsultaTrazabilidad cargarInfoBandejaConsultaTrazabilidad(
	    Trazabilidad at_parametros, boolean ab_onlyTurno
	)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		ConsultaTrazabilidad lct_return;

		lct_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lct_return = cargarInfoBandejaConsultaTrazabilidad(at_parametros, ab_onlyTurno, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarInfoBandejaConsultaTrazabilidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_return;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id_turno específico
	 *
	 * @param at_turno Objeto de tipo Turno que contiene parametros de busqueda
	 * @return Objeto de tipo Trazabilidad que contiene la informacion relacionada con el detalle de la trazabilidad para un turno
	 * @throws B2BException
	 */
	public synchronized Trazabilidad detalleTurno(Turno at_turno)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		Trazabilidad lt_trazabilidad;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lt_trazabilidad     = null;

		try
		{
			lt_trazabilidad = detalleTurno(at_turno, ldm_manager);
			lt_trazabilidad.setDecisionCalificacion(
			    getReferenceBusiness().findDecisionCalificacion(lt_trazabilidad, ldm_manager)
			);

			;

			{
				String ls_idTurno;

				ls_idTurno = at_turno.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Turno         lt_turno;
					TipoCausalDAO ltcd_tipoCausalDAO;

					lt_turno               = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);
					ltcd_tipoCausalDAO     = DaoCreator.getTipoCausalDAO(ldm_manager);

					if(lt_turno != null)
					{
						String ls_migrado;

						ls_migrado = lt_turno.getMigrado();

						if(StringUtils.isValidString(ls_migrado))
						{
							Collection<NotaDevolutiva> lcnd_result;

							lcnd_result = new ArrayList<NotaDevolutiva>();

							if(ls_migrado.equalsIgnoreCase(EstadoCommon.S))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
										                             .findById(lt_turno.getIdCirculo());

								if(lcr_circuloRegistral != null)
								{
									String ls_sistemaOrigen;

									ls_sistemaOrigen = lcr_circuloRegistral.getSistemaOrigen();

									if(StringUtils.isValidString(ls_sistemaOrigen))
									{
										Collection<TrazabilidadTurnoFolioNotaDevolutiva> lcttfnd_turnoFolio;
										Collection<TrazabilidadTurnoSirNotaDevolutiva>   lcttsnd_turnoSir;

										lcttfnd_turnoFolio     = null;
										lcttsnd_turnoSir       = null;

										switch(ls_sistemaOrigen)
										{
											case SistemaOrigenCommon.FOLIO:
												lcttfnd_turnoFolio = DaoCreator.getTrazabilidadTurnoFolioNotaDevolutivaDAO(
													    ldm_manager
													).findAllByTurnoActualizado(ls_idTurno);

												break;

											case SistemaOrigenCommon.SIR:
												lcttsnd_turnoSir = DaoCreator.getTrazabilidadTurnoSirNotaDevolutivaDAO(
													    ldm_manager
													).findAllByTurnoActualizado(ls_idTurno);

												break;

											default:
												break;
										}

										if(CollectionUtils.isValidCollection(lcttfnd_turnoFolio))
										{
											for(TrazabilidadTurnoFolioNotaDevolutiva ltf_temp : lcttfnd_turnoFolio)
											{
												if(ltf_temp != null)
												{
													NotaDevolutiva lnd_notaDevolutiva;

													lnd_notaDevolutiva = new NotaDevolutiva();

													lnd_notaDevolutiva.setComentario(
													    ltf_temp.getCausalDevolucionComentario()
													);
													lnd_notaDevolutiva.setDescripcionCausal(ltf_temp.getTipo());

													{
														double ld_codigo;

														ld_codigo = ltf_temp.getCodigo();

														if(ld_codigo > 0)
															lnd_notaDevolutiva.setIdCausal(
															    StringUtils.getString(ld_codigo)
															);
													}

													lcnd_result.add(lnd_notaDevolutiva);
												}
											}
										}

										else if(CollectionUtils.isValidCollection(lcttsnd_turnoSir))
										{
											for(TrazabilidadTurnoSirNotaDevolutiva lts_temp : lcttsnd_turnoSir)
											{
												if(lts_temp != null)
												{
													NotaDevolutiva lnd_notaDevolutiva;

													lnd_notaDevolutiva = new NotaDevolutiva();

													lnd_notaDevolutiva.setComentario(
													    lts_temp.getCausalDevolucionComentario()
													);
													lnd_notaDevolutiva.setDescripcionCausal(lts_temp.getTipo());

													{
														double ld_codigo;

														ld_codigo = lts_temp.getCodigo();

														if(ld_codigo > 0)
															lnd_notaDevolutiva.setIdCausal(
															    StringUtils.getString(ld_codigo)
															);
													}

													lcnd_result.add(lnd_notaDevolutiva);
												}
											}
										}
									}
								}
							}
							else if(ls_migrado.equalsIgnoreCase(EstadoCommon.N))
							{
								TurnoHistoria lth_turnoHistoria;

								lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(
									    ls_idTurno
									);

								if(lth_turnoHistoria != null)
								{
									Collection<NotaDevolutiva> lcnd_notaDevolutiva;

									lcnd_notaDevolutiva = DaoCreator.getNotaDevolutivaDAO(ldm_manager)
											                            .findByTurnoHistoria(
											    StringUtils.getString(lth_turnoHistoria.getIdTurnoHistoria())
											);

									if(CollectionUtils.isValidCollection(lcnd_notaDevolutiva))
									{
										for(NotaDevolutiva lnd_temp : lcnd_notaDevolutiva)
										{
											if(lnd_temp != null)
											{
												TipoCausal ltc_tipoCausal;

												ltc_tipoCausal = ltcd_tipoCausalDAO.findById(lnd_temp.getIdCausal());

												if(ltc_tipoCausal != null)
													lnd_temp.setDescripcionCausal(
													    ltc_tipoCausal.getDescripcionMotivo()
													);

												lcnd_result.add(lnd_temp);
											}
										}
									}
								}
							}

							{
								Collection<TurnoHistoria> lcth_cth;

								lcth_cth = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
										                 .findAllByIdSolicitud(lt_turno.getIdSolicitud());

								if(CollectionUtils.isValidCollection(lcth_cth))
								{
									for(TurnoHistoria lth_temp : lcth_cth)
									{
										if(lth_temp != null)
										{
											BigDecimal lbd_idEtapa;

											lbd_idEtapa = lth_temp.getIdEtapa();

											if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
											{
												long ll_idEtapaTurno;

												ll_idEtapaTurno = NumericUtils.getLong(lbd_idEtapa);

												if(
												    (ll_idEtapaTurno > NumericUtils.DEFAULT_LONG_VALUE)
													    && (ll_idEtapaTurno == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
												)
												{
													Collection<NotaDevolutiva> lcnd_notaDevolutiva;

													lcnd_notaDevolutiva = DaoCreator.getNotaDevolutivaDAO(ldm_manager)
															                            .findByTurnoHistoria(
															    StringUtils.getString(lth_temp.getIdTurnoHistoria())
															);

													if(CollectionUtils.isValidCollection(lcnd_notaDevolutiva))
													{
														for(NotaDevolutiva lnd_temp : lcnd_notaDevolutiva)
														{
															if(lnd_temp != null)
															{
																TipoCausal ltc_tipoCausal;

																ltc_tipoCausal = ltcd_tipoCausalDAO.findById(
																	    lnd_temp.getIdCausal()
																	);

																if(ltc_tipoCausal != null)
																	lnd_temp.setDescripcionCausal(
																	    ltc_tipoCausal.getDescripcionMotivo()
																	);

																lcnd_result.add(lnd_temp);
															}
														}
													}
												}
											}
										}
									}
								}
							}

							lt_trazabilidad.setNotaDevolutiva(lcnd_result);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("detalleTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_trazabilidad;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id turno determinado.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Turno que contiene el id turno requerido para
	 *            ejecutar sentencia SQL.
	 * @return Retorna una colección de datos de tipo ConsultaTrazabilidad que
	 *         coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Collection<ConsultaTrazabilidad> findConsultaTraza(Turno at_parametros)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<ConsultaTrazabilidad> lcct_consultaTraza;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lcct_consultaTraza     = null;

		try
		{
			lcct_consultaTraza = findConsultaTraza(at_parametros, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConsultaTraza", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcct_consultaTraza;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id solicitud determinado.
	 *
	 * @param as_nir
	 *            Objeto de tipo String que contiene el id solicitud requerido para
	 *            ejecutar sentencia SQL.
	 * @return Retorna una colección de datos de tipo TurnoHistoria que
	 *         coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Collection<ConsultaTrazabilidad> findConsultaTrazaSolicitud(String as_nir)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		Collection<ConsultaTrazabilidad> lcct_consultaTraza;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lcct_consultaTraza     = null;

		try
		{
			if(StringUtils.isValidString(as_nir))
			{
				ConsultaTrazabilidadDAO lctd_consultaTrazabilidadDAO;
				SolicitudDAO            lsd_solicitudDAO;
				Solicitud               ls_solicitud;

				lctd_consultaTrazabilidadDAO     = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager);
				lsd_solicitudDAO                 = DaoCreator.getSolicitudDAO(ldm_manager);

				ls_solicitud = lsd_solicitudDAO.findByNir(as_nir);

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
						lcct_consultaTraza = lctd_consultaTrazabilidadDAO.findByTurnoSolicitud(ls_idSolicitud);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConsultaTrazaSolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcct_consultaTraza;
	}

	/**
	 * Método encargado de consultar la data de los turnos derivados
	 *
	 * @param as_idTurno
	 *            Variable de tipo String que contiene el id del turno en trámite.
	 * @return Colección que contiene los datos de los turnos derivados.
	 * @throws B2BException
	 */
	public synchronized Collection<Trazabilidad> findDataAsociada(String as_idTurno)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<Trazabilidad> lct_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_return      = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				Collection<TurnoDerivado> lctd_turnos;
				TurnoDerivadoDAO          ltd_DAO;
				TurnoDerivado             ltd_turnoDerivado;

				ltd_DAO               = DaoCreator.getTurnoDerivadoDAO(ldm_manager);
				ltd_turnoDerivado     = new TurnoDerivado();

				ltd_turnoDerivado.setIdTurnoPadre(as_idTurno);

				lctd_turnos = ltd_DAO.findByIdTurnoPadreVinculados(ltd_turnoDerivado);

				if(CollectionUtils.isValidCollection(lctd_turnos))
				{
					TurnoDAO     lt_DAO;
					SolicitudDAO ls_DAO;

					lct_return     = new ArrayList<Trazabilidad>();
					lt_DAO         = DaoCreator.getTurnoDAO(ldm_manager);
					ls_DAO         = DaoCreator.getSolicitudDAO(ldm_manager);

					for(TurnoDerivado ltd_iterador : lctd_turnos)
					{
						if(ltd_iterador != null)
						{
							String ls_idTurno;

							ls_idTurno = ltd_iterador.getIdTurnoHijo();

							if(StringUtils.isValidString(ls_idTurno))
							{
								Turno lt_turno;

								lt_turno = new Turno();

								lt_turno.setIdTurno(ls_idTurno);

								lt_turno = lt_DAO.findById(lt_turno);

								if(lt_turno != null)
								{
									String       ls_idSolicitud;
									Trazabilidad lt_data;

									ls_idSolicitud     = lt_turno.getIdSolicitud();
									lt_data            = new Trazabilidad();

									lt_data.setTurno(lt_turno);

									if(StringUtils.isValidString(ls_idSolicitud))
									{
										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(ls_idSolicitud);

										ls_solicitud = ls_DAO.findById(ls_solicitud);

										if(ls_solicitud != null)
										{
											ls_solicitud.setTramiteAsociado(ltd_DAO.findTramiteVinculado(as_idTurno));
											lt_data.setSolicitud(ls_solicitud);
										}
									}

									lct_return.add(lt_data);
								}
							}
						}
					}

					if(lct_return.isEmpty())
						lct_return = null;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDataAsociada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_return;
	}

	/**
	 * Método encargado de consultar el detalle de un documento para un id turno
	 * determinado.
	 *
	 * @param art_rt
	 *            Objeto de tipo ReasignarTurnos que contiene el id turno requerido
	 *            para ejecutar sentencia SQL.
	 * @return Retorna un objeto de tipo ReasignarTurnos que contiene la información
	 *         de Reasignación
	 * @throws B2BException
	 */
	public synchronized ReasignarTurnos findDatosReasignacion(ReasignarTurnos art_rt)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(art_rt != null)
				art_rt = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findDatosReasignacion(art_rt);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosReasignacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return art_rt;
	}

	/**
	 * Método encargado de consultar el detalle de un documento para un id turno
	 * determinado.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Turno que contiene el id turno requerido para
	 *            ejecutar sentencia SQL.
	 * @return Retorna un objeto de tipo InformacionDocumento que coincide con los
	 *         parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized InformacionDocumento findInfoDoc(Turno at_parametros)
	    throws B2BException
	{
		DAOManager           ldm_manager;
		InformacionDocumento lid_infoDoc;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lid_infoDoc     = null;

		try
		{
			String ls_it;
			ls_it = at_parametros.getIdTurno();

			if(StringUtils.isValidString(ls_it))
			{
				lid_infoDoc = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findInfoDoc(at_parametros);

				if(lid_infoDoc != null)
				{
					Collection<SolicitudMatricula> lcsm_return;
					StringBuilder                  lsb_sb;
					lsb_sb     = new StringBuilder();

					lcsm_return = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findMatriculasByturno(ls_it);

					if(CollectionUtils.isValidCollection(lcsm_return))
					{
						int li_contador;
						li_contador = 0;

						for(SolicitudMatricula lsm_tmp : lcsm_return)
						{
							li_contador++;

							if(li_contador == lcsm_return.size())
							{
								if(lsm_tmp != null)
									lsb_sb.append(StringUtils.getStringNotNull(lsm_tmp.getIdCirculo()));
							}
							else
							{
								if(lsm_tmp != null)
									lsb_sb.append(StringUtils.getStringNotNull(lsm_tmp.getIdCirculo()) + ",");
							}
						}
					}

					lid_infoDoc.setInfoAlertas(
					    DaoCreator.getAlertaTurnoTramiteDAO(ldm_manager).findByIdTurno(ls_it, lsb_sb.toString())
					);
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInfoDoc", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lid_infoDoc;
	}

	/**
	 * Método encargado de buscar las matrículas asociadas al turno.
	 *
	 * @param as_idTurno
	 *            Variable de tipo String la cual contiene el id turno a consultar.
	 * @return Colección de matrículas asociadas al turno.
	 * @throws B2BException
	 */
	public synchronized Collection<SolicitudMatricula> findMatriculas(String as_idTurno)
	    throws B2BException
	{
		Collection<SolicitudMatricula> lcsm_return;
		DAOManager                     ldm_manager;

		lcsm_return     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_idTurno != null)
			{
				SolicitudMatriculaDAO losm_DAO;
				losm_DAO     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);

				lcsm_return = losm_DAO.findMatriculasByturno(as_idTurno);

				if(CollectionUtils.isValidCollection(lcsm_return))
				{
					Iterator<SolicitudMatricula> liosm_sm;
					liosm_sm = lcsm_return.iterator();

					while(liosm_sm.hasNext())
					{
						if(liosm_sm != null)
						{
							SolicitudMatricula losm_smtmp;

							losm_smtmp = liosm_sm.next();

							if(losm_smtmp != null)
							{
								String[]                    lls_tmp;
								String                      ls_circulo;
								String                      ls_matricula;
								AnotacionPredio             lap_tmp;
								Collection<AnotacionPredio> lcap_tmp;
								boolean                     lb_alerta;

								lls_tmp          = losm_smtmp.getIdCirculo().split("-");
								ls_circulo       = lls_tmp[0];
								ls_matricula     = lls_tmp[1];
								lap_tmp          = new AnotacionPredio();

								losm_smtmp.setIdCirculo(ls_circulo);
								losm_smtmp.setIdMatricula(NumericUtils.getLong(ls_matricula));
								lap_tmp.setIdCirculo(ls_circulo);
								lap_tmp.setIdMatricula(NumericUtils.getLongWrapper(ls_matricula));

								lcap_tmp     = DaoCreator.getAnotacionPredioDAO(ldm_manager)
										                     .findNaturalezaByMatricula(lap_tmp);

								lb_alerta = false;

								if(CollectionUtils.isValidCollection(lcap_tmp))
								{
									String                   ls_idNaturaleza;
									long                     ll_version;
									AlertaNaturalezaJuridica lontj_tmp;

									for(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio loapr_tmp : lcap_tmp)
									{
										if(loapr_tmp != null)
										{
											ls_idNaturaleza     = loapr_tmp.getIdNaturalezaJuridica();
											ll_version          = loapr_tmp.getVersion();

											lontj_tmp = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager)
													                  .findById(ls_idNaturaleza, ll_version);

											if(lontj_tmp != null)
												lb_alerta = true;
										}
									}

									losm_smtmp.setAlerta(lb_alerta);
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

			clh_LOGGER.error("findMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcsm_return;
	}

	/**
	 * Método encargado de consultar la fase actual para un id turno y/o nir
	 * determinados.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Trazabilidad que contiene el id turno , nir y id
	 *            solicitud determinados.
	 * @return Retorna un objeto de tipo Trazabilidad que coincide con los
	 *         parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Trazabilidad findProceso(Trazabilidad at_parametros)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		Trazabilidad lt_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lt_datos        = null;

		try
		{
			Solicitud ls_s;
			Turno     lt_t;
			lt_t     = at_parametros.getTurno();
			ls_s     = at_parametros.getSolicitud();

			String ls_sn;
			String ls_it;
			ls_it    = lt_t.getIdTurno();
			ls_sn    = ls_s.getNir();

			if(!StringUtils.isValidString(ls_sn) && !StringUtils.isValidString(ls_it))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);

			if(StringUtils.isValidString(ls_sn))
				at_parametros.setSolicitud(DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_s));

			if(at_parametros.getSolicitud() == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);

			lt_datos = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findProceso(at_parametros);

			if(lt_datos == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProceso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_datos;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id turno ,nir y/o id solicitud determinado.
	 *
	 * @param at_parametros de tipo Trazabilidad que contiene el id turno , nir y id solicitud determinados.
	 * @param ab_onlyTurno Variable de tipo boolean que contienen flag que intica el tipo de consulta
	 * @return Retorna una colección de datos de tipo Trazabilidad que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_parametros, boolean ab_onlyTurno)
	    throws B2BException
	{
		Collection<Trazabilidad> lct_trazabilidades;

		lct_trazabilidades = null;

		if(at_parametros != null)
		{
			DAOManager ldm_manager;
			Solicitud  ls_solicitud;
			Turno      lt_turno;

			ldm_manager      = DaoManagerFactory.getDAOManager();
			ls_solicitud     = at_parametros.getSolicitud();
			lt_turno         = at_parametros.getTurno();

			if(ls_solicitud == null)
				ls_solicitud = new Solicitud();

			if(lt_turno == null)
				lt_turno = new Turno();

			try
			{
				lct_trazabilidades = findTrazabilidad(at_parametros, ab_onlyTurno, ls_solicitud, lt_turno, ldm_manager);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("findTrazabilidad", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lct_trazabilidades;
	}

	/**
	 * Método encargado de consultar los nir vinculados para un id turno ,nir y/o id
	 * solicitud determinado.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Trazabilidad que contiene el id turno , nir y id
	 *            solicitud determinados.
	 * @return Retorna una colección de datos de tipo Trazabilidad que coincide con
	 *         los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Collection<Trazabilidad> findTrazabilidadVinculados(Trazabilidad at_parametros)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<Trazabilidad> lct_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lct_datos       = null;

		try
		{
			if(at_parametros != null)
			{
				Turno     lt_turno;
				Solicitud ls_solicitud;

				lt_turno         = at_parametros.getTurno();
				ls_solicitud     = at_parametros.getSolicitud();

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

				if((lt_turno != null) || (ls_solicitud != null))
				{
					SolicitudDAO lsd_DAO;
					String       ls_idSolicitud;
					String       ls_idSolicitudNir;

					lsd_DAO               = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_idSolicitud        = (lt_turno != null) ? lt_turno.getIdSolicitud() : null;
					ls_idSolicitudNir     = (ls_solicitud != null) ? ls_solicitud.getNir() : null;

					if(StringUtils.isValidString(ls_idSolicitudNir))
					{
						ls_solicitud          = lsd_DAO.findByNir(ls_solicitud);
						ls_idSolicitudNir     = (ls_solicitud != null) ? ls_solicitud.getIdSolicitud() : null;
					}

					ls_idSolicitud = StringUtils.isValidString(ls_idSolicitud) ? ls_idSolicitud
						                                                       : (StringUtils.isValidString(
						    ls_idSolicitudNir
						) ? ls_idSolicitudNir : null);

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						TurnoHistoriaDAO  lthd_DAO;
						SolicitudAsociada lsa_solicitudAsociada;

						lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

						ls_solicitud              = new Solicitud();
						lsa_solicitudAsociada     = new SolicitudAsociada();
						ls_solicitud.setIdSolicitud(ls_idSolicitud);
						ls_solicitud = lsd_DAO.findById(ls_solicitud);

						if(ls_solicitud != null)
						{
							lsa_solicitudAsociada.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lsa_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
									                              .findByIdSolicitud(lsa_solicitudAsociada);

							if(lsa_solicitudAsociada != null)
							{
								Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;

								lcsa_solicitudesAsociadas = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
										                                  .findAllByIdSolicitud(
										    lsa_solicitudAsociada, false
										);

								if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
								{
									lct_datos = new ArrayList<Trazabilidad>();

									for(SolicitudAsociada lsa_temp : lcsa_solicitudesAsociadas)
									{
										if(lsa_temp != null)
										{
											String ls_idSol1;

											ls_idSol1 = lsa_temp.getIdSolicitud1();

											if(StringUtils.isValidString(ls_idSol1))
											{
												ls_solicitud.setIdSolicitud(ls_idSol1);
												ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
														                     .findById(ls_solicitud);

												Trazabilidad        lt_trazabilidad;
												TipoEstadoSolicitud ltes_estadoSolicitud;

												lt_trazabilidad          = new Trazabilidad();
												ltes_estadoSolicitud     = new TipoEstadoSolicitud();

												ltes_estadoSolicitud.setIdTipoEstadoSolicitud(
												    StringUtils.getString(ls_solicitud.getEstadoSolicitud())
												);

												ltes_estadoSolicitud = DaoCreator.getTipoEstadoSolicitudDAO(
													    ldm_manager
													).findById(ltes_estadoSolicitud);

												if(ltes_estadoSolicitud != null)
												{
													TurnoHistoria lth_turnoHistoria;

													lth_turnoHistoria = new TurnoHistoria();

													lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());

													lth_turnoHistoria = lthd_DAO.findBySolicitud(lth_turnoHistoria);

													if(lth_turnoHistoria != null)
													{
														BigDecimal lbd_etapa;

														lbd_etapa = lth_turnoHistoria.getIdEtapa();

														if(NumericUtils.isValidBigDecimal(lbd_etapa))
														{
															String ls_turno;

															lt_turno     = new Turno();
															ls_turno     = lth_turnoHistoria.getIdTurno();

															if(StringUtils.isValidString(ls_turno))
															{
																lt_turno.setIdTurno(ls_turno);
																lt_turno = DaoCreator.getTurnoDAO(ldm_manager)
																		                 .findById(lt_turno);

																if(lt_turno != null)
																{
																	{
																		Long ll_etapaTurno;

																		ll_etapaTurno = lt_turno.getIdEtapaActual();

																		if(NumericUtils.isValidLong(ll_etapaTurno))
																		{
																			Etapa le_etapa;

																			le_etapa = new Etapa();

																			le_etapa.setIdEtapa(
																			    NumericUtils.getLong(ll_etapaTurno)
																			);
																			le_etapa = DaoCreator.getEtapaDAO(
																				    ldm_manager
																				).findById(le_etapa);

																			if(le_etapa != null)
																			{
																				Fases lf_fase;

																				lf_fase = new Fases();

																				lf_fase.setIdFase(le_etapa.getIdFase());
																				lf_fase = DaoCreator.getFasesDAO(
																					    ldm_manager
																					).findById(lf_fase);

																				if(lf_fase != null)
																					lt_trazabilidad.setFases(lf_fase);
																			}
																		}
																	}

																	{
																		String ls_procesoTurno;

																		ls_procesoTurno = lt_turno.getIdProceso();

																		if(StringUtils.isValidString(ls_procesoTurno))
																		{
																			Proceso lp_proceso;

																			lp_proceso = new Proceso();

																			lp_proceso.setIdProceso(ls_procesoTurno);

																			lp_proceso = DaoCreator.getProcesoDAO(
																				    ldm_manager
																				).findById(lp_proceso);

																			if(lp_proceso != null)
																				lt_trazabilidad.setProceso(lp_proceso);
																		}
																	}
																}

																lt_trazabilidad.setTurno(lt_turno);
															}
														}
													}

													ls_solicitud.setNombreEstadoSolicitud(
													    ltes_estadoSolicitud.getNombre()
													);

													lt_trazabilidad.setSolicitud(ls_solicitud);

													lt_trazabilidad.setNirEtapa10(true);

													lct_datos.add(lt_trazabilidad);
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
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTrazabilidadVinculados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lct_datos;
	}

	/**
	 * Método encargado de consultar los turnos asociados.
	 *
	 * @param as_s
	 *            String con un Turno que contiene el id turno requerido para
	 *            ejecutar sentencia SQL.
	 * @return Retorna un String extraído de una colección con los turnos asociados
	 * @throws B2BException
	 */
	public String findTurnosDerivadosConIndicadorVinculado(String as_s)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		TurnoHistoriaDAO lthd_thd;
		String           ls_turnosAsociados;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lthd_thd               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		ls_turnosAsociados     = null;

		try
		{
			Collection<Aprobacion> lc_turnosDerivados;

			lc_turnosDerivados = lthd_thd.findTurnosDerivadosConIndicadorVinculado(as_s);

			if(CollectionUtils.isValidCollection(lc_turnosDerivados))
			{
				StringBuilder lsb_tmp;
				int           li_contador;

				lsb_tmp         = new StringBuilder();
				li_contador     = 0;

				for(Aprobacion loa_tmp : lc_turnosDerivados)
				{
					if(li_contador == (lc_turnosDerivados.size() - 1))
						lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados());
					else
					{
						lsb_tmp = lsb_tmp.append(loa_tmp.getTurnosAsociados() + ", ");
						li_contador++;
					}
				}

				if(StringUtils.isValidString(lsb_tmp.toString()))
					ls_turnosAsociados = lsb_tmp.toString();
			}
			else
				ls_turnosAsociados = IdentificadoresCommon.SIN_TURNOS_ASOCIADOS;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnosDerivados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_turnosAsociados;
	}

	/**
	 * Método encargado de consultar los datos de trazabilidad para un caso determinado
	 *
	 * @param at_parametros Objeto de tipo Trazabilidad que contiene parametros de busqueda
	 * @param ab_onlyTurno Variable de tipo boolean que contienen flag que intica el tipo de consulta
	 * @param adm_manager Objeto de tipo DAOManager que contiene transaccion de bases de datos activa
	 * @return Objeto de tipo ConsultaTrazabilidad que contiene la informacion relacionada con la bandeja de consultar trazabilidad
	 * @throws B2BException
	 */
	protected synchronized ConsultaTrazabilidad cargarInfoBandejaConsultaTrazabilidad(
	    Trazabilidad at_parametros, boolean ab_onlyTurno, DAOManager adm_manager
	)
	    throws B2BException
	{
		ConsultaTrazabilidad lct_return;

		lct_return = null;

		if(at_parametros != null)
		{
			try
			{
				SolicitudDAO             lsd_DAO;
				SolicitudAsociadaDAO     lsad_DAO;
				TurnoDAO                 ltd_DAO;
				TurnoHistoriaDAO         lthd_DAO;
				Solicitud                ls_solicitud;
				Solicitud                ls_solicitudParam;
				String                   ls_expediente;
				String                   ls_idTurno;
				String                   ls_nir;
				Turno                    lt_turno;
				Turno                    lt_turnoParam;
				Collection<Trazabilidad> lct_datos;

				lsd_DAO               = DaoCreator.getSolicitudDAO(adm_manager);
				lsad_DAO              = DaoCreator.getSolicitudAsociadaDAO(adm_manager);
				ltd_DAO               = DaoCreator.getTurnoDAO(adm_manager);
				lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				lt_turnoParam         = at_parametros.getTurno();
				ls_solicitudParam     = at_parametros.getSolicitud();
				ls_nir                = (ls_solicitudParam != null) ? ls_solicitudParam.getNir() : null;
				ls_expediente         = (lt_turnoParam != null) ? lt_turnoParam.getExpediente() : null;
				ls_idTurno            = (lt_turnoParam != null) ? lt_turnoParam.getIdTurno() : null;
				ls_solicitud          = lsd_DAO.findByNir(ls_solicitudParam);
				lt_turno              = ltd_DAO.findByIdTurnoExpediente(ls_idTurno, ls_expediente);

				if((lt_turno == null) && (ls_solicitud == null))
				{
					boolean lb_turno;
					boolean lb_expediente;

					lb_turno          = StringUtils.isValidString(ls_idTurno);
					lb_expediente     = StringUtils.isValidString(ls_expediente);

					if(lb_turno && lb_expediente)
						throw new B2BException(ErrorKeys.ERROR_TURNO_EXPEDIENTE_CONSULTA);
					else if(lb_expediente)
						throw new B2BException(ErrorKeys.ERROR_EXPEDIENTE);
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_TURNO_EXPEDIENTE);
				}
				else if(ls_solicitud == null)
					ls_solicitud = lsd_DAO.findById(new Solicitud(lt_turno.getIdSolicitud()));
				else if(lt_turno == null)
					lt_turno = new Turno();

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;
					String ls_idSolicitudTurno;
					String ls_nirTurno;

					ls_idSolicitud          = ls_solicitud.getIdSolicitud();
					ls_idSolicitudTurno     = (lt_turno != null) ? lt_turno.getIdSolicitud() : null;
					ls_nirTurno             = ls_solicitud.getNir();

					if(
					    StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idSolicitudTurno)
						    && !ls_idSolicitudTurno.equalsIgnoreCase(ls_idSolicitud)
					)
						throw new B2BException(ErrorKeys.ERROR_NIR_TURNO);

					if(
					    StringUtils.isValidString(ls_nir) && StringUtils.isValidString(ls_nirTurno)
						    && !ls_nirTurno.equalsIgnoreCase(ls_nir)
					)
						throw new B2BException(ErrorKeys.ERROR_NIR_TURNO);
				}

				at_parametros.setTurno(lt_turno);

				lct_datos = findTrazabilidad(at_parametros, ab_onlyTurno, ls_solicitud, lt_turno, adm_manager);

				if(CollectionUtils.isValidCollection(lct_datos))
				{
					boolean           lb_fijarNir;
					ReferenceBusiness lrb_business;

					lb_fijarNir      = false;
					lrb_business     = getReferenceBusiness();
					lct_return       = new ConsultaTrazabilidad();

					lct_return.setSolicitud(ls_solicitud);

					for(Trazabilidad lt_temp : lct_datos)
					{
						if(lt_temp != null)
						{
							Turno  lt_tmp;
							String ls_idTurnoTmp;

							lt_tmp            = lt_temp.getTurno();
							ls_idTurnoTmp     = lt_tmp.getIdTurno();

							if((lt_tmp != null) && !StringUtils.isValidString(ls_idTurnoTmp))
							{
								Object[] aoa_messageArgs = new String[1];

								aoa_messageArgs[0] = ls_solicitud.getNir();

								lct_return.setMensaje(
								    addMessage(MessagesKeys.EL_NIR_NO_TIENE_TURNO, aoa_messageArgs, false)
								);
							}

							{
								Solicitud ls_solicitudTemp;

								ls_solicitudTemp = lt_temp.getSolicitud();

								if(
								    (ls_solicitudTemp != null) && StringUtils.isValidString(ls_solicitudTemp.getNir())
									    && lt_temp.isNirEtapa10()
								)
								{
									lct_return.setTrazabilidadInfo(lt_temp);
									lb_fijarNir = false;
								}

								lt_temp.setDecisionCalificacion(
								    lrb_business.findDecisionCalificacion(lt_temp, adm_manager)
								);
								lt_temp.setTipificacionTurno(
								    lrb_business.findTipificacionTurno(ls_idTurnoTmp, adm_manager)
								);
							}
						}
					}

					if(lb_fijarNir)
						lct_return.setTrazabilidadInfo(findProceso(at_parametros));

					lct_return.setEstado(true);
					lct_return.setTrazabilidad(lct_datos);

					if(ls_solicitud != null)
					{
						boolean                       lb_indVinculadoN;
						boolean                       lb_indVinculadoS;
						String                        ls_idSolicitud;
						String                        ls_turnoAfectado;
						Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;
						Collection<SolicitudAsociada> lcsa_solicitudesVinculadas;
						Turno                         lt_turnoSolicitud;
						AlertaTurnoTramiteDAO         lattd_alertaTurnoTramiteDAO;

						lb_indVinculadoN                = false;
						lb_indVinculadoS                = false;
						ls_idSolicitud                  = ls_solicitud.getIdSolicitud();
						ls_turnoAfectado                = null;
						lcsa_solicitudesAsociadas       = null;
						lcsa_solicitudesVinculadas      = null;
						lt_turnoSolicitud               = ltd_DAO.findByIdSolicitudProceso(
							    new Turno(ls_idSolicitud, ls_solicitud.getIdProceso())
							);
						lattd_alertaTurnoTramiteDAO     = DaoCreator.getAlertaTurnoTramiteDAO(adm_manager);

						lcsa_solicitudesAsociadas     = lsad_DAO.findByIdSolicitudIndVinculado(
							    new SolicitudAsociada(ls_idSolicitud, false, EstadoCommon.N)
							);
						lb_indVinculadoN              = CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas);

						lcsa_solicitudesVinculadas     = lsad_DAO.findByIdSolicitudIndVinculado(
							    new SolicitudAsociada(ls_idSolicitud, false, EstadoCommon.S)
							);
						lb_indVinculadoS               = CollectionUtils.isValidCollection(lcsa_solicitudesVinculadas);
						lct_return.setTurnoSolicitud(lt_turnoSolicitud);

						if(lb_indVinculadoN)
						{
							Collection<NirVinculado> lcnv_nirsAsociados;

							lcnv_nirsAsociados = new ArrayList<NirVinculado>();

							for(SolicitudAsociada lsa_solicitudTemp : lcsa_solicitudesAsociadas)
							{
								if(lsa_solicitudTemp != null)
								{
									Solicitud ls_temp;

									ls_temp = lsd_DAO.findById(new Solicitud(lsa_solicitudTemp.getIdSolicitud1()));

									if(ls_temp != null)
									{
										if(!StringUtils.isValidString(ls_turnoAfectado))
										{
											AlertaTurnoTramite latt_alerta;

											latt_alerta = lattd_alertaTurnoTramiteDAO.findFirstByIdSolicitudVinculada(
												    ls_temp.getIdSolicitud()
												);

											if(latt_alerta != null)
												ls_turnoAfectado = latt_alerta.getIdTurnoAfectado();
										}

										Turno lt_temp;

										lt_temp = ltd_DAO.findByIdSolicitudProceso(
											    new Turno(ls_temp.getIdSolicitud(), ls_temp.getIdProceso())
											);

										if(lt_temp != null)
											lcnv_nirsAsociados.add(findDataNirs(ls_temp, lt_temp, true, adm_manager));
										else
										{
											TurnoHistoria lth_turnoHistoria;

											lth_turnoHistoria = lthd_DAO.findBySolicitudEtapa(
												    new TurnoHistoria(
												        ls_temp.getIdSolicitud(),
												        NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_INICIAL)
												    )
												);

											if(lth_turnoHistoria != null)
											{
												lt_temp = new Turno(lth_turnoHistoria.getIdTurno());

												lcnv_nirsAsociados.add(findDataNirs(ls_temp, lt_temp, adm_manager));
											}
										}
									}
								}
							}

							lct_return.setTurnoAfectado(
							    StringUtils.isValidString(ls_turnoAfectado) ? ls_turnoAfectado
							                                                : ConstanteCommon.SIN_INFORMACION
							);
							lct_return.setNirVinculados(lcnv_nirsAsociados);
							lct_return.setNirPrincipal(findDataNirs(ls_solicitud, lt_turnoSolicitud, adm_manager));
						}

						if(lb_indVinculadoS)
						{
							Collection<NirVinculado> lcnv_nirsVinculados;

							lcnv_nirsVinculados = new ArrayList<NirVinculado>();

							for(SolicitudAsociada lsa_solicitudTemp : lcsa_solicitudesVinculadas)
							{
								if(lsa_solicitudTemp != null)
								{
									Solicitud ls_temp;

									ls_temp = lsd_DAO.findById(new Solicitud(lsa_solicitudTemp.getIdSolicitud1()));

									if(ls_temp != null)
									{
										Turno lt_temp;

										lt_temp = ltd_DAO.findByIdSolicitudProceso(
											    new Turno(ls_temp.getIdSolicitud(), ls_temp.getIdProceso())
											);

										if(lt_temp != null)
											lcnv_nirsVinculados.add(findDataNirs(ls_temp, lt_temp, true, adm_manager));
									}
								}
							}

							lct_return.setTurnosVinculadosNirVinculados(lcnv_nirsVinculados);
							lct_return.setTurnosVinculadosNirPrincipal(
							    findDataNirs(ls_solicitud, lt_turnoSolicitud, adm_manager)
							);
						}
						else if(!lb_indVinculadoN && !lb_indVinculadoS)
						{
							SolicitudAsociada lsa_solicitudAsociada;

							lsa_solicitudAsociada = lsad_DAO.findByIdSol1(new SolicitudAsociada(ls_idSolicitud, true));

							if(lsa_solicitudAsociada != null)
							{
								Solicitud ls_solicitudTemp;

								ls_solicitudTemp = lsd_DAO.findById(
									    new Solicitud(lsa_solicitudAsociada.getIdSolicitud())
									);

								if(ls_solicitudTemp != null)
								{
									Turno lt_temp;

									lt_temp = ltd_DAO.findByIdSolicitudProceso(
										    new Turno(
										        ls_solicitudTemp.getIdSolicitud(), ls_solicitudTemp.getIdProceso()
										    )
										);

									String ls_idSolTemp;

									ls_idSolTemp = ls_solicitudTemp.getIdSolicitud();

									{
										Collection<NirVinculado> lcnv_nirsVinculados;

										lcnv_nirsVinculados            = new ArrayList<NirVinculado>();
										lcsa_solicitudesVinculadas     = lsad_DAO.findByIdSolicitudIndVinculado(
											    new SolicitudAsociada(ls_idSolTemp, false, EstadoCommon.S)
											);

										if(CollectionUtils.isValidCollection(lcsa_solicitudesVinculadas))
										{
											for(SolicitudAsociada lsa_solicitudTemp : lcsa_solicitudesVinculadas)
											{
												if(lsa_solicitudTemp != null)
												{
													Solicitud ls_temp;

													ls_temp = lsd_DAO.findById(
														    new Solicitud(lsa_solicitudTemp.getIdSolicitud1())
														);

													if(ls_temp != null)
													{
														Turno lt_temporal;
														lt_temporal = ltd_DAO.findByIdSolicitudProceso(
															    new Turno(
															        ls_temp.getIdSolicitud(), ls_temp.getIdProceso()
															    )
															);

														if(lt_temporal != null)
															lcnv_nirsVinculados.add(
															    findDataNirs(ls_temp, lt_temporal, true, adm_manager)
															);
													}
												}
											}

											lct_return.setTurnosVinculadosNirVinculados(lcnv_nirsVinculados);
											lct_return.setTurnosVinculadosNirPrincipal(
											    findDataNirs(ls_solicitudTemp, lt_temp, adm_manager)
											);
										}
									}

									{
										Collection<NirVinculado> lcnv_nirsAsociados;

										lcnv_nirsAsociados            = new ArrayList<NirVinculado>();
										lcsa_solicitudesAsociadas     = lsad_DAO.findByIdSolicitudIndVinculado(
											    new SolicitudAsociada(ls_idSolTemp, false, EstadoCommon.N)
											);

										if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
										{
											for(SolicitudAsociada lsa_solicitudTemp : lcsa_solicitudesAsociadas)
											{
												if(lsa_solicitudTemp != null)
												{
													Solicitud ls_temp;

													ls_temp = lsd_DAO.findById(
														    new Solicitud(lsa_solicitudTemp.getIdSolicitud1())
														);

													if(ls_temp != null)
													{
														Turno lt_temporal;
														lt_temporal = ltd_DAO.findByIdSolicitudProceso(
															    new Turno(
															        ls_temp.getIdSolicitud(), ls_temp.getIdProceso()
															    )
															);

														if(lt_temporal != null)
															lcnv_nirsAsociados.add(
															    findDataNirs(ls_temp, lt_temporal, adm_manager)
															);
														else
														{
															TurnoHistoria lth_turnoHistoria;

															lth_turnoHistoria = lthd_DAO.findBySolicitudEtapa(
																    new TurnoHistoria(
																        ls_temp.getIdSolicitud(),
																        NumericUtils.getBigDecimal(
																            EtapaCommon.ID_ETAPA_INICIAL
																        )
																    )
																);

															if(lth_turnoHistoria != null)
															{
																lt_temporal = new Turno(lth_turnoHistoria.getIdTurno());

																lcnv_nirsAsociados.add(
																    findDataNirs(ls_temp, lt_temporal, adm_manager)
																);
															}
														}
													}
												}
											}

											lct_return.setNirVinculados(lcnv_nirsAsociados);
											lct_return.setNirPrincipal(
											    findDataNirs(ls_solicitudTemp, lt_temp, adm_manager)
											);
										}
									}
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				adm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarInfoBandejaConsultaTrazabilidad", lb2be_e);

				throw lb2be_e;
			}
		}

		return lct_return;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id_turno específico
	 *
	 * @param at_turno Objeto de tipo Turno que contiene parametros de busqueda
	 * @param ldm_manager Objeto de tipo DAOManager que contiene transaccion de bases de datos activa
	 * @return Objeto de tipo Trazabilidad que contiene la informacion relacionada con el detalle de la trazabilidad para un turno
	 * @throws B2BException
	 */
	protected synchronized Trazabilidad detalleTurno(Turno at_turno, DAOManager ldm_manager)
	    throws B2BException
	{
		Trazabilidad lt_trazabilidad;

		lt_trazabilidad = null;

		try
		{
			Turno lt_turno;

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(at_turno);

			if(lt_turno != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                          .findByIdTurno(new TurnoHistoria(lt_turno.getIdTurno()), true);

				if(lth_turnoHistoria != null)
				{
					lt_trazabilidad = new Trazabilidad();
					lt_trazabilidad.setTrazabilidad(findConsultaTraza(lt_turno, ldm_manager));
					lth_turnoHistoria.setMotivoNoTramite(lth_turnoHistoria.getMotivoNoTramite().replace("_", " "));
					lt_trazabilidad.setTurnoHistoria(lth_turnoHistoria);
				}
			}
			else
				throw new B2BException(ErrorKeys.TURNO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("detalleTurno", lb2be_e);

			throw lb2be_e;
		}

		return lt_trazabilidad;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id turno determinado.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Turno que contiene el id turno requerido para
	 *            ejecutar sentencia SQL.
	 * @return Retorna una colección de datos de tipo ConsultaTrazabilidad que
	 *         coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	protected synchronized Collection<ConsultaTrazabilidad> findConsultaTraza(
	    Turno at_parametros, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<ConsultaTrazabilidad> lcct_consultaTraza;

		lcct_consultaTraza = null;

		if(at_parametros != null)
		{
			if(StringUtils.isValidString(at_parametros.getIdTurno()))
				lcct_consultaTraza = DaoCreator.getConsultaTrazabilidadDAO(adm_manager).findConsultaTraza(
					    at_parametros
					);
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}

		return lcct_consultaTraza;
	}

	/**
	 * Método de sobrecarga del método findDataNirs
	 * @param as_solicitud con la solicitud del trámite
	 * @param at_turno con el turno del trámite
	 * @param adm_manager con el manager de la transacción
	 * @return de tipo Nir Vinculado con el resultado de la consulta
	 * @throws B2BException
	 */
	protected synchronized NirVinculado findDataNirs(Solicitud as_solicitud, Turno at_turno, DAOManager adm_manager)
	    throws B2BException
	{
		return findDataNirs(as_solicitud, at_turno, false, adm_manager);
	}

	/**
	 * Método de consulta con la base de datos para encontrar la información de nirs vinculados
	 * @param as_solicitud con la solicitud del trámite
	 * @param at_turno  con el turno del trámite
	 * @param ab_vinculado con el valor boolean del vinculado
	 * @param adm_manager con el manager de la transacción
	 * @return de tipo NirVinculado con el valor de la consulta
	 * @throws B2BException
	 */
	private synchronized NirVinculado findDataNirs(
	    Solicitud as_solicitud, Turno at_turno, boolean ab_vinculado, DAOManager adm_manager
	)
	    throws B2BException
	{
		NirVinculado lct_return;

		lct_return = null;

		if(as_solicitud != null)
		{
			lct_return = new NirVinculado(as_solicitud, at_turno, null);

			if(at_turno != null)
			{
				Etapa le_etapa;

				le_etapa = DaoCreator.getEtapaDAO(adm_manager)
						                 .findById(new Etapa(NumericUtils.getLong(at_turno.getIdEtapaActual())));

				if(le_etapa != null)
				{
					Fases lf_fase;

					lf_fase = DaoCreator.getFasesDAO(adm_manager).findById(new Fases(le_etapa.getIdFase()));

					if(lf_fase != null)
						lct_return.setFases(lf_fase);

					lct_return.setEtapa(le_etapa);
				}
			}

			{
				Acto la_acto;

				la_acto = DaoCreator.getActoDAO(adm_manager)
						                .findByIdSolicitudActoPrincipalIsNull(new Acto(as_solicitud.getIdSolicitud()));

				if(la_acto != null)
				{
					TipoActo ltp_tipoActo;

					ltp_tipoActo = DaoCreator.getTipoActoDAO(adm_manager).findById(
						    new TipoActo(la_acto.getIdTipoActo())
						);

					if(ltp_tipoActo != null)
					{
						la_acto.setTipoActo(ltp_tipoActo);

						if(ab_vinculado)
							lct_return.setTramiteVinculado(la_acto);
						else
							lct_return.setActo(la_acto);
					}
				}
				else if(!ab_vinculado)
				{
					Proceso lp_proceso;

					lp_proceso = DaoCreator.getProcesoDAO(adm_manager).findById(
						    new Proceso(as_solicitud.getIdProceso())
						);

					if(lp_proceso != null)
					{
						Subproceso lsp_subproceso;

						lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
								                       .findById(
								    new Subproceso(lp_proceso.getIdProceso(), as_solicitud.getIdSubproceso())
								);

						if(lsp_subproceso != null)
						{
							StringBuilder lsb_builder;

							lsb_builder = new StringBuilder(lp_proceso.getNombre());
							lsb_builder.append(IdentificadoresCommon.SIMBOLO_GUION);
							lsb_builder.append(lsp_subproceso.getNombre());

							{
								TipoActo lta_tipoActo;

								lta_tipoActo     = new TipoActo();
								la_acto          = new Acto();

								lta_tipoActo.setNombre(lsb_builder.toString());
								la_acto.setTipoActo(lta_tipoActo);
								lct_return.setTramiteVinculado(la_acto);
							}
						}
					}
				}
			}
		}

		return lct_return;
	}

	/**
	 * Método encargado de consultar la trazabilidad para un id turno ,nir y/o id
	 * solicitud determinado.
	 *
	 * @param at_parametros
	 *            Objeto de tipo Trazabilidad que contiene el id turno , nir y id
	 *            solicitud determinados.
	 * @return Retorna una colección de datos de tipo Trazabilidad que coincide con
	 *         los parametros de entrada.
	 * @throws B2BException
	 */
	private synchronized Collection<Trazabilidad> findTrazabilidad(
	    Trazabilidad at_parametros, boolean ab_onlyTurno, Solicitud as_solicitud, Turno at_turno, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<Trazabilidad> lct_datos;

		lct_datos = null;

		if((at_parametros != null) && (as_solicitud != null) && (at_turno != null))
		{
			String              ls_sn;
			String              ls_it;
			SolicitudDAO        lsd_DAO;
			TurnoDAO            ltd_DAO;
			CirculoRegistralDao lcrd_DAO;

			ls_it        = at_turno.getIdTurno();
			ls_sn        = as_solicitud.getNir();
			lsd_DAO      = DaoCreator.getSolicitudDAO(adm_manager);
			ltd_DAO      = DaoCreator.getTurnoDAO(adm_manager);
			lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(adm_manager);

			if(!ab_onlyTurno)
			{
				if(!StringUtils.isValidString(ls_sn) && !StringUtils.isValidString(ls_it))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
			}
			else if(!StringUtils.isValidString(ls_it))
				throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);

			if(StringUtils.isValidString(ls_sn))
				at_parametros.setSolicitud(DaoCreator.getConsultaTrazabilidadDAO(adm_manager).findByNIR(as_solicitud));

			if(at_parametros.getSolicitud() == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);

			lct_datos = DaoCreator.getConsultaTrazabilidadDAO(adm_manager).findAll(at_parametros);

			if(!CollectionUtils.isValidCollection(lct_datos))
			{
				SolicitudAsociada lsa_solicitudAsociada;
				Solicitud         ls_solicitud;

				lsa_solicitudAsociada     = new SolicitudAsociada();
				ls_solicitud              = lsd_DAO.findById(at_parametros.getSolicitud());

				if(ls_solicitud != null)
				{
					lsa_solicitudAsociada.setIdSolicitud1(ls_solicitud.getIdSolicitud());
					lsa_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
							                              .findByIdSol1(lsa_solicitudAsociada);

					if(lsa_solicitudAsociada != null)
					{
						Trazabilidad        lt_trazabilidad;
						TipoEstadoSolicitud ltes_estadoSolicitud;

						lt_trazabilidad          = new Trazabilidad();
						ltes_estadoSolicitud     = new TipoEstadoSolicitud();
						lct_datos                = new ArrayList<Trazabilidad>();

						ltes_estadoSolicitud.setIdTipoEstadoSolicitud(
						    StringUtils.getString(ls_solicitud.getEstadoSolicitud())
						);

						ltes_estadoSolicitud = DaoCreator.getTipoEstadoSolicitudDAO(adm_manager)
								                             .findById(ltes_estadoSolicitud);

						if(ltes_estadoSolicitud != null)
						{
							TurnoHistoria    lth_turnoHistoria;
							TurnoHistoriaDAO lthd_DAO;

							lth_turnoHistoria     = new TurnoHistoria();
							lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(adm_manager);

							lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());

							lth_turnoHistoria = lthd_DAO.findBySolicitud(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								BigDecimal lbd_etapa;

								lbd_etapa = lth_turnoHistoria.getIdEtapa();

								if(NumericUtils.isValidBigDecimal(lbd_etapa))
								{
									Turno  lt_turno;
									String ls_turno;

									lt_turno     = new Turno();
									ls_turno     = lth_turnoHistoria.getIdTurno();

									if(StringUtils.isValidString(ls_turno))
									{
										lt_turno.setIdTurno(ls_turno);
										lt_turno = ltd_DAO.findById(lt_turno);

										if(lt_turno != null)
										{
											{
												Long ll_etapaTurno;

												ll_etapaTurno = lt_turno.getIdEtapaActual();

												if(NumericUtils.isValidLong(ll_etapaTurno))
												{
													Etapa le_etapa;

													le_etapa = new Etapa();

													le_etapa.setIdEtapa(NumericUtils.getLong(ll_etapaTurno));
													le_etapa = DaoCreator.getEtapaDAO(adm_manager).findById(le_etapa);

													if(le_etapa != null)
													{
														Fases lf_fase;

														lf_fase = new Fases();

														lf_fase.setIdFase(le_etapa.getIdFase());
														lf_fase = DaoCreator.getFasesDAO(adm_manager).findById(lf_fase);

														if(lf_fase != null)
															lt_trazabilidad.setFases(lf_fase);
													}
												}
											}

											{
												String ls_procesoTurno;

												ls_procesoTurno = lt_turno.getIdProceso();

												if(StringUtils.isValidString(ls_procesoTurno))
												{
													Proceso lp_proceso;

													lp_proceso = new Proceso();

													lp_proceso.setIdProceso(ls_procesoTurno);

													lp_proceso = DaoCreator.getProcesoDAO(adm_manager)
															                   .findById(lp_proceso);

													if(lp_proceso != null)
														lt_trazabilidad.setProceso(lp_proceso);
												}
											}

											{
												String ls_migrado;

												ls_migrado = lt_turno.getMigrado();

												if(
												    StringUtils.isValidString(ls_migrado)
													    && ls_migrado.equalsIgnoreCase(EstadoCommon.S)
												)
												{
													String ls_idCirculo;

													ls_idCirculo = lt_turno.getIdCirculo();

													if(StringUtils.isValidString(ls_idCirculo))
													{
														CirculoRegistral lcr_circuloRegistral;

														lcr_circuloRegistral = lcrd_DAO.findById(ls_idCirculo);

														if(lcr_circuloRegistral != null)
														{
															String ls_sistemaOrigen;

															ls_sistemaOrigen = lcr_circuloRegistral.getSistemaOrigen();

															if(StringUtils.isValidString(ls_sistemaOrigen))
															{
																lt_turno.setFolio(
																    ls_sistemaOrigen.equalsIgnoreCase(
																        SistemaOrigenCommon.FOLIO
																    )
																);
																lt_turno.setSir(
																    ls_sistemaOrigen.equalsIgnoreCase(
																        SistemaOrigenCommon.SIR
																    )
																);
															}
														}
													}
												}
											}
										}

										lt_trazabilidad.setTurno(lt_turno);
									}
								}
							}

							ls_solicitud.setNombreEstadoSolicitud(ltes_estadoSolicitud.getNombre());

							lt_trazabilidad.setSolicitud(ls_solicitud);

							lt_trazabilidad.setNirEtapa10(true);

							lct_datos.add(lt_trazabilidad);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
			}
			else
			{
				for(Trazabilidad lt_temp : lct_datos)
				{
					if(lt_temp != null)
					{
						Turno lt_turno;

						lt_turno = lt_temp.getTurno();

						if(lt_turno != null)
						{
							String ls_migrado;

							ls_migrado = lt_turno.getMigrado();

							if(StringUtils.isValidString(ls_migrado) && ls_migrado.equalsIgnoreCase(EstadoCommon.S))
							{
								String ls_idCirculo;

								ls_idCirculo = lt_turno.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo))
								{
									CirculoRegistral lcr_circuloRegistral;

									lcr_circuloRegistral = lcrd_DAO.findById(ls_idCirculo);

									if(lcr_circuloRegistral != null)
									{
										String ls_sistemaOrigen;

										ls_sistemaOrigen = lcr_circuloRegistral.getSistemaOrigen();

										if(StringUtils.isValidString(ls_sistemaOrigen))
										{
											lt_turno.setFolio(
											    ls_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.FOLIO)
											);
											lt_turno.setSir(ls_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.SIR));
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return lct_datos;
	}
}
