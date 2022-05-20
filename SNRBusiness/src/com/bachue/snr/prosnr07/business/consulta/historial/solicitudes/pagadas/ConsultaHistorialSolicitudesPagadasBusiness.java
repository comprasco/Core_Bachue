package com.bachue.snr.prosnr07.business.consulta.historial.solicitudes.pagadas;

import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudesSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSalidaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSalidaBuscarSolicitudesCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitudSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoSalidaConsultarDetalleSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoSalidaConsultarDetalleSolicitudCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoTurno;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.TipoPersonaCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.SolicitudDAO;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;

import com.bachue.snr.prosnr07.common.constants.ErrorKeys;

import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las funcionalidades para consultar historia solicitudes pagadas
 *
 * @author jpatino
 */
public class ConsultaHistorialSolicitudesPagadasBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(
			    ConsultaHistorialSolicitudesPagadasBusiness.class,
			    ProyectosCommon.CONSULTA_HISTORIAL_SOLICITUDES_PAGADASA_07
			);

	/**
	 * Método encargado de realizar la operación buscarSolicitudes.
	 *
	 * @param atbs_param Objeto que contiene los datos de entrada de la operación.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_localIp Variable de tipo String que contiene la ip del servidor.
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario.
	 * @return Objeto de salida de la operación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaBuscarSolicitudes
	 */
	public TipoSalidaBuscarSolicitudes buscarSolicitudes(
	    TipoEntradaBuscarSolicitudes atbs_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                               ldm_manager;
		TipoSalidaBuscarSolicitudes              ltbs_return;
		TipoSalidaBuscarSolicitudesCodigoMensaje ltsbscm_codigo;
		TipoSolicitud[]                          ltsa_solicitudes;
		String                                   ls_descripcionMensaje;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ltbs_return               = new TipoSalidaBuscarSolicitudes();
		ltsbscm_codigo            = TipoSalidaBuscarSolicitudesCodigoMensaje.value1;
		ltsa_solicitudes          = new TipoSolicitud[0];
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);

		try
		{
			if(atbs_param != null)
			{
				boolean                                 lb_nir;
				String                                  ls_modulo;
				String                                  ls_servicio;
				String                                  ls_nir;
				TipoEntradaBuscarSolicitudesSolicitante ltebss_solicitante;

				ls_modulo              = atbs_param.getModulo();
				ls_servicio            = atbs_param.getServicio();
				ls_nir                 = atbs_param.getNir();
				ltebss_solicitante     = atbs_param.getSolicitante();
				lb_nir                 = StringUtils.isValidString(ls_nir);

				if(StringUtils.isValidString(ls_modulo))
				{
					if(!ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					{
						ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value4;
						throw new B2BException(ErrorKeys.ERROR_MODULO_NO_PERMITIDO);
					}
				}
				else
				{
					ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value4;
					throw new B2BException(ErrorKeys.ERROR_MODULO_NO_VALIDO);
				}

				if(!StringUtils.isValidString(ls_servicio))
				{
					ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value5;
					throw new B2BException(ErrorKeys.ERROR_SERVICIO_NO_VALIDO);
				}

				if((ltebss_solicitante != null) || lb_nir)
				{
					SolicitudDAO ls_DAO;

					ls_DAO = DaoCreator.getSolicitudDAO(ldm_manager);

					if(!lb_nir)
					{
						Collection<TipoSolicitud> lcts_solicitudes;
						Date                      ld_fechaDesde;
						Date                      ld_fechaHasta;
						String                    ls_numeroDocumento;
						String                    ls_idTipoDocumento;
						String                    ls_idTipoPersona;

						ls_numeroDocumento     = ltebss_solicitante.getNumeroDocumento();
						ls_idTipoDocumento     = ltebss_solicitante.getTipoDocumento();
						ls_idTipoPersona       = ltebss_solicitante.getTipoPersona();
						ld_fechaDesde          = DateUtils.getDate(
							    atbs_param.getFechaDesdeBusqueda(), FormatoFechaCommon.ANIO_MES_DIA_HORA_MINUTO_SEGUNDO
							);
						ld_fechaHasta          = DateUtils.getDate(
							    atbs_param.getFechaHastaBusqueda(), FormatoFechaCommon.ANIO_MES_DIA_HORA_MINUTO_SEGUNDO
							);

						if(ld_fechaDesde == null)
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value6;
							throw new B2BException(ErrorKeys.ERROR_FECHA_DESDE);
						}

						if(ld_fechaHasta == null)
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value6;
							throw new B2BException(ErrorKeys.ERROR_FECHA_HASTA);
						}

						if(ld_fechaDesde.compareTo(ld_fechaHasta) > 0)
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value6;
							throw new B2BException(ErrorKeys.FECHA_DESDE_SUPERIOR);
						}

						if(StringUtils.isValidString(ls_idTipoPersona))
						{
							if(
							    !(ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.INDETERMINADO)
								    || ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.JURIDICA)
								    || ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.NATURAL))
							)
							{
								ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value7;
								throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_SOLICITANTE_NO_PERMITIDO);
							}
						}
						else
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value7;
							throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_SOLICITANTE_NO_VALIDO);
						}

						if(StringUtils.isValidString(ls_idTipoDocumento))
						{
							InteresadoDocumentoTipo lidt_tipoDocumento;

							lidt_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
									                           .findById(ls_idTipoDocumento);

							if(lidt_tipoDocumento == null)
							{
								ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value8;
								throw new B2BException(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO);
							}
						}
						else
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value8;
							throw new B2BException(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO);
						}

						if(!StringUtils.isValidString(ls_numeroDocumento))
						{
							ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value9;
							throw new B2BException(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO);
						}

						lcts_solicitudes = ls_DAO.findSolicitudesPagadas(
							    ls_idTipoDocumento, ls_numeroDocumento, ls_servicio, ld_fechaDesde, ld_fechaHasta
							);

						if(
						    CollectionUtils.isValidCollection(lcts_solicitudes)
							    && (lcts_solicitudes.size() > NumericUtils.DEFAULT_INT_VALUE)
						)
							ltsa_solicitudes = lcts_solicitudes.toArray(new TipoSolicitud[lcts_solicitudes.size()]);
					}
					else
					{
						TipoSolicitud lts_solicitud;

						lts_solicitud = ls_DAO.findSolicitudPagada(ls_nir, ls_servicio);

						if(lts_solicitud != null)
						{
							if(StringUtils.isValidString(lts_solicitud.getFechaServicio()))
							{
								ltsa_solicitudes        = new TipoSolicitud[1];
								ltsa_solicitudes[0]     = lts_solicitud;
							}
						}
					}

					if(!CollectionUtils.isValidCollection(ltsa_solicitudes))
					{
						ltsa_solicitudes        = new TipoSolicitud[1];
						ltsa_solicitudes[0]     = new TipoSolicitud("", "", "", "");

						ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value2;
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
					}
				}
				else
				{
					ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value3;
					throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
				}
			}
			else
			{
				ltsbscm_codigo = TipoSalidaBuscarSolicitudesCodigoMensaje.value3;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_descripcionMensaje = addErrorCHSP(lb2be_e.getMessageKey());

			clh_LOGGER.error("buscarSolicitudes", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltbs_return.setSolicitudes(ltsa_solicitudes);
		ltbs_return.setCodigoMensaje(ltsbscm_codigo);
		ltbs_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltbs_return;
	}

	/**
	 * Método encargado de realizar la operación consultarDetalleSolicitud.
	 *
	 * @param atecds_entrada Objeto que contiene los datos de entrada.
	 * @param as_userId Variable de tipo String que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene los datos de salida.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoSalidaConsultarDetalleSolicitud
	 */
	public TipoSalidaConsultarDetalleSolicitud consultarDetalleSolicitud(
	    TipoEntradaConsultarDetalleSolicitud atecds_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager                                       ldm_manager;
		String                                           ls_descripcionMensaje;
		TipoSalidaConsultarDetalleSolicitud              ltscds_return;
		TipoSalidaConsultarDetalleSolicitudCodigoMensaje ltscdscm_codigo;
		TipoTurno[]                                      ltta_turnos;

		ldm_manager               = DaoManagerFactory.getDAOManager();
		ls_descripcionMensaje     = addMessage(MessagesKeys.OK);
		ltscds_return             = new TipoSalidaConsultarDetalleSolicitud();
		ltscdscm_codigo           = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value1;
		ltta_turnos               = new TipoTurno[0];

		try
		{
			if(atecds_entrada != null)
			{
				TipoEntradaConsultarDetalleSolicitudSolicitante ltecdss_solicitane;
				String                                          ls_modulo;

				ltecdss_solicitane     = atecds_entrada.getSolicitante();
				ls_modulo              = atecds_entrada.getModulo();

				if(StringUtils.isValidString(ls_modulo))
				{
					if(!ls_modulo.equalsIgnoreCase(SistemaOrigenCommon.SEDE_ELECTRONICA))
					{
						ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value4;
						throw new B2BException(ErrorKeys.ERROR_MODULO_NO_PERMITIDO);
					}
				}
				else
				{
					ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value4;
					throw new B2BException(ErrorKeys.ERROR_MODULO_NO_VALIDO);
				}

				if(ltecdss_solicitane != null)
				{
					Collection<TipoTurno> lctt_data;
					String                ls_idTipoPersona;
					String                ls_idTipoDocumento;
					String                ls_numeroDocumento;
					String                ls_nir;

					ls_idTipoPersona       = ltecdss_solicitane.getTipoPersona();
					ls_idTipoDocumento     = ltecdss_solicitane.getTipoDocumento();
					ls_numeroDocumento     = ltecdss_solicitane.getNumeroDocumento();
					ls_nir                 = atecds_entrada.getNir();

					if(StringUtils.isValidString(ls_idTipoPersona))
					{
						if(
						    !(ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.INDETERMINADO)
							    || ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.JURIDICA)
							    || ls_idTipoPersona.equalsIgnoreCase(TipoPersonaCommon.NATURAL))
						)
						{
							ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value5;
							throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_SOLICITANTE_NO_PERMITIDO);
						}
					}
					else
					{
						ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value5;
						throw new B2BException(ErrorKeys.ERROR_TIPO_PERSONA_SOLICITANTE_NO_VALIDO);
					}

					if(StringUtils.isValidString(ls_idTipoDocumento))
					{
						InteresadoDocumentoTipo lidt_tipoDocumento;

						lidt_tipoDocumento = DaoCreator.getInteresadoDocumentoTipoDAO(ldm_manager)
								                           .findById(ls_idTipoDocumento);

						if(lidt_tipoDocumento == null)
						{
							ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value6;
							throw new B2BException(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO);
						}
					}
					else
					{
						ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value6;
						throw new B2BException(ErrorKeys.ERROR_TIPO_DOC_SOLICITANTE_NO_VALIDO);
					}

					if(!StringUtils.isValidString(ls_numeroDocumento))
					{
						ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value7;
						throw new B2BException(ErrorKeys.ERROR_NUMERO_DOC_SOLICITANTE_NO_VALIDO);
					}

					if(!StringUtils.isValidString(ls_nir))
					{
						ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value8;
						throw new B2BException(ErrorKeys.ERROR_NIR_INVALIDO);
					}

					lctt_data = DaoCreator.getSolicitudDAO(ldm_manager)
							                  .findSolicitudesTurnoPagados(
							    ls_idTipoDocumento, ls_numeroDocumento, ls_nir
							);

					if(CollectionUtils.isValidCollection(lctt_data))
						ltta_turnos = lctt_data.toArray(new TipoTurno[lctt_data.size()]);
				}
				else
				{
					ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value3;
					throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
				}

				if(!CollectionUtils.isValidCollection(ltta_turnos))
				{
					ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value2;
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				}
			}
			else
			{
				ltscdscm_codigo = TipoSalidaConsultarDetalleSolicitudCodigoMensaje.value3;
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			ls_descripcionMensaje = addErrorCHSP(lb2be_e.getMessageKey());

			clh_LOGGER.error("consultarDetalleSolicitud", lb2be_e);
		}
		finally
		{
			ldm_manager.close();
		}

		ltscds_return.setTurnos(ltta_turnos);
		ltscds_return.setCodigoMensaje(ltscdscm_codigo);
		ltscds_return.setDescripcionMensaje(ls_descripcionMensaje);

		return ltscds_return;
	}
}
