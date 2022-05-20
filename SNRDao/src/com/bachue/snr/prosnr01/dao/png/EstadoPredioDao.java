package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.png.EstadoPredio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PNG_ESTADO_PREDIO.
 *
 * @author Julian Vaca
 */
public class EstadoPredioDao extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_ESTADO_PREDIO";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_ESTADO_PREDIO WHERE ID_ESTADO_PREDIO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_ESTADO_PREDIO SET NOMBRE = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ESTADO_PREDIO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_ESTADO_PREDIO (ID_ESTADO_PREDIO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Retorna el valor de Collection con objetos de tipo EstadoPredio.
	 *
	 * @return devuelve el valor del objeto collection de tipo EstadoPredio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	public Collection<EstadoPredio> findAll()
	    throws B2BException
	{
		Collection<EstadoPredio> lcr_cr;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcr_cr     = new ArrayList<EstadoPredio>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcr_cr.add(getEstadoPredio(lrs_rs));
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

		return lcr_cr;
	}

	/**
	 * Buscar por llave de la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @param aep_param de aep param
	 * @return el valor de estado predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoPredio findById(EstadoPredio aep_param)
	    throws B2BException
	{
		return ((aep_param != null) ? findById(aep_param.getIdEstadoPredio()) : null);
	}

	/**
	 * Buscar por llave de la tabla SDB_PNG_ESTADO_PREDIO.
	 *
	 * @param as_param de as param
	 * @return el valor de estado predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EstadoPredio findById(String as_param)
	    throws B2BException
	{
		EstadoPredio      lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcr_cr = getEstadoPredio(lrs_rs);
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

		return lcr_cr;
	}

	/**
	 * Metodo para insertar o actualizar un registro en la base de datos de la tabla SDB_PNG_ESTADO_NUPRE.
	 *
	 * @param aep_param correspondiente al valor del tipo de objeto EstadoPredio
	 * @param ab_query correspondiente al valor del tipo de objeto boolean, verdadero para insertar un nuevo registro
	 * en la base de datos, falso para actualizar el registro en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	public void insertOrUpdate(EstadoPredio aep_param, boolean ab_query)
	    throws B2BException
	{
		if(aep_param != null)
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
					lps_ps.setString(li_column++, aep_param.getIdEstadoPredio());

				lps_ps.setString(li_column++, aep_param.getNombre());
				lps_ps.setString(li_column++, aep_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aep_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aep_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aep_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aep_param.getIpModificacion());
					lps_ps.setString(li_column++, aep_param.getIdEstadoPredio());
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
	 * Retorna el valor de estado predio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de estado predio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EstadoPredio
	 */
	private EstadoPredio getEstadoPredio(ResultSet ars_rs)
	    throws SQLException
	{
		EstadoPredio ld_datos;
		ld_datos = new EstadoPredio();

		ld_datos.setIdEstadoPredio(ars_rs.getString("ID_ESTADO_PREDIO"));
		ld_datos.setNombre(ars_rs.getString("NOMBRE"));
		ld_datos.setActivo(ars_rs.getString("ACTIVO"));
		ld_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_datos;
	}
}
