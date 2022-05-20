package com.bachue.snr.prosnr02.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr02.model.pgn.TopologiaRegla;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_TOPOLOGIA_REGLA.
 *
 * @author  Juan Sebastian Gómez
 */
public class TopologiaReglaDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TOPOLOGIA_REGLA WHERE ACTIVO = 'S' ORDER BY DESCRIPCION ASC";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TopologiaRegla> findAll()
	    throws B2BException
	{
		Collection<TopologiaRegla> lctr_topologias;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lctr_topologias     = null;
		lps_ps              = null;
		lrs_rs              = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs              = lps_ps.executeQuery();
			lctr_topologias     = new ArrayList<TopologiaRegla>(1);

			while(lrs_rs.next())
				lctr_topologias.add(getObjectFromResultSet(lrs_rs));
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

		return lctr_topologias;
	}

	/**
	 * Retorna Objeto o variable de valor object from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TopologiaRegla getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TopologiaRegla ltr_regla;

		ltr_regla = new TopologiaRegla();

		ltr_regla.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ltr_regla.setIdTopologiaRegla(ars_rs.getString("ID_TOPOLOGIA_REGLA"));
		ltr_regla.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ltr_regla);

		return ltr_regla;
	}
}
