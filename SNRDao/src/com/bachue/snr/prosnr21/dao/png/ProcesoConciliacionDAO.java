package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.model.pgn.ProcesoConciliacion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las sentencias de la tabla
 * SDB_PGN_PROCESO_CONCILIACION
 *
 * @author arocha
 */
public class ProcesoConciliacionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_PROCESO_CONCILIACION ORDER BY "
		+ "ID_PROCESO_CONCILIACION";

	/** Constante cs_FIND_ALL_CTIVE. */
	private static final String cs_FIND_ALL_ACTIVE = "SELECT * FROM SDB_PGN_PROCESO_CONCILIACION WHERE ACTIVO='S' "
		+ "ORDER BY ID_PROCESO_CONCILIACION";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_PROCESO_CONCILIACION WHERE "
		+ "ID_PROCESO_CONCILIACION=?";

	/** Sentencia SQL para consultar los procesos activos de una entidad recaudadora */
	private static final String cs_FIND_BY_ID_ENTIDAD_RECAUDADORA = "SELECT * FROM SDB_PGN_PROCESO_CONCILIACION WHERE "
		+ "ID_ENTIDAD_RECAUDADORA=? AND ACTIVO='S' ORDER BY ID_PROCESO_CONCILIACION DESC";

	/** Constante cs_FIND_WITH_ENTIDAD_RECAUDADORA. */
	private static final String cs_FIND_WITH_ENTIDAD_RECAUDADORA = "SELECT PPC.*,PERC.NOMBRE_ENTIDAD_RECAUDADORA,PERC.CODIGO_ENTIDAD_RECAUDADORA FROM "
		+ "SDB_PGN_PROCESO_CONCILIACION PPC INNER JOIN SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION PERC ON "
		+ "PPC.ID_ENTIDAD_RECAUDADORA=PERC.ID_ENTIDAD_RECAUDADORA ORDER BY PPC.ID_PROCESO_CONCILIACION";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT "
		+ "SEC_PGN_PROCESO_CONCILIACION_ID_PROCESO_CONCILIACION.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_PROCESO_CONCILIACION (ID_PROCESO_CONCILIACION,"
		+ "ID_ENTIDAD_RECAUDADORA,HORA_PROGRAMACION,MINUTO_PROGRAMACION,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,"
		+ "IP_CREACION)VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_PROCESO_CONCILIACION SET ID_ENTIDAD_RECAUDADORA=?,"
		+ "HORA_PROGRAMACION=?,MINUTO_PROGRAMACION=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION="
		+ "SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_PROCESO_CONCILIACION=?";

	/**
	 * Instancia un nuevo objeto proceso  produccion DAO.
	 */
	public ProcesoConciliacionDAO()
	{
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ProcesoConciliacion con
	 * todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public Collection<ProcesoConciliacion> buscarConEntidadRecaudadora()
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lp_proceso;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lp_proceso     = new ArrayList<ProcesoConciliacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_WITH_ENTIDAD_RECAUDADORA);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getProcesoConciliacionConEntidadRecaudadora(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ProcesoConciliacion con
	 * todos los registros
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public Collection<ProcesoConciliacion> findAll()
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lp_proceso;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lp_proceso     = new ArrayList<ProcesoConciliacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getProcesoConciliacion(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de ProcesoConciliacion con
	 * todos los registros activos
	 *
	 * @return devuelve el valor del objeto collection con los registros activos
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public Collection<ProcesoConciliacion> findAllActive()
	    throws B2BException
	{
		Collection<ProcesoConciliacion> lp_proceso;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lp_proceso     = new ArrayList<ProcesoConciliacion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVE);
			lrs_rs     = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getProcesoConciliacion(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Retorna el valor del objeto de tipo ProcesoConciliacion de un id específico
	 *
	 * @param as_id Identificador del proceso de conciliacion a obtener
	 *
	 * @return devuelve el valor del objeto proceso produccion
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public ProcesoConciliacion findById(String as_id)
	    throws B2BException
	{
		ProcesoConciliacion lp_proceso;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lp_proceso     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setObject(li_contador++, as_id);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_proceso = getProcesoConciliacion(lrs_rs);
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

		return lp_proceso;
	}

	/**
	 * Retorna la parametrización de ejecución automatica de procesos de conciliación para una entidad recaudadora
	 *
	 * @param as_id Identificador de la entidad recaudadora
	 *
	 * @return Parametrización de ejecución automatica de procesos de conciliación para una entidad recaudadora
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 * @see ProcesoConciliacion
	 */
	public ProcesoConciliacion findByIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	    throws B2BException
	{
		ProcesoConciliacion lp_proceso;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lp_proceso     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ENTIDAD_RECAUDADORA);

			lps_ps.setObject(1, as_idEntidadRecaudadora);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lp_proceso = getProcesoConciliacion(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdEntidadRecaudadora", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lp_proceso;
	}

	/**
	 * Inserta o actualiza el registro en la tabla
	 *
	 * @param apc_parametros
	 *            correspondiente al valor del tipo de objeto ProcesoConciliacion
	 * @param ab_insertar
	 *            true si el registro se debe insertar. false si el registro se debe actualizar
	 * @throws B2BException
	 *             Señala que se ha producido una excepción
	 */
	public ProcesoConciliacion insertOrUpdate(ProcesoConciliacion apc_parametros, boolean ab_insertar)
	    throws B2BException
	{
		if(apc_parametros != null)
		{
			PreparedStatement lps_sentencia;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_secuencia;

			lps_sentencia     = null;
			lps_secuencia     = null;
			lrs_secuencia     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_sentencia     = lc_connection.prepareStatement(ab_insertar ? cs_INSERT : cs_UPDATE);

				if(ab_insertar)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
					lrs_secuencia     = lps_secuencia.executeQuery();

					if(lrs_secuencia.next())
					{
						Object lo_o;

						lo_o = lrs_secuencia.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							apc_parametros.setIdProcesoConciliacion(((BigDecimal)lo_o).toString());
						else
							throw new B2BException(ErrorKeys.PGN_PROCESO_CONCILIACION_SEQUENCE);
					}

					lps_sentencia.setString(li_column++, apc_parametros.getIdProcesoConciliacion());
				}

				lps_sentencia.setString(li_column++, apc_parametros.getIdEntidadRecaudadora());
				lps_sentencia.setLong(li_column++, apc_parametros.getHoraProgramacion());
				lps_sentencia.setLong(li_column++, apc_parametros.getMinutoProgramacion());
				lps_sentencia.setString(li_column++, apc_parametros.getActivo());

				if(ab_insertar)
				{
					lps_sentencia.setString(li_column++, apc_parametros.getIdUsuarioCreacion());
					lps_sentencia.setString(li_column++, apc_parametros.getIpCreacion());
				}
				else
				{
					lps_sentencia.setString(li_column++, apc_parametros.getIdUsuarioModificacion());
					lps_sentencia.setString(li_column++, apc_parametros.getIpModificacion());
					lps_sentencia.setString(li_column++, apc_parametros.getIdProcesoConciliacion());
				}

				lps_sentencia.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_sentencia);

				if(ab_insertar)
				{
					close(lps_secuencia);
					close(lrs_secuencia);
				}
			}
		}

		return apc_parametros;
	}

	/**
	 * Retorna el valor del proceso conciliación.
	 *
	 * @param ars_rs
	 *            correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de proceso conciliación.
	 * @throws SQLException
	 *             Señala que se ha producido una excepción
	 */
	private ProcesoConciliacion getProcesoConciliacion(ResultSet ars_rs)
	    throws SQLException
	{
		ProcesoConciliacion lpc_datos;

		lpc_datos = new ProcesoConciliacion();

		lpc_datos.setIdProcesoConciliacion(ars_rs.getString("ID_PROCESO_CONCILIACION"));
		lpc_datos.setIdEntidadRecaudadora(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lpc_datos.setHoraProgramacion(ars_rs.getLong("HORA_PROGRAMACION"));
		lpc_datos.setMinutoProgramacion(ars_rs.getLong("MINUTO_PROGRAMACION"));
		lpc_datos.setActivo(ars_rs.getString("ACTIVO"));

		{
			StringBuilder lsb_horaProceso;

			lsb_horaProceso = new StringBuilder();

			lsb_horaProceso.append(lpc_datos.getHoraProgramacion());
			lsb_horaProceso.append(":");

			{
				Long   ll_minutoProgramacion;
				String ls_minutoProgramacion;

				ll_minutoProgramacion = NumericUtils.getLongWrapper(lpc_datos.getMinutoProgramacion());

				if((ll_minutoProgramacion != null) && (ll_minutoProgramacion.compareTo(Long.valueOf(0L)) == 0L))
					ls_minutoProgramacion = "00";
				else
					ls_minutoProgramacion = StringUtils.getString(lpc_datos.getMinutoProgramacion());

				lsb_horaProceso.append(ls_minutoProgramacion);
			}

			lpc_datos.setHoraProceso(lsb_horaProceso.toString());
		}

		fillAuditoria(ars_rs, lpc_datos);

		return lpc_datos;
	}

	/**
	 * Retorna el valor del proceso conciliación.
	 *
	 * @param ars_rs
	 *            correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de proceso conciliación.
	 * @throws SQLException
	 *             Señala que se ha producido una excepción
	 */
	private ProcesoConciliacion getProcesoConciliacionConEntidadRecaudadora(ResultSet ars_rs)
	    throws SQLException
	{
		ProcesoConciliacion lpc_datos;
		lpc_datos = null;

		if(ars_rs != null)
		{
			lpc_datos = getProcesoConciliacion(ars_rs);

			if(lpc_datos != null)
			{
				lpc_datos.setNombreEntidadRecaudadora(ars_rs.getString("NOMBRE_ENTIDAD_RECAUDADORA"));
				lpc_datos.setCodigoEntidadRecaudadora(ars_rs.getString("CODIGO_ENTIDAD_RECAUDADORA"));
			}
		}

		return lpc_datos;
	}
}
