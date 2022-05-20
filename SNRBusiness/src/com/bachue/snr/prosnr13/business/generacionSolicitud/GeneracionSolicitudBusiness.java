package com.bachue.snr.prosnr13.business.generacionSolicitud;

import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESI;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESITipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoCriterioGSI;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoServicioGSI;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSI;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSITipoDocumento;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.security.Digest;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.cuentaCupos.BaseCuentaCupo;

import com.bachue.snr.prosnr01.common.constants.AlertaTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.RepositorioCommon;
import com.bachue.snr.prosnr01.common.constants.TipoArchivoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;
import com.bachue.snr.prosnr01.common.constants.TipoOficinaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.AccEntidadExternaDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDAO;
import com.bachue.snr.prosnr01.dao.acc.ActoDevolucionDineroDAO;
import com.bachue.snr.prosnr01.dao.acc.AlertaTurnoTramiteDAO;
import com.bachue.snr.prosnr01.dao.acc.CuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.acc.DevolucionDineroDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaCorreoElectronicoDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaDireccionDAO;
import com.bachue.snr.prosnr01.dao.acc.PersonaTelefonoDAO;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.UsuarioCuentaCupoDAO;
import com.bachue.snr.prosnr01.dao.pgn.CirculoRegistralDao;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.CodigoError;
import com.bachue.snr.prosnr01.model.devolucion.ActoDevolucionDinero;
import com.bachue.snr.prosnr01.model.devolucion.DevolucionDinero;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTurnoTramite;
import com.bachue.snr.prosnr01.model.sdb.acc.CalidadSolicitante;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacion;
import com.bachue.snr.prosnr01.model.sdb.acc.ModCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaPresentada;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudAsociada;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoEstadoSolicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TrasladoMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoDerivado;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuarioCuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPersona;
import com.bachue.snr.prosnr01.model.sdb.png.CirculoOrigenDestino;

import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;

import com.bachue.snr.prosnr12.common.constants.ErrorKeys;
import com.bachue.snr.prosnr12.common.constants.TipoUsuarioCuentaCupoCommon;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;

import com.google.gson.Gson;

import com.google.gson.internal.LinkedTreeMap;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.security.SecureRandom;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Clase encargada de la logica de negocio del servicio web Generación Solicitud.
 *
 * @author Manuel Blanco
 */
