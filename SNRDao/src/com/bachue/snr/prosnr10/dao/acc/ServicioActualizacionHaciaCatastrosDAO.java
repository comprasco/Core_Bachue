package com.bachue.snr.prosnr10.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr10.common.constants.ErrorKeys;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionHaciaCatastros;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 23/06/2020
 */
public class ServicioActualizacionHaciaCatastrosDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS (ID_TRANSACCION,ID_TIPO_OPERACION,FECHA_REGISTRO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?, ?, SYSTIMESTAMP, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_BUSCA_POR_ID. */
	private static final String cs_BUSCA_POR_ID = "SELECT * FROM  SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS WHERE ID_TRANSACCION = ? ";

	/** Constante cs_ACTUALIZAR_ESTADO_TRANSACCION. */
	private static final String cs_ACTUALIZAR_ESTADO_TRANSACCION = "UPDATE SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS"
		+ "SET ESTADO_REGISTRO = ?, NUMERO_REINTENTOS = ?, FECHA_NOTIFICACION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TRANSACCION = ? ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_ID_TRANSACCION.NEXTVAL FROM DUAL";

	/**
	 * Actualizar estado transaccion.
	 *
	 * @param asahc_param de ServicioActualizacionHaciaCatastros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEstadoTransaccion(ServicioActualizacionHaciaCatastros asahc_param)
	    throws B2BException
	{
		if(asahc_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_ACTUALIZAR_ESTADO_TRANSACCION);

				lps_ps.setString(li_column++, asahc_param.getEstadoRegistro());
				lps_ps.setLong(li_column++, asahc_param.getNumeroReintentos());
				setDate(lps_ps, asahc_param.getFechaNotificacion(), li_column++);
				lps_ps.setString(li_column++, asahc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, asahc_param.getIpModificacion());

				lps_ps.setString(li_column++, asahc_param.getIdTransaccion());

				lps_ps.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "updateEstado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}

			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Buscar por id.
	 *
	 * @param as_idTransaccion de as id transaccion
	 * @return el valor de servicio actualizacion hacia catastros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ServicioActualizacionHaciaCatastros buscarPorId(String as_idTransaccion)
	    throws B2BException
	{
		ServicioActualizacionHaciaCatastros lds_return;

		lds_return = null;

		if(StringUtils.isValidString(as_idTransaccion))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_BUSCA_POR_ID);

				lps_ps.setString(li_contador++, as_idTransaccion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lds_return = getObjectResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "buscarPorId", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lds_return;
	}

	/**
	 * Insercion en la tabla SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS.
	 *
	 * @param asahc_param de ServicioActualizacionHaciaCatastros
	 * @return de string idTransaccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String insert(ServicioActualizacionHaciaCatastros asahc_param)
	    throws B2BException
	{
		String ls_idTransaccion;

		ls_idTransaccion = null;

		if(asahc_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					Object lo_o;

					lo_o = lrs_rs.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
					{
						ls_idTransaccion = ((BigDecimal)lo_o).toString();

						lps_ps.setString(li_column++, ls_idTransaccion);
					}
					else
						throw new B2BException(ErrorKeys.SIN_SECUENCIA_SERVICIO_ACTUALIZACION_DESDE_CATASTROS);
				}

				lps_ps.setString(li_column++, asahc_param.getIdTipoOperacion());
				lps_ps.setString(li_column++, asahc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asahc_param.getIpCreacion());

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

		return ls_idTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor object ServicioActualizacionHaciaCatastros.
	 *
	 * @param ars_rs de ResultSet
	 * @return el valor de ServicioActualizacionHaciaCatastros
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private ServicioActualizacionHaciaCatastros getObjectResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ServicioActualizacionHaciaCatastros lsahc_catastro;

		lsahc_catastro = new ServicioActualizacionHaciaCatastros();

		lsahc_catastro.setIdTransaccion(ars_rs.getString("ID_TRANSACCION"));
		lsahc_catastro.setIdTipoOperacion(ars_rs.getString("ID_TIPO_OPERACION"));
		lsahc_catastro.setFechaNotificacion(ars_rs.getTimestamp("FECHA_NOTIFICACION"));
		lsahc_catastro.setEstadoRegistro(ars_rs.getString("ESTADO_REGISTRO"));
		lsahc_catastro.setNumeroReintentos(ars_rs.getLong("NUMERO_REINTENTOS"));
		lsahc_catastro.setFechaRegistro(ars_rs.getTimestamp("FECHA_REGISTRO"));

		fillAuditoria(ars_rs, lsahc_catastro);

		return lsahc_catastro;
	}
}
