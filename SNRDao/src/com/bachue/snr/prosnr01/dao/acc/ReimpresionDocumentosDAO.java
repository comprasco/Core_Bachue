package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.reimpresion.ReimpresionRecibos;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_REIMPRESION_DOCUMENTOS.
 *
 * @author ssanchez
 */
public class ReimpresionDocumentosDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_REIMPRESION_DOCUMENTOS(ID_REIMPRESION_DOCUMENTOS, ID_DOCUMENTOS_SALIDA, ID_SOLICITUD, ID_TURNO, ID_ECM, DOC_NAME, ID_TIPO_DOCUMENTAL, IMPRESION_EXITOSA, ID_CAUSAL_REIMPRESION,  ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_REIMPRESION_DOCUMENTOS_ID_REIMPRESION_DOCUMENTOS.NEXTVAL FROM DUAL";

	/**
	 * Inserta un registro
	 * con la información de Reimpresion Recibos suministrada.
	 *
	 * @param arr_param objeto contenedor de la información a insertar
	 * @param as_solicitud objeto de tipo string contenedor de la solicitud a insertar.
	 * @throws B2BException
	 */
	public void insertRecibos(ReimpresionRecibos arr_param, String as_solicitud)
	    throws B2BException
	{
		if((arr_param != null) && StringUtils.isValidString(as_solicitud))
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setLong(li_column++, arr_param.getIdDocumentoSalidaReimpresion());
				lps_ps.setString(li_column++, as_solicitud);
				lps_ps.setString(li_column++, arr_param.getIdTurno());
				lps_ps.setString(li_column++, arr_param.getIdEcm());
				lps_ps.setString(li_column++, arr_param.getIdNombreDocumento());
				lps_ps.setString(li_column++, arr_param.getTipo());
				lps_ps.setString(li_column++, EstadoCommon.S);
				lps_ps.setString(li_column++, arr_param.getIdCausalReimpresion());
				lps_ps.setString(li_column++, arr_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, arr_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertRecibos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Inserta un registro
	 * con la información de Documentos Salida suministrada.
	 *
	 * @param ads_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void insertDocumentos(DocumentosSalida ads_param)
	    throws B2BException
	{
		if((ads_param != null))
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				lps_ps.setLong(li_column++, ads_param.getIdDocumentoSalida());
				lps_ps.setString(li_column++, ads_param.getIdSolicitud());
				lps_ps.setString(li_column++, ads_param.getIdTurno());
				lps_ps.setString(li_column++, ads_param.getIdEcm());
				lps_ps.setString(li_column++, ads_param.getIdNombreDocumento());
				lps_ps.setString(li_column++, ads_param.getIdTipoDocumental());
				lps_ps.setString(li_column++, EstadoCommon.S);
				lps_ps.setString(li_column++, ads_param.getCausalReimpresionValor());
				lps_ps.setString(li_column++, ads_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ads_param.getIpCreacion());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertDocumentos", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}
}
