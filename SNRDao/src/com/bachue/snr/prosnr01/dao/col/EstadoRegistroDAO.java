package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoRegistro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PNG_ESTADO_REGISTRO.
 *
 * @author Carlos Calderón
 */
public class EstadoRegistroDAO extends BaseDAO implements IBase<EstadoRegistro>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_ESTADO_REGISTRO";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_ESTADO_REGISTRO WHERE ID_ESTADO_REGISTRO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_ESTADO_REGISTRO SET DESCRIPCION = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ESTADO_REGISTRO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_ESTADO_REGISTRO (ID_ESTADO_REGISTRO, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de EstadoRegistro consultando todo en la tabla
	 *
	 * @return devuelve el valor del objeto collection de EstadoRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoRegistro
	 */
	public Collection<EstadoRegistro> findAll()
	    throws B2BException
	{
		Collection<EstadoRegistro> lcer_er;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcer_er     = new ArrayList<EstadoRegistro>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcer_er.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcer_er))
			lcer_er = null;

		return lcer_er;
	}

	/** {@inheritdoc} */
	@Override
	public EstadoRegistro findById(EstadoRegistro aer_param)
	    throws B2BException
	{
		EstadoRegistro    ler_estadoRegistro;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ler_estadoRegistro     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, aer_param.getIdEstadoRegistro());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ler_estadoRegistro = getObjetFromResultSet(lrs_rs);
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

		return ler_estadoRegistro;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(EstadoRegistro aer_param, boolean ab_query)
	    throws B2BException
	{
		if(aer_param != null)
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
					lps_ps.setString(li_column++, aer_param.getIdEstadoRegistro());

				lps_ps.setString(li_column++, aer_param.getDescripcion());
				lps_ps.setString(li_column++, aer_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aer_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aer_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aer_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aer_param.getIpModificacion());
					lps_ps.setString(li_column++, aer_param.getIdEstadoRegistro());
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
	 * Método para la obtencion del objeto EstadoRegistro.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de EstadoRegistro
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EstadoRegistro
	 */
	private EstadoRegistro getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EstadoRegistro ler_estadoRegistro;

		ler_estadoRegistro = new EstadoRegistro();

		ler_estadoRegistro.setIdEstadoRegistro(ars_rs.getString("ID_ESTADO_REGISTRO"));
		ler_estadoRegistro.setDescripcion(ars_rs.getString("DESCRIPCION"));
		ler_estadoRegistro.setActivo(ars_rs.getString("ACTIVO"));
		ler_estadoRegistro.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ler_estadoRegistro.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ler_estadoRegistro.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ler_estadoRegistro.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ler_estadoRegistro.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ler_estadoRegistro.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ler_estadoRegistro;
	}
}
