package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_DRXC_TOTAL_CTA del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class DRXCTotalCTADAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_CONSULTA_BY_PERIODO_CORTE. */
	private static final String cs_CONSULTA_BY_PERIODO_CORTE = "SELECT * FROM SDB_CON_DRXC_TOTAL_CTA WHERE ID_PERIODO_CORTE = ?";

	/** Constante cs_UPDATE_NUMERO_AND_FECHA_SIIF. */
	private static final String cs_UPDATE_NUMERO_AND_FECHA_SIIF = "UPDATE SDB_CON_DRXC_TOTAL_CTA SET NUMERO_SIIF=?, FECHA_SIIF=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE ID_DRXC_TOTAL_CTA =? AND ID_ENTIDAD_RECAUDADORA=? AND ID_CUENTA=?";

	/** Constante cs_PROC_CREA_DRXC_TOTAL_CTA. */
	private static final String cs_PROC_CREA_DRXC_TOTAL_CTA = "BEGIN PKG_CONCILIACION_INGRESOS.PROC_CREA_DRXC_TOTAL_CTA (?,?,?,?,?,?,?); END;";

	/**
	 * Fill numero fecha SIIF.
	 *
	 * @param ldtc_obj the ldtc obj
	 * @throws B2BException the b 2 B exception
	 */
	public void fillNumeroFechaSIIF(DRXCTotalCTA ldtc_obj)
	    throws B2BException
	{
		if(ldtc_obj != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE_NUMERO_AND_FECHA_SIIF);

				lps_ps.setString(li_column++, ldtc_obj.getNumeroSIIF());
				setDate(lps_ps, ldtc_obj.getFechaSIIF(), li_column++);
				lps_ps.setString(li_column++, ldtc_obj.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ldtc_obj.getIpModificacion());
				lps_ps.setString(li_column++, ldtc_obj.getIdDrxcTotalCta());
				lps_ps.setString(li_column++, ldtc_obj.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, ldtc_obj.getIdCuenta());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "fillNumeroFechaSIIF(DRXCTotalCTA)", lse_e);
				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Find all by periodo.
	 *
	 * @param as_periodo de as periodo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DRXCTotalCTA> findAllByPeriodo(String as_periodo)
	    throws B2BException
	{
		Collection<DRXCTotalCTA> lcdtc_return;
		lcdtc_return = new ArrayList<DRXCTotalCTA>();

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
					lcdtc_return.add(getObjetFromResultSet(lrs_rs));
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

		return lcdtc_return;
	}

	/**
	 * Proc crear DRXC total CTA.
	 *
	 * @param as_idEntidad the as id entidad
	 * @param as_idPeriodoCorte the as id periodo corte
	 * @param as_idCuenta the as id cuenta
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public void procCrearDRXCTotalCTA(
	    String as_idEntidad, String as_idPeriodoCorte, String as_idCuenta, String as_usuario, String as_ip
	)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		if(
		    StringUtils.isValidString(as_idEntidad) && StringUtils.isValidString(as_idPeriodoCorte)
			    && StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_usuario)
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
				lcs_cs        = getConnection().prepareCall(cs_PROC_CREA_DRXC_TOTAL_CTA);

				lcs_cs.setString(li_i++, as_idEntidad);
				lcs_cs.setString(li_i++, as_idCuenta);
				lcs_cs.setString(li_i++, as_idPeriodoCorte);
				lcs_cs.setString(li_i++, as_usuario);
				lcs_cs.setString(li_i++, as_ip);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(6);
				ls_error      = lcs_cs.getString(7);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento CREA_DRXC_TOTAL_CTA: " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procCrearDRXCTotalCTA", lse_e);
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
	private DRXCTotalCTA getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		DRXCTotalCTA ldtcta_datos;
		ldtcta_datos = new DRXCTotalCTA();

		ldtcta_datos.setIdDrxcTotalCta(ars_rs.getString("ID_DRXC_TOTAL_CTA"));
		ldtcta_datos.setIdDrxcTotalBco(ars_rs.getString("ID_DRXC_TOTAL_BCO"));
		ldtcta_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		ldtcta_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		ldtcta_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		ldtcta_datos.setConsecutivoDrxc(ars_rs.getString("CONSECUTIVO_DRXC"));
		ldtcta_datos.setMonto(getDouble(ars_rs, "MONTO"));
		ldtcta_datos.setConciliado(ars_rs.getString("CONCILIADO"));
		ldtcta_datos.setAfectacion(ars_rs.getString("AFECTACION"));
		ldtcta_datos.setPrestacion(ars_rs.getString("PRESTACION"));
		ldtcta_datos.setMontoConAfectacionPrestacion(getDouble(ars_rs, "MONTO_CON_AFECTACION_CON_PRESTACION"));
		ldtcta_datos.setMontoSinAfectacionSinPrestacion(getDouble(ars_rs, "MONTO_SIN_AFECTACION_SIN_PRESTACION"));
		ldtcta_datos.setTotalMonto(getDouble(ars_rs, "TOTAL_MONTO"));
		ldtcta_datos.setNumeroSIIF(ars_rs.getString("NUMERO_SIIF"));
		ldtcta_datos.setFechaSIIF(ars_rs.getDate("FECHA_SIIF"));

		fillAuditoria(ars_rs, ldtcta_datos);

		return ldtcta_datos;
	}
}
