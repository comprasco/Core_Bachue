package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TRAMITE_VISITA_TIPO
 *
 * @author Bryan Márquez
 */
public class TramiteVisitaTipoDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TRAMITE_VISITA_TIPO SET NOMBRE = ?, "
		+ " ID_TRAMITE_VISITA = ?, ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_TRAMITE_VISITA_TIPO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TRAMITE_VISITA_TIPO (ID_TRAMITE_VISITA_TIPO,"
		+ " NOMBRE,ID_TRAMITE_VISITA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP)";

	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ACTIVO_BY_ID = "SELECT * FROM SDB_PGN_TRAMITE_VISITA_TIPO WHERE ACTIVO = 'S' AND ID_TRAMITE_VISITA_TIPO = ?";

	/**
	 * Find activo by id.
	 *
	 * @param as_idTipoTramiteVisita de as id tipo tramite visita
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramiteVisitaTipo findActivoById(String as_idTipoTramiteVisita)
	    throws B2BException
	{
		TramiteVisitaTipo lcae_tvt;

		lcae_tvt = null;

		if(StringUtils.isValidString(as_idTipoTramiteVisita))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ACTIVO_BY_ID);

				lps_ps.setString(1, as_idTipoTramiteVisita);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcae_tvt = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findActivoById", lse_e);

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
	public void insert(TramiteVisitaTipo atv_tvt)
	    throws B2BException
	{
		if(atv_tvt != null)
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
					lps_ps.setString(li_column++, atv_tvt.getIdTramiteVisitaTipo());
					lps_ps.setString(li_column++, atv_tvt.getNombre());
					lps_ps.setString(li_column++, atv_tvt.getIdTramiteVisita());
					lps_ps.setString(li_column++, atv_tvt.getActivo());
					lps_ps.setString(li_column++, atv_tvt.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atv_tvt.getIpCreacion());
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
	public void update(TramiteVisitaTipo atv_tvt)
	    throws B2BException
	{
		if(atv_tvt != null)
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

				lps_ps.setString(li_column++, atv_tvt.getIdTramiteVisitaTipo());
				lps_ps.setString(li_column++, atv_tvt.getNombre());
				lps_ps.setString(li_column++, atv_tvt.getIdTramiteVisita());
				lps_ps.setString(li_column++, atv_tvt.getActivo());
				lps_ps.setString(li_column++, atv_tvt.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atv_tvt.getIpModificacion());

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
	private TramiteVisitaTipo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TramiteVisitaTipo ltvt_tramiteVisitaTipo;

		ltvt_tramiteVisitaTipo = new TramiteVisitaTipo();

		ltvt_tramiteVisitaTipo.setIdTramiteVisitaTipo(lrs_rs.getString("ID_TRAMITE_VISITA_TIPO"));
		ltvt_tramiteVisitaTipo.setNombre(lrs_rs.getString("NOMBRE"));
		ltvt_tramiteVisitaTipo.setIdTramiteVisita(lrs_rs.getString("ID_TRAMITE_VISITA"));
		ltvt_tramiteVisitaTipo.setActivo(lrs_rs.getString("ACTIVO"));
		ltvt_tramiteVisitaTipo.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ltvt_tramiteVisitaTipo.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ltvt_tramiteVisitaTipo.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ltvt_tramiteVisitaTipo.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ltvt_tramiteVisitaTipo.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ltvt_tramiteVisitaTipo.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ltvt_tramiteVisitaTipo;
	}
}
