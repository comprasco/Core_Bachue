package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.DRXCTotalBCO;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_DRXC_TOTAL_BCO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class DRXCTotalBCODAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_CONSULTA_BY_PERIODO_CORTE. */
	private static final String cs_CONSULTA_BY_PERIODO_CORTE = "SELECT SUM(TOTAL_BCO_CON_AFECTACION_CON_PRESTACION) TOTAL_BCO_CON_AFECTACION_CON_PRESTACION, SUM(TOTAL_BCO_SIN_AFECTACION_SIN_PRESTACION) TOTAL_BCO_SIN_AFECTACION_SIN_PRESTACION, SUM(TOTAL_BCO_MONTO) TOTAL_BCO_MONTO FROM SDB_CON_DRXC_TOTAL_BCO WHERE ID_PERIODO_CORTE = ?";

	/** Constante cs_PROC_CREA_DRXC_TOTAL_CTA. */
	private static final String cs_PROC_CREA_DRXC_TOTAL_BCO = "BEGIN PKG_CONCILIACION_INGRESOS.PROC_CREA_DRXC_TOTAL_BCO (?,?,?,?,?); END;";

	/**
	 * Find all by periodo.
	 *
	 * @param as_periodo the as periodo
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<DRXCTotalBCO> findAllByPeriodo(String as_periodo)
	    throws B2BException
	{
		Collection<DRXCTotalBCO> lcdtb_return;
		lcdtb_return = new ArrayList<DRXCTotalBCO>();

		if(StringUtils.isValidString(as_periodo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_CONSULTA_BY_PERIODO_CORTE);

				lps_ps.setString(1, as_periodo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdtb_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByPeriodo", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcdtb_return;
	}

	/**
	 * Proc crear DRXC total BCO.
	 *
	 * @param as_idPeriodoCorte the as id periodo corte
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public void procCrearDRXCTotalBCO(String as_idPeriodoCorte, String as_usuario, String as_ip)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		if(
		    StringUtils.isValidString(as_idPeriodoCorte) && StringUtils.isValidString(as_usuario)
			    && StringUtils.isValidString(as_ip)
		)
		{
			try
			{
				int    li_i;
				int    li_return;
				String ls_error;

				li_i          = 1;
				li_return     = 0;
				lcs_cs        = getConnection().prepareCall(cs_PROC_CREA_DRXC_TOTAL_BCO);

				lcs_cs.setString(li_i++, as_idPeriodoCorte);
				lcs_cs.setString(li_i++, as_usuario);
				lcs_cs.setString(li_i++, as_ip);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_CREA_DRXC_TOTAL_BCO: " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procCrearDRXCTotalBCO", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private DRXCTotalBCO getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DRXCTotalBCO ldtbco_datos;
		ldtbco_datos = new DRXCTotalBCO();

		ldtbco_datos.setTotalAfectacionPrestacion(getDouble(ars_rs, "TOTAL_BCO_CON_AFECTACION_CON_PRESTACION"));
		ldtbco_datos.setTotalSinAfectacionSinPrestacion(getDouble(ars_rs, "TOTAL_BCO_SIN_AFECTACION_SIN_PRESTACION"));
		ldtbco_datos.setTotalBcoMonto(getDouble(ars_rs, "TOTAL_BCO_MONTO"));

		return ldtbco_datos;
	}
}
