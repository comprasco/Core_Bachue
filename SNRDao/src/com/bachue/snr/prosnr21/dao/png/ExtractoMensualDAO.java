package com.bachue.snr.prosnr21.dao.png;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.b2bsg.common.exception.B2BException;
import com.b2bsg.common.util.StringUtils;
import com.bachue.snr.prosnr21.model.pgn.ExtractoMensual;


/**
 * Clase encargada de las interacciones con base de datos para la tabla SDB_CON_EXTRACTO_MENSUAL del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoMensualDAO extends com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao
{
	/** Constante cs_FIND_PERIODO_BY_BANCO_CUENTA. */
	private static final String cs_FIND_PERIODO_BY_BANCO_CUENTA = "SELECT EM.PERIODO FROM SDB_CON_EXTRACTO_MENSUAL EM WHERE EM.ID_ENTIDAD_RECAUDADORA = ? AND EM.ID_CUENTA = ?";

	/** Constante cs_FIND_EXTRACTO_MENSUAL. */
	private static final String cs_FIND_EXTRACTO_MENSUAL = "SELECT * FROM SDB_CON_EXTRACTO_MENSUAL WHERE ID_ENTIDAD_RECAUDADORA = ? AND ID_CUENTA = ? AND PERIODO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_EXTRACTO_MENSUAL SET NUMERO_EXTRACTO_SIIF = ?, FECHA_EXTRACTO_SIIF = ?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=? WHERE NUMERO_EXTRACTO_MENSUAL =? AND ID_CUENTA = ?";

	/**
	 * Buscar extracto mensual.
	 *
	 * @param aem_objExtractoMensual de aem obj extracto mensual
	 * @return el valor de extracto mensual
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ExtractoMensual buscarExtractoMensual(ExtractoMensual aem_objExtractoMensual)
	    throws B2BException
	{
		ExtractoMensual lem_return;
		lem_return = null;

		if(aem_objExtractoMensual != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_EXTRACTO_MENSUAL);
				lps_ps.setString(li_column++, aem_objExtractoMensual.getIdEntidadRecaudadora());
				lps_ps.setString(li_column++, aem_objExtractoMensual.getIdCuenta());
				lps_ps.setString(li_column++, aem_objExtractoMensual.getPeriodo());
				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lem_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarExtractoMensual", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lem_return;
	}

	/**
	 * Buscar periodo por banco cuenta.
	 *
	 * @param as_idEntidadRecaudadora de as id entidad recaudadora
	 * @param as_idCuenta de as id cuenta
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ExtractoMensual> buscarPeriodoPorBancoCuenta(String as_idEntidadRecaudadora, String as_idCuenta)
	    throws B2BException
	{
		Collection<ExtractoMensual> lcem_return;

		lcem_return = new ArrayList<ExtractoMensual>();

		if(StringUtils.isValidString(as_idEntidadRecaudadora) && StringUtils.isValidString(as_idCuenta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_PERIODO_BY_BANCO_CUENTA);
				lps_ps.setString(li_column++, as_idEntidadRecaudadora);
				lps_ps.setString(li_column++, as_idCuenta);
				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcem_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarPeriodoPorBancoCuenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(lcem_return.isEmpty())
				lcem_return = null;
		}

		return lcem_return;
	}

	/**
	 * Update.
	 *
	 * @param aem_extractoMensual de aem extracto mensual
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ExtractoMensual aem_extractoMensual)
	    throws B2BException
	{
		if(aem_extractoMensual != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_ps            = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aem_extractoMensual.getNumeroExtractoSiif());
				setDate(lps_ps, aem_extractoMensual.getFechaExtractoSiif(), li_column++);
				lps_ps.setString(li_column++, aem_extractoMensual.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aem_extractoMensual.getIpModificacion());
				lps_ps.setString(li_column++, aem_extractoMensual.getNumeroExtractoMensual());
				lps_ps.setString(li_column++, aem_extractoMensual.getIdCuenta());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
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
	private ExtractoMensual getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ExtractoMensual lem_datos;
		lem_datos = new ExtractoMensual();

		lem_datos.setNumeroExtractoMensual(ars_rs.getString("NUMERO_EXTRACTO_MENSUAL"));
		lem_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lem_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lem_datos.setPeriodo(ars_rs.getString("PERIODO"));
		lem_datos.setFechaInicial(ars_rs.getDate("FECHA_INICIAL"));
		lem_datos.setFechaFinal(ars_rs.getDate("FECHA_FINAL"));
		lem_datos.setSaldoInicial(getDouble(ars_rs, "SALDO_INICIAL"));
		lem_datos.setCreditos(getDouble(ars_rs, "CREDITOS"));
		lem_datos.setDebitos(getDouble(ars_rs, "DEBITOS"));
		lem_datos.setSaldoFinal(getDouble(ars_rs, "SALDO_FINAL"));
		lem_datos.setNumeroExtractoSiif(ars_rs.getString("NUMERO_EXTRACTO_SIIF"));
		lem_datos.setFechaExtractoSiif(ars_rs.getDate("FECHA_EXTRACTO_SIIF"));

		fillAuditoria(ars_rs, lem_datos);

		return lem_datos;
	}
}
