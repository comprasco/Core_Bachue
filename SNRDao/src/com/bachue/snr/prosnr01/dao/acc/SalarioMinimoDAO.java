package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.SalarioMinimo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_SALARIO_MINIMO
 *
 * @author asantos
 */
public class SalarioMinimoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_SALARIO_MINIMO";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_SALARIO_MINIMO SET  VIGENCIA=?, VALOR_SALARIO = ? ,ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_SALARIO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_SALARIO_MINIMO(ID_SALARIO, VIGENCIA, VALOR_SALARIO,ID_USUARIO_CREACION, FECHA_CREACION,IP_CREACION) VALUES(?, ?, ?, ?,SYSTIMESTAMP,?)";

	/**
	 * Retorna el valor del objeto de SalarioMinimo.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SalarioMinimo
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de SalarioMinimo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SalarioMinimo
	 */
	public SalarioMinimo findById(SalarioMinimo at_param, boolean ab_b)
	    throws B2BException
	{
		SalarioMinimo             ls_object;
		Collection<SalarioMinimo> lcsm_sm;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder              lsb_sb;
		int                       li_column;

		li_column     = 1;
		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;
		lsb_sb        = new StringBuilder();
		lcsm_sm       = new ArrayList<SalarioMinimo>();
		ls_object     = new SalarioMinimo();

		try
		{
			lsb_sb.append(cs_FIND_BY_ID);

			if(at_param != null)
			{
				if(ab_b)
					lsb_sb.append(" WHERE ID_SALARIO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(ab_b)
					lps_ps.setString(li_column++, at_param.getIdSalario());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_sm.add(getObjetFromResultSet(lrs_rs));

				if(CollectionUtils.isValidCollection(lcsm_sm))
					ls_object.setInfoAll(lcsm_sm);
			}
			else
			{
				lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsm_sm.add(getObjetFromResultSet(lrs_rs));

				if(CollectionUtils.isValidCollection(lcsm_sm))
					ls_object.setInfoAll(lcsm_sm);
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

		return ls_object;
	}

	/**
	 * Inserta o actualiza el registro
	 *
	 * @param at_param correspondiente al valor del tipo de objeto SalarioMinimo
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(SalarioMinimo at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, at_param.getIdSalario());

				lps_ps.setString(li_column++, at_param.getVigencia());
				lps_ps.setBigDecimal(li_column++, at_param.getValorSalario());
				lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, at_param.getIpCreacion());

				if(!ab_query)
					lps_ps.setString(li_column++, at_param.getIdSalario());

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
	 * Retorna el valor de SalarioMinimo
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SalarioMinimo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SalarioMinimo getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SalarioMinimo ls_tipoActo;

		ls_tipoActo = new SalarioMinimo();

		ls_tipoActo.setIdSalario(ars_rs.getString("ID_SALARIO"));
		ls_tipoActo.setVigencia(ars_rs.getString("VIGENCIA"));
		ls_tipoActo.setValorSalario(ars_rs.getBigDecimal("VALOR_SALARIO"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));

		return ls_tipoActo;
	}
}
