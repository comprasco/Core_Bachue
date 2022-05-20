package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacionCancelada;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionClaseAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoInterviniente;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades AnotacionesBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class AnotacionesBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AnotacionesBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar anotaciones.
	 *
	 * @param ateda_entrada de ateda entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo anotacion[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoAnotacion[] consultarAnotaciones(
	    TipoEntradaDatosAnotacion ateda_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoAnotacion[] lta_salida;
		DAOManager      ldm_manager;
		BigInteger      lbi_codigoMensaje;
		String          ls_descripcionMensaje;

		lta_salida                = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(ateda_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;
			String ls_claseAnotacion;

			lp_predio             = new Predio(ateda_entrada);
			ls_claseAnotacion     = lp_predio.getClaseAnotacion();

			if(!StringUtils.isValidString(ls_claseAnotacion))
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_CLASE_ANOTACION));

			if(
			    validarTipoIdentificacionPredio(lp_predio, ProyectosCommon.COEXISTENCIA_14)
				    && validarNumeroIdentificacionPredio(lp_predio, ProyectosCommon.COEXISTENCIA_14)
			)
			{
				if(lp_predio.isFolioMatricula())
					validarMatriculaCoexistenciaNumerica(lp_predio.getNumeroIdentificacionPredio());

				CoexistenciaResponse lcr_response;

				lcr_response = new CoexistenciaResponse(lp_predio);

				filtrarConsulta(
				    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.VALIDACION_UNICA,
				    ldm_manager
				);

				{
					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = consultarInformacionMatricula(
						    lp_predio, ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

					if(lpr_predioRegistro != null)
					{
						Long ll_idMatricula;

						ll_idMatricula = lp_predio.getIdMatricula();

						if(NumericUtils.isValidLong(ll_idMatricula))
						{
							Collection<AnotacionPredio> lcap_anotacionPredio;

							if(ls_claseAnotacion.equalsIgnoreCase(TipoEntradaDatosAnotacionClaseAnotacion._normal))
								lcap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
										                             .findAnotacionesPredio(
										    lp_predio.getIdCirculo(), lp_predio.getIdMatricula()
										);
							else if(
							    ls_claseAnotacion.equalsIgnoreCase(
								        TipoEntradaDatosAnotacionClaseAnotacion._medidaCautelar
								    )
							)
								lcap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
										                             .findAllByCirculoMatriculaNaturaleza(
										    lp_predio.getIdCirculo(), NumericUtils.getLong(ll_idMatricula), "0400"
										);
							else
								lcap_anotacionPredio = null;

							if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
							{
								int                     li_count;
								AnotacionCancelacionDAO lacDAO_lac;
								OficinaOrigenDAO        looDAO_ood;

								lta_salida     = new TipoAnotacion[lcap_anotacionPredio.size()];
								lacDAO_lac     = DaoCreator.getBngAnotacionCancelacionDAO(ldm_manager);
								looDAO_ood     = DaoCreator.getOficinaOrigenDAO(ldm_manager);
								li_count       = 0;

								for(AnotacionPredio lap_tmp : lcap_anotacionPredio)
								{
									if(lap_tmp != null)
									{
										AnotacionCatastro lac_anotacion;

										lac_anotacion = obtenerDatosAnotacionIntervinientes(lap_tmp, true, ldm_manager);

										if(lac_anotacion != null)
										{
											TipoInterviniente[] li_tipoInterviniente;

											li_tipoInterviniente = null;

											{
												Collection<PropietarioCatastro> lic_intervinienteCatastro;

												lic_intervinienteCatastro = lac_anotacion.getIntervinientesCatastro();

												if(CollectionUtils.isValidCollection(lic_intervinienteCatastro))
												{
													int li_countInterviniente;

													li_tipoInterviniente      = new TipoInterviniente[lic_intervinienteCatastro
															.size()];
													li_countInterviniente     = 0;

													for(PropietarioCatastro lic_tmp : lic_intervinienteCatastro)
													{
														if(lic_tmp != null)
														{
															String ls_idTipoPersona;

															ls_idTipoPersona = lic_tmp.getTipoPersona();

															if(StringUtils.isValidString(ls_idTipoPersona))
															{
																String ls_tipoDocumentoPersona;
																String ls_numeroDocumentoPersona;
																String ls_numNIT;
																String ls_primerNombre;
																String ls_segundoNombre;
																String ls_primerApellido;
																String ls_segundoApellido;
																String ls_razonSocial;

																ls_tipoDocumentoPersona       = null;
																ls_numeroDocumentoPersona     = null;
																ls_numNIT                     = null;
																ls_primerNombre               = null;
																ls_segundoNombre              = null;
																ls_primerApellido             = null;
																ls_segundoApellido            = null;
																ls_razonSocial                = null;

																if(!ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.J))
																{
																	ls_tipoDocumentoPersona       = lic_tmp
																			.getIdDocumentoTipo();
																	ls_numeroDocumentoPersona     = lic_tmp
																			.getNumDocumentoPersona();
																	ls_primerNombre               = lic_tmp
																			.getPrimerNombre();
																	ls_segundoNombre              = lic_tmp
																			.getSegundoNombre();
																	ls_primerApellido             = lic_tmp
																			.getPrimerApellido();
																	ls_segundoApellido            = lic_tmp
																			.getSegundoApellido();
																}
																else
																{
																	ls_numNIT          = lic_tmp.getNumDocumentoPersona();
																	ls_razonSocial     = lic_tmp.getRazonSocial();
																}

																String ls_genero;

																ls_genero = null;

																{
																	String ls_codGenero;

																	ls_codGenero = lic_tmp.getCodGenero();

																	if(StringUtils.isValidString(ls_codGenero))
																	{
																		if(
																		    ls_codGenero.equalsIgnoreCase(
																			        EstadoCommon.M
																			    )
																		)
																			ls_genero = IdentificadoresCommon.NUM01;
																		else if(
																		    ls_codGenero.equalsIgnoreCase(
																			        EstadoCommon.F
																			    )
																		)
																			ls_genero = IdentificadoresCommon.NUM02;
																		else
																			ls_genero = IdentificadoresCommon.NUM03;
																	}
																}

																String ls_porcentaje;

																{
																	ls_porcentaje = lic_tmp.getPorcentajeParticipacion();

																	if(StringUtils.isValidString(ls_porcentaje))
																		ls_porcentaje = ls_porcentaje + "%";
																	else
																		ls_porcentaje = null;
																}

																li_tipoInterviniente[li_countInterviniente] = new TipoInterviniente(
																	    lic_tmp.getRolInterviniente(), ls_idTipoPersona,
																	    lic_tmp.getPropietario(),
																	    ls_tipoDocumentoPersona,
																	    ls_numeroDocumentoPersona, ls_numNIT,
																	    ls_porcentaje, ls_primerNombre, ls_segundoNombre,
																	    ls_primerApellido, ls_segundoApellido, ls_genero,
																	    ls_razonSocial
																	);
																li_countInterviniente++;
															}
														}
													}
												}
											}

											String ls_oficinaOrigen;

											ls_oficinaOrigen = null;

											{
												OficinaOrigen loo_oficinaOrigen;

												loo_oficinaOrigen = looDAO_ood.findByIdWithMaxVersion(
													    lp_predio.getIdCirculo()
													);

												if(loo_oficinaOrigen != null)
													ls_oficinaOrigen = loo_oficinaOrigen.getNombre();
											}

											TipoAnotacionCancelada[] ltac_anotacionCancelada;

											{
												Collection<AnotacionCancelacion> lcac_datos;
												Long                             ll_idAnotacion;
												Long                             ll_idMatricula2;

												ll_idAnotacion              = lap_tmp.getIdAnotacion();
												ll_idMatricula2             = lap_tmp.getIdMatricula();
												ltac_anotacionCancelada     = null;

												if((ll_idAnotacion != null) && (ll_idMatricula2 != null))
												{
													lcac_datos = lacDAO_lac.findByAnotaciones(
														    NumericUtils.getLong(ll_idAnotacion), lap_tmp.getIdCirculo(),
														    NumericUtils.getLong(ll_idMatricula2)
														);

													if(
													    (lcac_datos != null)
														    && (lcac_datos.size() > NumericUtils.DEFAULT_INT_VALUE)
													)
													{
														int li_anotacionCancelada;

														li_anotacionCancelada       = 0;
														ltac_anotacionCancelada     = new TipoAnotacionCancelada[lcac_datos
																.size()];

														for(AnotacionCancelacion lac_actual : lcac_datos)
														{
															if(lac_actual != null)
																ltac_anotacionCancelada[li_anotacionCancelada++] = new TipoAnotacionCancelada(
																	    String.valueOf(lac_actual.getIdAnotacion1())
																	);
														}
													}
												}
											}

											lta_salida[li_count] = new TipoAnotacion(
												    NumericUtils.getBigInteger(lac_anotacion.getOrden()),
												    ltac_anotacionCancelada, lac_anotacion.getComentario(),
												    lac_anotacion.getCodTipoDocumentoPublico(),
												    obtenerDateDesdeCalendar(lac_anotacion.getFechaDocumento()),
												    lac_anotacion.getNomDocumentoPublico(),
												    lac_anotacion.getCodDominioActoJuridico(),
												    lac_anotacion.getNomDominioActoJuridico(), ls_oficinaOrigen,
												    lac_anotacion.getEspecificacion(), lac_anotacion.getDominioDRR(),
												    lac_anotacion.getCodNaturalezaJuridicaFolio(),
												    obtenerDateDesdeCalendar(lac_anotacion.getFechaAnotacion()),
												    li_tipoInterviniente, lac_anotacion.getNir(),
												    lac_anotacion.getRadicacion(), lac_anotacion.getIdEstadoAnotacion(),
												    String.valueOf(lap_tmp.getValor()), lbi_codigoMensaje, "OK"
												);

											li_count++;
										}
									}
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarAnotaciones", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			lta_salida                = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarAnotaciones", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			lta_salida                = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(lta_salida == null)
		{
			lta_salida = new TipoAnotacion[1];

			{
				lta_salida[0] = new TipoAnotacion(
					    null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					    null, null, lbi_codigoMensaje, ls_descripcionMensaje
					);
			}
		}

		return lta_salida;
	}
}
