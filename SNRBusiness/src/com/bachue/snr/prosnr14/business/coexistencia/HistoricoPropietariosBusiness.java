package com.bachue.snr.prosnr14.business.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.HistoricoPropietarioDTO;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.PropietarioDTO;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios;

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

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;

import com.bachue.snr.prosnr14.common.constants.ErrorKeys;

import com.bachue.snr.prosnr14.model.coexistencia.CoexistenciaResponse;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades HistoricoPropietariosBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class HistoricoPropietariosBusiness extends CoexistenciaBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(HistoricoPropietariosBusiness.class, ProyectosCommon.COEXISTENCIA_14);

	/**
	 * Consultar historico propiedades.
	 *
	 * @param atechp_entrada de atechp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar historico propiedades
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(
	    TipoEntradaConsultarHistoricoPropiedades atechp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultarHistoricoPropiedades                                                                                     ltschp_salida;
		DAOManager                                                                                                                  ldm_manager;
		BigInteger                                                                                                                  lbi_codigoMensaje;
		String                                                                                                                      ls_descripcionMensaje;
		co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.PropietarioDTO[] lpdto_propietario;

		ltschp_salida             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;
		lpdto_propietario         = null;

		try
		{
			if(atechp_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			boolean lb_busquedaNIT;
			Persona lp_persona;

			lb_busquedaNIT     = false;
			lp_persona         = null;

			if(StringUtils.isValidString(atechp_entrada.getNumNIT()))
			{
				lb_busquedaNIT     = true;
				lp_persona         = new Persona(atechp_entrada, lb_busquedaNIT);
			}
			else
				lp_persona = new Persona(atechp_entrada, lb_busquedaNIT);

			String ls_numeroDocumento;
			String ls_tipoDocumento;

			ls_numeroDocumento     = lp_persona.getNumeroDocumento();
			ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();

			if(lb_busquedaNIT)
			{
				if(!StringUtils.isValidString(ls_numeroDocumento))
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_VALOR_NIT));
			}
			else
			{
				if(StringUtils.isValidString(ls_tipoDocumento) && !StringUtils.isValidString(ls_numeroDocumento))
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_VALOR_NUM_DOC_PERSONA));

				if(!StringUtils.isValidString(ls_tipoDocumento) && StringUtils.isValidString(ls_numeroDocumento))
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_TIPO_DOCUMENTO_PERSONA));

				if(!StringUtils.isValidString(ls_tipoDocumento) && !StringUtils.isValidString(ls_numeroDocumento))
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_VALOR_BUSQUEDA));
			}

			//TODO Implementar filtros de busqueda
			if(lb_busquedaNIT)
				lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
						                   .findRazonSocialByDocumento(ls_numeroDocumento, lp_persona.getRazonSocial());
			else
				lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
						                   .findByNumTipoDocOrigenBachue(ls_numeroDocumento, ls_tipoDocumento);

			if(lp_persona != null)
			{
				lpdto_propietario = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.PropietarioDTO[1];

				Collection<Propietario> lcp_propietario;

				lcp_propietario = DaoCreator.getPropietarioDAO(ldm_manager).findByPersona(lp_persona.getIdPersona());

				if(CollectionUtils.isValidCollection(lcp_propietario))
				{
					Predio                  lp_predio;
					CoexistenciaResponse    lcr_response;
					Collection<Propietario> lcp_cllFiltrada;
					String                  ls_mensajeConvenioCirculos;

					lp_predio = new Predio();

					lp_predio.setConvenio(atechp_entrada.getConvenio());

					lcr_response = new CoexistenciaResponse(lp_predio);

					lcr_response.setCllPropietario(lcp_propietario);

					lcr_response     = filtrarConsulta(
						    lcr_response, com.bachue.snr.prosnr14.common.constants.IdentificadoresCommon.PROPIETARIO,
						    ldm_manager
						);

					lcp_cllFiltrada                = lcr_response.getCllPropietario();
					ls_mensajeConvenioCirculos     = lcr_response.getMensajeConvenioCirculos();

					if(CollectionUtils.isValidCollection(lcp_cllFiltrada))
					{
						HistoricoPropietarioDTO[] lhp_historicoMatriculas;
						int                       li_countMatricula;

						lhp_historicoMatriculas     = new HistoricoPropietarioDTO[lcp_cllFiltrada.size()];
						li_countMatricula           = 0;

						for(Propietario lp_tmp : lcp_cllFiltrada)
						{
							if(lp_tmp != null)
							{
								lhp_historicoMatriculas[li_countMatricula] = new HistoricoPropietarioDTO(
									    NumericUtils.getInteger(lp_tmp.getOrden()), lp_tmp.getIdCirculo(),
									    String.valueOf(lp_tmp.getPorcentajeParticipacion()),
									    obtenerCalendarDesdeDate(lp_tmp.getFechaDesde()),
									    obtenerCalendarDesdeDate(lp_tmp.getFechaHasta()),
									    String.valueOf(lp_tmp.getIdMatricula()), lp_tmp.getNupre()
									);

								li_countMatricula++;
							}
						}

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

						if(!lb_busquedaNIT)
						{
							ls_tipoDocumentoPersona       = lp_persona.getIdDocumentoTipo();
							ls_numeroDocumentoPersona     = lp_persona.getNumeroDocumento();
							ls_primerNombre               = lp_persona.getPrimerNombre();
							ls_segundoNombre              = lp_persona.getSegundoNombre();
							ls_primerApellido             = lp_persona.getPrimerApellido();
							ls_segundoApellido            = lp_persona.getSegundoApellido();
						}
						else
						{
							ls_numNIT          = lp_persona.getNumeroDocumento();
							ls_razonSocial     = lp_persona.getRazonSocial();
						}

						lpdto_propietario[0] = new PropietarioDTO(
							    lhp_historicoMatriculas, ls_tipoDocumentoPersona, ls_numeroDocumentoPersona, ls_numNIT,
							    ls_primerApellido, ls_primerNombre, ls_segundoApellido, ls_segundoNombre, ls_razonSocial
							);

						{
							String     ls_mensajeFinal;
							BigInteger lbi_codigoFinal;

							ls_mensajeFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
								? ls_mensajeConvenioCirculos : "OK";
							lbi_codigoFinal     = StringUtils.isValidString(ls_mensajeConvenioCirculos)
								? BigInteger.valueOf(CodigoMensajeCommon.CODIGO_201) : lbi_codigoMensaje;

							ltschp_salida = new TipoSalidaConsultarHistoricoPropiedades(
								    lpdto_propietario, lbi_codigoFinal, ls_mensajeFinal
								);
						}
					}
					else if(StringUtils.isValidString(ls_mensajeConvenioCirculos))
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_MATRICULAS_PROPIETARIOS_NO_DISPONIBLES));
					else
						throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
				}
				else
					throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
			}
			else
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_CONSULTA_RESULTADOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarHistoricoPropiedades", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltschp_salida             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarHistoricoPropiedades", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltschp_salida             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltschp_salida == null)
			ltschp_salida = new TipoSalidaConsultarHistoricoPropiedades(null, lbi_codigoMensaje, ls_descripcionMensaje);

		return ltschp_salida;
	}

	/**
	 * Consultar historico propietarios.
	 *
	 * @param atechp_entrada de atechp entrada
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar historico propietarios
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(
	    TipoEntradaConsultarHistoricoPropietarios atechp_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultarHistoricoPropietarios ltschp_salida;
		DAOManager                               ldm_manager;
		BigInteger                               lbi_codigoMensaje;
		String                                   ls_descripcionMensaje;

		ltschp_salida             = null;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ls_descripcionMensaje     = null;

		try
		{
			if(atechp_entrada == null)
				throw new B2BException(addErrorCX(ErrorKeys.ERROR_OPERACION));

			Predio lp_predio;

			lp_predio = new Predio(atechp_entrada);

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
						Collection<Propietario> lcp_cllPropietario;

						lcp_cllPropietario = DaoCreator.getPropietarioDAO(ldm_manager)
								                           .findByIdCirculoMatriculaActivo(
								    lpr_predioRegistro.getIdCirculo(), lpr_predioRegistro.getIdMatricula()
								);

						if(CollectionUtils.isValidCollection(lcp_cllPropietario))
						{
							co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.PropietarioDTO[] lpdto_propietario;
							int                                                                                                                          li_countPropietario;

							lpdto_propietario       = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.PropietarioDTO[lcp_cllPropietario
									.size()];
							li_countPropietario     = 0;

							for(Propietario lp_tmp : lcp_cllPropietario)
							{
								if(lp_tmp != null)
								{
									Persona lp_persona;

									lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_tmp.getIdPersona());

									if(lp_persona != null)
									{
										String ls_idTipoPersona;

										ls_idTipoPersona = lp_persona.getIdTipoPersona();

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
												ls_tipoDocumentoPersona       = lp_persona.getIdDocumentoTipo();
												ls_numeroDocumentoPersona     = lp_persona.getNumeroDocumento();
												ls_primerNombre               = lp_persona.getPrimerNombre();
												ls_segundoNombre              = lp_persona.getSegundoNombre();
												ls_primerApellido             = lp_persona.getPrimerApellido();
												ls_segundoApellido            = lp_persona.getSegundoApellido();
											}
											else
											{
												ls_numNIT          = lp_persona.getNumeroDocumento();
												ls_razonSocial     = lp_persona.getRazonSocial();
											}

											{
												Collection<Propietario> lcp_propietario;

												lcp_propietario = DaoCreator.getPropietarioDAO(ldm_manager)
														                        .findByPersona(
														    lp_tmp.getIdPersona(), lpr_predioRegistro.getIdCirculo(),
														    NumericUtils.getLongWrapper(
														        lpr_predioRegistro.getIdMatricula()
														    )
														);

												if(CollectionUtils.isValidCollection(lcp_propietario))
												{
													co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[] lhp_historicoMatriculas;
													int                                                                                                                                   li_count;

													lhp_historicoMatriculas     = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO[lcp_propietario
															.size()];
													li_count                    = 0;

													for(Propietario lp_temp : lcp_propietario)
													{
														if(lp_temp != null)
														{
															lhp_historicoMatriculas[li_count] = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.HistoricoPropietarioDTO(
																    NumericUtils.getInteger(lp_temp.getOrden()),
																    lp_temp.getIdCirculo(),
																    String.valueOf(
																        lp_temp.getPorcentajeParticipacion()
																    ), obtenerCalendarDesdeDate(
																        lp_temp.getFechaDesde()
																    ), obtenerCalendarDesdeDate(
																        lp_temp.getFechaHasta()
																    ), String.valueOf(lp_temp.getIdMatricula()),
																    lp_temp.getNupre()
																);

															li_count++;
														}
													}

													lpdto_propietario[li_countPropietario] = new co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.PropietarioDTO(
														    lhp_historicoMatriculas, ls_tipoDocumentoPersona,
														    ls_numeroDocumentoPersona, ls_numNIT, ls_primerApellido,
														    ls_primerNombre, ls_segundoApellido, ls_segundoNombre,
														    ls_razonSocial
														);

													li_countPropietario++;
												}
											}
										}
									}
								}
							}

							ltschp_salida = new TipoSalidaConsultarHistoricoPropietarios(
								    lpdto_propietario, lbi_codigoMensaje, ls_descripcionMensaje
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
			clh_LOGGER.error("consultarHistoricoPropietarios", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			ltschp_salida             = null;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarHistoricoPropietarios", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			ltschp_salida             = null;
		}
		finally
		{
			ldm_manager.close();
		}

		if(ltschp_salida == null)
			ltschp_salida = new TipoSalidaConsultarHistoricoPropietarios(
				    null, lbi_codigoMensaje, ls_descripcionMensaje
				);

		return ltschp_salida;
	}
}
