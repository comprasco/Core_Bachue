package com.bachue.snr.prosnr01.business.certificados;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
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
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRequiereMatriculaCommon;
import com.bachue.snr.prosnr01.common.utils.ConversionTextos;
import com.bachue.snr.prosnr01.common.utils.ListadoCodigosConstantes;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DatosAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleAntSistemaDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.OficiosTextoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoDAO;
import com.bachue.snr.prosnr01.dao.acc.SubprocesoVersionDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.InteresadoDocumentoTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.registro.NuevaEntrada;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.ui.FechaVenceTerminosUI;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * Permite el manejo de logica de negocio y manejo de transacciones con la base
 * de datos
 *
 * @author Manuel Blanco
 *
 */
public class CertificadosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CertificadosBusiness.class);

	/**
	 * Busca el círculo y matrícula asociadas a un registro con un número predial
	 * específico
	 *
	 * @param as_numeroPredial
	 *            cadena de caracteres con el número predial a utilizar como filtro
	 *            en la busqueda
	 * @return Objeto resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized PredioRegistro buscarMatriculaPorNumeroPredial(String as_numeroPredial)
	    throws B2BException
	{
		DAOManager     ldm_manager;
		PredioRegistro lpr_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lpr_return      = null;

		try
		{
			if(StringUtils.isValidString(as_numeroPredial))
				lpr_return = DaoCreator.getPredioRegistroDAO(ldm_manager).findByNumeroPredial(as_numeroPredial);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("buscarMatriculaPorNumeroPredial", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lpr_return;
	}

	/**
	 * Método calcular Tipo documental
	 * @param as_solicitud con los parametros de consulta
	 * @return de tipo String con el tipo documental
	 */
	public synchronized String calcularTipoDocumental(Solicitud as_solicitud)
	{
		String ls_return;

		ls_return = null;

		if(as_solicitud != null)
		{
			String ls_idSubproceso;

			ls_idSubproceso = as_solicitud.getIdSubproceso();

			if(StringUtils.isValidString(ls_idSubproceso))
			{
				switch(ls_idSubproceso)
				{
					case ProcesoCommon.ID_SUBPROCESO_3:
					case ProcesoCommon.ID_SUBPROCESO_6:
						ls_return = TipoDocumentalCommon.CERTIFICADO_CATASTRAL;

						break;

					case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_AMPLIACION:
					case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_PERTENENCIA:
					case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_SERVIDUMBRE:
					case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_CLARIFICACION_TITULOS:
					case ProcesoCommon.ID_SUBPROCESO_14:
					case ProcesoCommon.ID_SUBPROCESO_12:
						ls_return = TipoDocumentalCommon.CERTIFICADO_DE_ANTIGUO_SISTEMA;

						break;

					default:
						ls_return = TipoDocumentalCommon.CERTIFICADO_TRADICION_LIBERTAD;

						break;
				}
			}
		}

		return ls_return;
	}

	/**
	 * Consulta los datos pre cargados para una matricula de nueva entrada en certificados
	 *
	 * @param as_turno
	 *            id del turno a consultar
	 * @return NuevaEntrada perteneciente al resultado de la consulta
	 * @throws B2BException
	 */
	public synchronized NuevaEntrada cargarDatosMatriculaNuevaEntrada(Turno at_turno)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		NuevaEntrada lne_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lne_return      = null;

		try
		{
			if(at_turno != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = at_turno.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					String ls_idSubproceso;

					ls_idSubproceso     = at_turno.getIdSubProceso();
					lne_return          = new NuevaEntrada();

					if(StringUtils.isValidString(ls_idSubproceso))
					{
						if(
						    ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_AMPLIACION)
							    || ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_DE_SERVIDUMBRE)
							    || ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_CLARIFICACION_TITULOS)
							    || ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_PERTENENCIA)
						)
						{
							Collection<SolicitudMatricula> lcsm_solicitudMatricula;

							lcsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
									                                .findByIdSolicitud(ls_idSolicitud, false);

							if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
								lne_return.setSolicitudMatriculaNuevaEntrada(lcsm_solicitudMatricula);
						}

						else if(
						    ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_ANTIGUO_SISTEMA)
							    || ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_CARENCIA_REGISTRAL)
						)
						{
							DatosAntSistema ldas_datosAntSistema;

							ldas_datosAntSistema = DaoCreator.getDatosAntSistemaDAO(ldm_manager)
									                             .findByIdSolicitudOne(ls_idSolicitud);

							if(ldas_datosAntSistema != null)
							{
								Collection<DetalleAntSistema> ldas_detalleAntSistema;

								ldas_detalleAntSistema = DaoCreator.getDetalleAntSistemaDAO(ldm_manager)
										                               .findByDatosAntSis(
										    ldas_datosAntSistema.getIdDatosAntSistema()
										);

								if(CollectionUtils.isValidCollection(ldas_detalleAntSistema))
									ldas_datosAntSistema.setDetalleAntSistemaNuevaEntrada(ldas_detalleAntSistema);

								lne_return.setDatosAntSistemaNuevaEntrada(ldas_datosAntSistema);
							}
						}
					}

					{
						Collection<SolicitudInterviniente> lcsi_solicitudInterviniente;

						lcsi_solicitudInterviniente = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
								                                    .findByIdSolicitud(ls_idSolicitud);

						if(CollectionUtils.isValidCollection(lcsi_solicitudInterviniente))
						{
							boolean                          lb_encontrado;
							String                           ls_idPersona;
							Iterator<SolicitudInterviniente> lisi_iterator;

							lb_encontrado     = false;
							lisi_iterator     = lcsi_solicitudInterviniente.iterator();
							ls_idPersona      = null;

							while(lisi_iterator.hasNext() && !lb_encontrado)
							{
								SolicitudInterviniente lsi_solicitudInterviniente;

								lsi_solicitudInterviniente = lisi_iterator.next();

								if(lsi_solicitudInterviniente != null)
								{
									String ls_idPersonaInterviniente;

									ls_idPersonaInterviniente     = lsi_solicitudInterviniente.getIdPersona();
									ls_idPersona                  = ls_idPersonaInterviniente;

									lb_encontrado = StringUtils.isValidString(ls_idPersonaInterviniente);
								}
							}

							if(StringUtils.isValidString(ls_idPersona))
							{
								Persona lp_persona;

								lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_idPersona);

								if(lp_persona != null)
									lne_return.setPersonaNuevaEntrada(lp_persona);
							}
						}
					}

					{
						SolicitudDireccionCertificado lsdc_solicitudDireccionCertificado;

						lsdc_solicitudDireccionCertificado = new SolicitudDireccionCertificado();
						lsdc_solicitudDireccionCertificado.setIdSolicitud(ls_idSolicitud);

						lsdc_solicitudDireccionCertificado = DaoCreator.getSolicitudDireccionCertificadoDAO(
							    ldm_manager
							).findByIdSolicitud(lsdc_solicitudDireccionCertificado);

						if(lsdc_solicitudDireccionCertificado != null)
							lne_return.setSolicitudDireccionCertificadoNuevaEntrada(lsdc_solicitudDireccionCertificado);
					}

					{
						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

						if(ls_solicitud != null)
							lne_return.setSolicitud(ls_solicitud);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("cargarDatosMatriculaNuevaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lne_return;
	}

	/**
	 * Busca los casos en estado aut para que el job les genere el certificado
	 *
	 * @param as_remoteIp
	 *            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void certificados(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosCertificados;
		DAOManager                ldm_manager;

		lcth_turnosCertificados     = null;
		ldm_manager                 = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_turnosCertificados = obtenerTurnosJob(
				    ConstanteCommon.JOB_CERTIFICADOS_WS_INVOKE, ConstanteCommon.JOB_CERTIFICADOS_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_300, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("certificados", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_turnosCertificados))
			findCertificados(lcth_turnosCertificados, as_remoteIp);
	}

	/**
	 * Obtiene los turnos con usuario y etapa ASG
	 *
	 * @param atc_tc
	 *            objeto del cual se extraerán los datos necesarios para la consulta
	 * @return Collection<TramiteCantidad> con la informacion de bd
	 * @throws B2BException
	 */
	public synchronized TramiteCantidad consultaBandeja(TramiteCantidad atc_tc, boolean ab_detalle)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			TurnoHistoriaDAO lthd_DAO;
			lthd_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

			Collection<TramiteCantidad> lca_dataFinalTemp;

			lca_dataFinalTemp = null;

			if(atc_tc != null)
			{
				atc_tc.setTurnos(lthd_DAO.bandejaAnalistaCertificadosEspeciales(atc_tc, ab_detalle));

				lca_dataFinalTemp = atc_tc.getTurnos();

				if(CollectionUtils.isValidCollection(lca_dataFinalTemp))
				{
					if(!ab_detalle)
					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setNir(atc_tc.getNir());
						lt_turno.setIdTurno(atc_tc.getIdTurno());
						lt_turno.setIdEtapaActual(atc_tc.getIdEtapa());
						lt_turno.setIdUsuarioCreacion(atc_tc.getUsuario());

						atc_tc.setTurnosSuspendidos(lthd_DAO.bandejaCalificacionSuspendidos(lt_turno, 0));
					}
				}
			}

			if(atc_tc != null)
			{
				if(CollectionUtils.isValidCollection(lca_dataFinalTemp))
				{
					for(TramiteCantidad lotc_tmp : lca_dataFinalTemp)
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
								lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
										                          .findById(lth_turnoHistoria);

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
										if(
										    NumericUtils.getLong(lth_tmp.getIdEtapa()) == EtapaCommon.ID_ETAPA_REANOTACION
										)
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
						}
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

		return atc_tc;
	}

