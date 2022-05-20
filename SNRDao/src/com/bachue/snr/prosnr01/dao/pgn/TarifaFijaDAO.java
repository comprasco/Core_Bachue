package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TarifaFija;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TARIFA_FIJA
 *
 * @author ccalderon
 */
public class TarifaFijaDAO extends BaseDAO implements IBase<TarifaFija>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TARIFA_FIJA WHERE ID_TARIFA_FIJA=? AND VERSION= ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TARIFA_FIJA SET ID_TIPO_ACTO= ?, VERSION_ACTO= ?,"
		+ "ID_PROCESO= ?, ID_SUBPROCESO= ?, VALOR= ?, FECHA_DESDE= ?, FECHA_HASTA= ?, ACTIVO= ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TARIFA_FIJA = ? AND VERSION= ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TARIFA_FIJA("
		+ "	ID_TARIFA_FIJA, VERSION, ID_TIPO_ACTO, VERSION_ACTO, ID_PROCESO, ID_SUBPROCESO, VALOR, FECHA_DESDE, FECHA_HASTA, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TARIFA_FIJA ORDER BY ID_TARIFA_FIJA ASC";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TARIFA_FIJA.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TarifaFija> findAll()
	    throws B2BException
	{
		Collection<TarifaFija> lcrc_crc;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcrc_crc     = new ArrayList<TarifaFija>();
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
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TARIFA_FIJA
	 * que coíncida con un ID_TARIFA_FIJA.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TarifaFija
	 * @return devuelve el valor del objeto tarifa fija
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TarifaFija findById(TarifaFija at_param)
	    throws B2BException
	{
		TarifaFija        ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, at_param.getIdTarifaFija());
			lps_ps.setLong(li_column++, NumericUtils.getLong(at_param.getVersion()));

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
	public void insertOrUpdate(TarifaFija ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdTarifaFija());
					lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersion()));
				}

				lps_ps.setString(li_column++, ac_parametros.getIdTipoActo());
				lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersionActo()));
				lps_ps.setString(li_column++, ac_parametros.getIdProceso());
				lps_ps.setString(li_column++, ac_parametros.getIdSubProceso());
				lps_ps.setInt(li_column++, NumericUtils.getInt(ac_parametros.getValor()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ac_parametros.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ac_parametros.getFechaHasta()));
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
					lps_ps.setString(li_column++, ac_parametros.getIdTarifaFija());
					lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersion()));
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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de TarifaFija.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de TarifaFija
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TarifaFija
	 */
	private TarifaFija getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TarifaFija lcad_datos;

		lcad_datos = new TarifaFija();

		lcad_datos.setIdTarifaFija(ars_rs.getString("ID_TARIFA_FIJA"));
		lcad_datos.setVersion(getLong(ars_rs, "VERSION"));
		lcad_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lcad_datos.setVersionActo(getLong(ars_rs, "VERSION_ACTO"));
		lcad_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lcad_datos.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lcad_datos.setValor(NumericUtils.getInteger(ars_rs.getInt("VALOR")));
		lcad_datos.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lcad_datos.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
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
