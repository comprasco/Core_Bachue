package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.CondicionTarifa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_CONDICION_TARIFA.
 *
 * @author ccalderon
 */
public class CondicionTarifaDAO extends BaseDAO implements IBase<CondicionTarifa>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CONDICION_TARIFA WHERE ID_CONDICION_TARIFA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CONDICION_TARIFA SET "
		+ " DESCRIPCION = ?, ACTIVA = ?, CODIGO_SQL_VALIDACION = ?, ID_TIPO_TARIFA_REGISTRAL = ?, VERSION_TARIFA_REGISTRAL = ?, ID_TARIFA_FIJA = ?, VERSION_TARIFA_FIJA = ?, TIPO_ACTO_CONDICION = ?,"
		+ " ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?"
		+ " WHERE ID_CONDICION_TARIFA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CONDICION_TARIFA"
		+ " (ID_CONDICION_TARIFA, DESCRIPCION, ACTIVA, CODIGO_SQL_VALIDACION, ID_TIPO_TARIFA_REGISTRAL, VERSION_TARIFA_REGISTRAL, ID_TARIFA_FIJA, VERSION_TARIFA_FIJA, TIPO_ACTO_CONDICION,"
		+ " IP_CREACION, ID_USUARIO_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CONDICION_TARIFA";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_CONDICION_TARIFA.
	 *
	 * @param ab_activo correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de CondicionTarifa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CondicionTarifa> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<CondicionTarifa> lcct_cct;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcct_cct     = new ArrayList<CondicionTarifa>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder();

			lsb_sb.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_sb.append(" WHERE ACTIVA = 'A'");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcct_cct.add(getObjetFromResultSet(lrs_rs));
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

		if(lcct_cct.isEmpty())
			lcct_cct = null;

		return lcct_cct;
	}

	/** {@inheritdoc} */
	public CondicionTarifa findById(CondicionTarifa act_param)
	    throws B2BException
	{
		return (act_param != null) ? findById(act_param.getIdCondicionTarifa()) : null;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CONDICION_TARIFA
	 * que coíncida con un ID_MEDIDA_AREA específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto condicion tarifa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CondicionTarifa findById(String as_param)
	    throws B2BException
	{
		CondicionTarifa   lct_ct;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lct_ct     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lct_ct = getObjetFromResultSet(lrs_rs);
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

		return lct_ct;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param act_parametros correspondiente al valor del tipo de objeto CondicionTarifa
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(CondicionTarifa act_parametros, boolean ab_query)
	    throws B2BException
	{
		if(act_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, act_parametros.getIdCondicionTarifa());

				lps_ps.setString(li_column++, act_parametros.getDescripcion());
				lps_ps.setString(li_column++, act_parametros.getActiva());
				lps_ps.setString(li_column++, act_parametros.getCodigoSqlValidacion());
				lps_ps.setString(li_column++, act_parametros.getIdTipoTarifaRegistral());
				lps_ps.setString(li_column++, act_parametros.getVersionTarifaRegistral());
				lps_ps.setString(li_column++, act_parametros.getIdTarifaFija());
				lps_ps.setString(li_column++, act_parametros.getVersionTarifaFija());
				lps_ps.setString(li_column++, act_parametros.getIdTipoActoCondicion());

				if(ab_query)
				{
					lps_ps.setString(li_column++, act_parametros.getIpCreacion());
					lps_ps.setString(li_column++, act_parametros.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, act_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, act_parametros.getIpModificacion());
					lps_ps.setString(li_column++, act_parametros.getIdCondicionTarifa());
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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de condición tarifa.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CondicionTarifa
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CondicionTarifa getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CondicionTarifa lct_ct;

		lct_ct = new CondicionTarifa();

		lct_ct.setIdCondicionTarifa(ars_rs.getString("ID_CONDICION_TARIFA"));
		lct_ct.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lct_ct.setActiva(ars_rs.getString("ACTIVA"));
		lct_ct.setCodigoSqlValidacion(ars_rs.getString("CODIGO_SQL_VALIDACION"));
		lct_ct.setIdTipoTarifaRegistral(ars_rs.getString("ID_TIPO_TARIFA_REGISTRAL"));
		lct_ct.setVersionTarifaRegistral(ars_rs.getString("VERSION_TARIFA_REGISTRAL"));
		lct_ct.setIdTarifaFija(ars_rs.getString("ID_TARIFA_FIJA"));
		lct_ct.setVersionTarifaFija(ars_rs.getString("VERSION_TARIFA_FIJA"));
		lct_ct.setIdTipoActoCondicion(ars_rs.getString("TIPO_ACTO_CONDICION"));
		lct_ct.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lct_ct.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lct_ct.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lct_ct.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lct_ct.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lct_ct.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lct_ct;
	}
}
