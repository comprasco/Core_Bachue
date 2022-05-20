package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoActoDerReg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_ACTO_DER_REG
 *
 * @author garias
 */
public class TipoActoDerRegDAO extends BaseDAO implements IBase<TipoActoDerReg>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_TIPO_DERECHO_REG, ID_TIPO_ACTO, FECHA_CREACION "
		+ "FROM SDB_PGN_TIPO_ACTO_DER_REG WHERE ID_TIPO_DERECHO_REG=? AND ID_TIPO_ACTO=?";

	/** {@inheritdoc} */
	@Override
	public TipoActoDerReg findById(TipoActoDerReg at_param)
	    throws B2BException
	{
		TipoActoDerReg    ls_object;
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

			lps_ps.setString(li_column++, at_param.getIdTipoDerechoReg());
			lps_ps.setString(li_column++, at_param.getIdTipoActo());

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

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TipoActoDerReg at_param, boolean ab_query)
	    throws B2BException
	{
	}

	/**
	 * Retorna el valor de TipoActoDerReg
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoActoDerReg
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoActoDerReg getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoActoDerReg ls_tipoActo;

		ls_tipoActo = new TipoActoDerReg();

		ls_tipoActo.setIdTipoDerechoReg(ars_rs.getString("ID_TIPO_DERECHO_REG"));
		ls_tipoActo.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ls_tipoActo;
	}
}
