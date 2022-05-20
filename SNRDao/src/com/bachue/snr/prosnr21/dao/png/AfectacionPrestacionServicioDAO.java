package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.model.pgn.AfectacionPrestacionServicio;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_EXTRACTO_DIARIO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class AfectacionPrestacionServicioDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_FIND_VALOR_TOTAL_BY_DATE_AND_RUBRO. */
	private static final String cs_FIND_VALOR_TOTAL_BY_DATE_AND_RUBRO = "SELECT DES.ID_RUBRO, DES.NUMERO_RUBRO, DES.NOMBRE, NVL(RES.VALOR_TOTAL,0) VALOR_RUBRO FROM SDB_PGN_RUBRO DES LEFT JOIN (SELECT PR.ID_RUBRO, NVL(SUM(IA.VALOR_POR_RUBRO),0) AS VALOR_TOTAL FROM SDB_PGN_RUBRO PR INNER JOIN SDB_PGN_RUBRO_HOMOLOGACION PRH ON PR.ID_RUBRO = PRH.ID_RUBRO INNER JOIN SDB_CON_INGRESO_AFECTACION IA ON PRH.ID_RUBROS_HOMOLOGACION = IA.ID_RUBROS_HOMOLOGACION INNER JOIN SDB_PGN_PERIODO_CORTE PC ON PC.ID_PERIODO_CORTE = IA.ID_PERIODO_CORTE_RPT WHERE TO_DATE(PC.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY') GROUP BY PR.ID_RUBRO UNION SELECT PR.ID_RUBRO, NVL(SUM(IA.VALOR_CONSERV_DOCUMENTAL),0) AS VALOR_TOTAL FROM SDB_PGN_RUBRO PR INNER JOIN SDB_CON_INGRESO_AFECTACION IA ON PR.ID_RUBRO = IA.ID_RUBRO_CONSERVACION_DOCUMENTAL INNER JOIN SDB_PGN_PERIODO_CORTE PC ON PC.ID_PERIODO_CORTE = IA.ID_PERIODO_CORTE_RPT WHERE TO_DATE(PC.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY') GROUP BY PR.ID_RUBRO ) RES ON RES.ID_RUBRO = DES.ID_RUBRO ORDER BY DES.ID_RUBRO ASC";

	/** Constante cs_FIND_VALOR_TOTAL_INGRESOS_SNR_OTRAS_ENTIDADES. */
	private static final String cs_FIND_VALOR_TOTAL_INGRESOS_SNR_OTRAS_ENTIDADES = "SELECT SUM(VALOR_INGRESOS_SNR) AS VALOR_INGRESOS_SNR ,SUM(IA.VALOR_OTRAS_ENTIDADES) AS VALOR_OTRAS_ENTIDADES FROM SDB_PGN_PERIODO_CORTE PC INNER JOIN SDB_CON_INGRESO_AFECTACION IA ON PC.ID_PERIODO_CORTE = IA.ID_PERIODO_CORTE_RPT WHERE TO_DATE(PC.DIA_CORTE, 'DD/MM/YY') = TO_DATE(?, 'DD/MM/YY')";

	/** Constante cs_FIND_DATA_REPORTE. */
	private static final String cs_FIND_DATA_REPORTE = "SELECT TRUNC(FECHA_LIQUIDACION) || ';' || NIR || ';' || NUMERO_REFERENCIA || ';' || ID_TURNO || ';' || ID_CIRCULO_MATRICULA || ';' || ID_MATRICULA || ';' || ID_PROCESO || ';' || NOMBRE_PROCESO || ';' || ID_SUBPROCESO || ';' || NOMBRE_SUBPROCESO || ';' || ID_RUBROS_HOMOLOGACION || ';' || ID_RUBRO_CONSERVACION_DOCUMENTAL || ';' || VALOR_CONSERV_DOCUMENTAL || ';' || VALOR_TOTAL || ';' || VALOR_POR_RUBRO || ';' || VALOR_OTRAS_ENTIDADES || ';' || VALOR_INGRESOS_SNR AS DATA_REPORTE FROM SDB_CON_INGRESO_AFECTACION WHERE ID_ARCHIVO = ?";

	/** Constante cs_PROC_CREA_INGRESO_AFECTACION. */
	private static final String cs_PROC_CREA_INGRESO_AFECTACION = "BEGIN PKG_REPORTES.PROC_CREA_INGRESO_AFECTACION (?,?,?,?,?); END;";

	public Collection<String> findDataById(String as_idArchivo)
	    throws B2BException
	{
		Collection<String> lcs_return;

		lcs_return = new ArrayList<String>(1);

		if(StringUtils.isValidString(as_idArchivo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				Collection<String> lcs_data;

				lps_ps       = getConnection().prepareStatement(cs_FIND_DATA_REPORTE);
				lcs_data     = new ArrayList<String>(1);

				lps_ps.setString(1, as_idArchivo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_data.add(lrs_rs.getString(1));

				if(!lcs_data.isEmpty())
				{
					StringBuilder lsb_titulos;

					lsb_titulos = new StringBuilder();

					lsb_titulos.append("FECHA_LIQUIDACION" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("NIR" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("NUMERO_REFERENCIA" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_TURNO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_CIRCULO_MATRICULA" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_MATRICULA" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_PROCESO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("NOMBRE_PROCESO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_SUBPROCESO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("NOMBRE_SUBPROCESO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ID_RUBROS_HOMOLOGACION" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append(
					    "ID_RUBRO_CONSERVACION_DOCUMENTAL" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT
					);
					lsb_titulos.append("VALOR_CONSERV_DOCUMENTAL" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("VALOR_TOTAL" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("VALOR_POR_RUBRO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("VALOR_OTRAS_ENTIDADES" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("VALOR_INGRESOS_SNR" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);

					lcs_return.add(lsb_titulos.toString());
					lcs_return.addAll(lcs_data);
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
		}

		if(lcs_return.isEmpty())
			lcs_return = null;

		return lcs_return;
	}

	/**
	 * Find valor tolta by date and rubro.
	 *
	 * @param ld_date de ld date
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AfectacionPrestacionServicio> findValorToltaByDateAndRubro(Date ld_date)
	    throws B2BException
	{
		Collection<AfectacionPrestacionServicio> lcaps_return;

		lcaps_return = new ArrayList<AfectacionPrestacionServicio>();

		if(ld_date != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_VALOR_TOTAL_BY_DATE_AND_RUBRO);

				setDate(lps_ps, ld_date, li_column++);
				setDate(lps_ps, ld_date, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaps_return.add(getDataFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findValorToltaByDateAndRubro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(!CollectionUtils.isValidCollection(lcaps_return))
				lcaps_return = null;
		}

		return lcaps_return;
	}

	/**
	 * Find valor total ingresos SNR otras entidades.
	 *
	 * @param ad_date de ld date
	 * @return el valor de afectacion prestacion servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AfectacionPrestacionServicio findValorTotalIngresosSNROtrasEntidades(Date ad_date)
	    throws B2BException
	{
		AfectacionPrestacionServicio lcaps_return;

		lcaps_return = null;

		if(ad_date != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_VALOR_TOTAL_INGRESOS_SNR_OTRAS_ENTIDADES);

				setDate(lps_ps, ad_date, li_column++);
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcaps_return = getDataFromResultSet(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findValorToltaByDateAndRubro", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaps_return;
	}

	/**
	 * Proc crea ingreso afectacion.
	 *
	 * @param as_date the as date
	 * @param as_usuario the as usuario
	 * @param as_ip the as ip
	 * @throws B2BException the b 2 B exception
	 */
	public void procCreaIngresoAfectacion(String as_date, String as_usuario, String as_ip)
	    throws B2BException
	{
		CallableStatement lcs_cs;

		lcs_cs = null;

		if(
		    StringUtils.isValidString(as_date) && StringUtils.isValidString(as_usuario)
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
				lcs_cs        = getConnection().prepareCall(cs_PROC_CREA_INGRESO_AFECTACION);

				lcs_cs.setString(li_i++, as_date);
				lcs_cs.setString(li_i++, as_usuario);
				lcs_cs.setString(li_i++, as_ip);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.SMALLINT);
				lcs_cs.registerOutParameter(li_i++, OracleTypes.VARCHAR);
				lcs_cs.execute();

				li_return     = lcs_cs.getInt(4);
				ls_error      = lcs_cs.getString(5);

				if(li_return < 0)
					throw new B2BException(
					    "Error en procedimiento PROC_CREA_INGRESO_AFECTACION: " + li_return + " :: " + ls_error
					);
			}
			catch(SQLException lse_e)
			{
				logError(this, "procCreaIngresoAfectacion", lse_e);
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
	private AfectacionPrestacionServicio getDataFromResultSet(ResultSet ars_rs, boolean ab_accion)
	    throws SQLException
	{
		AfectacionPrestacionServicio laps_datos;

		laps_datos = new AfectacionPrestacionServicio();

		if(ab_accion)
		{
			laps_datos.setNombreRubro(ars_rs.getString("NOMBRE"));
			laps_datos.setValorTotal(getDouble(ars_rs, "VALOR_RUBRO"));
		}
		else
		{
			laps_datos.setValorOtrasEntidades(getDouble(ars_rs, "VALOR_OTRAS_ENTIDADES"));
			laps_datos.setValorIngresosSnr(getDouble(ars_rs, "VALOR_INGRESOS_SNR"));
		}

		return laps_datos;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AfectacionPrestacionServicio getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AfectacionPrestacionServicio laps_datos;

		laps_datos = new AfectacionPrestacionServicio();

		laps_datos.setIdIngresoAfectacion(ars_rs.getString("ID_INGRESO_AFECTACION"));
		laps_datos.setIdPeriodoCorteRpt(ars_rs.getString("ID_PERIODO_CORTE_RPT"));
		laps_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		laps_datos.setIdRegistro(getDouble(ars_rs, "ID_REGISTRO"));
		laps_datos.setIdItem(getDouble(ars_rs, "ID_ITEM"));
		laps_datos.setFechaLiquidacion(ars_rs.getDate("FECHA_LIQUIDACION"));
		laps_datos.setNir(ars_rs.getString("NIR"));
		laps_datos.setNumeroReferencia(ars_rs.getString("NUMERO_REFERENCIA"));
		laps_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		laps_datos.setIdCirculoMatricula(ars_rs.getString("ID_CIRCULO_MATRICULA"));
		laps_datos.setIdMatricula(ars_rs.getString("ID_MATRICULA"));
		laps_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		laps_datos.setNombreProceso(ars_rs.getString("NOMBRE_PROCESO"));
		laps_datos.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		laps_datos.setNombreSubproceso(ars_rs.getString("NOMBRE_SUBPROCESO"));
		laps_datos.setIdRubro(ars_rs.getString("ID_RUBROS_HOMOLOGACION"));
		laps_datos.setIdRubroConservacionDocumental(ars_rs.getString("ID_RUBRO_CONSERVACION_DOCUMENTAL"));
		laps_datos.setValorConservDocumental(getDouble(ars_rs, "VALOR_CONSERV_DOCUMENTAL"));
		laps_datos.setValorTotal(getDouble(ars_rs, "VALOR_TOTAL"));
		laps_datos.setValorPorRubro(getDouble(ars_rs, "VALOR_POR_RUBRO"));
		laps_datos.setValorOtrasEntidades(getDouble(ars_rs, "VALOR_OTRAS_ENTIDADES"));
		laps_datos.setValorIngresosSnr(getDouble(ars_rs, "VALOR_INGRESOS_SNR"));

		fillAuditoria(ars_rs, laps_datos);

		return laps_datos;
	}
}
