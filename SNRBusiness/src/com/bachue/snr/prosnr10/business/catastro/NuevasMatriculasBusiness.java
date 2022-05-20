package com.bachue.snr.prosnr10.business.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredio;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioFuentesAdministrativasFuenteAdministrativa;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioPropietariosPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad;
import co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidadIntervinientesInterviniente;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.col.DominioRrr;
import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;
import com.bachue.snr.prosnr10.common.constants.TipoRRRCommon;

import com.bachue.snr.prosnr10.model.catastro.AnotacionCatastro;
import com.bachue.snr.prosnr10.model.catastro.IntervinienteCatastro;
import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;


/**
 * Clase para el manejo de reglas de negocio del servicio nuevas matriculas.
 *
 * @author Carlos Calderon
 */
public class NuevasMatriculasBusiness extends CatastroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NuevasMatriculasBusiness.class, ProyectosCommon.CATASTRO_10);

	/**
	 * Permite consultar los datos registrales asociados a la creación de nuevas matrículas por
	 * círculo registral en un determinado periodo de tiempo.
	 *
	 * @param atecnm_param de atecnm param
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar nuevas matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarNuevasMatriculas consultaNuevasMatriculas(
	    TipoEntradaConsultarNuevasMatriculas atecnm_param, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                                          ldm_manager;
		BigInteger                                                          lbi_codigoMensaje;
		String                                                              ls_descripcionMensaje;
		TipoSalidaConsultarNuevasMatriculas                                 ltsrcqo_return;
		TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] ltscnma_response;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		ltsrcqo_return            = null;
		ltscnma_response          = null;

		try
		{
			if(atecnm_param != null)
			{
				Predio                                                 lp_datos;
				TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial ltecnmae_agrupacion;

				ltecnmae_agrupacion = atecnm_param.getAgrupacionEspacial();

				if(ltecnmae_agrupacion == null)
					throw new B2BException(addErrorCTO(ErrorKeys.ERROR_AGRUPACION_ESPACIAL));

				lp_datos = new Predio(ltecnmae_agrupacion);

				{
					String ls_idDepartamento;
					String ls_idmunicipio;

					ls_idDepartamento     = lp_datos.getCodDepartamento();
					ls_idmunicipio        = lp_datos.getCodMunicipio();

					validarPaisDepMun(
					    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_idDepartamento, ls_idmunicipio, true,
					    ls_idDepartamento != null, ls_idmunicipio != null, ProyectosCommon.CATASTRO_10, ldm_manager
					);
				}

				if(validarCirculoRegistral(lp_datos, ldm_manager))
				{
					Collection<PredioRegistro> lcpr_predios;
					LocalDateTime              lldt_fechaInicio;
					LocalDateTime              lldt_fechaFin;

					lldt_fechaInicio     = obtenerLocalDateTime(lp_datos.getFechaInicial());
					lldt_fechaFin        = obtenerLocalDateTime(lp_datos.getFechaFinal());

					if(lldt_fechaInicio == null)
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_INGRESAR_FECHA_INICIAL));

					if(lldt_fechaFin == null)
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_INGRESAR_FECHA_FINAL));

					if(lldt_fechaInicio.isAfter(lldt_fechaFin))
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_FECHA_INICIAL_MENOR_A_FECHA_FINAL));

					lcpr_predios = DaoCreator.getPredioRegistroDAO(ldm_manager)
							                     .findAllByCirculoRangoFechas(
							    lp_datos.getIdCirculo(), obtenerDateDesdeLocalDateTime(lldt_fechaInicio),
							    obtenerDateDesdeLocalDateTime(lldt_fechaFin)
							);

					if(CollectionUtils.isValidCollection(lcpr_predios))
					{
						int li_contador;

						ltscnma_response     = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[lcpr_predios
								.size()];
						li_contador          = 0;

						for(PredioRegistro lpr_actual : lcpr_predios)
						{
							obtenerDatosPredio(ltscnma_response, li_contador, lpr_actual, ldm_manager);
							li_contador++;
						}

						ltsrcqo_return = new TipoSalidaConsultarNuevasMatriculas(
							    lp_datos.getCodDepartamento(), lp_datos.getCodMunicipio(), lp_datos.getIdCirculo(),
							    ltscnma_response, lbi_codigoMensaje, ls_descripcionMensaje
							);
					}
					else
						throw new B2BException(addErrorCTO(ErrorKeys.ERROR_SIN_REGISTROS));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ltsrcqo_return = null;
			clh_LOGGER.error("registrarCambioQuintoOrden", lb2be_e);

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
			ltsrcqo_return = null;
			clh_LOGGER.error("registrarCambioQuintoOrden", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsrcqo_return == null)
			ltsrcqo_return = new TipoSalidaConsultarNuevasMatriculas(
				    null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltsrcqo_return;
	}

	/**
	 * Llenar nueva matricula anotacion predio.
	 *
	 * @param apr_actual de apr actual
	 * @param adm_manager de adm manager
	 * @return the tipo salida consultar nuevas matriculas nuevas matriculas nueva matricula anotacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredio llenarNuevaMatriculaAnotacionPredio(
	    PredioRegistro apr_actual, DAOManager adm_manager
	)
	    throws B2BException
	{
		TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredio ltscnmnmnmap_anotacionPredioNuevasMatriculas;

		ltscnmnmnmap_anotacionPredioNuevasMatriculas = null;

		try
		{
			if(apr_actual != null)
			{
				String ls_idCirculo;
				long   ll_idMatricula;

				ls_idCirculo       = apr_actual.getIdCirculo();
				ll_idMatricula     = apr_actual.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					PredioSegregado lps_predioSegregado;

					lps_predioSegregado = DaoCreator.getPredioSegregadoDAO(adm_manager)
							                            .findByCirculo1Matricula1(
							    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula)
							);

					if(lps_predioSegregado != null)
					{
						Long ll_idAnotacion1;

						ll_idAnotacion1 = lps_predioSegregado.getIdAnotacion1();

						if(NumericUtils.isValidLong(ll_idAnotacion1))
						{
							AnotacionPredio lap_anotacionPredio;

							lap_anotacionPredio = DaoCreator.getAnotacionPredioDAO(adm_manager)
									                            .findById(
									    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula), ll_idAnotacion1
									);

							if(lap_anotacionPredio != null)
							{
								String ls_idDocumento;

								ls_idDocumento = lap_anotacionPredio.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioFuentesAdministrativasFuenteAdministrativa[] ltsnmfa_fuentesAdministrativas;

									ltsnmfa_fuentesAdministrativas = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioFuentesAdministrativasFuenteAdministrativa[1];

									obtenerDatosDocumento(
									    ltsnmfa_fuentesAdministrativas, ls_idDocumento,
									    lap_anotacionPredio.getVersionDocumento(), adm_manager
									);

									{
										NaturalezaJuridica lnj_naturalezaJuridica;

										lnj_naturalezaJuridica = DaoCreator.getNaturalezaJuridicaDAO(adm_manager)
												                               .findById(
												    lap_anotacionPredio.getIdNaturalezaJuridica(),
												    lap_anotacionPredio.getVersion()
												);

										String ls_descripcionTipoRrr;
										String ls_descripcionDominioRrr;
										String ls_nombreNaturaleza;

										ls_descripcionTipoRrr        = null;
										ls_descripcionDominioRrr     = null;
										ls_nombreNaturaleza          = null;

										if(lnj_naturalezaJuridica != null)
										{
											String ls_idTipoRrr;

											ls_idTipoRrr            = lnj_naturalezaJuridica.getIdtipoRrr();
											ls_nombreNaturaleza     = lnj_naturalezaJuridica.getNombre();

											if(StringUtils.isValidString(ls_idTipoRrr))
											{
												TipoRrr ltr_tipoRrr;

												ltr_tipoRrr = DaoCreator.getTipoRrrDAO(adm_manager)
														                    .findById(ls_idTipoRrr);

												if(ltr_tipoRrr != null)
												{
													ls_descripcionTipoRrr = ltr_tipoRrr.getDescripcion();

													{
														String ls_idDominioRrr;

														ls_idDominioRrr = lnj_naturalezaJuridica.getIdDominioRrr();

														if(StringUtils.isValidString(ls_idDominioRrr))
														{
															DominioRrr ldrrr_dominioRrr;

															ldrrr_dominioRrr = DaoCreator.getDominioRrrDAO(adm_manager)
																	                         .findByIdTipo(
																	    ls_idDominioRrr, ls_idTipoRrr
																	);

															if(ldrrr_dominioRrr != null)
																ls_descripcionDominioRrr = ldrrr_dominioRrr
																		.getDescripcion();
														}
													}
												}
											}
										}

										AnotacionCatastro                                                                                         lac_ac;
										TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioPropietariosPropietario[] ltsnmp_propietarios;

										lac_ac                  = obtenerDatosAnotacionIntervinientes(
											    lap_anotacionPredio, false, adm_manager
											);
										ltsnmp_propietarios     = null;

										if(lac_ac != null)
										{
											Collection<PropietarioCatastro> lcpc_intervinientes;

											lcpc_intervinientes = lac_ac.getIntervinientesCatastro();

											if(
											    (lcpc_intervinientes != null)
												    && (lcpc_intervinientes.size() > NumericUtils.DEFAULT_INT_VALUE)
											)
											{
												int li_contador;

												ltsnmp_propietarios     = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioPropietariosPropietario[lcpc_intervinientes
														.size()];
												li_contador             = NumericUtils.DEFAULT_INT_VALUE;

												for(PropietarioCatastro lpc_actual : lcpc_intervinientes)
												{
													if(lpc_actual != null)
														ltsnmp_propietarios[li_contador++] = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioPropietariosPropietario(
															    lpc_actual.getTipoPersona(),
															    lpc_actual.getTipoDocumentoPersona(),
															    lpc_actual.getNumDocumentoPersona(),
															    lpc_actual.getPorcentajeParticipacion()
															);
												}
											}
										}

										ltscnmnmnmap_anotacionPredioNuevasMatriculas = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredio(
											    apr_actual.getIdCirculo() + " - " + apr_actual.getIdMatricula(),
											    lap_anotacionPredio.getIdNaturalezaJuridica(), ls_nombreNaturaleza,
											    apr_actual.getNupre(), lap_anotacionPredio.getComentario(),
											    obtenerCalendarDesdeDate(lap_anotacionPredio.getFechaCreacion()),
											    StringUtils.getStringNotNull(ls_descripcionTipoRrr),
											    StringUtils.getStringNotNull(ls_descripcionDominioRrr),
											    ltsnmp_propietarios, ltsnmfa_fuentesAdministrativas
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
			clh_LOGGER.error("llenarNuevaMatriculaAnotacionPredio", lb2be_e);
		}

		return ltscnmnmnmap_anotacionPredioNuevasMatriculas;
	}

	/**
	 * Llenar nueva matricula publicidad.
	 *
	 * @param apr_actual de apr actual
	 * @param adm_manager de adm manager
	 * @return the tipo salida consultar nuevas matriculas nuevas matriculas nueva matricula lista publicidades publicidad[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad[] llenarNuevaMatriculaPublicidad(
	    PredioRegistro apr_actual, DAOManager adm_manager
	)
	    throws B2BException
	{
		TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad[] ltscnmnmnmlpp_publicidadNuevasMatriculas;

		ltscnmnmnmlpp_publicidadNuevasMatriculas = null;

		try
		{
			if(apr_actual != null)
			{
				String ls_idCirculo;
				long   ll_idMatricula;

				ls_idCirculo       = apr_actual.getIdCirculo();
				ll_idMatricula     = apr_actual.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					Collection<AnotacionPredio> lcap_anotacionPredio;

					lcap_anotacionPredio = consultarAnotacionesCirculoMatriculaRRR(
						    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula), TipoRRRCommon.PUBLICIDAD,
						    adm_manager
						);

					if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
					{
						int li_count;

						ltscnmnmnmlpp_publicidadNuevasMatriculas     = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad[lcap_anotacionPredio
								.size()];
						li_count                                     = 0;

						for(AnotacionPredio lap_anotacionPredio : lcap_anotacionPredio)
						{
							if(lap_anotacionPredio != null)
							{
								AnotacionCatastro lac_ac;

								lac_ac = obtenerDatosAnotacionIntervinientes(lap_anotacionPredio, adm_manager);

								if(lac_ac != null)
								{
									TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad ltscnmnmnmlpp_item;

									ltscnmnmnmlpp_item = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad(
										    lac_ac.getNumAnotacion(), lac_ac.getComentario(), lac_ac.getFechaAnotacion(),
										    lac_ac.getCodNaturalezaJuridicaFolio(),
										    lac_ac.getNomNaturalezaJuridicaFolio(), lac_ac.getDominioDRR(), null
										);

									{
										Collection<PropietarioCatastro> lcic_cic;
										int                             li_contadorIntervinientes;

										lcic_cic                      = lac_ac.getIntervinientesCatastro();
										li_contadorIntervinientes     = 0;

										if(CollectionUtils.isValidCollection(lcic_cic))
										{
											TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidadIntervinientesInterviniente[] ltscnmsmsmlppii_nuevaMatriculaIntervinientes;

											ltscnmsmsmlppii_nuevaMatriculaIntervinientes = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidadIntervinientesInterviniente[lcic_cic
													.size()];

											for(IntervinienteCatastro lic_actual : lcic_cic)
											{
												if(lic_actual != null)
													ltscnmsmsmlppii_nuevaMatriculaIntervinientes[li_contadorIntervinientes++] = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidadIntervinientesInterviniente(
														    lic_actual.getTipoDocumentoPersona(),
														    lic_actual.getNumDocumentoPersona(),
														    lic_actual.getPrimerNombre(), lic_actual.getSegundoNombre(),
														    lic_actual.getPrimerApellido(),
														    lic_actual.getSegundoApellido(), lic_actual.getRazonSocial(),
														    lic_actual.getRolInterviniente(),
														    lic_actual.getTipoParteInteresada()
														);
											}

											ltscnmnmnmlpp_item.setIntervinientes(
											    ltscnmsmsmlppii_nuevaMatriculaIntervinientes
											);
										}
									}

									ltscnmnmnmlpp_publicidadNuevasMatriculas[li_count++] = ltscnmnmnmlpp_item;
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("llenarNuevaMatriculaPublicidad", lb2be_e);
		}

		return ltscnmnmnmlpp_publicidadNuevasMatriculas;
	}

	/**
	 * Obtener datos documento.
	 *
	 * @param atscnma_response de atscnma response
	 * @param as_idDocumento de as id documento
	 * @param al_versionDocumento de al version documento
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void obtenerDatosDocumento(
	    TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioFuentesAdministrativasFuenteAdministrativa[] atscnma_response,
	    String                                                                                                                       as_idDocumento,
	    long                                                                                                                         al_versionDocumento,
	    DAOManager                                                                                                                   adm_manager
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idDocumento) && (atscnma_response != null))
		{
			Documento ld_documento;

			ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
					                     .findByIdDocumentoVersionNombres(as_idDocumento, al_versionDocumento);

			if(ld_documento != null)
			{
				Calendar lc_fechaDocumento;

				lc_fechaDocumento     = obtenerCalendarDesdeDate(ld_documento.getFechaDocumento());

				atscnma_response[0] = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredioFuentesAdministrativasFuenteAdministrativa(
					    ld_documento.getNumero(), ld_documento.getNombreTipoDocumento(), lc_fechaDocumento
					);
			}
		}
	}

	/**
	 * Obtener datos predio.
	 *
	 * @param atscnma_response de atscnma response
	 * @param ai_contador de ai contador
	 * @param apr_actual de apr actual
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void obtenerDatosPredio(
	    TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] atscnma_response, int ai_contador,
	    PredioRegistro                                                      apr_actual, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if((apr_actual != null) && CollectionUtils.isValidCollection(atscnma_response))
			{
				TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaAnotacionPredio               ltscnmnmnmap_anotacionPredioNuevasMatriculas;
				TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatriculaListaPublicidadesPublicidad[] ltscnmnmnmlpp_publicidadNuevasMatriculas;

				ltscnmnmnmap_anotacionPredioNuevasMatriculas     = llenarNuevaMatriculaAnotacionPredio(
					    apr_actual, adm_manager
					);
				ltscnmnmnmlpp_publicidadNuevasMatriculas         = llenarNuevaMatriculaPublicidad(
					    apr_actual, adm_manager
					);

				atscnma_response[ai_contador] = new TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula(
					    ltscnmnmnmap_anotacionPredioNuevasMatriculas, ltscnmnmnmlpp_publicidadNuevasMatriculas
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			atscnma_response = null;
			clh_LOGGER.error("obtenerDatosPredio", lb2be_e);
		}
	}
}
