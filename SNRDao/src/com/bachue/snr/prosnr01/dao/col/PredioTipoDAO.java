package com.bachue.snr.prosnr01.dao.col;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.col.PredioTipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades PredioTipoDAO.
 *
 * @author  garias
 * Fecha de Creacion: 26/08/2020
 */
public class PredioTipoDAO extends BaseDAO implements IBase<PredioTipo>
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_COL_PREDIO_TIPO";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_COL_PREDIO_TIPO WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_COL_PREDIO_TIPO WHERE ID_TIPO_PREDIO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_COL_PREDIO_TIPO SET ILICODE=?, DESCRIPCION=?, "
		+ "ACTIVO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_TIPO_PREDIO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_COL_PREDIO_TIPO (ID_TIPO_PREDIO, ILICODE, "
		+ "DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioTipo.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de PredioTipo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioTipo
	 */
	public Collection<PredioTipo> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<PredioTipo> lcpt_pt;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;
		StringBuilder          lsb_sbf;

		lcpt_pt     = new ArrayList<PredioTipo>();
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
	 * Find all activo map.
	 *
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> findAllActivoMap()
	    throws B2BException
	{
		Map<String, String> lmss_return;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lmss_return     = new HashMap<String, String>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
			{
				PredioTipo lpt_predioTipo;

				lpt_predioTipo = getObjetFromResultSet(lrs_rs);

				if(lpt_predioTipo != null)
					lmss_return.put(lpt_predioTipo.getIdTipoPredio(), lpt_predioTipo.getIlicode());
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivoMap", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lmss_return.isEmpty())
			lmss_return = null;

		return lmss_return;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param apt_param correspondiente al valor del tipo de objeto PredioTipo
	 * @return Predio tipo resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PredioTipo findById(PredioTipo apt_param)
	    throws B2BException
	{
		return (apt_param != null) ? findById(apt_param.getIdTipoPredio()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idTipoPredio correspondiente al valor del tipo de objeto String
	 * @return Predio tipo resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PredioTipo findById(String as_idTipoPredio)
	    throws B2BException
	{
		PredioTipo lcpt_pt;

		lcpt_pt = null;

		if(StringUtils.isValidString(as_idTipoPredio))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idTipoPredio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcpt_pt = getObjetFromResultSet(lrs_rs);
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
		}

		return lcpt_pt;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param apt_param de apt param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void insertOrUpdate(PredioTipo apt_param, boolean ab_query)
	    throws B2BException
	{
		if(apt_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, apt_param.getIdTipoPredio());

				lps_ps.setString(li_column++, apt_param.getIlicode());
				lps_ps.setString(li_column++, apt_param.getDescripcion());
				lps_ps.setString(li_column++, apt_param.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, apt_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apt_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apt_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apt_param.getIpModificacion());
					lps_ps.setString(li_column++, apt_param.getIdTipoPredio());
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
	 * Retorna el valor de PredioTipo.
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de PredioTipo
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see PredioTipo
	 */
	private PredioTipo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		PredioTipo lpt_pt;

		lpt_pt = new PredioTipo();

		lpt_pt.setIdTipoPredio(lrs_rs.getString("ID_TIPO_PREDIO"));
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
