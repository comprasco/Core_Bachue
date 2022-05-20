package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.png.EstadoAnotacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultar relacionadas a la tabla SDB_PNG_ESTADO_ANOTACION de la base de datos
 *
 * @author ccalderon
 */
public class EstadoAnotacionDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_ESTADO_ANOTACION WHERE ID_ESTADO_ANOTACION = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_ESTADO_ANOTACION ORDER BY NOMBRE ASC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_ESTADO_ANOTACION SET NOMBRE = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ESTADO_ANOTACION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_ESTADO_ANOTACION (ID_ESTADO_ANOTACION, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_ACC_ANOTACION_PREDIO.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<EstadoAnotacion> findAll()
	    throws B2BException
	{
		Collection<EstadoAnotacion> lea_ea;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lea_ea     = new ArrayList<EstadoAnotacion>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lea_ea.add(getEstadoAnotacion(lrs_rs));
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

		if(lea_ea.isEmpty())
			lea_ea = null;

		return lea_ea;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un idEstadoAnotacion específico.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto EstadoAnotacion
	 * @return devuelve el valor del objeto estado anotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoAnotacion
	 */
	public EstadoAnotacion findById(EstadoAnotacion at_param)
	    throws B2BException
	{
		EstadoAnotacion   lea_ea;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lea_ea     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdEstadoAnotacion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lea_ea = getEstadoAnotacion(lrs_rs);
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

		return lea_ea;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PNG_GRUPO_NATURALEZA_JURIDICA.
	 *
	 * @param aea_param correspondiente al valor del tipo de objeto EstadoAnotacion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(EstadoAnotacion aea_param, boolean ab_query)
	    throws B2BException
	{
		if(aea_param != null)
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
					lps_ps.setString(li_column++, aea_param.getIdEstadoAnotacion());

				lps_ps.setString(li_column++, aea_param.getNombre());
				lps_ps.setString(li_column++, aea_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aea_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aea_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aea_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aea_param.getIpModificacion());
					lps_ps.setString(li_column++, aea_param.getIdEstadoAnotacion());
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
	 * Retorna el valor de estado anotacion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de estado anotacion de tipo EstadoAnotacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EstadoAnotacion
	 */
	private EstadoAnotacion getEstadoAnotacion(ResultSet ars_rs)
	    throws SQLException
	{
		EstadoAnotacion lea_datos;
		lea_datos = new EstadoAnotacion();

		lea_datos.setIdEstadoAnotacion(ars_rs.getString("ID_ESTADO_ANOTACION"));
		lea_datos.setNombre(ars_rs.getString("NOMBRE"));
		lea_datos.setActivo(ars_rs.getString("ACTIVO"));
		lea_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lea_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lea_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lea_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lea_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lea_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lea_datos;
	}
}
