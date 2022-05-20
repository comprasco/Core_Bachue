package com.bachue.snr.prosnr04.business.operacionesFinancieras;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoCriterioCTSI;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSI;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSO;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoCriterioGLI;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLI;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLO;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoEntradaObtenerClavePDFLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoSalidaObtenerClavePDFLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoEntradaRegistrarPago;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoSalidaRegistrarPago;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoEntradaValidarActualizacionDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoSalidaValidarActualizacionDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcedenciaCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.RegistroPagoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudIntervinienteDAO;
import com.bachue.snr.prosnr01.dao.bng.PredioRegistroDAO;
import com.bachue.snr.prosnr01.dao.col.PredioTipoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CampoCriterioBusquedaDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.LibroAntiguoSistemaDao;
import com.bachue.snr.prosnr01.dao.proc.ProcedimientosDAO;
import com.bachue.snr.prosnr01.dao.util.ConsultasUtilidades;
import com.bachue.snr.prosnr01.dao.util.UtilDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;
import com.bachue.snr.prosnr01.model.procedimientos.RespuestaConsultaTarifa;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SubprocesoVersion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.ProcesoConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumentoPublico;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;

import com.bachue.snr.prosnr04.common.constants.ErrorKeys;

import com.bachue.snr.prosnr04.dao.acc.NotificacionPagoDAO;
import com.bachue.snr.prosnr04.dao.acc.NotificacionPagoRecibidaDAO;
import com.bachue.snr.prosnr04.dao.npa.DatosLiquidacionDAO;
import com.bachue.snr.prosnr04.dao.npa.RegistrarPagoDAO;
import com.bachue.snr.prosnr04.dao.npa.TipoCriterioDAO;
import com.bachue.snr.prosnr04.dao.npa.TipoServicioDAO;

import com.bachue.snr.prosnr04.model.acc.NotificacionPago;
import com.bachue.snr.prosnr04.model.acc.NotificacionPagoRecibida;
import com.bachue.snr.prosnr04.model.npa.DatosCreacionSolicitud;
import com.bachue.snr.prosnr04.model.npa.DatosLiquidacion;
import com.bachue.snr.prosnr04.model.npa.RegistroPago;
import com.bachue.snr.prosnr04.model.pgn.CanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo;
import com.bachue.snr.prosnr04.model.pgn.SucursalCanalOrigenServicio;
import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Clase que contiene los métodos de negocio relacionados a los servicios de OF de la SNR.
 *
 * @author Julian Vaca
 * Fecha de Creacion: 28/07/2019
 */
public class OperacionesFinancierasBusiness extends BaseBusiness
{
	/**
	 * Constante clh_LOGGER.
	 */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(OperacionesFinancierasBusiness.class, ProyectosCommon.NOTIFICACION_PAGOS_04);

	/** Propiedad is datos ant sistema. */
	private final String is_datosAntSistema = "DATOS_ANT_SISTEMA";

	/** Propiedad is detalle ant sistema. */
	private final String is_detalleAntSistema = "DETALLE_ANT_SISTEMA";

	/** Propiedad is solicitud interviniente. */
	private final String is_solicitudInterviniente = "SDB_ACC_SOLICITUD_INTERVINIENTE";

	/** Propiedad is solicitud matricula. */
	private final String is_solicitudMatricula = "SDB_ACC_SOLICITUD_MATRICULA";

