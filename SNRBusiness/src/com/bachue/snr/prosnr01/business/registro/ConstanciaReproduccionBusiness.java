package com.bachue.snr.prosnr01.business.registro;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.testamentos.TestamentosBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudReproduccionDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioSegregadoDAO;
import com.bachue.snr.prosnr01.dao.col.ParteInteresadaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.OficinaOrigenDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.VeredaDAO;
import com.bachue.snr.prosnr01.dao.pgn.ZonaRegistralDAO;
import com.bachue.snr.prosnr01.dao.png.GrupoNaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.NaturalezaJuridicaDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;

import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.col.ParteInteresada;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.GrupoNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


/**
 * Clase que contiene todos la logica para el manejo de contancia de reproduccion
 *
 * @author garias
 */
public class ConstanciaReproduccionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConstanciaReproduccionBusiness.class);

	/** Propiedad itb testamentos business. */
	private static TestamentosBusiness itb_testamentosBusiness;

	/**
	 * Encuentra constancia reproduccion.
	 *
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void findConstanciaReproduccion(String as_remoteIp)
	    throws B2BException
	{
		Collection<ConstanciaReproduccion> acl_solicitudReproduccion;
		DAOManager                         ldm_manager;

		acl_solicitudReproduccion     = null;
		ldm_manager                   = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = lcd_constantesDAO.findById(ConstanteCommon.JOB_CONSTANCIA_REPRODUCCION_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_limiteIntentos;

				ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_CONSTANCIA_REPRODUCCION_LIMITE_INTENTOS
					);

				acl_solicitudReproduccion = DaoCreator.getConsultaSolicitudReproduccionDAO(ldm_manager)
						                                  .findSolRepByStageState(
						    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_REPRODUCCION_DE_CONSTANCIA),
						    EstadoCommon.AUTOMATICA, ll_limiteIntentos
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acl_solicitudReproduccion))
			findConstanciaReproduccion(acl_solicitudReproduccion, as_remoteIp);
	}

	/**
	 * Encuentra una constancia reproduccion.
	 *
	 * @param accr_parametros correspondiente al valor del tipo de objeto Collection<ConstanciaReproduccion>
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized void findConstanciaReproduccion(
	    Collection<ConstanciaReproduccion> accr_parametros, String as_remoteIp
	)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CONSTANCIA_REPRODUCCION_BLOQUEO;
		ls_userId       = IdentificadoresCommon.JOB_CONSTANCIA_REPRODUCCION;

		{
			DAOManager ldm_processing;
			ldm_processing = DaoManagerFactory.getDAOManager();

			ldm_processing.setAutoCommit(true);

			try
			{
				ConstantesDAO lcd_constant;
				Constantes    lce_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lce_constant     = lcd_constant.findById(ls_constant);

				if(lce_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lce_constant.getCaracter());

					if(!lb_alreadyProcessing)
						lcd_constant.updateString(ls_constant, EstadoCommon.S, ls_userId);
				}
				else
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_constant;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_bitacora;
				DAOManager ldm_manager;

				ldm_bitacora     = DaoManagerFactory.getDAOManager();
				ldm_manager      = DaoManagerFactory.getDAOManager();

				ldm_bitacora.setAutoCommit(true);

				try
				{
					if(CollectionUtils.isValidCollection(accr_parametros))
					{
						String ls_usuarioProceso;

						ls_usuarioProceso = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_bitacora);

						if(StringUtils.isValidString(ls_usuarioProceso))
						{
							BitacoraProcesoDAO lbpd_bitacora;
							TurnoHistoriaDAO   lthd_turnoHistoriaDAO;

							lbpd_bitacora             = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
							lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

							for(ConstanciaReproduccion lcr_iterador : accr_parametros)
							{
								if(lcr_iterador != null)
								{
									TurnoHistoria lth_turno;

									lth_turno = lthd_turnoHistoriaDAO.findById(lcr_iterador.getTurnoHistoria());

									if(lth_turno != null)
									{
										String ls_mensaje;

										ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

										try
										{
											generarConstanciaReproduccion(
											    lcr_iterador, false, lbpd_bitacora, ls_usuarioProceso, as_remoteIp,
											    ldm_manager
											);

											lth_turno.setFechaExitoEjecucionAutomatica(new Date());
											ldm_manager.commit();
										}
										catch(B2BException lb2be_e)
										{
											ldm_manager.rollback();
											clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);

											ls_mensaje = getErrorMessage(lb2be_e);
										}

										actualizarIntentoEnvio(
										    lth_turno, ls_mensaje, ls_userId, as_remoteIp, ldm_bitacora
										);
									}
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_manager.setRollbackOnly();
					clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManager();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("findConstanciaReproduccion", lb2be_e);

					throw lb2be_e;
				}
				finally
				{
					ldm_processing.close();
				}
			}
		}
	}

	/**
	 * Retorna el valor del objeto de byte[] para generar la constancia de inscripcion
	 *
	 * @param acr_constanciaReproduccion correspondiente al valor del tipo de objeto ConstanciaReproduccion
	 * @param ab_firmaMasiva correspondiente al valor del tipo de objeto boolean
	 * @param as_idUsuario correspondiente al valor del tipo de objeto String
	 * @param as_ipRemota correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarConstanciaInscripcionRepConstancia(
	    ConstanciaReproduccion acr_constanciaReproduccion, boolean ab_firmaMasiva, String as_idUsuario,
	    String                 as_ipRemota
	)
	    throws B2BException
	{
		byte[]     lb_archivo;
		DAOManager ldm_manager;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_archivo      = null;

		try
		{
			if(acr_constanciaReproduccion != null)
			{
				SolicitudReproduccion lsr_solicitudReproduccion;
				TurnoHistoria         lth_idTurnoHistoria;

				lsr_solicitudReproduccion     = new SolicitudReproduccion();
				lth_idTurnoHistoria           = acr_constanciaReproduccion.getTurnoHistoria();

				if(lth_idTurnoHistoria != null)
				{
					lth_idTurnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findById(lth_idTurnoHistoria);

					if(lth_idTurnoHistoria != null)
					{
						lsr_solicitudReproduccion.setIdSolicitud(lth_idTurnoHistoria.getIdSolicitud());
						lsr_solicitudReproduccion.setIdCirculo(acr_constanciaReproduccion.getIdCirculo());
					}
				}

				lsr_solicitudReproduccion = DaoCreator.getConsultaSolicitudReproduccionDAO(ldm_manager)
						                                  .findByIdSolicitudIdCirculo(lsr_solicitudReproduccion, true);

				if(lsr_solicitudReproduccion != null)
				{
					acr_constanciaReproduccion.setSolicitudReproduccion(lsr_solicitudReproduccion);

					lb_archivo = archivoConstaciaReproduccion(acr_constanciaReproduccion, ldm_manager, false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarConstanciaInscripcionRepConstancia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_archivo;
	}

	/**
	 * Retorna el valor del objeto de byte[] para generar la constancia de reproduccion
	 *
	 * @param acr_constanciaReproduccion correspondiente al valor del tipo de objeto ConstanciaReproduccion
	 * @param ab_firmaMasiva correspondiente al valor del tipo de objeto boolean
	 * @param abpd_DAO correspondiente al valor del tipo de objeto BitacoraProcesoDAO
	 * @param as_userId correspondiente al valor del tipo de objeto String
	 * @param as_remoteIp correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] generarConstanciaReproduccion(
	    ConstanciaReproduccion acr_constanciaReproduccion, boolean ab_firmaMasiva, BitacoraProcesoDAO abpd_DAO,
	    String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_archivo;

		lba_archivo = null;

		try
		{
			if(acr_constanciaReproduccion != null)
			{
				SolicitudReproduccion    lsr_solicitudReproduccion;
				TurnoHistoria            lth_turnoHistoria;
				boolean                  lb_reproduccionConstancia;
				TurnoHistoriaDAO         lthd_DAO;
				SolicitudReproduccionDAO lsrd_DAO;
				SolicitudDAO             ls_DAO;
				Solicitud                ls_solicitud;
				boolean                  lb_reproduccionTestamento;

				lthd_DAO                      = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				lsrd_DAO                      = DaoCreator.getConsultaSolicitudReproduccionDAO(adm_manager);
				ls_DAO                        = DaoCreator.getSolicitudDAO(adm_manager);
				lth_turnoHistoria             = acr_constanciaReproduccion.getTurnoHistoria();
				lsr_solicitudReproduccion     = acr_constanciaReproduccion.getSolicitudReproduccion();
				lb_reproduccionConstancia     = false;
				ls_solicitud                  = null;
				lb_reproduccionTestamento     = false;

				if(lth_turnoHistoria != null)
					lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

				if(lsr_solicitudReproduccion != null)
					lsr_solicitudReproduccion = lsrd_DAO.findById(lsr_solicitudReproduccion);

				if(lsr_solicitudReproduccion != null)
					ls_solicitud = ls_DAO.findById(lsr_solicitudReproduccion.getIdSolicitud());

				lb_reproduccionConstancia = lsr_solicitudReproduccion == null;

				if(!lb_reproduccionConstancia)
				{
					if(ls_solicitud != null)
					{
						String ls_reproduccionTestamento;
						ls_reproduccionTestamento     = ls_solicitud.getReproduccionTestamento();

						lb_reproduccionTestamento = StringUtils.isValidString(ls_reproduccionTestamento)
								&& ls_reproduccionTestamento.equalsIgnoreCase(IdentificadoresCommon.S);

						if(lb_reproduccionTestamento)
						{
							ReproduccionConstanciaTestamento lst_reproduccionConstanciaTestamento;
							Testamento                       lt_testamento;

							lst_reproduccionConstanciaTestamento     = new ReproduccionConstanciaTestamento();
							lt_testamento                            = new Testamento();

							lst_reproduccionConstanciaTestamento.setDocumento(
							    DaoCreator.getDocumentoDAO(adm_manager).findById(ls_solicitud.getIdDocumento())
							);

							lt_testamento.setIdTestamento(ls_solicitud.getIdTestamento());
							lst_reproduccionConstanciaTestamento.setTestamento(
							    DaoCreator.getTestamentoDAO(adm_manager).findById(lt_testamento)
							);

							lba_archivo = getTestamentosBusiness()
									              .generarConstanciaReproduccionTestamento(
									    lst_reproduccionConstanciaTestamento, lth_turnoHistoria.getIdTurno(), as_userId,
									    as_remoteIp, adm_manager, false, false
									);
						}
						else
							lba_archivo = archivoConstaciaReproduccion(
								    acr_constanciaReproduccion, adm_manager, ab_firmaMasiva
								);
					}
				}

				if((lth_turnoHistoria != null) && ((lba_archivo != null) || lb_reproduccionConstancia))
				{
					if(!lb_reproduccionConstancia)
					{
						long                ll_idDocumentoSalida;
						long                ll_secuencia;
						boolean             lb_existeImagen;
						ImagenesDAO         lid_DAO;
						DocumentosSalidaDAO ldsd_DAO;
						Imagenes            li_imagenes;
						DocumentosSalida    lds_documentosSalida;

						lid_DAO      = DaoCreator.getImagenesDAO(adm_manager);
						ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

						li_imagenes              = new Imagenes();
						lds_documentosSalida     = new DocumentosSalida();
						ll_idDocumentoSalida     = 0;

						lb_existeImagen = ll_idDocumentoSalida > 0;

						li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						li_imagenes.setIdUsuarioCreacion(as_userId);
						li_imagenes.setIdUsuarioModificacion(as_userId);
						li_imagenes.setIpCreacion(as_remoteIp);
						li_imagenes.setIpModificacion(as_remoteIp);
						li_imagenes.setImagenBlob(lba_archivo);

						ll_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen);

						if(!lb_existeImagen)
						{
							if(ll_secuencia <= 0)
								throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

							lds_documentosSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
							lds_documentosSalida.setIdSolicitud(lsr_solicitudReproduccion.getIdSolicitud());

							lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
							lds_documentosSalida.setTipo(
							    lb_reproduccionTestamento ? TipoArchivoCommon.CONSTANCIA_REPRODUCCION_TESTAMENTOS
							                              : TipoArchivoCommon.CONSTANCIA_REPRODUCCION
							);
							lds_documentosSalida.setEstado(ab_firmaMasiva ? EstadoCommon.ACTIVO : EstadoCommon.ENTREGA);
							lds_documentosSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
							);
							lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_REPRODUCCION);
							lds_documentosSalida.setRepositorio(
							    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
							);
							lds_documentosSalida.setIdUsuarioCreacion(as_userId);
							lds_documentosSalida.setIdUsuarioModificacion(as_userId);
							lds_documentosSalida.setIpModificacion(as_remoteIp);
							lds_documentosSalida.setIpCreacion(as_remoteIp);

							ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);
						}

						getFirmaMasivaBusiness()
							    .generarPlantillaRegistro(
							    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp, ls_solicitud.getNir(),
							    lsr_solicitudReproduccion.getIdTurnoReproduccion(), false
							);
					}

					if(!ab_firmaMasiva)
					{
						MotivoTramite    lmt_motivo;
						MotivoTramiteDAO lmtd_DAO;
						long             ll_idMotivo;
						ll_idMotivo     = 0L;

						lmt_motivo     = new MotivoTramite();
						lmtd_DAO       = DaoCreator.getMotivoTramiteDAO(adm_manager);

						//CONSTANCIA_REPRODUCCION_SIN_REGISTROS
						if(lb_reproduccionConstancia)
						{
							lsr_solicitudReproduccion = new SolicitudReproduccion();
							lsr_solicitudReproduccion.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());
						}

						lsr_solicitudReproduccion     = lsrd_DAO.findByIdSolicitudIdCirculo(
							    lsr_solicitudReproduccion, false
							);
						ls_solicitud                  = ls_DAO.findById(lth_turnoHistoria.getIdSolicitud());

						if(ls_solicitud != null)
						{
							String ls_reproduccionTestamento;
							ls_reproduccionTestamento = ls_solicitud.getReproduccionTestamento();

							if(StringUtils.isValidString(ls_reproduccionTestamento))
							{
								if((lsr_solicitudReproduccion != null) && ls_reproduccionTestamento.equals("N"))
									ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION;
								else if((lsr_solicitudReproduccion == null) && ls_reproduccionTestamento.equals("N"))
									ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION_SIN_REGISTROS;
								else if((lsr_solicitudReproduccion != null) && ls_reproduccionTestamento.equals("S"))
									ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION_TESTAMENTO;
								else if((lsr_solicitudReproduccion == null) && ls_reproduccionTestamento.equals("S"))
									ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION_TESTAMENTO_SIN_REGISTROS;
							}
						}
						else if(lsr_solicitudReproduccion != null)
							ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION;
						else
							ll_idMotivo = MotivoTramiteCommon.CONSTANCIA_REPRODUCCION_SIN_REGISTROS;

						lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_REPRODUCCION_DE_CONSTANCIA);

						lmt_motivo.setIdMotivo(ll_idMotivo);

						lmt_motivo = lmtd_DAO.findById(lmt_motivo);

						if(lmt_motivo != null)
						{
							if(
							    (lth_turnoHistoria != null)
								    && !lth_turnoHistoria.getEstadoActividad().equalsIgnoreCase(EstadoCommon.TERMINADA)
							)
							{
								{
									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
									{
										String ls_observaciones;

										ls_observaciones = lth_turnoHistoriaAnterior.getObservaciones();

										if(StringUtils.isValidString(ls_observaciones))
											lth_turnoHistoria.setObservaciones(ls_observaciones);
									}
								}

								lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
								lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
								lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
								lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
								lth_turnoHistoria.setIpModificacion(as_remoteIp);

								lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

								DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarConstanciaReproduccion", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarConstanciaReproduccion", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}

		return lba_archivo;
	}

	/**
	 * Retorna Objeto o variable de valor testamentos business.
	 *
	 * @return el valor de testamentos business
	 */
	protected TestamentosBusiness getTestamentosBusiness()
	{
		if(itb_testamentosBusiness == null)
			itb_testamentosBusiness = new TestamentosBusiness();

		return itb_testamentosBusiness;
	}

	/**
	 * Retorna el valor del objeto de byte[] que representa el archivo de constancia de reproduccion
	 *
	 * @param acr_constanciaReproduccion correspondiente al valor del tipo de objeto ConstanciaReproduccion
	 * @param adm_manager correspondiente al valor del tipo de objeto DAOManager
	 * @param ab_firmaMasiva correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private byte[] archivoConstaciaReproduccion(
	    ConstanciaReproduccion acr_constanciaReproduccion, DAOManager adm_manager, boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		String ls_plantilla;
		byte[] lba_archivo;

		ls_plantilla     = null;
		lba_archivo      = null;

		if((acr_constanciaReproduccion != null) && (adm_manager != null))
		{
			byte[]     lba_plantilla;
			Constantes lc_constante;
			String     ls_constante;
			String     ls_sinInformacion;

			ls_constante          = ConstanteCommon.PLANTILLA_CONSTANCIA_REPRODUCCION;
			ls_sinInformacion     = ConstanteCommon.SIN_INFORMACION;
			lc_constante          = new Constantes();

			lc_constante.setIdConstante(ls_constante);

			lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

			if(lc_constante == null)
			{
				Object[] loa_messageArgs;

				loa_messageArgs        = new String[1];
				loa_messageArgs[0]     = ls_constante;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
			}

			lba_plantilla = lc_constante.getImagenes();

			if(lba_plantilla == null)
			{
				Object[] loa_messageArgs = new String[1];
				loa_messageArgs[0] = ls_constante;

				throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
			}
			else
			{
				SolicitudReproduccion lsr_solicitudReproduccion;

				lsr_solicitudReproduccion = acr_constanciaReproduccion.getSolicitudReproduccion();

				if(lsr_solicitudReproduccion != null)
				{
					lsr_solicitudReproduccion = DaoCreator.getConsultaSolicitudReproduccionDAO(adm_manager)
							                                  .findById(lsr_solicitudReproduccion);

					if(lsr_solicitudReproduccion != null)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = acr_constanciaReproduccion.getTurnoHistoria();

						if(lth_turnoHistoria != null)
						{
							String                      ls_turno;
							AnotacionPredio             lap_anotacionPredio;
							Collection<AnotacionPredio> lcap_anotacionPredio;

							ls_plantilla            = new String(lba_plantilla);
							ls_turno                = lsr_solicitudReproduccion.getIdTurnoReproduccion();
							lap_anotacionPredio     = new AnotacionPredio();

							lap_anotacionPredio.setRadicacion(ls_turno);

							lcap_anotacionPredio     = DaoCreator.getAnotacionPredioDAO(adm_manager)
									                                 .findByRadicacion(lap_anotacionPredio);
							lth_turnoHistoria        = DaoCreator.getTurnoHistoriaDAO(adm_manager)
									                                 .findById(lth_turnoHistoria);

							String                      ls_tag;
							StringBuilder               lsb_anotaciones;
							Integer                     li_idAnotacion;
							Calendar                    lic_fecha;
							int                         li_anho;
							int                         li_mes;
							int                         li_dia;
							Date                        ld_fechaActual;
							DateFormat                  lsf_formatTime;
							String                      ls_hora;
							long                        ll_idMatriculaTmp;
							DireccionPredioDAO          ldpd_DAO;
							TipoEjeDAO                  lted_DAO;
							DocumentoDAO                ldd_DAO;
							TipoDocumentoPublicoDAO     ltdpd_DAO;
							OficinaOrigenDAO            lood_DAO;
							MunicipioDAO                lmd_DAO;
							NaturalezaJuridicaDAO       lnjd_DAO;
							GrupoNaturalezaJuridicaDAO  lgnjd_DAO;
							AnotacionPredioCiudadanoDAO lapcd_DAO;
							PersonaDAO                  lpd_DAO;
							ParteInteresadaDAO          lpid_DAO;
							CirculoRegistralDao         lcrd_DAO;
							PredioRegistroDAO           lprd_DAO;
							ZonaRegistralDAO            lzrd_DAO;
							DepartamentoDAO             lded_DAO;
							VeredaDAO                   lvd_DAO;
							PredioSegregadoDAO          lpsd_DAO;

							ls_tag                   = null;
							lsb_anotaciones          = new StringBuilder();
							li_idAnotacion           = null;

							lic_fecha             = Calendar.getInstance();
							li_anho               = lic_fecha.get(Calendar.YEAR);
							li_mes                = lic_fecha.get(Calendar.MONTH) + 1;
							li_dia                = lic_fecha.get(Calendar.DAY_OF_MONTH);
							ld_fechaActual        = new Date();
							lsf_formatTime        = new SimpleDateFormat("HH:mm:ss");
							ls_hora               = lsf_formatTime.format(ld_fechaActual);
							ll_idMatriculaTmp     = -1;
							ldpd_DAO              = DaoCreator.getDireccionPredioDAO(adm_manager);
							lted_DAO              = DaoCreator.getTipoEjeDAO(adm_manager);
							ldd_DAO               = DaoCreator.getDocumentoDAO(adm_manager);
							ltdpd_DAO             = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
							lood_DAO              = DaoCreator.getOficinaOrigenDAO(adm_manager);
							lmd_DAO               = DaoCreator.getMunicipioDAO(adm_manager);
							lnjd_DAO              = DaoCreator.getNaturalezaJuridicaDAO(adm_manager);
							lgnjd_DAO             = DaoCreator.getGrupoNaturalezaJuridicaDAO(adm_manager);
							lapcd_DAO             = DaoCreator.getAnotacionPredioCiudadanoDAO(adm_manager);
							lpd_DAO               = DaoCreator.getPersonaDAO(adm_manager);
							lpid_DAO              = DaoCreator.getParteInteresadaDAO(adm_manager);
							lcrd_DAO              = DaoCreator.getCirculoRegistralDAO(adm_manager);
							lprd_DAO              = DaoCreator.getPredioRegistroDAO(adm_manager);
							lzrd_DAO              = DaoCreator.getZonaRegistralDAO(adm_manager);
							lded_DAO              = DaoCreator.getDepartamentoDAO(adm_manager);
							lvd_DAO               = DaoCreator.getVeredaDAO(adm_manager);
							lpsd_DAO              = DaoCreator.getPredioSegregadoDAO(adm_manager);

							ls_tag = "<TAG_DIA>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_tmp;
								ls_tmp = String.valueOf(li_dia);

								if(StringUtils.isValidString(ls_tmp))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
							}

							ls_tag = "<TAG_MES>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_tmp;
								ls_tmp = String.valueOf(li_mes);

								if(StringUtils.isValidString(ls_tmp))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
							}

							ls_tag = "<TAG_ANHO>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_tmp;
								ls_tmp = String.valueOf(li_anho);

								if(StringUtils.isValidString(ls_tmp))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
							}

							ls_tag = "<TAG_HORA>";

							if(ls_plantilla.contains(ls_tag))
							{
								if(StringUtils.isValidString(ls_hora))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_hora);
							}

							ls_tag = "<TAG_ID_NIR>";

							if(ls_plantilla.contains(ls_tag))
							{
								String ls_idSolicitud;
								ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud lso_solicitud;
									lso_solicitud = new Solicitud();

									lso_solicitud.setIdSolicitud(ls_idSolicitud);

									lso_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lso_solicitud);

									if(lso_solicitud != null)
										ls_plantilla = ls_plantilla.replace(ls_tag, lso_solicitud.getNir());
								}
							}

							ls_tag = "<TAG_ID_TURNO>";

							if(ls_plantilla.contains(ls_tag))
							{
								if(lth_turnoHistoria != null)
								{
									String ls_idTurno;
									ls_idTurno = lth_turnoHistoria.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_idTurno);
								}
							}

							if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
							{
								for(AnotacionPredio lap_anotacionPredioIterador : lcap_anotacionPredio)
								{
									if(lap_anotacionPredioIterador != null)
									{
										String ls_idCirculo;
										Long   lL_idMatricula = null;
										long   ll_idMatricula;

										ls_idCirculo          = lap_anotacionPredioIterador.getIdCirculo();
										lL_idMatricula        = lap_anotacionPredioIterador.getIdMatricula();
										ll_idMatricula        = NumericUtils.getLong(lL_idMatricula);

										if(ll_idMatriculaTmp != ll_idMatricula)
										{
											DireccionPredio             ldp_direccionPredio;
											Collection<DireccionPredio> lcdp_direccionPredio;

											ldp_direccionPredio = new DireccionPredio();

											ldp_direccionPredio.setIdCirculo(ls_idCirculo);
											ldp_direccionPredio.setIdMatricula(
											    NumericUtils.getLongWrapper(ll_idMatricula)
											);

											lsb_anotaciones.append("{\\pard ");
											lsb_anotaciones.append("Nro Matrícula:");
											lsb_anotaciones.append("{\\tab ");
											lsb_anotaciones.append(ls_idCirculo);
											lsb_anotaciones.append(" - ");
											lsb_anotaciones.append(ll_idMatricula);
											lsb_anotaciones.append("}");
											lsb_anotaciones.append("\\par}");

											ll_idMatriculaTmp     = ll_idMatricula;

											lcdp_direccionPredio = ldpd_DAO.findByIdCirculoMatricula(
												    ldp_direccionPredio
												);

											if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
											{
												StringBuilder lsb_direcciones;
												lsb_direcciones = new StringBuilder();

												for(DireccionPredio ldp_iterador : lcdp_direccionPredio)
												{
													if(ldp_iterador != null)
													{
														lsb_direcciones.append("{\\pard ");
														lsb_direcciones.append(ldp_iterador.getIdDireccion() + ") ");

														{
															String ls_tipoEje;
															ls_tipoEje = ldp_iterador.getIdTipoEjePrincipal();

															if(StringUtils.isValidString(ls_tipoEje))
															{
																TipoEje lte_tmp;
																lte_tmp = new TipoEje();
																lte_tmp.setIdTipoEje(ls_tipoEje);

																lte_tmp = lted_DAO.findById(lte_tmp);

																if(lte_tmp != null)
																	lsb_direcciones.append(
																	    StringUtils.getStringNotNull(
																	        lte_tmp.getNombre()
																	    ) + " "
																	);
															}
														}

														lsb_direcciones.append(
														    StringUtils.getStringNotNull(
														        ldp_iterador.getDatoEjePrincipal()
														    ) + " "
														);

														{
															String ls_tipoEje1;
															ls_tipoEje1 = ldp_iterador.getIdTipoEjeSecundario();

															if(StringUtils.isValidString(ls_tipoEje1))
															{
																TipoEje lte_tmp;
																lte_tmp = new TipoEje();
																lte_tmp.setIdTipoEje(ls_tipoEje1);

																lte_tmp = lted_DAO.findById(lte_tmp);

																if(lte_tmp != null)
																	lsb_direcciones.append(
																	    StringUtils.getStringNotNull(
																	        lte_tmp.getNombre()
																	    ) + " "
																	);
															}
														}

														lsb_direcciones.append(
														    StringUtils.getStringNotNull(
														        ldp_iterador.getDatoEjeSecundario()
														    ) + " "
														);
														lsb_direcciones.append(
														    StringUtils.getStringNotNull(
														        ldp_iterador.getComplementoDireccion()
														    )
														);

														lsb_direcciones.append("\\par}");
													}
												}

												lsb_anotaciones.append(lsb_direcciones);
												lsb_anotaciones.append("{\\pard \\par}");
											}

											{
												CirculoRegistral lcr_circuloRegistral;

												lcr_circuloRegistral = new CirculoRegistral();
												lcr_circuloRegistral.setIdCirculo(ls_idCirculo);

												lcr_circuloRegistral = lcrd_DAO.findById(lcr_circuloRegistral);

												if(lcr_circuloRegistral != null)
												{
													String ls_nombre;
													ls_nombre = lcr_circuloRegistral.getNombre();

													if(StringUtils.isValidString(ls_nombre))
													{
														lsb_anotaciones.append("{\\b CIRCULO REGISTRAL: }");
														lsb_anotaciones.append(ls_nombre);
														lsb_anotaciones.append(" - ");
														lsb_anotaciones.append(ls_idCirculo);
													}
												}
											}

											{
												PredioRegistro lpr_predioRegistro;
												lpr_predioRegistro = new PredioRegistro();

												lpr_predioRegistro.setIdCirculo(ls_idCirculo);
												lpr_predioRegistro.setIdMatricula(ll_idMatricula);

												lpr_predioRegistro = lprd_DAO.findByCirculoMatricula(
													    lpr_predioRegistro
													);

												if(lpr_predioRegistro != null)
												{
													ZonaRegistral lzr_zonaRegistral;
													lzr_zonaRegistral = new ZonaRegistral();

													lzr_zonaRegistral.setIdZonaRegistral(
													    lpr_predioRegistro.getIdZonaRegistral()
													);

													lzr_zonaRegistral = lzrd_DAO.findById(lzr_zonaRegistral);

													if(lzr_zonaRegistral != null)
													{
														String ls_pais;
														String ls_departamento;
														String ls_municipio;
														String ls_vereda;

														ls_pais             = lzr_zonaRegistral.getIdPais();
														ls_departamento     = lzr_zonaRegistral.getIdDepartamento();
														ls_municipio        = lzr_zonaRegistral.getIdMunicipio();
														ls_vereda           = lzr_zonaRegistral.getIdVereda();

														if(
														    StringUtils.isValidString(ls_pais)
															    && StringUtils.isValidString(ls_departamento)
															    && StringUtils.isValidString(ls_municipio)
															    && StringUtils.isValidString(ls_vereda)
														)
														{
															Departamento ld_departamento;
															Municipio    lm_municipio;
															Vereda       lv_vereda;

															ld_departamento     = new Departamento();
															lm_municipio        = new Municipio();
															lv_vereda           = new Vereda();

															ld_departamento.setIdPais(ls_pais);
															ld_departamento.setIdDepartamento(ls_departamento);

															ld_departamento = lded_DAO.findById(ld_departamento);

															if(ld_departamento != null)
															{
																String ls_nombreDepartamento;
																ls_nombreDepartamento = ld_departamento.getNombre();

																if(StringUtils.isValidString(ls_nombreDepartamento))
																{
																	lsb_anotaciones.append(" ");
																	lsb_anotaciones.append("{\\b DPTO: }");
																	lsb_anotaciones.append(ls_nombreDepartamento);
																}
															}

															lm_municipio.setIdPais(ls_pais);
															lm_municipio.setIdDepartamento(ls_departamento);
															lm_municipio.setIdMunicipio(ls_municipio);

															lm_municipio = lmd_DAO.findById(lm_municipio);

															if(lm_municipio != null)
															{
																String ls_nombreMun;
																ls_nombreMun = lm_municipio.getNombre();

																if(StringUtils.isValidString(ls_nombreMun))
																{
																	lsb_anotaciones.append(" ");
																	lsb_anotaciones.append("{\\b MUNICIPIO: }");
																	lsb_anotaciones.append(ls_nombreMun);
																}
															}

															lv_vereda.setIdPais(ls_pais);
															lv_vereda.setIdDepartamento(ls_departamento);
															lv_vereda.setIdMunicipio(ls_municipio);
															lv_vereda.setIdVereda(ls_vereda);

															lv_vereda = lvd_DAO.findById(lv_vereda);

															if(lv_vereda != null)
															{
																String ls_nombreVereda;
																ls_nombreVereda = lv_vereda.getNombre();

																if(StringUtils.isValidString(ls_nombreVereda))
																{
																	lsb_anotaciones.append(" ");
																	lsb_anotaciones.append("{\\b VEREDA: }");
																	lsb_anotaciones.append(ls_nombreVereda);
																}
															}
														}
													}
												}

												lsb_anotaciones.append("{\\pard \\par}");
											}
										}

										li_idAnotacion = NumericUtils.getInteger(
											    lap_anotacionPredioIterador.getIdAnotacion()
											);

										lsb_anotaciones.append("{\\pard ");
										lsb_anotaciones.append(
										    "_____________________________________________________________________________________________________________________"
										);
										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard \\par}");

										lsb_anotaciones.append("{\\pard ");

										lsb_anotaciones.append(
										    "{\\b ANOTACIÓN: Nro. " + String.format("%03d", li_idAnotacion)
										);
										lsb_anotaciones.append(
										    " Fecha: } "
										    + StringUtils.getString(
										        lap_anotacionPredioIterador.getFechaRadicacion(), "dd-MM-yyyy"
										    )
										);
										lsb_anotaciones.append(
										    " {\\b Radicación: }" + lap_anotacionPredioIterador.getRadicacion()
										);
										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");

										{
											Documento ld_documento;
											ld_documento = new Documento();

											ld_documento.setIdDocumento(lap_anotacionPredioIterador.getIdDocumento());
											ld_documento.setVersionDocumento(
											    NumericUtils.getLongWrapper(
											        lap_anotacionPredioIterador.getVersionDocumento()
											    )
											);

											ld_documento = ldd_DAO.findByIdDocumentoVersion(ld_documento);

											if(ld_documento != null)
											{
												TipoDocumentoPublico ltdp_tipoDocPublico;
												ltdp_tipoDocPublico = new TipoDocumentoPublico();

												ltdp_tipoDocPublico.setIdTipoDocumento(
												    ld_documento.getIdTipoDocumento()
												);

												ltdp_tipoDocPublico = ltdpd_DAO.findById(ltdp_tipoDocPublico);

												if(ltdp_tipoDocPublico != null)
													lsb_anotaciones.append("Doc: " + ltdp_tipoDocPublico.getNombre());

												lsb_anotaciones.append(" " + ld_documento.getNumero() + " DE FECHA ");
												lsb_anotaciones.append(
												    StringUtils.getString(
												        ld_documento.getFechaDocumento(), "dd-MM-yyyy"
												    )
												);

												{
													OficinaOrigen loo_oficinaOrigen;
													loo_oficinaOrigen = new OficinaOrigen();

													loo_oficinaOrigen.setIdOficinaOrigen(
													    ld_documento.getIdOficinaOrigen()
													);

													loo_oficinaOrigen.setVersion(ld_documento.getVersion());

													loo_oficinaOrigen = lood_DAO.findById(loo_oficinaOrigen);

													if(loo_oficinaOrigen != null)
													{
														String ls_pais;
														String ls_departamento;
														String ls_municipio;

														ls_pais             = loo_oficinaOrigen.getIdPais();
														ls_departamento     = loo_oficinaOrigen.getIdDepartamento();
														ls_municipio        = loo_oficinaOrigen.getIdMunicipio();

														lsb_anotaciones.append(" DE ");

														lsb_anotaciones.append(" " + loo_oficinaOrigen.getNombre());

														if(
														    StringUtils.isValidString(ls_pais)
															    && StringUtils.isValidString(ls_departamento)
															    && StringUtils.isValidString(ls_municipio)
														)
														{
															Municipio lm_municipio;
															lm_municipio = new Municipio();

															lm_municipio.setIdPais(ls_pais);
															lm_municipio.setIdDepartamento(ls_departamento);
															lm_municipio.setIdMunicipio(ls_municipio);

															lm_municipio = lmd_DAO.findById(lm_municipio);

															if(lm_municipio != null)
																lsb_anotaciones.append(" " + lm_municipio.getNombre());
														}
													}
													else
													{
														String ls_comentario;
														ls_comentario = ld_documento.getComentario();

														if(StringUtils.isValidString(ls_comentario))
															lsb_anotaciones.append(" " + ls_comentario);
														else
															lsb_anotaciones.append(" " + ls_sinInformacion);
													}
												}
											}
										}

										{
											BigDecimal lbd_valor;
											lbd_valor = lap_anotacionPredioIterador.getValor();

											lsb_anotaciones.append("{\\tab VALOR ACTO: $");

											if(lbd_valor != null)
											{
												NumberFormat lnf_numbreFormat;
												lnf_numbreFormat = NumberFormat.getNumberInstance(Locale.getDefault());

												String ls_valor;
												ls_valor = lnf_numbreFormat.format(lbd_valor);

												lsb_anotaciones.append(ls_valor);
											}

											lsb_anotaciones.append("}");
										}

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");

										String ls_comentario;
										ls_comentario = " ";

										{
											NaturalezaJuridica lnj_naturaleza;
											lnj_naturaleza = new NaturalezaJuridica();

											lnj_naturaleza.setIdNaturalezaJuridica(
											    lap_anotacionPredioIterador.getIdNaturalezaJuridica()
											);
											lnj_naturaleza.setVersion(lap_anotacionPredioIterador.getVersion());

											lnj_naturaleza = lnjd_DAO.findById(lnj_naturaleza);

											if(lnj_naturaleza != null)
											{
												GrupoNaturalezaJuridica lgnj_grupoNatJur;
												lgnj_grupoNatJur = new GrupoNaturalezaJuridica();

												lgnj_grupoNatJur.setIdGrupoNatJuridica(
												    lnj_naturaleza.getIdGrupoNatJur()
												);

												lgnj_grupoNatJur = lgnjd_DAO.findById(lgnj_grupoNatJur);

												if(lgnj_grupoNatJur != null)
													lsb_anotaciones.append(
													    "ESPECIFICACIÓN: " + lgnj_grupoNatJur.getNombre() + ": "
													);
												else
													lsb_anotaciones.append(ls_sinInformacion);

												{
													String ls_idNatJuridica;
													ls_idNatJuridica = lnj_naturaleza.getIdNaturalezaJuridica();

													if(StringUtils.isValidString(ls_idNatJuridica))
														lsb_anotaciones.append(ls_idNatJuridica + " ");
												}

												{
													String ls_nombreNatJuridica;
													ls_nombreNatJuridica = lnj_naturaleza.getNombre();

													if(StringUtils.isValidString(ls_nombreNatJuridica))
														lsb_anotaciones.append(ls_nombreNatJuridica);
												}

												{
													ls_comentario = lap_anotacionPredioIterador.getComentario();

													if(StringUtils.isValidString(ls_comentario))
														lsb_anotaciones.append(" " + ls_comentario);
												}
											}
										}

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");
										lsb_anotaciones.append("COMENTARIO: " + ls_comentario);
										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard ");
										lsb_anotaciones.append(
										    "{\\b PERSONAS QUE INTERVINIENEN EN EL ACTO (X-Titular de derecho real de dominio, I-Titular de dominio incompleto)}"
										);

										lsb_anotaciones.append("\\par}");

										lsb_anotaciones.append("{\\pard \\par}");

										{
											AnotacionPredioCiudadano             lapc_anotacionPredCiu;
											Collection<AnotacionPredioCiudadano> lcapc_intervinientes;

											lapc_anotacionPredCiu = new AnotacionPredioCiudadano();

											lapc_anotacionPredCiu.setIdCirculo(ls_idCirculo);
											lapc_anotacionPredCiu.setIdMatricula(ll_idMatricula);
											lapc_anotacionPredCiu.setIdAnotacion(NumericUtils.getLong(li_idAnotacion));

											lcapc_intervinientes = lapcd_DAO.findByCirculoMatricula(
												    lapc_anotacionPredCiu
												);

											if(CollectionUtils.isValidCollection(lcapc_intervinientes))
											{
												String ls_infoInterviniente;
												lsb_anotaciones.append("{\\rtf1\\ansi\\deff0 ");

												{
													String ls_titulosInterviniente;

													ls_titulosInterviniente     = null;

													ls_titulosInterviniente     = "  \\trowd\\cellx800\\cellx4000\\cellx6000\\cellx8000\\cellx9000\\cellx10000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Rol" + "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Nombre"
														+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Identificación" + "\\cell\\intbl " + "Propietario"
														+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} " + "%"
														+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
														+ "Calidad Interviniente" + "\\cell\\row ";

													ls_titulosInterviniente     = "{\\pard \\s3\\f1 \\b"
														+ ls_titulosInterviniente + "}";

													lsb_anotaciones = lsb_anotaciones.append(ls_titulosInterviniente);
												}

												for(AnotacionPredioCiudadano lapc_iterador : lcapc_intervinientes)
												{
													if(lapc_iterador != null)
													{
														ls_infoInterviniente = null;

														String  ls_rol;
														String  ls_nombrePersona;
														String  ls_tipoDocumento;
														String  ls_numeroDocumento;
														String  ls_propietario;
														String  ls_porcentaje;
														String  ls_descripcion;
														Persona lp_persona;

														ls_rol                 = lapc_iterador.getRolPersona();
														lp_persona             = new Persona();
														ls_nombrePersona       = "";
														ls_tipoDocumento       = "";
														ls_numeroDocumento     = "";
														ls_descripcion         = "";

														lp_persona.setIdPersona(lapc_iterador.getIdPersona());

														lp_persona = lpd_DAO.findById(lp_persona);

														if(lp_persona != null)
														{
															ls_tipoDocumento = lp_persona.getIdDocumentoTipo();

															if(StringUtils.isValidString(ls_tipoDocumento))
															{
																if(
																    ls_tipoDocumento.equalsIgnoreCase(
																	        IdentificadoresCommon.NIT
																	    )
																)
																	ls_nombrePersona = lp_persona.getRazonSocial();
																else
																{
																	String ls_primerNombre;
																	String ls_segundoNombre;
																	String ls_primerApellido;
																	String ls_segundoApellido;

																	ls_primerNombre        = lp_persona.getPrimerNombre();
																	ls_segundoNombre       = lp_persona.getSegundoNombre();
																	ls_primerApellido      = lp_persona
																			.getPrimerApellido();
																	ls_segundoApellido     = lp_persona
																			.getSegundoApellido();

																	ls_primerNombre     = StringUtils.isValidString(
																		    ls_primerNombre
																		) ? ls_primerNombre : "";

																	ls_segundoNombre     = StringUtils.isValidString(
																		    ls_segundoNombre
																		) ? (" " + ls_segundoNombre) : "";

																	ls_primerApellido     = StringUtils.isValidString(
																		    ls_primerApellido
																		) ? (" " + ls_primerApellido) : "";

																	ls_segundoApellido     = StringUtils.isValidString(
																		    ls_segundoApellido
																		) ? (" " + ls_segundoApellido) : "";

																	ls_nombrePersona = ls_primerNombre + " "
																		+ ls_segundoNombre + " " + ls_primerApellido
																		+ " " + ls_segundoApellido;
																}

																ls_numeroDocumento = lp_persona.getNumeroDocumento();
															}
														}

														{
															ls_propietario     = lapc_iterador.getPropietario();
															ls_porcentaje      = lapc_iterador
																	.getPorcentajeParticipacion();
														}

														{
															String ls_interesadaRrr;
															ls_interesadaRrr = lapc_iterador.getIdInteresadaRrr();

															if(StringUtils.isValidString(ls_interesadaRrr))
															{
																ParteInteresada lpi_parametros;
																lpi_parametros = new ParteInteresada();

																lpi_parametros.setIdParteInteresada(ls_interesadaRrr);

																lpi_parametros = lpid_DAO.findById(lpi_parametros);

																if(lpi_parametros != null)
																	ls_descripcion = lpi_parametros.getDescripcion();
															}
														}

														if(StringUtils.isValidString(ls_porcentaje))
															ls_porcentaje = ls_porcentaje + " %" + "   ";

														ls_infoInterviniente = "  \\trowd\\cellx800\\cellx4000\\cellx6000\\cellx8000\\cellx9000\\cellx10000\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ ls_rol
															+ "\\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ StringUtils.getStringNotNull(ls_nombrePersona)
															+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ ls_tipoDocumento + "  #" + ls_numeroDocumento
															+ "\\cell\\intbl "
															+ StringUtils.getStringNotNull(ls_propietario)
															+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ StringUtils.getStringNotNull(ls_porcentaje)
															+ " \\cell\\intbl\\fs15{\\fonttbl {\\f0 sans-serif;}} "
															+ StringUtils.getStringNotNull(ls_descripcion)
															+ "\\cell\\row ";

														lsb_anotaciones.append(ls_infoInterviniente);
													}
												}

												lsb_anotaciones.append("}");
											}
										}

										{
											PredioSegregado             lps_predioSegregado;
											Collection<PredioSegregado> lcps_prediosSegregados;

											lps_predioSegregado = new PredioSegregado();

											lps_predioSegregado.setIdCirculo(ls_idCirculo);
											lps_predioSegregado.setIdMatricula(
											    NumericUtils.getLongWrapper(ll_idMatricula)
											);
											lps_predioSegregado.setIdAnotacion(
											    NumericUtils.getLongWrapper(li_idAnotacion)
											);

											lcps_prediosSegregados = lpsd_DAO.findByCirculoMatriculaAnotacion(
												    lps_predioSegregado
												);

											if(CollectionUtils.isValidCollection(lcps_prediosSegregados))
											{
												lsb_anotaciones.append("{\\pard \\par}");
												lsb_anotaciones.append("{\\b MATRICULAS SEGREGADAS: }");

												for(PredioSegregado lps_iterador : lcps_prediosSegregados)
												{
													if(lps_iterador != null)
													{
														lsb_anotaciones.append("{\\pard \\par}");
														lsb_anotaciones.append(lps_iterador.getIdCirculo1());
														lsb_anotaciones.append(" - ");
														lsb_anotaciones.append(lps_iterador.getIdMatricula1());
													}
												}
											}
										}
									}
								}

								ls_tag = "<TAG_ANOTACIONES>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_anotaciones.toString());
							}

							{
								Map<String, String> lmss_datos;
								Constantes          lc_usuarioFirma;
								String              ls_tagUsuarioFirma;
								int                 li_incrX = 0;
								int                 li_incrY = 0;

								lc_usuarioFirma              = new Constantes();
								ls_tagUsuarioFirma           = "<TAG_USUARIO_FIRMA_SUSPENSION>";

								lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

								lc_usuarioFirma     = DaoCreator.getConstantesDAO(adm_manager)
										                            .findByIdWithImage(lc_usuarioFirma);
								ls_plantilla        = getFirmaMasivaBusiness()
										                      .reemplazarUsuarioFirmaCargo(
										    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
										    "<TAG_CARGO_FIRMA_SUSPENSION>"
										);
								lmss_datos          = finalizarPlantilla(
									    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
									);
								ls_plantilla        = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
								lba_archivo         = new PDFGenerator().convertirRTFToPDF(
									    ls_plantilla.getBytes(), adm_manager
									);

								if(lba_archivo == null)
									throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

								if(ab_firmaMasiva)
								{
									byte[] lba_grafo;

									lba_grafo = null;

									if(lc_usuarioFirma != null)
									{
										lba_grafo     = lc_usuarioFirma.getImagenes();
										li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
										li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
									}

									lba_archivo = getFirmaMasivaBusiness()
											              .reemplazarBookmarsFirma(
											    lba_archivo, lba_grafo, li_incrX, li_incrY
											);
								}
							}
						}
					}
				}
			}
		}

		return lba_archivo;
	}
}
