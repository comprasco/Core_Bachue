package com.bachue.snr.prosnr16.business.cyn.motor.envio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.mail.SendMail;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

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
import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAcuseDetalle;
import com.bachue.snr.prosnr16.model.sdb.pgn.Plantilla;
import com.bachue.snr.prosnr16.model.sdb.pgn.Reintentos;

import java.security.SecureRandom;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;

import javax.mail.internet.AddressException;


/**
 * Clase que contiene todos las propiedades MotorEnvioElectronicoBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/03/2020
 */
public class MotorEnvioElectronicoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MotorEnvioElectronicoBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * Motor envio electronico.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void motorEnvioElectronico(String as_remoteIp)
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
							    com.bachue.snr.prosnr16.common.constants.EstadoCommon.RECIBIDO, CanalCommon.ELECTRONICO
							);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(acm_mensajes))
			motorEnvioElectronico(acm_mensajes, as_remoteIp);
	}

	/**
	 * Motor envio electronico.
	 *
	 * @param acm_parametros de acm parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void motorEnvioElectronico(Collection<Mensaje> acm_parametros, String as_remoteIp)
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

				clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

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

				clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

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
					String        ls_jndiJavaMail;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);

					ls_jndiJavaMail = lcd_constantes.findString(ConstanteCommon.JOB_MOTOR_ENVIO_ELECTRONICO_JNDI);

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
									motorEnvioElectronico(
									    lm_iterador, lbpd_bitacora, ls_jndiJavaMail, ls_userId, as_remoteIp
									);
								}
								catch(Exception le_e)
								{
									clh_LOGGER.error("motorEnvioElectronico", le_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("motorEnvioElectronico", lb2be_e);
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
			clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

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

					clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

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
	 * Motor envio electronico.
	 *
	 * @param am_parametros de am parametros
	 * @param abpd_DAO de abpd DAO
	 * @param as_jndiJavaMail correspondiente al valor de jndi java mail
	 * @param as_username de as username
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws AddressException cuando se produce algun error en el proceso
	 * @throws MessagingException cuando se produce algun error en el proceso
	 */
	public synchronized void motorEnvioElectronico(
	    Mensaje am_parametros, BitacoraProcesoDAO abpd_DAO, String as_jndiJavaMail, String as_username,
	    String as_ipRemota
	)
	    throws B2BException, AddressException, MessagingException
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

			if(StringUtils.isValidString(ls_tipoCanal) && ls_tipoCanal.equalsIgnoreCase(CanalCommon.ELECTRONICO))
			{
				Plantilla     lp_plantilla;
				ConstantesDAO lcd_constantes;
				boolean       lb_validarCertimail;

				lcd_constantes          = DaoCreator.getConstantesDAO(ldm_manager);
				lb_validarCertimail     = BooleanUtils.getBooleanValue(
					    lcd_constantes.findString(ConstanteCommon.VALIDAR_CONTRA_CERTIMAIL)
					);

				{
					Reintentos lr_reintentos;

					lr_reintentos = DaoCreator.getReintentosDAO(ldm_manager).findById(ls_tipoCanal);

					if(lr_reintentos != null)
						lb_estadoFallido = li_intentos == NumericUtils.getInt(lr_reintentos.getNumeroMaximoIntentos());
				}

				lp_plantilla = DaoCreator.getPlantillaDAO(ldm_manager).findById(am_parametros.getIdPlantilla());

				if(lp_plantilla != null)
				{
					Date   ld_date;
					String ls_identificadorMensaje;
					String ls_idMensaje;
					String ls_clasificacion;

					ld_date                     = new Date();
					ls_clasificacion            = null;
					ls_identificadorMensaje     = null;
					ls_idMensaje                = am_parametros.getIdMensaje();

					if(StringUtils.isValidString(ls_idMensaje))
					{
						SendMail lse_emailPorEnviar;

						ls_clasificacion       = StringUtils.getStringNotNull(am_parametros.getClasificacion());
						lse_emailPorEnviar     = new SendMail(as_jndiJavaMail, false, false, false);

						llenarAsuntoCuerpoPlantilla(
						    ls_idMensaje, ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION),
						    lp_plantilla, ldm_manager
						);

						lse_emailPorEnviar.setSubject(lp_plantilla.getAsunto());
						lse_emailPorEnviar.setBody(lp_plantilla.getCuerpo());

						{
							String ls_correo;

							ls_correo = am_parametros.getCorreoElectronico();

							if(
							    ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION)
								    && lb_validarCertimail
							)
								ls_correo = ls_correo + ".rpost.biz";

							lse_emailPorEnviar.setTo(ls_correo);
						}

						{
							Map<String, byte[]> lmsb_archivosAdjuntos;

							lmsb_archivosAdjuntos = obtenerArchivosAdjuntos(ls_idMensaje, ldm_manager);

							if(CollectionUtils.isValidCollection(lmsb_archivosAdjuntos))
								lse_emailPorEnviar.setAttach(lmsb_archivosAdjuntos);
						}

						ls_identificadorMensaje = lse_emailPorEnviar.sendMailEvent();
					}

					am_parametros.setIdEstado(com.bachue.snr.prosnr16.common.constants.EstadoCommon.ENVIADO);
					am_parametros.setFechaEnvio(ld_date);
					am_parametros.setIdentificadorMensaje(
					    (StringUtils.isValidString(ls_identificadorMensaje)
						    && ls_clasificacion.equalsIgnoreCase(ClasificacionCommon.NOTIFICACION))
					    ? ls_identificadorMensaje : null
					);
					am_parametros.setIdUsuarioModificacion(as_username);
					am_parametros.setIpModificacion(as_ipRemota);

					lmd_DAO.update(am_parametros);

					if(!lb_validarCertimail)
					{
						MensajeAcuseDetalle lmad_acuse;

						lmad_acuse = new MensajeAcuseDetalle();

						lmad_acuse.setGuiaCorrespondenciaFisica(
						    StringUtils.getString(new SecureRandom().nextInt(NumericUtils.getInt(ld_date.getTime())))
						);
						lmad_acuse.setFechaAcuseDetalle(ld_date);
						lmad_acuse.setFechaEnvioCorrespondencia(ld_date);

						lmad_acuse.setIdMensaje(am_parametros.getIdMensaje());
						lmad_acuse.setIdUsuarioCreacion(as_username);
						lmad_acuse.setIpCreacion(as_ipRemota);

						DaoCreator.getMensajeAcuseDetalleDAO(ldm_manager).insert(lmad_acuse);

						am_parametros.setIdEstado(
						    com.bachue.snr.prosnr16.common.constants.EstadoCommon.ACUSADO_RECIBIDO
						);
						am_parametros.setIdUsuarioModificacion(as_username);
						am_parametros.setIpModificacion(as_ipRemota);

						lmd_DAO.update(am_parametros);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("motorEnvioElectronico", lb2be_e);

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
			    abpd_DAO, IdentificadoresCommon.MOTOR_ENVIO_ELECTRONICO, lb2be_e.getMessage(), as_username,
			    as_jndiJavaMail, (am_parametros != null) ? StringUtils.getString(am_parametros.getIdMensaje()) : null
			);

			throw lb2be_e;
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("motorEnvioElectronico", le_e);

			{
				if(lb_estadoFallido)
					am_parametros.setIdEstado(com.bachue.snr.prosnr16.common.constants.EstadoCommon.FALLIDO);
				else
					am_parametros.setIntentoEnvio(++li_intentos);

				am_parametros.setIdUsuarioModificacion(as_username);
				am_parametros.setIpModificacion(as_ipRemota);

				lmd_DAO.update(am_parametros);
			}

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.MOTOR_ENVIO_ELECTRONICO, le_e.getMessage(), as_username, as_jndiJavaMail,
			    (am_parametros != null) ? StringUtils.getString(am_parametros.getIdMensaje()) : null
			);

			throw new B2BException(le_e.getMessage());
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
