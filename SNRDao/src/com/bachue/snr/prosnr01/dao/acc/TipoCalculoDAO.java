package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoCalculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TIPO_CALCULO
 *
 * @author garias
 */
public class TipoCalculoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_CALCULO, NOMBRE, FECHA_CREACION FROM SDB_ACC_TIPO_CALCULO WHERE ID_TIPO_CALCULO=?";

	/**
	 * Retorna el valor del objeto de TipoCalculo.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoCalculo
	 * @return devuelve el valor de TipoCalculo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoCalculo
	 */
	public TipoCalculo findById(TipoCalculo at_param)
	    throws B2BException
	{
		TipoCalculo       ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoCalculo());
			lps_ps.setString(2, at_param.getNombre());

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
	 * Retorna el valor de TipoCalculo
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoCalculo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoCalculo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoCalculo ls_tipoActo;

		ls_tipoActo = new TipoCalculo();

		ls_tipoActo.setIdTipoCalculo(ars_rs.getString("ID_TIPO_CALCULO"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ls_tipoActo;
	}
}
