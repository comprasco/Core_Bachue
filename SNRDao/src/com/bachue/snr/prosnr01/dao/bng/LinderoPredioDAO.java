package com.bachue.snr.prosnr01.dao.bng;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades de LinderoPredioDAO.
 *
 * @author Julian Vaca
 */
public class LinderoPredioDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_LINDERO_PREDIO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA = "SELECT max(ID_LINDERO_PREDIO) ID_LINDERO_PREDIO "
		+ "FROM SDB_BNG_LINDERO_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/**
	 * Retorna el valor del objeto de tipo LinderoPredio filtrado por el id circulo y id matricula
	 *
	 * @param alp_param correspondiente al valor del tipo de objeto LinderoPredio con la informacion a consultar
	 * @return devuelve el valor del objeto lindero predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LinderoPredio
	 */
	public LinderoPredio findById(LinderoPredio alp_param)
	    throws B2BException
	{
		return (alp_param != null)
		? findById(alp_param.getIdCirculo(), NumericUtils.getLong(alp_param.getIdMatricula())) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo LinderoPredio filtrado por el id circulo y id matricula
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String con la informacion a consultar
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long con la informacion a consultar
	 * @return devuelve el valor del objeto lindero predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see LinderoPredio
	 */
	public LinderoPredio findById(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		LinderoPredio llp_linderoPredio;

		llp_linderoPredio = null;

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCirculo);
				lps_ps.setLong(2, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					llp_linderoPredio = getObjetFromResultSet(lrs_rs);
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

		return llp_linderoPredio;
	}

	/**
	 * Retorna el valor del maximo id de lindero predio
	 *
	 * @param at_param correspondiente al valor del tipo de objeto LinderoPredio
	 * @return devuelve el valor del objeto string
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String findMaxIdLindero(LinderoPredio at_param)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		String            ls_idLindero;

		lps_ps           = null;
		lrs_rs           = null;
		ls_idLindero     = null;

		try
		{
			if(at_param != null)
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, at_param.getIdCirculo());
				setLong(lps_ps, at_param.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_idLindero = lrs_rs.getString(1);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMaxIdLindero", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_idLindero;
	}

	/**
	 * Retorna el valor de LinderoPredio
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de LinderoPredio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see LinderoPredio
	 */
	private LinderoPredio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		LinderoPredio ldp_direccionPredio;

		ldp_direccionPredio = new LinderoPredio();

		ldp_direccionPredio.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldp_direccionPredio.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldp_direccionPredio.setLindero(ars_rs.getString("LINDERO"));
		ldp_direccionPredio.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ldp_direccionPredio.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return ldp_direccionPredio;
	}
}
