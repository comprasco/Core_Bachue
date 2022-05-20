package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ConResumen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_INCONSISTENCIA.
 *
 * @author Gabriel Arias
 */
public class ConResumenDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_CON_RESUMEN";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_CUENTA_PERIODO = "SELECT * FROM SDB_CON_RESUMEN WHERE ID_CUENTA=? AND ID_PERIODO_CORTE=?";

	/** Constante cs_FIND_COUNT_BY_CUENTA. */
	private static final String cs_FIND_COUNT_BY_CUENTA = "SELECT COUNT(*) FROM SDB_CON_RESUMEN WHERE ID_CUENTA=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_RESUMEN (ID_CUENTA,ID_PERIODO_CORTE,ESTADO_ACH,ESTADO_ASO,ESTADO_CRPS,ESTADO_MU,FECHA_ESTADO_ACH,FECHA_ESTADO_ASO,FECHA_ESTADO_CRPS,FECHA_ESTADO_MU,ID_ARCHIVO_ACH,ID_ARCHIVO_ASO,ID_ARCHIVO_CRPS,ID_ARCHIVO_MU,VERSION_ARCHIVO_ACH,VERSION_ARCHIVO_ASO,VERSION_ARCHIVO_CRPS,VERSION_ARCHIVO_MU,SALDO_FINAL,SALDO_INICIAL,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_RESUMEN SET ESTADO_ACH=?,ESTADO_ASO=?,ESTADO_CRPS=?,ESTADO_MU=?,FECHA_ESTADO_ACH=?,FECHA_ESTADO_ASO=?,FECHA_ESTADO_CRPS=?,FECHA_ESTADO_MU=?,ID_ARCHIVO_ACH=?,ID_ARCHIVO_ASO=?,ID_ARCHIVO_CRPS=?,ID_ARCHIVO_MU=?,VERSION_ARCHIVO_ACH=?,VERSION_ARCHIVO_ASO=?,VERSION_ARCHIVO_CRPS=?,VERSION_ARCHIVO_MU=?,SALDO_FINAL=?,SALDO_INICIAL=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_CUENTA=? AND ID_PERIODO_CORTE=?";

	/**
	 * Find all.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConResumen> findAll()
	    throws B2BException
	{
		Collection<ConResumen> lccr_return;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lccr_return     = new ArrayList<ConResumen>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_return.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lccr_return))
			lccr_return = null;

		return lccr_return;
	}

	/**
	 * Find by cuenta periodo.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @param as_idPeriodoCorte the as id periodo corte
	 * @return the con resumen
	 * @throws B2BException the b 2 B exception
	 */
	public ConResumen findByCuentaPeriodo(String as_idCuenta, String as_idPeriodoCorte)
	    throws B2BException
	{
		ConResumen lcr_return;

		lcr_return = null;

		if(StringUtils.isValidString(as_idCuenta) && StringUtils.isValidString(as_idPeriodoCorte))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_CUENTA_PERIODO);

				lps_ps.setString(li_column++, as_idCuenta);
				lps_ps.setString(li_column++, as_idPeriodoCorte);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcr_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCuentaPeriodo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcr_return;
	}

	/**
	 * Find count by cuenta.
	 *
	 * @param as_idCuenta the as id cuenta
	 * @return the int
	 * @throws B2BException the b 2 B exception
	 */
	public int findCountByCuenta(String as_idCuenta)
	    throws B2BException
	{
		int li_return;

		li_return = 0;

		if(StringUtils.isValidString(as_idCuenta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_COUNT_BY_CUENTA);

				lps_ps.setString(li_column++, as_idCuenta);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_return = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findCountByCuenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_return;
	}

	/**
	 * Insert.
	 *
	 * @param acr_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void insert(ConResumen acr_param)
	    throws B2BException
	{
		if(acr_param != null)
		{
			PreparedStatement lps_insercion;

			lps_insercion = null;

			try
			{
				int li_column;

				li_column         = 1;
				lps_insercion     = getConnection().prepareStatement(cs_INSERT);

				lps_insercion.setString(li_column++, acr_param.getIdCuenta());
				lps_insercion.setString(li_column++, acr_param.getIdPeriodoCorte());
				lps_insercion.setString(li_column++, acr_param.getEstadoAch());
				lps_insercion.setString(li_column++, acr_param.getEstadoAso());
				lps_insercion.setString(li_column++, acr_param.getEstadoCrps());
				lps_insercion.setString(li_column++, acr_param.getEstadoMu());
				setDate(lps_insercion, acr_param.getFechaEstadoAch(), li_column++);
				setDate(lps_insercion, acr_param.getFechaEstadoAso(), li_column++);
				setDate(lps_insercion, acr_param.getFechaEstadoCrps(), li_column++);
				setDate(lps_insercion, acr_param.getFechaEstadoMu(), li_column++);
				lps_insercion.setString(li_column++, acr_param.getIdArchivoAch());
				lps_insercion.setString(li_column++, acr_param.getIdArchivoAso());
				lps_insercion.setString(li_column++, acr_param.getIdArchivoCrps());
				lps_insercion.setString(li_column++, acr_param.getIdArchivoMu());
				setLong(lps_insercion, acr_param.getVersionArchivoAch(), li_column++);
				setLong(lps_insercion, acr_param.getVersionArchivoAso(), li_column++);
				setLong(lps_insercion, acr_param.getVersionArchivoCrps(), li_column++);
				setLong(lps_insercion, acr_param.getVersionArchivoMu(), li_column++);
				setDouble(lps_insercion, acr_param.getSaldoFinal(), li_column++);
				setDouble(lps_insercion, acr_param.getSaldoInicial(), li_column++);
				lps_insercion.setString(li_column++, acr_param.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, acr_param.getIpCreacion());

				lps_insercion.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param acr_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void update(ConResumen acr_param)
	    throws B2BException
	{
		if(acr_param != null)
		{
			PreparedStatement lps_update;

			lps_update = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_update        = lc_connection.prepareStatement(cs_UPDATE);

				lps_update.setString(li_column++, acr_param.getEstadoAch());
				lps_update.setString(li_column++, acr_param.getEstadoAso());
				lps_update.setString(li_column++, acr_param.getEstadoCrps());
				lps_update.setString(li_column++, acr_param.getEstadoMu());
				setDate(lps_update, acr_param.getFechaEstadoAch(), li_column++);
				setDate(lps_update, acr_param.getFechaEstadoAso(), li_column++);
				setDate(lps_update, acr_param.getFechaEstadoCrps(), li_column++);
				setDate(lps_update, acr_param.getFechaEstadoMu(), li_column++);
				lps_update.setString(li_column++, acr_param.getIdArchivoAch());
				lps_update.setString(li_column++, acr_param.getIdArchivoAso());
				lps_update.setString(li_column++, acr_param.getIdArchivoCrps());
				lps_update.setString(li_column++, acr_param.getIdArchivoMu());
				setLong(lps_update, acr_param.getVersionArchivoAch(), li_column++);
				setLong(lps_update, acr_param.getVersionArchivoAso(), li_column++);
				setLong(lps_update, acr_param.getVersionArchivoCrps(), li_column++);
				setLong(lps_update, acr_param.getVersionArchivoMu(), li_column++);
				setDouble(lps_update, acr_param.getSaldoFinal(), li_column++);
				setDouble(lps_update, acr_param.getSaldoInicial(), li_column++);
				lps_update.setString(li_column++, acr_param.getIdUsuarioModificacion());
				lps_update.setString(li_column++, acr_param.getIpModificacion());
				lps_update.setString(li_column++, acr_param.getIdCuenta());
				lps_update.setString(li_column++, acr_param.getIdPeriodoCorte());

				lps_update.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_update);
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
	private ConResumen getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConResumen lcr_datos;

		lcr_datos = new ConResumen();

		lcr_datos.setIdCuenta(ars_rs.getString("ID_CUENTA"));
		lcr_datos.setIdPeriodoCorte(ars_rs.getString("ID_PERIODO_CORTE"));
		lcr_datos.setEstadoAch(ars_rs.getString("ESTADO_ACH"));
		lcr_datos.setEstadoAso(ars_rs.getString("ESTADO_ASO"));
		lcr_datos.setEstadoCrps(ars_rs.getString("ESTADO_CRPS"));
		lcr_datos.setEstadoMu(ars_rs.getString("ESTADO_MU"));
		lcr_datos.setFechaEstadoAch(ars_rs.getDate("FECHA_ESTADO_ACH"));
		lcr_datos.setFechaEstadoAso(ars_rs.getDate("FECHA_ESTADO_ASO"));
		lcr_datos.setFechaEstadoCrps(ars_rs.getDate("FECHA_ESTADO_CRPS"));
		lcr_datos.setFechaEstadoMu(ars_rs.getDate("FECHA_ESTADO_MU"));
		lcr_datos.setIdArchivoAch(ars_rs.getString("ID_ARCHIVO_ACH"));
		lcr_datos.setIdArchivoAso(ars_rs.getString("ID_ARCHIVO_ASO"));
		lcr_datos.setIdArchivoCrps(ars_rs.getString("ID_ARCHIVO_CRPS"));
		lcr_datos.setIdArchivoMu(ars_rs.getString("ID_ARCHIVO_MU"));
		lcr_datos.setVersionArchivoAch(getLong(ars_rs, "VERSION_ARCHIVO_ACH"));
		lcr_datos.setVersionArchivoAso(getLong(ars_rs, "VERSION_ARCHIVO_ASO"));
		lcr_datos.setVersionArchivoCrps(getLong(ars_rs, "VERSION_ARCHIVO_CRPS"));
		lcr_datos.setVersionArchivoMu(getLong(ars_rs, "VERSION_ARCHIVO_MU"));
		lcr_datos.setSaldoFinal(getDouble(ars_rs, "SALDO_FINAL"));
		lcr_datos.setSaldoInicial(getDouble(ars_rs, "SALDO_INICIAL"));

		fillAuditoria(ars_rs, lcr_datos);

		return lcr_datos;
	}
}
