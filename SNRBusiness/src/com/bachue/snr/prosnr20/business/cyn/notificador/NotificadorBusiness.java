package com.bachue.snr.prosnr20.business.cyn.notificador;

import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MedioNotificarCommon;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.DocumentosSalidaDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;

import com.bachue.snr.prosnr01.model.notificaciones.PersonaNotificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import com.bachue.snr.prosnr20.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades NotificadorBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/04/2020
 */
public class NotificadorBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificadorBusiness.class, ProyectosCommon.NOTIFICADOR_20);

	/**
	 * Acusar mensaje.
	 *
	 * @param ateam_entrada de ateam entrada
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de tipo salida acusar mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaAcusarMensaje acusarMensaje(
	    TipoEntradaAcusarMensaje ateam_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		String     ls_descripcionMensaje;
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		ldm_manager               = DaoManagerFactory.getDAOManager();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(ateam_entrada != null)
			{
				String           ls_identificadorMensaje;
				String           ls_numeroGuia;
				Date             ld_fechaenvio;
				Date             ld_fechaAcuseRecibo;
				TurnoHistoria    lth_envioComponenteCYN;
				TurnoHistoriaDAO lthd_DAO;

				ls_identificadorMensaje     = StringUtils.getString(ateam_entrada.getIdentificador());
				ls_numeroGuia               = StringUtils.getString(ateam_entrada.getGuia());
				ld_fechaenvio               = obtenerDateDesdeCalendar(ateam_entrada.getFechaEnvio());
				ld_fechaAcuseRecibo         = obtenerDateDesdeCalendar(ateam_entrada.getFechaAcuse());
				lthd_DAO                    = DaoCreator.getTurnoHistoriaDAO(ldm_manager);

				if(!StringUtils.isValidString(ls_identificadorMensaje))
					throw new B2BException(addErrorN(ErrorKeys.ERROR_IDENTIFICADOR_NO_VALIDO));

				lth_envioComponenteCYN = lthd_DAO.findByIdMensaje(ls_identificadorMensaje);

				if(lth_envioComponenteCYN != null)
				{
					TurnoHistoria lth_reciboAcuse;
					Solicitud     ls_solicitud;
					long          ll_idTurnoHistoriaEnvioComp;

					lth_reciboAcuse                 = null;
					ls_solicitud                    = null;
					ll_idTurnoHistoriaEnvioComp     = NumericUtils.getLong(lth_envioComponenteCYN.getIdTurnoHistoria());

					if(ll_idTurnoHistoriaEnvioComp > NumericUtils.DEFAULT_LONG_VALUE)
						lth_reciboAcuse = lthd_DAO.findByIdAnterior(
							    new TurnoHistoria(NumericUtils.getBigDecimal(ll_idTurnoHistoriaEnvioComp), true), true
							);

					if(lth_reciboAcuse == null)
					{
						Object[] loa_messageArgs;

						loa_messageArgs        = new String[1];
						loa_messageArgs[0]     = StringUtils.getString(lth_envioComponenteCYN.getIdEtapa());

						throw new B2BException(addErrorN(ErrorKeys.ERROR_ETAPA_POSTERIOR_NO_EXISTE, loa_messageArgs));
					}

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(lth_reciboAcuse.getIdSolicitud());

					if(ls_solicitud != null)
					{
						boolean               lb_proceso47o48;
						long                  ll_idMotivo;
						long                  ll_idEtapa;
						Collection<Solicitud> lcs_solicitudesAsociadas;
						Solicitud             ls_solicitudVinculada;
						String                ls_idProceso;
						String                ls_medioNot;
						String                ls_idTurno;

						lb_proceso47o48              = false;
						ls_solicitudVinculada        = null;
						ls_idProceso                 = StringUtils.getStringNotNull(ls_solicitud.getIdProceso());
						ls_medioNot                  = StringUtils.getString(
							    ls_solicitud.getIdAutorizacionNotificacion()
							);
						ls_idTurno                   = lth_reciboAcuse.getIdTurno();
						ll_idEtapa                   = NumericUtils.getLong(lth_reciboAcuse.getIdEtapa());
						ll_idMotivo                  = NumericUtils.DEFAULT_LONG_VALUE;
						lcs_solicitudesAsociadas     = obtenerSolicitudesVinculadas(
							    ls_solicitud.getIdSolicitud(), false, ldm_manager
							);

						actualizarAcuseRecibido(
						    ls_identificadorMensaje, ll_idEtapa, ls_numeroGuia, ld_fechaenvio, ld_fechaAcuseRecibo,
						    ls_idTurno, as_userId, as_remoteIpAddress, ldm_manager
						);

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

									ls_procesoVinculado = ls_actual.getIdProceso();

									if(StringUtils.isValidString(ls_procesoVinculado))
										lb_proceso47o48 = (ls_procesoVinculado.equalsIgnoreCase(
											    ProcesoCommon.ID_PROCESO_47
											) || ls_procesoVinculado.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_48));

									ls_solicitudVinculada = lb_proceso47o48 ? ls_actual : null;
								}
							}
						}

						if(!StringUtils.isValidString(ls_medioNot))
							ls_medioNot = MedioNotificarCommon.DIRECCION_CORRESPONDENCIA;

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CORRESPONDENCIA)
						{
							if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
							{
								if(lb_proceso47o48 && (ls_solicitudVinculada != null))
									ls_medioNot = StringUtils.getString(
										    ls_solicitudVinculada.getIdAutorizacionNotificacion()
										);

								if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_CORRESPONDENCIA_ORIP;
								else if(
								    ls_medioNot.equals(MedioNotificarCommon.DIRECCION_CORRESPONDENCIA)
									    || ls_medioNot.equals(MedioNotificarCommon.DIRECCION_RESIDENCIA)
								)
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_CORRESPONDENCIA_DIRECCION;
								else
									throw new B2BException(addErrorN(ErrorKeys.ERROR_MEDIO_NOTIFICAR_CORRESPONDENCIA));
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
							{
								String           ls_idDocumentosSalida;
								DocumentosSalida lds_documentos;

								ls_idDocumentosSalida = lth_envioComponenteCYN.getIdDocumentoSalida();

								if(StringUtils.isValidString(ls_idDocumentosSalida))
								{
									long ll_idDocumentoSalida;

									ll_idDocumentoSalida     = NumericUtils.getLong(ls_idDocumentosSalida);
									lds_documentos           = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                                 .findById(ll_idDocumentoSalida);

									if(lds_documentos != null)
									{
										PersonaNotificacion lpn_personaNotificacion;

										lpn_personaNotificacion = DaoCreator.getPersonaNotificacionDAO(ldm_manager)
												                                .findById(
												    lds_documentos.getIdPersonaNotificacion()
												);

										if(lpn_personaNotificacion != null)
										{
											String ls_idPersona;

											ls_idPersona = lpn_personaNotificacion.getIdPersona();

											if(StringUtils.isValidString(ls_idPersona))
											{
												String ls_medioNotificar;

												ls_medioNotificar = lpn_personaNotificacion
														.getIdAutorizacionNotificacion();

												if(!StringUtils.isValidString(ls_medioNotificar))
												{
													Object[] loa_args;

													loa_args     = new String[1];

													loa_args[0] = lpn_personaNotificacion.getIdPersonaNotificacion();

													throw new B2BException(
													    addErrorN(
													        ErrorKeys.ERROR_AUTORIZA_NOTIFICACION_PERSONA, loa_args
													    )
													);
												}

												if(ls_medioNotificar.equals(MedioNotificarCommon.ORIP))
													ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_CORRESPONDENCIA_ORIP;
												else if(
												    ls_medioNotificar.equals(
													        MedioNotificarCommon.DIRECCION_CORRESPONDENCIA
													    )
													    || ls_medioNotificar.equals(
													        MedioNotificarCommon.DIRECCION_RESIDENCIA
													    )
												)
													ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_CORRESPONDENCIA_DIRECCION;
												else
													throw new B2BException(
													    addErrorN(ErrorKeys.ERROR_MEDIO_NOTIFICAR_CORRESPONDENCIA)
													);
											}
											else if(
											    !BooleanUtils.getBooleanValue(
												        lpn_personaNotificacion.getTerceroIndeterminado()
												    )
											)
												ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_ACTUACIONES;
										}
										else
											throw new B2BException(
											    addErrorN(ErrorKeys.ERROR_PERSONA_NOTIFICAR_DOCUMENTO_ACUSADO)
											);
									}
									else
										throw new B2BException(addErrorN(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE));
								}
								else
									throw new B2BException(addErrorN(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE));
							}
						}
						else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CORREO_ELECTRONICO)
						{
							if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_6))
							{
								if(lb_proceso47o48 && (ls_solicitudVinculada != null))
									ls_medioNot = StringUtils.getString(
										    ls_solicitudVinculada.getIdAutorizacionNotificacion()
										);

								if(ls_medioNot.equals(MedioNotificarCommon.ORIP))
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_EMAIL_ORIP;
								else if(ls_medioNot.equals(MedioNotificarCommon.CORREO_ELECTRONICO))
									ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_EMAIL_CORREO_ELECTRONICO;
								else
									throw new B2BException(addErrorN(ErrorKeys.ERROR_MEDIO_NOTIFICAR_EMAIL));
							}
							else if(ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3))
							{
								String           ls_idDocumentosSalida;
								DocumentosSalida lds_documentos;

								ls_idDocumentosSalida = lth_envioComponenteCYN.getIdDocumentoSalida();

								if(StringUtils.isValidString(ls_idDocumentosSalida))
								{
									long ll_idDocumentoSalida;

									ll_idDocumentoSalida     = NumericUtils.getLong(ls_idDocumentosSalida);
									lds_documentos           = DaoCreator.getDocumentosSalidaDAO(ldm_manager)
											                                 .findById(ll_idDocumentoSalida);

									if(lds_documentos != null)
									{
										PersonaNotificacion lpn_personaNotificacion;

										lpn_personaNotificacion = DaoCreator.getPersonaNotificacionDAO(ldm_manager)
												                                .findById(
												    lds_documentos.getIdPersonaNotificacion()
												);

										if(lpn_personaNotificacion != null)
										{
											String ls_idPersona;

											ls_idPersona = lpn_personaNotificacion.getIdPersona();

											if(StringUtils.isValidString(ls_idPersona))
											{
												String ls_medioNotificar;

												ls_medioNotificar = lpn_personaNotificacion
														.getIdAutorizacionNotificacion();

												if(!StringUtils.isValidString(ls_medioNotificar))
												{
													Object[] loa_args;

													loa_args     = new String[1];

													loa_args[0] = lpn_personaNotificacion.getIdPersonaNotificacion();

													throw new B2BException(
													    addErrorN(
													        ErrorKeys.ERROR_AUTORIZA_NOTIFICACION_PERSONA, loa_args
													    )
													);
												}

												if(ls_medioNotificar.equals(MedioNotificarCommon.ORIP))
													ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_EMAIL_ORIP;
												else if(
												    ls_medioNotificar.equals(MedioNotificarCommon.CORREO_ELECTRONICO)
												)
													ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_EMAIL_CORREO_ELECTRONICO;
												else
													throw new B2BException(
													    addErrorN(ErrorKeys.ERROR_MEDIO_NOTIFICAR_EMAIL)
													);
											}
											else if(
											    !BooleanUtils.getBooleanValue(
												        lpn_personaNotificacion.getTerceroIndeterminado()
												    )
											)
												throw new B2BException(
												    addErrorN(ErrorKeys.ERROR_PERSONA_NOTIFICAR_DOCUMENTO_ACUSADO)
												);
										}
									}
									else
										throw new B2BException(addErrorN(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE));
								}
								else
									throw new B2BException(addErrorN(ErrorKeys.ERROR_DOCUMENTO_SALIDA_NO_EXISTE));
							}
						}
						else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CITATORIO)
							ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_CITATORIO_NOTIFICADO;
						else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_AVISO)
							ll_idMotivo = MotivoTramiteCommon.EN_ESPERA_ACUSE_AVISO_NOTIFICADO;

						if(ll_idMotivo > NumericUtils.DEFAULT_LONG_VALUE)
							terminarTurnoHistoriaYCrearEtapa(
							    lth_reciboAcuse, ldm_manager, new MotivoTramite(ll_idEtapa, ll_idMotivo), as_userId,
							    as_remoteIpAddress, EstadoCommon.TERMINADA
							);
					}
				}
				else
					throw new B2BException(addErrorN(ErrorKeys.ERROR_IDENTIFICADOR_NO_EXISTE));
			}
			else
				throw new B2BException(addErrorN(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("acusarMensaje", lb2be_e);

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
			clh_LOGGER.error("acusarMensaje", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		return new TipoSalidaAcusarMensaje(lbi_codigoMensaje, ls_descripcionMensaje);
	}

	/**
	 * Actualizar acuse recibido.
	 *
	 * @param as_isMensaje de as is mensaje
	 * @param ll_idEtapa de ll id etapa
	 * @param as_numeroGuia de as numero guia
	 * @param ad_fechaEnvio de ad fecha envio
	 * @param ad_fechaAcuseRecibo de ad fecha acuse recibo
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_remoteIpAddress de as remote ip address
	 * @param adm_manager de adm manager
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void actualizarAcuseRecibido(
	    String as_isMensaje, long ll_idEtapa, String as_numeroGuia, Date ad_fechaEnvio, Date ad_fechaAcuseRecibo,
	    String as_idTurno, String as_userId, String as_remoteIpAddress, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_isMensaje))
			{
				DocumentosSalidaDAO          ldsd_DAO;
				TurnoDAO                     ltd_DAO;
				Collection<DocumentosSalida> lcds_documentos;

				ldsd_DAO            = DaoCreator.getDocumentosSalidaDAO(adm_manager);
				ltd_DAO             = DaoCreator.getTurnoDAO(adm_manager);
				lcds_documentos     = ldsd_DAO.findByIdMensaje(as_isMensaje);

				if(CollectionUtils.isValidCollection(lcds_documentos))
				{
					if(ad_fechaAcuseRecibo == null)
						ad_fechaAcuseRecibo = new Date();

					for(DocumentosSalida lds_iterator : lcds_documentos)

						if(lds_iterator != null)
						{
							if(
							    (ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CORRESPONDENCIA)
								    || (ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CORREO_ELECTRONICO)
							)
							{
								lds_iterator.setFechaGuia(ad_fechaEnvio);
								lds_iterator.setNumeroGuia(as_numeroGuia);
								lds_iterator.setFechaEnvioComponente(ad_fechaEnvio);
								lds_iterator.setFechaAcuseReciboComponente(ad_fechaAcuseRecibo);
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_CITATORIO)
							{
								lds_iterator.setFechaNotificacion(ad_fechaAcuseRecibo);
								lds_iterator.setIdTipoNotificacion("CORREO ELECTRONICO");
							}
							else if(ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_AVISO)
							{
								lds_iterator.setFechaEnvioComponente(ad_fechaEnvio);
								lds_iterator.setFechaAcuseReciboComponente(ad_fechaAcuseRecibo);
								lds_iterator.setIdTipoNotificacion("AVISO");
							}

							lds_iterator.setIdUsuarioModificacion(as_userId);
							lds_iterator.setIpModificacion(as_remoteIpAddress);

							ldsd_DAO.updateAcuseRecibido(lds_iterator);
						}

					if(
					    StringUtils.isValidString(as_idTurno)
						    && (ll_idEtapa == EtapaCommon.ID_ETAPA_ESPERA_POR_ACUSE_AVISO)
					)
					{
						Turno lt_turno;

						lt_turno = ltd_DAO.findById(as_idTurno);

						if(lt_turno != null)
						{
							lt_turno.setFechaAcuseReciboAviso(ad_fechaEnvio);
							lt_turno.setIdTipoNotificacion("AVISO");
							lt_turno.setIdUsuarioModificacion(as_userId);
							lt_turno.setIpModificacion(as_remoteIpAddress);

							ltd_DAO.actualizarAcuseRecibido(lt_turno);
						}
					}
				}
			}
			else
				throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_NO_EXISTE));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarAcuseRecibido", lb2be_e);
			throw lb2be_e;
		}
	}
}
