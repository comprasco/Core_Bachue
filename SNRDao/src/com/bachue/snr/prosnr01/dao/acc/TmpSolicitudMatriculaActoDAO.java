package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO
 *
 * @author stafur
 */
public class TmpSolicitudMatriculaActoDAO extends BaseDAO implements IBase<TmpSolicitudMatriculaActo>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, USUARIO FROM SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ACTO=?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, USUARIO, ACCION FROM SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO WHERE ID_SOLICITUD=? AND ACCION = ? AND ID_CIRCULO=? AND ID_MATRICULA=?";

	/** Constante cs_FIND_BY_ID_TURNO. */
	private static final String cs_FIND_BY_ID_TURNO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, USUARIO FROM SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO WHERE ID_TURNO=?";

	/** Constante cs_FIND_ALL_ID_TURNO. */
	private static final String cs_FIND_ALL_ID_TURNO = "SELECT ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, USUARIO FROM SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO WHERE ID_TURNO=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO SET FECHA=SYSTIMESTAMP, USUARIO=?, ID_TURNO = ?, ACCION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ?,IP_MODIFICACION=? WHERE ID_SOLICITUD=? AND ID_CIRCULO=? AND ID_MATRICULA=? AND ID_ACTO=?";

	/** Constante cs_UPDATE_ACCION. */
	private static final String cs_UPDATE_ACCION = "UPDATE SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO "
		+ "SET ACCION='N', FECHA_MODIFICACION = SYSTIMESTAMP, ID_USUARIO_MODIFICACION = ? WHERE ID_SOLICITUD=? AND ACCION IN('I','E')";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_TMP_SOLICITUD_MATRICULA_ACTO(ID_SOLICITUD, ID_CIRCULO, ID_MATRICULA, ID_ACTO, FECHA, USUARIO, ID_TURNO, ACCION, FECHA_CREACION, ID_USUARIO_CREACION,IP_CREACION) VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?,?,?, SYSTIMESTAMP, ?,?)";

	/**
	 * Retorna el valor del objeto de tipo Collection de TmpSolicitudMatriculaActo filtrado por id solicitud.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatriculaActo
	 * @return devuelve el valor del objeto collection de TmpSolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TmpSolicitudMatriculaActo
	 */
	public Collection<TmpSolicitudMatriculaActo> findAllByIdSolicitud(TmpSolicitudMatriculaActo at_param)
	    throws B2BException
	{
		Collection<TmpSolicitudMatriculaActo> ls_object;
		PreparedStatement                     lps_ps;
		ResultSet                             lrs_rs;

		ls_object     = new ArrayList<TmpSolicitudMatriculaActo>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

			lps_ps.setString(1, at_param.getIdSolicitud());
			lps_ps.setString(2, at_param.getAccion());
			lps_ps.setString(3, at_param.getIdCirculo());
			lps_ps.setLong(4, at_param.getIdMatricula());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdSolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(ls_object))
			ls_object = null;

		return ls_object;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de TmpSolicitudMatriculaActo filtrado por id turno.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatriculaActo
	 * @return devuelve el valor del objeto collection de TmpSolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TmpSolicitudMatriculaActo
	 */
	public Collection<TmpSolicitudMatriculaActo> findAllByIdTurno(TmpSolicitudMatriculaActo at_param)
	    throws B2BException
	{
		Collection<TmpSolicitudMatriculaActo> ls_object;
		PreparedStatement                     lps_ps;
		ResultSet                             lrs_rs;

		ls_object     = new ArrayList<TmpSolicitudMatriculaActo>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_ID_TURNO);

			lps_ps.setString(1, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByIdTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/** {@inheritdoc} */
	@Override
	public TmpSolicitudMatriculaActo findById(TmpSolicitudMatriculaActo at_param)
	    throws B2BException
	{
		TmpSolicitudMatriculaActo ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdSolicitud());
			lps_ps.setString(2, at_param.getIdCirculo());
			lps_ps.setLong(3, at_param.getIdMatricula());
			lps_ps.setString(4, at_param.getIdActo());

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
	 * Retorna el valor del objeto de tipo TmpSolicitudMatriculaActo filtrado por id turno.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatriculaActo
	 * @return devuelve el valor del objeto tmp solicitud matricula acto
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public TmpSolicitudMatriculaActo findByIdTurno(TmpSolicitudMatriculaActo at_param)
	    throws B2BException
	{
		TmpSolicitudMatriculaActo ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_TURNO);

			lps_ps.setString(1, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(TmpSolicitudMatriculaActo at_param, boolean ab_query)
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
					lps_ps.setString(li_column++, at_param.getIdActo());
				}

				lps_ps.setString(li_column++, at_param.getUsuario());
				lps_ps.setString(li_column++, at_param.getIdTurno());
				lps_ps.setString(li_column++, at_param.getAccion());

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

				if(!ab_query)
				{
					lps_ps.setString(li_column++, at_param.getIdSolicitud());
					lps_ps.setString(li_column++, at_param.getIdCirculo());
					lps_ps.setLong(li_column++, at_param.getIdMatricula());
					lps_ps.setString(li_column++, at_param.getIdActo());
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
	 * @param at_param correspondiente al valor del tipo de objeto TmpSolicitudMatriculaActo
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateAccionTmps(TmpSolicitudMatriculaActo at_param)
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
	 * Retorna el valor de TmpSolicitudMatriculaActo
	 *
	 * @param lrs_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TmpSolicitudMatriculaActo
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TmpSolicitudMatriculaActo getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		TmpSolicitudMatriculaActo ltsma_tsma;

		ltsma_tsma = new TmpSolicitudMatriculaActo();

		ltsma_tsma.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
		ltsma_tsma.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		ltsma_tsma.setIdMatricula(lrs_rs.getLong("ID_MATRICULA"));
		ltsma_tsma.setIdActo(lrs_rs.getString("ID_ACTO"));
		ltsma_tsma.setFecha(lrs_rs.getTimestamp("FECHA"));
		ltsma_tsma.setUsuario(lrs_rs.getString("USUARIO"));

		return ltsma_tsma;
	}
}
