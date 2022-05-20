package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades TramitesEnCursoBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
public class TramitesEnCursoBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(TramitesEnCursoBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar tramites.
	 *
	 * @param atedt_entrada de atedt entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos tramites
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaDatosTramites consultarTramites(
	    TipoEntradaDatosTramites atedt_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosTramites ltsdt_return;
		DAOManager              ldm_manager;
		BigInteger              lbi_codigoMensaje;
		String                  ls_descripcionMensaje;

		ltsdt_return              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atedt_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_datos;

			lp_datos = new Predio(atedt_entrada);

			if(
			    validarTipoIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_datos, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_datos.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_datos.getNumeroIdentificacionPredio());

				CoexistenciaResponse lcr_response;

				lcr_response = new CoexistenciaResponse(lp_datos);

				filtrarConsulta(
				    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.VALIDACION_UNICA,
				    ldm_manager
				);

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_datos, ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

					if(lpr_predioRegistro != null)
					{
						obtenerInfoZonaRegistralPredio(lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager);

						String ls_idTurno;

						ls_idTurno = lpr_predioRegistro.getTurnoBloqueo();

						if(StringUtils.isValidString(ls_idTurno))
						{
							Turno lt_turno;

							lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

							if(lt_turno != null)
							{
								lp_datos.setIdTurno(lt_turno.getIdTurno());
								lp_datos.setFechaCreacionTurno(obtenerCalendarDesdeDate(lt_turno.getFechaCreacion()));

								{
									String ls_idSolicitud;

									ls_idSolicitud = lt_turno.getIdSolicitud();

									if(StringUtils.isValidString(ls_idSolicitud))
									{
										Solicitud ls_solicitud;

										ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

										if(ls_solicitud != null)
										{
											lp_datos.setNir(ls_solicitud.getNir());
											lp_datos.setFechaCreacionNir(
											    obtenerCalendarDesdeDate(ls_solicitud.getFechaCreacion())
											);

											{
												String ls_idCirculo;

												ls_idCirculo = lt_turno.getIdCirculo();

												if(StringUtils.isValidString(ls_idCirculo))
												{
													Collection<Acto> lca_cllActo;

													lca_cllActo = DaoCreator.getActoDAO(ldm_manager)
															                    .findByIdSolicitudCirculo(
															    ls_idSolicitud, ls_idCirculo
															);

													if(CollectionUtils.isValidCollection(lca_cllActo))
													{
														TipoTiposActo[] ltta_tipoActo;
														int             li_count;

														ltta_tipoActo     = new TipoTiposActo[lca_cllActo.size()];
														li_count          = 0;

														for(Acto la_tmp : lca_cllActo)
														{
															if(la_tmp != null)
															{
																String     ls_descripcionActo;
																BigDecimal ls_valorAvaluo;

																ls_descripcionActo     = "";
																ls_valorAvaluo         = la_tmp.getValorAvaluo();

																{
																	TipoActo lta_tipoActo;

																	lta_tipoActo = DaoCreator.getTipoActoDAO(
																		    ldm_manager
																		).findTipoActoById(la_tmp.getIdTipoActo());

																	if(lta_tipoActo != null)
																		ls_descripcionActo = lta_tipoActo.getNombre();
																}

																ltta_tipoActo[li_count] = new TipoTiposActo(
																	    ls_descripcionActo, ls_valorAvaluo
																	);

																li_count++;
															}
														}

														String ls_nombreMunicipio;

														ls_nombreMunicipio = null;

														{
															Municipio lm_municipio;

															lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
																	                     .findById(
																	    lp_datos.getIdPais(),
																	    lp_datos.getCodDepartamento(),
																	    lp_datos.getCodMunicipio()
																	);

															if(lm_municipio != null)
																ls_nombreMunicipio = lm_municipio.getNombre();
														}

														String ls_oficinaOrigen;

														ls_oficinaOrigen = null;

														{
															CirculoRegistral lcr_circuloRegistral;

															lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(
																    ldm_manager
																).findById(ls_idCirculo);

															if(lcr_circuloRegistral != null)
															{
																OficinaOrigen loo_oficinaOrigen;

																loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(
																	    ldm_manager
																	)
																		                          .findByIdWithMaxVersion(
																		    lcr_circuloRegistral.getIdOficinaOrigen()
																		);

																if(loo_oficinaOrigen != null)
																	ls_oficinaOrigen = loo_oficinaOrigen.getNombre();
															}
														}

														ltsdt_return = new TipoSalidaDatosTramites(
															    lp_datos.getNir(), lp_datos.getFechaCreacionNir(),
															    lp_datos.getIdTurno(), lp_datos.getFechaCreacionTurno(),
															    ltta_tipoActo, ls_oficinaOrigen, ls_nombreMunicipio,
															    lbi_codigoMensaje, "OK"
															);
													}
													else
														throw new B2BException(
														    addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS)
														);
												}
											}
										}
										else
											throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
									}
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarTramites", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsdt_return              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarTramites", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsdt_return              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdt_return == null)
			ltsdt_return = new TipoSalidaDatosTramites(
				    null, null, null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltsdt_return;
	}
}
