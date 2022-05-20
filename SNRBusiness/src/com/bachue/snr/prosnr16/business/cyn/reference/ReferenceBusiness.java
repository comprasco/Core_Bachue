package com.bachue.snr.prosnr16.business.cyn.reference;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.business.BaseBusiness;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ReferenceBusiness.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class ReferenceBusiness extends BaseBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ReferenceBusiness.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/**
	 * Find constantes by id.
	 *
	 * @param as_parametro de as parametro
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		DAOManager ldm_manager;
		Constantes lc_datos;

		ldm_manager     = DaoManagerFactory.getDAOManagerCYN();
		lc_datos        = null;

		try
		{
			lc_datos = DaoCreator.getConstantesDAO(ldm_manager).findById(as_parametro);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findConstantesById", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lc_datos;
	}

	/**
	 * Obtener la propiedad caracter de una constante
	 * @param as_idConstante Codigo de la constante a buscar
	 * @return Propiedad caracter de la constante identificada con as_idConstante
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public synchronized String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		DAOManager ldm_manager;
		String     ls_constante;

		ldm_manager      = DaoManagerFactory.getDAOManagerCYN();
		ls_constante     = null;

		try
		{
			ls_constante = DaoCreator.getConstantesDAO(ldm_manager).findString(as_idConstante);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerCaracterConstante", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return ls_constante;
	}

	/**
	 * Obtener todos los ID_CONSTANTE que coincidan con un patrón y su el valor de la columna CARACTER concida sea
	 * igual a un valor
	 *
	 * @param as_IdLike Patrón sobre el cual se realizará búsqueda tipo like en en campo ID_CONSTANTE
	 * @param as_caracter Valor de coincidencia del campo CARACTER
	 * @return Listado de ID_CONSTANTE
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(
	    String as_IdLike, String as_caracter
	)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		DAOManager         ldm_manager;

		ldm_manager          = DaoManagerFactory.getDAOManagerCYN();
		lcs_idConstantes     = null;

		try
		{
			lcs_idConstantes = DaoCreator.getConstantesDAO(ldm_manager)
					                         .findAllIdsByIdLikeCaracter(as_IdLike, as_caracter);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerIdConstanesPorCaracterIdLikeCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcs_idConstantes;
	}

	/**
	 * Update caracter.
	 *
	 * @param as_id de as id
	 * @param as_caracter de as caracter
	 * @param as_userId de as user id
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public synchronized void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		DAOManager ldm_dm;

		ldm_dm = DaoManagerFactory.getDAOManagerCYN();

		try
		{
			DaoCreator.getConstantesDAO(ldm_dm).updateString(as_id, as_caracter, as_userId);
		}
		catch(B2BException lb2be_e)
		{
			ldm_dm.setRollbackOnly();

			clh_LOGGER.error("updateCaracter", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_dm.close();
		}
	}
}
