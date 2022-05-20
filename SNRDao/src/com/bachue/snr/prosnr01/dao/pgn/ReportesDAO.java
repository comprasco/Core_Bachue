package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.Reportes;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_REPORTES
 *
 * @author Julian Vaca
 */
public class ReportesDAO extends BaseDAO implements IBase<Reportes>
{
	/** Constante CS_FIND_ALL_BY_CODIGO. */
	private static final String CS_FIND_ALL_BY_CODIGO = "SELECT ID_REPORTE, CODIGO, DESCRIPCION, ORDEN_EJECUCION, ORDEN_CONSULTA, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION"
		+ " FROM SDB_PGN_REPORTES WHERE ESTADO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_REPORTES  WHERE ID_REPORTE=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_REPORTES SET CODIGO = ?, DESCRIPCION = ?, ORDEN_EJECUCION = ?, ORDEN_CONSULTA = ?, ESTADO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_REPORTE = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_REPORTES("
		+ "ID_REPORTE, CODIGO, DESCRIPCION, ORDEN_EJECUCION, ORDEN_CONSULTA, ESTADO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_REPORTES";

	/** Constante cs_FIND_SECUENCE_REPORTES. */
	private static final String cs_FIND_SECUENCE_REPORTES = "SELECT SEC_PGN_REPORTES_ID_REPORTE.NEXTVAL FROM DUAL";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_REPORTES.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Reportes> findAll()
	    throws B2BException
	{
		Collection<Reportes> lcrc_crc;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lcrc_crc     = new ArrayList<Reportes>();
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
	 * Método para encontrar todos los registros de la tabla SDB_PGN_REPORTES
	 * que coíncida con un CODIGO.
	 *
	 * @return devuelve el valor del objeto collection de Reportes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Reportes> findAllByActiveState()
	    throws B2BException
	{
		Collection<Reportes> ls_object = null;

		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		lps_ps                         = null;
		lrs_rs                         = null;

		ls_object = new ArrayList<Reportes>();

		try
		{
			lps_ps     = getConnection().prepareStatement(CS_FIND_ALL_BY_CODIGO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		return ls_object;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_REPORTES
	 * que coíncida con un ID_REPORTE.
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto Reportes
	 * @return devuelve el valor del objeto reportes
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Reportes findById(Reportes apt_parametros)
	    throws B2BException
	{
		Reportes          ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(1, NumericUtils.getLong(apt_parametros.getIdReporte()));

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
	public void insertOrUpdate(Reportes ac_parametros, boolean ab_query)
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
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_REPORTES);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdReporte((BigDecimal)lo_o);
						else
							throw new B2BException(ErrorKeys.PGN_REPORTES);
					}

					lps_ps.setBigDecimal(li_column++, ac_parametros.getIdReporte());
				}

				lps_ps.setString(li_column++, ac_parametros.getCodigo());
				lps_ps.setString(li_column++, ac_parametros.getDescripcion());
				lps_ps.setLong(li_column++, NumericUtils.getLong(ac_parametros.getOrdenEjecucion()));
				lps_ps.setString(li_column++, ac_parametros.getOrdenConsulta());
				lps_ps.setString(li_column++, ac_parametros.getEstado());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setInt(li_column++, NumericUtils.getInt(ac_parametros.getIdReporte()));
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
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de Reportes.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de Reportes
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Reportes
	 */
	private Reportes getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Reportes lr_regional;

		lr_regional = new Reportes();

		lr_regional.setIdReporte(ars_rs.getBigDecimal("ID_REPORTE"));
		lr_regional.setCodigo(ars_rs.getString("CODIGO"));
		lr_regional.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lr_regional.setOrdenEjecucion(NumericUtils.getLongWrapper(ars_rs.getInt("ORDEN_EJECUCION")));
		lr_regional.setOrdenConsulta(ars_rs.getString("ORDEN_CONSULTA"));
		lr_regional.setEstado(ars_rs.getString("ESTADO"));
		lr_regional.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lr_regional.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lr_regional.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lr_regional.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lr_regional.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lr_regional.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lr_regional;
	}
}