	/**
	 * Método encargado de actualizar los datos del solicitante.
	 *
	 * @param ateads_peticion Objeto de tipo TipoEntradaActualizarDatosSolicitante que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida actualizar datos solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    TipoEntradaActualizarDatosSolicitante ateads_peticion, String as_userIdConstant, String as_localIp,
	    String                                as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(ateads_peticion != null)
			{
				String ls_numeroReferencia;
				String ls_userId;

				ls_userId               = getSystemUser(as_userIdConstant, ldm_manager);
				ls_numeroReferencia     = StringUtils.getStringNotNull(ateads_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					DatosLiquidacion    ldl_dl;
					DatosLiquidacionDAO ldld_DAO;

					ldld_DAO     = DaoCreator.getDatosLiquidacionDAO(ldm_manager);
					ldl_dl       = ldld_DAO.findById(new DatosLiquidacion(ls_numeroReferencia));

					if(ldl_dl != null)
					{
						{
							RegistroPago    lrp_rp;
							RegistroPagoDAO lrpd_DAO;

							lrpd_DAO     = DaoCreator.getRegistroPagoDAO(ldm_manager);
							lrp_rp       = lrpd_DAO.findByIdSolicitud(ldl_dl.getIdSolicitud());

							if(lrp_rp != null)
							{
								String ls_estado;

								ls_estado = StringUtils.getStringNotNull(lrp_rp.getEstadoPago());

								if(!ls_estado.equalsIgnoreCase(EstadoCommon.DISPONIBLE))
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_REFERENCIA_INGRESADA_TIENE_PAGO_REALIZADO)
									);
							}
						}

						Date ld_fechaVencimiento;

						ld_fechaVencimiento = ldl_dl.getFechaVencimiento();

						if((ld_fechaVencimiento != null) && ld_fechaVencimiento.after(new Date()))
						{
							String ls_tipoDocSolicitante;
							String ls_numeroDocSolicitante;
							String ls_primerNombreSolicitante;
							String ls_segundoNombreSolicitante;
							String ls_primerApellidoSolicitante;
							String ls_segundoApellidoSolicitante;
							String ls_razonSocial;

							ls_tipoDocSolicitante             = StringUtils.getStringNotNull(
								    ateads_peticion.getTipoDocSolicitante()
								);
							ls_numeroDocSolicitante           = StringUtils.getStringNotNull(
								    ateads_peticion.getNumeroDocSolicitante()
								);
							ls_primerNombreSolicitante        = StringUtils.getStringNotNull(
								    ateads_peticion.getPrimerNombreSolicitante()
								);
							ls_segundoNombreSolicitante       = StringUtils.getStringNotNull(
								    ateads_peticion.getSegundoNombreSolicitante()
								);
							ls_primerApellidoSolicitante      = StringUtils.getStringNotNull(
								    ateads_peticion.getPrimerApellidoSolicitante()
								);
							ls_segundoApellidoSolicitante     = StringUtils.getStringNotNull(
								    ateads_peticion.getSegundoApellidoSolicitante()
								);
							ls_razonSocial                    = StringUtils.getStringNotNull(
								    ateads_peticion.getRazonSocial()
								);

							{
								Persona lp_insertar;

								lp_insertar = validarPersona(
									    ldl_dl.getIdSolicitud(), null, ls_tipoDocSolicitante, ls_numeroDocSolicitante,
									    ls_primerNombreSolicitante, ls_segundoNombreSolicitante,
									    ls_primerApellidoSolicitante, ls_segundoApellidoSolicitante, ls_razonSocial,
									    ldm_manager, ls_userId, as_localIp, as_remoteIp, new StringBuilder(),
									    new StringBuilder()
									);

								if(lp_insertar != null)
								{
									ldl_dl.setIdTipoPersona(
									    ls_tipoDocSolicitante.equalsIgnoreCase(IdentificadoresCommon.NIT)
									    ? TipoPersonaCommon.JURIDICA : TipoPersonaCommon.NATURAL
									);
									ldl_dl.setIdDocumentoTipo(ls_tipoDocSolicitante);
									ldl_dl.setNumeroDocumento(ls_numeroDocSolicitante);

									if(!lp_insertar.isSeleccionado())
									{
										ldl_dl.setPrimerNombre(ls_primerNombreSolicitante);
										ldl_dl.setSegundoNombre(ls_segundoNombreSolicitante);
										ldl_dl.setPrimerApellido(ls_primerApellidoSolicitante);
										ldl_dl.setSegundoApellido(ls_segundoApellidoSolicitante);
										ldl_dl.setRazonSocial(null);
									}
									else
									{
										ldl_dl.setPrimerNombre(null);
										ldl_dl.setSegundoNombre(null);
										ldl_dl.setPrimerApellido(null);
										ldl_dl.setSegundoApellido(null);
										ldl_dl.setRazonSocial(ls_razonSocial);
									}

									ldl_dl.setIdUsuarioModificacion(ls_userId);
									ldl_dl.setIpModificacion(as_remoteIp);

									ldld_DAO.actualizarDatosSolicitante(ldl_dl);
								}
							}
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_VENCIDO));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_OBLIGATORIO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("actualizarDatosSolicitante", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("actualizarDatosSolicitante", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaActualizarDatosSolicitante ltsads_response;

			ltsads_response = new TipoSalidaActualizarDatosSolicitante(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsads_response;
		}
	}

	/**
	 * Método encargado de consultar el estado de la liquidacion.
	 *
	 * @param atecel_peticion Objeto de tipo TipoEntradaConsultarEstadoLiquidacion que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida consultar estado liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    TipoEntradaConsultarEstadoLiquidacion atecel_peticion, String as_userIdConstant, String as_localIp,
	    String                                as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                            ldm_manager;
		String                                                ls_descripcionMensaje;
		String                                                ls_numeroReferenciaSNR;
		TipoSalidaConsultarEstadoLiquidacion                  ltsel_response;
		BigInteger                                            lbi_codigoMensaje;
		TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion ltscelet_estadoTransaccion;

		ls_descripcionMensaje          = null;
		ls_numeroReferenciaSNR         = "";
		ltscelet_estadoTransaccion     = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaDisponible;
		ltsel_response                 = new TipoSalidaConsultarEstadoLiquidacion();
		lbi_codigoMensaje              = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager                    = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			NotificacionPago lnp_respuesta;

			lnp_respuesta = null;

			if(atecel_peticion != null)
			{
				String ls_numeroReferencia;

				ls_numeroReferencia = StringUtils.getStringNotNull(atecel_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					NotificacionPago lnp_np;

					lnp_np = DaoCreator.getNotificacionPagoDAO(ldm_manager).findByNumeroReferencia(ls_numeroReferencia);

					if(lnp_np != null)
					{
						{
							Date     ld_fechaLimite;
							Date     ld_fechaVencimiento;
							Calendar lc_ultimoDia;

							ld_fechaLimite          = new Date();
							ld_fechaVencimiento     = lnp_np.getFechaVencimiento();
							lc_ultimoDia            = Calendar.getInstance();

							lc_ultimoDia.set(Calendar.YEAR, DateUtils.getYear(ld_fechaLimite));
							lc_ultimoDia.set(Calendar.MONTH, Calendar.DECEMBER);
							lc_ultimoDia.set(Calendar.DAY_OF_MONTH, 31);
							lc_ultimoDia.set(Calendar.HOUR_OF_DAY, 23);
							lc_ultimoDia.set(Calendar.MINUTE, 59);
							lc_ultimoDia.set(Calendar.SECOND, 59);
							lc_ultimoDia.set(Calendar.MILLISECOND, 999);

							ld_fechaLimite = lc_ultimoDia.getTime();

							if(!ld_fechaLimite.after(ld_fechaVencimiento))
							{
								ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaVencida;
								throw new B2BException(addErrorNP(ErrorKeys.NUMERO_REFERENCIA_VENCIDO));
							}
						}

						double ld_valorTransaccion;

						ld_valorTransaccion = NumericUtils.getDouble(
							    atecel_peticion.getMontoTransaccion(), NumericUtils.DEFAULT_DOUBLE_VALUE
							);

						if(ld_valorTransaccion >= NumericUtils.DEFAULT_DOUBLE_VALUE)
						{
							double ld_valorServicio;

							ld_valorServicio = lnp_np.getMontoTransaccion();

							if(ld_valorServicio == ld_valorTransaccion)
								lnp_respuesta = lnp_np;
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_MONTO_TRANSACCION_DIF_MONTO_BACHUE));
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_MONTO_MAYOR_TRANSACCION_CERO));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));

			if(lnp_respuesta != null)
			{
				ls_numeroReferenciaSNR = StringUtils.getStringNotNull(lnp_respuesta.getNumeroReferenciaSNR());
				ltsel_response.setCodigoTransaccionRecaudador(lnp_respuesta.getNumeroTransaccionRecaudador());

				{
					String ls_estadoTransaccion;

					ls_estadoTransaccion = lnp_respuesta.getEstado();

					if(StringUtils.isValidString(ls_estadoTransaccion))
					{
						if(ls_estadoTransaccion.equalsIgnoreCase(EstadoCommon.REFERENCIA_DISPONIBLE))
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaDisponible;
						else if(ls_estadoTransaccion.equalsIgnoreCase(EstadoCommon.REFERENCIA_ANULADA))
						{
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaAnulada;
							throw new B2BException(addErrorNP(ErrorKeys.NUMERO_REFERENCIA_ANULADA));
						}
						else if(ls_estadoTransaccion.equalsIgnoreCase(EstadoCommon.REFERENCIA_PAGADA))
						{
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaPagada;
							throw new B2BException(addErrorNP(ErrorKeys.NUMERO_REFERENCIA_PAGADO));
						}
						else if(ls_estadoTransaccion.equalsIgnoreCase(EstadoCommon.REFERENCIA_VENCIDA))
						{
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReferenciaVencida;
							throw new B2BException(addErrorNP(ErrorKeys.NUMERO_REFERENCIA_VENCIDO));
						}
						else if(ls_estadoTransaccion.equalsIgnoreCase(EstadoCommon.RECIBO_DE_CAJA_GENERADO))
						{
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ReciboCajaGenerado;
							throw new B2BException(addErrorNP(ErrorKeys.NUMERO_REFERENCIA_RECIBO_CAJA_GENERADO));
						}
						else
							ltscelet_estadoTransaccion = TipoSalidaConsultarEstadoLiquidacionEstadoTransaccion.ProductoEntregado;
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEstadoLiquidacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarEstadoLiquidacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		ltsel_response.setEstadoTransaccion(ltscelet_estadoTransaccion);
		ltsel_response.setNumeroReferencia(ls_numeroReferenciaSNR);
		ltsel_response.setCodigoMensaje(lbi_codigoMensaje);
		ltsel_response.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsel_response;
	}

	/**
	 * Método encargado de consultar la tarifa para un servicio.
	 *
	 * @param atects_peticion Objeto de tipo TipoEntradaConsultarTarifaServicio que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida consultar tarifa servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaConsultarTarifaServicio consultarTarifaServicio(
	    TipoEntradaConsultarTarifaServicio atects_peticion, String as_userIdConstant, String as_localIp,
	    String                             as_remoteIp
	)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		TipoServicioCTSO[] ltst_respuesta;
		String             ls_descripcionMensaje;
		BigInteger         lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltst_respuesta            = null;

		try
		{
			if(atects_peticion != null)
			{
				String ls_userId;

				ls_userId = getSystemUser(as_userIdConstant, ldm_manager);

				if(atects_peticion.getCanalOrigen().toString() == null)
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO));

				if(atects_peticion.getCodigoConvenio() != null)
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_CONVENIO_NO_VALIDO));

				{
					String  ls_tipoDocSolicitante;
					String  ls_numeroDocSolicitante;
					boolean lb_nit;

					ls_tipoDocSolicitante       = StringUtils.getStringNotNull(atects_peticion.getTipoDocSolicitante());
					ls_numeroDocSolicitante     = StringUtils.getStringNotNull(
						    atects_peticion.getNumeroDocSolicitante()
						);
					lb_nit                      = false;

					if(StringUtils.isValidString(ls_tipoDocSolicitante))
					{
						InteresadoDocumentoTipo litd_tipoDocumento;

						litd_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
								                           .findById(
								    new InteresadoDocumentoTipo(ls_tipoDocSolicitante)
								);

						if(litd_tipoDocumento != null)
							lb_nit = StringUtils.getStringNotNull(litd_tipoDocumento.getIdDocumentoTipo())
									                .equalsIgnoreCase(IdentificadoresCommon.NIT);
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_EXISTE));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO));

					if(!StringUtils.isValidString(ls_numeroDocSolicitante))
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO));
					else if(lb_nit)
					{
						Long ll_tmp;

						ll_tmp = NumericUtils.getLongWrapper(ls_numeroDocSolicitante, -1L);

						if(!NumericUtils.isValidLong(ll_tmp))
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO));
					}
				}

				{
					TipoServicioCTSI[] ltsa_servicios;

					ltsa_servicios = atects_peticion.getServicios();

					if(CollectionUtils.isValidCollection(ltsa_servicios))
						ltst_respuesta = verificarServicios(
							    ltsa_servicios, ls_userId, as_localIp, as_remoteIp, ldm_manager,
							    ConstanteCommon.CTS_RELACION_PROCESOS_CRITERIOS, null, null
							);
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_SERVICIOS_A_CONSULTAR_NO_VALIDO));
				}
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarTarifaServicio", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("consultarTarifaServicio", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaConsultarTarifaServicio ltscts_response;

			if(ltst_respuesta == null)
			{
				ltst_respuesta        = new TipoServicioCTSO[1];
				ltst_respuesta[0]     = new TipoServicioCTSO("", "", BigDecimal.valueOf(0), null, null);
			}

			ltscts_response = new TipoSalidaConsultarTarifaServicio(
				    ltst_respuesta, lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltscts_response;
		}
	}

	/**
	 * Método encargado de generar la liquidacion.
	 *
	 * @param ategl_peticion Objeto de tipo TipoEntradaGenerarLiquidacion que provienen de una peticion al servicio
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida generar liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaGenerarLiquidacion generarLiquidacion(
	    TipoEntradaGenerarLiquidacion ategl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                   ldm_manager;
		DAOManager                   ldm_notificacionPagos;
		TipoSalidaGenerarLiquidacion ltsgl_respuesta;
		String                       ls_descripcionMensaje;
		BigInteger                   lbi_codigoMensaje;
		boolean                      lb_error;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();
		ldm_notificacionPagos     = DaoManagerFactory.getDAOManagerNPA();
		ltsgl_respuesta           = new TipoSalidaGenerarLiquidacion();
		lb_error                  = false;

		try
		{
			String ls_userId;

			ls_userId = getSystemUser(as_userId, ldm_manager);

			if(ategl_peticion != null)
			{
				Persona             lp_insertar;
				String              ls_idCanalOrigen;
				String              ls_idSucursalCanalOrigen;
				String              ls_numeroDocumento;
				String              ls_tipoPersona;
				String              ls_tipoDocumento;
				String              ls_tipoServicio;
				String              ls_subtipoServicio;
				boolean             lb_cuentaCupo;
				boolean             lb_solicitudCopiasSE;
				StringBuilder       lsb_correo;
				StringBuilder       lsb_telefono;
				CirculoRegistralDao lcrd_circuloRegistralDAO;

				ls_numeroDocumento       = StringUtils.getString(ategl_peticion.getNumeroDocSolicitante());
				ls_tipoDocumento         = StringUtils.getStringNotNull(ategl_peticion.getTipoDocSolicitante());
				ls_tipoPersona           = StringUtils.getString(ategl_peticion.getTipoPersona());
				ls_tipoServicio          = new String();
				ls_subtipoServicio       = new String();
				lb_cuentaCupo            = false;
				lb_solicitudCopiasSE     = false;

				lsb_correo                   = new StringBuilder();
				lsb_telefono                 = new StringBuilder();
				lcrd_circuloRegistralDAO     = DaoCreator.getCirculoRegistralDAO(ldm_manager);

				if(!StringUtils.isValidString(ls_numeroDocumento))
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO));

				if(!StringUtils.isValidString(ls_tipoPersona))
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_PERSONA_SOLICITANTE_NO_VALIDO));

				Map<Integer, Map<String, String>> lmim_servicioCriterios;

				lmim_servicioCriterios = new LinkedHashMap<Integer, Map<String, String>>();

				{
					CanalOrigenServicio lcos_canalOrigenServicio;

					{
						String ls_codigoCanalOrigen;

						ls_codigoCanalOrigen = StringUtils.getString(ategl_peticion.getCodigoCanal());

						if(StringUtils.isValidString(ls_codigoCanalOrigen))
							lcos_canalOrigenServicio = DaoCreator.getCanalOrigenServicioDAO(ldm_notificacionPagos)
									                                 .findByCodigo(ls_codigoCanalOrigen);
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO));
					}

					if(lcos_canalOrigenServicio != null)
						ls_idCanalOrigen = lcos_canalOrigenServicio.getIdCanalOrigenServicio();
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CANAL_ORIGEN_NO_PARAMETRIZADO));
				}

				{
					String ls_codigoSucursalCanalOrigen;

					ls_codigoSucursalCanalOrigen = StringUtils.getString(ategl_peticion.getCodigoSucursalCanal());

					if(StringUtils.isValidString(ls_codigoSucursalCanalOrigen))
					{
						SucursalCanalOrigenServicio lscos_sucursalCanal;

						lscos_sucursalCanal = DaoCreator.getSucursalCanalOrigenServicioDAO(ldm_notificacionPagos)
								                            .findByIdCanalCodigoSucursal(
								    new SucursalCanalOrigenServicio(ls_idCanalOrigen, ls_codigoSucursalCanalOrigen)
								);

						if(lscos_sucursalCanal != null)
							ls_idSucursalCanalOrigen = lscos_sucursalCanal.getIdSucursalCanalOrigenServicio();
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_SUCURSAL_CANAL_ORIGEN_NO_PARAMETRIZADO));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CANAL_ORIGEN_NO_VALIDO));
				}

				{
					String ls_oripSolicitud;

					ls_oripSolicitud = ategl_peticion.getOripSolicitud();

					if(StringUtils.isValidString(ls_oripSolicitud))
					{
						CirculoRegistral lcr_circulo;

						lcr_circulo = lcrd_circuloRegistralDAO.findById(ls_oripSolicitud);

						if(lcr_circulo == null)
						{
							Object[] loa_args;

							loa_args        = new Object[1];
							loa_args[0]     = ls_oripSolicitud;

							throw new B2BException(addErrorNP(ErrorKeys.CIRCULO_REGISTRAL_NO_ENCONTRADO, loa_args));
						}
					}
				}

				{
					String ls_primerNombre;
					String ls_segundoNombre;
					String ls_primerApellido;
					String ls_segundoApellido;
					String ls_razonSocial;

					ls_primerNombre        = StringUtils.getString(ategl_peticion.getPrimerNombreSolicitante());
					ls_segundoNombre       = StringUtils.getString(ategl_peticion.getSegundoNombreSolicitante());
					ls_primerApellido      = StringUtils.getString(ategl_peticion.getPrimerApellidoSolicitante());
					ls_segundoApellido     = StringUtils.getString(ategl_peticion.getSegundoApellidoSolicitante());
					ls_razonSocial         = StringUtils.getString(ategl_peticion.getRazonSocial());

					lp_insertar = validarPersona(
						    null, ls_tipoPersona, ls_tipoDocumento, ls_numeroDocumento, ls_primerNombre,
						    ls_segundoNombre, ls_primerApellido, ls_segundoApellido, ls_razonSocial, ldm_manager,
						    ls_userId, as_localIp, as_remoteIp, lsb_correo, lsb_telefono, false
						);
				}

				boolean                lb_entidadExenta;
				Map<String, Boolean>   lmsb_validacionesInsercion;
				PredioTipoDAO          ltpd_predioTipoDAO;
				LibroAntiguoSistemaDao llasd_libroAntiguoSistemaDAO;
				Long                   ll_identificadorCopiasSE;
				CuentaCupo             lcc_cuentaCupo;
				BigDecimal             lbd_valorRecarga;

				lb_entidadExenta                 = StringUtils.isValidString(ategl_peticion.getCodigoConvenio());
				lmsb_validacionesInsercion       = new HashMap<String, Boolean>();
				ltpd_predioTipoDAO               = DaoCreator.getPredioTipoDao(ldm_manager);
				llasd_libroAntiguoSistemaDAO     = DaoCreator.getLibroAntiguoSistemaDAO(ldm_manager);
				ll_identificadorCopiasSE         = null;
				lcc_cuentaCupo                   = null;
				lbd_valorRecarga                 = null;

				lmsb_validacionesInsercion.put(is_datosAntSistema, new Boolean(false));
				lmsb_validacionesInsercion.put(is_detalleAntSistema, new Boolean(false));
				lmsb_validacionesInsercion.put(is_solicitudMatricula, new Boolean(false));
				lmsb_validacionesInsercion.put(is_solicitudInterviniente, new Boolean(false));

				{
					TipoServicioGLI[] lstglia_servicios;

					lstglia_servicios = ategl_peticion.getServicios();

					if(CollectionUtils.isValidCollection(lstglia_servicios))
					{
						int                li_servicios;
						TipoServicioCTSI[] ltsctsia_verificarServicios;

						li_servicios                    = lstglia_servicios.length;
						ltsctsia_verificarServicios     = new TipoServicioCTSI[li_servicios];

						for(int li_servicio = 0; li_servicio < li_servicios; li_servicio++)
						{
							TipoServicioGLI ltsgli_servicio;

							ltsgli_servicio = lstglia_servicios[li_servicio];

							if(ltsgli_servicio != null)
							{
								String           ls_tipoServicioActual;
								String           ls_subtipoServicioActual;
								TipoServicioCTSI ltsctsi_verificarServicio;

								ls_tipoServicioActual        = StringUtils.getStringNotNull(
									    ltsgli_servicio.getTipoServicio()
									);
								ls_subtipoServicioActual     = StringUtils.getStringNotNull(
									    ltsgli_servicio.getSubtipoServicio()
									);

								if(
								    !ls_tipoServicio.isEmpty()
									    && !ls_tipoServicio.equalsIgnoreCase(ls_tipoServicioActual)
								)
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_SOLO_PERMITE_UN_TIPO_SERVICIO));

								if(
								    !ls_subtipoServicio.isEmpty()
									    && !ls_subtipoServicio.equalsIgnoreCase(ls_subtipoServicioActual)
								)
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_SOLO_PERMITE_UN_SUB_TIPO_SERVICIO)
									);

								ltsctsi_verificarServicio                    = new TipoServicioCTSI();
								ltsctsia_verificarServicios[li_servicio]     = ltsctsi_verificarServicio;

								ltsctsi_verificarServicio.setTipoServicio(ls_tipoServicioActual);
								ltsctsi_verificarServicio.setSubtipoServicio(ls_subtipoServicioActual);

								{
									TipoCriterioGLI[] ltcglia_criterios;

									ltcglia_criterios = ltsgli_servicio.getCriterios();

									if(CollectionUtils.isValidCollection(ltcglia_criterios))
									{
										int                li_criterios;
										TipoCriterioCTSI[] ltctsia_criterios;

										li_criterios          = ltcglia_criterios.length;
										ltctsia_criterios     = new TipoCriterioCTSI[li_criterios];

										for(int li_criterio = 0; li_criterio < li_criterios; li_criterio++)
										{
											TipoCriterioGLI ltcgli_criterio;

											ltcgli_criterio = ltcglia_criterios[li_criterio];

											if(ltcgli_criterio != null)
											{
												String ls_codigo;
												String ls_valor;

												ls_codigo     = ltcgli_criterio.getCodigo();
												ls_valor      = ltcgli_criterio.getValor();

												if(
												    (ls_codigo.equalsIgnoreCase(IdentificadoresCommon.FORMA_ENVIO_MAIL)
													    || ls_codigo.equalsIgnoreCase(
													        IdentificadoresCommon.CORREO_ELECTRONICO_TITULAR
													    )) && lsb_correo.toString().isEmpty()
												)
													lsb_correo.append(ls_valor);
												else if(ls_codigo.equalsIgnoreCase(IdentificadoresCommon.CANTIDAD))
												{
													Integer li_valorCantidad;

													li_valorCantidad = NumericUtils.getInteger(ls_valor);

													if(li_valorCantidad.intValue() != 1)
														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_VALOR_CRITERIO_CANTIDAD_NO_VALIDO
														    )
														);
												}
												else if(
												    ls_codigo.equalsIgnoreCase(IdentificadoresCommon.TIPO_PREDIO_AS)
												)
												{
													PredioTipo lpt_predioTipo;

													lpt_predioTipo = ltpd_predioTipoDAO.findById(ls_valor);

													if(lpt_predioTipo == null)
														throw new B2BException(
														    addErrorNP(ErrorKeys.ERROR_TIPO_PREDIO_NO_VALIDO)
														);
												}
												else if(ls_codigo.equalsIgnoreCase(IdentificadoresCommon.LIBRO_AS))
												{
													LibroAntiguoSistema llas_libro;

													llas_libro = llasd_libroAntiguoSistemaDAO.findById(
														    NumericUtils.getLong(ls_valor)
														);

													if(llas_libro == null)
														throw new B2BException(
														    addErrorNP(ErrorKeys.ERROR_LIBRO_ANTIGUO_SISTEMA_NO_VALIDO)
														);
												}
												else if(
												    ls_codigo.equalsIgnoreCase(IdentificadoresCommon.ORIP_MATRICULA_AS)
												)
												{
													CirculoRegistral lcr_circuloRegistral;

													lcr_circuloRegistral = lcrd_circuloRegistralDAO.findById(ls_valor);

													if(lcr_circuloRegistral == null)
													{
														Object[] loa_args;

														loa_args     = new String[1];

														loa_args[0] = ls_valor;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.CIRCULO_REGISTRAL_AS_NO_ENCONTRADO, loa_args
														    )
														);
													}
												}
												else if(
												    ls_codigo.equalsIgnoreCase(IdentificadoresCommon.FECHA_DOCUMENTO)
												)
												{
													Date ld_fecha;

													ld_fecha = DateUtils.getDate(
														    ls_valor, FormatoFechaCommon.DIA_MES_ANIO
														);

													if(ld_fecha == null)
													{
														Object[] loa_args;

														loa_args     = new String[3];

														loa_args[0]     = ls_tipoServicioActual;
														loa_args[1]     = ls_subtipoServicioActual;
														loa_args[2]     = ls_codigo;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_FECHA_VALIDO,
														        loa_args
														    )
														);
													}
												}
												else if(
												    ls_codigo.equalsIgnoreCase(IdentificadoresCommon.TIPO_DOCUMENTO)
												)
												{
													TipoDocumentoPublico ltdp_tipoDocumentoPublico;

													ltdp_tipoDocumentoPublico = DaoCreator.getTipoDocumentoPublicoDAO(
														    ldm_manager
														).findById(StringUtils.getString(ls_valor));

													if(ltdp_tipoDocumentoPublico == null)
													{
														Object[] loa_args;

														loa_args     = new String[3];

														loa_args[0]     = ls_tipoServicioActual;
														loa_args[1]     = ls_subtipoServicioActual;
														loa_args[2]     = ls_codigo;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO,
														        loa_args
														    )
														);
													}
												}
												else if(
												    ls_codigo.equalsIgnoreCase(IdentificadoresCommon.ID_CUENTA_CUPO)
												)
												{
													if(
													    (ls_valor != null)
														    && (ls_valor.length() > NumericUtils.DEFAULT_INT_VALUE)
													)
														lcc_cuentaCupo = validarCuentaCupo(
															    ls_valor, new CodigoError(), ldm_manager
															);
													else
													{
														Object[] loa_args;

														loa_args     = new String[3];

														loa_args[0]     = ls_tipoServicioActual;
														loa_args[1]     = ls_subtipoServicioActual;
														loa_args[2]     = ls_codigo;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO,
														        loa_args
														    )
														);
													}
												}
												else if(
												    ls_codigo.equalsIgnoreCase(
													        IdentificadoresCommon.IDENTIFICADOR_COPIAS_SE
													    )
												)
												{
													validarSolicitudCopias(ls_valor, new CodigoError(), ldm_manager);

													ll_identificadorCopiasSE = NumericUtils.getLongWrapper(ls_valor);
												}
												else if(
												    ls_codigo.equalsIgnoreCase(
													        IdentificadoresCommon.CRITERIO_VALOR_RECARGA
													    )
												)
													lbd_valorRecarga = NumericUtils.getBigDecimal(ls_valor);
												else if(
												    ls_codigo.equalsIgnoreCase(
													        IdentificadoresCommon.NUMERO_CELULAR_TITULAR
													    ) && lsb_telefono.toString().isEmpty()
												)
													lsb_telefono.append(ls_valor);

												ltctsia_criterios[li_criterio] = new TipoCriterioCTSI(
													    ls_codigo, ls_valor
													);
											}
										}

										ltsctsi_verificarServicio.setCriterios(ltctsia_criterios);
									}
								}

								if(li_servicio == 0)
								{
									ls_tipoServicio          = ls_tipoServicioActual;
									ls_subtipoServicio       = ls_subtipoServicioActual;
									lb_cuentaCupo            = ls_tipoServicio.equalsIgnoreCase(
										    ProcesoCommon.ID_PROCESO_60
										) && ls_subtipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4);
									lb_solicitudCopiasSE     = ls_tipoServicio.equalsIgnoreCase(
										    ProcesoCommon.ID_PROCESO_5
										) && ls_subtipoServicio.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3);
								}

								if(!lb_cuentaCupo && !lb_solicitudCopiasSE)
								{
									BigInteger lbi_cantidad;

									lbi_cantidad = ltsgli_servicio.getCantidadSolicitada();

									if(lbi_cantidad != null)
									{
										boolean lb_procesoAlertas;
										int     li_cantidad;
										boolean lb_validarCantidadAlertas;
										boolean lb_validarCantidadSolicitada;

										li_cantidad           = lbi_cantidad.intValue();
										lb_procesoAlertas     = ls_tipoServicio.equalsIgnoreCase(
											    ProcesoCommon.ID_PROCESO_50
											) && ls_subtipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1);

										lb_validarCantidadAlertas        = lb_procesoAlertas && (li_cantidad != 1);
										lb_validarCantidadSolicitada     = !lb_procesoAlertas && (li_cantidad < 1);

										if(lb_validarCantidadAlertas || lb_validarCantidadSolicitada)
										{
											Object[] loa_messageArgs = new String[3];

											loa_messageArgs[0]     = ls_tipoServicio;
											loa_messageArgs[1]     = ls_subtipoServicio;
											loa_messageArgs[2]     = IdentificadoresCommon.CANTIDAD_SOLICITADA;

											throw new B2BException(
											    addErrorNP(
											        lb_validarCantidadAlertas
											        ? ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_VALOR_INVALIDO_ALERTAS
											        : ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_VALOR_INVALIDO,
											        loa_messageArgs
											    )
											);
										}
									}
								}
							}
						}

						TipoServicioCTSO[] ltst_respuesta;

						{
							TipoEntradaConsultarTarifaAlertasTitulares atectat_request;

							atectat_request = null;

							if(
							    ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_50)
								    && ls_subtipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
							)
								atectat_request = new TipoEntradaConsultarTarifaAlertasTitulares(
									    SistemaOrigenCommon.SEDE_ELECTRONICA, String.valueOf(li_servicios),
									    ls_tipoDocumento, ls_numeroDocumento
									);

							ltst_respuesta = verificarServicios(
								    ltsctsia_verificarServicios, ls_userId, as_localIp, as_remoteIp, ldm_manager,
								    ConstanteCommon.RELACION_PROCESOS_CRITERIOS, atectat_request,
								    lmsb_validacionesInsercion
								);

							int li_cantidadServicios;

							li_cantidadServicios = ltsctsia_verificarServicios.length;

							for(int li_servicio = 0; li_servicio < li_cantidadServicios; li_servicio++)
							{
								TipoServicioCTSI    ltscsti_servicio;
								Map<String, String> lmss_criterios;

								lmss_criterios       = new LinkedHashMap<String, String>();
								ltscsti_servicio     = ltsctsia_verificarServicios[li_servicio];

								if(ltscsti_servicio != null)
								{
									TipoCriterioCTSI[] ltcctsi_criterios;

									ltcctsi_criterios = ltscsti_servicio.getCriterios();

									if(CollectionUtils.isValidCollection(ltcctsi_criterios))
									{
										int li_criterios;

										li_criterios = ltcctsi_criterios.length;

										for(int li_criterio = 0; li_criterio < li_criterios; li_criterio++)
										{
											TipoCriterioCTSI ltcgli_criterio;

											ltcgli_criterio = ltcctsi_criterios[li_criterio];

											if(ltcgli_criterio != null)
											{
												String ls_codigo;
												String ls_valor;

												ls_codigo     = ltcgli_criterio.getCodigo();
												ls_valor      = ltcgli_criterio.getValor();

												lmss_criterios.put(ls_codigo, ls_valor);
											}
										}
									}
								}

								lmim_servicioCriterios.put(Integer.valueOf(li_servicio), lmss_criterios);
							}
						}

						int li_servicios2;

						li_servicios2 = ltst_respuesta.length;

						TipoServicioGLO[] ltsl_serviciosLiquidados;
						ltsl_serviciosLiquidados = new TipoServicioGLO[li_servicios2];

						BigDecimal lbdc_valor_total_servicio;
						lbdc_valor_total_servicio = BigDecimal.ZERO;

						for(int li_i = 0; li_i < li_servicios2; li_i++)
						{
							ltsl_serviciosLiquidados[li_i] = new TipoServicioGLO();
							ltsl_serviciosLiquidados[li_i].setTipoServicio(ltst_respuesta[li_i].getTipoServicio());
							ltsl_serviciosLiquidados[li_i].setSubtipoServicio(
							    ltst_respuesta[li_i].getSubtipoServicio()
							);
							ltsl_serviciosLiquidados[li_i].setCantidadSolicitada(
							    lstglia_servicios[li_i].getCantidadSolicitada()
							);

							{
								TipoServicioGLI lts_actual;

								lts_actual = ategl_peticion.getServicios()[li_i];

								if(lts_actual != null)
								{
									BigDecimal lbd_valor;
									BigDecimal lbd_valorServicio;

									lbd_valor     = ltst_respuesta[li_i].getValorServicio();

									lbd_valorServicio = lts_actual.getValorServicio();

									if(lb_cuentaCupo && (lcc_cuentaCupo != null))
									{
										lbd_valor = lbd_valorServicio;

										if(NumericUtils.isValidBigDecimal(lbd_valorRecarga, BigDecimal.ONE))
										{
											BigDecimal lbd_montoMinimo;
											BigDecimal lbd_montoMaximo;

											lbd_montoMinimo     = lcc_cuentaCupo.getValorMinimo();
											lbd_montoMaximo     = lcc_cuentaCupo.getValorMaximo();

											if(lbd_valorRecarga.compareTo(lbd_valorServicio) != 0)
												throw new B2BException(
												    addErrorNP(ErrorKeys.ERROR_VALOR_RECARGA_DIFERENTE_VALOR_SERVICIO)
												);

											if(
											    !NumericUtils.isValidBigDecimal(lbd_montoMinimo)
												    || !NumericUtils.isValidBigDecimal(lbd_montoMaximo, BigDecimal.ONE)
											)
												throw new B2BException(
												    addErrorNP(ErrorKeys.ERROR_VALORES_MIN_MAX_CUENTA_CUPO_NO_VALIDOS)
												);

											if(
											    (lbd_montoMinimo.compareTo(lbd_valorRecarga) > 0)
												    || (lbd_montoMaximo.compareTo(lbd_valorRecarga) < 0)
											)
												throw new B2BException(
												    addErrorNP(ErrorKeys.ERROR_VALOR_RECARGA_NO_VALIDO)
												);
										}
										else
											throw new B2BException(addErrorNP(ErrorKeys.ERROR_SIN_VALOR_RECARGA));
									}

									if(lb_solicitudCopiasSE)
										lbd_valorServicio = NumericUtils.getBigDecimal(lbd_valor.doubleValue());

									if(NumericUtils.isValidBigDecimal(lbd_valorServicio, BigDecimal.valueOf(1L)))
									{
										double ld_valorServicio;

										ld_valorServicio = lbd_valorServicio.doubleValue();

										if(ld_valorServicio != lbd_valor.doubleValue())
										{
											Object[] loa_messageArgs = new String[3];

											loa_messageArgs[0]     = ls_tipoServicio;
											loa_messageArgs[1]     = ls_subtipoServicio;
											loa_messageArgs[2]     = String.valueOf(li_i + 1);

											throw new B2BException(
											    addErrorNP(
											        ErrorKeys.ERROR_VALOR_SERVICIO_DIFERENTE_A_BACHUE, loa_messageArgs
											    )
											);
										}
									}
									else
									{
										Object[] loa_messageArgs = new String[3];

										loa_messageArgs[0]     = ls_tipoServicio;
										loa_messageArgs[1]     = ls_subtipoServicio;
										loa_messageArgs[2]     = String.valueOf(li_i + 1);

										throw new B2BException(
										    addErrorNP(ErrorKeys.ERROR_VALOR_SERVICIO_INVALIDO, loa_messageArgs)
										);
									}

									ltsl_serviciosLiquidados[li_i].setValorServicio(
									    lb_entidadExenta ? NumericUtils.DEFAULT_BIG_DECIMAL_VALUE : lbd_valor
									);
								}
							}

							BigDecimal lbdc_valor_actual;
							BigInteger lbi_cantidadSolicitada;

							lbdc_valor_actual          = BigDecimal.ZERO;
							lbi_cantidadSolicitada     = ltsl_serviciosLiquidados[li_i].getCantidadSolicitada();

							if(!NumericUtils.isValidBigInteger(lbi_cantidadSolicitada, BigInteger.valueOf(-1L)))
							{
								Object[] loa_messageArgs = new String[3];

								loa_messageArgs[0]     = ls_tipoServicio;
								loa_messageArgs[1]     = ls_subtipoServicio;
								loa_messageArgs[2]     = String.valueOf(li_i + 1);

								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_CANTIDAD_SERVICIO_INVALIDO, loa_messageArgs)
								);
							}

							lbdc_valor_actual     = lbdc_valor_actual.add(new BigDecimal(lbi_cantidadSolicitada));
							lbdc_valor_actual     = lbdc_valor_actual.multiply(
								    ltsl_serviciosLiquidados[li_i].getValorServicio()
								);

							lbdc_valor_total_servicio = lbdc_valor_total_servicio.add(lbdc_valor_actual);
						}

						ltsgl_respuesta.setServiciosLiquidados(ltsl_serviciosLiquidados);
						ltsgl_respuesta.setValorTotalServicio(lbdc_valor_total_servicio);
					}
				}

				UtilDAO                   lut_DAO;
				CampoCriterioBusquedaDAO  lccbd_DAO;
				SolicitudIntervinienteDAO lsid_DAO;
				Solicitud                 ls_solicitud;

				lut_DAO          = DaoCreator.getUtilDAO(ldm_manager);
				lsid_DAO         = DaoCreator.getSolicitudIntervinienteDAO(ldm_manager);
				lccbd_DAO        = DaoCreator.getCampoCriterioBusquedaDAO(ldm_manager);
				ls_solicitud     = crearSolicitud(
					    new DatosCreacionSolicitud(
					        ls_tipoServicio, ls_subtipoServicio, lp_insertar.getIdPersona(), ls_idCanalOrigen,
					        ls_idSucursalCanalOrigen, lsb_correo.toString(), lsb_telefono.toString(),
					        (lcc_cuentaCupo != null) ? lcc_cuentaCupo.getIdCuentaCupo() : null, lbd_valorRecarga,
					        ll_identificadorCopiasSE, lb_entidadExenta
					    ), as_userId, as_remoteIp, ldm_manager
					);

				if(ls_solicitud == null)
					throw new B2BException(ErrorKeys.ERROR_CREANDO_SOLICITUD);

				if(!lb_cuentaCupo && !lb_solicitudCopiasSE)
				{
					Constantes lc_actual;
					String     ls_ccrd;

					ls_ccrd     = ConstanteCommon.RELACION_CRITERIOS_DESTINO + IdentificadoresCommon.SIMBOLO_GUION_BAJO
						+ ls_tipoServicio + IdentificadoresCommon.SIMBOLO_GUION_BAJO + ls_subtipoServicio;

					lc_actual = com.bachue.snr.prosnr01.dao.DaoCreator.getConstantesDAO(ldm_manager).findById(ls_ccrd);

					if(lc_actual == null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_ccrd;

						throw new B2BException(
						    addErrorNP(ErrorKeys.ERROR_NO_SE_ENCONTRO_LA_CONSTANTE, loa_messageArgs)
						);
					}

					Constantes lcns_rc_actual;

					lcns_rc_actual = com.bachue.snr.prosnr01.dao.DaoCreator.getConstantesDAO(ldm_manager)
							                                                   .findByIdWithImage(lc_actual);

					if(lcns_rc_actual == null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_ccrd;

						throw new B2BException(
						    addErrorNP(ErrorKeys.ERROR_NO_SE_ENCONTRO_LA_CONSTANTE, loa_messageArgs)
						);
					}

					if(lcns_rc_actual.getImagenes() == null)
					{
						Object[] loa_messageArgs = new String[1];

						loa_messageArgs[0] = ls_ccrd;
						throw new B2BException(
						    addErrorNP(ErrorKeys.ERROR_NO_SE_ENCONTRO_ARCHIVO_DE_LA_CONSTANTE, loa_messageArgs)
						);
					}

					String ls_parametrizacion;
					String ls_procesoConsulta;

					ls_parametrizacion     = new String(lcns_rc_actual.getImagenes());
					ls_procesoConsulta     = null;

					if(ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2))
					{
						ProcesoConsulta lpc_procesoConsulta;

						lpc_procesoConsulta = DaoCreator.getProcesoConsultaDAO(ldm_manager)
								                            .findByProcesoSubproceso(
								    ls_tipoServicio, ls_subtipoServicio
								);

						if(lpc_procesoConsulta != null)
							ls_procesoConsulta = lpc_procesoConsulta.getIdProcesoConsulta();
					}

					if(StringUtils.isValidString(ls_parametrizacion))
					{
						if(CollectionUtils.isValidCollection(lmim_servicioCriterios))
						{
							String[] lsa_tablasCampos;

							lsa_tablasCampos = ls_parametrizacion.split("\n");

							for(Map.Entry<Integer, Map<String, String>> lmim_iterador : lmim_servicioCriterios.entrySet())
							{
								if(lmim_iterador != null)
								{
									Map<String, String> lmss_criterios;

									lmss_criterios = new LinkedHashMap<String, String>(lmim_iterador.getValue());

									if(
									    CollectionUtils.isValidCollection(lsa_tablasCampos)
										    && CollectionUtils.isValidCollection(lmss_criterios)
									)
									{
										boolean             lb_insertar;
										int                 li_tablaActual;
										Map<String, Object> lmso_llaves;
										Persona             lp_propietario;
										String[]            lsa_tablas;

										lsa_tablas         = lsa_tablasCampos[0].split(",");
										li_tablaActual     = 1;
										lmso_llaves        = new LinkedHashMap<String, Object>();
										lp_propietario     = null;

										lmso_llaves.put("SDB_ACC_SOLICITUD", ls_solicitud.getIdSolicitud());

										if(ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2))
										{
											String ls_orip;

											if(ls_subtipoServicio.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_8))
											{
												if(
												    StringUtils.isValidString(
													        lmss_criterios.get(IdentificadoresCommon.TIPO_DOCUMENTO)
													    )
													    && StringUtils.isValidString(
													        lmss_criterios.get(IdentificadoresCommon.NUMERO_DOCUMENTO)
													    )
													    && StringUtils.isValidString(
													        lmss_criterios.get(IdentificadoresCommon.FECHA_DOCUMENTO)
													    )
												)
													lmso_llaves.put(
													    "SDB_PGN_TIPO_CRITERIO_BUSQUEDA_DOCUMENTO",
													    TipoCriterioBusquedaCommon.DOCUMENTO
													);
											}

											ls_orip = lmss_criterios.get("ORIP_MATRICULA");
											lmso_llaves.put("SDB_PGN_PROCESO_CONSULTA", ls_procesoConsulta);
											lmso_llaves.put(
											    "SDB_PGN_TIPO_CRITERIO_BUSQUEDA",
											    TipoCriterioBusquedaCommon.IDENTIFICACION
											);

											lmss_criterios.put(
											    "CONSULTA_NACIONAL",
											    (StringUtils.isValidString(ls_orip)) ? EstadoCommon.NO : EstadoCommon.SI
											);
										}

										lmss_criterios.put("ID_SOLICITUD", ls_solicitud.getIdSolicitud());
										lmss_criterios.put(
										    "CANTIDAD",
										    ategl_peticion.getServicios()[lmim_iterador.getKey().intValue()].getCantidadSolicitada()
											                                                                    .toString()
										);

										for(String ls_tablas : lsa_tablas)
										{
											if(StringUtils.isValidString(ls_tablas))
											{
												String[] lsa_llave;

												lsa_llave = ls_tablas.split("\\|");

												if(CollectionUtils.isValidCollection(lsa_llave))
												{
													Map<String, Object> lmso_entidad;

													lb_insertar      = true;
													lmso_entidad     = new LinkedHashMap<String, Object>();

													lmso_entidad.put("TABLABD", lsa_llave[0]);

													for(int li_j = 1, li_max = lsa_llave.length; li_j < li_max;
														    li_j += 3
													)
													{
														String ls_tipoLlave;

														ls_tipoLlave = lsa_llave[li_j];

														if(StringUtils.isValidString(ls_tipoLlave))
														{
															if(ls_tipoLlave.equalsIgnoreCase("L"))
															{
																String ls_tablaReferenciada;

																lb_insertar              = false;
																ls_tablaReferenciada     = lsa_llave[li_j + 1];

																if(lmso_llaves.containsKey(ls_tablaReferenciada))
																{
																	String ls_campoReferenciado;

																	lb_insertar              = true;
																	ls_campoReferenciado     = lsa_llave[li_j + 2];

																	lmso_entidad.put(
																	    ls_campoReferenciado,
																	    lmso_llaves.get(ls_tablaReferenciada)
																	);
																}
															}
															else if(ls_tipoLlave.equalsIgnoreCase("S"))
															{
																List<Map<String, Object>> llmso_resultado;
																Map<Integer, Object>      lmio_criterios;
																String                    ls_columna;
																String                    ls_nombreSecuencia;
																String                    ls_senteciaSecuencia;

																lmio_criterios = new LinkedHashMap<Integer, Object>();

																lmio_criterios.put(Integer.valueOf(1), "1");
																lmio_criterios.put(Integer.valueOf(2), "1");

																ls_columna               = lsa_llave[li_j + 2];
																ls_nombreSecuencia       = lsa_llave[li_j + 1];
																ls_senteciaSecuencia     = "SELECT "
																	+ ls_nombreSecuencia
																	+ ".NEXTVAL LLAVE FROM DUAL WHERE ? = ?";

																llmso_resultado = lut_DAO.getCustonQuery(
																	    ls_senteciaSecuencia, lmio_criterios
																	);

																if(CollectionUtils.isValidCollection(llmso_resultado))
																{
																	Map<String, Object> lmso_resultado;

																	lmso_resultado = llmso_resultado.get(0);

																	if(lmso_resultado != null)
																	{
																		Object lo_llave;

																		lo_llave = lmso_resultado.get("LLAVE");

																		if(lo_llave != null)
																		{
																			lmso_entidad.put(ls_columna, lo_llave);

																			lmso_llaves.put(
																			    lmso_entidad.get("TABLABD").toString(),
																			    lo_llave
																			);
																		}
																	}
																}
															}
															else if(ls_tipoLlave.startsWith("M"))
															{
																String ls_columnaMax;

																ls_columnaMax = ls_tipoLlave.substring(2);

																if(StringUtils.isValidString(ls_columnaMax))
																{
																	int                  li_where;
																	Map<Integer, Object> lmio_criterios;
																	String               ls_sentencia;
																	String               ls_tabla;
																	String               ls_where;

																	lmio_criterios     = new LinkedHashMap<Integer, Object>();
																	ls_tabla           = lsa_llave[li_j + 1];

																	lmio_criterios.put(Integer.valueOf(1), "1");
																	lmio_criterios.put(Integer.valueOf(2), "1");

																	ls_sentencia     = "SELECT MAX(" + ls_columnaMax
																		+ ") LLAVE FROM " + ls_tabla;

																	ls_where     = "";
																	li_where     = 0;

																	for(Map.Entry<String, Object> lmeio_criterios : lmso_entidad
																		    .entrySet())
																	{
																		if(
																		    (lmeio_criterios != null)
																			    && !lmeio_criterios.getKey()
																			                           .equalsIgnoreCase(
																			        "TABLABD"
																			    )
																		)
																		{
																			if(ls_where.isEmpty())
																			{
																				lmio_criterios     = new LinkedHashMap<Integer, Object>();
																				ls_where           = " WHERE ";
																			}
																			else
																				ls_where += " AND ";

																			ls_where += (lmeio_criterios.getKey()
																				+ " = ?");
																			li_where++;

																			lmio_criterios.put(
																			    Integer.valueOf(li_where),
																			    lmeio_criterios.getValue()
																			);
																		}
																	}

																	if(li_where == 0)
																		ls_where = " WHERE ? = ?";

																	ls_sentencia += ls_where;

																	{
																		List<Map<String, Object>> llmso_resultado;

																		llmso_resultado = lut_DAO.getCustonQuery(
																			    ls_sentencia, lmio_criterios
																			);

																		if(
																		    CollectionUtils.isValidCollection(
																			        llmso_resultado
																			    )
																		)
																		{
																			long ll_max;

																			ll_max = 0;

																			for(Map<String, Object> lmso_resultado : llmso_resultado)
																			{
																				if(lmso_resultado != null)
																				{
																					Object lo_valor;

																					lo_valor = lmso_resultado.get(
																						    "LLAVE"
																						);

																					if(lo_valor != null)
																						ll_max = NumericUtils.getLong(
																							    lo_valor.toString()
																							);
																				}
																			}

																			ll_max++;

																			{
																				String ls_max;

																				{
																					StringBuilder lsb_max;

																					lsb_max = new StringBuilder();

																					lsb_max.append(ll_max);

																					ls_max = lsb_max.toString();
																				}

																				lmso_entidad.put(
																				    lsa_llave[li_j + 2], ls_max
																				);
																				lmso_llaves.put(
																				    lmso_entidad.get("TABLABD")
																					                .toString(), ls_max
																				);
																			}
																		}
																	}
																}
															}
														}
													}

													if(lb_insertar)
													{
														String[] lsa_campos;
														String   ls_nombreTabla;

														lmso_entidad.put("ID_USUARIO_CREACION", ls_userId);
														lmso_entidad.put("FECHA_CREACION", new Date());

														lsa_campos         = lsa_tablasCampos[li_tablaActual].split(
															    ","
															);
														ls_nombreTabla     = lmso_entidad.get("TABLABD").toString();

														if(StringUtils.isValidString(ls_nombreTabla))
														{
															if(ls_nombreTabla.equalsIgnoreCase("SDB_ACC_PERSONA"))
															{
																if(
																    lmss_criterios.containsKey(lsa_campos[1])
																	    && lmss_criterios.containsKey(lsa_campos[2])
																)
																{
																	if(
																	    ls_tipoServicio.equalsIgnoreCase(
																		        ProcesoCommon.ID_PROCESO_2
																		    )
																	)
																	{
																		if(
																		    CollectionUtils.isValidCollection(
																			        lsa_campos
																			    )
																		)
																		{
																			int li_size;

																			li_size = lsa_campos.length;

																			for(int i = 0; i < li_size; i++)
																			{
																				String ls_llave;
																				String ls_tmp;

																				ls_llave     = lsa_campos[i];
																				ls_tmp       = null;

																				if(StringUtils.isValidString(ls_llave))
																				{
																					ls_llave     = ls_llave.replace(
																						    "\r", ""
																						);
																					ls_tmp       = datoCampo(
																						    ls_llave, lmss_criterios
																						);
																				}

																				if(StringUtils.isValidString(ls_tmp))
																				{    //TODO validar longitud de campo ls_tmp.length() vs el campo longitud de la consulta CamposConsulta

																					CamposConsulta lcc_cc;

																					lcc_cc = lccbd_DAO
																							.buscarCamposPorTipoCampoCriterioServicio(
																							    (StringUtils
																							    .isValidString(ls_llave)
																							    && (ls_llave
																							    .equalsIgnoreCase(IdentificadoresCommon.TIPO_DOCUMENTO)
																							    || ls_llave
																							    .equalsIgnoreCase(IdentificadoresCommon.NUMERO_DOCUMENTO)
																							    || ls_llave
																							    .equalsIgnoreCase(IdentificadoresCommon.FECHA_DOCUMENTO)))
																							    ? TipoCriterioBusquedaCommon.DOCUMENTO
																							        : TipoCriterioBusquedaCommon.IDENTIFICACION,
																							    ls_llave
																							);

																					if(lcc_cc != null)
																						lmso_llaves.put(
																						    ls_llave,
																						    lcc_cc
																							    .getIdCampoCriterioBusqueda()
																						);
																				}
																			}
																		}
																	}
																	else
																	{
																		String ls_criterioNumeroDocumento;
																		String ls_criterioPrimerApellido;
																		String ls_criterioPrimerNombre;
																		String ls_criterioRazonSocial;
																		String ls_criterioSegundoApellido;
																		String ls_criterioSegundoNombre;
																		String ls_criterioTipoDocumento;
																		String ls_criterioTipoPersona;

																		ls_criterioTipoPersona         = datoCampo(
																			    lsa_campos[0], lmss_criterios
																			);
																		ls_criterioTipoDocumento       = datoCampo(
																			    lsa_campos[1], lmss_criterios
																			);
																		ls_criterioNumeroDocumento     = datoCampo(
																			    lsa_campos[2], lmss_criterios
																			);
																		ls_criterioPrimerNombre        = datoCampo(
																			    lsa_campos[3], lmss_criterios
																			);
																		ls_criterioSegundoNombre       = datoCampo(
																			    lsa_campos[4], lmss_criterios
																			);
																		ls_criterioPrimerApellido      = datoCampo(
																			    lsa_campos[5], lmss_criterios
																			);
																		ls_criterioSegundoApellido     = datoCampo(
																			    lsa_campos[6], lmss_criterios
																			);
																		ls_criterioRazonSocial         = datoCampo(
																			    lsa_campos[7], lmss_criterios
																			);

																		if(
																		    (ls_criterioTipoPersona != null)
																			    || (ls_criterioTipoDocumento != null)
																			    || (ls_criterioNumeroDocumento != null)
																			    || (ls_criterioPrimerNombre != null)
																			    || (ls_criterioSegundoNombre != null)
																			    || (ls_criterioPrimerApellido != null)
																			    || (ls_criterioSegundoApellido != null)
																			    || (ls_criterioRazonSocial != null)
																		)
																		{
																			lp_propietario = validarPersona(
																				    null, ls_criterioTipoPersona,
																				    ls_criterioTipoDocumento,
																				    ls_criterioNumeroDocumento,
																				    ls_criterioPrimerNombre,
																				    ls_criterioSegundoNombre,
																				    ls_criterioPrimerApellido,
																				    ls_criterioSegundoApellido,
																				    ls_criterioRazonSocial, ldm_manager,
																				    ls_userId, as_localIp, as_remoteIp,
																				    new StringBuilder(),
																				    new StringBuilder()
																				);

																			lmso_llaves.put(
																			    ls_nombreTabla,
																			    lp_propietario.getIdPersona()
																			);
																		}
																	}
																}
															}
															else
															{
																{
																	if(
																	    ls_nombreTabla.equalsIgnoreCase(
																		        "SDB_ACC_SOLICITUD_INTERVINIENTE"
																		    )
																	)
																	{
																		boolean lb_insertSolicitudInterviniente;
																		String  ls_idSolicitud;
																		String  ls_idPersona;

																		lb_insertSolicitudInterviniente     = false;
																		ls_idSolicitud                      = ls_solicitud
																				.getIdSolicitud();
																		ls_idPersona                        = StringUtils
																				.getString(
																				    lmso_llaves.get("SDB_ACC_PERSONA")
																				);

																		if(
																		    StringUtils.isValidString(ls_idSolicitud)
																			    && StringUtils.isValidString(
																			        ls_idPersona
																			    )
																		)
																		{
																			SolicitudInterviniente lsi_si;

																			lsi_si     = lsid_DAO.findById(
																				    ls_idSolicitud, ls_idPersona
																				);

																			lb_insertSolicitudInterviniente = (lsi_si == null);
																		}

																		lmsb_validacionesInsercion.put(
																		    is_solicitudInterviniente,
																		    BooleanUtils.getBoolean(
																		        StringUtils.getString(
																		            lb_insertSolicitudInterviniente
																		        )
																		    )
																		);
																	}
																}

																boolean lb_realizarInsert;

																lb_realizarInsert = true;

																for(String ls_campo : lsa_campos)
																{
																	String[] lsa_campodestino;

																	lsa_campodestino = ls_campo.split("\\|");

																	if(
																	    CollectionUtils.isValidCollection(
																		        lsa_campodestino
																		    )
																	)
																	{
																		String ls_posicion0;

																		ls_posicion0 = lsa_campodestino[0];

																		if(ls_posicion0.equalsIgnoreCase("X"))
																		{
																			if(
																			    lsa_campodestino[3].equalsIgnoreCase(
																				        "N"
																				    )
																			)
																			{
																				double ld_valorIngresado;

																				ld_valorIngresado = NumericUtils
																						.getDouble(lsa_campodestino[2]);

																				if(ld_valorIngresado >= 0)
																					lmso_entidad.put(
																					    lsa_campodestino[1],
																					    BigDecimal.valueOf(
																					        ld_valorIngresado
																					    )
																					);
																				else
																				{
																					Object[] loa_args;

																					loa_args     = new String[1];

																					loa_args[0] = lsa_campodestino[1];

																					throw new B2BException(
																					    addErrorNP(
																					        ErrorKeys.ERROR_VALOR_CRITERIO_GENERICO_NO_VALIDO,
																					        loa_args
																					    )
																					);
																				}
																			}
																			else
																				lmso_entidad.put(
																				    lsa_campodestino[1],
																				    lsa_campodestino[2]
																				);
																		}
																		else
																		{
																			String ls_campoDestino0;
																			String ls_valorIngresado;

																			ls_campoDestino0      = StringUtils
																					.getStringNotNull(ls_posicion0);
																			ls_valorIngresado     = lmss_criterios.get(
																				    ls_campoDestino0
																				);

																			if(
																			    lmss_criterios.containsKey(
																				        ls_campoDestino0
																				    ) && (ls_valorIngresado != null)
																			)
																			{
																				if(
																				    lsa_campodestino[2].equalsIgnoreCase(
																					        "N"
																					    )
																				)
																				{
																					double ld_valorIngresado;

																					ld_valorIngresado = NumericUtils
																							.getDouble(
																							    ls_valorIngresado
																							);

																					if(ld_valorIngresado > 0)
																						lmso_entidad.put(
																						    lsa_campodestino[1],
																						    BigDecimal.valueOf(
																						        ld_valorIngresado
																						    )
																						);
																					else
																					{
																						Object[] loa_args;

																						loa_args     = new String[1];

																						loa_args[0] = lsa_campodestino[0];

																						throw new B2BException(
																						    addErrorNP(
																						        ErrorKeys.ERROR_VALOR_CRITERIO_GENERICO_NO_VALIDO,
																						        loa_args
																						    )
																						);
																					}
																				}
																				else
																					lmso_entidad.put(
																					    lsa_campodestino[1],
																					    ls_valorIngresado
																					);
																			}
																		}
																	}
																}

																if(
																    (ls_nombreTabla.contains(is_datosAntSistema)
																	    && !lmsb_validacionesInsercion.get(
																	        is_datosAntSistema
																	    ).booleanValue())
																	    || (ls_nombreTabla.contains(
																	        is_detalleAntSistema
																	    )
																	    && !lmsb_validacionesInsercion.get(
																	        is_detalleAntSistema
																	    ).booleanValue())
																	    || (ls_nombreTabla.contains(
																	        is_solicitudMatricula
																	    )
																	    && !lmsb_validacionesInsercion.get(
																	        is_solicitudMatricula
																	    ).booleanValue())
																	    || (ls_nombreTabla.contains(
																	        is_solicitudInterviniente
																	    )
																	    && !lmsb_validacionesInsercion.get(
																	        is_solicitudInterviniente
																	    ).booleanValue())
																)
																	lb_realizarInsert = false;

																if(lb_realizarInsert)
																	lut_DAO.customInsert(lmso_entidad);
															}
														}
													}

													li_tablaActual++;
												}
											}
										}
									}
								}
							}

							if(ls_parametrizacion.contains("SDB_ACC_SOLICITUD_MATRICULA"))
							{
								String    ls_idSolicitud;
								Solicitud ls_solicitudCreada;

								ls_idSolicitud         = ls_solicitud.getIdSolicitud();
								ls_solicitudCreada     = DaoCreator.getSolicitudDAO(ldm_manager).findById(
									    ls_idSolicitud
									);

								if(ls_solicitudCreada != null)
								{
									String ls_idProceso;

									ls_idProceso = ls_solicitudCreada.getIdProceso();

									if(
									    StringUtils.isValidString(ls_idProceso)
										    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_50)
									)
									{
										int li_contador;

										li_contador = DaoCreator.getAlertaTitularDAO(ldm_manager)
												                    .countAlertaMatriculaPersona(ls_solicitudCreada);

										if(li_contador > 0)
										{
											Object[] loa_messageArgs;

											loa_messageArgs        = new String[1];
											loa_messageArgs[0]     = StringUtils.getString(li_contador);

											throw new B2BException(
											    addErrorNP(
											        ErrorKeys.ERROR_YA_EXISTEN_MATRICULAS_ALERTAS_PERSONA,
											        loa_messageArgs
											    )
											);
										}
									}
								}
							}
						}
					}
				}

				liquidarSolicitud(
				    ls_solicitud, ategl_peticion, ltsgl_respuesta, lb_entidadExenta, ldm_manager, ls_userId, as_remoteIp
				);

				if(lb_solicitudCopiasSE)
				{
					String          ls_idSolicitud;
					Solicitud       ls_solicitudProc;
					SolicitudCopias lsc_parametros;

					ls_idSolicitud       = ls_solicitud.getIdSolicitud();
					ls_solicitudProc     = new Solicitud();
					lsc_parametros       = new SolicitudCopias();

					ls_solicitudProc.setIdSolicitud(ls_idSolicitud);

					ls_solicitudProc = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_solicitudProc);

					if(ls_solicitudProc != null)
					{
						String  ls_exenta;
						boolean lb_exenta;

						ls_exenta     = ls_solicitudProc.getEntidadExenta();
						lb_exenta     = StringUtils.isValidString(ls_exenta)
								&& ls_exenta.equalsIgnoreCase(EstadoCommon.S);

						if(!lb_exenta)
							DaoCreator.getProcedimientosDAO(ldm_manager)
								          .procLlaCrearEtapaTrg(
								    ls_solicitudProc, null, IdentificadoresCommon.COPIAS,
								    NumericUtils.getLongWrapper(EtapaCommon.VALIDACION_INFORMACION_EN_OWCC)
								);
					}

					{
						Turno lt_turno;

						lt_turno = new Turno();

						lt_turno.setIdSolicitud(ls_idSolicitud);
						lt_turno.setIdProceso(ls_solicitud.getIdProceso());

						lt_turno = DaoCreator.getTurnoDAO(ldm_manager).findByIdSolicitudProceso(lt_turno);

						if(lt_turno != null)
							lsc_parametros.setTurno(lt_turno.getIdTurno());
					}

					lsc_parametros.setNir(ls_solicitud.getNir());
					lsc_parametros.setIdSolicitud(ls_idSolicitud);
					lsc_parametros.setIdentificadorCopiasSE(ll_identificadorCopiasSE);
					lsc_parametros.setIdUsuarioModificacion(as_userId);
					lsc_parametros.setIpModificacion(as_remoteIp);

					DaoCreator.getSolicitudCopiasDAO(ldm_manager).updateSolicitudByCopiasSE(lsc_parametros);
				}
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_notificacionPagos.setRollbackOnly();
			clh_LOGGER.error("generarLiquidacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
			lb_error                  = true;
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_notificacionPagos.setRollbackOnly();
			clh_LOGGER.error("generarLiquidacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
			lb_error                  = true;
		}
		finally
		{
			ldm_manager.close();
			ldm_notificacionPagos.close();
		}

		if(lb_error)
		{
			TipoServicioGLO[] ltst_response;

			ltst_response        = new TipoServicioGLO[1];
			ltst_response[0]     = new TipoServicioGLO("", "", BigInteger.valueOf(0), BigDecimal.valueOf(0));

			ltsgl_respuesta.setNumeroReferencia("");
			ltsgl_respuesta.setNir("");
			ltsgl_respuesta.setValorTotalServicio(BigDecimal.valueOf(0));
			ltsgl_respuesta.setFechaLiquidacion(obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()));
			ltsgl_respuesta.setFechaVencimiento(obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()));
			ltsgl_respuesta.setServiciosLiquidados(ltst_response);
		}
		else
		{
			if(ltsgl_respuesta.getNumeroReferencia() == null)
				ltsgl_respuesta.setNumeroReferencia("");

			if(ltsgl_respuesta.getNir() == null)
				ltsgl_respuesta.setNir("");

			if(ltsgl_respuesta.getFechaLiquidacion() == null)
				ltsgl_respuesta.setFechaLiquidacion(obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()));

			if(ltsgl_respuesta.getFechaVencimiento() == null)
				ltsgl_respuesta.setFechaVencimiento(obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()));

			if(!CollectionUtils.isValidCollection(ltsgl_respuesta.getServiciosLiquidados()))
			{
				TipoServicioGLO[] ltst_response;

				ltst_response        = new TipoServicioGLO[1];
				ltst_response[0]     = new TipoServicioGLO("", "", BigInteger.valueOf(0), BigDecimal.valueOf(0));

				ltsgl_respuesta.setServiciosLiquidados(ltst_response);
			}
		}

		if(ltsgl_respuesta.getCodigoMensaje() == null)
		{
			ltsgl_respuesta.setCodigoMensaje(lbi_codigoMensaje);
			ltsgl_respuesta.setDescripcionMensaje(ls_descripcionMensaje);
		}

		return ltsgl_respuesta;
	}

	/**
	 * Método encargado de notificar el pago.
	 *
	 * @param atenp_peticion Objeto de tipo TipoEntradaNotificarPago que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida notificar pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaNotificarPago notificarPago(
	    TipoEntradaNotificarPago atenp_peticion, String as_userIdConstant, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_managerNPA;
		DAOManager ldm_managerBachue;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_managerBachue         = DaoManagerFactory.getDAOManager();
		ldm_managerNPA            = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			double        ld_valorTransaccion;
			LocalDateTime lldt_fechaBancaria;
			LocalDateTime lldt_fechaTransaccion;
			String        ls_codigoEntidadRecaudadora;
			String        ls_codigoPuntoRecaudo;
			String        ls_numeroReferencia;
			String        ls_tipoRecaudo;
			String        ls_codigoTransaccion;
			String        ls_userId;

			ls_userId                       = getSystemUser(as_userIdConstant, ldm_managerBachue);
			ld_valorTransaccion             = NumericUtils.getDouble(
				    atenp_peticion.getMontoTransaccion(), NumericUtils.DEFAULT_DOUBLE_VALUE
				);
			lldt_fechaBancaria              = obtenerLocalDateTime(atenp_peticion.getFechaBancaria());
			lldt_fechaTransaccion           = obtenerLocalDateTime(atenp_peticion.getFechaTransaccion());
			ls_codigoEntidadRecaudadora     = StringUtils.getStringNotNull(
				    atenp_peticion.getCodigoEntidadRecaudadora()
				);
			ls_codigoPuntoRecaudo           = StringUtils.getStringNotNull(
				    atenp_peticion.getCodigoPuntoRecaudoEntidad()
				);
			ls_numeroReferencia             = StringUtils.getStringNotNull(atenp_peticion.getNumeroReferenciaBachue());
			ls_tipoRecaudo                  = StringUtils.getStringNotNull(atenp_peticion.getCodigoTipoRecaudo());
			ls_codigoTransaccion            = StringUtils.getStringNotNull(
				    atenp_peticion.getCodigoTransaccionRecaudador()
				);

			validarPago(
			    ld_valorTransaccion, lldt_fechaBancaria, lldt_fechaTransaccion, ls_codigoEntidadRecaudadora,
			    ls_codigoPuntoRecaudo, ls_numeroReferencia, ls_tipoRecaudo, ls_codigoTransaccion, ls_userId, as_localIp,
			    as_remoteIp, true, ldm_managerNPA
			);
		}
		catch(B2BException lb2be_e)
		{
			ldm_managerNPA.setRollbackOnly();
			ldm_managerBachue.setRollbackOnly();
			clh_LOGGER.error("notificarPago", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_managerNPA.setRollbackOnly();
			ldm_managerBachue.setRollbackOnly();
			clh_LOGGER.error("notificarPago", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_managerBachue.close();
			ldm_managerNPA.close();
		}

		{
			TipoSalidaNotificarPago ltsnp_response;

			ltsnp_response = new TipoSalidaNotificarPago(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsnp_response;
		}
	}

	/**
	 * Método encargado de Obtener la clave del pdf de liquidacion generado en el gestor documental.
	 *
	 * @param ateocpl_peticion Objeto de tipo TipoEntradaObtenerClavePDFLiquidacion que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida obtener clave PDF liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    TipoEntradaObtenerClavePDFLiquidacion ateocpl_peticion, String as_userIdConstant, String as_localIp,
	    String                                as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_did;
		String     ls_docname;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();
		ls_did                    = "";
		ls_docname                = "";

		try
		{
			if(ateocpl_peticion != null)
			{
				String ls_numeroReferencia;

				ls_numeroReferencia = StringUtils.getStringNotNull(ateocpl_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					DatosLiquidacion ldl_dl;

					ldl_dl = DaoCreator.getDatosLiquidacionDAO(ldm_manager)
							               .findById(new DatosLiquidacion(ls_numeroReferencia));

					if(ldl_dl != null)
					{
						Date ld_fechaVencimiento;
						ld_fechaVencimiento = ldl_dl.getFechaVencimiento();

						if((ld_fechaVencimiento != null) && ld_fechaVencimiento.after(new Date()))
						{
							DocumentosSalida lds_ds;

							lds_ds = com.bachue.snr.prosnr01.dao.DaoCreator.getDocumentosSalidaDAO(ldm_manager)
									                                           .findById(
									    new DocumentosSalida(ldl_dl.getIdDocumentoSalida())
									);

							if(lds_ds != null)
							{
								String ls_OWCC;
								String ls_OWCCDocName;

								ls_OWCC            = lds_ds.getIdEcm();
								ls_OWCCDocName     = lds_ds.getIdNombreDocumento();

								if(StringUtils.isValidString(ls_OWCC) && StringUtils.isValidString(ls_OWCCDocName))
								{
									ls_did         = ls_OWCC;
									ls_docname     = ls_OWCCDocName;
								}
								else
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_CLAVE_PDF_NO_GENERADA));
							}
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_VENCIDO));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerClavePDFLiquidacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("obtenerClavePDFLiquidacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaObtenerClavePDFLiquidacion ltsocpl_response;

			ltsocpl_response = new TipoSalidaObtenerClavePDFLiquidacion(
				    ls_did, ls_docname, lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltsocpl_response;
		}
	}

	/**
	 * Método encargado de registrar una anulacion para un numero de referencia dado.
	 *
	 * @param atera_peticion Objeto de tipo TipoEntradaRegistrarAnulacion que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida registrar anulacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarAnulacion registrarAnulacion(
	    TipoEntradaRegistrarAnulacion atera_peticion, String as_userIdConstant, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_managerNPA;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_managerNPA            = DaoManagerFactory.getDAOManagerNPA();
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(atera_peticion != null)
			{
				String ls_numeroreferencia;
				String ls_userId;

				ls_userId               = getSystemUser(as_userIdConstant, ldm_manager);
				ls_numeroreferencia     = StringUtils.getStringNotNull(atera_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroreferencia))
				{
					NotificacionPago    lnp_np;
					NotificacionPagoDAO lnpd_DAO;

					lnpd_DAO     = DaoCreator.getNotificacionPagoDAO(ldm_managerNPA);
					lnp_np       = lnpd_DAO.findByNumeroReferencia(ls_numeroreferencia);

					if(lnp_np != null)
					{
						String ls_estado;

						ls_estado = lnp_np.getEstado();

						if(StringUtils.isValidString(ls_estado))
						{
							if(ls_estado.equalsIgnoreCase(EstadoCommon.REFERENCIA_DISPONIBLE))
							{
								lnp_np.setEstado(EstadoCommon.REFERENCIA_ANULADA);
								lnp_np.setIdUsuarioModificacion(ls_userId);
								lnp_np.setIpModificacion(as_remoteIp);

								lnpd_DAO.update(lnp_np);
							}
							else
								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_NO_ES_POSIBLE_CAMBIAR_ESTADO_LIQUIDACION)
								);
						}
						else
							throw new B2BException(
							    addErrorNP(ErrorKeys.ERROR_NO_ES_POSIBLE_CAMBIAR_ESTADO_LIQUIDACION)
							);
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerNPA.setRollbackOnly();
			clh_LOGGER.error("registrarAnulacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerNPA.setRollbackOnly();
			clh_LOGGER.error("registrarAnulacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
			ldm_managerNPA.close();
		}

		{
			TipoSalidaRegistrarAnulacion ltsra_response;

			ltsra_response = new TipoSalidaRegistrarAnulacion(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsra_response;
		}
	}

	/**
	 * Método encargado de registrar una liquidacion desde Bachue.
	 *
	 * @param aterl_peticion Objeto de tipo TipoEntradaRegistrarLiquidacion que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida registrar liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    TipoEntradaRegistrarLiquidacion aterl_peticion, String as_userIdConstant, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_managerBachue;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = "OK";
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManagerNPA();
		ldm_managerBachue         = DaoManagerFactory.getDAOManager();

		try
		{
			if(aterl_peticion != null)
			{
				String              ls_numeroReferencia;
				NotificacionPagoDAO lnpd_DAO;
				String              ls_userId;

				ls_userId               = getSystemUser(as_userIdConstant, ldm_managerBachue);
				lnpd_DAO                = DaoCreator.getNotificacionPagoDAO(ldm_manager);
				ls_numeroReferencia     = StringUtils.getStringNotNull(aterl_peticion.getNumeroReferencia());

				if(!StringUtils.isValidString(aterl_peticion.getNir()))
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NIR_OBLIGATORIO));

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					NotificacionPago lnp_np;

					lnp_np = lnpd_DAO.findByNumeroReferencia(ls_numeroReferencia);

					if(lnp_np == null)
					{
						lnp_np = new NotificacionPago(ls_numeroReferencia);

						{
							Date ld_fechaLiquidacion;
							Date ld_fechaVencimiento;

							ld_fechaLiquidacion     = obtenerDateDesdeCalendar(aterl_peticion.getFechaLiquidacion());
							ld_fechaVencimiento     = obtenerDateDesdeCalendar(aterl_peticion.getFechaVencimiento());

							if((ld_fechaLiquidacion != null) && ld_fechaLiquidacion.before(new Date()))
								lnp_np.setFechaLiquidacion(ld_fechaLiquidacion);
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_FECHA_LIQUIDACION_NO_PERMITIDA));

							if((ld_fechaVencimiento != null) && ld_fechaVencimiento.after(ld_fechaLiquidacion))
								lnp_np.setFechaVencimiento(ld_fechaVencimiento);
							else
								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_FECHA_LIQUIDACION_POST_FECHA_VENCIMIENTO)
								);
						}

						{
							Liquidacion ll_liquidacion;
							double      ld_valorTotalServicio;
							ll_liquidacion     = DaoCreator.getAccLiquidacionDAO(ldm_managerBachue)
									                           .findByNumeroReferencia(ls_numeroReferencia);

							ld_valorTotalServicio = NumericUtils.DEFAULT_DOUBLE_VALUE;

							if(ll_liquidacion != null)
							{
								String ls_idTipoMayorValor;
								ls_idTipoMayorValor = ll_liquidacion.getIdTipoMayorValor();

								if(
								    StringUtils.isValidString(ls_idTipoMayorValor)
									    && (NumericUtils.getDouble(
									        ll_liquidacion.getTotalMayorValor(), NumericUtils.DEFAULT_DOUBLE_VALUE
									    ) > NumericUtils.DEFAULT_DOUBLE_VALUE)
								)
									ld_valorTotalServicio = NumericUtils.getDouble(
										    ll_liquidacion.getTotalMayorValor(), NumericUtils.DEFAULT_DOUBLE_VALUE
										);
								else
									ld_valorTotalServicio = NumericUtils.getDouble(
										    aterl_peticion.getValorTotalServicio(), NumericUtils.DEFAULT_DOUBLE_VALUE
										);
							}
							else
								ld_valorTotalServicio = NumericUtils.getDouble(
									    aterl_peticion.getValorTotalServicio(), NumericUtils.DEFAULT_DOUBLE_VALUE
									);

							if(!(ld_valorTotalServicio < NumericUtils.DEFAULT_DOUBLE_VALUE))
							{
								lnp_np.setMontoTransaccion(ld_valorTotalServicio);
								lnp_np.setEstado(
								    (ld_valorTotalServicio > NumericUtils.DEFAULT_DOUBLE_VALUE)
								    ? EstadoCommon.REFERENCIA_DISPONIBLE : EstadoCommon.REFERENCIA_PAGADA
								);
							}
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_VALOR_SERVICIO_MAYOR_CERO));
						}

						{
							String ls_codigoCanalOrigen;

							ls_codigoCanalOrigen = aterl_peticion.getCanalOrigenServicio();

							if(!StringUtils.isValidString(ls_codigoCanalOrigen))
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_INDICA_CANAL_ORIGEN));

							{
								SucursalCanalOrigenServicio lscos_sucursalCanalOrigenServicio;

								lscos_sucursalCanalOrigenServicio = DaoCreator.getSucursalCanalOrigenServicioDAO(
									    ldm_manager
									).findByCodigo(ls_codigoCanalOrigen);

								if(lscos_sucursalCanalOrigenServicio == null)
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_SUCURSAL_CANAL_ORIGEN_NO_PARAMETRIZADO)
									);

								lnp_np.setIdSucursalCanalOrigenServicio(
								    lscos_sucursalCanalOrigenServicio.getIdSucursalCanalOrigenServicio()
								);
							}
						}

						lnp_np.setIdUsuarioCreacion(ls_userId);
						lnp_np.setIpCreacion(as_remoteIp);

						lnpd_DAO.insert(lnp_np);
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_EXISTENTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerBachue.setRollbackOnly();
			clh_LOGGER.error("registrarLiquidacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerBachue.setRollbackOnly();
			clh_LOGGER.error("registrarLiquidacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
			ldm_managerBachue.close();
		}

		{
			TipoSalidaRegistrarLiquidacion ltsrl_response;

			ltsrl_response = new TipoSalidaRegistrarLiquidacion(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsrl_response;
		}
	}

	/**
	 * Método encargado de registrar el pago de una liquidacion.
	 *
	 * @param aterp_peticion Objeto de tipo TipoEntradaRegistrarPago que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida registrar pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarPago registrarPago(
	    TipoEntradaRegistrarPago aterp_peticion, String as_userIdConstant, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(aterp_peticion != null)
			{
				double        ld_valorTransaccion;
				LocalDateTime lldt_fechaBancaria;
				LocalDateTime lldt_fechaTransaccion;
				String        ls_codigoEntidadRecaudadora;
				String        ls_codigoPuntoRecaudo;
				String        ls_numeroReferencia;
				String        ls_tipoRecaudo;
				String        ls_codigoTransaccion;
				String        ls_userId;

				ls_userId                       = getSystemUser(as_userIdConstant, ldm_manager);
				ld_valorTransaccion             = NumericUtils.getDouble(
					    aterp_peticion.getMontoTransaccion(), NumericUtils.DEFAULT_DOUBLE_VALUE
					);
				lldt_fechaBancaria              = obtenerLocalDateTime(aterp_peticion.getFechaBancaria());
				lldt_fechaTransaccion           = obtenerLocalDateTime(aterp_peticion.getFechaTransaccion());
				ls_codigoEntidadRecaudadora     = StringUtils.getStringNotNull(
					    aterp_peticion.getCodigoEntidadRecaudadora()
					);
				ls_codigoPuntoRecaudo           = StringUtils.getStringNotNull(
					    aterp_peticion.getCodigoPuntoRecaudoEntidad()
					);
				ls_numeroReferencia             = StringUtils.getStringNotNull(
					    aterp_peticion.getNumeroReferenciaBachue()
					);
				ls_tipoRecaudo                  = StringUtils.getStringNotNull(aterp_peticion.getCodigoTipoRecaudo());
				ls_codigoTransaccion            = StringUtils.getStringNotNull(
					    aterp_peticion.getCodigoTransaccionRecaudador()
					);

				validarPago(
				    ld_valorTransaccion, lldt_fechaBancaria, lldt_fechaTransaccion, ls_codigoEntidadRecaudadora,
				    ls_codigoPuntoRecaudo, ls_numeroReferencia, ls_tipoRecaudo, ls_codigoTransaccion, ls_userId,
				    as_localIp, as_remoteIp, false, ldm_manager
				);
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarPago", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("registrarPago", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaRegistrarPago ltsrp_response;

			ltsrp_response = new TipoSalidaRegistrarPago(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsrp_response;
		}
	}

	/**
	 * Método encargado de registrar un recibo de caja generado.
	 *
	 * @param aterrc_peticion Objeto de tipo TipoEntradaRegistrarReciboCaja que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida registrar recibo caja
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    TipoEntradaRegistrarReciboCaja aterrc_peticion, String as_userIdConstant, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		DAOManager ldm_managerNPA;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();
		ldm_managerNPA            = DaoManagerFactory.getDAOManagerNPA();

		try
		{
			if(aterrc_peticion != null)
			{
				String ls_numeroReferencia;
				String ls_userId;

				ls_userId               = getSystemUser(as_userIdConstant, ldm_manager);
				ls_numeroReferencia     = StringUtils.getStringNotNull(aterrc_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					NotificacionPago    lnp_np;
					NotificacionPagoDAO lnpd_dAO;

					lnpd_dAO     = DaoCreator.getNotificacionPagoDAO(ldm_managerNPA);
					lnp_np       = lnpd_dAO.findByNumeroReferencia(ls_numeroReferencia);

					if(lnp_np != null)
					{
						Date ld_recibo;

						ld_recibo = obtenerDateDesdeCalendar(aterrc_peticion.getFechaHoraRecibo());

						if((ld_recibo != null) && ld_recibo.before(new Date()))
						{
							lnp_np.setEstado(EstadoCommon.RECIBO_DE_CAJA_GENERADO);
							lnp_np.setFechaReciboCaja(ld_recibo);
							lnp_np.setIdUsuarioModificacion(ls_userId);
							lnp_np.setIpModificacion(as_remoteIp);

							lnpd_dAO.update(lnp_np);
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_FECHA_RECIBO_CAJA_NO_VALIDA));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerNPA.setRollbackOnly();
			clh_LOGGER.error("registrarReciboCaja", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			ldm_managerNPA.setRollbackOnly();
			clh_LOGGER.error("registrarReciboCaja", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
			ldm_managerNPA.close();
		}

		{
			TipoSalidaRegistrarReciboCaja ltsrrc_response;

			ltsrrc_response = new TipoSalidaRegistrarReciboCaja(lbi_codigoMensaje, ls_descripcionMensaje);

			return ltsrrc_response;
		}
	}

	/**
	 * Método encargado de actualizar los datos del solicitante.
	 *
	 * @param atevads_peticion Objeto de tipo TipoEntradaValidarActualizacionDatosSolicitante que provienen de una peticion al servicio
	 * @param as_userIdConstant Objeto de tipo String que contiene el usuario que realiza la peticion
	 * @param as_localIp Objeto de tipo String que contiene IP local que realiza la peticion
	 * @param as_remoteIp Objeto de tipo String que contiene IP remota que realiza la peticion
	 * @return el valor de tipo salida validar actualizacion datos solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    TipoEntradaValidarActualizacionDatosSolicitante atevads_peticion, String as_userIdConstant, String as_localIp,
	    String                                          as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_descripcionMensaje;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = null;
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager               = DaoManagerFactory.getDAOManager();

		try
		{
			if(atevads_peticion != null)
			{
				String ls_numeroReferencia;

				ls_numeroReferencia = StringUtils.getStringNotNull(atevads_peticion.getNumeroReferencia());

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					Liquidacion    ll_liquidacion;
					LiquidacionDAO lld_DAO;

					lld_DAO            = DaoCreator.getAccLiquidacionDAO(ldm_manager);
					ll_liquidacion     = lld_DAO.findByNumeroReferencia(ls_numeroReferencia);

					if(ll_liquidacion != null)
					{
						{
							RegistroPago    lrp_rp;
							RegistroPagoDAO lrpd_DAO;

							lrpd_DAO     = DaoCreator.getRegistroPagoDAO(ldm_manager);
							lrp_rp       = lrpd_DAO.findByNumeroReferencia(ll_liquidacion.getNumeroReferencia());

							if(lrp_rp != null)
							{
								String ls_estado;

								ls_estado = StringUtils.getStringNotNull(lrp_rp.getEstadoPago());

								if(!ls_estado.equalsIgnoreCase(EstadoCommon.DISPONIBLE))
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_REFERENCIA_INGRESADA_TIENE_PAGO_REALIZADO)
									);
							}
						}

						Solicitud ls_solicitud;

						ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(
							    ll_liquidacion.getIdSolicitud()
							);

						if(ls_solicitud != null)
						{
							Persona lp_persona;

							lp_persona = DaoCreator.getPersonaDAO(ldm_manager).findById(ls_solicitud.getIdPersona());

							if(lp_persona != null)
							{
								String ls_tipoPersona;
								String ls_tipoDocSolicitante;
								String ls_numeroDocSolicitante;

								ls_tipoPersona              = lp_persona.getIdTipoPersona();
								ls_tipoDocSolicitante       = lp_persona.getIdDocumentoTipo();
								ls_numeroDocSolicitante     = lp_persona.getNumeroDocumento();

								if(
								    StringUtils.isValidString(ls_tipoPersona)
									    && StringUtils.isValidString(ls_tipoDocSolicitante)
									    && StringUtils.isValidString(ls_numeroDocSolicitante)
								)
								{
									if(
									    ls_tipoPersona.equalsIgnoreCase(TipoPersonaCommon.NATURAL)
										    || ls_tipoPersona.equalsIgnoreCase(TipoPersonaCommon.INDETERMINADO)
									)
									{
										if(
										    !(StringUtils.isValidString(lp_persona.getPrimerNombre())
											    && StringUtils.isValidString(lp_persona.getPrimerApellido()))
										)
											throw new B2BException(
											    addErrorNP(ErrorKeys.ERROR_USUARIO_DEBE_ACTUALIZAR_DATOS_PERSONALES)
											);
									}
									else
									{
										if(!StringUtils.isValidString(lp_persona.getRazonSocial()))
											throw new B2BException(
											    addErrorNP(ErrorKeys.ERROR_USUARIO_DEBE_ACTUALIZAR_DATOS_PERSONALES)
											);
									}
								}
								else
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_TIPO_Y_NUMERO_DOCUMENTO_NO_DILIGENCIADOS)
									);
							}
							else
								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_NO_SE_ENCONTRO_PERSONA_ASOCIADA_A_NUMERO_REFERENCIA)
								);
						}
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("validarActualizacionDatosSolicitante", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(lb2be_e.getMessageKey());

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("validarActualizacionDatosSolicitante", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		{
			TipoSalidaValidarActualizacionDatosSolicitante ltsrp_response;

			ltsrp_response = new TipoSalidaValidarActualizacionDatosSolicitante(
				    lbi_codigoMensaje, ls_descripcionMensaje
				);

			return ltsrp_response;
		}
	}

	/**
	 * Crea una solicitud.
	 *
	 * @param adcs_datosSolicitud Objeto contenedor de los datos a utilizar en la creación de la solicitud
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @param adm_manager Conexión a la base de datos
	 * @return Solicitud resultante de la creación
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	private Solicitud crearSolicitud(
	    DatosCreacionSolicitud adcs_datosSolicitud, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		Solicitud ls_solicitud;

		ls_solicitud = null;

		if(adcs_datosSolicitud != null)
		{
			SolicitudDAO lsd_DAO;
			String       ls_tipoServicio;
			String       ls_tipoSubservicio;

			ls_solicitud           = new Solicitud();
			lsd_DAO                = DaoCreator.getSolicitudDAO(adm_manager);
			ls_tipoServicio        = StringUtils.getStringNotNull(adcs_datosSolicitud.getTipoServicio());
			ls_tipoSubservicio     = StringUtils.getStringNotNull(adcs_datosSolicitud.getTipoSubservicio());

			ls_solicitud.setIdSolicitud(String.valueOf(lsd_DAO.findSecuence()));
			ls_solicitud.setIdProceso(ls_tipoServicio);
			ls_solicitud.setIdSubproceso(ls_tipoSubservicio);
			ls_solicitud.setFechaSolicitud(Calendar.getInstance().getTime());
			ls_solicitud.setEntidadExenta(adcs_datosSolicitud.isEntidadExenta() ? EstadoCommon.SI : EstadoCommon.NO);
			ls_solicitud.setDerechoPeticion(EstadoCommon.NO);
			ls_solicitud.setIdTipoRecepcion(ProcedenciaCommon.SEDE_ELECTRONICA);
			ls_solicitud.setIdProcedencia(ProcedenciaCommon.SEDE_ELECTRONICA);
			ls_solicitud.setIdCanalOrigen(adcs_datosSolicitud.getIdCanalOrigen());
			ls_solicitud.setIdSucursalCanalOrigen(adcs_datosSolicitud.getIdSucursalOrigen());
			ls_solicitud.setIdCuentaCupo(adcs_datosSolicitud.getIdCuentaCupo());
			ls_solicitud.setValorRecarga(adcs_datosSolicitud.getValorRecarga());

			{
				String ls_caracter;

				ls_caracter = DaoCreator.getConstantesDAO(adm_manager).findString(
					    ConstanteCommon.TIPO_RECEPCION_KIOSKO
					);

				if(
				    StringUtils.isValidString(ls_caracter)
					    && ls_caracter.equalsIgnoreCase(adcs_datosSolicitud.getIdCanalOrigen())
				)
				{
					ls_solicitud.setIdTipoRecepcion(ProcedenciaCommon.KIOSKO);
					ls_solicitud.setIdProcedencia(ProcedenciaCommon.KIOSKO);
				}
			}

			ls_solicitud.setFechaCreacion(Calendar.getInstance().getTime());
			ls_solicitud.setParticipaInterviniente(EstadoCommon.NO);
			ls_solicitud.setFechaUltimoEstado(Calendar.getInstance().getTime());
			ls_solicitud.setIdAutorizacionNotificacion("1");
			ls_solicitud.setIdAutorizacionComunicacion("1");

			{
				String ls_idPersona;

				ls_idPersona = adcs_datosSolicitud.getIdPersona();

				ls_solicitud.setIdPersona(ls_idPersona);

				if(!ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_60))
				{
					PersonaCorreoElectronicoDAO lpced_personaCorreoElectronicoDAO;
					String                      ls_correoElectronico;
					String                      ls_idCorreo;

					lpced_personaCorreoElectronicoDAO     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
					ls_correoElectronico                  = adcs_datosSolicitud.getCorreoElectronico();
					ls_idCorreo                           = null;

					{    //TODO revisar validaciones de correo puesto que ya se realiza esta validacion en el metodo validarPersona

						PersonaCorreoElectronico lpce_correo;

						lpce_correo = lpced_personaCorreoElectronicoDAO.findById(ls_idPersona, ls_correoElectronico);

						if(lpce_correo != null)
							ls_idCorreo = lpce_correo.getIdCorreoElectronico();
						else
						{
							lpce_correo = new PersonaCorreoElectronico();

							lpce_correo.setIdPersona(ls_idPersona);
							lpce_correo.setCorreoElectronico(ls_correoElectronico);
							lpce_correo.setFechaDesde(new Date());
							lpce_correo.setIdUsuarioCreacion(as_userId);
							lpce_correo.setIpCreacion(as_remoteIp);

							long ll_idCorreo;

							ll_idCorreo     = lpced_personaCorreoElectronicoDAO.insertOrUpdate(lpce_correo, true);

							ls_idCorreo = StringUtils.getString(ll_idCorreo);
						}
					}

					ls_solicitud.setIdCorreoElectronico(ls_idCorreo);
					ls_solicitud.setIdCorreoElectronicoComunicacion(ls_idCorreo);
				}
			}

			if(
			    ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_50)
				    && ls_tipoSubservicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
			)
				ls_solicitud.setIdTelefonoComunicacion(adcs_datosSolicitud.getTelefono());

			if(
			    ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_5)
				    && ls_tipoSubservicio.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
			)
				ls_solicitud.setIdentificadorCopiasSE(adcs_datosSolicitud.getIdentificadorCopiasSE());

			ls_solicitud.setNir(crearNir(as_userId, as_remoteIp, adm_manager));
			ls_solicitud.setIdUsuarioCreacion(as_userId);
			ls_solicitud.setIpCreacion(as_remoteIp);
			ls_solicitud.setDigitalizada(
			    (!(ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_50)
				    && ls_tipoSubservicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1))) ? EstadoCommon.SI
			                                                                             : EstadoCommon.NO
			);
			ls_solicitud.setVersionDocumento(Long.valueOf("1"));
			ls_solicitud.setTipoRegistroCertificado("I");

			lsd_DAO.insertOrUpdate(ls_solicitud, true);
		}

		return ls_solicitud;
	}

	/**
	 * Metodo encargado de extraer un campo de un mapa mediante el String (X|).
	 *
	 * @param as_dato Objeto de tipo String que contiene el dato a extraer.
	 * @param amss_tipoCriterio Objeto de tipo Map<String, String> que contiene un arreglo de campos almacenados.
	 * @return Objeto de tipo String que contiene el campo obtenido despues de su extracción.
	 */
	private String datoCampo(String as_dato, Map<String, String> amss_tipoCriterio)
	{
		String ls_retorno;

		ls_retorno = null;

		if(StringUtils.isValidString(as_dato))
		{
			if(as_dato.substring(0, 2).equalsIgnoreCase("X|"))
				ls_retorno = as_dato.substring(2);
			else
				ls_retorno = amss_tipoCriterio.get(as_dato);
		}

		return ls_retorno;
	}

