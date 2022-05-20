package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.npa.RegistroPago;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase DAO que extrae todos los atributos usados en la tabla SDB_ACC_REGISTRO_PAGO.
 *
 * @author Julian Vaca
 */
public class RegistroPagoDAO extends AuditoriaDao
{
	/** Constante cs_UPDATE_REGISTRO_PAGO. */
	private static final String cs_UPDATE_REGISTRO_PAGO = "UPDATE SDB_ACC_REGISTRO_PAGO SET ID_ENTIDAD_RECAUDO = ?, ID_SUCURSAL_RECAUDO = ?, ID_TIPO_RECAUDO = ?,"
		+ "CODIGO_TRANSACCION_RECAUDADOR = ?, FECHA_BANCARIA = ?, FECHA_TRANSACCION = ?, MONTO_TRANSACCION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ESTADO_PAGO = 'PAGADO',"
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE NUMERO_REFERENCIA = ?";

	/** Constante cs_UPDATE_ESTADO_REGISTRO_PAGO. */
	private static final String cs_UPDATE_ESTADO_REGISTRO_PAGO = "UPDATE SDB_ACC_REGISTRO_PAGO SET "
		+ " FECHA_GENERACION_RECIBO = SYSTIMESTAMP, NUMERO_RECIBO_CAJA = ?, ESTADO_GENERACION_RECIBO_CAJA = ?, FECHA_MODIFICACION = SYSTIMESTAMP, "
		+ "ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ? WHERE NUMERO_REFERENCIA = ?";
	private static final String CS_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_REGISTRO_PAGO WHERE ID_SOLICITUD = ?";

	/** Constante CS_FIND_BY_NUMERO_REFERENCIA. */
	private static final String CS_FIND_BY_NUMERO_REFERENCIA = "SELECT * FROM SDB_ACC_REGISTRO_PAGO WHERE NUMERO_REFERENCIA = ?";

	/** Constante cs_FIND_BY_FECHA_BANCARIA_ENVIADO_CONCILIACION. */
	private static final String cs_FIND_BY_FECHA_BANCARIA_ENVIADO_CONCILIACION = "SELECT * FROM SDB_ACC_REGISTRO_PAGO WHERE TRUNC(FECHA_BANCARIA)= TRUNC(?) AND ENVIADO_CONCILIACION = 'S'";

