package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_FASES.
 *
 * @author Manuel Blanco
 */
public class FasesDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_FASE,NOMBRE,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_FASES WHERE ID_FASE=? ORDER BY ID_FASE ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_FASE,NOMBRE,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_FASES ORDER BY ID_FASE ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_FASE,NOMBRE,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_FASES WHERE ESTADO = 'A' ORDER BY LENGTH(ID_FASE), ID_FASE";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_FASES SET NOMBRE=?,DESCRIPCION=?,ESTADO=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_FASE=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_FASES (ID_FASE, IP_CREACION, ID_USUARIO_CREACION, NOMBRE, DESCRIPCION, ESTADO, FECHA_CREACION) VALUES(?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_FASES.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Fases> findAll()
	    throws B2BException
	{
		Collection<Fases> ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = new LinkedList<Fases>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
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

		return ls_object;
	}

	/**
	 * Metodo para encontrar todos los regsitros de la tabla SDB_PGN_FASES que se encuentren activos.
	 *
	 * @return devuelve el valor del objeto collection de Fases
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Fases> findAllActivo()
	    throws B2BException
	{
		Collection<Fases> ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = new LinkedList<Fases>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

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
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_FASES
	 * que coincidadn con un ID_FASE especifico.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Fases
	 * @return devuelve el valor del objeto fases
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Fases findById(Fases at_param)
	    throws B2BException
	{
		Fases             ls_object;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(at_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(1, at_param.getIdFase());

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
		}

		return ls_object;
	}

	/**
	 * Metodo para insertar o actualizar registros de la taba SDB_PGN_FASES.
	 *
	 * @param at_param objeto que se va a insertar o modificar en la base de datos
	 * @param ab_query indica si se desea insertar o actualizar en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Fases at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setLong(li_column++, at_param.getIdFase());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
				}

				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(li_column++, at_param.getDescripcion());
				lps_ps.setString(li_column++, at_param.getEstado());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setLong(li_column++, at_param.getIdFase());
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
			}
		}
	}

	/**
	 * Metodo para obtener la respuesta de la sentecia sql indicada.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Fases getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Fases lf_fase;

		lf_fase = new Fases();

		lf_fase.setIdFase(ars_rs.getLong("ID_FASE"));
		lf_fase.setNombre(ars_rs.getString("NOMBRE"));
		lf_fase.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lf_fase.setEstado(ars_rs.getString("ESTADO"));
		lf_fase.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lf_fase.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lf_fase.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lf_fase.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lf_fase.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lf_fase.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lf_fase;
	}
}
