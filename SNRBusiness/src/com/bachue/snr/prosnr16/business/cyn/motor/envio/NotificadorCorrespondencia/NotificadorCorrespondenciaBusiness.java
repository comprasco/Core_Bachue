package com.bachue.snr.prosnr16.business.cyn.motor.envio.NotificadorCorrespondencia;

import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia;
import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.CodigoMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.DescripcionMensajeCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.MensajeDAO;

import com.bachue.snr.prosnr16.common.constants.ErrorKeys;
import com.bachue.snr.prosnr16.common.constants.EstadoCommon;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;
import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAcuseDetalle;

import java.math.BigInteger;

import java.util.Calendar;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades NotificadorCorrespondenciaBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 1/04/2020
 */
public class NotificadorCorrespondenciaBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NotificadorCorrespondenciaBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * Notificar correspondencia.
	 *
	 * @param atenc_entrada de atenc entrada
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de tipo salida notificar correspondencia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    TipoEntradaNotificarCorrespondencia atenc_entrada, String as_userId, String as_localIpAddress,
	    String                              as_remoteIpAddress
	)
	    throws B2BException
	{
		String     ls_descripcionMensaje;
		DAOManager ldm_manager;
		BigInteger lbi_codigoMensaje;

		ls_descripcionMensaje     = DescripcionMensajeCommon.EXITO;
		ldm_manager               = DaoManagerFactory.getDAOManagerCYN();
		lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_200);

		try
		{
			if(atenc_entrada != null)
			{
				String ls_identificadorEE;

				ls_identificadorEE = atenc_entrada.getIdentificador();

				if(StringUtils.isValidString(ls_identificadorEE))
				{
					MensajeDAO lmd_DAO;
					Mensaje    lm_mensaje;

					lmd_DAO        = DaoCreator.getMensajeDAO(ldm_manager);
					lm_mensaje     = lmd_DAO.findById(ls_identificadorEE, true, false);

					if(lm_mensaje != null)
					{
						MensajeAcuseDetalle lmad_acuse;

						lmad_acuse = new MensajeAcuseDetalle();

						{
							String ls_estado;

							ls_estado = lm_mensaje.getIdEstado();

							if(StringUtils.isValidString(ls_estado) && ls_estado.equalsIgnoreCase(EstadoCommon.ENVIADO))
								lm_mensaje.setIdEstado(EstadoCommon.ACUSADO_RECIBIDO);
							else
								throw new B2BException(addErrorCYN(ErrorKeys.ERROR_ESTADO_INVALIDO_OPERACION));
						}

						{
							String ls_tmp;

							ls_tmp = StringUtils.getString(atenc_entrada.getGuia());

							if(StringUtils.isValidString(ls_tmp))
								lmad_acuse.setGuiaCorrespondenciaFisica(ls_tmp);
							else
								throw new B2BException(addErrorCYN(ErrorKeys.ERROR_NUMERO_GUIA_INVALIDO));
						}

						{
							Calendar lc_tmp;

							lc_tmp = atenc_entrada.getFechaAcuse();

							if(lc_tmp != null)
								lmad_acuse.setFechaAcuseDetalle(obtenerDateDesdeCalendar(lc_tmp));
							else
								throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FECHA_ACUSE_INVALIDO));
						}

						{
							Calendar lc_tmp;

							lc_tmp = atenc_entrada.getFechaEnvio();

							if(lc_tmp != null)
								lmad_acuse.setFechaEnvioCorrespondencia(obtenerDateDesdeCalendar(lc_tmp));
							else
								throw new B2BException(addErrorCYN(ErrorKeys.ERROR_FECHA_ENVIO_INVALIDO));
						}

						lmad_acuse.setIdMensaje(lm_mensaje.getIdMensaje());
						lmad_acuse.setIdUsuarioCreacion(as_userId);
						lmad_acuse.setIpCreacion(as_remoteIpAddress);

						DaoCreator.getMensajeAcuseDetalleDAO(ldm_manager).insert(lmad_acuse);

						lm_mensaje.setIdUsuarioCreacion(as_userId);
						lm_mensaje.setIpCreacion(as_remoteIpAddress);

						lmd_DAO.update(lm_mensaje);
					}
					else
						throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_EE_NO_EXISTE));
				}
				else
					throw new B2BException(addErrorCYN(ErrorKeys.ERROR_IDENTIFICADOR_EE_INVALIDO));
			}
			else
				throw new B2BException(addErrorCYN(ErrorKeys.ERROR_OPERACION_NO_EXITOSA));
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();
			clh_LOGGER.error("notificarCorrespondencia", lb2be_e);

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
			clh_LOGGER.error("notificarCorrespondencia", le_e);

			lbi_codigoMensaje         = BigInteger.valueOf(CodigoMensajeCommon.CODIGO_500);
			ls_descripcionMensaje     = le_e.getMessage();
		}
		finally
		{
			ldm_manager.close();
		}

		return new TipoSalidaNotificarCorrespondencia(lbi_codigoMensaje, ls_descripcionMensaje);
	}
}
