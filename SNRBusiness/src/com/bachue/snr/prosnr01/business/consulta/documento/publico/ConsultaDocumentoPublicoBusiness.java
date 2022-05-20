package com.bachue.snr.prosnr01.business.consulta.documento.publico;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoEstadoSolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * Permite el manejo de datos de negocio para las consultas por documento público.
 *
 * @author Manuel Blanco
 */
public class ConsultaDocumentoPublicoBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaDocumentoPublicoBusiness.class);

	/**
	 * Retorna el valor del objeto de Documento.
	 *
	 * @param acdd_datosDoc correspondiente al valor del tipo de objeto ConsultaDatosDocumento
	 * @return devuelve el valor de Documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public synchronized Documento findByDatosConsulta(ConsultaDatosDocumento acdd_datosDoc)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Documento  ld_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ld_return       = null;

		try
		{
			if(acdd_datosDoc == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Documento               ld_documento;
			OficinaOrigen           loo_oficinaOrigen;
			Collection<Documento>   lca_datos;
			Collection<Documento>   lca_documentosAsociados;
			Collection<Documento>   lca_documentosRegistrados;
			DocumentoDAO            ldd_documentoDAO;
			AnotacionPredioDAO      lapd_anotacionPredioDAO;
			SolicitudDAO            lsd_solicitudDAO;
			TipoDocumentoPublicoDAO ltdpd_tipoDocPubDAO;
			OficinaOrigenDAO        lood_oficinaOrigenDAO;
			boolean                 lb_circulo;
			String                  ls_circulo;

			ld_return                     = new Documento();
			ld_documento                  = acdd_datosDoc.getDocumento();
			loo_oficinaOrigen             = acdd_datosDoc.getOficinaOrigen();
			lca_datos                     = new LinkedList<Documento>();
			lca_documentosAsociados       = null;
			lca_documentosRegistrados     = null;
			ldd_documentoDAO              = DaoCreator.getDocumentoDAO(ldm_manager);
			lapd_anotacionPredioDAO       = DaoCreator.getAnotacionPredioDAO(ldm_manager);
			lsd_solicitudDAO              = DaoCreator.getSolicitudDAO(ldm_manager);
			ltdpd_tipoDocPubDAO           = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager);
			lood_oficinaOrigenDAO         = DaoCreator.getOficinaOrigenDAO(ldm_manager);
			lb_circulo                    = false;
			ls_circulo                    = null;

			if(ld_documento == null)
				ld_documento = new Documento();

			if(!StringUtils.isValidString(ld_documento.getIdTipoDocumento()))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			if(!StringUtils.isValidString(ld_documento.getNumero()))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NUMERO_DE_DOC);

			if(ld_documento.getFechaDocumento() == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);
			else
				ld_documento.setFechaDocumentoValid(true);

			{
				CirculoRegistral lcr_circulo;

				lcr_circulo = acdd_datosDoc.getCirculoRegistral();

				if(lcr_circulo != null)
				{
					ls_circulo     = lcr_circulo.getIdCirculo();
					lb_circulo     = StringUtils.isValidString(ls_circulo);
				}
			}

			if(loo_oficinaOrigen != null)
			{
				Collection<Documento> lcd_docs;
				OficinaOrigenDAO      lood_DAO;

				lcd_docs     = ldd_documentoDAO.consultarDocumentoCriterios(ld_documento);
				lood_DAO     = DaoCreator.getOficinaOrigenDAO(ldm_manager);

				if(CollectionUtils.isValidCollection(lcd_docs))
				{
					for(Documento ld_iterator : lcd_docs)
					{
						if(ld_iterator != null)
						{
							String     ls_idOficinaOrigen;
							BigDecimal lbd_version;

							ls_idOficinaOrigen     = ld_iterator.getIdOficinaOrigen();
							lbd_version            = ld_iterator.getVersion();

							if(
							    StringUtils.isValidString(ls_idOficinaOrigen)
								    && NumericUtils.isValidBigDecimal(lbd_version)
							)
							{
								OficinaOrigen loo_temp;

								loo_temp = lood_DAO.findById(ls_idOficinaOrigen, lbd_version);

								if(loo_temp != null)
								{
									String  ls_tipoOficina;
									String  ls_pais;
									String  ls_departamento;
									String  ls_municipio;
									boolean lb_tipoOficinaValido;
									boolean lb_paisValido;
									boolean lb_departamentoValido;
									boolean lb_municipioValido;

									ls_tipoOficina            = loo_oficinaOrigen.getIdOficinaOrigen();
									ls_pais                   = loo_oficinaOrigen.getIdPais();
									ls_departamento           = loo_oficinaOrigen.getIdDepartamento();
									ls_municipio              = loo_oficinaOrigen.getIdMunicipio();
									lb_tipoOficinaValido      = StringUtils.isValidString(ls_tipoOficina);
									lb_paisValido             = StringUtils.isValidString(ls_pais);
									lb_departamentoValido     = StringUtils.isValidString(ls_departamento);
									lb_municipioValido        = StringUtils.isValidString(ls_municipio);

									{
										Map<String, String> lmss_comparadores;

										lmss_comparadores = new HashMap<String, String>();

										if(lb_tipoOficinaValido)
											lmss_comparadores.put(ls_idOficinaOrigen, loo_temp.getIdOficinaOrigen());

										if(lb_paisValido)
											lmss_comparadores.put(ls_pais, loo_temp.getIdPais());

										if(lb_departamentoValido)
											lmss_comparadores.put(ls_departamento, loo_temp.getIdDepartamento());

										if(lb_municipioValido)
											lmss_comparadores.put(ls_municipio, loo_temp.getIdMunicipio());

										{
											Set<Map.Entry<String, String>> lmess_entry;

											lmess_entry = lmss_comparadores.entrySet();

											if(lmess_entry != null)
											{
												Iterator<Map.Entry<String, String>> lis_iterator;

												lis_iterator = lmess_entry.iterator();

												if(lis_iterator != null)
												{
													boolean lb_cumple;

													lb_cumple = true;

													while(lis_iterator.hasNext() && lb_cumple)
													{
														Map.Entry<String, String> lmess_iterator;

														lmess_iterator = lis_iterator.next();

														if(lmess_iterator != null)
														{
															String ls_oficinaOrigen;
															String ls_oficinaBd;

															ls_oficinaOrigen     = lmess_iterator.getKey();
															ls_oficinaBd         = lmess_iterator.getValue();

															if(
															    (StringUtils.isValidString(ls_oficinaOrigen)
																    && StringUtils.isValidString(ls_oficinaBd))
															)
															{
																if(!ls_oficinaOrigen.equalsIgnoreCase(ls_oficinaBd))
																	lb_cumple = false;
															}
															else
																lb_cumple = false;
														}
													}

													if(lb_cumple)
														lca_datos.add(ld_iterator);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS);
			}

			if(CollectionUtils.isValidCollection(lca_datos))
			{
				lca_documentosAsociados       = new LinkedList<Documento>();
				lca_documentosRegistrados     = new LinkedList<Documento>();

				for(Documento ld_doc : lca_datos)
				{
					if(ld_doc != null)
					{
						Collection<AnotacionPredio> lcap_anotaciones;

						lcap_anotaciones = lb_circulo
							? lapd_anotacionPredioDAO.findByIdDocumentoVersionCirculo(
							    ld_doc.getIdDocumento(), NumericUtils.getLong(ld_doc.getVersionDocumento()), ls_circulo
							)
							: lapd_anotacionPredioDAO.findByIdDocumentoVersion(
							    ld_doc.getIdDocumento(), NumericUtils.getLong(ld_doc.getVersionDocumento())
							);

						{
							TipoDocumentoPublico ltdp_temp;

							ltdp_temp = new TipoDocumentoPublico();

							ltdp_temp.setIdTipoDocumento(ld_doc.getIdTipoDocumento());

							ltdp_temp = ltdpd_tipoDocPubDAO.findById(ltdp_temp);

							if(ltdp_temp != null)
								ld_doc.setNombreTipoDocumento(ltdp_temp.getNombre());
						}

						{
							OficinaOrigen loo_temp;

							loo_temp = new OficinaOrigen();

							loo_temp.setIdOficinaOrigen(ld_doc.getIdOficinaOrigen());
							loo_temp.setVersion(ld_doc.getVersion());

							loo_temp = lood_oficinaOrigenDAO.findById(loo_temp);

							if(loo_temp != null)
								ld_doc.setNombreOficinaOrigen(loo_temp.getNombre());
						}

						Collection<Solicitud> lcs_solicitudes;

						lcs_solicitudes = lsd_solicitudDAO.findAllByIdDocumento(ld_doc, true);

						if(CollectionUtils.isValidCollection(lcs_solicitudes))
						{
							Iterator<Solicitud>    lis_iterador;
							Long                   ll_estadoTerminado;
							String                 ls_idProceso6;
							SolicitudMatriculaDAO  lsm_DAO;
							TipoEstadoSolicitudDAO ltes_DAO;
							TurnoDAO               lt_DAO;

							ll_estadoTerminado     = NumericUtils.getLongWrapper(5L);
							ls_idProceso6          = "6";
							lsm_DAO                = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lis_iterador           = lcs_solicitudes.iterator();
							ltes_DAO               = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager);
							lt_DAO                 = DaoCreator.getTurnoDAO(ldm_manager);

							while(lis_iterador.hasNext())
							{
								Solicitud ls_temp;

								ls_temp = lis_iterador.next();

								if(ls_temp != null)
								{
									Long ll_estadoSolicitud;

									ll_estadoSolicitud = ls_temp.getEstadoSolicitud();

									if(
									    (NumericUtils.isValidLong(ll_estadoSolicitud)
										    && (ll_estadoSolicitud.compareTo(ll_estadoTerminado) != 0))
										    || (!NumericUtils.isValidLong(ll_estadoSolicitud))
									)
									{
										String ls_nir;
										String ls_idSolicitud;

										ls_nir             = ls_temp.getNir();
										ls_idSolicitud     = ls_temp.getIdSolicitud();

										if(
										    StringUtils.isValidString(ls_nir)
											    && StringUtils.isValidString(ls_idSolicitud)
										)
										{
											Collection<Turno> lct_turnos;
											String            ls_nombreEstado;
											Turno             lt_param;

											ls_nombreEstado     = null;
											lt_param            = new Turno();

											lt_param.setIdSolicitud(ls_idSolicitud);
											lt_param.setIdProceso(ls_idProceso6);

											if(lb_circulo)
												lt_param.setIdCirculo(ls_circulo);

											if(NumericUtils.isValidLong(ll_estadoSolicitud))
											{
												TipoEstadoSolicitud ltes_estado;

												ltes_estado = new TipoEstadoSolicitud();

												ltes_estado.setIdTipoEstadoSolicitud(ll_estadoSolicitud.toString());

												ltes_estado = ltes_DAO.findById(ltes_estado);

												if(ltes_estado != null)
													ls_nombreEstado = ltes_estado.getNombre();
											}

											lct_turnos = lb_circulo
												? lt_DAO.findAllByIdSolicitudProcesoCirculo(lt_param)
												: lt_DAO.findAllByIdSolicitudProceso(lt_param);

											if(CollectionUtils.isValidCollection(lct_turnos))
											{
												for(Turno lt_iterador : lct_turnos)
												{
													if(lt_iterador != null)
													{
														String ls_idCirculo;

														ls_idCirculo = lt_iterador.getIdCirculo();

														if(StringUtils.isValidString(ls_idCirculo))
														{
															Collection<SolicitudMatricula> lcsm_matriculas;
															Documento                      ld_documentoAdd;
															SolicitudMatricula             lsm_param;
															StringBuilder                  lsb_matriculas;

															ld_documentoAdd     = new Documento();
															lsm_param           = new SolicitudMatricula();
															lsb_matriculas      = new StringBuilder();

															lsm_param.setIdSolicitud(ls_idSolicitud);
															lsm_param.setIdCirculo(ls_idCirculo);

															lcsm_matriculas = lsm_DAO.findByIdSolicitudCirculo(
																    lsm_param
																);

															if(CollectionUtils.isValidCollection(lcsm_matriculas))
															{
																Iterator<SolicitudMatricula> lism_iterator;

																lism_iterator = lcsm_matriculas.iterator();

																while(lism_iterator.hasNext())
																{
																	SolicitudMatricula lsm_iterador;

																	lsm_iterador = lism_iterator.next();

																	if(lsm_iterador != null)
																	{
																		String ls_idCirculoPredio;
																		long   ll_idMatriculaPredio;

																		ls_idCirculoPredio       = lsm_iterador
																				.getIdCirculo();
																		ll_idMatriculaPredio     = lsm_iterador
																				.getIdMatricula();

																		if(
																		    StringUtils.isValidString(
																			        ls_idCirculoPredio
																			    ) && (ll_idMatriculaPredio > 0)
																		)
																		{
																			lsb_matriculas.append(
																			    ls_idCirculoPredio + "-"
																			    + ll_idMatriculaPredio
																			);

																			if(lism_iterator.hasNext())
																				lsb_matriculas.append(
																				    IdentificadoresCommon.SIMBOLO_COMA
																				);
																		}
																	}
																}
															}

															ld_documentoAdd.setNir(ls_nir);
															ld_documentoAdd.setIdTurno(lt_iterador.getIdTurno());
															ld_documentoAdd.setEstado(ls_nombreEstado);
															ld_documentoAdd.setNombreOficinaOrigen(
															    ld_doc.getNombreOficinaOrigen()
															);
															ld_documentoAdd.setIdOficinaOrigen(
															    ld_doc.getIdOficinaOrigen()
															);
															ld_documentoAdd.setNirAsociados(lsb_matriculas.toString());

															lca_documentosAsociados.add(ld_documentoAdd);
														}
													}
												}
											}
										}
									}
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcap_anotaciones))
						{
							Map<String, Documento> lmsd_data;
							TurnoDAO               lt_DAO;

							lmsd_data     = new HashMap<String, Documento>();
							lt_DAO        = DaoCreator.getTurnoDAO(ldm_manager);

							for(AnotacionPredio lap_iterator : lcap_anotaciones)
							{
								if(lap_iterator != null)
								{
									Documento ld_documentoTemp;
									Long      ll_idAnotacion;
									String    ls_llave;
									String    ls_idTurno;
									String    ls_matricula;
									String    ls_idAnotacion;

									ld_documentoTemp     = new Documento();
									ls_idTurno           = lap_iterator.getRadicacion();
									ls_matricula         = lap_iterator.getIdCirculo() + "-"
										+ lap_iterator.getIdMatricula();
									ll_idAnotacion       = lap_iterator.getIdAnotacion();
									ls_idAnotacion       = "";
									ls_llave             = ls_matricula + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
										+ ls_idTurno;

									if(StringUtils.isValidString(ls_idTurno))
									{
										Turno lt_turno;

										lt_turno = new Turno();

										lt_turno.setIdTurno(ls_idTurno);

										ld_documentoTemp.setIdTurno(ls_idTurno);

										lt_turno = lt_DAO.findById(lt_turno);

										if(lt_turno != null)
										{
											String ls_idSolicitud;

											ls_idSolicitud = lt_turno.getIdSolicitud();

											if(StringUtils.isValidString(ls_idSolicitud))
											{
												Solicitud ls_solicitud;

												ls_solicitud = new Solicitud();

												ls_solicitud.setIdSolicitud(ls_idSolicitud);

												ls_solicitud = lsd_solicitudDAO.findById(ls_solicitud);

												if(ls_solicitud != null)
													ld_documentoTemp.setNir(ls_solicitud.getNir());
											}
										}
									}

									ld_documentoTemp.setIdCirculo(lap_iterator.getIdCirculo());
									ld_documentoTemp.setIdMatricula(lap_iterator.getIdMatricula());

									if(NumericUtils.isValidLong(ll_idAnotacion))
										ls_idAnotacion += ll_idAnotacion;

									ld_documentoTemp.setAnotaciones(ls_idAnotacion);

									if(!lmsd_data.containsKey(ls_llave))
										lmsd_data.put(ls_llave, ld_documentoTemp);
									else
									{
										Documento ld_data;

										ld_data = lmsd_data.get(ls_llave);

										if(ld_data != null)
										{
											String ls_anotacionesData;

											ls_anotacionesData = ld_data.getAnotaciones();
											ls_anotacionesData += (IdentificadoresCommon.SIMBOLO_COMA + ls_idAnotacion);

											ld_data.setAnotaciones(ls_anotacionesData);
											lmsd_data.replace(ls_llave, ld_data);
										}
									}
								}
							}

							for(Map.Entry<String, Documento> lmsd_iterator : lmsd_data.entrySet())
							{
								if(lmsd_iterator != null)
								{
									Documento ld_documentoIterator;

									ld_documentoIterator = lmsd_iterator.getValue();

									if(ld_documentoIterator != null)
										lca_documentosRegistrados.add(ld_documentoIterator);
								}
							}
						}
					}
				}

				ld_return.setDocumentosAsociados(lca_documentosAsociados);
				ld_return.setDocumentosRegistrados(lca_documentosRegistrados);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosConsulta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ld_return;
	}
}
