package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.IncAviAleMenNot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_INC_AVI_ALE_MEN_NOT.
 *
 * @author Gabriel Arias
 */
public class IncAviAleMenNotDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_ACTIVO = "SELECT * FROM SDB_PGN_INC_AVI_ALE_MEN_NOT WHERE ID_INC=? AND ESTADO='A'";

	/**
	 * Find by id.
	 *
	 * @param as_idInc the as id inc
	 * @return the inc avi ale men not
	 * @throws B2BException the b 2 B exception
	 */
	public IncAviAleMenNot findByIdActivo(String as_idInc)
	    throws B2BException
	{
		IncAviAleMenNot liaamn_return;

		liaamn_return = null;

		if(StringUtils.isValidString(as_idInc))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTIVO);

				lps_ps.setString(1, as_idInc);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					liaamn_return = getObjetFromResultSet(lrs_rs);
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

		return liaamn_return;
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private IncAviAleMenNot getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		IncAviAleMenNot lci_datos;

		lci_datos = new IncAviAleMenNot();

		lci_datos.setIdInc(ars_rs.getString("ID_INC"));
		lci_datos.setMensaje(ars_rs.getString("MENSAJE"));
		lci_datos.setObservacion(ars_rs.getString("OBSERVACION"));
		lci_datos.setIdPlantilla(ars_rs.getString("ID_PLANTILLA"));
		lci_datos.setTipo(ars_rs.getString("TIPO"));
		lci_datos.setProceso(ars_rs.getString("PROCESO"));
		lci_datos.setEstado(ars_rs.getString("ESTADO"));

		fillAuditoria(ars_rs, lci_datos);

		return lci_datos;
	}
}
