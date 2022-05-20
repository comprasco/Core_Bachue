package com.bachue.snr.prosnr01.business.generarDocumentos;

import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.PropietarioDTO;
import co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;

import com.aspose.words.CellMerge;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Font;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.indicePropietarios.consultar.ClienteIndicePropietarios;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.DocumentoTipoIPEECommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RespuestaConsultaCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ConversionTextos;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.RespuestasConsultaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DepartamentoDAO;
import com.bachue.snr.prosnr01.dao.pgn.MunicipioDAO;
import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoOficinaDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoPersonaDAO;
import com.bachue.snr.prosnr01.dao.pgn.VeredaDAO;
import com.bachue.snr.prosnr01.dao.png.TipoEjeDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.registro.RespuestaConsulta;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.Pais;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoOficina;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.png.TipoEje;

import com.bachue.snr.prosnr04.model.npa.DatosLiquidacion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * Clase para el manejo del negocio de etapas 40, 41 AUT.
 *
 * @author Julian Vaca
 *
 */
public class GenerarDocumentosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GenerarDocumentosBusiness.class);

	/**
	 Metodo encargado de terminar una etapa y crear la siguiente según el motivo enviado.
	 * @param ath_parametros Argumento de tipo TurnoHistoria que contiene los argumentos necesarios para terminar y crear etapa.
	 * @param amt_motivoTramite Argumento de tipo MotivoTramite que contiene los argumentos necesarios para consultar el motivo tramite.
	 * @param as_usuario Argumento de tipo String que contiene el usuario que realiza la actualización.
	 * @param as_remoteIp Argumento de tipo String que contiene la ip desde donde se realiza la actualización.
	 * @throws B2BException
	 */
	public synchronized void enviarAEntrega(
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
				String ls_idSolicitud;

				ls_idSolicitud = ath_parametros.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Solicitud ls_solicitud;

					ls_solicitud     = new Solicitud(ls_idSolicitud);

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

					if(ls_solicitud != null)
					{
						{
							RespuestaConsulta lrc_respuestaConsulta;

							lrc_respuestaConsulta = new RespuestaConsulta();

							lrc_respuestaConsulta.setEstado(RespuestaConsultaCommon.GENERADO);
							lrc_respuestaConsulta.setIdSolicitud(ls_idSolicitud);

							DaoCreator.getRespuestasConsultaDAO(ldm_manager)
								          .updateCampoEstado(lrc_respuestaConsulta, RespuestaConsultaCommon.EXITOSO);
						}

						{
							String ls_medioANotificar;

							ls_medioANotificar = ls_solicitud.getIdAutorizacionNotificacion();

							if(StringUtils.isValidString(ls_medioANotificar))
							{
								long ll_idMotivo;

								if(ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_EN_ORIP;
								else if(ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_EMAIL;
								else if(
								    ls_medioANotificar.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
									    || ls_medioANotificar.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_CORRESPONDENCIA;
								else
									ll_idMotivo = 0L;

								if(ll_idMotivo > 0)
								{
									String ls_idTurno;

									ls_idTurno = ath_parametros.getIdTurno();

									if(StringUtils.isValidString(ls_idTurno))
									{
										TurnoHistoria lth_turnoHistoria;

										lth_turnoHistoria = new TurnoHistoria();

										lth_turnoHistoria.setIdTurno(ls_idTurno);
										lth_turnoHistoria.setIdEtapa(
										    NumericUtils.getBigDecimal(
										        EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS
										    )
										);
										lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

										lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
												                          .findByIdTurnoEtapa(lth_turnoHistoria);

										if(lth_turnoHistoria != null)
										{
											amt_motivoTramite.setIdMotivo(ll_idMotivo);
											ath_parametros.setIdTurnoHistoria(lth_turnoHistoria.getIdTurnoHistoria());

											terminarTurnoHistoriaYCrearEtapa(
											    ath_parametros, ldm_manager, amt_motivoTramite, as_usuario, as_remoteIp,
											    EstadoCommon.TERMINADA
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
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("enviarAEntrega", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método de transacciones con la base de datos con el fin de encontrar etapas
	 * 40 y 41 con estado actividad AUT de Turno Historia
	 *
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarDocumentos(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_parametros;
		DAOManager                ldm_manager;

		lcth_parametros     = null;
		ldm_manager         = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = lcd_constantesDAO.findById(ConstanteCommon.JOB_GENERAR_DOCUMENTOS_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_limiteIntentos;

				ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_GENERAR_DOCUMENTOS_LIMITE_INTENTOS
					);

				lcth_parametros = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
						                        .findDataJobGenerarDocumentos(false, ll_limiteIntentos);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("GenerarDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_parametros))
			generarDocumentos(lcth_parametros, as_remoteIp);
	}

	/**
	 * Método de transacciones con la base de datos para procesar casos encontrados
	 * en etapa 40 y 41 y estado actividad AUT
	 *
	 * @param acth_parametros
	 *            Objeto de tipo Collection<TurnoHistoria> que contiene casos a aser
	 *            procesados
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarDocumentos(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_GENERAR_DOCUMENTOS_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManager();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("generarDocumentos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

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

				clh_LOGGER.error("GenerarDocumentos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_processing.close();
			}
		}

		try
		{
			if(!lb_alreadyProcessing && StringUtils.isValidString(ls_userId))
			{
				DAOManager ldm_manager;
				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						ldm_manager.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									generarDocumentos(lth_iterador, lbpd_bitacora, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("GenerarDocumentos", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("GenerarDocumentos", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("GenerarDocumentos", lb2be_e);

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

					clh_LOGGER.error("GenerarDocumentos", lb2be_e);

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
	 * Método de transacciones con la base de datos para procesar de manera
	 * individual los casos encontrados
	 *
	 * @param ath_parametros
	 *            Objeto de tipo TurnoHistoria que contiene la etapa actual del caso
	 *            a ser procesado
	 * @param abpd_DAO
	 *            Objeto de tipo BitacoraProcesoDAO que va a registrar la bitacora
	 *            del caso a ser procesado
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized void generarDocumentos(
	    TurnoHistoria ath_parametros, BitacoraProcesoDAO abpd_DAO, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros != null)
			{
				long ll_idEtapa;

				ll_idEtapa = NumericUtils.getLong(ath_parametros.getIdEtapa());

				if(!StringUtils.isValidString(as_userId))
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = as_userId;

					throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_ENCONTRADO, loa_arg);
				}

				if((ll_idEtapa > 0L) && StringUtils.isValidString(as_userId))
				{
					String ls_idSolicitud;

					ls_idSolicitud = ath_parametros.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
						{
							String ls_idAutorizaNotificacion;

							ls_idAutorizaNotificacion = ls_solicitud.getIdAutorizacionNotificacion();

							if(StringUtils.isValidString(ls_idAutorizaNotificacion))
							{
								boolean lb_entidadExenta;
								boolean lb_etapa40;
								boolean lb_etapa41;
								long    ll_idMotivo;

								lb_entidadExenta     = BooleanUtils.getBooleanValue(ls_solicitud.getEntidadExenta());
								lb_etapa40           = ll_idEtapa == EtapaCommon.ID_ETAPA_40_GENERACION_CONSULTAS_INMEDIATOS;
								lb_etapa41           = ll_idEtapa == EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS;

								ll_idMotivo = 0L;

								if((!lb_entidadExenta && lb_etapa40) || (lb_etapa41))
								{
									boolean lb_correoElectronico;
									boolean lb_direccionResidencia;
									boolean lb_direccionCorrespondencia;
									boolean lb_orip;

									lb_correoElectronico            = ls_idAutorizaNotificacion.equalsIgnoreCase(
										    MedioNotificarCommon.CORREO_ELECTRONICO
										);
									lb_direccionResidencia          = ls_idAutorizaNotificacion.equalsIgnoreCase(
										    MedioNotificarCommon.DIRECCION_RESIDENCIA
										);
									lb_direccionCorrespondencia     = ls_idAutorizaNotificacion.equalsIgnoreCase(
										    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										);
									lb_orip                         = ls_idAutorizaNotificacion.equalsIgnoreCase(
										    MedioNotificarCommon.ORIP
										);

									generarDocumentosRespuesta(
									    ath_parametros, ls_solicitud, as_userId, as_remoteIp, ldm_manager
									);

									if(lb_etapa40)
									{
										if(lb_correoElectronico)
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_EMAIL;
										else if(lb_direccionResidencia)
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_CORRESPONDENCIA;
										else if(lb_direccionCorrespondencia)
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_CORRESPONDENCIA;
										else if(lb_orip)
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_CONSULTAS_EN_LA_ORIP;
										else
											ll_idMotivo = MotivoTramiteCommon.DEFAULT;
									}
									else if(lb_etapa41)
									{
										if(lb_correoElectronico)
											ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_EMAIL;
										else if(lb_direccionResidencia)
											ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_CORRESPONDENCIA;
										else if(lb_direccionCorrespondencia)
											ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_POR_CORRESPONDENCIA;
										else if(lb_orip)
											ll_idMotivo = MotivoTramiteCommon.GENERAR_CONSULTA_PARA_ENTREGA_EN_ORIP;
										else
											ll_idMotivo = MotivoTramiteCommon.DEFAULT;
									}
									else
										ll_idMotivo = MotivoTramiteCommon.DEFAULT;

									if(ll_idMotivo > 0L)
										terminarTurnoHistoriaYCrearEtapa(
										    ath_parametros, ldm_manager, new MotivoTramite(ll_idEtapa, ll_idMotivo),
										    as_userId, as_remoteIp, EstadoCommon.TERMINADA
										);
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[1];
										loa_arg[0]     = ath_parametros.getIdTurno();

										throw new B2BException(ErrorKeys.ERROR_MOTIVO_TRAMITE_CASO, loa_arg);
									}
								}

								else if(lb_etapa40)
									terminarTurnoHistoriaYCrearEtapa(
									    ath_parametros, ldm_manager,
									    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.CONSULTA_PARA_ENTIDAD_EXENTA),
									    as_userId, as_remoteIp, EstadoCommon.TERMINADA
									);
							}
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_idSolicitud;

								throw new B2BException(ErrorKeys.ERROR_MEDIO_NOTIFICAR_SOLICITUD, loa_arg);
							}
						}
						else
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = ls_idSolicitud;

							throw new B2BException(ErrorKeys.ERROR_SOLICITUD_ID, loa_arg);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NO_ID_SOLICITUD);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_USUARIO_Y_O_ETAPA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.GENERACION_DOCUMENTOS, lb2be_e.getMessage(), as_userId, as_remoteIp,
			    (ath_parametros != null) ? StringUtils.getString(ath_parametros.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método de transacciones con la base de datos para procesar el caso a un nivel
	 * especifico a travez de un procedimiento de BD
	 *
	 * @param ath_turnoHistoria
	 *            Objeto de tipo TurnoHistoria que contiene el id turno historia de
	 *            la etapa actual del caso
	 * @param as_solicitud
	 *            Objeto de tipo Solicitud que contiene el id solicitud del caso a
	 *            procesar
	 * @param as_userId
	 *            Objeto de tipo String que contiene el usuario que realiza la
	 *            accion en el sistema
	 * @param as_remoteIp
	 *            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @throws B2BException
	 */
	public synchronized byte[] generarDocumentosRespuesta(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_archivo;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lba_archivo     = null;

		try
		{
			if((ath_turnoHistoria != null) && (as_solicitud != null))
			{
				CriteriosDeBusqueda lcdb_criteriosDeBusqueda;

				lcdb_criteriosDeBusqueda = new CriteriosDeBusqueda();

				lcdb_criteriosDeBusqueda.setIdSolicitud(as_solicitud.getIdSolicitud());

				getCriteriosConsultaBusiness()
					    .validarCantidadDetalleCriterioBusqueda(lcdb_criteriosDeBusqueda, ldm_manager);

				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = new TurnoHistoria();

					lth_turnoHistoria.setIdTurno(ath_turnoHistoria.getIdTurno());
					lth_turnoHistoria.setIdEtapa(
					    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_41_ANALISTA_SOLICITUD_CONSULTAS_EXENTAS)
					);
					lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurnoEtapa(
						    lth_turnoHistoria
						);

					if(lth_turnoHistoria != null)
						lba_archivo = generarDocumentosRespuesta(
							    lth_turnoHistoria, as_solicitud, as_userId, as_remoteIp, ldm_manager
							);

					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_solicitud);

						if(ls_solicitud != null)
						{
							String ls_exenta;

							ls_exenta = ls_solicitud.getEntidadExenta();

							if(StringUtils.isValidString(ls_exenta) && BooleanUtils.getBooleanValue(ls_exenta))
								getRegistroBusiness()
									    .generarComunicadoRespuestaSolicitudExenta(
									    ldm_manager, ls_solicitud, lth_turnoHistoria, true, as_userId, as_remoteIp
									);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosRespuesta", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_archivo;
	}

	/**
	 * Método de transacciones con la base de datos para procesar el caso a un nivel
	 * especifico a travez de un procedimiento de BD.
	 *
	 * @param ath_th            Objeto de tipo TurnoHistoria que contiene el id turno historia de
	 *            la etapa actual del caso
	 * @param as_solicitud            Objeto de tipo Solicitud que contiene el id solicitud del caso a
	 *            procesar
	 * @param as_userId            Objeto de tipo String que contiene el usuario que realiza la
	 *            accion en el sistema
	 * @param as_remoteIp            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @param adm_manager            Objeto de tipo DAOManager que contiene la transaccion activa con
	 *            la Base de Datos
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarDocumentosRespuesta(
	    TurnoHistoria ath_th, Solicitud as_solicitud, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[] lba_pdf;

		lba_pdf = null;

		try
		{
			if(as_solicitud != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(as_solicitud);

				if(ls_solicitud != null)
				{
					String ls_idProceso;
					String ls_idSubproceso;

					ls_idProceso        = ls_solicitud.getIdProceso();
					ls_idSubproceso     = ls_solicitud.getIdSubproceso();

					if(StringUtils.isValidString(ls_idProceso) && StringUtils.isValidString(ls_idSubproceso))
					{
						int                   li_retorno;
						Map<String, Object>   lmso_data;
						ProcedimientosDAO     lpd_DAO;
						RespuestasConsultaDAO lrcd_DAO;
						String                ls_idSolicitud;
						String                ls_codigoVerificacion;
						DatosLiquidacion      ldl_datosLiquidacion;

						li_retorno                = -1;
						lmso_data                 = null;
						lpd_DAO                   = DaoCreator.getProcedimientosDAO(adm_manager);
						lrcd_DAO                  = DaoCreator.getRespuestasConsultaDAO(adm_manager);
						ls_idSolicitud            = ls_solicitud.getIdSolicitud();
						ls_codigoVerificacion     = null;
						ldl_datosLiquidacion      = DaoCreator.getDatosLiquidacionDAO(adm_manager)
								                                  .findByIdSolicitud(ls_idSolicitud);

						if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
							    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
							    && (ldl_datosLiquidacion != null)
						)
						{
							Constantes lc_constanteEndPoint;

							lc_constanteEndPoint = DaoCreator.getConstantesDAO(adm_manager)
									                             .findById(
									    ConstanteCommon.INDICE_PROPIETARIOS_ENDPOINT
									);

							if(lc_constanteEndPoint != null)
							{
								String ls_endPoint;

								ls_endPoint = lc_constanteEndPoint.getCaracter();

								if(StringUtils.isValidString(ls_endPoint))
								{
									Collection<Map<String, String>> lcmss_cllDatosConsumoIndividuales;

									lcmss_cllDatosConsumoIndividuales = obtenerDatosConsumoIndicePropietarios(
										    ls_idSolicitud, adm_manager
										);

									if(CollectionUtils.isValidCollection(lcmss_cllDatosConsumoIndividuales))
									{
										RespuestasConsultaDAO lrc_DAO;
										PersonaDAO            lp_DAO;

										lrc_DAO     = DaoCreator.getRespuestasConsultaDAO(adm_manager);
										lp_DAO      = DaoCreator.getPersonaDAO(adm_manager);

										for(Map<String, String> lmss_tmp : lcmss_cllDatosConsumoIndividuales)
										{
											if(CollectionUtils.isValidCollection(lmss_tmp))
											{
												TipoSalidaIndicePropietarios ltsip_salidaIndiceProp;

												ltsip_salidaIndiceProp = ClienteIndicePropietarios.consultar(
													    lmss_tmp, ls_endPoint
													);

												if(ltsip_salidaIndiceProp != null)
												{
													InmuebleDTO[] lidto_inmuebles;

													lidto_inmuebles = ltsip_salidaIndiceProp.getDatosInmueble();

													if(CollectionUtils.isValidCollection(lidto_inmuebles))
													{
														RespuestaConsulta lrc_respuestaConsulta;

														lrc_respuestaConsulta = new RespuestaConsulta();

														lrc_respuestaConsulta.setIdSolicitud(ls_idSolicitud);
														lrc_respuestaConsulta.setIdTipoCriterioBusqueda(
														    TipoCriterioBusquedaCommon.IDENTIFICACION
														);
														lrc_respuestaConsulta.setConsecutivoConsulta(
														    NumericUtils.getLongWrapper(
														        ltsip_salidaIndiceProp.getConsecutivoConsulta()
														    )
														);
														lrc_respuestaConsulta.setConsecutivoConsultaDetalle(
														    NumericUtils.getLongWrapper(
														        ltsip_salidaIndiceProp.getConsecutivoConsultaDetalle()
														    )
														);
														lrc_respuestaConsulta.setIdProcesoConsulta(
														    ltsip_salidaIndiceProp.getIdProcesoConsulta()
														);
														lrc_respuestaConsulta.setIdUsuarioCreacion(as_userId);
														lrc_respuestaConsulta.setIpCreacion(as_remoteIp);

														for(InmuebleDTO ltmdto_tmp : lidto_inmuebles)
														{
															if(ltmdto_tmp != null)
															{
																PropietarioDTO[] lppdto_propietarios;

																lppdto_propietarios = ltmdto_tmp.getPropietarios();

																if(
																    CollectionUtils.isValidCollection(
																	        lppdto_propietarios
																	    )
																)
																{
																	lrc_respuestaConsulta.setIdCirculo(
																	    ltmdto_tmp.getCodCirculoRegistral()
																	);
																	lrc_respuestaConsulta.setIdDepartamento(
																	    ltmdto_tmp.getCodDepartamento()
																	);
																	lrc_respuestaConsulta.setIdMunicipio(
																	    ltmdto_tmp.getCodMunicipio()
																	);
																	lrc_respuestaConsulta.setDireccion(
																	    ltmdto_tmp.getDireccionPredio()
																	);
																	lrc_respuestaConsulta.setIdMatricula(
																	    NumericUtils.getLongWrapper(
																	        ltmdto_tmp.getNumMatriculaInmobiliaria()
																	    )
																	);
																	lrc_respuestaConsulta.setNumeroPredial(
																	    ltmdto_tmp.getNumPredial()
																	);
																	lrc_respuestaConsulta.setNupre(
																	    ltmdto_tmp.getNUPRE()
																	);
																	lrc_respuestaConsulta.setNombreCirculo(
																	    ltmdto_tmp.getNomCirculoRegistral()
																	);
																	lrc_respuestaConsulta.setNombreDepartamentoCirculo(
																	    ltmdto_tmp.getNomDepartamento()
																	);
																	lrc_respuestaConsulta.setNombreMunicipioCirculo(
																	    ltmdto_tmp.getNomMunicipio()
																	);

																	lrc_respuestaConsulta.setRespuesta(
																	    EstadoCommon.EXITOSO
																	);

																	lrc_respuestaConsulta.setEstado(
																	    EstadoCommon.PENDIENTE
																	);

																	lrc_respuestaConsulta.setIdTipoCriterioBusqueda(
																	    TipoCriterioBusquedaCommon.IDENTIFICACION
																	);

																	for(PropietarioDTO lpdto_tmp : lppdto_propietarios)
																	{
																		if(lpdto_tmp != null)
																		{
																			Persona       lp_persona;
																			String        ls_idPersona;
																			String        ls_filtroBusquedaPersona;
																			StringBuilder lsb_criterioSeleccion;

																			lp_persona                   = new Persona(
																				    lpdto_tmp
																				);
																			ls_idPersona                 = null;
																			ls_filtroBusquedaPersona     = IdentificadoresCommon.TIPO_NUM_DOC;
																			lsb_criterioSeleccion        = new StringBuilder(
																				    "IDENTIFICACION: "
																				);

																			{
																				String ls_tipoDocumento;

																				ls_tipoDocumento = lp_persona
																						.getIdDocumentoTipo();

																				if(
																				    ls_tipoDocumento.equalsIgnoreCase(
																					        IdentificadoresCommon.NIT
																					    )
																					    && StringUtils.isValidString(
																					        lp_persona.getRazonSocial()
																					    )
																				)
																				{
																					lsb_criterioSeleccion.append(
																					    IdentificadoresCommon.NIT
																					    + IdentificadoresCommon.SIMBOLO_COMA_TXT
																					    + lp_persona.getNumeroDocumento()
																					    + IdentificadoresCommon.SIMBOLO_COMA_TXT
																					    + lp_persona.getRazonSocial()
																					);

																					ls_filtroBusquedaPersona = IdentificadoresCommon.TIPO_NUM_DOC_NIT;
																				}
																				else if(
																				    !(ls_tipoDocumento.equalsIgnoreCase(
																					        IdentificadoresCommon.NIT
																					    ))
																					    && (StringUtils.isValidString(
																					        lp_persona.getPrimerNombre()
																					    )
																					    || StringUtils.isValidString(
																					        lp_persona.getSegundoNombre()
																					    )
																					    || StringUtils.isValidString(
																					        lp_persona.getPrimerApellido()
																					    )
																					    || StringUtils.isValidString(
																					        lp_persona
																					        .getSegundoApellido()
																					    ))
																				)
																				{
																					String ls_idDocumentoTipo;
																					String ls_queryLanguage;

																					ls_idDocumentoTipo     = lp_persona
																							.getIdDocumentoTipo();
																					ls_queryLanguage       = null;

																					if(
																					    StringUtils.isValidString(
																						        ls_idDocumentoTipo
																						    )
																					)
																					{
																						switch(ls_idDocumentoTipo)
																						{
																							case DocumentoTipoIPEECommon.CC_BACHUE:
																								ls_queryLanguage = "Cedula_Ciudadania";

																								break;

																							case DocumentoTipoIPEECommon.CE_BACHUE:
																								ls_queryLanguage = "Cedula_Extranjeria";

																								break;

																							case DocumentoTipoIPEECommon.PA:
																								ls_queryLanguage = "Pasaporte";

																								break;

																							case DocumentoTipoIPEECommon.TI:
																								ls_queryLanguage = "Tarjeta_Identidad";

																								break;

																							case DocumentoTipoIPEECommon.NUIP:
																								ls_queryLanguage = "NUIP";

																								break;

																							default:
																								break;
																						}
																					}

																					lsb_criterioSeleccion.append(
																					    ls_queryLanguage
																					    + IdentificadoresCommon.SIMBOLO_COMA_TXT
																					    + lp_persona.getNumeroDocumento()
																					);

																					if(
																					    StringUtils.isValidString(
																						        lp_persona
																						        .getPrimerNombre()
																						    )
																					)
																						lsb_criterioSeleccion.append(
																						    IdentificadoresCommon.SIMBOLO_COMA_TXT
																						    + lp_persona.getPrimerNombre()
																						);

																					if(
																					    StringUtils.isValidString(
																						        lp_persona
																						        .getSegundoNombre()
																						    )
																					)
																						lsb_criterioSeleccion.append(
																						    IdentificadoresCommon.SIMBOLO_COMA_TXT
																						    + lp_persona
																							    .getSegundoNombre()
																						);

																					if(
																					    StringUtils.isValidString(
																						        lp_persona
																						        .getPrimerApellido()
																						    )
																					)
																						lsb_criterioSeleccion.append(
																						    IdentificadoresCommon.SIMBOLO_COMA_TXT
																						    + lp_persona
																							    .getPrimerApellido()
																						);

																					if(
																					    StringUtils.isValidString(
																						        lp_persona
																						        .getSegundoApellido()
																						    )
																					)
																						lsb_criterioSeleccion.append(
																						    IdentificadoresCommon.SIMBOLO_COMA_TXT
																						    + lp_persona
																							    .getSegundoApellido()
																						);

																					ls_filtroBusquedaPersona = IdentificadoresCommon.TIPO_NUM_DOC_NOMBRES;
																				}
																			}

																			lp_persona = lp_DAO.findByPersonData(
																				    lp_persona, ls_filtroBusquedaPersona
																				);

																			if(lp_persona != null)
																				ls_idPersona = lp_persona.getIdPersona();
																			else
																			{
																				lp_persona = new Persona(lpdto_tmp);
																				lp_persona.setIdPais(
																				    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
																				);
																				lp_persona.setIdUsuarioCreacion(
																				    as_userId
																				);
																				lp_persona.setIpCreacion(as_userId);

																				lp_persona     = lp_DAO.insertOrUpdate(
																					    lp_persona, true
																					);

																				ls_idPersona = lp_persona.getIdPersona();
																			}

																			{
																				lrc_respuestaConsulta.setIdPersona(
																				    ls_idPersona
																				);
																				lrc_respuestaConsulta
																					.setCriterioSeleccion(
																					    lsb_criterioSeleccion.toString()
																					);

																				lrc_DAO.insert(lrc_respuestaConsulta);
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

										li_retorno = NumericUtils.DEFAULT_INT_VALUE;
									}
								}
							}
						}
						else
							li_retorno = lpd_DAO.procConsultas(ls_idSolicitud, as_userId, as_remoteIp);

						if(li_retorno == NumericUtils.DEFAULT_INT_VALUE)
						{
							String  ls_plantilla;
							boolean lb_existe;
							boolean lb_firma;

							ls_plantilla     = null;
							lb_firma         = false;

							lb_existe = lrcd_DAO.conteoExitosoById(ls_idSolicitud) > 0;

							if(lb_existe)
								ls_plantilla = ConstanteCommon.PLANTILLA_RESULTADO_CONSULTA;
							else
							{
								switch(ls_idSubproceso)
								{
									case ProcesoCommon.ID_SUBPROCESO_5:
									case ProcesoCommon.ID_SUBPROCESO_6:
									case ProcesoCommon.ID_SUBPROCESO_7:
										ls_plantilla = ConstanteCommon.PLANTILLA_CERTIFICADO_NO_HALLAZGO_CONSULTA;
										lb_firma = true;

										break;

									default:
										ls_plantilla = ConstanteCommon.PLANTILLA_RESULTADO_NEGATIVO;

										break;
								}
							}

							if(StringUtils.isValidString(ls_plantilla))
							{
								lmso_data = generarDocumentosRespuesta(
									    ls_solicitud, ath_th, ls_plantilla, as_userId, as_remoteIp, adm_manager,
									    lb_firma
									);

								if(CollectionUtils.isValidCollection(lmso_data))
								{
									Object lo_documento;
									Object lo_codigo;

									lo_documento     = lmso_data.get(IdentificadoresCommon.DOCUMENTO);
									lo_codigo        = lmso_data.get(IdentificadoresCommon.CODIGO_VERIFICACION);

									if(lo_documento != null)
										lba_pdf = (byte[])lo_documento;

									if(lo_codigo != null)
										ls_codigoVerificacion = (String)lo_codigo;
								}
							}
							else
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
						}

						if((lba_pdf != null) && (ath_th != null))
						{
							Turno lt_turno;

							lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ath_th.getIdTurno());

							if(lt_turno != null)
							{
								ImagenesDAO         lid_DAO;
								DocumentosSalidaDAO ldsd_DAO;
								long                ll_idDocumentoSalida;
								long                li_secuencia;
								boolean             lb_existeImagen;
								Imagenes            li_imagenes;
								DocumentosSalida    lds_documentosSalida;

								lid_DAO                  = DaoCreator.getImagenesDAO(adm_manager);
								ldsd_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
								li_imagenes              = new Imagenes();
								lds_documentosSalida     = new DocumentosSalida();
								ll_idDocumentoSalida     = 0;

								lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
								lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
								lds_documentosSalida.setTipo(TipoArchivoCommon.CERTIFICADO);
								lds_documentosSalida.setEstado(EstadoCommon.A);
								lds_documentosSalida = ldsd_DAO.findDocumentByTurnoTipoEstado(lds_documentosSalida);

								if(lds_documentosSalida != null)
									ll_idDocumentoSalida = lds_documentosSalida.getIdDocumentoSalida();
								else
									lds_documentosSalida = new DocumentosSalida();

								lb_existeImagen = ll_idDocumentoSalida > 0;

								if(lb_existeImagen)
								{
									lds_documentosSalida.setIdDocumentoSalida(ll_idDocumentoSalida);

									lds_documentosSalida = ldsd_DAO.findById(lds_documentosSalida);

									if(lds_documentosSalida != null)
										li_imagenes.setIdImagen(
										    NumericUtils.getInt(lds_documentosSalida.getIdImagen())
										);
								}

								li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagenes.setIdTurno(lt_turno.getIdTurno());

								if(lb_existeImagen)
								{
									li_imagenes.setIdUsuarioModificacion(as_userId);
									li_imagenes.setIpModificacion(as_remoteIp);
								}
								else
								{
									li_imagenes.setIdUsuarioCreacion(as_userId);
									li_imagenes.setIpCreacion(as_remoteIp);
								}

								li_imagenes.setImagenBlob(lba_pdf);
								li_imagenes.setCodigoVerificacion(ls_codigoVerificacion);

								li_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen, true);

								if(!lb_existeImagen)
								{
									Integer li_idTurnoHistoriaI;
									int     li_idEtapa;

									li_idEtapa              = NumericUtils.getInt(ath_th.getIdEtapa());
									li_idTurnoHistoriaI     = NumericUtils.getInteger(ath_th.getIdTurnoHistoria());

									if(li_secuencia <= 0)
										throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

									lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(li_secuencia));
									lds_documentosSalida.setTipo(TipoArchivoCommon.CERTIFICADO);
									lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

									if(
									    (StringUtils.isValidString(ls_idProceso)
										    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2))
										    && (li_idEtapa == EtapaCommon.ID_ETAPA_40_GENERACION_CONSULTAS_INMEDIATOS)
									)
									{
										lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSULTAS);
										lds_documentosSalida.setDocumentoFinal(EstadoCommon.SI);
									}
									else
									{
										lds_documentosSalida.setIdTipoDocumental(
										    TipoDocumentalCommon.CERTIFICADO_TRADICION_LIBERTAD
										);
										lds_documentosSalida.setDocumentoFinal(EstadoCommon.NO);
									}

									lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
									lds_documentosSalida.setIdTurnoHistoria(li_idTurnoHistoriaI);
									lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());

									{
										Collection<DocumentosSalida> lcds_documentosExistentes;

										lcds_documentosExistentes = ldsd_DAO.findByIdTurnoHistoriaTipo(
											    lds_documentosSalida
											);

										if(!CollectionUtils.isValidCollection(lcds_documentosExistentes))
										{
											lds_documentosSalida.setIdUsuarioCreacion(as_userId);
											lds_documentosSalida.setIpCreacion(as_remoteIp);

											ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);
										}
										else
										{
											for(DocumentosSalida lds_docs : lcds_documentosExistentes)
											{
												if(lds_docs != null)
												{
													lds_documentosSalida.setIdDocumentoSalida(
													    lds_docs.getIdDocumentoSalida()
													);
													lds_documentosSalida.setIdUsuarioModificacion(as_userId);
													lds_documentosSalida.setIpModificacion(as_remoteIp);

													lds_documentosSalida.setIdEcm(null);

													ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
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
			clh_LOGGER.error("generarDocumentosRespuesta", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumentosRespuesta", le_e.getMessage());

			throw new B2BException(le_e.getMessage());
		}

		return lba_pdf;
	}

	/**
	 * Obtener datos consumo indice propietarios.
	 *
	 * @param ls_idSolicitud de ls id solicitud
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<Map<String, String>> obtenerDatosConsumoIndicePropietarios(
	    String ls_idSolicitud, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<Map<String, String>> lcmss_cllDatosConsumo;

		lcmss_cllDatosConsumo = new LinkedList<Map<String, String>>();

		try
		{
			if(StringUtils.isValidString(ls_idSolicitud))
			{
				String ls_caracterClientProfileEntidadIndicePropietarios;
				String ls_caracterClientProfileAuditoriaEntidadIndicePropietarios;
				String ls_caracterClientProfileAuditoriaUsuarioIndicePropietarios;

				{
					String     ls_idConstante;
					Constantes lc_clientProfile;

					{
						ls_idConstante       = ConstanteCommon.CLIENT_PROFILE_ENTIDAD_INDICE_PROPIETARIOS;
						lc_clientProfile     = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);

						if(lc_clientProfile != null)
							ls_caracterClientProfileEntidadIndicePropietarios = lc_clientProfile.getCaracter();
						else
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}
					}

					{
						ls_idConstante       = ConstanteCommon.CLIENT_PROFILE_AUDITORIA_ENTIDAD_INDICE_PROPIETARIOS;
						lc_clientProfile     = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);

						if(lc_clientProfile != null)
							ls_caracterClientProfileAuditoriaEntidadIndicePropietarios = lc_clientProfile.getCaracter();
						else
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}
					}

					{
						ls_idConstante       = ConstanteCommon.CLIENT_PROFILE_AUDITORIA_USUARIO_INDICE_PROPIETARIOS;
						lc_clientProfile     = DaoCreator.getConstantesDAO(adm_manager).findById(ls_idConstante);

						if(lc_clientProfile != null)
							ls_caracterClientProfileAuditoriaUsuarioIndicePropietarios = lc_clientProfile.getCaracter();
						else
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}
					}

					{
						DetalleCriterioBusquedaDAO ldcb_DAO;
						long                       li_cantidadConsecutivosConsultaDetalle;
						long                       li_consecutivoConsultaDetalle;

						ldcb_DAO                                   = DaoCreator.getDetalleCriterioBusquedaDAO(
							    adm_manager
							);
						li_cantidadConsecutivosConsultaDetalle     = ldcb_DAO.findMaxConsecutivoConsultaBySolicitud(
							    ls_idSolicitud, TipoCriterioBusquedaCommon.IDENTIFICACION
							);
						li_consecutivoConsultaDetalle              = 1;

						while(li_consecutivoConsultaDetalle <= li_cantidadConsecutivosConsultaDetalle)
						{
							Map<String, String> lmss_datosConsumo;

							lmss_datosConsumo = new HashMap<String, String>();

							{
								Collection<CriteriosDeBusqueda> lccb_criteriosBusqueda;

								lccb_criteriosBusqueda = ldcb_DAO
										.findByIdSolicitudTipoCriterioBusquedaConsecutivoConsulta(
										    ls_idSolicitud, TipoCriterioBusquedaCommon.IDENTIFICACION,
										    li_consecutivoConsultaDetalle
										);

								if(CollectionUtils.isValidCollection(lccb_criteriosBusqueda))
								{
									String ls_tipoPersona;

									ls_tipoPersona = null;

									for(CriteriosDeBusqueda lcb_criterioBusqueda : lccb_criteriosBusqueda)
									{
										if(lcb_criterioBusqueda != null)
										{
											String ls_idCampoCriterioBusqueda;

											ls_idCampoCriterioBusqueda = lcb_criterioBusqueda.getIdCampoCriterioBusqueda();

											if(
											    StringUtils.isValidString(ls_idCampoCriterioBusqueda)
												    && ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												        IdentificadoresCommon.NUM8
												    )
											)
												ls_tipoPersona = lcb_criterioBusqueda.getValorCampo();
										}
									}

									if(StringUtils.isValidString(ls_tipoPersona))
									{
										long   ll_consecutivoConsulta;
										String ls_idProcesoConsulta;

										ll_consecutivoConsulta     = 0;
										ls_idProcesoConsulta       = null;

										for(CriteriosDeBusqueda lcb_criterioBusqueda : lccb_criteriosBusqueda)
										{
											if(lcb_criterioBusqueda != null)
											{
												String ls_idCampoCriterioBusqueda;

												ls_idCampoCriterioBusqueda     = lcb_criterioBusqueda
														.getIdCampoCriterioBusqueda();
												ll_consecutivoConsulta         = lcb_criterioBusqueda.getConsecutivo();
												ls_idProcesoConsulta           = lcb_criterioBusqueda
														.getIdProcesoConsulta();

												if(StringUtils.isValidString(ls_idCampoCriterioBusqueda))
												{
													if(
													    ls_tipoPersona.equalsIgnoreCase(IdentificadoresCommon.NATURAL)
														    || ls_tipoPersona.equalsIgnoreCase(EstadoCommon.N)
													)
													{
														switch(ls_idCampoCriterioBusqueda)
														{
															case IdentificadoresCommon.NUM1:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.TIPO_DOCUMENTO_PERSONA,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM2:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.NUM_DOCUMENTO_PERSONA,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM3:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.PRIMER_NOMBRE,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM4:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.SEGUNDO_NOMBRE,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM5:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.PRIMER_APELLIDO,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM6:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.SEGUNDO_APELLIDO,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															default:
																break;
														}
													}
													else if(
													    ls_tipoPersona.equalsIgnoreCase(IdentificadoresCommon.JURIDICO)
														    || ls_tipoPersona.equalsIgnoreCase(EstadoCommon.J)
													)
													{
														switch(ls_idCampoCriterioBusqueda)
														{
															case IdentificadoresCommon.NUM2:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.NUM_NIT,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															case IdentificadoresCommon.NUM7:
																lmss_datosConsumo.put(
																    IdentificadoresCommon.RAZON_SOCIAL,
																    lcb_criterioBusqueda.getValorCampo()
																);

																break;

															default:
																break;
														}
													}
												}
											}
										}

										{
											lmss_datosConsumo.put(
											    IdentificadoresCommon.CONSECUTIVO_CONSULTA,
											    StringUtils.getString(ll_consecutivoConsulta)
											);

											lmss_datosConsumo.put(
											    IdentificadoresCommon.CONSECUTIVO_CONSULTA_DETALLE,
											    StringUtils.getString(li_consecutivoConsultaDetalle)
											);

											lmss_datosConsumo.put(
											    IdentificadoresCommon.ID_PROCESO_CONSULTA,
											    StringUtils.getString(ls_idProcesoConsulta)
											);

											lmss_datosConsumo.put(
											    IdentificadoresCommon.ENTIDAD,
											    ls_caracterClientProfileEntidadIndicePropietarios
											);
											lmss_datosConsumo.put(
											    IdentificadoresCommon.ID_AUDITORIA_ENTIDAD,
											    ls_caracterClientProfileAuditoriaEntidadIndicePropietarios
											);
											lmss_datosConsumo.put(
											    IdentificadoresCommon.ID_AUDITORIA_USUARIO,
											    ls_caracterClientProfileAuditoriaUsuarioIndicePropietarios
											);

											lcmss_cllDatosConsumo.add(lmss_datosConsumo);
										}
									}
								}
							}
							li_consecutivoConsultaDetalle++;
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerDatosConsumoIndicePropietarios", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerDatosConsumoIndicePropietarios", le_e.getMessage());

			throw new B2BException(le_e.getMessage());
		}

		return lcmss_cllDatosConsumo;
	}

	/**
	 * Método de transacciones con la base de datos para generar un documento pdf
	 * respuesta.
	 *
	 * @param as_solicitud de as solicitud
	 * @param ath_th de ath th
	 * @param as_plantilla de as plantilla
	 * @param as_userId            Objeto de tipo String que contiene el usuario que realiza la
	 *            accion en el sistema
	 * @param as_remoteIp            Objeto de tipo String que contienen la ip desde donde se genera la
	 *            petición
	 * @param adm_manager            Objeto de tipo DAOManager que contiene la transaccion activa con
	 *            la Base de Datos
	 * @param ab_firma de ab firma
	 * @return Objeto de tipo byte[] que contiene el documento resultado del proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Map<String, Object> generarDocumentosRespuesta(
	    Solicitud as_solicitud, TurnoHistoria ath_th, String as_plantilla, String as_userId, String as_remoteIp,
	    DAOManager adm_manager, boolean ab_firma
	)
	    throws B2BException
	{
		Map<String, Object> lmso_return;
		byte[]              lba_certificado;

		lmso_return         = new HashMap<String, Object>();
		lba_certificado     = null;

		try
		{
			if(as_solicitud != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = as_solicitud.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(as_plantilla))
				{
					ConstantesDAO              lcd_DAO;
					TurnoDAO                   ltd_DAO;
					CirculoRegistralDao        lcrd_DAO;
					MunicipioDAO               lmd_DAO;
					LiquidacionDAO             lld_DAO;
					DetalleCriterioBusquedaDAO ldcbd_DAO;
					PredioTipoDAO              lptd_DAO;
					TipoEjeDAO                 lted_DAO;
					DepartamentoDAO            ldd_DAO;
					VeredaDAO                  lvd_DAO;
					InteresadoDocumentoTipoDAO lidtd_DAO;
					TipoPersonaDAO             ltp_DAO;
					TipoDocumentoPublicoDAO    ltdpd_DAO;
					TipoOficinaDAO             ltod_DAO;
					PaisDAO                    lpd_DAO;
					PersonaDAO                 lpd_personaDAO;
					DireccionPredioDAO         ldpd_DAO;

					RespuestasConsultaDAO lrcd_DAO;
					TurnoHistoria         lth_turnoHistoria;

					lcd_DAO               = DaoCreator.getConstantesDAO(adm_manager);
					ltd_DAO               = DaoCreator.getTurnoDAO(adm_manager);
					lcrd_DAO              = DaoCreator.getCirculoRegistralDAO(adm_manager);
					lmd_DAO               = DaoCreator.getMunicipioDAO(adm_manager);
					lld_DAO               = DaoCreator.getAccLiquidacionDAO(adm_manager);
					ldcbd_DAO             = DaoCreator.getDetalleCriterioBusquedaDAO(adm_manager);
					lptd_DAO              = DaoCreator.getPredioTipoDao(adm_manager);
					lted_DAO              = DaoCreator.getTipoEjeDAO(adm_manager);
					ldd_DAO               = DaoCreator.getDepartamentoDAO(adm_manager);
					lvd_DAO               = DaoCreator.getVeredaDAO(adm_manager);
					lidtd_DAO             = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager);
					ltp_DAO               = DaoCreator.getTipoPersonaDAO(adm_manager);
					ltdpd_DAO             = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
					ltod_DAO              = DaoCreator.getTipoOficinaDAO(adm_manager);
					lpd_DAO               = DaoCreator.getPaisDAO(adm_manager);
					lrcd_DAO              = DaoCreator.getRespuestasConsultaDAO(adm_manager);
					lth_turnoHistoria     = new TurnoHistoria(ath_th.getIdTurnoHistoria());
					lpd_personaDAO        = DaoCreator.getPersonaDAO(adm_manager);
					ldpd_DAO              = DaoCreator.getDireccionPredioDAO(adm_manager);

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

					if(
					    (lth_turnoHistoria == null)
						    || (NumericUtils.getLong(lth_turnoHistoria.getIdTurnoHistoria()) <= 0)
					)
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

					else
					{
						Turno lt_turno;
						lt_turno = new Turno();

						lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());

						lt_turno = ltd_DAO.findById(lt_turno);

						if(lt_turno != null)
						{
							Constantes lc_constante;
							byte[]     lba_plantilla;

							lc_constante     = new Constantes(as_plantilla);

							lc_constante = lcd_DAO.findImagen(lc_constante);

							if(lc_constante == null)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = as_plantilla;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
							}

							lba_plantilla = lc_constante.getImagenes();

							if(lba_plantilla == null)
							{
								Object[] loa_messageArgs = new String[1];
								loa_messageArgs[0] = as_plantilla;

								throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
							}
							else
							{
								String ls_plantilla;

								ls_plantilla = new String(lba_plantilla);

								if(StringUtils.isValidString(ls_plantilla))
								{
									Map<String, String> lmss_datos;
									String              ls_tag;
									Turno               lt_datosTurno;

									lmss_datos        = null;
									ls_tag            = null;
									lt_datosTurno     = ltd_DAO.findById(lt_turno);

									if(lt_datosTurno != null)
									{
										String ls_codigoVerificacion;
										String ls_orip;

										ls_codigoVerificacion     = null;
										ls_orip                   = lt_datosTurno.getIdCirculo();

										if(StringUtils.isValidString(ls_orip))
										{
											CirculoRegistral lcr_circuloRegistral;

											lcr_circuloRegistral = new CirculoRegistral();
											lcr_circuloRegistral.setIdCirculo(ls_orip);

											lcr_circuloRegistral = lcrd_DAO.findById(lcr_circuloRegistral);

											if(lcr_circuloRegistral != null)
											{
												String ls_tipoOficina;

												ls_tipoOficina = lcr_circuloRegistral.getTipoOficina();

												if(StringUtils.isValidString(ls_tipoOficina))
												{
													if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.P))
														ls_tipoOficina = "PRINCIPAL";
													else if(ls_tipoOficina.equalsIgnoreCase(EstadoCommon.S))
														ls_tipoOficina = "SECCIONAL";

													ls_tag = "<TAG_PRINCIPAL_SECCIONAL>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoOficina);
												}

												ls_tag = "<TAG_NOMBRE_ORIP>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_nombreOrip;
													ls_nombreOrip = lcr_circuloRegistral.getNombre();

													if(StringUtils.isValidString(ls_nombreOrip))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreOrip);
												}

												ls_tag = "<TAG_MUN_OFI_ORIGEN>";

												if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_orip))
												{
													Municipio lm_municipio;

													lm_municipio     = new Municipio();

													lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
															                     .findByIdCirculo(ls_orip);

													if(lm_municipio != null)
													{
														String ls_munOficinaOrigen;
														ls_munOficinaOrigen = lm_municipio.getNombre();

														if(
														    ls_plantilla.contains(ls_tag)
															    && StringUtils.isValidString(ls_munOficinaOrigen)
														)
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, ls_munOficinaOrigen
																);
													}
												}
											}
										}

										{
											ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_turno;
												ls_turno = lt_turno.getIdTurno();

												if(StringUtils.isValidString(ls_turno))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
											}
										}

										{
											ls_tag = "<TAG_FECHA_LARGA>";

											if(ls_plantilla.contains(ls_tag))
											{
												Date   ld_fecha;
												String ls_fechaActual;

												ld_fecha           = new Date();
												ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

												if(StringUtils.isValidString(ls_fechaActual))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, ls_fechaActual.toUpperCase()
														);
											}
										}

										{
											ls_tag = "<TAG_RECIBO_LIQUIDACION>";

											if(ls_plantilla.contains(ls_tag))
											{
												Liquidacion ll_liquidacion;

												ll_liquidacion = new Liquidacion();

												ll_liquidacion.setIdSolicitud(ls_idSolicitud);

												ll_liquidacion = lld_DAO.findById(ll_liquidacion, false);

												if(ll_liquidacion != null)
												{
													String ls_numeroRecibo;

													ls_numeroRecibo = ll_liquidacion.getNumeroReciboLiquidacion();

													if(StringUtils.isValidString(ls_numeroRecibo))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroRecibo);
												}
											}
										}

										{
											Map<String, String> lmss_return;

											lmss_return     = null;

											lmss_return = ListadoCodigosConstantes.generarCodigos(
												    ConstanteCommon.TAGS_CONSULTAS
												);

											if(CollectionUtils.isValidCollection(lmss_return))
											{
												Set<String> lks_keySet;

												lks_keySet = lmss_return.keySet();

												if(lks_keySet != null)
												{
													Iterator<String> lis_iterator;

													lis_iterator = lks_keySet.iterator();

													if(lis_iterator != null)
													{
														CriteriosDeBusqueda lcb_criterio;

														lcb_criterio = new CriteriosDeBusqueda();

														lcb_criterio.setIdSolicitud(ls_idSolicitud);

														while(lis_iterator.hasNext())
														{
															ls_tag = lis_iterator.next();

															if(
															    StringUtils.isValidString(ls_tag)
																    && ls_plantilla.contains(ls_tag)
															)
															{
																StringBuilder lsb_builder;

																String        ls_idDepartamento;
																String        ls_idMunicipio;
																String        ls_idPais;
																String        ls_departamento;
																String        ls_municipio;

																lsb_builder           = new StringBuilder();
																ls_idDepartamento     = null;
																ls_idMunicipio        = null;
																ls_idPais             = null;
																ls_departamento       = null;
																ls_municipio          = null;

																switch(ls_tag)
																{
																	case "<TAG_TIPO_IDENTIFICACION>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_1
																		);

																		break;

																	case "<TAG_BQN_DOC_NUMERO>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_2
																		);

																		break;

																	case "<TAG_DIRECCION_CONSULTA>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_3
																		);

																		break;

																	case "<TAG_CEDULA_CATASTRAL>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_4
																		);

																		break;

																	case "<TAG_SOLICITUD_MATRICULA>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_5
																		);

																		break;

																	case "<TAG_NUPRE>":
																		lcb_criterio.setIdTipoCriterio(
																		    ProcesoCommon.ID_PROCESO_6
																		);

																		break;

																	default:
																		break;
																}

																Collection<CriteriosDeBusqueda> lccb_criterios;

																lccb_criterios = ldcbd_DAO
																		.findByIdSolicitudTipoCriterioBusqueda(
																		    lcb_criterio
																		);

																if(CollectionUtils.isValidCollection(lccb_criterios))
																{
																	long          ll_consecutivoAnt;
																	StringBuilder lsb_direccion;

																	ll_consecutivoAnt     = 1L;
																	lsb_direccion         = new StringBuilder();

																	for(CriteriosDeBusqueda lcb_temp : lccb_criterios)
																	{
																		if(lcb_temp != null)
																		{
																			String ls_idCampoBusqueda;
																			String ls_valorCampo;
																			long   ll_consecutivoNuevo;

																			ls_idCampoBusqueda      = lcb_temp
																					.getIdCampoCriterioBusqueda();
																			ll_consecutivoNuevo     = lcb_temp
																					.getConsecutivoConsultaDetalle();
																			ls_valorCampo           = lcb_temp
																					.getValorCampo();

																			if(ll_consecutivoAnt != ll_consecutivoNuevo)
																			{
																				lsb_builder.append(lsb_direccion);
																				lsb_builder.append("{\\pard \\par}");
																				lsb_builder.append("{\\pard \\par}");
																				ll_consecutivoAnt     = ll_consecutivoNuevo;
																				lsb_direccion         = new StringBuilder();
																			}

																			if(
																			    ls_tag.equalsIgnoreCase(
																				        "<TAG_DIRECCION_CONSULTA>"
																				    )
																			)
																			{
																				if(
																				    StringUtils.isValidString(
																					        ls_idCampoBusqueda
																					    )
																					    && (!ls_idCampoBusqueda
																					    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2))
																					    && (!ls_idCampoBusqueda
																					    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3))
																					    && (!ls_idCampoBusqueda
																					    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4))
																				)
																				{
																					switch(ls_idCampoBusqueda)
																					{
																						case ProcesoCommon.ID_SUBPROCESO_1:

																							PredioTipo lpt_predio;
																							lpt_predio = new PredioTipo();
																							lpt_predio.setIdTipoPredio(
																							    ls_valorCampo
																							);
																							lpt_predio = lptd_DAO
																									.findById(
																									    lpt_predio
																									);

																							if(lpt_predio != null)
																							{
																								lsb_builder.append(
																								    StringUtils
																									    .getStringNotNull(lpt_predio
																									        .getDescripcion()
																									    )
																								);
																								lsb_builder.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);
																							}

																							break;

																						case ProcesoCommon.ID_SUBPROCESO_6:
																						case ProcesoCommon.ID_SUBPROCESO_8:

																							TipoEje lte_eje;
																							lte_eje = new TipoEje();
																							lte_eje.setIdTipoEje(
																							    ls_valorCampo
																							);
																							lte_eje = lted_DAO.findById(
																								    lte_eje
																								);

																							if(lte_eje != null)
																							{
																								lsb_builder.append(
																								    StringUtils
																									    .getStringNotNull(lte_eje
																									        .getNombre()
																									    )
																								);
																								lsb_builder.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);
																							}

																							break;

																						default:
																							lsb_builder.append(
																							    ls_valorCampo
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);

																							break;
																					}
																				}
																				else
																				{
																					switch(ls_idCampoBusqueda)
																					{
																						case ProcesoCommon.ID_SUBPROCESO_2:

																							Departamento ld_departamento;
																							ld_departamento = new Departamento();
																							ld_departamento
																								.setIdDepartamento(
																								    StringUtils
																								    .getStringNotNull(ls_valorCampo)
																								);
																							ld_departamento.setIdPais(
																							    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
																							);
																							ld_departamento = ldd_DAO
																									.findById(
																									    ld_departamento
																									);

																							if(ld_departamento != null)
																							{
																								ls_departamento       = StringUtils
																										.getStringNotNull(
																										    ld_departamento
																										    .getNombre()
																										)
																									+ IdentificadoresCommon.ESPACIO_VACIO;
																								ls_idDepartamento     = ls_valorCampo;
																							}

																							break;

																						case ProcesoCommon.ID_SUBPROCESO_3:

																							Municipio lm_municipio;
																							lm_municipio = new Municipio();
																							lm_municipio.setIdMunicipio(
																							    ls_valorCampo
																							);
																							lm_municipio
																								.setIdDepartamento(
																								    StringUtils
																								    .getStringNotNull(ls_idDepartamento)
																								);
																							lm_municipio.setIdPais(
																							    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
																							);
																							lm_municipio = lmd_DAO
																									.findById(
																									    lm_municipio
																									);

																							if(lm_municipio != null)
																							{
																								ls_municipio       = StringUtils
																										.getStringNotNull(
																										    lm_municipio
																										    .getNombre()
																										)
																									+ IdentificadoresCommon.ESPACIO_VACIO;
																								ls_idMunicipio     = ls_valorCampo;
																							}

																							break;

																						case ProcesoCommon.ID_SUBPROCESO_4:

																							Vereda lv_vereda;
																							lv_vereda = new Vereda();
																							lv_vereda.setIdMunicipio(
																							    StringUtils
																								    .getStringNotNull(ls_idMunicipio)
																							);
																							lv_vereda.setIdDepartamento(
																							    ls_idDepartamento
																							);
																							lv_vereda.setIdPais(
																							    IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT
																							);
																							lv_vereda.setIdVereda(
																							    StringUtils
																								    .getStringNotNull(ls_valorCampo)
																							);
																							lv_vereda = lvd_DAO.findById(
																								    lv_vereda
																								);

																							if(lv_vereda != null)
																							{
																								lsb_direccion.append(
																								    " - "
																								);
																								lsb_direccion.append(
																								    StringUtils
																									    .getStringNotNull(lv_vereda
																									        .getNombre()
																									    )
																								);
																								lsb_direccion.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);

																								lsb_direccion.append(
																								    StringUtils
																									    .getStringNotNull(ls_departamento)
																								);
																								lsb_direccion.append(
																								    StringUtils
																									    .getStringNotNull(ls_municipio)
																								);
																							}

																							break;

																						default:
																							break;
																					}
																				}
																			}
																			else if(
																			    ls_tag.equalsIgnoreCase(
																				        "<TAG_TIPO_IDENTIFICACION>"
																				    )
																			)
																			{
																				if(
																				    StringUtils.isValidString(
																					        ls_idCampoBusqueda
																					    )
																					    && (ls_idCampoBusqueda
																					    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
																					    || ls_idCampoBusqueda
																					    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2))
																				)
																				{
																					if(
																					    ls_idCampoBusqueda
																						    .equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
																					)
																					{
																						InteresadoDocumentoTipo lidt_documento;

																						lidt_documento = lidtd_DAO
																								.findById(
																								    new InteresadoDocumentoTipo(
																								        ls_valorCampo
																								    )
																								);

																						if(lidt_documento != null)
																						{
																							lsb_builder.append(
																							    StringUtils
																								    .getStringNotNull(lidt_documento
																								        .getDescripcion()
																								    )
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}
																					}
																					else
																					{
																						lsb_builder.append(
																						    StringUtils.getStringNotNull(
																						        ls_valorCampo
																						    )
																						);
																						lsb_builder.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																					}
																				}
																				else if(
																				    ls_idCampoBusqueda.equalsIgnoreCase(
																					        ProcesoCommon.ID_SUBPROCESO_3
																					    )
																				)
																				{
																					lsb_builder.append(
																					    "{\\pard \\par}"
																					);
																					lsb_builder.append(
																					    StringUtils.getStringNotNull(
																					        ls_valorCampo
																					    )
																					);
																					lsb_builder.append(
																					    IdentificadoresCommon.ESPACIO_VACIO
																					);
																				}
																				else if(
																				    ls_idCampoBusqueda.equalsIgnoreCase(
																					        ProcesoCommon.ID_SUBPROCESO_8
																					    )
																				)
																				{
																					TipoPersona ltp_tipoPersona;

																					ltp_tipoPersona = ltp_DAO.findById(
																						    ls_valorCampo
																						);

																					if(ltp_tipoPersona != null)
																					{
																						lsb_builder.append(
																						    StringUtils.getStringNotNull(
																						        ltp_tipoPersona
																							        .getDescripcion()
																						    )
																						);
																						lsb_builder.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																					}
																				}
																				else
																				{
																					lsb_builder.append(
																					    StringUtils.getStringNotNull(
																					        ls_valorCampo
																					    )
																					);
																					lsb_builder.append(
																					    IdentificadoresCommon.ESPACIO_VACIO
																					);
																				}
																			}
																			else if(
																			    ls_tag.equalsIgnoreCase(
																				        "<TAG_BQN_DOC_NUMERO>"
																				    )
																			)
																			{
																				switch(ls_idCampoBusqueda)
																				{
																					case ProcesoCommon.ID_SUBPROCESO_1:

																						TipoDocumentoPublico ltdp_tipoDocumento;
																						ltdp_tipoDocumento = new TipoDocumentoPublico();
																						ltdp_tipoDocumento
																							.setIdTipoDocumento(
																							    ls_valorCampo
																							);
																						ltdp_tipoDocumento = ltdpd_DAO
																								.findById(
																								    ltdp_tipoDocumento
																								);

																						if(ltdp_tipoDocumento != null)
																						{
																							lsb_builder.append(
																							    StringUtils
																								    .getStringNotNull(ltdp_tipoDocumento
																								        .getNombre()
																								    )
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}

																						break;

																					case ProcesoCommon.ID_SUBPROCESO_4:

																						TipoOficina lto_tipoOficina;
																						lto_tipoOficina = new TipoOficina();
																						lto_tipoOficina.setIdTipoOficina(
																						    ls_valorCampo
																						);
																						lto_tipoOficina = ltod_DAO
																								.findById(
																								    lto_tipoOficina
																								);

																						if(lto_tipoOficina != null)
																						{
																							lsb_builder.append(
																							    StringUtils
																								    .getStringNotNull(lto_tipoOficina
																								        .getNombre()
																								    )
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}

																						break;

																					case ProcesoCommon.ID_SUBPROCESO_5:

																						Pais lp_pais;
																						lp_pais = new Pais();
																						lp_pais.setIdPais(
																						    ls_valorCampo
																						);
																						lp_pais = lpd_DAO.findById(
																							    lp_pais
																							);

																						if(lp_pais != null)
																						{
																							lsb_builder.append(
																							    lp_pais.getNombre()
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																							ls_idPais = ls_valorCampo;
																						}

																						break;

																					case ProcesoCommon.ID_SUBPROCESO_6:

																						Departamento ld_departamento;
																						ld_departamento = new Departamento();
																						ld_departamento
																							.setIdDepartamento(
																							    StringUtils
																							    .getStringNotNull(ls_valorCampo)
																							);
																						ld_departamento.setIdPais(
																						    ls_idPais
																						);
																						ld_departamento = ldd_DAO
																								.findById(
																								    ld_departamento
																								);

																						if(ld_departamento != null)
																						{
																							lsb_builder.append(
																							    StringUtils
																								    .getStringNotNull(ld_departamento
																								        .getNombre()
																								    )
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																							ls_idDepartamento = ls_valorCampo;
																						}

																						break;

																					case ProcesoCommon.ID_SUBPROCESO_7:

																						Municipio lm_municipio;
																						lm_municipio = new Municipio();
																						lm_municipio.setIdMunicipio(
																						    ls_valorCampo
																						);
																						lm_municipio.setIdDepartamento(
																						    ls_idDepartamento
																						);
																						lm_municipio.setIdPais(
																						    ls_idPais
																						);
																						lm_municipio = lmd_DAO.findById(
																							    lm_municipio
																							);

																						if(lm_municipio != null)
																						{
																							lsb_builder.append(
																							    StringUtils
																								    .getStringNotNull(lm_municipio
																								        .getNombre()
																								    )
																							);
																							lsb_builder.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}

																						break;

																					case ProcesoCommon.ID_SUBPROCESO_8:

																						OficinaOrigen loo_oficinaOrigen;
																						loo_oficinaOrigen = new OficinaOrigen();
																						loo_oficinaOrigen
																							.setIdOficinaOrigen(
																							    ls_valorCampo
																							);
																						loo_oficinaOrigen = buscarOficinaOrigenPorId(
																							    ls_valorCampo,
																							    adm_manager
																							);

																						if(loo_oficinaOrigen != null)
																						{
																							lsb_direccion.append(
																							    StringUtils
																								    .getStringNotNull(loo_oficinaOrigen
																								        .getNombre()
																								    )
																							);
																							lsb_direccion.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}

																						break;

																					default:
																						lsb_builder.append(
																						    StringUtils.getStringNotNull(
																						        ls_valorCampo
																						    )
																						);
																						lsb_builder.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);

																						break;
																				}
																			}
																			else if(
																			    ls_tag.equalsIgnoreCase(
																				        "<TAG_SOLICITUD_MATRICULA>"
																				    )
																			)
																			{
																				if(
																				    ls_idCampoBusqueda.equalsIgnoreCase(
																					        ProcesoCommon.ID_SUBPROCESO_1
																					    )
																				)
																				{
																					lsb_builder.append(ls_valorCampo);
																					lsb_builder.append(" - ");
																				}
																				else
																				{
																					lsb_builder.append(ls_valorCampo);
																					lsb_builder.append(
																					    IdentificadoresCommon.ESPACIO_VACIO
																					);
																				}
																			}
																			else if(
																			    ls_tag.equalsIgnoreCase(
																				        "<TAG_CEDULA_CATASTRAL>"
																				    )
																			)
																			{
																				if(
																				    ls_idCampoBusqueda.equalsIgnoreCase(
																					        ProcesoCommon.ID_SUBPROCESO_1
																					    )
																				)
																				{
																					lsb_builder.append(ls_valorCampo);
																					lsb_builder.append(" - ");
																				}
																				else
																				{
																					lsb_builder.append(ls_valorCampo);
																					lsb_builder.append(
																					    IdentificadoresCommon.ESPACIO_VACIO
																					);
																				}
																			}
																			else
																			{
																				lsb_builder.append(ls_valorCampo);
																				lsb_builder.append(
																				    IdentificadoresCommon.ESPACIO_VACIO
																				);
																			}
																		}
																	}

																	lsb_builder.append(lsb_direccion);
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, lsb_builder.toString()
																		);
																}
															}
														}
													}
												}
											}
										}

										ls_tag = TagCommon.TAG_EXENTA_CONSULTAS;

										{
											String ls_exenta;

											ls_exenta = as_solicitud.getEntidadExenta();

											if(
											    StringUtils.isValidString(ls_exenta)
												    && BooleanUtils.getBooleanValue(ls_exenta)
											)
												ls_plantilla = reemplazarTagEnPlantilla(
													    ls_plantilla, TagCommon.TAG_EXENTA_CONSULTAS,
													    lcd_DAO.findString(ConstanteCommon.TAG_EXENTA_CONSULTAS)
													);
										}

										{
											ls_tag = "<TAG_PIN>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_CONVENIO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");
										}

										{
											ls_tag = "<TAG_ID_PROCESO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Subproceso lsp_subproceso;

												lsp_subproceso = new Subproceso();

												lsp_subproceso.setIdProceso(ProcesoCommon.ID_PROCESO_2);
												lsp_subproceso.setIdSubproceso(as_solicitud.getIdSubproceso());

												lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
														                       .findById(lsp_subproceso);

												if(lsp_subproceso != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, lsp_subproceso.getNombre()
														);
											}
										}

										{
											ls_tag = "<TAG_DIAS_LETRAS>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_DIAS>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_MES_LETRAS>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_ANIO_LETRAS>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_ANIO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");
										}

										{
											ls_tag = "<TAG_DIRECCION_ORIP>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_TELEFONO_ORIP>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");

											ls_tag = "<TAG_CORREO_ORIP>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, "");
										}

										{
											if(ls_plantilla.contains("<TAG_USUARIO>"))
											{
												if(StringUtils.isValidString(as_userId))
													ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", as_userId);
											}
										}

										{
											ls_tag = "<TAG_RESULTADO_CONSULTA>";

											if(ls_plantilla.contains(ls_tag))
											{
												int li_finalTag;

												li_finalTag = ls_plantilla.lastIndexOf(ls_tag);

												if(li_finalTag > 0)
												{
													String ls_textoMitadSuperior;
													String ls_textoMitadInferior;
													String ls_tagNew;

													ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
													ls_textoMitadInferior     = ls_plantilla.substring(
														    li_finalTag + ls_tag.length()
														);

													ls_tagNew     = "{\\*\\bkmkstart TABLAS_RESPUESTAS}{\\*\\bkmkend TABLAS_RESPUESTAS} \\line "
														+ ls_tag;

													ls_plantilla = ls_textoMitadSuperior
														+ IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
														+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
												}
											}
										}

										if(!ab_firma)
										{
											Collection<String> lcs_paneles;

											lmss_datos                = finalizarPlantilla(
												    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
												);
											ls_plantilla              = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
											ls_codigoVerificacion     = lmss_datos.get(
												    IdentificadoresCommon.CODIGO_VERIFICACION
												);
											lcs_paneles               = lrcd_DAO.findPanelsByIdSolicitud(
												    ls_idSolicitud
												);

											if(CollectionUtils.isValidCollection(lcs_paneles))
											{
												com.aspose.words.Document ld_doc;
												ByteArrayInputStream      lbais_docInStream;
												ByteArrayOutputStream     lbaos_docOutStream;
												DocumentBuilder           ldb_builder;
												Table                     lt_tableNombrePanel;
												Table                     lt_tableCriteriosResultados;

												ld_doc                 = null;
												lbais_docInStream      = null;
												lbaos_docOutStream     = null;
												lbais_docInStream      = new ByteArrayInputStream(
													    ls_plantilla.getBytes()
													);
												lbaos_docOutStream     = new ByteArrayOutputStream();
												ld_doc                 = new com.aspose.words.Document(
													    lbais_docInStream
													);
												ldb_builder            = new DocumentBuilder(ld_doc);
												ldb_builder.moveToBookmark("TABLAS_RESPUESTAS");

												for(String ls_panel : lcs_paneles)
												{
													if(StringUtils.isValidString(ls_panel))
													{
														Font lf_font;

														lf_font = ldb_builder.getFont();

														int  li_tamanoLetra;
														long ll_porcentajetablas;

														ll_porcentajetablas     = (long)50.0;
														li_tamanoLetra          = 11;
														lt_tableNombrePanel     = ldb_builder.startTable();

														ldb_builder.insertCell();
														ldb_builder.getParagraphFormat()
															           .setAlignment(ParagraphAlignment.CENTER);
														ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
														lf_font.setUnderline(0);
														ldb_builder.getCellFormat()
															           .setPreferredWidth(
															    PreferredWidth.fromPercent(100.0)
															);
														lf_font.setSize(li_tamanoLetra);
														lf_font.setBold(true);
														ldb_builder.write(ls_panel);

														ldb_builder.endRow();
														ldb_builder.endTable();
														lt_tableCriteriosResultados = ldb_builder.startTable();

														ldb_builder.insertCell();
														ldb_builder.getParagraphFormat()
															           .setAlignment(ParagraphAlignment.LEFT);
														ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
														lf_font.setUnderline(0);
														lf_font.setBold(true);
														ldb_builder.getCellFormat()
															           .setPreferredWidth(
															    PreferredWidth.fromPercent(ll_porcentajetablas)
															);

														lf_font.setSize(li_tamanoLetra);
														lf_font.setBold(true);
														ldb_builder.write("Criterío búsqueda");

														ldb_builder.insertCell();
														ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
														ldb_builder.getCellFormat()
															           .setPreferredWidth(
															    PreferredWidth.fromPercent(ll_porcentajetablas)
															);
														lf_font.setSize(li_tamanoLetra);
														ldb_builder.write("Resultado");

														ldb_builder.endRow();

														{
															RespuestaConsulta lrc_respuesta;

															lrc_respuesta = new RespuestaConsulta();
															lrc_respuesta.setIdSolicitud(ls_idSolicitud);

															switch(ls_panel)
															{
																case "IDENTIFICACION":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_1
																	);

																	break;

																case "DOCUMENTO":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_2
																	);

																	break;

																case "DIRECCION":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_3
																	);

																	break;

																case "CEDULA CATASTRAL":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_4
																	);

																	break;

																case "MATRICULA":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_5
																	);

																	break;

																case "NUPRE":
																	lrc_respuesta.setIdTipoCriterioBusqueda(
																	    ProcesoCommon.ID_SUBPROCESO_6
																	);

																	break;

																default:
																	break;
															}

															Collection<RespuestaConsulta> lcrc_respuestas;

															lcrc_respuestas = lrcd_DAO.findByIdSolicitudTipoCriterio(
																    lrc_respuesta
																);

															if(CollectionUtils.isValidCollection(lcrc_respuestas))
															{
																for(RespuestaConsulta lrc_temp : lcrc_respuestas)
																{
																	if(lrc_temp != null)
																	{
																		String ls_respuesta;
																		String ls_criterioSeleccion;

																		ls_respuesta             = lrc_temp.getRespuesta();
																		ls_criterioSeleccion     = ConversionTextos
																				.convertirTextos(
																				    lrc_temp.getCriterioSeleccion(),
																				    IdentificadoresCommon.SIMBOLO_COMA_TXT,
																				    IdentificadoresCommon.ESPACIO_VACIO
																				);

																		if(StringUtils.isValidString(ls_respuesta))
																		{
																			if(
																			    ls_respuesta.equalsIgnoreCase(
																				        EstadoCommon.EXITOSO
																				    )
																			)
																			{
																				boolean       lb_hayDireccion;
																				boolean       lb_hayOficinaOrigen;
																				boolean       lb_hayTestamento;
																				boolean       lb_hayPersona;
																				StringBuilder lsb_texto;
																				String        ls_circulo;
																				Long          ll_matricula;

																				lb_hayDireccion         = StringUtils
																						.isValidString(
																						    lrc_temp.getIdDireccion()
																						);
																				lb_hayOficinaOrigen     = StringUtils
																						.isValidString(
																						    lrc_temp.getIdOficinaOrigen()
																						);
																				lb_hayTestamento        = StringUtils
																						.isValidString(
																						    lrc_temp.getIdTestamento()
																						);
																				lb_hayPersona           = StringUtils
																						.isValidString(
																						    lrc_temp.getIdPersona()
																						);
																				lsb_texto               = new StringBuilder();
																				ls_circulo              = lrc_temp
																						.getIdCirculo();
																				ll_matricula            = lrc_temp
																						.getIdMatricula();

																				if(
																				    StringUtils.isValidString(
																					        ls_circulo
																					    )
																					    && NumericUtils.isValidLong(
																					        ll_matricula
																					    )
																				)
																				{
																					lsb_texto.append(ls_circulo);
																					lsb_texto.append(
																					    IdentificadoresCommon.SIMBOLO_GUION_ESPACIOS
																					);
																					lsb_texto.append(ll_matricula);
																					lsb_texto.append(
																					    IdentificadoresCommon.ESPACIO_VACIO
																					);
																				}

																				if(lb_hayDireccion)
																				{
																					DireccionPredio ldp_direccion;

																					ldp_direccion = new DireccionPredio();

																					ldp_direccion.setIdCirculo(
																					    StringUtils.getStringNotNull(
																					        ls_circulo
																					    )
																					);
																					ldp_direccion.setIdMatricula(
																					    ll_matricula
																					);
																					ldp_direccion.setIdDireccion(
																					    lrc_temp.getIdDireccion()
																					);
																					ldp_direccion = ldpd_DAO
																							.findByIdCirculoMatriculaIdDireccion(
																							    ldp_direccion
																							);

																					if(ldp_direccion != null)
																					{
																						{
																							PredioTipo lpt_predio;
																							lpt_predio = new PredioTipo();
																							lpt_predio.setIdTipoPredio(
																							    ldp_direccion
																								    .getTipoPredio()
																							);
																							lpt_predio = lptd_DAO
																									.findById(
																									    lpt_predio
																									);

																							if(lpt_predio != null)
																							{
																								lsb_texto.append(
																								    StringUtils
																									    .getStringNotNull(lpt_predio
																									        .getDescripcion()
																									    )
																								);
																								lsb_texto.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);
																							}
																						}

																						{
																							TipoEje lte_eje;
																							lte_eje = new TipoEje();
																							lte_eje.setIdTipoEje(
																							    ldp_direccion
																								    .getIdTipoEjePrincipal()
																							);
																							lte_eje = lted_DAO.findById(
																								    lte_eje
																								);

																							if(lte_eje != null)
																							{
																								lsb_texto.append(
																								    StringUtils
																									    .getStringNotNull(lte_eje
																									        .getNombre()
																									    )
																								);
																								lsb_texto.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);
																							}

																							lsb_texto.append(
																							    ldp_direccion
																								    .getDatoEjePrincipal()
																							);
																							lsb_texto.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																						}

																						{
																							TipoEje lte_eje;
																							lte_eje = new TipoEje();
																							lte_eje.setIdTipoEje(
																							    StringUtils
																								    .getStringNotNull(ldp_direccion
																								        .getIdTipoEjeSecundario()
																								    )
																							);
																							lte_eje = lted_DAO.findById(
																								    lte_eje
																								);

																							if(lte_eje != null)
																							{
																								lsb_texto.append(
																								    StringUtils
																									    .getStringNotNull(lte_eje
																									        .getNombre()
																									    )
																								);
																								lsb_texto.append(
																								    IdentificadoresCommon.ESPACIO_VACIO
																								);
																							}

																							lsb_texto.append(
																							    StringUtils
																								    .getStringNotNull(ldp_direccion
																								        .getDatoEjeSecundario()
																								    )
																							);
																							lsb_texto.append(
																							    IdentificadoresCommon.ESPACIO_VACIO
																							);
																							lsb_texto.append(
																							    StringUtils
																								    .getStringNotNull(ldp_direccion
																								        .getComplementoDireccion()
																								    )
																							);
																						}
																					}
																				}

																				if(lb_hayOficinaOrigen)
																				{
																					// TODO Por defininir
																				}

																				if(lb_hayTestamento)
																				{
																					// TODO Por defininir
																				}

																				if(lb_hayPersona)
																				{
																					Persona lp_persona;

																					lp_persona     = new Persona(
																						    lrc_temp.getIdPersona()
																						);

																					lp_persona = lpd_personaDAO.findById(
																						    lp_persona
																						);

																					if(lp_persona != null)
																					{
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getTipoDocIdentidad()
																						    )
																						);
																						lsb_texto.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getNumeroDocumento()
																						    )
																						);
																						lsb_texto.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getPrimerNombre()
																						    )
																						);
																						lsb_texto.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getSegundoNombre()
																						    )
																						);
																						lsb_texto.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getPrimerApellido()
																						    )
																						);
																						lsb_texto.append(
																						    IdentificadoresCommon.ESPACIO_VACIO
																						);
																						lsb_texto.append(
																						    StringUtils.getStringNotNull(
																						        lp_persona
																							        .getSegundoApellido()
																						    )
																						);
																					}
																				}

																				ldb_builder.insertCell();
																				ldb_builder.getParagraphFormat()
																					           .setAlignment(
																					    ParagraphAlignment.LEFT
																					);
																				ldb_builder.getCellFormat()
																					           .setHorizontalMerge(
																					    CellMerge.NONE
																					);
																				lf_font.setUnderline(0);
																				lf_font.setBold(false);
																				ldb_builder.getCellFormat()
																					           .setPreferredWidth(
																					    PreferredWidth.fromPercent(
																					        ll_porcentajetablas
																					    )
																					);

																				lf_font.setSize(li_tamanoLetra);
																				lf_font.setBold(false);
																				ldb_builder.write(ls_criterioSeleccion);

																				ldb_builder.insertCell();
																				ldb_builder.getCellFormat()
																					           .setHorizontalMerge(
																					    CellMerge.NONE
																					);
																				ldb_builder.getCellFormat()
																					           .setPreferredWidth(
																					    PreferredWidth.fromPercent(
																					        ll_porcentajetablas
																					    )
																					);
																				lf_font.setSize(li_tamanoLetra);
																				ldb_builder.write(lsb_texto.toString());

																				ldb_builder.endRow();
																			}
																			else
																			{
																				ldb_builder.insertCell();
																				ldb_builder.getParagraphFormat()
																					           .setAlignment(
																					    ParagraphAlignment.LEFT
																					);
																				ldb_builder.getCellFormat()
																					           .setHorizontalMerge(
																					    CellMerge.NONE
																					);
																				lf_font.setUnderline(0);
																				lf_font.setBold(false);
																				ldb_builder.getCellFormat()
																					           .setPreferredWidth(
																					    PreferredWidth.fromPercent(
																					        ll_porcentajetablas
																					    )
																					);

																				lf_font.setSize(li_tamanoLetra);
																				lf_font.setBold(false);
																				ldb_builder.write(ls_criterioSeleccion);

																				ldb_builder.insertCell();
																				ldb_builder.getCellFormat()
																					           .setHorizontalMerge(
																					    CellMerge.NONE
																					);
																				ldb_builder.getCellFormat()
																					           .setPreferredWidth(
																					    PreferredWidth.fromPercent(
																					        ll_porcentajetablas
																					    )
																					);
																				lf_font.setSize(li_tamanoLetra);
																				ldb_builder.write(
																				    addMessage(
																				        MessagesKeys.NO_SE_ENCONTRARON_RESULTADOS
																				    )
																				);

																				ldb_builder.endRow();
																			}
																		}
																	}
																}
															}
														}

														lt_tableNombrePanel.setAlignment(TableAlignment.CENTER);
														lt_tableCriteriosResultados.setAlignment(TableAlignment.CENTER);
														ldb_builder.endTable();

														ldb_builder.writeln(ControlChar.LINE_BREAK);
													}
												}

												ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

												byte[] docBytes = lbaos_docOutStream.toByteArray();

												lba_certificado = new PDFGenerator().convertirRTFToPDF(
													    docBytes, adm_manager
													);
											}
										}
										else
										{
											Constantes lc_usuarioFirma;
											String     ls_tagUsuarioFirma;
											int        li_incrX;
											int        li_incrY;

											li_incrX               = 0;
											li_incrY               = 0;
											lc_usuarioFirma        = new Constantes();
											ls_tagUsuarioFirma     = "<TAG_USUARIO_FIRMA_SUSPENSION>";

											lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

											lc_usuarioFirma           = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
											ls_plantilla              = getFirmaMasivaBusiness()
													                            .reemplazarUsuarioFirmaCargo(
													    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
													    "<TAG_CARGO_FIRMA_SUSPENSION>"
													);
											lmss_datos                = finalizarPlantilla(
												    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
												);
											ls_plantilla              = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
											ls_codigoVerificacion     = lmss_datos.get(
												    IdentificadoresCommon.CODIGO_VERIFICACION
												);
											lba_certificado           = new PDFGenerator().convertirRTFToPDF(
												    ls_plantilla.getBytes(), adm_manager
												);

											if(lba_certificado == null)
												throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

											byte[] lba_grafo;

											lba_grafo = null;

											if(lc_usuarioFirma != null)
											{
												lba_grafo     = lc_usuarioFirma.getImagenes();
												li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
												li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
											}

											lba_certificado = getFirmaMasivaBusiness()
													                  .reemplazarBookmarsFirma(
													    lba_certificado, lba_grafo, li_incrX, li_incrY
													);
										}

										lmso_return.put(
										    IdentificadoresCommon.CODIGO_VERIFICACION, ls_codigoVerificacion
										);
									}
								}
							}
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
					}
				}
				else
					throw new B2BException("GenerarDocumentosRespuesta");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("GenerarDocumentosRespuesta", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("GenerarDocumentosRespuesta", le_e.getMessage());

			throw new B2BException(le_e.getMessage());
		}

		lmso_return.put(IdentificadoresCommon.DOCUMENTO, lba_certificado);

		return lmso_return;
	}
}
