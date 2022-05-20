package com.bachue.snr.prosnr10.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr10.model.acc.ServicioActualizacionDesdeCatastrosDetalle;

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
public class ServicioActualizacionDesdeCatastrosDetalleDAO extends AuditoriaDao
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS_DETALLE (ID_SALVEDAD, ID_SALVEDAD_DETALLE, ID_CIRCULO, TIPO_IDENTIFICACION_PREDIO, NUMERO_IDENTIFICACION_CATASTRAL, ID_MATRICULA, DIRECCION_PREDIO, COMENTARIO_SALVEDAD, FECHA_SALVEDAD, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante cs_FIND_MAX_ID_SALVEDAD_DETALLE_BY_ID_SALVEDAD. */
	private static final String cs_FIND_MAX_ID_SALVEDAD_DETALLE_BY_ID_SALVEDAD = "SELECT MAX(ID_SALVEDAD_DETALLE) AS ID_SALVEDAD_DETALLE FROM SDB_ACC_SERVICIO_ACTUALIZACION_DESDE_CATASTROS_DETALLE WHERE ID_SALVEDAD = ?";

	/**
	 * Insert.
	 *
	 * @param asadcd_param de asadcd param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ServicioActualizacionDesdeCatastrosDetalle asadcd_param)
	    throws B2BException
	{
		if(asadcd_param != null)
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

				lps_ps.setString(li_column++, asadcd_param.getIdSalvedad());
				lps_ps.setString(li_column++, asadcd_param.getIdSalvedadDetalle());
				lps_ps.setString(li_column++, asadcd_param.getIdCirculo());
				lps_ps.setString(li_column++, asadcd_param.getTipoIdentificacionPreido());
				lps_ps.setString(li_column++, asadcd_param.getNumeroIdentificacionCatastral());
				setLong(lps_ps, asadcd_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, asadcd_param.getDireccionPredio());
				lps_ps.setString(li_column++, asadcd_param.getComentarioSalvedad());
				lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(asadcd_param.getFechaSalvedad()));
				lps_ps.setString(li_column++, asadcd_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asadcd_param.getIpCreacion());

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
	public int findMaxIdSalvedadDetalleByIdSalvedad(String ls_idSalvedad)
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

			lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_SALVEDAD_DETALLE_BY_ID_SALVEDAD);

			lps_ps.setString(li_column++, ls_idSalvedad);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_idSalvedadDetalle = NumericUtils.getInt(lrs_rs.getString("ID_SALVEDAD_DETALLE"));
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
