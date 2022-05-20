package com.bachue.snr.prosnr04.dao.npa;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.npa.RegistroPago;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase DAO que extrae todos los atributos usados en la tabla SDB_ACC_REGISTRO_PAGO_NOTIFICACION_PAGO
 *
 * @author Julian Vaca
 *
 */
public class RegistrarPagoDAO extends AuditoriaDao
{
	private static final String cs_INSERTAR_REGISTRAR_PAGO         = "INSERT INTO SDB_ACC_REGISTRO_PAGO_NOTIFICACION_PAGO (ID_NUMERO_REFERENCIA, ID_ENTIDAD_RECAUDADORA, ID_PUNTO_RECAUDO_ENTIDAD, ID_TIPO_RECAUDO, CODIGO_TRANSACCION_RECAUDADOR, FECHA_BANCARIA, FECHA_TRANSACCION, MONTO_TRANSACCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?) ";
	private static final String cs_CONSULTAR_POR_NUMERO_REFERENCIA = "SELECT * FROM SDB_ACC_REGISTRO_PAGO_NOTIFICACION_PAGO WHERE ID_NUMERO_REFERENCIA = ?";

	/**
	 * Método que consulta SDB_ACC_REGISTRO_PAGO_NOTIFICACION_PAGO por la llave primaria de la tabla
	 *
	 * @param as_numeroReferencia Objeto de tipo String que contiene la llave primaria a consultar
	 * @return
	 * @throws B2BException
	 */
	public RegistroPago findById(String as_numeroReferencia)
	    throws B2BException
	{
		RegistroPago      ldl_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ldl_return     = null;
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_CONSULTAR_POR_NUMERO_REFERENCIA);

			lps_ps.setString(li_contador++, as_numeroReferencia);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldl_return = getRegistrarPago(lrs_rs);
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

		return ldl_return;
	}

	/**
	 * Método que inserta registro en la tabla SDB_ACC_REGISTRO_PAGO_NOTIFICACION_PAGO
	 *
	 * @param arp_parametros Objeto de tipo RegistroPago que contiene los parametros para realizar la insercion
	 * @throws B2BException
	 */
	public void insertarPago(RegistroPago arp_parametros)
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
				lps_ps = getConnection().prepareStatement(cs_INSERTAR_REGISTRAR_PAGO);

				lps_ps.setString(li_cont++, arp_parametros.getIdNumeroReferencia());
				lps_ps.setString(li_cont++, arp_parametros.getIdEntidadRecaudo());
				lps_ps.setString(li_cont++, arp_parametros.getIdPuntoRecaudoEntidad());
				lps_ps.setString(li_cont++, arp_parametros.getIdTipoRecaudo());
				lps_ps.setString(li_cont++, arp_parametros.getCodigoTransaccionRecaudador());
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(arp_parametros.getFechaBancaria()));
				lps_ps.setTimestamp(li_cont++, DateUtils.getTimestamp(arp_parametros.getFechaTransaccion()));
				lps_ps.setDouble(li_cont++, arp_parametros.getMontoTransaccion());
				lps_ps.setString(li_cont++, arp_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, arp_parametros.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarPago", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/* Retorna una nueva instancia del objeto RegistroPago a partir de un ResultSet que se recibe como parametros.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de un objeto RegistroPago
	 * @throws SQLException si hay algun error en la consulta
	 * @see com.bachue.snr.prosnr04.model.npa.RegistroPago
	 */
	private RegistroPago getRegistrarPago(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroPago lrp_datos;

		lrp_datos = new RegistroPago();

		lrp_datos.setIdNumeroReferencia(ars_rs.getString("ID_NUMERO_REFERENCIA"));
		lrp_datos.setIdEntidadRecaudo(ars_rs.getString("ID_ENTIDAD_RECAUDADORA"));
		lrp_datos.setIdPuntoRecaudoEntidad(ars_rs.getString("ID_PUNTO_RECAUDO_ENTIDAD"));
		lrp_datos.setIdTipoRecaudo(ars_rs.getString("ID_TIPO_RECAUDO"));
		lrp_datos.setCodigoTransaccionRecaudador(ars_rs.getString("CODIGO_TRANSACCION_RECAUDADOR"));
		lrp_datos.setFechaBancaria(ars_rs.getDate("FECHA_BANCARIA"));
		lrp_datos.setFechaTransaccion(ars_rs.getDate("FECHA_TRANSACCION"));
		lrp_datos.setMontoTransaccion(ars_rs.getDouble("MONTO_TRANSACCION"));

		fillAuditoriaCreacion(ars_rs, lrp_datos);

		return lrp_datos;
	}
}
