package com.bachue.snr.prosnr01.business.crearTurno;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.owcc.crearTurno.ClienteCrearTurno;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.SistemaOrigenCommon;
import com.bachue.snr.prosnr01.common.constants.parametros.ParametrosOWCCCommon;
import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.BitacoraProcesoDAO;
import com.bachue.snr.prosnr01.dao.acc.TurnoDAO;
import com.bachue.snr.prosnr01.dao.pgn.ConstantesDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades CrearTurnoBusiness.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class CrearTurnoBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CrearTurnoBusiness.class);

	/**
	 * Crear turno.
	 *
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void crearTurno(String as_remoteIp)
	    throws B2BException
	{
		Collection<Turno> lct_collection;
		DAOManager        ldm_manager;

		lct_collection     = null;
		ldm_manager        = DaoManagerFactory.getDAOManager();

		try
		{
			ConstantesDAO lcd_constantesDAO;
			Constantes    lc_constant;

			lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
			lc_constant           = DaoCreator.getConstantesDAO(ldm_manager)
					                              .findById(ConstanteCommon.JOB_CREAR_TURNO_WS_INVOKE);

			if((lc_constant != null) && BooleanUtils.getBooleanValue(lc_constant.getCaracter()))
			{
				long ll_cantidadIntentos;

				ll_cantidadIntentos     = lcd_constantesDAO.findEnteroById(
					    ConstanteCommon.JOB_CREAR_TURNO_LIMITE_INTENTOS
					);

				lct_collection = DaoCreator.getTurnoDAO(ldm_manager)
						                       .findAllByCrearTurno(EstadoCommon.N, ll_cantidadIntentos);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("crearTurno", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lct_collection))
			crearTurno(lct_collection, as_remoteIp);
	}

	/**
	 * Crear turno.
	 *
	 * @param act_parametros de act parametros
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void crearTurno(Collection<Turno> act_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_CREAR_TURNO_BLOQUEO;
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

				clh_LOGGER.error("crearTurno", lb2be_e);

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
					loa_messageArgs[0]     = StringUtils.getString(ls_constant);

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_messageArgs);
				}
			}
			catch(B2BException lb2be_e)
			{
				ldm_processing.setRollbackOnly();

				clh_LOGGER.error("crearTurno", lb2be_e);

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
				DAOManager ldm_constantes;

				ldm_bitacora       = DaoManagerFactory.getDAOManager();
				ldm_constantes     = DaoManagerFactory.getDAOManager();

				try
				{
					ConstantesDAO lcd_constantes;
					String        ls_endpoint;

					lcd_constantes     = DaoCreator.getConstantesDAO(ldm_constantes);
					ls_endpoint        = lcd_constantes.findString(ConstanteCommon.JOB_CREAR_TURNO_ENDPOINT);

					if(CollectionUtils.isValidCollection(act_parametros))
					{
						ldm_bitacora.setAutoCommit(true);

						BitacoraProcesoDAO lbpd_bitacora;
						TurnoDAO           ltd_turnoDAO;

						lbpd_bitacora     = DaoCreator.getBitacoraProcesoDAO(ldm_bitacora);
						ltd_turnoDAO      = DaoCreator.getTurnoDAO(ldm_bitacora);

						for(Turno lt_iterador : act_parametros)
						{
							if(lt_iterador != null)
							{
								try
								{
									crearTurno(
									    lt_iterador, lbpd_bitacora, ltd_turnoDAO, ls_endpoint, ls_userId, as_remoteIp
									);
								}
								catch(B2BException lb2b_e)
								{
									clh_LOGGER.error("crearTurno", lb2b_e);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_bitacora.setRollbackOnly();
					ldm_constantes.setRollbackOnly();

					clh_LOGGER.error("crearTurno", lb2be_e);
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
			clh_LOGGER.error("crearTurno", lb2be_e);

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

					clh_LOGGER.error("crearTurno", lb2be_e);

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
	 * Crear turno.
	 *
	 * @param at_parametro de at parametro
	 * @param abpd_DAO de abpd DAO
	 * @param atd_turnoDAO
	 * @param as_endpoint de as endpoint
	 * @param as_userId de as user id
	 * @param as_ipRemota de as ip remota
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void crearTurno(
	    Turno at_parametro, BitacoraProcesoDAO abpd_DAO, TurnoDAO atd_turnoDAO, String as_endpoint, String as_userId,
	    String as_ipRemota
	)
	    throws B2BException
	{
		if(at_parametro != null)
		{
			DAOManager ldm_manager;
			String     ls_mensaje;
			long       ll_intentoActual;

			ldm_manager          = DaoManagerFactory.getDAOManager();
			ls_mensaje           = addMessage(MessagesKeys.PROCESO_EXITOSO);
			ll_intentoActual     = at_parametro.getIntentosFallidosEjecucionAutomatica();

			ll_intentoActual++;

			try
			{
				if(!StringUtils.isValidString(as_endpoint))
					throw new B2BException(ErrorKeys.ERROR_ENDPOINT_NO_VALIDO);

				{
					ConstantesDAO lcd_constantesDAO;
					boolean       lb_intentarEnvio;
					long          ll_tiempoEspera;
					long          ll_limiteIntentos;

					lcd_constantesDAO     = DaoCreator.getConstantesDAO(ldm_manager);
					lb_intentarEnvio      = true;
					ll_tiempoEspera       = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_CREAR_TURNO_TIEMPO_ESPERA_INTENTO_ENVIO
						);
					ll_limiteIntentos     = lcd_constantesDAO.findEnteroById(
						    ConstanteCommon.JOB_CREAR_TURNO_LIMITE_INTENTOS
						);

					while(lb_intentarEnvio && (ll_intentoActual <= ll_limiteIntentos))
					{
						try
						{
							at_parametro.setCrearTurno(EstadoCommon.S);
							at_parametro.setIdUsuarioModificacion(as_userId);
							at_parametro.setIpModificacion(as_ipRemota);

							atd_turnoDAO.updateCrearTurno(at_parametro);

							{
								Map<String, String> lmss_parametros;

								lmss_parametros = new LinkedHashMap<String, String>();

								String ls_idCirculo;
								String ls_nombreCirculo;
								String ls_nir;
								String ls_idTurno;
								String ls_matriculasConcatenadas;

								ls_idCirculo                  = at_parametro.getIdCirculo();
								ls_nombreCirculo              = at_parametro.getNombreCirculo();
								ls_nir                        = at_parametro.getNir();
								ls_idTurno                    = at_parametro.getIdTurno();
								ls_matriculasConcatenadas     = obtenerMatriculasBySolicitudCirculo(
									    at_parametro.getIdSolicitud(), at_parametro.getIdCirculo(), ldm_manager
									);

								if(
								    StringUtils.isValidString(ls_idCirculo)
									    && StringUtils.isValidString(ls_nombreCirculo)
									    && StringUtils.isValidString(ls_nir) && StringUtils.isValidString(ls_idTurno)
									    && StringUtils.isValidString(ls_matriculasConcatenadas)
								)
								{
									lmss_parametros.put(ParametrosOWCCCommon.CODIGO_ORIP, ls_idCirculo);
									lmss_parametros.put(ParametrosOWCCCommon.ORIP, ls_nombreCirculo);
									lmss_parametros.put(ParametrosOWCCCommon.NIR, ls_nir);
									lmss_parametros.put(ParametrosOWCCCommon.TURNO, ls_idTurno);
									lmss_parametros.put(ParametrosOWCCCommon.MATRICULA, ls_matriculasConcatenadas);

									ClienteCrearTurno.crearTurno(
									    SistemaOrigenCommon.CORE, lmss_parametros, as_endpoint
									);
								}
							}
						}
						catch(B2BException lb2be_e)
						{
							if((ll_intentoActual + 1) >= ll_limiteIntentos)
								throw lb2be_e;

							ll_intentoActual++;

							{
								Object lo_monitor;

								lo_monitor = new Object();

								synchronized(lo_monitor)
								{
									lo_monitor.wait(ll_tiempoEspera);
								}
							}
						}
					}
				}

				at_parametro.setFechaExitoEjecucionAutomatica(new Date());
			}
			catch(B2BException lb2be_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = getErrorMessage(lb2be_e);

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.CREAR_TURNO, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(at_parametro.getIdTurno())
				);

				throw lb2be_e;
			}
			catch(InterruptedException lie_e)
			{
				ldm_manager.setRollbackOnly();

				ls_mensaje = lie_e.getLocalizedMessage();

				escribirEnBitacoraProceso(
				    abpd_DAO, IdentificadoresCommon.CREAR_TURNO, ls_mensaje, as_userId, as_endpoint,
				    StringUtils.getString(at_parametro.getIdTurno())
				);

				Thread.currentThread().interrupt();

				throw new B2BException(ls_mensaje);
			}
			finally
			{
				ldm_manager.close();

				at_parametro.setIntentosFallidosEjecucionAutomatica(ll_intentoActual);
				at_parametro.setRespuestaEjecucionAutomatica(ls_mensaje);
				at_parametro.setIpModificacion(as_ipRemota);
				at_parametro.setIdUsuarioModificacion(as_userId);

				atd_turnoDAO.updateIntentoEnvios(at_parametro);
			}
		}
	}
}
