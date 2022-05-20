package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.Regional;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_REGIONAL
 *
 * @author Julian Vaca
 */
public class RegionalDAO extends BaseDAO
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_REGIONAL";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_PGN_REGIONAL WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_REGIONAL WHERE ID_REGIONAL=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_REGIONAL SET NOMBRE = ?,"
		+ " ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ACTIVO = ? WHERE ID_REGIONAL = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_REGIONAL (ID_REGIONAL,NOMBRE,"
		+ " ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ACTIVO) VALUES (?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_REGIONAL_ID_REGIONAL.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de Regional con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de Regional
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Regional
	 */
	public Collection<Regional> findAll()
	    throws B2BException
	{
		Collection<Regional> ls_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		ls_object     = new ArrayList<Regional>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Regional con todos los registros activos
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Regional
	 */
	public Collection<Regional> findAllActivo()
	    throws B2BException
	{
		Collection<Regional> ls_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		ls_object     = new ArrayList<Regional>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Regional filtrado por ID
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Regional
	 * @return devuelve el valor del objeto regional
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Regional
	 */
	public Regional findById(Regional at_param)
	    throws B2BException
	{
		Regional          ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdRegional());

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
	 * Inserta o Actualiza el registro en la base de datos
	 *
	 * @param apt_parametros correspondiente al valor del tipo de objeto Regional
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Regional apt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(apt_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_secuencia     = null;
			lps_ps            = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				li_column         = 1;
				lps_ps            = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lc_connection     = getConnection();

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(ab_query)
				{
					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							apt_parametros.setIdRegional(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.PGN_REGIONAL_SEQUENCE);
					}
				}

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getIdRegional());
					lps_ps.setString(li_column++, apt_parametros.getNombre());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_parametros.getIpCreacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
				}

				if(!ab_query)
				{
					lps_ps.setString(li_column++, apt_parametros.getNombre());
					lps_ps.setString(li_column++, apt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_parametros.getIpModificacion());
					lps_ps.setString(li_column++, apt_parametros.getActivo());
					lps_ps.setString(li_column++, apt_parametros.getIdRegional());
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
	 * Retorna el valor de Regional
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Regional
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Regional
	 */
	private Regional getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Regional lr_regional;

		lr_regional = new Regional();

		lr_regional.setIdRegional(ars_rs.getString("ID_REGIONAL"));
		lr_regional.setNombre(ars_rs.getString("NOMBRE"));
		lr_regional.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lr_regional.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lr_regional.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lr_regional.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lr_regional.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lr_regional.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lr_regional.setActivo(ars_rs.getString("ACTIVO"));

		return lr_regional;
	}
}
