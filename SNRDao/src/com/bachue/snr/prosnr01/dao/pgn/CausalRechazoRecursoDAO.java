package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las sentencias de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO
 *
 * @author hcastaneda
 */
public class CausalRechazoRecursoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAUSAL_RECHAZO_RECURSO WHERE ID_CAUSAL_RECHAZO_RECURSO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAUSAL_RECHAZO_RECURSO SET NOMBRE= ?, ACTIVO= ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_CAUSAL_RECHAZO_RECURSO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAUSAL_RECHAZO_RECURSO("
		+ "	ID_CAUSAL_RECHAZO_RECURSO, NOMBRE, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAUSAL_RECHAZO_RECURSO";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CausalRechazoRecurso> findAll()
	    throws B2BException
	{
		Collection<CausalRechazoRecurso> lcrr_crr;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;

		lcrr_crr     = new ArrayList<CausalRechazoRecurso>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcrr_crr.add(getObjetFromResultSet(lrs_rs));
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

		if(lcrr_crr.isEmpty())
			lcrr_crr = null;

		return lcrr_crr;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO
	 * que coíncida con un ID_CAUSAL_RECHAZO_RECURSO.
	 *
	 * @param as_idCausalRechazoRecurso correspondiente al valor del tipo de objeto CausalRechazoRecurso
	 * @return devuelve el valor del objeto tarifa fija
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CausalRechazoRecurso findById(String as_idCausalRechazoRecurso)
	    throws B2BException
	{
		CausalRechazoRecurso ls_object;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(StringUtils.isValidString(as_idCausalRechazoRecurso))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idCausalRechazoRecurso);

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
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param acrr_parametros objeto a insertar o modificar
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(CausalRechazoRecurso acrr_parametros, boolean ab_query)
	    throws B2BException
	{
		if(acrr_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, acrr_parametros.getIdCausalRechazoRecurso());

				lps_ps.setString(li_column++, acrr_parametros.getNombre());
				lps_ps.setString(li_column++, acrr_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, acrr_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, acrr_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, acrr_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, acrr_parametros.getIpModificacion());
					lps_ps.setString(li_column++, acrr_parametros.getIdCausalRechazoRecurso());
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
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de CausalRechazoRecurso.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de CausalRechazoRecurso
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see CausalRechazoRecurso
	 */
	private CausalRechazoRecurso getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		CausalRechazoRecurso lcrr_datos;

		lcrr_datos = new CausalRechazoRecurso();

		if(ars_rs != null)
		{
			lcrr_datos.setIdCausalRechazoRecurso(ars_rs.getString("ID_CAUSAL_RECHAZO_RECURSO"));
			lcrr_datos.setNombre(ars_rs.getString("NOMBRE"));
			lcrr_datos.setActivo(ars_rs.getString("ACTIVO"));

			fillAuditoria(ars_rs, lcrr_datos);
		}

		return lcrr_datos;
	}
}
