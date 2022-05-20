package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.Periodicidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_PERIODICIDAD.
 *
 * @author Kevin Pulido
 */
public class PeriodicidadDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PERIODICIDAD (ID_PERIODICIDAD, NOMBRE, UNIDAD_TIEMPO, CANTIDAD, ACTIVO,ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PERIODICIDAD SET NOMBRE = ?, UNIDAD_TIEMPO = ? ,CANTIDAD = ?, ACTIVO = ?,ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_PERIODICIDAD = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PERIODICIDAD";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PERIODICIDAD WHERE ID_PERIODICIDAD = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_SDB_PGN_PERIODICIDAD_ID_PERIODICIDAD.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Periodicidad> findAll()
	    throws B2BException
	{
		Collection<Periodicidad> lcp_datos;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lcp_datos     = new ArrayList<Periodicidad>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcp_datos))
			lcp_datos = null;

		return lcp_datos;
	}

	/**
	 * Find by id.
	 *
	 * @param as_param de as param
	 * @return el valor de periodicidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Periodicidad findById(String as_param)
	    throws B2BException
	{
		Periodicidad lp_return;
		lp_return = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_return = getObjetFromResultSet(lrs_rs);
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

		return lp_return;
	}

	/**
	 * Insert or update.
	 *
	 * @param ap_param de ap param
	 * @param ab_insert de ab insert
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertOrUpdate(Periodicidad ap_param, boolean ab_insert)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(ap_param != null)
		{
			Connection        lc_connection;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(ab_insert ? cs_INSERT : cs_UPDATE);

				if(ab_insert)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				lps_ps.setString(li_column++, ap_param.getNombre());
				lps_ps.setString(li_column++, ap_param.getUnidadTiempo());
				lps_ps.setLong(li_column++, ap_param.getCantidad());
				lps_ps.setString(li_column++, ap_param.getActivo());

				if(ab_insert)
				{
					lps_ps.setString(li_column++, ap_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ap_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ap_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ap_param.getIpModificacion());
					lps_ps.setString(li_column++, ap_param.getIdPeriodicidad());
				}

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
				close(lps_secuencia);
				close(lrs_rs);
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
	private Periodicidad getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Periodicidad lp_datos;

		lp_datos = new Periodicidad();

		lp_datos.setIdPeriodicidad(ars_rs.getString("ID_PERIODICIDAD"));
		lp_datos.setNombre(ars_rs.getString("NOMBRE"));
		lp_datos.setUnidadTiempo(ars_rs.getString("UNIDAD_TIEMPO"));
		lp_datos.setCantidad(ars_rs.getLong("CANTIDAD"));
		lp_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lp_datos);

		return lp_datos;
	}
}
