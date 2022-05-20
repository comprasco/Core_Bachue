package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.NotificacionDetalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Su objetivo es hacer peticiones y retornar resultados producto de estas, a la tabla
 * SDB_ACC_NOTIFICACION_DETALLE.
 *
 * @author mblanco
 */
public class NotificacionDetalleDAO extends BaseDAO
{
	/** Constante cs_SECUENCIA. */
	private static final String cs_SECUENCIA = "SELECT SEC_ACC_NOTIFICACION_DETALLE_ID_NOTIFICACION_DETALLE.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_NOTIFICACION_DETALLE (ID_NOTIFICACION_DETALLE, ID_TURNO, ID_PERSONA, FECHA_NOTIFICACION_ENVIO, ID_TIPO_NOTIFICACION_ENVIO, FECHA_ACUSE_RECIBO, FECHA_NOTIFICACION, ID_TIPO_NOTIFICACION, RENUNCIA_TERMINOS, FECHA_RENUNCIA, INTERPONE_RECURSO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,SYSTIMESTAMP,SYSTIMESTAMP,?,?,SYSTIMESTAMP,?,?,SYSTIMESTAMP,?)";

	/**
	 * Inserta un registro en la tabla SDB_ACC_NOTIFICACION_DETALLE.
	 *
	 * @param and_param Objeto contenedor de la información a insertar en el registro
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insert(NotificacionDetalle and_param)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(and_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lc_connection     = getConnection();
			lrs_rs            = null;

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);
					lps_ps.setLong(li_column++, ll_secuencia);
				}

				lps_ps.setString(li_column++, and_param.getIdTurno());
				lps_ps.setString(li_column++, and_param.getIdPersona());
				lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(and_param.getFechaNotificacionEnvio()));
				lps_ps.setString(li_column++, and_param.getIdTipoNotificacionEnvio());
				lps_ps.setString(li_column++, and_param.getIdTipoNotificacion());
				lps_ps.setString(li_column++, and_param.getRenunciaTerminos());
				lps_ps.setString(li_column++, and_param.getInterponeRecurso());
				lps_ps.setString(li_column++, and_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, and_param.getIpCreacion());

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

		return ll_secuencia;
	}
}
