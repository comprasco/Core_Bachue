package com.bachue.snr.prosnr01.business.consulta.digitador.antiguo.sistema;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;
import com.bachue.snr.prosnr01.common.constants.UnidadMedidaAreaCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccDetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccLinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.acc.AccPredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.acc.AccSalvedadAnotacionDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroCalificacionDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionCancelacionDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.LinderoPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.bng.SalvedadAnotacionDAO;
import com.bachue.snr.prosnr01.dao.bng.SalvedadesDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.AlertaNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CausalCorreccionDAO;
import com.bachue.snr.prosnr01.dao.pgn.LibroAntiguoSistemaDao;
import com.bachue.snr.prosnr01.dao.pgn.MedidaAreaDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoEntidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.antiguoSistema.UbicacionZonaRegistral;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistema;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadAnotacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.MatriculaSegregacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ConsultaSalvedad;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadAnotacion;
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio para la fase de antiguo sistema y digitador masivo,
 *
 * @author Julian Vaca.
 *
 */
public class DigitadorAntiguoSistemaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(DigitadorAntiguoSistemaBusiness.class);

	/**
	 * metodo encargado de consultar todas la matriculas Dummy para un turno especifico.
	 *
	 * @param AccPredioRegistro
	 * @param as_userId
	 * @return Collection<AccPredioRegistro>
	 * @throws B2BException
	 */
	public synchronized Collection<AccPredioRegistro> findAccMatriculasByTurnoAntSistema(
	    AccPredioRegistro AccPredioRegistro, String as_userId
	)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<AccPredioRegistro> lcapr_pr;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			lcapr_pr = DaoCreator.getAccPredioRegistroDAO(ldm_manager).findByTurnoAntSistema(AccPredioRegistro, true);

			if(CollectionUtils.isValidCollection(lcapr_pr))
			{
				String ls_turno;

				ls_turno = AccPredioRegistro.getIdTurno();

				for(AccPredioRegistro lapr_pr : lcapr_pr)
				{
					if(lapr_pr != null)
					{
						long   ll_idMatricula;
						Long   ll_idComplementacion;
						String ls_idCirculo;
						String ls_tipoUsoSuelo;
						String ls_estadoGrabacion;

						ll_idMatricula           = NumericUtils.getLong(lapr_pr.getIdMatricula());
						ll_idComplementacion     = lapr_pr.getIdComplementacion();
						ls_idCirculo             = lapr_pr.getIdCirculo();
						ls_tipoUsoSuelo          = lapr_pr.getIdTipoUsoSuelo();
						ls_estadoGrabacion       = EstadoCommon.ESTADO_DATOS_BASICOS;

						{
							EstadoPredio lsp_estadoPredio;

							lsp_estadoPredio = new EstadoPredio();

							lsp_estadoPredio.setIdEstadoPredio(lapr_pr.getIdEstadoPredio());

							lsp_estadoPredio = DaoCreator.getEstadoPredioDao(ldm_manager).findById(lsp_estadoPredio);

							if(lsp_estadoPredio != null)
								lapr_pr.setEstadoPredio(lsp_estadoPredio.getNombre());
						}

						if(NumericUtils.isValidLong(ll_idComplementacion))
							ls_estadoGrabacion = EstadoCommon.ESTADO_CABIDA_LINDEROS;

						if(StringUtils.isValidString(ls_tipoUsoSuelo))
							ls_estadoGrabacion = EstadoCommon.ESTADO_AREA_PREDIO;

						{
							Collection<DireccionPredioAcc> acddp_direccionPredio;
							DireccionPredioAcc             ldpa_direccionActual;

							ldpa_direccionActual = new DireccionPredioAcc();

							ldpa_direccionActual.setIdCirculo(ls_idCirculo);
							ldpa_direccionActual.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
							ldpa_direccionActual.setIdTurno(ls_turno);

							acddp_direccionPredio = DaoCreator.getDireccionPredioAccDAO(ldm_manager)
									                              .findAllByIdCirculoMatriculaTurno(
									    ldpa_direccionActual
									);

							if(CollectionUtils.isValidCollection(acddp_direccionPredio))
								ls_estadoGrabacion = EstadoCommon.ESTADO_DIRECCION_PREDIO;
						}

						{
							AnotacionPredio             lap_anotacionPredio;
							Collection<AnotacionPredio> lcap_anotacionPredio;

							lap_anotacionPredio = new AnotacionPredio();

							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lcap_anotacionPredio = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
									                             .findByCirculoMatricula(lap_anotacionPredio);

							if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
							{
								lapr_pr.setEstadoAnotaciones(true);
								ls_estadoGrabacion = EstadoCommon.ESTADO_ANOTACIONES;
							}
						}

						lapr_pr.setEstadoGrabacion(ls_estadoGrabacion);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findAccMatriculasByTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcapr_pr;
	}

	/**
	 * Metodo encargado de consultar toda la información para un circulo y matricula Dummy
	 *
	 * @param apr_predioRegistro
	 * @param al_etapa
	 * @param as_userId
	 * @return DigitadorAntiguoSistema
	 * @throws B2BException
	 */
	public synchronized DigitadorAntiguoSistema findDigitadorAntiguoSistema(
	    AccPredioRegistro apr_predioRegistro, long al_etapa, String as_userId
	)
	    throws B2BException
	{
		return findDigitadorAntiguoSistema(apr_predioRegistro, al_etapa, as_userId, false, false);
	}

	/**
	 * Metodo encargado de consultar toda la información para un circulo y matricula Dummy
	 *
	 * @param apr_predioRegistro <code>AccPredioRegistro</code> parámetro con el cual se realizarán búsquedas
	 * @param al_etapa <code>long</code> parámetro con la etapa
	 * @param as_userId <code>String</code> parámetro con el usuario que invoca el método
	 * @param ab_consultaTraza <code>boolean</code> que indica si el metodo esta siendo usando por consulta trazabilidad
	 * @return DigitadorAntiguoSistema
	 * @throws B2BException
	 */
	public synchronized DigitadorAntiguoSistema findDigitadorAntiguoSistema(
	    AccPredioRegistro apr_predioRegistro, long al_etapa, String as_userId, boolean ab_consultaTraza,
	    boolean ab_withTurno
	)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		DigitadorAntiguoSistema ldas_das;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldas_das        = new DigitadorAntiguoSistema();

		try
		{
			if(apr_predioRegistro != null)
			{
				AccPredioRegistro lpr_predioRegistro;

				lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager).findById(apr_predioRegistro);

				if(lpr_predioRegistro != null)
				{
					AccPredioSegregadoDAO laps_DAO;
					long                  ll_idMatricula;
					String                ls_idCirculo;
					String                ls_idTurno;
					Long                  ll_idTurnoHistoria;
					DatosAntSistemaDAO    lodas_DAO;
					AnotacionPredioDAO    lapd_DAOBng;

					laps_DAO               = DaoCreator.getAccPredioSegregadoDAO(ldm_manager);
					lodas_DAO              = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
					lapd_DAOBng            = DaoCreator.getAnotacionPredioDAO(ldm_manager);
					ll_idMatricula         = NumericUtils.getLong(lpr_predioRegistro.getIdMatricula());
					ls_idCirculo           = lpr_predioRegistro.getIdCirculo();
					ls_idTurno             = lpr_predioRegistro.getIdTurno();
					ll_idTurnoHistoria     = apr_predioRegistro.getIdTurnoHistoria();

					ldas_das.setAccPredioRegistro(lpr_predioRegistro);
					// SALVEDADES
					{
						Collection<AccSalvedadPredio> lcsp_salvedades;

						lcsp_salvedades = DaoCreator.getAccSalvedadPredioDAO(ldm_manager)
								                        .findAll(
								    ls_idTurno, ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula)
								);

						if(CollectionUtils.isValidCollection(lcsp_salvedades))
						{
							CausalCorreccionDAO lcc_DAO;

							lcc_DAO = DaoCreator.getCausalCorreccionDAO(ldm_manager);

							for(AccSalvedadPredio lsp_iterador : lcsp_salvedades)
							{
								if(lsp_iterador != null)
								{
									CausalCorreccion lcc_causal;

									lcc_causal = lcc_DAO.findById(lsp_iterador.getIdCausal());

									if(lcc_causal != null)
										lsp_iterador.setNombre(lcc_causal.getNombre());
								}
							}
						}

						ldas_das.setSalvedadesPredio(lcsp_salvedades);
					}
					// TAB Alertas del predio
					{
						com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO loap_DAO;
						AlertaNaturalezaJuridicaDAO                        loanj_DAO;
						NaturalezaJuridicaDAO                              lnjd_DAO;
						Collection<AlertaNaturalezaJuridica>               lcanj_allInfo;
						Collection<AnotacionPredio>                        lcap_tmp;
						Map<String, Boolean>                               lcs_gravamenes;
						Map<String, Boolean>                               lcs_trasnferenciaDominio;
						AlertaNaturalezaJuridica                           lontj_tmp;
						AnotacionPredio                                    loap_tmp;

						loap_DAO                     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
						loanj_DAO                    = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager);
						lnjd_DAO                     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
						lcanj_allInfo                = new ArrayList<AlertaNaturalezaJuridica>();
						lcap_tmp                     = new ArrayList<AnotacionPredio>();
						lcs_gravamenes               = new HashMap<String, Boolean>();
						lcs_trasnferenciaDominio     = new HashMap<String, Boolean>();
						loap_tmp                     = new AnotacionPredio();

						lcs_gravamenes.put("0200", Boolean.TRUE);
						lcs_gravamenes.put("0400", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0100", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0300", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0600", Boolean.TRUE);

						loap_tmp.setIdCirculo(ls_idCirculo);
						loap_tmp.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

						lcap_tmp = loap_DAO.findNaturalezaByMatricula(loap_tmp);

						if(CollectionUtils.isValidCollection(lcap_tmp))
						{
							for(AnotacionPredio loapr_tmp : lcap_tmp)
							{
								if(loapr_tmp != null)
								{
									String ls_grupoNaturaleza;
									String ls_idNaturaleza;
									String ls_idEstadoAnotacion;
									String ls_anotacionCancelada;
									long   ll_version;

									ls_grupoNaturaleza        = loapr_tmp.getGrupoNaturaleza();
									ls_idNaturaleza           = loapr_tmp.getIdNaturalezaJuridica();
									ll_version                = loapr_tmp.getVersion();
									ls_idEstadoAnotacion      = loapr_tmp.getIdEstadoAnotacion();
									ls_anotacionCancelada     = loapr_tmp.getAnotacionCancelada();

									if(BooleanUtils.getBooleanValue(lcs_gravamenes.get(ls_grupoNaturaleza)))
									{
										if(
										    StringUtils.getStringNotNull(loapr_tmp.getAnotacionCancelada())
											               .equalsIgnoreCase(EstadoCommon.N)
										)
										{
											lontj_tmp = loanj_DAO.findById(ls_idNaturaleza, ll_version);

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}
									else if(
									    BooleanUtils.getBooleanValue(lcs_trasnferenciaDominio.get(ls_grupoNaturaleza))
									)
									{
										AnotacionPredio loap_info;

										loap_info = loap_DAO.findAllByTransferenciaDominio(loap_tmp);

										if(loap_info != null)
										{
											lontj_tmp = loanj_DAO.findById(ls_idNaturaleza, ll_version);

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}

									if(
									    StringUtils.isValidString(ls_idEstadoAnotacion)
										    && ls_idEstadoAnotacion.equalsIgnoreCase(EstadoCommon.A)
									)
									{
										NaturalezaJuridica lnj_naturalezaJuridica;

										lnj_naturalezaJuridica = lnjd_DAO.findById(ls_idNaturaleza, ll_version);

										if(lnj_naturalezaJuridica != null)
										{
											lontj_tmp = new AlertaNaturalezaJuridica();

											lontj_tmp.setIdNaturalezaJuridica(ls_idNaturaleza);
											lontj_tmp.setEspecificacion(lnj_naturalezaJuridica.getNombre());
											lontj_tmp.setNombreAlerta("ANOTACIÓN INVÁLIDA");

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}

									if(
									    StringUtils.isValidString(ls_anotacionCancelada)
										    && ls_anotacionCancelada.equalsIgnoreCase(EstadoCommon.S)
									)
									{
										NaturalezaJuridica lnj_naturalezaJuridica;

										lnj_naturalezaJuridica = lnjd_DAO.findById(ls_idNaturaleza, ll_version);

										if(lnj_naturalezaJuridica != null)
										{
											String ls_idGrupoNaturalezaJuridica;

											ls_idGrupoNaturalezaJuridica = lnj_naturalezaJuridica.getIdGrupoNatJur();

											if(
											    StringUtils.isValidString(ls_idGrupoNaturalezaJuridica)
												    && (ls_idGrupoNaturalezaJuridica.equalsIgnoreCase("0700")
												    || ls_idGrupoNaturalezaJuridica.equalsIgnoreCase("0800"))
											)
											{
												lontj_tmp = new AlertaNaturalezaJuridica();

												lontj_tmp.setIdNaturalezaJuridica(ls_idNaturaleza);
												lontj_tmp.setEspecificacion(lnj_naturalezaJuridica.getNombre());
												lontj_tmp.setNombreAlerta("ANOTACIÓN CANCELADA");

												if(lontj_tmp != null)
												{
													lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
													lcanj_allInfo.add(lontj_tmp);
												}
											}
										}
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcanj_allInfo))
								ldas_das.getInfoAlertas().setAllInfo(lcanj_allInfo);
						}
					}
					//TAB DATOS BASICOS
					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = new DatosBasicos();

						ldb_datosBasicos.setAccPredioRegistro(lpr_predioRegistro);
						//UBICACION
						{
							ZonaRegistral lzr_zonaRegistral;
							String        ls_idZonaRegistral;

							ls_idZonaRegistral     = lpr_predioRegistro.getIdZonaRegistral();

							lzr_zonaRegistral = new ZonaRegistral();

							lzr_zonaRegistral.setIdZonaRegistral(ls_idZonaRegistral);

							lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(lzr_zonaRegistral);

							if(lzr_zonaRegistral != null)
							{
								UbicacionZonaRegistral luzrt_ubicacion;

								luzrt_ubicacion = new UbicacionZonaRegistral();

								{
									CirculoRegistral ls_circuloUbicacion;

									ls_circuloUbicacion = new CirculoRegistral();

									ls_circuloUbicacion.setIdCirculo(lzr_zonaRegistral.getIdCirculo());

									ls_circuloUbicacion = DaoCreator.getCirculoRegistralDAO(ldm_manager)
											                            .findById(ls_circuloUbicacion);

									if(ls_circuloUbicacion != null)
										luzrt_ubicacion.setCirculoRegistral(ls_circuloUbicacion);
								}

								{
									String ls_idPais;
									String ls_idDepartamento;
									String ls_idMunicipio;
									String ls_idVereda;

									ls_idPais             = lzr_zonaRegistral.getIdPais();
									ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
									ls_idMunicipio        = lzr_zonaRegistral.getIdMunicipio();
									ls_idVereda           = lzr_zonaRegistral.getIdVereda();

									{
										Pais lp_pais;

										lp_pais = new Pais();

										lp_pais.setIdPais(ls_idPais);

										lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

										if(lp_pais != null)
											luzrt_ubicacion.setIdPais(lp_pais.getIdPais());
									}

									{
										Departamento ld_departamento;

										ld_departamento = new Departamento();

										ld_departamento.setIdPais(ls_idPais);
										ld_departamento.setIdDepartamento(ls_idDepartamento);

										ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
												                        .findById(ld_departamento);

										if(ld_departamento != null)
											luzrt_ubicacion.setDepartamento(ld_departamento);
									}

									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();

										lm_municipio.setIdPais(ls_idPais);
										lm_municipio.setIdDepartamento(ls_idDepartamento);
										lm_municipio.setIdMunicipio(ls_idMunicipio);

										lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

										if(lm_municipio != null)
											luzrt_ubicacion.setMunicipio(lm_municipio);
									}

									{
										Vereda lv_vereda;
										lv_vereda = new Vereda();

										lv_vereda.setIdPais(ls_idPais);
										lv_vereda.setIdDepartamento(ls_idDepartamento);
										lv_vereda.setIdMunicipio(ls_idMunicipio);
										lv_vereda.setIdVereda(ls_idVereda);

										lv_vereda = DaoCreator.getVeredaDAO(ldm_manager).findById(lv_vereda);

										if(lv_vereda != null)
											luzrt_ubicacion.setVereda(lv_vereda);
									}
								}

								{
									EstadoPredio lsp_estadoPredio;

									lsp_estadoPredio = new EstadoPredio();

									lsp_estadoPredio.setIdEstadoPredio(lpr_predioRegistro.getIdEstadoPredio());

									lsp_estadoPredio = DaoCreator.getEstadoPredioDao(ldm_manager)
											                         .findById(lsp_estadoPredio);

									if(lsp_estadoPredio != null)
										luzrt_ubicacion.setEstadoPredio(lsp_estadoPredio);
								}

								luzrt_ubicacion.setNupre(lpr_predioRegistro.getNupre());
								luzrt_ubicacion.setZonaRegistral(lzr_zonaRegistral);

								ldb_datosBasicos.setUbicacion(luzrt_ubicacion);
							}
						}
						//APERTURA
						{
							Documento ld_documento;

							ld_documento = new Documento();

							ld_documento.setIdDocumento(lpr_predioRegistro.getIdDocumento());
							ld_documento.setVersionDocumento(
							    NumericUtils.getLongWrapper(lpr_predioRegistro.getVersionDocumento())
							);

							ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findByIdDocumentoVersion(
								    ld_documento
								);

							if(ld_documento != null)
							{
								Apertura      lap_apertura;
								OficinaOrigen loo_oficinaOrigen;

								lap_apertura          = new Apertura();
								loo_oficinaOrigen     = new OficinaOrigen();

								loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
								loo_oficinaOrigen.setVersion(ld_documento.getVersion());

								loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
										                          .findById(loo_oficinaOrigen);

								if(loo_oficinaOrigen != null)
								{
									lap_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
									lap_apertura.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
									lap_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
								}

								lap_apertura.setDocumento(ld_documento);
								lap_apertura.setIdTipoOficina(ld_documento.getIdTipoOficina());
								lap_apertura.setIdTipoEntidad(ld_documento.getTipoEntidad());
								lap_apertura.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
								lap_apertura.setFechaApertura(lpr_predioRegistro.getFechaApertura());
								lap_apertura.setRadicacion(lpr_predioRegistro.getRadicacion());

								ldb_datosBasicos.setApertura(lap_apertura);
							}
						}
						//MATRICULA BASE
						{
							if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
							{
								Collection<PredioSegregado> lcps_prediosSegregados;
								MatriculaBase               lmd_matricula;
								PredioSegregado             lprs_predioSegreagdo;

								lmd_matricula            = new MatriculaBase();
								lprs_predioSegreagdo     = new PredioSegregado();

								lprs_predioSegreagdo.setIdCirculo1(ls_idCirculo);
								lprs_predioSegreagdo.setIdMatricula1(ll_idMatricula);
								lprs_predioSegreagdo.setIdTurno(ls_idTurno);

								if(ab_withTurno)
									lcps_prediosSegregados = laps_DAO.findAllByCirculo1Matricula1Turno(
										    lprs_predioSegreagdo
										);
								else
									lcps_prediosSegregados = laps_DAO.findAllByCirculo1Matricula1(lprs_predioSegreagdo);

								if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
								{
									ldb_datosBasicos.setPredioSegregadoACC(lcps_prediosSegregados);

									AnotacionPredioDAO                                                lap_DAO;
									Collection<DireccionPredio>                                       lcdp_cdp;
									Collection<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado> lcps_data;

									lap_DAO       = DaoCreator.getAnotacionPredioDAO(ldm_manager);
									lcdp_cdp      = new ArrayList<DireccionPredio>();
									lcps_data     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado>();

									for(PredioSegregado lps_Actual : lcps_prediosSegregados)
									{
										if(lps_Actual != null)
										{
											DireccionPredio                                       ldp_direccionPredio;
											com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_predio;

											ldp_direccionPredio     = new DireccionPredio();
											lps_predio              = new com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado(
												    lps_Actual
												);

											ldp_direccionPredio.setIdCirculo(lps_Actual.getIdCirculo());
											ldp_direccionPredio.setIdMatricula(
											    NumericUtils.getLongWrapper(lps_Actual.getIdMatricula())
											);

											ldp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager)
													                            .findById(ldp_direccionPredio);

											if(ldp_direccionPredio != null)
												lcdp_cdp.add(ldp_direccionPredio);

											lps_predio.setAnotaciones(
											    lap_DAO.findAnotacionesPredio(
											        lps_predio.getIdCirculo(), lps_predio.getIdMatricula()
											    )
											);

											lps_predio.setAnotacionesSegregadas(
											    lap_DAO.findAnotacionesPredio(
											        lps_predio.getIdCirculo1(), lps_predio.getIdMatricula1()
											    )
											);

											lcps_data.add(lps_predio);
										}
									}

									if(CollectionUtils.isValidCollection(lcdp_cdp))
										lmd_matricula.setDireccionPredio(lcdp_cdp);

									if(CollectionUtils.isValidCollection(lcps_data))
										ldb_datosBasicos.setPredioSegregado(lcps_data);
								}

								lmd_matricula.setIdCirculo(ls_idCirculo);

								ldb_datosBasicos.setMatriculaBase(lmd_matricula);
							}
						}
						//INFORMACION CATASTRAL
						{
							ldb_datosBasicos.setCodigoCatastral(lpr_predioRegistro.getNumeroPredial());
							ldb_datosBasicos.setCodigoCatastralAnterior(lpr_predioRegistro.getNumeroPredialAnt());
						}

						//DATOS ANTIGUO SISTEMA
						String ls_idDatosAntSistema;

						ls_idDatosAntSistema = lpr_predioRegistro.getIdDatosAntSistema();

						if(StringUtils.isValidString(ls_idDatosAntSistema))
						{
							DatosAntSistema ldas_datosAnt;

							ldas_datosAnt = DaoCreator.getDatosAntSistemaDAO(ldm_manager).findById(
								    ls_idDatosAntSistema
								);

							if(ldas_datosAnt != null)
							{
								ldb_datosBasicos.setDatosAntSistema(ldas_datosAnt);

								Collection<DetalleAntSistema> lcdas_detalles;

								lcdas_detalles = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
										                       .findByDatosAntSis(ls_idDatosAntSistema);

								if(CollectionUtils.isValidCollection(lcdas_detalles))
									ldb_datosBasicos.setDetallesAntSistema(lcdas_detalles);
							}
						}

						ldas_das.setDatosbasicos(ldb_datosBasicos);
					}
					//TAB DESCRIPCION CABIDA Y LINDEROS
					{
						//LINDEROS
						{
							AccLinderoPredio    lalp_lindero;
							AccLinderoPredioDAO lalp_DAO;
							String              ls_idLinderoPredio;

							lalp_DAO         = DaoCreator.getAccLinderoPredioDAO(ldm_manager);
							lalp_lindero     = new AccLinderoPredio();

							lalp_lindero.setIdTurno(ls_idTurno);
							lalp_lindero.setIdCirculo(ls_idCirculo);
							lalp_lindero.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_lindero, false);

							if(StringUtils.isValidString(ls_idLinderoPredio))
							{
								lalp_lindero.setIdLinderoPredio(ls_idLinderoPredio);

								lalp_lindero = lalp_DAO.findById(lalp_lindero);
							}
							else
							{
								ls_idLinderoPredio = lalp_DAO.findMaxIdLindero(lalp_lindero, true);

								lalp_lindero.setIdLinderoPredio(ls_idLinderoPredio);

								lalp_lindero = lalp_DAO.findById(lalp_lindero);
							}

							if(lalp_lindero != null)
								ldas_das.setAccLinderoPredio(lalp_lindero);

							{
								Turno lt_turno;

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_idTurno);

								if(lt_turno != null)
								{
									Collection<Acto> lca_data;

									lca_data = DaoCreator.getActoDAO(ldm_manager)
											                 .findByIdSolicitudCirculo(
											    lt_turno.getIdSolicitud(), ls_idCirculo
											);

									if(CollectionUtils.isValidCollection(lca_data))
									{
										boolean lb_textoLinderos;

										lb_textoLinderos = false;

										Map<String, String> lmss_actos;

										lmss_actos = generarCodigos(
											    ConstanteCommon.CODIGOS_ACTOS_GENERAR_LINDEROS, ldm_manager
											);

										if(CollectionUtils.isValidCollection(lmss_actos))
										{
											Iterator<Acto> lia_iterator;

											lia_iterator = lca_data.iterator();

											if(lia_iterator != null)
											{
												while(lia_iterator.hasNext() && !lb_textoLinderos)
												{
													Acto laa_data;

													laa_data = lia_iterator.next();

													if(laa_data != null)
													{
														String ls_idTipoActo;

														ls_idTipoActo        = laa_data.getIdTipoActo();
														lb_textoLinderos     = StringUtils.isValidString(ls_idTipoActo)
																&& lmss_actos.containsKey(ls_idTipoActo);
													}
												}
											}
										}

										if(lb_textoLinderos)
										{
											Constantes lc_constante;

											lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
													                     .findById(ConstanteCommon.TEXTO_LINDEROS_0317);

											if(lc_constante != null)
											{
												String ls_texto;

												ls_texto = lc_constante.getCaracter();

												if(StringUtils.isValidString(ls_texto))
												{
													String ls_tag;

													ls_tag = "<TAG_DATOS_DOCUMENTO>";

													if(ls_texto.contains(ls_tag))
													{
														DatosBasicos ldb_data;

														ldb_data = ldas_das.getDatosbasicos();

														if(ldb_data != null)
														{
															Apertura la_data;

															la_data = ldb_data.getApertura();

															if(la_data != null)
															{
																Documento ld_data;

																ld_data = la_data.getDocumento();

																if(ld_data != null)
																{
																	String ls_dataTag;

																	ls_dataTag = DaoCreator.getDocumentoDAO(
																		    ldm_manager
																		).findDataById(ld_data.getIdDocumento());

																	if(StringUtils.isValidString(ls_dataTag))
																		ls_texto = ls_texto.replace(ls_tag, ls_dataTag);
																}
															}
														}
													}

													ls_tag = "<TAG_NOMBRE_PREDIO>";

													if(ls_texto.contains(ls_tag))
													{
														String ls_dataTag;

														ls_dataTag = lpr_predioRegistro.getNombrePredio();

														if(StringUtils.isValidString(ls_dataTag))
															ls_texto = ls_texto.replace(ls_tag, ls_dataTag);
													}

													String ls_tag2;

													ls_tag      = "<TAG_AREA>";
													ls_tag2     = "<TAG_TIPO_USO_SUELO>";

													if(ls_texto.contains(ls_tag) || ls_texto.contains(ls_tag2))
													{
														AccAreaPredio laap_ap;

														laap_ap = new AccAreaPredio();

														laap_ap.setIdTurno(ls_idTurno);
														laap_ap.setIdCirculo(ls_idCirculo);
														laap_ap.setIdMatricula(
														    NumericUtils.getLongWrapper(ll_idMatricula)
														);

														laap_ap = DaoCreator.getAccAreaPredioDAO(ldm_manager)
																                .findByCirculoMatriculaTurno(laap_ap);

														if(laap_ap != null)
														{
															if(ls_texto.contains(ls_tag))
															{
																DetalleAreaPredio ldap_data;

																ldap_data = DaoCreator.getAccDetalleAreaPredioDAO(
																	    ldm_manager
																	)
																		                  .findAllByIdAreaPredioTipoUnidad(
																		    laap_ap.getIdArea(), ls_idCirculo,
																		    NumericUtils.getLongWrapper(ll_idMatricula),
																		    TipoAreaCommon.TERRENO,
																		    UnidadMedidaAreaCommon.METROS_CUADRADOS
																		);

																if(ldap_data != null)
																{
																	String ls_dataTag;

																	ls_dataTag = StringUtils.getString(
																		    ldap_data.getArea()
																		);

																	if(StringUtils.isValidString(ls_dataTag))
																		ls_texto = ls_texto.replace(
																			    ls_tag,
																			    ls_dataTag
																			    + IdentificadoresCommon.ESPACIO_VACIO
																			    + "m2"
																			);
																}
															}

															if(ls_texto.contains(ls_tag2))
															{
																TipoUsoSuelo ltus_data;

																ltus_data = DaoCreator.getTipoUsoSueloDAO(ldm_manager)
																		                  .findById(
																		    laap_ap.getTipoSuelo()
																		);

																if(ltus_data != null)
																{
																	String ls_dataTag;

																	ls_dataTag = ltus_data.getDescription();

																	if(StringUtils.isValidString(ls_dataTag))
																		ls_texto = ls_texto.replace(
																			    ls_tag2, ls_dataTag
																			);
																}
															}
														}
													}
												}

												ldas_das.setTextoLinderos(limpiarTags(ls_texto));
											}
										}
									}
								}
							}
						}
						//COMPLEMENTACION
						{
							Long ll_idComplementacion;

							ll_idComplementacion = lpr_predioRegistro.getIdComplementacion();

							if(NumericUtils.isValidLong(ll_idComplementacion))
							{
								ComplementacionPredio lcp_complemento;

								lcp_complemento = new ComplementacionPredio();

								lcp_complemento.setIdComplementacion(
								    String.valueOf(NumericUtils.getLong(ll_idComplementacion))
								);

								lcp_complemento = DaoCreator.getComplementacionPredioDAO(ldm_manager)
										                        .findById(lcp_complemento);

								if(lcp_complemento != null)
								{
									AccComplementacionPredio lacp_complementacion;
									String                   ls_tipoComplementacion;

									lacp_complementacion       = new AccComplementacionPredio();
									ls_tipoComplementacion     = null;

									lacp_complementacion.setIdComplementacionOriginal(
									    NumericUtils.getLongWrapper(lcp_complemento.getIdComplementacion())
									);
									lacp_complementacion.setIdTurno(ls_idTurno);

									lacp_complementacion = DaoCreator.getAccComplementacionPredioDAO(ldm_manager)
											                             .findByIdOriginal(lacp_complementacion);

									if(lacp_complementacion != null)
									{
										ls_tipoComplementacion = lacp_complementacion.getTipoComplementacion();

										if(
										    (apr_predioRegistro.isProcesoDivisionMaterial()
											    && apr_predioRegistro.isProcesoLoteo())
											    || apr_predioRegistro.isProcesoReloteo()
											    || apr_predioRegistro.isProcesoDivisionMaterial()
										)
										{
											if(
											    StringUtils.isValidString(ls_tipoComplementacion)
												    && (ls_tipoComplementacion.equalsIgnoreCase(
												        TipoComplementacionCommon.A
												    )
												    || ls_tipoComplementacion.equalsIgnoreCase(
												        TipoComplementacionCommon.C
												    ))
											)
												ldas_das.setAccComplementacionPredio(
												    new AccComplementacionPredio(lcp_complemento)
												);
											else
												ldas_das.setAccComplementacionPredio(lacp_complementacion);
										}
										else
											ldas_das.setAccComplementacionPredio(lacp_complementacion);
									}
									else
										ldas_das.setAccComplementacionPredio(
										    new AccComplementacionPredio(lcp_complemento)
										);

									if(al_etapa == EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO)
									{
										AccComplementacionPredio lacp_acp;
										lacp_acp = ldas_das.getAccComplementacionPredio();

										if(lacp_acp != null)
											lacp_acp.setTipoComplementacion(ls_tipoComplementacion);
									}
								}
							}
						}
					}
					//TAB AREA PREDIO
					{
						AccAreaPredio laap_ap;

						laap_ap = new AccAreaPredio();

						laap_ap.setIdTurno(ls_idTurno);
						laap_ap.setIdCirculo(ls_idCirculo);
						laap_ap.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

						laap_ap = DaoCreator.getAccAreaPredioDAO(ldm_manager).findByCirculoMatriculaTurno(laap_ap);

						if(laap_ap != null)
						{
							AccAreaUI     laaui_dataArea;
							AccAreaPredio laap_areaPredio;
							String        ls_idAreaPredio;

							laaui_dataArea      = new AccAreaUI();
							laap_areaPredio     = new AccAreaPredio();
							ls_idAreaPredio     = StringUtils.getString(laap_ap.getIdArea());

							if(StringUtils.isValidString(ls_idAreaPredio))
							{
								AccDetalleAreaPredioDAO ladap_DAO;
								Long                    ll_idMatriculaLong;
								MedidaAreaDAO           lma_DAO;

								ladap_DAO              = DaoCreator.getAccDetalleAreaPredioDAO(ldm_manager);
								ll_idMatriculaLong     = NumericUtils.getLongWrapper(ll_idMatricula);
								lma_DAO                = DaoCreator.getMedidaAreaDAO(ldm_manager);

								laap_areaPredio.setIdArea(ls_idAreaPredio);

								{
									Collection<DetalleAreaPredio> lcdap_areasTerreno;
									DetalleAreaPredio             ldap_areaTerreno;

									ldap_areaTerreno = new DetalleAreaPredio();

									ldap_areaTerreno.setIdTipoArea(TipoAreaCommon.TERRENO);
									ldap_areaTerreno.setIdMatricula(ll_idMatriculaLong);
									ldap_areaTerreno.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaTerreno.setIdCirculo(ls_idCirculo);

									lcdap_areasTerreno = ladap_DAO.findAllByIdAreaPredioTipo(ldap_areaTerreno);

									if(CollectionUtils.isValidCollection(lcdap_areasTerreno))
									{
										int                         li_tam;
										int                         li_count;
										Iterator<DetalleAreaPredio> lidap_iterator;
										StringBuilder               lsb_sb;

										li_tam             = lcdap_areasTerreno.size();
										li_count           = 1;
										lidap_iterator     = lcdap_areasTerreno.iterator();
										lsb_sb             = new StringBuilder();

										while(lidap_iterator.hasNext())
										{
											DetalleAreaPredio ldap_iterador;

											ldap_iterador = lidap_iterator.next();

											if(ldap_iterador != null)
											{
												Double ld_area;

												ld_area = ldap_iterador.getArea();

												if(NumericUtils.isValidDouble(ld_area))
												{
													String ls_medidaArea;
													String ls_separador;
													String ls_unidadMedida;

													ls_medidaArea       = ldap_iterador.getIdUnidadMedida();
													ls_separador        = (li_tam != li_count) ? ", " : "";
													ls_unidadMedida     = "";

													if(StringUtils.isValidString(ls_medidaArea))
													{
														MedidaArea lma_medidaArea;

														lma_medidaArea = lma_DAO.findById(ls_medidaArea);

														if(lma_medidaArea != null)
														{
															String ls_codigo;

															ls_codigo = lma_medidaArea.getCodigo();

															if(StringUtils.isValidString(ls_codigo))
																ls_unidadMedida = " " + ls_codigo;
														}
													}

													lsb_sb.append(ld_area);
													lsb_sb.append(ls_unidadMedida);
													lsb_sb.append(ls_separador);
												}
											}

											li_count++;
										}

										laaui_dataArea.setAreasTerreno(lcdap_areasTerreno);
										laaui_dataArea.setAreaTerreno(lsb_sb.toString());
									}
								}

								{
									DetalleAreaPredio ldap_areaConstruida;

									ldap_areaConstruida = new DetalleAreaPredio();

									ldap_areaConstruida.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
									ldap_areaConstruida.setIdMatricula(ll_idMatriculaLong);
									ldap_areaConstruida.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaConstruida.setIdCirculo(ls_idCirculo);

									ldap_areaConstruida = ladap_DAO.findByIdAreaPredioTipo(ldap_areaConstruida);

									if(ldap_areaConstruida != null)
									{
										Double ld_area;

										ld_area = ldap_areaConstruida.getArea();

										if(NumericUtils.isValidDouble(ld_area))
										{
											String        ls_medidaArea;
											StringBuilder lsb_sb;

											ls_medidaArea     = ldap_areaConstruida.getIdUnidadMedida();
											lsb_sb            = new StringBuilder();

											lsb_sb.append(ld_area);

											if(StringUtils.isValidString(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;

												lma_medidaArea = lma_DAO.findById(ls_medidaArea);

												if(lma_medidaArea != null)
												{
													String ls_codigo;

													ls_codigo = lma_medidaArea.getCodigo();

													if(StringUtils.isValidString(ls_codigo))
														lsb_sb.append(" " + ls_codigo);
												}
											}

											ldap_areaConstruida.setAreaLectura(lsb_sb.toString());
										}
									}

									laaui_dataArea.setDetalleAreaConstruida(ldap_areaConstruida);
								}

								{
									DetalleAreaPredio ldap_areaPrivada;

									ldap_areaPrivada = new DetalleAreaPredio();

									ldap_areaPrivada.setIdTipoArea(TipoAreaCommon.PRIVADA);
									ldap_areaPrivada.setIdMatricula(ll_idMatriculaLong);
									ldap_areaPrivada.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaPrivada.setIdCirculo(ls_idCirculo);

									ldap_areaPrivada = ladap_DAO.findByIdAreaPredioTipo(ldap_areaPrivada);

									if(ldap_areaPrivada != null)
									{
										Double ld_area;

										ld_area = ldap_areaPrivada.getArea();

										if(NumericUtils.isValidDouble(ld_area))
										{
											String        ls_medidaArea;
											StringBuilder lsb_sb;

											ls_medidaArea     = ldap_areaPrivada.getIdUnidadMedida();
											lsb_sb            = new StringBuilder();

											lsb_sb.append(ld_area);

											if(StringUtils.isValidString(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;

												lma_medidaArea = lma_DAO.findById(ls_medidaArea);

												if(lma_medidaArea != null)
												{
													String ls_codigo;

													ls_codigo = lma_medidaArea.getCodigo();

													if(StringUtils.isValidString(ls_codigo))
														lsb_sb.append(" " + ls_codigo);
												}
											}

											ldap_areaPrivada.setAreaLectura(lsb_sb.toString());
										}
									}

									laaui_dataArea.setDetalleAreaPrivada(ldap_areaPrivada);
								}

								{
									Double ld_tmp;

									ld_tmp = NumericUtils.getDoubleWrapper(laap_ap.getCoeficiente());

									if(NumericUtils.isValidDouble(ld_tmp))
										laap_areaPredio.setCoeficiente(ld_tmp);
								}

								{
									String ls_tmp;

									ls_tmp = lpr_predioRegistro.getIdTipoUsoSuelo();

									if(StringUtils.isValidString(ls_tmp))
										laap_areaPredio.setTipoSuelo(ls_tmp);
								}
							}

							laaui_dataArea.setAreaPredio(laap_areaPredio);
							ldas_das.setAreaUI(laaui_dataArea);
						}
					}
					//TAB DIRECCION PREDIO
					{
						Collection<DireccionPredioAcc> acddp_direccionPredio;
						DireccionPredioAcc             ldpa_direccionActual;
						Collection<DireccionDelPredio> lcddp_ddp;

						lcddp_ddp                = new ArrayList<DireccionDelPredio>();
						ldpa_direccionActual     = new DireccionPredioAcc();

						ldpa_direccionActual.setIdCirculo(ls_idCirculo);
						ldpa_direccionActual.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
						ldpa_direccionActual.setIdTurno(ls_idTurno);

						acddp_direccionPredio = DaoCreator.getDireccionPredioAccDAO(ldm_manager)
								                              .findAllByIdCirculoMatriculaTurno(ldpa_direccionActual);

						if(CollectionUtils.isValidCollection(acddp_direccionPredio))
						{
							for(DireccionPredioAcc ldpa_actual : acddp_direccionPredio)
							{
								if(ldpa_actual != null)
								{
									DireccionDelPredio lddp_ddp;

									lddp_ddp = new DireccionDelPredio();

									{
										DatosAntSistema        ldas_datosAntiguoSistema;
										UbicacionZonaRegistral luzrt_ubicacion;

										ldas_datosAntiguoSistema     = new DatosAntSistema();
										luzrt_ubicacion              = ldas_das.getDatosbasicos().getUbicacion();

										if(luzrt_ubicacion != null)
										{
											ldas_datosAntiguoSistema.setIdPais(luzrt_ubicacion.getIdPais());

											{
												Departamento ld_d;

												ld_d = luzrt_ubicacion.getDepartamento();

												if(ld_d != null)
													ldas_datosAntiguoSistema.setIdDepartamento(
													    ld_d.getIdDepartamento()
													);
											}

											{
												Municipio lm_m;

												lm_m = luzrt_ubicacion.getMunicipio();

												if(lm_m != null)
													ldas_datosAntiguoSistema.setIdMunicipio(lm_m.getIdMunicipio());
											}

											{
												Vereda lv_v;

												lv_v = luzrt_ubicacion.getVereda();

												if(lv_v != null)
													ldas_datosAntiguoSistema.setIdVereda(lv_v.getIdVereda());
											}
										}

										{
											PredioTipo lpt_predioTipo;
											lpt_predioTipo = new PredioTipo();

											lpt_predioTipo.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

											PredioTipoDAO lpt_DAO;
											lpt_DAO     = DaoCreator.getPredioTipoDao(ldm_manager);

											lpt_predioTipo = lpt_DAO.findById(lpt_predioTipo);

											if(lpt_predioTipo != null)
												ldas_datosAntiguoSistema.setNombrePredio(
												    lpt_predioTipo.getDescripcion()
												);
										}

										ldas_datosAntiguoSistema.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

										lddp_ddp.setDatosAntiguoSistema(ldas_datosAntiguoSistema);
									}

									lddp_ddp.setDireccionPredio(ldpa_actual);
									lddp_ddp.setDireccion(ldpa_actual.getDireccion());

									lcddp_ddp.add(lddp_ddp);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lcddp_ddp))
							ldas_das.setDireccionesDelPredio(lcddp_ddp);
					}
					//TAB ANOTACIONES
					{
						TurnoHistoria lth_turnoHistoria;
						boolean       lb_etapaCorreccion;
						boolean       lb_turnoHistoriaValido;

						lth_turnoHistoria = new TurnoHistoria();
						lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

						lth_turnoHistoria          = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
								                                   .findById(lth_turnoHistoria);
						lb_etapaCorreccion         = al_etapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES;
						lb_turnoHistoriaValido     = lth_turnoHistoria != null;

						if(lb_turnoHistoriaValido)
							ldas_das.setMotivoNoTramite(
							    StringUtils.getStringNotNull(lth_turnoHistoria.getObservacionesNoTramite())
							);

						if((al_etapa == EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA) || (lb_etapaCorreccion))
						{
//							DETALLE ANOTACIONES ANTIGUO SISTEMA
							Anotacion la_anotacion;

							la_anotacion = new Anotacion();

							if(lb_turnoHistoriaValido)
								la_anotacion.setTurnoHistoria(lth_turnoHistoria);

							la_anotacion.setAccPredioRegistro(lpr_predioRegistro);
//							SE CARGA LAS ANOTACIONES CREADAS PARA LA MATRICULA SELECCIONADA
							{
								com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;
								Collection<Anotacion>                              lca_anotacionesAgregadas;
								Collection<AnotacionPredio>                        lcap_cap;
								AnotacionPredio                                    lap_ap;

								lapd_DAO                     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
								lap_ap                       = new AnotacionPredio();
								lca_anotacionesAgregadas     = new ArrayList<Anotacion>();

								lap_ap.setIdCirculo(ls_idCirculo);
								lap_ap.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

								if(lb_etapaCorreccion)
								{
									lap_ap.setIdTurno(ls_idTurno);

									lcap_cap = lapd_DAO.findByCirculoMatriculaTurno(lap_ap);
								}
								else
									lcap_cap = lapd_DAO.findByCirculoMatricula(lap_ap);

								if(CollectionUtils.isValidCollection(lcap_cap))
								{
									AccSalvedadAnotacionDAO lsa_DAO;

									lsa_DAO = DaoCreator.getAccSalvedadAnotacionDAO(ldm_manager);

									for(AnotacionPredio lap_actual : lcap_cap)
									{
										if(lap_actual != null)
										{
											Anotacion la_anotacionAgregada;

											la_anotacionAgregada = new Anotacion();
//											SE CARGA LOS DOCUMENTOS PARA CADA ANOTACION
											{
												Documento ld_documento;

												ld_documento = new Documento();

												ld_documento.setIdDocumento(lap_actual.getIdDocumento());
												ld_documento.setVersionDocumento(
												    NumericUtils.getLongWrapper(lap_actual.getVersionDocumento())
												);

												ld_documento = DaoCreator.getDocumentoDAO(ldm_manager)
														                     .findByIdDocumentoVersion(ld_documento);

												if(ld_documento != null)
												{
													OficinaOrigen loo_oficinaOrigen;

													loo_oficinaOrigen = new OficinaOrigen();

													loo_oficinaOrigen.setIdOficinaOrigen(
													    ld_documento.getIdOficinaOrigen()
													);
													loo_oficinaOrigen.setVersion(ld_documento.getVersion());

													loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
															                          .findById(loo_oficinaOrigen);

													if(loo_oficinaOrigen != null)
													{
														ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
														ld_documento.setIdDepartamento(
														    loo_oficinaOrigen.getIdDepartamento()
														);
														ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
													}

													{
														TipoOficina lto_to;

														lto_to = new TipoOficina();

														lto_to.setIdTipoOficina(ld_documento.getIdTipoOficina());

														lto_to = DaoCreator.getTipoOficinaDAO(ldm_manager)
																               .findById(lto_to);

														if(lto_to != null)
														{
															TipoEntidad lte_te;

															lte_te = new TipoEntidad();

															lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

															lte_te = DaoCreator.getTipoEntidadDAO(ldm_manager)
																	               .findById(lte_te);

															ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
														}
													}

													la_anotacionAgregada.setDocumento(ld_documento);
												}
											}
//											SE CARGA LOS DATOS DE ANTIGUO SISTEMA PARA CADA ANOTACION
											{
												DatosAntSistema ldas_datosAntiguoSistema;

												ldas_datosAntiguoSistema = new DatosAntSistema();

												ldas_datosAntiguoSistema.setIdDatosAntSistema(
												    lap_actual.getIdDatosAntSistema()
												);

												ldas_datosAntiguoSistema = DaoCreator.getDatosAntSistemaDAO(
													    ldm_manager
													).findById(ldas_datosAntiguoSistema);

												if(ldas_datosAntiguoSistema != null)
													la_anotacion.setDatosAntiguoSistema(ldas_datosAntiguoSistema);
											}

//											SE CARGA LOS DATOS DE LOS INTERVINIENTES PARA CADA ANOTACION
											la_anotacionAgregada.setIntervinientesAgregados(
											    cargarIntervinientes(
											        ls_idCirculo, ll_idMatricula,
											        NumericUtils.getLong(lap_actual.getIdAnotacion()), true, ldm_manager
											    )
											);
//											SE CARGA LOS DATOS DE LOS PREDIO SEGREGADOS PARA CADA ANOTACION
											{
												Collection<Anotacion>       lca_prediosSegregados;
												Collection<PredioSegregado> lcps_prediosSegregados;
												PredioSegregado             lps_predioSegregado;

												lps_predioSegregado       = new PredioSegregado();
												lca_prediosSegregados     = new ArrayList<Anotacion>();

												lps_predioSegregado.setIdCirculo(ls_idCirculo);
												lps_predioSegregado.setIdMatricula(ll_idMatricula);
												lps_predioSegregado.setIdAnotacion(lap_actual.getIdAnotacion());
												lps_predioSegregado.setIdTurno(ls_idTurno);

												lcps_prediosSegregados = DaoCreator.getAccPredioSegregadoDAO(
													    ldm_manager
													).findByCirculoMatriculaAnotacionTurno(lps_predioSegregado);

												if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
												{
													for(PredioSegregado lps_actual : lcps_prediosSegregados)
													{
														if(lps_actual != null)
														{
															Anotacion      la_predioSegregado;
															PredioRegistro lpr_predioSegregado;

															la_predioSegregado      = new Anotacion();
															lpr_predioSegregado     = new PredioRegistro();

															lpr_predioSegregado.setIdMatricula(
															    lps_actual.getIdMatricula1()
															);
															lpr_predioSegregado.setIdCirculo(
															    lps_actual.getIdCirculo1()
															);

															la_predioSegregado.setPredioRegistro(lpr_predioSegregado);

															lca_prediosSegregados.add(la_predioSegregado);
														}
													}

													if(CollectionUtils.isValidCollection(lca_prediosSegregados))
														la_anotacionAgregada.setMatriculasSegregadas(
														    lca_prediosSegregados
														);
												}
											}
//											SE CARGAN LAS ANOTACIONES DE CANCELACION PARA LA MATRICULA SELECCIONADA
											{
												AnotacionCancelacion lac_anotacionCancelacion;

												lac_anotacionCancelacion = new AnotacionCancelacion();

												lac_anotacionCancelacion.setIdCirculo(ls_idCirculo);
												lac_anotacionCancelacion.setIdMatricula(ll_idMatricula);
												lac_anotacionCancelacion.setIdAnotacion(lap_actual.getIdAnotacion());
												lac_anotacionCancelacion.setIdTurno(ls_idTurno);

												lac_anotacionCancelacion = DaoCreator.getAnotacionCancelacionDAO(
													    ldm_manager
													).findByCirculoMatriculaAnotacinoTurno(lac_anotacionCancelacion);

												if(lac_anotacionCancelacion != null)
													la_anotacionAgregada.setAnotacionCancelacion(
													    lac_anotacionCancelacion
													);
											}

											la_anotacionAgregada.setIdAnotacion(
											    NumericUtils.getLong(lap_actual.getIdAnotacion())
											);

											{
												String             ls_naturalezaJuridica;
												String             ls_idNaturalezaJuridica;
												String             ls_nombre;
												NaturalezaJuridica lnj_parametros;
												long               ls_version;

												lnj_parametros     = new NaturalezaJuridica();
												ls_nombre          = null;

												ls_idNaturalezaJuridica     = lap_actual.getIdNaturalezaJuridica();
												ls_version                  = NumericUtils.getLong(
													    lap_actual.getVersion()
													);

												lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
												lnj_parametros.setVersion(ls_version);

												lnj_parametros = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager)
														                       .findById(lnj_parametros);

												if(lnj_parametros != null)
													ls_nombre = lnj_parametros.getNombre();

												ls_naturalezaJuridica = ls_idNaturalezaJuridica + "-" + ls_version;

												lap_actual.setIdNaturalezaJuridica(ls_naturalezaJuridica);

												la_anotacionAgregada.setAnotacionPredio(lap_actual);
												la_anotacionAgregada.setNaturalezaJuridica(ls_nombre);
											}

											{
												DetalleAntSistema ldas_detalleAntiguoSistema;

												ldas_detalleAntiguoSistema = new DetalleAntSistema();

												ldas_detalleAntiguoSistema.setIdDetalleAntSistema(
												    lap_actual.getIdDetalleAntSistema()
												);
												ldas_detalleAntiguoSistema.setIdDatosAntSistema(
												    lap_actual.getIdDatosAntSistema()
												);

												la_anotacionAgregada.setDetalleAntSistema(ldas_detalleAntiguoSistema);
											}

											{
												com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredioBng;

												lap_anotacionPredioBng = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();

												lap_anotacionPredioBng.setIdCirculo(ls_idCirculo);
												lap_anotacionPredioBng.setIdMatricula(
												    NumericUtils.getLongWrapper(ll_idMatricula)
												);
												lap_anotacionPredioBng.setIdAnotacion(
												    NumericUtils.getLongWrapper(la_anotacionAgregada.getIdAnotacion())
												);

												lap_anotacionPredioBng = lapd_DAOBng.findById(lap_anotacionPredioBng);

												if(lap_anotacionPredioBng != null)
													la_anotacionAgregada.setOrden(lap_anotacionPredioBng.getOrden());

												la_anotacionAgregada.setAnotacionIndividual(
												    lap_anotacionPredioBng == null
												);
											}

											{
												AccSalvedadAnotacion lsa_salvedad;

												lsa_salvedad = lsa_DAO.findById(
													    ls_idTurno, ls_idCirculo,
													    NumericUtils.getLongWrapper(ll_idMatricula),
													    lap_actual.getIdAnotacion()
													);

												if(lsa_salvedad != null)
													la_anotacionAgregada.setSalvedad(lsa_salvedad.getDescripcion());
											}

											{
												boolean lb_generaSegregacion;

												lb_generaSegregacion = validarAnotacionApertura(
													    lap_actual, ldm_manager
													);

												if(lb_generaSegregacion)
												{
													Collection<MatriculaSegregacion> lcms_matriculasExistentes;
													String                           ls_idAnotacionApertura;

													ls_idAnotacionApertura        = StringUtils.getString(
														    lap_actual.getIdAnotacion()
														);
													lcms_matriculasExistentes     = DaoCreator.getMatriculaSegregacionDAO(
														    ldm_manager
														)
															                                      .findByIdTurnoCirculoMatricula(
															    ls_idTurno, ls_idCirculo,
															    NumericUtils.getLongWrapper(ll_idMatricula),
															    ls_idAnotacionApertura
															);

													if(!CollectionUtils.isValidCollection(lcms_matriculasExistentes))
													{
														boolean lb_masivo;
														int     li_cantidadAperturar;

														li_cantidadAperturar     = lap_actual.getCantidadAperturar();
														lb_masivo                = li_cantidadAperturar > 10;

														la_anotacionAgregada.setSegregacionMasiva(lb_masivo);
														la_anotacionAgregada.setCantidadAperturar(li_cantidadAperturar);

														if(!lb_masivo)
														{
															Collection<MatriculaSegregacion> lcms_matriculasSegregacion;

															lcms_matriculasSegregacion = new ArrayList<MatriculaSegregacion>();

															for(
															    int li_count = 0; li_count < li_cantidadAperturar;
																    li_count++
															)
																lcms_matriculasSegregacion.add(
																    new MatriculaSegregacion()
																);

															la_anotacionAgregada.setMatriculasSegregacion(
															    lcms_matriculasSegregacion
															);
														}
													}
													else
													{
														la_anotacionAgregada.setMatriculasAperturadas(true);
														la_anotacionAgregada.setMatriculasInformacion(
														    DaoCreator.getAccPredioRegistroDAO(ldm_manager)
															              .findMatriculasInformacion(
															        ll_idTurnoHistoria, ls_idCirculo,
															        NumericUtils.getLongWrapper(ll_idMatricula),
															        ls_idAnotacionApertura
															    )
														);
													}
												}
											}

											lca_anotacionesAgregadas.add(la_anotacionAgregada);
										}
									}
								}

								if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
								{
									if(lb_etapaCorreccion)
									{
										AnotacionPredio lap_anotacionPredio;

										lap_anotacionPredio = new AnotacionPredio();

										lap_anotacionPredio.setIdCirculo(ls_idCirculo);
										lap_anotacionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
										lap_anotacionPredio.setIdTurno(ls_idTurno);

										validarReordenamientoPorFechaAnotacion(
										    lca_anotacionesAgregadas, lap_anotacionPredio, ldm_manager
										);
									}

									la_anotacion.setAnotacionesAgregadas(lca_anotacionesAgregadas);
									ldas_das.setTotalAnotaciones(lca_anotacionesAgregadas.size());
								}
							}

							ldas_das.setAnotacion(la_anotacion);
						}
						else
						{
							//DETALLE ANOTACIONES DIGITADOR MASIVO
							RegistroCalificacion             lorc_rc;
							Collection<RegistroCalificacion> lcrc_dataFinal;
							Collection<RegistroCalificacion> lcrc_dataPredio;

							lorc_rc = new RegistroCalificacion();

							lorc_rc.setIdTurnoHistoria(ll_idTurnoHistoria);
							lorc_rc.setIdCirculo(ls_idCirculo);
							lorc_rc.setIdMatricula(Long.valueOf(ll_idMatricula));
							lorc_rc.setTurno(ls_idTurno);

							lcrc_dataFinal      = new ArrayList<RegistroCalificacion>();
							lcrc_dataPredio     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
									                            .findDataPredio(lorc_rc);

							if(CollectionUtils.isValidCollection(lcrc_dataPredio))
							{
								DetalleAntSistemaDAO ldas_DAO;

								ldas_DAO = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);

								ldas_das.setTotalAnotaciones(lcrc_dataPredio.size());

								for(RegistroCalificacion lorc_rct : lcrc_dataPredio)
								{
									if(lorc_rct != null)
									{
										Collection<RegistroCalificacion> lcrc_dataPredioCiudadano;
										String                           ls_idAnotacion;
										String                           ls_idDocumento;
										String                           ls_idDatosAntSistema;
										String                           ls_idDetalleAntSistema;
										Long                             ll_idAnotacion;

										ls_idAnotacion               = lorc_rct.getIdAnotacionPredio();
										ls_idDocumento               = lorc_rct.getIdDocumento();
										ls_idDatosAntSistema         = lorc_rct.getIdDatosAntSistema();
										ls_idDetalleAntSistema       = lorc_rct.getIdDetalleAntSistema();
										lcrc_dataPredioCiudadano     = new ArrayList<RegistroCalificacion>();

										if(StringUtils.isValidString(ls_idAnotacion))
										{
											ll_idAnotacion     = NumericUtils.getLongWrapper(ls_idAnotacion);

											lcrc_dataPredioCiudadano = DaoCreator.getRegistroCalificacionDAO(
												    ldm_manager
												).findDataPredioAnotacion(ll_idAnotacion);

											if(CollectionUtils.isValidCollection(lcrc_dataPredioCiudadano))
												lorc_rct.setAllMatriculas(lcrc_dataPredioCiudadano);

											{
												com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredioInv;

												lap_anotacionPredioInv = lapd_DAOBng.findById(
													    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
													    ll_idAnotacion
													);

												if(lap_anotacionPredioInv != null)
												{
													String ls_invalido;

													ls_invalido = lap_anotacionPredioInv.getIdEstadoAnotacion();

													if(
													    StringUtils.isValidString(ls_invalido)
														    && ls_invalido.equalsIgnoreCase(EstadoCommon.A)
													)
														lorc_rct.setAnotacionInvalida(true);
												}
											}
										}

										if(StringUtils.isValidString(ls_idDocumento))
										{
											RegistroCalificacion lorc_dataDocumento;

											lorc_dataDocumento     = new RegistroCalificacion();

											lorc_dataDocumento = DaoCreator.getDocumentoDAO(ldm_manager)
													                           .detalleDocumento(ls_idDocumento);

											if(lorc_dataDocumento != null)
												lorc_rct.setDatosDocumento(lorc_dataDocumento);
										}

										boolean lb_b;

										lb_b = StringUtils.isValidString(ls_idDatosAntSistema)
												&& StringUtils.isValidString(ls_idDetalleAntSistema);

										if(lb_b)
										{
											DatosAntSistema lodas_das;

											lodas_das = new DatosAntSistema();

											lodas_das.setIdDatosAntSistema(ls_idDatosAntSistema);

											lodas_das = lodas_DAO.findById(lodas_das);

											if(lodas_das != null)
											{
												DetalleAntSistema ldas_detalleAntSistema;

												ldas_detalleAntSistema = new DetalleAntSistema();

												ldas_detalleAntSistema.setIdDatosAntSistema(ls_idDatosAntSistema);
												ldas_detalleAntSistema.setIdDetalleAntSistema(ls_idDetalleAntSistema);

												ldas_detalleAntSistema = ldas_DAO.findByDetalleYDatosAntSis(
													    ldas_detalleAntSistema
													);

												if(ldas_detalleAntSistema != null)
												{
													Long ll_libro;

													ll_libro = ldas_detalleAntSistema.getIdLibroAntSistema();

													ldas_das.setDatosAntSistemaValid(true);
													lodas_das.setAnio(
													    NumericUtils.getLongWrapper(ldas_detalleAntSistema.getAnio())
													);
													lodas_das.setFolio(
													    NumericUtils.getLongWrapper(ldas_detalleAntSistema.getFolio())
													);
													lodas_das.setTomo(
													    NumericUtils.getLongWrapper(ldas_detalleAntSistema.getFolio())
													);
													lodas_das.setPartida(ldas_detalleAntSistema.getPartida());

													if(NumericUtils.isValidLong(ll_libro))
													{
														LibroAntiguoSistema llas_las;

														llas_las = new LibroAntiguoSistema();

														llas_las.setIdLibroAntiguoSistema(
														    NumericUtils.getLong(ll_libro)
														);

														llas_las = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager)
																                 .findById(llas_las);

														if(llas_las != null)
															lodas_das.setNombreLibro(llas_las.getNombre());
													}
												}

												{
													PredioTipo lpt_tmp;

													lpt_tmp = new PredioTipo();
													lpt_tmp.setIdTipoPredio(lodas_das.getIdTipoPredio());

													lpt_tmp = DaoCreator.getPredioTipoDao(ldm_manager).findById(
														    lpt_tmp
														);

													if(lpt_tmp != null)
														lodas_das.setIdTipoPredio(lpt_tmp.getDescripcion());
												}

												lorc_rct.setAntiguoSistemaData(lodas_das);
											}
										}

										lorc_rct.setDatosValidosAntSistema(lb_b);
										lcrc_dataFinal.add(lorc_rct);
									}
								}

								if(CollectionUtils.isValidCollection(lcrc_dataFinal))
								{
									ldas_das.setIdTurno(ls_idTurno);
									ldas_das.getAnotaciones().setAllMatriculas(lcrc_dataFinal);
								}
							}
						}
					}
					// TAB SALVEDADES
//					{
//						Collection<ConsultaSalvedad> lccs_salvedades;
//						ConsultaSalvedad             lcs_consultaSalvedad;
//
//						lcs_consultaSalvedad     = new ConsultaSalvedad();
//
//						lcs_consultaSalvedad.setIdCirculo(ls_idCirculo);
//						lcs_consultaSalvedad.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
//
//						lccs_salvedades = DaoCreator.getSalvedadesDAO(ldm_manager).findCompuesto(lcs_consultaSalvedad);
//
//						if(CollectionUtils.isValidCollection(lccs_salvedades))
//							ldas_das.setSalvedades(lccs_salvedades);
//					}

					// TAB SEGREGACIONES
					{
						PredioSegregado             lps_predioSegregado;
						Collection<PredioSegregado> lcps_predios;

						lps_predioSegregado = new PredioSegregado();

						lps_predioSegregado.setIdMatricula(ll_idMatricula);
						lps_predioSegregado.setIdCirculo(ls_idCirculo);
						lps_predioSegregado.setIdTurno(ls_idTurno);

						if(ab_withTurno)
							lcps_predios = laps_DAO.findAllByCirculoMatriculaTurno(lps_predioSegregado);
						else
							lcps_predios = laps_DAO.findAllByCirculoMatricula(lps_predioSegregado);

						if(CollectionUtils.isValidCollection(lcps_predios))
						{
							AnotacionPredioDAO                                                lap_DAO;
							boolean                                                           lb_cierreFolio;
							com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO                laap_DAO;
							Collection<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado> lcps_prediosBng;
							NaturalezaJuridicaDAO                                             lnj_DAO;

							lap_DAO             = DaoCreator.getAnotacionPredioDAO(ldm_manager);
							lb_cierreFolio      = false;
							laap_DAO            = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);
							lcps_prediosBng     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado>();
							lnj_DAO             = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);

							for(PredioSegregado lps_actual : lcps_predios)
							{
								if(lps_actual != null)
								{
									boolean                                               ab_acc;
									com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_predio;
									Long                                                  ll_idMatricula1;
									String                                                ls_idCirculo1;

									lps_predio          = new com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado(
										    lps_actual
										);
									ll_idMatricula1     = lps_predio.getIdMatricula1();
									ls_idCirculo1       = lps_predio.getIdCirculo1();
									ab_acc              = laps_DAO.findPredioAcc(
										    ls_idCirculo1, ll_idMatricula1, ls_idTurno
										);

									lps_predio.setAnotaciones(
									    lap_DAO.findAnotacionesPredio(
									        lps_predio.getIdCirculo(), lps_predio.getIdMatricula()
									    )
									);

									lps_predio.setAnotacionesSegregadas(
									    ab_acc ? laap_DAO.findByCirculoMatriculaBng(ls_idCirculo1, ll_idMatricula1)
									           : lap_DAO.findAnotacionesPredio(ls_idCirculo1, ll_idMatricula1)
									);

									if(!lb_cierreFolio)
									{
										Long ll_idAnotacion;

										ll_idAnotacion = lps_actual.getIdAnotacion1();

										if(NumericUtils.isValidLong(ll_idAnotacion))
										{
											com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

											lap_anotacionPredio = null;

											if(ab_acc)
												lap_anotacionPredio = lap_DAO.findByIdAcc(
													    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
													    ll_idAnotacion
													);
											else
												lap_anotacionPredio = lap_DAO.findById(
													    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
													    ll_idAnotacion
													);

											if(lap_anotacionPredio != null)
											{
												NaturalezaJuridica lnj_data;

												lnj_data           = lnj_DAO.findByIdMaxVersion(
													    lap_anotacionPredio.getIdNaturalezaJuridica()
													);
												lb_cierreFolio     = (lnj_data != null)
														&& lnj_data.isRequiereCierreFolio();
											}
										}
									}

									lps_predio.setAnotacionesAcc(ab_acc);
									lcps_prediosBng.add(lps_predio);
								}
							}

							ldas_das.setCierreFolio(lb_cierreFolio);

							if(lb_cierreFolio)
							{
								CambioEstadoPredio lcep_cambioEstado;

								lcep_cambioEstado = new CambioEstadoPredio();

								lcep_cambioEstado.setIdTurno(ls_idTurno);

								lcep_cambioEstado = DaoCreator.getCambioEstadoPredioDAO(ldm_manager)
										                          .findByIdTurno(lcep_cambioEstado);

								if(lcep_cambioEstado != null)
									ldas_das.setCambioEstadoPredio(lcep_cambioEstado);
							}

							if(CollectionUtils.isValidCollection(lcps_prediosBng))
								ldas_das.setPrediosSegregados(lcps_prediosBng);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDigitadorAntiguoSistema", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldas_das;
	}

	/**
	 * Metodo encargado de consultar toda la información de los tabs consulta del predio.
	 *
	 * @param aopr_predioRegistro correspondiente al valor de aopr predio registro
	 * @return ConsultaPredio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized ConsultaPredio findInfoTabs(PredioRegistro aopr_predioRegistro)
	    throws B2BException
	{
		return findInfoTabs(aopr_predioRegistro, false, false);
	}

	/**
	 * Metodo encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param aopr_predioRegistro
	 * @param ab_withTurno indica si en el proceso se usará el turno.
	 * @return ConsultaPredio
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio findInfoTabs(PredioRegistro aopr_predioRegistro, boolean ab_withTurno)
	    throws B2BException
	{
		return findInfoTabs(aopr_predioRegistro, ab_withTurno, false);
	}

	/**
	 * Metodo encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param aopr_predioRegistro
	 * @param ab_with_turno
	 * @param ab_consultaTraza
	 * @return ConsultaPredio
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio findInfoTabs(
	    PredioRegistro aopr_predioRegistro, boolean ab_with_turno, boolean ab_consultaTraza
	)
	    throws B2BException
	{
		ConsultaPredio     lcp_return;
		AnotacionPredioDAO lapd_DAO;
		DAOManager         ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_return      = null;
		lapd_DAO        = DaoCreator.getAnotacionPredioDAO(ldm_manager);

		try
		{
			if(aopr_predioRegistro != null)
			{
				String ls_tipoMatricula;
				String ls_idCirculo;
				Long   ll_idMatricula;

				ls_tipoMatricula     = aopr_predioRegistro.getIdTipoPredio();
				ls_idCirculo         = aopr_predioRegistro.getIdCirculo();
				ll_idMatricula       = NumericUtils.getLongWrapper(aopr_predioRegistro.getIdMatricula());

				if(StringUtils.isValidString(ls_tipoMatricula))
				{
					if(ls_tipoMatricula.equalsIgnoreCase(IdentificadoresCommon.DEFINITIVA))
						lcp_return = findInfoTabsBng(aopr_predioRegistro, null, true, true, false, ab_consultaTraza);
					else if(ls_tipoMatricula.equalsIgnoreCase(IdentificadoresCommon.TEMPORAL))
					{
						AccPredioRegistro       lpr_predioRegistro;
						DigitadorAntiguoSistema ldas_das;

						lpr_predioRegistro = new AccPredioRegistro();

						lpr_predioRegistro.setIdMatricula(
						    NumericUtils.getLongWrapper(aopr_predioRegistro.getIdMatricula())
						);
						lpr_predioRegistro.setIdCirculo(aopr_predioRegistro.getIdCirculo());

						if(ab_with_turno)
						{
							String ls_idTurno;

							ls_idTurno = aopr_predioRegistro.getIdTurno();

							if(!StringUtils.isValidString(ls_idTurno))
							{
								TurnoHistoria lth_th;

								lth_th = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
										               .findById(aopr_predioRegistro.getIdTurnoHistoria());

								if(lth_th != null)
									ls_idTurno = lth_th.getIdTurno();
							}

							lpr_predioRegistro.setIdTurno(ls_idTurno);

							lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
									                           .findByCirculoMatriculaTurno(lpr_predioRegistro);
						}
						else
							lpr_predioRegistro = DaoCreator.getAccPredioRegistroDAO(ldm_manager)
									                           .findByCirculoMatricula(lpr_predioRegistro);

						if(lpr_predioRegistro != null)
						{
							long ll_etapa;

							ll_etapa     = aopr_predioRegistro.getEtapa();

							ldas_das = findDigitadorAntiguoSistema(
								    lpr_predioRegistro,
								    (ll_etapa > 0) ? ll_etapa : EtapaCommon.ID_ETAPA_DIGITADOR_MASIVO, null,
								    ab_consultaTraza, ab_with_turno
								);

							if(ldas_das != null)
							{
								lcp_return = new ConsultaPredio();

								if(ldas_das.getAccPredioRegistro() != null)
									lcp_return.setPredioRegistro(new PredioRegistro(ldas_das.getAccPredioRegistro()));

								lcp_return.setSalvedadesPredio(ldas_das.getSalvedadesPredio());
								lcp_return.setCierreFolio(ldas_das.isCierreFolio());

								{
									DatosBasicos ldb_datosBasicos;

									ldb_datosBasicos = ldas_das.getDatosbasicos();

									if(ldb_datosBasicos != null)
									{
										lcp_return.setDatosbasicos(ldb_datosBasicos);

										{
											DatosAntSistema ldas_datosAnt;

											ldas_datosAnt = ldb_datosBasicos.getDatosAntSistema();

											if(ldas_datosAnt != null)
												lcp_return.setAntiguoSistemaData(ldas_datosAnt);
										}

										{
											Collection<DetalleAntSistema> lcdas_detalles;

											lcdas_detalles = ldb_datosBasicos.getDetallesAntSistema();

											if(CollectionUtils.isValidCollection(lcdas_detalles))
												lcp_return.setDetallesAntSistema(lcdas_detalles);
										}
									}
								}

								if(ldas_das.getAccComplementacionPredio() != null)
									lcp_return.setComplementacionPredio(
									    new ComplementacionPredio(ldas_das.getAccComplementacionPredio())
									);

								if(ldas_das.getAccLinderoPredio() != null)
									lcp_return.setLinderoPredio(new LinderoPredio(ldas_das.getAccLinderoPredio()));

								if(ldas_das.getAreaUI() != null)
								{
									AccAreaUI laui_areaUI;

									laui_areaUI = ldas_das.getAreaUI();

									if(laui_areaUI != null)
									{
										Collection<DetalleAreaPredio> lcdap_detalleArea;

										lcdap_detalleArea = laui_areaUI.getAreasTerreno();

										if(CollectionUtils.isValidCollection(lcdap_detalleArea))
										{
											AreaPredio lap_areaPredio;

											lap_areaPredio = new AreaPredio();

											for(DetalleAreaPredio ldap_temp : lcdap_detalleArea)
											{
												if(ldap_temp != null)
												{
													String ls_tipoArea;

													ls_tipoArea = ldap_temp.getIdTipoArea();

													switch(ls_tipoArea)
													{
														case TipoAreaCommon.TERRENO:
															lap_areaPredio.setNombreAreaTerreno(
															    ldap_temp.getAreaLectura()
															);

															break;

														case TipoAreaCommon.CONSTRUIDA:
															lap_areaPredio.setNombreAreaConstruida(
															    ldap_temp.getAreaLectura()
															);

															break;

														case TipoAreaCommon.PRIVADA:
															lap_areaPredio.setNombreAreaPrivada(
															    ldap_temp.getAreaLectura()
															);

															break;

														case TipoAreaCommon.COEFICIENTE:
															lap_areaPredio.setCoeficiente(
															    NumericUtils.getBigDecimal(ldap_temp.getAreaLectura())
															);

															break;

														case TipoAreaCommon.ANOTACION:
															lap_areaPredio.setIdAnotacion(
															    NumericUtils.getBigDecimal(ldap_temp.getAreaLectura())
															);

														default:
															break;
													}
												}
											}

											if(lap_areaPredio != null)
											{
												Long ll_idAnotacion;

												ll_idAnotacion = NumericUtils.getLongWrapper(
													    lap_areaPredio.getIdAnotacion()
													);

												if(
												    StringUtils.isValidString(ls_idCirculo)
													    && NumericUtils.isValidLong(ll_idMatricula)
													    && NumericUtils.isValidLong(ll_idAnotacion)
												)
												{
													com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

													lap_anotacionPredio = lapd_DAO.findById(
														    ls_idCirculo, ll_idMatricula, ll_idAnotacion
														);

													if(lap_anotacionPredio != null)
														lap_areaPredio.setOrden(lap_anotacionPredio.getOrden());
												}

												Collection<AreaPredio> lcap_areaPredio;

												lcap_areaPredio = new ArrayList<AreaPredio>();

												lcap_areaPredio.add(lap_areaPredio);
												lcp_return.setDatosAreaPredio(lcap_areaPredio);
											}
										}
									}

									lcp_return.setAreaUI(ldas_das.getAreaUI());
								}

								if(ldas_das.getAreaPredio() != null)
									lcp_return.setAreaPredio(ldas_das.getAreaPredio());

								if(ldas_das.getDireccionesDelPredio() != null)
									lcp_return.setDireccionesDelPredio(ldas_das.getDireccionesDelPredio());

								if(ldas_das.getAnotaciones() != null)
									lcp_return.setAnotaciones(ldas_das.getAnotaciones());

								if(ldas_das.getAnotacion() != null)
									lcp_return.setAnotacion(ldas_das.getAnotacion());

								if(ldas_das.getInfoAlertas() != null)
								{
									AlertaNaturalezaJuridica             lanj_alertaNaturaleza;
									Collection<AlertaNaturalezaJuridica> lcanj_canj;

									lanj_alertaNaturaleza     = ldas_das.getInfoAlertas();
									lcanj_canj                = lanj_alertaNaturaleza.getAllInfo();

									if(CollectionUtils.isValidCollection(lcanj_canj))
									{
										for(AlertaNaturalezaJuridica lanj_temp : lcanj_canj)
										{
											if(lanj_temp != null)
											{
												Long ll_idAnotacion;

												ll_idAnotacion = NumericUtils.getLongWrapper(
													    lanj_temp.getIdAnotacion()
													);

												if(
												    StringUtils.isValidString(ls_idCirculo)
													    && NumericUtils.isValidLong(ll_idMatricula)
													    && NumericUtils.isValidLong(ll_idAnotacion)
												)
												{
													com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

													lap_anotacionPredio = lapd_DAO.findById(
														    ls_idCirculo, ll_idMatricula, ll_idAnotacion
														);

													if(lap_anotacionPredio != null)
														lanj_temp.setOrden(lap_anotacionPredio.getOrden());
												}
											}
										}
									}

									lcp_return.setInfoAlertas(ldas_das.getInfoAlertas());
								}

								if(ldas_das.getPrediosSegregados() != null)
									lcp_return.setPrediosSegregados(ldas_das.getPrediosSegregados());

								lcp_return.setDatosAntSistemaValid(ldas_das.isDatosAntSistemaValid());
								lcp_return.setTotalAnotaciones(ldas_das.getTotalAnotaciones());
								lcp_return.setCambioEstadoPredio(ldas_das.getCambioEstadoPredio());

								{
									Collection<ConsultaSalvedad> lcs_consultaSalvedades;

									lcs_consultaSalvedades = lcp_return.getSalvedades();

									if(CollectionUtils.isValidCollection(lcs_consultaSalvedades))
									{
										for(ConsultaSalvedad lcs_temp : lcs_consultaSalvedades)
										{
											if(lcs_temp != null)
											{
												Date ld_fechaRegistro;

												ld_fechaRegistro = lcs_temp.getFechaRegistro();

												if(ld_fechaRegistro != null)
													lcs_temp.setFechaRegistroString(
													    StringUtils.getString(
													        ld_fechaRegistro, FormatoFechaCommon.DIA_MES_ANIO
													    )
													);

												Long ll_idAnotacion;

												ll_idAnotacion = lcs_temp.getIdAnotacion();

												if(
												    StringUtils.isValidString(ls_idCirculo)
													    && NumericUtils.isValidLong(ll_idMatricula)
													    && NumericUtils.isValidLong(ll_idAnotacion)
												)
												{
													com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

													lap_anotacionPredio = lapd_DAO.findById(
														    ls_idCirculo, ll_idMatricula, ll_idAnotacion
														);

													if(lap_anotacionPredio != null)
														lcs_temp.setOrden(lap_anotacionPredio.getOrden());
												}
											}
										}
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
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInfoTabs", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_return;
	}

	/**
	 * Método encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param aopr_predioRegistro Objeto de tipo <code>PredioRegistro</code> que contiene id_circulo y id_matricula a consultar
	 * @param as_userId Objeto de tipo <code>java.lang.String</code> que contiene usuario que realiza la accion
	 * @param ab_anotacionesAcc Variable de tipo <code>boolean</code> que define si adicional de obtienen las anotaciones temporales
	 * @return Objeto de tipo <code>ConsultaPredio</code> que contiene resultado de la consulta realizada
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio findInfoTabsBng(
	    PredioRegistro aopr_predioRegistro, String as_userId, boolean ab_anotacionesAcc
	)
	    throws B2BException
	{
		return findInfoTabsBng(aopr_predioRegistro, as_userId, ab_anotacionesAcc, true, false);
	}

	/**
	 * Sobrecarga de método (<code>findInfoTabsBng</code>) encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param aopr_predioRegistro Objeto de tipo <code>PredioRegistro</code> que contiene id_circulo y id_matricula a consultar
	 * @param as_userId Objeto de tipo <code>java.lang.String</code> que contiene usuario que realiza la accion
	 * @param ab_anotacionesAcc Variable de tipo <code>boolean</code> que define si adicional de obtienen las anotaciones temporales
	 * @param ab_anotacionesDashBoard Variable de tipo <code>boolean</code> que define la forma en la que se obtienen las anotaciones (<code>true</code> -> dashBoard | <code>false</code> -> formulario )
	 * @return Objeto de tipo <code>ConsultaPredio</code> que contiene resultado de la consulta realizada
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio findInfoTabsBng(
	    PredioRegistro aopr_predioRegistro, String as_userId, boolean ab_anotacionesAcc, boolean ab_anotacionesDashBoard,
	    boolean ab_consultaPredio
	)
	    throws B2BException
	{
		return findInfoTabsBng(
		    aopr_predioRegistro, as_userId, ab_anotacionesAcc, ab_anotacionesDashBoard, ab_consultaPredio, false
		);
	}

	/**
	 * Sobrecarga de método (<code>findInfoTabsBng</code>) encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param aopr_predioRegistro Objeto de tipo <code>PredioRegistro</code> que contiene id_circulo y id_matricula a consultar
	 * @param as_userId Objeto de tipo <code>java.lang.String</code> que contiene usuario que realiza la accion
	 * @param ab_anotacionesAcc Variable de tipo <code>boolean</code> que define si adicional de obtienen las anotaciones temporales
	 * @param ab_anotacionesDashBoard Variable de tipo <code>boolean</code> que define la forma en la que se obtienen las anotaciones (<code>true</code> -> dashBoard | <code>false</code> -> formulario )
	 * @return Objeto de tipo <code>ConsultaPredio</code> que contiene resultado de la consulta realizada
	 * @throws B2BException
	 */
	public synchronized ConsultaPredio findInfoTabsBng(
	    PredioRegistro aopr_predioRegistro, String as_userId, boolean ab_anotacionesAcc, boolean ab_anotacionesDashBoard,
	    boolean ab_consultaPredio, boolean ab_consulaTraza
	)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		ConsultaPredio          lcp_das;
		RegistroCalificacionDAO lrcd_rcd;
		DocumentoDAO            ldd_dao;
		AnotacionPredioDAO      lapd_DAO;
		DatosAntSistemaDAO      ldas_DAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcp_das         = new ConsultaPredio();
		lrcd_rcd        = DaoCreator.getRegistroCalificacionDAO(ldm_manager);
		ldd_dao         = DaoCreator.getDocumentoDAO(ldm_manager);
		ldas_DAO        = DaoCreator.getDatosAntSistemaDAO(ldm_manager);

		try
		{
			if(aopr_predioRegistro != null)
			{
				PredioRegistro lpr_predioRegistro;
				boolean        lb_consultaPredio;

				lapd_DAO              = DaoCreator.getAnotacionPredioDAO(ldm_manager);
				lb_consultaPredio     = aopr_predioRegistro.isConsultaPredio();

				lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
						                           .findByCirculoMatricula(aopr_predioRegistro);

				if(lpr_predioRegistro != null)
				{
					long   ll_idMatricula;
					Long   ll_idMatriculaL;
					String ls_idCirculo;

					ll_idMatricula      = lpr_predioRegistro.getIdMatricula();
					ll_idMatriculaL     = NumericUtils.getLongWrapper(ll_idMatricula);
					ls_idCirculo        = lpr_predioRegistro.getIdCirculo();

					lcp_das.setPredioRegistro(lpr_predioRegistro);

					{
						Collection<SalvedadPredio> lcsp_salvedades;

						lcsp_salvedades = DaoCreator.getSalvedadPredioDAO(ldm_manager)
								                        .findByCirculoMatricula(ls_idCirculo, ll_idMatriculaL);

						if(CollectionUtils.isValidCollection(lcsp_salvedades))
						{
							int li_id;

							li_id = 1;

							for(SalvedadPredio lsp_iterador : lcsp_salvedades)
							{
								if(lsp_iterador != null)
									lsp_iterador.setId(NumericUtils.getLongWrapper(li_id));

								li_id++;
							}
						}

						lcp_das.setSalvedadesPredioBng(lcsp_salvedades);
					}
					// TAB Alertas del predio
					{
						AnotacionPredioDAO                                                loap_DAO;
						AlertaNaturalezaJuridicaDAO                                       loanj_DAO;
						NaturalezaJuridicaDAO                                             lnjd_DAO;
						Collection<AlertaNaturalezaJuridica>                              lcanj_allInfo;
						Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> lcap_tmp;
						Map<String, Boolean>                                              lcs_gravamenes;
						Map<String, Boolean>                                              lcs_trasnferenciaDominio;
						AlertaNaturalezaJuridica                                          lontj_tmp;
						com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio             loap_tmp;
						String                                                            ls_grupoNaturaleza;
						String                                                            ls_idNaturaleza;
						String                                                            ls_idEstadoAnotacion;
						String                                                            ls_anotacionCancelada;
						long                                                              ll_version;

						lnjd_DAO                     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
						loap_DAO                     = DaoCreator.getAnotacionPredioDAO(ldm_manager);
						loanj_DAO                    = DaoCreator.getAlertaNaturalezaJuridicaDAO(ldm_manager);
						lcanj_allInfo                = new ArrayList<AlertaNaturalezaJuridica>();
						lcap_tmp                     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio>();
						lcs_gravamenes               = new HashMap<String, Boolean>();
						lcs_trasnferenciaDominio     = new HashMap<String, Boolean>();
						loap_tmp                     = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();

						lcs_gravamenes.put("0200", Boolean.TRUE);
						lcs_gravamenes.put("0400", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0100", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0300", Boolean.TRUE);
						lcs_trasnferenciaDominio.put("0600", Boolean.TRUE);

						loap_tmp.setIdCirculo(ls_idCirculo);
						loap_tmp.setIdMatricula(ll_idMatriculaL);

						lcap_tmp = loap_DAO.findNaturalezaByMatricula(loap_tmp);

						if(CollectionUtils.isValidCollection(lcap_tmp))
						{
							for(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio loapr_tmp : lcap_tmp)
							{
								if(loapr_tmp != null)
								{
									ls_grupoNaturaleza        = loapr_tmp.getGrupoNaturaleza();
									ls_idNaturaleza           = loapr_tmp.getIdNaturalezaJuridica();
									ll_version                = loapr_tmp.getVersion();
									ls_idEstadoAnotacion      = loapr_tmp.getIdEstadoAnotacion();
									ls_anotacionCancelada     = loapr_tmp.getAnotacionCancelada();

									if(BooleanUtils.getBooleanValue(lcs_gravamenes.get(ls_grupoNaturaleza)))
									{
										if(
										    StringUtils.getStringNotNull(loapr_tmp.getAnotacionCancelada())
											               .equalsIgnoreCase(EstadoCommon.N)
										)
										{
											lontj_tmp = loanj_DAO.findById(ls_idNaturaleza, ll_version);

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}

									else if(
									    BooleanUtils.getBooleanValue(lcs_trasnferenciaDominio.get(ls_grupoNaturaleza))
									)
									{
										com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio loap_info;

										loap_info = loap_DAO.findAllByTransferenciaDominio(loap_tmp);

										if(loap_info != null)
										{
											lontj_tmp = loanj_DAO.findById(ls_idNaturaleza, ll_version);

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}

									if(
									    StringUtils.isValidString(ls_idEstadoAnotacion)
										    && ls_idEstadoAnotacion.equalsIgnoreCase(EstadoCommon.A)
									)
									{
										NaturalezaJuridica lnj_naturalezaJuridica;

										lnj_naturalezaJuridica = lnjd_DAO.findById(ls_idNaturaleza, ll_version);

										if(lnj_naturalezaJuridica != null)
										{
											lontj_tmp = new AlertaNaturalezaJuridica();

											lontj_tmp.setIdNaturalezaJuridica(ls_idNaturaleza);
											lontj_tmp.setEspecificacion(lnj_naturalezaJuridica.getNombre());
											lontj_tmp.setNombreAlerta("ANOTACIÓN INVÁLIDA");

											if(lontj_tmp != null)
											{
												lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
												lcanj_allInfo.add(lontj_tmp);
											}
										}
									}

									if(
									    StringUtils.isValidString(ls_anotacionCancelada)
										    && ls_anotacionCancelada.equalsIgnoreCase(EstadoCommon.S)
									)
									{
										NaturalezaJuridica lnj_naturalezaJuridica;

										lnj_naturalezaJuridica = lnjd_DAO.findById(ls_idNaturaleza, ll_version);

										if(lnj_naturalezaJuridica != null)
										{
											String ls_idGrupoNaturalezaJuridica;

											ls_idGrupoNaturalezaJuridica = lnj_naturalezaJuridica.getIdGrupoNatJur();

											if(
											    StringUtils.isValidString(ls_idGrupoNaturalezaJuridica)
												    && (ls_idGrupoNaturalezaJuridica.equalsIgnoreCase("0700")
												    || ls_idGrupoNaturalezaJuridica.equalsIgnoreCase("0800"))
											)
											{
												lontj_tmp = new AlertaNaturalezaJuridica();

												lontj_tmp.setIdNaturalezaJuridica(ls_idNaturaleza);
												lontj_tmp.setEspecificacion(lnj_naturalezaJuridica.getNombre());
												lontj_tmp.setNombreAlerta("ANOTACIÓN CANCELADA");

												if(lontj_tmp != null)
												{
													lontj_tmp.setIdAnotacion(loapr_tmp.getIdAnotacion().toString());
													lcanj_allInfo.add(lontj_tmp);
												}
											}
										}
									}
								}
							}

							if(CollectionUtils.isValidCollection(lcanj_allInfo))
							{
								Collection<AlertaNaturalezaJuridica> lcanj_infoDef;

								lcanj_infoDef = new ArrayList<AlertaNaturalezaJuridica>();

								for(AlertaNaturalezaJuridica lanj_temp : lcanj_allInfo)
								{
									if(lanj_temp != null)
									{
										NaturalezaJuridica lta_naturalezaJuridica;

										lta_naturalezaJuridica = new NaturalezaJuridica();

										lta_naturalezaJuridica.setIdNaturalezaJuridica(
										    lanj_temp.getIdNaturalezaJuridica()
										);

										lta_naturalezaJuridica = lnjd_DAO.findByIdMaxVersion(lta_naturalezaJuridica);

										if(lta_naturalezaJuridica != null)
											lanj_temp.setEspecificacion(lta_naturalezaJuridica.getNombre());

										{
											Long ll_idAnotacion;

											ll_idAnotacion = NumericUtils.getLongWrapper(lanj_temp.getIdAnotacion());

											if(
											    StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula >= 0L)
												    && NumericUtils.isValidLong(ll_idAnotacion)
											)
											{
												com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

												lap_anotacionPredio = lapd_DAO.findById(
													    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
													    ll_idAnotacion
													);

												if(lap_anotacionPredio != null)
													lanj_temp.setOrden(lap_anotacionPredio.getOrden());
											}
										}

										lcanj_infoDef.add(lanj_temp);
									}
								}

								lcp_das.getInfoAlertas().setAllInfo(lcanj_infoDef);
							}
						}
					}
					//TAB DATOS BASICOS
					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = new DatosBasicos();

						ldb_datosBasicos.setPredioRegistro(lpr_predioRegistro);
						//UBICACION
						{
							ZonaRegistral lzr_zonaRegistral;
							String        ls_idZonaRegistral;

							ls_idZonaRegistral     = lpr_predioRegistro.getIdZonaRegistral();

							lzr_zonaRegistral = new ZonaRegistral();

							lzr_zonaRegistral.setIdZonaRegistral(ls_idZonaRegistral);

							lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(ldm_manager).findById(lzr_zonaRegistral);

							if(lzr_zonaRegistral != null)
							{
								UbicacionZonaRegistral luzrt_ubicacion;

								luzrt_ubicacion = new UbicacionZonaRegistral();

								{
									CirculoRegistral ls_circuloUbicacion;

									ls_circuloUbicacion = new CirculoRegistral();

									ls_circuloUbicacion.setIdCirculo(lzr_zonaRegistral.getIdCirculo());

									ls_circuloUbicacion = DaoCreator.getCirculoRegistralDAO(ldm_manager)
											                            .findById(ls_circuloUbicacion);

									if(ls_circuloUbicacion != null)
										luzrt_ubicacion.setCirculoRegistral(ls_circuloUbicacion);
								}

								{
									String ls_idPais;
									String ls_idDepartamento;
									String ls_idMunicipio;
									String ls_idVereda;

									ls_idPais             = lzr_zonaRegistral.getIdPais();
									ls_idDepartamento     = lzr_zonaRegistral.getIdDepartamento();
									ls_idMunicipio        = lzr_zonaRegistral.getIdMunicipio();
									ls_idVereda           = lzr_zonaRegistral.getIdVereda();

									{
										Pais lp_pais;

										lp_pais = new Pais();

										lp_pais.setIdPais(ls_idPais);

										lp_pais = DaoCreator.getPaisDAO(ldm_manager).findById(lp_pais);

										if(lp_pais != null)
											luzrt_ubicacion.setIdPais(lp_pais.getIdPais());
									}

									{
										Departamento ld_departamento;

										ld_departamento = new Departamento();

										ld_departamento.setIdPais(ls_idPais);
										ld_departamento.setIdDepartamento(ls_idDepartamento);

										ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager)
												                        .findById(ld_departamento);

										if(ld_departamento != null)
											luzrt_ubicacion.setDepartamento(ld_departamento);
									}

									{
										Municipio lm_municipio;

										lm_municipio = new Municipio();

										lm_municipio.setIdPais(ls_idPais);
										lm_municipio.setIdDepartamento(ls_idDepartamento);
										lm_municipio.setIdMunicipio(ls_idMunicipio);

										lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

										if(lm_municipio != null)
											luzrt_ubicacion.setMunicipio(lm_municipio);
									}

									{
										Vereda lv_vereda;
										lv_vereda = new Vereda();

										lv_vereda.setIdPais(ls_idPais);
										lv_vereda.setIdDepartamento(ls_idDepartamento);
										lv_vereda.setIdMunicipio(ls_idMunicipio);
										lv_vereda.setIdVereda(ls_idVereda);

										lv_vereda = DaoCreator.getVeredaDAO(ldm_manager).findById(lv_vereda);

										if(lv_vereda != null)
											luzrt_ubicacion.setVereda(lv_vereda);
									}
								}

								{
									EstadoPredio lsp_estadoPredio;

									lsp_estadoPredio = new EstadoPredio();

									lsp_estadoPredio.setIdEstadoPredio(lpr_predioRegistro.getIdEstadoPredio());

									lsp_estadoPredio = DaoCreator.getEstadoPredioDao(ldm_manager)
											                         .findById(lsp_estadoPredio);

									if(lsp_estadoPredio != null)
										luzrt_ubicacion.setEstadoPredio(lsp_estadoPredio);
								}

								luzrt_ubicacion.setNupre(lpr_predioRegistro.getNupre());
								luzrt_ubicacion.setZonaRegistral(lzr_zonaRegistral);

								ldb_datosBasicos.setUbicacion(luzrt_ubicacion);
							}
						}
						//APERTURA
						{
							Documento ld_documento;
							ld_documento = new Documento();

							ld_documento.setIdDocumento(lpr_predioRegistro.getIdDocumento());
							ld_documento.setVersionDocumento(
							    NumericUtils.getLongWrapper(lpr_predioRegistro.getVersionDocumento())
							);

							ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findByIdDocumentoVersion(
								    ld_documento
								);

							{
								Apertura lap_apertura;
								lap_apertura = new Apertura();

								lap_apertura.setFechaApertura(lpr_predioRegistro.getFechaApertura());
								lap_apertura.setRadicacion(lpr_predioRegistro.getRadicacion());

								if(ld_documento != null)
								{
									OficinaOrigen loo_oficinaOrigen;

									loo_oficinaOrigen = new OficinaOrigen();

									loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
									loo_oficinaOrigen.setVersion(ld_documento.getVersion());

									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                          .findById(loo_oficinaOrigen);

									if(loo_oficinaOrigen != null)
									{
										lap_apertura.setIdPais(loo_oficinaOrigen.getIdPais());
										lap_apertura.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
										lap_apertura.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
									}

									lap_apertura.setDocumento(ld_documento);
									lap_apertura.setIdTipoOficina(ld_documento.getIdTipoOficina());
									lap_apertura.setIdTipoEntidad(ld_documento.getTipoEntidad());
									lap_apertura.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
								}

								ldb_datosBasicos.setApertura(lap_apertura);
							}
						}
						//MATRICULA BASE
						{
							if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
							{
								Collection<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado> lcps_prediosSegregados;
								MatriculaBase                                                     lmd_matricula;
								com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado             lprs_predioSegreagdo;

								lmd_matricula            = new MatriculaBase();
								lprs_predioSegreagdo     = new com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado();

								lprs_predioSegreagdo.setIdCirculo1(ls_idCirculo);
								lprs_predioSegreagdo.setIdMatricula1(NumericUtils.getLongWrapper(ll_idMatricula));

								lcps_prediosSegregados = DaoCreator.getPredioSegregadoDAO(ldm_manager)
										                               .findAllByCirculo1Matricula1(
										    lprs_predioSegreagdo
										);

								if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
								{
									ldb_datosBasicos.setPredioSegregado(lcps_prediosSegregados);

									Collection<DireccionPredio> lcdp_cdp;

									lcdp_cdp = new ArrayList<DireccionPredio>();

									for(com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_Actual : lcps_prediosSegregados)
									{
										if(lps_Actual != null)
										{
											DireccionPredio ldp_direccionPredio;

											ldp_direccionPredio = new DireccionPredio();

											ldp_direccionPredio.setIdCirculo(lps_Actual.getIdCirculo());
											ldp_direccionPredio.setIdMatricula(lps_Actual.getIdMatricula());

											lmd_matricula.setIdCirculo(lps_Actual.getIdCirculo());
											lmd_matricula.setIdMatricula(lps_Actual.getIdMatricula());

											ldp_direccionPredio = DaoCreator.getDireccionPredioDAO(ldm_manager)
													                            .findById(ldp_direccionPredio);

											if(ldp_direccionPredio != null)
												lcdp_cdp.add(ldp_direccionPredio);
										}
									}

									if(CollectionUtils.isValidCollection(lcdp_cdp))
										lmd_matricula.setDireccionPredio(lcdp_cdp);
								}

								ldb_datosBasicos.setMatriculaBase(lmd_matricula);
							}
						}
						//INFORMACION CATASTRAL
						{
							ldb_datosBasicos.setCodigoCatastral(lpr_predioRegistro.getNumeroPredial());
							ldb_datosBasicos.setCodigoCatastralAnterior(lpr_predioRegistro.getNumeroPredialAnt());
						}
						//INFO ANTIGUO SISTEMA
						{
							DatosAntSistema ldas_datos;
							ldas_datos = new DatosAntSistema();

							ldas_datos.setIdCirculo(ls_idCirculo);
							ldas_datos.setIdMatricula(ll_idMatriculaL);

							ldas_datos = ldas_DAO.findByCirculoMatricula(ldas_datos);

							if(ldas_datos != null)
							{
								lcp_das.setAntiguoSistemaData(ldas_datos);

								DetalleAntSistema             ldas_detalle;
								Collection<DetalleAntSistema> lcdas_allDetalles;

								ldas_detalle = new DetalleAntSistema();

								ldas_detalle.setIdDatosAntSistema(ldas_datos.getIdDatosAntSistema());

								lcdas_allDetalles = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
										                          .findByDatosAntSis(ldas_detalle);

								if(CollectionUtils.isValidCollection(lcdas_allDetalles))
								{
									LibroAntiguoSistemaDao las_DAO;
									las_DAO = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);

									for(DetalleAntSistema ldas_temp : lcdas_allDetalles)
									{
										if(ldas_temp != null)
										{
											{
												LibroAntiguoSistema las_libro;
												las_libro = new LibroAntiguoSistema();

												las_libro.setIdLibroAntiguoSistema(
												    NumericUtils.getLong(ldas_temp.getIdLibroAntSistema())
												);

												las_libro = las_DAO.findById(las_libro);

												if(las_libro != null)
													ldas_temp.setNombreLibro(las_libro.getNombre());
											}

											{
												StringBuilder lsb_nombrePredioDetalle;

												lsb_nombrePredioDetalle = new StringBuilder();

												lsb_nombrePredioDetalle.append(
												    IdentificadoresCommon.PREDIO_MIN + " "
												    + IdentificadoresCommon.SIMBOLO_NUMERAL
												    + ldas_datos.getConsecutivoPredioAntSistema() + " "
												    + ldas_datos.getNombrePredio()
												);

												ldas_temp.setNombrePredio(lsb_nombrePredioDetalle.toString());
											}
										}
									}
								}

								lcp_das.setDetallesAntSistema(lcdas_allDetalles);
							}
						}

						lcp_das.setDatosbasicos(ldb_datosBasicos);
					}
					//TAB DESCRIPCION CABIDA Y LINDEROS
					{
						//LINDEROS
						{
							LinderoPredio    lalp_lindero;
							LinderoPredioDAO lalp_DAO;

							lalp_DAO         = DaoCreator.getLinderoPredioDAO(ldm_manager);
							lalp_lindero     = new LinderoPredio();

							lalp_lindero.setIdCirculo(ls_idCirculo);
							lalp_lindero.setIdMatricula(ll_idMatriculaL);

							lalp_lindero = lalp_DAO.findById(lalp_lindero);

							if(lalp_lindero != null)
								lcp_das.setLinderoPredio(lalp_lindero);
						}
						//COMPLEMENTACION
						{
							ComplementacionPredio lacp_complemento;
							Long                  ll_idComplementacion;

							ll_idComplementacion = NumericUtils.getLongWrapper(
								    lpr_predioRegistro.getIdComplementacion()
								);

							if(NumericUtils.isValidLong(ll_idComplementacion))
							{
								lacp_complemento = new ComplementacionPredio();

								lacp_complemento.setIdComplementacion(
								    String.valueOf(NumericUtils.getLong(ll_idComplementacion))
								);

								lacp_complemento = DaoCreator.getComplementacionPredioDAO(ldm_manager)
										                         .findById(lacp_complemento);

								if(lacp_complemento != null)
									lcp_das.setComplementacionPredio(lacp_complemento);
								else
								{
									ComplementacionPredio lcp_complemento;

									lcp_complemento = new ComplementacionPredio();

									lcp_complemento.setIdComplementacion(
									    String.valueOf(NumericUtils.getLong(ll_idComplementacion))
									);

									lcp_complemento = DaoCreator.getComplementacionPredioDAO(ldm_manager)
											                        .findById(lcp_complemento);

									if(lcp_complemento != null)
										lcp_das.setComplementacionPredio(lcp_complemento);
								}
							}
						}
					}

					//TAB AREA PREDIO
					if(!ab_consultaPredio)
					{
						com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio lap_ap;

						lap_ap = new com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio();

						lap_ap.setIdCirculo(ls_idCirculo);
						lap_ap.setIdMatricula(ll_idMatricula);

						lap_ap = DaoCreator.getAreaPredioDAO(ldm_manager).findById(lap_ap);

						if(lap_ap != null)
						{
							AccAreaUI     laaui_dataArea;
							AccAreaPredio laap_areaPredio;
							String        ls_idAreaPredio;

							laaui_dataArea      = new AccAreaUI();
							laap_areaPredio     = new AccAreaPredio();
							ls_idAreaPredio     = StringUtils.getString(lap_ap.getIdArea());

							if(StringUtils.isValidString(ls_idAreaPredio))
							{
								DetalleAreaPredioDAO ldap_DAO;
								MedidaAreaDAO        lma_DAO;

								ldap_DAO     = DaoCreator.getDetalleAreaPredioDAO(ldm_manager);
								lma_DAO      = DaoCreator.getMedidaAreaDAO(ldm_manager);

								laap_areaPredio.setIdArea(ls_idAreaPredio);

								{
									Collection<DetalleAreaPredio> lcdap_areasTerreno;
									DetalleAreaPredio             ldap_areaTerreno;

									ldap_areaTerreno = new DetalleAreaPredio();

									ldap_areaTerreno.setIdTipoArea(TipoAreaCommon.TERRENO);
									ldap_areaTerreno.setIdMatricula(ll_idMatriculaL);
									ldap_areaTerreno.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaTerreno.setIdCirculo(ls_idCirculo);

									lcdap_areasTerreno = ldap_DAO.findAllByIdAreaPredioTipo(ldap_areaTerreno);

									if(CollectionUtils.isValidCollection(lcdap_areasTerreno))
									{
										int                         li_tam;
										int                         li_count;
										Iterator<DetalleAreaPredio> lidap_iterator;
										StringBuilder               lsb_sb;

										li_tam             = lcdap_areasTerreno.size();
										li_count           = 1;
										lidap_iterator     = lcdap_areasTerreno.iterator();
										lsb_sb             = new StringBuilder();

										while(lidap_iterator.hasNext())
										{
											DetalleAreaPredio ldap_iterador;

											ldap_iterador = lidap_iterator.next();

											if(ldap_iterador != null)
											{
												Double ld_area;

												ld_area = ldap_iterador.getArea();

												if(NumericUtils.isValidDouble(ld_area))
												{
													String ls_medidaArea;
													String ls_separador;
													String ls_unidadMedida;

													ls_medidaArea       = ldap_iterador.getIdUnidadMedida();
													ls_separador        = (li_tam != li_count) ? ", " : "";
													ls_unidadMedida     = "";

													if(StringUtils.isValidString(ls_medidaArea))
													{
														MedidaArea lma_medidaArea;

														lma_medidaArea = lma_DAO.findById(ls_medidaArea);

														if(lma_medidaArea != null)
														{
															String ls_codigo;

															ls_codigo = lma_medidaArea.getCodigo();

															if(StringUtils.isValidString(ls_codigo))
																ls_unidadMedida = " " + ls_codigo;
														}
													}

													lsb_sb.append(ld_area);
													lsb_sb.append(ls_unidadMedida);
													lsb_sb.append(ls_separador);
												}
											}

											li_count++;
										}

										laaui_dataArea.setAreasTerreno(lcdap_areasTerreno);
										laaui_dataArea.setAreaTerreno(lsb_sb.toString());
									}
								}

								{
									DetalleAreaPredio ldap_areaConstruida;

									ldap_areaConstruida = new DetalleAreaPredio();

									ldap_areaConstruida.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);
									ldap_areaConstruida.setIdMatricula(ll_idMatriculaL);
									ldap_areaConstruida.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaConstruida.setIdCirculo(ls_idCirculo);

									ldap_areaConstruida = ldap_DAO.findByIdAreaPredioTipo(ldap_areaConstruida);

									if(ldap_areaConstruida != null)
									{
										Double ld_area;

										ld_area = ldap_areaConstruida.getArea();

										if(NumericUtils.isValidDouble(ld_area))
										{
											String        ls_medidaArea;
											StringBuilder lsb_sb;

											ls_medidaArea     = ldap_areaConstruida.getIdUnidadMedida();
											lsb_sb            = new StringBuilder();

											lsb_sb.append(ld_area);

											if(StringUtils.isValidString(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;

												lma_medidaArea = lma_DAO.findById(ls_medidaArea);

												if(lma_medidaArea != null)
												{
													String ls_codigo;

													ls_codigo = lma_medidaArea.getCodigo();

													if(StringUtils.isValidString(ls_codigo))
														lsb_sb.append(" " + ls_codigo);
												}
											}

											ldap_areaConstruida.setAreaLectura(lsb_sb.toString());
										}
									}

									laaui_dataArea.setDetalleAreaConstruida(ldap_areaConstruida);
								}

								{
									DetalleAreaPredio ldap_areaPrivada;

									ldap_areaPrivada = new DetalleAreaPredio();

									ldap_areaPrivada.setIdTipoArea(TipoAreaCommon.PRIVADA);
									ldap_areaPrivada.setIdMatricula(ll_idMatriculaL);
									ldap_areaPrivada.setIdAreaPredio(ls_idAreaPredio);
									ldap_areaPrivada.setIdCirculo(ls_idCirculo);

									ldap_areaPrivada = ldap_DAO.findByIdAreaPredioTipo(ldap_areaPrivada);

									if(ldap_areaPrivada != null)
									{
										Double ld_area;

										ld_area = ldap_areaPrivada.getArea();

										if(NumericUtils.isValidDouble(ld_area))
										{
											String        ls_medidaArea;
											StringBuilder lsb_sb;

											ls_medidaArea     = ldap_areaPrivada.getIdUnidadMedida();
											lsb_sb            = new StringBuilder();

											lsb_sb.append(ld_area);

											if(StringUtils.isValidString(ls_medidaArea))
											{
												MedidaArea lma_medidaArea;

												lma_medidaArea = lma_DAO.findById(ls_medidaArea);

												if(lma_medidaArea != null)
												{
													String ls_codigo;

													ls_codigo = lma_medidaArea.getCodigo();

													if(StringUtils.isValidString(ls_codigo))
														lsb_sb.append(" " + ls_codigo);
												}
											}

											ldap_areaPrivada.setAreaLectura(lsb_sb.toString());
										}
									}

									laaui_dataArea.setDetalleAreaPrivada(ldap_areaPrivada);
								}

								{
									Double ld_tmp;

									ld_tmp = NumericUtils.getDoubleWrapper(lap_ap.getCoeficiente());

									if(NumericUtils.isValidDouble(ld_tmp))
										laap_areaPredio.setCoeficiente(ld_tmp);
								}

								{
									String ls_tmp;

									ls_tmp = lpr_predioRegistro.getIdTipoUsoSuelo();

									if(StringUtils.isValidString(ls_tmp))
										laap_areaPredio.setTipoSuelo(ls_tmp);
								}
							}

							laaui_dataArea.setAreaPredio(laap_areaPredio);
							lcp_das.setAreaUI(laaui_dataArea);
						}
					}
					else
						lcp_das.setAreaUI(generarDatosAreaPredio(ldm_manager, ls_idCirculo, ll_idMatricula));

					{
						AccAreaUI laui_areaUI;

						laui_areaUI = lcp_das.getAreaUI();

						if(laui_areaUI != null)
						{
							Collection<DetalleAreaPredio> lcdap_detalleArea;

							lcdap_detalleArea = laui_areaUI.getAreasTerreno();

							if(CollectionUtils.isValidCollection(lcdap_detalleArea))
							{
								AreaPredio lap_areaPredio;

								lap_areaPredio = new AreaPredio();

								for(DetalleAreaPredio ldap_temp : lcdap_detalleArea)
								{
									if(ldap_temp != null)
									{
										String ls_tipoArea;

										ls_tipoArea = ldap_temp.getIdTipoArea();

										switch(ls_tipoArea)
										{
											case TipoAreaCommon.TERRENO:
												lap_areaPredio.setNombreAreaTerreno(ldap_temp.getAreaLectura());

												break;

											case TipoAreaCommon.CONSTRUIDA:
												lap_areaPredio.setNombreAreaConstruida(ldap_temp.getAreaLectura());

												break;

											case TipoAreaCommon.PRIVADA:
												lap_areaPredio.setNombreAreaPrivada(ldap_temp.getAreaLectura());

												break;

											case TipoAreaCommon.COEFICIENTE:
												lap_areaPredio.setCoeficiente(
												    NumericUtils.getBigDecimal(ldap_temp.getAreaLectura())
												);

												break;

											case TipoAreaCommon.ANOTACION:
												lap_areaPredio.setIdAnotacion(
												    NumericUtils.getBigDecimal(ldap_temp.getAreaLectura())
												);

											default:
												break;
										}
									}
								}

								if(lap_areaPredio != null)
								{
									Long ll_idAnotacion;

									ll_idAnotacion = NumericUtils.getLongWrapper(lap_areaPredio.getIdAnotacion());

									if(
									    StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula >= 0L)
										    && NumericUtils.isValidLong(ll_idAnotacion)
									)
									{
										com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

										lap_anotacionPredio = lapd_DAO.findById(
											    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
											    ll_idAnotacion
											);

										if(lap_anotacionPredio != null)
											lap_areaPredio.setOrden(lap_anotacionPredio.getOrden());
									}

									Collection<AreaPredio> lcap_areaPredio;

									lcap_areaPredio = new ArrayList<AreaPredio>();

									lcap_areaPredio.add(lap_areaPredio);
									lcp_das.setDatosAreaPredio(lcap_areaPredio);
								}
							}
						}
					}
					//TAB DIRECCION PREDIO
					{
						DireccionPredio                ldpa_direccionActual;
						Collection<DireccionDelPredio> lcddp_ddp;

						lcddp_ddp                = new ArrayList<DireccionDelPredio>();
						ldpa_direccionActual     = new DireccionPredio();

						ldpa_direccionActual.setIdCirculo(ls_idCirculo);
						ldpa_direccionActual.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
						ldpa_direccionActual.setConsultaPredio(lb_consultaPredio);

						ldpa_direccionActual = DaoCreator.getDireccionPredioDAO(ldm_manager)
								                             .findById(ldpa_direccionActual);

						if(ldpa_direccionActual != null)
						{
							DireccionDelPredio lddp_ddp;

							if(lb_consultaPredio)
							{
								Collection<DireccionPredio> lcdp_cdp;

								lcdp_cdp = ldpa_direccionActual.getDireccionesPredio();

								if(CollectionUtils.isValidCollection(lcdp_cdp))
								{
									for(DireccionPredio ldp_tmp : lcdp_cdp)
									{
										if(ldp_tmp != null)
										{
											lddp_ddp = formarDireccion(
												    ldp_tmp, ldm_manager, lcp_das, lpr_predioRegistro
												);

											lcddp_ddp.add(lddp_ddp);
										}
									}
								}
							}
							else
							{
								lddp_ddp = formarDireccion(
									    ldpa_direccionActual, ldm_manager, lcp_das, lpr_predioRegistro
									);
								lcddp_ddp.add(lddp_ddp);
							}
						}

						if(CollectionUtils.isValidCollection(lcddp_ddp))
							lcp_das.setDireccionesDelPredio(lcddp_ddp);
					}
					//TAB ANOTACIONES
					{
						if(ab_anotacionesDashBoard)
						{
							//DETALLE ANOTACIONES
							RegistroCalificacion             lorc_rc;
							int                              li_totalAnotaciones;
							int                              li_contadorEliminadas;
							Collection<RegistroCalificacion> lcrc_dataFinal;
							Collection<RegistroCalificacion> lcrc_dataPredio;

							lorc_rc                   = new RegistroCalificacion();
							lcrc_dataFinal            = new ArrayList<RegistroCalificacion>();
							li_totalAnotaciones       = 0;
							li_contadorEliminadas     = 0;

							lorc_rc.setIdCirculo(ls_idCirculo);
							lorc_rc.setIdMatricula(ll_idMatriculaL);

							lcrc_dataPredio = lapd_DAO.findDataPredio(lorc_rc);

							if(CollectionUtils.isValidCollection(lcrc_dataPredio))
							{
								li_totalAnotaciones = li_totalAnotaciones + lcrc_dataPredio.size();

								for(RegistroCalificacion lorc_rct : lcrc_dataPredio)
								{
									if(lorc_rct != null)
									{
										String ls_idAnotacion;
										String ls_idDocumento;
										String ls_idDatosAntSistema;

										ls_idAnotacion           = StringUtils.getString(lorc_rct.getIdAnotacion());
										ls_idDocumento           = lorc_rct.getIdDocumento();
										ls_idDatosAntSistema     = lorc_rct.getIdDatosAntSistema();

										if(StringUtils.isValidString(ls_idAnotacion))
										{
											com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion             lac_anotacionCancelacion;
											com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio                  lap_anotacionPredio;
											Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion> lcac_anotacionesCanceladas;
											Collection<RegistroCalificacion>                                       lcrc_dataPredioCiudadano;
											Long                                                                   ll_idAnotacion;

											lac_anotacionCancelacion     = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion();
											lap_anotacionPredio          = new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio();
											ll_idAnotacion               = NumericUtils.getLongWrapper(ls_idAnotacion);

											lac_anotacionCancelacion.setIdCirculo(ls_idCirculo);
											lac_anotacionCancelacion.setIdMatricula(ll_idMatricula);
											lac_anotacionCancelacion.setIdAnotacion(
											    NumericUtils.getLong(ls_idAnotacion)
											);

											lap_anotacionPredio.setIdCirculo(ls_idCirculo);
											lap_anotacionPredio.setIdMatricula(ll_idMatriculaL);
											lap_anotacionPredio.setIdAnotacion(ll_idAnotacion);

											lcrc_dataPredioCiudadano = lrcd_rcd.findDataPredioAnotacionBng(
												    lap_anotacionPredio
												);

											if(CollectionUtils.isValidCollection(lcrc_dataPredioCiudadano))
												lorc_rct.setAllMatriculas(lcrc_dataPredioCiudadano);

											lcac_anotacionesCanceladas = DaoCreator.getBngAnotacionCancelacionDAO(
												    ldm_manager
												).findByAnotaciones(lac_anotacionCancelacion);

											if(CollectionUtils.isValidCollection(lcac_anotacionesCanceladas))
											{
												Iterator<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion> liac_iterador;
												String                                                               ls_cancelaciones;

												liac_iterador        = lcac_anotacionesCanceladas.iterator();
												ls_cancelaciones     = "";

												while(liac_iterador.hasNext())
												{
													com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion lac_anotacionCancelacionIterador;

													lac_anotacionCancelacionIterador = liac_iterador.next();

													if(lac_anotacionCancelacionIterador != null)
													{
														long ll_anotacionCancelacion;

														ll_anotacionCancelacion = lac_anotacionCancelacionIterador
																.getIdAnotacion1();

														if(ll_anotacionCancelacion >= 0)
															ls_cancelaciones += ll_anotacionCancelacion;
													}
												}

												if(StringUtils.isValidString(ls_cancelaciones))
													lorc_rct.setAnotacionCancelacion(ls_cancelaciones);
											}

											{
												com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredioInv;

												lap_anotacionPredioInv = lapd_DAO.findById(
													    ls_idCirculo, ll_idMatriculaL, ll_idAnotacion
													);

												if(lap_anotacionPredioInv != null)
												{
													String ls_invalido;

													ls_invalido = lap_anotacionPredioInv.getIdEstadoAnotacion();

													if(
													    StringUtils.isValidString(ls_invalido)
														    && ls_invalido.equalsIgnoreCase(EstadoCommon.A)
													)
														lorc_rct.setAnotacionInvalida(true);
												}
											}
										}

										if(StringUtils.isValidString(ls_idDocumento))
										{
											RegistroCalificacion lorc_dataDocumento;

											lorc_dataDocumento = ldd_dao.detalleDocumento(ls_idDocumento);

											if(lorc_dataDocumento != null)
												lorc_rct.setDatosDocumento(lorc_dataDocumento);
										}

										boolean lb_datosAntSistema;
										lb_datosAntSistema = StringUtils.isValidString(ls_idDatosAntSistema);

										{
											if(lb_datosAntSistema)
											{
												DatosAntSistema lodas_das;

												lodas_das = new DatosAntSistema();

												lodas_das.setIdDatosAntSistema(ls_idDatosAntSistema);

												lodas_das = ldas_DAO.findById(lodas_das);

												if(lodas_das != null)
												{
													lcp_das.setDatosAntSistemaValid(true);

													Long ll_libro;

													ll_libro = lodas_das.getIdLibroAntSistema();

													if(NumericUtils.isValidLong(ll_libro))
													{
														LibroAntiguoSistema llas_las;

														llas_las = new LibroAntiguoSistema();

														llas_las.setIdLibroAntiguoSistema(
														    NumericUtils.getLong(ll_libro)
														);

														llas_las = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager)
																                 .findById(llas_las);

														if(llas_las != null)
															lodas_das.setNombreLibro(llas_las.getNombre());
													}

													{
														PredioTipo lpt_tmp;

														lpt_tmp = new PredioTipo();

														lpt_tmp.setIdTipoPredio(lodas_das.getIdTipoPredio());

														lpt_tmp = DaoCreator.getPredioTipoDao(ldm_manager)
																                .findById(lpt_tmp);

														if(lpt_tmp != null)
															lodas_das.setIdTipoPredio(lpt_tmp.getDescripcion());
													}

													lorc_rct.setAntiguoSistemaData(lodas_das);
												}
											}
										}

										lorc_rct.setDatosValidosAntSistema(lb_datosAntSistema);
										lorc_rct.setAnotacionAntiguoSistema(false);

										lcrc_dataFinal.add(lorc_rct);
									}
								}

								//DETALLE ANOTACIONES ACC
								if(ab_anotacionesAcc)
								{
									String ls_idTurno;

									ls_idTurno = aopr_predioRegistro.getTurnoBloqueo();

									if(StringUtils.isValidString(ls_idTurno))
									{
										Turno lt_turno;

										lt_turno = new Turno();

										lt_turno.setIdTurno(ls_idTurno);

										lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

										if(lt_turno != null)
										{
											lorc_rc = new RegistroCalificacion();

											lorc_rc.setIdCirculo(ls_idCirculo);
											lorc_rc.setIdMatricula(ll_idMatriculaL);
											lorc_rc.setTurno(lt_turno.getIdTurno());

											lcrc_dataPredio = DaoCreator.getAccAnotacionPredioDAO(ldm_manager)
													                        .findDataPredio(lorc_rc);

											if(CollectionUtils.isValidCollection(lcrc_dataPredio))
											{
												li_totalAnotaciones = li_totalAnotaciones + lcrc_dataPredio.size();

												for(RegistroCalificacion lorc_rct : lcrc_dataPredio)
												{
													if(lorc_rct != null)
													{
														String  ls_idAnotacion;
														String  ls_idDocumento;
														String  ls_idDatosAntSistema;
														boolean lb_repetida;

														ls_idAnotacion           = lorc_rct.getIdAnotacionPredio();
														ls_idDocumento           = lorc_rct.getIdDocumento();
														ls_idDatosAntSistema     = lorc_rct.getIdDatosAntSistema();
														lb_repetida              = false;

														if(StringUtils.isValidString(ls_idAnotacion))
														{
															Collection<RegistroCalificacion> lcrc_dataPredioCiudadano;
															Long                             ll_idAnotacion;

															ll_idAnotacion     = NumericUtils.getLongWrapper(
																    ls_idAnotacion
																);

															lcrc_dataPredioCiudadano = DaoCreator.getRegistroCalificacionDAO(
																    ldm_manager
																).findDataPredioAnotacion(ll_idAnotacion);

															if(
															    CollectionUtils.isValidCollection(
																        lcrc_dataPredioCiudadano
																    )
															)
																lorc_rct.setAllMatriculas(lcrc_dataPredioCiudadano);
														}

														if(StringUtils.isValidString(ls_idDocumento))
														{
															RegistroCalificacion lorc_dataDocumento;

															lorc_dataDocumento = DaoCreator.getDocumentoDAO(
																    ldm_manager
																).detalleDocumento(ls_idDocumento);

															if(lorc_dataDocumento != null)
																lorc_rct.setDatosDocumento(lorc_dataDocumento);
														}

														boolean lb_datosAntSistema;
														lb_datosAntSistema = StringUtils.isValidString(
															    ls_idDatosAntSistema
															);

														{
															if(lb_datosAntSistema)
															{
																DatosAntSistema lodas_das;

																lodas_das = new DatosAntSistema();

																lodas_das.setIdDatosAntSistema(ls_idDatosAntSistema);

																lodas_das = ldas_DAO.findById(lodas_das);

																if(lodas_das != null)
																{
																	lcp_das.setDatosAntSistemaValid(true);

																	Long ll_libro;

																	ll_libro = lodas_das.getIdLibroAntSistema();

																	if(NumericUtils.isValidLong(ll_libro))
																	{
																		LibroAntiguoSistema llas_las;

																		llas_las = new LibroAntiguoSistema();

																		llas_las.setIdLibroAntiguoSistema(
																		    NumericUtils.getLong(ll_libro)
																		);

																		llas_las = DaoCreator.getLibroAntiguoSistemaDAO(
																			    ldm_manager
																			).findById(llas_las);

																		if(llas_las != null)
																			lodas_das.setNombreLibro(
																			    llas_las.getNombre()
																			);
																	}

																	{
																		PredioTipo lpt_tmp;

																		lpt_tmp = new PredioTipo();

																		lpt_tmp.setIdTipoPredio(
																		    lodas_das.getIdTipoPredio()
																		);

																		lpt_tmp = DaoCreator.getPredioTipoDao(
																			    ldm_manager
																			).findById(lpt_tmp);

																		if(lpt_tmp != null)
																			lodas_das.setIdTipoPredio(
																			    lpt_tmp.getDescripcion()
																			);
																	}

																	lorc_rct.setAntiguoSistemaData(lodas_das);
																}
															}
														}

														lorc_rct.setDatosValidosAntSistema(lb_datosAntSistema);
														lorc_rct.setAnotacionAntiguoSistema(ab_anotacionesAcc);

														if(ab_consulaTraza)
														{
															Iterator<RegistroCalificacion> lirc_iterator;

															lirc_iterator = lcrc_dataFinal.iterator();

															if(lirc_iterator != null)
															{
																while(lirc_iterator.hasNext() && !lb_repetida)
																{
																	RegistroCalificacion lrc_temp;

																	lrc_temp = lirc_iterator.next();

																	if(lrc_temp != null)
																	{
																		Long   ll_idAnotacionAgregar;
																		Long   ll_idAnotacionTemporal;
																		String ls_radicacionAgregar;
																		String ls_radicacionTemporal;
																		String ls_naturalezaAgregar;
																		String ls_naturalezaTemporal;

																		ll_idAnotacionAgregar      = lorc_rct
																				.getIdAnotacion();
																		ll_idAnotacionTemporal     = lrc_temp
																				.getIdAnotacion();
																		ls_radicacionAgregar       = lorc_rct
																				.getRadicacion();
																		ls_radicacionTemporal      = lrc_temp
																				.getRadicacion();
																		ls_naturalezaAgregar       = lorc_rct.getCodActo();
																		ls_naturalezaTemporal      = lrc_temp.getCodActo();

																		lb_repetida = NumericUtils.isValidLong(
																			    ll_idAnotacionAgregar
																			)
																				&& NumericUtils.isValidLong(
																				    ll_idAnotacionTemporal
																				)
																				&& ll_idAnotacionAgregar.equals(
																				    ll_idAnotacionAgregar
																				)
																				&& StringUtils.isValidString(
																				    ls_naturalezaAgregar
																				)
																				&& StringUtils.isValidString(
																				    ls_naturalezaTemporal
																				)
																				&& ls_naturalezaAgregar.equalsIgnoreCase(
																				    ls_naturalezaTemporal
																				)
																				&& StringUtils.isValidString(
																				    ls_radicacionAgregar
																				)
																				&& StringUtils.isValidString(
																				    ls_radicacionTemporal
																				)
																				&& ls_radicacionAgregar.equalsIgnoreCase(
																				    ls_radicacionTemporal
																				);
																	}
																}
															}
														}

														if(!lb_repetida)
															lcrc_dataFinal.add(lorc_rct);
														else
															li_contadorEliminadas++;
													}
												}
											}

											lcp_das.setIdTurno(lt_turno.getIdTurno());
										}
									}
								}
							}

							lcp_das.setTotalAnotaciones(
							    ab_consulaTraza ? (li_totalAnotaciones - li_contadorEliminadas) : li_totalAnotaciones
							);

							if(CollectionUtils.isValidCollection(lcrc_dataFinal))
								lcp_das.getAnotaciones().setAllMatriculas(lcrc_dataFinal);
						}
						else
						{
							Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio> lcap_datos;
							Anotacion                                                         la_anotacion;
							Collection<Anotacion>                                             lca_anotacionesAgregadas;
							DocumentoDAO                                                      ldd_DAO;
							OficinaOrigenDAO                                                  lood_DAO;
							TipoOficinaDAO                                                    ltod_DAO;
							TipoEntidadDAO                                                    lted_DAO;
							DatosAntSistemaDAO                                                ldasd_dAO;
							AnotacionPredioCiudadanoDAO                                       lapcd_DAO;
							PersonaDAO                                                        lpd_DAO;
							PredioSegregadoDAO                                                lpsd_DAO;
							AnotacionCancelacionDAO                                           lacd_DAO;
							NaturalezaJuridicaDAO                                             lnjd_DAO;

							lca_anotacionesAgregadas     = new ArrayList<Anotacion>();
							la_anotacion                 = new Anotacion();
							lcap_datos                   = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio>();
							lnjd_DAO                     = DaoCreator.getNaturalezaJuridicaDAO(ldm_manager);
							lacd_DAO                     = DaoCreator.getBngAnotacionCancelacionDAO(ldm_manager);
							lpsd_DAO                     = DaoCreator.getPredioSegregadoDAO(ldm_manager);
							lpd_DAO                      = DaoCreator.getPersonaDAO(ldm_manager);
							lapcd_DAO                    = DaoCreator.getAnotacionPredioCiudadanoDAO(ldm_manager);
							ldasd_dAO                    = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
							lted_DAO                     = DaoCreator.getTipoEntidadDAO(ldm_manager);
							ltod_DAO                     = DaoCreator.getTipoOficinaDAO(ldm_manager);
							lood_DAO                     = DaoCreator.getOficinaOrigenDAO(ldm_manager);
							ldd_DAO                      = DaoCreator.getDocumentoDAO(ldm_manager);

//							SE CARGA LAS ANOTACIONES CREADAS PARA LA MATRICULA SELECCIONADA
							lcap_datos                   = lapd_DAO.findByCirculoMatricula(
								    new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio(
								        ls_idCirculo, ll_idMatriculaL
								    ), true
								);

							if(CollectionUtils.isValidCollection(lcap_datos))
							{
								SalvedadAnotacionDAO lsa_DAO;

								lsa_DAO = DaoCreator.getSalvedadAnotacionDAO(ldm_manager);

								for(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_actualBNG : lcap_datos)
								{
									if(lap_actualBNG != null)
									{
										long      ll_idAnotacion;
										Anotacion la_anotacionAgregada;

										la_anotacionAgregada     = new Anotacion();
										ll_idAnotacion           = NumericUtils.getLong(lap_actualBNG.getIdAnotacion());

										la_anotacionAgregada.setAnotacionPredio(new AnotacionPredio(lap_actualBNG));
//										SE CARGA LOS DOCUMENTOS PARA CADA ANOTACION
										{
											Documento ld_documento;

											ld_documento = new Documento();

											ld_documento.setIdDocumento(lap_actualBNG.getIdDocumento());
											ld_documento.setVersionDocumento(
											    NumericUtils.getLongWrapper(lap_actualBNG.getVersionDocumento())
											);

											ld_documento = ldd_DAO.findByIdDocumentoVersion(ld_documento);

											if(ld_documento != null)
											{
												OficinaOrigen loo_oficinaOrigen;

												loo_oficinaOrigen = new OficinaOrigen();

												loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
												loo_oficinaOrigen.setVersion(ld_documento.getVersion());

												loo_oficinaOrigen = lood_DAO.findById(loo_oficinaOrigen);

												if(loo_oficinaOrigen != null)
												{
													ld_documento.setIdPais(loo_oficinaOrigen.getIdPais());
													ld_documento.setIdDepartamento(
													    loo_oficinaOrigen.getIdDepartamento()
													);
													ld_documento.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());
												}

												{
													TipoOficina lto_to;

													lto_to = new TipoOficina();

													lto_to.setIdTipoOficina(ld_documento.getIdTipoOficina());

													lto_to = ltod_DAO.findById(lto_to);

													if(lto_to != null)
													{
														TipoEntidad lte_te;

														lte_te = new TipoEntidad();

														lte_te.setIdTipoEntidad(lto_to.getIdTipoEntidad());

														lte_te = lted_DAO.findById(lte_te);

														ld_documento.setTipoEntidad(lte_te.getIdTipoEntidad());
													}
												}

												la_anotacionAgregada.setDocumento(ld_documento);
											}
										}
//										SE CARGA LOS DATOS DE ANTIGUO SISTEMA PARA CADA ANOTACION
										{
											DatosAntSistema ldas_datosAntiguoSistema;

											ldas_datosAntiguoSistema = new DatosAntSistema();

											ldas_datosAntiguoSistema.setIdDatosAntSistema(
											    lap_actualBNG.getIdDatosAntSistema()
											);

											ldas_datosAntiguoSistema = ldasd_dAO.findById(ldas_datosAntiguoSistema);

											if(ldas_datosAntiguoSistema != null)
												la_anotacion.setDatosAntiguoSistema(ldas_datosAntiguoSistema);
										}
//										SE CARGA LOS DATOS DE LOS INTERVINIENTES PARA CADA ANOTACION
										{
											Collection<Anotacion>                                                      lca_intervinientes;
											Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano> lca_intervinientesBNG;

											lca_intervinientes     = new ArrayList<Anotacion>();

											lca_intervinientesBNG = lapcd_DAO.consultaPredioCiudadanos(
												    new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano(
												        ls_idCirculo, ll_idMatricula, ll_idAnotacion
												    )
												);

											if(CollectionUtils.isValidCollection(lca_intervinientesBNG))
											{
												long ll_contadorInterviniente;

												ll_contadorInterviniente = 1L;

												for(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano lapc_actual : lca_intervinientesBNG)
												{
													if(lapc_actual != null)
													{
														Anotacion la_Interviniente;

														la_Interviniente = new Anotacion();

														{
															Persona lp_persona;

															lp_persona = new Persona();

															lp_persona.setIdPersona(lapc_actual.getIdPersona());

															lp_persona = lpd_DAO.findById(lp_persona);

															if(lp_persona != null)
																la_Interviniente.setPersona(lp_persona);
														}

														{
															SolicitudInterviniente lsi_solicitudInterviniente;

															lsi_solicitudInterviniente = new SolicitudInterviniente();

															lsi_solicitudInterviniente.setRolPersona(
															    lapc_actual.getRolPersona()
															);

															la_Interviniente.setSolicitudInterviniente(
															    lsi_solicitudInterviniente
															);
														}

														la_Interviniente.setAnotacionPredioCiudadano(
														    new AnotacionPredioCiudadano(lapc_actual)
														);
														la_Interviniente.setIdAnotacion(lapc_actual.getIdAnotacion());
														la_Interviniente.setContadorInterviniente(
														    ll_contadorInterviniente++
														);

														lca_intervinientes.add(la_Interviniente);
													}
												}

												if(CollectionUtils.isValidCollection(lca_intervinientes))
													la_anotacionAgregada.setIntervinientesAgregados(lca_intervinientes);
											}
										}
//										SE CARGA LOS DATOS DE LOS PREDIO SEGREGADOS PARA CADA ANOTACION
										{
											Collection<Anotacion>                                             lca_prediosSegregados;
											Collection<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado> lcps_prediosSegregados;

											lca_prediosSegregados      = new ArrayList<Anotacion>();
											lcps_prediosSegregados     = lpsd_DAO.findByCirculoMatriculaAnotacion(
												    new com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado(
												        ls_idCirculo, ll_idMatricula,
												        NumericUtils.getLongWrapper(ll_idAnotacion)
												    )
												);

											if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
											{
												for(com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_actual : lcps_prediosSegregados)
												{
													if(lps_actual != null)
													{
														Anotacion      la_predioSegregado;
														PredioRegistro lpr_predioSegregado;

														la_predioSegregado      = new Anotacion();
														lpr_predioSegregado     = new PredioRegistro();

														lpr_predioSegregado.setIdMatricula(
														    NumericUtils.getLong(lps_actual.getIdMatricula1())
														);
														lpr_predioSegregado.setIdCirculo(lps_actual.getIdCirculo1());

														la_predioSegregado.setPredioRegistro(lpr_predioSegregado);

														lca_prediosSegregados.add(la_predioSegregado);
													}
												}

												if(CollectionUtils.isValidCollection(lca_prediosSegregados))
													la_anotacionAgregada.setMatriculasSegregadas(lca_prediosSegregados);
											}
										}
//										SE CARGAN LAS ANOTACIONES DE CANCELACION PARA LA MATRICULA SELECCIONADA
										{
											Collection<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion> lac_anotacionCancelacion;

											lac_anotacionCancelacion     = new ArrayList<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion>();

											lac_anotacionCancelacion = lacd_DAO.findByAnotaciones(
												    new com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion(
												        ls_idCirculo, ll_idMatricula, ll_idAnotacion
												    )
												);

											if(CollectionUtils.isValidCollection(lac_anotacionCancelacion))
											{
												Iterator<com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion> li_iterator;

												li_iterator = lac_anotacionCancelacion.iterator();

												if(li_iterator != null)
													la_anotacionAgregada.setAnotacionCancelacion(
													    new AnotacionCancelacion(li_iterator.next())
													);
											}
										}
//										SE CARGAN LLAVE COMPUESTA PARA LA NATURALEZA JURIDICA DE CADA ANOTACION
										{
											String             ls_naturalezaJuridica;
											String             ls_idNaturalezaJuridica;
											String             ls_nombre;
											NaturalezaJuridica lnj_parametros;
											long               ls_version;

											lnj_parametros     = new NaturalezaJuridica();
											ls_nombre          = null;

											ls_idNaturalezaJuridica     = lap_actualBNG.getIdNaturalezaJuridica();
											ls_version                  = NumericUtils.getLong(
												    lap_actualBNG.getVersion()
												);

											lnj_parametros.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
											lnj_parametros.setVersion(ls_version);

											lnj_parametros = lnjd_DAO.findById(lnj_parametros);

											if(lnj_parametros != null)
												ls_nombre = lnj_parametros.getNombre();

											ls_naturalezaJuridica = ls_idNaturalezaJuridica
												+ IdentificadoresCommon.SIMBOLO_GUION + ls_version;

											lap_actualBNG.setIdNaturalezaJuridica(ls_naturalezaJuridica);

											la_anotacionAgregada.setAnotacionPredio(new AnotacionPredio(lap_actualBNG));
											la_anotacionAgregada.setNaturalezaJuridica(ls_nombre);
										}

										{
											DetalleAntSistema ldas_detalleAntiguoSistema;

											ldas_detalleAntiguoSistema = new DetalleAntSistema();

											ldas_detalleAntiguoSistema.setIdDetalleAntSistema(
											    lap_actualBNG.getIdDetalleAntSistema()
											);
											ldas_detalleAntiguoSistema.setIdDatosAntSistema(
											    lap_actualBNG.getIdDatosAntSistema()
											);

											la_anotacionAgregada.setDetalleAntSistema(ldas_detalleAntiguoSistema);
										}

										la_anotacionAgregada.setIdAnotacion(ll_idAnotacion);

										{
											SalvedadAnotacion lsa_salvedad;

											lsa_salvedad = lsa_DAO.find(
												    ls_idCirculo, ll_idMatriculaL,
												    NumericUtils.getLongWrapper(ll_idAnotacion)
												);

											if(lsa_salvedad != null)
												la_anotacionAgregada.setSalvedad(lsa_salvedad.getDescripcion());
										}

										lca_anotacionesAgregadas.add(la_anotacionAgregada);
									}
								}

								if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
									la_anotacion.setAnotacionesAgregadas(lca_anotacionesAgregadas);

								lcp_das.setAnotacion(la_anotacion);
							}
						}
					}
					// TAB SALVEDADES
					{
						Collection<ConsultaSalvedad> lccs_salvedades;
						ConsultaSalvedad             lcs_consultaSalvedad;
						SalvedadesDAO                ls_DAO;

						lcs_consultaSalvedad     = new ConsultaSalvedad();
						ls_DAO                   = DaoCreator.getSalvedadesDAO(ldm_manager);

						lcs_consultaSalvedad.setIdCirculo(ls_idCirculo);
						lcs_consultaSalvedad.setIdMatricula(ll_idMatriculaL);

						lccs_salvedades = ls_DAO.findCompuesto(lcs_consultaSalvedad);

						if(CollectionUtils.isValidCollection(lccs_salvedades))
						{
							for(ConsultaSalvedad lcs_temp : lccs_salvedades)
							{
								if(lcs_temp != null)
								{
									Date ld_fechaRegistro;

									ld_fechaRegistro = lcs_temp.getFechaRegistro();

									if(ld_fechaRegistro != null)
										lcs_temp.setFechaRegistroString(
										    StringUtils.getString(ld_fechaRegistro, FormatoFechaCommon.DIA_MES_ANIO)
										);

									Long ll_idAnotacion;

									ll_idAnotacion = lcs_temp.getIdAnotacion();

									if(
									    StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula >= 0L)
										    && NumericUtils.isValidLong(ll_idAnotacion)
									)
									{
										com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

										lap_anotacionPredio = lapd_DAO.findById(
											    ls_idCirculo, NumericUtils.getLongWrapper(ll_idMatricula),
											    ll_idAnotacion
											);

										if(lap_anotacionPredio != null)
											lcs_temp.setOrden(lap_anotacionPredio.getOrden());
									}
								}
							}

							lcp_das.setSalvedades(lccs_salvedades);
						}
					}
					// TAB SEGREGACIONES
					{
						com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado             lps_predioSegregado;
						PredioSegregadoDAO                                                lps_DAO;
						Collection<com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado> lcps_predios;

						lps_predioSegregado     = new com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado();
						lps_DAO                 = DaoCreator.getPredioSegregadoDAO(ldm_manager);

						lps_predioSegregado.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
						lps_predioSegregado.setIdCirculo(ls_idCirculo);

						lcps_predios = lps_DAO.findAllByCirculoMatricula(lps_predioSegregado);

						if(CollectionUtils.isValidCollection(lcps_predios))
						{
							for(com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado lps_temp : lcps_predios)
							{
								if(lps_temp != null)
								{
									String ls_idCirculoPredio;
									Long   ll_idAnotacionPredio;
									Long   ll_idMatriculaPredio;

									ll_idAnotacionPredio     = lps_temp.getIdAnotacion1();
									ls_idCirculoPredio       = lps_temp.getIdCirculo1();
									ll_idMatriculaPredio     = lps_temp.getIdMatricula1();

									if(
									    StringUtils.isValidString(ls_idCirculoPredio)
										    && NumericUtils.isValidLong(ll_idMatriculaPredio)
										    && NumericUtils.isValidLong(ll_idAnotacionPredio)
									)
									{
										com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio lap_anotacionPredio;

										lap_anotacionPredio = lapd_DAO.findById(
											    ls_idCirculoPredio, ll_idMatriculaPredio, ll_idAnotacionPredio
											);

										if(lap_anotacionPredio != null)
											lps_temp.setOrden(lap_anotacionPredio.getOrden());
									}
								}
							}

							lcp_das.setPrediosSegregados(lcps_predios);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInfoTabsBng", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcp_das;
	}

	/**
	 * Método de consulta con la base de datos encargado de encontrar la medida area
	 * @param acdap_detallesAreas con los parametros del área
	 * @param adm_manager con el manager de la transacción
	 * @throws B2BException
	 */
	public synchronized void llenarUnidadDeMedida(
	    Collection<DetalleAreaPredio> acdap_detallesAreas, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(CollectionUtils.isValidCollection(acdap_detallesAreas))
		{
			MedidaAreaDAO lmad_DAO;

			lmad_DAO = DaoCreator.getMedidaAreaDAO(adm_manager);

			for(DetalleAreaPredio ldap_temp : acdap_detallesAreas)
			{
				if(ldap_temp != null)
				{
					StringBuilder lsb_builder;
					MedidaArea    lma_temp;

					lsb_builder     = new StringBuilder(StringUtils.getString(ldap_temp.getArea()));
					lma_temp        = lmad_DAO.findById(new MedidaArea(ldap_temp.getIdUnidadMedida()));

					if(lma_temp != null)
					{
						lsb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);
						lsb_builder.append(lma_temp.getDescripcion());
						ldap_temp.setAreaLectura(lsb_builder.toString());
					}
				}
			}
		}
	}

	/**
	 * Método encargado de permitir modificar el ordenamiento de las anotaciones por medio de la fecha de la última anotación.
	 *
	 * @param aca_anotacionesAgregadas Argumento de tipo <code>Collection</code> que contiene las anotaciones a calcular.
	 * @param aap_anotacionPredio Argumento de tipo <code>Anotacion</code> que contiene los criterios necesarios para realizar el cálculo.
	 * @return Colección de anotaciones que contiene las anotaciones modificadas.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	public Collection<Anotacion> validarReordenamientoPorFechaAnotacion(
	    Collection<Anotacion> aca_anotacionesAgregadas, AnotacionPredio aap_anotacionPredio
	)
	    throws B2BException
	{
		Collection<Anotacion> lca_anotacionesAgregadas;
		DAOManager            ldm_manager;

		ldm_manager                  = DaoManagerFactory.getDAOManager();
		lca_anotacionesAgregadas     = null;

		try
		{
			if(CollectionUtils.isValidCollection(aca_anotacionesAgregadas) && (aap_anotacionPredio != null))
				lca_anotacionesAgregadas = validarReordenamientoPorFechaAnotacion(
					    aca_anotacionesAgregadas, aap_anotacionPredio, ldm_manager
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarReordenamientoPorFechaAnotacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lca_anotacionesAgregadas;
	}

	/**
	 * Método encargado de formar la dirección completa.
	*
	* @param adp_dp Objeto de tipo DireccionPredio que contiene los datos asociados alos tipos de eje de una dirección.
	* @param adm_manager Objeto encargado de manejar la transacción del método.
	* @param acp_cp Objeto de tipo Consulta Predio  la ubicación para una dirección determinada.
	* @param apr_predioRegistro Objeto de tipo Predio Registro que contiene el id para un predio determinado.
	* @return Dirección completa para  una matrícula determinado.
	* @throws B2BException
	*/
	private synchronized DireccionDelPredio formarDireccion(
	    DireccionPredio adp_dp, DAOManager adm_manager, ConsultaPredio acp_cp, PredioRegistro apr_predioRegistro
	)
	    throws B2BException
	{
		DireccionDelPredio ldp_dp;

		ldp_dp = new DireccionDelPredio();

		try
		{
			if(adp_dp != null)
			{
				{
					DatosAntSistema        ldas_datosAntiguoSistema;
					UbicacionZonaRegistral luzrt_ubicacion;

					ldas_datosAntiguoSistema     = new DatosAntSistema();
					luzrt_ubicacion              = acp_cp.getDatosbasicos().getUbicacion();

					if(luzrt_ubicacion != null)
					{
						String ls_idPais;

						ls_idPais = luzrt_ubicacion.getIdPais();

						if(StringUtils.isValidString(ls_idPais))
						{
							Pais lp_pais;

							lp_pais = new Pais();

							lp_pais.setIdPais(ls_idPais);

							lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(lp_pais);

							if(lp_pais != null)
								adp_dp.setPais(lp_pais.getNombre());

							ldas_datosAntiguoSistema.setIdPais(ls_idPais);
						}

						{
							Departamento ld_d;

							ld_d = luzrt_ubicacion.getDepartamento();

							if(ld_d != null)
							{
								String ls_dept;

								ldas_datosAntiguoSistema.setIdDepartamento(ld_d.getIdDepartamento());

								ls_dept = ld_d.getNombre();

								if(StringUtils.isValidString(ls_dept))
									adp_dp.setDepartamento(ls_dept);
							}
						}

						{
							Municipio lm_m;

							lm_m = luzrt_ubicacion.getMunicipio();

							if(lm_m != null)
							{
								ldas_datosAntiguoSistema.setIdMunicipio(lm_m.getIdMunicipio());

								String ls_municipio;

								ls_municipio = lm_m.getNombre();

								if(StringUtils.isValidString(ls_municipio))
									adp_dp.setMunicipio(ls_municipio);
							}
						}

						{
							Vereda lv_v;

							lv_v = luzrt_ubicacion.getVereda();

							if(lv_v != null)
								ldas_datosAntiguoSistema.setIdVereda(lv_v.getIdVereda());
						}
					}

					{
						PredioTipo lpt_predioTipo;
						lpt_predioTipo = new PredioTipo();

						lpt_predioTipo.setIdTipoPredio(apr_predioRegistro.getIdTipoPredio());

						PredioTipoDAO lpt_DAO;
						lpt_DAO     = DaoCreator.getPredioTipoDao(adm_manager);

						lpt_predioTipo = lpt_DAO.findById(lpt_predioTipo);

						if(lpt_predioTipo != null)
						{
							ldas_datosAntiguoSistema.setNombrePredio(lpt_predioTipo.getDescripcion());
							adp_dp.setTipoPredio(lpt_predioTipo.getDescripcion());
						}
					}

					ldas_datosAntiguoSistema.setIdTipoPredio(apr_predioRegistro.getIdTipoPredio());

					ldp_dp.setDatosAntiguoSistema(ldas_datosAntiguoSistema);
				}

				ldp_dp.setDireccionPredioBng(adp_dp);
				ldp_dp.setDireccion(adp_dp.getDireccion());
				ldp_dp.setIdDireccion(adp_dp.getIdDireccion());
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("formarDireccion", lb2be_e);

			throw lb2be_e;
		}

		return ldp_dp;
	}

	/**
	 *
	 * @param adm_manager Objeto de tipo <code>DAOManager</code> encargado de manejar la transacción del método.
	 * @param as_idCirculo Objeto de tipo <code>String</code> el cual será usado para realizar las consultas necesarias.
	 * @param al_idMatricula Objeto de tipo <code>long</code> el cual será usado para realizar las consultas necesarias.
	 * @return Objeto de tipo <code>AccAreaUI</code> lleno con el resultado de la consulta, si esta llegará a tener datos.
	 * @throws B2BException
	 */
	private AccAreaUI generarDatosAreaPredio(DAOManager adm_manager, String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		AccAreaUI laaui_return;

		laaui_return = null;

		if((adm_manager != null) && (as_idCirculo != null) && (al_idMatricula > 0))
		{
			AreaPredio lap_area;

			lap_area = DaoCreator.getAreaPredioDAO(adm_manager).findById(as_idCirculo, al_idMatricula);

			if(lap_area != null)
			{
				Collection<DetalleAreaPredio> lcdap_areas;
				DetalleAreaPredioDAO          ldpd_DAO;
				MedidaAreaDAO                 lmad_DAO;
				long                          ll_idArea;

				ll_idArea        = lap_area.getIdArea();
				ldpd_DAO         = DaoCreator.getDetalleAreaPredioDAO(adm_manager);
				lmad_DAO         = DaoCreator.getMedidaAreaDAO(adm_manager);
				lcdap_areas      = ldpd_DAO.findAllByIdAreaPredioTipo(
					    new DetalleAreaPredio(
					        as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), null,
					        StringUtils.getString(ll_idArea), TipoAreaCommon.TERRENO
					    )
					);
				laaui_return     = new AccAreaUI();

				if(CollectionUtils.isValidCollection(lcdap_areas))
				{
					if((lcdap_areas.size() > 1))
					{
						StringBuilder lsb_builder;
						int           li_contador;

						lsb_builder     = new StringBuilder();
						li_contador     = 1;

						for(DetalleAreaPredio lap_temp : lcdap_areas)
						{
							if(lap_temp != null)
							{
								MedidaArea lma_temp;

								lma_temp = lmad_DAO.findById(new MedidaArea(lap_temp.getIdUnidadMedida()));
								lsb_builder.append(lap_temp.getArea());
								lsb_builder.append(IdentificadoresCommon.ESPACIO_VACIO);

								if(lma_temp != null)
									lsb_builder.append(lma_temp.getDescripcion());

								if(li_contador == (lcdap_areas.size() - 1))
									lsb_builder.append(IdentificadoresCommon.SIMBOLO_COMA);

								li_contador++;
							}
						}

						lcdap_areas.clear();
						lcdap_areas.add(
						    new DetalleAreaPredio(
						        as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), lsb_builder.toString(),
						        TipoAreaCommon.TERRENO
						    )
						);
					}
					else
						llenarUnidadDeMedida(lcdap_areas, adm_manager);
				}

				{
					Collection<DetalleAreaPredio> lcdap_areasConstruidas;

					lcdap_areasConstruidas = ldpd_DAO.findAllByIdAreaPredioTipo(
						    new DetalleAreaPredio(
						        as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), null,
						        StringUtils.getString(ll_idArea), TipoAreaCommon.CONSTRUIDA
						    )
						);

					if(CollectionUtils.isValidCollection(lcdap_areasConstruidas))
					{
						llenarUnidadDeMedida(lcdap_areasConstruidas, adm_manager);
						lcdap_areas.addAll(lcdap_areasConstruidas);
					}
				}

				{
					Collection<DetalleAreaPredio> lcdap_areasPrivadas;

					lcdap_areasPrivadas = ldpd_DAO.findAllByIdAreaPredioTipo(
						    new DetalleAreaPredio(
						        as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), null,
						        StringUtils.getString(ll_idArea), TipoAreaCommon.PRIVADA
						    )
						);

					if(CollectionUtils.isValidCollection(lcdap_areasPrivadas))
					{
						llenarUnidadDeMedida(lcdap_areasPrivadas, adm_manager);
						lcdap_areas.addAll(lcdap_areasPrivadas);
					}
				}

				{
					Collection<DetalleAreaPredio> lcdap_areasParteRestante;

					lcdap_areasParteRestante = ldpd_DAO.findAllByIdAreaPredioTipo(
						    new DetalleAreaPredio(
						        as_idCirculo, NumericUtils.getLongWrapper(al_idMatricula), null,
						        StringUtils.getString(ll_idArea), TipoAreaCommon.PARTE_RESTANTE
						    )
						);

					if(CollectionUtils.isValidCollection(lcdap_areasParteRestante))
					{
						llenarUnidadDeMedida(lcdap_areasParteRestante, adm_manager);
						lcdap_areas.addAll(lcdap_areasParteRestante);
					}
				}

				{
					DetalleAreaPredio ldap_coeficiente;
					BigDecimal        lbd_coeficiente;
					String            ls_coeficiente;

					ldap_coeficiente     = new DetalleAreaPredio();
					lbd_coeficiente      = lap_area.getCoeficiente();
					ls_coeficiente       = "0";

					if(NumericUtils.isValidBigDecimal(lbd_coeficiente))
						ls_coeficiente = StringUtils.getString(lbd_coeficiente);

					ldap_coeficiente.setAreaLectura(ls_coeficiente);
					ldap_coeficiente.setIdTipoArea(TipoAreaCommon.COEFICIENTE);

					if(CollectionUtils.isValidCollection(lcdap_areas))
						lcdap_areas.add(ldap_coeficiente);
					else
					{
						lcdap_areas = new ArrayList<DetalleAreaPredio>();
						lcdap_areas.add(ldap_coeficiente);
					}
				}

				{
					DetalleAreaPredio ldap_anotacion;
					BigDecimal        lbd_anotacion;
					String            ls_anotacion;

					ldap_anotacion     = new DetalleAreaPredio();
					lbd_anotacion      = lap_area.getIdAnotacion();
					ls_anotacion       = IdentificadoresCommon.SIN_INFORMACION;

					if(NumericUtils.isValidBigDecimal(lbd_anotacion))
						ls_anotacion = StringUtils.getString(lbd_anotacion);

					ldap_anotacion.setAreaLectura(ls_anotacion);
					ldap_anotacion.setIdTipoArea(TipoAreaCommon.ANOTACION);

					if(CollectionUtils.isValidCollection(lcdap_areas))
						lcdap_areas.add(ldap_anotacion);
					else
					{
						lcdap_areas = new ArrayList<DetalleAreaPredio>();
						lcdap_areas.add(ldap_anotacion);
					}
				}

				laaui_return.setAreasTerreno(lcdap_areas);
			}
		}

		return laaui_return;
	}

	/**
	 *  Método encargado de permitir modificar el ordenamiento de las anotaciones por medio de la fecha de la última anotación.
	 *
	 * @param aca_anotacionesAgregadas Argumento de tipo <code>Collection</code> que contiene las anotaciones a calcular.
	 * @param aap_anotacionPredio Argumento de tipo <code>Anotacion</code> que contiene los criterios necesarios para realizar el cálculo.
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite realizar la conexión a la base de datos.
	 * @return Colección de anotaciones que contiene las anotaciones modificadas.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private Collection<Anotacion> validarReordenamientoPorFechaAnotacion(
	    Collection<Anotacion> aca_anotacionesAgregadas, AnotacionPredio aap_anotacionPredio, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(
			    CollectionUtils.isValidCollection(aca_anotacionesAgregadas) && (aap_anotacionPredio != null)
				    && (adm_manager != null)
			)
			{
				com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapd_DAO;
				Date                                               ld_fechaAnotacionMax;
				AnotacionPredio                                    lap_anotacionPredio;
				int                                                li_idAnotacionPredioMax;

				lapd_DAO                    = DaoCreator.getAccAnotacionPredioDAO(adm_manager);
				ld_fechaAnotacionMax        = null;
				lap_anotacionPredio         = new AnotacionPredio();
				li_idAnotacionPredioMax     = 0;

				lap_anotacionPredio.setIdCirculo(aap_anotacionPredio.getIdCirculo());
				lap_anotacionPredio.setIdMatricula(aap_anotacionPredio.getIdMatricula());
				lap_anotacionPredio.setIdTurno(aap_anotacionPredio.getIdTurno());

				li_idAnotacionPredioMax = lapd_DAO.consultarMaxIdAnotacion(lap_anotacionPredio);

				if(li_idAnotacionPredioMax > 0)
				{
					lap_anotacionPredio.setIdAnotacion(NumericUtils.getLongWrapper(li_idAnotacionPredioMax));

					lap_anotacionPredio = lapd_DAO.findByCirculoMatriculaTurnoAnotacion(lap_anotacionPredio);

					if(lap_anotacionPredio != null)
						ld_fechaAnotacionMax = lap_anotacionPredio.getFechaRadicacion();
				}

				for(Anotacion la_iterador : aca_anotacionesAgregadas)
				{
					if(la_iterador != null)
					{
						AnotacionPredio lap_anotacionPredioIterado;

						lap_anotacionPredioIterado = la_iterador.getAnotacionPredio();

						if(lap_anotacionPredioIterado != null)
						{
							Date ld_fechaAnotacionIterada;

							ld_fechaAnotacionIterada = lap_anotacionPredioIterado.getFechaRadicacion();

							la_iterador.setDeshabilitar(
							    (ld_fechaAnotacionMax != null) && (ld_fechaAnotacionIterada != null)
								    && ld_fechaAnotacionIterada.before(ld_fechaAnotacionMax)
								    && (ld_fechaAnotacionMax.compareTo(ld_fechaAnotacionIterada) != 0)
							);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarReordenamientoPorFechaAnotacion", lb2be_e);

			throw lb2be_e;
		}

		return aca_anotacionesAgregadas;
	}
}
