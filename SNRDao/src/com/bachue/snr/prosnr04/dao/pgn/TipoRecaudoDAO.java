package com.bachue.snr.prosnr04.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.TipoRecaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_TIPO_RECAUDO.
 *
 * @author Luis Chacón
 */
public class TipoRecaudoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_RECAUDO";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_RECAUDO WHERE ID_TIPO_RECAUDO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_RECAUDO SET NOMBRE_TIPO_RECAUDO = ?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_RECAUDO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_RECAUDO (ID_TIPO_RECAUDO, NOMBRE_TIPO_RECAUDO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo que busca todos los tipos de recaudo
	 *
	 * @return Collection de objetos TipoRecaudo
	 * @throws B2BException de b 2 B exception
	 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
	 */
	public Collection<TipoRecaudo> findAll()
	    throws B2BException
	{
		Collection<TipoRecaudo> lcta_ta;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcta_ta     = new ArrayList<TipoRecaudo>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcta_ta.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcta_ta))
			lcta_ta = null;

		return lcta_ta;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idTipoRecaudo específico.
	 *
	 * @param atr_param id correspondiente al tipo de recaudo
	 * @return el valor de tipo recaudo
	 * @throws B2BException si hay algun error
	 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
	 */
	public TipoRecaudo findById(TipoRecaudo atr_param)
	    throws B2BException
	{
		return (atr_param != null) ? findById(atr_param.getIdTipoRecaudo()) : null;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idTipoRecaudo específico.
	 *
	 * @param as_param id correspondiente al tipo de recaudo
	 * @return el valor de tipo recaudo
	 * @throws B2BException si hay algun error
	 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
	 */
	public TipoRecaudo findById(String as_param)
	    throws B2BException
	{
		TipoRecaudo       ltr_tt;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltr_tt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltr_tt = getObjetFromResultSet(lrs_rs);
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

		return ltr_tt;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param ata_param objeto TipoRecaudo
	 * @throws B2BException si hay algun error
	 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
	 */
	public void insert(TipoRecaudo ata_param)
	    throws B2BException
	{
		if(ata_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, ata_param.getIdTipoRecaudo());
				lps_ps.setString(li_column++, ata_param.getNombreTipoRecaudo());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ata_param.getIpCreacion());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_TIPO_RECAUDO.
	 *
	 * @param ata_param objeto TipoRecaudo
	 * @throws B2BException si hay algun error
	 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
	 */
	public void update(TipoRecaudo ata_param)
	    throws B2BException
	{
		if(ata_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, ata_param.getNombreTipoRecaudo());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ata_param.getIpModificacion());
				lps_ps.setString(li_column++, ata_param.getIdTipoRecaudo());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

/**
 * Retorna una nueva instancia del objeto TipoRecuado a partir de un ResultSet que se recibe como parametros.
 *
 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
 * @return el valor de un objeto TipoRecaudo
 * @throws SQLException si hay algun error en la consulta
 * @see com.bachue.snr.prosnr04.model.pgn.TipoRecaudo
 */
	private TipoRecaudo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoRecaudo ltr_tr;

		ltr_tr = new TipoRecaudo();

		ltr_tr.setIdTipoRecaudo(ars_rs.getString("ID_TIPO_RECAUDO"));
		ltr_tr.setNombreTipoRecaudo(ars_rs.getString("NOMBRE_TIPO_RECAUDO"));
		ltr_tr.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ltr_tr);

		return ltr_tr;
	}
}
