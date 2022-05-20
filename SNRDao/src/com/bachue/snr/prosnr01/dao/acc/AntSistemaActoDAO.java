package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AntSistemaActo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_ANT_SISTEMA_ACTO.
 *
 * @author Manuel Blanco
 */
public class AntSistemaActoDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ANT_SISTEMA_ACTO (ID_SOLICITUD, ID_CIRCULO, ID_DATOS_ANT_SISTEMA, ID_ACTO, ID_TURNO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ACTIVO) VALUES (?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ANT_SISTEMA_ACTO SET ID_TURNO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ACTIVO = ? WHERE ID_SOLICITUD = ? AND ID_CIRCULO = ? AND ID_DATOS_ANT_SISTEMA = ? AND ID_ACTO = ?";

	/** Constante cs_DELETE_BY_SOLICITUD. */
	private static final String cs_DELETE_BY_SOLICITUD = "DELETE FROM SDB_ACC_ANT_SISTEMA_ACTO WHERE ID_SOLICITUD = ?";

	/** Constante cs_DELETE_BY_ID_DATOS_ANT_SIS. */
	private static final String cs_DELETE_BY_ID_DATOS_ANT_SIS = "DELETE FROM SDB_ACC_ANT_SISTEMA_ACTO WHERE ID_DATOS_ANT_SISTEMA = ?";

	/**
	 * Elimina los registros asociados a un id datos ant sistema.
	 *
	 * @param as_param Cadena de caracteres con el id a utilizar como filtro en la eliminación
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdDatosAntSis(String as_param)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_ID_DATOS_ANT_SIS);

				lps_ps.setString(li_column++, as_param);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdDatosAntSis", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Elimina los registros de un ID solicitud específico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteBySolicitud(String as_param)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			Connection        lc_connection;

			lps_ps            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;
				li_column     = 1;

				lps_ps = lc_connection.prepareStatement(cs_DELETE_BY_SOLICITUD);

				lps_ps.setString(li_column++, as_param);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Calcula la secuencia de un nuevo registro e inserta el registro en la tabla, o actualiza un registro ya
	 * existente.
	 *
	 * @param aasa_asa correspondiente al valor del tipo de objeto AntSistemaActo
	 * @param ab_query Indicador de si se va a insertar o actualizar; true para insertar, false para actualizar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(AntSistemaActo aasa_asa, boolean ab_query)
	    throws B2BException
	{
		if(aasa_asa != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int    li_column;
				String ls_query;

				li_column     = 1;
				ls_query      = ab_query ? cs_INSERT : cs_UPDATE;

				lps_ps = getConnection().prepareStatement(ls_query);

				if(ab_query)
				{
					lps_ps.setString(li_column++, aasa_asa.getIdSolicitud());
					lps_ps.setString(li_column++, aasa_asa.getIdCirculo());
					lps_ps.setString(li_column++, aasa_asa.getIdDatosAntSistema());
					lps_ps.setString(li_column++, aasa_asa.getIdActo());
				}

				lps_ps.setString(li_column++, aasa_asa.getIdTurno());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aasa_asa.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aasa_asa.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aasa_asa.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aasa_asa.getIpModificacion());
				}

				lps_ps.setString(li_column++, aasa_asa.getActivo());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, aasa_asa.getIdSolicitud());
					lps_ps.setString(li_column++, aasa_asa.getIdCirculo());
					lps_ps.setString(li_column++, aasa_asa.getIdDatosAntSistema());
					lps_ps.setString(li_column++, aasa_asa.getIdActo());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}
}
