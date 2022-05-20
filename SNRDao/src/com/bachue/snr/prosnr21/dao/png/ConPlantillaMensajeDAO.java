package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ConPlantillaMensaje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_PLANTILLA_MENSAJE .
 *
 * @author Gabriel Arias
 */
public class ConPlantillaMensajeDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_CON_PLANTILLA_MENSAJE WHERE ID_PLANTILLA=?";

	/**
	 * Find by id.
	 *
	 * @param as_idPlantilla the as id plantilla
	 * @return the con plantilla mensaje
	 * @throws B2BException the b 2 B exception
	 */
	public ConPlantillaMensaje findById(String as_idPlantilla)
	    throws B2BException
	{
		ConPlantillaMensaje lcpm_return;

		lcpm_return = null;

		if(StringUtils.isValidString(as_idPlantilla))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idPlantilla);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcpm_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcpm_return;
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private ConPlantillaMensaje getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConPlantillaMensaje lcpm_datos;

		lcpm_datos = new ConPlantillaMensaje();

		lcpm_datos.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		lcpm_datos.setAsunto(ars_rs.getString("ASUNTO"));
		lcpm_datos.setCuerpo(ars_rs.getString("CUERPO"));
		lcpm_datos.setComentario(ars_rs.getString("COMENTARIO"));
		lcpm_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lcpm_datos);

		return lcpm_datos;
	}
}
