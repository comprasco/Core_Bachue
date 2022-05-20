package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.pgn.MensajeRespuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_MENSAJE_RESPUESTA
 * @author Carlos Calderón
 *
 */
public class MensajeRespuestaDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_MENSAJE_RESPUESTA";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_MENSAJE_RESPUESTA WHERE CODIGO_MENSAJE = ?";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_MENSAJE_RESPUESTA SET TEXTO_MENSAJE = ?, ACTIVO = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE CODIGO_MENSAJE = ?";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_MENSAJE_RESPUESTA (CODIGO_MENSAJE, TEXTO_MENSAJE, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_MENSAJE_RESPUESTA
	 */
	public Collection<MensajeRespuesta> findAll()
	    throws B2BException
	{
		Collection<MensajeRespuesta> lcmr_cllMensajeRespuesta;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lcmr_cllMensajeRespuesta     = new ArrayList<MensajeRespuesta>();
		lps_ps                       = null;
		lrs_rs                       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmr_cllMensajeRespuesta.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcmr_cllMensajeRespuesta))
			lcmr_cllMensajeRespuesta = null;

		return lcmr_cllMensajeRespuesta;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_MENSAJE_RESPUESTA
	 */
	public MensajeRespuesta findById(String as_param)
	    throws B2BException
	{
		MensajeRespuesta  lmr_mensajeRespuesta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lmr_mensajeRespuesta     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, as_param);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmr_mensajeRespuesta = getObjetFromResultSet(lrs_rs);
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

		return lmr_mensajeRespuesta;
	}

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un codigoMensaje específico de la tabla SDB_PGN_MENSAJE_RESPUESTA
	 */
	public MensajeRespuesta findById(MensajeRespuesta amr_param)
	    throws B2BException
	{
		MensajeRespuesta  lmr_mensajeRespuesta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lmr_mensajeRespuesta     = null;
		lps_ps                   = null;
		lrs_rs                   = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, amr_param.getCodigoMensaje());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmr_mensajeRespuesta = getObjetFromResultSet(lrs_rs);
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

		return lmr_mensajeRespuesta;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_MENSAJE_RESPUESTA
	 */
	public void insert(MensajeRespuesta amr_param)
	    throws B2BException
	{
		if(amr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, amr_param.getCodigoMensaje());
				lps_ps.setString(li_column++, amr_param.getTextoMensaje());
				lps_ps.setString(li_column++, amr_param.getActivo());
				lps_ps.setString(li_column++, amr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, amr_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_MENSAJE_RESPUESTA
	 */
	public void update(MensajeRespuesta amr_param)
	    throws B2BException
	{
		if(amr_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, amr_param.getTextoMensaje());
				lps_ps.setString(li_column++, amr_param.getActivo());
				lps_ps.setString(li_column++, amr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, amr_param.getIpModificacion());
				lps_ps.setString(li_column++, amr_param.getCodigoMensaje());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método para la obtencion del objeto MensajeRespuesta
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de MensajeRespuesta
	 * @throws SQLException
	 * @see MensajeRespuesta
	 */
	private MensajeRespuesta getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MensajeRespuesta lmr_mr;

		lmr_mr = new MensajeRespuesta();

		lmr_mr.setCodigoMensaje(ars_rs.getString("CODIGO_MENSAJE"));
		lmr_mr.setTextoMensaje(ars_rs.getString("TEXTO_MENSAJE"));
		lmr_mr.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lmr_mr);

		return lmr_mr;
	}
}
