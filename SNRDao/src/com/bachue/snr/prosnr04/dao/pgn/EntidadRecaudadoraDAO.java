package com.bachue.snr.prosnr04.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Class que contiene todos las propiedades EntidadRecaudadoraDAO.
 *
 * @author itrujillo
 */
public class EntidadRecaudadoraDAO extends AuditoriaDao
{
	/** Constante consulta cs_FIND_BY_CODIGO. */
	private static final String cs_FIND_BY_CODIGO = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA WHERE CODIGO_ENTIDAD_RECAUDADORA = ?";

	/** Constante consulta cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA ORDER BY NOMBRE_ENTIDAD_RECAUDADORA ASC";

	/** Constante consulta cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_ENTIDAD_RECAUDADORA WHERE ID_ENTIDAD_RECAUDADORA = ?";

	/** Constante consulta cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ENTIDAD_RECAUDADORA SET NOMBRE_ENTIDAD_RECAUDADORA = ?, CODIGO_ENTIDAD_RECAUDADORA = ?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ENTIDAD_RECAUDADORA = ?";

	/** Constante consulta cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ENTIDAD_RECAUDADORA (ID_ENTIDAD_RECAUDADORA, NOMBRE_ENTIDAD_RECAUDADORA, CODIGO_ENTIDAD_RECAUDADORA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 *  Metodo que busca todas las entidades recaudadoras
	 *
	 * @return el valor de collection de tipo EntidadRecaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.pgn.EntidadRecaudadora
	 */
	public Collection<EntidadRecaudadora> findAll()
	    throws B2BException
	{
		Collection<EntidadRecaudadora> lcer_cer;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcer_cer     = new ArrayList<EntidadRecaudadora>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcer_cer.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcer_cer))
			lcer_cer = null;

		return lcer_cer;
	}

	/**
	 * Metodo para encontrar los registros que coincidan
	 * con un codigo Entidad Recaudadora específico de la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param as_param cadema correspondiente al id
	 * @return el valor de entidad recaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public EntidadRecaudadora findByCodigo(String as_param)
	    throws B2BException
	{
		EntidadRecaudadora ler_return;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ler_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CODIGO);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ler_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCodigo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ler_return;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idEntidadRecaudadora específico.
	 *
	 * @param ata_param objeto para buscar un objeto tipo EntidadRecaudadora por ID
	 * @return el valor de entidad recaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public EntidadRecaudadora findById(EntidadRecaudadora ata_param)
	    throws B2BException
	{
		EntidadRecaudadora ler_entidadRecaudadora;

		ler_entidadRecaudadora = new EntidadRecaudadora();

		if(ata_param != null)
			ler_entidadRecaudadora = findById(ata_param.getIdEntidadRecaudadora());

		return ler_entidadRecaudadora;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idEntidadRecaudadora específico.
	 *
	 * @param ata_param objeto para buscar un objeto tipo EntidadRecaudadora por ID
	 * @return el valor de entidad recaudadora
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public EntidadRecaudadora findById(String as_idEntidadRecaudadora)
	    throws B2BException
	{
		EntidadRecaudadora ltr_pt;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(StringUtils.isValidString(as_idEntidadRecaudadora))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idEntidadRecaudadora);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltr_pt = getObjetFromResultSet(lrs_rs);
			}
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
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param ata_param objeto tipo EntidadRecaudadora a insertar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(EntidadRecaudadora ata_param)
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

				lps_ps.setString(li_column++, ata_param.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, ata_param.getNombreEntidadRecaudadora());
				lps_ps.setString(li_column++, ata_param.getCodigoEntidadRecaudadora());
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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA.
	 *
	 * @param ata_param objeto EntidadRecaudadora para actualizar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(EntidadRecaudadora ata_param)
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

				lps_ps.setString(li_column++, ata_param.getNombreEntidadRecaudadora());
				lps_ps.setString(li_column++, ata_param.getCodigoEntidadRecaudadora());
				lps_ps.setString(li_column++, ata_param.getActivo());

				lps_ps.setString(li_column++, ata_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ata_param.getIpModificacion());
				lps_ps.setString(li_column++, ata_param.getIdEntidadRecaudadora());

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
	 * Método para la obtencion del objeto EntidadRecaudadora.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private EntidadRecaudadora getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EntidadRecaudadora ler_er;

		ler_er = new EntidadRecaudadora();

		ler_er.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		ler_er.setNombreEntidadRecaudadora(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
		ler_er.setCodigoEntidadRecaudadora(ars_rs.getString("CODIGO_ENTIDAD_RECAUDADORA"));
		ler_er.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ler_er);

		return ler_er;
	}
}