	/**
	 * Método que inserta registro en la tabla SDB_ACC_REGISTRO_PAGO.
	 *
	 * @param arp_parametros Objeto de tipo RegistroPago que contiene los parametros para realizar la insercion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarEstadoPago(RegistroPago arp_parametros)
	    throws B2BException
	{
		if(arp_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont                                     = 1;
			lps_ps                                      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_ESTADO_REGISTRO_PAGO);

				lps_ps.setString(li_cont++, arp_parametros.getNumeroReciboCaja());
				lps_ps.setString(li_cont++, arp_parametros.getEstadoGeneracionReciboCaja());
				lps_ps.setString(li_cont++, arp_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, arp_parametros.getIpModificacion());
				lps_ps.setString(li_cont++, arp_parametros.getIdNumeroReferencia());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarEstadoPago", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que inserta registro en la tabla SDB_ACC_REGISTRO_PAGO.
	 *
	 * @param arp_parametros Objeto de tipo RegistroPago que contiene los parametros para realizar la insercion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void actualizarPago(RegistroPago arp_parametros)
	    throws B2BException
	{
		if(arp_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;

			li_cont     = 1;
			lps_ps      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_REGISTRO_PAGO);

				lps_ps.setString(li_cont++, arp_parametros.getIdEntidadRecaudo());
				lps_ps.setString(li_cont++, arp_parametros.getIdPuntoRecaudoEntidad());
				lps_ps.setString(li_cont++, arp_parametros.getIdTipoRecaudo());
				lps_ps.setString(li_cont++, arp_parametros.getCodigoTransaccionRecaudador());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(arp_parametros.getFechaBancaria()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(arp_parametros.getFechaTransaccion()));
				lps_ps.setDouble(li_cont++, arp_parametros.getMontoTransaccion());
				lps_ps.setString(li_cont++, arp_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, arp_parametros.getIpModificacion());
				lps_ps.setString(li_cont++, arp_parametros.getIdNumeroReferencia());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "actualizarPago", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Find by fecha bancaria enviado conciliacion.
	 *
	 * @param ad_fecha the ad fecha
	 * @return the registro pago
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<RegistroPago> findByFechaBancariaEnviadoConciliacion(Date ad_fecha)
	    throws B2BException
	{
		Collection<RegistroPago> lcrp_return;

		lcrp_return = new ArrayList<RegistroPago>(1);

		if(ad_fecha != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_FECHA_BANCARIA_ENVIADO_CONCILIACION);

				setDate(lps_ps, ad_fecha, 1);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcrp_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByFechaBancariaEnviadoConciliacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcrp_return.isEmpty())
			lcrp_return = null;

		return lcrp_return;
	}

	/**
	 * Método para encontrar un los registro RegistroPago por idSolicitud.
	 *
	 * @param as_idSolicitud String para filtrar en la BD
	 * @return Objeto RegistroPago consultado.
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public RegistroPago findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		RegistroPago lrp_object;

		lrp_object = new RegistroPago();

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(CS_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrp_object = getObjectFromResultSet(lrs_rs);
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

		return lrp_object;
	}

	/**
	 * Find by numero referencia.
	 *
	 * @param as_numeroReferencia de as numero referencia
	 * @return el valor de registro pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroPago findByNumeroReferencia(String as_numeroReferencia)
	    throws B2BException
	{
		RegistroPago lrp_object;

		lrp_object = new RegistroPago();

		if(StringUtils.isValidString(as_numeroReferencia))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(CS_FIND_BY_NUMERO_REFERENCIA);

				lps_ps.setString(1, as_numeroReferencia);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrp_object = getObjectFromResultSet(lrs_rs);
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
		}

		return lrp_object;
	}

	/**
	 * Método para extraer la información de la consulta a la BD.
	 *
	 * @param ars_rs objeto con informacion de la consulta a la BD
	 * @return objeto RegistroPago
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RegistroPago getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroPago lrp_tmp;

		lrp_tmp = new RegistroPago();

		lrp_tmp.setIdRegistroPago(ars_rs.getString("ID_REGISTRO_PAGO"));
		lrp_tmp.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lrp_tmp.setNir(ars_rs.getString("NIR"));
		lrp_tmp.setNumeroReferencia(ars_rs.getString("NUMERO_REFERENCIA"));
		lrp_tmp.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lrp_tmp.setIdTipoCanal(ars_rs.getString("ID_TIPO_CANAL"));
		lrp_tmp.setIdEntidadRecaudo(ars_rs.getString("ID_ENTIDAD_RECAUDO"));
		lrp_tmp.setIdSucursalRecaudo(ars_rs.getString("ID_SUCURSAL_RECAUDO"));
		lrp_tmp.setNumeroCuentaRecaudo(ars_rs.getString("NUMERO_CUENTA_RECAUDO"));
		lrp_tmp.setIdTipoRecaudo(ars_rs.getString("ID_TIPO_RECAUDO"));
		lrp_tmp.setFechaBancaria(ars_rs.getDate("FECHA_BANCARIA"));
		lrp_tmp.setMontoTransaccion(ars_rs.getDouble("MONTO_TRANSACCION"));
		lrp_tmp.setNumeroReciboCaja(ars_rs.getString("NUMERO_RECIBO_CAJA"));
		lrp_tmp.setFechaGeneracionRecibo(ars_rs.getDate("FECHA_GENERACION_RECIBO"));
		lrp_tmp.setIdLiquidacion(ars_rs.getString("ID_LIQUIDACION"));
		lrp_tmp.setConsecutivoLiquidacion(ars_rs.getInt("CONSECUTIVO_LIQUIDACION"));
		lrp_tmp.setIdDocumentoSalida(ars_rs.getLong("ID_DOCUMENTO_SALIDA"));
		lrp_tmp.setEstadoGeneracionReciboCaja(ars_rs.getString("ESTADO_GENERACION_RECIBO_CAJA"));
		lrp_tmp.setCodigoTransaccionRecaudador(ars_rs.getString("CODIGO_TRANSACCION_RECAUDADOR"));
		lrp_tmp.setFechaTransaccion(ars_rs.getDate("FECHA_TRANSACCION"));
		lrp_tmp.setEstadoPago(ars_rs.getString("ESTADO_PAGO"));

		fillAuditoria(ars_rs, lrp_tmp);

		return lrp_tmp;
	}
}
