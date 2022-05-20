package com.bachue.snr.prosnr01.business.firma;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.arcgis.json.ClienteJSON;
import com.bachue.prosnr01.integracion.cliente.arcgis.json.ImagenPdf;
import com.bachue.prosnr01.integracion.cliente.owcc.consultarDocumentos.ClienteObtenerArchivo;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.MarcaAgua;
import com.bachue.snr.prosnr01.business.actuaciones.administrativas.ActuacionesAdministrativasBusiness;
import com.bachue.snr.prosnr01.business.calificacion.SuspensionBusiness;
import com.bachue.snr.prosnr01.business.devolucionesDinero.DevolucionDineroBusiness;
import com.bachue.snr.prosnr01.business.segundaInstancia.SegundaInstanciaBusiness;
import com.bachue.snr.prosnr01.business.testamentos.TestamentosBusiness;
import com.bachue.snr.prosnr01.business.utilidades.PDFGenerator;

import com.bachue.snr.prosnr01.common.constants.AlertaTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.BookMarkCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MayorValorCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.MotivonNoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TagCommon;
import com.bachue.snr.prosnr01.common.constants.TipoActoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoAreaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccCausalRechazoRecursoDAO;
import com.bachue.snr.prosnr01.dao.acc.AlertaTurnoTramiteDAO;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.DetalleCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.FirmaMasivaDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroMayorValorDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudMatriculaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;
import com.bachue.snr.prosnr01.dao.acc.UsuariosFirmaDAO;
import com.bachue.snr.prosnr01.dao.bgn.ImagenesDAO;
import com.bachue.snr.prosnr01.dao.bng.AnotacionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DetalleAreaPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.DireccionPredioDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.htr.EstadoPredioDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MedidaAreaDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.numeracion.NumeracionResolucionCirculo;
import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudReproduccion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.FirmaMasiva;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroMayorValor;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTraslado;
import com.bachue.snr.prosnr01.model.sdb.acc.SoporteTrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Testamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuariosFirma;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioDireccion;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.Departamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MedidaArea;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.Municipio;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.Vereda;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.SimpleBookmark;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.net.URL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Clase para el manejo del negocio de firma masiva.
 *
 * @author Heiner Castañeda
 */
