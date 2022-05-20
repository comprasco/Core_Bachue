package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaRol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TRAMITE_VISITA_ROL
 *
 * @author Bryan Márquez
 */
public class TramiteVisitaRolDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TRAMITE_VISITA_ROL SET ID_ROL = ?, "
		+ " ID_TRAMITE_VISITA = ?, ID_TRAMITE_VISITA_TIPO = ?, ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TRAMITE_VISITA_TIPO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TRAMITE_VISITA_ROL (ID_TRAMITE_VISITA_ROL,"
		+ " ID_ROL, ID_TRAMITE_VISITA, ID_TRAMITE_VISITA_TIPO, ID_TRAMITE_VISITA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL_ACTIVO_BY_ID_ROL_ID_TRAMITE_VISITA. */
	private static final String cs_FIND_ALL_ACTIVO_BY_ID_ROL_ID_TRAMITE_VISITA = "SELECT * FROM SDB_PGN_TRAMITE_VISITA_ROL WHERE ACTIVO = 'S' AND ID_ROL = ? AND ID_TRAMITE_VISITA = ?";

	/**
	 * Find all activo by id rol.
	 *
	 * @param as_idRol de as id rol
	 * @param as_idTramiteVisita de as id tramite visita
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteVisitaRol> findAllActivoByIdRolIdTramiteVisita(String as_idRol, String as_idTramiteVisita)
	    throws B2BException
	{
		Collection<TramiteVisitaRol> lcae_tvt;

		lcae_tvt = null;

		if(StringUtils.isValidString(as_idRol) && StringUtils.isValidString(as_idTramiteVisita))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO_BY_ID_ROL_ID_TRAMITE_VISITA);

				lps_ps.setString(1, as_idRol);
				lps_ps.setString(2, as_idTramiteVisita);

				lrs_rs       = lps_ps.executeQuery();
				lcae_tvt     = new ArrayList<TramiteVisitaRol>(1);

				while(lrs_rs.next())
					lcae_tvt.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllActivoByIdRol", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcae_tvt;
	}

	/**
	 * Insert.
	 *
	 * @param atv_tv correspondiente al valor de atv tvt
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(TramiteVisitaRol atvr_tvr)
	    throws B2BException
	{
		if(atvr_tvr != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				{
					lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisitaRol());
					lps_ps.setString(li_column++, atvr_tvr.getIdRol());
					lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisita());
					lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisitaTipo());
					lps_ps.setString(li_column++, atvr_tvr.getActivo());
					lps_ps.setString(li_column++, atvr_tvr.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atvr_tvr.getIpCreacion());
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
	 * @param atv_tvt correspondiente al valor de atv tvt
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(TramiteVisitaRol atvr_tvr)
	    throws B2BException
	{
		if(atvr_tvr != null)
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

				lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisitaRol());
				lps_ps.setString(li_column++, atvr_tvr.getIdRol());
				lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisita());
				lps_ps.setString(li_column++, atvr_tvr.getIdTramiteVisitaTipo());
				lps_ps.setString(li_column++, atvr_tvr.getActivo());
				lps_ps.setString(li_column++, atvr_tvr.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atvr_tvr.getIpModificacion());

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
	private TramiteVisitaRol getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TramiteVisitaRol ltvr_tramiteVisitaRol;

		ltvr_tramiteVisitaRol = new TramiteVisitaRol();

		ltvr_tramiteVisitaRol.setIdTramiteVisitaRol(lrs_rs.getString("ID_TRAMITE_VISITA_ROL"));
		ltvr_tramiteVisitaRol.setIdRol(lrs_rs.getString("ID_ROL"));
		ltvr_tramiteVisitaRol.setIdTramiteVisita(lrs_rs.getString("ID_TRAMITE_VISITA"));
		ltvr_tramiteVisitaRol.setIdTramiteVisitaTipo(lrs_rs.getString("ID_TRAMITE_VISITA_TIPO"));
		ltvr_tramiteVisitaRol.setActivo(lrs_rs.getString("ACTIVO"));

		fillAuditoria(lrs_rs, ltvr_tramiteVisitaRol);

		return ltvr_tramiteVisitaRol;
	}
}
