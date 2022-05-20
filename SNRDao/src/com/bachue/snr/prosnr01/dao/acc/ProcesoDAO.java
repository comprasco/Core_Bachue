package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_PROCESO
 *
 * @author dbeltran
 */
public class ProcesoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_PROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, ACTIVO, RECEPCION_DOCUMENTO FROM SDB_ACC_PROCESO WHERE ID_PROCESO=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_PROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, ACTIVO, RECEPCION_DOCUMENTO FROM SDB_ACC_PROCESO ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT ID_PROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, ACTIVO, RECEPCION_DOCUMENTO FROM SDB_ACC_PROCESO WHERE ACTIVO = 'S'";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PROCESO(ID_PROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, ACTIVO,IP_CREACION,RECEPCION_DOCUMENTO) VALUES(?, ?, ?, SYSTIMESTAMP, ?, ?, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PROCESO SET NOMBRE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, ACTIVO=? ,IP_MODIFICACION = ?, RECEPCION_DOCUMENTO=? WHERE ID_PROCESO=?";

	/** Constante cs_FIND_ALL_REPEPCION. */
	private static final String cs_FIND_ALL_REPEPCION = "SELECT ID_PROCESO, NOMBRE, ID_USUARIO_CREACION, FECHA_CREACION, ACTIVO, RECEPCION_DOCUMENTO FROM SDB_ACC_PROCESO WHERE RECEPCION_DOCUMENTO = 'S' AND ACTIVO = 'S'  ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_REPEPCION. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_PROCESO SAP INNER JOIN SDB_ACC_SOLICITUD SAS ON SAS.ID_PROCESO = SAP.ID_PROCESO WHERE ID_SOLICITUD = ?";

	/**
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Proceso
	 */
	public Collection<Proceso> findAll(boolean ab_b)
	    throws B2BException
	{
		Collection<Proceso> lp_proceso;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;
		String              ls_query;

		lp_proceso     = new ArrayList<Proceso>();
		lps_ps         = null;
		lrs_rs         = null;
		ls_query       = cs_FIND_ALL;

		try
		{
			if(ab_b)
				ls_query = cs_FIND_ALL_REPEPCION;

			lps_ps     = getConnection().prepareStatement(ls_query);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getObjetFromResultSet(lrs_rs));
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
	 * Retorna el valor del objeto de Collection de Proceso.
	 *
	 * @param ab_OrderById si se ordena por id
	 * @return devuelve el valor de Collection de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Proceso
	 */
	public Collection<Proceso> findAllActivo(boolean ab_OrderById)
	    throws B2BException
	{
		Collection<Proceso> lp_proceso;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lp_proceso     = new ArrayList<Proceso>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_query;

			lsb_query = new StringBuilder(cs_FIND_ALL_ACTIVO);

			lsb_query.append(ab_OrderById ? " ORDER BY LENGTH(ID_PROCESO),ID_PROCESO " : " ORDER BY NOMBRE ASC ");
			lps_ps     = getConnection().prepareStatement(lsb_query.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lp_proceso.add(getObjetFromResultSet(lrs_rs));
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

		if(lp_proceso.isEmpty())
			lp_proceso = null;

		return lp_proceso;
	}

	/**
	 * Retorna el valor del objeto de Proceso.
	 *
	 * @param ap_param correspondiente al valor del tipo de objeto Proceso
	 * @return devuelve el valor de Proceso
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Proceso
	 */
	public Proceso findById(Proceso ap_param)
	    throws B2BException
	{
		return (ap_param != null) ? findById(ap_param.getIdProceso()) : null;
	}

	/**
	 * Busca en la tabla un registro relacionado a un id específico
	 *
	 * @param as_idProceso Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Proceso resultante de la consulta
	 * @throws B2BException
	 */
	public Proceso findById(String as_idProceso)
	    throws B2BException
	{
		Proceso lp_return;

		lp_return = null;

		if(StringUtils.isValidString(as_idProceso))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idProceso);

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
	 * Busca en la tabla un registro relacionado a un id específico
	 *
	 * @param as_idSolicitud Objeto contenedor del id a utilizar como filtro en la busqueda
	 * @return Proceso resultante de la consulta
	 * @throws B2BException
	 */
	public Proceso findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Proceso lp_return;

		lp_return = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lp_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

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
	 * Inserta o Actualiza el registro en la base de datos
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Proceso
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(Proceso at_param, boolean ab_query)
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
					lps_ps.setString(li_column++, at_param.getIdProceso());

				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(
				    li_column++, ab_query ? at_param.getIdUsuarioCreacion() : at_param.getIdUsuarioModificacion()
				);
				lps_ps.setString(li_column++, at_param.getActivo());
				lps_ps.setString(li_column++, ab_query ? at_param.getIpCreacion() : at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getRecepcionDocumento());

				if(!ab_query)
					lps_ps.setString(li_column++, at_param.getIdProceso());

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
	 * Retorna el valor de Proceso
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Proceso
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private Proceso getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Proceso lp_proceso;

		lp_proceso = new Proceso();

		lp_proceso.setIdProceso(lrs_rs.getString("ID_PROCESO"));
		lp_proceso.setNombre(lrs_rs.getString("NOMBRE"));
		lp_proceso.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lp_proceso.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lp_proceso.setActivo(lrs_rs.getString("ACTIVO"));
		lp_proceso.setRecepcionDocumento(lrs_rs.getString("RECEPCION_DOCUMENTO"));

		return lp_proceso;
	}
}
