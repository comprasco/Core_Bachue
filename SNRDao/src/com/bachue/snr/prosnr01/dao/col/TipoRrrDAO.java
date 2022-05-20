package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.col.TipoRrr;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas para la tabla SDB_COL_TIPO_RRR
 *
 * @author Julian Vaca
 */
public class TipoRrrDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_COL_TIPO_RRR";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_TIPO_RRR WHERE ID_TIPO_RRR=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_TIPO_RRR SET ILICODE=?, DESCRIPCION=?, "
		+ "ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_TIPO_RRR=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_TIPO_RRR (ID_TIPO_RRR, ILICODE, "
		+ "DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_COL_TIPO_RRR_ID_TIPO_RRR.NEXTVAL FROM DUAL";

	/**
	 * Retorna el valor del objeto de tipo Collection de TipoRrr consultado por activo
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para que el campo activo sea 'S'
	 * @return devuelve el valor del objeto collection de TipoRrr
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoRrr
	 */
	public Collection<TipoRrr> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<TipoRrr> lcpt_pt;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		StringBuilder       lsb_sbf;

		lcpt_pt     = new ArrayList<TipoRrr>();
		lps_ps      = null;
		lrs_rs      = null;
		lsb_sbf     = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcpt_pt.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcpt_pt))
			lcpt_pt = null;

		return lcpt_pt;
	}

	/**
	 * Find by id.
	 *
	 * @param atr_param de atr param
	 * @return de tipo rrr
	 * @throws B2BException de b 2 B exception
	 */
	public TipoRrr findById(TipoRrr atr_param)
	    throws B2BException
	{
		if(atr_param != null)
			return findById(atr_param.getIdTipoRrr());
		else

			return null;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param de as param
	 * @return de tipo rrr
	 * @throws B2BException de b 2 B exception
	 */
	public TipoRrr findById(String as_param)
	    throws B2BException
	{
		TipoRrr           ltr_pt;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ltr_pt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ltr_pt = getObjetFromResultSet(lrs_rs);
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

		return ltr_pt;
	}

	/**
	 * Insert or update.
	 *
	 * @param atr_param de atr param
	 * @param ab_query de ab query
	 * @throws B2BException de b 2 B exception
	 */
	public void insertOrUpdate(TipoRrr atr_param, boolean ab_query)
	    throws B2BException
	{
		if(atr_param != null)
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
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							atr_param.setIdTipoRrr(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, atr_param.getIdTipoRrr());
					}
				}

				lps_ps.setString(li_column++, atr_param.getIlicode());
				lps_ps.setString(li_column++, atr_param.getDescripcion());
				lps_ps.setString(li_column++, atr_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, atr_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, atr_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, atr_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, atr_param.getIpModificacion());
					lps_ps.setString(li_column++, atr_param.getIdTipoRrr());
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
	 * Retorna el valor de TipoRrr
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoRrr
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoRrr
	 */
	private TipoRrr getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TipoRrr lpt_pt;

		lpt_pt = new TipoRrr();

		lpt_pt.setIdTipoRrr(lrs_rs.getString("ID_TIPO_RRR"));
		lpt_pt.setIlicode(lrs_rs.getString("ILICODE"));
		lpt_pt.setDescripcion(lrs_rs.getString("DESCRIPCION"));
		lpt_pt.setActivo(lrs_rs.getString("ACTIVO"));
		lpt_pt.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lpt_pt.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lpt_pt.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lpt_pt.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lpt_pt.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lpt_pt.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return lpt_pt;
	}
}
