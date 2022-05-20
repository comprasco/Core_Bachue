package com.bachue.snr.prosnr01.business.calificacion;

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

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;
import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudAsociadaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TipoActoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.DocumentoDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentalDAO;
import com.bachue.snr.prosnr01.dao.pgn.TipoDocumentoPublicoDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.DatosEtapaAnterior;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionOficiosCirculo;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.BitacoraBloqueo;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalNegacionCopias;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * clase para el manejo del negocio para supension
 * @author Heiner Castañeda
 *
 */
public class SuspensionBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SuspensionBusiness.class);

	/** Constante Firma Masiva Business*/
	private FirmaMasivaBusiness ifmb_firmaMasivaBusiness;

	/**
	 * Método de transacción con la base de datos, encargado de actualizar las observaciones del turno historia
	 * @param lth_param con el turno historia
	 * @param as_usuario con el usuario de la transacción
	 * @param as_remoteIp con la ip de la transacción
	 * @throws B2BException
	 */
	public synchronized void actualizarObservacionesTurnoHistoria(
	    TurnoHistoria lth_param, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			actualizarObservacionesTurnoHistoria(lth_param, as_usuario, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarObservacionesTurnoHistoria", lb2be_e);
			ldm_manager.setRollbackOnly();
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Trae la información ingresada en la etapa 80 anterior del caso
	 *
	 * @param as_idTurnoHistoria id del turno historia en tramite
	 * @return Objeto turno historia relacionado a la etapa 80 anterior
	 * @throws B2BException
	 */
	public synchronized DatosEtapaAnterior buscarEtapa80Anterior(String as_idTurnoHistoria)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		DatosEtapaAnterior ldea_datos;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ldea_datos      = null;

		try
		{
			if(StringUtils.isValidString(as_idTurnoHistoria))
			{
				TurnoHistoriaDAO lthd_turnoHistoriaDAO;
				TurnoHistoria    lth_turnoActual;

				lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lth_turnoActual           = lthd_turnoHistoriaDAO.findById(
					    NumericUtils.getLongWrapper(as_idTurnoHistoria)
					);

				if(lth_turnoActual != null)
				{
					BigDecimal lbd_idAnt190;

					lbd_idAnt190 = lth_turnoActual.getIdAnterior();

					if(NumericUtils.isValidBigDecimal(lbd_idAnt190))
					{
						TurnoHistoria lth_turno190;

						lth_turno190 = lthd_turnoHistoriaDAO.findById(NumericUtils.getLongWrapper(lbd_idAnt190));

						if(lth_turno190 != null)
						{
							BigDecimal lbd_idAnt80;

							lbd_idAnt80 = lth_turno190.getIdAnterior();

							if(
							    NumericUtils.isValidBigDecimal(lbd_idAnt80)
								    && (lbd_idAnt80.longValue() == EtapaCommon.ID_ETAPA_CALIFICACION)
							)
							{
								TurnoHistoria lth_turno80Anterior;

								lth_turno80Anterior = lthd_turnoHistoriaDAO.findById(
									    NumericUtils.getLongWrapper(lbd_idAnt80)
									);

								if(lth_turno80Anterior != null)
								{
									ldea_datos = new DatosEtapaAnterior();

									ldea_datos.setTurnoHistoria(lth_turno80Anterior);

									OficiosTexto lot_oficios;

									lot_oficios = DaoCreator.getOficiosTextoDAO(ldm_manager)
											                    .findByTurnoHistoria(
											    lth_turno80Anterior.getIdTurnoHistoria()
											);

									if(lot_oficios != null)
										ldea_datos.setOficiosTexto(lot_oficios);
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

			clh_LOGGER.error("buscarEtapa80Anterior", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ldea_datos;
	}

	/**
	 * Método de consulta con la base de datos el cual obtiene los oficios texto basado en un turno
	 * @param as_idTurno con el turno de la búsqueda
	 * @return con los oficios texto pertenecientes al turno
	 * @throws B2BException
	 */
	public synchronized OficiosTexto buscarOficiosTextoPorTurno(String as_idTurno)
	    throws B2BException
	{
		OficiosTexto lot_oficiosTexto;
		DAOManager   ldm_manager;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lot_oficiosTexto     = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				String ls_turnoHistoria;
				ls_turnoHistoria = DaoCreator.getDocumentosSalidaDAO(ldm_manager).findTurnoHistoriaByIdTurno(
					    as_idTurno
					);

				if(StringUtils.isValidString(ls_turnoHistoria))
					lot_oficiosTexto = DaoCreator.getOficiosTextoDAO(ldm_manager)
							                         .findByTurnoHistoria(
							    NumericUtils.getLongWrapper(ls_turnoHistoria)
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("buscarOficiosTextoPorTurno", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lot_oficiosTexto;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de consultar los datos de suspensión del trámite
	 * @param ath_parametros objeto de tipo TurnoHistoria
	 * @param as_usuario usuario en sesion
	 * @param as_localIp Variable que contiene la ip desde la cual se responde la petición.
	 * @param as_remoteIp Variable que contiene la ip desde la cual se solicita la petición.
	 * @return
	 * @throws B2BException
	 */
	public synchronized Suspension findDataSuspensionOfTheProcedure(
	    TurnoHistoria ath_parametros, boolean ab_solicitudDocONegacionCert, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Suspension ls_return;

		ls_return       = new Suspension();
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_parametros != null)
			{
				TurnoHistoriaDAO lthd_DAO;
				SolicitudDAO     lsd_DAO;
				TurnoHistoria    lth_th;

				lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lsd_DAO      = DaoCreator.getSolicitudDAO(ldm_manager);
				lth_th       = lthd_DAO.findById(ath_parametros);

				if(lth_th != null)
				{
					long      ll_idEtapa;
					Solicitud ls_s;

					ll_idEtapa     = NumericUtils.getLong(lth_th.getIdEtapa());
					ls_s           = new Solicitud();

					ls_s.setIdSolicitud(lth_th.getIdSolicitud());

					ls_s = lsd_DAO.findById(ls_s);

					if(ls_s != null)
					{
						SolicitudAsociada lsa_sa;
						boolean           lb_desistimento;
						boolean           lb_solicitud;

						lb_desistimento     = false;
						lsa_sa              = new SolicitudAsociada();
						lb_solicitud        = false;

						lsa_sa.setIdSolicitud(ls_s.getIdSolicitud());

						ls_return.setSolicitud1(ls_s);

						lsa_sa = DaoCreator.getSolicitudAsociadaDAO(ldm_manager).findByIdSolicitud(lsa_sa);

						if((lsa_sa == null) && (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO))
						{
							lsa_sa = new SolicitudAsociada();

							lsa_sa.setIdSolicitud1(ls_s.getIdSolicitud());

							lsa_sa              = DaoCreator.getSolicitudAsociadaDAO(ldm_manager).findByIdSol1(lsa_sa);
							lb_desistimento     = true;
						}

						lb_solicitud = lsa_sa != null;

						if(lb_solicitud || ab_solicitudDocONegacionCert)
						{
							Solicitud ls_solicitud2;

							ls_solicitud2 = new Solicitud();

							if(
							    ab_solicitudDocONegacionCert && (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && lb_solicitud
							)
								ls_solicitud2 = lsd_DAO.findById(lsa_sa.getIdSolicitud1());

							else if(
							    ab_solicitudDocONegacionCert && (ll_idEtapa != EtapaCommon.ID_ETAPA_460)
								    && (ll_idEtapa != EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
								    && (ll_idEtapa != EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
							)
								ls_solicitud2 = ls_s;
							else if(lb_solicitud)
							{
								if(!lb_desistimento)
									ls_solicitud2.setIdSolicitud(lsa_sa.getIdSolicitud1());
								else
									ls_solicitud2.setIdSolicitud(lsa_sa.getIdSolicitud());

								ls_solicitud2 = lsd_DAO.findById(ls_solicitud2);
							}

							if(ls_solicitud2 != null)
							{
								String ls_idPersona;

								ls_idPersona = ls_solicitud2.getIdPersona();

								ls_return.setSolicitud2(ls_solicitud2);

								if(StringUtils.isValidString(ls_idPersona))
								{
									{
										Persona lp_p;

										lp_p = new Persona();

										lp_p.setIdPersona(ls_idPersona);

										lp_p = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_p);

										if(lp_p != null)
										{
											DatosDelInteresado lddi_ddi;

											lddi_ddi = ls_return.getDatosDelInteresado();

											lddi_ddi.setPersona(lp_p);

											ls_return.setDatosDelInteresado(lddi_ddi);

											{
												Collection<Persona> lcp_cp;

												lcp_cp = new ArrayList<Persona>();

												lcp_cp.add(lp_p);

												ls_return.setPersona(lp_p);
												ls_return.setPersonas(lcp_cp);
											}
										}
									}

									{
										String ls_idDireccion;

										ls_idDireccion = ls_solicitud2.getIdDireccion();

										if(StringUtils.isValidString(ls_idDireccion))
										{
											PersonaDireccion lpd_pd;

											lpd_pd = new PersonaDireccion();

											lpd_pd.setIdPersona(ls_idPersona);
											lpd_pd.setIdDireccion(ls_idDireccion);

											lpd_pd = DaoCreator.getPersonaDireccionDAO(ldm_manager).findById(lpd_pd);

											if(lpd_pd != null)
											{
												String ls_tipodireccion;

												ls_tipodireccion = lpd_pd.getTipoDireccion();

												if(StringUtils.isValidString(ls_tipodireccion))
												{
													DatosDelInteresado lddi_ddi;

													lddi_ddi = ls_return.getDatosDelInteresado();

													if(ls_tipodireccion.equalsIgnoreCase(EstadoCommon.C))
														lddi_ddi.setDireccionCorrespondencia(lpd_pd);
													else if(ls_tipodireccion.equalsIgnoreCase(EstadoCommon.R))
														lddi_ddi.setDireccionResidencia(lpd_pd);

													ls_return.setDatosDelInteresado(lddi_ddi);
												}
											}
										}
									}

									{
										String ls_idTelefono;

										ls_idTelefono = ls_solicitud2.getIdTelefono();

										if(StringUtils.isValidString(ls_idTelefono))
										{
											PersonaTelefono lpt_pt;

											lpt_pt = new PersonaTelefono();

											lpt_pt.setIdPersona(ls_idPersona);
											lpt_pt.setIdTelefono(ls_idTelefono);

											lpt_pt = DaoCreator.getPersonaTelefonoDAO(ldm_manager).findById(lpt_pt);

											if(lpt_pt != null)
											{
												String ls_tipoTelefono;

												ls_tipoTelefono = lpt_pt.getTipoTelefono();

												if(StringUtils.isValidString(ls_tipoTelefono))
												{
													DatosDelInteresado lddi_ddi;

													lddi_ddi = ls_return.getDatosDelInteresado();

													if(ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.F))
														lddi_ddi.setTelefonoFijo(lpt_pt);
													else if(ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.M))
														lddi_ddi.setTelefonoMovil(lpt_pt);

													ls_return.setDatosDelInteresado(lddi_ddi);
												}
											}
										}
									}

									{
										String ls_idCorreoElectronico;

										ls_idCorreoElectronico = ls_solicitud2.getIdCorreoElectronico();

										if(StringUtils.isValidString(ls_idCorreoElectronico))
										{
											PersonaCorreoElectronico lpce_pce;

											lpce_pce = new PersonaCorreoElectronico();

											lpce_pce.setIdPersona(ls_idPersona);
											lpce_pce.setIdCorreoElectronico(ls_idCorreoElectronico);

											lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager)
													                 .findById(lpce_pce);

											if(lpce_pce != null)
											{
												DatosDelInteresado lddi_ddi;

												lddi_ddi = ls_return.getDatosDelInteresado();

												lddi_ddi.setCorreoElectronico(lpce_pce);

												ls_return.setDatosDelInteresado(lddi_ddi);
											}
										}
									}
								}
							}
						}
					}

					{
						Turno lt_t;

						lt_t = new Turno();

						lt_t.setIdTurno(lth_th.getIdTurno());

						lt_t = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_t);

						if(lt_t != null)
							ls_return.setTurno(lt_t);
					}

					{
						TurnoHistoria lth_anterior;

						lth_anterior = new TurnoHistoria();

						lth_anterior.setIdTurnoHistoria(NumericUtils.getLongWrapper(lth_th.getIdAnterior()));

						lth_anterior = lthd_DAO.findById(lth_anterior);

						if(lth_anterior != null)
						{
							ls_return.setObservacionesProcesoAnterior(lth_anterior.getObservacionesNoTramite());

							OficiosTexto loo_oficiosTexto;
							loo_oficiosTexto = DaoCreator.getOficiosTextoDAO(ldm_manager)
									                         .findByTurnoHistoria(lth_anterior.getIdTurnoHistoria());

							if(loo_oficiosTexto != null)
								ls_return.setConsideracion(loo_oficiosTexto.getConsideracion());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("findDataSuspensionOfTheProcedure", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

/**
 * Método de transacciones con la base de datos para generar el acto administrativo para la suspensión
 * @param asu_parametros objeto de tipo suspensión que retorna el método
 * @param ab_firmaMasiva indica si se desea realizar firma masiva o no
 * @param adm_manager objeto donde se obtiene informacion de la base de datos
 * @return
 * @throws B2BException
 */
	public synchronized Suspension generarActoAdministrativoSuspensiones(
	    Suspension asu_parametros, boolean ab_firmaMasiva, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			ConstantesDAO lcd_DAO;
			lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

			if(asu_parametros != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = asu_parametros.getTurnoHistoria();

				if((lth_turnoHistoria == null) || (NumericUtils.getLong(lth_turnoHistoria.getIdTurnoHistoria()) <= 0))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

				if(lth_turnoHistoria != null)
				{
					Turno lt_turno;
					lt_turno = new Turno();

					lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());

					lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

					if(lt_turno != null)
					{
						String     ls_tipo;
						String     ls_constante;
						Constantes lc_constante;

						byte[]  lba_plantilla;
						boolean lb_art18;
						boolean lb_art19;
						boolean lb_proceso39;
						boolean lb_proceso43;
						boolean lb_etapa80;
						boolean lb_etapa410;
						long    ll_etapa;

						ls_tipo          = asu_parametros.getTipo();
						ls_constante     = null;
						lb_art18         = false;
						lb_art19         = false;
						lb_proceso39     = false;
						lb_proceso43     = false;
						lb_etapa80       = false;
						lb_etapa410      = false;
						ll_etapa         = asu_parametros.getEtapa();

						if(StringUtils.isValidString(ls_tipo))
						{
							lb_art18         = ls_tipo.equalsIgnoreCase(Suspension.ART_18);
							lb_art19         = ls_tipo.equalsIgnoreCase(Suspension.ART_19);
							lb_proceso39     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
							lb_proceso43     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);
							lb_etapa80       = (ll_etapa == EtapaCommon.ID_ETAPA_CALIFICACION);
							lb_etapa410      = (ll_etapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS);

							if(lb_art18)
								ls_constante = ConstanteCommon.PLANTILLA_RESOLUCION_ART_18;
							else if(lb_art19)
								ls_constante = ConstanteCommon.PLANTILLA_RESOLUCION_ART_19;
							else if(lb_proceso39)
							{
								if(lb_etapa80 || lb_etapa410)
								{
									String ls_respuestaDesistimiento;

									ls_respuestaDesistimiento = StringUtils.getStringNotNull(
										    asu_parametros.getRespuestaDesistimiento()
										);

									if(ls_respuestaDesistimiento.equals(EstadoCommon.S))
										ls_constante = ConstanteCommon.PLANTILLA_RESOL_DESISTIMIENTO_APROBACION;
									else
										ls_constante = ConstanteCommon.PLANTILLA_RESOL_DESISTIMIENTO_NEGACION_CALIFICACION;
								}
								else
									ls_constante = ConstanteCommon.PLANTILLA_RESOL_DESISTIMIENTO_NEGACION_FUERA_DE_TERMINO;
							}
							else if(lb_proceso43)
								ls_constante = ConstanteCommon.PLANTILLA_RESOLUCION_RESTITUCION;
						}
						else
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);

						lc_constante = new Constantes();
						lc_constante.setIdConstante(ls_constante);

						lc_constante = lcd_DAO.findImagen(lc_constante);

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
							String              ls_plantilla;
							TurnoDAO            ltd_DAO;
							CirculoRegistralDao lcrd_DAO;
							SolicitudDAO        lsd_DAO;
							ActoDAO             lad_DAO;

							ls_plantilla     = new String(lba_plantilla);
							ltd_DAO          = DaoCreator.getTurnoDAO(adm_manager);
							lcrd_DAO         = DaoCreator.getCirculoRegistralDAO(adm_manager);
							lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);
							lad_DAO          = DaoCreator.getActoDAO(adm_manager);

							if(StringUtils.isValidString(ls_plantilla))
							{
								String ls_tag;
								Turno  lt_datosTurno;

								ls_tag            = null;
								lt_datosTurno     = ltd_DAO.findById(lt_turno);

								if(lt_datosTurno != null)
								{
									Map<String, String> lmss_datos;
									String              ls_orip;
									Solicitud           lso_solicitud1;
									Solicitud           lso_solicitud2;
									boolean             lb_solcitudAsociadaValida;

									lmss_datos         = null;
									ls_orip            = lt_datosTurno.getIdCirculo();
									lso_solicitud2     = null;
									lso_solicitud1     = new Solicitud();

									lso_solicitud1.setIdSolicitud(lt_datosTurno.getIdSolicitud());

									if(lb_art19 || lb_proceso39 || lb_proceso43)
										lso_solicitud2 = lsd_DAO.findById(asu_parametros.getSolicitud2());

									lb_solcitudAsociadaValida     = lso_solicitud2 != null;

									lso_solicitud1 = lsd_DAO.findById(lso_solicitud1);

									if(StringUtils.isValidString(ls_orip))
									{
										CirculoRegistral lcr_circuloRegistral;

										lcr_circuloRegistral = new CirculoRegistral();
										lcr_circuloRegistral.setIdCirculo(ls_orip);

										lcr_circuloRegistral = lcrd_DAO.findById(lcr_circuloRegistral);

										if(lcr_circuloRegistral != null)
										{
											String     ls_tipoOficina;
											String     ls_oficinaOrigen;
											BigDecimal lbd_version;

											ls_tipoOficina       = lcr_circuloRegistral.getTipoOficina();
											ls_oficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
											lbd_version          = lcr_circuloRegistral.getVersion();

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

											{
												ls_tag = "<TAG_CIUDAD>";

												if(
												    ls_plantilla.contains(ls_tag)
													    && StringUtils.isValidString(ls_oficinaOrigen)
													    && (lbd_version != null)
												)
												{
													OficinaOrigen loo_oficinaOrigen;
													loo_oficinaOrigen = new OficinaOrigen();

													loo_oficinaOrigen.setIdOficinaOrigen(ls_oficinaOrigen);
													loo_oficinaOrigen.setVersion(lbd_version);

													loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
															                          .findById(loo_oficinaOrigen);

													if(loo_oficinaOrigen != null)
													{
														String ls_pais;
														String ls_departamento;
														String ls_municipio;

														ls_pais             = loo_oficinaOrigen.getIdPais();
														ls_departamento     = loo_oficinaOrigen.getIdDepartamento();
														ls_municipio        = loo_oficinaOrigen.getIdMunicipio();

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

															lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																	                     .findById(lm_municipio);

															if(lm_municipio != null)
															{
																String ls_nombreMun;
																ls_nombreMun = lm_municipio.getNombre();

																if(StringUtils.isValidString(ls_nombreMun))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombreMun
																		);
															}
														}
													}
												}
											}
										}
									}

									{
										String ls_turno;
										ls_turno = lt_turno.getIdTurno();

										if(StringUtils.isValidString(ls_turno))
										{
											ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);

											ls_tag = "<TAG_DOCUMENTO_TURNO>";

											if(ls_plantilla.contains(ls_tag))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
										}
									}

									ls_tag = "<TAG_NIR>";

									if(ls_plantilla.contains(ls_tag))
									{
										if(lso_solicitud1 != null)
											ls_plantilla = ls_plantilla.replace(ls_tag, lso_solicitud1.getNir());
									}

									ls_tag = "<TAG_NIR_VINCULADO>";

									if(ls_plantilla.contains(ls_tag))
									{
										SolicitudAsociada lsa_tmp;
										lsa_tmp = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
												                .findByIdProceso(lt_turno.getIdSolicitud(), true);

										if(lsa_tmp != null)
										{
											String ls_nir1;

											ls_nir1 = lsa_tmp.getNirSolicitud1();

											if(StringUtils.isValidString(ls_nir1))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir1);
										}
									}

									if(lso_solicitud1 != null)
									{
										ls_plantilla     = reemplazarTagsDocumento(
											    adm_manager, lso_solicitud1, lt_datosTurno, ls_plantilla
											);

										ls_tag = "<TAG_NOMBRE_INTERESADO>";

										String ls_numeroDocumento;
										ls_numeroDocumento = null;

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_idPersona;
											ls_idPersona = lso_solicitud1.getIdPersona();

											if(
											    (lb_art19 || lb_proceso39 || lb_proceso43)
												    && (lb_solcitudAsociadaValida)
											)
												ls_idPersona = lso_solicitud2.getIdPersona();

											if(StringUtils.isValidString(ls_idPersona))
											{
												Persona lp_persona;
												lp_persona = new Persona();

												lp_persona.setIdPersona(ls_idPersona);

												lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

												if(lp_persona != null)
												{
													StringBuilder lsb_nombre;
													String        ls_idTipoDocumento;

													lsb_nombre             = new StringBuilder();
													ls_idTipoDocumento     = lp_persona.getIdDocumentoTipo();
													ls_numeroDocumento     = lp_persona.getNumeroDocumento();

													if(
													    StringUtils.isValidString(ls_idTipoDocumento)
														    && !ls_idTipoDocumento.contentEquals(
														        IdentificadoresCommon.NIT
														    )
													)
													{
														String ls_primerNombre;
														String ls_segundoNombre;
														String ls_primeApellido;
														String ls_segundoApellido;

														ls_primerNombre        = lp_persona.getPrimerNombre();
														ls_segundoNombre       = lp_persona.getSegundoNombre();
														ls_primeApellido       = lp_persona.getPrimerApellido();
														ls_segundoApellido     = lp_persona.getSegundoApellido();

														lsb_nombre.append(
														    (StringUtils.isValidString(ls_primerNombre)
															    && !ls_primerNombre.equalsIgnoreCase("null"))
														    ? ls_primerNombre : ""
														);
														lsb_nombre.append(
														    (StringUtils.isValidString(ls_segundoNombre)
															    && !ls_segundoNombre.equalsIgnoreCase("null"))
														    ? (" " + ls_segundoNombre) : ""
														);
														lsb_nombre.append(
														    (StringUtils.isValidString(ls_primeApellido)
															    && !ls_primeApellido.equalsIgnoreCase("null"))
														    ? (" " + ls_primeApellido) : ""
														);
														lsb_nombre.append(
														    (StringUtils.isValidString(ls_segundoApellido)
															    && !ls_segundoApellido.equalsIgnoreCase("null"))
														    ? (" " + ls_segundoApellido) : ""
														);
													}
													else
														lsb_nombre.append(lp_persona.getRazonSocial());

													ls_plantilla = ls_plantilla.replace(
														    ls_tag, (lsb_nombre.toString()).trim()
														);
												}
											}
										}

										ls_tag = "<TAG_DOCUMENTO_INTERESADO>";

										if(ls_plantilla.contains(ls_tag))
										{
											if(StringUtils.isValidString(ls_numeroDocumento))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_numeroDocumento);
											else
												ls_plantilla = ls_plantilla.replace(ls_tag, " ");
										}

										ls_tag = "<TAG_CALIDAD_SOLICITANTE>";

										if(ls_plantilla.contains(ls_tag))
										{
											CalidadSolicitante lcs_calidadSol;

											lcs_calidadSol = DaoCreator.getCalidadSolicitanteDAO(adm_manager)
													                       .findById(
													    lb_solcitudAsociadaValida
													    ? lso_solicitud2.getIdCalidadSolicitante()
													        : lso_solicitud1.getIdCalidadSolicitante()
													);

											if(lcs_calidadSol != null)
												ls_plantilla = ls_plantilla.replace(ls_tag, lcs_calidadSol.getNombre());
										}
									}

									{
										ls_tag = "<TAG_CONTENTIVA_DE>";

										if(ls_plantilla.contains(ls_tag))
										{
											Acto             la_acto;
											Collection<Acto> lca_actos = null;

											la_acto = new Acto();

											la_acto.setIdSolicitud(lt_datosTurno.getIdSolicitud());
											la_acto.setIdCirculo(lt_datosTurno.getIdCirculo());

											lca_actos = lad_DAO.findByIdSolicitudActivoPrec(la_acto);

											if(CollectionUtils.isValidCollection(lca_actos))
											{
												StringBuilder lsb_sb;
												TipoActoDAO   ltad_DAO;
												int           li_contador;

												lsb_sb          = new StringBuilder();
												ltad_DAO        = DaoCreator.getTipoActoDAO(adm_manager);
												li_contador     = 1;

												for(Acto la_iterador : lca_actos)
												{
													if(la_iterador != null)
													{
														TipoActo lta_tipoActo;
														int      li_size;
														String   ls_signo;

														lta_tipoActo     = new TipoActo();
														li_size          = lca_actos.size();
														ls_signo         = "";

														if((li_size > 1) && (li_contador != li_size))
															ls_signo = ", ";

														lta_tipoActo.setIdTipoActo(la_iterador.getIdTipoActo());

														lta_tipoActo = ltad_DAO.findById(lta_tipoActo);

														if(lta_tipoActo != null)
														{
															lsb_sb.append(lta_tipoActo.getNombre());
															lsb_sb.append(ls_signo);
														}

														li_contador++;
													}
												}

												ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
											}
										}
									}

									{
										ls_tag = "<TAG_ID_SOLICITUD_MATRICULA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_idCirculo;
											ls_idCirculo = lt_turno.getIdCirculo();

											if(StringUtils.isValidString(ls_idCirculo))
											{
												SolicitudMatriculaActo             lsma_solicitudMatricula;
												Collection<SolicitudMatriculaActo> lcsma_datos;

												lsma_solicitudMatricula = new SolicitudMatriculaActo();
												lsma_solicitudMatricula.setIdSolicitud(lso_solicitud1.getIdSolicitud());
												lsma_solicitudMatricula.setIdCirculo(ls_idCirculo);

												lcsma_datos = DaoCreator.getSolicitudMatriculaActoDAO(adm_manager)
														                    .findByIdSolicitudCirculo(
														    lsma_solicitudMatricula, false
														);

												if(CollectionUtils.isValidCollection(lcsma_datos))
												{
													StringBuilder lsb_sb;
													int           li_contador;
													long          ll_repetida = 0;

													lsb_sb          = new StringBuilder();
													li_contador     = 1;

													for(SolicitudMatriculaActo lsma_iterador : lcsma_datos)
													{
														if(lsma_iterador != null)
														{
															if(ll_repetida != lsma_iterador.getIdMatricula())
															{
																ll_repetida = lsma_iterador.getIdMatricula();

																int    li_size;
																String ls_signo;

																li_size      = lcsma_datos.size();
																ls_signo     = "";

																if(li_size > 1)
																	ls_signo = ", ";

																if(li_contador == li_size)
																	ls_signo = IdentificadoresCommon.PUNTO;

																lsb_sb.append(lsma_iterador.getIdCirculo());
																lsb_sb.append("-");
																lsb_sb.append(lsma_iterador.getIdMatricula());
																lsb_sb.append(ls_signo);

																li_contador++;
															}
														}
													}

													ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb);
												}
											}
										}
									}

									{
										ls_tag = "<TAG_CONSIDERACION_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getConsideracion();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_MOTIVO_DEVOLUCION_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getConsideracion();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_CAUSAL_DEVOLUCION_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getConsideracion();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_PERTINENCIA_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getPertinencia();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_ARTICULO_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getArticulo();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_RAZON_1_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getRazon1();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}

									{
										ls_tag = "<TAG_RAZON_2_PANTALLA>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;
											ls_tmp = asu_parametros.getRazon2();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, "\"2." + ls_tmp + ".\"");
										}
									}

									{
										Calendar lc_fecha;

										lc_fecha = Calendar.getInstance();

										{
											int li_dia;

											li_dia = lc_fecha.get(Calendar.DAY_OF_MONTH);

											if(ls_plantilla.contains("<TAG_DIAS_LETRAS>"))
											{
												String ls_dia;

												ls_dia = NumericUtils.convertirLetras(li_dia, false);

												if(StringUtils.isValidString(ls_dia))
													ls_plantilla = ls_plantilla.replace("<TAG_DIAS_LETRAS>", ls_dia);
											}

											if(ls_plantilla.contains("<TAG_DIAS>") && (li_dia > 0))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_DIAS>", StringUtils.getString(li_dia)
													);
										}

										if(ls_plantilla.contains("<TAG_MES_LETRAS>"))
										{
											int    li_mes;
											String ls_mes;

											li_mes     = lc_fecha.get(Calendar.MONTH);
											ls_mes     = DateUtils.getMes(li_mes + 1);

											if(StringUtils.isValidString(ls_mes))
												ls_plantilla = ls_plantilla.replace("<TAG_MES_LETRAS>", ls_mes);
										}

										{
											int li_anio;

											li_anio = lc_fecha.get(Calendar.YEAR);

											if(ls_plantilla.contains("<TAG_ANIO_LETRAS>"))
											{
												String ls_anio;

												ls_anio = NumericUtils.convertirLetras(li_anio, false);

												if(StringUtils.isValidString(ls_anio))
													ls_plantilla = ls_plantilla.replace("<TAG_ANIO_LETRAS>", ls_anio);
											}

											if(ls_plantilla.contains("<TAG_ANIO>") && (li_anio > 0))
												ls_plantilla = ls_plantilla.replace(
													    "<TAG_ANIO>", StringUtils.getString(li_anio)
													);
										}
									}

									{
										Constantes lc_usuarioFirma;
										byte[]     lba_resolucion;
										String     ls_tagUsuarioFirma;
										int        li_incrX = 0;
										int        li_incrY = 0;

										lc_usuarioFirma     = new Constantes();
										ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

										lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

										Date   ld_fechaResolucion;
										Long   ll_NumeroResolucion;
										String ls_resolucion;
										String ls_oficio;
										Date   ld_fechaOficio;

										ld_fechaResolucion      = null;
										ll_NumeroResolucion     = null;
										ls_resolucion           = null;
										ls_oficio               = null;
										ld_fechaOficio          = null;

										if(ab_firmaMasiva)
										{
											NumeracionOficiosCirculo lnoc_numeracionOficios;
											lnoc_numeracionOficios = findNumeracionOficiosCirculo(
												    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
												);

											if(lnoc_numeracionOficios != null)
											{
												ls_oficio          = lnoc_numeracionOficios.getConsecutivoGenerado();
												ld_fechaOficio     = new Date();
											}

											{
												ls_tag = "<TAG_RESOLUCION>";

												NumeracionResolucionCirculo lnrc_numeracionCirculo;

												lnrc_numeracionCirculo = findNumeracionResolucionCirculo(
													    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
													);

												if(lnrc_numeracionCirculo != null)
													ls_resolucion = lnrc_numeracionCirculo.getConsecutivogenerado();

												if(ls_plantilla.contains(ls_tag) && (lnrc_numeracionCirculo != null))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_resolucion);

												ll_NumeroResolucion = lnrc_numeracionCirculo.getConsecutivoResolucion();
											}

											{
												ls_tag = "<TAG_FECHA_RESOL>";

												Date             ld_date;
												SimpleDateFormat formatter;
												String           ls_date;

												ld_date                = new Date();
												ld_fechaResolucion     = ld_date;
												formatter              = new SimpleDateFormat("dd/MM/yyyy");
												ls_date                = StringUtils.getStringNotNull(
													    formatter.format(ld_date)
													);

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_date);
											}
										}

										lc_usuarioFirma     = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
										ls_plantilla        = getFirmaMasivaBusiness()
												                      .reemplazarUsuarioFirmaCargo(
												    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
												    "<TAG_CARGO_FIRMA_SUSPENSION>"
												);

										lmss_datos         = finalizarPlantilla(
											    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
											);
										ls_plantilla       = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
										lba_resolucion     = new PDFGenerator().convertirRTFToPDF(
											    ls_plantilla.getBytes(), adm_manager
											);

										if(lba_resolucion == null)
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

											lba_resolucion = getFirmaMasivaBusiness()
													                 .reemplazarBookmarsFirma(
													    lba_resolucion, lba_grafo, li_incrX, li_incrY
													);
										}

										asu_parametros.setResolucionPDF(lba_resolucion);

										{
											ImagenesDAO         lid_DAO;
											DocumentosSalidaDAO ldsd_DAO;
											long                ll_idDocumentoSalida;
											long                li_secuencia;

											boolean lb_existeImagen;

											Imagenes         li_imagenes;
											DocumentosSalida lds_documentosSalida;

											lid_DAO      = DaoCreator.getImagenesDAO(adm_manager);
											ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

											li_imagenes              = new Imagenes();
											lds_documentosSalida     = new DocumentosSalida();

											ll_idDocumentoSalida = asu_parametros.getIdResolucion();
											li_imagenes.setImagenBlob(asu_parametros.getResolucionPDF());

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

											li_imagenes.setTipoArchivo(ExtensionCommon.PDF);
											li_imagenes.setIdUsuarioCreacion(as_userId);
											li_imagenes.setIdUsuarioModificacion(as_userId);
											li_imagenes.setIpCreacion(as_remoteIp);
											li_imagenes.setIpModificacion(as_remoteIp);
											li_imagenes.setCodigoVerificacion(
											    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
											);

											li_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen);

											if(!lb_existeImagen)
											{
												if(li_secuencia <= 0)
													throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

												lds_documentosSalida.setIdImagen(
												    NumericUtils.getLongWrapper(li_secuencia)
												);
												lds_documentosSalida.setTipo(TipoArchivoCommon.RESOLUCION);
												lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
												lds_documentosSalida.setIdTipoDocumental(
												    TipoDocumentalCommon.RESOLUCION
												);
												lds_documentosSalida.setRepositorio(
												    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
												);

												lds_documentosSalida.setFechaResolucion(ld_fechaResolucion);
												lds_documentosSalida.setConsecutivoResolucion(ll_NumeroResolucion);
												lds_documentosSalida.setFechaOficio(ld_fechaOficio);
												lds_documentosSalida.setConsecutivoOficio(ls_oficio);
												lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
												lds_documentosSalida.setIdTurnoHistoria(
												    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
												);
												lds_documentosSalida.setIdSolicitud(lso_solicitud1.getIdSolicitud());

												Collection<DocumentosSalida> lcds_documentosExistentes;

												lcds_documentosExistentes = ldsd_DAO.findByIdTurnoHistoriaTipo(
													    lds_documentosSalida
													);

												if(!CollectionUtils.isValidCollection(lcds_documentosExistentes))
												{
													long ll_idDocumento;

													lds_documentosSalida.setIdUsuarioCreacion(as_userId);
													lds_documentosSalida.setIpCreacion(as_remoteIp);
													lds_documentosSalida.setFechaResolucion(ld_fechaResolucion);
													lds_documentosSalida.setConsecutivoResolucion(ll_NumeroResolucion);
													ll_idDocumento = ldsd_DAO.insertOrUpdate(
														    lds_documentosSalida, true
														);

													if(ll_idDocumento > 0)
														asu_parametros.setIdResolucion(ll_idDocumento);
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

															lds_documentosSalida.setIdEcm(null);
															lds_documentosSalida.setFechaResolucion(ld_fechaResolucion);
															lds_documentosSalida.setConsecutivoResolucion(
															    ll_NumeroResolucion
															);

															long ll_idDocumento;

															ll_idDocumento = ldsd_DAO.insertOrUpdate(
																    lds_documentosSalida, false
																);

															if(ll_idDocumento > 0)
																asu_parametros.setIdResolucion(ll_idDocumento);
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
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarActoAdministrativoSuspensiones", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarActoAdministrativoSuspensiones", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}

		return asu_parametros;
	}

	/**
	 * Método de manejo de transacciones con la base de datos con el fin de generar el comunicado de suspensiones
	 * @param asu_parametros objeto de tipo suspensión que retorna el método
	 * @param ab_firmaMasiva indica si se desea realizar firma masiva o no
	 * @param adm_manager objeto donde se obtiene informacion de la base de datos
	 * @param as_userId  Variable de tipo String que contiene el usuario que realiza la acción.
	 * @param as_remoteIp Variable de tipo String que contiene la ip desde la cual se realiza la acción.
	 * @return
	 * @throws B2BException
	 */
	public synchronized Suspension generarComunicadoSuspensiones(
	    Suspension asu_parametros, boolean ab_firmaMasiva, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			ConstantesDAO lcd_DAO;
			lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

			if(asu_parametros != null)
			{
				TurnoHistoria lth_turnoHistoria;
				lth_turnoHistoria = asu_parametros.getTurnoHistoria();

				if(lth_turnoHistoria != null)
				{
					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

					if(lth_turnoHistoria != null)
					{
						Turno lt_turno;
						lt_turno = new Turno();

						lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());

						lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

						if(lt_turno != null)
						{
							String     ls_tipo;
							String     ls_constante;
							Constantes lc_constante;

							byte[] lba_plantilla;

							boolean       lb_art18;
							boolean       lb_art19;
							boolean       lb_proceso39;
							boolean       lb_proceso43;
							boolean       lb_proceso41;
							boolean       lb_esSolicitudDocumentos;
							boolean       lb_esProcedeNoCorreccion;
							boolean       lb_esAnalistaCopias;
							boolean       lb_esNegarSolicitudCertificados;
							boolean       lb_etapa80;
							long          ll_etapa80;
							StringBuilder lsb_nombre;

							ls_tipo          = asu_parametros.getTipo();
							ls_constante     = null;

							lb_art18                            = false;
							lb_art19                            = false;
							lb_proceso39                        = false;
							lb_proceso43                        = false;
							lb_proceso41                        = false;
							lb_etapa80                          = false;
							lb_esSolicitudDocumentos            = asu_parametros.isEsSolicitudDocumentos();
							lb_esProcedeNoCorreccion            = asu_parametros.isEsProcedeNoCorreccion();
							lb_esAnalistaCopias                 = asu_parametros.isEsAnalistaCopias();
							lb_esNegarSolicitudCertificados     = asu_parametros.isEsNegarSolicitudCertificados();
							lsb_nombre                          = new StringBuilder();

							ll_etapa80 = asu_parametros.getEtapa();

							if(StringUtils.isValidString(ls_tipo))
							{
								lb_art18         = ls_tipo.equalsIgnoreCase(Suspension.ART_18);
								lb_art19         = ls_tipo.equalsIgnoreCase(Suspension.ART_19);
								lb_proceso39     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
								lb_proceso43     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);
								lb_proceso41     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
								lb_etapa80       = ll_etapa80 == EtapaCommon.ID_ETAPA_CALIFICACION;

								if(lb_art18)
									ls_constante = ConstanteCommon.PLANTILLA_COMUNICADO_ART_18;
								else if(lb_art19)
									ls_constante = ConstanteCommon.PLANTILLA_COMUNICADO_ART_19;
								else if(lb_proceso39)
								{
									if(lb_etapa80)
									{
										String ls_respuestaDesistimiento;

										ls_respuestaDesistimiento = StringUtils.getStringNotNull(
											    asu_parametros.getRespuestaDesistimiento()
											);

										if(ls_respuestaDesistimiento.equals(EstadoCommon.S))
											ls_constante = ConstanteCommon.PLANTILLA_COMUNICADO_DESISTIMIENTO_APROBACION;
										else
											ls_constante = ConstanteCommon.PLANTILLA_COMUNICADO_DESISTIMIENTO_NEGACION_CALIFICACION;
									}
									else
										ls_constante = ConstanteCommon.PLANTILLA_COMUNICADO_DESISTIMIENTO_NEGACION_FUERA_DE_TERMINO;
								}
								else if(lb_proceso43)
									ls_constante = ConstanteCommon.PLANTILLA_OFICIO_RECHAZO_RESTITUCION;
								else if(lb_proceso41)
								{
									String ls_razonANegarProrroga;
									ls_razonANegarProrroga = asu_parametros.getRazonANegar();

									if(
									    !asu_parametros.isEsPersonaEntidadJuridica()
										    && StringUtils.isValidString(ls_razonANegarProrroga)
									)
									{
										if(
										    ls_razonANegarProrroga.equalsIgnoreCase(
											        IdentificadoresCommon.VENCIMIENTO_TERMINO
											    )
										)
											ls_constante = ConstanteCommon.PLANTILLA_NO_PROCEDE_CORRECION;
										else if(
										    ls_razonANegarProrroga.equalsIgnoreCase(
											        IdentificadoresCommon.VENCIMIENTO_PAGO
											    )
										)
											ls_constante = ConstanteCommon.PLANTILLA_NO_PROCEDE_CORRECION_MAYOR_VALOR;
									}
									else
										ls_constante = ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_NOPROCEDE;
								}
							}
							else if(lb_esSolicitudDocumentos)
								ls_constante = ConstanteCommon.PLANTILLA_OFICIO_SOLICITUD_DOCUMENTACION;
							else if(lb_esProcedeNoCorreccion)
								ls_constante = ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_NOPROCEDE;
							else if(lb_esAnalistaCopias)
								ls_constante = ConstanteCommon.PLANTILLA_EXPEDICION_COPIAS_NEGACION;
							else if(lb_esNegarSolicitudCertificados)
								ls_constante = ConstanteCommon.PLANTILLA_NEGACION_EXPEDICION_CERTIFICADOS;

							lc_constante = new Constantes();
							lc_constante.setIdConstante(ls_constante);

							lc_constante = lcd_DAO.findImagen(lc_constante);

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
								String                  ls_plantilla;
								TurnoDAO                ltd_DAO;
								CirculoRegistralDao     lcrd_DAO;
								DocumentoDAO            ldd_DAO;
								TipoDocumentoPublicoDAO ltdpd_DAO;
								SolicitudDAO            lsd_DAO;
								ActoDAO                 lad_DAO;

								ls_plantilla     = new String(lba_plantilla);
								ltd_DAO          = DaoCreator.getTurnoDAO(adm_manager);
								lcrd_DAO         = DaoCreator.getCirculoRegistralDAO(adm_manager);
								ldd_DAO          = DaoCreator.getDocumentoDAO(adm_manager);
								ltdpd_DAO        = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager);
								lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);
								lad_DAO          = DaoCreator.getActoDAO(adm_manager);

								if(StringUtils.isValidString(ls_plantilla))
								{
									String ls_tag;
									Turno  lt_datosTurno;

									ls_tag            = null;
									lt_datosTurno     = ltd_DAO.findById(lt_turno);

									if(lt_datosTurno != null)
									{
										Map<String, String> lmss_datos;
										String              ls_orip;
										String              ls_destinatario;
										String              ls_direccioncompleta;
										String              ls_idPais;
										String              ls_idDepartamento;
										String              ls_idMunicipio;
										Solicitud           lso_solicitud1;
										Solicitud           lso_solicitud2;

										lmss_datos               = null;
										ls_orip                  = lt_datosTurno.getIdCirculo();
										lso_solicitud1           = null;
										lso_solicitud2           = null;
										ls_destinatario          = null;
										ls_direccioncompleta     = null;
										ls_idPais                = null;
										ls_idDepartamento        = null;
										ls_idMunicipio           = null;
										lso_solicitud1           = new Solicitud();

										lso_solicitud1.setIdSolicitud(lt_datosTurno.getIdSolicitud());

										lso_solicitud1 = lsd_DAO.findById(lso_solicitud1);

										if(
										    lb_art19 || lb_proceso39 || lb_proceso43 || lb_proceso41
											    || asu_parametros.isEsSolicitudDocumentos()
										)
											lso_solicitud2 = lsd_DAO.findById(asu_parametros.getSolicitud2());

										if(StringUtils.isValidString(ls_orip))
										{
											CirculoRegistral lcr_circuloRegistral;

											lcr_circuloRegistral = new CirculoRegistral();
											lcr_circuloRegistral.setIdCirculo(ls_orip);

											lcr_circuloRegistral = lcrd_DAO.findById(lcr_circuloRegistral);

											if(lcr_circuloRegistral != null)
											{
												String     ls_tipoOficina;
												String     ls_oficinaOrigen;
												BigDecimal lbd_version;

												ls_tipoOficina       = lcr_circuloRegistral.getTipoOficina();
												ls_oficinaOrigen     = lcr_circuloRegistral.getIdOficinaOrigen();
												lbd_version          = lcr_circuloRegistral.getVersion();

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
													ls_destinatario = lcr_circuloRegistral.getNombre();

													if(StringUtils.isValidString(ls_destinatario))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_destinatario);
												}

												{
													ls_tag = "<TAG_CIUDAD>";

													if(
													    ls_plantilla.contains(ls_tag)
														    && StringUtils.isValidString(ls_oficinaOrigen)
														    && (lbd_version != null)
													)
													{
														OficinaOrigen loo_oficinaOrigen;
														loo_oficinaOrigen = new OficinaOrigen();

														loo_oficinaOrigen.setIdOficinaOrigen(ls_oficinaOrigen);
														loo_oficinaOrigen.setVersion(lbd_version);

														loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
																                          .findById(loo_oficinaOrigen);

														if(loo_oficinaOrigen != null)
														{
															String ls_pais;
															String ls_departamento;
															String ls_municipio;

															ls_pais             = loo_oficinaOrigen.getIdPais();
															ls_departamento     = loo_oficinaOrigen.getIdDepartamento();
															ls_municipio        = loo_oficinaOrigen.getIdMunicipio();

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

																lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
																		                     .findById(lm_municipio);

																if(lm_municipio != null)
																{
																	String ls_nombreMun;
																	ls_nombreMun = lm_municipio.getNombre();

																	if(StringUtils.isValidString(ls_nombreMun))
																		ls_plantilla = ls_plantilla.replace(
																			    ls_tag, ls_nombreMun
																			);
																}
															}
														}
													}
												}
											}
										}

										{
											String ls_turno;
											ls_turno = lt_turno.getIdTurno();

											if(StringUtils.isValidString(ls_turno))
											{
												ls_tag = "<TAG_ACC_TUR_ID_TURNO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);

												ls_tag = "<TAG_DOCUMENTO_TURNO>";

												if(ls_plantilla.contains(ls_tag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_turno);
											}
										}

										if(lb_esNegarSolicitudCertificados && (lso_solicitud1 != null))
										{
											ls_tag = "<TAG_TIPO_CERTIFICADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Subproceso lsp_subproceso;

												lsp_subproceso = DaoCreator.getSubprocesoDAO(adm_manager)
														                       .findById(
														    lso_solicitud1.getIdProceso(),
														    lso_solicitud1.getIdSubproceso()
														);

												if(lsp_subproceso != null)
												{
													String ls_nombre;
													ls_nombre = lsp_subproceso.getNombre();

													if(StringUtils.isValidString(ls_nombre))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombre);
												}
											}
										}

										if((lso_solicitud1 != null) || (lb_proceso41 && (lso_solicitud2 != null)))
										{
											String ls_idDocumento;

											if(lb_proceso41 && (lso_solicitud2 != null))
												ls_idDocumento = lso_solicitud2.getIdDocumento();
											else
												ls_idDocumento = lso_solicitud1.getIdDocumento();

											if(StringUtils.isValidString(ls_idDocumento))
											{
												Documento ld_documento;
												ld_documento = new Documento();
												ld_documento.setIdDocumento(ls_idDocumento);

												ld_documento = ldd_DAO.findById(ld_documento);

												if(ld_documento != null)
												{
													String     ls_tipoDocumento;
													String     ls_numero;
													String     ls_oficinaOrigen;
													BigDecimal lbd_version;
													Date       ld_fecha;

													ls_tipoDocumento     = ld_documento.getIdTipoDocumento();
													ls_numero            = ld_documento.getNumero();
													ls_oficinaOrigen     = ld_documento.getIdOficinaOrigen();
													lbd_version          = ld_documento.getVersion();
													ld_fecha             = ld_documento.getFechaDocumento();

													ls_tag = "<TAG_BQN_DOC_NUMERO>";

													if(ls_plantilla.contains(ls_tag))
													{
														if(StringUtils.isValidString(ls_numero))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_numero);
													}

													ls_tag = "<TAG_BQN_DOC_FECHA>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_fecha;
														ls_fecha = StringUtils.getString(
															    ld_fecha, FormatoFechaCommon.DIA_MES_ANIO
															);

														if(StringUtils.isValidString(ls_fecha))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_fecha);
													}

													{
														OficinaOrigen loo_oficinaOrigen;
														loo_oficinaOrigen = new OficinaOrigen();

														loo_oficinaOrigen.setIdOficinaOrigen(ls_oficinaOrigen);
														loo_oficinaOrigen.setVersion(lbd_version);

														loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
																                          .findById(loo_oficinaOrigen);

														if(loo_oficinaOrigen != null)
														{
															ls_idPais             = loo_oficinaOrigen.getIdPais();
															ls_idDepartamento     = loo_oficinaOrigen.getIdDepartamento();
															ls_idMunicipio        = loo_oficinaOrigen.getIdMunicipio();

															if(
															    StringUtils.isValidString(ls_idPais)
																    && StringUtils.isValidString(ls_idDepartamento)
															)
															{
																Departamento ld_departamento;
																ld_departamento = new Departamento();

																ld_departamento.setIdPais(ls_idPais);
																ld_departamento.setIdDepartamento(ls_idDepartamento);

																ld_departamento = DaoCreator.getDepartamentoDAO(
																	    adm_manager
																	).findById(ld_departamento);

																if(ld_departamento != null)
																{
																	ls_tag = "<TAG_DEP_OFI_ORIGEN>";

																	if(ls_plantilla.contains(ls_tag))
																	{
																		String ls_nombreDep;
																		ls_nombreDep = ld_departamento.getNombre();

																		if(StringUtils.isValidString(ls_nombreDep))
																			ls_plantilla = ls_plantilla.replace(
																				    ls_tag, ls_nombreDep + ", "
																				);
																	}
																}
															}

															ls_tag = "<TAG_ID_OFI_ORIGEN>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_nombre;
																ls_nombre = loo_oficinaOrigen.getNombre();

																if(StringUtils.isValidString(ls_nombre))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombre
																		);
															}

															ls_tag = "<TAG_DIR_OFI_ORIGEN>";

															if(ls_plantilla.contains(ls_tag))
															{
																String ls_dir;
																ls_dir = loo_oficinaOrigen.getDireccion();

																if(StringUtils.isValidString(ls_dir))
																	ls_plantilla = saltoDeCarroDespues(
																		    ls_plantilla, ls_tag, ls_dir
																		);
															}
														}
													}

													if(StringUtils.isValidString(ls_tipoDocumento))
													{
														TipoDocumentoPublico ltdp_tipoDocPublico;
														ltdp_tipoDocPublico = new TipoDocumentoPublico();

														ltdp_tipoDocPublico.setIdTipoDocumento(ls_tipoDocumento);

														ltdp_tipoDocPublico = ltdpd_DAO.findById(ltdp_tipoDocPublico);

														if(ltdp_tipoDocPublico != null)
														{
															String ls_nombreTipoDoc;
															ls_nombreTipoDoc     = ltdp_tipoDocPublico.getNombre();

															ls_tag = "<TAG_BQN_DOC_ID_TIPO_DOC>";

															if(ls_plantilla.contains(ls_tag))
															{
																if(StringUtils.isValidString(ls_nombreTipoDoc))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_nombreTipoDoc
																		);
															}
														}
													}
												}
											}

											ls_tag = "<TAG_NOMBRE_INTERESADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_idPersona;
												ls_idPersona = lso_solicitud1.getIdPersona();

												if(
												    (lb_art19 || lb_proceso39 || lb_proceso43 || lb_proceso41)
													    && (lso_solicitud2 != null)
												)
													ls_idPersona = lso_solicitud2.getIdPersona();

												if(StringUtils.isValidString(ls_idPersona))
												{
													Persona lp_persona;
													lp_persona = new Persona();

													lp_persona.setIdPersona(ls_idPersona);

													lp_persona = DaoCreator.getPersonaDAO(adm_manager)
															                   .findById(lp_persona);

													if(lp_persona != null)
													{
														String ls_idTipoDocumento;

														ls_idTipoDocumento = lp_persona.getIdDocumentoTipo();

														if(
														    StringUtils.isValidString(ls_idTipoDocumento)
															    && !ls_idTipoDocumento.contentEquals(
															        IdentificadoresCommon.NIT
															    )
														)
														{
															String ls_primerNombre;
															String ls_segundoNombre;
															String ls_primeApellido;
															String ls_segundoApellido;

															ls_primerNombre        = lp_persona.getPrimerNombre();
															ls_segundoNombre       = lp_persona.getSegundoNombre();
															ls_primeApellido       = lp_persona.getPrimerApellido();
															ls_segundoApellido     = lp_persona.getSegundoApellido();

															lsb_nombre.append(
															    (StringUtils.isValidString(ls_primerNombre)
																    && !ls_primerNombre.equalsIgnoreCase("null"))
															    ? ls_primerNombre : ""
															);
															lsb_nombre.append(
															    (StringUtils.isValidString(ls_segundoNombre)
																    && !ls_segundoNombre.equalsIgnoreCase("null"))
															    ? (" " + ls_segundoNombre) : ""
															);
															lsb_nombre.append(
															    (StringUtils.isValidString(ls_primeApellido)
																    && !ls_primeApellido.equalsIgnoreCase("null"))
															    ? (" " + ls_primeApellido) : ""
															);
															lsb_nombre.append(
															    (StringUtils.isValidString(ls_segundoApellido)
																    && !ls_segundoApellido.equalsIgnoreCase("null"))
															    ? (" " + ls_segundoApellido) : ""
															);
														}
														else
															lsb_nombre.append(lp_persona.getRazonSocial());

														ls_plantilla = ls_plantilla.replace(
															    ls_tag, (lsb_nombre.toString()).trim()
															);
													}
												}
											}

											String ls_idCirculo;

											ls_tag           = "<TAG_MUN_OFI_ORIGEN>";
											ls_idCirculo     = lt_datosTurno.getIdCirculo();

											if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_idCirculo))
											{
												Municipio lm_municipio;

												lm_municipio     = new Municipio();

												lm_municipio = DaoCreator.getMunicipioDAO(adm_manager)
														                     .findByIdCirculo(ls_idCirculo);

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

											{
												ls_tag = "<TAG_CAUSAL_DEVOLUCION_PANTALLA>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_tmp;
													ls_tmp = asu_parametros.getConsideracion();

													if(StringUtils.isValidString(ls_tmp))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
												}
											}

											if(lb_esAnalistaCopias)
											{
												ls_tag = "<TAG_CAUSAL_NEGACION_COPIAS>";

												if(ls_plantilla.contains(ls_tag))
												{
													CausalNegacionCopias lcnc_causalNeg;
													lcnc_causalNeg = DaoCreator.getCausalNegacionCopiasDAO(adm_manager)
															                       .findById(
															    asu_parametros.getIdCausalNegacionCopias()
															);

													if(lcnc_causalNeg != null)
													{
														String ls_tmp;
														ls_tmp = lcnc_causalNeg.getDescripcionCausalNegacionCopias();

														if(StringUtils.isValidString(ls_tmp))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
													}
												}
											}

											if(
											    lb_art19 || lb_proceso39 || lb_proceso43 || lb_proceso41
												    || lb_esSolicitudDocumentos || lb_esProcedeNoCorreccion
												    || lb_esAnalistaCopias || lb_esNegarSolicitudCertificados
											)
											{
												String ls_medioNotificar;

												if(
												    lb_esSolicitudDocumentos || lb_esProcedeNoCorreccion
													    || lb_esAnalistaCopias || lb_esNegarSolicitudCertificados
												)
													ls_medioNotificar = (lso_solicitud1 != null)
														? lso_solicitud1.getIdAutorizacionNotificacion() : null;
												else
													ls_medioNotificar = (lso_solicitud2 != null)
														? lso_solicitud2.getIdAutorizacionNotificacion() : null;

												if(StringUtils.isValidString(ls_medioNotificar))
												{
													String ls_idPersona;

													if(
													    lb_esSolicitudDocumentos || lb_esProcedeNoCorreccion
														    || lb_esAnalistaCopias || lb_esNegarSolicitudCertificados
													)
														ls_idPersona = lso_solicitud1.getIdPersona();
													else
														ls_idPersona = (lso_solicitud2 != null)
															? lso_solicitud2.getIdPersona() : null;

													if(StringUtils.isValidString(ls_idPersona))
													{
														Persona lp_persona;
														lp_persona = new Persona();

														lp_persona.setIdPersona(ls_idPersona);

														lp_persona = DaoCreator.getPersonaDAO(adm_manager)
																                   .findById(lp_persona);

														if(lp_persona != null)
														{
															if(
															    ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_RESIDENCIA
																    )
																    || ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
																    )
															)
															{
																PersonaDireccion lpd_pd;

																lpd_pd = new PersonaDireccion();

																lpd_pd.setIdPersona(ls_idPersona);

																lpd_pd.setIdDireccion(
																    (lb_esProcedeNoCorreccion || lb_esAnalistaCopias
																	    || lb_esNegarSolicitudCertificados)
																    ? lso_solicitud1.getIdDireccion()
																    : ((lso_solicitud2 != null)
																    ? lso_solicitud2.getIdDireccion() : null)
																);

																lpd_pd = DaoCreator.getPersonaDireccionDAO(adm_manager)
																		               .findById(lpd_pd);

																if(lpd_pd != null)
																{
																	ls_tag = "<TAG_DIR_INTERESADO>";

																	if(ls_plantilla.contains(ls_tag))
																	{
																		ls_direccioncompleta = lpd_pd.getDireccion();

																		if(
																		    StringUtils.isValidString(
																			        ls_direccioncompleta
																			    )
																		)
																			ls_plantilla = saltoDeCarroDespues(
																				    ls_plantilla, ls_tag,
																				    ls_direccioncompleta
																				);
																	}

																	ls_tag = "<TAG_DEPAR_INTERESADO>";

																	if(
																	    ls_plantilla.contains(ls_tag)
																		    && ls_plantilla.contains(
																		        "TAG_MUNICIPIO_INTERESADO"
																		    )
																	)
																	{
																		Departamento ld_departamento;
																		Municipio    lm_municipio;

																		ld_departamento     = new Departamento();
																		lm_municipio        = new Municipio();

																		ld_departamento.setIdPais(lpd_pd.getIdPais());
																		ld_departamento.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);

																		ld_departamento = DaoCreator.getDepartamentoDAO(
																			    adm_manager
																			).findById(ld_departamento);

																		lm_municipio.setIdPais(lpd_pd.getIdPais());
																		lm_municipio.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		lm_municipio.setIdMunicipio(
																		    lpd_pd.getIdMunicipio()
																		);

																		lm_municipio = DaoCreator.getMunicipioDAO(
																			    adm_manager
																			).findById(lm_municipio);

																		if(
																		    (ld_departamento != null)
																			    && (lm_municipio != null)
																		)
																		{
																			String ls_nombre;
																			ld_departamento.setNombreDepartamento(
																			    ld_departamento.getNombre()
																			);
																			ls_nombre = ld_departamento
																					.getNombreDepartamento();

																			if(
																			    StringUtils.isValidString(ls_nombre)
																				    && StringUtils.isValidString(
																				        lm_municipio.getNombre()
																				    )
																			)
																				ls_plantilla = escribirDepartamentoMunicipioInteresado(
																					    ls_plantilla, "<TAG_MUNICIPIO>",
																					    lm_municipio.getNombre(), ls_tag,
																					    ls_nombre
																					);
																		}
																	}
																	else if(ls_plantilla.contains(ls_tag))
																	{
																		Departamento ld_departamento;

																		ld_departamento = new Departamento();

																		ld_departamento.setIdPais(lpd_pd.getIdPais());
																		ld_departamento.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		ld_departamento = DaoCreator.getDepartamentoDAO(
																			    adm_manager
																			).findById(ld_departamento);

																		if(ld_departamento != null)
																		{
																			String ls_nombre;

																			ls_nombre = ld_departamento.getNombre();

																			if(StringUtils.isValidString(ls_nombre))
																				ls_plantilla = saltoDeCarroDespues(
																					    ls_plantilla, ls_tag, ls_nombre
																					);
																		}
																	}

																	else if(
																	    ls_plantilla.contains(
																		        "<TAG_MUNICIPIO_INTERESADO>"
																		    )
																	)
																	{
																		Municipio lm_municipio;

																		lm_municipio = new Municipio();

																		lm_municipio.setIdPais(lpd_pd.getIdPais());
																		lm_municipio.setIdDepartamento(
																		    lpd_pd.getIdDepartamento()
																		);
																		lm_municipio.setIdMunicipio(
																		    lpd_pd.getIdMunicipio()
																		);

																		lm_municipio = DaoCreator.getMunicipioDAO(
																			    adm_manager
																			).findById(lm_municipio);

																		if(lm_municipio != null)
																		{
																			String ls_nombre;

																			ls_nombre = lm_municipio.getNombre();

																			if(StringUtils.isValidString(ls_nombre))
																				ls_plantilla = saltoDeCarroDespues(
																					    ls_plantilla,
																					    "<TAG_MUNICIPIO_INTERESADO>",
																					    ls_nombre
																					);
																		}
																	}
																}
															}
															else if(
															    ls_medioNotificar.equalsIgnoreCase(
																        MedioNotificarCommon.CORREO_ELECTRONICO
																    )
															)
															{
																PersonaCorreoElectronico lpce_pce;

																lpce_pce = new PersonaCorreoElectronico();

																lpce_pce.setIdPersona(ls_idPersona);

																if(
																    lb_esSolicitudDocumentos
																	    || lb_esProcedeNoCorreccion
																	    || lb_esAnalistaCopias
																	    || lb_esNegarSolicitudCertificados
																)
																	lpce_pce.setIdCorreoElectronico(
																	    lso_solicitud1.getIdCorreoElectronico()
																	);
																else
																	lpce_pce.setIdCorreoElectronico(
																	    lso_solicitud2.getIdCorreoElectronico()
																	);

																lpce_pce = DaoCreator.getPersonaCorreoElectronicoDAO(
																	    adm_manager
																	).findById(lpce_pce);

																if(lpce_pce != null)
																{
																	ls_tag = "<TAG_CORREO_ELECTRONICO>";

																	if(ls_plantilla.contains(ls_tag))
																	{
																		String ls_email;
																		ls_email         = lpce_pce.getCorreoElectronico();
																		ls_plantilla     = saltoDeCarroDespues(
																			    ls_plantilla, ls_tag, ls_email
																			);
																	}
																}
															}
														}
													}
												}
											}

											ls_tag = "<TAG_CALIDAD_SOLICITANTE>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_idCalidadSol;
												ls_idCalidadSol = lso_solicitud1.getIdCalidadSolicitante();

												if(StringUtils.isValidString(ls_idCalidadSol))
												{
													CalidadSolicitante lcs_calidadSol;
													lcs_calidadSol = new CalidadSolicitante();

													lcs_calidadSol.setIdCalidadSolicitante(ls_idCalidadSol);

													lcs_calidadSol = DaoCreator.getCalidadSolicitanteDAO(adm_manager)
															                       .findById(lcs_calidadSol);

													if(lcs_calidadSol != null)
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, lcs_calidadSol.getNombre()
															);
												}
											}
										}

										{
											ls_tag = "<TAG_CONTENTIVA_DE>";

											if(ls_plantilla.contains(ls_tag))
											{
												Acto la_acto;
												la_acto = new Acto();

												la_acto.setIdSolicitud(lso_solicitud1.getIdSolicitud());

												la_acto = lad_DAO.findById(la_acto);

												if(la_acto != null)
												{
													TipoActo lta_tipoActo;
													lta_tipoActo = new TipoActo();

													lta_tipoActo.setIdTipoActo(la_acto.getIdTipoActo());

													lta_tipoActo = DaoCreator.getTipoActoDAO(adm_manager)
															                     .findById(lta_tipoActo);

													if(lta_tipoActo != null)
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, lta_tipoActo.getNombre()
															);
												}
											}
										}

										{
											String ls_tagNupre;

											ls_tag          = "<TAG_ID_SOLICITUD_MATRICULA>";
											ls_tagNupre     = "<TAG_NUPRE>";

											if(ls_plantilla.contains(ls_tag))
											{
												Collection<SolicitudMatricula> lcsm_datos;
												String                         ls_solicitudMatricula;

												ls_solicitudMatricula     = "";
												lcsm_datos                = DaoCreator.getSolicitudMatriculaDAO(
													    adm_manager
													)
														                                  .findByIdSolicitudCirculo(
														    lso_solicitud1.getIdSolicitud(),
														    lt_datosTurno.getIdCirculo()
														);

												if(CollectionUtils.isValidCollection(lcsm_datos))
												{
													int           li_contador;
													StringBuilder lsb_sbNupre;

													li_contador     = 1;
													lsb_sbNupre     = new StringBuilder();

													for(SolicitudMatricula lsm_iterador : lcsm_datos)
													{
														if(lsm_iterador != null)
														{
															int    li_size;
															String ls_signo;

															li_size      = lcsm_datos.size();
															ls_signo     = "";

															if(li_size > 1)
																ls_signo = ", ";

															if(li_contador == li_size)
																ls_signo = IdentificadoresCommon.PUNTO;

															ls_solicitudMatricula = ls_solicitudMatricula
																+ lsm_iterador.getIdCirculo() + "-"
																+ lsm_iterador.getIdMatricula() + ls_signo;

															if(ls_plantilla.contains(ls_tagNupre))
															{
																PredioRegistro lpr_predioRegistro;
																lpr_predioRegistro = new PredioRegistro();

																lpr_predioRegistro.setIdCirculo(
																    lsm_iterador.getIdCirculo()
																);
																lpr_predioRegistro.setIdMatricula(
																    lsm_iterador.getIdMatricula()
																);

																lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(
																	    adm_manager
																	).findByCirculoMatricula(lpr_predioRegistro);

																if(lpr_predioRegistro != null)
																{
																	String ls_nupre;
																	ls_nupre = lpr_predioRegistro.getNupre();

																	if(ls_nupre != null)
																	{
																		lsb_sbNupre.append(ls_nupre);
																		lsb_sbNupre.append(ls_signo);
																	}
																}
															}

															li_contador++;
														}
													}

													ls_plantilla = ls_plantilla.replace(ls_tag, ls_solicitudMatricula);

													if(ls_plantilla.contains(ls_tagNupre))
														ls_plantilla = ls_plantilla.replace(ls_tagNupre, lsb_sbNupre);
												}
											}
										}

										{
											ls_tag = "<TAG_FECHA_LARGA>";

											if(ls_plantilla.contains(ls_tag))
											{
												Date   ld_fechaActual;
												String ls_fechaActual;

												ld_fechaActual     = new Date();
												ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fechaActual);

												if(StringUtils.isValidString(ls_fechaActual))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, ls_fechaActual.toUpperCase()
														);
											}
										}

										{
											ls_tag = "<TAG_NIR>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(lso_solicitud1 != null)
												{
													String ls_nir;
													ls_nir = lso_solicitud1.getNir();

													if(StringUtils.isValidString(ls_nir))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);
												}
											}
										}

										{
											ls_tag = "<TAG_NIR_VINCULADO>";

											if(ls_plantilla.contains(ls_tag))
											{
												SolicitudAsociada lsa_tmp;
												lsa_tmp = DaoCreator.getSolicitudAsociadaDAO(adm_manager)
														                .findByIdProceso(
														    lt_turno.getIdSolicitud(), true
														);

												if(lsa_tmp != null)
												{
													String ls_nir1;

													ls_nir1 = lsa_tmp.getNirSolicitud1();

													if(StringUtils.isValidString(ls_nir1))
														ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir1);
												}
											}
										}

										String ls_consecutivoOficio;
										String ls_consecutivoResolucion;
										String ls_referenciaSalida;
										Long   ll_consecutivoResolucion;
										Date   ld_dateResol;
										Date   ld_fechaOficio;

										ls_consecutivoOficio         = null;
										ls_consecutivoResolucion     = null;
										ls_referenciaSalida          = null;
										ld_dateResol                 = null;
										ll_consecutivoResolucion     = null;
										ld_fechaOficio               = null;

										if(ab_firmaMasiva)
										{
											{
												ls_tag = "<TAG_OFICIO>";

												if(ls_plantilla.contains(ls_tag))
												{
													NumeracionOficiosCirculo lnoc_numeracionOficios;
													lnoc_numeracionOficios = findNumeracionOficiosCirculo(
														    lth_turnoHistoria, adm_manager, as_userId, as_remoteIp
														);

													if(lnoc_numeracionOficios != null)
													{
														ls_consecutivoOficio     = lnoc_numeracionOficios
																.getConsecutivoGenerado();

														ls_plantilla     = ls_plantilla.replace(
															    ls_tag, ls_consecutivoOficio
															);

														ld_fechaOficio = new Date();
													}
												}
											}

											{
												ls_tag = "<TAG_REFERENCIA_SALIDA>";

												if(ls_plantilla.contains(ls_tag))
												{
													if(
													    !lb_proceso41 && !lb_esSolicitudDocumentos
														    && !lb_esNegarSolicitudCertificados
														    && !lb_esProcedeNoCorreccion && !lb_esAnalistaCopias
													)
														ls_referenciaSalida = generarIdCorrespondencia(
															    lth_turnoHistoria, adm_manager, true
															);
													else
														ls_referenciaSalida = generarIdCorrespondencia(
															    lth_turnoHistoria, adm_manager, false
															);

													if(StringUtils.isValidString(ls_referenciaSalida))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_referenciaSalida
															);
												}
											}

											{
												ls_tag = "<TAG_RESOLUCION>";

												if(ls_plantilla.contains(ls_tag))
												{
													DocumentosSalida ldsr_documentosSalidaResolucion;

													ldsr_documentosSalidaResolucion = DaoCreator.getDocumentosSalidaDAO(
														    adm_manager
														)
															                                        .findByIdTurnoTipo(
															    lth_turnoHistoria.getIdTurno(),
															    TipoArchivoCommon.RESOLUCION, true,
															    lth_turnoHistoria.getIdTurnoHistoria()
															);

													if(ldsr_documentosSalidaResolucion != null)
													{
														ls_consecutivoResolucion     = String.format(
															    "%06d",
															    ldsr_documentosSalidaResolucion.getConsecutivoResolucion()
															);
														ll_consecutivoResolucion     = ldsr_documentosSalidaResolucion
																.getConsecutivoResolucion();

														ls_plantilla = ls_plantilla.replace(
															    ls_tag, ls_consecutivoResolucion
															);
													}
												}
											}

											{
												ls_tag = "<TAG_FECHA_RESOL>";

												if(ls_plantilla.contains(ls_tag))
												{
													String           ls_fechaResol;
													SimpleDateFormat formatter;

													ld_dateResol     = new Date();
													formatter        = new SimpleDateFormat("dd/MM/yyyy");

													if(ld_dateResol != null)
													{
														ls_fechaResol     = StringUtils.getStringNotNull(
															    formatter.format(ld_dateResol)
															);

														ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaResol);
													}
												}
											}
										}

										{
											ls_tag = "<TAG_USUARIO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_usuario;
												ls_usuario = asu_parametros.getUsuario();

												if(StringUtils.isValidString(ls_usuario))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_usuario);
											}
										}

										{
											ls_tag = "<TAG_CONSIDERACION_PANTALLA>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_tmp;
												ls_tmp = asu_parametros.getConsideracion();

												if(StringUtils.isValidString(ls_tmp))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
											}
										}

										{
											ls_tag = "<TAG_COMPLETITUD_DOCUMENTAL>";

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

													ls_tagNew     = "{\\*\\bkmkstart COMPLETITUD_DOCUMENTAL}{\\*\\bkmkend COMPLETITUD_DOCUMENTAL} \\line "
														+ ls_tag;

													ls_plantilla = ls_textoMitadSuperior
														+ IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
														+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
												}
											}
										}

										{
											Constantes lc_usuarioFirma;
											byte[]     lba_resolucion;
											String     ls_tagUsuarioFirma;
											int        li_incrX = 0;
											int        li_incrY = 0;

											lc_usuarioFirma     = new Constantes();
											ls_tagUsuarioFirma  = "<TAG_USUARIO_FIRMA_SUSPENSION>";

											lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

											lc_usuarioFirma     = lcd_DAO.findByIdWithImage(lc_usuarioFirma);
											ls_plantilla        = getFirmaMasivaBusiness()
													                      .reemplazarUsuarioFirmaCargo(
													    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma,
													    "<TAG_CARGO_FIRMA_SUSPENSION>"
													);
											lmss_datos          = finalizarPlantilla(
												    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), adm_manager
												);
											ls_plantilla        = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
											lba_resolucion      = new PDFGenerator().convertirRTFToPDF(
												    ls_plantilla.getBytes(), adm_manager
												);

											if(lba_resolucion == null)
												throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

											if(lb_esSolicitudDocumentos)
											{
												ByteArrayInputStream                 lbais_docInStream;
												ByteArrayOutputStream                lbaos_docOutStream;
												com.aspose.words.Document            ld_doc;
												Collection<AccCompletitudDocumental> lcacd_completitudDocumentales;
												DocumentBuilder                      ldb_builder;
												Table                                lt_table;

												lbais_docInStream      = new ByteArrayInputStream(
													    ls_plantilla.getBytes()
													);
												lbaos_docOutStream     = new ByteArrayOutputStream();
												ld_doc                 = new com.aspose.words.Document(
													    lbais_docInStream
													);
												ldb_builder            = new DocumentBuilder(ld_doc);

												ldb_builder.moveToBookmark("COMPLETITUD_DOCUMENTAL");
												ldb_builder.writeln(ControlChar.LINE_BREAK);

												AccCompletitudDocumental lacd_completitudDoc;
												lacd_completitudDoc = new AccCompletitudDocumental();
												lacd_completitudDoc.setIdSolicitud(lso_solicitud1.getIdSolicitud());
												lacd_completitudDoc.setIdProceso(lso_solicitud1.getIdProceso());
												lacd_completitudDoc.setIdSubproceso(lso_solicitud1.getIdSubproceso());

												lcacd_completitudDocumentales = DaoCreator.getAccCompletitudDocumentalDAO(
													    adm_manager
													).findAllByIdSolicitudProceso(lacd_completitudDoc);

												if(CollectionUtils.isValidCollection(lcacd_completitudDocumentales))
												{
													int  li_tamanoLetra;
													long ll_porcentajetablas;

													ll_porcentajetablas     = (long)50.0;
													li_tamanoLetra          = 9;
													lt_table                = ldb_builder.startTable();

													ldb_builder.insertCell();
													ldb_builder.getParagraphFormat()
														           .setAlignment(ParagraphAlignment.CENTER);
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getFont().setUnderline(0);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);

													ldb_builder.getFont().setSize(li_tamanoLetra);
													ldb_builder.write("TIPO DOCUMENTAL");

													ldb_builder.insertCell();
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);
													ldb_builder.getFont().setSize(li_tamanoLetra);
													ldb_builder.write("OBSERVACIONES");

													ldb_builder.endRow();

													for(AccCompletitudDocumental lacd_completitudTMP : lcacd_completitudDocumentales)
													{
														if(lacd_completitudTMP != null)
														{
															ldb_builder.insertCell();
															ldb_builder.getParagraphFormat()
																           .setAlignment(ParagraphAlignment.LEFT);
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getFont().setBold(false);
															ldb_builder.write(
															    StringUtils.getStringNotNull(
															        lacd_completitudTMP.getNombreTipoDocumental()
															    )
															);

															ldb_builder.insertCell();
															ldb_builder.getCellFormat()
																           .setHorizontalMerge(CellMerge.NONE);
															ldb_builder.getCellFormat()
																           .setPreferredWidth(
																    PreferredWidth.fromPercent(ll_porcentajetablas)
																);
															ldb_builder.getFont().setSize(li_tamanoLetra);
															ldb_builder.getFont().setBold(false);
															ldb_builder.write(
															    StringUtils.getStringNotNull(
															        lacd_completitudTMP.getObservaciones()
															    )
															);

															ldb_builder.endRow();
														}
													}

													lt_table.setAlignment(TableAlignment.CENTER);
													ldb_builder.endTable();
												}

												ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

												byte[] docBytes = lbaos_docOutStream.toByteArray();

												lba_resolucion = new PDFGenerator().convertirRTFToPDF(
													    docBytes, adm_manager
													);

												if(lba_resolucion == null)
													throw new B2BException(
													    ErrorKeys.ERROR_GENERANDO_PDF_SOLICITUD_DOCUMENTACION
													);
											}

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

												lba_resolucion = getFirmaMasivaBusiness()
														                 .reemplazarBookmarsFirma(
														    lba_resolucion, lba_grafo, li_incrX, li_incrY
														);
											}

											asu_parametros.setComunicadoPDF(lba_resolucion);

											{
												ImagenesDAO         lid_DAO;
												DocumentosSalidaDAO ldsd_DAO;
												long                ll_idDocumentoSalida;
												long                ll_secuencia;

												boolean lb_existeImagen;

												Imagenes         li_imagenes;
												DocumentosSalida lds_documentosSalida;

												lid_DAO      = DaoCreator.getImagenesDAO(adm_manager);
												ldsd_DAO     = DaoCreator.getDocumentosSalidaDAO(adm_manager);

												li_imagenes              = new Imagenes();
												lds_documentosSalida     = new DocumentosSalida();

												li_imagenes.setImagenBlob(asu_parametros.getComunicadoPDF());
												ll_idDocumentoSalida     = asu_parametros.getIdComunicado();

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
												li_imagenes.setIdUsuarioCreacion(as_userId);
												li_imagenes.setIdUsuarioModificacion(as_userId);
												li_imagenes.setIpCreacion(as_remoteIp);
												li_imagenes.setIpModificacion(as_remoteIp);
												li_imagenes.setCodigoVerificacion(
												    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
												);

												ll_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen);

												if(lb_existeImagen)
												{
													if(lds_documentosSalida != null)
													{
														lds_documentosSalida.setRepositorio(
														    ab_firmaMasiva ? RepositorioCommon.FINAL
														                   : RepositorioCommon.TEMPORAL
														);
														lds_documentosSalida.setReferenciaSalida(ls_referenciaSalida);
														lds_documentosSalida.setConsecutivoOficio(ls_consecutivoOficio);
														lds_documentosSalida.setConsecutivoResolucion(
														    ll_consecutivoResolucion
														);
														lds_documentosSalida.setFechaOficio(
														    ab_firmaMasiva ? ld_fechaOficio : null
														);
														lds_documentosSalida.setFechaResolucion(ld_dateResol);
														lds_documentosSalida.setIdUsuarioModificacion(as_userId);
														lds_documentosSalida.setIpModificacion(as_remoteIp);

														ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
													}
												}
												else
												{
													if(ll_secuencia <= 0)
														throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

													lds_documentosSalida.setIdImagen(
													    NumericUtils.getLongWrapper(ll_secuencia)
													);

													if(lb_proceso41 || lb_esProcedeNoCorreccion)
														lds_documentosSalida.setTipo(
														    TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES
														);
													else if(lb_esSolicitudDocumentos)
														lds_documentosSalida.setTipo(
														    TipoArchivoCommon.SOLICITUD_DOCUMENTO_CORRECCIONES
														);
													else if(lb_esNegarSolicitudCertificados)
														lds_documentosSalida.setTipo(
														    TipoArchivoCommon.COMUNICADO_NEGACION_CERTIFICADOS
														);
													else
														lds_documentosSalida.setTipo(TipoArchivoCommon.COMUNICADO);

													lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
													lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
													lds_documentosSalida.setIdTurnoHistoria(
													    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
													);
													lds_documentosSalida.setReferenciaSalida(ls_referenciaSalida);
													lds_documentosSalida.setConsecutivoOficio(ls_consecutivoOficio);
													lds_documentosSalida.setConsecutivoResolucion(
													    ll_consecutivoResolucion
													);

													lds_documentosSalida.setFechaOficio(ld_fechaOficio);
													lds_documentosSalida.setFechaResolucion(ld_dateResol);
													lds_documentosSalida.setIdSolicitud(
													    lso_solicitud1.getIdSolicitud()
													);

													if(lb_esNegarSolicitudCertificados)
														lds_documentosSalida.setDestinatario(lsb_nombre.toString());
													else
														lds_documentosSalida.setDestinatario(ls_destinatario);

													lds_documentosSalida.setDireccion(ls_direccioncompleta);
													lds_documentosSalida.setIdPais(ls_idPais);
													lds_documentosSalida.setIdDepartamento(ls_idDepartamento);
													lds_documentosSalida.setIdMunicipio(ls_idMunicipio);
													lds_documentosSalida.setIdTipoDocumental(
													    TipoDocumentalCommon.COMUNICACION
													);
													lds_documentosSalida.setRepositorio(
													    ab_firmaMasiva ? RepositorioCommon.FINAL
													                   : RepositorioCommon.TEMPORAL
													);

													lds_documentosSalida.setIdUsuarioCreacion(as_userId);

													Collection<DocumentosSalida> lcds_documentosExistentes;

													lcds_documentosExistentes = ldsd_DAO.findByIdTurnoHistoriaTipo(
														    lds_documentosSalida
														);

													if(!CollectionUtils.isValidCollection(lcds_documentosExistentes))
													{
														long ll_idDocumento;

														ll_idDocumento = ldsd_DAO.insertOrUpdate(
															    lds_documentosSalida, true
															);

														if(ll_idDocumento > 0)
															asu_parametros.setIdComunicado(ll_idDocumento);
													}
													else
													{
														for(DocumentosSalida lds_docs : lcds_documentosExistentes)
														{
															boolean lb_tipoArchivo;
															lb_tipoArchivo = lds_docs.getTipo()
																	                     .equalsIgnoreCase(
																	    TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES
																	)
																	|| lds_docs.getTipo()
																	               .equalsIgnoreCase(
																	    TipoArchivoCommon.SOLICITUD_DOCUMENTO_CORRECCIONES
																	)
																	|| lds_docs.getTipo()
																	               .equalsIgnoreCase(
																	    TipoArchivoCommon.COMUNICADO_NEGACION_CERTIFICADOS
																	);

															if(
															    (lds_docs != null)
																    && (lds_docs.getTipo()
																                    .equalsIgnoreCase(
																        TipoArchivoCommon.COMUNICADO
																    )
																    || ((lb_proceso41 || lb_esProcedeNoCorreccion
																    || lb_esNegarSolicitudCertificados)
																    && lb_tipoArchivo))
															)
															{
																lds_documentosSalida.setIdDocumentoSalida(
																    lds_docs.getIdDocumentoSalida()
																);
																lds_documentosSalida.setIdUsuarioModificacion(
																    as_userId
																);
																lds_documentosSalida.setIdEcm(null);

																lds_documentosSalida.setReferenciaSalida(
																    ls_referenciaSalida
																);
																lds_documentosSalida.setConsecutivoOficio(
																    ls_consecutivoOficio
																);
																lds_documentosSalida.setConsecutivoResolucion(
																    ll_consecutivoResolucion
																);

																lds_documentosSalida.setFechaOficio(
																    ab_firmaMasiva ? ld_fechaOficio : null
																);

																long ll_idDocumento;

																ll_idDocumento = ldsd_DAO.insertOrUpdate(
																	    lds_documentosSalida, false
																	);

																if(ll_idDocumento > 0)
																	asu_parametros.setIdComunicado(ll_idDocumento);
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
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarComunicadoSuspensiones", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarComunicadoSuspensiones", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}

		return asu_parametros;
	}

/**
 * Método de transacciones con la base de datos con el fin de generar el documento de suspensiones.
 *
 * @param acs_parametros objeto de tipo suspensión que retorna el método
 * @param as_userId usuario en sesion
 * @param as_remoteIp Variable que contiene la ip desde la cual se realiza la acción.
 * @return el valor de suspension
 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
 */
	public synchronized Suspension generarDocumentosSuspensiones(
	    Collection<Suspension> acs_parametros, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Suspension ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = new Suspension();

		String ls_idTurnoHistoria;

		ls_idTurnoHistoria = null;

		try
		{
			if(CollectionUtils.isValidCollection(acs_parametros))
			{
				Collection<ZipEntryUtil> lczeu_zip;
				Iterator<Suspension>     lis_is;
				TurnoHistoriaDAO         lthd_DAO;

				lczeu_zip     = new ArrayList<ZipEntryUtil>();
				lis_is        = acs_parametros.iterator();
				lthd_DAO      = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				while(lis_is.hasNext())
				{
					Suspension ls_iterator;

					ls_iterator = lis_is.next();

					if(ls_iterator != null)
					{
						boolean lb_art18;
						boolean lb_art19;
						boolean lb_proceso39;
						boolean lb_proceso41;
						boolean lb_proceso43;
						boolean lb_proceso45;
						boolean lb_esSolicitudDocumentos;
						boolean lb_esSuspensionSolicitudDocumentos;
						boolean lb_esProcedeNoCorreccion;
						boolean lb_esNegarSolicitudCertificados;
						boolean lb_esAnalistaCopias;
						long    ll_motivoTramite;
						String  ls_tipo;
						String  ls_idTurno;

						lb_art18                               = false;
						lb_art19                               = false;
						lb_proceso39                           = false;
						lb_proceso41                           = false;
						lb_proceso43                           = false;
						lb_proceso45                           = ls_iterator.isEsProceso45();
						lb_esSolicitudDocumentos               = ls_iterator.isEsSolicitudDocumentos();
						lb_esSuspensionSolicitudDocumentos     = ls_iterator.isEsSuspensionSolicitudDocumentos();
						lb_esProcedeNoCorreccion               = ls_iterator.isEsProcedeNoCorreccion();
						lb_esAnalistaCopias                    = ls_iterator.isEsAnalistaCopias();
						lb_esNegarSolicitudCertificados        = ls_iterator.isEsNegarSolicitudCertificados();
						ll_motivoTramite                       = ls_iterator.getMotivoTramite();
						ls_tipo                                = ls_iterator.getTipo();
						ls_idTurno                             = null;

						if(
						    !lb_esSolicitudDocumentos && !lb_esProcedeNoCorreccion && !lb_esAnalistaCopias
							    && !lb_esNegarSolicitudCertificados
						)
						{
							if(!StringUtils.isValidString(ls_tipo))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO);

							lb_art18         = ls_tipo.equalsIgnoreCase(Suspension.ART_18);
							lb_art19         = ls_tipo.equalsIgnoreCase(Suspension.ART_19);
							lb_proceso39     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
							lb_proceso41     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
							lb_proceso43     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);
						}

						if(lb_proceso43 && (ll_motivoTramite <= NumericUtils.DEFAULT_LONG_VALUE))
							throw new B2BException(ErrorKeys.ERROR_DESICION_ETAPA);

						if(lb_art18)
						{
							{
								String ls_tmp;
								ls_tmp = ls_iterator.getArticulo();

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_ARTICULO_VALIDO);
							}

							{
								String ls_tmp;
								ls_tmp = ls_iterator.getPertinencia();

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_PERTINENCIA_VALIDO);
							}

							{
								String ls_tmp;
								ls_tmp = ls_iterator.getRazon1();

								if(!StringUtils.isValidString(ls_tmp))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_1);
							}
						}
						else if(
						    lb_art19 || lb_proceso39
							    || (lb_proceso43
							    && (ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO))
							    || lb_proceso41 || lb_esSolicitudDocumentos || lb_esNegarSolicitudCertificados
						)
							ValidarRecepcionDocumentos(ls_iterator, ldm_manager, as_userId, as_remoteIp);

						if(
						    lb_art19 || lb_proceso39 || lb_esSolicitudDocumentos || lb_esProcedeNoCorreccion
							    || lb_esAnalistaCopias || lb_esNegarSolicitudCertificados
						)
						{
							String ls_tmp;
							ls_tmp = ls_iterator.getConsideracion();

							if(!StringUtils.isValidString(ls_tmp))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_CONSIDERACIONES);

							if(lb_esAnalistaCopias)
							{
								String ls_tmp2;
								ls_tmp2 = ls_iterator.getIdCausalNegacionCopias();

								if(!StringUtils.isValidString(ls_tmp2))
									throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CAUSAL_NEGACION_COPIAS);
							}
						}
						else if(lb_proceso43)
						{
							if(ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO)
							{
								String ls_motivoDevolucion;

								ls_motivoDevolucion = ls_iterator.getConsideracion();

								if(!StringUtils.isValidString(ls_motivoDevolucion))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_MOTIVO_DEVOLUCION);
							}
							else if(ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO)
							{
								String ls_causalDevolucion;

								ls_causalDevolucion = ls_iterator.getConsideracion();

								if(!StringUtils.isValidString(ls_causalDevolucion))
									throw new B2BException(ErrorKeys.DEBE_DIGITAR_CAUSAL_DEVOLUCION);
							}
						}
						else if(lb_proceso41)
						{
							String ls_motivoDevolucion;

							ls_motivoDevolucion = ls_iterator.getConsideracion();

							if(!StringUtils.isValidString(ls_motivoDevolucion))
								throw new B2BException(ErrorKeys.DEBE_DIGITAR_MOTIVO_NEGACION);
						}

						ls_iterator.setUsuario(as_userId);

						{
							TurnoHistoria lth_turnoHistoria;

							lth_turnoHistoria = ls_iterator.getTurnoHistoria();

							if(lth_turnoHistoria != null)
							{
								String        ls_observaciones;
								TurnoHistoria lth_update;

								ls_observaciones     = lth_turnoHistoria.getObservaciones();
								lth_update           = lthd_DAO.findById(lth_turnoHistoria);

								if(lth_update != null)
								{
									ls_idTurnoHistoria = StringUtils.getString(lth_update.getIdTurnoHistoria());

									if(StringUtils.isValidString(ls_observaciones))
									{
										lth_update.setObservaciones(ls_observaciones);
										lth_update.setIdUsuarioModificacion(as_userId);
										lth_update.setIpModificacion(as_remoteIp);

										lthd_DAO.updateObservaciones(lth_update);
									}

									ls_idTurno = lth_update.getIdTurno();
								}
							}
						}

						TurnoHistoria lth_turnoHistoria;

						lth_turnoHistoria = ls_iterator.getTurnoHistoria();

						if(lth_turnoHistoria != null)
						{
							TurnoHistoria lth_tmp;
							lth_tmp = lthd_DAO.findById(lth_turnoHistoria);

							if(lth_tmp != null)
							{
								ls_idTurnoHistoria = StringUtils.getString(lth_tmp.getIdTurnoHistoria());

								Solicitud ls_solicitud;
								ls_solicitud = new Solicitud();
								ls_solicitud.setIdSolicitud(lth_tmp.getIdSolicitud());

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitud);

								if(ls_solicitud != null)
								{
									AccCompletitudDocumental lacd_completitud;
									lacd_completitud = new AccCompletitudDocumental();
									lacd_completitud.setIdSolicitud(lth_tmp.getIdSolicitud());

									DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
										          .deleteByIdSolicitud(lacd_completitud);

									if(lb_esSolicitudDocumentos || lb_esSuspensionSolicitudDocumentos || lb_proceso45)
									{
										Collection<TipoDocumental> lctd_tmp;
										lctd_tmp = ls_iterator.getTiposDocumentales();

										if(CollectionUtils.isValidCollection(lctd_tmp))
										{
											if(lb_proceso45)
											{
												TipoDocumentalDAO ltdd_DAO;
												StringBuilder     lsb_sb;
												Constantes        lc_constante;

												ltdd_DAO         = DaoCreator.getTipoDocumentalDAO(ldm_manager);
												lsb_sb           = new StringBuilder();
												lc_constante     = new Constantes();

												lc_constante.setIdConstante(
												    ConstanteCommon.TIPO_DOC_ART19_CALIFICACION
												);

												lc_constante = DaoCreator.getConstantesDAO(ldm_manager)
														                     .findById(lc_constante);

												if(lc_constante != null)
												{
													String ls_caracter;
													ls_caracter = lc_constante.getCaracter();

													if(StringUtils.isValidString(ls_caracter))
													{
														Map<String, String> lmss_coleccionTiposObligatorios;
														lmss_coleccionTiposObligatorios = ListadoCodigosConstantes
																.generarCodigos(ls_caracter);

														if(
														    CollectionUtils.isValidCollection(
															        lmss_coleccionTiposObligatorios
															    )
														)
														{
															for(Map.Entry<String, String> lmss_entry : lmss_coleccionTiposObligatorios
																    .entrySet())
															{
																if(!BooleanUtils.getBooleanValue(lmss_entry.getValue()))
																{
																	String ls_idTipoDocumental;

																	ls_idTipoDocumental = lmss_entry.getKey();

																	if(StringUtils.isValidString(ls_idTipoDocumental))
																	{
																		com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental ltd_td;
																		boolean                                              lb_faltaPorAgregar;

																		ltd_td                 = new com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental();
																		lb_faltaPorAgregar     = false;

																		ltd_td.setIdTipoDocumental(ls_idTipoDocumental);

																		ltd_td = ltdd_DAO.findById(ltd_td);

																		for(TipoDocumental ltd_iterador : lctd_tmp)
																		{
																			if(ltd_iterador != null)
																			{
																				String ls_tipoDocumental;

																				ls_tipoDocumental = ltd_iterador
																						.getIdTipoDocumental();

																				if(
																				    StringUtils.isValidString(
																					        ls_tipoDocumental
																					    )
																				)
																				{
																					if(
																					    ls_tipoDocumental
																						    .equalsIgnoreCase(ls_idTipoDocumental)
																					)
																						lb_faltaPorAgregar = true;
																				}
																			}
																		}

																		if((ltd_td != null) && !lb_faltaPorAgregar)
																		{
																			if(
																			    lsb_sb.length() > NumericUtils.DEFAULT_INT_VALUE
																			)
																				lsb_sb.append(
																				    IdentificadoresCommon.SIMBOLO_COMA
																				);

																			lsb_sb.append(ltd_td.getNombre());
																		}
																	}
																}
															}
														}
													}
												}

												String ls_s;

												ls_s = lsb_sb.toString();

												if(StringUtils.isValidString(ls_s))
												{
													Object[] loa_messageArgs = new String[1];

													loa_messageArgs[0] = ls_s;

													throw new B2BException(
													    ErrorKeys.ERROR_TIPOS_DOCUMENTALES_OBLIGATORIOS, loa_messageArgs
													);
												}
											}

											for(TipoDocumental lotd_td : lctd_tmp)
											{
												AccCompletitudDocumental lacd_completitudTMP;
												Turno                    lt_turno;

												lacd_completitudTMP     = new AccCompletitudDocumental();
												lt_turno                = ls_iterator.getTurno();

												lacd_completitudTMP.setIdSolicitud(ls_solicitud.getIdSolicitud());

												if(lt_turno != null)
													lacd_completitudTMP.setIdTurno(lt_turno.getIdTurno());

												lacd_completitudTMP.setIdProceso(ls_solicitud.getIdProceso());
												lacd_completitudTMP.setIdSubproceso(ls_solicitud.getIdSubproceso());
												lacd_completitudTMP.setIdTipoDocumental(lotd_td.getIdTipoDocumental());
												lacd_completitudTMP.setObservaciones(lotd_td.getObservaciones());
												lacd_completitudTMP.setEntregado(EstadoCommon.N);

												if(!lb_esSuspensionSolicitudDocumentos)
													lacd_completitudTMP.setObligatorioTipoDocumental(EstadoCommon.S);
												else
												{
													boolean lb_seleccionado;
													lb_seleccionado = lotd_td.isSeleccionado();

													if(lb_seleccionado)
														lacd_completitudTMP.setObligatorioTipoDocumental(
														    EstadoCommon.S
														);
													else
														lacd_completitudTMP.setObligatorioTipoDocumental(
														    EstadoCommon.N
														);
												}

												lacd_completitudTMP.setIdUsuarioCreacion(as_userId);
												lacd_completitudTMP.setIpCreacion(as_remoteIp);
												lacd_completitudTMP.setDigitalizado(EstadoCommon.N);

												DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
													          .insert(lacd_completitudTMP);
											}
										}
									}

									{
										String ls_exenta;

										ls_exenta = ls_solicitud.getEntidadExenta();

										if(
										    StringUtils.isValidString(ls_exenta)
											    && BooleanUtils.getBooleanValue(ls_exenta)
										)
											getRegistroBusiness()
												    .generarComunicadoRespuestaSolicitudExenta(
												    ldm_manager, ls_solicitud, lth_turnoHistoria, true, as_userId,
												    as_remoteIp
												);
									}
								}
							}
						}

						{
							Suspension ls_suspension;

							ls_suspension = null;

							if(
							    lb_art18 || lb_art19 || lb_proceso39
								    || (lb_proceso43
								    && (ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO))
							)
							{
								ls_suspension = generarActoAdministrativoSuspensiones(
									    ls_iterator, false, ldm_manager, as_userId, as_remoteIp
									);

								if(ls_suspension != null)
								{
									String ls_nombreArticulo;
									byte[] lba_pdf;

									ls_nombreArticulo     = null;
									lba_pdf               = null;

									lba_pdf = ls_suspension.getResolucionPDF();

									if(lb_art18)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_RESOL_18;
									else if(lb_art19)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_RESOL_19;
									else if(lb_proceso39)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_DESISTIMIENTO;
									else if(lb_proceso43)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_RESTITUCION;

									if(lba_pdf == null)
										throw new B2BException(ErrorKeys.NO_GENERO_PDF_RESOL);

									lczeu_zip.add(
									    new ZipEntryUtil(
									        StringUtils.getStringNotNull(ls_idTurno)
									        + IdentificadoresCommon.SIMBOLO_GUION_BAJO + ls_nombreArticulo
									        + ExtensionCommon.PDF_PUNTO, new ByteArrayInputStream(lba_pdf)
									    )
									);
								}
							}

							if(
							    lb_art18 || lb_art19 || lb_proceso39
								    || ((lb_proceso43
								    && (ll_motivoTramite == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO))
								    || lb_proceso41 || lb_esSolicitudDocumentos || lb_esProcedeNoCorreccion
								    || lb_esAnalistaCopias || lb_esNegarSolicitudCertificados)
							)
							{
								ls_suspension = generarComunicadoSuspensiones(
									    ls_iterator, false, ldm_manager, as_userId, as_remoteIp
									);

								if(ls_suspension != null)
								{
									String ls_nombreArticulo;
									byte[] lba_pdf;

									ls_nombreArticulo     = null;
									lba_pdf               = null;

									lba_pdf = ls_suspension.getComunicadoPDF();

									if(lb_art18)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_18;
									else if(lb_art19)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_19;
									else if(lb_proceso39)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_DESISTIMIENTO;
									else if(lb_proceso43)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_OFICIO_RECHAZO_RESTITUCION;
									else if(lb_proceso41)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_NEGACION_PRORROGA;
									else if(lb_esSolicitudDocumentos)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_SOLICITUD_DOCUMENTOS;
									else if(lb_esProcedeNoCorreccion)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_PROCEDENCIA_NO_CORRECCION;
									else if(lb_esAnalistaCopias)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_CAUSAL_NEGACION_COPIAS;
									else if(lb_esNegarSolicitudCertificados)
										ls_nombreArticulo = ConstanteCommon.NOMBRE_ARCHIVO_NEGAR_SOLICITUD_CERTIFICADOS;

									if(lba_pdf == null)
										throw new B2BException(ErrorKeys.NO_GENERO_PDF_COMUNICACION);

									lczeu_zip.add(
									    new ZipEntryUtil(
									        StringUtils.getStringNotNull(ls_idTurno)
									        + IdentificadoresCommon.SIMBOLO_GUION_BAJO + ls_nombreArticulo
									        + IdentificadoresCommon.PUNTO + ExtensionCommon.PDF_MAYUS,
									        new ByteArrayInputStream(lba_pdf)
									    )
									);
								}
							}
						}
					}
				}

				if(CollectionUtils.isValidCollection(lczeu_zip))
					ls_return.setZipPdf(ZipUtils.generateZip(lczeu_zip));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosSuspensiones", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentosSuspensiones", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

