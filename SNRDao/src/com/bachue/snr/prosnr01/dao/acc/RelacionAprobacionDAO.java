package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.RelacionAprobacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Su objetivo es hacer peticiones y retornar resustados producto de estas, a la tabla 
 * SDB_ACC_RELACION_APROBACION.
 *
 * @author mblanco
 */
public class RelacionAprobacionDAO extends BaseDAO
{
	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_RELACION_APROBACION_ID_RELACION_APROBACION.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_RELACION_APROBACION (ID_RELACION_APROBACION, NUMERO_RELACION, TIPO_CALIFICACION, FECHA_RELACION, ID_TURNO, NIR, ESTADO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,SYSTIMESTAMP,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Retorna el valor del objeto de long con el Id generado al insertar el registro.
	 *
	 * @param ara_param correspondiente al valor del tipo de objeto RelacionAprobacion
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insert(RelacionAprobacion ara_param)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(ara_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			Connection lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			lc_connection = getConnection();

			try
			{
				int li_column;
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);
					lps_ps.setLong(li_column++, ll_secuencia);
				}

				lps_ps.setString(li_column++, ara_param.getNumeroRelacion());
				lps_ps.setString(li_column++, ara_param.getTipoCalificacion());
				lps_ps.setString(li_column++, ara_param.getIdTurno());
				lps_ps.setString(li_column++, ara_param.getNir());
				lps_ps.setString(li_column++, ara_param.getEstado());
				lps_ps.setString(li_column++, ara_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ara_param.getIpCreacion());

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
