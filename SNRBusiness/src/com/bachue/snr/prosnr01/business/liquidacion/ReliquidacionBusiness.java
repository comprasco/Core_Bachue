package com.bachue.snr.prosnr01.business.liquidacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.registro.RegistroBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.util.Collection;
import java.util.Date;


/**
 * Maneja las transacciones y validaciones correspondientes al proceso de reliquidación.
 *
 * @author Gabriel Arias
 */
public class ReliquidacionBusiness extends RegistroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReliquidacionBusiness.class);

	/**
	 * Reliquidacion.
	 *
	 * @param acl_parametros the acl parametros
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void reliquidacion(Collection<Liquidacion> acl_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_RELIQUIDACION_BLOQUEO;
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

				clh_LOGGER.error("reliquidacion", lb2be_e);

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

				clh_LOGGER.error("reliquidacion", lb2be_e);

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
					if(CollectionUtils.isValidCollection(acl_parametros))
					{
						BitacoraProcesoDAO lbpd_bitacora;

						lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);

						ldm_bitacora.setAutoCommit(true);

						for(Liquidacion ll_iterador : acl_parametros)
						{
							if(ll_iterador != null)
							{
								String ls_mensaje;

								ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

								try
								{
									reliquidacion(ll_iterador, as_remoteIp, ls_userId, lbpd_bitacora);

									ll_iterador.setFechaExitoEjecucionAutomatica(new Date());
								}
								catch(B2BException lb2be_e)
								{
									clh_LOGGER.error("reliquidacion", lb2be_e);

									ls_mensaje = getErrorMessage(lb2be_e);
								}

								{
									long ll_numeroIntentos;

									ll_numeroIntentos = ll_iterador.getIntentosFallidosEjecucionAutomatica();

									ll_iterador.setIntentosFallidosEjecucionAutomatica(ll_numeroIntentos + 1);
									ll_iterador.setRespuestaEjecucionAutomatica(ls_mensaje);
									ll_iterador.setIdUsuarioModificacion(ls_userId);
									ll_iterador.setIpModificacion(as_remoteIp);

									DaoCreator.getAccLiquidacionDAO(ldm_bitacora).updateIntentoEnvios(ll_iterador);
								}
							}
						}
					}
				}
				catch(Exception le_e)
				{
					ldm_bitacora.setRollbackOnly();

					clh_LOGGER.error("reliquidacion", le_e);
				}
				finally
				{
					ldm_bitacora.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("reliquidacion", lb2be_e);

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

					clh_LOGGER.error("reliquidacion", lb2be_e);

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
	 * Reliquidacion.
	 *
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public synchronized void reliquidacion(String as_remoteIp)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_data;
		DAOManager              ldm_manager;

		lcl_data        = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcl_data = obtenerDataJob(
				    ConstanteCommon.JOB_RELIQUIDACION_WS_INVOKE, ConstanteCommon.JOB_RELIQUIDACION_LIMITE_INTENTOS,
				    ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("reliquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcl_data))
			reliquidacion(lcl_data, as_remoteIp);
	}

	/**
	 * Obtener data job.
	 *
	 * @param as_idConstanteInvoke the as id constante invoke
	 * @param as_idConstanteLimiteIntentos the as id constante limite intentos
	 * @param adm_manager the adm manager
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	private synchronized Collection<Liquidacion> obtenerDataJob(
	    String as_idConstanteInvoke, String as_idConstanteLimiteIntentos, DAOManager adm_manager
	)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_return;

		lcl_return = null;

		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(adm_manager);
			lc_constant           = lcd_constantesDAO.findById(as_idConstanteInvoke);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_cantidadIntentos;

				ll_cantidadIntentos     = lcd_constantesDAO.findEnteroById(as_idConstanteLimiteIntentos);
				lcl_return              = DaoCreator.getAccLiquidacionDAO(adm_manager)
						                                .findReliquidacion(ll_cantidadIntentos);
			}
		}

		return lcl_return;
	}

	/**
	 * Reliquidacion.
	 *
	 * @param al_liquidacion the al liquidacion
	 * @param as_remoteIp the as remote ip
	 * @param as_usuarioProceso the as usuario proceso
	 * @param abpd_DAO the abpd DAO
	 * @throws B2BException the b 2 B exception
	 */
	private synchronized void reliquidacion(
	    Liquidacion al_liquidacion, String as_remoteIp, String as_usuarioProceso, BitacoraProcesoDAO abpd_DAO
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(al_liquidacion != null)
			{
				String ls_idSolicitud;

				ls_idSolicitud = al_liquidacion.getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					Solicitud ls_solicitud;

					ls_solicitud = DaoCreator.getSolicitudDAO(ldm_manager).findById(ls_idSolicitud);

					if(ls_solicitud != null)
					{
						boolean  lb_certificados;
						Registro lr_parametros;

						lb_certificados     = false;
						lr_parametros       = new Registro();

						{
							String ls_idProceso;

							ls_idProceso        = ls_solicitud.getIdProceso();
							lb_certificados     = StringUtils.isValidString(ls_idProceso)
									&& ls_idProceso.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1);
						}

						lr_parametros.setIdUsuarioCreacion(as_usuarioProceso);
						lr_parametros.setSolicitud(ls_solicitud);
						lr_parametros.setIdCondicion(IdentificadoresCommon.LIQUIDAR);
						lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);
						lr_parametros.setProcesoCertificados(lb_certificados);
						lr_parametros.setConsecutivoLiquidacion(al_liquidacion.getConsecutivo());

						lr_parametros = generarReciboLiquidacion(
							    lr_parametros, true, as_usuarioProceso, null, null, ldm_manager
							);

						if(lr_parametros != null)
						{
							DocumentosSalida lds_documento;

							lds_documento = lr_parametros.getDocumentoSalida();

							if(lds_documento != null)
							{
								String ls_endpoint;

								ls_endpoint = DaoCreator.getConstantesDAO(ldm_manager)
										                    .findString(
										    ConstanteCommon.JOB_ENVIO_GESTOR_DOCUMENTAL_ENDPOINT
										);

								enviarGestorDocumental(
								    lds_documento, abpd_DAO, ls_endpoint, as_usuarioProceso, as_remoteIp, ldm_manager
								);
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_DAO, IdentificadoresCommon.RELIQUIDACION, lb2be_e.getMessage(), as_usuarioProceso, as_remoteIp,
			    StringUtils.getString(al_liquidacion.getIdTurnoHistoria())
			);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}
}
