package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoEjecutoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultasd de la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA
 *
 * @author Julian Vaca
 */
public class TipoActoEjecutoriaDAO extends BaseDAO implements IBase<TipoActoEjecutoria>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_ACTO_EJECUTORIA WHERE ID_TIPO_ACTO=? AND VERSION_ACTO = ? AND ID_TIPO_DOCUMENTO = ? ORDER BY VERSION DESC";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_ACTO_EJECUTORIA SET VERSION = ?, TIEMPO_VENCIMIENTO = ?, HABILES = ?, "
		+ "ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_ACTO = ? "
		+ "AND VERSION_ACTO = ? AND ID_TIPO_DOCUMENTO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_ACTO_EJECUTORIA("
		+ "	ID_TIPO_ACTO, VERSION_ACTO, ID_TIPO_DOCUMENTO, VERSION, TIEMPO_VENCIMIENTO, HABILES, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_ACTO_EJECUTORIA";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoActoEjecutoria> findAll()
	    throws B2BException
	{
		Collection<TipoActoEjecutoria> lcrc_crc;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcrc_crc     = new ArrayList<TipoActoEjecutoria>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcrc_crc.add(getObjetFromResultSet(lrs_rs));
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

		if(lcrc_crc.isEmpty())
			lcrc_crc = null;

		return lcrc_crc;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA
	 * que coíncida con un ID_ARIFA_FIJA.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoActoEjecutoria
	 * @return devuelve el valor del objeto tipo acto ejecutoria
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoActoEjecutoria findById(TipoActoEjecutoria at_param)
	    throws B2BException
	{
		TipoActoEjecutoria ls_object;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, at_param.getIdTipoActo());
			setInteger(lps_ps, at_param.getVersionActo(), li_column++);
			lps_ps.setString(li_column++, at_param.getIdTipoDocumento());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(TipoActoEjecutoria ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdTipoActo());
					lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersionActo()));
					lps_ps.setString(li_column++, ac_parametros.getIdTipoDocumento());
				}

				lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersion()));
				lps_ps.setInt(li_column++, NumericUtils.getInt(ac_parametros.getTiempoVencimiento()));
				lps_ps.setString(li_column++, ac_parametros.getHabiles());
				lps_ps.setString(li_column++, ac_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdTipoActo());
					lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersionActo()));
					lps_ps.setString(li_column++, ac_parametros.getIdTipoDocumento());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de TipoActoEjecutoria.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de TipoActoEjecutoria
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoActoEjecutoria
	 */
	private TipoActoEjecutoria getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActoEjecutoria lcad_datos;

		lcad_datos = new TipoActoEjecutoria();

		lcad_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lcad_datos.setVersionActo(NumericUtils.getInteger(ars_rs.getInt("VERSION_ACTO")));
		lcad_datos.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		lcad_datos.setVersion(NumericUtils.getInteger(ars_rs.getInt("VERSION")));
		lcad_datos.setTiempoVencimiento(NumericUtils.getInteger(ars_rs.getInt("TIEMPO_VENCIMIENTO")));
		lcad_datos.setHabiles(ars_rs.getString("HABILES"));
		lcad_datos.setActivo(ars_rs.getString("ACTIVO"));
		lcad_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcad_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lcad_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcad_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcad_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lcad_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcad_datos;
	}
}
