package com.bachue.snr.prosnr16.business.cyn.motor.envio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr16.integracion.cliente.cyn.mensajeroSms.ClienteEnviarMensajeSms;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.MensajeDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr16.common.constants.CanalCommon;
import com.bachue.snr.prosnr16.common.constants.ClasificacionCommon;
import com.bachue.snr.prosnr16.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr16.common.constants.ErrorKeys;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;
import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import java.util.Calendar;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades MotorEnvioSmsBusiness.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 26/06/2020
 */
public class MotorEnvioSmsBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorEnvioSmsBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * Método de motor envio sms
	 * @param as_remoteIp con la ip remota del usuario que realiza la pertición
	 * @throws B2BException en caso de error
	 */
	public synchronized void motorEnvioSms(String as_remoteIp)
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
					                    .findById(ConstanteCommon.JOB_MOTOR_ENVIO_ELECTRONICO_WS_INVOKE);

			if(lc_constant != null)
			{
				if(BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
					acm_mensajes = DaoCreator.getMensajeDAO(ldm_manager)
							                     .findMensajesFilter(
							    com.bachue.snr.prosnr16.common.constants.EstadoCommon.RECIBIDO, CanalCommon.SMS
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("motorEnvioSms", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acm_mensajes))
			motorEnvioSms(acm_mensajes, as_remoteIp);
	}

	/**
	 * Método de motor envio SMS
	 * @param acm_parametros con los parametros del envio
	 * @param as_remoteIp con la ip de la máquina que realiza la transaccion
	 * @throws B2BException en caso de error
	 */
	public synchronized void motorEnvioSms(Collection<Mensaje> acm_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_MOTOR_ENVIO_ELECTRONICO_BLOQUEO;
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

				clh_LOGGER.error("motorEnvioSms", lb2be_e);

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

				clh_LOGGER.error("motorEnvioSms", lb2be_e);

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
					String        ls_endpointSms;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_endpointSms = lcd_constantes.findString(ConstanteCommon.JOB_MOTOR_ENVIO_SMS_ENDPOINT);

					if(CollectionUtils.isValidCollection(acm_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(Mensaje lm_iterador : acm_parametros)
						{
							if(lm_iterador != null)
							{
								try
								{
									motorEnvioSms(lm_iterador, lbpd_bitacora, ls_endpointSms, ls_userId, as_remoteIp);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("motorEnvioSms", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("motorEnvioSms", lb2be_e);
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
			clh_LOGGER.error("motorEnvioSms", lb2be_e);

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

					clh_LOGGER.error("motorEnvioSms", lb2be_e);

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
	 * Método de motor envio SMS
	 * @param am_parametros con el mensaje
	 * @param abpd_DAO con la bitacora del turnp
	 * @param as_endpointSms con el endpoint para envio sms
	 * @param as_username con el usuario de la transaccion
	 * @param as_ipRemota con la ip de la transaccion
	 * @throws B2BException
	 */
	public synchronized void motorEnvioSms(
	    Mensaje am_parametros, BitacoraProcesoDAO abpd_DAO, String as_endpointSms, String as_username,
	    String as_ipRemota
	)
	    throws B2BException
	{
		DAOManager ldm_manager;
		MensajeDAO lmd_DAO;
		int        li_intentos;
		boolean    lb_estadoFallido;

		ldm_manager          = DaoManagerFactory.getDAOManagerCYN();
		lmd_DAO              = DaoCreator.getMensajeDAO(ldm_manager);
		li_intentos          = 0;
		lb_estadoFallido     = false;

		if(am_parametros == null)
			throw new B2BException(addErrorCYN(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));

		try
		{
			String ls_tipoCanal;

			ls_tipoCanal     = am_parametros.getIdCanal();
			li_intentos      = am_parametros.getIntentoEnvio();

			if(StringUtils.isValidString(ls_tipoCanal) && ls_tipoCanal.equalsIgnoreCase(CanalCommon.SMS))
			{
				Plantilla lp_plantilla;

				{
					Reintentos lr_reintentos;

					lr_reintentos = DaoCreator.getReintentosDAO(ldm_manager).findById(ls_tipoCanal);

					if(lr_reintentos != null)
						lb_estadoFallido = li_intentos == NumericUtils.getInt(lr_reintentos.getNumeroMaximoIntentos());
				}

				lp_plantilla = DaoCreator.getPlantillaDAO(ldm_manager).findById(am_parametros.getIdPlantilla());

				if(lp_plantilla != null)
				{
					String ls_idMensaje;

					ls_idMensaje = am_parametros.getIdMensaje();

					if(StringUtils.isValidString(ls_idMensaje))
					{
						String ls_clasificacion;
						String ls_numeroTelefono;

						ls_clasificacion      = StringUtils.getStringNotNull(am_parametros.getClasificacion());
						ls_numeroTelefono     = am_parametros.getNumeroCelular();

						llenarAsuntoCuerpoPlantilla(
						    ls_idMensaje, ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION),
						    lp_plantilla, ldm_manager
						);

						if(
						    StringUtils.isValidString(ls_numeroTelefono)
							    && ExpresionRegular.getExpresionRegular().esTelefono(ls_numeroTelefono)
							    && (ls_numeroTelefono.length() == 10)
						)
							ClienteEnviarMensajeSms.enviarMensajeTexto(
							    lp_plantilla.getCuerpo(), ls_numeroTelefono, as_endpointSms
							);
						else
							throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FORMATO_NUMERO_CELULAR));

						am_parametros.setIdEstado(com.bachue.snr.prosnr16.common.constants.EstadoCommon.ENVIADO);
						am_parametros.setFechaEnvio(obtenerDateDesdeCalendar(Calendar.getInstance()));
						am_parametros.setIdUsuarioModificacion(as_username);
						am_parametros.setIpModificacion(as_ipRemota);

						lmd_DAO.update(am_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("motorEnvioSms", lb2be_e);

			{
				if(lb_estadoFallido)
					am_parametros.setIdEstado(com.bachue.snr.prosnr16.common.constants.EstadoCommon.FALLIDO);
				else
				{
					am_parametros.setIdEstado(com.bachue.snr.prosnr16.common.constants.EstadoCommon.RECIBIDO);
					am_parametros.setIntentoEnvio(++li_intentos);
				}

				am_parametros.setIdUsuarioModificacion(as_username);
				am_parametros.setIpModificacion(as_ipRemota);

				lmd_DAO.update(am_parametros);
			}

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.MOTOR_ENVIO_SMS, lb2be_e.getMessage(), as_username, as_endpointSms,
			    (am_parametros != null) ? StringUtils.getString(am_parametros.getIdMensaje()) : null
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
