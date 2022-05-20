package com.bachue.snr.prosnr04.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
 *
 * @author Luis Chacón
 */
public class PuntoRecaudoTipoRecaudoDAO extends AuditoriaDao
{
	/** Constante consulta cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO";

	/** Constante consulta cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO WHERE ID_PUNTO_RECAUDO_TIPO_RECAUDO = ?";

	/** Constante consulta cs_FIND_BY_PUNTO_Y_TIPO_RECAUDO. */
	private static final String cs_FIND_BY_PUNTO_Y_TIPO_RECAUDO = "SELECT * FROM SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO WHERE ID_PUNTO_RECAUDO = ? AND ID_TIPO_RECAUDO = ?";

	/** Constante consulta cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO SET ID_PUNTO_RECAUDO = ?, ID_TIPO_RECAUDO = ?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PUNTO_RECAUDO_TIPO_RECAUDO = ?";

	/** Constante consulta cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO (ID_PUNTO_RECAUDO_TIPO_RECAUDO, ID_PUNTO_RECAUDO, ID_TIPO_RECAUDO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo para consultar todos los puntos de recaudo tipo de recaudo
	 *
	 * @return Collection de objetos PuntoRecaudoTipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo
	 */
	public Collection<PuntoRecaudoTipoRecaudo> findAll()
	    throws B2BException
	{
		Collection<PuntoRecaudoTipoRecaudo> lcta_ta;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcta_ta     = new ArrayList<PuntoRecaudoTipoRecaudo>();
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
	 * con un idPuntoRecaudoTipoRecaudo específico.
	 *
	 * @param ata_param de tipo PuntoRecaudoTipoRecaudo
	 * @return el valor de punto recaudo tipo recaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo
	 */
	public PuntoRecaudoTipoRecaudo findById(PuntoRecaudoTipoRecaudo ata_param)
	    throws B2BException
	{
		PuntoRecaudoTipoRecaudo ltr_pt;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, ata_param.getIdPuntoRecaudoTipoRecaudo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltr_pt = getObjetFromResultSet(lrs_rs);
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

		return ltr_pt;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un id Punto Recaudo y id Tipo Recaudo específico.
	 *
	 * @param as_puntoRecauto cadena de punto de recaudo
	 * @param as_tipoRecaudo cadena de tipo de recaudo
	 * @return el valor de punto recaudo tipo recaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo
	 */
	public PuntoRecaudoTipoRecaudo findByPuntoyTipoRecaudo(String as_puntoRecauto, String as_tipoRecaudo)
	    throws B2BException
	{
		PuntoRecaudoTipoRecaudo ltr_pt;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_puntoRecauto) && StringUtils.isValidString(as_tipoRecaudo))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PUNTO_Y_TIPO_RECAUDO);

				lps_ps.setString(1, as_puntoRecauto);
				lps_ps.setString(2, as_tipoRecaudo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltr_pt = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByPuntoyTipoRecaudo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ltr_pt;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @param ata_param objeto de PuntoRecaudoTipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo
	 */
	public void insert(PuntoRecaudoTipoRecaudo ata_param)
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

				lps_ps.setString(li_column++, ata_param.getIdPuntoRecaudoTipoRecaudo());
				lps_ps.setString(li_column++, ata_param.getIdPuntoRecaudo());
				lps_ps.setString(li_column++, ata_param.getIdTipoRecaudo());
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
	 * Metodo actualizar un registro en la base de datos de la tabla SDB_PGN_PUNTO_RECAUDO_TIPO_RECAUDO.
	 *
	 * @param ata_param de objeto PuntoRecaudoTipoRecaudo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.PuntoRecaudoTipoRecaudo
	 */
	public void update(PuntoRecaudoTipoRecaudo ata_param)
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

				lps_ps.setString(li_column++, ata_param.getIdPuntoRecaudo());
				lps_ps.setString(li_column++, ata_param.getIdTipoRecaudo());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ata_param.getIpModificacion());
				lps_ps.setString(li_column++, ata_param.getIdPuntoRecaudoTipoRecaudo());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

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
 * Método para la obtencion del objeto PuntoRecaudoTipoRecaudo.
 *
 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
 * @return el valor de objet from result set
 * @throws SQLException Señala que se ha producido una excepción
 */
	private PuntoRecaudoTipoRecaudo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		PuntoRecaudoTipoRecaudo lprtr_prtr;

		lprtr_prtr = new PuntoRecaudoTipoRecaudo();

		lprtr_prtr.setIdPuntoRecaudoTipoRecaudo(ars_rs.getString("ID_PUNTO_RECAUDO_TIPO_RECAUDO"));
		lprtr_prtr.setIdPuntoRecaudo(ars_rs.getString("ID_PUNTO_RECAUDO"));
		lprtr_prtr.setIdTipoRecaudo(ars_rs.getString("ID_TIPO_RECAUDO"));
		lprtr_prtr.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lprtr_prtr);

		return lprtr_prtr;
	}
}