/**
 * Busca todas las plantillas asociadas al subproceso
 * @param ac_c Objeto certificados del cual se extraerán los parametros para realizar la búsqueda
 * @param as_idSubproceso subproceso para seleccionar la lista de caracteres que le corresponde
 * @param adm_manager DaoMananger que lleva el control de la transacción
 * @throws B2BException
 */
	public synchronized void consultaPlantillas(Certificados ac_c, String as_idSubproceso, DAOManager adm_manager)
	    throws B2BException
	{
		if((ac_c != null) && (adm_manager != null) && StringUtils.isValidString(as_idSubproceso))
		{
			ConstantesDAO          lcd_DAO;
			SubprocesoDAO          lsp_DAO;
			SubprocesoVersionDAO   lsv_DAO;
			Collection<Constantes> lcc_plantillas;
			Map<String, String>    lmss_caracter;
			Long                   ll_versionSubproceso;
			String                 ls_idProceso;

			lcd_DAO                  = DaoCreator.getConstantesDAO(adm_manager);
			lsp_DAO                  = DaoCreator.getSubprocesoDAO(adm_manager);
			lsv_DAO                  = DaoCreator.getSubprocesoVersionDAO(adm_manager);
			lcc_plantillas           = null;
			lmss_caracter            = new LinkedHashMap<String, String>();
			ll_versionSubproceso     = null;
			ls_idProceso             = null;

			{
				Solicitud ls_solicitud;

				ls_solicitud = ac_c.getSolicitud();

				if(ls_solicitud != null)
					ls_idProceso = ls_solicitud.getIdProceso();
			}

			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(ac_c.getIdTurnoHistoria());

				if(lth_turnoHistoria != null)
					ll_versionSubproceso = lth_turnoHistoria.getVersionSubproceso();
			}

			switch(as_idSubproceso)
			{
				case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_AMPLIACION:
				{
					Subproceso ls_subproceso;

					ls_subproceso = lsp_DAO.findById(ls_idProceso, as_idSubproceso);

					if(ls_subproceso != null)
					{
						SubprocesoVersion lsv_subProcesoVersion;

						lsv_subProcesoVersion = lsv_DAO.findById(ls_idProceso, as_idSubproceso, ll_versionSubproceso);

						if(lsv_subProcesoVersion != null)
						{
							String ls_plantilla;

							ls_plantilla = lsv_subProcesoVersion.getPlantilla();

							if(StringUtils.isValidString(ls_plantilla))
								lmss_caracter = ListadoCodigosConstantes.generarCodigos(ls_plantilla);
						}
					}

					break;
				}

				case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_PERTENENCIA:
				{
					Subproceso ls_subproceso;

					ls_subproceso = lsp_DAO.findById(ls_idProceso, as_idSubproceso);

					if(ls_subproceso != null)
					{
						SubprocesoVersion lsv_subProcesoVersion;

						lsv_subProcesoVersion = lsv_DAO.findById(ls_idProceso, as_idSubproceso, ll_versionSubproceso);

						if(lsv_subProcesoVersion != null)
						{
							String ls_plantilla;

							ls_plantilla = lsv_subProcesoVersion.getPlantilla();

							if(StringUtils.isValidString(ls_plantilla))
								lmss_caracter = ListadoCodigosConstantes.generarCodigos(ls_plantilla);
						}
					}

					break;
				}

				case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_CARENCIA:
				{
					Subproceso ls_subproceso;

					ls_subproceso = lsp_DAO.findById(ls_idProceso, as_idSubproceso);

					if(ls_subproceso != null)
					{
						SubprocesoVersion lsv_subProcesoVersion;

						lsv_subProcesoVersion = lsv_DAO.findById(ls_idProceso, as_idSubproceso, ll_versionSubproceso);

						if(lsv_subProcesoVersion != null)
						{
							String ls_plantilla;

							ls_plantilla = lsv_subProcesoVersion.getPlantilla();

							if(StringUtils.isValidString(ls_plantilla))
								lmss_caracter = ListadoCodigosConstantes.generarCodigos(ls_plantilla);
						}
					}

					break;
				}

				default:
					lmss_caracter = null;

					break;
			}

			if(CollectionUtils.isValidCollection(lmss_caracter))
			{
				Set<String> lks_keySet;

				lks_keySet         = lmss_caracter.keySet();
				lcc_plantillas     = new ArrayList<Constantes>();

				if(lks_keySet != null)
				{
					Iterator<String> lis_iterator;

					lis_iterator = lks_keySet.iterator();

					if(lis_iterator != null)
					{
						while(lis_iterator.hasNext())
						{
							Constantes lc_temp;

							lc_temp = new Constantes();

							lc_temp.setCaracter(lis_iterator.next());

							lc_temp = lcd_DAO.findByCaracter(lc_temp);

							if(lc_temp != null)
								lcc_plantillas.add(lc_temp);
						}
					}
				}
			}

			ac_c.setPlantillasPorCertificado(lcc_plantillas);
		}
	}

	/**
	 * Busca toda la informacion para mostrar en la pantalla de
	 * certificadosEspeciales.xhtml
	 *
	 * @return objeto Certificados resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized Certificados consultarDatosCertificadoEspecial(Certificados ac_c)
	    throws B2BException
	{
		Certificados lc_certificado;
		lc_certificado = null;

		if(ac_c != null)
		{
			DAOManager ldm_manager;

			ldm_manager = DaoManagerFactory.getDAOManager();

			try
			{
				Solicitud     ls_solicitud;
				SolicitudDAO  lsd_DAO;
				ConstantesDAO lcd_DAO;

				ls_solicitud     = ac_c.getSolicitud();
				lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);
				lcd_DAO          = DaoCreator.getConstantesDAO(ldm_manager);

				if(ls_solicitud != null)
				{
					ls_solicitud = lsd_DAO.findByNir(ls_solicitud);

					if(ls_solicitud != null)
					{
						String ls_idSolicitud;

						{
							Turno lt_turnoAnt;

							ls_idSolicitud     = ls_solicitud.getIdSolicitud();
							lt_turnoAnt        = new Turno();
							lc_certificado     = new Certificados();

							lt_turnoAnt.setIdTurno(ls_solicitud.getIdTurnoAnt());
							lt_turnoAnt = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turnoAnt);

							if(lt_turnoAnt != null)
							{
								Solicitud ls_solicitudAnt;

								ls_solicitudAnt = new Solicitud();

								ls_solicitudAnt.setIdSolicitud(lt_turnoAnt.getIdSolicitud());

								ls_solicitudAnt = lsd_DAO.findById(ls_solicitudAnt);

								if(ls_solicitudAnt != null)
									ls_solicitud.setNirAsociado(ls_solicitudAnt.getNir());
							}

							{
								boolean lb_nuevaEntrada;

								lb_nuevaEntrada = StringUtils.isValidString(ls_solicitud.getIdTurnoAnt())
										&& StringUtils.isValidString(ls_solicitud.getNirAsociado());

								lc_certificado.setSolicitud(ls_solicitud);
								lc_certificado.setIdTurnoHistoria(ac_c.getIdTurnoHistoria());
								lc_certificado.setNuevaEntrada(
								    lb_nuevaEntrada ? EstadoCommon.SI_TXT : EstadoCommon.NO_TXT
								);
							}
						}

						{
							SolicitudMatricula             lsm_solicitudMatricula;
							Collection<SolicitudMatricula> lcsm_matriculas;

							lsm_solicitudMatricula = new SolicitudMatricula();

							lsm_solicitudMatricula.setIdSolicitud(ls_idSolicitud);

							lcsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager)
									                        .findByIdSolicitud(lsm_solicitudMatricula, true);

							if(CollectionUtils.isValidCollection(lcsm_matriculas))
								lc_certificado.setMatriculas(lcsm_matriculas);
						}

						{
							SolicitudInterviniente             lsi_solicitudInterviniente;
							Collection<SolicitudInterviniente> lcsi_intervinientes;

							lsi_solicitudInterviniente = new SolicitudInterviniente();

							lsi_solicitudInterviniente.setIdSolicitud(ls_idSolicitud);

							lcsi_intervinientes = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager)
									                            .findByIdSolicitud(lsi_solicitudInterviniente);

							if(CollectionUtils.isValidCollection(lcsi_intervinientes))
							{
								Iterator<SolicitudInterviniente> lisi_solicitudInterviniente;

								lisi_solicitudInterviniente = lcsi_intervinientes.iterator();

								if(lisi_solicitudInterviniente != null)
								{
									lsi_solicitudInterviniente = lisi_solicitudInterviniente.next();

									if(lsi_solicitudInterviniente != null)
									{
										Persona lp_persona;

										lp_persona = new Persona();

										lp_persona.setIdPersona(lsi_solicitudInterviniente.getIdPersona());

										lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_persona);

										if(lp_persona != null)
											lc_certificado.setPersona(lp_persona);
									}
								}
							}
						}

						{
							Subproceso lsp_subProceso;

							lsp_subProceso = new Subproceso();

							lsp_subProceso.setIdProceso(ls_solicitud.getIdProceso());
							lsp_subProceso.setIdSubproceso(ls_solicitud.getIdSubproceso());

							lsp_subProceso = DaoCreator.getSubprocesoDAO(ldm_manager).findById(lsp_subProceso);

							if(lsp_subProceso != null)
								lc_certificado.setSubProceso(lsp_subProceso);
						}

						{
							SolicitudDireccionCertificado lsdc_direccionCertificado;

							lsdc_direccionCertificado = new SolicitudDireccionCertificado();

							lsdc_direccionCertificado.setIdSolicitud(ls_idSolicitud);

							lsdc_direccionCertificado = DaoCreator.getSolicitudDireccionCertificadoDAO(ldm_manager)
									                                  .findByIdSolicitud(lsdc_direccionCertificado);

							if(lsdc_direccionCertificado != null)
								lc_certificado.setDireccion(lsdc_direccionCertificado);
						}

						{
							Subproceso lsp_subproceso;

							lsp_subproceso = lc_certificado.getSubProceso();

							if(lsp_subproceso != null)
							{
								String ls_idSubproceso;

								ls_idSubproceso = lsp_subproceso.getIdSubproceso();

								Map<String, String> lmss_caracter;
								lmss_caracter = ListadoCodigosConstantes.generarCodigos(
									    lcd_DAO.findString(ConstanteCommon.ID_CERTIFICADOS_CON_VARIAS_PLANTILLAS)
									);

								if(
								    CollectionUtils.isValidCollection(lmss_caracter)
									    && lmss_caracter.containsKey(ls_idSubproceso)
								)
									consultaPlantillas(lc_certificado, ls_idSubproceso, ldm_manager);
								else
								{
									String ls_idConstante;

									ls_idConstante = null;

									switch(ls_idSubproceso)
									{
										case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_SERVIDUMBRE:
											ls_idConstante = ConstanteCommon.PLANTILLA_CERTIFICADO_SERVIDUMBRE;

											break;

										case ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_CLARIFICACION_TITULOS:
											ls_idConstante = ConstanteCommon.PLANTILLA_CERTIFICADO_CLARIFICACION_TITULOS;

											break;

										default:
											break;
									}

									Constantes lc_constante;

									lc_constante     = new Constantes(ls_idConstante);

									lc_constante = lcd_DAO.findById(lc_constante);

									if(lc_constante != null)
										lc_certificado.setPlantillaSeleccionada(lc_constante.getIdConstante());
								}
							}
						}
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				clh_LOGGER.error("consultarDatosCertificadoEspecial", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_manager.close();
			}
		}

		return lc_certificado;
	}

	/**
	 * Consulta el nir asociado a un turno de certificados
	 *
	 * @param as_turno
	 *            id del turno a consultar
	 * @return Solicitud perteneciente al proceso de certificados con el nir
	 *         relacionado al turno
	 * @throws B2BException
	 */
	public synchronized Solicitud consultarNirAsociadoNuevaEntrada(String as_turno)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Solicitud  ls_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		ls_return       = null;

		try
		{
			if(!StringUtils.isValidString(as_turno))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_ANTERIOR);

			Turno lt_turno;

			lt_turno = new Turno();

			lt_turno.setIdTurno(as_turno);

			lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

			if(lt_turno == null)
				throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_INVALIDO);

			{
				String ls_idProceso;

				ls_idProceso = StringUtils.getStringNotNull(lt_turno.getIdProceso());

				if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))
				{
					String ls_idSubProceso;
					long   ll_idEtapa;

					ls_idSubProceso     = lt_turno.getIdSubProceso();
					ll_idEtapa          = NumericUtils.getLong(lt_turno.getIdEtapaActual());

					if(
					    StringUtils.isValidString(ls_idSubProceso)
						    && (ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_AMPLIACION)
						    || ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_CARENCIA_REGISTRAL)
						    || ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_DE_SERVIDUMBRE)
						    || ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_CLARIFICACION_TITULOS)
						    || ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_ANTIGUO_SISTEMA)
						    || ls_idSubProceso.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_PERTENENCIA))
						    && ((ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_DE_CERTIFICADOS_FINALIZADO_NO_APROBADO)
						    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_DE_DEVOLUCION_DE_DINERO_FINALIZADO_NEGADO))
					)
					{
						ls_return = new Solicitud();

						ls_return.setIdSolicitud(lt_turno.getIdSolicitud());

						ls_return = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_return);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_NO_CUMPLE);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_PROCESO_INVALIDO_CERTIFICADOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarNirAsociadoNuevaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_return;
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

			if(lt_return != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(lt_return.getIdTurno());

				if(lth_turnoHistoria != null)
					lt_return.setFirmaJustificacion(lth_turnoHistoria.getObservacionesNoTramite());
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
	 * Busca todos los turnos de certificados para un nir específico
	 *
	 * @param as_nir
	 *            cadena de caracteres con el nir ingresado
	 * @return colección de turno historia resultante de la consulta
	 * @throws B2BException
	 */
	public synchronized Collection<TurnoHistoria> consultarTurnosAsociadosNuevaEntrada(String as_nir)
	    throws B2BException
	{
		DAOManager                ldm_manager;
		Collection<TurnoHistoria> lcth_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lcth_return     = new LinkedList<TurnoHistoria>();

		try
		{
			if(!StringUtils.isValidString(as_nir))
				throw new B2BException(ErrorKeys.ERROR_NIR_NO_INGRESADO);

			Solicitud ls_solicitud;

			ls_solicitud = new Solicitud();

			ls_solicitud.setNir(as_nir);

			ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findByNir(ls_solicitud);

			if(ls_solicitud != null)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
				lth_turnoHistoria.setIdProceso(ProcesoCommon.ID_PROCESO_1);

				lcth_return = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findAllBySolicitudProceso(lth_turnoHistoria);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarTurnosAsociadosNuevaEntrada", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcth_return;
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
	 * Bloquea el JOB e invoca el proceso de generación de certificados
	 *
	 * @param acth_parametros
	 *            Colección de casos a generar certificado
	 * @param as_remoteIp
	 *            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void findCertificados(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CERTIFICADOS_BLOQUEO;
		ls_userId       = "JOB CERTIFICADOS";

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

				clh_LOGGER.error("findCertificados", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						String ls_usuarioProceso;

						ls_usuarioProceso = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_manager);

						ldm_manager.setAutoCommit(true);

						if(StringUtils.isValidString(ls_usuarioProceso))
						{
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
										generarCertificado(lbpd_bitacora, lth_iterador, as_remoteIp, ls_usuarioProceso);

										lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
									}
									catch(B2BException lb2be_e)
									{
										clh_LOGGER.error("findCertificados", lb2be_e);

										ls_mensaje = getErrorMessage(lb2be_e);
									}

									actualizarIntentoEnvio(
									    lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager
									);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("findCertificados", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findCertificados", lb2be_e);

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

					clh_LOGGER.error("findCertificados", lb2be_e);

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
	 * Genera los certifiacados especiales
	 * @param ac_parametros Objeto Certificados del cual se extraerán los parametros para realizar la búsqueda y se le insertará el byte[] del certificado generado
	 * @param ab_firmaMasiva bandera que indica si es llamado desde job de firma
	 * @param ab_definitivo bandera que indica si el usuario acciono el boton de generar documentos de la pantalla generaCertificadosEspeciales.xhtml
	 * @param ab_salvar bandera que indica si el usuario acciono el boton de terminar de la pantalla generaCertificadosEspeciales.xhtml
	 * @param adm_manager DaoMananger que controla la transacción
	 * @throws B2BException
	 */
	public void generaCertificadosEspeciales(
	    Certificados ac_parametros, boolean ab_firmaMasiva, boolean ab_definitivo, boolean ab_salvar,
	    DAOManager adm_manager, Long al_idDocumentoSalidaExistente
	)
	    throws B2BException
	{
		if((ac_parametros != null) && (adm_manager != null))
		{
			ConstantesDAO lcd_DAO;
			lcd_DAO = DaoCreator.getConstantesDAO(adm_manager);

			TurnoHistoria lth_turnoHistoria;
			lth_turnoHistoria     = new TurnoHistoria(ac_parametros.getIdTurnoHistoria());

			lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

			if((lth_turnoHistoria == null) || (NumericUtils.getLong(lth_turnoHistoria.getIdTurnoHistoria()) <= 0))
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			else
			{
				Turno lt_turno;
				lt_turno = new Turno();

				lt_turno.setIdTurno(lth_turnoHistoria.getIdTurno());

				lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

				if(lt_turno != null)
				{
					String     ls_constante;
					Constantes lc_constante;
					byte[]     lba_plantilla;

					ls_constante     = ac_parametros.getPlantillaSeleccionada();
					lc_constante     = new Constantes();
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

						ls_plantilla     = new String(lba_plantilla);
						ltd_DAO          = DaoCreator.getTurnoDAO(adm_manager);
						lcrd_DAO         = DaoCreator.getCirculoRegistralDAO(adm_manager);
						lsd_DAO          = DaoCreator.getSolicitudDAO(adm_manager);

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
								String    ls_orip;
								Solicitud ls_solicitud;

								ls_orip          = lt_datosTurno.getIdCirculo();
								ls_solicitud     = ac_parametros.getSolicitud();

								ls_solicitud = lsd_DAO.findById(ls_solicitud);

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

										if(StringUtils.isValidString(ls_oficinaOrigen) && (lbd_version != null))
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
														String ls_munOficinaOrigen;
														ls_munOficinaOrigen = lm_municipio.getNombre();

														if(
														    ls_plantilla.contains(ls_tag)
															    && StringUtils.isValidString(ls_munOficinaOrigen)
														)
														{
															ls_tag = "<TAG_CIUDAD>";

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

												{
													ls_tag = "<TAG_DIRECCION_ORIP>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, loo_oficinaOrigen.getDireccion()
															);

													ls_tag = "<TAG_TELEFONO_ORIP>";

													if(ls_plantilla.contains(ls_tag))
														ls_plantilla = ls_plantilla.replace(
															    ls_tag, loo_oficinaOrigen.getTelefono()
															);

													ls_tag = "<TAG_CORREO_ORIP>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_correo;

														ls_correo = loo_oficinaOrigen.getCorreoElectronico();

														if(StringUtils.isValidString(ls_correo))
															ls_plantilla = ls_plantilla.replace(ls_tag, ls_correo);
													}
												}
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

								{
									String ls_tagFechaLarga;
									ls_tagFechaLarga = "<TAG_FECHA_LARGA>";

									if(ls_plantilla.contains(ls_tagFechaLarga))
									{
										Date   ld_fecha;
										String ls_fechaActual;

										ld_fecha           = new Date();
										ls_fechaActual     = DateUtils.convertirLetrasLarga(ld_fecha);

										if(StringUtils.isValidString(ls_fechaActual))
											ls_plantilla = ls_plantilla.replace(
												    ls_tagFechaLarga, ls_fechaActual.toUpperCase()
												);
									}
								}

								{
									String ls_fechaTurno;
									ls_fechaTurno = DateUtils.convertirLetrasLarga((lt_turno.getFechaCreacion()));

									if(StringUtils.isValidString(ls_fechaTurno))
									{
										ls_tag = "<TAG_ACC_FECHA_TURNO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(ls_tag, ls_fechaTurno);
									}
								}

								{
									ls_tag = "<TAG_DERECHOS_CAUSADOS>";

									if(ls_plantilla.contains(ls_tag) && (ls_solicitud != null))
									{
										String ls_idSolicitud;

										ls_idSolicitud = ls_solicitud.getIdSolicitud();

										if(StringUtils.isValidString(ls_idSolicitud))
										{
											Liquidacion ll_liquidacion;

											ll_liquidacion = new Liquidacion();

											ll_liquidacion.setIdSolicitud(ls_idSolicitud);

											ll_liquidacion = DaoCreator.getAccLiquidacionDAO(adm_manager)
													                       .findById(ll_liquidacion, false);

											if(ll_liquidacion != null)
											{
												BigDecimal lbd_valorTotal;

												lbd_valorTotal = ll_liquidacion.getValorTotal();

												if(NumericUtils.isValidBigDecimal(lbd_valorTotal))
													ls_plantilla = ls_plantilla.replace(
														    ls_tag, StringUtils.getString(lbd_valorTotal)
														);
											}
										}
									}
								}

								{
									ls_tag = "<TAG_NOMBRE_INTERESADO>";

									if(ls_plantilla.contains(ls_tag) && (ls_solicitud != null))
									{
										String ls_idPersona;
										ls_idPersona = ls_solicitud.getIdPersona();

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

												if(!ls_idTipoDocumento.contentEquals(IdentificadoresCommon.NIT))
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

												{
													ls_tag = "<TAG_TIPO_DOCUMENTO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_idTipoDoc;

														ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

														if(StringUtils.isValidString(ls_idTipoDoc))
														{
															InteresadoDocumentoTipo    lidt_tipoDocumento;
															InteresadoDocumentoTipoDAO lidt_DAO;

															lidt_tipoDocumento     = new InteresadoDocumentoTipo();
															lidt_DAO               = DaoCreator
																	.getInteresadoDocumentoTipoDAO(adm_manager);

															lidt_tipoDocumento.setIdDocumentoTipo(ls_idTipoDoc);

															lidt_tipoDocumento = lidt_DAO.findById(lidt_tipoDocumento);

															if(lidt_tipoDocumento != null)
															{
																String ls_descripcion;

																ls_descripcion = lidt_tipoDocumento.getDescripcion();

																if(StringUtils.isValidString(ls_descripcion))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_descripcion
																		);
															}
														}
													}
												}

												{
													ls_tag = "<TAG_NUM_DOC>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_numeroDocumento;

														ls_numeroDocumento = lp_persona.getNumeroDocumento();

														if(StringUtils.isValidString(ls_numeroDocumento))
															ls_plantilla = ls_plantilla.replace(
																    ls_tag, ls_numeroDocumento
																);
													}
												}

												ls_tag = "<TAG_CORREO_ELECTRONICO>";

												if(ls_plantilla.contains(ls_tag))
												{
													String ls_idCorreo;

													ls_idCorreo = ls_solicitud.getIdCorreoElectronico();

													if(StringUtils.isValidString(ls_idCorreo))
													{
														PersonaCorreoElectronico    lpce_correo;
														PersonaCorreoElectronicoDAO lpce_DAO;

														lpce_correo     = new PersonaCorreoElectronico();
														lpce_DAO        = DaoCreator.getPersonaCorreoElectronicoDAO(
															    adm_manager
															);

														lpce_correo.setIdPersona(ls_idPersona);
														lpce_correo.setIdCorreoElectronico(ls_idCorreo);

														lpce_correo = lpce_DAO.findById(lpce_correo);

														if(lpce_correo != null)
														{
															String ls_correoElectronico;

															ls_correoElectronico = lpce_correo.getCorreoElectronico();

															if(StringUtils.isValidString(ls_correoElectronico))
																ls_plantilla = saltoDeCarroDespues(
																	    ls_plantilla, ls_tag, ls_correoElectronico
																	);
														}
													}
												}

												{
													ls_tag = "<TAG_DIR_INTERESADO>";

													if(ls_plantilla.contains(ls_tag))
													{
														String ls_medioNotificar;

														ls_medioNotificar = ls_solicitud.getIdAutorizacionNotificacion();

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
															lpd_pd.setIdDireccion(ls_solicitud.getIdDireccion());

															lpd_pd = DaoCreator.getPersonaDireccionDAO(adm_manager)
																	               .findById(lpd_pd);

															if(lpd_pd != null)
															{
																{
																	String ls_idPais;
																	String ls_idDepartamento;
																	String ls_idMunicipio;

																	ls_idPais             = lpd_pd.getIdPais();
																	ls_idDepartamento     = lpd_pd.getIdDepartamento();
																	ls_idMunicipio        = lpd_pd.getIdMunicipio();

																	{
																		ls_tag = "<TAG_DIR_INTERESADO>";

																		if(ls_plantilla.contains(ls_tag))
																		{
																			String ls_direccioncompleta;
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
																	}

																	{
																		StringBuilder lsb_builder;

																		lsb_builder     = new StringBuilder();
																		ls_tag          = "<TAG_DEPAR_MUN_INTERESADO>";

																		if(ls_plantilla.contains(ls_tag))
																		{
																			if(
																			    StringUtils.isValidString(ls_idPais)
																				    && StringUtils.isValidString(
																				        ls_idDepartamento
																				    )
																			)
																			{
																				Departamento ld_departamento;
																				ld_departamento = new Departamento();

																				ld_departamento.setIdPais(ls_idPais);
																				ld_departamento.setIdDepartamento(
																				    ls_idDepartamento
																				);

																				ld_departamento = DaoCreator.getDepartamentoDAO(
																					    adm_manager
																					).findById(ld_departamento);

																				if(ld_departamento != null)
																				{
																					String ls_nombreDep;
																					ls_nombreDep = ld_departamento
																							.getNombre();

																					if(
																					    StringUtils.isValidString(
																						        ls_nombreDep
																						    )
																					)
																						lsb_builder.append(
																						    ls_nombreDep
																						);
																				}
																			}
																		}

																		if(
																		    StringUtils.isValidString(ls_idPais)
																			    && StringUtils.isValidString(
																			        ls_idDepartamento
																			    )
																			    && StringUtils.isValidString(
																			        ls_idMunicipio
																			    )
																		)
																		{
																			Municipio lm_municipio;
																			String    ls_nombreMun;

																			lm_municipio     = new Municipio();
																			ls_nombreMun     = null;

																			lm_municipio.setIdPais(ls_idPais);
																			lm_municipio.setIdDepartamento(
																			    ls_idDepartamento
																			);
																			lm_municipio.setIdMunicipio(ls_idMunicipio);

																			lm_municipio = DaoCreator.getMunicipioDAO(
																				    adm_manager
																				).findById(lm_municipio);

																			if(lm_municipio != null)
																			{
																				ls_nombreMun = lm_municipio.getNombre();

																				if(
																				    StringUtils.isValidString(
																					        ls_nombreMun
																					    )
																				)
																				{
																					lsb_builder.append(
																					    IdentificadoresCommon.SIMBOLO_COMA
																					);
																					lsb_builder.append(ls_nombreMun);
																				}

																				lsb_builder.append(
																				    IdentificadoresCommon.SALTO_LINEA_RTF
																				);

																				ls_plantilla = ls_plantilla.replace(
																					    ls_tag, lsb_builder
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
										}
									}
								}

								{
									ls_tag = "<TAG_MATRICULA>";

									if(ls_plantilla.contains(ls_tag))
									{
										String ls_idCirculo;
										ls_idCirculo = lt_turno.getIdCirculo();

										if(StringUtils.isValidString(ls_idCirculo))
										{
											SolicitudMatricula             lsm_solicitudMatricula;
											Collection<SolicitudMatricula> lcsm_datos;

											lsm_solicitudMatricula = new SolicitudMatricula();
											lsm_solicitudMatricula.setIdSolicitud(ls_solicitud.getIdSolicitud());
											lsm_solicitudMatricula.setIdCirculo(ls_idCirculo);

											lcsm_datos = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
													                   .findByIdSolicitudCirculo(
													    lsm_solicitudMatricula
													);

											if(CollectionUtils.isValidCollection(lcsm_datos))
											{
												StringBuilder lsb_sb;
												int           li_contador;
												long          ll_repetida = 0;

												lsb_sb          = new StringBuilder();
												li_contador     = 1;

												for(SolicitudMatricula lsm_iterador : lcsm_datos)
												{
													if(lsm_iterador != null)
													{
														if(ll_repetida != lsm_iterador.getIdMatricula())
														{
															ll_repetida = lsm_iterador.getIdMatricula();

															int    li_size;
															String ls_signo;

															li_size      = lcsm_datos.size();
															ls_signo     = "";

															if(li_size > 1)
																ls_signo = ", ";

															if(li_contador == li_size)
																ls_signo = IdentificadoresCommon.PUNTO;

															lsb_sb.append(lsm_iterador.getIdCirculo());
															lsb_sb.append("-");
															lsb_sb.append(lsm_iterador.getIdMatricula());
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

								ls_tag = TagCommon.TAG_EXENTA;

								{
									String ls_exenta;

									ls_exenta = (ls_solicitud != null) ? ls_solicitud.getEntidadExenta() : null;

									if(StringUtils.isValidString(ls_exenta) && BooleanUtils.getBooleanValue(ls_exenta))
										ls_plantilla = reemplazarTagEnPlantilla(
											    ls_plantilla, TagCommon.TAG_EXENTA,
											    lcd_DAO.findString(ConstanteCommon.TAG_EXENTA_CERTIFICADOS)
											);
								}

								ls_tag = ac_parametros.getTagResuelvePantalla();

								if(StringUtils.isValidString(ls_tag))
								{
									Constantes lc_tag;

									lc_tag = lcd_DAO.findById(ls_tag);

									if(lc_tag != null)
									{
										ls_tag = lc_tag.getCaracter();

										if(StringUtils.isValidString(ls_tag) && ls_plantilla.contains(ls_tag))
										{
											String ls_tmp;

											ls_tmp = ac_parametros.getTextoTag();

											if(StringUtils.isValidString(ls_tmp))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
										}
									}
								}

								if(ab_firmaMasiva)
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
								else
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
									String ls_usuario;
									String ls_ip;

									ls_usuario     = ac_parametros.getIdUsuarioCreacion();
									ls_ip          = ac_parametros.getIpCreacion();

									if(ls_plantilla.contains("<TAG_USUARIO>"))
									{
										if(StringUtils.isValidString(ls_usuario))
											ls_plantilla = ls_plantilla.replace("<TAG_USUARIO>", ls_usuario);
									}

									{
										Constantes lc_usuarioFirma;
										byte[]     lba_certificado;
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
											    ls_plantilla, lth_turnoHistoria.getIdTurnoHistoria(), ab_definitivo,
											    adm_manager
											);
										ls_plantilla        = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
										lba_certificado     = new PDFGenerator().convertirRTFToPDF(
											    ls_plantilla.getBytes(), adm_manager
											);

										if(lba_certificado == null)
											throw new B2BException(ErrorKeys.NO_SE_GENERO_RESOLUCION);

										ac_parametros.setDocumento(lba_certificado);

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

											lba_certificado = getFirmaMasivaBusiness()
													                  .reemplazarBookmarsFirma(
													    lba_certificado, lba_grafo, li_incrX, li_incrY
													);
										}

										if(ab_salvar)
										{
											ImagenesDAO         lid_DAO;
											DocumentosSalidaDAO ldsd_DAO;
											long                li_secuencia;
											boolean             lb_existeImagen;
											Imagenes            li_imagenes;
											DocumentosSalida    lds_documentosSalida;

											lid_DAO             = DaoCreator.getImagenesDAO(adm_manager);
											ldsd_DAO            = DaoCreator.getDocumentosSalidaDAO(adm_manager);
											lb_existeImagen     = NumericUtils.isValidLong(
												    al_idDocumentoSalidaExistente
												);

											li_imagenes              = new Imagenes();
											lds_documentosSalida     = new DocumentosSalida();

											if(lb_existeImagen)
											{
												lds_documentosSalida.setIdDocumentoSalida(
												    NumericUtils.getLong(al_idDocumentoSalidaExistente)
												);

												lds_documentosSalida = ldsd_DAO.findById(lds_documentosSalida);

												if(lds_documentosSalida != null)
													li_imagenes.setIdImagen(
													    NumericUtils.getInt(lds_documentosSalida.getIdImagen())
													);
												else
												{
													lds_documentosSalida     = new DocumentosSalida();

													lb_existeImagen = false;
												}
											}

											li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
											li_imagenes.setIdUsuarioCreacion(ls_usuario);
											li_imagenes.setIdUsuarioModificacion(ls_usuario);
											li_imagenes.setIpCreacion(ls_ip);
											li_imagenes.setIpModificacion(ls_ip);
											li_imagenes.setImagenBlob(lba_certificado);
											li_imagenes.setIdTurno(lt_turno.getIdTurno());
											li_imagenes.setCodigoVerificacion(
											    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
											);

											li_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen, true);

											if((li_secuencia <= 0) && !lb_existeImagen)
												throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

											if(!lb_existeImagen)
											{
												lds_documentosSalida.setIdImagen(
												    NumericUtils.getLongWrapper(li_secuencia)
												);
												lds_documentosSalida.setTipo(TipoArchivoCommon.CERTIFICADO);
												lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

												lds_documentosSalida.setRepositorio(
												    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
												);

												lds_documentosSalida.setIdTipoDocumental(
												    calcularTipoDocumental(ls_solicitud)
												);
												lds_documentosSalida.setIdTurno(lt_turno.getIdTurno());
												lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
												lds_documentosSalida.setIdTurnoHistoria(
												    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
												);
												lds_documentosSalida.setIdUsuarioCreacion(ls_usuario);
												lds_documentosSalida.setIpCreacion(ls_ip);

												ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);
											}
											else
											{
												lds_documentosSalida.setRepositorio(
												    ab_firmaMasiva ? RepositorioCommon.FINAL : RepositorioCommon.TEMPORAL
												);
												lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
												lds_documentosSalida.setIdUsuarioModificacion(ls_usuario);
												lds_documentosSalida.setIpModificacion(ls_ip);
												lds_documentosSalida.setIdEcm(null);
												lds_documentosSalida.setIdNombreDocumento(null);
												lds_documentosSalida.setFechaEnvio(null);

												ldsd_DAO.insertOrUpdate(lds_documentosSalida, false);
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
	}

	/**
	 * Termina la etapa actual y crea una nueva, generando a la vez el certificado
	 * correspondinete al proceso
	 *
	 * @param ath_turnoHistoria
	 *            Objeto contenedor del la infromación del tramite en proceso
	 * @param as_remoteIp
	 *            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void generarCertificado(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String ls_usuarioProceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			Map<String, SolicitudMatricula> lmsm_matriculas;

			{
				SolicitudMatricula lsm_tmp;

				lsm_tmp = new SolicitudMatricula();

				lsm_tmp.setIdTurnoCertificado(ath_turnoHistoria.getIdTurno());

				lmsm_matriculas = DaoCreator.getSolicitudMatriculaDAO(ldm_manager).findAllByTurnoCertificado(lsm_tmp);
			}

			if(CollectionUtils.isValidCollection(lmsm_matriculas))
			{
				for(Map.Entry<String, SolicitudMatricula> lmessm_data : lmsm_matriculas.entrySet())
				{
					if(lmessm_data != null)
					{
						SolicitudMatricula lsm_matricula;

						lsm_matricula = lmessm_data.getValue();

						if(lsm_matricula != null)
						{
							lsm_matricula.setTurnoHistoria(ath_turnoHistoria);

							{
								BigDecimal lbd_cantidadCertificados;

								lbd_cantidadCertificados = lsm_matricula.getCantidadCertificados();

								if(NumericUtils.isValidBigDecimal(lbd_cantidadCertificados))
								{
									for(int li_i = 0; li_i < NumericUtils.getInt(lbd_cantidadCertificados); li_i++)
										getFirmaMasivaBusiness()
											    .generarCertificadoTradicionLibertad(
											    lsm_matricula, IdentificadoresCommon.CERTIFICADOS, true, ldm_manager,
											    ls_usuarioProceso, as_remoteIp
											);
								}
							}
						}
					}
				}

				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ath_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_medioNotificar;
						long   ll_idMotivo;

						ls_medioNotificar = StringUtils.getStringNotNull(ls_solicitud.getIdAutorizacionNotificacion());

						if(ls_medioNotificar.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
							ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_POR_CORREO_ELECTRONICO;
						else
							ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_EN_LA_ORIP;

						terminarTurnoHistoriaYCrearEtapa(
						    ath_turnoHistoria, ldm_manager, new MotivoTramite(EtapaCommon.ID_ETAPA_300, ll_idMotivo),
						    ls_usuarioProceso, as_remoteIp, EstadoCommon.TERMINADA
						);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_SOLICITUD);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.CERTIFICADOS,
			    addMessage(ErrorKeys.ERROR_NO_MATRICULA, true), ls_usuarioProceso, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Enruta el certificado que se generará con el metodo que los genera
	 * @param ac_c Objeto Certificados del cual se extraerán los parametros para realizar la búsqueda
	 * @param ab_firmaMasiva bandera que indica si es llamado desde job de firma
	 * @param ab_definitivo bandera que indica si el usuario acciono el boton de generar documentos de la pantalla generaCertificadosEspeciales.xhtml
	 * @param ab_salvar bandera que indica si el usuario acciono el boton de terminar de la pantalla generaCertificadosEspeciales.xhtml
	 * @return Objeto Certificado cargado con el resultado de la operación
	 * @throws B2BException
	 */
	public synchronized Certificados generarCertificadosEspeciales(
	    Certificados ac_c, boolean ab_firmaMasiva, boolean ab_definitivo, boolean ab_salvar
	)
	    throws B2BException
	{
		DAOManager   ldm_manager;
		Certificados lc_certificado;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		lc_certificado     = null;

		try
		{
			lc_certificado = generarCertificadosEspeciales(ac_c, ab_firmaMasiva, ab_definitivo, ab_salvar, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarCertificadosEspeciales", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_certificado;
	}

	/**
	 * Enruta el certificado que se generará con el metodo que los genera.
	 *
	 * @param ac_c Objeto Certificados del cual se extraerán los parametros para realizar la búsqueda
	 * @param ab_firmaMasiva bandera que indica si es llamado desde job de firma
	 * @param ab_definitivo bandera que indica si el usuario acciono el boton de generar documentos de la pantalla generaCertificadosEspeciales.xhtml
	 * @param ab_salvar bandera que indica si el usuario acciono el boton de terminar de la pantalla generaCertificadosEspeciales.xhtml
	 * @param adm_manager DAOMananger que si es llamado desde firma masiva viene instanciado de lo contrario viene null
	 * @return Objeto Certificado cargado con el resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Certificados generarCertificadosEspeciales(
	    Certificados ac_c, boolean ab_firmaMasiva, boolean ab_definitivo, boolean ab_salvar, DAOManager adm_manager
	)
	    throws B2BException
	{
		return generarCertificadosEspeciales(ac_c, ab_firmaMasiva, ab_definitivo, ab_salvar, adm_manager, null);
	}

	/**
	 * Enruta el certificado que se generará con el metodo que los genera.
	 *
	 * @param ac_c Objeto Certificados del cual se extraerán los parametros para realizar la búsqueda
	 * @param ab_firmaMasiva bandera que indica si es llamado desde job de firma
	 * @param ab_definitivo bandera que indica si el usuario acciono el boton de generar documentos de la pantalla generaCertificadosEspeciales.xhtml
	 * @param ab_salvar bandera que indica si el usuario acciono el boton de terminar de la pantalla generaCertificadosEspeciales.xhtml
	 * @param adm_manager correspondiente al valor de adm manager
	 * @param al_idDocumentoSalidaExistente correspondiente al valor de al id documento salida existente
	 * @return Objeto Certificado cargado con el resultado de la operación
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Certificados generarCertificadosEspeciales(
	    Certificados ac_c, boolean ab_firmaMasiva, boolean ab_definitivo, boolean ab_salvar, DAOManager adm_manager,
	    Long al_idDocumentoSalidaExistente
	)
	    throws B2BException
	{
		Certificados lc_certificado;

		lc_certificado = null;

		if(ac_c != null)
		{
			Subproceso lsp_subProceso;

			lsp_subProceso = ac_c.getSubProceso();

			if(lsp_subProceso != null)
			{
				String ls_idSubproceso;

				ls_idSubproceso = lsp_subProceso.getIdSubproceso();

				if(StringUtils.isValidString(ls_idSubproceso))
				{
					String              ls_idTurno;
					TurnoHistoria       lth_turnoHistoria;
					Map<String, String> lmss_caracter;
					ConstantesDAO       lcd_DAO;

					ls_idTurno            = ac_c.getIdTurno();
					lth_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(ls_idTurno);
					lcd_DAO               = DaoCreator.getConstantesDAO(adm_manager);

					if(lth_turnoHistoria != null)
					{
						lmss_caracter = ListadoCodigosConstantes.generarCodigos(
							    lcd_DAO.findString(ConstanteCommon.ID_CERTIFICADOS_SIN_TAG)
							);

						SolicitudMatricula lsm_matricula;

						lsm_matricula = new SolicitudMatricula();

						lsm_matricula.setIdTurnoCertificado(ls_idTurno);
						lsm_matricula.setExpedirCertificado(EstadoCommon.S);

						lsm_matricula = DaoCreator.getSolicitudMatriculaDAO(adm_manager)
								                      .findByTurnoCertificado(lsm_matricula);

						if(lsm_matricula != null)
						{
							BigDecimal lbd_cantidadCertificados;

							lbd_cantidadCertificados = lsm_matricula.getCantidadCertificados();

							if(!NumericUtils.isValidBigDecimal(lbd_cantidadCertificados, BigDecimal.ONE))
								lbd_cantidadCertificados = BigDecimal.valueOf(1);

							for(
							    int li_contador = 0, li_cantidad = lbd_cantidadCertificados.intValue();
								    li_contador < ((ab_firmaMasiva || !ab_salvar) ? 1 : li_cantidad); li_contador++
							)
								if(
								    CollectionUtils.isValidCollection(lmss_caracter)
									    && lmss_caracter.containsKey(ls_idSubproceso)
								)
								{
									lsm_matricula.setTurnoHistoria(lth_turnoHistoria);

									lc_certificado = new Certificados();

									lc_certificado.setDocumento(
									    getFirmaMasivaBusiness()
										        .generarCertificadoTradicionLibertad(
										        lsm_matricula, IdentificadoresCommon.CERTIFICADOS, ab_firmaMasiva,
										        adm_manager, IdentificadoresCommon.CERTIFICADOS,
										        ac_c.getIpModificacion()
										    )
									);
									lc_certificado.setTagResuelvePantalla(null);
									lc_certificado.setTextoTag(null);
									lc_certificado.setNombreDocumento(lsp_subProceso.getNombre());
								}
								else
								{
									String ls_plantilla;

									ls_plantilla = ac_c.getPlantillaSeleccionada();

									if(StringUtils.isValidString(ls_plantilla))
									{
										lmss_caracter = ListadoCodigosConstantes.generarCodigos(
											    lcd_DAO.findString(ConstanteCommon.PLANTILLAS_CON_CONSTANTE)
											);

										if(CollectionUtils.isValidCollection(lmss_caracter))
										{
											String     ls_idConstante;
											Constantes lc_constante;
											boolean    lb_esConConstante;

											ls_idConstante        = null;
											lc_constante          = null;
											lb_esConConstante     = lmss_caracter.containsKey(ls_plantilla);

											if(lb_esConConstante)
											{
												switch(ls_plantilla)
												{
													case ConstanteCommon.PLANTILLA_CERTIFICADO_AMPLIACION_TRADICION:
														ls_idConstante = ConstanteCommon.TEXTO_AMPLIACION_TRADICION;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_ANTECEDENTE_REGISTRAL_FALSA_TRADICION:
														ls_idConstante = ConstanteCommon.TEXTO_ANTECEDENTE_REGISTRAL_FALSA_TRADICION;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_CARENCIA_DOMINIO_INCOMPLETO:
														ls_idConstante = ConstanteCommon.TEXTO_CARENCIA_DOMINIO_INCOMPLETO;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_CARENCIA_PLENO_DOMINIO:
														ls_idConstante = ConstanteCommon.TEXTO_CARENCIA_PLENO_DOMINIO;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_PERTENENCIA_PLENO_DOMINIO:
														ls_idConstante = ConstanteCommon.TEXTO_PERTENENCIA_PLENO_DOMINIO;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_PERTENENCIA_SIN_ANTECEDENTE:
														ls_idConstante = ConstanteCommon.TEXTO_PERTENENCIA_SIN_ANTECEDENTE;

														break;

													default:
														break;
												}
											}
											else
											{
												switch(ls_plantilla)
												{
													case ConstanteCommon.PLANTILLA_CERTIFICADO_CARENCIA_REGISTRAL:
														ls_idConstante = ConstanteCommon.TEXTO_CARENCIA_REGISTRAL;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_CLARIFICACION_TITULOS:
														ls_idConstante = ConstanteCommon.TEXTO_CLARIFICACION;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_NO_HALLAZGO_COMPLEMENTACION:
														ls_idConstante = ConstanteCommon.TEXTO_AMPLIACION_NO_HALLAZGO;

														break;

													case ConstanteCommon.PLANTILLA_CERTIFICADO_SERVIDUMBRE:
														ls_idConstante = ConstanteCommon.TEXTO_SERVIDUMBRE;

														break;

													default:
														break;
												}
											}

											if(lb_esConConstante)
											{
												lc_constante     = new Constantes(ls_idConstante);

												lc_constante = lcd_DAO.findById(lc_constante);
											}

											if((lc_constante != null) || !lb_esConConstante)
											{
												String ls_user;
												String ls_ip;
												String ls_texto;

												ls_user      = ac_c.getIdUsuarioCreacion();
												ls_ip        = ac_c.getIpCreacion();
												ls_texto     = ac_c.getTextoTag();

												lc_certificado = new Certificados();

												lc_certificado.setPlantillaSeleccionada(ls_plantilla);
												lc_certificado.setIdTurno(lth_turnoHistoria.getIdTurno());
												lc_certificado.setIdTurnoHistoria(
												    lth_turnoHistoria.getIdTurnoHistoria()
												);
												lc_certificado.setSolicitud(ac_c.getSolicitud());
												lc_certificado.setTagResuelvePantalla(ls_idConstante);

												if(ab_definitivo)
													lc_certificado.setTextoTag(ls_texto);

												lc_certificado.setIdUsuarioCreacion(ls_user);
												lc_certificado.setIpCreacion(ls_ip);

												generaCertificadosEspeciales(
												    lc_certificado, ab_firmaMasiva, ab_definitivo, ab_salvar,
												    adm_manager, al_idDocumentoSalidaExistente
												);

												lc_certificado.setNombreDocumento(lsp_subProceso.getNombre());

												if(!ab_definitivo)
												{
													if(lb_esConConstante)
														lc_certificado.setTextoTag(lc_constante.getTexto());
												}
												else if(!ab_firmaMasiva)
												{
													OficiosTexto    lot_oficios;
													OficiosTextoDAO lotd_DAO;
													boolean         lb_edicion;

													lot_oficios     = new OficiosTexto();
													lotd_DAO        = DaoCreator.getOficiosTextoDAO(adm_manager);
													lb_edicion      = false;

													lot_oficios.setTipo(ls_idSubproceso);
													lot_oficios.setIdTurnoHistoria(
													    lth_turnoHistoria.getIdTurnoHistoria()
													);

													lot_oficios = lotd_DAO.findByTurnoHistoriaTipo(lot_oficios);

													if(lot_oficios != null)
													{
														lb_edicion = true;
														lot_oficios.setIpModificacion(ls_ip);
														lot_oficios.setIdUsuarioModificacion(ls_user);
													}
													else
													{
														lot_oficios = new OficiosTexto();

														lot_oficios.setIpCreacion(ls_ip);
														lot_oficios.setIdUsuarioCreacion(ls_user);
														lot_oficios.setTipo(ls_idSubproceso);
														lot_oficios.setIdTurnoHistoria(
														    lth_turnoHistoria.getIdTurnoHistoria()
														);
													}

													{
														lot_oficios.setPlantilla(ls_plantilla);
														lot_oficios.setConsideracion(ls_texto);

														lotd_DAO.insertOrUpdate(lot_oficios, !lb_edicion);

														lc_certificado.setTextoTag(
														    ConversionTextos.convertirTextosFormatosCertificados(
														        ls_texto
														    )
														);
													}
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
							loa_args[0]     = ac_c.getIdTurno();
							throw new B2BException(ErrorKeys.ERROR_SIN_MATRICULA_ENVIAR_A_ANT_SISTEMA, loa_args);
						}
					}
				}
			}
		}

		return lc_certificado;
	}

	/**
	 * Guarda en base de datos la información suministrada por el usuario en la
	 * pestaña de datos del tramite
	 *
	 * @param ac_certificado
	 *            Objeto contenedor de la información de la solicitud del
	 *            certificado a tramitar
	 * @param as_userId
	 *            Id del usuario que ejecuta la acción
	 * @param as_remoteIp
	 *            Dirección IP del cliente que ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void guardarDatosTramite(Certificados ac_certificado, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ac_certificado == null)
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);

			Solicitud ls_solicitud;

			ls_solicitud = ac_certificado.getSolicitud();

			if(ls_solicitud != null)
			{
				String  ls_idSolicitud;
				long    ll_idMatriculaCertificado;
				String  ls_idCirculoCertificado;
				boolean lb_certificadosEspeciales;

				ls_idSolicitud                = ls_solicitud.getIdSolicitud();
				lb_certificadosEspeciales     = (StringUtils.isValidString(ls_idSolicitud)
						&& (!ls_idSolicitud.equalsIgnoreCase(SubProcesoCommon.CERTIFICADO_TRADICION_Y_LIBERTAD)
						|| !ls_idSolicitud.equalsIgnoreCase(
						    SubProcesoCommon.CERTIFICADO_PREDIAL_REGISTRA_SEDE_ELECTRONICA
						)));
				ll_idMatriculaCertificado     = 0L;
				ls_idCirculoCertificado       = null;

				{
					SolicitudDAO lsd_solicitudDAO;
					Solicitud    ls_solUpdate;

					lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(ldm_manager);
					ls_solUpdate         = lsd_solicitudDAO.findById(ls_solicitud);

					if(ls_solUpdate == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_ID_SOLICITUD);

					ls_solUpdate.setIdSubproceso(ls_solicitud.getIdSubproceso());
					ls_solUpdate.setIdTurnoAnt(ls_solicitud.getIdTurnoAnt());
					ls_solUpdate.setNirAsociado(ls_solicitud.getNirAsociado());
					ls_solUpdate.setTipoRegistroCertificado(ls_solicitud.getTipoRegistroCertificado());
					ls_solUpdate.setIdUsuarioModificacion(as_userId);
					ls_solUpdate.setIpModificacion(as_remoteIp);

					lsd_solicitudDAO.insertOrUpdate(ls_solUpdate, false);
				}

				{
					String ls_idTipoRequiereMatricula;

					ls_idTipoRequiereMatricula = StringUtils.getStringNotNull(
						    ac_certificado.getIdTipoRequiereMatricula()
						);

					if(
					    ls_idTipoRequiereMatricula.equals(TipoRequiereMatriculaCommon.CON_MATRICULA)
						    || ls_idTipoRequiereMatricula.equals(
						        TipoRequiereMatriculaCommon.DATOS_CON_MATRICULA_Y_SIN_MATRICULA
						    )
					)
					{
						Collection<SolicitudMatricula> lcsm_matriculas;

						lcsm_matriculas = ac_certificado.getMatriculas();

						if(CollectionUtils.isValidCollection(lcsm_matriculas))
						{
							SolicitudMatriculaDAO lsmd_solMatDAO;
							PredioRegistroDAO     lprd_predioRegistroDAO;

							lsmd_solMatDAO             = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
							lprd_predioRegistroDAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

							for(SolicitudMatricula lsm_data : lcsm_matriculas)
							{
								if(lsm_data != null)
								{
									{
										PredioRegistro lpr_predio;
										String         ls_idCirculo;
										long           ll_idMatricula;

										lpr_predio                    = new PredioRegistro();
										ls_idCirculo                  = lsm_data.getIdCirculo();
										ls_idCirculoCertificado       = lsm_data.getIdCirculo();
										ll_idMatricula                = lsm_data.getIdMatricula();
										ll_idMatriculaCertificado     = lsm_data.getIdMatricula();

										lpr_predio.setIdMatricula(ll_idMatricula);
										lpr_predio.setIdCirculo(ls_idCirculo);

										lpr_predio = lprd_predioRegistroDAO.findById(lpr_predio);

										if(lpr_predio != null)
										{
											String   ls_estadoPredio;
											String   ls_predioInconsistente;
											Object[] loa_args;

											ls_estadoPredio            = lpr_predio.getIdEstadoPredio();
											ls_predioInconsistente     = lpr_predio.getPredioInconsistente();
											loa_args                   = new String[1];

											loa_args[0] = ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION
												+ ll_idMatricula;

											if(StringUtils.isValidString(ls_estadoPredio))
											{
												if(ls_estadoPredio.equals(EstadoCommon.A))
													throw new B2BException(
													    ErrorKeys.MATRICULA_ESTADO_ANULADO, loa_args
													);
												else if(ls_estadoPredio.equals(EstadoCommon.T))
													throw new B2BException(
													    ErrorKeys.MATRICULA_ESTADO_TRASLADADO, loa_args
													);
											}

											if(
											    StringUtils.isValidString(ls_predioInconsistente)
												    && ls_predioInconsistente.equals(EstadoCommon.S)
												    && ac_certificado.isEsCertificadosMasivos()
											)
												throw new B2BException(
												    ErrorKeys.MATRICULA_ESTADO_INCONSISTENTE, loa_args
												);
										}
										else
										{
											Object[] loa_args;

											loa_args     = new String[2];

											loa_args[0]     = ls_idCirculo;
											loa_args[1]     = StringUtils.getString(ll_idMatricula);

											throw new B2BException(ErrorKeys.ERROR_MATRICULA_NO_VALIDA, loa_args);
										}
									}

									lsm_data.setIdSolicitud(ls_idSolicitud);

									SolicitudMatricula lsm_tmp;

									lsm_tmp = lsmd_solMatDAO.findById(lsm_data);

									if(lsm_tmp != null)
									{
										lsm_tmp.setIdUsuarioModificacion(as_userId);
										lsm_tmp.setIpModificacion(as_remoteIp);

										lsmd_solMatDAO.insertOrUpdate(lsm_tmp, false);
									}
									else
									{
										lsm_data.setExpedirCertificado(EstadoCommon.S);
										lsm_data.setEstado(EstadoCommon.A);
										lsm_data.setIdUsuarioCreacion(as_userId);
										lsm_data.setIpCreacion(as_remoteIp);

										lsmd_solMatDAO.insertOrUpdate(lsm_data, true);
									}
								}
							}
						}
					}

					if(
					    ls_idTipoRequiereMatricula.equals(TipoRequiereMatriculaCommon.DATOS_ANTIGUO_SISTEMA)
						    || ls_idTipoRequiereMatricula.equals(
						        TipoRequiereMatriculaCommon.DATOS_CON_MATRICULA_Y_SIN_MATRICULA
						    )
					)
					{
						Collection<DatosAntSistema> lcdas_datosAnt;
						DatosAntSistemaDAO          ldasd_datosAntDAO;
						DetalleAntSistemaDAO        ldasd_detalleAntDAO;

						lcdas_datosAnt          = ac_certificado.getDatosAntiguoSistema();
						ldasd_datosAntDAO       = DaoCreator.getDatosAntSistemaDAO(ldm_manager);
						ldasd_detalleAntDAO     = DaoCreator.getDetalleAntSistemaDAO(ldm_manager);

						if(CollectionUtils.isValidCollection(lcdas_datosAnt))
						{
							for(DatosAntSistema ldas_predio : lcdas_datosAnt)
							{
								if(ldas_predio != null)
								{
									ldas_predio = ldasd_datosAntDAO.findById(ldas_predio);

									if(ldas_predio != null)
									{
										String ls_adquisicionPredio;

										ls_adquisicionPredio = StringUtils.getStringNotNull(
											    ldas_predio.getAdquisicionPredio()
											);

										if(
										    ls_adquisicionPredio.equalsIgnoreCase(
											        IdentificadoresCommon.ADQUISICION_ANT_SISTEMA
											    )
										)
										{
											DetalleAntSistema             ldas_detalleTmp;
											Collection<DetalleAntSistema> ldas_detalles;

											ldas_detalleTmp = new DetalleAntSistema();

											ldas_detalleTmp.setIdDatosAntSistema(ldas_predio.getIdDatosAntSistema());

											ldas_detalles = ldasd_detalleAntDAO.findByDatosAntSis(ldas_detalleTmp);

											if(!CollectionUtils.isValidCollection(ldas_detalles))
											{
												Object[] loa_args;
												String   ls_nombrePredio;

												loa_args            = new String[2];
												ls_nombrePredio     = ldas_predio.getNombrePredio();

												loa_args[0]     = StringUtils.isValidString(ls_nombrePredio)
													? ls_nombrePredio : EstadoCommon.SIN_NOMBRE;
												loa_args[1]     = ldas_predio.getIdCirculo();

												throw new B2BException(
												    ErrorKeys.ERROR_SIN_DETALLES_ANT_SISTEMA, loa_args
												);
											}
										}
									}
								}
							}
						}

						{
							DatosAntSistema ldas_datosAntSistema;

							ldas_datosAntSistema = ac_certificado.getDatosAntiguoSistemaNuevaEntrada();

							if(ldas_datosAntSistema != null)
							{
								DatosAntSistema               ldas_datosEncontrados;
								Collection<DetalleAntSistema> lcdas_detalleAntSis;

								lcdas_detalleAntSis       = ac_certificado.getDetalleAntSistemaNuevaEntrada();
								ldas_datosEncontrados     = ldasd_datosAntDAO.findById(ldas_datosAntSistema);

								if(ldas_datosEncontrados != null)
								{
									ldas_datosEncontrados.setIdSolicitud(ls_idSolicitud);
									ldasd_datosAntDAO.insertOrUpdate(ldas_datosEncontrados, false);
								}
								else
								{
									ldas_datosAntSistema.setIdSolicitud(ls_idSolicitud);
									ldasd_datosAntDAO.insertOrUpdate(ldas_datosAntSistema, true);
								}

								if(CollectionUtils.isValidCollection(lcdas_detalleAntSis))
								{
									for(DetalleAntSistema ldas_tmp : lcdas_detalleAntSis)
									{
										if(ldas_tmp != null)
										{
											DetalleAntSistema ldas_encontrado;

											ldas_encontrado = ldasd_detalleAntDAO.findByDetalleYDatosAntSis(ldas_tmp);

											if(ldas_encontrado != null)
												ldasd_detalleAntDAO.insertOrUpdate(ldas_tmp, false);
											else
												ldasd_detalleAntDAO.insertOrUpdate(ldas_tmp, true);
										}
									}
								}
							}
						}
					}
				}

				{
					Persona lp_persona;

					lp_persona = ac_certificado.getPersona();

					if(lp_persona != null)
					{
						String ls_idTipoDoc;
						String ls_idTipoPersona;

						ls_idTipoDoc = lp_persona.getIdDocumentoTipo();

						if(!StringUtils.isValidString(ls_idTipoDoc))
							throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);

						if(ls_idTipoDoc.equals(IdentificadoresCommon.NIT))
							ls_idTipoPersona = EstadoCommon.J;
						else
							ls_idTipoPersona = EstadoCommon.N;

						lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						lp_persona.setIdTipoPersona(ls_idTipoPersona);
						lp_persona.setIdUsuarioCreacion(as_userId);
						lp_persona.setIpCreacion(as_remoteIp);

						String ls_idFlagPersona;

						ls_idFlagPersona = marcarFlagPersona(ldm_manager, lp_persona, as_userId, as_remoteIp);

						if(StringUtils.isValidString(ls_idFlagPersona))
							lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(lp_persona);

						if(lp_persona == null)
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_PERSONA);

						SolicitudInterviniente lsi_interviniente;

						lsi_interviniente = new SolicitudInterviniente();

						lsi_interviniente.setIdPersona(lp_persona.getIdPersona());
						lsi_interviniente.setIdSolicitud(ls_idSolicitud);
						lsi_interviniente.setIdUsuarioCreacion(as_userId);
						lsi_interviniente.setIpCreacion(as_remoteIp);

						DaoCreator.getSolicitudIntervinienteDAO(ldm_manager).insertOrUpdate(lsi_interviniente, true);
					}
				}

				{
					SolicitudDireccionCertificado lsdc_direccion;

					lsdc_direccion = ac_certificado.getDireccion();

					if(lsdc_direccion != null)
					{
						lsdc_direccion.setIdUsuarioCreacion(as_userId);
						lsdc_direccion.setIpCreacion(as_remoteIp);
						lsdc_direccion.setIdSolicitud(ls_idSolicitud);

						if(
						    lb_certificadosEspeciales && StringUtils.isValidString(ls_idCirculoCertificado)
							    && (ll_idMatriculaCertificado > 0L)
						)
						{
							lsdc_direccion.setIdCirculo(ls_idCirculoCertificado);
							lsdc_direccion.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatriculaCertificado));
						}

						DaoCreator.getSolicitudDireccionCertificadoDAO(ldm_manager).insert(lsdc_direccion);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("guardarDatosTramite", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Verifica si el número de anotaciones de un predio es mayor al permitido.
	 *
	 * @param as_idCirculo            id del círculo al que pertenece el predio
	 * @param al_idMatricula correspondiente al valor de al id matricula
	 * @return true si el número de anotaciones supera el limite establecido
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized boolean validarNumeroAnotacionesPredioInconsistente(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		DAOManager ldm_manager;
		boolean    lb_return;

		ldm_manager     = DaoManagerFactory.getDAOManager();
		lb_return       = false;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong((al_idMatricula)))
			{
				AnotacionPredio             lap_anotacionPredio;
				Collection<AnotacionPredio> lcap_anotaciones;

				lap_anotacionPredio = new AnotacionPredio();

				lap_anotacionPredio.setIdCirculo(as_idCirculo);
				lap_anotacionPredio.setIdMatricula(al_idMatricula);

				lcap_anotaciones = DaoCreator.getAnotacionPredioDAO(ldm_manager)
						                         .findByCirculoMatricula(lap_anotacionPredio, true);

				if(CollectionUtils.isValidCollection(lcap_anotaciones) && (lcap_anotaciones.size() > 150))
					lb_return = true;
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarNumeroAnotacionesPredioInconsistente", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lb_return;
	}

	/**
	 * Verifica si el número es par o impar y si corresponde al tipo partida ingresado.
	 *
	 * @param as_tipoPartida tipo de partida ingresado
	 * @param al_numeroPartida el número de partida ingresado
	 * @return verdadero si ambos corresponden, de lo contario, retorna falso.
	 * @throws B2BException
	 */
	public boolean verificarParImpar(String as_tipoPartida, Long al_numeroPartida)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(StringUtils.isValidString(as_tipoPartida) && NumericUtils.isValidLong(al_numeroPartida))
			{
				long ll_modulo;

				ll_modulo = NumericUtils.getLong(al_numeroPartida) % 2;

				if(
				    (as_tipoPartida.equalsIgnoreCase("PAR") && (ll_modulo == 0))
					    || (as_tipoPartida.equalsIgnoreCase("IMPAR") && (ll_modulo != 0))
				)
					lb_return = true;
			}
		}
		catch(Exception lb2be_e)
		{
			clh_LOGGER.error("verificarParImpar", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}
}
