package com.bachue.snr.prosnr01.business.visitas;

import com.aspose.words.CellMerge;
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

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.ByteArrayUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.integracion.EnvioGestorDocumentalBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.BookMarkCommon;
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
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;
import com.bachue.snr.prosnr01.common.constants.TramiteCommon;
import com.bachue.snr.prosnr01.common.utils.ConversionTextos;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.DependenciaSNRDAO;
import com.bachue.snr.prosnr01.dao.pgn.TagPlantillaResolucionDAO;
import com.bachue.snr.prosnr01.dao.pgn.TramiteVisitaTipoDAO;

import com.bachue.snr.prosnr01.model.business.DetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.model.business.EjecucionVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.DatosPlantillaDocumento;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaRol;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades VisitasBusiness.
 *
 * @author Jorge Esteban Patiño Fonseca Fecha de Creacion: ago 11, 2020
 */
public class VisitasBusiness extends EnvioGestorDocumentalBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(VisitasBusiness.class);

	/**
	 * Accion enviar delegado registro.
	 *
	 * @param as_idSolicitud  de as id solicitud
	 * @param as_idSubproceso de as id subproceso
	 * @param as_userId       de as user id
	 * @param as_remoteIp     de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se
	 *                      encuentra algun error controlado.
	 */
	public void accionEnviarDelegadoRegistro(
	    String as_idSolicitud, String as_idSubproceso, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitud))
			{
				if(StringUtils.isValidString(as_idSubproceso))
				{
					{
						TurnoHistoria lth_historia;

						lth_historia = new TurnoHistoria();

						lth_historia.setIdSolicitud(as_idSolicitud);
						lth_historia.setIdUsuarioModificacion(as_userId);
						lth_historia.setIpModificacion(as_remoteIp);

						lth_historia = DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(lth_historia);

						if(lth_historia != null)
						{
							Integer ls_codigoRespuesta;

							ls_codigoRespuesta = lth_historia.getTurnoHist();

							if(NumericUtils.isValidInteger(ls_codigoRespuesta) && (ls_codigoRespuesta.intValue() != 1))
								throw new B2BException(lth_historia.getCalificacion());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("accionEnviarDelegadoRegistro", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Aprobacion ejecucion visitas.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void aprobacionEjecucionVisitas(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_APROBACION_EJECUCION_DELEGADO_DE_REGISTRO_WS_INVOKE,
				    ConstanteCommon.JOB_APROBACION_EJECUCION_DELEGADO_DE_REGISTRO_LIMITE_INTENTOS,
				    EtapaCommon.APROBACION_EJECUCION_DELEGADO_DE_REGISTRO, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			aprobacionEjecucionVisitas(lcth_data, as_remoteIp);
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de SolicitudVisitas con
	 * todos los registros asociados a un id solicitud .
	 * Retorna el valor del objeto de tipo List de Map<String,Object> con
	 * todos los registros asociados a un id solicitud y el nombre de la persona que la realiza .
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el id de la solicitud.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized List<Map<String, Object>> buscarSolicitudConPersona(String as_idSolicitud)
	    throws B2BException
	{
		List<Map<String, Object>> lcmso_datos;
		DAOManager                ldm_manager;

		lcmso_datos     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
				lcmso_datos = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findByIdSolicitudWithPersona(
					    as_idSolicitud
					);

			if(!CollectionUtils.isValidCollection(lcmso_datos))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarSolicitudConPersona", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcmso_datos;
	}

	/**
	 * Cargar datos panel solicitud visitas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized EjecucionVisitas cargarDatosEjecucionVisitas(String as_idSolicitud, String as_userId)
	    throws B2BException
	{
		EjecucionVisitas lev_infoVisita;
		DAOManager       ldm_manager;

		lev_infoVisita     = null;
		ldm_manager        = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<SolicitudVisitas> lcsv_panelSolicitudVisita;
			Collection<TramiteVisita>    lcs_tramites;
			Map<String, Boolean>         lmsb_permisosRoles;

			lcsv_panelSolicitudVisita     = cargarDatosPanelSolicitudVisitas(as_idSolicitud, as_userId, ldm_manager);
			lcs_tramites                  = cargarDatosPanelTramitesARealizar(ldm_manager);
			lmsb_permisosRoles            = cargarDatosRolesUsuarios(ldm_manager, as_userId);

			if(
			    CollectionUtils.isValidCollection(lcsv_panelSolicitudVisita)
				    && CollectionUtils.isValidCollection(lcs_tramites)
				    && CollectionUtils.isValidCollection(lmsb_permisosRoles)
			)
			{
				lev_infoVisita = new EjecucionVisitas();

				{
					Iterator<SolicitudVisitas> lisv_iterator;

					lisv_iterator = lcsv_panelSolicitudVisita.iterator();

					if(lisv_iterator != null)
					{
						SolicitudVisitas lsv_info;

						lsv_info = lisv_iterator.next();

						if(lsv_info != null)
							lev_infoVisita.setIdSubProceso(lsv_info.getIdSubProceso());
					}
				}

				lev_infoVisita.setInformacionSolicitudVisita(lcsv_panelSolicitudVisita);
				lev_infoVisita.setTramitesARealizar(lcs_tramites);
				lev_infoVisita.setPermisosRoles(lmsb_permisosRoles);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosEjecucionVisitas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lev_infoVisita;
	}

	/**
	 * Cargar datos panel tipo tramites A realizar.
	 *
	 * @param as_idTramiteVisita de as id tramite visita
	 * @param as_idRol de as id rol
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Collection<TramiteVisitaTipo> cargarDatosPanelTipoTramitesARealizar(
	    String as_idTramiteVisita, String as_idRol
	)
	    throws B2BException
	{
		Collection<TramiteVisitaTipo> lctvt_tipoTramiteVisita;
		DAOManager                    ldm_manager;

		lctvt_tipoTramiteVisita     = null;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			Collection<TramiteVisitaRol> lctvr_tipoTramitesPorRol;

			lctvr_tipoTramitesPorRol = DaoCreator.getTramiteVisitaRolDAO(ldm_manager)
					                                 .findAllActivoByIdRolIdTramiteVisita(as_idRol, as_idTramiteVisita);

			if(CollectionUtils.isValidCollection(lctvr_tipoTramitesPorRol))
			{
				TramiteVisitaTipoDAO ltvtd_DAO;

				ltvtd_DAO                   = DaoCreator.getTramiteVisitaTipoDAO(ldm_manager);
				lctvt_tipoTramiteVisita     = new ArrayList<TramiteVisitaTipo>(1);

				for(TramiteVisitaRol ltvr_iterator : lctvr_tipoTramitesPorRol)
				{
					if(ltvr_iterator != null)
					{
						String ls_idTramiteVisitaTipo;

						ls_idTramiteVisitaTipo = ltvr_iterator.getIdTramiteVisitaTipo();

						if(StringUtils.isValidString(ls_idTramiteVisitaTipo))
						{
							TramiteVisitaTipo ltvt_tipoTramiteVisita;

							ltvt_tipoTramiteVisita = ltvtd_DAO.findActivoById(ls_idTramiteVisitaTipo);

							if(ltvt_tipoTramiteVisita != null)
								lctvt_tipoTramiteVisita.add(ltvt_tipoTramiteVisita);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosEjecucionVisitas", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lctvt_tipoTramiteVisita;
	}

	/**
	 * Cargar detalle ejecucion visitas.
	 *
	 * @param as_tipoTramiteSeleccionado de as tipo tramite seleccionado
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ejecucion visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DetalleEjecucionVisitas cargarDetalleEjecucionVisitas(
	    String as_tipoTramiteSeleccionado, String as_idSolicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DetalleEjecucionVisitas ldev_datosDetalle;

		ldev_datosDetalle = null;

		if(StringUtils.isValidString(as_tipoTramiteSeleccionado))
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				String ls_idSolicitudVisitas;

				ls_idSolicitudVisitas = null;

				{
					Collection<SolicitudVisitas> lcsv_solicitudesVisitas;

					lcsv_solicitudesVisitas = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
							                                .findByIdSolicitud(as_idSolicitud);

					if(CollectionUtils.isValidCollection(lcsv_solicitudesVisitas))
					{
						Iterator<SolicitudVisitas> lisv_iterator;

						lisv_iterator = lcsv_solicitudesVisitas.iterator();

						if(lisv_iterator != null)
						{
							SolicitudVisitas lsv_solicitudVisitas;

							lsv_solicitudVisitas = lisv_iterator.next();

							if(lsv_solicitudVisitas != null)
								ls_idSolicitudVisitas = lsv_solicitudVisitas.getIdSolicitudVisitas();
						}
					}
				}

				if(StringUtils.isValidString(ls_idSolicitudVisitas))
				{
					TramiteVisitaTipo ltvt_tramiteVisitaTipo;

					ltvt_tramiteVisitaTipo = DaoCreator.getTramiteVisitaTipoDAO(ldm_manager)
							                               .findActivoById(as_tipoTramiteSeleccionado);

					if(ltvt_tramiteVisitaTipo != null)
					{
						String ls_idTramite;
						String ls_idTramiteTipo;

						ls_idTramite         = ltvt_tramiteVisitaTipo.getIdTramiteVisita();
						ls_idTramiteTipo     = ltvt_tramiteVisitaTipo.getIdTramiteVisitaTipo();

						if(StringUtils.isValidString(ls_idTramite) && StringUtils.isValidString(ls_idTramiteTipo))
						{
							Constantes lc_constante;

							lc_constante = consultarConstante(ls_idTramite, ls_idTramiteTipo, ldm_manager);

							if(lc_constante != null)
								ldev_datosDetalle = llenarDetalleEjecucionVisitas(
									    lc_constante.getIdConstante(), ls_idSolicitudVisitas, null, as_userId,
									    as_remoteIp, ldm_manager
									);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("cargarDetalleEjecucionVisitas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldev_datosDetalle;
	}

	/**
	   * Cargar texto campo.
	   *
	   * @param ab_auto de ab auto
	   * @param as_idSolicitudVisitas de as id solicitud visitas
	   * @return el valor de collection
	   * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	   */
	public synchronized String[] cargarTextoCampos(boolean ab_auto, String as_idSolicitudVisitas)
	    throws B2BException
	{
		String[]   las_infoCampos;
		DAOManager ldm_manager;

		las_infoCampos     = null;
		ldm_manager        = DaoManagerFactory.getDAOManager();

		try
		{
			TagPlantillaResolucionDAO lprd_DAO;
			SolicitudVisitas          lsv_solicitud;

			lprd_DAO           = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager);
			las_infoCampos     = new String[2];
			lsv_solicitud      = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);

			if(lsv_solicitud != null)
			{
				String ls_texto;

				ls_texto = null;

				if(ab_auto)
				{
					TagPlantillaResolucion lpr_plantilla;

					lpr_plantilla = lprd_DAO.findByIdPlantillaTag(
						    ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL, "TAG_ORDENA"
						);

					if(lpr_plantilla != null)
						ls_texto = ConversionTextos.convertirTextosFormatosCertificados(lpr_plantilla.getTexto());
				}
				else
				{
					TagPlantillaResolucion lpr_plantilla;

					lpr_plantilla = lprd_DAO.findByIdPlantillaTag(
						    ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION, "TAG_RESUELVE_RESOLUCION"
						);

					if(lpr_plantilla != null)
						ls_texto = ConversionTextos.convertirTextosFormatosCertificados(lpr_plantilla.getTexto());
				}

				if(StringUtils.isValidString(ls_texto))
				{
					ls_texto              = resolverTagNombreOrip(ls_texto, lsv_solicitud.getIdCirculo(), ldm_manager);
					ls_texto              = resolverTagsFechaDesdeHasta(ls_texto, lsv_solicitud);
					las_infoCampos[0]     = ls_texto;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarTextoCampos", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return las_infoCampos;
	}

	/**
	 * Consultar usuarios reasignacion.
	 *
	 * @param as_idSolicitud correspondiente al valor de as id solicitud
	 * @param as_idSolicitud2 correspondiente al valor de as id solicitud 2
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de collection
	 */
	public Collection<Usuario> consultarUsuariosReasignacion(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Usuario> lcu_usuariosReturn;
		DAOManager          ldm_manager;

		lcu_usuariosReturn     = new LinkedList<Usuario>();
		ldm_manager            = DaoManagerFactory.getDAOManager();

		try
		{
			if(StringUtils.isValidString(as_idSolicitud))
			{
				Solicitud ls_solicitud;

				ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(as_idSolicitud);

				if(ls_solicitud != null)
				{
					Collection<SolicitudInterviniente> lcu_solicitudInterviniente;

					lcu_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
							                                   .findByIdSolicitud(as_idSolicitud);

					if(lcu_solicitudInterviniente != null)
					{
						Persona             lp_persona;
						Collection<Persona> lcp_persona;

						lp_persona      = new Persona();
						lcp_persona     = new LinkedList<Persona>();

						for(SolicitudInterviniente osi_osi : lcu_solicitudInterviniente)
						{
							lp_persona.setIdPersona(osi_osi.getIdPersona());
							lcp_persona.add(DaoCreator.getPersonaDAO(ldm_manager).findById(lp_persona));
						}

						if(lcp_persona != null)
						{
							Usuario liu_usuarioIterador;

							liu_usuarioIterador = new Usuario();
							liu_usuarioIterador.setIdDocumentoTipo(IdentificadoresCommon.CC);

							for(Persona ip_ip : lcp_persona)
							{
								liu_usuarioIterador.setNumeroDocumento(ip_ip.getNumeroDocumento());

								lcu_usuariosReturn.add(
								    DaoCreator.getUsuarioDAO(ldm_manager).findByTipoDoc(liu_usuarioIterador)
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

			clh_LOGGER.error("consultarUsuariosReasignacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_usuariosReturn;
	}

	/**
	 * Generar acta visita.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarActaVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp,
	    TurnoHistoria ath_turnoHistoria
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_ACTA_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_ACTA_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(ls_plantilla.contains(TagCommon.TAG_FECHA))
						{
							Date       ld_fechaActual;
							DateFormat lsf_dateFormat;

							ld_fechaActual     = new Date();
							lsf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);

							String ls_fecha    = lsf_dateFormat.format(ld_fechaActual);

							ls_plantilla       = ls_plantilla.replace(
								    TagCommon.TAG_FECHA, StringUtils.getStringNotNull(ls_fecha)
								);
						}

						{
							Calendar lc_fecha;

							lc_fecha = Calendar.getInstance();

							int li_dia;
							int li_mes;
							int li_anio;

							li_dia      = lc_fecha.get(Calendar.DAY_OF_MONTH);
							li_mes      = lc_fecha.get(Calendar.MONTH);
							li_anio     = lc_fecha.get(Calendar.YEAR);

							if(ls_plantilla.contains(TagCommon.TAG_DIA))
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_DIA, StringUtils.getStringNotNull(String.valueOf(li_dia))
									);

							if(ls_plantilla.contains(TagCommon.TAG_MES))
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MES, StringUtils.getStringNotNull(String.valueOf(li_mes))
									);

							if(ls_plantilla.contains(TagCommon.TAG_ANO))
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_ANO, StringUtils.getStringNotNull(String.valueOf(li_anio))
									);
						}

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
					else
					{
						String ls_idSolicitud;
						ls_idSolicitud = lsv_solicitud.getIdSolicitud();

						try
						{
							DocumentosSalida lds_documento;

							lds_documento = new DocumentosSalida();

							lds_documento.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							lds_documento.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);
							lds_documento.setTipo(TipoArchivoCommon.ACTA_VISITA);
							lds_documento.setEstado(EstadoCommon.ACTIVO);
							lds_documento.setDocumentoAutomatico(EstadoCommon.S);
							lds_documento.setRepositorio(RepositorioCommon.FINAL);
							lds_documento.setDocumentoFinal(EstadoCommon.S);
							lds_documento.setIdSolicitud(ls_idSolicitud);
							lds_documento.setIdEcm(IdentificadoresCommon.X);
							lds_documento.setIdNombreDocumento(IdentificadoresCommon.X);

							if((ath_turnoHistoria != null))
							{
								lds_documento.setIdTurnoHistoria(
								    NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria())
								);
								lds_documento.setIdTurno(ath_turnoHistoria.getIdTurno());
							}

							insertarDocumentoSalida(lba_documento, lds_documento, as_userId, as_remoteIp, ldm_manager);
						}
						catch(B2BException lb2be_e)
						{
							clh_LOGGER.error("guardarDocumentoSalida", lb2be_e);

							throw lb2be_e;
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarActaVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto anulacion visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoAnulacionVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					String ls_idSolicitud;

					ls_idSolicitud = lsv_solicitud.getIdSolicitud();

					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_ANULACION_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_ANULACION_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_ANULACION_TURNO_VISITA))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_ANULACION_TURNO_VISITA, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_ANULACION_TURNO_VISITA, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoAnulacionVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto cierre visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoCierreVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_CIERRE_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_CIERRE_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_CIERRE_VISITA))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_CIERRE_VISITA, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_CIERRE_VISITA, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoCierreVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto modificacion visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoModificacionVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_MODIFICACION_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_MODIFICACION_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_MODIFICACION))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_MODIFICACION, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_MODIFICACION, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoModificacionVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto ordena visita general.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto             de aot texto
	 * @param as_userId             de as user id
	 * @param as_remoteIp           de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se
	 *                      encuentra algun error controlado.
	 */
	public byte[] generarAutoOrdenaVisitaGeneral(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_definitivo;
				boolean          lb_intervinientes;

				lcd_DAO               = DaoCreator.getConstantesDAO(ldm_manager);
				lsv_solicitud         = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);
				lb_definitivo         = aot_texto != null;
				lb_intervinientes     = false;

				if(lsv_solicitud != null)
				{
					String ls_idSolicitud;
					String ls_plantilla;

					ls_idSolicitud     = lsv_solicitud.getIdSolicitud();
					ls_plantilla       = null;

					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes(ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL);

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, null, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_ORDENA) && !lb_definitivo)
						{
							TagPlantillaResolucion lpr_plantilla;

							lpr_plantilla = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager)
									                      .findByIdPlantillaTag(
									    ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL, "TAG_ORDENA"
									);

							if(lpr_plantilla != null)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_ORDENA,
									    ConversionTextos.convertirTextoSaltoLinea(lpr_plantilla.getTexto())
									);
							else
							{
								Object[] loa_args;

								loa_args        = new String[2];
								loa_args[0]     = ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL;
								loa_args[1]     = TagCommon.TAG_ORDENA;

								throw new B2BException(
								    "No se encontro tag plantilla resolucion para los argumentos: {0} - {1}", loa_args
								);
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						ls_plantilla = resolverTagsFechaDesdeHasta(ls_plantilla, lsv_solicitud);
					}

					if(lb_definitivo)
					{
						if(ls_plantilla.contains(TagCommon.TAG_ORDENA))
							ls_plantilla = ls_plantilla.replace(TagCommon.TAG_ORDENA, aot_texto.getResuelve());

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
							ls_plantilla = ls_plantilla.replace(
								    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_VISITA))
							ls_plantilla = ls_plantilla.replace(TagCommon.TAG_MOTIVO_VISITA, aot_texto.getRazon1());
					}

					lb_intervinientes = ls_plantilla.contains(TagCommon.TAG_INTERVINIENTES_VISITAS);

					if(lb_intervinientes)
					{
						int li_finalTag;

						li_finalTag = ls_plantilla.lastIndexOf(TagCommon.TAG_INTERVINIENTES_VISITAS);

						if(li_finalTag > 0)
						{
							String ls_textoMitadSuperior;
							String ls_textoMitadInferior;
							String ls_tagNew;

							ls_textoMitadSuperior     = ls_plantilla.substring(0, li_finalTag);
							ls_textoMitadInferior     = ls_plantilla.substring(
								    li_finalTag + TagCommon.TAG_INTERVINIENTES_VISITAS.length()
								);

							ls_tagNew     = BookMarkCommon.INTERVINIENTES_VISITAS_BKM
								+ TagCommon.TAG_INTERVINIENTES_VISITAS;

							ls_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
								+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
						}
					}

					ConstantesDAO lc_DAO;

					lc_DAO = DaoCreator.getConstantesDAO(ldm_manager);

					String ls_tag;

					ls_tag = "<TAG_FIRMA_USUARIO_VISITADOR>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = putCustomBookMark(
							    ls_plantilla, ls_tag, "FIRMA_USUARIO_VISITADOR",
							    lc_DAO.findById(ConstanteCommon.USUARIO_FIRMA_SUSPENSION), false
							);

					if(StringUtils.isValidString(ls_plantilla) && lb_intervinientes)
					{
						com.aspose.words.Document ld_doc;
						ByteArrayInputStream      lbais_docInStream;
						ByteArrayOutputStream     lbaos_docOutStream;
						DocumentBuilder           ldb_builder;
						Table                     lt_table;
						Font                      lf_font;
						int                       li_tamanoLetra;
						long                      ll_porcentajetablas;

						ld_doc                 = null;
						lbais_docInStream      = new ByteArrayInputStream(ls_plantilla.getBytes());
						lbaos_docOutStream     = new ByteArrayOutputStream();
						ld_doc                 = new com.aspose.words.Document(lbais_docInStream);
						ldb_builder            = new DocumentBuilder(ld_doc);
						ldb_builder.moveToBookmark(BookMarkCommon.INTERVINIENTES_VISITAS);
						lt_table                = null;
						lf_font                 = ldb_builder.getFont();
						li_tamanoLetra          = 11;
						ll_porcentajetablas     = (long)50.0;

						{
							Table lt_encabezado;

							lt_encabezado = ldb_builder.startTable();

							ldb_builder.insertCell();
							ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							lf_font.setUnderline(0);
							lf_font.setBold(true);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));

							lf_font.setSize(li_tamanoLetra);
							lf_font.setBold(true);
							ldb_builder.write("Funcionario");

							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							lf_font.setSize(li_tamanoLetra);
							ldb_builder.write("Identificación");
							ldb_builder.insertCell();
							ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
							ldb_builder.getCellFormat()
								           .setPreferredWidth(PreferredWidth.fromPercent(ll_porcentajetablas));
							lf_font.setSize(li_tamanoLetra);
							ldb_builder.write("Dependencia");

							ldb_builder.endRow();
							lt_encabezado.setAlignment(TableAlignment.CENTER);
							ldb_builder.endTable();
						}

						{
							Collection<SolicitudInterviniente> lcsi_intervinientes;

							lcsi_intervinientes = null;

							if(StringUtils.isValidString(ls_idSolicitud))
								lcsi_intervinientes = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
										                            .findByIdSolicitud(ls_idSolicitud);

							if(CollectionUtils.isValidCollection(lcsi_intervinientes))
							{
								PersonaDAO        lpd_DAO;
								DependenciaSNRDAO ldd_DAO;

								lpd_DAO      = DaoCreator.getPersonaDAO(ldm_manager);
								ldd_DAO      = DaoCreator.getDependenciaSNRDAO(ldm_manager);
								lt_table     = ldb_builder.startTable();

								for(SolicitudInterviniente lsi_interviniente : lcsi_intervinientes)
								{
									if(lsi_interviniente != null)
									{
										Persona lp_persona;

										lp_persona = lpd_DAO.findById(lsi_interviniente.getIdPersona());

										if(lp_persona != null)
										{
											ldb_builder.insertCell();
											ldb_builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											lf_font.setUnderline(0);
											lf_font.setBold(false);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);

											lf_font.setSize(li_tamanoLetra);
											lf_font.setBold(false);
											ldb_builder.write(StringUtils.getString(lp_persona.getNombreCompleto()));

											ldb_builder.insertCell();
											ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
											ldb_builder.getCellFormat()
												           .setPreferredWidth(
												    PreferredWidth.fromPercent(ll_porcentajetablas)
												);
											lf_font.setSize(li_tamanoLetra);
											ldb_builder.write(lp_persona.getNumeroDocumento());

											{
												DependenciaSNR ld_dependencia;

												ld_dependencia = ldd_DAO.findById(lsi_interviniente.getIdDependencia());

												if(ld_dependencia != null)
												{
													ldb_builder.insertCell();
													ldb_builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
													ldb_builder.getCellFormat()
														           .setPreferredWidth(
														    PreferredWidth.fromPercent(ll_porcentajetablas)
														);
													lf_font.setSize(li_tamanoLetra);
													ldb_builder.write(ld_dependencia.getNombreDependencia());
												}
											}

											ldb_builder.endRow();
										}
									}
								}
							}
						}

						{
							Iterator<Row> lir_temp;

							lir_temp = (lt_table != null) ? lt_table.iterator() : null;

							if((lir_temp != null) && lir_temp.hasNext())
								lt_table.setAlignment(TableAlignment.CENTER);

							ldb_builder.endTable();

							ld_doc.save(lbaos_docOutStream, SaveFormat.DOC);

							lba_documento = lbaos_docOutStream.toByteArray();
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(
						    lb_intervinientes ? lba_documento : ls_plantilla.getBytes(), ldm_manager
						);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					if(!lb_definitivo)
						guardarDocumentoSalidaVisitas(
						    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, false, ldm_manager, null
						);
					else
						actualizarDocumentoSalida(
						    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
						);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoOrdenaVisitaGeneral", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("Exception", le_e);

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoOrdenaVisitaGeneral", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto prorroga visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoProrrogaVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_PRORROGA_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_PRORROGA_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_INICIAL_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaInicialPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(TagCommon.TAG_FECHA_INICIAL_PANTALLA, ls_fecha);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_INICIAL_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_FINAL_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaFinalPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(TagCommon.TAG_FECHA_FINAL_PANTALLA, ls_fecha);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_FINAL_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoProrrogaVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto reanudacion visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoReanudacionVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_REANUDACION_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_REANUDACION_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_REANUDACION))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_REANUDACION, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_REANUDACION, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoReanudacionVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto reasignacion turno.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoReasignacionTurno(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_REASIGNACION_TURNO));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_REASIGNACION_TURNO;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_SOLICITUD_INTERVINIENTE))
						{
							String                             ls_idPersona;
							String                             ls_remplazoTag;
							Collection<SolicitudInterviniente> lcsi_solicitudIntervinientes;
							Persona                            lp_persona;

							ls_idPersona                     = "";
							ls_idSolicitud                   = lsv_solicitud.getIdSolicitud();
							lcsi_solicitudIntervinientes     = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
									                                         .findByIdSolicitud(ls_idSolicitud);

							for(SolicitudInterviniente isi_solicitudIntervinientes : lcsi_solicitudIntervinientes)
							{
								if(isi_solicitudIntervinientes.getVisitadorPrincipal().equals(EstadoCommon.S))
								{
									ls_idPersona = isi_solicitudIntervinientes.getIdPersona();

									break;
								}
							}

							if(StringUtils.isValidString(ls_idPersona))
							{
								lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);

								if(lp_persona != null)
								{
									ls_remplazoTag = lp_persona.getNombreCompleto();

									if(StringUtils.isValidString(ls_remplazoTag))
										ls_plantilla = ls_plantilla.replace(
											    TagCommon.TAG_SOLICITUD_INTERVINIENTE, ls_remplazoTag
											);
								}
							}
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoReasignacionTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar auto revocatoria visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoRevocatoriaVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_REVOCATORIA_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_REVOCATORIA_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_REVOCATORIA_TURNO_VISITA))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_REVOCATORIA_TURNO_VISITA, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_REVOCATORIA_TURNO_VISITA,
									    IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoRevocatoriaVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar suspension visita.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarAutoSuspensionVisita(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_AUTO_SUSPENSION_VISITA));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AUTO_SUSPENSION_VISITA;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								ls_idSolicitud = lsv_solicitud.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_idSubProceso;
										String ls_idProceso;

										ls_idSubProceso     = ls_solicitud.getIdSubproceso();
										ls_idProceso        = ls_solicitud.getIdProceso();

										if(
										    StringUtils.isValidString(ls_idSubProceso)
											    && StringUtils.isValidString(ls_idProceso)
										)
										{
											Subproceso ls_subproceso;

											ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
													                      .findById(ls_idProceso, ls_idSubProceso);

											if(ls_subproceso != null)
											{
												String ls_remplazoTag = ls_subproceso.getNombre();

												if(StringUtils.isValidString(ls_remplazoTag))
													ls_plantilla = ls_plantilla.replace(
														    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
														);
											}
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_SUSPENSION))
						{
							if(lb_datosCamposPantallaValidos)
							{
								String ls_reemplazoTag = aot_texto.getRazon1();

								if(StringUtils.isValidString(ls_reemplazoTag))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_MOTIVO_SUSPENSION, ls_reemplazoTag
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_SUSPENSION, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_INICIAL_SUSPENSION))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaInicialPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_FECHA_INICIAL_SUSPENSION, ls_fecha
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_INICIAL_SUSPENSION, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_FINAL_SUSPENSION))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaFinalPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(TagCommon.TAG_FECHA_FINAL_SUSPENSION, ls_fecha);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_FINAL_SUSPENSION, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarAutoSuspensionVisita", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar documentos.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_textoTags de aot texto tags
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ejecucion visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized DetalleEjecucionVisitas generarDocumentos(
	    String as_idConstante, String as_idSolicitudVisitas, OficiosTexto aot_textoTags, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		DetalleEjecucionVisitas ldev_informacionDetalleEjecucion;

		ldev_informacionDetalleEjecucion = null;

		if(
		    StringUtils.isValidString(as_idConstante) && StringUtils.isValidString(as_idSolicitudVisitas)
			    && validarDatosAuditoria(as_userId, as_remoteIp)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				ldev_informacionDetalleEjecucion = llenarDetalleEjecucionVisitas(
					    as_idConstante, as_idSolicitudVisitas, aot_textoTags, as_userId, as_remoteIp, ldm_manager
					);
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("generarDocumentos", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ldev_informacionDetalleEjecucion;
	}

	/**
	 * Generar informe aviso convocatoria audiencia publica reclamaciones.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarInformeAvisoConvocatoriaAudienciaPublicaReclamaciones(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla      = null;
				lsv_solicitud     = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);

				lb_datosCamposPantallaValidos = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes(
							    (ConstanteCommon.PLANTILLA_AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES)
							);

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONVOCA_PANTALLA))

							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONVOCA_PANTALLA,
									    StringUtils.getStringNotNull(aot_texto.getConsideracion())
									);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInformeAvisoConvocatoriaAudienciaPublicaReclamaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar informe intervencion obs hallazgos.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarInformeIntervencionObsHallazgos(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes(
							    (ConstanteCommon.PLANTILLA_INFORME_INTERVENCION_OBS_HALLAZGOS)
							);

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_INFORME_INTERVENCION_OBS_HALLAZGOS;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInformeIntervencionObsHallazgos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar informe visita final.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarInformeVisitaFinal(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

//						if(!lb_definitivo)
//							guardarDocumentoSalidaVisitas(
//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
//							);
//						else
//							actualizarDocumentoSalida(
//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInformeVisitaFinal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar informe visita semanal.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarInformeVisitaSemanal(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_INFORME_VISITA_SEMANAL));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_INFORME_VISITA_SEMANAL;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInformeVisitaSemanal", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar oficio requerimientos.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarOficioRequerimientos(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes((ConstanteCommon.PLANTILLA_OFICIO_REQUERIMIENTOS));

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_OFICIO_REQUERIMIENTOS;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

//						if(!lb_definitivo)
//							guardarDocumentoSalidaVisitas(
//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
//							);
//						else
//							actualizarDocumentoSalida(
//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarOficioRequerimientos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar resolucion levantamiento intervencion.
	 *
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_texto de aot texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarResolucionLevantamientoIntervencion(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla      = null;
				lsv_solicitud     = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);

				lb_datosCamposPantallaValidos = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes(
							    (ConstanteCommon.PLANTILLA_RESOLUCION_LEVANTAMIENTO_INTERVENCION)
							);

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_LEVANTAMIENTO_INTERVENCION;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla     = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);
						ls_plantilla     = resolverTagsResolucion(ls_plantilla);
						ls_plantilla     = resolverTagNombreOrip(
							    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
							);
						ls_plantilla     = resolverTagsPanelVisitas(
							    ls_plantilla, lsv_solicitud.getIdSolicitud(), as_userId, ldm_manager
							);

						if(lb_datosCamposPantallaValidos)
							ls_plantilla = resolverTagsInformes(ls_plantilla, aot_texto);
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarInformeAvisoConvocatoriaAudienciaPublicaReclamaciones", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Método encargado de generar resolucion ordena intervencion.
	 *
	 * @param as_idSolicitudVisitas Argumento de tipo <code>String</code> que
	 *                              contiene el id de la solicitud visitas.
	 * @param aot_texto             Argumento de tipo <code>OficiosTexto</code> que
	 *                              contiene el oficio texto de la plantilla.
	 * @param as_userId             Argumento de tipo <code>String</code> que
	 *                              contiene el id del usuario que realiza la
	 *                              operación.
	 * @param as_remoteIp           Argumento de tipo <code>String</code> que
	 *                              contiene la direccion ip de donde se ejecuta la
	 *                              operación.
	 * @return Variable de tipo <code>List</code> de byte con la información de la
	 *         plantilla
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se
	 *                      encuentra algun error controlado.
	 */
	public byte[] generarResolucionOrdenaIntervencion(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				boolean          lb_definitivo;
				String           ls_idSolicitud;

				lcd_DAO            = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla       = null;
				lsv_solicitud      = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);
				lb_definitivo      = aot_texto != null;
				ls_idSolicitud     = null;

				if(lsv_solicitud != null)
				{
					{
						Constantes lc_constante;
						byte[]     lba_plantilla;

						lc_constante = lcd_DAO.findImagen(
							    new Constantes(ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION)
							);

						if(lc_constante == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						lba_plantilla = lc_constante.getImagenes();

						if(lba_plantilla != null)
							ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsResolucion(ls_plantilla);

						{
							TagPlantillaResolucionDAO ltr_DAO;

							ltr_DAO = DaoCreator.getTagPlantillaResolucionDAO(ldm_manager);

							if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_RESOLUCION))
							{
								TagPlantillaResolucion lpr_plantilla;

								lpr_plantilla = ltr_DAO.findByIdPlantillaTag(
									    ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION,
									    "TAG_CONSIDERACIONES_RESOLUCION"
									);

								if(lpr_plantilla != null)
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_CONSIDERACIONES_RESOLUCION,
										    ConversionTextos.convertirTextoSaltoLinea(lpr_plantilla.getTexto())
										);
								else
								{
									Object[] loa_args;

									loa_args        = new String[2];
									loa_args[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION;
									loa_args[1]     = TagCommon.TAG_CONSIDERACIONES_RESOLUCION;

									throw new B2BException(
									    "No se encontro tag plantilla resolucion para los argumentos: {0} - {1}",
									    loa_args
									);
								}
							}

							if(ls_plantilla.contains(TagCommon.TAG_RESUELVE_RESOLUCION) && !lb_definitivo)
							{
								TagPlantillaResolucion lpr_plantilla;

								lpr_plantilla = ltr_DAO.findByIdPlantillaTag(
									    ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION,
									    "TAG_RESUELVE_RESOLUCION"
									);

								if(lpr_plantilla != null)
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_RESUELVE_RESOLUCION,
										    ConversionTextos.convertirTextoSaltoLinea(lpr_plantilla.getTexto())
										);
								else
								{
									Object[] loa_args;

									loa_args        = new String[2];
									loa_args[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION;
									loa_args[1]     = TagCommon.TAG_RESUELVE_RESOLUCION;

									throw new B2BException(
									    "No se encontro tag plantilla resolucion para los argumentos: {0} - {1}",
									    loa_args
									);
								}
							}

							ls_plantilla = resolverTagsFechaDesdeHasta(ls_plantilla, lsv_solicitud);

							if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
							{
								ls_idSolicitud = lsv_solicitud.getIdSolicitud();

								if(StringUtils.isValidString(ls_idSolicitud))
								{
									Solicitud ls_solicitud;

									ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

									if(ls_solicitud != null)
									{
										String ls_idSubProceso;
										String ls_idProceso;

										ls_idSubProceso     = ls_solicitud.getIdSubproceso();
										ls_idProceso        = ls_solicitud.getIdProceso();

										if(
										    StringUtils.isValidString(ls_idSubProceso)
											    && StringUtils.isValidString(ls_idProceso)
										)
										{
											Subproceso ls_subproceso;

											ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
													                      .findById(ls_idProceso, ls_idSubProceso);

											if(ls_subproceso != null)
											{
												String ls_remplazoTag = ls_subproceso.getNombre();

												if(StringUtils.isValidString(ls_remplazoTag))
													ls_plantilla = ls_plantilla.replace(
														    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
														);
											}
										}
									}
								}
							}

							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);
						}
					}

					ConstantesDAO lc_DAO;

					lc_DAO = DaoCreator.getConstantesDAO(ldm_manager);

					String ls_tag;

					ls_tag = "<TAG_RESOLUCION>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = putCustomBookMark(
							    ls_plantilla, ls_tag, "RESOLUCION", lc_DAO.findById(ConstanteCommon.TAG_RESOL)
							);

					ls_tag = "<TAG_FECHA_RESOL>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = putCustomBookMark(
							    ls_plantilla, ls_tag, "FECHA_RESOL", lc_DAO.findById(ConstanteCommon.TAG_FECHA_RESOL)
							);

					ls_tag = "<TAG_FIRMA_USUARIO_VISITADOR>";

					if(ls_plantilla.contains(ls_tag))
						ls_plantilla = putCustomBookMark(
							    ls_plantilla, ls_tag, "FIRMA_USUARIO_VISITADOR",
							    lc_DAO.findById(ConstanteCommon.USUARIO_FIRMA_SUSPENSION)
							);

					if(lb_definitivo)
					{
						if(ls_plantilla.contains(TagCommon.TAG_RESUELVE_RESOLUCION))
							ls_plantilla = ls_plantilla.replace(
								    TagCommon.TAG_RESUELVE_RESOLUCION,
								    StringUtils.getStringNotNull(aot_texto.getResuelve())
								);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_PANTALLA))
							ls_plantilla = ls_plantilla.replace(
								    TagCommon.TAG_CONSIDERACIONES_PANTALLA,
								    StringUtils.getStringNotNull(aot_texto.getConsideracion())
								);

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_VISITA))
							ls_plantilla = ls_plantilla.replace(
								    TagCommon.TAG_MOTIVO_VISITA, StringUtils.getStringNotNull(aot_texto.getRazon1())
								);
					}

					{
						Map<String, String> lmss_datos;

						lmss_datos = finalizarPlantilla(ls_plantilla, null, lb_definitivo, ldm_manager);

						if(CollectionUtils.isValidCollection(lmss_datos))
						{
							ls_plantilla = lmss_datos.get(IdentificadoresCommon.PLANTILLA);

							if(StringUtils.isValidString(ls_plantilla))
								lba_documento = new PDFGenerator().convertirRTFToPDF(
									    ls_plantilla.getBytes(), ldm_manager
									);
						}
					}

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					if(!lb_definitivo)
						guardarDocumentoSalidaVisitas(
						    lba_documento, as_userId, as_remoteIp, false, ls_idSolicitud, false, ldm_manager, null
						);
					else
						actualizarDocumentoSalida(
						    lba_documento, as_userId, as_remoteIp, false, ls_idSolicitud, ldm_manager
						);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarResolucionOrdenaIntervencion", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("Exception", le_e);

			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarResolucionOrdenaIntervencion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Generar resolucion prorroga intervencion.
	 *
	 * @param as_idSolicitudVisitas correspondiente al valor de as id solicitud visitas
	 * @param aot_texto correspondiente al valor de aot texto
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarResolucionProrrogaIntervencion(
	    String as_idSolicitudVisitas, OficiosTexto aot_texto, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		byte[]     lba_documento;
		DAOManager ldm_manager;

		lba_documento     = null;
		ldm_manager       = DaoManagerFactory.getDAOManager();

		try
		{
			if(validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas))
			{
				ConstantesDAO    lcd_DAO;
				String           ls_plantilla;
				SolicitudVisitas lsv_solicitud;
				String           ls_idSolicitud;
				boolean          lb_datosCamposPantallaValidos;

				lcd_DAO                           = DaoCreator.getConstantesDAO(ldm_manager);
				ls_plantilla                      = null;
				lsv_solicitud                     = DaoCreator.getSolicitudVisitasDAO(ldm_manager)
						                                          .findById(as_idSolicitudVisitas);
				ls_idSolicitud                    = lsv_solicitud.getIdSolicitud();
				lb_datosCamposPantallaValidos     = aot_texto != null;

				if(lsv_solicitud != null)
				{
					{
						byte[] lba_plantilla;

						lba_plantilla = lcd_DAO.findImagenes(
							    (ConstanteCommon.PLANTILLA_RESOLUCION_PRORROGA_INTERVENCION)
							);

						if(lba_plantilla == null)
						{
							Object[] loa_messageArgs;

							loa_messageArgs        = new String[1];
							loa_messageArgs[0]     = ConstanteCommon.PLANTILLA_RESOLUCION_PRORROGA_INTERVENCION;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
						}

						ls_plantilla = new String(lba_plantilla);
					}

					if(StringUtils.isValidString(ls_plantilla))
					{
						ls_plantilla = resolverTagsBasicos(ls_plantilla, lsv_solicitud, as_userId);

						if(ls_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_RESOLUCION))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_RESOLUCION, aot_texto.getConsideracion()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_CONSIDERACIONES_PANTALLA, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_MOTIVO_PRORROGA_RESOLUCION))
						{
							if(lb_datosCamposPantallaValidos)
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_PRORROGA_RESOLUCION, aot_texto.getResuelve()
									);
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_MOTIVO_PRORROGA_RESOLUCION, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
						{
							ls_idSolicitud = lsv_solicitud.getIdSolicitud();

							if(StringUtils.isValidString(ls_idSolicitud))
							{
								Solicitud ls_solicitud;

								ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

								if(ls_solicitud != null)
								{
									String ls_idSubProceso;
									String ls_idProceso;

									ls_idSubProceso     = ls_solicitud.getIdSubproceso();
									ls_idProceso        = ls_solicitud.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idSubProceso)
										    && StringUtils.isValidString(ls_idProceso)
									)
									{
										Subproceso ls_subproceso;

										ls_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager)
												                      .findById(ls_idProceso, ls_idSubProceso);

										if(ls_subproceso != null)
										{
											String ls_remplazoTag = ls_subproceso.getNombre();

											if(StringUtils.isValidString(ls_remplazoTag))
												ls_plantilla = ls_plantilla.replace(
													    TagCommon.TAG_TIPO_VISITA, ls_remplazoTag
													);
										}
									}
								}
							}
						}

						if(ls_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP))
							ls_plantilla = resolverTagNombreOrip(
								    ls_plantilla, lsv_solicitud.getIdCirculo(), ldm_manager
								);

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_INICIAL_PRORROGA_RESOL))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaInicialPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_FECHA_INICIAL_PRORROGA_RESOL, ls_fecha
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_INICIAL_PRORROGA_RESOL, IdentificadoresCommon.DATO_INVALIDO
									);
						}

						if(ls_plantilla.contains(TagCommon.TAG_FECHA_FINAL_PRORROGA_RESOL))
						{
							if(lb_datosCamposPantallaValidos)
							{
								DateFormat ldf_dateFormat;
								String     ls_fecha;

								ldf_dateFormat     = new SimpleDateFormat(FormatoFechaCommon.DIA_MES_ANIO);
								ls_fecha           = ldf_dateFormat.format(aot_texto.getFechaFinalPantalla());

								if(StringUtils.isValidString(ls_fecha))
									ls_plantilla = ls_plantilla.replace(
										    TagCommon.TAG_FECHA_FINAL_PRORROGA_RESOL, ls_fecha
										);
							}
							else
								ls_plantilla = ls_plantilla.replace(
									    TagCommon.TAG_FECHA_FINAL_PRORROGA_RESOL, IdentificadoresCommon.DATO_INVALIDO
									);
						}
					}

					ls_plantilla     = StringUtils.reemplazarCaracteresUTF8(ls_plantilla);

					lba_documento     = new PDFGenerator().convertirRTFToPDF(ls_plantilla.getBytes(), ldm_manager);

					lba_documento = corregirFirmaDocumento(lba_documento, ls_plantilla, ldm_manager);

					if(lba_documento == null)
						throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

					//						if(!lb_definitivo)
					//							guardarDocumentoSalidaVisitas(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager, null
					//							);
					//						else
					//							actualizarDocumentoSalida(
					//							    lba_documento, as_userId, as_remoteIp, true, ls_idSolicitud, ldm_manager
					//							);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarResolucionProrrogaIntervencion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_documento;
	}

	/**
	 * Guardar documento OWCC.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idUsuario de as id usuario
	 * @param as_ipRemota de as ip remota
	 * @param ab_auto de ab auto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void guardarDocumentoOWCC(
	    String as_idSolicitud, String as_idUsuario, String as_ipRemota, boolean ab_auto
	)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
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
						DocumentosSalida lds_documento;

						lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
								                      .findLastByIdSolicitudTipo(
								    as_idSolicitud,
								    (ab_auto ? TipoDocumentalCommon.AUTO : TipoDocumentalCommon.RESOLUCION), true
								);

						if((lds_documento != null) && !lds_documento.isEnviadoOwcc())
						{
							enviarGestorDocumental(
							    lds_documento, lbpd_bitacora, ls_endpoint, as_idUsuario, as_ipRemota, ldm_manager
							);

							if(!lds_documento.isEnviadoOwcc())
								throw new B2BException(
								    "Ocurrió un error enviando el documento al OWCC, intentelo nuevamente"
								);
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
	 * Salvar ejecucion visitas.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aba_documento de aba documento
	 * @param aot_solucionTags de aot solucion tags
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void salvarEjecucionVisitas(
	    boolean ab_procesoReasignacion, Collection<Usuario> acu_usuarios, String as_idConstante,
	    String as_idSolicitudVisitas, byte[] aba_documento, OficiosTexto aot_solucionTags, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		if(
		    validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitudVisitas)
			    && StringUtils.isValidString(as_idConstante) && ByteArrayUtils.isValidArray(aba_documento)
			    && (aot_solucionTags != null)
		)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				SolicitudVisitas lsv_visitas;

				lsv_visitas = DaoCreator.getSolicitudVisitasDAO(ldm_manager).findById(as_idSolicitudVisitas);

				if(lsv_visitas != null)
				{
					if(ab_procesoReasignacion)
						salvarUsuariosVisitadoresReasignados(
						    acu_usuarios, lsv_visitas.getIdSolicitud(), as_userId, as_remoteIp
						);

					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lsv_visitas.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_idSubProceso;
						String ls_idSolicitud;

						ls_idSubProceso     = ls_solicitud.getIdSubproceso();
						ls_idSolicitud      = ls_solicitud.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSubProceso) && StringUtils.isValidString(ls_idSolicitud))
						{
							TurnoHistoria    lth_historia;
							DocumentosSalida lds_documento;

							lth_historia      = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
									                          .findBySolicitudEtapa(
									    ls_idSolicitud, null, EtapaCommon.ID_ETAPA_830, EstadoCommon.ASIGNADA
									);
							lds_documento     = new DocumentosSalida();

							if(as_idConstante.equalsIgnoreCase(ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL))
								generarActaVisita(
								    as_idSolicitudVisitas, aot_solucionTags, as_userId, as_remoteIp, lth_historia
								);

							llenarDocumentoSalida(
							    as_idConstante, ls_idSolicitud, ls_idSubProceso, lds_documento, lth_historia, as_userId,
							    as_remoteIp
							);
							insertarDocumentoSalida(aba_documento, lds_documento, as_userId, as_remoteIp, ldm_manager);
							insertarActualizarOficioTexto(
							    ldm_manager, ls_idSolicitud, aot_solucionTags, lth_historia, as_userId, as_remoteIp
							);

							if(lth_historia != null)
							{
								MotivoTramite lmt_motivo;

								switch(as_idConstante)
								{
									case ConstanteCommon.PLANTILLA_INFORME_VISITA_SEMANAL:
										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager)
												                   .findById(
												    EtapaCommon.ID_ETAPA_830, MotivoTramiteCommon.INFORME_SEMANAL_830
												);

										break;

									case ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL:
										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager)
												                   .findById(
												    EtapaCommon.ID_ETAPA_830, MotivoTramiteCommon.INFORME_FINAL_830
												);

										break;

									case ConstanteCommon.PLANTILLA_INFORME_INTERVENCION_OBS_HALLAZGOS:
										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager)
												                   .findById(
												    EtapaCommon.ID_ETAPA_830,
												    MotivoTramiteCommon.INFORME_INTERVENCION_830
												);

										break;

									case ConstanteCommon.PLANTILLA_AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES:
										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager)
												                   .findById(
												    EtapaCommon.ID_ETAPA_830,
												    MotivoTramiteCommon.AVISO_CONVOCATORIA_DE_AUDIENCIA_PUBLICA_DE_RECLAMACIONES_830
												);

										break;

									default:
										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager)
												                   .findById(
												    EtapaCommon.ID_ETAPA_830,
												    MotivoTramiteCommon.ENVIO_DOCUMENTOS_EJECUCION_VISITAS
												);

										break;
								}

								if(lmt_motivo != null)
									terminarTurnoHistoriaYCrearEtapa(
									    lth_historia, ldm_manager, lmt_motivo, as_userId, as_remoteIp,
									    EstadoCommon.TERMINADA, true, true, true, true
									);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarEjecucionVisitas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}
	}

	/**
	 * Salvar informacion visitas.
	 *
	 * @param asv_solicitud de asv solicitud
	 * @param as_userId     de as user id
	 * @param as_remoteIp   de as remote ip
	 * @return el valor de solicitud visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se
	 *                      encuentra algun error controlado.
	 */
	public SolicitudVisitas salvarInformacionVisitas(
	    String as_idSolicitudComp, SolicitudVisitas asv_solicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		if(validarDatosAuditoria(as_userId, as_remoteIp) && (asv_solicitud != null))
		{
			try
			{
				Solicitud ls_solicitud;

				ls_solicitud = asv_solicitud.getSolicitud();

				if((ls_solicitud != null) && (!StringUtils.isValidString(as_idSolicitudComp)))
				{
					SolicitudDAO lsd_DAO;
					String       ls_idSolicitud;

					lsd_DAO            = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_idSolicitud     = StringUtils.getString(lsd_DAO.findSecuence());

					ls_solicitud.setIdSolicitud(ls_idSolicitud);
					ls_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					ls_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					lsd_DAO.insertOrUpdate(ls_solicitud, true);
					asv_solicitud.setIdSolicitud(ls_idSolicitud);
					asv_solicitud = DaoCreator.getSolicitudVisitasDAO(ldm_manager).insertOrUpdate(asv_solicitud, true);
				}
				else
				{
					SolicitudDAO lsd_DAO;
					String       ls_idSolicitud;

					lsd_DAO            = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_idSolicitud     = as_idSolicitudComp;

					ls_solicitud.setIdSolicitud(ls_idSolicitud);
					ls_solicitud.setIdAutorizacionComunicacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					ls_solicitud.setIdAutorizacionNotificacion(MedioNotificarCommon.CORREO_ELECTRONICO);
					lsd_DAO.insertOrUpdate(ls_solicitud, false);
					asv_solicitud.setIdSolicitud(ls_idSolicitud);

					asv_solicitud = DaoCreator.getSolicitudVisitasDAO(ldm_manager).insertOrUpdate(asv_solicitud, false);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarInformacionVisitas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return asv_solicitud;
	}

	/**
	 * Salvar usuarios visitadores.
	 *
	 * @param acu_usuarios   de acu usuarios
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId      de as user id
	 * @param as_remoteIp    de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se
	 *                      encuentra algun error controlado.
	 */
	public String salvarUsuariosVisitadores(
	    String as_nirComp, Collection<Usuario> acu_usuarios, String as_idSolicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_nir;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_nir          = null;

		if(
		    validarDatosAuditoria(as_userId, as_remoteIp) && StringUtils.isValidString(as_idSolicitud)
			    && (acu_usuarios != null)
		)
		{
			try
			{
				if(CollectionUtils.isValidCollection(acu_usuarios))
				{
					PersonaDAO                  lpd_DAO;
					PersonaCorreoElectronicoDAO lpcd_DAO;
					SolicitudIntervinienteDAO   lsid_DAO;
					long                        ll_id_correoElectonico;
					boolean                     lb_usuarioSeleccionado;
					boolean                     lb_visitadorPrincipal;
					int                         li_contadorVisitadorPrincipal;

					lpd_DAO                           = DaoCreator.getPersonaDAO(ldm_manager);
					lpcd_DAO                          = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);
					lsid_DAO                          = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
					ll_id_correoElectonico            = 0L;
					lb_usuarioSeleccionado            = false;
					lb_visitadorPrincipal             = false;
					li_contadorVisitadorPrincipal     = 0;

					for(Usuario lu_usuario : acu_usuarios)
					{
						if((lu_usuario != null) && lu_usuario.isSeleccionado())
						{
							Collection<Persona>    lcp_personas;
							SolicitudInterviniente lsi_interviniente;
							String                 ls_idPersona;

							lcp_personas               = lpd_DAO.findByDocumentoAndTipoDocumento(
								    lu_usuario.getNumeroDocumento(), lu_usuario.getIdDocumentoTipo(), true
								);
							lsi_interviniente          = new SolicitudInterviniente();
							ls_idPersona               = null;
							lb_usuarioSeleccionado     = true;
							lb_visitadorPrincipal      = lu_usuario.isVisitadorPrincipal();

							if(lb_visitadorPrincipal)
								li_contadorVisitadorPrincipal++;

							if(CollectionUtils.isValidCollection(lcp_personas))
							{
								{
									Persona lp_persona;

									lp_persona = lcp_personas.iterator().next();

									if(lp_persona != null)
										ls_idPersona = lp_persona.getIdPersona();
								}
							}
							else
							{
								Persona lp_persona;

								lp_persona = new Persona();

								lp_persona.setPrimerNombre(lu_usuario.getPrimerNombre());
								lp_persona.setSegundoNombre(lu_usuario.getSegundoNombre());
								lp_persona.setPrimerApellido(lu_usuario.getPrimerApellido());
								lp_persona.setSegundoApellido(lu_usuario.getSegundoApellido());
								lp_persona.setIdDocumentoTipo(lu_usuario.getIdDocumentoTipo());
								lp_persona.setNumeroDocumento(lu_usuario.getNumeroDocumento());
								lp_persona.setIdTipoPersona(TipoPersonaCommon.NATURAL);
								lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
								lp_persona.setIdUsuarioCreacion(as_userId);
								lp_persona.setIpCreacion(as_remoteIp);

								lp_persona = lpd_DAO.insertOrUpdate(lp_persona, true);

								if(lp_persona != null)
									ls_idPersona = lp_persona.getIdPersona();
							}

							{
								PersonaCorreoElectronico lpce_correo;

								lpce_correo = new PersonaCorreoElectronico();

								lpce_correo.setIdPersona(ls_idPersona);
								lpce_correo.setCorreoElectronico(lu_usuario.getCorreoElectronicoCorporativo());
								lpce_correo.setFechaDesde(new Date());
								lpce_correo.setIdUsuarioCreacion(as_userId);
								lpce_correo.setIpCreacion(as_remoteIp);

								ll_id_correoElectonico = lpcd_DAO.insertOrUpdate(lpce_correo, true);
							}

							lsi_interviniente.setIdPersona(ls_idPersona);
							lsi_interviniente.setIdSolicitud(as_idSolicitud);
							lsi_interviniente.setIdAutorizacionNotificacion(MedioNotificarCommon.CORREO_ELECTRONICO);
							lsi_interviniente.setIdAutorizacionComunicacion(MedioNotificarCommon.CORREO_ELECTRONICO);
							lsi_interviniente.setIdCorreoElectronico(StringUtils.getString(ll_id_correoElectonico));
							lsi_interviniente.setIdCorreoElectronicoComunicacion(
							    StringUtils.getString(ll_id_correoElectonico)
							);
							lsi_interviniente.setIdDependencia(lu_usuario.getIdDependencia());

							if(li_contadorVisitadorPrincipal <= 1)
								lsi_interviniente.setVisitadorPrincipal(lb_visitadorPrincipal ? "S" : "N");
							else
							{
								li_contadorVisitadorPrincipal = 0;
								throw new B2BException("Solo puede haber un visitador principal por solicitud");
							}

							lsi_interviniente.setIdUsuarioCreacion(as_userId);
							lsi_interviniente.setIpCreacion(as_remoteIp);

							if(as_nirComp == null)
								lsid_DAO.insertOrUpdate(lsi_interviniente, true);
							else
								lsid_DAO.insertOrUpdate(lsi_interviniente, false);
						}
					}

					if(!lb_usuarioSeleccionado)
						throw new B2BException("Debe seleccionar al menos un usuario");

					if(!lb_visitadorPrincipal && (li_contadorVisitadorPrincipal != 1))
					{
						li_contadorVisitadorPrincipal = 0;
						throw new B2BException("Debe seleccionar al menos un visitador principal");
					}

					if(as_nirComp == null)
					{
						ls_nir = crearNir(as_userId, as_remoteIp, ldm_manager);

						if(StringUtils.isValidString(ls_nir))
						{
							SolicitudDAO lsd_DAO;
							Solicitud    ls_solicitud;

							lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
							ls_solicitud     = lsd_DAO.findById(as_idSolicitud);

							if(ls_solicitud != null)
							{
								ls_solicitud.setNir(ls_nir);

								lsd_DAO.insertOrUpdate(ls_solicitud, false);
							}
						}
					}
					else
					{
						ls_nir = as_nirComp;

						if(StringUtils.isValidString(ls_nir))
						{
							SolicitudDAO lsd_DAO;
							Solicitud    ls_solicitud;

							lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
							ls_solicitud     = lsd_DAO.findById(as_idSolicitud);

							if(ls_solicitud != null)
							{
								ls_solicitud.setNir(ls_nir);

								lsd_DAO.insertOrUpdate(ls_solicitud, false);
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("salvarInformacionVisitas", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return ls_nir;
	}

	/**
	 * Salvar usuarios visitadores reasignados.
	 *
	 * @param acu_usuarios correspondiente al valor de acu usuarios
	 * @param as_idSolicitud correspondiente al valor de as id solicitud
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarUsuariosVisitadoresReasignados(
	    Collection<Usuario> acu_usuarios, String as_idSolicitud, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(CollectionUtils.isValidCollection(acu_usuarios))
			{
				PersonaDAO                         lpd_DAO;
				SolicitudIntervinienteDAO          lsid_DAO;
				Collection<SolicitudInterviniente> lcsi_solicitudInterviniente;
				Usuario                            lu_usuario;
				Usuario                            lu_usuarioReasignacion;
				Persona                            lp_persona;
				boolean                            lb_visitadorPrincipal;
				boolean                            lb_usuarioSeleccionado;
				int                                li_contadorVisitadorPrincipal;
				int                                li_contadorUsuarios;

				lpd_DAO                           = DaoCreator.getPersonaDAO(ldm_manager);
				lsid_DAO                          = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
				lcsi_solicitudInterviniente       = lsid_DAO.findByIdSolicitud(as_idSolicitud);
				lu_usuario                        = new Usuario();
				lu_usuarioReasignacion            = new Usuario();
				lp_persona                        = new Persona();
				li_contadorVisitadorPrincipal     = 0;
				li_contadorUsuarios               = 0;
				lb_visitadorPrincipal             = false;
				lb_usuarioSeleccionado            = false;

				if(
				    CollectionUtils.isValidCollection(lcsi_solicitudInterviniente)
					    && CollectionUtils.isValidCollection(acu_usuarios)
				)
				{
					for(SolicitudInterviniente isi_iteradorSI : lcsi_solicitudInterviniente)
						isi_iteradorSI.setVisitadorPrincipal(IdentificadoresCommon.N);

					for(Usuario iu_iteradorUsuario : acu_usuarios)
					{
						if(
						    iu_iteradorUsuario.isSeleccionado()
							    && StringUtils.isValidString(iu_iteradorUsuario.getIdUsuario())
						)
						{
							lp_persona = lpd_DAO.findMaxByNumTipoDoc(
								    iu_iteradorUsuario.getNumeroDocumento(), iu_iteradorUsuario.getIdDocumentoTipo()
								);

							for(SolicitudInterviniente isi_iteradorSI : lcsi_solicitudInterviniente)
							{
								if(isi_iteradorSI.getIdPersona().equals(lp_persona.getIdPersona()))
								{
									isi_iteradorSI.setIdPersona(lp_persona.getIdPersona());
									isi_iteradorSI.setReasignado(IdentificadoresCommon.S);
									isi_iteradorSI.setIdUsuarioReasignado(
									    iu_iteradorUsuario.getIdUsuarioReasignacion()
									);

									if(iu_iteradorUsuario.isVisitadorPrincipal())
									{
										isi_iteradorSI.setVisitadorPrincipal(IdentificadoresCommon.S);
										li_contadorVisitadorPrincipal++;
									}

									lsid_DAO.insertOrUpdate(isi_iteradorSI, false);
									li_contadorUsuarios++;
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

			clh_LOGGER.error("salvarUsuariosVisitadoresReasignados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Actualizar documento salida.
	 *
	 * @param aba_documento de aba documento
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param lb_auto de lb auto
	 * @param as_idSolicitud de as id solicitud
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void actualizarDocumentoSalida(
	    byte[] aba_documento, String as_userId, String as_remoteIp, boolean lb_auto, String as_idSolicitud,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		if(aba_documento != null)
		{
			try
			{
				DocumentosSalida lds_documento;

				lds_documento = DaoCreator.getDocumentosSalidaDAO(adm_manager)
						                      .findLastByIdSolicitudTipo(
						    as_idSolicitud, (lb_auto ? TipoDocumentalCommon.AUTO : TipoDocumentalCommon.RESOLUCION),
						    true
						);

				if(lds_documento != null)
				{
					Long ll_idImagen;

					ll_idImagen = lds_documento.getIdImagen();

					if(NumericUtils.isValidLong(ll_idImagen))
					{
						ImagenesDAO lid_DAO;
						Imagenes    li_imagen;

						lid_DAO       = DaoCreator.getImagenesDAO(adm_manager);
						li_imagen     = lid_DAO.findById(ll_idImagen.longValue());

						if(li_imagen != null)
						{
							li_imagen.setImagenBlob(aba_documento);
							li_imagen.setIdUsuarioModificacion(as_userId);
							li_imagen.setIpModificacion(as_remoteIp);

							lid_DAO.insertOrUpdate(li_imagen, false);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_IMAGEN);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("guardarDocumentoSalida", lb2be_e);

				throw lb2be_e;
			}
		}
	}

	/**
	 * Aprobacion ejecucion visitas.
	 *
	 * @param acth_parametros de acth parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void aprobacionEjecucionVisitas(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_APROBACION_EJECUCION_DELEGADO_DE_REGISTRO_BLOQUEO;
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

				clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

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
				Constantes    lc_constant;

				lcd_constant     = DaoCreator.getConstantesDAO(ldm_processing);
				lc_constant      = lcd_constant.findById(ls_constant);

				if(lc_constant != null)
				{
					lb_alreadyProcessing = BooleanUtils.getBooleanValue(lc_constant.getCaracter());

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

				clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

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
						BitacoraProcesoDAO lbp_DAO;

						lbp_DAO = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									aprobacionEjecucionVisitas(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

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

					clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

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

					clh_LOGGER.error("aprobacionEjecucionVisitas", lb2be_e);

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
	 * Aprobacion ejecucion visitas.
	 *
	 * @param abpd_bitacoraProcesoDAO de abpd bitacora proceso DAO
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_remoteIp de as remote ip
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void aprobacionEjecucionVisitas(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_bitacora;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		ldm_bitacora     = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				TurnoHistoriaDAO lthd_DAO;
				Long             ll_idTurnoHistoria;

				lthd_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ll_idTurnoHistoria     = ath_turnoHistoria.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					TurnoHistoria lth_turnoHistoria;

					lth_turnoHistoria = lthd_DAO.findById(ll_idTurnoHistoria);

					if(lth_turnoHistoria != null)
					{
						String ls_idSolicitud;

						ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

						if(StringUtils.isValidString(ls_idSolicitud))
						{
							Solicitud ls_solicitud;

							ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

							if(ls_solicitud != null)
							{
								DocumentosSalida lds_documentoSalida;

								lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
										                            .findByIdTurnoHistoriaTipoDocumental(
										    NumericUtils.getIntegerWrapper(lth_turnoHistoria.getIdAnterior()),
										    TipoDocumentalCommon.AUTO
										);

								if(lds_documentoSalida == null)
									lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                            .findByIdTurnoHistoriaTipoDocumental(
											    NumericUtils.getIntegerWrapper(lth_turnoHistoria.getIdAnterior()),
											    TipoDocumentalCommon.INFORMES
											);

								if(lds_documentoSalida == null)
									lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                            .findByIdTurnoHistoriaTipoDocumental(
											    NumericUtils.getIntegerWrapper(lth_turnoHistoria.getIdAnterior()),
											    TipoDocumentalCommon.RESOLUCION
											);

								if(lds_documentoSalida != null)
								{
									BitacoraProcesoDAO lbpd_bitacora;

									lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

									ldm_bitacora.setAutoCommit(true);

									if((lds_documentoSalida != null) && !lds_documentoSalida.isEnviadoOwcc())
									{
										String ls_endpoint;

										ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
												                    .findString(
												    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
												);

										if(StringUtils.isValidString(ls_endpoint))
										{
											enviarGestorDocumental(
											    lds_documentoSalida, lbpd_bitacora, ls_endpoint, as_userId, as_remoteIp,
											    ldm_manager
											);

											if(!lds_documentoSalida.isEnviadoOwcc())
												throw new B2BException(
												    "Ocurrió un error enviando el documento al OWCC, intentelo nuevamente"
												);
										}
									}

									{
										String ls_TipoDocumental;

										ls_TipoDocumental = lds_documentoSalida.getTipo();

										if(StringUtils.isValidString(ls_TipoDocumental))
										{
											TurnoHistoria lth_historiaAnt;

											lth_historiaAnt = lthd_DAO.findByIdAnterior(lth_turnoHistoria);

											if(lth_historiaAnt != null)
											{
												Map<String, Boolean> lmsb_permisos;

												lmsb_permisos = cargarDatosRolesUsuarios(
													    ldm_manager, lth_historiaAnt.getIdUsuario()
													);

												if(CollectionUtils.isValidCollection(lmsb_permisos))
												{
													boolean lb_liderVigilanciaControl;
													boolean lb_responsableDeSeguimiento;
													boolean lb_delegadoRegistro;
													boolean lb_visitador;

													lb_liderVigilanciaControl       = BooleanUtils.getBooleanValue(
														    lmsb_permisos.get(
														        RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL_TXT
														    )
														);
													lb_delegadoRegistro             = BooleanUtils.getBooleanValue(
														    lmsb_permisos.get(RolCommon.CB_ROL_DELEGADO_REGISTRO_TXT)
														);
													lb_visitador                    = BooleanUtils.getBooleanValue(
														    lmsb_permisos.get(RolCommon.CB_ROL_VISITADOR_TXT)
														);
													lb_responsableDeSeguimiento     = BooleanUtils.getBooleanValue(
														    lmsb_permisos.get(
														        RolCommon.CB_ROL_RESPONSABLE_SEGUIMIENTO_VISITAS
														    )
														);

													if(lb_delegadoRegistro)
													{
														Collection<DocumentosSalida> lcds_documentoFirma;

														lcds_documentoFirma = new ArrayList<DocumentosSalida>(1);

														lcds_documentoFirma.add(lds_documentoSalida);

														getFirmaMasivaBusiness()
															    .firmarDocumentos(
															    lcds_documentoFirma, lth_turnoHistoria, as_userId,
															    as_remoteIp, ldm_manager
															);
													}

													switch(ls_TipoDocumental)
													{
														case TipoArchivoCommon.AUTO_SUSPENSION_VISITA:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_LIDER_SUSPENSION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_SUSPENSION_TRAMITE_VISITAS
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_REASIGNACION_TURNO:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_REASIGNACION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_VISITADOR_REASIGNACION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_PRORROGA_VISITA:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_LIDER_PRORROGA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_PRORROGA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_visitador)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_VISITADOR_PRORROGA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_REANUDACION_VISITA:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_LIDER_REANUDACION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_VISITADOR_REANUDACION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_REVOCATORIA_VISITA:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_REVOCATORIA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.FINALIZADO_POR_REVOCATORIA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_ANULACION_TURNO_VISITA:

															if(lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_ANULACION
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);
															else if(lb_delegadoRegistro)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.FINALIZADO_POR_ANULACION_TURNO
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.RESOLUCION_PRORROGA_INTERVENCION:

															if(
															    !ath_turnoHistoria.getIdSubproceso()
																                      .equals(TramiteCommon.AUTO)
																    || !ath_turnoHistoria.getIdSubproceso()
																                             .equals(
																        TramiteCommon.RESOLUCIONES
																    )
															)
															{
																if(lb_visitador)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
																else if(lb_liderVigilanciaControl)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_LIDER
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
																else if(lb_delegadoRegistro)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_DELEGADO
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
															}
															else
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_REVOCATORIA
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

														case TipoArchivoCommon.AUTO_CIERRE_TURNO_VISITA:

															if(lb_visitador || lb_liderVigilanciaControl)
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_CIERRE
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															if(lb_delegadoRegistro)    // 
																terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.ENVIO_APROBACION_VISITADOR_CIERRE
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															break;

//														case TipoArchivoCommon.AUTO_MODIFICACION_VISITA:
//															terminarTurnoHistoriaYCrearEtapa(
//															    ath_turnoHistoria, ldm_manager,
//															    new MotivoTramite(
//															        EtapaCommon.ID_ETAPA_835,
//															        MotivoTramiteCommon.ENVIO_APROBACION_DELEGADO_SUSPENSION
//															    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
//															);
//
//															break;
														case TipoArchivoCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION:

															if(
															    !ath_turnoHistoria.getIdSubproceso()
																                      .equals(TramiteCommon.AUTO)
																    || !ath_turnoHistoria.getIdSubproceso()
																                             .equals(
																        TramiteCommon.RESOLUCIONES
																    )
															)
															{
																if(lb_visitador)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
																else if(lb_liderVigilanciaControl)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_LIDER
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
																else if(lb_delegadoRegistro)
																	terminarTurnoHistoriaYCrearEtapa(
																	    ath_turnoHistoria, ldm_manager,
																	    new MotivoTramite(
																	        EtapaCommon.ID_ETAPA_835,
																	        MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_DELEGADO
																	    ), as_userId, as_remoteIp,
																	    EstadoCommon.TERMINADA
																	);
															}

															break;

														case TipoArchivoCommon.INFORME_VISITA_FINAL:
															terminarTurnoHistoriaYCrearEtapa(
															    ath_turnoHistoria, ldm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_835,
															        MotivoTramiteCommon.INFORME_FINAL
															    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
															);

															break;

														case TipoArchivoCommon.INFORME_VISITA_SEMANAL:
															terminarTurnoHistoriaYCrearEtapa(
															    ath_turnoHistoria, ldm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_835,
															        MotivoTramiteCommon.INFORME_SEMANAL
															    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
															);

															break;

														case TipoArchivoCommon.INFORME_INTERVENCION_OBS_HALLAZGOS:
															terminarTurnoHistoriaYCrearEtapa(
															    ath_turnoHistoria, ldm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_835,
															        MotivoTramiteCommon.INFORME_INTERVENCION
															    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
															);

															break;

														case TipoArchivoCommon.AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES:
															terminarTurnoHistoriaYCrearEtapa(
															    ath_turnoHistoria, ldm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_835,
															        MotivoTramiteCommon.AVISO_CONVOCATORIA_DE_AUDIENCIA_PUBLICA_DE_RECLAMACIONES
															    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
															);

															break;

														case TipoArchivoCommon.OFICIO_REQUERIMIENTOS:

															TurnoHistoria lthp_turnoHistoria;
															lthp_turnoHistoria = terminarTurnoHistoriaYCrearEtapa(
																    ath_turnoHistoria, ldm_manager,
																    new MotivoTramite(
																        EtapaCommon.ID_ETAPA_835,
																        MotivoTramiteCommon.OFICIO_REQUERIMIENTOS
																    ), as_userId, as_remoteIp, EstadoCommon.TERMINADA
																);

															if(lthp_turnoHistoria != null)
																insertarFirmaMasiva(
																    ldm_manager, as_userId,
																    StringUtils.getString(
																        lthp_turnoHistoria.getIdTurnoHistoria()
																    )
																);

															break;

														default:
															break;
													}
												}
												else
													throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
											}
											else
												throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_INVALIDO);
										}
										else
											throw new B2BException(ErrorKeys.ERROR_TIPO_DOCUMENTAL_VALIDO);
									}
								}
								else
									throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA);
							}
							else
							{
								Object[] loa_args;

								loa_args        = new String[1];
								loa_args[0]     = ls_idSolicitud;

								throw new B2BException(ErrorKeys.ERROR_NO_EXISTE_SOLICITUD_ACC, loa_args);
							}
						}
						else
							throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
			}
			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_bitacora.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.APROBACION_EJECUCION_DELEGADO_DE_REGISTRO,
			    lb2be_e.getMessage(), as_userId, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
			ldm_manager.close();
		}
	}

	/**
	 * Cargar datos panel solicitud visitas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<SolicitudVisitas> cargarDatosPanelSolicitudVisitas(
	    String as_idSolicitud, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<SolicitudVisitas> lcsv_solicitudes;

		lcsv_solicitudes = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_userId) && (adm_manager != null))
		{
			try
			{
				lcsv_solicitudes = DaoCreator.getSolicitudVisitasDAO(adm_manager)
						                         .findDatosPanelSolicitudVisitas(as_idSolicitud);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDatosPanelSolicitudVisitas", lb2be_e);

				throw lb2be_e;
			}
		}

		return lcsv_solicitudes;
	}

	/**
	 * Cargar datos panel tramites A realizar.
	 *
	 * @param adm_manager de adm manager
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Collection<TramiteVisita> cargarDatosPanelTramitesARealizar(DAOManager adm_manager)
	    throws B2BException
	{
		Collection<TramiteVisita> lcs_tramites;

		lcs_tramites = null;

		if(adm_manager != null)
		{
			try
			{
				lcs_tramites = DaoCreator.getTramiteVisitaDAO(adm_manager).findAllActivo();
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDatosPanelTramitesARealizar", lb2be_e);

				throw lb2be_e;
			}
		}

		return lcs_tramites;
	}

	/**
	 * Consultar constante.
	 *
	 * @param as_idTramite de as id tramite
	 * @param as_idTramiteTipo de as id tramite tipo
	 * @param adm_manager de adm manager
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Constantes consultarConstante(
	    String as_idTramite, String as_idTramiteTipo, DAOManager adm_manager
	)
	    throws B2BException
	{
		Constantes lc_constante;

		lc_constante = null;

		try
		{
			if(StringUtils.isValidString(as_idTramite) && StringUtils.isValidString(as_idTramiteTipo))
			{
				DatosPlantillaDocumento ldpd_datosPlantillaDocumento;

				ldpd_datosPlantillaDocumento = DaoCreator.getDatosPlantillaDocumentoDAO(adm_manager)
						                                     .findByIdTramiteIdTipoTramiteActivo(
						    as_idTramite, as_idTramiteTipo
						);

				if(ldpd_datosPlantillaDocumento != null)
				{
					String ls_idConstante;

					ls_idConstante = ldpd_datosPlantillaDocumento.getIdConstante();

					if(StringUtils.isValidString(ls_idConstante))
					{
						lc_constante = DaoCreator.getConstantesDAO(adm_manager).findByIdWithoutImage(ls_idConstante);

						if(lc_constante == null)
						{
							String[] loa_args;

							loa_args        = new String[1];
							loa_args[0]     = ls_idConstante;

							throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDetalleEjecucionVisitas", lb2be_e);

			throw lb2be_e;
		}

		return lc_constante;
	}

	/**
	 * Generar documento ejecucion visitas.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_textoCampos de aot texto campos
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized byte[] generarDocumentoEjecucionVisitas(
	    String as_idConstante, String as_idSolicitudVisitas, OficiosTexto aot_textoCampos, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		byte[] lba_documento;

		lba_documento = null;

		if(
		    StringUtils.isValidString(as_idConstante) && StringUtils.isValidString(as_idSolicitudVisitas)
			    && validarDatosAuditoria(as_userId, as_remoteIp)
		)
		{
			try
			{
				switch(as_idConstante)
				{
					case ConstanteCommon.PLANTILLA_AUTO_ANULACION_VISITA:
						lba_documento = generarAutoAnulacionVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_CIERRE_VISITA:
						lba_documento = generarAutoCierreVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_MODIFICACION_VISITA:
						lba_documento = generarAutoModificacionVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL:
						lba_documento = generarAutoOrdenaVisitaGeneral(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_PRORROGA_VISITA:
						lba_documento = generarAutoProrrogaVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_REANUDACION_VISITA:
						lba_documento = generarAutoReanudacionVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_REASIGNACION_TURNO:
						lba_documento = generarAutoReasignacionTurno(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_REVOCATORIA_VISITA:
						lba_documento = generarAutoRevocatoriaVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AUTO_SUSPENSION_VISITA:
						lba_documento = generarAutoSuspensionVisita(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION:
						lba_documento = generarResolucionOrdenaIntervencion(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

					case ConstanteCommon.PLANTILLA_RESOLUCION_LEVANTAMIENTO_INTERVENCION:
						lba_documento = generarResolucionLevantamientoIntervencion(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_RESOLUCION_PRORROGA_INTERVENCION:
						lba_documento = generarResolucionProrrogaIntervencion(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_INFORME_VISITA_SEMANAL:
						lba_documento = generarInformeVisitaSemanal(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL:
						lba_documento = generarInformeVisitaFinal(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES:
						lba_documento = generarInformeAvisoConvocatoriaAudienciaPublicaReclamaciones(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_INFORME_INTERVENCION_OBS_HALLAZGOS:
						lba_documento = generarInformeIntervencionObsHallazgos(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					case ConstanteCommon.PLANTILLA_OFICIO_REQUERIMIENTOS:
						lba_documento = generarOficioRequerimientos(
							    as_idSolicitudVisitas, aot_textoCampos, as_userId, as_remoteIp
							);

						break;

					default:
						break;
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarDetalleEjecucionVisitas", lb2be_e);

				throw lb2be_e;
			}
		}

		return lba_documento;
	}

	/**
	 * Insertar actualizar oficio texto.
	 *
	 * @param adm_manager de adm manager
	 * @param as_idSolicitud de as id solicitud
	 * @param aot_oficioTexto de aot oficio texto
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void insertarActualizarOficioTexto(
	    DAOManager adm_manager, String as_idSolicitud, OficiosTexto aot_oficioTexto, TurnoHistoria ath_historia,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			OficiosTextoDAO lotd_DAO;
			OficiosTexto    lot_oficio;

			lotd_DAO       = DaoCreator.getOficiosTextoDAO(adm_manager);
			lot_oficio     = lotd_DAO.findByIdSolicitud(as_idSolicitud);

			if(lot_oficio != null)
			{
				lot_oficio.setConsideracion(aot_oficioTexto.getConsideracion());
				lot_oficio.setRazon1(aot_oficioTexto.getRazon1());
				lot_oficio.setResuelve(aot_oficioTexto.getResuelve());
				lot_oficio.setIdUsuarioModificacion(as_userId);
				lot_oficio.setIpModificacion(as_remoteIp);

				if(ath_historia != null)
					lot_oficio.setIdTurnoHistoria(ath_historia.getIdTurnoHistoria());

				lotd_DAO.insertOrUpdate(lot_oficio, false);
			}
			else
			{
				lot_oficio = new OficiosTexto();

				lot_oficio.setIdSolicitud(as_idSolicitud);
				lot_oficio.setConsideracion(aot_oficioTexto.getConsideracion());
				lot_oficio.setRazon1(aot_oficioTexto.getRazon1());
				lot_oficio.setResuelve(aot_oficioTexto.getResuelve());
				lot_oficio.setIdUsuarioCreacion(as_userId);
				lot_oficio.setIpCreacion(as_remoteIp);

				if(ath_historia != null)
					lot_oficio.setIdTurnoHistoria(ath_historia.getIdTurnoHistoria());

				lotd_DAO.insertOrUpdate(lot_oficio, true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("insertarActualizarOficioTexto", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Llenar detalle ejecucion visitas.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitudVisitas de as id solicitud visitas
	 * @param aot_textoTags de aot texto tags
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @return el valor de detalle ejecucion visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized DetalleEjecucionVisitas llenarDetalleEjecucionVisitas(
	    String as_idConstante, String as_idSolicitudVisitas, OficiosTexto aot_textoTags, String as_userId,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		DetalleEjecucionVisitas ldev_informacionDetalleEjecucion;

		ldev_informacionDetalleEjecucion = null;

		if(
		    StringUtils.isValidString(as_idConstante) && StringUtils.isValidString(as_idSolicitudVisitas)
			    && validarDatosAuditoria(as_userId, as_remoteIp)
		)
		{
			try
			{
				byte[] lba_documento;

				lba_documento                        = null;
				ldev_informacionDetalleEjecucion     = new DetalleEjecucionVisitas();

				if(aot_textoTags == null)
				{
					Collection<TagPlantillaResolucion> lctpr_tagsEnPantalla;

					lctpr_tagsEnPantalla = DaoCreator.getTagPlantillaResolucionDAO(adm_manager)
							                             .findByIdPlantilla(as_idConstante);

					if(CollectionUtils.isValidCollection(lctpr_tagsEnPantalla))
						ldev_informacionDetalleEjecucion.setCamposPantalla(lctpr_tagsEnPantalla);
					else
						throw new B2BException("Falta parametrización de tags para la plantilla: " + as_idConstante);

					ldev_informacionDetalleEjecucion.setIdConstante(as_idConstante);
					ldev_informacionDetalleEjecucion.setIdSolicitudVisitas(as_idSolicitudVisitas);
				}

				lba_documento = generarDocumentoEjecucionVisitas(
					    as_idConstante, as_idSolicitudVisitas, aot_textoTags, as_userId, as_remoteIp
					);

				if(ByteArrayUtils.isValidArray(lba_documento))
					ldev_informacionDetalleEjecucion.setDocumento(lba_documento);
				else
					throw new B2BException("Falta parametrización de documento para la plantilla: " + as_idConstante);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("llenarDetalleEjecucionVisitas", lb2be_e);

				throw lb2be_e;
			}
		}

		return ldev_informacionDetalleEjecucion;
	}

	/**
	 * Llenar documento salida.
	 *
	 * @param as_idConstante de as id constante
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idSubProceso de as id sub proceso
	 * @param ads_documento de ads documento
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 */
	private synchronized void llenarDocumentoSalida(
	    String as_idConstante, String as_idSolicitud, String as_idSubProceso, DocumentosSalida ads_documento,
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_remoteIp
	)
	{
		if(ads_documento != null)
		{
			if(ath_turnoHistoria != null)
			{
				ads_documento.setIdTurno(ath_turnoHistoria.getIdTurno());
				ads_documento.setIdTurnoHistoria(NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria()));
			}

			switch(as_idSubProceso)
			{
				case ProcesoCommon.ID_SUBPROCESO_1:
				case ProcesoCommon.ID_SUBPROCESO_2:
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				default:
					break;
			}

			switch(as_idConstante)
			{
				case ConstanteCommon.PLANTILLA_AUTO_ANULACION_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_ANULACION_TURNO_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_CIERRE_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_CIERRE_TURNO_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_MODIFICACION_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_MODIFICACION_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_ORDENA_VISITA_GENERAL:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_ORDENA_VISITA_GENERAL);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_PRORROGA_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_PRORROGA_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_REANUDACION_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_REANUDACION_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_REASIGNACION_TURNO:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_REASIGNACION_TURNO);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_REVOCATORIA_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_REVOCATORIA_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_AUTO_SUSPENSION_VISITA:
					ads_documento.setTipo(TipoArchivoCommon.AUTO_SUSPENSION_VISITA);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.AUTO);

					break;

				case ConstanteCommon.PLANTILLA_RESOLUCION_ORDENA_INTERVENCION:
					ads_documento.setTipo(TipoArchivoCommon.RESOLUCION_ORDENA_INTERVENCION);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);

					break;

				case ConstanteCommon.PLANTILLA_RESOLUCION_PRORROGA_INTERVENCION:
					ads_documento.setTipo(TipoArchivoCommon.RESOLUCION_PRORROGA_INTERVENCION);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);

					break;

				case ConstanteCommon.PLANTILLA_RESOLUCION_LEVANTAMIENTO_INTERVENCION:
					ads_documento.setTipo(TipoArchivoCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);

					break;

				case ConstanteCommon.PLANTILLA_INFORME_VISITA_SEMANAL:
					ads_documento.setTipo(TipoArchivoCommon.INFORME_VISITA_SEMANAL);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);

					break;

				case ConstanteCommon.PLANTILLA_INFORME_VISITA_FINAL:
					ads_documento.setTipo(TipoArchivoCommon.INFORME_VISITA_FINAL);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);

					break;

				case ConstanteCommon.PLANTILLA_INFORME_INTERVENCION_OBS_HALLAZGOS:
					ads_documento.setTipo(TipoArchivoCommon.INFORME_INTERVENCION_OBS_HALLAZGOS);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);

					break;

				case ConstanteCommon.PLANTILLA_AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES:
					ads_documento.setTipo(TipoArchivoCommon.AVISO_CONVOCATORIA_AUDIENCIA_PUBLICA_RECLAMACIONES);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.INFORMES);

					break;

				case ConstanteCommon.PLANTILLA_OFICIO_REQUERIMIENTOS:
					ads_documento.setTipo(TipoArchivoCommon.OFICIO_REQUERIMIENTOS);
					ads_documento.setIdTipoDocumental(TipoDocumentalCommon.RESOLUCION);

					break;

				default:
					break;
			}

			ads_documento.setIdSolicitud(as_idSolicitud);
			ads_documento.setIdNombreDocumento(IdentificadoresCommon.X);
			ads_documento.setIdEcm(IdentificadoresCommon.X);
		}
	}

	/**
	 * Resolver tag firma.
	 *
	 * @param as_plantilla correspondiente al valor de as plantilla
	 * @return el valor de string
	 * @throws B2BException
	 */
	private String resolverTagFirma(String as_plantilla)
	    throws B2BException
	{
		String        ls_tag      = TagCommon.TAG_FIRMA_USUARIO_VISITADOR;
		DAOManager    ldm_manager;
		ConstantesDAO lc_DAO;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		lc_DAO                    = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			if(as_plantilla.contains(ls_tag))
				as_plantilla = putCustomBookMark(
					    as_plantilla, ls_tag, "FIRMA_USUARIO_VISITADOR",
					    lc_DAO.findById(ConstanteCommon.USUARIO_FIRMA_SUSPENSION), true
					);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("resolverTagNombreOrip", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_plantilla;
	}

	/**
	 * Resolver tag nombre orip.
	 *
	 * @param as_plantilla de as plantilla
	 * @param as_idCirculo de as id circulo
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagNombreOrip(String as_plantilla, String as_idCirculo, DAOManager adm_manager)
	    throws B2BException
	{
		if(as_plantilla != null)
		{
			try
			{
				if(as_plantilla.contains(TagCommon.TAG_NOMBRE_ORIP) && StringUtils.isValidString(as_idCirculo))
				{
					CirculoRegistral lcr_circuloRegistral;

					lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(as_idCirculo);

					if(lcr_circuloRegistral != null)
					{
						String ls_nombre;

						ls_nombre = lcr_circuloRegistral.getNombre();

						if(StringUtils.isValidString(ls_nombre))
							as_plantilla = as_plantilla.replace(TagCommon.TAG_NOMBRE_ORIP, ls_nombre);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("resolverTagNombreOrip", lb2be_e);
				throw lb2be_e;
			}
		}

		return as_plantilla;
	}

	/**
	     * Resolver tags basicos.
	     *
	     * @param as_plantilla correspondiente al valor de as plantilla
	     * @param asv_sv de asv sv
	     * @param as_userId de as user id
	     * @return el valor de string
	     * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	     */
	private String resolverTagsBasicos(String as_plantilla, SolicitudVisitas asv_sv, String as_userId)
	    throws B2BException
	{
		String ls_tag;

		if(StringUtils.isValidString(as_plantilla))
		{
			as_plantilla     = resolverTagsResolucionCB(as_plantilla);
			as_plantilla     = resolverTagsResolucionInicial(as_plantilla);
			as_plantilla     = resolverTagFirma(as_plantilla);

			ls_tag = TagCommon.TAG_USUARIO;

			if(as_plantilla.contains(ls_tag))
				as_plantilla = reemplazarTagEnPlantilla(as_plantilla, TagCommon.TAG_USUARIO, as_userId);
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags fecha desde hasta.
	 *
	 * @param as_plantilla de as plantilla
	 * @param asv_solicitudVisitas de asv solicitud visitas
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagsFechaDesdeHasta(String as_plantilla, SolicitudVisitas asv_solicitudVisitas)
	    throws B2BException
	{
		if(as_plantilla != null)
		{
			if(as_plantilla.contains(TagCommon.TAG_FECHA_DESDE))
			{
				Date ld_fechaDesde;

				ld_fechaDesde = asv_solicitudVisitas.getFechaDesde();

				if(ld_fechaDesde != null)
					as_plantilla = as_plantilla.replace(
						    TagCommon.TAG_FECHA_DESDE,
						    StringUtils.getString(ld_fechaDesde, FormatoFechaCommon.DIA_MES_ANIO)
						);
			}

			if(as_plantilla.contains(TagCommon.TAG_FECHA_HASTA))
			{
				Date ld_fechaHasta;

				ld_fechaHasta = asv_solicitudVisitas.getFechaHasta();

				if(ld_fechaHasta != null)
					as_plantilla = as_plantilla.replace(
						    TagCommon.TAG_FECHA_HASTA,
						    StringUtils.getString(ld_fechaHasta, FormatoFechaCommon.DIA_MES_ANIO)
						);
			}
		}

		return as_plantilla;
	}

	private String resolverTagsInformes(String as_plantilla, OficiosTexto aot_texto)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_plantilla) && (aot_texto != null))
		{
			if(as_plantilla.contains(TagCommon.TAG_CONSIDERACIONES))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_CONSIDERACIONES, StringUtils.getStringNotNull(aot_texto.getConsideracion())
					);

			if(as_plantilla.contains(TagCommon.TAG_PERTINENCIA))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_PERTINENCIA, StringUtils.getStringNotNull(aot_texto.getPertinencia())
					);

			if(as_plantilla.contains(TagCommon.TAG_ANTECEDENTES))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_ANTECEDENTES, StringUtils.getStringNotNull(aot_texto.getAntecedentes())
					);

			if(as_plantilla.contains(TagCommon.TAG_RAZON1))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_RAZON1, StringUtils.getStringNotNull(aot_texto.getRazon1())
					);

			if(as_plantilla.contains(TagCommon.TAG_RAZON2))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_RAZON2, StringUtils.getStringNotNull(aot_texto.getRazon2())
					);

			if(as_plantilla.contains(TagCommon.TAG_ANALISIS_PROBATORIO))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_ANALISIS_PROBATORIO,
					    StringUtils.getStringNotNull(aot_texto.getAnalisisProbatorio())
					);

			if(as_plantilla.contains(TagCommon.TAG_CONSIDERACIONES_RESOLUCION))
				as_plantilla = as_plantilla.replace(
					    TagCommon.TAG_CONSIDERACIONES_RESOLUCION,
					    StringUtils.getStringNotNull(aot_texto.getConsideracion())
					);
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags panel visitas.
	 *
	 * @param as_plantilla de as plantilla
	 * @param as_idSolicitud de as id solicitud
	 * @param as_userId de as user id
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagsPanelVisitas(
	    String as_plantilla, String as_idSolicitud, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_plantilla) && StringUtils.isValidString(as_idSolicitud)
			    && StringUtils.isValidString(as_userId)
		)
		{
			try
			{
				Collection<SolicitudVisitas> lcsv_solicitudes;
				lcsv_solicitudes = cargarDatosPanelSolicitudVisitas(as_idSolicitud, as_userId, adm_manager);

				if(CollectionUtils.isValidCollection(lcsv_solicitudes))
				{
					for(SolicitudVisitas lsv_solicitudVisitas : lcsv_solicitudes)
					{
						if(as_plantilla.contains(TagCommon.TAG_ORIP))
							as_plantilla = as_plantilla.replace(
								    TagCommon.TAG_ORIP,
								    StringUtils.getStringNotNull(
								        lsv_solicitudVisitas.getIdCirculo() + "-"
								        + lsv_solicitudVisitas.getNombreCirculo()
								    )
								);

						if(as_plantilla.contains(TagCommon.TAG_TIPO_VISITA))
							as_plantilla = as_plantilla.replace(
								    TagCommon.TAG_TIPO_VISITA,
								    StringUtils.getStringNotNull(lsv_solicitudVisitas.getNombreSubProceso())
								);

						if(as_plantilla.contains(TagCommon.TAG_DEPENDENCIA))
							as_plantilla = as_plantilla.replace(
								    TagCommon.TAG_DEPENDENCIA,
								    StringUtils.getStringNotNull(lsv_solicitudVisitas.getNombreDependencia())
								);

						if(as_plantilla.contains(TagCommon.TAG_VISITADOR_PRINCIPAL))
							as_plantilla = as_plantilla.replace(
								    TagCommon.TAG_VISITADOR_PRINCIPAL,
								    StringUtils.getStringNotNull(lsv_solicitudVisitas.getNombreUsuario())
								);

						if(
						    as_plantilla.contains(TagCommon.TAG_FECHA_DESDE)
							    || as_plantilla.contains(TagCommon.TAG_FECHA_HASTA)
						)
							as_plantilla = resolverTagsFechaDesdeHasta(as_plantilla, lsv_solicitudVisitas);

						if(as_plantilla.contains(TagCommon.TAG_CIUDAD_ORIP))
						{
							String ls_idCirculo;
							ls_idCirculo = lsv_solicitudVisitas.getIdCirculo();

							if(StringUtils.isValidString(ls_idCirculo))
							{
								CirculoRegistral lcr_circuloRegistral;

								lcr_circuloRegistral = DaoCreator.getCirculoRegistralDAO(adm_manager)
										                             .findById(ls_idCirculo);

								if(lcr_circuloRegistral != null)
								{
									String ls_nombre;

									ls_nombre = lcr_circuloRegistral.getNombre();

									if(StringUtils.isValidString(ls_nombre))
										as_plantilla = as_plantilla.replace(TagCommon.TAG_CIUDAD_ORIP, ls_nombre);
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("resolverTipoVisita", lb2be_e);
				throw lb2be_e;
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver tags resolucion.
	 *
	 * @param as_plantilla de as plantilla
	 * @return el valor de string
	 */
	private String resolverTagsResolucion(String as_plantilla)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			if(as_plantilla.contains(TagCommon.TAG_RESOLUCION))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(TagCommon.TAG_RESOLUCION);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(li_finalTag + TagCommon.TAG_RESOLUCION.length());

					ls_tagNew     = BookMarkCommon.RESOLUCION_BKM + TagCommon.TAG_RESOLUCION;

					as_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
						+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
				}
			}

			if(as_plantilla.contains(TagCommon.TAG_FECHA_RESOL))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(TagCommon.TAG_FECHA_RESOL);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(
						    li_finalTag + TagCommon.TAG_FECHA_RESOL.length()
						);

					ls_tagNew     = BookMarkCommon.FECHA_RESOL_BKM + TagCommon.TAG_FECHA_RESOL;

					as_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
						+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
				}
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver tag resolucion CB.
	 *
	 * @param as_plantilla correspondiente al valor de as plantilla
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagsResolucionCB(String as_plantilla)
	    throws B2BException
	{
		String        ls_tag;
		DAOManager    ldm_manager;
		ConstantesDAO lc_DAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_DAO          = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			{
				ls_tag = TagCommon.TAG_RESOLUCION;

				if(as_plantilla.contains(ls_tag))
					as_plantilla = putCustomBookMark(
						    as_plantilla, ls_tag, "RESOLUCION", lc_DAO.findById(ConstanteCommon.TAG_RESOL), false
						);
			}

			{
				ls_tag = TagCommon.TAG_FECHA_RESOL;

				if(as_plantilla.contains(ls_tag))
					as_plantilla = putCustomBookMark(
						    as_plantilla, ls_tag, "FECHA_RESOL", lc_DAO.findById(ConstanteCommon.TAG_FECHA_RESOL), false
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("resolverTagsResolucionCB", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_plantilla;
	}

	/**
	     * Resolver tags resolucion inicial.
	     *
	     * @param as_plantilla correspondiente al valor de as plantilla
	     * @return el valor de string
	     */
	private String resolverTagsResolucionInicial(String as_plantilla)
	{
		if(StringUtils.isValidString(as_plantilla))
		{
			if(as_plantilla.contains(TagCommon.TAG_RESOLUCION_INICIAL))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(TagCommon.TAG_RESOLUCION_INICIAL);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(
						    li_finalTag + TagCommon.TAG_RESOLUCION_INICIAL.length()
						);

					ls_tagNew     = BookMarkCommon.RESOLUCION_INICIAL_BKM + TagCommon.TAG_RESOLUCION_INICIAL;

					as_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
						+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
				}
			}

			if(as_plantilla.contains(TagCommon.TAG_FECHA_RESOLUCION_INICIAL))
			{
				int li_finalTag;

				li_finalTag = as_plantilla.lastIndexOf(TagCommon.TAG_FECHA_RESOLUCION_INICIAL);

				if(li_finalTag > 0)
				{
					String ls_textoMitadSuperior;
					String ls_textoMitadInferior;
					String ls_tagNew;

					ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
					ls_textoMitadInferior     = as_plantilla.substring(
						    li_finalTag + TagCommon.TAG_FECHA_RESOLUCION_INICIAL.length()
						);

					ls_tagNew     = BookMarkCommon.FECHA_RESOLUCION_INICIAL_BKM
						+ TagCommon.TAG_FECHA_RESOLUCION_INICIAL;

					as_plantilla = ls_textoMitadSuperior + IdentificadoresCommon.ESPACIO_VACIO + ls_tagNew
						+ IdentificadoresCommon.ESPACIO_VACIO + ls_textoMitadInferior;
				}
			}
		}

		return as_plantilla;
	}

	/**
	 * Resolver tag resolucion inicial CB.
	 *
	 * @param as_plantilla correspondiente al valor de as plantilla
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String resolverTagsResolucionInicialCB(String as_plantilla)
	    throws B2BException
	{
		String        ls_tag;
		DAOManager    ldm_manager;
		ConstantesDAO lc_DAO;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lc_DAO          = DaoCreator.getConstantesDAO(ldm_manager);

		try
		{
			{
				ls_tag = TagCommon.TAG_RESOLUCION_INICIAL;

				if(as_plantilla.contains(ls_tag))
					as_plantilla = putCustomBookMark(
						    as_plantilla, ls_tag, "RESOLUCION_INICIAL",
						    lc_DAO.findById(ConstanteCommon.RESOLUCION_INICIAL), true
						);
			}

			{
				ls_tag = TagCommon.TAG_FECHA_RESOLUCION_INICIAL;

				if(as_plantilla.contains(ls_tag))
					as_plantilla = putCustomBookMark(
						    as_plantilla, ls_tag, "FECHA_RESOLUCION_INICIAL",
						    lc_DAO.findById(ConstanteCommon.FECHA_RESOLUCION_INICIAL), true
						);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("resolverTagsResolucionInicialCB", lb2be_e);
			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return as_plantilla;
	}
}
