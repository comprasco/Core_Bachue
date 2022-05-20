package com.bachue.snr.prosnr10.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionDesdeCatastros;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS
 *
 * @author Carlos Calderon
 *
 */
public class ServicioActualizacionDesdeCatastrosDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS (ID_SALVEDAD,TIPO_SALVEDAD,DOCUMENTO_SOPORTE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS_ID_SALVEDAD.NEXTVAL FROM DUAL";

	/**
	 * Insert.
	 *
	 * @param asadc_param de asadc param
	 * @return de string
	 * @throws B2BException de b 2 B exception
	 */
	public String insert(ServicioActualizacionDesdeCatastros asadc_param)
	    throws B2BException
	{
		String ls_idSalvedad;

		ls_idSalvedad = null;

		if(asadc_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int         li_column;
				Connection  lc_connection;
				InputStream lis_in;

				li_column         = 1;
				lc_connection     = getConnection();
				lis_in            = new ByteArrayInputStream(asadc_param.getDocumentoSoporte());

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						ls_idSalvedad = ((BigDecimal)lo_o).toString();

						lps_ps.setString(li_column++, ls_idSalvedad);
					}
					else
						throw new B2BException(ErrorKeys.SIN_SECUENCIA_SERVICIO_ACTUALIZACION_DESDE_CATASTROS);
				}

				lps_ps.setString(li_column++, asadc_param.getTipoSalvedad());
				lps_ps.setBinaryStream(li_column++, lis_in);
				lps_ps.setString(li_column++, asadc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asadc_param.getIpCreacion());

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

		return ls_idSalvedad;
	}
}
