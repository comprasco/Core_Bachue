package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacion;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregada;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregadaAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaAgregadaAnotacionPredioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatriz;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatrizAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaAgregacionMatriculaMatrizAnotacionPredioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacion;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatriz;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaMatrizAnotacionPredioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregada;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregadaAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaSegregacionMatriculaSegregadaAnotacionPredioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioAgregacion;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioAgregacionMatriculaAgregada;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioAgregacionMatriculaAgregadaAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioAgregacionMatriculaMatriz;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioAgregacionMatriculaMatrizAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacion;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatriz;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaMatrizAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregada;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaSinCambioSegregacionMatriculaSegregadaAnotacionPredio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades SegregacionSinCambioPropietarioBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 15/03/2020
 */
public class CambioLinderosPredioBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CambioLinderosPredioBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Consulta segregacion con cambio propietario.
	 *
	 * @param atecsccp_entrada de atecsccp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consulta segregacion con cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    TipoEntradaConsultaSegregacionConCambioPropietario atecsccp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                        ldm_manager;
		TipoSalidaConsultaSegregacionConCambioPropietario ltscsccp_respuesta;
		BigInteger                                        lbi_codigoMensaje;
		String                                            ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltscsccp_respuesta        = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atecsccp_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			Predio lp_datos;

			lp_datos = new Predio(atecsccp_entrada);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

				if(lpr_predioRegistro != null)
				{
					AnotacionCatastro lac_ac;

					lac_ac = DaoCreator.getAnotacionPredioDAO(ldm_manager)
							               .findSegregacionAgregacionPredio(
							    lpr_predioRegistro.getIdCirculo(),
							    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula()), true
							);

					if(lac_ac != null)
					{
						String ls_tipoAnotacion;

						ls_tipoAnotacion = lac_ac.getTipoAnotacion();

						if(StringUtils.isValidString(ls_tipoAnotacion))
						{
							AnotacionCatastro               lac_entrada;
							TipoSalidaAgregacion            ltsa_agregacion;
							TipoSalidaSegregacion           ltss_segregacion;
							boolean                         lb_matriculaMatriz;
							String                          ls_drr;
							Collection<PredioSegregado>     lcps_prediosSegregadosOMatrices;
							Collection<PropietarioCatastro> lcpc_propietarios;

							ltsa_agregacion        = null;
							ltss_segregacion       = null;
							lb_matriculaMatriz     = ls_tipoAnotacion.equalsIgnoreCase(
								    IdentificadoresCommon.SEGREGACION
								);
							lac_entrada            = obtenerSegregacionAgregacionPredio(
								    lac_ac.getIdCirculo(), lac_ac.getIdMatricula(),
								    NumericUtils.getLong(lac_ac.getNumAnotacion()), lpr_predioRegistro.getIdTipoPredio(),
								    true, lb_matriculaMatriz, ldm_manager
								);
							ls_drr                 = lac_entrada.getDominioDRR();

							obtenerInfoZonaRegistralPredio(
							    lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager
							);

							lcps_prediosSegregadosOMatrices = DaoCreator.getPredioSegregadoDAO(ldm_manager)
									                                        .findByCirculoMatriculaAnotacion(
									    lac_entrada.getIdCirculo(), lac_entrada.getIdMatricula(),
									    NumericUtils.getLong(lac_entrada.getNumAnotacion()), lb_matriculaMatriz
									);

							if(lb_matriculaMatriz)
							{
								TipoSalidaSegregacionMatriculaMatrizAnotacionPredio lssmmap_anotacionPredio;
								TipoSalidaSegregacionMatriculaSegregada[]           ltssms_matriculasSegregadas;

								ltssms_matriculasSegregadas     = null;
								lssmmap_anotacionPredio         = new TipoSalidaSegregacionMatriculaMatrizAnotacionPredio(
									    lac_entrada.getComentario(), lac_entrada.getFechaAnotacion(),
									    lac_entrada.getValorActo(), null
									);
								lcpc_propietarios               = lac_entrada.getPropietariosCatastro();

								if(
								    (lcpc_propietarios != null)
									    && (lcpc_propietarios.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int                                                              li_count;
									TipoSalidaSegregacionMatriculaMatrizAnotacionPredioPropietario[] ltssms_propietarios;

									li_count                = 0;
									ltssms_propietarios     = new TipoSalidaSegregacionMatriculaMatrizAnotacionPredioPropietario[lcpc_propietarios
											.size()];

									for(PropietarioCatastro lpc_actual : lcpc_propietarios)
									{
										if(lpc_actual != null)
											ltssms_propietarios[li_count++] = new TipoSalidaSegregacionMatriculaMatrizAnotacionPredioPropietario(
												    lpc_actual.getTipoPersona(), lpc_actual.getTipoDocumentoPersona(),
												    lpc_actual.getNumDocumentoPersona(), ls_drr,
												    lpc_actual.getPorcentajeParticipacion()
												);
									}

									lssmmap_anotacionPredio.setPropietarios(ltssms_propietarios);
								}

								if(
								    (lcps_prediosSegregadosOMatrices != null)
									    && (lcps_prediosSegregadosOMatrices.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int               li_countSegregada;
									PredioRegistroDAO lprd_predioRegistroDAO;

									lprd_predioRegistroDAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

									li_countSegregada               = 0;
									ltssms_matriculasSegregadas     = new TipoSalidaSegregacionMatriculaSegregada[lcps_prediosSegregadosOMatrices
											.size()];

									for(PredioSegregado lps_actual : lcps_prediosSegregadosOMatrices)
									{
										if(lps_actual != null)
										{
											PredioRegistro    lpr_predio;
											AnotacionCatastro lac_segregadas;

											lpr_predio = lprd_predioRegistroDAO.findByFolioMatricula(
												    lps_actual.getIdCirculo1(),
												    NumericUtils.getLong(lps_actual.getIdMatricula1())
												);

											{
												Long ll_idMatricula;

												ll_idMatricula = lps_actual.getIdMatricula1();

												if(NumericUtils.isValidLong(ll_idMatricula))
												{
													lac_segregadas     = obtenerSegregacionAgregacionPredio(
														    lps_actual.getIdCirculo1(),
														    NumericUtils.getLong(ll_idMatricula),
														    NumericUtils.getLong(lps_actual.getIdAnotacion1()),
														    lpr_predio.getIdTipoPredio(), true, !lb_matriculaMatriz,
														    ldm_manager
														);

													lcpc_propietarios = lac_segregadas.getPropietariosCatastro();

													{
														TipoSalidaSegregacionMatriculaSegregadaAnotacionPredioPropietario[] ltssms_propietarios;

														ltssms_propietarios = null;

														if(
														    (lcpc_propietarios != null)
															    && (lcpc_propietarios.size() > NumericUtils.DEFAULT_INT_VALUE)
														)
														{
															int li_countPropietario;

															li_countPropietario     = 0;
															ls_drr                  = lac_segregadas.getDominioDRR();
															ltssms_propietarios     = new TipoSalidaSegregacionMatriculaSegregadaAnotacionPredioPropietario[lcpc_propietarios
																	.size()];

															for(PropietarioCatastro lpc_actual : lcpc_propietarios)
															{
																if(lpc_actual != null)
																	ltssms_propietarios[li_countPropietario++] = new TipoSalidaSegregacionMatriculaSegregadaAnotacionPredioPropietario(
																		    lpc_actual.getTipoPersona(),
																		    lpc_actual.getTipoDocumentoPersona(),
																		    lpc_actual.getNumDocumentoPersona(), ls_drr,
																		    lpc_actual.getPorcentajeParticipacion()
																		);
															}
														}

														ltssms_matriculasSegregadas[li_countSegregada++] = new TipoSalidaSegregacionMatriculaSegregada(
															    String.valueOf(lac_segregadas.getIdMatricula()),
															    lac_segregadas.getDireccion(),
															    lac_segregadas.getEstado(),
															    new TipoSalidaSegregacionMatriculaSegregadaAnotacionPredio(
															        lac_segregadas.getComentario(),
															        lac_segregadas.getFechaAnotacion(),
															        lac_segregadas.getValorActo(), ltssms_propietarios
															    )
															);
													}
												}
											}
										}
									}
								}

								ltss_segregacion = new TipoSalidaSegregacion(
									    lac_entrada.getCodigoActo(), lac_entrada.getNombreActo(),
									    new TipoSalidaSegregacionMatriculaMatriz(
									        String.valueOf(lac_entrada.getIdMatricula()), lac_entrada.getDireccion(),
									        lac_entrada.getEstado(), lssmmap_anotacionPredio
									    ), ltssms_matriculasSegregadas
									);
							}
							else
							{
								TipoSalidaAgregacionMatriculaMatriz[]                ltsams_matriculasMatrices;
								TipoSalidaAgregacionMatriculaAgregadaAnotacionPredio lsammap_anotacionPredio;

								ltsams_matriculasMatrices     = null;

								lsammap_anotacionPredio     = new TipoSalidaAgregacionMatriculaAgregadaAnotacionPredio(
									    lac_entrada.getComentario(), lac_entrada.getFechaAnotacion(),
									    lac_entrada.getValorActo(), null
									);
								lcpc_propietarios           = lac_entrada.getPropietariosCatastro();

								if(
								    (lcpc_propietarios != null)
									    && (lcpc_propietarios.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int                                                               li_count;
									TipoSalidaAgregacionMatriculaAgregadaAnotacionPredioPropietario[] ltsama_propietarios;

									li_count                = 0;
									ltsama_propietarios     = new TipoSalidaAgregacionMatriculaAgregadaAnotacionPredioPropietario[lcpc_propietarios
											.size()];

									for(PropietarioCatastro lpc_actual : lcpc_propietarios)
									{
										if(lpc_actual != null)
											ltsama_propietarios[li_count++] = new TipoSalidaAgregacionMatriculaAgregadaAnotacionPredioPropietario(
												    lpc_actual.getTipoPersona(), lpc_actual.getTipoDocumentoPersona(),
												    lpc_actual.getNumDocumentoPersona(), ls_drr,
												    lpc_actual.getPorcentajeParticipacion()
												);
									}

									lsammap_anotacionPredio.setPropietarios(ltsama_propietarios);
								}

								if(
								    (lcps_prediosSegregadosOMatrices != null)
									    && (lcps_prediosSegregadosOMatrices.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int               li_countMatriz;
									PredioRegistroDAO lprd_predioRegistroDAO;

									lprd_predioRegistroDAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

									li_countMatriz                = 0;
									ltsams_matriculasMatrices     = new TipoSalidaAgregacionMatriculaMatriz[lcps_prediosSegregadosOMatrices
											.size()];

									for(PredioSegregado lps_actual : lcps_prediosSegregadosOMatrices)
									{
										if(lps_actual != null)
										{
											PredioRegistro    lpr_return;
											AnotacionCatastro lac_matrices;

											lpr_return = lprd_predioRegistroDAO.findByFolioMatricula(
												    lps_actual.getIdCirculo(),
												    NumericUtils.getLong(lps_actual.getIdMatricula())
												);

											{
												Long ll_idMatricula;

												ll_idMatricula = lps_actual.getIdMatricula();

												if(NumericUtils.isValidLong(ll_idMatricula))
												{
													lac_matrices     = obtenerSegregacionAgregacionPredio(
														    lps_actual.getIdCirculo(),
														    NumericUtils.getLong(ll_idMatricula),
														    NumericUtils.getLong(lps_actual.getIdAnotacion()),
														    lpr_return.getIdTipoPredio(), true, !lb_matriculaMatriz,
														    ldm_manager
														);

													lcpc_propietarios = lac_matrices.getPropietariosCatastro();

													if(
													    (lcpc_propietarios != null)
														    && (lcpc_propietarios.size() > NumericUtils.DEFAULT_INT_VALUE)
													)
													{
														int                                                             li_countPropietario;
														TipoSalidaAgregacionMatriculaMatrizAnotacionPredioPropietario[] ltssms_propietarios;

														li_countPropietario     = 0;
														ls_drr                  = lac_matrices.getDominioDRR();
														ltssms_propietarios     = new TipoSalidaAgregacionMatriculaMatrizAnotacionPredioPropietario[lcpc_propietarios
																.size()];

														for(PropietarioCatastro lpc_actual : lcpc_propietarios)
														{
															if(lpc_actual != null)
																ltssms_propietarios[li_countPropietario++] = new TipoSalidaAgregacionMatriculaMatrizAnotacionPredioPropietario(
																	    lpc_actual.getTipoPersona(),
																	    lpc_actual.getTipoDocumentoPersona(),
																	    lpc_actual.getNumDocumentoPersona(), ls_drr,
																	    lpc_actual.getPorcentajeParticipacion()
																	);
														}

														ltsams_matriculasMatrices[li_countMatriz++] = new TipoSalidaAgregacionMatriculaMatriz(
															    String.valueOf(lac_matrices.getIdMatricula()),
															    lac_matrices.getDireccion(), lac_matrices.getEstado(),
															    new TipoSalidaAgregacionMatriculaMatrizAnotacionPredio(
															        lac_matrices.getComentario(),
															        lac_matrices.getFechaAnotacion(),
															        lac_matrices.getValorActo(), ltssms_propietarios
															    )
															);
													}
												}
											}
										}
									}
								}

								ltsa_agregacion = new TipoSalidaAgregacion(
									    lac_entrada.getCodigoActo(), lac_entrada.getNombreActo(),
									    ltsams_matriculasMatrices,
									    new TipoSalidaAgregacionMatriculaAgregada(
									        String.valueOf(lac_entrada.getIdMatricula()), lac_entrada.getDireccion(),
									        lac_entrada.getEstado(), lsammap_anotacionPredio
									    )
									);
							}

							ltscsccp_respuesta = new TipoSalidaConsultaSegregacionConCambioPropietario(
								    lp_datos.getCodDepartamento(), lp_datos.getCodMunicipio(),
								    lp_datos.getCodCirculoRegistral(), ltss_segregacion, ltsa_agregacion,
								    lbi_codigoMensaje, ls_descripcionMensaje
								);
						}
					}
					else
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_INFO_MUTACION));
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaSegregacionConCambioPropietario", lb2be_e);
			ltscsccp_respuesta = null;

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaSegregacionConCambioPropietario", le_e);
			ltscsccp_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscsccp_respuesta == null)
			ltscsccp_respuesta = new TipoSalidaConsultaSegregacionConCambioPropietario(
				    null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltscsccp_respuesta;
	}

	/**
	 * Permite consultar en Bachué la actualización de la delimitación de un predio
	 * por agregación o desagregación de varios predios sin cambio de propietario.
	 *
	 * @param atecsscp_entrada de atecsscp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consulta segregacion sin cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    TipoEntradaConsultaSegregacionSinCambioPropietario atecsscp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                        ldm_manager;
		TipoSalidaConsultaSegregacionSinCambioPropietario ltsccscp_respuesta;
		BigInteger                                        lbi_codigoMensaje;
		String                                            ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltsccscp_respuesta        = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;

		try
		{
			if(atecsscp_entrada == null)
				throw new B2BException(addErrorCTO(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS_CODIGO));

			Predio lp_datos;

			lp_datos = new Predio(atecsscp_entrada);

			if(validarTipoIdentificacionPredio(lp_datos) && validarNumeroIdentificacionPredio(lp_datos))
			{
				PredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = consultarInformacionMatricula(lp_datos, ldm_manager);

				if(lpr_predioRegistro != null)
				{
					AnotacionCatastro lac_ac;

					lac_ac = DaoCreator.getAnotacionPredioDAO(ldm_manager)
							               .findSegregacionAgregacionPredio(
							    lpr_predioRegistro.getIdCirculo(),
							    NumericUtils.getLongWrapper(lpr_predioRegistro.getIdMatricula()), false
							);

					if(lac_ac != null)
					{
						String ls_tipoAnotacion;

						ls_tipoAnotacion = lac_ac.getTipoAnotacion();

						if(StringUtils.isValidString(ls_tipoAnotacion))
						{
							boolean                        lb_matriculaMatriz;
							AnotacionCatastro              lac_return;
							TipoSalidaSinCambioAgregacion  ltssca_agregacion;
							TipoSalidaSinCambioSegregacion ltsscs_segregacion;
							Collection<PredioSegregado>    lcps_prediosSegregadosOMatrices;

							lb_matriculaMatriz     = ls_tipoAnotacion.equalsIgnoreCase(
								    IdentificadoresCommon.SEGREGACION
								);
							ltssca_agregacion      = null;
							ltsscs_segregacion     = null;
							lac_return             = obtenerSegregacionAgregacionPredio(
								    lac_ac.getIdCirculo(), lac_ac.getIdMatricula(),
								    NumericUtils.getLong(lac_ac.getNumAnotacion()), lpr_predioRegistro.getIdTipoPredio(),
								    false, lb_matriculaMatriz, ldm_manager
								);

							obtenerInfoZonaRegistralPredio(
							    lp_datos, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager
							);

							lcps_prediosSegregadosOMatrices = DaoCreator.getPredioSegregadoDAO(ldm_manager)
									                                        .findByCirculoMatriculaAnotacion(
									    lac_return.getIdCirculo(), lac_return.getIdMatricula(),
									    NumericUtils.getLong(lac_return.getNumAnotacion()), lb_matriculaMatriz
									);

							if(lb_matriculaMatriz)
							{
								if(
								    (lcps_prediosSegregadosOMatrices != null)
									    && (lcps_prediosSegregadosOMatrices.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int                                                li_countSegregada;
									PredioRegistroDAO                                  lprd_predioRegistroDAO;
									TipoSalidaSinCambioSegregacionMatriculaSegregada[] ltsscsma_matriculasSegregadas;

									li_countSegregada                 = 0;
									lprd_predioRegistroDAO            = DaoCreator.getPredioRegistroDAO(ldm_manager);
									ltsscsma_matriculasSegregadas     = new TipoSalidaSinCambioSegregacionMatriculaSegregada[lcps_prediosSegregadosOMatrices
											.size()];

									for(PredioSegregado lps_actual : lcps_prediosSegregadosOMatrices)
									{
										if(lps_actual != null)
										{
											PredioRegistro    lpr_predio;
											AnotacionCatastro lac_segregadas;

											lpr_predio = lprd_predioRegistroDAO.findByFolioMatricula(
												    lps_actual.getIdCirculo1(),
												    NumericUtils.getLong(lps_actual.getIdMatricula1())
												);

											{
												Long ll_idMatricula;

												ll_idMatricula = lps_actual.getIdMatricula1();

												if(NumericUtils.isValidLong(ll_idMatricula))
												{
													lac_segregadas = obtenerSegregacionAgregacionPredio(
														    lps_actual.getIdCirculo1(),
														    NumericUtils.getLong(ll_idMatricula),
														    NumericUtils.getLong(lps_actual.getIdAnotacion1()),
														    lpr_predio.getIdTipoPredio(), true, !lb_matriculaMatriz,
														    ldm_manager
														);

													if(lac_segregadas != null)
														ltsscsma_matriculasSegregadas[li_countSegregada++] = new TipoSalidaSinCambioSegregacionMatriculaSegregada(
															    new TipoSalidaSinCambioSegregacionMatriculaSegregadaAnotacionPredio(
															        lac_segregadas.getComentario()
															    ), String.valueOf(lac_segregadas.getIdMatricula()),
															    lac_segregadas.getDireccion(),
															    lac_segregadas.getEstado()
															);
												}
											}
										}
									}

									ltsscs_segregacion = new TipoSalidaSinCambioSegregacion(
										    lac_return.getCodigoActo(), lac_return.getNombreActo(),
										    new TipoSalidaSinCambioSegregacionMatriculaMatriz(
										        new TipoSalidaSinCambioSegregacionMatriculaMatrizAnotacionPredio(
										            lac_return.getComentario()
										        ), NumericUtils.getInteger(lac_return.getIdMatricula()),
										        lac_return.getDireccion(), lac_return.getEstado()
										    ), ltsscsma_matriculasSegregadas
										);
								}
							}
							else
							{
								if(
								    (lcps_prediosSegregadosOMatrices != null)
									    && (lcps_prediosSegregadosOMatrices.size() > NumericUtils.DEFAULT_INT_VALUE)
								)
								{
									int                                            li_countMatriz;
									PredioRegistroDAO                              lprd_predioRegistroDAO;
									TipoSalidaSinCambioAgregacionMatriculaMatriz[] ltsscsma_matriculasAgregadas;

									li_countMatriz                   = 0;
									lprd_predioRegistroDAO           = DaoCreator.getPredioRegistroDAO(ldm_manager);
									ltsscsma_matriculasAgregadas     = new TipoSalidaSinCambioAgregacionMatriculaMatriz[lcps_prediosSegregadosOMatrices
											.size()];

									for(PredioSegregado lps_actual : lcps_prediosSegregadosOMatrices)
									{
										if(lps_actual != null)
										{
											PredioRegistro    lpr_return;
											AnotacionCatastro lac_matrices;

											lpr_return = lprd_predioRegistroDAO.findByFolioMatricula(
												    lps_actual.getIdCirculo(),
												    NumericUtils.getLong(lps_actual.getIdMatricula())
												);

											{
												Long ll_idMatricula;

												ll_idMatricula = lps_actual.getIdMatricula();

												if(NumericUtils.isValidLong(ll_idMatricula))
												{
													lac_matrices = obtenerSegregacionAgregacionPredio(
														    lps_actual.getIdCirculo(),
														    NumericUtils.getLong(ll_idMatricula),
														    NumericUtils.getLong(lps_actual.getIdAnotacion()),
														    lpr_return.getIdTipoPredio(), true, !lb_matriculaMatriz,
														    ldm_manager
														);

													if(lac_matrices != null)
														ltsscsma_matriculasAgregadas[li_countMatriz++] = new TipoSalidaSinCambioAgregacionMatriculaMatriz(
															    new TipoSalidaSinCambioAgregacionMatriculaMatrizAnotacionPredio(
															        lac_matrices.getComentario()
															    ), String.valueOf(lac_matrices.getIdMatricula()),
															    lac_matrices.getDireccion(), lac_matrices.getEstado()
															);
												}
											}
										}
									}

									ltssca_agregacion = new TipoSalidaSinCambioAgregacion(
										    lac_return.getCodigoActo(), lac_return.getNombreActo(),
										    ltsscsma_matriculasAgregadas,
										    new TipoSalidaSinCambioAgregacionMatriculaAgregada(
										        new TipoSalidaSinCambioAgregacionMatriculaAgregadaAnotacionPredio(
										            lac_return.getComentario()
										        ), String.valueOf(lac_return.getIdMatricula()),
										        lac_return.getDireccion(), lac_return.getEstado()
										    )
										);
								}
							}

							ltsccscp_respuesta = new TipoSalidaConsultaSegregacionSinCambioPropietario(
								    lp_datos.getCodDepartamento(), lp_datos.getCodMunicipio(),
								    lp_datos.getCodCirculoRegistral(), ltsscs_segregacion, ltssca_agregacion,
								    lbi_codigoMensaje, ls_descripcionMensaje
								);
						}
					}
					else
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_INFO_MUTACION));
				}
				else
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_NUMERO_ID_CATASTRAL_NO_EXISTENTE_CODIGO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaSegregacionSinCambioPropietario", lb2be_e);
			ltsccscp_respuesta = null;

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultaSegregacionSinCambioPropietario", le_e);
			ltsccscp_respuesta     = null;

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsccscp_respuesta == null)
			ltsccscp_respuesta = new TipoSalidaConsultaSegregacionSinCambioPropietario(
				    null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltsccscp_respuesta;
	}

	/**
	 * Obtener segregacion agregacion predio.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param al_idAnotacion de al id anotacion
	 * @param as_idTipoPredio de as id tipo predio
	 * @param ab_busquedaPropietarios de ab busqueda propietarios
	 * @param adm_manager de adm manager
	 * @return el valor de anotacion catastro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized AnotacionCatastro obtenerSegregacionAgregacionPredio(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion, String as_idTipoPredio,
	    boolean ab_busquedaPropietarios, boolean ab_matriculaMatriz, DAOManager adm_manager
	)
	    throws B2BException
	{
		AnotacionCatastro lac_retorno;

		lac_retorno = new AnotacionCatastro();

		try
		{
			AnotacionPredio lap_anotacionPredio;

			lap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(adm_manager)
					                            .findById(
					    as_idCirculo, Long.valueOf(al_idMatricula), Long.valueOf(al_idAnotacion)
					);

			if(lap_anotacionPredio != null)
			{
				lac_retorno.setIdMatricula(NumericUtils.getLong(lap_anotacionPredio.getIdMatricula()));
				lac_retorno.setIdCirculo(lap_anotacionPredio.getIdCirculo());
				lac_retorno.setNumAnotacion(StringUtils.getString(lap_anotacionPredio.getIdAnotacion()));
				lac_retorno.setCodigoActo(lap_anotacionPredio.getIdNaturalezaJuridica());
				lac_retorno.setNombreActo(lap_anotacionPredio.getEspecificacion());
				lac_retorno.setComentario(lap_anotacionPredio.getComentario());
				lac_retorno.setFechaAnotacion(obtenerCalendarDesdeDate(lap_anotacionPredio.getFechaRegistro()));

				{
					Double ld_valorActo;

					ld_valorActo = NumericUtils.getDoubleWrapper(lap_anotacionPredio.getValor());

					if(NumericUtils.isValidDouble(ld_valorActo, NumericUtils.DEFAULT_DOUBLE_VALUE, false))
						lac_retorno.setValorActo(ld_valorActo);
				}

				{
					NaturalezaJuridica lnj_nj;

					lnj_nj = DaoCreator.getNaturalezaJuridicaDAO(adm_manager)
							               .findById(
							    lap_anotacionPredio.getIdNaturalezaJuridica(), lap_anotacionPredio.getVersion()
							);

					if(lnj_nj != null)
					{
						TipoRrr ltrrr_trrr;

						ltrrr_trrr = DaoCreator.getTipoRrrDAO(adm_manager).findById(lnj_nj.getIdtipoRrr());

						if(ltrrr_trrr != null)
							lac_retorno.setDominioDRR(ltrrr_trrr.getDescripcion());
					}
				}

				if(ab_busquedaPropietarios)
				{
					Collection<PropietarioCatastro> lcpc_propietarios;

					lcpc_propietarios = DaoCreator.getPropietarioDAO(adm_manager)
							                          .findPropietariosAnotacionPredio(
							    as_idCirculo, al_idMatricula, al_idAnotacion, ab_matriculaMatriz
							);

					lac_retorno.setPropietariosCatastro(lcpc_propietarios);
				}
			}

			lac_retorno.setDireccion(
			    consultarDireccionCirculoMatricula(as_idCirculo, String.valueOf(al_idMatricula), adm_manager)
			);
			lac_retorno.setEstado(consultarEstadoPredio(as_idTipoPredio, adm_manager));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerSegregacionAgregacionPredio", lb2be_e);
			throw lb2be_e;
		}

		return lac_retorno;
	}
}
