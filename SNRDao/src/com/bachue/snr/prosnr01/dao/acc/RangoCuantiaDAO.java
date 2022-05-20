package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.RangoCuantia;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_RANGO_CUANTIA.
 *
 * @author Carlos Calderon
 */
public class RangoCuantiaDAO extends BaseDAO implements IBase<RangoCuantia>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_RANGO_CUANTIA  WHERE ID_RANGO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_RANGO_CUANTIA SET OPERADOR_INFERIOR = ?, RANGO_INFERIOR = ?, OPERADOR_SUPERIOR = ?, "
		+ "RANGO_SUPERIOR = ?, PORCENTAJE = ?, VALOR = ?, FECHA_DESDE = ?,  FECHA_HASTA = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, "
		+ "IP_MODIFICACION = ? WHERE ID_RANGO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_RANGO_CUANTIA(ID_RANGO,OPERADOR_INFERIOR,RANGO_INFERIOR,OPERADOR_SUPERIOR,"
		+ "RANGO_SUPERIOR,PORCENTAJE,VALOR,FECHA_DESDE,FECHA_HASTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_RANGO_CUANTIA";

	/** Constante cs_FIND_SECUENCE_RANGO_CUANTIA. */
	private static final String cs_FIND_SECUENCE_RANGO_CUANTIA = "SELECT SEC_PGN_RANGO_CUANTIA_ID_RANGO.NEXTVAL FROM DUAL";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_RANGO_CUANTIA.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RangoCuantia
	 */
	public Collection<RangoCuantia> findAll()
	    throws B2BException
	{
		Collection<RangoCuantia> lcrc_crc;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcrc_crc     = new ArrayList<RangoCuantia>();
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
	 * Método para encontrar todos los registros de la tabla SDB_PGN_RANGO_CUANTIA
	 * que coíncida con un ID_RANGO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto RangoCuantia
	 * @return devuelve el valor de RangoCuantia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RangoCuantia
	 */
	public RangoCuantia findById(RangoCuantia at_param)
	    throws B2BException
	{
		RangoCuantia      ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdRango());

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
	public void insertOrUpdate(RangoCuantia ac_parametros, boolean ab_query)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_RANGO_CUANTIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdRango(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_RANGO_CUANTIA);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdRango());
				}

				lps_ps.setString(li_column++, ac_parametros.getOperadorInferior());
				lps_ps.setDouble(li_column++, NumericUtils.getDouble(ac_parametros.getRangoInferior()));
				lps_ps.setString(li_column++, ac_parametros.getOperadorSuperior());
				lps_ps.setDouble(li_column++, NumericUtils.getDouble(ac_parametros.getRangoSuperior()));
				lps_ps.setInt(li_column++, NumericUtils.getInt(ac_parametros.getPorcentaje()));
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
					lps_ps.setString(li_column++, ac_parametros.getIdRango());
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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de RangoCuantia.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RangoCuantia getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		RangoCuantia lcad_datos;

		lcad_datos = new RangoCuantia();

		lcad_datos.setIdRango(ars_rs.getString("ID_RANGO"));
		lcad_datos.setOperadorInferior(ars_rs.getString("OPERADOR_INFERIOR"));
		lcad_datos.setRangoInferior(NumericUtils.getDoubleWrapper(ars_rs.getDouble("RANGO_INFERIOR")));
		lcad_datos.setOperadorSuperior(ars_rs.getString("OPERADOR_SUPERIOR"));
		lcad_datos.setRangoSuperior(NumericUtils.getDoubleWrapper(ars_rs.getDouble("RANGO_SUPERIOR")));
		lcad_datos.setPorcentaje(NumericUtils.getInteger(ars_rs.getInt("PORCENTAJE")));
		lcad_datos.setValor(NumericUtils.getInteger(ars_rs.getInt("VALOR")));
		lcad_datos.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lcad_datos.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lcad_datos.setActivo(ars_rs.getString("ACTIVO"));
		lcad_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lcad_datos.setFechaCreacion(ars_rs.getDate("FECHA_CREACION"));
		lcad_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lcad_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lcad_datos.setFechaModificacion(ars_rs.getDate("FECHA_MODIFICACION"));
		lcad_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lcad_datos;
	}
}
