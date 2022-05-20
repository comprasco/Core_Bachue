package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoNupre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PNG_ESTADO_NUPRE.
 *
 * @author Carlos Calderón
 */
public class EstadoNupreDAO extends BaseDAO implements IBase<EstadoNupre>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_ESTADO_NUPRE";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_ESTADO_NUPRE WHERE ID_ESTADO_NUPRE = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_ESTADO_NUPRE SET NOMBRE = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ESTADO_NUPRE = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_ESTADO_NUPRE (ID_ESTADO_NUPRE, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de EstadoNupre consultando todo en la tabla.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoNupre
	 */
	public Collection<EstadoNupre> findAll()
	    throws B2BException
	{
		Collection<EstadoNupre> lcen_en;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcen_en     = new ArrayList<EstadoNupre>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcen_en.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcen_en))
			lcen_en = null;

		return lcen_en;
	}

	/** {@inheritdoc} */
	public EstadoNupre findById(EstadoNupre aen_param)
	    throws B2BException
	{
		return (aen_param != null) ? findById(aen_param.getIdEstadoNupre()) : null;
	}

	/**
	 * Find by id.
	 *
	 * @param as_idEstadoNupre de as id estado nupre
	 * @return el valor de estado nupre
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoNupre findById(String as_idEstadoNupre)
	    throws B2BException
	{
		EstadoNupre len_return;

		len_return = null;

		if(StringUtils.isValidString(as_idEstadoNupre))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idEstadoNupre);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					len_return = getObjetFromResultSet(lrs_rs);
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

		return len_return;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(EstadoNupre aen_param, boolean ab_query)
	    throws B2BException
	{
		if(aen_param != null)
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

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, aen_param.getIdEstadoNupre());

				lps_ps.setString(li_column++, aen_param.getNombre());
				lps_ps.setString(li_column++, aen_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aen_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aen_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aen_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aen_param.getIpModificacion());
					lps_ps.setString(li_column++, aen_param.getIdEstadoNupre());
				}

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
	 * Método para la obtencion del objeto EstadoNupre.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de EstadoNupre
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EstadoNupre
	 */
	private EstadoNupre getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EstadoNupre len_en;

		len_en = new EstadoNupre();

		len_en.setIdEstadoNupre(ars_rs.getString("ID_ESTADO_NUPRE"));
		len_en.setNombre(ars_rs.getString("NOMBRE"));
		len_en.setActivo(ars_rs.getString("ACTIVO"));
		len_en.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		len_en.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		len_en.setIpCreacion(ars_rs.getString("IP_CREACION"));
		len_en.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		len_en.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		len_en.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return len_en;
	}
}
