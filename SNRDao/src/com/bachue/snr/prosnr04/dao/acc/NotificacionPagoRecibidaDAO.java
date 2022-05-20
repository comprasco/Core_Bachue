package com.bachue.snr.prosnr04.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.acc.NotificacionPagoRecibida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades NotificacionPagoRecibidaDAO en donde se insertan, actualizan y consultar valores
 * correspondientes a la tabla de la base de datos SDB_ACC_NOTIFICACION_PAGO_RECIBIDA
 *
 * @author Julian Vaca
 */
public class NotificacionPagoRecibidaDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_NOTIFICACION_PAGO_RECIBIDA SET NUMERO_REFERENCIA_BACHUE = ?,"
		+ "CODIGO_ENTIDAD_RECAUDADORA = ?,CODIGO_PUNTO_RECAUDO_ENTIDAD = ?,CODIGO_TIPO_RECAUDO = ?,FECHA_TRANSACCION =?,FECHA_BANCARIA = ?,"
		+ "CODIGO_TRANSACCION_RECAUDADOR = ?,MONTO_TRANSACCION = ?,CODIGO_MENSAJE = ?,"
		+ "ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = ?,IP_MODIFICACION = ?"
		+ "WHERE ID_NOTIFICACION_PAGO_RECIBIDA = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_NOTIFICACION_PAGO_RECIBIDA WHERE ID_NOTIFICACION_PAGO_RECIBIDA = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_SDB_ACC_NOTIFICACION_PAGO_RECIBIDA_ID_NOTIFICACION_PAGO_RECIBIDA.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_NOTIFICACION_PAGO_RECIBIDA (ID_NOTIFICACION_PAGO_RECIBIDA,"
		+ "NUMERO_REFERENCIA_BACHUE,CODIGO_ENTIDAD_RECAUDADORA,CODIGO_PUNTO_RECAUDO_ENTIDAD,CODIGO_TIPO_RECAUDO,FECHA_TRANSACCION,FECHA_BANCARIA,"
		+ "CODIGO_TRANSACCION_RECAUDADOR,MONTO_TRANSACCION,CODIGO_MENSAJE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Metodo para encontrar todos los registros que coincidan
	 * con un Id Notificacino Pago Recibida específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return el valor de notificacion pago recibida
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public NotificacionPagoRecibida findById(String as_param)
	    throws B2BException
	{
		NotificacionPagoRecibida lnpr_npr;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lnpr_npr     = null;
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			if(StringUtils.isValidString(as_param))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lnpr_npr = getObjetFromResultSet(lrs_rs);
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

		return lnpr_npr;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_ACC_NOTIFICACION_PAGO_RECIBIDA.
	 *
	 * @param anpr_param correspondiente al valor del tipo de objeto NotificacionPagoRecibida
	 * @return el valor de notificacion pago recibida
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public NotificacionPagoRecibida insert(NotificacionPagoRecibida anpr_param)
	    throws B2BException
	{
		if(anpr_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					long ll_secuencia;

					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setLong(li_column++, ll_secuencia);

					anpr_param.setIdNotificacionPagoRecibida("" + ll_secuencia);
				}

				lps_ps.setString(li_column++, anpr_param.getNumeroReferenciaBachue());
				lps_ps.setString(li_column++, anpr_param.getCodigoEntidadRecaudadora());
				lps_ps.setString(li_column++, anpr_param.getCodigoPuntoRecaudoEntidad());
				lps_ps.setString(li_column++, anpr_param.getCodigoTipoRecaudo());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anpr_param.getFechaTransaccion()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anpr_param.getFechaBancaria()));
				lps_ps.setString(li_column++, anpr_param.getCodigoTransaccionRecaudador());
				lps_ps.setDouble(li_column++, anpr_param.getMontoTransaccion());
				lps_ps.setString(li_column++, anpr_param.getCodigoMensaje());
				lps_ps.setString(li_column++, anpr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, anpr_param.getIpCreacion());

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

		return anpr_param;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_ACC_NOTIFICACION_PAGO_RECIBIDA.
	 *
	 * @param anpr_param correspondiente al valor del tipo de objeto NotificacionPagoRecibida
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(NotificacionPagoRecibida anpr_param)
	    throws B2BException
	{
		if(anpr_param != null)
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

				lps_ps.setString(li_column++, anpr_param.getNumeroReferenciaBachue());
				lps_ps.setString(li_column++, anpr_param.getCodigoEntidadRecaudadora());
				lps_ps.setString(li_column++, anpr_param.getCodigoPuntoRecaudoEntidad());
				lps_ps.setString(li_column++, anpr_param.getCodigoTipoRecaudo());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anpr_param.getFechaTransaccion()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anpr_param.getFechaBancaria()));
				lps_ps.setString(li_column++, anpr_param.getCodigoTransaccionRecaudador());
				lps_ps.setDouble(li_column++, anpr_param.getMontoTransaccion());
				lps_ps.setString(li_column++, anpr_param.getCodigoMensaje());
				lps_ps.setString(li_column++, anpr_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, anpr_param.getIpModificacion());
				lps_ps.setString(li_column++, anpr_param.getIdNotificacionPagoRecibida());

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
	 * Método para la obtencion del objeto TipoRecaudo.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objeto del ResultSet
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private NotificacionPagoRecibida getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		NotificacionPagoRecibida lnpr_npr;

		lnpr_npr = new NotificacionPagoRecibida();

		lnpr_npr.setIdNotificacionPagoRecibida(ars_rs.getString("ID_NOTIFICACION_PAGO_RECIBIDA"));
		lnpr_npr.setNumeroReferenciaBachue(ars_rs.getString("NUMERO_REFERENCIA_BACHUE"));
		lnpr_npr.setCodigoEntidadRecaudadora(ars_rs.getString("CODIGO_ENTIDAD_RECAUDADORA"));
		lnpr_npr.setCodigoPuntoRecaudoEntidad(ars_rs.getString("CODIGO_PUNTO_RECAUDO_ENTIDAD"));
		lnpr_npr.setCodigoTipoRecaudo(ars_rs.getString("CODIGO_TIPO_RECAUDO"));
		lnpr_npr.setFechaTransaccion(ars_rs.getDate("FECHA_TRANSACCION"));
		lnpr_npr.setFechaBancaria(ars_rs.getDate("FECHA_BANCARIA"));
		lnpr_npr.setCodigoTransaccionRecaudador(ars_rs.getString("CODIGO_TRANSACCION_RECAUDADOR"));
		lnpr_npr.setMontoTransaccion(ars_rs.getDouble("MONTO_TRANSACCION"));
		lnpr_npr.setCodigoMensaje(ars_rs.getString("CODIGO_MENSAJE"));

		fillAuditoria(ars_rs, lnpr_npr);

		return lnpr_npr;
	}
}
