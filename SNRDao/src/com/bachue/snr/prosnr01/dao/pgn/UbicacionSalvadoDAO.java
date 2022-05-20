package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.UbicacionSalvado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_UBICACION_SALVADO.
 *
 * @author Bryan Márquez
 */
public class UbicacionSalvadoDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_UBICACION_SALVADO SET NOMBRE = ?, "
		+ "ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_UBICACION_GUARDADO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_UBICACION_SALVADO (ID_UBICACION_SALVADO,"
		+ " NOMBRE,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION) VALUES (?,?,?,?,SYSTIMESTAMP)";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_UBICACION_SALVADO WHERE ACTIVO = 'S' ";

	/** Constante cs_FIND_BY_ID_ACTIVO. */
	private static final String cs_FIND_BY_ID_ACTIVO = "SELECT * FROM SDB_PGN_UBICACION_SALVADO WHERE ACTIVO = 'S' AND ID_UBICACION_SALVADO = ?";

	/**
	 * Find all activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UbicacionSalvado> findAllActivo()
	    throws B2BException
	{
		Collection<UbicacionSalvado> lcus_cus;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcus_cus     = new ArrayList<UbicacionSalvado>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcus_cus.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcus_cus.isEmpty())
			lcus_cus = null;

		return lcus_cus;
	}

	/**
	 * Find by id activo.
	 *
	 * @param as_idUbicacionSalvado correspondiente al valor de as id ubicacion salvado
	 * @return el valor de ubicacion salvado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UbicacionSalvado findByIdActivo(String as_idUbicacionSalvado)
	    throws B2BException
	{
		UbicacionSalvado lus_ubicacionSalvado;

		lus_ubicacionSalvado = null;

		if(StringUtils.isValidString(as_idUbicacionSalvado))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ACTIVO);
				lps_ps.setString(1, as_idUbicacionSalvado);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lus_ubicacionSalvado = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lus_ubicacionSalvado;
	}

	/**
	 * Insert.
	 *
	 * @param aus_us correspondiente al valor de aus us
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(UbicacionSalvado aus_us)
	    throws B2BException
	{
		if(aus_us != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT);

				{
					lps_ps.setString(li_column++, aus_us.getIdUbicacionSalvado());
					lps_ps.setString(li_column++, aus_us.getNombre());
					lps_ps.setString(li_column++, aus_us.getActivo());
					lps_ps.setString(li_column++, aus_us.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aus_us.getIpCreacion());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param atv_tv correspondiente al valor de atv tv
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(UbicacionSalvado aus_us)
	    throws B2BException
	{
		if(aus_us != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, aus_us.getIdUbicacionSalvado());
				lps_ps.setString(li_column++, aus_us.getNombre());
				lps_ps.setString(li_column++, aus_us.getActivo());
				lps_ps.setString(li_column++, aus_us.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aus_us.getIpModificacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param lrs_rs correspondiente al valor de lrs rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private UbicacionSalvado getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		UbicacionSalvado lug_ubicacionSalvado;

		lug_ubicacionSalvado = new UbicacionSalvado();

		lug_ubicacionSalvado.setIdUbicacionSalvado(lrs_rs.getString("ID_UBICACION_SALVADO"));
		lug_ubicacionSalvado.setNombre(lrs_rs.getString("NOMBRE"));
		lug_ubicacionSalvado.setActivo(lrs_rs.getString("ACTIVO"));
		fillAuditoria(lrs_rs, lug_ubicacionSalvado);

		return lug_ubicacionSalvado;
	}
}
