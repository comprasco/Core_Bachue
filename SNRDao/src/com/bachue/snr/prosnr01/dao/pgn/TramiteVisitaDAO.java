package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TRAMITE_VISITA
 *
 * @author Bryan Márquez
 */
public class TramiteVisitaDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TRAMITE_VISITA SET NOMBRE = ?, "
		+ "ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TRAMITE_VISITA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TRAMITE_VISITA (ID_TRAMITE_VISITA,"
		+ " NOMBRE,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION) VALUES (?,?,?,?,SYSTIMESTAMP)";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_TRAMITE_VISITA WHERE ACTIVO = 'S' ";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TRAMITE_VISITA WHERE ID_TRAMITE_VISITA = ? ";

	/**
	 * Find all activo.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteVisita> findAllActivo()
	    throws B2BException
	{
		Collection<TramiteVisita> lcae_cae;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcae_cae     = new ArrayList<TramiteVisita>(1);
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcae_cae.add(getObjetFromResultSet(lrs_rs));
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

		if(lcae_cae.isEmpty())
			lcae_cae = null;

		return lcae_cae;
	}

	/**
	 * Find by id.
	 *
	 * @param as_idTramiteVisita correspondiente al valor de as id tramite visita
	 * @return el valor de tramite visita
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramiteVisita findById(String as_idTramiteVisita)
	    throws B2BException
	{
		TramiteVisita     ltv_tv;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltv_tv     = new TramiteVisita();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_idTramiteVisita);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ltv_tv = getObjetFromResultSet(lrs_rs);
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

		return ltv_tv;
	}

	/**
	 * Insert.
	 *
	 * @param atv_tv correspondiente al valor de atv tv
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TramiteVisita atv_tv)
	    throws B2BException
	{
		if(atv_tv != null)
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
					lps_ps.setString(li_column++, atv_tv.getIdTramiteVisita());
					lps_ps.setString(li_column++, atv_tv.getNombre());
					lps_ps.setString(li_column++, atv_tv.getActivo());
					lps_ps.setString(li_column++, atv_tv.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atv_tv.getIpCreacion());
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
	public void update(TramiteVisita atv_tv)
	    throws B2BException
	{
		if(atv_tv != null)
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

				lps_ps.setString(li_column++, atv_tv.getIdTramiteVisita());
				lps_ps.setString(li_column++, atv_tv.getNombre());
				lps_ps.setString(li_column++, atv_tv.getActivo());
				lps_ps.setString(li_column++, atv_tv.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atv_tv.getIpModificacion());

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
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs correspondiente al valor de lrs rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private TramiteVisita getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TramiteVisita ltv_tramiteVisita;

		ltv_tramiteVisita = new TramiteVisita();

		ltv_tramiteVisita.setIdTramiteVisita(lrs_rs.getString("ID_TRAMITE_VISITA"));
		ltv_tramiteVisita.setNombre(lrs_rs.getString("NOMBRE"));
		ltv_tramiteVisita.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, ltv_tramiteVisita);

		return ltv_tramiteVisita;
	}
}
