package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.AlertaTramite;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_ALERTA_TRAMITE.
 *
 * @author Jorge Patiño
 */
public class AlertaTramiteDAO extends BaseDAO implements IBase<AlertaTramite>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ALERTA_TRAMITE,DESCRIPCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_ALERTA_TRAMITE WHERE ID_ALERTA_TRAMITE=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ALERTA_TRAMITE SET DESCRIPCION=?,"
		+ " ACTIVO=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP"
		+ " WHERE ID_ALERTA_TRAMITE=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ALERTA_TRAMITE("
		+ "ID_ALERTA_TRAMITE,DESCRIPCION, ACTIVO,IP_CREACION,ID_USUARIO_CREACION,FECHA_CREACION)"
		+ "VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_ALERTA_TRAMITE,DESCRIPCION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_ALERTA_TRAMITE ORDER BY ID_ALERTA_TRAMITE ASC";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_PGN_ALERTA_TRAMITE_ID_ALERTA_TRAMITE.NEXTVAL FROM DUAL";

/**
 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_ALERTA_TRAMITE.
 *
 * @param ab_activo correspondiente al valor del tipo de objeto boolean
 * @return devuelve el valor de Collection
 * @throws B2BException Señala que se ha producido una excepción
 *
 */
	public Collection<AlertaTramite> findAll(boolean ab_activo)
	    throws B2BException
	{
		Collection<AlertaTramite> lcat_cat;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcat_cat     = new ArrayList<AlertaTramite>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			if(ab_activo)
				lsb_consulta.append(" WHERE ESTADO = 'A'");

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcat_cat.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcat_cat.isEmpty())
			lcat_cat = null;

		return lcat_cat;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_ALERTA_TRAMITE
	 * que coíncida con un IdAlertaTramite específico.
	 *
	 * @param aat_at correspondiente al valor del tipo de objeto AlertaTramite
	 * @return devuelve el valor de AlertaTramite
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AlertaTramite
	 */
	@Override
	public AlertaTramite findById(AlertaTramite aat_at)
	    throws B2BException
	{
		AlertaTramite     lat_at;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lat_at     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setBigDecimal(1, aat_at.getIdAlertaTramite());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lat_at = getObjetFromResultSet(lrs_rs);
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

		return lat_at;
	}

	/**
	 * Metodo para obtener los causales de devolución en aprobación filtrados por una etapa especifica
	 * determinada previamente.
	 *
	 * @param aat_at Objeto contenedor de la etapa a usar como filtro
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(AlertaTramite aat_at, boolean ab_query)
	    throws B2BException
	{
		if(aat_at != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_c;

				lc_c          = getConnection();
				li_column     = 1;
				lps_ps        = lc_c.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
					lrs_rs            = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							aat_at.setIdAlertaTramite(((BigDecimal)lo_o));

						lps_ps.setBigDecimal(li_column++, aat_at.getIdAlertaTramite());
					}
				}

				lps_ps.setString(li_column++, aat_at.getDescripcion());
				lps_ps.setString(li_column++, aat_at.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aat_at.getIpCreacion());
					lps_ps.setString(li_column++, aat_at.getIdUsuarioCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aat_at.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aat_at.getIpModificacion());
					lps_ps.setBigDecimal(li_column++, aat_at.getIdAlertaTramite());
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
				close(lrs_rs);
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

/**
 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de Alerta Trámite.
 *
 * @param ars_rs objeto que recoge el resultado de la consulta
 * @return el valor de objet from result set
 * @throws SQLException Señala que se ha producido una excepción
 */
	private AlertaTramite getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTramite lat_at;

		lat_at = new AlertaTramite();

		lat_at.setIdAlertaTramite(ars_rs.getBigDecimal("ID_ALERTA_TRAMITE"));
		lat_at.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lat_at.setActivo(ars_rs.getString("ACTIVO"));
		lat_at.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lat_at.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lat_at.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lat_at.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lat_at.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lat_at.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lat_at;
	}
}
