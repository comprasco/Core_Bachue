package com.bachue.snr.prosnr01.business.aprobador.antiguo.sistema;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.AccionAntSistemaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccPredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.FirmaMasivaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaActoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.FirmaMasiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;


/**
 * Clase que contiene todos la logica para la funcionalidad de aprobacion de antiguo sistema
 *
 * @author garias
 */
public class AprobadorAntiguoSistemaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AprobadorAntiguoSistemaBusiness.class);

	/**
	 * Busca los casos que tenga asignados un usuario determinado en etapa 110 para
	 * ser procesados, ya sea por firmar o devolver a antiguo sistema.
	 *
	 * @param ap_param Objeto el cual contiene la etapa y el id usuario para ser
	 * utilizados como filtro en la consulta
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @param as_remoteIp Dirección IP del cliente que ejecuta el proceso
	 * @return Colección de aprobaciones resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Aprobacion> findAllData(Aprobacion ap_param, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		TurnoHistoriaDAO       lthd_tad;
		Collection<Aprobacion> lca_dataFinal;
		Collection<Aprobacion> lca_dataFinalTemp;

		ldm_manager           = DaoManagerFactory.getDAOManager();
		lthd_tad              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		lca_dataFinal         = null;
		lca_dataFinalTemp     = null;

		try
		{
			if(ap_param == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Collection<Aprobacion> lca_data;
			String                 ls_nir;
			String                 ls_idSolicitud;

			ls_nir             = ap_param.getNir();
			ls_idSolicitud     = null;

			if(StringUtils.isValidString(ls_nir))
			{
				Solicitud ls_solicitud = new Solicitud();
				ls_solicitud.setNir(ls_nir);
				ls_solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_solicitud);

				if(ls_solicitud != null)
					ls_idSolicitud = ls_solicitud.getIdSolicitud();
				else
					ls_idSolicitud = ls_nir;
			}

			ap_param.setIdSolicitud(ls_idSolicitud);
			ap_param.setIdUsuarioCreacion(as_userId);

			lca_data = lthd_tad.findAllData(ap_param, true);

			if(CollectionUtils.isValidCollection(lca_data))
			{
				lca_dataFinal         = new ArrayList<Aprobacion>();
				lca_dataFinalTemp     = new ArrayList<Aprobacion>();

				DatosAntSistemaDAO    ldasd_datosAntDAO;
				TurnoDAO              ltd_turnoDAO;
				AccPredioRegistroDAO  laprd_predioRegDAO;
				SolicitudMatriculaDAO lsmd_solicitudMatrDAO;
				TurnoHistoriaDAO      lthd_turnoHistoriaDAO;

				ldasd_datosAntDAO     = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				ltd_turnoDAO          = DaoCreator.getTurnoDAO(ldm_manager);
				laprd_predioRegDAO    = DaoCreator.getAccPredioRegistroDAO(ldm_manager);
				lsmd_solicitudMatrDAO = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
				lthd_turnoHistoriaDAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(Aprobacion la_oa : lca_data)
				{
					if(la_oa != null)
					{
						String                 ls_idTurno;
						Collection<Aprobacion> lca_turnosDerivados;

						ls_idTurno              = la_oa.getIdTurno();
						lca_turnosDerivados     = lthd_tad.findTurnosDerivados(ls_idTurno, false, false);

						if(CollectionUtils.isValidCollection(lca_turnosDerivados))
						{
							StringBuilder lsb_tmp;
							lsb_tmp = new StringBuilder();

							for(Aprobacion la_tmp : lca_turnosDerivados)
								lsb_tmp = lsb_tmp.append(la_tmp.getTurnosAsociados() + ", ");

							if(StringUtils.isValidString(lsb_tmp.toString()))
								la_oa.setTurnosAsociados(lsb_tmp.toString());
						}

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = new TurnoHistoria();

							lth_turnoHistoria.setIdTurno(ls_idTurno);
							lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_MAYOR_VALOR));
							lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

							lth_turnoHistoria = lthd_turnoHistoriaDAO.findByIdTurnoEtapa(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								String ls_generarRelacion;
								ls_generarRelacion = lth_turnoHistoria.getGenerarRelacion();

								if(
								    StringUtils.isValidString(ls_generarRelacion)
									    && ls_generarRelacion.equals(EstadoCommon.SI)
								)
								{
									la_oa.setBloquearCheck(true);
									la_oa.setGenerarRelacion(true);
									la_oa.setObservacionesAprobador(lth_turnoHistoria.getObservaciones());
								}
							}

							la_oa.setTurnoHistoria(lth_turnoHistoria);
						}

						{
							Turno lt_turno;

							lt_turno = new Turno();

							lt_turno.setIdTurno(ls_idTurno);

							lt_turno = ltd_turnoDAO.findById(lt_turno);

							if(lt_turno != null)
							{
								la_oa.setTurno(lt_turno);

								DatosAntSistema             ldas_datos;
								Collection<DatosAntSistema> lcdas_predios;

								ldas_datos = new DatosAntSistema();

								ldas_datos.setIdSolicitud(ls_idTurno);
								ldas_datos.setIdCirculo(lt_turno.getIdCirculo());

								lcdas_predios = ldasd_datosAntDAO.findByIdSolicitudCirculo(ldas_datos);

								if(CollectionUtils.isValidCollection(lcdas_predios))
								{
									StringBuilder             lsb_matriculas;
									StringBuilder             lsb_certificadosAsociados;
									String                    ls_coma;
									String                    ls_guion;
									Iterator<DatosAntSistema> lidas_iterador;

									lsb_matriculas                = new StringBuilder();
									lsb_certificadosAsociados     = new StringBuilder();
									ls_coma                       = ", ";
									ls_guion                      = "-";
									lidas_iterador                = lcdas_predios.iterator();

									while(lidas_iterador.hasNext())
									{
										DatosAntSistema ldas_temp;

										ldas_temp = lidas_iterador.next();

										if(ldas_temp != null)
										{
											String ls_accion;
											String ls_turnoCertificado;

											ls_accion               = ldas_temp.getAccion();
											ls_turnoCertificado     = ldas_temp.getIdTurnoCertificado();

											if(StringUtils.isValidString(ls_turnoCertificado))
											{
												lsb_certificadosAsociados.append(ls_turnoCertificado);

												if(lidas_iterador.hasNext())
													lsb_certificadosAsociados.append(ls_coma);
											}

											if(StringUtils.isValidString(ls_accion))
											{
												String ls_idDatosAntSis;

												ls_idDatosAntSis = ldas_temp.getIdDatosAntSistema();

												if(
												    ls_accion.equalsIgnoreCase(AccionAntSistemaCommon.CREAR_MATRICULAS)
													    || ls_accion.equalsIgnoreCase(
													        AccionAntSistemaCommon.CREAR_MATRICULA_E_INFORME
													    )
												)
												{
													AccPredioRegistro             lapr_predioReg;
													Collection<AccPredioRegistro> lcapr_matriculas;

													lapr_predioReg = new AccPredioRegistro();

													lapr_predioReg.setIdDatosAntSistema(ls_idDatosAntSis);
													lapr_predioReg.setIdTurno(ls_idTurno);

													lcapr_matriculas = laprd_predioRegDAO.findByTurnoAntSistema(
														    lapr_predioReg, false
														);

													if(CollectionUtils.isValidCollection(lcapr_matriculas))
													{
														Iterator<AccPredioRegistro> liapr_iterador;

														liapr_iterador = lcapr_matriculas.iterator();

														if(StringUtils.isValidString(lsb_matriculas.toString()))
															lsb_matriculas.append(ls_coma);

														while(liapr_iterador.hasNext())
														{
															AccPredioRegistro lapr_temp;

															lapr_temp = liapr_iterador.next();

															if(lapr_temp != null)
															{
																lsb_matriculas.append(lapr_temp.getIdCirculo());
																lsb_matriculas.append(ls_guion);
																lsb_matriculas.append(lapr_temp.getIdMatricula());

																if(liapr_iterador.hasNext())
																	lsb_matriculas.append(ls_coma);
															}
														}
													}
												}
												else if(
												    ls_accion.equalsIgnoreCase(
													        AccionAntSistemaCommon.ASOCIAR_MATRICULAS
													    )
													    || ls_accion.equalsIgnoreCase(
													        AccionAntSistemaCommon.ASOCIAR_MATRICULA_E_INFORME
													    )
												)
												{
													Collection<SolicitudMatricula> lcsm_matriculas;

													lcsm_matriculas = lsmd_solicitudMatrDAO
															.findMatriculasByturnoAntSistema(
															    ls_idTurno, ls_idDatosAntSis
															);

													if(CollectionUtils.isValidCollection(lcsm_matriculas))
													{
														Iterator<SolicitudMatricula> lism_iterador;

														lism_iterador = lcsm_matriculas.iterator();

														if(StringUtils.isValidString(lsb_matriculas.toString()))
															lsb_matriculas.append(ls_coma);

														while(lism_iterador.hasNext())
														{
															SolicitudMatricula lsm_temp;

															lsm_temp = lism_iterador.next();

															if(lsm_temp != null)
															{
																lsb_matriculas.append(lsm_temp.getIdCirculo());
																lsb_matriculas.append(ls_guion);
																lsb_matriculas.append(lsm_temp.getIdMatricula());

																if(lism_iterador.hasNext())
																	lsb_matriculas.append(ls_coma);
															}
														}
													}
												}
											}
										}
									}

									la_oa.setMatriculasRelacionadas(lsb_matriculas.toString());
									la_oa.setTurnosAsociados(lsb_certificadosAsociados.toString());
								}
							}
						}

						{
							Collection<Aprobacion> lca_idImages;
							lca_idImages = lthd_tad.findIdsDocs(ls_idTurno);

							if(CollectionUtils.isValidCollection(lca_idImages))
							{
								la_oa.setDataZip(lca_idImages);
								la_oa.setImageValida(true);
							}
							else
								la_oa.setImageValida(false);
						}

						TurnoHistoria lth_temp;
						lth_temp = new TurnoHistoria();

						lth_temp.setIdTurnoHistoria(la_oa.getIdTurnoHistoria());

						lth_temp = lthd_tad.findById(lth_temp);

						if(lth_temp != null)
						{
							TurnoHistoria lth_anterior;
							lth_anterior = new TurnoHistoria();

							lth_anterior.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_temp.getIdAnterior()));

							lth_anterior = lthd_tad.findById(lth_anterior);

							if(lth_anterior != null)
								la_oa.setObservacionesProcesoAnterior(lth_anterior.getObservaciones());
						}

						lca_dataFinal.add(la_oa);
					}
				}
			}

			if(lca_dataFinal != null)
			{
				for(Aprobacion lotc_tmp : lca_dataFinal)
				{
					if(lotc_tmp != null)
					{
						String        ls_idTurnoHistoria;
						TurnoHistoria lth_turnoHistoria;

						ls_idTurnoHistoria     = StringUtils.getString(lotc_tmp.getIdTurnoHistoria());
						lth_turnoHistoria      = new TurnoHistoria();

						if(StringUtils.isValidString(ls_idTurnoHistoria))
						{
							lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));
							lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

							if(lth_turnoHistoria != null)
							{
								TurnoHistoria lth_tmp;
								lth_tmp = new TurnoHistoria();

								lth_tmp.setIdTurnoHistoria(
								    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
								);

								lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_tmp);

								if(lth_tmp != null)
								{
									if(NumericUtils.getLong(lth_tmp.getIdEtapa()) == EtapaCommon.ID_ETAPA_REANOTACION)
									{
										TurnoHistoria lth_temp;
										lth_temp = new TurnoHistoria();

										lth_temp.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lth_tmp.getIdAnterior())
										);

										lth_temp = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_temp);
									}
								}

								{
									FechaVenceTerminosUI lfvtui_fechaVencimiento;
									long                 ll_tiempoVencimiento;

									lfvtui_fechaVencimiento = new FechaVenceTerminosUI();

									lfvtui_fechaVencimiento.setFechaInicial(lth_turnoHistoria.getFechaCreacion());
									lfvtui_fechaVencimiento.setFechaFinal(lth_turnoHistoria.getFechaVencimiento());
									lfvtui_fechaVencimiento.setTipoCalendario(EstadoCommon.H);
									lfvtui_fechaVencimiento.setIdCirculo(lth_turnoHistoria.getIdCirculoUsuario());

									ll_tiempoVencimiento = DaoCreator.getUtilDAO(ldm_manager)
											                             .funcionCalcularDiasFechas(
											    lfvtui_fechaVencimiento
											);

									lotc_tmp.setFechaCreacion(lth_turnoHistoria.getFechaCreacion());
									lotc_tmp.setFechaVencimiento(lth_turnoHistoria.getFechaVencimiento());

									lotc_tmp.setTiempoVencimiento(ll_tiempoVencimiento);

									if(ll_tiempoVencimiento <= 0)
										lotc_tmp.setRojoTiempoVencimiento(true);
								}
							}
						}

						lca_dataFinalTemp.add(lotc_tmp);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAllData", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_dataFinal;
	}

	/**
	 * Carga la información de la bandeja de entrada de aprobación antiguo sistema
	 * dependiendo del usuario que tenga sesión iniciada.
	 *
	 * @param as_userId Id de usuario que ejecuta el proceso
	 * @param as_idTurno Numero de turno que puede ser o no especificado por pantalla
	 * para ser añadido a los filtros de la consulta
	 * @param as_nir Numero de NIR que puede ser o no especificado por pantalla
	 * para ser añadido a los filtros de la consulta
	 * @return Colección de cantidad de tramites que tiene disponibles el usuario que
	 * ejecuta el proceso
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

		lc_data         = null;
		lc_result       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lc_result     = new ArrayList<TramiteCantidad>();
			lc_data       = findAprobadorAntiguoSistema(as_userId);

			if(CollectionUtils.isValidCollection(lc_data))
			{
				for(Etapa le_etapaActual : lc_data)
				{
					LinkedList<LinkedHashMap<String, Object>> ll_data;
					LinkedHashMap<Integer, Object>            lhmp_criterios;
					String                                    ls_idSolicitud;

					lhmp_criterios = new LinkedHashMap<Integer, Object>();

					lhmp_criterios.put(
					    NumericUtils.getInteger(1), NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa())
					);

					lhmp_criterios.put(NumericUtils.getInteger(2), as_userId);

					ls_idSolicitud = null;

					if(StringUtils.isValidString(as_nir))
					{
						Solicitud ls_Solicitud = new Solicitud();
						ls_Solicitud.setNir(as_nir);
						ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

						if(ls_Solicitud != null)
							ls_idSolicitud = ls_Solicitud.getIdSolicitud();
						else
							ls_idSolicitud = as_nir;
					}

					TurnoHistoriaDAO lthDAO_th;
					lthDAO_th     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					ll_data = lthDAO_th.getCantidadPorBandeja(as_idTurno, ls_idSolicitud, lhmp_criterios);

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

								ls_cantidad = llhm_actual.get(ConstanteCommon.LABEL_CANTIDAD_BANDEJAS_ENTRADA).toString();

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
					else
						lc_result.add(
						    new TramiteCantidad(
						        NumericUtils.getInteger(0), NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa()),
						        le_etapaActual.getNombre()
						    )
						);
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
	 * Crea un archivo ZIP con todos los documentos activos que se encuentren en la
	 * tabla documentos salida de la base de datos con estado activo para el turno
	 * solicitado.
	 *
	 * @param at_param Contenedor de los parámetros como el turno, para la búsqueda
	 * de documentos
	 * @param as_userId Id de usuario que está ejecutando el proceso
	 * @param as_remoteIp Dirección IP del cliente que está ejecutando el proceso
	 * @return Objeto de aprobación que contiene el archivo ZIP con el resultado de
	 * la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Aprobacion
	 */
	public synchronized Aprobacion generateZip(Aprobacion at_param, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Aprobacion loa_data;

		ImagenesDAO              loid_id;
		Collection<ZipEntryUtil> lczeu_zip;

		String ls_nombreFile;
		int    li_i;

		ldm_manager     = DaoManagerFactory.getDAOManager();

		loid_id      = DaoCreator.getImagenesDAO(ldm_manager);
		li_i         = 1;
		loa_data     = new Aprobacion();

		lczeu_zip         = null;
		ls_nombreFile     = "PROCESO_ANTIGUO_SISTEMA";

		try
		{
			if(at_param != null)
			{
				Collection<Aprobacion> lca_tmpDataImages;
				lca_tmpDataImages = at_param.getDataZip();

				if(CollectionUtils.isValidCollection(lca_tmpDataImages))
				{
					lczeu_zip = new ArrayList<ZipEntryUtil>();

					for(Aprobacion loa_tmp : lca_tmpDataImages)
					{
						if(loa_tmp != null)
						{
							Long ll_idImagen;
							ll_idImagen = loa_tmp.getIdImage();

							if(NumericUtils.isValidLong(ll_idImagen))
							{
								Imagenes li_imagen;
								li_imagen = new Imagenes();

								li_imagen.setIdImagen(NumericUtils.getInt(ll_idImagen));

								li_imagen = loid_id.findById(li_imagen);

								if(li_imagen != null)
								{
									byte[] lba_imagenBlob;

									lba_imagenBlob = li_imagen.getImagenBlob();

									if(lba_imagenBlob != null)
									{
										ZipEntryUtil lzeu_pdf;

										li_i     = li_i + 1;

										lzeu_pdf = new ZipEntryUtil(
											    ls_nombreFile + li_i + IdentificadoresCommon.PUNTO
											    + li_imagen.getTipoArchivo(), new ByteArrayInputStream(lba_imagenBlob)
											);
										lczeu_zip.add(lzeu_pdf);
									}
								}
							}
						}
					}

					if(CollectionUtils.isValidCollection(lczeu_zip))
						loa_data.setZipFinal(ZipUtils.generateZip(lczeu_zip));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generateZip", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return loa_data;
	}

	/**
	 * Define la etapa siguiente de un proceso según la etapa de la que provenga y
	 * el motivo que tenga el turno historia, invoca el procedimiento proc crea etapa
	 * y termina el turno historia actual de las etapas 110.
	 *
	 * @param aca_param Contenedor de las aprobaciones las cuales se van a llevar a otra
	 * etapa
	 * @param as_usuario Id del usuario que está ejecutando el proceso
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta el proceso
	 * @return Objeto con las aprobaciones que fueron procesadas y las que no, para que
	 * sean tratadas en pantalla después del proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Aprobacion
	 */
	public synchronized Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_param, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Aprobacion la_aprobacion;

		ldm_manager       = DaoManagerFactory.getDAOManager();
		la_aprobacion     = null;

		try
		{
			if(!CollectionUtils.isValidCollection(aca_param))
				throw new B2BException(ErrorKeys.ERROR_REALIZAR_REVISION_TURNO);

			if(CollectionUtils.isValidCollection(aca_param))
			{
				TurnoHistoriaDAO          lthd_DAO;
				TurnoDAO                  ltd_DAO;
				MotivoTramiteDAO          lmtd_DAO;
				ProcedimientosDAO         lpd_DAO;
				DocumentosSalidaDAO       lds_DAO;
				DatosAntSistemaDAO        ldas_DAO;
				SolicitudMatriculaDAO     lsmd_DAO;
				SolicitudMatriculaActoDAO lsmad_DAO;
				FirmaMasivaDAO            lfmd_DAO;
				TipoDocumentalDAO         ltdd_DAO;
				ConstantesDAO             lcd_DAO;

				int li_contador;

				lthd_DAO      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ltd_DAO       = DaoCreator.getTurnoDAO(ldm_manager);
				lmtd_DAO      = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				lpd_DAO       = DaoCreator.getProcedimientosDAO(ldm_manager);
				lds_DAO       = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				ldas_DAO      = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
				lsmd_DAO      = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
				lsmad_DAO     = DaoCreator.getSolicitudMatriculaActoDAO(ldm_manager);
				lfmd_DAO      = DaoCreator.getFirmaMasivaDAO(ldm_manager);
				ltdd_DAO      = DaoCreator.getTipoDocumentalDAO(ldm_manager);
				lcd_DAO       = DaoCreator.getConstantesDAO(ldm_manager);

				li_contador = 1;

				if(!StringUtils.isValidString(as_usuario))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_USUARIO);

				for(Aprobacion la_iterador : aca_param)
				{
					if(la_iterador != null)
					{
						String ls_firmar;

						ls_firmar = la_iterador.getFirmar();

						if(StringUtils.isValidString(ls_firmar))
						{
							Long          ll_turnoHistoria;
							TurnoHistoria lth_turnoHistoria;
							BigDecimal    lbd_etapa;
							Long          ll_motivo;
							String        ls_observaciones;
							boolean       lb_firmar;

							ll_turnoHistoria      = la_iterador.getIdTurnoHistoria();
							lth_turnoHistoria     = new TurnoHistoria();
							ls_observaciones      = null;
							lb_firmar             = false;

							if(!NumericUtils.isValidLong(ll_turnoHistoria))
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = "" + li_contador;

								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA_LINEA, loa_messageArgs);
							}

							lth_turnoHistoria.setIdTurnoHistoria(ll_turnoHistoria);

							lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

							if(lth_turnoHistoria == null)
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

							TurnoHistoria lth_turnoAnterior;

							lth_turnoAnterior = new TurnoHistoria();

							Long   ll_turnoAnterior;
							String ls_idSolicitud;
							Turno  lt_turno;
							String ls_idCirculo;

							ls_idSolicitud     = lth_turnoHistoria.getIdSolicitud();
							lt_turno           = new Turno();
							ls_idCirculo       = null;

							lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());
							lt_turno = ltd_DAO.findById(lt_turno);

							if(lt_turno != null)
								ls_idCirculo = lt_turno.getIdCirculo();

							ll_turnoAnterior = NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior());

							lth_turnoAnterior.setIdTurnoHistoria(ll_turnoAnterior);

							lth_turnoAnterior = lthd_DAO.findById(lth_turnoAnterior);

							if(lth_turnoAnterior == null)
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

							lbd_etapa = lth_turnoAnterior.getIdEtapa();

							if(!NumericUtils.isValidBigDecimal(lbd_etapa))
								throw new B2BException(ErrorKeys.ERROR_SIN_ID_ETAPA);

							ll_motivo = lth_turnoAnterior.getIdMotivo();

							if(!NumericUtils.isValidLong(ll_motivo))
								throw new B2BException(ErrorKeys.ERROR_MOTIVO_INVALIDO);

							if(ls_firmar.equalsIgnoreCase(IdentificadoresCommon.FIRMAR))
							{
								boolean lb_entregarDocumentos;
								String  ls_idProceso;

								lb_entregarDocumentos     = true;
								ls_idProceso              = StringUtils.getStringNotNull(
									    lth_turnoHistoria.getIdProceso()
									);
								ls_observaciones          = la_iterador.getObservacionesAprobador();

								if(!StringUtils.isValidString(ls_observaciones))
								{
									Object[] loa_messageArgs = new String[1];
									loa_messageArgs[0] = la_iterador.getIdTurno();

									throw new B2BException(
									    ErrorKeys.ERROR_SIN_OBSERVACIONES_APROBADOR, loa_messageArgs
									);
								}

								if(lbd_etapa.equals(NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA)))
								{
									if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA
										        )
										    )
									)
									{
										ll_motivo     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.ENVIAR_A_CALIFICACION
											);
										lb_firmar     = true;
									}
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA
										        )
										    )
									)
										ll_motivo = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO
											);

									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA
										        )
										    )
									)
										ll_motivo = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.APROBADO_RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA
											);
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
										        )
										    )
										    || ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
										        )
										    )
										    || ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA
										        )
										    )
									)
									{
										ll_motivo     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.AUTORIZA_FIRMA_LIBRO_AS
											);
										lb_firmar     = true;
									}
								}
								else if(
								    lbd_etapa.equals(
									        NumericUtils.getBigDecimal(
									            EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA
									        )
									    )
									    || lbd_etapa.equals(
									        NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_APROBACION)
									    )
								)
								{
									if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
										        )
										    )
										    || ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
										        )
										    )
										    || ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
										        )
										    )
									)
									{
										ll_motivo     = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.AUTORIZA_FIRMA_LIBRO_AS
											);
										lb_firmar     = true;
									}
									else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
									{
										if(
										    ll_motivo.equals(
											        NumericUtils.getLongWrapper(
											            MotivoTramiteCommon.CREAR_MATRICULA_PARA_CERTIFICADOS
											        )
											    )
										)
											ll_motivo = NumericUtils.getLongWrapper(
												    MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS
												);
										else if(
										    ll_motivo.equals(
											        NumericUtils.getLongWrapper(
											            MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CERTIFICADOS
											        )
											    )
										)
											ll_motivo = NumericUtils.getLongWrapper(
												    MotivoTramiteCommon.ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS
												);
									}
									else if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_3))
									{
										if(
										    ll_motivo.equals(
											        NumericUtils.getLongWrapper(
											            MotivoTramiteCommon.CREAR_MATRICULA_PARA_CORRECCIONES
											        )
											    )
										)
											ll_motivo = NumericUtils.getLongWrapper(
												    MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES
												);
										else if(
										    ll_motivo.equals(
											        NumericUtils.getLongWrapper(
											            MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CORRECCIONES
											        )
											    )
										)
											ll_motivo = NumericUtils.getLongWrapper(
												    MotivoTramiteCommon.ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES
												);
									}
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_APROBAR_SOLICITUD_COMPLEMENTACION
										        )
										    )
										    || ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_APROBADOR_ANTIGUO_SISTEMA
										        )
										    )
									)
										ll_motivo = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.SUSPENSION_PROCESO_REGISTRO_TERMINADO
											);
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_AL_ANALISTA
										        )
										    )
									)
										ll_motivo = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO
											);
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA
										        )
										    )
									)
										ll_motivo = NumericUtils.getLongWrapper(
											    MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_AL_CALIFICADOR
											);

									Constantes       lc_constante;
									DocumentosSalida lds_docSalida;
									String           ls_idConstante;

									ls_idConstante     = ConstanteCommon.TIPO_DOCUMENTAL_INFORME_BUSQUEDA;
									lds_docSalida      = new DocumentosSalida();
									lc_constante       = new Constantes();

									lc_constante.setIdConstante(ls_idConstante);

									lc_constante = lcd_DAO.findById(ls_idConstante);

									if(lc_constante == null)
									{
										Object[] loa_messageArgs;

										loa_messageArgs        = new String[1];
										loa_messageArgs[0]     = ls_idConstante;

										throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
									}

									TipoDocumental ltd_tipoDoc;

									ltd_tipoDoc = new TipoDocumental();

									ltd_tipoDoc.setIdTipoDocumental(lc_constante.getCaracter());

									ltd_tipoDoc = ltdd_DAO.findById(ltd_tipoDoc);

									if(ltd_tipoDoc == null)
										throw new B2BException(ErrorKeys.TIPO_DOCUMENTAL_INVALIDO);

									lds_docSalida.setIdTurnoHistoria(
									    NumericUtils.getInteger(lth_turnoAnterior.getIdTurnoHistoria())
									);
									lds_docSalida.setTipo(ltd_tipoDoc.getNombre());

									Collection<DocumentosSalida> lcds_documentos;

									lcds_documentos = lds_DAO.findByIdTurnoHistoriaTipo(lds_docSalida);

									if(CollectionUtils.isValidCollection(lcds_documentos))
									{
										for(DocumentosSalida lds_doc : lcds_documentos)
										{
											if(lds_doc != null)
											{
												lds_doc.setEstado(EstadoCommon.ENTREGA);
												lds_doc.setIdUsuarioModificacion(as_usuario);
												lds_doc.setIpModificacion(as_remoteIp);

												lds_DAO.insertOrUpdate(lds_doc, false);
											}
										}
									}
								}
								else if(
								    lbd_etapa.equals(
									        NumericUtils.getBigDecimal(
									            EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S
									        )
									    )
								)
								{
									boolean lb_generarNegracionFirma;
									String  ls_idTurno;

									lb_generarNegracionFirma     = false;
									ls_idTurno                   = lt_turno.getIdTurno();

									if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.GENERAR_RESOLUCION_AUTORIZACION_FIRMA_LIBROS_AS
										        )
										    )
									)
									{
										boolean       lb_motivoCalculado;
										TurnoHistoria lth_turnoHistoria100;

										lb_motivoCalculado       = false;
										lth_turnoHistoria100     = lthd_DAO.findByIdTurnoEtapa(
											    ls_idTurno, EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA
											);

										if(lth_turnoHistoria100 != null)
										{
											String ls_idProcesoEtapa;

											ls_idProcesoEtapa = lth_turnoHistoria100.getIdProceso();

											if(StringUtils.isValidString(ls_idProcesoEtapa))
											{
												long ll_idMotivoEtapa;

												ll_idMotivoEtapa = NumericUtils.getLong(
													    lth_turnoHistoria100.getIdMotivo()
													);

												if(ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
												{
													if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
													)
													{
														ll_motivo              = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.ENVIAR_A_CALIFICACION
															);
														lb_firmar              = true;
														lb_motivoCalculado     = true;
													}
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
													)
													{
														ll_motivo              = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO
															);
														lb_motivoCalculado     = true;
													}
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA
													)
													{
														ll_motivo              = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.APROBADO_INFORME_DE_BUSQUEDA_PARA_CALIFICACION
															);
														lb_firmar              = true;
														lb_motivoCalculado     = true;
													}
												}
											}
										}

										TurnoHistoria lth_turnoHistoria101;

										lth_turnoHistoria101 = lthd_DAO.findByIdTurnoEtapa(
											    ls_idTurno, EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA
											);

										if((lth_turnoHistoria101 != null) && !lb_motivoCalculado)
										{
											String ls_idProcesoEtapa;

											ls_idProcesoEtapa = lth_turnoHistoria101.getIdProceso();

											if(StringUtils.isValidString(ls_idProcesoEtapa))
											{
												long ll_idMotivoEtapa;

												ll_idMotivoEtapa = NumericUtils.getLong(
													    lth_turnoHistoria101.getIdMotivo()
													);

												if(ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
												{
													if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
													)
													{
														lb_firmar     = true;
														ll_motivo     = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS
															);
													}
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS
															);
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
													)
													{
													}
												}
												else if(ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
												{
													if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES
															);
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.ASOCIACION_DE_MATRICULA_EXISTOSA_PARA_CORRECCIONES
															);
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.INFORME_DE_BUSQUEDA_CORRECCIONES_AS
															);
												}
												else if(ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
												{
													if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO
															);
													else if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
													)
														ll_motivo = NumericUtils.getLongWrapper(
															    MotivoTramiteCommon.APROBADO_INFORME_DE_BUSQUEDA_PARA_CALIFICACION
															);
												}
												else if(ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_37))
												{
													if(
													    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA
													)
													{
													}
												}
											}
										}
									}
									else if(
									    ll_motivo.equals(
										        NumericUtils.getLongWrapper(
										            MotivoTramiteCommon.NEGACION_AUTORIZACION_FIRMA_LIBROS_AS
										        )
										    )
									)
									{
										boolean       lb_motivoSeleccionado;
										TurnoHistoria lth_turnoHistoria100;

										lb_generarNegracionFirma     = true;
										lb_motivoSeleccionado        = false;
										lb_entregarDocumentos        = false;
										lth_turnoHistoria100         = lthd_DAO.findByIdTurnoEtapa(
											    ls_idTurno, EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA
											);

										if(lth_turnoHistoria100 != null)
										{
											long ll_idMotivoEtapa;

											ll_idMotivoEtapa = NumericUtils.getLong(lth_turnoHistoria100.getIdMotivo());

											if(
											    ll_idMotivoEtapa == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
											)
											{
												inactivarPredioRegistro(
												    ls_idSolicitud, ldm_manager, as_usuario, as_remoteIp
												);

												lb_motivoSeleccionado = true;
											}
											else if(
											    ll_idMotivoEtapa == MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA
											)
											{
												lb_motivoSeleccionado = true;

												inactivarSolicitudMatricula(
												    ls_idSolicitud, ls_idCirculo, ldm_manager, as_usuario, as_remoteIp
												);
											}
											else if(
											    ll_idMotivoEtapa == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA
											)
												lb_motivoSeleccionado = true;
										}

										TurnoHistoria lth_turnoHistoria101;

										lth_turnoHistoria101 = lthd_DAO.findByIdTurnoEtapa(
											    ls_idTurno, EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA
											);

										if((lth_turnoHistoria101 != null) && !lb_motivoSeleccionado)
										{
											String ls_idProcesoEtapa;

											ls_idProcesoEtapa = lth_turnoHistoria101.getIdProceso();

											if(StringUtils.isValidString(ls_idProcesoEtapa))
											{
												if(
												    ls_idProcesoEtapa.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
													    || ls_idProcesoEtapa.equalsIgnoreCase(
													        ProcesoCommon.ID_PROCESO_3
													    )
													    || ls_idProcesoEtapa.equalsIgnoreCase(
													        ProcesoCommon.ID_PROCESO_6
													    )
												)
													ll_motivo = NumericUtils.getLongWrapper(
														    MotivoTramiteCommon.DEVOLVER_AL_BUSCADOR_DE_ANTIGUO_SISTEMA
														);

												inactivarPredioRegistro(
												    ls_idSolicitud, ldm_manager, as_usuario, as_remoteIp
												);

												inactivarSolicitudMatricula(
												    ls_idSolicitud, ls_idCirculo, ldm_manager, as_usuario, as_remoteIp
												);
											}
										}
									}

									if(lb_entregarDocumentos)
									{
										Constantes       lc_constante;
										DocumentosSalida lds_docSalida;
										String           ls_idConstante;

										ls_idConstante     = ConstanteCommon.TIPO_DOCUMENTAL_INFORME_BUSQUEDA;
										lds_docSalida      = new DocumentosSalida();
										lc_constante       = new Constantes();

										lc_constante.setIdConstante(ls_idConstante);

										lc_constante = lcd_DAO.findById(ls_idConstante);

										if(lc_constante == null)
										{
											Object[] loa_messageArgs;

											loa_messageArgs        = new String[1];
											loa_messageArgs[0]     = ls_idConstante;

											throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
										}

										TipoDocumental ltd_tipoDoc;

										ltd_tipoDoc = new TipoDocumental();

										ltd_tipoDoc.setIdTipoDocumental(lc_constante.getCaracter());

										ltd_tipoDoc = ltdd_DAO.findById(ltd_tipoDoc);

										if(ltd_tipoDoc == null)
											throw new B2BException(ErrorKeys.TIPO_DOCUMENTAL_INVALIDO);

										lds_docSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(lth_turnoAnterior.getIdTurnoHistoria())
										);
										lds_docSalida.setTipo(ltd_tipoDoc.getNombre());

										Collection<DocumentosSalida> lcds_documentos;

										lcds_documentos = lds_DAO.findByIdTurnoHistoriaTipo(lds_docSalida);

										if(CollectionUtils.isValidCollection(lcds_documentos))
										{
											for(DocumentosSalida lds_doc : lcds_documentos)
											{
												if(lds_doc != null)
												{
													lds_doc.setEstado(EstadoCommon.ENTREGA);
													lds_doc.setIdUsuarioModificacion(as_usuario);
													lds_doc.setIpModificacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_doc, false);
												}
											}
										}
									}
									else
									{
										Collection<DocumentosSalida> lcds_documentos;

										lcds_documentos = lds_DAO.findByIdTurnoEstadoA(
											    lth_turnoHistoria.getIdTurno(), true, false
											);

										if(CollectionUtils.isValidCollection(lcds_documentos))
										{
											for(DocumentosSalida lds_doc : lcds_documentos)
											{
												if(lds_doc != null)
												{
													lds_doc.setEstado(EstadoCommon.INACTIVO);
													lds_doc.setIdUsuarioModificacion(as_usuario);
													lds_doc.setIpModificacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_doc, false);
												}
											}
										}
									}

									if(lb_generarNegracionFirma)
										getAntiguoSistemaBusiness()
											    .generarPdfAutorizacionFirma(
											    lth_turnoAnterior, true, true, as_usuario, as_remoteIp, as_remoteIp,
											    ldm_manager
											);
								}

								if(NumericUtils.getLong(lbd_etapa) != EtapaCommon.ANALISIS_CREACION_MATRICULA_OFICIO)
									lbd_etapa = NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_MAYOR_VALOR);
								else
									lb_firmar = true;
							}
							else if(ls_firmar.equalsIgnoreCase(IdentificadoresCommon.ANALISTA))
							{
								boolean lb_etapa101;
								boolean lb_etapa114;

								lb_etapa101     = false;
								lb_etapa114     = false;

								{
									BigDecimal lbd_idTHAnterior;

									lbd_idTHAnterior = lth_turnoHistoria.getIdAnterior();

									if(NumericUtils.isValidBigDecimal(lbd_idTHAnterior))
									{
										TurnoHistoria lth_tHAnterior;

										lth_tHAnterior = new TurnoHistoria();

										lth_tHAnterior.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lbd_idTHAnterior)
										);

										lth_tHAnterior = lthd_DAO.findById(lth_tHAnterior);

										if(lth_tHAnterior != null)
										{
											BigDecimal lbd_idEtapa;

											lbd_idEtapa = lth_tHAnterior.getIdEtapa();

											if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
											{
												lb_etapa101     = lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA;
												lb_etapa114     = lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S;
											}
										}
									}
								}

								lbd_etapa     = NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_MAYOR_VALOR);
								ll_motivo     = NumericUtils.getLongWrapper(
									    lb_etapa101 ? MotivoTramiteCommon.DEVOLVER_AL_BUSCADOR_DE_ANTIGUO_SISTEMA
									                : (lb_etapa114
									    ? MotivoTramiteCommon.DEVOLVER_A_AUTORIZAR_FIRMA_LIBRO_AS
									    : MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_AL_ANALISTA)
									);

								ls_observaciones = la_iterador.getObservaciones();

								if(!StringUtils.isValidString(ls_observaciones))
								{
									Object[] loa_messageArgs = new String[1];
									loa_messageArgs[0] = la_iterador.getIdTurno();

									throw new B2BException(
									    ErrorKeys.ERROR_SIN_OBSERVACIONES_APROBADOR, loa_messageArgs
									);
								}

								{
									DocumentosSalida lds_docSalida;
									lds_docSalida = new DocumentosSalida();

									lds_docSalida.setIdTurnoHistoria(
									    NumericUtils.getInteger(lth_turnoAnterior.getIdTurnoHistoria())
									);

									Collection<DocumentosSalida> lcds_documentos;
									lcds_documentos = lds_DAO.findByIdTurnoHistoria(lds_docSalida);

									if(CollectionUtils.isValidCollection(lcds_documentos))
									{
										for(DocumentosSalida lds_doc : lcds_documentos)
										{
											if(lds_doc != null)
											{
												lds_doc.setEstado(EstadoCommon.I);
												lds_doc.setIdUsuarioModificacion(as_usuario);
												lds_doc.setIpModificacion(as_remoteIp);

												lds_DAO.insertOrUpdate(lds_doc, false);
											}
										}
									}
								}

								{
									DatosAntSistema ldas_dataAntSistema;
									ldas_dataAntSistema = new DatosAntSistema();

									ldas_dataAntSistema.setIdSolicitud(ls_idSolicitud);

									Collection<DatosAntSistema> lcdas_dataAntSistema;
									lcdas_dataAntSistema = ldas_DAO.findByIdSolicitud(ldas_dataAntSistema);

									if(CollectionUtils.isValidCollection(lcdas_dataAntSistema))
									{
										for(DatosAntSistema ldas_temp : lcdas_dataAntSistema)
										{
											if(ldas_temp != null)
											{
												ldas_temp.setRevisadoAntSistema(EstadoCommon.S);
												ldas_temp.setIdUsuarioModificacion(as_usuario);
												ldas_temp.setIpModificacion(as_remoteIp);

												ldas_DAO.updateRevisionAntSistema(ldas_temp);
											}
										}
									}
								}

								if(!lb_etapa114)
								{
									SolicitudMatricula lsm_solicitudMatricula;
									lsm_solicitudMatricula = new SolicitudMatricula();

									lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);
									lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);

									Collection<SolicitudMatricula> lcsm_dataSolicitudMatricula;
									lcsm_dataSolicitudMatricula = lsmd_DAO.findByIdSolicitudCirculo(
										    lsm_solicitudMatricula
										);

									if(CollectionUtils.isValidCollection(lcsm_dataSolicitudMatricula))
									{
										for(SolicitudMatricula ldas_temp : lcsm_dataSolicitudMatricula)
										{
											if(ldas_temp != null)
											{
												String ls_idDatosAntSistema;
												ls_idDatosAntSistema = ldas_temp.getIdDatosAntSistema();

												if(StringUtils.isValidString(ls_idDatosAntSistema))
												{
													ldas_temp.setEstado(EstadoCommon.I);
													ldas_temp.setIdUsuarioModificacion(as_usuario);
													ldas_temp.setIpModificacion(as_remoteIp);

													lsmd_DAO.insertOrUpdate(ldas_temp, false);

													long ll_idMatricula;

													ll_idMatricula = ldas_temp.getIdMatricula();

													SolicitudMatriculaActo lsma_solicitudMatriculaActo;
													lsma_solicitudMatriculaActo = new SolicitudMatriculaActo();

													lsma_solicitudMatriculaActo.setIdSolicitud(ls_idSolicitud);
													lsma_solicitudMatriculaActo.setIdCirculo(ls_idCirculo);
													lsma_solicitudMatriculaActo.setIdMatricula(ll_idMatricula);

													Collection<SolicitudMatriculaActo> lcdas_dataSolMatriculaActo;
													lcdas_dataSolMatriculaActo = lsmad_DAO.findByIdSolicitudCirculo(
														    lsma_solicitudMatriculaActo, false, true
														);

													if(CollectionUtils.isValidCollection(lcdas_dataSolMatriculaActo))
													{
														for(SolicitudMatriculaActo lsma_temp : lcdas_dataSolMatriculaActo)
														{
															if(lsma_temp != null)
															{
																lsma_temp.setEstado(EstadoCommon.I);
																lsma_temp.setIdUsuarioModificacion(as_usuario);
																lsma_temp.setIpModificacion(as_remoteIp);

																lsmad_DAO.insertOrUpdate(lsma_temp, false);
															}
														}
													}
												}
											}
										}
									}
								}
							}

							if((!NumericUtils.isValidBigDecimal(lbd_etapa)) || (!NumericUtils.isValidLong(ll_motivo)))
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

							MotivoTramite lmt_motivo;

							lmt_motivo = new MotivoTramite();

							lmt_motivo.setIdEtapaOrigen(NumericUtils.getLong(lbd_etapa));
							lmt_motivo.setIdMotivo(NumericUtils.getLong(ll_motivo));

							lmt_motivo = lmtd_DAO.findById(lmt_motivo);

							if((lmt_motivo != null))
							{
								if(lb_firmar)
								{
									FirmaMasiva lfm_firmaMasiva;

									lfm_firmaMasiva = new FirmaMasiva();

									lfm_firmaMasiva.setUsuario(as_usuario);
									lfm_firmaMasiva.setTipoFirma(1);
									lfm_firmaMasiva.setLlave1(
									    StringUtils.getString(NumericUtils.getLong(ll_turnoHistoria))
									);
									lfm_firmaMasiva.setIdUsuarioCreacion(as_usuario);

									lfmd_DAO.insertFirmaMasiva(lfm_firmaMasiva);

									lth_turnoHistoria.setMotivoNoTramite(lmt_motivo.getDescripcion());
								}

								{
									String ls_estadoActividad;

									ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

									if(
									    StringUtils.isValidString(ls_estadoActividad)
										    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
									)
										throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
								}

								lth_turnoHistoria.setEstadoActividad(
								    lb_firmar ? EstadoCommon.AUTOMATICA : EstadoCommon.TERMINADA
								);
								lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
								lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
								lth_turnoHistoria.setObservaciones(ls_observaciones);
								lth_turnoHistoria.setIndicadorDevolucion(EstadoCommon.N);
								lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
								lth_turnoHistoria.setIpModificacion(as_remoteIp);

								lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

								if(!lb_firmar)
									lpd_DAO.spCreateStage(lth_turnoHistoria);
							}
						}
					}

					li_contador++;
				}

				la_aprobacion = new Aprobacion();

				la_aprobacion.setDataZip(aca_param);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarAprobacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return la_aprobacion;
	}

	/**
	 * Consulta en la base de datos los casos asignados que tenga un usuario
	 * entre las etapas 110 y 114.
	 *
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @return Colección de etapas que cumplen con el criterio de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	private Collection<Etapa> findAprobadorAntiguoSistema(String as_userId)
	    throws B2BException
	{
		DAOManager        ldm_manager;
		Collection<Etapa> lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getEtapaDAO(ldm_manager).findAprobadorAntiguoSistema();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}
}
