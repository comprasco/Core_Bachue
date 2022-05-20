package com.bachue.snr.prosnr01.business.entrega;

import com.aspose.words.CellMerge;
import com.aspose.words.ControlChar;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Font;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.PreferredWidth;
import com.aspose.words.Row;
import com.aspose.words.SaveFormat;
import com.aspose.words.Table;
import com.aspose.words.TableAlignment;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.ZipEntryUtil;
import com.b2bsg.common.file.ZipUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.bioclient.padFirmas.ClientePadFirmas;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.business.certificados.CertificadosBusiness;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.BookMarkCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.MotivonNoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoNotificacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCompletitudDocumentalDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.NotificacionDetalleDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaEntregaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.ProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.ReimpresionDocumentosDAO;
import com.bachue.snr.prosnr01.dao.acc.RelacionAprobacionDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDerivadoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioCiudadanoDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.entrega.DatosPredioTurnoEntrega;
import com.bachue.snr.prosnr01.model.entrega.DocumentoAdicional;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.reimpresion.Reimpresion;
import com.bachue.snr.prosnr01.model.reimpresion.ReimpresionRecibos;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.NotificacionDetalle;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.RelacionAprobacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoRecepcion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioCirculo;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.awt.Color;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.SQLException;

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
 * Clase para el manejo del negocio de entrega para hacer el proceso
 * correspondiente para la entrega.
 *
 * @author jpatino
 */
