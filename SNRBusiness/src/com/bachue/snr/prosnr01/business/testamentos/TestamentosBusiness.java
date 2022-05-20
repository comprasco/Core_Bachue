package com.bachue.snr.prosnr01.business.testamentos;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TestamentoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.consulta.trazabilidad.ConsultaTrazabilidadDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoTestamentoDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;

import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoEntidad;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoTestamento;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;
import com.bachue.snr.prosnr01.model.ui.UsuarioActividadUI;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Clase que contiene los métodos de negocio para la fase de calificación para
 * el rol del calificador, por ejemplo buscar turnos para calificar, obtener la
 * matricula involucrada en el turno, obtener detalle de intervinientes, obtener
 * la lista de causales de devolución, guardar detalles de la anotación entre
 * otros.
 *
 * @author dbeltran.
 *
 */
public class TestamentosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(TestamentosBusiness.class);

	/** Constante mensaje Error*/
	private String is_mensajeError;

	/**
	 * Método  encargado de insertar información  del calificador que está asociado al usuario que ejecuta el proceso.
	 *
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la dirección ip remota desde donde se realiza la acción.
	 * @return  Retorna  un objeto de tipo Persona que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Persona getDatosUsuarioCalificador(String as_idUsuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    lp_persona;
		Usuario    lu_usuario;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lp_persona      = new Persona();
		lu_usuario      = new Usuario();

		try
		{
			UsuarioDAO lu_DAO;

			lu_DAO = DaoCreator.getUsuarioDAO(ldm_manager);

			lu_usuario.setIdUsuario(as_idUsuario);

			lu_usuario = lu_DAO.findById(lu_usuario);

			if(lu_usuario != null)
			{
				PersonaDAO lp_DAO;

				lp_DAO = DaoCreator.getPersonaDAO(ldm_manager);

				lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());

				lp_persona = lp_DAO.findDataCalificador(lp_persona);

				if(lp_persona != null)
					lp_persona = lp_DAO.findById(lp_persona);
				else
				{
					String ls_idFlagPersona;

					lp_persona = new Persona();

					lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
					lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());
					lp_persona.setIdTipoPersona(EstadoCommon.N);
					lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
					lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
					lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
					lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
					lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					lp_persona.setIdUsuario(lu_usuario.getIdUsuario());

					ls_idFlagPersona = marcarFlagPersona(ldm_manager, lp_persona, as_idUsuario, as_remoteIp);

					if(StringUtils.isValidString(ls_idFlagPersona))
						lp_persona = lp_DAO.findById(ls_idFlagPersona);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getDatosUsuarioCalificador", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_persona;
	}

	/**
	 *
	 * Método encargado de consultar los campos que se modificaron en el id turno historia anterior.
	 *
	 * @param as_idTurno Variable de tipo String que contiene el id turno para un proceso determinado.
	 * @return Retorna  una lista de datos de tipo LinkedHashMap<String, Object> que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Collection<Map<String, Object>> getIndicadoresByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<Map<String, Object>> lcmso_result;
		DAOManager                      ldm_manager;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcmso_result     = null;

		try
		{
			Map<Integer, Object> lhmp_criterio;

			lhmp_criterio = new LinkedHashMap<Integer, Object>();

			lhmp_criterio.put(NumericUtils.getInteger(1), as_idTurno);

			lcmso_result = DaoCreator.getUtilDAO(ldm_manager)
					                     .getCustonQuery(ConsultasUtilidades.CS_INDICADORES_CAMPOS, lhmp_criterio);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("getIndicadoresByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcmso_result;
	}

	/**
	 * Método de asiganación del valor de la propiedad mensaje error
	 * @param as_me con el valor a asignar
	 */
	public void setMensajeError(String as_me)
	{
		is_mensajeError = as_me;
	}

	/**
	 * Método de obtención del valor de la propiedad Mensaje Error
	 * @return
	 */
	public String getMensajeError()
	{
		return is_mensajeError;
	}

	/**
	 *      Metodo encargado de terminar una etapa y crear la siguiente según el motivo enviado.
	 *
	 * @param ath_parametros Argumento de tipo TurnoHistoria que contiene los argumentos necesarios para terminar y crear etapa.
	 * @param amt_motivoTramite Argumento de tipo MotivoTramite que contiene los argumentos necesarios para consultar el motivo tramite.
	 * @param as_usuario Argumento de tipo String que contiene el usuario que realiza la actualización.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip desde donde se realiza la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		if((ath_parametros != null) && (amt_motivoTramite != null))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				terminarTurnoHistoriaYCrearEtapa(
				    ath_parametros, ldm_manager, amt_motivoTramite, as_usuario, as_remoteIp, EstadoCommon.TERMINADA
				);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("actualizarEtapaYCrearSiguiente", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método de consulta de de la reprodduccion de constancia por turno
	 * @param at_turno con el parametro del turno a buscar
	 * @return ReproduccionConstanciaTestamento con los datos solicitados
	 * @throws B2BException en caso de algún errorr
	 */
	public synchronized ReproduccionConstanciaTestamento buscarRepConstanciaDocumento(Documento at_turno)
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrct_rct;
		DAOManager                       ldm_manager;
		lrct_rct        = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		SolicitudDAO            lsd_solicituDao;
		DocumentoDAO            ldd_documentoDAO;
		TipoDocumentoPublicoDAO ltdd_tipoDocumentoDAO;
		OficinaOrigenDAO        looD_oficinaOrigenDAO;
		TurnoDAO                ltd_turnoDAO;
		TestamentoDAO           ltd_testamentoDAO;
		TipoTestamentoDAO       ltt_tipoTestamentoDAO;

		lsd_solicituDao = DaoCreator.getSolicitudDAO(ldm_manager);
		ldd_documentoDAO = DaoCreator.getDocumentoDAO(ldm_manager);
		ltdd_tipoDocumentoDAO = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager);
		looD_oficinaOrigenDAO = DaoCreator.getOficinaOrigenDAO(ldm_manager);
		ltd_turnoDAO    = DaoCreator.getTurnoDAO(ldm_manager);
		ltd_testamentoDAO = DaoCreator.getTestamentoDAO(ldm_manager);
		ltt_tipoTestamentoDAO = DaoCreator.getTipoTestamentoDAO(ldm_manager);

		try
		{
			if(at_turno != null)
			{
				DocumentoConstancia             ldc_documentoConstancia;
				Collection<DocumentoConstancia> lcdc_documentoConstancia;
				ldc_documentoConstancia      = new DocumentoConstancia();
				lcdc_documentoConstancia     = new LinkedList<DocumentoConstancia>();

				{
					ldc_documentoConstancia.setNumero(at_turno.getNumero());
					ldc_documentoConstancia.setIdTipoDocumento(at_turno.getIdTipoDocumento());
					ldc_documentoConstancia.setFechaDocumento(at_turno.getFechaDocumento());
					ldc_documentoConstancia.setIdOficinaOrigen(at_turno.getIdOficinaOrigen());

					lcdc_documentoConstancia = ldd_documentoDAO.consultaMaxDocConstancia(ldc_documentoConstancia);

					if(CollectionUtils.isValidCollection(lcdc_documentoConstancia))
					{
						Solicitud ls_solicitud;
						ls_solicitud = null;

						for(DocumentoConstancia idc_doc : lcdc_documentoConstancia)
						{
							lrct_rct = new ReproduccionConstanciaTestamento();

							if(idc_doc != null)
							{
								ls_solicitud = lsd_solicituDao.findByIdDocumento(idc_doc.getIdDocumento());

								if(ls_solicitud != null)
								{
									if(StringUtils.isValidString(ls_solicitud.getIdTestamento()))
									{
										Documento ld_documento;
										ld_documento = ldd_documentoDAO.findById(idc_doc.getIdDocumento());

										if(ld_documento != null)
										{
											TipoDocumentoPublico ltdp_tipoDocumento;
											ltdp_tipoDocumento = ltdd_tipoDocumentoDAO.findById(
												    ld_documento.getIdTipoDocumento()
												);

											if(ltdp_tipoDocumento != null)
												ld_documento.setNombreTipoDocumento(ltdp_tipoDocumento.getNombre());

											OficinaOrigen loo_oficinaOrigen;
											loo_oficinaOrigen     = new OficinaOrigen();
											loo_oficinaOrigen     = looD_oficinaOrigenDAO.findById(
												    ld_documento.getIdOficinaOrigen(), ld_documento.getVersion()
												);

											if(loo_oficinaOrigen != null)
												ld_documento.setNombreOficinaOrigen(loo_oficinaOrigen.getNombre());

											lrct_rct.setDocumento(ld_documento);
										}

										Turno lt_turno;
										lt_turno = new Turno();
										lt_turno.setIdSolicitud(ls_solicitud.getIdSolicitud());
										lt_turno.setIdProceso(ProcesoCommon.ID_PROCESO_6);
										lt_turno = ltd_turnoDAO.findByIdSolicitudProceso(lt_turno);

										if(lt_turno != null)
										{
											lrct_rct.setTurno(lt_turno);

											Testamento lt_testamento;
											lt_testamento = new Testamento();
											lt_testamento.setIdTurno(lt_turno.getIdTurno());
											lt_testamento = ltd_testamentoDAO.findByTurno(lt_testamento);

											if(StringUtils.isValidString(lt_testamento.getIdTipoTestamento()))
											{
												TipoTestamento ltt_tipoTestamento;
												ltt_tipoTestamento = new TipoTestamento();
												ltt_tipoTestamento.setIdTipoTestamento(
												    lt_testamento.getIdTipoTestamento()
												);
												ltt_tipoTestamento = ltt_tipoTestamentoDAO.findById(ltt_tipoTestamento);
												lt_testamento.setNombreTipoTestamento(ltt_tipoTestamento.getNombre());
											}

											lrct_rct.setTestamento(lt_testamento);
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

			clh_LOGGER.error("ReproduccionConstanciaTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrct_rct;
	}

	/**
	 * Método de consulta de de la reprodduccion de constancia por turno
	 * @param at_turno con el parametro del turno a buscar
	 * @return ReproduccionConstanciaTestamento con los datos solicitados
	 * @throws B2BException en caso de algún errorr
	 */
	public synchronized ReproduccionConstanciaTestamento buscarRepConstanciaTurno(Turno at_turno)
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrct_rct;
		DAOManager                       ldm_manager;
		lrct_rct        = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(at_turno != null)
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findByIdCirculo(at_turno);

				if(lt_turno != null)
				{
					lrct_rct = new ReproduccionConstanciaTestamento();

					String ls_idSolicitud;
					ls_idSolicitud = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;
						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							String ls_idTestamento;
							ls_idTestamento = ls_solicitud.getIdTestamento();

							if(StringUtils.isValidString(ls_idTestamento))
							{
								Testamento lt_testamento;
								lt_testamento = new Testamento();

								String ls_idTipoTestamento;
								lt_testamento.setIdTestamento(ls_idTestamento);
								lt_testamento           = DaoCreator.getTestamentoDAO(ldm_manager)
										                                .findById(lt_testamento);
								ls_idTipoTestamento     = lt_testamento.getIdTipoTestamento();

								if(StringUtils.isValidString(ls_idTipoTestamento))
								{
									TipoTestamento ltt_tipoTestamento;
									ltt_tipoTestamento = new TipoTestamento();
									ltt_tipoTestamento.setIdTipoTestamento(ls_idTipoTestamento);
									ltt_tipoTestamento = DaoCreator.getTipoTestamentoDAO(ldm_manager)
											                           .findById(ltt_tipoTestamento);
									lt_testamento.setNombreTipoTestamento(ltt_tipoTestamento.getNombre());
								}

								lrct_rct.setTestamento(lt_testamento);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_EL_TURNO_NO_CORRESPONDE_A_TESTAMENTO);

							String ls_idDocumento;
							ls_idDocumento = ls_solicitud.getIdDocumento();

							if(StringUtils.isValidString(ls_idDocumento))
							{
								Documento ld_documento;
								ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ls_idDocumento);

								if(ld_documento != null)
								{
									String ls_idTipoDocumentoPublico;
									ls_idTipoDocumentoPublico = ld_documento.getIdTipoDocumento();

									if(StringUtils.isValidString(ls_idTipoDocumentoPublico))
									{
										TipoDocumentoPublico lt_tipoDocumento;
										lt_tipoDocumento = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
												                         .findById(ls_idTipoDocumentoPublico);

										if(lt_tipoDocumento != null)
											ld_documento.setNombreTipoDocumento(lt_tipoDocumento.getNombre());
									}

									String ls_idOficinaOrigen;
									ls_idOficinaOrigen = ld_documento.getIdOficinaOrigen();

									if(StringUtils.isValidString(ls_idOficinaOrigen))
									{
										OficinaOrigen loo_oficinaOrigen;
										loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
												                          .findById(
												    ls_idOficinaOrigen, ld_documento.getVersion()
												);

										if(loo_oficinaOrigen != null)
											ld_documento.setNombreOficinaOrigen(loo_oficinaOrigen.getNombre());
									}

									lrct_rct.setDocumento(ld_documento);
								}
							}
						}
					}

					lrct_rct.setTurno(lt_turno);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ReproduccionConstanciaTestamento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lrct_rct;
	}

	/**
	 * Obtiene los turnos con usuario y etapa ASG
	 *
	 * @param atc_tc
	 *            objeto del cual se extraerán los datos necesarios para la consulta
	 * @return Collection<TramiteCantidad> con la informacion de bd
	 * @throws B2BException
	 */
	public synchronized Collection<TramiteCantidad> consultaBandeja(TramiteCantidad atc_tc, boolean ab_detalle)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lctc_tramitesCantidad;
		Collection<TramiteCantidad> lctc_tramitesCantidadTemp;

		ldm_manager                   = DaoManagerFactory.getDAOManager();
		lctc_tramitesCantidad         = null;
		lctc_tramitesCantidadTemp     = null;

		try
		{
			if(atc_tc != null)
				lctc_tramitesCantidad = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                              .bandejaTestamentos(atc_tc, ab_detalle);
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(lctc_tramitesCantidad != null)
			{
				lctc_tramitesCantidadTemp = new ArrayList<TramiteCantidad>();

				for(TramiteCantidad lotc_tmp : lctc_tramitesCantidad)
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

						lctc_tramitesCantidadTemp.add(lotc_tmp);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_tramitesCantidadTemp;
	}

	/**
	 * Obtiene la información de un turno
	 *
	 * @param as_idTurno id del turno a utilizar como filtro en la consulta
	 * @return Objeto resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized Turno consultarTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Turno      lt_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lt_return       = null;

		try
		{
			lt_return = consultarTurno(as_idTurno, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_return;
	}

	/**
	 * Obtiene la información de un turno
	 *
	 * @param as_idTurno id del turno a utilizar como filtro en la consulta
	 * @param adm_manager <code>DAOManager</code> que controla las conexiones con la BD.
	 * @return Objeto resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized Turno consultarTurno(String as_idTurno, DAOManager adm_manager)
	    throws B2BException
	{
		Turno lt_return;

		lt_return = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
				lt_return = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTurno", lb2be_e);

			throw lb2be_e;
		}

		return lt_return;
	}

	/**
	 * Metodo encargado de consultar el turno historia inmediatamente anterior al criterio enviado.
	 * @param ath_turnoHistoria Argumento de tipo TurnoHistoria que contienen los criterios necesarios
	 * para realizar la busqueda.
	 * @return Objeto de tipo TurnoHistoria que contiene los datos que cumplieron con los criterios de búsqueda.
	 * @throws B2BException
	 */
	public synchronized TurnoHistoria consultarTurnoHistoriaAnterior(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		TurnoHistoria lth_datos;

		lth_datos = null;

		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(ll_idTurnoHistoria != null)
				{
					TurnoHistoria    lth_turnoHistoria;
					TurnoHistoriaDAO lthd_DAO;

					lth_turnoHistoria     = new TurnoHistoria();
					lthd_DAO              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_turnoHistoria.setIdTurnoHistoria(ll_idTurnoHistoria);

					lth_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						TurnoHistoria lth_turnoHistoriaAnterior;

						lth_turnoHistoriaAnterior = new TurnoHistoria();

						lth_turnoHistoriaAnterior.setIdTurnoHistoria(
						    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
						);

						lth_datos = lthd_DAO.findById(lth_turnoHistoriaAnterior);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarTurnoHistoriaAnterior", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lth_datos;
	}

	/**
	* Método encargado de encontrar turnos relacionados con la solicitud actual
	* @param as_idTurnoHistoria String del id turno historia para realizar busquedas en la BD
	* @return Retorna un string con mensaje.
	* @throws B2BException
	*/
	public synchronized String encontrarTurnosActuales(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_mensaje;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_mensaje      = "";

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = new TurnoHistoria();
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						if(NumericUtils.getLong(ls_solicitud.getEstadoSolicitud()) != 5)
						{
							Turno             lt_turno;
							Collection<Turno> lct_turnos;

							lt_turno = new Turno();
							lt_turno.setIdSolicitud(ls_solicitud.getIdSolicitud());

							lct_turnos = DaoCreator.getTurnoDAO(ldm_manager).findByIdSolicitud(lt_turno);

							if(CollectionUtils.isValidCollection(lct_turnos))
							{
								int li_cont;
								li_cont = 1;

								for(Turno lt_tmp : lct_turnos)
								{
									if(lt_tmp != null)
									{
										if(li_cont == 1)
											ls_mensaje = ls_mensaje + lt_tmp.getIdTurno();
										else
											ls_mensaje = ls_mensaje + IdentificadoresCommon.SIMBOLO_COMA
												+ lt_tmp.getIdTurno();

										li_cont = li_cont + 1;
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

			clh_LOGGER.error("encontrarTurnosActuales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_mensaje;
	}

	/**
	 * Termina la etapa e invoca el procedimiento de crear etapa para enviar a ant
	 * sistema
	 *
	 * @param ath_turnoHistoria
	 *            Objeto del cual se extraerán los parametros para realizar el envio
	 *            a ant sistema
	 * @throws B2BException
	 */
	public synchronized void enviarAEtapaPosterior(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;
			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoriaDAO lthd_DAO;
				TurnoHistoria    lth_dataTurn;

				lthd_DAO         = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_dataTurn     = lthd_DAO.findByIdTurno(ath_turnoHistoria.getIdTurno());

				if(lth_dataTurn != null)
				{
					MotivoTramite lmt_motivo;

					lmt_motivo = new MotivoTramite();

					lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES);
					lmt_motivo.setIdMotivo(NumericUtils.getLong(ath_turnoHistoria.getIdMotivo()));

					lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
						lth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_dataTurn.setObservaciones(ath_turnoHistoria.getObservaciones());
						lth_dataTurn.setIdUsuarioModificacion(ath_turnoHistoria.getIdUsuarioModificacion());
						lth_dataTurn.setIpModificacion(ath_turnoHistoria.getIpModificacion());

						lthd_DAO.insertOrUpdate(lth_dataTurn, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_dataTurn);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("enviarAAntSistema", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de cambiar de etapa para el proceso de reproducción de constancia.
	 *
	 * @param ac_c Objeto de tipo Calificacion que contiene los datos determinados para realizar el cambio de etapa.
	 * @param as_userId correspondiente al valor de user id
	 * @param as_remoteIp correspondiente al valor de remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void enviarAprobadorRepConstancia(Calificacion ac_c, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_c != null)
			{
				Long ll_idMotivo;
				Long ls_idTurnoHistoria;

				ll_idMotivo            = ac_c.getIdMotivo();
				ls_idTurnoHistoria     = ac_c.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idMotivo))
				{
					MotivoTramite    lmt_motivo;
					TurnoHistoria    lth_dataTurn;
					TurnoHistoriaDAO lothd_DAO;

					lth_dataTurn     = new TurnoHistoria();
					lothd_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

					lth_dataTurn.setIdTurnoHistoria(ls_idTurnoHistoria);

					lth_dataTurn = lothd_DAO.findById(lth_dataTurn);

					if(lth_dataTurn != null)
					{
						lmt_motivo = new MotivoTramite();

						lmt_motivo.setIdMotivo(NumericUtils.getLong(ll_idMotivo));
						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CALIFICACION);

						lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							{
								String ls_estadoActividad;

								ls_estadoActividad = lth_dataTurn.getEstadoActividad();

								if(
								    StringUtils.isValidString(ls_estadoActividad)
									    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
								)
									throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
							}

							lth_dataTurn.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_dataTurn.setMotivo(lmt_motivo.getDescripcion());
							lth_dataTurn.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_dataTurn.setIdUsuarioModificacion(ac_c.getIdUsuarioModificacion());
							lth_dataTurn.setIpModificacion(ac_c.getIpModificacion());

							{
								ConstanciaReproduccion lcr_constanciaReproduccion;

								lcr_constanciaReproduccion = new ConstanciaReproduccion();

								if(lth_dataTurn != null)
								{
									Turno  lt_turno;
									String ls_turno;

									lt_turno     = new Turno();
									ls_turno     = lth_dataTurn.getIdTurno();

									if(StringUtils.isValidString(ls_turno))
									{
										lt_turno.setIdTurno(ls_turno);
										lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

										if(lt_turno != null)
										{
											String ls_idCirculo;

											ls_idCirculo = lt_turno.getIdCirculo();

											lcr_constanciaReproduccion.setTurnoHistoria(lth_dataTurn);

											if(StringUtils.isValidString(ls_idCirculo))
											{
												byte[] lba_documento;

												lcr_constanciaReproduccion.setIdCirculo(ls_idCirculo);
												lba_documento = getConstanciaReproduccionBusiness()
														                .generarConstanciaInscripcionRepConstancia(
														    lcr_constanciaReproduccion, false, as_userId, as_remoteIp
														);

												if(lba_documento != null)
												{
													Imagenes            li_imagenRelacion;
													ImagenesDAO         li_DAO;
													DocumentosSalidaDAO lds_DAO;
													long                ll_idImagen;
													Long                ll_turnoHistoria;
													String              ls_numeroSolicitud;

													li_imagenRelacion      = new Imagenes();
													li_DAO                 = DaoCreator.getImagenesDAO(ldm_manager);
													lds_DAO                = DaoCreator.getDocumentosSalidaDAO(
														    ldm_manager
														);
													ll_turnoHistoria       = lth_dataTurn.getIdTurnoHistoria();
													ls_numeroSolicitud     = lth_dataTurn.getIdSolicitud();

													li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
													li_imagenRelacion.setIdUsuarioCreacion(as_userId);
													li_imagenRelacion.setIpCreacion(as_remoteIp);
													li_imagenRelacion.setImagenBlob(lba_documento);

													ll_idImagen = li_DAO.insertOrUpdate(li_imagenRelacion, true);

													if(
													    !NumericUtils.isValidLong(
														        NumericUtils.getLongWrapper(ll_idImagen), 1
														    )
													)
														throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

													DocumentosSalida lds_documento;
													Integer          lbi_idTurnoHistoria;

													lds_documento           = new DocumentosSalida();
													lbi_idTurnoHistoria     = NumericUtils.isValidLong(
														    ll_turnoHistoria
														) ? NumericUtils.getInteger(ll_turnoHistoria.toString()) : null;

													lds_documento.setIdTurnoHistoria(lbi_idTurnoHistoria);
													lds_documento.setIdSolicitud(ls_numeroSolicitud);
													lds_documento.setIdTurno(ls_turno);
													lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
													lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
													lds_documento.setTipo(TipoArchivoCommon.CONSTANCIA_REPRODUCCION);
													lds_documento.setIdTipoDocumental(
													    TipoDocumentalCommon.CONSTANCIA_REPRODUCCION
													);
													lds_documento.setRepositorio(RepositorioCommon.TEMPORAL);
													lds_documento.setEstado(EstadoCommon.ACTIVO);
													lds_documento.setIdUsuarioCreacion(as_userId);
													lds_documento.setIpCreacion(as_remoteIp);

													lds_DAO.insertOrUpdate(lds_documento, true);
													lothd_DAO.insertOrUpdate(lth_dataTurn, false);

													DaoCreator.getProcedimientosDAO(ldm_manager)
														          .spCreateStage(lth_dataTurn);
												}
												else
													throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
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

			clh_LOGGER.error("enviarAprobadorRepConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar el detalle de todos los documentos asociados a un id documento, id oficina origen y número.
	 *
	 * @param acccd_consultaCriteriosCalificacionDocumento Objeto que contiene los datos necesarios para ejecutar la sentencia select sobre la tabla SDB_BGN_DOCUMENTO.
	 * @return Retorna  una colección de datos de tipo  DataConsultaDatosDocumento que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Collection<DataConsultaDatosDocumento> findByDatosDocumento(
	    ConsultaCriteriosCalificacionDocumento acccd_consultaCriteriosCalificacionDocumento
	)
	    throws B2BException
	{
		DAOManager                             ldm_manager;
		Collection<DataConsultaDatosDocumento> ll_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ll_result       = new ArrayList<DataConsultaDatosDocumento>();

		try
		{
			if(acccd_consultaCriteriosCalificacionDocumento != null)
			{
				Documento ld_documento;
				ld_documento = acccd_consultaCriteriosCalificacionDocumento.getDocumento();

				if(ld_documento != null)
				{
					String ls_idTipoDocumento;
					String ls_numero;
					Date   ld_fechaDocumento;
					String ls_idOficinaOrigen;

					ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
					ls_numero              = ld_documento.getNumero();
					ld_fechaDocumento      = ld_documento.getFechaDocumento();
					ls_idOficinaOrigen     = ld_documento.getIdOficinaOrigen();

					if(!StringUtils.isValidString(ls_idTipoDocumento))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

					if(!StringUtils.isValidString(ls_numero))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

					if(ld_fechaDocumento == null)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_FECHA_DOC);

					if(!StringUtils.isValidString(ls_idOficinaOrigen))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_OFICINA_ORIGEN);

					AnotacionPredio       lap_anotacionPredio;
					Collection<Documento> lcd_documentos;

					lap_anotacionPredio     = acccd_consultaCriteriosCalificacionDocumento.getAnotacionPredio();
					lcd_documentos          = DaoCreator.getDocumentoDAO(ldm_manager).consultarDocumentos(ld_documento);

					if(!CollectionUtils.isValidCollection(lcd_documentos))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);

					if(lap_anotacionPredio != null)
					{
						String ls_idCirculo;
						Long   ll_idMatricula;

						ls_idCirculo     = lap_anotacionPredio.getIdCirculo();

						ll_idMatricula = lap_anotacionPredio.getIdMatricula();

						if(StringUtils.isValidString(ls_idCirculo))
						{
							for(Documento ld_iterador : lcd_documentos)
							{
								DataConsultaDatosDocumento ldccdc_datos;
								AnotacionPredio            lap_anotacion;
								PredioRegistro             lpr_predioRegistro;
								Long                       ll_version;

								lap_anotacion     = lap_anotacionPredio;

								ldccdc_datos     = new DataConsultaDatosDocumento();

								lpr_predioRegistro = new PredioRegistro();
								lpr_predioRegistro.setIdCirculo(ls_idCirculo);
								lpr_predioRegistro.setIdMatricula(NumericUtils.getLong(ll_idMatricula));
								ll_version = null;

								String ls_idDocumento;

								ls_idDocumento = ld_iterador.getIdDocumento();

								if(StringUtils.isValidString(ls_idDocumento))
								{
									ll_version = ld_iterador.getVersionDocumento();

									if(NumericUtils.isValidLong(ll_version))
										ldccdc_datos.setVersion(ll_version);

									lap_anotacion.setIdDocumento(ls_idDocumento);
									lpr_predioRegistro.setIdDocumento(ls_idDocumento);

									lap_anotacion     = DaoCreator.getAnotacionPredioDAO(ldm_manager)
											                          .findAnotacionPredio(lap_anotacion);

									lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(ldm_manager)
											                           .findByDocumento(lpr_predioRegistro);

									if((lap_anotacion != null) || (lpr_predioRegistro != null))
									{
										ldccdc_datos.setDocumento(ld_iterador);

										Collection<DataConsultaPorCriterio> lcdcpc_consultaPorCriterio;
										Collection<DataConsultaPorCriterio> lcdcpc_dataCriteriosFinal;

										lcdcpc_consultaPorCriterio     = DaoCreator.getConsultaPorCriteriosDAO(
											    ldm_manager
											).findByDatosDocumento(ls_idCirculo, ls_idDocumento);
										lcdcpc_dataCriteriosFinal      = new ArrayList<DataConsultaPorCriterio>();

										if(CollectionUtils.isValidCollection(lcdcpc_consultaPorCriterio))
										{
											LinkedHashMap<String, DataConsultaPorCriterio> llhm_data;

											llhm_data = new LinkedHashMap<String, DataConsultaPorCriterio>();

											for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_consultaPorCriterio)
											{
												Long   ll_idMatriculaIterador;
												String ls_idCirculoIterador;

												ll_idMatriculaIterador     = ldcpc_iterador.getIdMatricula();
												ls_idCirculoIterador       = ldcpc_iterador.getIdCirculo();

												if(
												    StringUtils.isValidString(ls_idCirculoIterador)
													    && NumericUtils.isValidLong(ll_idMatriculaIterador)
												)
												{
													String ls_matriculaCompleta;

													ls_matriculaCompleta = ls_idCirculoIterador + "-"
														+ ll_idMatriculaIterador;

													if(llhm_data.containsKey(ls_matriculaCompleta))
													{
														DataConsultaPorCriterio ldcpc_temp;

														ldcpc_temp = llhm_data.get(ls_matriculaCompleta);

														if(ldcpc_temp != null)
														{
															String ls_anotaciones;
															String ls_anotacion;

															ls_anotaciones     = ldcpc_temp.getIdAnotacion();
															ls_anotacion       = ldcpc_iterador.getIdAnotacion();

															ls_anotaciones += ("," + ls_anotacion);

															ldcpc_temp.setIdAnotacion(ls_anotaciones);
														}
													}
													else
														llhm_data.put(ls_matriculaCompleta, ldcpc_iterador);
												}
											}

											for(Map.Entry<String, DataConsultaPorCriterio> entry : llhm_data.entrySet())
												lcdcpc_dataCriteriosFinal.add(entry.getValue());
										}

										ldccdc_datos.setDataConsultaPorCriterio(lcdcpc_dataCriteriosFinal);

										Solicitud ls_solicitud;
										ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager)
												                     .findByIdDocumento(ls_idDocumento);

										if(ls_solicitud != null)
											ldccdc_datos.setSolicitud(
											    DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud)
											);

										ll_result.add(ldccdc_datos);
									}
								}
							}
						}
					}

					if(!CollectionUtils.isValidCollection(ll_result))
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INFORMACION_DOC);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DATOS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByDatosDocumento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ll_result;
	}

	/**
	 * Método encargado de consultar el círculo asociado a un id turno determinado.
	 *
	 * @param as_idTurno Variable de tipo Long que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized String findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();

			lhmp_criterios.put(NumericUtils.getInteger(1), as_idTurno);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(ConsultasUtilidades.CS_CIRCULO_BY_ID_TURNO, lhmp_criterios);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.ID_CIRCULO);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 *     Método encargado de consultar el detalle de un id constante determinado.
	 *
	 * @param as_param Variable de tipo String  que contiene un id constante determinado.
	 * @return Retorna  un objeto de tipo Constantes que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Constantes findConstanteById(String as_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_data         = null;

		try
		{
			lc_data = DaoCreator.getConstantesDAO(ldm_manager).findById(as_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstanteById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_data;
	}

	/**
	 * Método encargado de consultar los turnos de la etapa 80 y un usuario determinado, además se encarga de validar si muestra o no el detalle de dicho turno.
	 *
	 * @param auaiu_usuarioActividad Objeto que contiene los parametros para realizar la consulta, tales como Etapa y Usuario, Nir y/o Turno
	 * @return Colección de turnos historia consultados para la etapa 80 y el usuario determinado.
	 * @throws B2BException
	 */
	public synchronized Collection<UsuarioActividadUI> findDataBandeja(UsuarioActividadUI auaiu_usuarioActividad)
	    throws B2BException
	{
		Collection<UsuarioActividadUI> lcth_return;
		DAOManager                     ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcth_return     = null;

		try
		{
			if(auaiu_usuarioActividad != null)
			{
				if(
				    StringUtils.isValidString(auaiu_usuarioActividad.getIdUsuario())
					    && NumericUtils.isValidBigDecimal(auaiu_usuarioActividad.getIdEtapa())
				)
				{
					String ls_nir;

					ls_nir = auaiu_usuarioActividad.getNir();

					if(StringUtils.isValidString(ls_nir))
					{
						Solicitud    ls_solicitud;
						SolicitudDAO ls_DAO;

						ls_solicitud     = new Solicitud();
						ls_DAO           = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setNir(ls_nir);

						ls_solicitud = ls_DAO.findByNir(ls_solicitud);

						if(ls_solicitud != null)
							auaiu_usuarioActividad.setIdSolicitud(ls_solicitud.getIdSolicitud());
					}

					ConsultaTrazabilidadDAO lct_DAO;

					lct_DAO         = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager);
					lcth_return     = lct_DAO.findDatosBandeja(auaiu_usuarioActividad);

					if(CollectionUtils.isValidCollection(lcth_return))
					{
						Constantes    lc_constante;
						ConstantesDAO lc_DAO;

						lc_constante     = new Constantes();
						lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

						lc_constante.setIdConstante(ConstanteCommon.ETAPAS_DEVOLUCION_CALIFICACION);

						lc_constante = lc_DAO.findById(lc_constante);

						if(lc_constante != null)
						{
							String[]                    lsa_etapas;
							HashMap<BigDecimal, String> lhmss_etapas;

							lsa_etapas       = lc_constante.getCaracter().split(",");
							lhmss_etapas     = new HashMap<BigDecimal, String>();

							if(CollectionUtils.isValidCollection(lsa_etapas))
							{
								for(String ls_iterador : lsa_etapas)
									lhmss_etapas.put(
									    NumericUtils.getBigDecimal(NumericUtils.getLong(ls_iterador)), ls_iterador
									);

								boolean lb_mostrarPrimero;

								lb_mostrarPrimero = false;

								for(UsuarioActividadUI luaui_iterador : lcth_return)
								{
									if(luaui_iterador != null)
									{
										BigDecimal lbd_etapa;

										lbd_etapa = luaui_iterador.getIdEtapa();

										if(NumericUtils.isValidBigDecimal(lbd_etapa))
										{
											if(lhmss_etapas.containsKey(lbd_etapa))
												luaui_iterador.setMostrar(true);
											else
											{
												if(!lb_mostrarPrimero)
												{
													luaui_iterador.setMostrar(true);
													lb_mostrarPrimero = true;
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

			clh_LOGGER.error("findDataBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcth_return;
	}

	/**
	 * Método encargado de  consultar el id solicitud para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized String findIdSolicitudByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios = new LinkedHashMap<Integer, Object>();
			lhmp_criterios.put(NumericUtils.getInteger(1), as_idTurnoHistoria);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(
					    ConsultasUtilidades.CS_SOLICITUD_BY_ID_TURNO_HIST, lhmp_criterios
					);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.ID_SOLICITUD);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findIdSolicitudByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Obtiene la información de un Libro Testamento.
	 *
	 * @param alt_libroTestamento con los parametros de búsqueda
	 * @return Objeto resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized LibroTestamento findLibroTestamento(
	    LibroTestamento alt_libroTestamento, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		LibroTestamento lt_libroTestamento;

		try
		{
			lt_libroTestamento = findLibroTestamento(alt_libroTestamento, ldm_manager, as_userId, as_remoteIp);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTurno", lb2be_e);
			ldm_manager.setRollbackOnly();
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lt_libroTestamento;
	}

	/**
	 * Obtiene la información de un turno
	 *
	 * @param alt_libroTestamento con los parametros de búsqueda
	 * @param adm_manager <code>DAOManager</code> que controla las conexiones con la BD.
	 * @return Objeto resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized LibroTestamento findLibroTestamento(
	    LibroTestamento alt_libroTestamento, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		LibroTestamento lt_return;

		lt_return = null;

		try
		{
			if(alt_libroTestamento != null)
			{
				lt_return = DaoCreator.getLibroTestamentoDAO(adm_manager).findById(alt_libroTestamento);

				if(lt_return == null)
				{
					Calendar lc_date;
					Date     ld_date;
					int      li_ano;
					int      li_inicio;

					ld_date       = new Date();
					lc_date       = Calendar.getInstance();
					lt_return     = new LibroTestamento();
					li_inicio     = 1;

					lc_date.setTime(ld_date);

					li_ano = lc_date.get(Calendar.YEAR);

					lt_return.setAno(NumericUtils.getLongWrapper(li_ano));
					lt_return.setLibro(NumericUtils.getLongWrapper(li_inicio));
					lt_return.setLibroAntSistema(NumericUtils.getLongWrapper(12L));
					lt_return.setTomo(NumericUtils.getLongWrapper(li_inicio));
					lt_return.setFolio(NumericUtils.getLongWrapper(li_inicio));
					lt_return.setIdCirculo(alt_libroTestamento.getIdCirculo());
					lt_return.setIdUsuarioCreacion(as_userId);
					lt_return.setIpCreacion(as_remoteIp);

					DaoCreator.getLibroTestamentoDAO(adm_manager).insert(lt_return);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTurno", lb2be_e);

			throw lb2be_e;
		}

		return lt_return;
	}

	/**
	 * Método encargado de consultar las observaciones para id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo String que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized String findObservacionesByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_data;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_data         = null;

		try
		{
			Collection<Map<String, Object>> lm_result      = null;
			Map<Integer, Object>            lhmp_criterios;

			lhmp_criterios                                 = new LinkedHashMap<Integer, Object>();
			lhmp_criterios.put(NumericUtils.getInteger(1), as_idTurnoHistoria);

			lm_result = DaoCreator.getUtilDAO(ldm_manager)
					                  .getCustonQuery(
					    ConsultasUtilidades.CS_OBSERVACIONES_PROCESO_ANTE, lhmp_criterios
					);

			if(CollectionUtils.isValidCollection(lm_result))
			{
				Iterator<Map<String, Object>> limso_iterador;

				limso_iterador = lm_result.iterator();

				if(limso_iterador.hasNext())
				{
					Map<String, Object> lmso_iterator;

					lmso_iterator = limso_iterador.next();

					if(lmso_iterator != null)
					{
						{
							Object lo_inicio;
							lo_inicio = lmso_iterator.get(IdentificadoresCommon.OBSERVACIONES);

							if((lo_inicio != null) && lo_inicio instanceof String)
								ls_data = ((String)lo_inicio).toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findObservacionesByIdTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_data;
	}

	/**
	 * Metodo encargardo de consultar una solicitud por turno.
	 * @param as_idTurno Argumento de tipo String que contiene el turno para realizar la consulta.
	 * @return Objeto de tipo Solicitud que contiene los valores que coinciden con los criterios de búsqueda.
	 * @throws B2BException
	 */
	public synchronized Solicitud findSolicitudByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Solicitud  ls_solicitud;
		DAOManager ldm_manager;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ls_solicitud     = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			{
				Turno lt_turno;

				lt_turno = new Turno();
				lt_turno.setIdTurno(as_idTurno);

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

				if(lt_turno != null)
				{
					ls_solicitud = new Solicitud();
					ls_solicitud.setIdSolicitud(lt_turno.getIdSolicitud());

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudByIdTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_solicitud;
	}

	/**
	 * Metodo para encontrar un tipo acto por id
	 *
	 * @return lta_tipoActoReturn con la información consultada
	 * @throws B2BException
	 */
	public TipoActo findTipoActoById(String as_idTipoActo)
	    throws B2BException
	{
		TipoActo   lta_tipoActoReturn;
		DAOManager ldm_manager;

		ldm_manager            = DaoManagerFactory.getDAOManager();
		lta_tipoActoReturn     = null;

		try
		{
			if(StringUtils.isValidString(as_idTipoActo))
				lta_tipoActoReturn = DaoCreator.getTipoActoDAO(ldm_manager).findTipoActoById(as_idTipoActo);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTipoActoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lta_tipoActoReturn;
	}

	/**
	 * Método encargado de consultar el detalle de un registro para un id turno historia y id etapa determinado.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene un id turno historia determinado.
	 * @param al_idEtapa Variable de tipo Long que contiene un id etapa determinado.
	 * @return Retorna  un objeto de tipo TurnoHistoria que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized TurnoHistoria findTurnoHistoria(Long al_idTurnoHistoria, Long al_idEtapa)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findDataTurn(al_idTurnoHistoria, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoria", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método encargado de consultar un turno historia por su respectivo id_turno_historia.
	 *
	 * @param ath_param Objeto que contiene los datos necesarios para ejecutar la sentencia select.
	 * @return Retorna el objeto turno historia respectivo a la consulta.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		DAOManager    ldm_manager;
		TurnoHistoria lth_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lth_datos       = null;

		try
		{
			lth_datos = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ath_param);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoHistoriaById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lth_datos;
	}

	/**
	 * Método encargado de consultar la cantidad de turnos para un usuario en un una etapa y estado activadad determinado.
	 *
	 * @param as_estado Variable de tipo String que contiene un estado determinado.
	 * @param as_idUsuario Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param al_idEtapa Variable de tipo long que contiene un  id etapa determinado.
	 * @return Retorna  una variable de tipo Integer que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized Integer findTurnosCantidad(
	    String as_estado, String as_idUsuario, long al_idEtapa, boolean ab_allUsuarios
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		TurnoHistoriaDAO lthd_turnoHistoriaDAO;
		Integer          li_dato;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		li_dato                   = null;

		try
		{
			if(!ab_allUsuarios)
				li_dato = lthd_turnoHistoriaDAO.findTurnosCantidad(as_estado, as_idUsuario, al_idEtapa, null);
			else
				li_dato = lthd_turnoHistoriaDAO.findTurnosCantidadForAllUsers(as_estado, al_idEtapa);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnosCantidad", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return li_dato;
	}

	/**
	 * Construye el formulario de correcciones
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException
	 */
	public synchronized byte[] generarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String                           as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			lba_return = generarConstanciaReproduccionTestamento(
				    ast_solicitudTestamento, as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, false, false
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("generarConstanciaTestamento", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método de generación de la constancia de testamento
	 * @param ast_solicitudTestamento  con los parametros
	 * @param as_userId con el usuario de la transacción
	 * @param as_remoteIp con la ip de la transacción
	 * @param ldm_manager con el manager de la transacción
	 * @param ab_salvar para saber si se salva en BD
	 * @param ab_firma para identificar si viene de firma masiva
	 * @return tipo byte con el documento generado
	 * @throws B2BException
	 */
	public synchronized byte[] generarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String                           as_remoteIp, DAOManager ldm_manager, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return     = null;

		lba_return = generarConstanciaReproduccionTestamento(
			    ast_solicitudTestamento, as_idTurnoHistoria, ldm_manager, as_userId, as_remoteIp, ab_salvar, ab_firma
			);

		return lba_return;
	}

	/**
	 * Construye el formulario de correcciones
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException
	 */
	public synchronized byte[] generarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			lba_return = generarConstanciaTestamento(
				    ast_solicitudTestamento, as_nir, as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, false,
				    false
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("generarConstanciaTestamento", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Obtiene genera los datos correspondientes a la data de testamentos en la consulta detallada
	 *
	 * @param as_turno
	 *            con el nir por el cual se iniciará la búsqueda
	 * @return SolicitudTestamento con la información de base de datos
	 * @throws B2BException
	 */
	public synchronized SolicitudTestamento generarDataTestamentos(String as_turno)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		SolicitudTestamento lst_solicitudTestamentos;

		lst_solicitudTestamentos     = new SolicitudTestamento();
		ldm_manager                  = DaoManagerFactory.getDAOManager();

		try
		{
			if(as_turno != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(as_turno);

				if(ls_solicitud != null)
				{
					Documento ld_documento;

					ld_documento = DaoCreator.getDocumentoDAO(ldm_manager).findById(ls_solicitud.getIdDocumento());

					if(ld_documento != null)
					{
						{
							TipoDocumentoPublico ltdp_tipoDocumentoPublico;
							ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();
							ltdp_tipoDocumentoPublico.setIdTipoDocumento(ld_documento.getIdTipoDocumento());
							ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(ldm_manager)
									                                  .findById(ltdp_tipoDocumentoPublico);

							if(ltdp_tipoDocumentoPublico != null)
								ld_documento.setTipoDocumento(ltdp_tipoDocumentoPublico);
						}

						String ls_idPais;
						Pais   lp_pais;

						ls_idPais     = ld_documento.getIdPais();
						lp_pais       = DaoCreator.getPaisDAO(ldm_manager).findById(ls_idPais);

						if(lp_pais != null)
						{
							ld_documento.setPais(lp_pais);

							OficinaOrigen loo_oficinaOrigen;

							loo_oficinaOrigen = new OficinaOrigen();

							loo_oficinaOrigen.setIdOficinaOrigen(ld_documento.getIdOficinaOrigen());
							loo_oficinaOrigen.setVersion(ld_documento.getVersion());

							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager).findById(loo_oficinaOrigen);

							if(loo_oficinaOrigen != null)
							{
								ld_documento.setOficinaOrigen(loo_oficinaOrigen);

								Departamento ld_departamento;

								ld_departamento = new Departamento();

								ld_departamento.setIdPais(lp_pais.getIdPais());
								ld_departamento.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());

								ld_departamento = DaoCreator.getDepartamentoDAO(ldm_manager).findById(ld_departamento);

								if(
								    (ld_departamento != null)
									    && StringUtils.isValidString(ld_departamento.getIdDepartamento())
								)
									ld_documento.setDepartamento(ld_departamento);

								Municipio lm_municipio;

								lm_municipio = new Municipio();

								lm_municipio.setIdPais(lp_pais.getIdPais());
								lm_municipio.setIdDepartamento(loo_oficinaOrigen.getIdDepartamento());
								lm_municipio.setIdMunicipio(loo_oficinaOrigen.getIdMunicipio());

								lm_municipio = DaoCreator.getMunicipioDAO(ldm_manager).findById(lm_municipio);

								if((lm_municipio != null) && StringUtils.isValidString(lm_municipio.getIdMunicipio()))
									ld_documento.setMunicipio(lm_municipio);
							}
						}

						{
							TipoOficina lto_tipoOficina;

							lto_tipoOficina = new TipoOficina();

							lto_tipoOficina.setIdTipoOficina(ld_documento.getIdTipoOficina());

							lto_tipoOficina = DaoCreator.getTipoOficinaDAO(ldm_manager).findById(lto_tipoOficina);

							if(
							    (lto_tipoOficina != null)
								    && StringUtils.isValidString(lto_tipoOficina.getIdTipoOficina())
							)
							{
								ld_documento.setTipoOficina(lto_tipoOficina);

								TipoEntidad lte_tipoEntidad;

								lte_tipoEntidad = new TipoEntidad();

								lte_tipoEntidad.setIdTipoEntidad(lto_tipoOficina.getIdTipoEntidad());

								lte_tipoEntidad = DaoCreator.getTipoEntidadDAO(ldm_manager).findById(lte_tipoEntidad);

								if(
								    (lte_tipoEntidad != null)
									    && StringUtils.isValidString(lte_tipoEntidad.getIdTipoEntidad())
								)
									ld_documento.setTipoEntidadDocumento(lte_tipoEntidad);
							}
						}

						lst_solicitudTestamentos.setDocumento(ld_documento);
					}

					{
						Testamento lt_testamento;

						lt_testamento = new Testamento();

						lt_testamento.setIdTestamento(ls_solicitud.getIdTestamento());

						lt_testamento = DaoCreator.getTestamentoDAO(ldm_manager).findById(lt_testamento);

						if((lt_testamento != null) && StringUtils.isValidString(lt_testamento.getIdTestamento()))
						{
							TipoTestamento ltt_tipoTestamento;

							ltt_tipoTestamento = new TipoTestamento();

							ltt_tipoTestamento.setIdTipoTestamento(lt_testamento.getIdTipoTestamento());

							ltt_tipoTestamento = DaoCreator.getTipoTestamentoDAO(ldm_manager)
									                           .findById(ltt_tipoTestamento);

							if(
							    (ltt_tipoTestamento != null)
								    && StringUtils.isValidString(ltt_tipoTestamento.getIdTipoTestamento())
							)
								lt_testamento.setNombreTipoTestamento(ltt_tipoTestamento.getNombre());

							String ls_idTestamentoAnterior;

							ls_idTestamentoAnterior = lt_testamento.getIdTestamentoAnterior();

							if(StringUtils.isValidString(ls_idTestamentoAnterior))
							{
								Testamento lt_testamentoAnterior;

								lt_testamentoAnterior = new Testamento();

								lt_testamentoAnterior.setIdTestamento(ls_idTestamentoAnterior);

								lt_testamentoAnterior = DaoCreator.getTestamentoDAO(ldm_manager)
										                              .findById(lt_testamentoAnterior);

								if(
								    (lt_testamentoAnterior != null)
									    && StringUtils.isValidString(lt_testamentoAnterior.getIdTestamento())
								)
								{
									ltt_tipoTestamento.setIdTipoTestamento(lt_testamentoAnterior.getIdTipoTestamento());
									ltt_tipoTestamento = DaoCreator.getTipoTestamentoDAO(ldm_manager)
											                           .findById(ltt_tipoTestamento);

									if(
									    (ltt_tipoTestamento != null)
										    && StringUtils.isValidString(ltt_tipoTestamento.getIdTipoTestamento())
									)
									{
										lt_testamentoAnterior.setNombreTipoTestamento(ltt_tipoTestamento.getNombre());

										Solicitud ls_solicitudAnterior;
										ls_solicitudAnterior = DaoCreator.getSolicitudDAO(ldm_manager)
												                             .findByIdTestamento(
												    lt_testamentoAnterior.getIdTestamento()
												);

										if(ls_solicitudAnterior != null)
										{
											Documento ld_documentoAnterior;
											ld_documentoAnterior = DaoCreator.getDocumentoDAO(ldm_manager)
													                             .findById(
													    ls_solicitudAnterior.getIdDocumento()
													);

											if(ld_documentoAnterior != null)
											{
												{
													TipoDocumentoPublico ltdp_tipoDocumentoPublico;

													ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();

													ltdp_tipoDocumentoPublico.setIdTipoDocumento(
													    ld_documentoAnterior.getIdTipoDocumento()
													);

													ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
														    ldm_manager
														).findById(ltdp_tipoDocumentoPublico);

													if(ltdp_tipoDocumentoPublico != null)
														ld_documentoAnterior.setTipoDocumento(
														    ltdp_tipoDocumentoPublico
														);
												}

												{
													TipoOficina lto_tipoOficina;

													lto_tipoOficina = new TipoOficina();

													lto_tipoOficina.setIdTipoOficina(
													    ld_documentoAnterior.getIdTipoOficina()
													);

													lto_tipoOficina = DaoCreator.getTipoOficinaDAO(ldm_manager)
															                        .findById(lto_tipoOficina);

													if(
													    (lto_tipoOficina != null)
														    && StringUtils.isValidString(
														        lto_tipoOficina.getIdTipoOficina()
														    )
													)
														ld_documentoAnterior.setTipoOficina(lto_tipoOficina);
												}

												lst_solicitudTestamentos.setDocumentoAnterior(ld_documentoAnterior);
											}
										}
									}

									lst_solicitudTestamentos.setTestamentoAnterior(lt_testamentoAnterior);
								}
							}
						}

						lst_solicitudTestamentos.setTestamento(lt_testamento);
					}

					{
						SolicitudInterviniente lsi_solicitudInterviniente;

						lsi_solicitudInterviniente = new SolicitudInterviniente();

						lsi_solicitudInterviniente.setIdSolicitud(ls_solicitud.getIdSolicitud());

						lsi_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
								                                   .findByIdSolicitudUltimo(lsi_solicitudInterviniente);

						if(
						    (lsi_solicitudInterviniente != null)
							    && StringUtils.isValidString(lsi_solicitudInterviniente.getIdPersona())
						)
						{
							lst_solicitudTestamentos.setSolicitudInterviniente(lsi_solicitudInterviniente);

							Persona lp_persona;

							lp_persona = new Persona();

							lp_persona.setIdPersona(lsi_solicitudInterviniente.getIdPersona());

							lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_persona);

							if((lp_persona != null) && StringUtils.isValidString(lp_persona.getIdPersona()))
							{
								lst_solicitudTestamentos.setPersona(lp_persona);

								InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;

								lidt_interesadoDocumentoTipo = new InteresadoDocumentoTipo();

								lidt_interesadoDocumentoTipo.setIdDocumentoTipo(lp_persona.getIdDocumentoTipo());

								lidt_interesadoDocumentoTipo = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
										                                     .findById(lidt_interesadoDocumentoTipo);

								if(lidt_interesadoDocumentoTipo != null)
									lst_solicitudTestamentos.setInteresadoDocumentoTipo(lidt_interesadoDocumentoTipo);
							}
						}
					}

					{
						CalidadSolicitante lcs_calidadSolicitante;

						lcs_calidadSolicitante = new CalidadSolicitante();

						lcs_calidadSolicitante.setIdCalidadSolicitante(ls_solicitud.getIdCalidadSolicitante());

						lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(ldm_manager)
								                               .findById(lcs_calidadSolicitante);

						if(lcs_calidadSolicitante != null)
							lst_solicitudTestamentos.setCalidadSolicitante(lcs_calidadSolicitante);
					}
				}

				lst_solicitudTestamentos.setSolicitud(ls_solicitud);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultaBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lst_solicitudTestamentos;
	}

	/**
	 * guardar Constancia Reproduccion Testamento
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException
	 */
	public synchronized byte[] guardarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String                           as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			lba_return = generarConstanciaReproduccionTestamento(
				    ast_solicitudTestamento, as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, true, false
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("guardarConstanciaTestamento", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Construye el formulario de correcciones
	 *
	 * @param as_idTurnoHistoria id turno historia del tramite en proceso
	 * @param as_userId id del usuario que ejecuta la acción
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException
	 */
	public synchronized byte[] guardarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_return      = null;

		try
		{
			lba_return = generarConstanciaTestamento(
				    ast_solicitudTestamento, as_nir, as_idTurnoHistoria, as_userId, as_remoteIp, ldm_manager, true,
				    false
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("guardarConstanciaTestamento", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Método de transacciones para la base de datos de reproducción de constancia de testamentos.
	 *
	 * @param arct_reproduccionConstanciaTestamento con los parametros a guardar en solicitud de reproduccion
	 * @param as_cantidadCopias parametro a actualizar en SDB_ACC_TESTAMENTOS
	 * @param ab_copias Validación si se actualiza en SDB-ACC_TESTAMENTOS
	 * @param as_userId con el usuario que realiza la transaccion
	 * @param as_localIp con la dirección local de quien realiza la transaccion
	 * @param as_remoteIp con la ip remota de quien realiza la transaccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarSolicitudReproduccion(
	    ReproduccionConstanciaTestamento arct_reproduccionConstanciaTestamento, String as_cantidadCopias,
	    boolean                          ab_copias, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(arct_reproduccionConstanciaTestamento != null)
			{
				Turno lt_turno;
				lt_turno = arct_reproduccionConstanciaTestamento.getTurno();

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lt_turno.getIdSolicitud());

					if(ls_solicitud != null)
					{
						if(!ab_copias)
						{
							SolicitudReproduccion ls_solicitudReproduccion;

							ls_solicitudReproduccion = new SolicitudReproduccion();
							ls_solicitudReproduccion.setIdSolicitud(ls_solicitud.getIdSolicitud());
							ls_solicitudReproduccion.setIdTurnoReproduccion(lt_turno.getIdTurno());
							ls_solicitudReproduccion.setIdCirculo(lt_turno.getIdCirculo());
							ls_solicitudReproduccion.setIdUsuarioCreacion(as_userId);
							ls_solicitudReproduccion.setIdUsuarioCreacion(as_localIp);
							ls_solicitudReproduccion.setIpCreacion(as_remoteIp);

							DaoCreator.getConsultaSolicitudReproduccionDAO(ldm_manager).insert(
							    ls_solicitudReproduccion
							);
						}
						else
						{
							Testamento lt_testamento;
							lt_testamento = arct_reproduccionConstanciaTestamento.getTestamento();

							if(lt_testamento != null)
							{
								lt_testamento.setNumeroCopias(as_cantidadCopias);
								updateTestamento(lt_testamento, as_userId, as_localIp, ldm_manager);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("guardarConstanciaTestamento", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de  consultar el detalle de un registro  de trazabilidad para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo BigDecimal que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized BigDecimal obtenerEtapaActual(String as_idTurnoHistoria)
	    throws B2BException
	{
		BigDecimal lbd_result;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lbd_result      = null;

		try
		{
			TurnoHistoriaDAO lth_DAO;
			lth_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
				lbd_result = lth_turnoHistoria.getIdEtapa();
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerEtapaActual", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_result;
	}

	/**
	 *  Método encargado de  consultar el detalle de un registro  de trazabilidad para un id turno historia determinado.
	 *
	 * @param as_idTurnoHistoria Variable de tipo String que contiene un id turno historia determinado.
	 * @return Retorna  una variable de tipo BigDecimal que coincide con los parametros de entrada.
	 * @throws B2BException
	 */
	public synchronized BigDecimal obtenerEtapaAnterior(String as_idTurnoHistoria)
	    throws B2BException
	{
		BigDecimal lbd_result;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lbd_result      = null;

		try
		{
			TurnoHistoriaDAO lth_DAO;
			TurnoHistoria    lth_turnoHistoria;
			TurnoHistoria    lth_temp;

			lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lth_turnoHistoria     = new TurnoHistoria();
			lth_temp              = new TurnoHistoria();

			lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(as_idTurnoHistoria));

			lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria != null)
			{
				lth_temp = lth_DAO.findByIdAnterior(lth_turnoHistoria);

				if(lth_temp != null)
					lbd_result = lth_temp.getIdEtapa();
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerEtapaAnterior", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_result;
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Tipo Documental Acto.
	 *
	 * @param atd_parametros            objeto a insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @param adm_manager correspondiente al valor de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarLibroTestamento(
	    LibroTestamento atd_parametros, String as_usuario, String as_ip, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(atd_parametros != null)
			{
				atd_parametros.setIdUsuarioModificacion(as_usuario);
				atd_parametros.setIpModificacion(as_ip);

				DaoCreator.getLibroTestamentoDAO(adm_manager).update(atd_parametros, false);
			}
			else
				throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarTipoDocumentalActo", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método para salvar lo realizado ya sea una inserción o modificación de Tipo Documental Acto.
	 *
	 * @param atd_parametros            objeto a insertar o modificar
	 * @param as_usuario            usuario que realiza la acción de salvar
	 * @param as_ip            ip de donde se invoca el metodo de salvar
	 * @param adm_manager correspondiente al valor de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void updateTestamento(
	    Testamento atd_parametros, String as_usuario, String as_ip, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(atd_parametros != null)
			{
				atd_parametros.setIdUsuarioModificacion(as_usuario);
				atd_parametros.setIpModificacion(as_ip);

				DaoCreator.getTestamentoDAO(adm_manager).update(atd_parametros);
			}
			else
				throw new B2BException(ErrorKeys.TESTAMENTO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("updateTestamento", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar si se muestra la bandeja de calificación completa.
	 *
	 * @return Variable de tipo boolean que define si se muestra la bandeja de calificación completa.
	 * @throws B2BException
	 */
	public synchronized boolean visualizarBandeja()
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			Constantes    lc_constante;
			ConstantesDAO lc_DAO;

			lc_constante     = new Constantes();
			lc_DAO           = DaoCreator.getConstantesDAO(ldm_manager);

			lc_constante.setIdConstante(ConstanteCommon.VISUALIZAR_DETALLE_CALIFICACION);

			lc_constante = lc_DAO.findById(lc_constante);

			if(lc_constante != null)
			{
				String ls_caracter;

				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
					lb_return = ls_caracter.equalsIgnoreCase(EstadoCommon.S);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("visualizarBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Método de generación de la constancia de testamento.
	 *
	 * @param ast_solicitudTestamento  con los parametros
	 * @param as_idTurnoHistoria correspondiente al valor de as id turno historia
	 * @param adm_manager correspondiente al valor de adm manager
	 * @param as_userId con el usuario de la transacción
	 * @param as_remoteIp con la ip de la transacción
	 * @param ab_salvar para saber si se salva en BD
	 * @param ab_firma para identificar si viene de firma masiva
	 * @return tipo byte con el documento generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] generarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, DAOManager adm_manager,
	    String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[] lba_return;
		String ls_plantilla;
		ls_plantilla = obtenerPlantillaDeConstante(
			    adm_manager, ConstanteCommon.PLANTILLA_CONSTANCIA_REPRODUCCION_TESTAMENTOS
			);

		String ls_tag;
		ls_tag         = null;
		lba_return     = null;

		if(StringUtils.isValidString(ls_plantilla))
		{
			if(ast_solicitudTestamento != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(as_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Turno lt_turno;
					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurnoHistoria);

					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(lt_turno != null)
					{
						{
							ls_tag = "<TAG_DIA>";

							Date             ld_date   = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("dd");
							String           ls_date   = formatter.format(ld_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
						}

						{
							ls_tag = "<TAG_MES>";

							Date             ld_date   = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("MM");
							String           ls_date   = formatter.format(ld_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
						}

						{
							ls_tag = "<TAG_ANHO>";

							Date             ld_date   = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
							String           ls_date   = formatter.format(ld_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
						}

						{
							ls_tag = "<TAG_HORA>";

							Date             ld_date     = new Date();
							SimpleDateFormat formatter2  = new SimpleDateFormat("hh: mm: ss aa");
							String           ls_dateHour = formatter2.format(ld_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_dateHour);
						}

						CirculoRegistral lcr_circuloRegistral;
						lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
								                             .findById(lt_turno.getIdCirculo());

						if(lcr_circuloRegistral != null)
						{
							OficinaOrigen loo_oficinaOrigen;
							loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
									                          .findById(
									    lcr_circuloRegistral.getIdOficinaOrigen(), NumericUtils.getBigDecimal(1)
									);

							if(loo_oficinaOrigen != null)
							{
								{
									ls_tag = "<TAG_OFICINA_DE_ORIGEN>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(loo_oficinaOrigen.getNombre())
											);
								}

								{
									Pais lp_pais;
									lp_pais = DaoCreator.getPaisDAO(adm_manager).findById(
										    loo_oficinaOrigen.getIdPais()
										);

									if(lp_pais != null)
									{
										Departamento ld_departamento;
										ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
												                        .findById(
												    loo_oficinaOrigen.getIdPais(), loo_oficinaOrigen.getIdDepartamento()
												);

										if(ld_departamento != null)
										{
											{
												ls_tag = "<DEPARTAMENTO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getStringNotNull(ld_departamento.getNombre())
														);
											}

											Municipio lm_municipio;
											lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
													                     .findById(
													    loo_oficinaOrigen.getIdPais(),
													    loo_oficinaOrigen.getIdDepartamento(),
													    loo_oficinaOrigen.getIdMunicipio()
													);

											if(lm_municipio != null)
											{
												{
													ls_tag = "<TAG_MUNICIPIO>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag,
															    StringUtils.getStringNotNull(lm_municipio.getNombre())
															);
												}
											}
										}
									}
								}
							}
						}

						if(ls_solicitud != null)
						{
							{
								ls_tag = "<TAG_ID_NIR>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(ls_solicitud.getNir())
										);
							}

							{
								ls_tag = "<TAG_ID_TURNO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lt_turno.getIdTurno())
										);
							}
						}
					}

					Documento ld_documento;
					ld_documento = ast_solicitudTestamento.getDocumento();

					if(ld_documento != null)
					{
						{
							ls_tag = "<TAG_TIPO_DE_DOCUMENTO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(
									    ls_tag, StringUtils.getStringNotNull(ld_documento.getIdTipoDocumento())
									);
						}

						{
							ls_tag = "<TAG_NUMERO_DE_DOCUMENTO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(
									    ls_tag, StringUtils.getStringNotNull(ld_documento.getNumero())
									);
						}

						{
							ls_tag = "<TAG_FECHA_DE_DOCUMENTO>";

							Date             ld_date   = ld_documento.getFechaDocumento();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							String           ls_date   = formatter.format(ld_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getStringNotNull(ls_date));
						}

						{
							Testamento lt_testamento;
							lt_testamento = ast_solicitudTestamento.getTestamento();

							if(lt_testamento != null)
							{
								Persona lp_persona;
								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(
									    lt_testamento.getIdPersona()
									);

								if(lp_persona != null)
								{
									{
										ls_tag = "<TAG_TIPO_DE_DOCUMENTO_DE_IDENTIFICACION>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getStringNotNull(lp_persona.getIdDocumentoTipo())
												);
									}

									{
										ls_tag = "<TAG_NUMERO_DE_IDENTIFICACION>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getStringNotNull(lp_persona.getNumeroDocumento())
												);
									}

									{
										ls_tag = "<TAG_PRIMER_APELLIDO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(
												        lp_persona.getPrimerApellido()
												    )
												);
									}

									{
										ls_tag = "<TAG_SEGUNDO_APELLIDO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getStringNotNull(lp_persona.getSegundoApellido())
												);
									}

									{
										ls_tag = "<TAG_PRIMER_NOMBRE>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(lp_persona.getPrimerNombre())
												);
									}

									{
										ls_tag = "<TAG_SEGUNDO_NOMBRE>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(lp_persona.getSegundoNombre())
												);
									}
								}

								{
									ls_tag = "<TAG_TOMO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(lt_testamento.getTomo())
											);
								}

								{
									ls_tag = "<TAG_FOLIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(lt_testamento.getFolio())
											);
								}

								{
									ls_tag = "<TAG_ANO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(lt_testamento.getAnoTestamento())
											);
								}

								{
									ls_tag = "<TAG_FECHA_CREACION>";

									Date             ld_date   = lt_testamento.getFechaRegistro();
									SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
									String           ls_date   = formatter.format(ld_date);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(ls_date)
											);
								}

								{
									ls_tag = "<TAG_ID_TIPO_TESTAMENTO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    StringUtils.getStringNotNull(lt_testamento.getNombreTipoTestamento())
											);
								}

								{
									ls_tag = "<TAG_OBSERVACIONES>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(lt_testamento.getObservacion())
											);
								}
							}
						}
					}

					Map<String, String> lmss_datos;

					lmss_datos       = null;
					lmss_datos       = finalizarPlantilla(
						    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), true, adm_manager
						);
					ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

					lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

					if((lba_return != null) && (ab_salvar || ab_firma))
					{
						Imagenes    li_imagenes;
						ImagenesDAO li_DAO;
						long        ll_idImagenTemp;

						li_imagenes     = new Imagenes();
						li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);
						li_imagenes.setImagenBlob(lba_return);
						li_imagenes.setCodigoVerificacion(lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION));

						ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

						if(ll_idImagenTemp > 0)
						{
							DocumentosSalida    lds_documentoSalida;
							DocumentosSalidaDAO lds_DAO;
							Long                ll_idImagen;

							lds_documentoSalida     = new DocumentosSalida();
							lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
							ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

							lds_documentoSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lds_documentoSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
							lds_documentoSalida.setTipo(TipoArchivoCommon.CONSTANCIA_REPRODUCCION_TESTAMENTOS);
							lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_REPRODUCCION);
							lds_documentoSalida.setRepositorio(
							    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
							);
							lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentoSalida.setReferenciaSalida(lth_turnoHistoria.getIdTurno());

							lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
								    lds_documentoSalida
								);

							if(lds_documentoSalida != null)
							{
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setIdUsuarioModificacion(as_userId);
								lds_documentoSalida.setIpModificacion(as_remoteIp);

								lds_DAO.updateImagen(lds_documentoSalida);
							}
							else
							{
								lds_documentoSalida = new DocumentosSalida();

								lds_documentoSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
								lds_documentoSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setTipo(TipoArchivoCommon.CONSTANCIA_REPRODUCCION_TESTAMENTOS);
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_REPRODUCCION);
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setReferenciaSalida(lth_turnoHistoria.getIdTurno());
								lds_documentoSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
								);
								lds_documentoSalida.setRepositorio(
								    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
								);
								lds_documentoSalida.setIdUsuarioCreacion(as_userId);
								lds_documentoSalida.setIpCreacion(as_remoteIp);

								lds_DAO.insertOrUpdate(lds_documentoSalida, true);
							}
						}
					}
				}
			}
		}

		return lba_return;
	}

	/**
	 * Método de generación de la constancia de Testamento
	 * @param ast_solicitudTestamento con la Solicitud, el testamento y el documento
	 * @param as_nir con el nir del turno
	 * @param as_idTurnoHistoria turno actual
	 * @param as_userId usuario que genera el pdf
	 * @param as_remoteIp la ip que genera el pdf
	 * @param ldm_manager con el manejo de la transacción
	 * @param ab_salvar con la acción de salvar
	 * @param ab_firma con la acción de agregar firma
	 * @return la plantilla resuelta
	 * @throws B2BException
	 */
	private synchronized byte[] generarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_remoteIp, DAOManager ldm_manager, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = null;

		if(!StringUtils.isValidString(as_idTurnoHistoria))
			throw new B2BException(ErrorKeys.TURNO_HISTORIA_INVALIDO);

		Long          ll_idTurnoHistoria;
		TurnoHistoria lth_turnoHistoria;

		ll_idTurnoHistoria     = NumericUtils.getLongWrapper(as_idTurnoHistoria);
		lth_turnoHistoria      = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(ll_idTurnoHistoria);

		if(lth_turnoHistoria != null)
			lth_turnoHistoria.setNir(as_nir);

		lba_return = generarConstanciaTestamento(
			    ast_solicitudTestamento, ldm_manager, lth_turnoHistoria, as_userId, as_remoteIp, ab_salvar, ab_firma
			);

		return lba_return;
	}

	/**
	 * Construye el formulario de correcciones.
	 *
	 * @param ast_solicitudTestamento correspondiente al valor de ast solicitud testamento
	 * @param adm_manager Conexión a la base de datos
	 * @param as_idTurnoHistoria id del turno historia actual
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @param ab_salvar correspondiente al valor de ab salvar
	 * @param ab_firma correspondiente al valor de ab firma
	 * @return arreglo de bytes correspondiente al documento generado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private byte[] generarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, DAOManager adm_manager, TurnoHistoria as_idTurnoHistoria,
	    String as_userId, String as_remoteIp, boolean ab_salvar, boolean ab_firma
	)
	    throws B2BException
	{
		byte[] lba_return;
		String ls_plantilla;

		lba_return = null;

		if(!StringUtils.isValidString(as_idTurnoHistoria.getIdTurno()))
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

		ls_plantilla = obtenerPlantillaDeConstante(adm_manager, ConstanteCommon.PLANTILLA_CONSTANCIA_TESTAMENTOS);

		if(StringUtils.isValidString(ls_plantilla))
		{
			SolicitudTestamento lst_solicitudTestamento;

			lst_solicitudTestamento = generarDataTestamentos(as_idTurnoHistoria.getNir());

			if(lst_solicitudTestamento != null)
			{
				Solicitud ls_solicitud;
				ls_solicitud = lst_solicitudTestamento.getSolicitud();

				if(ls_solicitud != null)
				{
					String ls_tag;

					{
						ls_tag = "<TAG_SYSDATE_DAY>";

						Date             ld_date   = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						String           ls_date   = formatter.format(ld_date);

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
					}

					{
						ls_tag = "<TAG_SYSDATE_HOUR>";

						Date             ld_date     = new Date();
						SimpleDateFormat formatter2  = new SimpleDateFormat("hh: mm: ss aa");
						String           ls_dateHour = formatter2.format(ld_date);

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_dateHour);
					}

					{
						ls_tag = "<TAG_ID_TURNO>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(
								    ls_tag, StringUtils.getStringNotNull(as_idTurnoHistoria.getIdTurno())
								);
					}

					{
						ls_tag = "<TAG_NIR>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(
								    ls_tag, StringUtils.getStringNotNull(as_idTurnoHistoria.getNir())
								);
					}

					{
						Documento ld_documento;
						ld_documento = lst_solicitudTestamento.getDocumento();

						if(ld_documento != null)
						{
							{
								ls_tag = "<TAG_MUNICIPIO>";

								Municipio lm_municipio;
								lm_municipio = ld_documento.getMunicipio();

								if((lm_municipio != null) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lm_municipio.getNombre())
										);
							}

							{
								ls_tag = "<TAG_TIPO_DE_DOCUMENTO>";

								TipoDocumentoPublico ltdp_tipoDocumentoPublico;
								ltdp_tipoDocumentoPublico = ld_documento.getTipoDocumento();

								if((ltdp_tipoDocumentoPublico != null) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag,
										    StringUtils.getStringNotNull(ltdp_tipoDocumentoPublico.getNombre()) + " "
										);
							}

							{
								ls_tag = "<TAG_NUMERO_DE_DOCUMENTO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(ld_documento.getNumero()) + " "
										);
							}

							{
								ls_tag = "<TAG_FECHA_DE_DOCUMENTO>";

								Date             ld_date   = ld_documento.getFechaDocumento();
								SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
								String           ls_date   = formatter.format(ld_date);

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(ls_date) + " "
										);
							}

							{
								ls_tag = "<TAG_OFICINA_DE_ORIGEN>";

								OficinaOrigen loo_oficinaOrigen;
								loo_oficinaOrigen = ld_documento.getOficinaOrigen();

								if((loo_oficinaOrigen != null) && ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(loo_oficinaOrigen.getNombre())
										);
							}
						}
					}

					{
						Persona lp_persona;
						lp_persona = lst_solicitudTestamento.getPersona();

						if(lp_persona != null)
						{
							{
								ls_tag = "<TAG_PRIMER_APELLIDO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lp_persona.getPrimerApellido()) + " "
										);
							}

							{
								ls_tag = "<TAG_SEGUNDO_APELLIDO>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lp_persona.getSegundoApellido()) + " "
										);
							}

							{
								ls_tag = "<TAG_PRIMER_NOMBRE>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lp_persona.getPrimerNombre()) + " "
										);
							}

							{
								ls_tag = "<TAG_SEGUNDO_NOMBRE>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lp_persona.getSegundoNombre())
										);
							}

							{
								ls_tag = "<TAG_TIPO_DE_DOCUMENTO_DE_IDENTIFICACION>";

								InteresadoDocumentoTipo lidt_interesadoDocumentoTipo;
								lidt_interesadoDocumentoTipo = lst_solicitudTestamento.getInteresadoDocumentoTipo();

								if(ls_plantilla.contains(ls_tag) && (lidt_interesadoDocumentoTipo != null))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag,
										    StringUtils.getStringNotNull(lidt_interesadoDocumentoTipo.getDescripcion())
										    + " "
										);
							}

							{
								ls_tag = "<TAG_NUMERO_DE_IDENTIFICACION>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(
										    ls_tag, StringUtils.getStringNotNull(lp_persona.getNumeroDocumento())
										);
							}
						}

						{
							Testamento lt_testamento;
							lt_testamento = lst_solicitudTestamento.getTestamento();

							Testamento lt_testamentoTemp;
							lt_testamentoTemp = ast_solicitudTestamento.getTestamento();

							if(!ab_salvar && (ast_solicitudTestamento != null))
							{
								LibroTestamento llt_libroTestamento;
								llt_libroTestamento = ast_solicitudTestamento.getLibroTestamento();

								if(llt_libroTestamento != null)
								{
									{
										ls_tag = "<TAG_TOMO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(llt_libroTestamento.getTomo())
												);
									}

									{
										ls_tag = "<TAG_FOLIO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(llt_libroTestamento.getFolio())
												);
									}

									{
										ls_tag = "<TAG_ANO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getString(llt_libroTestamento.getAno())
												);
									}

									{
										ls_tag = "<TAG_FECHA_CREACION>";

										Date             ld_date   = new Date();
										SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
										String           ls_date   = formatter.format(ld_date);

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, StringUtils.getStringNotNull(ls_date)
												);
									}

									{
										ls_tag = "<TAG_OBSERVACIONES>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag,
												    StringUtils.getStringNotNull(lt_testamentoTemp.getObservacion())
												);
									}
								}
							}
							else if(ab_salvar)
							{
								LibroTestamento llt_libroTestamento;
								llt_libroTestamento     = ast_solicitudTestamento.getLibroTestamento();
								llt_libroTestamento     = findLibroTestamento(
									    llt_libroTestamento, as_userId, as_remoteIp
									);

								{
									ls_tag = "<TAG_OBSERVACIONES>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(
											        lt_testamentoTemp.getObservacion()
											    )
											);

									lt_testamento.setObservacion(lt_testamentoTemp.getObservacion());
								}

								{
									ls_tag = "<TAG_FOLIO>";

									long ll_folio;
									long ll_tomo;
									long ll_libro;

									Long lL_folioAsignar;
									Long iL_libroAsignar;
									Long iL_tomoAsignar;
									ll_folio            = NumericUtils.getLong(llt_libroTestamento.getFolio());
									ll_tomo             = NumericUtils.getLong(llt_libroTestamento.getTomo());
									ll_libro            = NumericUtils.getLong(llt_libroTestamento.getLibro());
									lL_folioAsignar     = null;
									iL_libroAsignar     = null;
									iL_tomoAsignar      = null;

									if((ll_folio >= 1L) && (ll_folio <= 300L))
									{
										lL_folioAsignar     = NumericUtils.getLongWrapper(ll_folio);
										ll_folio            = ll_folio + 1L;
									}

									if(ll_folio > 300)
									{
										ll_libro     = ll_libro + 1L;
										ll_folio     = 1L;
									}

									if(ll_libro > 300L)
									{
										ll_tomo      = ll_tomo + 1L;
										ll_libro     = 1L;
									}

									iL_tomoAsignar     = NumericUtils.getLongWrapper(ll_tomo);

									iL_libroAsignar = NumericUtils.getLongWrapper(ll_libro);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(llt_libroTestamento.getFolio())
											);

									lt_testamento.setFolio(lL_folioAsignar);
									lt_testamento.setLibro(iL_libroAsignar);
									llt_libroTestamento.setFolio(NumericUtils.getLongWrapper(ll_folio));
									llt_libroTestamento.setLibro(NumericUtils.getLongWrapper(ll_libro));

									ls_tag = "<TAG_TOMO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(iL_tomoAsignar)
											);

									lt_testamento.setTomo(iL_tomoAsignar);
									llt_libroTestamento.setTomo(NumericUtils.getLongWrapper(ll_tomo));

									ls_tag = "<TAG_ANO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(llt_libroTestamento.getAno())
											);

									lt_testamento.setAnoTestamento(llt_libroTestamento.getAno());
								}

								{
									ls_tag = "<TAG_FECHA_CREACION>";

									Date             ld_date   = new Date();
									SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
									String           ls_date   = formatter.format(ld_date);

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(ls_date)
											);

									lt_testamento.setFechaRegistro(ld_date);
								}

								lt_testamento.setIdTurno(as_idTurnoHistoria.getIdTurno());

								salvarLibroTestamento(llt_libroTestamento, as_userId, as_remoteIp, adm_manager);

								updateTestamento(lt_testamento, as_userId, as_remoteIp, adm_manager);
							}

							if(lt_testamento != null)
							{
								{
									ls_tag = "<TAG_ID_TIPO_TESTAMENTO>";

									String ls_tipoTestamento;
									ls_tipoTestamento = lt_testamento.getNombreTipoTestamento();

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getStringNotNull(ls_tipoTestamento)
											);

									if(!ls_tipoTestamento.equals("INSCRIPCION"))
									{
										Testamento lt_testamentoAnterior;
										lt_testamentoAnterior = lst_solicitudTestamento.getTestamentoAnterior();

										if(lt_testamentoAnterior != null)
										{
											String ls_tipoTestamentoAnt;
											ls_tipoTestamentoAnt     = lt_testamentoAnterior.getNombreTipoTestamento();

											ls_tag = "<TAG_ID_TIPO_TESTAMENTO_ANT>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag, StringUtils.getStringNotNull(ls_tipoTestamentoAnt)
													);

											Solicitud ls_solicitudAnt;
											ls_solicitudAnt     = new Solicitud();
											ls_solicitudAnt     = DaoCreator.getSolicitudDAO(adm_manager)
													                            .findByIdTestamento(
													    lt_testamentoAnterior.getIdTestamento()
													);

											if(ls_solicitudAnt != null)
											{
												Documento ld_documentoAnt;
												ld_documentoAnt = DaoCreator.getDocumentoDAO(adm_manager)
														                        .findById(
														    ls_solicitudAnt.getIdDocumento()
														);

												if(ld_documentoAnt != null)
												{
													{
														ls_tag = "<TAG_NUMERO_DE_DOCUMENTO_ANT>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag,
																    StringUtils.getStringNotNull(
																        ld_documentoAnt.getNumero()
																    )
																);

														ls_tag = "<TAG_FECHA_DE_DOCUMENTO_ANT>";

														Date             ld_date   = ld_documentoAnt.getFechaDocumento();
														SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
														String           ls_date   = formatter.format(ld_date);

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, StringUtils.getStringNotNull(ls_date) + " "
																);
													}

													{
														TipoDocumentoPublico ltdp_tipoDocumentoPublico;
														ltdp_tipoDocumentoPublico = new TipoDocumentoPublico();
														ltdp_tipoDocumentoPublico.setIdTipoDocumento(
														    ld_documentoAnt.getIdTipoDocumento()
														);
														ltdp_tipoDocumentoPublico     = DaoCreator.getTipoDocumentoPublicoDAO(
															    adm_manager
															).findById(ltdp_tipoDocumentoPublico);

														ls_tag = "<TAG_TIPO_DE_DOCUMENTO_ANT>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag,
																    StringUtils.getStringNotNull(
																        ltdp_tipoDocumentoPublico.getNombre()
																    )
																);

														OficinaOrigen loo_oficinaOrigen;
														loo_oficinaOrigen = new OficinaOrigen();
														loo_oficinaOrigen.setIdOficinaOrigen(
														    ld_documentoAnt.getIdOficinaOrigen()
														);
														loo_oficinaOrigen.setVersion(ld_documentoAnt.getVersion());
														loo_oficinaOrigen     = DaoCreator.getOficinaOrigenDAO(
															    adm_manager
															).findById(loo_oficinaOrigen);

														ls_tag = "<TAG_OFICINA_DE_ORIGEN_ANT>";

														if(ls_plantilla.contains(ls_tag))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag,
																    StringUtils.getStringNotNull(
																        loo_oficinaOrigen.getNombre()
																    )
																);
													}
												}
											}
										}
									}
								}
							}
						}
					}

					Map<String, String> lmss_datos;

					lmss_datos       = null;
					lmss_datos       = finalizarPlantilla(
						    ls_plantilla, as_idTurnoHistoria.getIdTurnoHistoria(), true, adm_manager
						);
					ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

					lba_return = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), adm_manager);

					if((lba_return != null) && (ab_salvar || ab_firma))
					{
						Imagenes    li_imagenes;
						ImagenesDAO li_DAO;
						long        ll_idImagenTemp;

						li_imagenes     = new Imagenes();
						li_DAO          = DaoCreator.getImagenesDAO(adm_manager);

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);
						li_imagenes.setImagenBlob(lba_return);
						li_imagenes.setCodigoVerificacion(lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION));

						ll_idImagenTemp = li_DAO.insertOrUpdate(li_imagenes, true);

						if(ll_idImagenTemp > 0)
						{
							DocumentosSalida    lds_documentoSalida;
							DocumentosSalidaDAO lds_DAO;
							Long                ll_idImagen;

							lds_documentoSalida     = new DocumentosSalida();
							lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
							ll_idImagen             = NumericUtils.getLongWrapper(ll_idImagenTemp);

							lds_documentoSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lds_documentoSalida.setIdTurno(as_idTurnoHistoria.getIdTurno());
							lds_documentoSalida.setTipo(TipoArchivoCommon.CONSTANCIA_INSCRIPCION_TESTAMENTOS);
							lds_documentoSalida.setIdTipoDocumental(
							    TipoDocumentalCommon.CONSTANCIA_INSCRIPCION_TESTAMENTOS
							);
							lds_documentoSalida.setRepositorio(
							    ab_firma ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
							);
							lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
							lds_documentoSalida.setReferenciaSalida(as_idTurnoHistoria.getIdTurno());

							lds_documentoSalida = lds_DAO.findDocumentByTurnoTipoEstadoReferenciaSalida(
								    lds_documentoSalida
								);

							if(lds_documentoSalida != null)
							{
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setIdUsuarioModificacion(as_userId);
								lds_documentoSalida.setIpModificacion(as_remoteIp);

								lds_DAO.updateImagen(lds_documentoSalida);
							}
							else
							{
								lds_documentoSalida = new DocumentosSalida();

								lds_documentoSalida.setIdTurno(as_idTurnoHistoria.getIdTurno());
								lds_documentoSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
								lds_documentoSalida.setIdImagen(ll_idImagen);
								lds_documentoSalida.setTipo(TipoArchivoCommon.CONSTANCIA_INSCRIPCION_TESTAMENTOS);
								lds_documentoSalida.setIdTipoDocumental(
								    TipoDocumentalCommon.CONSTANCIA_INSCRIPCION_TESTAMENTOS
								);
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setReferenciaSalida(as_idTurnoHistoria.getIdTurno());
								lds_documentoSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(as_idTurnoHistoria.getIdTurnoHistoria())
								);
								lds_documentoSalida.setIdUsuarioCreacion(as_userId);
								lds_documentoSalida.setIpCreacion(as_remoteIp);

								lds_DAO.insertOrUpdate(lds_documentoSalida, true);
							}
						}
					}
				}
			}
		}

		return lba_return;
	}
}
