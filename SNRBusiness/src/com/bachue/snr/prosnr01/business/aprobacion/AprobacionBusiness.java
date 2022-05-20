package com.bachue.snr.prosnr01.business.aprobacion;

import com.aspose.words.CellMerge;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.reference.ReferenceBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.CausalAprobacionDevolucionCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.LineaProduccionCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.MotivonNoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDecisionRecursoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecursoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.DireccionPredioAccDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.RecursoDAO;
import com.bachue.snr.prosnr01.dao.acc.RecursoDecisionDAO;
import com.bachue.snr.prosnr01.dao.acc.RelacionAprobacionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudAsociadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TrasladoMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDerivadoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioDAO;
import com.bachue.snr.prosnr01.dao.aut.UsuarioRolDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDecisionRecursoDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.CambioEstadoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.FirmaMasiva;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Recurso;
import com.bachue.snr.prosnr01.model.sdb.acc.RecursoDecision;
import com.bachue.snr.prosnr01.model.sdb.acc.RelacionAprobacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.UsuarioLinea;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import java.awt.Color;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase para el manejo del negocio de aprobación incluyendo el visto bueno del registrador.
 * @author Alejandro Santos
 *
 */
public class AprobacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AprobacionBusiness.class);

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
	 * Método de actualizacion de relacion y observaciones del turno historia
	 * @param aa_aprobacion con los parametros de la aprobacion a actualizar
	 * @throws B2BException
	 */
	public void actualizarTurnoHistoriaRelacion(Aprobacion aa_aprobacion, String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		DAOManager ldm_manager;
		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_aprobacion != null)
			{
				TurnoHistoriaDAO lth_DAO;
				lth_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = lth_DAO.findById(aa_aprobacion.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria.setIpModificacion(as_ipRemota);
					lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);
					lth_turnoHistoria.setGenerarRelacion(EstadoCommon.SI);
					lth_turnoHistoria.setObservaciones(aa_aprobacion.getObservacionesAprobador());

					lth_DAO.insertOrUpdate(lth_turnoHistoria, false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarTurnoHistoriaRelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar si el proceso es una adjudicación de remate.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el turno historia del proceso.
	 * @return Variable de tipo boolean que valida si el proceso es una adjudicación de remate.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean adjudicacionRemate(Long al_idTurnoHistoria)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				TurnoHistoria    lth_turnoHistoria;
				TurnoHistoriaDAO lth_DOA;

				lth_turnoHistoria     = new TurnoHistoria();
				lth_DOA               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);

				lth_turnoHistoria = lth_DOA.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_motivoNoTramite;

					ls_motivoNoTramite = lth_turnoHistoria.getMotivoNoTramite();

					if(StringUtils.isValidString(ls_motivoNoTramite))
						lb_return = ls_motivoNoTramite.equalsIgnoreCase(
							    MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_REMATE
							) || ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.INSCRITO_CON_ACLARACION)
								|| ls_motivoNoTramite.equalsIgnoreCase(
								    MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_LIQUIDACION_JUDICIAL
								);

					if(!lb_return)
					{
						String ls_idTurno;

						ls_idTurno = lth_turnoHistoria.getIdTurno();

						if(StringUtils.isValidString(ls_idTurno))
						{
							Collection<TurnoDerivado> lctd_turnosDerivados;
							TurnoDerivado             ltd_turnoDerivado;

							ltd_turnoDerivado = new TurnoDerivado();

							ltd_turnoDerivado.setIdTurnoPadre(ls_idTurno);

							lctd_turnosDerivados     = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
									                                 .findByIdTurnoPadreVinculados(ltd_turnoDerivado);

							lb_return = CollectionUtils.isValidCollection(lctd_turnosDerivados);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("adjudicacionRemate", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Aprobar traslado etapa 660.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor de al id turno historia
	 * @param as_ObservacionesTramite correspondiente al valor de as observaciones tramite
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void aprobarTrasladoEtapa660(
	    long al_idTurnoHistoria, String as_ObservacionesTramite, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if((al_idTurnoHistoria > NumericUtils.DEFAULT_LONG_VALUE) && (as_ObservacionesTramite != null))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				TurnoHistoria lth_turnoHistoria;
				long          ll_idMotivo;

				ll_idMotivo           = 0;
				lth_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                              .findById(NumericUtils.getLongWrapper(al_idTurnoHistoria));

				if(lth_turnoHistoria != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_idSubProceso;

						ls_idSubProceso = ls_solicitud.getIdSubproceso();

						if(StringUtils.isValidString(ls_idSubProceso))
						{
							if(ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1))
								ll_idMotivo = MotivoTramiteCommon.EJECUTAR_TRASLADO_INDIVIDUAL;
							else if(ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2))
								ll_idMotivo = MotivoTramiteCommon.EJECUTAR_TRASLADO_MASIVO;
						}
					}

					if(StringUtils.isValidString(as_ObservacionesTramite))
						lth_turnoHistoria.setObservaciones(as_ObservacionesTramite);

					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(EtapaCommon.ID_ETAPA_EJECUCION_TRASLADOS, ll_idMotivo), as_userId, as_remoteIp,
					    EstadoCommon.TERMINADA
					);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("aprobarTrasladoEtapa660", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método encargado de cargar la justificacion del cierre de folio.
	 *
	 * @param acep_arg Objeto que contiene la información para realizar la consulta.
	 * @return Cambio estado predio que contiene la justificacion del cierre de folio.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see CambioEstadoPredio
	 */
	public synchronized CambioEstadoPredio cargarJustificacionCierreFolio(CambioEstadoPredio acep_arg)
	    throws B2BException
	{
		CambioEstadoPredio lcep_return;

		lcep_return = null;

		if(acep_arg != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcep_return = DaoCreator.getCambioEstadoPredioDAO(ldm_manager).findByIdTurno(acep_arg);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarJustificacionCierreFolio", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcep_return;
	}

	/**
	 * Consultar traslados matriculas.
	 *
	 * @param al_idTurnoHistoria de id turno historia actual.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TrasladoMatricula> consultarTrasladosMatriculas(
	    Long al_idTurnoHistoria, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<TrasladoMatricula> lcmt_matriculas;

		ldm_manager         = DaoManagerFactory.getDAOManager();
		lcmt_matriculas     = null;

		try
		{
			if(al_idTurnoHistoria != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_idTurnoHistoria);

				if(lth_turnoHistoria != null)
				{
					DireccionPredioDAO                                 ldpd_DAO;
					DireccionPredioAccDAO                              ldpad_DAO;
					AnotacionPredioDAO                                 lapd_DAO;
					com.bachue.snr.prosnr01.dao.acc.AnotacionPredioDAO lapda_DAO;

					ldpd_DAO      = DaoCreator.getDireccionPredioDAO(ldm_manager);
					ldpad_DAO     = DaoCreator.getDireccionPredioAccDAO(ldm_manager);
					lapd_DAO      = DaoCreator.getAnotacionPredioDAO(ldm_manager);
					lapda_DAO     = DaoCreator.getAccAnotacionPredioDAO(ldm_manager);

					lcmt_matriculas = DaoCreator.getTrasladoMatriculaDAO(ldm_manager)
							                        .findByIdSolicitud(lth_turnoHistoria.getIdSolicitud(), true);

					if(CollectionUtils.isValidCollection(lcmt_matriculas))
					{
						for(TrasladoMatricula ltm_actual : lcmt_matriculas)
						{
							if(ltm_actual != null)
							{
								String ls_idCirculoOrigen;
								String ls_idCirculoDestino;
								Long   ll_idMatriculaOrigen;
								Long   ll_idMatriculaDestino;

								ls_idCirculoOrigen        = ltm_actual.getIdCirculoOrigen();
								ll_idMatriculaOrigen      = ltm_actual.getIdMatriculaOrigen();
								ls_idCirculoDestino       = ltm_actual.getIdCirculoDestino();
								ll_idMatriculaDestino     = ltm_actual.getIdMatriculaDestinoTmp();

								if(
								    StringUtils.isValidString(ls_idCirculoOrigen)
									    && NumericUtils.isValidLong(ll_idMatriculaOrigen)
								)
								{
									String                      ls_idDireccion;
									DireccionPredio             ldp_direccionOrigen;
									Collection<AnotacionPredio> lcapb_anotacionesOrigen;

									lcapb_anotacionesOrigen     = lapd_DAO.findAnotacionesPredio(
										    ls_idCirculoOrigen, ll_idMatriculaOrigen
										);
									ls_idDireccion              = ldpd_DAO.findMaxIdDireccion(
										    ls_idCirculoOrigen, NumericUtils.getLong(ll_idMatriculaOrigen)
										);
									ldp_direccionOrigen         = ldpd_DAO.findByIdCirculoMatriculaIdDireccion(
										    ls_idCirculoOrigen, NumericUtils.getLong(ll_idMatriculaOrigen),
										    ls_idDireccion
										);

									if(CollectionUtils.isValidCollection(lcapb_anotacionesOrigen))
										ltm_actual.setAnotacionesOrigen(lcapb_anotacionesOrigen.size());

									if(ldp_direccionOrigen != null)
										ltm_actual.setDireccionOrigen(ldp_direccionOrigen.getDireccion());
								}

								if(
								    StringUtils.isValidString(ls_idCirculoDestino)
									    && NumericUtils.isValidLong(ll_idMatriculaDestino)
								)
								{
									DireccionPredioAcc                                                ldpa_direccionDestino;
									Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lcap_anotacionesDestino;

									ldpa_direccionDestino       = ldpad_DAO.findLastByIdCirculoMatricula(
										    ls_idCirculoDestino, ll_idMatriculaDestino
										);
									lcap_anotacionesDestino     = lapda_DAO.findByCirculoMatricula(
										    ls_idCirculoDestino, ll_idMatriculaDestino
										);

									if(ldpa_direccionDestino != null)
										ltm_actual.setDireccionDestino(ldpa_direccionDestino.getDireccion());

									if(CollectionUtils.isValidCollection(lcap_anotacionesDestino))
										ltm_actual.setAnotacionesDestino(lcap_anotacionesDestino.size());
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

			clh_LOGGER.error("consultarTrasladosMatriculas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lcmt_matriculas))
			lcmt_matriculas = null;

		return lcmt_matriculas;
	}

	/**
	 * Termina la etapa actual y regresa el trámite a etapa de calificación o de antiguo sistema,
	 * dependiendo de donde provenga este.
	 *
	 * @param aa_param Objeto contenedor de la información del caso a devolver
	 * @param as_usuario Usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void devolverAprobacion(Aprobacion aa_param, String as_usuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(aa_param != null)
			{
				TurnoHistoriaDAO    lthd_DAO;
				DocumentosSalidaDAO lds_DAO;
				MotivoTramiteDAO    lmtd_DAO;
				ProcedimientosDAO   lpd_DAO;
				TurnoHistoria       lth_turnoHistoria;
				boolean             lb_procesoCertificados;
				boolean             lb_procesoCorrecciones;
				boolean             lb_procesoCoordinadorJuridico;
				boolean             lb_aprobacion132;
				boolean             lb_aprobacion175;
				boolean             lb_aprobacion650;
				boolean             lb_aprobacion675;
				boolean             lb_aprobacion680;
				boolean             lb_aprobacion685;
				boolean             lb_visitas800;
				boolean             lb_visitas810;
				boolean             lb_aprobacion470;
				boolean             lb_aprobacion380;
				boolean             lb_aprobacion385;
				boolean             lb_aprobacionApoyoNacional;
				boolean             lb_aprobacion111;
				boolean             lb_aprobacion111_102;

				lthd_DAO                          = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lds_DAO                           = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				lmtd_DAO                          = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				lpd_DAO                           = DaoCreator.getProcedimientosDAO(ldm_manager);
				lb_procesoCertificados            = aa_param.isProcesoCertificados();
				lb_procesoCorrecciones            = aa_param.isProcesoCorrecciones();
				lb_procesoCoordinadorJuridico     = aa_param.isProcesoCoordinadorJuridico();
				lb_aprobacionApoyoNacional        = aa_param.isVieneAprobacionApoyoNacional();
				lb_aprobacion132                  = false;
				lb_aprobacion175                  = false;
				lb_aprobacion650                  = false;
				lb_aprobacion675                  = false;
				lb_aprobacion680                  = false;
				lb_aprobacion685                  = false;
				lb_visitas800                     = false;
				lb_visitas810                     = false;
				lb_aprobacion470                  = false;
				lb_aprobacion380                  = false;
				lb_aprobacion385                  = false;
				lb_aprobacion111                  = false;
				lb_aprobacion111_102              = false;

				lth_turnoHistoria = lthd_DAO.findById(aa_param.getIdTurnoHistoria());

				if(lth_turnoHistoria == null)
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

				TurnoHistoria lth_turnoAnt;

				lth_turnoAnt = lthd_DAO.findById(NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior()));

				if(lth_turnoAnt != null)
				{
					BigDecimal lbd_idEtapa;
					String     ls_idProceso;
					String     ls_idSubproceso;

					lbd_idEtapa         = lth_turnoAnt.getIdEtapa();
					ls_idProceso        = lth_turnoAnt.getIdProceso();
					ls_idSubproceso     = lth_turnoAnt.getIdSubproceso();

					lb_aprobacion132     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES);

					lb_aprobacion111     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_MAYOR_VALOR);

					lb_aprobacion111_102     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_BUSCADOR_AS_CIRCULO_DESTINO);

					lb_aprobacion175     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS);

					lb_aprobacion650     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS);

					lb_visitas800     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_SOLICITUD_VISITAS);
					lb_visitas810     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO);

					lb_aprobacion470     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& ((lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_430)
							|| (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_465));

					lb_aprobacion380     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_ANALISIS_DEVOLUCIONES_DE_DINERO);

					lb_aprobacion385 = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_377);

					if(
					    StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso)
						    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
						    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
					)
					{
						lb_aprobacion675     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
								&& (lbd_idEtapa.longValue() == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS);

						lb_aprobacion680     = NumericUtils.isValidBigDecimal(lbd_idEtapa)
								&& (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS);

						lb_aprobacion685 = NumericUtils.isValidBigDecimal(lbd_idEtapa)
								&& (lbd_idEtapa.longValue() == EtapaCommon.APROBACION_RESOLUCION);
					}
				}

				if(!StringUtils.isValidString(as_usuario))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_USUARIO);

				if(
				    aa_param.isDevolucionAntiguoSistema() || lb_procesoCertificados || lb_procesoCorrecciones
					    || lb_procesoCoordinadorJuridico || lb_aprobacion650 || lb_aprobacion685 || lb_aprobacion675
					    || lb_aprobacion680 || lb_visitas800 || lb_visitas810 || lb_aprobacion470 || lb_aprobacion380
					    || lb_aprobacion385 || lb_aprobacionApoyoNacional
				)
				{
					String ls_comentario;

					ls_comentario = aa_param.getObservaciones();

					if(!StringUtils.isValidString(ls_comentario))
						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

					String ls_motivoNoTramite;

					ls_motivoNoTramite = lth_turnoHistoria.getMotivoNoTramite();

					if(
					    StringUtils.isValidString(ls_motivoNoTramite)
						    && (ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.APROBADO_ANTIGUO_SISTEMA)
						    || ls_motivoNoTramite.equalsIgnoreCase(
						        MotivonNoTramiteCommon.RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA
						    ) || lb_aprobacion175 || lb_procesoCoordinadorJuridico || lb_aprobacion650 || lb_visitas800
						    || lb_visitas810 || lb_aprobacion470)
					)
					{
						DocumentosSalida lds_docSalida;

						lds_docSalida = new DocumentosSalida();

						if(lb_aprobacion470)
						{
							BigDecimal lbd_idEtapa;

							lbd_idEtapa = lth_turnoAnt.getIdEtapa();

							if(lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_465)
								lds_docSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(NumericUtils.getLongWrapper(lth_turnoAnt.getIdAnterior()))
								);
							else
								lds_docSalida.setIdTurnoHistoria(
								    NumericUtils.getInteger(
								        NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
								    )
								);
						}
						else
							lds_docSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior()))
							);

						Collection<DocumentosSalida> lcds_documentos;

						lcds_documentos = lds_DAO.findByIdTurnoHistoria(lds_docSalida);

						if(CollectionUtils.isValidCollection(lcds_documentos))
						{
							ImagenesDAO li_DAO;

							li_DAO = DaoCreator.getImagenesDAO(ldm_manager);

							for(DocumentosSalida lds_doc : lcds_documentos)
							{
								if(lds_doc != null)
								{
									lds_doc.setEstado(EstadoCommon.INACTIVO);
									lds_doc.setIdUsuarioModificacion(as_usuario);
									lds_doc.setIpModificacion(as_remoteIp);
									lds_DAO.insertOrUpdate(lds_doc, false);
									li_DAO.eliminarCodigoVerificacion(lds_doc.getIdImagen());
								}
							}
						}
					}

					if(lb_aprobacionApoyoNacional)
					{
						Collection<SolicitudApoyoNacional> lcsan_solicitudesApoyoNacional;
						lcsan_solicitudesApoyoNacional = DaoCreator.getSolicitudApoyoNacionalDAO(ldm_manager)
								                                       .findByIdSolicitud(
								    lth_turnoHistoria.getIdSolicitud()
								);

						if(lcsan_solicitudesApoyoNacional != null)
						{
							for(SolicitudApoyoNacional lsan_solicitudTMP : lcsan_solicitudesApoyoNacional)
							{
								if(lsan_solicitudTMP != null)
								{
									Turno lt_tmp;
									lt_tmp = DaoCreator.getTurnoDAO(ldm_manager)
											               .findById(lsan_solicitudTMP.getIdTurnoRegistro());

									if(lt_tmp != null)
									{
										lt_tmp.setSolicitudApoyo(IdentificadoresCommon.N);
										lt_tmp.setIdUsuarioModificacion(as_usuario);
										lt_tmp.setIpModificacion(as_remoteIp);

										DaoCreator.getTurnoDAO(ldm_manager).actualizarSolicitudApoyo(lt_tmp);
									}
								}
							}
						}

						Collection<UsuarioLinea> lcul_usuariosLinea;
						lcul_usuariosLinea = DaoCreator.getUsuarioLineaDAO(ldm_manager)
								                           .findByRolLineaProdAndFecha(
								    RolCommon.CB_ROL_CALIFICADOR, LineaProduccionCommon.APOYO_NACIONAL, new Date()
								);

						if(CollectionUtils.isValidCollection(lcul_usuariosLinea))
						{
							for(UsuarioLinea lul_usuarioLinea : lcul_usuariosLinea)
							{
								if(lul_usuarioLinea != null)
								{
									lul_usuarioLinea.setActivo(IdentificadoresCommon.N);

									DaoCreator.getUsuarioLineaDAO(ldm_manager).insertOrUpdate(lul_usuarioLinea, false);
								}
							}
						}
					}

					MotivoTramite lmt_motivo;
					long          ll_idMotivo;
					long          ll_idEtapa;

					lmt_motivo = new MotivoTramite();

					if(lb_procesoCertificados)
						ll_idMotivo = MotivoTramiteCommon.DEVOLUCION_DESDE_APROBADOR_DEL_REGISTRADOR_CERTIFICADOS;
					else if(lb_procesoCorrecciones)
					{
						if(lb_aprobacion132)
							ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_EJECUTOR_DE_CORRECCIONES;
						else if(lb_aprobacion175)
							ll_idMotivo = MotivoTramiteCommon.DEVOLUCION_DOCUMENTOS_DESDE_RESPONSABLE_DE_ACTUACIONES_ADMINISTRATIVAS;
						else
							ll_idMotivo = MotivoTramiteCommon.DEVOLVER_ANALISTA_DE_CORRECCIONES;
					}
					else if(lb_procesoCoordinadorJuridico)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_DESDE_COORDINADOR_JURIDICO;
					else if(lb_aprobacionApoyoNacional)
						ll_idMotivo = MotivoTramiteCommon.NEGAR_SOLICITUD_APOYO_NACIONAL;
					else if(lb_aprobacion685)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_DESDE_REVISION_SUPERINTENDENTE;
					else if(lb_aprobacion675)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_DESDE_REVISION_JURIDICA;
					else if(lb_aprobacion680)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_DESDE_DESPACHO;
					else if(lb_aprobacion470)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_ANALISTA_SEGUNDA_INSTANCIA;
					else if(lb_aprobacion380)
						ll_idMotivo = MotivoTramiteCommon.GENERAR_RESOLUCION_DE_NEGACION;
					else if(lb_aprobacion385)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_ETAPA_ANTERIOR_385;
					else
						ll_idMotivo = MotivoTramiteCommon.APROBADOR_ANTIGUO_SISTEMA_DEVOLVER_APROBADOR_ANTIGUO_SISTEMA;

					if(lb_aprobacion111)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_BUSCAR_ANTIGUO_SISTEMA;

					if(lb_aprobacion111_102)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_BUSCAR_ANTIGUO_SISTEMA_CIRCULO_DESTINO;

					if(lb_procesoCorrecciones)
						ll_idEtapa = EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES;
					else if(lb_procesoCoordinadorJuridico)
						ll_idEtapa = EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS;
					else if(lb_aprobacionApoyoNacional)
						ll_idEtapa = EtapaCommon.ID_ETAPA_APROBADOR_APOYO_NACIONAL;
					else if(lb_aprobacion685)
						ll_idEtapa = EtapaCommon.ID_ETAPA_APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION;
					else if(lb_aprobacion675)
						ll_idEtapa = EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS;
					else if(lb_aprobacion680)
						ll_idEtapa = EtapaCommon.APROBACION_RESOLUCION;
					else if(lb_visitas800)
						ll_idEtapa = EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO;
					else if(lb_visitas810)
						ll_idEtapa = EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE;
					else if(lb_aprobacion470)
						ll_idEtapa = EtapaCommon.ID_ETAPA_470;
					else if(lb_aprobacion380)
						ll_idEtapa = EtapaCommon.ID_ETAPA_380;
					else if(lb_aprobacion385)
						ll_idEtapa = EtapaCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES;
					else if(lb_aprobacion111 || lb_aprobacion111_102)
						ll_idEtapa = EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO;
					else
						ll_idEtapa = EtapaCommon.ID_ETAPA_APROBACION;

					if(lb_aprobacion650)
						ll_idMotivo = MotivoTramiteCommon.DEVOLVER_ANALISTA_TRANSLADOS;

					else if(lb_visitas800 || lb_visitas810)
						ll_idMotivo = MotivoTramiteCommon.AUTO_DE_PRUEBAS_APROBADO;

					lmt_motivo.setIdEtapaOrigen(ll_idEtapa);
					lmt_motivo.setIdMotivo(ll_idMotivo);

					lmt_motivo = lmtd_DAO.findById(lmt_motivo);

					if((lmt_motivo != null) && (lth_turnoHistoria != null))
					{
						{
							String ls_estadoActividad;

							ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

							if(
							    StringUtils.isValidString(ls_estadoActividad)
								    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
							)
								throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
						}

						lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						lth_turnoHistoria.setObservaciones(ls_comentario);
						lth_turnoHistoria.setObservacionesNoTramite(aa_param.getMotivoNoTramite());
						lth_turnoHistoria.setIndicadorDevolucion(EstadoCommon.SI);
						lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
						lth_turnoHistoria.setIpModificacion(as_remoteIp);

						lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

						lpd_DAO.spCreateStage(lth_turnoHistoria);
					}
				}
				else
				{
					{
						CausalAprobacionDevolucion lcad_causalDevolucion;
						MotivoTramite              lmt_motivo;
						long                       ll_idMotivo;
						String                     ls_comentario;

						lcad_causalDevolucion     = aa_param.getCausalDevolucion();
						lmt_motivo                = null;
						ll_idMotivo               = MotivoTramiteCommon.DEFAULT;

						if(lcad_causalDevolucion != null)
						{
							String ls_causal;

							ls_causal = lcad_causalDevolucion.getIdCausalAprobacionDevolucion();

							if(StringUtils.isValidString(ls_causal))
							{
								long ll_causal;

								lmt_motivo     = new MotivoTramite();
								ll_causal      = NumericUtils.getLong(ls_causal);

								if(ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_A_ANALISIS_DE_DESISTIMIENTO)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_ANALISTA_DE_DESISTIMIENTO;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_ETAPA_ANTERIOR_420)
									    || (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_ETAPA_ANTERIOR_415)
								)
								{
									if(lth_turnoAnt != null)
									{
										BigDecimal lbd_idEtapa;
										long       ll_idMotivoAnt;

										lbd_idEtapa        = lth_turnoAnt.getIdEtapa();
										ll_idMotivoAnt     = NumericUtils.getLong(lth_turnoAnt.getIdMotivo());

										if(NumericUtils.isValidBigDecimal(lbd_idEtapa))
										{
											if(
											    (lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
												    && (ll_idMotivoAnt == MotivoTramiteCommon.ETAPA_415_RECHAZA_RECURSO)
											)
												ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_RECHAZO_DE_RECURSOS;
											else if(
											    lbd_idEtapa.longValue() == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS
											)
												ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_COORDINADOR_JURIDICO;
										}
									}
								}
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_NEGACION_DE_LA_SOLICITUD)
									    || (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_RESOLUCION_DE_GRABACION)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_ANALISTA_DE_GRABACION;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_NEGACION_AL_EJECUTOR)
									    || (ll_causal == CausalAprobacionDevolucionCommon.DEVOLVER_GRABACION_DEL_FOLIO)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_AL_EJECUTOR_DE_LA_GRABACION;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.INSCRIPCION_TESTAMENTO_CON_OBSERVACIONES)
									    || (ll_causal == CausalAprobacionDevolucionCommon.NO_GENERAR_INSCRIPCION)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_ANALISTA_DE_TESTAMENTOS;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.ERROR_AL_GENERAR_DOCUMENTOS)
									    || (ll_causal == CausalAprobacionDevolucionCommon.ERROR_DATOS_DEL_TURNO)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_REPRODUCCION_CONSTANCIA_TESTAMENTOS;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.ERROR_EN_LA_GENERACION_DEL_DOCUMENTO)
									    || (ll_causal == CausalAprobacionDevolucionCommon.ERROR_EN_LA_SELECCION_DE_LA_PLANTILLA)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLUCION_DESDE_APROBADOR_DEL_REGISTRADOR_CERTIFICADOS;
								else if(
								    (ll_causal == CausalAprobacionDevolucionCommon.MODIFICAR_DATOS_EN_EL_FOLIO_A_CREAR)
									    || (ll_causal == CausalAprobacionDevolucionCommon.NEGAR_SOLICITUD)
								)
									ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_ETAPA_ANTERIOR_105;
								else
									lmt_motivo = null;
							}
						}

						if(lmt_motivo == null)
						{
							lmt_motivo = new MotivoTramite();

							TurnoHistoria lth_turnoHistAnt;

							lth_turnoHistAnt = lthd_DAO.findById(
								    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
								);

							if(lth_turnoHistAnt != null)
							{
								BigDecimal lbd_etapa;

								lbd_etapa = lth_turnoHistAnt.getIdEtapa();

								if(
								    NumericUtils.isValidBigDecimal(lbd_etapa)
									    && (lbd_etapa.longValue() == EtapaCommon.ID_ETAPA_CALIFICACION)
								)
								{
									Long ll_idMotivoAnt;

									ll_idMotivoAnt = lth_turnoHistAnt.getIdMotivo();

									if(NumericUtils.isValidLong(ll_idMotivoAnt))
									{
										long ll_valorIdMotivo;

										ll_valorIdMotivo = NumericUtils.getLong(ll_idMotivoAnt);

										if(ll_valorIdMotivo == MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO)
											ll_idMotivo = MotivoTramiteCommon.NO_APRUEBA_RECHAZO_DE_DESISTIMIENTO;
										else if(
										    ll_valorIdMotivo == MotivoTramiteCommon.DESISTIMIENTO_PARA_TURNO_TRAMITE
										)
											ll_idMotivo = MotivoTramiteCommon.DEVOLUCION_DE_APROBACION_POR_DESISTIMIENTO;
									}
								}
							}

							if(ll_idMotivo == MotivoTramiteCommon.DEFAULT)
								ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_CALIFICACION;
						}

						ls_comentario = aa_param.getObservaciones();

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
						lmt_motivo.setIdMotivo(ll_idMotivo);

						lmt_motivo = lmtd_DAO.findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							long                        ll_idMotivoFinal;
							Map<TurnoHistoria, Boolean> lmthb_turnos;
							String                      ls_motivoNoTramite;
							String                      ls_descripcion;

							ll_idMotivoFinal       = lmt_motivo.getIdMotivo();
							lmthb_turnos           = new HashMap<TurnoHistoria, Boolean>();
							ls_motivoNoTramite     = aa_param.getMotivoNoTramite();
							ls_descripcion         = lmt_motivo.getDescripcion();

							if(ll_idMotivoFinal == MotivoTramiteCommon.DEVOLVER_A_CALIFICACION)
							{
								String ls_turno;

								ls_turno = lth_turnoHistoria.getIdTurno();

								if(StringUtils.isValidString(ls_turno))
								{
									Collection<TurnoDerivado> lctd_turnosDerivados;
									TurnoDerivado             ltd_turnoDerivado;

									ltd_turnoDerivado = new TurnoDerivado();

									ltd_turnoDerivado.setIdTurnoPadre(ls_turno);

									lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
											                             .findByIdTurnoPadreVinculados(
											    ltd_turnoDerivado
											);

									if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
									{
										Iterator<TurnoDerivado> litd_iterator;

										litd_iterator = lctd_turnosDerivados.iterator();

										while(litd_iterator.hasNext())
										{
											TurnoDerivado ltd_iterador;

											ltd_iterador = litd_iterator.next();

											if(ltd_iterador != null)
											{
												TurnoHistoria lth_turnoHistoriaDerivado;

												ll_idMotivoFinal              = MotivoTramiteCommon.DEVOLUCION_DESDE_APROBADOR_DEL_REGISTRADOR_TRAMITES_VINCULADOS;
												lth_turnoHistoriaDerivado     = new TurnoHistoria();

												{
													MotivoTramite lmt_motivoTramite;

													lmt_motivoTramite = new MotivoTramite();

													lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
													lmt_motivoTramite.setIdMotivo(ll_idMotivoFinal);

													lmt_motivoTramite = lmtd_DAO.findById(lmt_motivoTramite);

													if(lmt_motivoTramite != null)
														ls_descripcion = lmt_motivoTramite.getDescripcion();
												}

												lth_turnoHistoriaDerivado.setIdTurno(ltd_iterador.getIdTurnoHijo());
												lth_turnoHistoriaDerivado.setIdEtapa(
												    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_APROBACION)
												);
												lth_turnoHistoriaDerivado.setEstadoActividad(EstadoCommon.BLOQUEADO);

												lth_turnoHistoriaDerivado = lthd_DAO.findByIdTurnoEtapa(
													    lth_turnoHistoriaDerivado
													);

												if(lth_turnoHistoriaDerivado != null)
													lmthb_turnos.put(lth_turnoHistoriaDerivado, Boolean.FALSE);
											}
										}
									}
								}
							}

							lmthb_turnos.put(lth_turnoHistoria, Boolean.TRUE);

							if(CollectionUtils.isValidCollection(lmthb_turnos))
							{
								for(Map.Entry<TurnoHistoria, Boolean> lm_iterador : lmthb_turnos.entrySet())
								{
									TurnoHistoria lth_derivado;

									lth_derivado = lm_iterador.getKey();

									if(lth_derivado != null)
									{
										lth_derivado.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_derivado.setMotivo(ls_descripcion);
										lth_derivado.setIdMotivo(NumericUtils.getLongWrapper(ll_idMotivoFinal));
										lth_derivado.setObservacionesNoTramite(ls_motivoNoTramite);
										lth_derivado.setObservaciones(ls_comentario);
										lth_derivado.setIdUsuarioModificacion(as_usuario);
										lth_derivado.setIpModificacion(as_remoteIp);

										lth_turnoHistoria.setIndicadorDevolucion(EstadoCommon.SI);

										lthd_DAO.insertOrUpdate(lth_derivado, false);

										BigDecimal lbd_idTurnoHistoriaAnteriorDerivado;

										lbd_idTurnoHistoriaAnteriorDerivado = lth_derivado.getIdAnterior();

										if(NumericUtils.isValidBigDecimal(lbd_idTurnoHistoriaAnteriorDerivado))
										{
											Collection<DocumentosSalida> lcds_documentos;
											DocumentosSalida             lds_documentoSalida;
											Long                         ll_idTurnoHistoriaAnterior;

											lds_documentoSalida            = new DocumentosSalida();
											ll_idTurnoHistoriaAnterior     = NumericUtils.getLongWrapper(
												    lbd_idTurnoHistoriaAnteriorDerivado
												);

											if(NumericUtils.isValidLong(ll_idTurnoHistoriaAnterior))
											{
												lds_documentoSalida.setIdTurnoHistoria(
												    NumericUtils.getInteger(ll_idTurnoHistoriaAnterior)
												);

												lcds_documentos = lds_DAO.findByIdTurnoHistoria(lds_documentoSalida);

												if(CollectionUtils.isValidCollection(lcds_documentos))
												{
													for(DocumentosSalida lds_iterador : lcds_documentos)
													{
														if(lds_iterador != null)
														{
															lds_iterador.setEstado(EstadoCommon.INACTIVO);

															lds_DAO.insertOrUpdate(lds_iterador, false);
														}
													}
												}
											}
										}

										lpd_DAO.spCreateStage(lth_derivado);
									}
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("devolverAprobacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Ejecutar traslado procedimiento.
	 *
	 * @param al_turnoHistoria Argumento de tipo <code>Long</code> que contiene los criterios necesarios para realizar la invocación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void ejecutarTrasladoProcedimiento(Long al_turnoHistoria, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_turnoHistoria != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(al_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
					lth_turnoHistoria.setIpModificacion(as_remoteIp);

					DaoCreator.getProcedimientosDAO(ldm_manager).procEjecutarTraslado(lth_turnoHistoria);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("ejecutarTrasladoProcedimiento", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar si el proceso es realizar registro.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el id del turno historia
	 * @return Variable de tipo boolean que valida si el proceso es de realizar registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized boolean esRealizarRegistro(Long al_idTurnoHistoria)
	    throws B2BException
	{
		boolean    lb_return;
		DAOManager ldm_manager;

		lb_return       = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoria    lth_turnoHistoria;
			TurnoHistoriaDAO lth_DAO;

			lth_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			lth_turnoHistoria     = lth_DAO.findById(al_idTurnoHistoria);

			if(lth_turnoHistoria != null)
			{
				TurnoHistoria lth_turnoAnterior;

				lth_turnoAnterior = lth_DAO.findById(NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior()));

				if(lth_turnoAnterior != null)
				{
					long ll_idMotivo;

					ll_idMotivo     = NumericUtils.getLong(lth_turnoAnterior.getIdMotivo());

					lb_return = (NumericUtils.getLong(lth_turnoAnterior.getIdEtapa()) == EtapaCommon.ID_ETAPA_CALIFICACION)
							&& ((ll_idMotivo == MotivoTramiteCommon.REALIZAR_REGISTRO)
							|| (ll_idMotivo == MotivoTramiteCommon.INSCRITO_ADJUDICACION_REMATE));
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("esRealizarRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Metodo para realizar transaccciones con la base de datos para encontrar todos los registros de aprobacion
	 * de cierta etapa y usuario.
	 *
	 * @param aa_param El objeto aprobación donde se extraera el idEtapa y el usuario de la etapa
	 * @param ab_conUsuario Argumento de tipo <code>Boolean</code> que indica si la aprobación tiene un usuario asignado
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Aprobacion> findAllData(Aprobacion aa_param, boolean ab_conUsuario)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Aprobacion> lc_dataFinal;

		Collection<Aprobacion> lc_dataFinalTemp;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lc_dataFinal         = null;
		lc_dataFinalTemp     = null;

		try
		{
			if(aa_param != null)
			{
				TurnoHistoriaDAO lotad_tad;
				String           ls_nir;

				lotad_tad     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ls_nir        = aa_param.getNir();

				if(StringUtils.isValidString(ls_nir))
				{
					if(StringUtils.isValidString(ls_nir))
					{
						Solicitud los_Solicitud = new Solicitud();
						los_Solicitud.setNir(ls_nir);
						los_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(los_Solicitud);

						if(los_Solicitud != null)
							aa_param.setIdSolicitud(los_Solicitud.getIdSolicitud());
					}
				}

				{
					Collection<Aprobacion> lc_data;

					lc_data = lotad_tad.findAllData(aa_param, ab_conUsuario);

					if(CollectionUtils.isValidCollection(lc_data))
					{
						StringBuilder lsb_tmp;
						TurnoDAO      ltd_DAO;

						lsb_tmp              = new StringBuilder();
						ltd_DAO              = DaoCreator.getTurnoDAO(ldm_manager);
						lc_dataFinal         = new ArrayList<Aprobacion>();
						lc_dataFinalTemp     = new ArrayList<Aprobacion>();

						for(Aprobacion la_a : lc_data)
						{
							if(la_a != null)
							{
								Long                   ll_idTurnoHistoria;
								Collection<Aprobacion> lc_turnosDerivados;

								ll_idTurnoHistoria = la_a.getIdTurnoHistoria();

								if(NumericUtils.isValidLong(ll_idTurnoHistoria))
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = lotad_tad.findById(ll_idTurnoHistoria);

									if(lth_turnoHistoria != null)
									{
										la_a.setObservacionesAprobador(lth_turnoHistoria.getObservaciones());

										String        ls_observaciones;
										TurnoHistoria lth_tmp;
										lth_tmp = new TurnoHistoria();

										lth_tmp.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
										);

										lth_tmp = lotad_tad.findById(lth_tmp);

										if((lth_tmp != null) && (lth_tmp.getObservaciones() != null))
										{
											ls_observaciones = lth_tmp.getObservaciones();
											lth_turnoHistoria.setObservaciones(ls_observaciones);
										}

										la_a.setTurnoHistoria(lth_turnoHistoria);

										String ls_generarRelacion;
										ls_generarRelacion = StringUtils.getStringNotNull(
											    lth_turnoHistoria.getGenerarRelacion()
											);

										la_a.setGenerarRelacion(ls_generarRelacion.equalsIgnoreCase(EstadoCommon.SI));
									}
								}

								{
									String ls_idTurno;
									Turno  lt_turno;

									ls_idTurno     = la_a.getIdTurno();
									lt_turno       = ltd_DAO.findById(ls_idTurno);

									if(lt_turno != null)
										la_a.setTurno(lt_turno);

									lc_turnosDerivados = lotad_tad.findTurnosDerivadosNoVinculados(ls_idTurno);

									if(CollectionUtils.isValidCollection(lc_turnosDerivados))
									{
										Iterator<Aprobacion> li_iterator;

										li_iterator = lc_turnosDerivados.iterator();

										if(li_iterator != null)
										{
											while(li_iterator.hasNext())
											{
												Aprobacion la_tmp;

												la_tmp = li_iterator.next();

												if(la_tmp != null)
												{
													String ls_turnosAsociados;

													ls_turnosAsociados = la_tmp.getTurnosAsociados();

													if(StringUtils.isValidString(ls_turnosAsociados))
													{
														lsb_tmp.append(ls_turnosAsociados);

														if(li_iterator.hasNext())
															lsb_tmp.append(IdentificadoresCommon.SIMBOLO_COMA);
													}
												}
											}
										}
									}

									lc_turnosDerivados = lotad_tad.findTurnosDerivadosConIndicadorVinculado(ls_idTurno);

									if(CollectionUtils.isValidCollection(lc_turnosDerivados))
									{
										Iterator<Aprobacion> lia_iterator;

										lia_iterator = lc_turnosDerivados.iterator();

										if(lia_iterator != null)
										{
											while(lia_iterator.hasNext())
											{
												Aprobacion la_tmp;

												la_tmp = lia_iterator.next();

												if(la_tmp != null)
												{
													Turno lt_temp;

													lt_temp = ltd_DAO.findById(la_tmp.getTurnosAsociados());

													if(lt_temp != null)
													{
														String ls_solicitud;

														ls_solicitud = lt_temp.getIdSolicitud();

														if(StringUtils.isValidString(ls_solicitud))
														{
															Collection<Turno> lct_turnos;

															lct_turnos = ltd_DAO.findAllByIdSolicitudProceso(
																    new Turno(ls_solicitud, ProcesoCommon.ID_PROCESO_1)
																);

															if(CollectionUtils.isValidCollection(lct_turnos))
															{
																Iterator<Turno> lit_iterator;

																lit_iterator = lct_turnos.iterator();

																if(lit_iterator != null)
																{
																	while(lit_iterator.hasNext())
																	{
																		Turno lt_tmp;

																		lt_tmp = lit_iterator.next();

																		if(lt_tmp != null)
																		{
																			if(
																			    StringUtils.isValidString(
																				        lsb_tmp.toString()
																				    )
																			)
																			{
																				lsb_tmp.append(
																				    IdentificadoresCommon.SIMBOLO_COMA
																				);
																				lsb_tmp.append(lt_tmp.getIdTurno());
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

									{
										String ls_turnosCertificados;

										ls_turnosCertificados = lsb_tmp.toString();

										if(StringUtils.isValidString(ls_turnosCertificados))
											la_a.setTurnosAsociados(ls_turnosCertificados);
									}

									{
										Collection<Aprobacion> lc_idImages;
										lc_idImages = lotad_tad.findIdsDocs(ls_idTurno);

										if(CollectionUtils.isValidCollection(lc_idImages))
										{
											la_a.setDataZip(lc_idImages);
											la_a.setImageValida(true);
										}
										else
											la_a.setImageValida(false);
									}

									TurnoHistoria lth_temp;
									lth_temp = new TurnoHistoria();

									lth_temp.setIdTurnoHistoria(la_a.getIdTurnoHistoria());

									TurnoHistoriaDAO lth_DAO;
									lth_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

									lth_temp = lth_DAO.findById(lth_temp);

									if(lth_temp != null)
									{
										TurnoHistoria lth_tempAnterior;
										lth_tempAnterior = new TurnoHistoria();

										lth_tempAnterior.setIdTurnoHistoria(
										    NumericUtils.getLongWrapper(lth_temp.getIdAnterior())
										);

										lth_tempAnterior = lth_DAO.findById(lth_tempAnterior);

										if(lth_tempAnterior != null)
										{
											BigDecimal lbd_etapa;

											lbd_etapa = lth_tempAnterior.getIdEtapa();

											if(NumericUtils.isValidBigDecimal(lbd_etapa))
											{
												int li_etapa;

												li_etapa = NumericUtils.getInt(lth_tempAnterior.getIdEtapa());

												la_a.setEtapaAnterior100O110((li_etapa == 100) || (li_etapa == 110));
												la_a.setEtapaAnterior180(li_etapa == 180);
											}
										}
									}

									{
										ReferenceBusiness lrb_business;

										lrb_business = getReferenceBusiness();

										la_a.setTipificacionTurno(
										    lrb_business.findTipificacionTurno(ls_idTurno, ldm_manager)
										);
									}

									lc_dataFinal.add(la_a);
								}
							}
						}
					}
				}
			}

			if(lc_dataFinal != null)
			{
				for(Aprobacion lotc_tmp : lc_dataFinal)
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

						lc_dataFinalTemp.add(lotc_tmp);
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

		return lc_dataFinalTemp;
	}

	/**
	 * Metodo para realizar transaccciones con la base de datos para encontrar todos los registros de aprobacion
	 * de cierta etapa y usuario.
	 *
	 * @param aa_param El objeto aprobación donde se extraera el idEtapa y el usuario de la etapa
	 * @param ab_bandejaSubproceso correspondiente al valor del tipo de objeto boolean
	 * @return Retorna  una colección de datos de tipo  Aprobacion que coincide con los parametros de entrada.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<Aprobacion> findDetalleBandeja(
	    Aprobacion aa_param, boolean ab_bandejaSubproceso, boolean ab_conUsuario, String as_userId
	)
	    throws B2BException
	{
		DAOManager             ldm_manager;
		Collection<Aprobacion> lcca_dataFinal;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		lcca_dataFinal     = new LinkedList<Aprobacion>();

		try
		{
			if(aa_param != null)
			{
				TurnoHistoriaDAO lthd_tad;

				lthd_tad = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				if(aa_param.isConsultaDetalle())
				{
					String ls_nir;

					ls_nir = aa_param.getNir();

					if(StringUtils.isValidString(ls_nir))
					{
						Solicitud ls_Solicitud = new Solicitud();

						ls_Solicitud.setNir(ls_nir);

						ls_Solicitud = DaoCreator.getConsultaTrazabilidadDAO(ldm_manager).findByNIR(ls_Solicitud);

						if(ls_Solicitud != null)
							aa_param.setIdSolicitud(ls_Solicitud.getIdSolicitud());
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
					}
				}

				if(ab_bandejaSubproceso)
				{
					Long    ll_idEtapa;
					boolean lb_duplicado0463;
					boolean lb_mostrarPrincipal;

					ll_idEtapa              = aa_param.getIdEtapa();
					lb_duplicado0463        = false;
					lb_mostrarPrincipal     = false;

					if(StringUtils.isValidString(aa_param.getIdTurno()) && (aa_param.getIdTurno() != null))
					{
						TurnoHistoria    lth_turnoHistoria;
						TurnoHistoriaDAO lthd_thd;
						Acto             lca_acto;

						lthd_thd              = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
						lth_turnoHistoria     = lthd_thd.findByIdTurno(aa_param.getIdTurno());
						lca_acto              = new Acto();

						if(lth_turnoHistoria != null)
						{
							lca_acto.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
							lca_acto.setIdTipoActo("0463");
							lca_acto = DaoCreator.getActoDAO(ldm_manager).findByIdSolicitudTipoActo(lca_acto);

							if((lca_acto != null) && (lca_acto.getIdActo() != null))
								lb_duplicado0463 = true;
						}
					}

					if(NumericUtils.isValidLong(ll_idEtapa) && !lb_duplicado0463)
					{
						Collection<Proceso> lcp_procesos;
						UsuarioDAO          lud_DAO;
						UsuarioRolDAO       lurd_DAO;
						ConstantesDAO       lcd_DAO;

						lcp_procesos     = lthd_tad.findProcesosByIdEtapa(ll_idEtapa);
						lud_DAO          = DaoCreator.getUsuarioDAO(ldm_manager);
						lurd_DAO         = DaoCreator.getUsuarioRolDAO(ldm_manager);
						lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);

						if(CollectionUtils.isValidCollection(lcp_procesos))
						{
							for(Proceso lp_data : lcp_procesos)
							{
								if(lp_data != null)
								{
									Aprobacion             la_aprobacion;
									Collection<Aprobacion> lca_tmp;
									String                 ls_idProceso;
									boolean                lb_procesoValido;
									boolean                lb_procesoAprobacionEjecucionVisitas;
									boolean                lb_rolDelegado;
									boolean                lb_rolLider;

									la_aprobacion                            = new Aprobacion();
									ls_idProceso                             = lp_data.getIdProceso();
									lb_procesoValido                         = StringUtils.isValidString(ls_idProceso);
									lb_procesoAprobacionEjecucionVisitas     = lb_procesoValido
											&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_62)
											&& (ll_idEtapa.longValue() == EtapaCommon.ID_ETAPA_APROBACION_EJECUCION);
									lb_rolDelegado                           = false;
									lb_rolLider                              = false;

									if(lb_procesoAprobacionEjecucionVisitas)
									{
										Usuario lu_usuario;

										lu_usuario = lud_DAO.findById(new Usuario(as_userId));

										if(lu_usuario != null)
										{
											UsuarioRol lur_usuarioRol;

											lur_usuarioRol     = lurd_DAO.findById(
												    new UsuarioRol(as_userId, RolCommon.CB_ROL_DELEGADO_REGISTRO)
												);

											lb_rolDelegado     = lur_usuarioRol != null;

											lur_usuarioRol     = lurd_DAO.findById(
												    new UsuarioRol(
												        as_userId, RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL
												    )
												);

											lb_rolLider = lur_usuarioRol != null;
										}

										if(lb_rolDelegado || lb_rolLider)
										{
											Constantes lc_constante;

											lc_constante = lcd_DAO.findByIdWithoutImage(
												    lb_rolDelegado
												    ? ConstanteCommon.MOT_AUTO_APROBACION_EJECUCION_CB_ROL_DELEGADO_REGISTRO
												    : (lb_rolLider
												    ? ConstanteCommon.MOT_AUTO_APROBACION_EJECUCION_CB_ROL_LIDER_VIGILANCIA_CONTROL
												    : null)
												);

											if(lc_constante != null)
											{
												String ls_caracter;

												ls_caracter = lc_constante.getCaracter();

												if(StringUtils.isValidString(ls_caracter))
												{
													aa_param.setIdMotivosAprobacionEjecucion(ls_caracter);
													aa_param.setConsultaAuto(true);
												}
											}
											else
												throw new B2BException(
												    "El usuario no posee roles válidos para este proceso."
												);
										}
										else
											throw new B2BException(
											    "El usuario no posee roles válidos para este proceso."
											);
									}

									lca_tmp = lthd_tad.findDataAprobacion(aa_param, ls_idProceso, ab_conUsuario, false);

									if(lb_procesoAprobacionEjecucionVisitas && (lb_rolDelegado || lb_rolLider))
									{
										Constantes lc_constante;

										lc_constante = lcd_DAO.findByIdWithoutImage(
											    lb_rolDelegado
											    ? ConstanteCommon.MOT_RESOL_INFO_APROBACION_EJECUCION_CB_ROL_DELEGADO_REGISTRO
											    : (lb_rolLider
											    ? ConstanteCommon.MOT_RESOL_INFO_APROBACION_EJECUCION_CB_ROL_LIDER_VIGILANCIA_CONTROL
											    : null)
											);

										if(lc_constante != null)
										{
											String ls_caracter;

											ls_caracter = lc_constante.getCaracter();

											if(StringUtils.isValidString(ls_caracter))
												aa_param.setIdMotivosAprobacionEjecucion(ls_caracter);
										}
										else
											throw new B2BException(
											    "El usuario no posee roles válidos para este proceso."
											);

										aa_param.setConsultaAuto(false);

										{
											Collection<Aprobacion> lca_resultados3_4_5;

											lca_resultados3_4_5 = lthd_tad.findDataAprobacion(
												    aa_param, ls_idProceso, ab_conUsuario, false
												);

											if(CollectionUtils.isValidCollection(lca_resultados3_4_5))
											{
												if(lca_tmp != null)
													lca_tmp.addAll(lca_resultados3_4_5);
												else
													lca_tmp = lca_resultados3_4_5;
											}
										}
									}

									if(CollectionUtils.isValidCollection(lca_tmp))
									{
										String ls_nombreProceso;

										ls_nombreProceso = StringUtils.getStringNotNull(lp_data.getNombre());

										la_aprobacion.setNombreProceso(
										    StringUtils.getStringLowerCase(ls_nombreProceso)
										);
										la_aprobacion.setDatosBandeja(lca_tmp);
										la_aprobacion.setIdProceso(ls_idProceso);

										lcca_dataFinal.add(la_aprobacion);

										if(
										    lb_procesoValido
											    && !ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_62)
										)
											lb_mostrarPrincipal = true;
									}
								}
							}
						}
					}

					if(lb_duplicado0463 || lb_mostrarPrincipal)
					{
						Aprobacion             la_aprobacion;
						Collection<Aprobacion> lca_tmp;

						la_aprobacion     = new Aprobacion();
						lca_tmp           = lthd_tad.findDataAprobacion(aa_param, "6", ab_conUsuario, true);

						if(CollectionUtils.isValidCollection(lca_tmp))
						{
							la_aprobacion.setNombreProceso("prioridad Ley 1579 de 2012");
							la_aprobacion.setDatosBandeja(lca_tmp);

							lcca_dataFinal.add(la_aprobacion);
						}
					}
				}
				else
					lcca_dataFinal = lthd_tad.findDataAprobacion(aa_param, ab_conUsuario);
			}

			if(!CollectionUtils.isValidCollection(lcca_dataFinal))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetalleBandeja", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcca_dataFinal;
	}

	/**
	 *  Método encargado de consultar la cantidad de turnos para un usuario en un una etapa, estado activadad determinado y trámite determinado.
	 *
	 * @param as_estado Variable de tipo String que contiene un estado determinado.
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param al_etapa correspondiente al valor del tipo de objeto long
	 * @param as_proceso Variable de tipo String que contiene el tramite determinado.
	 * @param ab_bandejaSubprocesos correspondiente al valor del tipo de objeto boolean
	 * @return Variable de tipo Integer que contiene la cantidad de turnos del tramite para el usuario, estado y etapa determinados.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Integer
	 */
	public synchronized Integer findTurnosCantidad(
	    String as_estado, String as_userId, long al_etapa, String as_proceso, boolean ab_bandejaSubprocesos
	)
	    throws B2BException
	{
		DAOManager       ldm_manager;
		TurnoHistoriaDAO lthd_turnoHistoriaDAO;
		Integer          li_return;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		li_return                 = null;

		try
		{
			li_return = lthd_turnoHistoriaDAO.findTurnosCantidad(
				    as_estado, as_userId, al_etapa, as_proceso, ab_bandejaSubprocesos
				);
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

		return li_return;
	}

	/**
	 * Metodo para poder generar zip de las aprobaciones relaciones.
	 *
	 * @param aba_ba El objeto aprobación donde se extrae la información necesaria para el zip
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarArchivoZipAprobacionRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException
	{
		byte[] lba_zipFinal;

		lba_zipFinal = null;

		try
		{
			lba_zipFinal = generarArchivoZipRelacion(aba_ba);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoZipAprobacionRelacion", lb2be_e);

			throw lb2be_e;
		}

		return lba_zipFinal;
	}

	/**
	 * Ordena y procesa los tramites enviados a aprobar llamando al proceso de generar el
	 * documento PDF por cada circulo diferente que exista en la colección de aprobaciones.
	 *
	 * @param aca_aprobaciones Colección contenedora de todos los tramites a aprobar para
	 * generar el consecutivo relación
	 * @param ab_firmar Indicar si los tramites seleccionados se van a firmar
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @param as_remoteIp Dirección IP del cliente que ejecuta el proceso
	 * @return HashMap con los consecutivos y documentos PDF generados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws Exception Señala que se ha producido una excepción
	 * @see Map
	 */
	public synchronized Map<String, byte[]> generarDocumentoRelacionAprobacion(
	    Collection<Aprobacion> aca_aprobaciones, boolean ab_firmar, String as_userId, String as_remoteIp
	)
	    throws B2BException, Exception
	{
		Map<String, byte[]> lm_documentos;
		DAOManager          ldm_manager;

		lm_documentos     = new LinkedHashMap<String, byte[]>();
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<Aprobacion> lca_aprobacionesSeleccionadas;
			Collection<String>     lcs_circulos;

			lca_aprobacionesSeleccionadas     = new LinkedList<Aprobacion>();
			lcs_circulos                      = new LinkedList<String>();

			if(CollectionUtils.isValidCollection(aca_aprobaciones))
			{
				TurnoHistoriaDAO lth_DAO;

				lth_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(Aprobacion la_aprobacion : aca_aprobaciones)
				{
					if(la_aprobacion != null)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = lth_DAO.findById(la_aprobacion.getIdTurnoHistoria());

						if(lth_turnoHistoria != null)
						{
							if(la_aprobacion.isGenerarRelacion())
							{
								Turno lt_turno;

								lt_turno = la_aprobacion.getTurno();

								lth_turnoHistoria.setGenerarRelacion(EstadoCommon.SI);
								lth_turnoHistoria.setObservaciones(la_aprobacion.getObservacionesAprobador());

								lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

								if(lt_turno != null)
								{
									String ls_circulo;
									ls_circulo = StringUtils.getStringNotNull(lt_turno.getIdCirculo());

									if(lcs_circulos.isEmpty())
										lcs_circulos.add(ls_circulo);
									else
									{
										boolean lb_repetido;
										lb_repetido = false;

										for(String ls_circuloAgregado : lcs_circulos)
										{
											if(
											    StringUtils.isValidString(ls_circuloAgregado)
												    && ls_circulo.equalsIgnoreCase(ls_circuloAgregado)
											)
											{
												lb_repetido = true;

												break;
											}
										}

										if(!lb_repetido)
											lcs_circulos.add(ls_circulo);
									}

									lca_aprobacionesSeleccionadas.add(la_aprobacion);
								}
							}
							else
							{
								lth_turnoHistoria.setGenerarRelacion(EstadoCommon.NO);

								lth_DAO.insertOrUpdate(lth_turnoHistoria, false);
							}
						}
					}
				}
			}

			if(
			    CollectionUtils.isValidCollection(lca_aprobacionesSeleccionadas)
				    && CollectionUtils.isValidCollection(lcs_circulos)
			)
			{
				for(String ls_circuloTurno : lcs_circulos)
				{
					if(StringUtils.isValidString(ls_circuloTurno))
					{
						Collection<Aprobacion> lca_temp;
						lca_temp = new LinkedList<Aprobacion>();

						for(Aprobacion la_aprobacion : lca_aprobacionesSeleccionadas)
						{
							if(la_aprobacion != null)
							{
								Turno lt_turno;
								lt_turno = la_aprobacion.getTurno();

								if(lt_turno != null)
								{
									String ls_temp;
									ls_temp = StringUtils.getStringNotNull(lt_turno.getIdCirculo());

									if(ls_temp.equalsIgnoreCase(ls_circuloTurno))
										lca_temp.add(la_aprobacion);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lca_temp))
						{
							lm_documentos.putAll(
							    crearPdfAprobacionRelacion(
							        lca_temp, ls_circuloTurno, ab_firmar, as_userId, as_remoteIp, ldm_manager
							    )
							);

							if(ab_firmar)
							{
								String ls_numeroRelacion;
								byte[] lba_documentoRelacion;

								ls_numeroRelacion         = null;
								lba_documentoRelacion     = null;

								for(Map.Entry<String, byte[]> lme_iterator : lm_documentos.entrySet())
								{
									if(lme_iterator != null)
									{
										ls_numeroRelacion         = lme_iterator.getKey();
										lba_documentoRelacion     = lme_iterator.getValue();

										if(StringUtils.isValidString(ls_numeroRelacion))
										{
											lm_documentos.remove(ls_numeroRelacion);

											break;
										}
									}
								}

								salvarAprobacion(
								    lca_temp, ls_numeroRelacion, lba_documentoRelacion, as_userId, as_remoteIp,
								    ldm_manager, ab_firmar
								);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_APROBACIONES_SELECCIONADAS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoRelacionAprobacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lm_documentos;
	}

	/**
	 * Metodo para poder generar zip de aprobación.
	 *
	 * @param at_param El objeto aprobación donde se extrae la información necesaria para el zip
	 * @return devuelve el valor de Aprobacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Aprobacion
	 */
	public synchronized Aprobacion generateZip(Aprobacion at_param)
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
		ls_nombreFile     = ConstanteCommon.NOMBRE_ARCHIVO_APROBACION;

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
									DocumentosSalida lds_documento;

									lds_documento = new DocumentosSalida();

									lds_documento.setIdImagen(ll_idImagen);

									lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                      .findByIdImagen(lds_documento);

									if(lds_documento != null)
									{
										String ls_tipo;
										ls_tipo = lds_documento.getTipo();

										if(StringUtils.isValidString(ls_tipo))
											ls_nombreFile = ls_tipo;
									}

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
	 * Inactivar matricula traslado por solicitud.
	 *
	 * @param atm_matricula correspondiente al valor de TrasladoMatricula
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void inactivarMatriculaTrasladoById(
	    TrasladoMatricula atm_matricula, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(atm_matricula != null)
			{
				TrasladoMatricula    lmt_matriculas;
				TrasladoMatriculaDAO ltmd_DAO;

				ltmd_DAO           = DaoCreator.getTrasladoMatriculaDAO(ldm_manager);
				lmt_matriculas     = ltmd_DAO.buscarPorIdSolicitudCirculoMatriculaOrigen(
					    atm_matricula.getIdSolicitud(), atm_matricula.getIdCirculoOrigen(),
					    atm_matricula.getIdMatriculaOrigen()
					);

				if(lmt_matriculas != null)
				{
					lmt_matriculas.setIdUsuarioModificacion(as_userId);
					lmt_matriculas.setIpModificacion(as_remoteIp);

					ltmd_DAO.actualizarTrasladoMatriculaEstadoN(lmt_matriculas);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("inactivarMatriculaTrasladoBySolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Inserta el tipo decision recurso escogido en pantalla
	 *
	 * @param aca_param Coleccion contenedor de la información del caso a insertar
	 * * @param as_idTipoDecisionRecurso String tipo decision recurso a insertar
	 * @param as_usuario Usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void insertarTipoDecisionRecurso(
	    Collection<Aprobacion> aca_param, String as_idTipoDecisionRecurso, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(aca_param))
			{
				Iterator<Aprobacion> lia_iterator;
				TurnoHistoriaDAO     lotad_DAO;
				SolicitudDAO         lsd_DAO;
				SolicitudAsociadaDAO lsad_DAO;
				RecursoDAO           lrd_DAO;
				RecursoDecisionDAO   lrdd_DAO;

				lia_iterator     = aca_param.iterator();
				lotad_DAO        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
				lsad_DAO         = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);
				lrd_DAO          = DaoCreator.getRecursoDAO(ldm_manager);
				lrdd_DAO         = DaoCreator.getRecursoDecisionDAO(ldm_manager);

				while(lia_iterator.hasNext())
				{
					Aprobacion la_aprobacion;

					la_aprobacion = lia_iterator.next();

					if((la_aprobacion != null) && la_aprobacion.isGenerarRelacion())
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = new TurnoHistoria();

						lth_turnoHistoria.setIdTurnoHistoria(la_aprobacion.getIdTurnoHistoria());

						lth_turnoHistoria = lotad_DAO.findById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							BigDecimal lbd_idAnterior;

							lbd_idAnterior = lth_turnoHistoria.getIdAnterior();

							if(NumericUtils.isValidBigDecimal(lbd_idAnterior))
							{
								TurnoHistoria lth_turnoAnterior;

								lth_turnoAnterior = new TurnoHistoria();

								lth_turnoAnterior.setIdTurnoHistoria(
								    NumericUtils.getLongWrapper(lbd_idAnterior.longValue())
								);

								lth_turnoAnterior = lotad_DAO.findById(lth_turnoAnterior);

								if(lth_turnoAnterior != null)
								{
									long    ll_idEtapaAnt;
									long    ll_idEtapaActual;
									boolean lb_etapa470;
									String  ls_idTipoRecurso;
									String  ls_idRecurso;

									ll_idEtapaAnt        = NumericUtils.getLong(lth_turnoAnterior.getIdEtapa());
									ll_idEtapaActual     = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
									lb_etapa470          = ll_idEtapaActual == EtapaCommon.ID_ETAPA_470;
									ls_idTipoRecurso     = null;
									ls_idRecurso         = null;

									{
										Solicitud ls_solicitud;

										ls_solicitud = lsd_DAO.findById(lth_turnoAnterior.getIdSolicitud());

										if(ls_solicitud != null)
										{
											if(lb_etapa470)
											{
												String ls_maxIdSolicitud;

												ls_maxIdSolicitud = lsad_DAO.findMaxByIdSolicitud(
													    ls_solicitud.getIdSolicitud()
													);

												if(ls_maxIdSolicitud != null)
												{
													Solicitud ls_solicitudAsociada;

													ls_solicitudAsociada = lsd_DAO.findById(ls_maxIdSolicitud);

													if(ls_solicitudAsociada != null)
													{
														String ls_idProceso;
														String ls_idSubProceso;

														ls_idProceso        = ls_solicitudAsociada.getIdProceso();
														ls_idSubProceso     = ls_solicitudAsociada.getIdSubproceso();

														if(
														    StringUtils.isValidString(ls_idProceso)
															    && StringUtils.isValidString(ls_idSubProceso)
														)
														{
															if(
															    ls_idProceso.equalsIgnoreCase(
																        ProcesoCommon.ID_PROCESO_47
																    )
																    && ls_idSubProceso.equalsIgnoreCase(
																        SubProcesoCommon.RECURSO_DE_APELACION
																    )
															)
																ls_idTipoRecurso = TipoRecursoCommon.APELACION;
															else if(
															    ls_idProceso.equalsIgnoreCase(
																        ProcesoCommon.ID_PROCESO_48
																    )
															)
																ls_idTipoRecurso = ls_idSubProceso.equalsIgnoreCase(
																	    SubProcesoCommon.PROCESO_48_RECURSO_DE_QUEJA
																	) ? TipoRecursoCommon.QUEJA
																	  : (ls_idSubProceso.equalsIgnoreCase(
																	    SubProcesoCommon.PROCESO_48_REVOCATORIA_DIRECTA
																	) ? TipoRecursoCommon.REVOCATORIA_DIRECTA : null);
														}
													}
												}
											}
											else
											{
												SolicitudAsociada lsa_solicitudAsociada;

												lsa_solicitudAsociada = lsad_DAO.findByIdSolicitudProceso(
													    ls_solicitud.getIdSolicitud(), ProcesoCommon.ID_PROCESO_47
													);

												if(lsa_solicitudAsociada != null)
												{
													Solicitud ls_solicitudAsociada;

													ls_solicitudAsociada = lsd_DAO.findById(
														    lsa_solicitudAsociada.getIdSolicitud()
														);

													if(ls_solicitudAsociada != null)
													{
														String ls_idSubProceso;

														ls_idSubProceso = ls_solicitudAsociada.getIdSubproceso();

														if(StringUtils.isValidString(ls_idSubProceso))
															ls_idTipoRecurso = ls_idSubProceso;
													}
												}
											}
										}
									}

									if(!StringUtils.isValidString(ls_idTipoRecurso))
										throw new B2BException(ErrorKeys.TIPO_RECURSO_NO_ENCONTRADO);

									{
										Recurso lr_recurso;

										lr_recurso = new Recurso();

										lr_recurso.setIdSolicitudRecurso(lth_turnoHistoria.getIdSolicitud());

										lr_recurso = lrd_DAO.findByIdSolicitudRecurso(lr_recurso);

										if(lr_recurso != null)
											ls_idRecurso = lr_recurso.getIdRecurso();
									}

									if(
									    (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_460)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_465)
									)
									{
										RecursoDecision lrd_recursoDecision;

										lrd_recursoDecision = new RecursoDecision();

										lrd_recursoDecision.setIdTipoDecisionRecurso(as_idTipoDecisionRecurso);
										lrd_recursoDecision.setIdTurno(lth_turnoHistoria.getIdTurno());
										lrd_recursoDecision.setIdTipoRecurso(ls_idTipoRecurso);
										lrd_recursoDecision.setIdRecurso(ls_idRecurso);
										lrd_recursoDecision.setActivo(EstadoCommon.S);

										{
											RecursoDecision lrd_tmp;
											boolean         lb_insert;

											lrd_tmp       = lrdd_DAO.findByIdTurno(lrd_recursoDecision);
											lb_insert     = lrd_tmp == null;

											if(lb_insert)
											{
												lrd_recursoDecision.setIdUsuarioCreacion(as_usuario);
												lrd_recursoDecision.setIpCreacion(as_remoteIp);
											}
											else
											{
												lrd_recursoDecision.setIdUsuarioModificacion(as_usuario);
												lrd_recursoDecision.setIpModificacion(as_remoteIp);
											}

											lrdd_DAO.insertOrUpdate(lrd_recursoDecision, lb_insert);
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

			clh_LOGGER.error("insertarTipoDecisionRecurso", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de generar el mensaje informativo para el proceso de Adjudicación de remate.
	 *
	 * @param al_idTurnoHistoria Variable de tipo Long que contiene el turno historia del proceso
	 * @return Variable de tipo String que contiene el mensaje generado por el sistema.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see String[]
	 */
	public synchronized String[] mensajeAdjudicacionRemate(Long al_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String[]   lsa_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lsa_return      = null;

		try
		{
			if(NumericUtils.isValidLong(al_idTurnoHistoria))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(al_idTurnoHistoria);

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Collection<SolicitudMatricula> lcsm_matriculas;
						SolicitudMatricula             lsm_solicitudMatricula;

						lsm_solicitudMatricula = new SolicitudMatricula();

						lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

						lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
								                        .findByIdSolicitud(lsm_solicitudMatricula);

						if(CollectionUtils.isValidCollection(lcsm_matriculas))
						{
							Iterator<SolicitudMatricula> lism_iterator;
							StringBuilder                lsb_sb;

							lism_iterator     = lcsm_matriculas.iterator();
							lsb_sb            = new StringBuilder();
							lsa_return        = new String[2];

							{
								List<Map<String, Object>> llmso_mso;
								Map<Integer, Object>      lhmp_criterios;

								lhmp_criterios = new LinkedHashMap<Integer, Object>();
								lhmp_criterios.put(NumericUtils.getInteger(1), lth_turnoHistoria.getIdTurno());

								llmso_mso = DaoCreator.getUtilDAO(ldm_manager)
										                  .getCustonQuery(
										    ConsultasUtilidades.CS_CONSULTA_DATA_TURNO_VINCULADO, lhmp_criterios
										);

								if(CollectionUtils.isValidCollection(llmso_mso))
								{
									Iterator<Map<String, Object>> limso_iterador;

									limso_iterador = llmso_mso.iterator();

									if(limso_iterador.hasNext())
									{
										Map<String, Object> lmso_iterator;

										lmso_iterator = limso_iterador.next();

										if(lmso_iterator != null)
										{
											Object lo_mensaje;

											lo_mensaje = lmso_iterator.get(IdentificadoresCommon.DESCRIPCION);

											if(lo_mensaje instanceof String)
												lsa_return[0] = ((String)lo_mensaje);
										}
									}
								}
							}

							while(lism_iterator.hasNext())
							{
								SolicitudMatricula lsm_iterador;

								lsm_iterador = lism_iterator.next();

								if(lsm_iterador != null)
									lsb_sb.append(
									    lsm_iterador.getIdCirculo() + "-" + lsm_iterador.getIdMatricula() + " "
									);
							}

							lsa_return[1] = lsb_sb.toString();
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("mensajeAdjudicacionRemate", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lsa_return;
	}

	/**
	 * Consulta en la base de datos los documentos asociados a un turno.
	 *
	 * @param as_idTurno id turno a utilizar como filtro en la busqueda
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public synchronized Collection<DocumentosSalida> obtenerDocumentosPorTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentos;

		lcds_documentos = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DocumentosSalida lds_doc;

				lds_doc = new DocumentosSalida();

				lds_doc.setIdTurno(as_idTurno);

				lcds_documentos = DaoCreator.getDocumentosSalidaDAO(ldm_manager).findByIdTurnoEstadoA(lds_doc, true);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("obtenerDocumentosPorTurno", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcds_documentos;
	}

	/**
	 * Método para poder salvar las aprobaciones hechas por el registrador.
	 *
	 * @param aca_param Objeto donde se tomara la información para poder salvar la información
	 * @param as_usuario Nombre de usuario del registrador
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Aprobacion
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

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			la_aprobacion = salvarAprobacion(aca_param, null, null, as_usuario, as_remoteIp, ldm_manager);
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
	 * Verifica si la etapa anterior de un trámite proviene de antiguo sistema.
	 *
	 * @param at_param Objeto contenedor de la información del trámite
	 * @return true si el tramite proviene de antiguo sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see BigDecimal
	 */
	public synchronized BigDecimal validacionEtapaAnteriorAprobacionAS(Aprobacion at_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		BigDecimal lbd_idEtapa;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lbd_idEtapa     = null;

		try
		{
			TurnoHistoriaDAO lotad_tad;

			lotad_tad = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			if(at_param != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurnoHistoria(at_param.getIdTurnoHistoria());

				lth_turnoHistoria = lotad_tad.findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					BigDecimal lbd_idAnterior;

					lbd_idAnterior = lth_turnoHistoria.getIdAnterior();

					if(NumericUtils.isValidBigDecimal(lbd_idAnterior))
					{
						TurnoHistoria lth_turnoAnterior;
						Long          ll_idTurnoHistoriaAnterior;

						lth_turnoAnterior     = new TurnoHistoria();

						ll_idTurnoHistoriaAnterior = NumericUtils.getLongWrapper(lbd_idAnterior.longValue());

						lth_turnoAnterior.setIdTurnoHistoria(ll_idTurnoHistoriaAnterior);

						lth_turnoAnterior = lotad_tad.findById(lth_turnoAnterior);

						if(lth_turnoAnterior != null)
							lbd_idEtapa = lth_turnoAnterior.getIdEtapa();
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validacionEtapaAnteriorAprobacionAS", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lbd_idEtapa;
	}

	/**
	 * Verifica si la etapa anterior de un trámite proviene de un tramite de recursos.
	 *
	 * @param aca_param Colección contenedor de la información del trámite
	 * @return true si el tramite proviene de antiguo sistema
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see BigDecimal
	 */
	public synchronized Aprobacion validacionEtapaAnteriorAprobacionRecursos(Collection<Aprobacion> aca_param)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_esRecursos;
		boolean    lb_NoEsObligatorio;
		boolean    lb_preseleccionRechazo;
		Aprobacion la_aprobacionTMP;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lb_esRecursos              = false;
		lb_NoEsObligatorio         = false;
		lb_preseleccionRechazo     = false;
		la_aprobacionTMP           = new Aprobacion();

		try
		{
			if(CollectionUtils.isValidCollection(aca_param))
			{
				Iterator<Aprobacion>   lia_iterator;
				TurnoHistoriaDAO       lotad_tad;
				TipoDecisionRecursoDAO ltdrd_DAO;

				lia_iterator     = aca_param.iterator();
				lotad_tad        = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ltdrd_DAO        = DaoCreator.getTipoDecisionRecursoDAO(ldm_manager);

				while(lia_iterator.hasNext())
				{
					Aprobacion la_aprobacion;

					la_aprobacion = lia_iterator.next();

					if((la_aprobacion != null) && la_aprobacion.isGenerarRelacion())
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = new TurnoHistoria();

						lth_turnoHistoria.setIdTurnoHistoria(la_aprobacion.getIdTurnoHistoria());

						lth_turnoHistoria = lotad_tad.findById(lth_turnoHistoria);

						if(lth_turnoHistoria != null)
						{
							BigDecimal lbd_idAnterior;

							lbd_idAnterior = lth_turnoHistoria.getIdAnterior();

							if(NumericUtils.isValidBigDecimal(lbd_idAnterior))
							{
								TurnoHistoria lth_turnoAnterior;
								Long          ll_idTurnoHistoriaAnterior;

								lth_turnoAnterior     = new TurnoHistoria();

								ll_idTurnoHistoriaAnterior = NumericUtils.getLongWrapper(lbd_idAnterior.longValue());

								lth_turnoAnterior.setIdTurnoHistoria(ll_idTurnoHistoriaAnterior);

								lth_turnoAnterior = lotad_tad.findById(lth_turnoAnterior);

								if(lth_turnoAnterior != null)
								{
									long    ll_idEtapaAnt;
									long    ll_idMotivoAnt;
									long    ll_idEtapaActual;
									boolean lb_etapa470;

									ll_idEtapaAnt        = NumericUtils.getLong(lth_turnoAnterior.getIdEtapa());
									ll_idMotivoAnt       = NumericUtils.getLong(lth_turnoAnterior.getIdMotivo());
									ll_idEtapaActual     = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
									lb_etapa470          = ll_idEtapaActual == EtapaCommon.ID_ETAPA_470;

									if(
									    (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_460)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_465)
									)
									{
										if((ll_idEtapaAnt == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA) && !lb_etapa470)
											lb_esRecursos = false;
										else if(
										    !((ll_idEtapaAnt == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS)
											    && (ll_idMotivoAnt == MotivoTramiteCommon.REVISION_AUTO_DE_PRUEBAS_APROBADA_POR_COORDINADOR_JURIDICO))
										)
											lb_esRecursos = true;
									}

									if(
									    ((ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA))
										    && (ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_APERTURA_DE_PRUEBAS)
									)
										lb_NoEsObligatorio = true;
									else if(
									    ((ll_idEtapaAnt == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
										    || (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_460))
										    && (ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_DE_RECURSOS)
									)
										lb_preseleccionRechazo = true;

									{
										TurnoHistoria lth_maxEtapa;

										lth_maxEtapa = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
												                     .findMaxByIdEtapa(
												    lb_etapa470 ? EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA
												                    : EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS,
												    lth_turnoHistoria.getIdSolicitud()
												);

										if(lth_maxEtapa != null)
										{
											long ll_idMotivoEtapaMax;

											ll_idMotivoEtapaMax = NumericUtils.getLong(lth_maxEtapa.getIdMotivo());

											if(ll_idMotivoEtapaMax > 0)
											{
												String ls_idTipoDecisionRecurso;

												if(lb_etapa470)
													ls_idTipoDecisionRecurso = (ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS)
														? TipoDecisionRecursoCommon.SOLICITUD_DE_PRUEBAS
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
														? TipoDecisionRecursoCommon.CONCEDE_RECURSO_APELACION
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_APELACION)
														? TipoDecisionRecursoCommon.NIEGA_RECURSO_DE_APELACION
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_QUEJA)
														? TipoDecisionRecursoCommon.CONCEDE_RECURSO_DE_QUEJA
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_QUEJA)
														? TipoDecisionRecursoCommon.NIEGA_RECURSO_DE_QUEJA
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_REVOCATORIA_DIRECTA)
														? TipoDecisionRecursoCommon.CONCEDE_RECURSO_DE_REVOCATORIA_DIRECTA
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_430_RESOLUCION_NEGANDO_RECURSO_DE_REVOCATORIA_DIRECTA)
														? TipoDecisionRecursoCommon.NIEGA_RECURSO_DE_REVOCATORIA_DIRECTA
														: null))))));
												else
													ls_idTipoDecisionRecurso = (ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_AUTO_DE_APERTURA_DE_PRUEBAS)
														? TipoDecisionRecursoCommon.SOLICITUD_DE_PRUEBAS
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_CONCEDIENDO_RECURSO_DE_REPOSICION)
														? TipoDecisionRecursoCommon.REVOCA_TOTALMENTE_DECISION
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_NEGANDO_RECURSO_DE_REPOSICION)
														? TipoDecisionRecursoCommon.NIEGA_RECURSO_DE_REPOSICION
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_NEGANDO_RECURSO_REPOSICION_CONCEDIENDO_APELACION)
														? TipoDecisionRecursoCommon.CONFIRMA_DECISION_Y_CONCEDE_APELACION
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_CONCEDIENDO_PARCIALMENTE_RECURSO_REPOSICION)
														? TipoDecisionRecursoCommon.CONCEDE_DECISION_PARCIALMENTE
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_RECHAZANDO_RECURSO)
														? TipoDecisionRecursoCommon.RECHAZO_DE_LOS_RECURSOS
														: ((ll_idMotivoEtapaMax == MotivoTramiteCommon.ETAPA_410_RESOLUCION_APROBANDO_RECURSO_APELACION)
														? TipoDecisionRecursoCommon.CONCEDE_RECURSO_APELACION : null))))));

												la_aprobacionTMP.setIdTipoDecisionRecurso(ls_idTipoDecisionRecurso);

												{
													TipoDecisionRecurso ltdr_tipoDecisionRecurso;

													ltdr_tipoDecisionRecurso = new TipoDecisionRecurso();

													ltdr_tipoDecisionRecurso.setIdTipoDecisionRecurso(
													    ls_idTipoDecisionRecurso
													);

													ltdr_tipoDecisionRecurso = ltdrd_DAO.findById(
														    ltdr_tipoDecisionRecurso
														);

													if(ltdr_tipoDecisionRecurso != null)
														la_aprobacionTMP.setNombreTipoDecisionRecurso(
														    ltdr_tipoDecisionRecurso.getDescripcion()
														);
												}
											}
										}
									}

									la_aprobacionTMP.setIdEtapa(NumericUtils.getLongWrapper(ll_idEtapaActual));
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

			clh_LOGGER.error("validacionEtapaAnteriorAprobacionRecursos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		la_aprobacionTMP.setEsRecursos(lb_esRecursos);
		la_aprobacionTMP.setNoEsObligatorioRecursos(lb_NoEsObligatorio);
		la_aprobacionTMP.setPreseleccionRechazoRecursos(lb_preseleccionRechazo);

		return la_aprobacionTMP;
	}

	/**
	 * Genera el documento PDF de la aprobación de relaciones en la etapa 190.
	 *
	 * @param aca_aprobaciones Colección de aprobaciones con la informacion de los procesos
	 * seleccionados para enviar a etapa de entrega o notificación
	 * @param as_idCirculo Círculo registral al cual pertenecen los procesos a aprobar
	 * @param ab_firmar Indicar si los tramites seleccionados se van a firmar
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @param adm_manager Conexión a la base de datos con la cual se está generando la
	 * transacción
	 * @return Objeto HashMap contenedor de el numero consecutivo generado para el tramite y
	 * el documento PDF generado
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws Exception Señala que se ha producido una excepción
	 * @see LinkedHashMap
	 */
	private LinkedHashMap<String, byte[]> crearPdfAprobacionRelacion(
	    Collection<Aprobacion> aca_aprobaciones, String as_idCirculo, boolean ab_firmar, String as_userId,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException, Exception
	{
		LinkedHashMap<String, byte[]> llhm_return;

		llhm_return = new LinkedHashMap<String, byte[]>();

		try
		{
			String              ls_idConstante;
			Constantes          lc_plantilla;
			ConstantesDAO       lc_DAO;
			Map<String, String> lmss_datos;
			String              ls_plantilla;
			String              ls_numeroConsecutivo;
			byte[]              lab_imagenPlantilla;
			byte[]              lba_return;

			ls_idConstante           = ConstanteCommon.PLANTILLA_GENERAR_RELACION;
			lc_plantilla             = new Constantes();
			lc_DAO                   = DaoCreator.getConstantesDAO(adm_manager);
			lmss_datos               = null;
			ls_numeroConsecutivo     = null;
			lab_imagenPlantilla      = null;
			lba_return               = null;

			lc_plantilla.setIdConstante(ls_idConstante);

			lc_plantilla = lc_DAO.findByIdWithImage(lc_plantilla);

			if(lc_plantilla == null)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ls_idConstante;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}

			lab_imagenPlantilla = lc_plantilla.getImagenes();

			if(lab_imagenPlantilla == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ls_plantilla     = new String(lab_imagenPlantilla);

			ls_plantilla = escribirTagFechaLarga(ls_plantilla);

			{
				CirculoRegistral lcr_circulo;
				lcr_circulo = new CirculoRegistral();

				lcr_circulo.setIdCirculo(as_idCirculo);

				lcr_circulo = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(lcr_circulo);

				if(lcr_circulo != null)
				{
					String ls_tagNombreOficina;
					ls_tagNombreOficina = "<TAG_ID_OFI_REGISTRO>";

					if(ls_plantilla.contains(ls_tagNombreOficina))
					{
						String ls_nombreOficina;
						ls_nombreOficina     = StringUtils.getStringNotNull(lcr_circulo.getNombre());

						ls_plantilla = ls_plantilla.replace(ls_tagNombreOficina, ls_nombreOficina);
					}

					String ls_tag;
					ls_tag = "<TAG_MUN_OFI_ORIGEN>";

					if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_idCirculo))
					{
						Municipio lm_municipio;

						lm_municipio     = new Municipio();

						lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(as_idCirculo);

						if(lm_municipio != null)
						{
							String ls_munOficinaOrigen;
							ls_munOficinaOrigen = lm_municipio.getNombre();

							if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_munOficinaOrigen))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
						}
					}
				}
			}

			{
				Constantes    lc_consecutivo;
				StringBuilder lsb_nombreConstante;
				String        ls_nombreConstante;
				int           li_anio;

				lc_consecutivo          = new Constantes();
				lsb_nombreConstante     = new StringBuilder();
				li_anio                 = Calendar.getInstance().get(Calendar.YEAR);

				lsb_nombreConstante.append(ConstanteCommon.NUMERO_APROBACION_RELACION_);
				lsb_nombreConstante.append(as_idCirculo);
				lsb_nombreConstante.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
				lsb_nombreConstante.append(StringUtils.getString(li_anio));

				ls_nombreConstante = StringUtils.getStringNotNull(lsb_nombreConstante.toString());

				lc_consecutivo.setIdConstante(ls_nombreConstante);

				lc_consecutivo = lc_DAO.findById(lc_consecutivo);

				if(lc_consecutivo == null)
				{
					BigInteger lbi_numero;

					lbi_numero         = BigInteger.valueOf(0);
					lc_consecutivo     = new Constantes();

					lc_consecutivo.setIdConstante(ls_nombreConstante);
					lc_consecutivo.setCaracter(IdentificadoresCommon.PUNTO);
					lc_consecutivo.setEntero(lbi_numero);
					lc_consecutivo.setDescripcion(
					    ConstanteCommon.DESCRIPCION_CONSTANTE_APROBACION_RELACION + as_idCirculo + " - " + li_anio
					);
					lc_consecutivo.setIdUsuarioCreacion(as_userId);
					lc_consecutivo.setIpCreacion(as_remoteIp);

					lc_DAO.insertOrUpdate(lc_consecutivo, true);
				}

				if(lc_consecutivo != null)
				{
					int           li_nuevoConsecutivo;
					StringBuilder lsb_constructor;
					String        ls_temp;
					BigInteger    lbi_entero;

					li_nuevoConsecutivo     = 0;
					lsb_constructor         = new StringBuilder();
					lbi_entero              = lc_consecutivo.getEntero();

					if(NumericUtils.isValidBigInteger(lbi_entero))
						li_nuevoConsecutivo = lbi_entero.intValue() + 1;

					lsb_constructor.append(li_anio);
					lsb_constructor.append("-");
					lsb_constructor.append(as_idCirculo);
					lsb_constructor.append("-");

					ls_temp = StringUtils.getStringNotNull(StringUtils.getString(li_nuevoConsecutivo));

					for(int li_i = 0; li_i < (10 - ls_temp.length()); li_i++)
						lsb_constructor.append("0");

					lsb_constructor.append(ls_temp);

					ls_numeroConsecutivo = lsb_constructor.toString();

					if(ab_firmar)
					{
						lc_consecutivo.setEntero(NumericUtils.getBigInteger(ls_temp));
						lc_consecutivo.setIdUsuarioModificacion(as_userId);
						lc_consecutivo.setIpModificacion(as_remoteIp);

						lc_DAO.insertOrUpdate(lc_consecutivo, false);
					}
				}
			}

			{
				String ls_tagConsecutivo;
				ls_tagConsecutivo = "<TAG_CONSECUTIVO_RELACION_ORIP>";

				if(StringUtils.isValidString(ls_numeroConsecutivo) && ls_plantilla.contains(ls_tagConsecutivo))
					ls_plantilla = ls_plantilla.replace(ls_tagConsecutivo, ls_numeroConsecutivo);
			}

			lmss_datos       = finalizarPlantilla(ls_plantilla, null, adm_manager);
			ls_plantilla     = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

			{
				ByteArrayInputStream      lbais_docInStream;
				com.aspose.words.Document ld_doc;
				DocumentBuilder           ldb_builder;
				Table                     lt_table;
				ByteArrayOutputStream     lbaos_docOutStream;

				lbais_docInStream      = new ByteArrayInputStream(ls_plantilla.getBytes());
				ld_doc                 = new com.aspose.words.Document(lbais_docInStream);
				ldb_builder            = new DocumentBuilder(ld_doc);
				lbaos_docOutStream     = new ByteArrayOutputStream();

				ldb_builder.moveToDocumentEnd();

				ldb_builder.writeln(ControlChar.LINE_BREAK);

				if(CollectionUtils.isValidCollection(aca_aprobaciones))
				{
					lt_table = ldb_builder.startTable();

					ldb_builder.insertCell();
					ldb_builder.getRowFormat().setHeadingFormat(true);
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
					ldb_builder.getFont().setColor(Color.black);
					ldb_builder.getFont().setSize(15);
					ldb_builder.getFont().setName("Arial");
					ldb_builder.getFont().setBold(true);
					ldb_builder.write("DETALLE DE LA RELACIÓN");

					ldb_builder.insertCell();
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

					ldb_builder.insertCell();
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

					ldb_builder.insertCell();
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

					ldb_builder.insertCell();
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

					ldb_builder.endRow();

					ldb_builder.insertCell();
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
					ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
					ldb_builder.getFont().setSize(10);
					ldb_builder.write("TURNO");

					ldb_builder.insertCell();
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
					ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
					ldb_builder.getFont().setSize(10);
					ldb_builder.write("FECHA RADICACIÓN");

					ldb_builder.insertCell();
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
					ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
					ldb_builder.getFont().setSize(10);
					ldb_builder.write("TURNOS CERTIFICADOS");

					ldb_builder.insertCell();
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
					ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
					ldb_builder.getFont().setSize(10);
					ldb_builder.write("TIPO CALIFICACIÓN");

					ldb_builder.insertCell();
					ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
					ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
					ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
					ldb_builder.getFont().setSize(10);
					ldb_builder.write("NIR");
					ldb_builder.endRow();

					TurnoDerivadoDAO ltdd_turnoDerivadoDAO;
					SolicitudDAO     lsd_solicitudDAO;

					ltdd_turnoDerivadoDAO     = DaoCreator.getTurnoDerivadoDAO(adm_manager);
					lsd_solicitudDAO          = DaoCreator.getSolicitudDAO(adm_manager);

					for(Aprobacion la_aprobacion : aca_aprobaciones)
					{
						if(la_aprobacion != null)
						{
							String                    ls_fechaRadicacion;
							String                    ls_idTurno;
							Turno                     lt_turno;
							Collection<TurnoDerivado> lctd_turnosDerivados;
							Collection<String>        lcs_nirVinculados;
							boolean                   lb_turnoDerivado;

							ls_fechaRadicacion       = "";
							ls_idTurno               = la_aprobacion.getIdTurno();
							lt_turno                 = la_aprobacion.getTurno();
							lctd_turnosDerivados     = ltdd_turnoDerivadoDAO.findByIdTurnoPadreVinculados(ls_idTurno);
							lcs_nirVinculados        = new LinkedList<String>();
							lb_turnoDerivado         = CollectionUtils.isValidCollection(lctd_turnosDerivados);

							if(lt_turno != null)
							{
								Date ld_fecha;
								ld_fecha     = lt_turno.getFechaCreacion();

								ls_fechaRadicacion = StringUtils.getStringNotNull(ld_fecha.toString());
							}

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);

							if(lb_turnoDerivado)
							{
								StringBuilder           lsb_turnos;
								Iterator<TurnoDerivado> litd_iterador;

								lsb_turnos        = new StringBuilder(ls_idTurno);
								litd_iterador     = lctd_turnosDerivados.iterator();

								while(litd_iterador.hasNext())
								{
									TurnoDerivado ltd_tmp;

									ltd_tmp = litd_iterador.next();

									if(ltd_tmp != null)
									{
										String ls_idTurnoHijo;

										ls_idTurnoHijo = ltd_tmp.getIdTurnoHijo();

										if(StringUtils.isValidString(ls_idTurnoHijo))
										{
											lsb_turnos.append(IdentificadoresCommon.SIMBOLO_COMA);
											lsb_turnos.append(ls_idTurnoHijo);

											String ls_nir;

											ls_nir = lsd_solicitudDAO.findNirByIdTurno(ls_idTurnoHijo);

											if(StringUtils.isValidString(ls_nir))
												lcs_nirVinculados.add(ls_nir);
										}
									}
								}

								ldb_builder.write(StringUtils.getStringNotNull(lsb_turnos.toString()));
							}
							else
								ldb_builder.write(StringUtils.getStringNotNull(ls_idTurno));

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);
							ldb_builder.write(StringUtils.getStringNotNull(ls_fechaRadicacion));

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);
							ldb_builder.write(StringUtils.getStringNotNull(la_aprobacion.getTurnosAsociados()));

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);
							ldb_builder.write(StringUtils.getStringNotNull(la_aprobacion.getMotivoNoTramite()));

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);

							if(lb_turnoDerivado && CollectionUtils.isValidCollection(lcs_nirVinculados))
							{
								String           ls_nirPrincipal;
								StringBuilder    lsb_nir;
								Iterator<String> lis_iterador;

								ls_nirPrincipal     = StringUtils.getStringNotNull(la_aprobacion.getNir());
								lsb_nir             = new StringBuilder(ls_nirPrincipal);
								lis_iterador        = lcs_nirVinculados.iterator();

								while(lis_iterador.hasNext())
								{
									String ls_tmp;

									ls_tmp = lis_iterador.next();

									if(ls_tmp != null)
									{
										lsb_nir.append(IdentificadoresCommon.SIMBOLO_COMA);
										lsb_nir.append(ls_tmp);
									}
								}

								ldb_builder.write(StringUtils.getStringNotNull(lsb_nir.toString()));
							}
							else
								ldb_builder.write(StringUtils.getStringNotNull(la_aprobacion.getNir()));

							ldb_builder.endRow();
						}
					}

					lt_table.setAlignment(TableAlignment.CENTER);
					ldb_builder.endTable();
				}

				ldb_builder.moveToDocumentEnd();

				ldb_builder.insertParagraph();
				ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
				ldb_builder.getFont().setSize(6);
				ldb_builder.getFont().setBold(false);
				ldb_builder.write("Proyectó y elaboró: " + as_userId);

				ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

				byte[] lba_docBytes = lbaos_docOutStream.toByteArray();

				lba_return = new PDFGenerator().convertirRTFToPDF(lba_docBytes, adm_manager);
			}

			llhm_return.put(ls_numeroConsecutivo, lba_return);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("crearPdfAprobacionRelacion", lb2be_e);

			throw lb2be_e;
		}

		return llhm_return;
	}

	/**
	 * Método para poder salvar las aprobaciones hechas por el registrador.
	 *
	 * @param aca_param Objeto donde se tomara la información para poder salvar la información
	 * @param as_numeroRelacion correspondiente al valor del tipo de objeto String
	 * @param aba_documentoRelacion correspondiente al valor del tipo de objeto byte[]
	 * @param as_usuario Nombre de usuario del registrador
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Aprobacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Aprobacion
	 */
	private Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_param, String as_numeroRelacion, byte[] aba_documentoRelacion, String as_usuario,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		return salvarAprobacion(
		    aca_param, as_numeroRelacion, aba_documentoRelacion, as_usuario, as_remoteIp, adm_manager, false
		);
	}

	/**
	 * Método para poder salvar las aprobaciones hechas por el registrador.
	 *
	 * @param aca_param Objeto donde se tomara la información para poder salvar la información
	 * @param as_numeroRelacion correspondiente al valor del tipo de objeto String
	 * @param aba_documentoRelacion correspondiente al valor del tipo de objeto byte[]
	 * @param as_usuario Nombre de usuario del registrador
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @return devuelve el valor de Aprobacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Aprobacion
	 */
	private Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_param, String as_numeroRelacion, byte[] aba_documentoRelacion, String as_usuario,
	    String as_remoteIp, DAOManager adm_manager, boolean ab_firmar
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(aca_param))
			{
				TurnoHistoriaDAO      lthd_DAO;
				DocumentosSalidaDAO   lds_DAO;
				RelacionAprobacionDAO lra_DAO;
				ConstantesDAO         lc_DAO;
				int                   li_contador;
				long                  ll_idImagen;

				lthd_DAO        = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				lds_DAO         = DaoCreator.getDocumentosSalidaDAO(adm_manager);
				lra_DAO         = DaoCreator.getRelacionAprobacionDAO(adm_manager);
				lc_DAO          = DaoCreator.getConstantesDAO(adm_manager);
				li_contador     = 1;
				ll_idImagen     = NumericUtils.DEFAULT_LONG_VALUE;

				if(!StringUtils.isValidString(as_usuario))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_USUARIO);

				if(aba_documentoRelacion != null)
				{
					ImagenesDAO li_DAO;
					Imagenes    li_imagenRelacion;

					li_DAO                = DaoCreator.getImagenesDAO(adm_manager);
					li_imagenRelacion     = new Imagenes();

					li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					li_imagenRelacion.setIdUsuarioCreacion(as_usuario);
					li_imagenRelacion.setIpCreacion(as_remoteIp);
					li_imagenRelacion.setImagenBlob(aba_documentoRelacion);

					ll_idImagen = li_DAO.insertOrUpdate(li_imagenRelacion, true);

					if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
						throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
				}

				for(Aprobacion la_iterador : aca_param)
				{
					if(la_iterador != null)
					{
						Long          ll_turnoHistoria;
						TurnoHistoria lth_turnoHistoria;
						Turno         lt_turno;
						String        ls_idTurno;
						String        ls_observacionesAprobador;

						ll_turnoHistoria              = la_iterador.getIdTurnoHistoria();
						lth_turnoHistoria             = new TurnoHistoria();
						lt_turno                      = la_iterador.getTurno();
						ls_observacionesAprobador     = la_iterador.getObservacionesAprobador();

						if(lt_turno == null)
							throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

						ls_idTurno = StringUtils.getStringNotNull(lt_turno.getIdTurno());

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

						if(la_iterador.isGenerarRelacion())
						{
							String ls_motivoNoTramite;
							String ls_idSolicitud;

							ls_motivoNoTramite     = lth_turnoHistoria.getMotivoNoTramite();
							ls_idSolicitud         = lth_turnoHistoria.getIdSolicitud();

							if(
							    StringUtils.isValidString(ls_motivoNoTramite)
								    && ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.REALIZAR_REGISTRO_PARCIAL
								    )
							)
							{
								if(StringUtils.isValidString(ls_idSolicitud))
								{
									DocumentosSalida             lds_documentoSalida;
									Collection<DocumentosSalida> lcds_documentosSalida;

									lds_documentoSalida       = new DocumentosSalida();
									lcds_documentosSalida     = null;

									lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
									lcds_documentosSalida = lds_DAO.findByIdSolicitud(lds_documentoSalida);

									if(CollectionUtils.isValidCollection(lcds_documentosSalida))
									{
										boolean lb_notaDevolutiva;
										boolean lb_constanciaInscripcion;

										lb_notaDevolutiva            = false;
										lb_constanciaInscripcion     = false;

										for(DocumentosSalida lds_doc : lcds_documentosSalida)
										{
											if(lds_doc != null)
											{
												String ls_tipo;

												ls_tipo = lds_doc.getTipo();

												if(StringUtils.isValidString(ls_tipo))
												{
													if(ls_tipo.equalsIgnoreCase(TipoArchivoCommon.REGISTRO_PARCIAL))
														lb_constanciaInscripcion = true;

													if(
													    ls_tipo.equalsIgnoreCase(
														        TipoArchivoCommon.NOTA_DEVOLUTIVA_PARCIAL
														    )
													)
														lb_notaDevolutiva = true;
												}
												else
													throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_SALIDA_INVALIDO);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
										}

										if(!(lb_notaDevolutiva && lb_constanciaInscripcion))
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_FIRMA_INSCRIPCION_PARCIAL);
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_FIRMA_INSCRIPCION_PARCIAL);
								}
								else
									throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
							}

							if(aba_documentoRelacion != null)
							{
								String           ls_idConstante;
								DocumentosSalida lds_documento;
								Integer          lbi_idTurnoHistoria;
								Constantes       lc_tipoDocumental;

								ls_idConstante          = ConstanteCommon.TIPO_DOCUMENTAL_GENERAR_RELACION;
								lds_documento           = new DocumentosSalida();
								lbi_idTurnoHistoria     = NumericUtils.getInteger(ll_turnoHistoria.toString());
								lc_tipoDocumental       = lc_DAO.findById(ls_idConstante);

								if(lc_tipoDocumental == null)
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = ls_idConstante;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
								}

								lds_documento.setIdTurnoHistoria(lbi_idTurnoHistoria);
								lds_documento.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
								lds_documento.setIdTurno(ls_idTurno);
								lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));
								lds_documento.setRepositorio(
								    ab_firmar ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
								);
								lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								lds_documento.setTipo(TipoArchivoCommon.RELACION_APROBACION);
								lds_documento.setIdTipoDocumental(lc_tipoDocumental.getCaracter());
								lds_documento.setEstado(EstadoCommon.ENTREGA);
								lds_documento.setIdUsuarioCreacion(as_usuario);
								lds_documento.setIpCreacion(as_remoteIp);

								lds_DAO.insertOrUpdate(lds_documento, true);
							}

							if(StringUtils.isValidString(as_numeroRelacion))
							{
								RelacionAprobacion lra_relacion;
								lra_relacion = new RelacionAprobacion();

								lra_relacion.setNumeroRelacion(as_numeroRelacion);
								lra_relacion.setTipoCalificacion(la_iterador.getMotivoNoTramite());
								lra_relacion.setIdTurno(ls_idTurno);
								lra_relacion.setNir(la_iterador.getNir());
								lra_relacion.setEstado(EstadoCommon.ACTIVO);
								lra_relacion.setIdUsuarioCreacion(as_usuario);
								lra_relacion.setIpCreacion(as_remoteIp);

								lra_DAO.insert(lra_relacion);
							}

							{
								{
									String ls_estadoActividad;

									ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

									if(
									    StringUtils.isValidString(ls_estadoActividad)
										    && !ls_estadoActividad.equalsIgnoreCase(EstadoCommon.ASIGNADA)
									)
										throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
								}

								if(lth_turnoHistoria != null)
								{
									Solicitud ls_solicitud;
									long      ls_idEtapa;
									String    ls_idProceso;
									boolean   lb_procesoCopias;

									ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										ls_idEtapa           = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());
										ls_idProceso         = ls_solicitud.getIdProceso();
										lb_procesoCopias     = StringUtils.isValidString(ls_idProceso)
												&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5);

										TurnoHistoria lth_turnoHistoriaAnt;

										lth_turnoHistoriaAnt = lthd_DAO.findById(
											    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
											);

										BigDecimal lbd_idEtapa;
										Long       lL_idMotivo;

										long ll_idEtapa;
										long ll_idMotivo;

										lbd_idEtapa          = lth_turnoHistoriaAnt.getIdEtapa();
										lL_idMotivo          = lth_turnoHistoriaAnt.getIdMotivo();

										ll_idEtapa      = (lbd_idEtapa != null) ? NumericUtils.getLong(lbd_idEtapa) : 0L;
										ll_idMotivo     = (lL_idMotivo != null) ? NumericUtils.getLong(lL_idMotivo) : 0L;

										if(lb_procesoCopias)
										{
											if(lth_turnoHistoriaAnt != null)
											{
												{
													boolean lb_firmaMasiva;

													lb_firmaMasiva = ((ll_idEtapa == EtapaCommon.ID_ETAPA_20)
															&& (ll_idMotivo == MotivoTramiteCommon.COPIAS_CON_INFORMACION_DIGITALIZADAS))
															|| ((ll_idEtapa == EtapaCommon.VALIDACION_INFORMACION_EN_OWCC)
															&& (ll_idMotivo == MotivoTramiteCommon.CON_INFORMACION_EN_OWCC_PENDIENTE_PAGO))
															|| ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
															&& (ll_idMotivo == MotivoTramiteCommon.NOTA_INFORMATIVA_DE_NEGACION));

													if(lb_firmaMasiva)
													{
														insertarFirmaMasiva(
														    adm_manager, as_usuario,
														    StringUtils.getString(ll_turnoHistoria)
														);

														lth_turnoHistoria.setEstadoActividad(EstadoCommon.AUTOMATICA);
														lth_turnoHistoria.setObservaciones(ls_observacionesAprobador);

														lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);
													}
												}
											}
										}
										else
										{
											Map<TurnoHistoria, Boolean> lmthb_turnos;
											lmthb_turnos = null;

											BigDecimal lb_idEtapa;
											lb_idEtapa = lth_turnoHistoria.getIdEtapa();

											if(
											    NumericUtils.isValidBigDecimal(lb_idEtapa)
												    && (lb_idEtapa.longValue() != EtapaCommon.ID_ETAPA_380)
											)
												lmthb_turnos = getFirmaMasivaBusiness()
														               .turnosDerivados(
														    lth_turnoHistoria, EtapaCommon.ID_ETAPA_APROBACION,
														    adm_manager, true
														);

											if(CollectionUtils.isValidCollection(lmthb_turnos))
											{
												for(Map.Entry<TurnoHistoria, Boolean> lmthb_iterador : lmthb_turnos
													    .entrySet())
												{
													TurnoHistoria lth_turnoHistoriaActual;

													lth_turnoHistoriaActual = lmthb_iterador.getKey();

													if(lth_turnoHistoriaActual != null)
														if(
														    (lb_idEtapa.longValue() == EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO)
														)
															if(
															    ll_idEtapa == EtapaCommon.ID_ETAPA_BUSCADOR_AS_CIRCULO_DESTINO
															)
															{
																insertarFirmaMasiva(
																    adm_manager, as_usuario,
																    StringUtils.getString(
																        lth_turnoHistoriaActual.getIdTurnoHistoria()
																    )
																);

																lth_turnoHistoriaActual.setEstadoActividad(
																    EstadoCommon.AUTOMATICA
																);
																lth_turnoHistoriaActual.setObservaciones(
																    ls_observacionesAprobador
																);
																lth_turnoHistoriaActual.setNumeroRelacion(
																    as_numeroRelacion
																);

																lthd_DAO.insertOrUpdate(lth_turnoHistoriaActual, false);
															}
															else
																terminarTurnoHistoriaYCrearEtapa(
																    lth_turnoHistoria, adm_manager,
																    new MotivoTramite(
																        EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO,
																        MotivoTramiteCommon.APROBADOR_CIRCULO_DESTINO
																    ), as_usuario, as_remoteIp, EstadoCommon.TERMINADA
																);
														else if(
														    (lb_idEtapa.longValue() == EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA)
															    && (ll_idMotivo == MotivoTramiteCommon.COMPLEMETACION_APROBADA_CIRCULO_REGISTRAL_DESTINO_ENVIO_APROBACION_CIRCULO_REGISTRAL_ORIGEN)
															    && (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
														)
															terminarTurnoHistoriaYCrearEtapa(
															    lth_turnoHistoria, adm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA,
															        MotivoTramiteCommon.COMPLEMENTACION_DE_OTRO_CIRCULO_APROBADA_ENVIO_A_GENERACION_DE_CERTIFICADOS
															    ), as_usuario, as_remoteIp, EstadoCommon.TERMINADA
															);
														else
														{
															insertarFirmaMasiva(
															    adm_manager, as_usuario,
															    StringUtils.getString(
															        lth_turnoHistoriaActual.getIdTurnoHistoria()
															    )
															);

															lth_turnoHistoriaActual.setEstadoActividad(
															    EstadoCommon.AUTOMATICA
															);
															lth_turnoHistoriaActual.setObservaciones(
															    ls_observacionesAprobador
															);
															lth_turnoHistoriaActual.setIdUsuario(as_usuario);
															lth_turnoHistoriaActual.setNumeroRelacion(
															    as_numeroRelacion
															);

															lthd_DAO.insertOrUpdate(lth_turnoHistoriaActual, false);
														}
												}
											}
										}
									}
								}
							}
						}
					}

					li_contador++;
				}
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarAprobacion", lb2be_e);

			throw lb2be_e;
		}

		return null;
	}
}
