package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActoCondicion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades de TipoActoCondicionDAO.
 *
 * @author Julian Vaca
 */
public class TipoActoCondicionDAO extends BaseDAO implements IBase<TipoActoCondicion>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_ACTO_CONDICION WHERE ID_TIPO_ACTO_CONDICION=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_ACTO_CONDICION SET ID_TIPO_ACTO=?, VERSION= ?, ID_PROCESO= ?, ID_SUBPROCESO= ?, "
		+ "ID_CONDICION= ?, ORDEN=?, ACTIVO= ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_ACTO_CONDICION = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_ACTO_CONDICION("
		+ "	ID_TIPO_ACTO_CONDICION, ID_TIPO_ACTO, VERSION, ID_PROCESO, ID_SUBPROCESO, ID_CONDICION, ORDEN, ACTIVO, "
		+ " ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION) " + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_ACTO_CONDICION";

	/** Constante cs_FIND_SECUENCE_SDB_PGN_TIPO_ACTO_CONDICION. */
	private static final String cs_FIND_SECUENCE_SDB_PGN_TIPO_ACTO_CONDICION = "SELECT SEC_PGN_TIPO_ACTO_CONDICION_ID_TIPO_ACTO_CONDICION.NEXTVAL FROM DUAL";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_ACTO_CONDICION.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoActoCondicion> findAll()
	    throws B2BException
	{
		Collection<TipoActoCondicion> lcrc_crc;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcrc_crc     = new ArrayList<TipoActoCondicion>();
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
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_ACTO_CONDICION
	 * que coíncida con un ID_ARIFA_FIJA.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoActoCondicion
	 * @return devuelve el valor del objeto tipo acto condicion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoActoCondicion findById(TipoActoCondicion at_param)
	    throws B2BException
	{
		TipoActoCondicion ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoActo());

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
	public void insertOrUpdate(TipoActoCondicion ac_parametros, boolean ab_query)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_SDB_PGN_TIPO_ACTO_CONDICION);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdTipoActoCondicion(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_TIPO_ACTO_CONDICION);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdTipoActoCondicion());
				}

				lps_ps.setString(li_column++, ac_parametros.getIdTipoActo());
				lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getVersion()));
				lps_ps.setString(li_column++, ac_parametros.getIdProceso());
				lps_ps.setString(li_column++, ac_parametros.getIdSubProceso());
				lps_ps.setString(li_column++, ac_parametros.getIdCondicion());
				lps_ps.setInt(li_column++, NumericUtils.getInt(ac_parametros.getOrden()));
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
					lps_ps.setString(li_column++, ac_parametros.getIdTipoActoCondicion());
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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de TipoActoCondicion.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de TipoActoCondicion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoActoCondicion
	 */
	private TipoActoCondicion getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActoCondicion lcad_datos;

		lcad_datos = new TipoActoCondicion();

		lcad_datos.setIdTipoActoCondicion(ars_rs.getString("ID_TIPO_ACTO_CONDICION"));
		lcad_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lcad_datos.setVersion(NumericUtils.getInteger(ars_rs.getInt("VERSION")));
		lcad_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lcad_datos.setIdSubProceso(ars_rs.getString("ID_SUBPROCESO"));
		lcad_datos.setIdCondicion(ars_rs.getString("ID_CONDICION"));
		lcad_datos.setOrden(NumericUtils.getInteger(ars_rs.getInt("ORDEN")));
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
