package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.MensajeComunicacionEnviado;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_MENSAJE_COMUNICACION_ENVIADO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class MensajeComunicacionEnviadoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MENSAJE_COMUNICACION_ENVIADO "
		+ " WHERE ID_MENSAJE_COMUNICACION_ENVIADO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MENSAJE_COMUNICACION_ENVIADO "
		+ "(ID_MENSAJE_COMUNICACION_ENVIADO, ID_MENSAJE, ID_TURNO_HISTORIA, ID_SOLICITUD, FECHA_EXITO_EJECUCION_AUTOMATICA, RESPUESTAS_EJECUCION_AUTOMATICA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_MENSAJE_COMUNICACION_ENVIADO ";

	/** Constante cs_FIND_SECUENCE_ID_MENSAJE_COMUNICACION_ENVIADO. */
	private static final String cs_FIND_SECUENCE_ID_MENSAJE_COMUNICACION_ENVIADO = "SELECT SEC_ACC_MENSAJE_COMUNICACION_ENVIADO_ID_MENSAJE_COMUNICACION_ENVIADO.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeComunicacionEnviado> findAll()
	    throws B2BException
	{
		Collection<MensajeComunicacionEnviado> lcmce_mensajes;
		PreparedStatement                      lps_ps;
		ResultSet                              lrs_rs;

		lcmce_mensajes     = new ArrayList<MensajeComunicacionEnviado>();
		lps_ps             = null;
		lrs_rs             = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmce_mensajes.add(getObjectFromResultSet(lrs_rs));
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

		if(lcmce_mensajes.isEmpty())
			lcmce_mensajes = null;

		return lcmce_mensajes;
	}

	/**
	 * Find by id.
	 *
	 * @param amce_param de MensajeComunicacionEnviado
	 * @return el valor de mensaje comunicacion enviado
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeComunicacionEnviado findById(MensajeComunicacionEnviado amce_param)
	    throws B2BException
	{
		MensajeComunicacionEnviado lmce_mensaje;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lmce_mensaje     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, amce_param.getIdMensajeComunicacionEnviado());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmce_mensaje = getObjectFromResultSet(lrs_rs);
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

		return lmce_mensaje;
	}

	/**
	 * Insert.
	 *
	 * @param amce_parametros de ac parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(MensajeComunicacionEnviado amce_parametros)
	    throws B2BException
	{
		if(amce_parametros != null)
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

				lps_ps     = lc_connection.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_ID_MENSAJE_COMUNICACION_ENVIADO);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;
					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						amce_parametros.setIdMensajeComunicacionEnviado(StringUtils.getString(lo_o));
					else
						throw new B2BException(ErrorKeys.ACC_MENSAJE_COMUNICACION_ENVIADO_SEQUENCE);
				}

				lps_ps.setString(li_column++, amce_parametros.getIdMensajeComunicacionEnviado());
				lps_ps.setString(li_column++, amce_parametros.getIdMensaje());
				lps_ps.setString(li_column++, amce_parametros.getIdTurnoHistoria());
				lps_ps.setString(li_column++, amce_parametros.getIdSolicitud());
				setDate(lps_ps, amce_parametros.getFechaExitoEjecucionAutomatica(), li_column++);
				lps_ps.setString(li_column++, amce_parametros.getRespuestasEjecucionAutomatica());
				lps_ps.setString(li_column++, amce_parametros.getIpCreacion());
				lps_ps.setString(li_column++, amce_parametros.getIdUsuarioCreacion());

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

				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Retorna Objeto o variable de valor MensajeComunicacionEnviado.
	 *
	 * @param ars_rs de ResultSet
	 * @return el valor de MensajeComunicacionEnviado
	 * @throws SQLException cuando se produce algún error en el proceso
	 */
	private MensajeComunicacionEnviado getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MensajeComunicacionEnviado lmce_mensaje;

		lmce_mensaje = new MensajeComunicacionEnviado();

		lmce_mensaje.setIdMensajeComunicacionEnviado(ars_rs.getString("ID_MENSAJE_COMUNICACION_ENVIADO"));
		lmce_mensaje.setIdMensaje(ars_rs.getString("ID_MENSAJE"));
		lmce_mensaje.setIdTurnoHistoria(ars_rs.getString("ID_TURNO_HISTORIA"));
		lmce_mensaje.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lmce_mensaje.setFechaExitoEjecucionAutomatica(ars_rs.getDate("FECHA_EXITO_EJECUCION_AUTOMATICA"));
		lmce_mensaje.setRespuestasEjecucionAutomatica(ars_rs.getString("RESPUESTAS_EJECUCION_AUTOMATICA"));

		fillAuditoriaCreacion(ars_rs, lmce_mensaje);

		return lmce_mensaje;
	}
}
