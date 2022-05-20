package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.RPTPrograma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_RPT_PROGRAMA.
 *
 * @author Kevin Pulido
 */
public class RPTProgramaDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_RPT_PROGRAMA (ID_REPORTE, NOMBRE, SQL_EJECUCION, FECHA_ULTIMA_EJECUCION, RESULTADO, RESULTADO_DETALLE, FECHA_PROXIMA_EJECUCION, HORA_PROGRAMADA, MINUTOS_PROGRAMADA, TIPO_ARCHIVO, ID_PERIODICIDAD, ID_EXPEDIENTE, ID_TIPO_DOCUMENTAL, OBSERVACIONES, SOLICITA_CTA, ACTIVO, PROCEDIMIENTO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_RPT_PROGRAMA SET NOMBRE = ?, SQL_EJECUCION = ?, FECHA_ULTIMA_EJECUCION = ?, RESULTADO = ?, RESULTADO_DETALLE = ?, FECHA_PROXIMA_EJECUCION = ?, HORA_PROGRAMADA = ?, MINUTOS_PROGRAMADA = ?, TIPO_ARCHIVO = ?, ID_PERIODICIDAD = ?, ID_EXPEDIENTE = ?, ID_TIPO_DOCUMENTAL = ?, OBSERVACIONES = ?, SOLICITA_CTA = ?, ACTIVO = ?, PROCEDIMIENTO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_REPORTE = ?";

	/** Constante cs_FIND_ALL ACTIVE. */
	private static final String cs_FIND_ALL_ACTIVE = "SELECT * FROM SDB_PGN_RPT_PROGRAMA WHERE ACTIVO = 'S'";

	/** Constante cs_FIND_ALL_PERIODICIDAD. */
	private static final String cs_FIND_ALL_PERIODICIDAD = "SELECT P.NOMBRE AS NOMBRE_PERIODICIDAD, R.* FROM SDB_PGN_RPT_PROGRAMA R INNER JOIN SDB_PGN_PERIODICIDAD P ON R.ID_PERIODICIDAD = P.ID_PERIODICIDAD";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_RPT_PROGRAMA WHERE ID_REPORTE = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_SDB_PGN_RPT_PROGRAMA_ID_REPORTE.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RPTPrograma> findAll()
	    throws B2BException
	{
		Collection<RPTPrograma> lcp_datos;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcp_datos     = new ArrayList<RPTPrograma>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVE);
			lrs_rs     = lps_ps.executeQuery();

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
	 * Find all periodicidad.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RPTPrograma> findAllPeriodicidad()
	    throws B2BException
	{
		Collection<RPTPrograma> lcp_datos;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lcp_datos     = new ArrayList<RPTPrograma>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_PERIODICIDAD);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcp_datos.add(getObjetFromResultSet2(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllPeriodicidad", lse_e);

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
	public RPTPrograma findById(String as_param)
	    throws B2BException
	{
		RPTPrograma lp_return;
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
	 * @param arptp_param de arptp param
	 * @param ab_insert de ab insert
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RPTPrograma insertOrUpdate(RPTPrograma arptp_param, boolean ab_insert)
	    throws B2BException
	{
		RPTPrograma lrptp_result;
		long        ll_secuencia;

		ll_secuencia     = 0;
		lrptp_result     = null;

		if(arptp_param != null)
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

				lps_ps.setString(li_column++, arptp_param.getNombre());
				lps_ps.setString(li_column++, arptp_param.getSqlEjecucion());
				setDate(lps_ps, arptp_param.getFechaUltimaEjecucion(), li_column++);
				lps_ps.setLong(li_column++, arptp_param.getResultado());
				lps_ps.setString(li_column++, arptp_param.getResultadoDetalle());
				setDate(lps_ps, arptp_param.getFechaProximaEjecucion(), li_column++);
				lps_ps.setLong(li_column++, arptp_param.getHoraProgramada());
				lps_ps.setLong(li_column++, arptp_param.getMinutosProgramada());
				lps_ps.setString(li_column++, arptp_param.getTipoArchivo());
				lps_ps.setString(li_column++, arptp_param.getIdPeriodicidad());
				lps_ps.setString(li_column++, arptp_param.getExpedientes());
				lps_ps.setString(li_column++, arptp_param.getTiposDocumentales());
				lps_ps.setString(li_column++, arptp_param.getObservaciones());
				lps_ps.setString(li_column++, arptp_param.getSolicitaCTA());
				lps_ps.setString(li_column++, arptp_param.getActivo());
				lps_ps.setString(li_column++, arptp_param.getProcedimiento());

				if(ab_insert)
				{
					lps_ps.setString(li_column++, arptp_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, arptp_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, arptp_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, arptp_param.getIpModificacion());
					lps_ps.setString(li_column++, arptp_param.getIdReporte());
				}

				lrptp_result = arptp_param;
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

		return lrptp_result;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private RPTPrograma getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		RPTPrograma lrptp_datos;

		lrptp_datos = new RPTPrograma();

		lrptp_datos.setIdReporte(ars_rs.getString("ID_REPORTE"));
		lrptp_datos.setNombre(ars_rs.getString("NOMBRE"));
		lrptp_datos.setSqlEjecucion(ars_rs.getString("SQL_EJECUCION"));
		lrptp_datos.setFechaUltimaEjecucion(ars_rs.getDate("FECHA_ULTIMA_EJECUCION"));
		lrptp_datos.setResultado(ars_rs.getLong("RESULTADO"));
		lrptp_datos.setResultadoDetalle(ars_rs.getString("RESULTADO_DETALLE"));
		lrptp_datos.setFechaProximaEjecucion(ars_rs.getDate("FECHA_PROXIMA_EJECUCION"));
		lrptp_datos.setHoraProgramada(ars_rs.getLong("HORA_PROGRAMADA"));
		lrptp_datos.setMinutosProgramada(ars_rs.getLong("MINUTOS_PROGRAMADA"));
		lrptp_datos.setTipoArchivo(ars_rs.getString("TIPO_ARCHIVO"));
		lrptp_datos.setIdPeriodicidad(ars_rs.getString("ID_PERIODICIDAD"));
		lrptp_datos.setExpedientes(ars_rs.getString("ID_EXPEDIENTE"));
		lrptp_datos.setTiposDocumentales(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lrptp_datos.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lrptp_datos.setSolicitaCTA(ars_rs.getString("SOLICITA_CTA"));
		lrptp_datos.setActivo(ars_rs.getString("ACTIVO"));
		lrptp_datos.setProcedimiento(ars_rs.getString("PROCEDIMIENTO"));

		fillAuditoria(ars_rs, lrptp_datos);

		return lrptp_datos;
	}

	/**
	 * Retorna Objeto o variable de valor objet from result set 2.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set 2
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private RPTPrograma getObjetFromResultSet2(ResultSet ars_rs)
	    throws SQLException
	{
		RPTPrograma lrptp_datos;

		lrptp_datos = new RPTPrograma();

		if(ars_rs != null)
		{
			lrptp_datos = getObjetFromResultSet(ars_rs);
			lrptp_datos.setNombrePeriodicidad(ars_rs.getString("NOMBRE_PERIODICIDAD"));

			{
				StringBuilder lsb_horaProceso;

				lsb_horaProceso = new StringBuilder();

				lsb_horaProceso.append(lrptp_datos.getHoraProgramada());
				lsb_horaProceso.append(":");

				{
					Long   ll_minutoProgramacion;
					String ls_minutoProgramacion;

					ll_minutoProgramacion = NumericUtils.getLongWrapper(lrptp_datos.getMinutosProgramada());

					if((ll_minutoProgramacion != null) && (ll_minutoProgramacion.compareTo(Long.valueOf(0L)) == 0L))
						ls_minutoProgramacion = "00";
					else
						ls_minutoProgramacion = StringUtils.getString(lrptp_datos.getMinutosProgramada());

					lsb_horaProceso.append(ls_minutoProgramacion);
				}

				lrptp_datos.setHoraProceso(lsb_horaProceso.toString());
			}
		}

		return lrptp_datos;
	}
}
