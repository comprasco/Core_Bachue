package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.model.sdb.pgn.EstadoActividad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase que contiene todos las consultas correspondientes a la tabla SDB_PGN_ESTADO_ACTIVIDAD
 *
 * @author garias
 */
public class EstadoActividadDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_ESTADO_ACTIVIDAD,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_ESTADO_ACTIVIDAD WHERE ID_ESTADO_ACTIVIDAD = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_ESTADO_ACTIVIDAD,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_ESTADO_ACTIVIDAD";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_ESTADO_ACTIVIDAD SET DESCRIPCION=?,ESTADO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_ESTADO_ACTIVIDAD=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_ESTADO_ACTIVIDAD (ID_ESTADO_ACTIVIDAD,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,IP_CREACION,FECHA_CREACION) VALUES (?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_ESTADO_ACTIVIDAD,DESCRIPCION,ESTADO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION FROM SDB_PGN_ESTADO_ACTIVIDAD WHERE ESTADO='S'";

	/**
	 * Retorna el valor del objeto de tipo EstadoActividad
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para que el estado = 'A' y se ordene por el id estado actividad,
	 * falso para ordernar los resultados por id estado actividad ascendente
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	public Collection<EstadoActividad> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<EstadoActividad> lcea_return;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;
		StringBuilder                lsb_sbf;

		lcea_return     = new LinkedList<EstadoActividad>();
		lps_ps          = null;
		lrs_rs          = null;
		lsb_sbf         = new StringBuilder(cs_FIND_ALL);

		try
		{
			if(ab_b)
				lsb_sbf = lsb_sbf.append(" WHERE ESTADO = 'A' ORDER BY ID_ESTADO_ACTIVIDAD ASC");
			else
				lsb_sbf = lsb_sbf.append(" ORDER BY ID_ESTADO_ACTIVIDAD ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sbf.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcea_return.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcea_return))
			lcea_return = null;

		return lcea_return;
	}

	/**
	 * Retorna el valor de la consulta de estado actividad donde el estado sea S
	 *
	 * @return devuelve el valor del objeto collection de  EstadoActividad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	public Collection<EstadoActividad> findAllActivo()
	    throws B2BException
	{
		Collection<EstadoActividad> lccr_ccr;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lccr_ccr     = new ArrayList<EstadoActividad>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Retorna el valor del objeto de tipo EstadoActividad consultado por ID
	 *
	 * @param aea_param correspondiente al valor del tipo de objeto EstadoActividad
	 * @return devuelve el valor del objeto estado actividad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	public EstadoActividad findById(EstadoActividad aea_param)
	    throws B2BException
	{
		EstadoActividad   lea_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lea_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_column++, aea_param.getIdEstadoActividad());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lea_return = getObjetFromResultSet(lrs_rs);
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

		return lea_return;
	}

	/**
	 * Inserta o actualiza el registro en la base de datos
	 *
	 * @param aea_param correspondiente al valor del tipo de objeto EstadoActividad
	 * @param ab_query correspondiente al valor del tipo de objeto boolean, verdadero para insertar un nuevo registro
	 * en la base de datos, falso para actualizar el registro en la baes de datos.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(EstadoActividad aea_param, boolean ab_query)
	    throws B2BException
	{
		if(aea_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
					lps_ps.setString(li_column++, aea_param.getIdEstadoActividad());

				lps_ps.setString(li_column++, aea_param.getDescripcion());
				lps_ps.setString(li_column++, aea_param.getEstado());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, aea_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aea_param.getIpModificacion());
					lps_ps.setString(li_column++, aea_param.getIdEstadoActividad());
				}
				else
				{
					lps_ps.setString(li_column++, aea_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aea_param.getIpCreacion());
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
	 * Retorna el valor de EstadoActividad contenido en el ResultSet
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet EstadoActividad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EstadoActividad
	 */
	private EstadoActividad getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		EstadoActividad lea_estadoActividad;

		lea_estadoActividad = new EstadoActividad();

		lea_estadoActividad.setIdEstadoActividad(ars_rs.getString("ID_ESTADO_ACTIVIDAD"));
		lea_estadoActividad.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lea_estadoActividad.setEstado(ars_rs.getString("ESTADO"));
		lea_estadoActividad.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lea_estadoActividad.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lea_estadoActividad.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lea_estadoActividad.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lea_estadoActividad.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lea_estadoActividad.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lea_estadoActividad;
	}
}