	/**
	 * Liquidar solicitud.
	 *
	 * @param as_solicitud de as solicitud
	 * @param ategl_peticion de ategl peticion
	 * @param atsgl_respuesta de atsgl respuesta
	 * @param ab_entidadExenta de ab entidad exenta
	 * @param adm_manager de adm manager
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void liquidarSolicitud(
	    Solicitud as_solicitud, TipoEntradaGenerarLiquidacion ategl_peticion,
	    TipoSalidaGenerarLiquidacion atsgl_respuesta, boolean ab_entidadExenta, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		try
		{
			boolean lb_liquidar;
			String  ls_numeroReferencia;

			lb_liquidar             = true;
			ls_numeroReferencia     = null;

			{
				Solicitud ls_temp;

				ls_temp = DaoCreator.getSolicitudDAO(adm_manager).findById(as_solicitud);

				if(ls_temp != null)
				{
					Long   al_identificadorCopiasSE;
					String ls_idProceso;
					String ls_idSubproceso;

					al_identificadorCopiasSE     = ls_temp.getIdentificadorCopiasSE();
					ls_idProceso                 = ls_temp.getIdProceso();
					ls_idSubproceso              = ls_temp.getIdSubproceso();

					if(
					    NumericUtils.isValidLong(al_identificadorCopiasSE)
						    && (StringUtils.isValidString(ls_idProceso)
						    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_5))
						    && (StringUtils.isValidString(ls_idSubproceso)
						    && ls_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3))
					)
						lb_liquidar = DaoCreator.getSolicitudCopiasDAO(adm_manager)
								                    .validarNumeroFolios(al_identificadorCopiasSE);
				}
			}

			if(lb_liquidar)
			{
				Liquidacion al_liquidacion;

				al_liquidacion = new Liquidacion();

				al_liquidacion.setSolicitud(as_solicitud);
				al_liquidacion.setIdSolicitud(as_solicitud.getIdSolicitud());
				al_liquidacion.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
				al_liquidacion.setIdUsuarioCreacion(as_userId);
				al_liquidacion.setIpCreacion(as_remoteIp);

				al_liquidacion = DaoCreator.getProcedimientosDAO(adm_manager).procLiquidacion(al_liquidacion);

				if(al_liquidacion != null)
				{
					String ls_retorno;

					ls_retorno = al_liquidacion.getCodigoRespuesta();

					if(StringUtils.getStringNotNull(ls_retorno).equalsIgnoreCase("0"))
					{
						String ls_indCondicion;
						ls_indCondicion = StringUtils.getStringNotNull(IdentificadoresCommon.LIQUIDAR);

						if(ls_indCondicion.equalsIgnoreCase(IdentificadoresCommon.LIQUIDAR))
						{
							Registro lr_parametros;

							lr_parametros = new Registro();

							lr_parametros.setIdUsuarioCreacion(as_userId);
							lr_parametros.setSolicitud(as_solicitud);
							lr_parametros.setIdCondicion(ls_indCondicion);
							lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);

							lr_parametros = getRegistroBusiness()
									                .generarReciboLiquidacion(
									    lr_parametros, true, as_userId, null, null, adm_manager, true
									);

							if(lr_parametros != null)
								ls_numeroReferencia = lr_parametros.getTipoRecibo();
						}
					}
					else
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[2];
						loa_messageArgs[0]     = ls_retorno;
						loa_messageArgs[1]     = al_liquidacion.getRespuestaLiquidacion();

						throw new B2BException(addErrorNP(ErrorKeys.ERROR_PROCEDIMIENTO_LIQUIDACION, loa_messageArgs));
					}
				}
			}

			{
				TurnoHistoria lth_param;

				lth_param = new TurnoHistoria();

				lth_param.setIdSolicitud(as_solicitud.getIdSolicitud());
				lth_param.setIdUsuarioModificacion(as_userId);
				lth_param.setIpModificacion(as_remoteIp);

				DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_param);
			}

			Calendar lcln_ultimo_dia = Calendar.getInstance();
			lcln_ultimo_dia.set(Calendar.MONTH, Calendar.DECEMBER);
			lcln_ultimo_dia.set(Calendar.DAY_OF_MONTH, 31);
			lcln_ultimo_dia.set(Calendar.HOUR_OF_DAY, 23);
			lcln_ultimo_dia.set(Calendar.MINUTE, 59);
			lcln_ultimo_dia.set(Calendar.SECOND, 59);
			lcln_ultimo_dia.set(Calendar.MILLISECOND, 999);

			atsgl_respuesta.setFechaLiquidacion(
			    lb_liquidar ? obtenerCalendarDesdeDate(as_solicitud.getFechaSolicitud()) : null
			);
			atsgl_respuesta.setFechaVencimiento(
			    lb_liquidar ? obtenerCalendarDesdeDate(lcln_ultimo_dia.getTime()) : null
			);
			atsgl_respuesta.setNir(as_solicitud.getNir());
			atsgl_respuesta.setNumeroReferencia(ls_numeroReferencia);

			/* INSERTAR EN DATOS LIQUIDACION */
			if(lb_liquidar)
			{
				DatosLiquidacionDAO ldld_DAO;

				ldld_DAO = DaoCreator.getDatosLiquidacionDAO(adm_manager);

				if(StringUtils.isValidString(ls_numeroReferencia))
				{
					DatosLiquidacion ldl_dl;

					ldl_dl = ldld_DAO.findById(new DatosLiquidacion(ls_numeroReferencia));

					if(ldl_dl == null)
					{
						String    ls_nir;
						Solicitud ls_solicitud;

						ls_nir = as_solicitud.getNir();

						if(!StringUtils.isValidString(ls_nir))
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NIR_NO_VALIDO));

						as_solicitud.setNir(ls_nir);

						ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findByNir(as_solicitud);

						if(ls_solicitud != null)
						{
							String ls_idSolicitud;
							String ls_idPersona;

							ldl_dl     = new DatosLiquidacion(ls_numeroReferencia);

							ls_idSolicitud     = ls_solicitud.getIdSolicitud();
							ls_idPersona       = ls_solicitud.getIdPersona();

							{
								Persona lp_persona;

								lp_persona = new Persona();

								lp_persona.setIdPersona(ls_idPersona);

								lp_persona = DaoCreator.getPersonaDAO(adm_manager).findById(lp_persona);

								if(lp_persona != null)
								{
									ldl_dl.setIdTipoPersona(lp_persona.getIdTipoPersona());
									ldl_dl.setIdDocumentoTipo(lp_persona.getIdDocumentoTipo());
									ldl_dl.setNumeroDocumento(lp_persona.getNumeroDocumento());
									ldl_dl.setPrimerNombre(lp_persona.getPrimerNombre());
									ldl_dl.setSegundoNombre(lp_persona.getSegundoNombre());
									ldl_dl.setPrimerApellido(lp_persona.getPrimerApellido());
									ldl_dl.setSegundoApellido(lp_persona.getSegundoApellido());
									ldl_dl.setRazonSocial(lp_persona.getRazonSocial());
								}
								else
								{
									Object[] loa_messageArgs = new String[1];

									loa_messageArgs[0] = ls_nir;

									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_DATOS_SOLICITANTE_NO_EXISTENTES, loa_messageArgs)
									);
								}
							}

