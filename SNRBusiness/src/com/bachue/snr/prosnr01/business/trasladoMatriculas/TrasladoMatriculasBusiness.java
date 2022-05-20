package com.bachue.snr.prosnr01.business.trasladoMatriculas;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.TrasladoMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio para la fase de traslado matriculas,
 * por ejemplo crear, asociar , grabar matriculas, reporte de resultados entre otros.
 *
 * @author Sebastian Sanchez.
 *
 */
public class TrasladoMatriculasBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(TrasladoMatriculasBusiness.class);

	/**
	 * Metodo encargado de terminar una etapa y crear la siguiente según el motivo enviado.
	 *
	 * @param ath_parametros Argumento de tipo TurnoHistoria que contiene los argumentos necesarios para terminar y crear etapa.
	 * @param amt_motivoTramite Argumento de tipo MotivoTramite que contiene los argumentos necesarios para consultar el motivo tramite.
	 * @param as_usuario Argumento de tipo String que contiene el usuario que realiza la actualización.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip desde donde se realiza la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		if((ath_parametros != null) && (amt_motivoTramite != null))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				terminarTurnoHistoriaYCrearEtapa(
				    ath_parametros, ldm_manager, amt_motivoTramite, as_usuario, as_remoteIp, EstadoCommon.TERMINADA
				);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("actualizarEtapaYCrearSiguiente", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de cargar la bandeja de consulta de proyecta resolucion.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param al_idEtapa Variable de tipo Long que contiene un id etapa determinado.
	 * @param as_idTurno Variable de tipo String que contiene un id turno determinado.
	 * @param as_nir Variable de tipo String que contiene un nir determinado.
	 * @return  Retorna una lista de LinkedHashMap que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see List
	 */
	public synchronized List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		List<Map<String, Object>> ll_result;
		String                    ls_idSolicitud;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		ll_result          = null;
		ls_idSolicitud     = null;

		try
		{
			Map<Integer, Object> llhm_lhmpCriterios;
			TurnoHistoriaDAO     lthd_DAO;

			lthd_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			llhm_lhmpCriterios     = new LinkedHashMap<Integer, Object>();
			llhm_lhmpCriterios.put(NumericUtils.getInteger(1), al_idEtapa);
			llhm_lhmpCriterios.put(NumericUtils.getInteger(2), as_userId);

			if(StringUtils.isValidString(as_nir))
			{
				Solicitud ls_Solicitud = new Solicitud();
				ls_Solicitud.setNir(as_nir);
				ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

				if(ls_Solicitud != null)
					ls_idSolicitud = ls_Solicitud.getIdSolicitud();
			}

			ll_result = DaoCreator.getTrasladoMatriculaDAO(ldm_manager)
					                  .getCustonQueryDetail(as_idTurno, ls_idSolicitud, al_idEtapa, llhm_lhmpCriterios);

			if(CollectionUtils.isValidCollection(ll_result))
			{
				for(Map<String, Object> lm_temp : ll_result)
				{
					if(CollectionUtils.isValidCollection(lm_temp))
					{
						//Objeto seleccionado (boolean)
						lm_temp.put(IdentificadoresCommon.SELECCIONADO, BooleanUtils.getBoolean(EstadoCommon.F));

						for(Map.Entry<String, Object> lme_temp : lm_temp.entrySet())
						{
							if((lme_temp != null))
							{
								String ls_key;

								ls_key = lme_temp.getKey();

								if(StringUtils.isValidString(ls_key))
								{
									String ls_tipificacion;

									ls_tipificacion = null;

									switch(ls_key)
									{
										case IdentificadoresCommon.ID_TURNO:

											String ls_idTurno;
											ls_idTurno = StringUtils.getString(lme_temp.getValue());

											if(StringUtils.isValidString(ls_idTurno))
											{
												ReferenceBusiness      lrb_business;
												Collection<Aprobacion> lca_turnosDerivados;

												lrb_business            = getReferenceBusiness();
												lca_turnosDerivados     = lthd_DAO.findTurnosDerivados(
													    ls_idTurno, true, true
													);
												ls_tipificacion         = lrb_business.findTipificacionTurno(
													    as_idTurno, ldm_manager
													);

												if(CollectionUtils.isValidCollection(lca_turnosDerivados))
												{
													StringBuilder lsb_tmp;
													StringBuilder lsb_tmp_relacionados;
													lsb_tmp                  = new StringBuilder();
													lsb_tmp_relacionados     = new StringBuilder();

													for(Aprobacion loa_tmp : lca_turnosDerivados)
													{
														String ls_idProcesoHijo;
														ls_idProcesoHijo = loa_tmp.getIdProceso();

														if(
														    ls_idProcesoHijo.equalsIgnoreCase(
															        ProcesoCommon.ID_SUBPROCESO_1
															    )
														)
															lsb_tmp = lsb_tmp.append(
																    loa_tmp.getTurnosAsociados() + ", "
																);
														else if(
														    ls_idProcesoHijo.equalsIgnoreCase(
															        ProcesoCommon.ID_SUBPROCESO_6
															    )
														)
															lsb_tmp_relacionados = lsb_tmp_relacionados.append(
																    loa_tmp.getTurnosAsociados() + ", "
																);
													}

													if(StringUtils.isValidString(lsb_tmp.toString()))
														lm_temp.put("TURNO_ASOCIADO", lsb_tmp.toString());

													if(StringUtils.isValidString(lsb_tmp_relacionados.toString()))
														lm_temp.put(
														    "TURNO_RELACIONADO", lsb_tmp_relacionados.toString()
														);
												}
											}

											break;

										default:
											break;
									}

									lm_temp.put(IdentificadoresCommon.ID_MOTIVO, ls_tipificacion);
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

			clh_LOGGER.error("findDetailInbox", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Método encargado de consultar la cantidad de datos tramitados para un usuario determinado.
	 *
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_idTurno Variable de tipo String que contiene un id turno determinado.
	 * @param as_nir Variable de tipo String que contiene un nir determinado.
	 * @return  Retorna  una colección de datos   de tipo  TramiteCantidad que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<TramiteCantidad> findInboxByUserId(
	    String as_userId, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		Collection<Etapa>           lc_data;
		Collection<TramiteCantidad> lc_result;
		DAOManager                  ldm_manager;
		TrasladoMatriculaDAO        ltmd_trasladoMatriculasDAO;

		lc_data                        = null;
		lc_result                      = null;
		ldm_manager                    = DaoManagerFactory.getDAOManager();
		ltmd_trasladoMatriculasDAO     = DaoCreator.getTrasladoMatriculaDAO(ldm_manager);

		try
		{
			lc_result     = new ArrayList<TramiteCantidad>();
			lc_data       = findProyectaResolucion();

			if(CollectionUtils.isValidCollection(lc_data))
			{
				for(Etapa le_etapaActual : lc_data)
				{
					if(le_etapaActual != null)
					{
						LinkedList<LinkedHashMap<String, Object>> ll_data;
						LinkedHashMap<Integer, Object>            llhmio_criterios;
						String                                    ls_idSolicitud;

						llhmio_criterios = new LinkedHashMap<Integer, Object>();

						llhmio_criterios.put(
						    NumericUtils.getInteger(1), NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa())
						);

						llhmio_criterios.put(NumericUtils.getInteger(2), as_userId);

						ls_idSolicitud = null;

						if(StringUtils.isValidString(as_nir))
						{
							Solicitud ls_Solicitud = new Solicitud();
							ls_Solicitud.setNir(as_nir);

							ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

							if(ls_Solicitud != null)
								ls_idSolicitud = ls_Solicitud.getIdSolicitud();
							else
								ls_idSolicitud = EstadoCommon.NIR_NO_VALIDO;
						}

						ll_data = ltmd_trasladoMatriculasDAO.getCustonQueryIbox(
							    as_idTurno, ls_idSolicitud, llhmio_criterios
							);

						if(CollectionUtils.isValidCollection(ll_data))
						{
							Iterator<LinkedHashMap<String, Object>> li_consulta;

							li_consulta = ll_data.iterator();

							while(li_consulta.hasNext())
							{
								LinkedHashMap<String, Object> llhm_actual;

								llhm_actual = li_consulta.next();

								if(llhm_actual != null)
								{
									String ls_cantidad;

									ls_cantidad = llhm_actual.get(IdentificadoresCommon.CANTIDAD).toString();

									lc_result.add(
									    new TramiteCantidad(
									        NumericUtils.getInteger(ls_cantidad),
									        NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa()),
									        le_etapaActual.getNombre()
									    )
									);
								}
							}
						}
					}
				}
			}

			if(!CollectionUtils.isValidCollection(lc_result))
				lc_result = null;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findInboxByUserId", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_result;
	}

	/**
	 * Método encargado de salvar los procesos seleccionados.
	 *
	 * @param alm_data de Argumento de tipo <code>List</code> que contiene la información de los procesos.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id de la etapa.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene el la ip desde donde se realiza la operación.
	 * @return el valor Objeto de tipo <code>List</code> que contiene la información actualizada de los procesos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized List<Map<String, Object>> saveDetails(
	    List<Map<String, Object>> alm_data, String as_usuario, long al_idEtapa, String as_remoteIp
	)
	    throws B2BException
	{
		List<Map<String, Object>> ls_return;
		DAOManager                ldm_manager;

		ls_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(alm_data))
			{
				TurnoHistoriaDAO lthd_DAO;

				lthd_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(Map<String, Object> lm_temp : alm_data)
				{
					if(CollectionUtils.isValidCollection(lm_temp))
					{
						boolean lb_seleccionado;
						String  ls_idTurno;

						lb_seleccionado     = false;
						ls_idTurno          = null;

						for(Map.Entry<String, Object> lme_temp : lm_temp.entrySet())
						{
							if((lme_temp != null))
							{
								String ls_key;

								ls_key = lme_temp.getKey();

								if(StringUtils.isValidString(ls_key))
								{
									switch(ls_key)
									{
										case IdentificadoresCommon.SELECCIONADO:

											Object lo_seleccionado;
											lo_seleccionado = lme_temp.getValue();

											if(lo_seleccionado != null)
											{
												String ls_valor;

												ls_valor = lo_seleccionado.toString();

												if(ls_valor.equalsIgnoreCase("true"))
													lb_seleccionado = true;
											}

											break;

										case IdentificadoresCommon.ID_TURNO:

											Object lo_turno;
											lo_turno = lme_temp.getValue();

											if(lo_turno != null)
											{
												String ls_tmp;

												ls_tmp = lo_turno.toString();

												if(StringUtils.isValidString(ls_tmp))
													ls_idTurno = ls_tmp;
											}

											break;

										default:
											break;
									}
								}

								if(lb_seleccionado)
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = lthd_DAO.findByIdTurno(ls_idTurno);

									if(lth_turnoHistoria != null)
										terminarTurnoHistoriaYCrearEtapa(
										    lth_turnoHistoria, ldm_manager,
										    new MotivoTramite(al_idEtapa, MotivoTramiteCommon.FIRMAR), as_usuario,
										    as_remoteIp, EstadoCommon.TERMINADA, true, true, true, true
										);
								}
							}
						}
					}
				}
			}

			if(CollectionUtils.isValidCollection(alm_data))
				ls_return = alm_data;
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProyectaResolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encargado de listar las etapas que se encuentran entre 100 y 104.
	 *
	 * @return  Retorna  una colección de datos   de tipo  Etapa que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	private synchronized Collection<Etapa> findProyectaResolucion()
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getEtapaDAO(ldm_manager).findProyectaResolucion();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findProyectaResolucion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}
}
