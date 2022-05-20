package com.bachue.snr.prosnr10.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionHaciaCatastrosDetalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS_DETALLE.
 *
 * @author Carlos Calderon
 */
public class ServicioActualizacionHaciaCatastrosDetalleDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_DETALLE (ID_TRANSACCION, ID_DETALLE_TRANSACCION, ID_MATRICULA, ID_CIRCULO, ID_NATURALEZA_JURIDICA, VERSION_NATURALEZA_JURIDICA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_MAX_ID_SALVEDAD_DETALLE_BY_ID_SALVEDAD. */
	private static final String cs_FIND_MAX_ID_TRANSACCION_DETALLE_BY_ID_TRANSACCION = "SELECT MAX(ID_DETALLE_TRANSACCION) AS ID_DETALLE_TRANSACCION FROM SDB_ACC_SERVICIO_ACTUALIZACION_HACIA_CATASTROS_DETALLE WHERE ID_TRANSACCION = ?";

	/**
	 * Insert.
	 *
	 * @param asahcd_param de ServicioActualizacionHaciaCatastrosDetalle
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ServicioActualizacionHaciaCatastrosDetalle asahcd_param)
	    throws B2BException
	{
		if(asahcd_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, asahcd_param.getIdTransaccion());
				lps_ps.setString(li_column++, asahcd_param.getIdDetalleTransaccion());
				lps_ps.setLong(li_column++,asahcd_param.getIdMatricula());
				lps_ps.setString(li_column++, asahcd_param.getIdCirculo());
				lps_ps.setString(li_column++, asahcd_param.getIdNaturalezaJuridica());
				lps_ps.setLong(li_column++,asahcd_param.getVersionNaturalezaJuridica());
				lps_ps.setString(li_column++, asahcd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asahcd_param.getIpCreacion());

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
	 * Find max id salvedad detalle by id salvedad.
	 *
	 * @param ls_idSalvedad de ls id salvedad
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int findMaxIdTransaccionDetalleByIdTransaccion(String ls_idSalvedad)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		int               li_idSalvedadDetalle;

		lps_ps                   = null;
		lrs_rs                   = null;
		li_idSalvedadDetalle     = 0;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_TRANSACCION_DETALLE_BY_ID_TRANSACCION);

			lps_ps.setString(li_column++, ls_idSalvedad);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_idSalvedadDetalle = NumericUtils.getInt(lrs_rs.getString("ID_DETALLE_TRANSACCION"));
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

		return li_idSalvedadDetalle;
	}
}
