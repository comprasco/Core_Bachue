package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculasCriterioBusqueda;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmuebleTipoIdentificacionPredio;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietarioTipoIdentificacionPredio;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario;
import co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoNupre;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;

import com.bachue.snr.prosnr10.common.constants.TipoIdentificacionPredioCommon;

import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import com.google.gson.Gson;

import com.google.gson.internal.LinkedTreeMap;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades InmueblesBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class InmueblesBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(InmueblesBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar datos inmueble.
	 *
	 * @param atedi_entrada de atedi entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos inmueble
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosInmueble consultarDatosInmueble(
	    TipoEntradaDatosInmueble atedi_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosInmueble ltsdi_salida;
		DAOManager              ldm_manager;
		BigInteger              lbi_codigoMensaje;
		String                  ls_descripcionMensaje;

		ltsdi_salida              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atedi_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(atedi_entrada);

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
						obtenerInfoZonaRegistralPredio(lp_predio, lpr_predioRegistro.getIdZonaRegistral(), ldm_manager);

						CirculoRegistral lcr_circuloRegistral;

						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(ldm_manager)
								                             .findById(lpr_predioRegistro.getIdCirculo());

						if(lcr_circuloRegistral != null)
						{
							String ls_estadoNupre;

							ls_estadoNupre = null;

							{
								EstadoNupre len_estadoNupre;

								len_estadoNupre = DaoCreator.getEstadoNupreDAO(ldm_manager)
										                        .findById(lpr_predioRegistro.getIdEstadoNupre());

								if(len_estadoNupre != null)
									ls_estadoNupre = len_estadoNupre.getNombre();
							}

							String ls_direccionPredio;

							ls_direccionPredio = null;

							{
								ls_direccionPredio = consultarDireccionCirculoMatricula(
									    lp_predio.getIdCirculo(), String.valueOf(lp_predio.getIdMatricula()),
									    ldm_manager
									);
							}

							String ls_nombreDepartamento;

							ls_nombreDepartamento = null;

							{
								Departamento ld_departamento;

								ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
										                        .findById(
										    lp_predio.getIdPais(), lp_predio.getCodDepartamento()
										);

								if(ld_departamento != null)
									ls_nombreDepartamento = ld_departamento.getNombre();
							}

							String ls_nombreMunicipio;

							ls_nombreMunicipio = null;

							{
								Municipio lm_municipio;

								lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager)
										                     .findById(
										    lp_predio.getIdPais(), lp_predio.getCodDepartamento(),
										    lp_predio.getCodMunicipio()
										);

								if(lm_municipio != null)
									ls_nombreMunicipio = lm_municipio.getNombre();
							}

							String ls_nombreVereda;

							ls_nombreVereda = null;

							{
								Vereda lv_vereda;

								lv_vereda = DaoCreator.getVeredaDAO(ldm_manager)
										                  .findById(
										    lp_predio.getIdPais(), lp_predio.getCodDepartamento(),
										    lp_predio.getCodMunicipio(), lp_predio.getIdVereda()
										);

								if(lv_vereda != null)
									ls_nombreVereda = lv_vereda.getNombre();
							}

							String ls_nir;

							ls_nir = null;

							{
								ls_nir = DaoCreator.getSolicitudDAO(ldm_manager)
										               .findNirByIdTurno(lpr_predioRegistro.getRadicacion());
							}

							String ls_tipopredio;

							ls_tipopredio = null;

							{
								PredioTipo lpt_predioTipo;

								lpt_predioTipo = DaoCreator.getPredioTipoDao(ldm_manager)
										                       .findById(lpr_predioRegistro.getIdTipoPredio());

								if(lpt_predioTipo != null)
									ls_tipopredio = lpt_predioTipo.getDescripcion();
							}

							String ls_linderos;

							ls_linderos = null;

							{
								LinderoPredio llp_linderoPredio;

								llp_linderoPredio = DaoCreator.getLinderoPredioDAO(ldm_manager)
										                          .findById(
										    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula()
										);

								if(llp_linderoPredio != null)
									ls_linderos = llp_linderoPredio.getLindero();
							}

							String ls_complementaciones;

							ls_complementaciones = null;

							{
								ComplementacionPredio lcp_complementacionPredio;

								lcp_complementacionPredio = DaoCreator.getComplementacionPredioDAO(ldm_manager)
										                                  .findById(
										    String.valueOf(lpr_predioRegistro.getIdComplementacion())
										);

								if(lcp_complementacionPredio != null)
									ls_complementaciones = lcp_complementacionPredio.getComplementacion();
							}

							Date   ld_fechaInstrumento;
							String ls_nomTipoDocumentoPublico;

							ld_fechaInstrumento            = null;
							ls_nomTipoDocumentoPublico     = null;

							{
								Documento ldoc_documento;

								ldoc_documento = DaoCreator.getDocumentoDAO(ldm_manager)
										                       .findByIdDocumentoVersion(
										    lpr_predioRegistro.getIdDocumento(),
										    NumericUtils.getLongWrapper(lpr_predioRegistro.getVersionDocumento())
										);

								if(ldoc_documento != null)
								{
									ld_fechaInstrumento = ldoc_documento.getFechaDocumento();

									{
										TipoDocumentoPublico ltdp_tipoDocumentoPublico;

										ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
												                                  .findById(
												    ldoc_documento.getIdTipoDocumento()
												);

										if(ltdp_tipoDocumentoPublico != null)
											ls_nomTipoDocumentoPublico = ltdp_tipoDocumentoPublico.getNombre();
									}
								}
							}

							Long ll_areaInmueble;
							Long ll_areaConstruida;
							Long ll_areaPrivada;

							{
								Double ld_areaInmueble;
								Double ld_areaConstruida;
								Double ld_areaPrivada;

								ld_areaInmueble       = null;
								ld_areaConstruida     = null;
								ld_areaPrivada        = null;

								{
									String ls_idCirculo;
									long   ll_idMatricula;

									ls_idCirculo       = lpr_predioRegistro.getIdCirculo();
									ll_idMatricula     = lpr_predioRegistro.getIdMatricula();

									{
										AreaPredio lap_areaPredio;

										lap_areaPredio = DaoCreator.getAreaPredioDAO(ldm_manager)
												                       .findById(ls_idCirculo, ll_idMatricula);

										if(lap_areaPredio != null)
										{
											Collection<DetalleAreaPredio> lcdap_detalleAreaPredio;

											lcdap_detalleAreaPredio = DaoCreator.getDetalleAreaPredioDAO(ldm_manager)
													                                .findByIdAreaPredio(
													    String.valueOf(lap_areaPredio.getIdArea()), ls_idCirculo,
													    ll_idMatricula
													);

											if(CollectionUtils.isValidCollection(lcdap_detalleAreaPredio))
											{
												for(DetalleAreaPredio ldap_tmp : lcdap_detalleAreaPredio)
												{
													if(ldap_tmp != null)
													{
														String ls_tipoArea;

														ls_tipoArea = ldap_tmp.getIdTipoArea();

														if(ls_tipoArea != null)
														{
															switch(ls_tipoArea)
															{
																case IdentificadoresCommon.NUM1:
																	ld_areaInmueble = ldap_tmp.getArea();

																case IdentificadoresCommon.NUM2:
																	ld_areaConstruida = ldap_tmp.getArea();

																case IdentificadoresCommon.NUM3:
																	ld_areaPrivada = ldap_tmp.getArea();

																default:
																	break;
															}
														}
													}
												}
											}
										}
									}
								}

								if(NumericUtils.isValidDouble(ld_areaInmueble))
									ll_areaInmueble = NumericUtils.getLongWrapper(
										    NumericUtils.getDouble(ld_areaInmueble)
										);

								else
									ll_areaInmueble = new Long(NumericUtils.DEFAULT_LONG_VALUE);

								if(NumericUtils.isValidDouble(ld_areaConstruida))
									ll_areaConstruida = NumericUtils.getLongWrapper(
										    NumericUtils.getDouble(ld_areaConstruida)
										);
								else
									ll_areaConstruida = new Long(NumericUtils.DEFAULT_LONG_VALUE);

								if(NumericUtils.isValidDouble(ld_areaPrivada))
									ll_areaPrivada = NumericUtils.getLongWrapper(
										    NumericUtils.getDouble(ld_areaPrivada)
										);
								else
									ll_areaPrivada = new Long(NumericUtils.DEFAULT_LONG_VALUE);
							}

							ltsdi_salida = new TipoSalidaDatosInmueble(
								    String.valueOf(lpr_predioRegistro.getIdMatricula()),
								    lpr_predioRegistro.getIdCirculo(), lcr_circuloRegistral.getNombre(),
								    lpr_predioRegistro.getNupre(), ls_estadoNupre, lpr_predioRegistro.getNumeroPredial(),
								    lpr_predioRegistro.getNumeroPredialAnt(), ls_direccionPredio,
								    lp_predio.getCodDepartamento(), ls_nombreDepartamento, lp_predio.getCodMunicipio(),
								    ls_nombreMunicipio, ls_nombreVereda,
								    IdentificadoresCommon.SIN_INFORMACION    /*nomBarrio*/,
								    obtenerCalendarDesdeDate(lpr_predioRegistro.getFechaCreacion()),
								    obtenerCalendarDesdeDate(ld_fechaInstrumento), lpr_predioRegistro.getRadicacion(),
								    ls_nomTipoDocumentoPublico, ls_nir, ll_areaInmueble, ll_areaConstruida,
								    ll_areaPrivada,
								    consultarEstadoPredio(lpr_predioRegistro.getIdEstadoPredio(), ldm_manager),
								    ls_tipopredio, ls_linderos, ls_complementaciones, lbi_codigoMensaje, "OK"
								);
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
			clh_LOGGER.error("consultarDatosInmueble", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltsdi_salida              = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDatosInmueble", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltsdi_salida              = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdi_salida == null)
			ltsdi_salida = new TipoSalidaDatosInmueble(
				    null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				    null, null, null, null, null, null, null, null, null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltsdi_salida;
	}

	/**
	 * Consultar propietarios.
	 *
	 * @param atedp_entrada de atedp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosPropietario consultarPropietarios(
	    TipoEntradaDatosPropietario atedp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosPropietario ltsdp_salida;
		DAOManager                 ldm_manager;
		BigInteger                 lbi_codigoMensaje;
		String                     ls_descripcionMensaje;

		ltsdp_salida              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atedp_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(atedp_entrada);

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
						Collection<PropietarioCatastro> lcp_propietario;

						lcp_propietario = DaoCreator.getPropietarioDAO(ldm_manager)
								                        .findPropietariosAnotacionPredio(
								    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula(), 0, true,
								    true
								);

						if(CollectionUtils.isValidCollection(lcp_propietario))
						{
							TipoPropietario[] ltp_tipoPropietario;
							int               li_count;

							ltp_tipoPropietario     = new TipoPropietario[lcp_propietario.size()];
							li_count                = 0;

							for(PropietarioCatastro lpc_temp : lcp_propietario)
							{
								if(lpc_temp != null)
								{
									String ls_idDocumentoTipo;

									ls_idDocumentoTipo = lpc_temp.getIdDocumentoTipo();

									if(StringUtils.isValidString(ls_idDocumentoTipo))
									{
										String ls_primerNombre;
										String ls_segundoNombre;
										String ls_primerApellido;
										String ls_segundoApellido;
										String ls_nombreCompleto;
										String ls_razonSocial;
										String ls_numeroDocumento;
										String ls_numNIT;

										ls_primerNombre        = null;
										ls_segundoNombre       = null;
										ls_primerApellido      = null;
										ls_segundoApellido     = null;
										ls_nombreCompleto      = null;
										ls_razonSocial         = null;
										ls_numeroDocumento     = null;
										ls_numNIT              = null;

										if(ls_idDocumentoTipo.equalsIgnoreCase(IdentificadoresCommon.NIT))
										{
											ls_razonSocial        = lpc_temp.getRazonSocial();
											ls_nombreCompleto     = lpc_temp.getRazonSocial();
											ls_numNIT             = lpc_temp.getNumDocumentoPersona();
										}
										else
										{
											ls_primerNombre        = lpc_temp.getPrimerNombre();
											ls_segundoNombre       = lpc_temp.getSegundoNombre();
											ls_primerApellido      = lpc_temp.getPrimerApellido();
											ls_segundoApellido     = lpc_temp.getSegundoApellido();
											ls_numeroDocumento     = lpc_temp.getNumDocumentoPersona();
											ls_nombreCompleto      = lpc_temp.getNombrePersona();
										}

										ltp_tipoPropietario[li_count] = new TipoPropietario(
											    lpc_temp.getTipoPersona(), ls_idDocumentoTipo, ls_numeroDocumento,
											    ls_numNIT, lpc_temp.getTipoDocumentoPersona(), ls_primerApellido,
											    ls_segundoApellido, ls_primerNombre, ls_segundoNombre,
											    lpc_temp.getDescripcionGenero(), ls_razonSocial, ls_nombreCompleto,
											    lpc_temp.getPorcentajeParticipacion()
											);

										li_count++;
									}
								}
							}

							ltsdp_salida = new TipoSalidaDatosPropietario(ltp_tipoPropietario, lbi_codigoMensaje, "OK");
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
			clh_LOGGER.error("consultarPropietarios", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltsdp_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarPropietarios", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltsdp_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdp_salida == null)
			ltsdp_salida = new TipoSalidaDatosPropietario(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsdp_salida;
	}

	/**
	 * Consultar direcciones anteriores.
	 *
	 * @param ateda_entrada de ateda entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida direcciones anteriores
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    TipoEntradaDireccionesAnteriores ateda_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDireccionesAnteriores ltsda_salida;
		DAOManager                      ldm_manager;
		BigInteger                      lbi_codigoMensaje;
		String                          ls_descripcionMensaje;

		ltsda_salida              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(ateda_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(ateda_entrada);

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
						Collection<DireccionPredio> lcdp_direccionPredio;

						lcdp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager)
								                             .findByIdCirculoMatricula(
								    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula()
								);

						if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
						{
							TipoDireccionAnterior[] ltda_tipoDireccionAnterior;
							int                     li_count;

							ltda_tipoDireccionAnterior     = new TipoDireccionAnterior[lcdp_direccionPredio.size()];
							li_count                       = 0;

							for(DireccionPredio ldp_tmp : lcdp_direccionPredio)
							{
								if(ldp_tmp != null)
								{
									ltda_tipoDireccionAnterior[li_count] = new TipoDireccionAnterior(
										    ldp_tmp.getDireccion()
										);

									li_count++;
								}
							}

							ltsda_salida = new TipoSalidaDireccionesAnteriores(
								    ltda_tipoDireccionAnterior, lbi_codigoMensaje, "OK"
								);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDireccionesAnteriores", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltsda_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDireccionesAnteriores", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltsda_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsda_salida == null)
			ltsda_salida = new TipoSalidaDireccionesAnteriores(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsda_salida;
	}

	/**
	 * Consultar matriculas.
	 *
	 * @param atecm_entrada de atecm entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consulta matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultaMatriculas consultarMatriculas(
	    TipoEntradaConsultaMatriculas atecm_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultaMatriculas ltscm_salida;
		DAOManager                   ldm_manager;
		BigInteger                   lbi_codigoMensaje;
		String                       ls_descripcionMensaje;
		TipoMatricula[]              ltm_tipoMatricula;
		int                          li_countMatricula;

		ltscm_salida              = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		ltm_tipoMatricula         = null;
		li_countMatricula         = 0;

		try
		{
			if(atecm_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(atecm_entrada);

			{
				String ls_criterioBusqueda;
				String ls_valorCriterioBusqueda;
				String ls_codDepartamento;
				String ls_codMunicipio;
				String ls_mensajeConvenioCirculos;

				ls_criterioBusqueda            = lp_predio.getCriterioBusqueda();
				ls_valorCriterioBusqueda       = lp_predio.getValorCriterioBusqueda();
				ls_codDepartamento             = lp_predio.getCodDepartamento();
				ls_codMunicipio                = lp_predio.getCodMunicipio();
				ls_mensajeConvenioCirculos     = null;

				if(
				    !StringUtils.isValidString(ls_criterioBusqueda)
					    || !StringUtils.isValidString(ls_valorCriterioBusqueda)
				)
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_CRITERIO_VALOR_BUSQUEDA));

				{
					boolean lb_deptValidado;

					lb_deptValidado = false;

					if(StringUtils.isValidString(ls_codDepartamento))
					{
						validarPaisDepMun(
						    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, ls_codDepartamento, null, false, true, false,
						    ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);

						lb_deptValidado = true;
					}

					if(StringUtils.isValidString(ls_codMunicipio) && lb_deptValidado)
						validarPaisDepMun(
						    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT, null, ls_codMunicipio, false, false, true,
						    ProyectosCommon.COEXISTENCIA_14, ldm_manager
						);
				}

				String ls_documentoTipo;

				ls_documentoTipo = null;

				if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoCC
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.CC;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoCE
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.CE;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoNUIP
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.NU;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoPA
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.PS;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoTI
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.TI;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaConsultaMatriculasCriterioBusqueda._numeroDocumentoNIT
					    )
				)
					ls_documentoTipo = IdentificadoresCommon.NIT;

				if(StringUtils.isValidString(ls_documentoTipo))
				{
					Persona lp_persona;

					lp_persona = new Persona();

					lp_persona.setNumeroDocumento(ls_valorCriterioBusqueda);
					lp_persona.setTipoDocIdentidad(ls_documentoTipo);

					lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
							                   .findByPersonData(lp_persona, IdentificadoresCommon.TIPO_NUM_DOC);

					if(lp_persona != null)
					{
						Collection<PredioRegistro> lcpr_predioRegistro;

						lcpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
								                            .findAllByPersona(
								    lp_persona.getIdPersona(), ls_codDepartamento, ls_codMunicipio
								);

						if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
						{
							CoexistenciaResponse       lcr_request;
							Collection<PredioRegistro> lcps_cllFiltrada;

							lcr_request = new CoexistenciaResponse(lp_predio);
							lcr_request.setCllPredioRegistro(lcpr_predioRegistro);

							lcr_request     = filtrarConsulta(
								    lcr_request,
								    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_REGISTRO,
								    ldm_manager
								);

							lcps_cllFiltrada               = lcr_request.getCllPredioRegistro();
							ls_mensajeConvenioCirculos     = lcr_request.getMensajeConvenioCirculos();

							if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
							{
								Collection<String> lcs_matricula;

								lcs_matricula = new ArrayList<String>();

								for(PredioRegistro lpr_pr : lcps_cllFiltrada)
								{
									if(lpr_pr != null)
										lcs_matricula.add(
										    lpr_pr.getIdCirculo() + String.valueOf(lpr_pr.getIdMatricula())
										);
								}

								if(CollectionUtils.isValidCollection(lcs_matricula))
								{
									ltm_tipoMatricula = new TipoMatricula[lcs_matricula.size()];

									for(String ls_tmp : lcs_matricula)
									{
										if(ls_tmp != null)
										{
											ltm_tipoMatricula[li_countMatricula] = new TipoMatricula(ls_tmp);

											li_countMatricula++;
										}
									}
								}
								else
									throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
							}
							else if(StringUtils.isValidString(ls_mensajeConvenioCirculos))
								throw new B2BException(
								    addErrorCX(ErrorKeys.ERROR_MATRICULAS_PROPIETARIOS_NO_DISPONIBLES)
								);
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
				else
				{
					if(ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaConsultaMatriculasCriterioBusqueda._nombres))
					{
						Object lo_object;

						lo_object = new Gson().fromJson(
							    String.valueOf(atecm_entrada.getValorCriterioBusqueda()), Object.class
							);

						if((lo_object != null) && (lo_object instanceof Map<?, ?>))
						{
							Map<String, String> lltm_datos;

							lltm_datos = (LinkedTreeMap<String, String>)lo_object;

							if(CollectionUtils.isValidCollection(lltm_datos))
							{
								String ls_primerNombre;
								String ls_segundoNombre;
								String ls_primerApellido;
								String ls_segundoApellido;

								ls_primerNombre        = lltm_datos.containsKey(IdentificadoresCommon.primerNombreJSON)
									? lltm_datos.get(IdentificadoresCommon.primerNombreJSON) : null;
								ls_segundoNombre       = lltm_datos.containsKey(
									    IdentificadoresCommon.segundoNombreJSON
									) ? lltm_datos.get(IdentificadoresCommon.segundoNombreJSON) : null;
								ls_primerApellido      = lltm_datos.containsKey(
									    IdentificadoresCommon.primerApellidoJSON
									) ? lltm_datos.get(IdentificadoresCommon.primerApellidoJSON) : null;
								ls_segundoApellido     = lltm_datos.containsKey(
									    IdentificadoresCommon.segundoApellidoJSON
									) ? lltm_datos.get(IdentificadoresCommon.segundoApellidoJSON) : null;

								if(
								    !StringUtils.isValidString(ls_primerNombre)
									    || !StringUtils.isValidString(ls_primerApellido)
								)
									throw new B2BException(
									    addErrorCX(ErrorKeys.ERROR_PRIMER_NOMBRE_APELLIDO_PROPIETARIO)
									);

								Persona lp_persona;

								lp_persona = new Persona();

								lp_persona.setPrimerNombre(ls_primerNombre);
								lp_persona.setSegundoNombre(ls_segundoNombre);
								lp_persona.setPrimerApellido(ls_primerApellido);
								lp_persona.setSegundoApellido(ls_segundoApellido);

								{
									Collection<Persona> lcp_cllPersona;

									lcp_cllPersona = DaoCreator.getPersonaDAO(ldm_manager)
											                       .findCollectionByPersonData(
											    lp_persona, IdentificadoresCommon.NOMBRES
											);

									if(CollectionUtils.isValidCollection(lcp_cllPersona))
									{
										Collection<String> lcs_matricula;

										lcs_matricula = new ArrayList<String>();

										for(Persona lp_personaTmp : lcp_cllPersona)
										{
											if(lp_personaTmp != null)
											{
												Collection<PredioRegistro> lcpr_predioRegistro;

												lcpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
														                            .findAllByPersona(
														    lp_personaTmp.getIdPersona(), ls_codDepartamento,
														    ls_codMunicipio
														);

												if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
												{
													CoexistenciaResponse       lcr_request;
													Collection<PredioRegistro> lcps_cllFiltrada;

													lcr_request = new CoexistenciaResponse(lp_predio);
													lcr_request.setCllPredioRegistro(lcpr_predioRegistro);

													lcr_request     = filtrarConsulta(
														    lcr_request,
														    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_REGISTRO,
														    ldm_manager
														);

													lcps_cllFiltrada               = lcr_request.getCllPredioRegistro();
													ls_mensajeConvenioCirculos     = lcr_request
															.getMensajeConvenioCirculos();

													if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
													{
														for(PredioRegistro lpr_pr : lcps_cllFiltrada)
														{
															if(lpr_pr != null)
																lcs_matricula.add(
																    lpr_pr.getIdCirculo()
																    + String.valueOf(lpr_pr.getIdMatricula())
																);
														}
													}
													else if(StringUtils.isValidString(ls_mensajeConvenioCirculos))
														throw new B2BException(
														    addErrorCX(
														        ErrorKeys.ERROR_MATRICULAS_PROPIETARIOS_NO_DISPONIBLES
														    )
														);
													else
														throw new B2BException(
														    addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS)
														);
												}
											}
										}

										if(CollectionUtils.isValidCollection(lcs_matricula))
										{
											ltm_tipoMatricula = new TipoMatricula[lcs_matricula.size()];

											for(String ls_tmp : lcs_matricula)
											{
												if(ls_tmp != null)
												{
													ltm_tipoMatricula[li_countMatricula] = new TipoMatricula(ls_tmp);

													li_countMatricula++;
												}
											}
										}
										else
											throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
									}
									else
										throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
								}
							}
						}
					}
					else if(
					    ls_criterioBusqueda.equalsIgnoreCase(
						        TipoEntradaConsultaMatriculasCriterioBusqueda._razonSocial
						    )
					)
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
								                     .findById(ConstanteCommon.CANTIDAD_MINIMA_LONGITUD_RAZON_SOCIAL);

						if(lc_constante != null)
						{
							String ls_caracter;

							ls_caracter = lc_constante.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
							{
								if(ls_valorCriterioBusqueda.length() < Integer.parseInt(ls_caracter))
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = ls_caracter;

									throw new B2BException(
									    addErrorCX(ErrorKeys.ERROR_CANTIDAD_CARACTERES_RAZON_SOCIAL, loa_arg)
									);
								}

								Persona lp_persona;

								lp_persona = new Persona();

								lp_persona.setRazonSocial(ls_valorCriterioBusqueda);

								{
									Collection<Persona> lcp_cllPersona;

									lcp_cllPersona = DaoCreator.getPersonaDAO(ldm_manager)
											                       .findCollectionByPersonData(
											    lp_persona, IdentificadoresCommon.RAZON_SOCIAL
											);

									if(CollectionUtils.isValidCollection(lcp_cllPersona))
									{
										Collection<String> lcs_matricula;

										lcs_matricula = new ArrayList<String>();

										for(Persona lp_personaTmp : lcp_cllPersona)
										{
											if(lp_personaTmp != null)
											{
												Collection<PredioRegistro> lcpr_predioRegistro;

												lcpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
														                            .findAllByPersona(
														    lp_personaTmp.getIdPersona(), ls_codDepartamento,
														    ls_codMunicipio
														);

												if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
												{
													CoexistenciaResponse       lcr_request;
													Collection<PredioRegistro> lcps_cllFiltrada;

													lcr_request = new CoexistenciaResponse(lp_predio);
													lcr_request.setCllPredioRegistro(lcpr_predioRegistro);

													lcr_request     = filtrarConsulta(
														    lcr_request,
														    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_REGISTRO,
														    ldm_manager
														);

													lcps_cllFiltrada               = lcr_request.getCllPredioRegistro();
													ls_mensajeConvenioCirculos     = lcr_request
															.getMensajeConvenioCirculos();

													if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
													{
														for(PredioRegistro lpr_pr : lcps_cllFiltrada)
														{
															if(lpr_pr != null)
																lcs_matricula.add(
																    lpr_pr.getIdCirculo()
																    + String.valueOf(lpr_pr.getIdMatricula())
																);
														}
													}
													else if(StringUtils.isValidString(ls_mensajeConvenioCirculos))
														throw new B2BException(
														    addErrorCX(
														        ErrorKeys.ERROR_MATRICULAS_PROPIETARIOS_NO_DISPONIBLES
														    )
														);
													else
														throw new B2BException(
														    addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS)
														);
												}
											}
										}

										if(CollectionUtils.isValidCollection(lcs_matricula))
										{
											ltm_tipoMatricula = new TipoMatricula[lcs_matricula.size()];

											for(String ls_tmp : lcs_matricula)
											{
												if(ls_tmp != null)
												{
													ltm_tipoMatricula[li_countMatricula] = new TipoMatricula(ls_tmp);

													li_countMatricula++;
												}
											}
										}
										else
											throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
									}
									else
										throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
								}
							}
							else
								throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
						}
						else
							throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
					}
					else if(
					    ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaConsultaMatriculasCriterioBusqueda._direccion)
					)
					{
						Collection<PredioRegistro> lcpr_predioRegistro;

						lcpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
								                            .findAllByDireccionCompleta(
								    lp_predio.getValorCriterioBusqueda(), ls_codDepartamento, ls_codMunicipio
								);

						if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
						{
							CoexistenciaResponse       lcr_request;
							Collection<PredioRegistro> lcps_cllFiltrada;

							lcr_request = new CoexistenciaResponse(lp_predio);
							lcr_request.setCllPredioRegistro(lcpr_predioRegistro);

							lcr_request     = filtrarConsulta(
								    lcr_request,
								    com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PREDIO_REGISTRO,
								    ldm_manager
								);

							lcps_cllFiltrada               = lcr_request.getCllPredioRegistro();
							ls_mensajeConvenioCirculos     = lcr_request.getMensajeConvenioCirculos();

							if(CollectionUtils.isValidCollection(lcps_cllFiltrada))
							{
								Collection<String> lcs_matricula;

								lcs_matricula = new ArrayList<String>();

								for(PredioRegistro lpr_pr : lcps_cllFiltrada)
								{
									if(lpr_pr != null)
										lcs_matricula.add(
										    lpr_pr.getIdCirculo() + String.valueOf(lpr_pr.getIdMatricula())
										);
								}

								if(CollectionUtils.isValidCollection(lcs_matricula))
								{
									ltm_tipoMatricula = new TipoMatricula[lcs_matricula.size()];

									for(String ls_tmp : lcs_matricula)
									{
										if(ls_tmp != null)
										{
											ltm_tipoMatricula[li_countMatricula] = new TipoMatricula(ls_tmp);

											li_countMatricula++;
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

				{
					String     ls_mensajeFinal;
					BigInteger lbi_codigoFinal;

					ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
						? ls_mensajeConvenioCirculos : "OK";
					lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
						? BigInteger.valueOf(CodigoMensajeCommon.CODIGO_201) : lbi_codigoMensaje;

					ltscm_salida = new TipoSalidaConsultaMatriculas(
						    ltm_tipoMatricula, lbi_codigoFinal, ls_mensajeFinal
						);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculas", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltscm_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarMatriculas", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltscm_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltscm_salida == null)
			ltscm_salida = new TipoSalidaConsultaMatriculas(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltscm_salida;
	}

	/**
	 * Consultar datos basicos.
	 *
	 * @param atedbm_entrada de atedbm entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida datos basicos matriculas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    TipoEntradaDatosBasicosMatriculas atedbm_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaDatosBasicosMatriculas                                                                ltsdbm_salida;
		DAOManager                                                                                      ldm_manager;
		BigInteger                                                                                      lbi_codigoMensaje;
		String                                                                                          ls_descripcionMensaje;
		co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula[] ltm_tipoMatriculaDatosBasicos;
		int                                                                                             li_countMatriculas;
		String                                                                                          ls_mensajeConvenioCirculos;

		ltsdbm_salida                     = null;
		ldm_manager                       = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje                 = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje             = null;
		li_countMatriculas                = 0;
		ltm_tipoMatriculaDatosBasicos     = null;
		ls_mensajeConvenioCirculos        = null;

		try
		{
			if(atedbm_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;
			String ls_criterioBusqueda;
			String ls_valorCriterioBusqueda;

			lp_predio                    = new Predio(atedbm_entrada);
			ls_criterioBusqueda          = lp_predio.getCriterioBusqueda();
			ls_valorCriterioBusqueda     = lp_predio.getValorCriterioBusqueda();

			if(!StringUtils.isValidString(ls_criterioBusqueda) || !StringUtils.isValidString(ls_valorCriterioBusqueda))
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_CRITERIO_VALOR_BUSQUEDA));

			if(
			    ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoCC
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoCE
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoNUIP
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoPA
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoTI
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoNIT
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaDatosBasicosMatriculasCriterioBusqueda._nombres)
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._razonSocial
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._direccion
				    )
			)
			{
				TipoEntradaConsultaMatriculas                 ltecm_consultaMatriculas;
				TipoSalidaConsultaMatriculas                  ltscm_salidaMatriculas;
				TipoEntradaConsultaMatriculasCriterioBusqueda ltecmb_criterioBusqueda;

				ltecmb_criterioBusqueda = null;

				if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoCC
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoCC;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoCE
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoCE;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoNUIP
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoNUIP;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoPA
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoPA;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoTI
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoTI;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroDocumentoNIT
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.numeroDocumentoNIT;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaDatosBasicosMatriculasCriterioBusqueda._nombres)
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.nombres;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(
					        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._razonSocial
					    )
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.razonSocial;
				else if(
				    ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaDatosBasicosMatriculasCriterioBusqueda._direccion)
				)
					ltecmb_criterioBusqueda = TipoEntradaConsultaMatriculasCriterioBusqueda.direccion;

				ltecm_consultaMatriculas     = new TipoEntradaConsultaMatriculas(
					    lp_predio.getConvenio(), lp_predio.getCodDepartamento(), lp_predio.getCodMunicipio(),
					    ltecmb_criterioBusqueda, lp_predio.getValorCriterioBusqueda()
					);

				ltscm_salidaMatriculas = consultarMatriculas(ltecm_consultaMatriculas, as_userId, as_remoteIp);

				if(ltscm_salidaMatriculas != null)
				{
					int li_codigoMensajeConsultarMatriculas;

					li_codigoMensajeConsultarMatriculas = NumericUtils.getInt(ltscm_salidaMatriculas.getCodMensaje());

					if((li_codigoMensajeConsultarMatriculas == 200) || (li_codigoMensajeConsultarMatriculas == 201))
					{
						if(li_codigoMensajeConsultarMatriculas == 201)
							ls_mensajeConvenioCirculos = ltscm_salidaMatriculas.getDescripcionMensaje();

						TipoMatricula[] tm_tipoMatricula;

						tm_tipoMatricula = ltscm_salidaMatriculas.getMatriculas();

						if(CollectionUtils.isValidCollection(tm_tipoMatricula))
						{
							ltm_tipoMatriculaDatosBasicos = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula[tm_tipoMatricula.length];

							for(TipoMatricula ltm_tmp : tm_tipoMatricula)
							{
								if(ltm_tmp != null)
								{
									Predio lp_predioTmp;

									lp_predioTmp = new Predio();

									lp_predioTmp.setConvenio(lp_predio.getConvenio());
									lp_predioTmp.setNumeroIdentificacionPredio(ltm_tmp.getMatricula());
									lp_predioTmp.setTipoIdentificacionPredio(TipoIdentificacionPredioCommon.MATRICULA);

									consultarDatosPorMatricula(
									    ltm_tipoMatriculaDatosBasicos, li_countMatriculas, lp_predioTmp, as_userId,
									    as_remoteIp
									);

									li_countMatriculas++;
								}
							}
						}
					}
					else
						throw new B2BException(ltscm_salidaMatriculas.getDescripcionMensaje());
				}
			}
			else if(
			    ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaDatosBasicosMatriculasCriterioBusqueda._matricula)
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroPredial
				    )
				    || ls_criterioBusqueda.equalsIgnoreCase(
				        TipoEntradaDatosBasicosMatriculasCriterioBusqueda._numeroPredialAnterior
				    ) || ls_criterioBusqueda.equalsIgnoreCase(TipoEntradaDatosBasicosMatriculasCriterioBusqueda._NUPRE)
			)
			{
				ltm_tipoMatriculaDatosBasicos = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula[1];

				lp_predio.setNumeroIdentificacionPredio(ls_valorCriterioBusqueda);
				lp_predio.setTipoIdentificacionPredio(ls_criterioBusqueda);

				consultarDatosPorMatricula(
				    ltm_tipoMatriculaDatosBasicos, li_countMatriculas, lp_predio, as_userId, as_remoteIp
				);

				li_countMatriculas++;
			}

			{
				String     ls_mensajeFinal;
				BigInteger lbi_codigoFinal;

				ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
					? ls_mensajeConvenioCirculos : "OK";
				lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
					? BigInteger.valueOf(CodigoMensajeCommon.CODIGO_201) : lbi_codigoMensaje;

				ltsdbm_salida = new TipoSalidaDatosBasicosMatriculas(
					    NumericUtils.getBigInteger(li_countMatriculas), ltm_tipoMatriculaDatosBasicos, lbi_codigoFinal,
					    ls_mensajeFinal
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDatosBasicos", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);

			ltsdbm_salida = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarDatosBasicos", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();

			ltsdbm_salida = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltsdbm_salida == null)
			ltsdbm_salida = new TipoSalidaDatosBasicosMatriculas(null, null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltsdbm_salida;
	}

	/**
	 * Consultar datos por matricula.
	 *
	 * @param atm_tipoMatriculaDatosBasicos de atm tipo matricula datos basicos
	 * @param ai_countMatriculas de ai count matriculas
	 * @param ap_predio de ap predio
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarDatosPorMatricula(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula[] atm_tipoMatriculaDatosBasicos,
	    int                                                                                             ai_countMatriculas,
	    Predio                                                                                          ap_predio,
	    String                                                                                          as_userId,
	    String                                                                                          as_remoteIp
	)
	    throws B2BException
	{
		TipoEntradaDatosInmueble                         ltedi_entradaDatosInmueble;
		TipoSalidaDatosInmueble                          ltsdi_salidaDatosInmueble;
		String                                           ls_tipoidentificacionPredio;
		TipoEntradaDatosInmuebleTipoIdentificacionPredio lteditip_tipoidentificacionPredio;

		ls_tipoidentificacionPredio           = ap_predio.getTipoIdentificacionPredio();
		lteditip_tipoidentificacionPredio     = null;

		if(ls_tipoidentificacionPredio.equalsIgnoreCase(TipoEntradaDatosInmuebleTipoIdentificacionPredio._matricula))
			lteditip_tipoidentificacionPredio = TipoEntradaDatosInmuebleTipoIdentificacionPredio.matricula;
		else if(
		    ls_tipoidentificacionPredio.equalsIgnoreCase(
			        TipoEntradaDatosInmuebleTipoIdentificacionPredio._numeroPredial
			    )
		)
			lteditip_tipoidentificacionPredio = TipoEntradaDatosInmuebleTipoIdentificacionPredio.numeroPredial;
		else if(
		    ls_tipoidentificacionPredio.equalsIgnoreCase(
			        TipoEntradaDatosInmuebleTipoIdentificacionPredio._numeroPredialAnterior
			    )
		)
			lteditip_tipoidentificacionPredio = TipoEntradaDatosInmuebleTipoIdentificacionPredio.numeroPredialAnterior;
		else if(ls_tipoidentificacionPredio.equalsIgnoreCase(TipoEntradaDatosInmuebleTipoIdentificacionPredio._NUPRE))
			lteditip_tipoidentificacionPredio = TipoEntradaDatosInmuebleTipoIdentificacionPredio.NUPRE;

		ltedi_entradaDatosInmueble     = new TipoEntradaDatosInmueble(
			    ap_predio.getConvenio(), lteditip_tipoidentificacionPredio, ap_predio.getNumeroIdentificacionPredio()
			);

		ltsdi_salidaDatosInmueble = consultarDatosInmueble(ltedi_entradaDatosInmueble, as_userId, as_remoteIp);

		if(ltsdi_salidaDatosInmueble != null)
		{
			int li_codMensaje;

			li_codMensaje = NumericUtils.getInt(ltsdi_salidaDatosInmueble.getCodMensaje());

			if(li_codMensaje == 200)
			{
				TipoEntradaDatosPropietario                         ltedp_entradaDatosPropietario;
				TipoSalidaDatosPropietario                          ltsdp_salidaDatosPropietario;
				String                                              ls_tipoidentificacionPredioProp;
				TipoEntradaDatosPropietarioTipoIdentificacionPredio ltedptip_tipoIdentificacion;

				ls_tipoidentificacionPredioProp     = ap_predio.getTipoIdentificacionPredio();
				ltedptip_tipoIdentificacion         = null;

				if(
				    ls_tipoidentificacionPredioProp.equalsIgnoreCase(
					        TipoEntradaDatosPropietarioTipoIdentificacionPredio._matricula
					    )
				)
					ltedptip_tipoIdentificacion = TipoEntradaDatosPropietarioTipoIdentificacionPredio.matricula;
				else if(
				    ls_tipoidentificacionPredioProp.equalsIgnoreCase(
					        TipoEntradaDatosPropietarioTipoIdentificacionPredio._numeroPredial
					    )
				)
					ltedptip_tipoIdentificacion = TipoEntradaDatosPropietarioTipoIdentificacionPredio.numeroPredial;
				else if(
				    ls_tipoidentificacionPredioProp.equalsIgnoreCase(
					        TipoEntradaDatosPropietarioTipoIdentificacionPredio._numeroPredialAnterior
					    )
				)
					ltedptip_tipoIdentificacion = TipoEntradaDatosPropietarioTipoIdentificacionPredio.numeroPredialAnterior;
				else if(
				    ls_tipoidentificacionPredioProp.equalsIgnoreCase(
					        TipoEntradaDatosPropietarioTipoIdentificacionPredio._NUPRE
					    )
				)
					ltedptip_tipoIdentificacion = TipoEntradaDatosPropietarioTipoIdentificacionPredio.NUPRE;

				{
					co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[] ltp_tipoPropietarioDatosBasicos;

					ltp_tipoPropietarioDatosBasicos     = null;

					ltedp_entradaDatosPropietario     = new TipoEntradaDatosPropietario(
						    ap_predio.getConvenio(), ltedptip_tipoIdentificacion,
						    ap_predio.getNumeroIdentificacionPredio()
						);

					ltsdp_salidaDatosPropietario = consultarPropietarios(
						    ltedp_entradaDatosPropietario, as_userId, as_remoteIp
						);

					if(
					    (ltsdp_salidaDatosPropietario != null)
						    && (NumericUtils.getInt(ltsdp_salidaDatosPropietario.getCodMensaje()) == 200)
					)
					{
						TipoPropietario[] ltp_tipoPropietario;

						ltp_tipoPropietario = ltsdp_salidaDatosPropietario.getPropietarios();

						if(CollectionUtils.isValidCollection(ltp_tipoPropietario))
						{
							int li_propietarioCount;

							ltp_tipoPropietarioDatosBasicos     = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario[ltp_tipoPropietario.length];
							li_propietarioCount                 = 0;

							for(TipoPropietario ltp_tmp : ltp_tipoPropietario)
							{
								if(ltp_tmp != null)
								{
									ltp_tipoPropietarioDatosBasicos[li_propietarioCount] = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario(
										    ltp_tmp.getTipoDocumentoPersona(), ltp_tmp.getNumDocumentoPersona(),
										    ltp_tmp.getNumNIT(), ltp_tmp.getPrimerApellido(),
										    ltp_tmp.getSegundoApellido(), ltp_tmp.getPrimerNombre(),
										    ltp_tmp.getSegundoNombre(), ltp_tmp.getRazonSocial(),
										    ltp_tmp.getPorcentajeParticipacion()
										);

									li_propietarioCount++;
								}
							}
						}
					}

					atm_tipoMatriculaDatosBasicos[ai_countMatriculas] = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula(
						    NumericUtils.getInteger(ltsdi_salidaDatosInmueble.getEstadoFolio()),
						    ltsdi_salidaDatosInmueble.getNumMatriculaInmobiliaria(),
						    ltsdi_salidaDatosInmueble.getCodCirculoRegistral(),
						    ltsdi_salidaDatosInmueble.getNomCirculoRegistral(),
						    ltsdi_salidaDatosInmueble.getNumPredial(), ltsdi_salidaDatosInmueble.getEstadoNUPRE(),
						    ltsdi_salidaDatosInmueble.getNUPRE(), ltsdi_salidaDatosInmueble.getDireccionPredio(),
						    ltsdi_salidaDatosInmueble.getCodDepartamento(),
						    ltsdi_salidaDatosInmueble.getNomDepartamento(), ltsdi_salidaDatosInmueble.getCodMunicipio(),
						    ltsdi_salidaDatosInmueble.getNomMunicipio(), ltsdi_salidaDatosInmueble.getTipoPredio(),
						    ltp_tipoPropietarioDatosBasicos
						);
				}
			}
			else
				throw new B2BException(li_codMensaje + " - " + ltsdi_salidaDatosInmueble.getDescripcionMensaje());
		}
	}
}