public class GeneracionSolicitudBusiness extends BaseCuentaCupo implements ConstantesCriterioCuentaCupo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GeneracionSolicitudBusiness.class, ProyectosCommon.GENERACION_SOLICITUD_13);

	/**
	 * Consulta el estado de un NIR asociado a una solicitud de cuenta cupo.
	 *
	 * @param ateces_entrada            Objeto contenedor de los parametros a utilizar en la busqueda
	 * @return Objeto contenedor de la respuesta de la operación
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	public synchronized TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(
	    TipoEntradaConsultarEstadoSolicitud ateces_entrada
	)
	    throws B2BException
	{
		TipoSalidaConsultarEstadoSolicitud ltsces_return;
		DAOManager                         ldm_manager;
		CodigoError                        lce_codigoSalida;
		String                             ls_mensajeSalida;

		ltsces_return        = new TipoSalidaConsultarEstadoSolicitud(
			    "", "", "", "", "", "", obtenerCalendarDesdeLocalDateTime(LocalDateTime.now()), null, null
			);
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(ateces_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(ateces_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			String ls_tipoDocumento;
			String ls_numeroDocumento;

			{
				TipoSolicitanteCESI ltscesi_solicitante;

				ltscesi_solicitante = ateces_entrada.getSolicitante();

				if(ltscesi_solicitante != null)
				{
					ls_tipoDocumento       = null;
					ls_numeroDocumento     = ltscesi_solicitante.getNumeroDocumento();

					{
						TipoSolicitanteCESITipoDocumento ltscesitd_tipoDoc;

						ltscesitd_tipoDoc = ltscesi_solicitante.getTipoDocumento();

						if(ltscesitd_tipoDoc != null)
							ls_tipoDocumento = ltscesitd_tipoDoc.getValue();
					}

					validarTipoDocumento(ls_tipoDocumento, lce_codigoSalida, ldm_manager, false);

					validarNumeroDocumento(ls_numeroDocumento);

					validarTipoPersona(ltscesi_solicitante.getTipoPersona(), ldm_manager, false);
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			{
				Solicitud ls_solicitud;
				String    ls_idSolicitud;

				ls_solicitud       = validarNirCuentaCupo(ateces_entrada.getNIR(), lce_codigoSalida, ldm_manager);
				ls_idSolicitud     = ls_solicitud.getIdSolicitud();

				ltsces_return.setNIR(ls_solicitud.getNir());

				{
					CuentaCupoDAO lccd_cuentaCupoDAO;
					CuentaCupo    lcc_cuentaCupo;

					lccd_cuentaCupoDAO     = DaoCreator.getCuentaCupoDAO(ldm_manager);
					lcc_cuentaCupo         = lccd_cuentaCupoDAO.findByIdSolicitud(ls_idSolicitud);

					if(lcc_cuentaCupo == null)
					{
						ModCuentaCupo lmcc_modCuentaCupo;

						lmcc_modCuentaCupo = DaoCreator.getModCuentaCupoDAO(ldm_manager)
								                           .findByIdSolicitud(ls_idSolicitud);

						if(lmcc_modCuentaCupo != null)
							lcc_cuentaCupo = lccd_cuentaCupoDAO.findByIdSolicitud(
								    lmcc_modCuentaCupo.getIdSolicitudCreacion()
								);
						else
						{
							lce_codigoSalida.setCodigoError(BigInteger.valueOf(412L));

							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_CUENTA_CUPOS));
						}
					}

					if(lcc_cuentaCupo != null)
					{
						UsuarioCuentaCupo lucc_usuario;

						lucc_usuario = DaoCreator.getUsuarioCuentaCupoDAO(ldm_manager)
								                     .findByIdCuentaAndDoc(
								    lcc_cuentaCupo.getIdCuentaCupo(), ls_tipoDocumento, ls_numeroDocumento
								);

						if(
						    (lucc_usuario == null)
							    || !StringUtils.getStringNotNull(lucc_usuario.getTipoUsuario())
							                       .equals(TipoUsuarioCuentaCupoCommon.ADMINISTRADOR)
						)
						{
							Object[] loa_args;

							loa_args     = new String[2];

							loa_args[0]     = ls_tipoDocumento;
							loa_args[1]     = ls_numeroDocumento;

							throw new B2BException(
							    addMessageGCC(ErrorKeys.ERROR_USUARIO_NO_ADMIN_O_NO_EXISTE, loa_args)
							);
						}
					}
					else
					{
						lce_codigoSalida.setCodigoError(BigInteger.valueOf(412L));

						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_CUENTA_CUPOS));
					}
				}

				{
					TurnoHistoria lth_turnoHistoria;
					long          ll_idEtapa;

					lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(ldm_manager).findLastBySolicitud(ls_idSolicitud);

					if(lth_turnoHistoria == null)
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_CUENTA_CUPOS));

					{
						BigDecimal lbd_idEtapa;
						Etapa      le_etapa;

						lbd_idEtapa     = lth_turnoHistoria.getIdEtapa();
						ll_idEtapa      = NumericUtils.isValidBigDecimal(lbd_idEtapa)
							? NumericUtils.getLong(lbd_idEtapa) : 0L;

						le_etapa = DaoCreator.getEtapaDAO(ldm_manager).findById(ll_idEtapa);

						if(le_etapa != null)
						{
							ltsces_return.setIdEtapa(StringUtils.getString(le_etapa.getIdEtapa()));
							ltsces_return.setNombreEtapa(le_etapa.getNombre());
						}
						else
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_CUENTA_CUPOS));
					}

					{
						MotivoTramite lmt_motivoTramite;
						String        ls_idMotivo;
						String        ls_descripcionMotivo;

						lmt_motivoTramite        = DaoCreator.getMotivoTramiteDAO(ldm_manager)
								                                 .findById(
								    ll_idEtapa, NumericUtils.getLong(lth_turnoHistoria.getIdMotivo())
								);
						ls_idMotivo              = "";
						ls_descripcionMotivo     = "";

						if(lmt_motivoTramite != null)
						{
							ls_idMotivo              = StringUtils.getString(lmt_motivoTramite.getIdMotivo());
							ls_descripcionMotivo     = lmt_motivoTramite.getDescripcion();
						}

						ltsces_return.setIdMotivo(ls_idMotivo);
						ltsces_return.setDescripcionMotivo(ls_descripcionMotivo);
					}

					{
						String ls_estadoActividad;

						ls_estadoActividad = StringUtils.getStringNotNull(lth_turnoHistoria.getEstadoActividad());

						ltsces_return.setEstado(
						    ls_estadoActividad.equals(EstadoCommon.ASIGNADA) ? EstadoCommon.ACTIVO_TXT
						                                                     : EstadoCommon.INACTIVO_TXT
						);

						if(ls_estadoActividad.equals(EstadoCommon.TERMINADA))
						{
							Calendar lc_fechaFin;

							lc_fechaFin = obtenerCalendarDesdeDate(lth_turnoHistoria.getFechaModificacion());

							if(lc_fechaFin != null)
								ltsces_return.setFechaFin(lc_fechaFin);
						}
					}
				}
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			ls_mensajeSalida = addMessage(MessagesKeys.OK);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarEstadoSolicitud", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarEstadoSolicitud", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		ltsces_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltsces_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltsces_return;
	}

	/**
	 * Consulta los usuarios de una cuenta cupo.
	 *
	 * @param ategs_entrada            Objeto contenedor de la información del usuario a consultar
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return TipoSalidaConsultarUsuarios con la respuesta de la operación
	 * @throws B2BException             Si ocurre un error en la base de datos
	 */
	public synchronized TipoSalidaGenerarSolicitud generarSolicitud(
	    TipoEntradaGenerarSolicitud ategs_entrada, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaGenerarSolicitud ltscu_return;
		DAOManager                 ldm_manager;
		CodigoError                lce_codigoSalida;
		String                     ls_mensajeSalida;
		String                     ls_nir;

		ls_nir               = null;
		ltscu_return         = new TipoSalidaGenerarSolicitud("", "", "", "", null, null);
		ldm_manager          = DaoManagerFactory.getDAOManager();
		lce_codigoSalida     = new CodigoError();
		ls_mensajeSalida     = "";

		try
		{
			if(ategs_entrada == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(!validarModuloSE(ategs_entrada.getModulo()))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MODULO_NO_VALIDO));

			{
				TipoServicioGSI[] ltsgsia_servicios;

				ltsgsia_servicios = ategs_entrada.getServicios();

				if(CollectionUtils.isValidCollection(ltsgsia_servicios))
				{
					TipoSolicitanteGSI ltsgsi_solicitante;
					String             ls_tipoDocumento;
					String             ls_numeroDocumento;
					String             ls_tipoPersona;

					ltsgsi_solicitante = ategs_entrada.getSolicitante();

					if(ltsgsi_solicitante != null)
					{
						TipoSolicitanteGSITipoDocumento ltsgsitd_tipoDoc;

						ltsgsitd_tipoDoc = ltsgsi_solicitante.getTipoDocumento();

						if(ltsgsitd_tipoDoc == null)
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

						ls_tipoDocumento       = ltsgsitd_tipoDoc.getValue();
						ls_numeroDocumento     = ltsgsi_solicitante.getNumeroDocumento();
						ls_tipoPersona         = ltsgsi_solicitante.getTipoPersona();
					}
					else
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

					for(
					    int li_numeroTipoServicio = 0, li_cantidadServicios = ltsgsia_servicios.length;
						    li_numeroTipoServicio < li_cantidadServicios; li_numeroTipoServicio++
					)
					{
						TipoServicioGSI ltsgsi_tipoServicio;

						ltsgsi_tipoServicio = ltsgsia_servicios[li_numeroTipoServicio];

						if(ltsgsi_tipoServicio != null)
						{
							String ls_tipoServicio;
							String ls_subproceso;

							ls_tipoServicio     = ltsgsi_tipoServicio.getTipoServicio();
							ls_subproceso       = StringUtils.getStringNotNull(
								    ltsgsi_tipoServicio.getSubtipoServicio()
								);

							{
								if(!StringUtils.isValidString(ls_tipoServicio))
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TIPO_SERVICIO));

								if(
								    !(ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_4)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_38)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_39)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_45)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_47)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_60)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_40)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_41)
									    || ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_70))
								)
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_SERVICIO_NO_VALIDO));

								if(!StringUtils.isValidString(ls_subproceso))
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TIPO_SUBSERVICIO));
							}

							{
								TipoCriterioGSI[] ltcgsi_criterios;
								boolean           lb_solicitanteNitObligatorio;

								ltcgsi_criterios     = ltsgsi_tipoServicio.getCriterios();

								lb_solicitanteNitObligatorio = ls_tipoServicio.equals(ProcesoCommon.ID_PROCESO_60)
										&& ls_subproceso.equals(ProcesoCommon.ID_SUBPROCESO_1);

								validarTipoDocumento(
								    ls_tipoDocumento, lce_codigoSalida, ldm_manager, lb_solicitanteNitObligatorio
								);

								validarNumeroDocumento(ls_numeroDocumento);

								validarTipoPersona(ls_tipoPersona, ldm_manager, lb_solicitanteNitObligatorio);

								switch(ls_tipoServicio)
								{
									case ProcesoCommon.ID_PROCESO_60:
									{
										Collection<String>  lcs_codigosObligatorios;
										Map<String, String> lmss_valoresIngresados;
										Map<String, String> lmss_llavesValor;

										lmss_valoresIngresados = new LinkedHashMap<String, String>();

										switch(ls_subproceso)
										{
											case ProcesoCommon.ID_SUBPROCESO_1:
											{
												lcs_codigosObligatorios = obtenerConstantesCreacion(false);

												{
													Collection<String> lcs_camposCreacion;

													lcs_camposCreacion = obtenerConstantesCreacion(true);

													// FORMATO Comentar lambda antes de formatear
													lcs_camposCreacion
															.forEach(ls_llave -> lmss_valoresIngresados.put(ls_llave, null));
												}

												lmss_llavesValor = procesarCriteriosCuentaCupo(
													    ltcgsi_criterios, lmss_valoresIngresados,
													    lcs_codigosObligatorios
													);

												{
													ls_nir = crearCuentaCupo(
														    lmss_llavesValor, lce_codigoSalida, ldm_manager, as_userId,
														    as_remoteIp
														);

													ltscu_return.setNIR(ls_nir);
													ltscu_return.setMensajeAlSolicitante("");
												}

												break;
											}

											case ProcesoCommon.ID_SUBPROCESO_2:
											{
												lcs_codigosObligatorios = obtenerConstantesModificacion();

												// FORMATO Comentar lambda antes de formatear
												lcs_codigosObligatorios
														.forEach(ls_llave -> lmss_valoresIngresados.put(ls_llave, null));
												lmss_llavesValor = procesarCriteriosCuentaCupo(
													    ltcgsi_criterios, lmss_valoresIngresados,
													    lcs_codigosObligatorios
													);

												{
													ls_nir = modificarCuentaCupo(
														    lmss_llavesValor, ls_tipoDocumento, ls_numeroDocumento,
														    lce_codigoSalida, ldm_manager, as_userId, as_remoteIp
														);

													ltscu_return.setNIR(ls_nir);
													ltscu_return.setMensajeAlSolicitante("");
												}

												break;
											}

											case ProcesoCommon.ID_SUBPROCESO_3:
											{
												lcs_codigosObligatorios = obtenerConstantesCancelacion();

												// FORMATO Comentar lambda antes de formatear
												lcs_codigosObligatorios
														.forEach(ls_llave -> lmss_valoresIngresados.put(ls_llave, null));
												lmss_llavesValor = procesarCriteriosCuentaCupo(
													    ltcgsi_criterios, lmss_valoresIngresados,
													    lcs_codigosObligatorios
													);

												{
													ls_nir = cancelarCuentaCupo(
														    lmss_llavesValor, ls_tipoDocumento, ls_numeroDocumento,
														    lce_codigoSalida, ldm_manager, as_userId, as_remoteIp
														);

													ltscu_return.setNIR(ls_nir);
													ltscu_return.setMensajeAlSolicitante("");
												}
												break;
											}

											default:
												throw new B2BException(
												    addMessageGCC(ErrorKeys.ERROR_TIPO_SUBSERVICIO_NO_VALIDO)
												);
										}

										break;
									}

									case ProcesoCommon.ID_PROCESO_4:
									case ProcesoCommon.ID_PROCESO_38:
									case ProcesoCommon.ID_PROCESO_39:
									case ProcesoCommon.ID_PROCESO_40:
									case ProcesoCommon.ID_PROCESO_41:
									case ProcesoCommon.ID_PROCESO_45:
									case ProcesoCommon.ID_PROCESO_47:
									{
										if(
										    (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
											    && (ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
											    || ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
											    || ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
											    && ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39)
											    && ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_6))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40)
											    && ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41)
											    && ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_45)
											    && ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_6))
											    || (ls_tipoServicio.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
											    && (ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
											    || ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
											    || ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
											    || ls_subproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)))
										)
										{
											Map<String, String> lmss_resultado;

											lmss_resultado = generarSolicitudRegistro(
												    ltcgsi_criterios,
												    new Persona(ls_tipoDocumento, ls_numeroDocumento, ls_tipoPersona),
												    ls_tipoServicio, ls_subproceso, lce_codigoSalida, ldm_manager,
												    as_userId, as_remoteIp
												);

											if(CollectionUtils.isValidCollection(lmss_resultado))
											{
												ls_nir = lmss_resultado.get(IdentificadoresCommon.NIR);
												ltscu_return.setNIR(ls_nir);
												ltscu_return.setMensajeAlSolicitante(
												    StringUtils.getStringNotNull(
												        lmss_resultado.get(IdentificadoresCommon.DESCRIPCION_MENSAJE)
												    )
												);
											}

											break;
										}
										else
											throw new B2BException(
											    addMessageGCC(ErrorKeys.ERROR_TIPO_SUBSERVICIO_NO_VALIDO)
											);
									}

									case ProcesoCommon.ID_PROCESO_70:
									{
										switch(ls_subproceso)
										{
											case ProcesoCommon.ID_SUBPROCESO_1:
											{
												ls_nir = generarSolicitudAlertaTierras(
													    ltcgsi_criterios,
													    new Persona(
													        ls_tipoDocumento, ls_numeroDocumento, ls_tipoPersona
													    ), lce_codigoSalida, ldm_manager, as_userId, as_remoteIp
													);

												ltscu_return.setNIR(ls_nir);
												ltscu_return.setMensajeAlSolicitante("");

												break;
											}

											default:
												throw new B2BException(
												    addMessageGCC(ErrorKeys.ERROR_TIPO_SUBSERVICIO_NO_VALIDO)
												);
										}

										break;
									}

									default:
										throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_SERVICIO_NO_VALIDO));
								}
							}
						}
					}
				}
				else
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));
			}

			lce_codigoSalida.setCodigoError(BigInteger.valueOf(200L));

			{
				String ls_mensajeInformativo;

				ls_mensajeInformativo = lce_codigoSalida.getMensajeInformativo();

				if(StringUtils.isValidString(ls_mensajeInformativo))
					ls_mensajeSalida = ls_mensajeInformativo;
				else
					ls_mensajeSalida = addMessage(MessagesKeys.OK);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitud", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(Exception le_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitud", le_e);

			ls_mensajeSalida = le_e.getLocalizedMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(500L));
		}
		finally
		{
			ldm_manager.close();
		}

		DAOManager ldm_manager2;

		ldm_manager2 = DaoManagerFactory.getDAOManager();

		try
		{
			Thread.sleep(5500);

			if(StringUtils.isValidString(ls_nir))
			{
				Solicitud ls_solicitudTMP;
				ls_solicitudTMP = DaoCreator.getSolicitudDAO(ldm_manager2).findByNir(ls_nir);

				if(ls_solicitudTMP != null)
				{
					String ls_idTipoEstadoSolicitud = StringUtils.getString(ls_solicitudTMP.getEstadoSolicitud());

					if(StringUtils.isValidString(ls_idTipoEstadoSolicitud))
					{
						TipoEstadoSolicitud ltes_tipoEstadoSolicitud;
						ltes_tipoEstadoSolicitud = new TipoEstadoSolicitud();

						ltes_tipoEstadoSolicitud.setIdTipoEstadoSolicitud(ls_idTipoEstadoSolicitud);

						ltes_tipoEstadoSolicitud = DaoCreator.getTipoEstadoSolicitudDAO(ldm_manager2)
								                                 .findById(ltes_tipoEstadoSolicitud);

						if(ltes_tipoEstadoSolicitud != null)
						{
							ltscu_return.setCodigoEstadoSolicitud(ltes_tipoEstadoSolicitud.getIdTipoEstadoSolicitud());
							ltscu_return.setDescripcionEstadoSolicitud(ltes_tipoEstadoSolicitud.getNombre());
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitud", lb2be_e);

			ls_mensajeSalida = lb2be_e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		catch(InterruptedException e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarSolicitud", e);

			ls_mensajeSalida = e.getMessage();

			if(!NumericUtils.isValidBigInteger(lce_codigoSalida.getCodigoError()))
				lce_codigoSalida.setCodigoError(BigInteger.valueOf(400L));
		}
		finally
		{
			ldm_manager2.close();
		}

		ltscu_return.setCodigoMensaje(lce_codigoSalida.getCodigoError());
		ltscu_return.setDescripcionMensaje(ls_mensajeSalida);

		return ltscu_return;
	}

	/**
	 * Crea una solicitud de modificación de cuenta cupo.
	 *
	 * @param amss_llavesValor            Objeto contenedor de los valores a modificar
	 * @param as_tipoDocumento            tipo de documento de la persona solicitante
	 * @param as_numeroDocumento            numero de documento de la persona solicitante
	 * @param ace_codigoSalida            Objeto contenedor del código de respuesta de la operación
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejectua la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return el valor de string
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private String cancelarCuentaCupo(
	    Map<String, String> amss_llavesValor, String as_tipoDocumento, String as_numeroDocumento,
	    CodigoError ace_codigoSalida, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		String        ls_nir;
		String        ls_idSolicitud;
		Solicitud     ls_solicitudCreacion;
		ModCuentaCupo lmcc_modCuentaCupo;

		lmcc_modCuentaCupo = new ModCuentaCupo();

		{
			String     ls_idCuentaCupo;
			CuentaCupo lcc_cuentaCupo;

			ls_idCuentaCupo     = amss_llavesValor.get(ConstantesCriterioCuentaCupo.ID_CUENTA_CUPO);
			lcc_cuentaCupo      = validarCuentaCupo(ls_idCuentaCupo, ace_codigoSalida, adm_manager);

			ls_solicitudCreacion = DaoCreator.getSolicitudDAO(adm_manager).findById(lcc_cuentaCupo.getIdSolicitud());

			if(ls_solicitudCreacion == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_SOLICITUD_CREACION));

			validarUsuarioCuentaCupo(
			    as_tipoDocumento, as_numeroDocumento, ls_idCuentaCupo, ace_codigoSalida, adm_manager, true
			);

			lmcc_modCuentaCupo.setIdCuentaCupo(lcc_cuentaCupo.getIdCuentaCupo());
			lmcc_modCuentaCupo.setIdSolicitudCreacion(ls_solicitudCreacion.getIdSolicitud());
		}

		{
			Solicitud ls_solicitud;

			ls_solicitud     = crearSolicitudCuentaCupo(
				    ls_solicitudCreacion.getIdPersona(),
				    NumericUtils.getLongWrapper(ls_solicitudCreacion.getIdTelefono()),
				    NumericUtils.getLongWrapper(ls_solicitudCreacion.getIdCorreoElectronico()), adm_manager,
				    ProcesoCommon.ID_SUBPROCESO_3, amss_llavesValor.get(
				        ConstantesCriterioCuentaCupo.MOTIVO_CANCELACION
				    ), as_userId, as_remoteIp
				);

			ls_idSolicitud     = ls_solicitud.getIdSolicitud();
			ls_nir             = ls_solicitud.getNir();

			lmcc_modCuentaCupo.setIdSolicitud(ls_idSolicitud);
		}

		lmcc_modCuentaCupo.setValorMaximo(BigDecimal.ZERO);
		lmcc_modCuentaCupo.setValorMinimo(BigDecimal.ZERO);
		lmcc_modCuentaCupo.setEstado(EstadoCommon.EN_PROCESO_TXT);
		lmcc_modCuentaCupo.setIdUsuarioCreacion(as_userId);
		lmcc_modCuentaCupo.setIpCreacion(as_remoteIp);

		DaoCreator.getModCuentaCupoDAO(adm_manager).insert(lmcc_modCuentaCupo);

		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
			lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
			lth_turnoHistoria.setIpModificacion(as_remoteIp);

			DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
		}

		return ls_nir;
	}

	/**
	 * Consultar usuario cuenta cupo by datos.
	 *
	 * @param ap_persona de ap persona
	 * @param adm_manager de adm manager
	 * @return el valor de usuario cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private UsuarioCuentaCupo consultarUsuarioCuentaCupoByDatos(Persona ap_persona, DAOManager adm_manager)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_usuarioCuentaCupo;

		lucc_usuarioCuentaCupo = null;

		if(ap_persona != null)
		{
			try
			{
				String ls_idTipoPersona;

				ls_idTipoPersona = ap_persona.getIdTipoPersona();

				if(StringUtils.isValidString(ls_idTipoPersona))
				{
					UsuarioCuentaCupoDAO lucc_DAO;

					lucc_DAO = DaoCreator.getUsuarioCuentaCupoDAO(adm_manager);

					if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.N))
						lucc_usuarioCuentaCupo = lucc_DAO.findAdminByTipoDocNumDocNombres(ap_persona);
					else if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.J))
						lucc_usuarioCuentaCupo = lucc_DAO.findAdminByTipoDocNumDocRazonSocial(ap_persona);
				}
			}

			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("consultarUsuarioCuentaCupoByDatos", lb2be_e);

				throw lb2be_e;
			}
		}

		return lucc_usuarioCuentaCupo;
	}

	/**
	 * Contiene.
	 *
	 * @param aai_enteros correspondiente al valor de token generados previamente
	 * @param ai_token correspondiente al valor de token actual
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	private boolean contiene(int[] aai_enteros, int ai_token)
	{
		boolean lb_contiene;

		lb_contiene = false;

		if(ai_token == -1)
			lb_contiene = true;
		else if(aai_enteros != null)
		{
			for(int li_indice = 0, li_tamano = aai_enteros.length; (li_indice < li_tamano) && !lb_contiene;
				    li_indice++
			)
				lb_contiene = aai_enteros[li_indice] == ai_token;
		}

		return lb_contiene;
	}

	/**
	 * Realiza las inserciones de información necesarias para la creación de la
	 * cuenta cupo.
	 *
	 * @param amss_llavesValor            Objeto contenedor de la información del proceso
	 * @param ace_codigoSalida            Objeto contenedor del código de salida
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return el valor de string
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private String crearCuentaCupo(
	    Map<String, String> amss_llavesValor, CodigoError ace_codigoSalida, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(amss_llavesValor))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		Persona lp_persona;
		String  ls_idSolicitud;
		String  ls_correoElectronicoRepresentante;
		String  ls_nir;
		long    ll_idEntidadExterna;

		lp_persona                            = insertarRepresentante(
			    amss_llavesValor, ace_codigoSalida, adm_manager, as_userId, as_remoteIp
			);
		ls_correoElectronicoRepresentante     = amss_llavesValor.get(
			    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_CORREO_ELECTRONICO
			);

		{
			String ls_idPersona;
			long   ll_idTelefono;
			long   ll_idCorreoElectronico;

			ls_idPersona = lp_persona.getIdPersona();

			{
				String ls_tipoDocumento;
				String ls_numeroDocumento;

				ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();
				ls_numeroDocumento     = lp_persona.getNumeroDocumento();

				ll_idTelefono = insertarTelefonoCelular(
					    amss_llavesValor.get(ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_CELULAR), ls_tipoDocumento,
					    ls_numeroDocumento, ls_idPersona, adm_manager, as_userId, as_remoteIp
					);
			}

			ll_idCorreoElectronico = insertarCorreoElectronico(
				    ls_correoElectronicoRepresentante, ls_idPersona, adm_manager, as_userId, as_remoteIp
				);

			{
				Solicitud ls_solicitud;

				ls_solicitud     = crearSolicitudCuentaCupo(
					    ls_idPersona, NumericUtils.getLongWrapper(ll_idTelefono),
					    NumericUtils.getLongWrapper(ll_idCorreoElectronico), adm_manager, ProcesoCommon.ID_SUBPROCESO_1,
					    null, as_userId, as_remoteIp
					);

				ls_idSolicitud     = ls_solicitud.getIdSolicitud();
				ls_nir             = ls_solicitud.getNir();
			}

			ll_idEntidadExterna = insertarEntidadExterna(
				    amss_llavesValor, ace_codigoSalida, ls_idPersona, adm_manager, as_userId, as_remoteIp
				);
		}

		{
			long ll_idCuentaCupo;

			ll_idCuentaCupo = insertarCuentaCupo(
				    amss_llavesValor, ls_idSolicitud, ll_idEntidadExterna, adm_manager, as_userId, as_remoteIp
				);

			{
				UsuarioCuentaCupoDAO luccd_usuarioCuentaCupoDAO;
				UsuarioCuentaCupo    lucc_usuario;

				luccd_usuarioCuentaCupoDAO = DaoCreator.getUsuarioCuentaCupoDAO(adm_manager);

				{
					UsuarioCuentaCupo lucc_usuarioCuentaCupo;

					lucc_usuarioCuentaCupo = luccd_usuarioCuentaCupoDAO.findByCorreoElectronico(
						    ls_correoElectronicoRepresentante
						);

					if(lucc_usuarioCuentaCupo != null)
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_USUARIO_YA_EXISTENTE));
				}

				lucc_usuario = new UsuarioCuentaCupo(lp_persona);

				lucc_usuario.setIdCuentaCupo(StringUtils.getString(ll_idCuentaCupo));
				lucc_usuario.setCorreoElectronico(ls_correoElectronicoRepresentante);
				lucc_usuario.setEstado(EstadoCommon.A);
				lucc_usuario.setTipoUsuario(TipoUsuarioCuentaCupoCommon.ADMINISTRADOR);
				lucc_usuario.setIdUsuarioCreacion(as_userId);
				lucc_usuario.setIpCreacion(as_remoteIp);

				luccd_usuarioCuentaCupoDAO.insert(lucc_usuario);
			}
		}

		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
			lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
			lth_turnoHistoria.setIpModificacion(as_remoteIp);

			DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
		}

		return ls_nir;
	}

	/**
	 * Crea el registro de la solicitud para determinado subproceso de cuenta cupos.
	 *
	 * @param as_idPersona            id de la persona representante de la cuenta cupos
	 * @param al_idTelefono            id del teléfono del representante
	 * @param al_idCorreoElectronico            id del correo electrónico del representante
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_subproceso            identificador del subproceso de cuenta cupos
	 * @param as_observaciones de as observaciones
	 * @param as_userId            id del usuario que ejecuta la acción
	 * @param as_remoteIp            dirección IP del cliente que ejecuta la acción
	 * @return id de la solicitud generada
	 * @throws B2BException             Si ocurre un error en base de datos
	 */
	private Solicitud crearSolicitudCuentaCupo(
	    String as_idPersona, Long al_idTelefono, Long al_idCorreoElectronico, DAOManager adm_manager,
	    String as_subproceso, String as_observaciones, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		SolicitudDAO lsd_solicitudDAO;
		Solicitud    ls_solicitud;
		String       ls_idSolicitud;

		lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(adm_manager);
		ls_solicitud         = new Solicitud();

		{
			int li_idSolicitud;

			li_idSolicitud     = lsd_solicitudDAO.findSecuence();

			ls_idSolicitud = StringUtils.getString(li_idSolicitud);
		}

		ls_solicitud.setIdSolicitud(ls_idSolicitud);
		ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_60);
		ls_solicitud.setIdSubproceso(as_subproceso);
		ls_solicitud.setIdPersona(as_idPersona);
		ls_solicitud.setIdTelefono(NumericUtils.isValidLong(al_idTelefono, 1L) ? al_idTelefono.toString() : null);
		ls_solicitud.setIdCorreoElectronico(
		    NumericUtils.isValidLong(al_idCorreoElectronico, 1L) ? al_idCorreoElectronico.toString() : null
		);
		ls_solicitud.setDerechoPeticion(EstadoCommon.N);
		ls_solicitud.setParticipaInterviniente(EstadoCommon.S);
		ls_solicitud.setDigitalizada(EstadoCommon.S);
		ls_solicitud.setComentario(as_observaciones);
		ls_solicitud.setFechaSolicitud(new Date());
		ls_solicitud.setIdUsuarioCreacion(as_userId);
		ls_solicitud.setIpCreacion(as_remoteIp);

		{
			String ls_nir;

			ls_nir = crearNir(as_userId, as_remoteIp, adm_manager);

			if(!StringUtils.isValidString(ls_nir))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_GENERANDO_NIR));

			ls_solicitud.setNir(ls_nir);
		}

		lsd_solicitudDAO.insertOrUpdate(ls_solicitud, true);

		return ls_solicitud;
	}

	/**
	 * Extraer id persona.
	 *
	 * @param ap_persona de ap persona
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String extraerIdPersona(Persona ap_persona, DAOManager adm_manager)
	    throws B2BException
	{
		String ls_idPersona;

		ls_idPersona = null;

		if(ap_persona != null)
		{
			try
			{
				Collection<Persona> lcp_personasConsultadas;

				lcp_personasConsultadas = DaoCreator.getPersonaDAO(adm_manager)
						                                .findByDocumentoAndTipoDocumento(
						    ap_persona.getNumeroDocumento(), ap_persona.getIdDocumentoTipo(), true
						);

				if(CollectionUtils.isValidCollection(lcp_personasConsultadas))
				{
					String ls_idTipoPersona;

					ls_idTipoPersona = ap_persona.getIdTipoPersona();

					if(StringUtils.isValidString(ls_idTipoPersona))
					{
						for(Persona lp_personaConsultada : lcp_personasConsultadas)
						{
							if((lp_personaConsultada != null) && !StringUtils.isValidString(ls_idPersona))
							{
								if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.N))
								{
									String ls_primerNombrePersonaConsultada;
									String ls_primerApellidoPersonaConsultada;

									ls_primerNombrePersonaConsultada       = lp_personaConsultada.getPrimerNombre();
									ls_primerApellidoPersonaConsultada     = lp_personaConsultada.getPrimerApellido();

									if(
									    StringUtils.isValidString(ls_primerNombrePersonaConsultada)
										    && ls_primerNombrePersonaConsultada.equalsIgnoreCase(
										        ap_persona.getPrimerNombre()
										    ) && StringUtils.isValidString(ls_primerApellidoPersonaConsultada)
										    && ls_primerApellidoPersonaConsultada.equalsIgnoreCase(
										        ap_persona.getPrimerApellido()
										    )
									)
									{
										boolean lb_validoTemporal;

										lb_validoTemporal = true;

										if(StringUtils.isValidString(ap_persona.getSegundoNombre()))
										{
											String ls_segundoNombrePersonaConsultada;

											ls_segundoNombrePersonaConsultada = lp_personaConsultada.getSegundoNombre();

											if(
											    !(StringUtils.isValidString(ls_segundoNombrePersonaConsultada)
												    && ls_segundoNombrePersonaConsultada.equalsIgnoreCase(
												        ap_persona.getSegundoNombre()
												    ))
											)
												lb_validoTemporal = false;
										}

										if(
										    StringUtils.isValidString(ap_persona.getSegundoApellido())
											    && lb_validoTemporal
										)
										{
											String ls_segundoApellidoPersonaConsultada;

											ls_segundoApellidoPersonaConsultada = lp_personaConsultada
													.getSegundoApellido();

											if(
											    !(StringUtils.isValidString(ls_segundoApellidoPersonaConsultada)
												    && ls_segundoApellidoPersonaConsultada.equalsIgnoreCase(
												        ap_persona.getSegundoApellido()
												    ))
											)
												lb_validoTemporal = false;
										}

										if(lb_validoTemporal)
											ls_idPersona = lp_personaConsultada.getIdPersona();
									}
								}
								else if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.J))
								{
									String ls_razonSocialPersonaConsultada;

									ls_razonSocialPersonaConsultada = lp_personaConsultada.getRazonSocial();

									if(
									    StringUtils.isValidString(ls_razonSocialPersonaConsultada)
										    && ls_razonSocialPersonaConsultada.equalsIgnoreCase(
										        ap_persona.getRazonSocial()
										    )
									)
										ls_idPersona = lp_personaConsultada.getIdPersona();
								}
							}
						}
					}
				}
			}

			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("extraerIdPersona", lb2be_e);

				throw lb2be_e;
			}
		}

		return ls_idPersona;
	}

	/**
	 * Generar solicitud alerta tierras.
	 *
	 * @param atcgsi_criterios de atcgsi criterios
	 * @param ap_persona de ap persona
	 * @param ace_codigoSalida de ace codigo salida
	 * @param adm_manager de adm manager
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private String generarSolicitudAlertaTierras(
	    TipoCriterioGSI[] atcgsi_criterios, Persona ap_persona, CodigoError ace_codigoSalida, DAOManager adm_manager,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		String ls_nir;

		ls_nir = null;

		try
		{
			if(!CollectionUtils.isValidCollection(atcgsi_criterios))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(ap_persona == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_PERSONA_SOLICITUD));

			String ls_primerNombre;
			String ls_segundoNombre;
			String ls_primerApellido;
			String ls_segundoApellido;
			String ls_razonSocial;
			String ls_correoElectronico;

			ls_primerNombre          = null;
			ls_segundoNombre         = null;
			ls_primerApellido        = null;
			ls_segundoApellido       = null;
			ls_razonSocial           = null;
			ls_correoElectronico     = null;

			for(
			    int li_posicion = 0, li_tamanioCriterios = atcgsi_criterios.length; li_posicion < li_tamanioCriterios;
				    li_posicion++
			)
			{
				TipoCriterioGSI ltcgsi_criterio;

				ltcgsi_criterio = atcgsi_criterios[li_posicion];

				if(ltcgsi_criterio != null)
				{
					String ls_codigo;

					ls_codigo = StringUtils.getStringNotNull(ltcgsi_criterio.getCodigo());

					switch(ls_codigo)
					{
						case IdentificadoresCommon.PRIMER_NOMBRE_SOLICITANTE:
							ls_primerNombre = ltcgsi_criterio.getValor();

							break;

						case IdentificadoresCommon.SEGUNDO_NOMBRE_SOLICITANTE:
							ls_segundoNombre = ltcgsi_criterio.getValor();

							break;

						case IdentificadoresCommon.PRIMER_APELLIDO_SOLICITANTE:
							ls_primerApellido = ltcgsi_criterio.getValor();

							break;

						case IdentificadoresCommon.SEGUNDO_APELLIDO_SOLICITANTE:
							ls_segundoApellido = ltcgsi_criterio.getValor();

							break;

						case IdentificadoresCommon.RAZON_SOCIAL:
							ls_razonSocial = ltcgsi_criterio.getValor();

							break;

						case IdentificadoresCommon.CORREO_ELECTRONICO:
							ls_correoElectronico = ltcgsi_criterio.getValor();

							break;

						default:
							break;
					}
				}
			}

			if(!validarCorreoElectronico(ls_correoElectronico))
			{
				ace_codigoSalida.setCodigoError(BigInteger.valueOf(525L));
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_ELECTRONICO_NO_VALIDO));
			}

			String ls_idPersona;

			ls_idPersona = null;

			{
				String ls_idTipoPersona;

				ls_idTipoPersona = ap_persona.getIdTipoPersona();

				if(StringUtils.isValidString(ls_idTipoPersona))
				{
					if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.N))
					{
						if(!StringUtils.isValidString(ls_primerNombre))
							propagarErrorCampoObligatorio(
							    ace_codigoSalida, IdentificadoresCommon.PRIMER_NOMBRE_SOLICITANTE
							);

						if(!StringUtils.isValidString(ls_primerApellido))
							propagarErrorCampoObligatorio(
							    ace_codigoSalida, IdentificadoresCommon.PRIMER_APELLIDO_SOLICITANTE
							);

						ap_persona.setPrimerNombre(ls_primerNombre);
						ap_persona.setSegundoNombre(ls_segundoNombre);
						ap_persona.setPrimerApellido(ls_primerApellido);
						ap_persona.setSegundoApellido(ls_segundoApellido);
					}
					else if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.J))
					{
						if(StringUtils.isValidString(ls_razonSocial))
							ap_persona.setRazonSocial(ls_razonSocial);
						else
							propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.RAZON_SOCIAL);
					}
					else
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_PERSONA_SOLICITUD));
				}

				{
					ls_idPersona = extraerIdPersona(ap_persona, adm_manager);

					if(!StringUtils.isValidString(ls_idPersona))
					{
						ap_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						ap_persona.setIdUsuarioCreacion(as_userId);
						ap_persona.setIpCreacion(as_userId);

						ap_persona     = DaoCreator.getPersonaDAO(adm_manager).insertOrUpdate(ap_persona, true);

						ls_idPersona = ap_persona.getIdPersona();
					}
				}
			}

			long ll_idCorreoElectronico;

			{
				PersonaCorreoElectronico lpce_personaCorreoElectronico;

				lpce_personaCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
						                                      .findByIdPersonaCorreoMax(
						    ls_idPersona, ls_correoElectronico
						);

				if(lpce_personaCorreoElectronico != null)
					ll_idCorreoElectronico = NumericUtils.getLong(
						    lpce_personaCorreoElectronico.getIdCorreoElectronico()
						);
				else
				{
					lpce_personaCorreoElectronico = new PersonaCorreoElectronico();

					lpce_personaCorreoElectronico.setIdPersona(ls_idPersona);
					lpce_personaCorreoElectronico.setCorreoElectronico(ls_correoElectronico);
					lpce_personaCorreoElectronico.setFechaDesde(new Date());
					lpce_personaCorreoElectronico.setIdUsuarioCreacion(as_userId);
					lpce_personaCorreoElectronico.setIpCreacion(as_userId);

					ll_idCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
							                               .insertOrUpdate(lpce_personaCorreoElectronico, true);
				}
			}

			String ls_idSolicitud;

			{
				SolicitudDAO lsd_solicitudDAO;
				Solicitud    ls_solicitud;

				lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(adm_manager);
				ls_solicitud         = new Solicitud();

				{
					int li_idSolicitud;

					li_idSolicitud     = lsd_solicitudDAO.findSecuence();

					ls_idSolicitud = StringUtils.getString(li_idSolicitud);
				}

				ls_solicitud.setIdSolicitud(ls_idSolicitud);
				ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_70);
				ls_solicitud.setIdSubproceso(ProcesoCommon.ID_SUBPROCESO_1);
				ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.SEDE_ELECTRONICA);
				ls_solicitud.setIdPersona(ls_idPersona);
				ls_solicitud.setFechaSolicitud(new Date());
				ls_solicitud.setIdCorreoElectronico(StringUtils.getString(ll_idCorreoElectronico));
				ls_solicitud.setIdCorreoElectronicoComunicacion(StringUtils.getString(ll_idCorreoElectronico));
				ls_solicitud.setIdAutorizacionNotificacion(IdentificadoresCommon.NUM1);
				ls_solicitud.setIdAutorizacionComunicacion(IdentificadoresCommon.NUM1);
				ls_solicitud.setDerechoPeticion(EstadoCommon.N);
				ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
				ls_solicitud.setDigitalizada(EstadoCommon.N);
				ls_solicitud.setIdUsuarioCreacion(as_userId);
				ls_solicitud.setIpCreacion(as_remoteIp);

				{
					ls_nir = crearNir(as_userId, as_remoteIp, adm_manager);

					if(!StringUtils.isValidString(ls_nir))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_GENERANDO_NIR));

					ls_solicitud.setNir(ls_nir);
				}

				lsd_solicitudDAO.insertOrUpdate(ls_solicitud, true);
			}

			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
				lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
				lth_turnoHistoria.setIpModificacion(as_remoteIp);

				DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudAlertaTierras", lb2be_e);

			throw lb2be_e;
		}

		return ls_nir;
	}

	/**
	 * Generar solicitud de registro.
	 *
	 * @param atcgsi_criterios            de atcgsi criterios
	 * @param ap_persona            de ap persona
	 * @param as_idProceso            de as id proceso
	 * @param as_idSubproceso de as id subproceso
	 * @param ace_codigoSalida            de lce codigo salida
	 * @param adm_manager            de adm manager
	 * @param as_userId            de as user id
	 * @param as_remoteIp            de as remote ip
	 * @return el valor de string
	 * @throws B2BException             Objeto de tipo B2BException, se produce cuando se encuentra algun
	 *             error controlado.
	 */
	private Map<String, String> generarSolicitudRegistro(
	    TipoCriterioGSI[] atcgsi_criterios, Persona ap_persona, String as_idProceso, String as_idSubproceso,
	    CodigoError ace_codigoSalida, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		Map<String, String> lmss_resultado;

		lmss_resultado = new HashMap<String, String>();

		try
		{
			if(!CollectionUtils.isValidCollection(atcgsi_criterios))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

			if(ap_persona == null)
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_PERSONA_SOLICITUD));

			if(!StringUtils.isValidString(as_idProceso))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_SERVICIO_NO_VALIDO));

			if(!StringUtils.isValidString(as_idSubproceso))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_SUBSERVICIO_NO_VALIDO));

			boolean lb_validacionesTurno;
			boolean lb_validacionesCirculoMatricula;
			boolean lb_devoluciones;

			/**
			 * Variables que sirven en el método para los flujos: Proceso 38, subproceso 1
			 */
			String ls_idCirculo;
			String ls_idMatricula;
			String ls_idCirculoDestino;

			/**
			 * Variables que sirven en el método para los flujos: Proceso 39, subproceso 6
			 * Proceso 45, subproceso 6 Proceso 47, subproceso 1 Proceso 47, subproceso 2
			 * Proceso 47, subproceso 3 Proceso 47, subproceso 4
			 */
			String ls_turno;
			String ls_calidadSolicitante;

			/**
			 * Variables que sirven en el método para los flujos: Proceso 4, subproceso 2
			 * Proceso 38, subproceso 1 Proceso 39, subproceso 6 Proceso 45, subproceso 6
			 * Proceso 47, subproceso 1 Proceso 47, subproceso 2 Proceso 47, subproceso 3
			 * Proceso 47, subproceso 4
			 */
			String ls_primerNombre;
			String ls_segundoNombre;
			String ls_primerApellido;
			String ls_segundoApellido;
			String ls_razonSocial;
			String ls_correoElectronico;
			String ls_observacion;

			/**
			 * Variables que sirven en el método para los flujos: Proceso 38, subproceso 1
			 * Proceso 39, subproceso 6 Proceso 45, subproceso 6 Proceso 47, subproceso 1
			 * Proceso 47, subproceso 2 Proceso 47, subproceso 3 Proceso 47, subproceso 4
			 */
			String ls_documentosSolicitados;

			/**
			 * Variables que sirven en el método para los flujos: Proceso 4, subproceso 1,2 , Proceso 40, subproceso 4 ,Proceso 41, subproceso 4
			 * Proceso 4, subproceso 3
			 */
			Date ld_fechaConsignacion;
			String ls_tipoDocumento;
			String ls_actos;
			String ls_certificados;
			String ls_numeroDocumento;
			String ls_solicitanteApoderado;
			String ls_solicitanteInterviniente;
			String ls_genero;
			String ls_codigoEntidadFinancieraConsignacion;
			String ls_extemporaneo;
			String ls_tipoConsignacion;
			String ls_numeroTipoConsignacion;
			String ls_valorConsignacion;
			String ls_nirCriterio;
			String ls_numeroCuentaBancariaConsignacion;
			String ls_codigoEntidadFinanciera;
			String ls_idEntidadRecaudadora;
			String ls_tipoCuenta;
			String ls_numeroCuenta;
			String ls_telefonoFijo;
			String ls_telefonoMovil;
			String ls_titularCuentaTipoDocumento;
			String ls_titularCuentaNumeroDocumento;
			String ls_titularCuentaPrimerNombre;
			String ls_titularCuentaSegundoNombre;
			String ls_titularCuentaPrimerApellido;
			String ls_titularCuentaSegundoApellido;
			String ls_titularCuentaRazonSocial;
			String ls_poderdanteTipoDocumento;
			String ls_poderdanteNumeroDocumento;
			String ls_poderdantePrimerNombre;
			String ls_poderdanteSegundoNombre;
			String ls_poderdantePrimerApellido;
			String ls_poderdanteSegundoApellido;
			String ls_poderdanteRazonSocial;
			String ls_intervinienteTipoDocumento;
			String ls_intervinienteNumeroDocumento;
			String ls_intervinientePrimerNombre;
			String ls_intervinienteSegundoNombre;
			String ls_intervinientePrimerApellido;
			String ls_intervinienteSegundoApellido;
			String ls_intervinienteRazonSocial;
			String ls_empresaDireccionCorrespondencia;
			String ls_motivoSolicitud;

			/**
			 * Variables que sirven en el método para los flujos:
			 * Proceso 4, subproceso 3
			 */
			String ls_tipoDevolucion;
			String ls_codigoCuentaCupo;
			String ls_codigoNotaCredito;

			lb_validacionesTurno                = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_39)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_45)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_47)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41)
					|| (as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
					&& as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1));
			lb_validacionesCirculoMatricula     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38);
			lb_devoluciones                     = as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_40)
					|| as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_41);

			ls_idCirculo                               = null;
			ls_actos                                   = null;
			ls_certificados                            = null;
			ls_idMatricula                             = null;
			ls_idCirculoDestino                        = null;
			ls_turno                                   = null;
			ls_calidadSolicitante                      = null;
			ls_primerNombre                            = null;
			ls_segundoNombre                           = null;
			ls_primerApellido                          = null;
			ls_segundoApellido                         = null;
			ls_razonSocial                             = null;
			ls_correoElectronico                       = null;
			ls_observacion                             = null;
			ls_documentosSolicitados                   = null;
			ls_tipoDocumento                           = null;
			ls_numeroDocumento                         = null;
			ls_nirCriterio                             = null;
			ls_solicitanteApoderado                    = null;
			ls_solicitanteInterviniente                = null;
			ls_genero                                  = null;
			ls_codigoEntidadFinancieraConsignacion     = null;
			ls_tipoConsignacion                        = null;
			ls_numeroTipoConsignacion                  = null;
			ls_valorConsignacion                       = null;
			ld_fechaConsignacion                       = null;
			ls_numeroCuentaBancariaConsignacion        = null;
			ls_idEntidadRecaudadora                    = null;
			ls_codigoEntidadFinanciera                 = null;
			ls_tipoCuenta                              = null;
			ls_numeroCuenta                            = null;
			ls_telefonoFijo                            = null;
			ls_telefonoMovil                           = null;
			ls_titularCuentaTipoDocumento              = null;
			ls_titularCuentaNumeroDocumento            = null;
			ls_titularCuentaPrimerNombre               = null;
			ls_titularCuentaSegundoNombre              = null;
			ls_titularCuentaPrimerApellido             = null;
			ls_titularCuentaSegundoApellido            = null;
			ls_titularCuentaRazonSocial                = null;
			ls_poderdanteTipoDocumento                 = null;
			ls_poderdanteNumeroDocumento               = null;
			ls_poderdantePrimerNombre                  = null;
			ls_poderdanteSegundoNombre                 = null;
			ls_poderdantePrimerApellido                = null;
			ls_poderdanteSegundoApellido               = null;
			ls_poderdanteRazonSocial                   = null;
			ls_intervinienteTipoDocumento              = null;
			ls_intervinienteNumeroDocumento            = null;
			ls_intervinientePrimerNombre               = null;
			ls_intervinienteSegundoNombre              = null;
			ls_intervinientePrimerApellido             = null;
			ls_intervinienteSegundoApellido            = null;
			ls_intervinienteRazonSocial                = null;
			ls_empresaDireccionCorrespondencia         = null;
			ls_motivoSolicitud                         = null;
			ls_tipoDevolucion                          = null;
			ls_codigoCuentaCupo                        = null;
			ls_codigoNotaCredito                       = null;
			ls_extemporaneo                            = "N";

			for(
			    int li_posicion = 0, li_tamanioCriterios = atcgsi_criterios.length; li_posicion < li_tamanioCriterios;
				    li_posicion++
			)
			{
				TipoCriterioGSI ltcgsi_criterio;

				ltcgsi_criterio = atcgsi_criterios[li_posicion];

				if(ltcgsi_criterio != null)
				{
					String ls_codigo;

					ls_codigo = StringUtils.getStringNotNull(ltcgsi_criterio.getCodigo());

					if((lb_validacionesTurno && !lb_devoluciones) || lb_validacionesCirculoMatricula)
					{
						switch(ls_codigo)
						{
							case IdentificadoresCommon.TURNO:
								ls_turno = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.ID_CIRCULO:
								ls_idCirculo = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.ID_MATRICULA:
								ls_idMatricula = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.ID_CIRCULO_DESTINO:
								ls_idCirculoDestino = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PRIMER_NOMBRE_SOLICITANTE:
								ls_primerNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SEGUNDO_NOMBRE_SOLICITANTE:
								ls_segundoNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PRIMER_APELLIDO_SOLICITANTE:
								ls_primerApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SEGUNDO_APELLIDO_SOLICITANTE:
								ls_segundoApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.RAZON_SOCIAL:
								ls_razonSocial = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CALIDAD_SOLICITANTE:
								ls_calidadSolicitante = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.EMAIL:
								ls_correoElectronico = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.DOCUMENTOS_SOLICITADOS:
								ls_documentosSolicitados = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.OBSERVACION:
								ls_observacion = ltcgsi_criterio.getValor();

								break;

							default:
								break;
						}
					}
					else if(lb_devoluciones)
					{
						switch(ls_codigo)
						{
							case IdentificadoresCommon.TIPO_DOCUMENTO:
							case IdentificadoresCommon.EMPRESA_TIPO_DOCUMENTO:
								ls_tipoDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.NUMERO_DOCUMENTO:
							case IdentificadoresCommon.EMPRESA_NUMERO_DOCUMENTO:
								ls_numeroDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PRIMER_NOMBRE:
								ls_primerNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SEGUNDO_NOMBRE:
								ls_segundoNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PRIMER_APELLIDO:
								ls_primerApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SEGUNDO_APELLIDO:
								ls_segundoApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.EMPRESA_RAZON_SOCIAL:
								ls_razonSocial = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_TIPO_DOCUMENTO:
								ls_intervinienteTipoDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_NUMERO_DOCUMENTO:
								ls_intervinienteNumeroDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_PRIMER_NOMBRE:
								ls_intervinientePrimerNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_SEGUNDO_NOMBRE:
								ls_intervinienteSegundoNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_PRIMER_APELLIDO:
								ls_intervinientePrimerApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_SEGUNDO_APELLIDO:
								ls_intervinienteSegundoApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.INTERVINIENTE_RAZON_SOCIAL:
								ls_intervinienteRazonSocial = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SOLICITANTE_APODERADO:
								ls_solicitanteApoderado = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.SOLICITANTE_INTERVINIENTE:
								ls_solicitanteInterviniente = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CORREO_ELECTRONICO:
							case IdentificadoresCommon.EMPRESA_CORREO_ELECTRONICO:
								ls_correoElectronico = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.OBSERVACIONES:
								ls_observacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CODIGO_ENTIDAD_FINANCIERA_CONSIGNACION:
								ls_codigoEntidadFinancieraConsignacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TIPO_CONSIGNACION:
								ls_tipoConsignacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.NUMERO_TIPO_CONSIGNACION:
								ls_numeroTipoConsignacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.VALOR_CONSIGNACION:
								ls_valorConsignacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.FECHA_CONSIGNACION:
								ld_fechaConsignacion = DateUtils.getDate(
									    ltcgsi_criterio.getValor(), FormatoFechaCommon.DIA_MES_ANIO
									);

								break;

							case IdentificadoresCommon.NUMERO_CUENTA_BANCARIA_CONSIGNACION:
								ls_numeroCuentaBancariaConsignacion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.GENERO:
								ls_genero = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TELEFONO_FIJO:
							case IdentificadoresCommon.EMPRESA_TELEFONO_FIJO:
								ls_telefonoFijo = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TELEFONO_MOVIL:
							case IdentificadoresCommon.EMPRESA_TELEFONO_MOVIL:
								ls_telefonoMovil = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CODIGO_ENTIDAD_FINANCIERA:
								ls_codigoEntidadFinanciera = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TIPO_CUENTA:
								ls_tipoCuenta = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.NUMERO_CUENTA:
								ls_numeroCuenta = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_TIPO_DOCUMENTO:
								ls_poderdanteTipoDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_TIPO_DOCUMENTO:
								ls_titularCuentaTipoDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_NUMERO_DOCUMENTO:
								ls_poderdanteNumeroDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_NUMERO_DOCUMENTO:
								ls_titularCuentaNumeroDocumento = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_PRIMER_NOMBRE:
								ls_poderdantePrimerNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_PRIMER_NOMBRE:
								ls_titularCuentaPrimerNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_SEGUNDO_NOMBRE:
								ls_poderdanteSegundoNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_SEGUNDO_NOMBRE:
								ls_titularCuentaSegundoNombre = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_PRIMER_APELLIDO:
								ls_poderdantePrimerApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_PRIMER_APELLIDO:
								ls_titularCuentaPrimerApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_SEGUNDO_APELLIDO:
								ls_poderdanteSegundoApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_SEGUNDO_APELLIDO:
								ls_titularCuentaSegundoApellido = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.PODERDANTE_RAZON_SOCIAL:
								ls_poderdanteRazonSocial = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TITULAR_CUENTA_RAZON_SOCIAL:
								ls_titularCuentaRazonSocial = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.EMPRESA_DIRECCION_CORRESPONDENCIA:
								ls_empresaDireccionCorrespondencia = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.MOTIVO_SOLICITUD:
								ls_motivoSolicitud = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TURNO:
								ls_turno = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.NIR:
								ls_nirCriterio = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.ACTOS:
								ls_actos = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CERTIFICADOS:
								ls_certificados = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.TIPO_DEVOLUCION:
								ls_tipoDevolucion = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CODIGO_CUENTA_CUPO:
								ls_codigoCuentaCupo = ltcgsi_criterio.getValor();

								break;

							case IdentificadoresCommon.CODIGO_NOTA_CREDITO:
								ls_codigoNotaCredito = ltcgsi_criterio.getValor();

								break;

							default:
								break;
						}
					}
				}
			}

			Turno         lt_turno;
			TurnoHistoria lth_turnoHistoria;
			Solicitud     ls_solicitudNirCriterios;

			lt_turno                     = null;
			ls_solicitudNirCriterios     = null;

			if(lb_validacionesTurno)
			{
				String ls_idProcesoTurno;

				ls_idProcesoTurno = null;

				if(!StringUtils.isValidString(ls_turno))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_INVALIDO));

				lt_turno              = DaoCreator.getTurnoDAO(adm_manager).findById(ls_turno);
				lth_turnoHistoria     = DaoCreator.getTurnoHistoriaDAO(adm_manager).findByIdTurno(ls_turno);

				if(((lt_turno == null) || (lth_turnoHistoria == null)))
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(509L));
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_NO_EXISTE));
				}

				ls_idProcesoTurno = lt_turno.getIdProceso();

				if(lb_devoluciones)
				{
					if(
					    ((as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
						    && as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_4))
						    || (as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)
						    && as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_4)))
					)
					{
						{
							if(!ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_4))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(512L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_DEVOLUCION_O_SUSPENDIDO));
							}
							else
							{
								Constantes lc_constante;
								Long       ll_etapa;

								ll_etapa     = lt_turno.getIdEtapaActual();

								lc_constante = DaoCreator.getConstantesDAO(adm_manager)
										                     .findById(
										    (as_idProceso.equals(ProcesoCommon.ID_PROCESO_40))
										    ? ConstanteCommon.TURNO_SUSPENDIDO_40_4
										        : ConstanteCommon.TURNO_SUSPENDIDO_41_4
										);

								if((lc_constante != null) && NumericUtils.isValidLong(ll_etapa))
								{
									String ls_caracter;

									ls_caracter = lc_constante.getCaracter();

									if(StringUtils.isValidString(ls_caracter))
									{
										Collection<String> lcs_valores;

										lcs_valores = separarPorSimboloComa(ls_caracter, true);

										if(
										    CollectionUtils.isValidCollection(lcs_valores)
											    && !lcs_valores.contains(ll_etapa.toString())
										)
										{
											ace_codigoSalida.setCodigoError(BigInteger.valueOf(512L));
											throw new B2BException(
											    addMessageGCC(ErrorKeys.ERROR_TURNO_DEVOLUCION_O_SUSPENDIDO)
											);
										}
									}
								}
							}
						}

						if(!StringUtils.isValidString(ls_nirCriterio))
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_INVALIDO));
						else
						{
							ls_solicitudNirCriterios = DaoCreator.getSolicitudDAO(adm_manager).findByNir(
								    ls_nirCriterio
								);

							if(ls_solicitudNirCriterios == null)
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(515L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_EXISTE));
							}
							else
							{
								String ls_idSolicitudCriterio;
								ls_idSolicitudCriterio = ls_solicitudNirCriterios.getIdSolicitud();

								if(!ls_idSolicitudCriterio.equalsIgnoreCase(lt_turno.getIdSolicitud()))
								{
									ace_codigoSalida.setCodigoError(BigInteger.valueOf(511L));
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_NO_PERTENECE_NIR));
								}
							}
						}
					}

					if(
					    as_idProceso.equals(ProcesoCommon.ID_PROCESO_4)
						    && (!StringUtils.isValidString(ls_idProcesoTurno)
						    || !(ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_1)
						    || ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_2)
						    || ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_6)))
					)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(501L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_PROCESO_NO_PERMITIDO_DEVOLUCION));
					}

					if(
					    as_idProceso.equals(ProcesoCommon.ID_PROCESO_4)
						    && as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_1)
					)
					{
						{
							TurnoDerivado ltd_derivado;

							ltd_derivado = DaoCreator.getTurnoDerivadoDAO(adm_manager).findByIdHijo(ls_turno);

							if(ltd_derivado != null)
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(504L));
								throw new B2BException(
								    addMessageGCC(ErrorKeys.ERROR_SOLICITUD_DEVOLUCION_TURNO_EXISTENTE)
								);
							}
						}

						{
							DevolucionDinero ldd_devolucionDinero;

							ldd_devolucionDinero = DaoCreator.getDevolucionDineroDAO(adm_manager)
									                             .findByIdturnoDevolucion(ls_turno);

							if(ldd_devolucionDinero != null)
							{
								Solicitud ls_solicitudDevDinero;

								ls_solicitudDevDinero = DaoCreator.getSolicitudDAO(adm_manager)
										                              .findById(
										    StringUtils.getStringNotNull(ldd_devolucionDinero.getIdSolicitud())
										);

								if(ls_solicitudDevDinero != null)
								{
									Long ll_estadoSolicitud;

									ll_estadoSolicitud = ls_solicitudDevDinero.getEstadoSolicitud();

									if((ll_estadoSolicitud != null) && (ll_estadoSolicitud.longValue() != 5L))
									{
										ace_codigoSalida.setCodigoError(BigInteger.valueOf(504L));
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_SOLICITUD_DEVOLUCION_TURNO_EXISTENTE)
										);
									}
								}
							}
						}
					}
				}

				long ll_idEtapa;

				ll_idEtapa = NumericUtils.getLong(lth_turnoHistoria.getIdEtapa());

				switch(as_idProceso)
				{
					case ProcesoCommon.ID_PROCESO_45:

						if(
						    !((ll_idEtapa >= EtapaCommon.ID_ETAPA_INICIAL)
							    && (ll_idEtapa <= EtapaCommon.ENVIO_CITATORIO_Y_O_ACTA_NOTIFICACION))
						)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(502L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_ETAPA_PERMITIDA));
						}

						break;

					case ProcesoCommon.ID_PROCESO_47:

						if(
						    !((ll_idEtapa == EtapaCommon.ID_ETAPA_EN_ESPERA_TERMINO_PARA_INTERPONER_RECURSOS)
							    || (ll_idEtapa == EtapaCommon.EN_ESPERA_INTERPOSICION_DE_RECURSOS_CORRECCIONES)
							    || (ll_idEtapa == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA))
						)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(502L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_ETAPA_PERMITIDA));
						}

						break;

					case ProcesoCommon.ID_PROCESO_4:

						if(ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_1))
						{
							if(!StringUtils.isValidString(ls_certificados))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(510L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CERTIFICADOS_OBLIGATORIOS));
							}

							if(!(ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_DE_CERTIFICADOS_FINALIZADO_NO_APROBADO))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(502L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_ETAPA_PERMITIDA));
							}
						}
						else if(ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_2))
						{
							//TODO Revisar logica de proceso 2 devoluciones de dinero con Leidy.
						}
						else if(ls_idProcesoTurno.equals(ProcesoCommon.ID_PROCESO_6))
						{
							if(!StringUtils.isValidString(ls_actos))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(508L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_ACTOS_NO_VALIDOS));
							}
							else
							{
								if(
								    !((ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_DE_REGISTRO_FINALIZADO)
									    || (ll_idEtapa == EtapaCommon.FINALIZACION_PROCESO_DEVUELTO_AL_PUBLICO_NOTA_DEVOLUTIVA)
									    || (ll_idEtapa == EtapaCommon.FINALIZACION_PROCESO_DE_REGISTRO_PARCIAL)
									    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_FINALIZADO_POR_DESISTIMIENTO)
									    || (ll_idEtapa == EtapaCommon.ID_ETAPA_PROCESO_DE_CERTIFICADOS_FINALIZADO_NO_APROBADO))
								)
								{
									ace_codigoSalida.setCodigoError(BigInteger.valueOf(502L));
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TURNO_ETAPA_PERMITIDA));
								}

								{
									String ls_idCirculoTurno;
									String ls_idSolicitudTurno;

									ls_idCirculoTurno       = lt_turno.getIdCirculo();
									ls_idSolicitudTurno     = lt_turno.getIdSolicitud();

									if(
									    StringUtils.isValidString(ls_idCirculoTurno)
										    && StringUtils.isValidString(ls_idSolicitudTurno)
									)
									{
										Collection<Acto>   lca_actos;
										Collection<String> lcs_valores;

										lcs_valores     = separarPorSimboloComa(ls_actos, true);
										lca_actos       = DaoCreator.getActoDAO(adm_manager)
												                        .findByIdSolicitudCirculo(
												    ls_idSolicitudTurno, ls_idCirculoTurno
												);

										if(CollectionUtils.isValidCollection(lca_actos))
										{
											for(Acto la_actual : lca_actos)
											{
												if(la_actual != null)
												{
													if(
													    CollectionUtils.isValidCollection(lcs_valores)
														    && !lcs_valores.contains(la_actual.getIdTipoActo())
													)
													{
														ace_codigoSalida.setCodigoError(BigInteger.valueOf(508L));
														throw new B2BException(
														    addMessageGCC(ErrorKeys.ERROR_ACTOS_NO_VALIDOS)
														);
													}
												}
											}
										}
									}
								}
							}
						}

						break;

					default:
						break;
				}
			}

			if(lb_validacionesCirculoMatricula)
			{
				{
					CirculoRegistralDao lcr_DAO;

					lcr_DAO = DaoCreator.getCirculoRegistralDAO(adm_manager);

					{
						if(!StringUtils.isValidString(ls_idCirculo))
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CIRCULO_ORIGEN_INVALIDO));

						CirculoRegistral lcr_circuloRegistralOrigen;

						lcr_circuloRegistralOrigen = lcr_DAO.findById(ls_idCirculo);

						if(lcr_circuloRegistralOrigen == null)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(527L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CIRCULO_ORIGEN_NO_EXISTE));
						}
					}

					{
						if(!StringUtils.isValidString(ls_idCirculoDestino))
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CIRCULO_DESTINO_INVALIDO));

						CirculoRegistral lcr_circuloRegistralDestino;

						lcr_circuloRegistralDestino = lcr_DAO.findById(ls_idCirculoDestino);

						if(lcr_circuloRegistralDestino == null)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(529L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CIRCULO_DESTINO_NO_EXISTE));
						}
					}

					{
						CirculoOrigenDestino lcod_circuloOrigenDestino;

						lcod_circuloOrigenDestino = DaoCreator.getCirculoOrigenDestinoDAO(adm_manager)
								                                  .findById(ls_idCirculo, ls_idCirculoDestino);

						if(lcod_circuloOrigenDestino == null)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(530L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TRASLADO_CIRCULOS));
						}
					}
				}

				{
					if(!StringUtils.isValidString(ls_idMatricula))
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MATRICULA_INVALIDA));

					PredioRegistro lpr_predioRegistro;

					lpr_predioRegistro = DaoCreator.getPredioRegistroDAO(adm_manager)
							                           .findByCirculoMatricula(
							    ls_idCirculo, NumericUtils.getLong(ls_idMatricula)
							);

					if(lpr_predioRegistro == null)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(528L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MATRICULA_NO_EXISTE));
					}
				}
			}
			else if(lb_devoluciones)
			{
				validarTipoDocumento(ls_tipoDocumento, ace_codigoSalida, adm_manager, false);
				validarNumeroDocumento(ls_numeroDocumento);

				if(
				    !(ls_tipoDocumento.equalsIgnoreCase(ap_persona.getIdDocumentoTipo())
					    && ls_numeroDocumento.equalsIgnoreCase(ap_persona.getNumeroDocumento()))
				)
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(505L));
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_SOLICITANTE_NO_COINCIDEN));
				}

				if(
				    !as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)
					    && !(StringUtils.isValidString(ls_codigoEntidadFinanciera)
					    || StringUtils.isValidString(ls_tipoCuenta) || StringUtils.isValidString(ls_numeroCuenta))
				)
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(506L));
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIOS_TITULAR_CUENTA_OBLIGATORIOS));
				}

				if(!StringUtils.isValidString(ls_telefonoMovil))
					propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.TELEFONO_MOVIL);

				if(!StringUtils.isValidString(ls_telefonoFijo))
					propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.TELEFONO_FIJO);

				if(!as_idProceso.equals(ProcesoCommon.ID_PROCESO_41) && !StringUtils.isValidString(ls_motivoSolicitud))
					propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.MOTIVO_SOLICITUD);

				if(!as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
				{
					Constantes lc_constante;

					lc_constante = DaoCreator.getConstantesDAO(adm_manager).findById(
						    ConstanteCommon.VALORES_TIPO_CUENTA
						);

					if(lc_constante != null)
					{
						String ls_caracter;

						ls_caracter = lc_constante.getCaracter();

						if(StringUtils.isValidString(ls_caracter))
						{
							Collection<String> lcs_valores;

							lcs_valores = separarPorSimboloComa(ls_caracter, true);

							if(CollectionUtils.isValidCollection(lcs_valores) && !lcs_valores.contains(ls_tipoCuenta))
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALORES_TIPO_CUENTA));
						}
					}
				}

				if(
				    as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_3)
					    && !(as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
					    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
				)
				{
					if(StringUtils.isValidString(ls_tipoDevolucion))
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(adm_manager)
								                     .findById(ConstanteCommon.VALORES_TIPO_DEVOLUCION);

						if(lc_constante != null)
						{
							String ls_caracter;

							ls_caracter = lc_constante.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
							{
								Collection<String> lcs_valores;

								lcs_valores = separarPorSimboloComa(ls_caracter, true);

								if(
								    CollectionUtils.isValidCollection(lcs_valores)
									    && !lcs_valores.contains(ls_tipoDevolucion)
								)
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_DEVOLUCION_NO_VALIDO));
							}
						}
					}
					else
						propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.TIPO_DEVOLUCION);

					if(StringUtils.isValidString(ls_codigoCuentaCupo))
					{
						CuentaCupo lcc_cuentaCupo;

						lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(adm_manager).findByCodigo(ls_codigoCuentaCupo);

						if(lcc_cuentaCupo != null)
						{
							String ls_estado;

							ls_estado = lcc_cuentaCupo.getEstado();

							if(
							    StringUtils.isValidString(ls_estado)
								    && !ls_estado.equalsIgnoreCase(EstadoCommon.CANCELADO_TXT)
							)
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(520L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CUENTA_CUPO_DEBE_ESTAR_CANCELADA));
							}

							if(ls_tipoDevolucion.equalsIgnoreCase(IdentificadoresCommon.CUENTA_CUPO))
							{
								long ll_saldo;

								ll_saldo = NumericUtils.getLong(lcc_cuentaCupo.getSaldo());

								if(ll_saldo <= 0)
								{
									ace_codigoSalida.setCodigoError(BigInteger.valueOf(521L));
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CUENTA_CUPO_SIN_SALDO));
								}
							}

							if(ls_tipoDevolucion.equalsIgnoreCase(IdentificadoresCommon.NOTA_CREDITO))
							{
								if(StringUtils.isValidString(ls_codigoNotaCredito))
								{
									NotaCreditoCuentaCupo lnccc_notaCreditoCuentaCupo;

									lnccc_notaCreditoCuentaCupo = DaoCreator.getNotaCreditoCuentaCupoDAO(adm_manager)
											                                    .findByCodigo(ls_codigoNotaCredito);

									if(lnccc_notaCreditoCuentaCupo != null)
									{
										{
											String ls_idCuentaCupoNotaCredito;

											ls_idCuentaCupoNotaCredito = lnccc_notaCreditoCuentaCupo.getIdCuentaCupo();

											if(
											    StringUtils.isValidString(ls_idCuentaCupoNotaCredito)
												    || ls_idCuentaCupoNotaCredito.equalsIgnoreCase(
												        lcc_cuentaCupo.getIdCuentaCupo()
												    )
											)
											{
												ace_codigoSalida.setCodigoError(BigInteger.valueOf(516L));
												throw new B2BException(
												    addMessageGCC(ErrorKeys.ERROR_SOLICITUD_FUERA_TERMINOS)
												);
											}
										}

										{
											long ll_saldo;

											ll_saldo = NumericUtils.getLong(lnccc_notaCreditoCuentaCupo.getSaldo());

											if(ll_saldo <= 0)
											{
												ace_codigoSalida.setCodigoError(BigInteger.valueOf(522L));
												throw new B2BException(
												    addMessageGCC(ErrorKeys.ERROR_NOTA_CREDITO_SIN_SALDO)
												);
											}
										}
									}
									else
									{
										ace_codigoSalida.setCodigoError(BigInteger.valueOf(519L));
										throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NOTA_CREDITO_NO_VALIDA));
									}
								}
								else
								{
									ace_codigoSalida.setCodigoError(BigInteger.valueOf(519L));
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NOTA_CREDITO_NO_VALIDA));
								}
							}
						}
						else
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(518L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CUENTA_CUPO_NO_VALIDA));
						}
					}
					else
						propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.CODIGO_CUENTA_CUPO);

					if(
					    ls_tipoDevolucion.equalsIgnoreCase(IdentificadoresCommon.NOTA_CREDITO)
						    && !StringUtils.isValidString(ls_codigoNotaCredito)
					)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(519L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NOTA_CREDITO_NO_VALIDA));
					}
				}
				else
				{
					if(as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_2))
					{
						if(!StringUtils.isValidString(ls_codigoEntidadFinancieraConsignacion))
							propagarErrorCampoObligatorio(
							    ace_codigoSalida, IdentificadoresCommon.CODIGO_ENTIDAD_FINANCIERA_CONSIGNACION
							);

						if(!StringUtils.isValidString(ls_tipoConsignacion))
							propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.TIPO_CONSIGNACION);

						if(!StringUtils.isValidString(ls_numeroTipoConsignacion))
							propagarErrorCampoObligatorio(
							    ace_codigoSalida, IdentificadoresCommon.NUMERO_TIPO_CONSIGNACION
							);

						if(!StringUtils.isValidString(ls_valorConsignacion))
							propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.VALOR_CONSIGNACION);

						if(!StringUtils.isValidString(ls_numeroCuentaBancariaConsignacion))
							propagarErrorCampoObligatorio(
							    ace_codigoSalida, IdentificadoresCommon.NUMERO_CUENTA_BANCARIA_CONSIGNACION
							);

						if(ld_fechaConsignacion != null)
							propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.FECHA_CONSIGNACION);
					}

					if(!(as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)))
					{
						DAOManager ldm_NPA;

						ldm_NPA = DaoManagerFactory.getDAOManagerNPA();

						try
						{
							String             ls_tmpCodigoEntidad;
							EntidadRecaudadora lee_ee;
							ls_tmpCodigoEntidad = null;

							if(StringUtils.isValidString(ls_codigoEntidadFinancieraConsignacion))
								ls_tmpCodigoEntidad = ls_codigoEntidadFinancieraConsignacion;
							else if(StringUtils.isValidString(ls_codigoEntidadFinanciera))
								ls_tmpCodigoEntidad = ls_codigoEntidadFinanciera;

							lee_ee = DaoCreator.getEntidadRecaudadoraDAO(ldm_NPA).findByCodigo(ls_tmpCodigoEntidad);

							if(lee_ee == null)
								throw new B2BException(
								    addMessageGCC(ErrorKeys.ERROR_CODIGO_ENTIDAD_RECAUDADORA_NO_EXISTE)
								);
							else
								ls_idEntidadRecaudadora = lee_ee.getIdEntidadRecaudadora();
						}
						catch(B2BException lb2be_e)
						{
							ldm_NPA.setRollbackOnly();

							clh_LOGGER.error("generarSolicitudRegistro", lb2be_e);

							throw lb2be_e;
						}
						finally
						{
							ldm_NPA.close();
						}
					}

					if(as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_2))
					{
						Constantes lc_constante;

						lc_constante = DaoCreator.getConstantesDAO(adm_manager)
								                     .findById(ConstanteCommon.VALORES_TIPO_CONSIGNACION);

						if(lc_constante != null)
						{
							String ls_caracter;

							ls_caracter = lc_constante.getCaracter();

							if(StringUtils.isValidString(ls_caracter))
							{
								Collection<String> lcs_valores;

								lcs_valores = separarPorSimboloComa(ls_caracter, true);

								if(
								    CollectionUtils.isValidCollection(lcs_valores)
									    && !lcs_valores.contains(ls_tipoConsignacion)
								)
									throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALORES_TIPO_CONSIGNACION));
							}
						}
					}

					if(
					    !(as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
						    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
					)
					{
						Map<String, String> lmsss_mensajesInformativos;
						String              ls_mensaje;

						lmsss_mensajesInformativos     = mensajesInformativosDevolucionDinero(
							    ld_fechaConsignacion, ls_numeroTipoConsignacion, ls_turno, adm_manager
							);
						ls_mensaje                     = null;

						if(CollectionUtils.isValidCollection(lmsss_mensajesInformativos))
						{
							for(Map.Entry<String, String> lmess_entry : lmsss_mensajesInformativos.entrySet())
							{
								if(lmess_entry != null)
								{
									String ls_llave;

									ls_llave       = lmess_entry.getKey();
									ls_mensaje     = lmess_entry.getValue();

									if(
									    StringUtils.isValidString(ls_llave)
										    && ls_llave.equals(IdentificadoresCommon.TURNO)
									)
									{
										if(StringUtils.isValidString(ls_mensaje) && ls_mensaje.contains("fuera de"))
											ls_extemporaneo = EstadoCommon.S;

										lmss_resultado.put(IdentificadoresCommon.DESCRIPCION_MENSAJE, ls_mensaje);
									}
									else if(StringUtils.isValidString(ls_mensaje))
										ace_codigoSalida.setMensajeInformativo(ls_mensaje);
								}
							}
						}
					}
					else
						lmss_resultado.put(
						    IdentificadoresCommon.DESCRIPCION_MENSAJE,
						    DaoCreator.getConstantesDAO(adm_manager)
							              .findString(
							        as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
							        ? ConstanteCommon.MSN_RECEPCION_DOCUMENTOS_EXITOSO
							            : ConstanteCommon.MSN_RECEPCION_PRORROGA_EXITOSO
							    )
						);
				}
			}

			if(lb_devoluciones)
			{
				if(as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_1))
				{
					if(
					    !StringUtils.isValidString(ls_solicitanteInterviniente)
						    || (!(ls_solicitanteInterviniente.equals(EstadoCommon.S)
						    || ls_solicitanteInterviniente.equals(EstadoCommon.N)))
					)
						propagarErrorCampoObligatorio(
						    ace_codigoSalida, IdentificadoresCommon.SOLICITANTE_INTERVINIENTE
						);
				}
				else if(
				    as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_2)
					    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
					    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)
				)
				{
					if(StringUtils.isValidString(ls_solicitanteApoderado))
					{
						if(ls_solicitanteApoderado.equalsIgnoreCase(EstadoCommon.S))
							ls_calidadSolicitante = CalidadSolicitanteCommon.APODERADO;
						else if(ls_solicitanteApoderado.equalsIgnoreCase(EstadoCommon.N))
							ls_calidadSolicitante = CalidadSolicitanteCommon.INTERVINIENTE;
					}
					else
						propagarErrorCampoObligatorio(ace_codigoSalida, IdentificadoresCommon.SOLICITANTE_APODERADO);
				}
			}
			else
			{
				if(!StringUtils.isValidString(ls_calidadSolicitante))
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(526L));
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CALIDAD_SOLICITANTE));
				}

				CalidadSolicitante lcs_calidadSolicitante;

				lcs_calidadSolicitante = DaoCreator.getCalidadSolicitanteDAO(adm_manager).findById(
					    ls_calidadSolicitante
					);

				if(lcs_calidadSolicitante == null)
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(526L));
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CALIDAD_SOLICITANTE));
				}

				if(
				    as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
					    && as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
				)
				{
					if(
					    !(ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERESADO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERVINIENTE)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.TERCERO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.AGENTE_OFICIOSO))
					)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(526L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CALIDAD_SOLICITANTE));
					}
				}
				else if(
				    as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_38)
					    && as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
				)
				{
					if(
					    !(ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.FUNCIONARIO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERVINIENTE))
					)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(526L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CALIDAD_SOLICITANTE));
					}
				}
				else
				{
					if(
					    !(ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERESADO)
						    || ls_calidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.INTERVINIENTE))
					)
					{
						ace_codigoSalida.setCodigoError(BigInteger.valueOf(526L));
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CALIDAD_SOLICITANTE));
					}
				}
			}

			if(!validarCorreoElectronico(ls_correoElectronico))
			{
				ace_codigoSalida.setCodigoError(BigInteger.valueOf(525L));
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_ELECTRONICO_NO_VALIDO));
			}

			String                      ls_idPersona;
			String                      ls_idPersonaTitularCuenta;
			String                      ls_idPersonaPoderdante;
			String                      ls_idPersonaInterviniente;
			boolean                     lb_validarempresaDireccionCorrespondencia;
			String                      ls_idTipoPersona;
			PersonaCorreoElectronicoDAO lpced_DAO;
			PersonaTelefonoDAO          lpt_DAO;

			ls_idTipoPersona                              = ap_persona.getIdTipoPersona();
			ls_idPersonaInterviniente                     = null;
			ls_idPersonaTitularCuenta                     = null;
			ls_idPersonaPoderdante                        = null;
			ls_idPersona                                  = null;
			lb_validarempresaDireccionCorrespondencia     = false;
			lpced_DAO                                     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
			lpt_DAO                                       = DaoCreator.getPersonaTelefonoDAO(adm_manager);

			if(!(as_idProceso.equals(ProcesoCommon.ID_PROCESO_40) || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)))
			{
				if(StringUtils.isValidString(ls_idTipoPersona))
				{
					if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.N))
					{
						if(!StringUtils.isValidString(ls_primerNombre))
						{
							String ls_campo;

							ls_campo = null;

							if((lb_validacionesTurno && !lb_devoluciones) || lb_validacionesCirculoMatricula)
								ls_campo = IdentificadoresCommon.PRIMER_NOMBRE_SOLICITANTE;
							else if(lb_devoluciones)
								ls_campo = IdentificadoresCommon.PRIMER_NOMBRE;

							propagarErrorCampoObligatorio(ace_codigoSalida, ls_campo);
						}

						if(!StringUtils.isValidString(ls_primerApellido))
						{
							String ls_campo;

							ls_campo = null;

							if((lb_validacionesTurno && !lb_devoluciones) || lb_validacionesCirculoMatricula)
								ls_campo = IdentificadoresCommon.PRIMER_APELLIDO_SOLICITANTE;
							else if(lb_devoluciones)
								ls_campo = IdentificadoresCommon.PRIMER_APELLIDO;

							propagarErrorCampoObligatorio(ace_codigoSalida, ls_campo);
						}

						ap_persona.setPrimerNombre(ls_primerNombre);
						ap_persona.setSegundoNombre(ls_segundoNombre);
						ap_persona.setPrimerApellido(ls_primerApellido);
						ap_persona.setSegundoApellido(ls_segundoApellido);
						ap_persona.setIdNaturalGenero(ls_genero);
					}
					else if(ls_idTipoPersona.equalsIgnoreCase(EstadoCommon.J))
					{
						if(StringUtils.isValidString(ls_razonSocial))
							ap_persona.setRazonSocial(ls_razonSocial);
						else
						{
							String ls_campo;

							ls_campo = null;

							if((lb_validacionesTurno && !lb_devoluciones) || lb_validacionesCirculoMatricula)
								ls_campo = IdentificadoresCommon.RAZON_SOCIAL;
							else if(lb_devoluciones)
								ls_campo = IdentificadoresCommon.EMPRESA_RAZON_SOCIAL;

							propagarErrorCampoObligatorio(ace_codigoSalida, ls_campo);
						}

						if(lb_devoluciones)
						{
							if(StringUtils.isValidString(ls_empresaDireccionCorrespondencia))
								lb_validarempresaDireccionCorrespondencia = true;
							else
								propagarErrorCampoObligatorio(
								    ace_codigoSalida, IdentificadoresCommon.EMPRESA_DIRECCION_CORRESPONDENCIA
								);
						}
					}
					else
						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_PERSONA_SOLICITUD));
				}

				{
					if(
					    as_idProceso.equals(ProcesoCommon.ID_PROCESO_4)
						    && !as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
					)
					{
						String    ls_idSolicitudTurno;
						Solicitud ls_solicitudTemporal;

						ls_idSolicitudTurno      = lt_turno.getIdSolicitud();
						ls_solicitudTemporal     = DaoCreator.getSolicitudDAO(adm_manager).findById(
							    ls_idSolicitudTurno
							);

						if(ls_solicitudTemporal != null)
						{
							Persona lp_tmp;

							lp_tmp = DaoCreator.getPersonaDAO(adm_manager).findById(
								    ls_solicitudTemporal.getIdPersona()
								);

							if(lp_tmp != null)
								ls_idPersona = lp_tmp.getIdPersona();
							else
								ls_idPersona = extraerIdPersona(ap_persona, adm_manager);
						}
						else
							ls_idPersona = extraerIdPersona(ap_persona, adm_manager);
					}
					else
						ls_idPersona = extraerIdPersona(ap_persona, adm_manager);

					if(!StringUtils.isValidString(ls_idPersona))
					{
						ap_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						ap_persona.setIdUsuarioCreacion(as_userId);
						ap_persona.setIpCreacion(as_userId);

						ap_persona     = DaoCreator.getPersonaDAO(adm_manager).insertOrUpdate(ap_persona, true);

						ls_idPersona = ap_persona.getIdPersona();
					}

					if(as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3))
					{
						UsuarioCuentaCupo lucc_usuarioCuentaCupo;

						lucc_usuarioCuentaCupo = consultarUsuarioCuentaCupoByDatos(ap_persona, adm_manager);

						if(lucc_usuarioCuentaCupo == null)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(517));
							throw new B2BException(
							    addMessageGCC(ErrorKeys.ERROR_SOLICITANTE_ADMINISTRADOR_CUENTA_CUPO)
							);
						}
					}
				}
			}
			else if(ls_solicitudNirCriterios != null)
			{
				ls_idPersona = ls_solicitudNirCriterios.getIdPersona();

				if(StringUtils.isValidString(ls_idPersona))
				{
					Persona lp_tmp;

					lp_tmp = DaoCreator.getPersonaDAO(adm_manager).findById(ls_idPersona);

					if(lp_tmp != null)
					{
						String ls_tipoDocumentoTmp;
						String ls_numeroDocumentoTmp;
						String ls_tipoPersonaTmp;

						ls_tipoDocumentoTmp       = lp_tmp.getIdDocumentoTipo();
						ls_numeroDocumentoTmp     = lp_tmp.getNumeroDocumento();
						ls_tipoPersonaTmp         = lp_tmp.getIdTipoPersona();

						if(
						    !(ls_tipoDocumentoTmp.equals(ap_persona.getIdDocumentoTipo())
							    && ls_numeroDocumentoTmp.equals(ap_persona.getNumeroDocumento())
							    && ls_tipoPersonaTmp.equals(ls_idTipoPersona))
						)
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(513L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_SOLICITANTE_INGRESADO));
						}
					}
				}
			}

			if(lb_devoluciones)
			{
				if(as_idSubproceso.equals(ProcesoCommon.ID_PROCESO_1) && (lt_turno != null))
				{
					String ls_idSolicitudTurno;

					ls_idSolicitudTurno = lt_turno.getIdSolicitud();

					if(StringUtils.isValidString(ls_idSolicitudTurno))
					{
						SolicitudInterviniente lsi_si;

						lsi_si = null;

						if(BooleanUtils.getBooleanValue(ls_solicitanteInterviniente))
							lsi_si = DaoCreator.getSolicitudIntervinienteDAO(adm_manager)
									               .findById(ls_idSolicitudTurno, ls_idPersona);
						else
						{
							Persona lp_personaInterviniente;

							lp_personaInterviniente = new Persona(
								    ls_intervinienteTipoDocumento, ls_intervinienteNumeroDocumento
								);

							validarTipoDocumento(
							    ls_intervinienteTipoDocumento, EstadoCommon.I, ace_codigoSalida, adm_manager, false
							);
							validarNumeroDocumento(ls_intervinienteNumeroDocumento, EstadoCommon.I);

							if(StringUtils.isValidString(ls_intervinienteTipoDocumento))
							{
								if(!ls_intervinienteTipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
								{
									if(!StringUtils.isValidString(ls_intervinientePrimerNombre))
										propagarErrorCampoObligatorio(
										    ace_codigoSalida, IdentificadoresCommon.INTERVINIENTE_PRIMER_NOMBRE
										);

									if(!StringUtils.isValidString(ls_intervinientePrimerApellido))
										propagarErrorCampoObligatorio(
										    ace_codigoSalida, IdentificadoresCommon.INTERVINIENTE_PRIMER_APELLIDO
										);

									lp_personaInterviniente.setPrimerNombre(ls_intervinientePrimerNombre);
									lp_personaInterviniente.setSegundoNombre(ls_intervinienteSegundoNombre);
									lp_personaInterviniente.setPrimerApellido(ls_intervinientePrimerApellido);
									lp_personaInterviniente.setSegundoApellido(ls_intervinienteSegundoApellido);
									lp_personaInterviniente.setIdTipoPersona(EstadoCommon.N);
								}
								else
								{
									if(StringUtils.isValidString(ls_intervinienteRazonSocial))
									{
										lp_personaInterviniente.setRazonSocial(ls_intervinienteRazonSocial);
										lp_personaInterviniente.setIdTipoPersona(EstadoCommon.J);
									}
									else
										propagarErrorCampoObligatorio(
										    ace_codigoSalida, IdentificadoresCommon.INTERVINIENTE_RAZON_SOCIAL
										);
								}
							}

							{
								if(
								    as_idProceso.equals(ProcesoCommon.ID_PROCESO_4)
									    && !as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
								)
								{
									Solicitud ls_solicitudTemporal;

									ls_solicitudTemporal = DaoCreator.getSolicitudDAO(adm_manager)
											                             .findById(ls_idSolicitudTurno);

									if(ls_solicitudTemporal != null)
									{
										Persona lp_tmp;

										lp_tmp = DaoCreator.getPersonaDAO(adm_manager)
												               .findById(ls_solicitudTemporal.getIdPersona());

										if(lp_tmp != null)
											ls_idPersonaInterviniente = lp_tmp.getIdPersona();
										else
											ls_idPersonaInterviniente = extraerIdPersona(
												    lp_personaInterviniente, adm_manager
												);
									}
									else
										ls_idPersonaInterviniente = extraerIdPersona(
											    lp_personaInterviniente, adm_manager
											);
								}
								else
									ls_idPersonaInterviniente = extraerIdPersona(lp_personaInterviniente, adm_manager);

								if(!StringUtils.isValidString(ls_idPersonaInterviniente))
								{
									lp_personaInterviniente.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
									lp_personaInterviniente.setIdUsuarioCreacion(as_userId);
									lp_personaInterviniente.setIpCreacion(as_userId);

									lp_personaInterviniente     = DaoCreator.getPersonaDAO(adm_manager)
											                                    .insertOrUpdate(
											    lp_personaInterviniente, true
											);

									ls_idPersonaInterviniente = lp_personaInterviniente.getIdPersona();
								}
							}

							lsi_si = DaoCreator.getSolicitudIntervinienteDAO(adm_manager)
									               .findById(ls_idSolicitudTurno, ls_idPersonaInterviniente);
						}

						if(lsi_si == null)
						{
							if(BooleanUtils.getBooleanValue(ls_solicitanteInterviniente))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(503L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SOLICITANTE_REGISTRADO_TURNO));
							}
							else
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(507L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERVINIENTE_REGISTRADO_TURNO));
							}
						}
					}
				}

				if(!as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
				{
					Persona lp_personaTitularCuenta;

					lp_personaTitularCuenta = new Persona();

					validarTipoDocumento(
					    ls_titularCuentaTipoDocumento, EstadoCommon.T, ace_codigoSalida, adm_manager, false
					);
					validarNumeroDocumento(ls_titularCuentaNumeroDocumento, EstadoCommon.T);

					lp_personaTitularCuenta.setIdDocumentoTipo(ls_titularCuentaTipoDocumento);
					lp_personaTitularCuenta.setNumeroDocumento(ls_titularCuentaNumeroDocumento);

					if(!ls_titularCuentaTipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					{
						if(
						    StringUtils.isValidString(ls_titularCuentaPrimerNombre)
							    && StringUtils.isValidString(ls_titularCuentaPrimerApellido)
						)
						{
							lp_personaTitularCuenta.setPrimerNombre(ls_titularCuentaPrimerNombre);
							lp_personaTitularCuenta.setSegundoNombre(ls_titularCuentaSegundoNombre);
							lp_personaTitularCuenta.setPrimerApellido(ls_titularCuentaPrimerApellido);
							lp_personaTitularCuenta.setSegundoApellido(ls_titularCuentaSegundoApellido);
							lp_personaTitularCuenta.setIdTipoPersona(EstadoCommon.N);
						}
						else
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(506L));
							throw new B2BException(
							    addMessageGCC(ErrorKeys.ERROR_CRITERIOS_TITULAR_CUENTA_OBLIGATORIOS)
							);
						}
					}
					else
					{
						if(StringUtils.isValidString(ls_titularCuentaRazonSocial))
						{
							lp_personaTitularCuenta.setRazonSocial(ls_titularCuentaRazonSocial);
							lp_personaTitularCuenta.setIdTipoPersona(EstadoCommon.J);
						}
						else
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(506L));
							throw new B2BException(
							    addMessageGCC(ErrorKeys.ERROR_CRITERIOS_TITULAR_CUENTA_OBLIGATORIOS)
							);
						}
					}

					{
						ls_idPersonaTitularCuenta = extraerIdPersona(lp_personaTitularCuenta, adm_manager);

						if(!StringUtils.isValidString(ls_idPersonaTitularCuenta))
						{
							lp_personaTitularCuenta.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
							lp_personaTitularCuenta.setIdUsuarioCreacion(as_userId);
							lp_personaTitularCuenta.setIpCreacion(as_userId);

							lp_personaTitularCuenta     = DaoCreator.getPersonaDAO(adm_manager)
									                                    .insertOrUpdate(lp_personaTitularCuenta, true);

							ls_idPersonaTitularCuenta = lp_personaTitularCuenta.getIdPersona();
						}
					}
				}

				if(
				    StringUtils.isValidString(ls_solicitanteApoderado)
					    && ls_solicitanteApoderado.equalsIgnoreCase(EstadoCommon.S)
					    && (as_idProceso.equals(ProcesoCommon.ID_PROCESO_2)
					    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
					    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
				)
				{
					Persona lp_personaPoderdante;

					lp_personaPoderdante = new Persona();

					validarTipoDocumento(
					    ls_poderdanteTipoDocumento, EstadoCommon.P, ace_codigoSalida, adm_manager, false
					);
					validarNumeroDocumento(ls_poderdanteNumeroDocumento, EstadoCommon.P);

					lp_personaPoderdante.setIdDocumentoTipo(ls_poderdanteTipoDocumento);
					lp_personaPoderdante.setNumeroDocumento(ls_poderdanteNumeroDocumento);

					if(!ls_poderdanteTipoDocumento.equalsIgnoreCase(IdentificadoresCommon.NIT))
					{
						if(
						    StringUtils.isValidString(ls_poderdantePrimerNombre)
							    && StringUtils.isValidString(ls_poderdantePrimerApellido)
						)
						{
							lp_personaPoderdante.setPrimerNombre(ls_poderdantePrimerNombre);
							lp_personaPoderdante.setSegundoNombre(ls_poderdanteSegundoNombre);
							lp_personaPoderdante.setPrimerApellido(ls_poderdantePrimerApellido);
							lp_personaPoderdante.setSegundoApellido(ls_poderdanteSegundoApellido);
							lp_personaPoderdante.setIdTipoPersona(EstadoCommon.N);
						}
						else
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(506L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIOS_PODERDANTE_OBLIGATORIOS));
						}
					}
					else
					{
						if(StringUtils.isValidString(ls_poderdanteRazonSocial))
						{
							lp_personaPoderdante.setRazonSocial(ls_poderdanteRazonSocial);
							lp_personaPoderdante.setIdTipoPersona(EstadoCommon.J);
						}
						else
						{
							ace_codigoSalida.setCodigoError(BigInteger.valueOf(506L));
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIOS_PODERDANTE_OBLIGATORIOS));
						}
					}

					{
						ls_idPersonaPoderdante = extraerIdPersona(lp_personaPoderdante, adm_manager);

						if(!StringUtils.isValidString(ls_idPersonaPoderdante))
						{
							lp_personaPoderdante.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
							lp_personaPoderdante.setIdUsuarioCreacion(as_userId);
							lp_personaPoderdante.setIpCreacion(as_userId);

							lp_personaPoderdante     = DaoCreator.getPersonaDAO(adm_manager)
									                                 .insertOrUpdate(lp_personaPoderdante, true);

							ls_idPersonaPoderdante = lp_personaPoderdante.getIdPersona();
						}
					}
				}
			}

			long                     ll_idCorreoElectronico;
			PersonaCorreoElectronico lpce_personaCorreoElectronico;

			ll_idCorreoElectronico            = 1L;
			lpce_personaCorreoElectronico     = lpced_DAO.findByIdPersonaCorreoMax(ls_idPersona, ls_correoElectronico);

			if(lpce_personaCorreoElectronico != null)
				ll_idCorreoElectronico = NumericUtils.getLong(lpce_personaCorreoElectronico.getIdCorreoElectronico());
			else
			{
				lpce_personaCorreoElectronico = new PersonaCorreoElectronico();

				lpce_personaCorreoElectronico.setIdPersona(ls_idPersona);
				lpce_personaCorreoElectronico.setCorreoElectronico(ls_correoElectronico);
				lpce_personaCorreoElectronico.setFechaDesde(new Date());
				lpce_personaCorreoElectronico.setIdUsuarioCreacion(as_userId);
				lpce_personaCorreoElectronico.setIpCreacion(as_userId);

				ll_idCorreoElectronico = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager)
						                               .insertOrUpdate(lpce_personaCorreoElectronico, true);
			}

			String ls_idTelefonoMovil;
			String ls_idDireccion;

			ls_idTelefonoMovil     = null;
			ls_idDireccion         = null;

			if(lb_devoluciones)
			{
				PersonaTelefono lpt_personaTelefono;

				lpt_personaTelefono = lpt_DAO.findMaxByIdTelefonoTipoTelefonoTelefono(
					    ls_idPersona, EstadoCommon.F, ls_telefonoFijo
					);

				if(lpt_personaTelefono == null)
				{
					lpt_personaTelefono = new PersonaTelefono();

					lpt_personaTelefono.setIdPersona(ls_idPersona);
					lpt_personaTelefono.setTipoTelefono(EstadoCommon.F);
					lpt_personaTelefono.setTelefono(ls_telefonoFijo);
					lpt_personaTelefono.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					lpt_personaTelefono.setIdDepartamento(IdentificadoresCommon.INDICATIVO_BOGOTA_DEPARTAMENTO);
					lpt_personaTelefono.setFechaDesde(new Date());
					lpt_personaTelefono.setIdUsuarioCreacion(as_userId);
					lpt_personaTelefono.setIpCreacion(as_remoteIp);

					lpt_DAO.insertOrUpdate(lpt_personaTelefono, true);
				}

				lpt_personaTelefono = lpt_DAO.findMaxByIdTelefonoTipoTelefonoTelefono(
					    ls_idPersona, EstadoCommon.M, ls_telefonoMovil
					);

				if(lpt_personaTelefono == null)
				{
					lpt_personaTelefono = new PersonaTelefono();

					lpt_personaTelefono.setIdPersona(ls_idPersona);
					lpt_personaTelefono.setTipoTelefono(EstadoCommon.M);
					lpt_personaTelefono.setTelefono(ls_telefonoMovil);
					lpt_personaTelefono.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					lpt_personaTelefono.setIdDepartamento(IdentificadoresCommon.INDICATIVO_BOGOTA_DEPARTAMENTO);
					lpt_personaTelefono.setFechaDesde(new Date());
					lpt_personaTelefono.setIdUsuarioCreacion(as_userId);
					lpt_personaTelefono.setIpCreacion(as_remoteIp);

					ls_idTelefonoMovil = StringUtils.getString(lpt_DAO.insertOrUpdate(lpt_personaTelefono, true));
				}

				if(lb_validarempresaDireccionCorrespondencia)
					ls_idDireccion = obtenerIdDireccionDesdeJSON(
						    ls_empresaDireccionCorrespondencia, ls_idPersona, as_userId, as_remoteIp, adm_manager
						);
			}

			String ls_idSolicitud;
			int    li_token;

			li_token = -1;

			if(
			    as_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_4)
				    && as_idSubproceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
				    && BooleanUtils.getBooleanValue(ls_solicitanteInterviniente)
			)
				li_token = obtenerToken();

			String       ls_nir;
			SolicitudDAO lsd_solicitudDAO;
			Solicitud    ls_solicitud;

			lsd_solicitudDAO     = DaoCreator.getSolicitudDAO(adm_manager);
			ls_solicitud         = new Solicitud();
			ls_nir               = null;

			{
				int li_idSolicitud;

				li_idSolicitud     = lsd_solicitudDAO.findSecuence();

				ls_idSolicitud = StringUtils.getString(li_idSolicitud);
			}

			ls_solicitud.setIdSolicitud(ls_idSolicitud);
			ls_solicitud.setIdProceso(as_idProceso);
			ls_solicitud.setIdSubproceso(as_idSubproceso);
			ls_solicitud.setIdEtapaActual(lb_devoluciones ? null : Long.valueOf(EtapaCommon.ID_ETAPA_DIGITALIZACION));
			ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.SEDE_ELECTRONICA);
			ls_solicitud.setIdTurnoAnt(ls_turno);
			ls_solicitud.setIdPersona(ls_idPersona);
			ls_solicitud.setFechaSolicitud(new Date());
			ls_solicitud.setIdCalidadSolicitante(ls_calidadSolicitante);
			ls_solicitud.setIdCorreoElectronico(StringUtils.getString(ll_idCorreoElectronico));
			ls_solicitud.setIdCorreoElectronicoComunicacion(StringUtils.getString(ll_idCorreoElectronico));
			ls_solicitud.setIdTelefono(ls_idTelefonoMovil);
			ls_solicitud.setIdTelefonoComunicacion(ls_idTelefonoMovil);
			ls_solicitud.setIdDireccion(ls_idDireccion);
			ls_solicitud.setIdDireccionComunicacion(ls_idDireccion);
			ls_solicitud.setIdAutorizacionNotificacion(IdentificadoresCommon.NUM1);
			ls_solicitud.setIdAutorizacionComunicacion(IdentificadoresCommon.NUM1);
			ls_solicitud.setDerechoPeticion(EstadoCommon.N);
			ls_solicitud.setParticipaInterviniente(EstadoCommon.N);
			ls_solicitud.setDigitalizada(EstadoCommon.N);
			ls_solicitud.setComentario(ls_observacion);
			ls_solicitud.setMotivoConsulta(ls_motivoSolicitud);
			ls_solicitud.setIdUsuarioCreacion(as_userId);
			ls_solicitud.setIpCreacion(as_remoteIp);

			{
				ls_nir = crearNir(as_userId, as_remoteIp, adm_manager);

				if(!StringUtils.isValidString(ls_nir))
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_GENERANDO_NIR));

				ls_solicitud.setNir(ls_nir);
			}

			lsd_solicitudDAO.insertOrUpdate(ls_solicitud, true);

			if(li_token > NumericUtils.DEFAULT_INT_VALUE)
				ls_nir = ls_nir + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
					+ Digest.digest(Integer.toString(li_token), "SHA-256");

			lmss_resultado.put(IdentificadoresCommon.NIR, ls_nir);

			if(StringUtils.isValidString(ls_documentosSolicitados))
			{
				Collection<String> lcs_documentosSolicitados;

				lcs_documentosSolicitados = separarPorSimboloComa(ls_documentosSolicitados, true);

				if(CollectionUtils.isValidCollection(lcs_documentosSolicitados))
				{
					for(String ls_documentoSolicitado : lcs_documentosSolicitados)
					{
						if(StringUtils.isValidString(ls_documentoSolicitado))
						{
							AccCompletitudDocumental lacd_accCompletitudDocumental;

							lacd_accCompletitudDocumental = new AccCompletitudDocumental();

							lacd_accCompletitudDocumental.setIdSolicitud(ls_idSolicitud);
							lacd_accCompletitudDocumental.setIdProceso(as_idProceso);
							lacd_accCompletitudDocumental.setIdSubproceso(as_idSubproceso);
							lacd_accCompletitudDocumental.setIdTipoDocumental(ls_documentoSolicitado);
							lacd_accCompletitudDocumental.setEntregado(EstadoCommon.N);
							lacd_accCompletitudDocumental.setObligatorioTipoDocumental(EstadoCommon.S);
							lacd_accCompletitudDocumental.setIdUsuarioCreacion(as_userId);
							lacd_accCompletitudDocumental.setIpCreacion(as_remoteIp);

							DaoCreator.getAccCompletitudDocumentalDAO(adm_manager).insert(
							    lacd_accCompletitudDocumental
							);
						}
					}
				}
			}

			if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_4)
					|| as_idProceso.equals(ProcesoCommon.ID_PROCESO_39)
					|| as_idProceso.equals(ProcesoCommon.ID_PROCESO_45)
					|| as_idProceso.equals(ProcesoCommon.ID_PROCESO_47)
					|| as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
					|| as_idProceso.equals(ProcesoCommon.ID_PROCESO_41)
					)
			{
				{
					SolicitudAsociada lsa_solicitudAsociada;

					lsa_solicitudAsociada = new SolicitudAsociada();

					lsa_solicitudAsociada.setIdSolicitud(lt_turno.getIdSolicitud());
					lsa_solicitudAsociada.setIdSolicitud1(ls_idSolicitud);
					lsa_solicitudAsociada.setIndVinculado(EstadoCommon.N);
					lsa_solicitudAsociada.setIdUsuarioCreacion(as_userId);
					lsa_solicitudAsociada.setIpCreacion(as_remoteIp);

					DaoCreator.getSolicitudAsociadaDAO(adm_manager).insertOrUpdate(lsa_solicitudAsociada, true);
				}

				{
					AlertaTurnoTramite latt_alertaTurnoTramite;
					String             ls_alertaTramite;

					latt_alertaTurnoTramite = new AlertaTurnoTramite();

					latt_alertaTurnoTramite.setIdTurnoAfectado(ls_turno);
					latt_alertaTurnoTramite.setIdSolicitud(lt_turno.getIdSolicitud());
					latt_alertaTurnoTramite.setIdSolicitudVinculada(ls_idSolicitud);
					latt_alertaTurnoTramite.setActivo(EstadoCommon.SI);
					latt_alertaTurnoTramite.setIdUsuarioCreacion(as_userId);
					latt_alertaTurnoTramite.setIpCreacion(as_remoteIp);

					switch(as_idProceso)
					{
						case ProcesoCommon.ID_PROCESO_39:
							ls_alertaTramite = AlertaTramiteCommon.EN_TRAMITE_DE_DESISTIMIENTO;

							break;

						case ProcesoCommon.ID_PROCESO_45:
							ls_alertaTramite = AlertaTramiteCommon.EN_TRAMITE_DE_SUSPENSION;

							break;

						case ProcesoCommon.ID_PROCESO_47:
							ls_alertaTramite = AlertaTramiteCommon.EN_TRAMITE_DE_INTERPOSICION_DE_RECURSO;

							break;

						case ProcesoCommon.ID_PROCESO_40:
							ls_alertaTramite = AlertaTramiteCommon.TRAMITE_DE_ENTREGA_DE_DOCUMENTOS;

							break;
						
						case ProcesoCommon.ID_PROCESO_41:
							ls_alertaTramite = AlertaTramiteCommon.EN_TRAMITE_DE_PRORROGA;

							break;

						case ProcesoCommon.ID_PROCESO_48:
							ls_alertaTramite = AlertaTramiteCommon.TRAMITE_DE_ENTREGA_DE_DOCUMENTOS_SEGUNDA_INSTANCIA;
							
							break;


						default:
							ls_alertaTramite = null;

							break;
					}

					if(StringUtils.isValidString(ls_alertaTramite))
					{
						Collection<String>    lcs_turnosAsociados;
						AlertaTurnoTramiteDAO lattd_alertaTurnoTramiteDAO;

						latt_alertaTurnoTramite.setIdAlertaTramite(ls_alertaTramite);
						lcs_turnosAsociados             = obtenerColeccionTurnosBitacoraBloqueo(ls_turno, adm_manager);
						lattd_alertaTurnoTramiteDAO     = DaoCreator.getAlertaTurnoTramiteDAO(adm_manager);

						if(CollectionUtils.isValidCollection(lcs_turnosAsociados))
						{
							for(String ls_turnoAsociado : lcs_turnosAsociados)
							{
								if(StringUtils.isValidString(ls_turnoAsociado))
								{
									latt_alertaTurnoTramite.setIdTurnoAsociado(ls_turnoAsociado);

									lattd_alertaTurnoTramiteDAO.insertarAlerta(latt_alertaTurnoTramite);
								}
							}
						}
						else
						{
							latt_alertaTurnoTramite.setIdTurnoAsociado(null);

							lattd_alertaTurnoTramiteDAO.insertarAlerta(latt_alertaTurnoTramite);
						}
					}
				}
			}
			
			if(lb_validacionesCirculoMatricula)
			{
				TrasladoMatricula ltm_trasladoMatricula;

				ltm_trasladoMatricula = new TrasladoMatricula();

				ltm_trasladoMatricula.setIdSolicitud(ls_idSolicitud);
				ltm_trasladoMatricula.setIdCirculoOrigen(ls_idCirculo);
				ltm_trasladoMatricula.setIdCirculoDestino(ls_idCirculoDestino);
				ltm_trasladoMatricula.setIdMatriculaOrigen(NumericUtils.getLongWrapper(ls_idMatricula));
				ltm_trasladoMatricula.setActivo(EstadoCommon.S);
				ltm_trasladoMatricula.setIdUsuarioCreacion(as_userId);
				ltm_trasladoMatricula.setIpCreacion(as_remoteIp);

				DaoCreator.getTrasladoMatriculaDAO(adm_manager).insert(ltm_trasladoMatricula);
			}
			else if(
			    lb_devoluciones
				    && !(as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
				    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
			)
			{
				if(StringUtils.isValidString(ls_idPersonaInterviniente))
				{
					PersonaPresentada lpp_personaRepresentada;

					lpp_personaRepresentada = new PersonaPresentada();

					lpp_personaRepresentada.setIdSolicitud(ls_idSolicitud);
					lpp_personaRepresentada.setIdPersonaApoderado(ls_idPersona);
					lpp_personaRepresentada.setIdCalidadSolicitante(CalidadSolicitanteCommon.APODERADO);
					lpp_personaRepresentada.setIdPersonaRepresentadaApoderado(ls_idPersonaInterviniente);
					lpp_personaRepresentada.setIdCalidadPersonaRepresentada(CalidadSolicitanteCommon.INTERESADO);
					lpp_personaRepresentada.setIdUsuarioCreacion(as_userId);
					lpp_personaRepresentada.setIpCreacion(as_remoteIp);

					DaoCreator.getPersonaPresentadaDAO(adm_manager).insert(lpp_personaRepresentada);
				}

				{
					String           ls_idDevolucionDinero;
					DevolucionDinero ldd_devolucionDinero;

					ldd_devolucionDinero      = new DevolucionDinero();
					ls_idDevolucionDinero     = null;

					ldd_devolucionDinero.setIdSolicitud(ls_idSolicitud);
					ldd_devolucionDinero.setIdTurnoDevolucion(ls_turno);
					ldd_devolucionDinero.setIntervieneTramite(ls_solicitanteInterviniente);
					ldd_devolucionDinero.setIdEntidadRecaudadora(ls_idEntidadRecaudadora);
					ldd_devolucionDinero.setCodEntidadConsignacionErrada(ls_codigoEntidadFinancieraConsignacion);
					ldd_devolucionDinero.setTipoConsignacion(ls_tipoConsignacion);
					ldd_devolucionDinero.setNumeroConsignacionErrada(ls_numeroTipoConsignacion);
					ldd_devolucionDinero.setValorConsignacionErrada(
					    NumericUtils.getDoubleWrapper(ls_valorConsignacion)
					);
					ldd_devolucionDinero.setFechaConsignacionErrada(ld_fechaConsignacion);
					ldd_devolucionDinero.setNumeroCuentaConsignacionErrada(ls_numeroCuentaBancariaConsignacion);
					ldd_devolucionDinero.setCodigoEntidadFinancieraDevolucion(ls_codigoEntidadFinanciera);
					ldd_devolucionDinero.setNumeroCuenta(ls_numeroCuenta);
					ldd_devolucionDinero.setTipoCuenta(ls_tipoCuenta);
					ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersonaTitularCuenta);
					ldd_devolucionDinero.setTipoDevolucion(ls_tipoDevolucion);
					ldd_devolucionDinero.setCodigoCuentaCupo(ls_codigoCuentaCupo);
					ldd_devolucionDinero.setCodigoNotaCredito(ls_codigoNotaCredito);
					ldd_devolucionDinero.setExtemporaneo(ls_extemporaneo);
					ldd_devolucionDinero.setIdUsuarioCreacion(as_userId);
					ldd_devolucionDinero.setIpCreacion(as_remoteIp);

					ldd_devolucionDinero.setTipoTelefonoFijoTitular(EstadoCommon.F);
					ldd_devolucionDinero.setTipoTelefonoMovilTitular(EstadoCommon.M);

					if(li_token > NumericUtils.DEFAULT_INT_VALUE)
						ldd_devolucionDinero.setTokenVerificacion(Integer.valueOf(li_token));

					ls_idDevolucionDinero = DaoCreator.getDevolucionDineroDAO(adm_manager).insert(ldd_devolucionDinero);

					if(lt_turno != null)
					{
						String ls_idCirculoTurno;
						String ls_idSolicitudTurno;

						ls_idCirculoTurno       = lt_turno.getIdCirculo();
						ls_idSolicitudTurno     = lt_turno.getIdSolicitud();

						if(
						    StringUtils.isValidString(ls_idCirculoTurno)
							    && StringUtils.isValidString(ls_idSolicitudTurno)
						)
						{
							Collection<String>      lcs_valores;
							ActoDevolucionDineroDAO laddd_DAO;
							ActoDAO                 lad_DAO;

							laddd_DAO       = DaoCreator.getActoDevolucionDineroDAO(adm_manager);
							lad_DAO         = DaoCreator.getActoDAO(adm_manager);
							lcs_valores     = separarPorSimboloComa(ls_actos, true);

							if(CollectionUtils.isValidCollection(lcs_valores))
							{
								for(String ls_tipoActoactual : lcs_valores)
								{
									if(ls_tipoActoactual != null)
									{
										Acto la_acto;

										la_acto = lad_DAO.findBySolicitudIdCirculoTipoActo(
											    new Acto(ls_idSolicitudTurno, ls_idCirculoTurno, ls_tipoActoactual)
											);

										if(la_acto != null)
										{
											ActoDevolucionDinero ladd_acto;

											ladd_acto = new ActoDevolucionDinero();

											ladd_acto.setIdDevolucionDinero(ls_idDevolucionDinero);
											ladd_acto.setIdActoDevolucionDinero(la_acto.getIdActo());
											ladd_acto.setVersionActo(String.valueOf(la_acto.getVersion()));
											ladd_acto.setIdSolicitudInicial(ls_idSolicitudTurno);
											ladd_acto.setIdSolicitud(ls_idSolicitud);
											ladd_acto.setValorTotalLiquidado(NumericUtils.DEFAULT_LONG_VALUE);
											ladd_acto.setIdUsuarioCreacion(as_userId);
											ladd_acto.setIpCreacion(as_remoteIp);

											laddd_DAO.insert(ladd_acto);
										}
									}
								}
							}
						}
					}
				}
			}

			if(
			    StringUtils.isValidString(ls_idPersonaPoderdante)
				    && (as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
				    || as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
			)
			{
				PersonaPresentada lpp_personaRepresentada;

				lpp_personaRepresentada = new PersonaPresentada();

				lpp_personaRepresentada.setIdSolicitud(ls_idSolicitud);
				lpp_personaRepresentada.setIdPersonaApoderado(ls_idPersonaPoderdante);
				lpp_personaRepresentada.setIdCalidadSolicitante(CalidadSolicitanteCommon.APODERADO);
				lpp_personaRepresentada.setIdPersonaRepresentadaApoderado(ls_idPersona);
				lpp_personaRepresentada.setIdCalidadPersonaRepresentada(CalidadSolicitanteCommon.INTERESADO);
				lpp_personaRepresentada.setIdUsuarioCreacion(as_userId);
				lpp_personaRepresentada.setIpCreacion(as_remoteIp);

				DaoCreator.getPersonaPresentadaDAO(adm_manager).insert(lpp_personaRepresentada);
			}

			if(
			    as_idProceso.equals(ProcesoCommon.ID_PROCESO_40)
				    || as_idSubproceso.equals(ProcesoCommon.ID_SUBPROCESO_4)
			)
			{
				{
					DevolucionDinero    ldd_devolucionDinero;
					DevolucionDineroDAO lddd_DAO;

					lddd_DAO                 = DaoCreator.getDevolucionDineroDAO(adm_manager);
					ldd_devolucionDinero     = lddd_DAO.findByIdturno(ls_turno);

					if(ldd_devolucionDinero != null)
					{
						ldd_devolucionDinero.setCodigoEntidadFinancieraDevolucion(ls_codigoEntidadFinanciera);
						ldd_devolucionDinero.setNumeroCuenta(ls_numeroCuenta);
						ldd_devolucionDinero.setTipoCuenta(ls_tipoCuenta);
						ldd_devolucionDinero.setIdPersonaTitularCuenta(ls_idPersonaTitularCuenta);
						ldd_devolucionDinero.setIdUsuarioModificacion(as_userId);
						ldd_devolucionDinero.setIpModificacion(as_remoteIp);

						lddd_DAO.update(ldd_devolucionDinero);
					}
				}
			}

				byte[]   lba_pdf;
				Registro lr_registro;

				lr_registro = new Registro();

				lr_registro.setNirProceso(ls_nir);
				lr_registro.setFechaCreacion(new Date());
				lr_registro.setSolicitud(ls_solicitud);

				if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_40))
					lr_registro.setRecepcionDocumentos(true);

				if(as_idProceso.equals(ProcesoCommon.ID_PROCESO_41))
					lr_registro.setEsSuspensionEntregaDocumentacion(true);

				lr_registro.setIdTurno(ls_turno);

				lba_pdf = getRegistroBusiness().crearPdfRegistro(lr_registro, as_userId, adm_manager);

				if(lba_pdf != null)
				{
					Imagenes         li_imagenes;
					DocumentosSalida lds_documentosSalida;
					long             ll_secuencia;

					li_imagenes              = new Imagenes();
					lds_documentosSalida     = new DocumentosSalida();

					li_imagenes.setImagenBlob(lba_pdf);

					li_imagenes.setTipoArchivo(ExtensionCommon.PDF_MAYUS);
					li_imagenes.setIdUsuarioCreacion(as_userId);
					li_imagenes.setIpCreacion(as_remoteIp);

					ll_secuencia = DaoCreator.getImagenesDAO(adm_manager).insertOrUpdate(li_imagenes, true);

					if(ll_secuencia <= 0)
						throw new B2BException(com.bachue.snr.prosnr01.common.constants.ErrorKeys.NO_INSERTO_IMAGENES);

					lds_documentosSalida.setIdImagen(NumericUtils.getLongWrapper(ll_secuencia));
					lds_documentosSalida.setTipo(TipoArchivoCommon.SOLICITUD);
					lds_documentosSalida.setEstado(EstadoCommon.ACTIVO);
					lds_documentosSalida.setIdSolicitud(ls_solicitud.getIdSolicitud());
					lds_documentosSalida.setIdTipoDocumental(TipoDocumentalCommon.RESUMEN_DE_LA_SOLICITUD);
					lds_documentosSalida.setRepositorio(RepositorioCommon.FINAL);
					lds_documentosSalida.setIdUsuarioCreacion(as_userId);
					lds_documentosSalida.setIpCreacion(as_remoteIp);

					DaoCreator.getDocumentosSalidaDAO(adm_manager).insertOrUpdate(lds_documentosSalida, true);

					lr_registro.setPdf(lba_pdf);
					lr_registro.setNirProceso(ls_nir);
				}

			DaoCreator.getProcedimientosDAO(adm_manager)
				          .spCreateStage(new TurnoHistoria(ls_idSolicitud, as_userId, as_remoteIp));

			if(li_token > NumericUtils.DEFAULT_INT_VALUE)
			{
				String    ls_idSolicitudTurno;
				Solicitud ls_solicitudTemporal;

				ls_idSolicitudTurno      = lt_turno.getIdSolicitud();
				ls_solicitudTemporal     = DaoCreator.getSolicitudDAO(adm_manager).findById(ls_idSolicitudTurno);

				if(ls_solicitudTemporal != null)
				{
					String ls_idPersonaRegistro;
					String ls_idMailRegistro;
					String ls_idTelefonoRegistro;

					ls_idPersonaRegistro      = ls_solicitudTemporal.getIdPersona();
					ls_idMailRegistro         = ls_solicitudTemporal.getIdCorreoElectronico();
					ls_idTelefonoRegistro     = ls_solicitudTemporal.getIdTelefono();

					if(!StringUtils.isValidString(ls_idMailRegistro))
						ls_idMailRegistro = ls_solicitudTemporal.getIdCorreoElectronicoComunicacion();

					if(!StringUtils.isValidString(ls_idTelefonoRegistro))
						ls_idTelefonoRegistro = ls_solicitudTemporal.getIdTelefonoComunicacion();

					if(StringUtils.isValidString(ls_idPersonaRegistro))
					{
						PersonaCorreoElectronico lpc_pc;
						PersonaTelefono          lpt_pt;

						lpc_pc     = lpced_DAO.findById(ls_idPersonaRegistro, ls_idMailRegistro);
						lpt_pt     = lpt_DAO.findById(ls_idPersonaRegistro, ls_idTelefonoRegistro);

						lth_turnoHistoria = DaoCreator.getTurnoHistoriaDAO(adm_manager)
								                          .findBySolicitudEtapa(
								    ls_idSolicitud, null, EtapaCommon.ID_ETAPA_INICIAL, null
								);

						if(lth_turnoHistoria != null)
						{
							MensajeComunicacion lmc_mensajeComunicacion;

							lmc_mensajeComunicacion = new MensajeComunicacion();

							{
								Long ll_idTurnoHistoria;
								ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

								if(NumericUtils.isValidLong(ll_idTurnoHistoria))
									lmc_mensajeComunicacion.setIdTurnoHistoria(ll_idTurnoHistoria.toString());
							}

							{
								Constantes lc_constante;

								lc_constante = DaoCreator.getConstantesDAO(adm_manager)
										                     .findById(ConstanteCommon.ID_COMPONENTE_BACHUE_DEFAULT);

								if(lc_constante != null)
									lmc_mensajeComunicacion.setIdOrigen(lc_constante.getCaracter());
							}

							lmc_mensajeComunicacion.setIdSolicitud(ls_idSolicitud);
							lmc_mensajeComunicacion.setIdUsuarioCreacion(as_userId);
							lmc_mensajeComunicacion.setIpCreacion(as_remoteIp);

							if(lpc_pc != null)
							{
								lmc_mensajeComunicacion.setCorreoElectronico(lpc_pc.getCorreoElectronico());
								lmc_mensajeComunicacion.setIdCanal(CanalCommon.ELECTRONICO);
								lmc_mensajeComunicacion.setIdPlantilla("200");

								DaoCreator.getMensajeComunicacionDAO(adm_manager)
									          .insertOrUpdate(lmc_mensajeComunicacion, true);
							}

							if(lpt_pt != null)
							{
								String ls_tipoTelefono;

								ls_tipoTelefono = lpt_pt.getTipoTelefono();

								if(
								    StringUtils.isValidString(ls_tipoTelefono)
									    && ls_tipoTelefono.equalsIgnoreCase(EstadoCommon.M)
								)
								{
									lmc_mensajeComunicacion.setNumeroCelular(lpt_pt.getTelefono());
									lmc_mensajeComunicacion.setIdCanal(CanalCommon.SMS);
									lmc_mensajeComunicacion.setIdPlantilla("201");

									DaoCreator.getMensajeComunicacionDAO(adm_manager)
										          .insertOrUpdate(lmc_mensajeComunicacion, true);
								}
							}

							if(!((lpc_pc != null) || (lpt_pt != null)))
							{
								ace_codigoSalida.setCodigoError(BigInteger.valueOf(531L));
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIOS_COMUNICACIONES));
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitudRegistro", lb2be_e);

			throw lb2be_e;
		}

		return lmss_resultado;
	}

	/**
	 * Inserta el correo electronico de una persona.
	 *
	 * @param as_correo            Objeto contenedor del correo a insertar
	 * @param as_idPersona            Id de la persona a asociar al correo
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return Número identificador del correo electrónico creado
	 * @throws B2BException             Si ocurre un error en base de datos
	 */
	private long insertarCorreoElectronico(
	    String as_correo, String as_idPersona, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_correo) || !StringUtils.isValidString(as_idPersona))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		long ll_idCorreoElectronico;

		ll_idCorreoElectronico = 0;

		if(!validarCorreoElectronico(as_correo))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CORREO_FORMATO_NO_VALIDO));

		{
			PersonaCorreoElectronicoDAO lpced_personaCorreoDAO;
			PersonaCorreoElectronico    lpce_personaCorreo;

			lpced_personaCorreoDAO     = DaoCreator.getPersonaCorreoElectronicoDAO(adm_manager);
			lpce_personaCorreo         = lpced_personaCorreoDAO.findByIdPersonaCorreoMax(as_idPersona, as_correo);

			if(lpce_personaCorreo == null)
				ll_idCorreoElectronico = insertarCorreoPersonaRepresentanteEntidad(
					    as_idPersona, as_correo, as_userId, as_remoteIp, lpced_personaCorreoDAO
					);
		}

		return ll_idCorreoElectronico;
	}

	/**
	 * Crea la cuenta cupo de una entidad externa.
	 *
	 * @param amss_llavesValor de amss llaves valor
	 * @param as_idSolicitud de as id solicitud
	 * @param al_idEntidadExterna            id de la entidad externa asociada a la cuenta cupo a crear
	 * @param adm_manager            Coneción a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return número identificador del la cuenta cupo creada
	 * @throws B2BException             Si ocurre un error en base de datos
	 */
	private long insertarCuentaCupo(
	    Map<String, String> amss_llavesValor, String as_idSolicitud, long al_idEntidadExterna, DAOManager adm_manager,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(amss_llavesValor) || !StringUtils.isValidString(as_idSolicitud))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		CuentaCupo lcc_cuentaCupo;

		lcc_cuentaCupo = new CuentaCupo();

		lcc_cuentaCupo.setIdEntidadExterna(StringUtils.getString(al_idEntidadExterna));

		{
			int           li_anio;
			ConstantesDAO lcd_constantesDAO;
			StringBuilder lsb_idConstante;
			StringBuilder lsb_codigo;
			String        ls_idConstante;
			Constantes    lc_constante;

			li_anio               = LocalDate.now().getYear();
			lcd_constantesDAO     = DaoCreator.getConstantesDAO(adm_manager);

			lsb_idConstante = new StringBuilder(ConstanteCommon.CODIGO_CUENTA_CUPO);

			lsb_idConstante.append(IdentificadoresCommon.SIMBOLO_GUION_BAJO);
			lsb_idConstante.append(li_anio);

			lsb_codigo = new StringBuilder(IdentificadoresCommon.CC);

			lsb_codigo.append(li_anio);

			ls_idConstante     = lsb_idConstante.toString();
			lc_constante       = lcd_constantesDAO.findById(ls_idConstante);

			if(lc_constante != null)
			{
				BigInteger lbi_entero;

				lbi_entero = lc_constante.getEntero();

				if(NumericUtils.isValidBigInteger(lbi_entero))
				{
					lbi_entero = lbi_entero.add(BigInteger.valueOf(1L));

					lsb_codigo.append(lbi_entero);

					lcc_cuentaCupo.setCodigo(lsb_codigo.toString());

					lc_constante.setEntero(lbi_entero);
					lc_constante.setIdUsuarioModificacion(as_userId);
					lc_constante.setIpModificacion(as_userId);

					lcd_constantesDAO.insertOrUpdate(lc_constante, false);
				}
			}
			else
			{
				lc_constante = new Constantes();

				lc_constante.setIdConstante(ls_idConstante);
				lc_constante.setEntero(BigInteger.valueOf(1L));
				lc_constante.setDescripcion(
				    "CONSTANTE PARA ALMACENAR EL CONSECUTIVO DE LOS CODIGOS DE LAS CUENTA CUPO CREADAS EN EL ANIO "
				    + li_anio
				);
				lc_constante.setIdUsuarioCreacion(as_userId);
				lc_constante.setIpCreacion(as_remoteIp);
				lc_constante.setTipo(EstadoCommon.A);

				lcd_constantesDAO.insertOrUpdate(lc_constante, true);

				lsb_codigo.append(BigInteger.valueOf(1L));

				lcc_cuentaCupo.setCodigo(lsb_codigo.toString());
			}
		}

		lcc_cuentaCupo.setEstado(EstadoCommon.EN_PROCESO_TXT);
		lcc_cuentaCupo.setValorMaximo(BigDecimal.ZERO);
		lcc_cuentaCupo.setValorMinimo(BigDecimal.ZERO);
		lcc_cuentaCupo.setSaldo(BigDecimal.ZERO);
		lcc_cuentaCupo.setMigrado(EstadoCommon.N);
		lcc_cuentaCupo.setIdUsuarioCreacion(as_userId);
		lcc_cuentaCupo.setIpCreacion(as_remoteIp);

		{
			BigDecimal lbd_montoSolicitado;

			lbd_montoSolicitado = NumericUtils.getBigDecimal(
				    amss_llavesValor.get(ConstantesCriterioCuentaCupo.MONTO_SOLICITADO)
				);

			if(!NumericUtils.isValidBigDecimal(lbd_montoSolicitado))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_MONTO_SOLICITADO_NO_VALIDO));

			lcc_cuentaCupo.setMontoSolicitado(lbd_montoSolicitado);
		}

		{
			String ls_descripcionFrecuencia;

			ls_descripcionFrecuencia = amss_llavesValor.get(
				    ConstantesCriterioCuentaCupo.DESCRIPCION_FRECUENCIA_RECARGAS
				);

			if(ls_descripcionFrecuencia.length() > 4000)
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstantesCriterioCuentaCupo.DESCRIPCION_FRECUENCIA_RECARGAS;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args));
			}

			lcc_cuentaCupo.setDescripcionFrecuenciaRecargas(ls_descripcionFrecuencia);
		}

		{
			String ls_descripcionNecesidad;

			ls_descripcionNecesidad = amss_llavesValor.get(
				    ConstantesCriterioCuentaCupo.DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO
				);

			if(ls_descripcionNecesidad.length() > 4000)
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstantesCriterioCuentaCupo.DESCRIPCION_NECESIDAD_DE_CUENTA_CUPO;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args));
			}

			lcc_cuentaCupo.setDescripcionNecesidadDeCuentaCupo(ls_descripcionNecesidad);
		}

		{
			String ls_descripcionOrigen;

			ls_descripcionOrigen = amss_llavesValor.get(ConstantesCriterioCuentaCupo.DESCRIPCION_ORIGEN_RECURSOS);

			if(ls_descripcionOrigen.length() > 4000)
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstantesCriterioCuentaCupo.DESCRIPCION_ORIGEN_RECURSOS;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args));
			}

			lcc_cuentaCupo.setDescripcionOrigenRecursos(ls_descripcionOrigen);
		}

		lcc_cuentaCupo.setIdSolicitud(as_idSolicitud);

		{
			String ls_ocupacionRepresentante;

			ls_ocupacionRepresentante = amss_llavesValor.get(
				    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_OCUPACION_PROFESION
				);

			if(StringUtils.isValidString(ls_ocupacionRepresentante) && (ls_ocupacionRepresentante.length() > 400))
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_OCUPACION_PROFESION;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args));
			}

			lcc_cuentaCupo.setRepresentanteLegalOcupacionProfesion(ls_ocupacionRepresentante);
		}

		return DaoCreator.getCuentaCupoDAO(adm_manager).insert(lcc_cuentaCupo);
	}

	/**
	 * Inserta la información de la entidad externa.
	 *
	 * @param amss_llavesValor            Objeto contenedor de la información de la entidad externa
	 * @param ace_codigoSalida de ace codigo salida
	 * @param as_idPersona            id de la persona a asociar como representante legal
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return Número identificador de la entidad externa creada
	 * @throws B2BException             Si ocurre un error en base de datos
	 */
	private long insertarEntidadExterna(
	    Map<String, String> amss_llavesValor, CodigoError ace_codigoSalida, String as_idPersona, DAOManager adm_manager,
	    String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(amss_llavesValor) || !StringUtils.isValidString(as_idPersona))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		AccEntidadExternaDAO laeed_entidadExternaDAO;
		String               ls_tipoDocumentoEmpresa;
		String               ls_numeroDocumentoEmpresa;
		long                 ll_idEntidadExterna;

		laeed_entidadExternaDAO       = DaoCreator.getAccEntidadExternaDAO(adm_manager);
		ls_tipoDocumentoEmpresa       = amss_llavesValor.get(ConstantesCriterioCuentaCupo.EMPRESA_TIPO_DOCUMENTO);
		ls_numeroDocumentoEmpresa     = amss_llavesValor.get(ConstantesCriterioCuentaCupo.EMPRESA_NUMERO_DOCUMENTO);

		validarTipoDocumento(ls_tipoDocumentoEmpresa, ace_codigoSalida, adm_manager);

		validarNumeroDocumento(ls_numeroDocumentoEmpresa);

		{
			AccEntidadExterna laee_entidad;

			laee_entidad = laeed_entidadExternaDAO.findByTipoDocNumDoc(
				    ls_tipoDocumentoEmpresa, ls_numeroDocumentoEmpresa
				);

			if(laee_entidad != null)
			{
				laee_entidad.setNombre(
				    validarRazonSocial(amss_llavesValor.get(ConstantesCriterioCuentaCupo.RAZON_SOCIAL))
				);
				laee_entidad.setIdRepresentanteLegal(as_idPersona);
				laee_entidad.setIdUsuarioModificacion(as_userId);
				laee_entidad.setIpModificacion(as_remoteIp);

				laeed_entidadExternaDAO.update(laee_entidad);

				ll_idEntidadExterna = NumericUtils.getLong(laee_entidad.getIdEntidadExterna());
			}
			else
			{
				laee_entidad = new AccEntidadExterna();

				laee_entidad.setIdDocumentoTipo(ls_tipoDocumentoEmpresa);
				laee_entidad.setNumeroDocumento(ls_numeroDocumentoEmpresa);
				laee_entidad.setNombre(
				    validarRazonSocial(amss_llavesValor.get(ConstantesCriterioCuentaCupo.RAZON_SOCIAL))
				);

				{
					String ls_idActividadEconomica;

					ls_idActividadEconomica = StringUtils.getStringNotNull(
						    amss_llavesValor.get(ConstantesCriterioCuentaCupo.DESCRIPCION_ACTIVIDAD_ECONOMICA)
						);

					if(ls_idActividadEconomica.length() > 20)
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.DESCRIPCION_ACTIVIDAD_ECONOMICA;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					laee_entidad.setIdActividadEconomica(ls_idActividadEconomica);
				}

				laee_entidad.setIdTipoEntidad("5");

				{
					String ls_direccion;

					ls_direccion = amss_llavesValor.get(ConstantesCriterioCuentaCupo.EMPRESA_DIRECCION);

					if(ls_direccion.length() > 255)
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.DESCRIPCION_ACTIVIDAD_ECONOMICA;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					laee_entidad.setDireccion(ls_direccion);
				}

				laee_entidad.setIdRepresentanteLegal(as_idPersona);
				laee_entidad.setIdTipoOficina(TipoOficinaCommon.PARTICULAR);
				laee_entidad.setEntidadExenta(EstadoCommon.N);
				laee_entidad.setActivo(EstadoCommon.S);
				laee_entidad.setIdUsuarioCreacion(as_userId);
				laee_entidad.setIpCreacion(as_remoteIp);

				ll_idEntidadExterna = laeed_entidadExternaDAO.insert(laee_entidad);
			}
		}

		return ll_idEntidadExterna;
	}

	/**
	 * Inserta un representante en la tabla de persona.
	 *
	 * @param amss_llavesValor            Objeto contenedor de la información del proceso
	 * @param ace_codigoSalida            Objeto contenedor del código de salida
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return id de la persona creada
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private Persona insertarRepresentante(
	    Map<String, String> amss_llavesValor, CodigoError ace_codigoSalida, DAOManager adm_manager, String as_userId,
	    String as_remoteIp
	)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(amss_llavesValor))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		PersonaDAO lpd_personaDAO;
		Persona    lp_persona;

		lpd_personaDAO     = DaoCreator.getPersonaDAO(adm_manager);
		lp_persona         = new Persona();

		{
			String ls_tipoDocumento;

			ls_tipoDocumento = StringUtils.getStringNotNull(
				    amss_llavesValor.get(ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_TIPO_DOCUMENTO)
				);

			validarTipoDocumento(ls_tipoDocumento, ace_codigoSalida, adm_manager, false);

			lp_persona.setIdDocumentoTipo(ls_tipoDocumento);

			if(ls_tipoDocumento.equals(IdentificadoresCommon.NIT))
			{
				lp_persona.setIdTipoPersona(EstadoCommon.J);

				{
					String ls_razonSocial;

					ls_razonSocial = amss_llavesValor.get(ConstantesCriterioCuentaCupo.RAZON_SOCIAL);

					if(ls_razonSocial.length() > 500)
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.RAZON_SOCIAL;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					lp_persona.setRazonSocial(ls_razonSocial);
				}
			}
			else
			{
				lp_persona.setIdTipoPersona(EstadoCommon.N);

				{
					String ls_primerNombre;

					ls_primerNombre = amss_llavesValor.get(
						    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_PRIMER_NOMBRE
						);

					if(StringUtils.isValidString(ls_primerNombre) && (ls_primerNombre.length() > 200))
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_PRIMER_NOMBRE;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					lp_persona.setPrimerNombre(ls_primerNombre);
				}

				{
					String ls_segundoNombre;

					ls_segundoNombre = amss_llavesValor.get(
						    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_SEGUNDO_NOMBRE
						);

					if(StringUtils.isValidString(ls_segundoNombre) && (ls_segundoNombre.length() > 200))
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_SEGUNDO_NOMBRE;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					lp_persona.setSegundoNombre(ls_segundoNombre);
				}

				{
					String ls_primerApellido;

					ls_primerApellido = amss_llavesValor.get(
						    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_PRIMER_APELLIDO
						);

					if(StringUtils.isValidString(ls_primerApellido) && (ls_primerApellido.length() > 200))
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_PRIMER_APELLIDO;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					lp_persona.setPrimerApellido(ls_primerApellido);
				}

				{
					String ls_segundoApellido;

					ls_segundoApellido = amss_llavesValor.get(
						    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_SEGUNDO_APELLIDO
						);

					if(StringUtils.isValidString(ls_segundoApellido) && (ls_segundoApellido.length() > 200))
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_SEGUNDO_APELLIDO;

						throw new B2BException(
						    addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args)
						);
					}

					lp_persona.setSegundoApellido(ls_segundoApellido);
				}
			}
		}

		{
			String ls_numeroDocumento;

			ls_numeroDocumento = amss_llavesValor.get(
				    ConstantesCriterioCuentaCupo.REPRESENTANTE_LEGAL_NUMERO_DOCUMENTO
				);

			validarNumeroDocumento(ls_numeroDocumento);

			lp_persona.setNumeroDocumento(ls_numeroDocumento);
		}

		lp_persona.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
		lp_persona.setOrigenBachue(EstadoCommon.S);

		{
			Persona lp_personaBusqueda;

			lp_personaBusqueda = lpd_personaDAO.findByDocNameMaxId(lp_persona);

			if(lp_personaBusqueda != null)
			{
				lp_persona.setIdPersona(lp_personaBusqueda.getIdPersona());
				lp_persona.setIdUsuarioModificacion(as_userId);
				lp_persona.setIpModificacion(as_remoteIp);

				lpd_personaDAO.insertOrUpdate(lp_persona, false);
			}
			else
			{
				lp_persona.setIdUsuarioCreacion(as_userId);
				lp_persona.setIpCreacion(as_remoteIp);

				lp_persona = lpd_personaDAO.insertOrUpdate(lp_persona, true);

				if(lp_persona == null)
				{
					ace_codigoSalida.setCodigoError(BigInteger.valueOf(482L));

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INSERTANDO_REPRESENTANTE));
				}
			}
		}

		return lp_persona;
	}

	/**
	 * Inserta el teléfono de una persona.
	 *
	 * @param as_telefono            Objeto contenedor del teléfono a insertar
	 * @param as_tipoDocumento            tipo de documento asociado al dueño del teléfono
	 * @param as_numeroDocumento            número de documento asociado al dueño del teléfono
	 * @param as_idPersona            Id de la persona a asociar al correo
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejecuta la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return el valor de long
	 * @throws B2BException             Si ocurre un error en base de datos
	 */
	private long insertarTelefonoCelular(
	    String as_telefono, String as_tipoDocumento, String as_numeroDocumento, String as_idPersona,
	    DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_telefono) || !StringUtils.isValidString(as_idPersona))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		long ll_idTelefono;

		ll_idTelefono = 0;

		validarTelefono(as_telefono, as_tipoDocumento, as_numeroDocumento, adm_manager);

		{
			PersonaTelefonoDAO lptd_personaTelefonoDAO;
			PersonaTelefono    lpt_personaTelefono;

			lptd_personaTelefonoDAO     = DaoCreator.getPersonaTelefonoDAO(adm_manager);
			lpt_personaTelefono         = lptd_personaTelefonoDAO.findByIdPersonaTelefonoMax(as_idPersona, as_telefono);

			if(lpt_personaTelefono == null)
				ll_idTelefono = insertarTelefonoPersonaRepresentanteEntidad(
					    as_idPersona, as_telefono, null, as_userId, as_remoteIp, lptd_personaTelefonoDAO
					);
		}

		return ll_idTelefono;
	}

	/**
	 * Crea una solicitud de modificación de cuenta cupo.
	 *
	 * @param amss_llavesValor            Objeto contenedor de los valores a modificar
	 * @param as_tipoDocumento            tipo de documento de la persona solicitante
	 * @param as_numeroDocumento            numero de documento de la persona solicitante
	 * @param ace_codigoSalida            Objeto contenedor del código de respuesta de la operación
	 * @param adm_manager            Conexión a la base de datos
	 * @param as_userId            Id del usuario que ejectua la acción
	 * @param as_remoteIp            Dirección IP del cliente que ejecuta la acción
	 * @return el valor de string
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private String modificarCuentaCupo(
	    Map<String, String> amss_llavesValor, String as_tipoDocumento, String as_numeroDocumento,
	    CodigoError ace_codigoSalida, DAOManager adm_manager, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		ModCuentaCupo lmcc_modificacionCuentaCupo;
		String        ls_idSolicitud;
		String        ls_nir;
		Solicitud     ls_solicitudCreacion;

		lmcc_modificacionCuentaCupo = new ModCuentaCupo();

		{
			String ls_idCuentaCupo;

			ls_idCuentaCupo = amss_llavesValor.get(ConstantesCriterioCuentaCupo.ID_CUENTA_CUPO);

			lmcc_modificacionCuentaCupo.setIdCuentaCupo(ls_idCuentaCupo);

			{
				CuentaCupo lcc_cuentaCupo;

				lcc_cuentaCupo     = validarCuentaCupo(ls_idCuentaCupo, ace_codigoSalida, adm_manager);

				ls_solicitudCreacion = DaoCreator.getSolicitudDAO(adm_manager).findById(
					    lcc_cuentaCupo.getIdSolicitud()
					);

				if(ls_solicitudCreacion == null)
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_SOLICITUD_CREACION));

				lmcc_modificacionCuentaCupo.setIdSolicitudCreacion(ls_solicitudCreacion.getIdSolicitud());
			}

			validarUsuarioCuentaCupo(
			    as_tipoDocumento, as_numeroDocumento, ls_idCuentaCupo, ace_codigoSalida, adm_manager, true
			);
		}

		{
			Solicitud ls_solicitud;

			ls_solicitud     = crearSolicitudCuentaCupo(
				    ls_solicitudCreacion.getIdPersona(),
				    NumericUtils.getLongWrapper(ls_solicitudCreacion.getIdTelefono()),
				    NumericUtils.getLongWrapper(ls_solicitudCreacion.getIdCorreoElectronico()), adm_manager,
				    ProcesoCommon.ID_SUBPROCESO_2, amss_llavesValor.get(ConstantesCriterioCuentaCupo.MOTIVO_SOLICITUD),
				    as_userId, as_remoteIp
				);

			ls_idSolicitud     = ls_solicitud.getIdSolicitud();
			ls_nir             = ls_solicitud.getNir();

			lmcc_modificacionCuentaCupo.setIdSolicitud(ls_idSolicitud);
		}

		{
			BigDecimal lbd_valorMinimo;
			BigDecimal lbd_valorMaximo;
			int        li_comparacionValores;

			lbd_valorMinimo     = NumericUtils.getBigDecimal(
				    amss_llavesValor.get(ConstantesCriterioCuentaCupo.NUEVO_VALOR_MINIMO)
				);
			lbd_valorMaximo     = NumericUtils.getBigDecimal(
				    amss_llavesValor.get(ConstantesCriterioCuentaCupo.NUEVO_VALOR_MAXIMO)
				);

			if(!NumericUtils.isValidBigDecimal(lbd_valorMinimo, BigDecimal.ONE))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_MINIMO_NO_VALIDO));

			if(!NumericUtils.isValidBigDecimal(lbd_valorMaximo, BigDecimal.ONE))
				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_MAXIMO_NO_VALIDO));

			li_comparacionValores = lbd_valorMinimo.compareTo(lbd_valorMaximo);

			switch(li_comparacionValores)
			{
				case 0:
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_MIN_MAX_IGUALES));

				case 1:
					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_MINIMO_MAYOR_QUE_MAXIMO));

				default:
					break;
			}

			lmcc_modificacionCuentaCupo.setValorMinimo(lbd_valorMinimo);
			lmcc_modificacionCuentaCupo.setValorMaximo(lbd_valorMaximo);
		}

		lmcc_modificacionCuentaCupo.setEstado(EstadoCommon.EN_PROCESO_TXT);
		lmcc_modificacionCuentaCupo.setIdUsuarioCreacion(as_userId);
		lmcc_modificacionCuentaCupo.setIpCreacion(as_remoteIp);

		DaoCreator.getModCuentaCupoDAO(adm_manager).insert(lmcc_modificacionCuentaCupo);

		{
			TurnoHistoria lth_turnoHistoria;

			lth_turnoHistoria = new TurnoHistoria();

			lth_turnoHistoria.setIdSolicitud(ls_idSolicitud);
			lth_turnoHistoria.setIdUsuarioModificacion(as_userId);
			lth_turnoHistoria.setIpModificacion(as_remoteIp);

			DaoCreator.getProcedimientosDAO(adm_manager).spCreateStage(lth_turnoHistoria);
		}

		return ls_nir;
	}

	/**
	 * Obtener id direccion desde JSON.
	 *
	 * @param as_empresaDireccionCorrespondencia de as empresa direccion correspondencia
	 * @param as_idPersona de as id persona
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param adm_manager de adm manager
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized String obtenerIdDireccionDesdeJSON(
	    String as_empresaDireccionCorrespondencia, String as_idPersona, String as_userId, String as_remoteIp,
	    DAOManager adm_manager
	)
	    throws B2BException
	{
		String ls_idDireccion;

		ls_idDireccion = null;

		if(StringUtils.isValidString(as_empresaDireccionCorrespondencia) && StringUtils.isValidString(as_idPersona))
		{
			Object lo_object;

			lo_object = new Gson().fromJson(String.valueOf(as_empresaDireccionCorrespondencia), Object.class);

			if((lo_object != null) && (lo_object instanceof Map<?, ?>))
			{
				Map<String, String> lltm_datos;

				lltm_datos = (LinkedTreeMap<String, String>)lo_object;

				if(CollectionUtils.isValidCollection(lltm_datos))
				{
					PersonaDireccion lpd_personaDireccion;

					lpd_personaDireccion = new PersonaDireccion();

					{
						String ls_tipoPredio;
						String ls_tipoDireccion;
						String ls_pais;
						String ls_departamento;
						String ls_municipio;
						String ls_datoEjePrincipal;
						String ls_letraEjePrincipal;
						String ls_complementoEjePrincipal;
						String ls_coordenadaEjePrincipal;
						String ls_datoEjeSecundario;
						String ls_letraEjeSecundario;
						String ls_complementoEjeSecundario;
						String ls_coordenadaEjeSecundario;
						String ls_dato2EjeSecundario;
						String ls_letra2EjeSecundario;
						String ls_complemento2EjeSecundario;
						String ls_coordenada2EjeSecundario;
						String ls_complementoDireccion;
						String ls_complementoDescripcion;

						ls_tipoPredio     = lltm_datos.containsKey(IdentificadoresCommon.TIPO_PREDIO)
							? lltm_datos.get(IdentificadoresCommon.TIPO_PREDIO) : null;

						ls_tipoDireccion     = lltm_datos.containsKey(IdentificadoresCommon.TIPO_DIRECCION)
							? lltm_datos.get(IdentificadoresCommon.TIPO_DIRECCION) : null;

						ls_pais     = lltm_datos.containsKey(IdentificadoresCommon.PAIS)
							? lltm_datos.get(IdentificadoresCommon.PAIS) : null;

						ls_departamento     = lltm_datos.containsKey(IdentificadoresCommon.DEPARTAMENTO)
							? lltm_datos.get(IdentificadoresCommon.DEPARTAMENTO) : null;

						ls_municipio     = lltm_datos.containsKey(IdentificadoresCommon.MUNICIPIO)
							? lltm_datos.get(IdentificadoresCommon.MUNICIPIO) : null;

						ls_datoEjePrincipal     = lltm_datos.containsKey(IdentificadoresCommon.DATO_EJE_PRINCIPAL)
							? lltm_datos.get(IdentificadoresCommon.DATO_EJE_PRINCIPAL) : null;

						ls_letraEjePrincipal     = lltm_datos.containsKey(IdentificadoresCommon.LETRA_EJE_PRINCIPAL)
							? lltm_datos.get(IdentificadoresCommon.LETRA_EJE_PRINCIPAL) : null;

						ls_complementoEjePrincipal     = lltm_datos.containsKey(
							    IdentificadoresCommon.COMPLEMENTO_EJE_PRINCIPAL
							) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO_EJE_PRINCIPAL) : null;

						ls_coordenadaEjePrincipal     = lltm_datos.containsKey(
							    IdentificadoresCommon.COORDENADA_EJE_PRINCIPAL
							) ? lltm_datos.get(IdentificadoresCommon.COORDENADA_EJE_PRINCIPAL) : null;

						ls_datoEjeSecundario     = lltm_datos.containsKey(IdentificadoresCommon.DATO_EJE_SECUNDARIO)
							? lltm_datos.get(IdentificadoresCommon.DATO_EJE_SECUNDARIO) : null;

						ls_letraEjeSecundario     = lltm_datos.containsKey(IdentificadoresCommon.LETRA_EJE_SECUNDARIO)
							? lltm_datos.get(IdentificadoresCommon.LETRA_EJE_SECUNDARIO) : null;

						ls_complementoEjeSecundario     = lltm_datos.containsKey(
							    IdentificadoresCommon.COMPLEMENTO_EJE_SECUNDARIO
							) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO_EJE_SECUNDARIO) : null;

						ls_coordenadaEjeSecundario     = lltm_datos.containsKey(
							    IdentificadoresCommon.COORDENADA_EJE_SECUNDARIO
							) ? lltm_datos.get(IdentificadoresCommon.COORDENADA_EJE_SECUNDARIO) : null;

						ls_dato2EjeSecundario     = lltm_datos.containsKey(IdentificadoresCommon.DATO2_EJE_SECUNDARIO)
							? lltm_datos.get(IdentificadoresCommon.DATO2_EJE_SECUNDARIO) : null;

						ls_letra2EjeSecundario     = lltm_datos.containsKey(
							    IdentificadoresCommon.LETRA2_EJE_SECUNDARIO
							) ? lltm_datos.get(IdentificadoresCommon.LETRA2_EJE_SECUNDARIO) : null;

						ls_complemento2EjeSecundario     = lltm_datos.containsKey(
							    IdentificadoresCommon.COMPLEMENTO2_EJE_SECUNDARIO
							) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO2_EJE_SECUNDARIO) : null;

						ls_coordenada2EjeSecundario     = lltm_datos.containsKey(
							    IdentificadoresCommon.COORDENADA2_EJE_SECUNDARIO
							) ? lltm_datos.get(IdentificadoresCommon.COORDENADA2_EJE_SECUNDARIO) : null;

						ls_complementoDireccion     = lltm_datos.containsKey(
							    IdentificadoresCommon.COMPLEMENTO_DIRECCION_MINS
							) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO_DIRECCION_MINS) : null;

						ls_complementoDescripcion = lltm_datos.containsKey(
							    IdentificadoresCommon.COMPLEMENTO_DESCRIPCION
							) ? lltm_datos.get(IdentificadoresCommon.COMPLEMENTO_DESCRIPCION) : null;

						lpd_personaDireccion.setIdPersona(as_idPersona);
						lpd_personaDireccion.setDatoEjeSecundario(ls_datoEjeSecundario);
						lpd_personaDireccion.setDato2EjeSecundario(ls_dato2EjeSecundario);

						if(StringUtils.isValidString(ls_datoEjePrincipal))
							lpd_personaDireccion.setDatoEjePrincipal(ls_datoEjePrincipal);
						else
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS));

						{
							validarPaisDepMun(
							    ls_pais, ls_departamento, ls_municipio, ProyectosCommon.DEVOLUCIONES_12_PAIS_DIRECCION,
							    adm_manager
							);

							lpd_personaDireccion.setIdPais(ls_pais);
							lpd_personaDireccion.setIdDepartamento(ls_departamento);
							lpd_personaDireccion.setIdMunicipio(ls_municipio);
						}

						if(
						    StringUtils.isValidString(ls_tipoDireccion)
							    && (ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.R)
							    || ls_tipoDireccion.equalsIgnoreCase(EstadoCommon.C))
						)
							lpd_personaDireccion.setTipoDireccion(ls_tipoDireccion);
						else
							throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS));

						{
							Map<String, String> lmpt_predioTipo;
							Map<String, String> lmss_letraEje;
							Map<String, String> lmss_tipoEje;
							Map<String, String> lmss_coordenada;

							lmpt_predioTipo     = DaoCreator.getPredioTipoDao(adm_manager).findAllActivoMap();
							lmss_letraEje       = DaoCreator.getLetraEjeDAO(adm_manager).findAllActivoMap();
							lmss_tipoEje        = DaoCreator.getTipoEjeDAO(adm_manager).findAllActivoMap();
							lmss_coordenada     = DaoCreator.getCoordenadaDAO(adm_manager).findAllActivoMap();

							if(
							    CollectionUtils.isValidCollection(lmpt_predioTipo)
								    && lmpt_predioTipo.containsKey(ls_tipoPredio)
							)
								lpd_personaDireccion.setIdTipoPredio(ls_tipoPredio);
							else
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS));

							if(CollectionUtils.isValidCollection(lmss_letraEje))
							{
								if(StringUtils.isValidString(ls_letraEjePrincipal))
								{
									if(lmss_letraEje.containsKey(ls_letraEjePrincipal))
										lpd_personaDireccion.setIdLetraEjePrincipal(ls_letraEjePrincipal);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_letraEjeSecundario))
								{
									if(lmss_letraEje.containsKey(ls_letraEjeSecundario))
										lpd_personaDireccion.setIdLetra1EjeSecundario(ls_letraEjeSecundario);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_letra2EjeSecundario))
								{
									if(lmss_letraEje.containsKey(ls_letra2EjeSecundario))
										lpd_personaDireccion.setIdLetra2EjeSecundario(ls_letra2EjeSecundario);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}
							}
							else
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERNO_SISTEMA));

							if(CollectionUtils.isValidCollection(lmss_tipoEje))
							{
								if(StringUtils.isValidString(ls_complementoEjePrincipal))
								{
									if(lmss_tipoEje.containsKey(ls_complementoEjePrincipal))
										lpd_personaDireccion.setIdComplementoEjePrincipal(ls_complementoEjePrincipal);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_complementoEjeSecundario))
								{
									if(lmss_tipoEje.containsKey(ls_complementoEjeSecundario))
										lpd_personaDireccion.setIdComplemento1EjeSecundario(
										    ls_complementoEjeSecundario
										);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_complemento2EjeSecundario))
								{
									if(lmss_tipoEje.containsKey(ls_complemento2EjeSecundario))
										lpd_personaDireccion.setIdComplemento2EjeSecundario(
										    ls_complemento2EjeSecundario
										);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}
							}
							else
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERNO_SISTEMA));

							if(CollectionUtils.isValidCollection(lmss_coordenada))
							{
								if(StringUtils.isValidString(ls_coordenadaEjePrincipal))
								{
									if(lmss_coordenada.containsKey(ls_coordenadaEjePrincipal))
										lpd_personaDireccion.setIdCoordenadaEjePrincipal(ls_coordenadaEjePrincipal);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_coordenadaEjeSecundario))
								{
									if(lmss_coordenada.containsKey(ls_coordenadaEjeSecundario))
										lpd_personaDireccion.setIdCoordenada1EjeSecundario(ls_coordenadaEjeSecundario);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}

								if(StringUtils.isValidString(ls_coordenada2EjeSecundario))
								{
									if(lmss_coordenada.containsKey(ls_coordenada2EjeSecundario))
										lpd_personaDireccion.setIdCoordenada2EjeSecundario(ls_coordenada2EjeSecundario);
									else
										throw new B2BException(
										    addMessageGCC(ErrorKeys.ERROR_DATOS_DIRECCION_INVALIDOS)
										);
								}
							}
							else
								throw new B2BException(addMessageGCC(ErrorKeys.ERROR_INTERNO_SISTEMA));
						}

						lpd_personaDireccion.setIdComplementoDireccion(ls_complementoDireccion);
						lpd_personaDireccion.setComplementoDireccion(ls_complementoDescripcion);

						{
							PersonaDireccionDAO lpd_DAO;
							PersonaDireccion    lpd_personaDireccionConsultada;

							lpd_DAO                            = DaoCreator.getPersonaDireccionDAO(adm_manager);
							lpd_personaDireccionConsultada     = lpd_DAO.findFirstByDatosDireccionJSON(
								    lpd_personaDireccion
								);

							if(lpd_personaDireccionConsultada != null)
								ls_idDireccion = lpd_personaDireccionConsultada.getIdDireccion();
							else
							{
								lpd_personaDireccion.setFechaDesde(new Date());
								lpd_personaDireccion.setActivo(EstadoCommon.S);
								lpd_personaDireccion.setIdUsuarioCreacion(as_userId);
								lpd_personaDireccion.setIpCreacion(as_remoteIp);

								ls_idDireccion = StringUtils.getString(
									    lpd_DAO.insertOrUpdate(lpd_personaDireccion, true)
									);
							}
						}
					}
				}
			}
		}

		return ls_idDireccion;
	}

	/**
	 * Obtener token.
	 *
	 * @return el valor de token
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized int obtenerToken()
	    throws B2BException
	{
		int li_random;

		li_random = -1;

		try
		{
			Date         ld_fechaBase;
			SecureRandom lsr_random;

			//TODO validar periodo de tiempo a filtrar token
			ld_fechaBase = new Date();

			/*
			 * Obtener los valors ya generados segun la fecha y el perido
			 *
			 * */
			int[] lia_generados;

			lia_generados     = null;
			lsr_random        = SecureRandom.getInstance("SHA1PRNG");

			lsr_random.setSeed(ld_fechaBase.getTime());

			while(contiene(lia_generados, li_random))
				li_random = Math.abs(lsr_random.nextInt(999999));
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerToken", le_e);
			throw new B2BException(le_e.getMessage());
		}

		return li_random;
	}

	/**
	 * Verifica los criterios ingresados en la operación.
	 *
	 * @param atcgsia_criterios            Objeto contenedor de los criterios a verificar
	 * @param amss_valoresIngresados de amss valores ingresados
	 * @param acs_codigosObligatorios de acs codigos obligatorios
	 * @return Mapa con las llaves y valores ingresados
	 * @throws B2BException             Si no se cumple una validación de negocio
	 */
	private Map<String, String> procesarCriteriosCuentaCupo(
	    TipoCriterioGSI[] atcgsia_criterios, Map<String, String> amss_valoresIngresados,
	    Collection<String> acs_codigosObligatorios
	)
	    throws B2BException
	{
		if(
		    !(CollectionUtils.isValidCollection(atcgsia_criterios)
			    || !CollectionUtils.isValidCollection(amss_valoresIngresados)
			    || !CollectionUtils.isValidCollection(acs_codigosObligatorios))
		)
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_PARAMETROS_ENTRADA_NO_VALIDOS));

		Map<String, String> lmss_mapaCriteriosFaltantes;

		lmss_mapaCriteriosFaltantes = new LinkedHashMap<String, String>();

		// FORMATO Comentar lambda antes de formatear
		acs_codigosObligatorios.forEach(ls_codigo -> lmss_mapaCriteriosFaltantes.put(ls_codigo, null));
		for(
		    int li_posicion = 0, li_tamanioCriterios = atcgsia_criterios.length; li_posicion < li_tamanioCriterios;
			    li_posicion++
		)
		{
			TipoCriterioGSI ltcgsi_criterio;

			ltcgsi_criterio = atcgsia_criterios[li_posicion];

			if(ltcgsi_criterio != null)
			{
				String ls_codigo;

				ls_codigo = StringUtils.getStringNotNull(ltcgsi_criterio.getCodigo());

				if(amss_valoresIngresados.containsKey(ls_codigo))
				{
					String           ls_valor;
					Optional<String> los_returnedValue;

					ls_valor = ltcgsi_criterio.getValor();

					// FORMATO Comentar lambda antes de formatear
					los_returnedValue = acs_codigosObligatorios.stream()
							.filter(ls_valueTmp -> ls_valueTmp.equals(ls_codigo)).findFirst();
					if(los_returnedValue.isPresent() && !StringUtils.isValidString(ls_valor))
					{
						Object[] loa_args;

						loa_args     = new String[1];

						loa_args[0] = ls_codigo;

						throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIO_NO_VALIDO, loa_args));
					}

					amss_valoresIngresados.put(ls_codigo, ls_valor);

					lmss_mapaCriteriosFaltantes.remove(ls_codigo);
				}
				else
				{
					Object[] loa_args;

					loa_args     = new String[1];

					loa_args[0] = ls_codigo;

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIO_NO_PARAMETRIZADO, loa_args));
				}
			}
		}

		if(CollectionUtils.isValidCollection(lmss_mapaCriteriosFaltantes))
		{
			StringBuilder lsb_criteriosFaltantes;
			Object[]      loa_args;

			lsb_criteriosFaltantes     = new StringBuilder();
			loa_args                   = new String[1];

			// FORMATO Comentar lambda antes de formatear
			lmss_mapaCriteriosFaltantes.forEach((ls_llave, ls_valor) -> 
			{
				lsb_criteriosFaltantes.append(ls_llave);
				lsb_criteriosFaltantes.append(IdentificadoresCommon.SIMBOLO_COMA);
			});
			lsb_criteriosFaltantes.deleteCharAt(lsb_criteriosFaltantes.length() - 2);
			loa_args[0] = lsb_criteriosFaltantes.toString();

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CRITERIOS_FALTANTES, loa_args));
		}

		return amss_valoresIngresados;
	}

	/**
	 * Propagar error campo obligatorio.
	 *
	 * @param ace_codigoSalida de ace codigo salida
	 * @param as_campo de as campo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void propagarErrorCampoObligatorio(CodigoError ace_codigoSalida, String as_campo)
	    throws B2BException
	{
		if((ace_codigoSalida != null) && StringUtils.isValidString(as_campo))
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = as_campo;

			ace_codigoSalida.setCodigoError(BigInteger.valueOf(523L));
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_CAMPO_OBLIGATORIO, loa_args));
		}
	}

	/**
	 * Verifica que el NIR sea de cuenta cupos.
	 *
	 * @param as_nir            Objeto contenedor del NIR a validar
	 * @param ace_codigoSalida            Objeto contenedor del código de salida
	 * @param adm_manager            Conexión a la base de datos
	 * @return el valor de solicitud
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private Solicitud validarNirCuentaCupo(String as_nir, CodigoError ace_codigoSalida, DAOManager adm_manager)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_nir))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_NIR));

		Solicitud ls_solicitud;

		ls_solicitud = DaoCreator.getSolicitudDAO(adm_manager).findByNir(as_nir);

		if(ls_solicitud != null)
		{
			String ls_idProceso;

			ls_idProceso = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());

			if(!ls_idProceso.equals(ProcesoCommon.ID_PROCESO_60))
			{
				ace_codigoSalida.setCodigoError(BigInteger.valueOf(412L));

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_CUENTA_CUPOS));
			}
		}
		else
		{
			ace_codigoSalida.setCodigoError(BigInteger.valueOf(411L));

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_NIR_NO_EXISTENTE));
		}

		return ls_solicitud;
	}

	/**
	 * Verifica que la razon social sea válida.
	 *
	 * @param as_razonSocial            Objeto contenedor de la razón social
	 * @return Razón social verificada
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private String validarRazonSocial(String as_razonSocial)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_razonSocial) && (as_razonSocial.length() > 500))
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = ConstantesCriterioCuentaCupo.RAZON_SOCIAL;

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_VALOR_CRITERIO_DEMASIADO_GRANDE, loa_args));
		}

		return as_razonSocial;
	}

	/**
	 * Verifica que el número de teléfono ingresado sea válido.
	 *
	 * @param as_telefono            Objeto contenedor del número de teléfono a validar
	 * @param as_tipoDocumento            tipo de documento de la persona asociada al teléfono
	 * @param as_numeroDocumento            número de documento de la persona asociada al teléfono
	 * @param adm_manager            Conexión a la base de datos
	 * @throws B2BException             Si no se cumple una regla de negocio
	 */
	private void validarTelefono(
	    String as_telefono, String as_tipoDocumento, String as_numeroDocumento, DAOManager adm_manager
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_telefono))
		{
			Object[] loa_args;

			loa_args     = new String[2];

			loa_args[0]     = as_tipoDocumento;
			loa_args[1]     = as_numeroDocumento;

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TELEFONO, loa_args));
		}

		ConstantesDAO lcd_constantesDAO;
		Constantes    lc_digitosTelMovil;

		lcd_constantesDAO      = DaoCreator.getConstantesDAO(adm_manager);
		lc_digitosTelMovil     = lcd_constantesDAO.findById(ConstanteCommon.DIGITOS_TEL_MOVIL_COL);

		if((lc_digitosTelMovil != null))
		{
			BigInteger lbi_digitosMovil;

			lbi_digitosMovil = lc_digitosTelMovil.getEntero();

			if(NumericUtils.isValidBigInteger(lbi_digitosMovil))
			{
				int li_digitosTelefono;
				int li_digitosMovil;

				li_digitosTelefono     = as_telefono.length();
				li_digitosMovil        = lbi_digitosMovil.intValue();

				if(!((li_digitosTelefono == li_digitosMovil)))
				{
					Object[] loa_args;

					loa_args     = new String[3];

					loa_args[0]     = as_tipoDocumento;
					loa_args[1]     = as_numeroDocumento;
					loa_args[2]     = StringUtils.getString(li_digitosMovil);

					throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TELEFONO_NO_VALIDO, loa_args));
				}
			}
			else
			{
				Object[] loa_args;

				loa_args     = new String[1];

				loa_args[0] = ConstanteCommon.DIGITOS_TEL_MOVIL_COL;

				throw new B2BException(addMessageGCC(ErrorKeys.ERROR_ENTERO_CONSTANTE_NO_VALIDO, loa_args));
			}
		}
		else
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = ConstanteCommon.DIGITOS_TEL_MOVIL_COL;

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_CONSTANTE, loa_args));
		}
	}

	/**
	 * Verifica que el tipo documento ingresado sea válido y exista.
	 *
	 * @param as_tipoDocUsuario
	 *            id del tipo documento a validar
	 * @param ace_codigoSalida
	 *            Contenedor del código de salida
	 * @param adm_manager
	 *            Conexión a la base de datos
	 * @throws B2BException
	 *             Si no se cumple una regla de negocio.
	 */
	private void validarTipoDocumento(String as_tipoDocUsuario, CodigoError ace_codigoSalida, DAOManager adm_manager)
	    throws B2BException
	{
		validarTipoDocumento(as_tipoDocUsuario, ace_codigoSalida, adm_manager, true);
	}

	/**
	 * Verifica que el tipo documento ingresado sea válido y exista.
	 *
	 * @param as_tipoDocUsuario
	 *            id del tipo documento a validar
	 * @param ace_codigoSalida
	 *            Contenedor del código de salida
	 * @param adm_manager
	 *            Conexión a la base de datos
	 * @param ab_validarNit
	 *            true para validar que el tipo de documento sea NIT
	 * @throws B2BException
	 *             Si no se cumple una regla de negocio.
	 */
	private void validarTipoDocumento(
	    String as_tipoDocUsuario, CodigoError ace_codigoSalida, DAOManager adm_manager, boolean ab_validarNit
	)
	    throws B2BException
	{
		validarTipoDocumento(as_tipoDocUsuario, null, ace_codigoSalida, adm_manager, ab_validarNit);
	}

	/**
	 * Verifica que el tipo documento ingresado sea válido y exista.
	 *
	 * @param as_tipoDocUsuario            id del tipo documento a validar
	 * @param as_tipoError correspondiente al valor de as tipo error
	 * @param ace_codigoSalida            Contenedor del código de salida
	 * @param adm_manager            Conexión a la base de datos
	 * @param ab_validarNit            true para validar que el tipo de documento sea NIT
	 * @throws B2BException             Si no se cumple una regla de negocio.
	 */
	private void validarTipoDocumento(
	    String as_tipoDocUsuario, String as_tipoError, CodigoError ace_codigoSalida, DAOManager adm_manager,
	    boolean ab_validarNit
	)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_tipoDocUsuario))
		{
			String ls_error;

			ls_error = ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO;

			if(StringUtils.isValidString(as_tipoError))
			{
				switch(as_tipoError)
				{
					case EstadoCommon.P:
						ls_error = ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO_PODERDANTE;

						break;

					case EstadoCommon.I:
						ls_error = ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO_INTERVINIENTE;

						break;

					case EstadoCommon.T:
						ls_error = ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO_TITULAR_CUENTA;

						break;

					default:
						ls_error = ErrorKeys.ERROR_SIN_TIPO_DOCUMENTO;

						break;
				}
			}

			throw new B2BException(addMessageGCC(ls_error));
		}

		InteresadoDocumentoTipo lidt_tipoDoc;

		lidt_tipoDoc = DaoCreator.getInteresadoDocumentoTipoDAO(adm_manager).findById(as_tipoDocUsuario);

		if(lidt_tipoDoc == null)
		{
			Object[] loa_args;

			loa_args     = new String[1];

			loa_args[0] = as_tipoDocUsuario;

			ace_codigoSalida.setCodigoError(BigInteger.valueOf(455L));

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_VALIDO, loa_args));
		}

		if(ab_validarNit && !as_tipoDocUsuario.equalsIgnoreCase(IdentificadoresCommon.NIT))
		{
			ace_codigoSalida.setCodigoError(BigInteger.valueOf(410L));

			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_DOCUMENTO_NO_NIT));
		}
	}

	/**
	 * Verifica el tipo de persona ingresado.
	 *
	 * @param as_tipoPersona            Id del tipo persona a verificar
	 * @param adm_manager            Conexión a la base de datos
	 * @param ab_validarNit            true para validar que el tipo de persona sea jurídica
	 * @throws B2BException             Si ocurre un error en base de datos.
	 */
	private void validarTipoPersona(String as_tipoPersona, DAOManager adm_manager, boolean ab_validarNit)
	    throws B2BException
	{
		if(!StringUtils.isValidString(as_tipoPersona))
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_SIN_TIPO_PERSONA));

		TipoPersona ltp_tipoPersona;

		ltp_tipoPersona = DaoCreator.getTipoPersonaDAO(adm_manager).findById(as_tipoPersona);

		if(ltp_tipoPersona == null)
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_PERSONA_NO_VALIDO));

		if(!as_tipoPersona.equalsIgnoreCase(EstadoCommon.J) && ab_validarNit)
			throw new B2BException(addMessageGCC(ErrorKeys.ERROR_TIPO_PERSONA_NO_JURIDICO));
	}
}


