package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_CAMPOS_CONSULTA
 *
 * @author ccalderon
 */
public class CamposConsultaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAMPOS_CONSULTA WHERE ID_CONSULTA=? AND ID_CAMPO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAMPOS_CONSULTA SET TIPO_CAMPO = ?, NOMBRE_CAMPO = ?, OBLIGATORIO = ?, ESTADO = ?, ORDEN = ?, CAMPO_TABLA = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE ID_CONSULTA = ? AND ID_CAMPO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAMPOS_CONSULTA (ID_CONSULTA, ID_CAMPO, TIPO_CAMPO, NOMBRE_CAMPO, OBLIGATORIO, ESTADO, ORDEN, CAMPO_TABLA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT  * FROM SDB_PGN_CAMPOS_CONSULTA ";

	/** Constante cs_FIND_ALL_BY_ID_CONSULTA_ACTIVE. */
	private static final String cs_FIND_ALL_BY_ID_CONSULTA_ACTIVE = "SELECT * FROM SDB_PGN_CAMPOS_CONSULTA WHERE ID_CONSULTA=? AND ESTADO = 'S' ORDER BY ORDEN ASC";

	/**
	 * Retorna el valor del objeto de tipo Collection de CamposConsulta con todos los registros
	 *
	 * @return devuelve el valor del objeto collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CamposConsulta> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<CamposConsulta> lc_data;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;
		StringBuilder              lsb_sbf;

		lc_data     = new ArrayList<CamposConsulta>();
		lps_ps      = null;
		lrs_rs      = null;
		lsb_sbf     = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" WHERE ESTADO = 'S' ORDER BY NOMBRE_CAMPO ");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY NOMBRE_CAMPO ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_data.add(getObjetFromResultSet(lrs_rs));
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

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de CamposConsulta filtrado por el id de la consulta activa
	 *
	 * @param ac_c correspondiente al valor del tipo de objeto Consultas
	 * @return devuelve el valor del objeto collection de CamposConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CamposConsulta> findAllActive(Consultas ac_c)
	    throws B2BException
	{
		Collection<CamposConsulta> lc_data;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lc_data     = new ArrayList<CamposConsulta>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			if(ac_c != null)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ID_CONSULTA_ACTIVE);

				lps_ps.setLong(1, ac_c.getIdConsulta());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lc_data.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActive", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_data.isEmpty())
			lc_data = null;

		return lc_data;
	}

	/**
	 * Retorna el valor del objeto de tipo CamposConsulta
	 *
	 * @param at_param correspondiente al valor del tipo de objeto CamposConsulta
	 * @return devuelve el valor del objeto campos consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CamposConsulta findById(CamposConsulta at_param)
	    throws B2BException
	{
		CamposConsulta    lcc_cc;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcc_cc     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(at_param != null)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				setLong(lps_ps, at_param.getIdConsulta(), 1);
				setLong(lps_ps, at_param.getIdCampo(), 2);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_cc = getObjetFromResultSet(lrs_rs);
			}
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

		return lcc_cc;
	}

	/**
	 * Inserta o actualiza el registro en la tabla
	 *
	 * @param acc_parametros correspondiente al valor del tipo de objeto CamposConsulta
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(CamposConsulta acc_parametros, boolean ab_query)
	    throws B2BException
	{
		if(acc_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setLong(li_column++, NumericUtils.getLong(acc_parametros.getIdConsulta()));
					lps_ps.setLong(li_column++, NumericUtils.getLong(acc_parametros.getIdCampo()));
				}

				lps_ps.setString(li_column++, acc_parametros.getTipoCampo());
				lps_ps.setString(li_column++, acc_parametros.getNombreCampo());
				lps_ps.setString(li_column++, acc_parametros.getObligatorio());
				lps_ps.setString(li_column++, acc_parametros.getEstado());
				lps_ps.setLong(li_column++, NumericUtils.getLong(acc_parametros.getOrden()));
				lps_ps.setString(li_column++, acc_parametros.getCampoTabla());

				if(ab_query)
				{
					lps_ps.setString(li_column++, acc_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, acc_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, acc_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acc_parametros.getIpModificacion());
					lps_ps.setLong(li_column++, NumericUtils.getLong(acc_parametros.getIdConsulta()));
					lps_ps.setLong(li_column++, NumericUtils.getLong(acc_parametros.getIdCampo()));
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
	 * Retorna el valor de CamposConsulta
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de CamposConsulta
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CamposConsulta
	 */
	private CamposConsulta getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CamposConsulta lc_datos;

		lc_datos = new CamposConsulta();

		lc_datos.setIdConsulta(getLong(ars_rs, "ID_CONSULTA"));
		lc_datos.setIdCampo(getLong(ars_rs, "ID_CAMPO"));
		lc_datos.setTipoCampo(ars_rs.getString("TIPO_CAMPO"));
		lc_datos.setNombreCampo(ars_rs.getString("NOMBRE_CAMPO"));
		lc_datos.setObligatorio(ars_rs.getString("OBLIGATORIO"));
		lc_datos.setEstado(ars_rs.getString("ESTADO"));
		lc_datos.setOrden(getLong(ars_rs, "ORDEN"));
		lc_datos.setCampoTabla(ars_rs.getString("CAMPO_TABLA"));

		lc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lc_datos;
	}
}