public class EntregaBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EntregaBusiness.class);

	/**
	 * Actualizar estado impresion.
	 *
	 * @param ads_documento de ads documento
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEstadoImpresion(DocumentosSalida ads_documento, String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ads_documento != null)
			{
				if(validarDatosAuditoria(as_idUsuario, as_ipRemota))
				{
					DocumentosSalidaDAO ldsd_DAO;
					DocumentosSalida    lds_temp;

					ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
					lds_temp     = ldsd_DAO.findById(ads_documento);

					if(lds_temp != null)
					{
						lds_temp.setEstadoImpresion(ads_documento.getEstadoImpresion());
						lds_temp.setIdUsuarioModificacion(as_idUsuario);
						lds_temp.setIpModificacion(as_ipRemota);
						ldsd_DAO.updateEstadoImpresion(lds_temp);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarEstadoImpresion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Actualizar interviene.
	 *
	 * @param as_interviene de as interviene
	 * @param as_idTurno de as id turno
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarInterviene(String as_interviene, String as_idTurno, String as_idUsuario, String as_ipRemota)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_idUsuario, as_ipRemota))
			{
				if(StringUtils.isValidString(as_interviene))
				{
					if(StringUtils.isValidString(as_idTurno))
					{
						TurnoDAO ltd_DAO;

						ltd_DAO = DaoCreator.getTurnoDAO(ldm_manager);

						{
							Turno lt_turno;

							lt_turno = ltd_DAO.findById(as_idTurno);

							if(lt_turno != null)
							{
								lt_turno.setIntervieneEntrega(as_interviene);
								lt_turno.setIdUsuarioModificacion(as_idUsuario);
								lt_turno.setIpModificacion(as_ipRemota);
								ltd_DAO.actualizarInterviene(lt_turno);
							}
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = as_idTurno;

								throw new B2BException(ErrorKeys.TURNO_NO_EXISTE, loa_args);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_INTERVIENE);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarInterviene", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Actualizar renuncia terminos.
	 *
	 * @param as_renuncia de as renuncia
	 * @param as_idTurno de as id turno
	 * @param al_idEtapa de al id etapa
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void actualizarRenunciaTerminos(
	    String as_renuncia, String as_idTurno, long al_idEtapa, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_idUsuario, as_ipRemota))
			{
				if(StringUtils.isValidString(as_renuncia))
				{
					if(StringUtils.isValidString(as_idTurno))
					{
						TurnoHistoriaDAO ltd_DAO;

						ltd_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = ltd_DAO.findByIdTurnoEtapa(as_idTurno, al_idEtapa);

							if(lth_turnoHistoria != null)
							{
								lth_turnoHistoria.setRenunciaTerminos(as_renuncia);
								ltd_DAO.actualizarRenunciaTerminos(lth_turnoHistoria, as_idUsuario);
							}
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = as_idTurno;

								throw new B2BException(ErrorKeys.TURNO_NO_EXISTE, loa_args);
							}
						}
					}
					else
						throw new B2BException(ErrorKeys.TURNO_INVALIDO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_RENUNCIA_TERMINOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("actualizarInterviene", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo encargado de consultar los tipos documentales de un apoderado o tercero.
	 *
	 * @param as_solicitud Argumento de tipo <code>Solicitud</code> que contiene la calidad solicitante para saber si es apoderado o tercero.
	 * @param actd_tiposDocumentales Argumento de tipo <code>Collection</code> que contiene los tipos documentales cargados por pantalla.
	 * @param as_constanteApoderado Argumento de tipo <code>String</code> que contiene la constante a usar para el apoderado.
	 * @param as_constanteTercero Argumento de tipo <code>String</code> que contiene la constante a usar para el tercero.
	 * @return <code>Collection</code> que contiene los tipos documentales cargados por constante.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TipoDocumental> actualizarTiposDocumentales(
	    Solicitud as_solicitud, Collection<TipoDocumental> actd_tiposDocumentales, String as_constanteApoderado,
	    String as_constanteTercero
	)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_tiposRetorno;

		lctd_tiposRetorno = null;

		if(
		    (as_solicitud != null)
			    && (CollectionUtils.isValidCollection(actd_tiposDocumentales) || as_solicitud.isEsCopias())
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lctd_tiposRetorno = actualizarTiposDocumentales(
					    ldm_manager, as_solicitud, actd_tiposDocumentales, as_constanteApoderado, as_constanteTercero
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("actualizarTiposDocumentales", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lctd_tiposRetorno;
	}

	/**
	 * Método encargado de consultar la persona asociada a un documento a reimprimir para un id turno y/o nir
	 * determinado.
	 *
	 * @return Retorna una collection de tipo CausalReimpresion que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<CausalReimpresion> cargarCausalReimpresion()
	    throws B2BException
	{
		DAOManager                    ldm_manager;
		Collection<CausalReimpresion> lccr_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lccr_datos      = null;

		try
		{
			lccr_datos = DaoCreator.getCausalReimpresionDAO(ldm_manager).findAll(true);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarCausalReimpresion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccr_datos;
	}

	/**
	 * Método encargado de consultar la persona asociada a un documento a reimprimir para un id turno y/o nir
	 * determinado.
	 *
	 * @param as_idTurno Objeto de tipo String que contiene el id turno determinado.
	 * @return Retorna un objeto de tipo Persona que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Persona cargarDatosPersonaReimpresion(String as_idTurno)
	    throws B2BException
	{
		Persona lp_persona;

		lp_persona = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Turno         lt_turno;
				ConstantesDAO lcd_constantesDAO;

				lt_turno              = DaoCreator.getTurnoDAO(ldm_manager).findById(as_idTurno);
				lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);

				if(lt_turno != null)
				{
					String ls_idSolicitud;
					String ls_idCirculo;

					ls_idSolicitud     = lt_turno.getIdSolicitud();
					ls_idCirculo       = lt_turno.getIdCirculo();

					if(StringUtils.isValidString(ls_idSolicitud) && StringUtils.isValidString(ls_idCirculo))
					{
						Collection<SolicitudMatricula> lcsm_solicitudMatricula;

						lcsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
								                                .findByIdSolicitudCirculo(ls_idSolicitud, ls_idCirculo);

						if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
						{
							PredioRegistroDAO lprd_predioRegistroDAO;

							lprd_predioRegistroDAO = DaoCreator.getPredioRegistroDAO(ldm_manager);

							for(SolicitudMatricula lc_temp : lcsm_solicitudMatricula)
							{
								if(lc_temp != null)
								{
									long ll_idMatriculaPrimi;
									Long ll_idMatricula;

									ll_idMatriculaPrimi     = lc_temp.getIdMatricula();
									ll_idMatricula          = NumericUtils.getLongWrapper(ll_idMatriculaPrimi);

									if(NumericUtils.isValidLong(ll_idMatricula))
									{
										Collection<PredioRegistro> lcpr_predioRegistro;

										lcpr_predioRegistro = lprd_predioRegistroDAO.findByIdCirculoIdMatricula(
											    ls_idCirculo, ll_idMatricula
											);

										if(CollectionUtils.isValidCollection(lcpr_predioRegistro))
										{
											for(PredioRegistro lpr_temp : lcpr_predioRegistro)
											{
												if(lpr_temp != null)
												{
													String ls_predioInconsistente;

													ls_predioInconsistente = lpr_temp.getPredioInconsistente();

													if(
													    StringUtils.isValidString(ls_predioInconsistente)
														    && ls_predioInconsistente.equalsIgnoreCase(EstadoCommon.S)
													)
													{
														Object[] loa_messageArgs = new String[2];

														loa_messageArgs[0]     = ls_idCirculo;
														loa_messageArgs[1]     = StringUtils.getString(ll_idMatricula);
														throw new B2BException(
														    ErrorKeys.PREDIO_INCONSISTENTE_REIMPRESION, loa_messageArgs
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

					PersonaEntrega lpe_personaEntrega;

					lpe_personaEntrega = DaoCreator.getPersonaEntregaDAO(ldm_manager).findByIdTurno(as_idTurno);

					if(lpe_personaEntrega != null)
					{
						Long ll_rangoInicial;
						Long ll_rangoFinal;

						ll_rangoInicial     = lcd_constantesDAO.findEntero(
							    ConstanteCommon.ETAPA_RANGO_INICIAL_REIMPRESION
							);
						ll_rangoFinal       = lcd_constantesDAO.findEntero(
							    ConstanteCommon.ETAPA_RANGO_FINAL_REIMPRESION
							);

						if(NumericUtils.isValidLong(ll_rangoInicial) && NumericUtils.isValidLong(ll_rangoFinal))
						{
							Collection<DocumentosSalida> lcds_documentosSalida;
							long                         ll_rangoInicialPrimi;
							long                         ll_rangoFinalPrimi;

							ll_rangoInicialPrimi     = NumericUtils.getLong(ll_rangoInicial);
							ll_rangoFinalPrimi       = NumericUtils.getLong(ll_rangoFinal);

							lcds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									                              .findReimpresionDocumentosTurnosImpresos(
									    as_idTurno, ll_rangoInicialPrimi, ll_rangoFinalPrimi
									);

							if(CollectionUtils.isValidCollection(lcds_documentosSalida))
							{
								String ls_idPersona;

								ls_idPersona = lpe_personaEntrega.getIdPersona();

								if(StringUtils.isValidString(ls_idPersona))
									lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);
							}

							else
							{
								Object[] loa_args;
								loa_args        = new String[1];
								loa_args[0]     = as_idTurno;
								throw new B2BException(ErrorKeys.ERROR_IMPRESIONES_SUPERADAS, loa_args);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDatosPersonaReimpresion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lp_persona;
	}

	/**
	 * Método encargado de consultar los documentos a reimprimir para un id turno
	 * determinado.
	 *
	 * @param as_idTurno Objeto de tipo String que contiene el id turno determinado.
	 * @return Retorna una colección de datos de tipo DocumentosSalida que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DocumentosSalida> cargarDocumentosAReimprimir(String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentosReimpresion;
		DAOManager                   ldm_manager;
		DocumentosSalidaDAO          ldsd_documentosSalidaDAO;
		ConstantesDAO                lcd_constantesDAO;
		TurnoDAO                     ltd_turnoDAO;
		TipoDocumentalDAO            ltpd_tipoDocumentalDAO;

		ldm_manager                    = DaoManagerFactory.getDAOManager();
		ldsd_documentosSalidaDAO       = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
		lcd_constantesDAO              = DaoCreator.getConstantesDAO(ldm_manager);
		ltd_turnoDAO                   = DaoCreator.getTurnoDAO(ldm_manager);
		ltpd_tipoDocumentalDAO         = DaoCreator.getTipoDocumentalDAO(ldm_manager);
		lcds_documentosReimpresion     = null;

		try
		{
			Long ll_rangoInicial;
			Long ll_rangoFinal;

			ll_rangoInicial     = lcd_constantesDAO.findEntero(ConstanteCommon.ETAPA_RANGO_INICIAL_REIMPRESION);
			ll_rangoFinal       = lcd_constantesDAO.findEntero(ConstanteCommon.ETAPA_RANGO_FINAL_REIMPRESION);

			if(NumericUtils.isValidLong(ll_rangoInicial) && NumericUtils.isValidLong(ll_rangoFinal))
			{
				Reimpresion lr_reimpresion;

				lr_reimpresion = new Reimpresion();
				lr_reimpresion.setIdTurno(as_idTurno);
				lr_reimpresion.setRangoInicial(ll_rangoInicial);
				lr_reimpresion.setRangoFinal(ll_rangoFinal);

				if(StringUtils.isValidString(as_idTurno))
				{
					Turno lt_turno;

					lt_turno = ltd_turnoDAO.findById(as_idTurno);

					if(lt_turno != null)
					{
						Long ll_idEtapaActual;
						long ll_idEtapaActualPrimi;
						long ll_rangoInicialPrimi;
						long ll_rangoFinalPrimi;

						ll_idEtapaActual          = lt_turno.getIdEtapaActual();
						ll_idEtapaActualPrimi     = NumericUtils.getLong(ll_idEtapaActual);
						ll_rangoInicialPrimi      = NumericUtils.getLong(ll_rangoInicial);
						ll_rangoFinalPrimi        = NumericUtils.getLong(ll_rangoFinal);

						if(NumericUtils.isValidLong(ll_idEtapaActual))
						{
							if(
							    (ll_idEtapaActualPrimi >= ll_rangoInicialPrimi)
								    && (ll_idEtapaActualPrimi < ll_rangoFinalPrimi)
							)
								lcds_documentosReimpresion = ldsd_documentosSalidaDAO.findReimpresionDocumentosTurnos(
									    lr_reimpresion
									);

							if(CollectionUtils.isValidCollection(lcds_documentosReimpresion))
							{
								for(DocumentosSalida lc_temp : lcds_documentosReimpresion)
								{
									if(lc_temp != null)
									{
										TipoDocumental ltd_tipoDocumental;

										ltd_tipoDocumental = ltpd_tipoDocumentalDAO.findById(
											    lc_temp.getIdTipoDocumental()
											);

										if(ltd_tipoDocumental != null)
										{
											String ls_idTipoDocumental;

											ls_idTipoDocumental = ltd_tipoDocumental.getNombre();

											if(StringUtils.isValidString(ls_idTipoDocumental))
												lc_temp.setIdTipoDocumentalNombre(ls_idTipoDocumental);
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

			clh_LOGGER.error("cargarDocumentosAReimprimir", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcds_documentosReimpresion;
	}

	/**
	 * Método encargado de cargar los documentos a imprimir para un turno de entrega.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id turno a consultar.
	 * @return Colección de tipo <code>DocumentosSalida</code> que contiene los criterios que coincidieron con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<DocumentosSalida> cargarDocumentosImpresion(String as_idTurno)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentosImprimir;

		lcds_documentosImprimir = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lcds_documentosImprimir = cargarDocumentosImpresion(as_idTurno, ldm_manager);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDocumentosImpresion", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lcds_documentosImprimir;
	}

	/**
	 * Método encargado de consultar los documentos a reimprimir para un id turno y/o nir
	 * determinado.
	 *
	 * @param as_idTurno Objeto de tipo String que contiene el id turno determinado.
	 * @param as_nir Objeto de tipo String que contiene el nir determinado.
	 * @return Retorna una colección de datos de tipo DocumentosSalida que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteTurno> cargarDocumentosReimpresion(String as_idTurno, String as_nir)
	    throws B2BException
	{
		Collection<TramiteTurno> lctt_tramitesTurno;
		DAOManager               ldm_manager;
		UtilDAO                  lud_DAO;
		ConstantesDAO            lcd_constantesDAO;
		TurnoDAO                 ltd_turnoDAO;
		TurnoHistoriaDAO         lthd_turnoHistoriaDAO;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lud_DAO                   = DaoCreator.getUtilDAO(ldm_manager);
		lcd_constantesDAO         = DaoCreator.getConstantesDAO(ldm_manager);
		ltd_turnoDAO              = DaoCreator.getTurnoDAO(ldm_manager);
		lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
		lctt_tramitesTurno        = null;

		try
		{
			Long ll_rangoInicial;
			Long ll_rangoFinal;

			ll_rangoInicial     = lcd_constantesDAO.findEntero(ConstanteCommon.ETAPA_RANGO_INICIAL_REIMPRESION);
			ll_rangoFinal       = lcd_constantesDAO.findEntero(ConstanteCommon.ETAPA_RANGO_FINAL_REIMPRESION);

			if(NumericUtils.isValidLong(ll_rangoInicial) && NumericUtils.isValidLong(ll_rangoFinal))
			{
				if(
				    (StringUtils.isValidString(as_idTurno) && !StringUtils.isValidString(as_nir))
					    || (StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_nir))
				)
				{
					Turno lt_turno;

					lt_turno = ltd_turnoDAO.findById(as_idTurno);

					if(lt_turno != null)
					{
						Long ll_idEtapaActual;
						long ll_idEtapaActualPrimi;
						long ll_rangoInicialPrimi;
						long ll_rangoFinalPrimi;

						ll_idEtapaActual          = lt_turno.getIdEtapaActual();
						ll_idEtapaActualPrimi     = NumericUtils.getLong(ll_idEtapaActual);
						ll_rangoInicialPrimi      = NumericUtils.getLong(ll_rangoInicial);
						ll_rangoFinalPrimi        = NumericUtils.getLong(ll_rangoFinal);

						if(NumericUtils.isValidLong(ll_idEtapaActual))
						{
							if(
							    (ll_idEtapaActualPrimi >= ll_rangoInicialPrimi)
								    && (ll_idEtapaActualPrimi < ll_rangoFinalPrimi)
							)
							{
								lctt_tramitesTurno = lud_DAO.findReimpresionDoc(
									    as_idTurno, as_nir, ll_rangoInicial, ll_rangoFinal, false
									);

								if(!CollectionUtils.isValidCollection(lctt_tramitesTurno))
								{
									TurnoHistoria lth_turnoHistoria;

									lth_turnoHistoria = lthd_turnoHistoriaDAO.findByIdTurnoEtapa(
										    as_idTurno, ll_idEtapaActualPrimi
										);

									if(lth_turnoHistoria != null)
									{
										String ls_estadoActividad;

										ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

										if(
										    (!StringUtils.isValidString(ls_estadoActividad))
											    || (!ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA))
										)
										{
											Object[] loa_args;
											loa_args        = new String[1];
											loa_args[0]     = as_idTurno;
											throw new B2BException(
											    ErrorKeys.ERROR_ESTADO_ACTIVIDAD_REIMPRESION, loa_args
											);
										}
									}

									Object[] loa_args;
									loa_args        = new String[1];
									loa_args[0]     = as_idTurno;
									throw new B2BException(
									    ErrorKeys.ERROR_NO_SE_ENCONTRARON_REGISTROS_REIMPRESION_TURNO, loa_args
									);
								}
							}
							else
							{
								Object[] loa_args;
								loa_args        = new String[1];
								loa_args[0]     = as_idTurno;
								throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA_REIMPRESION, loa_args);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);
					}
					else
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
				}
				else if((!StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_nir)))
				{
					lctt_tramitesTurno = lud_DAO.findReimpresionDoc(
						    as_idTurno, as_nir, ll_rangoInicial, ll_rangoFinal, true
						);

					if(!CollectionUtils.isValidCollection(lctt_tramitesTurno))
					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(as_nir);

						if(ls_solicitud != null)
						{
							String ls_idSolicitud;

							ls_idSolicitud = ls_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Collection<Turno> lct_turno;

								lct_turno = ltd_turnoDAO.findByIdSolicitudEtapa(
									    ls_idSolicitud, ll_rangoInicial, ll_rangoFinal
									);

								if(CollectionUtils.isValidCollection(lct_turno))
								{
									for(Turno lt_temp : lct_turno)
									{
										if(lt_temp != null)
										{
											Long ll_idEtapaActualNir;

											ll_idEtapaActualNir = lt_temp.getIdEtapaActual();

											if(NumericUtils.isValidLong(ll_idEtapaActualNir))
											{
												String ls_idTurno;
												long   ll_idEtapaActualPrimiNir;

												ls_idTurno                   = lt_temp.getIdTurno();
												ll_idEtapaActualPrimiNir     = NumericUtils.getLong(
													    ll_idEtapaActualNir
													);

												if(
												    StringUtils.isValidString(ls_idTurno)
													    && (ll_idEtapaActualPrimiNir > 0)
												)
												{
													TurnoHistoria lth_turnoHistoria;

													lth_turnoHistoria = lthd_turnoHistoriaDAO.findByIdTurnoEtapa(
														    ls_idTurno, ll_idEtapaActualPrimiNir
														);

													if(lth_turnoHistoria != null)
													{
														String ls_estadoActividad;

														ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

														if(
														    (!StringUtils.isValidString(ls_estadoActividad))
															    || (!ls_estadoActividad.equalsIgnoreCase(
															        EstadoCommon.TERMINADA
															    ))
														)
															throw new B2BException(
															    ErrorKeys.ERROR_ESTADO_ACTIVIDAD_REIMPRESION_NIR
															);
													}
												}
											}
										}
									}
								}
								else
								{
									Object[] loa_args;
									loa_args        = new String[1];
									loa_args[0]     = as_nir;
									throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA_REIMPRESION_NIR, loa_args);
								}
							}
						}

						{
							Object[] loa_args;
							loa_args        = new String[1];
							loa_args[0]     = as_nir;
							throw new B2BException(
							    ErrorKeys.ERROR_NO_SE_ENCONTRARON_REGISTROS_REIMPRESION_NIR, loa_args
							);
						}
					}
				}

				else
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);
			}

			if(CollectionUtils.isValidCollection(lctt_tramitesTurno))
			{
				Iterator<TramiteTurno> litt_iterator;

				litt_iterator = lctt_tramitesTurno.iterator();

				if(litt_iterator != null)
				{
					DocumentosSalidaDAO ldsd_DAO;

					ldsd_DAO = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

					while(litt_iterator.hasNext())
					{
						TramiteTurno ltt_temporal;
						boolean      lb_error;

						ltt_temporal     = litt_iterator.next();
						lb_error         = true;

						if(ltt_temporal != null)
						{
							String                       ls_idTurno;
							Collection<DocumentosSalida> lcds_documentosSalida;

							ls_idTurno                = ltt_temporal.getIdTurno();
							lcds_documentosSalida     = ldsd_DAO.findByIdTurnoEstadoImpreso(ls_idTurno);

							if(CollectionUtils.isValidCollection(lcds_documentosSalida))
							{
								Iterator<DocumentosSalida> lids_iteradorDocumento;

								lids_iteradorDocumento = lcds_documentosSalida.iterator();

								while(lids_iteradorDocumento.hasNext() && lb_error)
								{
									DocumentosSalida lds_documentoTemporal;

									lds_documentoTemporal = lids_iteradorDocumento.next();

									if(
									    (lds_documentoTemporal != null)
										    && (lds_documentoTemporal.getReimpresionDocumento() == 0)
									)
										lb_error = false;
								}

								if(lb_error)
								{
									Object[] loa_args;
									loa_args        = new String[1];
									loa_args[0]     = ls_idTurno;
									throw new B2BException(ErrorKeys.ERROR_IMPRESIONES_SUPERADAS, loa_args);
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

			clh_LOGGER.error("cargarDocumentosReimpresion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctt_tramitesTurno;
	}

	/**
	 * Método encargado de consultar los recibos a reimprimir para un id turno y/o nir
	 * determinado.
	 *
	 * @param as_idTurno Objeto de tipo String que contiene el id turno determinado.
	 * @param as_nir Objeto de tipo String que contiene el nir determinado.
	 * @return Retorna una colección de datos de tipo ReimpresionRecibos que coincide con los parametros de entrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<ReimpresionRecibos> cargarRecibosCajaLiquidacion(String as_idTurno, String as_nir)
	    throws B2BException
	{
		Collection<ReimpresionRecibos> lcrr_documentosCajaLiquidacion;
		DAOManager                     ldm_manager;
		DocumentosSalidaDAO            ldsd_documentosSalidaDAO;
		ProcesoDAO                     lpd_procesoDAO;
		TipoDocumentalDAO              ltdd_tipoDocumentalDAO;

		ldm_manager                        = DaoManagerFactory.getDAOManager();
		ldsd_documentosSalidaDAO           = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
		lpd_procesoDAO                     = DaoCreator.getProcesoDAO(ldm_manager);
		ltdd_tipoDocumentalDAO             = DaoCreator.getTipoDocumentalDAO(ldm_manager);
		lcrr_documentosCajaLiquidacion     = null;

		try
		{
			if(!StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_nir))
				lcrr_documentosCajaLiquidacion = ldsd_documentosSalidaDAO.findReciboCajaLiquidacion(as_nir, true);

			else if(!StringUtils.isValidString(as_idTurno) && !StringUtils.isValidString(as_nir))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_NIR_O_TURNO);

			else
				lcrr_documentosCajaLiquidacion = ldsd_documentosSalidaDAO.findReciboCajaLiquidacion(as_idTurno, false);

			if(CollectionUtils.isValidCollection(lcrr_documentosCajaLiquidacion))
			{
				for(ReimpresionRecibos lc_temp : lcrr_documentosCajaLiquidacion)
				{
					if(lc_temp != null)
					{
						Proceso        lp_proceso;
						TipoDocumental ltd_tipoDocumental;

						lp_proceso             = lpd_procesoDAO.findById(lc_temp.getProceso());
						ltd_tipoDocumental     = ltdd_tipoDocumentalDAO.findById(lc_temp.getTipo());

						if((lp_proceso != null) && (ltd_tipoDocumental != null))
						{
							String ls_nombreProceso;
							String ls_tipoDocumental;

							ls_nombreProceso      = lp_proceso.getNombre();
							ls_tipoDocumental     = ltd_tipoDocumental.getNombre();

							if(
							    StringUtils.isValidString(ls_nombreProceso)
								    && StringUtils.isValidString(ls_tipoDocumental)
							)
							{
								lc_temp.setNombreProceso(ls_nombreProceso);
								lc_temp.setTipoDocumentalNombre(ls_tipoDocumental);
							}
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_REGISTROS_REIMPRESION);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarRecibosCajaLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcrr_documentosCajaLiquidacion;
	}

	/**
	 * Cargar turno.
	 *
	 * @param att_param de att param
	 * @param al_etapa de al etapa
	 * @return el valor de entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Entrega cargarTurno(TramiteTurno att_param, long al_etapa)
	    throws B2BException
	{
		Entrega    leui_entrega;
		DAOManager ldm_manager;

		leui_entrega     = null;
		ldm_manager      = DaoManagerFactory.getDAOManager();

		try
		{
			String ls_turno;
			String ls_turnoHistoria;

			ls_turno             = null;
			ls_turnoHistoria     = null;

			if(att_param != null)
			{
				ls_turno             = att_param.getIdTurno();
				ls_turnoHistoria     = StringUtils.getString(att_param.getIdTurnoHistoria());
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			if(StringUtils.isValidString(ls_turno))
			{
				Turno lt_turno;

				lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(ls_turno);

				if(lt_turno != null)
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lt_turno.getIdSolicitud());

					if(ls_solicitud != null)
					{
						Collection<SolicitudMatricula> lcsm_matriculas;
						SolicitudMatricula             lsm_sm;

						lcsm_matriculas     = null;
						lsm_sm              = new SolicitudMatricula();

						lsm_sm.setIdSolicitud(ls_solicitud.getIdSolicitud());
						lsm_sm.setIdCirculo(lt_turno.getIdCirculo());
						lsm_sm.setIdTurnoCertificado(lt_turno.getIdTurno());
						lcsm_matriculas = findMatriculasBySolicitudCirculo(lsm_sm, ldm_manager);

						{
							String                  ls_idTurno;
							DatosPredioTurnoEntrega ldpte_consultaDatosPredio;

							ls_idTurno                    = lt_turno.getIdTurno();
							ldpte_consultaDatosPredio     = new DatosPredioTurnoEntrega();

							leui_entrega = new Entrega();
							leui_entrega.setNir(ls_solicitud.getNir());
							leui_entrega.setTurno(lt_turno);
							leui_entrega.setIdTurno(ls_turno);
							leui_entrega.setSolicitud(ls_solicitud);
							leui_entrega.setDatosMatriculas(lcsm_matriculas);
							ldpte_consultaDatosPredio.setIdTurno(ls_idTurno);
							ldpte_consultaDatosPredio = DaoCreator.getConsultasDAO(ldm_manager)
									                                  .findDatosPredioByTurnoEntrega(
									    ldpte_consultaDatosPredio
									);

							if(ldpte_consultaDatosPredio == null)
								ldpte_consultaDatosPredio = new DatosPredioTurnoEntrega();

							leui_entrega.setDatosPredioEntrega(ldpte_consultaDatosPredio);

							{
								Collection<SolicitudInterviniente> lcsi_datos;
								String                             ls_solicitudStr;
								SolicitudInterviniente             lsi_parametros;

								lcsi_datos          = null;
								ls_solicitudStr     = ls_solicitud.getIdSolicitud();
								lsi_parametros      = new SolicitudInterviniente();

								lsi_parametros.setIdSolicitud(ls_solicitudStr);
								lsi_parametros.setIdTurno(ls_idTurno);
								lsi_parametros.setIdEtapa(al_etapa);
								lsi_parametros.setIdTurnoHistoria(ls_turnoHistoria);

								{
									Collection<Solicitud>              lcs_solicitudesAsociadas;
									Collection<SolicitudInterviniente> lcsi_personasSolicitudesAsociadas;

									boolean lb_proceso47o48;

									lb_proceso47o48                       = false;
									lcs_solicitudesAsociadas              = obtenerSolicitudesVinculadas(
										    ls_solicitudStr, false, ldm_manager
										);
									lcsi_personasSolicitudesAsociadas     = new ArrayList<SolicitudInterviniente>(1);

									if(CollectionUtils.isValidCollection(lcs_solicitudesAsociadas))
									{
										Iterator<Solicitud> lis_iterator;
										PersonaDAO          lpd_DAO;

										lis_iterator     = lcs_solicitudesAsociadas.iterator();
										lpd_DAO          = DaoCreator.getPersonaDAO(ldm_manager);

										while(lis_iterator.hasNext() && !lb_proceso47o48)
										{
											Solicitud ls_actual;

											ls_actual = lis_iterator.next();

											if(ls_actual != null)
											{
												String ls_procesoVinculado;

												ls_procesoVinculado = ls_actual.getIdProceso();

												if(StringUtils.isValidString(ls_procesoVinculado))
													lb_proceso47o48 = (ls_procesoVinculado.equalsIgnoreCase(
														    ProcesoCommon.ID_PROCESO_47
														)
															|| ls_procesoVinculado.equalsIgnoreCase(
															    ProcesoCommon.ID_PROCESO_48
															));

												{
													String ls_idPersona;

													ls_idPersona = ls_actual.getIdPersona();

													if(StringUtils.isValidString(ls_idPersona))
													{
														Persona lp_persona;

														lp_persona = lpd_DAO.findById(ls_idPersona);

														if(lp_persona != null)
															lcsi_personasSolicitudesAsociadas.add(
															    new SolicitudInterviniente(
															        lp_persona, ls_actual.getIdSolicitud()
															    )
															);
													}
												}
											}
										}
									}

									leui_entrega.setProcesoVinculado47o48(lb_proceso47o48);

									//TODO 4.6.	Sección Datos Personas a Notificar: REQ131
									lcsi_datos = findSolicitudIntervinienteBySolicitud(lsi_parametros, ldm_manager);

									if(!CollectionUtils.isValidCollection(lcsi_datos))
									{
										Persona                lp_interesado;
										SolicitudInterviniente lsi_interviniente;

										lp_interesado         = DaoCreator.getPersonaDAO(ldm_manager)
												                              .findById(ls_solicitud.getIdPersona());
										lsi_interviniente     = new SolicitudInterviniente();

										if(lp_interesado == null)
										{
											Object[] aoa_messageArgs = new String[1];
											aoa_messageArgs[0] = lt_turno.getIdTurno();
											throw new B2BException(
											    ErrorKeys.ERROR_SIN_INTERVINIENTES_TURNO, aoa_messageArgs
											);
										}

										lcsi_datos = new LinkedList<SolicitudInterviniente>();

										lsi_interviniente.setPersona(lp_interesado);
										lsi_interviniente.setIdSolicitud(ls_solicitudStr);
										lsi_interviniente.setIdPersona(lp_interesado.getIdPersona());

										lcsi_datos.add(lsi_interviniente);
									}

									if(CollectionUtils.isValidCollection(lcsi_datos))
									{
										if(CollectionUtils.isValidCollection(lcsi_personasSolicitudesAsociadas))
										{
											for(SolicitudInterviniente lsi_iterator : lcsi_personasSolicitudesAsociadas)
											{
												if((lsi_iterator != null) && !lcsi_datos.contains(lsi_iterator))
													lcsi_datos.add(lsi_iterator);
											}
										}

										leui_entrega.setDatosIntervinientes(lcsi_datos);
									}
								}
							}
						}

						{
							leui_entrega.setTiposDocumentales(
							    DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
								              .findByIdTurnoDigitalizado(ls_turno, EstadoCommon.N)
							);
						}
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs     = new String[1];

						loa_messageArgs[0] = lt_turno.getIdTurno();
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD_TURNO, loa_messageArgs);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.TURNO_SELECCIONADO_INVALIDO);
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return leui_entrega;
	}

	/**
	 * Procesa un tramite de entrega para devolverlo a una etapa anterior.
	 *
	 * @param ath_th            Objeto contenedor del turno historia asociado al tramite de
	 *            entrega
	 * @param as_decisionEntrega            Cadena de caracteres con la desición final sobre el tramite
	 * @param as_idUsuario            Id del usuario que ejecuta el proceso
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta el proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void devolverACalificacion(
	    TurnoHistoria ath_th, String as_decisionEntrega, String as_idUsuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_th != null)
			{
				MotivoTramite lmt_motivo;

				lmt_motivo = new MotivoTramite();

				lmt_motivo.setIdEtapaOrigen(NumericUtils.getLong(ath_th.getIdEtapa()));

				if(StringUtils.isValidString(as_decisionEntrega))
				{
					if(as_decisionEntrega.equalsIgnoreCase(EstadoCommon.C))
						lmt_motivo.setIdMotivo(MotivoTramiteCommon.TRAMITE_DE_REANOTACION_DEVOLUCION_CALIFICACION);
					else
						lmt_motivo.setIdMotivo(MotivoTramiteCommon.DEFAULT);
				}
				else
					throw new B2BException("Debe seleccionar decision de entrega");

				lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

				if(lmt_motivo != null)
				{
					TurnoHistoria                lth_turnoHistoria;
					TurnoHistoriaDAO             lth_DAO;
					DocumentosSalidaDAO          ldsd_DAO;
					Collection<DocumentosSalida> lcds_documentosSalida;
					DocumentosSalida             lds_documentoSalida;

					lth_DAO                   = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
					ldsd_DAO                  = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
					lth_turnoHistoria         = new TurnoHistoria();
					lcds_documentosSalida     = new ArrayList<DocumentosSalida>();
					lds_documentoSalida       = new DocumentosSalida();

					lth_turnoHistoria.setIdTurnoHistoria(ath_th.getIdTurnoHistoria());
					lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						lds_documentoSalida.setIdTurno(lth_turnoHistoria.getIdTurno());
						lds_documentoSalida.setTipo(ConstanteCommon.NOTA_DEVOLUTIVA);
						lcds_documentosSalida = ldsd_DAO.findByIdTurnoTipo(lds_documentoSalida);

						if(CollectionUtils.isValidCollection(lcds_documentosSalida))
						{
							ImagenesDAO li_DAO;

							li_DAO = DaoCreator.getImagenesDAO(ldm_manager);

							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
							lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
							lth_turnoHistoria.setIdUsuarioModificacion(as_idUsuario);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lth_DAO.insertOrUpdate(lth_turnoHistoria, false);

							DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_turnoHistoria);

							for(DocumentosSalida lds_temp : lcds_documentosSalida)
							{
								if(lds_temp != null)
								{
									lds_temp.setEstado(EstadoCommon.INACTIVO);
									lds_temp.setIdUsuarioModificacion(as_idUsuario);
									lds_temp.setIpModificacion(as_remoteIp);

									ldsd_DAO.insertOrUpdate(lds_temp, false);
									li_DAO.eliminarCodigoVerificacion(lds_temp.getIdImagen());
								}
							}
						}
						else
							throw new B2BException("NADA EN DOCUMENTO SALIDA");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("devolverACalificacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Elimina los registros de terceros agregados para una solicitud especifica.
	 *
	 * @param ape_persona            Objeto contenedor de la solicitud a usar como filtro para la
	 *            eliminación de registros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void eliminarDatosPersonaTercero(PersonaEntrega ape_persona)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			DaoCreator.getPersonaEntregaDAO(ldm_manager).delete(ape_persona);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("eliminarDatosPersonaTercero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Eliminar tipo documental.
	 *
	 * @param atd_tipoDocumental de atd tipo documental
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void eliminarTipoDocumental(TipoDocumental atd_tipoDocumental)
	    throws B2BException
	{
		if(atd_tipoDocumental != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager).deleteById(atd_tipoDocumental);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("eliminarTipoDocumental", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar los
	 * correos del interviniente seleccionado pertenecientes a determinada solicitud.
	 *
	 * @param ape_data            Objeto contenedor de filtros para la busqueda
	 * @return Coleccion con registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<String> findCorreosByIdPersona(SolicitudInterviniente ape_data)
	    throws B2BException
	{
		DAOManager                           ldm_manager;
		Collection<PersonaCorreoElectronico> lcpe_correos;
		Collection<String>                   lcpe_datos;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcpe_correos     = null;
		lcpe_datos       = new ArrayList<String>();

		try
		{
			String ls_idPersona;

			ls_idPersona = ape_data.getIdPersona();

			if(StringUtils.isValidString(ls_idPersona))
				lcpe_correos = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager).findByIdPersona(ls_idPersona);

			if(lcpe_correos != null)
			{
				for(PersonaCorreoElectronico lpce_pce : lcpe_correos)
				{
					if(lpce_pce != null)
					{
						String ls_correo;

						ls_correo = lpce_pce.getCorreoElectronico();

						if(StringUtils.isValidString(ls_correo))
							lcpe_datos.add(ls_correo);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCorreosByIdPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpe_datos;
	}

	/**
	 * Permite encontrar la información personal del interviniente agregado como
	 * tercero.
	 *
	 * @param as_idPersona            id persona del interviniente agregado como tercero
	 * @return Objeto Persona contenedor de la información encontrada del
	 *         interviniente agregado como tercero
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Persona findDatosPersonaTercero(String as_idPersona)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Persona    lp_persona;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lp_persona      = null;

		try
		{
			lp_persona = new Persona();

			lp_persona.setIdPersona(as_idPersona);

			lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_persona);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosPersonaTercero", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lp_persona;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar datos
	 * del predio por el turno de la entrega.
	 *
	 * @param adpte_idTurno            Objeto con Id turno para realizar la consulta
	 * @return el valor de datos predio turno entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DatosPredioTurnoEntrega findDatosPredioByTurnoEntrega(DatosPredioTurnoEntrega adpte_idTurno)
	    throws B2BException
	{
		DAOManager              ldm_manager;
		DatosPredioTurnoEntrega ldpte_result;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ldpte_result     = null;

		try
		{
			ConsultasDAO lcDAO_consultas;
			lcDAO_consultas = DaoCreator.getConsultasDAO(ldm_manager);

			if(adpte_idTurno == null)
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);

			ldpte_result = lcDAO_consultas.findDatosPredioByTurnoEntrega(adpte_idTurno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDatosPredioByTurnoEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldpte_result;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar la
	 * entrega.
	 *
	 * @param al_idState            long para saber el estado
	 * @param as_nir            String con el nir
	 * @param as_idTurno            String con el id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteTurno> findDetailDelivery(
	    long al_idState, String as_nir, String as_idTurno, String as_userId
	)
	    throws B2BException
	{
		return findDetailDelivery(al_idState, as_nir, as_idTurno, false, as_userId);
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar la
	 * entrega.
	 *
	 * @param idState correspondiente al valor de id estado
	 * @param as_nir            String con el nir
	 * @param as_idTurno            String con el id turno
	 * @param ab_notificaciones Boolean con el valor de notificaciones
	 * @param as_userId correspondiente al valor de user id
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteTurno> findDetailDelivery(
	    long al_idState, String as_nir, String as_idTurno, boolean ab_notificaciones, String as_userId
	)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		Collection<TramiteTurno> lctt_result;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lctt_result     = new ArrayList<TramiteTurno>();

		try
		{
			int                             ll_count;
			LinkedHashMap<Integer, Object>  lhmpCriterios;
			StringBuilder                   lsb_consulta;
			Collection<Map<String, Object>> ll_result;

			ll_count         = 1;
			ll_result        = null;
			lsb_consulta     = new StringBuilder(ConsultasUtilidades.CS_CONSULTA_ENTREGA_TURNO_NIR);
			lsb_consulta.append(
			    ab_notificaciones ? "AND TH.ESTADO_ACTIVIDAD <> 'TER' AND S.ID_PROCESO <> '38'"
			                      : "AND TH.ESTADO_ACTIVIDAD = 'ASG'"
			);
			lhmpCriterios = new LinkedHashMap<Integer, Object>();

			lhmpCriterios.put(Integer.valueOf(ll_count++), Long.valueOf(al_idState));

			if(StringUtils.isValidString(as_nir))
			{
				lsb_consulta.append(" AND S.NIR = ? ");
				lhmpCriterios.put(Integer.valueOf(ll_count++), as_nir);
			}

			if(StringUtils.isValidString(as_idTurno))
			{
				lsb_consulta.append(" AND T.ID_TURNO = ? ");
				lhmpCriterios.put(Integer.valueOf(ll_count++), as_idTurno);
			}

			String  ls_constante;
			boolean lb_etapaValidaEntregaCorrespondencia;

			lb_etapaValidaEntregaCorrespondencia     = false;
			ls_constante                             = ConstanteCommon.ETAPAS_VALIDAS_ENTREGA_CORRESPONDENCIA;

			if(StringUtils.isValidString(ls_constante))
			{
				Constantes lc_constante;

				lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_constante);

				if(lc_constante != null)
				{
					String ls_caracter;

					ls_caracter = lc_constante.getCaracter();

					if(StringUtils.isValidString(ls_caracter))
					{
						int      li_tam;
						String[] lsa_codigos;

						lsa_codigos     = ls_caracter.split(",");
						li_tam          = lsa_codigos.length;

						if(li_tam > 0)
						{
							for(int li_count = 0; li_count < li_tam; li_count++)
							{
								String ls_codigo;

								ls_codigo = lsa_codigos[li_count];

								if(StringUtils.isValidString(ls_codigo))
								{
									if(ls_codigo.equalsIgnoreCase(StringUtils.getString(al_idState)))
									{
										lb_etapaValidaEntregaCorrespondencia = true;

										Collection<UsuarioCirculo> luc_usuarioCirculos;

										luc_usuarioCirculos = DaoCreator.getUsuarioCirculoDAO(ldm_manager)
												                            .findByUsuarioActive(as_userId);

										if(CollectionUtils.isValidCollection(luc_usuarioCirculos))
										{
											String ls_circulos;
											ls_circulos = null;

											for(UsuarioCirculo luc_tmp : luc_usuarioCirculos)
											{
												if(StringUtils.isValidString(ls_circulos))
												{
													lsb_consulta.append(",?");
													lhmpCriterios.put(
													    Integer.valueOf(ll_count++), luc_tmp.getIdCirculo()
													);
												}
												else
												{
													ls_circulos = luc_tmp.getIdCirculo();
													lsb_consulta.append("AND T.ID_CIRCULO IN (?");
													lhmpCriterios.put(
													    Integer.valueOf(ll_count++), luc_tmp.getIdCirculo()
													);
												}
											}

											if(StringUtils.isValidString(ls_circulos))
												lsb_consulta.append(")");
										}
									}
								}
							}
						}
					}
				}
			}

			lsb_consulta.append(" ORDER BY TH.ID_TURNO ASC, TH.FECHA_INICIAL, TH.FECHA_CREACION DESC");

			ll_result = DaoCreator.getUtilDAO(ldm_manager).getCustonQuery(lsb_consulta.toString(), lhmpCriterios);

			if(CollectionUtils.isValidCollection(ll_result))
			{
				Iterator<Map<String, Object>> li_consulta;

				li_consulta = ll_result.iterator();

				while(li_consulta.hasNext())
				{
					Map<String, Object> llhm_actual;

					llhm_actual = li_consulta.next();

					if(llhm_actual != null)
					{
						TramiteTurno ltt_turno;
						String       ls_idTurno;
						ls_idTurno     = null;

						ltt_turno = new TramiteTurno();

						{
							ls_idTurno = StringUtils.getString((String)llhm_actual.get(IdentificadoresCommon.ID_TURNO));

							if(StringUtils.isValidString(ls_idTurno))
								ltt_turno.setIdTurno(ls_idTurno);
						}

						{
							String ls_tmp;

							oracle.sql.TIMESTAMP lts_timeStamp = (oracle.sql.TIMESTAMP)llhm_actual.get(
								    IdentificadoresCommon.FECHA_CREACION
								);

							if(lts_timeStamp != null)
							{
								ls_tmp = StringUtils.getString(
									    (new Date(lts_timeStamp.dateValue().getTime())), FormatoFechaCommon.DIA_MES_ANIO
									);

								if(StringUtils.isValidString(ls_tmp))
									ltt_turno.setFechaCreacion(
									    DateUtils.getDate(ls_tmp, FormatoFechaCommon.DIA_MES_ANIO)
									);
							}
						}

						{
							String ls_tmp;

							oracle.sql.TIMESTAMP lts_timeStamp = (oracle.sql.TIMESTAMP)llhm_actual.get(
								    IdentificadoresCommon.FECHA_CREACION_ETAPA
								);

							if(lts_timeStamp != null)
							{
								ls_tmp = StringUtils.getString(
									    (new Date(lts_timeStamp.dateValue().getTime())), FormatoFechaCommon.DIA_MES_ANIO
									);

								if(StringUtils.isValidString(ls_tmp))
								{
									ltt_turno.setFechaCreacionEtapa(
									    DateUtils.getDate(ls_tmp, FormatoFechaCommon.DIA_MES_ANIO)
									);

									if(al_idState == EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
										ltt_turno.setFechaAsignacion(
										    DateUtils.getDate(ls_tmp, FormatoFechaCommon.DIA_MES_ANIO)
										);
								}
							}
						}

						if(al_idState != EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(
								    ((Date)llhm_actual.get(IdentificadoresCommon.FECHA_ASIGNACION)),
								    FormatoFechaCommon.DIA_MES_ANIO
								);

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setFechaAsignacion(
								    DateUtils.getDate(ls_tmp, FormatoFechaCommon.DIA_MES_ANIO)
								);
							else
							{
								String ls_tmp2;

								oracle.sql.TIMESTAMP lts_timeStamp = (oracle.sql.TIMESTAMP)llhm_actual.get(
									    IdentificadoresCommon.FECHA_CREACION_ETAPA
									);

								if(lts_timeStamp != null)
								{
									ls_tmp2 = StringUtils.getString(
										    (new Date(lts_timeStamp.dateValue().getTime())),
										    FormatoFechaCommon.DIA_MES_ANIO
										);

									if(StringUtils.isValidString(ls_tmp2))
									{
										ltt_turno.setFechaCreacionEtapa(
										    DateUtils.getDate(ls_tmp2, FormatoFechaCommon.DIA_MES_ANIO)
										);

										if(StringUtils.isValidString(ls_tmp2))
											ltt_turno.setFechaAsignacion(
											    DateUtils.getDate(ls_tmp2, FormatoFechaCommon.DIA_MES_ANIO)
											);
									}
								}
							}
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(
								    llhm_actual.get(IdentificadoresCommon.ID_TURNO_HISTORIA).toString()
								);

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_tmp));
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(llhm_actual.get(IdentificadoresCommon.NIR).toString());

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setNir(ls_tmp);
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(
								    llhm_actual.get(IdentificadoresCommon.ESTADO_ACTIVIDAD).toString()
								);

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setEstadoActividad(ls_tmp);
						}

						{
							String ls_tmp;
							Object lo_obserb;

							lo_obserb     = llhm_actual.get(IdentificadoresCommon.OBSERVACIONES);
							ls_tmp        = StringUtils.getStringNotNull(
								    (lo_obserb != null) ? lo_obserb.toString() : null
								);

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setObservaciones(ls_tmp);
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString((String)llhm_actual.get(IdentificadoresCommon.NOMBRE));

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setNombre(ls_tmp);
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(
								    (String)llhm_actual.get(IdentificadoresCommon.MOTIVO_NO_TRAMITE)
								);

							if(StringUtils.isValidString(ls_tmp))
								ltt_turno.setMotivoNoTramite(ls_tmp);
						}

						if(al_idState == EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(
								    (String)llhm_actual.get(IdentificadoresCommon.NOMBRE_PROCESO)
								);

							if(StringUtils.isValidString(ls_tmp))
							{
								Object[] loa_arg;

								loa_arg        = new String[1];
								loa_arg[0]     = ls_tmp;

								ltt_turno.setMotivoNoTramite(
								    getMessages().getString(MessagesKeys.IMPRESION_DOCUMENTOS_PROCESO, loa_arg)
								);
							}
						}

						if(lb_etapaValidaEntregaCorrespondencia)
						{
							ltt_turno.setTipificacionTurno(IdentificadoresCommon.NORMAL);

							TurnoHistoriaDAO lotad_tad;
							StringBuilder    lsb_tmp;
							TurnoDAO         ltd_DAO;
							ltd_DAO     = DaoCreator.getTurnoDAO(ldm_manager);

							lsb_tmp     = new StringBuilder();

							lotad_tad = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

							Collection<Aprobacion> lc_turnosDerivados;

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
																	if(StringUtils.isValidString(lsb_tmp.toString()))
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
									ltt_turno.setTurnosAsociados(ls_turnosCertificados);
							}
						}

						lctt_result.add(ltt_turno);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetailDelivery", lb2be_e);

			throw lb2be_e;
		}
		catch(SQLException lsqle_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findDetailDelivery", lsqle_e);

			throw new B2BException(lsqle_e.getLocalizedMessage());
		}
		finally
		{
			ldm_manager.close();
		}

		if(!CollectionUtils.isValidCollection(lctt_result))
			lctt_result = null;

		return lctt_result;
	}

	/**
	 * Find inbox by turno nir.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteCantidad> findInboxByTurnoNir(String as_idTurno, String as_nir)
	    throws B2BException
	{
		Collection<TramiteCantidad> lc_result;
		DAOManager                  ldm_manager;

		lc_result       = new ArrayList<TramiteCantidad>();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<Etapa> lc_data;

			lc_data = findEntrega(ldm_manager);

			if(CollectionUtils.isValidCollection(lc_data))
			{
				for(Etapa le_etapaActual : lc_data)
				{
					List<Map<String, Object>> ll_data;
					Map<Integer, Object>      lhmpCriterios;
					String                    ls_idSolicitud;

					lhmpCriterios      = new LinkedHashMap<Integer, Object>();
					ls_idSolicitud     = null;

					lhmpCriterios.put(Integer.valueOf(1), NumericUtils.getLongWrapper(le_etapaActual.getIdEtapa()));

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

					ll_data = DaoCreator.getUtilDAO(ldm_manager)
							                .getCustonQueryEntrega(
							    ConsultasUtilidades.CS_CANTIDAD_POR_ENTREGA, as_idTurno, ls_idSolicitud, lhmpCriterios
							);

					if(CollectionUtils.isValidCollection(ll_data))
					{
						Iterator<Map<String, Object>> li_consulta;

						li_consulta = ll_data.iterator();

						while(li_consulta.hasNext())
						{
							Map<String, Object> llhm_actual;

							llhm_actual = li_consulta.next();

							if(llhm_actual != null)
							{
								String ls_cantidad;

								ls_cantidad = llhm_actual.get(IdentificadoresCommon.CANTIDAD).toString();

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
				}
			}
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

		if(!CollectionUtils.isValidCollection(lc_result))
			lc_result = null;

		return lc_result;
	}

	/**
	 * Obtiene los turnos con etapa ASG.
	 *
	 * @param atc_tc objeto del cual se extraerán los datos necesarios para la consulta
	 * @return Collection<TramiteCantidad> con la informacion de bd
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteCantidad> findInboxByTurnoNirCorrespondencia(TramiteCantidad atc_tc)
	    throws B2BException
	{
		DAOManager                  ldm_manager;
		Collection<TramiteCantidad> lctc_tramitesCantidad;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lctc_tramitesCantidad     = null;

		try
		{
			if(atc_tc != null)
			{
				long ll_idEtapa;

				ll_idEtapa = NumericUtils.getLong(atc_tc.getIdEtapa());

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_IMPRESION_DOCUMENTOS_PARA_ENTREGA_CORRESPONDENCIA)
				{
					String ls_constante;
					ls_constante = ConstanteCommon.ETAPAS_VALIDAS_ENTREGA_CORRESPONDENCIA;

					if(StringUtils.isValidString(ls_constante))
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(ldm_manager).findById(ls_constante);

						if(lc_constante != null)
						{
							String ls_caracter;

							ls_caracter = lc_constante.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
							{
								int      li_tam;
								String[] lsa_codigos;

								lsa_codigos     = ls_caracter.split(",");
								li_tam          = lsa_codigos.length;

								if(li_tam > 0)
								{
									String        ls_turno;
									String        ls_nir;
									TurnoHistoria lth_tmp;

									ls_turno     = atc_tc.getIdTurno();
									ls_nir       = atc_tc.getNir();
									lth_tmp      = null;

									if(StringUtils.isValidString(ls_nir))
										lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
												                .findByNIRBandejaCorrespondencia(ls_nir, lsa_codigos);
									else if(StringUtils.isValidString(ls_turno))
										lth_tmp = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
												                .findByIdTurnoBandejaCorrespondencia(
												    ls_turno, lsa_codigos
												);

									if(lth_tmp != null)
									{
										Object[] loa_args;

										loa_args        = new String[1];
										loa_args[0]     = atc_tc.getUsuario();
										throw new B2BException(
										    ErrorKeys.ERROR_CIRCULO_DEL_TURNO_NO_VALIDO_PARA_EL_USUARIO, loa_args
										);
									}

									for(int li_count = 0; li_count < li_tam; li_count++)
									{
										String ls_codigo;

										ls_codigo = lsa_codigos[li_count];

										if(StringUtils.isValidString(ls_codigo))
										{
											Collection<UsuarioCirculo> luc_usuarioCirculos;

											luc_usuarioCirculos = DaoCreator.getUsuarioCirculoDAO(ldm_manager)
													                            .findByUsuarioActive(
													    atc_tc.getUsuario()
													);

											if(CollectionUtils.isValidCollection(luc_usuarioCirculos))
											{
												Collection<TramiteCantidad> lctc_tmp;
												lctc_tmp = new ArrayList<TramiteCantidad>();

												for(UsuarioCirculo luc_tmp : luc_usuarioCirculos)
												{
													String ls_circulo;
													ls_circulo = luc_tmp.getIdCirculo();
													atc_tc.setIdCirculo(ls_circulo);

													atc_tc.setIdEtapa(NumericUtils.getLongWrapper(ls_codigo));

													if(lctc_tramitesCantidad == null)
														lctc_tramitesCantidad = new ArrayList<TramiteCantidad>();

													lctc_tmp.addAll(
													    DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														              .bandejaImpresionDocumentosCorrespondencia(
														        atc_tc
														    )
													);
												}

												TramiteCantidad ltc_tramiteTMP;

												ltc_tramiteTMP = null;

												if(CollectionUtils.isValidCollection(lctc_tmp))
												{
													int li_cantidad;

													ltc_tramiteTMP     = new TramiteCantidad();
													li_cantidad        = NumericUtils.DEFAULT_INT_VALUE;

													for(TramiteCantidad ltc_tmp : lctc_tmp)
													{
														if(ltc_tmp != null)
														{
															li_cantidad = li_cantidad
																+ NumericUtils.getInt(ltc_tmp.getCantidad());
															ltc_tramiteTMP.setIdEtapa(ltc_tmp.getIdEtapa());
															ltc_tramiteTMP.setNombre(ltc_tmp.getNombre());
														}
													}

													ltc_tramiteTMP.setCantidad(NumericUtils.getInteger(li_cantidad));
												}

												if(ltc_tramiteTMP != null)
													lctc_tramitesCantidad.add(ltc_tramiteTMP);
											}
										}
									}

									if(
									    !CollectionUtils.isValidCollection(lctc_tramitesCantidad)
										    && (StringUtils.isValidString(ls_turno)
										    || StringUtils.isValidString(ls_nir))
									)
									{
										Object[] loa_args;

										loa_args        = new String[1];
										loa_args[0]     = atc_tc.getUsuario();
										throw new B2BException(
										    ErrorKeys.ERROR_CIRCULO_DEL_TURNO_NO_VALIDO_PARA_EL_USUARIO, loa_args
										);
									}
								}
							}
						}
					}
				}
				else
					lctc_tramitesCantidad = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
							                              .bandejaImpresionDocumentosCorrespondencia(atc_tc);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findInboxByTurnoNirCorrespondencia", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctc_tramitesCantidad;
	}

	/**
	 * Find matriculas by solicitud circulo.
	 *
	 * @param asm_param de asm param
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findMatriculasBySolicitudCirculo(
	    SolicitudMatricula asm_param, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<SolicitudMatricula> ldp_datos;

		ldp_datos = null;

		if(asm_param != null)
		{
			try
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(asm_param.getIdSolicitud());

				if(ls_solicitud != null)
				{
					String ls_idProceso;

					ls_idProceso = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());

					if(ls_idProceso.equals(ProcesoCommon.ID_PROCESO_1))
					{
						SolicitudMatricula lsm_matricula;

						lsm_matricula = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
								                      .findByTurnoCertificado(asm_param, false);

						if(lsm_matricula != null)
						{
							ldp_datos = new LinkedList<SolicitudMatricula>();

							ldp_datos.add(lsm_matricula);
						}
					}
					else
						ldp_datos = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
								                  .findByIdSolicitudCirculoEntrega(
								    asm_param.getIdSolicitud(), asm_param.getIdCirculo()
								);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("findMatriculasBySolicitudCirculo", lb2be_e);

				throw lb2be_e;
			}
		}

		return ldp_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar
	 * matriculas.
	 *
	 * @param asm_param            Objeto SolicitudMatricula para extraer el id solicitud y el id
	 *            circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findMatriculasBySolicitudCirculo(SolicitudMatricula asm_param)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = findMatriculasBySolicitudCirculo(asm_param, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findMatriculasBySolicitudCirculo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar
	 * matriculas por solicitud, turno y circulo.
	 *
	 * @param asm_parametros            Objeto SolicitudMatricula para extraer el id solicitud y el id
	 *            circulo
	 * @return Collección de SolicitudMatricula que contiene los datos que cumplen
	 *         con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudMatricula> findMatriculasBySolicitudCirculoTurno(
	    SolicitudMatricula asm_parametros
	)
	    throws B2BException
	{
		DAOManager                     ldm_manager;
		Collection<SolicitudMatricula> ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findByIdSolicitudCirculoTurno(asm_parametros);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByIdSolicitudCirculoTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar las
	 * personas entrega pertenecientes a determinada solicitud.
	 *
	 * @param ape_data            Objeto contenedor de filtros para la busqueda
	 * @return Coleccion con registros resultantes de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<PersonaEntrega> findPersonaEntregaBySolicitud(PersonaEntrega ape_data)
	    throws B2BException
	{
		DAOManager                 ldm_manager;
		Collection<PersonaEntrega> lcpe_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcpe_datos      = null;

		try
		{
			lcpe_datos = DaoCreator.getPersonaEntregaDAO(ldm_manager).findBySolicitud(ape_data);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findPersonaEntregaBySolicitud", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcpe_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar la
	 * solicitud por medio de su ID.
	 *
	 * @param at_turno            Objeto Solicitud para extraer información del id solicitud
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Solicitud findSolicitudById(Solicitud at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getSolicitudDAO(ldm_manager).findById(at_turno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findSolicitudById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para hallar la
	 * solicitud del interviniente por la solicitud.
	 *
	 * @param asi_parametros            Objeto SolicitudInterviniente para obtener el Id solicitud
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<SolicitudInterviniente> findSolicitudIntervinienteBySolicitud(
	    SolicitudInterviniente asi_parametros, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<SolicitudInterviniente> lcsi_datos;

		lcsi_datos = null;

		if(asi_parametros != null)
		{
			try
			{
				String                      ls_idTurno;
				String                      ls_idSolicitud;
				String                      ls_idTurnoHistoria;
				boolean                     lb_notaDevolutiva;
				long                        ll_idEtapa;
				AnotacionPredioCiudadanoDAO loapc_DAO;
				SolicitudIntervinienteDAO   lsid_DAO;
				TurnoHistoriaDAO            lth_DAO;
				TurnoHistoria               lth_turnoHistoria;

				ls_idTurno             = asi_parametros.getIdTurno();
				ls_idSolicitud         = asi_parametros.getIdSolicitud();
				ll_idEtapa             = asi_parametros.getIdEtapa();
				ls_idTurnoHistoria     = asi_parametros.getIdTurnoHistoria();
				lb_notaDevolutiva      = false;
				loapc_DAO              = DaoCreator.getAnotacionPredioCiudadanoDAO(adm_manager);
				lsid_DAO               = DaoCreator.getSolicitudIntervinienteDAO(adm_manager);
				lth_DAO                = DaoCreator.getTurnoHistoriaDAO(adm_manager);
				lth_turnoHistoria      = lth_DAO.findById(
					    new TurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria))
					);

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_TRAMITES)
				{
					if(lth_turnoHistoria != null)
						lb_notaDevolutiva = StringUtils.getStringNotNull(lth_turnoHistoria.getCalificacion())
								                           .contains("NOTA DEVOLUTIVA");
				}

				if((ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA) || lb_notaDevolutiva)
				{
					if(StringUtils.isValidString(ls_idSolicitud))
					{
						Solicitud             ls_solicitudPrincipal;
						Solicitud             ls_vinculadoRecuros;
						String                ls_procesoPrincipal;
						Collection<Solicitud> lcs_solicitudesAsociadas;
						boolean               lb_proceso47o48;

						lcs_solicitudesAsociadas     = obtenerSolicitudesVinculadas(ls_idSolicitud, false, adm_manager);
						lb_proceso47o48              = false;
						ls_vinculadoRecuros          = null;
						ls_solicitudPrincipal        = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);
						ls_procesoPrincipal          = (ls_solicitudPrincipal != null)
							? ls_solicitudPrincipal.getIdProceso() : null;

						if(
						    (StringUtils.isValidString(ls_procesoPrincipal))
							    && ls_procesoPrincipal.equals(ProcesoCommon.ID_PROCESO_3)
						)
						{
							if(lth_turnoHistoria != null)
							{
								DocumentosSalida lds_ds;

								lds_ds = DaoCreator.getDocumentosSalidaDAO(adm_manager)
										               .findById(
										    NumericUtils.getLong(lth_turnoHistoria.getIdDocumentoSalida())
										);

								if(lds_ds != null)
								{
									String ls_personaNotificacion;

									ls_personaNotificacion = lds_ds.getIdPersonaNotificacion();

									if(StringUtils.isValidString(ls_personaNotificacion))
									{
										PersonaNotificacion lpn_personaNotificacion;

										lpn_personaNotificacion = DaoCreator.getPersonaNotificacionDAO(adm_manager)
												                                .findById(ls_personaNotificacion);

										if(lpn_personaNotificacion != null)
										{
											String ls_idPersona;

											ls_idPersona = lpn_personaNotificacion.getIdPersona();

											if(StringUtils.isValidString(ls_idPersona))
											{
												Persona                lp_persona;
												SolicitudInterviniente lsi_si;
												TipoRecepcion          ltr_tr;

												lsi_si         = new SolicitudInterviniente();
												lcsi_datos     = new ArrayList<SolicitudInterviniente>();
												lp_persona     = DaoCreator.getPersonaDAO(adm_manager)
														                       .findById(ls_idPersona);
												ltr_tr         = DaoCreator.getTipoRecepcionDAO(adm_manager)
														                       .findById(
														    new TipoRecepcion(
														        ls_solicitudPrincipal.getIdTipoRecepcion()
														    )
														);

												if(lp_persona != null)
													lsi_si.setPersona(lp_persona);

												lsi_si.setIdSolicitud(ls_idSolicitud);
												lsi_si.setIdPersona(ls_idPersona);
												lsi_si.setRolPersona("");    //TODO que rol se coloca?

												if(ltr_tr != null)
													lsi_si.setIdAutorizacionNotificacion(ltr_tr.getNombre());

												lcsi_datos.add(lsi_si);
											}
										}
									}
								}
							}
						}
						else
						{
							if(CollectionUtils.isValidCollection(lcs_solicitudesAsociadas))
							{
								Iterator<Solicitud> lis_iterator;

								lis_iterator = lcs_solicitudesAsociadas.iterator();

								while(lis_iterator.hasNext() && !lb_proceso47o48)
								{
									Solicitud ls_actual;

									ls_actual = lis_iterator.next();

									if(ls_actual != null)
									{
										String ls_procesoVinculado;

										ls_vinculadoRecuros     = ls_actual;
										ls_procesoVinculado     = ls_actual.getIdProceso();

										if(StringUtils.isValidString(ls_procesoVinculado))
											lb_proceso47o48 = (ls_procesoVinculado.equalsIgnoreCase(
												    ProcesoCommon.ID_PROCESO_47
												) || ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48));
									}
								}
							}
						}

						lcsi_datos = lsid_DAO.findBySolicitudPersona(
							    (lb_proceso47o48 && (ls_vinculadoRecuros != null))
							    ? new SolicitudInterviniente(ls_vinculadoRecuros.getIdSolicitud()) : asi_parametros
							);
					}
				}
				else
				{
					if(StringUtils.isValidString(ls_idTurno))
					{
						Collection<AnotacionPredioDireccion> lcap_datos;
						AnotacionPredioDireccion             loapd_apd;
						loapd_apd = new AnotacionPredioDireccion();

						loapd_apd.setRadicacion(ls_idTurno);
						lcap_datos = DaoCreator.getAnotacionPredioDAO(adm_manager).findByRadicacion(loapd_apd, null);

						if(CollectionUtils.isValidCollection(lcap_datos))
						{
							lcsi_datos = new ArrayList<SolicitudInterviniente>();

							for(AnotacionPredioDireccion loap_tmp : lcap_datos)
							{
								if(loap_tmp != null)
								{
									long                             ll_idMatricula;
									String                           ls_idCirculo;
									RegistroCalificacion             lorc_tmp;
									Collection<RegistroCalificacion> lcrc_rc;
									SolicitudInterviniente           losi_si;
									Persona                          lop_tmp;

									lorc_tmp           = new RegistroCalificacion();
									ll_idMatricula     = loap_tmp.getIdMatricula();
									ls_idCirculo       = loap_tmp.getIdCirculo();

									lorc_tmp.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
									lorc_tmp.setIdCirculo(ls_idCirculo);
									lorc_tmp.setIdAnotacion(NumericUtils.getLongWrapper(loap_tmp.getIdAnotacion()));

									lcrc_rc = loapc_DAO.findDataPredioAnotacion(lorc_tmp, true);

									if(CollectionUtils.isValidCollection(lcrc_rc))
									{
										for(RegistroCalificacion lorcc_tmp : lcrc_rc)
										{
											if(lorcc_tmp != null)
											{
												{
													Iterator<SolicitudInterviniente> lisi_si;
													boolean                          lb_add;
													String                           ls_persona;

													lisi_si        = lcsi_datos.iterator();
													lb_add         = true;
													ls_persona     = lorcc_tmp.getIdPersona();

													while(lisi_si.hasNext() && lb_add)
													{
														SolicitudInterviniente losi_tmp;

														losi_tmp = lisi_si.next();

														if(losi_tmp != null)
															lb_add = !ls_persona.equalsIgnoreCase(
																    losi_tmp.getIdPersona()
																);
													}

													if(lb_add)
													{
														lop_tmp     = new Persona();
														losi_si     = new SolicitudInterviniente();

														losi_si.setRolPersona(
														    StringUtils.getStringNotNull(lorcc_tmp.getRolPersona())
															               .toUpperCase()
														);
														lop_tmp.setNombreCompleto(lorcc_tmp.getNombrePersona());
														lop_tmp.setRazonSocial(lorcc_tmp.getRazonSocial());
														lop_tmp.setIdPersona(lorcc_tmp.getIdPersona());
														lop_tmp.setIdDocumentoTipo(
														    StringUtils.getStringNotNull(lorcc_tmp.getTipoDoc())
														);
														lop_tmp.setNumeroDocumento(lorcc_tmp.getDocumento());

														losi_si.setPersona(lop_tmp);
														losi_si.setIdSolicitud(ls_idSolicitud);
														losi_si.setIdPersona(ls_persona);
														lcsi_datos.add(losi_si);
													}
												}
											}
										}
									}
								}
							}
						}
						else
						{
							if(StringUtils.isValidString(ls_idSolicitud))
								lcsi_datos = lsid_DAO.findBySolicitudPersona(asi_parametros);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("findSolicitudIntervinienteBySolicitud", lb2be_e);

				throw lb2be_e;
			}
		}

		return lcsi_datos;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para encontrar el
	 * Turno por medio de su Id.
	 *
	 * @param at_turno            Objeto Turno para extraer el Id turno
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Turno findTurnoById(Turno at_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Turno      ldp_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldp_datos       = null;

		try
		{
			ldp_datos = DaoCreator.getTurnoDAO(ldm_manager).findById(at_turno);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findTurnoById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldp_datos;
	}

	/**
	 * Método para poder generar un archivo comprimido en zip de las imagenes que
	 * tenga el turno.
	 *
	 * @param ads_param            Objeto para poder extraer el id turno para hallar la consulta
	 * @param ab_activos de ab activos
	 * @param adm_manager de adm manager
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarArchivoZip(
	    DocumentosSalida ads_param, boolean ab_activos, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[]                       lba_zipFinal;
		Collection<DocumentosSalida> ldp_datos;

		lba_zipFinal     = null;
		ldp_datos        = null;

		try
		{
			if(ads_param != null)
			{
				String ls_turno;

				ls_turno = ads_param.getIdTurno();

				if(StringUtils.isValidString(ls_turno))
				{
					Turno lt_turno;
					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(ls_turno);

					if(lt_turno != null)
					{
						String ls_idProceso;
						ls_idProceso = lt_turno.getIdProceso();

						if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42))
						{
							Solicitud ls_solicitud;
							ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lt_turno.getIdSolicitud());

							if(ls_solicitud != null)
							{
								String ls_turnoAnterior;
								ls_turnoAnterior = ls_solicitud.getIdTurnoAnt();

								if(StringUtils.isValidString(ls_turnoAnterior))
									ads_param.setIdTurno(ls_turnoAnterior);
							}
						}
					}
				}
			}

			ldp_datos = DaoCreator.getDocumentosSalidaDAO(adm_manager).findByIdTurnoEstadoA(ads_param, ab_activos);

			if(CollectionUtils.isValidCollection(ldp_datos))
			{
				Collection<ZipEntryUtil> lczeu_zip;
				int                      li_contador;
				ImagenesDAO              loid_id;

				lczeu_zip       = new ArrayList<ZipEntryUtil>();
				li_contador     = 1;
				loid_id         = DaoCreator.getImagenesDAO(adm_manager);

				for(DocumentosSalida lds_actual : ldp_datos)
				{
					Imagenes li_detalleImage;

					li_detalleImage = loid_id.findById(NumericUtils.getLong(lds_actual.getIdImagen()));

					if(li_detalleImage != null)
					{
						byte[] lba_imagenBlob;

						lba_imagenBlob = li_detalleImage.getImagenBlob();

						if(lba_imagenBlob != null)
						{
							ZipEntryUtil lzeu_pdf;

							lzeu_pdf = new ZipEntryUtil(
								    lds_actual.getIdTurno() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
								    + lds_actual.getTipo() + IdentificadoresCommon.SIMBOLO_GUION_BAJO + (li_contador++)
								    + IdentificadoresCommon.PUNTO + li_detalleImage.getTipoArchivo(),
								    new ByteArrayInputStream(lba_imagenBlob)
								);
							lczeu_zip.add(lzeu_pdf);
						}
					}
				}

				{
					Collection<TurnoDerivado> lctd_turnoDerivado;
					TurnoDerivado             ltd_turnosDerivados;

					ltd_turnosDerivados = new TurnoDerivado();
					ltd_turnosDerivados.setIdTurnoPadre(ads_param.getIdTurno());

					lctd_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(adm_manager)
							                           .findByIdTurnoPadre(ltd_turnosDerivados);

					if(CollectionUtils.isValidCollection(lctd_turnoDerivado))
					{
						for(TurnoDerivado ltd_actual : lctd_turnoDerivado)
						{
							if(!ltd_actual.isIndVinculado())
							{
								Collection<DocumentosSalida> ldp_docsDerivados;
								DocumentosSalida             lds_derivado;

								lds_derivado = new DocumentosSalida();

								lds_derivado.setIdTurno(ltd_actual.getIdTurnoHijo());

								ldp_docsDerivados = DaoCreator.getDocumentosSalidaDAO(adm_manager)
										                          .findByIdTurnoEstadoA(lds_derivado, ab_activos);

								if(CollectionUtils.isValidCollection(ldp_docsDerivados))
								{
									for(DocumentosSalida lds_actual : ldp_docsDerivados)
									{
										Imagenes loi_detalleImage;

										loi_detalleImage = new Imagenes();

										loi_detalleImage.setIdImagen(NumericUtils.getInt(lds_actual.getIdImagen()));

										loi_detalleImage = loid_id.findById(loi_detalleImage);

										if(loi_detalleImage != null)
										{
											ZipEntryUtil lzeu_pdf;

											lzeu_pdf = new ZipEntryUtil(
												    lds_actual.getIdTurno() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
												    + lds_actual.getTipo() + IdentificadoresCommon.SIMBOLO_GUION_BAJO
												    + (li_contador++) + IdentificadoresCommon.PUNTO
												    + loi_detalleImage.getTipoArchivo(),
												    new ByteArrayInputStream(loi_detalleImage.getImagenBlob())
												);
											lczeu_zip.add(lzeu_pdf);
										}
									}
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lczeu_zip))
					lba_zipFinal = ZipUtils.generateZip(lczeu_zip);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarArchivoZip", lb2be_e);

			throw lb2be_e;
		}

		return lba_zipFinal;
	}

	/**
	 * Método para poder generar un archivo comprimido en zip de las imagenes que
	 * tenga el turno.
	 *
	 * @param ads_param            Objeto para poder extraer el id turno para hallar la consulta
	 * @param ab_activos de ab activos
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarArchivoZip(DocumentosSalida ads_param, boolean ab_activos)
	    throws B2BException
	{
		DAOManager ldm_manager;
		byte[]     lba_zipFinal;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lba_zipFinal     = null;

		try
		{
			lba_zipFinal = generarArchivoZip(ads_param, ab_activos, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarArchivoZip", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_zipFinal;
	}

	/**
	 * Metodo para poder generar zip de entregas relaciones.
	 *
	 * @param aba_ba El objeto aprobación donde se extrae la información necesaria para el zip
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see byte[]
	 */
	public synchronized byte[] generarArchivoZipEntregaRelacion(Map<String, byte[]> aba_ba)
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
	 * Generar documento entrega.
	 *
	 * @param aefg_param de aefg param
	 * @param as_idTurno de as id turno
	 * @param ap_personaEntrega de ap persona entrega
	 * @param ab_interviene de ab interviene
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public synchronized byte[] generarDocumentoEntrega(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_personaEntrega, boolean ab_interviene,
	    String as_idUsuario, String as_ipRemota
	)
	    throws Exception
	{
		byte[] lba_documento;

		lba_documento = null;

		if(
		    (aefg_param != null) && StringUtils.isValidString(as_idTurno) && (ap_personaEntrega != null)
			    && StringUtils.isValidString(as_idUsuario)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lba_documento = generarDocumentoEntrega(
					    aefg_param, as_idTurno, ap_personaEntrega,
					    DaoCreator.getDocumentosSalidaDAO(ldm_manager)
						              .findByIdTurnoEstadoA(new DocumentosSalida(as_idTurno), true), as_idUsuario, false,
					    ldm_manager
					);

				if(lba_documento != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(as_idTurno);

					if(lth_turnoHistoria != null)
					{
						DocumentosSalida    lds_documentoSalida;
						DocumentosSalida    lds_temp;
						DocumentosSalidaDAO ldsd_DAO;
						String              ls_idSolicitud;

						lds_documentoSalida     = new DocumentosSalida(as_idTurno);
						lds_temp                = null;
						ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
						ls_idSolicitud          = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							lds_documentoSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
							);
							lds_documentoSalida.setIdSolicitud(ls_idSolicitud);

							{
								PersonaEntregaDAO lped_DAO;
								PersonaEntrega    le_persona;

								lped_DAO       = DaoCreator.getPersonaEntregaDAO(ldm_manager);
								le_persona     = lped_DAO.findByIdTurno(as_idTurno);

								if(le_persona != null)
									lds_documentoSalida.setDestinatario(le_persona.getIdPersona());
								else
								{
									if(ab_interviene)
									{
										Persona lp_persona;

										lp_persona = DaoCreator.getPersonaDAO(ldm_manager)
												                   .findById(ap_personaEntrega.getIdPersona());

										if(lp_persona != null)
										{
											le_persona = new PersonaEntrega();

											le_persona.setIdPersona(lp_persona.getIdPersona());
											le_persona.setFechaEntrega(new Date());
											le_persona.setIdCalidadPersonaEntrega(
											    CalidadSolicitanteCommon.INTERVINIENTE
											);
											le_persona.setIdTurno(as_idTurno);
											le_persona.setIdSolicitud(ls_idSolicitud);
											le_persona.setIdUsuarioCreacion(as_idUsuario);
											le_persona.setIpCreacion(as_ipRemota);

											lped_DAO.insert(le_persona);
										}
										else
										{
											Object[] loa_args;

											loa_args        = new String[1];
											loa_args[0]     = as_idTurno;
											throw new B2BException(
											    ErrorKeys.ERROR_NO_SE_ENCONTRO_PERSONA_ENTREGA, loa_args
											);
										}
									}
									else
									{
										Object[] loa_args;

										loa_args        = new String[1];
										loa_args[0]     = as_idTurno;
										throw new B2BException(
										    ErrorKeys.ERROR_NO_SE_ENCONTRO_PERSONA_ENTREGA, loa_args
										);
									}
								}
							}

							lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
							lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICACION);
							lds_documentoSalida.setEstado(EstadoCommon.ENTREGA);
							lds_documentoSalida.setDocumentoAutomatico(EstadoCommon.S);
							lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
							lds_documentoSalida.setDocumentoFinal(EstadoCommon.N);

							lds_temp = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(lds_documentoSalida);

							{
								boolean lb_existe;
								Long    ll_idImagen;

								lb_existe       = lds_temp != null;
								ll_idImagen     = null;

								{
									Imagenes li_imagen;

									li_imagen = new Imagenes();

									li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagen.setImagenBlob(lba_documento);

									if(!lb_existe)
									{
										li_imagen.setIdUsuarioCreacion(as_idTurno);
										li_imagen.setIpCreacion(as_ipRemota);
										ll_idImagen = NumericUtils.getLongWrapper(
											    DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, true)
											);
										lds_documentoSalida.setIdImagen(ll_idImagen);
										lds_documentoSalida.setIdUsuarioCreacion(as_idUsuario);
										lds_documentoSalida.setIpCreacion(as_ipRemota);
										ldsd_DAO.insertOrUpdate(lds_documentoSalida, true);
									}
									else
									{
										li_imagen.setIdUsuarioModificacion(as_idTurno);
										li_imagen.setIpModificacion(as_ipRemota);
										li_imagen.setIdImagen(NumericUtils.getLong(ll_idImagen));
										DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, false);
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

				clh_LOGGER.error("generarDocumentoEntrega", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_documento;
	}

	/**
	 * Generar documento reimpresion.
	 *
	 * @param aefg_param de aefg param
	 * @param as_idTurno de as id turno
	 * @param ap_persona de ap persona
	 * @param acds_docs de acds docs
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public synchronized byte[] generarDocumentoReimpresion(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_persona, Collection<DocumentosSalida> acds_docs,
	    String as_idUsuario, String as_ipRemota
	)
	    throws Exception
	{
		byte[] lba_documento;

		lba_documento = null;

		if(
		    (aefg_param != null) && StringUtils.isValidString(as_idTurno) && (ap_persona != null)
			    && StringUtils.isValidString(as_idUsuario) && CollectionUtils.isValidCollection(acds_docs)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				lba_documento = generarDocumentoEntrega(
					    aefg_param, as_idTurno, ap_persona, acds_docs, as_idUsuario, true, ldm_manager
					);

				if(lba_documento != null)
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(as_idTurno);

					if(lth_turnoHistoria != null)
					{
						DocumentosSalida    lds_documentoSalida;
						DocumentosSalida    lds_temp;
						DocumentosSalidaDAO ldsd_DAO;
						String              ls_idSolicitud;

						lds_documentoSalida     = new DocumentosSalida(as_idTurno);
						lds_temp                = null;
						ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
						ls_idSolicitud          = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							lds_documentoSalida.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
							);
							lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
						}

						lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.COMUNICACION);
						lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICACION);
						lds_documentoSalida.setEstado(EstadoCommon.ENTREGA);
						lds_documentoSalida.setDocumentoAutomatico(EstadoCommon.S);
						lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
						lds_documentoSalida.setDocumentoFinal(EstadoCommon.N);

						lds_temp = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(lds_documentoSalida);

						{
							boolean lb_existe;
							Long    ll_idImagen;

							lb_existe       = lds_temp != null;
							ll_idImagen     = null;

							{
								Imagenes li_imagen;

								li_imagen = new Imagenes();

								li_imagen.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
								li_imagen.setImagenBlob(lba_documento);

								if(!lb_existe)
								{
									li_imagen.setIdUsuarioCreacion(as_idTurno);
									li_imagen.setIpCreacion(as_ipRemota);
									ll_idImagen = NumericUtils.getLongWrapper(
										    DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, true)
										);
									lds_documentoSalida.setIdImagen(ll_idImagen);
									lds_documentoSalida.setIdUsuarioCreacion(as_idUsuario);
									lds_documentoSalida.setIpCreacion(as_ipRemota);
									ldsd_DAO.insertOrUpdate(lds_documentoSalida, true);
								}
								else
								{
									li_imagen.setIdUsuarioModificacion(as_idTurno);
									li_imagen.setIpModificacion(as_ipRemota);
									li_imagen.setIdImagen(NumericUtils.getLong(ll_idImagen));
									DaoCreator.getImagenesDAO(ldm_manager).insertOrUpdate(li_imagen, false);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarDocumentoEntrega", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lba_documento;
	}

	/**
	 * Ordena y procesa los tramites enviados a aprobar llamando al proceso de generar el
	 * documento PDF por cada circulo diferente que exista en la colección de aprobaciones.
	 *
	 * @param actt_tramitesTurno Colección contenedora de todos los tramites a aprobar para
	 * generar el consecutivo relación
	 * @param ab_firmar Indicar si los tramites seleccionados se van a firmar
	 * @param as_userId Id del usuario que ejecuta el proceso
	 * @param as_remoteIp Dirección IP del cliente que ejecuta el proceso
	 * @return HashMap con los consecutivos y documentos PDF generados
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws Exception Señala que se ha producido una excepción
	 * @see Map
	 */
	public synchronized Map<String, byte[]> generarDocumentoRelacionEntrega(
	    Collection<TramiteTurno> actt_tramitesTurno, boolean ab_firmar, String as_userId, String as_remoteIp
	)
	    throws B2BException, Exception
	{
		Map<String, byte[]> lm_documentos;
		DAOManager          ldm_manager;

		lm_documentos     = new LinkedHashMap<String, byte[]>();
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<TramiteTurno> lctt_entregasSeleccionadas;
			Collection<String>       lcs_circulos;

			lctt_entregasSeleccionadas     = new LinkedList<TramiteTurno>();
			lcs_circulos                   = new LinkedList<String>();

			if(CollectionUtils.isValidCollection(actt_tramitesTurno))
			{
				TurnoHistoriaDAO lth_DAO;

				lth_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				for(TramiteTurno ltt_tramiteTurnoTMP : actt_tramitesTurno)
				{
					if(ltt_tramiteTurnoTMP != null)
					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = lth_DAO.findById(ltt_tramiteTurnoTMP.getIdTurnoHistoria());

						if(lth_turnoHistoria != null)
						{
							if(ltt_tramiteTurnoTMP.isGenerarRelacion())
							{
								Turno lt_turno;

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(
									    ltt_tramiteTurnoTMP.getIdTurno()
									);

								lth_turnoHistoria.setGenerarRelacion(EstadoCommon.SI);

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

									lctt_entregasSeleccionadas.add(ltt_tramiteTurnoTMP);
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
			    CollectionUtils.isValidCollection(lctt_entregasSeleccionadas)
				    && CollectionUtils.isValidCollection(lcs_circulos)
			)
			{
				for(String ls_circuloTurno : lcs_circulos)
				{
					if(StringUtils.isValidString(ls_circuloTurno))
					{
						Collection<TramiteTurno> lctt_temp;
						lctt_temp = new LinkedList<TramiteTurno>();

						for(TramiteTurno ltt_tramiteTurnoTMP : lctt_entregasSeleccionadas)
						{
							if(ltt_tramiteTurnoTMP != null)
							{
								Turno lt_turno;
								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(
									    ltt_tramiteTurnoTMP.getIdTurno()
									);

								if(lt_turno != null)
								{
									String ls_temp;
									ls_temp = StringUtils.getStringNotNull(lt_turno.getIdCirculo());

									if(ls_temp.equalsIgnoreCase(ls_circuloTurno))
										lctt_temp.add(ltt_tramiteTurnoTMP);
								}
							}
						}

						if(CollectionUtils.isValidCollection(lctt_temp))
						{
							lm_documentos.putAll(
							    crearPdfAprobacionEntrega(
							        lctt_temp, ls_circuloTurno, ab_firmar, as_userId, as_remoteIp, ldm_manager
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

								long ll_idImagen;
								ll_idImagen = NumericUtils.DEFAULT_LONG_VALUE;

								if(lba_documentoRelacion != null)
								{
									ImagenesDAO li_DAO;
									Imagenes    li_imagenRelacion;

									li_DAO                = DaoCreator.getImagenesDAO(ldm_manager);
									li_imagenRelacion     = new Imagenes();

									li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
									li_imagenRelacion.setIdUsuarioCreacion(as_userId);
									li_imagenRelacion.setIpCreacion(as_remoteIp);
									li_imagenRelacion.setImagenBlob(lba_documentoRelacion);

									ll_idImagen = li_DAO.insertOrUpdate(li_imagenRelacion, true);

									if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
										throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);
								}

								for(TramiteTurno ltt_tramiteTurnoTMP : lctt_entregasSeleccionadas)
								{
									if(ltt_tramiteTurnoTMP != null)
									{
										TurnoHistoria lth_turnoHistoria;

										lth_turnoHistoria = new TurnoHistoria();

										lth_turnoHistoria.setIdTurno(ltt_tramiteTurnoTMP.getIdTurno());
										lth_turnoHistoria.setIdEtapa(
										    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_ENTREGA_MEDIDAS_CAUTELARES)
										);
										lth_turnoHistoria.setNir(ltt_tramiteTurnoTMP.getNir());

										salvarImpresionDocumentosCorrespondencia(
										    lth_turnoHistoria, ls_numeroRelacion, ll_idImagen, ldm_manager, as_remoteIp,
										    as_userId
										);
									}
								}
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

			clh_LOGGER.error("generarDocumentoRelacionEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lm_documentos;
	}

	/**
	 * Método que consume el envio al OWCC.
	 *
	 * @param as_idTurno <code>String</code> que contiene el turno
	 *     con el cual se realizará la búsqueda del documento a enviar
	 * @param aofdto_firma de aofdto firma
	 * @param as_idUsuario <code>String</code> con el usuario que realiza la acción
	 * @param as_ipRemota <code>String</code> con la ip del usuario que realiza la acción
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarDocumentoOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_ipRemota
	)
	    throws B2BException
	{
		guardarDocumentoOWCC(as_idTurno, aofdto_firma, as_idUsuario, as_ipRemota, false);
	}

	/**
	 * Método que consume el envio al OWCC.
	 *
	 * @param as_idTurno <code>String</code> que contiene el turno
	 *     con el cual se realizará la búsqueda del documento a enviar
	 * @param aofdto_firma de aofdto firma
	 * @param as_idUsuario <code>String</code> con el usuario que realiza la acción
	 * @param as_ipRemota <code>String</code> con la ip del usuario que realiza la acción
	 * @param ab_notificacion con el valor boolean de notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarDocumentoOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_ipRemota,
	    boolean ab_notificacion
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idTurno) && (ab_notificacion || (aofdto_firma != null)))
		{
			DAOManager ldm_bitacora;
			DAOManager ldm_manager;

			ldm_bitacora     = DaoManagerFactory.getDAOManager();
			ldm_manager      = DaoManagerFactory.getDAOManager();

			try
			{
				ConstantesDAO lcd_DAO;
				String        ls_endpoint;

				lcd_DAO         = DaoCreator.getConstantesDAO(ldm_manager);
				ls_endpoint     = lcd_DAO.findString(ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT);

				if(StringUtils.isValidString(ls_endpoint))
				{
					BitacoraProcesoDAO lbpd_bitacora;

					lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

					ldm_bitacora.setAutoCommit(true);

					{
						TurnoHistoria lth_historia;

						lth_historia = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findByIdTurno(as_idTurno);

						if(lth_historia != null)
						{
							DocumentosSalida lds_documento;

							lds_documento = new DocumentosSalida();
							lds_documento.setIdTurnoHistoria(
							    NumericUtils.getInteger(lth_historia.getIdTurnoHistoria())
							);
							lds_documento.setIdTipoDocumental(
							    ab_notificacion ? TipoDocumentalCommon.ACTA_NOTIFICACION
							                    : TipoDocumentalCommon.COMUNICACION
							);
							lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									                      .findByIdTurnoHistoriaTipoDocumental(lds_documento);

							if(lds_documento != null)
							{
								if(!lds_documento.isEnviadoOwcc())
								{
									enviarGestorDocumental(
									    lds_documento, lbpd_bitacora, ls_endpoint, as_idUsuario, as_ipRemota,
									    ldm_manager
									);

									if(!lds_documento.isEnviadoOwcc())
										throw new B2BException(
										    "Ocurrió un error enviando el documento al OWCC, intentelo nuevamente"
										);
									else if(!ab_notificacion)
									{
										String ls_constante;

										ls_constante = lcd_DAO.findString(ConstanteCommon.PAD_DE_FIRMAS_ENDPOINT);

										if(StringUtils.isValidString(ls_constante))
											ClientePadFirmas.eliminarFirma(aofdto_firma, ls_constante);
									}
								}
								else
								{
									String ls_constante;

									ls_constante = lcd_DAO.findString(ConstanteCommon.PAD_DE_FIRMAS_ENDPOINT);

									if(StringUtils.isValidString(ls_constante))
										ClientePadFirmas.eliminarFirma(aofdto_firma, ls_constante);
								}
							}
						}
					}
				}
				else
				{
					Object[] loa_params;

					loa_params        = new String[1];
					loa_params[0]     = ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_params);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_bitacora.setRollbackOnly();
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("guardarDocumentoOWCC", lb2be_e);
				throw lb2be_e;
			}
			finally
			{
				ldm_bitacora.close();
				ldm_manager.close();
			}
		}
	}

	/**
	 * Guarda la información de un interviniente o la actualiza, en caso de ya
	 * existir, para la etapa de entrega.
	 *
	 * @param ar_registro            Objeto contenedor de los datos de contacto del interviniente a
	 *            insertar o actualizar
	 * @param as_userId            Id del usuario que ejecuta el proceso
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta el proceso
	 * @return Objeto registro con los posibles nuevos cambios ocurridos al insertar
	 *         la información en la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized PersonaEntrega salvarDatosIntervininete(
	    Registro ar_registro, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		PersonaEntrega lpe_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpe_return      = null;

		try
		{
			ConstantesDAO lc_DAO;
			Solicitud     ls_solicitud;
			Persona       lp_persona;

			lc_DAO = DaoCreator.getConstantesDAO(ldm_manager);

			if(ar_registro == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			ls_solicitud = ar_registro.getSolicitud();

			if(ls_solicitud == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);

			lp_persona = ar_registro.getPersona();

			if(lp_persona == null)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA);

			PersonaDireccion         lpd_dirCor;
			PersonaDireccion         lpd_dirRe;
			PersonaTelefono          lpt_telFi;
			PersonaTelefono          lpt_telMo;
			PersonaCorreoElectronico lpc_correo;
			boolean                  lb_seleccionadoEsApoderado;
			String                   ls_dirCor;
			String                   ls_dirRe;
			String                   ls_telFi;
			String                   ls_telMo;
			String                   ls_correo1;
			String                   ls_tipoPersona;
			String                   ls_tipoDocumento;

			lpd_dirCor                     = ar_registro.getDireccionCorrespondencia();
			lpd_dirRe                      = ar_registro.getDireccionResidencia();
			lpt_telFi                      = ar_registro.getTelefonoFijo();
			lpt_telMo                      = ar_registro.getTelefonoMovil();
			lpc_correo                     = ar_registro.getPersonaCorreoElectronico();
			ls_dirCor                      = lpd_dirCor.getDireccion();
			ls_dirRe                       = lpd_dirRe.getDireccion();
			ls_telFi                       = lpt_telFi.getTelefono();
			ls_telMo                       = lpt_telMo.getTelefono();
			ls_correo1                     = lpc_correo.getCorreoElectronico();
			lb_seleccionadoEsApoderado     = ar_registro.isSeleccionadoEsApoderado();
			ls_tipoPersona                 = lp_persona.getIdTipoPersona();
			ls_tipoDocumento               = lp_persona.getIdDocumentoTipo();

			if(!StringUtils.isValidString(ls_dirCor))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_CORRESPONDENCIA);

			if(!StringUtils.isValidString(ls_dirRe))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIRECCION_RESIDENCIA);

			if(!StringUtils.isValidString(ls_telFi))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO);

			if(!StringUtils.isValidString(ls_telMo))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL);

			if(!lb_seleccionadoEsApoderado)
			{
				if(!StringUtils.isValidString(ls_correo1))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
			}

			if(!StringUtils.isValidString(ls_tipoPersona))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

			if(!StringUtils.isValidString(ls_tipoDocumento))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

			{
				String ls_documento;
				ls_documento = lp_persona.getNumeroDocumento();

				if(!StringUtils.isValidString(ls_documento))
					throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);

				{
					Constantes lc_constante;
					String     ls_caracter;

					lc_constante     = new Constantes();
					ls_caracter      = ConstanteCommon.CARACTERES_ESPECIALES;
					lc_constante.setIdConstante(ls_caracter);

					lc_constante = lc_DAO.findById(lc_constante);

					if(lc_constante != null)
					{
						String  ls_constantes;
						boolean lb_valido;

						ls_constantes     = lc_constante.getCaracter();
						lb_valido         = false;

						if(!StringUtils.isValidString(ls_constantes))
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ls_caracter;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lb_valido = StringUtils.isValidCharacters(ls_documento, ls_constantes);

						if(!lb_valido)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
					}
				}

				String ls_calidadActua;
				ls_calidadActua = ls_solicitud.getIdCalidadSolicitante();

				if(!StringUtils.isValidString(ls_calidadActua))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_INTERESADO);

				if(ls_tipoPersona.equalsIgnoreCase("N"))
				{
					if(
					    !ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
						    && !ls_tipoDocumento.equalsIgnoreCase("-1")
					)
					{
						{
							String ls_nacionalidad;
							ls_nacionalidad = lp_persona.getIdPais();

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							String ls_primerNombre;
							ls_primerNombre = lp_persona.getPrimerNombre();

							if(!StringUtils.isValidString(ls_primerNombre))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
						}

						{
							String ls_primerApellido;
							ls_primerApellido = lp_persona.getPrimerApellido();

							if(!StringUtils.isValidString(ls_primerApellido))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
						}

						{
							String ls_genero;
							ls_genero = lp_persona.getIdNaturalGenero();

							if(!StringUtils.isValidString(ls_genero))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
				}

				if(StringUtils.getStringNotNull(ls_tipoPersona).equalsIgnoreCase(EstadoCommon.J))
				{
					if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					{
						{
							String ls_nacionalidad;
							ls_nacionalidad = lp_persona.getIdPais();

							if(!StringUtils.isValidString(ls_nacionalidad))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
						}

						{
							String ls_razonSocial;
							ls_razonSocial = lp_persona.getRazonSocial();

							if(!StringUtils.isValidString(ls_razonSocial))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
						}
					}
					else
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
				}

				String ls_medioComunicar;
				String ls_medioNotificar;
				String ls_idCorreoCo;
				String ls_idCorreoNo;
				String ls_idDireccionCo;
				String ls_idDireccionNo;
				String ls_idTelefonoCo;
				String ls_idTelefonoNo;

				ls_medioComunicar     = ls_solicitud.getIdAutorizacionComunicacion();
				ls_medioNotificar     = ls_solicitud.getIdAutorizacionNotificacion();
				ls_idCorreoCo         = null;
				ls_idCorreoNo         = null;
				ls_idDireccionCo      = null;
				ls_idDireccionNo      = null;
				ls_idTelefonoCo       = null;
				ls_idTelefonoNo       = null;

				if(!lb_seleccionadoEsApoderado)
				{
					if(!StringUtils.isValidString(ls_medioComunicar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);

					if(!StringUtils.isValidString(ls_medioNotificar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_NOTIFICAR);

					if(
					    (ls_medioNotificar.equals(MedioNotificarCommon.ORIP)
						    || ls_medioComunicar.equals(MedioNotificarCommon.ORIP))
						    && !(StringUtils.isValidString(ls_telFi) || StringUtils.isValidString(ls_telMo)
						    || StringUtils.isValidString(ls_correo1))
					)
						throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);

					PersonaDireccion lpd_residencia;
					lpd_residencia = ar_registro.getDireccionResidencia();

					if(
					    (ls_medioComunicar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
						    || ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
						    || StringUtils.isValidString(ls_dirRe)) && (lpd_residencia != null)
					)
					{
						if(ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA))
							ls_idDireccionNo = lpd_residencia.getIdDireccion();

						if(ls_medioComunicar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA))
							ls_idDireccionCo = lpd_residencia.getIdDireccion();

						{
							String ls_tipoDireccion;
							ls_tipoDireccion = lpd_residencia.getTipoDireccion();

							if(!StringUtils.isValidString(ls_tipoDireccion))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DIRECCION);
						}

						{
							String ls_tipoEje;
							ls_tipoEje = lpd_residencia.getIdTipoEjePrincipal();

							if(!StringUtils.isValidString(ls_tipoEje))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
						}

						{
							String ls_ejePrincipal;
							ls_ejePrincipal = lpd_residencia.getDatoEjePrincipal();

							if(!StringUtils.isValidString(ls_ejePrincipal))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL);
						}

						{
							String ls_tipoEje1;
							ls_tipoEje1 = lpd_residencia.getIdTipoEjeSecundario();

							if(!StringUtils.isValidString(ls_tipoEje1))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
						}

						{
							String ls_ejeSecundario;
							ls_ejeSecundario = lpd_residencia.getDatoEjeSecundario();

							if(!StringUtils.isValidString(ls_ejeSecundario))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_EJE_SECUNDARIO);
						}

						{
							String ls_pais;
							ls_pais = lpd_residencia.getIdPais();

							if(!StringUtils.isValidString(ls_pais))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
						}

						{
							String ls_departamento;
							ls_departamento = lpd_residencia.getIdDepartamento();

							if(!StringUtils.isValidString(ls_departamento))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_RESIDENCIA);
						}

						{
							String ls_municipio;
							ls_municipio = lpd_residencia.getIdMunicipio();

							if(!StringUtils.isValidString(ls_municipio))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_RESIDENCIA);
						}
					}

					PersonaDireccion lpd_correspondencia;
					lpd_correspondencia = ar_registro.getDireccionCorrespondencia();

					if(
					    (ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
						    || StringUtils.isValidString(ls_dirCor)) && (lpd_correspondencia != null)
					)
					{
						{
							String ls_tipoDireccion;

							ls_tipoDireccion     = lpd_correspondencia.getTipoDireccion();
							ls_idDireccionNo     = lpd_correspondencia.getIdDireccion();

							if(!StringUtils.isValidString(ls_tipoDireccion))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DIRECCION_COR);
						}

						{
							String ls_tipoEje;
							ls_tipoEje = lpd_correspondencia.getIdTipoEjePrincipal();

							if(!StringUtils.isValidString(ls_tipoEje))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE_COR);
						}

						{
							String ls_ejePrincipal;
							ls_ejePrincipal = lpd_correspondencia.getDatoEjePrincipal();

							if(!StringUtils.isValidString(ls_ejePrincipal))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_EJE_PRINCIPAL_COR);
						}

						{
							String ls_tipoEje1;
							ls_tipoEje1 = lpd_correspondencia.getIdTipoEjeSecundario();

							if(!StringUtils.isValidString(ls_tipoEje1))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1_COR);
						}

						{
							String ls_ejeSecundario;
							ls_ejeSecundario = lpd_correspondencia.getDatoEjeSecundario();

							if(!StringUtils.isValidString(ls_ejeSecundario))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_EJE_SECUNDARIO_COR);
						}

						{
							String ls_pais;
							ls_pais = lpd_correspondencia.getIdPais();

							if(!StringUtils.isValidString(ls_pais))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_CORRESPONDENCIA);

							if(
							    !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
								    && !StringUtils.isValidString(lpd_correspondencia.getComplementoDireccion())
							)
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_COMPLEMENTO_CORRESPONDENCIA);
						}

						{
							String ls_departamento;
							ls_departamento = lpd_correspondencia.getIdDepartamento();

							if(!StringUtils.isValidString(ls_departamento))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_CORRESPONDENCIA);
						}

						{
							String ls_municipio;
							ls_municipio = lpd_correspondencia.getIdMunicipio();

							if(!StringUtils.isValidString(ls_municipio))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUN_CORRESPONDENCIA);
						}
					}
				}

				PersonaTelefono lpt_telefonoFijo;
				lpt_telefonoFijo = ar_registro.getTelefonoFijo();

				if(StringUtils.isValidString(ls_telFi) && (lpt_telefonoFijo != null))
				{
					String ls_telefono;
					ls_telefono = lpt_telefonoFijo.getTelefono();

					if(StringUtils.isValidString(ls_telefono))
					{
						String ls_pais;
						ls_pais = lpt_telefonoFijo.getIdPais();

						if(!StringUtils.isValidString(ls_pais))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_FIJO);

						{
							String ls_departamento;
							ls_departamento = lpt_telefonoFijo.getIdDepartamento();

							if(!StringUtils.isValidString(ls_departamento))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEP_TEL_FIJO);
						}

						{
							String ls_indicativo;
							ls_indicativo = lpt_telefonoFijo.getIndicativo();

							if(!StringUtils.isValidString(ls_indicativo))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_INDICATIVO);
						}

						{
							Long ll_telefono;
							ll_telefono = NumericUtils.getLongWrapper(ls_telefono);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO);

							if(!NumericUtils.isValidLong(ll_telefono, 1))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_FIJO_VALIDO);

							String     ls_idConstante;
							Constantes lc_digitosFijo;

							ls_idConstante     = ConstanteCommon.DIGITOS_TEL_FIJO_COL;
							lc_digitosFijo     = lc_DAO.findById(ls_idConstante);

							if(lc_digitosFijo == null)
							{
								Object[] loa_messageArgs;

								loa_messageArgs        = new String[1];
								loa_messageArgs[0]     = ls_idConstante;

								throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
							}

							BigInteger lbi_temp;
							int        li_digitos;

							lbi_temp       = lc_digitosFijo.getEntero();
							li_digitos     = 0;

							if(NumericUtils.isValidBigInteger(lbi_temp))
								li_digitos = lbi_temp.intValue();

							if(
							    ls_pais.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
								    && (ls_telefono.length() != li_digitos)
							)
								throw new B2BException(ErrorKeys.ERROR_TELEFONO_FIJO_MALFORMADO);
						}
					}
				}

				PersonaTelefono lpt_telefonoMovil;
				lpt_telefonoMovil = ar_registro.getTelefonoMovil();

				if(StringUtils.isValidString(ls_medioComunicar))
				{
					if(
					    (StringUtils.isValidString(ls_telMo) || ls_medioComunicar.equals(MedioNotificarCommon.SMS))
						    && (lpt_telefonoMovil != null)
					)
					{
						String ls_telefono;

						ls_telefono         = lpt_telefonoMovil.getTelefono();
						ls_idTelefonoCo     = lpt_telefonoMovil.getIdTelefono();

						{
							String ls_idPais;
							ls_idPais = lpt_telefonoMovil.getIdPais();

							if(!StringUtils.isValidString(ls_idPais))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);

							{
								Long ll_telefono;
								ll_telefono = NumericUtils.getLongWrapper(ls_telefono);

								if(!StringUtils.isValidString(ls_telefono))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL);

								if(!NumericUtils.isValidLong(ll_telefono, 1))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_TELEFONO_MOVIL_VALIDO);

								String     ls_idConstante;
								Constantes lc_digitosMovil;

								ls_idConstante      = ConstanteCommon.DIGITOS_TEL_MOVIL_COL;
								lc_digitosMovil     = lc_DAO.findById(ls_idConstante);

								if(lc_digitosMovil == null)
								{
									Object[] loa_messageArgs;

									loa_messageArgs        = new String[1];
									loa_messageArgs[0]     = ls_idConstante;

									throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
								}

								BigInteger lbi_temp;
								int        li_digitos;

								lbi_temp       = lc_digitosMovil.getEntero();
								li_digitos     = 0;

								if(NumericUtils.isValidBigInteger(lbi_temp))
									li_digitos = lbi_temp.intValue();

								if(
								    ls_idPais.equals(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
									    && (ls_telefono.length() != li_digitos)
								)
									throw new B2BException(ErrorKeys.ERROR_TELEFONO_MOVIL_MALFORMADO);
							}
						}
					}
				}

				PersonaCorreoElectronico lpce_correo;
				lpce_correo = ar_registro.getPersonaCorreoElectronico();

				if(!lb_seleccionadoEsApoderado)
				{
					if(
					    (ls_medioComunicar.equals(MedioNotificarCommon.CORREO_ELECTRONICO)
						    || ls_medioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO)
						    || StringUtils.isValidString(ls_correo1)) && (lpce_correo != null)
					)
					{
						String ls_correo;
						ls_correo = lpce_correo.getCorreoElectronico();

						if(ls_medioComunicar.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
							ls_idCorreoCo = lpce_correo.getIdCorreoElectronico();

						if(ls_medioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
							ls_idCorreoNo = lpce_correo.getIdCorreoElectronico();

						if(
						    StringUtils.isValidString(ls_correo)
							    && !ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo)
						)
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
					}
				}

				{
					PersonaDAO lpd_DAO;
					String     ls_idPersona;

					lpd_DAO     = DaoCreator.getPersonaDAO(ldm_manager);

					ls_idPersona = marcarFlagPersona(ldm_manager, lp_persona, as_userId, as_remoteIp);

					if(StringUtils.isValidString(ls_idPersona))
						lp_persona = lpd_DAO.findById(ls_idPersona);

					if(ar_registro.isPersonaCargada())
					{
						Persona lp_temp;
						lp_temp     = new Persona();

						lp_temp = lpd_DAO.findDataCalificador(lp_persona);

						if(lp_temp != null)
						{
							lp_temp     = lpd_DAO.findById(lp_temp);

							ls_idPersona = lp_temp.getIdPersona();
						}

						ar_registro.setIdPersona(ls_idPersona);
					}
					else
						ar_registro.setIdPersona(ls_idPersona);

					if((lpd_dirRe != null) && StringUtils.isValidString(ls_dirRe))
					{
						validarDireccion(lpd_dirRe, true);

						PersonaDireccionDAO lpdd_DAO;
						PersonaDireccion    lpd_direccion;
						boolean             lb_crearDireccion;

						lpdd_DAO          = DaoCreator.getPersonaDireccionDAO(ldm_manager);
						lpd_direccion     = null;

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && StringUtils.isValidString(lpd_dirRe.getIdDireccion())
						)
						{
							lpd_dirRe.setIdPersona(ls_idPersona);

							lpd_direccion = lpdd_DAO.findById(lpd_dirRe);
						}

						lb_crearDireccion = lpd_direccion == null;

						if(lb_crearDireccion)
						{
							long ll_max;

							{
								String ls_idTipoPredio;

								ls_idTipoPredio = lpd_dirRe.getIdTipoPredio();

								if(!StringUtils.isValidString(ls_idTipoPredio))
									throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
							}

							lpd_dirRe.setIdPersona(ls_idPersona);
							lpd_dirRe.setFechaDesde(new Date());
							lpd_dirRe.setIdUsuarioCreacion(as_userId);
							lpd_dirRe.setIpCreacion(as_remoteIp);

							ll_max = lpdd_DAO.insertOrUpdate(lpd_dirRe, lb_crearDireccion);

							if(ll_max > 0)
								lpd_dirRe.setIdDireccion(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
						}
					}

					if((lpd_dirCor != null) && StringUtils.isValidString(ls_dirCor))
					{
						validarDireccion(lpd_dirCor, false);

						PersonaDireccionDAO lpdd_DAO;
						PersonaDireccion    lpd_direccion;
						boolean             lb_crearDireccion;

						lpdd_DAO              = DaoCreator.getPersonaDireccionDAO(ldm_manager);
						lpd_direccion         = null;
						lb_crearDireccion     = false;

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && StringUtils.isValidString(lpd_dirCor.getIdDireccion())
						)
						{
							lpd_dirCor.setIdPersona(ls_idPersona);

							lpd_direccion = lpdd_DAO.findById(lpd_dirCor);
						}

						if(lpd_direccion == null)
						{
							long ll_max;

							{
								String ls_idTipoPredio;

								ls_idTipoPredio = lpd_dirCor.getIdTipoPredio();

								if(!StringUtils.isValidString(ls_idTipoPredio))
									throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
							}

							lb_crearDireccion = true;
							lpd_dirCor.setIdPersona(ls_idPersona);
							lpd_dirCor.setFechaDesde(new Date());
							lpd_dirCor.setIdUsuarioCreacion(as_userId);
							lpd_dirCor.setIpCreacion(as_remoteIp);

							ll_max = lpdd_DAO.insertOrUpdate(lpd_dirCor, lb_crearDireccion);

							if(ll_max > 0)
								lpd_dirCor.setIdDireccion(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
						}
					}

					if((lpt_telefonoFijo != null) && StringUtils.isValidString(lpt_telefonoFijo.getTelefono()))
					{
						PersonaTelefonoDAO lptd_DAO;
						PersonaTelefono    lpt_telefono;
						boolean            lb_crearTelefono;

						lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
						lpt_telefono         = null;
						lb_crearTelefono     = false;

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && StringUtils.isValidString(lpt_telefonoFijo.getIdTelefono())
						)
						{
							lpt_telefonoFijo.setIdPersona(ls_idPersona);

							lpt_telefono = lptd_DAO.findById(lpt_telefonoFijo);
						}

						if(lpt_telefono == null)
						{
							long ll_max;

							lb_crearTelefono = true;
							lpt_telefonoFijo.setIdPersona(ls_idPersona);
							lpt_telefonoFijo.setTipoTelefono("F");
							lpt_telefonoFijo.setFechaDesde(new Date());
							lpt_telefonoFijo.setIdUsuarioCreacion(as_userId);
							lpt_telefonoFijo.setIpCreacion(as_remoteIp);
							lpt_telefonoFijo.setIdDepartamento(lpt_telFi.getIdDepartamento());
							ll_max = lptd_DAO.insertOrUpdate(lpt_telefonoFijo, lb_crearTelefono);

							if(ll_max > 0)
								lpt_telefonoFijo.setIdTelefono(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
						}
					}

					if((lpt_telefonoMovil != null) && StringUtils.isValidString(lpt_telefonoMovil.getTelefono()))
					{
						PersonaTelefonoDAO lptd_DAO;
						PersonaTelefono    lpt_telefono;
						boolean            lb_crearTelefono;

						lptd_DAO             = DaoCreator.getPersonaTelefonoDAO(ldm_manager);
						lpt_telefono         = null;
						lb_crearTelefono     = false;

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && StringUtils.isValidString(lpt_telefonoMovil.getIdTelefono())
						)
						{
							lpt_telefonoMovil.setIdPersona(ls_idPersona);

							lpt_telefono = lptd_DAO.findById(lpt_telefonoMovil);
						}

						if(lpt_telefono == null)
						{
							long ll_max;

							lb_crearTelefono = true;
							lpt_telefonoMovil.setIdPersona(ls_idPersona);
							lpt_telefonoMovil.setTipoTelefono("M");
							lpt_telefonoMovil.setFechaDesde(new Date());
							lpt_telefonoMovil.setIdUsuarioCreacion(as_userId);
							lpt_telefonoMovil.setIpCreacion(as_remoteIp);
							lpt_telefonoMovil.setIdDepartamento(IdentificadoresCommon.INDICATIVO_BOGOTA_DEPARTAMENTO);

							ll_max = lptd_DAO.insertOrUpdate(lpt_telefonoMovil, lb_crearTelefono);

							if(ll_max > 0)
								lpt_telefonoMovil.setIdTelefono(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
						}
					}

					if((lpce_correo != null) && StringUtils.isValidString(lpce_correo.getCorreoElectronico()))
					{
						PersonaCorreoElectronicoDAO lpced_DAO;
						PersonaCorreoElectronico    lpc_datos;
						boolean                     lb_crearCorreo;

						lpced_DAO          = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
						lpc_datos          = null;
						lb_crearCorreo     = false;

						if(
						    StringUtils.isValidString(ls_idPersona)
							    && StringUtils.isValidString(lpce_correo.getIdCorreoElectronico())
						)
						{
							lpce_correo.setIdPersona(ls_idPersona);

							lpc_datos = lpced_DAO.findById(lpce_correo);
						}

						if(lpc_datos == null)
						{
							long ll_max;

							lb_crearCorreo = true;
							lpce_correo.setIdPersona(ls_idPersona);
							lpce_correo.setFechaDesde(new Date());
							lpce_correo.setIdUsuarioCreacion(as_userId);
							lpce_correo.setIpCreacion(as_remoteIp);

							ll_max = lpced_DAO.insertOrUpdate(lpce_correo, lb_crearCorreo);

							if(ll_max > 0)
								lpce_correo.setIdCorreoElectronico(StringUtils.getString(ll_max));
							else
								throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
						}
					}

					{
						PersonaEntrega    lpe_personaEntrega;
						PersonaEntregaDAO lpe_DAO;

						lpe_personaEntrega     = new PersonaEntrega();
						lpe_DAO                = DaoCreator.getPersonaEntregaDAO(ldm_manager);

						lpe_personaEntrega.setIdSolicitud(ls_solicitud.getIdSolicitud());
						lpe_personaEntrega.setIdTurno(StringUtils.getString(ar_registro.getIdTurno()));
						lpe_personaEntrega.setIdPersona(ar_registro.getIdPersona());
						lpe_personaEntrega.setIdCalidadPersonaEntrega(ls_calidadActua);
						lpe_personaEntrega.setIdAutorizacionComunicacion(ls_medioComunicar);
						lpe_personaEntrega.setIdAutorizacionNotificacion(ls_medioNotificar);

						if(lpce_correo != null)
							lpe_personaEntrega.setIdCorreoElectronicoPersonaEntrega(
							    lpce_correo.getIdCorreoElectronico()
							);

						if(!lb_seleccionadoEsApoderado)
						{
							if(
							    ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
								    && (lpd_dirCor != null)
							)
								ls_idDireccionNo = lpd_dirCor.getIdDireccion();
							else if(
							    ls_medioNotificar.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
								    && (lpd_dirRe != null)
							)
								ls_idDireccionCo = lpd_dirRe.getIdDireccion();
						}

						lpe_personaEntrega.setIdCorreoElectronicoPersonaEntrega(ls_idCorreoNo);
						lpe_personaEntrega.setIdDireccionPersonaEntrega(ls_idDireccionNo);
						lpe_personaEntrega.setIdTelefonoPersonaEntrega(ls_idTelefonoNo);
						lpe_personaEntrega.setIdCorreoElectronicoPersonaEntregaComunicacion(ls_idCorreoCo);
						lpe_personaEntrega.setIdDireccionPersonaEntregaComunicacion(ls_idDireccionCo);
						lpe_personaEntrega.setIdTelefonoPersonaEntregaComunicacion(ls_idTelefonoCo);

						lpe_personaEntrega.setIdUsuarioCreacion(as_userId);
						lpe_personaEntrega.setIpCreacion(as_remoteIp);

						lpe_DAO.insert(lpe_personaEntrega);

						lpe_return = lpe_personaEntrega;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarDatosIntervininete", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpe_return;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para poder guardar
	 * los datos obtenidos por la aplicacion y finalizar un proceso de Entrega.
	 *
	 * @param ae_entrega            Objeto contenedor de los datos necesarios para terminar el proceso
	 *            de entrega
	 * @param as_idUsuario            Id del usuario que ejecuta el proceso
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta el proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarEntrega(Entrega ae_entrega, String as_idUsuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ae_entrega == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			TurnoHistoria          lth_turnoHistoria;
			TurnoHistoriaDAO       lth_DAO;
			String                 ls_correo;
			Long                   ll_turnoHistoria;
			Turno                  lt_turno;
			String                 ls_turno;
			DocumentosSalidaDAO    lds_DAO;
			boolean                lb_validacionSuspension;
			TurnoHistoria          lt_turnoHistoria;
			MotivoTramiteDAO       lmt_DAO;
			MotivoTramite          lmt_motivo;
			String                 ls_idSolicitud;
			Solicitud              ls_solicitud;
			SolicitudDAO           ls_DAO;
			long                   ll_idEtapa;
			SolicitudInterviniente lsi_interviniente;
			PersonaEntrega         lpe_personaEntrega;

			ls_solicitud                = new Solicitud();
			ls_DAO                      = DaoCreator.getSolicitudDAO(ldm_manager);
			ll_idEtapa                  = ae_entrega.getIdEtapa();
			lmt_motivo                  = new MotivoTramite();
			lmt_DAO                     = DaoCreator.getMotivoTramiteDAO(ldm_manager);
			lb_validacionSuspension     = true;
			lt_turnoHistoria            = new TurnoHistoria();
			lth_turnoHistoria           = new TurnoHistoria();
			lth_DAO                     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
			ls_correo                   = ae_entrega.getCorreoElectronico();
			ll_turnoHistoria            = ae_entrega.getTurnoHistoria();
			lt_turno                    = ae_entrega.getTurno();
			lds_DAO                     = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
			lsi_interviniente           = ae_entrega.getIntervinienteEntrega();
			lpe_personaEntrega          = ae_entrega.getTercero();

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.ERROR_TURNO_INVALIDO);

			ls_turno = lt_turno.getIdTurno();

			lth_turnoHistoria.setIdTurnoHistoria(ll_turnoHistoria);

			lth_turnoHistoria = lth_DAO.findById(lth_turnoHistoria);

			if(lth_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			ls_solicitud.setIdSolicitud(ls_idSolicitud);

			ls_solicitud = ls_DAO.findById(ls_solicitud);

			if(StringUtils.isValidString(ls_correo))
			{
				if(ls_solicitud != null)
				{
					String ls_idPersona;

					ls_idPersona = ls_solicitud.getIdPersona();

					if(StringUtils.isValidString(ls_idPersona))
					{
						long                        ll_idPersonaCorreo;
						PersonaCorreoElectronico    lpcr_personaCorreo;
						PersonaCorreoElectronicoDAO lpce_DAO;

						lpcr_personaCorreo     = new PersonaCorreoElectronico();
						lpce_DAO               = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);

						lpcr_personaCorreo.setIdPersona(ls_idPersona);
						lpcr_personaCorreo.setCorreoElectronico(ls_correo);
						lpcr_personaCorreo.setFechaDesde(new Date());
						lpcr_personaCorreo.setIdUsuarioCreacion(as_idUsuario);
						lpcr_personaCorreo.setIpCreacion(as_remoteIp);

						ll_idPersonaCorreo = lpce_DAO.insertOrUpdate(lpcr_personaCorreo, true);

						if(ll_idPersonaCorreo > 0)
						{
							ls_solicitud.setIdCorreoElectronico(StringUtils.getString(ll_idPersonaCorreo));
							ls_solicitud.setIdUsuarioModificacion(as_idUsuario);

							ls_DAO.insertOrUpdate(ls_solicitud, false);
						}
					}
				}
			}

			{
				PersonaEntregaDAO lpe_DAO;

				lpe_DAO = DaoCreator.getPersonaEntregaDAO(ldm_manager);

				if(lsi_interviniente != null)
				{
					PersonaEntrega lpe_insert;
					lpe_insert = new PersonaEntrega();

					lpe_insert.setIdPersona(lsi_interviniente.getIdPersona());
					lpe_insert.setIdSolicitud(lsi_interviniente.getIdSolicitud());
					lpe_insert.setIdTurno(ls_turno);
					lpe_insert.setIdCalidadPersonaEntrega(CalidadSolicitanteCommon.INTERVINIENTE);
					lpe_insert.setIdUsuarioCreacion(as_idUsuario);
					lpe_insert.setIpCreacion(as_remoteIp);

					lpe_DAO.insert(lpe_insert);
				}
				else if(lpe_personaEntrega != null)
				{
					String ls_calidad;

					ls_calidad = StringUtils.getStringNotNull(lpe_personaEntrega.getIdCalidadPersonaEntrega());

					if(ls_calidad.equals(CalidadSolicitanteCommon.APODERADO))
					{
						Collection<SolicitudInterviniente> lcsi_intervinientes;
						lcsi_intervinientes = ae_entrega.getIntervinientesApoderado();

						if(CollectionUtils.isValidCollection(lcsi_intervinientes))
						{
							for(SolicitudInterviniente lsi_iterador : lcsi_intervinientes)
							{
								if(lsi_iterador != null)
								{
									PersonaEntrega lpe_insert;
									lpe_insert = new PersonaEntrega();

									lpe_insert.setIdPersona(lsi_iterador.getIdPersona());
									lpe_insert.setIdPersonaTercero(lpe_personaEntrega.getIdPersona());
									lpe_insert.setIdSolicitud(lsi_iterador.getIdSolicitud());
									lpe_insert.setIdTurno(ls_turno);
									lpe_insert.setIdCalidadPersonaEntrega(CalidadSolicitanteCommon.INTERVINIENTE);
									lpe_insert.setIdUsuarioCreacion(as_idUsuario);
									lpe_insert.setIpCreacion(as_remoteIp);

									lpe_DAO.insert(lpe_insert);
								}
							}
						}
					}
				}

				byte[]                          lba_cedula;
				byte[]                          lba_carta;
				Map<String, DocumentoAdicional> lmsba_documentosAdicionales;
				ConstantesDAO                   lc_DAO;
				String                          ls_idConstante;
				Constantes                      lc_tipoDocumental;
				String                          ls_caracter;
				String[]                        lsa_tiposDoc;
				String                          ls_numeroSolicitud;
				ImagenesDAO                     li_DAO;

				lba_cedula                      = ae_entrega.getCedulaCiudadania();
				lba_carta                       = ae_entrega.getCartaAutorizacion();
				lmsba_documentosAdicionales     = ae_entrega.getDocumentosAdicionales();
				lc_DAO                          = DaoCreator.getConstantesDAO(ldm_manager);
				ls_numeroSolicitud              = lth_turnoHistoria.getIdSolicitud();
				li_DAO                          = DaoCreator.getImagenesDAO(ldm_manager);
				ls_idConstante                  = ConstanteCommon.TIPOS_DOCUMENTOS_AUTORIZACION_ENTREGA;

				lc_tipoDocumental = lc_DAO.findById(ls_idConstante);

				if(lc_tipoDocumental == null)
				{
					Object[] loa_messageArgs;

					loa_messageArgs        = new String[1];
					loa_messageArgs[0]     = ls_idConstante;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}

				ls_caracter     = StringUtils.getStringNotNull(lc_tipoDocumental.getCaracter());

				lsa_tiposDoc = ls_caracter.split(",");

				if(lmsba_documentosAdicionales == null)
					lmsba_documentosAdicionales = new LinkedHashMap<String, DocumentoAdicional>();

				if(lba_cedula != null)
				{
					DocumentoAdicional lda_docCedula;
					lda_docCedula = new DocumentoAdicional();

					lda_docCedula.setBytesDocumento(lba_cedula);

					lmsba_documentosAdicionales.put(lsa_tiposDoc[0], lda_docCedula);
				}

				if(lba_carta != null)
				{
					DocumentoAdicional lda_docCarta;
					lda_docCarta = new DocumentoAdicional();

					lda_docCarta.setBytesDocumento(lba_carta);

					lmsba_documentosAdicionales.put(lsa_tiposDoc[1], lda_docCarta);
				}

				if(CollectionUtils.isValidCollection(lmsba_documentosAdicionales))
				{
					for(Map.Entry<String, DocumentoAdicional> lme_iterator : lmsba_documentosAdicionales.entrySet())
					{
						if(lme_iterator != null)
						{
							Imagenes           li_imagenRelacion;
							long               ll_idImagen;
							DocumentoAdicional lda_documento;
							byte[]             lba_bytesDocumento;

							li_imagenRelacion     = new Imagenes();
							lda_documento         = lme_iterator.getValue();

							if(lda_documento == null)
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

							lba_bytesDocumento = lda_documento.getBytesDocumento();

							if(lba_bytesDocumento == null)
								throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

							li_imagenRelacion.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenRelacion.setIdUsuarioCreacion(as_idUsuario);
							li_imagenRelacion.setIpCreacion(as_remoteIp);
							li_imagenRelacion.setImagenBlob(lba_bytesDocumento);

							ll_idImagen = li_DAO.insertOrUpdate(li_imagenRelacion, true);

							if(!NumericUtils.isValidLong(NumericUtils.getLongWrapper(ll_idImagen), 1))
								throw new B2BException(ErrorKeys.SIN_SECUENCIA_IMAGENES);

							DocumentosSalida lds_documento;
							Integer          lbi_idTurnoHistoria;

							lds_documento           = new DocumentosSalida();
							lbi_idTurnoHistoria     = new Integer(ll_turnoHistoria.toString());

							lds_documento.setIdTurnoHistoria(lbi_idTurnoHistoria);
							lds_documento.setIdSolicitud(ls_numeroSolicitud);
							lds_documento.setIdTurno(ls_turno);
							lds_documento.setIdImagen(NumericUtils.getLongWrapper(ll_idImagen));

							String ls_observaciones;
							ls_observaciones = StringUtils.getStringNotNull(lda_documento.getObservaciones());

							if(ls_observaciones.length() > 400)
								throw new B2BException(ErrorKeys.ERROR_OBSERVACIONES_TAM_400);

							lds_documento.setObservaciones(ls_observaciones);
							lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documento.setTipo(TipoArchivoCommon.DOCUMENTOS_ENTREGA);
							lds_documento.setIdTipoDocumental(lme_iterator.getKey());
							lds_documento.setEstado(EstadoCommon.ENTREGA);
							lds_documento.setIdUsuarioCreacion(as_idUsuario);
							lds_documento.setIpCreacion(as_remoteIp);

							lds_DAO.insertOrUpdate(lds_documento, true);
						}
					}
				}
			}

			{
				DocumentosSalida             lds_documentoSalidaAux;
				Collection<DocumentosSalida> lcds_documentosSalida;

				lds_documentoSalidaAux = new DocumentosSalida();

				lds_documentoSalidaAux.setIdTurno(ls_turno);

				lcds_documentosSalida = lds_DAO.findByIdTurnoEstadoA(lds_documentoSalidaAux, true);

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
				{
					for(DocumentosSalida lds_iterador : lcds_documentosSalida)
					{
						if(lds_iterador != null)
						{
							lds_iterador.setEstado(EstadoCommon.ENTREGA);
							lds_DAO.insertOrUpdate(lds_iterador, false);
						}
					}
				}
			}

			{
				Collection<TurnoDerivado> lctd_turnosDerivados;
				TurnoDerivado             ltd_turnosHijos;

				ltd_turnosHijos = new TurnoDerivado();

				ltd_turnosHijos.setIdTurnoPadre(ls_turno);

				lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager).findByIdTurnoPadre(ltd_turnosHijos);

				if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
				{
					for(TurnoDerivado ltd_turno : lctd_turnosDerivados)
					{
						if((ltd_turno != null) && !ltd_turno.isIndVinculado())
						{
							Collection<DocumentosSalida> lcds_docsDerivados;
							DocumentosSalida             lds_docDerivado;

							lds_docDerivado = new DocumentosSalida();

							lds_docDerivado.setIdTurno(ltd_turno.getIdTurnoHijo());

							lcds_docsDerivados = lds_DAO.findByIdTurnoEstadoA(lds_docDerivado, true);

							if(CollectionUtils.isValidCollection(lcds_docsDerivados))
							{
								for(DocumentosSalida lds_iterador : lcds_docsDerivados)
								{
									if(lds_iterador != null)
									{
										lds_iterador.setEstado(EstadoCommon.ENTREGA);
										lds_DAO.insertOrUpdate(lds_iterador, false);
									}
								}
							}
						}
					}
				}
			}

			TurnoHistoria lth_turnoHistoriaAnt;

			lth_turnoHistoriaAnt = lth_DAO.findById(NumericUtils.getLongWrapper(lth_turnoHistoria.getIdAnterior()));

			if(lth_turnoHistoriaAnt != null)
			{
				long   ll_idMotivoAnt;
				long   ll_idEtapaAnt;
				Long   lL_idMotivo;
				String ls_motivoNoTramite;
				String ls_renuncia;

				ll_idMotivoAnt         = NumericUtils.DEFAULT_LONG_VALUE;
				ll_idEtapaAnt          = NumericUtils.DEFAULT_LONG_VALUE;
				lL_idMotivo            = lth_turnoHistoriaAnt.getIdMotivo();
				ls_motivoNoTramite     = lth_turnoHistoriaAnt.getMotivoNoTramite();
				ls_renuncia            = StringUtils.getStringNotNull(ae_entrega.getRenunciaTerminos());

				{
					BigDecimal lbd_idEtapaAnt;

					lbd_idEtapaAnt = lth_turnoHistoriaAnt.getIdEtapa();

					if(NumericUtils.isValidBigDecimal(lbd_idEtapaAnt))
						ll_idEtapaAnt = lbd_idEtapaAnt.longValue();
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_ETAPA);
				}

				if(lL_idMotivo != null)
					ll_idMotivoAnt = NumericUtils.getLong(lL_idMotivo);

				if(
				    StringUtils.isValidString(ls_motivoNoTramite)
					    && ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.REALIZAR_REGISTRO_PARCIAL)
				)
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL),
					    as_idUsuario, as_remoteIp, EstadoCommon.TERMINADA, false
					);
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_ORIP_INSCRIPCION)
				{
					String ls_idProceso;
					long   ll_idMotivo;

					ls_idProceso     = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
					ll_idMotivo      = 0L;

					{
						long ll_idMotivoRecursos;

						ll_idMotivoRecursos = calcularIdMotivoRecursos(ldm_manager, ls_idSolicitud);

						if(ll_idMotivoRecursos > 0)
							ll_idMotivo = ll_idMotivoRecursos;
						else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_42))
							ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)
							    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES)
						)
						{
							if(ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_ORIP_SOLICITUD_DOCUMENTACION)
								ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES_ENTREGA;
							else if(ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_ORIP_COBRO_POR_MAYOR_VALOR)
								ll_idMotivo = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR_CORRECCIONES;
							else if(
							    (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_ORIP)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_ORIP)
							)
								ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
							else if(ll_idMotivoAnt == MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_ORIP)
								ll_idMotivo = MotivoTramiteCommon.NEGACION_APERTURA_ACTUACIONES_ADMINISTRATIVAS;
						}
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
							    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
						)
						{
							if(ll_idMotivoAnt == MotivoTramiteCommon.SUSPENSION_ARTICULO_19_ORIP)
								ll_idMotivo = MotivoTramiteCommon.SUSPENSION_TEMPORAL_DEL_TRAMITE_DE_REGISTRO;
							else if(
							    (ll_idMotivoAnt == MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_REPRODUCCION_CONSTANCIA_ORIP)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_DESISTIMIENTO)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_RESTITUCION)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.REPRODUCCION_TESTAMENTOS_CORRREO_ELECTRONICO)
							)
								ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
							else if(ll_idMotivoAnt == MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_ORIP)
								ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO;
							else if(
							    (ll_idMotivoAnt == MotivoTramiteCommon.MAYOR_VALOR_ORIP)
								    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
							)
								ll_idMotivo = MotivoTramiteCommon.PENDIENTE_PAGO_MAYOR_VALOR;
							else if(
							    ll_idMotivoAnt == MotivoTramiteCommon.GENERAR_CITATORIO_DE_NOTIFICACION_AUTO_DE_PRUEBAS
							)
								ll_idMotivo = MotivoTramiteCommon.COMUNICACION_AUTO_DE_PRUEBAS_ENVIADA_288;
							else if(ll_idMotivoAnt == MotivoTramiteCommon.TESTAMENTO_ENTREGA_ORIP)
								ll_idMotivo = MotivoTramiteCommon.FINALIZACION_PROCESO_DE_TESTAMENTOS_244;
						}
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_37)
							    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
						)
						{
							if(
							    (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_GRABACION_ORIP)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP)
							)
								ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						}
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39)
							    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
						)
						{
							if(ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_DESISTIMIENTO)
								ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						}
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
							    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
							    || ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
						)
							ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_5;
						else if(
						    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_44)
							    && (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA)
						)
						{
							if(
							    (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_ORIP)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_ORIP)
							)
								ll_idMotivo = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO_200;
							else if(
							    ll_idMotivoAnt == MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_ORIP
							)
								ll_idMotivo = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO_200;
						}
						else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38))
						{
							if(ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
							{
								if(ll_idMotivoAnt == MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_ORIP)
									ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_TRASLADO_MATRICULAS;
								else if(
								    ll_idMotivoAnt == MotivoTramiteCommon.EN_ESPERA_DE_ENTREGA_DOCUMENTACION_TRASLADOS_ORIP
								)
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_DOCUMENTACION_TRASLADOS_TRASLADO_MATRICULAS;
							}
							else if(ll_idEtapaAnt == EtapaCommon.APROBACION_DE_TRASLADOS_OFICINA_DESTINO)
							{
								if(
								    ll_idMotivoAnt == MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_30
								)
									ll_idMotivo = MotivoTramiteCommon.SOLICITUD_DOCUMENTO_ELECTRONICO_OWCC_TRASLADO_MATRICULAS;
							}
						}

						if(ll_idMotivo > 0)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, ldm_manager, new MotivoTramite(ll_idEtapa, ll_idMotivo), as_idUsuario,
							    as_remoteIp, EstadoCommon.TERMINADA, false
							);
						else
							throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_PARAMETRIZACION_PROCESO_ANTERIOR);
					}
				}
				else if(
				    (ll_idEtapaAnt == EtapaCommon.ID_ETAPA_APROBACION)
					    && (ll_idMotivoAnt == MotivoTramiteCommon.ENTREGA_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL)
				)
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL),
					    as_idUsuario, as_remoteIp, EstadoCommon.TERMINADA, false
					);
				else if(StringUtils.isValidString(ls_correo))
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO), as_idUsuario,
					    as_remoteIp, EstadoCommon.TERMINADA, false
					);
				else if(
				    ((ll_idEtapa == EtapaCommon.ID_ETAPA_ENTREGA_CERTIFICADOS_ORIP_FINALIZADO)
					    || (ll_idEtapa == EtapaCommon.ENTREGA_DOCUMENTOS_CORRESPONDENCIA))
				)
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.ENTREGA_DE_CERTIFICADOS_FINALIZADO),
					    as_idUsuario, as_remoteIp, EstadoCommon.TERMINADA, false
					);
				else if(
				    ((ll_idEtapa >= EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
					    && (ll_idEtapa <= EtapaCommon.ID_ETAPA_ENTREGA_CORRESPONDENCIA_NOTA_DEVOLUTIVA))
					    && ls_renuncia.equals(EstadoCommon.NO)
				)
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS),
					    as_idUsuario, as_remoteIp, EstadoCommon.TERMINADA, false
					);
				else
					terminarTurnoHistoriaYCrearEtapa(
					    lth_turnoHistoria, ldm_manager,
					    new MotivoTramite(ll_idEtapa, MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO), as_idUsuario,
					    as_remoteIp, EstadoCommon.TERMINADA, false
					);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA_ANTERIOR);

			lt_turnoHistoria.setIdTurnoHistoria(ll_turnoHistoria);

			lb_validacionSuspension = DaoCreator.getTurnoHistoriaDAO(ldm_manager).validacionSuspencion(
				    lt_turnoHistoria
				);

			if(
			    (ll_idEtapa >= EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
				    && (ll_idEtapa <= EtapaCommon.ID_ETAPA_ENTREGA_CORRESPONDENCIA_NOTA_DEVOLUTIVA)
			)
			{
				NotificacionDetalleDAO lnd_DAO;
				NotificacionDetalle    lnd_notificacion;
				String                 ls_idPersona;
				String                 ls_renuncia;
				String                 ls_recurso;

				lnd_DAO              = DaoCreator.getNotificacionDetalleDAO(ldm_manager);
				lnd_notificacion     = new NotificacionDetalle();
				ls_idPersona         = null;
				ls_renuncia          = ae_entrega.getRenunciaTerminos();
				ls_recurso           = null;

				lnd_notificacion.setIdTurno(ls_turno);

				if(lsi_interviniente != null)
					ls_idPersona = lsi_interviniente.getIdPersona();
				else if(lpe_personaEntrega != null)
					ls_idPersona = lpe_personaEntrega.getIdPersona();
				else
					throw new B2BException(ErrorKeys.DEBE_AGREGAR_INTERVINIENTE);

				lnd_notificacion.setIdPersona(ls_idPersona);
				lnd_notificacion.setIdTipoNotificacion(TipoNotificacionCommon.PERSONAL);

				if(StringUtils.isValidString(ls_renuncia))
				{
					if(ls_renuncia.equals(EstadoCommon.SI))
						ls_recurso = EstadoCommon.NO;
					else
						ls_recurso = EstadoCommon.SI;
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_RENUNCIA_TERMINOS);

				lnd_notificacion.setRenunciaTerminos(ls_renuncia);
				lnd_notificacion.setInterponeRecurso(ls_recurso);
				lnd_notificacion.setIdUsuarioCreacion(as_idUsuario);
				lnd_notificacion.setIpCreacion(as_remoteIp);

				lnd_DAO.insert(lnd_notificacion);
			}

			{
				boolean lb_vieneDeMayorValor;

				lb_vieneDeMayorValor = false;

				if(lth_turnoHistoriaAnt != null)
				{
					long ll_idEtapaL;
					long ll_idMotivo;

					ll_idEtapaL     = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdEtapa());
					ll_idMotivo     = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdMotivo());

					lb_vieneDeMayorValor = (ll_idEtapaL == EtapaCommon.ID_ETAPA_APROBACION)
							&& ((ll_idMotivo == MotivoTramiteCommon.MAYOR_VALOR_CORREO_ELECTRONICO)
							|| (ll_idMotivo == MotivoTramiteCommon.MAYOR_VALOR_CORRESPONDENCIA)
							|| (ll_idMotivo == MotivoTramiteCommon.MAYOR_VALOR_ORIP));
				}

				//Terminación de turnos derivados
				if(
				    ((ll_idEtapa < EtapaCommon.ID_ETAPA_ENTREGA_ORIP_NOTA_DEVOLUTIVA)
					    || (ll_idEtapa > EtapaCommon.ID_ETAPA_ENTREGA_CORRESPONDENCIA_NOTA_DEVOLUTIVA))
					    && lb_validacionSuspension && !StringUtils.isValidString(ls_correo) && !lb_vieneDeMayorValor
				)
				{
					TurnoDerivado             ltd_turnosDerivado;
					Collection<TurnoDerivado> lctd_turnoDerivados;

					ltd_turnosDerivado = new TurnoDerivado();

					ltd_turnosDerivado.setIdTurnoPadre(ls_turno);

					lctd_turnoDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
							                            .findByIdTurnoPadre(ltd_turnosDerivado);

					if(CollectionUtils.isValidCollection(lctd_turnoDerivados))
					{
						for(TurnoDerivado ltd_actual : lctd_turnoDerivados)
						{
							if(ltd_actual != null)
							{
								lmt_motivo = new MotivoTramite();

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_30);
								lmt_motivo.setIdMotivo(MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO);

								lmt_motivo = lmt_DAO.findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									TurnoHistoria lth_th;

									lth_th = new TurnoHistoria();

									lth_th.setIdTurno(ltd_actual.getIdTurnoHijo());
									lth_th.setIdEtapa(NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30));

									lth_th = lth_DAO.findByIdTurnoEtapaDiffASG(lth_th);

									if(lth_th != null)
									{
										lth_th.setEstadoActividad(EstadoCommon.TERMINADA);

										lth_th.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));

										lth_th.setMotivo(lmt_motivo.getDescripcion());
										lth_th.setIdUsuarioModificacion(as_idUsuario);
										lth_th.setIpModificacion(as_remoteIp);

										lth_DAO.insertOrUpdate(lth_th, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_th);
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

			clh_LOGGER.error("salvarEntrega", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Salvar impresion documentos correspondencia.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarImpresionDocumentosCorrespondencia(
	    TurnoHistoria ath_turnoHistoria, String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				salvarImpresionDocumentosCorrespondencia(
				    ath_turnoHistoria, null, NumericUtils.DEFAULT_LONG_VALUE, ldm_manager, as_remoteIp, as_userId
				);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarImpresionDocumentosCorrespondencia", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Salvar impresion documentos correspondencia.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarImpresionDocumentosCorrespondencia(
	    TurnoHistoria ath_turnoHistoria, String as_numeroRelacion, long al_idImagenRelacion, DAOManager adm_manager,
	    String as_remoteIp, String as_userId
	)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			String ls_idTurno;

			ls_idTurno = ath_turnoHistoria.getIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdTurno(ls_idTurno);
				lth_turnoHistoria.setIdEtapa(ath_turnoHistoria.getIdEtapa());
				lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurnoEtapa(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					if(al_idImagenRelacion > 0)
					{
						ConstantesDAO       lc_DAO;
						DocumentosSalidaDAO lds_DAO;
						String              ls_idConstante;
						DocumentosSalida    lds_documento;
						Integer             lbi_idTurnoHistoria;
						Constantes          lc_tipoDocumental;
						Long                ll_turnoHistoria;

						lc_DAO                  = DaoCreator.getConstantesDAO(adm_manager);
						ll_turnoHistoria        = lth_turnoHistoria.getIdTurnoHistoria();
						lds_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
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
						lds_documento.setIdImagen(NumericUtils.getLongWrapper(al_idImagenRelacion));
						lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
						lds_documento.setTipo(TipoArchivoCommon.RELACION_APROBACION);
						lds_documento.setIdTipoDocumental(lc_tipoDocumental.getCaracter());
						lds_documento.setEstado(EstadoCommon.ENTREGA);
						lds_documento.setIdUsuarioCreacion(as_userId);
						lds_documento.setIpCreacion(as_remoteIp);

						lds_DAO.insertOrUpdate(lds_documento, true);
					}

					if(StringUtils.isValidString(as_numeroRelacion))
					{
						RelacionAprobacionDAO lra_DAO;
						RelacionAprobacion    lra_relacion;

						lra_relacion     = new RelacionAprobacion();
						lra_DAO          = DaoCreator.getRelacionAprobacionDAO(adm_manager);

						lra_relacion.setNumeroRelacion(as_numeroRelacion);
						lra_relacion.setTipoCalificacion(lth_turnoHistoria.getMotivoNoTramite());
						lra_relacion.setIdTurno(ls_idTurno);
						lra_relacion.setNir(ath_turnoHistoria.getNir());
						lra_relacion.setEstado(EstadoCommon.ACTIVO);
						lra_relacion.setIdUsuarioCreacion(as_userId);
						lra_relacion.setIpCreacion(as_remoteIp);

						lra_DAO.insert(lra_relacion);
					}

					Solicitud ls_solicitud;
					ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lth_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_idComunicacion;
						String ls_idNotificacion;

						ls_idComunicacion     = ls_solicitud.getIdAutorizacionComunicacion();
						ls_idNotificacion     = ls_solicitud.getIdAutorizacionNotificacion();

						if(
						    StringUtils.isValidString(ls_idComunicacion)
							    && StringUtils.isValidString(ls_idNotificacion) && ls_idComunicacion.equals("1")
							    && ls_idNotificacion.equals("1")
						)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, adm_manager,
							    new MotivoTramite(
							        NumericUtils.getLong(ath_turnoHistoria.getIdEtapa()),
							        MotivoTramiteCommon.ENTREGA_CONSULTAS_POR_CORRESPONDENCIA
							    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA, false, true
							);
						else
							terminarTurnoHistoriaYCrearEtapa(
							    lth_turnoHistoria, adm_manager,
							    new MotivoTramite(
							        NumericUtils.getLong(ath_turnoHistoria.getIdEtapa()),
							        MotivoTramiteCommon.IMPRESION_DE_DOCUMENTOS_PARA_CORRESPONDENCIA
							    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA, false, true
							);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_TURNO);
		}
	}

	/**
	 * Metodo para salvar un documento a reimprimir.
	 *
	 * @param acds_reimpresion            collection de tipo DocumentosSalida a ser validado para reimprimir
	 * @param as_userId            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el proceso.
	 * @param as_remoteIp            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarReimpresionDocumentos(
	    Collection<DocumentosSalida> acds_reimpresion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager               ldm_manager;
		ReimpresionDocumentosDAO lrdd_reimpresionDocumentosDAO;
		DocumentosSalidaDAO      ldsd_documentosSalidaDAO;

		ldm_manager                       = DaoManagerFactory.getDAOManager();
		lrdd_reimpresionDocumentosDAO     = DaoCreator.getReimpresionDocumentosDAO(ldm_manager);
		ldsd_documentosSalidaDAO          = DaoCreator.getDocumentosSalidaDAO(ldm_manager);

		try
		{
			if(CollectionUtils.isValidCollection(acds_reimpresion))
			{
				for(DocumentosSalida lc_temp : acds_reimpresion)
				{
					if(lc_temp != null)
					{
						int li_reimpresion;

						li_reimpresion = 1;

						lc_temp.setReimpresionDocumento(li_reimpresion);
						lc_temp.setIdUsuarioCreacion(as_userId);
						lc_temp.setIpCreacion(as_remoteIp);
						lc_temp.setIdUsuarioModificacion(as_userId);
						lc_temp.setIpModificacion(as_remoteIp);

						lrdd_reimpresionDocumentosDAO.insertDocumentos(lc_temp);
						ldsd_documentosSalidaDAO.updateReimpresionDocumentos(lc_temp);
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReimpresionDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Metodo para salvar un recibo a reimprimir.
	 *
	 * @param ar_reimpresion            documento a ser validado para reimprimir
	 * @param as_userId            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el proceso.
	 * @param as_remoteIp            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarReimpresionRecibos(
	    ReimpresionRecibos ar_reimpresion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ar_reimpresion != null)
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ar_reimpresion.getNir());

				if(ls_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = ls_solicitud.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitud))
					{
						DocumentosSalida lds_documentoSalida;
						long             ll_idDocumentoSalida;

						ll_idDocumentoSalida = ar_reimpresion.getIdDocumentoSalidaReimpresion();
						ar_reimpresion.setIdDocumentoSalida(ll_idDocumentoSalida);

						lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager).findById(ar_reimpresion);

						if(lds_documentoSalida != null)
						{
							int li_reimpresion;

							li_reimpresion = lds_documentoSalida.getReimpresionDocumento();

							li_reimpresion++;

							ar_reimpresion.setIdUsuarioCreacion(as_userId);
							ar_reimpresion.setIpCreacion(as_remoteIp);
							ar_reimpresion.setIdUsuarioModificacion(as_userId);
							ar_reimpresion.setIpModificacion(as_remoteIp);

							ar_reimpresion.setIdEcm(lds_documentoSalida.getIdEcm());
							ar_reimpresion.setIdNombreDocumento(lds_documentoSalida.getIdNombreDocumento());
							ar_reimpresion.setReimpresionDocumento(li_reimpresion);

							DaoCreator.getReimpresionDocumentosDAO(ldm_manager)
								          .insertRecibos(ar_reimpresion, ls_idSolicitud);
							DaoCreator.getDocumentosSalidaDAO(ldm_manager).updateReimpresionRecibos(ar_reimpresion);
						}
					}
				}
			}
		}

		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarReimpresionRecibos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de salvar los tipo documentales para el proceso de
	 * correcciones.
	 *
	 * @param actd_tiposDocumentales            Colección que contiene la información de los tipos documentales
	 *            que se desean insertar.
	 * @param as_idTurno            <code>String</code> que contiene el id del turno con el cual se
	 *            esta realizando el proceso.
	 * @param as_userId            Variable de tipo String que contiene el id del usuario que está
	 *            realizando el proceso.
	 * @param as_remoteIp            Variable de tipo String que contiene la ip del usuario que está
	 *            realizando el proceso.
	 * @return el valor de collection
	 * @throws B2BException             Señala que se ha producido una excepción
	 */
	public synchronized Collection<TipoDocumental> salvarTiposDocumentales(
	    Collection<TipoDocumental> actd_tiposDocumentales, String as_idTurno, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(actd_tiposDocumentales))
			{
				AccCompletitudDocumentalDAO lacd_DAO;
				CertificadosBusiness        lcb_certificadosBusiness;

				lacd_DAO                     = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager);
				lcb_certificadosBusiness     = getCertificadosBusiness();

				for(com.bachue.snr.prosnr01.model.registro.TipoDocumental lacd_iterador : actd_tiposDocumentales)
				{
					if(lacd_iterador != null)
					{
						lacd_iterador.setObligatorioTipoDocumental(
						    lacd_iterador.isSeleccionado() ? EstadoCommon.S : EstadoCommon.N
						);

						if(!StringUtils.isValidString(lacd_iterador.getIdCompletitud()))
						{
							String ls_idSolicitud;

							{
								Turno lt_turno;

								lt_turno           = lcb_certificadosBusiness.consultarTurno(as_idTurno, ldm_manager);
								ls_idSolicitud     = (lt_turno != null) ? lt_turno.getIdSolicitud() : null;
							}

							lacd_iterador.setIdSolicitud(ls_idSolicitud);
							lacd_iterador.setIdTurno(as_idTurno);
							lacd_iterador.setIdUsuarioCreacion(as_userId);
							lacd_iterador.setIpCreacion(as_remoteIp);
							lacd_iterador.setDigitalizado(EstadoCommon.N);
							lacd_DAO.insert(lacd_iterador);
						}
						else
						{
							AccCompletitudDocumental lacd_temp;

							lacd_temp = lacd_DAO.findById(lacd_iterador.getIdCompletitud());

							if(lacd_temp != null)
							{
								lacd_iterador.setIdUsuarioModificacion(as_userId);
								lacd_iterador.setIpModificacion(as_remoteIp);
								lacd_DAO.update(lacd_iterador);
							}
							else
								lacd_DAO.insert(lacd_iterador);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("salvarTiposDocumentalesCorrecciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return actd_tiposDocumentales;
	}

	/**
	 * Cargar documentos impresion.
	 *
	 * @param as_idTurno de as id turno
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<DocumentosSalida> cargarDocumentosImpresion(
	    String as_idTurno, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<DocumentosSalida> lcds_documentosImprimir;
		boolean                      lb_correciones;

		lcds_documentosImprimir     = null;
		lb_correciones              = false;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				DocumentosSalidaDAO ldsd_DAO;

				ldsd_DAO = DaoCreator.getDocumentosSalidaDAO(adm_manager);

				{
					Turno lt_turno;

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(as_idTurno);

					if(lt_turno != null)
					{
						if(
						    lt_turno.getIdEtapaActual()
							            .equals(
							        NumericUtils.getLongWrapper(EtapaCommon.IMPRESION_DOCUMENTOS_CORRESPONDENCIA)
							    )
						)
							lb_correciones = true;

						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lt_turno.getIdSolicitud());

						if(ls_solicitud != null)
						{
							String ls_idProceso;

							ls_idProceso     = ls_solicitud.getIdProceso();

							lcds_documentosImprimir = (StringUtils.isValidString(ls_idProceso)
									&& (ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
									|| ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)))
								? ldsd_DAO.findByIdTurnoEstadoImpresionSinRepo(as_idTurno)
								: ldsd_DAO.findByIdTurnoEstadoImpresion(as_idTurno, lb_correciones);
						}
					}
				}

				if(CollectionUtils.isValidCollection(lcds_documentosImprimir))
				{
					{
						Collection<TurnoDerivado> lctd_turnosDerivados;

						lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(adm_manager)
								                             .findByIdTurnoPadre(new TurnoDerivado(as_idTurno));

						if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
						{
							for(TurnoDerivado ltd_actual : lctd_turnosDerivados)
							{
								if(!ltd_actual.isIndVinculado())
								{
									Collection<DocumentosSalida> lcds_docsHijo;

									lcds_docsHijo = ldsd_DAO.findByIdTurnoEstadoImpresion(
										    ltd_actual.getIdTurnoHijo(), false
										);

									if(CollectionUtils.isValidCollection(lcds_docsHijo))
										lcds_documentosImprimir.addAll(lcds_docsHijo);
								}
							}
						}
					}

					{
						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(as_idTurno);

						if(lth_turnoHistoria != null)
						{
							DocumentosSalida lds_temp;

							lds_temp = ldsd_DAO.findByIdTurnoHistoriaTipoDocumental(
								    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria()),
								    TipoDocumentalCommon.COMUNICACION
								);

							if(lds_temp != null)
								lcds_documentosImprimir.add(lds_temp);
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDocumentosImpresion", lb2be_e);

				throw lb2be_e;
			}
		}

		return lcds_documentosImprimir;
	}

	/**
	 * Genera el documento PDF de la aprobación de relaciones en la etapa 190.
	 *
	 * @param actt_tramitesTurno Colección de tramites turno con la informacion de los procesos
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
	private LinkedHashMap<String, byte[]> crearPdfAprobacionEntrega(
	    Collection<TramiteTurno> actt_tramitesTurno, String as_idCirculo, boolean ab_firmar, String as_userId,
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

					lsb_constructor.append(IdentificadoresCommon.MC);
					lsb_constructor.append(IdentificadoresCommon.SIMBOLO_GUION);
					lsb_constructor.append(li_anio);
					lsb_constructor.append(IdentificadoresCommon.SIMBOLO_GUION);
					lsb_constructor.append(as_idCirculo);
					lsb_constructor.append(IdentificadoresCommon.SIMBOLO_GUION);

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

				if(CollectionUtils.isValidCollection(actt_tramitesTurno))
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

					for(TramiteTurno ltt_tmp : actt_tramitesTurno)
					{
						if(ltt_tmp != null)
						{
							String                    ls_fechaRadicacion;
							String                    ls_idTurno;
							Turno                     lt_turno;
							Collection<TurnoDerivado> lctd_turnosDerivados;
							Collection<String>        lcs_nirVinculados;
							boolean                   lb_turnoDerivado;

							ls_fechaRadicacion       = "";
							ls_idTurno               = ltt_tmp.getIdTurno();
							lt_turno                 = DaoCreator.getTurnoDAO(adm_manager).findById(ls_idTurno);
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
							ldb_builder.write(StringUtils.getStringNotNull(ltt_tmp.getTurnosAsociados()));

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(20));
							ldb_builder.getFont().setSize(8);
							ldb_builder.getFont().setBold(false);
							ldb_builder.write(StringUtils.getStringNotNull(ltt_tmp.getMotivoNoTramite()));

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

								ls_nirPrincipal     = StringUtils.getStringNotNull(ltt_tmp.getNir());
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
								ldb_builder.write(StringUtils.getStringNotNull(ltt_tmp.getNir()));

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
			clh_LOGGER.error("crearPdfAprobacionEntrega", lb2be_e);

			throw lb2be_e;
		}

		return llhm_return;
	}

	/**
	 * Escribir documentos salida.
	 *
	 * @param acds_documentos de acds documentos
	 * @param adb_builder de adb builder
	 * @param af_font de af font
	 * @param atd_DAO de atd DAO
	 * @param al_porcentajetablas de al porcentajetablas
	 * @param ai_tamanoLetra de ai tamano letra
	 * @param ai_contador de ai contador
	 * @return el valor de int
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	private Map<String, Integer> escribirDocumentosSalida(
	    Collection<DocumentosSalida> acds_documentos, DocumentBuilder adb_builder, Font af_font,
	    TipoDocumentalDAO atd_DAO, long al_porcentajetablas, int ai_tamanoLetra, int ai_contador, int ai_contadorSalto
	)
	    throws Exception
	{
		Map<String, Integer> lmsio_valores;

		lmsio_valores = new HashMap<String, Integer>(2);

		if(
		    CollectionUtils.isValidCollection(acds_documentos) && (adb_builder != null) && (af_font != null)
			    && (atd_DAO != null)
		)
		{
			for(DocumentosSalida lds_temp : acds_documentos)
			{
				if(lds_temp != null)
				{
					String ls_nombreDocumento;

					ls_nombreDocumento = lds_temp.getTipo();

					if(StringUtils.isValidString(ls_nombreDocumento))
					{
						adb_builder.insertCell();
						adb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
						adb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						af_font.setUnderline(0);
						adb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(al_porcentajetablas));

						af_font.setBold(false);

						{
							StringBuilder lsb_texto;

							lsb_texto = new StringBuilder(StringUtils.getString(ai_contador++));

							lsb_texto.append(IdentificadoresCommon.ESPACIO_VACIO);
							lsb_texto.append(TagCommon.TAG_SALTO);
							lsb_texto.append(ai_contadorSalto++);
							lsb_texto.append(IdentificadoresCommon.MAYOR_QUE);

							adb_builder.write(lsb_texto.toString());
						}

						adb_builder.insertCell();
						adb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
						adb_builder.getCellFormat().setPreferredWidth(PreferredWidth.fromPercent(al_porcentajetablas));
						af_font.setSize(ai_tamanoLetra);

						{
							StringBuilder lsb_texto;

							lsb_texto = new StringBuilder(
								    ls_nombreDocumento.replaceAll(
								        IdentificadoresCommon.SIMBOLO_GUION_BAJO, IdentificadoresCommon.ESPACIO_VACIO
								    )
								);
							lsb_texto.append(IdentificadoresCommon.ESPACIO_VACIO);
							lsb_texto.append(TagCommon.TAG_SALTO);
							lsb_texto.append(ai_contadorSalto++);
							lsb_texto.append(IdentificadoresCommon.MAYOR_QUE);

							adb_builder.write(lsb_texto.toString());
						}

						adb_builder.endRow();
					}
				}
			}
		}

		lmsio_valores.put("CONTADOR", NumericUtils.getInteger(ai_contador));
		lmsio_valores.put("CONTADOR_SALTOS", NumericUtils.getInteger(ai_contadorSalto));

		return lmsio_valores;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para econtrar las
	 * etapas de la entrega.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private Collection<Etapa> findEntrega(DAOManager adm_manager)
	    throws B2BException
	{
		Collection<Etapa> lc_data;

		try
		{
			String ls_etapasNotificacion;

			ls_etapasNotificacion = DaoCreator.getConstantesDAO(adm_manager)
					                              .findString(ConstanteCommon.ETAPAS_NOTIFICACION_PERSONAL);

			if(!StringUtils.isValidString(ls_etapasNotificacion))
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstanteCommon.ETAPAS_NOTIFICACION_PERSONAL;

				throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
			}

			lc_data = DaoCreator.getEtapaDAO(adm_manager).findEntrega(ls_etapasNotificacion.split(","));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findEntrega", lb2be_e);

			throw lb2be_e;
		}

		return lc_data;
	}

	/**
	 * Generar documento entrega.
	 *
	 * @param aofd_param de aefg param
	 * @param as_idTurno de as id turno
	 * @param ap_personaEntrega de ap persona entrega
	 * @param adp_datos de adp datos
	 * @param as_idUsuario de as id usuario
	 * @param ab_reimpresion de ab reimpresion
	 * @param adm_manager de adm manager
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	private byte[] generarDocumentoEntrega(
	    ObtenerFirmaDTO aofd_param, String as_idTurno, Persona ap_personaEntrega, Collection<DocumentosSalida> adp_datos,
	    String as_idUsuario, boolean ab_reimpresion, DAOManager adm_manager
	)
	    throws Exception
	{
		byte[] lba_return;

		lba_return = null;

		if(
		    (aofd_param != null) && StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_idUsuario)
			    && (ap_personaEntrega != null) && (adm_manager != null)
		)
		{
			try
			{
				ConstantesDAO lcd_DAO;

				lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

				{
					byte[] lba_plantilla;

					lba_plantilla = lcd_DAO.findImagenes(ConstanteCommon.PLANTILLA_ENTREGA_ORIP);

					if(lba_plantilla == null)
					{
						Object[] loa_messageArgs = new String[1];
						loa_messageArgs[0] = ConstanteCommon.PLANTILLA_ENTREGA_ORIP;

						throw new B2BException(ErrorKeys.NO_IMAGEN_CONSTANTE, loa_messageArgs);
					}
					else
					{
						String   ls_plantilla;
						TurnoDAO ltd_DAO;

						ls_plantilla     = new String(lba_plantilla);
						ltd_DAO          = DaoCreator.getTurnoDAO(adm_manager);

						if(StringUtils.isValidString(ls_plantilla))
						{
							Turno lt_turno;

							lt_turno = ltd_DAO.findById(as_idTurno);

							if(lt_turno != null)
							{
								String ls_tag;
								String ls_idCirculo;

								ls_tag           = TagCommon.TAG_MUN_OFI_ORIGEN;
								ls_idCirculo     = lt_turno.getIdCirculo();

								if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
								{
									Municipio lm_municipio;

									lm_municipio     = new Municipio();

									lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findByIdCirculo(
										    ls_idCirculo
										);

									if(lm_municipio != null)
									{
										String ls_munOficinaOrigen;
										ls_munOficinaOrigen = lm_municipio.getNombre();

										if(
										    ls_plantilla.contains(ls_tag)
											    && StringUtils.isValidString(ls_munOficinaOrigen)
										)
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_munOficinaOrigen);
									}
								}

								ls_tag           = TagCommon.TAG_ID_TURNO;
								ls_plantilla     = reemplazarTagEnPlantilla(ls_plantilla, ls_tag, as_idTurno);

								ls_tag = TagCommon.TAG_FECHA_LARGA;

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = escribirTagFechaLarga(ls_plantilla);

								ls_tag = TagCommon.TAG_PERSONA_ENTREGA;

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_personaEntrega;

									ls_personaEntrega = ap_personaEntrega.getNombreCompleto();

									if(!StringUtils.isValidString(ls_personaEntrega))
									{
										StringBuilder lsb_persona;

										lsb_persona = new StringBuilder();

										{
											String ls_primerApellido;

											ls_primerApellido = ap_personaEntrega.getPrimerApellido();
											lsb_persona.append(
											    StringUtils.isValidString(ls_primerApellido)
											    ? (ls_primerApellido + IdentificadoresCommon.ESPACIO_VACIO)
											    : IdentificadoresCommon.DATO_INVALIDO
											);
										}

										{
											String ls_segundoApellido;

											ls_segundoApellido = ap_personaEntrega.getSegundoApellido();
											lsb_persona.append(
											    StringUtils.isValidString(ls_segundoApellido)
											    ? (ls_segundoApellido + IdentificadoresCommon.ESPACIO_VACIO)
											    : IdentificadoresCommon.DATO_INVALIDO
											);
										}

										{
											String ls_primerNombre;

											ls_primerNombre = ap_personaEntrega.getPrimerNombre();
											lsb_persona.append(
											    StringUtils.isValidString(ls_primerNombre)
											    ? (ls_primerNombre + IdentificadoresCommon.ESPACIO_VACIO)
											    : IdentificadoresCommon.DATO_INVALIDO
											);
										}

										{
											String ls_segundoNombre;

											ls_segundoNombre = ap_personaEntrega.getSegundoNombre();
											lsb_persona.append(
											    StringUtils.isValidString(ls_segundoNombre)
											    ? (ls_segundoNombre + IdentificadoresCommon.ESPACIO_VACIO)
											    : IdentificadoresCommon.DATO_INVALIDO
											);
										}

										ls_personaEntrega = lsb_persona.toString();
									}

									if(StringUtils.isValidString(ls_personaEntrega))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_personaEntrega)
											);
									else
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, IdentificadoresCommon.ESPACIO_VACIO
											);
								}

								ls_tag = TagCommon.TAG_TIPO_DOCUMENTO_PERSONA_ENTREGA;

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_tipoDocumento;

									ls_tipoDocumento = ap_personaEntrega.getIdDocumentoTipo();

									if(StringUtils.isValidString(ls_tipoDocumento))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_tipoDocumento)
											);
									else
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, IdentificadoresCommon.ESPACIO_VACIO
											);
								}

								ls_tag = TagCommon.TAG_NUMERO_DOCUMENTO_PERSONA_ENTREGA;

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_numeroDocumento;

									ls_numeroDocumento = ap_personaEntrega.getNumeroDocumento();

									if(StringUtils.isValidString(ls_numeroDocumento))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ls_numeroDocumento)
											);
									else
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, IdentificadoresCommon.ESPACIO_VACIO
											);
								}

								ls_tag = TagCommon.TAG_USUARIO;

								if(ls_plantilla.contains(ls_tag))
								{
									if(StringUtils.isValidString(as_idUsuario))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(as_idUsuario)
											);
									else
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, IdentificadoresCommon.ESPACIO_VACIO
											);
								}

								ls_plantilla     = reemplazarTagPadFirmas(ls_plantilla);

								ls_tag = TagCommon.TAG_DOCUMENTOS_ENTREGA;

								if(ls_plantilla.contains(ls_tag))
								{
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

											ls_tagNew     = BookMarkCommon.DOCUMENTOS_ENTREGA_BKM + ls_tag;

											ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO
												+ ls_tagNew + IdentificadoresCommon.ESPACIO_VACIO
												+ ls_textoMitadInferior;
										}
									}

									{
										DocumentosSalida             lds_documentoSalida;
										DocumentosSalidaDAO          ldsd_DAO;
										Collection<DocumentosSalida> ldp_datos;
										Map<String, String>          lmss_resultados;

										lds_documentoSalida     = new DocumentosSalida(as_idTurno);
										ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(adm_manager);
										ldp_datos               = ab_reimpresion ? adp_datos
											                                     : ldsd_DAO.findByIdTurnoEstadoA(
											    lds_documentoSalida, true, true
											);
										lmss_resultados         = finalizarPlantilla(
											    ls_plantilla, null, false, adm_manager
											);

										if(CollectionUtils.isValidCollection(lmss_resultados))
											ls_plantilla = lmss_resultados.get(IdentificadoresCommon.PLANTILLA);

										if(CollectionUtils.isValidCollection(ldp_datos))
										{
											if(StringUtils.isValidString(ls_plantilla))
											{
												com.aspose.words.Document ld_doc;
												ByteArrayInputStream      lbais_docInStream;
												DocumentBuilder           ldb_builder;
												Table                     lt_table;
												Font                      lf_font;
												int                       li_tamanoLetra;
												long                      ll_porcentajetablas;
												int                       li_contador;
												int                       li_contadorSaltos;

												ld_doc                = null;
												lbais_docInStream     = new ByteArrayInputStream(
													    ls_plantilla.getBytes()
													);
												ld_doc                = new com.aspose.words.Document(
													    lbais_docInStream
													);
												ldb_builder           = new DocumentBuilder(ld_doc);
												ldb_builder.moveToBookmark(BookMarkCommon.DOCUMENTOS_ENTREGA);
												lf_font                 = ldb_builder.getFont();
												li_tamanoLetra          = 11;
												ll_porcentajetablas     = (long)50.0;
												li_contador             = 1;
												li_contadorSaltos       = 1;

												{
													Table lt_encabezado;

													lt_encabezado = ldb_builder.startTable();

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
													ldb_builder.write("Nro.");

													ldb_builder.insertCell();
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);
													lf_font.setSize(li_tamanoLetra);
													ldb_builder.write("Documento");

													ldb_builder.endRow();
													lt_encabezado.setAlignment(TableAlignment.CENTER);
													ldb_builder.endTable();
												}

												{
													TipoDocumentalDAO    ltocd_DAO;
													Map<String, Integer> lmsio_valores;

													ltocd_DAO         = DaoCreator.getTipoDocumentalDAO(adm_manager);
													lt_table          = ldb_builder.startTable();
													lmsio_valores     = escribirDocumentosSalida(
														    ldp_datos, ldb_builder, lf_font, ltocd_DAO,
														    ll_porcentajetablas, li_tamanoLetra, li_contador,
														    li_contadorSaltos
														);

													if(CollectionUtils.isValidCollection(lmsio_valores))
													{
														li_contador           = NumericUtils.getInt(
															    lmsio_valores.get("CONTADOR")
															);
														li_contadorSaltos     = NumericUtils.getInt(
															    lmsio_valores.get("CONTADOR_SALTOS")
															);
													}

													if(!ab_reimpresion)
													{
														Collection<TurnoDerivado> lctd_turnoDerivado;
														TurnoDerivado             ltd_turnosDerivados;

														ltd_turnosDerivados = new TurnoDerivado();
														ltd_turnosDerivados.setIdTurnoPadre(as_idTurno);

														lctd_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(
															    adm_manager
															).findByIdTurnoPadre(ltd_turnosDerivados);

														if(CollectionUtils.isValidCollection(lctd_turnoDerivado))
														{
															for(TurnoDerivado ltd_actual : lctd_turnoDerivado)
															{
																if(!ltd_actual.isIndVinculado())
																{
																	Collection<DocumentosSalida> ldp_docsDerivados;
																	DocumentosSalida             lds_derivado;

																	lds_derivado = new DocumentosSalida();

																	lds_derivado.setIdTurno(
																	    ltd_actual.getIdTurnoHijo()
																	);

																	ldp_docsDerivados = ldsd_DAO.findByIdTurnoEstadoA(
																		    lds_derivado, true
																		);

																	if(
																	    CollectionUtils.isValidCollection(
																		        ldp_docsDerivados
																		    )
																	)
																	{
																		lmsio_valores = escribirDocumentosSalida(
																			    ldp_docsDerivados, ldb_builder, lf_font,
																			    ltocd_DAO, ll_porcentajetablas,
																			    li_tamanoLetra, li_contador,
																			    li_contadorSaltos
																			);

																		if(
																		    CollectionUtils.isValidCollection(
																			        lmsio_valores
																			    )
																		)
																		{
																			li_contador           = NumericUtils.getInt(
																				    lmsio_valores.get("CONTADOR")
																				);
																			li_contadorSaltos     = NumericUtils.getInt(
																				    lmsio_valores.get(
																				        "CONTADOR_SALTOS"
																				    )
																				);
																		}
																	}
																}
															}
														}
													}

													Iterator<Row> lir_temp;

													lir_temp = lt_table.iterator();

													if(lir_temp.hasNext())
														lt_table.setAlignment(TableAlignment.CENTER);

													ldb_builder.endTable();

													ldb_builder.writeln(ControlChar.LINE_BREAK);

													{
														ByteArrayOutputStream lbaos_docOutStream;

														lbaos_docOutStream = new ByteArrayOutputStream(1);

														ld_doc.save(lbaos_docOutStream, SaveFormat.RTF);

														lba_return       = lbaos_docOutStream.toByteArray();
														ls_plantilla     = new String(lba_return);
													}

													{
														ByteArrayOutputStream lbaos_docOutStream;

														lbaos_docOutStream = new ByteArrayOutputStream(1);

														ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

														lba_return = lbaos_docOutStream.toByteArray();
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);
									}
								}

								lba_return     = new PDFGenerator().convertirRTFToPDF(lba_return, adm_manager);

								lba_return = corregirFirmaDocumento(lba_return, ls_plantilla, adm_manager);

								if(lba_return == null)
									throw new B2BException(ErrorKeys.ERROR_PDF_ERROR_GENERACION);
								else
									lba_return = solucionarBookMarkPadFirmas(adm_manager, lba_return, aofd_param);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarDocumentoEntrega", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_return;
	}
}