							ldl_dl.setIdSolicitud(ls_idSolicitud);
							ldl_dl.setIdTipoCanalOrigen(as_solicitud.getIdCanalOrigen());
							ldl_dl.setCodigoSucursalCanal(
							    NumericUtils.getLongWrapper(as_solicitud.getIdSucursalCanalOrigen())
							);

							{
								DocumentosSalida lds_documentoSalida;

								lds_documentoSalida = new DocumentosSalida();

								lds_documentoSalida.setIdSolicitud(ls_idSolicitud);
								lds_documentoSalida.setTipo(TipoArchivoCommon.RECIBO_LIQUIDACION);
								lds_documentoSalida.setEstado(EstadoCommon.ACTIVO);

								lds_documentoSalida = DaoCreator.getDocumentosSalidaDAO(adm_manager)
										                            .findDocument(lds_documentoSalida);

								if(lds_documentoSalida != null)
									ldl_dl.setIdDocumentoSalida(lds_documentoSalida.getIdDocumentoSalida());
								else
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_NO_EXISTE));
							}

							{
								Date ld_fechaLiquidacion;
								Date ld_fechaVencimiento;

								ld_fechaLiquidacion     = ls_solicitud.getFechaSolicitud();
								ld_fechaVencimiento     = lcln_ultimo_dia.getTime();

								if((ld_fechaLiquidacion != null) && ld_fechaLiquidacion.before(new Date()))
									ldl_dl.setFechaLiquidacion(ld_fechaLiquidacion);
								else
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_FECHA_LIQUIDACION_NO_PERMITIDA));

								if((ld_fechaVencimiento != null) && ld_fechaVencimiento.after(ld_fechaLiquidacion))
									ldl_dl.setFechaVencimiento(ld_fechaVencimiento);
								else
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_FECHA_LIQUIDACION_POST_FECHA_VENCIMIENTO)
									);
							}

							{
								double ld_valorTotalServicio;

								ld_valorTotalServicio = NumericUtils.getDouble(
									    atsgl_respuesta.getValorTotalServicio(), NumericUtils.DEFAULT_DOUBLE_VALUE
									);

								if((ld_valorTotalServicio > NumericUtils.DEFAULT_DOUBLE_VALUE) || ab_entidadExenta)
									ldl_dl.setValorTotalServicio(ld_valorTotalServicio);
								else
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_NIR_NO_EXISTE));
							}

							ldl_dl.setIdUsuarioCreacion(as_userId);
							ldl_dl.setIpCreacion(as_remoteIp);

							ldld_DAO.insertarDatosLiquidacion(ldl_dl);
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NIR_NO_EXISTE));
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_EXISTENTE));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
			}

			/* INSERTAR TIPO SERVICIOS */
			TipoServicioGLI[] ltsglia_serviciosLiquidados;

			ltsglia_serviciosLiquidados = ategl_peticion.getServicios();

			if((ltsglia_serviciosLiquidados != null) && lb_liquidar)
			{
				TipoServicioDAO ltsr_TSDAO;
				TipoCriterioDAO ltcr_TSDAO;

				ltcr_TSDAO     = com.bachue.snr.prosnr01.dao.DaoCreator.getTipoCriterioDAO(adm_manager);
				ltsr_TSDAO     = com.bachue.snr.prosnr01.dao.DaoCreator.getTipoServicioDAO(adm_manager);

				for(TipoServicioGLI lts_actual : ltsglia_serviciosLiquidados)
				{
					if(lts_actual != null)
					{
						com.bachue.snr.prosnr04.model.npa.TipoServicio ltsr_insertar;

						ltsr_insertar = new com.bachue.snr.prosnr04.model.npa.TipoServicio();
						ltsr_insertar.setIdNumeroReferencia(atsgl_respuesta.getNumeroReferencia());
						ltsr_insertar.setIdProceso(lts_actual.getTipoServicio());
						ltsr_insertar.setIdSubProceso(lts_actual.getSubtipoServicio());
						ltsr_insertar.setCantidadSolicitada(lts_actual.getCantidadSolicitada().longValue());
						ltsr_insertar.setValorUnitario(lts_actual.getValorServicio().doubleValue());
						ltsr_insertar.setValorTotal(lts_actual.getValorServicio().doubleValue());
						ltsr_insertar.setIdUsuarioCreacion(as_userId);
						ltsr_insertar.setIpCreacion(as_remoteIp);

						ltsr_insertar = ltsr_TSDAO.insertarTipoServicio(ltsr_insertar);

						if(CollectionUtils.isValidCollection(lts_actual.getCriterios()))
						{
							for(TipoCriterioGLI ltc_actual : lts_actual.getCriterios())
							{
								if(ltc_actual != null)
								{
									com.bachue.snr.prosnr04.model.npa.TipoCriterio ltcr_insertar;
									ltcr_insertar = new com.bachue.snr.prosnr04.model.npa.TipoCriterio();

									ltcr_insertar.setIdNumeroReferencia(atsgl_respuesta.getNumeroReferencia());
									ltcr_insertar.setIdTipoServicioNotificacionPago(
									    ltsr_insertar.getIdTipoServicioNotificacionPago()
									);
									ltcr_insertar.setIdProceso(ltsr_insertar.getIdProceso());
									ltcr_insertar.setIdSubProceso(ltsr_insertar.getIdSubProceso());
									ltcr_insertar.setCodigo(ltc_actual.getCodigo());
									ltcr_insertar.setValor(StringUtils.getStringNotEmpty(ltc_actual.getValor()));
									ltcr_insertar.setIdUsuarioCreacion(as_userId);
									ltcr_insertar.setIpCreacion(as_remoteIp);

									ltcr_TSDAO.insertarTipoCriterio(ltcr_insertar);
								}
							}
						}
					}
				}
			}

			if(!lb_liquidar)
			{
				atsgl_respuesta.setCodigoMensaje(BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200));
				atsgl_respuesta.setDescripcionMensaje(
				    getMessages().getString(MessagesKeys.NO_LIQUIDACION_COPIAS_SIN_NUMERO_FOLIOS)
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("liquidarSolicitud", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar una expresión determinada en la parametrizacion de notificacion de pagos.
	 *
	 * @param as_expresion Objeto de tipo String que contiene la expresion a validar.
	 * @param as_condicion Objeto de tipo String que contiene la condición a ser cumplida ('=' IGUALDAD, '<>' DIFERENCIA, '<' MENOR QUE).
	 * @param as_comparacion Objeto de tipo String que contiene el valor de comparación.
	 * @param amss_criterios Objeto de tipo Map<String, String> que contiene lista de valores donde la llave es la expresion a validar.
	 * @return Variable de tipo boolean que retorna TRUE cuando @ as_expresion
	 */
	private boolean validarExpresion(
	    String as_expresion, String as_condicion, String as_comparacion, Map<String, String> amss_criterios
	)
	{
		boolean lb_retorno;

		lb_retorno = true;

		if(as_condicion.equalsIgnoreCase("="))
		{
			if(as_comparacion.equalsIgnoreCase("NULL"))
			{
				String ls_valor;
				ls_valor = "";

				if(amss_criterios.get(as_expresion) != null)
					ls_valor = amss_criterios.get(as_expresion);

				lb_retorno = ls_valor.isEmpty();
			}
			else
			{
				String ls_valor;
				ls_valor = "";

				if(amss_criterios.get(as_expresion) != null)
					ls_valor = amss_criterios.get(as_expresion);

				lb_retorno = ls_valor.equalsIgnoreCase(as_comparacion);
			}
		}
		else if(as_condicion.equalsIgnoreCase("<>"))
		{
			if(as_comparacion.equalsIgnoreCase("NULL"))
			{
				String ls_valor;
				ls_valor = "";

				if(amss_criterios.get(as_expresion) != null)
					ls_valor = amss_criterios.get(as_expresion);

				lb_retorno = !ls_valor.isEmpty();
			}
		}
		else if(as_condicion.equalsIgnoreCase("<"))
		{
			String ls_valor;
			ls_valor = as_comparacion;

			if(amss_criterios.get(as_expresion) != null)
				ls_valor = amss_criterios.get(as_expresion);

			if(ls_valor.isEmpty())
				ls_valor = as_comparacion;

			lb_retorno = Long.parseLong(ls_valor) < Long.parseLong(as_comparacion);
		}
		else
			lb_retorno = false;

		return lb_retorno;
	}

	/**
	 * Método encargado de validar el pago recibido por servicios.
	 *
	 * @param ad_valorTransaccion Variable de tipo double que contiene el valor Transacción
	 * @param aldt_fechaBancaria Objeto de tipo Date que contiene la fecha Bancaria
	 * @param aldt_fechaTransaccion Objeto de tipo Date que contiene la fecha Transaccion
	 * @param as_codigoEntidadRecaudadora Objeto de tipo String que contiene el codigo Entidad Recaudadora
	 * @param as_codigoPuntoRecaudo Objeto de tipo String que contiene el codigo Punto Recaudo
	 * @param as_numeroReferencia Objeto de tipo String que contiene el numero referencia
	 * @param as_tipoRecaudo Objeto de tipo String que contiene el tipo de recaudo
	 * @param as_codigoTransaccion Objeto de tipo String que contiene el codigo de la transaccion
	 * @param as_userId Objeto de tipo String que contiene el usuario que realiza la accion
	 * @param as_localIp Objeto de tipo String que contiene la ip local
	 * @param as_remoteIp Objeto de tipo String que contiene la ip local remota
	 * @param ab_notificar de ab notificar
	 * @param adm_manager Objeto de tipo DAOManager que contiene la transaccion del preceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void validarPago(
	    double ad_valorTransaccion, LocalDateTime aldt_fechaBancaria, LocalDateTime aldt_fechaTransaccion,
	    String as_codigoEntidadRecaudadora, String as_codigoPuntoRecaudo, String as_numeroReferencia,
	    String as_tipoRecaudo, String as_codigoTransaccion, String as_userId, String as_localIp, String as_remoteIp,
	    boolean ab_notificar, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_codigoEntidadRecaudadora))
			{
				if(StringUtils.isValidString(as_codigoPuntoRecaudo))
				{
					if(StringUtils.isValidString(as_numeroReferencia))
					{
						if(!StringUtils.isValidString(as_tipoRecaudo))
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_TIPO_RECAUDO_NO_VALIDO));

						if(!StringUtils.isValidString(as_codigoTransaccion))
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_TRANSACCION_RECAUDO_NO_VALIDO));

						if(ab_notificar)
						{
							String              ls_codigoMensaje;
							NotificacionPago    lnp_np;
							NotificacionPagoDAO lnpd_DAO;

							ls_codigoMensaje     = "0";
							lnpd_DAO             = DaoCreator.getNotificacionPagoDAO(adm_manager);
							lnp_np               = lnpd_DAO.findByNumeroReferencia(as_numeroReferencia);

							try
							{
								String             ls_idEntidadRecaudadora;
								String             ls_idPuntoRecaudo;
								EntidadRecaudadora ler_er;
								PuntoRecaudo       lpr_pr;
								TipoRecaudo        ltr_tr;

								ltr_tr = DaoCreator.getTipoRecaudoDAO(adm_manager).findById(as_tipoRecaudo);

								if(ltr_tr == null)
								{
									ls_codigoMensaje = "1";
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_TIPO_RECAUDO_NO_VALIDO));
								}

								ler_er = DaoCreator.getEntidadRecaudadoraDAO(adm_manager)
										               .findByCodigo(as_codigoEntidadRecaudadora);

								if(ler_er == null)
								{
									ls_codigoMensaje = "2";
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_RECAUDADORA_NO_PERMITIDO));
								}

								lpr_pr = DaoCreator.getPuntoRecaudoDAO(adm_manager)
										               .findByCodigo(
										    as_codigoPuntoRecaudo, ler_er.getIdEntidadRecaudadora()
										);

								if(lpr_pr == null)
								{
									ls_codigoMensaje = "3";
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_CODIGO_PUNTO_RECAUDO_NO_PERMITIDO)
									);
								}

								ls_idEntidadRecaudadora     = lpr_pr.getIdEntidadRecaudadora();
								ls_idPuntoRecaudo           = lpr_pr.getIdPuntoRecaudo();

								if(lnp_np != null)
								{
									Date ld_fechaVencimiento;
									Date ld_now;

									ld_fechaVencimiento     = lnp_np.getFechaVencimiento();
									ld_now                  = new Date();

									if(ld_fechaVencimiento == null)
										throw new B2BException(addErrorNP(ErrorKeys.ERROR_FECHA_VENCIMIENTO_NO_VALIDA));

									if(ld_fechaVencimiento.after(ld_now))
									{
										Calendar lc_calendar;
										Date     ld_test;

										lc_calendar     = Calendar.getInstance();
										ld_test         = lc_calendar.getTime();

										lc_calendar.setTime(ld_now);
										lc_calendar.add(Calendar.DAY_OF_YEAR, 1);

										if(aldt_fechaTransaccion == null)
											throw new B2BException(
											    addErrorNP(ErrorKeys.ERROR_FECHA_TRANSACCION_NO_VALIDA)
											);
										else if(
										    aldt_fechaTransaccion.isAfter(obtenerLocalDateTime(ld_fechaVencimiento))
										)
										{
											Object[] loa_messageArgs = new String[1];

											loa_messageArgs[0] = StringUtils.getString(
												    ld_fechaVencimiento, FormatoFechaCommon.DIA_MES_ANIO
												);

											throw new B2BException(
											    addErrorNP(
											        ErrorKeys.ERROR_FECHA_TRANSACCION_NO_PERMITIDA, loa_messageArgs
											    )
											);
										}

										if(aldt_fechaBancaria == null)
											throw new B2BException(
											    addErrorNP(ErrorKeys.ERROR_FECHA_BANCARIA_NO_VALIDA)
											);

										if(aldt_fechaBancaria.isBefore(obtenerLocalDateTime(ld_test)))
										{
											PuntoRecaudoTipoRecaudo lprtr_prtr;

											lprtr_prtr = DaoCreator.getPuntoRecaudoTipoRecaudoDAO(adm_manager)
													                   .findByPuntoyTipoRecaudo(
													    ls_idPuntoRecaudo, as_tipoRecaudo
													);

											if(lprtr_prtr == null)
											{
												ltr_tr.getNombreTipoRecaudo();
												lpr_pr.getNombrePuntoRecaudo();
												ler_er.getNombreEntidadRecaudadora();

												Object[] loa_messageArgs = new String[3];

												loa_messageArgs[0]     = ler_er.getNombreEntidadRecaudadora();
												loa_messageArgs[1]     = lpr_pr.getNombrePuntoRecaudo();
												loa_messageArgs[2]     = ltr_tr.getNombreTipoRecaudo();

												ls_codigoMensaje = "4";
												throw new B2BException(
												    addErrorNP(
												        ErrorKeys.ERROR_TIPO_RECAUDO_PUNTO_RECAUDO_NO_VALIDO,
												        loa_messageArgs
												    )
												);
											}

											if(ad_valorTransaccion >= NumericUtils.DEFAULT_DOUBLE_VALUE)
											{
												double ld_valorServicio;

												ld_valorServicio = lnp_np.getMontoTransaccion();

												if(ld_valorServicio == ad_valorTransaccion)
												{
													NotificacionPagoRecibidaDAO lnprd_DAO;
													NotificacionPagoRecibida    lnpr_npr;

													lnprd_DAO     = DaoCreator.getNotificacionPagoRecibidaDAO(
														    adm_manager
														);
													lnpr_npr      = lnprd_DAO.findById(
														    lnp_np.getIdNotificacionPagoRecibida()
														);

													if(lnpr_npr == null)
													{
														lnpr_npr = new NotificacionPagoRecibida();

														lnpr_npr.setNumeroReferenciaBachue(as_numeroReferencia);
														lnpr_npr.setCodigoEntidadRecaudadora(ls_idEntidadRecaudadora);
														lnpr_npr.setCodigoPuntoRecaudoEntidad(ls_idPuntoRecaudo);
														lnpr_npr.setCodigoTipoRecaudo(as_tipoRecaudo);
														lnpr_npr.setFechaTransaccion(
														    obtenerDateDesdeLocalDateTime(aldt_fechaTransaccion)
														);
														lnpr_npr.setFechaBancaria(
														    obtenerDateDesdeLocalDateTime(aldt_fechaBancaria)
														);
														lnpr_npr.setCodigoTransaccionRecaudador(as_codigoTransaccion);
														lnpr_npr.setMontoTransaccion(ad_valorTransaccion);
														lnpr_npr.setCodigoMensaje(ls_codigoMensaje);
														lnpr_npr.setIdUsuarioCreacion(as_userId);
														lnpr_npr.setIpCreacion(as_remoteIp);

														lnprd_DAO.insert(lnpr_npr);

														lnp_np.setIdNotificacionPagoRecibida(
														    lnpr_npr.getIdNotificacionPagoRecibida()
														);
														lnp_np.setMontoTransaccion(ad_valorTransaccion);
														lnp_np.setIdPuntoRecaudoTipoRecaudo(
														    lprtr_prtr.getIdPuntoRecaudoTipoRecaudo()
														);
														lnp_np.setEstado(EstadoCommon.REFERENCIA_PAGADA);
														lnp_np.setNumeroTransaccionRecaudador(as_codigoTransaccion);
														lnp_np.setFechaBancaria(
														    obtenerDateDesdeLocalDateTime(aldt_fechaBancaria)
														);
														lnp_np.setFechaTransaccion(
														    obtenerDateDesdeLocalDateTime(aldt_fechaTransaccion)
														);

														lnpd_DAO.update(lnp_np);
													}
													else
													{
														ls_codigoMensaje = "5";
														throw new B2BException(
														    addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_NOTIFICADO)
														);
													}
												}
												else
												{
													ls_codigoMensaje = "6";
													throw new B2BException(
													    addErrorNP(
													        ErrorKeys.ERROR_MONTO_TRANSACCION_DIF_MONTO_LIQUIDADO
													    )
													);
												}
											}
											else
											{
												ls_codigoMensaje = "7";
												throw new B2BException(
												    addErrorNP(ErrorKeys.ERROR_MONTO_MAYOR_TRANSACCION_CERO)
												);
											}
										}
										else
										{
											Object[] loa_messageArgs = new String[1];

											loa_messageArgs[0]     = StringUtils.getString(
												    ld_test, FormatoFechaCommon.DIA_MES_ANIO
												);

											ls_codigoMensaje = "8";
											throw new B2BException(
											    addErrorNP(
											        ErrorKeys.ERROR_FECHA_BANCARIA_NO_PERMITIDA, loa_messageArgs
											    )
											);
										}
									}
									else
									{
										ls_codigoMensaje = "9";
										throw new B2BException(addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_VENCIDO));
									}
								}
								else
									throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_EXISTE));
							}
							catch(B2BException lb2be_e)
							{
								clh_LOGGER.error("validarPago", lb2be_e);

								if(ls_codigoMensaje.equalsIgnoreCase("0"))
									throw lb2be_e;
								else
								{
									NotificacionPagoRecibidaDAO lnprd_DAO;
									NotificacionPagoRecibida    lnpr_npr;

									lnprd_DAO     = DaoCreator.getNotificacionPagoRecibidaDAO(adm_manager);
									lnpr_npr      = (lnp_np != null)
										? lnprd_DAO.findById(lnp_np.getIdNotificacionPagoRecibida()) : null;

									if(lnpr_npr == null)
									{
										lnpr_npr = new NotificacionPagoRecibida();

										lnpr_npr.setNumeroReferenciaBachue(as_numeroReferencia);
										lnpr_npr.setCodigoEntidadRecaudadora(as_codigoEntidadRecaudadora);
										lnpr_npr.setCodigoPuntoRecaudoEntidad(as_codigoPuntoRecaudo);
										lnpr_npr.setCodigoTipoRecaudo(as_tipoRecaudo);
										lnpr_npr.setFechaTransaccion(
										    obtenerDateDesdeLocalDateTime(aldt_fechaTransaccion)
										);
										lnpr_npr.setFechaBancaria(obtenerDateDesdeLocalDateTime(aldt_fechaBancaria));
										lnpr_npr.setCodigoTransaccionRecaudador(as_codigoTransaccion);
										lnpr_npr.setMontoTransaccion(ad_valorTransaccion);
										lnpr_npr.setCodigoMensaje(ls_codigoMensaje);
										lnpr_npr.setIdUsuarioCreacion(as_userId);
										lnpr_npr.setIpCreacion(as_remoteIp);

										lnprd_DAO.insert(lnpr_npr);

										adm_manager.commit();

										throw lb2be_e;
									}
									else
										throw new B2BException(
										    addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_NOTIFICADO)
										);
								}
							}
						}
						else
						{
							Liquidacion    ll_liquidacion;
							LiquidacionDAO lld_DAO;

							lld_DAO            = DaoCreator.getAccLiquidacionDAO(adm_manager);
							ll_liquidacion     = lld_DAO.findByNumeroReferencia(as_numeroReferencia);

							if(ll_liquidacion == null)
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_DISPONIBLE));

							if(ll_liquidacion.getConsecutivo() <= 0)
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_DISPONIBLE));

							LocalDateTime ld_fechaVencimiento;
							Calendar      lc_ultimoDia = Calendar.getInstance();

							ld_fechaVencimiento = obtenerLocalDateTime(ll_liquidacion.getFechaVencimiento());

							if(ld_fechaVencimiento != null)
								lc_ultimoDia.set(Calendar.YEAR, ld_fechaVencimiento.getYear());

							lc_ultimoDia.set(Calendar.MONTH, Calendar.DECEMBER);
							lc_ultimoDia.set(Calendar.DAY_OF_MONTH, 31);
							lc_ultimoDia.set(Calendar.HOUR_OF_DAY, 23);
							lc_ultimoDia.set(Calendar.MINUTE, 59);
							lc_ultimoDia.set(Calendar.SECOND, 59);
							lc_ultimoDia.set(Calendar.MILLISECOND, 999);

							ld_fechaVencimiento = obtenerLocalDateTime(lc_ultimoDia.getTime());

							if(aldt_fechaTransaccion == null)
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_FECHA_TRANSACCION_NO_VALIDA));

							if((ld_fechaVencimiento != null) && ld_fechaVencimiento.isAfter(aldt_fechaTransaccion))
							{
								Calendar lc_calendar;
								Date     ld_test;
								Date     ld_now;

								ld_now          = new Date();
								lc_calendar     = Calendar.getInstance();
								ld_test         = lc_calendar.getTime();

								lc_calendar.setTime(ld_now);
								lc_calendar.add(Calendar.DAY_OF_YEAR, 1);

								if(
								    (aldt_fechaBancaria != null)
									    && aldt_fechaBancaria.isBefore(obtenerLocalDateTime(ld_test))
								)
								{
									String ls_estado;

									ls_estado = ll_liquidacion.getIdTipoEstadoLiquidacion();

									if(
									    (StringUtils.isValidString(ls_estado)
										    && !ls_estado.equalsIgnoreCase(EstadoCommon.ESTADO_LIQUIDACION_LIQUIDADO))
									)
										throw new B2BException(
										    addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_DISPONIBLE)
										);

									if(ad_valorTransaccion != NumericUtils.DEFAULT_DOUBLE_VALUE)
									{
										double ld_valorServicio;

										ld_valorServicio = NumericUtils.getDouble(ll_liquidacion.getValorTotal(), 0D);

										if(ll_liquidacion != null)
										{
											String ls_idTipoMayorValor;
											ls_idTipoMayorValor = ll_liquidacion.getIdTipoMayorValor();

											if(
											    StringUtils.isValidString(ls_idTipoMayorValor)
												    && (NumericUtils.getDouble(
												        ll_liquidacion.getTotalMayorValor(),
												        NumericUtils.DEFAULT_DOUBLE_VALUE
												    ) > NumericUtils.DEFAULT_DOUBLE_VALUE)
											)
												ld_valorServicio = NumericUtils.getDouble(
													    ll_liquidacion.getTotalMayorValor(),
													    NumericUtils.DEFAULT_DOUBLE_VALUE
													);
											else
												ld_valorServicio = NumericUtils.getDouble(
													    ll_liquidacion.getValorTotal(),
													    NumericUtils.DEFAULT_DOUBLE_VALUE
													);
										}

										if(ld_valorServicio == ad_valorTransaccion)
										{
											RegistrarPagoDAO lrpd_DAO;
											RegistroPago     lrp_rp;

											lrpd_DAO     = DaoCreator.getRegistrarPagoDAO(adm_manager);
											lrp_rp       = lrpd_DAO.findById(as_numeroReferencia);

											if(lrp_rp == null)
											{
												lrp_rp = new RegistroPago(as_numeroReferencia);

												lrp_rp.setIdEntidadRecaudo(as_codigoEntidadRecaudadora);
												lrp_rp.setIdPuntoRecaudoEntidad(as_codigoPuntoRecaudo);
												lrp_rp.setIdTipoRecaudo(as_tipoRecaudo);
												lrp_rp.setCodigoTransaccionRecaudador(as_codigoTransaccion);
												lrp_rp.setFechaBancaria(
												    obtenerDateDesdeLocalDateTime(aldt_fechaBancaria)
												);
												lrp_rp.setFechaTransaccion(
												    obtenerDateDesdeLocalDateTime(aldt_fechaTransaccion)
												);
												lrp_rp.setMontoTransaccion(ad_valorTransaccion);
												lrp_rp.setIdUsuarioCreacion(as_userId);
												lrp_rp.setIpCreacion(as_remoteIp);

												lrpd_DAO.insertarPago(lrp_rp);

												DaoCreator.getRegistroPagoDAO(adm_manager).actualizarPago(lrp_rp);

												{
													Solicitud ls_solicitud;

													ls_solicitud = new Solicitud();

													ls_solicitud.setIdSolicitud(ll_liquidacion.getIdSolicitud());
													ls_solicitud.setIdUsuarioModificacion(as_userId);
													ls_solicitud.setIpModificacion(as_remoteIp);

													DaoCreator.getProcedimientosDAO(adm_manager)
														          .procLlaCrearEtapaTrg(
														    ls_solicitud, as_numeroReferencia,
														    IdentificadoresCommon.REGISTRAR_PAGO,
														    NumericUtils.getLongWrapper(
														        EtapaCommon.ID_ETAPA_PENDIENTE_DE_PAGO
														    )
														);
												}
											}
											else
												throw new B2BException(
												    addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_PAGADO)
												);
										}
										else
											throw new B2BException(
											    addErrorNP(ErrorKeys.ERROR_MONTO_TRANSACCION_DIF_MONTO_LIQUIDADO)
											);
									}
									else
										throw new B2BException(
										    addErrorNP(ErrorKeys.ERROR_MONTO_MAYOR_TRANSACCION_CERO)
										);
								}
								else
								{
									Object[] loa_messageArgs = new String[1];

									loa_messageArgs[0] = StringUtils.getString(
										    ld_test, FormatoFechaCommon.DIA_MES_ANIO
										);

									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_FECHA_BANCARIA_NO_PERMITIDA, loa_messageArgs)
									);
								}
							}
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_RECIBO_LIQUIDACION_VENCIDO));
						}
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_REFERENCIA_NO_VALIDO));
				}
				else
					throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_PUNTO_RECAUDO_NO_VALIDO));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_RECAUDADORA_NO_VALIDO));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarPago", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Método encargado de validar si una persona existe en el sistema Bachué.
	 *
	 * @param as_idSolicitud Objeto de tipo String que contiene el ID_SOLICITUD a validar.
	 * @param as_tipoPersona Objeto de tipo String que contiene el ID_TIPO_PERSONA a validar.
	 * @param as_tipoDocumento Objeto de tipo String que contiene el ID_TIPO_DOCUMENTO a validar.
	 * @param as_numeropDocumento Objeto de tipo String que contiene el NUMERO_DOCUMENTO a validar.
	 * @param as_primerNombre Objeto de tipo String que contiene el PRIMER_NOMBRE a validar.
	 * @param as_segundoNombre Objeto de tipo String que contiene el SEGUNDO_NOMBRE a validar.
	 * @param as_primerApellido Objeto de tipo String que contiene el PRIMER_APELLIDO a validar.
	 * @param as_segundoApellido Objeto de tipo String que contiene el SEGUNDO_APELLIDO a validar.
	 * @param as_razonSocial Objeto de tipo String que contiene el RAZON_SOCIAL a validar.
	 * @param ldm_manager Conexión a la base de datos con la cual se está generando la transacción.
	 * @param as_userId Id del usuario que ejecuta la acción.
	 * @param as_localIp Dirección IP del servidor que atiende la petición.
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción.
	 * @param asb_correo Objeto de tipo String que contiene el CORREO_ELECTRONICO a validar.
	 * @param asb_telefono Objeto de tipo String que contiene el TELEFONO a validar.
	 * @return Objeto de tipo Persona que contiene el resultado de la busqueda de los argumentos ingresados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Persona validarPersona(
	    String as_idSolicitud, String as_tipoPersona, String as_tipoDocumento, String as_numeropDocumento,
	    String as_primerNombre, String as_segundoNombre, String as_primerApellido, String as_segundoApellido,
	    String as_razonSocial, DAOManager ldm_manager, String as_userId, String as_localIp, String as_remoteIp,
	    StringBuilder asb_correo, StringBuilder asb_telefono
	)
	    throws B2BException
	{
		return validarPersona(
		    as_idSolicitud, as_tipoPersona, as_tipoDocumento, as_numeropDocumento, as_primerNombre, as_segundoNombre,
		    as_primerApellido, as_segundoApellido, as_razonSocial, ldm_manager, as_userId, as_localIp, as_remoteIp,
		    asb_correo, asb_telefono, true
		);
	}

	/**
	 * Método encargado de validar si una persona existe en el sistema Bachué.
	 *
	 * @param as_idSolicitud Objeto de tipo String que contiene el ID_SOLICITUD a validar.
	 * @param as_tipoPersona Objeto de tipo String que contiene el ID_TIPO_PERSONA a validar.
	 * @param as_tipoDocumento Objeto de tipo String que contiene el ID_TIPO_DOCUMENTO a validar.
	 * @param as_numeroDocumento Objeto de tipo String que contiene el NUMERO_DOCUMENTO a validar.
	 * @param as_primerNombre Objeto de tipo String que contiene el PRIMER_NOMBRE a validar.
	 * @param as_segundoNombre Objeto de tipo String que contiene el SEGUNDO_NOMBRE a validar.
	 * @param as_primerApellido Objeto de tipo String que contiene el PRIMER_APELLIDO a validar.
	 * @param as_segundoApellido Objeto de tipo String que contiene el SEGUNDO_APELLIDO a validar.
	 * @param as_razonSocial Objeto de tipo String que contiene el RAZON_SOCIAL a validar.
	 * @param ldm_manager Conexión a la base de datos con la cual se está generando la transacción.
	 * @param as_userId Id del usuario que ejecuta la acción.
	 * @param as_localIp Dirección IP del servidor que atiende la petición.
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción.
	 * @param asb_correo Objeto de tipo String que contiene el CORREO_ELECTRONICO a validar.
	 * @param asb_telefono Objeto de tipo String que contiene el TELEFONO a validar.
	 * @param ab_validarNombres variable de tipo boolean que indica si se debe validar nombres y apellidos o razon social.
	 * @return Objeto de tipo Persona que contiene el resultado de la busqueda de los argumentos ingresados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized Persona validarPersona(
	    String as_idSolicitud, String as_tipoPersona, String as_tipoDocumento, String as_numeroDocumento,
	    String as_primerNombre, String as_segundoNombre, String as_primerApellido, String as_segundoApellido,
	    String as_razonSocial, DAOManager ldm_manager, String as_userId, String as_localIp, String as_remoteIp,
	    StringBuilder asb_correo, StringBuilder asb_telefono, boolean ab_validarNombres
	)
	    throws B2BException
	{
		boolean      lb_nit;
		Persona      lp_insertar;
		Persona      lp_tmp;
		PersonaDAO   lpd_DAO;
		SolicitudDAO lsd_DAO;

		lp_insertar     = new Persona();
		lp_tmp          = null;
		lpd_DAO         = com.bachue.snr.prosnr01.dao.DaoCreator.getPersonaDAO(ldm_manager);
		lsd_DAO         = com.bachue.snr.prosnr01.dao.DaoCreator.getSolicitudDAO(ldm_manager);

		if(StringUtils.isValidString(as_tipoPersona))
		{
			Collection<TipoPersona> lctp_tipoPersona;

			lctp_tipoPersona = DaoCreator.getTipoPersonaDAO(ldm_manager).findAll();

			if(CollectionUtils.isValidCollection(lctp_tipoPersona))
			{
				boolean lb_tipoPersonaValido;

				lb_tipoPersonaValido = false;

				Iterator<TipoPersona> litp_iterator;

				litp_iterator = lctp_tipoPersona.iterator();

				while(litp_iterator.hasNext() && !lb_tipoPersonaValido)
				{
					TipoPersona ltp_actual;

					ltp_actual = litp_iterator.next();

					if(ltp_actual != null)
					{
						String ls_nombre;

						ls_nombre = ltp_actual.getDescripcion();

						if(StringUtils.isValidString(ls_nombre) && ls_nombre.equalsIgnoreCase(as_tipoPersona))
							lb_tipoPersonaValido = true;
					}
				}

				if(!lb_tipoPersonaValido)
					throw new B2BException(addErrorNP(ErrorKeys.DEBE_DIGITAR_ID_TIPO_PERSONA));
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_EXISTE));
		}

		if(StringUtils.isValidString(as_tipoDocumento))
		{
			InteresadoDocumentoTipo litd_tipoDocumento;

			litd_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager).findById(as_tipoDocumento);

			if(litd_tipoDocumento != null)
				lb_nit = StringUtils.getStringNotNull(litd_tipoDocumento.getIdDocumentoTipo())
						                .equalsIgnoreCase(IdentificadoresCommon.NIT);
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_EXISTE));
		}
		else
			throw new B2BException(addErrorNP(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO));

		if(!StringUtils.isValidString(as_numeroDocumento))
			throw new B2BException(addErrorNP(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO));

		if((lb_nit && !StringUtils.isValidString(as_razonSocial)) && ab_validarNombres)
			throw new B2BException(addErrorNP(ErrorKeys.ERROR_RAZON_SOCIAL_NO_VALIDO));
		else if(
		    (!lb_nit && (!StringUtils.isValidString(as_primerNombre) || !StringUtils.isValidString(as_primerApellido)))
			    && ab_validarNombres
		)
			throw new B2BException(addErrorNP(ErrorKeys.ERROR_PRIMER_NOMBRE_APELLIDO_NO_VALIDO));

		{
			ExpresionRegular ler_validador;

			ler_validador = ExpresionRegular.getExpresionRegular();

			if(StringUtils.isValidString(as_primerNombre) && !ler_validador.esSoloLetras(as_primerNombre))
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PRIMER_NOMBRE_NO_VALIDO));

			if(StringUtils.isValidString(as_primerApellido) && !ler_validador.esSoloLetras(as_primerApellido))
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_PRIMER_APELLIDO_NO_VALIDO));
		}

		lp_insertar.setIdDocumentoTipo(as_tipoDocumento);
		lp_insertar.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		lp_insertar.setIdUsuarioCreacion(as_userId);
		lp_insertar.setIpCreacion(as_remoteIp);
		lp_insertar.setNumeroDocumento(as_numeroDocumento);
		lp_insertar.setSeleccionado(lb_nit);

		lp_insertar.setIdTipoPersona(
		    as_tipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT) ? TipoPersonaCommon.JURIDICA
		                                                                 : TipoPersonaCommon.NATURAL
		);

		if(!lb_nit)
		{
			lp_insertar.setPrimerNombre(as_primerNombre);
			lp_insertar.setSegundoNombre(as_segundoNombre);
			lp_insertar.setPrimerApellido(as_primerApellido);
			lp_insertar.setSegundoApellido(as_segundoApellido);
		}
		else
			lp_insertar.setRazonSocial(as_razonSocial);

		{
			String ls_idPersonaFlag;

			ls_idPersonaFlag = marcarFlagPersona(ldm_manager, lp_insertar, as_userId, as_localIp);

			if(StringUtils.isValidString(ls_idPersonaFlag))
			{
				lp_tmp = lpd_DAO.findById(ls_idPersonaFlag);

				if(lp_tmp != null)
				{
					UtilDAO lut_DAO;
					String  ls_idPersonaNueva;
					String  ls_idCorreoElectronico;
					String  ls_idTelefonoCelular;

					lut_DAO                    = DaoCreator.getUtilDAO(ldm_manager);
					ls_idPersonaNueva          = lp_tmp.getIdPersona();
					ls_idCorreoElectronico     = asb_correo.toString();
					ls_idTelefonoCelular       = asb_telefono.toString();

					if(
					    (asb_correo != null) && (asb_correo.toString().trim().length() > 0)
						    && (asb_correo.indexOf(IdentificadoresCommon.ARROBA) > -1)
					)
					{
						Map<Integer, Object>      lmio_criterios;
						List<Map<String, Object>> llls_lhpso_cel;

						lmio_criterios = new LinkedHashMap<Integer, Object>();
						lmio_criterios.put(Integer.valueOf(1), ls_idPersonaNueva);
						lmio_criterios.put(Integer.valueOf(2), ls_idCorreoElectronico);

						llls_lhpso_cel     = lut_DAO.getCustonQuery(
							    ConsultasUtilidades.CS_CONSULTA_DATA_EMAIL_PERSONA, lmio_criterios
							);

						ls_idCorreoElectronico = null;

						if(CollectionUtils.isValidCollection(llls_lhpso_cel))
						{
							for(Map<String, Object> lcel_actual : llls_lhpso_cel)
								ls_idCorreoElectronico = (lcel_actual != null)
									? lcel_actual.get(IdentificadoresCommon.ID_CORREO_ELECTRONICO).toString() : null;
						}

						if(ls_idCorreoElectronico == null)
						{
							PersonaCorreoElectronico    lpc_pc;
							PersonaCorreoElectronicoDAO lpced_DAO;

							lpc_pc        = new PersonaCorreoElectronico();
							lpced_DAO     = DaoCreator.getPersonaCorreoElectronicoDAO(ldm_manager);

							lpc_pc.setIdPersona(ls_idPersonaNueva);
							lpc_pc.setCorreoElectronico(asb_correo.toString());
							lpc_pc.setFechaDesde(new Date());
							lpc_pc.setIdUsuarioCreacion(as_userId);
							lpc_pc.setIpCreacion(as_remoteIp);

							lpced_DAO.insertOrUpdate(lpc_pc, true);

							ls_idCorreoElectronico = lpced_DAO.findIdCorreoElectronico(lpc_pc) + "";
						}
					}

					asb_correo.delete(0, asb_correo.length()).append(ls_idCorreoElectronico);

					if((asb_telefono != null) && (asb_telefono.toString().trim().length() > 0))
					{
						String ls_telefono;

						ls_telefono = asb_telefono.toString();

						if(StringUtils.isValidString(ls_telefono))
						{
							Map<Integer, Object>      lmio_criterios;
							List<Map<String, Object>> llls_lhpso_cel;

							lmio_criterios = new LinkedHashMap<Integer, Object>();
							lmio_criterios.put(Integer.valueOf(1), ls_idPersonaNueva);
							lmio_criterios.put(Integer.valueOf(2), ls_telefono);

							llls_lhpso_cel     = lut_DAO.getCustonQuery(
								    ConsultasUtilidades.CS_CONSULTA_DATA_CELULAR_PERSONA, lmio_criterios
								);

							ls_idTelefonoCelular = null;

							if(CollectionUtils.isValidCollection(llls_lhpso_cel))
							{
								for(Map<String, Object> lcel_actual : llls_lhpso_cel)
									ls_idTelefonoCelular = lcel_actual.get(IdentificadoresCommon.ID_TELEFONO).toString();
							}

							if(ls_idTelefonoCelular == null)
							{
								PersonaTelefono    lpt_telefono;
								PersonaTelefonoDAO lptd_DAO;

								lpt_telefono     = new PersonaTelefono();
								lptd_DAO         = DaoCreator.getPersonaTelefonoDAO(ldm_manager);

								lpt_telefono.setIdPersona(ls_idPersonaNueva);
								lpt_telefono.setTelefono(ls_telefono);
								lpt_telefono.setTipoTelefono(EstadoCommon.M);
								lpt_telefono.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
								lpt_telefono.setIndicativo(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
								lpt_telefono.setFechaDesde(new Date());
								lpt_telefono.setIdUsuarioCreacion(as_userId);
								lpt_telefono.setIpCreacion(as_remoteIp);

								lptd_DAO.insertOrUpdate(lpt_telefono, true);

								ls_idTelefonoCelular = lptd_DAO.findIdTelefono(lpt_telefono) + "";
							}
						}
					}

					asb_telefono.delete(0, asb_telefono.length()).append(ls_idTelefonoCelular);

					Solicitud ls_solicitud;

					ls_solicitud = lsd_DAO.findById(as_idSolicitud);

					if((ls_solicitud != null))
					{
						if(ls_idCorreoElectronico != null)
							ls_solicitud.setIdCorreoElectronico(ls_idCorreoElectronico);

						if(ls_idTelefonoCelular != null)
							ls_solicitud.setIdTelefono(ls_idTelefonoCelular);

						ls_solicitud.setIdPersona(ls_idPersonaNueva);
						ls_solicitud.setIdUsuarioModificacion(as_userId);
						ls_solicitud.setIpModificacion(as_remoteIp);

						lsd_DAO.insertOrUpdate(ls_solicitud, false);
					}
				}
			}
		}

		asb_correo.append("");
		asb_telefono.append("");

		return lp_tmp;
	}

	/**
	 * Método encargado de verificar los servicios a consultar una tarifa vigente sean válidos.
	 *
	 * @param atsctsia_servicios Objeto de tipo TipoServicioCTSI[] que contiene los servicios a verificar.
	 * @param as_userId Id del usuario que ejecuta la acción.
	 * @param as_localIp Dirección IP del servidor que atiende la petición.
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción.
	 * @param adm_manager Conexión a la base de datos con la cual se está generando la transacción.
	 * @param as_constante de as constante
	 * @param atectat_request de atectat request
	 * @param amsb_validacionesInsercion de amsb validaciones insercion
	 * @return Objeto de tipo TipoServicioCTSO[] que contiene los servicios consultados ya verificados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized TipoServicioCTSO[] verificarServicios(
	    TipoServicioCTSI[] atsctsia_servicios, String as_userId, String as_localIp, String as_remoteIp,
	    DAOManager adm_manager, String as_constante, TipoEntradaConsultarTarifaAlertasTitulares atectat_request,
	    Map<String, Boolean> amsb_validacionesInsercion
	)
	    throws B2BException
	{
		TipoServicioCTSO[] ltst_respuesta;

		ltst_respuesta = null;

		try
		{
			ProcedimientosDAO lpd_DAO;

			lpd_DAO = DaoCreator.getProcedimientosDAO(adm_manager);

			if(CollectionUtils.isValidCollection(atsctsia_servicios))
			{
				Collection<Constantes>  lc_relacionCriterios;
				CirculoRegistralDao     lcrd_DAO;
				int                     li_servicios;
				boolean                 lb_validacionesInsercionLlenas;
				Map<String, Constantes> lmsc_relacionCriterios;
				PredioRegistroDAO       lprd_DAO;
				Set<String>             ls_hashSet;
				String                  ls_relacionCriterios;

				lc_relacionCriterios               = null;
				li_servicios                       = atsctsia_servicios.length;
				lb_validacionesInsercionLlenas     = CollectionUtils.isValidCollection(amsb_validacionesInsercion);
				lmsc_relacionCriterios             = new HashMap<String, Constantes>();
				lcrd_DAO                           = DaoCreator.getCirculoRegistralDAO(adm_manager);
				lprd_DAO                           = DaoCreator.getPredioRegistroDAO(adm_manager);
				ls_hashSet                         = new HashSet<String>();
				ls_relacionCriterios               = null;
				ltst_respuesta                     = new TipoServicioCTSO[li_servicios];

				for(int li_servicio = 0; li_servicio < li_servicios; li_servicio++)
				{
					String              ls_tipoServicio;
					String              ls_tipoSubServicio;
					Map<String, String> lmss_criterios;
					TipoServicioCTSI    ltsctsi_servicio;
					TipoCriterioCTSI[]  ltctsia_criterios;

					ltsctsi_servicio = atsctsia_servicios[li_servicio];

					if(ltsctsi_servicio == null)
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_SERVICIO_SOLICITADO_INVALIDO));

					ls_tipoServicio = StringUtils.getString(ltsctsi_servicio.getTipoServicio());

					if(StringUtils.isValidString(ls_tipoServicio))
					{
						Proceso lp_p;

						lp_p = DaoCreator.getProcesoDAO(adm_manager).findById(ls_tipoServicio);

						if(lp_p == null)
						{
							Object[] loa_messageArgs = new String[1];

							loa_messageArgs[0] = ls_tipoServicio;

							throw new B2BException(
							    addErrorNP(ErrorKeys.ERROR_CODIGO_SERVICIO_SOLICITADO_NO_EXISTENTE, loa_messageArgs)
							);
						}
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_SERVICIO_SOLICITADO_INVALIDO));

					ls_tipoSubServicio = StringUtils.getString(ltsctsi_servicio.getSubtipoServicio());

					if(StringUtils.isValidString(ls_tipoSubServicio))
					{
						SubprocesoVersion lspv_spv;

						lspv_spv = DaoCreator.getSubprocesoVersionDAO(adm_manager)
								                 .findByIdMaxVersion(ls_tipoServicio, ls_tipoSubServicio);

						if(lspv_spv == null)
						{
							Object[] loa_messageArgs = new String[1];

							loa_messageArgs[0] = ls_tipoSubServicio;

							throw new B2BException(
							    addErrorNP(ErrorKeys.ERROR_CODIGO_SUBSERVICIO_SOLICITADO_NO_EXISTE, loa_messageArgs)
							);
						}
					}
					else
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_SUBSERVICIO_SOLICITADO_INVALIDO));

					if(li_servicio == 0)
					{
						ls_relacionCriterios     = as_constante + IdentificadoresCommon.SIMBOLO_GUION_BAJO
							+ ls_tipoServicio + IdentificadoresCommon.SIMBOLO_GUION_BAJO + ls_tipoSubServicio
							+ IdentificadoresCommon.SIMBOLO_GUION_BAJO;

						lc_relacionCriterios = DaoCreator.getConstantesDAO(adm_manager).findbyLike(as_constante + "%");

						if(CollectionUtils.isValidCollection(lc_relacionCriterios))
						{
							for(Constantes lc_actual : lc_relacionCriterios)
							{
								if(lc_actual != null)
								{
									String ls_idConstante;

									ls_idConstante = lc_actual.getIdConstante();

									if(
									    StringUtils.isValidString(ls_idConstante)
										    && ls_idConstante.startsWith(ls_relacionCriterios)
									)
										lmsc_relacionCriterios.put(ls_idConstante, lc_actual);
								}
							}
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_NO_EXISTEN_CONSTANTES_PARA_CRITERIOS));
					}

					ltctsia_criterios     = ltsctsi_servicio.getCriterios();
					lmss_criterios        = new HashMap<String, String>();

					if(
					    !lmsc_relacionCriterios.containsKey(ls_relacionCriterios + 1)
						    && (CollectionUtils.isValidCollection(ltctsia_criterios))
					)
					{
						Object[] loa_messageArgs = new String[2];

						loa_messageArgs[0]     = ls_tipoServicio;
						loa_messageArgs[1]     = ls_tipoSubServicio;

						throw new B2BException(
						    addErrorNP(ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_PARAMETRIZACION_NO_VALIDA, loa_messageArgs)
						);
					}
					else if(
					    lmsc_relacionCriterios.containsKey(ls_relacionCriterios + 1)
						    && !(CollectionUtils.isValidCollection(ltctsia_criterios))
					)
						throw new B2BException(addErrorNP(ErrorKeys.ERROR_CRITERIOS_NO_VALIDO));

					if(
					    CollectionUtils.isValidCollection(lc_relacionCriterios)
						    && (CollectionUtils.isValidCollection(ltctsia_criterios))
					)
					{
						for(TipoCriterioCTSI ltc_criterio : ltctsia_criterios)
						{
							if(ltc_criterio != null)
							{
								String ls_codigocriterio;
								String ls_valorcriterio;

								ls_codigocriterio     = ltc_criterio.getCodigo();
								ls_valorcriterio      = StringUtils.getStringNotNull(ltc_criterio.getValor());

								if(StringUtils.isValidString(ls_codigocriterio))
								{
									if(StringUtils.isValidString(ls_valorcriterio))
										lmss_criterios.put(ls_codigocriterio, ls_valorcriterio);
									else
									{
										Object[] loa_messageArgs = new String[4];

										loa_messageArgs[0]     = ls_codigocriterio;
										loa_messageArgs[1]     = ls_tipoServicio;
										loa_messageArgs[2]     = ls_tipoSubServicio;
										loa_messageArgs[3]     = String.valueOf(li_servicio + 1);

										throw new B2BException(
										    addErrorNP(
										        ErrorKeys.ERROR_VALOR_CRITERIO_PROCESO_SUBPROCESO_NO_VALIDO,
										        loa_messageArgs
										    )
										);
									}
								}
								else
								{
									Object[] loa_messageArgs = new String[3];

									loa_messageArgs[0]     = ls_tipoServicio;
									loa_messageArgs[1]     = ls_tipoSubServicio;
									loa_messageArgs[2]     = String.valueOf(li_servicio + 1);

									throw new B2BException(
									    addErrorNP(
									        ErrorKeys.ERROR_CODIGO_CRITERIO_PROCESO_SUBPROCESO_NO_VALIDO,
									        loa_messageArgs
									    )
									);
								}
							}
							else
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_CRITERIOS_NO_VALIDO));
						}
					}

					int li_criterios;
					int li_control;

					li_criterios     = lmss_criterios.size();
					li_control       = 0;

					while(li_control < 100)
					{
						li_control++;

						if(lmsc_relacionCriterios.containsKey(ls_relacionCriterios + li_control))
						{
							boolean    lb_validarTodasDependencias;
							Constantes lc_relacionCriterio;
							String     ls_dependencias;
							String     ls_relacionProcesosCriterios;
							String[]   lsa_dependencias;
							String[]   lsa_relacionProcesosCriterios;

							lc_relacionCriterio = lmsc_relacionCriterios.get(ls_relacionCriterios + li_control);

							if(lc_relacionCriterio == null)
								throw new B2BException(addErrorNP(ErrorKeys.ERROR_CODIGO_SUBSERVICIOS_NO_PERMITIDOS));

							lb_validarTodasDependencias     = BooleanUtils.getBooleanValue(
								    lc_relacionCriterio.getEntero()
								);

							ls_relacionProcesosCriterios      = lc_relacionCriterio.getCaracter();
							ls_dependencias                   = StringUtils.getString(lc_relacionCriterio.getTexto());
							lsa_dependencias                  = (ls_dependencias != null)
								? ls_dependencias.split("\\^") : null;
							lsa_relacionProcesosCriterios     = (ls_relacionProcesosCriterios != null)
								? ls_relacionProcesosCriterios.split(",") : null;

							if(CollectionUtils.isValidCollection(lsa_relacionProcesosCriterios))
							{
								for(String ls_relacionProcesosCriterio : lsa_relacionProcesosCriterios)
								{
									if(StringUtils.isValidString(ls_relacionProcesosCriterio))
									{
										boolean lb_obligatorio;

										lb_obligatorio = false;

										if(li_control == 1)
											lb_obligatorio = true;
										else
										{
											boolean lb_cumpleTodasDependencias;

											lb_cumpleTodasDependencias = true;

											if(CollectionUtils.isValidCollection(lsa_dependencias))
											{
												for(String ls_dependencia : lsa_dependencias)
												{
													if(StringUtils.isValidString(ls_dependencia))
													{
														String[] lsa_variables;

														lsa_variables = ls_dependencia.trim().split(" ");

														if((lsa_variables != null))
														{
															int li_tamanioVariables;

															li_tamanioVariables = lsa_variables.length;

															if(li_tamanioVariables > 2)
															{
																for(
																    int li_posicion = 0;
																	    li_posicion < li_tamanioVariables;
																	    li_posicion += 4
																)
																{
																	lb_obligatorio = validarExpresion(
																		    lsa_variables[li_posicion],
																		    lsa_variables[li_posicion + 1],
																		    lsa_variables[li_posicion + 2],
																		    lmss_criterios
																		);

																	if(lb_obligatorio)
																		li_posicion = li_tamanioVariables;
																}
															}
															else
																throw new B2BException(
																    addErrorNP(
																        ErrorKeys.ERROR_PARAMETRIZACION_RELACION_PROCESOS_CRITERIOS
																    )
																);
														}
														else
															throw new B2BException(
															    addErrorNP(
															        ErrorKeys.ERROR_PARAMETRIZACION_RELACION_PROCESOS_CRITERIOS
															    )
															);

														if(lb_obligatorio && !lb_validarTodasDependencias)
															break;

														if(!lb_obligatorio && lb_validarTodasDependencias)
															lb_cumpleTodasDependencias = false;
													}
												}

												if(lb_validarTodasDependencias && !lb_cumpleTodasDependencias)
													lb_obligatorio = false;
											}
										}

										if(lb_obligatorio)
										{
											if(lb_validacionesInsercionLlenas)
											{
												{
													boolean lb_insertDatosAnt;

													lb_insertDatosAnt = BooleanUtils.getBooleanValue(
														    amsb_validacionesInsercion.get(is_datosAntSistema)
														);

													if(
													    !lb_insertDatosAnt
														    && lmss_criterios.containsKey("TIPO_PREDIO_AS")
													)
														amsb_validacionesInsercion.put(
														    is_datosAntSistema, Boolean.TRUE
														);
												}

												{
													boolean lb_insertDetalleAnt;

													lb_insertDetalleAnt = BooleanUtils.getBooleanValue(
														    amsb_validacionesInsercion.get(is_detalleAntSistema)
														);

													if(
													    !lb_insertDetalleAnt
														    && (lmss_criterios.containsKey("LIBRO_AS")
														    || lmss_criterios.containsKey(
														        IdentificadoresCommon.NUMERO_MATRICULA_AS
														    ))
													)
													{
														String ls_idCirculoAS;
														Long   ll_idMatriculaAS;

														ls_idCirculoAS       = null;
														ll_idMatriculaAS     = null;

														if(
														    (lmss_criterios.get(
															        IdentificadoresCommon.ORIP_MATRICULA_AS
															    ) != null)
															    && !lmss_criterios.get(
															        IdentificadoresCommon.ORIP_MATRICULA_AS
															    ).isEmpty()
														)
														{
															String ls_tmp;

															ls_tmp = StringUtils.getStringNotNull(
																    lmss_criterios.get(
																        IdentificadoresCommon.ORIP_MATRICULA_AS
																    )
																);

															if(StringUtils.isValidString(ls_tmp))
															{
																CirculoRegistral lcr_cr;
																ls_idCirculoAS     = ls_tmp;

																lcr_cr = lcrd_DAO.findById(ls_idCirculoAS);

																if(lcr_cr == null)
																{
																	Object[] loa_messageArgs = new String[1];

																	loa_messageArgs[0] = ls_idCirculoAS;

																	throw new B2BException(
																	    addErrorNP(
																	        ErrorKeys.CIRCULO_REGISTRAL_AS_NO_ENCONTRADO,
																	        loa_messageArgs
																	    )
																	);
																}
															}
															else
															{
																Object[] loa_messageArgs = new String[3];

																loa_messageArgs[0]     = ls_tipoServicio;
																loa_messageArgs[1]     = ls_tipoSubServicio;
																loa_messageArgs[2]     = IdentificadoresCommon.ORIP_MATRICULA_AS;

																throw new B2BException(
																    addErrorNP(
																        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO,
																        loa_messageArgs
																    )
																);
															}
														}

														if(
														    ((lmss_criterios.get(
															        IdentificadoresCommon.NUMERO_MATRICULA_AS
															    ) != null)
															    && !lmss_criterios.get(
															        IdentificadoresCommon.NUMERO_MATRICULA_AS
															    ).isEmpty())
														)
														{
															String ls_tmp;
															Long   ll_tmp;

															ls_tmp     = StringUtils.getStringNotNull(
																    lmss_criterios.get(
																        IdentificadoresCommon.NUMERO_MATRICULA_AS
																    )
																);
															ll_tmp     = NumericUtils.getLongWrapper(ls_tmp, -1L);

															if(NumericUtils.isValidLong(ll_tmp))
															{
																if(ls_tmp.length() > 10)
																{
																	Object[] loa_messageArgs = new String[3];

																	loa_messageArgs[0]     = ls_tipoServicio;
																	loa_messageArgs[1]     = ls_tipoSubServicio;
																	loa_messageArgs[2]     = IdentificadoresCommon.NUMERO_MATRICULA_AS;

																	throw new B2BException(
																	    addErrorNP(
																	        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_LONGITUD_INVALIDA,
																	        loa_messageArgs
																	    )
																	);
																}

																ll_idMatriculaAS = ll_tmp;
															}
														}

														if(
														    !StringUtils.isValidString(ls_idCirculoAS)
															    && NumericUtils.isValidLong(ll_idMatriculaAS, -1L)
														)
														{
															Object[] loa_messageArgs = new String[3];

															loa_messageArgs[0]     = ls_tipoServicio;
															loa_messageArgs[1]     = ls_tipoSubServicio;
															loa_messageArgs[2]     = IdentificadoresCommon.ORIP_MATRICULA_AS;

															throw new B2BException(
															    addErrorNP(
															        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_OBLIGATORIO,
															        loa_messageArgs
															    )
															);
														}

														if(
														    StringUtils.isValidString(ls_idCirculoAS)
															    && NumericUtils.isValidLong(ll_idMatriculaAS, -1L)
														)
														{
															PredioRegistro lpr_pr;

															lpr_pr = lprd_DAO.findByCirculoMatricula(
																    ls_idCirculoAS,
																    NumericUtils.getLong(ll_idMatriculaAS)
																);

															if(lpr_pr == null)
															{
																StringBuilder lsb_error;
																Object[]      loa_messageArgs = new String[1];

																lsb_error = new StringBuilder(
																	    IdentificadoresCommon.CIRCULO_REGISTRAL
																	);

																lsb_error.append(IdentificadoresCommon.ESPACIO_VACIO);
																lsb_error.append(ls_idCirculoAS);
																lsb_error.append(IdentificadoresCommon.SIMBOLO_COMA);

																if(NumericUtils.isValidLong(ll_idMatriculaAS))
																{
																	lsb_error.append(
																	    IdentificadoresCommon.NUMERO_MATRICULA
																	);
																	lsb_error.append(
																	    IdentificadoresCommon.ESPACIO_VACIO
																	);
																	lsb_error.append(ll_idMatriculaAS.toString());
																}

																loa_messageArgs[0] = lsb_error.toString();

																throw new B2BException(
																    addErrorNP(
																        ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA,
																        loa_messageArgs
																    )
																);
															}
														}

														amsb_validacionesInsercion.put(
														    is_detalleAntSistema, Boolean.TRUE
														);
													}
												}

												{
													boolean lb_insertSolicitudMatricula;

													lb_insertSolicitudMatricula = BooleanUtils.getBooleanValue(
														    amsb_validacionesInsercion.get(is_solicitudMatricula)
														);

													if(
													    !lb_insertSolicitudMatricula
														    && (lmss_criterios.containsKey(
														        IdentificadoresCommon.ORIP_MATRICULA
														    )
														    && (lmss_criterios.containsKey(
														        IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO
														    )
														    || lmss_criterios.containsKey(
														        IdentificadoresCommon.CEDULA_CATASTRAL_GUION_BAJO
														    )))
													)
														amsb_validacionesInsercion.put(
														    is_solicitudMatricula, Boolean.TRUE
														);
												}
											}

											if(
											    !lmss_criterios.containsKey(ls_relacionProcesosCriterio)
												    || !StringUtils.isValidString(
												        lmss_criterios.get(ls_relacionProcesosCriterio)
												    )
											)
											{
												Object[] loa_messageArgs = new String[3];

												loa_messageArgs[0]     = ls_tipoServicio;
												loa_messageArgs[1]     = ls_tipoSubServicio;
												loa_messageArgs[2]     = ls_relacionProcesosCriterio;

												throw new B2BException(
												    addErrorNP(
												        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_OBLIGATORIO,
												        loa_messageArgs
												    )
												);
											}
											else
											{
												if(
												    ls_relacionProcesosCriterio.equalsIgnoreCase(
													        IdentificadoresCommon.FORMA_ENVIO_MAIL
													    )
													    || ls_relacionProcesosCriterio.equalsIgnoreCase(
													        IdentificadoresCommon.CORREO_ELECTRONICO_TITULAR
													    )
												)
												{
													String ls_correo;

													ls_correo = lmss_criterios.get(ls_relacionProcesosCriterio);

													if(
													    StringUtils.isValidString(ls_correo)
														    && (!ExpresionRegular.getExpresionRegular()
														                             .esCorreoElectronico(ls_correo))
													)
													{
														Object[] loa_messageArgs = new String[3];

														loa_messageArgs[0]     = ls_tipoServicio;
														loa_messageArgs[1]     = ls_tipoSubServicio;
														loa_messageArgs[2]     = ls_relacionProcesosCriterio;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_VALIDO,
														        loa_messageArgs
														    )
														);
													}
												}
												else if(
												    ls_relacionProcesosCriterio.equalsIgnoreCase(
													        IdentificadoresCommon.CRITERIO_VALOR_RECARGA
													    )
												)
												{
													String ls_valorRecarga;

													ls_valorRecarga = lmss_criterios.get(ls_relacionProcesosCriterio);

													if(
													    !NumericUtils.isValidBigDecimal(
														        NumericUtils.getBigDecimal(ls_valorRecarga)
														    )
													)
													{
														Object[] loa_messageArgs = new String[3];

														loa_messageArgs[0]     = ls_tipoServicio;
														loa_messageArgs[1]     = ls_tipoSubServicio;
														loa_messageArgs[2]     = ls_relacionProcesosCriterio;

														throw new B2BException(
														    addErrorNP(
														        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_VALOR_INVALIDO,
														        loa_messageArgs
														    )
														);
													}
												}
											}
										}

										li_criterios--;
									}
								}
							}
						}
					}

					if(li_criterios > 0)
					{
						if(li_control == 1)
						{
							Object[] loa_messageArgs = new String[2];

							loa_messageArgs[0]     = ls_tipoServicio;
							loa_messageArgs[1]     = ls_tipoSubServicio;

							throw new B2BException(
							    addErrorNP(
							        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_PARAMETRIZACION_NO_VALIDA, loa_messageArgs
							    )
							);
						}
						else
							throw new B2BException(addErrorNP(ErrorKeys.ERROR_CRITERIOS_NO_VALIDO_EXISTENTES));
					}

					{
						RespuestaConsultaTarifa lrct_return;
						Long                    ll_idMatricula;
						String                  ls_idCirculo;
						String                  ls_cedulaCatastral;

						ll_idMatricula         = null;
						ls_idCirculo           = null;
						ls_cedulaCatastral     = null;

						if(
						    (lmss_criterios.get(IdentificadoresCommon.ORIP_MATRICULA) != null)
							    && !lmss_criterios.get(IdentificadoresCommon.ORIP_MATRICULA).isEmpty()
						)
						{
							String ls_tmp;

							ls_tmp = StringUtils.getStringNotNull(
								    lmss_criterios.get(IdentificadoresCommon.ORIP_MATRICULA)
								);

							if(StringUtils.isValidString(ls_tmp))
							{
								CirculoRegistral lcr_cr;
								ls_idCirculo     = ls_tmp;

								lcr_cr = lcrd_DAO.findById(ls_idCirculo);

								if(lcr_cr == null)
								{
									Object[] loa_messageArgs = new String[1];

									loa_messageArgs[0] = ls_idCirculo;

									throw new B2BException(
									    addErrorNP(ErrorKeys.CIRCULO_REGISTRAL_NO_ENCONTRADO, loa_messageArgs)
									);
								}
							}
							else
							{
								Object[] loa_messageArgs = new String[3];

								loa_messageArgs[0]     = ls_tipoServicio;
								loa_messageArgs[1]     = ls_tipoSubServicio;
								loa_messageArgs[2]     = IdentificadoresCommon.ORIP_MATRICULA;

								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO, loa_messageArgs)
								);
							}
						}

						if(
						    (lmss_criterios.get(IdentificadoresCommon.CEDULA_CATASTRAL_GUION_BAJO) != null)
							    && !lmss_criterios.get(IdentificadoresCommon.CEDULA_CATASTRAL_GUION_BAJO).isEmpty()
						)
							ls_cedulaCatastral = StringUtils.getStringNotNull(
								    lmss_criterios.get(IdentificadoresCommon.CEDULA_CATASTRAL_GUION_BAJO)
								);

						if(
						    ((lmss_criterios.get(IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO) != null)
							    && !lmss_criterios.get(IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO).isEmpty())
							    || StringUtils.isValidString(ls_cedulaCatastral)
						)
						{
							String ls_tmp;
							Long   ll_tmp;

							ls_tmp     = StringUtils.getStringNotNull(
								    lmss_criterios.get(IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO)
								);
							ll_tmp     = NumericUtils.getLongWrapper(ls_tmp, -1L);

							if(NumericUtils.isValidLong(ll_tmp))
							{
								if(ls_tmp.length() > 10)
								{
									Object[] loa_messageArgs = new String[3];

									loa_messageArgs[0]     = ls_tipoServicio;
									loa_messageArgs[1]     = ls_tipoSubServicio;
									loa_messageArgs[2]     = IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO;

									throw new B2BException(
									    addErrorNP(
									        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_LONGITUD_INVALIDA,
									        loa_messageArgs
									    )
									);
								}

								ll_idMatricula = ll_tmp;
							}
							else if(!StringUtils.isValidString(ls_cedulaCatastral))
							{
								Object[] loa_messageArgs = new String[3];

								loa_messageArgs[0]     = ls_tipoServicio;
								loa_messageArgs[1]     = ls_tipoSubServicio;
								loa_messageArgs[2]     = IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO;

								throw new B2BException(
								    addErrorNP(ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_INVALIDO, loa_messageArgs)
								);
							}

							if(
							    StringUtils.isValidString(ls_idCirculo)
								    && (NumericUtils.isValidLong(ll_idMatricula)
								    || StringUtils.isValidString(ls_cedulaCatastral))
							)
							{
								{
									PredioRegistro lpr_pr;

									lpr_pr = lprd_DAO.findByCirculoMatricula(
										    ls_idCirculo, NumericUtils.getLong(ll_idMatricula), ls_cedulaCatastral
										);

									if(lpr_pr == null)
									{
										StringBuilder lsb_error;
										Object[]      loa_messageArgs = new String[1];

										lsb_error = new StringBuilder(IdentificadoresCommon.CIRCULO_REGISTRAL);

										lsb_error.append(IdentificadoresCommon.ESPACIO_VACIO);
										lsb_error.append(ls_idCirculo);
										lsb_error.append(IdentificadoresCommon.SIMBOLO_COMA);

										if(NumericUtils.isValidLong(ll_idMatricula))
										{
											lsb_error.append(IdentificadoresCommon.NUMERO_MATRICULA);
											lsb_error.append(IdentificadoresCommon.ESPACIO_VACIO);
											lsb_error.append(ll_idMatricula.toString());
										}

										if(StringUtils.isValidString(ls_cedulaCatastral))
										{
											if(NumericUtils.isValidLong(ll_idMatricula))
												lsb_error.append(IdentificadoresCommon.SIMBOLO_COMA);

											lsb_error.append(IdentificadoresCommon.CEDULA_CATASTRAL);
											lsb_error.append(IdentificadoresCommon.ESPACIO_VACIO);
											lsb_error.append(ls_cedulaCatastral);
											lsb_error.append(IdentificadoresCommon.SIMBOLO_COMA);
										}

										loa_messageArgs[0] = lsb_error.toString();

										throw new B2BException(
										    addErrorNP(ErrorKeys.MATRICULA_INMOBILIARIA_NO_ENCONTRADA, loa_messageArgs)
										);
									}
									else if(atectat_request == null)
									{
										if(
										    !NumericUtils.isValidLong(ll_idMatricula)
											    && StringUtils.isValidString(ls_cedulaCatastral)
										)
										{
											int                li_longitudCriterios;
											TipoCriterioCTSI[] ltc_criteriosNew;

											li_longitudCriterios     = ltctsia_criterios.length + 1;
											ltc_criteriosNew         = new TipoCriterioCTSI[li_longitudCriterios];
											ll_idMatricula           = Long.valueOf(lpr_pr.getIdMatricula());

											for(int li_count = 0; li_count < li_longitudCriterios; li_count++)
											{
												if((li_longitudCriterios - 1) == li_count)
													ltc_criteriosNew[li_count] = new TipoCriterioCTSI(
														    IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO,
														    String.valueOf(ll_idMatricula)
														);
												else
													ltc_criteriosNew[li_count] = ltctsia_criterios[li_count];
											}

											if(CollectionUtils.isValidCollection(ltc_criteriosNew))
												ltsctsi_servicio.setCriterios(ltc_criteriosNew);
										}
									}
								}

								StringBuilder lsb_sb;

								lsb_sb = new StringBuilder();

								lsb_sb.append(ls_idCirculo);
								lsb_sb.append(IdentificadoresCommon.SIMBOLO_GUION);
								lsb_sb.append(ll_idMatricula);

								if(!ls_hashSet.add(lsb_sb.toString()))
								{
									Object[] loa_messageArgs = new String[5];

									loa_messageArgs[0]     = ls_tipoServicio;
									loa_messageArgs[1]     = ls_tipoSubServicio;
									loa_messageArgs[2]     = String.valueOf(li_servicio + 1);
									loa_messageArgs[3]     = IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO;
									loa_messageArgs[4]     = IdentificadoresCommon.ORIP_MATRICULA;

									throw new B2BException(
									    addErrorNP(
									        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_DUPLICADOS, loa_messageArgs
									    )
									);
								}
							}
							else
							{
								if(li_criterios > 0)
								{
									Object[] loa_messageArgs = new String[5];

									loa_messageArgs[0]     = ls_tipoServicio;
									loa_messageArgs[1]     = ls_tipoSubServicio;
									loa_messageArgs[2]     = IdentificadoresCommon.ORIP_MATRICULA;
									loa_messageArgs[3]     = IdentificadoresCommon.NUMERO_MATRICULA_GUION_BAJO;
									loa_messageArgs[4]     = IdentificadoresCommon.CEDULA_CATASTRAL_GUION_BAJO;

									throw new B2BException(
									    addErrorNP(
									        ErrorKeys.ERROR_SERVICIO_SUBSERVICIO_CRITERIO_FORMATO_NO_VALIDO,
									        loa_messageArgs
									    )
									);
								}
							}
						}

						if(!ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_60))
						{
							if(atectat_request == null)
								lrct_return = lpd_DAO.procConsultaTarifa(    //TODO VALIDAR ID_CIRCULO EN PROCEDIMIENTO PARA PROCESO DE CONSULTAS
									    ls_tipoServicio, ls_tipoSubServicio, ls_idCirculo, ll_idMatricula,
									    ls_cedulaCatastral, as_userId, as_localIp
									);
							else
							{
								TipoSalidaConsultarTarifaAlertasTitulares ltsctat_salida;
								BigDecimal                                lbd_valor;

								ltsctat_salida = getGestionAlertasBussines()
										                 .consultarTarifaAlertas(atectat_request, adm_manager);

								if(
								    !ltsctat_salida.getCodigoMensaje()
									                   .equals(
									        TipoSalidaConsultarTarifaAlertasTitularesCodigoMensaje.value1
									    )
								)
									throw new B2BException(ltsctat_salida.getDescripcionMensaje());

								lbd_valor     = new BigDecimal(ltsctat_salida.getTarifaNuevasAlertas());

								lbd_valor     = lbd_valor.divide(
									    new BigDecimal(atectat_request.getCantidadAlertasNuevas())
									);

								lrct_return = new RespuestaConsultaTarifa(lbd_valor, null, null, null, null);
							}
						}
						else
							lrct_return = new RespuestaConsultaTarifa();

						if(lrct_return != null)
						{
							int      li_retorno;
							Object[] loa_messageArgs = new String[2];

							loa_messageArgs[0]     = ls_idCirculo;
							loa_messageArgs[1]     = StringUtils.getString(ll_idMatricula);
							li_retorno             = NumericUtils.getInt(lrct_return.getRetorno());

							switch(li_retorno)
							{
								case 0:
									ltst_respuesta[li_servicio] = new TipoServicioCTSO(
										    ls_tipoServicio, ls_tipoSubServicio, lrct_return.getValorProducto(),
										    lrct_return.getDireccionPredio(), lrct_return.getEstadoPredio()
										);

									break;

								case 90:
								{
									if(
									    StringUtils.isValidString(as_constante)
										    && as_constante.equalsIgnoreCase(
										        ConstanteCommon.CTS_RELACION_PROCESOS_CRITERIOS
										    )
									)
										ltst_respuesta[li_servicio] = new TipoServicioCTSO(
											    ls_tipoServicio, ls_tipoSubServicio, lrct_return.getValorProducto(),
											    lrct_return.getDireccionPredio(), lrct_return.getEstadoPredio()
											);
									else
										throw new B2BException(
										    addErrorNP(
										        ErrorKeys.ERROR_CERTIFICADO_NO_EXPEDIR_150_ANOTACIONES, loa_messageArgs
										    )
										);

									break;
								}

								case -50:
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_CERTIFICADO_NO_EXPEDIR_TRASLADADO, loa_messageArgs)
									);

								case -40:
									throw new B2BException(
									    addErrorNP(ErrorKeys.ERROR_CERTIFICADO_NO_EXPEDIR_ANULADO, loa_messageArgs)
									);

								case -30:
									throw new B2BException(
									    addErrorNP(
									        ErrorKeys.ERROR_CERTIFICADO_NO_EXPEDIR_INCONSISTENTE, loa_messageArgs
									    )
									);

								default:
								{
									if(li_retorno < 0)
										throw new B2BException(lrct_return.getMensaje());

									break;
								}
							}
						}
					}
				}
			}
			else
				throw new B2BException(addErrorNP(ErrorKeys.ERROR_DEBE_EXISTIR_UN_SERVICIO_A_CONSULTAR));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("verificarServicios", lb2be_e);
			throw lb2be_e;
		}

		return ltst_respuesta;
	}
}
