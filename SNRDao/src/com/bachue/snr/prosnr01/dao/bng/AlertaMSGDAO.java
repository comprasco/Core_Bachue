package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades AlertaMSGInactivacionDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 6/04/2020
 */
public class AlertaMSGDAO extends AuditoriaDao
{
	/** Constante cs_DELETE_BY_ID_ALERTA_TIERRAS. */
	private static final String cs_DELETE_BY_ID_ALERTA_TIERRAS = "DELETE FROM SBD_BNG_ALERTA_MSG WHERE ID_ALERTA_TIERRA = ?";

	/**
	 * Delete by id alerta tierras.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteByIdAlertaTierras(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_ALERTA_TIERRAS);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lps_ps.executeUpdate();
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteByIdAlertasTierras", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}
}
