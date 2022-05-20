package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.Expediente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_EXPEDIENTE.
 *
 * @author Kevin Pulido
 */
public class ExpedienteDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL_ACTIVE = "SELECT * FROM SDB_PGN_EXPEDIENTE WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_EXPEDIENTE WHERE ID_EXPEDIENTE = ? AND ACTIVO = 'S'";

	/**
	 * Find all active.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Expediente> findAllActive()
	    throws B2BException
	{
		Collection<Expediente> lce_datos;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lce_datos     = new ArrayList<Expediente>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVE);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lce_datos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActive", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lce_datos))
			lce_datos = null;

		return lce_datos;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param de as param
	 * @return el valor de expediente
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Expediente findById(String as_param)
	    throws B2BException
	{
		Expediente le_return;
		le_return = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					le_return = getObjetFromResultSet(lrs_rs);
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

		return le_return;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Expediente getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Expediente le_datos;

		le_datos = new Expediente();

		le_datos.setIdExpediente(ars_rs.getString("ID_EXPEDIENTE"));
		le_datos.setNombre(ars_rs.getString("NOMBRE"));
		le_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, le_datos);

		return le_datos;
	}
}
