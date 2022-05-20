package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDecisionRecurso;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_TIPO_DECISION_RECURSO
 *
 * @author jpatino
 */
public class TipoDecisionRecursoDAO extends BaseDAO implements IBase<TipoDecisionRecurso>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_DECISION_RECURSO WHERE ID_TIPO_DECISION_RECURSO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_DECISION_RECURSO SET DESCRIPCION= ?, ACTIVO = ?,"
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TIPO_DECISION_RECURSO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_DECISION_RECURSO("
		+ "ID_TIPO_DECISION_RECURSO, DESCRIPCION, ACTIVO, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)"
		+ " VALUES(?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_DECISION_RECURSO";

	/** Constante cs_FIND_SECUENCE_TIPO_DECISION_RECURSO. */
	private static final String cs_FIND_SECUENCE_TIPO_DECISION_RECURSO = "SELECT SEC_PGN_TIPO_DECISION_RECURSO_ID_TIPO_DECISION_RECURSO.NEXTVAL FROM DUAL";

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_TIPO_DECISION_RECURSO.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TipoDecisionRecurso> findAll()
	    throws B2BException
	{
		Collection<TipoDecisionRecurso> lcrc_crc;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcrc_crc     = new ArrayList<TipoDecisionRecurso>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcrc_crc.add(getObjetFromResultSet(lrs_rs));
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

		if(lcrc_crc.isEmpty())
			lcrc_crc = null;

		return lcrc_crc;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_DECISION_RECURSO
	 * que coíncida con un ID_TIPO_DECISION_RECURSO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TipoDecisionRecurso
	 * @return devuelve el valor del objeto tipo decision recurso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TipoDecisionRecurso findById(TipoDecisionRecurso at_param)
	    throws B2BException
	{
		TipoDecisionRecurso ls_object;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdTipoDecisionRecurso());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
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
	 * @param ac_parametros objeto a insertar o modificar
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(TipoDecisionRecurso ac_parametros, boolean ab_query)
	    throws B2BException
	{
		if(ac_parametros != null)
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
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_TIPO_DECISION_RECURSO);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ac_parametros.setIdTipoDecisionRecurso(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_TIPO_DECISION_RECURSO);
					}

					lps_ps.setString(li_column++, ac_parametros.getIdTipoDecisionRecurso());
				}

				lps_ps.setString(li_column++, ac_parametros.getDescripcion());
				lps_ps.setString(li_column++, ac_parametros.getActivo());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ac_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIpModificacion());
					lps_ps.setString(li_column++, ac_parametros.getIdTipoDecisionRecurso());
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

				if(ab_query)
				{
					close(lps_secuencia);
					close(lrs_rs);
				}
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de TipoDecisionRecurso.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de TipoDecisionRecurso
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see TipoDecisionRecurso
	 */
	private TipoDecisionRecurso getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		TipoDecisionRecurso lr_regional;

		lr_regional = new TipoDecisionRecurso();

		lr_regional.setIdTipoDecisionRecurso(ars_rs.getString("ID_TIPO_DECISION_RECURSO"));
		lr_regional.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lr_regional.setActivo(ars_rs.getString("ACTIVO"));
		lr_regional.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lr_regional.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lr_regional.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lr_regional.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lr_regional.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lr_regional.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lr_regional;
	}
}
