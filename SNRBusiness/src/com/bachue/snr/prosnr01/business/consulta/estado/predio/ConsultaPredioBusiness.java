package com.bachue.snr.prosnr01.business.consulta.estado.predio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.arcgis.json.ClienteJSON;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.EtapaDAO;
import com.bachue.snr.prosnr01.dao.pgn.FasesDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.consulta.solicitud.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 *  Clase que contiene los métodos de negocio  para la consulta de un predio especifico.
 *
 * @author Alejandro Guayambuco.
 */
public class ConsultaPredioBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaPredioBusiness.class);

	/**
	 *  Método encargado de  realizar consulta de anotaciones para un predio con unos criterios previamente seleccionados.
	 *
	 *
	 * @param acp_consultaPredio Objeto que contiene los criterios y la información seleccionados  para la respectiva consulta.
	 * @param as_userId Variable de tipo String que contiene el usuario que realiza la acción.
	 * @return Retorna  un objeto de tipo ConsultaPredio que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio consultaCriterios(ConsultaPredio acp_consultaPredio, String as_userId)
	    throws B2BException
	{
		DAOManager                       ldm_manager;
		ConsultaPredio                   lccp_return;
		Documento                        lod_documentoCriterios;
		Persona                          lop_personaCriterios;
		Collection<RegistroCalificacion> lcrc_dataFinal;
		String                           ls_idNaturaleza;
		Long                             ll_idAnotacion;
		Collection<Documento>            lcd_infoAll;
		AnotacionPredioDAO               loap_DAO;

		lcrc_dataFinal     = new ArrayList<RegistroCalificacion>();
		ldm_manager        = DaoManagerFactory.getDAOManager();
		lccp_return        = null;
		lcd_infoAll        = null;
		loap_DAO           = DaoCreator.getAnotacionPredioDAO(ldm_manager);

		try
		{
			if(acp_consultaPredio != null)
			{
				boolean lb_fechaDocumento;
				boolean lb_idTipoOficina;
				boolean lb_idOficinaOrigen;
				boolean lb_documetoTipo;
				boolean lb_primerNombre;
				boolean lb_segundoNombre;
				boolean lb_primerApellido;
				boolean lb_segundoApellido;
				boolean lb_razonSocial;
				boolean lb_consultaTotal;

				lod_documentoCriterios     = acp_consultaPredio.getDocumentoCriterio();
				lop_personaCriterios       = acp_consultaPredio.getPersonaCriterio();
				ls_idNaturaleza            = acp_consultaPredio.getIdNaturaleza();
				ll_idAnotacion             = acp_consultaPredio.getIdAnotacion();
				lb_consultaTotal           = acp_consultaPredio.isConsultaTotal();

				lb_fechaDocumento      = false;
				lb_idTipoOficina       = false;
				lb_idOficinaOrigen     = false;
				lb_documetoTipo        = false;
				lb_primerNombre        = false;
				lb_segundoNombre       = false;
				lb_primerApellido      = false;
				lb_segundoApellido     = false;
				lb_razonSocial         = false;

				if(lb_consultaTotal)
					lcrc_dataFinal = loap_DAO.consultaCriterios(acp_consultaPredio);
				else
				{
					if(lod_documentoCriterios != null)
					{
						{
							String ls_idTipoDocumento;
							ls_idTipoDocumento = lod_documentoCriterios.getIdTipoDocumento();

							if(!StringUtils.isValidString(ls_idTipoDocumento))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
						}

						{
							String ls_numero;
							ls_numero = lod_documentoCriterios.getNumero();

							if(!StringUtils.isValidString(ls_numero))
								throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
						}

						{
							Date ld_fechaDocumento;
							ld_fechaDocumento = lod_documentoCriterios.getFechaDocumento();

							if(ld_fechaDocumento != null)
								lb_fechaDocumento = true;
						}

						{
							String ls_idTipoOficina;
							ls_idTipoOficina = lod_documentoCriterios.getIdTipoOficina();

							if(StringUtils.isValidString(ls_idTipoOficina))
								lb_idTipoOficina = true;
						}

						{
							String ls_idOficinaOrigen;

							ls_idOficinaOrigen = lod_documentoCriterios.getIdOficinaOrigen();

							if(StringUtils.isValidString(ls_idOficinaOrigen))
								lb_idOficinaOrigen = true;
						}

						{
							DocumentoDAO lodd_DAO;

							lodd_DAO = DaoCreator.getDocumentoDAO(ldm_manager);

							lod_documentoCriterios.setFechaDocumentoValid(lb_fechaDocumento);
							lod_documentoCriterios.setIdOficinaOrigenValid(lb_idOficinaOrigen);
							lod_documentoCriterios.setTipoOficinaValid(lb_idTipoOficina);
							acp_consultaPredio.setInfoDocumentoCriterio(true);
							acp_consultaPredio.setDocumentoCriterio(lod_documentoCriterios);

							lcd_infoAll = lodd_DAO.consultarDocumentoCriterios(lod_documentoCriterios);
						}
					}

					if(lop_personaCriterios != null)
					{
						String ls_primeroNombre;
						String ls_segundoNombre;
						String ls_primerApellido;
						String ls_segundoApellido;
						String ls_Documento;
						String ls_razonSocial;

						ls_Documento           = lop_personaCriterios.getIdDocumentoTipo();
						ls_primeroNombre       = lop_personaCriterios.getPrimerNombre();
						ls_segundoNombre       = lop_personaCriterios.getSegundoNombre();
						ls_primerApellido      = lop_personaCriterios.getPrimerApellido();
						ls_segundoApellido     = lop_personaCriterios.getSegundoApellido();
						ls_razonSocial         = lop_personaCriterios.getRazonSocial();

						if(StringUtils.isValidString(ls_Documento))
						{
							if(ExpresionRegular.getExpresionRegular().contieneCaracteresEspeciales(ls_Documento))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);

							lb_documetoTipo = true;
						}

						lb_primerNombre        = StringUtils.isValidString(ls_primeroNombre);
						lb_segundoNombre       = StringUtils.isValidString(ls_segundoNombre);
						lb_primerApellido      = StringUtils.isValidString(ls_primerApellido);
						lb_segundoApellido     = StringUtils.isValidString(ls_segundoApellido);
						lb_razonSocial         = StringUtils.isValidString(ls_razonSocial);

						if(!lb_documetoTipo)
						{
							if(!lb_primerNombre || !lb_primerApellido)
							{
								if(!lb_razonSocial)
									throw new B2BException(ErrorKeys.SIN_PARAMETROS_ENTRADA);
							}
						}

						{
							lop_personaCriterios.setDocumentoValid(lb_documetoTipo);
							lop_personaCriterios.setPrimerNombreValid(lb_primerNombre);
							lop_personaCriterios.setSegundoNombreValid(lb_segundoNombre);
							lop_personaCriterios.setPrimerApellidoValid(lb_primerApellido);
							lop_personaCriterios.setSegundoApellidoValid(lb_segundoApellido);
							lop_personaCriterios.setRazonSocialValid(lb_razonSocial);
							acp_consultaPredio.setInfoPersonaCriterio(true);
							acp_consultaPredio.setPersonaCriterio(lop_personaCriterios);
						}
					}

					if(NumericUtils.isValidLong(ll_idAnotacion))
						acp_consultaPredio.setAnotacioneCriterio(true);

					if(StringUtils.isValidString(ls_idNaturaleza))
						acp_consultaPredio.setNaturalezaCriterio(true);

					if(CollectionUtils.isValidCollection(lcd_infoAll))
					{
						Collection<RegistroCalificacion> lcrc_anotacionTmp;
						Collection<RegistroCalificacion> lcrc_anotacionFinal;

						lcrc_anotacionTmp       = new ArrayList<RegistroCalificacion>();
						lcrc_anotacionFinal     = new ArrayList<RegistroCalificacion>();

						for(Documento lod_tmp : lcd_infoAll)
						{
							if(lod_tmp != null)
							{
								acp_consultaPredio.getDocumentoCriterio().setIdDocumento(lod_tmp.getIdDocumento());

								lcrc_anotacionTmp = loap_DAO.consultaCriterios(acp_consultaPredio);

								if(CollectionUtils.isValidCollection(lcrc_anotacionTmp))
									lcrc_anotacionFinal.addAll(lcrc_anotacionTmp);
							}
						}

						if(CollectionUtils.isValidCollection(lcrc_anotacionFinal))
							lcrc_dataFinal = lcrc_anotacionFinal;
					}

					else
						lcrc_dataFinal = loap_DAO.consultaCriterios(acp_consultaPredio);
				}

				if(CollectionUtils.isValidCollection(lcrc_dataFinal))
				{
					AnotacionPredioCiudadanoDAO      loapc_DAO;
					String                           ls_circulo;
					Long                             ll_matricula;
					DocumentoDAO                     lodd_dao;
					RegistroCalificacion             lod_dataDoc;
					AnotacionCancelacionDAO          loac_dao;
					AnotacionCancelacion             loac_ac;
					Collection<AnotacionCancelacion> lcac_data;
					StringBuilder                    lsb_cancelaciones;

					loapc_DAO        = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager);
					lccp_return      = new ConsultaPredio();
					ls_circulo       = acp_consultaPredio.getIdCirculo();
					ll_matricula     = acp_consultaPredio.getIdMatricula();
					lod_dataDoc      = new RegistroCalificacion();
					lodd_dao         = DaoCreator.getDocumentoDAO(ldm_manager);
					loac_dao         = DaoCreator.getBngAnotacionCancelacionDAO(ldm_manager);

					Collection<RegistroCalificacion> lcrc_infoInterviniente;
					lcrc_infoInterviniente = new ArrayList<RegistroCalificacion>();

					for(RegistroCalificacion lorc_tmp : lcrc_dataFinal)
					{
						if(lorc_tmp != null)
						{
							lorc_tmp.setIdMatricula(ll_matricula);
							lorc_tmp.setIdCirculo(ls_circulo);
							lod_dataDoc = lodd_dao.detalleDocumento(lorc_tmp.getIdDocumento());

							if(lod_dataDoc != null)
								lorc_tmp.setDatosDocumento(lod_dataDoc);

							lcrc_infoInterviniente = loapc_DAO.findDataPredioAnotacion(lorc_tmp, false);

							if(CollectionUtils.isValidCollection(lcrc_infoInterviniente))
								lorc_tmp.setAllMatriculas(lcrc_infoInterviniente);

							lsb_cancelaciones     = new StringBuilder();
							loac_ac               = new AnotacionCancelacion();

							loac_ac.setIdAnotacion(NumericUtils.getLong(lorc_tmp.getIdAnotacion()));
							loac_ac.setIdCirculo(ls_circulo);
							loac_ac.setIdMatricula(NumericUtils.getLong(ll_matricula));

							lcac_data = loac_dao.findByAnotaciones(loac_ac);

							if(CollectionUtils.isValidCollection(lcac_data))
							{
								for(AnotacionCancelacion loac_tmp : lcac_data)
									lsb_cancelaciones.append(loac_tmp.getIdAnotacion1() + ",");

								if(StringUtils.isValidString(lsb_cancelaciones.toString()))
									lorc_tmp.setAnotacionCancelacion(lsb_cancelaciones.toString());
							}
						}
					}

					lccp_return.getAnotaciones().setAllMatriculas(lcrc_dataFinal);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaCriterios", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccp_return;
	}

	/**
	 *  Método encargado de  realizar consulta del detalle de un predio para una matrícula determinada.
	 *
	 * @param acp_consultaPredio Objeto que contiene una matrícula determinada para realizar la busqueda del detalle para ese predio.
	 * @return  Retorna  un objeto de tipo ConsultaPredio que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio consultar(ConsultaPredio acp_consultaPredio)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		ConsultaPredio lcp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_return      = new ConsultaPredio();

		try
		{
			if(acp_consultaPredio != null)
			{
				Collection<ConsultaPredio> lccp_return;
				Long                       ll_idMatricula;
				String                     ls_idCirculo;

				lccp_return     = new ArrayList<ConsultaPredio>();

				ll_idMatricula     = acp_consultaPredio.getIdMatricula();
				ls_idCirculo       = acp_consultaPredio.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					if(NumericUtils.isValidLong(ll_idMatricula))
					{
						PredioRegistro lpr_predioRegistro;

						lpr_predioRegistro = new PredioRegistro();

						lpr_predioRegistro.setValidMatricula(true);
						lpr_predioRegistro.setIdCirculo(ls_idCirculo);
						lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

						lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager).findById(lpr_predioRegistro);

						if(lpr_predioRegistro != null)
						{
							{
								if(
								    StringUtils.getStringNotNull(lpr_predioRegistro.getPredioInconsistente())
									               .equalsIgnoreCase(EstadoCommon.S)
								)
									throw new B2BException(ErrorKeys.PREDIO_INCONSISTENTE);

								if(
								    StringUtils.getStringNotNull(lpr_predioRegistro.getIdEstadoPredio())
									               .equalsIgnoreCase(EstadoCommon.A)
								)
									throw new B2BException(ErrorKeys.PREDIO_ANULADO);

								if(
								    !StringUtils.getStringNotNull(lpr_predioRegistro.getPredioDefinitivo())
									                .equalsIgnoreCase(EstadoCommon.D)
								)
									throw new B2BException(ErrorKeys.PREDIO_NO_DEFINTIIVO);
							}

							Collection<SolicitudMatricula> lcsm_solicitudesMatricula;
							SolicitudMatricula             lsm_solicitudMatricula;
							lsm_solicitudMatricula = new SolicitudMatricula();

							lcp_return.setEstadoPredio(lpr_predioRegistro.getNombreEstado());
							lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);
							lsm_solicitudMatricula.setIdMatricula(NumericUtils.getLong(ll_idMatricula));

							lcsm_solicitudesMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
									                                  .findByCirculoMatricula(lsm_solicitudMatricula);

							if(CollectionUtils.isValidCollection(lcsm_solicitudesMatricula))
							{
								SolicitudDAO lsd_SolicitudDAO;
								TurnoDAO     ltd_turnoDAO;
								ProcesoDAO   lpd_procesoDAO;
								EtapaDAO     led_EtapaDAO;
								FasesDAO     lfd_fasesDAO;

								lsd_SolicitudDAO     = DaoCreator.getSolicitudDAO(ldm_manager);
								ltd_turnoDAO         = DaoCreator.getTurnoDAO(ldm_manager);
								lpd_procesoDAO       = DaoCreator.getProcesoDAO(ldm_manager);
								led_EtapaDAO         = DaoCreator.getEtapaDAO(ldm_manager);
								lfd_fasesDAO         = DaoCreator.getFasesDAO(ldm_manager);

								for(SolicitudMatricula lsm_iterador : lcsm_solicitudesMatricula)
								{
									if(lsm_iterador != null)
									{
										ConsultaPredio lcp_consultaPredio;
										String         ls_idSolicitud;

										lcp_consultaPredio     = new ConsultaPredio();
										ls_idSolicitud         = lsm_iterador.getIdSolicitud();

										if(StringUtils.isValidString(ls_idSolicitud))
										{
											Turno     lt_turno;
											Solicitud ls_solicitud;

											ls_solicitud     = new Solicitud();
											lt_turno         = new Turno();

											ls_solicitud.setIdSolicitud(ls_idSolicitud);
											lt_turno.setIdSolicitud(ls_idSolicitud);
											lt_turno.setIdCirculo(ls_idCirculo);

											ls_solicitud     = lsd_SolicitudDAO.findById(ls_solicitud);
											lt_turno         = ltd_turnoDAO.findBySolicitudProceso(lt_turno);

											if((ls_solicitud != null) && (lt_turno != null))
											{
												String ls_nir;
												String ls_idTurno;

												ls_nir         = ls_solicitud.getNir();
												ls_idTurno     = lt_turno.getIdTurno();

												if(
												    StringUtils.isValidString(ls_nir)
													    && StringUtils.isValidString(ls_idTurno)
												)
												{
													String ls_idProceso;
													Long   ll_idEtapaActual;

													ls_idProceso         = ls_solicitud.getIdProceso();
													ll_idEtapaActual     = lt_turno.getIdEtapaActual();

													lcp_consultaPredio.setNir(ls_nir);
													lcp_consultaPredio.setIdTurno(ls_idTurno);

													if(StringUtils.isValidString(ls_idProceso))
													{
														Proceso lp_proceso;

														lp_proceso = new Proceso();

														lp_proceso.setIdProceso(ls_idProceso);

														lp_proceso = lpd_procesoDAO.findById(lp_proceso);

														if(lp_proceso != null)
														{
															String ls_nombre;

															ls_nombre = lp_proceso.getNombre();

															if(StringUtils.isValidString(ls_nombre))
																lcp_consultaPredio.setProceso(ls_nombre);
														}
													}

													if(NumericUtils.isValidLong(ll_idEtapaActual))
													{
														Etapa le_etapa;

														le_etapa = new Etapa();

														le_etapa.setIdEtapa(NumericUtils.getLong(ll_idEtapaActual));

														le_etapa = led_EtapaDAO.findById(le_etapa);

														if(le_etapa != null)
														{
															Fases lf_fases;
															lf_fases = new Fases();
															lf_fases.setIdFase(le_etapa.getIdFase());

															lf_fases = lfd_fasesDAO.findById(lf_fases);

															if(lf_fases != null)
															{
																String ls_nombre;

																ls_nombre = lf_fases.getNombre();

																if(StringUtils.isValidString(ls_nombre))
																	lcp_consultaPredio.setEstado(ls_nombre);
															}
														}
													}

													lcp_consultaPredio.setSolicitudMatricula(lsm_iterador);
													lcp_consultaPredio.setIdCirculo(ls_idCirculo);
													lcp_consultaPredio.setIdMatricula(ll_idMatricula);

													lccp_return.add(lcp_consultaPredio);
												}
											}
										}
									}
								}
							}
						}
						else
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;

							throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PREDIO, aoa_messageArgs);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);
				}
				else
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);

				lcp_return.setDataConsulta(lccp_return);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultar", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_return;
	}

	/**
	 *  Método encargado de  realizar consulta del detalle de un predio para una matrícula Temporal determinada.
	 *
	 * @param acp_consultaPredio Objeto que contiene una matrícula temporal determinada para realizar la busqueda del detalle para ese predio.
	 * @return  Retorna  un objeto de tipo ConsultaPredio que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio consultarAcc(ConsultaPredio acp_consultaPredio)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		ConsultaPredio locp_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		locp_return     = new ConsultaPredio();

		try
		{
			if(acp_consultaPredio != null)
			{
				Long   ll_idMatricula;
				String ls_idCirculo;

				ll_idMatricula     = acp_consultaPredio.getIdMatricula();
				ls_idCirculo       = acp_consultaPredio.getIdCirculo();

				if(StringUtils.isValidString(ls_idCirculo))
				{
					if(NumericUtils.isValidLong(ll_idMatricula))
					{
						AccPredioRegistro lpr_predioRegistro;

						lpr_predioRegistro = new AccPredioRegistro();

						lpr_predioRegistro.setIdCirculo(ls_idCirculo);
						lpr_predioRegistro.setIdMatricula(ll_idMatricula);

						lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
								                           .findByCirculoMatricula(lpr_predioRegistro);

						if(lpr_predioRegistro != null)
						{
							if(
							    StringUtils.getStringNotNull(lpr_predioRegistro.getPredioInconsistente())
								               .equalsIgnoreCase(EstadoCommon.S)
							)
								throw new B2BException(ErrorKeys.PREDIO_INCONSISTENTE);

							if(
							    StringUtils.getStringNotNull(lpr_predioRegistro.getIdEstadoPredio())
								               .equalsIgnoreCase(EstadoCommon.A)
							)
								throw new B2BException(ErrorKeys.PREDIO_ANULADO);

//							if(
//							    !StringUtils.getStringNotNull(lpr_predioRegistro.getPredioDefinitivo())
//								                .equalsIgnoreCase(EstadoCommon.T)
//							)
//								throw new B2BException(ErrorKeys.PREDIO_NO_DEFINTIIVO);
							EstadoPredio lsp_sp;

							lsp_sp = new EstadoPredio();

							lsp_sp.setIdEstadoPredio(lpr_predioRegistro.getIdEstadoPredio());

							lsp_sp = DaoCreator.getEstadoPredioDao(ldm_manager).findById(lsp_sp);

							if(lsp_sp != null)
								locp_return.setEstadoPredio(StringUtils.getStringUpperCase(lsp_sp.getNombre()));
						}
						else
						{
							Object[] aoa_messageArgs = new String[1];

							aoa_messageArgs[0] = ls_idCirculo + "-" + ll_idMatricula;

							throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_PREDIO, aoa_messageArgs);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_MATRICULA);
				}
				else
					throw new B2BException(ErrorKeys.CIRCULO_REGISTRAL_SELECCIONADO_INVALIDO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarAcc", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return locp_return;
	}

	/**
	 * Método encargado de  realizar consulta de url de mapa geográfico para un número predial determinado.
	 *
	 * @param as_numeroPredial Variable de tipo String que contiene un  número predial determinado.
	 *
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 */
	public synchronized String obtenerUrlMapaGeografico(String as_numeroPredial)
	    throws B2BException
	{
		String     ls_return;
		DAOManager ldm_manager;

		ls_return       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_DAO;

			String        ls_invocarMapa;

			lcd_DAO     = DaoCreator.getConstantesDAO(ldm_manager);

			ls_invocarMapa = lcd_DAO.findString(ConstanteCommon.ARCGIS_INVOCAR_MAPA);

			if(BooleanUtils.getBooleanValue(ls_invocarMapa))
			{
				String ls_urlEncontrar;
				String ls_arcgisEndpointLocal;
				String ls_arcgisEndpointOnline;
				String ls_urlExportar;

				ls_urlEncontrar             = lcd_DAO.findString(ConstanteCommon.ARCGIS_ENCONTRAR_URL);
				ls_arcgisEndpointLocal      = lcd_DAO.findString(ConstanteCommon.ARCGIS_ENDPOINT_LOCAL);
				ls_arcgisEndpointOnline     = lcd_DAO.findString(ConstanteCommon.ARCGIS_ENDPOINT_ARCGISONLINE);
				ls_urlExportar              = lcd_DAO.findString(ConstanteCommon.ARCGIS_EXPORTAR_MAPA_URL);

				ls_return = ClienteJSON.getMap(
					    as_numeroPredial, ls_urlEncontrar, ls_urlExportar, ls_arcgisEndpointLocal,
					    ls_arcgisEndpointOnline
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerUrlMapaGeografico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}
}
