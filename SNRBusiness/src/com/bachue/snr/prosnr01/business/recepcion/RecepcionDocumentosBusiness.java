package com.bachue.snr.prosnr01.business.recepcion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.util.Collection;
import java.util.Date;


/**
 * Clase para el manejo del negocio de recepcion documentos
 * @author Gabriel Arias
 *
 */
public class RecepcionDocumentosBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RecepcionDocumentosBusiness.class);

	/**
	 * Busca los casos en estado aut para que el job los procese
	 *
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void recepcionDocumentos(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_data;
		DAOManager                ldm_manager;

		lcth_data       = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_data = obtenerTurnosJob(
				    ConstanteCommon.JOB_RECEPCION_DOCUMENTOS_WS_INVOKE,
				    ConstanteCommon.JOB_RECEPCION_DOCUMENTOS_LIMITE_INTENTOS, EtapaCommon.ID_ETAPA_16, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("recepcionDocumentos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_data))
			recepcionDocumentos(lcth_data, as_remoteIp);
	}

	/**
	 * Bloquea el JOB e invoca el proceso
	 *
	 * @param acth_parametros Colección de casos a realizar el proceso
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void recepcionDocumentos(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_RECEPCION_DOCUMENTOS_BLOQUEO;
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

				clh_LOGGER.error("recepcionDocumentos", lb2be_e);

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
									recepcionDocumentos(lbp_DAO, lth_iterador, as_remoteIp, ls_userId);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("recepcionDocumentos", lb2be_e);

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

					clh_LOGGER.error("recepcionDocumentos", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("recepcionDocumentos", lb2be_e);

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

					clh_LOGGER.error("recepcionDocumentos", lb2be_e);

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
	 * Método encargado de ejecutar el procedimiento de recepcion documentos
	 *
	 * @param abpd_bitacoraProcesoDAO DAO de bitacoraProceso
	 * @param ath_turnoHistoria Objeto que contiene la información del turno historia
	 * @param as_remoteIp Variable que contiene la ip
	 * @param as_userId Variable que contiene el usuario
	 * @throws B2BException
	 */
	public synchronized void recepcionDocumentos(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String as_userId
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				Long ll_idTurnoHistoria;

				ll_idTurnoHistoria = ath_turnoHistoria.getIdTurnoHistoria();

				if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ath_turnoHistoria.getIdSolicitud());

					if(ls_solicitud != null)
					{
						String ls_idTurnoAnt;
						String ls_idProceso;

						ls_idProceso      = ls_solicitud.getIdProceso();
						ls_idTurnoAnt     = ls_solicitud.getIdTurnoAnt();

						if(
						    StringUtils.isValidString(ls_idTurnoAnt)
							    || (StringUtils.isValidString(ls_idProceso)
							    && ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_46))
						)
						{
							int      li_respuesta;
							Registro lr_arg;

							li_respuesta     = -1;
							lr_arg           = new Registro();

							lr_arg.setSolicitud(ls_solicitud);
							lr_arg.setIdTurno(ls_idTurnoAnt);
							lr_arg.setIdUsuarioCreacion(as_userId);
							lr_arg.setIpCreacion(as_remoteIp);

							li_respuesta = DaoCreator.getProcedimientosDAO(ldm_manager).procRecepcionDocumentos(lr_arg);

							if(li_respuesta >= 0)
							{
								ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
								ath_turnoHistoria.setIdUsuarioModificacion(as_userId);
								ath_turnoHistoria.setIpModificacion(as_remoteIp);

								DaoCreator.getTurnoHistoriaDAO(ldm_manager).insertOrUpdate(ath_turnoHistoria, false);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TURNO_ANT_INVALIDO);
					}
					else
						throw new B2BException(ErrorKeys.ERROR_ID_SOLICITUD_VALIDO);
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

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.RECEPCION, lb2be_e.getMessage(), as_userId, as_remoteIp,
			    (ath_turnoHistoria != null) ? StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
