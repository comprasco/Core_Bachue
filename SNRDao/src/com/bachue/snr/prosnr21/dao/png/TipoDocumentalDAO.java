package com.bachue.snr.prosnr21.dao.png;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;
import com.bachue.snr.prosnr21.model.pgn.TipoDocumental;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_DOCUMENTAL.
 *
 * @author Kevin Pulido
 */
public class TipoDocumentalDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL_ACTIVE = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE ID_TIPO_DOCUMENTAL = ? AND ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_EXPEDIENTE = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL WHERE ID_EXPEDIENTE = ? AND ACTIVO = 'S'";

	/**
	 * Find all active.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> findAllActive()
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_datos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lctd_datos     = new ArrayList<TipoDocumental>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVE);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lctd_datos))
			lctd_datos = null;

		return lctd_datos;
	}

	/**
	 * Find by expediente.
	 *
	 * @param as_param de as param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> findByExpediente(String as_param)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_datos;
		lctd_datos = new ArrayList<TipoDocumental>(0);

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_EXPEDIENTE);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctd_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByExpediente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}

			if(!CollectionUtils.isValidCollection(lctd_datos))
				lctd_datos = null;
		}

		return lctd_datos;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param de as param
	 * @return el valor de tipo documental
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoDocumental findById(String as_param)
	    throws B2BException
	{
		TipoDocumental ltd_return;
		ltd_return = null;

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
					ltd_return = getObjetFromResultSet(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TipoDocumental getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoDocumental ltd_datos;

		ltd_datos = new TipoDocumental();

		ltd_datos.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		ltd_datos.setNombre(ars_rs.getString("NOMBRE"));
		ltd_datos.setIdExpediente(ars_rs.getString("ID_EXPEDIENTE"));
		ltd_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ltd_datos);

		return ltd_datos;
	}
}
