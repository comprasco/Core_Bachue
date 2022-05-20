package com.bachue.snr.prosnr01.business.gobernaciones;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.BaseBusiness;

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

import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;
import java.util.Date;


/**
 * Permite el manejo de logica de negocio y manejo de transacciones con la base
 * de datos
 *
 * @author Luis Chacón
 *
 */
public class GobernacionesBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GobernacionesBusiness.class);

	/**
	 * Bloquea el JOB e invoca el proceso de validación de pago del impuesto
	 *
	 * @param acth_parametros
	 *            Colección de casos a validar
	 * @param as_remoteIp
	 *            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void findGobernaciones(Collection<TurnoHistoria> acth_parametros, String as_remoteIp)
	    throws B2BException
	{
		boolean lb_alreadyProcessing;
		String  ls_constant;
		String  ls_userId;

		ls_constant     = ConstanteCommon.JOB_GOBERNACIONES_BLOQUEO;
		ls_userId       = "JOB Gobernaciones";

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

				clh_LOGGER.error("findGobernaciones", lb2be_e);

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
				DAOManager ldm_manager;

				ldm_manager = DaoManagerFactory.getDAOManager();

				try
				{
					if(CollectionUtils.isValidCollection(acth_parametros))
					{
						String ls_usuarioProceso;

						ls_usuarioProceso = getSystemUser(ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS, ldm_manager);

						ldm_manager.setAutoCommit(true);

						if(StringUtils.isValidString(ls_usuarioProceso))
						{
							BitacoraProcesoDAO lbpd_bitacora;

							lbpd_bitacora = DaoCreator.getBitacoraProcesoDAO(ldm_manager);

							for(TurnoHistoria lth_iterador : acth_parametros)
							{
								if(lth_iterador != null)
								{
									String ls_mensaje;

									ls_mensaje = addMessage(MessagesKeys.PROCESO_EXITOSO);

									try
									{
										validarGobernacionesImpuesto(
										    lbpd_bitacora, lth_iterador, as_remoteIp, ls_usuarioProceso
										);

										lth_iterador.setFechaExitoEjecucionAutomatica(new Date());
									}
									catch(B2BException lb2be_e)
									{
										clh_LOGGER.error("findGobernaciones", lb2be_e);

										ls_mensaje = getErrorMessage(lb2be_e);
									}

									actualizarIntentoEnvio(
									    lth_iterador, ls_mensaje, ls_userId, as_remoteIp, ldm_manager
									);
								}
							}
						}
					}
				}
				catch(B2BException lb2be_e)
				{
					ldm_manager.setRollbackOnly();

					clh_LOGGER.error("findGobernaciones", lb2be_e);
				}
				finally
				{
					ldm_manager.close();
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findGobernaciones", lb2be_e);

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

					clh_LOGGER.error("findGobernaciones", lb2be_e);

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
	 * Busca los casos en estado aut para que el job valide el pago del impuesto
	 *
	 * @param as_remoteIp Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void gobernacionesImpuestos(String as_remoteIp)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_turnosGobernaciones;
		DAOManager                ldm_manager;

		lcth_turnosGobernaciones     = null;
		ldm_manager                  = DaoManagerFactory.getDAOManager();

		try
		{
			lcth_turnosGobernaciones = obtenerTurnosJob(
				    ConstanteCommon.JOB_GOBERNACIONES_WS_INVOKE, ConstanteCommon.JOB_GOBERNACIONES_LIMITE_INTENTOS,
				    EtapaCommon.ID_ETAPA_11, ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("gobernacionesImpuestos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		if(CollectionUtils.isValidCollection(lcth_turnosGobernaciones))
			findGobernaciones(lcth_turnosGobernaciones, as_remoteIp);
	}

	/**
	 * Valida si la etapa actual ya pago el impuesto por cada uno de los actos de cada circulo
	 *
	 * @param ath_turnoHistoria
	 *            Objeto contenedor del la infromación del tramite en proceso
	 * @param as_remoteIp
	 *            Dirección IP del cliente donde se ejecuta la acción
	 * @throws B2BException
	 */
	public synchronized void validarGobernacionesImpuesto(
	    BitacoraProcesoDAO abpd_bitacoraProcesoDAO, TurnoHistoria ath_turnoHistoria, String as_remoteIp,
	    String ls_usuarioProceso
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(ath_turnoHistoria == null)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRO_ID_TURNO_HISTORIA);

			Collection<Acto> lca_actos;
			boolean          lb_actoImpuestoRegistroSinPagar;

			lb_actoImpuestoRegistroSinPagar     = false;
			lca_actos                           = DaoCreator.getActoDAO(ldm_manager)
					                                            .findByIdSolicitudImpuestoRegistro(
					    ath_turnoHistoria.getIdSolicitud()
					);

			if(CollectionUtils.isValidCollection(lca_actos))
			{
				for(Acto la_actoTMP : lca_actos)
				{
					if(la_actoTMP != null)
					{
						String ls_numeroBolestaFiscal;
						Date   ld_fechaPagoImpuesto;

						ls_numeroBolestaFiscal     = la_actoTMP.getNumeroBoletaFiscal();
						ld_fechaPagoImpuesto       = la_actoTMP.getFechaPagoImpuesto();

						if(!StringUtils.isValidString(ls_numeroBolestaFiscal) && (ld_fechaPagoImpuesto == null))
							lb_actoImpuestoRegistroSinPagar = true;
					}
				}
			}

			if(lb_actoImpuestoRegistroSinPagar)
			{
				ath_turnoHistoria.setInvocarProcedimientoGobernaciones(true);
				terminarTurnoHistoriaYCrearEtapa(
				    ath_turnoHistoria, ldm_manager,
				    new MotivoTramite(EtapaCommon.ID_ETAPA_11, MotivoTramiteCommon.INICIO_TRAMITE_CALIFICACION),
				    ls_usuarioProceso, as_remoteIp, EstadoCommon.AUTOMATICA
				);
			}
			else
			{
				ath_turnoHistoria.setObservaciones(
				    "Etapa terminada automáticamente por No requerir pago de impuesto de registro"
				);
				ath_turnoHistoria.setInvocarProcedimientoGobernaciones(true);

				terminarTurnoHistoriaYCrearEtapa(
				    ath_turnoHistoria, ldm_manager,
				    new MotivoTramite(EtapaCommon.ID_ETAPA_11, MotivoTramiteCommon.PENDIENTE_PAGO_IMPUESTO_REGISTRO),
				    ls_usuarioProceso, as_remoteIp, EstadoCommon.AUTOMATICA
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			escribirEnBitacoraProceso(
			    abpd_bitacoraProcesoDAO, IdentificadoresCommon.GOBERNACIONES, lb2be_e.getMessage(), ls_usuarioProceso,
			    as_remoteIp,
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
