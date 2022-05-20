package com.bachue.snr.prosnr01.business.consulta.recibo.liquidacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.integracion.cliente.npa.registrarAnulacion.ClienteRegistrarAnulacion;

import com.bachue.snr.prosnr01.business.registro.RegistroBusiness;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoLiquidacionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;
import com.bachue.snr.prosnr01.dao.acc.LiquidacionDAO;

import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.util.Collection;


/**
 * Clase para el manejo del negocio para la consulta recibo liquidacion.
 *
 * @author Gabriel Arias
 */
public class ConsultaReciboLiquidacionBusiness extends RegistroBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaReciboLiquidacionBusiness.class);

	/**
	 * Método encargado de validar si es posible eliminar la liquidación.
	 *
	 * @param al_liquidacion Objeto que contiene la información de la liquidación que se va a eliminar.
	 * @throws B2BException
	 */
	public synchronized void confirmarEliminarLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_remoteIp
	)
	    throws B2BException
	{
		DAOManager ldm_manager;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			confirmarEliminarLiquidacion(al_liquidacion, as_userId, as_remoteIp, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarEliminarLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de consultar la información de la bandeja.
	 *
	 * @param as_numeroReferencia Variable que contiene el numero de referencia.
	 * @return Colección que contiene la información consultada para la bandeja.
	 * @throws B2BException
	 */
	public synchronized Collection<Liquidacion> consultarBandejaRecibos(String as_numeroReferencia)
	    throws B2BException
	{
		Collection<Liquidacion> lcl_return;
		DAOManager              ldm_manager;

		lcl_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcl_return = DaoCreator.getAccLiquidacionDAO(ldm_manager).findNoPagada(as_numeroReferencia);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("consultarBandejaRecibos", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcl_return;
	}

	/**
	 * Método encargado de finalizar el proceso.
	 *
	 * @param al_liquidacion Objeto que contiene la información del proceso.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException
	 */
	public synchronized void terminarProcesoRecibo(Liquidacion al_liquidacion, String as_userId, String as_remoteIp)
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
						Registro lr_parametros;
						String   ls_condicion;

						lr_parametros     = new Registro();
						ls_condicion      = al_liquidacion.getCondicion();

						if(!StringUtils.isValidString(ls_condicion))
							throw new B2BException(ErrorKeys.ERROR_CONDICION_LIQUIDACION);

						lr_parametros.setIdUsuarioCreacion(as_userId);
						lr_parametros.setSolicitud(ls_solicitud);
						lr_parametros.setIdCondicion(ls_condicion);
						lr_parametros.setTipoRecibo(IdentificadoresCommon.RECIBO_LIQUIDACION);

						lr_parametros = generarReciboLiquidacion(
							    lr_parametros, true, as_userId, null, null, ldm_manager
							);

						if(lr_parametros != null)
						{
							byte[] lba_pdf;

							lba_pdf = lr_parametros.getPdf();

							if(lba_pdf == null)
								throw new B2BException(ErrorKeys.ERROR_GENERANDO_RECIBO_LIQUIDACION);
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_NO_ID_SOLICITUD);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("terminarProcesoRecibo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}
	}

	/**
	 * Método encargado de validar si es posible eliminar la liquidación.
	 *
	 * @param al_liquidacion Objeto que contiene la información de la liquidación que se va a eliminar.
	 * @return boolean que indica si es posible eliminar la liquidación.
	 * @throws B2BException
	 */
	public synchronized boolean validarEliminarLiquidacion(Liquidacion al_liquidacion)
	    throws B2BException
	{
		boolean    lcl_return;
		DAOManager ldm_manager;

		lcl_return      = false;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			lcl_return = validarEliminarLiquidacion(al_liquidacion, ldm_manager);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("validarEliminarLiquidacion", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcl_return;
	}

	/**
	 * Método encargado de validar si es posible eliminar la liquidación.
	 *
	 * @param al_liquidacion Objeto que contiene la información de la liquidación que se va a eliminar.
	 * @param as_userId
	 * @param as_remoteIp
	 * @param adm_manager
	 * @throws B2BException
	 */
	protected synchronized void confirmarEliminarLiquidacion(
	    Liquidacion al_liquidacion, String as_userId, String as_remoteIp, DAOManager adm_manager
	)
	    throws B2BException
	{
		try
		{
			if(validarEliminarLiquidacion(al_liquidacion, adm_manager))
			{
				Constantes lc_constante;
				String     ls_constante;

				ls_constante     = ConstanteCommon.JOB_ENVIO_NOTIFICACION_PAGO_ENDPOINT;
				lc_constante     = DaoCreator.getConstantesDAO(adm_manager).findById(ls_constante);

				if(lc_constante != null)
				{
					String ls_numeroReferencia;

					ls_numeroReferencia = al_liquidacion.getNumeroReferencia();

					if(StringUtils.isValidString(ls_numeroReferencia))
					{
						LiquidacionDAO ll_DAO;

						ll_DAO             = DaoCreator.getAccLiquidacionDAO(adm_manager);
						al_liquidacion     = ll_DAO.findByNumeroReferencia(ls_numeroReferencia);

						if(al_liquidacion != null)
						{
							al_liquidacion.setIdUsuarioModificacion(as_userId);
							al_liquidacion.setIpModificacion(as_remoteIp);
							al_liquidacion.setIdTipoEstadoLiquidacion(EstadoLiquidacionCommon.ANULADO);

							ll_DAO.insertOrUpdate(al_liquidacion, false);
						}
						else
							throw new B2BException(ErrorKeys.ERROR_NUMERO_REFERENCIA);

						ClienteRegistrarAnulacion.registrarAnulacion(ls_numeroReferencia, lc_constante.getCaracter());
					}
					else
						throw new B2BException(ErrorKeys.ERROR_NUMERO_REFERENCIA);
				}
				else
				{
					Object[] loa_arg;

					loa_arg        = new String[1];
					loa_arg[0]     = ls_constante;

					throw new B2BException(ErrorKeys.ERROR_SIN_CONSTANTE, loa_arg);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarEliminarLiquidacion", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Método de consulta con la base de datos para validar el estado de liquidación
	 * @param al_liquidacion con los parametros de búsqueda 
	 * @param adm_manager con el manager de la transacción
	 * @return de tipo boolean con la respuesta de liquidación
	 * @throws B2BException
	 */
	private synchronized boolean validarEliminarLiquidacion(Liquidacion al_liquidacion, DAOManager adm_manager)
	    throws B2BException
	{
		boolean lcl_return;

		lcl_return = false;

		try
		{
			if(al_liquidacion != null)
			{
				Liquidacion ll_liquidacion;

				ll_liquidacion = DaoCreator.getAccLiquidacionDAO(adm_manager).findById(al_liquidacion, false);

				if(ll_liquidacion != null)
				{
					String ls_estado;

					ls_estado      = ll_liquidacion.getIdTipoEstadoLiquidacion();
					lcl_return     = StringUtils.isValidString(ls_estado)
							&& ls_estado.equalsIgnoreCase(EstadoLiquidacionCommon.LIQUIDADO);

					if(!lcl_return)
						throw new B2BException(ErrorKeys.ERROR_ELIMINAR_RECIBO_LIQUIDACION);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarEliminarLiquidacion", lb2be_e);

			throw lb2be_e;
		}

		return lcl_return;
	}
}
