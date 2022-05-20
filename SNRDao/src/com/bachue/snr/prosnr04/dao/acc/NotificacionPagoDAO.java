package com.bachue.snr.prosnr04.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.acc.NotificacionPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las propiedades NotificacionPagoDAO en donde se insertan, actualizan y consultar valores
 * correspondientes a la tabla de la base de datos SDB_ACC_NOTIFICACION_PAGO
 *
 * @author Julian Vaca
 */
public class NotificacionPagoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_NUMERO_REFERENCIA. */
	private static final String cs_FIND_BY_NUMERO_REFERENCIA = "SELECT * FROM SDB_ACC_NOTIFICACION_PAGO WHERE NUMERO_REFERENCIA_SNR = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_NOTIFICACION_PAGO SET ESTADO = ?,FECHA_LIQUIDACION = ?, FECHA_VENCIMIENTO = ?, ID_NOTIFICACION_PAGO_RECIBIDA = ?, ID_PUNTO_RECAUDO_TIPO_RECAUDO = ?, "
		+ "ID_SUCURSAL_CANAL_ORIGEN_SERVICIO = ?, FECHA_TRANSACCION = ?, FECHA_BANCARIA = ?, NUMERO_TRANSACCION_RECAUDADOR = ?, MONTO_TRANSACCION = ?, FECHA_RECIBO_CAJA = ?, "
		+ "ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE NUMERO_REFERENCIA_SNR = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_NOTIFICACION_PAGO (ESTADO,NUMERO_REFERENCIA_SNR, FECHA_LIQUIDACION, FECHA_VENCIMIENTO, ID_NOTIFICACION_PAGO_RECIBIDA, ID_PUNTO_RECAUDO_TIPO_RECAUDO, "
		+ "ID_SUCURSAL_CANAL_ORIGEN_SERVICIO, FECHA_TRANSACCION, FECHA_BANCARIA, NUMERO_TRANSACCION_RECAUDADOR, MONTO_TRANSACCION,  "
		+ "ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ "VALUES(?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?, SYSTIMESTAMP, ?)";

	/**
	 * Metodo para encontrar los registros que coincidan
	 * con un numero referencia snr específico de la tabla SDB_ACC_NOTIFICACION_PAGO.
	 *
	 * @param as_numeroReferenciaSNR correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto notificacion pago
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.acc.NotificacionPago.NotificacionPago
	 */
	public NotificacionPago findByNumeroReferencia(String as_numeroReferenciaSNR)
	    throws B2BException
	{
		NotificacionPago  lpr_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpr_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(StringUtils.isValidString(as_numeroReferenciaSNR))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUMERO_REFERENCIA);

				lps_ps.setString(1, as_numeroReferenciaSNR);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_return = getObjetFromResultSet(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNumeroReferencia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_return;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_ACC_NOTIFICACION_PAGO.
	 *
	 * @param anp_param correspondiente al valor del tipo de objeto NotificacionPago
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(NotificacionPago anp_param)
	    throws B2BException
	{
		if(anp_param != null)
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

				lps_ps.setString(li_column++, anp_param.getEstado());
				lps_ps.setString(li_column++, anp_param.getNumeroReferenciaSNR());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaVencimiento()));
				lps_ps.setString(li_column++, anp_param.getIdNotificacionPagoRecibida());
				lps_ps.setString(li_column++, anp_param.getIdPuntoRecaudoTipoRecaudo());
				lps_ps.setString(li_column++, anp_param.getIdSucursalCanalOrigenServicio());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaTransaccion()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaBancaria()));
				lps_ps.setString(li_column++, anp_param.getNumeroTransaccionRecaudador());
				lps_ps.setDouble(li_column++, anp_param.getMontoTransaccion());
				lps_ps.setString(li_column++, anp_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, anp_param.getIpCreacion());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_ACC_NOTIFICACION_PAGO.
	 *
	 * @param anp_param correspondiente al valor del tipo de objeto NotificacionPago
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(NotificacionPago anp_param)
	    throws B2BException
	{
		if(anp_param != null)
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

				lps_ps.setString(li_column++, anp_param.getEstado());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaLiquidacion()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaVencimiento()));
				lps_ps.setString(li_column++, anp_param.getIdNotificacionPagoRecibida());
				lps_ps.setString(li_column++, anp_param.getIdPuntoRecaudoTipoRecaudo());
				lps_ps.setString(li_column++, anp_param.getIdSucursalCanalOrigenServicio());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaTransaccion()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaBancaria()));
				lps_ps.setString(li_column++, anp_param.getNumeroTransaccionRecaudador());
				lps_ps.setDouble(li_column++, anp_param.getMontoTransaccion());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(anp_param.getFechaReciboCaja()));
				lps_ps.setString(li_column++, anp_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, anp_param.getIpModificacion());
				lps_ps.setString(li_column++, anp_param.getNumeroReferenciaSNR());

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
	 * Método para la obtencion del objeto NotificacionPago.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de objeto del tipo NotificacionPago
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see com.bachue.snr.prosnr04.model.acc.NotificacionPago.NotificacionPago
	 */
	private NotificacionPago getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		NotificacionPago lnp_np;

		lnp_np = new NotificacionPago();

		lnp_np.setNumeroReferenciaSNR(ars_rs.getString("NUMERO_REFERENCIA_SNR"));
		lnp_np.setFechaLiquidacion(ars_rs.getTimestamp("FECHA_LIQUIDACION"));
		lnp_np.setFechaVencimiento(ars_rs.getTimestamp("FECHA_VENCIMIENTO"));
		lnp_np.setIdNotificacionPagoRecibida(ars_rs.getString("ID_NOTIFICACION_PAGO_RECIBIDA"));
		lnp_np.setIdPuntoRecaudoTipoRecaudo(ars_rs.getString("ID_PUNTO_RECAUDO_TIPO_RECAUDO"));
		lnp_np.setIdSucursalCanalOrigenServicio(ars_rs.getString("ID_SUCURSAL_CANAL_ORIGEN_SERVICIO"));
		lnp_np.setFechaTransaccion(ars_rs.getTimestamp("FECHA_TRANSACCION"));
		lnp_np.setFechaBancaria(ars_rs.getTimestamp("FECHA_BANCARIA"));
		lnp_np.setNumeroTransaccionRecaudador(ars_rs.getString("NUMERO_TRANSACCION_RECAUDADOR"));
		lnp_np.setMontoTransaccion(ars_rs.getDouble("MONTO_TRANSACCION"));
		lnp_np.setFechaReciboCaja(ars_rs.getTimestamp("FECHA_RECIBO_CAJA"));
		lnp_np.setEstado(ars_rs.getString("ESTADO"));

		fillAuditoria(ars_rs, lnp_np);

		return lnp_np;
	}
}