/**
 * Método de transacciones con la base de datos para terminar el proceso de suspensiones.
 *
 * @param acs_parametros objeto de tipo suspension para saber cual fue la supsension que se termino
 * @param as_usuario usuario en sesion
 * @param as_ipRemote correspondiente al valor de as ip remote
 * @return el valor de suspension
 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
 */
	public synchronized Suspension terminarProcesoSuspensiones(
	    Collection<Suspension> acs_parametros, String as_usuario, String as_ipRemote
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Suspension ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = new Suspension();

		try
		{
			if(CollectionUtils.isValidCollection(acs_parametros))
			{
				Iterator<Suspension> lis_is;
				TurnoHistoriaDAO     lthd_DAO;
				MotivoTramiteDAO     lmtd_DAO;
				OficiosTextoDAO      lotd_DAO;
				ProcedimientosDAO    lpd_DAO;

				lis_is       = acs_parametros.iterator();
				lmtd_DAO     = DaoCreator.getMotivoTramiteDAO(ldm_manager);
				lotd_DAO     = DaoCreator.getOficiosTextoDAO(ldm_manager);
				lpd_DAO      = DaoCreator.getProcedimientosDAO(ldm_manager);
				lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				while(lis_is.hasNext())
				{
					Suspension ls_parametros;

					ls_parametros = lis_is.next();

					if(ls_parametros != null)
					{
						TurnoHistoria lth_turnoHistoria;
						lth_turnoHistoria = ls_parametros.getTurnoHistoria();

						if(lth_turnoHistoria != null)
						{
							Long ll_turnoHistoria;
							ll_turnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

							if(NumericUtils.isValidLong(ll_turnoHistoria))
							{
								String        ls_nombrePlantilla;
								MotivoTramite lmt_motivo;
								long          ll_etapa;
								boolean       lb_actualizarAutoriza;

								lmt_motivo                = new MotivoTramite();
								ll_etapa                  = ls_parametros.getEtapa();
								ls_nombrePlantilla        = null;
								lb_actualizarAutoriza     = false;

								lmt_motivo.setIdEtapaOrigen(
								    (ll_etapa > 0) ? ll_etapa : EtapaCommon.ID_ETAPA_CALIFICACION
								);

								{
									String ls_proceso;

									ls_proceso = ls_parametros.getProceso();

									if(ls_parametros.isEsAnalistaCopias())
									{
										ls_nombrePlantilla = ConstanteCommon.PLANTILLA_EXPEDICION_COPIAS_NEGACION;
										lmt_motivo.setIdMotivo(MotivoTramiteCommon.ETAPA_350_NOTA_DE_NEGACION_COPIAS);
									}
									else if(StringUtils.isValidString(ls_proceso))
									{
										if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39))
										{
											boolean lb_etapa80O420;
											long    ll_motivo;

											lb_etapa80O420 = (ll_etapa == EtapaCommon.ID_ETAPA_CALIFICACION)
													|| (ll_etapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS);

											if(lb_etapa80O420)
											{
												String ls_respuestaDesistimiento;

												ls_respuestaDesistimiento = ls_parametros.getRespuestaDesistimiento();

												if(!StringUtils.isValidString(ls_respuestaDesistimiento))
													throw new B2BException(ErrorKeys.ERROR_SIN_RESPUESTA_DESISTIMIENTO);

												if(ls_respuestaDesistimiento.equals(EstadoCommon.S))
													ll_motivo = MotivoTramiteCommon.DESISTIMIENTO_PARA_TURNO_TRAMITE;
												else
													ll_motivo = MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO;
											}
											else
												ll_motivo = MotivoTramiteCommon.TRAMITE_DESISTIMIENTO_CON_TURNO_EN_FASE_ENTREGA_O_FINALIZADO;

											lmt_motivo.setIdMotivo(ll_motivo);
										}
										else if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43))
										{
											long ll_motivo;

											ll_motivo = ls_parametros.getMotivoTramite();

											lmt_motivo.setIdMotivo(
											    (ll_motivo > NumericUtils.DEFAULT_LONG_VALUE) ? ll_motivo
											                                                  : NumericUtils.DEFAULT_LONG_VALUE
											);
										}
										else if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41))
										{
											lmt_motivo.setIdMotivo(MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS);

											String ls_razonANegarProrroga;
											ls_razonANegarProrroga = ls_parametros.getRazonANegar();

											if(!ls_parametros.isEsPersonaEntidadJuridica())
											{
												if(
												    ls_razonANegarProrroga.equalsIgnoreCase(
													        IdentificadoresCommon.VENCIMIENTO_TERMINO
													    )
												)
													ls_nombrePlantilla = ConstanteCommon.PLANTILLA_NO_PROCEDE_CORRECION;
												else if(
												    ls_razonANegarProrroga.equalsIgnoreCase(
													        IdentificadoresCommon.VENCIMIENTO_PAGO
													    )
												)
													ls_nombrePlantilla = ConstanteCommon.PLANTILLA_NO_PROCEDE_CORRECION_MAYOR_VALOR;
											}
											else
												ls_nombrePlantilla = ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_NOPROCEDE;
										}
										else if(ls_proceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
											lmt_motivo.setIdMotivo(MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS);
										else
											lb_actualizarAutoriza = true;
									}
									else if(ls_parametros.isEsSolicitudDocumentos())
									{
										ls_nombrePlantilla = ConstanteCommon.PLANTILLA_OFICIO_SOLICITUD_DOCUMENTACION;
										lmt_motivo.setIdMotivo(
										    MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES
										);
									}
									else if(ls_parametros.isEsProcedeNoCorreccion())
									{
										ls_nombrePlantilla = ConstanteCommon.PLANTILLA_OFICIO_REMISORIO_ENTIDAD_CORRECCION_NOPROCEDE;
										lmt_motivo.setIdMotivo(MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION);
									}
									else if(ls_parametros.isEsNegarSolicitudCertificados())
									{
										ls_nombrePlantilla = ConstanteCommon.PLANTILLA_NEGACION_EXPEDICION_CERTIFICADOS;
										lmt_motivo.setIdMotivo(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS);
									}
									else
										lb_actualizarAutoriza = true;

									if(lb_actualizarAutoriza)
										lmt_motivo.setIdMotivo(MotivoTramiteCommon.SUSPENSION_DE_TERMINOS);
								}

								lmt_motivo = lmtd_DAO.findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									lth_turnoHistoria = lthd_DAO.findById(lth_turnoHistoria);

									if(lth_turnoHistoria != null)
									{
										{
											OficiosTexto lot_oficios;
											lot_oficios = new OficiosTexto();

											lot_oficios.setTipo(ls_parametros.getTipo());
											lot_oficios.setArticulo(ls_parametros.getArticulo());
											lot_oficios.setPertinencia(ls_parametros.getPertinencia());
											lot_oficios.setRazon1(
											    ls_parametros.isEsAnalistaCopias()
											    ? ls_parametros.getIdCausalNegacionCopias() : ls_parametros.getRazon1()
											);
											lot_oficios.setRazon2(ls_parametros.getRazon2());
											lot_oficios.setConsideracion(ls_parametros.getConsideracion());
											lot_oficios.setIdTurnoHistoria(ll_turnoHistoria);
											lot_oficios.setPlantilla(ls_nombrePlantilla);
											lot_oficios.setIpCreacion(as_ipRemote);
											lot_oficios.setIdUsuarioCreacion(as_usuario);

											lotd_DAO.insertOrUpdate(lot_oficios, true);
										}

										{
											String ls_estadoActividad;

											ls_estadoActividad = lth_turnoHistoria.getEstadoActividad();

											if(
											    StringUtils.isValidString(ls_estadoActividad)
												    && ls_estadoActividad.equalsIgnoreCase(EstadoCommon.TERMINADA)
											)
												throw new B2BException(ErrorKeys.NO_SE_PUEDE_TERMINAR_TURNO_HIST_ASG);
										}

										lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										lth_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										lth_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										lth_turnoHistoria.setIdUsuarioModificacion(as_usuario);
										lth_turnoHistoria.setIpModificacion(as_ipRemote);

										lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);

										{
											OficiosTexto lot_oficiosTexto;

											lot_oficiosTexto = DaoCreator.getOficiosTextoDAO(ldm_manager)
													                         .findByTurnoHistoria(
													    lth_turnoHistoria.getIdTurnoHistoria()
													);

											if(lot_oficiosTexto != null)
											{
												String ls_tipo;

												ls_tipo = lot_oficiosTexto.getTipo();

												if(
												    StringUtils.isValidString(ls_tipo)
													    && ls_tipo.equalsIgnoreCase(Suspension.ART_19)
												)
													lb_actualizarAutoriza = false;
											}
										}

										if(lb_actualizarAutoriza)
										{
											SolicitudDAO lsd_DAO;
											Solicitud    ls_solicitud;

											lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
											ls_solicitud     = lsd_DAO.findById(lth_turnoHistoria.getIdSolicitud());

											if(ls_solicitud != null)
											{
												ls_solicitud.setIdAutorizacionNotificacion(
												    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
												);

												lsd_DAO.insertOrUpdate(ls_solicitud, false);
											}
										}

										lpd_DAO.spCreateStage(lth_turnoHistoria);
									}
								}
							}
							else
								throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ls_return = null;
			clh_LOGGER.error("terminarProcesoSuspensiones", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoSuspensiones", le_e);

			throw new B2BException(le_e.getLocalizedMessage());
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
	}

	/**
	 * Método encarga de validar si el tramite actual tiene algún tipo de suspención
	 * @param as_circulo con el circulo del trámite
	 * @param as_matricula con la matricula del trámite
	 * @return de tipo boolean con la respuesta de la suspensión
	 * @throws B2BException
	 */
	public synchronized boolean validarTramiteSuspension(String as_circulo, String as_matricula)
	    throws B2BException
	{
		return validarTramiteSuspension(as_circulo, as_matricula, false, null);
	}

	/**
	 * Método encarga de validar si el tramite actual tiene algún tipo de suspención
	 * @param as_circulo con el circulo del trámite
	 * @param as_matricula con la matricula del trámite
	 * @return de tipo boolean con la respuesta de la suspensión
	 * @throws B2BException
	 */
	public synchronized boolean validarTramiteSuspension(
	    String as_circulo, String as_matricula, boolean lb_actoProhibicion, Collection<String> acc_alertas
	)
	    throws B2BException
	{
		boolean lb_suspensionEnTramite;

		lb_suspensionEnTramite = false;

		if(StringUtils.isValidString(as_circulo) && StringUtils.isValidString(as_matricula))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<BitacoraBloqueo> lcbb_bitacorasBloqueo;

				lcbb_bitacorasBloqueo = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
						                              .findAllTurnosBloqueoActivoDifDesbloqueo(
						    as_circulo, as_matricula
						);

				TurnoHistoriaDAO     lthd_DAO;
				TurnoDAO             ltd_DAO;
				SolicitudAsociadaDAO lsad_DAO;
				SolicitudDAO         lsd_DAO;

				lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
				lsad_DAO     = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);
				lsd_DAO      = DaoCreator.getSolicitudDAO(ldm_manager);

				if(CollectionUtils.isValidCollection(lcbb_bitacorasBloqueo))
				{
					{
						Iterator<BitacoraBloqueo> libb_iterator;

						libb_iterator = lcbb_bitacorasBloqueo.iterator();

						if(libb_iterator != null)
						{
							while(libb_iterator.hasNext() && !lb_suspensionEnTramite)
							{
								BitacoraBloqueo lbb_temp;

								lbb_temp = libb_iterator.next();

								if(lbb_temp != null)
								{
									String ls_turno;

									ls_turno                   = lbb_temp.getIdTurnoBloqueo();
									lb_suspensionEnTramite     = CollectionUtils.isValidCollection(
										    lthd_DAO.findAllInEtapa280Or281(ls_turno)
										);

									if(!lb_suspensionEnTramite)
									{
										if(lb_actoProhibicion)
											throw new B2BException(ErrorKeys.ERROR_NO_PRESENTA_FOLIOS_SUSPENSION);

										Turno lt_turno;

										lt_turno = ltd_DAO.findById(ls_turno);

										if(lt_turno != null)
										{
											String ls_idSolicitud;

											ls_idSolicitud = lt_turno.getIdSolicitud();

											if(StringUtils.isValidString(ls_idSolicitud))
											{
												Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;

												lcsa_solicitudesAsociadas = lsad_DAO.findAllByIdSolicitud(
													    new SolicitudAsociada(ls_idSolicitud, false), false
													);

												if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
												{
													Iterator<SolicitudAsociada> lisa_iterator;

													lisa_iterator = lcsa_solicitudesAsociadas.iterator();

													if(lisa_iterator != null)
													{
														while(lisa_iterator.hasNext() && !lb_suspensionEnTramite)
														{
															SolicitudAsociada lsa_temp;

															lsa_temp = lisa_iterator.next();

															if(lsa_temp != null)
															{
																Solicitud ls_solTemp;

																ls_solTemp     = lsd_DAO.findById(
																	    lsa_temp.getIdSolicitud1()
																	);

																lb_suspensionEnTramite = ((ls_solTemp != null)
																		&& ls_solTemp.getIdProceso()
																		                 .equalsIgnoreCase(
																		    ProcesoCommon.ID_PROCESO_45
																		));
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
				}
				else if(lb_actoProhibicion)
					throw new B2BException(ErrorKeys.ERROR_NO_PRESENTA_FOLIOS_SUSPENSION);

				Collection<AnotacionPredio> lcc_anotacionPredio;
				lcc_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                            .findAnotacionesPredio(
						    as_circulo, NumericUtils.getLongWrapper(as_matricula)
						);

				if(CollectionUtils.isValidCollection(lcc_anotacionPredio))
				{
					Collection<String> acc_alertas2;
					acc_alertas2 = new ArrayList<String>();

					for(AnotacionPredio liap_iterador : lcc_anotacionPredio)
					{
						if(liap_iterador != null)
						{
							String ls_naturalezaJuridica;
							String ls_estadoAnotacion;

							ls_naturalezaJuridica     = liap_iterador.getIdNaturalezaJuridica();
							ls_estadoAnotacion        = liap_iterador.getAnotacionCancelada();

							if(
							    StringUtils.isValidString(ls_naturalezaJuridica)
								    && StringUtils.isValidString(ls_estadoAnotacion)
								    && ls_naturalezaJuridica.equalsIgnoreCase("0463")
								    && ls_estadoAnotacion.equalsIgnoreCase(EstadoCommon.N)
							)
							{
								Solicitud ls_solicitudDocumento;
								ls_solicitudDocumento = lsd_DAO.findByIdDocumento(liap_iterador.getIdDocumento());

								if(ls_solicitudDocumento != null)
									acc_alertas2.add(
									    "La matrícula" + as_circulo + "-" + as_matricula
									    + ", presenta prohibición judicial radicada con el NIR "
									    + ls_solicitudDocumento.getNir()
									);
							}
						}
					}

					acc_alertas = acc_alertas2;
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarTramiteSuspension", lb2be_e);
				ldm_manager.setRollbackOnly();
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lb_suspensionEnTramite;
	}

	/**
	 * Método encarga de validar si el tramite actual tiene algún tipo de suspención
	 * @param as_circulo con el circulo del trámite
	 * @param as_matricula con la matricula del trámite
	 * @return de tipo boolean con la respuesta de la suspensión
	 * @throws B2BException
	 */
	public synchronized Collection<String> validarTramiteSuspension2(
	    String as_circulo, String as_matricula, boolean lb_actoProhibicion, Collection<String> acc_alertas
	)
	    throws B2BException
	{
		Collection<String> acc_alertas2;
		boolean            lb_suspensionEnTramite;

		lb_suspensionEnTramite     = false;
		acc_alertas2               = new ArrayList<String>();

		if(StringUtils.isValidString(as_circulo) && StringUtils.isValidString(as_matricula))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Collection<BitacoraBloqueo> lcbb_bitacorasBloqueo;

				lcbb_bitacorasBloqueo = DaoCreator.getBitacoraBloqueoDAO(ldm_manager)
						                              .findAllTurnosBloqueoActivoDifDesbloqueo(
						    as_circulo, as_matricula
						);

				TurnoHistoriaDAO     lthd_DAO;
				TurnoDAO             ltd_DAO;
				SolicitudAsociadaDAO lsad_DAO;
				SolicitudDAO         lsd_DAO;

				lthd_DAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
				lsad_DAO     = DaoCreator.getSolicitudAsociadaDAO(ldm_manager);
				lsd_DAO      = DaoCreator.getSolicitudDAO(ldm_manager);

				if(CollectionUtils.isValidCollection(lcbb_bitacorasBloqueo))
				{
					{
						Iterator<BitacoraBloqueo> libb_iterator;

						libb_iterator = lcbb_bitacorasBloqueo.iterator();

						if(libb_iterator != null)
						{
							while(libb_iterator.hasNext() && !lb_suspensionEnTramite)
							{
								BitacoraBloqueo lbb_temp;

								lbb_temp = libb_iterator.next();

								if(lbb_temp != null)
								{
									String ls_turno;

									ls_turno                   = lbb_temp.getIdTurnoBloqueo();
									lb_suspensionEnTramite     = CollectionUtils.isValidCollection(
										    lthd_DAO.findAllInEtapa280Or281(ls_turno)
										);

									if(!lb_suspensionEnTramite)
									{
										if(lb_actoProhibicion)
											throw new B2BException(ErrorKeys.ERROR_NO_PRESENTA_FOLIOS_SUSPENSION);

										Turno lt_turno;

										lt_turno = ltd_DAO.findById(ls_turno);

										if(lt_turno != null)
										{
											String ls_idSolicitud;

											ls_idSolicitud = lt_turno.getIdSolicitud();

											if(StringUtils.isValidString(ls_idSolicitud))
											{
												Collection<SolicitudAsociada> lcsa_solicitudesAsociadas;

												lcsa_solicitudesAsociadas = lsad_DAO.findAllByIdSolicitud(
													    new SolicitudAsociada(ls_idSolicitud, false), false
													);

												if(CollectionUtils.isValidCollection(lcsa_solicitudesAsociadas))
												{
													Iterator<SolicitudAsociada> lisa_iterator;

													lisa_iterator = lcsa_solicitudesAsociadas.iterator();

													if(lisa_iterator != null)
													{
														while(lisa_iterator.hasNext() && !lb_suspensionEnTramite)
														{
															SolicitudAsociada lsa_temp;

															lsa_temp = lisa_iterator.next();

															if(lsa_temp != null)
															{
																Solicitud ls_solTemp;

																ls_solTemp     = lsd_DAO.findById(
																	    lsa_temp.getIdSolicitud1()
																	);

																lb_suspensionEnTramite = ((ls_solTemp != null)
																		&& ls_solTemp.getIdProceso()
																		                 .equalsIgnoreCase(
																		    ProcesoCommon.ID_PROCESO_45
																		));
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
				}
				else if(lb_actoProhibicion)
					throw new B2BException(ErrorKeys.ERROR_NO_PRESENTA_FOLIOS_SUSPENSION);

				Collection<AnotacionPredio> lcc_anotacionPredio;
				lcc_anotacionPredio = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                            .findAnotacionesPredio(
						    as_circulo, NumericUtils.getLongWrapper(as_matricula)
						);

				if(CollectionUtils.isValidCollection(lcc_anotacionPredio))
				{
					for(AnotacionPredio liap_iterador : lcc_anotacionPredio)
					{
						if(liap_iterador != null)
						{
							String ls_naturalezaJuridica;
							String ls_estadoAnotacion;

							ls_naturalezaJuridica     = liap_iterador.getIdNaturalezaJuridica();
							ls_estadoAnotacion        = liap_iterador.getAnotacionCancelada();

							if(
							    StringUtils.isValidString(ls_naturalezaJuridica)
								    && StringUtils.isValidString(ls_estadoAnotacion)
								    && ls_naturalezaJuridica.equalsIgnoreCase("0463")
								    && ls_estadoAnotacion.equalsIgnoreCase(EstadoCommon.N)
							)
							{
								Solicitud ls_solicitudDocumento;
								ls_solicitudDocumento = lsd_DAO.findByIdDocumento(liap_iterador.getIdDocumento());

								if(ls_solicitudDocumento != null)
									acc_alertas2.add(
									    "La matrícula " + as_circulo + "-" + as_matricula
									    + ", presenta prohibición judicial radicada con el NIR "
									    + ls_solicitudDocumento.getNir()
									);
							}
						}
					}
				}
				if (lb_suspensionEnTramite)
					acc_alertas2.add("La matrícula No." + as_circulo + "-" + as_matricula + " , tiene una solicitud de suspensión en trámite de acuerdo a los Artículos 18 y 19, Ley 1579 de 2012.");
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarTramiteSuspension", lb2be_e);
				ldm_manager.setRollbackOnly();
				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return acc_alertas2;
	}

	/**
	 * Método de sobreescritura del metofo getFirmaMasivaBusiness
	 */
	protected FirmaMasivaBusiness getFirmaMasivaBusiness()
	{
		if(ifmb_firmaMasivaBusiness == null)
			ifmb_firmaMasivaBusiness = new FirmaMasivaBusiness();

		return ifmb_firmaMasivaBusiness;
	}

	/**
	 * Método de transacciones con la base de datos con el fin de validar el formulario de datos de interesado
	 * @param asu_parametros objeto de tipo suspensión
	 * @param adm_manager transaccion con la base de datos
	 * @param as_usuario usuario en sesion
	 * @param as_remoteIp Variable que contiene la ip desde la cual se realiza la acción.
	 * @return
	 * @throws B2BException
	 */
	private void ValidarRecepcionDocumentos(
	    Suspension asu_parametros, DAOManager adm_manager, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			Solicitud     lso_solicitud;
			SolicitudDAO  lsd_DAO;
			ConstantesDAO lc_DAO;

			lsd_DAO     = DaoCreator.getSolicitudDAO(adm_manager);
			lc_DAO      = DaoCreator.getConstantesDAO(adm_manager);

			lso_solicitud = lsd_DAO.findById(asu_parametros.getSolicitud2());

			if(lso_solicitud != null)
			{
				DatosDelInteresado lddi_ddi;
				String             ls_idPersonaOld;
				String             ls_idTelefonoOld;
				String             ls_idCorreoOld;
				String             ls_idDireccionOld;

				ls_idPersonaOld       = lso_solicitud.getIdPersona();
				ls_idTelefonoOld      = lso_solicitud.getIdTelefono();
				ls_idCorreoOld        = lso_solicitud.getIdCorreoElectronico();
				ls_idDireccionOld     = lso_solicitud.getIdDireccion();
				lddi_ddi              = asu_parametros.getDatosDelInteresado();

				if(lddi_ddi != null)
				{
					Persona lp_p;
					String  ls_idAutorizacionComunicacion;
					String  ls_idAutorizacionNotificacion;

					lp_p                              = lddi_ddi.getPersona();
					ls_idAutorizacionComunicacion     = asu_parametros.isEsNegarSolicitudCertificados()
						? StringUtils.getStringNotNull(lso_solicitud.getIdAutorizacionComunicacion())
						: lso_solicitud.getIdAutorizacionComunicacion();
					ls_idAutorizacionNotificacion     = lso_solicitud.getIdAutorizacionNotificacion();

					if(
					    !StringUtils.isValidString(ls_idAutorizacionComunicacion)
						    && !asu_parametros.isEsNegarSolicitudCertificados()
					)
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);

					if(!StringUtils.isValidString(ls_idAutorizacionNotificacion))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_NOTIFICAR);

					if(lp_p != null)
					{
						String                      ls_idPersona;
						PersonaDireccionDAO         lpdd_DAO;
						PersonaTelefonoDAO          lptd_DAO;
						PersonaCorreoElectronicoDAO lpced_DAO;

						lpced_DAO        = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
						lptd_DAO         = DaoCreator.getPersonaTelefonoDAO(adm_manager);
						lpdd_DAO         = DaoCreator.getPersonaDireccionDAO(adm_manager);
						ls_idPersona     = lp_p.getIdPersona();

						if(lddi_ddi.isEditarDatosBasicos())
						{
							{
								{
									String ls_tipoDocumento;

									ls_tipoDocumento = lp_p.getIdDocumentoTipo();

									if(
									    !StringUtils.isValidString(ls_tipoDocumento)
										    || ls_tipoDocumento.equalsIgnoreCase("-1")
									)
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

									String ls_tipoPersona;

									ls_tipoPersona = lp_p.getIdTipoPersona();

									if(StringUtils.isValidString(ls_tipoPersona))
									{
										if(ls_tipoPersona.equalsIgnoreCase(EstadoCommon.N))
										{
											if(
											    !ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT)
												    && !ls_tipoDocumento.equalsIgnoreCase("-1")
											)
											{
												{
													String ls_nacionalidad;
													ls_nacionalidad = lp_p.getIdPais();

													if(!StringUtils.isValidString(ls_nacionalidad))
														throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
												}

												{
													String ls_genero;
													ls_genero = lp_p.getIdNaturalGenero();

													if(!StringUtils.isValidString(ls_genero))
														throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
												}

												{
													String ls_primerNombre;
													ls_primerNombre = lp_p.getPrimerNombre();

													if(!StringUtils.isValidString(ls_primerNombre))
														throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
												}

												{
													String ls_primerApellido;
													ls_primerApellido = lp_p.getPrimerApellido();

													if(!StringUtils.isValidString(ls_primerApellido))
														throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
												}
											}
											else
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NATURAL);
										}

										if(
										    StringUtils.getStringNotNull(ls_tipoPersona).equalsIgnoreCase(
											        EstadoCommon.J
											    )
										)
										{
											if(ls_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
											{
												{
													String ls_nacionalidad;
													ls_nacionalidad = lp_p.getIdPais();

													if(!StringUtils.isValidString(ls_nacionalidad))
														throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
												}

												{
													String ls_razonSocial;
													ls_razonSocial = lp_p.getRazonSocial();

													if(!StringUtils.isValidString(ls_razonSocial))
														throw new B2BException(ErrorKeys.DEBE_DIGITAR_RAZON_SOCIAL);
												}
											}
											else
												throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_JURIDICO);
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);

									String ls_tmp;

									ls_tmp = lp_p.getNumeroDocumento();

									if(!StringUtils.isValidString(ls_tmp))
										throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
									else
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

											lb_valido = StringUtils.isValidCharacters(ls_tmp, ls_constantes);

											if(!lb_valido)
												throw new B2BException(ErrorKeys.DEBE_DIGITAR_DOC_SIN_CARACTERES);
										}
									}
								}

								{
									String ls_tmp;

									ls_tmp = lp_p.getIdPais();

									if(!StringUtils.isValidString(ls_tmp))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
								}

								{
									String ls_tmp;

									ls_tmp = lp_p.getIdNaturalGenero();

									if(!StringUtils.isValidString(ls_tmp))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
								}

								{
									String ls_interviene;
									ls_interviene = lso_solicitud.getParticipaInterviniente();

									if(!StringUtils.isValidString(ls_interviene))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_INTERVIENE);
								}

								{
									if(
									    !StringUtils.isValidString(ls_idAutorizacionComunicacion)
										    && !asu_parametros.isEsNegarSolicitudCertificados()
									)
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MEDIO_COMUNICAR);
								}
							}

							{
								String ls_idFlagPersona;

								ls_idFlagPersona = marcarFlagPersona(adm_manager, lp_p, as_usuario, as_remoteIp);

								if(StringUtils.isValidString(ls_idFlagPersona))
								{
									ls_idPersona = ls_idFlagPersona;

									{
										PersonaDireccion lpd_direccion;

										lpd_direccion = new PersonaDireccion();

										lpd_direccion.setIdPersona(ls_idPersonaOld);
										lpd_direccion.setIdDireccion(ls_idDireccionOld);

										lpd_direccion = lpdd_DAO.findById(lpd_direccion);

										if(lpd_direccion != null)
										{
											long ll_maxDir;

											{
												String ls_idTipoPredio;

												ls_idTipoPredio = lpd_direccion.getIdTipoPredio();

												if(!StringUtils.isValidString(ls_idTipoPredio))
													throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_PREDIO);
											}

											lpd_direccion.setIdPersona(ls_idFlagPersona);
											lpd_direccion.setFechaDesde(new Date());
											lpd_direccion.setIdUsuarioCreacion(as_usuario);
											lpd_direccion.setIpCreacion(as_remoteIp);

											ll_maxDir = lpdd_DAO.insertOrUpdate(lpd_direccion, true);

											if(ll_maxDir > 0)
												lso_solicitud.setIdDireccion("" + ll_maxDir);
										}
									}

									{
										PersonaTelefono lpt_telefono;

										lpt_telefono = new PersonaTelefono();

										lpt_telefono.setIdPersona(ls_idPersonaOld);
										lpt_telefono.setIdTelefono(ls_idTelefonoOld);

										lpt_telefono = lptd_DAO.findById(lpt_telefono);

										if(lpt_telefono != null)
										{
											long ll_maxTel;

											lpt_telefono.setIdPersona(ls_idFlagPersona);
											lpt_telefono.setIdUsuarioCreacion(as_usuario);
											lpt_telefono.setIpCreacion(as_remoteIp);
											lpt_telefono.setFechaDesde(new Date());

											ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telefono, true);

											if(ll_maxTel > 0)
												lso_solicitud.setIdTelefono("" + ll_maxTel);
										}
									}

									{
										PersonaCorreoElectronico lpc_correo;

										lpc_correo = new PersonaCorreoElectronico();

										lpc_correo.setIdPersona(ls_idPersonaOld);
										lpc_correo.setIdCorreoElectronico(ls_idCorreoOld);

										lpc_correo = lpced_DAO.findById(lpc_correo);

										if(lpc_correo != null)
										{
											long ll_maxMail;

											lpc_correo.setIdPersona(ls_idFlagPersona);
											lpc_correo.setIdUsuarioCreacion(as_usuario);
											lpc_correo.setIpCreacion(as_remoteIp);
											lpc_correo.setFechaDesde(new Date());

											ll_maxMail = lpced_DAO.insertOrUpdate(lpc_correo, true);

											if(ll_maxMail > 0)
												lso_solicitud.setIdCorreoElectronico("" + ll_maxMail);
										}
									}

									String ls_calidadSolicitante;
									ls_calidadSolicitante = asu_parametros.getSolicitud2().getIdCalidadSolicitante();

									if(!StringUtils.isValidString(ls_calidadSolicitante))
										throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CALIDAD_SOL);

									lso_solicitud.setIdPersona(ls_idFlagPersona);
									lso_solicitud.setIdCalidadSolicitante(ls_calidadSolicitante);
									lso_solicitud.setIdUsuarioModificacion(as_usuario);
									lso_solicitud.setIpModificacion(as_remoteIp);

									lsd_DAO.updateSolicitud(lso_solicitud, 1);
								}
							}
						}

						if(lddi_ddi.isEditarDireccionResidencia())
						{
							PersonaDireccion lpd_residencia;

							lpd_residencia = lddi_ddi.getDireccionResidencia();

							if(lpd_residencia != null)
							{
								String ls_direccionCompleta;

								ls_direccionCompleta = lpd_residencia.getDireccion();

								if(StringUtils.isValidString(ls_direccionCompleta))
								{
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
										String ls_pais;
										ls_pais = lpd_residencia.getIdPais();

										if(!StringUtils.isValidString(ls_pais))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);

										if(
										    !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
											    && !StringUtils.isValidString(lpd_residencia.getComplementoDireccion())
										)
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_COMPLEMENTO_RESIDENCIA);
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

								if(
								    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
									    || ls_idAutorizacionComunicacion.equals(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
								{
									long ll_maxDir;

									lpd_residencia.setIdPersona(ls_idPersona);
									lpd_residencia.setFechaDesde(new Date());
									lpd_residencia.setIdUsuarioCreacion(as_usuario);
									lpd_residencia.setIpCreacion(as_remoteIp);

									ll_maxDir = lpdd_DAO.insertOrUpdate(lpd_residencia, true);

									if(ll_maxDir > 0)
									{
										lso_solicitud.setIdDireccion("" + ll_maxDir);
										lso_solicitud.setIdUsuarioModificacion(as_usuario);
										lso_solicitud.setIpModificacion(as_remoteIp);

										lsd_DAO.updateSolicitud(lso_solicitud, 2);
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
								}
							}
						}

						if(lddi_ddi.isEditarDireccionCorrespondencia())
						{
							PersonaDireccion lpd_correspondencia;

							lpd_correspondencia = lddi_ddi.getDireccionCorrespondencia();

							if(lpd_correspondencia != null)
							{
								String ls_direccionCompleta;

								ls_direccionCompleta = lpd_correspondencia.getDireccion();

								if(StringUtils.isValidString(ls_direccionCompleta))
								{
									{
										String ls_tipoDireccion;
										ls_tipoDireccion = lpd_correspondencia.getTipoDireccion();

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
										String ls_pais;
										ls_pais = lpd_correspondencia.getIdPais();

										if(!StringUtils.isValidString(ls_pais))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_CORRESPONDENCIA);

										if(
										    !ls_pais.equalsIgnoreCase(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT)
											    && !StringUtils.isValidString(
											        lpd_correspondencia.getComplementoDireccion()
											    )
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

								if(
								    ls_idAutorizacionComunicacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
									    || ls_idAutorizacionNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
								{
									long ll_maxDir;

									lpd_correspondencia.setIdPersona(ls_idPersona);
									lpd_correspondencia.setFechaDesde(new Date());
									lpd_correspondencia.setIdUsuarioCreacion(as_usuario);
									lpd_correspondencia.setIpCreacion(as_remoteIp);

									ll_maxDir = lpdd_DAO.insertOrUpdate(lpd_correspondencia, true);

									if(ll_maxDir > 0)
									{
										lso_solicitud.setIdDireccion("" + ll_maxDir);
										lso_solicitud.setIdUsuarioModificacion(as_usuario);
										lso_solicitud.setIpModificacion(as_remoteIp);

										lsd_DAO.updateSolicitud(lso_solicitud, 2);
									}
									else
										throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_DIRECCION);
								}
							}
						}

						if(lddi_ddi.isEditarDatosContacto())
						{
							boolean lb_notifOrip;

							lb_notifOrip = ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.ORIP)
									|| ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.ORIP);

							PersonaTelefono lpt_telefonoFijo;
							lpt_telefonoFijo = lddi_ddi.getTelefonoFijo();

							PersonaTelefono lpt_telefonoMovil;
							lpt_telefonoMovil = lddi_ddi.getTelefonoMovil();

							PersonaCorreoElectronico lpce_correo;
							lpce_correo = lddi_ddi.getCorreoElectronico();

							if(lb_notifOrip)
							{
								if(lpt_telefonoFijo != null)
								{
									String ls_numFijo;

									ls_numFijo = lpt_telefonoFijo.getTelefono();

									if(StringUtils.isValidString(ls_numFijo))
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
											ll_telefono = NumericUtils.getLongWrapper(ls_numFijo);

											if(!StringUtils.isValidString(ls_numFijo))
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
												    && (ls_numFijo.length() != li_digitos)
											)
												throw new B2BException(ErrorKeys.ERROR_TELEFONO_FIJO_MALFORMADO);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);

									if(StringUtils.isValidString(lpt_telefonoFijo.getTelefono()))
									{
										long ll_maxTel;

										lpt_telefonoFijo.setIdUsuarioCreacion(as_usuario);
										lpt_telefonoFijo.setIpCreacion(as_remoteIp);
										lpt_telefonoFijo.setIdPersona(ls_idPersona);
										lpt_telefonoFijo.setTipoTelefono(EstadoCommon.F);
										lpt_telefonoFijo.setFechaDesde(new Date());

										ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telefonoFijo, true);

										if(ll_maxTel > 0)
											lpt_telefonoFijo.setIdTelefono("" + ll_maxTel);
										else
											throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
									}
								}

								if(lpt_telefonoMovil != null)
								{
									String ls_numMovil;

									ls_numMovil = lpt_telefonoMovil.getTelefono();

									if(StringUtils.isValidString(ls_numMovil))
									{
										String ls_idPais;
										ls_idPais = lpt_telefonoMovil.getIdPais();

										if(!StringUtils.isValidString(ls_idPais))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);

										{
											Long ll_telefono;
											ll_telefono = NumericUtils.getLongWrapper(ls_numMovil);

											if(!StringUtils.isValidString(ls_numMovil))
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
												    && (ls_numMovil.length() != li_digitos)
											)
												throw new B2BException(ErrorKeys.ERROR_TELEFONO_MOVIL_MALFORMADO);
										}

										if(
										    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.ORIP)
											    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.ORIP)
										)
										{
											long ll_maxTel;

											lpt_telefonoFijo.setIdUsuarioCreacion(as_usuario);
											lpt_telefonoFijo.setIpCreacion(as_remoteIp);
											lpt_telefonoFijo.setIdPersona(ls_idPersona);
											lpt_telefonoFijo.setTipoTelefono(EstadoCommon.M);
											lpt_telefonoFijo.setFechaDesde(new Date());

											ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telefonoFijo, true);

											if(ll_maxTel > 0)
											{
												lso_solicitud.setIdTelefono("" + ll_maxTel);
												lso_solicitud.setIdUsuarioModificacion(as_usuario);
												lso_solicitud.setIpModificacion(as_remoteIp);

												lsd_DAO.updateSolicitud(lso_solicitud, 3);
											}
											else
												throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);
								}

								if((lpce_correo != null))
								{
									String ls_correoElec;

									ls_correoElec = lpce_correo.getCorreoElectronico();

									if(StringUtils.isValidString(ls_correoElec))
									{
										if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correoElec))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

										{
											if(
											    ls_idAutorizacionNotificacion.equalsIgnoreCase(
												        MedioNotificarCommon.CORREO_ELECTRONICO
												    )
												    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
												        MedioNotificarCommon.CORREO_ELECTRONICO
												    )
											)
											{
												long ll_maxCorreo;

												lpce_correo.setIdPersona(ls_idPersona);
												lpce_correo.setFechaDesde(new Date());
												lpce_correo.setIdUsuarioCreacion(as_usuario);
												lpce_correo.setIpCreacion(as_remoteIp);

												ll_maxCorreo = lpced_DAO.insertOrUpdate(lpce_correo, true);

												if(ll_maxCorreo > 0)
												{
													lso_solicitud.setIdCorreoElectronico("" + ll_maxCorreo);
													lso_solicitud.setIdUsuarioModificacion(as_usuario);
													lso_solicitud.setIpModificacion(as_remoteIp);

													lsd_DAO.updateSolicitud(lso_solicitud, 4);
												}
												else
													throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_CORREO);
											}
										}
									}
									else
										throw new B2BException(ErrorKeys.ERROR_DATOS_CONTACTO);
								}
							}
							else
							{
								if(lpt_telefonoFijo != null)
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
											String ls_numero;
											Long   ll_telefono;

											ls_numero       = lpt_telefonoFijo.getTelefono();
											ll_telefono     = NumericUtils.getLongWrapper(ls_numero);

											if(
											    StringUtils.isValidString(ls_numero)
												    && !NumericUtils.isValidLong(ll_telefono, 1)
											)
												throw new B2BException(ErrorKeys.TELEFONO_FIJO_INVALIDO);

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
												    && (ls_numero.length() != li_digitos)
											)
												throw new B2BException(ErrorKeys.ERROR_TELEFONO_FIJO_MALFORMADO);
										}

										if(StringUtils.isValidString(lpt_telefonoFijo.getTelefono()))
										{
											long ll_maxTel;

											lpt_telefonoFijo.setIdUsuarioCreacion(as_usuario);
											lpt_telefonoFijo.setIpCreacion(as_remoteIp);
											lpt_telefonoFijo.setIdPersona(ls_idPersona);
											lpt_telefonoFijo.setTipoTelefono(EstadoCommon.F);
											lpt_telefonoFijo.setFechaDesde(new Date());

											ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telefonoFijo, true);

											if(ll_maxTel > 0)
												lpt_telefonoFijo.setIdTelefono("" + ll_maxTel);
											else
												throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
										}
									}
								}

								if(lpt_telefonoMovil != null)
								{
									String ls_telefono;
									ls_telefono = lpt_telefonoMovil.getTelefono();

									if(StringUtils.isValidString(ls_telefono))
									{
										String ls_idPais;
										ls_idPais = lpt_telefonoMovil.getIdPais();

										if(!StringUtils.isValidString(ls_idPais))
											throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);

										{
											String ls_numero;
											Long   ll_telefono;

											ls_numero       = lpt_telefonoMovil.getTelefono();
											ll_telefono     = NumericUtils.getLongWrapper(ls_numero);

											if(
											    StringUtils.isValidString(ls_numero)
												    && !NumericUtils.isValidLong(ll_telefono, 1)
											)
												throw new B2BException(ErrorKeys.TELEFONO_FIJO_MOVIL);

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
												    && (ls_numero.length() != li_digitos)
											)
												throw new B2BException(ErrorKeys.ERROR_TELEFONO_MOVIL_MALFORMADO);
										}

										if(
										    ls_idAutorizacionNotificacion.equals(MedioNotificarCommon.SMS)
											    || ls_idAutorizacionComunicacion.equals(MedioNotificarCommon.SMS)
										)
										{
											long ll_maxTel;

											lpt_telefonoMovil.setIdUsuarioCreacion(as_usuario);
											lpt_telefonoMovil.setIpCreacion(as_remoteIp);
											lpt_telefonoMovil.setIdPersona(ls_idPersona);
											lpt_telefonoMovil.setTipoTelefono(EstadoCommon.F);
											lpt_telefonoMovil.setFechaDesde(new Date());

											ll_maxTel = lptd_DAO.insertOrUpdate(lpt_telefonoMovil, true);

											if(ll_maxTel > 0)
											{
												lso_solicitud.setIdTelefono("" + ll_maxTel);
												lso_solicitud.setIdUsuarioModificacion(as_usuario);
												lso_solicitud.setIpModificacion(as_remoteIp);

												lsd_DAO.updateSolicitud(lso_solicitud, 3);
											}
											else
												throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_TEL);
										}
									}
								}

								if(lpce_correo != null)
								{
									String ls_correo;
									ls_correo = lpce_correo.getCorreoElectronico();

									if(StringUtils.isValidString(ls_correo))
									{
										if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo))
											throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

										{
											if(
											    ls_idAutorizacionNotificacion.equalsIgnoreCase(
												        MedioNotificarCommon.CORREO_ELECTRONICO
												    )
												    || ls_idAutorizacionComunicacion.equalsIgnoreCase(
												        MedioNotificarCommon.CORREO_ELECTRONICO
												    )
											)
											{
												long ll_maxCorreo;

												lpce_correo.setIdPersona(ls_idPersona);
												lpce_correo.setFechaDesde(new Date());
												lpce_correo.setIdUsuarioCreacion(as_usuario);
												lpce_correo.setIpCreacion(as_remoteIp);

												ll_maxCorreo = lpced_DAO.insertOrUpdate(lpce_correo, true);

												if(ll_maxCorreo > 0)
												{
													lso_solicitud.setIdCorreoElectronico("" + ll_maxCorreo);
													lso_solicitud.setIdUsuarioModificacion(as_usuario);
													lso_solicitud.setIpModificacion(as_remoteIp);

													lsd_DAO.updateSolicitud(lso_solicitud, 4);
												}
												else
													throw new B2BException(ErrorKeys.NO_SE_GENERO_ID_CORREO);
											}
										}
									}
									else
										throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("ValidarRecepcionDocumentos", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método de transacción con la base de datos para actualizar las observaciones de un turno historia
	 * @param ath_param con el turno historia a actualizar
	 * @param as_usuario con el usuario de la transaccion
	 * @param as_remoteIp con la ip de la transacción
	 * @param adm_manager con el manager de la transacción con la BD
	 * @throws B2BException
	 */
	private void actualizarObservacionesTurnoHistoria(
	    TurnoHistoria ath_param, String as_usuario, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if((ath_param != null) && NumericUtils.isValidLong(ath_param.getIdTurnoHistoria()))
			{
				ath_param.setIdUsuarioModificacion(as_usuario);
				ath_param.setIpModificacion(as_remoteIp);

				DaoCreator.getTurnoHistoriaDAO(adm_manager).updateObservaciones(ath_param);
			}
		}
		catch(B2BException lb2be_e)
		{
			throw lb2be_e;
		}
	}
}
