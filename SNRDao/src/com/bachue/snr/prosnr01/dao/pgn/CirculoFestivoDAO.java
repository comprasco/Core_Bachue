package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoFestivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_CIRCULO_FESTIVO.
 *
 * @author Sebastian Tafur
 */
public class CirculoFestivoDAO extends BaseDAO implements IBase<CirculoFestivo>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CIRCULO_FESTIVO WHERE ID_CIRCULO_FESTIVO = ?";

	/** Constante cs_FIND_BY_ID_FECHA. */
	private static final String cs_FIND_BY_ID_FECHA = "SELECT * FROM SDB_PGN_CIRCULO_FESTIVO WHERE ID_CIRCULO = ? AND TO_CHAR(FECHA,'DD/MM/YYYY') = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CIRCULO_FESTIVO SET ID_CIRCULO = ?,"
		+ " FECHA = ?, DESCRIPCION = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP"
		+ " WHERE ID_CIRCULO_FESTIVO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CIRCULO_FESTIVO("
		+ "ID_CIRCULO, FECHA, DESCRIPCION, ACTIVO, IP_CREACION, ID_USUARIO_CREACION, FECHA_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CIRCULO_FESTIVO";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_CIRCULO_FESTIVO.
	 *
	 * @param ab_activo correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de CirculoFestivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CirculoFestivo> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<CirculoFestivo> lcf_ccf;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcf_ccf     = new ArrayList<CirculoFestivo>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_sb.append(" WHERE ESTADO = 'A'");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcf_ccf.add(getObjetFromResultSet(lrs_rs));
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

		if(lcf_ccf.isEmpty())
			lcf_ccf = null;

		return lcf_ccf;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_FESTIVO
	 * que coíncida con un Id circulo festivo específico.
	 *
	 * @param acf_param objeto del cual se extrae el id para realizar la consulta en la base de datos
	 * @return devuelve el valor del objeto circulo festivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CirculoFestivo findById(CirculoFestivo acf_param)
	    throws B2BException
	{
		return (acf_param != null) ? findById(acf_param.getIdCirculoFestivo()) : null;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_FESTIVO
	 * que coíncida con un ID_MEDIDA_AREA específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto circulo festivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CirculoFestivo findById(String as_param)
	    throws B2BException
	{
		CirculoFestivo    lcf_cf;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcf_cf     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcf_cf = getObjetFromResultSet(lrs_rs);
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

		return lcf_cf;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CIRCULO_FESTIVO
	 * que coíncida con un ID_MEDIDA_AREA específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto circulo festivo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CirculoFestivo findByIdAndFecha(String as_param, Date ld_fecha)
	    throws B2BException
	{
		CirculoFestivo    lcf_cf;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcf_cf     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_count;
			li_count     = 1;
			lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_FECHA);

			lps_ps.setString(li_count++, as_param);
			lps_ps.setString(li_count++, StringUtils.getString(ld_fecha, FormatoFechaCommon.DIA_MES_ANIO));

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcf_cf = getObjetFromResultSet(lrs_rs);
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

		return lcf_cf;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param acf_parametros correspondiente al valor del tipo de objeto CirculoFestivo
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(CirculoFestivo acf_parametros, boolean ab_query)
	    throws B2BException
	{
		if(acf_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				lps_ps.setString(li_column++, acf_parametros.getIdCirculo());
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(acf_parametros.getFecha()));
				lps_ps.setString(li_column++, acf_parametros.getDescripcion());
				lps_ps.setString(li_column++, acf_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, acf_parametros.getIpCreacion());
					lps_ps.setString(li_column++, acf_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, acf_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acf_parametros.getIpModificacion());
					lps_ps.setString(li_column++, acf_parametros.getIdCirculoFestivo());
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
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de medida area.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CirculoFestivo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CirculoFestivo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CirculoFestivo lcf_datos;

		lcf_datos = new CirculoFestivo();

		lcf_datos.setIdCirculoFestivo(ars_rs.getString("ID_CIRCULO_FESTIVO"));
		lcf_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lcf_datos.setFecha(ars_rs.getDate("FECHA"));
		lcf_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lcf_datos.setActivo(ars_rs.getString("ACTIVO"));
		lcf_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcf_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcf_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcf_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcf_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcf_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcf_datos;
	}
}
