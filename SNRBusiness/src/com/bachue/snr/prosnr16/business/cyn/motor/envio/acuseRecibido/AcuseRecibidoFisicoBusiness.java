package com.bachue.snr.prosnr16.business.cyn.motor.envio.acuseRecibido;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr16.integracion.cliente.cyn.envioAcuseRecibido.ClienteAcusarMensaje;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr16.common.constants.ErrorKeys;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;
import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAcuseDetalle;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 26/03/2020
 */
public class AcuseRecibidoFisicoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AcuseRecibidoFisicoBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * Acuse recibido.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoFisico(String as_remoteIp)
	    throws B2BException
	{
		Collection<Mensaje> acm_mensajes;
		DAOManager          ldm_manager;

		acm_mensajes     = null;
		ldm_manager      = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			Constantes lc_constant;

			lc_constant = DaoCreator.getConstantesDAO(ldm_manager)
					                    .findById(ConstanteCommon.JOB_ACUSE_RECIBIDO_FISICO_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					acm_mensajes = DaoCreator.getMensajeDAO(ldm_manager)
							                     .findMensajesFilter(
							    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_RECIBIDO,
							    CanalCommon.FISICO, true, ClasificacionCommon.NOTIFICACION
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acm_mensajes))
			acuseRecibidoFisico(acm_mensajes, as_remoteIp);
	}

	/**
	 * Acuse recibido fisico.
	 *
	 * @param acm_mensajes de acm mensajes
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoFisico(Collection<Mensaje> acm_mensajes, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_ACUSE_RECIBIDO_FISICO_BLOQUEO;
		ls_userId       = null;

		{
			DAOManager ldm_usuario;

			ldm_usuario = DaoManagerFactory.getDAOManagerCYN();

			try
			{
				ls_userId = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, false, ldm_usuario);
			}
			catch(B2BException lb2be_e)
			{
				ldm_usuario.setRollbackOnly();

				clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

				throw lb2be_e;
			}
			finally
			{
				ldm_usuario.close();
			}
		}

		{
			DAOManager ldm_processing;

			ldm_processing = DaoManagerFactory.getDAOManagerCYN();

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
					loa_messageArgs[0]     = StringUtils.getString(ls_constant);

					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs));
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

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
				DAOManager ldm_bitacora;
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManagerCYN();
				ldm_constantes     = DaoManagerFactory.getDAOManagerCYN();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_endpoint = lcd_constantes.findString(ConstanteCommon.JOB_ACUSE_RECIBIDO_FISICO_ENDPOINT);

					if(CollectionUtils.isValidCollection(acm_mensajes))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(Mensaje lm_iterador : acm_mensajes)
						{
							if(lm_iterador != null)
							{
								try
								{
									acuseRecibidoFisico(
									    lm_iterador, lbpd_bitacora, ls_endpoint, ls_userId, as_remoteIp
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("acuseRecibidoFisico", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);
				}
				finally
				{
					ldm_bitacora.close();
					ldm_constantes.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			if(!lb_alreadyProcessing)
			{
				DAOManager ldm_processing;
				ldm_processing = DaoManagerFactory.getDAOManagerCYN();

				ldm_processing.setAutoCommit(true);

				try
				{
					DaoCreator.getConstantesDAO(ldm_processing).updateString(ls_constant, EstadoCommon.N, ls_userId);
				}
				catch(B2BException lb2be_e)
				{
					ldm_processing.setRollbackOnly();

					clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

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
	 * Acuse recibido fisico.
	 *
	 * @param lm_iterador de lm iterador
	 * @param abpd_DAO de abpd DAO
	 * @param as_endpoint de as endpoint
	 * @param as_username de as username
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void acuseRecibidoFisico(
	    Mensaje lm_iterador, BitacoraProcesoDAO abpd_DAO, String as_endpoint, String as_username, String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			if(lm_iterador != null)
			{
				MensajeAcuseDetalle lmad_acuceDetalle;

				lmad_acuceDetalle = DaoCreator.getMensajeAcuseDetalleDAO(ldm_manager)
						                          .findByIdMensaje(lm_iterador.getIdMensaje());

				if(lmad_acuceDetalle != null)
				{
					{
						lm_iterador.setIdEstado(
						    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_NOTIFICADO
						);
						lm_iterador.setIdUsuarioModificacion(as_username);
						lm_iterador.setIpModificacion(as_ipRemota);

						DaoCreator.getMensajeDAO(ldm_manager).update(lm_iterador);
					}

					ClienteAcusarMensaje.acusarMensaje(
					    lm_iterador.getIdMensaje(), lmad_acuceDetalle.getGuiaCorrespondenciaFisica(),
					    obtenerCalendarDesdeDate(lmad_acuceDetalle.getFechaAcuseDetalle()), obtenerCalendarDesdeDate(lm_iterador.getFechaEnvio()),
					    as_endpoint
					);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("acuseRecibidoFisico", lb2be_e);

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.MOTOR_ENVIO_FISICO, lb2be_e.getMessage(), as_username, as_endpoint,
			    (lm_iterador != null) ? StringUtils.getString(lm_iterador.getIdMensaje()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