public class FirmaMasivaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(FirmaMasivaBusiness.class);

	/**
	 * Método de transacciones con la base de datos para encontrar la posicion de un bookmark específico en un documento.
	 *
	 * @param mBM lista de bookmarks en un documento
	 * @param sCad nombre del bookmark a buscar
	 * @param iIncrX incremento de compensacion en el eje x
	 * @param iIncrY incremento de compensacion en el eje y
	 * @return el valor de int[]
	 */
	@SuppressWarnings("rawtypes")
	public int[] PosicionBookmark(Map mBM, String sCad, int iIncrX, int iIncrY)
	{
		int[] iRet = new int[5];
		iRet[0] = 0;

		ArrayList alKids = (ArrayList)mBM.get("Kids");

		if(alKids != null)
		{
			for(int i = 0; i < alKids.size(); i++)
			{
				iRet = PosicionBookmark((Map)alKids.get(i), sCad, iIncrX, iIncrY);

				if(iRet[0] == 2)
					return iRet;
			}
		}

		if(((String)mBM.get("Title")).trim().equalsIgnoreCase(sCad))
		{
			String[] asPar = ((String)mBM.get("Page")).split(" ");
			iRet[0]     = 2;
			iRet[1]     = (int)Float.parseFloat(asPar[2]) + iIncrX;
			iRet[2]     = (int)Float.parseFloat(asPar[3]) + iIncrY;
			iRet[3]     = (int)Float.parseFloat(asPar[4]);
			iRet[4]     = (int)Float.parseFloat(asPar[0]);
		}

		return iRet;
	}

	/**
	 * Metodo de transacciones con la base de datos con el fin de encontrar las firmas existentes
	 * en la tabla SDB_ACC_FIRMA_MASIVA.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmaMasiva(String as_remoteIp)
	    throws B2BException
	{
		Collection<FirmaMasiva> lcfm_masiva;
		DAOManager              ldm_manager;

		lcfm_masiva     = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = lcd_constantesDAO.findById(ConstanteCommon.JOB_FIRMA_MASIVA_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_limiteIntentos;

				ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_FIRMA_MASIVA_LIMITE_INTENTOS
					);

				lcfm_masiva = DaoCreator.getFirmaMasivaDAO(ldm_manager)
						                    .searchAllByTipoFirma(new FirmaMasiva(), ll_limiteIntentos);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("firmaMasiva", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcfm_masiva))
			firmaMasiva(lcfm_masiva, as_remoteIp);
	}

	/**
	 * Método de transacciones con la base de datos para firmar masivamente.
	 *
	 * @param acfm_parametros colecccion que se va a firmar
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmaMasiva(Collection<FirmaMasiva> acfm_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_FIRMA_MASIVA_BLOQUEO;
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

				clh_LOGGER.error("firmaMasiva", lb2be_e);

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

				clh_LOGGER.error("firmaMasiva", lb2be_e);

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
					if(CollectionUtils.isValidCollection(acfm_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;
						TurnoHistoriaDAO   lthd_turnoHistoriaDAO;

						lbpd_bitacora             = DaoCreator.getBitacoraProcesoDAO(ldm_manager);
						lthd_turnoHistoriaDAO     = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

						ldm_manager.setAutoCommit(true);

						for(FirmaMasiva lfm_iterador : acfm_parametros)
						{
							if(lfm_iterador != null)
							{
								TurnoHistoria lth_turno;

								lth_turno = lthd_turnoHistoriaDAO.findById(
									    NumericUtils.getLongWrapper(lfm_iterador.getLlave1())
									);

								if(lth_turno != null)
								{
									String ls_mensaje;

									ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

									try
									{
										int li_tipoFirma;

										li_tipoFirma = lfm_iterador.getTipoFirma();

										if((li_tipoFirma == FirmaMasiva.TIPO_FIRMA_1))
											firmaMasivaAprobacion(
											    lfm_iterador, lth_turno, lbpd_bitacora, as_remoteIp, ls_userId
											);

										lth_turno.setFechaExitoEjecucionAutomatica(new Date());
									}
									catch(B2BException lb2be_e)
									{
										clh_LOGGER.error("firmaMasiva", lb2be_e);

										ls_mensaje = getErrorMessage(lb2be_e);
									}

									actualizarIntentoEnvio(lth_turno, ls_mensaje, ls_userId, as_remoteIp, ldm_manager);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("firmaMasiva", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("firmaMasiva", lb2be_e);

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

					clh_LOGGER.error("firmaMasiva", lb2be_e);

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
	 * Método de transacciones con la base de datos para aprobar por medio de firma masiva un documento.
	 *
	 * @param afm_parametros  objeto de tipo firma masiva para su aprobacion
	 * @param ath_turnoHistoria de ath turno historia
	 * @param abpd_DAO objeto a insertar en la base de datos
	 * @param as_remoteIp de as remote ip
	 * @param as_usuarioProceso de as usuario proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmaMasivaAprobacion(
	    FirmaMasiva afm_parametros, TurnoHistoria ath_turnoHistoria, BitacoraProcesoDAO abpd_DAO, String as_remoteIp,
	    String as_usuarioProceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_TURNO_HISTORIA);

			if(afm_parametros == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			String ls_usuario;

			ls_usuario = afm_parametros.getUsuario();

			{
				Usuario lu_usuario;

				lu_usuario = null;

				if(StringUtils.isValidString(ls_usuario))
				{
					lu_usuario = new Usuario();
					lu_usuario.setIdUsuario(ls_usuario);
					lu_usuario = DaoCreator.getUsuarioDAO(ldm_manager).findById(lu_usuario);
				}
				else
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_usuario;

					throw new B2BException(ErrorKeys.ERROR_USUARIO_NO_ENCONTRADO);
				}
			}

			{
				UsuariosFirmaDAO lfufd_DAO;
				UsuariosFirma    lfuf_parametros;

				lfufd_DAO           = DaoCreator.getUsuariosFirmaDAO(ldm_manager);
				lfuf_parametros     = new UsuariosFirma();

				lfuf_parametros.setIdUsuario(ls_usuario);

				lfuf_parametros = lfufd_DAO.searchByUser(lfuf_parametros);
			}

			{
				Long                ll_turnoHistoria;
				String              ls_motivoNoTramite;
				TurnoHistoriaDAO    lthd_DAO;
				SolicitudDAO        lsd_DAO;
				DocumentosSalidaDAO ldsd_DAO;
				ConstantesDAO       lcd_DAO;

				ll_turnoHistoria       = ath_turnoHistoria.getIdTurnoHistoria();
				ls_motivoNoTramite     = ath_turnoHistoria.getMotivoNoTramite();
				lthd_DAO               = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				lsd_DAO                = DaoCreator.getSolicitudDAO(ldm_manager);
				ldsd_DAO               = DaoCreator.getDocumentosSalidaDAO(ldm_manager);
				lcd_DAO                = DaoCreator.getConstantesDAO(ldm_manager);

				if(StringUtils.isValidString(ls_motivoNoTramite))
				{
					Solicitud lso_solicitud;
					String    ls_idSolicitud;

					lso_solicitud      = new Solicitud();
					ls_idSolicitud     = ath_turnoHistoria.getIdSolicitud();

					lso_solicitud.setIdSolicitud(ls_idSolicitud);

					lso_solicitud = lsd_DAO.findById(lso_solicitud);

					if(lso_solicitud != null)
					{
						Subproceso lsp_subproceso;
						String     ls_idProceso;
						String     ls_idSubproceso;
						String     ls_idAutorizaNotificacion;

						lsp_subproceso      = new Subproceso();
						ls_idProceso        = StringUtils.getStringNotNull(lso_solicitud.getIdProceso());
						ls_idSubproceso     = StringUtils.getStringNotNull(lso_solicitud.getIdSubproceso());

						ls_idAutorizaNotificacion = lso_solicitud.getIdAutorizacionNotificacion();

						lsp_subproceso.setIdProceso(ls_idProceso);
						lsp_subproceso.setIdSubproceso(ls_idSubproceso);

						lsp_subproceso = DaoCreator.getSubprocesoDAO(ldm_manager).findById(lsp_subproceso);

						if(StringUtils.isValidString(ls_idAutorizaNotificacion))
						{
							BigDecimal    lbd_idAnterior;
							boolean       lb_cancelacionAutomatica;
							boolean       lb_copias;
							boolean       lb_correoElectronico;
							boolean       lb_direccionResidencia;
							boolean       lb_direccionCorrespondencia;
							boolean       lb_orip;
							long          ll_idMotivoAnt;
							long          ll_idMotivoActual;
							long          ll_idEtapa;
							TurnoHistoria lth_turnoHistoriaAnt;

							lbd_idAnterior                  = ath_turnoHistoria.getIdAnterior();
							ll_idMotivoActual               = NumericUtils.getLong(ath_turnoHistoria.getIdMotivo());
							lth_turnoHistoriaAnt            = new TurnoHistoria(lbd_idAnterior);
							ll_idMotivoAnt                  = 0L;
							ll_idEtapa                      = 0L;
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

							lth_turnoHistoriaAnt = lthd_DAO.findById(lth_turnoHistoriaAnt);

							if(lth_turnoHistoriaAnt != null)
							{
								Long       lL_idMotivo;
								BigDecimal lbd_idEtapa;

								lL_idMotivo     = lth_turnoHistoriaAnt.getIdMotivo();
								lbd_idEtapa     = lth_turnoHistoriaAnt.getIdEtapa();

								if(lL_idMotivo != null)
									ll_idMotivoAnt = NumericUtils.getLong(lL_idMotivo);

								if(lbd_idEtapa != null)
									ll_idEtapa = NumericUtils.getLong(lbd_idEtapa);
							}

							lb_cancelacionAutomatica     = ((ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
									&& (ll_idMotivoAnt == MotivoTramiteCommon.REALIZAR_REGISTRO_CANCELACION_AUTOMATICA));

							lb_copias = StringUtils.isValidString(ls_idProceso)
									&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5);

							if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS)
							{
								VolverAEnviarDocumentosOWCC(ath_turnoHistoria, ls_usuario, as_remoteIp, ldm_manager);

								long ll_idMotivo;

								if(ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_APERTURA_DE_PRUEBAS)
									ll_idMotivo = MotivoTramiteCommon.REVISION_AUTO_DE_PRUEBAS_APROBADA_POR_COORDINADOR_JURIDICO;
								else
									ll_idMotivo = MotivoTramiteCommon.REVISION_RESOLUCION_APROBADA_POR_COORDINADOR_JURIDICO;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
								    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
								    && (ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_14)
								    || ls_idSubproceso.equalsIgnoreCase(
								        ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_AMPLIACION
								    )
								    || ls_idSubproceso.equalsIgnoreCase(
								        ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_PERTENENCIA
								    )
								    || ls_idSubproceso.equalsIgnoreCase(
								        ProcesoCommon.ID_SUBPROCESO_CERTIFICADO_SERVIDUMBRE
								    ))
							)
							{
								getAntiguoSistemaBusiness()
									    .generarPdfSolicitudComplementacionOfiDestino(
									    StringUtils.getString(lth_turnoHistoriaAnt.getIdTurnoHistoria()), false,
									    ls_usuario, as_remoteIp
									);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA,
								        MotivoTramiteCommon.APROBADOR_CIRCULO_DESTINO
								    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if((ll_idEtapa == EtapaCommon.ID_ETAPA_BUSCADOR_AS_CIRCULO_DESTINO))
							{
								getAntiguoSistemaBusiness()
									    .generarPdfSolicitudComplementacionOfiDestino(
									    StringUtils.getString(lth_turnoHistoriaAnt.getIdTurnoHistoria()), false,
									    ls_usuario, as_remoteIp
									);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        EtapaCommon.SOLICITAR_COMPLEMENTACION_OTRO_CICRCULO,
								        MotivoTramiteCommon.APROBADO_CIRCULO_DESTINO
								    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_PENDIENTE_DE_PAGO)
								    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
								    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
							)
								getRegistroBusiness()
									    .generarComunicadoRespuestaSolicitudExenta(
									    ldm_manager, lso_solicitud, ath_turnoHistoria, true, as_usuarioProceso,
									    as_remoteIp
									);
							else if(
							    (ll_idEtapa == EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
								    || (ll_idEtapa == EtapaCommon.APROBACION_RESOLUCION)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_REVISION_JURIDICA_FASE_TRASLADOS)
							)
							{
								ActuacionesAdministrativasBusiness laab_aaBusiness;
								OficiosTexto                       lot_oficiosTexto;

								laab_aaBusiness      = getActuacionesAdministrativasBusiness();
								lot_oficiosTexto     = new OficiosTexto();

								lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());
								lot_oficiosTexto.setProyectar(true);

								laab_aaBusiness.generarDocumentoAutoResolucion(
								    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false, false,
								    false, true, ldm_manager
								);

								VolverAEnviarDocumentosOWCC(ath_turnoHistoria, ls_usuario, as_remoteIp, ldm_manager);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        ll_idEtapa,
								        MotivoTramiteCommon.APROBACION_RESOLUCION_CREACION_SUPRESION_MODIFICACION
								    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_SOLICITUD_VISITAS)
								    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_62)
							)
							{
								boolean lb_auto;

								lb_auto = ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
										|| ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2);

								{
									DocumentosSalida lds_documento;

									lds_documento = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                      .findLastByIdSolicitudTipo(
											    ls_idSolicitud,
											    (lb_auto ? TipoDocumentalCommon.AUTO : TipoDocumentalCommon.RESOLUCION),
											    true
											);

									if(lds_documento != null)
									{
										Long ll_idImagen;

										ll_idImagen = lds_documento.getIdImagen();

										if(NumericUtils.isValidLong(ll_idImagen))
										{
											Imagenes li_imagen;

											li_imagen = DaoCreator.getImagenesDAO(ldm_manager)
													                  .findById(NumericUtils.getLong(ll_idImagen));

											if(li_imagen != null)
											{
												byte[] lba_imagen;

												lba_imagen = li_imagen.getImagenBlob();

												if(lba_imagen != null)
												{
													String ls_resolucion;

													ls_resolucion = null;

													{
														NumeracionResolucionCirculo lnrc_numeracionCirculo;

														lnrc_numeracionCirculo = findNumeracionResolucionCirculo(
															    ath_turnoHistoria, ldm_manager, as_usuarioProceso,
															    as_remoteIp
															);

														if(lnrc_numeracionCirculo != null)
															ls_resolucion = lnrc_numeracionCirculo
																	.getConsecutivogenerado();
													}

													if(StringUtils.isValidString(ls_resolucion))
													{
														lba_imagen     = getFirmaMasivaBusiness()
																                 .reemplazarBookmarsFirma(
																    lba_imagen,
																    MarcaAgua.crearImagenConTexto(
																        ls_resolucion, ls_resolucion.length() * 9, 12,
																        12
																    ), 0, 0, BookMarkCommon.RESOLUCION, false
																);

														lba_imagen = getFirmaMasivaBusiness()
																             .reemplazarBookmarsFirma(
																    lba_imagen,
																    MarcaAgua.crearImagenConTexto(
																        lcd_DAO.findString(
																            ConstanteCommon.TAG_FECHA_RESOL
																        ), ls_resolucion.length() * 9, 12, 12
																    ), 0, 0, BookMarkCommon.FECHA_RESOL, false
																);
														guardarDocumentoSalidaVisitas(
														    lba_imagen, as_usuarioProceso, as_remoteIp, lb_auto,
														    ls_idSolicitud, true, ldm_manager, ath_turnoHistoria
														);
													}

													terminarTurnoHistoriaYCrearEtapa(
													    ath_turnoHistoria, ldm_manager,
													    new MotivoTramite(
													        EtapaCommon.ID_ETAPA_SOLICITUD_VISITAS,
													        lb_auto ? MotivoTramiteCommon.GENERACION_AUTO_VISITAS
													                : MotivoTramiteCommon.GENERACION_RESOLUCION_VISITAS
													    ), as_usuarioProceso, as_remoteIp, EstadoCommon.TERMINADA, true,
													    true, true, true
													);

													DaoCreator.getFirmaMasivaDAO(ldm_manager)
														          .deleteFirmaMasiva(afm_parametros);
												}
											}
										}
									}
								}
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_DELEGADO_REGISTRO)
								    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_62)
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ath_turnoHistoria.getIdUsuarioCreacion(), as_remoteIp,
								    ldm_manager
								);
								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE,
								        MotivoTramiteCommon.RESOLUCION_APROBADA_SUPERINTENDENTE
								    ), as_usuarioProceso, as_remoteIp, EstadoCommon.TERMINADA, true, true, true, true
								);
								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(lb_copias)
							{
								boolean lb_informacionDigitalizada;
								boolean lb_informacionPendientePago;
								boolean lb_estamparMarcaAgua;

								lb_informacionDigitalizada      = ((ll_idEtapa == EtapaCommon.ID_ETAPA_20)
										&& (ll_idMotivoAnt == MotivoTramiteCommon.COPIAS_CON_INFORMACION_DIGITALIZADAS));
								lb_informacionPendientePago     = ((ll_idEtapa == EtapaCommon.VALIDACION_INFORMACION_EN_OWCC)
										&& (ll_idMotivoAnt == MotivoTramiteCommon.CON_INFORMACION_EN_OWCC_PENDIENTE_PAGO));

								lb_estamparMarcaAgua = lb_informacionDigitalizada || lb_informacionPendientePago;

								if(lb_estamparMarcaAgua)
								{
									Collection<SolicitudCopias> lcsc_solicitudCopias;

									lcsc_solicitudCopias = DaoCreator.getSolicitudCopiasDAO(ldm_manager)
											                             .findByIdSolicitud(ls_idSolicitud);

									if(CollectionUtils.isValidCollection(lcsc_solicitudCopias))
									{
										DetalleCriterioBusquedaDAO ldcbd_DAO;

										CirculoRegistralDao        lcrd_DAO;
										TurnoDAO                   ltd_DAO;
										ImagenesDAO                li_DAO;
										MotivoTramiteDAO           lmtd_DAO;
										ProcedimientosDAO          lpd_DAO;
										FirmaMasivaDAO             lfmd_DAO;

										ldcbd_DAO     = DaoCreator.getDetalleCriterioBusquedaDAO(ldm_manager);

										lcrd_DAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);
										ltd_DAO      = DaoCreator.getTurnoDAO(ldm_manager);
										li_DAO       = DaoCreator.getImagenesDAO(ldm_manager);
										lmtd_DAO     = DaoCreator.getMotivoTramiteDAO(ldm_manager);
										lpd_DAO      = DaoCreator.getProcedimientosDAO(ldm_manager);
										lfmd_DAO     = DaoCreator.getFirmaMasivaDAO(ldm_manager);

										for(SolicitudCopias lsc_iterador : lcsc_solicitudCopias)
										{
											if(lsc_iterador != null)
											{
												Constantes lc_constante;

												lc_constante = lcd_DAO.findById(
													    ConstanteCommon.OBTENER_ARCHIVO_OWCC_ENDPOINT
													);

												if(lc_constante != null)
												{
													byte[] lba_archivo;

													lba_archivo = ClienteObtenerArchivo.obtenerArchivo(
														    lsc_iterador.getIdEcmOriginal(),
														    lsc_iterador.getDocNameOriginal(),
														    lc_constante.getCaracter()
														);

													Constantes lc_constanteTexto;

													lc_constanteTexto = null;

													String ls_tipoRecepcion;
													ls_tipoRecepcion = lso_solicitud.getIdTipoRecepcion();

													if(lba_archivo != null)
													{
														Collection<CriteriosDeBusqueda> lccdb_detalleCriterioBusqueda;

														lccdb_detalleCriterioBusqueda = ldcbd_DAO.findByIdSolicitud(
															    ls_idSolicitud
															);

														if(
														    CollectionUtils.isValidCollection(
															        lccdb_detalleCriterioBusqueda
															    )
														)
														{
															CriteriosDeBusqueda lcdb_detalleCriterioBusqueda;

															lcdb_detalleCriterioBusqueda = lccdb_detalleCriterioBusqueda.iterator()
																	                                                        .next();

															if(lcdb_detalleCriterioBusqueda != null)
															{
																String ls_idTipoCriterioBusqueda;

																ls_idTipoCriterioBusqueda = lcdb_detalleCriterioBusqueda
																		.getIdTipoCriterio();

																if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
																	lc_constanteTexto = lcd_DAO.findById(
																		    ls_idTipoCriterioBusqueda.equalsIgnoreCase(
																		        TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
																		    )
																		    ? ConstanteCommon.TEXTO_MARCA_DE_AGUA_ANTIGUO_SISTEMA
																		    : ConstanteCommon.TEXTO_MARCA_DE_AGUA
																		);
															}
														}
														else if(
														    StringUtils.isValidString(ls_tipoRecepcion)
															    && ls_tipoRecepcion.equals("8")
														)
															lc_constanteTexto = lcd_DAO.findById(
																    ConstanteCommon.TEXTO_MARCA_DE_AGUA
																);

														if(lc_constanteTexto != null)
														{
															String ls_texto;

															ls_texto = lc_constanteTexto.getCaracter();

															if(StringUtils.isValidString(ls_texto))
															{
																Turno  lt_turno;
																String ls_idTurno;
																Long   ll_idTurnoHistoria;

																ls_idTurno             = ath_turnoHistoria.getIdTurno();
																ll_idTurnoHistoria     = ath_turnoHistoria
																		.getIdTurnoHistoria();

																lt_turno = ltd_DAO.findById(ls_idTurno);

																if(lt_turno != null)
																{
																	CirculoRegistral lcr_circuloRegistral;

																	lcr_circuloRegistral = lcrd_DAO.findById(
																		    lt_turno.getIdCirculo()
																		);

																	if(lcr_circuloRegistral != null)
																	{
																		byte[] lba_archivoMarcaAgua;

																		lba_archivoMarcaAgua = MarcaAgua
																				.estamparMarcaDeAgua(
																				    lba_archivo,
																				    ls_texto.replace(
																				        "XXXXXX",
																				        lcr_circuloRegistral
																				        .getNombreCirculoRegistral()
																				    ), 600, 850
																				);

																		{
																			Imagenes li_imagenes;

																			DocumentosSalida lds_documentosSalida;
																			long     ll_secuencia;

																			li_imagenes              = new Imagenes();
																			lds_documentosSalida     = new DocumentosSalida();

																			li_imagenes.setImagenBlob(
																			    lba_archivoMarcaAgua
																			);

																			li_imagenes.setTipoArchivo(
																			    ExtensionCommon.PDF_MAYUS
																			);
																			li_imagenes.setIdUsuarioCreacion(
																			    as_usuarioProceso
																			);
																			li_imagenes.setIpCreacion(as_remoteIp);
																			li_imagenes.setIdTurno(ls_idTurno);
																			li_imagenes.setCodigoVerificacion(
																			    li_DAO.generarCodigoConvenio(
																			        ll_idTurnoHistoria
																			    )
																			);

																			ll_secuencia = li_DAO.insertOrUpdate(
																				    li_imagenes, true, false
																				);

																			if(ll_secuencia <= 0)
																				throw new B2BException(
																				    ErrorKeys.NO_INSERTO_IMAGENES
																				);

																			lds_documentosSalida.setIdImagen(
																			    NumericUtils.getLongWrapper(
																			        ll_secuencia
																			    )
																			);
																			lds_documentosSalida.setTipo(
																			    TipoArchivoCommon.COPIA
																			);
																			lds_documentosSalida.setEstado(
																			    EstadoCommon.ACTIVO
																			);
																			lds_documentosSalida.setIdSolicitud(
																			    ls_idSolicitud
																			);
																			lds_documentosSalida.setIdTurno(ls_idTurno);
																			lds_documentosSalida.setIdTurnoHistoria(
																			    NumericUtils.getInteger(
																			        ll_idTurnoHistoria
																			    )
																			);
																			lds_documentosSalida.setIdTipoDocumental(
																			    lsc_iterador.getIdTipoDocumental()
																			);
																			lds_documentosSalida.setRepositorio(
																			    RepositorioCommon.TEMPORAL
																			);
																			lds_documentosSalida.setIdUsuarioCreacion(
																			    as_usuarioProceso
																			);
																			lds_documentosSalida.setIpCreacion(
																			    as_remoteIp
																			);

																			ldsd_DAO.insertOrUpdate(
																			    lds_documentosSalida, true
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

										if(ath_turnoHistoria != null)
										{
											MotivoTramite lmt_motivoTramite;
											boolean       lb_subprocesoOrip;
											boolean       lb_subprocesoCorreo;
											boolean       lb_subprocesoExentas;
											boolean       lb_subprocesoCopiaVirtual;

											lmt_motivoTramite             = new MotivoTramite();
											lb_subprocesoOrip             = StringUtils.isValidString(ls_idSubproceso)
													&& ls_idSubproceso.equalsIgnoreCase(
													    SubProcesoCommon.COPIAS_COPIA_MEDIO_FISICO
													);
											lb_subprocesoCorreo           = StringUtils.isValidString(ls_idSubproceso)
													&& ls_idSubproceso.equalsIgnoreCase(
													    SubProcesoCommon.COPIAS_COPIA_MEDIO_MAGNETICO
													);
											lb_subprocesoExentas          = StringUtils.isValidString(ls_idSubproceso)
													&& ls_idSubproceso.equalsIgnoreCase(
													    SubProcesoCommon.COPIAS_COPIAS_EXENTAS
													);
											lb_subprocesoCopiaVirtual     = StringUtils.isValidString(ls_idSubproceso)
													&& ls_idSubproceso.equalsIgnoreCase(
													    SubProcesoCommon.APROBACION_DE_GENERACION_DE_COPIAS
													);

											lmt_motivoTramite.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
											lmt_motivoTramite.setIdMotivo(
											    (lb_informacionDigitalizada && lb_subprocesoCopiaVirtual)
											    ? MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_ORIP
											    : ((lb_informacionDigitalizada && lb_subprocesoOrip)
											    ? MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_ORIP
											    : ((lb_informacionDigitalizada && lb_subprocesoCorreo)
											    ? MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_CORREO
											    : ((lb_informacionPendientePago && lb_subprocesoExentas && lb_orip)
											    ? MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_ORIP
											    : ((lb_informacionPendientePago && lb_subprocesoExentas
												    && lb_correoElectronico)
											    ? MotivoTramiteCommon.APROBACION_DE_GENERACION_DE_COPIAS_EXENTA_CORREO
											    : NumericUtils.DEFAULT_LONG_VALUE))))
											);

											lmt_motivoTramite = lmtd_DAO.findById(lmt_motivoTramite);

											if(lmt_motivoTramite != null)
											{
												ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
												ath_turnoHistoria.setIdMotivo(
												    NumericUtils.getLongWrapper(lmt_motivoTramite.getIdMotivo())
												);
												ath_turnoHistoria.setMotivo(lmt_motivoTramite.getDescripcion());
												ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
												ath_turnoHistoria.setIpModificacion(as_remoteIp);

												lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

												lpd_DAO.spCreateStage(ath_turnoHistoria);

												lfmd_DAO.deleteFirmaMasiva(afm_parametros);
											}
										}
									}
								}
								else if(
								    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_COPIAS)
									    && (ll_idMotivoAnt == MotivoTramiteCommon.ETAPA_350_NOTA_DE_NEGACION_COPIAS)
								)
								{
									SuspensionBusiness lsb_business;
									Suspension         lsu_suspension;

									lsb_business       = getSuspensionBusiness();
									lsu_suspension     = lsb_business.findDataSuspensionOfTheProcedure(
										    ath_turnoHistoria, false, ls_usuario, as_remoteIp, as_remoteIp
										);
									lsu_suspension.setEsAnalistaCopias(true);

									lsu_suspension.setTurnoHistoria(ath_turnoHistoria);
									lsb_business.generarComunicadoSuspensiones(
									    lsu_suspension, true, ldm_manager, ls_usuario, as_remoteIp
									);

									long   ll_idMotivo;
									String ls_medioNot;

									ll_idMotivo     = 0L;
									ls_medioNot     = StringUtils.getStringNotNull(
										    lso_solicitud.getIdAutorizacionNotificacion()
										);

									if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
										ll_idMotivo = MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_CORREO_ELECTRONICO_620;
									else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
										ll_idMotivo = MotivoTramiteCommon.NOTA_DE_NEGACION_COPIAS_ORIP;

									terminarTurnoHistoriaYCrearEtapa(
									    ath_turnoHistoria, ldm_manager,
									    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
									    as_remoteIp, EstadoCommon.TERMINADA
									);

									DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS)
							{
								ActuacionesAdministrativasBusiness laab_aaBusiness;
								long                               ll_idMotivo;
								long                               ll_etapaOrigen;
								OficiosTexto                       lot_oficiosTexto;

								laab_aaBusiness      = getActuacionesAdministrativasBusiness();
								ll_idMotivo          = 0;
								ll_etapaOrigen       = 0;
								lot_oficiosTexto     = new OficiosTexto();

								lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());

								if(ll_idMotivoAnt == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)
								{
									laab_aaBusiness.generarDocumentoAutoResolucion(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
									    true, true, ldm_manager
									);

									laab_aaBusiness.generarDocumentoFijacionDesfijacion(
									    ath_turnoHistoria, ls_usuario, as_remoteIp, true, true, ldm_manager
									);
									ll_idMotivo     = MotivoTramiteCommon.APROBACION_RESOLUCION_TRASLADO;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION;
								}
								else if(
								    (ll_idMotivoAnt == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)
									    || (ll_idMotivoAnt == MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
								)
								{
									if(ll_idMotivoAnt == MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)
									{
										laab_aaBusiness.generarDocumentoAutoResolucion(
										    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
										    true, true, ldm_manager
										);

										if(lb_correoElectronico)
											ll_idMotivo = MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_CORREO_ELECTRONICO;
										else if(lb_direccionCorrespondencia || lb_direccionResidencia)
											ll_idMotivo = MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_DIRECCION_CORRESPONDENCIA;
										else if(lb_orip)
											ll_idMotivo = MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_ORIP;
										else
											ll_idMotivo = MotivoTramiteCommon.DEFAULT;
									}
									else
									{
										laab_aaBusiness.generarDocumentoAutoResolucion(
										    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
										    true, true, ldm_manager
										);

										if(lb_correoElectronico)
											ll_idMotivo = MotivoTramiteCommon.ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_CORREO_ELECTRONICO;
										else if(lb_direccionResidencia)
											ll_idMotivo = MotivoTramiteCommon.ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_DIRECCION_RESIDENCIA;
										else if(lb_direccionCorrespondencia)
											ll_idMotivo = MotivoTramiteCommon.NEGACION_SOLICITUD_DE_TRASLADOS_DIRECCION_CORRESPONDENCIA;
										else if(lb_orip)
											ll_idMotivo = MotivoTramiteCommon.ESPERA_ENTREGA_DOCUMENTACION_TRASLADOS_ORIP;
										else
											ll_idMotivo = MotivoTramiteCommon.DEFAULT;
									}

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION;
								}

								if(ll_idMotivoAnt == MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS_MASIVA)
								{
									laab_aaBusiness.generarDocumentoAutoResolucion(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
									    true, true, ldm_manager
									);

									laab_aaBusiness.generarDocumentoFijacionDesfijacion(
									    ath_turnoHistoria, ls_usuario, as_remoteIp, true, true, ldm_manager
									);
									ll_idMotivo     = MotivoTramiteCommon.PUBLICACION_ACTOS_ADMINISTIVOS_OFICINA_ORIGEN;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_REGISTRO;
								}

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.ANALISIS_DE_SEGUNDA_INSTANCIA)
							{
								long ll_idMotivo;
								long ll_etapaOrigen;

								ll_idMotivo        = 0;
								ll_etapaOrigen     = 0;

								OficiosTexto lot_oficiosTexto;

								lot_oficiosTexto   = new OficiosTexto();

								lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());

								if(ll_idMotivoAnt == MotivoTramiteCommon.ETAPA_430_AUTO_DE_APERTURA_DE_PRUEBAS)
								{
									SegundaInstanciaBusiness lsib_segundaInstanciaBusiness;

									lsib_segundaInstanciaBusiness = getSegundaInstanciaBusiness();

									lsib_segundaInstanciaBusiness.generarDocumentosSegundaInstancia(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true,
									    ldm_manager
									);

									{
										SolicitudAsociada lsa_solicitudAsociada;

										lsa_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
												                              .findByIdSolicitudProceso(
												    lso_solicitud.getIdSolicitud(), ProcesoCommon.ID_PROCESO_48
												);

										if(lsa_solicitudAsociada != null)
										{
											Solicitud ls_solicitudVinculada;

											ls_solicitudVinculada = lsd_DAO.findById(
												    lsa_solicitudAsociada.getIdSolicitud1()
												);

											if(ls_solicitudVinculada != null)
											{
												String ls_autorizaNotificacion;

												ls_autorizaNotificacion = ls_solicitudVinculada
														.getIdAutorizacionNotificacion();

												if(StringUtils.isValidString(ls_autorizaNotificacion))
													ll_idMotivo = ls_autorizaNotificacion.equalsIgnoreCase(
														    MedioNotificarCommon.ORIP
														) ? MotivoTramiteCommon.AUTO_DE_PRUEBAS_APROBADO
														  : ((ls_autorizaNotificacion.equalsIgnoreCase(
														    MedioNotificarCommon.DIRECCION_RESIDENCIA
														)
															|| ls_autorizaNotificacion.equalsIgnoreCase(
															    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
															))
														? MotivoTramiteCommon.AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORRESPONDENCIA
														: (ls_autorizaNotificacion.equalsIgnoreCase(
														    MedioNotificarCommon.CORREO_ELECTRONICO
														)
														? MotivoTramiteCommon.AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORREO
														: 0L));
											}
										}
									}

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_470;
								}
								else
								{
									ll_idMotivo     = MotivoTramiteCommon.RESOLUCION_DE_RECURSO_APROBADA;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_465;
								}

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								if(ll_idMotivo > 0)
								{
									terminarTurnoHistoriaYCrearEtapa(
									    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
									    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
									);

									DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_460)
							{
								long ll_idMotivo;
								long ll_etapaOrigen;

								ll_idMotivo        = 0;
								ll_etapaOrigen     = 0;

								TagPlantillaResolucion             lpr_plantillaResolucion;
								ActuacionesAdministrativasBusiness laab_aaBusiness;

								laab_aaBusiness    = getActuacionesAdministrativasBusiness();
								lpr_plantillaResolucion = new TagPlantillaResolucion();

								lpr_plantillaResolucion.setIdTurnoHistoria(
								    StringUtils.getString(lth_turnoHistoriaAnt.getIdTurnoHistoria())
								);

								if(ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_DE_RECURSO_POR_LEY)
								{
									laab_aaBusiness.generarResolucionRechazaRecurso(
									    lpr_plantillaResolucion, ls_usuario, as_remoteIp, true, true, ldm_manager
									);

									ll_idMotivo     = MotivoTramiteCommon.APROBADO_RECHAZO_DE_RECURSO_POR_LEY;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_470;
								}
								else if(ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDE_SEGUNDA_INSTANCIA)
								{
									OficiosTexto lot_oficiosTexto;

									lot_oficiosTexto = new OficiosTexto();

									lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());
									lot_oficiosTexto.setSegundaInstancia(true);

									laab_aaBusiness.generarDocumentoAutoResolucion(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, true,
									    false, true, ldm_manager
									);
									ll_idMotivo        = MotivoTramiteCommon.APROBADO_NO_PROCEDE_SEGUNDA_INSTANCIA;
									ll_etapaOrigen     = EtapaCommon.ID_ETAPA_470;
								}

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_465)
							{
								long ll_idMotivo;
								long ll_etapaOrigen;

								ll_idMotivo        = 0;
								ll_etapaOrigen     = 0;

								OficiosTexto lot_oficiosTexto;

								lot_oficiosTexto   = new OficiosTexto();

								SegundaInstanciaBusiness lsib_segundaInstanciaBusiness;
								TurnoHistoria            lth_turnoHistoria430;
								long                     ll_idMotivo430;

								lsib_segundaInstanciaBusiness = getSegundaInstanciaBusiness();
								lth_turnoHistoria430 = new TurnoHistoria(
									    NumericUtils.getBigDecimal(lth_turnoHistoriaAnt.getIdAnterior())
									);
								ll_idMotivo430     = 0L;
								lth_turnoHistoria430 = lthd_DAO.findById(lth_turnoHistoria430);

								if(lth_turnoHistoria430 != null)
								{
									lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoria430.getIdTurnoHistoria());
									ll_idMotivo430 = NumericUtils.getLong(lth_turnoHistoria430.getIdMotivo());
									lsib_segundaInstanciaBusiness.generarDocumentosSegundaInstancia(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivo430, true, true,
									    ldm_manager
									);

									if(
									    (ll_idMotivo430 == MotivoTramiteCommon.ETAPA_430_RESOLUCION_CONCEDIENDO_RECURSO_DE_APELACION)
										    || (ll_idMotivo430 == MotivoTramiteCommon.AUTO_DE_PRUEBAS_APROBADO)
										    || (ll_idMotivo430 == MotivoTramiteCommon.APROBADO_NO_PROCEDE_SEGUNDA_INSTANCIA)
										    || (ll_idMotivo430 == MotivoTramiteCommon.APROBADO_RECHAZO_DE_RECURSO_POR_LEY)
									)
										ll_idMotivo = MotivoTramiteCommon.RESOLUCION_DE_RECURSO_APROBADA;
								}

								ll_etapaOrigen = EtapaCommon.ID_ETAPA_470;

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.EJECUCION_DE_TRASLADOS)
							{
								long ll_idMotivo;
								long ll_etapaOrigen;

								ll_idMotivo        = 0;
								ll_etapaOrigen     = 0;

								if(ll_idMotivoAnt == MotivoTramiteCommon.PROCESO_TRASLADO_MASIVO)
								{
									ll_idMotivo     = MotivoTramiteCommon.PUBLICACION_ACTOS_ADMINISTIVOS_OFICINA_ORIGEN;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO;
								}
								else
								{
									ll_idMotivo     = MotivoTramiteCommon.PUBLICACION_ACTOS_ADMINISTIVOS_OFICINA_ORIGEN;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION_DE_TRASLADOS_OFICINA_DESTINO;
								}

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_377)
							{
								long ll_idMotivo;
								long ll_etapaOrigen;

								ll_idMotivo        = 0;
								ll_etapaOrigen     = 0;

								DevolucionDineroBusiness lsib_devolucionDineroBusiness;
								lsib_devolucionDineroBusiness = getDevolucionDineroBusiness();

								if(ll_idMotivoAnt == MotivoTramiteCommon.APROBACION_DEVOLUCION_DINERO)
									ll_idMotivo = MotivoTramiteCommon.APROBACION_DEVOLUCION_DINERO;
								else
									ll_idMotivo = MotivoTramiteCommon.NEGACION_DEVOLUCION_DINERO;

								OficiosTexto lot_oficiosTexto;

								lot_oficiosTexto = new OficiosTexto();
								lot_oficiosTexto.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
								lsib_devolucionDineroBusiness.generarDocumentoActoAdministrativo(
								    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, ldm_manager
								);
								ll_etapaOrigen = EtapaCommon.AUTORIZACION_RESPONSABLE_DEVOLUCIONES;

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
							{
								ActuacionesAdministrativasBusiness laab_aaBusiness;
								long                               ll_idMotivo;
								long                               ll_etapaOrigen;
								OficiosTexto                       lot_oficiosTexto;

								laab_aaBusiness      = getActuacionesAdministrativasBusiness();
								ll_idMotivo          = 0;
								ll_etapaOrigen       = 0;
								lot_oficiosTexto     = new OficiosTexto();

								lot_oficiosTexto.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());

								if(ll_idMotivoAnt == MotivoTramiteCommon.PROCESO_TRASLADO_INDIVIDUAL)
								{
									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION_DE_TRASLADOS_OFICINA_DESTINO;

									laab_aaBusiness.generarDocumentoAutoResolucion(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
									    true, false, true, ldm_manager
									);

									if(lb_correoElectronico)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_40;
									else if(lb_direccionCorrespondencia || lb_direccionResidencia)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_50;
									else if(lb_orip)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_INDIVIDUAL_APROBADO_30;
									else
										ll_idMotivo = MotivoTramiteCommon.DEFAULT;
								}
								else if(ll_idMotivoAnt == MotivoTramiteCommon.PROCESO_TRASLADO_MASIVO)
								{
									laab_aaBusiness.generarDocumentoAutoResolucion(
									    lot_oficiosTexto, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true, false,
									    true, false, true, ldm_manager
									);

									if(lb_correoElectronico)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_40;
									else if(lb_direccionCorrespondencia || lb_direccionResidencia)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_50;
									else if(lb_orip)
										ll_idMotivo = MotivoTramiteCommon.PROCESO_FINALIZADO_TRASLADO_MASIVO_APROBADO_30;
									else
										ll_idMotivo = MotivoTramiteCommon.DEFAULT;

									ll_etapaOrigen = EtapaCommon.ID_ETAPA_APROBACION_DIRECCION_TECNICA_DE_REGISTRO_OFICINA_DESTINO;
								}

								laab_aaBusiness.generarComunicadoResolucionTraslado(
								    lth_turnoHistoriaAnt, as_usuarioProceso, as_remoteIp, true, true, ldm_manager
								);

								ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								DaoCreator.getProcedimientosDAO(ldm_manager)
									          .procDesbloqueoMatriculas(
									    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
									);

								DaoCreator.getProcedimientosDAO(ldm_manager)
									          .procAsignarMatriculaTraslado(lth_turnoHistoriaAnt);

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager, new MotivoTramite(ll_etapaOrigen, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    ls_motivoNoTramite.contains(MotivonNoTramiteCommon.SUSPENSION)
								    || ls_motivoNoTramite.contains(MotivonNoTramiteCommon.RESTITUCION)
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.TRAMITE_DESISTIMIENTO_CON_TURNO_EN_FASE_ENTREGA_O_FINALIZADO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.DESISTIMIENTO_PARA_TURNO_TRAMITE))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.DESISTIMIENTO_FINALIZADO))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS))
							)
							{
								OficiosTexto lot_oficio;

								lot_oficio = new OficiosTexto();

								lot_oficio.setIdTurnoHistoria(NumericUtils.getLongWrapper(lbd_idAnterior));

								lot_oficio = DaoCreator.getOficiosTextoDAO(ldm_manager).findByTurnoHistoria(lot_oficio);

								if(lot_oficio != null)
								{
									String ls_tipo;
									ls_tipo = lot_oficio.getTipo();

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION)
									)
										ls_tipo = ProcesoCommon.ID_PROCESO_41;

									SuspensionBusiness lsb_business;
									Suspension         lsu_suspension;
									DocumentosSalida   lds_documentoSalida;
									String             ls_respuestaDesistimiento;

									boolean lb_art18;
									boolean lb_art19;
									boolean lb_proceso39;
									boolean lb_proceso43;
									boolean lb_proceso41;
									boolean lb_esSolicitudDocumentos;
									boolean lb_esProcedeNoCorreccion;
									boolean lb_esNegarSolicitudCertificados;

									lsb_business            = getSuspensionBusiness();
									lsu_suspension          = lsb_business.findDataSuspensionOfTheProcedure(
										    ath_turnoHistoria, false, ls_usuario, as_remoteIp, as_remoteIp
										);
									lds_documentoSalida     = new DocumentosSalida();

									lb_art18                            = false;
									lb_art19                            = false;
									lb_proceso39                        = false;
									lb_proceso43                        = false;
									lb_proceso41                        = false;
									lb_esProcedeNoCorreccion            = false;
									lb_esNegarSolicitudCertificados     = false;
									lb_esSolicitudDocumentos            = false;
									ls_respuestaDesistimiento           = null;

									if(StringUtils.isValidString(ls_tipo))
									{
										lb_art18         = ls_tipo.equalsIgnoreCase(Suspension.ART_18);
										lb_art19         = ls_tipo.equalsIgnoreCase(Suspension.ART_19);
										lb_proceso39     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39);
										lb_proceso43     = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_43);

										lb_proceso41 = ls_tipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);
									}

									lds_documentoSalida.setIdTurnoHistoria(NumericUtils.getInteger(ll_turnoHistoria));
									lds_documentoSalida.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());
									lds_documentoSalida.setTipo(TipoArchivoCommon.RESOLUCION);
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

									lds_documentoSalida = ldsd_DAO.findDocument(lds_documentoSalida);

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO)
									)
									{
										ls_respuestaDesistimiento = EstadoCommon.N;
										lsu_suspension.setRespuestaDesistimiento(ls_respuestaDesistimiento);
									}

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CERTIFICADOS)
									)
										lb_esNegarSolicitudCertificados = true;

									if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
									{
										if(ll_idMotivoAnt == MotivoTramiteCommon.DESISTIMIENTO_PARA_TURNO_TRAMITE)
											ls_respuestaDesistimiento = EstadoCommon.S;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO)
											ls_respuestaDesistimiento = EstadoCommon.N;

										lsu_suspension.setRespuestaDesistimiento(ls_respuestaDesistimiento);
									}

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES)
									)
										lb_esSolicitudDocumentos = true;

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS)
									)
										lsu_suspension.setRazonANegar(IdentificadoresCommon.VENCIMIENTO_PAGO);
									else if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION)
									)
										lsu_suspension.setRazonANegar(IdentificadoresCommon.VENCIMIENTO_TERMINO);

									if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS)
									)
										lb_esProcedeNoCorreccion = true;

									lsu_suspension.setEsSolicitudDocumentos(lb_esSolicitudDocumentos);
									lsu_suspension.setEsProcedeNoCorreccion(lb_esProcedeNoCorreccion);
									lsu_suspension.setEsNegarSolicitudCertificados(lb_esNegarSolicitudCertificados);

									if(lds_documentoSalida != null)
									{
										lsu_suspension.setTurnoHistoria(ath_turnoHistoria);
										lsu_suspension.setTipo(ls_tipo);
										lsu_suspension.setArticulo(lot_oficio.getArticulo());
										lsu_suspension.setPertinencia(lot_oficio.getPertinencia());
										lsu_suspension.setRazon1(lot_oficio.getRazon1());
										lsu_suspension.setRazon2(lot_oficio.getRazon2());
										lsu_suspension.setConsideracion(lot_oficio.getConsideracion());
										lsu_suspension.setUsuario(ls_usuario);

										if(lb_proceso39)
											lsu_suspension.setEtapa(ll_idEtapa);

										if(
										    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISIS_DE_DESISTIMIENTO)
											    && (ll_idMotivoAnt == MotivoTramiteCommon.DESISTIMIENTO_FINALIZADO)
										)
										{
											lb_proceso39 = true;
											lsu_suspension.setEtapa(EtapaCommon.ID_ETAPA_CALIFICACION);
										}

										if(
										    lb_art18 || lb_art19 || lb_proceso39
											    || (lb_proceso43
											    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO))
										)
											lsb_business.generarActoAdministrativoSuspensiones(
											    lsu_suspension, true, ldm_manager, as_usuarioProceso, as_remoteIp
											);
									}
									else
									{
										Object[] loa_arg;

										loa_arg        = new String[3];
										loa_arg[0]     = IdentificadoresCommon.RESOLUCION;
										loa_arg[1]     = StringUtils.getString(ll_turnoHistoria);
										loa_arg[2]     = ath_turnoHistoria.getIdSolicitud();

										if(
										    lb_art18 || lb_art19 || lb_proceso39
											    || (lb_proceso43
											    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO))
										)
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_REGISTROS, loa_arg);
									}

									lds_documentoSalida = new DocumentosSalida();
									lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

									if(
									    ((ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION))
										    || lb_esNegarSolicitudCertificados
									)
									{
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(lth_turnoHistoriaAnt.getIdTurnoHistoria())
										);

										if(lb_esNegarSolicitudCertificados)
											lds_documentoSalida.setTipo(
											    TipoArchivoCommon.COMUNICADO_NEGACION_CERTIFICADOS
											);
										else
											lds_documentoSalida.setTipo(
											    TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES
											);
									}
									else if(lb_esSolicitudDocumentos)
									{
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_turnoHistoria)
										);
										lds_documentoSalida.setTipo(TipoArchivoCommon.SOLICITUD_DOCUMENTO_CORRECCIONES);
									}
									else if(lb_esProcedeNoCorreccion)
									{
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(lth_turnoHistoriaAnt.getIdTurnoHistoria())
										);

										lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES);
									}
									else if(lb_esNegarSolicitudCertificados)
									{
										lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO_NEGACION_CERTIFICADOS);
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(lth_turnoHistoriaAnt.getIdTurnoHistoria())
										);
									}
									else if(
									    (ll_idEtapa == EtapaCommon.ID_ETAPA_RESTITUCION_DE_TURNOS)
										    && (ll_idMotivoAnt == MotivoTramiteCommon.DESISTIMIENTO_FINALIZADO)
									)
									{
										lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO_DEMANDA);
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_turnoHistoria)
										);
										lds_documentoSalida.setEstado(EstadoCommon.E);
									}
									else
									{
										lds_documentoSalida.setIdTurnoHistoria(
										    NumericUtils.getInteger(ll_turnoHistoria)
										);
										lds_documentoSalida.setTipo(TipoArchivoCommon.COMUNICADO);
									}

									lds_documentoSalida.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

									lds_documentoSalida = ldsd_DAO.findDocument(lds_documentoSalida);

									if(lds_documentoSalida != null)
									{
										TurnoHistoria lth_thTemp;
										lth_thTemp = null;

										if(!StringUtils.isValidString(lsu_suspension.getUsuario()))
										{
											lth_thTemp = new TurnoHistoria();

											lth_thTemp.setIdTurnoHistoria(
											    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
											);

											lth_thTemp = lthd_DAO.findById(lth_thTemp);

											if(lth_thTemp == null)
												lth_thTemp = new TurnoHistoria();

											lsu_suspension.setUsuario(lth_thTemp.getIdUsuarioModificacion());
										}

										if(lb_proceso39)
											lsu_suspension.setEtapa(ll_idEtapa);

										if(
										    lb_art18 || lb_art19 || lb_proceso39 || lb_proceso41
											    || lb_esSolicitudDocumentos || lb_esNegarSolicitudCertificados
											    || lb_esProcedeNoCorreccion
											    || (lb_proceso43
											    && (ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO))
										)
										{
											lsu_suspension.setTurnoHistoria(ath_turnoHistoria);
											lsu_suspension.setTipo(ls_tipo);
											lsb_business.generarComunicadoSuspensiones(
											    lsu_suspension, true, ldm_manager, ls_usuario, as_remoteIp
											);
										}
									}
									else
									{
										if(
										    !lb_proceso43
											    && (ll_idMotivoAnt != MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO)
										)
										{
											Object[] loa_arg;

											loa_arg        = new String[3];
											loa_arg[0]     = IdentificadoresCommon.COMUNICADO;
											loa_arg[1]     = StringUtils.getString(ll_turnoHistoria);
											loa_arg[2]     = ath_turnoHistoria.getIdSolicitud();

											throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_SALIDA_REGISTROS, loa_arg);
										}
									}

									{
										MotivoTramite lmt_motivo;
										long          ll_idMotivo;

										lmt_motivo      = new MotivoTramite();
										ll_idMotivo     = 0;

										if(lb_art18)
										{
											ll_idMotivo = MotivoTramiteCommon.FIRMAR;
											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										}
										else if(lb_art19)
										{
											if(lb_correoElectronico)
												ll_idMotivo = MotivoTramiteCommon.SUSPENSION_ARTICULO_19_CORREO_ELECTRONICO;
											else if(lb_direccionResidencia)
												ll_idMotivo = MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_RESIDENCIA_;
											else if(lb_direccionCorrespondencia)
												ll_idMotivo = MotivoTramiteCommon.SUSPENSION_ARTICULO_19_DIRECCION_CORRESPONDENCIA;
											else if(lb_orip)
												ll_idMotivo = MotivoTramiteCommon.SUSPENSION_ARTICULO_19_ORIP;
											else
												ll_idMotivo = MotivoTramiteCommon.DEFAULT;

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										}
										else if(lb_proceso39)
										{
											if(
											    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
												    && (ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO)
											)
											{
												if(lb_correoElectronico)
													ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORREO_ELECTRONICO;
												else if(lb_direccionResidencia || lb_direccionCorrespondencia)
													ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORRESPONDENCIA;
												else if(lb_orip)
													ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_ORIP;
												else
													ll_idMotivo = MotivoTramiteCommon.DEFAULT;
											}
											else
											{
												if(lb_correoElectronico)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_DESISTIMIENTO;
												else if(lb_direccionResidencia || lb_direccionCorrespondencia)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_DESISTIMIENTO;
												else if(lb_orip)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_DESISTIMIENTO;
												else
													ll_idMotivo = MotivoTramiteCommon.DEFAULT;
											}

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										}
										else if(lb_proceso43)
										{
											if(ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_APROBADO)
												ll_idMotivo = MotivoTramiteCommon.DEVOLVER_A_CALIFICACION;
											else if(ll_idMotivoAnt == MotivoTramiteCommon.RESTITUCION_DE_TURNOS_NEGADO)
											{
												if(lb_correoElectronico)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_POR_CORREO_ELECTRONICO_TRAMITE_RESTITUCION;
												else if(lb_direccionResidencia || lb_direccionCorrespondencia)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_POR_CORRESPONDENCIA_TRAMITE_RESTITUCION;
												else if(lb_orip)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_ORIP_TRAMITE_RESTITUCION;
												else
													ll_idMotivo = MotivoTramiteCommon.DEFAULT;
											}

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										}
										else if(lb_proceso41)
										{
											if(lb_correoElectronico)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_SOLICITUD_CORRECCION_CORREO;
											else if(lb_direccionResidencia || lb_direccionCorrespondencia)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_SOLICITUD_CORRECCION_CORRESPONDENCIA;
											else if(lb_orip)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_SOLICITUD_CORRECCION_ORIP;
											else
												ll_idMotivo = MotivoTramiteCommon.DEFAULT;

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES);
										}
										else if(lb_esSolicitudDocumentos)
										{
											if(lb_correoElectronico)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_SOLICITUD_DOCUMENTACION;
											else if(lb_direccionResidencia || lb_direccionCorrespondencia)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORRESPONDENCIA_SOLICITUD_DOCUMENTACION;
											else if(lb_orip)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_ORIP_SOLICITUD_DOCUMENTACION;
											else
												ll_idMotivo = MotivoTramiteCommon.DEFAULT;

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES);
										}
										else if(lb_esNegarSolicitudCertificados)
										{
											if(lb_correoElectronico)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_NEGADO_CORREO;
											else if(lb_direccionResidencia || lb_direccionCorrespondencia)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_NEGADO_CORRESPONDENCIA;
											else if(lb_orip)
												ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_NEGADO_ORIP;
											else
												ll_idMotivo = MotivoTramiteCommon.DEFAULT;

											lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										}

										lmt_motivo.setIdMotivo(ll_idMotivo);

										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

										if(lmt_motivo != null)
										{
											ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

											if(ath_turnoHistoria != null)
											{
												ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
												ath_turnoHistoria.setIdMotivo(
												    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
												);
												ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
												ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
												ath_turnoHistoria.setIpModificacion(as_remoteIp);

												lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

												DaoCreator.getProcedimientosDAO(ldm_manager)
													          .spCreateStage(ath_turnoHistoria);

												DaoCreator.getFirmaMasivaDAO(ldm_manager)
													          .deleteFirmaMasiva(afm_parametros);

												if(lb_art18)
												{
													AlertaTurnoTramite latt_att;
													String             ls_idTurno;

													latt_att       = new AlertaTurnoTramite();
													ls_idTurno     = ath_turnoHistoria.getIdTurno();

													latt_att.setIdTurnoAfectado(ls_idTurno);
													latt_att.setIdAlertaTramite(
													    AlertaTramiteCommon.EN_TRAMITE_DE_SUSPENSION
													);

													{
														SolicitudAsociada lsa_solicAsociada;

														lsa_solicAsociada = DaoCreator.getSolicitudAsociadaDAO(
															    ldm_manager
															).findByIdSol1(ls_idSolicitud);

														if(lsa_solicAsociada != null)
															latt_att.setIdSolicitud(lsa_solicAsociada.getIdSolicitud());
													}

													latt_att.setIdSolicitudVinculada(ls_idSolicitud);
													latt_att.setActivo(EstadoCommon.SI);
													latt_att.setIdUsuarioCreacion(ls_usuario);
													latt_att.setIpCreacion(as_remoteIp);

													{
														Collection<String>    lcs_turnosAsociados;
														AlertaTurnoTramiteDAO lattd_alertaTurnoTramiteDAO;

														lcs_turnosAsociados             = obtenerColeccionTurnosBitacoraBloqueo(
															    ls_idTurno, ldm_manager
															);
														lattd_alertaTurnoTramiteDAO     = DaoCreator
																.getAlertaTurnoTramiteDAO(ldm_manager);

														if(CollectionUtils.isValidCollection(lcs_turnosAsociados))
														{
															for(String ls_turnoAsociado : lcs_turnosAsociados)
															{
																if(StringUtils.isValidString(ls_turnoAsociado))
																{
																	latt_att.setIdTurnoAsociado(ls_turnoAsociado);

																	lattd_alertaTurnoTramiteDAO.insertarAlerta(
																	    latt_att
																	);
																}
															}
														}
														else
														{
															latt_att.setIdTurnoAsociado(null);

															lattd_alertaTurnoTramiteDAO.insertarAlerta(latt_att);
														}
													}
												}
											}
										}
										else
										{
											Object[] loa_arg;

											loa_arg        = new String[2];
											loa_arg[0]     = StringUtils.getString(EtapaCommon.ID_ETAPA_APROBACION);
											loa_arg[1]     = StringUtils.getString(ll_idMotivo);

											throw new B2BException(ErrorKeys.ERROR_MOTIVO_ETAPA, loa_arg);
										}
									}
								}
								else
								{
									Object[] loa_arg;

									loa_arg        = new String[1];
									loa_arg[0]     = StringUtils.getString(ll_turnoHistoria);

									throw new B2BException(
									    ErrorKeys.ERROR_OFICIO_TEXTO_REGISTRO_TURNO_HISTORIA, loa_arg
									);
								}
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.APROBAR_PRORROGA_DOCUMENTOS)
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.SOLICITUD_DOCUMENTO_CORRECCIONES, ls_usuario, as_remoteIp, true,
								    ldm_manager
								);

								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORREO_ELECTRONICO;
								else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORRESPONDENCIA;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && ((ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_PRORROGA_DOCUMENTOS)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.NO_PROCEDENCIA_DE_LA_CORRECCION))
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES, ls_usuario, as_remoteIp, true,
								    ldm_manager
								);

								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_1))
								{
									if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORREO_ELECTRONICO;
									else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_ORIP;
									else
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRECCION_CORRESPONDENCIA;
								}
								else
									ll_idMotivo = MotivoTramiteCommon.CORRECCION_INTERNA_FINALIZADA;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.RECHAZO_SOLICITUD_DESISTIMIENTO)
							)
							{
								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORREO_ELECTRONICO;
								else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.APROBADO_RECHAZO_SOLICITUD_DESISTIMIENTO_CORRESPONDENCIA;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.MODIFICAR_MATRICULAS)
							)
							{
								{
									SolicitudMatriculaDAO          lsmd_solicitudMatriculaDAO;
									SolicitudMatricula             lsm_tmp;
									Collection<SolicitudMatricula> lcsm_matriculas;

									lsmd_solicitudMatriculaDAO     = DaoCreator.getSolicitudMatriculaDAO(ldm_manager);
									lsm_tmp                        = new SolicitudMatricula();

									lsm_tmp.setIdSolicitud(ls_idSolicitud);

									lcsm_matriculas = lsmd_solicitudMatriculaDAO.findByIdSolicitud(lsm_tmp);

									if(CollectionUtils.isValidCollection(lcsm_matriculas))
									{
										EstadoPredioDAO   lepd_estadoPredioDAO;
										PredioRegistroDAO lprd_predioRegistroDAO;

										lepd_estadoPredioDAO       = DaoCreator.getHTREstadoPredioDAO(ldm_manager);
										lprd_predioRegistroDAO     = DaoCreator.getPredioRegistroDAO(ldm_manager);

										for(SolicitudMatricula lsm_data : lcsm_matriculas)
										{
											if(lsm_data != null)
											{
												PredioRegistro lpr_predio;

												lpr_predio = new PredioRegistro();

												lpr_predio.setIdCirculo(lsm_data.getIdCirculo());
												lpr_predio.setIdMatricula(lsm_data.getIdMatricula());

												lpr_predio = lprd_predioRegistroDAO.findById(lpr_predio);

												if(lpr_predio != null)
												{
													com.bachue.snr.prosnr01.model.sdb.htr.EstadoPredio lep_estadoPredio;

													lep_estadoPredio = new com.bachue.snr.prosnr01.model.sdb.htr.EstadoPredio();

													lep_estadoPredio.setIdCirculo(lpr_predio.getIdCirculo());
													lep_estadoPredio.setIdMatricula(
													    NumericUtils.getBigInteger(lpr_predio.getIdMatricula())
													);
													lep_estadoPredio.setIdEstadoPredioAnt(
													    lpr_predio.getIdEstadoPredio()
													);
													lep_estadoPredio.setJustificacionCambio(
													    ath_turnoHistoria.getObservaciones()
													);
													lep_estadoPredio.setIdUsuarioCreacion(ls_usuario);
													lep_estadoPredio.setIpCreacion(as_remoteIp);

													lepd_estadoPredioDAO.insert(lep_estadoPredio);

													lpr_predio.setIdEstadoPredio(EstadoCommon.S);
													lpr_predio.setIdUsuarioModificacion(ls_usuario);
													lpr_predio.setIpModificacion(as_remoteIp);

													lprd_predioRegistroDAO.insertOrUpdate(lpr_predio, false);
												}
											}
										}
									}
								}

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(
								        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES,
								        MotivoTramiteCommon.REAPERTURA_DE_FOLIO_POR_APROBACION
								    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR_CORRECCIONES)
							)
							{
								generarDocumentosMayorValor(ldm_manager, ath_turnoHistoria, ls_usuario, as_remoteIp);

								{
									String ls_medioNot;
									long   ll_idMotivo;

									ls_medioNot = StringUtils.getStringNotNull(
										    lso_solicitud.getIdAutorizacionNotificacion()
										);

									if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_COBRO_POR_MAYOR_VALOR;
									else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_ORIP_COBRO_POR_MAYOR_VALOR;
									else
										ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORRESPONDENCIA_COBRO_POR_MAYOR_VALOR;

									terminarTurnoHistoriaYCrearEtapa(
									    ath_turnoHistoria, ldm_manager,
									    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo),
									    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
									);

									DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
								}
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.SOLICITUD_DOCUMENTACION_PARA_PROCESO_DE_CORRECCIONES)
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.SOLICITUD_DOCUMENTO_CORRECCIONES, ls_usuario, as_remoteIp, true,
								    ldm_manager
								);

								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORREO_ELECTRONICO_SOLICITUD_DOCUMENTACION;
								else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_ORIP_SOLICITUD_DOCUMENTACION;
								else
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_CORRESPONDENCIA_SOLICITUD_DOCUMENTACION;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.REALIZAR_CORRECCIONES)
							)
							{
								DaoCreator.getProcedimientosDAO(ldm_manager)
									          .procCorreccion(
									    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
									);

								getCalificacionBusiness()
									    .generarPlantillaEntidadCorreccionSiProcede(
									    ldm_manager, ath_turnoHistoria.getIdSolicitud(), ath_turnoHistoria.getIdTurno(),
									    ls_usuario, ath_turnoHistoria.getIdTurnoHistoria(), as_remoteIp, true, true
									);

								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.OFICIO_REMISORIO_ENTIDAD_MEDIDA_CAUTELAR, ls_usuario, as_remoteIp,
								    false, ldm_manager
								);

								long ll_idMotivo;

								if(ls_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_1))
								{
									String ls_medioNot;

									ls_medioNot = StringUtils.getStringNotNull(
										    lso_solicitud.getIdAutorizacionNotificacion()
										);

									if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
										ll_idMotivo = MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORREO_ELECTRONICO;
									else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
										ll_idMotivo = MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_ORIP;
									else
										ll_idMotivo = MotivoTramiteCommon.CORRECCION_REALIZADA_ENTREGA_CORRESPONDENCIA;
								}
								else
									ll_idMotivo = MotivoTramiteCommon.CORRECCION_INTERNA_FINALIZADA;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo),
								    ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    ((ll_idEtapa == EtapaCommon.ID_ETAPA_TESTAMENTOS)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.REPRODUCCION_DE_CONSTANCIA_DE_TESTAMENTOS))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_REPRODUCCION_DE_CONSTANCIA)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.REPRODUCCION_DE_CONSTANCIA_AUTOMATICA))
							)
							{
								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.REPRODUCCION_TESTAMENTOS_CORRREO_ELECTRONICO;
								else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.REPRODUCCION_TESTAMENTOS_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.REPRODUCCION_TESTAMENTOS_ENTREGA_DIRECCIONES;

								{
									ReproduccionConstanciaTestamento lst_reproduccionConstanciaTestamento;
									Testamento                       lt_testamento;

									lst_reproduccionConstanciaTestamento     = new ReproduccionConstanciaTestamento();
									lt_testamento                            = new Testamento();

									lst_reproduccionConstanciaTestamento.setDocumento(
									    DaoCreator.getDocumentoDAO(ldm_manager).findById(
									        lso_solicitud.getIdDocumento()
									    )
									);

									lt_testamento.setIdTestamento(lso_solicitud.getIdTestamento());
									lst_reproduccionConstanciaTestamento.setTestamento(
									    DaoCreator.getTestamentoDAO(ldm_manager).findById(lt_testamento)
									);

									new TestamentosBusiness().generarConstanciaReproduccionTestamento(
									    lst_reproduccionConstanciaTestamento, ath_turnoHistoria.getIdTurno(), ls_usuario,
									    as_remoteIp, ldm_manager, false, true
									);
								}

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
								    && ls_idSubproceso.equalsIgnoreCase(SubProcesoCommon.TESTAMENTO)
								    && (ll_idEtapa == EtapaCommon.ID_ETAPA_TESTAMENTOS)
								    && ((ll_idMotivoAnt == MotivoTramiteCommon.APROBAR_TESTAMENTOS)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.APROBACION_MODIFICACION_TESTAMENTO)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.APROBACION_ANULACION_TESTAMENTO)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.APROBACION_REVOCATORIA_TESTAMENTO))
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.COMUNICADO    /*Cambiar tipo archivo*/, ls_usuario, as_remoteIp,
								    true, ldm_manager
								);

								String ls_medioNot;
								long   ll_idMotivo;

								ls_medioNot = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.TESTAMENTO_ENTREGA_CORREO_ELECTRONICO;
								else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.TESTAMENTO_ENTREGA_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.TESTAMENTO_ENTREGA_DIRECCION_RESIDENCIA_CORRESPONDENCIA_190;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_CERTIFICADOS_ESPECIALES)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.GENERACION_CERTIFICADO_ESPECIAL)
							)
							{
								if(lsp_subproceso != null)
								{
									OficiosTexto lot_oficios;

									lot_oficios = new OficiosTexto();

									lot_oficios.setIdTurnoHistoria(lth_turnoHistoriaAnt.getIdTurnoHistoria());
									lot_oficios.setTipo(ls_idSubproceso);

									lot_oficios = DaoCreator.getOficiosTextoDAO(ldm_manager)
											                    .findByTurnoHistoriaTipo(lot_oficios);

									if(lot_oficios != null)
									{
										Certificados lc_certificado;
										String       ls_idTurno;

										lc_certificado     = new Certificados();
										ls_idTurno         = ath_turnoHistoria.getIdTurno();

										lc_certificado.setTextoTag(lot_oficios.getConsideracion());
										lc_certificado.setSubProceso(lsp_subproceso);
										lc_certificado.setIdTurno(ls_idTurno);
										lc_certificado.setPlantillaSeleccionada(lot_oficios.getPlantilla());
										lc_certificado.setIpCreacion(as_remoteIp);
										lc_certificado.setIpModificacion(as_remoteIp);
										lc_certificado.setIdUsuarioCreacion(ls_usuario);
										lc_certificado.setIdUsuarioModificacion(ls_usuario);
										lc_certificado.setSolicitud(lso_solicitud);

										Collection<DocumentosSalida> lcds_documentos;

										lcds_documentos = ldsd_DAO.findByIdTurnoTipoActivo(
											    ls_idTurno, TipoArchivoCommon.CERTIFICADO
											);

										if(CollectionUtils.isValidCollection(lcds_documentos))
										{
											for(DocumentosSalida lds_documento : lcds_documentos)
											{
												if(lds_documento != null)
													getCertificadosBusiness()
														    .generarCertificadosEspeciales(
														    lc_certificado, true, true, true, ldm_manager,
														    NumericUtils.getLongWrapper(
														        lds_documento.getIdDocumentoSalida()
														    )
														);
											}
										}

										Collection<DocumentosSalida> lcds_documentos2;

										lcds_documentos2 = ldsd_DAO.findByIdTurnoTipoActivo(
											    ls_idTurno, TipoArchivoCommon.COMUNICADO_NEGACION_CERTIFICADOS
											);

										if(CollectionUtils.isValidCollection(lcds_documentos2))
										{
											for(DocumentosSalida lds_documento2 : lcds_documentos2)
											{
												if(lds_documento2 != null)
												{
													lds_documento2.setEstado(EstadoCommon.I);
													lds_documento2.setIdUsuarioModificacion(as_usuarioProceso);
													lds_documento2.setIpModificacion(as_remoteIp);

													ldsd_DAO.insertOrUpdate(lds_documento2, false);
												}
											}
										}
									}
								}

								long   ll_idMotivo;
								String ls_medioCom;

								ll_idMotivo     = 0;
								ls_medioCom     = StringUtils.getStringNotNull(
									    lso_solicitud.getIdAutorizacionNotificacion()
									);

								if(ls_medioCom.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_CORREO_ELECTRONICO;
								else
									ll_idMotivo = MotivoTramiteCommon.ENTREGA_CERTIFICADOS_ORIP;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.COBRO_POR_MAYOR_VALOR)
							)
							{
								generarDocumentosMayorValor(ldm_manager, ath_turnoHistoria, ls_usuario, as_remoteIp);

								{
									MotivoTramite lmt_motivo;
									long          ll_idMotivo;

									lmt_motivo      = new MotivoTramite();
									ll_idMotivo     = 0;

									{
										ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

										if(ath_turnoHistoria == null)
											throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

										ls_solicitud = lsd_DAO.findById(ls_solicitud);

										if(ls_solicitud != null)
										{
											String ls_medioNot;

											ls_medioNot = StringUtils.getStringNotNull(
												    ls_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.MAYOR_VALOR_CORREO_ELECTRONICO;
											else if(
											    ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
												    || ls_medioNot.equalsIgnoreCase(
												        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
												    )
											)
												ll_idMotivo = MotivoTramiteCommon.MAYOR_VALOR_CORRESPONDENCIA;
											else if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.MAYOR_VALOR_ORIP;
										}
									}

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
									lmt_motivo.setIdMotivo(ll_idMotivo);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if((lmt_motivo != null) && (ath_turnoHistoria != null))
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										{
											String                ls_idTurno;
											RegistroMayorValor    lrmv_rmv;
											RegistroMayorValorDAO lrmvd_DAO;

											lrmvd_DAO      = DaoCreator.getRegistroMayorValorDAO(ldm_manager);
											ls_idTurno     = ath_turnoHistoria.getIdTurno();
											lrmv_rmv       = new RegistroMayorValor(
												    ls_idTurno, MayorValorCommon.PENDIENTE_PAGO, ls_usuario, as_remoteIp
												);

											if(CollectionUtils.isValidCollection(lrmvd_DAO.findByIdTurno(ls_idTurno)))
												lrmvd_DAO.updateEstadoMayorValor(lrmv_rmv);
										}

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.NOTA_DEVOLUTIVA)
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.NOTA_DEVOLUTIVA_CANCELACION
								    )
							)
							{
								if(validarTurnosAsociados(ath_turnoHistoria, ldm_manager))
								{
									{
										String ls_tipoArchivo;

										RegistroCalificacion lr_registroCalificacion;
										lr_registroCalificacion     = new RegistroCalificacion();

										ls_tipoArchivo = ls_motivoNoTramite.equalsIgnoreCase(
											    MotivonNoTramiteCommon.NOTA_DEVOLUTIVA
											) ? TipoArchivoCommon.NOTA_DEVOLUTIVA
											  : (ls_motivoNoTramite.equalsIgnoreCase(
											    MotivonNoTramiteCommon.NOTA_DEVOLUTIVA_CANCELACION
											) ? TipoArchivoCommon.COMUNICADO_CANCELACION : "");

										if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.NOTA_DEVOLUTIVA_CANCELACION
											    )
										)
										{
											lr_registroCalificacion.setNotaDevolutiva(true);
											lr_registroCalificacion.setUserId(as_usuarioProceso);
											lr_registroCalificacion.setSalvar(true);
											lr_registroCalificacion.setIdTurnoHistoria(
											    lth_turnoHistoriaAnt.getIdTurnoHistoria()
											);
											getRegistroCalificacionBusiness()
												    .genereteFileCancelacion(
												    ldm_manager, lr_registroCalificacion, true, as_usuarioProceso,
												    as_remoteIp
												);

											lr_registroCalificacion.setNotaDevolutivaMedidaCautelar(true);
											lr_registroCalificacion.setNotaDevolutiva(false);
											lr_registroCalificacion.setUserId(as_usuarioProceso);
											lr_registroCalificacion.setSalvar(true);
											lr_registroCalificacion.setIdTurnoHistoria(
											    lth_turnoHistoriaAnt.getIdTurnoHistoria()
											);
											getRegistroCalificacionBusiness()
												    .genereteFileCancelacion(
												    ldm_manager, lr_registroCalificacion, true, as_usuarioProceso,
												    as_remoteIp
												);
											firmarDocumentos(
											    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
											    TipoArchivoCommon.COMUNICADO_CANCELACION, ls_usuario, as_remoteIp, true,
											    ldm_manager
											);
											firmarDocumentos(
											    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
											    TipoArchivoCommon.NOTA_DEVOLUTIVA, ls_usuario, as_remoteIp, true,
											    ldm_manager
											);
										}

										firmarDocumentos(
										    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION, ls_tipoArchivo,
										    ls_usuario, as_remoteIp, true, ldm_manager
										);
									}

									ProcedimientosDAO lpd_DAO;
									lpd_DAO = DaoCreator.getProcedimientosDAO(ldm_manager);

									{
										String ls_turno;
										ls_turno = ath_turnoHistoria.getIdTurno();

										if(StringUtils.isValidString(ls_turno))
										{
											TurnoDerivado             ltd_turnoDerivado;
											Collection<TurnoDerivado> lctd_turnoDerivado;

											ltd_turnoDerivado = new TurnoDerivado();

											ltd_turnoDerivado.setIdTurnoPadre(ls_turno);

											lctd_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
													                           .findByIdTurnoPadre(ltd_turnoDerivado);

											if(CollectionUtils.isValidCollection(lctd_turnoDerivado))
											{
												for(TurnoDerivado ltd_iterador : lctd_turnoDerivado)
												{
													if(ltd_iterador != null)
													{
														TurnoHistoria             lth_turnoHistoriaTmp;
														Collection<TurnoHistoria> lcth_turnoEtapa30;

														lth_turnoHistoriaTmp = new TurnoHistoria();

														lth_turnoHistoriaTmp.setIdTurno(ltd_iterador.getIdTurnoHijo());
														lth_turnoHistoriaTmp.setIdEtapa(
														    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
														);
														lth_turnoHistoriaTmp.setEstadoActividad(EstadoCommon.TERMINADA);

														lcth_turnoEtapa30 = lthd_DAO.findByTurnoEtapaEstActDif(
															    lth_turnoHistoriaTmp
															);

														if(CollectionUtils.isValidCollection(lcth_turnoEtapa30))
														{
															for(TurnoHistoria lth_iteradorEtapa30 : lcth_turnoEtapa30)
															{
																if(lth_iteradorEtapa30 != null)
																{
																	lth_iteradorEtapa30.setEstadoActividad(
																	    EstadoCommon.TERMINADA
																	);
																	lth_iteradorEtapa30.setIdMotivo(
																	    NumericUtils.getLongWrapper(
																	        MotivoTramiteCommon.SUSPENSION_CERTIFICADO_DEVUELTO_AL_PUBLICO
																	    )
																	);
																	lth_iteradorEtapa30.setIdUsuarioModificacion(
																	    ls_usuario
																	);
																	lth_iteradorEtapa30.setIpModificacion(as_remoteIp);

																	lthd_DAO.insertOrUpdate(lth_iteradorEtapa30, false);

																	lpd_DAO.spCreateStage(lth_iteradorEtapa30);
																}
															}
														}
													}
												}
											}
										}
									}

									{
										MotivoTramite lmt_motivo;
										long          ll_idMotivo;

										lmt_motivo      = new MotivoTramite();
										ll_idMotivo     = 0;

										if(ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.NOTA_DEVOLUTIVA))
										{
											ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

											if(ath_turnoHistoria == null)
												throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

											Solicitud ls_solicitud;

											ls_solicitud = new Solicitud();

											ls_solicitud.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

											ls_solicitud = lsd_DAO.findById(ls_solicitud);

											if(ls_solicitud != null)
											{
												String ls_medioNot;

												ls_medioNot = StringUtils.getStringNotNull(
													    ls_solicitud.getIdAutorizacionNotificacion()
													);

												if(
												    ls_medioNot.equalsIgnoreCase(
													        MedioNotificarCommon.CORREO_ELECTRONICO
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_CORREO;
												else if(
												    ls_medioNot.equalsIgnoreCase(
													        MedioNotificarCommon.DIRECCION_RESIDENCIA
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_RESIDENCIA;
												else if(
												    ls_medioNot.equalsIgnoreCase(
													        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_CORRESPONDENCIA;
												else if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.ORIP))
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_ORIP;
											}
										}
										else if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.NOTA_DEVOLUTIVA_CANCELACION
											    )
										)
											ll_idMotivo = MotivoTramiteCommon.CANCELACION_NOTA_DEVOLUTIVA;

										lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
										lmt_motivo.setIdMotivo(ll_idMotivo);

										lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

										if(lmt_motivo != null)
										{
											if(ath_turnoHistoria != null)
											{
												ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
												ath_turnoHistoria.setIdMotivo(
												    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
												);
												ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
												ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
												ath_turnoHistoria.setIpModificacion(as_remoteIp);

												lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

												lpd_DAO.spCreateStage(ath_turnoHistoria);

												DaoCreator.getFirmaMasivaDAO(ldm_manager)
													          .deleteFirmaMasiva(afm_parametros);
											}
										}
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.REALIZAR_REGISTRO_VENTA_PARCIAL
								    )
							)
							{
								RegistroCalificacion lotc_tc;
								lotc_tc = new RegistroCalificacion();
								lotc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
								lotc_tc.setSalvar(true);
								getRegistroCalificacionBusiness()
									    .genereteFileComunicadoDireccion(
									    ldm_manager, lotc_tc, ls_usuario, as_remoteIp, true
									);
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.REGISTRO, ls_usuario, as_remoteIp, true, ldm_manager
								);

								MotivoTramite     lmt_motivo;
								long              ll_idMotivo;
								ProcedimientosDAO lpd_DAO;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = 0;
								lpd_DAO         = DaoCreator.getProcedimientosDAO(ldm_manager);

								if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
								else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.DEFAULT;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										lpd_DAO.procRealizarRegistro(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										{
											TurnoHistoria lth_temp;

											lth_temp = new TurnoHistoria();

											lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
											lth_temp.setIdUsuarioCreacion(ls_usuario);
											lth_temp.setIpCreacion(as_remoteIp);

											lpd_DAO.procCerrarFolio(lth_temp);
										}

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procCrearMatriculaAntSistema(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);

										TurnoDerivado             ltd_turnoDerivado;
										Collection<TurnoDerivado> lctd_turnosDerivados;

										ltd_turnoDerivado = new TurnoDerivado();
										ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

										lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
												                             .findByIdTurnoPadre(ltd_turnoDerivado);

										if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
										{
											for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
											{
												if(ltd_iterador != null)
												{
													SolicitudMatricula lsm_solicitudMatricula;
													String             ls_turnoHijo;

													lsm_solicitudMatricula     = new SolicitudMatricula();
													ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

													lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
													lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

													lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
														    ldm_manager
														).findByTurnoCertificado(lsm_solicitudMatricula);

													if(lsm_solicitudMatricula != null)
													{
														BigDecimal lbd_cantidadCertificados;
														lbd_cantidadCertificados = lsm_solicitudMatricula
																.getCantidadCertificados();

														if(lbd_cantidadCertificados != null)
														{
															Double ld_cantidad;
															int    li_cantidad;

															ld_cantidad     = NumericUtils.getDoubleWrapper(
																    lbd_cantidadCertificados
																);
															li_cantidad     = (ld_cantidad != null)
																? NumericUtils.getInt(ld_cantidad) : 0;

															lsm_solicitudMatricula.setTurnoHistoria(ath_turnoHistoria);

															for(int li_i = 1; li_i <= li_cantidad; li_i++)
																generarCertificadoTradicionLibertad(
																    lsm_solicitudMatricula, IdentificadoresCommon.FIRMA,
																    true, ldm_manager, as_usuarioProceso, as_remoteIp
																);
														}
													}

													{
														TurnoHistoria lth_turnoDerivado;
														lth_turnoDerivado = new TurnoHistoria();

														lth_turnoDerivado.setIdTurno(ls_turnoHijo);
														lth_turnoDerivado.setIdEtapa(
														    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
														);
														lth_turnoDerivado.setEstadoActividad(EstadoCommon.AUTOMATICA);

														lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
															    lth_turnoDerivado
															);

														if(lth_turnoDerivado != null)
														{
															lth_turnoDerivado.setEstadoActividad(
															    EstadoCommon.TERMINADA
															);
															lth_turnoDerivado.setMotivo(
															    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
															);
															lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
															lth_turnoDerivado.setIpModificacion(as_remoteIp);

															lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
														}
													}
												}
											}
										}

										generarPlantillaRegistro(
										    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
										    lso_solicitud.getNir()
										);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_SUSTANCIADOR_ACTUACIONES_ADMINISTRATIVAS)
							{
								Long     ll_idTurnoHistoriaAnterior;
								String   ls_idTurno;
								Turno    lt_turno;
								TurnoDAO lt_DAO;

								ll_idTurnoHistoriaAnterior     = NumericUtils.getLongWrapper(
									    ath_turnoHistoria.getIdAnterior()
									);
								ls_idTurno                     = ath_turnoHistoria.getIdTurno();
								lt_DAO                         = DaoCreator.getTurnoDAO(ldm_manager);
								lt_turno                       = lt_DAO.findById(ls_idTurno);

								if(lt_turno != null)
								{
									OficiosTexto lot_data;

									lot_data = new OficiosTexto();

									lot_data.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());

									if(
									    (ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_APERTURA)
										    || (ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_ACLARACION)
									)
									{
										Collection<PersonaNotificacion> lcpn_personas;
										Collection<DocumentosSalida>    lcds_data;
										String                          ls_expediente;

										lcpn_personas     = DaoCreator.getPersonaNotificacionDAO(ldm_manager)
												                          .findByIdTurnoDecision(
												    ls_idTurno, ll_idMotivoAnt, NumericUtils.getLongWrapper(ll_idEtapa)
												);
										ls_expediente     = generarExpediente(
											    ls_usuario, as_remoteIp, lt_turno, ldm_manager
											);

										if(StringUtils.isValidString(ls_expediente))
										{
											lt_turno.setExpediente(ls_expediente);
											lot_data.setExpediente(ls_expediente);
											lt_DAO.actualizarExpediente(ls_expediente, ls_usuario, ls_idTurno);
										}

										getActuacionesAdministrativasBusiness()
											    .generarDocumentoAutoResolucion(
											    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt
											);

										inactivarDocumentosActivosTurnoHistoriaTipo(
										    ll_idTurnoHistoriaAnterior, TipoArchivoCommon.COMUNICADO, ldm_manager,
										    as_remoteIp, as_usuarioProceso
										);

										inactivarDocumentosActivosTurnoHistoriaTipo(
										    ll_idTurnoHistoriaAnterior, TipoArchivoCommon.COMUNICADO_INDETERMINADO,
										    ldm_manager, as_remoteIp, as_usuarioProceso
										);

										if(CollectionUtils.isValidCollection(lcpn_personas))
										{
											for(PersonaNotificacion lpn_iterador : lcpn_personas)
											{
												if(lpn_iterador != null)
													getActuacionesAdministrativasBusiness()
														    .generarDocumentoComunicado(
														    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt,
														    lpn_iterador
														);
											}
										}

										lcds_data = ldsd_DAO.findByIdTurnoEstadoImpresion(ls_idTurno, false);

										if(CollectionUtils.isValidCollection(lcds_data))
										{
											String ls_endpoint;

											ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
													                    .findString(
													    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													);

											if(StringUtils.isValidString(ls_endpoint))
											{
												for(DocumentosSalida lds_iterador : lcds_data)
												{
													if(lds_iterador != null)
													{
														if(!lds_iterador.isEnviadoOwcc())
														{
															getEnvioGestorDocumentalBusiness()
																    .enviarGestorDocumental(
																    lds_iterador, abpd_DAO, ls_endpoint, ls_usuario,
																    as_remoteIp, ldm_manager
																);

															if(!lds_iterador.isEnviadoOwcc())
																throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

										{
											long   ll_idMotivo;
											String ls_medioNot;

											ll_idMotivo     = 0L;
											ls_medioNot     = StringUtils.getStringNotNull(
												    lso_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_APERTURA_220;
											else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_APERTURA_200;
											else
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_APERTURA_215;

											terminarTurnoHistoriaYCrearEtapa(
											    ath_turnoHistoria, ldm_manager,
											    new MotivoTramite(
											        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo
											    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
									else if(ll_idMotivoAnt == MotivoTramiteCommon.NEGACION_APERTURA)
									{
										getActuacionesAdministrativasBusiness()
											    .generarDocumentoAutoResolucion(
											    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt
											);

										Collection<DocumentosSalida> lcds_data;

										lcds_data = ldsd_DAO.findByIdTurnoEstadoImpresion(ls_idTurno, false);

										if(CollectionUtils.isValidCollection(lcds_data))
										{
											String ls_endpoint;

											ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
													                    .findString(
													    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													);

											if(StringUtils.isValidString(ls_endpoint))
											{
												for(DocumentosSalida lds_iterador : lcds_data)
												{
													if(lds_iterador != null)
													{
														if(!lds_iterador.isEnviadoOwcc())
														{
															getEnvioGestorDocumentalBusiness()
																    .enviarGestorDocumental(
																    lds_iterador, abpd_DAO, ls_endpoint, ls_usuario,
																    as_remoteIp, ldm_manager
																);

															if(!lds_iterador.isEnviadoOwcc())
																throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

										{
											long   ll_idMotivo;
											String ls_medioNot;

											ll_idMotivo     = 0L;
											ls_medioNot     = StringUtils.getStringNotNull(
												    lso_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_CORREO_ELECTRONICO;
											else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_ORIP;
											else
												ll_idMotivo = MotivoTramiteCommon.NEGACION_APERTURA_ENTREGA_DIRECCION_DE_RESIDENCIA_CORRESPONDENCIA;

											terminarTurnoHistoriaYCrearEtapa(
											    ath_turnoHistoria, ldm_manager,
											    new MotivoTramite(
											        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo
											    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
									else if(ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_PRUEBAS)
									{
										String                          ls_expediente;
										Collection<DocumentosSalida>    lcds_data;
										Collection<PersonaNotificacion> lcpn_personas;

										lcpn_personas     = DaoCreator.getPersonaNotificacionDAO(ldm_manager)
												                          .findByIdTurnoDecision(
												    ls_idTurno, ll_idMotivoAnt, NumericUtils.getLongWrapper(ll_idEtapa)
												);
										ls_expediente     = generarExpediente(
											    ls_usuario, as_remoteIp, lt_turno, ldm_manager
											);

										if(StringUtils.isValidString(ls_expediente))
										{
											lt_turno.setExpediente(ls_expediente);
											lt_DAO.actualizarExpediente(ls_expediente, ls_usuario, ls_idTurno);
										}

										getActuacionesAdministrativasBusiness()
											    .generarDocumentoAutoResolucion(
											    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt
											);

										inactivarDocumentosActivosTurnoHistoriaTipo(
										    ll_idTurnoHistoriaAnterior, TipoArchivoCommon.COMUNICADO, ldm_manager,
										    as_remoteIp, as_usuarioProceso
										);

										inactivarDocumentosActivosTurnoHistoriaTipo(
										    ll_idTurnoHistoriaAnterior, TipoArchivoCommon.COMUNICADO_INDETERMINADO,
										    ldm_manager, as_remoteIp, as_usuarioProceso
										);

										if(CollectionUtils.isValidCollection(lcpn_personas))
										{
											for(PersonaNotificacion lpn_iterador : lcpn_personas)
											{
												if(lpn_iterador != null)
													getActuacionesAdministrativasBusiness()
														    .generarDocumentoComunicado(
														    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt,
														    lpn_iterador
														);
											}
										}

										lcds_data = ldsd_DAO.findByIdTurnoEstadoImpresion(ls_idTurno, false);

										if(CollectionUtils.isValidCollection(lcds_data))
										{
											String ls_endpoint;

											ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
													                    .findString(
													    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													);

											if(StringUtils.isValidString(ls_endpoint))
											{
												for(DocumentosSalida lds_iterador : lcds_data)
												{
													if(lds_iterador != null)
													{
														if(!lds_iterador.isEnviadoOwcc())
														{
															getEnvioGestorDocumentalBusiness()
																    .enviarGestorDocumental(
																    lds_iterador, abpd_DAO, ls_endpoint, ls_usuario,
																    as_remoteIp, ldm_manager
																);

															if(!lds_iterador.isEnviadoOwcc())
																throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

										{
											long   ll_idMotivo;
											String ls_medioNot;

											ll_idMotivo     = 0L;
											ls_medioNot     = StringUtils.getStringNotNull(
												    lso_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_PRUEBAS_220;
											else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_PRUEBAS_200;
											else
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_AUTO_PRUEBAS_215;

											terminarTurnoHistoriaYCrearEtapa(
											    ath_turnoHistoria, ldm_manager,
											    new MotivoTramite(
											        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo
											    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
									else if(ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_DE_LA_DECISION)
									{
										getActuacionesAdministrativasBusiness()
											    .generarDocumentoAutoResolucion(
											    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt
											);

										Collection<DocumentosSalida> lcds_data;

										lcds_data = ldsd_DAO.findByIdTurnoEstadoImpresion(ls_idTurno, false);

										if(CollectionUtils.isValidCollection(lcds_data))
										{
											String ls_endpoint;

											ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
													                    .findString(
													    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													);

											if(StringUtils.isValidString(ls_endpoint))
											{
												for(DocumentosSalida lds_iterador : lcds_data)
												{
													if(lds_iterador != null)
													{
														if(!lds_iterador.isEnviadoOwcc())
														{
															getEnvioGestorDocumentalBusiness()
																    .enviarGestorDocumental(
																    lds_iterador, abpd_DAO, ls_endpoint, ls_usuario,
																    as_remoteIp, ldm_manager
																);

															if(!lds_iterador.isEnviadoOwcc())
																throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

										{
											long   ll_idMotivo;
											String ls_medioNot;

											ll_idMotivo     = 0L;
											ls_medioNot     = StringUtils.getStringNotNull(
												    lso_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_RESOLUCION_DECISION_220;
											else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_RESOLUCION_DECISION_200;
											else
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_RESOLUCION_DECISION_215;

											terminarTurnoHistoriaYCrearEtapa(
											    ath_turnoHistoria, ldm_manager,
											    new MotivoTramite(
											        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo
											    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
									else if(ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_ACLARATORIA_DE_LA_DECISION)
									{
										getActuacionesAdministrativasBusiness()
											    .generarDocumentoAutoResolucion(
											    lot_data, ls_usuario, as_remoteIp, ll_idMotivoAnt
											);

										Collection<DocumentosSalida> lcds_data;

										lcds_data = ldsd_DAO.findByIdTurnoEstadoImpresion(ls_idTurno, false);

										if(CollectionUtils.isValidCollection(lcds_data))
										{
											String ls_endpoint;

											ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
													                    .findString(
													    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
													);

											if(StringUtils.isValidString(ls_endpoint))
											{
												for(DocumentosSalida lds_iterador : lcds_data)
												{
													if(lds_iterador != null)
													{
														if(!lds_iterador.isEnviadoOwcc())
														{
															getEnvioGestorDocumentalBusiness()
																    .enviarGestorDocumental(
																    lds_iterador, abpd_DAO, ls_endpoint, ls_usuario,
																    as_remoteIp, ldm_manager
																);

															if(!lds_iterador.isEnviadoOwcc())
																throw new B2BException(ErrorKeys.ERROR_ENVIO_OWCC);
														}
													}
												}
											}
										}
										else
											throw new B2BException(ErrorKeys.ERROR_DOCUMENTOS_NO_ENCONTRADOS);

										{
											long   ll_idMotivo;
											String ls_medioNot;

											ll_idMotivo     = 0L;
											ls_medioNot     = StringUtils.getStringNotNull(
												    lso_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_220;
											else if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_200;
											else
												ll_idMotivo = MotivoTramiteCommon.NOTIFICACION_PERSONAL_ACLARACION_RESOLUCION_DECISION_215;

											terminarTurnoHistoriaYCrearEtapa(
											    ath_turnoHistoria, ldm_manager,
											    new MotivoTramite(
											        EtapaCommon.ID_ETAPA_APROBACION_CORRECCIONES, ll_idMotivo
											    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
							{
								VolverAEnviarDocumentosOWCC(ath_turnoHistoria, ls_usuario, as_remoteIp, ldm_manager);

								long ll_idMotivo;
								ll_idMotivo = NumericUtils.DEFAULT_LONG_VALUE;

								if(ll_idMotivoAnt == MotivoTramiteCommon.ETAPA_415_RECHAZA_RECURSO)
								{
									TagPlantillaResolucion ltpr_plantillaResol;
									ltpr_plantillaResol = new TagPlantillaResolucion();

									ltpr_plantillaResol.setIdTurnoHistoria(
									    StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria())
									);

									Collection<CausalRechazoRecurso> lccrr_causalesRechazoRecurso;
									boolean                          lb_registrosCargados;

									lccrr_causalesRechazoRecurso     = DaoCreator.getCausalRechazoRecursoDAO(
										    ldm_manager
										).findAll();
									lb_registrosCargados             = false;

									if(CollectionUtils.isValidCollection(lccrr_causalesRechazoRecurso))
									{
										AccCausalRechazoRecursoDAO lacrrd_DAO;

										lacrrd_DAO = DaoCreator.getAccCausalRechazoRecursoDAO(ldm_manager);

										for(CausalRechazoRecurso lcrr_iterador : lccrr_causalesRechazoRecurso)
										{
											if(lcrr_iterador != null)
											{
												String ls_idCausalRechazoRecurso;

												ls_idCausalRechazoRecurso = lcrr_iterador.getIdCausalRechazoRecurso();

												if(StringUtils.isValidString(ls_idCausalRechazoRecurso))
												{
													int     li_contador;
													boolean lb_seleccionado;

													li_contador         = lacrrd_DAO.findCountByIdCausalIdTurno(
														    ls_idCausalRechazoRecurso, ath_turnoHistoria.getIdTurno()
														);
													lb_seleccionado     = li_contador > 0;

													if(lb_seleccionado)
														lb_registrosCargados = true;

													lcrr_iterador.setSeleccionado(lb_seleccionado);
												}
											}
										}

										if(lb_registrosCargados)
											ltpr_plantillaResol.setCausalesRechazaRecurso(lccrr_causalesRechazoRecurso);
									}

									getActuacionesAdministrativasBusiness()
										    .generarResolucionRechazaRecurso(
										    ltpr_plantillaResol, ls_usuario, as_remoteIp, true, true, ldm_manager
										);

									ll_idMotivo = MotivoTramiteCommon.GENERAR_CITATORIO_PARA_RECHAZO_DE_RECURSO;
								}

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS)
							{
								VolverAEnviarDocumentosOWCC(ath_turnoHistoria, ls_usuario, as_remoteIp, ldm_manager);

								long ll_idMotivo;

								ll_idMotivo = 0L;

								if(
								    ll_idMotivoAnt == MotivoTramiteCommon.REVISION_AUTO_DE_PRUEBAS_APROBADA_POR_COORDINADOR_JURIDICO
								)
								{
									SolicitudAsociada lsa_solicitudAsociada;

									lsa_solicitudAsociada = DaoCreator.getSolicitudAsociadaDAO(ldm_manager)
											                              .findByIdSolicitudProceso(
											    lso_solicitud.getIdSolicitud(), ProcesoCommon.ID_PROCESO_47
											);

									if(lsa_solicitudAsociada != null)
									{
										Solicitud ls_solicitudVinculada;

										ls_solicitudVinculada = lsd_DAO.findById(
											    lsa_solicitudAsociada.getIdSolicitud1()
											);

										if(ls_solicitudVinculada != null)
										{
											String ls_autorizaNotificacion;

											ls_autorizaNotificacion = ls_solicitudVinculada
													.getIdAutorizacionNotificacion();

											if(StringUtils.isValidString(ls_autorizaNotificacion))
												ll_idMotivo = ls_autorizaNotificacion.equalsIgnoreCase(
													    MedioNotificarCommon.ORIP
													)
													? MotivoTramiteCommon.GENERAR_CITATORIO_DE_NOTIFICACION_AUTO_DE_PRUEBAS
													: ((ls_autorizaNotificacion.equalsIgnoreCase(
													    MedioNotificarCommon.DIRECCION_RESIDENCIA
													)
														|| ls_autorizaNotificacion.equalsIgnoreCase(
														    MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
														))
													? MotivoTramiteCommon.ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORRESPONDENCIA
													: (ls_autorizaNotificacion.equalsIgnoreCase(
													    MedioNotificarCommon.CORREO_ELECTRONICO
													)
													? MotivoTramiteCommon.ETAPA_190_AUTO_PRUEBAS_APROBADO_ESPERA_PRUEBAS_ENTREGA_CORREO
													: 0L));
										}
									}
								}
								else if(
								    ll_idMotivoAnt == MotivoTramiteCommon.REVISION_RESOLUCION_APROBADA_POR_COORDINADOR_JURIDICO
								)
									ll_idMotivo = MotivoTramiteCommon.GENERAR_CITATORIO_DE_NOTIFICACION_RESOLUCION;

								terminarTurnoHistoriaYCrearEtapa(
								    ath_turnoHistoria, ldm_manager,
								    new MotivoTramite(EtapaCommon.ID_ETAPA_APROBACION, ll_idMotivo), ls_usuario,
								    as_remoteIp, EstadoCommon.TERMINADA
								);

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.INSCRITO_ADJUDICACION_DE_BALDIO)
							)
							{
								RegistroCalificacion lotc_tc;
								lotc_tc = new RegistroCalificacion();
								lotc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
								lotc_tc.setSalvar(true);
								getRegistroCalificacionBusiness()
									    .genereteFileComunicadoDireccion(
									    ldm_manager, lotc_tc, ls_usuario, as_remoteIp, true
									);
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.REGISTRO, ls_usuario, as_remoteIp, true, ldm_manager
								);

								MotivoTramite     lmt_motivo;
								long              ll_idMotivo;
								ProcedimientosDAO lpd_DAO;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = 0;
								lpd_DAO         = DaoCreator.getProcedimientosDAO(ldm_manager);

								if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
								else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.DEFAULT;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										lpd_DAO.procCrearMatriculaAntSistema(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);

										TurnoDerivado             ltd_turnoDerivado;
										Collection<TurnoDerivado> lctd_turnosDerivados;

										ltd_turnoDerivado = new TurnoDerivado();
										ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

										lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
												                             .findByIdTurnoPadre(ltd_turnoDerivado);

										if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
										{
											for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
											{
												if(ltd_iterador != null)
												{
													SolicitudMatricula lsm_solicitudMatricula;
													String             ls_turnoHijo;

													lsm_solicitudMatricula     = new SolicitudMatricula();
													ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

													lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
													lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

													lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
														    ldm_manager
														).findByTurnoCertificado(lsm_solicitudMatricula);

													if(lsm_solicitudMatricula != null)
													{
														BigDecimal lbd_cantidadCertificados;
														lbd_cantidadCertificados = lsm_solicitudMatricula
																.getCantidadCertificados();

														if(lbd_cantidadCertificados != null)
														{
															Double ld_cantidad;
															int    li_cantidad;

															ld_cantidad     = NumericUtils.getDoubleWrapper(
																    lbd_cantidadCertificados
																);
															li_cantidad     = (ld_cantidad != null)
																? NumericUtils.getInt(ld_cantidad) : 0;

															lsm_solicitudMatricula.setTurnoHistoria(ath_turnoHistoria);

															for(int li_i = 1; li_i <= li_cantidad; li_i++)
																generarCertificadoTradicionLibertad(
																    lsm_solicitudMatricula, IdentificadoresCommon.FIRMA,
																    true, ldm_manager, as_usuarioProceso, as_remoteIp
																);
														}
													}

													{
														TurnoHistoria lth_turnoDerivado;
														lth_turnoDerivado = new TurnoHistoria();

														lth_turnoDerivado.setIdTurno(ls_turnoHijo);
														lth_turnoDerivado.setIdEtapa(
														    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
														);
														lth_turnoDerivado.setEstadoActividad(EstadoCommon.AUTOMATICA);

														lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
															    lth_turnoDerivado
															);

														if(lth_turnoDerivado != null)
														{
															lth_turnoDerivado.setEstadoActividad(
															    EstadoCommon.TERMINADA
															);
															lth_turnoDerivado.setMotivo(
															    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
															);
															lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
															lth_turnoDerivado.setIpModificacion(as_remoteIp);

															lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
														}
													}
												}
											}
										}

										generarPlantillaRegistro(
										    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
										    lso_solicitud.getNir(), null, true
										);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.REALIZAR_REGISTRO_DESENGLOBE
								    )
							)
							{
								RegistroCalificacion lotc_tc;
								lotc_tc = new RegistroCalificacion();
								lotc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
								lotc_tc.setSalvar(true);
								getRegistroCalificacionBusiness()
									    .genereteFileComunicadoDireccion(
									    ldm_manager, lotc_tc, ls_usuario, as_remoteIp, true
									);
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.REGISTRO, ls_usuario, as_remoteIp, true, ldm_manager
								);

								MotivoTramite     lmt_motivo;
								long              ll_idMotivo;
								ProcedimientosDAO lpd_DAO;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = 0;
								lpd_DAO         = DaoCreator.getProcedimientosDAO(ldm_manager);

								if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
								else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.DEFAULT;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										lpd_DAO.procRealizarRegistro(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										{
											TurnoHistoria lth_temp;

											lth_temp = new TurnoHistoria();

											lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
											lth_temp.setIdUsuarioCreacion(ls_usuario);
											lth_temp.setIpCreacion(as_remoteIp);

											lpd_DAO.procCerrarFolio(lth_temp);
										}

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procCrearMatriculaAntSistema(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);

										TurnoDerivado             ltd_turnoDerivado;
										Collection<TurnoDerivado> lctd_turnosDerivados;

										ltd_turnoDerivado = new TurnoDerivado();
										ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

										lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
												                             .findByIdTurnoPadre(ltd_turnoDerivado);

										if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
										{
											for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
											{
												if(ltd_iterador != null)
												{
													SolicitudMatricula lsm_solicitudMatricula;
													String             ls_turnoHijo;

													lsm_solicitudMatricula     = new SolicitudMatricula();
													ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

													lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
													lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

													lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
														    ldm_manager
														).findByTurnoCertificado(lsm_solicitudMatricula);

													if(lsm_solicitudMatricula != null)
													{
														BigDecimal lbd_cantidadCertificados;
														lbd_cantidadCertificados = lsm_solicitudMatricula
																.getCantidadCertificados();

														if(lbd_cantidadCertificados != null)
														{
															Double ld_cantidad;
															int    li_cantidad;

															ld_cantidad     = NumericUtils.getDoubleWrapper(
																    lbd_cantidadCertificados
																);
															li_cantidad     = (ld_cantidad != null)
																? NumericUtils.getInt(ld_cantidad) : 0;

															lsm_solicitudMatricula.setTurnoHistoria(ath_turnoHistoria);

															for(int li_i = 1; li_i <= li_cantidad; li_i++)
																generarCertificadoTradicionLibertad(
																    lsm_solicitudMatricula, IdentificadoresCommon.FIRMA,
																    true, ldm_manager, as_usuarioProceso, as_remoteIp
																);
														}
													}

													{
														TurnoHistoria lth_turnoDerivado;
														lth_turnoDerivado = new TurnoHistoria();

														lth_turnoDerivado.setIdTurno(ls_turnoHijo);
														lth_turnoDerivado.setIdEtapa(
														    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
														);
														lth_turnoDerivado.setEstadoActividad(EstadoCommon.AUTOMATICA);

														lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
															    lth_turnoDerivado
															);

														if(lth_turnoDerivado != null)
														{
															lth_turnoDerivado.setEstadoActividad(
															    EstadoCommon.TERMINADA
															);
															lth_turnoDerivado.setMotivo(
															    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
															);
															lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
															lth_turnoDerivado.setIpModificacion(as_remoteIp);

															lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
														}
													}
												}
											}
										}

										generarPlantillaRegistro(
										    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
										    lso_solicitud.getNir()
										);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.REALIZAR_REGISTRO)
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.REALIZAR_REGISTRO_CANCELACION
								    )
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_REMATE
								    )
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_LIQUIDACION_JUDICIAL
								    )
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.INSCRITO_CON_ACLARACION
								    ) || lb_cancelacionAutomatica
							)
							{
								if(validarTurnosAsociados(ath_turnoHistoria, ldm_manager))
								{
									{
										boolean lb_entregar;

										String  ls_tipoArchivo;

										lb_entregar = ls_motivoNoTramite.equalsIgnoreCase(
											    MotivonNoTramiteCommon.REALIZAR_REGISTRO
											);

										if(
										    lb_cancelacionAutomatica
											    || ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.REALIZAR_REGISTRO
											    )
										)
											ls_tipoArchivo = TipoArchivoCommon.REGISTRO;
										else if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.REALIZAR_REGISTRO_CANCELACION
											    )
										)
										{
											ls_tipoArchivo = TipoArchivoCommon.COMUNICADO_CANCELACION;

											RegistroCalificacion lotc_tc;
											lotc_tc = new RegistroCalificacion();
											lotc_tc.setIdTurnoHistoria(
											    NumericUtils.getLongWrapper((ath_turnoHistoria.getIdAnterior()))
											);
											lotc_tc.setSalvar(true);
											lotc_tc.setUserId(ls_usuario);
											getRegistroCalificacionBusiness()
												    .genereteFileCancelacion(
												    ldm_manager, lotc_tc, true, ls_usuario, as_remoteIp
												);
										}
										else if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_REMATE
											    )
										)
										{
											ls_tipoArchivo     = TipoArchivoCommon.COMUNICADO_CANCELACION;
											lb_entregar        = true;
										}
										else
											ls_tipoArchivo = IdentificadoresCommon.DATO_INVALIDO;

										firmarDocumentos(
										    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION, ls_tipoArchivo,
										    ls_usuario, as_remoteIp, lb_entregar, ldm_manager
										);

										if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.REALIZAR_REGISTRO
											    )
										)
											firmarDocumentos(
											    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
											    TipoArchivoCommon.NOTA_INFORMATIVA_PAGO_EN_EXCESO, ls_usuario,
											    as_remoteIp, lb_entregar, ldm_manager
											);

										RegistroCalificacion lotc_tc;
										lotc_tc = new RegistroCalificacion();
										lotc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
										lotc_tc.setSalvar(true);
										getRegistroCalificacionBusiness()
											    .genereteFileComunicadoDireccion(
											    ldm_manager, lotc_tc, ls_usuario, as_remoteIp, true
											);
									}

									MotivoTramite lmt_motivo;
									long          ll_idMotivo;

									lmt_motivo      = new MotivoTramite();
									ll_idMotivo     = 0;

									if(
									    ls_idAutorizaNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.CORREO_ELECTRONICO
										    )
									)
										ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
									else if(
									    ls_idAutorizaNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_RESIDENCIA
										    )
									)
										ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
									else if(
									    ls_idAutorizaNotificacion.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
										ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
									else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
										ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
									else
										ll_idMotivo = MotivoTramiteCommon.DEFAULT;

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
									lmt_motivo.setIdMotivo(ll_idMotivo);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if(lmt_motivo != null)
									{
										ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

										if(ath_turnoHistoria != null)
										{
											ProcedimientosDAO lpd_DAO;

											lpd_DAO = DaoCreator.getProcedimientosDAO(ldm_manager);

											ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
											ath_turnoHistoria.setIdMotivo(
											    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
											);
											ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
											ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
											ath_turnoHistoria.setIpModificacion(as_remoteIp);

											lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

											lpd_DAO.procRealizarRegistro(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

											{
												TurnoHistoria lth_temp;

												lth_temp = new TurnoHistoria();

												lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
												lth_temp.setIdUsuarioCreacion(ls_usuario);
												lth_temp.setIpCreacion(as_remoteIp);

												lpd_DAO.procCerrarFolio(lth_temp);
											}

											lpd_DAO.procDesbloqueoMatriculas(
											    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
											);

											lpd_DAO.spCreateStage(ath_turnoHistoria);
										}
									}

									{
										TurnoDerivado             ltd_turnoDerivado;
										Collection<TurnoDerivado> lctd_turnosDerivados;

										ltd_turnoDerivado = new TurnoDerivado();
										ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

										lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
												                             .findByIdTurnoPadre(ltd_turnoDerivado);

										if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
										{
											for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
											{
												if((ltd_iterador != null) && !(ltd_iterador.isIndVinculado()))
												{
													SolicitudMatricula lsm_solicitudMatricula;
													String             ls_turnoHijo;

													lsm_solicitudMatricula     = new SolicitudMatricula();
													ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

													lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
													lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

													lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
														    ldm_manager
														).findByTurnoCertificado(lsm_solicitudMatricula);

													if(lsm_solicitudMatricula != null)
													{
														BigDecimal lbd_cantidadCertificados;
														lbd_cantidadCertificados = lsm_solicitudMatricula
																.getCantidadCertificados();

														if(lbd_cantidadCertificados != null)
														{
															Double ld_cantidad;
															int    li_cantidad;

															ld_cantidad     = NumericUtils.getDoubleWrapper(
																    lbd_cantidadCertificados
																);
															li_cantidad     = (ld_cantidad != null)
																? NumericUtils.getInt(ld_cantidad) : 0;

															lsm_solicitudMatricula.setTurnoHistoria(ath_turnoHistoria);

															for(int li_i = 1; li_i <= li_cantidad; li_i++)
																generarCertificadoTradicionLibertad(
																    lsm_solicitudMatricula, IdentificadoresCommon.FIRMA,
																    true, ldm_manager, as_usuarioProceso, as_remoteIp
																);
														}
													}

													{
														TurnoHistoria lth_turnoDerivado;
														lth_turnoDerivado = new TurnoHistoria();

														lth_turnoDerivado.setIdTurno(ls_turnoHijo);
														lth_turnoDerivado.setIdEtapa(
														    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
														);
														lth_turnoDerivado.setEstadoActividad(EstadoCommon.AUTOMATICA);

														lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
															    lth_turnoDerivado
															);

														if(lth_turnoDerivado != null)
														{
															lth_turnoDerivado.setMotivo(
															    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
															);

															terminarTurnoHistoriaYCrearEtapa(
															    lth_turnoDerivado, ldm_manager,
															    new MotivoTramite(
															        EtapaCommon.ID_ETAPA_30,
															        MotivoTramiteCommon.PROCESO_DE_REGISTRO_FINALIZADO
															    ), ls_usuario, as_remoteIp, EstadoCommon.TERMINADA,
															    false, true, false, false
															);
														}
													}
												}
											}
										}
									}

									if(
									    ls_motivoNoTramite.equalsIgnoreCase(
										        MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_REMATE
										    )
										    || ls_motivoNoTramite.equalsIgnoreCase(
										        MotivonNoTramiteCommon.INSCRITO_CON_ADJUDICACION_EN_LIQUIDACION_JUDICIAL
										    )
										    || ls_motivoNoTramite.equalsIgnoreCase(
										        MotivonNoTramiteCommon.INSCRITO_CON_ACLARACION
										    )
										    || ls_motivoNoTramite.equalsIgnoreCase(
										        MotivonNoTramiteCommon.REALIZAR_REGISTRO_CANCELACION
										    )
										    || ls_motivoNoTramite.equalsIgnoreCase(
										        MotivonNoTramiteCommon.REALIZAR_REGISTRO
										    ) || lb_cancelacionAutomatica
									)
										generarPlantillaRegistro(
										    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
										    lso_solicitud.getNir(), null, true
										);

									DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.APROBADO_SOLICITUD_COMPLEMENTACION
								    )
							)
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.SOLICITUD_AMPLIACION, ls_usuario, as_remoteIp, true, ldm_manager
								);

								MotivoTramite lmt_motivo;
								long          ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = MotivoTramiteCommon.AMPLIAR_COMPLEMENTACION_DE_OTRO_CIRCULO;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(ls_usuario);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.AMPLIAR_COMPLEMENTACION_DE_OTRO_CIRCULO
								    )
							)
							{
								MotivoTramite lmt_motivo;
								long          ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = MotivoTramiteCommon.ANALISIS_DE_COMPLETITUD_COMPLEMENTACION;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(ls_motivoNoTramite.contains(MotivonNoTramiteCommon.REPRODUCCION_DE_CONSTANCIA))
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.CONSTANCIA_REPRODUCCION, ls_usuario, as_remoteIp, true,
								    ldm_manager
								);

								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.REGISTRO, ls_usuario, as_remoteIp, true, ldm_manager
								);

								String ls_idCirculo;
								Turno  lt_turno;

								ls_idCirculo     = null;
								lt_turno         = new Turno();

								lt_turno.setIdTurno(ath_turnoHistoria.getIdTurno());

								lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findById(lt_turno);

								if(lt_turno != null)
									ls_idCirculo = lt_turno.getIdCirculo();

								if(StringUtils.isValidString(ls_idCirculo))
								{
									Acto la_acto;
									la_acto = new Acto();

									la_acto.setIdSolicitud(ls_idSolicitud);
									la_acto.setIdCirculo(ls_idCirculo);
									la_acto.setIdTipoActo(TipoActoCommon.REPRODUCCION_CONSTANCIA_DE_INSCRIPCION);

									la_acto = DaoCreator.getActoDAO(ldm_manager)
											                .findBySolicitudIdCirculoTipoActo(la_acto);

									if(la_acto != null)
									{
										BigDecimal lbd_cantidadActos;
										lbd_cantidadActos = la_acto.getCantidadActos();

										if(NumericUtils.isValidBigDecimal(lbd_cantidadActos))
										{
											int li_cantidadActos;

											li_cantidadActos = NumericUtils.getInt(lbd_cantidadActos);

											{
												DocumentosSalida             lds_documento;
												Collection<DocumentosSalida> lcds_documentosSalida;

												lds_documento = new DocumentosSalida();

												lds_documento.setIdSolicitud(ls_idSolicitud);
												lds_documento.setIdTurnoHistoria(
												    NumericUtils.getInteger(ath_turnoHistoria.getIdTurnoHistoria())
												);
												lds_documento.setTipo(TipoArchivoCommon.CONSTANCIA_REPRODUCCION);
												lds_documento.setEstado(EstadoCommon.ACTIVO);
												lcds_documentosSalida = ldsd_DAO
														.findAllDocumentByTurnoHistoriaTipoEstado(lds_documento);

												if(CollectionUtils.isValidCollection(lcds_documentosSalida))
													li_cantidadActos = li_cantidadActos - lcds_documentosSalida.size();
											}

											if(li_cantidadActos > 0)
											{
												ConstanciaReproduccion lcr_constanciaReproduccion;
												SolicitudReproduccion  lsr_solicitudReproduccion;

												lcr_constanciaReproduccion     = new ConstanciaReproduccion();
												lsr_solicitudReproduccion      = new SolicitudReproduccion();

												lsr_solicitudReproduccion.setIdSolicitud(ls_idSolicitud);
												lsr_solicitudReproduccion.setIdCirculo(ls_idCirculo);

												lsr_solicitudReproduccion = DaoCreator.getConsultaSolicitudReproduccionDAO(
													    ldm_manager
													).findByIdSolicitudIdCirculo(lsr_solicitudReproduccion, true);

												lcr_constanciaReproduccion.setSolicitudReproduccion(
												    lsr_solicitudReproduccion
												);
												lcr_constanciaReproduccion.setTurnoHistoria(ath_turnoHistoria);

												for(int li_i = 0; li_i < li_cantidadActos; li_i++)
												{
													// TODO
													getConstanciaReproduccionBusiness()
														    .generarConstanciaReproduccion(
														    lcr_constanciaReproduccion, true, abpd_DAO, ls_usuario,
														    as_remoteIp, ldm_manager
														);

													//													generarPlantillaRegistro(
													//													    ath_turnoHistoria, ldm_manager, as_usuarioProceso, as_remoteIp,
													//													    lso_solicitud.getNir(),
													//													    lsr_solicitudReproduccion.getIdTurnoReproduccion(), true
													//													);
												}
											}
										}
									}
								}

								{
									MotivoTramite lmt_motivo;
									long          ll_idMotivo;

									lmt_motivo      = new MotivoTramite();
									ll_idMotivo     = MotivoTramiteCommon.ENTREGA_REPRODUCCION_CONSTANCIA_ORIP;

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
									lmt_motivo.setIdMotivo(ll_idMotivo);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if(lmt_motivo != null)
									{
										ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

										if(ath_turnoHistoria != null)
										{
											ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
											ath_turnoHistoria.setIdMotivo(
											    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
											);
											ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
											ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
											ath_turnoHistoria.setIpModificacion(as_remoteIp);

											lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

											DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(
											    ath_turnoHistoria
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
								}
							}
							else if(ls_motivoNoTramite.contains(MotivonNoTramiteCommon.APERTURA))
							{
								firmarDocumentos(
								    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
								    TipoArchivoCommon.REGISTRO, ls_usuario, as_remoteIp, true, ldm_manager
								);

								if(
								    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
									    && (ll_idMotivoAnt == MotivoTramiteCommon.REGISTRO_DIGITADOR_MASIVO)
								)
								{
									RegistroCalificacion lotc_tc;
									lotc_tc = new RegistroCalificacion();
									lotc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
									lotc_tc.setSalvar(true);
									getRegistroCalificacionBusiness()
										    .genereteFileComunicadoDireccion(
										    ldm_manager, lotc_tc, ls_usuario, as_remoteIp, true
										);
								}

								MotivoTramite     lmt_motivo;
								long              ll_idMotivo;
								boolean           lb_digitadorMasivo;
								ProcedimientosDAO lpd_DAO;

								lmt_motivo             = new MotivoTramite();
								ll_idMotivo            = 0;
								lpd_DAO                = DaoCreator.getProcedimientosDAO(ldm_manager);
								lb_digitadorMasivo     = ls_motivoNoTramite.equalsIgnoreCase(
									    MotivonNoTramiteCommon.REGISTRO_DIGITADOR_MASIVO
									);

								if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
								else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.DEFAULT;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										if(lb_digitadorMasivo)
										{
											lpd_DAO.procRealizarRegistro(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

											{
												TurnoHistoria lth_temp;

												lth_temp = new TurnoHistoria();

												lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
												lth_temp.setIdUsuarioCreacion(ls_usuario);
												lth_temp.setIpCreacion(as_remoteIp);

												lpd_DAO.procCerrarFolio(lth_temp);
											}

											lpd_DAO.procDesbloqueoMatriculas(
											    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
											);
										}

										lpd_DAO.procCrearMatriculaAntSistema(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);

										if(lb_digitadorMasivo)
										{
											TurnoDerivado             ltd_turnoDerivado;
											Collection<TurnoDerivado> lctd_turnosDerivados;

											ltd_turnoDerivado = new TurnoDerivado();
											ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

											lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
													                             .findByIdTurnoPadre(ltd_turnoDerivado);

											if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
											{
												for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
												{
													if(ltd_iterador != null)
													{
														SolicitudMatricula lsm_solicitudMatricula;
														String             ls_turnoHijo;

														lsm_solicitudMatricula     = new SolicitudMatricula();
														ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

														lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
														lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

														lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
															    ldm_manager
															).findByTurnoCertificado(lsm_solicitudMatricula);

														if(lsm_solicitudMatricula != null)
														{
															BigDecimal lbd_cantidadCertificados;
															lbd_cantidadCertificados = lsm_solicitudMatricula
																	.getCantidadCertificados();

															if(lbd_cantidadCertificados != null)
															{
																Double ld_cantidad;
																int    li_cantidad;

																ld_cantidad     = NumericUtils.getDoubleWrapper(
																	    lbd_cantidadCertificados
																	);
																li_cantidad     = (ld_cantidad != null)
																	? NumericUtils.getInt(ld_cantidad) : 0;

																lsm_solicitudMatricula.setTurnoHistoria(
																    ath_turnoHistoria
																);

																for(int li_i = 1; li_i <= li_cantidad; li_i++)
																	generarCertificadoTradicionLibertad(
																	    lsm_solicitudMatricula,
																	    IdentificadoresCommon.FIRMA, true, ldm_manager,
																	    as_usuarioProceso, as_remoteIp
																	);
															}
														}

														{
															TurnoHistoria lth_turnoDerivado;
															lth_turnoDerivado = new TurnoHistoria();

															lth_turnoDerivado.setIdTurno(ls_turnoHijo);
															lth_turnoDerivado.setIdEtapa(
															    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
															);
															lth_turnoDerivado.setEstadoActividad(
															    EstadoCommon.AUTOMATICA
															);

															lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
																    lth_turnoDerivado
																);

															if(lth_turnoDerivado != null)
															{
																lth_turnoDerivado.setEstadoActividad(
																    EstadoCommon.TERMINADA
																);
																lth_turnoDerivado.setMotivo(
																    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
																);
																lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
																lth_turnoDerivado.setIpModificacion(as_remoteIp);

																lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
															}
														}
													}
												}
											}

											generarPlantillaRegistro(
											    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
											    lso_solicitud.getNir()
											);
										}

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(MotivonNoTramiteCommon.APROBADO_ANTIGUO_SISTEMA)
								    || ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA
								    )
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S)
								    && (ll_idMotivoActual == MotivoTramiteCommon.ENVIAR_A_CALIFICACION))
							)
							{
								{
									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
										    TipoArchivoCommon.CREACION_MATRICULA, ls_usuario, as_remoteIp, true,
										    ldm_manager
										);
								}

								{
									Collection<DocumentosSalida> lcds_documentos;
									DocumentosSalida             lds_docSalida;

									lds_docSalida = new DocumentosSalida();

									lds_docSalida.setIdTurno(ath_turnoHistoria.getIdTurno());
									lds_docSalida.setTipo(TipoArchivoCommon.CREACION_MATRICULA);

									lcds_documentos = ldsd_DAO.findByIdTurnoTipo(lds_docSalida);

									if(CollectionUtils.isValidCollection(lcds_documentos))
									{
										for(DocumentosSalida lds_doc : lcds_documentos)
										{
											if(lds_doc != null)
											{
												lds_doc.setEstado(EstadoCommon.ENTREGA);
												lds_doc.setIdUsuarioModificacion(ls_usuario);
												lds_doc.setIpModificacion(as_remoteIp);

												ldsd_DAO.insertOrUpdate(lds_doc, false);
											}
										}
									}
								}

								MotivoTramite lmt_motivo;
								long          ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = MotivoTramiteCommon.ENVIAR_A_CALIFICACION;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getProcedimientosDAO(ldm_manager)
											          .procCrearMatriculaAntSistema(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ((ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
								    && ((ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA_PARA_CERTIFICADOS)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CERTIFICADOS)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CERTIFICADOS)))
								    || ((ll_idEtapa == EtapaCommon.ID_ETAPA_AUTORIZACION_FIRMA_LIBROS_A_S)
								    && (ll_idMotivoActual == MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS))
							)
							{
								{
									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
										    TipoArchivoCommon.CREACION_MATRICULA, ls_usuario, as_remoteIp, true,
										    ldm_manager
										);
								}

								{
									DocumentosSalida lds_docSalida;
									lds_docSalida = new DocumentosSalida();

									lds_docSalida.setIdTurno(ath_turnoHistoria.getIdTurno());
									lds_docSalida.setTipo(TipoArchivoCommon.CREACION_MATRICULA);

									Collection<DocumentosSalida> lcds_documentos;
									lcds_documentos = ldsd_DAO.findByIdTurnoTipo(lds_docSalida);

									if(CollectionUtils.isValidCollection(lcds_documentos))
									{
										for(DocumentosSalida lds_doc : lcds_documentos)
										{
											if(lds_doc != null)
											{
												lds_doc.setEstado(EstadoCommon.ENTREGA);
												lds_doc.setIdUsuarioModificacion(ls_usuario);
												lds_doc.setIpModificacion(as_remoteIp);

												ldsd_DAO.insertOrUpdate(lds_doc, false);
											}
										}
									}
								}

								MotivoTramite lmt_motivo;

								lmt_motivo = new MotivoTramite();

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_MAYOR_VALOR);
								lmt_motivo.setIdMotivo(
								    MotivoTramiteCommon.CREACION_DE_MATRICULA_EXISTOSA_PARA_CERTIFICADOS
								);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getProcedimientosDAO(ldm_manager)
											          .procCrearMatriculaAntSistema(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ls_motivoNoTramite.equalsIgnoreCase(
								        MotivonNoTramiteCommon.APROBADO_RECHAZAR_SOLICITUD_DESDE_ANTIGUO_SISTEMA
								    )
							)
							{
								{
									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
										    TipoArchivoCommon.NOTA_DEVOLUTIVA, ls_usuario, as_remoteIp, true,
										    ldm_manager
										);
								}

								MotivoTramite lmt_motivo;
								long          ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_ORIP;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(ls_motivoNoTramite.contains(MotivonNoTramiteCommon.REALIZAR_REGISTRO_PARCIAL))
							{
								MotivoTramite lmt_motivo;
								long          ll_idMotivo;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = 0;

								if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_CORREO_ELECTRONICO;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_RESIDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_RESIDENCIA;
								else if(
								    ls_idAutorizaNotificacion.equalsIgnoreCase(
									        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
									    )
								)
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_DIRECCION_CORRESPONDENCIA;
								else if(ls_idAutorizaNotificacion.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.INSCRIPCION_REGISTRO_ORIP;
								else
									ll_idMotivo = MotivoTramiteCommon.DEFAULT;

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ProcedimientosDAO lpd_DAO;
										lpd_DAO = DaoCreator.getProcedimientosDAO(ldm_manager);

										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										lpd_DAO.procRealizarRegistro(
										    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
										);

										{
											TurnoHistoria lth_temp;

											lth_temp = new TurnoHistoria();

											lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
											lth_temp.setIdUsuarioCreacion(ls_usuario);
											lth_temp.setIpCreacion(as_remoteIp);

											lpd_DAO.procCerrarFolio(lth_temp);
										}

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);
									}
								}

								{
									TurnoDerivado             ltd_turnoDerivado;
									Collection<TurnoDerivado> lctd_turnosDerivados;

									ltd_turnoDerivado = new TurnoDerivado();
									ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

									lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
											                             .findByIdTurnoPadre(ltd_turnoDerivado);

									if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
									{
										for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
										{
											if(ltd_iterador != null)
											{
												SolicitudMatricula lsm_solicitudMatricula;
												String             ls_turnoHijo;

												lsm_solicitudMatricula     = new SolicitudMatricula();
												ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

												lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
												lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

												lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
													    ldm_manager
													).findByTurnoCertificado(lsm_solicitudMatricula);

												if(lsm_solicitudMatricula != null)
												{
													BigDecimal lbd_cantidadCertificados;
													lbd_cantidadCertificados = lsm_solicitudMatricula
															.getCantidadCertificados();

													if(lbd_cantidadCertificados != null)
													{
														Double ld_cantidad;
														int    li_cantidad;

														ld_cantidad     = NumericUtils.getDoubleWrapper(
															    lbd_cantidadCertificados
															);
														li_cantidad     = (ld_cantidad != null)
															? NumericUtils.getInt(ld_cantidad) : 0;

														lsm_solicitudMatricula.setTurnoHistoria(ath_turnoHistoria);

														for(int li_i = 1; li_i <= li_cantidad; li_i++)
															generarCertificadoTradicionLibertad(
															    lsm_solicitudMatricula, IdentificadoresCommon.FIRMA,
															    true, ldm_manager, as_usuarioProceso, as_remoteIp
															);
													}
												}

												{
													TurnoHistoria lth_turnoDerivado;
													lth_turnoDerivado = new TurnoHistoria();

													lth_turnoDerivado.setIdTurno(ls_turnoHijo);
													lth_turnoDerivado.setIdEtapa(
													    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
													);
													lth_turnoDerivado.setEstadoActividad(EstadoCommon.AUTOMATICA);

													lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(lth_turnoDerivado);

													if(lth_turnoDerivado != null)
													{
														lth_turnoDerivado.setEstadoActividad(EstadoCommon.TERMINADA);
														lth_turnoDerivado.setMotivo(
														    MotivonNoTramiteCommon.CERTIFICADO_EXPEDIDO_POR_INSCRIPCION_DE_REGISTRO
														);
														lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
														lth_turnoDerivado.setIpModificacion(as_remoteIp);

														lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
													}
												}
											}
										}
									}
								}

								DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
							}
							else if(ls_motivoNoTramite.contains(MotivonNoTramiteCommon.MEDIDAS_CAUTELARES))
							{
								{
									Collection<String> lcs_tipoArchivos;

									lcs_tipoArchivos = new ArrayList<String>();

									lcs_tipoArchivos.add(TipoArchivoCommon.COMUNICADO_DEMANDA);
									lcs_tipoArchivos.add(TipoArchivoCommon.NOTA_DEVOLUTIVA);
									lcs_tipoArchivos.add(TipoArchivoCommon.REGISTRO);

									for(String ls_actual : lcs_tipoArchivos)
									{
										if(StringUtils.isValidString(ls_actual))
											firmarDocumentos(
											    ath_turnoHistoria, ConstanteCommon.USUARIO_FIRMA_SUSPENSION, ls_actual,
											    ls_usuario, as_remoteIp, true, ldm_manager
											);
									}
								}

								MotivoTramite     lmt_motivo;
								long              ll_idMotivo;
								ProcedimientosDAO lpd_DAO;

								lmt_motivo      = new MotivoTramite();
								ll_idMotivo     = 0;
								lpd_DAO         = DaoCreator.getProcedimientosDAO(ldm_manager);

								if(
								    (ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
									    && ((ll_idMotivoAnt == MotivoTramiteCommon.INSCRITO_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL)
									    || (ll_idMotivoAnt == MotivoTramiteCommon.NOTA_DEVOLUTIVA_MEDIDA_CAUTELAR)
									    || (ll_idMotivoAnt == MotivoTramiteCommon.REGISTRO_MEDIDA_CAUTELAR))
								)
								{
									OficinaOrigen loo_oficinaOrigen;

									loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(ldm_manager)
											                          .findBySolicitud(ls_idSolicitud);

									if(loo_oficinaOrigen != null)
									{
										RegistroCalificacion lrc_registroCalificacion;

										lrc_registroCalificacion = new RegistroCalificacion();

										lrc_registroCalificacion.setIdTurnoHistoria(
										    ath_turnoHistoria.getIdTurnoHistoria()
										);

										lrc_registroCalificacion.setOficinaOrigenMedidaCautelar(loo_oficinaOrigen);

										if(ll_idMotivoAnt == MotivoTramiteCommon.NOTA_DEVOLUTIVA_MEDIDA_CAUTELAR)
										{
											lrc_registroCalificacion.setNotaDevolutivaMedidaCautelar(true);

											Collection<Acto> lca_actos;
											String           ls_idCirculo;

											lca_actos        = DaoCreator.getActoDAO(ldm_manager)
													                         .findByIdSolicitud(ls_idSolicitud);
											ls_idCirculo     = "";

											{
												Turno lt_turno;

												lt_turno = DaoCreator.getTurnoDAO(ldm_manager)
														                 .findById(ath_turnoHistoria.getIdTurno());

												if(lt_turno != null)
													ls_idCirculo = lt_turno.getIdCirculo();
											}

											if(CollectionUtils.isValidCollection(lca_actos))
											{
												String         ls_referencia;
												String         ls_numeroProceso;
												Iterator<Acto> lia_iterador;

												ls_referencia        = null;
												ls_numeroProceso     = null;
												lia_iterador         = lca_actos.iterator();

												while(
												    lia_iterador.hasNext() && (ls_referencia == null)
													    && (ls_numeroProceso == null)
												)
												{
													Acto la_acto;

													la_acto = lia_iterador.next();

													if(
													    (la_acto != null)
														    && ls_idCirculo.equals(
														        StringUtils.getStringNotNull(la_acto.getIdCirculo())
														    )
													)
													{
														ls_referencia        = la_acto.getReferencia();
														ls_numeroProceso     = la_acto.getNumeroProceso();
													}
												}

												lrc_registroCalificacion.setReferencia(ls_referencia);
												lrc_registroCalificacion.setNumeroProceso(ls_numeroProceso);
											}
										}
										else
											lrc_registroCalificacion.setMedidaCautelar(true);

										lrc_registroCalificacion.setSalvar(true);
										lrc_registroCalificacion.setUserId(ls_usuario);

										getRegistroCalificacionBusiness()
											    .genereteFileCancelacion(
											    ldm_manager, lrc_registroCalificacion, true, ls_usuario, as_remoteIp
											);

										if(ll_idMotivoAnt == MotivoTramiteCommon.REGISTRO_MEDIDA_CAUTELAR)
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_MEDIDA_CAUTELAR;
										else
											ll_idMotivo = MotivoTramiteCommon.ENTREGA_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL;
									}
								}

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_idMotivo);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										if(
										    ls_motivoNoTramite.equalsIgnoreCase(
											        MotivonNoTramiteCommon.REALIZAR_REGISTRO_MEDIDAS_CAUTELARES
											    )
										)
										{
											lpd_DAO.procRealizarRegistro(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);
											generarPlantillaRegistro(
											    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
											    lso_solicitud.getNir(), null, true
											);
										}

										if(
										    ll_idMotivoAnt == MotivoTramiteCommon.INSCRITO_MEDIDAS_CAUTELARES_CON_REGISTRO_PARCIAL
										)
											generarPlantillaRegistro(
											    ath_turnoHistoria, ldm_manager, ls_usuario, as_remoteIp,
											    lso_solicitud.getNir(), null, true
											);

										{
											TurnoHistoria lth_temp;

											lth_temp = new TurnoHistoria();

											lth_temp.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
											lth_temp.setIdUsuarioCreacion(ls_usuario);
											lth_temp.setIpCreacion(as_remoteIp);

											lpd_DAO.procCerrarFolio(lth_temp);
										}

										lpd_DAO.procDesbloqueoMatriculas(
										    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
										);

										lpd_DAO.spCreateStage(ath_turnoHistoria);

										{
											TurnoDerivado             ltd_turnoDerivado;
											Collection<TurnoDerivado> lctd_turnosDerivados;

											ltd_turnoDerivado = new TurnoDerivado();
											ltd_turnoDerivado.setIdTurnoPadre(ath_turnoHistoria.getIdTurno());

											lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
													                             .findByIdTurnoPadre(ltd_turnoDerivado);

											if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
											{
												for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
												{
													if(ltd_iterador != null)
													{
														SolicitudMatricula lsm_solicitudMatricula;
														String             ls_turnoHijo;

														lsm_solicitudMatricula     = new SolicitudMatricula();
														ls_turnoHijo               = ltd_iterador.getIdTurnoHijo();

														lsm_solicitudMatricula.setIdTurnoCertificado(ls_turnoHijo);
														lsm_solicitudMatricula.setExpedirCertificado(EstadoCommon.S);

														lsm_solicitudMatricula = DaoCreator.getSolicitudMatriculaDAO(
															    ldm_manager
															).findByTurnoCertificado(lsm_solicitudMatricula);

														if(
														    (lsm_solicitudMatricula != null)
															    && ls_motivoNoTramite.equalsIgnoreCase(
															        MotivonNoTramiteCommon.REALIZAR_REGISTRO_MEDIDAS_CAUTELARES
															    )
														)
														{
															BigDecimal lbd_cantidadCertificados;

															lbd_cantidadCertificados = lsm_solicitudMatricula
																	.getCantidadCertificados();

															if(lbd_cantidadCertificados != null)
															{
																Double ld_cantidad;
																int    li_cantidad;

																ld_cantidad     = NumericUtils.getDoubleWrapper(
																	    lbd_cantidadCertificados
																	);
																li_cantidad     = (ld_cantidad != null)
																	? NumericUtils.getInt(ld_cantidad) : 0;

																lsm_solicitudMatricula.setTurnoHistoria(
																    ath_turnoHistoria
																);

																for(int li_i = 1; li_i <= li_cantidad; li_i++)
																	generarCertificadoTradicionLibertad(
																	    lsm_solicitudMatricula,
																	    IdentificadoresCommon.FIRMA, true, ldm_manager,
																	    as_usuarioProceso, as_remoteIp
																	);
															}
														}

														{
															TurnoHistoria lth_turnoDerivado;
															lth_turnoDerivado = new TurnoHistoria();

															lth_turnoDerivado.setIdTurno(ls_turnoHijo);
															lth_turnoDerivado.setIdEtapa(
															    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_30)
															);
															lth_turnoDerivado.setEstadoActividad(
															    EstadoCommon.AUTOMATICA
															);

															lth_turnoDerivado = lthd_DAO.findByIdTurnoEtapa(
																    lth_turnoDerivado
																);

															if(lth_turnoDerivado != null)
															{
																lth_turnoDerivado.setEstadoActividad(
																    EstadoCommon.TERMINADA
																);
																lth_turnoDerivado.setMotivo(
																    MotivonNoTramiteCommon.CERTIFICADO_PROCESADO_POR_MEDIDA_CAUTELAR
																);
																lth_turnoDerivado.setIdUsuarioModificacion(ls_usuario);
																lth_turnoDerivado.setIpModificacion(as_remoteIp);

																lthd_DAO.insertOrUpdate(lth_turnoDerivado, false);
															}
														}
													}
												}
											}
										}

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ((ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS))
								    && ((ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA))
							)
							{
								{
									Suspension ls_param;

									ls_param = new Suspension();
									ls_param.setTurnoHistoria(ath_turnoHistoria);

									if(ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
										getGrabacionBusiness()
											    .generarDocumentoNegacion(
											    ls_param, ls_usuario, as_remoteIp, true, true, ldm_manager
											);
									else if(
									    (ll_idMotivoAnt == MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
										    || (ll_idMotivoAnt == MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA)
									)
									{
										ProcedimientosDAO lpd_DAO;

										lpd_DAO = DaoCreator.getProcedimientosDAO(ldm_manager);

										if(ll_idMotivoAnt == MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
											getGrabacionBusiness()
												    .generarDocumentoResolucion(
												    ls_param, ls_usuario, as_remoteIp, true, true, ldm_manager
												);

										if(ll_idMotivoAnt == MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA)
										{
											getGrabacionBusiness()
												    .generarDocumentoMatriculaCreada(
												    ls_param, ls_usuario, as_remoteIp, true, true, ldm_manager
												);

											{
												DocumentosSalida             lds_docSalida;
												Collection<DocumentosSalida> lcds_documentos;

												lds_docSalida = new DocumentosSalida();

												lds_docSalida.setIdTurno(ath_turnoHistoria.getIdTurno());
												lds_docSalida.setTipo(TipoArchivoCommon.CREACION_MATRICULA);

												lcds_documentos = ldsd_DAO.findByIdTurnoTipo(lds_docSalida);

												if(CollectionUtils.isValidCollection(lcds_documentos))
												{
													for(DocumentosSalida lds_doc : lcds_documentos)
													{
														if(lds_doc != null)
														{
															lds_doc.setEstado(EstadoCommon.ENTREGA);
															lds_doc.setIdUsuarioModificacion(ls_usuario);
															lds_doc.setIpModificacion(as_remoteIp);

															ldsd_DAO.insertOrUpdate(lds_doc, false);
														}
													}
												}
											}

											lpd_DAO.procDesbloqueoMatriculas(
											    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
											);

											lpd_DAO.procCrearMatriculaAntSistema(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

											lpd_DAO.procDesbloqueoMatriculas(
											    ath_turnoHistoria.getIdTurno(), ls_usuario, as_remoteIp
											);
										}
									}
								}

								{
									MotivoTramite lmt_motivo;
									long          ll_idMotivo;

									lmt_motivo      = new MotivoTramite();
									ll_idMotivo     = 0;

									{
										ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

										if(ath_turnoHistoria == null)
											throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

										Solicitud ls_solicitud;

										ls_solicitud = new Solicitud();

										ls_solicitud.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

										ls_solicitud = lsd_DAO.findById(ls_solicitud);

										if(
										    (ls_solicitud != null)
											    && (ll_idMotivoAnt != MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
										)
										{
											boolean lb_ejecucion;
											String  ls_medioNot;

											lb_ejecucion     = (ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
													&& (ll_idMotivoAnt == MotivoTramiteCommon.EJECUCION_GRABACION_MATRICULA);
											ls_medioNot      = StringUtils.getStringNotNull(
												    ls_solicitud.getIdAutorizacionNotificacion()
												);

											if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
											{
												if(lb_ejecucion)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORREO_ELECTRONICO;
												else
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORREO_ELECTRONICO;
											}
											else if(
											    ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
												    || ls_medioNot.equalsIgnoreCase(
												        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
												    )
											)
											{
												if(lb_ejecucion)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP_DE_CORRESPONDENCIA;
												else
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_CORRESPONDENCIA;
											}
											else if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.ORIP))
											{
												if(lb_ejecucion)
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_MATRICULA_GRABADA_ORIP;
												else
													ll_idMotivo = MotivoTramiteCommon.ENTREGA_NEGACION_DE_LA_SOLICITUD_DE_GRABACION_ORIP;
											}
										}
										else
											ll_idMotivo = MotivoTramiteCommon.APROBACION_RESOLUCION_PARA_GRABACION_DE_MATRICULA;
									}

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
									lmt_motivo.setIdMotivo(ll_idMotivo);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if((lmt_motivo != null) && (ath_turnoHistoria != null))
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);
										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    ((ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION_ANTIGUO_SISTEMA)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ANTIGUO_SISTEMA))
								    && ((ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ASOCIAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_FIRMA)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_DESDE_ANTIGUO_SISTEMA_FIRMA)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.ASOCIAR_MATRICULA_PARA_CALIFICACION_FIRMA)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.INFORME_DE_BUSQUEDA_PARA_CALIFICACION_FIRMA)
								    || (ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA_DESDE_ANTIGUO_SISTEMA_101_FIRMA))
							)
							{
								Collection<DatosAntSistema> lcdas_data;

								lcdas_data = getAntiguoSistemaBusiness()
										             .obtenerDatosAntSistema(
										    ldm_manager, ath_turnoHistoria.getIdTurno(), false
										);

								if(CollectionUtils.isValidCollection(lcdas_data))
								{
									Map<String, Object> lmso_mso;
									MotivoTramite       lmt_motivo;

									lmso_mso       = new HashMap<String, Object>();
									lmt_motivo     = new MotivoTramite();

									lmso_mso.put(IdentificadoresCommon.ID_TURNO, ath_turnoHistoria.getIdTurno());
									lmso_mso.put(IdentificadoresCommon.NIR, lso_solicitud.getNir());
									lmso_mso.put(IdentificadoresCommon.ID_SOLICITUD, lso_solicitud.getIdSolicitud());

									inactivarDocumentosActivosTurnoHistoriaTipo(
									    ath_turnoHistoria.getIdTurnoHistoria(), TipoArchivoCommon.COMUNICADO_FIRMA_AS,
									    ldm_manager, as_remoteIp, as_usuarioProceso
									);

									for(DatosAntSistema ldas_das : lcdas_data)
										getAntiguoSistemaBusiness()
											    .generarPdfSolicitudFirma(
											    ldas_das, lmso_mso, false, ls_usuario, "", as_remoteIp, ldm_manager
											);

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA);
									lmt_motivo.setIdMotivo(MotivoTramiteCommon.AUTORIZA_FIRMA_LIBRO_AS);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if((lmt_motivo != null) && (ath_turnoHistoria != null))
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);
										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(ll_idEtapa == EtapaCommon.ANALISIS_CREACION_MATRICULA_OFICIO)
							{
								long          ll_motivoTramite;
								MotivoTramite lmt_motivo;

								ll_motivoTramite     = NumericUtils.DEFAULT_LONG_VALUE;
								lmt_motivo           = new MotivoTramite();

								ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

								if(ath_turnoHistoria == null)
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

								Solicitud ls_solicitud;

								ls_solicitud = new Solicitud();

								ls_solicitud.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

								ls_solicitud = lsd_DAO.findById(ls_solicitud);

								if(ls_solicitud != null)
								{
									String ls_medioNot;
									String ls_usuarioOrip;

									ls_medioNot        = StringUtils.getStringNotNull(
										    ls_solicitud.getIdAutorizacionNotificacion()
										);
									ls_usuarioOrip     = StringUtils.getStringNotNull(ls_solicitud.getUsuarioOrip());

									if(ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA)
										DaoCreator.getProcedimientosDAO(ldm_manager)
											          .procCrearMatriculaAntSistema(
											    ath_turnoHistoria.getIdTurnoHistoria(), ls_usuario, as_remoteIp
											);

									if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
									{
										if(
										    ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO
										)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO_CORREO_ELECTRONICO;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_CORREO_ELECTRONICO;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.MATRICULA_YA_EXISTENTE)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_CORREO;
										}
									}
									else if(
									    ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
										    || ls_medioNot.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
									{
										if(
										    ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO
										)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.MATRICULA_YA_EXISTENTE)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE;
										}
									}
									else if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.ORIP))
									{
										if(
										    ll_idMotivoAnt == MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO
										)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_NEGACION_SOLICITUD_DE_CREACION_DE_MATRICULA_POR_OFICIO_ORIP;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.CREAR_MATRICULA)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_APROBADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.APROBADA_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_ORIP;
										}
										else if(ll_idMotivoAnt == MotivoTramiteCommon.MATRICULA_YA_EXISTENTE)
										{
											if(ls_usuarioOrip.equalsIgnoreCase(IdentificadoresCommon.S))
												ll_motivoTramite = MotivoTramiteCommon.PROCESO_CREACION_DE_MATRICULAS_FINALIZADO_NEGADO;
											else
												ll_motivoTramite = MotivoTramiteCommon.NEGAR_SOLICITUD_DE_CREACION_DE_MATRICULA_DE_OFICIO_YA_EXISTENTE_ORIP;
										}
									}

									OficiosTexto aot_oficioTextoData;
									aot_oficioTextoData = new OficiosTexto();
									aot_oficioTextoData.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									if(ll_idMotivoAnt != MotivoTramiteCommon.CREAR_MATRICULA)
										getAntiguoSistemaBusiness()
											    .generarDocumentoMatriculaOficioAS(
											    aot_oficioTextoData, ls_usuario, as_remoteIp, ll_idMotivoAnt, true, true,
											    ldm_manager
											);
								}

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_ANTIGUO_SISTEMA);
								lmt_motivo.setIdMotivo(ll_motivoTramite);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if((lmt_motivo != null) && (ath_turnoHistoria != null))
								{
									ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
									ath_turnoHistoria.setIdMotivo(
									    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
									);
									ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
									ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
									ath_turnoHistoria.setIpModificacion(as_remoteIp);

									lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

									DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);
									DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_835)
							{
								Map<String, Boolean> lmsb_permisos;
								boolean              lb_visitador;

								lmsb_permisos     = null;

								lb_visitador = false;

								long ll_etapaActual;

								ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

								if(ath_turnoHistoria == null)
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
								else
									ll_etapaActual = NumericUtils.getLong(ath_turnoHistoria.getIdEtapa());

								TurnoHistoria lth_turnoHistoriaAnterior;

								lth_turnoHistoriaAnterior = new TurnoHistoria();

								lth_turnoHistoriaAnterior.setIdTurnoHistoria(
								    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
								);

								lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

								if(lth_turnoHistoriaAnterior != null)
									lmsb_permisos = cargarDatosRolesUsuarios(
										    ldm_manager, lth_turnoHistoriaAnterior.getIdUsuarioCreacion()
										);

								if(CollectionUtils.isValidCollection(lmsb_permisos))
									lb_visitador = BooleanUtils.getBooleanValue(
										    lmsb_permisos.get(RolCommon.CB_ROL_VISITADOR_TXT)
										);

								MotivoTramite lmt_motivo;
								long          ll_motivoTramite;

								lmt_motivo     = new MotivoTramite();

								ll_motivoTramite = NumericUtils.DEFAULT_LONG_VALUE;

								String ls_usuario840;

								ls_usuario840 = null;

								if(ll_etapaActual == EtapaCommon.ID_ETAPA_CREACION_TURNO_ETAPA_MEJORAMIENTO)
								{
									String ls_idSolicitudAsociada;
									ls_idSolicitudAsociada     = null;

									ls_idSolicitudAsociada = DaoCreator.getProcedimientosDAO(ldm_manager)
											                               .crearSolicitudDesdeOtra(lso_solicitud);

									if(StringUtils.isValidString(ls_idSolicitudAsociada))
									{
										String ls_nir;
										ls_nir = crearNir(ls_usuario, as_remoteIp, ldm_manager);

										if(StringUtils.isValidString(ls_nir))
										{
											Solicitud ls_solicitudAsociada;
											ls_solicitudAsociada = lsd_DAO.findById(ls_idSolicitudAsociada);

											if(ls_solicitudAsociada != null)
											{
												ls_solicitudAsociada.setNir(ls_nir);
												ls_solicitudAsociada.setIdUsuarioModificacion(ls_usuario);
												ls_solicitudAsociada.setIpModificacion(as_remoteIp);

												lsd_DAO.insertOrUpdate(ls_solicitudAsociada, false);

												TurnoHistoria lth_asociado;

												lth_asociado = new TurnoHistoria();
												lth_asociado.setIdSolicitud(ls_idSolicitudAsociada);
												lth_asociado = DaoCreator.getTurnoHistoriaDAO(ldm_manager)
														                     .findBySolicitud(lth_asociado);
												DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(
												    lth_asociado
												);
												DaoCreator.getFirmaMasivaDAO(ldm_manager)
													          .deleteFirmaMasiva(afm_parametros);
											}
										}
									}
								}
								else
								{
									if(ll_etapaActual == EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845)
									{
										if(
										    ll_idMotivoAnt == MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_DELEGADO
										)
											ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION;
										else
											ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION;

										lmt_motivo.setIdEtapaOrigen(
										    EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845
										);

										ls_usuario840 = ls_usuario;

										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ath_turnoHistoria.getIdUsuarioCreacion(),
										    as_remoteIp, ldm_manager
										);
									}
									else if(ll_etapaActual == EtapaCommon.ID_ETAPA_APROBACION_EJECUCION_VISITAS)
									{
										if(ll_idMotivoAnt == MotivoTramiteCommon.AUTO_REASIGNACION_TURNO)
											ll_motivoTramite = MotivoTramiteCommon.AUTO_REASIGNACION_TURNO;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_PRORROGA_DE_VISITA)
											if(!lb_visitador)
												ll_motivoTramite = MotivoTramiteCommon.AUTO_DE_PRORROGA_DE_VISITA;
											else
												ll_motivoTramite = MotivoTramiteCommon.AUTO_DE_PRORROGA_DE_VISITA_VISITADOR;
										else if(
										    ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_SUSPENSION_DEL_TURNO_DE_VISITA
										)
											ll_motivoTramite = MotivoTramiteCommon.AUTO_DE_SUSPENSION_DEL_TURNO_DE_VISITA;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.REANUDACION_DEL_TURNO_DE_VISITA)
											ll_motivoTramite = MotivoTramiteCommon.REANUDACION_DEL_TURNO_DE_VISITA;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.REVOCATORIA_DEL_TURNO_DE_VISITA)
											ll_motivoTramite = MotivoTramiteCommon.REVOCATORIA_DEL_TURNO_DE_VISITA;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.ANULACION_DEL_TURNO_DE_VISITA)
											ll_motivoTramite = MotivoTramiteCommon.ANULACION_DEL_TURNO_DE_VISITA;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.CIERRE_DEL_TURNO_DE_VISITA)
											if(!lb_visitador)
												ll_motivoTramite = MotivoTramiteCommon.CIERRE_DEL_TURNO_DE_VISITA;
											else
												ll_motivoTramite = MotivoTramiteCommon.CIERRE_DEL_TURNO_DE_VISITA_VISITADOR;
										else if(
										    ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_840
										)
											ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION;
										else if(
										    ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION_840
										)
											ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.INFORME_FINAL)
											ll_motivoTramite = MotivoTramiteCommon.INFORME_FINAL;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.INFORME_INTERVENCION)
											ll_motivoTramite = MotivoTramiteCommon.INFORME_INTERVENCION;
										else if(ll_idMotivoAnt == MotivoTramiteCommon.OFICIO_REQUERIMIENTOS)
											ll_motivoTramite = MotivoTramiteCommon.OFICIO_REQUERIMIENTOS_840;

										if(
										    lb_visitador
											    && (ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR)
										)
											ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR_840;

										if(
										    lb_visitador
											    && (ll_idMotivoAnt == MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR)
										)
											ll_motivoTramite = MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR_840;

										lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_EJECUCION_VISITAS);

										ls_usuario840 = lth_turnoHistoriaAnterior.getIdUsuarioCreacion();

										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ath_turnoHistoria.getIdUsuarioCreacion(),
										    as_remoteIp, ldm_manager
										);
									}

									lmt_motivo.setIdMotivo(ll_motivoTramite);

									lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

									if(lmt_motivo != null)
									{
										ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

										if(ath_turnoHistoria != null)
										{
											ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
											ath_turnoHistoria.setIdMotivo(
											    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
											);
											ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
											ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario840);
											ath_turnoHistoria.setIpModificacion(as_remoteIp);

											lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

											DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(
											    ath_turnoHistoria
											);

											DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
										}
									}
								}
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_840)
							{
								MotivoTramite lmt_motivo;
								long          ll_motivoTramite;

								lmt_motivo     = new MotivoTramite();

								ll_motivoTramite = NumericUtils.DEFAULT_LONG_VALUE;

								long ll_etapaActual;

								ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

								if(ath_turnoHistoria == null)
									throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);
								else
									ll_etapaActual = NumericUtils.getLong(ath_turnoHistoria.getIdEtapa());

								String ls_usuario840;
								ls_usuario840 = null;

								if(ll_etapaActual == EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845)
								{
									if(ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_APROBACION;
									else if(ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION_APROBACION;
									else if(
									    ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_260
									)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_APROBACION;
									else if(ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION_270)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION_APROBACION;

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_SUPERINTENDENTE_845);

									ls_usuario840 = ls_usuario;
								}
								else
								{
									if(
									    ll_idMotivoAnt == MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_VISITADOR_840
									)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_LEVANTAMIENTO_INTERVENCION_840_A_840;
									else if(
									    ll_idMotivoAnt == MotivoTramiteCommon.GENERACION_RESOLUCION_PRORROGA_INTERVENCION_VISITADOR_840
									)
										ll_motivoTramite = MotivoTramiteCommon.RESOLUCION_PRORROGA_INTERVENCION_840_A_840;
									else if(ll_idMotivoAnt == MotivoTramiteCommon.AUTO_DE_PRORROGA_DE_VISITA_VISITADOR)
										ll_motivoTramite = MotivoTramiteCommon.AUTO_DE_PRORROGA_DE_VISITA_VISITADOR_840;
									else if(ll_idMotivoAnt == MotivoTramiteCommon.CIERRE_DEL_TURNO_DE_VISITA_VISITADOR)
										ll_motivoTramite = MotivoTramiteCommon.CIERRE_DEL_TURNO_DE_VISITA_VISITADOR_840;

									lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION_EJECUCION_VISITAS);

									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
										ls_usuario840 = ls_usuario;

									firmarDocumentos(
									    lth_turnoHistoriaAnterior, ath_turnoHistoria.getIdUsuarioCreacion(), as_remoteIp,
									    ldm_manager
									);
								}

								lmt_motivo.setIdMotivo(ll_motivoTramite);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario840);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_TESTAMENTOS)
								    && (ll_idMotivoAnt == MotivoTramiteCommon.NOTA_DEVOLUTIVA_ENTREGA_ORIP)
								    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6)
								    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
							)
							{
								long          ll_motivoTramite;
								MotivoTramite lmt_motivo;
								Solicitud     ls_solicitud;

								ls_solicitud         = new Solicitud();
								ll_motivoTramite     = NumericUtils.DEFAULT_LONG_VALUE;
								lmt_motivo           = new MotivoTramite();

								ls_solicitud.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());

								ls_solicitud = lsd_DAO.findById(ls_solicitud);

								if(ls_solicitud != null)
								{
									String ls_medioNot;

									ls_medioNot = StringUtils.getStringNotNull(
										    ls_solicitud.getIdAutorizacionNotificacion()
										);

									if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.ORIP))
										ll_motivoTramite = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_ORIP;
									else if(
									    ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.DIRECCION_RESIDENCIA)
										    || ls_medioNot.equalsIgnoreCase(
										        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
										    )
									)
										ll_motivoTramite = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_CORRESPONDENCIA;
									else if(ls_medioNot.equalsIgnoreCase(MedioNotificarCommon.CORREO_ELECTRONICO))
										ll_motivoTramite = MotivoTramiteCommon.ENTREGA_NOTA_DEVOLUTVA_CORREO;
								}

								{
									TurnoHistoria lth_turnoHistoriaAnterior;

									lth_turnoHistoriaAnterior = new TurnoHistoria();

									lth_turnoHistoriaAnterior.setIdTurnoHistoria(
									    NumericUtils.getLongWrapper(ath_turnoHistoria.getIdAnterior())
									);

									lth_turnoHistoriaAnterior = lthd_DAO.findById(lth_turnoHistoriaAnterior);

									if(lth_turnoHistoriaAnterior != null)
										firmarDocumentos(
										    lth_turnoHistoriaAnterior, ConstanteCommon.USUARIO_FIRMA_SUSPENSION,
										    TipoArchivoCommon.NOTA_DEVOLUTIVA, ls_usuario, as_remoteIp, true,
										    ldm_manager
										);
								}

								lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_APROBACION);
								lmt_motivo.setIdMotivo(ll_motivoTramite);

								lmt_motivo = DaoCreator.getMotivoTramiteDAO(ldm_manager).findById(lmt_motivo);

								if(lmt_motivo != null)
								{
									ath_turnoHistoria = lthd_DAO.findById(ath_turnoHistoria);

									if(ath_turnoHistoria != null)
									{
										ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
										ath_turnoHistoria.setIdMotivo(
										    NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo())
										);
										ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
										ath_turnoHistoria.setIdUsuarioModificacion(ls_usuario);
										ath_turnoHistoria.setIpModificacion(as_remoteIp);

										lthd_DAO.insertOrUpdate(ath_turnoHistoria, false);

										DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);

										DaoCreator.getFirmaMasivaDAO(ldm_manager).deleteFirmaMasiva(afm_parametros);
									}
								}
							}
							else
							{
								Object[] loa_arg;

								loa_arg        = new String[2];
								loa_arg[0]     = ls_motivoNoTramite;
								loa_arg[1]     = ls_idSolicitud;

								throw new B2BException(ErrorKeys.ERROR_MOTIVO_SOLICITUD, loa_arg);
							}
						}
						else
						{
							Object[] loa_arg;

							loa_arg        = new String[1];
							loa_arg[0]     = ls_idSolicitud;

							throw new B2BException(ErrorKeys.ERROR_AUTORIZA_NOTIFICACION, loa_arg);
						}
					}
					else
					{
						Object[] loa_arg;

						loa_arg        = new String[1];
						loa_arg[0]     = StringUtils.getString(ll_turnoHistoria);

						throw new B2BException(ErrorKeys.ERROR_SOLICITUD_TURNO_HISTORIA, loa_arg);
					}
				}
				else
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = StringUtils.getString(ll_turnoHistoria);

					throw new B2BException(ErrorKeys.ERROR_MOTIVO_TURNO_HISTORIA, loa_arg);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.APROBACION, lb2be_e.getMessage(), as_usuarioProceso, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		catch(Exception lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.APROBACION, lb2be_e.getMessage(), as_usuarioProceso, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Firmar documentos.
	 *
	 * @param ads_documentosSalida de ads documentos salida
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void firmarDocumentos(
	    Collection<DocumentosSalida> ads_documentosSalida, TurnoHistoria ath_turnoHistoria, String as_usuario,
	    String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(ads_documentosSalida))
			{
				DocumentosSalidaDAO ldsd_DAO;

				ldsd_DAO = DaoCreator.getDocumentosSalidaDAO(adm_manager);

				for(DocumentosSalida lds_documentoSalida : ads_documentosSalida)
				{
					if(lds_documentoSalida != null)
					{
						long ll_documentoSalida;

						ll_documentoSalida = lds_documentoSalida.getIdDocumentoSalida();

						if(ll_documentoSalida > 0)
						{
							Imagenes    li_imagenes;
							ImagenesDAO lid_DAO;

							li_imagenes     = new Imagenes();
							lid_DAO         = DaoCreator.getImagenesDAO(adm_manager);

							li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentoSalida.getIdImagen()));

							li_imagenes = lid_DAO.findById(li_imagenes);

							if(li_imagenes != null)
							{
								byte[] lba_archivo;

								lba_archivo = li_imagenes.getImagenBlob();

								if(lba_archivo != null)
								{
									byte[]     lba_grafo;
									int        li_incrX;
									int        li_incrY;
									Constantes lc_usuarioFirma;

									lba_grafo     = null;
									li_incrX      = 0;
									li_incrY      = 0;

									int li_fontSize;
									int li_temp;

									li_fontSize   = 12;
									li_temp       = 9;
									lc_usuarioFirma = new Constantes();

									lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

									lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager)
											                        .findByIdWithImage(lc_usuarioFirma);

									if(lc_usuarioFirma != null)
									{
										lba_grafo     = lc_usuarioFirma.getImagenes();
										li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
										li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
									}

									lba_archivo = reemplazarBookmarsFirma(
										    lba_archivo, lba_grafo, li_incrX, li_incrY, "FIRMA_USUARIO_VISITADOR", true
										);

									{
										String                      ls_resolucion;
										Long                        ll_NumeroResolucion;
										NumeracionResolucionCirculo lnrc_numeracionCirculo;

										ls_resolucion              = null;
										ll_NumeroResolucion        = null;
										lnrc_numeracionCirculo     = findNumeracionResolucionCirculo(
											    ath_turnoHistoria, adm_manager, as_usuario, as_remoteIp
											);

										if(lnrc_numeracionCirculo != null)
										{
											ls_resolucion           = lnrc_numeracionCirculo.getConsecutivogenerado();
											ll_NumeroResolucion     = lnrc_numeracionCirculo.getConsecutivoResolucion();
										}

										if(StringUtils.isValidString(ls_resolucion))
											lba_archivo = getFirmaMasivaBusiness()
													              .reemplazarBookmarsFirma(
													    lba_archivo,
													    MarcaAgua.crearImagenConTexto(
													        ls_resolucion, ls_resolucion.length() * li_temp, li_fontSize,
													        li_fontSize
													    ), 0, 0, "RESOLUCION", false
													);

										{
											Date             ld_date;
											Date             ld_fechaResol;
											SimpleDateFormat lsdf_formatter;
											String           ls_date;

											ld_date            = new Date();
											ld_fechaResol      = ld_date;
											lsdf_formatter     = new SimpleDateFormat("dd/MM/yyyy");
											ls_date            = StringUtils.getStringNotNull(
												    lsdf_formatter.format(ld_date)
												);

											lba_archivo = getFirmaMasivaBusiness()
													              .reemplazarBookmarsFirma(
													    lba_archivo,
													    MarcaAgua.crearImagenConTexto(
													        ls_date, ls_date.length() * li_temp, li_fontSize,
													        li_fontSize
													    ), 0, 0, "FECHA_RESOL", false
													);

											li_imagenes.setIdImagen(
											    NumericUtils.getInt(lds_documentoSalida.getIdImagen())
											);
											li_imagenes.setImagenBlob(lba_archivo);

											{
												lds_documentoSalida.setConsecutivoResolucion(ll_NumeroResolucion);
												lds_documentoSalida.setFechaResolucion(ld_fechaResol);
												lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
												lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);

												lds_documentoSalida.setIdEcm(null);
												lds_documentoSalida.setFechaEnvio(null);
												lds_documentoSalida.setIdNombreDocumento(null);
												lds_documentoSalida.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
												li_imagenes.setIdUsuarioModificacion(as_usuario);
												li_imagenes.setIpModificacion(as_remoteIp);

												lid_DAO.insertOrUpdate(li_imagenes, false, false);
												ldsd_DAO.insertOrUpdate(lds_documentoSalida, false);
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
			clh_LOGGER.error("firmarDocumentos", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Generar certificado tradicion libertad.
	 *
	 * @param asm_parametros de asm parametros
	 * @param as_pantalla de as pantalla
	 * @param ab_firmaMasiva de ab firma masiva
	 * @param adm_manager de adm manager
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarCertificadoTradicionLibertad(
	    SolicitudMatricula asm_parametros, String as_pantalla, boolean ab_firmaMasiva, DAOManager adm_manager,
	    String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		return generarCertificadoTradicionLibertad(
		    asm_parametros, as_pantalla, ab_firmaMasiva, adm_manager, as_usuario, as_remoteIp, false
		);
	}

	/**
	 * Generar certificado tradicion libertad.
	 *
	 * @param asm_parametros de asm parametros
	 * @param as_pantalla de as pantalla
	 * @param ab_firmaMasiva de ab firma masiva
	 * @param adm_manager de adm manager
	 * @param as_usuario de as usuario
	 * @param as_remoteIp de as remote ip
	 * @param ab_sinQR de ab sin QR
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized byte[] generarCertificadoTradicionLibertad(
	    SolicitudMatricula asm_parametros, String as_pantalla, boolean ab_firmaMasiva, DAOManager adm_manager,
	    String as_usuario, String as_remoteIp, boolean ab_sinQR
	)
	    throws B2BException
	{
		byte[] lba_archivo;
		lba_archivo = null;

		try
		{
			if(asm_parametros != null)
			{
				boolean    lb_qr;
				boolean    lb_certificados;
				byte[]     lba_plantilla;
				Constantes lc_constante;
				String     ls_constante;

				lb_qr               = false;
				lb_certificados     = as_pantalla.equalsIgnoreCase(IdentificadoresCommon.CERTIFICADOS);
				ls_constante        = null;

				if(StringUtils.isValidString(as_pantalla))
				{
					if(as_pantalla.equalsIgnoreCase(IdentificadoresCommon.PREDIO))
						ls_constante = ConstanteCommon.PLANTILLA_CONSULTA_PREDIO;
					else if(lb_certificados)
					{
						lb_qr            = true;
						ls_constante     = ConstanteCommon.PLANTILLA_CERTIFICADO_INMEDIATO;
					}
					else
					{
						if(!ab_sinQR)
							lb_qr = true;

						ls_constante = ConstanteCommon.PLANTILLA_CERTIFICADO_TRADICION_LIBERTAD;
					}
				}

				lc_constante = new Constantes();
				lc_constante.setIdConstante(ls_constante);

				lc_constante = DaoCreator.getConstantesDAO(adm_manager).findImagen(lc_constante);

				if(lc_constante == null)
				{
					Object[] loa_messageArgs = new String[1];
					loa_messageArgs[0] = ls_constante;

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
					Map<String, String> lmss_datos;
					String              ls_plantilla;
					String              ls_idCirculo;
					String              ls_tag;
					long                ll_idMatricula;
					TurnoHistoria       lth_turnoHistoria;

					lmss_datos            = null;
					lth_turnoHistoria     = asm_parametros.getTurnoHistoria();
					ls_plantilla          = new String(lba_plantilla);
					ls_idCirculo          = asm_parametros.getIdCirculo();
					ls_tag                = null;
					ll_idMatricula        = asm_parametros.getIdMatricula();

					if(StringUtils.isValidString(ls_idCirculo) && (ll_idMatricula > 0))
					{
						{
							String ls_tagTurnosBloqueo;

							ls_tagTurnosBloqueo = "<TAG_TURNOS_TRAMITES>";

							if(ls_plantilla.contains(ls_tagTurnosBloqueo))
							{
								Collection<String> lcs_turnosBloqueo;
								Collection<Turno>  lct_turnos;

								lcs_turnosBloqueo     = DaoCreator.getBitacoraBloqueoDAO(adm_manager)
										                              .findTurnoBloqueoRegistroByCirculoMatricula(
										    new PredioRegistro(ls_idCirculo, ll_idMatricula), true
										);
								lct_turnos            = new LinkedList<Turno>();

								if(CollectionUtils.isValidCollection(lcs_turnosBloqueo))
								{
									TurnoDAO ltd_turnoDAO;

									ltd_turnoDAO = DaoCreator.getTurnoDAO(adm_manager);

									for(String ls_idTurno : lcs_turnosBloqueo)
									{
										if(StringUtils.isValidString(ls_idTurno))
										{
											Turno lt_turno;

											lt_turno = ltd_turnoDAO.findNombreEtapaById(ls_idTurno);

											if(lt_turno != null)
												lct_turnos.add(lt_turno);
										}
									}
								}

								if(CollectionUtils.isValidCollection(lct_turnos))
								{
									StringBuilder   lsb_tablaDetalle;
									int             li_numFila;
									Iterator<Turno> lit_iterador;

									lsb_tablaDetalle     = new StringBuilder(
										    "\\trowd \\irow0\\irowband0\\ltrrow\\ts20\\trgaph70\\trleft142\\trbrdrt\\brdrs\\brdrw10 \\trbrdrl\\brdrs\\brdrw10 \\trbrdrb\\brdrs\\brdrw10 \\trbrdrr\\brdrs\\brdrw10 \\trbrdrh\\brdrs\\brdrw10 \\trbrdrv\\brdrs\\brdrw10 \\trftsWidth1\\trftsWidthB3\\trftsWidthA3\\trautofit1\\trpaddl108\\trpaddr108\\trpaddfl3\\trpaddft3\\trpaddfb3\\trpaddfr3\\tblrsid5898610\\tllkhdrrows\\tbllkhdrcols\\tbllknocolband\\tblind250\\tblindtype3\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3387\\clshdrawnil\\cellx3529\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3637\\clshdrawnil\\cellx7166\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3466\\clshdrawnil \\cellx10632\\pard\\plain \\ltrpar\\qc \\li0\\ri0\\widctlpar\\intbl\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\pararsid5898610\\yts20 \\rtlch\\fcs1 \\af0\\afs22\\alang1025 \\ltrch\\fcs0 \\f31506\\fs22\\lang3082\\langfe1033\\cgrid\\langnp3082\\langfenp1033{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\b\\f1\\fs20\\insrsid5898610\\charrsid5898610 Turno\\cell Fecha creación\\cell Estado actual del turno\\cell }\\pard\\plain \\ltrpar\\ql \\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\intbl\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0 \\rtlch\\fcs1 \\af0\\afs22\\alang1025\\ltrch\\fcs0\\f31506\\fs22\\lang3082\\langfe1033\\cgrid\\langnp3082\\langfenp1033"
										);
									li_numFila           = 0;
									lit_iterador         = lct_turnos.iterator();

									while(lit_iterador.hasNext())
									{
										Turno lt_data;

										lt_data = lit_iterador.next();

										if(lt_data != null)
										{
											lsb_tablaDetalle.append(
											    "{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\insrsid5898610\\trowd\\irow"
											);
											lsb_tablaDetalle.append(li_numFila);
											lsb_tablaDetalle.append("\\irowband");
											lsb_tablaDetalle.append(li_numFila);
											lsb_tablaDetalle.append(
											    "\\ltrrow\\ts20\\trgaph70\\trleft142\\trbrdrt\\brdrs\\brdrw10 \\trbrdrl\\brdrs\\brdrw10 \\trbrdrb\\brdrs\\brdrw10 \\trbrdrr\\brdrs\\brdrw10 \\trbrdrh\\brdrs\\brdrw10 \\trbrdrv\\brdrs\\brdrw10 \\trftsWidth1\\trftsWidthB3\\trftsWidthA3\\trautofit1\\trpaddl108\\trpaddr108\\trpaddfl3\\trpaddft3\\trpaddfb3\\trpaddfr3\\tblrsid5898610\\tllkhdrrows\\tbllkhdrcols\\tbllknocolband\\tblind250\\tblindtype3\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3387\\clshdrawnil\\cellx3529\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3637\\clshdrawnil\\cellx7166\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3466\\clshdrawnil\\cellx10632\\row\\ltrrow}\\pard\\plain\\ltrpar\\qj\\li0\\ri0\\widctlpar\\intbl\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\pararsid3408279\\yts20\\rtlch\\fcs1\\af0\\afs22\\alang1025 \\ltrch\\fcs0 \\f31506\\fs22\\lang3082\\langfe1033\\cgrid\\langnp3082\\langfenp1033"
											);

											lsb_tablaDetalle.append(
											    "{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\lang9226\\langfe1033\\langnp9226\\insrsid15231298{"
											);

											{
												String ls_idTurno;

												ls_idTurno = lt_data.getIdTurno();

												lsb_tablaDetalle.append(
												    StringUtils.isValidString(ls_idTurno) ? ls_idTurno
												                                          : IdentificadoresCommon.SIN_INFORMACION
												);
											}

											lsb_tablaDetalle.append(
											    "}}{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\lang9226\\langfe1033\\langnp9226\\insrsid5898610\\charrsid15231298\\cell}{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\insrsid15231298{"
											);

											{
												Date ld_fechaCreacion;

												ld_fechaCreacion = lt_data.getFechaCreacion();

												lsb_tablaDetalle.append(
												    (ld_fechaCreacion != null)
												    ? StringUtils.getString(ld_fechaCreacion, "dd/MM/yyyy HH:mm")
												    : IdentificadoresCommon.SIN_INFORMACION
												);
											}

											lsb_tablaDetalle.append(
											    "}}{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\insrsid5898610\\cell}{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\insrsid15231298{"
											);

											{
												String ls_estado;

												ls_estado = lt_data.getNombreEtapaActual();

												lsb_tablaDetalle.append(
												    StringUtils.isValidString(ls_estado) ? ls_estado
												                                         : IdentificadoresCommon.SIN_INFORMACION
												);
											}

											lsb_tablaDetalle.append(
											    "}}{\\rtlch\\fcs1\\ab\\af1\\afs20\\ltrch\\fcs0\\f1\\fs20\\insrsid5898610\\cell}\\pard\\plain\\ltrpar\\ql\\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\intbl\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\rtlch\\fcs1\\af0\\afs22\\alang1025 \\ltrch\\fcs0 \\f31506\\fs22\\lang3082\\langfe1033\\cgrid\\langnp3082\\langfenp1033"
											);

											li_numFila++;
										}
									}

									lsb_tablaDetalle.append(
									    "{\\rtlch\\fcs1 \\ab\\af1\\afs20 \\ltrch\\fcs0 \\f1\\fs20\\insrsid16736571 \\trowd \\irow"
									);
									lsb_tablaDetalle.append(li_numFila);
									lsb_tablaDetalle.append("\\irowband");
									lsb_tablaDetalle.append(li_numFila);
									lsb_tablaDetalle.append(
									    "\\lastrow \\ltrrow\\ts20\\trgaph70\\trleft142\\trbrdrt\\brdrs\\brdrw10 \\trbrdrl\\brdrs\\brdrw10 \\trbrdrb\\brdrs\\brdrw10 \\trbrdrr\\brdrs\\brdrw10 \\trbrdrh\\brdrs\\brdrw10 \\trbrdrv\\brdrs\\brdrw10 \\trftsWidth1\\trftsWidthB3\\trftsWidthA3\\trautofit1\\trpaddl108\\trpaddr108\\trpaddfl3\\trpaddft3\\trpaddfb3\\trpaddfr3\\tblrsid5898610\\tllkhdrrows\\tbllkhdrcols\\tbllknocolband\\tblind250\\tblindtype3\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3387\\clshdrawnil\\cellx3529\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3637\\clshdrawnil\\cellx7166\\clvertalt\\clbrdrt\\brdrs\\brdrw10\\clbrdrl\\brdrs\\brdrw10\\clbrdrb\\brdrs\\brdrw10\\clbrdrr\\brdrs\\brdrw10\\cltxlrtb\\clftsWidth3\\clwWidth3466\\clshdrawnil\\cellx10632\\row}\\pard\\ltrpar\\qj\\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0\\pararsid3408279 {\\rtlch\\fcs1 \\ab\\af1\\afs20\\ltrch\\fcs0 \\f1\\fs20\\insrsid5898610 \\par }"
									);

									ls_plantilla = ls_plantilla.replace(
										    ls_tagTurnosBloqueo, lsb_tablaDetalle.toString()
										);
								}
								else
									ls_plantilla = ls_plantilla.replace(
										    ls_tagTurnosBloqueo, IdentificadoresCommon.SIN_INFORMACION
										);
							}
						}

						CirculoRegistral lcr_circulo;

						lcr_circulo     = DaoCreator.getCirculoRegistralDAO(adm_manager).findById(ls_idCirculo);

						ls_plantilla = escribirInfoCirculoRegistral(lcr_circulo, ls_plantilla);

						if(lth_turnoHistoria != null)
						{
							Solicitud lso_solicitud;

							lso_solicitud     = new Solicitud();
							lb_qr             = validarQR(lth_turnoHistoria, adm_manager, lb_qr);

							lso_solicitud.setIdSolicitud(lth_turnoHistoria.getIdSolicitud());

							lso_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(lso_solicitud);

							if(lso_solicitud != null)
							{
								String ls_nir;

								ls_nir     = lso_solicitud.getNir();
								ls_tag     = "<ID_NIR>";

								if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_nir))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_nir);

								ls_tag = TagCommon.TAG_EXENTA;

								{
									String ls_exenta;

									ls_exenta = lso_solicitud.getEntidadExenta();

									if(StringUtils.isValidString(ls_exenta) && BooleanUtils.getBooleanValue(ls_exenta))
										reemplazarTagEnPlantilla(
										    ls_plantilla, ConstanteCommon.TAG_EXENTA_CERTIFICADOS,
										    DaoCreator.getConstantesDAO(adm_manager)
											              .findString(ConstanteCommon.TAG_EXENTA_CERTIFICADOS)
										);
								}
							}
						}

						ls_tag = "<ID_CIRCULO_REGISTRAL>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, ls_idCirculo);

						ls_tag = "<ID_MATRICULA>";

						if(ls_plantilla.contains(ls_tag))
							ls_plantilla = ls_plantilla.replace(ls_tag, StringUtils.getString(ll_idMatricula));

						ls_plantilla     = escribirTagFechaHora(ls_plantilla);

						ls_tag = "<TAG_SALVEDADES>";

						if(ls_plantilla.contains(ls_tag))
						{
							SalvedadPredio             lsp_salvedad;
							Collection<SalvedadPredio> lcsp_salvedades;

							lsp_salvedad = new SalvedadPredio();

							lsp_salvedad.setIdCirculo(ls_idCirculo);
							lsp_salvedad.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lcsp_salvedades = DaoCreator.getSalvedadPredioDAO(adm_manager)
									                        .findByCirculoMatricula(lsp_salvedad);

							if(CollectionUtils.isValidCollection(lcsp_salvedades))
							{
								StringBuilder            lsb_salvedades;
								String                   ls_tmp;
								Iterator<SalvedadPredio> lisp_iterador;

								lsb_salvedades     = new StringBuilder();
								ls_tmp             = null;
								lisp_iterador      = lcsp_salvedades.iterator();

								while(lisp_iterador.hasNext())
								{
									SalvedadPredio lsp_data;

									lsp_data = lisp_iterador.next();

									if(lsp_data != null)
									{
										lsb_salvedades.append("{\\pard Salvedad número: ");
										lsb_salvedades.append(lsp_data.getIdSalvedadPredio());
										lsb_salvedades.append(IdentificadoresCommon.SIMBOLO_COMA);
										lsb_salvedades.append("fecha registro: ");
										lsb_salvedades.append(lsp_data.getFechaRegistro());
										lsb_salvedades.append(IdentificadoresCommon.SIMBOLO_COMA);
										lsb_salvedades.append("descripción: ");
										lsb_salvedades.append(lsp_data.getDescripcion());
										lsb_salvedades.append(IdentificadoresCommon.SIMBOLO_COMA);
										lsb_salvedades.append("radicación: ");
										lsb_salvedades.append(lsp_data.getRadicacion());
										lsb_salvedades.append("\\par}");

										if(lisp_iterador.hasNext())
											lsb_salvedades.append("{\\pard \\par}");
									}
								}

								ls_tmp = lsb_salvedades.toString();

								if(StringUtils.isValidString(ls_tmp))
									ls_plantilla = ls_plantilla.replace(ls_tag, ls_tmp);
							}
							else
								ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
						}

						{
							AreaPredio lap_areaPredio;

							lap_areaPredio = new AreaPredio();

							lap_areaPredio.setIdCirculo(ls_idCirculo);
							lap_areaPredio.setIdMatricula(ll_idMatricula);

							lap_areaPredio = DaoCreator.getAreaPredioDAO(adm_manager).findById(lap_areaPredio);

							if(lap_areaPredio != null)
							{
								long ll_idAreaPredio;

								ll_idAreaPredio = lap_areaPredio.getIdArea();

								if(ll_idAreaPredio > 0L)
								{
									DetalleAreaPredioDAO ldap_DAO;
									Long                 ll_idMatriculaLong;
									MedidaAreaDAO        lma_DAO;

									ldap_DAO               = DaoCreator.getDetalleAreaPredioDAO(adm_manager);
									ll_idMatriculaLong     = NumericUtils.getLongWrapper(ll_idMatricula);
									lma_DAO                = DaoCreator.getMedidaAreaDAO(adm_manager);
									ls_tag                 = "<TAG_AREA_PREDIO>";

									if(ls_plantilla.contains(ls_tag))
									{
										Collection<DetalleAreaPredio> lcdap_areasTerreno;
										DetalleAreaPredio             ldap_area;
										StringBuilder                 lsb_sb;

										ldap_area     = new DetalleAreaPredio();
										lsb_sb        = new StringBuilder();

										ldap_area.setIdAreaPredio(StringUtils.getString(ll_idAreaPredio));
										ldap_area.setIdCirculo(ls_idCirculo);
										ldap_area.setIdMatricula(ll_idMatriculaLong);
										ldap_area.setIdTipoArea(TipoAreaCommon.TERRENO);

										lcdap_areasTerreno = ldap_DAO.findAllByIdAreaPredioTipo(ldap_area);

										if(CollectionUtils.isValidCollection(lcdap_areasTerreno))
										{
											Iterator<DetalleAreaPredio> lidap_iterator;

											lidap_iterator = lcdap_areasTerreno.iterator();

											while(lidap_iterator.hasNext())
											{
												DetalleAreaPredio ldap_detalleIterador;
												String            ls_separador;

												ldap_detalleIterador     = lidap_iterator.next();
												ls_separador             = lidap_iterator.hasNext() ? ", " : "";

												if(ldap_detalleIterador != null)
												{
													Double ld_area;

													ld_area = ldap_detalleIterador.getArea();

													if(NumericUtils.isValidDouble(ld_area))
													{
														String ls_unidadMedida;

														ls_unidadMedida = ldap_detalleIterador.getIdUnidadMedida();

														lsb_sb.append(StringUtils.getString(ld_area));

														if(StringUtils.isValidString(ls_unidadMedida))
														{
															MedidaArea lma_medidaArea;

															lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

															if(lma_medidaArea != null)
																lsb_sb.append(" " + lma_medidaArea.getCodigo());
														}

														lsb_sb.append(ls_separador);
													}
												}
											}
										}

										ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
									}

									ls_tag = "<TAG_AREA_PRIVADA>";

									if(ls_plantilla.contains(ls_tag))
									{
										DetalleAreaPredio ldap_areaPrivada;
										DetalleAreaPredio ldap_area;
										StringBuilder     lsb_sb;

										ldap_area     = new DetalleAreaPredio();
										lsb_sb        = new StringBuilder();

										ldap_area.setIdAreaPredio(StringUtils.getString(ll_idAreaPredio));
										ldap_area.setIdCirculo(ls_idCirculo);
										ldap_area.setIdMatricula(ll_idMatriculaLong);
										ldap_area.setIdTipoArea(TipoAreaCommon.PRIVADA);

										ldap_areaPrivada = ldap_DAO.findByIdAreaPredioTipo(ldap_area);

										if(ldap_areaPrivada != null)
										{
											Double ld_area;

											ld_area = ldap_areaPrivada.getArea();

											if(NumericUtils.isValidDouble(ld_area))
											{
												String ls_unidadMedida;

												ls_unidadMedida = ldap_areaPrivada.getIdUnidadMedida();

												lsb_sb.append(StringUtils.getString(ld_area));

												if(StringUtils.isValidString(ls_unidadMedida))
												{
													MedidaArea lma_medidaArea;

													lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

													if(lma_medidaArea != null)
														lsb_sb.append(" " + lma_medidaArea.getCodigo());
												}
											}
										}

										ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
									}

									ls_tag = "<TAG_AREA_CONSTRUIDA>";

									if(ls_plantilla.contains(ls_tag))
									{
										DetalleAreaPredio ldap_areaConstruida;
										DetalleAreaPredio ldap_area;
										StringBuilder     lsb_sb;

										ldap_area     = new DetalleAreaPredio();
										lsb_sb        = new StringBuilder();

										ldap_area.setIdAreaPredio(StringUtils.getString(ll_idAreaPredio));
										ldap_area.setIdCirculo(ls_idCirculo);
										ldap_area.setIdMatricula(ll_idMatriculaLong);
										ldap_area.setIdTipoArea(TipoAreaCommon.CONSTRUIDA);

										ldap_areaConstruida = ldap_DAO.findByIdAreaPredioTipo(ldap_area);

										if(ldap_areaConstruida != null)
										{
											Double ld_area;

											ld_area = ldap_areaConstruida.getArea();

											if(NumericUtils.isValidDouble(ld_area))
											{
												String ls_unidadMedida;

												ls_unidadMedida = ldap_areaConstruida.getIdUnidadMedida();

												lsb_sb.append(StringUtils.getString(ld_area));

												if(StringUtils.isValidString(ls_unidadMedida))
												{
													MedidaArea lma_medidaArea;

													lma_medidaArea = lma_DAO.findById(ls_unidadMedida);

													if(lma_medidaArea != null)
														lsb_sb.append(" " + lma_medidaArea.getCodigo());
												}
											}
										}

										ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
									}

									ls_tag = "<TAG_COEFICIENTE>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    StringUtils.getString(
											        NumericUtils.getBigDecimal(lap_areaPredio.getCoeficiente())
											    )
											);
								}
							}
						}

						{
							PredioRegistro lpr_predioRegistro;
							lpr_predioRegistro = new PredioRegistro();

							lpr_predioRegistro.setIdCirculo(ls_idCirculo);
							lpr_predioRegistro.setIdMatricula(ll_idMatricula);

							lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(adm_manager)
									                           .findByCirculoMatricula(lpr_predioRegistro);

							if(lpr_predioRegistro != null)
							{
								ZonaRegistral lzr_zonaRegistral;
								lzr_zonaRegistral = new ZonaRegistral();

								lzr_zonaRegistral.setIdZonaRegistral(lpr_predioRegistro.getIdZonaRegistral());

								lzr_zonaRegistral = DaoCreator.getZonaRegistralDAO(adm_manager)
										                          .findById(lzr_zonaRegistral);

								{
									String ls_tag_matrTrasladada;

									ls_tag_matrTrasladada = "<TAG_MATRICULA_TRASLADADA>";

									if(ls_plantilla.contains(ls_tag_matrTrasladada))
									{
										StringBuilder lsb_infoTraslado;

										lsb_infoTraslado = new StringBuilder();

										lsb_infoTraslado.append(
										    "{\\pard =======================================================================================================================\\par}"
										);
										lsb_infoTraslado.append(
										    "{\\pard \\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 INFORMACION TRASLADO DE MATRICULA \\par}"
										);

										lsb_infoTraslado.append(
										    "{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 FUNDAMENTOS LEGALES\\tab \\tab \\tab \\tab \\tab \\tab \\tab \\tab "
										    + "\\par Decreto(s):  }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\f31502\\fs20\\insrsid2293951\\charrsid12987649 <DECRETO_NUMERO>}{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 "
										    + "\\par }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 Resoluci\\'f3n(es) de Traslado Circulo Origen: }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\f31502\\fs20\\insrsid2293951\\charrsid12987649 "
										    + "<TAG_NUMERO_RESOL_ORIGEN> }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 "
										    + "\\par }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 Resoluci\\'f3n(es) de Circulo Destino: }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\f31502\\fs20\\insrsid2761070\\charrsid12987649 "
										    + "<TAG_NUMERO_RESOL_DESTINO> }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2761070\\charrsid12987649 "
										    + "\\par }\\pard \\ltrpar\\qj \\li0\\ri0\\sa160\\sl259\\slmult1\\widctlpar\\wrapdefault\\aspalpha\\aspnum\\faauto\\adjustright\\rin0\\lin0\\itap0\\pararsid2761070 {\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 "
										    + "Circulo Registral Origen: }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\f31502\\fs20\\insrsid2761070\\charrsid12987649 <ID_CIRCULO_REGISTRAL_ORIGEN>}{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2761070\\charrsid12987649   }{"
										    + "\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\b\\f31502\\fs20\\insrsid2293951\\charrsid12987649 Matricula Origen: }{\\rtlch\\fcs1 \\af36\\afs20 \\ltrch\\fcs0 \\f31502\\fs20\\insrsid2761070\\charrsid12987649 <ID_MATRICULA_ORIGEN> "
										);

										ls_plantilla = ls_plantilla.replace(
											    ls_tag_matrTrasladada, lsb_infoTraslado.toString()
											);

										SoporteTrasladoMatricula lstm_soporteTrasladoMatricula;
										lstm_soporteTrasladoMatricula = DaoCreator.getSoporteTrasladoMatriculaDAO(
											    adm_manager
											).findByIdCirculoMatricula(ls_idCirculo, ll_idMatricula);

										if((lstm_soporteTrasladoMatricula != null) && (lcr_circulo != null))
										{
											SoporteTraslado lst_soporteTraslado;
											String          ls_textoTag;
											OficinaOrigen   loo_oficinaOrigen;
											String          ls_nombreOficinaOrigen;
											String          ls_idTurno;

											ls_idTurno                 = null;
											ls_textoTag                = null;
											ls_nombreOficinaOrigen     = "";
											lst_soporteTraslado        = DaoCreator.getSoporteTrasladoDAO(adm_manager)
													                                   .findById(
													    lstm_soporteTrasladoMatricula.getIdSoporteTraslado()
													);

											loo_oficinaOrigen = new OficinaOrigen();
											loo_oficinaOrigen.setIdOficinaOrigen(lcr_circulo.getIdOficinaOrigen());
											loo_oficinaOrigen.setVersion(lcr_circulo.getVersion());

											loo_oficinaOrigen = DaoCreator.getOficinaOrigenDAO(adm_manager)
													                          .findById(loo_oficinaOrigen);

											if(loo_oficinaOrigen != null)
												ls_nombreOficinaOrigen = loo_oficinaOrigen.getNombre();

											if(lst_soporteTraslado != null)
											{
												Solicitud ls_solicitud;

												ls_idTurno     = lst_soporteTraslado.getIdTurno();

												ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager)
														                     .findById(
														    lst_soporteTraslado.getIdSolicitud()
														);

												if(ls_solicitud != null)
												{
													Documento ld_documento;
													ld_documento = DaoCreator.getDocumentoDAO(adm_manager)
															                     .findByIdDocumentoVersion(
															    ls_solicitud.getIdDocumento(),
															    ls_solicitud.getVersionDocumento()
															);

													if(ld_documento != null)
													{
														TipoDocumentoPublico ltdp_tipoDocumentoPublico;
														ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
															    adm_manager
															).findById(ld_documento.getIdTipoDocumento());

														if(ltdp_tipoDocumentoPublico != null)
															ls_textoTag = ltdp_tipoDocumentoPublico.getNombre()
																+ IdentificadoresCommon.ESPACIO_VACIO
																+ ld_documento.getNumero()
																+ IdentificadoresCommon.ESPACIO_VACIO + EstadoCommon.DE
																+ IdentificadoresCommon.ESPACIO_VACIO
																+ ld_documento.getFechaDocumento()
																+ ls_nombreOficinaOrigen;
													}
												}
											}

											ls_tag = "<DECRETO_NUMERO>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(StringUtils.isValidString(ls_textoTag))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_textoTag);
											}

											ls_tag = "<TAG_NUMERO_RESOL_ORIGEN>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(StringUtils.isValidString(ls_idTurno))
												{
													long ll_turnoHistoriaMax;
													ll_turnoHistoriaMax = DaoCreator.getTurnoHistoriaDAO(adm_manager)
															                            .findMaxByIdTurnoEtapa(
															    EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS, ls_idTurno
															);

													SoporteTraslado lst_soporteTrasladoTMP;
													lst_soporteTrasladoTMP = DaoCreator.getSoporteTrasladoDAO(
														    adm_manager
														).findByIdTurnoHistoria(ll_turnoHistoriaMax);

													if(lst_soporteTrasladoTMP != null)
													{
														TipoDocumentoPublico ltdp_tipoDocumentoPublico;
														ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
															    adm_manager
															).findById(lst_soporteTrasladoTMP.getIdTipoDocumento());

														if(ltdp_tipoDocumentoPublico != null)
														{
															String        ls_texto;
															OficinaOrigen loo_oficinaOrigen2;
															loo_oficinaOrigen2 = new OficinaOrigen();

															loo_oficinaOrigen2.setIdOficinaOrigen(
															    lst_soporteTrasladoTMP.getIdOficinaOrigen()
															);
															loo_oficinaOrigen2.setVersion(
															    obtenerVersionMaximaOficinaOrigen(
															        lst_soporteTrasladoTMP.getIdOficinaOrigen(),
															        adm_manager
															    )
															);

															loo_oficinaOrigen2 = DaoCreator.getOficinaOrigenDAO(
																    adm_manager
																).findById(loo_oficinaOrigen2);

															if(loo_oficinaOrigen2 != null)
															{
																Date ld_fechaDocumento;
																ld_fechaDocumento = lst_soporteTrasladoTMP
																		.getFechaDocumento();

																DateFormat lsf_dateFormat = new SimpleDateFormat(
																	    FormatoFechaCommon.DIA_MES_ANIO
																	);

																String     ls_fecha = lsf_dateFormat.format(
																	    ld_fechaDocumento
																	);

																ls_texto = ltdp_tipoDocumentoPublico.getNombre()
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ lst_soporteTrasladoTMP.getNumero()
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ EstadoCommon.DE
																	+ IdentificadoresCommon.ESPACIO_VACIO + ls_fecha
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ loo_oficinaOrigen2.getNombre();

																if(StringUtils.isValidString(ls_texto))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_texto
																		);
															}
														}
													}
												}
											}

											ls_tag = "<TAG_NUMERO_RESOL_DESTINO>";

											if(ls_plantilla.contains(ls_tag))
											{
												if(StringUtils.isValidString(ls_idTurno))
												{
													long ll_turnoHistoriaMax;
													ll_turnoHistoriaMax = DaoCreator.getTurnoHistoriaDAO(adm_manager)
															                            .findMaxByIdTurnoEtapa(
															    EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO,
															    ls_idTurno
															);

													SoporteTraslado lst_soporteTrasladoTMP;
													lst_soporteTrasladoTMP = DaoCreator.getSoporteTrasladoDAO(
														    adm_manager
														).findByIdTurnoHistoria(ll_turnoHistoriaMax);

													if(lst_soporteTrasladoTMP != null)
													{
														TipoDocumentoPublico ltdp_tipoDocumentoPublico;
														ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
															    adm_manager
															).findById(lst_soporteTrasladoTMP.getIdTipoDocumento());

														if(ltdp_tipoDocumentoPublico != null)
														{
															String        ls_texto;
															OficinaOrigen loo_oficinaOrigen2;
															loo_oficinaOrigen2 = new OficinaOrigen();

															loo_oficinaOrigen2.setIdOficinaOrigen(
															    lst_soporteTrasladoTMP.getIdOficinaOrigen()
															);
															loo_oficinaOrigen2.setVersion(
															    obtenerVersionMaximaOficinaOrigen(
															        lst_soporteTrasladoTMP.getIdOficinaOrigen(),
															        adm_manager
															    )
															);

															loo_oficinaOrigen2 = DaoCreator.getOficinaOrigenDAO(
																    adm_manager
																).findById(loo_oficinaOrigen2);

															if(loo_oficinaOrigen2 != null)
															{
																Date ld_fechaDocumento;
																ld_fechaDocumento = lst_soporteTrasladoTMP
																		.getFechaDocumento();

																DateFormat lsf_dateFormat = new SimpleDateFormat(
																	    FormatoFechaCommon.DIA_MES_ANIO
																	);

																String     ls_fecha = lsf_dateFormat.format(
																	    ld_fechaDocumento
																	);

																ls_texto = ltdp_tipoDocumentoPublico.getNombre()
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ lst_soporteTrasladoTMP.getNumero()
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ EstadoCommon.DE
																	+ IdentificadoresCommon.ESPACIO_VACIO + ls_fecha
																	+ IdentificadoresCommon.ESPACIO_VACIO
																	+ loo_oficinaOrigen2.getNombre();

																if(StringUtils.isValidString(ls_texto))
																	ls_plantilla = ls_plantilla.replace(
																		    ls_tag, ls_texto
																		);
															}
														}
													}
												}
											}

											ls_tag = "<ID_CIRCULO_REGISTRAL_ORIGEN>";

											if(ls_plantilla.contains(ls_tag))
											{
												CirculoRegistral lcr_circuloRegistralTMP;
												lcr_circuloRegistralTMP = DaoCreator.getCirculoRegistralDAO(
													    adm_manager
													).findById(lstm_soporteTrasladoMatricula.getIdCirculoOrigen());

												if(lcr_circuloRegistralTMP != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    lcr_circuloRegistralTMP.getIdCirculo()
														    + IdentificadoresCommon.ESPACIO_VACIO
														    + lcr_circuloRegistralTMP.getNombre()
														);
											}

											ls_tag = "<ID_MATRICULA_ORIGEN>";

											if(ls_plantilla.contains(ls_tag) && (lst_soporteTraslado != null))
												ls_plantilla = ls_plantilla.replace(
													    ls_tag,
													    lstm_soporteTrasladoMatricula.getIdCirculoOrigen()
													    + IdentificadoresCommon.SIMBOLO_GUION
													    + lstm_soporteTrasladoMatricula.getMatriculaOrigen()
													);
										}
									}
								}

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

										ld_departamento = DaoCreator.getDepartamentoDAO(adm_manager)
												                        .findById(ld_departamento);

										if(ld_departamento != null)
										{
											String ls_nombreDepartamento;
											ls_nombreDepartamento     = ld_departamento.getNombre();
											ls_tag                    = "<TAG_DEPTO>";

											if(StringUtils.isValidString(ls_nombreDepartamento))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreDepartamento);
										}

										lm_municipio.setIdPais(ls_pais);
										lm_municipio.setIdDepartamento(ls_departamento);
										lm_municipio.setIdMunicipio(ls_municipio);

										lm_municipio = DaoCreator.getMunicipioDAO(adm_manager).findById(lm_municipio);

										if(lm_municipio != null)
										{
											String ls_nombreMun;
											ls_nombreMun     = lm_municipio.getNombre();
											ls_tag           = "<TAG_MUNICIPIO>";

											if(StringUtils.isValidString(ls_nombreMun))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreMun);
										}

										lv_vereda.setIdPais(ls_pais);
										lv_vereda.setIdDepartamento(ls_departamento);
										lv_vereda.setIdMunicipio(ls_municipio);
										lv_vereda.setIdVereda(ls_vereda);

										lv_vereda = DaoCreator.getVeredaDAO(adm_manager).findById(lv_vereda);

										if(lv_vereda != null)
										{
											String ls_nombreVereda;
											ls_nombreVereda     = lv_vereda.getNombre();
											ls_tag              = "<TAG_VEREDA>";

											if(StringUtils.isValidString(ls_nombreVereda))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_nombreVereda);
										}
									}
								}

								ls_tag = "<TAG_FECHA_APERTURA_FOLIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									Date ld_fechaApertura;
									ld_fechaApertura = lpr_predioRegistro.getFechaApertura();

									if(ld_fechaApertura != null)
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_fechaApertura, "dd/MM/yyyy HH:mm")
											);
								}

								ls_tag = "<TAG_RADICADO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_radicacion;
									ls_radicacion = lpr_predioRegistro.getRadicacion();

									if(StringUtils.isValidString(ls_radicacion))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_radicacion);
								}

								{
									String ls_idDocumento;
									ls_idDocumento = lpr_predioRegistro.getIdDocumento();

									if(StringUtils.isValidString(ls_idDocumento))
									{
										Documento ld_documento;
										ld_documento = new Documento();

										ld_documento.setIdDocumento(ls_idDocumento);

										ld_documento = DaoCreator.getDocumentoDAO(adm_manager).findById(ld_documento);

										if(ld_documento != null)
										{
											ls_tag = "<TAG_ID_TIPO_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String               ls_idTipoDocumento;
												TipoDocumentoPublico ltdp_tipoDoc;

												ls_idTipoDocumento     = ld_documento.getIdTipoDocumento();
												ltdp_tipoDoc           = new TipoDocumentoPublico();

												ltdp_tipoDoc.setIdTipoDocumento(ls_idTipoDocumento);

												ltdp_tipoDoc = DaoCreator.getTipoDocumentoPublicoDAO(adm_manager)
														                     .findById(ltdp_tipoDoc);

												if(ltdp_tipoDoc != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getStringNotNull(ltdp_tipoDoc.getNombre())
														);
											}

											ls_tag = "<TAG_NUMERO_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												String ls_numero;
												ls_numero = ld_documento.getNumero();

												if(StringUtils.isValidString(ls_numero))
													ls_plantilla = ls_plantilla.replace(ls_tag, ls_numero);
											}

											ls_tag = "<TAG_FECHA_DOCUMENTO>";

											if(ls_plantilla.contains(ls_tag))
											{
												Date ld_fechaDocumento;
												ld_fechaDocumento = ld_documento.getFechaDocumento();

												if(ld_fechaDocumento != null)
													ls_plantilla = ls_plantilla.replace(
														    ls_tag,
														    StringUtils.getString(
														        ld_fechaDocumento, FormatoFechaCommon.DIA_MES_ANIO
														    )
														);
											}
										}
									}
									else
									{
										ls_tag = "<TAG_ID_TIPO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, ConstanteCommon.SIN_INFORMACION
												);

										ls_tag = "<TAG_NUMERO_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, ConstanteCommon.SIN_INFORMACION
												);

										ls_tag = "<TAG_FECHA_DOCUMENTO>";

										if(ls_plantilla.contains(ls_tag))
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, ConstanteCommon.SIN_INFORMACION
												);
									}
								}

								ls_tag = "<TAG_CODIGO_CATASTRAL>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_codigoCatastral;
									ls_codigoCatastral = lpr_predioRegistro.getNumeroPredial();

									if(StringUtils.isValidString(ls_codigoCatastral))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_codigoCatastral);
								}

								ls_tag = "<ID_ESTADO_PREDIO>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_idEstadoPredio;
									ls_idEstadoPredio = lpr_predioRegistro.getIdEstadoPredio();

									if(StringUtils.isValidString(ls_idEstadoPredio))
									{
										EstadoPredio lep_estadoPredio;
										lep_estadoPredio = new EstadoPredio();

										lep_estadoPredio.setIdEstadoPredio(ls_idEstadoPredio);

										lep_estadoPredio = DaoCreator.getEstadoPredioDao(adm_manager)
												                         .findById(lep_estadoPredio);

										if(lep_estadoPredio != null)
											ls_plantilla = ls_plantilla.replace(ls_tag, lep_estadoPredio.getNombre());
									}
								}

								ls_tag = "<TAG_COMPLEMENTACION>";

								if(ls_plantilla.contains(ls_tag))
								{
									BigDecimal lbd_idComplementacion;

									lbd_idComplementacion = lpr_predioRegistro.getIdComplementacion();

									if(NumericUtils.isValidBigDecimal(lbd_idComplementacion))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    escribirComplementacionPredio(
											        adm_manager, lbd_idComplementacion.toString()
											    )
											);
								}

								{
									PredioTipo lpt_predioTipo;
									lpt_predioTipo = new PredioTipo();

									lpt_predioTipo.setIdTipoPredio(lpr_predioRegistro.getIdTipoPredio());

									lpt_predioTipo = DaoCreator.getPredioTipoDao(adm_manager).findById(lpt_predioTipo);

									if(lpt_predioTipo != null)
									{
										ls_tag = "<TAG_ID_TIPO_PREDIO>";

										if(ls_plantilla.contains(ls_tag))
										{
											String ls_tipoPredio;
											ls_tipoPredio = lpt_predioTipo.getDescripcion();

											if(StringUtils.isValidString(ls_tipoPredio))
												ls_plantilla = ls_plantilla.replace(ls_tag, ls_tipoPredio);
										}
									}
								}

								ls_tag = "<TAG_IMAGEN_LINDEROS>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_codigoCatastral;
									ls_codigoCatastral = lpr_predioRegistro.getNumeroPredial();

									if(StringUtils.isValidString(ls_codigoCatastral))
									{
										ConstantesDAO lcd_DAO;
										String        ls_urlEncontrar;
										String        ls_arcgisEndpointLocal;
										String        ls_arcgisEndpointOnline;
										String        ls_urlExportar;
										String        ls_urlImagen;

										lcd_DAO     = DaoCreator.getConstantesDAO(adm_manager);

										ls_urlEncontrar             = lcd_DAO.findString(
											    ConstanteCommon.ARCGIS_ENCONTRAR_URL
											);
										ls_arcgisEndpointLocal      = lcd_DAO.findString(
											    ConstanteCommon.ARCGIS_ENDPOINT_LOCAL
											);
										ls_arcgisEndpointOnline     = lcd_DAO.findString(
											    ConstanteCommon.ARCGIS_ENDPOINT_ARCGISONLINE
											);
										ls_urlExportar              = lcd_DAO.findString(
											    ConstanteCommon.ARCGIS_EXPORTAR_MAPA_URL
											);

										ls_urlImagen = ClienteJSON.getMap(
											    ls_codigoCatastral, ls_urlEncontrar, ls_urlExportar,
											    ls_arcgisEndpointLocal, ls_arcgisEndpointOnline
											);

										if(StringUtils.isValidString(ls_urlImagen))
										{
											InputStream lis_imagen;
											lis_imagen     = new URL(ls_urlImagen).openStream();

											ls_plantilla = ls_plantilla.replace(ls_tag, ImagenPdf.test(lis_imagen));

											lis_imagen.close();
										}
										else
											ls_plantilla = ls_plantilla.replace(
												    ls_tag, addMessage(
												        ErrorKeys.ERROR_INFORMACION_CATASTRAL_MAPA, true
												    )
												);
									}
									else
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, addMessage(ErrorKeys.ERROR_INFORMACION_CATASTRAL_MAPA, true)
											);
								}
							}
						}

						{
							LinderoPredio llp_linderoPredio;
							llp_linderoPredio = new LinderoPredio();

							llp_linderoPredio.setIdCirculo(ls_idCirculo);
							llp_linderoPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							llp_linderoPredio = DaoCreator.getLinderoPredioDAO(adm_manager).findById(llp_linderoPredio);

							if(llp_linderoPredio != null)
							{
								ls_tag = "<TAG_DECRIPCION_LINDEROS>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_lindero;
									ls_lindero = llp_linderoPredio.getLindero();

									if(StringUtils.isValidString(ls_lindero))
									{
										ls_lindero     = ls_lindero.replace("}", "");
										ls_lindero     = ls_lindero.replace("{", "");

										ls_plantilla = ls_plantilla.replace(ls_tag, ls_lindero);
									}
								}
							}
						}

						{
							DireccionPredio             ldp_direccionPredio;
							DireccionPredioDAO          ldpd_direccionPredioDAO;
							String                      ls_maxIdDireccion;
							Collection<DireccionPredio> lcdp_direccionPredio;

							ldp_direccionPredio         = new DireccionPredio();
							ldpd_direccionPredioDAO     = DaoCreator.getDireccionPredioDAO(adm_manager);

							ldp_direccionPredio.setIdCirculo(ls_idCirculo);
							ldp_direccionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							ls_maxIdDireccion     = ldpd_direccionPredioDAO.findMaxIdDireccion(ldp_direccionPredio);

							ls_tag = "<TAG_DIRECCION_PREDIO>";

							if(ls_plantilla.contains(ls_tag))
							{
								lcdp_direccionPredio = ldpd_direccionPredioDAO.findByIdCirculoMatricula(
									    ldp_direccionPredio
									);

								if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
								{
									String ls_direcciones;

									ls_direcciones = escribirDireccionesPredio(adm_manager, lcdp_direccionPredio);

									if(StringUtils.isValidString(ls_direcciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_direcciones);
								}
							}

							ls_tag = "<TAG_DIRECCION_PREDIO_ACTUAL>";

							if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_maxIdDireccion))
							{
								ldp_direccionPredio.setIdDireccion(ls_maxIdDireccion);

								lcdp_direccionPredio = ldpd_direccionPredioDAO.findByCirculoMatriculaIdDireccion(
									    ldp_direccionPredio, true
									);

								if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
								{
									String ls_direcciones;

									ls_direcciones = escribirDireccionesPredio(adm_manager, lcdp_direccionPredio);

									if(StringUtils.isValidString(ls_direcciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_direcciones);
								}
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
							}

							ls_tag = "<TAG_DIRECCION_PREDIO_ANTERIORES>";

							if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(ls_maxIdDireccion))
							{
								ldp_direccionPredio.setIdDireccion(ls_maxIdDireccion);

								lcdp_direccionPredio = ldpd_direccionPredioDAO.findByCirculoMatriculaIdDireccion(
									    ldp_direccionPredio, false
									);

								if(CollectionUtils.isValidCollection(lcdp_direccionPredio))
								{
									String ls_direcciones;

									ls_direcciones = escribirDireccionesPredio(adm_manager, lcdp_direccionPredio);

									if(StringUtils.isValidString(ls_direcciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_direcciones);
								}
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
							}
						}

						{
							AnotacionPredio             lap_anotacionPredio;
							AnotacionPredioDAO          lapd_anotacionPredioDAO;
							Collection<AnotacionPredio> lcap_anotacionPredio;

							lap_anotacionPredio         = new AnotacionPredio();
							lapd_anotacionPredioDAO     = DaoCreator.getAnotacionPredioDAO(adm_manager);

							lap_anotacionPredio.setIdCirculo(ls_idCirculo);
							lap_anotacionPredio.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							ls_tag = "<TAG_ANOTACIONES>";

							if(ls_plantilla.contains(ls_tag))
							{
								lcap_anotacionPredio = lapd_anotacionPredioDAO.findByCirculoMatricula(
									    lap_anotacionPredio, true
									);

								if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
								{
									String ls_anotaciones;

									ls_anotaciones = escribirAnotaciones(
										    adm_manager, lcap_anotacionPredio, ls_idCirculo, ll_idMatricula, false
										);

									if(StringUtils.isValidString(ls_anotaciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_anotaciones);
								}
							}

							ls_tag = "<TAG_USUARIO>";

							if(ls_plantilla.contains(ls_tag) && StringUtils.isValidString(as_usuario))
								ls_plantilla = ls_plantilla.replace(ls_tag, as_usuario);

							ls_tag = "<TAG_ANOTACIONES_VALIDAS>";

							if(ls_plantilla.contains(ls_tag))
							{
								lap_anotacionPredio.setIdEstadoAnotacion(EstadoCommon.V);

								lcap_anotacionPredio = lapd_anotacionPredioDAO.findByCirculoMatriculaEstadoAnotacion(
									    lap_anotacionPredio
									);

								if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
								{
									String ls_anotaciones;

									ls_anotaciones = escribirAnotaciones(
										    adm_manager, lcap_anotacionPredio, ls_idCirculo, ll_idMatricula, true
										);

									if(StringUtils.isValidString(ls_anotaciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_anotaciones);
								}
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
							}

							ls_tag = "<TAG_ANOTACIONES_INVALIDAS>";

							if(ls_plantilla.contains(ls_tag))
							{
								lap_anotacionPredio.setIdEstadoAnotacion(EstadoCommon.A);

								lcap_anotacionPredio = lapd_anotacionPredioDAO.findByCirculoMatriculaEstadoAnotacion(
									    lap_anotacionPredio
									);

								if(CollectionUtils.isValidCollection(lcap_anotacionPredio))
								{
									String ls_anotaciones;

									ls_anotaciones = escribirAnotaciones(
										    adm_manager, lcap_anotacionPredio, ls_idCirculo, ll_idMatricula, true
										);

									if(StringUtils.isValidString(ls_anotaciones))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_anotaciones);
								}
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
							}
						}

						{
							PredioSegregado             lps_predioSegregado;
							Collection<PredioSegregado> lcps_predioSegregado;

							lps_predioSegregado = new PredioSegregado();

							lps_predioSegregado.setIdCirculo(ls_idCirculo);
							lps_predioSegregado.setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));

							lcps_predioSegregado = DaoCreator.getPredioSegregadoDAO(adm_manager)
									                             .findAllByCirculoMatricula(lps_predioSegregado);

							if(CollectionUtils.isValidCollection(lcps_predioSegregado))
							{
								ls_tag = "<TAG_MATRICULAS_SEGREGADAS_CON_DIRECCION>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_matriculasSegregadas;

									ls_matriculasSegregadas = escribirMatriculasSegregadas(
										    adm_manager, lcps_predioSegregado, true
										);

									if(StringUtils.isValidString(ls_matriculasSegregadas))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_matriculasSegregadas);
								}
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);

								ls_tag = "<TAG_MATRICULAS_SEGREGADAS>";

								if(ls_plantilla.contains(ls_tag))
								{
									String ls_matriculasSegregadas;

									ls_matriculasSegregadas = escribirMatriculasSegregadas(
										    adm_manager, lcps_predioSegregado, false
										);

									if(StringUtils.isValidString(ls_matriculasSegregadas))
										ls_plantilla = ls_plantilla.replace(ls_tag, ls_matriculasSegregadas);
								}
							}
						}

						{
							PredioSegregado             lps_predioSegregado;
							Collection<PredioSegregado> lcps_predioSegregado;

							lps_predioSegregado = new PredioSegregado();

							lps_predioSegregado.setIdCirculo1(ls_idCirculo);
							lps_predioSegregado.setIdMatricula1(NumericUtils.getLongWrapper(ll_idMatricula));

							lcps_predioSegregado = DaoCreator.getPredioSegregadoDAO(adm_manager)
									                             .findAllByCirculo1Matricula1(lps_predioSegregado);

							if(CollectionUtils.isValidCollection(lcps_predioSegregado))
							{
								StringBuilder lsb_sb;
								lsb_sb = new StringBuilder();

								for(PredioSegregado lcps_iterador : lcps_predioSegregado)
								{
									if(lcps_iterador != null)
									{
										lsb_sb.append(lcps_iterador.getIdCirculo());
										lsb_sb.append(" - ");
										lsb_sb.append(lcps_iterador.getIdMatricula());
										lsb_sb.append("{\\pard \\par}");
									}
								}

								ls_tag = "<MATRICULAS_ABIERTAS_CON_BASE_EN>";

								if(ls_plantilla.contains(ls_tag))
									ls_plantilla = ls_plantilla.replace(ls_tag, lsb_sb.toString());
								else
									ls_plantilla = ls_plantilla.replace(ls_tag, " ");
							}
						}

						{
							AreaPredio lap_areaPredio;

							lap_areaPredio = new AreaPredio();

							lap_areaPredio.setIdCirculo(ls_idCirculo);
							lap_areaPredio.setIdMatricula(ll_idMatricula);

							lap_areaPredio = DaoCreator.getAreaPredioDAO(adm_manager).findById(lap_areaPredio);

							if(lap_areaPredio != null)
							{
								{
									Double lD_areaPredio;
									double ld_areaPredio;

									lD_areaPredio     = NumericUtils.getDoubleWrapper(lap_areaPredio.getAreaPredio());
									ld_areaPredio     = NumericUtils.getDouble(lD_areaPredio);

									ls_tag = "<TAG_AREA_PREDIO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_areaPredio)
											);
								}

								{
									Double lD_areaPrivada;
									double ld_areaPrivada;

									lD_areaPrivada     = NumericUtils.getDoubleWrapper(lap_areaPredio.getAreaPredio());
									ld_areaPrivada     = NumericUtils.getDouble(lD_areaPrivada);

									ls_tag = "<TAG_AREA_PRIVADA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_areaPrivada)
											);
								}

								{
									Double lD_areaConstruida;
									double ld_areaConstruida;

									lD_areaConstruida     = NumericUtils.getDoubleWrapper(
										    lap_areaPredio.getAreaPredio()
										);
									ld_areaConstruida     = NumericUtils.getDouble(lD_areaConstruida);

									ls_tag = "<TAG_AREA_CONSTRUIDA>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_areaConstruida)
											);
								}

								{
									Double lD_coeficiente;
									double ld_coeficiente;

									lD_coeficiente     = NumericUtils.getDoubleWrapper(lap_areaPredio.getAreaPredio());
									ld_coeficiente     = NumericUtils.getDouble(lD_coeficiente);

									ls_tag = "<TAG_COEFICIENTE>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag, StringUtils.getString(ld_coeficiente)
											);
								}
							}
						}
					}

					{
						String ls_turnoCertificado;
						ls_turnoCertificado = asm_parametros.getIdTurnoCertificado();

						if(StringUtils.isValidString(ls_turnoCertificado))
						{
							ls_tag = "<ID_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ls_turnoCertificado);

							{
								Turno lt_turno;

								lt_turno = new Turno();

								lt_turno.setIdTurno(ls_turnoCertificado);

								lt_turno = DaoCreator.getTurnoDAO(adm_manager).findById(lt_turno);

								if(lt_turno != null)
								{
									ls_tag = "<TAG_FECHA_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(
											    ls_tag,
											    StringUtils.getString(
											        lt_turno.getFechaCreacion(), FormatoFechaCommon.DIA_MES_ANIO
											    )
											);
								}
								else
								{
									ls_tag = "<TAG_FECHA_TURNO>";

									if(ls_plantilla.contains(ls_tag))
										ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
								}
							}
						}
						else
						{
							ls_tag = "<ID_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);

							ls_tag = "<TAG_FECHA_TURNO>";

							if(ls_plantilla.contains(ls_tag))
								ls_plantilla = ls_plantilla.replace(ls_tag, ConstanteCommon.SIN_INFORMACION);
						}

						{
							Constantes lc_usuarioFirma;
							String     ls_tagUsuarioFirma;

							int li_incrX = 0;
							int li_incrY = 0;

							lc_usuarioFirma = new Constantes();

							lc_usuarioFirma.setIdConstante(ConstanteCommon.USUARIO_FIRMA_SUSPENSION);

							ls_tagUsuarioFirma     = "<TAG_USUARIO_FIRMA_SUSPENSION>";
							lc_usuarioFirma        = DaoCreator.getConstantesDAO(adm_manager)
									                               .findByIdWithImage(lc_usuarioFirma);
							ls_plantilla           = reemplazarUsuarioFirmaCargo(
								    ls_plantilla, lc_usuarioFirma, ls_tagUsuarioFirma, "<TAG_CARGO_FIRMA_SUSPENSION>"
								);
							lmss_datos             = finalizarPlantilla(
								    ls_plantilla,
								    (lth_turnoHistoria != null) ? lth_turnoHistoria.getIdTurnoHistoria() : null,
								    adm_manager
								);
							ls_plantilla           = lmss_datos.get(IdentificadoresCommon.PLANTILLA);
							lba_archivo            = new PDFGenerator().convertirRTFToPDF(
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

								lba_archivo = reemplazarBookmarsFirma(lba_archivo, lba_grafo, li_incrX, li_incrY);
							}
						}

						if(StringUtils.isValidString(ls_turnoCertificado))
						{
							ImagenesDAO         lid_DAO;
							DocumentosSalidaDAO ldsd_DAO;
							long                ll_idDocumentoSalida;
							long                ll_secuencia;
							boolean             lb_existeImagen;
							Imagenes            li_imagenes;
							DocumentosSalida    lds_documentosSalida;
							Solicitud           ls_solicitud;

							lid_DAO                  = DaoCreator.getImagenesDAO(adm_manager);
							ldsd_DAO                 = DaoCreator.getDocumentosSalidaDAO(adm_manager);
							li_imagenes              = new Imagenes();
							lds_documentosSalida     = new DocumentosSalida();
							ls_solicitud             = null;
							ll_idDocumentoSalida     = 0;
							lb_existeImagen          = ll_idDocumentoSalida > 0;

							if(lb_existeImagen)
							{
								lds_documentosSalida.setIdDocumentoSalida(ll_idDocumentoSalida);

								lds_documentosSalida = ldsd_DAO.findById(lds_documentosSalida);

								if(lds_documentosSalida != null)
									li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentosSalida.getIdImagen()));
							}

							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioCreacion(as_usuario);
							li_imagenes.setIdUsuarioModificacion(as_usuario);
							li_imagenes.setIpCreacion(as_remoteIp);
							li_imagenes.setIpModificacion(as_remoteIp);
							li_imagenes.setImagenBlob(lba_archivo);
							li_imagenes.setCodigoVerificacion(
							    lmss_datos.get(IdentificadoresCommon.CODIGO_VERIFICACION)
							);

							if(lth_turnoHistoria != null)
							{
								TurnoHistoria lth_temp;

								lth_temp = DaoCreator.getTurnoHistoriaDAO(adm_manager).findById(lth_turnoHistoria);

								if(lth_temp != null)
								{
									String ls_idSolicitud;

									ls_idSolicitud = lth_temp.getIdSolicitud();

									if(StringUtils.isValidString(ls_idSolicitud))
										ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitud);

									li_imagenes.setIdTurno(lth_temp.getIdTurno());
								}
							}

							ll_secuencia = lid_DAO.insertOrUpdate(li_imagenes, !lb_existeImagen, lb_qr);

							if(!lb_existeImagen)
							{
								if(ll_secuencia <= 0)
									throw new B2BException(ErrorKeys.NO_INSERTO_IMAGENES);

								lds_documentosSalida.setIdTurno(ls_turnoCertificado);
								lds_documentosSalida.setIdSolicitud(asm_parametros.getIdSolicitud());

								lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
								lds_documentosSalida.setTipo(TipoArchivoCommon.CERTIFICADO);
								lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);

								if(lth_turnoHistoria != null)
									lds_documentosSalida.setIdTurnoHistoria(
									    NumericUtils.getInteger(lth_turnoHistoria.getIdTurnoHistoria())
									);

								lds_documentosSalida.setIdTipoDocumental(
								    lb_certificados ? getCertificadosBusiness().calcularTipoDocumental(ls_solicitud)
								                    : TipoDocumentalCommon.CERTIFICADO_TRADICION_LIBERTAD
								);

								lds_documentosSalida.setDocumentoFinal(
								    lb_certificados ? EstadoCommon.SI : EstadoCommon.NO
								);
								lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
								lds_documentosSalida.setIdUsuarioCreacion(as_usuario);
								lds_documentosSalida.setIpCreacion(as_remoteIp);

								ldsd_DAO.insertOrUpdate(lds_documentosSalida, true);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarCertificadoTradicionLibertad", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarCertificadoTradicionLibertad", le_e);

			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lba_archivo;
	}

	/**
	 * Método encargado de generar plantilla registro.
	 *
	 * @param ath_turnoHistoria Contiene la información del turno historia
	 * @param adm_dm Controla las transacciones de base de datos.
	 * @param as_usuario Contiene el id del usuario.
	 * @param as_remoteIp Contiene la ip del servidor.
	 * @param as_nir Contiene el nir de la solicitud.
	 * @param as_nir Contiene el turno del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void generarPlantillaRegistro(
	    TurnoHistoria ath_turnoHistoria, DAOManager adm_dm, String as_usuario, String as_remoteIp, String as_nir,
	    String as_idTurno, boolean ab_final
	)
	    throws B2BException
	{
		if(ath_turnoHistoria != null)
		{
			try
			{
				boolean                              lb_reproduccionConstancia;
				RegistroCalificacion                 lrc_tc;
				AnotacionPredioDireccion             lapd_apd;
				Collection<AnotacionPredioDireccion> lcapd_apd;
				Collection<RegistroCalificacion>     lcrc_crc;

				lb_reproduccionConstancia     = StringUtils.isValidString(as_idTurno);
				lapd_apd                      = new AnotacionPredioDireccion();
				lcrc_crc                      = new ArrayList<RegistroCalificacion>();

				lapd_apd.setRadicacion(lb_reproduccionConstancia ? as_idTurno : ath_turnoHistoria.getIdTurno());

				lcapd_apd = DaoCreator.getAnotacionPredioDAO(adm_dm).findByRadicacionJob(lapd_apd);

				if(CollectionUtils.isValidCollection(lcapd_apd))
				{
					RegistroCalificacion lrc_tmp;

					lrc_tmp = new RegistroCalificacion();

					for(AnotacionPredioDireccion lapd_tmp : lcapd_apd)
					{
						if(lapd_tmp != null)
						{
							lrc_tmp.setIdCirculo(lapd_tmp.getIdCirculo() + "-" + lapd_tmp.getIdMatricula());

							lcrc_crc.add(lrc_tmp);

							lrc_tmp = new RegistroCalificacion();
						}
					}

					if(CollectionUtils.isValidCollection(lcrc_crc))
					{
						lrc_tc = new RegistroCalificacion();

						lrc_tc.setJob190(true);
						lrc_tc.setReproduccionConstancia(lb_reproduccionConstancia);
						lrc_tc.setUserId(as_usuario);
						lrc_tc.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());
						lrc_tc.setNir(as_nir);
						lrc_tc.setSalvar(true);
						lrc_tc.setInfoFile(lcrc_crc);

						getRegistroCalificacionBusiness()
							    .genereteFileRegistro(
							    adm_dm, lrc_tc, !lb_reproduccionConstancia || ab_final, as_usuario, as_remoteIp, false
							);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("generarPlantillaRegistro", lb2be_e);
				throw lb2be_e;
			}
		}
	}

	/**
	 * Método encargado de generar plantilla registro.
	 *
	 * @param ath_turnoHistoria Contiene la información del turno historia
	 * @param adm_dm Controla las transacciones de base de datos.
	 * @param as_usuario Contiene el id del usuario.
	 * @param as_remoteIp Contiene la ip del servidor.
	 * @param as_nir Contiene el nir de la solicitud.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void generarPlantillaRegistro(
	    TurnoHistoria ath_turnoHistoria, DAOManager adm_dm, String as_usuario, String as_remoteIp, String as_nir
	)
	    throws B2BException
	{
		generarPlantillaRegistro(ath_turnoHistoria, adm_dm, as_usuario, as_remoteIp, as_nir, null, false);
	}

	/**
	 * Método de transacciones con la base de datos para ubicar la firma en el docuemnto.
	 *
	 * @param aba_pdf docuemnto donde se va a poner la firma
	 * @param aba_grafo imagen de la firma
	 * @param ai_incrX incremento de compensacion en el eje x
	 * @param ai_incrY incremento de compensacion en el eje y
	 * @param as_bookMarkFirma <code>String</code> que indica el bookmark a reemplazar
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] reemplazarBookmarsFirma(
	    byte[] aba_pdf, byte[] aba_grafo, int ai_incrX, int ai_incrY, String as_bookMarkFirma
	)
	    throws B2BException
	{
		return reemplazarBookmarsFirma(aba_pdf, aba_grafo, ai_incrX, ai_incrY, as_bookMarkFirma, true);
	}

	/**
	 * Método de transacciones con la base de datos para ubicar la firma en el docuemnto.
	 *
	 * @param aba_pdf docuemnto donde se va a poner la firma
	 * @param aba_grafo imagen de la firma
	 * @param ai_incrX incremento de compensacion en el eje x
	 * @param ai_incrY incremento de compensacion en el eje y
	 * @param as_bookMarkFirma <code>String</code> que indica el bookmark a reemplazar
	 * @param ab_ponerSobreTag <code>boolean</code> que si la firma va sobre el tag
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] reemplazarBookmarsFirma(
	    byte[] aba_pdf, byte[] aba_grafo, int ai_incrX, int ai_incrY, String as_bookMarkFirma, boolean ab_ponerSobreTag
	)
	    throws B2BException
	{
		byte[] lba_return;

		lba_return = aba_pdf;

		try
		{
			if(aba_grafo != null)
			{
				OutputStream                  los_out;
				PdfReader                     lpr_reader;
				List<HashMap<String, Object>> llhmso_list;
				float                         lf_x;
				float                         lf_y;
				int                           li_pagina;

				lpr_reader      = new PdfReader(aba_pdf);
				llhmso_list     = SimpleBookmark.getBookmark(lpr_reader);
				los_out         = new ByteArrayOutputStream();
				li_pagina       = lpr_reader.getNumberOfPages();
				lf_x            = 0f;
				lf_y            = 0f;

				if(CollectionUtils.isValidCollection(llhmso_list))
				{
					for(HashMap<String, Object> lhmso_actual : llhmso_list)
					{
						if(lhmso_actual != null)
						{
							int[] iPos = PosicionBookmark(lhmso_actual, as_bookMarkFirma, ai_incrX, ai_incrY);

							if(iPos[0] == 2)
							{
								lf_x          = NumericUtils.getFloat(iPos[1]);
								lf_y          = NumericUtils.getFloat(iPos[2]);
								li_pagina     = iPos[4];
							}

							iPos = PosicionBookmark(lhmso_actual, "CODIGO_DE_BARRAS", ai_incrX, ai_incrY);

							if(iPos[0] == 2)
							{
								lf_x          = NumericUtils.getFloat(iPos[1]);
								lf_y          = NumericUtils.getFloat(iPos[2]);
								li_pagina     = iPos[4];
							}
						}
					}

					Image      li_image;
					PdfStamper lps_pdfStamper;

					lps_pdfStamper     = new PdfStamper(lpr_reader, los_out);
					li_image           = Image.getInstance(aba_grafo);

					PdfContentByte lpc_content = lps_pdfStamper.getOverContent(li_pagina);

					if(ab_ponerSobreTag)
						li_image.setAbsolutePosition((lf_x - (li_image.getWidth() / 2)), lf_y);
					else
						li_image.setAbsolutePosition((lf_x), lf_y - li_image.getHeight());

					lpc_content.addImage(li_image);

					lps_pdfStamper.close();
					lpr_reader.close();

					lba_return = ((ByteArrayOutputStream)los_out).toByteArray();
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("reemplazarBookmarsFirma", le_e);

			throw new B2BException(le_e.getMessage(), le_e);
		}

		return lba_return;
	}

	/**
	 * Método de transacciones con la base de datos para ubicar la firma en el docuemnto.
	 *
	 * @param aba_pdf docuemnto donde se va a poner la firma
	 * @param aba_grafo imagen de la firma
	 * @param ai_incrX incremento de compensacion en el eje x
	 * @param ai_incrY incremento de compensacion en el eje y
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] reemplazarBookmarsFirma(byte[] aba_pdf, byte[] aba_grafo, int ai_incrX, int ai_incrY)
	    throws B2BException
	{
		return reemplazarBookmarsFirma(aba_pdf, aba_grafo, ai_incrX, ai_incrY, "ESPACIO_FIRMA_CARGO");
	}

	/**
	 * Método de transacciones con la base de datos para remplazar el usuario que firma.
	 *
	 * @param as_plantilla indica en que plantilla se desea cambiar el usuario que firma
	 * @param ac_usuarioFirma es el usuario que firma
	 * @param as_tagNombreUsuario nombre del usuario a firmar
	 * @param as_tagCargoUsuario carga que tiene el usuario que va a firmar
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String reemplazarUsuarioFirmaCargo(
	    String as_plantilla, Constantes ac_usuarioFirma, String as_tagNombreUsuario, String as_tagCargoUsuario
	)
	    throws B2BException
	{
		try
		{
			if((ac_usuarioFirma != null) && StringUtils.isValidString(as_plantilla))
			{
				String ls_caracter;
				String ls_tag;

				ls_tag          = null;
				ls_caracter     = ac_usuarioFirma.getCaracter();
				ls_tag          = as_tagNombreUsuario;

				if(as_plantilla.contains(ls_tag))
				{
					if(StringUtils.isValidString(ls_caracter))
						as_plantilla = as_plantilla.replace(ls_tag, ls_caracter);
				}

				ls_tag = as_tagCargoUsuario;

				if(as_plantilla.contains(ls_tag))
				{
					String ls_descripcion;

					ls_descripcion = ac_usuarioFirma.getDescripcion();

					if(StringUtils.isValidString(ls_descripcion))
						as_plantilla = as_plantilla.replace(ls_tag, ls_descripcion);
				}

				if(StringUtils.isValidString(ls_caracter))
				{
					int li_finalTag;

					li_finalTag = as_plantilla.lastIndexOf(ls_caracter);

					if(li_finalTag > 0)
					{
						String ls_textoMitadSuperior;
						String ls_textoMitadInferior;
						String ls_tagNew;

						ls_textoMitadSuperior     = as_plantilla.substring(0, li_finalTag);
						ls_textoMitadInferior     = as_plantilla.substring(li_finalTag + ls_caracter.length());
						ls_tagNew                 = "{\\*\\bkmkstart ESPACIO_FIRMA_CARGO}{\\*\\bkmkend ESPACIO_FIRMA_CARGO} \\line "
							+ ls_caracter;
						as_plantilla              = ls_textoMitadSuperior + " " + ls_tagNew + " "
							+ ls_textoMitadInferior;
					}
				}
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("reemplazarUsuarioFirmaCargo", le_e);

			throw le_e;
		}

		return as_plantilla;
	}

	/**
	 * Método que se encarga de consultar los turnos derivados con base a un turno historia.
	 *
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia en proceso.
	 * @param al_idEtapa Variable de tipo long que contiene el id etapa de la etapa a consultar.
	 * @param ldm_manager DaoManager que se usará para realizar las transacciones.
	 * @param lb_turnoPadre Variable booleana que indica si al no encontrar datos se debe agregar el turno historia principal.
	 * @return Mapa que contiene el turno historia principal y sus derivados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Map<TurnoHistoria, Boolean> turnosDerivados(
	    TurnoHistoria ath_turnoHistoria, long al_idEtapa, DAOManager ldm_manager, boolean lb_turnoPadre
	)
	    throws B2BException
	{
		Map<TurnoHistoria, Boolean> lmthb_return;

		lmthb_return = null;

		try
		{
			if(ath_turnoHistoria != null)
			{
				String ls_idTurno;

				ls_idTurno = ath_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					Collection<TurnoDerivado> lctd_turnosDerivados;
					TurnoDerivado             ltd_turnoDerivado;

					ltd_turnoDerivado = new TurnoDerivado();

					ltd_turnoDerivado.setIdTurnoPadre(ls_idTurno);

					lctd_turnosDerivados = DaoCreator.getTurnoDerivadoDAO(ldm_manager)
							                             .findByIdTurnoPadreVinculados(ltd_turnoDerivado);

					if(CollectionUtils.isValidCollection(lctd_turnosDerivados))
					{
						lmthb_return = new HashMap<TurnoHistoria, Boolean>();

						TurnoHistoriaDAO lthd_DAO;

						lmthb_return.put(ath_turnoHistoria, Boolean.TRUE);

						lthd_DAO = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

						for(TurnoDerivado ltd_iterador : lctd_turnosDerivados)
						{
							String ls_idTurnoDerivado;

							ls_idTurnoDerivado = ltd_iterador.getIdTurnoHijo();

							if(StringUtils.isValidString(ls_idTurnoDerivado))
							{
								TurnoHistoria lth_turnoHistoriaDerivado;

								lth_turnoHistoriaDerivado = new TurnoHistoria();

								lth_turnoHistoriaDerivado.setIdTurno(ls_idTurnoDerivado);
								lth_turnoHistoriaDerivado.setIdEtapa(NumericUtils.getBigDecimal(al_idEtapa));
								lth_turnoHistoriaDerivado.setEstadoActividad(EstadoCommon.BLOQUEADO);

								lth_turnoHistoriaDerivado = lthd_DAO.findByIdTurnoEtapa(lth_turnoHistoriaDerivado);

								if(lth_turnoHistoriaDerivado != null)
									lmthb_return.put(lth_turnoHistoriaDerivado, Boolean.FALSE);
							}
						}
					}
					else
					{
						if(lb_turnoPadre)
						{
							lmthb_return = new HashMap<TurnoHistoria, Boolean>();

							lmthb_return.put(ath_turnoHistoria, Boolean.TRUE);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("turnosDerivados", lb2be_e);

			throw lb2be_e;
		}

		return lmthb_return;
	}

	/**
	 * Método temporal para volver a enviar documentos al OWCC
	 *
	 * @param ath_turnoHistoria extraen datos para ingresarlos al documento
	 * @param as_usuario usurio en sesion
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void VolverAEnviarDocumentosOWCC(
	    TurnoHistoria ath_turnoHistoria, String as_usuario, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			TurnoHistoria lth_turnoHistoriaAnt;

			lth_turnoHistoriaAnt = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdAnterior(
				    ath_turnoHistoria, true
				);

			if(lth_turnoHistoriaAnt != null)
			{
				long ll_idEtapaAnt;

				ll_idEtapaAnt = NumericUtils.getLong(lth_turnoHistoriaAnt.getIdEtapa());

				if(ll_idEtapaAnt == EtapaCommon.ID_ETAPA_REVISION_DE_RECURSOS)
				{
					TurnoHistoria lth_turnoHistoriaAnt2;

					lth_turnoHistoriaAnt2 = DaoCreator.getTurnoHistoriaDAO(adm_manager)
							                              .findByIdAnterior(lth_turnoHistoriaAnt, true);

					if(lth_turnoHistoriaAnt2 != null)
						lth_turnoHistoriaAnt = lth_turnoHistoriaAnt2;
				}
				else if(ll_idEtapaAnt == EtapaCommon.APROBACION_RESOLUCION)
				{
					TurnoHistoria lth_turnoHistoriaAnt2;

					lth_turnoHistoriaAnt.setEstadoActividad(EstadoCommon.TERMINADA);
					lth_turnoHistoriaAnt.setIdEtapa(
					    NumericUtils.getBigDecimal(EtapaCommon.PROYECTAR_RESOLUCION_TRASLADOS)
					);

					lth_turnoHistoriaAnt2 = DaoCreator.getTurnoHistoriaDAO(adm_manager)
							                              .findByIdTurnoEtapa(lth_turnoHistoriaAnt);

					if(lth_turnoHistoriaAnt2 != null)
						lth_turnoHistoriaAnt = lth_turnoHistoriaAnt2;
				}

				Collection<DocumentosSalida> lcds_documentosSalida;

				lcds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
						                              .findByIdTurnoHistoria(
						    NumericUtils.getInteger(lth_turnoHistoriaAnt.getIdTurnoHistoria())
						);

				if(CollectionUtils.isValidCollection(lcds_documentosSalida))
				{
					for(DocumentosSalida lds_tmp : lcds_documentosSalida)
					{
						if(lds_tmp != null)
						{
							lds_tmp.setIdEcm(null);
							lds_tmp.setIdNombreDocumento(null);
							lds_tmp.setFechaEnvio(null);

							DaoCreator.getDocumentosSalidaDAO(adm_manager).updateIdEcmAndDocName(lds_tmp);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("VolverAEnviarDocumentosOWCC", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método de transacciones con la base de datos para firmar documentos.
	 *
	 * @param ath_turnoHistoria extraen datos para ingresarlos al documento
	 * @param as_constanteFirma constante donde se obtine la informacion del usuario que firma
	 * @param as_tipoArchivo  tipo de archivo el cual se va a firmar
	 * @param as_usuario usurio en sesion
	 * @param as_remoteIp de as remote ip
	 * @param ab_entregar Indica si los documentos se deben dejar como activo o entregados
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void firmarDocumentos(
	    TurnoHistoria ath_turnoHistoria, String as_constanteFirma, String as_tipoArchivo, String as_usuario,
	    String as_remoteIp, boolean ab_entregar, DAOManager adm_manager
	)
	    throws B2BException
	{
		firmarDocumentos(
		    ath_turnoHistoria, as_constanteFirma, as_tipoArchivo, as_usuario, as_remoteIp, ab_entregar, false,
		    adm_manager
		);
	}

	/**
	 * Método de transacciones con la base de datos para firmar documentos.
	 *
	 * @param ath_turnoHistoria extraen datos para ingresarlos al documento
	 * @param as_constanteFirma constante donde se obtine la informacion del usuario que firma
	 * @param as_tipoArchivo  tipo de archivo el cual se va a firmar
	 * @param as_usuario usurio en sesion
	 * @param as_remoteIp de as remote ip
	 * @param ab_entregar Indica si los documentos se deben dejar como activo o entregados
	 * @param ab_qr Indica si el documento debe generar el QR
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void firmarDocumentos(
	    TurnoHistoria ath_turnoHistoria, String as_constanteFirma, String as_tipoArchivo, String as_usuario,
	    String as_remoteIp, boolean ab_entregar, boolean ab_qr, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			DocumentosSalida    lds_documentoSalida;
			DocumentosSalidaDAO ldsd_DAO;

			lds_documentoSalida     = new DocumentosSalida();
			ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(adm_manager);

			lds_documentoSalida.setIdTurnoHistoria(new Integer(NumericUtils.getInt(ath_turnoHistoria.getIdAnterior())));
			lds_documentoSalida.setIdSolicitud(ath_turnoHistoria.getIdSolicitud());
			lds_documentoSalida.setTipo(as_tipoArchivo);

			lds_documentoSalida.setEstado(
			    as_tipoArchivo.equalsIgnoreCase(ConstanteCommon.CONSTANCIA_INSCRIPCION) ? EstadoCommon.INACTIVO
			                                                                            : EstadoCommon.ACTIVO
			);

			lds_documentoSalida = ldsd_DAO.findDocumentByTurnoHistoriaTipoEstado(lds_documentoSalida);

			if(lds_documentoSalida != null)
			{
				long ll_documentoSalida;

				ll_documentoSalida = lds_documentoSalida.getIdDocumentoSalida();

				if(ll_documentoSalida > 0)
				{
					Imagenes    li_imagenes;
					ImagenesDAO lid_DAO;

					li_imagenes     = new Imagenes();
					lid_DAO         = DaoCreator.getImagenesDAO(adm_manager);

					li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentoSalida.getIdImagen()));

					li_imagenes = lid_DAO.findById(li_imagenes);

					if(li_imagenes != null)
					{
						byte[] lba_archivo;

						lba_archivo = li_imagenes.getImagenBlob();

						if(lba_archivo != null)
						{
							byte[]     lba_grafo;
							int        li_incrX;
							int        li_incrY;
							Constantes lc_usuarioFirma;

							lba_grafo     = null;
							li_incrX      = 0;
							li_incrY      = 0;

							lc_usuarioFirma = new Constantes();

							lc_usuarioFirma.setIdConstante(as_constanteFirma);

							lc_usuarioFirma = DaoCreator.getConstantesDAO(adm_manager).findByIdWithImage(
								    lc_usuarioFirma
								);

							if(lc_usuarioFirma != null)
							{
								lba_grafo     = lc_usuarioFirma.getImagenes();
								li_incrX      = NumericUtils.getInt(lc_usuarioFirma.getEntero());
								li_incrY      = NumericUtils.getInt(lc_usuarioFirma.getReal());
							}

							lba_archivo = reemplazarBookmarsFirma(lba_archivo, lba_grafo, li_incrX, li_incrY);

							li_imagenes.setIdImagen(NumericUtils.getInt(lds_documentoSalida.getIdImagen()));
							li_imagenes.setImagenBlob(lba_archivo);

							if(ab_entregar)
							{
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setRepositorio(RepositorioCommon.FINAL);
							}
							else
							{
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);
								lds_documentoSalida.setRepositorio(RepositorioCommon.TEMPORAL);
							}

							if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.COMUNICADO_DEMANDA))
								lds_documentoSalida.setRepositorio(RepositorioCommon.TEMPORAL);

							if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.REGISTRO))
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_DE_INSCRIPCION);
							else if(
							    as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.COMUNICADO_CANCELACION)
								    || as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.COMUNICADO_DEMANDA)
								    || as_tipoArchivo.equalsIgnoreCase(
								        TipoArchivoCommon.COMUNICADO_NEGACION_CORRECCIONES
								    )
								    || as_tipoArchivo.equalsIgnoreCase(
								        TipoArchivoCommon.OFICIO_REMISORIO_ENTIDAD_CORRECCION_SIPROCEDE
								    )
								    || as_tipoArchivo.equalsIgnoreCase(
								        TipoArchivoCommon.OFICIO_REMISORIO_ENTIDAD_MEDIDA_CAUTELAR
								    )
							)
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
							else if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.NOTA_DEVOLUTIVA))
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.NOTA_DEVOLUTIVA);
							else if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.CONSTANCIA_REPRODUCCION))
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.CONSTANCIA_REPRODUCCION);
							else if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.SOLICITUD_AMPLIACION))
								lds_documentoSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
							else if(as_tipoArchivo.equalsIgnoreCase(TipoArchivoCommon.NOTA_INFORMATIVA_MAYOR_VALOR))
								lds_documentoSalida.setIdTipoDocumental(
								    TipoDocumentalCommon.NOTA_INFORMATIVA_MAYOR_VALOR
								);

							lds_documentoSalida.setIdEcm(null);
							lds_documentoSalida.setFechaEnvio(null);
							lds_documentoSalida.setIdNombreDocumento(null);
							li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
							li_imagenes.setIdUsuarioModificacion(as_usuario);
							li_imagenes.setIpModificacion(as_remoteIp);

							lid_DAO.insertOrUpdate(li_imagenes, false, ab_qr);
							ldsd_DAO.insertOrUpdate(lds_documentoSalida, false);
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("firmarDocumentos", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método de transacciones con la base de datos para firmar documentos.
	 *
	 * @param ath_turnoHistoria extraen datos para ingresarlos al documento
	 * @param as_constanteFirma constante donde se obtine la informacion del usuario que firma
	 * @param as_tipoArchivo  tipo de archivo el cual se va a firmar
	 * @param as_usuario usurio en sesion
	 * @param as_remoteIp de as remote ip
	 * @param ab_entregar Indica si los documentos se deben dejar como activo o entregados
	 * @param ab_qr Indica si el documento debe generar el QR
	 * @param adm_manager objeto que contiene los resultados de las consultas en la base de datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void firmarDocumentos(
	    TurnoHistoria ath_turnoHistoria, String as_usuario, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			Collection<DocumentosSalida> lds_documentosSalida;

			lds_documentosSalida     = new LinkedList<DocumentosSalida>();

			lds_documentosSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
					                             .findByIdTurnoHistoria(
					    new Integer(NumericUtils.getInt(ath_turnoHistoria.getIdAnterior()))
					);

			firmarDocumentos(lds_documentosSalida, ath_turnoHistoria, as_usuario, as_remoteIp, adm_manager);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("firmarDocumentos", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de generar los documentos de mayor valor.
	 *
	 * @param adm_manager Argumento de tipo <code>DAOManager</code> que permite la conexión a la base de datos.
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene los criterios necesarios para generar los documentos.
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip del usuario que realiza la operación
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private synchronized void generarDocumentosMayorValor(
	    DAOManager adm_manager, TurnoHistoria ath_turnoHistoria, String as_usuario, String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			if(ath_turnoHistoria != null)
			{
				byte[]                       lba_archivo;
				TurnoHistoria                lth_anterior;
				Collection<DocumentosSalida> lcds_documentoSalida;
				DocumentosSalida             lds_documentoSalida;
				DocumentosSalidaDAO          ldsd_DAO;

				lds_documentoSalida     = null;
				ldsd_DAO                = DaoCreator.getDocumentosSalidaDAO(adm_manager);

				lcds_documentoSalida = ldsd_DAO.findByIdTurnoHistoriaTipo(
					    NumericUtils.getIntegerWrapper(ath_turnoHistoria.getIdAnterior()),
					    IdentificadoresCommon.NOTA_INFORMATIVA_MAYOR_VALOR
					);

				if(!CollectionUtils.isValidCollection(lcds_documentoSalida))
					lcds_documentoSalida = ldsd_DAO.findByIdTurnoHistoriaTipo(
						    NumericUtils.getIntegerWrapper(ath_turnoHistoria.getIdAnterior()),
						    IdentificadoresCommon.NOTA_INFORMATIVA_PAGO_EN_EXCESO
						);

				if(CollectionUtils.isValidCollection(lcds_documentoSalida))
				{
					for(DocumentosSalida lids_ds : lcds_documentoSalida)
						lds_documentoSalida = lids_ds;
				}

				lth_anterior = new TurnoHistoria(ath_turnoHistoria.getIdAnterior());

				if(
				    (lds_documentoSalida != null)
					    && !lds_documentoSalida.getTipo().equals(IdentificadoresCommon.NOTA_INFORMATIVA_MAYOR_VALOR)
				)
				{
					lds_documentoSalida     = new DocumentosSalida();
					lba_archivo             = getLiquidacionBusiness()
							                          .generarPDFNotaInformativaPorPagoEnExceso(
							    lth_anterior, true, adm_manager, lds_documentoSalida, as_usuario, as_remoteIp
							);

					if(lba_archivo != null)
						getLiquidacionBusiness()
							    .insertarImagenDocumentosSalida(
							    adm_manager, ath_turnoHistoria, lba_archivo,
							    ConstanteCommon.TIPO_DOCUMENTAL_NOTA_INFORMATIVA_MV, as_usuario, as_remoteIp,
							    lds_documentoSalida
							);
				}
				else
				{
					lds_documentoSalida     = new DocumentosSalida();
					lba_archivo             = getLiquidacionBusiness()
							                          .generarPDFCobroMayorValor(
							    lth_anterior, adm_manager, as_usuario, as_remoteIp, lds_documentoSalida, true
							);

					if(lba_archivo != null)
						getLiquidacionBusiness()
							    .insertarImagenDocumentosSalida(
							    adm_manager, ath_turnoHistoria, lba_archivo, ConstanteCommon.TIPO_DOCUMENTAL_COBRO_MV,
							    as_usuario, as_remoteIp, lds_documentoSalida
							);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosMayorValor", lb2be_e);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumentosMayorValor", le_e);

			throw new B2BException(le_e.getMessage());
		}
	}

	/**
	 * Método encargado de validar si el proceso puede continuar con base a las validaciones de turnos asociados, es decir, si es un turno asociado debe haber terminado primero el turno principal y si no es un turno derivado lo deja continuar.
	 *
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia en trámite
	 * @param adm_manager DaoManager para realizar las transacciones
	 * @return Variable booleana que valida si se peude continuar con el proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized boolean validarTurnosAsociados(TurnoHistoria ath_turnoHistoria, DAOManager adm_manager)
	    throws B2BException
	{
		boolean lb_return;

		lb_return = false;

		try
		{
			if(ath_turnoHistoria != null)
			{
				String ls_idTurno;

				ls_idTurno = ath_turnoHistoria.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					TurnoDerivado ltd_turnoDerivado;

					ltd_turnoDerivado = new TurnoDerivado();

					ltd_turnoDerivado.setIdTurnoHijo(ls_idTurno);

					ltd_turnoDerivado = DaoCreator.getTurnoDerivadoDAO(adm_manager).findByIdHijo(ltd_turnoDerivado);

					if(ltd_turnoDerivado != null)
					{
						String ls_idTurnoPadre;

						ls_idTurnoPadre = ltd_turnoDerivado.getIdTurnoPadre();

						if(StringUtils.isValidString(ls_idTurnoPadre))
						{
							TurnoHistoria lth_turnoHistoriaPadre;

							lth_turnoHistoriaPadre = new TurnoHistoria();

							lth_turnoHistoriaPadre.setIdTurno(ls_idTurnoPadre);
							lth_turnoHistoriaPadre.setIdEtapa(
							    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_APROBACION)
							);
							lth_turnoHistoriaPadre.setEstadoActividad(EstadoCommon.AUTOMATICA);

							lth_turnoHistoriaPadre = DaoCreator.getTurnoHistoriaDAO(adm_manager)
									                               .findByIdTurnoEtapa(lth_turnoHistoriaPadre);

							if(lth_turnoHistoriaPadre == null)
								lb_return = true;
						}
					}
					else
						lb_return = true;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarTurnosAsociados", lb2be_e);

			throw lb2be_e;
		}

		return lb_return;
	}
}
