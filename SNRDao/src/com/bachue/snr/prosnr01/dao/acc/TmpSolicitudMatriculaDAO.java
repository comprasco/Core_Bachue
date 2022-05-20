package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultasd de la tabla SDB_ACC_TMP_SOLICITUD_MATRICULA
 *
 * @author hcastaneda
 */
public class TmpSolicitudMatriculaDAO extends BaseDAO implements IBase<TmpSolicitudMatricula>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ACCION, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_TMP_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, "
		+ "EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, ACCION, ID_USUARIO_CREACION, FECHA_CREACION "
		+ "FROM SDB_ACC_TMP_SOLICITUD_MATRICULA WHERE ID_SOLICITUD=? AND ACCION=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TMP_SOLICITUD_MATRICULA "
		+ "SET EXPEDIR_CERTIFICADO=?, CANTIDAD_CERTIFICADOS=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, ACCION=?,  FECHA_MODIFICACION = SYSTIMESTAMP "
		+ "WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_UPDATE_ACCION. */
	private static final String cs_UPDATE_ACCION = "UPDATE SDB_ACC_TMP_SOLICITUD_MATRICULA "
		+ "SET ACCION='N', FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION=? WHERE ID_SOLICITUD=? AND ACCION IN('I','E')";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TMP_SOLICITUD_MATRICULA"
		+ "(ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, EXPEDIR_CERTIFICADO, CANTIDAD_CERTIFICADOS, "
		+ "ID_USUARIO_CREACION,IP_CREACION,ACCION , FECHA_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?,SYSTIMESTAMP)";

	/** {@inheritdoc} */
	@Override
	public TmpSolicitudMatricula findById(TmpSolicitudMatricula at_param)
	    throws B2BException
	{
		TmpSolicitudMatricula ls_object;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdSolicitud());
			lps_ps.setString(2, at_param.getIdCirculo());
			lps_ps.setLong(3, at_param.getIdMatricula());

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
	 * Retorna el valor del objeto de tipo id solicitud.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatricula
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<TmpSolicitudMatricula> findByIdSolicitud(TmpSolicitudMatricula at_param)
	    throws B2BException
	{
		Collection<TmpSolicitudMatricula> lcsm_datos;
		PreparedStatement                 lps_ps;
		ResultSet                         lrs_rs;

		lcsm_datos     = new ArrayList<TmpSolicitudMatricula>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

			lps_ps.setString(1, at_param.getIdSolicitud());
			lps_ps.setString(2, at_param.getAccion());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcsm_datos.add(getObjetFromResultSet(lrs_rs));
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

		if(lcsm_datos.isEmpty())
			lcsm_datos = null;

		return lcsm_datos;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TmpSolicitudMatricula at_param, boolean ab_query)
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
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdCirculo());
					lps_ps.setLong(li_column++, at_param.getIdMatricula());
				}

				lps_ps.setString(li_column++, at_param.getExpedirCertificado());
				lps_ps.setBigDecimal(li_column++, at_param.getCantidadCertificados());

				if(ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
				}

				lps_ps.setString(li_column++, at_param.getAccion());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdCirculo());
					lps_ps.setLong(li_column++, at_param.getIdMatricula());
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
	 * Actualiza el registro en la tabla
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatricula
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAccionTmps(TmpSolicitudMatricula at_param)
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
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ACCION);

				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getIdSolicitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateAccionTmps", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de TmpSolicitudMatricula
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TmpSolicitudMatricula
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TmpSolicitudMatricula getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TmpSolicitudMatricula ls_solicitud;

		ls_solicitud = new TmpSolicitudMatricula();

		ls_solicitud.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		ls_solicitud.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		ls_solicitud.setIdMatricula(lrs_rs.getLong("ID_MATRICULA"));
		ls_solicitud.setExpedirCertificado(lrs_rs.getString("EXPEDIR_CERTIFICADO"));
		ls_solicitud.setCantidadCertificados(getBigDecimal(lrs_rs, "CANTIDAD_CERTIFICADOS"));
		ls_solicitud.setAccion(lrs_rs.getString("ACCION"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));

		return ls_solicitud;
	}
}
