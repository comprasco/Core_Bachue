package com.bachue.snr.prosnr11.business.controlDigitalizacion;

import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion;
import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoHistoriaDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr11.common.constants.ErrorKeys;

import java.math.BigInteger;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase de lógica de negocio para el servicio Control Digitalización.
 * @author hcastaneda
 *
 */
public class ControlDigitalizacionBusiness extends BaseBusiness
{
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ControlDigitalizacionBusiness.class, ProyectosCommon.CONTROL_DIGITALIZACION_11);

	/**
	 * Método encargado de notificar la digitalización de los documentos de una solicitud.
	 *
	 * @param atend_request Argumento de tipo <code>TipoEntradaNotificarDigitalizacion</code>
	 * que contiene los criterios necesarios para realizar la notificación de digitalización.
	 * @param as_userIdConstant Argumento de tipo <code>String</code> que contiene el usuario que realiza la petición.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la petición.
	 * @return Objeto de tipo <code>TipoSalidaNotificarDigitalizacion</code> que contiene el código y la descripción de la respuesta de la operación.
	 * @throws B2BException
	 */
	public synchronized TipoSalidaNotificarDigitalizacion notificarDigitalizacion(
	    TipoEntradaNotificarDigitalizacion atend_request, String as_userIdConstant, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaNotificarDigitalizacion ltsnd_tipoSalidaNotificarDigitalizacion;
		String                            ls_descripcionMensaje;
		BigInteger                        lbi_codigoMensaje;
		DAOManager                        ldm_manager;

		ltsnd_tipoSalidaNotificarDigitalizacion     = new TipoSalidaNotificarDigitalizacion();
		ls_descripcionMensaje                       = addMessage(MessagesKeys.OK);
		lbi_codigoMensaje                           = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);
		ldm_manager                                 = DaoManagerFactory.getDAOManager();

		try
		{
			if(atend_request != null)
			{
				TurnoHistoriaDAO lthd_DAO;
				String           ls_identificador;
				String           ls_numero;
				String           ls_estado;

				lthd_DAO             = DaoCreator.getTurnoHistoriaDAO(ldm_manager);
				ls_identificador     = null;
				ls_numero            = null;
				ls_estado            = null;

				{
					String ls_sistemaOrigen;

					ls_sistemaOrigen = atend_request.getSistemaOrigen();

					if(
					    !StringUtils.isValidString(ls_sistemaOrigen)
						    || (!ls_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORE)
						    && !ls_sistemaOrigen.equalsIgnoreCase(SistemaOrigenCommon.CORRESPONDENCIA))
					)
						throw new B2BException(ErrorKeys.ERROR_SISTEMA_ORIGEN_NO_PERMITIDO);
				}

				{
					String ls_identificadorTramite;

					ls_identificadorTramite = atend_request.getIdentificadorTramite();

					if(!StringUtils.isValidString(ls_identificadorTramite))
						throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_TRAMITE_NO_VALIDO);

					{
						String   ls_separador;
						String[] lsa_array;

						ls_separador     = ":";
						lsa_array        = ls_identificadorTramite.split(ls_separador);

						if(!CollectionUtils.isValidCollection(lsa_array))
						{
							Object[] loa_object;

							loa_object     = new String[1];

							loa_object[0] = ls_separador;

							throw new B2BException(
							    addErrorCD(ErrorKeys.ERROR_SEPARADOR_IDENTIFICADOR_TRAMITE_NO_VALIDO, loa_object)
							);
						}

						if(lsa_array.length < 2)
							throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_TRAMITE_NO_VALIDO);

						ls_identificador     = lsa_array[0];
						ls_numero            = lsa_array[1];

						if(!StringUtils.isValidString(ls_identificador) || !StringUtils.isValidString(ls_numero))
							throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_TRAMITE_NO_VALIDO);
					}
				}

				ls_estado = atend_request.getEstado();

				if(
				    !StringUtils.isValidString(ls_estado)
					    || (!ls_estado.equalsIgnoreCase(EstadoCommon.DIGITALIZADO)
					    && !ls_estado.equalsIgnoreCase(EstadoCommon.EN_PROCESO))
				)
					throw new B2BException(ErrorKeys.ERROR_ESTADO_NO_VALIDO);

				{
					boolean lb_nir;
					boolean lb_turno;

					lb_nir       = ls_identificador.equalsIgnoreCase(IdentificadoresCommon.NIR);
					lb_turno     = ls_identificador.equalsIgnoreCase(IdentificadoresCommon.TURNO);

					if(!lb_nir && !lb_turno)
						throw new B2BException(ErrorKeys.ERROR_IDENTIFICADOR_TRAMITE_NO_PARAMETRIZADO);

					if(lb_nir)
					{
						Solicitud    ls_solicitud;
						SolicitudDAO lsd_DAO;

						ls_solicitud     = new Solicitud();
						lsd_DAO          = DaoCreator.getSolicitudDAO(ldm_manager);

						ls_solicitud.setNir(ls_numero);

						ls_solicitud = lsd_DAO.findByNir(ls_solicitud);

						if(ls_solicitud == null)
							throw new B2BException(ErrorKeys.ERROR_NIR_NO_ENCONTRADO);

						if(ls_estado.equalsIgnoreCase(EstadoCommon.DIGITALIZADO))
						{
							TurnoHistoria lth_turnoHistoria;
							long          ll_etapa;
							String        ls_estadoActividad;

							lth_turnoHistoria      = new TurnoHistoria();
							ll_etapa               = EtapaCommon.ID_ETAPA_DIGITALIZACION;
							ls_estadoActividad     = EstadoCommon.ASIGNADA;

							lth_turnoHistoria.setIdSolicitud(ls_solicitud.getIdSolicitud());
							lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(ll_etapa));
							lth_turnoHistoria.setEstadoActividad(ls_estadoActividad);

							lth_turnoHistoria = lthd_DAO.findBySolicitudEtapa(lth_turnoHistoria);

							if(lth_turnoHistoria == null)
							{
								Object[] loa_object;
								int      li_contador;

								loa_object      = new String[3];
								li_contador     = 0;

								loa_object[li_contador++]     = StringUtils.getString(ll_etapa);
								loa_object[li_contador++]     = ls_estadoActividad;
								loa_object[li_contador++]     = ls_numero;

								throw new B2BException(addErrorCD(ErrorKeys.ERROR_NIR_SIN_ETAPA, loa_object));
							}

							{
								Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

								lcacd_completitudDocumental = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
										                                    .findAllByIdSolicitud(
										    ls_solicitud.getIdSolicitud()
										);

								validarDigitalizacionCompletitudDocumental(lcacd_completitudDocumental);
							}

							ls_solicitud.setDigitalizada(EstadoCommon.S);
							ls_solicitud.setIdUsuarioModificacion(as_userIdConstant);
							ls_solicitud.setIpModificacion(as_remoteIp);

							lsd_DAO.updateDigitalizadaSolicitud(ls_solicitud);

							{
								Solicitud ls_solicitudProc;

								ls_solicitudProc = new Solicitud();

								ls_solicitudProc.setIdSolicitud(ls_solicitud.getIdSolicitud());
								ls_solicitudProc.setIdUsuarioModificacion(as_userIdConstant);
								ls_solicitudProc.setIpModificacion(as_remoteIp);

								DaoCreator.getProcedimientosDAO(ldm_manager)
									          .procLlaCrearEtapaTrg(
									    ls_solicitudProc, null, IdentificadoresCommon.DIGITALIZACION,
									    NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_DIGITALIZACION)
									);
							}
						}
					}
					else if(lb_turno)
					{
						TurnoDAO ltd_DAO;

						ltd_DAO = DaoCreator.getTurnoDAO(ldm_manager);

						{
							Turno lt_turno;

							lt_turno = new Turno();

							lt_turno.setIdTurno(ls_numero);

							lt_turno = ltd_DAO.findById(ls_numero);

							if(lt_turno == null)
								throw new B2BException(ErrorKeys.ERROR_TURNO_NO_ENCONTRADO);
						}

						{
							TurnoHistoria lth_turnoHistoria;
							long          ll_etapa;
							String        ls_estadoActividad;

							lth_turnoHistoria      = new TurnoHistoria();
							ll_etapa               = EtapaCommon.ID_ETAPA_DIGITALIZACION_CORE_BACHUE;
							ls_estadoActividad     = EstadoCommon.ASIGNADA;

							lth_turnoHistoria.setIdTurno(ls_numero);
							lth_turnoHistoria.setIdEtapa(NumericUtils.getBigDecimal(ll_etapa));
							lth_turnoHistoria.setEstadoActividad(ls_estadoActividad);

							lth_turnoHistoria = lthd_DAO.findByIdTurnoEtapa(lth_turnoHistoria);

							if(lth_turnoHistoria == null)
							{
								Object[] loa_object;
								int      li_contador;

								loa_object      = new String[2];
								li_contador     = 0;

								loa_object[li_contador++]     = StringUtils.getString(ll_etapa);
								loa_object[li_contador++]     = ls_estadoActividad;

								throw new B2BException(addErrorCD(ErrorKeys.ERROR_TURNO_NO_TIENE_ETAPA, loa_object));
							}

							{
								Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

								lcacd_completitudDocumental = DaoCreator.getAccCompletitudDocumentalDAO(ldm_manager)
										                                    .findByIdTurno(ls_numero);

								validarDigitalizacionCompletitudDocumental(lcacd_completitudDocumental);
							}

							lth_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
							lth_turnoHistoria.setIdUsuarioModificacion(as_userIdConstant);
							lth_turnoHistoria.setIpModificacion(as_remoteIp);

							lthd_DAO.insertOrUpdate(lth_turnoHistoria, false);
						}

						{
							Turno lt_turno;

							lt_turno = new Turno();

							lt_turno.setDigitalizado(EstadoCommon.S);
							lt_turno.setIdTurno(ls_numero);

							ltd_DAO.actualizarDigitalizado(lt_turno, as_userIdConstant);
						}
					}
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("notificarDigitalizacion", lb2be_e);

			Map<String, String> lmss_codigoMensaje;

			lmss_codigoMensaje     = obtenerErrorServicios(addErrorCD(lb2be_e.getMessageKey()));

			lbi_codigoMensaje         = NumericUtils.getBigInteger(
				    lmss_codigoMensaje.get(IdentificadoresCommon.CODIGO_MENSAJE)
				);
			ls_descripcionMensaje     = lmss_codigoMensaje.get(IdentificadoresCommon.DESCRIPCION_MENSAJE);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarDigitalizacion", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		ltsnd_tipoSalidaNotificarDigitalizacion.setCodigoMensaje(lbi_codigoMensaje);
		ltsnd_tipoSalidaNotificarDigitalizacion.setDescripcionMensaje(ls_descripcionMensaje);

		return ltsnd_tipoSalidaNotificarDigitalizacion;
	}

	/**
	 * Método encargado de validar si existen registros por digitalizar en completitud documental.
	 *
	 * @param acacd_completitudDocumental Argumento de tipo <code>Collection</code> que contiene los registros a validar.
	 * @throws B2BException Se genera cuando se produce una excepción.
	 */
	private void validarDigitalizacionCompletitudDocumental(
	    Collection<AccCompletitudDocumental> acacd_completitudDocumental
	)
	    throws B2BException
	{
		try
		{
			if(CollectionUtils.isValidCollection(acacd_completitudDocumental))
			{
				boolean                            lb_encontrado;
				Iterator<AccCompletitudDocumental> liacd_iterator;

				lb_encontrado      = false;
				liacd_iterator     = acacd_completitudDocumental.iterator();

				while(liacd_iterator.hasNext() && !lb_encontrado)
				{
					AccCompletitudDocumental lacd_completitudDocumental;

					lacd_completitudDocumental = liacd_iterator.next();

					if(lacd_completitudDocumental != null)
					{
						String ls_digitalizado;

						ls_digitalizado     = lacd_completitudDocumental.getDigitalizado();

						lb_encontrado = !StringUtils.isValidString(ls_digitalizado)
								|| !ls_digitalizado.equalsIgnoreCase(EstadoCommon.S);
					}
				}

				if(lb_encontrado)
					throw new B2BException(ErrorKeys.ERROR_COMPLETITUD_DOCUMENTAL_SIN_DIGITALIZAR);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarDigitalizacionCompletitudDocumental", lb2be_e);

			throw lb2be_e;
		}
	}
}
