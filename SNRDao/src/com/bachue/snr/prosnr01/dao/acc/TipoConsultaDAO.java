package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TIPO_CONSULTA
 *
 * @author garias
 */
public class TipoConsultaDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_CONSULTA, NOMBRE, FECHA_CREACION FROM SDB_ACC_TIPO_CONSULTA WHERE ID_TIPO_CONSULTA=?";

	/**
	 * Retorna el valor del objeto de TipoConsulta filtrado por ID.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoConsulta
	 * @return devuelve el valor de TipoConsulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoConsulta
	 */
	public TipoConsulta findById(TipoConsulta at_param)
	    throws B2BException
	{
		TipoConsulta      ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, at_param.getIdTipoConsulta());
			lps_ps.setString(li_column++, at_param.getNombre());

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
	 * Retorna el valor de TipoConsulta
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoConsulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoConsulta getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoConsulta ls_tipoActo;

		ls_tipoActo = new TipoConsulta();

		ls_tipoActo.setIdTipoConsulta(ars_rs.getString("ID_TIPO_CONSULTA"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ls_tipoActo;
	}
}
