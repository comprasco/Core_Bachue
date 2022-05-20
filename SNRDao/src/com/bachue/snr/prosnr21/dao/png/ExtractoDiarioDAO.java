package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.ExtractoDiario;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_EXTRACTO_DIARIO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoDiarioDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_FIND_BY_CUENTA_BANCO_FECHA. */
	private static final String cs_FIND_BY_CUENTA_BANCO_FECHA = "SELECT * FROM SDB_CON_EXTRACTO_DIARIO WHERE ID_CUENTA = ? AND ID_ENTIDAD_RECAUDADORA=? AND TO_DATE(FECHA_MOVIMIENTO, 'DD/MM/YYYY') = TO_DATE(?, 'DD/MM/YYYY')";

	/** Constante cs_PROC_CREA_EXTRACTO_DIARIO. */
	private static final String cs_PROC_CREA_EXTRACTO_DIARIO = "BEGIN PKG_REPORTES.PROC_CREA_EXTRACTO_DIARIO (?,?,?,?,?,?); END;";

	/**
	 * Find by banco cuenta fecha.
	 *
	 * @param aed_datos de aed datos
	 * @return el valor de extracto diario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ExtractoDiario findByBancoCuentaFecha(ExtractoDiario aed_datos)
	    throws B2BException
	{
		ExtractoDiario led_return;
		StringBuilder  lsb_sb;

		lsb_sb         = new StringBuilder(cs_FIND_BY_CUENTA_BANCO_FECHA);
		led_return     = null;

		if(aed_datos != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(lsb_sb.toString());
				lps_ps.setString(li_column++, aed_datos.getIdCuenta());
				lps_ps.setString(li_column++, aed_datos.getIdEntidadRecaudadora());
				setDate(lps_ps, aed_datos.getFechaMovimiento(), li_column++);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					led_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByBancoCuentaFecha", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return led_return;
	}

	/**
	 * Proc crea extracto diario.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @param as_idCorte the as id corte
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public void procCreaExtractoDiario(String as_idCuenta, String as_idCorte, String as_usuario, String as_ip)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		if(
		    StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_idCorte)
			    && StringUtils.isValidString(as_usuario) && StringUtils.isValidString(as_ip)
		)
		{
			try
			{
				int    li_i;
				int    li_return;
				String ls_error;

				li_i          = 1;
				li_return     = 0;
				lcs_cs        = getConnection().prepareCall(cs_PROC_CREA_EXTRACTO_DIARIO);

				lcs_cs.setString(li_i++, as_idCuenta);
				lcs_cs.setString(li_i++, as_idCorte);
				lcs_cs.setString(li_i++, as_usuario);
				lcs_cs.setString(li_i++, as_ip);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(5);
				ls_error      = lcs_cs.getString(6);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento cs_PROC_CREA_EXTRACTO_DIARIO: " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procCreaExtractoDiario", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lcs_cs);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ExtractoDiario getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ExtractoDiario led_datos;
		led_datos = new ExtractoDiario();

		led_datos.setNumExtractoDiario(ars_rs.getString("NUMERO_EXTRACTO_DIARIO"));
		led_datos.setNumExtractoSIIF(ars_rs.getString("NUMERO_EXTRACTO_SIIF"));
		led_datos.setFechaExtractoSIIF(ars_rs.getDate("FECHA_EXTRACTO_SIIF"));
		led_datos.setFechaMovimiento(ars_rs.getDate("FECHA_MOVIMIENTO"));
		led_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		led_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		led_datos.setSaldoinicial(getDouble(ars_rs, "SALDO_INICIAL"));
		led_datos.setTotalDebito(getDouble(ars_rs, "TOTAL_DEBITO"));
		led_datos.setTotalCredito(getDouble(ars_rs, "TOTAL_CREDITO"));
		led_datos.setSaldoFinal(getDouble(ars_rs, "SALDO_FINAL"));
		led_datos.setIngresosConAfecConPrest(getDouble(ars_rs, "INGRESOS_CON_AFEC_CON_PREST"));
		led_datos.setIngresosSinAfecSinPrest(getDouble(ars_rs, "INGRESOS_SIN_AFEC_SIN_PREST"));
		led_datos.setTotalTransacciones(getDouble(ars_rs, "TOTAL_TRANSACCIONES"));

		fillAuditoria(ars_rs, led_datos);

		return led_datos;
	}
}
