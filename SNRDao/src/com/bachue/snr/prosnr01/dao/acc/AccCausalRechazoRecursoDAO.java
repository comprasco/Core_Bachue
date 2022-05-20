package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AccCausalRechazoRecurso;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las sentencias de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO
 *
 * @author hcastaneda
 */
public class AccCausalRechazoRecursoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_CAUSAL_RECHAZO_RECURSO WHERE ID_CAUSAL_RECHAZO_RECURSO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_CAUSAL_RECHAZO_RECURSO SET ID_CAUSAL = ?, ID_SOLICITUD = ?, ID_TURNO = ?, ID_TURNO_HISTORIA = ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CAUSAL_RECHAZO_RECURSO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_CAUSAL_RECHAZO_RECURSO("
		+ "	ID_CAUSAL_RECHAZO_RECURSO, ID_CAUSAL, ID_SOLICITUD, ID_TURNO, ID_TURNO_HISTORIA, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT * FROM SDB_ACC_CAUSAL_RECHAZO_RECURSO WHERE ID_TURNO = ?";

	/** Constante cs_FIND_COUNT_BY_ID_CAUSAL_ID_TURNO. */
	private static final String cs_FIND_COUNT_BY_ID_CAUSAL_ID_TURNO = "SELECT COUNT(0) FROM SDB_ACC_CAUSAL_RECHAZO_RECURSO WHERE ID_CAUSAL = ? AND ID_TURNO = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_CAUSAL_RECHAZO_RECURSO_ID_CAUSAL_RECHAZO_RECURSO.NEXTVAL FROM DUAL";

	/** Constante cs_DELETE_BY_ID_TURNO_HISTORIA. */
	private static final String cs_DELETE_BY_ID_TURNO_HISTORIA = "DELETE FROM SDB_ACC_CAUSAL_RECHAZO_RECURSO WHERE ID_TURNO_HISTORIA = ?";

	/**
	 * Método encargado de borrar todos los registros de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO que coincidan con un ID_TURNO_HISTORIA.
	 *
	 * @param al_idTurnoHistoria Argumento de tipo <code>Long</code> que contiene el ID_TURNO_HISTORIA para borrar.
	 * @throws B2BException Señala que se ha generado una excepción
	 */
	public void deleteByIdTurnoHistoria(Long al_idTurnoHistoria)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		try
		{
			if(al_idTurnoHistoria != null)
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, al_idTurnoHistoria, 1);

				lps_ps.executeUpdate();
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "deleteByIdTurnoHistoria", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
		}
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO
	 * que coíncida con un ID_CAUSAL_RECHAZO_RECURSO.
	 *
	 * @param as_idAccCausalRechazoRecurso correspondiente al valor del tipo de objeto AccCausalRechazoRecurso
	 * @return devuelve el valor del objeto tarifa fija
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public AccCausalRechazoRecurso findById(String as_idAccCausalRechazoRecurso)
	    throws B2BException
	{
		AccCausalRechazoRecurso ls_object;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(as_idAccCausalRechazoRecurso != null)
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idAccCausalRechazoRecurso);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO que coincida con los parametros de consulta.
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id turno a consultar.
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AccCausalRechazoRecurso> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<AccCausalRechazoRecurso> lcrr_crr;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;

		lcrr_crr     = new ArrayList<AccCausalRechazoRecurso>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			if(StringUtils.isValidString(as_idTurno))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

				lps_ps.setString(1, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcrr_crr.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcrr_crr.isEmpty())
			lcrr_crr = null;

		return lcrr_crr;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_ACC_CAUSAL_RECHAZO_RECURSO .
	 *
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id turno a consultar.
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findCountByIdCausalIdTurno(String as_idCausal, String as_idTurno)
	    throws B2BException
	{
		int               li_contador;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_contador     = 0;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idCausal) && StringUtils.isValidString(as_idTurno))
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_COUNT_BY_ID_CAUSAL_ID_TURNO);

				lps_ps.setString(li_count++, as_idCausal);
				lps_ps.setString(li_count++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_contador = lrs_rs.getInt(1);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findCountByIdCausalIdTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_contador;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param aacrr_parametros objeto a insertar o modificar
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public AccCausalRechazoRecurso insertOrUpdate(AccCausalRechazoRecurso aacrr_parametros, boolean ab_query)
	    throws B2BException
	{
		AccCausalRechazoRecurso lacrr_return;

		lacrr_return = null;

		if(aacrr_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lrs_rs            = null;
			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							aacrr_parametros.setIdCausalRechazoRecurso(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, aacrr_parametros.getIdCausalRechazoRecurso());
						}
						else
							throw new B2BException(ErrorKeys.SIN_SECUENCIA_CAUSAL_RECHAZO_RECURSO);
					}
				}

				lps_ps.setString(li_column++, aacrr_parametros.getIdCausal());
				lps_ps.setString(li_column++, aacrr_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, aacrr_parametros.getIdTurno());
				setLong(lps_ps, aacrr_parametros.getIdTurnoHistoria(), li_column++);

				if(ab_query)
				{
					lps_ps.setString(li_column++, aacrr_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aacrr_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aacrr_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aacrr_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, aacrr_parametros.getIdCausalRechazoRecurso());

				lps_ps.executeUpdate();

				lacrr_return = aacrr_parametros;
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

		return lacrr_return;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de AccCausalRechazoRecurso.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de AccCausalRechazoRecurso
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AccCausalRechazoRecurso
	 */
	private AccCausalRechazoRecurso getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccCausalRechazoRecurso lcrr_datos;

		lcrr_datos = new AccCausalRechazoRecurso();

		if(ars_rs != null)
		{
			lcrr_datos.setIdCausalRechazoRecurso(ars_rs.getString("ID_CAUSAL_RECHAZO_RECURSO"));
			lcrr_datos.setIdCausal(ars_rs.getString("ID_CAUSAL"));
			lcrr_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
			lcrr_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
			lcrr_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));

			fillAuditoria(ars_rs, lcrr_datos);
		}

		return lcrr_datos;
	}
}
