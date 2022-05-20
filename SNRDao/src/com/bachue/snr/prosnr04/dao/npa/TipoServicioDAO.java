package com.bachue.snr.prosnr04.dao.npa;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr04.model.npa.TipoServicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase DAO que extrae todos los atributos usados en la tabla SDB_ACC_TIPO_SERVICIO_NOTIFICACION_PAGO
 *
 * @author Julian Vaca
 *
 */
public class TipoServicioDAO extends AuditoriaDao
{
	
	/** Constante cs_INSERTAR_TIPO_SERVICIO. */
	private static final String cs_INSERTAR_TIPO_SERVICIO = "INSERT INTO SDB_ACC_TIPO_SERVICIO_NOTIFICACION_PAGO (ID_TIPO_SERVICIO_NOTIFICACION_PAGO,ID_NUMERO_REFERENCIA, ID_PROCESO, ID_SUBPROCESO, CANTIDAD_SOLICITADA, VALOR_UNITARIO, VALOR_TOTAL, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_TIPO_SERVICIO_NOTIFICACION_PAGO_ID_TIPO_SERVICIO_NOTIFICACION_PAGO.NEXTVAL FROM DUAL";

	/**
	 * Método que inserta registro en la tabla SDB_ACC_TIPO_SERVICIO_NOTIFICACION_PAGO.
	 *
	 * @param ats_parametros Objeto de tipo TipoServicio que contiene los parametros para realizar la insercion
	 * @return el valor de tipo servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoServicio insertarTipoServicio(TipoServicio ats_parametros)
	    throws B2BException
	{
		if(ats_parametros != null)
		{
			int               li_cont;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			li_cont     	  = 1;
			lps_ps      	  = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				long ll_secuencia;

				ll_secuencia = 0;

				lps_ps = lc_connection.prepareStatement(cs_INSERTAR_TIPO_SERVICIO);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setLong(li_cont++, ll_secuencia);
					ats_parametros.setIdTipoServicioNotificacionPago(""+ll_secuencia);
				}

				lps_ps.setString(li_cont++, ats_parametros.getIdNumeroReferencia());
				lps_ps.setString(li_cont++, ats_parametros.getIdProceso());
				lps_ps.setString(li_cont++, ats_parametros.getIdSubProceso());
				lps_ps.setLong(li_cont++, ats_parametros.getCantidadSolicitada());
				lps_ps.setDouble(li_cont++, ats_parametros.getValorUnitario());
				lps_ps.setDouble(li_cont++, ats_parametros.getValorTotal());
				lps_ps.setString(li_cont++, ats_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, ats_parametros.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertarTipoServicio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
		return ats_parametros;
	}
}
