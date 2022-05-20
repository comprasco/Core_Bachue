package com.bachue.snr.prosnr01.business.registro;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;
import com.bachue.snr.prosnr01.business.calificacion.RegistroCalificacionBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;
import com.bachue.snr.prosnr01.dao.pgn.MotivoTramiteDAO;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;


/**
 * Maneja las transacciones y validaciones correspondientes al proceso de cancelaciones
 *
 * @author mblanco
 *
 */
public class CancelacionBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CancelacionBusiness.class);

	/**
	 * Metodo de transacciones con la base de datos con el fin de encontrar las cancelaciones existentes
	 * en la tabla SDB_ACC_TURNO_HISTORIA
	 * @throws B2BException
	 */
	public synchronized void cancelacion(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosCancelacion;
		DAOManager                ldm_manager;

		lcth_turnosCancelacion     = null;
		ldm_manager                = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_turnosCancelacion = obtenerTurnosJob(
				    ConstanteCommon.JOB_CANCELACION_WS_INVOKE, ConstanteCommon.JOB_CANCELACION_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_CANCELACION, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findCancelacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_turnosCancelacion))
			findCancelacion(lcth_turnosCancelacion, as_remoteIp);
	}

	/**
	 * Maneja transacciones con la base de datos para procesar los tramites en cancelaciones.
	 *
	 * @param acth_parametros colecccion que se va a procesar
	 * @param as_remoteIp Dirección IP donde se ejecuta el proceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void findCancelacion(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CANCELACION_BLOQUEO;
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

				clh_LOGGER.error("findCancelacion", lb2be_e);

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

				clh_LOGGER.error("findCancelacion", lb2be_e);

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
				DAOManager ldm_bitacora;

				ldm_bitacora = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(TurnoHistoria lth_iterador : acth_parametros)
						{
							if(lth_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									generarCancelacion(lth_iterador, as_remoteIp, ls_userId, lbpd_bitacora);

									lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("findCancelacion", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								actualizarIntentoEnvio(lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_bitacora);
							}
						}
					}
				}
				catch(Exception le_e)
				{
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("findCancelacion", le_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findCancelacion", lb2be_e);

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

					clh_LOGGER.error("findCancelacion", lb2be_e);

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
	 * Manejo de transacciones con la base de datos para procesar los tramites en cancelaciones.
	 *
	 * @param ath_turnoHistoria colecccion que se va a procesar
	 * @param as_remoteIp Dirección IP donde se ejecuta el proceso
	 * @param as_usuarioProceso correspondiente al valor de usuario proceso
	 * @param abpd_DAO correspondiente al valor de BitacoraProcesoDAO
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private synchronized void generarCancelacion(
	    TurnoHistoria ath_turnoHistoria, String as_remoteIp, String as_usuarioProceso, BitacoraProcesoDAO abpd_DAO
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria != null)
			{
				RegistroCalificacion lrc_registroCalificacion;

				lrc_registroCalificacion = new RegistroCalificacion();

				lrc_registroCalificacion.setIdTurnoHistoria(ath_turnoHistoria.getIdTurnoHistoria());

				RegistroCalificacionBusiness lrcb_regCalBus;
				lrcb_regCalBus = new RegistroCalificacionBusiness();

				lrcb_regCalBus.genereteFileRegistro(
				    lrc_registroCalificacion, true, as_usuarioProceso, as_remoteIp, true
				);

				{
					MotivoTramite    lmt_motivo;
					MotivoTramiteDAO lmtd_DAO;
					long             ll_idMotivo;

					lmt_motivo      = new MotivoTramite();
					lmtd_DAO        = DaoCreator.getMotivoTramiteDAO(ldm_manager);
					ll_idMotivo     = MotivoTramiteCommon.CANCELACION_REALIZAR_REGISTRO;

					lmt_motivo.setIdEtapaOrigen(EtapaCommon.ID_ETAPA_CANCELACION);
					lmt_motivo.setIdMotivo(ll_idMotivo);

					lmt_motivo = lmtd_DAO.findById(lmt_motivo);

					if(lmt_motivo != null)
					{
						ath_turnoHistoria.setEstadoActividad(EstadoCommon.TERMINADA);
						ath_turnoHistoria.setMotivo(lmt_motivo.getDescripcion());
						ath_turnoHistoria.setIdMotivo(NumericUtils.getLongWrapper(lmt_motivo.getIdMotivo()));
						ath_turnoHistoria.setIdUsuarioModificacion(as_usuarioProceso);

						DaoCreator.getTurnoHistoriaDAO(ldm_manager).insertOrUpdate(ath_turnoHistoria, false);

						DaoCreator.getProcedimientosDAO(ldm_manager).spCreateStage(ath_turnoHistoria);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.GENERAR_CANCELACION, lb2be_e.getMessage(), as_usuarioProceso,
			    as_remoteIp, StringUtils.getString(ath_turnoHistoria.getIdTurnoHistoria()) 
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
