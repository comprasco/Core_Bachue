package com.bachue.snr.prosnr01.business.consulta.reparto.calificacion;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.consulta.reparto.calificacion.ConsultaRepartoCalificacion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import java.util.Collection;


/**
 * Clase para el manejo del negocio de consulta reparto calificacion para realizar.
 *
 * @author Sebastian Tafur
 */
public class ConsultaRepartoCalificacionBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaRepartoCalificacionBusiness.class);

	/**
	 * Método para realizar transaccciones con la base de datos para consultar por ORIP.
	 *
	 * @param as_usuario String con el usuario en sesión
	 * @return devuelve el valor de Collection de usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<Usuario> findByORIP(String as_usuario)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		Collection<Usuario> lcu_usuarios;

		ldm_manager      = DaoManagerFactory.getDAOManager();
		lcu_usuarios     = null;

		try
		{
			String ls_idUsuario;
			ls_idUsuario = as_usuario;

			if(StringUtils.isValidString(ls_idUsuario))
				lcu_usuarios = DaoCreator.getConsultaRepartoCalificacionDAO(ldm_manager).findByORIP(ls_idUsuario);
			else
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByORIP", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lcu_usuarios;
	}

	/**
	 * Método para realizar transaccciones con la base de datos para consultar por el usuario.
	 *
	 * @param au_usuario Objeto usuario para extraer la información del id usuario para la consulta
	 * @return devuelve el valor de Collection de ConsultaRepartoCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized Collection<ConsultaRepartoCalificacion> findByUser(Usuario au_usuario)
	    throws B2BException
	{
		DAOManager                              ldm_manager;
		Collection<ConsultaRepartoCalificacion> lccrc_consulta;

		ldm_manager        = DaoManagerFactory.getDAOManager();
		lccrc_consulta     = null;

		try
		{
			Usuario lu_usuario;
			String  ls_idUsuario;

			lu_usuario       = au_usuario;
			ls_idUsuario     = lu_usuario.getIdUsuario();

			if(StringUtils.isValidString(ls_idUsuario))
				lccrc_consulta = DaoCreator.getConsultaRepartoCalificacionDAO(ldm_manager).findByUser(lu_usuario);
			else
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findByUser", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccrc_consulta;
	}
}
