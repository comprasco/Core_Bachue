package com.bachue.snr.prosnr01.business.consulta.estado.predio;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.firma.FirmaMasivaBusiness;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;


/**
 * Clase para el manejo del negocio para la consulta del predio.
 *
 * @author Gabriel Arias
 */
public class ConsultaEstadoPredioBusiness
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaEstadoPredioBusiness.class);

	/** Propiedad ifmb business. */
	private FirmaMasivaBusiness ifmb_business;

	/**
	 * Método de transacciones con la base de datos para encontrar el estado del predio.
	 *
	 * @param asm_parametros objeto del cual se extrae el ID_MATRICULA y el ID_CIRCULO para encontrar los datos en la base de datos
	 * @param as_usuario usuario en sesión
	 * @param as_remoteIp Variable que contiene la ip desde la cual se realiza la acción.
	 * @return devuelve el valor de byte[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public synchronized byte[] findEstado(SolicitudMatricula asm_parametros, String as_usuario, String as_remoteIp)
	    throws B2BException
	{
		DAOManager         ldm_manager;
		SolicitudMatricula lsm_solicitudMatricula;
		byte[]             lba_return;

		ldm_manager                = DaoManagerFactory.getDAOManager();
		lsm_solicitudMatricula     = null;
		lba_return                 = null;

		try
		{
			String ls_ic;
			long   ls_im;

			ls_ic     = asm_parametros.getIdCirculo();
			ls_im     = asm_parametros.getIdMatricula();

			if(StringUtils.isValidString(ls_ic) && (ls_im > 0))
			{
				lsm_solicitudMatricula = DaoCreator.getConsultaEstadoPredioDAO(ldm_manager).findEstado(asm_parametros);

				if(lsm_solicitudMatricula != null)
					lba_return = getFirmaMasivaBusiness()
							             .generarCertificadoTradicionLibertad(
							    lsm_solicitudMatricula, IdentificadoresCommon.PREDIO, false, ldm_manager, as_usuario,
							    as_remoteIp
							);

				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
			}

			else
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("findEstado", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Retorna el valor de firma masiva business.
	 *
	 * @return el valor de firma masiva business
	 */
	private FirmaMasivaBusiness getFirmaMasivaBusiness()
	{
		if(ifmb_business == null)
			ifmb_business = new FirmaMasivaBusiness();

		return ifmb_business;
	}
}
